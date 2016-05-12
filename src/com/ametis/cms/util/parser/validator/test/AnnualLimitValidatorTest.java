package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.AnnualLimitValidator;

public class AnnualLimitValidatorTest {

	public static void main(String[] args){
		System.out.println("123 " + AnnualLimitValidator.isValid("123 "));
		System.out.println("1234567890123456789012345"  + AnnualLimitValidator.isValid("1234567890123456789012345 "));
		System.out.println("123ABC7890123ABC789012345 " + AnnualLimitValidator.isValid("123ABC7890123ABC789012345 "));
		System.out.println("12345678901234567890123456789 " + AnnualLimitValidator.isValid("12345678901234567890123456789 "));
		System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXY " + AnnualLimitValidator.isValid("ABCDEFGHIJKLMNOPQRSTUVWXY "));
	}
}
