package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.RejectCategoryService;
import com.ametis.cms.service.RejectedCaseService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.RejectedCaseForm;

// imports+ 

// imports- 

/**
 * RejectedCase is a mapping of rejected_case Table.
 */
public class RejectedCaseFormController extends SimpleFormController
// extends+

// extends-
{

	RejectedCaseService rejectedCaseService;

	ResourceBundleMessageSource alertProperties;
	private RejectCategoryService rejectCategoryService;

	// foreign affairs

	CaseService caseService;
	SecurityService securityService;
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
		this.caseService = obj;
	}

	public CaseService getCaseService() {
		return this.caseService;
	}
	
	// -- foreign affairs end

	public RejectCategoryService getRejectCategoryService() {
		return rejectCategoryService;
	}

	public void setRejectCategoryService(RejectCategoryService rejectCategoryService) {
		this.rejectCategoryService = rejectCategoryService;
	}

	public void setRejectedCaseService(RejectedCaseService object) {
		this.rejectedCaseService = object;
	}

	public RejectedCaseService getRejectedCaseService() {
		return this.rejectedCaseService;
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

	public RejectedCaseFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		RejectedCaseForm tmp = null;
		Long rejectedCaseId = WebUtil.getParameterLong(request,
				"rejectedCaseId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (rejectedCaseId != null) {
			java.io.Serializable pkey = rejectedCaseId;
			RejectedCase object = rejectedCaseService.get(pkey);

			if (object != null) { // edit object

				tmp = new RejectedCaseForm(object);
				String breadcrumb = "<a href=\"case?navigation=detail&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Case Rejection";
				request.setAttribute("breadcrumb", breadcrumb);				

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new RejectedCaseForm();
				// foreign affairs

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");

				if (caseId != null) {
					Case forClass = caseService.get(caseId);
					
					if (forClass != null){
						tmp.setCaseNumber(forClass.getCaseNumber());
						tmp.setCaseId(forClass.getCaseId().toString());
					}

				} else {
					Case forClass = new Case();
					// tmp.setCaseId("");
					tmp.getRejectedCase().setCaseId(forClass);
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new RejectedCaseForm();
			// foreign affairs

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			if (caseId != null) {
				Case forClass = caseService.get(caseId);
				
				if (forClass != null){
					tmp.setCaseNumber(forClass.getCaseNumber());
					tmp.setCaseId(forClass.getCaseId().toString());
				}

			} else {
				Case forClass = new Case();
				// tmp.setCaseId("");
				tmp.getRejectedCase().setCaseId(forClass);
			}

			String breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Reject Case Request";
			request.setAttribute("breadcrumb", breadcrumb);

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		RejectedCaseForm rejectedCaseForm = (RejectedCaseForm) command;
		RejectedCase rejectedCase = rejectedCaseForm.getRejectedCase();
		
		
		System.out.println("ERROR : " + errors);

		// errors.setNestedPath("rejectedCase");
		// getValidator().validate(rejectedCase, errors);
		// errors.setNestedPath("");
		errors.printStackTrace();
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
		
		String[] eqParam = {"tipe"};
		Object[] eqValue = {RejectCategory.REJECT_CASE};
		
		int total = rejectCategoryService.getTotal(null,null,eqParam,eqValue);
		
		Collection<RejectCategory> categories = rejectCategoryService.search(null,null,eqParam,eqValue,0,total);
		
		model.put("rejectCategories", categories);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		RejectedCaseForm rejectedCaseForm = (RejectedCaseForm) command;

		RejectedCase res = null;
		String alertMsg = "";
		String redirectURL = "case?navigation=detail&caseId=";
		try {
			// foreign affairs

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}
			
			if (rejectedCaseForm.getRejectedCase().getCaseId() != null){
				Case theCase = caseService.get(rejectedCaseForm.getRejectedCase().getCaseId().getCaseId());
				rejectedCaseForm.getRejectedCase().setCaseId(theCase);
			}
			

			if (rejectedCaseForm.isNewRejectedCase()) {
				ActionUser user= securityService.getActionUser(request); 
			
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEREJECTEDCASE");
				
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEREJECTEDCASE access");
					return errorResult;
					
				}
				RejectedCase rejectedCase = rejectedCaseForm.getRejectedCase();
				
				res = rejectedCaseService.create(rejectedCase, user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.rejectedcase", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.rejectedcase", null, "fail", Locale
									.getDefault());
				}
				
			} else {
				
				ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEREJECTEDCASE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEREJECTEDCASE access");
				return errorResult;
				
			}
				res = rejectedCaseService.update(rejectedCaseForm
						.getRejectedCase(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.rejectedcase", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.rejectedcase", null, "fail", Locale
									.getDefault());
				}
				
				

			}
			redirectURL += rejectedCaseForm.getRejectedCase().getCaseId().getCaseId();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (rejectedCaseForm.isNewRejectedCase()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.rejectedcase", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.rejectedcase", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(redirectURL));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, cde);
		
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
	}
	// class+

	// class-
}
