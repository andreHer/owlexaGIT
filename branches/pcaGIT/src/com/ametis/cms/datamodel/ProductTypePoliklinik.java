
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="product_type_poliklinik")
public class ProductTypePoliklinik implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- product_type_poliklinik.product_type_poliklinik_id --------- 
 schema        = null
 tableName     = product_type_poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer productTypePoliklinikId;
									
	/**data for the column :
	
 --------- product_type_poliklinik.status --------- 
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
 ordinal       = 4
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- product_type_poliklinik.type --------- 
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
 ordinal       = 5
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer type;
			
	/**data for the column :
	
 --------- product_type_poliklinik.description --------- 
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
 ordinal       = 6
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- product_type_poliklinik.created_time --------- 
 schema        = null
 tableName     = product_type_poliklinik
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- product_type_poliklinik.created_by --------- 
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
 ordinal       = 8
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- product_type_poliklinik.modified_time --------- 
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
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- product_type_poliklinik.modified_by --------- 
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
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- product_type_poliklinik.deleted_time --------- 
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
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- product_type_poliklinik.deleted_by --------- 
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
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- product_type_poliklinik.deleted_status --------- 
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
 ordinal       = 13
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** ProductType
	data for the foreign class :
	
 --------- product_type.product_type_id --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = product_type_id
 foreignTab    = product_type_poliklinik
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
	private ProductType productTypeId;
				/** Poliklinik
	data for the foreign class :
	
 --------- poliklinik.poliklinik_id --------- 
 schema        = null
 tableName     = poliklinik
 foreignCol    = poliklinik_id
 foreignTab    = product_type_poliklinik
 catalog       = insura-master
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================



	 */
	private Poliklinik poliklinikId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="product_type_poliklinik_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProductTypePoliklinikId(){
		return productTypePoliklinikId;
	}
	public void setProductTypePoliklinikId(java.lang.Integer value){
		productTypePoliklinikId = value;
	}
			// PK GETTER SETTER END

												@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
	}
				@Column(name="type")
	public java.lang.Integer getType(){
		return type;
	}
	public void setType(java.lang.Integer value){
		type = value;
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
	
			/** ProductType */
	@ManyToOne
	@JoinColumn(name="product_type_id")
	public ProductType getProductTypeId(){
		return this.productTypeId;
	}

	/** ProductType */
	public void setProductTypeId(ProductType obj){
		this.productTypeId = obj;
	}
				/** Poliklinik */
	@ManyToOne
	@JoinColumn(name="poliklinik_id")
	public Poliklinik getPoliklinikId(){
		return this.poliklinikId;
	}

	/** Poliklinik */
	public void setPoliklinikId(Poliklinik obj){
		this.poliklinikId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}