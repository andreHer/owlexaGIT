
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
import com.ametis.cms.datamodel.GroupProduct;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.GroupProductService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.GroupProductForm;



// imports+ 

// imports- 


/**
 * GroupProduct is a mapping of group_product Table.
*/
public class GroupProductFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	GroupProductService groupProductService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			MemberGroupService memberGroupService;
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
	public void setMemberGroupService(MemberGroupService obj){
		this.memberGroupService = obj;
	}

	public MemberGroupService getMemberGroupService(){
		return this.memberGroupService;
	}
				ProductService productService;

	public void setProductService(ProductService obj){
		this.productService = obj;
	}

	public ProductService getProductService(){
		return this.productService;
	}
						
	// -- foreign affairs end


	public void setGroupProductService (GroupProductService object){
	    this.groupProductService = object;
	}
	public GroupProductService getGroupProductService (){
	    return this.groupProductService;
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


	public GroupProductFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		GroupProductForm tmp = null;
						Integer groupProductId = WebUtil.getParameterInteger (request,"groupProductId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								groupProductId != null
				) {
						java.io.Serializable pkey = groupProductId;
						GroupProduct object = groupProductService.get (pkey );

			 if (object != null){ // edit object

				tmp = new GroupProductForm(object);
			 // foreign affairs
	

	
				tmp.setProductId(""+
					object.getProductId().getProductId()
				);


	
				tmp.setMemberGroupId(""+
					object.getMemberGroupId().getMemberGroupId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new GroupProductForm();
					 // foreign affairs
	
	
	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getGroupProduct().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getGroupProduct().setProductId(forClass);
			}


	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getGroupProduct().setMemberGroupId(memberGroup);
			}else{
				MemberGroup forClass = new MemberGroup();
//				tmp.setMemberGroupId("");
				tmp.getGroupProduct().setMemberGroupId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new GroupProductForm();
					 // foreign affairs
		
	


	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getGroupProduct().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getGroupProduct().setProductId(forClass);
			}


	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getGroupProduct().setMemberGroupId(memberGroup);
			}else{
				MemberGroup forClass = new MemberGroup();
//				tmp.setMemberGroupId("");
				tmp.getGroupProduct().setMemberGroupId(forClass);
			}


		// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		GroupProductForm groupProductForm = ( GroupProductForm ) command;
		GroupProduct groupProduct = groupProductForm.getGroupProduct();

//		errors.setNestedPath("groupProduct");
//		getValidator().validate(groupProduct, errors);
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

		GroupProductForm groupProductForm = ( GroupProductForm ) command;

		GroupProduct res = null;
		String alertMsg="";
		try {
	
					if(groupProductForm.getProductId() != null){
				groupProductForm.getGroupProduct().setProductId(
					this.productService.get(
						new Integer((groupProductForm.getProductId()))
						)
				);
			}
	
					if(groupProductForm.getMemberGroupId() != null){
				groupProductForm.getGroupProduct().setMemberGroupId(
					this.memberGroupService.get(
						new Integer((groupProductForm.getMemberGroupId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (groupProductForm.isNewGroupProduct ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEGROUPPRODUCT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEGROUPPRODUCT access");
				return errorResult;
				
			}
				res = groupProductService.create (groupProductForm.getGroupProduct(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.groupproduct",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.groupproduct",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEGROUPPRODUCT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEGROUPPRODUCT access");
				return errorResult;
				
			}
				res = groupProductService.update (groupProductForm.getGroupProduct(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.groupproduct",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.groupproduct",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (groupProductForm.isNewGroupProduct ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.groupproduct",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.groupproduct",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("groupproduct"+"?alert="+alertMsg));
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
