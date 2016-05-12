
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
 * ProcedureMedicine is a mapping of procedure_medicine Table.
*/
public class ProcedureMedicineFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ProcedureMedicineService procedureMedicineService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			MedicineService medicineService;

	public void setMedicineService(MedicineService obj){
		this.medicineService = obj;
	}

	public MedicineService getMedicineService(){
		return this.medicineService;
	}
	ProcedureService medicalProcedureService;

	public void setMedicalProcedureService(ProcedureService obj){
		this.medicalProcedureService = obj;
	}

	public ProcedureService getMedicalProcedureService(){
		return this.medicalProcedureService;
	}
			
	// -- foreign affairs end


	public void setProcedureMedicineService (ProcedureMedicineService object){
	    this.procedureMedicineService = object;
	}
	public ProcedureMedicineService getProcedureMedicineService (){
	    return this.procedureMedicineService;
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

	
	public ProcedureMedicineFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProcedureMedicineForm tmp = null;
						Integer procedureMedicineId = WebUtil.getParameterInteger (request,"procedureMedicineId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								procedureMedicineId != null
				) {
						java.io.Serializable pkey = procedureMedicineId;
						ProcedureMedicine object = procedureMedicineService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProcedureMedicineForm(object);
			 // foreign affairs
	
				tmp.setMedicineId(""+
					object.getMedicineId().getMedicineId()
				);


	
				if (object.getProcedureId() != null){
					tmp.setProcedureId(""+
						object.getProcedureId().getProcedureId()
					);
				}


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProcedureMedicineForm();
					 // foreign affairs
	
	
				Integer medicineId = WebUtil.getParameterInteger (request,"medicineId");
		
			if(medicineId!=null){
				Medicine forClass = new Medicine();
				forClass.setMedicineId(medicineId);
				tmp.setMedicineId(""+medicineId);

				Medicine medicine = this.medicineService.get(medicineId);
				tmp.getProcedureMedicine().setMedicineId(medicine);
			}


	
	
				Integer procedureId = WebUtil.getParameterInteger (request,"procedureId");
		
			if(procedureId!=null){
				Procedure forClass = new Procedure();
				forClass.setProcedureId(procedureId);
				tmp.setProcedureId(""+procedureId);

				Procedure medicalProcedure = this.medicalProcedureService.get(procedureId);
				tmp.getProcedureMedicine().setProcedureId(medicalProcedure);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProcedureMedicineForm();
					 // foreign affairs
		
	
				Integer medicineId = WebUtil.getParameterInteger (request,"medicineId");
		
			if(medicineId!=null){
				Medicine forClass = new Medicine();
				forClass.setMedicineId(medicineId);
				tmp.setMedicineId(""+medicineId);

				Medicine medicine = this.medicineService.get(medicineId);
				tmp.getProcedureMedicine().setMedicineId(medicine);
			}else{
				Medicine forClass = new Medicine();
//				tmp.setMedicineId("");
				tmp.getProcedureMedicine().setMedicineId(forClass);
			}


	
	
				Integer procedureId = WebUtil.getParameterInteger (request,"procedureId");
		
			if(procedureId!=null){
				Procedure forClass = new Procedure();
				forClass.setProcedureId(procedureId);
				tmp.setProcedureId(""+procedureId);

				Procedure medicalProcedure = this.medicalProcedureService.get(procedureId);
				tmp.getProcedureMedicine().setProcedureId(medicalProcedure);
			}


		// -- foreign affairs end



		}
																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProcedureMedicineForm procedureMedicineForm = ( ProcedureMedicineForm ) command;
		ProcedureMedicine procedureMedicine = procedureMedicineForm.getProcedureMedicine();

//		errors.setNestedPath("procedureMedicine");
//		getValidator().validate(procedureMedicine, errors);
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

		ProcedureMedicineForm procedureMedicineForm = ( ProcedureMedicineForm ) command;

		ProcedureMedicine res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(procedureMedicineForm.getMedicineId() != null){
				procedureMedicineForm.getProcedureMedicine().setMedicineId(
					this.medicineService.get(
						new Integer((procedureMedicineForm.getMedicineId()))
						)
				);
			}
	
					if(procedureMedicineForm.getProcedureId() != null){
				procedureMedicineForm.getProcedureMedicine().setProcedureId(
					this.medicalProcedureService.get(
						new Integer((procedureMedicineForm.getProcedureId()))
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
			
			if (procedureMedicineForm.isNewProcedureMedicine ()) {
				res = procedureMedicineService.create (procedureMedicineForm.getProcedureMedicine(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.proceduremedicine",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.proceduremedicine",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = procedureMedicineService.update (procedureMedicineForm.getProcedureMedicine(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.proceduremedicine",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.proceduremedicine",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (procedureMedicineForm.isNewProcedureMedicine ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.proceduremedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.proceduremedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("proceduremedicine"+"?alert="+alertMsg));
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
