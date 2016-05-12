
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="item_category_map")
public class ItemCategoryMap implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- item_category_map.item_category_map_id --------- 
 schema        = null
 tableName     = item_category_map
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Integer itemCategoryMapId;
									
	/**data for the column :
	
 --------- item_category_map.is_active --------- 
 schema        = null
 tableName     = item_category_map
 foreignCol    = 
 foreignTab    = 
 catalog       = wahrecon
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
	private Integer isActive;
					
	// foreign affairs
	
			/** CaseCategory
	data for the foreign class :
	
 --------- case_category.case_category_id --------- 
 schema        = null
 tableName     = case_category
 foreignCol    = case_category_id
 foreignTab    = item_category_map
 catalog       = wahrecon
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
	private CaseCategory caseCategoryId;
				/** Item
	data for the foreign class :
	
 --------- item.item_id --------- 
 schema        = null
 tableName     = item
 foreignCol    = item_id
 foreignTab    = item_category_map
 catalog       = wahrecon
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
	private Item itemId;
				/** ItemCategory
	data for the foreign class :
	
 --------- item_category.item_category_id --------- 
 schema        = null
 tableName     = item_category
 foreignCol    = item_category_id
 foreignTab    = item_category_map
 catalog       = wahrecon
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
	private ItemCategory itemCategoryId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="item_category_map_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getItemCategoryMapId(){
		return itemCategoryMapId;
	}
	public void setItemCategoryMapId(java.lang.Integer value){
		itemCategoryMapId = value;
	}
			// PK GETTER SETTER END

												@Column(name="is_active")
	public java.lang.Integer getIsActive(){
		return isActive;
	}
	public void setIsActive(java.lang.Integer value){
		isActive = value;
	}
					
	// foreign affairs
	
			/** CaseCategory */
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId(){
		return this.caseCategoryId;
	}

	/** CaseCategory */
	public void setCaseCategoryId(CaseCategory obj){
		this.caseCategoryId = obj;
	}
				/** Item */
	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId(){
		return this.itemId;
	}

	/** Item */
	public void setItemId(Item obj){
		this.itemId = obj;
	}
				/** ItemCategory */
	@ManyToOne
	@JoinColumn(name="item_category_id")
	public ItemCategory getItemCategoryId(){
		return this.itemCategoryId;
	}

	/** ItemCategory */
	public void setItemCategoryId(ItemCategory obj){
		this.itemCategoryId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}