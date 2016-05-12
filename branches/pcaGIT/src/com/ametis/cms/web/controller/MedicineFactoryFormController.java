
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
 * MedicineFactory is a mapping of medicine_factory Table.
*/
public class MedicineFactoryFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	MedicineFactoryService medicineFactoryService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
		
	// -- foreign affairs end


	public void setMedicineFactoryService (MedicineFactoryService object){
	    this.medicineFactoryService = object;
	}
	public MedicineFactoryService getMedicineFactoryService (){
	    return this.medicineFactoryService;
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

	
	public MedicineFactoryFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MedicineFactoryForm tmp = null;
						Integer medicineFactoryId = WebUtil.getParameterInteger (request,"medicineFactoryId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								medicineFactoryId != null
				) {
						java.io.Serializable pkey = medicineFactoryId;
						MedicineFactory object = medicineFactoryService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MedicineFactoryForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MedicineFactoryForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MedicineFactoryForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		MedicineFactoryForm medicineFactoryForm = ( MedicineFactoryForm ) command;
		MedicineFactory medicineFactory = medicineFactoryForm.getMedicineFactory();

//		errors.setNestedPath("medicineFactory");
//		getValidator().validate(medicineFactory, errors);
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

		MedicineFactoryForm medicineFactoryForm = ( MedicineFactoryForm ) command;

		MedicineFactory res = null;
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
			
			if (medicineFactoryForm.isNewMedicineFactory ()) {
				res = medicineFactoryService.create (medicineFactoryForm.getMedicineFactory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.medicinefactory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.medicinefactory",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = medicineFactoryService.update (medicineFactoryForm.getMedicineFactory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.medicinefactory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.medicinefactory",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (medicineFactoryForm.isNewMedicineFactory ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.medicinefactory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.medicinefactory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("medicinefactory"+"?alert="+alertMsg));
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
