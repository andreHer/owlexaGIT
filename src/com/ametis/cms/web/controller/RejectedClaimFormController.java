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
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.RejectCategoryService;
import com.ametis.cms.service.RejectedClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.RejectedClaimForm;

// imports+ 

// imports- 

/**
 * RejectedClaim is a mapping of rejected_claim Table.
 */
public class RejectedClaimFormController extends SimpleFormController
// extends+

// extends-

{

	RejectedClaimService rejectedClaimService;

	ResourceBundleMessageSource alertProperties;

	RejectCategoryService rejectCategoryService;

	ClaimService claimService;
	SecurityService securityService;

	// foreign affairs

	// -- foreign affairs end
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public void setRejectedClaimService(RejectedClaimService object) {
		this.rejectedClaimService = object;
	}

	public RejectedClaimService getRejectedClaimService() {
		return this.rejectedClaimService;
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

	public RejectedClaimFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		RejectedClaimForm tmp = null;
		Long rejectedClaimId = WebUtil.getParameterLong(request,
				"rejectedClaimId");
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (rejectedClaimId != null) {
			java.io.Serializable pkey = rejectedClaimId;

			RejectedClaim object = rejectedClaimService.get(pkey);

			if (object != null) { // edit object

				tmp = new RejectedClaimForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new RejectedClaimForm();
				// foreign affairs
				// -- foreign affairs end
			}
			tmp.setRejectionCategory(new RejectCategory());
		} // mau edit end
		else { // bikin baru
			tmp = new RejectedClaimForm();
			tmp.setRejectionCategory(new RejectCategory());
			// foreign affairs
			// -- foreign affairs end
			String breadcrumb = "<a href=\"claim?navigation=detail&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Reject Claim";
			request.setAttribute("breadcrumb", breadcrumb);
		}

		if (claimId != null && tmp != null) {
			Claim claim = claimService.get(claimId);
			
			if (claim != null){
				tmp.setClaimId(claim.getClaimId().toString());
				tmp.setClaimNumber(claim.getClaimNumber());
			}
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		RejectedClaimForm rejectedClaimForm = (RejectedClaimForm) command;
		RejectedClaim rejectedClaim = rejectedClaimForm.getRejectedClaim();

		// errors.setNestedPath("rejectedClaim");
		// getValidator().validate(rejectedClaim, errors);
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

		Collection rejectCategory = rejectCategoryService.getAll();
		model.put("rejectCategory", rejectCategory);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		RejectedClaimForm rejectedClaimForm = (RejectedClaimForm) command;

		RejectedClaim res = null;
		String alertMsg = "";
		String redirectURL = "claim?navigation=detail&claimId=";

		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (rejectedClaimForm.isNewRejectedClaim()) {

				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEREJECTEDCLAIM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATEREJECTEDCLAIM access");
					return errorResult;

				}
				
				
				res = rejectedClaimService.create(rejectedClaimForm
						.getRejectedClaim(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.rejectedclaim", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.rejectedclaim", null, "fail", Locale
									.getDefault());
				}
				redirectURL += res.getClaimId().getClaimId() + "&alert="
						+ alertMsg;
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEREJECTEDCLAIM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATEREJECTEDCLAIM access");
					return errorResult;

				}
				res = rejectedClaimService.update(rejectedClaimForm
						.getRejectedClaim(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.rejectedclaim", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.rejectedclaim", null, "fail", Locale
									.getDefault());
				}

				redirectURL += res.getClaimId().getClaimId() + "&alert="
						+ alertMsg;
			}
		} catch (Exception ex) {
			if (rejectedClaimForm.isNewRejectedClaim()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.rejectedclaim", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.rejectedclaim", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
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

		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numInt);

	}

	// class+
	public RejectCategoryService getRejectCategoryService() {
		return rejectCategoryService;
	}

	public void setRejectCategoryService(
			RejectCategoryService rejectCategoryService) {
		this.rejectCategoryService = rejectCategoryService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	// class-

}
