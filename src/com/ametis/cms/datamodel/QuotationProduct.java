
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="quotation_product")
public class QuotationProduct implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- quotation_product.quotation_product_id --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private Integer quotationProductId;
									
	/**data for the column :
	
 --------- quotation_product.quantity --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer quantity;
			
	/**data for the column :
	
 --------- quotation_product.member_type --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer memberType;
			
	/**data for the column :
	
 --------- quotation_product.created_time --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- quotation_product.created_by --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- quotation_product.deleted_time --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- quotation_product.deleted_by --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- quotation_product.deleted_status --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- quotation_product.modified_time --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- quotation_product.modified_by --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private String modifiedBy;
		
	// foreign affairs
	
			/** Product
	data for the foreign class :
	
 --------- product.product_id --------- 
 schema        = null
 tableName     = product
 foreignCol    = product_id
 foreignTab    = quotation_product
 catalog       = wanaartha
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
				/** Quotation
	data for the foreign class :
	
 --------- quotation.quotation_id --------- 
 schema        = null
 tableName     = quotation
 foreignCol    = quotation_id
 foreignTab    = quotation_product
 catalog       = wanaartha
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
	private Quotation quotationId;
	private Integer totalMaleMember;
	private Integer totalFemaleMember;
	private Integer totalChildMember;
	private Integer totalS0;
	private Integer totalS1;
	private Integer totalS2;
	private Integer totalS3;
	private Integer totalS4;
	private Integer totalS5;
	private Integer totalK0;
	private Integer totalK1;
	private Integer totalK2;
	private Integer totalK3;
	private Integer totalK4;
	private Integer totalK5;
	private Double quotationMaleTotal;
	private Double quotationFemaleTotal;
	private Double quotationChildTotal;
	private Double quotationS0Total;
	private Double quotationS1Total;
	private Double quotationS2Total;
	private Double quotationS3Total;
	private Double quotationS4Total;
	private Double quotationS5Total;
	private Double quotationK1Total;
	private Double quotationK0Total;
	private Double quotationK2Total;
	private Double quotationK3Total;
	private Double quotationK4Total;
	private Double quotationK5Total;
	
	private Double quotationProductTotal;
	
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="quotation_product_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getQuotationProductId(){
		return quotationProductId;
	}
	public void setQuotationProductId(java.lang.Integer value){
		quotationProductId = value;
	}
			// PK GETTER SETTER END

												@Column(name="quantity")
	public java.lang.Integer getQuantity(){
		return quantity;
	}
	public void setQuantity(java.lang.Integer value){
		quantity = value;
	}
				@Column(name="member_type")
	public java.lang.Integer getMemberType(){
		return memberType;
	}
	public void setMemberType(java.lang.Integer value){
		memberType = value;
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
				/** Quotation */
	@ManyToOne
	@JoinColumn(name="quotation_id")
	public Quotation getQuotationId(){
		return this.quotationId;
	}

	/** Quotation */
	public void setQuotationId(Quotation obj){
		this.quotationId = obj;
	}
	@Column(name="total_male_member")
	public Integer getTotalMaleMember() {
		return totalMaleMember;
	}
	public void setTotalMaleMember(Integer totalMaleMember) {
		this.totalMaleMember = totalMaleMember;
	}
	@Column(name="total_female_member")
	public Integer getTotalFemaleMember() {
		return totalFemaleMember;
	}
	public void setTotalFemaleMember(Integer totalFemaleMember) {
		this.totalFemaleMember = totalFemaleMember;
	}
	@Column(name="total_child_member")
	public Integer getTotalChildMember() {
		return totalChildMember;
	}
	public void setTotalChildMember(Integer totalChildMember) {
		this.totalChildMember = totalChildMember;
	}
	@Column(name="total_s0")
	public Integer getTotalS0() {
		return totalS0;
	}
	public void setTotalS0(Integer totalS0) {
		this.totalS0 = totalS0;
	}
	@Column(name="total_s1")
	public Integer getTotalS1() {
		return totalS1;
	}
	public void setTotalS1(Integer totalS1) {
		this.totalS1 = totalS1;
	}
	@Column(name="total_s2")
	public Integer getTotalS2() {
		return totalS2;
	}
	public void setTotalS2(Integer totalS2) {
		this.totalS2 = totalS2;
	}
	@Column(name="total_s3")
	public Integer getTotalS3() {
		return totalS3;
	}
	public void setTotalS3(Integer totalS3) {
		this.totalS3 = totalS3;
	}
	@Column(name="total_s4")
	public Integer getTotalS4() {
		return totalS4;
	}
	public void setTotalS4(Integer totalS4) {
		this.totalS4 = totalS4;
	}
	@Column(name="total_s5")
	public Integer getTotalS5() {
		return totalS5;
	}
	public void setTotalS5(Integer totalS5) {
		this.totalS5 = totalS5;
	}
	@Column(name="total_k0")
	public Integer getTotalK0() {
		return totalK0;
	}
	public void setTotalK0(Integer totalK0) {
		this.totalK0 = totalK0;
	}
	@Column(name="total_k1")
	public Integer getTotalK1() {
		return totalK1;
	}
	public void setTotalK1(Integer totalK1) {
		this.totalK1 = totalK1;
	}
	@Column(name="total_k2")
	public Integer getTotalK2() {
		return totalK2;
	}
	public void setTotalK2(Integer totalK2) {
		this.totalK2 = totalK2;
	}
	@Column(name="total_k3")
	public Integer getTotalK3() {
		return totalK3;
	}
	public void setTotalK3(Integer totalK3) {
		this.totalK3 = totalK3;
	}
	@Column(name="total_k4")
	public Integer getTotalK4() {
		return totalK4;
	}
	public void setTotalK4(Integer totalK4) {
		this.totalK4 = totalK4;
	}
	@Column(name="total_k5")
	public Integer getTotalK5() {
		return totalK5;
	}
	public void setTotalK5(Integer totalK5) {
		this.totalK5 = totalK5;
	}
	@Column(name="quotation_male_total")
	public Double getQuotationMaleTotal() {
		return quotationMaleTotal;
	}
	public void setQuotationMaleTotal(Double quotationMemberTotal) {
		this.quotationMaleTotal = quotationMemberTotal;
	}
	@Column(name="quotation_female_total")
	public Double getQuotationFemaleTotal() {
		return quotationFemaleTotal;
	}
	public void setQuotationFemaleTotal(Double quotationFemaleTotal) {
		this.quotationFemaleTotal = quotationFemaleTotal;
	}
	@Column(name="quotation_child_total")
	public Double getQuotationChildTotal() {
		return quotationChildTotal;
	}
	public void setQuotationChildTotal(Double quotationChildTotal) {
		this.quotationChildTotal = quotationChildTotal;
	}
	@Column(name="quotation_s0_total")
	public Double getQuotationS0Total() {
		return quotationS0Total;
	}
	public void setQuotationS0Total(Double quotationS0Total) {
		this.quotationS0Total = quotationS0Total;
	}
	@Column(name="quotation_s1_total")
	public Double getQuotationS1Total() {
		return quotationS1Total;
	}
	public void setQuotationS1Total(Double quotationS1Total) {
		this.quotationS1Total = quotationS1Total;
	}
	@Column(name="quotation_s2_total")
	public Double getQuotationS2Total() {
		return quotationS2Total;
	}
	public void setQuotationS2Total(Double quotationS2Total) {
		this.quotationS2Total = quotationS2Total;
	}
	@Column(name="quotation_s3_total")
	public Double getQuotationS3Total() {
		return quotationS3Total;
	}
	public void setQuotationS3Total(Double quotationS3Total) {
		this.quotationS3Total = quotationS3Total;
	}
	@Column(name="quotation_s4_total")
	public Double getQuotationS4Total() {
		return quotationS4Total;
	}
	public void setQuotationS4Total(Double quotationS4Total) {
		this.quotationS4Total = quotationS4Total;
	}
	@Column(name="quotation_s5_total")
	public Double getQuotationS5Total() {
		return quotationS5Total;
	}
	public void setQuotationS5Total(Double quotationS5Total) {
		this.quotationS5Total = quotationS5Total;
	}
	@Column(name="quotation_k1_total")
	public Double getQuotationK1Total() {
		return quotationK1Total;
	}
	public void setQuotationK1Total(Double quotationK1Total) {
		this.quotationK1Total = quotationK1Total;
	}
	@Column(name="quotation_k0_total")
	public Double getQuotationK0Total() {
		return quotationK0Total;
	}
	
	public void setQuotationK0Total(Double quotationK0Total) {
		this.quotationK0Total = quotationK0Total;
	}
	@Column(name="quotation_k2_total")
	public Double getQuotationK2Total() {
		return quotationK2Total;
	}
	public void setQuotationK2Total(Double quotationK2Total) {
		this.quotationK2Total = quotationK2Total;
	}
	@Column(name="quotation_k3_total")
	public Double getQuotationK3Total() {
		return quotationK3Total;
	}
	public void setQuotationK3Total(Double quotationK3Total) {
		this.quotationK3Total = quotationK3Total;
	}
	@Column(name="quotation_k4_total")
	public Double getQuotationK4Total() {
		return quotationK4Total;
	}
	public void setQuotationK4Total(Double quotationK4Total) {
		this.quotationK4Total = quotationK4Total;
	}
	@Column(name="quotation_k5_total")
	public Double getQuotationK5Total() {
		return quotationK5Total;
	}
	public void setQuotationK5Total(Double quotationK5Total) {
		this.quotationK5Total = quotationK5Total;
	}
	@Column(name="quotation_product_total")
	public Double getQuotationProductTotal() {
		return quotationProductTotal;
	}
	public void setQuotationProductTotal(Double quotationProductTotal) {
		this.quotationProductTotal = quotationProductTotal;
	}
			
	

}