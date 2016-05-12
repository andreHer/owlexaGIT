package com.ametis.cms.web.form;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.DiagnosisSet;
import com.ametis.cms.datamodel.DiscountUsageType;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.datamodel.Procedure;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductBenefit;
import com.ametis.cms.datamodel.ProductLayerLimit;
import com.ametis.cms.datamodel.TreatmentLocation;
import com.ametis.cms.util.StringUtil;

// imports- 
/**
 * ProductBenefit is a mapping of product_benefit Table.
 */
public class ProductBenefitForm implements java.io.Serializable // extends+ 
// extends- 
{

    private boolean isNewProductBenefit = false;
    private ProductBenefit productBenefitBean;
    private String productName;
    private String itemCategoryName;
    private String sharedBenefitName;
    
    private String diagnosisName;
    private String procedureName;
    private String diagnosisSetName;
    private String layerName;
    private String layerType;
    /*
     * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
     * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
     * mengacu ke referensi itu biar nanti automatic loading
     *
     */

    public ProductBenefitForm() {
        this.productBenefitBean = new ProductBenefit();
        this.isNewProductBenefit = true;
    }

    public ProductBenefitForm(ProductBenefit object) {
        this.productBenefitBean = object;
    }

    public boolean isNewProductBenefit() {

        return this.isNewProductBenefit;
    }

    public ProductBenefit getProductBenefit() {
        return this.productBenefitBean;
    }

    public void setProductBenefit(ProductBenefit object) {
        this.productBenefitBean = object;
    }

    public void setProductBenefitId(String obj) {

        productBenefitBean.setProductBenefitId(StringUtil.getIntegerValue(obj, 0));

    }

    public String getProductBenefitId() {
        return StringUtil.getStringValue(
                productBenefitBean.getProductBenefitId());

    }

    public void setBenefitLimit(String obj) {

        productBenefitBean.setBenefitLimit(StringUtil.getDoubleValue(obj, 0));

    }

    public String getBenefitLimit() {
    	String result = "";
    	
    	if (productBenefitBean.getBenefitLimit() != null){
    		BigDecimal bc = new BigDecimal(productBenefitBean.getBenefitLimit());
    		result = bc.toPlainString();
    	}
    	return result;
        

    }
    public void setDeductibleLimit(String obj) {

        productBenefitBean.setDeductibleLimit(StringUtil.getDoubleValue(obj, 0));

    }

    public String getDeductibleLimit() {
String result = "";
    	
    	if (productBenefitBean.getDeductibleLimit() != null){
    		BigDecimal bc = new BigDecimal(productBenefitBean.getDeductibleLimit());
    		result = bc.toPlainString();
    	}
    	return result;

    }
    public void setIsExcludePlanLimit(String obj) {
    	System.out.println("obj " + obj);
    	if (obj != null && !obj.equalsIgnoreCase("")){
			productBenefitBean.setIsDeductPlanLimit(Integer.valueOf(obj));
    	}
    	else {
    		productBenefitBean.setIsDeductPlanLimit(1);
    	}
    }

    public String getIsExcludePlanLimit() {
    	String result = "";
    	
    	if (productBenefitBean.getIsDeductPlanLimit() != null){
    		result = StringUtil.getStringValue(productBenefitBean.getIsDeductPlanLimit());
    	}
        return result;

    }
    public void setProviderContract(String obj) {

        productBenefitBean.setProviderContract(StringUtil.getIntegerValue(obj, 0));

    }

    public String getProviderContract() {
        return StringUtil.getStringValue(
                productBenefitBean.getProviderContract());

    }

    public void setMaxOccurance(String obj) {

    	if (obj != null && !obj.equalsIgnoreCase("")){
    		Double doubleVal = Double.valueOf(obj);
    		productBenefitBean.setMaxOccurance(doubleVal.intValue());
    	}

    }

    public String getMaxOccurance() {
    	String result = "";
    	
    	if (productBenefitBean.getMaxOccurance() != null){
    		result = StringUtil.getStringValue(
                    productBenefitBean.getMaxOccurance());
    	}
    	return result ;
        

    }

    public void setMaxOccurancePerCase(String obj) {

        if (obj != null && !obj.equalsIgnoreCase("")) {
        	Double doubleVal = Double.valueOf(obj);
            productBenefitBean.setMaxOccurancePerCase(doubleVal);
        }
    }

    public String getMaxOccurancePerCase() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getMaxOccurancePerCase() != null) {
            result = StringUtil.getStringValue(productBenefitBean.getMaxOccurancePerCase());
        }

        return result;


    }

    public void setMaxBenefitPerCase(String obj) {

        if (obj != null && !obj.equalsIgnoreCase("")) {
            productBenefitBean.setMaxBenefitPerCase(StringUtil.getDoubleValue(obj, 0));
        }
    }

    public String getMaxBenefitPerCase() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getMaxBenefitPerCase() != null) {
        	
        		BigDecimal bc = new BigDecimal(productBenefitBean.getMaxBenefitPerCase());
        		result = bc.toPlainString();
        	
        }

        return result;


    }

    public void setDiscount(String obj) {

        productBenefitBean.setDiscount(StringUtil.getDoubleValue(obj, 0));

    }

    public String getDiscount() {
        return StringUtil.getStringValue(
                productBenefitBean.getDiscount());

    }

    public void setCostSharingPercentage(String obj) {

        if (obj != null && !obj.equalsIgnoreCase("")) {
            productBenefitBean.setCostSharingPercentage(StringUtil.getDoubleValue(obj, 0));
        }
    }

    public String getCostSharingAmount() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getCostSharingAmount() != null) {
            
        		BigDecimal bc = new BigDecimal(productBenefitBean.getCostSharingAmount());
        		result = bc.toPlainString();
        	
        }
        return result;
    }
    public void setCostSharingAmount(String obj) {

        if (obj != null && !obj.equalsIgnoreCase("")) {
            productBenefitBean.setCostSharingAmount(StringUtil.getDoubleValue(obj, 0));
        }
    }

    public String getCostSharingPercentage() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getCostSharingPercentage() != null) {
            result = StringUtil.getStringValue(productBenefitBean.getCostSharingPercentage());
        }
        return result;
    }

    public void setIsCostSharing(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {

            if (obj.equalsIgnoreCase("1")) {
                productBenefitBean.setCostSharing(true);
            } else {
                productBenefitBean.setCostSharing(false);
            }
        } else {
            productBenefitBean.setCostSharing(false);
        }
    }

    public String getIsCostSharing() {
        String result = "0";

        if (productBenefitBean != null && productBenefitBean.isCostSharing() != null && productBenefitBean.isCostSharing()) {
            result = "1";
        }
        return result;
    }

    public void setIsAsCharge(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {

            if (obj.equalsIgnoreCase("1")) {
                productBenefitBean.setIsAsCharge(true);
            } else {
                productBenefitBean.setIsAsCharge(false);
            }
        } else {
            productBenefitBean.setIsAsCharge(false);
        }
    }

    public String getIsAsCharge() {
        String result = "0";

        if (productBenefitBean != null && productBenefitBean.getIsAsCharge() != null && productBenefitBean.getIsAsCharge()) {
            result = "1";
        }


        return result;
    }


    public void setDependentCostSharingPercentage(String obj) {

        if (obj != null && !obj.equalsIgnoreCase("")) {
            productBenefitBean.setDependentCoSharePercentage(StringUtil.getDoubleValue(obj, 0));
        }
    }

    public String getDependentCostSharingAmount() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getDependentCoShareAmount() != null) {
            result = StringUtil.getStringValue(productBenefitBean.getDependentCoShareAmount());
        }
        return result;
    }
    public void setDependentCostSharingAmount(String obj) {

        if (obj != null && !obj.equalsIgnoreCase("")) {
            productBenefitBean.setDependentCoShareAmount(StringUtil.getDoubleValue(obj, 0));
        }
    }

    public String getDependentCostSharingPercentage() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getDependentCoSharePercentage() != null) {
            result = StringUtil.getStringValue(productBenefitBean.getDependentCoSharePercentage());
        }
        return result;
    }


    // foreign affairs
    public void setDiscountUsageType(String obj) {

        if (obj != null && !obj.equals("")) {
            DiscountUsageType fk = new DiscountUsageType();
            fk.setDiscountUsageTypeId(StringUtil.getIntegerValue(obj, 0));
            productBenefitBean.setDiscountUsageType(fk);

        }

    }

    public String getDiscountUsageType() {
        String result = "";
        if (productBenefitBean.getDiscountUsageType() != null) {
            result = StringUtil.getStringValue(
                    productBenefitBean.getDiscountUsageType().getDiscountUsageTypeId());
        }
        return result;


    }
    //---

    public void setBenefitUsageType(String obj) {
        BenefitUsageType fk = new BenefitUsageType();
        fk.setBenefitUsageTypeId(StringUtil.getIntegerValue(obj, 0));
        productBenefitBean.setBenefitUsageType(fk);

    }

    public String getBenefitUsageType() {
    	String result = "";
    	
    	if (productBenefitBean.getBenefitUsageType() != null){
    		result = StringUtil.getStringValue(
                    productBenefitBean.getBenefitUsageType().getBenefitUsageTypeId());
    	}
    	
    	return result;
        

    }
    //---

    public void setProductId(String obj) {
    	
    	if(obj != null && !obj.equals("")){
	        Product fk = new Product();
	        fk.setProductId(StringUtil.getIntegerValue(obj, 0));
	        productBenefitBean.setProductId(fk);
    	}

    }

    public String getProductId() {
    	String result = "";
    	
    	if (productBenefitBean.getProductId()!= null){
    		result = productBenefitBean.getProductId().getProductId().toString();
    	}
    		
    	return result;

    }
    public void setProductLayerId(String obj) {
    	
    	if(obj != null && !obj.equals("")){
	        ProductLayerLimit fk = new ProductLayerLimit();
	        fk.setProductLayerLimitId(StringUtil.getIntegerValue(obj, 0));
	        productBenefitBean.setProductLayerId(fk);
    	}

    }

    public String getProductLayerId() {
    	String result = "";
    	
    	if (productBenefitBean.getProductLayerId()!= null){
    		result = productBenefitBean.getProductLayerId().getProductLayerLimitId().toString();
    	}
    		
    	return result;

    }
    //---

    public void setMeasurementUnit(String obj) {
    	
    	if (obj != null && !obj.equals("")){
	        MeasurementUnit fk = new MeasurementUnit();
	        fk.setMeasurementUnitId(StringUtil.getIntegerValue(obj, 0));
	        productBenefitBean.setMeasurementUnit(fk);
    	}

    }

    public String getMeasurementUnit() {
    	String result = "";
    	
    	if (productBenefitBean.getMeasurementUnit() != null){
    		result = StringUtil.getStringValue(
                    productBenefitBean.getMeasurementUnit().getMeasurementUnitId());
    	}
    	
    	return result;
        

    }
    //---

    public void setTreatmentLocation(String obj) {
        if (obj != null && !obj.equals("")) {
            TreatmentLocation fk = new TreatmentLocation();
            fk.setLocationId(StringUtil.getIntegerValue(obj, 0));
            productBenefitBean.setTreatmentLocation(fk);
        }
    }

    public String getTreatmentLocation() {
        String result = "";

        if (productBenefitBean.getTreatmentLocation() != null) {
            result = StringUtil.getStringValue(productBenefitBean.getTreatmentLocation().getLocationId());
        }
        return result;
    }
    //---

    public void setItemCategoryId(String obj) {
    	if (obj != null && !obj.equals("")){
	        ItemCategory fk = new ItemCategory();
	        fk.setItemCategoryId(StringUtil.getIntegerValue(obj, 0));
	        productBenefitBean.setItemCategoryId(fk);
    	}

    }

    public String getItemCategoryId() {
    	
    	String result ="";
    	if (productBenefitBean.getItemCategoryId() != null){
    		result =  StringUtil.getStringValue(
                    productBenefitBean.getItemCategoryId().getItemCategoryId());
    	}
    	
    	return result;
        

    }
    //---

    public void setCaseCategoryId(String obj) {
    	if (obj != null && !obj.equals("")){
	        CaseCategory fk = new CaseCategory();
	        fk.setCaseCategoryId(StringUtil.getIntegerValue(obj, 0));
	        productBenefitBean.setCaseCategoryId(fk);
    	}

    }

    public String getCaseCategoryId() {
    	String result ="";
    	
    	if (productBenefitBean.getCaseCategoryId() != null){
    		result = StringUtil.getStringValue(
                    productBenefitBean.getCaseCategoryId().getCaseCategoryId());
    	}
    	return result;
        

    }
    public void setSharedBenefitId(String obj) {
    	if (obj != null && !obj.equals("")){
	        ProductBenefit fk = new ProductBenefit();
	        fk.setProductBenefitId(StringUtil.getIntegerValue(obj, 0));
	        productBenefitBean.setSharedBenefitId(fk);
    	}

    }

    public String getSharedBenefitId() {
    	String result ="";
    	
    	if (productBenefitBean.getSharedBenefitId() != null){
    		result = StringUtil.getStringValue(
                    productBenefitBean.getSharedBenefitId().getProductBenefitId());
    	}
    	return result;
        

    }
    public String getProductName() {
       
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }

    public void setTreatmentLevel(String level) {
        if (level != null && !level.equalsIgnoreCase("")) {
            productBenefitBean.setTreatmentLevel(Integer.valueOf(level));
        }
    }

    public String getTreatmentLevel() {
        return StringUtil.getStringValue(productBenefitBean.getTreatmentLevel(), "");
    }

    public String getCoverageDaysInterval() {
        return StringUtil.getStringValue(productBenefitBean.getCoverageDaysInterval(), "");
    }

    public void setCoverageDaysInterval(String coverageDaysInterval) {
        if (coverageDaysInterval != null && !coverageDaysInterval.equals("")) {
            productBenefitBean.setCoverageDaysInterval(Integer.valueOf(coverageDaysInterval));
        }
    }
    public String getPreCoverageDaysInterval() {
        return StringUtil.getStringValue(productBenefitBean.getPreCoverageDaysInterval(), "");
    }

    public void setPreCoverageDaysInterval(String coverageDaysInterval) {
        if (coverageDaysInterval != null && !coverageDaysInterval.equals("")) {
            productBenefitBean.setPreCoverageDaysInterval(Integer.valueOf(coverageDaysInterval));
        }
    }

    public String getBenefitUpgradeLimit() {
        return StringUtil.getStringValue(productBenefitBean.getBenefitUpgradeLimit(), "");
    }

    public void setBenefitUpgradeLimit(String benefitUpgradeLimit) {
        if (benefitUpgradeLimit != null && !benefitUpgradeLimit.equals("")) {
            productBenefitBean.setBenefitUpgradeLimit(Double.valueOf(benefitUpgradeLimit));
        }
    }

    public String getIsBenefitUpgradable() {
        String result = "0";

        if (productBenefitBean != null && productBenefitBean.isBenefitUpgradable() != null && productBenefitBean.isBenefitUpgradable()) {
            result = "1";
        }


        return result;
    }

    public void setIsBenefitUpgradable(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {

            if (obj.equalsIgnoreCase("1")) {
                productBenefitBean.setBenefitUpgradable(true);
            } else {
                productBenefitBean.setBenefitUpgradable(false);
            }
        } else {
            productBenefitBean.setBenefitUpgradable(false);
        }
    }

    public String getBenefitUpgradeCoverageDaysInterval() {
        return StringUtil.getStringValue(productBenefitBean.getBenefitUpgradeCoverageDaysInterval(), "");
    }

    public void setBenefitUpgradeCoverageDaysInterval(
            String benefitUpgradeCoverageDaysInterval) {
        if (benefitUpgradeCoverageDaysInterval != null && !benefitUpgradeCoverageDaysInterval.equals("")) {
            productBenefitBean.setBenefitUpgradeCoverageDaysInterval(Integer.valueOf(benefitUpgradeCoverageDaysInterval));
        }
    }

    public String getDependentSharingMethod() {
        return StringUtil.getStringValue(productBenefitBean.getDependentSharingMethod(), "");
    }

    public void setDependentSharingMethod(String dependentSharingMethod) {
        if (dependentSharingMethod != null && !dependentSharingMethod.equals("")) {
            productBenefitBean.setDependentSharingMethod(Integer.valueOf(dependentSharingMethod));
        }
    }

    public String getDependentCoveredStatus() {
        return StringUtil.getStringValue(productBenefitBean.getDependentCoveredStatus(), "");
    }

    public void setDependentCoveredStatus(String dependentCoveredStatus) {
        if (dependentCoveredStatus != null && !dependentCoveredStatus.equals("")) {
            productBenefitBean.setDependentCoveredStatus(Integer.valueOf(dependentCoveredStatus));
        }
    }

    public void setCashlessPercentage(String cashless) {
        if (cashless != null && !cashless.equalsIgnoreCase("")) {
            productBenefitBean.setCashlessPercentage(Double.valueOf(cashless));
        }
    }

    public String getCashlessPercentage() {
        String result = "";

        result = StringUtil.getStringValue(productBenefitBean.getCashlessPercentage(), "100");
        return result;
    }

    public void setReimbursementPercentage(String reimbursement) {
        if (reimbursement != null && !reimbursement.equalsIgnoreCase("")) {
            productBenefitBean.setReimbursementPercentage(Double.valueOf(reimbursement));
        }
    }

    public String getReimbursementPercentage() {
        String result = "";

        result = StringUtil.getStringValue(productBenefitBean.getReimbursementPercentage(), "100");
        return result;
    }

    public void setIsReimburseAsCharge(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {

            if (obj.equalsIgnoreCase("1")) {
                productBenefitBean.setReimburseAsCharge(true);
            } else {
                productBenefitBean.setReimburseAsCharge(false);
            }
        } else {
            productBenefitBean.setReimburseAsCharge(false);
        }
    }

    public String getIsReimburseAsCharge() {
        String result = "0";

        if (productBenefitBean != null && productBenefitBean.getReimburseAsCharge() != null && productBenefitBean.getReimburseAsCharge()) {
            result = "1";
        }


        return result;
    }

    public void setInformation(String info) {
        
           productBenefitBean.setInformation(info);
        
    }

    public String getInformation() {
        String result = productBenefitBean.getInformation();
        
        return result;
    }
   
    public String getSharedBenefitName() {
		return sharedBenefitName;
	}

	public void setSharedBenefitName(String sharedBenefitName) {
		this.sharedBenefitName = sharedBenefitName;
	}

	public void setReimbursementBenefitLimit(String reimbursement) {
        if (reimbursement != null && !reimbursement.equals("")) {
            productBenefitBean.setReimbursementBenefitLimit(Double.valueOf(reimbursement));
        }
    }

    public String getReimbursementBenefitLimit() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getReimbursementBenefitLimit() != null) {
            
        		BigDecimal bc = new BigDecimal(productBenefitBean.getReimbursementBenefitLimit());
        		result = bc.toPlainString();
        	
        }
        return result;
        
        
    }
    
    public void setSMMLimit(String smm) {
        if (smm != null && !smm.equals("")) {
            productBenefitBean.setSmmLimit(Double.valueOf(smm));
        }
    }

    public String getSMMLimit() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getSmmLimit() != null) {
            
        		BigDecimal bc = new BigDecimal(productBenefitBean.getSmmLimit());
        		result = bc.toPlainString();        	
        }
        return result;
    }
    
    public void setIsSMMBenefit(String smm) {
        if (smm != null && !smm.equals("")) {
            productBenefitBean.setIsSMMBenefit(Integer.valueOf(smm));
        }
    }

    public String getIsSMMBenefit() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getIsSMMBenefit() != null) {
        	result = productBenefitBean.getIsSMMBenefit().toString();        	
        }
        return result;
    }
    public void setEffectiveSMMDay(String smm) {
        if (smm != null && !smm.equals("")) {
            productBenefitBean.setEffectiveSMMDay(Integer.valueOf(smm));
        }
    }

    public String getEffectiveSMMDay() {
        String result = "";

        if (productBenefitBean != null && productBenefitBean.getEffectiveSMMDay() != null) {
        	result = productBenefitBean.getEffectiveSMMDay().toString();        	
        }
        return result;
    }
    
    public void setIsEdcEnabled(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {
            if (obj.equalsIgnoreCase("1")) {
                productBenefitBean.setIsEdcEnabled(Integer.valueOf(1));
            } else {
                productBenefitBean.setIsEdcEnabled(0);
            }
        } else {
            productBenefitBean.setIsEdcEnabled(0);
        }
    }

    public String getIsEdcEnabled() {
        String result = "0";

        if (productBenefitBean != null && productBenefitBean.getIsEdcEnabled() != null && productBenefitBean.getIsEdcEnabled().intValue() == 1) {
            result = "1";
        }


        return result;
    }
    public void setBenefitShowOnEdc(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {
            if (obj.equalsIgnoreCase("1")) {
                productBenefitBean.setBenefitShowedOnEdc(1);
            } else {
                productBenefitBean.setBenefitShowedOnEdc(0);
            }
        } else {
            productBenefitBean.setBenefitShowedOnEdc(0);
        }
    }

    public String getBenefitShowOnEdc() {
        String result = "0";

        if (productBenefitBean != null && productBenefitBean.getBenefitShowedOnEdc() != null && productBenefitBean.getBenefitShowedOnEdc().intValue() == 1) {
            result = "1";
        }


        return result;
    }
    public void setDivertBenefit(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {
            if (obj.equalsIgnoreCase("1")) {
                productBenefitBean.setDivertBenefit(true);
            } else {
            	productBenefitBean.setDivertBenefit(false);
            }
        } else {
        	productBenefitBean.setDivertBenefit(false);
        }
    }

    public String getDivertBenefit() {
        String result = "0";

        if (productBenefitBean != null && productBenefitBean.getDivertBenefit() != null && productBenefitBean.getDivertBenefit().booleanValue()) {
            result = "1";
        }


        return result;
    }
	
	public String getBenefitUpgradeType() {
		
		String result = "";
		if (productBenefitBean.getBenefitUpgradeType() != null){
			result = productBenefitBean.getBenefitUpgradeType().toString();
		}
		return result;
	}

	public void setBenefitUpgradeType(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			productBenefitBean.setBenefitUpgradeType(Integer.valueOf(obj));
		}
	}

	
	public String getBenefitUpgradePercentage() {
		String result = "";
		if (productBenefitBean.getBenefitUpgradePercentage() != null){
			result = productBenefitBean.getBenefitUpgradePercentage().toString();
		}
		return result;
	}

	public void setBenefitUpgradePercentage(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			productBenefitBean.setBenefitUpgradePercentage(Double.valueOf(obj));
		}		
	}
	public String getDiagnosisId() {
		String result = "";
		if (productBenefitBean.getDiagnosisId() != null){
			result = productBenefitBean.getDiagnosisId().getDiagnosisId().toString();
		}
		return result;
	}

	public void setDiagnosisId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			productBenefitBean.setDiagnosisId(diagnosis);
		}		
	}
	public String getDiagnosisSetId() {
		String result = "";
		if (productBenefitBean.getDiagnosisSetId() != null){
			result = productBenefitBean.getDiagnosisSetId().getDiagnosisSetId().toString();
		}
		return result;
	}

	public void setDiagnosisSetId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			DiagnosisSet diagnosis = new DiagnosisSet();
			diagnosis.setDiagnosisSetId(Integer.valueOf(obj));
			productBenefitBean.setDiagnosisSetId(diagnosis);
		}		
	}
	public String getProcedureId() {
		String result = "";
		if (productBenefitBean.getProcedureId() != null){
			result = productBenefitBean.getProcedureId().getProcedureId().toString();
		}
		return result;
	}

	public void setProcedureId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Procedure procedure = new Procedure();
			procedure.setProcedureId(Integer.valueOf(obj));
			productBenefitBean.setProcedureId(procedure);
		}		
	}

	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getDiagnosisSetName() {
		return diagnosisSetName;
	}

	public void setDiagnosisSetName(String diagnosisSetName) {
		this.diagnosisSetName = diagnosisSetName;
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getLayerType() {
		return layerType;
	}

	public void setLayerType(String layerType) {
		this.layerType = layerType;
	}
	
	
}
