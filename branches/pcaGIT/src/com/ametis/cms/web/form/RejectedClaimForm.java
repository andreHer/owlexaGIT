
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * RejectedClaim is a mapping of rejected_claim Table.
*/
public class RejectedClaimForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRejectedClaim = false;
	private RejectedClaim rejectedClaimBean ;
	private String claimNumber;
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RejectedClaimForm()
    {
    	this.rejectedClaimBean = new RejectedClaim();
    	this.isNewRejectedClaim = true;
    }
    public RejectedClaimForm (RejectedClaim object){
		this.rejectedClaimBean = object;
    }
    public boolean isNewRejectedClaim (){

    	return this.isNewRejectedClaim;
    }
	public RejectedClaim getRejectedClaim (){
		return this.rejectedClaimBean ;
	}
	public void setRejectedClaim (RejectedClaim object){
		this.rejectedClaimBean = object;
	}

			
	public void setRejectedClaimId(String obj){

		rejectedClaimBean.setRejectedClaimId(StringUtil.getLongValue(obj,0));

	}

	public String getRejectedClaimId(){
		return StringUtil.getStringValue(
		rejectedClaimBean.getRejectedClaimId());

	}
	
				
	public void setClaimId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Claim claim = new Claim();
			claim.setClaimId(Integer.valueOf(obj));
			
			rejectedClaimBean.setClaimId(claim);
		}

	}

	public String getClaimId(){
		String result = "";
		
		if (rejectedClaimBean.getClaimId() != null){
			result = rejectedClaimBean.getClaimId().getClaimId().toString();
		}
		return result;

	}
	
				
	public void setRejectionCategory(RejectCategory obj){

		rejectedClaimBean.setRejectionCategory(obj);

	}

	public RejectCategory getRejectionCategory(){
		return 	rejectedClaimBean.getRejectionCategory();

	}
	
					public void setRejectionSubject(String obj){

		rejectedClaimBean.setRejectionSubject(new String(obj));

	}

	public String getRejectionSubject(){
		return StringUtil.getStringValue(
		rejectedClaimBean.getRejectionSubject());

	}
	
					public void setDescription(String obj){

		rejectedClaimBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		rejectedClaimBean.getDescription());

	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	
				
// class+ 

// class- 
}
