
package com.ametis.cms.datamodel;


import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="data_import_stage")
public class DataImportStage implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

    public static final int TIPE_HEADER_CLAIM = 1;
    public static final int TIPE_DETAIL_CLAIM = 2;
    
    public static final int TIPE_MEMBER = 3;
    public static final int TIPE_PROVIDER = 4;
    public static final int TIPE_POLICY_PROVIDER = 5;
    public static final int TIPE_MEMBER_PROVIDER = 6;
    public static final int TIPE_PROVIDER_TYPE_PROCEDURE = 7;
    public static final int TIPE_PROVIDER_TYPE_DIAGNOSIS = 8;
    public static final int TIPE_PROVIDER_PROCEDURE = 9;
    public static final int TIPE_PROVIDER_DIAGNOSIS = 10;
    public static final int TIPE_CLAIM = 11;
    public static final int TIPE_ITEM_MAPPING = 12;
    public static final int TIPE_MEMBER_DIAGNOSIS = 13;
    public static final int TIPE_PAYMENT = 14;
    public static final int TIPE_GROUP_SUSPEND = 15;
    public static final int TIPE_PRODUCT_UPLOAD = 16;
    
    public static final int CARD_PRINT_REQUESTED = 4;
    public static final int UPLOAD_PROCCESSED = 2;
    
    public static final int STATUS_OPEN = 0;
    public static final int STATUS_EXTRACT = 1;
    public static final int STATUS_MIGRATE = 2;
    public static final int STATUS_ACTIVATED = 3;
    

	//Fields
		
	/**data for the column :
	
 --------- data_import_stage.id --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
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
	private Integer id;
			
	/**data for the column :
	
 --------- data_import_stage.import_date --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Date importDate;
			
	/**data for the column :
	
 --------- data_import_stage.import_raw_file --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String importRawFile;
			
	/**data for the column :
	
 --------- data_import_stage.import_ready_file --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String importReadyFile;
			
	/**data for the column :
	
 --------- data_import_stage.status --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- data_import_stage.import_number --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String importNumber;
			
	/**data for the column :
	
 --------- data_import_stage.description --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- data_import_stage.created_time --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- data_import_stage.created_by --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- data_import_stage.modified_time --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- data_import_stage.modified_by --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- data_import_stage.deleted_time --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- data_import_stage.deleted_by --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 30
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- data_import_stage.deleted_status --------- 
 schema        = null
 tableName     = data_import_stage
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		

        private ExportImportTemplate exportImportTemplate;
        private Integer tipe;
        private PolicyMemberMovement movementId;
        private String fileFormat;
        private String datePattern;
        private Integer firstLineHeader;
        private Client clientId;
        private String fileDirectory;
        private Date receivedDate;

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

						@Column(name="import_date")
	public java.sql.Date getImportDate(){
		return importDate;
	}
	public void setImportDate(java.sql.Date value){
		importDate = value;
	}
				@Column(name="import_raw_file")
	public java.lang.String getImportRawFile(){
		return importRawFile;
	}
	public void setImportRawFile(java.lang.String value){
		importRawFile = value;
	}
				@Column(name="import_ready_file")
	public java.lang.String getImportReadyFile(){
		return importReadyFile;
	}
	public void setImportReadyFile(java.lang.String value){
		importReadyFile = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
				@Column(name="import_number")
	public java.lang.String getImportNumber(){
		return importNumber;
	}
	public void setImportNumber(java.lang.String value){
		importNumber = value;
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

        @ManyToOne
        @JoinColumn(name="template_id")
    public ExportImportTemplate getExportImportTemplate() {
        return exportImportTemplate;
    }

    public void setExportImportTemplate(ExportImportTemplate exportImportTemplate) {
        this.exportImportTemplate = exportImportTemplate;
    }

    @Column(name="tipe")
    public Integer getTipe() {
        return tipe;
    }

    public void setTipe(Integer tipe) {
        this.tipe = tipe;
    }
    @ManyToOne
    @JoinColumn(name="movement_id")
	public PolicyMemberMovement getMovementId() {
		return movementId;
	}
	public void setMovementId(PolicyMemberMovement movementId) {
		this.movementId = movementId;
	}
	@Column(name="file_format")
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	@Column(name="date_pattern")
	public String getDatePattern() {
		return datePattern;
	}
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	@Column(name="is_first_line_header")
	public Integer getFirstLineHeader() {
		return firstLineHeader;
	}
	public void setFirstLineHeader(Integer firstLineHeader) {
		this.firstLineHeader = firstLineHeader;
	}
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}
	@Column(name="file_directory")
	public String getFileDirectory() {
		return fileDirectory;
	}
	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}
	@Column(name="receive_date")
	public Date getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	



}