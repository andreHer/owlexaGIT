
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="client_contract_item")
public class ClientContractItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int PER_MEMBER = 1;
	public static final int PER_CLAIM = 2;
	public static final int PER_MONTHLY_FIXED_FEE = 3;
	public static final int PER_QUARTER_FIXED_FEE = 4;
	public static final int PER_SEMESTER_FIXED_FEE = 5;
	public static final int PER_ANNUM_FIXED_FEE = 6;
	
	public static final int OTHER_LOSTCARD = 1;
	public static final int OTHER_REPRINT = 2;
	public static final int OTHER_CHANGEPLAN = 3;
	
	
	public static final int REIMBURSEMENT = 1;
	public static final int CASHLESS = 2;
	public static final int BOTH = 3;

	//Fields
		
	/**data for the column :
	
 --------- client_contract_item.client_contract_item_id --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer clientContractItemId;
									
	/**data for the column :
	
 --------- client_contract_item.item_price --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double itemPrice;
			
	/**data for the column :
	
 --------- client_contract_item.item_price_reference --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double itemPriceReference;
			
	/**data for the column :
	
 --------- client_contract_item.description --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- client_contract_item.status --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- client_contract_item.created_time --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- client_contract_item.created_by --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- client_contract_item.modified_time --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- client_contract_item.modified_by --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- client_contract_item.deleted_time --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- client_contract_item.deleted_by --------- 
 schema        = null
 tableName     = client_contract_item
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String deletedBy;
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** ClientContract
	data for the foreign class :
	
 --------- client_contract.client_contract_id --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = client_contract_id
 foreignTab    = client_contract_item
 catalog       = insura-lintas
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
	private ClientContract clientContractId;
	private Item itemId;
	private CaseCategory caseCategoryId;
	private Integer claimType;
	private Integer claimStatusProcess;
	private Integer contractUnitType;
	private Integer volumeStart;
	private Integer volumeEnd;
	private Double discountPercentage;
	private Double discountAmountPerQuantity;
	
	
	@Id
	@Column(name="client_contract_item_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getClientContractItemId(){
		return clientContractItemId;
	}
	public void setClientContractItemId(java.lang.Integer value){
		clientContractItemId = value;
	}
			// PK GETTER SETTER END

												@Column(name="item_price")
	public java.lang.Double getItemPrice(){
		return itemPrice;
	}
	public void setItemPrice(java.lang.Double value){
		itemPrice = value;
	}
				@Column(name="item_price_reference")
	public java.lang.Double getItemPriceReference(){
		return itemPriceReference;
	}
	public void setItemPriceReference(java.lang.Double value){
		itemPriceReference = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
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
	
			/** ClientContract */
	@ManyToOne
	@JoinColumn(name="client_contract_id")
	public ClientContract getClientContractId(){
		return this.clientContractId;
	}

	/** ClientContract */
	public void setClientContractId(ClientContract obj){
		this.clientContractId = obj;
	}
				/** Item */
	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId(){
		return this.itemId;
	}

	/** Item */
	public void setItemId(Item obj){
		this.itemId = obj;
	}
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId() {
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}
	@Column(name="claim_type")
	public Integer getClaimType() {
		return claimType;
	}
	public void setClaimType(Integer claimType) {
		this.claimType = claimType;
	}
	@Column(name="contract_unit_type")
	public Integer getContractUnitType() {
		return contractUnitType;
	}
	public void setContractUnitType(Integer contractUnitType) {
		this.contractUnitType = contractUnitType;
	}
	@Column(name="volume_start")
	public Integer getVolumeStart() {
		return volumeStart;
	}
	public void setVolumeStart(Integer volumeStart) {
		this.volumeStart = volumeStart;
	}
	@Column(name="volume_end")
	public Integer getVolumeEnd() {
		return volumeEnd;
	}
	public void setVolumeEnd(Integer volumeEnd) {
		this.volumeEnd = volumeEnd;
	}
	@Column(name="discount_percentage")
	public Double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	@Column(name="discount_amount_per_quantity")
	public Double getDiscountAmountPerQuantity() {
		return discountAmountPerQuantity;
	}
	public void setDiscountAmountPerQuantity(Double discountAmountPerQuantity) {
		this.discountAmountPerQuantity = discountAmountPerQuantity;
	}
	@Column(name="claim_status_process")
	public Integer getClaimStatusProcess() {
		return claimStatusProcess;
	}
	public void setClaimStatusProcess(Integer claimStatusProcess) {
		this.claimStatusProcess = claimStatusProcess;
	}
	
	


}