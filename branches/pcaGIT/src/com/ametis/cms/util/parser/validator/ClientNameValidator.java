package com.ametis.cms.util.parser.validator;

import java.util.Collection;

import com.ametis.cms.datamodel.Client;
import com.ametis.cms.service.ClientService;

public class ClientNameValidator {
	public static String isValid (String input,ClientService clientService){
		String result = "";
		
		if (input != null && !input.equalsIgnoreCase("")){
			
				if (input.trim().length() > 50){
					result = "Max. 50 karakter untuk Client Name";
				}
				else {
					Collection<Client> clients;
					try {
						clients = clientService.getAll();
						if(clients!=null){
							for(Client client : clients){
								if(input.equalsIgnoreCase(client.getClientName())){
									result="OK";
									break ;
								}
								else{
									result="Nama Client tidak ditemukan di tabel client";
								}
							}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
			}
		
		else {
			result = "OK";
		}
		
		return result;
	}
}
