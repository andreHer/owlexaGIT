
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
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyMember;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyMemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyMemberForm;



// imports+ 

// imports- 

/**
 * PolicyMember is a mapping of policy_member Table.
*/
public class PolicyMemberFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	PolicyMemberService policyMemberService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	SecurityService securityService;
	PolicyService policyService;

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

	public void setPolicyService(PolicyService obj){
		this.policyService = obj;
	}

	public PolicyService getPolicyService(){
		return this.policyService;
	}
				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
			
	// -- foreign affairs end


	public void setPolicyMemberService (PolicyMemberService object){
	    this.policyMemberService = object;
	}
	public PolicyMemberService getPolicyMemberService (){
	    return this.policyMemberService;
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


	public PolicyMemberFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyMemberForm tmp = null;
						Long policyMemberId = WebUtil.getParameterLong (request, "policyMemberId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								policyMemberId != null
				) {
						java.io.Serializable pkey = policyMemberId;
						PolicyMember object = policyMemberService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PolicyMemberForm(object);
			 // foreign affairs
	
				tmp.setPolicyId(""+
					object.getPolicyId().getPolicyId()
				);


	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyMemberForm();
					 // foreign affairs
	
	
				Integer policyId = WebUtil.getParameterInteger (request, "policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyMember().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getPolicyMember().setPolicyId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getPolicyMember().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getPolicyMember().setMemberId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyMemberForm();
					 // foreign affairs
		
	
				Integer policyId = WebUtil.getParameterInteger (request, "policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyMember().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getPolicyMember().setPolicyId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getPolicyMember().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getPolicyMember().setMemberId(forClass);
			}


		// -- foreign affairs end



		}
																		
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		PolicyMemberForm policyMemberForm = ( PolicyMemberForm ) command;
		PolicyMember policyMember = policyMemberForm.getPolicyMember();

//		errors.setNestedPath("policyMember");
//		getValidator().validate(policyMember, errors);
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

		PolicyMemberForm policyMemberForm = ( PolicyMemberForm ) command;

		PolicyMember res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(policyMemberForm.getPolicyId() != null){
				policyMemberForm.getPolicyMember().setPolicyId(
					this.policyService.get(
						new Long((policyMemberForm.getPolicyId()))
						)
				);
			}
	
					if(policyMemberForm.getMemberId() != null){
				policyMemberForm.getPolicyMember().setMemberId(
					this.memberService.get(
						new Integer((policyMemberForm.getMemberId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}

			ActionUser user  = securityService.getActionUser(request);
			
			if (policyMemberForm.isNewPolicyMember ()) {
				res = policyMemberService.create (policyMemberForm.getPolicyMember(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.policymember",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policymember",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyMemberService.update (policyMemberForm.getPolicyMember(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policymember",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policymember",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (policyMemberForm.isNewPolicyMember ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policymember",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policymember",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("policymember"+"?alert="+alertMsg));
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
