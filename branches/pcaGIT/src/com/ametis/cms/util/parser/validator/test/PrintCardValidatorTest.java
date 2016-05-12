package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.PrintCardValidator;
import com.ametis.cms.util.parser.validator.RelationshipValidator;

public class PrintCardValidatorTest {
	
	public static void main(String[] args){
		System.out.println("Y --> " + PrintCardValidator.isValid("Y"));
		System.out.println("N --> " + PrintCardValidator.isValid("N"));
		System.out.println("YY --> " + PrintCardValidator.isValid("YY"));
		System.out.println("X --> " + PrintCardValidator.isValid("X"));
		System.out.println("null --> " + PrintCardValidator.isValid(""));
		
		
	
	}

}
