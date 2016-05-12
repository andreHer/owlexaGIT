
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
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.ItemCategoryMap;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ItemCategoryMapService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ItemCategoryMapForm;



// imports+ 

// imports- 

/**
 * ItemCategoryMap is a mapping of item_category_map Table.
*/
public class ItemCategoryMapFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ItemCategoryMapService itemCategoryMapService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
			CaseCategoryService caseCategoryService;
	
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}

	public void setCaseCategoryService(CaseCategoryService obj){
		this.caseCategoryService = obj;
	}

	public CaseCategoryService getCaseCategoryService(){
		return this.caseCategoryService;
	}
				ItemService itemService;

	public void setItemService(ItemService obj){
		this.itemService = obj;
	}

	public ItemService getItemService(){
		return this.itemService;
	}
				ItemCategoryService itemCategoryService;

	public void setItemCategoryService(ItemCategoryService obj){
		this.itemCategoryService = obj;
	}

	public ItemCategoryService getItemCategoryService(){
		return this.itemCategoryService;
	}
			
	// -- foreign affairs end


	public void setItemCategoryMapService (ItemCategoryMapService object){
	    this.itemCategoryMapService = object;
	}
	public ItemCategoryMapService getItemCategoryMapService (){
	    return this.itemCategoryMapService;
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


	public ItemCategoryMapFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ItemCategoryMapForm tmp = null;
						Integer itemCategoryMapId = WebUtil.getParameterInteger (request,"itemCategoryMapId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								itemCategoryMapId != null
				) {
						java.io.Serializable pkey = itemCategoryMapId;
						ItemCategoryMap object = itemCategoryMapService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ItemCategoryMapForm(object);
			 // foreign affairs
	
				tmp.setCaseCategoryId(""+
					object.getCaseCategoryId().getCaseCategoryId()
				);


	
				tmp.setItemId(""+
					object.getItemId().getItemId()
				);


	
				tmp.setItemCategoryId(""+
					object.getItemCategoryId().getItemCategoryId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ItemCategoryMapForm();
					 // foreign affairs
	
	
				Integer caseCategoryId = WebUtil.getParameterInteger (request,"caseCategoryId");
		
			if(caseCategoryId!=null){
				CaseCategory forClass = new CaseCategory();
				forClass.setCaseCategoryId(caseCategoryId);
				tmp.setCaseCategoryId(""+caseCategoryId);

				CaseCategory caseCategory = this.caseCategoryService.get(caseCategoryId);
				tmp.getItemCategoryMap().setCaseCategoryId(caseCategory);
			}else{
				CaseCategory forClass = new CaseCategory();
//				tmp.setCaseCategoryId("");
				tmp.getItemCategoryMap().setCaseCategoryId(forClass);
			}


	
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getItemCategoryMap().setItemId(item);
			}else{
				Item forClass = new Item();
//				tmp.setItemId("");
				tmp.getItemCategoryMap().setItemId(forClass);
			}


	
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getItemCategoryMap().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getItemCategoryMap().setItemCategoryId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ItemCategoryMapForm();
					 // foreign affairs
		
	
				Integer caseCategoryId = WebUtil.getParameterInteger (request,"caseCategoryId");
		
			if(caseCategoryId!=null){
				CaseCategory forClass = new CaseCategory();
				forClass.setCaseCategoryId(caseCategoryId);
				tmp.setCaseCategoryId(""+caseCategoryId);

				CaseCategory caseCategory = this.caseCategoryService.get(caseCategoryId);
				tmp.getItemCategoryMap().setCaseCategoryId(caseCategory);
			}else{
				CaseCategory forClass = new CaseCategory();
//				tmp.setCaseCategoryId("");
				tmp.getItemCategoryMap().setCaseCategoryId(forClass);
			}


	
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getItemCategoryMap().setItemId(item);
			}else{
				Item forClass = new Item();
//				tmp.setItemId("");
				tmp.getItemCategoryMap().setItemId(forClass);
			}


	
	
				Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
		
			if(itemCategoryId!=null){
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(""+itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService.get(itemCategoryId);
				tmp.getItemCategoryMap().setItemCategoryId(itemCategory);
			}else{
				ItemCategory forClass = new ItemCategory();
//				tmp.setItemCategoryId("");
				tmp.getItemCategoryMap().setItemCategoryId(forClass);
			}


		// -- foreign affairs end



		}
												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ItemCategoryMapForm itemCategoryMapForm = ( ItemCategoryMapForm ) command;
		ItemCategoryMap itemCategoryMap = itemCategoryMapForm.getItemCategoryMap();

//		errors.setNestedPath("itemCategoryMap");
//		getValidator().validate(itemCategoryMap, errors);
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

		ItemCategoryMapForm itemCategoryMapForm = ( ItemCategoryMapForm ) command;

		ItemCategoryMap res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(itemCategoryMapForm.getCaseCategoryId() != null){
				itemCategoryMapForm.getItemCategoryMap().setCaseCategoryId(
					this.caseCategoryService.get(
						new Integer((itemCategoryMapForm.getCaseCategoryId()))
						)
				);
			}
	
					if(itemCategoryMapForm.getItemId() != null){
				itemCategoryMapForm.getItemCategoryMap().setItemId(
					this.itemService.get(
						new Integer((itemCategoryMapForm.getItemId()))
						)
				);
			}
	
					if(itemCategoryMapForm.getItemCategoryId() != null){
				itemCategoryMapForm.getItemCategoryMap().setItemCategoryId(
					this.itemCategoryService.get(
						new Integer((itemCategoryMapForm.getItemCategoryId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEITEMCATEGORY");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEITEMCATEGORY access");
				return errorResult;
				
			}

			if (itemCategoryMapForm.isNewItemCategoryMap ()) {
				res = itemCategoryMapService.create (itemCategoryMapForm.getItemCategoryMap(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.itemcategorymap",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.itemcategorymap",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = itemCategoryMapService.update (itemCategoryMapForm.getItemCategoryMap(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.itemcategorymap",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.itemcategorymap",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (itemCategoryMapForm.isNewItemCategoryMap ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.itemcategorymap",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.itemcategorymap",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("itemcategorymap"+"?alert="+alertMsg));
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
