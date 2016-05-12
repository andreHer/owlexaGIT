
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
 * ProviderSetMapping is a mapping of provider_set_mapping Table.
*/
public class ProviderSetMappingFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ProviderSetMappingService providerSetMappingService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ProviderSetService providerSetService;

	public void setProviderSetService(ProviderSetService obj){
		this.providerSetService = obj;
	}

	public ProviderSetService getProviderSetService(){
		return this.providerSetService;
	}
			
	// -- foreign affairs end


	public void setProviderSetMappingService (ProviderSetMappingService object){
	    this.providerSetMappingService = object;
	}
	public ProviderSetMappingService getProviderSetMappingService (){
	    return this.providerSetMappingService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getActionUserService() {
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

	
	public ProviderSetMappingFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProviderSetMappingForm tmp = null;
						Integer providerSetMappingId = WebUtil.getParameterInteger (request,"providerSetMappingId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								providerSetMappingId != null
				) {
						java.io.Serializable pkey = providerSetMappingId;
						ProviderSetMapping object = providerSetMappingService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProviderSetMappingForm(object);
			 // foreign affairs
	
				tmp.setProviderSetId(""+
					object.getProviderSetId().getProviderSetId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderSetMappingForm();
					 // foreign affairs
	
	
				Integer providerSetId = WebUtil.getParameterInteger (request,"providerSetId");
		
			if(providerSetId!=null){
				ProviderSet forClass = new ProviderSet();
				forClass.setProviderSetId(providerSetId);
				tmp.setProviderSetId(""+providerSetId);

				ProviderSet providerSet = this.providerSetService.get(providerSetId);
				tmp.getProviderSetMapping().setProviderSetId(providerSet);
			}else{
				ProviderSet forClass = new ProviderSet();
//				tmp.setProviderSetId("");
				tmp.getProviderSetMapping().setProviderSetId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderSetMappingForm();
					 // foreign affairs
		
	
				Integer providerSetId = WebUtil.getParameterInteger (request,"providerSetId");
		
			if(providerSetId!=null){
				ProviderSet forClass = new ProviderSet();
				forClass.setProviderSetId(providerSetId);
				tmp.setProviderSetId(""+providerSetId);

				ProviderSet providerSet = this.providerSetService.get(providerSetId);
				tmp.getProviderSetMapping().setProviderSetId(providerSet);
			}else{
				ProviderSet forClass = new ProviderSet();
//				tmp.setProviderSetId("");
				tmp.getProviderSetMapping().setProviderSetId(forClass);
			}


		// -- foreign affairs end



		}
																																				
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProviderSetMappingForm providerSetMappingForm = ( ProviderSetMappingForm ) command;
		ProviderSetMapping providerSetMapping = providerSetMappingForm.getProviderSetMapping();

//		errors.setNestedPath("providerSetMapping");
//		getValidator().validate(providerSetMapping, errors);
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

		ProviderSetMappingForm providerSetMappingForm = ( ProviderSetMappingForm ) command;

		ProviderSetMapping res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(providerSetMappingForm.getProviderSetId() != null){
				providerSetMappingForm.getProviderSetMapping().setProviderSetId(
					this.providerSetService.get(
						new Integer((providerSetMappingForm.getProviderSetId()))
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
			
			if (providerSetMappingForm.isNewProviderSetMapping ()) {
				res = providerSetMappingService.create (providerSetMappingForm.getProviderSetMapping(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.providersetmapping",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.providersetmapping",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = providerSetMappingService.update (providerSetMappingForm.getProviderSetMapping(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.providersetmapping",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.providersetmapping",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (providerSetMappingForm.isNewProviderSetMapping ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.providersetmapping",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.providersetmapping",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("providersetmapping"+"?alert="+alertMsg));
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
