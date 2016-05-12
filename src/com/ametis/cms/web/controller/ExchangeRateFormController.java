
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
 * ExchangeRate is a mapping of exchange_rate Table.
*/
public class ExchangeRateFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ExchangeRateService exchangeRateService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	private CurrencyService currencyService;
	// foreign affairs
	
		
	// -- foreign affairs end


	public void setExchangeRateService (ExchangeRateService object){
	    this.exchangeRateService = object;
	}
	public ExchangeRateService getExchangeRateService (){
	    return this.exchangeRateService;
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

	
	public ExchangeRateFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ExchangeRateForm tmp = null;
						Integer exchangeRateId = WebUtil.getParameterInteger (request,"exchangeRateId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								exchangeRateId != null
				) {
						java.io.Serializable pkey = exchangeRateId;
						ExchangeRate object = exchangeRateService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ExchangeRateForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ExchangeRateForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ExchangeRateForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																																
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ExchangeRateForm exchangeRateForm = ( ExchangeRateForm ) command;
		ExchangeRate exchangeRate = exchangeRateForm.getExchangeRate();
		
		errors.printStackTrace();

//		errors.setNestedPath("exchangeRate");
//		getValidator().validate(exchangeRate, errors);
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
		
		Collection<Currency> currencyList = currencyService.getAll();
		model.put("currencyList", currencyList);

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ExchangeRateForm exchangeRateForm = ( ExchangeRateForm ) command;

		ExchangeRate res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			ActionUser user = securityService.getActionUser(request);
			
			if (exchangeRateForm.isNewExchangeRate ()) {
				res = exchangeRateService.create (exchangeRateForm.getExchangeRate(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.exchangerate",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.exchangerate",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = exchangeRateService.update (exchangeRateForm.getExchangeRate(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.exchangerate",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.exchangerate",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (exchangeRateForm.isNewExchangeRate ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.exchangerate",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.exchangerate",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("exchangerate"+"?alert="+alertMsg));
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
	public CurrencyService getCurrencyService() {
		return currencyService;
	}
	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

// class- 
}
