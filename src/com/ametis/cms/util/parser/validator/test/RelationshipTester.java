package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.RelationshipValidator;

public class RelationshipTester {

	public static void main(String[] args){
		System.out.println("EMPLOYEEEEE --> " + RelationshipValidator.isValid("EMPLOYEEEE"));
		System.out.println("EMPLOYEE --> " + RelationshipValidator.isValid("EMPLOYEE"));
		System.out.println("SPOUSE --> " + RelationshipValidator.isValid("spouse"));
		System.out.println("SON --> " + RelationshipValidator.isValid("son"));
		
	
	}
}
