
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

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.service.ActionService;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.LogUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ActionForm;



// imports+ 

// imports- 


/**
 * Action is a mapping of action Table.
*/
public class ActionFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ActionService actionService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
		
	// -- foreign affairs end
	
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setActionService (ActionService object){
	    this.actionService = object;
	}
	public ActionService getActionService (){
	    return this.actionService;
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


	public ActionFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ActionForm tmp = null;
						Integer actionId = WebUtil.getParameterInteger (request,"actionId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								actionId != null
				) {
						java.io.Serializable pkey = actionId;
						Action object = actionService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ActionForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ActionForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ActionForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ActionForm actionForm = ( ActionForm ) command;
		Action action = actionForm.getAction();

//		errors.setNestedPath("action");
//		getValidator().validate(action, errors);
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

		ActionForm actionForm = ( ActionForm ) command;

		Action res = null;
		String alertMsg="";
		String navigation = WebUtil.getParameterString(request, "navigation","");
		
		ModelAndView result = null;
		
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			ActionUser user = securityService.getActionUser(request);
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEACTION");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEACTION access");
				return errorResult;
				
			}
			if (actionForm.isNewAction ()) {
				res = actionService.create (actionForm.getAction(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.action",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.action",null,"fail",Locale.getDefault());
				}
					
				if (navigation.equalsIgnoreCase("simpandantambah")){
					result = new ModelAndView(new RedirectView("action-form?navigation=tambah"));
					result.addObject("alert", alertMsg);
				}
				else {
					result = new ModelAndView(new RedirectView("action"));
					result.addObject("alert", alertMsg);
				}
				LogUtil.log(logService, user, "Create Action Data - " + alertMsg, res.toString());

			}
			else {
				res = actionService.update (actionForm.getAction(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.action",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.action",null,"fail",Locale.getDefault());
				}
				result = new ModelAndView(new RedirectView("action?navigation=detail&actionId="+res.getActionId()));
				result.addObject("alert", alertMsg);
				LogUtil.log(logService, user, "Update Action Data - " + alertMsg, res.toString());

			}
		}catch (Exception ex) {
			if (actionForm.isNewAction ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.action",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.action",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return result;
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
