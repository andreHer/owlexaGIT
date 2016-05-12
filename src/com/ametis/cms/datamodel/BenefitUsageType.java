
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
@Table(name="benefit_usage_type")
public class BenefitUsageType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int OCCURANCE = 1;
	public static final int PERDISABILITY = 2;
	public static final int ANNUAL = 3;
	
	
	
	//Fields
		
	/**data for the column :
	
 --------- benefit_usage_type.benefit_usage_type_id --------- 
 schema        = null
 tableName     = benefit_usage_type
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
	private Integer benefitUsageTypeId;
			
	/**data for the column :
	
 --------- benefit_usage_type.benefit_usage --------- 
 schema        = null
 tableName     = benefit_usage_type
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
	private String benefitUsage;
			
	/**data for the column :
	
 --------- benefit_usage_type.description --------- 
 schema        = null
 tableName     = benefit_usage_type
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
	
 --------- product_benefit.benefit_usage_type --------- 
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
 ordinal       = 6
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = benefit_usage_type.benefit_usage_type_id

=========================================



	 */
	@SuppressWarnings("unchecked")
	private Set <ProductBenefit> productBenefits = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="benefit_usage_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getBenefitUsageTypeId(){
		return benefitUsageTypeId;
	}
	public void setBenefitUsageTypeId(java.lang.Integer value){
		benefitUsageTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="benefit_usage")
	public java.lang.String getBenefitUsage(){
		return benefitUsage;
	}
	public void setBenefitUsage(java.lang.String value){
		benefitUsage = value;
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
	@OneToMany(mappedBy="benefitUsageType")
	public Set<ProductBenefit> getProductBenefits(){
		return this.productBenefits;
	}

	/** ProductBenefit */
	public void setProductBenefits(Set<ProductBenefit> obj){
		this.productBenefits = obj;
	}
			
	//exported key end



}