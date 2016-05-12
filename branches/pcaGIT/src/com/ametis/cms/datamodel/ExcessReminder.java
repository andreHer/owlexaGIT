
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
@Table(name="excess_reminder")
public class ExcessReminder implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- excess_reminder.excess_reminder_id --------- 
 schema        = null
 tableName     = excess_reminder
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
	private Integer excessReminderId;
			
	/**data for the column :
	
 --------- excess_reminder.reminder_date --------- 
 schema        = null
 tableName     = excess_reminder
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
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date reminderDate;
			
	/**data for the column :
	
 --------- excess_reminder.reminder_counter --------- 
 schema        = null
 tableName     = excess_reminder
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer reminderCounter;
			
	/**data for the column :
	
 --------- excess_reminder.reminder_remarks --------- 
 schema        = null
 tableName     = excess_reminder
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
	private String reminderRemarks;
			
	/**data for the column :
	
 --------- excess_reminder.excess_charge_id --------- 
 schema        = null
 tableName     = excess_reminder
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private ExcessCharge excessChargeId;
			
	/**data for the column :
	
 --------- excess_reminder.created_time --------- 
 schema        = null
 tableName     = excess_reminder
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- excess_reminder.created_by --------- 
 schema        = null
 tableName     = excess_reminder
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- excess_reminder.modified_time --------- 
 schema        = null
 tableName     = excess_reminder
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- excess_reminder.modified_by --------- 
 schema        = null
 tableName     = excess_reminder
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- excess_reminder.deleted_time --------- 
 schema        = null
 tableName     = excess_reminder
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
	
 --------- excess_reminder.deleted_by --------- 
 schema        = null
 tableName     = excess_reminder
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
	
 --------- excess_reminder.deleted_status --------- 
 schema        = null
 tableName     = excess_reminder
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- excess_reminder.reminder_by --------- 
 schema        = null
 tableName     = excess_reminder
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
	private String reminderBy;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="excess_reminder_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getExcessReminderId(){
		return excessReminderId;
	}
	public void setExcessReminderId(java.lang.Integer value){
		excessReminderId = value;
	}
			// PK GETTER SETTER END

						@Column(name="reminder_date")
	public java.sql.Date getReminderDate(){
		return reminderDate;
	}
	public void setReminderDate(java.sql.Date value){
		reminderDate = value;
	}
				@Column(name="reminder_counter")
	public java.lang.Integer getReminderCounter(){
		return reminderCounter;
	}
	public void setReminderCounter(java.lang.Integer value){
		reminderCounter = value;
	}
				@Column(name="reminder_remarks")
	public java.lang.String getReminderRemarks(){
		return reminderRemarks;
	}
	public void setReminderRemarks(java.lang.String value){
		reminderRemarks = value;
	}
	@ManyToOne
	@JoinColumn(name="excess_charge_id")
	public ExcessCharge getExcessChargeId(){
		return excessChargeId;
	}
	public void setExcessChargeId(ExcessCharge value){
		excessChargeId = value;
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
				@Column(name="reminder_by")
	public java.lang.String getReminderBy(){
		return reminderBy;
	}
	public void setReminderBy(java.lang.String value){
		reminderBy = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}