
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="card_type")
public class CardType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;


	public static final int SHOW_CARD = 1;
	public static final int SWIPE_CARD = 2;
	public static final int SMART_CARD = 3;
	//Fields
		
	/**data for the column :
	
 --------- card_type.card_type_id --------- 
 schema        = null
 tableName     = card_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = auto_increment
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 5
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer cardTypeId;
			
	/**data for the column :
	
 --------- card_type.card_type_name --------- 
 schema        = null
 tableName     = card_type
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
	private String cardTypeName;
			
	/**data for the column :
	
 --------- card_type.card_type_code --------- 
 schema        = null
 tableName     = card_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 10
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String cardTypeCode;
			
	/**data for the column :
	
 --------- card_type.description --------- 
 schema        = null
 tableName     = card_type
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="card_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getCardTypeId(){
		return cardTypeId;
	}
	public void setCardTypeId(java.lang.Integer value){
		cardTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="card_type_name")
	public java.lang.String getCardTypeName(){
		return cardTypeName;
	}
	public void setCardTypeName(java.lang.String value){
		cardTypeName = value;
	}
				@Column(name="card_type_code")
	public java.lang.String getCardTypeCode(){
		return cardTypeCode;
	}
	public void setCardTypeCode(java.lang.String value){
		cardTypeCode = value;
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

	
		
	//exported key end



}