package com.ametis.cms.util.parser.validator;

public class VipValidator {
	public static String isValid (String input){
		String result = "";
		
		if (input != null){
			if (input.equalsIgnoreCase("")){
				result = "OK";
			}
			else {
				if (input.equalsIgnoreCase("VIP")){
					result = "OK";
				}
				else {
					result = "Jika Peserta Bukan VIP, Harap dikosongkan. Jika Peserta merupakan VIP, mohon masukkan field dengan nilai 'VIP' ";
				}
			}
		}
		else {
			result = "OK";
		}
		
		return result;
	}
}
