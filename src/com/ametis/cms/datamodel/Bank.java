
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="bank")
public class Bank implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- bank.bank_id --------- 
 schema        = null
 tableName     = bank
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
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
	private Integer bankId;
			
	/**data for the column :
	
 --------- bank.bank_name --------- 
 schema        = null
 tableName     = bank
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
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
	private String bankName;
			
	/**data for the column :
	
 --------- bank.swift_code --------- 
 schema        = null
 tableName     = bank
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String swiftCode;
			
	/**data for the column :
	
 --------- bank.description --------- 
 schema        = null
 tableName     = bank
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- bank.bank_code --------- 
 schema        = null
 tableName     = bank
 foreignCol    = 
 foreignTab    = 
 catalog       = jasindo
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankCode;
	private String clearingCode;
        
        private String createdBy;
        private String modifiedBy;
        private String deletedBy;
        private Timestamp createdTime;
        private Timestamp modifiedTime;
        private Timestamp deletedTime;
        private Integer deletedStatus;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="bank_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	public java.lang.Integer getBankId(){
		return bankId;
	}
	public void setBankId(java.lang.Integer value){
		bankId = value;
	}
			// PK GETTER SETTER END

						@Column(name="bank_name")
	public java.lang.String getBankName(){
		return bankName;
	}
	public void setBankName(java.lang.String value){
		bankName = value;
	}
				@Column(name="swift_code")
	public java.lang.String getSwiftCode(){
		return swiftCode;
	}
	public void setSwiftCode(java.lang.String value){
		swiftCode = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="bank_code")
	public java.lang.String getBankCode(){
		return bankCode;
	}
	public void setBankCode(java.lang.String value){
		bankCode = value;
	}
		
        
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end

        @Column(name="created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name="created_time")
    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name="deleted_by")
    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
    

    @Column(name="deleted_status")
    public Integer getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(Integer deletedStatus) {
        this.deletedStatus = deletedStatus;
    }

    @Column(name="deleted_time")
    public Timestamp getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Timestamp deletedTime) {
        this.deletedTime = deletedTime;
    }

    @Column(name="modified_by")
    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(name="modified_time")
    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
    @Column(name="clearing_code")
	public String getClearingCode() {
		return clearingCode;
	}
	public void setClearingCode(String clearingCode) {
		this.clearingCode = clearingCode;
	}



}