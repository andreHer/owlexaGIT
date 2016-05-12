
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
import com.ametis.cms.datamodel.GroupBenefit;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.GroupBenefitService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.GroupBenefitForm;



// imports+ 

// imports- 


/**
 * GroupBenefit is a mapping of group_benefit Table.
*/
public class GroupBenefitFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	GroupBenefitService groupBenefitService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
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
				ItemCategoryService itemCategoryService;

	public void setItemCategoryService(ItemCategoryService obj){
		this.itemCategoryService = obj;
	}

	public ItemCategoryService getItemCategoryService(){
		return this.itemCategoryService;
	}
			
	// -- foreign affairs end


	public void setGroupBenefitService (GroupBenefitService object){
	    this.groupBenefitService = object;
	}
	public GroupBenefitService getGroupBenefitService (){
	    return this.groupBenefitService;
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


	public GroupBenefitFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		GroupBenefitForm tmp = null;
						Integer groupBenefitId = WebUtil.getParameterInteger (request,"groupBenefitId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								groupBenefitId != null
				) {
						java.io.Serializable pkey = groupBenefitId;
						GroupBenefit object = groupBenefitService.get (pkey );

			 if (object != null){ // edit object

				tmp = new GroupBenefitForm(object);
			 // foreign affairs
	
				tmp.setMemberGroupId(""+
					object.getMemberGroupId().getMemberGroupId()
				);


	
				tmp.setItemCategoryId(""+
					object.getItemCategoryId().getItemCategoryId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new GroupBenefitForm();
					 // foreign affairs
	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getGroupBenefit().setMemberGroupId(memberGroup);
			}else{
				MemberGroup forClass = new MemberGroup();
//				tmp.setMemberGroupId("");
				tmp.getGroupBenefit().setMemberGroupId(forClass);
			}


	
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getGroupBenefit().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getGroupBenefit().setItemCategoryId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new GroupBenefitForm();
					 // foreign affairs
		
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getGroupBenefit().setMemberGroupId(memberGroup);
			}else{
				MemberGroup forClass = new MemberGroup();
//				tmp.setMemberGroupId("");
				tmp.getGroupBenefit().setMemberGroupId(forClass);
			}


	
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getGroupBenefit().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getGroupBenefit().setItemCategoryId(forClass);
			}


		// -- foreign affairs end



		}
																										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		GroupBenefitForm groupBenefitForm = ( GroupBenefitForm ) command;
		GroupBenefit groupBenefit = groupBenefitForm.getGroupBenefit();

//		errors.setNestedPath("groupBenefit");
//		getValidator().validate(groupBenefit, errors);
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

		GroupBenefitForm groupBenefitForm = ( GroupBenefitForm ) command;

		GroupBenefit res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(groupBenefitForm.getMemberGroupId() != null){
				groupBenefitForm.getGroupBenefit().setMemberGroupId(
					this.memberGroupService.get(
						new Integer((groupBenefitForm.getMemberGroupId()))
						)
				);
			}
	
					if(groupBenefitForm.getItemCategoryId() != null){
				groupBenefitForm.getGroupBenefit().setItemCategoryId(
					this.itemCategoryService.get(
						new Integer((groupBenefitForm.getItemCategoryId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (groupBenefitForm.isNewGroupBenefit ()) {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEGROUPBENEFIT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEGROUPBENEFIT access");
				return errorResult;
				
			}
				res = groupBenefitService.create (groupBenefitForm.getGroupBenefit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.groupbenefit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.groupbenefit",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEGROUPBENEFIT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEGROUPBENEFIT access");
				return errorResult;
				
			}
				res = groupBenefitService.update (groupBenefitForm.getGroupBenefit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.groupbenefit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.groupbenefit",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (groupBenefitForm.isNewGroupBenefit ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.groupbenefit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.groupbenefit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("groupbenefit"+"?alert="+alertMsg));
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
