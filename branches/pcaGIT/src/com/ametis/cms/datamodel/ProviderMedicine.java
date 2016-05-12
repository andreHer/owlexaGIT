
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_medicine")
public class ProviderMedicine implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_medicine.provider_medicine_id --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer providerMedicineId;
			
	/**data for the column :
	
 --------- provider_medicine.deleted_status --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- provider_medicine.modified_time --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_medicine.modified_by --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- provider_medicine.deleted_by --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private String deletedBy;
			
	/**data for the column :
	
 --------- provider_medicine.deleted_time --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	
 --------- provider_medicine.created_time --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
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
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- provider_medicine.created_by --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- provider_medicine.item_value --------- 
 schema        = null
 tableName     = provider_medicine
 foreignCol    = 
 foreignTab    = 
 catalog       = healthcare
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double itemValue;
								
	// foreign affairs
	
			/** Provider
	data for the foreign class :
	
 --------- provider.provider_id --------- 
 schema        = null
 tableName     = provider
 foreignCol    = provider_id
 foreignTab    = provider_medicine
 catalog       = healthcare
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
	private Provider providerId;
				/** Medicine
	data for the foreign class :
	
 --------- medicine.medicine_id --------- 
 schema        = null
 tableName     = medicine
 foreignCol    = medicine_id
 foreignTab    = provider_medicine
 catalog       = healthcare
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
	private Medicine medicineId;
	private Client clientId;
	private String description;
	private Double referencePrice;

	private String url;

	private String type;

	private Integer statusUpdate;

	private String uploadNote;
	private Double kelas1;
	private Double kelas2;
	private Double kelas3;
	private Double vip;
	private Double svip;
	private Double rj;

	private Policy policyId;

	private MemberGroup memberGroupId;

	private Double marginKelas1;
    
    private Double marginKelas2;

    private Double marginKelas3;
    
    private Double marginSVIP;
    
    private Double marginVIP;
    
    private Double marginRJ;
    
    private Double diskonKelas1;
    
    private Double diskonKelas2;
    
    private Double diskonKelas3;
    
    private Double diskonSVIP;
    
    private Double diskonVIP;
    
    private Double diskonRJ;
			
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_medicine_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProviderMedicineId(){
		return providerMedicineId;
	}
	public void setProviderMedicineId(java.lang.Integer value){
		providerMedicineId = value;
	}
			// PK GETTER SETTER END

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
				@Column(name="item_value")
	public java.lang.Double getItemValue(){
		return itemValue;
	}
	public void setItemValue(java.lang.Double value){
		itemValue = value;
	}
								
	// foreign affairs
	
			/** Provider */
	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return this.providerId;
	}

	/** Provider */
	public void setProviderId(Provider obj){
		this.providerId = obj;
	}
				/** Medicine */
	@ManyToOne
	@JoinColumn(name="medicine_id")
	public Medicine getMedicineId(){
		return this.medicineId;
	}

	/** Medicine */
	public void setMedicineId(Medicine obj){
		this.medicineId = obj;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="reference_price")
	public Double getReferencePrice() {
		return referencePrice;
	}
	public void setReferencePrice(Double referencePrice) {
		this.referencePrice = referencePrice;
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
	@Column(name="kelas1")
	public Double getKelas1() {
		return kelas1;
	}
	public void setKelas1(Double kelas1) {
		this.kelas1 = kelas1;
	}
	@Column(name="kelas2")
	public Double getKelas2() {
		return kelas2;
	}
	public void setKelas2(Double kelas2) {
		this.kelas2 = kelas2;
	}
	@Column(name="kelas3")
	public Double getKelas3() {
		return kelas3;
	}
	public void setKelas3(Double kelas3) {
		this.kelas3 = kelas3;
	}
	@Column(name="vip")
	public Double getVip() {
		return vip;
	}
	public void setVip(Double vip) {
		this.vip = vip;
	}
	@Column(name="svip")
	public Double getSvip() {
		return svip;
	}
	public void setSvip(Double svip) {
		this.svip = svip;
	}
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
	
		this.clientId = clientId;
	}
	@Column(name="rj")
	public Double getRj() {
		return rj;
	}
	public void setRj(Double rj) {
		this.rj = rj;
	}

	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}

	
	@Column(name="margin_kelas1")
    public Double getMarginKelas1() {
		return marginKelas1;
	}
	public void setMarginKelas1(Double marginKelas1) {
		this.marginKelas1 = marginKelas1;
	}
	
	@Column(name="margin_kelas2")
	public Double getMarginKelas2() {
		return marginKelas2;
	}
	public void setMarginKelas2(Double marginKelas2) {
		this.marginKelas2 = marginKelas2;
	}
	
	@Column(name="margin_kelas3")
	public Double getMarginKelas3() {
		return marginKelas3;
	}
	public void setMarginKelas3(Double marginKelas3) {
		this.marginKelas3 = marginKelas3;
	}
	
	@Column(name="margin_svip")
	public Double getMarginSVIP() {
		return marginSVIP;
	}
	public void setMarginSVIP(Double marginSVIP) {
		this.marginSVIP = marginSVIP;
	}
	
	@Column(name="margin_vip")
	public Double getMarginVIP() {
		return marginVIP;
	}
	public void setMarginVIP(Double marginVIP) {
		this.marginVIP = marginVIP;
	}
	
	@Column(name="margin_rj")
	public Double getMarginRJ() {
		return marginRJ;
	}
	public void setMarginRJ(Double marginRJ) {
		this.marginRJ = marginRJ;
	}
	
	@Column(name="diskon_kelas1")
	public Double getDiskonKelas1() {
		return diskonKelas1;
	}
	public void setDiskonKelas1(Double diskonKelas1) {
		this.diskonKelas1 = diskonKelas1;
	}
	
	@Column(name="diskon_kelas2")
	public Double getDiskonKelas2() {
		return diskonKelas2;
	}
	public void setDiskonKelas2(Double diskonKelas2) {
		this.diskonKelas2 = diskonKelas2;
	}
	
	@Column(name="diskon_kelas3")
	public Double getDiskonKelas3() {
		return diskonKelas3;
	}
	public void setDiskonKelas3(Double diskonKelas3) {
		this.diskonKelas3 = diskonKelas3;
	}
	
	@Column(name="diskon_svip")
	public Double getDiskonSVIP() {
		return diskonSVIP;
	}
	public void setDiskonSVIP(Double diskonSVIP) {
		this.diskonSVIP = diskonSVIP;
	}
	
	@Column(name="diskon_vip")
	public Double getDiskonVIP() {
		return diskonVIP;
	}
	public void setDiskonVIP(Double diskonVIP) {
		this.diskonVIP = diskonVIP;
	}
	
	@Column(name="diskon_rj")
	public Double getDiskonRJ() {
		return diskonRJ;
	}
	public void setDiskonRJ(Double diskonRJ) {
		this.diskonRJ = diskonRJ;
	}


}