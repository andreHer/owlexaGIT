
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Gender;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Gender is a mapping of gender Table.
*/
public class GenderForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewGender = false;
	private Gender genderBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public GenderForm()
    {
    	this.genderBean = new Gender();
    	this.isNewGender = true;
    }
    public GenderForm (Gender object){
		this.genderBean = object;
    }
    public boolean isNewGender (){

    	return this.isNewGender;
    }
	public Gender getGender (){
		return this.genderBean ;
	}
	public void setGender (Gender object){
		this.genderBean = object;
	}

			
	public void setGenderId(String obj){

		genderBean.setGenderId(StringUtil.getIntegerValue(obj,0));

	}

	public String getGenderId(){
		return StringUtil.getStringValue(
		genderBean.getGenderId());

	}
	
					public void setGenderName(String obj){

		genderBean.setGenderName(new String(obj));

	}

	public String getGenderName(){
		return StringUtil.getStringValue(
		genderBean.getGenderName());

	}
	
					public void setDescription(String obj){

		genderBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		genderBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
