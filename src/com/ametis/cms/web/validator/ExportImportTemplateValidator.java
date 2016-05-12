package com.ametis.cms.web.validator;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
// imports+ 

// imports- 
/**
 * ExportImportTemplate is a mapping of export_import_template Table.
 */
public class ExportImportTemplateValidator implements Validator // extends+ 
// extends- 
{

    public boolean supports(Class clazz) {
        return ExportImportTemplateForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "ID_REQUIRED", "id is required");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "templateMapping", "TEMPLATE_MAPPING_REQUIRED", "Template Mapping is required");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "templateName", "TEMPLATE_NAME_REQUIRED", "Template Name is required");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "delimiter", "DELIMITER_REQUIRED", "Delimiter is required");

// foreign affairs
        // -- foreign affairs end

    }
// class+ 
// class- 
}
