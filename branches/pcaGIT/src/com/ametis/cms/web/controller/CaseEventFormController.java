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
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.datamodel.EventCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseEventService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.EventCategoryService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.TimeUtils;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CaseEventForm;

// imports+ 

// imports- 

/**
 * CaseEvent is a mapping of case_event Table.
 */
public class CaseEventFormController extends SimpleFormController
// extends+

// extends-

{

	CaseEventService caseEventService;

	ResourceBundleMessageSource alertProperties;

	EventCategoryService eventCategoryService;

	// foreign affairs

	CaseService myCaseService;
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
	public void setCaseService(CaseService obj) {
		this.myCaseService = obj;
	}

	public CaseService getCaseService() {
		return this.myCaseService;
	}

	// -- foreign affairs end

	public void setCaseEventService(CaseEventService object) {
		this.caseEventService = object;
	}

	public CaseEventService getCaseEventService() {
		return this.caseEventService;
	}

	// generate by default
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPropertiesUtil(ResourceBundleMessageSource object) {
		this.alertProperties = object;
	}

	public ResourceBundleMessageSource getPropertiesUtil() {
		return this.alertProperties;
	}

	public CaseEventFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CaseEventForm tmp = null;
		Integer caseEventId = WebUtil.getParameterInteger(request,
				"caseEventId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (caseEventId != null) {
			java.io.Serializable pkey = caseEventId;
			CaseEvent object = caseEventService.get(pkey);

			if (object != null) { // edit object

				tmp = new CaseEventForm(object);
				// foreign affairs

				tmp.setCase(object.getCaseId());

				String breadcrumb = "<a href=\"case?navigation=detail&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">Detail Case</a>" +
						"&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseevent?navigation=list&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">List Case Monitoring</a>" +
								"&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseevent?navigation=detail&caseId="+object.getCaseId().getCaseId()+"&caseEventId="+object.getCaseEventId()+"\" class=\"linkbreadcrumb\">Detail Case Monitoring</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Monitoring";
				request.setAttribute("breadcrumb", breadcrumb);
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseEventForm();
				// foreign affairs

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");

				if (caseId != null) {
					
					Case forClass = new Case();
					forClass.setCaseId(caseId);
					tmp.setCase(forClass);

					Case myCase = this.myCaseService.get(caseId);
					tmp.getCaseEvent().setCaseId(myCase);
				} else {
					Case forClass = new Case();
					// tmp.setCaseId("");
					tmp.getCaseEvent().setCaseId(forClass);
				}

				// -- foreign affairs end
				tmp.setEventCategoryId(new EventCategory());
			}
			
		} // mau edit end
		else { // bikin baru
			tmp = new CaseEventForm();
			// foreign affairs

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			if (caseId != null) {
				
				Case forClass = new Case();
				forClass.setCaseId(caseId);
				tmp.setCase(forClass);

				Case myCase = this.myCaseService.get(caseId);
				tmp.getCaseEvent().setCaseId(myCase);
				
			} else {
				
				Case forClass = new Case();
				// tmp.setCaseId("");
				tmp.getCaseEvent().setCaseId(forClass);
			}
			
			tmp.setEventCategoryId(new EventCategory());

			// -- foreign affairs end
			String breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a>&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Monitoring";
			request.setAttribute("breadcrumb", breadcrumb);
		}

		result = tmp;
		Timestamp now = new Timestamp(System.currentTimeMillis());
		populateTimeForm(tmp, now);
			
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		CaseEventForm caseEventForm = (CaseEventForm) command;
		CaseEvent caseEvent = caseEventForm.getCaseEvent();
		
		System.out.println("ERROR : " + errors);

		// errors.setNestedPath("caseEvent");
		// getValidator().validate(caseEvent, errors);
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
		Collection eventCategory = eventCategoryService.getAll();
		model.put("eventCategories", eventCategory);
		
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
		
		

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		CaseEventForm caseEventForm = (CaseEventForm) command;

		CaseEvent res = null;
		String alertMsg = "";
		String redirectURL = "";
	
		try {
			// foreign affairs

			caseEventForm.setMonitoringTime(TimeUtils.getTimestamp(caseEventForm.getDate(), caseEventForm.getHour(), caseEventForm.getMinute()));
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}
			

			if (caseEventForm.isNewCaseEvent()) {
				
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATECASEEVENT");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATECASEEVENT access");
					return errorResult;
					
				}
				caseEventForm.getCaseEvent().setMonitoredBy(user.getUser().getUsername());
				res = caseEventService.create(caseEventForm.getCaseEvent(),
						user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.caseevent", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.caseevent", null, "fail", Locale
									.getDefault());
				}
			} else {
				ActionUser user= securityService.getActionUser(request); 
				
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATECASEEVENT");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATECASEEVENT access");
					return errorResult;
					
				}
				caseEventForm.getCaseEvent().setMonitoredBy(user.getUser().getUsername());
				res = caseEventService.update(caseEventForm.getCaseEvent(),
						user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.caseevent", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.caseevent", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (caseEventForm.isNewCaseEvent()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.caseevent", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.caseevent", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("caseevent?navigation=list&caseId=" + caseEventForm.getCase().getCaseId() +  "&alert="
				+ alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		
		super.initBinder(req, binder);
		
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);
	
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
		
		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numInt);
		
	}
private void populateTimeForm (CaseEventForm form, Timestamp callTime){
		
		if (form != null && callTime != null){
			//form.setDate()
			Date date = new Date (callTime.getTime());
			form.setDate(date);
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTimeInMillis(callTime.getTime());
			
			int hour = cal.get(Calendar.HOUR);
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
	
	// class+

	public EventCategoryService getEventCategoryService() {
		return eventCategoryService;
	}

	public void setEventCategoryService(
			EventCategoryService eventCategoryService) {
		this.eventCategoryService = eventCategoryService;
	}

	// class-

}
