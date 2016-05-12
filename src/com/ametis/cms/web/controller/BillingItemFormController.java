
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
 * BillingItem is a mapping of billing_item Table.
*/
public class BillingItemFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	BillingItemService billingItemService ;
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
				ClaimService claimService;

	public void setClaimService(ClaimService obj){
		this.claimService = obj;
	}

	public ClaimService getClaimService(){
		return this.claimService;
	}
				ClientService clientService;

	public void setClientService(ClientService obj){
		this.clientService = obj;
	}

	public ClientService getClientService(){
		return this.clientService;
	}
				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
				PolicyService policyService;

	public void setPolicyService(PolicyService obj){
		this.policyService = obj;
	}

	public PolicyService getPolicyService(){
		return this.policyService;
	}
			
	// -- foreign affairs end


	public void setBillingItemService (BillingItemService object){
	    this.billingItemService = object;
	}
	public BillingItemService getBillingItemService (){
	    return this.billingItemService;
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

	
	public BillingItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		BillingItemForm tmp = null;
						Integer billingItemId = WebUtil.getParameterInteger (request,"billingItemId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								billingItemId != null
				) {
						java.io.Serializable pkey = billingItemId;
						BillingItem object = billingItemService.get (pkey );

			 if (object != null){ // edit object

				tmp = new BillingItemForm(object);
			
	
				tmp.setClaimId(""+
					object.getClaimId().getClaimId()
				);


	
				tmp.setClientId(""+
					object.getClientId().getClientId()
				);


	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


	
				tmp.setPolicyId(""+
					object.getPolicyId().getPolicyId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new BillingItemForm();
	
				Integer claimId = WebUtil.getParameterInteger (request,"claimId");
		
			if(claimId!=null){
				Claim forClass = new Claim();
				forClass.setClaimId(claimId);
				tmp.setClaimId(""+claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getBillingItem().setClaimId(claim);
			}else{
				Claim forClass = new Claim();
//				tmp.setClaimId("");
				tmp.getBillingItem().setClaimId(forClass);
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getBillingItem().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getBillingItem().setClientId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getBillingItem().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getBillingItem().setMemberId(forClass);
			}


	
	
				Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getBillingItem().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getBillingItem().setPolicyId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new BillingItemForm();
					 // foreign affairs
		
	
				Integer memberImportId = WebUtil.getParameterInteger (request,"memberImportId");
		
			if(memberImportId!=null){
				MemberImport forClass = new MemberImport();
				forClass.setId(memberImportId);
				tmp.setMemberImportId(""+memberImportId);

				MemberImport memberImport = this.memberImportService.get(memberImportId);
				tmp.getBillingItem().setMemberImportId(memberImport);
			}else{
				MemberImport forClass = new MemberImport();
//				tmp.setMemberImportId("");
				tmp.getBillingItem().setMemberImportId(forClass);
			}


	
	
				Integer claimId = WebUtil.getParameterInteger (request,"claimId");
		
			if(claimId!=null){
				Claim forClass = new Claim();
				forClass.setClaimId(claimId);
				tmp.setClaimId(""+claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getBillingItem().setClaimId(claim);
			}else{
				Claim forClass = new Claim();
//				tmp.setClaimId("");
				tmp.getBillingItem().setClaimId(forClass);
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getBillingItem().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getBillingItem().setClientId(forClass);
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getBillingItem().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getBillingItem().setMemberId(forClass);
			}


	
	
				Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getBillingItem().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getBillingItem().setPolicyId(forClass);
			}


		// -- foreign affairs end



		}
																																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		BillingItemForm billingItemForm = ( BillingItemForm ) command;
		BillingItem billingItem = billingItemForm.getBillingItem();

//		errors.setNestedPath("billingItem");
//		getValidator().validate(billingItem, errors);
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

		BillingItemForm billingItemForm = ( BillingItemForm ) command;

		BillingItem res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(billingItemForm.getMemberImportId() != null){
				billingItemForm.getBillingItem().setMemberImportId(
					this.memberImportService.get(
						new Integer((billingItemForm.getMemberImportId()))
						)
				);
			}
	
					if(billingItemForm.getClaimId() != null){
				billingItemForm.getBillingItem().setClaimId(
					this.claimService.get(
						new Integer((billingItemForm.getClaimId()))
						)
				);
			}
	
					if(billingItemForm.getClientId() != null){
				billingItemForm.getBillingItem().setClientId(
					this.clientService.get(
						new Integer((billingItemForm.getClientId()))
						)
				);
			}
	
					if(billingItemForm.getMemberId() != null){
				billingItemForm.getBillingItem().setMemberId(
					this.memberService.get(
						new Integer((billingItemForm.getMemberId()))
						)
				);
			}
	
					if(billingItemForm.getPolicyId() != null){
				billingItemForm.getBillingItem().setPolicyId(
					this.policyService.get(
						new Integer((billingItemForm.getPolicyId()))
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
			
			if (billingItemForm.isNewBillingItem ()) {
				res = billingItemService.create (billingItemForm.getBillingItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.billingitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.billingitem",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = billingItemService.update (billingItemForm.getBillingItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.billingitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.billingitem",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (billingItemForm.isNewBillingItem ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.billingitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.billingitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("billingitem"+"?alert="+alertMsg));
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
