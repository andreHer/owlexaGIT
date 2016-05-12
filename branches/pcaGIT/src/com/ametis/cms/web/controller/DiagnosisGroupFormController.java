
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
import com.ametis.cms.datamodel.DiagnosisGroup;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.DiagnosisGroupService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.DiagnosisGroupForm;;



// imports+ 

// imports- 

/**
 * DiagnosisGroup is a mapping of diagnosis_group Table.
*/
public class DiagnosisGroupFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	DiagnosisGroupService diagnosisGroupService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
		
	// -- foreign affairs end


	public void setDiagnosisGroupService (DiagnosisGroupService object){
	    this.diagnosisGroupService = object;
	}
	public DiagnosisGroupService getDiagnosisGroupService (){
	    return this.diagnosisGroupService;
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
	
	public void setSecurityService (SecurityService object){
	    this.securityService = object;
	}
	public SecurityService getSecurityService (){
	    return this.securityService;
	}

	
	public DiagnosisGroupFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		DiagnosisGroupForm tmp = null;
						Integer diagnosisGroupId = WebUtil.getParameterInteger (request,"diagnosisGroupId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								diagnosisGroupId != null
				) {
						java.io.Serializable pkey = diagnosisGroupId;
						DiagnosisGroup object = diagnosisGroupService.get (pkey );

			 if (object != null){ // edit object

				tmp = new DiagnosisGroupForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new DiagnosisGroupForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new DiagnosisGroupForm();
					 // foreign affairs
			// -- foreign affairs end



		}
								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		DiagnosisGroupForm diagnosisGroupForm = ( DiagnosisGroupForm ) command;
		DiagnosisGroup diagnosisGroup = diagnosisGroupForm.getDiagnosisGroup();

//		errors.setNestedPath("diagnosisGroup");
//		getValidator().validate(diagnosisGroup, errors);
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

		DiagnosisGroupForm diagnosisGroupForm = ( DiagnosisGroupForm ) command;

		DiagnosisGroup res = null;
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
			
			if (diagnosisGroupForm.isNewDiagnosisGroup ()) {
				res = diagnosisGroupService.create (diagnosisGroupForm.getDiagnosisGroup(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.diagnosisgroup",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.diagnosisgroup",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = diagnosisGroupService.update (diagnosisGroupForm.getDiagnosisGroup(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.diagnosisgroup",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.diagnosisgroup",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (diagnosisGroupForm.isNewDiagnosisGroup ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.diagnosisgroup",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.diagnosisgroup",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("diagnosisgroup"+"?alert="+alertMsg));
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
