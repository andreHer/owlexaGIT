
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="most_frequent_item")
public class MostFrequentItem implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- most_frequent_item.most_frequent_item_id --------- 
 schema        = null
 tableName     = most_frequent_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = -5 
 isPrimaryKey  = true

=========================================


*/
	private Long mostFrequentItemId;
			
	/**data for the column :
	
 --------- most_frequent_item.item_id --------- 
 schema        = null
 tableName     = most_frequent_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private Item itemId;
			
	/**data for the column :
	
 --------- most_frequent_item.case_category_id --------- 
 schema        = null
 tableName     = most_frequent_item
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private CaseCategory caseCategoryId;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="most_frequent_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Long getMostFrequentItemId(){
		return mostFrequentItemId;
	}
	public void setMostFrequentItemId(java.lang.Long value){
		mostFrequentItemId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="item_id")
	public Item getItemId(){
		return itemId;
	}
	public void setItemId(Item value){
		itemId = value;
	}
	@ManyToOne
	@JoinColumn(name="case_category_id")
	public CaseCategory getCaseCategoryId(){
		return caseCategoryId;
	}
	public void setCaseCategoryId(CaseCategory value){
		caseCategoryId = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}