
package com.ametis.cms.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ametis.cms.web.form.ProductBenefitForm;

// imports- 

/**
 * ProductBenefit is a mapping of product_benefit Table.
*/
public class ProductBenefitValidator implements Validator
// extends+ 

// extends- 
{
	public boolean supports(Class clazz) {
		return ProductBenefitForm.class.isAssignableFrom(clazz);
	}
	public void validate(Object obj, Errors errors) {
		
		ProductBenefitForm form = (ProductBenefitForm) obj;
		
								ValidationUtils.rejectIfEmptyOrWhitespace
 (errors,"productBenefitId","PRODUCT_BENEFIT_ID_REQUIRED","productBenefitId is required");
																																																																																																																																																																																				
// foreign affairs
	
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"benefitUsageType","BENEFIT_USAGE_TYPE_REQUIRED",
	"benefitUsageType is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"productId","PRODUCT_ID_REQUIRED",
	"productId is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"measurementUnit","MEASUREMENT_UNIT_REQUIRED",
	"measurementUnit is required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"treatmentLocation","TREATMENT_LOCATION_REQUIRED",
	"treatmentLocation is required");
		
		if (form.getDiagnosisName() == null || form.getDiagnosisName().equalsIgnoreCase("")){
			ValidationUtils.rejectIfEmptyOrWhitespace
			(errors,"itemCategoryId","ITEM_CATEGORY_ID_REQUIRED",
			"itemCategoryId is required");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace
(errors,"caseCategoryId","CASE_CATEGORY_ID_REQUIRED",
	"caseCategoryId is required");
			// -- foreign affairs end

	}

// class+ 

// class- 
}
