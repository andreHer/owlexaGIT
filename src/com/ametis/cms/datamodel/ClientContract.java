
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
@Table(name="client_contract")
public class ClientContract implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int MONTHLY = 1;
	public static final int QUARTERLY = 2;
	public static final int SEMESTER = 3;
	public static final int ANNUALY = 4;

	public static final int PRORATE_MONTHLY = 1;
	public static final int PRORATE_QUARTERLY = 2;
	public static final int PRORATE_SEMESTER = 3;
	public static final int PRORATE_NONE = -1;
	//Fields
		
	/**data for the column : 
 --------- client_contract.client_contract_id --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = auto_increment
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
	private Integer clientContractId;
			
	/**data for the column :
	
 --------- client_contract.client_contract_number --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String clientContractNumber;
									
	/**data for the column :
	
 --------- client_contract.contract_value_per_member --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private Double contractValuePerMember;
			
	/**data for the column :
	
 --------- client_contract.total_contract_value --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 6
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double totalContractValue;
			
	/**data for the column :
	
 --------- client_contract.is_using_proration --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 7
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isUsingProration;
	private Integer isUsingVolumeLevel;
			
	/**data for the column :
	
 --------- client_contract.payment_periode --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer paymentPeriode;
			
	/**data for the column :
	
 --------- client_contract.contract_start_date --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 9
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date contractStartDate;
			
	/**data for the column :
	
 --------- client_contract.contract_end_date --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date contractEndDate;
			
	/**data for the column :
	
 --------- client_contract.total_member --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 10
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalMember;
			
	/**data for the column :
	
 --------- client_contract.description --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 12
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String description;
			
	/**data for the column :
	
 --------- client_contract.created_time --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = on update CURRENT_TIMESTAMP
 defaultValue  = CURRENT_TIMESTAMP
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 13
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp createdTime;
			
	/**data for the column :
	
 --------- client_contract.created_by --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	
 --------- client_contract.modified_time --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- client_contract.modified_by --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- client_contract.deleted_time --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = 0000-00-00 00:00:00
 decDigits     = 0
 radix         = 10
 nullable      = 0
 ordinal       = 17
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp deletedTime;
			
	/**data for the column :
	
 --------- client_contract.deleted_by --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String deletedBy;
			
	/**data for the column :
	
 --------- client_contract.deleted_status --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer deletedStatus;
			
	/**data for the column :
	
 --------- client_contract.contract_status --------- 
 schema        = null
 tableName     = client_contract
 foreignCol    = 
 foreignTab    = 
 catalog       = insura-lintas
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer contractStatus;
		
	// foreign affairs
	
			/** ContractType
	data for the foreign class :
	
 --------- contract_type.contract_type_id --------- 
 schema        = null
 tableName     = contract_type
 foreignCol    = contract_type_id
 foreignTab    = client_contract
 catalog       = insura-lintas
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
	private ContractType contractTypeId;
				/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = client_contract
 catalog       = insura-lintas
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
	private Currency currencyId;
		
	private Double discountPercentage;
	private Double discountAmountPerQuantity;
	private Integer billingCutOffDate;
	private Integer invoiceDueLength;
	private ProductType productTypeId;
	private Integer membershipPeriode;
	private Integer prorateType;
	private Integer isRefundAvailable;
	
	@Id
	@Column(name="client_contract_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public java.lang.Integer getClientContractId(){
		return clientContractId;
	}
	public void setClientContractId(java.lang.Integer value){
		clientContractId = value;
	}
			// PK GETTER SETTER END

						@Column(name="client_contract_number")
	public java.lang.String getClientContractNumber(){
		return clientContractNumber;
	}
	public void setClientContractNumber(java.lang.String value){
		clientContractNumber = value;
	}
										@Column(name="contract_value_per_member")
	public java.lang.Double getContractValuePerMember(){
		return contractValuePerMember;
	}
	public void setContractValuePerMember(java.lang.Double value){
		contractValuePerMember = value;
	}
				@Column(name="total_contract_value")
	public java.lang.Double getTotalContractValue(){
		return totalContractValue;
	}
	public void setTotalContractValue(java.lang.Double value){
		totalContractValue = value;
	}
				@Column(name="is_using_proration")
	public java.lang.Integer getIsUsingProration(){
		return isUsingProration;
	}
	public void setIsUsingProration(java.lang.Integer value){
		isUsingProration = value;
	}
				@Column(name="payment_periode")
	public java.lang.Integer getPaymentPeriode(){
		return paymentPeriode;
	}
	public void setPaymentPeriode(java.lang.Integer value){
		paymentPeriode = value;
	}
				@Column(name="contract_start_date")
	public java.sql.Date getContractStartDate(){
		return contractStartDate;
	}
	public void setContractStartDate(java.sql.Date value){
		contractStartDate = value;
	}
				@Column(name="contract_end_date")
	public java.sql.Date getContractEndDate(){
		return contractEndDate;
	}
	public void setContractEndDate(java.sql.Date value){
		contractEndDate = value;
	}
				@Column(name="total_member")
	public java.lang.Integer getTotalMember(){
		return totalMember;
	}
	public void setTotalMember(java.lang.Integer value){
		totalMember = value;
	}
				@Column(name="description")
	public java.lang.String getDescription(){
		return description;
	}
	public void setDescription(java.lang.String value){
		description = value;
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
				@Column(name="contract_status")
	public java.lang.Integer getContractStatus(){
		return contractStatus;
	}
	public void setContractStatus(java.lang.Integer value){
		contractStatus = value;
	}
		
	// foreign affairs
	
			/** ContractType */
	@ManyToOne
	@JoinColumn(name="contract_type_id")
	public ContractType getContractTypeId(){
		return this.contractTypeId;
	}

	/** ContractType */
	public void setContractTypeId(ContractType obj){
		this.contractTypeId = obj;
	}
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
	@Column(name="discount_percentage")
	public Double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	@Column(name="discount_amount_per_quantity")
	public Double getDiscountAmountPerQuantity() {
		return discountAmountPerQuantity;
	}
	public void setDiscountAmountPerQuantity(Double discountAmountPerQuantity) {
		this.discountAmountPerQuantity = discountAmountPerQuantity;
	}
	@Column(name="billing_cut_off_date")
	public Integer getBillingCutOffDate() {
		return billingCutOffDate;
	}
	public void setBillingCutOffDate(Integer billingCutOffDate) {
		this.billingCutOffDate = billingCutOffDate;
	}
	@Column(name="is_using_volume_level")
	public Integer getIsUsingVolumeLevel() {
		return isUsingVolumeLevel;
	}
	public void setIsUsingVolumeLevel(Integer isUsingVolumeLevel) {
		this.isUsingVolumeLevel = isUsingVolumeLevel;
	}
	@Column(name="invoice_due_length")
	public Integer getInvoiceDueLength() {
		return invoiceDueLength;
	}
	public void setInvoiceDueLength(Integer invoiceDueLength) {
		this.invoiceDueLength = invoiceDueLength;
	}
	@ManyToOne
	@JoinColumn(name="product_type_id")
	public ProductType getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(ProductType productTypeId) {
		this.productTypeId = productTypeId;
	}
	@ManyToOne
	@JoinColumn(name="currency_id")
	public Currency getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(Currency currencyId) {
		this.currencyId = currencyId;
	}
	@Column(name="membership_periode")
	public Integer getMembershipPeriode() {
		return membershipPeriode;
	}
	public void setMembershipPeriode(Integer membershipPeriode) {
		this.membershipPeriode = membershipPeriode;
	}
	@Column(name="prorate_type")
	public Integer getProrateType() {
		return prorateType;
	}
	public void setProrateType(Integer prorateType) {
		this.prorateType = prorateType;
	}
	@Column(name="is_refund_available")
	public Integer getIsRefundAvailable() {
		return isRefundAvailable;
	}
	public void setIsRefundAvailable(Integer isRefundAvailable) {
		this.isRefundAvailable = isRefundAvailable;
	}
			
	
	

	

}