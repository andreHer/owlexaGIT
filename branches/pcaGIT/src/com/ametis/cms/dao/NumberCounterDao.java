package com.ametis.cms.dao;

import org.hibernate.Criteria;

import com.ametis.cms.datamodel.NumberCounter;

public interface NumberCounterDao {

	public NumberCounter getNumberCounter(int clientId) throws Exception;
	public NumberCounter updateNumberCounter(NumberCounter counter ) throws Exception;
	
	public Criteria getCriteria() throws Exception ;
}
