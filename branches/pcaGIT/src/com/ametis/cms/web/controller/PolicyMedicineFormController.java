
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
 * PolicyMedicine is a mapping of policy_medicine Table.
*/
public class PolicyMedicineFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	PolicyMedicineService policyMedicineService ;
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
				PolicyService policyService;

	public void setPolicyService(PolicyService obj){
		this.policyService = obj;
	}

	public PolicyService getPolicyService(){
		return this.policyService;
	}
			
	// -- foreign affairs end


	public void setPolicyMedicineService (PolicyMedicineService object){
	    this.policyMedicineService = object;
	}
	public PolicyMedicineService getPolicyMedicineService (){
	    return this.policyMedicineService;
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

	
	public PolicyMedicineFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyMedicineForm tmp = null;
		Integer policyMedicineId = WebUtil.getParameterInteger (request,"policyMedicineId");
		Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		String navigation = WebUtil.getParameterString (request,"navigation", "");

		String breadcrumb = "";
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								policyMedicineId != null
				) {
						java.io.Serializable pkey = policyMedicineId;
						PolicyMedicine object = policyMedicineService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PolicyMedicineForm(object);
			 // foreign affairs
	
				tmp.setMedicineId(""+
					object.getMedicineId().getMedicineId()
				);


	
				tmp.setPolicyId(""+
					object.getPolicyId().getPolicyId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyMedicineForm();
					 // foreign affairs
	
	
				Integer medicineId = WebUtil.getParameterInteger (request,"medicineId");
		
			if(medicineId!=null){
				Medicine forClass = new Medicine();
				forClass.setMedicineId(medicineId);
				tmp.setMedicineId(""+medicineId);

				Medicine medicine = this.medicineService.get(medicineId);
				tmp.getPolicyMedicine().setMedicineId(medicine);
			}else{
				Medicine forClass = new Medicine();
//				tmp.setMedicineId("");
				tmp.getPolicyMedicine().setMedicineId(forClass);
			}

//				Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyMedicine().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getPolicyMedicine().setPolicyId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyMedicineForm();
					 // foreign affairs
		
	
				Integer medicineId = WebUtil.getParameterInteger (request,"medicineId");
		
			if(medicineId!=null){
				Medicine forClass = new Medicine();
				forClass.setMedicineId(medicineId);
				tmp.setMedicineId(""+medicineId);

				Medicine medicine = this.medicineService.get(medicineId);
				tmp.getPolicyMedicine().setMedicineId(medicine);
			}else{
				Medicine forClass = new Medicine();
//				tmp.setMedicineId("");
				tmp.getPolicyMedicine().setMedicineId(forClass);
			}
//				Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyMedicine().setPolicyId(policy);
			}else{
				Policy forClass = new Policy();
//				tmp.setPolicyId("");
				tmp.getPolicyMedicine().setPolicyId(forClass);
			}


		// -- foreign affairs end
		}
		
	    if (navigation.equalsIgnoreCase("upload")) {          
			breadcrumb = "<a href=\"policymedicine?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Medicine Formularium</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Medicine";
	    }

		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);
		request.setAttribute("navigation", navigation);
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {

		Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		String navigation = WebUtil.getParameterString (request,"navigation", "");

		String breadcrumb = "";

		PolicyMedicineForm policyMedicineForm = ( PolicyMedicineForm ) command;
		PolicyMedicine policyMedicine = policyMedicineForm.getPolicyMedicine();
		
	    if (navigation.equalsIgnoreCase("upload")) {          
			breadcrumb = "<a href=\"policymedicine?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Medicine Formularium</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Medicine";
	    }

		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);
		request.setAttribute("navigation", navigation);

//		errors.setNestedPath("policyMedicine");
//		getValidator().validate(policyMedicine, errors);
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
		
		Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		String navigation = WebUtil.getParameterString (request,"navigation", "");

		String breadcrumb = "";

		PolicyMedicineForm policyMedicineForm = ( PolicyMedicineForm ) command;

		PolicyMedicine res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(policyMedicineForm.getMedicineId() != null){
				policyMedicineForm.getPolicyMedicine().setMedicineId(
					this.medicineService.get(
						new Integer((policyMedicineForm.getMedicineId()))
						)
				);
			}
	
					if(policyMedicineForm.getPolicyId() != null){
				policyMedicineForm.getPolicyMedicine().setPolicyId(
					this.policyService.get(
						new Integer((policyMedicineForm.getPolicyId()))
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
			
			if (policyMedicineForm.isNewPolicyMedicine ()) {
				res = policyMedicineService.create (policyMedicineForm.getPolicyMedicine(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.policymedicine",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policymedicine",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyMedicineService.update (policyMedicineForm.getPolicyMedicine(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policymedicine",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policymedicine",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (policyMedicineForm.isNewPolicyMedicine ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policymedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policymedicine",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
	    if (navigation.equalsIgnoreCase("upload")) {          
			breadcrumb = "<a href=\"policymedicine?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Medicine Formularium</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Medicine";
	    }

		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);
		request.setAttribute("navigation", navigation);
		
		return new ModelAndView(new RedirectView("policymedicine"+"?alert="+alertMsg));
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
