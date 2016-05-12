
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
/*
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
*/
import java.util.Locale;
import java.util.Collection;
import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import com.ametis.cms.service.*;
import com.ametis.cms.util.*;



// imports+ 

// imports- 

/**
 * PolicyCoverageFund is a mapping of policy_coverage_fund Table.
*/
public class PolicyCoverageFundFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	PolicyCoverageFundService policyCoverageFundService ;
	private CaseCategoryService caseCategoryService;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
		
	// -- foreign affairs end
	

	public void setPolicyCoverageFundService (PolicyCoverageFundService object){
	    this.policyCoverageFundService = object;
	}
	public PolicyCoverageFundService getPolicyCoverageFundService (){
	    return this.policyCoverageFundService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getUserService() {
		return actionuserService;
	}
	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}
	
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}
	
	public void setSecurityService (SecurityService object){
	    this.securityService = object;
	}
	public SecurityService getSecurityService (){
	    return this.securityService;
	}

	
	public PolicyCoverageFundFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyCoverageFundForm tmp = null;
						Integer policyCoverageFundId = WebUtil.getParameterInteger (request,"policyCoverageFundId");
		
						Integer policyId = WebUtil.getParameterInteger(request, "policyId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								policyCoverageFundId != null
				) {
						java.io.Serializable pkey = policyCoverageFundId;
						PolicyCoverageFund object = policyCoverageFundService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PolicyCoverageFundForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyCoverageFundForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyCoverageFundForm();
					 // foreign affairs
			// -- foreign affairs end



		}
		
		if (policyId != null){
			tmp.setPolicyId(policyId.toString());
		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		PolicyCoverageFundForm policyCoverageFundForm = ( PolicyCoverageFundForm ) command;
		PolicyCoverageFund policyCoverageFund = policyCoverageFundForm.getPolicyCoverageFund();

//		errors.setNestedPath("policyCoverageFund");
//		getValidator().validate(policyCoverageFund, errors);
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
		
		try {
			Collection<CaseCategory> ccList = caseCategoryService.getAll();
			model.put("caseCategoryList", ccList);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PolicyCoverageFundForm policyCoverageFundForm = ( PolicyCoverageFundForm ) command;

		PolicyCoverageFund res = null;
		String alertMsg="";
		String policyId = "";
		try {

			policyId = policyCoverageFundForm.getPolicyId();
			
			ActionUser user = securityService.getActionUser(request);
			
			if (policyCoverageFundForm.isNewPolicyCoverageFund ()) {
				res = policyCoverageFundService.create (policyCoverageFundForm.getPolicyCoverageFund(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.policycoveragefund",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policycoveragefund",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyCoverageFundService.update (policyCoverageFundForm.getPolicyCoverageFund(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policycoveragefund",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policycoveragefund",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (policyCoverageFundForm.isNewPolicyCoverageFund ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policycoveragefund",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policycoveragefund",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("policycoveragefund"+"?alert="+alertMsg+"&policyId="+policyId));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
	}
// class+ 
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}
	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

// class- 
}
