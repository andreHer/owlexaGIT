
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Priority is a mapping of priority Table.
*/
public class PriorityForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPriority = false;
	private Priority priorityBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PriorityForm()
    {
    	this.priorityBean = new Priority();
    	this.isNewPriority = true;
    }
    public PriorityForm (Priority object){
		this.priorityBean = object;
    }
    public boolean isNewPriority (){

    	return this.isNewPriority;
    }
	public Priority getPriority (){
		return this.priorityBean ;
	}
	public void setPriority (Priority object){
		this.priorityBean = object;
	}

			
	public void setPriorityId(String obj){

		priorityBean.setPriorityId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPriorityId(){
		return StringUtil.getStringValue(
		priorityBean.getPriorityId());

	}
	
					public void setPriorityCode(String obj){

		priorityBean.setPriorityCode(new String(obj));

	}

	public String getPriorityCode(){
		return StringUtil.getStringValue(
		priorityBean.getPriorityCode());

	}
	
					public void setDescription(String obj){

		priorityBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		priorityBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
