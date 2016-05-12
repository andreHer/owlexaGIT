
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
 * ProviderPoliklinik is a mapping of provider_poliklinik Table.
*/
public class ProviderPoliklinikFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ProviderPoliklinikService providerPoliklinikService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			PoliklinikService poliklinikService;

	public void setPoliklinikService(PoliklinikService obj){
		this.poliklinikService = obj;
	}

	public PoliklinikService getPoliklinikService(){
		return this.poliklinikService;
	}
				ProviderService providerService;

	public void setProviderService(ProviderService obj){
		this.providerService = obj;
	}

	public ProviderService getProviderService(){
		return this.providerService;
	}
			
	// -- foreign affairs end


	public void setProviderPoliklinikService (ProviderPoliklinikService object){
	    this.providerPoliklinikService = object;
	}
	public ProviderPoliklinikService getProviderPoliklinikService (){
	    return this.providerPoliklinikService;
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

	
	public ProviderPoliklinikFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProviderPoliklinikForm tmp = null;
		Integer providerPoliklinikId = WebUtil.getParameterInteger (request,"providerPoliklinikId");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		String breadcrumb = "";
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								providerPoliklinikId != null
				) {
						java.io.Serializable pkey = providerPoliklinikId;
						ProviderPoliklinik object = providerPoliklinikService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProviderPoliklinikForm(object);
			 // foreign affairs
	
				tmp.setPoliklinikId(""+
					object.getPoliklinikId().getPoliklinikId()
				);


	
				tmp.setProviderId(""+
					object.getProviderId().getProviderId()
				);
				
				if (object.getProviderId() != null){
					Provider provider = providerService.get(object.getProviderId().getProviderId());
					
					if (provider != null){
						tmp.setProviderName(provider.getProviderName());
					}
				}


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderPoliklinikForm();
					 // foreign affairs
	
	
				Integer poliklinikId = WebUtil.getParameterInteger (request,"poliklinikId");
		
			if(poliklinikId!=null){
				Poliklinik forClass = new Poliklinik();
				forClass.setPoliklinikId(poliklinikId);
				tmp.setPoliklinikId(""+poliklinikId);

				Poliklinik poliklinik = this.poliklinikService.get(poliklinikId);
				tmp.getProviderPoliklinik().setPoliklinikId(poliklinik);
			}else{
				Poliklinik forClass = new Poliklinik();
//				tmp.setPoliklinikId("");
				tmp.getProviderPoliklinik().setPoliklinikId(forClass);
			}
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProviderPoliklinik().setProviderId(provider);
			}else{
				Provider forClass = new Provider();
//				tmp.setProviderId("");
				tmp.getProviderPoliklinik().setProviderId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderPoliklinikForm();
					 // foreign affairs
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				if (provider != null){
					tmp.getProviderPoliklinik().setProviderId(provider);
					tmp.setProviderName(provider.getProviderName());
				}
			}
		}
		
		breadcrumb = "<a href=\"providerpoliklinik?providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Poliklinik </a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Poliklinik";
		request.setAttribute("breadcrumb", breadcrumb);
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProviderPoliklinikForm providerPoliklinikForm = ( ProviderPoliklinikForm ) command;
		ProviderPoliklinik providerPoliklinik = providerPoliklinikForm.getProviderPoliklinik();

		errors.printStackTrace();
//		errors.setNestedPath("providerPoliklinik");
//		getValidator().validate(providerPoliklinik, errors);
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

		ProviderPoliklinikForm providerPoliklinikForm = ( ProviderPoliklinikForm ) command;

		ProviderPoliklinik res = null;
		String alertMsg="";
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String providerId = providerPoliklinikForm.getProviderId();
		try {
			
			ActionUser user = securityService.getActionUser(request);
			
			if (providerPoliklinikForm.isNewProviderPoliklinik ()) {
				if (navigation.equalsIgnoreCase("upload")){
					res = providerPoliklinikService.createUpload (providerPoliklinikForm.getProviderPoliklinik(),providerPoliklinikForm.getMultipartFile(),user);
	
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.providerpoliklinik",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.providerpoliklinik",null,"fail",Locale.getDefault());
					}
				}
				else {
					res = providerPoliklinikService.create (providerPoliklinikForm.getProviderPoliklinik(),user);

					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.providerpoliklinik",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.providerpoliklinik",null,"fail",Locale.getDefault());
					}	
				}
			}
			else {
				res = providerPoliklinikService.update (providerPoliklinikForm.getProviderPoliklinik(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.providerpoliklinik",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.providerpoliklinik",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (providerPoliklinikForm.isNewProviderPoliklinik ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.providerpoliklinik",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.providerpoliklinik",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("providerpoliklinik"+"?providerId="+providerId+"&alert="+alertMsg));
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
