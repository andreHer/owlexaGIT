package com.ametis.cms.dto;

import java.io.Serializable;
import java.util.Collection;

import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ExcessCharge;

public class ExcessDto implements Serializable{

	private ExcessCharge excessCharge;
	private Collection<ClaimItem> claimItemList;
	public ExcessCharge getExcessCharge() {
		return excessCharge;
	}
	public void setExcessCharge(ExcessCharge excessCharge) {
		this.excessCharge = excessCharge;
	}
	public Collection<ClaimItem> getClaimItemList() {
		return claimItemList;
	}
	public void setClaimItemList(Collection<ClaimItem> claimItemList) {
		this.claimItemList = claimItemList;
	}
	
	
}
