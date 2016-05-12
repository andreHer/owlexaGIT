package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.JobDepartmentValidator;
import com.ametis.cms.util.parser.validator.NameValidator;

public class JobDepartmentValidatorTester {

	public static void main(String[] args){
		System.out.println("Information Technology--> " + JobDepartmentValidator.isValid("Information Technology", "Department") );
		System.out.println("Information Technology; --> " + JobDepartmentValidator.isValid("Information Technology;", "Department") );
		System.out.println("Kepala Divisi?? --> " + JobDepartmentValidator.isValid("Kepala Divisi??", "Job Function") );
		System.out.println("HRD / PERSONALIA --> " + JobDepartmentValidator.isValid("HRD / PERSONALIA", "Job Function") );
		System.out.println("Kepala Divisi- --> " + JobDepartmentValidator.isValid("Kepala Divisi-", "Job Function") );
	}
}
