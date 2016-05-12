
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.CoverageStatus;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * CoverageStatus is a mapping of coverage_status Table.
*/
public class CoverageStatusForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewCoverageStatus = false;
	private CoverageStatus coverageStatusBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public CoverageStatusForm()
    {
    	this.coverageStatusBean = new CoverageStatus();
    	this.isNewCoverageStatus = true;
    }
    public CoverageStatusForm (CoverageStatus object){
		this.coverageStatusBean = object;
    }
    public boolean isNewCoverageStatus (){

    	return this.isNewCoverageStatus;
    }
	public CoverageStatus getCoverageStatus (){
		return this.coverageStatusBean ;
	}
	public void setCoverageStatus (CoverageStatus object){
		this.coverageStatusBean = object;
	}

			
	public void setCoverageStatusId(String obj){

		coverageStatusBean.setCoverageStatusId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCoverageStatusId(){
		return StringUtil.getStringValue(
		coverageStatusBean.getCoverageStatusId());

	}
	
					public void setCoverageStatusName(String obj){

		coverageStatusBean.setCoverageStatusName(new String(obj));

	}

	public String getCoverageStatusName(){
		return StringUtil.getStringValue(
		coverageStatusBean.getCoverageStatusName());

	}
	
					public void setDescription(String obj){

		coverageStatusBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		coverageStatusBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
