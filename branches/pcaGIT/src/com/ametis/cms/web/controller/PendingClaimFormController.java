
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

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.PendingClaim;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.PendingCategoryService;
import com.ametis.cms.service.PendingClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PendingClaimForm;



// imports+ 

// imports- 


/**
 * PendingClaim is a mapping of pending_claim Table.
*/
public class PendingClaimFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	PendingClaimService pendingClaimService ;
	ClaimService claimService;
	ResourceBundleMessageSource alertProperties ;
	PendingCategoryService pendingCategoryService;
	// foreign affairs
	
		
	// -- foreign affairs end
	SecurityService securityService;
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

	public void setPendingClaimService (PendingClaimService object){
	    this.pendingClaimService = object;
	}
	public PendingClaimService getPendingClaimService (){
	    return this.pendingClaimService;
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


	public PendingClaimFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PendingClaimForm tmp = null;
		Integer pendingClaimId = WebUtil.getParameterInteger (request,"pendingClaimId");
		Integer claimId = WebUtil.getParameterInteger(request,"claimId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (pendingClaimId != null) {
			java.io.Serializable pkey = pendingClaimId;
			PendingClaim object = pendingClaimService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PendingClaimForm(object);
			 // foreign affairs
		// -- foreign affairs end
				String breadcrumb = "<a href=\"claim?navigation=detail&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;" +
						"<a href=\"pendingclaim?navigation=detail&pendingClaimId="+pendingClaimId+"\" class=\"linkbreadcrumb\">Detail Pending Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Pending Claim";
				request.setAttribute("breadcrumb", breadcrumb);
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PendingClaimForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PendingClaimForm();
					 // foreign affairs
			// -- foreign affairs end
			String breadcrumb = "<a href=\"claim?navigation=detail&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Pending Claim";
			request.setAttribute("breadcrumb", breadcrumb);


		}
				
		if (claimId != null && tmp != null){
			Claim claim = claimService.get(claimId);
			
			if (claim != null){
				tmp.setClaimId(claim.getClaimId().toString());
				tmp.setClaimNumber(claim.getClaimNumber());
			}
			
		}
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		PendingClaimForm pendingClaimForm = ( PendingClaimForm ) command;
		PendingClaim pendingClaim = pendingClaimForm.getPendingClaim();
		
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
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		Collection pendingCategory = pendingCategoryService.getAll();
		model.put("pendingCategory", pendingCategory);
		model.put("claimId",claimId);
		
		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PendingClaimForm pendingClaimForm = ( PendingClaimForm ) command;
		String redirectURL = "pendingclaim";

		PendingClaim res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (pendingClaimForm.isNewPendingClaim ()) {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEPENDINGCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEPENDINGCLAIM access");
				return errorResult;
				
			}
				res = pendingClaimService.create (pendingClaimForm.getPendingClaim(),user);
					
				
				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.pendingclaim",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.pendingclaim",null,"fail",Locale.getDefault());
				}
				redirectURL = "claim?navigation=detail&claimId=" + pendingClaimForm.getClaimId()+"&alert="+alertMsg;
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEPENDINGCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEPENDINGCLAIM access");
				return errorResult;
				
			}
				res = pendingClaimService.update (pendingClaimForm.getPendingClaim(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.pendingclaim",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.pendingclaim",null,"fail",Locale.getDefault());
				}
				redirectURL = "claim?navigation=detail&claimId=" + pendingClaimForm.getClaimId()+"&alert="+alertMsg;

			}
		}catch (Exception ex) {
			if (pendingClaimForm.isNewPendingClaim ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.pendingclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.pendingclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(redirectURL));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Integer.class,true);
		binder.registerCustomEditor(Integer.class,num);
	}
// class+ 
	public PendingCategoryService getPendingCategoryService() {
		return pendingCategoryService;
	}
	public void setPendingCategoryService(
			PendingCategoryService pendingCategoryService) {
		this.pendingCategoryService = pendingCategoryService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

// class- 

}
