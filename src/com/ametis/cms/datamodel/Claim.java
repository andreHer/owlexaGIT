package com.ametis.cms.datamodel;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimExchangeDto;

@Entity
@Table(name = "claim")
public class Claim implements java.io.Serializable {

	public static final int CLAIM_SHOW = 0;
	public static final int CLAIM_SWIPE = 1;
	public static final int CLAIM_CASE = 2;

	public static final int CLAIM_OPEN = 1;
	public static final int CLAIM_PENDING_DOCUMENT = 2;
	public static final int CLAIM_VERIFIED = 3;
	public static final int CLAIM_REJECT = 4;
	public static final int CLAIM_CLOSED = 5;
	public static final int CLAIM_PAID = 6;
	public static final int CLAIM_PENDING_INVESTIGATION = 7;
	public static final int CLAIM_CHECKED = 8;
	public static final int CLAIM_APPROVED = 9;
	public static final int CLAIM_PENDING = 10;
	public static final int CLAIM_PROCESS_OK = 11;
	public static final int CLAIM_COMPLETE = 12;
	public static final int CLAIM_PAYMENT_ISSUED = 13;
	public static final int CLAIM_VOID = -1;
	public static final int CLAIM_PAYMENT_DISPOSITIONED = 23;
	//Add 20150414 by FVO, penambahan status baru untuk claim yang di create melalui case
	public static final int CLAIM_PRE_OPEN  = 19;

	private Integer isReported;
	private String medicalRemarks;
	private String checkerRemarks;
	private String paymentRemarks;
	private String rejectionRemarks;
	private String paidBy;
	private Timestamp paidTime;
	private String verifiedBy;
	private Timestamp verifiedTime;

	private Double pembayaranDimuka;
	private Double pembulatan;
	private Double remainingMemberLimit;
	private String diagnosis1Code;
	private String diagnosis2Code;
	private String diagnosis3Code;

	private String paymentNumber;

	private java.sql.Date paymentConfirmDate;
	private Date paymentGeneratedDate;

	private Double exGratiaValue;
	private String groupName;
	private MemberProduct productId;
	private Integer correctionClaim;
	private Boolean isCorrected;
	private Timestamp correctionTime;
	private String numberClaimCounter;
	private Integer roomIsFull;
	private Integer isSurgery;
	private Integer successfulDelivery;
	private String surgeryLevel;
	private Integer roomNotAvailable;
	private Integer hasComplication;
	private Integer isAccident;
	private Integer memberGroupId;
	private Integer clientId;
	private Client client;
	private Integer brokerId;
	private Integer tipe; // tipe sistem
	
	private Integer isUsingSMM;
	private String nomorSuratRujukan;
	private Integer isRujukan;
	private Integer claimRujukanId;
	private String doctorName;
	private Integer providerReferralId;
	private Integer isClausulExcluded;
	private String clausulReference;
	private Timestamp reconcileTime;
	private String electronicRefNumber;
	private String voidNotes;
	
	private Double refund;
	private Double prePaidExcess;
	private Double paidToProvider;
	private String preClaimCorrectionNumber;
	
	private Policy policy;

	private static final long serialVersionUID = 1L;

	// Fields

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_id --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = auto_increment
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 0 ordinal = 1
	 * size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer claimId;

	/**
	 * data for the column :
	 * 
	 * --------- claim.description --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 2 size = 65535 type =
	 * -1 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String description;

	/**
	 * data for the column :
	 * 
	 * --------- claim.location_id --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 4 size = 11 type = 4
	 * isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private TreatmentLocation locationId;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_number --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 5 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String claimNumber;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_charge_value --------- schema = null tableName =
	 * claim foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 8
	 * size = 22 type = 8 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Double claimChargeValue;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_date --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 9 size = 10 type =
	 * 91 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Date claimDate;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_closed_date --------- schema = null tableName =
	 * claim foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 10
	 * size = 10 type = 91 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Date claimClosedDate;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_remarks --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 11 size = 65535 type =
	 * -1 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String claimRemarks;

	/**
	 * data for the column :
	 * 
	 * --------- claim.approved_by --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 12 size = 11 type =
	 * 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String approvedBy;

	/**
	 * data for the column :
	 * 
	 * --------- claim.approved_time --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 13 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp approvedTime;

	/**
	 * data for the column :
	 * 
	 * --------- claim.closed_by --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 14 size = 11 type =
	 * 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String closedBy;

	/**
	 * data for the column :
	 * 
	 * --------- claim.closed_time --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 15 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp closedTime;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_paid_value --------- schema = null tableName =
	 * claim foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 18
	 * size = 22 type = 8 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Double claimPaidValue;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_approved_value --------- schema = null tableName =
	 * claim foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 19
	 * size = 22 type = 8 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Double claimApprovedValue;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_provider_status --------- schema = null tableName =
	 * claim foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 22
	 * size = 11 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer claimProviderStatus;

	/**
	 * data for the column :
	 * 
	 * --------- claim.claim_payment_status --------- schema = null tableName =
	 * claim foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 23
	 * size = 11 type = 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer claimPaymentStatus;

	/**
	 * data for the column :
	 * 
	 * --------- claim.provider_name --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 25 size = 255 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String providerName;

	/**
	 * data for the column :
	 * 
	 * --------- claim.total_item --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 28 size = 11 type =
	 * 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer totalItem;

	/**
	 * data for the column :
	 * 
	 * --------- claim.created_time --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 29 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp createdTime;

	/**
	 * data for the column :
	 * 
	 * --------- claim.created_by --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 30 size = 50 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String createdBy;

	/**
	 * data for the column :
	 * 
	 * --------- claim.deleted_time --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 31 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp deletedTime;

	/**
	 * data for the column :
	 * 
	 * --------- claim.deleted_by --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 32 size = 50 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String deletedBy;

	/**
	 * data for the column :
	 * 
	 * --------- claim.modified_time --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 33 size = 19 type =
	 * 93 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Timestamp modifiedTime;

	/**
	 * data for the column :
	 * 
	 * --------- claim.modified_by --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 34 size = 50 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String modifiedBy;

	/**
	 * data for the column :
	 * 
	 * --------- claim.deleted_status --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 35 size = 11 type =
	 * 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Integer deletedStatus;

	/**
	 * data for the column :
	 * 
	 * --------- claim.admission_date --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 36 size = 10 type =
	 * 91 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Date admissionDate;

	/**
	 * data for the column :
	 * 
	 * --------- claim.discharge_date --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 37 size = 10 type =
	 * 91 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private java.sql.Date dischargeDate;

	/**
	 * data for the column :
	 * 
	 * --------- claim.diagnosis3_id --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 38 size = 11 type =
	 * 4 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private Diagnosis diagnosis3Id;

	/**
	 * data for the column :
	 * 
	 * --------- claim.iterative --------- schema = null tableName = claim
	 * foreignCol = foreignTab = catalog = insurance remarks = defaultValue =
	 * null decDigits = 0 radix = 10 nullable = 1 ordinal = 39 size = 3 type =
	 * 12 isPrimaryKey = false
	 * 
	 * =========================================
	 * 
	 * 
	 */
	private String iterative;

	// foreign affairs

	/**
	 * ClaimType data for the foreign class :
	 * 
	 * --------- claim_type.claim_type_id --------- schema = null tableName =
	 * claim_type foreignCol = claim_type_id foreignTab = claim catalog =
	 * insurance remarks = auto_increment defaultValue = null decDigits = 0
	 * radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey =
	 * true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private ClaimType claimTypeId;
	/**
	 * CaseStatus data for the foreign class :
	 * 
	 * --------- case_status.case_status_id --------- schema = null tableName =
	 * case_status foreignCol = claim_status foreignTab = claim catalog =
	 * insurance remarks = auto_increment defaultValue = null decDigits = 0
	 * radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey =
	 * true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private CaseStatus claimStatus;
	private CaseStatus secondaryStatus;
	/**
	 * Member data for the foreign class :
	 * 
	 * --------- member.member_id --------- schema = null tableName = member
	 * foreignCol = member_id foreignTab = claim catalog = insurance remarks =
	 * auto_increment defaultValue = null decDigits = 0 radix = 10 nullable = 0
	 * ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Member memberId;
	/**
	 * Diagnosis data for the foreign class :
	 * 
	 * --------- diagnosis.diagnosis_id --------- schema = null tableName =
	 * diagnosis foreignCol = diagnosis_id foreignTab = claim catalog =
	 * insurance remarks = auto_increment defaultValue = null decDigits = 0
	 * radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey =
	 * true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Diagnosis diagnosisId;
	/**
	 * Diagnosis data for the foreign class :
	 * 
	 * --------- diagnosis.diagnosis_id --------- schema = null tableName =
	 * diagnosis foreignCol = diagnosis2_id foreignTab = claim catalog =
	 * insurance remarks = auto_increment defaultValue = null decDigits = 0
	 * radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey =
	 * true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Diagnosis diagnosis2Id;
	/**
	 * BatchClaim data for the foreign class :
	 * 
	 * --------- batch_claim.batch_claim_id --------- schema = null tableName =
	 * batch_claim foreignCol = batch_claim_id foreignTab = claim catalog =
	 * insurance remarks = auto_increment defaultValue = null decDigits = 0
	 * radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey =
	 * true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private BatchClaim batchClaimId;
	/**
	 * Case data for the foreign class :
	 * 
	 * --------- case.case_id --------- schema = null tableName = case
	 * foreignCol = case_id foreignTab = claim catalog = insurance remarks =
	 * auto_increment defaultValue = null decDigits = 0 radix = 10 nullable = 0
	 * ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Case caseId;
	/**
	 * CaseCategory data for the foreign class :
	 * 
	 * --------- case_category.case_category_id --------- schema = null
	 * tableName = case_category foreignCol = case_category_id foreignTab =
	 * claim catalog = insurance remarks = auto_increment defaultValue = null
	 * decDigits = 0 radix = 10 nullable = 0 ordinal = 1 size = 11 type = 4
	 * isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private CaseCategory caseCategoryId;
	/**
	 * Provider data for the foreign class :
	 * 
	 * --------- provider.provider_id --------- schema = null tableName =
	 * provider foreignCol = provider_id foreignTab = claim catalog = insurance
	 * remarks = auto_increment defaultValue = null decDigits = 0 radix = 10
	 * nullable = 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Provider providerId = null;
	/**
	 * Payment data for the foreign class :
	 * 
	 * --------- payment.payment_id --------- schema = null tableName = payment
	 * foreignCol = payment_id foreignTab = claim catalog = insurance remarks =
	 * auto_increment defaultValue = null decDigits = 0 radix = 10 nullable = 0
	 * ordinal = 1 size = 11 type = 4 isPrimaryKey = true
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	
	private Integer manualRegistration;
	
	/**data for the column :
	
 --------- claim.manual_registration --------- 
 schema        = null
 tableName     = claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insurance
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
	
	private Payment paymentId;
	private PaymentInstallment paymentInstallmentId;

	private Double claimExcessValue;

	private String otherNumber;
	private String memberName;
	private String memberNumber;
	private Boolean isReconciled;
	private Timestamp lastReconciliationTime;
	private String reconciledBy;
	private String relationship;
	private String sex;
	private String plan;
	private String productClass;
	private String providerArea;

	private Date birthdate;
	private String bank;
	private String bankAccount;
	private String claimServiceType;
	private String claimPaymentType;
	private String payeeName;
	private Date effectiveDate;
	private Date expireDate;
	private String diagnoseName;
	private String groupPolicyNumber;
	private String memberOtherNumber;
	private String memberPolicyNumber;
	private String department;
	private Integer policyId;
	private String edcClaimNumber;
	private String terminalId;
	private String merchantId;
	private String edcTraceNumber;
	private Integer isEDCBatchAssigned;
	private Integer isExGratia;
	private Integer roomUpgradeType;
	private Double currentRoomRate;
	private Double productRoomRate;
	private String voidTraceNumber;
	private Double cobClaimCharge;
	private Integer cobStatus;
	private Double cobClaimPaidValue;
	private Date cobRequestDate;
	private String longitude;
	private String latitude;
	private Currency claimCurrencyId;
	private Currency productCurrencyId;
	private ExchangeRate exchangeRateId;
	private Double exchangeRate;
	private Date exchangeRateDate;
	private Timestamp benefitCheckedTime;
	private InvoiceItem invoiceItemId;
	
	private Double productCurrencyChargeValue;
	private Double productCurrencyApprovedValue;
	private Double productCurrencyExcessValue;
	private Double productCurrencyPolicyApprovedValue;
	private Double claimPolicyApprovedValue;
	
	private PolicyBenefit policyBenefitId;
	private Integer isUsingPolicyBenefit;
	
	
	private Integer labDocument;
	private Integer prescriptionDocument;
	private Integer edcDocument;
	private Integer glDocument;
	private Integer invoiceDocument;
	private Integer referalDocument;
	private Timestamp recapTime;
	private String recapBy;
	private Integer recapStatus;
	private String cardNumber;
	private Claim cobClaimReferenceId;
	private Double escalationTotalDay;
	
	private Integer isUsingMultiLayerLimit;
	private MemberLimitLayer smmLayerId;
	private MemberLimitLayer multiLayerId;
	private String excessRemarks;
	private Integer isPaid;
	
	private String clientPaymentVoucherNumber;
	private Integer clientPaymentApprovalStatus;
	private Date clientPaymentVoucherDate;
	private String currency;
	private String clientPaidApprovalAmount;
	private String bankCodeFromClient;
	private String bankNameFromClient;
	private String accountHolderNameFromClient;
	private String accountNumberFromClient;
	private String clientPaymentCategory;
	private Double billingRate;
	private Integer isBilled;

	// -- foreign affairs end

	// -- exported key

	/**
	 * ClaimItem data for the exported class :
	 * 
	 * --------- claim_item.claim_id --------- schema = null tableName =
	 * claim_item foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 3
	 * size = 11 type = 4 isPrimaryKey = false this columns points to =
	 * claim.claim_id
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Set<ClaimItem> claimItems = new HashSet(0);
	/**
	 * ExcessCharge data for the exported class :
	 * 
	 * --------- excess_charge.claim_id --------- schema = null tableName =
	 * excess_charge foreignCol = foreignTab = catalog = insurance remarks =
	 * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 4
	 * size = 11 type = 4 isPrimaryKey = false this columns points to =
	 * claim.claim_id
	 * 
	 * =========================================
	 * 
	 * 
	 * 
	 */
	private Set<ExcessCharge> excessCharges = new HashSet(0);

	// -- exported key end

	// Fields End

	// PK GETTER SETTER
	@Id
	@Column(name = "claim_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public java.lang.Integer getClaimId() {
		return claimId;
	}

	public void setClaimId(java.lang.Integer value) {
		claimId = value;
	}

	// PK GETTER SETTER END

	@Column(name = "description")
	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String value) {
		description = value;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "location_id")
	public TreatmentLocation getLocationId() {
		return locationId;
	}

	public void setLocationId(TreatmentLocation value) {
		locationId = value;
	}

	@Column(name = "claim_number")
	public java.lang.String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(java.lang.String value) {
		claimNumber = value;
	}

	@Column(name = "claim_charge_value")
	public java.lang.Double getClaimChargeValue() {
		return claimChargeValue;
	}

	public void setClaimChargeValue(java.lang.Double value) {
		claimChargeValue = value;
	}

	@Column(name = "claim_date")
	public java.sql.Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(java.sql.Date value) {
		claimDate = value;
	}

	@Column(name = "claim_closed_date")
	public java.sql.Date getClaimClosedDate() {
		return claimClosedDate;
	}

	public void setClaimClosedDate(java.sql.Date value) {
		claimClosedDate = value;
	}

	@Column(name = "claim_remarks")
	public java.lang.String getClaimRemarks() {
		return claimRemarks;
	}

	public void setClaimRemarks(java.lang.String value) {
		claimRemarks = value;
	}

	@Column(name = "approved_by")
	public java.lang.String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(java.lang.String value) {
		approvedBy = value;
	}

	@Column(name = "approved_time")
	public java.sql.Timestamp getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(java.sql.Timestamp value) {
		approvedTime = value;
	}

	@Column(name = "closed_by")
	public java.lang.String getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(java.lang.String value) {
		closedBy = value;
	}

	@Column(name = "closed_time")
	public java.sql.Timestamp getClosedTime() {
		return closedTime;
	}

	public void setClosedTime(java.sql.Timestamp value) {
		closedTime = value;
	}

	@Column(name = "claim_paid_value")
	public java.lang.Double getClaimPaidValue() {
		return claimPaidValue;
	}

	public void setClaimPaidValue(java.lang.Double value) {
		claimPaidValue = value;
	}

	@Column(name = "claim_approved_value")
	public java.lang.Double getClaimApprovedValue() {
		return claimApprovedValue;
	}

	public void setClaimApprovedValue(java.lang.Double value) {
		claimApprovedValue = value;
	}

	@Column(name = "claim_provider_status")
	public java.lang.Integer getClaimProviderStatus() {
		return claimProviderStatus;
	}

	public void setClaimProviderStatus(java.lang.Integer value) {
		claimProviderStatus = value;
	}

	@Column(name = "claim_payment_status")
	public java.lang.Integer getClaimPaymentStatus() {
		return claimPaymentStatus;
	}

	public void setClaimPaymentStatus(java.lang.Integer value) {
		claimPaymentStatus = value;
	}

	@Column(name = "provider_name")
	public java.lang.String getProviderName() {
		return providerName;
	}

	public void setProviderName(java.lang.String value) {
		providerName = value;
	}

	@Column(name = "total_item")
	public java.lang.Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(java.lang.Integer value) {
		totalItem = value;
	}

	@Column(name = "created_time")
	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(java.sql.Timestamp value) {
		createdTime = value;
	}

	@Column(name = "created_by")
	public java.lang.String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(java.lang.String value) {
		createdBy = value;
	}

	@Column(name = "deleted_time")
	public java.sql.Timestamp getDeletedTime() {
		return deletedTime;
	}

	public void setDeletedTime(java.sql.Timestamp value) {
		deletedTime = value;
	}

	@Column(name = "deleted_by")
	public java.lang.String getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(java.lang.String value) {
		deletedBy = value;
	}

	@Column(name = "modified_time")
	public java.sql.Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(java.sql.Timestamp value) {
		modifiedTime = value;
	}

	@Column(name = "modified_by")
	public java.lang.String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(java.lang.String value) {
		modifiedBy = value;
	}

	@Column(name = "deleted_status")
	public java.lang.Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(java.lang.Integer value) {
		deletedStatus = value;
	}

	@Column(name = "admission_date")
	public java.sql.Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(java.sql.Date value) {
		admissionDate = value;
	}

	@Column(name = "discharge_date")
	public java.sql.Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(java.sql.Date value) {
		dischargeDate = value;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "diagnosis3_id")
	public Diagnosis getDiagnosis3Id() {
		return diagnosis3Id;
	}

	public void setDiagnosis3Id(Diagnosis value) {
		diagnosis3Id = value;
	}

	@Column(name = "iterative")
	public java.lang.String getIterative() {
		return iterative;
	}

	public void setIterative(java.lang.String value) {
		iterative = value;
	}

	// foreign affairs

	/** ClaimType */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "claim_type_id")
	public ClaimType getClaimTypeId() {
		return this.claimTypeId;
	}

	/** ClaimType */
	public void setClaimTypeId(ClaimType obj) {
		this.claimTypeId = obj;
	}

	/** CaseStatus */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "claim_status")
	public CaseStatus getClaimStatus() {
		return this.claimStatus;
	}

	/** CaseStatus */
	public void setClaimStatus(CaseStatus obj) {
		this.claimStatus = obj;
	}

	/** Member */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "member_id")
	public Member getMemberId() {
		return this.memberId;
	}

	/** Member */
	public void setMemberId(Member obj) {
		this.memberId = obj;
	}

	/** Diagnosis */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "diagnosis_id")
	public Diagnosis getDiagnosisId() {
		return this.diagnosisId;
	}

	/** Diagnosis */
	public void setDiagnosisId(Diagnosis obj) {
		this.diagnosisId = obj;
	}

	/** Diagnosis */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "diagnosis2_id")
	public Diagnosis getDiagnosis2Id() {
		return this.diagnosis2Id;
	}

	/** Diagnosis */
	public void setDiagnosis2Id(Diagnosis obj) {
		this.diagnosis2Id = obj;
	}

	/** BatchClaim */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "batch_claim_id")
	public BatchClaim getBatchClaimId() {
		return this.batchClaimId;
	}

	/** BatchClaim */
	public void setBatchClaimId(BatchClaim obj) {
		this.batchClaimId = obj;
	}

	/** Case */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "case_id", nullable = true)
	public Case getCaseId() {
		return this.caseId;
	}

	/** Case */
	public void setCaseId(Case obj) {
		this.caseId = obj;
	}

	/** CaseCategory */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "case_category_id")
	public CaseCategory getCaseCategoryId() {
		return this.caseCategoryId;
	}

	/** CaseCategory */
	public void setCaseCategoryId(CaseCategory obj) {
		this.caseCategoryId = obj;
	}

	/** Provider */
	@ManyToOne(fetch=FetchType.LAZY ,optional = true)
	@JoinColumn(name = "provider_id", nullable = true)
	public Provider getProviderId() {
		return this.providerId;
	}

	/** Provider */
	public void setProviderId(Provider obj) {
		this.providerId = obj;
	}

	/** Payment */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "payment_id", nullable = true)
	public Payment getPaymentId() {
		return this.paymentId;
	}

	/** Payment */
	public void setPaymentId(Payment obj) {
		this.paymentId = obj;
	}

	// foreign affairs end

	// exported key

	@Column(name = "claim_excess_value")
	public Double getClaimExcessValue() {
		return claimExcessValue;
	}

	public void setClaimExcessValue(Double claimExcessValue) {
		this.claimExcessValue = claimExcessValue;
	}

	/** ClaimItem */
	@OneToMany(mappedBy = "claimId")
	public Set<ClaimItem> getClaimItems() {
		return this.claimItems;
	}

	/** ClaimItem */
	public void setClaimItems(Set<ClaimItem> obj) {
		this.claimItems = obj;
	}

	/** ExcessCharge */
	@OneToMany(mappedBy = "claimId")
	public Set<ExcessCharge> getExcessCharges() {
		return this.excessCharges;
	}

	/** ExcessCharge */
	public void setExcessCharges(Set<ExcessCharge> obj) {
		this.excessCharges = obj;
	}

	@Column(name = "checker_remarks")
	public String getCheckerRemarks() {
		return checkerRemarks;
	}

	public void setCheckerRemarks(String checkerRemarks) {
		this.checkerRemarks = checkerRemarks;
	}

	@Column(name = "medical_remarks")
	public String getMedicalRemarks() {
		return medicalRemarks;
	}

	public void setMedicalRemarks(String medicalRemarks) {
		this.medicalRemarks = medicalRemarks;
	}

	@Column(name = "payment_remarks")
	public String getPaymentRemarks() {
		return paymentRemarks;
	}

	public void setPaymentRemarks(String paymentRemarks) {
		this.paymentRemarks = paymentRemarks;
	}

	@Column(name = "other_number")
	public String getOtherNumber() {
		return otherNumber;
	}

	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}

	@Column(name = "paid_by")
	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	@Column(name = "paid_time")
	public Timestamp getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(Timestamp paidTime) {
		this.paidTime = paidTime;
	}

	@Column(name = "verified_by")
	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	@Column(name = "verified_time")
	public Timestamp getVerifiedTime() {
		return verifiedTime;
	}

	public void setVerifiedTime(Timestamp verifiedTime) {
		this.verifiedTime = verifiedTime;
	}

	@Column(name = "pembayaran_dimuka")
	public Double getPembayaranDimuka() {
		return pembayaranDimuka;
	}

	public void setPembayaranDimuka(Double pembayaranDimuka) {
		this.pembayaranDimuka = pembayaranDimuka;
	}

	@Column(name = "pembulatan_claim")
	public Double getPembulatan() {
		return pembulatan;
	}

	public void setPembulatan(Double pembulatan) {
		this.pembulatan = pembulatan;
	}

	@Column(name = "remaining_member_limit")
	public Double getRemainingMemberLimit() {
		return remainingMemberLimit;
	}

	public void setRemainingMemberLimit(Double remainingMemberLimit) {
		this.remainingMemberLimit = remainingMemberLimit;
	}

	@Column(name = "diagnosis1_code")
	public String getDiagnosis1Code() {
		return diagnosis1Code;
	}

	public void setDiagnosis1Code(String diagnosis1Code) {
		this.diagnosis1Code = diagnosis1Code;
	}

	@Column(name = "diagnosis2_code")
	public String getDiagnosis2Code() {
		return diagnosis2Code;
	}

	public void setDiagnosis2Code(String diagnosis2Code) {
		this.diagnosis2Code = diagnosis2Code;
	}

	@Column(name = "diagnosis3_code")
	public String getDiagnosis3Code() {
		return diagnosis3Code;
	}

	public void setDiagnosis3Code(String diagnosis3Code) {
		this.diagnosis3Code = diagnosis3Code;
	}

	@Column(name = "member_name")
	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Column(name = "payment_number")
	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	@Column(name = "payment_confirmed_date")
	public java.sql.Date getPaymentConfirmDate() {
		return paymentConfirmDate;
	}

	public void setPaymentConfirmDate(java.sql.Date paymentConfirmDate) {
		this.paymentConfirmDate = paymentConfirmDate;
	}

	@Column(name = "payment_generated_date")
	public Date getPaymentGeneratedDate() {
		return paymentGeneratedDate;
	}

	public void setPaymentGeneratedDate(Date paymentGeneratedDate) {
		this.paymentGeneratedDate = paymentGeneratedDate;
	}

	@Column(name = "ex_gratia_value")
	public Double getExGratiaValue() {
		return exGratiaValue;
	}

	public void setExGratiaValue(Double exGratiaValue) {
		this.exGratiaValue = exGratiaValue;
	}

	@Column(name = "group_name")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "member_number")
	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	@Column(name = "is_reconciled")
	public Boolean isReconciled() {
		return isReconciled;
	}

	public void setReconciled(Boolean isReconciled) {
		this.isReconciled = isReconciled;
	}

	@Column(name = "last_reconciliation_time")
	public Timestamp getLastReconciliationTime() {
		return lastReconciliationTime;
	}

	public void setLastReconciliationTime(Timestamp lastReconciliationTime) {
		this.lastReconciliationTime = lastReconciliationTime;
	}

	@Column(name = "reconciled_by")
	public String getReconciledBy() {
		return reconciledBy;
	}

	public void setReconciledBy(String reconciledBy) {
		this.reconciledBy = reconciledBy;
	}

	@Column(name = "relationship")
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "member_plan")
	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Column(name = "class")
	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	@Column(name = "provider_area")
	public String getProviderArea() {
		return providerArea;
	}

	public void setProviderArea(String providerArea) {
		this.providerArea = providerArea;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public MemberProduct getProductId() {
		return productId;
	}

	public void setProductId(MemberProduct productId) {
		this.productId = productId;
	}

	
	@Column(name = "correction_claim_id")
	public Integer getCorrectionClaim() {
		return correctionClaim;
	}

	public void setCorrectionClaim(Integer correctionClaim) {
		this.correctionClaim = correctionClaim;
	}

	@Column(name = "is_corrected")
	public Boolean getIsCorrected() {
		return isCorrected;
	}

	public void setIsCorrected(Boolean isCorrected) {
		this.isCorrected = isCorrected;
	}

	@Column(name = "correction_time")
	public Timestamp getCorrectionTime() {
		return correctionTime;
	}

	public void setCorrectionTime(Timestamp correctionTime) {
		this.correctionTime = correctionTime;
	}

	public ClaimDto exportDto() {
		ClaimDto claim = new ClaimDto();

		claim.setAdmissionDate(admissionDate);
		claim.setApprovedBy(approvedBy);
		claim.setApprovedTime(approvedTime);
		claim.setCheckerRemarks(checkerRemarks);
		claim.setClaimApprovedValue(claimApprovedValue);
		claim.setClaimChargeValue(claimChargeValue);
		claim.setClaimClosedDate(claimClosedDate);
		claim.setClaimDate(claimDate);
		
		claim.setClaimExcessValue(claimExcessValue);
		claim.setClaimId(claimId);
		System.out.println("EXPORTING CLAIM ID LIST : " + claimId);
		claim.setClaimNumber(claimNumber);
		claim.setClaimPaidValue(claimPaidValue);
		claim.setClaimPaymentStatus(claimPaymentStatus);
		claim.setClaimProviderStatus(claimProviderStatus);
		claim.setClaimRemarks(claimRemarks);

		if (batchClaimId != null){
			claim.setInvoiceNumber(batchClaimId.getInvoiceNumber());
		}

		claim.setClaimPaymentType(claimPaymentType);
		claim.setClaimServiceType(claimServiceType);
		
		if (claimStatus != null) {
			claim.setStatus(claimStatus.getCaseStatusName());
			claim.setClaimStatus(claimStatus.getCaseStatusId());			
		}

		claim.setClosedTime(closedTime);
		claim.setCreatedBy(createdBy);
		claim.setCreatedTime(createdTime);
		claim.setDeletedBy(deletedBy);
		claim.setDeletedStatus(deletedStatus);
		claim.setDeletedTime(deletedTime);
		claim.setDescription(description);
		claim.setDiagnosis1Code(diagnosis1Code);
		claim.setDiagnosis2Code(diagnosis2Code);

		claim.setDiagnosis3Code(diagnosis3Code);

		claim.setDischargeDate(dischargeDate);
		claim.setExGratiaValue(exGratiaValue);

		if (diagnosisId != null) {
			
			claim.setDiagnosis1Name(diagnosisId.getDiagnosisName());
		}
		if (memberId.getMemberGroupId() != null) {
			claim.setGroupName(memberId.getMemberGroupId().getGroupName());
		} else {
			claim.setGroupName("-");
		}

		claim.setMedicalRemarks(medicalRemarks);
		if (memberId != null) {
			claim.setMemberName(memberId.getFirstName());
			claim.setMemberNumber(memberId.getCustomerPolicyNumber());
		}

		claim.setEffectiveDate(memberId.getEffectiveDate());
		claim.setExpireDate(memberId.getExpireDate());

		claim.setBirthday(memberId.getBirthday());

		claim.setModifiedBy(modifiedBy);
		claim.setModifiedTime(modifiedTime);
		claim.setOtherNumber(otherNumber);
		claim.setPaidBy(paidBy);
		claim.setPaidTime(paidTime);

		if (paymentId != null) {

			claim.setPaymentConfirmDate(paymentId.getPaymentConfirmDate());
			claim.setPaymentGeneratedDate(paymentId.getPaymentTime());

			claim.setPaymentNumber(paymentId.getPaymentNumber());
			claim.setPaymentRemarks(paymentId.getRemarks());

		} else {
			System.out.println("TIDAK ADA PAYMENT ID");
			claim.setPaymentConfirmDate(null);
			claim.setPaymentGeneratedDate(null);

			claim.setPaymentNumber("");
			claim.setPaymentRemarks("");
		}

		claim.setPlan(plan);
		claim.setProductClass(productClass);

		claim.setProviderArea(providerArea);

		claim.setProviderName(providerName);

		claim.setRelationship(relationship);
		claim.setRemainingMemberLimit(remainingMemberLimit);

		claim.setSex(sex);
		claim.setTotalItem(totalItem);
		claim.setVerifiedBy(verifiedBy);
		claim.setVerifiedTime(verifiedTime);

		if (paymentId != null) {
			claim.setPayeeName(paymentId.getPayeeName());
			claim.setBankAccount(paymentId.getAccountNumber());
			claim.setBank(paymentId.getBankName());

		} else {
			claim.setPayeeName("");
			claim.setBankAccount("");
		}

		return claim;

	}

	public Claim cloneClaim() {
		Claim claim = new Claim();

		claim.setAdmissionDate(admissionDate);
		claim.setApprovedBy(approvedBy);
		claim.setApprovedTime(approvedTime);
		if (batchClaimId != null){
			claim.setBatchClaimId(batchClaimId);
		}
		
		if (caseCategoryId != null){
			claim.setCaseCategoryId(caseCategoryId);
		}

		if (caseId != null){
			claim.setCaseId(caseId);
		}

		claim.setCheckerRemarks(checkerRemarks);
		claim.setClaimApprovedValue(claimApprovedValue);
		claim.setClaimChargeValue(claimChargeValue);
		claim.setClaimClosedDate(claimClosedDate);
		claim.setClaimDate(claimDate);
		claim.setClaimExcessValue(claimExcessValue);
		// claim.setClaimId(claimId);
		claim.setClaimNumber(claimNumber);
		claim.setClaimPaidValue(claimPaidValue);
		claim.setClaimPaymentStatus(claimPaymentStatus);
		claim.setClaimProviderStatus(claimProviderStatus);
		claim.setClaimRemarks(claimRemarks);
		
		if (claimStatus != null){
			claim.setClaimStatus(claimStatus);
		}
		claim.setClaimTypeId(claimTypeId);
		claim.setClosedBy(closedBy);
		claim.setClosedTime(closedTime);
		claim.setCreatedBy(createdBy);
		claim.setCreatedTime(createdTime);
		claim.setDeletedBy(deletedBy);
		claim.setDeletedStatus(deletedStatus);
		claim.setDeletedTime(deletedTime);
		claim.setDescription(description);
		if (diagnosis1Code != null){
			claim.setDiagnosis1Code(diagnosis1Code);
		}
		claim.setDiagnosis2Code(diagnosis2Code);
		claim.setDiagnosis2Id(diagnosis2Id);
		claim.setDiagnosis3Code(diagnosis3Code);
		if (diagnosis3Id != null){
			claim.setDiagnosis3Id(diagnosis3Id);
		}
		if (diagnosisId != null){
			claim.setDiagnosisId(diagnosisId);
		}
		claim.setDischargeDate(dischargeDate);
		claim.setExGratiaValue(exGratiaValue);
		claim.setGroupName(groupName);
		claim.setClaimItems(claimItems);
		claim.setExcessCharges(excessCharges);
		claim.setIterative(iterative);
		claim.setLastReconciliationTime(lastReconciliationTime);
		claim.setLocationId(locationId);
		claim.setMedicalRemarks(medicalRemarks);
		claim.setMemberId(memberId);
		claim.setMemberName(memberName);
		claim.setMemberNumber(memberNumber);
		claim.setModifiedBy(modifiedBy);
		claim.setModifiedTime(modifiedTime);
		claim.setOtherNumber(otherNumber);
		claim.setPaidBy(paidBy);
		claim.setPaidTime(paidTime);
		claim.setPaymentConfirmDate(paymentConfirmDate);
		claim.setPaymentGeneratedDate(paymentGeneratedDate);
		claim.setPaymentId(paymentId);
		claim.setPaymentNumber(paymentNumber);
		claim.setPaymentRemarks(paymentRemarks);
		claim.setPembayaranDimuka(pembayaranDimuka);
		claim.setPembulatan(pembulatan);
		claim.setPlan(plan);
		claim.setProductClass(productClass);
		claim.setProductId(productId);
		claim.setProviderArea(providerArea);
		claim.setProviderId(providerId);
		claim.setProviderName(providerName);
		claim.setReconciled(isReconciled);
		claim.setReconciledBy(reconciledBy);
		claim.setRelationship(relationship);
		claim.setRemainingMemberLimit(remainingMemberLimit);
		claim.setSex(sex);
		claim.setTotalItem(totalItem);
		claim.setVerifiedBy(verifiedBy);
		claim.setVerifiedTime(verifiedTime);
		claim.setIsCorrected(false);
		claim.setCorrectionClaim(null);
		claim.setCorrectionTime(null);

		return claim;
	}

	@Column(name = "number_claim_counter")
	public String getNumberClaimCounter() {
		return numberClaimCounter;
	}

	public void setNumberClaimCounter(String numberClaimCounter) {
		this.numberClaimCounter = numberClaimCounter;
	}

	@Column(name = "room_is_full")
	public Integer getRoomIsFull() {
		return roomIsFull;
	}

	public void setRoomIsFull(Integer roomIsFull) {
		this.roomIsFull = roomIsFull;
	}

	@Column(name = "is_surgery")
	public Integer getIsSurgery() {
		return isSurgery;
	}

	public void setIsSurgery(Integer isSurgery) {
		this.isSurgery = isSurgery;
	}

	@Column(name = "surgery_level")
	public String getSurgeryLevel() {
		return surgeryLevel;
	}

	public void setSurgeryLevel(String surgeryLevel) {
		this.surgeryLevel = surgeryLevel;
	}

	@Column(name = "room_not_available")
	public Integer getRoomNotAvailable() {
		return roomNotAvailable;
	}

	public void setRoomNotAvailable(Integer roomNotAvailable) {
		this.roomNotAvailable = roomNotAvailable;
	}

	@Column(name = "has_complication")
	public Integer getHasComplication() {
		return hasComplication;
	}

	public void setHasComplication(Integer hasComplication) {
		this.hasComplication = hasComplication;
	}

	@Column(name = "is_accident")
	public Integer getIsAccident() {
		return isAccident;
	}

	public void setIsAccident(Integer isAccident) {
		this.isAccident = isAccident;
	}

	@Column(name = "member_group_id")
	public Integer getMemberGroupId() {
		return memberGroupId;
	}

	public void setMemberGroupId(Integer memberGroupId) {
		this.memberGroupId = memberGroupId;
	}

	@Column(name = "client_id")
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@Column(name = "broker_id")
	public Integer getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(Integer brokerId) {
		this.brokerId = brokerId;
	}

	@Column(name = "birthday")
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Column(name = "bank")
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "bank_account")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "claim_service_type")
	public String getClaimServiceType() {
		return claimServiceType;
	}

	public void setClaimServiceType(String claimServiceType) {
		this.claimServiceType = claimServiceType;
	}

	@Column(name = "claim_payment_type")
	public String getClaimPaymentType() {
		return claimPaymentType;
	}

	public void setClaimPaymentType(String claimPaymentType) {
		this.claimPaymentType = claimPaymentType;
	}

	@Column(name = "payee_name")
	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	@Column(name = "effective_date")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "expire_date")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Column(name = "diagnose_name")
	public String getDiagnoseName() {
		return diagnoseName;
	}

	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}

	@Column(name = "tipe")
	public Integer getTipe() {
		return tipe;
	}

	public void setTipe(Integer tipe) {
		this.tipe = tipe;
	}

	@Column(name = "is_reported")
	public Integer getIsReported() {
		return isReported;
	}

	public void setIsReported(Integer isReported) {
		this.isReported = isReported;
	}

	// exported key end

	/*
	 * cross check dengan all database kalau belum cocok berarti harus
	 * ditambahin
	 * 
	 */
	@Column(name = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "group_policy_number")
	public String getGroupPolicyNumber() {
		return groupPolicyNumber;
	}

	public void setGroupPolicyNumber(String groupPolicyNumber) {
		this.groupPolicyNumber = groupPolicyNumber;
	}

	@Column(name = "member_other_number")
	public String getMemberOtherNumber() {
		return memberOtherNumber;
	}

	public void setMemberOtherNumber(String memberOtherNumber) {
		this.memberOtherNumber = memberOtherNumber;
	}

	@Column(name = "member_policy_number")
	public String getMemberPolicyNumber() {
		return memberPolicyNumber;
	}

	public void setMemberPolicyNumber(String memberPolicyNumber) {
		this.memberPolicyNumber = memberPolicyNumber;
	}

	@Column(name = "policy_id")
	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	@Column(name="nomor_surat_rujukan")
	public String getNomorSuratRujukan() {
		return nomorSuratRujukan;
	}

	public void setNomorSuratRujukan(String nomorSuratRujukan) {
		this.nomorSuratRujukan = nomorSuratRujukan;
	}

	@Column(name="is_rujukan")
	public Integer getIsRujukan() {
		return isRujukan;
	}

	public void setIsRujukan(Integer isRujukan) {
		this.isRujukan = isRujukan;
	}

	@Column(name="claim_rujukan_id")
	public Integer getClaimRujukanId() {
		return claimRujukanId;
	}

	public void setClaimRujukanId(Integer claimRujukanId) {
		this.claimRujukanId = claimRujukanId;
	}

	@Column(name="doctor_name")
	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	@Column(name="provider_referral_id")
	public Integer getProviderReferralId() {
		return providerReferralId;
	}

	public void setProviderReferralId(Integer providerReferralId) {
		this.providerReferralId = providerReferralId;
	}

	@Column(name="edc_claim_number")
	public String getEdcClaimNumber() {
		return edcClaimNumber;
	}

	public void setEdcClaimNumber(String edcClaimNumber) {
		this.edcClaimNumber = edcClaimNumber;
	}

	@Column(name="terminal_id")
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Column(name="merchant_id")
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	@Column(name="edc_trace_number")
	public String getEdcTraceNumber() {
		return edcTraceNumber;
	}

	public void setEdcTraceNumber(String edcTraceNumber) {
		this.edcTraceNumber = edcTraceNumber;
	}

	@Column(name="is_edc_batch_assigned")
	public Integer getIsEDCBatchAssigned() {
		return isEDCBatchAssigned;
	}

	public void setIsEDCBatchAssigned(Integer isEDCBatchAssigned) {
		this.isEDCBatchAssigned = isEDCBatchAssigned;
	}

	@Column(name="is_ex_gratia")
	public Integer getIsExGratia() {
		return isExGratia;
	}

	public void setIsExGratia(Integer isExGratia) {
		this.isExGratia = isExGratia;
	}

	@Column(name="room_upgrade_type")
	public Integer getRoomUpgradeType() {
		return roomUpgradeType;
	}

	public void setRoomUpgradeType(Integer roomUpgradeType) {
		this.roomUpgradeType = roomUpgradeType;
	}

	@Column(name="current_room_rate")
	public Double getCurrentRoomRate() {
		return currentRoomRate;
	}

	public void setCurrentRoomRate(Double currentRoomRate) {
		this.currentRoomRate = currentRoomRate;
	}

	@Column(name="product_room_rate")
	public Double getProductRoomRate() {
		return productRoomRate;
	}

	public void setProductRoomRate(Double productRoomRate) {
		this.productRoomRate = productRoomRate;
	}

	@Column(name="is_clausul_excluded")
	public Integer getIsClausulExcluded() {
		return isClausulExcluded;
	}

	public void setIsClausulExcluded(Integer isClausulExcluded) {
		this.isClausulExcluded = isClausulExcluded;
	}
	@Column(name="clausul_reference")
	public String getClausulReference() {
		return clausulReference;
	}

	public void setClausulReference(String clausulReference) {
		this.clausulReference = clausulReference;
	}

	@Column(name="reconcile_time")
	public Timestamp getReconcileTime() {
		return reconcileTime;
	}

	public void setReconcileTime(Timestamp reconcileTime) {
		this.reconcileTime = reconcileTime;
	}

	@Column(name="void_trace_number")
	public String getVoidTraceNumber() {
		return voidTraceNumber;
	}

	public void setVoidTraceNumber(String voidTraceNumber) {
		this.voidTraceNumber = voidTraceNumber;
	}

	@Column(name="cob_claim_charge")
	public Double getCobClaimCharge() {
		return cobClaimCharge;
	}

	public void setCobClaimCharge(Double cobClaimCharge) {
		this.cobClaimCharge = cobClaimCharge;
	}

	@Column(name="cob_status")
	public Integer getCobStatus() {
		return cobStatus;
	}

	public void setCobStatus(Integer cobStatus) {
		this.cobStatus = cobStatus;
	}

	@Column(name="cob_claim_paid_value")
	public Double getCobClaimPaidValue() {
		return cobClaimPaidValue;
	}

	public void setCobClaimPaidValue(Double cobClaimPaidValue) {
		this.cobClaimPaidValue = cobClaimPaidValue;
	}

	@Column(name="cob_request_date")
	public Date getCobRequestDate() {
		return cobRequestDate;
	}

	public void setCobRequestDate(Date cobRequestDate) {
		this.cobRequestDate = cobRequestDate;
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="claim_currency_id")
	public Currency getClaimCurrencyId() {
		return claimCurrencyId;
	}

	public void setClaimCurrencyId(Currency claimCurrencyId) {
		this.claimCurrencyId = claimCurrencyId;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="exchange_rate_id")
	public ExchangeRate getExchangeRateId() {
		return exchangeRateId;
	}

	public void setExchangeRateId(ExchangeRate exchangeRateId) {
		this.exchangeRateId = exchangeRateId;
	}

	@Column(name="exchange_rate")
	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name="electronic_ref_number")
	public String getElectronicRefNumber() {
		return electronicRefNumber;
	}

	public void setElectronicRefNumber(String electronicRefNumber) {
		this.electronicRefNumber = electronicRefNumber;
	}

	@Column(name="product_currency_charge_value")
	public Double getProductCurrencyChargeValue() {
		return productCurrencyChargeValue;
	}

	public void setProductCurrencyChargeValue(Double productCurrencyChargeValue) {
		this.productCurrencyChargeValue = productCurrencyChargeValue;
	}

	@Column(name="product_currency_approved_value")
	public Double getProductCurrencyApprovedValue() {
		return productCurrencyApprovedValue;
	}

	public void setProductCurrencyApprovedValue(Double productCurrencyApprovedValue) {
		this.productCurrencyApprovedValue = productCurrencyApprovedValue;
	}

	@Column(name="product_currency_excess_value")
	public Double getProductCurrencyExcessValue() {
		return productCurrencyExcessValue;
	}

	public void setProductCurrencyExcessValue(Double productCurrencyExcessValue) {
		this.productCurrencyExcessValue = productCurrencyExcessValue;
	}

	@ManyToOne
	@JoinColumn(name="product_currency_id")
	public Currency getProductCurrencyId() {
		return productCurrencyId;
	}

	public void setProductCurrencyId(Currency productCurrencyId) {
		this.productCurrencyId = productCurrencyId;
	}

	@Column(name="exchange_rate_date")
	public Date getExchangeRateDate() {
		return exchangeRateDate;
	}

	public void setExchangeRateDate(Date exchangeRateDate) {
		this.exchangeRateDate = exchangeRateDate;
	}

	@Column(name="void_notes")
	public String getVoidNotes() {
		return voidNotes;
	}

	public void setVoidNotes(String voidNotes) {
		this.voidNotes = voidNotes;
	}

	@ManyToOne
	@JoinColumn(name="secondary_status")	
	public CaseStatus getSecondaryStatus() {
		return secondaryStatus;
	}

	public void setSecondaryStatus(CaseStatus secondaryStatus) {
		this.secondaryStatus = secondaryStatus;
	}

	public Boolean getIsReconciled() {
		return isReconciled;
	}

	public void setIsReconciled(Boolean isReconciled) {
		this.isReconciled = isReconciled;
	}

	@Column(name="lab_document")
	public Integer getLabDocument() {
		return labDocument;
	}

	public void setLabDocument(Integer labDocument) {
		this.labDocument = labDocument;
	}

	@Column(name="prescription_document")
	public Integer getPrescriptionDocument() {
		return prescriptionDocument;
	}

	public void setPrescriptionDocument(Integer prescriptionDocument) {
		this.prescriptionDocument = prescriptionDocument;
	}

	@Column(name="edc_document")
	public Integer getEdcDocument() {
		return edcDocument;
	}

	public void setEdcDocument(Integer edcDocument) {
		this.edcDocument = edcDocument;
	}

	@Column(name="gl_document")
	public Integer getGlDocument() {
		return glDocument;
	}

	public void setGlDocument(Integer glDocument) {
		this.glDocument = glDocument;
	}

	@Column(name="invoice_document")
	public Integer getInvoiceDocument() {
		return invoiceDocument;
	}

	public void setInvoiceDocument(Integer invoiceDocument) {
		this.invoiceDocument = invoiceDocument;
	}

	@Column(name="referal_document")
	public Integer getReferalDocument() {
		return referalDocument;
	}

	public void setReferalDocument(Integer referalDocument) {
		this.referalDocument = referalDocument;
	}

	@Column(name="recap_time")
	public Timestamp getRecapTime() {
		return recapTime;
	}

	public void setRecapTime(Timestamp recapTime) {
		this.recapTime = recapTime;
	}

	@Column(name="recap_by")
	public String getRecapBy() {
		return recapBy;
	}

	public void setRecapBy(String recapBy) {
		this.recapBy = recapBy;
	}

	@Column(name="recap_status")
	public Integer getRecapStatus() {
		return recapStatus;
	}

	public void setRecapStatus(Integer recapStatus) {
		this.recapStatus = recapStatus;
	}

	@Column(name="product_currency_policy_approved_value")
	public Double getProductCurrencyPolicyApprovedValue() {
		return productCurrencyPolicyApprovedValue;
	}

	public void setProductCurrencyPolicyApprovedValue(
			Double productCurrencyPolicyApprovedValue) {
		this.productCurrencyPolicyApprovedValue = productCurrencyPolicyApprovedValue;
	}

	@Column(name="claim_policy_approved_value")
	public Double getClaimPolicyApprovedValue() {
		return claimPolicyApprovedValue;
	}

	public void setClaimPolicyApprovedValue(Double claimPolicyApprovedValue) {
		this.claimPolicyApprovedValue = claimPolicyApprovedValue;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="policy_benefit_id")
	public PolicyBenefit getPolicyBenefitId() {
		return policyBenefitId;
	}

	public void setPolicyBenefitId(PolicyBenefit policyBenefitId) {
		this.policyBenefitId = policyBenefitId;
	}

	@Column(name="is_using_policy_benefit")
	public Integer getIsUsingPolicyBenefit() {
		return isUsingPolicyBenefit;
	}

	public void setIsUsingPolicyBenefit(Integer isUsingPolicyBenefit) {
		this.isUsingPolicyBenefit = isUsingPolicyBenefit;
	}

	@Column(name="card_number")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cob_claim_reference_id")
	public Claim getCobClaimReferenceId() {
		return cobClaimReferenceId;
	}

	public void setCobClaimReferenceId(Claim cobClaimReferenceId) {
		this.cobClaimReferenceId = cobClaimReferenceId;
	}

	@Column(name="escalation_total_day")
	public Double getEscalationTotalDay() {
		return escalationTotalDay;
	}

	public void setEscalationTotalDay(Double escalationTotalDay) {
		this.escalationTotalDay = escalationTotalDay;
	}

	@Column(name="successful_delivery")
	public Integer getSuccessfulDelivery() {
		return successfulDelivery;
	}

	public void setSuccessfulDelivery(Integer successfulDelivery) {
		this.successfulDelivery = successfulDelivery;
	}

	@Column(name="is_using_smm")
	public Integer getIsUsingSMM() {
		return isUsingSMM;
	}

	public void setIsUsingSMM(Integer isUsingSMM) {
		this.isUsingSMM = isUsingSMM;
	}

	@Column(name="is_using_multi_layer_limit")
	public Integer getIsUsingMultiLayerLimit() {
		return isUsingMultiLayerLimit;
	}

	public void setIsUsingMultiLayerLimit(Integer isUsingMultiLayerLimit) {
		this.isUsingMultiLayerLimit = isUsingMultiLayerLimit;
	}

	@ManyToOne
	@JoinColumn(name="smm_layer_id")
	public MemberLimitLayer getSmmLayerId() {
		return smmLayerId;
	}

	public void setSmmLayerId(MemberLimitLayer smmLayerId) {
		this.smmLayerId = smmLayerId;
	}

	@ManyToOne
	@JoinColumn(name="multi_layer_id")
	public MemberLimitLayer getMultiLayerId() {
		return multiLayerId;
	}

	public void setMultiLayerId(MemberLimitLayer multiLayerId) {
		this.multiLayerId = multiLayerId;
	}

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "client_id", insertable = false, updatable = false)
	public Client getClient(){
		return this.client;
	}

	/** Client */
	public void setClient(Client obj){
		this.client = obj;
	}

	@Column(name="rejection_remarks")
	public String getRejectionRemarks() {
		return rejectionRemarks;
	}

	public void setRejectionRemarks(String rejectionRemarks) {
		this.rejectionRemarks = rejectionRemarks;
	}

	@Column(name="benefit_checked_time")
	public Timestamp getBenefitCheckedTime() {
		return benefitCheckedTime;
	}

	public void setBenefitCheckedTime(Timestamp benefitCheckedTime) {
		this.benefitCheckedTime = benefitCheckedTime;
	}

	@Column(name="excess_remarks")
	public String getExcessRemarks() {
		return excessRemarks;
	}

	public void setExcessRemarks(String excessRemarks) {
		this.excessRemarks = excessRemarks;
	}

	@Column(name="refund")
	public Double getRefund() {
		return refund;
	}

	public void setRefund(Double refund) {
		this.refund = refund;
	}

	@Column(name="pre_paid_excess")
	public Double getPrePaidExcess() {
		return prePaidExcess;
	}

	public void setPrePaidExcess(Double prePaidExcess) {
		this.prePaidExcess = prePaidExcess;
	}

	@Column(name="paid_to_provider")
	public Double getPaidToProvider() {
		return paidToProvider;
	}

	public void setPaidToProvider(Double paidToProvider) {
		this.paidToProvider = paidToProvider;
	}

	@Column(name="is_paid")
	public Integer getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}
	

	@Column(name="pre_claim_correction_number")
	public String getPreClaimCorrectionNumber() {
		return preClaimCorrectionNumber;
	}

	public void setPreClaimCorrectionNumber(String preClaimCorrectionNumber) {
		this.preClaimCorrectionNumber = preClaimCorrectionNumber;
	}

	@Column(name="client_payment_voucher_number")
	public String getClientPaymentVoucherNumber() {
		return clientPaymentVoucherNumber;
	}

	public void setClientPaymentVoucherNumber(String clientPaymentVoucherNumber) {
		this.clientPaymentVoucherNumber = clientPaymentVoucherNumber;
	}

	@Column(name="client_payment_approval_status")
	public Integer getClientPaymentApprovalStatus() {
		return clientPaymentApprovalStatus;
	}

	public void setClientPaymentApprovalStatus(Integer clientPaymentApprovalStatus) {
		this.clientPaymentApprovalStatus = clientPaymentApprovalStatus;
	}

	@Column(name="client_payment_voucher_date")
	public Date getClientPaymentVoucherDate() {
		return clientPaymentVoucherDate;
	}

	public void setClientPaymentVoucherDate(Date clientPaymentVoucherDate) {
		this.clientPaymentVoucherDate = clientPaymentVoucherDate;
	}

	@Column(name="currency")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name="client_paid_approval_amount")
	public String getClientPaidApprovalAmount() {
		return clientPaidApprovalAmount;
	}

	public void setClientPaidApprovalAmount(String clientPaidApprovalAmount) {
		this.clientPaidApprovalAmount = clientPaidApprovalAmount;
	}

	@Column(name="bank_code_from_client")
	public String getBankCodeFromClient() {
		return bankCodeFromClient;
	}

	public void setBankCodeFromClient(String bankCodeFromClient) {
		this.bankCodeFromClient = bankCodeFromClient;
	}

	@Column(name="bank_name_from_client")
	public String getBankNameFromClient() {
		return bankNameFromClient;
	}

	public void setBankNameFromClient(String bankNameFromClient) {
		this.bankNameFromClient = bankNameFromClient;
	}

	@Column(name="account_holder_name_from_client")
	public String getAccountHolderNameFromClient() {
		return accountHolderNameFromClient;
	}

	public void setAccountHolderNameFromClient(String accountHolderNameFromClient) {
		this.accountHolderNameFromClient = accountHolderNameFromClient;
	}

	@Column(name="account_number_from_client")
	public String getAccountNumberFromClient() {
		return accountNumberFromClient;
	}

	public void setAccountNumberFromClient(String accountNumberFromClient) {
		this.accountNumberFromClient = accountNumberFromClient;
	}

	@Column(name="client_payment_category")
	public String getClientPaymentCategory() {
		return clientPaymentCategory;
	}

	public void setClientPaymentCategory(String clientPaymentCategory) {
		this.clientPaymentCategory = clientPaymentCategory;
	}

	@Column(name="manual_registration")
	public Integer getManualRegistration() {
		return manualRegistration;
	}

	public void setManualRegistration(Integer manualRegistration) {
		this.manualRegistration = manualRegistration;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_installment_id")
	public PaymentInstallment getPaymentInstallmentId() {
		return paymentInstallmentId;
	}

	public void setPaymentInstallmentId(PaymentInstallment paymentInstallmentId) {
		this.paymentInstallmentId = paymentInstallmentId;
	}
	
	@ManyToOne
	@JoinColumn(name = "policy_id", referencedColumnName = "policy_id", insertable = false, updatable = false)
	public Policy getPolicy(){
		return this.policy;
	}

	/** Client */
	public void setPolicy(Policy obj){
		this.policy = obj;
	}
	
	public ClaimExchangeDto exportExchangeDto(){
		ClaimExchangeDto claim = new ClaimExchangeDto();
		
		if(memberId != null){
			claim.setClientCode(memberId.getClientId().getClientCode());
			claim.setGroupCode(memberId.getMemberGroupId().getMemberGroupCode());
			claim.setMemberId(memberId.getMemberId());
			claim.setClientMemberId(memberId.getCustomerPolicyNumber());
			claim.setMemberName(memberId.getFirstName());
		}
		
		if(policyId != null){
			claim.setPolicyNo(policy.getPolicyNumber());
		}
		
		claim.setClaimsId(claimNumber);
		claim.setClaimsStatus(claimStatus.getCaseStatusName());
		claim.setClaimsProcessStatus(claimStatus.getCaseStatusName());
		claim.setCardNumber(cardNumber);
		
		if(caseCategoryId != null){
			claim.setProdType(caseCategoryId.getCaseCategoryCode());
		}
		
		claim.setClaimsType(claim.getClaimsType());
		
		if(productId != null){
			claim.setProductId(productId.getProductId().getProductCode());
		}
		
		if(admissionDate != null){
			claim.setAdmissionDate(admissionDate);
		}
		
		if(dischargeDate != null){
			claim.setDischargeDate(dischargeDate);
		}
		
		if(diagnosisId != null){
			claim.setIcdCode(diagnosisId.getDiagnosisCode());
			claim.setDiagnosisDesc(diagnosisId.getDescription());
		}
		
		if(diagnosis2Id != null){
			claim.setIcdCode2(diagnosis2Id.getDiagnosisCode());
			claim.setDiagnosisDesc2(diagnosis2Id.getDescription());
		}
		
		if(diagnosis3Id != null){
			claim.setIcdCode(diagnosis3Id.getDiagnosisCode());
			claim.setDiagnosisDesc(diagnosis3Id.getDescription());
		}
		
	    if(providerId != null){
	    	claim.setProviderCode(providerId.getProviderCode());
	    	claim.setProviderName(providerId.getProviderName());
	    	
	    	claim.setProviderCodeReimburse(providerId.getProviderCode());
	    	claim.setProviderNameReimburse(providerId.getProviderName());
	    }
	    
	    if(claimCurrencyId != null){
	    	claim.setCurrency(claimCurrencyId.getCurrencyCode());
	    }
		
	    claim.setAmountIncurred(claimChargeValue);
	    claim.setAmountApprove(claimApprovedValue);
	    claim.setAmountExcess(claimExcessValue);
	    claim.setAmountExcessPaid(prePaidExcess);
	    claim.setAmountPaidToProvider(paidToProvider);
	    claim.setAmountExcessByProvider(prePaidExcess);
	    claim.setAmountRefund(refund);
	    
	    claim.setDoctorName(doctorName);
	    
	    if(batchClaimId != null){
	    	claim.setBatchNumber(batchClaimId.getBatchClaimNumber());
		    claim.setProviderInvoiceNo(batchClaimId.getInvoiceNumber());
		    claim.setProviderInvoiceDate(batchClaimId.getInvoiceDate());
	    }
	    
	    if(approvedTime != null){
	    	Date approvedDate = new Date(approvedTime.getTime());
		    claim.setApproveDate(approvedDate);
	    }
	    
	    /*
	     	RECEIVED DATE
			DATE REGISTER 
			SUBMISSION DATE
			DATE DUE 
			LOS
			ROOM USAGE
	     */
	    
	     claim.setRemarks(claimRemarks);
	     
	     //return the claim header fufufu
	    
		return claim;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invoice_item_id")
	public InvoiceItem getInvoiceItemId() {
		return invoiceItemId;
	}

	public void setInvoiceItemId(InvoiceItem invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}

	@Column(name="billing_rate")
	public Double getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Double billingRate) {
		this.billingRate = billingRate;
	}

	@Column(name="is_billed")
	public Integer getIsBilled() {
		return isBilled;
	}

	public void setIsBilled(Integer isBilled) {
		this.isBilled = isBilled;
	}
	
	
}