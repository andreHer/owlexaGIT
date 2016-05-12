
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="procedure_medicine")
public class ProcedureMedicine implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- procedure_medicine.procedure_medicine_id --------- 
 schema        = null
 tableName     = procedure_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private Integer procedureMedicineId;
									
	/**data for the column :
	
 --------- procedure_medicine.total_usage --------- 
 schema        = null
 tableName     = procedure_medicine
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
	private Double totalUsage;
			
	/**data for the column :
	
 --------- procedure_medicine.total_charge --------- 
 schema        = null
 tableName     = procedure_medicine
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
	private Double totalCharge;
			
	/**data for the column :
	
 --------- procedure_medicine.reference_price --------- 
 schema        = null
 tableName     = procedure_medicine
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
	private Double referencePrice;
			
	/**data for the column :
	
 --------- procedure_medicine.description --------- 
 schema        = null
 tableName     = procedure_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- procedure_medicine.created_time --------- 
 schema        = null
 tableName     = procedure_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- procedure_medicine.created_by --------- 
 schema        = null
 tableName     = procedure_medicine
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
	
 --------- procedure_medicine.modified_time --------- 
 schema        = null
 tableName     = procedure_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- procedure_medicine.modified_by --------- 
 schema        = null
 tableName     = procedure_medicine
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
	
 --------- procedure_medicine.deleted_time --------- 
 schema        = null
 tableName     = procedure_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	
 --------- procedure_medicine.deleted_by --------- 
 schema        = null
 tableName     = procedure_medicine
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
	
 --------- procedure_medicine.deleted_status --------- 
 schema        = null
 tableName     = procedure_medicine
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
		
	// foreign affairs
	
			/** Medicine
	data for the foreign class :
	
 --------- medicine.medicine_id --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = medicine_id
 foreignTab    = procedure_medicine
 catalog       = insura-master
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
	private Medicine medicineId;
				/** MedicalProcedure
	data for the foreign class :
	
 --------- medical_procedure.procedure_id --------- 
 schema        = null
 tableName     = medical_procedure
 foreignCol    = procedure_id
 foreignTab    = procedure_medicine
 catalog       = insura-master
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
	private Procedure procedureId;

	private String uploadNote;

	private Integer statusUpdate;

	private String type;

	private String url;
	
	private Client clientId;
	private Policy policyId;
	
	private MemberGroup memberGroupId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="procedure_medicine_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProcedureMedicineId(){
		return procedureMedicineId;
	}
	public void setProcedureMedicineId(java.lang.Integer value){
		procedureMedicineId = value;
	}
			// PK GETTER SETTER END

												@Column(name="total_usage")
	public java.lang.Double getTotalUsage(){
		return totalUsage;
	}
	public void setTotalUsage(java.lang.Double value){
		totalUsage = value;
	}
				@Column(name="total_charge")
	public java.lang.Double getTotalCharge(){
		return totalCharge;
	}
	public void setTotalCharge(java.lang.Double value){
		totalCharge = value;
	}
				@Column(name="reference_price")
	public java.lang.Double getReferencePrice(){
		return referencePrice;
	}
	public void setReferencePrice(java.lang.Double value){
		referencePrice = value;
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
	
			/** Medicine */
	@ManyToOne
	@JoinColumn(name="medicine_id")
	public Medicine getMedicineId(){
		return this.medicineId;
	}

	/** Medicine */
	public void setMedicineId(Medicine obj){
		this.medicineId = obj;
	}
				/** MedicalProcedure */
	@ManyToOne
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId(){
		return this.procedureId;
	}

	/** MedicalProcedure */
	public void setProcedureId(Procedure obj){
		this.procedureId = obj;
	}
			
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="status_update")
	public Integer getStatusUpdate() {
		return statusUpdate;
	}
	public void setStatusUpdate(Integer statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	@Column(name="upload_note")
	public String getUploadNote() {
		return uploadNote;
	}
	public void setUploadNote(String uploadNote) {
		this.uploadNote = uploadNote;
	}
	

}