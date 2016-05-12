package com.ametis.cms.web.controller;

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
import com.ametis.cms.datamodel.ClaimCompletion;
import com.ametis.cms.datamodel.PendingClaim;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimCompletionService;
import com.ametis.cms.service.PendingClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ClaimCompletionForm;

// imports+ 

// imports- 

/**
 * ClaimCompletion is a mapping of claim_completion Table.
 */
public class ClaimCompletionFormController extends SimpleFormController
// extends+

// extends-
{

	ClaimCompletionService claimCompletionService;

	ResourceBundleMessageSource alertProperties;

	PendingClaimService pendingClaimService;

	// foreign affairs

	// -- foreign affairs end
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

	public void setClaimCompletionService(ClaimCompletionService object) {
		this.claimCompletionService = object;
	}

	public ClaimCompletionService getClaimCompletionService() {
		return this.claimCompletionService;
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

	public ClaimCompletionFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ClaimCompletionForm tmp = null;
		Long completeClaimId = WebUtil.getParameterLong(request,
				"completeClaimId");

		Integer pendingClaimId = WebUtil.getParameterInteger(request,
				"pendingClaimId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (completeClaimId != null) {
			java.io.Serializable pkey = completeClaimId;
			ClaimCompletion object = claimCompletionService.get(pkey);

			if (object != null) { // edit object

				tmp = new ClaimCompletionForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClaimCompletionForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClaimCompletionForm();
			// foreign affairs
			// -- foreign affairs end
			
		}

		if (pendingClaimId != null) {
			PendingClaim pendingClaim = pendingClaimService.get(pendingClaimId);

			if (pendingClaim != null) {
				tmp.setPendingClaimId(pendingClaim.getPendingClaimId().toString());
				tmp.setClaimId(pendingClaim.getClaimId().getClaimId().toString());
				tmp.setClaimNumber(pendingClaim.getClaimId().getClaimNumber());
				tmp.setPendingClaimNumber(pendingClaim.getPendingNumber());
				
				if (completeClaimId == null){
				
					String breadcrumb = "<a href=\"claim?navigation=detail&claimId="+pendingClaim.getClaimId().getClaimId()+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Complete Pending Claim";
					request.setAttribute("breadcrumb", breadcrumb);
				}
			}
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ClaimCompletionForm claimCompletionForm = (ClaimCompletionForm) command;
		ClaimCompletion claimCompletion = claimCompletionForm
				.getClaimCompletion();

		// errors.setNestedPath("claimCompletion");
		// getValidator().validate(claimCompletion, errors);
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

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClaimCompletionForm claimCompletionForm = (ClaimCompletionForm) command;

		ClaimCompletion res = null;
		String alertMsg = "";
		String claimId = "";
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ClaimCompletion claimCompletion = claimCompletionForm
					.getClaimCompletion();
			if (claimCompletion != null) {

				PendingClaim pendingClaim = claimCompletion.getPendingClaimId();

				if (pendingClaim != null) {
					Claim claim = pendingClaim.getClaimId();

					if (claim != null) {
						claimId = claim.getClaimId().toString();
					}
				}

			}
			if (claimCompletionForm.isNewClaimCompletion()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATECLAIMCOMPLETION");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATECLAIMCOMPLETION access");
					return errorResult;

				}
				res = claimCompletionService.create(claimCompletionForm
						.getClaimCompletion(), user);

				if (res != null) {
					PendingClaim pending = pendingClaimService.get(res.getPendingClaimId().getPendingClaimId());
					claimId = pending.getClaimId().getClaimId().toString();
					alertMsg = alertProperties.getMessage(
							"success.create.claimcompletion", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.claimcompletion", null, "fail", Locale
									.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATECLAIMCOMPLETION");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATECLAIMCOMPLETION access");
					return errorResult;

				}
				res = claimCompletionService.update(claimCompletionForm
						.getClaimCompletion(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.claimcompletion", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.claimcompletion", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (claimCompletionForm.isNewClaimCompletion()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.claimcompletion", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.claimcompletion", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(
				"claim?navigation=detail&claimId=" + claimId + "&alert="
						+ alertMsg));
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

	public PendingClaimService getPendingClaimService() {
		return pendingClaimService;
	}

	public void setPendingClaimService(PendingClaimService pendingClaimService) {
		this.pendingClaimService = pendingClaimService;
	}

	// class-
}
