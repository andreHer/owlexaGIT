
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
 * MemberGroupProviderDiagnosis is a mapping of member_group_provider_diagnosis Table.
*/
public class MemberGroupProviderDiagnosisFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	MemberGroupProviderDiagnosisService memberGroupProviderDiagnosisService ;
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
				MemberGroupProviderService memberGroupProviderService;

	public void setMemberGroupProviderService(MemberGroupProviderService obj){
		this.memberGroupProviderService = obj;
	}

	public MemberGroupProviderService getMemberGroupProviderService(){
		return this.memberGroupProviderService;
	}
			
	// -- foreign affairs end


	public void setMemberGroupProviderDiagnosisService (MemberGroupProviderDiagnosisService object){
	    this.memberGroupProviderDiagnosisService = object;
	}
	public MemberGroupProviderDiagnosisService getMemberGroupProviderDiagnosisService (){
	    return this.memberGroupProviderDiagnosisService;
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

	
	public MemberGroupProviderDiagnosisFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		MemberGroupProviderDiagnosisForm tmp = null;
						Integer memberGroupProviderDiagnosisId = WebUtil.getParameterInteger (request,"memberGroupProviderDiagnosisId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								memberGroupProviderDiagnosisId != null
				) {
						java.io.Serializable pkey = memberGroupProviderDiagnosisId;
						MemberGroupProviderDiagnosis object = memberGroupProviderDiagnosisService.get (pkey );

			 if (object != null){ // edit object

				tmp = new MemberGroupProviderDiagnosisForm(object);
			 // foreign affairs
	
				tmp.setDiagnosisId(""+
					object.getDiagnosisId().getDiagnosisId()
				);


	
				tmp.setMemberGroupProviderId(""+
					object.getMemberGroupProviderId().getMemberGroupProviderId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberGroupProviderDiagnosisForm();
					 // foreign affairs
	
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getMemberGroupProviderDiagnosis().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getMemberGroupProviderDiagnosis().setDiagnosisId(forClass);
			}


	
	
				Integer memberGroupProviderId = WebUtil.getParameterInteger (request,"memberGroupProviderId");
		
			if(memberGroupProviderId!=null){
				MemberGroupProvider forClass = new MemberGroupProvider();
				forClass.setMemberGroupProviderId(memberGroupProviderId);
				tmp.setMemberGroupProviderId(""+memberGroupProviderId);

				MemberGroupProvider memberGroupProvider = this.memberGroupProviderService.get(memberGroupProviderId);
				tmp.getMemberGroupProviderDiagnosis().setMemberGroupProviderId(memberGroupProvider);
			}else{
				MemberGroupProvider forClass = new MemberGroupProvider();
//				tmp.setMemberGroupProviderId("");
				tmp.getMemberGroupProviderDiagnosis().setMemberGroupProviderId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberGroupProviderDiagnosisForm();
					 // foreign affairs
		
	
				Integer diagnosisId = WebUtil.getParameterInteger (request,"diagnosisId");
		
			if(diagnosisId!=null){
				Diagnosis forClass = new Diagnosis();
				forClass.setDiagnosisId(diagnosisId);
				tmp.setDiagnosisId(""+diagnosisId);

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getMemberGroupProviderDiagnosis().setDiagnosisId(diagnosis);
			}else{
				Diagnosis forClass = new Diagnosis();
//				tmp.setDiagnosisId("");
				tmp.getMemberGroupProviderDiagnosis().setDiagnosisId(forClass);
			}


	
	
				Integer memberGroupProviderId = WebUtil.getParameterInteger (request,"memberGroupProviderId");
		
			if(memberGroupProviderId!=null){
				MemberGroupProvider forClass = new MemberGroupProvider();
				forClass.setMemberGroupProviderId(memberGroupProviderId);
				tmp.setMemberGroupProviderId(""+memberGroupProviderId);

				MemberGroupProvider memberGroupProvider = this.memberGroupProviderService.get(memberGroupProviderId);
				tmp.getMemberGroupProviderDiagnosis().setMemberGroupProviderId(memberGroupProvider);
			}else{
				MemberGroupProvider forClass = new MemberGroupProvider();
//				tmp.setMemberGroupProviderId("");
				tmp.getMemberGroupProviderDiagnosis().setMemberGroupProviderId(forClass);
			}


		// -- foreign affairs end



		}
																								
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		MemberGroupProviderDiagnosisForm memberGroupProviderDiagnosisForm = ( MemberGroupProviderDiagnosisForm ) command;
		MemberGroupProviderDiagnosis memberGroupProviderDiagnosis = memberGroupProviderDiagnosisForm.getMemberGroupProviderDiagnosis();

//		errors.setNestedPath("memberGroupProviderDiagnosis");
//		getValidator().validate(memberGroupProviderDiagnosis, errors);
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

		MemberGroupProviderDiagnosisForm memberGroupProviderDiagnosisForm = ( MemberGroupProviderDiagnosisForm ) command;

		MemberGroupProviderDiagnosis res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(memberGroupProviderDiagnosisForm.getDiagnosisId() != null){
				memberGroupProviderDiagnosisForm.getMemberGroupProviderDiagnosis().setDiagnosisId(
					this.diagnosisService.get(
						new Integer((memberGroupProviderDiagnosisForm.getDiagnosisId()))
						)
				);
			}
	
					if(memberGroupProviderDiagnosisForm.getMemberGroupProviderId() != null){
				memberGroupProviderDiagnosisForm.getMemberGroupProviderDiagnosis().setMemberGroupProviderId(
					this.memberGroupProviderService.get(
						new Integer((memberGroupProviderDiagnosisForm.getMemberGroupProviderId()))
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
			
			if (memberGroupProviderDiagnosisForm.isNewMemberGroupProviderDiagnosis ()) {
				res = memberGroupProviderDiagnosisService.create (memberGroupProviderDiagnosisForm.getMemberGroupProviderDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.membergroupproviderdiagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.membergroupproviderdiagnosis",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = memberGroupProviderDiagnosisService.update (memberGroupProviderDiagnosisForm.getMemberGroupProviderDiagnosis(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.membergroupproviderdiagnosis",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.membergroupproviderdiagnosis",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (memberGroupProviderDiagnosisForm.isNewMemberGroupProviderDiagnosis ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.membergroupproviderdiagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.membergroupproviderdiagnosis",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("membergroupproviderdiagnosis"+"?alert="+alertMsg));
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
