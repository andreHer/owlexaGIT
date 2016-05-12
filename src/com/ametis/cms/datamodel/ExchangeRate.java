
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="exchange_rate")
public class ExchangeRate implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- exchange_rate.exchange_rate_id --------- 
 schema        = null
 tableName     = exchange_rate
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer exchangeRateId;
			
	/**data for the column :
	
 --------- exchange_rate.currency_exchange_name --------- 
 schema        = null
 tableName     = exchange_rate
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String currencyExchangeName;
			
	/**data for the column :
	
 --------- exchange_rate.exchange_rate_date --------- 
 schema        = null
 tableName     = exchange_rate
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date exchangeRateDate;
			
	/**data for the column :
	
 --------- exchange_rate.first_currency_id --------- 
 schema        = null
 tableName     = exchange_rate
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Currency firstCurrencyId;
			
	/**data for the column :
	
 --------- exchange_rate.second_currency_id --------- 
 schema        = null
 tableName     = exchange_rate
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Currency secondCurrencyId;
			
	/**data for the column :
	
 --------- exchange_rate.rate_first_to_second --------- 
 schema        = null
 tableName     = exchange_rate
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
	private Double rateFirstToSecond;
			
	/**data for the column :
	
 --------- exchange_rate.rate_second_to_first --------- 
 schema        = null
 tableName     = exchange_rate
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double rateSecondToFirst;
			
	/**data for the column :
	
 --------- exchange_rate.description --------- 
 schema        = null
 tableName     = exchange_rate
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- exchange_rate.created_time --------- 
 schema        = null
 tableName     = exchange_rate
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
	
 --------- exchange_rate.created_by --------- 
 schema        = null
 tableName     = exchange_rate
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
	
 --------- exchange_rate.modified_time --------- 
 schema        = null
 tableName     = exchange_rate
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
	
 --------- exchange_rate.modified_by --------- 
 schema        = null
 tableName     = exchange_rate
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
	
 --------- exchange_rate.deleted_time --------- 
 schema        = null
 tableName     = exchange_rate
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
	
 --------- exchange_rate.deleted_by --------- 
 schema        = null
 tableName     = exchange_rate
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
	
 --------- exchange_rate.deleted_status --------- 
 schema        = null
 tableName     = exchange_rate
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
	@Column(name="exchange_rate_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getExchangeRateId(){
		return exchangeRateId;
	}
	public void setExchangeRateId(java.lang.Integer value){
		exchangeRateId = value;
	}
			// PK GETTER SETTER END

						@Column(name="currency_exchange_name")
	public java.lang.String getCurrencyExchangeName(){
		return currencyExchangeName;
	}
	public void setCurrencyExchangeName(java.lang.String value){
		currencyExchangeName = value;
	}
				@Column(name="exchange_rate_date")
	public java.sql.Date getExchangeRateDate(){
		return exchangeRateDate;
	}
	public void setExchangeRateDate(java.sql.Date value){
		exchangeRateDate = value;
	}
	@ManyToOne
	@JoinColumn(name="first_currency_id")
	public Currency getFirstCurrencyId(){
		return firstCurrencyId;
	}
	public void setFirstCurrencyId(Currency value){
		firstCurrencyId = value;
	}
	@ManyToOne
	@JoinColumn(name="second_currency_id")
	public Currency getSecondCurrencyId(){
		return secondCurrencyId;
	}
	public void setSecondCurrencyId(Currency value){
		secondCurrencyId = value;
	}
				@Column(name="rate_first_to_second")
	public java.lang.Double getRateFirstToSecond(){
		return rateFirstToSecond;
	}
	public void setRateFirstToSecond(java.lang.Double value){
		rateFirstToSecond = value;
	}
				@Column(name="rate_second_to_first")
	public java.lang.Double getRateSecondToFirst(){
		return rateSecondToFirst;
	}
	public void setRateSecondToFirst(java.lang.Double value){
		rateSecondToFirst = value;
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