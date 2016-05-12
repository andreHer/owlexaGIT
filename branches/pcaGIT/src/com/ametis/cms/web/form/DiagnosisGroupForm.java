
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * DiagnosisGroup is a mapping of diagnosis_group Table.
*/
public class DiagnosisGroupForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDiagnosisGroup = false;
	private DiagnosisGroup diagnosisGroupBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DiagnosisGroupForm()
    {
    	this.diagnosisGroupBean = new DiagnosisGroup();
    	this.isNewDiagnosisGroup = true;
    }
    public DiagnosisGroupForm (DiagnosisGroup object){
		this.diagnosisGroupBean = object;
    }
    public boolean isNewDiagnosisGroup (){

    	return this.isNewDiagnosisGroup;
    }
	public DiagnosisGroup getDiagnosisGroup (){
		return this.diagnosisGroupBean ;
	}
	public void setDiagnosisGroup (DiagnosisGroup object){
		this.diagnosisGroupBean = object;
	}

			
	public void setDiagnosisGroupId(String obj){

		diagnosisGroupBean.setDiagnosisGroupId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDiagnosisGroupId(){
		return StringUtil.getStringValue(
		diagnosisGroupBean.getDiagnosisGroupId());

	}
	
					public void setDiagnosisGroupCode(String obj){

		diagnosisGroupBean.setDiagnosisGroupCode(new String(obj));

	}

	public String getDiagnosisGroupCode(){
		return StringUtil.getStringValue(
		diagnosisGroupBean.getDiagnosisGroupCode());

	}
	
					public void setDiagnosisGroupCategory(String obj){

		diagnosisGroupBean.setDiagnosisGroupCategory(new String(obj));

	}

	public String getDiagnosisGroupCategory(){
		return StringUtil.getStringValue(
		diagnosisGroupBean.getDiagnosisGroupCategory());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
