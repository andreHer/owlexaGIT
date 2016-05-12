package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.VipValidator;

public class ValidatorTestClass {

	public static void main(String[] args){
		System.out.println(" Test 1 VIPX " + VipValidator.isValid("VIPX"));
		System.out.println(" TEST 2 VIP " + VipValidator.isValid("VIP"));
		System.out.println(" TEST 3 " + VipValidator.isValid(""));
	}
}
