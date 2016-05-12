package com.ametis.cms.datamodel;

import javax.persistence.*;

@Entity
@Table(name = "policy_member_movement")
public class PolicyMemberMovement implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
 
    private Integer id;  
    private String movementNumber;  
    private java.sql.Date movementDate;  
    private String movementFilePath;  
    private Policy policyId;  
    private Integer movementType; 
    private Integer status;   
    private String description;  
    private java.sql.Timestamp createdTime;   
    private String createdBy;   
    private java.sql.Timestamp modifiedTime;
    private String modifiedBy;
    private java.sql.Timestamp deletedTime;
    private String deletedBy;
    private Integer deletedStatus;    
    private Integer totalMember;
    private String formatedMovementFile;
    private String fileFormat;
    private String datePattern;
    private Integer firstLineHeader;
    private ExportImportTemplate templateId;

    // foreign affairs
    // -- foreign affairs end
    // -- exported key
    // -- exported key end
    // Fields End
    // PK GETTER SETTER
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer value) {
        id = value;
    }
    // PK GETTER SETTER END

    @Column(name = "movement_number")
    public java.lang.String getMovementNumber() {
        return movementNumber;
    }

    public void setMovementNumber(java.lang.String value) {
        movementNumber = value;
    }

    @Column(name = "movement_date")
    public java.sql.Date getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(java.sql.Date value) {
        movementDate = value;
    }

    @Column(name = "movement_file_path")
    public java.lang.String getMovementFilePath() {
        return movementFilePath;
    }

    public void setMovementFilePath(java.lang.String value) {
        movementFilePath = value;
    }

    @ManyToOne
    @JoinColumn(name = "policy_id")
    public Policy getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Policy value) {
        policyId = value;
    }

    @Column(name = "movement_type")
    public java.lang.Integer getMovementType() {
        return movementType;
    }

    public void setMovementType(java.lang.Integer value) {
        movementType = value;
    }

    @Column(name = "status")
    public java.lang.Integer getStatus() {
        return status;
    }

    public void setStatus(java.lang.Integer value) {
        status = value;
    }

    @Column(name = "description")
    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String value) {
        description = value;
    }

    @Column(name = "created_time")
    public java.sql.Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(java.sql.Timestamp value) {
        createdTime = value;
    }

    @Column(name = "created_by")
    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(java.lang.String value) {
        createdBy = value;
    }

    @Column(name = "modified_time")
    public java.sql.Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(java.sql.Timestamp value) {
        modifiedTime = value;
    }

    @Column(name = "modified_by")
    public java.lang.String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(java.lang.String value) {
        modifiedBy = value;
    }

    @Column(name = "deleted_time")
    public java.sql.Timestamp getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(java.sql.Timestamp value) {
        deletedTime = value;
    }

    @Column(name = "deleted_by")
    public java.lang.String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(java.lang.String value) {
        deletedBy = value;
    }

    @Column(name = "deleted_status")
    public java.lang.Integer getDeletedStatus() {
        return deletedStatus;
    }

    public void setDeletedStatus(java.lang.Integer value) {
        deletedStatus = value;
    }

    
    
    // foreign affairs
    // foreign affairs end
// exported key
    //exported key end

    @Column(name="total_member")
    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }

    @Column(name="formated_movement_file")
    public String getFormatedMovementFile() {
        return formatedMovementFile;
    }

    public void setFormatedMovementFile(String formatedMovementFile) {
        this.formatedMovementFile = formatedMovementFile;
    }

    @ManyToOne
    @JoinColumn(name="template_id")
	public ExportImportTemplate getTemplateId() {
		return templateId;
	}

	public void setTemplateId(ExportImportTemplate templateId) {
		this.templateId = templateId;
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
    
}