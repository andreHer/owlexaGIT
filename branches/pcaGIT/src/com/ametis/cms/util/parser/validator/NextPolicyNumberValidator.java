package com.ametis.cms.util.parser.validator;

import java.util.Collection;

import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.parser.PolicyParser;

public class NextPolicyNumberValidator {

		public static String isValid(String input,String clientCode, PolicyService policyService){
			String result = "Field Policy Number Wajib Diisi dengan panjang maksimal 50 Karakter";
			
			if (input != null && input.trim().length() < 50){
				if (clientCode != null && clientCode.trim().length() > 0){
					try {
						Policy policy = policyService.getActivePolicyByNumber(input, clientCode);
						
						if (policy != null){
							result = "OK";
						}
						else {
							result = "Policy tidak ditemukan di dalam database";
						}
					}
					catch (Exception e){
						e.printStackTrace();
					}
				}
			}
			
			
			
			return result;
		}

}
