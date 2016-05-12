
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
@Table(name="pending_category")
public class PendingCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int PENDING_DOCUMENT = 1;
	public static final int PENDING_INVESTIGATION = 2;

	//Fields
		
	/**data for the column :
	
 --------- pending_category.pending_category_id --------- 
 schema        = null
 tableName     = pending_category
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
	private Integer pendingCategoryId;
			
	/**data for the column :
	
 --------- pending_category.pending_category_name --------- 
 schema        = null
 tableName     = pending_category
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String pendingCategoryName;
			
	/**data for the column :
	
 --------- pending_category.description --------- 
 schema        = null
 tableName     = pending_category
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

	
			/** PendingClaim
	data for the exported class :
	
 --------- pending_claim.pending_category --------- 
 schema        = null
 tableName     = pending_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 2
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = pending_category.pending_category_id

=========================================



	 */
	private Set <PendingClaim> pendingClaims = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="pending_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPendingCategoryId(){
		return pendingCategoryId;
	}
	public void setPendingCategoryId(java.lang.Integer value){
		pendingCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="pending_category_name")
	public java.lang.String getPendingCategoryName(){
		return pendingCategoryName;
	}
	public void setPendingCategoryName(java.lang.String value){
		pendingCategoryName = value;
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

	
			/** PendingClaim */
	@OneToMany(mappedBy="pendingCategory")
	public Set<PendingClaim> getPendingClaims(){
		return this.pendingClaims;
	}

	/** PendingClaim */
	public void setPendingClaims(Set<PendingClaim> obj){
		this.pendingClaims = obj;
	}
			
	//exported key end



}