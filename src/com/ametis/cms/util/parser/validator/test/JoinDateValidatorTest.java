package com.ametis.cms.util.parser.validator.test;

import java.sql.Date;

import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.util.parser.validator.EffectiveDateValidator;
import com.ametis.cms.util.parser.validator.JoinDateValidator;

public class JoinDateValidatorTest {
	public static void main(String[] args){
		Policy policy = new Policy();
		policy.setEffectiveDate(Date.valueOf("2016-02-15"));
		policy.setExpireDate(Date.valueOf("2017-01-14"));
		
		System.out.println("2016-02-15 --> " + JoinDateValidator.isValid("2016-02-15",policy) );
		System.out.println("2016-12-15 --> " + JoinDateValidator.isValid("2016-12-15",policy) );
		System.out.println("2017-01-14 --> " + JoinDateValidator.isValid("2017-01-14",policy) );
		System.out.println("2017-01-16 --> " + JoinDateValidator.isValid("2017-01-16",policy) );
	}
	
}
