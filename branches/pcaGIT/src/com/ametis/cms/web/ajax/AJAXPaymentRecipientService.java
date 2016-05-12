package com.ametis.cms.web.ajax;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.ClaimReceiving;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.dto.ClaimReceiveDto;
import com.ametis.cms.service.ClaimReceivingService;
import com.ametis.cms.service.PaymentRecipientService;

public class AJAXPaymentRecipientService {
	private PaymentRecipientService paymentRecipientService;
	private ClaimReceivingService claimReceiveService;
	
	

	public ClaimReceivingService getClaimReceiveService() {
		return claimReceiveService;
	}

	public void setClaimReceiveService(ClaimReceivingService claimReceiveService) {
		this.claimReceiveService = claimReceiveService;
	}

	public PaymentRecipientService getPaymentRecipientService() {
		return paymentRecipientService;
	}

	public void setPaymentRecipientService(
			PaymentRecipientService paymentRecipientService) {
		this.paymentRecipientService = paymentRecipientService;
	
	}
	public ClaimReceiveDto completeBatchClaimForm (int claimReceiveId){
		ClaimReceiveDto result = null;
		
		try {
			ClaimReceiving receive = claimReceiveService.get(claimReceiveId);
			
			if (receive != null){
				result = new ClaimReceiveDto();
				result.setClientId(receive.getClientId() == null ? "" : receive.getClientId().getClientId().toString());
				result.setClientName(receive.getClientId() == null ? "" : receive.getClientId().getClientName());
				result.setMemberGroupId(receive.getMemberGroupId() == null ? "" : receive.getMemberGroupId().getMemberGroupId().toString());
				result.setMemberGroupName(receive.getMemberGroupId() == null ? "" : receive.getMemberGroupId().getGroupName());
				result.setProviderId(receive.getProviderId() == null ? "" : receive.getProviderId().getProviderId().toString());
				result.setProviderName(receive.getProviderId() == null ? "" : receive.getProviderId().getProviderName());
				result.setReceiveDate(receive.getReceiveDate() == null ? "" : receive.getReceiveDate().toString());
				
				//riyan -> request nilai totalclaim dari claim receivings
//				result.setTotalReceiving(receive.getTotalReceiving() == null ? "" : receive.getTotalReceiving().toString());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	public String getPaymentRecipient (int claimType){
		String result = null;
		
		try {
			Collection<PaymentRecipient> tmp = null;
			if (claimType == ClaimType.CASHLESS){
				tmp = paymentRecipientService.getCashlessRecipient();
			}
			else if (claimType == ClaimType.REIMBURESEMENT || claimType == ClaimType.REIMBURSEMENT_KHUSUS){
				tmp = paymentRecipientService.getReimbursementRecipient();
			}
			else {
				tmp = paymentRecipientService.getAll();
			}
			
			if (tmp != null ){
				Iterator<PaymentRecipient> iterator = tmp.iterator();
				result = "";
				
				PaymentRecipient recipient = null;
				
				while (iterator.hasNext()){
					recipient = iterator.next();
					
					result += "<option value='"+recipient.getPaymentRecipientId()+"'>"+recipient.getPaymentRecipientName()+"</option>";
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
