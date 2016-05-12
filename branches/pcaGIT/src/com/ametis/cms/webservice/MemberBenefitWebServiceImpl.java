package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.dto.MemberBenefitDto;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberService;

@WebService(name = "MemberBenefitWebService", endpointInterface = "com.ametis.cms.webservice.IMemberBenefitWebService")

public class MemberBenefitWebServiceImpl implements IMemberBenefitWebService {

	private MemberBenefitService memberBenefitService;
	private MemberService memberService;
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

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public Collection<MemberBenefitDto> getAllMemberBenefit(
			Integer memberGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean synchronize(Integer memberBenefitId, ActionUser actionUser) {
		// TODO Auto-generated method stub
		return false;
	}

}
