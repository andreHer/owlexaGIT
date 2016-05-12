
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="member_limit_layer")
public class MemberLimitLayer implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- member_limit_layer.member_limit_layer_id --------- 
 schema        = null
 tableName     = member_limit_layer
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
	private Integer memberLimitLayerId;
												
	/**data for the column :
	
 --------- member_limit_layer.annual_limit --------- 
 schema        = null
 tableName     = member_limit_layer
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
	private Double deductible;
	private Integer layer;
	private Diagnosis diagnosisId;
			
	/**data for the column :
	
 --------- member_limit_layer.actual_benefit_limit --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double actualBenefitLimit;
			
	/**data for the column :
	
 --------- member_limit_layer.benefit_usage --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double benefitUsage;
			
	/**data for the column :
	
 --------- member_limit_layer.co_share_percentage --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double coSharePercentage;
			
	/**data for the column :
	
 --------- member_limit_layer.co_share_amount --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double coShareAmount;
			
	/**data for the column :
	
 --------- member_limit_layer.status --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- member_limit_layer.created_time --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- member_limit_layer.created_by --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- member_limit_layer.modified_time --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- member_limit_layer.modified_by --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- member_limit_layer.deleted_time --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- member_limit_layer.deleted_by --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- member_limit_layer.deleted_status --------- 
 schema        = null
 tableName     = member_limit_layer
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** ProductLayerLimit
	data for the foreign class :
	
 --------- product_layer_limit.product_layer_limit_id --------- 
 schema        = null
 tableName     = product_layer_limit
 foreignCol    = product_limit_layer_id
 foreignTab    = member_limit_layer
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
	private ProductLayerLimit productLimitLayerId;
				/** Member
	data for the foreign class :
	
 --------- member.member_id --------- 
 schema        = null
 tableName     = member
 foreignCol    = member_id
 foreignTab    = member_limit_layer
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
	private Member memberId;
				/** Product
	data for the foreign class :
	
 --------- product.product_id --------- 
 schema        = null
 tableName     = product
 foreignCol    = product_id
 foreignTab    = member_limit_layer
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
	private DiagnosisSet diagnosisSetId;
	private MemberProduct memberProductId;
	private MemberLimitLayer familyLimitLayerId;
	
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="member_limit_layer_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getMemberLimitLayerId(){
		return memberLimitLayerId;
	}
	public void setMemberLimitLayerId(java.lang.Integer value){
		memberLimitLayerId = value;
	}
			// PK GETTER SETTER END

	@Column(name="annual_limit")
	public java.lang.Double getAnnualLimit(){
		return annualLimit;
	}
	public void setAnnualLimit(java.lang.Double value){
		annualLimit = value;
	}
				@Column(name="actual_benefit_limit")
	public java.lang.Double getActualBenefitLimit(){
		return actualBenefitLimit;
	}
	public void setActualBenefitLimit(java.lang.Double value){
		actualBenefitLimit = value;
	}
				@Column(name="benefit_usage")
	public java.lang.Double getBenefitUsage(){
		return benefitUsage;
	}
	public void setBenefitUsage(java.lang.Double value){
		benefitUsage = value;
	}
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
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
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
	
			/** ProductLayerLimit */
	@ManyToOne
	@JoinColumn(name="product_limit_layer_id")
	public ProductLayerLimit getProductLimitLayerId(){
		return this.productLimitLayerId;
	}

	/** ProductLayerLimit */
	public void setProductLimitLayerId(ProductLayerLimit obj){
		this.productLimitLayerId = obj;
	}
				/** Member */
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId(){
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj){
		this.memberId = obj;
	}
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
	@JoinColumn(name="member_product_id")
	public MemberProduct getMemberProductId() {
		return memberProductId;
	}
	public void setMemberProductId(MemberProduct memberProductId) {
		this.memberProductId = memberProductId;
	}
	@Column(name="deductible")
	public Double getDeductible() {
		return deductible;
	}
	public void setDeductible(Double deductible) {
		this.deductible = deductible;
	}
	@ManyToOne
	@JoinColumn(name="diagnosis_id")
	public Diagnosis getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(Diagnosis diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	@Column(name="layer")
	public Integer getLayer() {
		return layer;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
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
	@JoinColumn(name="family_limit_layer_id")
	public MemberLimitLayer getFamilyLimitLayerId() {
		return familyLimitLayerId;
	}
	public void setFamilyLimitLayerId(MemberLimitLayer familyLimitLayerId) {
		this.familyLimitLayerId = familyLimitLayerId;
	}
			
	
	


}