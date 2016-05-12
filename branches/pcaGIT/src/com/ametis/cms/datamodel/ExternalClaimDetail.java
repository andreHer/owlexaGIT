package com.ametis.cms.datamodel;

import java.sql.*;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.Generated;

@Entity
@Table(name = "external_claim_detail")
public class ExternalClaimDetail implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public static final String CLAIM_NUMBER = "ClaimNumber";
    public static final String ITEM_CODE = "ItemCode";
    public static final String CLAIM_ITEM_CHARGE = "ClaimItemCharge";
    public static final String CLAIM_ITEM_APPROVED = "ClaimItemApproved";
    public static final String CLAIM_ITEM_EXCESS = "ClaimItemExcess";
    public static final String DESCRIPTION = "ClaimDescription";
    public static final String BENEFIT_REMARKS = "BenefitRemarks";
    public static final String TOTAL_ITEM_USAGE = "TotalItemUsage";
    public static final String CLAIM_ITEM_BENEFIT = "ClaimItemBenefit";
    public static final String ITEM_NAME = "ItemName";
    //Fields
    /**
     * data for the column :
     *
     * --------- external_claim_detail.id --------- schema = null tableName =
     * external_claim_detail foreignCol = foreignTab = catalog = insura remarks
     * = auto_increment defaultValue = null decDigits = 0 radix = 10 nullable =
     * 0 ordinal = 1 size = 11 type = 4 isPrimaryKey = true
     *
     * =========================================
     *
     *
     */
    private Integer id;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.claim_number --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 2 size = 155 type = 12 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String claimNumber;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.item_code --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 3 size = 155 type = 12 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String itemCode;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.item_name --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 4 size = 255 type = 12 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String itemName;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.charge_value --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 5 size = 22 type = 8 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Double chargeValue;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.approved_value --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 6 size = 22 type = 8 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Double approvedValue;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.excess_value --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 7 size = 22 type = 8 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private Double excessValue;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.status --------- schema = null tableName
     * = external_claim_detail foreignCol = foreignTab = catalog = insura
     * remarks = defaultValue = null decDigits = 0 radix = 10 nullable = 1
     * ordinal = 8 size = 50 type = 12 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String status;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.created_by --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 9 size = 50 type = 12 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String createdBy;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.created_time --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 10 size = 10 type = 91 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private java.sql.Date createdTime;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.modified_time --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 11 size = 10 type = 91 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private java.sql.Date modifiedTime;
    /**
     * data for the column :
     *
     * --------- external_claim_detail.modified_by --------- schema = null
     * tableName = external_claim_detail foreignCol = foreignTab = catalog =
     * insura remarks = defaultValue = null decDigits = 0 radix = 10 nullable =
     * 1 ordinal = 12 size = 50 type = 12 isPrimaryKey = false
     *
     * =========================================
     *
     *
     */
    private String modifiedBy;
    private String benefitRemarks;
    private String description;
    private Integer itemAmount;
    private Double claimItemBenefit;
    private Integer importSessionId;

    // foreign affairs
    // -- foreign affairs end
    // -- exported key
    // -- exported key end
    // Fields End
    // PK GETTER SETTER
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer value) {
        id = value;
    }
    // PK GETTER SETTER END

    @Column(name = "claim_number")
    public java.lang.String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(java.lang.String value) {
        claimNumber = value;
    }

    @Column(name = "item_code")
    public java.lang.String getItemCode() {
        return itemCode;
    }

    public void setItemCode(java.lang.String value) {
        itemCode = value;
    }

    @Column(name = "item_name")
    public java.lang.String getItemName() {
        return itemName;
    }

    public void setItemName(java.lang.String value) {
        itemName = value;
    }

    @Column(name = "charge_value")
    public java.lang.Double getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(java.lang.Double value) {
        chargeValue = value;
    }

    @Column(name = "approved_value")
    public java.lang.Double getApprovedValue() {
        return approvedValue;
    }

    public void setApprovedValue(java.lang.Double value) {
        approvedValue = value;
    }

    @Column(name = "excess_value")
    public java.lang.Double getExcessValue() {
        return excessValue;
    }

    public void setExcessValue(java.lang.Double value) {
        excessValue = value;
    }

    @Column(name = "status")
    public java.lang.String getStatus() {
        return status;
    }

    public void setStatus(java.lang.String value) {
        status = value;
    }

    @Column(name = "created_by")
    public java.lang.String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(java.lang.String value) {
        createdBy = value;
    }

    @Column(name = "created_time")
    public java.sql.Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(java.sql.Date value) {
        createdTime = value;
    }

    @Column(name = "modified_time")
    public java.sql.Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(java.sql.Date value) {
        modifiedTime = value;
    }

    @Column(name = "modified_by")
    public java.lang.String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(java.lang.String value) {
        modifiedBy = value;
    }

    @Column(name = "benefit_remarks")
    public String getBenefitRemarks() {
        return benefitRemarks;
    }

    public void setBenefitRemarks(String benefitRemarks) {
        this.benefitRemarks = benefitRemarks;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "item_amount")
    public Integer getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }

    @Column(name = "item_benefit_value")
    public Double getClaimItemBenefit() {
        return claimItemBenefit;
    }

    public void setClaimItemBenefit(Double claimItemBenefit) {
        this.claimItemBenefit = claimItemBenefit;
    }

    public void setValue(String key, String value) {
        if (key.equalsIgnoreCase(BENEFIT_REMARKS)) {
            this.benefitRemarks = value;
        }
        if (key.equalsIgnoreCase(CLAIM_ITEM_APPROVED)) {
            this.approvedValue = Double.valueOf(value);
        }
        if (key.equalsIgnoreCase(CLAIM_ITEM_BENEFIT)) {
            this.claimItemBenefit = Double.valueOf(value);
        }
        if (key.equalsIgnoreCase(CLAIM_ITEM_CHARGE)) {
            this.chargeValue = Double.valueOf(value);
        }
        if (key.equalsIgnoreCase(CLAIM_ITEM_APPROVED)) {
            this.approvedValue = Double.valueOf(value);
        }
        if (key.equalsIgnoreCase(CLAIM_ITEM_EXCESS)) {
            this.excessValue = Double.valueOf(value);
        }
        if (key.equalsIgnoreCase(CLAIM_NUMBER)) {
            this.claimNumber = value;
        }
        if (key.equalsIgnoreCase(DESCRIPTION)) {
            this.description = value;
        }
        if (key.equalsIgnoreCase(ITEM_CODE)) {
            this.itemCode = value;
        }
        if (key.equalsIgnoreCase(TOTAL_ITEM_USAGE)) {
            this.itemAmount = Integer.valueOf(value);
        }
        if (key.equalsIgnoreCase(ITEM_NAME)) {
            this.itemName = value;
        }

    }
    
    
    // foreign affairs
    // foreign affairs end
// exported key
    //exported key end

    @Column(name="import_session_id")
    public Integer getImportSessionId() {
        return importSessionId;
    }

    public void setImportSessionId(Integer importSessionId) {
        this.importSessionId = importSessionId;
    }
}