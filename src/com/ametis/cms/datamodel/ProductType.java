
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="product_type")
public class ProductType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public static final int FULL_ADMINISTRATION = 1;
	
	public static final int GROUP = 1;
	public static final int FAMILY = 2;
	public static final int INDIVIDUAL = 3;
	public static final int GROUP_FAMILY  = 4;

	
	//Fields
		
	/**data for the column :
	
 --------- product_type.product_type_id --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
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
	private Integer productTypeId;
			
	/**data for the column :
	
 --------- product_type.description --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- product_type.product_type_name --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String productTypeName;
			
	/**data for the column :
	
 --------- product_type.created_time --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- product_type.created_by --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- product_type.modified_time --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- product_type.modified_by --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- product_type.deleted_time --------- 
 schema        = null
 tableName     = product_type
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
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- product_type.deleted_by --------- 
 schema        = null
 tableName     = product_type
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- product_type.deleted_status --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
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
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** InsurancePackage
	data for the exported class :
	
 --------- insurance_package.package_type --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product_type.product_type_id

=========================================



	 */
	private Set <InsurancePackage> insurancePackages = new HashSet(0);
				/** Member
	data for the exported class :
	
 --------- member.member_type --------- 
 schema        = null
 tableName     = member
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 47
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product_type.product_type_id

=========================================



	 */
	private Set <Member> members = new HashSet(0);
				/** Policy
	data for the exported class :
	
 --------- policy.product_type_id --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product_type.product_type_id

=========================================



	 */
	
				/** Product
	data for the exported class :
	
 --------- product.product_type --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 36
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product_type.product_type_id

=========================================



	 */
	private Set <Product> products = new HashSet(0);
				/** ProductTypePoliklinik
	data for the exported class :
	
 --------- product_type_poliklinik.product_type_id --------- 
 schema        = null
 tableName     = product_type_poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 5
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product_type.product_type_id

=========================================



	 */
	private Set <ProductTypePoliklinik> productTypePolikliniks = new HashSet(0);
				/** ProductTypeProvider
	data for the exported class :
	
 --------- product_type_provider.product_type_id --------- 
 schema        = null
 tableName     = product_type_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 5
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product_type.product_type_id

=========================================



	 */
	private Set <ProductTypeProvider> productTypeProviders = new HashSet(0);
			
	// -- exported key end

	// Fields End


			// PK GETTER SETTER
			@Id
	@Column(name="product_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProductTypeId(){
		return productTypeId;
	}
	public void setProductTypeId(java.lang.Integer value){
		productTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="product_type_name")
	public java.lang.String getProductTypeName(){
		return productTypeName;
	}
	public void setProductTypeName(java.lang.String value){
		productTypeName = value;
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

	
			/** InsurancePackage */
	@OneToMany(mappedBy="packageType")
	public Set<InsurancePackage> getInsurancePackages(){
		return this.insurancePackages;
	}

	/** InsurancePackage */
	public void setInsurancePackages(Set<InsurancePackage> obj){
		this.insurancePackages = obj;
	}
				/** Member */
	@OneToMany(mappedBy="memberType")
	public Set<Member> getMembers(){
		return this.members;
	}

	/** Member */
	public void setMembers(Set<Member> obj){
		this.members = obj;
	}

				/** Product */
	@OneToMany(mappedBy="productType")
	public Set<Product> getProducts(){
		return this.products;
	}

	/** Product */
	public void setProducts(Set<Product> obj){
		this.products = obj;
	}
				/** ProductTypePoliklinik */
	@OneToMany(mappedBy="productTypeId")
	public Set<ProductTypePoliklinik> getProductTypePolikliniks(){
		return this.productTypePolikliniks;
	}

	/** ProductTypePoliklinik */
	public void setProductTypePolikliniks(Set<ProductTypePoliklinik> obj){
		this.productTypePolikliniks = obj;
	}
				/** ProductTypeProvider */
	@OneToMany(mappedBy="productTypeId")
	public Set<ProductTypeProvider> getProductTypeProviders(){
		return this.productTypeProviders;
	}

	/** ProductTypeProvider */
	public void setProductTypeProviders(Set<ProductTypeProvider> obj){
		this.productTypeProviders = obj;
	}
			
	//exported key end



}