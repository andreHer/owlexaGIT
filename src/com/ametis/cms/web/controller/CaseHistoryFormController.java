
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
 * CaseHistory is a mapping of case_history Table.
*/
public class CaseHistoryFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	CaseHistoryService caseHistoryService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	

						
	// -- foreign affairs end


	public void setCaseHistoryService (CaseHistoryService object){
	    this.caseHistoryService = object;
	}
	public CaseHistoryService getCaseHistoryService (){
	    return this.caseHistoryService;
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

	
	public CaseHistoryFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		CaseHistoryForm tmp = null;
						Integer caseHistoryId = WebUtil.getParameterInteger (request,"caseHistoryId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								caseHistoryId != null
				) {
						java.io.Serializable pkey = caseHistoryId;
						CaseHistory object = caseHistoryService.get (pkey );

			 if (object != null){ // edit object

				tmp = new CaseHistoryForm(object);
			 // foreign affairs
	
				tmp.setAfterStatus(""+
					object.getAfterStatus().getCaseStatusId()
				);


	
				tmp.setBeforeStatus(""+
					object.getBeforeStatus().getCaseStatusId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseHistoryForm();
					 // foreign affairs
	

		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CaseHistoryForm();
					 // foreign affairs
		
			// -- foreign affairs end



		}
																																
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		CaseHistoryForm caseHistoryForm = ( CaseHistoryForm ) command;
		CaseHistory caseHistory = caseHistoryForm.getCaseHistory();

//		errors.setNestedPath("caseHistory");
//		getValidator().validate(caseHistory, errors);
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

		CaseHistoryForm caseHistoryForm = ( CaseHistoryForm ) command;

		CaseHistory res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			
			ActionUser user = securityService.getActionUser(request);
			
			if (caseHistoryForm.isNewCaseHistory ()) {
				res = caseHistoryService.create (caseHistoryForm.getCaseHistory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.casehistory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.casehistory",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = caseHistoryService.update (caseHistoryForm.getCaseHistory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.casehistory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.casehistory",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (caseHistoryForm.isNewCaseHistory ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.casehistory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.casehistory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("casehistory"+"?alert="+alertMsg));
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
