package com.ametis.cms.util.automation.task;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.User;

import com.ametis.cms.service.PaymentService;

public class PaymentUploadGeneratorTask {

	private PaymentService paymentService;
	
	public PaymentService getPaymentService() {
		return paymentService;
	}
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	
	public void processPaymentUpload(){
		try {
			System.out.println("PROCESS UPLOAD PAYMENT");
			ActionUser actionUser = new ActionUser();
			User user = new User();
			user.setUsername("system-daemon");
			
			actionUser.setUser(user);
			paymentService.deliverPaymentUploadGenerator(actionUser);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
