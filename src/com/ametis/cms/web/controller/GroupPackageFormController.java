
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
import com.ametis.cms.datamodel.GroupPackage;
import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.GroupPackageService;
import com.ametis.cms.service.InsurancePackageService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.GroupPackageForm;



// imports+ 

// imports- 


/**
 * GroupPackage is a mapping of group_package Table.
*/
public class GroupPackageFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	GroupPackageService groupPackageService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			MemberGroupService memberGroupService;
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setMemberGroupService(MemberGroupService obj){
		this.memberGroupService = obj;
	}

	public MemberGroupService getMemberGroupService(){
		return this.memberGroupService;
	}
							InsurancePackageService insurancePackageService;

	public void setInsurancePackageService(InsurancePackageService obj){
		this.insurancePackageService = obj;
	}

	public InsurancePackageService getInsurancePackageService(){
		return this.insurancePackageService;
	}
			
	// -- foreign affairs end


	public void setGroupPackageService (GroupPackageService object){
	    this.groupPackageService = object;
	}
	public GroupPackageService getGroupPackageService (){
	    return this.groupPackageService;
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


	public GroupPackageFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		GroupPackageForm tmp = null;
						Integer groupPackageId = WebUtil.getParameterInteger (request,"groupPackageId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								groupPackageId != null
				) {
						java.io.Serializable pkey = groupPackageId;
						GroupPackage object = groupPackageService.get (pkey );

			 if (object != null){ // edit object

				tmp = new GroupPackageForm(object);
			 // foreign affairs
	

	
				tmp.setMemberGroupId(""+
					object.getMemberGroupId().getMemberGroupId()
				);


	
				tmp.setPackageId(""+
					object.getPackageId().getPackageId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new GroupPackageForm();
					 // foreign affairs
	
	



	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getGroupPackage().setMemberGroupId(memberGroup);
			}else{
				MemberGroup forClass = new MemberGroup();
//				tmp.setMemberGroupId("");
				tmp.getGroupPackage().setMemberGroupId(forClass);
			}


	
	
				Integer packageId = WebUtil.getParameterInteger (request,"packageId");
		
			if(packageId!=null){
				InsurancePackage forClass = new InsurancePackage();
				forClass.setPackageId(packageId);
				tmp.setPackageId(""+packageId);

				InsurancePackage insurancePackage = this.insurancePackageService.get(packageId);
				tmp.getGroupPackage().setPackageId(insurancePackage);
			}else{
				InsurancePackage forClass = new InsurancePackage();
//				tmp.setPackageId("");
				tmp.getGroupPackage().setPackageId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new GroupPackageForm();



	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getGroupPackage().setMemberGroupId(memberGroup);
			}else{
				MemberGroup forClass = new MemberGroup();
//				tmp.setMemberGroupId("");
				tmp.getGroupPackage().setMemberGroupId(forClass);
			}


	
	
				Integer packageId = WebUtil.getParameterInteger (request,"packageId");
		
			if(packageId!=null){
				InsurancePackage forClass = new InsurancePackage();
				forClass.setPackageId(packageId);
				tmp.setPackageId(""+packageId);

				InsurancePackage insurancePackage = this.insurancePackageService.get(packageId);
				tmp.getGroupPackage().setPackageId(insurancePackage);
			}else{
				InsurancePackage forClass = new InsurancePackage();
//				tmp.setPackageId("");
				tmp.getGroupPackage().setPackageId(forClass);
			}


		// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		GroupPackageForm groupPackageForm = ( GroupPackageForm ) command;
		GroupPackage groupPackage = groupPackageForm.getGroupPackage();

//		errors.setNestedPath("groupPackage");
//		getValidator().validate(groupPackage, errors);
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

		GroupPackageForm groupPackageForm = ( GroupPackageForm ) command;

		GroupPackage res = null;
		String alertMsg="";
		try {

	
					if(groupPackageForm.getMemberGroupId() != null){
				groupPackageForm.getGroupPackage().setMemberGroupId(
					this.memberGroupService.get(
						new Integer((groupPackageForm.getMemberGroupId()))
						)
				);
			}
	
					if(groupPackageForm.getPackageId() != null){
				groupPackageForm.getGroupPackage().setPackageId(
					this.insurancePackageService.get(
						new Integer((groupPackageForm.getPackageId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (groupPackageForm.isNewGroupPackage ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEGROUPPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEGROUPPACKAGE access");
				return errorResult;
				
			}
				res = groupPackageService.create (groupPackageForm.getGroupPackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.grouppackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.grouppackage",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEGROUPPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEGROUPPACKAGE access");
				return errorResult;
				
			}
				res = groupPackageService.update (groupPackageForm.getGroupPackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.grouppackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.grouppackage",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (groupPackageForm.isNewGroupPackage ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.grouppackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.grouppackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("grouppackage"+"?alert="+alertMsg));
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
