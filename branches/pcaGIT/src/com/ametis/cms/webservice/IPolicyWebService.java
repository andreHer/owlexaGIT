package com.ametis.cms.webservice;


import java.util.Collection;

import javax.jws.WebService;

import com.ametis.cms.datamodel.Policy;

@WebService (targetNamespace="http://ametis.co.id/services/PolicyWebService")
public interface IPolicyWebService {

	public void synchronizePolicy(Integer policyId) throws Exception;
	public Collection<Integer> getPolicySynchronizeList () throws Exception;
	
	public boolean commitSynchronize (Integer policyId) throws Exception;
	
	public Collection<Integer> getActivePolicy() throws Exception;
	
	public void updateExpiredPolicy() throws Exception;
}
