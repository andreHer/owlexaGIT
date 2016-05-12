
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
 * PolicyProviderDiagnosis is a mapping of policy_provider_diagnosis Table.
*/
public class PolicyProviderDiagnosisFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	PolicyProviderDiagnosisService policyProviderDiagnosisService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			DiagnosisService diagnosisService;

	public void setDiagnosisService(DiagnosisService obj){
		this.diagnosisService = obj;
	}

	public DiagnosisService getDiagnosisService(){
		return this.diagnosisService;
	}
				PolicyProviderService policyProviderService;

	public void setPolicyProviderService(PolicyProviderService obj){
		this.policyProviderService = obj;
	}

	public PolicyProviderService getPolicyProviderService(){
		return this.policyProviderService;
	}
			
	// -- foreign affairs end


	public void setPolicyProviderDiagnosisService (PolicyProviderDiagnosisService object){
	    this.policyProviderDiagnosisService = object;
	}
	public PolicyProviderDiagnosisService getPolicyProviderDiagnosisService (){
	    return this.policyProviderDiagnosisService;
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

	
	public PolicyProviderDiagnosisFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyProviderDiagnosisForm tmp = null;
						Integer policyProviderDiagnosisId = WebUtil.getParameterInteger (request,"policyProviderDiagnosisId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								policyProviderDiagnosisId != null
				) {
						java.io.Serializable pkey = policyProviderDiagnosisId;
						PolicyProviderDiagnosis object = policyProviderDiagnosisService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PolicyProviderDiagnosisForm(object);
			 // foreign affairs
	
				tmp.setDiagnosisId(""+
					object.getDiagnosisId().getDiagnosisId()
				);


	
				tmp.setPolicyProviderId(""+
					object.getPolicyProviderId().getPolicyProviderId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyProviderDiagnosisForm();
					 // foreign affairs
	
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getPolicyProviderDiagnosis().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getPolicyProviderDiagnosis().setDiagnosisId(forClass);
			}


	
	
				Integer policyProviderId = WebUtil.getParameterInteger (request,"policyProviderId");
		
			if(policyProviderId!=null){
				PolicyProvider forClass = new PolicyProvider();
				forClass.setPolicyProviderId(policyProviderId);
				tmp.setPolicyProviderId(""+policyProviderId);

				PolicyProvider policyProvider = this.policyProviderService.get(policyProviderId);
				tmp.getPolicyProviderDiagnosis().setPolicyProviderId(policyProvider);
			}else{
				PolicyProvider forClass = new PolicyProvider();
//				tmp.setPolicyProviderId("");
				tmp.getPolicyProviderDiagnosis().setPolicyProviderId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyProviderDiagnosisForm();
					 // foreign affairs
		
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getPolicyProviderDiagnosis().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getPolicyProviderDiagnosis().setDiagnosisId(forClass);
			}


	
	
				Integer policyProviderId = WebUtil.getParameterInteger (request,"policyProviderId");
		
			if(policyProviderId!=null){
				PolicyProvider forClass = new PolicyProvider();
				forClass.setPolicyProviderId(policyProviderId);
				tmp.setPolicyProviderId(""+policyProviderId);

				PolicyProvider policyProvider = this.policyProviderService.get(policyProviderId);
				tmp.getPolicyProviderDiagnosis().setPolicyProviderId(policyProvider);
			}else{
				PolicyProvider forClass = new PolicyProvider();
//				tmp.setPolicyProviderId("");
				tmp.getPolicyProviderDiagnosis().setPolicyProviderId(forClass);
			}


		// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		PolicyProviderDiagnosisForm policyProviderDiagnosisForm = ( PolicyProviderDiagnosisForm ) command;
		PolicyProviderDiagnosis policyProviderDiagnosis = policyProviderDiagnosisForm.getPolicyProviderDiagnosis();

//		errors.setNestedPath("policyProviderDiagnosis");
//		getValidator().validate(policyProviderDiagnosis, errors);
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

		PolicyProviderDiagnosisForm policyProviderDiagnosisForm = ( PolicyProviderDiagnosisForm ) command;

		PolicyProviderDiagnosis res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(policyProviderDiagnosisForm.getDiagnosisId() != null){
				policyProviderDiagnosisForm.getPolicyProviderDiagnosis().setDiagnosisId(
					this.diagnosisService.get(
						new Integer((policyProviderDiagnosisForm.getDiagnosisId()))
						)
				);
			}
	
					if(policyProviderDiagnosisForm.getPolicyProviderId() != null){
				policyProviderDiagnosisForm.getPolicyProviderDiagnosis().setPolicyProviderId(
					this.policyProviderService.get(
						new Integer((policyProviderDiagnosisForm.getPolicyProviderId()))
						)
				);
			}
	
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
			
			if (policyProviderDiagnosisForm.isNewPolicyProviderDiagnosis ()) {
				res = policyProviderDiagnosisService.create (policyProviderDiagnosisForm.getPolicyProviderDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.policyproviderdiagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policyproviderdiagnosis",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyProviderDiagnosisService.update (policyProviderDiagnosisForm.getPolicyProviderDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policyproviderdiagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policyproviderdiagnosis",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (policyProviderDiagnosisForm.isNewPolicyProviderDiagnosis ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policyproviderdiagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policyproviderdiagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("policyproviderdiagnosis"+"?alert="+alertMsg));
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
