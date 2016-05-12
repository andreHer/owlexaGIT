
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
import com.ametis.cms.datamodel.AuthMember;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.AuthMemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.AuthMemberForm;



// imports+ 

// imports- 


/**
 * AuthMember is a mapping of auth_member Table.
*/
public class AuthMemberFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	AuthMemberService authMemberService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
		
	// -- foreign affairs end

	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public void setAuthMemberService (AuthMemberService object){
	    this.authMemberService = object;
	}
	public AuthMemberService getAuthMemberService (){
	    return this.authMemberService;
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


	public AuthMemberFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		AuthMemberForm tmp = null;
						Integer authMemberId = WebUtil.getParameterInteger (request,"authMemberId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								authMemberId != null
				) {
						java.io.Serializable pkey = authMemberId;
						AuthMember object = authMemberService.get (pkey );

			 if (object != null){ // edit object

				tmp = new AuthMemberForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new AuthMemberForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new AuthMemberForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		AuthMemberForm authMemberForm = ( AuthMemberForm ) command;
		AuthMember authMember = authMemberForm.getAuthMember();

//		errors.setNestedPath("authMember");
//		getValidator().validate(authMember, errors);
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

		AuthMemberForm authMemberForm = ( AuthMemberForm ) command;

		AuthMember res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "AUTHMEMBERFORM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for AUTHMEMBERFORM access");
				return errorResult;
				
			}

			if (authMemberForm.isNewAuthMember ()) {
				res = authMemberService.create (authMemberForm.getAuthMember(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.authmember",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.authmember",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = authMemberService.update (authMemberForm.getAuthMember(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.authmember",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.authmember",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (authMemberForm.isNewAuthMember ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.authmember",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.authmember",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("authmember"+"?alert="+alertMsg));
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
