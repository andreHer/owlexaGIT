package com.ametis.cms.util.parser.validator;

public class ImportBatchNoValidator {
	
	public static String isValid (String input){
		String result = "";

		if(input !=null && !input.equalsIgnoreCase("")){


			if (input.trim().length() > 100){
				result = "Jumlah Maksimal Karakter adalah 100.";


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
