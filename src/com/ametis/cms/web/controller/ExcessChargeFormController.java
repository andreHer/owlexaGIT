
package com.ametis.cms.web.controller;

import java.text.NumberFormat;
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
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ExcessChargeForm;



// imports+ 

// imports- 


/**
 * ExcessCharge is a mapping of excess_charge Table.
*/
public class ExcessChargeFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	ExcessChargeService excessChargeService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
			MemberService memberService;
			private SecurityService securityService;
			private ActivityLogService logService;

			
			
			public ActivityLogService getLogService() {
				return logService;
			}

			public void setLogService(ActivityLogService logService) {
				this.logService = logService;
			}
			public SecurityService getSecurityService() {
				return securityService;
			}

			public void setSecurityService(SecurityService securityService) {
				this.securityService = securityService;
			}
	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
				ClaimService claimService;

	public void setClaimService(ClaimService obj){
		this.claimService = obj;
	}

	public ClaimService getClaimService(){
		return this.claimService;
	}
			
	// -- foreign affairs end


	public void setExcessChargeService (ExcessChargeService object){
	    this.excessChargeService = object;
	}
	public ExcessChargeService getExcessChargeService (){
	    return this.excessChargeService;
	}
		// generate by default
		private UserService  userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}


	public ExcessChargeFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ExcessChargeForm tmp = null;
		Integer excessChargeId = WebUtil.getParameterInteger (request,"excessChargeId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (excessChargeId != null) {
			
			java.io.Serializable pkey = excessChargeId;
			ExcessCharge object = excessChargeService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ExcessChargeForm(object);
			 // foreign affairs
	
				tmp.setMemberId(""+
					object.getMemberId().getMemberId()
				);


	
				tmp.setClaimId(""+
					object.getClaimId().getClaimId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ExcessChargeForm();
					 // foreign affairs
	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getExcessCharge().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getExcessCharge().setMemberId(forClass);
			}


	
	
				Integer claimId = WebUtil.getParameterInteger (request,"claimId");
		
			if(claimId!=null){
				Claim forClass = new Claim();
				forClass.setClaimId(claimId);
				tmp.setClaimId(""+claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getExcessCharge().setClaimId(claim);
			}else{
				Claim forClass = new Claim();
//				tmp.setClaimId("");
				tmp.getExcessCharge().setClaimId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ExcessChargeForm();
					 // foreign affairs
		
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(""+memberId);

				Member member = this.memberService.get(memberId);
				tmp.getExcessCharge().setMemberId(member);
			}else{
				Member forClass = new Member();
//				tmp.setMemberId("");
				tmp.getExcessCharge().setMemberId(forClass);
			}


	
	
				Integer claimId = WebUtil.getParameterInteger (request,"claimId");
		
			if(claimId!=null){
				Claim forClass = new Claim();
				forClass.setClaimId(claimId);
				tmp.setClaimId(""+claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getExcessCharge().setClaimId(claim);
			}else{
				Claim forClass = new Claim();
//				tmp.setClaimId("");
				tmp.getExcessCharge().setClaimId(forClass);
			}


		// -- foreign affairs end



		}
																																				
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ExcessChargeForm excessChargeForm = ( ExcessChargeForm ) command;
		ExcessCharge excessCharge = excessChargeForm.getExcessCharge();

//		errors.setNestedPath("excessCharge");
//		getValidator().validate(excessCharge, errors);
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

		ExcessChargeForm excessChargeForm = ( ExcessChargeForm ) command;

		ExcessCharge res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(excessChargeForm.getMemberId() != null){
				excessChargeForm.getExcessCharge().setMemberId(
					this.memberService.get(
						new Integer((excessChargeForm.getMemberId()))
						)
				);
			}
	
					if(excessChargeForm.getClaimId() != null){
				excessChargeForm.getExcessCharge().setClaimId(
					this.claimService.get(
						new Integer((excessChargeForm.getClaimId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			

			if (excessChargeForm.isNewExcessCharge ()) {
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEEXCESSCHARGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEEXCESSCHARGE access");
				return errorResult;
				
			}
				res = excessChargeService.create (excessChargeForm.getExcessCharge(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.excesscharge",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.excesscharge",null,"fail",Locale.getDefault());
				}
			}
			else {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEEXCESSCHARGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEEXCESSCHARGE access");
				return errorResult;
				
			}
				res = excessChargeService.update (excessChargeForm.getExcessCharge(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.excesscharge",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.excesscharge",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			ex.printStackTrace();
			if (excessChargeForm.isNewExcessCharge ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.excesscharge",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.excesscharge",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("excesscharge"+"?alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
		
		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
        binder.registerCustomEditor(java.lang.Double.class,
                new CustomNumberEditor (java.lang.Double.class, nf, true));
	}
// class+ 

// class- 

}
