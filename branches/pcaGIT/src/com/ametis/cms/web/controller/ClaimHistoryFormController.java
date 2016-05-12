
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
 * ClaimHistory is a mapping of claim_history Table.
*/
public class ClaimHistoryFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ClaimHistoryService claimHistoryService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ClaimService claimService;

	public void setClaimService(ClaimService obj){
		this.claimService = obj;
	}

	public ClaimService getClaimService(){
		return this.claimService;
	}
			
	// -- foreign affairs end


	public void setClaimHistoryService (ClaimHistoryService object){
	    this.claimHistoryService = object;
	}
	public ClaimHistoryService getClaimHistoryService (){
	    return this.claimHistoryService;
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

	
	public ClaimHistoryFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ClaimHistoryForm tmp = null;
						Integer claimHistoryId = WebUtil.getParameterInteger (request,"claimHistoryId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								claimHistoryId != null
				) {
						java.io.Serializable pkey = claimHistoryId;
						ClaimHistory object = claimHistoryService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ClaimHistoryForm(object);
			 // foreign affairs
	
				tmp.setClaimId(""+
					object.getClaimId().getClaimId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClaimHistoryForm();
					 // foreign affairs
	
	
				Integer claimId = WebUtil.getParameterInteger (request,"claimId");
		
			if(claimId!=null){
				Claim forClass = new Claim();
				forClass.setClaimId(claimId);
				tmp.setClaimId(""+claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getClaimHistory().setClaimId(claim);
			}else{
				Claim forClass = new Claim();
//				tmp.setClaimId("");
				tmp.getClaimHistory().setClaimId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClaimHistoryForm();
					 // foreign affairs
		
	
				Integer claimId = WebUtil.getParameterInteger (request,"claimId");
		
			if(claimId!=null){
				Claim forClass = new Claim();
				forClass.setClaimId(claimId);
				tmp.setClaimId(""+claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getClaimHistory().setClaimId(claim);
			}else{
				Claim forClass = new Claim();
//				tmp.setClaimId("");
				tmp.getClaimHistory().setClaimId(forClass);
			}


		// -- foreign affairs end



		}
																																																																		
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ClaimHistoryForm claimHistoryForm = ( ClaimHistoryForm ) command;
		ClaimHistory claimHistory = claimHistoryForm.getClaimHistory();

//		errors.setNestedPath("claimHistory");
//		getValidator().validate(claimHistory, errors);
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

		ClaimHistoryForm claimHistoryForm = ( ClaimHistoryForm ) command;

		ClaimHistory res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(claimHistoryForm.getClaimId() != null){
				claimHistoryForm.getClaimHistory().setClaimId(
					this.claimService.get(
						new Integer((claimHistoryForm.getClaimId()))
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
			
			if (claimHistoryForm.isNewClaimHistory ()) {
				res = claimHistoryService.create (claimHistoryForm.getClaimHistory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.claimhistory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.claimhistory",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = claimHistoryService.update (claimHistoryForm.getClaimHistory(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.claimhistory",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.claimhistory",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (claimHistoryForm.isNewClaimHistory ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.claimhistory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.claimhistory",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("claimhistory"+"?alert="+alertMsg));
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
