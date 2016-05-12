package com.ametis.cms.util.parser.validator;

public class RelationshipValidator {

	public static String isValid(String input){
		String result = "";
		
		if (input != null){
			if (!input.trim().equalsIgnoreCase("")){
				if (input.trim().equalsIgnoreCase("EMPLOYEE") ||
						input.trim().equalsIgnoreCase("HUSBAND")||
						input.trim().equalsIgnoreCase("WIFE") ||
						input.trim().equalsIgnoreCase("SPOUSE")
						|| input.trim().equalsIgnoreCase("CHILDREN")
						|| input.trim().equalsIgnoreCase("SON")||
						input.trim().equalsIgnoreCase("DAUGHTER") ||
						input.trim().equalsIgnoreCase("GRAND MOTHER") ||
						input.trim().equalsIgnoreCase("GRAND FATHER") ||
						input.trim().equalsIgnoreCase("NEPHEW") ||
						input.trim().equalsIgnoreCase("WIFE") ||
						input.trim().equalsIgnoreCase("DAUGHTER") ||
						input.trim().equalsIgnoreCase("SON") ||
						input.trim().equalsIgnoreCase("MAID")){
					
					result = "OK";
				}
				else {
					result = "Field Relationship tidak sesuai dengan ketentuan";
				}
					
			}
			else {
				result = "Field Relationship wajib diisi";
			}
		}
		else {
			result = "Field Relationship wajib diisi";
		}
		
		return result;
	}
}
