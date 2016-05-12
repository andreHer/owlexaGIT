package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.HandphoneValidator;

public class HandphoneTester {

	public static void main(String[] args){
		System.out.println(HandphoneValidator.isValid("0812331232333123") + " 0812331232333123");
		System.out.println(HandphoneValidator.isValid("08123312312312ASD") + " 08123312312312ASD");
	}
}
