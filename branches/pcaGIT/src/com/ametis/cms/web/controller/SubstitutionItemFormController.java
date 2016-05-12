
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
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.SubstitutionItem;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CostContainmentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.SubstitutionItemService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.SubstitutionItemForm;



// imports+ 

// imports- 


/**
 * SubstitutionItem is a mapping of substitution_item Table.
*/
public class SubstitutionItemFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	SubstitutionItemService substitutionItemService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			CostContainmentService costContainmentService;
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
	public void setCostContainmentService(CostContainmentService obj){
		this.costContainmentService = obj;
	}

	public CostContainmentService getCostContainmentService(){
		return this.costContainmentService;
	}
			
	// -- foreign affairs end


	public void setSubstitutionItemService (SubstitutionItemService object){
	    this.substitutionItemService = object;
	}
	public SubstitutionItemService getSubstitutionItemService (){
	    return this.substitutionItemService;
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


	public SubstitutionItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		SubstitutionItemForm tmp = null;
						Integer substitutionItemId = WebUtil.getParameterInteger (request,"substitutionItemId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								substitutionItemId != null
				) {
						java.io.Serializable pkey = substitutionItemId;
						SubstitutionItem object = substitutionItemService.get (pkey );

			 if (object != null){ // edit object

				tmp = new SubstitutionItemForm(object);
			 // foreign affairs
	
				tmp.setCostContainmentId(""+
					object.getCostContainmentId().getCostContainmentId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new SubstitutionItemForm();
					 // foreign affairs
	
	
				Integer costContainmentId = WebUtil.getParameterInteger (request,"costContainmentId");
		
			if(costContainmentId!=null){
				CostContainment forClass = new CostContainment();
				forClass.setCostContainmentId(costContainmentId);
				tmp.setCostContainmentId(""+costContainmentId);

				CostContainment costContainment = this.costContainmentService.get(costContainmentId);
				tmp.getSubstitutionItem().setCostContainmentId(costContainment);
			}else{
				CostContainment forClass = new CostContainment();
//				tmp.setCostContainmentId("");
				tmp.getSubstitutionItem().setCostContainmentId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new SubstitutionItemForm();
					 // foreign affairs
		
	
				Integer costContainmentId = WebUtil.getParameterInteger (request,"costContainmentId");
		
			if(costContainmentId!=null){
				CostContainment forClass = new CostContainment();
				forClass.setCostContainmentId(costContainmentId);
				tmp.setCostContainmentId(""+costContainmentId);

				CostContainment costContainment = this.costContainmentService.get(costContainmentId);
				tmp.getSubstitutionItem().setCostContainmentId(costContainment);
			}else{
				CostContainment forClass = new CostContainment();
//				tmp.setCostContainmentId("");
				tmp.getSubstitutionItem().setCostContainmentId(forClass);
			}


		// -- foreign affairs end



		}
																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		SubstitutionItemForm substitutionItemForm = ( SubstitutionItemForm ) command;
		SubstitutionItem substitutionItem = substitutionItemForm.getSubstitutionItem();

//		errors.setNestedPath("substitutionItem");
//		getValidator().validate(substitutionItem, errors);
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

		SubstitutionItemForm substitutionItemForm = ( SubstitutionItemForm ) command;

		SubstitutionItem res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(substitutionItemForm.getCostContainmentId() != null){
				substitutionItemForm.getSubstitutionItem().setCostContainmentId(
					this.costContainmentService.get(
						new Integer((substitutionItemForm.getCostContainmentId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (substitutionItemForm.isNewSubstitutionItem ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATESUBSTITUTIONITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATESUBSTITUTIONITEM access");
				return errorResult;
				
			}
				res = substitutionItemService.create (substitutionItemForm.getSubstitutionItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.substitutionitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.substitutionitem",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATESUBSTITUTIONITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATESUBSTITUTIONITEM access");
				return errorResult;
				
			}
				res = substitutionItemService.update (substitutionItemForm.getSubstitutionItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.substitutionitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.substitutionitem",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (substitutionItemForm.isNewSubstitutionItem ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.substitutionitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.substitutionitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("substitutionitem"+"?alert="+alertMsg));
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
