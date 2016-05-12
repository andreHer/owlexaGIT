package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandphoneValidator {

	public static String isValid (String input){
		String result = "OK";
		
		if (input != null){
			if (input.equalsIgnoreCase("")){
				result = "OK";
			}
			else {
				if(input.length() > 25){
					result = "Field Handphone mempunyai batas Maksimum 25 Karakter";
				}
				else {
					String regex = "\\d{8,20}";
					
					Pattern pattern = Pattern.compile(regex);
					
					Matcher matcher = pattern.matcher(input);
					
					if (matcher.matches()){
						result = "OK";
					}
					else {
						result = "Format Handphone harus numeric, contoh: 08137237372";
					}
				}
			}			
		}
		
		
		return result;
	}
}
