
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
 * DiagnosisSetDetail is a mapping of diagnosis_set_detail Table.
*/
public class DiagnosisSetDetailFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	DiagnosisSetDetailService diagnosisSetDetailService ;
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
				DiagnosisSetService diagnosisSetService;

	public void setDiagnosisSetService(DiagnosisSetService obj){
		this.diagnosisSetService = obj;
	}

	public DiagnosisSetService getDiagnosisSetService(){
		return this.diagnosisSetService;
	}
			
	// -- foreign affairs end


	public void setDiagnosisSetDetailService (DiagnosisSetDetailService object){
	    this.diagnosisSetDetailService = object;
	}
	public DiagnosisSetDetailService getDiagnosisSetDetailService (){
	    return this.diagnosisSetDetailService;
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

	
	public DiagnosisSetDetailFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		DiagnosisSetDetailForm tmp = null;
						Integer diagnosisSetDetailId = WebUtil.getParameterInteger (request,"diagnosisSetDetailId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								diagnosisSetDetailId != null
				) {
						java.io.Serializable pkey = diagnosisSetDetailId;
						DiagnosisSetDetail object = diagnosisSetDetailService.get (pkey );

			 if (object != null){ // edit object

				tmp = new DiagnosisSetDetailForm(object);
			 // foreign affairs
	
				tmp.setDiagnosisId(""+
					object.getDiagnosisId().getDiagnosisId()
				);


	
				tmp.setDiagnosisSetId(""+
					object.getDiagnosisSetId().getDiagnosisSetId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new DiagnosisSetDetailForm();
					 // foreign affairs
	
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getDiagnosisSetDetail().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getDiagnosisSetDetail().setDiagnosisId(forClass);
			}


	
	
				Integer diagnosisSetId = WebUtil.getParameterInteger (request,"diagnosisSetId");
		
			if(diagnosisSetId!=null){
				DiagnosisSet forClass = new DiagnosisSet();
				forClass.setDiagnosisSetId(diagnosisSetId);
				tmp.setDiagnosisSetId(""+diagnosisSetId);

				DiagnosisSet diagnosisSet = this.diagnosisSetService.get(diagnosisSetId);
				tmp.getDiagnosisSetDetail().setDiagnosisSetId(diagnosisSet);
			}else{
				DiagnosisSet forClass = new DiagnosisSet();
//				tmp.setDiagnosisSetId("");
				tmp.getDiagnosisSetDetail().setDiagnosisSetId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new DiagnosisSetDetailForm();
					 // foreign affairs
		
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getDiagnosisSetDetail().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getDiagnosisSetDetail().setDiagnosisId(forClass);
			}


	
	
				Integer diagnosisSetId = WebUtil.getParameterInteger (request,"diagnosisSetId");
		
			if(diagnosisSetId!=null){
				DiagnosisSet forClass = new DiagnosisSet();
				forClass.setDiagnosisSetId(diagnosisSetId);
				tmp.setDiagnosisSetId(""+diagnosisSetId);

				DiagnosisSet diagnosisSet = this.diagnosisSetService.get(diagnosisSetId);
				tmp.getDiagnosisSetDetail().setDiagnosisSetId(diagnosisSet);
			}else{
				DiagnosisSet forClass = new DiagnosisSet();
//				tmp.setDiagnosisSetId("");
				tmp.getDiagnosisSetDetail().setDiagnosisSetId(forClass);
			}


		// -- foreign affairs end



		}
																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		DiagnosisSetDetailForm diagnosisSetDetailForm = ( DiagnosisSetDetailForm ) command;
		DiagnosisSetDetail diagnosisSetDetail = diagnosisSetDetailForm.getDiagnosisSetDetail();

//		errors.setNestedPath("diagnosisSetDetail");
//		getValidator().validate(diagnosisSetDetail, errors);
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

		DiagnosisSetDetailForm diagnosisSetDetailForm = ( DiagnosisSetDetailForm ) command;

		DiagnosisSetDetail res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(diagnosisSetDetailForm.getDiagnosisId() != null){
				diagnosisSetDetailForm.getDiagnosisSetDetail().setDiagnosisId(
					this.diagnosisService.get(
						new Integer((diagnosisSetDetailForm.getDiagnosisId()))
						)
				);
			}
	
					if(diagnosisSetDetailForm.getDiagnosisSetId() != null){
				diagnosisSetDetailForm.getDiagnosisSetDetail().setDiagnosisSetId(
					this.diagnosisSetService.get(
						new Integer((diagnosisSetDetailForm.getDiagnosisSetId()))
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
			
			if (diagnosisSetDetailForm.isNewDiagnosisSetDetail ()) {
				res = diagnosisSetDetailService.create (diagnosisSetDetailForm.getDiagnosisSetDetail(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.diagnosissetdetail",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.diagnosissetdetail",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = diagnosisSetDetailService.update (diagnosisSetDetailForm.getDiagnosisSetDetail(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.diagnosissetdetail",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.diagnosissetdetail",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (diagnosisSetDetailForm.isNewDiagnosisSetDetail ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.diagnosissetdetail",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.diagnosissetdetail",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("diagnosissetdetail"+"?alert="+alertMsg));
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
