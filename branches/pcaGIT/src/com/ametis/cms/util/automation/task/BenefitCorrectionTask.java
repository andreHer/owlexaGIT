package com.ametis.cms.util.automation.task;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberProductService;

public class BenefitCorrectionTask {

	private MemberProductService memberProductService;

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}
	
	public void executeCorrection(){
		try {
			
			User theUser = new User();
			theUser.setUsername("benefit-corrector");
			ActionUser user = new ActionUser();
			user.setUser(theUser);
			
			System.out.println("Execute Benefit Correction");
			memberProductService.updateInconsistentFamilyPlan(user);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
