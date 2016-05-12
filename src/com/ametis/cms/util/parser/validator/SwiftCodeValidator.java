package com.ametis.cms.util.parser.validator;

import com.ametis.cms.datamodel.Bank;
import com.ametis.cms.service.BankService;

public class SwiftCodeValidator {

	public static String isValid(String input, BankService bankService){
		String result = "OK";
		
		if (input != null){
			if (!input.trim().equalsIgnoreCase("")){
				try {
					Bank bank = bankService.getBankBySwiftCode(input.trim());
					
					if (bank == null){
						result = "Mohon masukkan Swift Code Bank yang terdaftar";
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
}
