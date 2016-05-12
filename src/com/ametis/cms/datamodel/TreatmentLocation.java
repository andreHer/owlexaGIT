
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
@Table(name="treatment_location")
public class TreatmentLocation implements java.io.Serializable{
	public static final int DOMESTIC = 1;
	public static final int OVERSEAS = 2;
	public static final int BOTH = 3;
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- treatment_location.location_id --------- 
 schema        = null
 tableName     = treatment_location
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
	private Integer locationId;
			
	/**data for the column :
	
 --------- treatment_location.location --------- 
 schema        = null
 tableName     = treatment_location
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
	private String location;
			
	/**data for the column :
	
 --------- treatment_location.description --------- 
 schema        = null
 tableName     = treatment_location
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
	private Integer isDefault;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** ProductBenefit
	data for the exported class :
	
 --------- product_benefit.treatment_location --------- 
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
 ordinal       = 12
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = treatment_location.location_id

=========================================



	 */
	private Set <ProductBenefit> productBenefits = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="location_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getLocationId(){
		return locationId;
	}
	public void setLocationId(java.lang.Integer value){
		locationId = value;
	}
			// PK GETTER SETTER END

						@Column(name="location")
	public java.lang.String getLocation(){
		return location;
	}
	public void setLocation(java.lang.String value){
		location = value;
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
	@OneToMany(mappedBy="treatmentLocation")
	public Set<ProductBenefit> getProductBenefits(){
		return this.productBenefits;
	}

	/** ProductBenefit */
	public void setProductBenefits(Set<ProductBenefit> obj){
		this.productBenefits = obj;
	}
	@Column(name="is_default")
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
			
	
	//exported key end



}