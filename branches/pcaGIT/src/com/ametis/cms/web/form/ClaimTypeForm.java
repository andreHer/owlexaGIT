
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * ClaimType is a mapping of claim_type Table.
*/
public class ClaimTypeForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewClaimType = false;
	private ClaimType claimTypeBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public ClaimTypeForm()
    {
    	this.claimTypeBean = new ClaimType();
    	this.isNewClaimType = true;
    }
    public ClaimTypeForm (ClaimType object){
		this.claimTypeBean = object;
    }
    public boolean isNewClaimType (){

    	return this.isNewClaimType;
    }
	public ClaimType getClaimType (){
		return this.claimTypeBean ;
	}
	public void setClaimType (ClaimType object){
		this.claimTypeBean = object;
	}

			
	public void setClaimTypeId(String obj){

		claimTypeBean.setClaimTypeId(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimTypeId(){
		return StringUtil.getStringValue(
		claimTypeBean.getClaimTypeId());

	}
	
					public void setClaimTypeName(String obj){

		claimTypeBean.setClaimTypeName(new String(obj));

	}

	public String getClaimTypeName(){
		return StringUtil.getStringValue(
		claimTypeBean.getClaimTypeName());

	}
	
					public void setDescription(String obj){

		claimTypeBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		claimTypeBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
