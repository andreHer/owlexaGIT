
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="treatment_upgrade_type")
public class TreatmentUpgradeType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int SESUAI_PLAN = 1;
	public static final int NAIK_ATAS_PERMINTAAN_SENDIRI = 2;
	public static final int KAMAR_PENUH = 3;
	public static final int KAMAR_TIDAK_TERSEDIA = 4;
	public static final int REKOMENDASI_DOKTER = 5;
	
	public static final int PRO_RATE_UPGRADE_CALC = 1;
	public static final int PERCENTAGE_UPGRADE_CALC = 2;
	public static final int FIXED_POINT_CALC = 3;
	
	public static final int MULTIPLIER_WITHOUT_BENEFIT_CHECK = 1;
	public static final int MULTIPLIER_WITH_BENEFIT_CHECK = 2;

	//Fields
		
	/**data for the column :
	
 --------- treatment_upgrade_type.treatment_upgrade_type_id --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer treatmentUpgradeTypeId;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.treatment_upgrade_type_name --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String treatmentUpgradeTypeName;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.treatment_upgrade_type_code --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String treatmentUpgradeTypeCode;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.description --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- treatment_upgrade_type.created_by --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.created_time --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- treatment_upgrade_type.modified_time --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.modified_by --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.deleted_time --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.deleted_by --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- treatment_upgrade_type.deleted_status --------- 
 schema        = null
 tableName     = treatment_upgrade_type
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
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
	@Column(name="treatment_upgrade_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getTreatmentUpgradeTypeId(){
		return treatmentUpgradeTypeId;
	}
	public void setTreatmentUpgradeTypeId(java.lang.Integer value){
		treatmentUpgradeTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="treatment_upgrade_type_name")
	public java.lang.String getTreatmentUpgradeTypeName(){
		return treatmentUpgradeTypeName;
	}
	public void setTreatmentUpgradeTypeName(java.lang.String value){
		treatmentUpgradeTypeName = value;
	}
				@Column(name="treatment_upgrade_type_code")
	public java.lang.String getTreatmentUpgradeTypeCode(){
		return treatmentUpgradeTypeCode;
	}
	public void setTreatmentUpgradeTypeCode(java.lang.String value){
		treatmentUpgradeTypeCode = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
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