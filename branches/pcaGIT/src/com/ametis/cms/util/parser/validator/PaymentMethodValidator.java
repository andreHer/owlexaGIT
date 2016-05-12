package com.ametis.cms.util.parser.validator;

public class PaymentMethodValidator {
	public static String isValid (String input){
		String result = "";

		if(input !=null && !input.equalsIgnoreCase("")){


			if (input.trim().length() > 1){
				result = "Kode Payment lebih dari 2 karakter ";


			}
			else {
				if(input.equalsIgnoreCase("A")||input.equalsIgnoreCase("S")||input.equalsIgnoreCase("Q")||input.equalsIgnoreCase("M")){
					
					result="OK";
				}
				else{
					result="Kode Payment yang dimasukkan salah";
				}
			}
		}
		else {
			result = "OK";
		}

		return result;
	}

}
