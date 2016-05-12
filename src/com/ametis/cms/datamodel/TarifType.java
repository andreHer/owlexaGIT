
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="tarif_type")
public class TarifType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	
	public static final int GENERAL = 1;
	public static final int GENERAL_CLIENT = 2;
	public static final int GENERAL_POLICY = 3;
	public static final int GENERAL_MEMBER_GROUP = 4;
	public static final int PROVIDER_CLIENT = 5;
	public static final int PROVIDER_POLICY = 6;
	public static final int PROVIDER_MEMBER_GROUP = 7;
	//Fields
		
	/**data for the column :
	
 --------- tarif_type.tarif_type_id --------- 
 schema        = public
 tableName     = tarif_type
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private Integer tarifTypeId;
			
	/**data for the column :
	
 --------- tarif_type.tarif_type_name --------- 
 schema        = public
 tableName     = tarif_type
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
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
	private String tarifTypeName;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="tarif_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getTarifTypeId(){
		return tarifTypeId;
	}
	public void setTarifTypeId(java.lang.Integer value){
		tarifTypeId = value;
	}
			// PK GETTER SETTER END

						@Column(name="tarif_type_name")
	public java.lang.String getTarifTypeName(){
		return tarifTypeName;
	}
	public void setTarifTypeName(java.lang.String value){
		tarifTypeName = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}