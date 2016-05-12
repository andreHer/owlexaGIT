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
import com.ametis.cms.datamodel.CaseInvestigation;
import com.ametis.cms.datamodel.InvestigationCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseInvestigationService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.InvestigationCategoryService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CaseInvestigationForm;

// imports+ 

// imports- 

/**
 * CaseInvestigation is a mapping of case_investigation Table.
 */
public class CaseInvestigationFormController extends SimpleFormController
// extends+

// extends-

{

	CaseInvestigationService caseInvestigationService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	InvestigationCategoryService investigationCategoryService;
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

	public void setInvestigationCategoryService(InvestigationCategoryService obj) {
		this.investigationCategoryService = obj;
	}

	public InvestigationCategoryService getInvestigationCategoryService() {
		return this.investigationCategoryService;
	}

	CaseService caseService;

	public void setTbCaseService(CaseService obj) {
		this.caseService = obj;
	}

	public CaseService getCaseService() {
		return this.caseService;
	}

	// -- foreign affairs end

	public void setCaseInvestigationService(CaseInvestigationService object) {
		this.caseInvestigationService = object;
	}

	public CaseInvestigationService getCaseInvestigationService() {
		return this.caseInvestigationService;
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

	public CaseInvestigationFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CaseInvestigationForm tmp = null;
		Long caseInvestigationId = WebUtil.getParameterLong(request,
				"caseInvestigationId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb = "";

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (caseInvestigationId != null) {
			java.io.Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object != null) { // edit object

				tmp = new CaseInvestigationForm(object);
				// foreign affairs

				tmp.setInvestigationCategoryId(object
						.getInvestigationCategoryId());

				tmp.setCase(object.getCaseId());
				breadcrumb = "<a href=\"case?navigation=detail&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">Detail Case</a>" +
						" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseinvestigation?navigation=list&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">List Case Investigation</a>"+ 
						" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseinvestigation?navigation=detail&caseInvestigationId="+object.getCaseInvestigationId()+"&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">Detail Case Investigation</a>"+
						" &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Case Investigation";
				request.setAttribute("breadcrumb", breadcrumb);

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseInvestigationForm();
				// foreign affairs

				Integer investigationCategoryId = WebUtil.getParameterInteger(
						request, "investigationCategoryId");

				if (investigationCategoryId != null) {
					InvestigationCategory forClass = new InvestigationCategory();
					forClass
							.setInvestigationCategoryId(investigationCategoryId);
					tmp.setInvestigationCategoryId(forClass);

					InvestigationCategory investigationCategory = this.investigationCategoryService
							.get(investigationCategoryId);
					tmp.getCaseInvestigation().setInvestigationCategoryId(
							investigationCategory);
				} else {
					InvestigationCategory forClass = new InvestigationCategory();
					// tmp.setInvestigationCategoryId("");
					tmp.getCaseInvestigation().setInvestigationCategoryId(
							forClass);
				}

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");

				if (caseId != null) {
					Case forClass = new Case();
					forClass.setCaseId(caseId);
					tmp.setCase(forClass);

					Case tbCase = this.caseService.get(caseId);
					tmp.getCaseInvestigation().setCaseId(tbCase);
				} else {
					Case forClass = new Case();
					// tmp.setCaseId("");
					tmp.getCaseInvestigation().setCaseId(forClass);
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CaseInvestigationForm();
			// foreign affairs

			Integer investigationCategoryId = WebUtil.getParameterInteger(
					request, "investigationCategoryId");

			if (investigationCategoryId != null) {
				InvestigationCategory forClass = new InvestigationCategory();
				forClass.setInvestigationCategoryId(investigationCategoryId);
				tmp.setInvestigationCategoryId(forClass);

				InvestigationCategory investigationCategory = this.investigationCategoryService
						.get(investigationCategoryId);
				tmp.getCaseInvestigation().setInvestigationCategoryId(
						investigationCategory);
			} else {
				InvestigationCategory forClass = new InvestigationCategory();
				// tmp.setInvestigationCategoryId("");
				tmp.getCaseInvestigation().setInvestigationCategoryId(forClass);
			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			if (caseId != null) {
				Case forClass = new Case();
				forClass.setCaseId(caseId);
				tmp.setCase(forClass);

				Case tbCase = caseService.get(caseId);
				tmp.getCaseInvestigation().setCaseId(tbCase);
			} else {
				Case forClass = new Case();
				// tmp.setCaseId("");
				tmp.getCaseInvestigation().setCaseId(forClass);
			}

			// -- foreign affairs end
	        if (navigation.equalsIgnoreCase("tambah")) {
	        	breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Investigation";
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

		CaseInvestigationForm caseInvestigationForm = (CaseInvestigationForm) command;
		CaseInvestigation caseInvestigation = caseInvestigationForm
				.getCaseInvestigation();
		
        if (navigation.equalsIgnoreCase("tambah")) {
        	breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Investigation";
        }
        
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
		

		System.out.println("ERROR : " + errors);

		// errors.setNestedPath("caseInvestigation");
		// getValidator().validate(caseInvestigation, errors);
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

		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		
		if (navigation.equalsIgnoreCase("extend")){
			InvestigationCategory iv = investigationCategoryService.get(-1);
			Collection investigationCategories = new java.util.Vector<InvestigationCategory>();
			investigationCategories.add(iv);
			model.put("investigationCategories", investigationCategories);
		}
		else {
			Collection investigationCategories = investigationCategoryService
					.getAll();
			model.put("investigationCategories", investigationCategories);
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

		CaseInvestigationForm caseInvestigationForm = (CaseInvestigationForm) command;

		CaseInvestigation res = null;
		String alertMsg = "";
		try {

			// foreign affairs
			// if(caseInvestigationForm.getInvestigationCategoryId() != null){
			// caseInvestigationForm.getCaseInvestigation().setInvestigationCategoryId(
			// this.investigationCategoryService.get(
			// new Integer((caseInvestigationForm.getInvestigationCategoryId()))
			// )
			// );
			// }
			//	
			// if(caseInvestigationForm.getCaseId() != null){
			// caseInvestigationForm.getCaseInvestigation().setCaseId(
			// this.tbCaseService.get(
			// new Integer((caseInvestigationForm.getCaseId()))
			// )
			// );
			// }

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");

			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (caseInvestigationForm.isNewCaseInvestigation()) {

				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATECASEINVESTIGATION");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATECASEINVESTIGATIOn access");
					return errorResult;

				}
				res = caseInvestigationService.create(caseInvestigationForm
						.getCaseInvestigation(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.caseinvestigation", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.caseinvestigation", null, "fail",
							Locale.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATECASEINVESTIGATION");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATECASEINVESTIGATION access");
					return errorResult;

				}
				res = caseInvestigationService.update(caseInvestigationForm
						.getCaseInvestigation(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.caseinvestigation", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.caseinvestigation", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (caseInvestigationForm.isNewCaseInvestigation()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.caseinvestigation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.caseinvestigation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
        if (navigation.equalsIgnoreCase("tambah")) {
        	breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Case Investigation";
        }
        
        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
		
		return new ModelAndView(new RedirectView(
				"caseinvestigation?navigation=list&caseId="
						+ caseInvestigationForm.getCase().getCaseId()
						+ "&alert=" + alertMsg));
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
		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numInt);
	}
	// class+

	// class-

}
