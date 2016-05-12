
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
@Table(name="substitution_item")
public class SubstitutionItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- substitution_item.substitution_item_id --------- 
 schema        = null
 tableName     = substitution_item
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
	private Integer substitutionItemId;
						
	/**data for the column :
	
 --------- substitution_item.item_id --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer itemId;
			
	/**data for the column :
	
 --------- substitution_item.measurement_unit --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer measurementUnit;
			
	/**data for the column :
	
 --------- substitution_item.description --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- substitution_item.total_substitution_value --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalSubstitutionValue;
			
	/**data for the column :
	
 --------- substitution_item.total_item --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalItem;
			
	/**data for the column :
	
 --------- substitution_item.created_time --------- 
 schema        = null
 tableName     = substitution_item
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- substitution_item.created_by --------- 
 schema        = null
 tableName     = substitution_item
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
	private String createdBy;
			
	/**data for the column :
	
 --------- substitution_item.deleted_time --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- substitution_item.deleted_by --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- substitution_item.modified_time --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- substitution_item.modified_by --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- substitution_item.deleted_status --------- 
 schema        = null
 tableName     = substitution_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** CostContainment
	data for the foreign class :
	
 --------- cost_containment.cost_containment_id --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = cost_containment_id
 foreignTab    = substitution_item
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
	private CostContainment costContainmentId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="substitution_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getSubstitutionItemId(){
		return substitutionItemId;
	}
	public void setSubstitutionItemId(java.lang.Integer value){
		substitutionItemId = value;
	}
			// PK GETTER SETTER END

									@Column(name="item_id")
	public java.lang.Integer getItemId(){
		return itemId;
	}
	public void setItemId(java.lang.Integer value){
		itemId = value;
	}
				@Column(name="measurement_unit")
	public java.lang.Integer getMeasurementUnit(){
		return measurementUnit;
	}
	public void setMeasurementUnit(java.lang.Integer value){
		measurementUnit = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="total_substitution_value")
	public java.lang.Double getTotalSubstitutionValue(){
		return totalSubstitutionValue;
	}
	public void setTotalSubstitutionValue(java.lang.Double value){
		totalSubstitutionValue = value;
	}
				@Column(name="total_item")
	public java.lang.Double getTotalItem(){
		return totalItem;
	}
	public void setTotalItem(java.lang.Double value){
		totalItem = value;
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
	
			/** CostContainment */
	@ManyToOne
	@JoinColumn(name="cost_containment_id")
	public CostContainment getCostContainmentId(){
		return this.costContainmentId;
	}

	/** CostContainment */
	public void setCostContainmentId(CostContainment obj){
		this.costContainmentId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}