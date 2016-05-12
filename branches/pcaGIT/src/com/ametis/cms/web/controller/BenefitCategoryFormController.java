
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BenefitCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BenefitCategoryService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.BenefitCategoryForm;



// imports+ 

// imports- 


/**
 * BenefitCategory is a mapping of benefit_category Table.
*/
public class BenefitCategoryFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	BenefitCategoryService benefitCategoryService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
		
	// -- foreign affairs end

	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public void setBenefitCategoryService (BenefitCategoryService object){
	    this.benefitCategoryService = object;
	}
	public BenefitCategoryService getBenefitCategoryService (){
	    return this.benefitCategoryService;
	}
		// generate by default
		private UserService  userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}


	public BenefitCategoryFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		BenefitCategoryForm tmp = null;
						Integer benefitCategoryId = WebUtil.getParameterInteger (request,"benefitCategoryId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								benefitCategoryId != null
				) {
						java.io.Serializable pkey = benefitCategoryId;
						BenefitCategory object = benefitCategoryService.get (pkey );

			 if (object != null){ // edit object

				tmp = new BenefitCategoryForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new BenefitCategoryForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new BenefitCategoryForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		BenefitCategoryForm benefitCategoryForm = ( BenefitCategoryForm ) command;
		BenefitCategory benefitCategory = benefitCategoryForm.getBenefitCategory();

//		errors.setNestedPath("benefitCategory");
//		getValidator().validate(benefitCategory, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
			ini dipake buat populate data - data yang dibutuhkan
			contoh : Problem membutuhkan ProblemCategory
			nah fungsi method ini yaitu untuk populate list problem category ke jsp
			nanti biar bisa ditangkep sama jspnya

			contoh : Collection pc = pcontroller.searchPC();

			model.addObject("pcbeans", pc);

		*/

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		BenefitCategoryForm benefitCategoryForm = ( BenefitCategoryForm ) command;

		BenefitCategory res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (benefitCategoryForm.isNewBenefitCategory ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEBENEFITCATEGORY");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEBENEFITCATEGORY access");
				return errorResult;
				
			}
				res = benefitCategoryService.create (benefitCategoryForm.getBenefitCategory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.benefitcategory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.benefitcategory",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEBENEFITCATEGORY");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEBENEFITCATEGORY access");
				return errorResult;
				
			}
				res = benefitCategoryService.update (benefitCategoryForm.getBenefitCategory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.benefitcategory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.benefitcategory",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (benefitCategoryForm.isNewBenefitCategory ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.benefitcategory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.benefitcategory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("benefitcategory"+"?alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
	}
// class+ 

// class- 

}
