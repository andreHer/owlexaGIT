
package com.ametis.cms.web.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

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
import com.ametis.cms.datamodel.CallCategory;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.FirstCall;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CallCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.CaseStatusService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.FirstCallService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PriorityService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.TimeUtils;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.FirstCallForm;



//imports+ 

//imports- 


/**
 * FirstCall is a mapping of first_call Table.
 */
public class FirstCallFormController extends SimpleFormController
//extends+ 

//extends- 

{


	
	FirstCallService firstCallService ;
	ResourceBundleMessageSource alertProperties ;	
	CaseStatusService caseStatusService;
	private SecurityService securityService;
	private PriorityService priorityService;
	private ActivityLogService logService;
	private ClaimService claimService;
	private CaseService caseService;
	
	

	
	
	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public PriorityService getPriorityService() {
		return priorityService;
	}

	public void setPriorityService(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	public void setCaseStatusService(CaseStatusService obj){
		this.caseStatusService = obj;
	}
	
	public CaseStatusService getCaseStatusService(){
		return this.caseStatusService;
	}
	CallCategoryService callCategoryService;
	
	public void setCallCategoryService(CallCategoryService obj){
		this.callCategoryService = obj;
	}
	
	public CallCategoryService getCallCategoryService(){
		return this.callCategoryService;
	}
	MemberService memberService;
	
	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}
	
	public MemberService getMemberService(){
		return this.memberService;
	}
	
	// -- foreign affairs end
	
	
	public void setFirstCallService (FirstCallService object){
		this.firstCallService = object;
	}
	public FirstCallService getFirstCallService (){
		return this.firstCallService;
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
	
	
	public FirstCallFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		Object result = null;
		FirstCallForm tmp = null;
		Integer callId = WebUtil.getParameterInteger (request,"callId");
		Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		Integer caseId = WebUtil.getParameterInteger(request, "caseId");
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		
		Integer caseType = WebUtil.getParameterInteger(request,"caseType");
		
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		/*
		 ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		 */
		if (callId != null) {
			java.io.Serializable pkey = callId;
			FirstCall object = firstCallService.get(pkey);
			
			if (object != null){ // edit object
				System.out.println("MASUK EDIT");
				System.out.println("OBJECT : "+object.getCustomerId());
				tmp = new FirstCallForm(object);
				// foreign affairs
				if (object.getHandledby()!= null){
					tmp.setHandledby(""+object.getHandledby().getUserId());
				}
				tmp.setStatus(""+object.getStatus().getCaseStatusId());				
				tmp.setCallCategoryId(""+object.getCallCategoryId().getCallCategoryId());
				
				/*Member member = new Member();
				 member.setMemberId(object.getCustomerId().getMemberId());
				 tmp.setCustomerId(member);*/
				tmp.setCustomerId(object.getCustomerId());
				if (object.getPriority() != null){
					tmp.setPriority(object.getPriority().getPriorityId().toString());
				}
				if(object.getCustomerId().getGroupName()!=null){
					tmp.setGroupName(object.getCustomerId().getGroupName());
				}
				tmp.setMemberStatus(object.getCustomerId().getStatus().toString());
				tmp.setFirstCall(object);
				String breadcrumb = "<a href=\"call\" class=\"linkbreadcrumb\">Manage Call</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;" +
						"<a href=\"call?navigation=detail&firstCallId="+callId+"\" class=\"linkbreadcrumb\">Detail Call</a> " +
								" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Call";
				if(navigation.equalsIgnoreCase("caseelog") || navigation.equalsIgnoreCase("memberelog") || navigation.equalsIgnoreCase("claimelog")||
						navigation.equalsIgnoreCase("updatecaseelog") || navigation.equalsIgnoreCase("updatememberelog") || navigation.equalsIgnoreCase("updateclaimelog")){
					breadcrumb = "Register Call";
				}
				request.setAttribute("breadcrumb", breadcrumb);
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				
				
				tmp = new FirstCallForm();
				
				// foreign affairs
								
				//Buat dapetin memberId dari tabel member, memberId ditangkep dari URL yang dikasih
				if (memberId != null){
					Member member = memberService.get(memberId);
					
					if (member != null && tmp != null) {
						tmp.setCustomerId(member);
					}
					
				}
				
				Integer handledby = WebUtil.getParameterInteger (request,"handledby");
				
				if(handledby!=null){
					User forClass = new User();
					forClass.setUserId(handledby);
					tmp.setHandledby(""+handledby);
					
					User user = this.userService.get(handledby);
					tmp.getFirstCall().setHandledby(user);
				}else{
					
				}		
				
				
				//Ini buat nangkep statusId dari tabel member
				Integer status = WebUtil.getParameterInteger (request,"status");
				
				if(status!=null){
					CaseStatus forClass = new CaseStatus();
					forClass.setCaseStatusId(status);
					tmp.setStatus(""+status);
					
					CaseStatus caseStatus = this.caseStatusService.get(status);
					tmp.getFirstCall().setStatus(caseStatus);
				}else{
				}
				
				
				
				//Ini buat nangkep Call Category ID dari tabel member
				Integer callCategoryId = WebUtil.getParameterInteger (request,"callCategoryId");
				
				if(callCategoryId!=null){
					CallCategory forClass = new CallCategory();
					forClass.setCallCategoryId(callCategoryId);
					tmp.setCallCategoryId(""+callCategoryId);
					
					CallCategory callCategory = this.callCategoryService.get(callCategoryId);
					tmp.getFirstCall().setCallCategoryId(callCategory);
				}else{

				}
				
				Integer customerId = WebUtil.getParameterInteger (request,"memberId");
				
				if(customerId!=null){
					Member forClass = new Member();
					
					tmp.setCustomerId(forClass);
					
					Member member = this.memberService.get(customerId);
					tmp.getFirstCall().setCustomerId(member);
					tmp.setGroupName(member.getGroupName());
					tmp.setMemberStatus(member.getStatus().toString());
				}else{
				}
				
				
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new FirstCallForm();
			
			// foreign affairs
			
			
			Integer handledby = WebUtil.getParameterInteger (request,"handledby");
			
			if(handledby!=null){
				User forClass = new User();
				forClass.setUserId(handledby);
				tmp.setHandledby(""+handledby);
				
				User user = this.userService.get(handledby);
				tmp.getFirstCall().setHandledby(user);
			}else{

			}
			
			
			
			
			Integer status = WebUtil.getParameterInteger (request,"status");
			
			if(status!=null){
				CaseStatus forClass = new CaseStatus();
				forClass.setCaseStatusId(status);
				tmp.setStatus(""+status);
				
				CaseStatus caseStatus = this.caseStatusService.get(status);
				tmp.getFirstCall().setStatus(caseStatus);
			}else{
			}
			
			
			
			
			Integer callCategoryId = WebUtil.getParameterInteger (request,"callCategoryId");
			
			if(callCategoryId!=null){
				CallCategory forClass = new CallCategory();
				forClass.setCallCategoryId(callCategoryId);
				tmp.setCallCategoryId(""+callCategoryId);
				
				CallCategory callCategory = this.callCategoryService.get(callCategoryId);
				tmp.getFirstCall().setCallCategoryId(callCategory);
			}else{
				
			}
			
			
			
			
			Integer customerId = WebUtil.getParameterInteger (request,"memberId");
			
			if(customerId!=null){
				Member forClass = new Member();				
				tmp.setCustomerId(forClass);
				
				Member member = this.memberService.get(customerId);
				tmp.getFirstCall().setCustomerId(member);
				tmp.setGroupName(member.getGroupName());
				tmp.setMemberStatus(member.getStatus().toString());
			}else{
			}
			
			
			
			
			String breadcrumb = "<a href=\"call\" class=\"linkbreadcrumb\">Manage Call</a> " +
						" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Call";
			if(navigation.equalsIgnoreCase("caseelog") || navigation.equalsIgnoreCase("memberelog") || navigation.equalsIgnoreCase("claimelog")||
					navigation.equalsIgnoreCase("updatecaseelog") || navigation.equalsIgnoreCase("updatememberelog") || navigation.equalsIgnoreCase("updateclaimelog")){
				breadcrumb = "Register Call";
			}
			request.setAttribute("breadcrumb", breadcrumb);
			
		}
		if (claimId != null){
			Claim claim = claimService.get(claimId);
			
			if (claim != null){
				tmp.getFirstCall().setClaimId(claim);
				tmp.setClaimId(claim.getClaimId().toString());
			}
		}
		
		if (caseId != null){
		
			Case myCase = caseService.get(caseId);
			
			if (myCase != null){
				
				tmp.getFirstCall().setCaseId(myCase);
				
				tmp.setCaseId(myCase.getCaseId().toString());
				
			}
		}
		
		if(navigation.equalsIgnoreCase("caseelog")){
			tmp.getFirstCall().setCallLogType(FirstCall.CALL_LOG_TYPE_CASE);
			tmp.setCallLogType(String.valueOf(FirstCall.CALL_LOG_TYPE_CASE));
		}else if(navigation.equalsIgnoreCase("memberelog")){
			tmp.getFirstCall().setCallLogType(FirstCall.CALL_LOG_TYPE_MEMBER);
			tmp.setCallLogType(String.valueOf(FirstCall.CALL_LOG_TYPE_MEMBER));
		}else if(navigation.equalsIgnoreCase("claimelog")){
			tmp.getFirstCall().setCallLogType(FirstCall.CALL_LOG_TYPE_CLAIM);
			tmp.setCallLogType(String.valueOf(FirstCall.CALL_LOG_TYPE_CLAIM));
		}
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		populateTimeForm(tmp, now);
		
		
		request.setAttribute("caseType",caseType);
		request.setAttribute("navigation", navigation);
		request.setAttribute("memberId", memberId);
		result =  tmp;
		return result;
	}
	
	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
	throws Exception {
		
		
		
		FirstCallForm firstCallForm = ( FirstCallForm ) command;
		FirstCall firstCall = firstCallForm.getFirstCall();
		
		System.out.println(errors);
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
		try {
			String navigation= WebUtil.getParameterString(request, "navigation", "");
			
			
			model.put("navigation", navigation);
			
			String[] pName = {"deletedStatus"};
			Object[] pValue = {Integer.valueOf(0)};
			
			Collection category = callCategoryService.search(null, null, pName, pValue, 0, -1);
			model.put("callCategory", category);
			
			Collection priority = priorityService.getAll();
			model.put("priority", priority);
			
			Collection hours = new Vector();
			String tmp = "";
			for (int i = 0; i < 24; i++){
				tmp = "";
				if (i < 10){
					tmp = "0"+i;
				}
				else {
					tmp = "" + i;
				}
				hours.add(tmp);
			}
			
			model.put("hours", hours);
			
			Collection minutes = new Vector();
			
			for (int i = 0; i < 60; i++){
				tmp = "";
				if (i < 10){
					tmp = "0"+i;
				}
				else {
					tmp = "" + i;
				}
				minutes.add(tmp);
			}
			model.put("minutes", minutes);
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}

		
		
		return model;
	}
	
	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
	throws Exception {
		
		FirstCallForm firstCallForm = ( FirstCallForm ) command;
		String subnav = WebUtil.getParameterString(request, "subnav", "");
		
		FirstCall res = null;
		String redirectUrl = "firstcall?subnav="+subnav+"&navigation=detail&callId=";
		RedirectView redirectView = new RedirectView(redirectUrl); 
		String callId="";
		try {
			
			
			firstCallForm.setCallStartTime(TimeUtils.getTimestamp(firstCallForm.getDate(),firstCallForm.getHour(),firstCallForm.getMinute()));
			firstCallForm.setCallEndTime(new Timestamp(System.currentTimeMillis()));
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(1));
			firstCallForm.getFirstCall().setStatus(status);
			
			firstCallForm.getFirstCall().setHandledby(null);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		String alertMsg="";
		try {
			// foreign affairs
//			if(firstCallForm.getHandledby() != null){
//				firstCallForm.getFirstCall().setHandledby(
//						this.userService.get(
//								new Integer((firstCallForm.getHandledby()))
//						)
//				);
//			}
//			
//			if(firstCallForm.getStatus() != null){
//				firstCallForm.getFirstCall().setStatus(
//						this.caseStatusService.get(
//								new Integer((firstCallForm.getStatus()))
//						)
//				);
//			}
//			
//			if(firstCallForm.getCallCategoryId() != null){
//				firstCallForm.getFirstCall().setCallCategoryId(
//						this.callCategoryService.get(
//								new Integer((firstCallForm.getCallCategoryId()))
//						)
//				);
//			}
//			
//			if(firstCallForm.getCustomerId() != null){
//				firstCallForm.getFirstCall().setCustomerId(
//						
//						firstCallForm.getCustomerId()
//						
//				);
//			}
			
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
						
			if (firstCallForm.isNewFirstCall ()) {
				ActionUser user= securityService.getActionUser(request); 
				
				if (firstCallForm.getCustomerId() == null){
					if (firstCallForm.getMemberId() != null){
						Member member = new Member();
						member.setMemberId(firstCallForm.getMemberId());
						firstCallForm.getFirstCall().setCustomerId(member);
					}
				}
				
				if (firstCallForm.getFollowup() == null || firstCallForm.getFollowup().equalsIgnoreCase("0")){
					CaseStatus close = new CaseStatus();
					close.setCaseStatusId(CaseStatus.CASE_CLOSED);
					firstCallForm.getFirstCall().setStatus(close);
				}
			
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEFIRSTCALL");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEFIRSTCALL access");
					return errorResult;
					
				}
				
				/* Afwan
				 * merubah kondisi checkbox jika checkbox di centang, maka akan melempar nilai "on" pada local variable "status"
				 * public void setFollowup (String status){
						this.firstCallBean.setFollowup(status);
					}
				 */
				if("on".equalsIgnoreCase(firstCallForm.getFollowup())){
					firstCallForm.setFollowup("Y");
				}else{
					firstCallForm.setFollowup("N");
				}
				
				res = firstCallService.create (firstCallForm.getFirstCall(),user);
				
				if (res!=null){
					
					alertMsg = alertProperties.getMessage ("success.create.firstcall",null,"success",Locale.getDefault());
					
					CallCategory callCategory = res.getCallCategoryId();
					
					redirectUrl += res.getCallId();
					
					String navigation = WebUtil.getParameterString(request, "navigation", "");
					System.out.println("NAVI : "+navigation);
					if(subnav.equalsIgnoreCase("caseelog")){
						redirectUrl += "&caseId="+res.getCaseId().getCaseId();
					}else if(subnav.equalsIgnoreCase("memberelog")){
						redirectUrl += "&memberId="+res.getCustomerId().getMemberId();
					}else if(subnav.equalsIgnoreCase("claimelog")){
						redirectUrl += "&claimId="+res.getClaimId().getClaimId();
					}
					
					if (callCategory.getCallCategoryId().intValue() == FirstCall.INPATIENT){					
						Integer memberId = res.getCustomerId().getMemberId();
						redirectView = new RedirectView ("case-form?navigation=tambah&caseType="+FirstCall.INPATIENT+"&memberId="+memberId);
					}
					else if (callCategory.getCallCategoryId().intValue() == FirstCall.OUTPATIENT){					
						Integer memberId = res.getCustomerId().getMemberId();
						redirectView = new RedirectView ("case-form?navigation=tambah&memberId="+memberId+"&caseType="+FirstCall.OUTPATIENT);
					}
					else {
						redirectView = new RedirectView(redirectUrl);
					}
					
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.firstcall",null,"fail",Locale.getDefault());
				}
			}
			else {
				ActionUser user= securityService.getActionUser(request); 
			
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEFIRSTCALL");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEFIRSTCALL access");
					return errorResult;
					
				}
				
				/*
				 * merubah kondisi checkbox jika checkbox di centang, maka akan melempar nilai "on" pada local variable "status"
				 * public void setFollowup (String status){
						this.firstCallBean.setFollowup(status);
					}
				 */
				if("on".equalsIgnoreCase(firstCallForm.getFollowup())){
					firstCallForm.setFollowup("Y");
				}else{
					firstCallForm.setFollowup("N");
				}
//				System.out.println(firstCallForm.getFollowup());
				res = firstCallService.update (firstCallForm.getFirstCall(),user);
				
				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.firstcall",null,"success",Locale.getDefault());
					
					CallCategory callCategory = res.getCallCategoryId();
					
					redirectUrl += res.getCallId();
					
					if(subnav.equalsIgnoreCase("updatecaseelog")){
						redirectUrl += "&caseId="+res.getCaseId().getCaseId();
					}else if(subnav.equalsIgnoreCase("updatememberelog")){
						redirectUrl += "&memberId="+res.getCustomerId().getMemberId();
					}else if(subnav.equalsIgnoreCase("updateclaimelog")){
						redirectUrl += "&claimId="+res.getClaimId().getClaimId();
					}
					
					if (callCategory.getCallCategoryId().intValue() == FirstCall.INPATIENT){					
						Integer memberId = res.getCustomerId().getMemberId();
						redirectView = new RedirectView ("case-form?navigation=tambah&caseType="+FirstCall.INPATIENT+"&memberId="+memberId);
					}
					else if (callCategory.getCallCategoryId().intValue() == FirstCall.OUTPATIENT){					
						Integer memberId = res.getCustomerId().getMemberId();
						redirectView = new RedirectView ("case-form?navigation=tambah&memberId="+memberId+"&caseType="+FirstCall.OUTPATIENT);
					}
					else {
						redirectView = new RedirectView(redirectUrl);
					}
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.firstcall",null,"fail",Locale.getDefault());
				}
				
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			if (firstCallForm.isNewFirstCall ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.firstcall",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.firstcall",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		System.out.println(errors);
		return new ModelAndView(redirectView);

		
		
	}
	private void populateTimeForm (FirstCallForm form, Timestamp callTime){
		
		if (form != null && callTime != null){
			//form.setDate()
			Date date = new Date (callTime.getTime());
			form.setDate(date);
			
			Calendar cal = Calendar.getInstance();
			
			
			cal.setTimeInMillis(callTime.getTime());
			
			
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			String tmp = "";
			if (hour < 10){
				tmp = "0" + hour;
			}
			else {
				tmp = "" + hour;
			}
			
			form.setHour(tmp);
			
			int minute = cal.get(Calendar.MINUTE);
			
			if (minute < 10){
				tmp = "0" + minute;
			}
			else {
				tmp = "" + minute;
			}
			
			form.setMinute(tmp);
			
			
		
		}
		
	}
	
	
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class,true);
		binder.registerCustomEditor(Integer.class,numInt);
	}
//	class+ 

	
	
//	class- 
	
}
