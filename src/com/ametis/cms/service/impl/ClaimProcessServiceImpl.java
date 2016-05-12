package com.ametis.cms.service.impl;

import java.io.Serializable;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.service.ClaimProcessService;
import com.ametis.cms.service.ClaimService;

public class ClaimProcessServiceImpl implements ClaimProcessService{
	
	private ClaimService claimService;
	
	

	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}



	@Override
	public boolean voidClaim(Integer claimId, String voidNote, ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		
		Claim claim = claimService.openClaim(claimId, user);
		boolean result = false;
		
		if (claim != null){
			result = claimService.voidClaim(claimId, "XXXXX", voidNote, user);
		}
				
		
		return result;
	}

}
