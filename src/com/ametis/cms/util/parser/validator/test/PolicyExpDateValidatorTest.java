package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.PolicyExpDateValidator;
import com.ametis.cms.util.parser.validator.RelationshipValidator;

public class PolicyExpDateValidatorTest {
	
	public static void main(String[] args){
		System.out.println("test 1 --> " + PolicyExpDateValidator.isValid("2016-04-03"));
		System.out.println("test 2 --> " + PolicyExpDateValidator.isValid("03/04/2016"));
		
		
	
	}

}
