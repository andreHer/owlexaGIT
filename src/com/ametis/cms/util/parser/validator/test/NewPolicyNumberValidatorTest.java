package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.NewPolicyNumberValidator;


public class NewPolicyNumberValidatorTest {
	
	public static void main(String[] args){
		System.out.println("LA-01-2015, Policy number LA-01-2015, MUTASI --> " + NewPolicyNumberValidator.isValid("LA-01-2015","LA-01-2015","MUTASI"));
		System.out.println("LA-01-2015, Policy number LA-01-2016, MUTASI --> " + NewPolicyNumberValidator.isValid("LA-01-2015","LA-01-2016","MUTASI"));
		System.out.println(", Policy number LA-01-2015, MUTASI --> " + NewPolicyNumberValidator.isValid("","LA-01-2015","MUTASI"));
		System.out.println(", Policy number LA-01-2015, ADDITION --> " + NewPolicyNumberValidator.isValid("","LA-01-2015","ADDITION"));
	
	}

}
