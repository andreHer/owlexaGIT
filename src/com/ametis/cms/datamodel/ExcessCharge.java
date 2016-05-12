
package com.ametis.cms.datamodel;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;


@Entity
@Table(name="excess_charge")
public class ExcessCharge implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	private String excessNumberCounter;
	/**data for the column :
	
 --------- excess_charge.excess_charge_id --------- 
 schema        = null
 tableName     = excess_charge
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
	private Integer excessChargeId;
			
	/**data for the column :
	
 --------- excess_charge.excess_charge_value --------- 
 schema        = null
 tableName     = excess_charge
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double excessChargeValue;
	private String excessChargeNumber;
									
	/**data for the column :
	
 --------- excess_charge.excess_charge_status --------- 
 schema        = null
 tableName     = excess_charge
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
	private PaymentStatus excessChargeStatus;
			
	/**data for the column :
	
 --------- excess_charge.remarks --------- 
 schema        = null
 tableName     = excess_charge
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String remarks;
			
	/**data for the column :
	
 --------- excess_charge.excess_charge_time --------- 
 schema        = null
 tableName     = excess_charge
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
	private java.sql.Date excessChargeTime;
			
	/**data for the column :
	
 --------- excess_charge.created_time --------- 
 schema        = null
 tableName     = excess_charge
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
	
 --------- excess_charge.created_by --------- 
 schema        = null
 tableName     = excess_charge
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
	
 --------- excess_charge.deleted_time --------- 
 schema        = null
 tableName     = excess_charge
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
	
 --------- excess_charge.deleted_by --------- 
 schema        = null
 tableName     = excess_charge
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
	
 --------- excess_charge.modified_time --------- 
 schema        = null
 tableName     = excess_charge
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
	
 --------- excess_charge.modified_by --------- 
 schema        = null
 tableName     = excess_charge
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
	
 --------- excess_charge.deleted_status --------- 
 schema        = null
 tableName     = excess_charge
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
	
 --------- excess_charge.last_reminder_time --------- 
 schema        = null
 tableName     = excess_charge
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date lastReminderTime;
			
	/**data for the column :
	
 --------- excess_charge.next_reminder_time --------- 
 schema        = null
 tableName     = excess_charge
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date nextReminderTime;
			
	/**data for the column :
	
 --------- excess_charge.reminder_counter --------- 
 schema        = null
 tableName     = excess_charge
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer reminderCounter;
		
	// foreign affairs
	
			/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = excess_charge
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
				/** Claim
	data for the foreign class :
	
 --------- claim.claim_id --------- 
 schema        = null
 tableName     = claim
 foreignCol    = claim_id
 foreignTab    = excess_charge
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
	private Claim claimId;
	
	private Set<ExcessReminder> excessReminders = new HashSet(0);
	private Set<ExcessChargeItem> excessChargeItems = new HashSet(0);
	
			
	private String memberNumber;
	private String memberName;
	private Invoice invoiceId;
	private Integer excessLetterSent;
	private Double outstanding;
	private Double excessPaidValue;
	private String printHistory;
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="excess_charge_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getExcessChargeId(){
		return excessChargeId;
	}
	public void setExcessChargeId(java.lang.Integer value){
		excessChargeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="excess_charge_value")
	public java.lang.Double getExcessChargeValue(){
		return excessChargeValue;
	}
	public void setExcessChargeValue(java.lang.Double value){
		excessChargeValue = value;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="excess_charge_status")
	public PaymentStatus getExcessChargeStatus(){
		return excessChargeStatus;
	}
	public void setExcessChargeStatus(PaymentStatus value){
		excessChargeStatus = value;
	}
				@Column(name="remarks")
	public java.lang.String getRemarks(){
		return remarks;
	}
	public void setRemarks(java.lang.String value){
		remarks = value;
	}
				@Column(name="excess_charge_time")
	public java.sql.Date getExcessChargeTime(){
		return excessChargeTime;
	}
	public void setExcessChargeTime(java.sql.Date value){
		excessChargeTime = value;
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
				@Column(name="last_reminder_time")
	public java.sql.Date getLastReminderTime(){
		return lastReminderTime;
	}
	public void setLastReminderTime(java.sql.Date value){
		lastReminderTime = value;
	}
				@Column(name="next_reminder_time")
	public java.sql.Date getNextReminderTime(){
		return nextReminderTime;
	}
	public void setNextReminderTime(java.sql.Date value){
		nextReminderTime = value;
	}
				@Column(name="reminder_counter")
	public java.lang.Integer getReminderCounter(){
		return reminderCounter;
	}
	public void setReminderCounter(java.lang.Integer value){
		reminderCounter = value;
	}
		
	// foreign affairs
	@Column(name="excess_charge_number")
	public String getExcessChargeNumber() {
		return excessChargeNumber;
	}
	public void setExcessChargeNumber(String excessChargeNumber) {
		this.excessChargeNumber = excessChargeNumber;
	}
			/** Member */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
				/** Claim */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="claim_id")
	public Claim getClaimId(){
		return this.claimId;
	}

	/** Claim */
	public void setClaimId(Claim obj){
		this.claimId = obj;
	}
	
	@OneToMany(mappedBy="excessChargeId")
	@Sort
	@OrderBy(value="reminderDate")	
	public Set<ExcessReminder> getExcessReminders() {
		return excessReminders;
	}
	public void setExcessReminders(Set<ExcessReminder> excessReminders) {
		this.excessReminders = excessReminders;
	}
	
	@OneToMany(mappedBy="excessChargeId")
	public Set<ExcessChargeItem> getExcessChargeItems() {
		return excessChargeItems;
	}
	public void setExcessChargeItems(Set<ExcessChargeItem> excessChargeItems) {
		this.excessChargeItems = excessChargeItems;
	}
	@Column(name="excess_number_counter")
	public String getExcessNumberCounter() {
		return excessNumberCounter;
	}
	public void setExcessNumberCounter(String excessNumberCounter) {
		this.excessNumberCounter = excessNumberCounter;
	}
	@Column(name="member_number")
	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	@Column(name="member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoice_id")
	public Invoice getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Invoice invoiceId) {
		this.invoiceId = invoiceId;
	}
	@Column(name="excess_letter_sent")
	public Integer getExcessLetterSent() {
		return excessLetterSent;
	}
	public void setExcessLetterSent(Integer excessLetterSent) {
		this.excessLetterSent = excessLetterSent;
	}
	@Column(name="outstanding")
	public Double getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}
	@Column(name="excess_paid_value")
	public Double getExcessPaidValue() {
		return excessPaidValue;
	}
	public void setExcessPaidValue(Double excessPaidValue) {
		this.excessPaidValue = excessPaidValue;
	}
	@Column(name="print_history")
	public String getPrintHistory() {
		return printHistory;
	}
	public void setPrintHistory(String printHistory) {
		this.printHistory = printHistory;
	}
	

	
	
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}