
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
@Table(name="case_conversation")
public class CaseConversation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- case_conversation.case_id --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 1
 size          = 11
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Case caseId;
			
	/**data for the column :
	
 --------- case_conversation.conversation_id --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 2
 size          = 11
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long conversationId;
			
	/**data for the column :
	
 --------- case_conversation.conversation_subject --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String conversationSubject;
			
	/**data for the column :
	
 --------- case_conversation.conversation_description --------- 
 schema        = null
 tableName     = case_conversation
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
	private String conversationDescription;
			
	/**data for the column :
	
 --------- case_conversation.conversation_time --------- 
 schema        = null
 tableName     = case_conversation
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
	private java.sql.Timestamp conversationTime;
			
	/**data for the column :
	
 --------- case_conversation.conversation_category --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 5
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private ConversationCategory conversationCategory;
			
	/**data for the column :
	
 --------- case_conversation.created_by --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- case_conversation.created_time --------- 
 schema        = null
 tableName     = case_conversation
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
	
 --------- case_conversation.modified_by --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- case_conversation.modified_time --------- 
 schema        = null
 tableName     = case_conversation
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- case_conversation.deleted_by --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
 --------- case_conversation.deleted_time --------- 
 schema        = null
 tableName     = case_conversation
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- case_conversation.deleted_status --------- 
 schema        = null
 tableName     = case_conversation
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
	private Priority priority;
	private Integer status;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="conversation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getConversationId(){
		return conversationId;
	}
	public void setConversationId(java.lang.Long value){
		conversationId = value;
	}
			// PK GETTER SETTER END
	@ManyToOne
	@JoinColumn(name="case_id")
	public Case getCaseId(){
		return this.caseId;
	}
	public void setCaseId(Case value){
		this.caseId = value;
	}
							@Column(name="conversation_subject")
	public java.lang.String getConversationSubject(){
		return conversationSubject;
	}
	public void setConversationSubject(java.lang.String value){
		conversationSubject = value;
	}
				@Column(name="conversation_description")
	public java.lang.String getConversationDescription(){
		return conversationDescription;
	}
	public void setConversationDescription(java.lang.String value){
		conversationDescription = value;
	}
				@Column(name="conversation_time")
	public java.sql.Timestamp getConversationTime(){
		return conversationTime;
	}
	public void setConversationTime(java.sql.Timestamp value){
		conversationTime = value;
	}
	@ManyToOne
	@JoinColumn(name="conversation_category")
	public ConversationCategory getConversationCategory(){
		return conversationCategory;
	}
	public void setConversationCategory(ConversationCategory value){
		conversationCategory = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
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
	@ManyToOne
	@JoinColumn(name="priority")
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
		
	


}