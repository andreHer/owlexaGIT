package com.ametis.cms.util.parser.validator;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ametis.cms.datamodel.Policy;

public class BirthDateValidator {

	public static String isValid (String input){
		String result = "Field Birth Date wajib diisi dengan format (yyyy-mm-dd) contoh: 2015-01-05";
		
		if (input != null){
			if (!input.trim().equalsIgnoreCase("")){
				if (input.trim().length() != 10  ){
					result = "Field Birth Date wajib diisi dengan format 10 karaketer yyyy-mm-dd ";
				}
				else {
					String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
					Pattern pattern = Pattern.compile(regex);
					
					Matcher matcher = pattern.matcher(input);
					
					if (matcher.matches()){						
						result = "OK";											
					}
					else {
						result = "Format effective tidak sesuai aturan (yyyy-mm-dd), contoh: 2015-01-05";
					}
				}				
			}
		}
		
		return result;
	}
}
