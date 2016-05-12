
package com.ametis.cms.datamodel;


import com.ametis.cms.util.Converter;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;


@Entity
@Table(name="member_import")
public class MemberImport implements java.io.Serializable{
	
	public static final String ACTION_TYPE_RENEWAL = "RENEWAL";
	public static final String ACTION_TYPE_ADDITION = "ADDITION";
	public static final String ACTION_TYPE_CHANGEPLAN = "CHANGEPLAN";
	public static final String ACTION_TYPE_DELETION = "DELETION";
	public static final String ACTION_TYPE_UPDATE = "UPDATE";
	
	private static final long serialVersionUID = 1L;
    public static final String SWIPE_CARD_NUMBER_INDEX = "SwipeCardNumberIndex";
    public static final String SWIPE_CARD_NUMBER = "SwipeCardNumber";
    public static final String BIRTHDATE = "BirthDate";
    public static final String DEPARTMENT = "Department";
    public static final String EFFECTIVE_DATE = "EffectiveDate";
    public static final String JOIN_DATE = "JoinDate";
    public static final String SEX = "Sex";
    public static final String PRODUCT = "Product";
    public static final String CUSTOMER_NUMBER = "CustomerNumber";
    public static final String MEMBER_NAME = "CustomerName";
    
    public static final String CARD_TEMPLATE = "CardTemplate";
    
    
    public static final String BANK_NAME = "BankName";
    public static final String BANK_ACCOUNT_NUMBER = "BankAccountNumber";
    public static final String BANK_ACCOUNT_NAME = "BankAccountName";
    public static final String BANK_BRANCH = "BankBranch";
    public static final String SWIFT_CODE = "SwiftCode";
    
    public static final String EXPIRE_DATE = "ExpireDate";
    public static final String HANDPHONE = "Handphone";
    public static final String EMAIL = "Email";
    public static final String PARENT_NUMBER = "ParentNumber";
    public static final String PARENT_NAME = "ParentName";
    public static final String IS_VIP = "IsVip";
    public static final String IS_BLACKLIST = "IsBlacklist";
    public static final String IS_WARNING = "IsWarning";
    public static final String POLICY_NUMBER = "PolicyNumber";
    public static final String SALARY = "Salary";
    public static final String RELATIONSHIP = "Relationship";
    
    public static final String CLIENT_CODE = "ClientCode";
    public static final String CLIENT_NAME = "ClientName";
    public static final String GROUP_CODE = "GroupCode";
    public static final String ACTION_TYPE = "ActionType";
    public static final String EMPLOYEE_ID = "EmployeeID";
    
    public static final String JOB_FUNCTION = "JobFunction";
    
    public static final String COB_NUMBER = "CobNumber";
    public static final String ADDITIONAL_INFO = "AdditionalInfo";
    public static final String SUSPEND_START = "SuspendStart";
    public static final String SUSPEND_END = "SuspendEnd";
    public static final String SUBJECT_TO_NO_CLAIM = "SubjectToNoClaim";
    public static final String PRINT_CARD = "PrintCard";
    public static final String NEW_POLICY_NUMBER = "NewPolicyNumber";
    public static final String ANNUAL_LIMIT = "AnnualLimit";
    public static final String GRACE_START = "GraceStart";
    public static final String GRACE_END = "GraceEnd";
    public static final String IMPORT_BATCH_NUMBER = "BatchNumber";
    public static final String SUB_POLICY_NUMBER = "SubPolicyNumber";
    public static final String LINKED_CARD_NUMBER = "LinkedCardNumber";
    public static final String POLICY_EXP_DATE = "PolicyExpireDate";
    public static final String PAYMENT_METHOD = "PaymentMethod";
    public static final String NEXT_MEMBER_NUMBER = "NextMemberNumber";
    public static final String NEXT_POLICY_NUMBER = "NextPolicyNumber";
    
        

    private Integer id;
	private String swipeCardNumberIndex;
	private String swipeCardNumber;	
	private String birthdate;
	private String department;	
	private java.sql.Date effectiveDate;
	private java.sql.Date joinDate;	
	private java.sql.Date expireDate;
	private String sex;
	private String product;		
	private String memberNumber;
	private String memberName;
	private Integer isMigrated;
	private String accountNo;
	private String bankAccount;
	private String bankName;
	private String bankBranch;
    private DataImportStage importSessionId;
    private String handphone;
    private String email;
    private String isVIP;
    private String isBlacklist;
    private String isWarning;
    private String parentNumber;
    private String parentName;
    private String policyNumber;
    private String migrationStatus;
    private String migrationNote;
    private String salary;
    private String relationship;
    private String cardTemplate;
    private String jobFunction;
    private String swiftCode;
    private String bpjsNumber;
    private String suspendStart;
    private String suspendEnd;
    private String noClaimFlag;
    private String isProRate;
    private String annualLimit;
    private Date graceStartDate;
    private Date graceEndDate;
    private String importBatchNumber;
    private String subPolicyNumber;
    private String linkedCardNumber;
    private String printCard;
    
    private String groupCode;
    private String clientCode;
    private String clientName;
    private String policyExpireDate;
    private String additionalInfo;
    
    private String nik;
    private String groupName;
    
    
    private Member memberId;
    
    private String paymentMethod;
    private String nextMemberNumber;
    private String nextPolicyNumber;
    private String nextCardNumber;
    private Date nextExpireDate;
    private Date nextEffectiveDate;
    private String nextNewRelationship;
    private String nextNewGroupCode;
    private String nextNewEmployeeNumber;
    
    private Timestamp createdTime;
    private String createdBy;
    private Timestamp modifiedTime;
    private String modifiedBy;
    private String deletedBy;
    private Timestamp deletedTime;
    private String actionType;
    private Integer deletedStatus;
    private CardPrinting cardPrintingId;
    private String newPolicyNumber;
    private Integer isBilled;
    private InvoiceItem invoiceItemId;
    private Double billingRate;
    private Double refundRate;
    private Policy policyId;
    
    private Integer suspendReactiveStatus;
    
    private String uploadNote;

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

				@Column(name="account_number")
	public java.lang.String getAccountNo(){
		return accountNo;
	}
	public void setAccountNo(java.lang.String value){
		accountNo = value;
	}
				@Column(name="bank_account_name")
	public java.lang.String getBankAccount(){
		return bankAccount;
	}
	public void setBankAccount(java.lang.String value){
		bankAccount = value;
	}
				@Column(name="bank")
	public java.lang.String getBankName(){
		return bankName;
	}
	public void setBankName(java.lang.String value){
		bankName = value;
	}

		
 
	@Column(name="swipe_card_number_index")
    public String getSwipeCardNumberIndex() {
		return swipeCardNumberIndex;
	}
	public void setSwipeCardNumberIndex(String swipeCardNumberIndex) {
		this.swipeCardNumberIndex = swipeCardNumberIndex;
	}
	@Column(name="swipe_card_number")
	public String getSwipeCardNumber() {
		return swipeCardNumber;
	}
	public void setSwipeCardNumber(String swipeCardNumber) {
		this.swipeCardNumber = swipeCardNumber;
	}
	@Column(name="birthdate")
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	@Column(name="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name="effective_date")
	public java.sql.Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(java.sql.Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	@Column(name="join_date")
	public java.sql.Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(java.sql.Date joinDate) {
		this.joinDate = joinDate;
	}
	@Column(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="product")
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	@Column(name="customer_number")
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	@Column(name="member_name")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name="is_migrated")
	public Integer getIsMigrated() {
		return isMigrated;
	}
	public void setIsMigrated(Integer isMigrated) {
		this.isMigrated = isMigrated;
	}
	public void setValue (String key, String value){
            
            if (key.equalsIgnoreCase(MEMBER_NAME)){
                this.memberName = value;
            }
            if (key.equalsIgnoreCase(CUSTOMER_NUMBER)){
                this.memberNumber = value;
            }
            if (key.equalsIgnoreCase(EFFECTIVE_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.effectiveDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(JOIN_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.joinDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(BIRTHDATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.birthdate = value;
                }
            }
            if (key.equalsIgnoreCase(EXPIRE_DATE)){
                if (value != null && !value.trim().equalsIgnoreCase("")){
                    this.expireDate = Date.valueOf(value);
                }
            }
            if (key.equalsIgnoreCase(BANK_ACCOUNT_NAME)){
                this.bankAccount = value;
            }
            if (key.equalsIgnoreCase(BANK_ACCOUNT_NUMBER)){
                this.accountNo = value;
            }
            if (key.equalsIgnoreCase(BANK_BRANCH)){
                this.bankBranch = value;
            }
            if (key.equalsIgnoreCase(BANK_NAME)){
                this.bankName = value;
            }
            if (key.equalsIgnoreCase(SWIFT_CODE)){
            	this.swiftCode = value;
            }
            if (key.equalsIgnoreCase(SEX)){
                this.sex = value;
            }
            if (key.equalsIgnoreCase(DEPARTMENT)){
                this.department = value;
            }
            if (key.equalsIgnoreCase(PRODUCT)){
                this.product = value;
            }
            if (key.equalsIgnoreCase(SWIPE_CARD_NUMBER)){
                this.swipeCardNumber = value;
            }
            if (key.equalsIgnoreCase(SWIPE_CARD_NUMBER_INDEX)){
                this.swipeCardNumberIndex = value;
            }
            if (key.equalsIgnoreCase(HANDPHONE)){
                this.handphone = value;
            }
            if (key.equalsIgnoreCase(SALARY)){
                this.salary = value;
            }
            if (key.equalsIgnoreCase(PARENT_NUMBER)){
                this.parentNumber = value;
            }
            if (key.equalsIgnoreCase(PARENT_NAME)){
                this.parentName = value;
            }
            if (key.equalsIgnoreCase(POLICY_NUMBER)){
                this.policyNumber = value;
            }
            if (key.equalsIgnoreCase(IS_VIP)){
                this.isVIP = value;
            }
            if (key.equalsIgnoreCase(IS_BLACKLIST)){
                this.isBlacklist = value;
            }
            if (key.equalsIgnoreCase(IS_WARNING)){
                this.isWarning = value;
            }
            if (key.equalsIgnoreCase(RELATIONSHIP)){
                this.relationship = value;
            }
            if (key.equalsIgnoreCase(EMAIL)){
                this.email = value;
            }    
            if (key.equalsIgnoreCase(GROUP_CODE)){
            	this.groupCode = value;
            }
            if (key.equalsIgnoreCase(CLIENT_CODE)){
            	this.clientCode = value;
            }
            if (key.equalsIgnoreCase(EMPLOYEE_ID)){
            	this.nik = value;
            }
            if (key.equalsIgnoreCase(ACTION_TYPE)){
            	this.actionType = value;
            }
            if (key.equalsIgnoreCase(CARD_TEMPLATE)){
            	this.cardTemplate = value;
            }
            if (key.equalsIgnoreCase(JOB_FUNCTION)){
            	this.jobFunction = value;
            }
            if (key.equalsIgnoreCase(COB_NUMBER)){
            	this.bpjsNumber = value;
            }
            if (key.equalsIgnoreCase(ADDITIONAL_INFO)){
            	this.additionalInfo = value;
            }
            if (key.equalsIgnoreCase(SUSPEND_START)){
            	this.suspendStart = value;
         
            }
            if (key.equalsIgnoreCase(SUSPEND_END)){
            	this.suspendEnd = value;
            }
            if (key.equalsIgnoreCase(SUBJECT_TO_NO_CLAIM)){
            	this.noClaimFlag = value;
            }
            if (key.equalsIgnoreCase(PRINT_CARD)){
            	this.printCard = value;
            }
            if (key.equalsIgnoreCase(NEW_POLICY_NUMBER)){
            	this.newPolicyNumber = value;
            }
            if (key.equalsIgnoreCase(ANNUAL_LIMIT)){
            	this.annualLimit = value;
            }
            if (key.equalsIgnoreCase(GRACE_START)){
            	if (value != null && !value.trim().equalsIgnoreCase("")){
            		this.graceStartDate.valueOf(value);
            	}
            }
            if (key.equalsIgnoreCase(GRACE_END)){
            	if (value != null && !value.trim().equalsIgnoreCase("")){
            		this.graceEndDate.valueOf(value);
            	}
            }
            if (key.equalsIgnoreCase(IMPORT_BATCH_NUMBER)){
            	this.importBatchNumber = value;
            }
            if (key.equalsIgnoreCase(SUB_POLICY_NUMBER)){
            	this.subPolicyNumber = value;
            }
            if (key.equalsIgnoreCase(LINKED_CARD_NUMBER)){
            	this.linkedCardNumber = value;
            }
            if (key.equalsIgnoreCase(CLIENT_NAME)){
            	this.clientName = value;
            }
            if (key.equalsIgnoreCase(POLICY_EXP_DATE)){
            	this.policyExpireDate = value;
            }
            if (key.equalsIgnoreCase(PAYMENT_METHOD)){
            	this.paymentMethod = value;
            }
            if (key.equalsIgnoreCase(NEXT_MEMBER_NUMBER)){
            	this.nextMemberNumber = value;
            }
            if (key.equalsIgnoreCase(NEXT_POLICY_NUMBER)){
            	this.nextPolicyNumber = value;
            }
        }
        
        

	@ManyToOne
    @JoinColumn(name="import_session_id")
    public DataImportStage getImportSessionId() {
        return importSessionId;
    }

    public void setImportSessionId(DataImportStage importSessionId) {
        this.importSessionId = importSessionId;
    }
    @Column(name="expire_date")
	public java.sql.Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(java.sql.Date expireDate) {
		this.expireDate = expireDate;
	}
	@Column(name="handphone")
	public String getHandphone() {
		return handphone;
	}
	public void setHandphone(String handphone) {
		this.handphone = handphone;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="created_time")
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name="created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="modified_time")
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	@Column(name="modified_by")
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Column(name="deleted_by")
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	@Column(name="deleted_time")
	public Timestamp getDeletedTime() {
		return deletedTime;
	}
	public void setDeletedTime(Timestamp deletedTime) {
		this.deletedTime = deletedTime;
	}
	@Column(name="deleted_status")
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	@Column(name="bank_branch")
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	@Column(name="is_vip")
	public String getIsVIP() {
		return isVIP;
	}
	public void setIsVIP(String isVIP) {
		this.isVIP = isVIP;
	}
	@Column(name="is_blacklist")
	public String getIsBlacklist() {
		return isBlacklist;
	}
	public void setIsBlacklist(String isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	@Column(name="is_warning")
	public String getIsWarning() {
		return isWarning;
	}
	public void setIsWarning(String isWarning) {
		this.isWarning = isWarning;
	}
	@Column(name="parent_number")
	public String getParentNumber() {
		return parentNumber;
	}
	public void setParentNumber(String parentNumber) {
		this.parentNumber = parentNumber;
	}
	@Column(name="parent_name")
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@Column(name="policy_number")
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	@Column(name="migration_status")
	public String getMigrationStatus() {
		return migrationStatus;
	}
	public void setMigrationStatus(String migrationStatus) {
		this.migrationStatus = migrationStatus;
	}
	@Column(name="migration_note")
	public String getMigrationNote() {
		return migrationNote;
	}
	public void setMigrationNote(String migrationNote) {
		this.migrationNote = migrationNote;
	}
	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMemberId() {
		return memberId;
	}
	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}
	@Column(name="salary")
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Column(name="relationship")
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	@Column(name="action_type")
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	@Column(name="card_template")
	public String getCardTemplate() {
		return cardTemplate;
	}
	public void setCardTemplate(String cardTemplate) {
		this.cardTemplate = cardTemplate;
	}
	@Column(name="job_function")
	public String getJobFunction() {
		return jobFunction;
	}
	public void setJobFunction(String jobFunction) {
		this.jobFunction = jobFunction;
	}
	@Column(name="swift_code")
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	@Column(name="bpjs_number")
	public String getBpjsNumber() {
		return bpjsNumber;
	}
	public void setBpjsNumber(String bpjsNumber) {
		this.bpjsNumber = bpjsNumber;
	}
	@Column(name="suspend_start")
	public String getSuspendStart() {
		return suspendStart;
	}
	public void setSuspendStart(String suspendStart) {
		this.suspendStart = suspendStart;
	}
	@Column(name="suspend_end")
	public String getSuspendEnd() {
		return suspendEnd;
	}
	public void setSuspendEnd(String suspendEnd) {
		this.suspendEnd = suspendEnd;
	}
	@Column(name="no_claim_flag")
	public String getNoClaimFlag() {
		return noClaimFlag;
	}
	public void setNoClaimFlag(String noClaimFlag) {
		this.noClaimFlag = noClaimFlag;
	}
	@Column(name="is_pro_rate")
	public String getIsProRate() {
		return isProRate;
	}
	public void setIsProRate(String isProRate) {
		this.isProRate = isProRate;
	}
	@Column(name="annual_limit")
	public String getAnnualLimit() {
		return annualLimit;
	}
	public void setAnnualLimit(String annualLimit) {
		this.annualLimit = annualLimit;
	}
	@Column(name="grace_start_date")
	public Date getGraceStartDate() {
		return graceStartDate;
	}
	public void setGraceStartDate(Date graceStartDate) {
		this.graceStartDate = graceStartDate;
	}
	@Column(name="grace_end_date")
	public Date getGraceEndDate() {
		return graceEndDate;
	}
	public void setGraceEndDate(Date graceEndDate) {
		this.graceEndDate = graceEndDate;
	}
	@Column(name="import_batch_number")
	public String getImportBatchNumber() {
		return importBatchNumber;
	}
	public void setImportBatchNumber(String importBatchNumber) {
		this.importBatchNumber = importBatchNumber;
	}
	@Column(name="sub_policy_number")
	public String getSubPolicyNumber() {
		return subPolicyNumber;
	}
	public void setSubPolicyNumber(String subPolicyNumber) {
		this.subPolicyNumber = subPolicyNumber;
	}
	@Column(name="linked_card_number")
	public String getLinkedCardNumber() {
		return linkedCardNumber;
	}
	public void setLinkedCardNumber(String linkedCardNumber) {
		this.linkedCardNumber = linkedCardNumber;
	}
	@ManyToOne
	@JoinColumn(name="card_printing_id")
	public CardPrinting getCardPrintingId() {
		return cardPrintingId;
	}
	public void setCardPrintingId(CardPrinting cardPrintingId) {
		this.cardPrintingId = cardPrintingId;
	}
	@Column(name="print_card")
	public String getPrintCard() {
		return printCard;
	}
	public void setPrintCard(String printCard) {
		this.printCard = printCard;
	}
	@Column(name="group_code")
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	@Column(name="client_code")
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	@Column(name="new_policy_number")
	public String getNewPolicyNumber() {
		return newPolicyNumber;
	}
	public void setNewPolicyNumber(String newPolicyNumber) {
		this.newPolicyNumber = newPolicyNumber;
	
	}
	@Column(name="additional_info")
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	@Column(name="nik")
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	@Column(name="group_name")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name="is_billed")
	public Integer getIsBilled() {
		return isBilled;
	}
	public void setIsBilled(Integer isBilled) {
		this.isBilled = isBilled;
	}
	@ManyToOne
	@JoinColumn(name="invoice_item_id")
	public InvoiceItem getInvoiceItemId() {
		return invoiceItemId;
	}
	public void setInvoiceItemId(InvoiceItem invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}
	@Column(name="suspend_reactive_status")
	public Integer getSuspendReactiveStatus() {
		return suspendReactiveStatus;
	}
	public void setSuspendReactiveStatus(Integer suspendReactiveStatus) {
		this.suspendReactiveStatus = suspendReactiveStatus;
	}
	@Column(name="client_name")
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Column(name="policy_expire_date")
	public String getPolicyExpireDate() {
		return policyExpireDate;
	}
	public void setPolicyExpireDate(String policyExpireDate) {
		this.policyExpireDate = policyExpireDate;
	}
	@Column(name="payment_method")
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	@Column(name="next_member_number")
	public String getNextMemberNumber() {
		return nextMemberNumber;
	}
	public void setNextMemberNumber(String nextMemberNumber) {
		this.nextMemberNumber = nextMemberNumber;
	}
	@Column(name="next_policy_number")
	public String getNextPolicyNumber() {
		return nextPolicyNumber;
	}
	public void setNextPolicyNumber(String nextPolicyNumber) {
		this.nextPolicyNumber = nextPolicyNumber;
	}
	@Column(name="next_card_number")
	public String getNextCardNumber() {
		return nextCardNumber;
	}
	public void setNextCardNumber(String nextCardNumber) {
		this.nextCardNumber = nextCardNumber;
	}
	@Column(name="next_expire_date")
	public Date getNextExpireDate() {
		return nextExpireDate;
	}
	public void setNextExpireDate(Date nextExpireDate) {
		this.nextExpireDate = nextExpireDate;
	}
	@Column(name="next_effective_date")
	public Date getNextEffectiveDate() {
		return nextEffectiveDate;
	}
	public void setNextEffectiveDate(Date nextEffectiveDate) {
		this.nextEffectiveDate = nextEffectiveDate;
	}
	@ManyToOne
	@JoinColumn(name="policy_id")
	public Policy getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Policy policyId) {
		this.policyId = policyId;
	}
	@Column(name="upload_note")
	public String getUploadNote() {
		return uploadNote;
	}
	public void setUploadNote(String uploadNote) {
		this.uploadNote = uploadNote;
	}
	@Column(name="billing_rate")
	public Double getBillingRate() {
		return billingRate;
	}
	public void setBillingRate(Double billingRate) {
		this.billingRate = billingRate;
	}
	@Column(name="refund_rate")
	public Double getRefundRate() {
		return refundRate;
	}
	public void setRefundRate(Double refundRate) {
		this.refundRate = refundRate;
	}
	@Column(name="next_new_relationship")
	public String getNextNewRelationship() {
		return nextNewRelationship;
	}
	public void setNextNewRelationship(String nextNewRelationship) {
		this.nextNewRelationship = nextNewRelationship;
	}
	@Column(name="next_new_group_code")
	public String getNextNewGroupCode() {
		return nextNewGroupCode;
	}
	public void setNextNewGroupCode(String nextNewGroupCode) {
		this.nextNewGroupCode = nextNewGroupCode;
	}
	@Column(name="next_nnew_employee_number")
	public String getNextNewEmployeeNumber() {
		return nextNewEmployeeNumber;
	}
	public void setNextNewEmployeeNumber(String nextNewEmployeeNumber) {
		this.nextNewEmployeeNumber = nextNewEmployeeNumber;
	}
    
	

}