package com.ametis.cms.util.parser.validator;

import com.ametis.cms.datamodel.Member;
import com.ametis.cms.service.MemberService;

public class NextEmpNumberValidator {
	
	public static String isValid(String input,MemberService memberService){
		String result = "OK";
		
		if (input != null){
			if (!input.trim().equalsIgnoreCase("")){
				try {
					Member member = memberService.get(input.trim());
					
					if (member == null){
						result = "Mohon masukkan Next Emp Number yang terdaftar";
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
