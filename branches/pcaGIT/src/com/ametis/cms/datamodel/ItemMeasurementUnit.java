
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="item_measurement_unit")
public class ItemMeasurementUnit implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- item_measurement_unit.item_measurement_unit_id --------- 
 schema        = null
 tableName     = item_measurement_unit
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
	private Integer itemMeasurementUnitId;
									
	/**data for the column :
	
 --------- item_measurement_unit.created_time --------- 
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
 ordinal       = 4
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- item_measurement_unit.created_by --------- 
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
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- item_measurement_unit.deleted_time --------- 
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
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- item_measurement_unit.deleted_by --------- 
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
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- item_measurement_unit.modified_time --------- 
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
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- item_measurement_unit.modified_by --------- 
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
 ordinal       = 9
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- item_measurement_unit.deleted_status --------- 
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
 ordinal       = 10
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** ItemCategory
	data for the foreign class :
	
 --------- item_category.item_category_id --------- 
 schema        = null
 tableName     = item_category
 foreignCol    = item_category_id
 foreignTab    = item_measurement_unit
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
	private ItemCategory itemCategoryId;
				/** MeasurementUnit
	data for the foreign class :
	
 --------- measurement_unit.measurement_unit_id --------- 
 schema        = null
 tableName     = measurement_unit
 foreignCol    = measurement_unit_id
 foreignTab    = item_measurement_unit
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
	private MeasurementUnit measurementUnitId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="item_measurement_unit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getItemMeasurementUnitId(){
		return itemMeasurementUnitId;
	}
	public void setItemMeasurementUnitId(java.lang.Integer value){
		itemMeasurementUnitId = value;
	}
			// PK GETTER SETTER END

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
	
			/** ItemCategory */
	@ManyToOne
	@JoinColumn(name="item_category_id")
	public ItemCategory getItemCategoryId(){
		return this.itemCategoryId;
	}

	/** ItemCategory */
	public void setItemCategoryId(ItemCategory obj){
		this.itemCategoryId = obj;
	}
				/** MeasurementUnit */
	@ManyToOne
	@JoinColumn(name="measurement_unit_id")
	public MeasurementUnit getMeasurementUnitId(){
		return this.measurementUnitId;
	}

	/** MeasurementUnit */
	public void setMeasurementUnitId(MeasurementUnit obj){
		this.measurementUnitId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}