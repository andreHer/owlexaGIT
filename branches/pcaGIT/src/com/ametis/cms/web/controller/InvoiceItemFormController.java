
package com.ametis.cms.web.controller;

import java.text.NumberFormat;
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
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.InvoiceItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.InvoiceItemService;
import com.ametis.cms.service.InvoiceService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.InvoiceItemForm;



// imports+ 

// imports- 


/**
 * InvoiceItem is a mapping of invoice_item Table.
*/
public class InvoiceItemFormController extends SimpleFormController
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
	InvoiceItemService invoiceItemService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			ItemService itemService;
			private SecurityService securityService;

			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setItemService(ItemService obj){
		this.itemService = obj;
	}

	public ItemService getItemService(){
		return this.itemService;
	}
				InvoiceService invoiceService;

	public void setInvoiceService(InvoiceService obj){
		this.invoiceService = obj;
	}

	public InvoiceService getInvoiceService(){
		return this.invoiceService;
	}
			
	// -- foreign affairs end


	public void setInvoiceItemService (InvoiceItemService object){
	    this.invoiceItemService = object;
	}
	public InvoiceItemService getInvoiceItemService (){
	    return this.invoiceItemService;
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


	public InvoiceItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		InvoiceItemForm tmp = null;
						Integer invoiceItemId = WebUtil.getParameterInteger (request,"invoiceItemId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								invoiceItemId != null
				) {
						java.io.Serializable pkey = invoiceItemId;
						InvoiceItem object = invoiceItemService.get (pkey );

			 if (object != null){ // edit object

				tmp = new InvoiceItemForm(object);
			 // foreign affairs
	
				tmp.setItemId(""+
					object.getItemId().getItemId()
				);


	
				tmp.setInvoiceId(""+
					object.getInvoiceId().getInvoiceId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new InvoiceItemForm();
					 // foreign affairs
	
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getInvoiceItem().setItemId(item);
			}else{
				Item forClass = new Item();
//				tmp.setItemId("");
				tmp.getInvoiceItem().setItemId(forClass);
			}


	
	
				Integer invoiceId = WebUtil.getParameterInteger (request,"invoiceId");
		
			if(invoiceId!=null){
				Invoice forClass = new Invoice();
				forClass.setInvoiceId(invoiceId);
				tmp.setInvoiceId(""+invoiceId);

				Invoice invoice = this.invoiceService.get(invoiceId);
				tmp.getInvoiceItem().setInvoiceId(invoice);
			}else{
				Invoice forClass = new Invoice();
//				tmp.setInvoiceId("");
				tmp.getInvoiceItem().setInvoiceId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new InvoiceItemForm();
					 // foreign affairs
		
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getInvoiceItem().setItemId(item);
			}else{
				Item forClass = new Item();
//				tmp.setItemId("");
				tmp.getInvoiceItem().setItemId(forClass);
			}


	
	
				Integer invoiceId = WebUtil.getParameterInteger (request,"invoiceId");
		
			if(invoiceId!=null){
				Invoice forClass = new Invoice();
				forClass.setInvoiceId(invoiceId);
				tmp.setInvoiceId(""+invoiceId);

				Invoice invoice = this.invoiceService.get(invoiceId);
				tmp.getInvoiceItem().setInvoiceId(invoice);
			}else{
				Invoice forClass = new Invoice();
//				tmp.setInvoiceId("");
				tmp.getInvoiceItem().setInvoiceId(forClass);
			}


		// -- foreign affairs end



		}
																										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		InvoiceItemForm invoiceItemForm = ( InvoiceItemForm ) command;
		InvoiceItem invoiceItem = invoiceItemForm.getInvoiceItem();

//		errors.setNestedPath("invoiceItem");
//		getValidator().validate(invoiceItem, errors);
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

		InvoiceItemForm invoiceItemForm = ( InvoiceItemForm ) command;

		InvoiceItem res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(invoiceItemForm.getItemId() != null){
				invoiceItemForm.getInvoiceItem().setItemId(
					this.itemService.get(
						new Integer((invoiceItemForm.getItemId()))
						)
				);
			}
	
					if(invoiceItemForm.getInvoiceId() != null){
				invoiceItemForm.getInvoiceItem().setInvoiceId(
					this.invoiceService.get(
						new Integer((invoiceItemForm.getInvoiceId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			if (invoiceItemForm.isNewInvoiceItem ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEINVOICEITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEINVOICEITEM access");
				return errorResult;
				
			}
				res = invoiceItemService.create (invoiceItemForm.getInvoiceItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.invoiceitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.invoiceitem",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEINVOICEITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEINVOICEITEM access");
				return errorResult;
				
			}
				res = invoiceItemService.update (invoiceItemForm.getInvoiceItem(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.invoiceitem",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.invoiceitem",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (invoiceItemForm.isNewInvoiceItem ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.invoiceitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.invoiceitem",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("invoiceitem"+"?alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
		
		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
        binder.registerCustomEditor(java.lang.Double.class,
                new CustomNumberEditor (java.lang.Double.class, nf, true));
	}
// class+ 

// class- 

}
