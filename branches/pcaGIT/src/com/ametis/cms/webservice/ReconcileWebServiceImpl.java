package com.ametis.cms.webservice;

import javax.jws.WebService;

import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberService;

@WebService(name = "ReconcileWebService", endpointInterface = "com.ametis.cms.webservice.IReconcileWebService")
public class ReconcileWebServiceImpl implements IReconcileWebService{

	private ClaimService claimService;
	private ClaimItemService claimItemService;
	private MemberService memberService;
	
	
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
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
}
