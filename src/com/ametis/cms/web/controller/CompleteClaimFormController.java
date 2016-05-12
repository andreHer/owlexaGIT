
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
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

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.PendingCategoryService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CompleteClaimForm;



// imports+ 

// imports- 


/**
 * PendingClaim is a mapping of pending_claim Table.
*/
public class CompleteClaimFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ClaimService claimService;
	ResourceBundleMessageSource alertProperties ;
	PendingCategoryService pendingCategoryService;
	// foreign affairs
	
		
	// -- foreign affairs end


			// generate by default
	private UserService  userService;
	
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
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


	public CompleteClaimFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		CompleteClaimForm tmp = null;
		
		Integer completeClaimId = WebUtil.getParameterInteger (request,"completeClaimId");
		Integer claimId = WebUtil.getParameterInteger(request,"claimId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (completeClaimId != null) {
			java.io.Serializable pkey = completeClaimId;
			Claim object = null;

			 if (object != null){ // edit object

				tmp = new CompleteClaimForm(object);
			
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CompleteClaimForm();
					 // foreign affairs
		
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CompleteClaimForm();
			
			String breadcrumb = "<a href=\"claim?navigation=detail&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Complete Pending Claim";
			request.setAttribute("breadcrumb", breadcrumb);
		}
				
		if (claimId != null && tmp != null){
			Claim claim = claimService.get(claimId);
			
			if (claim != null){
				tmp.setClaim(claim);
			}
			else {
				tmp.setClaim(new Claim());
			}
		}
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		CompleteClaimForm completeClaimForm = ( CompleteClaimForm ) command;
		
		
		System.out.println ("ERROR : " + errors);
//		errors.setNestedPath("pendingClaim");
//		getValidator().validate(pendingClaim, errors);
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

		CompleteClaimForm completeClaimForm = ( CompleteClaimForm ) command;
		String redirectURL = "claim";

		Claim res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			if (completeClaimForm.isNewCompleteClaim()) {
				
				
				res = null;
					
				
				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.completeclaim",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.completeclaim",null,"fail",Locale.getDefault());
				}
				redirectURL = "claim?claimId" + completeClaimForm.getClaim().getClaimId() +"&alert="+alertMsg;
			}
			else {
				res = null;

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.completeclaim",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.completeclaim",null,"fail",Locale.getDefault());
				}
				redirectURL = "claim?claimId" + completeClaimForm.getClaim().getClaimId()+"&alert="+alertMsg;

			}
		}catch (Exception ex) {
			if (completeClaimForm.isNewCompleteClaim()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.completeclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.completeclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(redirectURL));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Integer.class,true);
		binder.registerCustomEditor(Integer.class,num);
	}
// class+ 
	
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

// class- 

}
