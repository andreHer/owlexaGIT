
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="payment_mode")
public class PaymentMode implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- payment_mode.payment_mode_id --------- 
 schema        = null
 tableName     = payment_mode
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer paymentModeId;
			
	/**data for the column :
	
 --------- payment_mode.description --------- 
 schema        = null
 tableName     = payment_mode
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- payment_mode.payment_mode_name --------- 
 schema        = null
 tableName     = payment_mode
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String paymentModeName;
			
	/**data for the column :
	
 --------- payment_mode.is_default --------- 
 schema        = null
 tableName     = payment_mode
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isDefault;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="payment_mode_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPaymentModeId(){
		return paymentModeId;
	}
	public void setPaymentModeId(java.lang.Integer value){
		paymentModeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="payment_mode_name")
	public java.lang.String getPaymentModeName(){
		return paymentModeName;
	}
	public void setPaymentModeName(java.lang.String value){
		paymentModeName = value;
	}
				@Column(name="is_default")
	public java.lang.Integer getIsDefault(){
		return isDefault;
	}
	public void setIsDefault(java.lang.Integer value){
		isDefault = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}