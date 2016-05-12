
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="ref_district")
public class RefDistrict implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- ref_district.id --------- 
 schema        = null
 tableName     = ref_district
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
	private Integer id;
			
	/**data for the column :
	
 --------- ref_district.description --------- 
 schema        = null
 tableName     = ref_district
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
	
 --------- ref_district.deleted_status --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- ref_district.modified_time --------- 
 schema        = null
 tableName     = ref_district
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- ref_district.modified_by --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- ref_district.deleted_by --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- ref_district.deleted_time --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- ref_district.created_time --------- 
 schema        = null
 tableName     = ref_district
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- ref_district.created_by --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- ref_district.longitude --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String longitude;
			
	/**data for the column :
	
 --------- ref_district.latitude --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String latitude;
			
	/**data for the column :
	
 --------- ref_district.district_name --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String districtName;
			
	/**data for the column :
	
 --------- ref_district.district_code --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String districtCode;
			
	/**data for the column :
	
 --------- ref_district.city_name --------- 
 schema        = null
 tableName     = ref_district
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-master
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String cityName;
					
	// foreign affairs
	
			/** RefCity
	data for the foreign class :
	
 --------- ref_city.id --------- 
 schema        = null
 tableName     = ref_city
 foreignCol    = city_id
 foreignTab    = ref_district
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
	private RefCity cityId;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getId(){
		return id;
	}
	public void setId(java.lang.Integer value){
		id = value;
	}
			// PK GETTER SETTER END

						@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="deleted_by")
	public java.lang.String getDeletedBy(){
		return deletedBy;
	}
	public void setDeletedBy(java.lang.String value){
		deletedBy = value;
	}
				@Column(name="deleted_time")
	public java.sql.Timestamp getDeletedTime(){
		return deletedTime;
	}
	public void setDeletedTime(java.sql.Timestamp value){
		deletedTime = value;
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
				@Column(name="longitude")
	public java.lang.String getLongitude(){
		return longitude;
	}
	public void setLongitude(java.lang.String value){
		longitude = value;
	}
				@Column(name="latitude")
	public java.lang.String getLatitude(){
		return latitude;
	}
	public void setLatitude(java.lang.String value){
		latitude = value;
	}
				@Column(name="district_name")
	public java.lang.String getDistrictName(){
		return districtName;
	}
	public void setDistrictName(java.lang.String value){
		districtName = value;
	}
				@Column(name="district_code")
	public java.lang.String getDistrictCode(){
		return districtCode;
	}
	public void setDistrictCode(java.lang.String value){
		districtCode = value;
	}
				@Column(name="city_name")
	public java.lang.String getCityName(){
		return cityName;
	}
	public void setCityName(java.lang.String value){
		cityName = value;
	}
					
	// foreign affairs
	
			/** RefCity */
	@ManyToOne
	@JoinColumn(name="city_id")
	public RefCity getCityId(){
		return this.cityId;
	}

	/** RefCity */
	public void setCityId(RefCity obj){
		this.cityId = obj;
	}
			
	// foreign affairs end

// exported key

	
		
	//exported key end



}