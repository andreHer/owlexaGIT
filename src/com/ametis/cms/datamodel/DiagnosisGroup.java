
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="diagnosis_group")
public class DiagnosisGroup implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- diagnosis_group.diagnosis_group_id --------- 
 schema        = public
 tableName     = diagnosis_group
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = nextval('diagnosis_group_diagnosis_group_id_seq'::regclass)
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer diagnosisGroupId;
			
	/**data for the column :
	
 --------- diagnosis_group.diagnosis_group_code --------- 
 schema        = public
 tableName     = diagnosis_group
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosisGroupCode;
			
	/**data for the column :
	
 --------- diagnosis_group.diagnosis_group_category --------- 
 schema        = public
 tableName     = diagnosis_group
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosisGroupCategory;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="diagnosis_group_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getDiagnosisGroupId(){
		return diagnosisGroupId;
	}
	public void setDiagnosisGroupId(java.lang.Integer value){
		diagnosisGroupId = value;
	}
			// PK GETTER SETTER END

						@Column(name="diagnosis_group_code")
	public java.lang.String getDiagnosisGroupCode(){
		return diagnosisGroupCode;
	}
	public void setDiagnosisGroupCode(java.lang.String value){
		diagnosisGroupCode = value;
	}
				@Column(name="diagnosis_group_category")
	public java.lang.String getDiagnosisGroupCategory(){
		return diagnosisGroupCategory;
	}
	public void setDiagnosisGroupCategory(java.lang.String value){
		diagnosisGroupCategory = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}