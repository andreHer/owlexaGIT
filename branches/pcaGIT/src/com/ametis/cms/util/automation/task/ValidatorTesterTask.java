package com.ametis.cms.util.automation.task;

import com.ametis.cms.service.BankService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.util.parser.validator.ClientCodeValidator;
import com.ametis.cms.util.parser.validator.ClientNameValidator;
import com.ametis.cms.util.parser.validator.EmplPrincipleNumberValidator;
import com.ametis.cms.util.parser.validator.GroupCodeValidator;
import com.ametis.cms.util.parser.validator.NextGroupCodeValidator;
import com.ametis.cms.util.parser.validator.NextPolicyNumberValidator;
import com.ametis.cms.util.parser.validator.PolicyNumberValidator;
import com.ametis.cms.util.parser.validator.ProductPlanIDValidator;
import com.ametis.cms.util.parser.validator.SubPolicyNOValidator;
import com.ametis.cms.util.parser.validator.SwiftCodeValidator;
import com.ametis.cms.util.parser.validator.SwipeCardValidator;

public class ValidatorTesterTask {

	private MemberService memberService;
	private BankService bankService;
	private PolicyService policyService;
	private ClientService clientService;
	private MemberGroupService memberGroupService;
	private ProductService productService;
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public BankService getBankService() {
		return bankService;
	}
	public void setBankService(BankService bankService) {
		this.bankService = bankService;
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
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}
	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	public void validateAll(){
		try {
			System.out.println("VALIDATE ALL VALIDATOR ----");
			
			System.out.println("VALIDATE GROUP CODE ----");
			
			System.out.println(" VALIDATE GROUP CODE G0001 --> " + GroupCodeValidator.isValid("G0001", memberGroupService));
			System.out.println(" VALIDATE GROUP CODE A-Z6767 --> " + GroupCodeValidator.isValid("A-Z6767", memberGroupService));
			
			System.out.println("VALIDATE SWIFT CODE ----");
			
			System.out.println(" VALIDATE SWIFT CODE BTANIDJA --> " + SwiftCodeValidator.isValid("BTANIDJA", bankService));
			System.out.println(" VALIDATE GROUP CODE PUJI --> " + SwiftCodeValidator.isValid("PUJI", bankService));
			
			System.out.println("VALIDATE POLICY NUMBER ----");
			
			System.out.println(" VALIDATE POLICY NUMBER LA-01-2015 AND client code 001 --> " + PolicyNumberValidator.isValid("LA-01-2015","001", policyService));
			System.out.println(" VALIDATE POLICY NUMBER POLIS IRFAN 01 AND client code 001 --> " + PolicyNumberValidator.isValid("POLIS IRFAN 01","001", policyService));

			System.out.println("VALIDATE PRODUCT PLAN ID----");
			
			System.out.println(" VALIDATE PRODUCT PLAN ID RI-YKKBI AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("RI-YKKBI","YKKBI", "010",productService,policyService));
			System.out.println(" VALIDATE PRODUCT PLAN ID LA-MT-A AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("LA-MT-A","YKKBI", "010",productService,policyService));
			System.out.println(" (spasi) VALIDATE PRODUCT PLAN ID RI-YKK BI AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("RI-YKK BI","YKKBI", "010",productService,policyService));
			System.out.println(" (koma) VALIDATE PRODUCT PLAN ID RI-YKK,BI AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("RI-YKK,BI","YKKBI", "010",productService,policyService));
			System.out.println(" (titikkoma) VALIDATE PRODUCT PLAN ID RI-YKK;BI AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("RI-YKK;BI","YKKBI", "010",productService,policyService));
			System.out.println(" (separator) VALIDATE PRODUCT PLAN ID RI-YKK|BI AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("RI-YKK|BI","YKKBI", "010",productService,policyService));
			System.out.println(" (titikdua) VALIDATE PRODUCT PLAN ID RI-YKK:BI AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("RI-YKK:BI","YKKBI", "010",productService,policyService));
			System.out.println("  VALIDATE PRODUCT PLAN ID RI-YKKKKKKKKKKKKKKKKKKKKsdfsdfsdfsdfsdfsdfsdfsdflksldjfhsljdfhjsdfkpjaspodjihsdflishdfjhjsdfhshdfkjskdfjksdfhkjskfskfhsjfhkjsjkfksjfhssjfksfksdfasdpojajsdaposdapsojdpooewojjidjijdsijcfsdsijsdifijfsdifjisjdfsidfsdfjsdfijsdfsidjfsdfijsidjfsdfijsdfijjsdfoijsdfoijsdfoijsdfoijsdfoijhskdjfhksjdfhdksjdfhkjsdkfjshkjdfjksdhfkjhiwehsjdhfjsodifhsjdflisudlfhsfpspefujlshfksuhdkfjslkdfjKKKKKKKKKKBI AND POLICE NUMBER YKKBI AND code 010 --> " + ProductPlanIDValidator.isValid("YKKKKKKKKKKKKKKKKKKKKsdfsdfsdfsdfsdfsdfsdfsdflksldjfhsljdfhjsdfkpjaspodjihsdflishdfjhjsdfhshdfkjskdfjksdfhkjskfskfhsjfhkjsjkfksjfhssjfksfksdfasdpojajsdaposdapsojdpooewojjidjijdsijcfsdsijsdifijfsdifjisjdfsidfsdfjsdfijsdfsidjfsdfijsidjfsdfijsdfijjsdfoijsdfoijsdfoijsdfoijsdfoijhskdjfhksjdfhdksjdfhkjsdkfjshkjdfjksdhfkjhiwehsjdhfjsodifhsjdflisudlfhsfpspefujlshfksuhdkfjslkdfjKKKKKKKKKKBI","YKKBI", "010",productService,policyService));

			System.out.println("Client Code Number ----");
			
			System.out.println(" VALIDATE Client Code 145  --> " + ClientCodeValidator.isValid("145", clientService));
			System.out.println(" VALIDATE Client Code 001 --> " + ClientCodeValidator.isValid("001",clientService));
			System.out.println(" VALIDATE Client Code 00111 --> " + ClientCodeValidator.isValid("00111",clientService));
			
			System.out.println("Next Group Code ----");
			
			System.out.println(" VALIDATE Next Group Code G0004  --> " + NextGroupCodeValidator.isValid("G0004", memberGroupService));
			System.out.println(" VALIDATE Next Group Code YYY4  --> " + NextGroupCodeValidator.isValid("YYY4", memberGroupService));
			
			System.out.println("Next Policy Number ----");
			
			System.out.println(" VALIDATE NEXT POLICY NUMBER LA-01-2015 AND client code 001 --> " + PolicyNumberValidator.isValid("LA-01-2015","001", policyService));
			System.out.println(" VALIDATE NEXT POLICY NUMBER POLIS IRFAN 01 AND client code 001 --> " + PolicyNumberValidator.isValid("POLIS IRFAN 01","001", policyService));
			
			
			

			System.out.println("Swipe Card No ----");
			
			System.out.println(" VALIDATE Swipe Card No 1000620030000042 AND client code 00 AND Policy Number 146.900.30.12.2014 and actionType ADDITION  --> " + SwipeCardValidator.isValid("1000620030000042","00","146.900.30.12.2014","ADDITION",memberService, policyService));
			System.out.println(" VALIDATE Swipe Card No 100062003000009898989 AND client code 00 AND Policy Number 146.900.30.12.2014 and actionType UPDATE  --> " + SwipeCardValidator.isValid("100062003000009898989","00","146.900.30.12.2014","UPDATE",memberService, policyService));
			System.out.println(" VALIDATE Swipe Card No null AND client code 001 AND Policy Number LA-01-2015 and actionType ADDITION  --> " + SwipeCardValidator.isValid("","001","LA-01-2015","ADDITION",memberService, policyService));
			
			
			System.out.println("Client Name ----");
			
			System.out.println(" VALIDATE Client Name  PT. Asuransi ABC  --> " + ClientNameValidator.isValid("PT. Asuransi ABC",clientService));
			System.out.println(" VALIDATE Client Name  PT. Asuransi XYZ  --> " + ClientNameValidator.isValid("PT. Asuransi XYZ",clientService));
			
			
			System.out.println("Sub Policy No ----");
			
			System.out.println(" VALIDATE Sub Policy No  AJB-00234-PO  --> " + SubPolicyNOValidator.isValid("AJB-00234-PO","AJB-00234-PO"));
			System.out.println(" VALIDATE Sub Policy No  AJB-00234-POX  --> " + SubPolicyNOValidator.isValid("AJB-00234-POX","AJB-00234-PO"));
			
			System.out.println("Empl Principle Number ----");
			
			/**
			System.out.println(" VALIDATE Empl Principle Number  70980765-01 and client code 001 and policy number LA-01-2015  --> " + EmplPrincipleNumberValidator.isValid("70980765-01","001","LA-01-2015",memberService));
			System.out.println("(bukan employee) VALIDATE Empl Principle Number  10001-3 and client code 00234 and policy number AJB-00234-PO  --> " + EmplPrincipleNumberValidator.isValid("10001-3","00234","AJB-00234-PO",memberService));
			System.out.println(" VALIDATE Empl Principle Number  null and client code null and policy number null  --> " + EmplPrincipleNumberValidator.isValid("","","",memberService));
			*/

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
}
