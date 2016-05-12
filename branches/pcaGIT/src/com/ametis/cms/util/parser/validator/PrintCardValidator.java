package com.ametis.cms.util.parser.validator;

public class PrintCardValidator {
	public static String isValid (String input){
		String result = "";

		if(input !=null && !input.equalsIgnoreCase("")){


			if (input.trim().length() > 1){
				result = "Isi field Print Card hanya Y / N";


			}
			else {
				if(input.equalsIgnoreCase("Y") ||input.equalsIgnoreCase("N")){
					result="OK";
				}
				else{
					result = "Isi field Print Card hanya Y / N";	
				}
				
			}
		}
		else {
			result = "Print Card Tidak boleh kosong";
		}

		return result;
	}

}
