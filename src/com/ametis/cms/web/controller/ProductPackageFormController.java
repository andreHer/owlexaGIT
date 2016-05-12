
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
import com.ametis.cms.datamodel.InsurancePackage;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductPackage;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.InsurancePackageService;
import com.ametis.cms.service.ProductPackageService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProductPackageForm;



// imports+ 

// imports- 


/**
 * ProductPackage is a mapping of product_package Table.
*/
public class ProductPackageFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	SecurityService securityService;
	

	public SecurityService getSecurityService() {
		return securityService;
	}
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	ProductPackageService productPackageService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			ProductService productService;

	public void setProductService(ProductService obj){
		this.productService = obj;
	}

	public ProductService getProductService(){
		return this.productService;
	}
				InsurancePackageService insurancePackageService;

	public void setInsurancePackageService(InsurancePackageService obj){
		this.insurancePackageService = obj;
	}

	public InsurancePackageService getInsurancePackageService(){
		return this.insurancePackageService;
	}
			
	// -- foreign affairs end


	public void setProductPackageService (ProductPackageService object){
	    this.productPackageService = object;
	}
	public ProductPackageService getProductPackageService (){
	    return this.productPackageService;
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


	public ProductPackageFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProductPackageForm tmp = null;
						Integer productPackageId = WebUtil.getParameterInteger (request,"productPackageId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								productPackageId != null
				) {
						java.io.Serializable pkey = productPackageId;
						ProductPackage object = productPackageService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProductPackageForm(object);
			 // foreign affairs
	
				tmp.setProductId(""+
					object.getProductId().getProductId()
				);


	
				tmp.setPackageId(""+
					object.getPackageId().getPackageId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductPackageForm();
					 // foreign affairs
	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getProductPackage().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getProductPackage().setProductId(forClass);
			}


	
	
				Integer packageId = WebUtil.getParameterInteger (request,"packageId");
		
			if(packageId!=null){
				InsurancePackage forClass = new InsurancePackage();
				forClass.setPackageId(packageId);
				tmp.setPackageId(""+packageId);

				InsurancePackage insurancePackage = this.insurancePackageService.get(packageId);
				tmp.getProductPackage().setPackageId(insurancePackage);
			}else{
				InsurancePackage forClass = new InsurancePackage();
//				tmp.setPackageId("");
				tmp.getProductPackage().setPackageId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProductPackageForm();
					 // foreign affairs
		
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getProductPackage().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getProductPackage().setProductId(forClass);
			}


	
	
				Integer packageId = WebUtil.getParameterInteger (request,"packageId");
		
			if(packageId!=null){
				InsurancePackage forClass = new InsurancePackage();
				forClass.setPackageId(packageId);
				tmp.setPackageId(""+packageId);

				InsurancePackage insurancePackage = this.insurancePackageService.get(packageId);
				tmp.getProductPackage().setPackageId(insurancePackage);
			}else{
				InsurancePackage forClass = new InsurancePackage();
//				tmp.setPackageId("");
				tmp.getProductPackage().setPackageId(forClass);
			}


		// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProductPackageForm productPackageForm = ( ProductPackageForm ) command;
		ProductPackage productPackage = productPackageForm.getProductPackage();

//		errors.setNestedPath("productPackage");
//		getValidator().validate(productPackage, errors);
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

		ProductPackageForm productPackageForm = ( ProductPackageForm ) command;

		ProductPackage res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(productPackageForm.getProductId() != null){
				productPackageForm.getProductPackage().setProductId(
					this.productService.get(
						new Integer((productPackageForm.getProductId()))
						)
				);
			}
	
					if(productPackageForm.getPackageId() != null){
				productPackageForm.getProductPackage().setPackageId(
					this.insurancePackageService.get(
						new Integer((productPackageForm.getPackageId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (productPackageForm.isNewProductPackage ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEPRODUCTPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEPRODUCTPACKAGE access");
				return errorResult;
				
			}
				res = productPackageService.create (productPackageForm.getProductPackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.productpackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.productpackage",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEPRODUCTPACKAGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEPRODUCTPACKAGE access");
				return errorResult;
				
			}
				res = productPackageService.update (productPackageForm.getProductPackage(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.productpackage",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.productpackage",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (productPackageForm.isNewProductPackage ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.productpackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.productpackage",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("productpackage"+"?alert="+alertMsg));
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
