
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
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyPayment;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.PolicyPaymentService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyPaymentForm;



// imports+ 

// imports- 

/**
 * PolicyPayment is a mapping of policy_payment Table.
*/
public class PolicyPaymentFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	PolicyPaymentService policyPaymentService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	SecurityService securityService;
			PolicyService policyService;

			
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

	public void setPolicyService(PolicyService obj){
		this.policyService = obj;
	}

	public PolicyService getPolicyService(){
		return this.policyService;
	}
			
	// -- foreign affairs end


	public void setPolicyPaymentService (PolicyPaymentService object){
	    this.policyPaymentService = object;
	}
	public PolicyPaymentService getPolicyPaymentService (){
	    return this.policyPaymentService;
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


	public PolicyPaymentFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyPaymentForm tmp = null;
						Long policyPaymentId = WebUtil.getParameterLong (request, "policyPaymentId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								policyPaymentId != null
				) {
						java.io.Serializable pkey = policyPaymentId;
						PolicyPayment object = policyPaymentService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PolicyPaymentForm(object);
			 // foreign affairs
	
				tmp.setPolicyId(""+
					object.getPolicyId().getPolicyId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyPaymentForm();
					 // foreign affairs
	
	
				Integer policyId = WebUtil.getParameterInteger (request, "policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyPayment().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getPolicyPayment().setPolicyId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyPaymentForm();
					 // foreign affairs
		
	
				Integer policyId = WebUtil.getParameterInteger (request, "policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyPayment().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getPolicyPayment().setPolicyId(forClass);
			}


		// -- foreign affairs end



		}
						
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		PolicyPaymentForm policyPaymentForm = ( PolicyPaymentForm ) command;
		PolicyPayment policyPayment = policyPaymentForm.getPolicyPayment();

//		errors.setNestedPath("policyPayment");
//		getValidator().validate(policyPayment, errors);
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

		PolicyPaymentForm policyPaymentForm = ( PolicyPaymentForm ) command;

		PolicyPayment res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(policyPaymentForm.getPolicyId() != null){
				policyPaymentForm.getPolicyPayment().setPolicyId(
					this.policyService.get(
						new Long((policyPaymentForm.getPolicyId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);
			
			
			if (policyPaymentForm.isNewPolicyPayment ()) {
				res = policyPaymentService.create (policyPaymentForm.getPolicyPayment(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.policypayment",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policypayment",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyPaymentService.update (policyPaymentForm.getPolicyPayment(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policypayment",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policypayment",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (policyPaymentForm.isNewPolicyPayment ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policypayment",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policypayment",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("policypayment"+"?alert="+alertMsg));
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
