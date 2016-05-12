package com.ametis.cms.util.parser.validator;

public class FreeTextValidator {

	public static String isValid (String input, int length, String fieldName){
		String result = "OK";

		if (input != null){
			if (!input.trim().equalsIgnoreCase("")){
				if (input.trim().length() > length){
					result = "Field "+ fieldName + " dapat diisi dengan karakter bebas dengan panjang " + length + " karakter";
				}
			}
		}
		
		return result;
	}
}
