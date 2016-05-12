
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="external_raw_data")
public class ExternalRawData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- external_raw_data.id --------- 
 schema        = null
 tableName     = external_raw_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer id;
			
	/**data for the column :
	
 --------- external_raw_data.raw_data --------- 
 schema        = null
 tableName     = external_raw_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String rawData;
			
	/**data for the column :
	
 --------- external_raw_data.is_migrated --------- 
 schema        = null
 tableName     = external_raw_data
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer isMigrated;
					
	// foreign affairs
	
			/** ExportImportTemplate
	data for the foreign class :
	
 --------- export_import_template.id --------- 
 schema        = null
 tableName     = export_import_template
 foreignCol    = template_id
 foreignTab    = external_raw_data
 catalog       = insura
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
	private ExportImportTemplate templateId;
    private DataImportStage importSessionId;
    private Policy policyId;
    private String createdBy;
    private Timestamp createdTime;
    private String modifiedBy;
    private Timestamp modifiedTime;
    private Integer deletedStatus;
    private String deletedBy;
    private Timestamp deletedTime;
    private PolicyMemberMovement movementId;
    private Integer totalToken;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="raw_data")
	public java.lang.String getRawData(){
		return rawData;
	}
	public void setRawData(java.lang.String value){
		rawData = value;
	}
				@Column(name="is_migrated")
	public java.lang.Integer getIsMigrated(){
		return isMigrated;
	}
	public void setIsMigrated(java.lang.Integer value){
		isMigrated = value;
	}
					
	// foreign affairs
	
			/** ExportImportTemplate */
	@ManyToOne
	@JoinColumn(name="template_id")
	public ExportImportTemplate getTemplateId(){
		return this.templateId;
	}

	/** ExportImportTemplate */
	public void setTemplateId(ExportImportTemplate obj){
		this.templateId = obj;
	}
			
        
	// foreign affairs end

// exported key

	
		
	//exported key end

	@ManyToOne
    @JoinColumn(name="import_session_id")
    public DataImportStage getImportSessionId() {
        return importSessionId;
    }

    public void setImportSessionId(DataImportStage importSessionId) {
        this.importSessionId = importSessionId;
    }
    @ManyToOne
    @JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
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
	@Column(name="deleted_status")
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	@Column(name="deleted_by")
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	@Column(name="deleted_time")
	public Timestamp getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}
	
	@ManyToOne
	@JoinColumn(name="movement_id")
	public PolicyMemberMovement getMovementId() {
		return movementId;
	}
	public void setMovementId(PolicyMemberMovement movementId) {
		this.movementId = movementId;
	}
	@Column(name="total_token")
	public Integer getTotalToken() {
		return totalToken;
	}
	public void setTotalToken(Integer totalToken) {
		this.totalToken = totalToken;
	}

	


}