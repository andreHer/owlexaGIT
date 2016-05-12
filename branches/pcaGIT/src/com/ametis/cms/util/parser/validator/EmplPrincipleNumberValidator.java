package com.ametis.cms.util.parser.validator;

import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;

public class EmplPrincipleNumberValidator {

	public static String isValid(String input,String clientCode,String policyNumber, MemberService memberService,PolicyService policyService){
		String result = "";
		
		if (input != null && input.trim().length() <= 50){
			if (clientCode != null && clientCode.trim().length() > 0){
				try {
					
					
					Member member= memberService.getEmployee(input, policyNumber, clientCode);
					
					if (member != null){
						result = "OK";
					}
					else {
						result = "Member Employee tidak ditemukan di dalam database";
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		else{
			try{
				Policy policy = policyService.getActivePolicyByNumber(policyNumber);
				if(policy.getProductType().getProductTypeId() == ProductType.GROUP_FAMILY){
					result="Wajib diisi jika peserta sebagai tanggungan (Dependant).";
				}
				else{
					result="OK";
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}
}
