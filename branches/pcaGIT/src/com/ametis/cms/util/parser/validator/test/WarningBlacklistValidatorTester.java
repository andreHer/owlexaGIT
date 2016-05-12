package com.ametis.cms.util.parser.validator.test;

import com.ametis.cms.util.parser.validator.JobDepartmentValidator;
import com.ametis.cms.util.parser.validator.NameValidator;
import com.ametis.cms.util.parser.validator.WarningBlacklistValidator;

public class WarningBlacklistValidatorTester {

	public static void main(String[] args){
		System.out.println("Telah Terjadi Fraud --> " + WarningBlacklistValidator.isValid("Telah Terjadi Fraud", "Blacklist") );
		System.out.println("Excess Belum Dibayar --> " + WarningBlacklistValidator.isValid("Excess Belum Dibayar", "Warning") );
		System.out.println("FRAUD ATTEMPTED --> " + WarningBlacklistValidator.isValid("FRAUD ATTEMPTED", "Warning") );
		System.out.println("T12391723 FRAUD ATTEMPTED FRAUD ATTEMPTED  120391209381290381092380128390180318903801283012830180293810oasdlajdlkjakl FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED  --> "
		+ WarningBlacklistValidator.isValid("T12391723 FRAUD ATTEMPTED FRAUD ATTEMPTED  120391209381290381092380128390180318903801283012830180293810oasdlajdlkjakl FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED  ", "Blacklist") );
		System.out.println("T12391723 FRAUD ATTEMPTED FRAUD ATTEMPTED  120391209381290381092380128390180318903801283012830180293810oasdlajdlkjakl "
				+ "FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED   --> " + WarningBlacklistValidator.isValid("T12391723 FRAUD ATTEMPTED FRAUD ATTEMPTED  120391209381290381092380128390180318903801283012830180293810oasdlajdlkjakl FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED FRAUD ATTEMPTED  ", "Warning") );
	}
}
