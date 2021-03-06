
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
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.ItemMeasurementUnit;
import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemMeasurementUnitService;
import com.ametis.cms.service.MeasurementUnitService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ItemMeasurementUnitForm;



// imports+ 

// imports- 


/**
 * ItemMeasurementUnit is a mapping of item_measurement_unit Table.
*/
public class ItemMeasurementUnitFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ItemMeasurementUnitService itemMeasurementUnitService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			ItemCategoryService itemCategoryService;
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setItemCategoryService(ItemCategoryService obj){
		this.itemCategoryService = obj;
	}

	public ItemCategoryService getItemCategoryService(){
		return this.itemCategoryService;
	}
				MeasurementUnitService measurementUnitService;

	public void setMeasurementUnitService(MeasurementUnitService obj){
		this.measurementUnitService = obj;
	}

	public MeasurementUnitService getMeasurementUnitService(){
		return this.measurementUnitService;
	}
			
	// -- foreign affairs end


	public void setItemMeasurementUnitService (ItemMeasurementUnitService object){
	    this.itemMeasurementUnitService = object;
	}
	public ItemMeasurementUnitService getItemMeasurementUnitService (){
	    return this.itemMeasurementUnitService;
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


	public ItemMeasurementUnitFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ItemMeasurementUnitForm tmp = null;
						Integer itemMeasurementUnitId = WebUtil.getParameterInteger (request,"itemMeasurementUnitId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								itemMeasurementUnitId != null
				) {
						java.io.Serializable pkey = itemMeasurementUnitId;
						ItemMeasurementUnit object = itemMeasurementUnitService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ItemMeasurementUnitForm(object);
			 // foreign affairs
	
				tmp.setItemCategoryId(""+
					object.getItemCategoryId().getItemCategoryId()
				);


	
				tmp.setMeasurementUnitId(""+
					object.getMeasurementUnitId().getMeasurementUnitId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ItemMeasurementUnitForm();
					 // foreign affairs
	
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getItemMeasurementUnit().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getItemMeasurementUnit().setItemCategoryId(forClass);
			}


	
	
				Integer measurementUnitId = WebUtil.getParameterInteger (request,"measurementUnitId");
		
			if(measurementUnitId!=null){
				MeasurementUnit forClass = new MeasurementUnit();
				forClass.setMeasurementUnitId(measurementUnitId);
				tmp.setMeasurementUnitId(""+measurementUnitId);

				MeasurementUnit measurementUnit = this.measurementUnitService.get(measurementUnitId);
				tmp.getItemMeasurementUnit().setMeasurementUnitId(measurementUnit);
			}else{
				MeasurementUnit forClass = new MeasurementUnit();
//				tmp.setMeasurementUnitId("");
				tmp.getItemMeasurementUnit().setMeasurementUnitId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ItemMeasurementUnitForm();
					 // foreign affairs
		
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getItemMeasurementUnit().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getItemMeasurementUnit().setItemCategoryId(forClass);
			}


	
	
				Integer measurementUnitId = WebUtil.getParameterInteger (request,"measurementUnitId");
		
			if(measurementUnitId!=null){
				MeasurementUnit forClass = new MeasurementUnit();
				forClass.setMeasurementUnitId(measurementUnitId);
				tmp.setMeasurementUnitId(""+measurementUnitId);

				MeasurementUnit measurementUnit = this.measurementUnitService.get(measurementUnitId);
				tmp.getItemMeasurementUnit().setMeasurementUnitId(measurementUnit);
			}else{
				MeasurementUnit forClass = new MeasurementUnit();
//				tmp.setMeasurementUnitId("");
				tmp.getItemMeasurementUnit().setMeasurementUnitId(forClass);
			}


		// -- foreign affairs end



		}
																						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ItemMeasurementUnitForm itemMeasurementUnitForm = ( ItemMeasurementUnitForm ) command;
		ItemMeasurementUnit itemMeasurementUnit = itemMeasurementUnitForm.getItemMeasurementUnit();

//		errors.setNestedPath("itemMeasurementUnit");
//		getValidator().validate(itemMeasurementUnit, errors);
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

		ItemMeasurementUnitForm itemMeasurementUnitForm = ( ItemMeasurementUnitForm ) command;

		ItemMeasurementUnit res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(itemMeasurementUnitForm.getItemCategoryId() != null){
				itemMeasurementUnitForm.getItemMeasurementUnit().setItemCategoryId(
					this.itemCategoryService.get(
						new Integer((itemMeasurementUnitForm.getItemCategoryId()))
						)
				);
			}
	
					if(itemMeasurementUnitForm.getMeasurementUnitId() != null){
				itemMeasurementUnitForm.getItemMeasurementUnit().setMeasurementUnitId(
					this.measurementUnitService.get(
						new Integer((itemMeasurementUnitForm.getMeasurementUnitId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (itemMeasurementUnitForm.isNewItemMeasurementUnit ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEITEMMEASUREMENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEITEMMEASUREMENT access");
				return errorResult;
				
			}
				res = itemMeasurementUnitService.create (itemMeasurementUnitForm.getItemMeasurementUnit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.itemmeasurementunit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.itemmeasurementunit",null,"fail",Locale.getDefault());
				}
			}
			else {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEITEMMEASUREMENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEITEMMEASUREMENT access");
				return errorResult;
				
			}
				res = itemMeasurementUnitService.update (itemMeasurementUnitForm.getItemMeasurementUnit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.itemmeasurementunit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.itemmeasurementunit",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (itemMeasurementUnitForm.isNewItemMeasurementUnit ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.itemmeasurementunit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.itemmeasurementunit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("itemmeasurementunit"+"?alert="+alertMsg));
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
