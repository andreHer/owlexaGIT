package com.ametis.cms.util.parser.validator.test;

import java.sql.Date;

import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.util.parser.validator.EffectiveDateValidator;

public class EffectiveDateTester {

	public static void main(String[] args){
		Policy policy = new Policy();
		policy.setEffectiveDate(Date.valueOf("2016-02-15"));
		policy.setExpireDate(Date.valueOf("2017-01-14"));
		
		System.out.println("2016-02-15 --> " + EffectiveDateValidator.isValid("2016-02-15",policy) );
		System.out.println("2016-12-15 --> " + EffectiveDateValidator.isValid("2016-12-15",policy) );
		System.out.println("2017-01-14 --> " + EffectiveDateValidator.isValid("2017-01-14",policy) );
		System.out.println("2017-01-16 --> " + EffectiveDateValidator.isValid("2017-01-16",policy) );
	}
}
