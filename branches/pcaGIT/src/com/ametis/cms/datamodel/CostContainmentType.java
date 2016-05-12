
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
@Table(name="cost_containment_type")
public class CostContainmentType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int CASE_REJECTION  = 1;
	public static final int CLAIM_REJECTION = 2;
	public static final int BENEFIT_CHECK = 3;
	public static final int MONITORING_CHECK = 4;
	public static final int CLAIM_ITEM_REJECTION = 5;

	//Fields
		
	/**data for the column :
	
 --------- cost_containment_type.cost_containment_type_id --------- 
 schema        = null
 tableName     = cost_containment_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = 
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 2
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer costContainmentTypeId;
			
	/**data for the column :
	
 --------- cost_containment_type.cost_containment_type_name --------- 
 schema        = null
 tableName     = cost_containment_type
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
	private String costContainmentTypeName;
			
	/**data for the column :
	
 --------- cost_containment_type.description --------- 
 schema        = null
 tableName     = cost_containment_type
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

	
			/** CostContainment
	data for the exported class :
	
 --------- cost_containment.cost_containment_type --------- 
 schema        = null
 tableName     = cost_containment
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 2
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = cost_containment_type.cost_containment_type_id

=========================================



	 */
	private Set <CostContainment> costContainments = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="cost_containment_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCostContainmentTypeId(){
		return costContainmentTypeId;
	}
	public void setCostContainmentTypeId(java.lang.Integer value){
		costContainmentTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="cost_containment_type_name")
	public java.lang.String getCostContainmentTypeName(){
		return costContainmentTypeName;
	}
	public void setCostContainmentTypeName(java.lang.String value){
		costContainmentTypeName = value;
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

	
			/** CostContainment */
	@OneToMany(mappedBy="costContainmentType")
	public Set<CostContainment> getCostContainments(){
		return this.costContainments;
	}

	/** CostContainment */
	public void setCostContainments(Set<CostContainment> obj){
		this.costContainments = obj;
	}
			
	//exported key end



}