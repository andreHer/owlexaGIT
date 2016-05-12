package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WarningBlacklistValidator {
	
	/**
	 * 
	 * @param input
	 * @param fieldName representasikan Warning atau Blacklist Text
	 * @return
	 */
	public static String isValid (String input,String fieldName){
		String result = "Field "+fieldName+" wajib diisi dengan karakter maksimum 200 karakter alphanumeric";
		
		if (input != null && !input.trim().equalsIgnoreCase("")){
			
			if (input.trim().length() <= 100){
				String regex = "^[\\p{L} .,';/-]+$";
				Pattern pattern = Pattern.compile(regex);
				
				Matcher matcher = pattern.matcher(input);
				
				if (matcher.matches()){
					result = "OK";
				}
				else {
					result = "Mohon masukkan Field "+fieldName+" dengan format standard contoh: Telah Terjadi Fraud!";
				}
			}
			else {
				result = "Field "+fieldName+" wajib diisi dengan karakter maksimum 200 alphanumeric";
			}
		}
		
		return result;
	}
}
