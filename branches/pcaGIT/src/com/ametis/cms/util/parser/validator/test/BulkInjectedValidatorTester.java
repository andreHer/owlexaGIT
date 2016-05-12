package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.util.parser.validator.PolicyNumberValidator;

public class BulkInjectedValidatorTester {

	private MemberService memberService;
	private PolicyService policyService;
	private ClientService clientService;
	private ProductService productService;
	
	
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void testFields(){
		System.out.println("TEST POLICY NUMBER = POLIS GROUP RE-TEST 01  | CLIENT CODE = 007" + PolicyNumberValidator.isValid("POLIS GROUP RE-TEST 01","007", policyService) );
		
	}
}
