
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="product_diagnosis_clausul")
public class ProductDiagnosisClausul implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- product_diagnosis_clausul.product_diagnosis_clausul_id --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 30
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long productDiagnosisClausulId;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.product_id --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 10
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Long productId;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.diagnosis_id --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = -5 
 isPrimaryKey  = false

=========================================


*/
	private Long diagnosisId;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.decision --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer decision;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.parameter_umur --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer parameterUmur;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.umur --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer umur;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.benefit_amount --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double benefitAmount;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.benefit_amount_parameter --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer benefitAmountParameter;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.beban_benefit --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer bebanBenefit;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.created_time --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.created_by --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	private String createdBy;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.modified_time --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	
 --------- product_diagnosis_clausul.modified_by --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
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
	
 --------- product_diagnosis_clausul.deleted_time --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.deleted_by --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- product_diagnosis_clausul.deleted_status --------- 
 schema        = null
 tableName     = product_diagnosis_clausul
 foreignCol    = 
 foreignTab    = 
 catalog       = media
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;

	private String uploadNote;

	private Integer statusUpdate;

	private String type;

	private String url;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="product_diagnosis_clausul_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getProductDiagnosisClausulId(){
		return productDiagnosisClausulId;
	}
	public void setProductDiagnosisClausulId(java.lang.Long value){
		productDiagnosisClausulId = value;
	}
			// PK GETTER SETTER END

						@Column(name="product_id")
	public java.lang.Long getProductId(){
		return productId;
	}
	public void setProductId(java.lang.Long value){
		productId = value;
	}
				@Column(name="diagnosis_id")
	public java.lang.Long getDiagnosisId(){
		return diagnosisId;
	}
	public void setDiagnosisId(java.lang.Long value){
		diagnosisId = value;
	}
				@Column(name="decision")
	public java.lang.Integer getDecision(){
		return decision;
	}
	public void setDecision(java.lang.Integer value){
		decision = value;
	}
				@Column(name="parameter_umur")
	public java.lang.Integer getParameterUmur(){
		return parameterUmur;
	}
	public void setParameterUmur(java.lang.Integer value){
		parameterUmur = value;
	}
				@Column(name="umur")
	public java.lang.Integer getUmur(){
		return umur;
	}
	public void setUmur(java.lang.Integer value){
		umur = value;
	}
				@Column(name="benefit_amount")
	public java.lang.Double getBenefitAmount(){
		return benefitAmount;
	}
	public void setBenefitAmount(java.lang.Double value){
		benefitAmount = value;
	}
				@Column(name="benefit_amount_parameter")
	public java.lang.Integer getBenefitAmountParameter(){
		return benefitAmountParameter;
	}
	public void setBenefitAmountParameter(java.lang.Integer value){
		benefitAmountParameter = value;
	}
				@Column(name="beban_benefit")
	public java.lang.Integer getBebanBenefit(){
		return bebanBenefit;
	}
	public void setBebanBenefit(java.lang.Integer value){
		bebanBenefit = value;
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