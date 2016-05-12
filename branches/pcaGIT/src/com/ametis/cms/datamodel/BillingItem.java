
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="billing_item")
public class BillingItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int BILLING_OPEN = 1;
	public static final int BILLING_INVOICED = 2;
	public static final int BILLING_PAID = 3;
	//Fields
		
	/**data for the column :
	
 --------- billing_item.billing_item_id --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private Integer billingItemId;
			
	/**data for the column :
	
 --------- billing_item.invoice_id --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private Invoice invoiceId;
			
	/**data for the column :
	
 --------- billing_item.item_id --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Item itemId;
			
	/**data for the column :
	
 --------- billing_item.billing_value --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
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
	private Double billingValue;
			
	/**data for the column :
	
 --------- billing_item.billing_status --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer billingStatus;
									
	/**data for the column :
	
 --------- billing_item.claim_status --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer claimStatus;
			
	/**data for the column :
	
 --------- billing_item.member_name --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberName;
			
	/**data for the column :
	
 --------- billing_item.policy_number --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String policyNumber;
			
	/**data for the column :
	
 --------- billing_item.member_number --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberNumber;
			
	/**data for the column :
	
 --------- billing_item.claim_number --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String claimNumber;
			
	/**data for the column :
	
 --------- billing_item.created_time --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- billing_item.created_by --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- billing_item.modified_time --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- billing_item.modified_by --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- billing_item.deleted_time --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- billing_item.deleted_by --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- billing_item.deleted_status --------- 
 schema        = null
 tableName     = billing_item
 foreignCol    = 
 foreignTab    = 
 catalog       = mp-new
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
											
	// foreign affairs
	
			/** MemberImport
	data for the foreign class :
	
 --------- member_import.id --------- 
 schema        = null
 tableName     = member_import
 foreignCol    = member_import_id
 foreignTab    = billing_item
 catalog       = mp-new
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
	private MemberImport memberImportId;
				/** Claim
	data for the foreign class :
	
 --------- claim.claim_id --------- 
 schema        = null
 tableName     = claim
 foreignCol    = claim_id
 foreignTab    = billing_item
 catalog       = mp-new
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
	private Claim claimId;
				/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = billing_item
 catalog       = mp-new
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
	private Client clientId;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = billing_item
 catalog       = mp-new
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
	private Member memberId;
				/** Policy
	data for the foreign class :
	
 --------- policy.policy_id --------- 
 schema        = null
 tableName     = policy
 foreignCol    = policy_id
 foreignTab    = billing_item
 catalog       = mp-new
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
	private Policy policyId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="billing_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getBillingItemId(){
		return billingItemId;
	}
	public void setBillingItemId(java.lang.Integer value){
		billingItemId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="invoice_id")
	public Invoice getInvoiceId(){
		return invoiceId;
	}
	public void setInvoiceId(Invoice value){
		invoiceId = value;
	}
	
	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId(){
		return itemId;
	}
	public void setItemId(Item value){
		itemId = value;
	}
	@Column(name="billing_value")
	public java.lang.Double getBillingValue(){
		return billingValue;
	}
	public void setBillingValue(java.lang.Double value){
		billingValue = value;
	}
				@Column(name="billing_status")
	public java.lang.Integer getBillingStatus(){
		return billingStatus;
	}
	public void setBillingStatus(java.lang.Integer value){
		billingStatus = value;
	}
										@Column(name="claim_status")
	public java.lang.Integer getClaimStatus(){
		return claimStatus;
	}
	public void setClaimStatus(java.lang.Integer value){
		claimStatus = value;
	}
				@Column(name="member_name")
	public java.lang.String getMemberName(){
		return memberName;
	}
	public void setMemberName(java.lang.String value){
		memberName = value;
	}
				@Column(name="policy_number")
	public java.lang.String getPolicyNumber(){
		return policyNumber;
	}
	public void setPolicyNumber(java.lang.String value){
		policyNumber = value;
	}
				@Column(name="member_number")
	public java.lang.String getMemberNumber(){
		return memberNumber;
	}
	public void setMemberNumber(java.lang.String value){
		memberNumber = value;
	}
				@Column(name="claim_number")
	public java.lang.String getClaimNumber(){
		return claimNumber;
	}
	public void setClaimNumber(java.lang.String value){
		claimNumber = value;
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
	
			/** MemberImport */
	@ManyToOne
	@JoinColumn(name="member_import_id")
	public MemberImport getMemberImportId(){
		return this.memberImportId;
	}

	/** MemberImport */
	public void setMemberImportId(MemberImport obj){
		this.memberImportId = obj;
	}
				/** Claim */
	@ManyToOne
	@JoinColumn(name="claim_id")
	public Claim getClaimId(){
		return this.claimId;
	}

	/** Claim */
	public void setClaimId(Claim obj){
		this.claimId = obj;
	}
				/** Client */
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
				/** Member */
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
				/** Policy */
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return this.policyId;
	}

	/** Policy */
	public void setPolicyId(Policy obj){
		this.policyId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}