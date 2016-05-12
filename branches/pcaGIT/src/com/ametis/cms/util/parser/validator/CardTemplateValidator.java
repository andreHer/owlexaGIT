package com.ametis.cms.util.parser.validator;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.service.ClientService;

public class CardTemplateValidator {
	public static String isValid (String input){
		String result = "OK";

		if(input !=null){
			if(input.trim().length() > 50){
				
				result = "Field Card Template mempunyai panjang maksimal 50 Karakter";
			}
		}

		return result;
	}
}
