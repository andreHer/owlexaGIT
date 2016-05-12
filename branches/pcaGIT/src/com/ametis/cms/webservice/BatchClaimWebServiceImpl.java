package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentMethod;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.dto.BatchClaimDto;
import com.ametis.cms.dto.PaymentDto;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.PaymentService;

@WebService(name = "BatchClaimWebService", endpointInterface = "com.ametis.cms.webservice.IBatchClaimWebService")
public class BatchClaimWebServiceImpl implements IBatchClaimWebService{

	private BatchClaimService batchClaimService;
	private PaymentService paymentService;
	
	
	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public String createBatchClaim(BatchClaimDto batchClaim, ActionUser actionUser) {
		// TODO Auto-generated method stub
		
		String result = "";
		
		try {
			BatchClaim batch = convertBatchClaim(batchClaim); 
			
			batchClaimService.create(batch, actionUser);
			
			if (batch != null){
				result = "success";
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	private BatchClaim convertBatchClaim (BatchClaimDto batchClaimDto){
		BatchClaim result =null;
		
		try {
			
			if (batchClaimDto != null){
				result = new BatchClaim();
				result.setBatchClaimCloseDate(batchClaimDto.getBatchClaimCloseDate());
				result.setBatchClaimDate(batchClaimDto.getBatchClaimDate());
				result.setBatchClaimDatePsea(batchClaimDto.getBatchClaimDatePsea());
				result.setBatchClaimFinalValue(batchClaimDto.getBatchClaimFinalValue());
				
				result.setBatchClaimId(batchClaimDto.getBatchClaimId());
				result.setBatchClaimInitialValue(batchClaimDto.getBatchClaimInitialValue());
				result.setBatchClaimNumber(batchClaimDto.getBatchClaimNumber());
				result.setBatchClaimPaidValue(batchClaimDto.getBatchClaimPaidValue());
				
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(batchClaimDto.getBatchClaimStatus());
				result.setBatchClaimStatus(status);
				
				ClaimType claimType = new ClaimType();
				claimType.setClaimTypeId(batchClaimDto.getBatchClaimType());
				result.setBatchClaimType(claimType);
				
				result.setBatchDueDate(batchClaimDto.getBatchDueDate());
				result.setBatchExcessValue(batchClaimDto.getBatchExcessValue());
				result.setBatchNumberPsea(batchClaimDto.getBatchNumberPsea());
				result.setBatchPaidExcessValue(batchClaimDto.getBatchPaidExcessValue());
				
				Currency currency = new Currency();
				currency.setCurrencyId(batchClaimDto.getClaimCurrency());
				
				result.setClaimCurrency(currency);
				
				Client client = new Client();
				client.setClientId(batchClaimDto.getClientId());
				result.setClientId(client);
				
				result.setCreatedBy(batchClaimDto.getCreatedBy());
				result.setCreatedTime(batchClaimDto.getCreatedTime());
				result.setModifiedBy(batchClaimDto.getModifiedBy());
				result.setModifiedTime(batchClaimDto.getModifiedTime());
				
				result.setDescription(batchClaimDto.getDescription());
				result.setInvoiceDate(batchClaimDto.getInvoiceDate());
				result.setInvoiceNumber(batchClaimDto.getInvoiceNumber());
				
				result.setLastUpdateBatchPsea(batchClaimDto.getLastUpdateBatchPsea());
				
				if (batchClaimDto.getMemberGroupId() != null){
					MemberGroup memberGroup = new MemberGroup();
					memberGroup.setMemberGroupId(batchClaimDto.getMemberGroupId());
					result.setMemberGroupId(memberGroup);
				}
				
				if (batchClaimDto.getMemberId() != null){
					Member member = new Member();
					member.setMemberId(batchClaimDto.getMemberId());
					result.setMemberId(member);
				}
				
				if (batchClaimDto.getProviderId() != null){
					Provider provider = new Provider();
					provider.setProviderId(batchClaimDto.getProviderId());
					result.setProviderId(provider );
				}
				
				Currency paymentCurrency = new Currency();
				paymentCurrency.setCurrencyId(batchClaimDto.getPaymentCurrency());
				result.setPaymentCurrency(paymentCurrency);
				
				result.setPaymentDate(batchClaimDto.getPaymentDate());
				
				PaymentMethod paymentMethod = new PaymentMethod();
				paymentMethod.setPaymentMethodId(batchClaimDto.getPaymentMethod());
				
				result.setPaymentMethod(paymentMethod);
				
				PaymentRecipient paymentRecipient = new PaymentRecipient();
				paymentRecipient.setPaymentRecipientId(batchClaimDto.getPaymentRecipient());
				result.setPaymentRecipient(paymentRecipient);
				
				Priority priority = new Priority();
				priority.setPriorityId(batchClaimDto.getPriorityId());
				result.setPriority(priority);
				
				result.setTotalClaim(batchClaimDto.getTotalClaim());
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public String createBatchClaimWithCDV(BatchClaimDto batchClaimDto,String cdvNumber,String cdvDate, ActionUser actionUser) {
		// TODO Auto-generated method stub
		
		String result = "";
		
		try {
			BatchClaim batchClaim = convertBatchClaim(batchClaimDto) ;
				
			BatchClaim batch =	batchClaimService.create(batchClaim, actionUser);
			
			if (batch != null){
				result = "success";
				
				Payment payment = new Payment();
				payment.setBatchClaim(batch);
				payment.setPaymentNumber(cdvNumber);
				
				payment.setPaymentTime(Date.valueOf(cdvDate));
				
				paymentService.create(payment, actionUser);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public BatchClaim getBatchClaim(String number) {
		// TODO Auto-generated method stub
		
		BatchClaim result = null;
		
		try {
			
			String[] param = {"batchClaimNumber"};
			Object[] value = {number};
			
			int total = batchClaimService.getTotal(param,value,null,null);
			Collection<BatchClaim> batchClaimCollections = batchClaimService.search(null,null,param,value,0, total);
			
			if (batchClaimCollections != null){
				java.util.Iterator<BatchClaim> batchIterator = batchClaimCollections.iterator();
				if (batchIterator != null){
					result = batchIterator.next();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public BatchClaim getBatchClaimByOtherNumber(String otherNumber) {
		// TODO Auto-generated method stub
		BatchClaim result = null;
		
		try {
			
			String[] param = {"batchNumberPsea"};
			Object[] value = {otherNumber};
			
			int total = batchClaimService.getTotal(param,value,null,null);
			Collection<BatchClaim> batchClaimCollections = batchClaimService.search( null,null,param,value,0, total);
			
			if (batchClaimCollections != null){
				java.util.Iterator<BatchClaim> batchIterator = batchClaimCollections.iterator();
				if (batchIterator != null){
					result = batchIterator.next();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	public String createBatchClaimWithPayment(BatchClaimDto batchClaim, PaymentDto paymentDto,
			ActionUser actionUser) {
		// TODO Auto-generated method stub
		String result = "";
		try {
			if (batchClaim != null){
				BatchClaim batch = convertBatchClaim(batchClaim);
				
				batch = batchClaimService.create(batch, actionUser);
				
				if (batch != null){
					
					Payment payment = paymentService.convertPayment(paymentDto);
					
					payment.setBatchClaim(batch);
					
					paymentService.create(payment, actionUser);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
