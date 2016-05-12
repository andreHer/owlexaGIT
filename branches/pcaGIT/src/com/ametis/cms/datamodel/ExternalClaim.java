
package com.ametis.cms.datamodel;


import com.ametis.cms.util.Converter;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="external_claim")
public class ExternalClaim implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
        public static final String CLAIM_NUMBER = "ClaimNumber";
        public static final String BATCH_NUMBER = "BatchNumber";
        public static final String ADMISSION_DATE = "AdmissionDate";
        public static final String DISCHARGE_DATE = "DischargeDate";
        public static final String CASE_CATEGORY = "CaseCategory";
        public static final String CLAIM_TYPE = "ClaimType";
        public static final String CLAIM_CHARGE = "ClaimChargeValue";
        public static final String CLAIM_APPROVED_VALUE = "ClaimApprovedValue";
        public static final String CLAIM_EXCESS_VALUE = "ClaimExcessValue";
        public static final String CLAIM_EX_GRATIA = "ClaimExGratiaValue";
        public static final String POLICY_NUMBER = "PolicyNumber";
        public static final String CUSTOMER_NUMBER = "CustomerNumber";
        public static final String MEMBER_NAME = "CustomerName";
        public static final String DIAGNOSE_CODE = "DiagnoseCode";
        public static final String PROVIDER_NAME = "ProviderName";
        public static final String PROVIDER_CODE = "ProviderCode";
        public static final String PAYMENT_NUMBER = "PaymentNumber";
        public static final String PAYMENT_DATE = "PaymentDate";
        public static final String PLAN_CODE = "PlanCode";
        public static final String BANK_NAME = "BankName";
        public static final String BANK_ACCOUNT_NUMBER = "BankAccountNumber";
        public static final String BANK_ACCOUNT_NAME = "BankAccountName";
        public static final String BANK_BRANCH = "BankBranch";
        public static final String REGISTRATION_DATE = "RegistrationDate";
        public static final String APPROVAL_DATE = "ApprovalDate";
        public static final String USER_ENTRY = "CreatedBy";
        public static final String CREATED_TIME = "CreatedTime";
        public static final String EMPLOYEE_NAME = "EmployeeName";
        public static final String INVOICE_NUMBER = "InvoiceNumber";
        public static final String RELATIONSHIP = "Relationship";
        public static final String BIRTHDATE = "Birthdate";
        public static final String AREA		= "Area";
        public static final String BRANCH	= "Branch";
        public static final String RECEIVED_INSURANCE = "ReceivedInsurance";
        public static final String CURRENCY = "Currency";
        public static final String EXCESS_PAYMENT = "ExcessPayment";
        public static final String EXCESS_PAYMENT_DATE = "ExcessPaymentDate";
        public static final String DIAGNOSIS_NAME = "DiagnosisName";
        public static final String DOCTOR_NAME = "DoctorName";
        public static final String REMARKS = "Remarks";
        public static final String ITEM_CODE = "ItemCode";
        public static final String ITEM_NAME = "ItemName";
        public static final String EMPLOYEE_NUMBER = "EmployeeNumber";
        public static final String INVOICE_DATE = "InvoiceDate";
        
        public static final String CLIENT_CODE = "ClientCode";
        public static final String PAYMENT_VOUCHER_NO = "PaymentVoucherNo";
        public static final String PAYMENT_VOUCHER_DATE = "PaymentVoucherDate";
        public static final String BANK_CODE = "BankCode";
        public static final String CATEGORY_PROCES = "ProcessCategory";
        public static final String AMOUNT_TOTAL = "AmountTotal";
        

	//Fields
		
	/**data for the column :
	
 --------- external_claim.id --------- 
 schema        = null
 tableName     = external_claim
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
	private Integer id;
			
	/**data for the column :
	
 --------- external_claim.claim_number --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String claimNumber;
			
	/**data for the column :
	
 --------- external_claim.claim_category --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String claimCategory;
			
	/**data for the column :
	
 --------- external_claim.claim_type --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String claimType;
			
	/**data for the column :
	
 --------- external_claim.batch_number --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String batchNumber;
			
	/**data for the column :
	
 --------- external_claim.admission_date --------- 
 schema        = null
 tableName     = external_claim
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
	private java.sql.Date admissionDate;
			
	/**data for the column :
	
 --------- external_claim.discharge_date --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private java.sql.Date dischargeDate;
			
	/**data for the column :
	
 --------- external_claim.claim_charge_value --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 8
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double claimChargeValue;
			
	/**data for the column :
	
 --------- external_claim.claim_approved_value --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double claimApprovedValue;
			
	/**data for the column :
	
 --------- external_claim.excess_charge_value --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 10
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double excessChargeValue;
			
	/**data for the column :
	
 --------- external_claim.ex_gratia_value --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 11
 size          = 22
 type          = 8 
 isPrimaryKey  = false

=========================================


*/
	private Double exGratiaValue;
			
	/**data for the column :
	
 --------- external_claim.created_by --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String createdBy;
			
	/**data for the column :
	
 --------- external_claim.created_time --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	
 --------- external_claim.modified_by --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private String modifiedBy;
			
	/**data for the column :
	
 --------- external_claim.modified_time --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 15
 size          = 19
 type          = 93 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Timestamp modifiedTime;
			
	/**data for the column :
	
 --------- external_claim.claim_status --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 16
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String claimStatus;
			
	/**data for the column :
	
 --------- external_claim.member_number --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 17
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberNumber;
			
	/**data for the column :
	
 --------- external_claim.member_name --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 18
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String memberName;
			
	/**data for the column :
	
 --------- external_claim.cdv_number --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 19
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String cdvNumber;
			
	/**data for the column :
	
 --------- external_claim.diagnosis1_code --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 20
 size          = 10
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis1Code;
			
	/**data for the column :
	
 --------- external_claim.diagnosis2_code --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 21
 size          = 10
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis2Code;
			
	/**data for the column :
	
 --------- external_claim.diagnosis3_code --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 22
 size          = 10
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String diagnosis3Code;
			
	/**data for the column :
	
 --------- external_claim.remaining_limit --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
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
	private Double remainingLimit;
			
	/**data for the column :
	
 --------- external_claim.provider_code --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 24
 size          = 15
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String providerCode;
			
	/**data for the column :
	
 --------- external_claim.provider_name --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 25
 size          = 255
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String providerName;
			
	/**data for the column :
	
 --------- external_claim.registration_time --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 26
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date registrationTime;
			
	/**data for the column :
	
 --------- external_claim.verification_time --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 27
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date verificationTime;
			
	/**data for the column :
	
 --------- external_claim.payment_time --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 28
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date paymentTime;
			
	/**data for the column :
	
 --------- external_claim.location --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 29
 size          = 20
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String location;
			
	/**data for the column :
	
 --------- external_claim.cdv_generated_date --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 30
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date cdvGeneratedDate;
			
	/**data for the column :
	
 --------- external_claim.cdv_confirm_date --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 31
 size          = 10
 type          = 91 
 isPrimaryKey  = false

=========================================


*/
	private java.sql.Date cdvConfirmDate;
			
	/**data for the column :
	
 --------- external_claim.is_migrated --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = 0
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 32
 size          = 2
 type          = 4 
 isPrimaryKey  = false

=========================================


*/
	private Integer isMigrated;
			
	/**data for the column :
	
 --------- external_claim.pay_to --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 33
 size          = 50
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String payTo;
			
	/**data for the column :
	
 --------- external_claim.account_no --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 34
 size          = 155
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String accountNo;
			
	/**data for the column :
	
 --------- external_claim.bank_account --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 35
 size          = 155
 type          = 12 
 isPrimaryKey  = false

=========================================


*/
	private String bankAccount;
			
	/**data for the column :
	
 --------- external_claim.bank_name --------- 
 schema        = null
 tableName     = external_claim
 foreignCol    = 
 foreignTab    = 
 catalog       = insura
 remarks       = 
 defaultValue  = null
 decDigits     = 0
 radix         = 10
 nullable      = 1
 ordinal       = 36
 size          = 155
 type          = 12 
 isPrimaryKey  = false

=========================================


*/

	private String bankName;    
    private String relationship;
    private String birthdate;
    private String branch;
    private String receivedInsurance;
    private String currency;
    private String excessPayment;
    private String excessPaymentDate;
    private String diagnosisName;
    private String doctorName;
    private String remarks;
    private String itemCode;
    private String itemName;
    private String employeeNumber;
    private String employeeName;
    private String invoiceNumber;
    private String invoiceDate;
		
    
    public String paymentVoucherNo;
    public Date paymentVoucherDate;
    public String bankCode;
    public String processCategory;
    public Double amountTotal;
    private String clientCode;
	// foreign affairs
	
		
	// -- foreign affairs end

	// -- exported key

	
		
	// -- exported key end

	// Fields End
        private Integer importSessionId;


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

						@Column(name="claim_number")
	public java.lang.String getClaimNumber(){
		return claimNumber;
	}
	public void setClaimNumber(java.lang.String value){
		claimNumber = value;
	}
				@Column(name="claim_category")
	public java.lang.String getClaimCategory(){
		return claimCategory;
	}
	public void setClaimCategory(java.lang.String value){
		claimCategory = value;
	}
				@Column(name="claim_type")
	public java.lang.String getClaimType(){
		return claimType;
	}
	public void setClaimType(java.lang.String value){
		claimType = value;
	}
				@Column(name="batch_number")
	public java.lang.String getBatchNumber(){
		return batchNumber;
	}
	public void setBatchNumber(java.lang.String value){
		batchNumber = value;
	}
				@Column(name="admission_date")
	public java.sql.Date getAdmissionDate(){
		return admissionDate;
	}
	public void setAdmissionDate(java.sql.Date value){
		admissionDate = value;
	}
				@Column(name="discharge_date")
	public java.sql.Date getDischargeDate(){
		return dischargeDate;
	}
	public void setDischargeDate(java.sql.Date value){
		dischargeDate = value;
	}
				@Column(name="claim_charge_value")
	public java.lang.Double getClaimChargeValue(){
		return claimChargeValue;
	}
	public void setClaimChargeValue(java.lang.Double value){
		claimChargeValue = value;
	}
				@Column(name="claim_approved_value")
	public java.lang.Double getClaimApprovedValue(){
		return claimApprovedValue;
	}
	public void setClaimApprovedValue(java.lang.Double value){
		claimApprovedValue = value;
	}
				@Column(name="excess_charge_value")
	public java.lang.Double getExcessChargeValue(){
		return excessChargeValue;
	}
	public void setExcessChargeValue(java.lang.Double value){
		excessChargeValue = value;
	}
				@Column(name="ex_gratia_value")
	public java.lang.Double getExGratiaValue(){
		return exGratiaValue;
	}
	public void setExGratiaValue(java.lang.Double value){
		exGratiaValue = value;
	}
				@Column(name="created_by")
	public java.lang.String getCreatedBy(){
		return createdBy;
	}
	public void setCreatedBy(java.lang.String value){
		createdBy = value;
	}
				@Column(name="created_time")
	public java.sql.Timestamp getCreatedTime(){
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp value){
		createdTime = value;
	}
				@Column(name="modified_by")
	public java.lang.String getModifiedBy(){
		return modifiedBy;
	}
	public void setModifiedBy(java.lang.String value){
		modifiedBy = value;
	}
				@Column(name="modified_time")
	public java.sql.Timestamp getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(java.sql.Timestamp value){
		modifiedTime = value;
	}
				@Column(name="claim_status")
	public java.lang.String getClaimStatus(){
		return claimStatus;
	}
	public void setClaimStatus(java.lang.String value){
		claimStatus = value;
	}
				@Column(name="member_number")
	public java.lang.String getMemberNumber(){
		return memberNumber;
	}
	public void setMemberNumber(java.lang.String value){
		memberNumber = value;
	}
				@Column(name="member_name")
	public java.lang.String getMemberName(){
		return memberName;
	}
	public void setMemberName(java.lang.String value){
		memberName = value;
	}
				@Column(name="cdv_number")
	public java.lang.String getCdvNumber(){
		return cdvNumber;
	}
	public void setCdvNumber(java.lang.String value){
		cdvNumber = value;
	}
				@Column(name="diagnosis1_code")
	public java.lang.String getDiagnosis1Code(){
		return diagnosis1Code;
	}
	public void setDiagnosis1Code(java.lang.String value){
		diagnosis1Code = value;
	}
				@Column(name="diagnosis2_code")
	public java.lang.String getDiagnosis2Code(){
		return diagnosis2Code;
	}
	public void setDiagnosis2Code(java.lang.String value){
		diagnosis2Code = value;
	}
				@Column(name="diagnosis3_code")
	public java.lang.String getDiagnosis3Code(){
		return diagnosis3Code;
	}
	public void setDiagnosis3Code(java.lang.String value){
		diagnosis3Code = value;
	}
				@Column(name="remaining_limit")
	public java.lang.Double getRemainingLimit(){
		return remainingLimit;
	}
	public void setRemainingLimit(java.lang.Double value){
		remainingLimit = value;
	}
				@Column(name="provider_code")
	public java.lang.String getProviderCode(){
		return providerCode;
	}
	public void setProviderCode(java.lang.String value){
		providerCode = value;
	}
				@Column(name="provider_name")
	public java.lang.String getProviderName(){
		return providerName;
	}
	public void setProviderName(java.lang.String value){
		providerName = value;
	}
				@Column(name="registration_time")
	public java.sql.Date getRegistrationTime(){
		return registrationTime;
	}
	public void setRegistrationTime(java.sql.Date value){
		registrationTime = value;
	}
				@Column(name="verification_time")
	public java.sql.Date getVerificationTime(){
		return verificationTime;
	}
	public void setVerificationTime(java.sql.Date value){
		verificationTime = value;
	}
				@Column(name="payment_time")
	public java.sql.Date getPaymentTime(){
		return paymentTime;
	}
	public void setPaymentTime(java.sql.Date value){
		paymentTime = value;
	}
				@Column(name="location")
	public java.lang.String getLocation(){
		return location;
	}
	public void setLocation(java.lang.String value){
		location = value;
	}
				@Column(name="cdv_generated_date")
	public java.sql.Date getCdvGeneratedDate(){
		return cdvGeneratedDate;
	}
	public void setCdvGeneratedDate(java.sql.Date value){
		cdvGeneratedDate = value;
	}
				@Column(name="cdv_confirm_date")
	public java.sql.Date getCdvConfirmDate(){
		return cdvConfirmDate;
	}
	public void setCdvConfirmDate(java.sql.Date value){
		cdvConfirmDate = value;
	}
				@Column(name="is_migrated")
	public java.lang.Integer getIsMigrated(){
		return isMigrated;
	}
	public void setIsMigrated(java.lang.Integer value){
		isMigrated = value;
	}
				@Column(name="pay_to")
	public java.lang.String getPayTo(){
		return payTo;
	}
	public void setPayTo(java.lang.String value){
		payTo = value;
	}
				@Column(name="account_no")
	public java.lang.String getAccountNo(){
		return accountNo;
	}
	public void setAccountNo(java.lang.String value){
		accountNo = value;
	}
				@Column(name="bank_account")
	public java.lang.String getBankAccount(){
		return bankAccount;
	}
	public void setBankAccount(java.lang.String value){
		bankAccount = value;
	}
				@Column(name="bank_name")
	public java.lang.String getBankName(){
		return bankName;
	}
	public void setBankName(java.lang.String value){
		bankName = value;
	}

		
        
        public void setValue (String key, String value){
            System.out.println("KEY = "+ key + " VALUE = " + value);
        	
            if (key.equalsIgnoreCase(MEMBER_NAME)){
                this.memberName = value;
            }
            if (key.equalsIgnoreCase(CUSTOMER_NUMBER)){
                this.memberNumber = value;
            }
            if (key.equalsIgnoreCase(ADMISSION_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.admissionDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(DISCHARGE_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.dischargeDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(BANK_ACCOUNT_NAME)){
                this.bankAccount = value;
            }
            if (key.equalsIgnoreCase(BANK_ACCOUNT_NUMBER)){
                this.accountNo = value;
            }
            if (key.equalsIgnoreCase(BANK_NAME)){
                this.bankName = value;
            }
            if (key.equalsIgnoreCase(BATCH_NUMBER)){
                this.batchNumber = value;
            }
            if (key.equalsIgnoreCase(CASE_CATEGORY)){
                this.claimCategory = value;
            }
            if (key.equalsIgnoreCase(CLAIM_APPROVED_VALUE)){
                this.claimApprovedValue = Double.valueOf(value);
            }
            if (key.equalsIgnoreCase(CLAIM_CHARGE)){
                this.claimChargeValue = Double.valueOf(value);
            }
            if (key.equalsIgnoreCase(CLAIM_EXCESS_VALUE)){
                this.excessChargeValue = Double.valueOf(value);
            }
            if (key.equalsIgnoreCase(CLAIM_EX_GRATIA)){
                this.exGratiaValue = Double.valueOf(value);
            }
            if (key.equalsIgnoreCase(CLAIM_NUMBER)){
                this.claimNumber = value;
            }
            if (key.equalsIgnoreCase(CLAIM_TYPE)){
                this.claimType = value;
            }
            if (key.equalsIgnoreCase(DIAGNOSE_CODE)){
                this.diagnosis1Code  = value;
            }
            if (key.equalsIgnoreCase(PAYMENT_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.cdvConfirmDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(PAYMENT_NUMBER)){
                this.cdvNumber = value;
            }
            if (key.equalsIgnoreCase(PROVIDER_CODE)){
                this.providerCode = value;
            }
            if (key.equalsIgnoreCase(PROVIDER_NAME)){
                this.providerName = value;
            }
            if (key.equalsIgnoreCase(REGISTRATION_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                	System.out.println("DATE VAL = " + value);
                    this.registrationTime = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(EMPLOYEE_NAME)){
            	this.employeeName = value;
            }
            if (key.equalsIgnoreCase(INVOICE_NUMBER)){
            	this.invoiceNumber = value;
            }
            if (key.equalsIgnoreCase(RELATIONSHIP)){
            	this.relationship = value;
            }
            if (key.equalsIgnoreCase(BIRTHDATE)){
            	this.birthdate = value;
            }
            if (key.equalsIgnoreCase(AREA)){
            	this.location = value;
            }
            if (key.equalsIgnoreCase(BRANCH)){
            	this.branch = value;
            }
            if (key.equalsIgnoreCase(RECEIVED_INSURANCE)){
            	this.receivedInsurance = value;
            }
            if (key.equalsIgnoreCase(CURRENCY)){
            	this.currency = value;
            }
            if (key.equalsIgnoreCase(EXCESS_PAYMENT)){
            	this.excessPayment = value;
            }
            if (key.equalsIgnoreCase(EXCESS_PAYMENT_DATE)){
            	this.excessPaymentDate = value;
            }
            
            if (key.equalsIgnoreCase(DIAGNOSIS_NAME)){
            	this.diagnosisName = value;
            }
            if (key.equalsIgnoreCase(DOCTOR_NAME)){
            	this.doctorName = value;
            }
            if (key.equalsIgnoreCase(REMARKS)){
            	this.remarks = value;
            }
            if (key.equalsIgnoreCase(ITEM_CODE)){
            	this.itemCode = value;
            }
            if (key.equalsIgnoreCase(ITEM_NAME)){
            	this.itemName = value;
            }
            if (key.equalsIgnoreCase(EMPLOYEE_NUMBER)){
            	this.employeeNumber = value;
            }
            if (key.equalsIgnoreCase(INVOICE_DATE)){
            	this.invoiceDate = value;
            }
            if (key.equalsIgnoreCase(PAYMENT_VOUCHER_NO)){
            	this.paymentVoucherNo = value;
            }
            if (key.equalsIgnoreCase(PAYMENT_VOUCHER_DATE)){
            	if (value != null && !value.equalsIgnoreCase("")){
            		this.paymentVoucherDate = Date.valueOf(value);
            	}
            }
            if (key.equalsIgnoreCase(BANK_CODE)){
            	this.bankCode = value;
            }
            if (key.equalsIgnoreCase(CATEGORY_PROCES)){
            	this.processCategory = value;
            }
            if (key.equalsIgnoreCase(AMOUNT_TOTAL)){
            	if (value != null && !value.equalsIgnoreCase("")){
            		this.amountTotal = Double.valueOf(value);
            	}
            }
            
            
        }
        
        @Column(name="payment_voucher_no")
        public String getPaymentVoucherNo() {
			return paymentVoucherNo;
		}
		public void setPaymentVoucherNo(String paymentVoucherNo) {
			this.paymentVoucherNo = paymentVoucherNo;
		}
		@Column(name="payment_voucher_date")
		public Date getPaymentVoucherDate() {
			return paymentVoucherDate;
		}
		public void setPaymentVoucherDate(Date paymentVoucherDate) {
			this.paymentVoucherDate = paymentVoucherDate;
		}
		@Column(name="bank_code")
		public String getBankCode() {
			return bankCode;
		}
		public void setBankCode(String bankCode) {
			this.bankCode = bankCode;
		}
		@Column(name="process_category")
		public String getProcessCategory() {
			return processCategory;
		}
		public void setProcessCategory(String processCategory) {
			this.processCategory = processCategory;
		}
		@Column(name="amount_total")
		public Double getAmountTotal() {
			return amountTotal;
		}
		public void setAmountTotal(Double amountTotal) {
			this.amountTotal = amountTotal;
		}
		@Column(name="import_session_id")
    public Integer getImportSessionId() {
        return importSessionId;
    }

    public void setImportSessionId(Integer importSessionId) {
        this.importSessionId = importSessionId;
    }
    @Column(name="relationship")
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Column(name="birthdate")
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	@Column(name="branch")
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Column(name="received_insurance")
	public String getReceivedInsurance() {
		return receivedInsurance;
	}
	public void setReceivedInsurance(String receivedInsurance) {
		this.receivedInsurance = receivedInsurance;
	}
	@Column(name="currency")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@Column(name="excess_payment")
	public String getExcessPayment() {
		return excessPayment;
	}
	public void setExcessPayment(String excessPayment) {
		this.excessPayment = excessPayment;
	}
	@Column(name="excess_payment_date")
	public String getExcessPaymentDate() {
		return excessPaymentDate;
	}
	public void setExcessPaymentDate(String excessPaymentDate) {
		this.excessPaymentDate = excessPaymentDate;
	}
	@Column(name="diagnosis_name")
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	@Column(name="doctor_name")
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="item_code")
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	@Column(name="item_name")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name="employee_number")
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	@Column(name="employee_name")
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@Column(name="invoice_number")
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	@Column(name="invoice_date")
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	@Column(name="client_code")
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	

}