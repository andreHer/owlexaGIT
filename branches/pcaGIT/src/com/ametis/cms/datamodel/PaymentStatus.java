
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="payment_status")
public class PaymentStatus implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int PAYMENT_OPEN = 1;
	public static final int PAYMENT_PAID = 2;
	public static final int PAYMENT_APPROVED = 3;	
	public static final int PAYMENT_BAD = 4;
	public static final int PAYMENT_PENDING = 5; 
	public static final int PAYMENT_DISPOSITION = 6;
	public static final int PAYMENT_VOID = -1;
	public static final int PAYMENT_PARTIAL_PAID = 7;
	public static final int PAYMENT_PARTIAL_DISPOSITION = 8;
	
	public static final int PAYMENT_DRAFT = -2;

	//Fields
		
	/**data for the column :
	
 --------- payment_status.payment_status_id --------- 
 schema        = null
 tableName     = payment_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 2
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer paymentStatusId;
			
	/**data for the column :
	
 --------- payment_status.payment_status_name --------- 
 schema        = null
 tableName     = payment_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String paymentStatusName;
			
	/**data for the column :
	
 --------- payment_status.description --------- 
 schema        = null
 tableName     = payment_status
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End

	public PaymentStatus(){}
	public PaymentStatus(int paymentStatus){
		this.paymentStatusId = Integer.valueOf(paymentStatus);
	}
	// PK GETTER SETTER
			@Id
	@Column(name="payment_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPaymentStatusId(){
		return paymentStatusId;
	}
	public void setPaymentStatusId(java.lang.Integer value){
		paymentStatusId = value;
	}
			// PK GETTER SETTER END

						@Column(name="payment_status_name")
	public java.lang.String getPaymentStatusName(){
		return paymentStatusName;
	}
	public void setPaymentStatusName(java.lang.String value){
		paymentStatusName = value;
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

	
		
	//exported key end



}