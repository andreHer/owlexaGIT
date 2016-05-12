
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
 * ProviderDoctor is a mapping of provider_doctor Table.
*/
public class ProviderDoctorFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ProviderDoctorService providerDoctorService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	private PoliklinikService poliklinikService;
	// foreign affairs
	
			ProviderService providerService;

	public void setProviderService(ProviderService obj){
		this.providerService = obj;
	}

	public ProviderService getProviderService(){
		return this.providerService;
	}
				ProviderItemService providerItemService;

	public void setProviderItemService(ProviderItemService obj){
		this.providerItemService = obj;
	}

	public ProviderItemService getProviderItemService(){
		return this.providerItemService;
	}
			
	// -- foreign affairs end


	public void setProviderDoctorService (ProviderDoctorService object){
	    this.providerDoctorService = object;
	}
	public ProviderDoctorService getProviderDoctorService (){
	    return this.providerDoctorService;
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
	

	
	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}

	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}

	public ProviderDoctorFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProviderDoctorForm tmp = null;
		Integer providerDoctor = WebUtil.getParameterInteger (request,"providerDoctor");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								providerDoctor != null
				) {
						java.io.Serializable pkey = providerDoctor;
						ProviderDoctor object = providerDoctorService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProviderDoctorForm(object);
			 // foreign affairs
	
				tmp.setProviderId(""+
					object.getProviderId().getProviderId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderDoctorForm();
					 // foreign affairs
	
	
//				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProviderDoctor().setProviderId(provider);
			}


	
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderDoctorForm();
					 // foreign affairs
		
	
//				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProviderDoctor().setProviderId(provider);
				tmp.setProviderName(provider.getProviderName());
			}else{
			}
		}
							
		String breadcrumb = "<a href=\"providerdoctor?providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Doctor </a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Doctor";
		request.setAttribute("breadcrumb", breadcrumb);
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProviderDoctorForm providerDoctorForm = ( ProviderDoctorForm ) command;
		ProviderDoctor providerDoctor = providerDoctorForm.getProviderDoctor();
		
		errors.printStackTrace();

//		errors.setNestedPath("providerDoctor");
//		getValidator().validate(providerDoctor, errors);
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
		
		try {
			Collection<Poliklinik> poliklinikList = poliklinikService.getAll();
			model.put("poliklinikList", poliklinikList);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ProviderDoctorForm providerDoctorForm = ( ProviderDoctorForm ) command;

		ProviderDoctor res = null;
		String alertMsg="";
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String providerId = providerDoctorForm.getProviderId();
		try {
			
			ActionUser user = securityService.getActionUser(request);
			
			if (providerDoctorForm.isNewProviderDoctor ()) {
				
				if (navigation.equalsIgnoreCase("upload")){
					
					res = providerDoctorService.createUpload (providerDoctorForm.getProviderDoctor(),providerDoctorForm.getMultipartFile(),user);
	
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.providerdoctor",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.providerdoctor",null,"fail",Locale.getDefault());
					}
				}
				else {
					
					res = providerDoctorService.create (providerDoctorForm.getProviderDoctor(),user);
	
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.providerdoctor",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.providerdoctor",null,"fail",Locale.getDefault());
					}
				}
			}
			else {
				res = providerDoctorService.update (providerDoctorForm.getProviderDoctor(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.providerdoctor",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.providerdoctor",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (providerDoctorForm.isNewProviderDoctor ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.providerdoctor",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.providerdoctor",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("providerdoctor"+"?providerId="+providerId+"&alert="+alertMsg));
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
