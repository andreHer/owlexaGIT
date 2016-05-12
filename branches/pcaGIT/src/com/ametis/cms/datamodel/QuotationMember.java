
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="quotation_member")
public class QuotationMember implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- quotation_member.quotation_member_id --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 15
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer quotationMemberId;
			
	/**data for the column :
	
 --------- quotation_member.quotation_id --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer quotationId;
			
	/**data for the column :
	
 --------- quotation_member.member_name --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String memberName;
			
	/**data for the column :
	
 --------- quotation_member.member_number --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String memberNumber;
			
	/**data for the column :
	
 --------- quotation_member.birthdate --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private java.sql.Date birthdate;
			
	/**data for the column :
	
 --------- quotation_member.sex --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 5
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String sex;
			
	/**data for the column :
	
 --------- quotation_member.telephone --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 15
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String telephone;
			
	/**data for the column :
	
 --------- quotation_member.handphone --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 15
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String handphone;
			
	/**data for the column :
	
 --------- quotation_member.address --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String address;
			
	/**data for the column :
	
 --------- quotation_member.email --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String email;
			
	/**data for the column :
	
 --------- quotation_member.marital_status --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer maritalStatus;
			
	/**data for the column :
	
 --------- quotation_member.bank_account --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankAccount;
			
	/**data for the column :
	
 --------- quotation_member.bank_account_name --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankAccountName;
			
	/**data for the column :
	
 --------- quotation_member.bank_name --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankName;
			
	/**data for the column :
	
 --------- quotation_member.parent_name --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String parentName;
			
	/**data for the column :
	
 --------- quotation_member.parent_number --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String parentNumber;
			
	/**data for the column :
	
 --------- quotation_member.created_time --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- quotation_member.created_by --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String createdBy;
			
	/**data for the column :
	
 --------- quotation_member.modified_time --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 19
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- quotation_member.modified_by --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- quotation_member.deleted_time --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 21
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- quotation_member.deleted_by --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- quotation_member.deleted_status --------- 
 schema        = null
 tableName     = quotation_member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 23
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="quotation_member_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getQuotationMemberId(){
		return quotationMemberId;
	}
	public void setQuotationMemberId(java.lang.Integer value){
		quotationMemberId = value;
	}
			// PK GETTER SETTER END

						@Column(name="quotation_id")
	public java.lang.Integer getQuotationId(){
		return quotationId;
	}
	public void setQuotationId(java.lang.Integer value){
		quotationId = value;
	}
				@Column(name="member_name")
	public java.lang.String getMemberName(){
		return memberName;
	}
	public void setMemberName(java.lang.String value){
		memberName = value;
	}
				@Column(name="member_number")
	public java.lang.String getMemberNumber(){
		return memberNumber;
	}
	public void setMemberNumber(java.lang.String value){
		memberNumber = value;
	}
				@Column(name="birthdate")
	public java.sql.Date getBirthdate(){
		return birthdate;
	}
	public void setBirthdate(java.sql.Date value){
		birthdate = value;
	}
				@Column(name="sex")
	public java.lang.String getSex(){
		return sex;
	}
	public void setSex(java.lang.String value){
		sex = value;
	}
				@Column(name="telephone")
	public java.lang.String getTelephone(){
		return telephone;
	}
	public void setTelephone(java.lang.String value){
		telephone = value;
	}
				@Column(name="handphone")
	public java.lang.String getHandphone(){
		return handphone;
	}
	public void setHandphone(java.lang.String value){
		handphone = value;
	}
				@Column(name="address")
	public java.lang.String getAddress(){
		return address;
	}
	public void setAddress(java.lang.String value){
		address = value;
	}
				@Column(name="email")
	public java.lang.String getEmail(){
		return email;
	}
	public void setEmail(java.lang.String value){
		email = value;
	}
				@Column(name="marital_status")
	public java.lang.Integer getMaritalStatus(){
		return maritalStatus;
	}
	public void setMaritalStatus(java.lang.Integer value){
		maritalStatus = value;
	}
				@Column(name="bank_account")
	public java.lang.String getBankAccount(){
		return bankAccount;
	}
	public void setBankAccount(java.lang.String value){
		bankAccount = value;
	}
				@Column(name="bank_account_name")
	public java.lang.String getBankAccountName(){
		return bankAccountName;
	}
	public void setBankAccountName(java.lang.String value){
		bankAccountName = value;
	}
				@Column(name="bank_name")
	public java.lang.String getBankName(){
		return bankName;
	}
	public void setBankName(java.lang.String value){
		bankName = value;
	}
				@Column(name="parent_name")
	public java.lang.String getParentName(){
		return parentName;
	}
	public void setParentName(java.lang.String value){
		parentName = value;
	}
				@Column(name="parent_number")
	public java.lang.String getParentNumber(){
		return parentNumber;
	}
	public void setParentNumber(java.lang.String value){
		parentNumber = value;
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
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}