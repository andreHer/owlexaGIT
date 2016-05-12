
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
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ActivityLogForm;



// imports+ 

// imports- 


/**
 * ActivityLog is a mapping of activity_log Table.
*/
public class ActivityLogFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ActivityLogService activityLogService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
		
	// -- foreign affairs end

	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public void setActivityLogService (ActivityLogService object){
	    this.activityLogService = object;
	}
	public ActivityLogService getActivityLogService (){
	    return this.activityLogService;
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


	public ActivityLogFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ActivityLogForm tmp = null;
						Integer activityLogId = WebUtil.getParameterInteger (request,"activityLogId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								activityLogId != null
				) {
						java.io.Serializable pkey = activityLogId;
						ActivityLog object = activityLogService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ActivityLogForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ActivityLogForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ActivityLogForm();
					 // foreign affairs
			// -- foreign affairs end



		}
														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ActivityLogForm activityLogForm = ( ActivityLogForm ) command;
		ActivityLog activityLog = activityLogForm.getActivityLog();

//		errors.setNestedPath("activityLog");
//		getValidator().validate(activityLog, errors);
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

		ActivityLogForm activityLogForm = ( ActivityLogForm ) command;

		ActivityLog res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
ActionUser user = securityService.getActionUser(request);
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEACTIVITYLOG");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEACTIVITYLOG access");
				return errorResult;
				
			}
			if (activityLogForm.isNewActivityLog ()) {
				res = activityLogService.create (activityLogForm.getActivityLog());

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.activitylog",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.activitylog",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = activityLogService.update (activityLogForm.getActivityLog(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.activitylog",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.activitylog",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (activityLogForm.isNewActivityLog ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.activitylog",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.activitylog",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("activitylog"+"?alert="+alertMsg));
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
