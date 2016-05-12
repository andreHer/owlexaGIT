
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
@Table(name="item_category")
public class ItemCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int RUANGAN = 1;
	public static final int POLIKLINIK = 4;
	public static final int ICU = 2;
	
	//Fields
		
	/**data for the column :
	
 --------- item_category.item_category_id --------- 
 schema        = null
 tableName     = item_category
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
	private Integer itemCategoryId;
			
	/**data for the column :
	
 --------- item_category.description --------- 
 schema        = null
 tableName     = item_category
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
	private String description;
			
	/**data for the column :
	
 --------- item_category.item_category_name --------- 
 schema        = null
 tableName     = item_category
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
	private String itemCategoryName;
			
	/**data for the column :
	
 --------- item_category.item_category_code --------- 
 schema        = null
 tableName     = item_category
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
	private String itemCategoryCode;
			
	/**data for the column :
	
 --------- item_category.created_time --------- 
 schema        = null
 tableName     = item_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- item_category.created_by --------- 
 schema        = null
 tableName     = item_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- item_category.deleted_time --------- 
 schema        = null
 tableName     = item_category
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- item_category.deleted_by --------- 
 schema        = null
 tableName     = item_category
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- item_category.modified_time --------- 
 schema        = null
 tableName     = item_category
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- item_category.modified_by --------- 
 schema        = null
 tableName     = item_category
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- item_category.deleted_status --------- 
 schema        = null
 tableName     = item_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String itemCategoryEDCName;
	
	private ItemCategory parentCategoryId;
	private CaseCategory caseCategoryId;
	private Integer isEdcItem;
	private Integer isSurgeryItem;
	private Integer isMaternityItem;
	private Integer isInpatientItem;
	private Integer isDentalItem;
	private Integer isOutpatientItem;
	
	private Integer isClientInvoiceItem;
	
	private String itemCategoryEDCCode;
	private String itemCategoryAlternateCode;
	private String itemCategoryAlternateCode2;
	
	
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** GroupBenefit
	data for the exported class :
	
 --------- group_benefit.item_category_id --------- 
 schema        = null
 tableName     = group_benefit
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
 this columns points to  = item_category.item_category_id

=========================================



	 */
	private Set <GroupBenefit> groupBenefits = new HashSet(0);
				/** Item
	data for the exported class :
	
 --------- item.item_category_id --------- 
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
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = item_category.item_category_id

=========================================



	 */
	private Set <Item> items = new HashSet(0);
				/** ItemMeasurementUnit
	data for the exported class :
	
 --------- item_measurement_unit.item_category_id --------- 
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
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = item_category.item_category_id

=========================================



	 */
	private Set <ItemMeasurementUnit> itemMeasurementUnits = new HashSet(0);
				/** MemberBenefit
	data for the exported class :
	
 --------- member_benefit.item_category_id --------- 
 schema        = null
 tableName     = member_benefit
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
 this columns points to  = item_category.item_category_id

=========================================



	 */
	private Set <MemberBenefit> memberBenefits = new HashSet(0);
				/** ProductBenefit
	data for the exported class :
	
 --------- product_benefit.item_category_id --------- 
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
 ordinal       = 9
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = item_category.item_category_id

=========================================



	 */
	private Set <ProductBenefit> productBenefits = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="item_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getItemCategoryId(){
		return itemCategoryId;
	}
	public void setItemCategoryId(java.lang.Integer value){
		itemCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="item_category_name")
	public java.lang.String getItemCategoryName(){
		return itemCategoryName;
	}
	public void setItemCategoryName(java.lang.String value){
		itemCategoryName = value;
	}
				@Column(name="item_category_code")
	public java.lang.String getItemCategoryCode(){
		return itemCategoryCode;
	}
	public void setItemCategoryCode(java.lang.String value){
		itemCategoryCode = value;
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

	
			/** GroupBenefit */
	@OneToMany(mappedBy="itemCategoryId")
	public Set<GroupBenefit> getGroupBenefits(){
		return this.groupBenefits;
	}

	/** GroupBenefit */
	public void setGroupBenefits(Set<GroupBenefit> obj){
		this.groupBenefits = obj;
	}
				/** Item */
	@OneToMany(mappedBy="itemCategoryId")
	public Set<Item> getItems(){
		return this.items;
	}

	/** Item */
	public void setItems(Set<Item> obj){
		this.items = obj;
	}
				/** ItemMeasurementUnit */
	@OneToMany(mappedBy="itemCategoryId")
	public Set<ItemMeasurementUnit> getItemMeasurementUnits(){
		return this.itemMeasurementUnits;
	}

	/** ItemMeasurementUnit */
	public void setItemMeasurementUnits(Set<ItemMeasurementUnit> obj){
		this.itemMeasurementUnits = obj;
	}
				/** MemberBenefit */
	@OneToMany(mappedBy="itemCategoryId")
	public Set<MemberBenefit> getMemberBenefits(){
		return this.memberBenefits;
	}

	/** MemberBenefit */
	public void setMemberBenefits(Set<MemberBenefit> obj){
		this.memberBenefits = obj;
	}
				/** ProductBenefit */
	@OneToMany(mappedBy="itemCategoryId")
	public Set<ProductBenefit> getProductBenefits(){
		return this.productBenefits;
	}

	/** ProductBenefit */
	public void setProductBenefits(Set<ProductBenefit> obj){
		this.productBenefits = obj;
	}
	@Column(name="item_category_edc_name")
	public String getItemCategoryEDCName() {
		return itemCategoryEDCName;
	}
	public void setItemCategoryEDCName(String itemCategoryEDCName) {
		this.itemCategoryEDCName = itemCategoryEDCName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_category_id")
	public ItemCategory getParentCategoryId() {
		return parentCategoryId;
	}
	public void setParentCategoryId(ItemCategory parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	@Column(name="is_edc_item")
	public Integer getIsEdcItem() {
		return isEdcItem;
	}
	public void setIsEdcItem(Integer isEdcItem) {
		this.isEdcItem = isEdcItem;
	}
	@Column(name="is_surgery_item")
	public Integer getIsSurgeryItem() {
		return isSurgeryItem;
	}
	public void setIsSurgeryItem(Integer isSurgeryItem) {
		this.isSurgeryItem = isSurgeryItem;
	}
	@Column(name="is_maternity_item")
	public Integer getIsMaternityItem() {
		return isMaternityItem;
	}
	public void setIsMaternityItem(Integer isMaternityItem) {
		this.isMaternityItem = isMaternityItem;
	}
	@Column(name="is_inpatient_item")
	public Integer getIsInpatientItem() {
		return isInpatientItem;
	}
	public void setIsInpatientItem(Integer isInpatientItem) {
		this.isInpatientItem = isInpatientItem;
	}
	@Column(name="is_dental_item")
	public Integer getIsDentalItem() {
		return isDentalItem;
	}
	public void setIsDentalItem(Integer isDentalItem) {
		this.isDentalItem = isDentalItem;
	}
	@Column(name="is_outpatient_item")
	public Integer getIsOutpatientItem() {
		return isOutpatientItem;
	}
	public void setIsOutpatientItem(Integer isOutpatientItem) {
		this.isOutpatientItem = isOutpatientItem;
	}
	@Column(name="item_category_edc_code")
	public String getItemCategoryEDCCode() {
		return itemCategoryEDCCode;
	}
	public void setItemCategoryEDCCode(String itemCategoryEDCCode) {
		this.itemCategoryEDCCode = itemCategoryEDCCode;
	}
	@Column(name="item_category_alternate_code")
	public String getItemCategoryAlternateCode() {
		return itemCategoryAlternateCode;
	}
	public void setItemCategoryAlternateCode(String itemCategoryAlternateCode) {
		this.itemCategoryAlternateCode = itemCategoryAlternateCode;
	}
	@Column(name="item_category_alternate_code_2")
	public String getItemCategoryAlternateCode2() {
		return itemCategoryAlternateCode2;
	}
	public void setItemCategoryAlternateCode2(String itemCategoryAlternateCode2) {
		this.itemCategoryAlternateCode2 = itemCategoryAlternateCode2;
	}
	@Column(name="is_client_invoice_item")
	public Integer getIsClientInvoiceItem() {
		return isClientInvoiceItem;
	}
	public void setIsClientInvoiceItem(Integer isClientInvoiceItem) {
		this.isClientInvoiceItem = isClientInvoiceItem;
	}
			
	
	//exported key end



}