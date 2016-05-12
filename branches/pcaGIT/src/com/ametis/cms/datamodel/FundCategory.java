
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="fund_category")
public class FundCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int FUND_ADDITION = 1;
	public static final int EXCESS_PAYMENT = 2;
	public static final int OUTSTANDING_PAYMENT = 3;
	public static final int POLICY_PAYMENT = 4;
	public static final int PROVIDER_CAPITATION = 5;
	public static final int EXCESS_BUFFER_POOL = 6;

	//Fields
		
	/**data for the column :
	
 --------- fund_category.fund_category_id --------- 
 schema        = null
 tableName     = fund_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 5
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer fundCategoryId;
			
	/**data for the column :
	
 --------- fund_category.fund_category_name --------- 
 schema        = null
 tableName     = fund_category
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
	private String fundCategoryName;
			
	/**data for the column :
	
 --------- fund_category.fund_category_code --------- 
 schema        = null
 tableName     = fund_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 15
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String fundCategoryCode;
			
	/**data for the column :
	
 --------- fund_category.description --------- 
 schema        = null
 tableName     = fund_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="fund_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getFundCategoryId(){
		return fundCategoryId;
	}
	public void setFundCategoryId(java.lang.Integer value){
		fundCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="fund_category_name")
	public java.lang.String getFundCategoryName(){
		return fundCategoryName;
	}
	public void setFundCategoryName(java.lang.String value){
		fundCategoryName = value;
	}
				@Column(name="fund_category_code")
	public java.lang.String getFundCategoryCode(){
		return fundCategoryCode;
	}
	public void setFundCategoryCode(java.lang.String value){
		fundCategoryCode = value;
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