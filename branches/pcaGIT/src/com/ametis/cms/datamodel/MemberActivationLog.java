
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="member_activation_log")
public class MemberActivationLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member_activation_log.member_activation_log_id --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private Long memberActivationLogId;
			
	/**data for the column :
	
 --------- member_activation_log.member_id --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private Integer memberId;
			
	/**data for the column :
	
 --------- member_activation_log.member_number --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberNumber;
			
	/**data for the column :
	
 --------- member_activation_log.member_name --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberName;
			
	/**data for the column :
	
 --------- member_activation_log.product_code --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String productCode;
			
	/**data for the column :
	
 --------- member_activation_log.product_id --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer productId;
			
	/**data for the column :
	
 --------- member_activation_log.activation_time --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private java.sql.Timestamp activationTime;
			
	/**data for the column :
	
 --------- member_activation_log.total_benefit_populated --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private Integer totalBenefitPopulated;
			
	/**data for the column :
	
 --------- member_activation_log.total_clausul_populated --------- 
 schema        = null
 tableName     = member_activation_log
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalClausulPopulated;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="member_activation_log_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getMemberActivationLogId(){
		return memberActivationLogId;
	}
	public void setMemberActivationLogId(java.lang.Long value){
		memberActivationLogId = value;
	}
			// PK GETTER SETTER END

						@Column(name="member_id")
	public java.lang.Integer getMemberId(){
		return memberId;
	}
	public void setMemberId(java.lang.Integer value){
		memberId = value;
	}
				@Column(name="member_number")
	public java.lang.String getMemberNumber(){
		return memberNumber;
	}
	public void setMemberNumber(java.lang.String value){
		memberNumber = value;
	}
				@Column(name="member_name")
	public java.lang.String getMemberName(){
		return memberName;
	}
	public void setMemberName(java.lang.String value){
		memberName = value;
	}
				@Column(name="product_code")
	public java.lang.String getProductCode(){
		return productCode;
	}
	public void setProductCode(java.lang.String value){
		productCode = value;
	}
				@Column(name="product_id")
	public java.lang.Integer getProductId(){
		return productId;
	}
	public void setProductId(java.lang.Integer value){
		productId = value;
	}
				@Column(name="activation_time")
	public java.sql.Timestamp getActivationTime(){
		return activationTime;
	}
	public void setActivationTime(java.sql.Timestamp value){
		activationTime = value;
	}
				@Column(name="total_benefit_populated")
	public java.lang.Integer getTotalBenefitPopulated(){
		return totalBenefitPopulated;
	}
	public void setTotalBenefitPopulated(java.lang.Integer value){
		totalBenefitPopulated = value;
	}
				@Column(name="total_clausul_populated")
	public java.lang.Integer getTotalClausulPopulated(){
		return totalClausulPopulated;
	}
	public void setTotalClausulPopulated(java.lang.Integer value){
		totalClausulPopulated = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}