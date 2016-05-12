
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="outstanding")
public class Outstanding implements java.io.Serializable{
	
	public static final int OUTSTANDING_OPEN = 0;
	public static final int OUTSTANDING_FINAL = 1;
	public static final int OUTSTANDING_CLOSED = 2;
	
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- outstanding.outstanding_id --------- 
 schema        = null
 tableName     = outstanding
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
	private Integer outstandingId;
			
	/**data for the column :
	
 --------- outstanding.outstanding_value --------- 
 schema        = null
 tableName     = outstanding
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double outstandingValue;
									
	/**data for the column :
	
 --------- outstanding.outstanding_status --------- 
 schema        = null
 tableName     = outstanding
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private PaymentStatus outstandingStatus;
			
	/**data for the column :
	
 --------- outstanding.outstanding_time --------- 
 schema        = null
 tableName     = outstanding
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
	private java.sql.Timestamp outstandingTime;
			
	/**data for the column :
	
 --------- outstanding.created_time --------- 
 schema        = null
 tableName     = outstanding
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
	
 --------- outstanding.created_by --------- 
 schema        = null
 tableName     = outstanding
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
	
 --------- outstanding.deleted_time --------- 
 schema        = null
 tableName     = outstanding
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
	
 --------- outstanding.deleted_by --------- 
 schema        = null
 tableName     = outstanding
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
	
 --------- outstanding.modified_time --------- 
 schema        = null
 tableName     = outstanding
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
	
 --------- outstanding.modified_by --------- 
 schema        = null
 tableName     = outstanding
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
	
 --------- outstanding.deleted_status --------- 
 schema        = null
 tableName     = outstanding
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- outstanding.outstanding_excess_value --------- 
 schema        = null
 tableName     = outstanding
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double outstandingExcessValue;
		
	// foreign affairs
	
			/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = outstanding
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
	private Client clientId;
				/** BatchClaim
	data for the foreign class :
	
 --------- batch_claim.batch_claim_id --------- 
 schema        = null
 tableName     = batch_claim
 foreignCol    = batch_claim_id
 foreignTab    = outstanding
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
	private BatchClaim batchClaimId;
			
	// -- foreign affairs end

	// -- exported key

	
			/** Payment
	data for the exported class :
	
 --------- payment.outstanding_id --------- 
 schema        = null
 tableName     = payment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = outstanding.outstanding_id

=========================================



	 */
	private Set <Payment> payments = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="outstanding_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getOutstandingId(){
		return outstandingId;
	}
	public void setOutstandingId(java.lang.Integer value){
		outstandingId = value;
	}
			// PK GETTER SETTER END

						@Column(name="outstanding_value")
	public java.lang.Double getOutstandingValue(){
		return outstandingValue;
	}
	public void setOutstandingValue(java.lang.Double value){
		outstandingValue = value;
	}
	@ManyToOne
	@JoinColumn(name="outstanding_status")
	public PaymentStatus getOutstandingStatus(){
		return outstandingStatus;
	}
	public void setOutstandingStatus(PaymentStatus value){
		outstandingStatus = value;
	}
				@Column(name="outstanding_time")
	public java.sql.Timestamp getOutstandingTime(){
		return outstandingTime;
	}
	public void setOutstandingTime(java.sql.Timestamp value){
		outstandingTime = value;
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
				@Column(name="outstanding_excess_value")
	public java.lang.Double getOutstandingExcessValue(){
		return outstandingExcessValue;
	}
	public void setOutstandingExcessValue(java.lang.Double value){
		outstandingExcessValue = value;
	}
		
	// foreign affairs
	
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
				/** BatchClaim */
	@ManyToOne
	@JoinColumn(name="batch_claim_id")
	public BatchClaim getBatchClaimId(){
		return this.batchClaimId;
	}

	/** BatchClaim */
	public void setBatchClaimId(BatchClaim obj){
		this.batchClaimId = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** Payment */
	@OneToMany(mappedBy="outstandingId")
	public Set<Payment> getPayments(){
		return this.payments;
	}

	/** Payment */
	public void setPayments(Set<Payment> obj){
		this.payments = obj;
	}
			
	//exported key end



}