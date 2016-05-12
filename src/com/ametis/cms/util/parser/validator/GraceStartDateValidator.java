package com.ametis.cms.util.parser.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GraceStartDateValidator {
	
	public static String isValid (String input){
		String result = "";
		Date tanggal;

		if(input !=null && !input.equalsIgnoreCase("")){
			if(input.trim().length() > 10){
				result="Format tanggal salah";
			}

			else{



				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					sdf.setLenient(false);
					tanggal=sdf.parse(input);
					result ="OK";

				}
				catch(ParseException ex) { 
					result ="Format Tanggal Salah";

				}
			}
		}
		else{
			result ="OK";
		}

		return result;
	}

}
