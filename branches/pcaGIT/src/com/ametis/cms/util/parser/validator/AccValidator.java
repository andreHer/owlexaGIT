package com.ametis.cms.util.parser.validator;

public class AccValidator {
	public static String isValid (String input){
		String result = "";

		if(input !=null && !input.equalsIgnoreCase("")){


			if (input.trim().length() > 100){
				result = "Jumlah Maksimal Karakter Acc adalah 100.";


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
