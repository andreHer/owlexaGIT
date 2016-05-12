package com.ametis.cms.service;

import java.io.Serializable;

import com.ametis.cms.datamodel.ActionUser;

public interface ClaimProcessService {

	public boolean voidClaim (Integer claimId, String voidNote, ActionUser user) throws Exception;
	
}
