package com.ametis.cms.webservice;

import javax.jws.WebService;

import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.FirstCallService;
import com.ametis.cms.service.MemberService;


@WebService(name = "CRMWebService", 
		endpointInterface = "com.ametis.cms.webservice.ICRMWebService",
		serviceName="CRMWebService")
		
		
public class CRMWebServiceImpl  implements ICRMWebService {

	private ClientService clientService;
	
	
	private MemberService memberService;
	private ClaimService claimService;
	private CaseService caseService;
	private FirstCallService firstCallService;
	
	
	
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}
	public CaseService getCaseService() {
		return caseService;
	}
	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	public FirstCallService getFirstCallService() {
		return firstCallService;
	}
	public void setFirstCallService(FirstCallService firstCallService) {
		this.firstCallService = firstCallService;
	}
	
	
	

}
