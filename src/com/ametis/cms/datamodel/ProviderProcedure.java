
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;

import org.hibernate.annotations.Generated;


@Entity
@Table(name="provider_procedure")
public class ProviderProcedure implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- provider_procedure.provider_procedure_id --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Integer providerProcedureId;
			
	/**data for the column :
	
 --------- provider_procedure.provider_id --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Provider providerId;
			
	/**data for the column :
	
 --------- provider_procedure.procedure_id --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Procedure procedureId;
			
	/**data for the column :
	
 --------- provider_procedure.average_length_of_stay --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer averageLengthOfStay;
			
	/**data for the column :
	
 --------- provider_procedure.average_cost --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double averageCost;
			
	/**data for the column :
	
 --------- provider_procedure.version_date --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date versionDate;
			
	/**data for the column :
	
 --------- provider_procedure.status --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer status;
			
	/**data for the column :
	
 --------- provider_procedure.created_time --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 8
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- provider_procedure.created_by --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String createdBy;
			
	/**data for the column :
	
 --------- provider_procedure.modified_time --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 10
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- provider_procedure.modified_by --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String modifiedBy;
			
	/**data for the column :
	
 --------- provider_procedure.deleted_time --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 12
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- provider_procedure.deleted_by --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 13
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- provider_procedure.deleted_status --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 14
 size          = 2
 type          = -6 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- provider_procedure.procedure_class --------- 
 schema        = null
 tableName     = provider_procedure
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private TreatmentClass procedureClass;
	private TreatmentRisk treatmentRisk;
	private Double c1;
	private Double c2;
	private Double c3;
	private Double vip;
	private Double svip;
	private Double rj;
	
	private String description;

	private String url;

	private String type;

	private Integer statusUpdate;

	private String uploadNote;

	private Client clientId;

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
			
		
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="provider_procedure_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getProviderProcedureId(){
		return providerProcedureId;
	}
	public void setProviderProcedureId(java.lang.Integer value){
		providerProcedureId = value;
	}
			// PK GETTER SETTER END

	@ManyToOne
	@JoinColumn(name="provider_id")
	public Provider getProviderId(){
		return providerId;
	}
	public void setProviderId(Provider value){
		providerId = value;
	}
	
	@ManyToOne
	@JoinColumn(name="procedure_id")
	public Procedure getProcedureId(){
		return procedureId;
	}
	public void setProcedureId(Procedure value){
		procedureId = value;
	}
				@Column(name="average_length_of_stay")
	public java.lang.Integer getAverageLengthOfStay(){
		return averageLengthOfStay;
	}
	public void setAverageLengthOfStay(java.lang.Integer value){
		averageLengthOfStay = value;
	}
				@Column(name="average_cost")
	public java.lang.Double getAverageCost(){
		return averageCost;
	}
	public void setAverageCost(java.lang.Double value){
		averageCost = value;
	}
				@Column(name="version_date")
	public java.sql.Date getVersionDate(){
		return versionDate;
	}
	public void setVersionDate(java.sql.Date value){
		versionDate = value;
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
				@Column(name="deleted_status")
	public java.lang.Integer getDeletedStatus(){
		return deletedStatus;
	}
	public void setDeletedStatus(java.lang.Integer value){
		deletedStatus = value;
	}
	@ManyToOne
	@JoinColumn(name="procedure_class")
	public TreatmentClass getProcedureClass(){
		return procedureClass;
	}
	public void setProcedureClass(TreatmentClass value){
		procedureClass = value;
	}
	@ManyToOne
	@JoinColumn(name="treatment_risk_id")
	public TreatmentRisk getTreatmentRisk() {
		return treatmentRisk;
	}
	public void setTreatmentRisk(TreatmentRisk treatmentRisk) {
		this.treatmentRisk = treatmentRisk;
	}
	@Column(name="c1")
	public Double getC1() {
		return c1;
	}
	public void setC1(Double c1) {
		this.c1 = c1;
	}
	@Column(name="c2")
	public Double getC2() {
		return c2;
	}
	public void setC2(Double c2) {
		this.c2 = c2;
	}
	@Column(name="c3")
	public Double getC3() {
		return c3;
	}
	public void setC3(Double c3) {
		this.c3 = c3;
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
	@Column(name="rj")
	public Double getRj() {
		return rj;
	}
	public void setRj(Double rj) {
		this.rj = rj;
	}
	
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}
	public void setClientId(Client clientId) {
		this.clientId = clientId;
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
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



	
}