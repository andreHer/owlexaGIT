package com.ametis.cms.util.parser.validator;

import java.util.Collection;


public class ActionTypeValidator {
	
	public static String isValid (String input){
		String result = "";
		
		if (input != null && !input.trim().equalsIgnoreCase("")){
			if (input.equalsIgnoreCase("ADDITION")||
					input.equalsIgnoreCase("LOSTCARD")||
					input.equalsIgnoreCase("REACTIVE")||
					input.equalsIgnoreCase("DELETION")||
					input.equalsIgnoreCase("SUSPEND")||
					input.equalsIgnoreCase("UPDATE")||
					input.equalsIgnoreCase("CHANGEPLAN")||
					input.equalsIgnoreCase("MUTASI")||
					input.equalsIgnoreCase("RENEWAL")){
				
				result = "OK";
			}
			else {
				
					result = "Action Type yang dimasukkan tidak sesuai ";
				}
			
		}
		else {
			result = "Action Type tidak boleh kosong";
		}
		
		return result;
	}
	
}
