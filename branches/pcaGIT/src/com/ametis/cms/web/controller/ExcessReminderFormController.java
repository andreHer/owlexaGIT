
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
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.ExcessReminder;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.ExcessReminderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ExcessReminderForm;

public class ExcessReminderFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ExcessReminderService excessReminderService ;
	ExcessChargeService excessChargeService;
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
	public void setExcessReminderService (ExcessReminderService object){
	    this.excessReminderService = object;
	}
	public ExcessReminderService getExcessReminderService (){
	    return this.excessReminderService;
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


	public ExcessReminderFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ExcessReminderForm tmp = null;
		Integer excessReminderId = WebUtil.getParameterInteger (request,"excessReminderId");
		Integer excessChargeId = WebUtil.getParameterInteger(request, "excessChargeId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (excessReminderId != null) {
			java.io.Serializable pkey = excessReminderId;
			ExcessReminder object = excessReminderService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ExcessReminderForm(object);
			
				String breadcrumb = "<a href=\"excesscharge?navigation=detail&excessChargeId="+excessChargeId+"\" class=\"linkbreadcrumb\">Detail Excess Charge</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Excess Reminder";
				request.setAttribute("breadcrumb", breadcrumb);
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ExcessReminderForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ExcessReminderForm();
			
			if (excessChargeId != null){
				ExcessCharge excessCharge = excessChargeService.get(excessChargeId);
				
				
				if (excessCharge != null){
					int counter = excessCharge.getReminderCounter().intValue() + 1;
					
					tmp.setReminderCounter(counter+"");
					tmp.setExcessChargeId(excessCharge.getExcessChargeId().toString());
					tmp.setExcessChargeNumber(excessCharge.getExcessChargeNumber());
					
				}
				String breadcrumb = "<a href=\"excesscharge?navigation=detail&excessChargeId="+excessChargeId+"\" class=\"linkbreadcrumb\">Detail Excess Charge</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Excess Reminder";
				request.setAttribute("breadcrumb", breadcrumb);
			}
					 // foreign affairs
			// -- foreign affairs end
		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ExcessReminderForm excessReminderForm = ( ExcessReminderForm ) command;
		ExcessReminder excessReminder = excessReminderForm.getExcessReminder();
		System.out.println ("ERROR : " + errors);
//		errors.setNestedPath("excessReminder");
//		getValidator().validate(excessReminder, errors);
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

		ExcessReminderForm excessReminderForm = ( ExcessReminderForm ) command;

		ExcessReminder res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");

			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (excessReminderForm.isNewExcessReminder ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEEXCESSREMINDER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEEXCESSREMINDER access");
				return errorResult;
				
			}
				res = excessReminderService.create (excessReminderForm.getExcessReminder(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.excessreminder",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.excessreminder",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEEXCESSREMINDER");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEEXCESSREMINDER access");
				return errorResult;
				
			}
				res = excessReminderService.update (excessReminderForm.getExcessReminder(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.excessreminder",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.excessreminder",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			ex.printStackTrace();
			if (excessReminderForm.isNewExcessReminder ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.excessreminder",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.excessreminder",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("excesscharge?navigation=detail&excessChargeId="+excessReminderForm.getExcessChargeId()+"&alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
		CustomNumberEditor numInt = new CustomNumberEditor (Integer.class,true);
		binder.registerCustomEditor(Integer.class,numInt);
		
	}
// class+ 
	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}
	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

// class- 
}
