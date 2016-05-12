package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.service.MemberService;

public class MemberNumberValidator {

	public static String isValid (String input,Policy policy,String actionType, MemberService memberService){
		String result = "Field Member Name harus diisi dengan maksimal 50 Karakter";
		
		try {
			if (input != null && input.trim().length() <= 50 && policy != null && actionType != null){
				
				String regex = "^[\\p{L} .-]+$";
				Pattern pattern = Pattern.compile(regex);
				
				Matcher matcher = pattern.matcher(input);
				
				if (matcher.matches()){
				
					if(actionType.equalsIgnoreCase("ADDITION")){
						String[] eqParam = {"customerPolicyNumber","policyId.policyId","deletedStatus"};
						Object[] eqValue = {input.trim(),policy.getPolicyId(),0};
						
						int total = memberService.getTotal(null,null,eqParam,eqValue);
						
						if (total > 0){
							result = "Field Member Name terdapat duplikasi di dalam database";
						}
					}
					else {
						String[] eqParam = {"customerPolicyNumber","policyId.policyId","deletedStatus"};
						Object[] eqValue = {input.trim(),policy.getPolicyId(),0};
						
						int total = memberService.getTotal(null,null,eqParam,eqValue);
						
						if (total == 0){
							result = "Field Member Name belum terdaftar di dalam database";
						}
					}
				}
				else {
					result = "Field Member Name tidak sesuai dengan format, contoh: J000001-A,J000001-01 atau J000001A";
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	
		
		return result;
	}
}
