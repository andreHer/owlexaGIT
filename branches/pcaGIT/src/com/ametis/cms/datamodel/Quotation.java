
package com.ametis.cms.datamodel;


import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="quotation")
public class Quotation implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	public static final int QUOTATION_TYPE_NEW = 1;
	public static final int QUOTATION_TYPE_RENEWAL = 2;
	
	//Fields
		
	/**data for the column :
	
 --------- quotation.quotation_id --------- 
 schema        = null
 tableName     = quotation
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
	private Integer quotationId;
			
	/**data for the column :
	
 --------- quotation.quotation_no --------- 
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
 ordinal       = 2
 size          = 100
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String quotationNo;
			
	/**data for the column :
	
 --------- quotation.quotation_date --------- 
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
 ordinal       = 3
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date quotationDate;
			
	/**data for the column :
	
 --------- quotation.request_date --------- 
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
 ordinal       = 4
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date requestDate;
						
	/**data for the column :
	
 --------- quotation.effective_date --------- 
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
 ordinal       = 6
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date effectiveDate;
			
	/**data for the column :
	
 --------- quotation.renewal_date --------- 
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
 ordinal       = 7
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date renewalDate;
			
	/**data for the column :
	
 --------- quotation.payment_mode --------- 
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
 ordinal       = 8
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private PaymentMode paymentMode;
			
	/**data for the column :
	
 --------- quotation.remarks --------- 
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
 ordinal       = 9
 size          = 65535
 type          = -1 
 isPrimaryKey  = false

=========================================


*/
	private String remarks;
			
	/**data for the column :
	
 --------- quotation.quotation_type_id --------- 
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
 ordinal       = 10
 size          = 3
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private QuotationType quotationTypeId;
			
	/**data for the column :
	
 --------- quotation.is_individual --------- 
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
 ordinal       = 11
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isIndividual;
			
	/**data for the column :
	
 --------- quotation.is_family_plan --------- 
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
 ordinal       = 12
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isFamilyPlan;
			
	/**data for the column :
	
 --------- quotation.is_discount_group_by_employee --------- 
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
 ordinal       = 13
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isDiscountGroupByEmployee;
			
	/**data for the column :
	
 --------- quotation.installment_amount --------- 
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
 ordinal       = 14
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer installmentAmount;
			
	/**data for the column :
	
 --------- quotation.aggregate_limit --------- 
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
 ordinal       = 15
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double aggregateLimit;
			
	/**data for the column :
	
 --------- quotation.toc --------- 
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
 ordinal       = 16
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer toc;
			
	/**data for the column :
	
 --------- quotation.payor_id --------- 
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
 ordinal       = 17
 size          = 5
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer payorId;
						
	/**data for the column :
	
 --------- quotation.is_agent_commision_gross_premi --------- 
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
 ordinal       = 19
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isAgentCommisionGrossPremi;
						
	/**data for the column :
	
 --------- quotation.max_children --------- 
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
 ordinal       = 21
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer maxChildren;
			
	/**data for the column :
	
 --------- quotation.is_aso_policy --------- 
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
 ordinal       = 22
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isAsoPolicy;
			
	/**data for the column :
	
 --------- quotation.aso_deposit --------- 
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
 ordinal       = 23
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double asoDeposit;
			
	/**data for the column :
	
 --------- quotation.brc_date --------- 
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
 ordinal       = 24
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date brcDate;
			
	/**data for the column :
	
 --------- quotation.requested_by --------- 
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
 ordinal       = 25
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String requestedBy;
			
	/**data for the column :
	
 --------- quotation.is_unit_premi --------- 
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
 ordinal       = 26
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isUnitPremi;
			
	/**data for the column :
	
 --------- quotation.is_wife_only --------- 
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
 ordinal       = 27
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isWifeOnly;
			
	/**data for the column :
	
 --------- quotation.uang_pertanggungan --------- 
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
 ordinal       = 28
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double uangPertanggungan;
			
	/**data for the column :
	
 --------- quotation.uang_premi --------- 
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
 ordinal       = 29
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double uangPremi;
			
	/**data for the column :
	
 --------- quotation.total_member --------- 
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
 ordinal       = 30
 size          = 6
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer totalMember;
			
	/**data for the column :
	
 --------- quotation.budget_premi --------- 
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
 ordinal       = 31
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double budgetPremi;
			
	/**data for the column :
	
 --------- quotation.comission --------- 
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
 ordinal       = 32
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double comission;
			
	/**data for the column :
	
 --------- quotation.claim_ratio --------- 
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
 ordinal       = 33
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double claimRatio;
								
	// foreign affairs
	
			/** Broker
	data for the foreign class :
	
 --------- broker.broker_id --------- 
 schema        = null
 tableName     = broker
 foreignCol    = broker_id
 foreignTab    = quotation
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
	private Broker brokerId;
				/** Client
	data for the foreign class :
	
 --------- client.client_id --------- 
 schema        = null
 tableName     = client
 foreignCol    = client_id
 foreignTab    = quotation
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
	private Client clientId;
				/** MemberGroup
	data for the foreign class :
	
 --------- member_group.member_group_id --------- 
 schema        = null
 tableName     = member_group
 foreignCol    = member_group_id
 foreignTab    = quotation
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
	private MemberGroup memberGroupId;
				/** Currency
	data for the foreign class :
	
 --------- currency.currency_id --------- 
 schema        = null
 tableName     = currency
 foreignCol    = currency_id
 foreignTab    = quotation
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
	private Currency currencyId;
				/** Branch
	data for the foreign class :
	
 --------- branch.branch_id --------- 
 schema        = null
 tableName     = branch
 foreignCol    = branch_id
 foreignTab    = quotation
 catalog       = wanaartha
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
	private Branch branchId;
	private SubscriptionStatus status;
	private Double cardCostPerMember;
	private Double totalCardCost;
	private Double manualBookCostPerMember;
	private Double totalManualBookCost;
	
	private Double adminFeePercentage;
	private Double adminFeeTotal;
	private Double comissionFeePercentage;
	private Double comissionFeeTotal;
	private Double remunerationFeePercentage;
	private Double remunerationFeeTotal;
	private Double ppnPercentage;
	private Double ppnTotal;
	private Double pphPercentage;
	private Double pphTotal;
	private Double profitPercentage;
	private Double profitTotal;
	private Double contestPercentage;
	private Double contestTotal;
	private Double marginContingencyPercentage;
	private Double marginContingencyTotal;
	private Double claimReimbursePercentage;
	private Double claimReimburseTotal;
	private Double entertainFeePercentage;
	private Double entertainFeeTotal;
	private Double tpaFeePercentage;
	private Double tpaFeeTotal;
	private Integer tpaFeeType;
	private CardType cardTypeId;
	private Tpa tpaId;
	private Quotation parentQuotationId;
	private Policy policyId;
						
	// -- foreign affairs end

	// -- exported key

	
			/** Policy
	data for the exported class :
	
 --------- policy.quotation_id --------- 
 schema        = null
 tableName     = policy
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = quotation.quotation_id

=========================================



	 */
	private Set <Policy> policys = new HashSet(0);
				/** QuotationProduct
	data for the exported class :
	
 --------- quotation_product.quotation_id --------- 
 schema        = null
 tableName     = quotation_product
 foreignCol    = 
 foreignTab    = 
 catalog       = wanaartha
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 3
 size          = 11
 type          = 4 
 isPrimaryKey  = false
 this columns points to  = quotation.quotation_id

=========================================



	 */
	private Set <QuotationProduct> quotationProducts = new HashSet(0);
			
	// -- exported key end

	// Fields End


	// PK GETTER SETTER
			@Id
	@Column(name="quotation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public java.lang.Integer getQuotationId(){
		return quotationId;
	}
	public void setQuotationId(java.lang.Integer value){
		quotationId = value;
	}
			// PK GETTER SETTER END

						@Column(name="quotation_no")
	public java.lang.String getQuotationNo(){
		return quotationNo;
	}
	public void setQuotationNo(java.lang.String value){
		quotationNo = value;
	}
				@Column(name="quotation_date")
	public java.sql.Date getQuotationDate(){
		return quotationDate;
	}
	public void setQuotationDate(java.sql.Date value){
		quotationDate = value;
	}
				@Column(name="request_date")
	public java.sql.Date getRequestDate(){
		return requestDate;
	}
	public void setRequestDate(java.sql.Date value){
		requestDate = value;
	}
							@Column(name="effective_date")
	public java.sql.Date getEffectiveDate(){
		return effectiveDate;
	}
	public void setEffectiveDate(java.sql.Date value){
		effectiveDate = value;
	}
				@Column(name="renewal_date")
	public java.sql.Date getRenewalDate(){
		return renewalDate;
	}
	public void setRenewalDate(java.sql.Date value){
		renewalDate = value;
	}
	@ManyToOne
	@JoinColumn(name="payment_mode")
	public PaymentMode getPaymentMode(){
		return paymentMode;
	}
	public void setPaymentMode(PaymentMode value){
		paymentMode = value;
	}
				@Column(name="remarks")
	public java.lang.String getRemarks(){
		return remarks;
	}
	public void setRemarks(java.lang.String value){
		remarks = value;
	}
	@ManyToOne
	@JoinColumn(name="quotation_type_id")
	public QuotationType getQuotationTypeId(){
		return quotationTypeId;
	}
	public void setQuotationTypeId(QuotationType value){
		quotationTypeId = value;
	}
				@Column(name="is_individual")
	public java.lang.Integer getIsIndividual(){
		return isIndividual;
	}
	public void setIsIndividual(java.lang.Integer value){
		isIndividual = value;
	}
				@Column(name="is_family_plan")
	public java.lang.Integer getIsFamilyPlan(){
		return isFamilyPlan;
	}
	public void setIsFamilyPlan(java.lang.Integer value){
		isFamilyPlan = value;
	}
				@Column(name="is_discount_group_by_employee")
	public java.lang.Integer getIsDiscountGroupByEmployee(){
		return isDiscountGroupByEmployee;
	}
	public void setIsDiscountGroupByEmployee(java.lang.Integer value){
		isDiscountGroupByEmployee = value;
	}
				@Column(name="installment_amount")
	public java.lang.Integer getInstallmentAmount(){
		return installmentAmount;
	}
	public void setInstallmentAmount(java.lang.Integer value){
		installmentAmount = value;
	}
				@Column(name="aggregate_limit")
	public java.lang.Double getAggregateLimit(){
		return aggregateLimit;
	}
	public void setAggregateLimit(java.lang.Double value){
		aggregateLimit = value;
	}
				@Column(name="toc")
	public java.lang.Integer getToc(){
		return toc;
	}
	public void setToc(java.lang.Integer value){
		toc = value;
	}
				@Column(name="payor_id")
	public java.lang.Integer getPayorId(){
		return payorId;
	}
	public void setPayorId(java.lang.Integer value){
		payorId = value;
	}
							@Column(name="is_agent_commision_gross_premi")
	public java.lang.Integer getIsAgentCommisionGrossPremi(){
		return isAgentCommisionGrossPremi;
	}
	public void setIsAgentCommisionGrossPremi(java.lang.Integer value){
		isAgentCommisionGrossPremi = value;
	}
							@Column(name="max_children")
	public java.lang.Integer getMaxChildren(){
		return maxChildren;
	}
	public void setMaxChildren(java.lang.Integer value){
		maxChildren = value;
	}
				@Column(name="is_aso_policy")
	public java.lang.Integer getIsAsoPolicy(){
		return isAsoPolicy;
	}
	public void setIsAsoPolicy(java.lang.Integer value){
		isAsoPolicy = value;
	}
				@Column(name="aso_deposit")
	public java.lang.Double getAsoDeposit(){
		return asoDeposit;
	}
	public void setAsoDeposit(java.lang.Double value){
		asoDeposit = value;
	}
				@Column(name="brc_date")
	public java.sql.Date getBrcDate(){
		return brcDate;
	}
	public void setBrcDate(java.sql.Date value){
		brcDate = value;
	}
				@Column(name="requested_by")
	public java.lang.String getRequestedBy(){
		return requestedBy;
	}
	public void setRequestedBy(java.lang.String value){
		requestedBy = value;
	}
				@Column(name="is_unit_premi")
	public java.lang.Integer getIsUnitPremi(){
		return isUnitPremi;
	}
	public void setIsUnitPremi(java.lang.Integer value){
		isUnitPremi = value;
	}
				@Column(name="is_wife_only")
	public java.lang.Integer getIsWifeOnly(){
		return isWifeOnly;
	}
	public void setIsWifeOnly(java.lang.Integer value){
		isWifeOnly = value;
	}
				@Column(name="uang_pertanggungan")
	public java.lang.Double getUangPertanggungan(){
		return uangPertanggungan;
	}
	public void setUangPertanggungan(java.lang.Double value){
		uangPertanggungan = value;
	}
				@Column(name="uang_premi")
	public java.lang.Double getUangPremi(){
		return uangPremi;
	}
	public void setUangPremi(java.lang.Double value){
		uangPremi = value;
	}
				@Column(name="total_member")
	public java.lang.Integer getTotalMember(){
		return totalMember;
	}
	public void setTotalMember(java.lang.Integer value){
		totalMember = value;
	}
				@Column(name="budget_premi")
	public java.lang.Double getBudgetPremi(){
		return budgetPremi;
	}
	public void setBudgetPremi(java.lang.Double value){
		budgetPremi = value;
	}
				@Column(name="comission")
	public java.lang.Double getComission(){
		return comission;
	}
	public void setComission(java.lang.Double value){
		comission = value;
	}
				@Column(name="claim_ratio")
	public java.lang.Double getClaimRatio(){
		return claimRatio;
	}
	public void setClaimRatio(java.lang.Double value){
		claimRatio = value;
	}
								
	// foreign affairs
	
			/** Broker */
	@ManyToOne
	@JoinColumn(name="broker_id")
	public Broker getBrokerId(){
		return this.brokerId;
	}

	/** Broker */
	public void setBrokerId(Broker obj){
		this.brokerId = obj;
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
				/** MemberGroup */
	@ManyToOne
	@JoinColumn(name="member_group_id")
	public MemberGroup getMemberGroupId(){
		return this.memberGroupId;
	}

	/** MemberGroup */
	public void setMemberGroupId(MemberGroup obj){
		this.memberGroupId = obj;
	}
				/** Currency */
	@ManyToOne
	@JoinColumn(name="currency_id")
	public Currency getCurrencyId(){
		return this.currencyId;
	}

	/** Currency */
	public void setCurrencyId(Currency obj){
		this.currencyId = obj;
	}
				/** Branch */
	@ManyToOne
	@JoinColumn(name="branch_id")
	public Branch getBranchId(){
		return this.branchId;
	}

	/** Branch */
	public void setBranchId(Branch obj){
		this.branchId = obj;
	}
						
	// foreign affairs end

// exported key

	
			/** Policy */
	@OneToMany(mappedBy="quotationId")
	public Set<Policy> getPolicys(){
		return this.policys;
	}

	/** Policy */
	public void setPolicys(Set<Policy> obj){
		this.policys = obj;
	}
				/** QuotationProduct */
	@OneToMany(mappedBy="quotationId")
	public Set<QuotationProduct> getQuotationProducts(){
		return this.quotationProducts;
	}

	/** QuotationProduct */
	public void setQuotationProducts(Set<QuotationProduct> obj){
		this.quotationProducts = obj;
	}
	
	@ManyToOne
	@JoinColumn(name="status_id")
	public SubscriptionStatus getStatus() {
		return status;
	}
	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}
	@Column(name="card_per_member_cost")
	public Double getCardCostPerMember() {
		return cardCostPerMember;
	}
	public void setCardCostPerMember(Double cardCostPerMember) {
		this.cardCostPerMember = cardCostPerMember;
	}
	@Column(name="total_card_cost")
	public Double getTotalCardCost() {
		return totalCardCost;
	}
	public void setTotalCardCost(Double totalCardCost) {
		this.totalCardCost = totalCardCost;
	}
	@Column(name="manual_book_per_member_cost")
	public Double getManualBookCostPerMember() {
		return manualBookCostPerMember;
	}
	public void setManualBookCostPerMember(Double manualBookCostPerMember) {
		this.manualBookCostPerMember = manualBookCostPerMember;
	}
	@Column(name="total_manual_book_cost")
	public Double getTotalManualBookCost() {
		return totalManualBookCost;
	}
	public void setTotalManualBookCost(Double totalManualBookCost) {
		this.totalManualBookCost = totalManualBookCost;
	}
	@Column(name="admin_fee_percentage")
	public Double getAdminFeePercentage() {
		return adminFeePercentage;
	}
	public void setAdminFeePercentage(Double adminFeePercentage) {
		this.adminFeePercentage = adminFeePercentage;
	}
	@Column(name="admin_fee_total")
	public Double getAdminFeeTotal() {
		return adminFeeTotal;
	}
	public void setAdminFeeTotal(Double adminFeeTotal) {
		this.adminFeeTotal = adminFeeTotal;
	}
	@Column(name="comission_fee_percentage")
	public Double getComissionFeePercentage() {
		return comissionFeePercentage;
	}
	public void setComissionFeePercentage(Double comissionFeePercentage) {
		this.comissionFeePercentage = comissionFeePercentage;
	}
	@Column(name="comission_fee_total")
	public Double getComissionFeeTotal() {
		return comissionFeeTotal;
	}
	public void setComissionFeeTotal(Double comissionFeeTotal) {
		this.comissionFeeTotal = comissionFeeTotal;
	
	}
	@Column(name="remuneration_fee_percentage")
	public Double getRemunerationFeePercentage() {
		return remunerationFeePercentage;
	}
	public void setRemunerationFeePercentage(Double remunerationFeePercentage) {
		this.remunerationFeePercentage = remunerationFeePercentage;
	}
	@Column(name="remuneration_fee_total")
	public Double getRemunerationFeeTotal() {
		return remunerationFeeTotal;
	}
	public void setRemunerationFeeTotal(Double remunerationFeeTotal) {
		this.remunerationFeeTotal = remunerationFeeTotal;
	}
	@Column(name="ppn_percentage")
	public Double getPpnPercentage() {
		return ppnPercentage;
	}
	public void setPpnPercentage(Double ppnPercentage) {
		this.ppnPercentage = ppnPercentage;
	}
	@Column(name="ppn_total")
	public Double getPpnTotal() {
		return ppnTotal;
	}
	public void setPpnTotal(Double ppnTotal) {
		this.ppnTotal = ppnTotal;
	}
	@Column(name="pph_percentage")
	public Double getPphPercentage() {
		return pphPercentage;
	}
	public void setPphPercentage(Double pphPercentage) {
		this.pphPercentage = pphPercentage;
	}
	@Column(name="pph_total")
	public Double getPphTotal() {
		return pphTotal;
	}
	public void setPphTotal(Double pphTotal) {
		this.pphTotal = pphTotal;
	}
	@Column(name="profit_percentage")
	public Double getProfitPercentage() {
		return profitPercentage;
	}
	public void setProfitPercentage(Double profitPercentage) {
		this.profitPercentage = profitPercentage;
	}
	@Column(name="profit_total")
	public Double getProfitTotal() {
		return profitTotal;
	}
	public void setProfitTotal(Double profitTotal) {
		this.profitTotal = profitTotal;
	}
	@Column(name="contest_percentage")
	public Double getContestPercentage() {
		return contestPercentage;
	}
	public void setContestPercentage(Double contestPercentage) {
		this.contestPercentage = contestPercentage;
	}
	@Column(name="contest_total")
	public Double getContestTotal() {
		return contestTotal;
	}
	public void setContestTotal(Double contestTotal) {
		this.contestTotal = contestTotal;
	}
	@Column(name="margin_contingency_percentage")
	public Double getMarginContingencyPercentage() {
		return marginContingencyPercentage;
	}
	public void setMarginContingencyPercentage(Double marginContingencyPercentage) {
		this.marginContingencyPercentage = marginContingencyPercentage;
	}
	@Column(name="margin_contingency_total")
	public Double getMarginContingencyTotal() {
		return marginContingencyTotal;
	}
	public void setMarginContingencyTotal(Double marginContingencyTotal) {
		this.marginContingencyTotal = marginContingencyTotal;
	}
	@Column(name="claim_reimburse_percentage")
	public Double getClaimReimbursePercentage() {
		return claimReimbursePercentage;
	}
	public void setClaimReimbursePercentage(Double claimReimbursePercentage) {
		this.claimReimbursePercentage = claimReimbursePercentage;
	}
	@Column(name="claim_reimburse_total")
	public Double getClaimReimburseTotal() {
		return claimReimburseTotal;
	}
	public void setClaimReimburseTotal(Double claimReimburseTotal) {
		this.claimReimburseTotal = claimReimburseTotal;
	}
	@Column(name="entertain_fee_percentage")
	public Double getEntertainFeePercentage() {
		return entertainFeePercentage;
	}
	public void setEntertainFeePercentage(Double entertainFeePercentage) {
		this.entertainFeePercentage = entertainFeePercentage;
	}
	@Column(name="entertain_fee_total")
	public Double getEntertainFeeTotal() {
		return entertainFeeTotal;
	}
	public void setEntertainFeeTotal(Double entertainFeeTotal) {
		this.entertainFeeTotal = entertainFeeTotal;
	}
	@Column(name="tpa_fee_percentage")
	public Double getTpaFeePercentage() {
		return tpaFeePercentage;
	}
	public void setTpaFeePercentage(Double tpaFeePercentage) {
		this.tpaFeePercentage = tpaFeePercentage;
	}
	@Column(name="tpa_fee_total")
	public Double getTpaFeeTotal() {
		return tpaFeeTotal;
	}
	public void setTpaFeeTotal(Double tpaFeeTotal) {
		this.tpaFeeTotal = tpaFeeTotal;
	}
	@Column(name="tpa_fee_type")
	public Integer getTpaFeeType() {
		return tpaFeeType;
	}
	public void setTpaFeeType(Integer tpaFeeType) {
		this.tpaFeeType = tpaFeeType;
	}
	@ManyToOne
	@JoinColumn(name="card_type_id")
	public CardType getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(CardType cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
	@ManyToOne
	@JoinColumn(name="tpa_id")
	public Tpa getTpaId() {
		return tpaId;
	}
	public void setTpaId(Tpa tpaId) {
		this.tpaId = tpaId;
	}
	@ManyToOne
	@JoinColumn(name="parent_quotation_id")
	public Quotation getParentQuotationId() {
		return parentQuotationId;
	}
	public void setParentQuotationId(Quotation parentQuotationId) {
		this.parentQuotationId = parentQuotationId;
	}
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
			
	
	

	

}