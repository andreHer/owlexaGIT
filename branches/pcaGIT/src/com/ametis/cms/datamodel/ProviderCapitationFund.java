
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_capitation_fund")
public class ProviderCapitationFund implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_capitation_fund.provider_capitation_fund_id --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer providerCapitationFundId;
			
	/**data for the column :
	
 --------- provider_capitation_fund.provider_id --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer providerId;
			
	/**data for the column :
	
 --------- provider_capitation_fund.client_id --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer clientId;
			
	/**data for the column :
	
 --------- provider_capitation_fund.current_capitation_fund --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double currentCapitationFund;
			
	/**data for the column :
	
 --------- provider_capitation_fund.total_capitation_fund --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalCapitationFund;
			
	/**data for the column :
	
 --------- provider_capitation_fund.capitation_fund_usage --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double capitationFundUsage;
			
	/**data for the column :
	
 --------- provider_capitation_fund.last_addition_date --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date lastAdditionDate;
			
	/**data for the column :
	
 --------- provider_capitation_fund.last_usage_date --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date lastUsageDate;
			
	/**data for the column :
	
 --------- provider_capitation_fund.created_time --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- provider_capitation_fund.created_by --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- provider_capitation_fund.modified_time --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_capitation_fund.modified_by --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- provider_capitation_fund.deleted_time --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- provider_capitation_fund.deleted_by --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- provider_capitation_fund.deleted_status --------- 
 schema        = null
 tableName     = provider_capitation_fund
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
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
	@Column(name="provider_capitation_fund_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getProviderCapitationFundId(){
		return providerCapitationFundId;
	}
	public void setProviderCapitationFundId(java.lang.Integer value){
		providerCapitationFundId = value;
	}
			// PK GETTER SETTER END

						@Column(name="provider_id")
	public java.lang.Integer getProviderId(){
		return providerId;
	}
	public void setProviderId(java.lang.Integer value){
		providerId = value;
	}
				@Column(name="client_id")
	public java.lang.Integer getClientId(){
		return clientId;
	}
	public void setClientId(java.lang.Integer value){
		clientId = value;
	}
				@Column(name="current_capitation_fund")
	public java.lang.Double getCurrentCapitationFund(){
		return currentCapitationFund;
	}
	public void setCurrentCapitationFund(java.lang.Double value){
		currentCapitationFund = value;
	}
				@Column(name="total_capitation_fund")
	public java.lang.Double getTotalCapitationFund(){
		return totalCapitationFund;
	}
	public void setTotalCapitationFund(java.lang.Double value){
		totalCapitationFund = value;
	}
				@Column(name="capitation_fund_usage")
	public java.lang.Double getCapitationFundUsage(){
		return capitationFundUsage;
	}
	public void setCapitationFundUsage(java.lang.Double value){
		capitationFundUsage = value;
	}
				@Column(name="last_addition_date")
	public java.sql.Date getLastAdditionDate(){
		return lastAdditionDate;
	}
	public void setLastAdditionDate(java.sql.Date value){
		lastAdditionDate = value;
	}
				@Column(name="last_usage_date")
	public java.sql.Date getLastUsageDate(){
		return lastUsageDate;
	}
	public void setLastUsageDate(java.sql.Date value){
		lastUsageDate = value;
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