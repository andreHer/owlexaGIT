
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.*;



// imports+ 

// imports- 

/**
 * ClaimReceiving is a mapping of claim_receiving Table.
*/
public class ClaimReceivingFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ClaimReceivingService claimReceivingService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ClientService clientService;

	public void setClientService(ClientService obj){
		this.clientService = obj;
	}

	public ClientService getClientService(){
		return this.clientService;
	}
				ProviderService providerService;

	public void setProviderService(ProviderService obj){
		this.providerService = obj;
	}

	public ProviderService getProviderService(){
		return this.providerService;
	}
				MemberGroupService memberGroupService;

	public void setMemberGroupService(MemberGroupService obj){
		this.memberGroupService = obj;
	}

	public MemberGroupService getMemberGroupService(){
		return this.memberGroupService;
	}
			
	// -- foreign affairs end


	public void setClaimReceivingService (ClaimReceivingService object){
	    this.claimReceivingService = object;
	}
	public ClaimReceivingService getClaimReceivingService (){
	    return this.claimReceivingService;
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

	
	public ClaimReceivingFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ClaimReceivingForm tmp = null;
		Integer claimReceivingId = WebUtil.getParameterInteger (request,"claimReceivingId");
		String breadcrumb = "";
		String navigation = WebUtil.getParameterString (request,"navigation", "");

		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								claimReceivingId != null
				) {
						java.io.Serializable pkey = claimReceivingId;
						ClaimReceiving object = claimReceivingService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ClaimReceivingForm(object);
			 // foreign affairs
	
				
				if (object.getClientId() != null){
					Client client = clientService.get(object.getClientId().getClientId());
					
					tmp.setClientId(""+
						object.getClientId().getClientId()
					);
					tmp.setClientName(client.getClientName());
				}

				if (object.getProviderId() != null){
	
					Provider provider = providerService.get(object.getProviderId().getProviderId());
					
					tmp.setProviderName(provider.getProviderName());
					tmp.setProviderId(""+
						object.getProviderId().getProviderId()
					);
				}


				if (object.getMemberGroupId() != null){
		
					MemberGroup group = memberGroupService.get(object.getMemberGroupId().getMemberGroupId());
					
					tmp.setMemberGroupId(""+
						object.getMemberGroupId().getMemberGroupId()
					);
					tmp.setMemberGroupName(group.getGroupName());
				}


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClaimReceivingForm();
					 // foreign affairs
	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getClaimReceiving().setClientId(client);
			}


	
	
				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getClaimReceiving().setProviderId(provider);
			}

				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getClaimReceiving().setMemberGroupId(memberGroup);
			}

		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClaimReceivingForm();
					 // foreign affairs
		
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getClaimReceiving().setClientId(client);
			}


	
	
				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getClaimReceiving().setProviderId(provider);
			}


	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId(""+memberGroupId);

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getClaimReceiving().setMemberGroupId(memberGroup);
			}

			Calendar calendar = Calendar.getInstance();
			
			String hh = calendar.get(Calendar.HOUR_OF_DAY) + "";
			
			if (hh.length() == 1){
				hh = "0" + hh;
			}
			String mm = calendar.get(Calendar.MINUTE) + "";
			
			if (mm.length() == 1){
				mm = "0" + mm;
			}
			
			tmp.setReceiveTime(hh+":"+mm);
			tmp.setReceiveDate(new java.sql.Date(System.currentTimeMillis()).toString());
		}
		
        if (navigation.equalsIgnoreCase("tambah")) {
            breadcrumb = "<a href=\"claimreceiving\" class=\"linkbreadcrumb\">Search Claim Receiving</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Claim Receiving";
        }
        if (navigation.equalsIgnoreCase("edit")) {
            breadcrumb = "<a href=\"claimreceiving\" class=\"linkbreadcrumb\">Search Claim Receiving</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Edit Claim Receiving";
        }
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
																															
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {

		String breadcrumb = "";
		String navigation = WebUtil.getParameterString (request,"navigation", "");

		ClaimReceivingForm claimReceivingForm = ( ClaimReceivingForm ) command;
		ClaimReceiving claimReceiving = claimReceivingForm.getClaimReceiving();

//		errors.setNestedPath("claimReceiving");
//		getValidator().validate(claimReceiving, errors);
//		errors.setNestedPath("");
		
        if (navigation.equalsIgnoreCase("tambah")) {
            breadcrumb = "<a href=\"claimreceiving\" class=\"linkbreadcrumb\">Search Claim Receiving</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Claim Receiving";
        }
        if (navigation.equalsIgnoreCase("edit")) {
            breadcrumb = "<a href=\"claimreceiving\" class=\"linkbreadcrumb\">Search Claim Receiving</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Edit Claim Receiving";
        }
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
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
		
		String breadcrumb = "";
		String navigation = WebUtil.getParameterString (request,"navigation", "");

		ClaimReceivingForm claimReceivingForm = ( ClaimReceivingForm ) command;

		ClaimReceiving res = null;
		String alertMsg="";
		try {
		// foreign affairs
	
				
			ActionUser user = securityService.getActionUser(request);
			
			if (claimReceivingForm.isNewClaimReceiving ()) {
				res = claimReceivingService.create (claimReceivingForm.getClaimReceiving(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.claimreceiving",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.claimreceiving",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = claimReceivingService.update (claimReceivingForm.getClaimReceiving(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.claimreceiving",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.claimreceiving",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (claimReceivingForm.isNewClaimReceiving ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.claimreceiving",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.claimreceiving",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
        if (navigation.equalsIgnoreCase("tambah")) {
            breadcrumb = "<a href=\"claimreceiving\" class=\"linkbreadcrumb\">Search Claim Receiving</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Claim Receiving";
        }
        if (navigation.equalsIgnoreCase("edit")) {
            breadcrumb = "<a href=\"claimreceiving\" class=\"linkbreadcrumb\">Search Claim Receiving</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Edit Claim Receiving";
        }        
        
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
		
		return new ModelAndView(new RedirectView("claimreceiving"+"?alert="+alertMsg));
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
