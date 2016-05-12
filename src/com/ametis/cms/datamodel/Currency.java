
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="currency")
public class Currency implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- currency.currency_id --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 5
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer currencyId;
			
	/**data for the column :
	
 --------- currency.currency_code --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 10
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private Integer isDefault;
	private String currencyCode;
			
	/**data for the column :
	
 --------- currency.currency_name --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 20
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String currencyName;
			
	/**data for the column :
	
 --------- currency.description --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- currency.created_time --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- currency.created_by --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- currency.updated_time --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp updatedTime;
			
	/**data for the column :
	
 --------- currency.updated_by --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String updatedBy;
			
	/**data for the column :
	
 --------- currency.deleted_by --------- 
 schema        = null
 tableName     = currency
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- currency.deleted_time --------- 
 schema        = null
 tableName     = currency
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
	
 --------- currency.deleted_status --------- 
 schema        = null
 tableName     = currency
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer deletedStatus;
	private String currencyEdcCode;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="currency_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCurrencyId(){
		return currencyId;
	}
	public void setCurrencyId(java.lang.Integer value){
		currencyId = value;
	}
			// PK GETTER SETTER END

						@Column(name="currency_code")
	public java.lang.String getCurrencyCode(){
		return currencyCode;
	}
	public void setCurrencyCode(java.lang.String value){
		currencyCode = value;
	}
				@Column(name="currency_name")
	public java.lang.String getCurrencyName(){
		return currencyName;
	}
	public void setCurrencyName(java.lang.String value){
		currencyName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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
				@Column(name="updated_time")
	public java.sql.Timestamp getUpdatedTime(){
		return updatedTime;
	}
	public void setUpdatedTime(java.sql.Timestamp value){
		updatedTime = value;
	}
				@Column(name="updated_by")
	public java.lang.String getUpdatedBy(){
		return updatedBy;
	}
	public void setUpdatedBy(java.lang.String value){
		updatedBy = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
	@Column (name="is_default")
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	@Column(name="currency_edc_code")
	public String getCurrencyEdcCode() {
		return currencyEdcCode;
	}
	public void setCurrencyEdcCode(String currencyEdcCode) {
		this.currencyEdcCode = currencyEdcCode;
	}
		
	



}