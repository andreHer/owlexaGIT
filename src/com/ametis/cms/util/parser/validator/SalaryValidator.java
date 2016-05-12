package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalaryValidator {

	public static String isValid (String input){
		String result = "";
		
		if (input != null){
			if (input.trim().length() <= 12){
				String regex = "[0-9]*";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(input);
				
				if (matcher.matches()){
					result = "OK";
				}
				else {
					result = "Field Salary harus diisi dengan format numeric dengan maksimal 12 digit, contoh: 1000000000";
				}
			}
			else {
				result = "Field Salary harus diisi dengan format numeric dengan maksimal 12 digit, contoh: 1000000000";
			}
		}
		else {
			result = "OK";
		}
		
		return result;
	}
}
