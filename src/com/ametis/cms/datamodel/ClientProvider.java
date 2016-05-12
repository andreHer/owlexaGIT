
package com.ametis.cms.datamodel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="client_provider")
public class ClientProvider implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int PAYMENT_CAPITATION = 1;
	public static final int PAYMENT_FFS = 2;
	public static final int PAYMENT_INDEMNITY = 3;

	//Fields
		
	/**data for the column :
	
 --------- client_provider.client_provider_id --------- 
 schema        = null
 tableName     = client_provider
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
	private Integer clientProviderId;
			
	/**data for the column :
	
 --------- client_provider.client_id --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 2
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Client clientId;
			
	/**data for the column :
	
 --------- client_provider.provider_id --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private Provider providerId;
			
	/**data for the column :
	
 --------- client_provider.created_time --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- client_provider.created_by --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- client_provider.deleted_time --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- client_provider.deleted_by --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- client_provider.modified_time --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- client_provider.modified_by --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- client_provider.deleted_status --------- 
 schema        = null
 tableName     = client_provider
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
	private ProviderSet providerSetId;
	private Integer mappingType;
	
	private Integer inpatient;
	private Integer outpatient;
	private Integer dental;
	private Integer optical;
	private Integer maternity;
	private Integer mcuLab;
	
	private Integer ppk1;
	private Integer ppk2;
	private Integer ppk3;

	private Integer paymentType;
	
	private String url;

	private String type;

	private Integer statusUpdate;

	private String uploadNote;
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="client_provider_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getClientProviderId(){
		return clientProviderId;
	}
	public void setClientProviderId(java.lang.Integer value){
		clientProviderId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return clientId;
	}
	public void setClientId(Client value){
		clientId = value;
	}
	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return providerId;
	}
	public void setProviderId(Provider value){
		providerId = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
	@ManyToOne
	@JoinColumn(name="provider_set_id")
	public ProviderSet getProviderSetId() {
		return providerSetId;
	}
	public void setProviderSetId(ProviderSet providerSetId) {
		this.providerSetId = providerSetId;
	}
	@Column(name="mapping_type")
	public Integer getMappingType() {
		return mappingType;
	}
	public void setMappingType(Integer mappingType) {
		this.mappingType = mappingType;
	}
		
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="status_update")
	public Integer getStatusUpdate() {
		return statusUpdate;
	}
	public void setStatusUpdate(Integer statusUpdate) {
		this.statusUpdate = statusUpdate;
	}
	@Column(name="upload_note")
	public String getUploadNote() {
		return uploadNote;
	}
	public void setUploadNote(String uploadNote) {
		this.uploadNote = uploadNote;
	}
	@Column(name="inpatient")
	public Integer getInpatient() {
		return inpatient;
	}
	public void setInpatient(Integer inpatient) {
		this.inpatient = inpatient;
	}
	@Column(name="outpatient")
	public Integer getOutpatient() {
		return outpatient;
	}
	public void setOutpatient(Integer outpatient) {
		this.outpatient = outpatient;
	}
	@Column(name="dental")
	public Integer getDental() {
		return dental;
	}
	public void setDental(Integer dental) {
		this.dental = dental;
	}
	@Column(name="optical")
	public Integer getOptical() {
		return optical;
	}
	public void setOptical(Integer optical) {
		this.optical = optical;
	}
	@Column(name="maternity")
	public Integer getMaternity() {
		return maternity;
	}
	public void setMaternity(Integer maternity) {
		this.maternity = maternity;
	}
	@Column(name="mcu_lab")
	public Integer getMcuLab() {
		return mcuLab;
	}
	public void setMcuLab(Integer mcuLab) {
		this.mcuLab = mcuLab;
	}
	@Column(name="ppk1")
	public Integer getPpk1() {
		return ppk1;
	}
	public void setPpk1(Integer ppk1) {
		this.ppk1 = ppk1;
	}
	@Column(name="ppk2")
	public Integer getPpk2() {
		return ppk2;
	}
	public void setPpk2(Integer ppk2) {
		this.ppk2 = ppk2;
	}
	@Column(name="ppk3")
	public Integer getPpk3() {
		return ppk3;
	}
	public void setPpk3(Integer ppk3) {
		this.ppk3 = ppk3;
	}
	@Column(name="payment_type")
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	

	
}