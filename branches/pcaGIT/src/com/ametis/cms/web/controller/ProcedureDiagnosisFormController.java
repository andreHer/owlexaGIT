
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
 * ProcedureDiagnosis is a mapping of procedure_diagnosis Table.
*/
public class ProcedureDiagnosisFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ProcedureDiagnosisService procedureDiagnosisService ;
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
				ProcedureService medicalProcedureService;

	public void setMedicalProcedureService(ProcedureService obj){
		this.medicalProcedureService = obj;
	}

	public ProcedureService getMedicalProcedureService(){
		return this.medicalProcedureService;
	}
			
	// -- foreign affairs end


	public void setProcedureDiagnosisService (ProcedureDiagnosisService object){
	    this.procedureDiagnosisService = object;
	}
	public ProcedureDiagnosisService getProcedureDiagnosisService (){
	    return this.procedureDiagnosisService;
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

	
	public ProcedureDiagnosisFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProcedureDiagnosisForm tmp = null;
						Integer procedureDiagnosisId = WebUtil.getParameterInteger (request,"procedureDiagnosisId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								procedureDiagnosisId != null
				) {
						java.io.Serializable pkey = procedureDiagnosisId;
						ProcedureDiagnosis object = procedureDiagnosisService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProcedureDiagnosisForm(object);
			 // foreign affairs
	
				if (object.getDiagnosisId() != null){
					tmp.setDiagnosisId(""+
						object.getDiagnosisId().getDiagnosisId()
					);
				}


	
				if (object.getProcedureId() != null){
					tmp.setProcedureId(""+
						object.getProcedureId().getProcedureId()
					);
				}


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProcedureDiagnosisForm();
					 // foreign affairs
	
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getProcedureDiagnosis().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getProcedureDiagnosis().setDiagnosisId(forClass);
			}


	
	
				Integer procedureId = WebUtil.getParameterInteger (request,"procedureId");
		
			if(procedureId!=null){
				Procedure forClass = new Procedure();
				forClass.setProcedureId(procedureId);
				tmp.setProcedureId(""+procedureId);

				Procedure medicalProcedure = this.medicalProcedureService.get(procedureId);
				tmp.getProcedureDiagnosis().setProcedureId(medicalProcedure);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProcedureDiagnosisForm();
					 // foreign affairs
		
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getProcedureDiagnosis().setDiagnosisId(diagnosis);
			}


	
	
				Integer procedureId = WebUtil.getParameterInteger (request,"procedureId");
		
			if(procedureId!=null){
				Procedure forClass = new Procedure();
				forClass.setProcedureId(procedureId);
				tmp.setProcedureId(""+procedureId);

				Procedure medicalProcedure = this.medicalProcedureService.get(procedureId);
				tmp.getProcedureDiagnosis().setProcedureId(medicalProcedure);
			}

		// -- foreign affairs end



		}
																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProcedureDiagnosisForm procedureDiagnosisForm = ( ProcedureDiagnosisForm ) command;
		ProcedureDiagnosis procedureDiagnosis = procedureDiagnosisForm.getProcedureDiagnosis();

//		errors.setNestedPath("procedureDiagnosis");
//		getValidator().validate(procedureDiagnosis, errors);
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

		ProcedureDiagnosisForm procedureDiagnosisForm = ( ProcedureDiagnosisForm ) command;

		ProcedureDiagnosis res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(procedureDiagnosisForm.getDiagnosisId() != null){
				procedureDiagnosisForm.getProcedureDiagnosis().setDiagnosisId(
					this.diagnosisService.get(
						new Integer((procedureDiagnosisForm.getDiagnosisId()))
						)
				);
			}
	
					if(procedureDiagnosisForm.getProcedureId() != null){
				procedureDiagnosisForm.getProcedureDiagnosis().setProcedureId(
					this.medicalProcedureService.get(
						new Integer((procedureDiagnosisForm.getProcedureId()))
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
			
			if (procedureDiagnosisForm.isNewProcedureDiagnosis ()) {
				res = procedureDiagnosisService.create (procedureDiagnosisForm.getProcedureDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.procedurediagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.procedurediagnosis",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = procedureDiagnosisService.update (procedureDiagnosisForm.getProcedureDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.procedurediagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.procedurediagnosis",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (procedureDiagnosisForm.isNewProcedureDiagnosis ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.procedurediagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.procedurediagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("procedurediagnosis"+"?alert="+alertMsg));
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
