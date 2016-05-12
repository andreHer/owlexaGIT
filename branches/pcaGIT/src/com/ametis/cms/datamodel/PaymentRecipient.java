
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
@Table(name="payment_recipient")
public class PaymentRecipient implements java.io.Serializable{
	public static final int MEMBER_GROUP = 1;
	public static final int MEMBER = 2;
	public static final int PROVIDER = 3;
	public static final int CLIENT = 4;
	public static final int BROKER = 5;
	
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- payment_recipient.payment_recipient_id --------- 
 schema        = null
 tableName     = payment_recipient
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
	private Integer paymentRecipientId;
			
	/**data for the column :
	
 --------- payment_recipient.payment_recipient_name --------- 
 schema        = null
 tableName     = payment_recipient
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
	private String paymentRecipientName;
			
	/**data for the column :
	
 --------- payment_recipient.description --------- 
 schema        = null
 tableName     = payment_recipient
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
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** BatchClaim
	data for the exported class :
	
 --------- batch_claim.payment_recipient --------- 
 schema        = null
 tableName     = batch_claim
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
 this columns points to  = payment_recipient.payment_recipient_id

=========================================



	 */
	private Set <BatchClaim> batchClaims = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="payment_recipient_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPaymentRecipientId(){
		return paymentRecipientId;
	}
	public void setPaymentRecipientId(java.lang.Integer value){
		paymentRecipientId = value;
	}
			// PK GETTER SETTER END

						@Column(name="payment_recipient_name")
	public java.lang.String getPaymentRecipientName(){
		return paymentRecipientName;
	}
	public void setPaymentRecipientName(java.lang.String value){
		paymentRecipientName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** BatchClaim */
	@OneToMany(mappedBy="paymentRecipient")
	public Set<BatchClaim> getBatchClaims(){
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj){
		this.batchClaims = obj;
	}
			
	//exported key end



}