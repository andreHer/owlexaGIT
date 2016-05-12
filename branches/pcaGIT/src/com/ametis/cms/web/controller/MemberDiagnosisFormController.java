
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
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberDiagnosis;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.MemberDiagnosisService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberDiagnosisForm;



// imports+ 

// imports- 


/**
 * MemberDiagnosis is a mapping of member_diagnosis Table.
*/
public class MemberDiagnosisFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	MemberDiagnosisService memberDiagnosisService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			DiagnosisService diagnosisService;
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setDiagnosisService(DiagnosisService obj){
		this.diagnosisService = obj;
	}

	public DiagnosisService getDiagnosisService(){
		return this.diagnosisService;
	}
				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
			
	// -- foreign affairs end


	public void setMemberDiagnosisService (MemberDiagnosisService object){
	    this.memberDiagnosisService = object;
	}
	public MemberDiagnosisService getMemberDiagnosisService (){
	    return this.memberDiagnosisService;
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


	public MemberDiagnosisFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MemberDiagnosisForm tmp = null;
						Integer memberDiagnosisId = WebUtil.getParameterInteger (request,"memberDiagnosisId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								memberDiagnosisId != null
				) {
						java.io.Serializable pkey = memberDiagnosisId;
						MemberDiagnosis object = memberDiagnosisService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MemberDiagnosisForm(object);
			 // foreign affairs
	
				tmp.setDiagnosisId(""+
					object.getDiagnosisId().getDiagnosisId()
				);


	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberDiagnosisForm();
					 // foreign affairs
	
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getMemberDiagnosis().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getMemberDiagnosis().setDiagnosisId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberDiagnosis().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberDiagnosis().setMemberId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberDiagnosisForm();
					 // foreign affairs
		
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getMemberDiagnosis().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getMemberDiagnosis().setDiagnosisId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberDiagnosis().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberDiagnosis().setMemberId(forClass);
			}


		// -- foreign affairs end



		}
																						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		MemberDiagnosisForm memberDiagnosisForm = ( MemberDiagnosisForm ) command;
		MemberDiagnosis memberDiagnosis = memberDiagnosisForm.getMemberDiagnosis();

//		errors.setNestedPath("memberDiagnosis");
//		getValidator().validate(memberDiagnosis, errors);
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

		MemberDiagnosisForm memberDiagnosisForm = ( MemberDiagnosisForm ) command;

		MemberDiagnosis res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(memberDiagnosisForm.getDiagnosisId() != null){
				memberDiagnosisForm.getMemberDiagnosis().setDiagnosisId(
					this.diagnosisService.get(
						new Integer((memberDiagnosisForm.getDiagnosisId()))
						)
				);
			}
	
					if(memberDiagnosisForm.getMemberId() != null){
				memberDiagnosisForm.getMemberDiagnosis().setMemberId(
					this.memberService.get(
						new Integer((memberDiagnosisForm.getMemberId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (memberDiagnosisForm.isNewMemberDiagnosis ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEMEMBERDIAGNOSIS");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEMEMBERDIAGNOSIS access");
				return errorResult;
				
			}
				res = memberDiagnosisService.create (memberDiagnosisForm.getMemberDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.memberdiagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.memberdiagnosis",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEMEMBERDIAGNOSIS");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEMEMBERDIAGNOSIS access");
				return errorResult;
				
			}
				res = memberDiagnosisService.update (memberDiagnosisForm.getMemberDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.memberdiagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.memberdiagnosis",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (memberDiagnosisForm.isNewMemberDiagnosis ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.memberdiagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.memberdiagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("memberdiagnosis"+"?alert="+alertMsg));
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
