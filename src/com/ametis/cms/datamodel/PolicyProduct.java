
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="policy_product")
public class PolicyProduct implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- policy_product.policy_product_id --------- 
 schema        = null
 tableName     = policy_product
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
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long policyProductId;
						
	/**data for the column :
	
 --------- policy_product.total --------- 
 schema        = null
 tableName     = policy_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer total;
					
	// foreign affairs
	
			/** Policy
	data for the foreign class :
	
 --------- policy.policy_id --------- 
 schema        = null
 tableName     = policy
 foreignCol    = policy_id
 foreignTab    = policy_product
 catalog       = wanaartha
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 11
 type          = -5 
 isPrimaryKey  = true

=========================================



	 */
	private Policy policyId;
				/** Product
	data for the foreign class :
	
 --------- product.product_id --------- 
 schema        = null
 tableName     = product
 foreignCol    = product_id
 foreignTab    = policy_product
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
	private Relationship relationshipId;
	private Integer deletedStatus;
	private String deletedBy;
	private Timestamp deletedTime;
	private String createdBy;
	private Timestamp createdTime;
	private Timestamp modifiedTime;
	private String modifiedBy;
	
	private String sex;
	private Double price;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="policy_product_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getPolicyProductId(){
		return policyProductId;
	}
	public void setPolicyProductId(java.lang.Long value){
		policyProductId = value;
	}
			// PK GETTER SETTER END

									@Column(name="total")
	public java.lang.Integer getTotal(){
		return total;
	}
	public void setTotal(java.lang.Integer value){
		total = value;
	}
					
	// foreign affairs
	
			/** Policy */
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId(){
		return this.policyId;
	}

	/** Policy */
	public void setPolicyId(Policy obj){
		this.policyId = obj;
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
	@JoinColumn(name="relationship_id")
	public Relationship getRelationshipId() {
		return relationshipId;
	}
	public void setRelationshipId(Relationship relationshipId) {
		this.relationshipId = relationshipId;
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
	@Column(name="modified_time")
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Column(name="modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Column(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
			
	



}