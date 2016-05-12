package com.ametis.cms.util.parser.validator;

public class AccountNameValidator {
	public static String isValid (String input){
		String result = "";

		if(input !=null){
			if (!input.trim().equalsIgnoreCase("")){
				if (input.length() > 100){
					result = "Jumlah Maksimal Karakter adalah 100.";
				}
				else {
					result = "OK";
				}
			}
			else {
				result = "OK";
			}
		}
		else {
			result = "OK";
		}

		return result;
	}

}
