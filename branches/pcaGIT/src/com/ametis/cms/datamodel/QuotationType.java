
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="quotation_type")
public class QuotationType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- quotation_type.quotation_type_id --------- 
 schema        = null
 tableName     = quotation_type
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
	private Integer quotationTypeId;
			
	/**data for the column :
	
 --------- quotation_type.description --------- 
 schema        = null
 tableName     = quotation_type
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
	
 --------- quotation_type.quotation_type_name --------- 
 schema        = null
 tableName     = quotation_type
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
	private String quotationTypeName;
			
	/**data for the column :
	
 --------- quotation_type.is_default --------- 
 schema        = null
 tableName     = quotation_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isDefault;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="quotation_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getQuotationTypeId(){
		return quotationTypeId;
	}
	public void setQuotationTypeId(java.lang.Integer value){
		quotationTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="quotation_type_name")
	public java.lang.String getQuotationTypeName(){
		return quotationTypeName;
	}
	public void setQuotationTypeName(java.lang.String value){
		quotationTypeName = value;
	}
				@Column(name="is_default")
	public java.lang.Integer getIsDefault(){
		return isDefault;
	}
	public void setIsDefault(java.lang.Integer value){
		isDefault = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}