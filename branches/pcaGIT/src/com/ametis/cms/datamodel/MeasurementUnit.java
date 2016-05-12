
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="measurement_unit")
public class MeasurementUnit implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- measurement_unit.measurement_unit_id --------- 
 schema        = null
 tableName     = measurement_unit
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
	private Integer measurementUnitId;
			
	/**data for the column :
	
 --------- measurement_unit.measurement_unit_name --------- 
 schema        = null
 tableName     = measurement_unit
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
	private String measurementUnitName;
			
	/**data for the column :
	
 --------- measurement_unit.description --------- 
 schema        = null
 tableName     = measurement_unit
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
			
	/**data for the column :
	
 --------- measurement_unit.created_time --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- measurement_unit.created_by --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- measurement_unit.deleted_time --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- measurement_unit.deleted_by --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- measurement_unit.modified_time --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- measurement_unit.modified_by --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- measurement_unit.deleted_status --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** ClaimItem
	data for the exported class :
	
 --------- claim_item.measurement_unit_id --------- 
 schema        = null
 tableName     = claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = measurement_unit.measurement_unit_id

=========================================



	 */
	
	private Set <ClaimItem> claimItems = new HashSet(0);
				/** ItemMeasurementUnit
	data for the exported class :
	
 --------- item_measurement_unit.measurement_unit_id --------- 
 schema        = null
 tableName     = item_measurement_unit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = measurement_unit.measurement_unit_id

=========================================



	 */
	private Set <ItemMeasurementUnit> itemMeasurementUnits = new HashSet(0);
				/** ProductBenefit
	data for the exported class :
	
 --------- product_benefit.measurement_unit --------- 
 schema        = null
 tableName     = product_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = measurement_unit.measurement_unit_id

=========================================



	 */
	private Set <ProductBenefit> productBenefits = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="measurement_unit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMeasurementUnitId(){
		return measurementUnitId;
	}
	public void setMeasurementUnitId(java.lang.Integer value){
		measurementUnitId = value;
	}
			// PK GETTER SETTER END

						@Column(name="measurement_unit_name")
	public java.lang.String getMeasurementUnitName(){
		return measurementUnitName;
	}
	public void setMeasurementUnitName(java.lang.String value){
		measurementUnitName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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

	
			/** ClaimItem */
	@OneToMany(mappedBy="measurementUnitId")
	public Set<ClaimItem> getClaimItems(){
		return this.claimItems;
	}

	/** ClaimItem */
	public void setClaimItems(Set<ClaimItem> obj){
		this.claimItems = obj;
	}
				/** ItemMeasurementUnit */
	@OneToMany(mappedBy="measurementUnitId")
	public Set<ItemMeasurementUnit> getItemMeasurementUnits(){
		return this.itemMeasurementUnits;
	}

	/** ItemMeasurementUnit */
	public void setItemMeasurementUnits(Set<ItemMeasurementUnit> obj){
		this.itemMeasurementUnits = obj;
	}
				/** ProductBenefit */
	@OneToMany(mappedBy="measurementUnit")
	public Set<ProductBenefit> getProductBenefits(){
		return this.productBenefits;
	}

	/** ProductBenefit */
	public void setProductBenefits(Set<ProductBenefit> obj){
		this.productBenefits = obj;
	}

			
	//exported key end



}