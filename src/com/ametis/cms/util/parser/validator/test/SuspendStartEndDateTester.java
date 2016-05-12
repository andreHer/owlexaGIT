package com.ametis.cms.util.parser.validator.test;

import java.sql.Date;

import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.util.parser.validator.EffectiveDateValidator;
import com.ametis.cms.util.parser.validator.SuspendEndDateValidator;
import com.ametis.cms.util.parser.validator.SuspendStartDateValidator;

public class SuspendStartEndDateTester {

	public static void main(String[] args){
		Policy policy = new Policy();
		policy.setEffectiveDate(Date.valueOf("2016-02-15"));
		policy.setExpireDate(Date.valueOf("2017-01-14"));
		
		System.out.println("2016-02-15 --> " + SuspendStartDateValidator.isValid("","SUSPEND",policy) );
		System.out.println("2016-12-15 --> " + SuspendStartDateValidator.isValid("2016-12-15","BLOCK",policy) );
		System.out.println("2017-01-14 --> " + SuspendStartDateValidator.isValid("2017-01-14","SUSPEND",policy) );
		System.out.println("2017-01-16 --> " + SuspendStartDateValidator.isValid("2017-01-16","SUSPEND",policy) );
		
		System.out.println("END DATE TEST ------------ ");
		
		System.out.println("2016-02-15 --> " + SuspendEndDateValidator.isValid("","SUSPEND",policy) );
		System.out.println("2016-12-15 --> " + SuspendEndDateValidator.isValid("2016-12-15","BLOCK",policy) );
		System.out.println("2017-01-14 --> " + SuspendEndDateValidator.isValid("2017-01-14","SUSPEND",policy) );
		System.out.println("2017-01-16 --> " + SuspendEndDateValidator.isValid("2017-01-16","SUSPEND",policy) );
	}
}
