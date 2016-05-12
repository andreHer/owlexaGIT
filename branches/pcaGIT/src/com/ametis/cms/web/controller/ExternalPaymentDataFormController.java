
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
import com.ametis.cms.datamodel.ExternalPaymentData;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ExternalPaymentDataService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ExternalPaymentDataForm;



// imports+ 

// imports- 

/**
 * ExternalPaymentData is a mapping of external_payment_data Table.
*/
public class ExternalPaymentDataFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ExternalPaymentDataService externalPaymentDataService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
		
	// -- foreign affairs end

private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public void setExternalPaymentDataService (ExternalPaymentDataService object){
	    this.externalPaymentDataService = object;
	}
	public ExternalPaymentDataService getExternalPaymentDataService (){
	    return this.externalPaymentDataService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getActionUserService() {
		return actionuserService;
	}
	public void setActionUserService(UserService userService) {
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

	
	public ExternalPaymentDataFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ExternalPaymentDataForm tmp = null;
						Long id = WebUtil.getParameterLong (request, "id");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								id != null
				) {
						java.io.Serializable pkey = id;
						ExternalPaymentData object = externalPaymentDataService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ExternalPaymentDataForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ExternalPaymentDataForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ExternalPaymentDataForm();
					 // foreign affairs
			// -- foreign affairs end



		}
												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ExternalPaymentDataForm externalPaymentDataForm = ( ExternalPaymentDataForm ) command;
		ExternalPaymentData externalPaymentData = externalPaymentDataForm.getExternalPaymentData();

//		errors.setNestedPath("externalPaymentData");
//		getValidator().validate(externalPaymentData, errors);
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

		ExternalPaymentDataForm externalPaymentDataForm = ( ExternalPaymentDataForm ) command;

		ExternalPaymentData res = null;
		String alertMsg="";
		try {
		// foreign affairs
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
			
			if (externalPaymentDataForm.isNewExternalPaymentData ()) {
				res = externalPaymentDataService.create (externalPaymentDataForm.getExternalPaymentData(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.externalpaymentdata",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.externalpaymentdata",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = externalPaymentDataService.update (externalPaymentDataForm.getExternalPaymentData(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.externalpaymentdata",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.externalpaymentdata",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (externalPaymentDataForm.isNewExternalPaymentData ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.externalpaymentdata",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.externalpaymentdata",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("externalpaymentdata"+"?alert="+alertMsg));
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
