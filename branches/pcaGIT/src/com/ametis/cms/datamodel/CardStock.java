
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="card_stock")
public class CardStock implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- card_stock.id --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long id;
			
	/**data for the column :
	
 --------- card_stock.card_stock_batch_number --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String cardStockBatchNumber;
			
	/**data for the column :
	
 --------- card_stock.batch_date --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private java.sql.Date batchDate;
			
	/**data for the column :
	
 --------- card_stock.initial_stock --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer initialStock;
			
	/**data for the column :
	
 --------- card_stock.used_stock --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer additionalStock;
			
	/**data for the column :
	
 --------- card_stock.remaining_stock --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer remainingStock;
			
	/**data for the column :
	
 --------- card_stock.status --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- card_stock.created_time --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- card_stock.created_by --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- card_stock.modified_time --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- card_stock.modified_by --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- card_stock.deleted_time --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- card_stock.deleted_by --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- card_stock.deleted_status --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- card_stock.client_id --------- 
 schema        = null
 tableName     = card_stock
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Client clientId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Long getId(){
		return id;
	}
	public void setId(java.lang.Long value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="card_stock_batch_number")
	public java.lang.String getCardStockBatchNumber(){
		return cardStockBatchNumber;
	}
	public void setCardStockBatchNumber(java.lang.String value){
		cardStockBatchNumber = value;
	}
				@Column(name="batch_date")
	public java.sql.Date getBatchDate(){
		return batchDate;
	}
	public void setBatchDate(java.sql.Date value){
		batchDate = value;
	}
				@Column(name="initial_stock")
	public java.lang.Integer getInitialStock(){
		return initialStock;
	}
	public void setInitialStock(java.lang.Integer value){
		initialStock = value;
	}
				@Column(name="additional_stock")
	public java.lang.Integer getAdditionalStock(){
		return additionalStock;
	}
	public void setAdditionalStock(java.lang.Integer value){
		additionalStock = value;
	}
				@Column(name="remaining_stock")
	public java.lang.Integer getRemainingStock(){
		return remainingStock;
	}
	public void setRemainingStock(java.lang.Integer value){
		remainingStock = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
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
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return clientId;
	}
	public void setClientId(Client value){
		clientId = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}