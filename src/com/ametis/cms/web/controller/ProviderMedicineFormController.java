
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
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderMedicine;
import com.ametis.cms.service.MedicineService;
import com.ametis.cms.service.ProviderMedicineService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProviderMedicineForm;



// imports+ 

// imports- 

/**
 * ProviderMedicine is a mapping of provider_medicine Table.
*/
public class ProviderMedicineFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ProviderMedicineService providerMedicineService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ProviderService providerService;

	public void setProviderService(ProviderService obj){
		this.providerService = obj;
	}

	public ProviderService getProviderService(){
		return this.providerService;
	}
				MedicineService medicineService;

	public void setMedicineService(MedicineService obj){
		this.medicineService = obj;
	}

	public MedicineService getMedicineService(){
		return this.medicineService;
	}
			
	// -- foreign affairs end


	public void setProviderMedicineService (ProviderMedicineService object){
	    this.providerMedicineService = object;
	}
	public ProviderMedicineService getProviderMedicineService (){
	    return this.providerMedicineService;
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

	
	public ProviderMedicineFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ProviderMedicineForm tmp = null;
						Integer providerMedicineId = WebUtil.getParameterInteger (request,"providerMedicineId");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								providerMedicineId != null
				) {
						java.io.Serializable pkey = providerMedicineId;
						ProviderMedicine object = providerMedicineService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ProviderMedicineForm(object);
			 // foreign affairs
	
				tmp.setProviderId(""+
					object.getProviderId().getProviderId()
				);
				if (object.getProviderId() != null){
					Provider provider = providerService.get(object.getProviderId().getProviderId());
					
					tmp.setProviderName(provider.getProviderName());
				}
				if (object.getMedicineId() != null){
					Medicine medicine = medicineService.get(object.getMedicineId().getMedicineId());
					if (medicine != null){
						tmp.setMedicineName(medicine.getMedicineName());
						tmp.setMedicineId(medicine.getMedicineId().toString());
					}
				}



		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderMedicineForm();
					 // foreign affairs
	
	
//				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProviderMedicine().setProviderId(provider);
				tmp.setProviderName(provider.getProviderName());
			}
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderMedicineForm();
					 // foreign affairs
//				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId(""+providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getProviderMedicine().setProviderId(provider);
				tmp.setProviderName(provider.getProviderName());
			}
		}
		String breadcrumb = "<a href=\"providermedicine?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Medicine</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Medicine";
		request.setAttribute("breadcrumb", breadcrumb);
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ProviderMedicineForm providerMedicineForm = ( ProviderMedicineForm ) command;
		ProviderMedicine providerMedicine = providerMedicineForm.getProviderMedicine();

//		errors.setNestedPath("providerMedicine");
//		getValidator().validate(providerMedicine, errors);
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

		ProviderMedicineForm providerMedicineForm = ( ProviderMedicineForm ) command;

		ProviderMedicine res = null;
		String alertMsg="";
		String providerId = providerMedicineForm.getProviderId();
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		try {
	
	
			ActionUser user = securityService.getActionUser(request);
			
			if (providerMedicineForm.isNewProviderMedicine ()) {
				if (navigation.equalsIgnoreCase("upload")){
	
					res = providerMedicineService.createUpload(providerMedicineForm.getProviderMedicine(),providerMedicineForm.getMedicineFile(),user);
					
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.providermedicine",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.providermedicine",null,"fail",Locale.getDefault());
					}
				}
				else {
					res = providerMedicineService.create (providerMedicineForm.getProviderMedicine(),user);
	
					if (res!=null){
						alertMsg = alertProperties.getMessage ("success.create.providermedicine",null,"success",Locale.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage ("fail.create.providermedicine",null,"fail",Locale.getDefault());
					}
				}
			}
			else {
				res = providerMedicineService.update (providerMedicineForm.getProviderMedicine(),user);

				if (res!=null){
					providerId = res.getProviderId().getProviderId().toString();
					alertMsg = alertProperties.getMessage ("success.update.providermedicine",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.providermedicine",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (providerMedicineForm.isNewProviderMedicine ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.providermedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.providermedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("providermedicine"+"?alert="+alertMsg+"&providerId="+providerId));
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
