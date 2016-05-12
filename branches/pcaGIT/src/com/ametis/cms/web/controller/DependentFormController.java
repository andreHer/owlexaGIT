
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
import com.ametis.cms.datamodel.Dependent;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Relationship;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.DependentService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.RelationshipService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.DependentForm;



// imports+ 

// imports- 

/**
 * Dependent is a mapping of dependent Table.
*/
public class DependentFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	DependentService dependentService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
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

				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
				RelationshipService relationshipService;

	public void setRelationshipService(RelationshipService obj){
		this.relationshipService = obj;
	}

	public RelationshipService getRelationshipService(){
		return this.relationshipService;
	}
			
	// -- foreign affairs end


	public void setDependentService (DependentService object){
	    this.dependentService = object;
	}
	public DependentService getDependentService (){
	    return this.dependentService;
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


	public DependentFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		DependentForm tmp = null;
						Integer dependentId = WebUtil.getParameterInteger (request,"dependentId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								dependentId != null
				) {
						java.io.Serializable pkey = dependentId;
						Dependent object = dependentService.get (pkey );

			 if (object != null){ // edit object

				tmp = new DependentForm(object);
			 // foreign affairs
	
				tmp.setStatus(""+
					object.getStatus().getStatusId()
				);


	
				tmp.setMemberId(object.getMemberId());


	
				tmp.setRelationshipId(""+
					object.getRelationshipId().getRelationshipId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new DependentForm();
					 // foreign affairs
	
	
				Integer status = WebUtil.getParameterInteger (request,"status");
		
			if(status!=null){
				SubscriptionStatus forClass = new SubscriptionStatus(status);
				
				tmp.setStatus(""+status);

				
				tmp.getDependent().setStatus(forClass);
			}else{
				SubscriptionStatus forClass = new SubscriptionStatus();
//				tmp.setStatus("");
				tmp.getDependent().setStatus(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(forClass);

				Member member = this.memberService.get(memberId);
				tmp.getDependent().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getDependent().setMemberId(forClass);
			}


	
	
				Integer relationshipId = WebUtil.getParameterInteger (request,"relationshipId");
		
			if(relationshipId!=null){
				Relationship forClass = new Relationship();
				forClass.setRelationshipId(relationshipId);
				tmp.setRelationshipId(""+relationshipId);

				Relationship relationship = this.relationshipService.get(relationshipId);
				tmp.getDependent().setRelationshipId(relationship);
			}else{
				Relationship forClass = new Relationship();
//				tmp.setRelationshipId("");
				tmp.getDependent().setRelationshipId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new DependentForm();
					 // foreign affairs
		
	
				Integer status = WebUtil.getParameterInteger (request,"status");
		
			if(status!=null){
				SubscriptionStatus forClass = new SubscriptionStatus();
				forClass.setStatusId(status);
				tmp.setStatus(""+status);

				
				tmp.getDependent().setStatus(forClass);
			}else{
				SubscriptionStatus forClass = new SubscriptionStatus();
//				tmp.setStatus("");
				tmp.getDependent().setStatus(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(forClass);

				Member member = this.memberService.get(memberId);
				tmp.getDependent().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getDependent().setMemberId(forClass);
			}


	
	
				Integer relationshipId = WebUtil.getParameterInteger (request,"relationshipId");
		
			if(relationshipId!=null){
				Relationship forClass = new Relationship();
				forClass.setRelationshipId(relationshipId);
				tmp.setRelationshipId(""+relationshipId);

				Relationship relationship = this.relationshipService.get(relationshipId);
				tmp.getDependent().setRelationshipId(relationship);
			}else{
				Relationship forClass = new Relationship();
//				tmp.setRelationshipId("");
				tmp.getDependent().setRelationshipId(forClass);
			}


		// -- foreign affairs end



		}
																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		DependentForm dependentForm = ( DependentForm ) command;
		Dependent dependent = dependentForm.getDependent();

//		errors.setNestedPath("dependent");
//		getValidator().validate(dependent, errors);
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
		Collection relationship = relationshipService.getAll();
		model.put("relationships",relationship);
		
		
		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		DependentForm dependentForm = ( DependentForm ) command;

		Dependent res = null;
		String alertMsg="";
		try {
		// foreign affairs
			if(dependentForm.getStatus() != null){
				dependentForm.getDependent().setStatus(	new SubscriptionStatus(Integer.valueOf(dependentForm.getStatus())));
			}
	
	
	
					if(dependentForm.getRelationshipId() != null){
				dependentForm.getDependent().setRelationshipId(
					this.relationshipService.get(
						new Integer((dependentForm.getRelationshipId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (dependentForm.isNewDependent ()) {
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEDEPENDENT");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEDEPENDENT access");
					return errorResult;
					
				}
				res = dependentService.create (dependentForm.getDependent(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.dependent",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.dependent",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEDEPENDENT");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEDEPENDENT access");
					return errorResult;
					
				}
				res = dependentService.update (dependentForm.getDependent(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.dependent",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.dependent",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (dependentForm.isNewDependent ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.dependent",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.dependent",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("dependent"+"?alert="+alertMsg));
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
