
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
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.Quotation;
import com.ametis.cms.datamodel.QuotationProduct;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.QuotationProductService;
import com.ametis.cms.service.QuotationService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.QuotationProductForm;



// imports+ 

// imports- 

/**
 * QuotationProduct is a mapping of quotation_product Table.
*/
public class QuotationProductFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	QuotationProductService quotationProductService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	SecurityService securityService;
			ProductService productService;

			
	public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}

	public void setProductService(ProductService obj){
		this.productService = obj;
	}

	public ProductService getProductService(){
		return this.productService;
	}
				QuotationService quotationService;

	public void setQuotationService(QuotationService obj){
		this.quotationService = obj;
	}

	public QuotationService getQuotationService(){
		return this.quotationService;
	}
			
	// -- foreign affairs end


	public void setQuotationProductService (QuotationProductService object){
	    this.quotationProductService = object;
	}
	public QuotationProductService getQuotationProductService (){
	    return this.quotationProductService;
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


	public QuotationProductFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		QuotationProductForm tmp = null;
						Integer quotationProductId = WebUtil.getParameterInteger (request,"quotationProductId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								quotationProductId != null
				) {
						java.io.Serializable pkey = quotationProductId;
						QuotationProduct object = quotationProductService.get (pkey );

			 if (object != null){ // edit object

				tmp = new QuotationProductForm(object);
			 // foreign affairs
	
				tmp.setProductId(""+
					object.getProductId().getProductId()
				);


	
				tmp.setQuotationId(""+
					object.getQuotationId().getQuotationId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new QuotationProductForm();
					 // foreign affairs
	
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getQuotationProduct().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getQuotationProduct().setProductId(forClass);
			}


	
	
				Integer quotationId = WebUtil.getParameterInteger (request,"quotationId");
		
			if(quotationId!=null){
				Quotation forClass = new Quotation();
				forClass.setQuotationId(quotationId);
				tmp.setQuotationId(""+quotationId);

				Quotation quotation = this.quotationService.get(quotationId);
				tmp.getQuotationProduct().setQuotationId(quotation);
			}else{
				Quotation forClass = new Quotation();
//				tmp.setQuotationId("");
				tmp.getQuotationProduct().setQuotationId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new QuotationProductForm();
					 // foreign affairs
		
	
				Integer productId = WebUtil.getParameterInteger (request,"productId");
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getQuotationProduct().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getQuotationProduct().setProductId(forClass);
			}


	
	
				Integer quotationId = WebUtil.getParameterInteger (request,"quotationId");
		
			if(quotationId!=null){
				Quotation forClass = new Quotation();
				forClass.setQuotationId(quotationId);
				tmp.setQuotationId(""+quotationId);

				Quotation quotation = this.quotationService.get(quotationId);
				tmp.getQuotationProduct().setQuotationId(quotation);
			}else{
				Quotation forClass = new Quotation();
//				tmp.setQuotationId("");
				tmp.getQuotationProduct().setQuotationId(forClass);
			}


		// -- foreign affairs end



		}
																										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		QuotationProductForm quotationProductForm = ( QuotationProductForm ) command;
		QuotationProduct quotationProduct = quotationProductForm.getQuotationProduct();

//		errors.setNestedPath("quotationProduct");
//		getValidator().validate(quotationProduct, errors);
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

		QuotationProductForm quotationProductForm = ( QuotationProductForm ) command;

		QuotationProduct res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(quotationProductForm.getProductId() != null){
				quotationProductForm.getQuotationProduct().setProductId(
					this.productService.get(
						new Integer((quotationProductForm.getProductId()))
						)
				);
			}
	
					if(quotationProductForm.getQuotationId() != null){
				quotationProductForm.getQuotationProduct().setQuotationId(
					this.quotationService.get(
						new Integer((quotationProductForm.getQuotationId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);
			
			if (quotationProductForm.isNewQuotationProduct ()) {
				res = quotationProductService.create (quotationProductForm.getQuotationProduct(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.quotationproduct",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.quotationproduct",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = quotationProductService.update (quotationProductForm.getQuotationProduct(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.quotationproduct",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.quotationproduct",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (quotationProductForm.isNewQuotationProduct ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.quotationproduct",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.quotationproduct",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("quotationproduct"+"?alert="+alertMsg));
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
