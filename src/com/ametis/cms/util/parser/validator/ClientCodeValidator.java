package com.ametis.cms.util.parser.validator;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.service.ClientService;

public class ClientCodeValidator {
	
	public static String isValid (String input,ClientService clientService){
		String result = "";

		if(input !=null){


			if(input.trim().length() > 3){
				
				result = "Isi Client Code Maksimal 3 karakter";
			}
			else{
				try {
					Client client = clientService.getClient(input);
					if(client!=null){
						result="Client Code sudah ada";
					}
					else{
						result="OK";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		else {
			result = "Client Code Kosong";
		}

		return result;
	}

}
