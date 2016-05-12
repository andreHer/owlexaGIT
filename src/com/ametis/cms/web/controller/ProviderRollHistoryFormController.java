
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
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.*;



// imports+ 

// imports- 

/**
 * ProviderRollHistory is a mapping of provider_roll_history Table.
*/
public class ProviderRollHistoryFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ProviderRollHistoryService providerRollHistoryService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	
	private ProviderService providerService;
	// foreign affairs
	
		
	// -- foreign affairs end


	public void setProviderRollHistoryService (ProviderRollHistoryService object){
	    this.providerRollHistoryService = object;
	}
	public ProviderRollHistoryService getProviderRollHistoryService (){
	    return this.providerRollHistoryService;
	}
		// generate by default
//		private ActionUserService  actionuserService;
//	public ActionUserService getActionUserService() {
//		return actionuserService;
//	}
//	public void setActionUserService(ActionUserService userService) {
//		this.actionuserService = userService;
//	}
	
	
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

	
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public ProviderRollHistoryFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProviderRollHistoryForm tmp = null;
		Integer providerRollHistoryId = WebUtil.getParameterInteger (request,"providerRollHistoryId");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if(providerRollHistoryId != null) {
			java.io.Serializable pkey = providerRollHistoryId;
			ProviderRollHistory object = providerRollHistoryService.get (pkey );
			
			if (object != null){ // edit object
				tmp = new ProviderRollHistoryForm(object);
				Provider provider = providerService.get(object.getProviderId().getProviderId());
				tmp.setProviderName(provider.getProviderName());
				
				String breadcrumb = "<a href=\"providerrollhistory?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider Roll Paper</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Provider Roll Paper";
				request.setAttribute("breadcrumb", breadcrumb);
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderRollHistoryForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderRollHistoryForm();
			if (providerId != null){
				Provider provider = providerService.get(providerId);
				if (provider != null){
					tmp.setProviderId(provider.getProviderId().toString());
					tmp.setProviderName(provider.getProviderName());
				}
				String breadcrumb = "<a href=\"providerrollhistory?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider Roll Paper</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Provider Roll Paper";
				request.setAttribute("breadcrumb", breadcrumb);

			}
		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProviderRollHistoryForm providerRollHistoryForm = ( ProviderRollHistoryForm ) command;
		ProviderRollHistory providerRollHistory = providerRollHistoryForm.getProviderRollHistory();

//		errors.setNestedPath("providerRollHistory");
//		getValidator().validate(providerRollHistory, errors);
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

		ProviderRollHistoryForm providerRollHistoryForm = ( ProviderRollHistoryForm ) command;

		ProviderRollHistory res = null;
		String alertMsg="";
		String providerId = "";
		
		Provider objectProv = null;
		
		try {
		// foreign affairs
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
			
			providerId = providerRollHistoryForm.getProviderId();
			if(providerId != null)
				objectProv = providerService.get(Integer.valueOf(providerId));
			
			if (providerRollHistoryForm.isNewProviderRollHistory ()) {
				res = providerRollHistoryService.create(providerRollHistoryForm.getProviderRollHistory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.providerrollhistory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.providerrollhistory",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = providerRollHistoryService.update (providerRollHistoryForm.getProviderRollHistory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.providerrollhistory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.providerrollhistory",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (providerRollHistoryForm.isNewProviderRollHistory ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.providerrollhistory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.providerrollhistory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("providerrollhistory"+"?providerId="+providerId+"&alert="
				+ alertMsg));
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
