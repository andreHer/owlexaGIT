package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.GraceStartDateValidator;
import com.ametis.cms.util.parser.validator.NewPolicyNumberValidator;

public class GraceStartDateValidatorTest {
	
	public static void main(String[] args){
		System.out.println("1975-05-03 , MUTASI --> " + GraceStartDateValidator.isValid("1975-05-03"));
		System.out.println("1975-05-03 , MUTASI --> " + GraceStartDateValidator.isValid("1975/05/03"));
		System.out.println("1975-05-03 , MUTASI --> " + GraceStartDateValidator.isValid("1975-13-03"));
	
	}

}
