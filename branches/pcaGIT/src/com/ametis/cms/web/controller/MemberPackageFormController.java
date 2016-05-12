
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
import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberPackage;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.InsurancePackageService;
import com.ametis.cms.service.MemberPackageService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.SubscriptionStatusService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberPackageForm;



// imports+ 

// imports- 


/**
 * MemberPackage is a mapping of member_package Table.
*/
public class MemberPackageFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	MemberPackageService memberPackageService ;
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
			SubscriptionStatusService subscriptionStatusService;

	public void setSubscriptionStatusService(SubscriptionStatusService obj){
		this.subscriptionStatusService = obj;
	}

	public SubscriptionStatusService getSubscriptionStatusService(){
		return this.subscriptionStatusService;
	}
				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
				InsurancePackageService insurancePackageService;

	public void setInsurancePackageService(InsurancePackageService obj){
		this.insurancePackageService = obj;
	}

	public InsurancePackageService getInsurancePackageService(){
		return this.insurancePackageService;
	}
			
	// -- foreign affairs end


	public void setMemberPackageService (MemberPackageService object){
	    this.memberPackageService = object;
	}
	public MemberPackageService getMemberPackageService (){
	    return this.memberPackageService;
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


	public MemberPackageFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MemberPackageForm tmp = null;
						Integer memberPackageId = WebUtil.getParameterInteger (request,"memberPackageId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								memberPackageId != null
				) {
						java.io.Serializable pkey = memberPackageId;
						MemberPackage object = memberPackageService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MemberPackageForm(object);
			 // foreign affairs
	
				tmp.setMemberPackageStatus(""+
					object.getMemberPackageStatus().getStatusId()
				);


	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


	
				tmp.setPackageId(""+
					object.getPackageId().getPackageId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberPackageForm();
					 // foreign affairs
	
	
				Integer memberPackageStatus = WebUtil.getParameterInteger (request,"memberPackageStatus");
		
			if(memberPackageStatus!=null){
				SubscriptionStatus forClass = new SubscriptionStatus();
				forClass.setStatusId(memberPackageStatus);
				tmp.setMemberPackageStatus(""+memberPackageStatus);

				SubscriptionStatus subscriptionStatus = this.subscriptionStatusService.get(memberPackageStatus);
				tmp.getMemberPackage().setMemberPackageStatus(subscriptionStatus);
			}else{
				SubscriptionStatus forClass = new SubscriptionStatus();
//				tmp.setMemberPackageStatus("");
				tmp.getMemberPackage().setMemberPackageStatus(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberPackage().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberPackage().setMemberId(forClass);
			}


	
	
				Integer packageId = WebUtil.getParameterInteger (request,"packageId");
		
			if(packageId!=null){
				InsurancePackage forClass = new InsurancePackage();
				forClass.setPackageId(packageId);
				tmp.setPackageId(""+packageId);

				InsurancePackage insurancePackage = this.insurancePackageService.get(packageId);
				tmp.getMemberPackage().setPackageId(insurancePackage);
			}else{
				InsurancePackage forClass = new InsurancePackage();
//				tmp.setPackageId("");
				tmp.getMemberPackage().setPackageId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberPackageForm();
					 // foreign affairs
		
	
				Integer memberPackageStatus = WebUtil.getParameterInteger (request,"memberPackageStatus");
		
			if(memberPackageStatus!=null){
				SubscriptionStatus forClass = new SubscriptionStatus();
				forClass.setStatusId(memberPackageStatus);
				tmp.setMemberPackageStatus(""+memberPackageStatus);

				SubscriptionStatus subscriptionStatus = this.subscriptionStatusService.get(memberPackageStatus);
				tmp.getMemberPackage().setMemberPackageStatus(subscriptionStatus);
			}else{
				SubscriptionStatus forClass = new SubscriptionStatus();
//				tmp.setMemberPackageStatus("");
				tmp.getMemberPackage().setMemberPackageStatus(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberPackage().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberPackage().setMemberId(forClass);
			}


	
	
				Integer packageId = WebUtil.getParameterInteger (request,"packageId");
		
			if(packageId!=null){
				InsurancePackage forClass = new InsurancePackage();
				forClass.setPackageId(packageId);
				tmp.setPackageId(""+packageId);

				InsurancePackage insurancePackage = this.insurancePackageService.get(packageId);
				tmp.getMemberPackage().setPackageId(insurancePackage);
			}else{
				InsurancePackage forClass = new InsurancePackage();
//				tmp.setPackageId("");
				tmp.getMemberPackage().setPackageId(forClass);
			}


		// -- foreign affairs end



		}
																																
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		MemberPackageForm memberPackageForm = ( MemberPackageForm ) command;
		MemberPackage memberPackage = memberPackageForm.getMemberPackage();

//		errors.setNestedPath("memberPackage");
//		getValidator().validate(memberPackage, errors);
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

		MemberPackageForm memberPackageForm = ( MemberPackageForm ) command;

		MemberPackage res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(memberPackageForm.getMemberPackageStatus() != null){
				memberPackageForm.getMemberPackage().setMemberPackageStatus(
					this.subscriptionStatusService.get(
						new Integer((memberPackageForm.getMemberPackageStatus()))
						)
				);
			}
	
					if(memberPackageForm.getMemberId() != null){
				memberPackageForm.getMemberPackage().setMemberId(
					this.memberService.get(
						new Integer((memberPackageForm.getMemberId()))
						)
				);
			}
	
					if(memberPackageForm.getPackageId() != null){
				memberPackageForm.getMemberPackage().setPackageId(
					this.insurancePackageService.get(
						new Integer((memberPackageForm.getPackageId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (memberPackageForm.isNewMemberPackage ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEMEMBERPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEMEMBERPACKAGE access");
				return errorResult;
				
			}
				res = memberPackageService.create (memberPackageForm.getMemberPackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.memberpackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.memberpackage",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEMEMBERPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEMEMBERPACKAGE access");
				return errorResult;
				
			}
				res = memberPackageService.update (memberPackageForm.getMemberPackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.memberpackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.memberpackage",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (memberPackageForm.isNewMemberPackage ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.memberpackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.memberpackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("memberpackage"+"?alert="+alertMsg));
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
