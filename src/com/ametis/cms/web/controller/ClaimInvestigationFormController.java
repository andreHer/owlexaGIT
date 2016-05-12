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
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimInvestigation;
import com.ametis.cms.datamodel.InvestigationCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimInvestigationService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.InvestigationCategoryService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ClaimInvestigationForm;

// imports+ 

// imports- 

/**
 * ClaimInvestigation is a mapping of claim_investigation Table.
 */
public class ClaimInvestigationFormController extends SimpleFormController
// extends+

// extends-

{

	ClaimInvestigationService claimInvestigationService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	InvestigationCategoryService investigationCategoryService;
	private SecurityService securityService;

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

	ClaimService claimService;

	public void setClaimService(ClaimService obj) {
		this.claimService = obj;
	}

	public ClaimService getClaimService() {
		return this.claimService;
	}

	// -- foreign affairs end

	public void setClaimInvestigationService(ClaimInvestigationService object) {
		this.claimInvestigationService = object;
	}

	public ClaimInvestigationService getClaimInvestigationService() {
		return this.claimInvestigationService;
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

	public ClaimInvestigationFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ClaimInvestigationForm tmp = null;
		Long claimInvestigationId = WebUtil.getParameterLong(request,
				"claimInvestigationId");
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		Integer index = WebUtil.getParameterInteger(request, "index");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (claimInvestigationId != null) {
			java.io.Serializable pkey = claimInvestigationId;
			ClaimInvestigation object = claimInvestigationService.get(pkey);



			if (object != null) { // edit object

				tmp = new ClaimInvestigationForm(object);
				// foreign affairs

				tmp.setInvestigationCategoryId(object
						.getInvestigationCategoryId().getInvestigationCategoryId().toString());

				
				tmp.setClaimId(object.getClaimId().getClaimId().toString());
				tmp.setClaimNumber(object.getClaimId().getClaimNumber());

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClaimInvestigationForm();
				// foreign affairs

				Integer investigationCategoryId = WebUtil.getParameterInteger(
						request, "investigationCategoryId");

				if (investigationCategoryId != null) {
					

					InvestigationCategory investigationCategory = this.investigationCategoryService
							.get(investigationCategoryId);
					
					if (investigationCategory != null){
						tmp.getClaimInvestigation().setInvestigationCategoryId(
							investigationCategory);
						tmp.setInvestigationCategoryId(investigationCategory.getInvestigationCategoryId().toString());
					}
				}

				if (claimId != null) {
					Claim claim = this.claimService.get(claimId);
					if (claim != null){
						tmp.getClaimInvestigation().setClaimId(claim);
						tmp.setClaimId(claim.getClaimId().toString());
						tmp.setClaimNumber(claim.getClaimNumber());
					}
					
				} 

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClaimInvestigationForm();
			// foreign affairs

			Integer investigationCategoryId = WebUtil.getParameterInteger(
					request, "investigationCategoryId");

			if (investigationCategoryId != null) {

				InvestigationCategory investigationCategory = this.investigationCategoryService
						.get(investigationCategoryId);
				if (investigationCategory != null){
					tmp.getClaimInvestigation().setInvestigationCategoryId(
						investigationCategory);
					tmp.setInvestigationCategoryId(investigationCategoryId.toString());
				}
			}


			if (claimId != null) {

				Claim claim = this.claimService.get(claimId);
				if (claim != null){
					tmp.getClaimInvestigation().setClaimId(claim);
					tmp.setClaimId(claim.getClaimId().toString());
					tmp.setClaimNumber(claim.getClaimNumber());
				}
			}

			// -- foreign affairs end

		}
		
		String breadcrumb = "<a href=\"claim?navigation="+navigation+"&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Claim Navigation";
		request.setAttribute("breadcrumb", breadcrumb);		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		ClaimInvestigationForm claimInvestigationForm = (ClaimInvestigationForm) command;
		ClaimInvestigation claimInvestigation = claimInvestigationForm
				.getClaimInvestigation();
		
		String breadcrumb = "<a href=\"claim?navigation="+navigation+"&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Claim Navigation";
		request.setAttribute("breadcrumb", breadcrumb);
		
		request.setAttribute("navigation", navigation);
		request.setAttribute("claimId", claimId);

		// errors.setNestedPath("claimInvestigation");
		// getValidator().validate(claimInvestigation, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		

		Collection investigationCategories = investigationCategoryService
				.getAll();
		model.put("investigationCategories", investigationCategories);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClaimInvestigationForm claimInvestigationForm = (ClaimInvestigationForm) command;
		String navigation = WebUtil.getParameterString(request,
				"navigation", "");
		Integer claimId = WebUtil.getParameterInteger(request,
				"claimId");

		ClaimInvestigation res = null;
		String alertMsg = "";
		try {
		
			if (claimInvestigationForm.isNewClaimInvestigation()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATECLAIMINVESTIGATION");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATECLAIMINVESTIGATION access");
					return errorResult;

				}
				res = claimInvestigationService.create(claimInvestigationForm
						.getClaimInvestigation(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.claiminvestigation", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.claiminvestigation", null, "fail",
							Locale.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATECLAIMINVESTIGATION");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATECLAIMINVESTIGATION access");
					return errorResult;

				}
				res = claimInvestigationService.update(claimInvestigationForm
						.getClaimInvestigation(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.claiminvestigation", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.claiminvestigation", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (claimInvestigationForm.isNewClaimInvestigation()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.claiminvestigation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.claiminvestigation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		String breadcrumb = "<a href=\"claim?navigation="+navigation+"&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Claim Navigation";
		request.setAttribute("breadcrumb", breadcrumb);
		
		return new ModelAndView(new RedirectView(
				"claiminvestigation?navigation=list&claimId="
						+ claimInvestigationForm.getClaimId()
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
