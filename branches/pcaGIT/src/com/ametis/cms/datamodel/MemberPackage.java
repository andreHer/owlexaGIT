
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="member_package")
public class MemberPackage implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member_package.member_package_id --------- 
 schema        = null
 tableName     = member_package
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
	private Integer memberPackageId;
									
	/**data for the column :
	
 --------- member_package.register_date --------- 
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
 ordinal       = 4
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date registerDate;
			
	/**data for the column :
	
 --------- member_package.expire_date --------- 
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
 ordinal       = 5
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date expireDate;
			
	/**data for the column :
	
 --------- member_package.renewal_date --------- 
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
 ordinal       = 6
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date renewalDate;
						
	/**data for the column :
	
 --------- member_package.created_time --------- 
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
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_package.created_by --------- 
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
 ordinal       = 9
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- member_package.deleted_time --------- 
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
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member_package.deleted_by --------- 
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
 ordinal       = 11
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- member_package.modified_time --------- 
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
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member_package.modified_by --------- 
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
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member_package.deleted_status --------- 
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
 ordinal       = 14
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- member_package.resign_date --------- 
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
 ordinal       = 15
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date resignDate;
		
	// foreign affairs
	
			/** SubscriptionStatus
	data for the foreign class :
	
 --------- subscription_status.status_id --------- 
 schema        = null
 tableName     = subscription_status
 foreignCol    = member_package_status
 foreignTab    = member_package
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
	private SubscriptionStatus memberPackageStatus;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = member_package
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
	private Member memberId;
				/** InsurancePackage
	data for the foreign class :
	
 --------- insurance_package.package_id --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = package_id
 foreignTab    = member_package
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
	private InsurancePackage packageId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="member_package_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMemberPackageId(){
		return memberPackageId;
	}
	public void setMemberPackageId(java.lang.Integer value){
		memberPackageId = value;
	}
			// PK GETTER SETTER END

												@Column(name="register_date")
	public java.sql.Date getRegisterDate(){
		return registerDate;
	}
	public void setRegisterDate(java.sql.Date value){
		registerDate = value;
	}
				@Column(name="expire_date")
	public java.sql.Date getExpireDate(){
		return expireDate;
	}
	public void setExpireDate(java.sql.Date value){
		expireDate = value;
	}
				@Column(name="renewal_date")
	public java.sql.Date getRenewalDate(){
		return renewalDate;
	}
	public void setRenewalDate(java.sql.Date value){
		renewalDate = value;
	}
							@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
				@Column(name="resign_date")
	public java.sql.Date getResignDate(){
		return resignDate;
	}
	public void setResignDate(java.sql.Date value){
		resignDate = value;
	}
		
	// foreign affairs
	
			/** SubscriptionStatus */
	@ManyToOne
	@JoinColumn(name="member_package_status")
	public SubscriptionStatus getMemberPackageStatus(){
		return this.memberPackageStatus;
	}

	/** SubscriptionStatus */
	public void setMemberPackageStatus(SubscriptionStatus obj){
		this.memberPackageStatus = obj;
	}
				/** Member */
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
				/** InsurancePackage */
	@ManyToOne
	@JoinColumn(name="package_id")
	public InsurancePackage getPackageId(){
		return this.packageId;
	}

	/** InsurancePackage */
	public void setPackageId(InsurancePackage obj){
		this.packageId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}