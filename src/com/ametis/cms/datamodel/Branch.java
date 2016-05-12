
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="branch")
public class Branch implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- branch.branch_id --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer branchId;
			
	/**data for the column :
	
 --------- branch.branch_name --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 200
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String branchName;
			
	/**data for the column :
	
 --------- branch.address --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private String address;
			
	/**data for the column :
	
 --------- branch.country --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String country;
			
	/**data for the column :
	
 --------- branch.city --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String city;
			
	/**data for the column :
	
 --------- branch.province --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String province;
			
	/**data for the column :
	
 --------- branch.status_id --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer statusId;
			
	/**data for the column :
	
 --------- branch.created_time --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	
 --------- branch.created_by --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- branch.deleted_time --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	
 --------- branch.deleted_by --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- branch.deleted_status --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- branch.modified_time --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- branch.modified_by --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- branch.branch_head --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String branchHead;
			
	/**data for the column :
	
 --------- branch.branch_finance_head --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String branchFinanceHead;
			
	/**data for the column :
	
 --------- branch.branch_actuary_head --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String branchActuaryHead;
			
	/**data for the column :
	
 --------- branch.branch_marketing_head --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String branchMarketingHead;
			
	/**data for the column :
	
 --------- branch.branch_claim_head --------- 
 schema        = null
 tableName     = branch
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String branchClaimHead;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Quotation
	data for the exported class :
	
 --------- quotation.branch_id --------- 
 schema        = null
 tableName     = quotation
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 35
 size          = 10
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = branch.branch_id

=========================================



	 */
	private Set <Quotation> quotations = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="branch_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getBranchId(){
		return branchId;
	}
	public void setBranchId(java.lang.Integer value){
		branchId = value;
	}
			// PK GETTER SETTER END

						@Column(name="branch_name")
	public java.lang.String getBranchName(){
		return branchName;
	}
	public void setBranchName(java.lang.String value){
		branchName = value;
	}
				@Column(name="address")
	public java.lang.String getAddress(){
		return address;
	}
	public void setAddress(java.lang.String value){
		address = value;
	}
				@Column(name="country")
	public java.lang.String getCountry(){
		return country;
	}
	public void setCountry(java.lang.String value){
		country = value;
	}
				@Column(name="city")
	public java.lang.String getCity(){
		return city;
	}
	public void setCity(java.lang.String value){
		city = value;
	}
				@Column(name="province")
	public java.lang.String getProvince(){
		return province;
	}
	public void setProvince(java.lang.String value){
		province = value;
	}
				@Column(name="status_id")
	public java.lang.Integer getStatusId(){
		return statusId;
	}
	public void setStatusId(java.lang.Integer value){
		statusId = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
				@Column(name="branch_head")
	public java.lang.String getBranchHead(){
		return branchHead;
	}
	public void setBranchHead(java.lang.String value){
		branchHead = value;
	}
				@Column(name="branch_finance_head")
	public java.lang.String getBranchFinanceHead(){
		return branchFinanceHead;
	}
	public void setBranchFinanceHead(java.lang.String value){
		branchFinanceHead = value;
	}
				@Column(name="branch_actuary_head")
	public java.lang.String getBranchActuaryHead(){
		return branchActuaryHead;
	}
	public void setBranchActuaryHead(java.lang.String value){
		branchActuaryHead = value;
	}
				@Column(name="branch_marketing_head")
	public java.lang.String getBranchMarketingHead(){
		return branchMarketingHead;
	}
	public void setBranchMarketingHead(java.lang.String value){
		branchMarketingHead = value;
	}
				@Column(name="branch_claim_head")
	public java.lang.String getBranchClaimHead(){
		return branchClaimHead;
	}
	public void setBranchClaimHead(java.lang.String value){
		branchClaimHead = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** Quotation */
	@OneToMany(mappedBy="branchId")
	public Set<Quotation> getQuotations(){
		return this.quotations;
	}

	/** Quotation */
	public void setQuotations(Set<Quotation> obj){
		this.quotations = obj;
	}
			
	//exported key end



}