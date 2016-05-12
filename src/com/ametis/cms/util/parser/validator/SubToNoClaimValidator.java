package com.ametis.cms.util.parser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubToNoClaimValidator {
	public static String isValid (String input){
		String result = "";

		if(input !=null && !input.equalsIgnoreCase("")){
			if (input.trim().length() > 3){
				result = "Jumlah Maksimal Karakter Angka adalah 3 diisi dari jumlah hari dihitung dari effective date + sub to no claim.";
			}
			else {
				String regex = "[0-9]*";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(input);
				if (matcher.matches()){
					result = "OK";
				}
				else {
					result = "Field Subject To No Claim mempunyai format angka, contoh: 15, 120, 180";
				}
			}
		}
		else {
			result = "OK";
		}

		return result;
	}

}
