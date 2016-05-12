
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="product_layer_limit")
public class ProductLayerLimit implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- product_layer_limit.product_layer_limit_id --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Integer productLayerLimitId;
						
	/**data for the column :
	
 --------- product_layer_limit.co_share_percentage --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double coSharePercentage;
			
	/**data for the column :
	
 --------- product_layer_limit.co_share_amount --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double coShareAmount;
			
	/**data for the column :
	
 --------- product_layer_limit.annual_limit --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double annualLimit;
			
	/**data for the column :
	
 --------- product_layer_limit.layer_level --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer layerLevel;
			
	/**data for the column :
	
 --------- product_layer_limit.description --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- product_layer_limit.created_time --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- product_layer_limit.created_by --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- product_layer_limit.modified_time --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- product_layer_limit.modified_by --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- product_layer_limit.deleted_time --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- product_layer_limit.deleted_by --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- product_layer_limit.deleted_status --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
			/** Product
	data for the foreign class :
	
 --------- product.product_id --------- 
 schema        = null
 tableName     = product
 foreignCol    = product_id
 foreignTab    = product_layer_limit
 catalog       = insura-lintas
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
	private Product productId;
	private Diagnosis diagnosisId;
	private DiagnosisSet diagnosisSetId;
	private Double deductible;
	
	private ProductType productTypeId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="product_layer_limit_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getProductLayerLimitId(){
		return productLayerLimitId;
	}
	public void setProductLayerLimitId(java.lang.Integer value){
		productLayerLimitId = value;
	}
			// PK GETTER SETTER END

									@Column(name="co_share_percentage")
	public java.lang.Double getCoSharePercentage(){
		return coSharePercentage;
	}
	public void setCoSharePercentage(java.lang.Double value){
		coSharePercentage = value;
	}
				@Column(name="co_share_amount")
	public java.lang.Double getCoShareAmount(){
		return coShareAmount;
	}
	public void setCoShareAmount(java.lang.Double value){
		coShareAmount = value;
	}
				@Column(name="annual_limit")
	public java.lang.Double getAnnualLimit(){
		return annualLimit;
	}
	public void setAnnualLimit(java.lang.Double value){
		annualLimit = value;
	}
				@Column(name="layer_level")
	public java.lang.Integer getLayerLevel(){
		return layerLevel;
	}
	public void setLayerLevel(java.lang.Integer value){
		layerLevel = value;
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
	
			/** Product */
	@ManyToOne
	@JoinColumn(name="product_id")
	public Product getProductId(){
		return this.productId;
	}

	/** Product */
	public void setProductId(Product obj){
		this.productId = obj;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Diagnosis diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	@Column(name="deductible")
	public Double getDeductible() {
		return deductible;
	}
	public void setDeductible(Double deductible) {
		this.deductible = deductible;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis_set_id")
	public DiagnosisSet getDiagnosisSetId() {
		return diagnosisSetId;
	}
	public void setDiagnosisSetId(DiagnosisSet diagnosisSetId) {
		this.diagnosisSetId = diagnosisSetId;
	}
	@ManyToOne
	@JoinColumn(name="product_type_id")
	public ProductType getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(ProductType productTypeId) {
		this.productTypeId = productTypeId;
	}
			
	


}