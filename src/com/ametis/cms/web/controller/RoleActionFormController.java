
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
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.RoleAction;
import com.ametis.cms.service.ActionService;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.RoleActionService;
import com.ametis.cms.service.RoleService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.RoleActionForm;



// imports+ 

// imports- 


/**
 * RoleAction is a mapping of role_action Table.
*/
public class RoleActionFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	RoleActionService roleActionService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			ActionService actionService;
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
	public void setActionService(ActionService obj){
		this.actionService = obj;
	}

	public ActionService getActionService(){
		return this.actionService;
	}
				RoleService roleService;

	public void setRoleService(RoleService obj){
		this.roleService = obj;
	}

	public RoleService getRoleService(){
		return this.roleService;
	}
			
	// -- foreign affairs end


	public void setRoleActionService (RoleActionService object){
	    this.roleActionService = object;
	}
	public RoleActionService getRoleActionService (){
	    return this.roleActionService;
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


	public RoleActionFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		RoleActionForm tmp = null;
						Integer roleActionId = WebUtil.getParameterInteger (request,"roleActionId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								roleActionId != null
				) {
						java.io.Serializable pkey = roleActionId;
						RoleAction object = roleActionService.get (pkey );

			 if (object != null){ // edit object

				tmp = new RoleActionForm(object);
			 // foreign affairs
	
				tmp.setActionId(""+
					object.getActionId().getActionId()
				);


	
				tmp.setRoleId(""+
					object.getRoleId().getRoleId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new RoleActionForm();
					 // foreign affairs
	
	
				Integer actionId = WebUtil.getParameterInteger (request,"actionId");
		
			if(actionId!=null){
				Action forClass = new Action();
				forClass.setActionId(actionId);
				tmp.setActionId(""+actionId);

				Action action = this.actionService.get(actionId);
				tmp.getRoleAction().setActionId(action);
			}else{
				Action forClass = new Action();
//				tmp.setActionId("");
				tmp.getRoleAction().setActionId(forClass);
			}


	
	
				Integer roleId = WebUtil.getParameterInteger (request,"roleId");
		
			if(roleId!=null){
				Role forClass = new Role();
				forClass.setRoleId(roleId);
				tmp.setRoleId(""+roleId);

				Role role = this.roleService.get(roleId);
				tmp.getRoleAction().setRoleId(role);
			}else{
				Role forClass = new Role();
//				tmp.setRoleId("");
				tmp.getRoleAction().setRoleId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new RoleActionForm();
					 // foreign affairs
		
	
				Integer actionId = WebUtil.getParameterInteger (request,"actionId");
		
			if(actionId!=null){
				Action forClass = new Action();
				forClass.setActionId(actionId);
				tmp.setActionId(""+actionId);

				Action action = this.actionService.get(actionId);
				tmp.getRoleAction().setActionId(action);
			}else{
				Action forClass = new Action();
//				tmp.setActionId("");
				tmp.getRoleAction().setActionId(forClass);
			}


	
	
				Integer roleId = WebUtil.getParameterInteger (request,"roleId");
		
			if(roleId!=null){
				Role forClass = new Role();
				forClass.setRoleId(roleId);
				tmp.setRoleId(""+roleId);

				Role role = this.roleService.get(roleId);
				tmp.getRoleAction().setRoleId(role);
			}else{
				Role forClass = new Role();
//				tmp.setRoleId("");
				tmp.getRoleAction().setRoleId(forClass);
			}


		// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		RoleActionForm roleActionForm = ( RoleActionForm ) command;
		RoleAction roleAction = roleActionForm.getRoleAction();

//		errors.setNestedPath("roleAction");
//		getValidator().validate(roleAction, errors);
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

		RoleActionForm roleActionForm = ( RoleActionForm ) command;

		RoleAction res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(roleActionForm.getActionId() != null){
				roleActionForm.getRoleAction().setActionId(
					this.actionService.get(
						new Integer((roleActionForm.getActionId()))
						)
				);
			}
	
					if(roleActionForm.getRoleId() != null){
				roleActionForm.getRoleAction().setRoleId(
					this.roleService.get(
						new Integer((roleActionForm.getRoleId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (roleActionForm.isNewRoleAction ()) {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEROLEACTION");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEROLEACTION access");
				return errorResult;
				
			}
				res = roleActionService.create (roleActionForm.getRoleAction(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.roleaction",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.roleaction",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEROLEACTION");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEROLEACTION access");
				return errorResult;
				
			}
				res = roleActionService.update (roleActionForm.getRoleAction(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.roleaction",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.roleaction",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (roleActionForm.isNewRoleAction ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.roleaction",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.roleaction",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("roleaction"+"?alert="+alertMsg));
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
