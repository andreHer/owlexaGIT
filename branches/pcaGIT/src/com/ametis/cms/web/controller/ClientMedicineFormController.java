
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
 * ClientMedicine is a mapping of client_medicine Table.
*/
public class ClientMedicineFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ClientMedicineService clientMedicineService ;
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
				ClientService clientService;

	public void setClientService(ClientService obj){
		this.clientService = obj;
	}

	public ClientService getClientService(){
		return this.clientService;
	}
			
	// -- foreign affairs end


	public void setClientMedicineService (ClientMedicineService object){
	    this.clientMedicineService = object;
	}
	public ClientMedicineService getClientMedicineService (){
	    return this.clientMedicineService;
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

	
	public ClientMedicineFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ClientMedicineForm tmp = null;
						Integer clientMedicineId = WebUtil.getParameterInteger (request,"clientMedicineId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								clientMedicineId != null
				) {
						java.io.Serializable pkey = clientMedicineId;
						ClientMedicine object = clientMedicineService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ClientMedicineForm(object);
			 // foreign affairs
	
				tmp.setMedicineId(""+
					object.getMedicineId().getMedicineId()
				);


	
				tmp.setClientId(""+
					object.getClientId().getClientId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClientMedicineForm();
					 // foreign affairs
	
	
				Integer medicineId = WebUtil.getParameterInteger (request,"medicineId");
		
			if(medicineId!=null){
				Medicine forClass = new Medicine();
				forClass.setMedicineId(medicineId);
				tmp.setMedicineId(""+medicineId);

				Medicine medicine = this.medicineService.get(medicineId);
				tmp.getClientMedicine().setMedicineId(medicine);
			}else{
				Medicine forClass = new Medicine();
//				tmp.setMedicineId("");
				tmp.getClientMedicine().setMedicineId(forClass);
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getClientMedicine().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getClientMedicine().setClientId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClientMedicineForm();
					 // foreign affairs
		
	
				Integer medicineId = WebUtil.getParameterInteger (request,"medicineId");
		
			if(medicineId!=null){
				Medicine forClass = new Medicine();
				forClass.setMedicineId(medicineId);
				tmp.setMedicineId(""+medicineId);

				Medicine medicine = this.medicineService.get(medicineId);
				tmp.getClientMedicine().setMedicineId(medicine);
			}else{
				Medicine forClass = new Medicine();
//				tmp.setMedicineId("");
				tmp.getClientMedicine().setMedicineId(forClass);
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getClientMedicine().setClientId(client);
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				tmp.getClientMedicine().setClientId(forClass);
			}


		// -- foreign affairs end



		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ClientMedicineForm clientMedicineForm = ( ClientMedicineForm ) command;
		ClientMedicine clientMedicine = clientMedicineForm.getClientMedicine();

//		errors.setNestedPath("clientMedicine");
//		getValidator().validate(clientMedicine, errors);
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

		ClientMedicineForm clientMedicineForm = ( ClientMedicineForm ) command;

		ClientMedicine res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(clientMedicineForm.getMedicineId() != null){
				clientMedicineForm.getClientMedicine().setMedicineId(
					this.medicineService.get(
						new Integer((clientMedicineForm.getMedicineId()))
						)
				);
			}
	
					if(clientMedicineForm.getClientId() != null){
				clientMedicineForm.getClientMedicine().setClientId(
					this.clientService.get(
						new Integer((clientMedicineForm.getClientId()))
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
			
			if (clientMedicineForm.isNewClientMedicine ()) {
				res = clientMedicineService.create (clientMedicineForm.getClientMedicine(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.clientmedicine",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.clientmedicine",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = clientMedicineService.update (clientMedicineForm.getClientMedicine(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.clientmedicine",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.clientmedicine",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (clientMedicineForm.isNewClientMedicine ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.clientmedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.clientmedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("clientmedicine"+"?alert="+alertMsg));
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
