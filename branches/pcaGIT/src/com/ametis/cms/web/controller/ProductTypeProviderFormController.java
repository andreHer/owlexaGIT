
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
 * ProductTypeProvider is a mapping of product_type_provider Table.
*/
public class ProductTypeProviderFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ProductTypeProviderService productTypeProviderService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ProductTypeService productTypeService;

	public void setProductTypeService(ProductTypeService obj){
		this.productTypeService = obj;
	}

	public ProductTypeService getProductTypeService(){
		return this.productTypeService;
	}
				ProviderService providerService;

	public void setProviderService(ProviderService obj){
		this.providerService = obj;
	}

	public ProviderService getProviderService(){
		return this.providerService;
	}
			
	// -- foreign affairs end


	public void setProductTypeProviderService (ProductTypeProviderService object){
	    this.productTypeProviderService = object;
	}
	public ProductTypeProviderService getProductTypeProviderService (){
	    return this.productTypeProviderService;
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

	
	public ProductTypeProviderFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProductTypeProviderForm tmp = null;
						Integer productTypeProviderId = WebUtil.getParameterInteger (request,"productTypeProviderId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								productTypeProviderId != null
				) {
						java.io.Serializable pkey = productTypeProviderId;
						ProductTypeProvider object = productTypeProviderService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProductTypeProviderForm(object);
			 // foreign affairs
	
				tmp.setProductTypeId(""+
					object.getProductTypeId().getProductTypeId()
				);


	
				tmp.setProviderId(""+
					object.getProviderId().getProviderId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductTypeProviderForm();
					 // foreign affairs
	
	
				Integer productTypeId = WebUtil.getParameterInteger (request,"productTypeId");
		
			if(productTypeId!=null){
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(productTypeId);
				tmp.setProductTypeId(""+productTypeId);

				ProductType productType = this.productTypeService.get(productTypeId);
				tmp.getProductTypeProvider().setProductTypeId(productType);
			}else{
				ProductType forClass = new ProductType();
//				tmp.setProductTypeId("");
				tmp.getProductTypeProvider().setProductTypeId(forClass);
			}


	
	
				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProductTypeProvider().setProviderId(provider);
			}else{
				Provider forClass = new Provider();
//				tmp.setProviderId("");
				tmp.getProductTypeProvider().setProviderId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProductTypeProviderForm();
					 // foreign affairs
		
	
				Integer productTypeId = WebUtil.getParameterInteger (request,"productTypeId");
		
			if(productTypeId!=null){
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(productTypeId);
				tmp.setProductTypeId(""+productTypeId);

				ProductType productType = this.productTypeService.get(productTypeId);
				tmp.getProductTypeProvider().setProductTypeId(productType);
			}else{
				ProductType forClass = new ProductType();
//				tmp.setProductTypeId("");
				tmp.getProductTypeProvider().setProductTypeId(forClass);
			}


	
	
				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProductTypeProvider().setProviderId(provider);
			}else{
				Provider forClass = new Provider();
//				tmp.setProviderId("");
				tmp.getProductTypeProvider().setProviderId(forClass);
			}


		// -- foreign affairs end



		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProductTypeProviderForm productTypeProviderForm = ( ProductTypeProviderForm ) command;
		ProductTypeProvider productTypeProvider = productTypeProviderForm.getProductTypeProvider();

//		errors.setNestedPath("productTypeProvider");
//		getValidator().validate(productTypeProvider, errors);
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

		ProductTypeProviderForm productTypeProviderForm = ( ProductTypeProviderForm ) command;

		ProductTypeProvider res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(productTypeProviderForm.getProductTypeId() != null){
				productTypeProviderForm.getProductTypeProvider().setProductTypeId(
					this.productTypeService.get(
						new Integer((productTypeProviderForm.getProductTypeId()))
						)
				);
			}
	
					if(productTypeProviderForm.getProviderId() != null){
				productTypeProviderForm.getProductTypeProvider().setProviderId(
					this.providerService.get(
						new Integer((productTypeProviderForm.getProviderId()))
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
			
			if (productTypeProviderForm.isNewProductTypeProvider ()) {
				res = productTypeProviderService.create (productTypeProviderForm.getProductTypeProvider(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.producttypeprovider",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.producttypeprovider",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = productTypeProviderService.update (productTypeProviderForm.getProductTypeProvider(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.producttypeprovider",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.producttypeprovider",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (productTypeProviderForm.isNewProductTypeProvider ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.producttypeprovider",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.producttypeprovider",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("producttypeprovider"+"?alert="+alertMsg));
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
