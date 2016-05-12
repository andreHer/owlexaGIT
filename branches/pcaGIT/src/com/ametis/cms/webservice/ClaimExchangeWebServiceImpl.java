package com.ametis.cms.webservice;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.xfire.transport.http.XFireServletController;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.dto.ClaimExchangeDetailDto;
import com.ametis.cms.dto.ClaimExchangeDto;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.UserService;

@WebService(name = "ClaimExchangeWebService", endpointInterface = "com.ametis.cms.webservice.IClaimExchangeWebService",
		serviceName="ClaimExchangeWebService")
public class ClaimExchangeWebServiceImpl implements IClaimExchangeWebService {

	private ClaimService claimService;
	
	private ClaimItemService claimItemService;
	
	public ClaimService getClaimService() {
		return claimService;
	}


	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}


	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}


	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}


	public Collection<ClaimExchangeDto> getClaimExchange(Integer claimId,
			String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
		Collection<ClaimExchangeDto> result = new Vector<ClaimExchangeDto>();
		
		try {
			System.out.println("GENERATING CLAIM EXCHANGE");
			String[] eqParam = {"deletedStatus","claimId"};
			Object[] eqValue = {Integer.valueOf(0),claimId};
			
			String[] required = {
					"Claim.MemberId", "Claim.MemberId.MemberGroupId", 
					"Claim.BatchClaimId", "Claim.ClaimStatus", 
					"Claim.DiagnosisId", "Claim.Diagnosis2Id", "Claim.Diagnosis3Id",
					"Claim.PaymentId", "Claim.CaseCategoryId", "Claim.PolicyId", "Claim.ProductId", "Claim.ProviderId",
					"Claim.ClaimCurrencyId"
					};
			
			//Collection<Claim> tempClaimList = claimService.search(null,null,eqParam,eqValue,0,15);
			Collection<Claim> tempClaimList = claimService.search(null, null, eqParam, eqValue, required, 0, 15);
			
			
			if (tempClaimList != null){
				for (Iterator iterator = tempClaimList.iterator(); iterator.hasNext();) {
					Claim claim = (Claim) iterator.next();
					
					if (claim != null)
					{	
						System.out.println("Create Claim Exchange for Claim ID : "+ claim.getClaimId() + " , Claim Number : " + claim.getClaimNumber());
						//create the header
						ClaimExchangeDto dto = claim.exportExchangeDto();
						
						//now create the details fufufufu
						String[] eqParamItem = {"deletedStatus","claimId.claimId"};
						Object[] eqValueItem = {Integer.valueOf(0),claimId};
						
						String[] requiredItem = {
								"ClaimItem.ClaimId", "ClaimItem.ItemId"
								};
						Collection<ClaimItem> tempClaimItemList = claimItemService.search(null, null, eqParamItem, eqValueItem, requiredItem, 0, 15);
						
						Collection<ClaimExchangeDetailDto> claimDetail = new Vector<ClaimExchangeDetailDto>();
						
						if (tempClaimItemList != null){
							for (Iterator iteratorItem = tempClaimItemList.iterator(); iteratorItem.hasNext();) {
								ClaimItem claimItem = (ClaimItem) iteratorItem.next();
								if(claimItem != null){
									System.out.println("Create Claim Exchange Detail for Claim ID : "+ claim.getClaimId() + " , Claim Item : " + claimItem.getItemCode());
									
									ClaimExchangeDetailDto detailDto = claimItem.exportExchangeDetail();
									
									claimDetail.add(detailDto);
								}
							}
								
						}
						
						dto.setClaimExchangeDetailList(claimDetail);
						
						result.add(dto);
					}
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}



}
