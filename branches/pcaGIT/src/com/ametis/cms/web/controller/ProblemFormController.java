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
import com.ametis.cms.datamodel.Problem;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.ProblemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProblemForm;

// imports+ 

// imports- 

/**
 * Problem is a mapping of problem Table.
 */
public class ProblemFormController extends SimpleFormController
// extends+

// extends-
{

	ProblemService problemService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	SecurityService securityService;

	private ClaimService claimService;
	private BatchClaimService batchClaimService;
	private PaymentService paymentService;
	private ExcessChargeService excessChargeService;
	private ClaimItemService claimItemService;
	
	// -- foreign affairs end
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	public void setProblemService(ProblemService object) {
		this.problemService = object;
	}

	public ProblemService getProblemService() {
		return this.problemService;
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

	public ProblemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ProblemForm tmp = null;
		Integer problemId = WebUtil.getParameterInteger(request, "problemId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (problemId != null) {
			java.io.Serializable pkey = problemId;
			Problem object = problemService.get(pkey);

			if (object != null) { // edit object

				tmp = new ProblemForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProblemForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			
			
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			
			Claim claim = claimService.get(claimId);
			
			tmp = new ProblemForm();
			
			
			
			if (claim != null){
				tmp.setProblemSubject(claim.getClaimNumber());
			}
			// foreign affairs
			// -- foreign affairs end

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ProblemForm problemForm = (ProblemForm) command;
		Problem problem = problemForm.getProblem();

		// errors.setNestedPath("problem");
		// getValidator().validate(problem, errors);
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

		String type = WebUtil.getParameterString(request, "problemType", "");
		model.put("problemType", type);
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ProblemForm problemForm = (ProblemForm) command;

		Problem res = null;
		String alertMsg = "";
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}
			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CREATEPROBLEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for CREATEPROBLEM access");
				return errorResult;

			}

			if (problemForm.isNewProblem()) {

				res = problemService.create(problemForm.getProblem(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.problem", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.problem", null, "fail", Locale
									.getDefault());
				}
			} else {
				res = problemService.update(problemForm.getProblem(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.problem", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.problem", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (problemForm.isNewProblem()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.problem", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.problem", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("problem" + "?alert="
				+ alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"yyyy/MM/dd"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
	}

	// class+
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	// class-
}
