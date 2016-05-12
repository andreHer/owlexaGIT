
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
/*
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
*/
import java.util.Locale;
import java.util.Collection;
import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import com.ametis.cms.service.*;
import com.ametis.cms.util.*;



// imports+ 

// imports- 

/**
 * MemberLimitLayer is a mapping of member_limit_layer Table.
*/
public class MemberLimitLayerFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	MemberLimitLayerService memberLimitLayerService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ProductLayerLimitService productLayerLimitService;

	public void setProductLayerLimitService(ProductLayerLimitService obj){
		this.productLayerLimitService = obj;
	}

	public ProductLayerLimitService getProductLayerLimitService(){
		return this.productLayerLimitService;
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


	public void setMemberLimitLayerService (MemberLimitLayerService object){
	    this.memberLimitLayerService = object;
	}
	public MemberLimitLayerService getMemberLimitLayerService (){
	    return this.memberLimitLayerService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getUserService() {
		return actionuserService;
	}
	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}
	
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}
	
	public void setSecurityService (SecurityService object){
	    this.securityService = object;
	}
	public SecurityService getSecurityService (){
	    return this.securityService;
	}

	
	public MemberLimitLayerFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MemberLimitLayerForm tmp = null;
						Integer memberLimitLayerId = WebUtil.getParameterInteger (request,"memberLimitLayerId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								memberLimitLayerId != null
				) {
						java.io.Serializable pkey = memberLimitLayerId;
						MemberLimitLayer object = memberLimitLayerService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MemberLimitLayerForm(object);
			 // foreign affairs
	
				tmp.setProductLimitLayerId(""+
					object.getProductLimitLayerId().getProductLayerLimitId()
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
				tmp = new MemberLimitLayerForm();
					 // foreign affairs
	
	
				Integer productLimitLayerId = WebUtil.getParameterInteger (request,"productLimitLayerId");
		
			if(productLimitLayerId!=null){
				ProductLayerLimit forClass = new ProductLayerLimit();
				forClass.setProductLayerLimitId(productLimitLayerId);
				tmp.setProductLimitLayerId(""+productLimitLayerId);

				ProductLayerLimit productLayerLimit = this.productLayerLimitService.get(productLimitLayerId);
				tmp.getMemberLimitLayer().setProductLimitLayerId(productLayerLimit);
			}else{
				ProductLayerLimit forClass = new ProductLayerLimit();
//				tmp.setProductLimitLayerId("");
				tmp.getMemberLimitLayer().setProductLimitLayerId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberLimitLayer().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberLimitLayer().setMemberId(forClass);
			}


	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getMemberLimitLayer().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getMemberLimitLayer().setProductId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberLimitLayerForm();
					 // foreign affairs
		
	
				Integer productLimitLayerId = WebUtil.getParameterInteger (request,"productLimitLayerId");
		
			if(productLimitLayerId!=null){
				ProductLayerLimit forClass = new ProductLayerLimit();
				forClass.setProductLayerLimitId(productLimitLayerId);
				tmp.setProductLimitLayerId(""+productLimitLayerId);

				ProductLayerLimit productLayerLimit = this.productLayerLimitService.get(productLimitLayerId);
				tmp.getMemberLimitLayer().setProductLimitLayerId(productLayerLimit);
			}else{
				ProductLayerLimit forClass = new ProductLayerLimit();
//				tmp.setProductLimitLayerId("");
				tmp.getMemberLimitLayer().setProductLimitLayerId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberLimitLayer().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getMemberLimitLayer().setMemberId(forClass);
			}


	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getMemberLimitLayer().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getMemberLimitLayer().setProductId(forClass);
			}


		// -- foreign affairs end



		}
																																				
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		MemberLimitLayerForm memberLimitLayerForm = ( MemberLimitLayerForm ) command;
		MemberLimitLayer memberLimitLayer = memberLimitLayerForm.getMemberLimitLayer();

//		errors.setNestedPath("memberLimitLayer");
//		getValidator().validate(memberLimitLayer, errors);
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

		MemberLimitLayerForm memberLimitLayerForm = ( MemberLimitLayerForm ) command;

		MemberLimitLayer res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(memberLimitLayerForm.getProductLimitLayerId() != null){
				memberLimitLayerForm.getMemberLimitLayer().setProductLimitLayerId(
					this.productLayerLimitService.get(
						new Integer((memberLimitLayerForm.getProductLimitLayerId()))
						)
				);
			}
	
					if(memberLimitLayerForm.getMemberId() != null){
				memberLimitLayerForm.getMemberLimitLayer().setMemberId(
					this.memberService.get(
						new Integer((memberLimitLayerForm.getMemberId()))
						)
				);
			}
	
					if(memberLimitLayerForm.getProductId() != null){
				memberLimitLayerForm.getMemberLimitLayer().setProductId(
					this.productService.get(
						new Integer((memberLimitLayerForm.getProductId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			/*
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			ActionUser user = null;
			if (auth != null && auth.getPrincipal() instanceof UserDetails) {
				UserDetails principal = (UserDetails)auth.getPrincipal();
				String[] eqP = new String[]{"email"};
				String[] eqQ = new String[]{principal.getUsername()};

					Collection coll = 
				actionuserService.search(null,null,eqP,eqQ,-1,-1);
				if(coll==null||coll.size()<=0){
					user = new ActionUser();
				}else{
					ActionUser users[] = 
					new ActionUser[coll.size()];
					coll.toArray(users);
					user = users[0];
				}


			}
			*/
			
			ActionUser user = securityService.getActionUser(request);
			
			if (memberLimitLayerForm.isNewMemberLimitLayer ()) {
				res = memberLimitLayerService.create (memberLimitLayerForm.getMemberLimitLayer(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.memberlimitlayer",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.memberlimitlayer",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = memberLimitLayerService.update (memberLimitLayerForm.getMemberLimitLayer(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.memberlimitlayer",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.memberlimitlayer",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (memberLimitLayerForm.isNewMemberLimitLayer ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.memberlimitlayer",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.memberlimitlayer",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("memberlimitlayer"+"?alert="+alertMsg));
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
