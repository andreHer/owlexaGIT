package com.ametis.cms.util.parser.validator;

public class LinkedCardNoValidator {
	
	public static String isValid (String input){
		String result = "";

		if(input !=null && !input.equalsIgnoreCase("")){
			
			if(input.trim().length()>19){
				result="Jumlah maksimal 19 karakter untuk field Linked Card No ";
			}

			else{
			 try  
			  {  
			    double d = Double.parseDouble(input);
			    result="OK"; 

			  }  
			  catch(NumberFormatException nfe)  
			  {  
			    result="Format Linked Card NO harus Numeric";
			  }  
			}

		}
		else {
			result = "OK";
		}

		return result;
	}

}
