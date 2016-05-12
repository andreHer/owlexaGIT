
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
import com.ametis.cms.datamodel.Clausul;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductClausul;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClausulService;
import com.ametis.cms.service.ProductClausulService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProductClausulForm;



// imports+ 

// imports- 


/**
 * ProductClausul is a mapping of product_clausul Table.
*/
public class ProductClausulFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ProductClausulService productClausulService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
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
			ProductService productService;

	public void setProductService(ProductService obj){
		this.productService = obj;
	}

	public ProductService getProductService(){
		return this.productService;
	}
				ClausulService clausulService;

	public void setClausulService(ClausulService obj){
		this.clausulService = obj;
	}

	public ClausulService getClausulService(){
		return this.clausulService;
	}
			
	// -- foreign affairs end


	public void setProductClausulService (ProductClausulService object){
	    this.productClausulService = object;
	}
	public ProductClausulService getProductClausulService (){
	    return this.productClausulService;
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


	public ProductClausulFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProductClausulForm tmp = null;
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		Integer productClausulId = WebUtil.getParameterInteger (request,"productClausulId");
		String currentNavigation =  WebUtil.getParameterString(request, "currentNavigation", "");
		Integer index = WebUtil.getParameterInteger(request, "index");
		String searchtext = WebUtil.getParameterString(request,
				"searchtext", "");
		String navigation = WebUtil.getParameterString(request,
				"navigation", "");
		String searchby = WebUtil.getParameterString(request,
				"searchby", "");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								productClausulId != null
				) {
						java.io.Serializable pkey = productClausulId;
						ProductClausul object = productClausulService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProductClausulForm(object);
			 // foreign affairs
	
				tmp.setProductId(""+
					object.getProductId().getProductId()
				);

				tmp.setProductName(object.getProductId().getProductName());
	
				tmp.setClausulId(""+
					object.getClausulId().getClausulId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductClausulForm();
					 // foreign affairs
	
	
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				Product product = this.productService.get(productId);
				tmp.getProductClausul().setProductId(product);
			}else{
				Product forClass = new Product();
//				tmp.setProductId("");
				tmp.getProductClausul().setProductId(forClass);
			}


	
	
				Integer clausulId = WebUtil.getParameterInteger (request,"clausulId");
		
			if(clausulId!=null){
				Clausul forClass = new Clausul();
				forClass.setClausulId(clausulId);
				tmp.setClausulId(""+clausulId);

				Clausul clausul = this.clausulService.get(clausulId);
				tmp.getProductClausul().setClausulId(clausul);
				if (clausul != null){
					tmp.setClausulName(clausul.getClausulName());
				}
			}else{
				
			}

			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProductClausulForm();
					 // foreign affairs
		
	
		
			if(productId!=null){
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId(""+productId);

				
				Product product = this.productService.get(productId);
				tmp.getProductClausul().setProductId(product);
				if (product != null){
					tmp.setProductName(product.getProductName());
				}
				
			}else{
				
			}


	
	
			Integer clausulId = WebUtil.getParameterInteger (request,"clausulId");
			
			if(clausulId!=null){
				Clausul forClass = new Clausul();
				forClass.setClausulId(clausulId);
				tmp.setClausulId(""+clausulId);
	
				Clausul clausul = this.clausulService.get(clausulId);
				tmp.getProductClausul().setClausulId(clausul);
				if (clausul != null){
					tmp.setClausulName(clausul.getClausulName());
				}
			}
		}
		
	
		String breadcrumb="";
		
		if(navigation.equalsIgnoreCase("list")){
			breadcrumb = "<a href=\"productclausul?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Clausul</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Clausul";
		}
		if(navigation.equalsIgnoreCase("listexdiagnosis")){
			breadcrumb = "<a href=\"productclausul?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Diagnosis Exclusion</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Diagnosis Exclusion";
		}	
		
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("currentNavigation", currentNavigation);
		request.setAttribute("productId", productId);
									
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {

		Integer productId = WebUtil.getParameterInteger(request, "productId");
		String navigation = WebUtil.getParameterString(request, "navigation","");
		String currentNavigation = WebUtil.getParameterString(request, "currentNavigation","");
		
		ProductClausulForm productClausulForm = ( ProductClausulForm ) command;
		ProductClausul productClausul = productClausulForm.getProductClausul();
		errors.printStackTrace();
		
		String breadcrumb="";
		
		if(navigation.equalsIgnoreCase("list")){
			breadcrumb = "<a href=\"productclausul?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Clausul</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Clausul";
			System.out.println("list navigation : "+ navigation);
		}
		if(navigation.equalsIgnoreCase("listexdiagnosis")){
			breadcrumb = "<a href=\"productclausul?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Diagnosis Exclusion</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Diagnosis Exclusion";
			System.out.println("listexdiagnosis navigation : "+ navigation);
		}
		
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("currentNavigation", currentNavigation);
		request.setAttribute("productId", productId);
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		String currentNavigation =  WebUtil.getParameterString(request, "currentNavigation", "");
		
		model.put("currentNavigation", currentNavigation);
		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		String navigation = WebUtil.getParameterString(request, "navigation","");
		String currentNavigation = WebUtil.getParameterString(request, "currentNavigation", "");

		ProductClausulForm productClausulForm = ( ProductClausulForm ) command;

		ProductClausul res = null;
		String alertMsg="";
		ModelAndView redirectView = null;
		
		try {
		// foreign affairs
					if(productClausulForm.getProductId() != null){
				productClausulForm.getProductClausul().setProductId(
					this.productService.get(
						new Integer((productClausulForm.getProductId()))
						)
				);
			}
	
					if(productClausulForm.getClausulId() != null){
				productClausulForm.getProductClausul().setClausulId(
					this.clausulService.get(
						new Integer((productClausulForm.getClausulId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (productClausulForm.isNewProductClausul ()) {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEPRODUCTCLAUSUL");
			
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEPRODUCTCLAUSUL access");
					return errorResult;
					
				}
				
				String clausulName = productClausulForm.getClausulName();
				String clausulId = productClausulForm.getClausulId();
				
				if ((clausulId == null || clausulId.equalsIgnoreCase("") )&& clausulName != null && !clausulName.equalsIgnoreCase("")){
					String[] eqParam = {"clausulName"};
					Object[] eqValue = {clausulName};
					
					int total = clausulService.getTotal(null,null,eqParam,eqValue);
					
					if (total == 0){
						Clausul clausul = new Clausul();
						clausul.setClausulName(clausulName);
						clausul.setDeletedStatus(0);
						
						Clausul clause = clausulService.create(clausul, user);
						
						if (clause != null){
							productClausulForm.getProductClausul().setClausulId(clause);
						}
					}
				}
				
				res = productClausulService.create (productClausulForm.getProductClausul(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.productclausul",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.productclausul",null,"fail",Locale.getDefault());
				}
				redirectView = new ModelAndView(new RedirectView("productclausul?navigation=list&productId="+res.getProductId().getProductId()));
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEPRODUCTCLAUSUL");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEPRODUCTCLAUSUL access");
				return errorResult;
				
			}
				res = productClausulService.update (productClausulForm.getProductClausul(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.productclausul",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.productclausul",null,"fail",Locale.getDefault());
				}

				redirectView = new ModelAndView(new RedirectView("productclausul?navigation=list&productId="+productClausulForm.getProductId()));
			}
		}catch (Exception ex) {
			if (productClausulForm.isNewProductClausul ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.productclausul",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.productclausul",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb="";
		
		if(navigation.equalsIgnoreCase("list")){
			breadcrumb = "<a href=\"productclausul?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Clausul</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Clausul";
		}
		if(navigation.equalsIgnoreCase("listexdiagnosis")){
			breadcrumb = "<a href=\"productclausul?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Diagnosis Exclusion</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Diagnosis Exclusion";
		}	
		
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("currentNavigation", currentNavigation);
		request.setAttribute("productId", productId);
		
		return redirectView ;
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
