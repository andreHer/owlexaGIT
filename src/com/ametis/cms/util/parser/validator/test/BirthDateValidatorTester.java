package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.BirthDateValidator;

public class BirthDateValidatorTester {

	public static void main(String[] args){
		System.out.println("2016-01-01 -->" + BirthDateValidator.isValid("2016-01-01"));
		System.out.println("2016-26-01 -->" + BirthDateValidator.isValid("2016-26-01"));
	}
}
