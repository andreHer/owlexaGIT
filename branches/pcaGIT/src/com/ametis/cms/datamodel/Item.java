
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="item")
public class Item implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int DIAGNOSTIC_TYPE = 1;
	public static final int ADMINISTRATIVE_TYPE = 2;
	
	public static final int DRUGS_OP = 36; 

	//Fields
		
	/**data for the column :
	
 --------- item.item_id --------- 
 schema        = null
 tableName     = item
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
	private Integer itemId;
						
	/**data for the column :
	
 --------- item.item_name --------- 
 schema        = null
 tableName     = item
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
	private String itemName;
			
	/**data for the column :
	
 --------- item.item_code --------- 
 schema        = null
 tableName     = item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String itemCode;
			
	/**data for the column :
	
 --------- item.item_description --------- 
 schema        = null
 tableName     = item
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String itemDescription;
			
	/**data for the column :
	
 --------- item.item_default_value --------- 
 schema        = null
 tableName     = item
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
	private Double itemDefaultValue;
			
	/**data for the column :
	
 --------- item.created_time --------- 
 schema        = null
 tableName     = item
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
	
 --------- item.created_by --------- 
 schema        = null
 tableName     = item
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
	
 --------- item.deleted_time --------- 
 schema        = null
 tableName     = item
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
	
 --------- item.deleted_by --------- 
 schema        = null
 tableName     = item
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
	
 --------- item.modified_time --------- 
 schema        = null
 tableName     = item
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
	
 --------- item.modified_by --------- 
 schema        = null
 tableName     = item
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
	
 --------- item.deleted_status --------- 
 schema        = null
 tableName     = item
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
		
	// foreign affairs
	
			/** ItemCategory
	data for the foreign class :
	
 --------- item_category.item_category_id --------- 
 schema        = null
 tableName     = item_category
 foreignCol    = item_category_id
 foreignTab    = item
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
	private Integer itemType;
	private String medicalCode;
	private String itemEDCCode;
	private String itemAlternateCode;
	private String itemAlternateCode2;
	private String itemEDCName;
	private Item aggregateItemId;
	private String memberMovementType;
	
			
	// -- foreign affairs end

	// -- exported key

	
			/** ClaimItem
	data for the exported class :
	
 --------- claim_item.item_id --------- 
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
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = item.item_id

=========================================



	 */
	private Set <ClaimItem> claimItems = new HashSet(0);
				/** CostContainment
	data for the exported class :
	
 --------- cost_containment.item_id --------- 
 schema        = null
 tableName     = cost_containment
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
 this columns points to  = item.item_id

=========================================



	 */
	private Set <CostContainment> costContainments = new HashSet(0);
				/** DiagnosisItem
	data for the exported class :
	
 --------- diagnosis_item.item_id --------- 
 schema        = null
 tableName     = diagnosis_item
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
 this columns points to  = item.item_id

=========================================



	 */
	private Set <DiagnosisItem> diagnosisItems = new HashSet(0);
				/** InvoiceItem
	data for the exported class :
	
 --------- invoice_item.item_id --------- 
 schema        = null
 tableName     = invoice_item
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
 this columns points to  = item.item_id

=========================================



	 */
	private Set <InvoiceItem> invoiceItems = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getItemId(){
		return itemId;
	}
	public void setItemId(java.lang.Integer value){
		itemId = value;
	}
			// PK GETTER SETTER END

									@Column(name="item_name")
	public java.lang.String getItemName(){
		return itemName;
	}
	public void setItemName(java.lang.String value){
		itemName = value;
	}
				@Column(name="item_code")
	public java.lang.String getItemCode(){
		return itemCode;
	}
	public void setItemCode(java.lang.String value){
		itemCode = value;
	}
				@Column(name="item_description")
	public java.lang.String getItemDescription(){
		return itemDescription;
	}
	public void setItemDescription(java.lang.String value){
		itemDescription = value;
	}
				@Column(name="item_default_value")
	public java.lang.Double getItemDefaultValue(){
		return itemDefaultValue;
	}
	public void setItemDefaultValue(java.lang.Double value){
		itemDefaultValue = value;
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
			
	// foreign affairs end

// exported key

	
			/** ClaimItem */
	@OneToMany(mappedBy="itemId")
	public Set<ClaimItem> getClaimItems(){
		return this.claimItems;
	}

	/** ClaimItem */
	public void setClaimItems(Set<ClaimItem> obj){
		this.claimItems = obj;
	}
				/** CostContainment */
	@OneToMany(mappedBy="itemId")
	public Set<CostContainment> getCostContainments(){
		return this.costContainments;
	}

	/** CostContainment */
	public void setCostContainments(Set<CostContainment> obj){
		this.costContainments = obj;
	}
				/** DiagnosisItem */
	@OneToMany(mappedBy="itemId")
	public Set<DiagnosisItem> getDiagnosisItems(){
		return this.diagnosisItems;
	}

	/** DiagnosisItem */
	public void setDiagnosisItems(Set<DiagnosisItem> obj){
		this.diagnosisItems = obj;
	}
				/** InvoiceItem */
	@OneToMany(mappedBy="itemId")
	public Set<InvoiceItem> getInvoiceItems(){
		return this.invoiceItems;
	}

	/** InvoiceItem */
	public void setInvoiceItems(Set<InvoiceItem> obj){
		this.invoiceItems = obj;
	}
	@Column(name="item_type")
	public Integer getItemType() {
		return itemType;
	}
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}
	@Column(name="medical_code")
	public String getMedicalCode() {
		return medicalCode;
	}
	public void setMedicalCode(String medicalCode) {
		this.medicalCode = medicalCode;
	}
	@Column(name="item_edc_code")
	public String getItemEDCCode() {
		return itemEDCCode;
	}
	public void setItemEDCCode(String itemEDCCode) {
		this.itemEDCCode = itemEDCCode;
	}
	@Column(name="item_alternate_code")
	public String getItemAlternateCode() {
		return itemAlternateCode;
	}
	public void setItemAlternateCode(String itemAlternateCode) {
		this.itemAlternateCode = itemAlternateCode;
	}
	@Column(name="item_edc_name")
	public String getItemEDCName() {
		return itemEDCName;
	}
	public void setItemEDCName(String itemEDCName) {
		this.itemEDCName = itemEDCName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="aggregate_item_id")
	public Item getAggregateItemId() {
		return aggregateItemId;
	}
	public void setAggregateItemId(Item aggregateItemId) {
		this.aggregateItemId = aggregateItemId;
	}
	@Column(name="item_alternate_code_2")
	public String getItemAlternateCode2() {
		return itemAlternateCode2;
	}
	public void setItemAlternateCode2(String itemAlternateCode2) {
		this.itemAlternateCode2 = itemAlternateCode2;
	}
	@Column(name="member_movement_type")
	public String getMemberMovementType() {
		return memberMovementType;
	}
	public void setMemberMovementType(String memberMovementType) {
		this.memberMovementType = memberMovementType;
	}
	
	
	//exported key end
	


}