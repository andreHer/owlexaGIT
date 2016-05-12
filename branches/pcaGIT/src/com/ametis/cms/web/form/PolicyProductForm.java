
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * PolicyProduct is a mapping of policy_product Table.
*/
public class PolicyProductForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPolicyProduct = false;
	private PolicyProduct policyProductBean ;
	
	private String policyNumber;
	private String productCode;

	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PolicyProductForm()
    {
    	this.policyProductBean = new PolicyProduct();
    	this.isNewPolicyProduct = true;
    }
    public PolicyProductForm (PolicyProduct object){
		this.policyProductBean = object;
    }
    public boolean isNewPolicyProduct (){

    	return this.isNewPolicyProduct;
    }
	public PolicyProduct getPolicyProduct (){
		return this.policyProductBean ;
	}
	public void setPolicyProduct (PolicyProduct object){
		this.policyProductBean = object;
	}

	public void setPolicyProductId(String obj){

		policyProductBean.setPolicyProductId(StringUtil.getLongValue(obj,0));

	}

	public String getPolicyProductId(){
		return StringUtil.getStringValue(
		policyProductBean.getPolicyProductId());

	}
	
							
	public void setTotal(String obj){

		policyProductBean.setTotal(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotal(){
		return StringUtil.getStringValue(
		policyProductBean.getTotal());

	}
	
					

	// foreign affairs
	
	

	
	public void setPolicyId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			Policy fk = new Policy();
			fk.setPolicyId(StringUtil.getIntegerValue(obj,0));
			policyProductBean.setPolicyId(fk);
		}

	}

	public String getPolicyId(){
		String result = "";
		
		if (policyProductBean.getProductId() != null){
			result = StringUtil.getStringValue(
					policyProductBean.getPolicyId().getPolicyId());
		}
		return result;
		

	}
	//---
	
	

	
	public void setProductId(String obj){
            if (obj != null && !obj.equalsIgnoreCase("")){
		Product fk = new Product();
		fk.setProductId(StringUtil.getIntegerValue(obj,0));
		policyProductBean.setProductId(fk);
            }

	}

	public String getProductId(){
		String result = "";
		
		if (policyProductBean.getProductId() != null){
			result = StringUtil.getStringValue(
					policyProductBean.getProductId().getProductId());
		}
		
		return result;
		 

	}
	public void setRelationshipId(String obj){
        if (obj != null && !obj.equalsIgnoreCase("")){
			Relationship fk = new Relationship();
			fk.setRelationshipId(StringUtil.getIntegerValue(obj,0));
			policyProductBean.setRelationshipId(fk);
        }

	}

	public String getRelationshipId(){
		String result = "";
	
		if (policyProductBean.getRelationshipId() != null){
			result = StringUtil.getStringValue(
					policyProductBean.getRelationshipId().getRelationshipId());
		}	
		return result; 
	}
	public void setPrice(String obj){
        if (obj != null && !obj.equalsIgnoreCase("")){
			
			policyProductBean.setPrice(Double.valueOf(obj));
        }

	}

	public String getPrice(){
		String result = "";
	
		if (policyProductBean.getPrice() != null){
			result = StringUtil.getStringValue(
					policyProductBean.getPrice());
		}	
		return result; 
	}
	public void setSex(String obj){
        if (obj != null && !obj.equalsIgnoreCase("")){
			
			policyProductBean.setSex(obj);
        }

	}

	public String getSex(){
		String result = "";
	
			result = StringUtil.getStringValue(
					policyProductBean.getSex());
			
		return result; 
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	
 
}


