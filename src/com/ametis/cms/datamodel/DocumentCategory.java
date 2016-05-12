
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="document_category")
public class DocumentCategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- document_category.document_category_id --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private Integer documentCategoryId;
			
	/**data for the column :
	
 --------- document_category.document_category_name --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 150
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String documentCategoryName;
			
	/**data for the column :
	
 --------- document_category.document_category_code --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String documentCategoryCode;
			
	/**data for the column :
	
 --------- document_category.description --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- document_category.created_time --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 5
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- document_category.created_by --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	
 --------- document_category.modified_time --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- document_category.modified_by --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- document_category.deleted_time --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- document_category.deleted_by --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- document_category.deleted_status --------- 
 schema        = null
 tableName     = document_category
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
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
	private Integer accessibleToProvider;
	private Integer accessibleToClient;
	private Integer accessibleToHRD;
	private Integer accessibleToBroker;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Document
	data for the exported class :
	
 --------- document.document_category_id --------- 
 schema        = null
 tableName     = document
 foreignCol    = 
 foreignTab    = 
 catalog       = qsmart-uat
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 5
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = document_category.document_category_id

=========================================



	 */
	private Set <Document> documents = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="document_category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getDocumentCategoryId(){
		return documentCategoryId;
	}
	public void setDocumentCategoryId(java.lang.Integer value){
		documentCategoryId = value;
	}
			// PK GETTER SETTER END

						@Column(name="document_category_name")
	public java.lang.String getDocumentCategoryName(){
		return documentCategoryName;
	}
	public void setDocumentCategoryName(java.lang.String value){
		documentCategoryName = value;
	}
				@Column(name="document_category_code")
	public java.lang.String getDocumentCategoryCode(){
		return documentCategoryCode;
	}
	public void setDocumentCategoryCode(java.lang.String value){
		documentCategoryCode = value;
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

	
			/** Document */
	@OneToMany(mappedBy="documentCategoryId")
	public Set<Document> getDocuments(){
		return this.documents;
	}

	/** Document */
	public void setDocuments(Set<Document> obj){
		this.documents = obj;
	}
	@Column(name="accessible_to_provider")
	public Integer getAccessibleToProvider() {
		return accessibleToProvider;
	}
	public void setAccessibleToProvider(Integer accessibleToProvider) {
		this.accessibleToProvider = accessibleToProvider;
	}
	@Column(name="accessible_to_client")
	public Integer getAccessibleToClient() {
		return accessibleToClient;
	}
	public void setAccessibleToClient(Integer accessibleToClient) {
		this.accessibleToClient = accessibleToClient;
	}
	@Column(name="accessible_to_hrd")
	public Integer getAccessibleToHRD() {
		return accessibleToHRD;
	}
	public void setAccessibleToHRD(Integer accessibleToHRD) {
		this.accessibleToHRD = accessibleToHRD;
	}
	@Column(name="accessible_to_broker")
	public Integer getAccessibleToBroker() {
		return accessibleToBroker;
	}
	public void setAccessibleToBroker(Integer accessibleToBroker) {
		this.accessibleToBroker = accessibleToBroker;
	}
	
	



}