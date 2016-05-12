
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="medicine")
public class Medicine implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- medicine.medicine_id --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer medicineId;
			
	/**data for the column :
	
 --------- medicine.medicine_type --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private String medicineType;
			
	/**data for the column :
	
 --------- medicine.deleted_status --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- medicine.modified_time --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- medicine.modified_by --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- medicine.deleted_by --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- medicine.deleted_time --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- medicine.created_time --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- medicine.created_by --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- medicine.medicine_name --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String medicineName;
			
	/**data for the column :
	
 --------- medicine.description --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- medicine.general_diagnose --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String generalDiagnose;
			
	/**data for the column :
	
 --------- medicine.medical_dosage --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String medicalDosage;
			
	/**data for the column :
	
 --------- medicine.medicine_classification --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String medicineClassification;
			
	/**data for the column :
	
 --------- medicine.medicine_code --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String medicineCode;
			
	/**data for the column :
	
 --------- medicine.medicine_price --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double medicinePrice;
			
	/**data for the column :
	
 --------- medicine.medicine_factory --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String medicineFactory;
			
	/**data for the column :
	
 --------- medicine.contra_indication --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String contraIndication;
	private MedicineFactory medicineFactoryId;
	private MedicineCategory medicineCategoryId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** ProviderMedicine
	data for the exported class :
	
 --------- provider_medicine.medicine_id --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = medicine.medicine_id

=========================================



	 */
	private Set <ProviderMedicine> providerMedicines = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="medicine_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMedicineId(){
		return medicineId;
	}
	public void setMedicineId(java.lang.Integer value){
		medicineId = value;
	}
			// PK GETTER SETTER END

						@Column(name="medicine_type")
	public java.lang.String getMedicineType(){
		return medicineType;
	}
	public void setMedicineType(java.lang.String value){
		medicineType = value;
	}
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
				@Column(name="medicine_name")
	public java.lang.String getMedicineName(){
		return medicineName;
	}
	public void setMedicineName(java.lang.String value){
		medicineName = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="general_diagnose")
	public java.lang.String getGeneralDiagnose(){
		return generalDiagnose;
	}
	public void setGeneralDiagnose(java.lang.String value){
		generalDiagnose = value;
	}
				@Column(name="medical_dosage")
	public java.lang.String getMedicalDosage(){
		return medicalDosage;
	}
	public void setMedicalDosage(java.lang.String value){
		medicalDosage = value;
	}
				@Column(name="medicine_classification")
	public java.lang.String getMedicineClassification(){
		return medicineClassification;
	}
	public void setMedicineClassification(java.lang.String value){
		medicineClassification = value;
	}
				@Column(name="medicine_code")
	public java.lang.String getMedicineCode(){
		return medicineCode;
	}
	public void setMedicineCode(java.lang.String value){
		medicineCode = value;
	}
				@Column(name="medicine_price")
	public java.lang.Double getMedicinePrice(){
		return medicinePrice;
	}
	public void setMedicinePrice(java.lang.Double value){
		medicinePrice = value;
	}
				@Column(name="medicine_factory")
	public java.lang.String getMedicineFactory(){
		return medicineFactory;
	}
	public void setMedicineFactory(java.lang.String value){
		medicineFactory = value;
	}
				@Column(name="contra_indication")
	public java.lang.String getContraIndication(){
		return contraIndication;
	}
	public void setContraIndication(java.lang.String value){
		contraIndication = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** ProviderMedicine */
	@OneToMany(mappedBy="medicineId")
	public Set<ProviderMedicine> getProviderMedicines(){
		return this.providerMedicines;
	}

	/** ProviderMedicine */
	public void setProviderMedicines(Set<ProviderMedicine> obj){
		this.providerMedicines = obj;
	}
	@ManyToOne
	@JoinColumn(name="medicine_factory_id")
	public MedicineFactory getMedicineFactoryId() {
		return medicineFactoryId;
	}
	public void setMedicineFactoryId(MedicineFactory medicineFactoryId) {
		this.medicineFactoryId = medicineFactoryId;
	}
	@ManyToOne
	@JoinColumn(name="medicine_category_id")
	public MedicineCategory getMedicineCategoryId() {
		return medicineCategoryId;
	}
	public void setMedicineCategoryId(MedicineCategory medicineCategoryId) {
		this.medicineCategoryId = medicineCategoryId;
	}
			
	//exported key end

	


}