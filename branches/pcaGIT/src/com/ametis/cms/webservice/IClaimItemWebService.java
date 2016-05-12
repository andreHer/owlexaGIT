package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ClaimItem;

@WebService (targetNamespace="http://ametis.co.id/services/ClaimItemWebService")
public interface IClaimItemWebService {

	public Collection<ClaimItem> getClaimItems (int claimId) ;
	public ClaimItem getClaimItem (int claimItemId);
	public String createClaimItem (ClaimItem claimItem, ActionUser actionUser);
	
}
