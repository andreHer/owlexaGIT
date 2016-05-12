
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
import com.ametis.cms.datamodel.OutstandingClaimItem;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.OutstandingClaimItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.OutstandingClaimItemForm;



// imports+ 

// imports- 

/**
 * OustandingClaimItem is a mapping of oustanding_claim_item Table.
*/
public class OutstandingClaimItemFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	OutstandingClaimItemService oustandingClaimItemService ;
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
	public void setOutstandingClaimItemService (OutstandingClaimItemService object){
	    this.oustandingClaimItemService = object;
	}
	public OutstandingClaimItemService getOutstandingClaimItemService (){
	    return this.oustandingClaimItemService;
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


	public OutstandingClaimItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		OutstandingClaimItemForm tmp = null;
						Long outstandingClaimItemId = WebUtil.getParameterLong (request, "outstandingClaimItemId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								outstandingClaimItemId != null
				) {
						java.io.Serializable pkey = outstandingClaimItemId;
						OutstandingClaimItem object = oustandingClaimItemService.get (pkey );

			 if (object != null){ // edit object

				tmp = new OutstandingClaimItemForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new OutstandingClaimItemForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new OutstandingClaimItemForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		OutstandingClaimItemForm oustandingClaimItemForm = ( OutstandingClaimItemForm ) command;
		OutstandingClaimItem oustandingClaimItem = oustandingClaimItemForm.getOustandingClaimItem();

//		errors.setNestedPath("oustandingClaimItem");
//		getValidator().validate(oustandingClaimItem, errors);
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

		OutstandingClaimItemForm oustandingClaimItemForm = ( OutstandingClaimItemForm ) command;

		OutstandingClaimItem res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}


			if (oustandingClaimItemForm.isNewOustandingClaimItem ()) {
				
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEOUTSTANDINGCLAIM");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEOUTSTANDINGCLAIM access");
					return errorResult;
					
				}
				
				res = oustandingClaimItemService.create (oustandingClaimItemForm.getOustandingClaimItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.oustandingclaimitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.oustandingclaimitem",null,"fail",Locale.getDefault());
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
				res = oustandingClaimItemService.update (oustandingClaimItemForm.getOustandingClaimItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.oustandingclaimitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.oustandingclaimitem",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (oustandingClaimItemForm.isNewOustandingClaimItem ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.oustandingclaimitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.oustandingclaimitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("oustandingclaimitem"+"?alert="+alertMsg));
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
