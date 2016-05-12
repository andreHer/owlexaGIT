
package com.ametis.cms.web.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemService; //adq
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ItemCategoryForm;



// imports+ 

// imports- 


/**
 * ItemCategory is a mapping of item_category Table.
*/
public class ItemCategoryFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ItemCategoryService itemCategoryService ;
	
	ItemService itemService ;
	
	ResourceBundleMessageSource alertProperties ;
	private CaseCategoryService caseCategoryService;
	// foreign affairs
	
		
	// -- foreign affairs end
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

	public void setItemCategoryService (ItemCategoryService object){
	    this.itemCategoryService = object;
	}
	public ItemCategoryService getItemCategoryService (){
	    return this.itemCategoryService;
	}
		// generate by default
		private UserService  userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}


	public ItemCategoryFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ItemCategoryForm tmp = null;
						Integer itemCategoryId = WebUtil.getParameterInteger (request,"itemCategoryId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								itemCategoryId != null
				) {
						java.io.Serializable pkey = itemCategoryId;
						ItemCategory object = itemCategoryService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ItemCategoryForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ItemCategoryForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ItemCategoryForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ItemCategoryForm itemCategoryForm = ( ItemCategoryForm ) command;
		ItemCategory itemCategory = itemCategoryForm.getItemCategory();

//		errors.setNestedPath("itemCategory");
//		getValidator().validate(itemCategory, errors);
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
		
		Collection<CaseCategory> ccList = caseCategoryService.getAll();
		
		model.put("caseCategoryList", ccList);

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ItemCategoryForm itemCategoryForm = ( ItemCategoryForm ) command;

		ItemCategory res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (itemCategoryForm.isNewItemCategory ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEITEMCATEGORY");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEITEMCATEGORY access");
				return errorResult;
				
			}
				//res = itemCategoryService.create(itemCategoryForm.getItemCategory(),user);
			
			    //add by adq check claim item already Exist 20102015
				String codeClaimItem = null;
				String getFormCodeClaimItem = null;
				getFormCodeClaimItem = itemCategoryForm.getItemCategoryCode();
				codeClaimItem = itemCategoryService.getCode(getFormCodeClaimItem);
				
                if(getFormCodeClaimItem.equalsIgnoreCase(codeClaimItem)){
                //JOptionPane.showMessageDialog(null, " Category Code is already Exist ");
				}
                else{	       	           
	             res = itemCategoryService.create(itemCategoryForm.getItemCategory(),user);
                }
                //end add adq 20102015
				
				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.itemcategory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.itemcategory",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEITEMCATEGORY");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEITEMCATEGORY access");
				return errorResult;
				
			}
				res = itemCategoryService.update (itemCategoryForm.getItemCategory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.itemcategory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.itemcategory",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (itemCategoryForm.isNewItemCategory ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.itemcategory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.itemcategory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("itemcategory"+"?alert="+alertMsg));
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
