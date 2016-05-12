
package com.ametis.cms.web.controller;

import java.text.NumberFormat;
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
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.OutstandingService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.OutstandingForm;



// imports+ 

// imports- 


/**
 * Outstanding is a mapping of outstanding Table.
*/
public class OutstandingFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	OutstandingService outstandingService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			ClientService clientService;
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setClientService(ClientService obj){
		this.clientService = obj;
	}

	public ClientService getClientService(){
		return this.clientService;
	}
				BatchClaimService batchClaimService;

	public void setBatchClaimService(BatchClaimService obj){
		this.batchClaimService = obj;
	}

	public BatchClaimService getBatchClaimService(){
		return this.batchClaimService;
	}
			
	// -- foreign affairs end


	public void setOutstandingService (OutstandingService object){
	    this.outstandingService = object;
	}
	public OutstandingService getOutstandingService (){
	    return this.outstandingService;
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


	public OutstandingFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		OutstandingForm tmp = null;
						Integer outstandingId = WebUtil.getParameterInteger (request,"outstandingId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								outstandingId != null
				) {
						java.io.Serializable pkey = outstandingId;
						Outstanding object = outstandingService.get (pkey );

			 if (object != null){ // edit object

				tmp = new OutstandingForm(object);
			 // foreign affairs
	
				tmp.setClientId(""+
					object.getClientId().getClientId()
				);


	
				tmp.setBatchClaimId(""+
					object.getBatchClaimId().getBatchClaimId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new OutstandingForm();
					 // foreign affairs
	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getOutstanding().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getOutstanding().setClientId(forClass);
			}


	
	
				Integer batchClaimId = WebUtil.getParameterInteger (request,"batchClaimId");
		
			if(batchClaimId!=null){
				BatchClaim forClass = new BatchClaim();
				forClass.setBatchClaimId(batchClaimId);
				tmp.setBatchClaimId(""+batchClaimId);

				BatchClaim batchClaim = this.batchClaimService.get(batchClaimId);
				tmp.getOutstanding().setBatchClaimId(batchClaim);
			}else{
				BatchClaim forClass = new BatchClaim();
//				tmp.setBatchClaimId("");
				tmp.getOutstanding().setBatchClaimId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new OutstandingForm();
					 // foreign affairs
		
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getOutstanding().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getOutstanding().setClientId(forClass);
			}


	
	
				Integer batchClaimId = WebUtil.getParameterInteger (request,"batchClaimId");
		
			if(batchClaimId!=null){
				BatchClaim forClass = new BatchClaim();
				forClass.setBatchClaimId(batchClaimId);
				tmp.setBatchClaimId(""+batchClaimId);

				BatchClaim batchClaim = this.batchClaimService.get(batchClaimId);
				tmp.getOutstanding().setBatchClaimId(batchClaim);
			}else{
				BatchClaim forClass = new BatchClaim();
//				tmp.setBatchClaimId("");
				tmp.getOutstanding().setBatchClaimId(forClass);
			}


		// -- foreign affairs end



		}
																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		OutstandingForm outstandingForm = ( OutstandingForm ) command;
		Outstanding outstanding = outstandingForm.getOutstanding();

//		errors.setNestedPath("outstanding");
//		getValidator().validate(outstanding, errors);
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

		OutstandingForm outstandingForm = ( OutstandingForm ) command;

		Outstanding res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(outstandingForm.getClientId() != null){
				outstandingForm.getOutstanding().setClientId(
					this.clientService.get(
						new Integer((outstandingForm.getClientId()))
						)
				);
			}
	
					if(outstandingForm.getBatchClaimId() != null){
				outstandingForm.getOutstanding().setBatchClaimId(
					this.batchClaimService.get(
						new Integer((outstandingForm.getBatchClaimId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (outstandingForm.isNewOutstanding ()) {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEOUTSTANDING");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEOUTSTANDING access");
				return errorResult;
				
			}
				res = outstandingService.create (outstandingForm.getOutstanding(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.outstanding",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.outstanding",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEOUTSTANDING");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEOUTSTANDING access");
				return errorResult;
				
			}
				res = outstandingService.update (outstandingForm.getOutstanding(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.outstanding",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.outstanding",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (outstandingForm.isNewOutstanding ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.outstanding",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.outstanding",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("outstanding"+"?alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
		
		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
        binder.registerCustomEditor(java.lang.Double.class,
                new CustomNumberEditor (java.lang.Double.class, nf, true));
	}
// class+ 

// class- 

}
