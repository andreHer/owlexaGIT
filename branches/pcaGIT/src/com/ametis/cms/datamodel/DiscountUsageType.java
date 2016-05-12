
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
@Table(name="discount_usage_type")
public class DiscountUsageType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- discount_usage_type.discount_usage_type_id --------- 
 schema        = null
 tableName     = discount_usage_type
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
	private Integer discountUsageTypeId;
			
	/**data for the column :
	
 --------- discount_usage_type.discount_usage_type_name --------- 
 schema        = null
 tableName     = discount_usage_type
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
	private String discountUsageTypeName;
			
	/**data for the column :
	
 --------- discount_usage_type.description --------- 
 schema        = null
 tableName     = discount_usage_type
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

	
			/** ProductBenefit
	data for the exported class :
	
 --------- product_benefit.discount_usage_type --------- 
 schema        = null
 tableName     = product_benefit
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = discount_usage_type.discount_usage_type_id

=========================================



	 */
	private Set <ProductBenefit> productBenefits = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="discount_usage_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getDiscountUsageTypeId(){
		return discountUsageTypeId;
	}
	public void setDiscountUsageTypeId(java.lang.Integer value){
		discountUsageTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="discount_usage_type_name")
	public java.lang.String getDiscountUsageTypeName(){
		return discountUsageTypeName;
	}
	public void setDiscountUsageTypeName(java.lang.String value){
		discountUsageTypeName = value;
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

	
			/** ProductBenefit */
	@OneToMany(mappedBy="discountUsageType")
	public Set<ProductBenefit> getProductBenefits(){
		return this.productBenefits;
	}

	/** ProductBenefit */
	public void setProductBenefits(Set<ProductBenefit> obj){
		this.productBenefits = obj;
	}
			
	//exported key end



}