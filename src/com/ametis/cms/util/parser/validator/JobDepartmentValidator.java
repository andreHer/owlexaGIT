package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JobDepartmentValidator {
	
	/**
	 * 
	 * @param input
	 * @param fieldName representasikan Job Function atau Department
	 * @return
	 */
	public static String isValid (String input,String fieldName){
		String result = "Field "+fieldName+" wajib diisi dengan karakter maksimum 100 karakter alphanumeric";
		
		if (input != null && !input.trim().equalsIgnoreCase("")){
			
			if (input.trim().length() <= 100){
				String regex = "^[\\p{L} .,';/-]+$";
				Pattern pattern = Pattern.compile(regex);
				
				Matcher matcher = pattern.matcher(input);
				
				if (matcher.matches()){
					result = "OK";
				}
				else {
					result = "Mohon masukkan Field "+fieldName+" dengan format standard contoh: Kepala Bagian/HSE";
				}
			}
			else {
				result = "Field "+fieldName+" wajib diisi dengan karakter maksimum 100 alphanumeric";
			}
		}
		
		return result;
	}
}
