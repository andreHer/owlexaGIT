
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="outstanding_claim_item")
public class OutstandingClaimItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- oustanding_claim_item.outstanding_claim_item_id --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long outstandingClaimItemId;
			
	/**data for the column :
	
 --------- oustanding_claim_item.item_code --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 25
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String itemCode;
			
	/**data for the column :
	
 --------- oustanding_claim_item.item_name --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String itemName;
			
	/**data for the column :
	
 --------- oustanding_claim_item.description --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	
 --------- oustanding_claim_item.claim_item_amount --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Double claimItemAmount;
			
	/**data for the column :
	
 --------- oustanding_claim_item.claim_item_charge --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Double claimItemCharge;
			
	/**data for the column :
	
 --------- oustanding_claim_item.claim_item_approved_value --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Double claimItemApprovedValue;
			
	/**data for the column :
	
 --------- oustanding_claim_item.created_time --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	
 --------- oustanding_claim_item.created_by --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	
 --------- oustanding_claim_item.outstanding_claim_id --------- 
 schema        = null
 tableName     = oustanding_claim_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 15
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private OutstandingClaim outstandingClaimId;
		
	private Item itemId;
	private ItemCategory itemCategoryId;
	private Double precalculatedApprovedValue;
	private Double precalculatedExcessValue;
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="outstanding_claim_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getOutstandingClaimItemId(){
		return outstandingClaimItemId;
	}
	public void setOutstandingClaimItemId(java.lang.Long value){
		outstandingClaimItemId = value;
	}
			// PK GETTER SETTER END

						@Column(name="item_code")
	public java.lang.String getItemCode(){
		return itemCode;
	}
	public void setItemCode(java.lang.String value){
		itemCode = value;
	}
				@Column(name="item_name")
	public java.lang.String getItemName(){
		return itemName;
	}
	public void setItemName(java.lang.String value){
		itemName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="claim_item_amount")
	public java.lang.Double getClaimItemAmount(){
		return claimItemAmount;
	}
	public void setClaimItemAmount(java.lang.Double value){
		claimItemAmount = value;
	}
				@Column(name="claim_item_charge")
	public java.lang.Double getClaimItemCharge(){
		return claimItemCharge;
	}
	public void setClaimItemCharge(java.lang.Double value){
		claimItemCharge = value;
	}
				@Column(name="claim_item_approved_value")
	public java.lang.Double getClaimItemApprovedValue(){
		return claimItemApprovedValue;
	}
	public void setClaimItemApprovedValue(java.lang.Double value){
		claimItemApprovedValue = value;
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
	@ManyToOne
	@JoinColumn(name="outstanding_claim_id")
	public OutstandingClaim getOutstandingClaimId(){
		return outstandingClaimId;
	}
	public void setOutstandingClaimId(OutstandingClaim value){
		outstandingClaimId = value;
	}
	
	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId() {
		return itemId;
	}
	public void setItemId(Item itemId) {
		this.itemId = itemId;
	}
	@Column(name="precalculated_approved_value")

	public Double getPrecalculatedApprovedValue() {
		return precalculatedApprovedValue;
	}
	public void setPrecalculatedApprovedValue(Double precalculatedApprovedValue) {
		this.precalculatedApprovedValue = precalculatedApprovedValue;
	}
	@Column(name="precalculated_excess_value")
	public Double getPrecalculatedExcessValue() {
		return precalculatedExcessValue;
	}
	public void setPrecalculatedExcessValue(Double precalculatedExcessValue) {
		this.precalculatedExcessValue = precalculatedExcessValue;
	}
	@ManyToOne
	@JoinColumn(name="item_category_id")
	public ItemCategory getItemCategoryId() {
		return itemCategoryId;
	}
	public void setItemCategoryId(ItemCategory itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}
	
	
	
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}