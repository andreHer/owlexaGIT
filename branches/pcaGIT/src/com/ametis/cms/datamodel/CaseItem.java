
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
@Table(name="case_item")
public class CaseItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- case_item.case_item_id --------- 
 schema        = null
 tableName     = case_item
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
	private Integer caseItemId;

	private Case caseId;
	
	/**data for the column :
	
 --------- case_item.item_id --------- 
 schema        = null
 tableName     = case_item
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

=========================================


*/
	private Item itemId;
			
	/**data for the column :
	
 --------- case_item.usage_time --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp usageTime;
			
	/**data for the column :
	
 --------- case_item.usage_amount --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double usageAmount;
			
	/**data for the column :
	
 --------- case_item.usage_value --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Double usageValue;
						
	/**data for the column :
	
 --------- case_item.created_time --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- case_item.created_by --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String createdBy;
			
	/**data for the column :
	
 --------- case_item.deleted_time --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- case_item.deleted_by --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- case_item.modified_time --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- case_item.modified_by --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- case_item.deleted_status --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- case_item.description --------- 
 schema        = null
 tableName     = case_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
		
	// foreign affairs
	
			/** CaseEvent
	data for the foreign class :
	
 --------- case_event.case_event_id --------- 
 schema        = null
 tableName     = case_event
 foreignCol    = case_event_id
 foreignTab    = case_item
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
	private CaseEvent caseEventId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="case_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCaseItemId(){
		return caseItemId;
	}
	public void setCaseItemId(java.lang.Integer value){
		caseItemId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId(){
		return itemId;
	}
	public void setItemId(Item value){
		itemId = value;
	}
				@Column(name="usage_time")
	public java.sql.Timestamp getUsageTime(){
		return usageTime;
	}
	public void setUsageTime(java.sql.Timestamp value){
		usageTime = value;
	}
				@Column(name="usage_amount")
	public java.lang.Double getUsageAmount(){
		return usageAmount;
	}
	public void setUsageAmount(java.lang.Double value){
		usageAmount = value;
	}
				@Column(name="usage_value")
	public java.lang.Double getUsageValue(){
		return usageValue;
	}
	public void setUsageValue(java.lang.Double value){
		usageValue = value;
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
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
		
	// foreign affairs
	
			/** CaseEvent */
	@ManyToOne
	@JoinColumn(name="case_event_id")
	public CaseEvent getCaseEventId(){
		return this.caseEventId;
	}

	/** CaseEvent */
	public void setCaseEventId(CaseEvent obj){
		this.caseEventId = obj;
	}
	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId() {
		return caseId;
	}
	public void setCaseId(Case caseId) {
		this.caseId = caseId;
	}

	
	// foreign affairs end

// exported key

	
		
	//exported key end



}