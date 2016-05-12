
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="treatment_risk")
public class TreatmentRisk implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- treatment_risk.treatment_risk_id --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 3
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer treatmentRiskId;
			
	/**data for the column :
	
 --------- treatment_risk.treatment_risk_name --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String treatmentRiskName;
			
	/**data for the column :
	
 --------- treatment_risk.treatment_risk_code --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String treatmentRiskCode;
			
	/**data for the column :
	
 --------- treatment_risk.description --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- treatment_risk.risk_index --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double riskIndex;
			
	/**data for the column :
	
 --------- treatment_risk.created_time --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- treatment_risk.created_by --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- treatment_risk.modified_time --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- treatment_risk.modified_by --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- treatment_risk.deleted_time --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- treatment_risk.deleted_by --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- treatment_risk.deleted_status --------- 
 schema        = null
 tableName     = treatment_risk
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="treatment_risk_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getTreatmentRiskId(){
		return treatmentRiskId;
	}
	public void setTreatmentRiskId(java.lang.Integer value){
		treatmentRiskId = value;
	}
			// PK GETTER SETTER END

						@Column(name="treatment_risk_name")
	public java.lang.String getTreatmentRiskName(){
		return treatmentRiskName;
	}
	public void setTreatmentRiskName(java.lang.String value){
		treatmentRiskName = value;
	}
				@Column(name="treatment_risk_code")
	public java.lang.String getTreatmentRiskCode(){
		return treatmentRiskCode;
	}
	public void setTreatmentRiskCode(java.lang.String value){
		treatmentRiskCode = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="risk_index")
	public java.lang.Double getRiskIndex(){
		return riskIndex;
	}
	public void setRiskIndex(java.lang.Double value){
		riskIndex = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}