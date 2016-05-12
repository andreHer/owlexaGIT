
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
import com.ametis.cms.datamodel.OutstandingClaim;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.OutstandingClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.OutstandingClaimForm;



// imports+ 

// imports- 

/**
 * OutstandingClaim is a mapping of outstanding_claim Table.
*/
public class OutstandingClaimFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	OutstandingClaimService outstandingClaimService ;
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

	public void setOutstandingClaimService (OutstandingClaimService object){
	    this.outstandingClaimService = object;
	}
	public OutstandingClaimService getOutstandingClaimService (){
	    return this.outstandingClaimService;
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


	public OutstandingClaimFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		OutstandingClaimForm tmp = null;
						Long outstandingClaimId = WebUtil.getParameterLong (request, "outstandingClaimId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								outstandingClaimId != null
				) {
						java.io.Serializable pkey = outstandingClaimId;
						OutstandingClaim object = outstandingClaimService.get (pkey );

			 if (object != null){ // edit object

				tmp = new OutstandingClaimForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new OutstandingClaimForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new OutstandingClaimForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																																										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		OutstandingClaimForm outstandingClaimForm = ( OutstandingClaimForm ) command;
		OutstandingClaim outstandingClaim = outstandingClaimForm.getOutstandingClaim();

//		errors.setNestedPath("outstandingClaim");
//		getValidator().validate(outstandingClaim, errors);
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

		OutstandingClaimForm outstandingClaimForm = ( OutstandingClaimForm ) command;

		OutstandingClaim res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			
			if (outstandingClaimForm.isNewOutstandingClaim ()) {
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEOUTSTANDINGCLAIM");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEOUTSTANDINGCLAIM access");
					return errorResult;
					
				}
				res = outstandingClaimService.create (outstandingClaimForm.getOutstandingClaim(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.outstandingclaim",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.outstandingclaim",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEOUTSTANDINGCLAIM");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEOUTSTANDINGCLAIM access");
					return errorResult;
					
				}
				res = outstandingClaimService.update (outstandingClaimForm.getOutstandingClaim(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.outstandingclaim",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.outstandingclaim",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (outstandingClaimForm.isNewOutstandingClaim ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.outstandingclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.outstandingclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("outstandingclaim"+"?alert="+alertMsg));
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

// class- 
}
