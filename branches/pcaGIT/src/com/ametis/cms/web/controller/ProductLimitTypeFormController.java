
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
import com.ametis.cms.datamodel.ProductLimitType;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ProductLimitTypeService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProductLimitTypeForm;



// imports+ 

// imports- 


/**
 * ProductLimitType is a mapping of product_limit_type Table.
*/
public class ProductLimitTypeFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ProductLimitTypeService productLimitTypeService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
		
	// -- foreign affairs end
	SecurityService securityService;
	
private ActivityLogService logService;

	
	
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

	public void setProductLimitTypeService (ProductLimitTypeService object){
	    this.productLimitTypeService = object;
	}
	public ProductLimitTypeService getProductLimitTypeService (){
	    return this.productLimitTypeService;
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


	public ProductLimitTypeFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProductLimitTypeForm tmp = null;
						Integer productLimitTypeId = WebUtil.getParameterInteger (request,"productLimitTypeId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								productLimitTypeId != null
				) {
						java.io.Serializable pkey = productLimitTypeId;
						ProductLimitType object = productLimitTypeService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProductLimitTypeForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductLimitTypeForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProductLimitTypeForm();
					 // foreign affairs
			// -- foreign affairs end



		}
								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProductLimitTypeForm productLimitTypeForm = ( ProductLimitTypeForm ) command;
		ProductLimitType productLimitType = productLimitTypeForm.getProductLimitType();

//		errors.setNestedPath("productLimitType");
//		getValidator().validate(productLimitType, errors);
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

		ProductLimitTypeForm productLimitTypeForm = ( ProductLimitTypeForm ) command;

		ProductLimitType res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (productLimitTypeForm.isNewProductLimitType ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEPRODUCTLIMITTYPE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEPRODUCTLIMITTYPE access");
				return errorResult;
				
			}
				res = productLimitTypeService.create (productLimitTypeForm.getProductLimitType(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.productlimittype",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.productlimittype",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEPRODUCTLIMITTYPE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEPRODUCTLIMITTYPE access");
				return errorResult;
				
			}
				res = productLimitTypeService.update (productLimitTypeForm.getProductLimitType(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.productlimittype",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.productlimittype",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (productLimitTypeForm.isNewProductLimitType ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.productlimittype",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.productlimittype",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("productlimittype"+"?alert="+alertMsg));
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
