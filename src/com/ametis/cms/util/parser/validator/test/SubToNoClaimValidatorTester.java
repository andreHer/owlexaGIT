package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.SubToNoClaimValidator;

public class SubToNoClaimValidatorTester {
	
	public static void main(String[] args){
		System.out.println("123 " + SubToNoClaimValidator.isValid("123 "));
		System.out.println("1234 " + SubToNoClaimValidator.isValid("1234 "));
	}
}
