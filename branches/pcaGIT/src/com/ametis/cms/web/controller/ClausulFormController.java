
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
import com.ametis.cms.datamodel.Clausul;
import com.ametis.cms.datamodel.ClausulCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClausulCategoryService;
import com.ametis.cms.service.ClausulService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ClausulForm;



// imports+ 

// imports- 


/**
 * Clausul is a mapping of clausul Table.
*/
public class ClausulFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ClausulService clausulService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			ClausulCategoryService clausulCategoryService;
			private SecurityService securityService;
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
	public void setClausulCategoryService(ClausulCategoryService obj){
		this.clausulCategoryService = obj;
	}

	public ClausulCategoryService getClausulCategoryService(){
		return this.clausulCategoryService;
	}
			
	// -- foreign affairs end


	public void setClausulService (ClausulService object){
	    this.clausulService = object;
	}
	public ClausulService getClausulService (){
	    return this.clausulService;
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


	public ClausulFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ClausulForm tmp = null;
						Integer clausulId = WebUtil.getParameterInteger (request,"clausulId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								clausulId != null
				) {
						java.io.Serializable pkey = clausulId;
						Clausul object = clausulService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ClausulForm(object);
			 // foreign affairs
	
				tmp.setClausulCategoryId(""+
					object.getClausulCategoryId().getClausulCategoryId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClausulForm();
					 // foreign affairs
	
	
				Integer clausulCategoryId = WebUtil.getParameterInteger (request,"clausulCategoryId");
		
			if(clausulCategoryId!=null){
				ClausulCategory forClass = new ClausulCategory();
				forClass.setClausulCategoryId(clausulCategoryId);
				tmp.setClausulCategoryId(""+clausulCategoryId);

				ClausulCategory clausulCategory = this.clausulCategoryService.get(clausulCategoryId);
				tmp.getClausul().setClausulCategoryId(clausulCategory);
			}else{
				ClausulCategory forClass = new ClausulCategory();
//				tmp.setClausulCategoryId("");
				tmp.getClausul().setClausulCategoryId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClausulForm();
					 // foreign affairs
		
	
				Integer clausulCategoryId = WebUtil.getParameterInteger (request,"clausulCategoryId");
		
			if(clausulCategoryId!=null){
				ClausulCategory forClass = new ClausulCategory();
				forClass.setClausulCategoryId(clausulCategoryId);
				tmp.setClausulCategoryId(""+clausulCategoryId);

				ClausulCategory clausulCategory = this.clausulCategoryService.get(clausulCategoryId);
				tmp.getClausul().setClausulCategoryId(clausulCategory);
			}else{
				ClausulCategory forClass = new ClausulCategory();
//				tmp.setClausulCategoryId("");
				tmp.getClausul().setClausulCategoryId(forClass);
			}


		// -- foreign affairs end



		}
																										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ClausulForm clausulForm = ( ClausulForm ) command;
		Clausul clausul = clausulForm.getClausul();

//		errors.setNestedPath("clausul");
//		getValidator().validate(clausul, errors);
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

		Collection<ClausulCategory> clausulCategories = clausulCategoryService.getAll();
		model.put("clausulCategories", clausulCategories);
		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClausulForm clausulForm = ( ClausulForm ) command;

		Clausul res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(clausulForm.getClausulCategoryId() != null){
				clausulForm.getClausul().setClausulCategoryId(
					this.clausulCategoryService.get(
						new Integer((clausulForm.getClausulCategoryId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (clausulForm.isNewClausul ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATECLAUSUL");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATECLAUSUL access");
				return errorResult;
				
			}
				res = clausulService.create (clausulForm.getClausul(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.clausul",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.clausul",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATECLAUSUL");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATECLAUSUL access");
				return errorResult;
				
			}
				res = clausulService.update (clausulForm.getClausul(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.clausul",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.clausul",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (clausulForm.isNewClausul ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.clausul",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.clausul",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("clausul"+"?alert="+alertMsg));
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
