package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFieldValidator {

	public static String isValid (String input){
		String result = "OK";
		
		if (input != null){
			if (input.trim().equalsIgnoreCase("")){
				result = "OK";
			}
			else {
				String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
				Pattern pattern = Pattern.compile(regex);
				
				Matcher matcher = pattern.matcher(input);
				
				if (matcher.matches()){
					result = "OK";
				}
				else {
					result = "Format Email tidak sesuai aturan, contoh: support@example.co.id";
				}
			}
		}
		
		return result;
	}
}
