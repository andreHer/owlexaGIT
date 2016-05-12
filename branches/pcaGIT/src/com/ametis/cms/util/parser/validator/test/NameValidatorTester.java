package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.NameValidator;

public class NameValidatorTester {

	public static void main(String[] args){
		System.out.println("ADHITYO 2--> " + NameValidator.isValid("ADHITYO 2", "Member Name") );
		System.out.println("ADHITYO.; --> " + NameValidator.isValid("ADHITYO.;", "Member Name") );
		System.out.println("ADHITYO. /PRIYAMBODO --> " + NameValidator.isValid("ADHITYO. /PRIYAMBODO", "Member Name") );
		System.out.println("dr. ADHITYO PRIYAMBODO --> " + NameValidator.isValid("dr. ADHITYO PRIYAMBODO", "Member Name") );
		System.out.println("dr. ADHITYO PRIYAMBODO, MTi --> " + NameValidator.isValid("dr. ADHITYO PRIYAMBODO, MTi", "Member Name") );
	}
}
