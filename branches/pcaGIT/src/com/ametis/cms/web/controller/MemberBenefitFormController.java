
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
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberBenefitForm;



// imports+ 

// imports- 


/**
 * MemberBenefit is a mapping of member_benefit Table.
*/
public class MemberBenefitFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	MemberBenefitService memberBenefitService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			MemberService memberService;
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
				ItemCategoryService itemCategoryService;

	public void setItemCategoryService(ItemCategoryService obj){
		this.itemCategoryService = obj;
	}

	public ItemCategoryService getItemCategoryService(){
		return this.itemCategoryService;
	}
			
	// -- foreign affairs end


	public void setMemberBenefitService (MemberBenefitService object){
	    this.memberBenefitService = object;
	}
	public MemberBenefitService getMemberBenefitService (){
	    return this.memberBenefitService;
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


	public MemberBenefitFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MemberBenefitForm tmp = null;
						Integer memberBenefitId = WebUtil.getParameterInteger (request,"memberBenefitId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								memberBenefitId != null
				) {
						java.io.Serializable pkey = memberBenefitId;
						MemberBenefit object = memberBenefitService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MemberBenefitForm(object);
			 // foreign affairs
	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


	
				tmp.setItemCategoryId(""+
					object.getItemCategoryId().getItemCategoryId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberBenefitForm();
					 // foreign affairs
	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberBenefit().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberBenefit().setMemberId(forClass);
			}


	
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getMemberBenefit().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getMemberBenefit().setItemCategoryId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberBenefitForm();
					 // foreign affairs
		
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberBenefit().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberBenefit().setMemberId(forClass);
			}


	
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getMemberBenefit().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getMemberBenefit().setItemCategoryId(forClass);
			}


		// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		MemberBenefitForm memberBenefitForm = ( MemberBenefitForm ) command;
		MemberBenefit memberBenefit = memberBenefitForm.getMemberBenefit();

//		errors.setNestedPath("memberBenefit");
//		getValidator().validate(memberBenefit, errors);
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

		MemberBenefitForm memberBenefitForm = ( MemberBenefitForm ) command;

		MemberBenefit res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(memberBenefitForm.getMemberId() != null){
				memberBenefitForm.getMemberBenefit().setMemberId(
					this.memberService.get(
						new Integer((memberBenefitForm.getMemberId()))
						)
				);
			}
	
					if(memberBenefitForm.getItemCategoryId() != null){
				memberBenefitForm.getMemberBenefit().setItemCategoryId(
					this.itemCategoryService.get(
						new Integer((memberBenefitForm.getItemCategoryId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (memberBenefitForm.isNewMemberBenefit ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEMEMBERBENEFIT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEMEMBERBENEFIT access");
				return errorResult;
				
			}
				res = memberBenefitService.create (memberBenefitForm.getMemberBenefit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.memberbenefit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.memberbenefit",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEMEMBERBENEFIT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEMEMBERBENEFIT access");
				return errorResult;
				
			}
				res = memberBenefitService.update (memberBenefitForm.getMemberBenefit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.memberbenefit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.memberbenefit",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (memberBenefitForm.isNewMemberBenefit ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.memberbenefit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.memberbenefit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("memberbenefit"+"?alert="+alertMsg));
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
