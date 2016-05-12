package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.PaymentResult;
import com.ametis.cms.dto.CaseCategoryDto;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimExchangeDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.ClaimSCDto;
import com.ametis.cms.dto.PaymentTransactionClientLogDto;


@WebService (targetNamespace="http://ametis.co.id/services/ClaimWebService")
public interface IClaimWebService {
	@WebMethod
	public Collection<ClaimSCDto> getLastClaim (String memberNumber);
	
	@WebMethod
	public CaseCategoryDto getCaseCategory(String edcCode);
	
	
	@WebMethod
	public Collection<ClaimDto> getMemberClaimList (@WebParam(name="memberId",header=true) Integer memberId, Date startDate, Date endDate);
	@WebMethod
	public Collection<ClaimItemDto> getMemberClaimDetailList (Integer memberId, Date startDate, Date endDate);
	//Add by aju on 20150909, for getting claim detail per claimId
	@WebMethod
	public Collection<ClaimItemDto> getMemberClaimDetailByClaimId (Integer claimId);
	
	@WebMethod
	public ActionResult reverseVoidClaim( String merchantId, String terminalId, String traceNumber)
	throws Exception;
	
	@WebMethod
	public ActionResult commitSynchronizeClaim (Integer claimId, String username, String password) throws Exception;
	
	@WebMethod
	public ActionResult commitSynchronizeClaimItem (Integer claimItemId, String username, String password) throws Exception;
	
	@WebMethod
	public ActionResult commitClaimPaid (Integer claimId,Date paymentDate, String username, String password) throws Exception;
	
	@WebMethod
	public Collection<ClaimDto> getReconcileClaimList (String username, String password) throws Exception;
	
	@WebMethod
	public Collection<ClaimItemDto> getReconcileClaimItemList (Integer claimId, String username, String password) throws Exception;
	
	
	@WebMethod
	public ActionResult voidClaim( String tracenumber,String referenceNumber,
			String merchantId, String terminalId) throws Exception ;
	
	@WebMethod
	public ActionResult registerClaim(ClaimSCDto claimDto,
			Collection<ClaimItemDto> claimDetails, String merchantId, String terminalId, String traceNumber,String referenceNumber)
			throws Exception ;
	
	@WebMethod
	public ActionResult registerClaimSys(ClaimSCDto claimDto,
			Collection<ClaimItemDto> claimDetails, String merchantId, String terminalId, String traceNumber,String referenceNumber)
			throws Exception ;
	
	
	@WebMethod
	public ActionResult referCase(String cardNum,String refNum,String poliId, 
			String merchantId, String terminalId, String traceNumber ) throws Exception ;
	
	@WebMethod
	public ActionResult inquiryReference(String cardNum,String refNum,String merchantId, String terminalId) throws Exception;
	
	@WebMethod
	public ActionResult reverseVoidClaim( String cardNumber, String merchantId, String terminalId, String traceNumber) throws Exception ;
	
	@WebMethod 
	public String ping ()throws Exception;
	
	@WebMethod
	public Collection<ClaimExchangeDto> getClaimExchange (Integer claimId, String username, String password) throws Exception;
	
	@WebMethod
	public PaymentResult claimPaymentActivity(PaymentTransactionClientLogDto log, String username) throws Exception;
}