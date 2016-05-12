
package com.ametis.cms.datamodel;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="insurance_package")
public class InsurancePackage implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	//Fields
		
	/**data for the column :
	
 --------- insurance_package.package_id --------- 
 schema        = null
 tableName     = insurance_package
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
	private Integer packageId;
			
	/**data for the column :
	
 --------- insurance_package.package_name --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String packageName;
			
	/**data for the column :
	
 --------- insurance_package.package_code --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String packageCode;
	private String policyNumber;
	private Double newBusinessPremium;
	private Double movementPremium;
	private Double refundPremium;
	private Broker brokerId;
	private MemberGroup memberGroupId;
	private Date startDate;
	private Date expireDate;
	private SubscriptionStatus status;
	private Integer closingSource;
	private Date closingDate;
	
			
	/**data for the column :
	
 --------- insurance_package.annual_benefit_value --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 4
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double annualBenefitValue;
			
	/**data for the column :
	
 --------- insurance_package.package_description --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 5
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String packageDescription;
			
	/**data for the column :
	
 --------- insurance_package.package_status --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer packageStatus;
									
	/**data for the column :
	
 --------- insurance_package.created_time --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- insurance_package.created_by --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String createdBy;
			
	/**data for the column :
	
 --------- insurance_package.deleted_time --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- insurance_package.deleted_by --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- insurance_package.modified_time --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- insurance_package.modified_by --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- insurance_package.deleted_status --------- 
 schema        = null
 tableName     = insurance_package
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 11
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
		
	// foreign affairs
	
			/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = insurance_package
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
	private Client clientId;
				/** ProductType
	data for the foreign class :
	
 --------- product_type.product_type_id --------- 
 schema        = null
 tableName     = product_type
 foreignCol    = package_type
 foreignTab    = insurance_package
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
	private ProductType packageType;
			
	// -- foreign affairs end

	// -- exported key

	
			/** GroupPackage
	data for the exported class :
	
 --------- group_package.package_id --------- 
 schema        = null
 tableName     = group_package
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
 this columns points to  = insurance_package.package_id

=========================================



	 */
	private Set <GroupPackage> groupPackages = new HashSet(0);
				/** MemberPackage
	data for the exported class :
	
 --------- member_package.package_id --------- 
 schema        = null
 tableName     = member_package
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
 this columns points to  = insurance_package.package_id

=========================================



	 */
	private Set <MemberPackage> memberPackages = new HashSet(0);
				/** ProductPackage
	data for the exported class :
	
 --------- product_package.package_id --------- 
 schema        = null
 tableName     = product_package
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
 this columns points to  = insurance_package.package_id

=========================================



	 */
	private Set <ProductPackage> productPackages = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="package_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getPackageId(){
		return packageId;
	}
	public void setPackageId(java.lang.Integer value){
		packageId = value;
	}
			// PK GETTER SETTER END

						@Column(name="package_name")
	public java.lang.String getPackageName(){
		return packageName;
	}
	public void setPackageName(java.lang.String value){
		packageName = value;
	}
				@Column(name="package_code")
	public java.lang.String getPackageCode(){
		return packageCode;
	}
	public void setPackageCode(java.lang.String value){
		packageCode = value;
	}
				@Column(name="annual_benefit_value")
	public java.lang.Double getAnnualBenefitValue(){
		return annualBenefitValue;
	}
	public void setAnnualBenefitValue(java.lang.Double value){
		annualBenefitValue = value;
	}
				@Column(name="package_description")
	public java.lang.String getPackageDescription(){
		return packageDescription;
	}
	public void setPackageDescription(java.lang.String value){
		packageDescription = value;
	}
				@Column(name="package_status")
	public java.lang.Integer getPackageStatus(){
		return packageStatus;
	}
	public void setPackageStatus(java.lang.Integer value){
		packageStatus = value;
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
		
	// foreign affairs
	
			/** Client */
	@ManyToOne
	@JoinColumn(name="client_id")
	public Client getClientId(){
		return this.clientId;
	}

	/** Client */
	public void setClientId(Client obj){
		this.clientId = obj;
	}
				/** ProductType */
	@ManyToOne
	@JoinColumn(name="package_type")
	public ProductType getPackageType(){
		return this.packageType;
	}

	/** ProductType */
	public void setPackageType(ProductType obj){
		this.packageType = obj;
	}
			
	// foreign affairs end

// exported key

	
			/** GroupPackage */
	@OneToMany(mappedBy="packageId")
	public Set<GroupPackage> getGroupPackages(){
		return this.groupPackages;
	}

	/** GroupPackage */
	public void setGroupPackages(Set<GroupPackage> obj){
		this.groupPackages = obj;
	}
				/** MemberPackage */
	@OneToMany(mappedBy="packageId")
	public Set<MemberPackage> getMemberPackages(){
		return this.memberPackages;
	}

	/** MemberPackage */
	public void setMemberPackages(Set<MemberPackage> obj){
		this.memberPackages = obj;
	}
				/** ProductPackage */
	@OneToMany(mappedBy="packageId")
	public Set<ProductPackage> getProductPackages(){
		return this.productPackages;
	}

	/** ProductPackage */
	public void setProductPackages(Set<ProductPackage> obj){
		this.productPackages = obj;
	}
	@Column(name="policy_number")
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	@Column(name="new_business_premium")
	public Double getNewBusinessPremium() {
		return newBusinessPremium;
	}
	public void setNewBusinessPremium(Double newBusinessPremium) {
		this.newBusinessPremium = newBusinessPremium;
	}
	@Column(name="movement_premium")
	public Double getMovementPremium() {
		return movementPremium;
	}
	public void setMovementPremium(Double movementPremium) {
		this.movementPremium = movementPremium;
	}
	@Column(name="refund_premium")
	public Double getRefundPremium() {
		return refundPremium;
	}
	public void setRefundPremium(Double refundPremium) {
		this.refundPremium = refundPremium;
	}
	@ManyToOne
	@JoinColumn(name="broker_id")
	public Broker getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Broker brokerId) {
		this.brokerId = brokerId;
	}
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId() {
		return memberGroupId;
	}
	public void setMemberGroupId(MemberGroup memberGroupId) {
		this.memberGroupId = memberGroupId;
	}
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(name="expire_date")
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	@ManyToOne
	@JoinColumn(name="status_id")
	public SubscriptionStatus getStatus() {
		return status;
	}
	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}
	@Column(name="closing_source")
	public Integer getClosingSource() {
		return closingSource;
	}
	public void setClosingSource(Integer closingSource) {
		this.closingSource = closingSource;
	}
	@Column(name="closing_date")
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	
	
			
	//exported key end



}