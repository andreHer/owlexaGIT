
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
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.DiagnosisItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.DiagnosisItemService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.DiagnosisItemForm;



// imports+ 

// imports- 


/**
 * DiagnosisItem is a mapping of diagnosis_item Table.
*/
public class DiagnosisItemFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	DiagnosisItemService diagnosisItemService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			DiagnosisService diagnosisService;
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
	public void setDiagnosisService(DiagnosisService obj){
		this.diagnosisService = obj;
	}

	public DiagnosisService getDiagnosisService(){
		return this.diagnosisService;
	}
				ItemService itemService;

	public void setItemService(ItemService obj){
		this.itemService = obj;
	}

	public ItemService getItemService(){
		return this.itemService;
	}
			
	// -- foreign affairs end


	public void setDiagnosisItemService (DiagnosisItemService object){
	    this.diagnosisItemService = object;
	}
	public DiagnosisItemService getDiagnosisItemService (){
	    return this.diagnosisItemService;
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


	public DiagnosisItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		DiagnosisItemForm tmp = null;
						Integer diagnosisItemId = WebUtil.getParameterInteger (request,"diagnosisItemId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								diagnosisItemId != null
				) {
						java.io.Serializable pkey = diagnosisItemId;
						DiagnosisItem object = diagnosisItemService.get (pkey );

			 if (object != null){ // edit object

				tmp = new DiagnosisItemForm(object);
			 // foreign affairs
	
				tmp.setDiagnosisId(""+
					object.getDiagnosisId().getDiagnosisId()
				);


	
				tmp.setItemId(""+
					object.getItemId().getItemId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new DiagnosisItemForm();
					 // foreign affairs
	
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getDiagnosisItem().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getDiagnosisItem().setDiagnosisId(forClass);
			}


	
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getDiagnosisItem().setItemId(item);
			}else{
				Item forClass = new Item();
//				tmp.setItemId("");
				tmp.getDiagnosisItem().setItemId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new DiagnosisItemForm();
					 // foreign affairs
		
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getDiagnosisItem().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getDiagnosisItem().setDiagnosisId(forClass);
			}


	
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getDiagnosisItem().setItemId(item);
			}else{
				Item forClass = new Item();
//				tmp.setItemId("");
				tmp.getDiagnosisItem().setItemId(forClass);
			}


		// -- foreign affairs end



		}
																						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		DiagnosisItemForm diagnosisItemForm = ( DiagnosisItemForm ) command;
		DiagnosisItem diagnosisItem = diagnosisItemForm.getDiagnosisItem();

//		errors.setNestedPath("diagnosisItem");
//		getValidator().validate(diagnosisItem, errors);
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

		DiagnosisItemForm diagnosisItemForm = ( DiagnosisItemForm ) command;

		DiagnosisItem res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(diagnosisItemForm.getDiagnosisId() != null){
				diagnosisItemForm.getDiagnosisItem().setDiagnosisId(
					this.diagnosisService.get(
						new Integer((diagnosisItemForm.getDiagnosisId()))
						)
				);
			}
	
					if(diagnosisItemForm.getItemId() != null){
				diagnosisItemForm.getDiagnosisItem().setItemId(
					this.itemService.get(
						new Integer((diagnosisItemForm.getItemId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (diagnosisItemForm.isNewDiagnosisItem ()) {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEDIAGNOSISITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEDIAGNOSISITEM access");
				return errorResult;
				
			}
				res = diagnosisItemService.create (diagnosisItemForm.getDiagnosisItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.diagnosisitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.diagnosisitem",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEDIAGNOSISITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEDIAGNOSISITEM access");
				return errorResult;
				
			}
				res = diagnosisItemService.update (diagnosisItemForm.getDiagnosisItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.diagnosisitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.diagnosisitem",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (diagnosisItemForm.isNewDiagnosisItem ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.diagnosisitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.diagnosisitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("diagnosisitem"+"?alert="+alertMsg));
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
