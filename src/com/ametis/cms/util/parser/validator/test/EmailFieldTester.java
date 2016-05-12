package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.EmailFieldValidator;

public class EmailFieldTester {

	public static void main(String[] args){
		String card = "10001600300000001";
		System.out.println("YEAR CODE = " + card.substring(4,6));
		System.out.println("CLIENT CODE = " + card.substring(6,9));
		System.out.println("YEAR CODE = " + 2015 %100  + " 1998 = " + 1998 %100  + " 2120 = " + 2120 % 100);
		
		
		
		System.out.println("adhit@testing.com --> " + EmailFieldValidator.isValid("adhit@testing.com"));
		System.out.println("adhit@123123.123--> " + EmailFieldValidator.isValid("adhit@123123.123"));
		System.out.println("adhiasdasd --> " + EmailFieldValidator.isValid("adhiasdasd"));
		System.out.println("12312312@123123123.com --> " + EmailFieldValidator.isValid("12312312@123123123.com"));
	}
}
