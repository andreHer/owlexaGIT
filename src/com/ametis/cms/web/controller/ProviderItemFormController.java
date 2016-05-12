
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
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.ProviderItemService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProviderItemForm;



// imports+ 

// imports- 


/**
 * ProviderItem is a mapping of provider_item Table.
*/
public class ProviderItemFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ProviderItemService providerItemService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			ProviderService providerService;
			SecurityService securityService;
			private ActivityLogService logService;
			private ItemService itemService;

			
			
			public ItemService getItemService() {
				return itemService;
			}

			public void setItemService(ItemService itemService) {
				this.itemService = itemService;
			}

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
	public void setProviderService(ProviderService obj){
		this.providerService = obj;
	}

	public ProviderService getProviderService(){
		return this.providerService;
	}
			
	// -- foreign affairs end


	public void setProviderItemService (ProviderItemService object){
	    this.providerItemService = object;
	}
	public ProviderItemService getProviderItemService (){
	    return this.providerItemService;
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


	public ProviderItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProviderItemForm tmp = null;
		Integer providerItemId = WebUtil.getParameterInteger (request,"providerItemId");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (providerItemId != null) {
						java.io.Serializable pkey = providerItemId;
						ProviderItem object = providerItemService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProviderItemForm(object);
			 // foreign affairs
	
				tmp.setProviderId(""+
					object.getProviderId().getProviderId().toString()
				);
				if (object.getItemId() != null){
					tmp.setItemId(object.getItemId().getItemId().toString());
					
					Item item = itemService.get(object.getItemId().getItemId());
					
					if (item != null){
						tmp.setItemName(item.getItemName());
					}
				}
				
				
				Provider provider = providerService.get(object.getProviderId().getProviderId());
				
				if (provider != null){
					tmp.setProviderName(provider.getProviderName());
				}


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderItemForm();
					 // foreign affairs
	
	
//				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProviderItem().setProviderId(provider);
				tmp.setProviderName(provider.getProviderName());
			}else{
				
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderItemForm();
					 // foreign affairs
		
	
//			Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProviderItem().setProviderId(provider);
				tmp.setProviderName(provider.getProviderName());
			}

		}
		
		String breadcrumb = "<a href=\"provideritem?navigation=list&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Room & Board </a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Item";
		request.setAttribute("breadcrumb", breadcrumb);
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProviderItemForm providerItemForm = ( ProviderItemForm ) command;
		ProviderItem providerItem = providerItemForm.getProviderItem();

//		errors.setNestedPath("providerItem");
//		getValidator().validate(providerItem, errors);
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

		ProviderItemForm providerItemForm = ( ProviderItemForm ) command;

		ProviderItem res = null;
		String alertMsg="";
		String providerId = providerItemForm.getProviderId();
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		try {
			
			if (providerItemForm.isNewProviderItem ()) {
				ActionUser user= securityService.getActionUser(request); 
			
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEPROVIDERITEM");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEPROVIDERITEM access");
					return errorResult;
					
				}
			
				if (navigation.equalsIgnoreCase("upload")){
					res = providerItemService.createUpload (providerItemForm.getProviderItem(),providerItemForm.getMultipartFile(),user);
	
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.provideritem",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.provideritem",null,"fail",Locale.getDefault());
					}
				}
				else {
					res = providerItemService.create (providerItemForm.getProviderItem(),user);

					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.provideritem",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.provideritem",null,"fail",Locale.getDefault());
					}
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEPROVIDERITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEPROVIDERITEM access");
				return errorResult;
				
			}
				res = providerItemService.update (providerItemForm.getProviderItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.provideritem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.provideritem",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (providerItemForm.isNewProviderItem ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.provideritem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.provideritem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("provideritem"+"?navigation=list&providerId="+providerId+"&alert="+alertMsg));
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
