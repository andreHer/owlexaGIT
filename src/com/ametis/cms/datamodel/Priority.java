
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
@Table(name="priority")
public class Priority implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- priority.priority_id --------- 
 schema        = null
 tableName     = priority
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
	private Integer priorityId;
			
	/**data for the column :
	
 --------- priority.priority_code --------- 
 schema        = null
 tableName     = priority
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
	private String priorityCode;
			
	/**data for the column :
	
 --------- priority.description --------- 
 schema        = null
 tableName     = priority
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
	
 --------- batch_claim.priority --------- 
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
 ordinal       = 12
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = priority.priority_id

=========================================



	 */
	private Set <BatchClaim> batchClaims = new HashSet(0);
			
	private Integer isDefault;
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="priority_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPriorityId(){
		return priorityId;
	}
	public void setPriorityId(java.lang.Integer value){
		priorityId = value;
	}
			// PK GETTER SETTER END

						@Column(name="priority_code")
	public java.lang.String getPriorityCode(){
		return priorityCode;
	}
	public void setPriorityCode(java.lang.String value){
		priorityCode = value;
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
	@OneToMany(mappedBy="priority")
	public Set<BatchClaim> getBatchClaims(){
		return this.batchClaims;
	}

	/** BatchClaim */
	public void setBatchClaims(Set<BatchClaim> obj){
		this.batchClaims = obj;
	}
	@Column (name="is_default")
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
			
	
	//exported key end



}