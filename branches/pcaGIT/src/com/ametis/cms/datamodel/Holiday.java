
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="holiday")
public class Holiday implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- holiday.id_holiday --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = nextval('holiday_id_holiday_seq'::regclass)
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 1
 size          = 10
 type          = 4 
 isPrimaryKey  = true

=========================================


*/
	private Integer idHoliday;
			
	/**data for the column :
	
 --------- holiday.tgl_holiday --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 13
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date tglHoliday;
			
	/**data for the column :
	
 --------- holiday.description --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 255
 type          = 1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- holiday.created_by --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 1
 type          = 1 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- holiday.modified_by --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 1
 type          = 1 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- holiday.deleted_status --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- holiday.modified_time --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- holiday.created_time --------- 
 schema        = public
 tableName     = holiday
 foreignCol    = 
 foreignTab    = 
 catalog       = null
 remarks       = null
 defaultValue  = null
 decDigits     = 6
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 29
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id_holiday")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getIdHoliday(){
		return idHoliday;
	}
	public void setIdHoliday(java.lang.Integer value){
		idHoliday = value;
	}
			// PK GETTER SETTER END

						@Column(name="tgl_holiday")
	public java.sql.Date getTglHoliday(){
		return tglHoliday;
	}
	public void setTglHoliday(java.sql.Date value){
		tglHoliday = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
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
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
		
	//exported key end



}