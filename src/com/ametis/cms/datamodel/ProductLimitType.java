
package com.ametis.cms.datamodel;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="product_limit_type")
public class ProductLimitType implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/*
	 * Limit Type INNER_LIMIT akan melakukan perhitungan
	 * yang tergantung pada masing - masing benefit ITEM.
	 * Pada umumnya annual limit adalah unlimited
	 * 
	 * dan mungkin ada batasan / claim
	 */
	public static final int INNER_LIMIT = 1;
	
	/*
	 * Limit type OUTER LIMIT akan melakukan perhitungan
	 * yang tergantung pada annual limit. Pada umumnya
	 * inner limitnya akan as charge kecuali kamar.
	 */
	public static final int OUTER_LIMIT = 2;
	
	/*
	 * Limi type DISSABILITY umumnya di check per 
	 */
	public static final int DISSABILITY = 3;
	public static final int CASE_BY_CASE = 4;
	
	

	//Fields
		
	/**data for the column :
	
 --------- product_limit_type.product_limit_type_id --------- 
 schema        = null
 tableName     = product_limit_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Integer productLimitTypeId;
			
	/**data for the column :
	
 --------- product_limit_type.product_limit --------- 
 schema        = null
 tableName     = product_limit_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String productLimit;
			
	/**data for the column :
	
 --------- product_limit_type.description --------- 
 schema        = null
 tableName     = product_limit_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String description;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Product
	data for the exported class :
	
 --------- product.product_limit_type --------- 
 schema        = null
 tableName     = product
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = product_limit_type.product_limit_type_id

=========================================



	 */
	private Set <Product> products = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="product_limit_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProductLimitTypeId(){
		return productLimitTypeId;
	}
	public void setProductLimitTypeId(java.lang.Integer value){
		productLimitTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="product_limit")
	public java.lang.String getProductLimit(){
		return productLimit;
	}
	public void setProductLimit(java.lang.String value){
		productLimit = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** Product */
	@OneToMany(mappedBy="productLimitType")
	public Set<Product> getProducts(){
		return this.products;
	}

	/** Product */
	public void setProducts(Set<Product> obj){
		this.products = obj;
	}
			
	//exported key end



}