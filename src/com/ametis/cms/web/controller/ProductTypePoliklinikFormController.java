
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
 * ProductTypePoliklinik is a mapping of product_type_poliklinik Table.
*/
public class ProductTypePoliklinikFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ProductTypePoliklinikService productTypePoliklinikService ;
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
				PoliklinikService poliklinikService;

	public void setPoliklinikService(PoliklinikService obj){
		this.poliklinikService = obj;
	}

	public PoliklinikService getPoliklinikService(){
		return this.poliklinikService;
	}
			
	// -- foreign affairs end


	public void setProductTypePoliklinikService (ProductTypePoliklinikService object){
	    this.productTypePoliklinikService = object;
	}
	public ProductTypePoliklinikService getProductTypePoliklinikService (){
	    return this.productTypePoliklinikService;
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

	
	public ProductTypePoliklinikFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProductTypePoliklinikForm tmp = null;
						Integer productTypePoliklinikId = WebUtil.getParameterInteger (request,"productTypePoliklinikId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								productTypePoliklinikId != null
				) {
						java.io.Serializable pkey = productTypePoliklinikId;
						ProductTypePoliklinik object = productTypePoliklinikService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProductTypePoliklinikForm(object);
			 // foreign affairs
	
				tmp.setProductTypeId(""+
					object.getProductTypeId().getProductTypeId()
				);


	
				tmp.setPoliklinikId(""+
					object.getPoliklinikId().getPoliklinikId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductTypePoliklinikForm();
					 // foreign affairs
	
	
				Integer productTypeId = WebUtil.getParameterInteger (request,"productTypeId");
		
			if(productTypeId!=null){
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(productTypeId);
				tmp.setProductTypeId(""+productTypeId);

				ProductType productType = this.productTypeService.get(productTypeId);
				tmp.getProductTypePoliklinik().setProductTypeId(productType);
			}else{
				ProductType forClass = new ProductType();
//				tmp.setProductTypeId("");
				tmp.getProductTypePoliklinik().setProductTypeId(forClass);
			}


	
	
				Integer poliklinikId = WebUtil.getParameterInteger (request,"poliklinikId");
		
			if(poliklinikId!=null){
				Poliklinik forClass = new Poliklinik();
				forClass.setPoliklinikId(poliklinikId);
				tmp.setPoliklinikId(""+poliklinikId);

				Poliklinik poliklinik = this.poliklinikService.get(poliklinikId);
				tmp.getProductTypePoliklinik().setPoliklinikId(poliklinik);
			}else{
				Poliklinik forClass = new Poliklinik();
//				tmp.setPoliklinikId("");
				tmp.getProductTypePoliklinik().setPoliklinikId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProductTypePoliklinikForm();
					 // foreign affairs
		
	
				Integer productTypeId = WebUtil.getParameterInteger (request,"productTypeId");
		
			if(productTypeId!=null){
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(productTypeId);
				tmp.setProductTypeId(""+productTypeId);

				ProductType productType = this.productTypeService.get(productTypeId);
				tmp.getProductTypePoliklinik().setProductTypeId(productType);
			}else{
				ProductType forClass = new ProductType();
//				tmp.setProductTypeId("");
				tmp.getProductTypePoliklinik().setProductTypeId(forClass);
			}


	
	
				Integer poliklinikId = WebUtil.getParameterInteger (request,"poliklinikId");
		
			if(poliklinikId!=null){
				Poliklinik forClass = new Poliklinik();
				forClass.setPoliklinikId(poliklinikId);
				tmp.setPoliklinikId(""+poliklinikId);

				Poliklinik poliklinik = this.poliklinikService.get(poliklinikId);
				tmp.getProductTypePoliklinik().setPoliklinikId(poliklinik);
			}else{
				Poliklinik forClass = new Poliklinik();
//				tmp.setPoliklinikId("");
				tmp.getProductTypePoliklinik().setPoliklinikId(forClass);
			}


		// -- foreign affairs end



		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProductTypePoliklinikForm productTypePoliklinikForm = ( ProductTypePoliklinikForm ) command;
		ProductTypePoliklinik productTypePoliklinik = productTypePoliklinikForm.getProductTypePoliklinik();

//		errors.setNestedPath("productTypePoliklinik");
//		getValidator().validate(productTypePoliklinik, errors);
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

		ProductTypePoliklinikForm productTypePoliklinikForm = ( ProductTypePoliklinikForm ) command;

		ProductTypePoliklinik res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(productTypePoliklinikForm.getProductTypeId() != null){
				productTypePoliklinikForm.getProductTypePoliklinik().setProductTypeId(
					this.productTypeService.get(
						new Integer((productTypePoliklinikForm.getProductTypeId()))
						)
				);
			}
	
					if(productTypePoliklinikForm.getPoliklinikId() != null){
				productTypePoliklinikForm.getProductTypePoliklinik().setPoliklinikId(
					this.poliklinikService.get(
						new Integer((productTypePoliklinikForm.getPoliklinikId()))
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
			
			if (productTypePoliklinikForm.isNewProductTypePoliklinik ()) {
				res = productTypePoliklinikService.create (productTypePoliklinikForm.getProductTypePoliklinik(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.producttypepoliklinik",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.producttypepoliklinik",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = productTypePoliklinikService.update (productTypePoliklinikForm.getProductTypePoliklinik(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.producttypepoliklinik",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.producttypepoliklinik",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (productTypePoliklinikForm.isNewProductTypePoliklinik ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.producttypepoliklinik",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.producttypepoliklinik",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("producttypepoliklinik"+"?alert="+alertMsg));
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
