package com.ametis.cms.util.parser.validator;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ametis.cms.datamodel.Policy;

public class SuspendEndDateValidator {

	public static String isValid(String input, String actionType,Policy policy){
		String result = "OK";
		
		
		if (actionType != null && (actionType.equalsIgnoreCase("SUSPEND") || actionType.equalsIgnoreCase("BLOCK"))){
			if (input != null){
				if (!input.trim().equalsIgnoreCase("")){
					String regex = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
					Pattern pattern = Pattern.compile(regex);
					
					Matcher matcher = pattern.matcher(input);
					
					if (matcher.matches()){
						if (policy.getEffectiveDate() != null && policy.getExpireDate() != null){
							Date suspendEndDate = Date.valueOf(input);
							
							if ((policy.getEffectiveDate().before(suspendEndDate))
									&& (policy.getExpireDate().equals(suspendEndDate) || policy.getExpireDate().after(suspendEndDate))){
								
								result = "OK";		
							}
							else {
								result = "Suspend End date harus diantara tanggal aktif policy";
							}
							
						}						
					}
					else {
						result = "Format Suspend End Date tidak sesuai aturan (yyyy-mm-dd), contoh: 2015-01-05";
					}
				}
				else {
					result = "Suspend End Date wajib diisi jika action type SUSPEND/BLOCK. Format suspend End date adalah yyyy-mm-dd";
				}
			}
			else {
				result = "Suspend End Date wajib diisi jika action type SUSPEND/BLOCK. Format suspend End date adalah yyyy-mm-dd";
			}
		}
		
		return result;
	}
}
