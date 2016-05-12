
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
 * RefundItem is a mapping of refund_item Table.
*/
public class RefundItemFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	RefundItemService refundItemService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			MemberImportService memberImportService;

	public void setMemberImportService(MemberImportService obj){
		this.memberImportService = obj;
	}

	public MemberImportService getMemberImportService(){
		return this.memberImportService;
	}
				RefundService refundService;

	public void setRefundService(RefundService obj){
		this.refundService = obj;
	}

	public RefundService getRefundService(){
		return this.refundService;
	}
				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
			
	// -- foreign affairs end


	public void setRefundItemService (RefundItemService object){
	    this.refundItemService = object;
	}
	public RefundItemService getRefundItemService (){
	    return this.refundItemService;
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

	
	public RefundItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		RefundItemForm tmp = null;
						Integer refundItemId = WebUtil.getParameterInteger (request,"refundItemId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								refundItemId != null
				) {
						java.io.Serializable pkey = refundItemId;
						RefundItem object = refundItemService.get (pkey );

			 if (object != null){ // edit object

				tmp = new RefundItemForm(object);
			 // foreign affairs
	
				tmp.setMemberImportId(""+
					object.getMemberImport().getId()
				);


	
				tmp.setRefundId(""+
					object.getRefundId().getRefundId()
				);


	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new RefundItemForm();
					 // foreign affairs
	
	
				Integer memberImportId = WebUtil.getParameterInteger (request,"memberImportId");
		
			if(memberImportId!=null){
				MemberImport forClass = new MemberImport();
				forClass.setId(memberImportId);
				tmp.setMemberImportId(""+memberImportId);

				MemberImport memberImport = this.memberImportService.get(memberImportId);
				tmp.getRefundItem().setMemberImport(memberImport);
			}


	
	
				Integer refundId = WebUtil.getParameterInteger (request,"refundId");
		
			if(refundId!=null){
				Refund forClass = new Refund();
				forClass.setRefundId(refundId);
				tmp.setRefundId(""+refundId);

				Refund refund = this.refundService.get(refundId);
				tmp.getRefundItem().setRefundId(refund);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getRefundItem().setMemberId(member);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new RefundItemForm();
					 // foreign affairs
		
	
				Integer memberImportId = WebUtil.getParameterInteger (request,"memberImportId");
		
			if(memberImportId!=null){
				MemberImport forClass = new MemberImport();
				forClass.setId(memberImportId);
				tmp.setMemberImportId(""+memberImportId);

				MemberImport memberImport = this.memberImportService.get(memberImportId);
				tmp.getRefundItem().setMemberImport(memberImport);
			}


	
	
				Integer refundId = WebUtil.getParameterInteger (request,"refundId");
		
			if(refundId!=null){
				Refund forClass = new Refund();
				forClass.setRefundId(refundId);
				tmp.setRefundId(""+refundId);

				Refund refund = this.refundService.get(refundId);
				tmp.getRefundItem().setRefundId(refund);
			}	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getRefundItem().setMemberId(member);
			}


		// -- foreign affairs end



		}
																																
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		RefundItemForm refundItemForm = ( RefundItemForm ) command;
		RefundItem refundItem = refundItemForm.getRefundItem();

//		errors.setNestedPath("refundItem");
//		getValidator().validate(refundItem, errors);
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

		RefundItemForm refundItemForm = ( RefundItemForm ) command;

		RefundItem res = null;
		String alertMsg="";
		try {
		
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			ActionUser user = securityService.getActionUser(request);
			
			if (refundItemForm.isNewRefundItem ()) {
				res = refundItemService.create (refundItemForm.getRefundItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.refunditem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.refunditem",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = refundItemService.update (refundItemForm.getRefundItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.refunditem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.refunditem",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (refundItemForm.isNewRefundItem ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.refunditem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.refunditem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("refunditem"+"?alert="+alertMsg));
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
