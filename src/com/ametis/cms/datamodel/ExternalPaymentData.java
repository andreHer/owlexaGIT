
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="external_payment_data")
public class ExternalPaymentData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- external_payment_data.id --------- 
 schema        = null
 tableName     = external_payment_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long id;
			
	/**data for the column :
	
 --------- external_payment_data.policy_holder --------- 
 schema        = null
 tableName     = external_payment_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String policyHolder;
			
	/**data for the column :
	
 --------- external_payment_data.cdv_number --------- 
 schema        = null
 tableName     = external_payment_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String cdvNumber;
			
	/**data for the column :
	
 --------- external_payment_data.claim_paid_date --------- 
 schema        = null
 tableName     = external_payment_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date claimPaidDate;
			
	/**data for the column :
	
 --------- external_payment_data.is_synchronized --------- 
 schema        = null
 tableName     = external_payment_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer isSynchronized;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getId(){
		return id;
	}
	public void setId(java.lang.Long value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="policy_holder")
	public java.lang.String getPolicyHolder(){
		return policyHolder;
	}
	public void setPolicyHolder(java.lang.String value){
		policyHolder = value;
	}
				@Column(name="cdv_number")
	public java.lang.String getCdvNumber(){
		return cdvNumber;
	}
	public void setCdvNumber(java.lang.String value){
		cdvNumber = value;
	}
				@Column(name="claim_paid_date")
	public java.sql.Date getClaimPaidDate(){
		return claimPaidDate;
	}
	public void setClaimPaidDate(java.sql.Date value){
		claimPaidDate = value;
	}
				@Column(name="is_synchronized")
	public java.lang.Integer getIsSynchronized(){
		return isSynchronized;
	}
	public void setIsSynchronized(java.lang.Integer value){
		isSynchronized = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}