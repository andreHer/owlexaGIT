package com.ametis.cms.util.parser.validator;

public class NewPolicyNumberValidator {

	public static String isValid (String input,String policyNumber, String actionType){
		String result = "";

		if (actionType.equalsIgnoreCase("MUTASI") && input==null || actionType.equalsIgnoreCase("MUTASI") && input.equalsIgnoreCase("")){
			result = "Wajib diisi jika action type-nya adalah MUTASI";
		}
		else{



			if(input !=null && !input.equalsIgnoreCase("")){
				if (input.equalsIgnoreCase(policyNumber)){
					result="Diisi dengan nomor polis yang berbeda dengan nomor polis yang diisi pada kolom 3. ";
				}
				else{



					if (input.trim().length() > 50){
						result = "Jumlah Maksimal Karakter New Policy Number adalah 50.";


					}
					else {
						result = "OK";
					}
				}
			}
			else {
				result = "OK";
			}

		}

		return result;
	}


}
