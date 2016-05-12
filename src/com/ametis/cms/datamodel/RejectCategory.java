
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
@Table(name="reject_category")
public class RejectCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int REJECT_CLAIM = 1;
	public static final int REJECT_CASE = 2;
	
	public static final int MEMBER_ACTIVATION = 2;
	public static final int MEMBER_EXPIRED = 1;
	public static final int PRE_EXISTING_DIAGNOSIS = 7;
	public static final int DIAGNOSIS_EXCLUSION = 6;
	public static final int GRACE_PERIODE = 8;
	public static final int SUSPEND = 9;
	public static final int SUBJECT_NO_CLAIM = 10;
	//Fields
		
	/**data for the column :
	
 --------- reject_category.reject_category_id --------- 
 schema        = null
 tableName     = reject_category
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
	private Integer rejectCategoryId;
			
	/**data for the column :
	
 --------- reject_category.reject_category_name --------- 
 schema        = null
 tableName     = reject_category
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
	private String rejectCategoryName;
			
	/**data for the column :
	
 --------- reject_category.description --------- 
 schema        = null
 tableName     = reject_category
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

	
			/** RejectedClaim
	data for the exported class :
	
 --------- rejected_claim.rejection_category --------- 
 schema        = null
 tableName     = rejected_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 4
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = reject_category.reject_category_id

=========================================



	 */
	private Set <RejectedClaim> rejectedClaims = new HashSet(0);
	private Integer tipe;
	private Integer deletedStatus;
	private String rejectCategoryCode;
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="reject_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getRejectCategoryId(){
		return rejectCategoryId;
	}
	public void setRejectCategoryId(java.lang.Integer value){
		rejectCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="reject_category_name")
	public java.lang.String getRejectCategoryName(){
		return rejectCategoryName;
	}
	public void setRejectCategoryName(java.lang.String value){
		rejectCategoryName = value;
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

	
			/** RejectedClaim */
	@OneToMany(mappedBy="rejectionCategory")
	public Set<RejectedClaim> getRejectedClaims(){
		return this.rejectedClaims;
	}

	/** RejectedClaim */
	public void setRejectedClaims(Set<RejectedClaim> obj){
		this.rejectedClaims = obj;
	}
	@Column(name="tipe")
	public Integer getTipe() {
		return tipe;
	}
	public void setTipe(Integer tipe) {
		this.tipe = tipe;
	}
	@Column(name="reject_category_code")
	public String getRejectCategoryCode() {
		return rejectCategoryCode;
	}
	public void setRejectCategoryCode(String rejectCategoryCode) {
		this.rejectCategoryCode = rejectCategoryCode;
	}
	@Column(name="deleted_status")
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
			
	//exported key end



}