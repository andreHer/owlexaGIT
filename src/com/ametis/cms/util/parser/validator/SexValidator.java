package com.ametis.cms.util.parser.validator;

public class SexValidator {

	public static String isValid (String input){
		String result = "Field sex harus di-isi dengan nilai : F=Female, M=Male, P=Perempuan, L=Laki-Laki";
		
		if(input != null && !input.trim().equalsIgnoreCase("")){
			if (input.equalsIgnoreCase("M") || input.equalsIgnoreCase("F")||input.equalsIgnoreCase("L") || input.equalsIgnoreCase("P")){
				result = "OK";
			}
		}
		
		return result;
	}
}
