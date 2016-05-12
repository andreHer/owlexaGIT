
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
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CaseCategoryForm;



// imports+ 

// imports- 


/**
 * CaseCategory is a mapping of case_category Table.
*/
public class CaseCategoryFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	CaseCategoryService caseCategoryService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
		
	// -- foreign affairs end

	private SecurityService securityService;
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public void setCaseCategoryService (CaseCategoryService object){
	    this.caseCategoryService = object;
	}
	public CaseCategoryService getCaseCategoryService (){
	    return this.caseCategoryService;
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


	public CaseCategoryFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		CaseCategoryForm tmp = null;
						Integer caseCategoryId = WebUtil.getParameterInteger (request,"caseCategoryId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								caseCategoryId != null
				) {
						java.io.Serializable pkey = caseCategoryId;
						CaseCategory object = caseCategoryService.get (pkey );

			 if (object != null){ // edit object

				tmp = new CaseCategoryForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseCategoryForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CaseCategoryForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		CaseCategoryForm caseCategoryForm = ( CaseCategoryForm ) command;
		CaseCategory caseCategory = caseCategoryForm.getCaseCategory();

//		errors.setNestedPath("caseCategory");
//		getValidator().validate(caseCategory, errors);
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

		CaseCategoryForm caseCategoryForm = ( CaseCategoryForm ) command;

		CaseCategory res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (caseCategoryForm.isNewCaseCategory ()) {
			
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATECASECATEGORY");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATECASECATEGORY access");
					return errorResult;
					
				}
				res = caseCategoryService.create (caseCategoryForm.getCaseCategory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.casecategory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.casecategory",null,"fail",Locale.getDefault());
				}
			}
			else {
				
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATECASECATEGORY");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATECASECATEGORY access");
					return errorResult;
					
				}
				
				res = caseCategoryService.update (caseCategoryForm.getCaseCategory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.casecategory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.casecategory",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (caseCategoryForm.isNewCaseCategory ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.casecategory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.casecategory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("casecategory"+"?alert="+alertMsg));
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
