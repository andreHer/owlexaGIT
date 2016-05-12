
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="broker")
public class Broker implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- broker.broker_id --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private Integer brokerId;
			
	/**data for the column :
	
 --------- broker.broker_name --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String brokerName;
			
	/**data for the column :
	
 --------- broker.broker_code --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String brokerCode;
			
	/**data for the column :
	
 --------- broker.address --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
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
	private String address;
			
	/**data for the column :
	
 --------- broker.city --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String city;
			
	/**data for the column :
	
 --------- broker.province --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String province;
			
	/**data for the column :
	
 --------- broker.country --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String country;
			
	/**data for the column :
	
 --------- broker.zipcode --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String zipcode;
			
	/**data for the column :
	
 --------- broker.telephone --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 20
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String telephone;
			
	/**data for the column :
	
 --------- broker.faximile --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 20
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String faximile;
			
	/**data for the column :
	
 --------- broker.broker_type --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer brokerType;
			
	/**data for the column :
	
 --------- broker.status --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- broker.created_time --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- broker.created_by --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- broker.deleted_status --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- broker.deleted_by --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- broker.deleted_time --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- broker.modified_time --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- broker.modified_by --------- 
 schema        = null
 tableName     = broker
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
			/** Quotation
	data for the exported class :
	
 --------- quotation.broker_id --------- 
 schema        = null
 tableName     = quotation
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 34
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = broker.broker_id

=========================================



	 */
	private Set <Quotation> quotations = new HashSet(0);
	private RefCity cityId;
	private RefCountry countryId;
	private RefProvince provinceId;
	private String longitude;
	private String latitude;
						
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="broker_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getBrokerId(){
		return brokerId;
	}
	public void setBrokerId(java.lang.Integer value){
		brokerId = value;
	}
			// PK GETTER SETTER END

						@Column(name="broker_name")
	public java.lang.String getBrokerName(){
		return brokerName;
	}
	public void setBrokerName(java.lang.String value){
		brokerName = value;
	}
				@Column(name="broker_code")
	public java.lang.String getBrokerCode(){
		return brokerCode;
	}
	public void setBrokerCode(java.lang.String value){
		brokerCode = value;
	}
				@Column(name="address")
	public java.lang.String getAddress(){
		return address;
	}
	public void setAddress(java.lang.String value){
		address = value;
	}
				@Column(name="city")
	public java.lang.String getCity(){
		return city;
	}
	public void setCity(java.lang.String value){
		city = value;
	}
				@Column(name="province")
	public java.lang.String getProvince(){
		return province;
	}
	public void setProvince(java.lang.String value){
		province = value;
	}
				@Column(name="country")
	public java.lang.String getCountry(){
		return country;
	}
	public void setCountry(java.lang.String value){
		country = value;
	}
				@Column(name="zipcode")
	public java.lang.String getZipcode(){
		return zipcode;
	}
	public void setZipcode(java.lang.String value){
		zipcode = value;
	}
				@Column(name="telephone")
	public java.lang.String getTelephone(){
		return telephone;
	}
	public void setTelephone(java.lang.String value){
		telephone = value;
	}
				@Column(name="faximile")
	public java.lang.String getFaximile(){
		return faximile;
	}
	public void setFaximile(java.lang.String value){
		faximile = value;
	}
				@Column(name="broker_type")
	public java.lang.Integer getBrokerType(){
		return brokerType;
	}
	public void setBrokerType(java.lang.Integer value){
		brokerType = value;
	}
				@Column(name="status")
	public java.lang.Integer getStatus(){
		return status;
	}
	public void setStatus(java.lang.Integer value){
		status = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
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
		
	// foreign affairs
	
		
	// foreign affairs end

// exported key

	
			/** Quotation */
	@OneToMany(mappedBy="brokerId")
	public Set<Quotation> getQuotations(){
		return this.quotations;
	}

	/** Quotation */
	public void setQuotations(Set<Quotation> obj){
		this.quotations = obj;
	}
	@ManyToOne
	@JoinColumn(name="city_id")
	public RefCity getCityId() {
		return cityId;
	}
	public void setCityId(RefCity cityId) {
		this.cityId = cityId;
	}
	@ManyToOne
	@JoinColumn(name="country_id")
	public RefCountry getCountryId() {
		return countryId;
	}
	public void setCountryId(RefCountry countryId) {
		this.countryId = countryId;
	}
	@ManyToOne
	@JoinColumn(name="province_id")
	public RefProvince getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(RefProvince provinceId) {
		this.provinceId = provinceId;
	}
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
						
	//exported key end



}