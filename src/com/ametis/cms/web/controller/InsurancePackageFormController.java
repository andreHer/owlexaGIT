
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.InsurancePackageService;
import com.ametis.cms.service.ProductTypeService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.InsurancePackageForm;



// imports+ 

// imports- 


/**
 * InsurancePackage is a mapping of insurance_package Table.
*/
public class InsurancePackageFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	InsurancePackageService insurancePackageService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			ClientService clientService;
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setClientService(ClientService obj){
		this.clientService = obj;
	}

	public ClientService getClientService(){
		return this.clientService;
	}
				ProductTypeService productTypeService;

	public void setProductTypeService(ProductTypeService obj){
		this.productTypeService = obj;
	}

	public ProductTypeService getProductTypeService(){
		return this.productTypeService;
	}
			
	// -- foreign affairs end


	public void setInsurancePackageService (InsurancePackageService object){
	    this.insurancePackageService = object;
	}
	public InsurancePackageService getInsurancePackageService (){
	    return this.insurancePackageService;
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


	public InsurancePackageFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		InsurancePackageForm tmp = null;
						Integer packageId = WebUtil.getParameterInteger (request,"packageId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								packageId != null
				) {
						java.io.Serializable pkey = packageId;
						InsurancePackage object = insurancePackageService.get (pkey );

			 if (object != null){ // edit object

				tmp = new InsurancePackageForm(object);
			 // foreign affairs
	
				tmp.setClientId(""+
					object.getClientId().getClientId()
				);


	
				tmp.setPackageType(""+
					object.getPackageType().getProductTypeId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new InsurancePackageForm();
					 // foreign affairs
	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getInsurancePackage().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getInsurancePackage().setClientId(forClass);
			}


	
	
				Integer packageType = WebUtil.getParameterInteger (request,"packageType");
		
			if(packageType!=null){
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(packageType);
				tmp.setPackageType(""+packageType);

				ProductType productType = this.productTypeService.get(packageType);
				tmp.getInsurancePackage().setPackageType(productType);
			}else{
				ProductType forClass = new ProductType();
//				tmp.setPackageType("");
				tmp.getInsurancePackage().setPackageType(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new InsurancePackageForm();
					 // foreign affairs
		
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getInsurancePackage().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getInsurancePackage().setClientId(forClass);
			}


	
	
				Integer packageType = WebUtil.getParameterInteger (request,"packageType");
		
			if(packageType!=null){
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(packageType);
				tmp.setPackageType(""+packageType);

				ProductType productType = this.productTypeService.get(packageType);
				tmp.getInsurancePackage().setPackageType(productType);
			}else{
				ProductType forClass = new ProductType();
//				tmp.setPackageType("");
				tmp.getInsurancePackage().setPackageType(forClass);
			}


		// -- foreign affairs end



		}
																																
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		InsurancePackageForm insurancePackageForm = ( InsurancePackageForm ) command;
		InsurancePackage insurancePackage = insurancePackageForm.getInsurancePackage();

//		errors.setNestedPath("insurancePackage");
//		getValidator().validate(insurancePackage, errors);
//		errors.setNestedPath("");
		
		errors.printStackTrace();
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
		Collection<ProductType> types = productTypeService.getAll();
		
		model.put("productTypes",types);

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		InsurancePackageForm insurancePackageForm = ( InsurancePackageForm ) command;

		InsurancePackage res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(insurancePackageForm.getClientId() != null){
				insurancePackageForm.getInsurancePackage().setClientId(
					this.clientService.get(
						new Integer((insurancePackageForm.getClientId()))
						)
				);
			}
	
					if(insurancePackageForm.getPackageType() != null){
				insurancePackageForm.getInsurancePackage().setPackageType(
					this.productTypeService.get(
						new Integer((insurancePackageForm.getPackageType()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (insurancePackageForm.isNewInsurancePackage ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEINSURANCEPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEINSURANCEPACKAGE access");
				return errorResult;
				
			}
				res = insurancePackageService.create (insurancePackageForm.getInsurancePackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.insurancepackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.insurancepackage",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEINSURANCEPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEINSURANCEPACKAGE access");
				return errorResult;
				
			}
				res = insurancePackageService.update (insurancePackageForm.getInsurancePackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.insurancepackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.insurancepackage",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (insurancePackageForm.isNewInsurancePackage ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.insurancepackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.insurancepackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("insurancepackage"+"?alert="+alertMsg));
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
