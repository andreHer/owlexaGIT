package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator {

	/**
	 * 
	 * @param input
	 * @param fieldName diisi dengan Member Name, Employee Name dsb
	 * @return
	 */
	public static String isValid (String input,String fieldName){
		String result = "Field "+fieldName+" wajib diisi dengan karakter maksimum 60 huruf";
		
		if (input != null && !input.trim().equalsIgnoreCase("")){
			
			if (input.trim().length() <= 60){
				String regex = "^[\\p{L} .,'-]+$";
				Pattern pattern = Pattern.compile(regex);
				
				Matcher matcher = pattern.matcher(input);
				
				if (matcher.matches()){
					result = "OK";
				}
				else {
					result = "Mohon masukkan Field "+fieldName+" dengan format standard contoh: Rendy Hariady";
				}
			}
			else {
				result = "Field "+fieldName+" wajib diisi dengan karakter maksimum 60 huruf";
			}
		}
		
		return result;
	}
}
