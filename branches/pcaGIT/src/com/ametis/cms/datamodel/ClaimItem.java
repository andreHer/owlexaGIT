package com.ametis.cms.datamodel;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.core.io.DescriptiveResource;

import com.ametis.cms.dto.ClaimExchangeDetailDto;
import com.ametis.cms.dto.ClaimItemDto;
import javax.persistence.*;

@Entity
@Table(name = "claim_item")
public class ClaimItem implements java.io.Serializable {

	public static final String TIDAK_AMBIL_BENEFIT = "Tidak Mengambil Benefit";
    private static final long serialVersionUID = 1L;
    public static final int CLAIM_ITEM_OPEN = 1;
    public static final int CLAIM_ITEM_APPROVED = 9;
    public static final int CLAIM_ITEM_REJECTED = 4;
    public static final int CLAIM_ITEM_CHECKED = 8;
    public static final int CLAIM_ITEM_PRE_OPEN = 19;
    //Fields
    /**
     * data for the column :
     *
     * --------- claim_item.claim_item_id --------- schema = null tableName =
     * claim_item foreignCol = foreignTab = catalog = insurance remarks =
     * auto_increment defaultValue = null decDigits = 0 radix = 10 nullable = 0
     * ordinal = 1 size = 11 type = 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     */
    private Integer claimItemId;
    /**
     * data for the column :
     *
     * --------- claim_item.claim_item_remarks --------- schema = null tableName
     * = claim_item foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 4
     * size = 65535 type = -1 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String claimItemRemarks;
    /**
     * data for the column :
     *
     * --------- claim_item.claim_item_description --------- schema = null
     * tableName = claim_item foreignCol = foreignTab = catalog = insurance
     * remarks = defaultValue = null decDigits = 0 radix = 10 nullable = 1
     * ordinal = 5 size = 65535 type = -1 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String claimItemDescription;
    /**
     * data for the column :
     *
     * --------- claim_item.claim_item_status --------- schema = null tableName
     * = claim_item foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 6
     * size = 2 type = 4 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private CaseStatus claimItemStatus;
    /**
     * data for the column :
     *
     * --------- claim_item.claim_item_value --------- schema = null tableName =
     * claim_item foreignCol = foreignTab = catalog = insurance remarks =
     * defaultValue = null decDigits = 0 radix = 10 nullable = 1 ordinal = 7
     * size = 22 type = 8 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Double claimItemValue;
    
    private Double claimItemAmount;
    private Double claimItemApprovedValue;
    private Double claimItemCoveredValue;
    
    
    private CoverageStatus coverageStatus;
    private java.sql.Timestamp createdTime;
    private String createdBy;
    private java.sql.Timestamp deletedTime;
    private String deletedBy;
    private java.sql.Timestamp modifiedTime;
    private String modifiedBy;
    private Integer deletedStatus;
    private Item itemId;
    private Claim claimId;
    private MeasurementUnit measurementUnitId;
    private java.sql.Date verifiedDate;
    private String verifiedBy;
    private java.sql.Date benefitCheckedDate;
    private String benefitCheckedBy;
    private Double excessValue;
    private String medicalRemarks;
    private String benefitCheckRemarks;
    private Double claimExGratia;
    private MemberBenefit memberBenefitId;
    private ClaimItem correctionClaimItem;
    private Boolean isCorrected;
    private Timestamp correctionTime;
    private String correctedBy;
    private Double preApproveRemainingBenefit;
    private Double postApproveRemainingBenefit;
    private String itemName;
    private String itemCode;
    private String clientItemCode;
    private Double prePaidExcess; // excess (seharusnya) dibayar di tempat 
    private Double pembayaranDiMuka; // pembayaran di muka (realisasi) 
    private Double refund; // dana dikembalikan ke peserta
    private Double paidToProvider;
    private Integer isReconciled;
    private String reconciledBy;
    private Timestamp reconcileTime;
    
    private Double productCurrencyChargeValue;
	private Double productCurrencyApprovedValue;
	private Double productCurrencyExcessValue;
	private Double exchangeRate;
	private Date exchangeRateDate;
	private Double additionalCoverageValue;
	private String additionalCoverageNote;
	private PolicyBenefit policyBenefitId;
	private Integer isSMMBenefit;
	private Integer isMultiLayerBenefit;
	
	private Double faultExcessProvider;
	
    // -- foreign affairs end

    // -- exported key
    // -- exported key end
    // Fields End
    // PK GETTER SETTER
    @Id
    @Column(name = "claim_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public java.lang.Integer getClaimItemId() {
        return claimItemId;
    }

    public void setClaimItemId(java.lang.Integer value) {
        claimItemId = value;
    }
    // PK GETTER SETTER END

    @Column(name = "claim_item_remarks")
    public java.lang.String getClaimItemRemarks() {
        return claimItemRemarks;
    }

    public void setClaimItemRemarks(java.lang.String value) {
        claimItemRemarks = value;
    }

    @Column(name = "claim_item_description")
    public java.lang.String getClaimItemDescription() {
        return claimItemDescription;
    }

    public void setClaimItemDescription(java.lang.String value) {
        claimItemDescription = value;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "claim_item_status")
    public CaseStatus getClaimItemStatus() {
        return claimItemStatus;
    }

    public void setClaimItemStatus(CaseStatus value) {
        claimItemStatus = value;
    }

    @Column(name = "claim_item_value")
    public java.lang.Double getClaimItemValue() {
        return claimItemValue;
    }

    public void setClaimItemValue(java.lang.Double value) {
        claimItemValue = value;
    }

    @Column(name = "claim_item_amount")
    public java.lang.Double getClaimItemAmount() {
        return claimItemAmount;
    }

    public void setClaimItemAmount(java.lang.Double value) {
        claimItemAmount = value;
    }

    @Column(name = "claim_item_approved_value")
    public java.lang.Double getClaimItemApprovedValue() {
        return claimItemApprovedValue;
    }

    public void setClaimItemApprovedValue(java.lang.Double value) {
        claimItemApprovedValue = value;
    }

    @Column(name = "claim_item_covered_value")
    public java.lang.Double getClaimItemCoveredValue() {
        return claimItemCoveredValue;
    }

    public void setClaimItemCoveredValue(java.lang.Double value) {
        claimItemCoveredValue = value;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "coverage_status")
    public CoverageStatus getCoverageStatus() {
        return coverageStatus;
    }

    public void setCoverageStatus(CoverageStatus value) {
        coverageStatus = value;
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

    // foreign affairs
    /**
     * Item
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "item_id")
    public Item getItemId() {
        return this.itemId;
    }

    /**
     * Item
     */
    public void setItemId(Item obj) {
        this.itemId = obj;
    }

    /**
     * Claim
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "claim_id")
    public Claim getClaimId() {
        return this.claimId;
    }

    /**
     * Claim
     */
    public void setClaimId(Claim obj) {
        this.claimId = obj;
    }

    /**
     * MeasurementUnit
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "measurement_unit_id")
    public MeasurementUnit getMeasurementUnitId() {
        return this.measurementUnitId;
    }

    /**
     * MeasurementUnit
     */
    public void setMeasurementUnitId(MeasurementUnit obj) {
        this.measurementUnitId = obj;
    }

    @Column(name = "benefit_check_by")
    public String getBenefitCheckedBy() {
        return benefitCheckedBy;
    }

    public void setBenefitCheckedBy(String benefitCheckedBy) {
        this.benefitCheckedBy = benefitCheckedBy;
    }

    @Column(name = "benefit_check_date")
    public java.sql.Date getBenefitCheckedDate() {
        return benefitCheckedDate;
    }

    public void setBenefitCheckedDate(java.sql.Date benefitCheckedDate) {
        this.benefitCheckedDate = benefitCheckedDate;
    }

    @Column(name = "excess_value")
    public Double getExcessValue() {
        return excessValue;
    }

    public void setExcessValue(Double excessValue) {
        this.excessValue = excessValue;
    }

    @Column(name = "verified_by")
    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    @Column(name = "verified_date")    
    public java.sql.Date getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(java.sql.Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    @Column(name = "medical_remarks")
    public String getMedicalRemarks() {
        return medicalRemarks;
    }

    public void setMedicalRemarks(String medicalRemarks) {
        this.medicalRemarks = medicalRemarks;
    }

    @Column(name = "benefit_check_remarks")
    public String getBenefitCheckRemarks() {
        return benefitCheckRemarks;
    }

    public void setBenefitCheckRemarks(String benefitCheckRemarks) {
        this.benefitCheckRemarks = benefitCheckRemarks;
    }

    @Column(name = "ex_gratia_value")
    public Double getClaimExGratia() {
        return claimExGratia;
    }

    public void setClaimExGratia(Double claimExGratia) {
        this.claimExGratia = claimExGratia;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_benefit_id")
    public MemberBenefit getMemberBenefitId() {
        return memberBenefitId;
    }

    public void setMemberBenefitId(MemberBenefit memberBenefitId) {
        this.memberBenefitId = memberBenefitId;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "correction_claim_item_id")
    public ClaimItem getCorrectionClaimItem() {
        return correctionClaimItem;
    }

    public void setCorrectionClaimItem(ClaimItem correctionClaimItem) {
        this.correctionClaimItem = correctionClaimItem;
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

    @Column(name = "corrected_by")
    public String getCorrectedBy() {
        return correctedBy;
    }

    public void setCorrectedBy(String correctedBy) {
        this.correctedBy = correctedBy;
    }
    
    @Column(name="post_approve_remaining_benefit")
    public Double getPostApproveRemainingBenefit() {
        return postApproveRemainingBenefit;
    }

    public void setPostApproveRemainingBenefit(Double postApproveRemainingBenefit) {
        this.postApproveRemainingBenefit = postApproveRemainingBenefit;
    }

    @Column(name="pre_approve_remaining_benefit")
    public Double getPreApproveRemainingBenefit() {
        return preApproveRemainingBenefit;
    }

    public void setPreApproveRemainingBenefit(Double preApproveRemainingBenefit) {
        this.preApproveRemainingBenefit = preApproveRemainingBenefit;
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
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="claim_id" ,insertable=false,updatable=false)
    public Claim getClaim2Id(){
        return this.claimId;
    }
    public void setClaim2Id (Claim claim){
        this.claimId = claim;
    }


    @Column(name="pre_paid_excess")
    public Double getPrePaidExcess() {
		return prePaidExcess;
	}

	public void setPrePaidExcess(Double prePaidExcess) {
		this.prePaidExcess = prePaidExcess;
	}

	@Column(name="refund")
	public Double getRefund() {
		return refund;
	}

	public void setRefund(Double refund) {
		this.refund = refund;
	}

	public ClaimItemDto export() {

        ClaimItemDto result = new ClaimItemDto();

        result.setClaimItemId(claimItemId);
        System.out.println("CLAIM ITEM ID EXPORT : " + claimItemId);
        result.setBenefitRemarks(benefitCheckRemarks);
        result.setClaimItemAmount(claimItemAmount);
        result.setClaimItemApprovedValue(claimItemApprovedValue);
        result.setClaimItemName(itemId.getItemName());
        result.setClaimItemValue(claimItemValue);
        result.setClaimNumber(claimId.getClaimNumber());
        result.setDescription(claimItemDescription);
        

        result.setExcessValue(excessValue);
        result.setItemCode(itemId.getItemCode());

        return result;
    }
    
    

	@Column(name="is_reconciled")
    public Integer getIsReconciled() {
		return isReconciled;
	}

	public void setIsReconciled(Integer isReconciled) {
		this.isReconciled = isReconciled;
	}

	@Column(name="reconciled_by")
	public String getReconciledBy() {
		return reconciledBy;
	}

	public void setReconciledBy(String reconciledBy) {
		this.reconciledBy = reconciledBy;
	}

	@Column(name="reconcile_time")
	public Timestamp getReconcileTime() {
		return reconcileTime;
	}

	public void setReconcileTime(Timestamp reconcileTime) {
		this.reconcileTime = reconcileTime;
	}

	public ClaimItem clone() {
        ClaimItem claimItem = new ClaimItem();

        claimItem.setBenefitCheckedBy(benefitCheckedBy);
        claimItem.setBenefitCheckedDate(benefitCheckedDate);
        claimItem.setBenefitCheckRemarks(benefitCheckRemarks);
        claimItem.setClaimExGratia(claimExGratia);
        claimItem.setClaimId(claimId);
        claimItem.setClaimItemAmount(claimItemAmount);
        claimItem.setClaimItemApprovedValue(claimItemApprovedValue);
        claimItem.setClaimItemCoveredValue(claimItemCoveredValue);
        claimItem.setClaimItemDescription(this.claimItemDescription);
        //claimItem.setClaimItemId(value)
        claimItem.setClaimItemRemarks(claimItemRemarks);
        claimItem.setClaimItemStatus(this.claimItemStatus);
        claimItem.setClaimItemValue(claimItemValue);
        claimItem.setCorrectedBy(correctedBy);
        claimItem.setCorrectionClaimItem(correctionClaimItem);
        claimItem.setCorrectionTime(correctionTime);
        claimItem.setCoverageStatus(coverageStatus);
        claimItem.setCreatedBy(createdBy);
        claimItem.setCreatedTime(createdTime);
        claimItem.setDeletedBy(deletedBy);
        claimItem.setDeletedStatus(deletedStatus);
        claimItem.setDeletedTime(deletedTime);
        claimItem.setExcessValue(excessValue);
        claimItem.setIsCorrected(isCorrected);
        claimItem.setItemId(itemId);
        claimItem.setMeasurementUnitId(measurementUnitId);
        claimItem.setMedicalRemarks(medicalRemarks);
        claimItem.setMemberBenefitId(memberBenefitId);
        claimItem.setModifiedBy(modifiedBy);
        claimItem.setModifiedTime(modifiedTime);
        claimItem.setVerifiedBy(verifiedBy);
        claimItem.setVerifiedDate(verifiedDate);

        return claimItem;
    }

	@Column(name="product_currency_charge_value")
	public Double getProductCurrencyChargeValue() {
		return productCurrencyChargeValue;
	}

	public void setProductCurrencyChargeValue(Double productCurrencyChargeValue) {
		this.productCurrencyChargeValue = productCurrencyChargeValue;
	}

	@Column(name="product_currency_approve_value")
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

	@Column(name="exchange_rate")
	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Column(name="exchange_rate_date")
	public Date getExchangeRateDate() {
		return exchangeRateDate;
	}

	public void setExchangeRateDate(Date exchangeRateDate) {
		this.exchangeRateDate = exchangeRateDate;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="policy_benefit_id")
	public PolicyBenefit getPolicyBenefitId() {
		return policyBenefitId;
	}

	public void setPolicyBenefitId(PolicyBenefit policyBenefitId) {
		this.policyBenefitId = policyBenefitId;
	}

	@Column(name="additional_coverage_value")
	public Double getAdditionalCoverageValue() {
		return additionalCoverageValue;
	}

	public void setAdditionalCoverageValue(Double additionalCoverageValue) {
		this.additionalCoverageValue = additionalCoverageValue;
	}

	@Column(name="additional_coverage_note")
	public String getAdditionalCoverageNote() {
		return additionalCoverageNote;
	}

	public void setAdditionalCoverageNote(String additionalCoverageNote) {
		this.additionalCoverageNote = additionalCoverageNote;
	}

	@Column(name="client_item_code")
	public String getClientItemCode() {
		return clientItemCode;
	}

	public void setClientItemCode(String clientItemCode) {
		this.clientItemCode = clientItemCode;
	}

	@Column(name="paid_to_provider")
	public Double getPaidToProvider() {
		return paidToProvider;
	}

	public void setPaidToProvider(Double paidToProvider) {
		this.paidToProvider = paidToProvider;
	}

	@Column(name="fault_excess_provider")
	public Double getFaultExcessProvider() {
		return faultExcessProvider;
	}

	public void setFaultExcessProvider(Double faultExcessProvider) {
		this.faultExcessProvider = faultExcessProvider;
	}

	@Column(name="pembayaran_di_muka")
	public Double getPembayaranDiMuka() {
		return pembayaranDiMuka;
	}

	public void setPembayaranDiMuka(Double pembayaranDiMuka) {
		this.pembayaranDiMuka = pembayaranDiMuka;
	}

	@Column(name="is_smm_benefit")
	public Integer getIsSMMBenefit() {
		return isSMMBenefit;
	}

	public void setIsSMMBenefit(Integer isSMMBenefit) {
		this.isSMMBenefit = isSMMBenefit;
	}
	

	@Column(name="is_multi_layer_benefit")
	public Integer getIsMultiLayerBenefit() {
		return isMultiLayerBenefit;
	}

	public void setIsMultiLayerBenefit(Integer isMultiLayerBenefit) {
		this.isMultiLayerBenefit = isMultiLayerBenefit;
	}
	
	public ClaimExchangeDetailDto exportExchangeDetail(){
		
		ClaimExchangeDetailDto claimDetail = new ClaimExchangeDetailDto();
		
		claimDetail.setClaimId(claimId.getClaimId());
		
		if(itemId != null){
			claimDetail.setBenefitCode(itemId.getItemCode());
			claimDetail.setBenefitDescription(itemId.getItemDescription());
		}
		
		claimDetail.setQtyBenefit(claimItemAmount);
		claimDetail.setAmountIncurred(claimItemValue);
		claimDetail.setAmountApproved(claimItemApprovedValue);
		claimDetail.setAmountNotApproved(excessValue);
		claimDetail.setAmountExcessPaid(prePaidExcess);
		
		claimDetail.setAmountRefund(refund);
		claimDetail.setAmountPaidToProv(paidToProvider);
		
		claimDetail.setRemarks(claimItemRemarks);
		
		return claimDetail;
		
	}
	
	
}