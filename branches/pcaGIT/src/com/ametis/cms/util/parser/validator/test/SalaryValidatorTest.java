package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.SalaryValidator;

public class SalaryValidatorTest {

	public static void main(String[] args){
		System.out.println(" 1000000000000 " + SalaryValidator.isValid("1000000000000"));
		System.out.println(" 10000000 " + SalaryValidator.isValid("10000000"));
		System.out.println(" 10000000. " + SalaryValidator.isValid("10000000."));
		
	}
}
