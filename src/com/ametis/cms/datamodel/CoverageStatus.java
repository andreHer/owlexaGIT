
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="coverage_status")
public class CoverageStatus implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int COVERED = 2;
	public static final int UNCOVERED = 1;

	//Fields
		
	/**data for the column :
	
 --------- coverage_status.coverage_status_id --------- 
 schema        = null
 tableName     = coverage_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer coverageStatusId;
			
	/**data for the column :
	
 --------- coverage_status.coverage_status_name --------- 
 schema        = null
 tableName     = coverage_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
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
	private String coverageStatusName;
			
	/**data for the column :
	
 --------- coverage_status.description --------- 
 schema        = null
 tableName     = coverage_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
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
	private String description;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="coverage_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCoverageStatusId(){
		return coverageStatusId;
	}
	public void setCoverageStatusId(java.lang.Integer value){
		coverageStatusId = value;
	}
			// PK GETTER SETTER END

						@Column(name="coverage_status_name")
	public java.lang.String getCoverageStatusName(){
		return coverageStatusName;
	}
	public void setCoverageStatusName(java.lang.String value){
		coverageStatusName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}