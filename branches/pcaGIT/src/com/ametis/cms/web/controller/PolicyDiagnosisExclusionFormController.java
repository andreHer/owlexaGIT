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
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.service.PolicyDiagnosisExclusionService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyDiagnosisExclusionForm;

// imports+ 

// imports- 

/**
 * PolicyDiagnosisExclusion is a mapping of policy_diagnosis_exclusion Table.
 */
public class PolicyDiagnosisExclusionFormController extends
		SimpleFormController
// extends+

// extends-

{

	PolicyDiagnosisExclusionService policyDiagnosisExclusionService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;

	// foreign affairs

	// -- foreign affairs end

	public void setPolicyDiagnosisExclusionService(
			PolicyDiagnosisExclusionService object) {
		this.policyDiagnosisExclusionService = object;
	}

	public PolicyDiagnosisExclusionService getPolicyDiagnosisExclusionService() {
		return this.policyDiagnosisExclusionService;
	}

	// generate by default
	private UserService actionuserService;

	public UserService getActionUserService() {
		return actionuserService;
	}

	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public void setPropertiesUtil(ResourceBundleMessageSource object) {
		this.alertProperties = object;
	}

	public ResourceBundleMessageSource getPropertiesUtil() {
		return this.alertProperties;
	}

	public void setSecurityService(SecurityService object) {
		this.securityService = object;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	public PolicyDiagnosisExclusionFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		PolicyDiagnosisExclusionForm tmp = null;
		Integer id = WebUtil.getParameterInteger(request, "id");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		Integer memberId= WebUtil.getParameterInteger(request, "memberId");
		Integer index = WebUtil.getParameterInteger(request, "index");
		String searchby = WebUtil.getParameterString(request, "searchby", "");

		String breadcrumb = "";

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (id != null) {
			java.io.Serializable pkey = id;
			PolicyDiagnosisExclusion object = policyDiagnosisExclusionService
					.get(pkey);

			if (object != null) { // edit object

				tmp = new PolicyDiagnosisExclusionForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyDiagnosisExclusionForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyDiagnosisExclusionForm();
			// foreign affairs
			// -- foreign affairs end

		}
		breadcrumb = "<a href=\"policydiagnosisexclusion-upload?navigation="+navigation+"&arah=&policyId="+policyId+"&memberId="+memberId+"&index="+index+"&id=&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">Upload Document</a>";
		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		PolicyDiagnosisExclusionForm policyDiagnosisExclusionForm = (PolicyDiagnosisExclusionForm) command;
		PolicyDiagnosisExclusion policyDiagnosisExclusion = policyDiagnosisExclusionForm
				.getPolicyDiagnosisExclusion();

		// errors.setNestedPath("policyDiagnosisExclusion");
		// getValidator().validate(policyDiagnosisExclusion, errors);
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

		PolicyDiagnosisExclusionForm policyDiagnosisExclusionForm = (PolicyDiagnosisExclusionForm) command;

		PolicyDiagnosisExclusion res = null;
		String alertMsg = "";
		String navigation = WebUtil.getParameterString(request, "navigation",
				"");
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);

			if (policyDiagnosisExclusionForm.isNewPolicyDiagnosisExclusion()) {
				if (navigation.equalsIgnoreCase("upload")) {

					res = policyDiagnosisExclusionService.createUpload(policyDiagnosisExclusionForm
							.getPolicyDiagnosisExclusion(), policyDiagnosisExclusionForm
							.getMultipartFile(), user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.providerdoctor", null,
								"success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.providerdoctor", null, "fail",
								Locale.getDefault());
					}
				} else {
					res = policyDiagnosisExclusionService.create(
							policyDiagnosisExclusionForm
									.getPolicyDiagnosisExclusion(), user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.policydiagnosisexclusion",
								null, "success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.policydiagnosisexclusion", null,
								"fail", Locale.getDefault());
					}
				}
			} else {
				res = policyDiagnosisExclusionService.update(
						policyDiagnosisExclusionForm
								.getPolicyDiagnosisExclusion(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.policydiagnosisexclusion", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.policydiagnosisexclusion", null,
							"fail", Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (policyDiagnosisExclusionForm.isNewPolicyDiagnosisExclusion()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.policydiagnosisexclusion", null, "fail",
						Locale.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.policydiagnosisexclusion", null, "fail",
						Locale.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("policy"
				+ "?alert=" + alertMsg));
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

	// class-

}
