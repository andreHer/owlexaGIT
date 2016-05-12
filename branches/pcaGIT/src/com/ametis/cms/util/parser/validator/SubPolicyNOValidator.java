package com.ametis.cms.util.parser.validator;

public class SubPolicyNOValidator {

	public static String isValid (String input, String policyNumber){
		String result = "";



		if(input !=null && !input.equalsIgnoreCase("")){

			if(input.equalsIgnoreCase(policyNumber)){
				result="Tidak boleh diisi sama dengan nomor polis";
			}
			else {

				if (input.trim().length() > 50){
					result = "Jumlah Maksimal Karakter adalah 100.";


				}
				else {
					result = "OK";
				}
			}
		}
		else {
			result = "OK";
		}

		return result;
	}

}
