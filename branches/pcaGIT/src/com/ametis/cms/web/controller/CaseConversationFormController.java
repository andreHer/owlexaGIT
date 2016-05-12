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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseConversation;
import com.ametis.cms.datamodel.ConversationCategory;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseConversationService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ConversationCategoryService;
import com.ametis.cms.service.PriorityService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CaseConversationForm;

// imports+ 

// imports- 

/**
 * CaseConversation is a mapping of case_conversation Table.
 */
public class CaseConversationFormController extends SimpleFormController
// extends+

// extends-

{

	CaseConversationService caseConversationService;
	private PriorityService priorityService;

	ResourceBundleMessageSource alertProperties;

	CaseService caseService;

	ConversationCategoryService conversationCategoryService;

	// foreign affairs

	// -- foreign affairs end
	private SecurityService securityService;
	
private ActivityLogService logService;
	
	
	
	public PriorityService getPriorityService() {
	return priorityService;
}

public void setPriorityService(PriorityService priorityService) {
	this.priorityService = priorityService;
}

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
	public void setCaseConversationService(CaseConversationService object) {
		this.caseConversationService = object;
	}

	public CaseConversationService getCaseConversationService() {
		return this.caseConversationService;
	}

	// generate by default
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ConversationCategoryService getConversationCategoryService() {
		return conversationCategoryService;
	}

	public void setConversationCategoryService(
			ConversationCategoryService conversationCategoryService) {
		this.conversationCategoryService = conversationCategoryService;
	}

	public void setPropertiesUtil(ResourceBundleMessageSource object) {
		this.alertProperties = object;
	}

	public ResourceBundleMessageSource getPropertiesUtil() {
		return this.alertProperties;
	}

	public CaseConversationFormController() {
		setSessionForm(true);
		//setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CaseConversationForm tmp = null;
		Long conversationId = WebUtil.getParameterLong(request,
				"conversationId");
		String navigation = WebUtil.getParameterString(request,
				"navigation", "");
		String breadcrumb ="";

		String caseIdStr = request.getParameter("caseId");
		Integer caseId = null;

		

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (conversationId != null) {
			java.io.Serializable pkey = conversationId;
			CaseConversation object = caseConversationService.get(pkey);

			if (object != null) { // edit object

				tmp = new CaseConversationForm(object);
				Calendar cal = Calendar.getInstance();
				
				cal.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
				tmp.setDate(new java.sql.Date(cal.getTimeInMillis()).toString());
				tmp.setTime(cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
				
				Case tmpCase = tmp.getCase();
				if (tmpCase != null){
					tmpCase = caseService.get(tmpCase.getCaseId());
					
					
				}
				
				if (tmpCase != null){
					tmp.setCase(tmpCase);
				}
				breadcrumb = "<a href=\"case?navigation=detail&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">Detail Case</a>" +
				" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseconversation?navigation=list&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">List Case Conversation</a>"+ 
				" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseconversation?navigation=detail&conversationId="+object.getConversationId()+"&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">Detail Case Conversation</a>"+
				" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Case Conversation";
				request.setAttribute("breadcrumb", breadcrumb);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseConversationForm();
				tmp.setConversationCategory(new ConversationCategory());
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CaseConversationForm();
			tmp.setConversationCategory(new ConversationCategory());
			// foreign affairs
			// -- foreign affairs end

			Calendar cal = Calendar.getInstance();
			
			cal.setTime(new java.sql.Timestamp(System.currentTimeMillis()));
			tmp.setDate(new java.sql.Date(cal.getTimeInMillis()).toString());
			tmp.setTime(cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE));
			try {
				caseId = Integer.valueOf(caseIdStr);
			} catch (Exception e) {

			}

			if (caseId != null) {
				Case caseBean = caseService.get(caseId);

				if (caseBean != null && tmp != null) {
					tmp.setCase(caseBean);
				}
				else {
					tmp.setCase(new Case());
				}
			}
			else {
				tmp.setCase(new Case());
			}
	        if (navigation.equalsIgnoreCase("tambah")) {
	        	breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Conversation";
	        }

		}
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		String navigation = WebUtil.getParameterString(request,
				"navigation", "");
		Integer caseId = WebUtil.getParameterInteger(request,
				"caseId");
		String breadcrumb ="";
		
		CaseConversationForm caseConversationForm = (CaseConversationForm) command;
		CaseConversation caseConversation = caseConversationForm
				.getCaseConversation();
		
        if (navigation.equalsIgnoreCase("tambah")) {
        	breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Conversation";
        }
        
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);

		// errors.setNestedPath("caseConversation");
		// getValidator().validate(caseConversation, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
		 * ini dipake buat populate data - data yang dibutuhkan contoh : Problem
		 * membutuhkan ProblemCategory nah fungsi method ini yaitu untuk
		 * populate list problem category ke jsp nanti biar bisa ditangkep sama
		 * jspnya
		 * 
		 * contoh : Collection pc = pcontroller.searchPC();
		 * 
		 * model.addObject("pcbeans", pc);
		 * 
		 */
		Collection conversationCategory = conversationCategoryService.getAll();
		model.put("conversationCategory", conversationCategory);
		
		Collection<Priority> priorityList = priorityService.getAll();
		
		model.put("priorityList", priorityList);
		
		
		try {
			
			
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

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		String navigation = WebUtil.getParameterString(request,
				"navigation", "");
		Integer caseId = WebUtil.getParameterInteger(request,
				"caseId");
		String breadcrumb ="";

		CaseConversationForm caseConversationForm = (CaseConversationForm) command;
		
		CaseConversation res = null;
		
		String tmpDate = caseConversationForm.getDate();
		
		
		try {
			
			java.sql.Date tanggal = java.sql.Date.valueOf(tmpDate);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(tanggal.getTime());
			
			
			String hour = caseConversationForm.getHour();
			String minute = caseConversationForm.getMinute();
			
			cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
			cal.set(Calendar.MINUTE, Integer.valueOf(minute));
			
			caseConversationForm.setConversationTime(new Timestamp( cal.getTimeInMillis()));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		String alertMsg = "";
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}
			

			if (caseConversationForm.isNewCaseConversation()) {
				
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATECASECONVERSATION");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATECASECONVERSATION access");
					return errorResult;
					
				}
				res = caseConversationService.create(caseConversationForm
						.getCaseConversation(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.caseconversation", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.caseconversation", null, "fail",
							Locale.getDefault());
				}
			} else {
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATECASECONVERSATION");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATECASECONVERSATION access");
					return errorResult;
					
				}
				res = caseConversationService.update(caseConversationForm
						.getCaseConversation(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.caseconversation", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.caseconversation", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (caseConversationForm.isNewCaseConversation()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.caseconversation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.caseconversation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
        if (navigation.equalsIgnoreCase("tambah")) {
        	breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Conversation";
        }
        
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
        
		return new ModelAndView(new RedirectView("caseconversation?navigation=list&caseId=" + caseConversationForm.getCase().getCaseId() + "&alert="
				+ alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Number.class, num);
	}

	// class+
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	
	// class-

}
