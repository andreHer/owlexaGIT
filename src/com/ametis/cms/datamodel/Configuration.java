package com.ametis.cms.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
public class Configuration {

    private Double inpatientThreshold;
    private Double outpatientThreshold;
    private Double maternityThreshold;
    private Double dentalThreshold;
    private Double opticalThreshold;
    private Double medicalCheckUpThreshold;
    private String company;
    private String companyAddress;
    private String companyCode;
    private Double monthlyUsageThreshold;
    private Integer configurationId;
    private Integer isActive;
    private String bankAccount;
    private String bankName;
    private String bankBranch;
    private String bankAccountName;
    private String generalManager;
    private String generalManagerLabel;
    private String director;
    private String directorLabel;
    private String financeManager;
    private String financeManagerLabel;
    private Integer centralizedCDVNumbering;
    private Integer centralizedClaimNumbering;
    private Integer centralizedExcessNumbering;
    private Integer centralizedCallNumbering;
    private Integer centralizedFundNumbering;
    private Integer centralizedBatchNumbering;
    
    private Integer isUsingSequenceNumber;
    
    private Integer cdvNumber;
    private Integer claimNumber;
    private Integer excessNumber;
    private Integer callNumber;
    private Integer fundNumber;
    private Integer batchNumber;
    
    private Integer groupCodeNumber;
    

    private Integer claimRejectionNumber;
    private Integer caseRejectionNumber;
    private Integer claimPendingNumber;
    private Integer caseNumber;
    private Integer claimReceivingNumber;
    
    private String cdvNumberSeqName;
    private String claimNumberSeqName;
    private String excessNumberSeqName;
    private String callNumberSeqName;
    private String fundNumberSeqName;
    private String batchNumberSeqName;
    private String claimRejectSeqName;
    private String caseRejectSeqName;
    private String claimPendingSeqName;
    private String caseNumberSeqName;
    private String invoiceNumberSeqName;
    private String otherPaymentNumberSeqName;
    private String cardNumberSeqName;
    
    private Integer isUsingBranch;
    private Integer otherPaymentNumber;
    private Integer isUsingOtherPaymentNumber;
    private Integer excessExpireDay;
    private Integer reminderExpireDay;
    private String theme;
    private String memberStoragePath;
    private String reportPath;
    private String policyTermConditionPath;
    
    
    private String callNumberTemplate;
    private String claimNumberTemplate;
    private String caseNumberTemplate;
    private String paymentNumberTemplate;
    private String batchNumberTemplate;
    private String claimRejectionNumberTemplate;
    private String caseRejectionNumberTemplate;
    private String claimPendingNumberTemplate;
    private String excessNumberTemplate;
    private String fundNumberTemplate;
    private String invoiceNumberTemplate;
    private Integer invoiceNumber;
    private String excessPrintClaimType;
    private Integer isUsingClientNotification;
    private Integer isUsingMedicineDB;
    private String edcFilterFileName;
    private String edcFilterDelimiter;
    private String uploadStorageDirectory;
    private String swipeCardPrefix;
    
    private String emailNotifier;
    private String emailServer;
    private String emailPort;
    
    private Client clientId;
    
    private Integer uploadMemberTemplateId;
    private Integer uploadProviderTemplateId;
    private Integer uploadMemberDiagnosisTemplateId;
    private Integer uploadMemberProviderTemplateId;
    private Integer uploadClaimHeaderTemplateId;
    private Integer uploadClaimDetailTemplateId;
    private Integer uploadPaymentTemplateId;
    private Integer uploadProductImportTemplateId;
    private Integer uploadGroupSuspendTemplateId;
    
    //Add 20150819 by FVO, for global default setting
    private Integer edcAlertGlobal;
    
    private Double providerRollPaperRegister;
    private Double providerRollPaperPayment;
    private Integer providerRollPaperLimit;
    private Double providerRollPaperLength;
    
    private String providerCodeTemplate;
    private Integer ruangPerawatanItemId;
    
    //bcc untuk email notification
    private String emailBccClaim;
    private String emailBccFinance;
    private String emailPassword;

    public Configuration() {
    }

    @Column(name = "inpatient_threshold")
    public Double getInpatientThreshold() {
        return inpatientThreshold;
    }

    public void setInpatientThreshold(Double inpatientThreshold) {
        this.inpatientThreshold = inpatientThreshold;
    }

    @Column(name = "outpatient_threshold")
    public Double getOutpatientThreshold() {
        return outpatientThreshold;
    }

    public void setOutpatientThreshold(Double outpatientThreshold) {
        this.outpatientThreshold = outpatientThreshold;
    }

    @Column(name = "maternity_threshold")
    public Double getMaternityThreshold() {
        return maternityThreshold;
    }

    public void setMaternityThreshold(Double maternityThreshold) {
        this.maternityThreshold = maternityThreshold;
    }

    @Column(name = "dental_threshold")
    public Double getDentalThreshold() {
        return dentalThreshold;
    }

    public void setDentalThreshold(Double dentalThreshold) {
        this.dentalThreshold = dentalThreshold;
    }

    @Column(name = "optical_threshold")
    public Double getOpticalThreshold() {
        return opticalThreshold;
    }

    public void setOpticalThreshold(Double opticalThreshold) {
        this.opticalThreshold = opticalThreshold;
    }

    @Column(name = "monthly_usage_threshold")
    public Double getMonthlyUsageThreshold() {
        return monthlyUsageThreshold;
    }

    public void setMonthlyUsageThreshold(Double monthlyUsageThreshold) {
        this.monthlyUsageThreshold = monthlyUsageThreshold;
    }

    @Column(name = "company_code")
    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "company_address")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuration_id")
    public Integer getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(Integer configurationId) {
        this.configurationId = configurationId;
    }

    @Column(name = "is_active")
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Column(name = "bank_account")
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Column(name = "bank_name")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Column(name = "bank_branch")
    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    @Column(name = "bank_account_name")
    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    @Column(name = "general_manager")
    public String getGeneralManager() {
        return generalManager;
    }

    public void setGeneralManager(String generalManager) {
        this.generalManager = generalManager;
    }

    @Column(name = "general_manager_label")
    public String getGeneralManagerLabel() {
        return generalManagerLabel;
    }

    public void setGeneralManagerLabel(String generalManagerLabel) {
        this.generalManagerLabel = generalManagerLabel;
    }

    @Column(name = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Column(name = "director_label")
    public String getDirectorLabel() {
        return directorLabel;
    }

    public void setDirectorLabel(String directorLabel) {
        this.directorLabel = directorLabel;
    }

    @Column(name = "finance_manager")
    public String getFinanceManager() {
        return financeManager;
    }

    public void setFinanceManager(String financeManager) {
        this.financeManager = financeManager;
    }

    @Column(name = "finance_manager_label")
    public String getFinanceManagerLabel() {
        return financeManagerLabel;
    }

    public void setFinanceManagerLabel(String financeManagerLabel) {
        this.financeManagerLabel = financeManagerLabel;
    }

    @Column(name = "centralized_cdv_numbering")
    public Integer getCentralizedCDVNumbering() {
        return centralizedCDVNumbering;
    }

    public void setCentralizedCDVNumbering(Integer centralizedCDVNumbering) {
        this.centralizedCDVNumbering = centralizedCDVNumbering;
    }

    @Column(name = "centralized_claim_numbering")
    public Integer getCentralizedClaimNumbering() {
        return centralizedClaimNumbering;
    }

    public void setCentralizedClaimNumbering(Integer centralizedClaimNumbering) {
        this.centralizedClaimNumbering = centralizedClaimNumbering;
    }

    @Column(name = "centralized_excess_numbering")
    public Integer getCentralizedExcessNumbering() {
        return centralizedExcessNumbering;
    }

    public void setCentralizedExcessNumbering(Integer centralizedExcessNumbering) {
        this.centralizedExcessNumbering = centralizedExcessNumbering;
    }

    @Column(name = "centralized_call_numbering")
    public Integer getCentralizedCallNumbering() {
        return centralizedCallNumbering;
    }

    public void setCentralizedCallNumbering(Integer centralizedCallNumbering) {
        this.centralizedCallNumbering = centralizedCallNumbering;
    }

    @Column(name = "centralized_fund_numbering")
    public Integer getCentralizedFundNumbering() {
        return centralizedFundNumbering;
    }

    public void setCentralizedFundNumbering(Integer centralizedFundNumbering) {
        this.centralizedFundNumbering = centralizedFundNumbering;
    }

    @Column(name = "centralized_batch_numbering")
    public Integer getCentralizedBatchNumbering() {
        return centralizedBatchNumbering;
    }

    public void setCentralizedBatchNumbering(Integer centralizedBatchNumbering) {
        this.centralizedBatchNumbering = centralizedBatchNumbering;
    }

    @Column(name = "cdv_number")
    public Integer getCdvNumber() {
        return cdvNumber;
    }

    public void setCdvNumber(Integer cdvNumber) {
        this.cdvNumber = cdvNumber;
    }

    @Column(name = "claim_number")
    public Integer getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(Integer claimNumber) {
        this.claimNumber = claimNumber;
    }

    @Column(name = "excess_number")
    public Integer getExcessNumber() {
        return excessNumber;
    }

    public void setExcessNumber(Integer excessNumber) {
        this.excessNumber = excessNumber;
    }

    @Column(name = "call_number")
    public Integer getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(Integer callNumber) {
        this.callNumber = callNumber;
    }

    @Column(name = "fund_number")
    public Integer getFundNumber() {
        return fundNumber;
    }

    public void setFundNumber(Integer fundNumber) {
        this.fundNumber = fundNumber;
    }

    @Column(name = "batch_number")
    public Integer getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Column(name = "other_payment_number")
    public Integer getOtherPaymentNumber() {
        return otherPaymentNumber;
    }

    public void setOtherPaymentNumber(Integer otherPaymentNumber) {
        this.otherPaymentNumber = otherPaymentNumber;
    }

    @Column(name = "is_using_other_payment_number")
    public Integer getIsUsingOtherPaymentNumber() {
        return isUsingOtherPaymentNumber;
    }

    public void setIsUsingOtherPaymentNumber(Integer isUsingOtherPaymentNumber) {
        this.isUsingOtherPaymentNumber = isUsingOtherPaymentNumber;
    }

    @Column(name = "is_using_branch")
    public Integer getIsUsingBranch() {
        return isUsingBranch;
    }

    public void setIsUsingBranch(Integer isUsingBranch) {
        this.isUsingBranch = isUsingBranch;
    }

    @Column(name = "excess_expire_day")
    public Integer getExcessExpireDay() {
        return excessExpireDay;
    }

    public void setExcessExpireDay(Integer excessExpireDay) {
        this.excessExpireDay = excessExpireDay;
    }

    @Column(name = "excess_reminder_day")
    public Integer getReminderExpireDay() {
        return reminderExpireDay;
    }

    public void setReminderExpireDay(Integer reminderExpireDay) {
        this.reminderExpireDay = reminderExpireDay;
    }

    @Column(name = "theme")
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Column(name="member_storage_path")
    public String getMemberStoragePath() {
        return memberStoragePath;
    }

    public void setMemberStoragePath(String memberStoragePath) {
        this.memberStoragePath = memberStoragePath;
    }

    @Column(name="benefit_term_condition_path")
    public String getPolicyTermConditionPath() {
        return policyTermConditionPath;
    }

    public void setPolicyTermConditionPath(String policyTermConditionPath) {
        this.policyTermConditionPath = policyTermConditionPath;
    }

    @Column(name="report_storage_path")
    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    @Column(name="claim_rejection_number")
	public Integer getClaimRejectionNumber() {
		return claimRejectionNumber;
	}

	public void setClaimRejectionNumber(Integer claimRejectionNumber) {
		this.claimRejectionNumber = claimRejectionNumber;
	}

	@Column(name="case_rejection_number")
	public Integer getCaseRejectionNumber() {
		return caseRejectionNumber;
	}

	public void setCaseRejectionNumber(Integer caseRejectionNumber) {
		this.caseRejectionNumber = caseRejectionNumber;
	}

	@Column(name="claim_pending_number")
	public Integer getClaimPendingNumber() {
		return claimPendingNumber;
	}

	public void setClaimPendingNumber(Integer claimPendingNumber) {
		this.claimPendingNumber = claimPendingNumber;
	}

	@Column(name="call_number_template")
	public String getCallNumberTemplate() {
		return callNumberTemplate;
	}

	public void setCallNumberTemplate(String callNumberTemplate) {
		this.callNumberTemplate = callNumberTemplate;
	}

	@Column(name="claim_number_template")
	public String getClaimNumberTemplate() {
		return claimNumberTemplate;
	}

	public void setClaimNumberTemplate(String claimNumberTemplate) {
		this.claimNumberTemplate = claimNumberTemplate;
	}

	@Column(name="case_number_template")
	public String getCaseNumberTemplate() {
		return caseNumberTemplate;
	}

	public void setCaseNumberTemplate(String caseNumberTemplate) {
		this.caseNumberTemplate = caseNumberTemplate;
	}

	@Column(name="payment_number_template")
	public String getPaymentNumberTemplate() {
		return paymentNumberTemplate;
	}

	public void setPaymentNumberTemplate(String paymentNumberTemplate) {
		this.paymentNumberTemplate = paymentNumberTemplate;
	}

	@Column(name="batch_number_template")
	public String getBatchNumberTemplate() {
		return batchNumberTemplate;
	}

	public void setBatchNumberTemplate(String batchNumberTemplate) {
		this.batchNumberTemplate = batchNumberTemplate;
	}

	@Column(name="claim_rejection_number_template")
	public String getClaimRejectionNumberTemplate() {
		return claimRejectionNumberTemplate;
	}

	public void setClaimRejectionNumberTemplate(String claimRejectionNumberTemplate) {
		this.claimRejectionNumberTemplate = claimRejectionNumberTemplate;
	}

	@Column(name="case_rejection_number_template")
	public String getCaseRejectionNumberTemplate() {
		return caseRejectionNumberTemplate;
	}

	public void setCaseRejectionNumberTemplate(String caseRejectionNumberTemplate) {
		this.caseRejectionNumberTemplate = caseRejectionNumberTemplate;
	}

	@Column(name="claim_pending_number_template")
	public String getClaimPendingNumberTemplate() {
		return claimPendingNumberTemplate;
	}

	public void setClaimPendingNumberTemplate(String claimPendingNumberTemplate) {
		this.claimPendingNumberTemplate = claimPendingNumberTemplate;
	}

	@Column(name="excess_number_template")
	public String getExcessNumberTemplate() {
		return excessNumberTemplate;
	}

	public void setExcessNumberTemplate(String excessNumberTemplate) {
		this.excessNumberTemplate = excessNumberTemplate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="client_id")
	public Client getClientId() {
		return clientId;
	}

	public void setClientId(Client clientId) {
		this.clientId = clientId;
	}

	@Column(name="case_number")
	public Integer getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(Integer caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Column(name="fund_number_template")
	public String getFundNumberTemplate() {
		return fundNumberTemplate;
	}

	public void setFundNumberTemplate(String fundNumberTemplate) {
		this.fundNumberTemplate = fundNumberTemplate;
	}

	@Column(name="invoice_number_template")
	public String getInvoiceNumberTemplate() {
		return invoiceNumberTemplate;
	}

	public void setInvoiceNumberTemplate(String invoiceNumberTemplate) {
		this.invoiceNumberTemplate = invoiceNumberTemplate;
	}

	@Column(name="invoice_number")
	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Column(name="excess_print_claim_type")
	public String getExcessPrintClaimType() {
		return excessPrintClaimType;
	}

	public void setExcessPrintClaimType(String excessPrintClaimType) {
		this.excessPrintClaimType = excessPrintClaimType;
	}

	@Column(name="is_using_email_notification")
	public Integer getIsUsingClientNotification() {
		return isUsingClientNotification;
	}

	public void setIsUsingClientNotification(Integer isUsingClientNotification) {
		this.isUsingClientNotification = isUsingClientNotification;
	}

	@Column(name="is_using_medicine_db")
	public Integer getIsUsingMedicineDB() {
		return isUsingMedicineDB;
	}

	public void setIsUsingMedicineDB(Integer isUsingMedicineDB) {
		this.isUsingMedicineDB = isUsingMedicineDB;
	}

	@Column(name="edc_filter_file_name")
	public String getEdcFilterFileName() {
		return edcFilterFileName;
	}

	public void setEdcFilterFileName(String edcFilterFileName) {
		this.edcFilterFileName = edcFilterFileName;
	}

	@Column(name="edc_filter_delimiter")
	public String getEdcFilterDelimiter() {
		return edcFilterDelimiter;
	}

	public void setEdcFilterDelimiter(String edcFilterDelimiter) {
		this.edcFilterDelimiter = edcFilterDelimiter;
	}

	@Column(name="cdv_number_seq_name")
	public String getCdvNumberSeqName() {
		return cdvNumberSeqName;
	}

	public void setCdvNumberSeqName(String cdvNumberSeqName) {
		this.cdvNumberSeqName = cdvNumberSeqName;
	}

	@Column(name="claim_number_seq_name")
	public String getClaimNumberSeqName() {
		return claimNumberSeqName;
	}

	public void setClaimNumberSeqName(String claimNumberSeqName) {
		this.claimNumberSeqName = claimNumberSeqName;
	}

	@Column(name="excess_number_seq_name")
	public String getExcessNumberSeqName() {
		return excessNumberSeqName;
	}

	public void setExcessNumberSeqName(String excessNumberSeqName) {
		this.excessNumberSeqName = excessNumberSeqName;
	}

	@Column(name="call_number_seq_name")
	public String getCallNumberSeqName() {
		return callNumberSeqName;
	}

	public void setCallNumberSeqName(String callNumberSeqName) {
		this.callNumberSeqName = callNumberSeqName;
	}

	@Column(name="fund_number_seq_name")
	public String getFundNumberSeqName() {
		return fundNumberSeqName;
	}

	public void setFundNumberSeqName(String fundNumberSeqName) {
		this.fundNumberSeqName = fundNumberSeqName;
	}

	@Column(name="batch_number_seq_name")
	public String getBatchNumberSeqName() {
		return batchNumberSeqName;
	}

	public void setBatchNumberSeqName(String batchNumberSeqName) {
		this.batchNumberSeqName = batchNumberSeqName;
	}

	@Column(name="claim_reject_seq_name")
	public String getClaimRejectSeqName() {
		return claimRejectSeqName;
	}

	public void setClaimRejectSeqName(String claimRejectSeqName) {
		this.claimRejectSeqName = claimRejectSeqName;
	}

	@Column(name="case_reject_seq_name")
	public String getCaseRejectSeqName() {
		return caseRejectSeqName;
	}

	public void setCaseRejectSeqName(String caseRejectSeqName) {
		this.caseRejectSeqName = caseRejectSeqName;
	}

	@Column(name="claim_pending_seq_name")
	public String getClaimPendingSeqName() {
		return claimPendingSeqName;
	}

	public void setClaimPendingSeqName(String claimPendingSeqName) {
		this.claimPendingSeqName = claimPendingSeqName;
	}

	@Column(name="case_number_seq_name")
	public String getCaseNumberSeqName() {
		return caseNumberSeqName;
	}

	public void setCaseNumberSeqName(String caseNumberSeqName) {
		this.caseNumberSeqName = caseNumberSeqName;
	}

	@Column(name="other_payment_num_seq_name")
	public String getOtherPaymentNumberSeqName() {
		return otherPaymentNumberSeqName;
	}

	public void setOtherPaymentNumberSeqName(String otherPaymentNumberSeqName) {
		this.otherPaymentNumberSeqName = otherPaymentNumberSeqName;
	}
	
	@Column(name="is_using_sequence_number")
	public Integer getIsUsingSequenceNumber() {
		return isUsingSequenceNumber;
	}

	public void setIsUsingSequenceNumber(Integer isUsingSequenceNumber) {
		this.isUsingSequenceNumber = isUsingSequenceNumber;
	}

	@Column(name="invoice_number_seq_name")
	public String getInvoiceNumberSeqName() {
		return invoiceNumberSeqName;
	}

	public void setInvoiceNumberSeqName(String invoiceNumberSeqName) {
		this.invoiceNumberSeqName = invoiceNumberSeqName;
	}

	@Column(name="upload_storage_directory")
	public String getUploadStorageDirectory() {
		return uploadStorageDirectory;
	}

	public void setUploadStorageDirectory(String uploadStorageDirectory) {
		this.uploadStorageDirectory = uploadStorageDirectory;
	}

	@Column(name="swipe_card_prefix")
	public String getSwipeCardPrefix() {
		return swipeCardPrefix;
	}

	public void setSwipeCardPrefix(String swipeCardPrefix) {
		this.swipeCardPrefix = swipeCardPrefix;
	}

	@Column(name="card_number_seq_name")
	public String getCardNumberSeqName() {
		return cardNumberSeqName;
	}

	public void setCardNumberSeqName(String cardNumberSeqName) {
		this.cardNumberSeqName = cardNumberSeqName;
	}

	@Column(name="claim_receiving_number")
	public Integer getClaimReceivingNumber() {
		return claimReceivingNumber;
	}

	public void setClaimReceivingNumber(Integer claimReceivingNumber) {
		this.claimReceivingNumber = claimReceivingNumber;
	}

	@Column(name="group_code_number")
	public Integer getGroupCodeNumber() {
		return groupCodeNumber;
	}

	public void setGroupCodeNumber(Integer groupCodeNumber) {
		this.groupCodeNumber = groupCodeNumber;
	}

	@Column(name="email_notifier")
	public String getEmailNotifier() {
		return emailNotifier;
	}

	public void setEmailNotifier(String emailNotifier) {
		this.emailNotifier = emailNotifier;
	}

	@Column(name="email_server")
	public String getEmailServer() {
		return emailServer;
	}

	public void setEmailServer(String emailServer) {
		this.emailServer = emailServer;
	}

	@Column(name="email_port")
	public String getEmailPort() {
		return emailPort;
	}

	public void setEmailPort(String emailPort) {
		this.emailPort = emailPort;
	}

	@Column(name="upload_member_template_id")
	public Integer getUploadMemberTemplateId() {
		return uploadMemberTemplateId;
	}

	public void setUploadMemberTemplateId(Integer uploadMemberTemplateId) {
		this.uploadMemberTemplateId = uploadMemberTemplateId;
	}

	@Column(name="upload_provider_template_id")
	public Integer getUploadProviderTemplateId() {
		return uploadProviderTemplateId;
	}

	public void setUploadProviderTemplateId(Integer uploadProviderTemplateId) {
		this.uploadProviderTemplateId = uploadProviderTemplateId;
	}

	@Column(name="upload_member_diagnosis_template_id")
	public Integer getUploadMemberDiagnosisTemplateId() {
		return uploadMemberDiagnosisTemplateId;
	}

	public void setUploadMemberDiagnosisTemplateId(
			Integer uploadMemberDiagnosisTemplateId) {
		this.uploadMemberDiagnosisTemplateId = uploadMemberDiagnosisTemplateId;
	}

	@Column(name="upload_member_provider_template_id")
	public Integer getUploadMemberProviderTemplateId() {
		return uploadMemberProviderTemplateId;
	}

	public void setUploadMemberProviderTemplateId(
			Integer uploadMemberProviderTemplateId) {
		this.uploadMemberProviderTemplateId = uploadMemberProviderTemplateId;
	}

	@Column(name="upload_product_import_template_id")
	public Integer getUploadProductImportTemplateId() {
		return uploadProductImportTemplateId;
	}

	public void setUploadProductImportTemplateId(Integer uploadProductImportTemplateId) {
		this.uploadProductImportTemplateId = uploadProductImportTemplateId;
	}

	@Column(name="upload_claim_header_template_id")
	public Integer getUploadClaimHeaderTemplateId() {
		return uploadClaimHeaderTemplateId;
	}

	public void setUploadClaimHeaderTemplateId(Integer uploadClaimHeaderTemplateId) {
		this.uploadClaimHeaderTemplateId = uploadClaimHeaderTemplateId;
	}

	@Column(name="upload_claim_detail_template_id")
	public Integer getUploadClaimDetailTemplateId() {
		return uploadClaimDetailTemplateId;
	}

	public void setUploadClaimDetailTemplateId(Integer uploadClaimDetailTemplateId) {
		this.uploadClaimDetailTemplateId = uploadClaimDetailTemplateId;
	}

	@Column(name="upload_payment_template_id")
	public Integer getUploadPaymentTemplateId() {
		return uploadPaymentTemplateId;
	}

	public void setUploadPaymentTemplateId(Integer uploadPaymentTemplateId) {
		this.uploadPaymentTemplateId = uploadPaymentTemplateId;
	}
	@Column(name="upload_group_suspend_template_id")
	public Integer getUploadGroupSuspendTemplateId() {
		return uploadGroupSuspendTemplateId;
	}

	public void setUploadGroupSuspendTemplateId(Integer uploadGroupSuspendTemplateId) {
		this.uploadGroupSuspendTemplateId = uploadGroupSuspendTemplateId;
	}

	@Column(name="medical_check_up_threshold")
	public Double getMedicalCheckUpThreshold() {
		return medicalCheckUpThreshold;
	}

	public void setMedicalCheckUpThreshold(Double medicalCheckUpThreshold) {
		this.medicalCheckUpThreshold = medicalCheckUpThreshold;
	}

	@Column(name="edc_alert_global")
	public Integer getEdcAlertGlobal() {
		return edcAlertGlobal;
	}

	public void setEdcAlertGlobal(Integer edcAlertGlobal) {
		this.edcAlertGlobal = edcAlertGlobal;
	}

	@Column(name="provider_roll_paper_register")
	public Double getProviderRollPaperRegister() {
		return providerRollPaperRegister;
	}

	public void setProviderRollPaperRegister(Double providerRollPaperRegister) {
		this.providerRollPaperRegister = providerRollPaperRegister;
	}

	@Column(name="provider_roll_paper_payment")
	public Double getProviderRollPaperPayment() {
		return providerRollPaperPayment;
	}

	public void setProviderRollPaperPayment(Double providerRollPaperPayment) {
		this.providerRollPaperPayment = providerRollPaperPayment;
	}

	@Column(name="provider_roll_paper_limit")
	public Integer getProviderRollPaperLimit() {
		return providerRollPaperLimit;
	}

	public void setProviderRollPaperLimit(Integer providerRollPaperLimit) {
		this.providerRollPaperLimit = providerRollPaperLimit;
	}

	@Column(name="provider_roll_paper_length")
	public Double getProviderRollPaperLength() {
		return providerRollPaperLength;
	}

	public void setProviderRollPaperLength(Double providerRollPaperLength) {
		this.providerRollPaperLength = providerRollPaperLength;
	}

	@Column(name="provider_code_template")
	public String getProviderCodeTemplate() {
		return providerCodeTemplate;
	}

	public void setProviderCodeTemplate(String providerCodeTemplate) {
		this.providerCodeTemplate = providerCodeTemplate;
	}
	@Column(name="email_bcc_claim")
	public String getEmailBccClaim() {
		return emailBccClaim;
	}

	public void setEmailBccClaim(String emailBccClaim) {
		this.emailBccClaim = emailBccClaim;
	}
	
	@Column(name="email_bcc_finance")
	public String getEmailBccFinance() {
		return emailBccFinance;
	}

	public void setEmailBccFinance(String emailBccFinance) {
		this.emailBccFinance = emailBccFinance;
	}

	@Column(name="ruang_perawatan_item_id")
	public Integer getRuangPerawatanItemId() {
		return ruangPerawatanItemId;
	}

	public void setRuangPerawatanItemId(Integer ruangPerawatanItemId) {
		this.ruangPerawatanItemId = ruangPerawatanItemId;
	}
	
	@Column(name="email_password")
	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	
	
    
    
}
