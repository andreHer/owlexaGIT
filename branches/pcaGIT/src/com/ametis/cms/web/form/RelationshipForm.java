
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.Relationship;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * Relationship is a mapping of relationship Table.
*/
public class RelationshipForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewRelationship = false;
	private Relationship relationshipBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public RelationshipForm()
    {
    	this.relationshipBean = new Relationship();
    	this.isNewRelationship = true;
    }
    public RelationshipForm (Relationship object){
		this.relationshipBean = object;
    }
    public boolean isNewRelationship (){

    	return this.isNewRelationship;
    }
	public Relationship getRelationship (){
		return this.relationshipBean ;
	}
	public void setRelationship (Relationship object){
		this.relationshipBean = object;
	}

			
	public void setRelationshipId(String obj){

		relationshipBean.setRelationshipId(StringUtil.getIntegerValue(obj,0));

	}

	public String getRelationshipId(){
		return StringUtil.getStringValue(
		relationshipBean.getRelationshipId());

	}
	
					public void setRelationshipName(String obj){

		relationshipBean.setRelationshipName(new String(obj));

	}

	public String getRelationshipName(){
		return StringUtil.getStringValue(
		relationshipBean.getRelationshipName());

	}
	
					public void setDescription(String obj){

		relationshipBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		relationshipBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
