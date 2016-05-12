
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
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.SubscriptionStatusService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberProductForm;



// imports+ 

// imports- 


/**
 * MemberProduct is a mapping of member_product Table.
*/
public class MemberProductFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	MemberProductService memberProductService ;
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
				ProductService productService;

	public void setProductService(ProductService obj){
		this.productService = obj;
	}

	public ProductService getProductService(){
		return this.productService;
	}
			
	// -- foreign affairs end


	public void setMemberProductService (MemberProductService object){
	    this.memberProductService = object;
	}
	public MemberProductService getMemberProductService (){
	    return this.memberProductService;
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


	public MemberProductFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MemberProductForm tmp = null;
						Integer memberProductId = WebUtil.getParameterInteger (request,"memberProductId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								memberProductId != null
				) {
						java.io.Serializable pkey = memberProductId;
						MemberProduct object = memberProductService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MemberProductForm(object);
			 // foreign affairs
	
				tmp.setMemberProductStatus(""+
					object.getMemberProductStatus().getStatusId()
				);


	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


	
				tmp.setProductId(""+
					object.getProductId().getProductId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberProductForm();
					 // foreign affairs
	
	
				Integer memberProductStatus = WebUtil.getParameterInteger (request,"memberProductStatus");
		
			if(memberProductStatus!=null){
				SubscriptionStatus forClass = new SubscriptionStatus();
				forClass.setStatusId(memberProductStatus);
				tmp.setMemberProductStatus(""+memberProductStatus);

				SubscriptionStatus subscriptionStatus = this.subscriptionStatusService.get(memberProductStatus);
				tmp.getMemberProduct().setMemberProductStatus(subscriptionStatus);
			}else{
				SubscriptionStatus forClass = new SubscriptionStatus();
//				tmp.setMemberProductStatus("");
				tmp.getMemberProduct().setMemberProductStatus(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberProduct().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberProduct().setMemberId(forClass);
			}


	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getMemberProduct().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getMemberProduct().setProductId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberProductForm();
					 // foreign affairs
		
	
				Integer memberProductStatus = WebUtil.getParameterInteger (request,"memberProductStatus");
		
			if(memberProductStatus!=null){
				SubscriptionStatus forClass = new SubscriptionStatus();
				forClass.setStatusId(memberProductStatus);
				tmp.setMemberProductStatus(""+memberProductStatus);

				SubscriptionStatus subscriptionStatus = this.subscriptionStatusService.get(memberProductStatus);
				tmp.getMemberProduct().setMemberProductStatus(subscriptionStatus);
			}else{
				SubscriptionStatus forClass = new SubscriptionStatus();
//				tmp.setMemberProductStatus("");
				tmp.getMemberProduct().setMemberProductStatus(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberProduct().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberProduct().setMemberId(forClass);
			}


	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getMemberProduct().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getMemberProduct().setProductId(forClass);
			}


		// -- foreign affairs end



		}
																																
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		MemberProductForm memberProductForm = ( MemberProductForm ) command;
		MemberProduct memberProduct = memberProductForm.getMemberProduct();

//		errors.setNestedPath("memberProduct");
//		getValidator().validate(memberProduct, errors);
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

		MemberProductForm memberProductForm = ( MemberProductForm ) command;

		MemberProduct res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(memberProductForm.getMemberProductStatus() != null){
				memberProductForm.getMemberProduct().setMemberProductStatus(
					this.subscriptionStatusService.get(
						new Integer((memberProductForm.getMemberProductStatus()))
						)
				);
			}
	
					if(memberProductForm.getMemberId() != null){
				memberProductForm.getMemberProduct().setMemberId(
					this.memberService.get(
						new Integer((memberProductForm.getMemberId()))
						)
				);
			}
	
					if(memberProductForm.getProductId() != null){
				memberProductForm.getMemberProduct().setProductId(
					this.productService.get(
						new Integer((memberProductForm.getProductId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (memberProductForm.isNewMemberProduct ()) {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEMEMBERPRODUCT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEMEMBERPRODUCT access");
				return errorResult;
				
			}
				res = memberProductService.create (memberProductForm.getMemberProduct(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.memberproduct",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.memberproduct",null,"fail",Locale.getDefault());
				}
			}
			else {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEMEMBERPRODUCT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEMEMBERPRODUCT access");
				return errorResult;
				
			}
				res = memberProductService.update (memberProductForm.getMemberProduct(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.memberproduct",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.memberproduct",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (memberProductForm.isNewMemberProduct ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.memberproduct",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.memberproduct",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("memberproduct"+"?alert="+alertMsg));
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
