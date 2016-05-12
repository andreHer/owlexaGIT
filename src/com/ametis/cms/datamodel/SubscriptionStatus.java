
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
@Table(name="subscription_status")
public class SubscriptionStatus implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int ACTIVE = 1;
	public static final int TERMINATED = 2;
	public static final int RESIGNED = 3;
	public static final int PENDING = -1;
	public static final int INACTIVE = 4;
	public static final int BLOCKED = -2;
	public static final int REGISTRATION_INITIALIZED = 5;
	public static final int PENDING_UPGRADE = -3;
	public static final int PENDING_RENEWAL = -4;
	public static final int PENDING_MUTATION = -5;
	public static final int UPGRADED = 6;
	public static final int EXTENDED_ACTIVE = 8;
	public static final int REVISION = 9;
	public static final int GRACE = 7;
	public static final int ADVANCED_RENEWAL = 10;
	public static final int EXPIRED = -6;
	
	/**
	 * BUG FIX Renewal 
	 */
	public static final int RENEWED = 11;
	
	
	

	//Fields
		
	/**data for the column :
	
 --------- subscription_status.status_id --------- 
 schema        = null
 tableName     = subscription_status
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
	private Integer statusId;
			
	/**data for the column :
	
 --------- subscription_status.status --------- 
 schema        = null
 tableName     = subscription_status
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
	private String status;
			
	/**data for the column :
	
 --------- subscription_status.description --------- 
 schema        = null
 tableName     = subscription_status
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

	
			/** MemberPackage
	data for the exported class :
	
 --------- member_package.member_package_status --------- 
 schema        = null
 tableName     = member_package
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
 this columns points to  = subscription_status.status_id

=========================================



	 */
	private Set <MemberPackage> memberPackages = new HashSet(0);
				/** MemberProduct
	data for the exported class :
	
 --------- member_product.member_product_status --------- 
 schema        = null
 tableName     = member_product
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
 this columns points to  = subscription_status.status_id

=========================================



	 */
	private Set <MemberProduct> memberProducts = new HashSet(0);
				/** Provider
	data for the exported class :
	
 --------- provider.status_id --------- 
 schema        = null
 tableName     = provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = subscription_status.status_id

=========================================



	 */
	private Set <Provider> providers = new HashSet(0);
			
	// -- exported key end

	// Fields End

	public SubscriptionStatus(Integer statusId){
		this.statusId = statusId;
	}
	public SubscriptionStatus(){
		
	}
	// PK GETTER SETTER
			@Id
	@Column(name="status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getStatusId(){
		return statusId;
	}
	public void setStatusId(java.lang.Integer value){
		statusId = value;
	}
			// PK GETTER SETTER END

						@Column(name="status")
	public java.lang.String getStatus(){
		return status;
	}
	public void setStatus(java.lang.String value){
		status = value;
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

	
			/** MemberPackage */
	@OneToMany(mappedBy="memberPackageStatus")
	public Set<MemberPackage> getMemberPackages(){
		return this.memberPackages;
	}

	/** MemberPackage */
	public void setMemberPackages(Set<MemberPackage> obj){
		this.memberPackages = obj;
	}
				/** MemberProduct */
	@OneToMany(mappedBy="memberProductStatus")
	public Set<MemberProduct> getMemberProducts(){
		return this.memberProducts;
	}

	/** MemberProduct */
	public void setMemberProducts(Set<MemberProduct> obj){
		this.memberProducts = obj;
	}
				/** Provider */
	@OneToMany(mappedBy="statusId")
	public Set<Provider> getProviders(){
		return this.providers;
	}

	/** Provider */
	public void setProviders(Set<Provider> obj){
		this.providers = obj;
	}
			
	//exported key end



}