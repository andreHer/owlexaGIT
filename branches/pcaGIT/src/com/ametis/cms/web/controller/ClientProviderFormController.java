
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
import com.ametis.cms.datamodel.ClientProvider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ClientProviderForm;



// imports+ 

// imports- 


/**
 * ClientProvider is a mapping of client_provider Table.
*/
public class ClientProviderFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ClientProviderService clientProviderService ;
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
	public void setClientProviderService (ClientProviderService object){
	    this.clientProviderService = object;
	}
	public ClientProviderService getClientProviderService (){
	    return this.clientProviderService;
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


	public ClientProviderFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ClientProviderForm tmp = null;
						Integer clientProviderId = WebUtil.getParameterInteger (request,"clientProviderId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								clientProviderId != null
				) {
						java.io.Serializable pkey = clientProviderId;
						ClientProvider object = clientProviderService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ClientProviderForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClientProviderForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClientProviderForm();
					 // foreign affairs
			// -- foreign affairs end



		}
		
		
		String breadcrumb = "";
		
		breadcrumb = "<a href=\"clientprovider-upload\" class=\"linkbreadcrumb\">Register Upload Client Provider</a>";
		request.setAttribute("breadcrumb", breadcrumb);
																						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ClientProviderForm clientProviderForm = ( ClientProviderForm ) command;
		ClientProvider clientProvider = clientProviderForm.getClientProvider();

//		errors.setNestedPath("clientProvider");
//		getValidator().validate(clientProvider, errors);
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

		ClientProviderForm clientProviderForm = ( ClientProviderForm ) command;

		ClientProvider res = null;
		String alertMsg="";
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			ActionUser user = securityService.getActionUser(request);

			if (clientProviderForm.isNewClientProvider ()) {
				if (navigation.equalsIgnoreCase("upload")){
					
					res = clientProviderService.createUpload (clientProviderForm.getClientProvider(),clientProviderForm.getMultipartFile(),user);
	
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.clientprovider",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.clientprovider",null,"fail",Locale.getDefault());
					}
				}
				else {
					
					boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATECLIENTPROVIDER");
					
					if (!isUserAuthorized){
						
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
						errorResult.addObject("errorMessage", "You Are Not Authorized for CREATECLIENTPROVIDER access");
						return errorResult;
						
					}
					res = clientProviderService.create (clientProviderForm.getClientProvider(),user);
	
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.clientprovider",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.clientprovider",null,"fail",Locale.getDefault());
					}
				}
			}
			else {
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATECLIENTPROVIDER");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATECLIENTPROVIDER access");
					return errorResult;
					
				}
				res = clientProviderService.update (clientProviderForm.getClientProvider(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.clientprovider",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.clientprovider",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (clientProviderForm.isNewClientProvider ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.clientprovider",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.clientprovider",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("clientprovider"+"?alert="+alertMsg));
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
