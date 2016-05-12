package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.LinkedCardNoValidator;
import com.ametis.cms.util.parser.validator.RelationshipValidator;

public class LinkedCardNoValidatorTest {

	
	public static void main(String[] args){
		System.out.println("test 1 --> " + LinkedCardNoValidator.isValid("34234234234"));
		System.out.println("test 2 --> " + LinkedCardNoValidator.isValid("234234g7378y8"));
		
	
	}
}
