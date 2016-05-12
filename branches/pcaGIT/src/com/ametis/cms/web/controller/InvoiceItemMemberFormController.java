
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
 * InvoiceItemMember is a mapping of invoice_item_member Table.
*/
public class InvoiceItemMemberFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	InvoiceItemMemberService invoiceItemMemberService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			InvoiceItemService invoiceItemService;

	public void setInvoiceItemService(InvoiceItemService obj){
		this.invoiceItemService = obj;
	}

	public InvoiceItemService getInvoiceItemService(){
		return this.invoiceItemService;
	}
				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
			
	// -- foreign affairs end


	public void setInvoiceItemMemberService (InvoiceItemMemberService object){
	    this.invoiceItemMemberService = object;
	}
	public InvoiceItemMemberService getInvoiceItemMemberService (){
	    return this.invoiceItemMemberService;
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

	
	public InvoiceItemMemberFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		InvoiceItemMemberForm tmp = null;
						Integer invoiceItemMemberId = WebUtil.getParameterInteger (request,"invoiceItemMemberId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								invoiceItemMemberId != null
				) {
						java.io.Serializable pkey = invoiceItemMemberId;
						InvoiceItemMember object = invoiceItemMemberService.get (pkey );

			 if (object != null){ // edit object

				tmp = new InvoiceItemMemberForm(object);
			 // foreign affairs
	
				tmp.setInvoiceItemId(""+
					object.getInvoiceItemId().getInvoiceItemId()
				);


	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new InvoiceItemMemberForm();
					 // foreign affairs
	
	
				Integer invoiceItemId = WebUtil.getParameterInteger (request,"invoiceItemId");
		
			if(invoiceItemId!=null){
				InvoiceItem forClass = new InvoiceItem();
				forClass.setInvoiceItemId(invoiceItemId);
				tmp.setInvoiceItemId(""+invoiceItemId);

				InvoiceItem invoiceItem = this.invoiceItemService.get(invoiceItemId);
				tmp.getInvoiceItemMember().setInvoiceItemId(invoiceItem);
			}else{
				InvoiceItem forClass = new InvoiceItem();
//				tmp.setInvoiceItemId("");
				tmp.getInvoiceItemMember().setInvoiceItemId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getInvoiceItemMember().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getInvoiceItemMember().setMemberId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new InvoiceItemMemberForm();
					 // foreign affairs
		
	
				Integer invoiceItemId = WebUtil.getParameterInteger (request,"invoiceItemId");
		
			if(invoiceItemId!=null){
				InvoiceItem forClass = new InvoiceItem();
				forClass.setInvoiceItemId(invoiceItemId);
				tmp.setInvoiceItemId(""+invoiceItemId);

				InvoiceItem invoiceItem = this.invoiceItemService.get(invoiceItemId);
				tmp.getInvoiceItemMember().setInvoiceItemId(invoiceItem);
			}else{
				InvoiceItem forClass = new InvoiceItem();
//				tmp.setInvoiceItemId("");
				tmp.getInvoiceItemMember().setInvoiceItemId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getInvoiceItemMember().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getInvoiceItemMember().setMemberId(forClass);
			}


		// -- foreign affairs end



		}
																																				
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		InvoiceItemMemberForm invoiceItemMemberForm = ( InvoiceItemMemberForm ) command;
		InvoiceItemMember invoiceItemMember = invoiceItemMemberForm.getInvoiceItemMember();

//		errors.setNestedPath("invoiceItemMember");
//		getValidator().validate(invoiceItemMember, errors);
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

		InvoiceItemMemberForm invoiceItemMemberForm = ( InvoiceItemMemberForm ) command;

		InvoiceItemMember res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(invoiceItemMemberForm.getInvoiceItemId() != null){
				invoiceItemMemberForm.getInvoiceItemMember().setInvoiceItemId(
					this.invoiceItemService.get(
						new Integer((invoiceItemMemberForm.getInvoiceItemId()))
						)
				);
			}
	
					if(invoiceItemMemberForm.getMemberId() != null){
				invoiceItemMemberForm.getInvoiceItemMember().setMemberId(
					this.memberService.get(
						new Integer((invoiceItemMemberForm.getMemberId()))
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
			
			if (invoiceItemMemberForm.isNewInvoiceItemMember ()) {
				res = invoiceItemMemberService.create (invoiceItemMemberForm.getInvoiceItemMember(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.invoiceitemmember",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.invoiceitemmember",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = invoiceItemMemberService.update (invoiceItemMemberForm.getInvoiceItemMember(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.invoiceitemmember",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.invoiceitemmember",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (invoiceItemMemberForm.isNewInvoiceItemMember ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.invoiceitemmember",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.invoiceitemmember",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("invoiceitemmember"+"?alert="+alertMsg));
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
