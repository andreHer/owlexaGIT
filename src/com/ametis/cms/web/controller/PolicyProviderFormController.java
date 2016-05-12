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
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyProvider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyProviderForm;

// imports+

// imports-

/**
 * PolicyProvider is a mapping of policy_provider Table.
 */
public class PolicyProviderFormController extends SimpleFormController
// extends+

// extends-
{

	PolicyProviderService policyProviderService;
	private PolicyService policyService;

	ResourceBundleMessageSource alertProperties;
	private ActivityLogService logService;
	private SecurityService securityService;

	// foreign affairs

	// -- foreign affairs end

	public void setPolicyProviderService(PolicyProviderService object) {
		this.policyProviderService = object;
	}

	public PolicyProviderService getPolicyProviderService() {
		return this.policyProviderService;
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

	public PolicyProviderFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		PolicyProviderForm tmp = null;
		Integer id = WebUtil.getParameterInteger(request, "id");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (id != null) {
			java.io.Serializable pkey = id;
			PolicyProvider object = policyProviderService.get(pkey);

			if (object != null) { // edit object

				tmp = new PolicyProviderForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyProviderForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyProviderForm();
			// foreign affairs
			// -- foreign affairs end

			if (policyId != null) {
				Policy policy = policyService.get(policyId);

				if (policy != null) {
					tmp.setPolicyId(policy.getPolicyId().toString());
				}
			}

		}
		
		String breadcrumb = "";
	    if (navigation.equalsIgnoreCase ("listpolicy")){
			breadcrumb = "<a href=\"policyprovider?navigation="+navigation+"&arah=&index=1&id=&policyId="+policyId+"&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">List Policy Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Provider";
	    }else{
			breadcrumb = "<a href=\"policyprovider?navigation="+navigation+"&arah=&index=1&id=&policyId="+policyId+"&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">List Policy Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Provider";
	    }
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);
		request.setAttribute("navigation", navigation);
		request.setAttribute("searchby", searchby);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");

		PolicyProviderForm policyProviderForm = (PolicyProviderForm) command;
		PolicyProvider policyProvider = policyProviderForm.getPolicyProvider();

		String breadcrumb = "";
	    if (navigation.equalsIgnoreCase ("listpolicy")){
			breadcrumb = "<a href=\"policyprovider?navigation="+navigation+"&arah=&index=1&id=&policyId="+policyId+"&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">List Policy Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Provider";
	    }else{
			breadcrumb = "<a href=\"policyprovider?navigation="+navigation+"&arah=&index=1&id=&policyId="+policyId+"&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">List Policy Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Provider";
	    }
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);
		request.setAttribute("navigation", navigation);
		request.setAttribute("searchby", searchby);
		
		// errors.setNestedPath("policyProvider");
		// getValidator().validate(policyProvider, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		try {
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			if (policyId != null) {
				Policy policy = policyService.get(policyId);
				model.put("policyNumber", policy.getPolicyNumber());
				model.put("policyId", policyId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PolicyProviderForm policyProviderForm = (PolicyProviderForm) command;

		PolicyProvider res = null;
		String alertMsg = "";
		String navigation = WebUtil.getParameterString(request, "navigation",
				"");
		
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		
		try {

			ActionUser user = securityService.getActionUser(request);

			if (policyProviderForm.isNewPolicyProvider()) {
				if (navigation.equalsIgnoreCase("upload")) {

					res = policyProviderService.createUpload(policyProviderForm
							.getPolicyProvider(), policyProviderForm
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
					res = policyProviderService.create(policyProviderForm
							.getPolicyProvider(), user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.policyprovider", null,
								"success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.policyprovider", null, "fail",
								Locale.getDefault());
					}
				}
			} else {
				res = policyProviderService.update(policyProviderForm
						.getPolicyProvider(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.policyprovider", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.policyprovider", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (policyProviderForm.isNewPolicyProvider()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.policyprovider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.policyprovider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "";
	    if (navigation.equalsIgnoreCase ("listpolicy")){
			breadcrumb = "<a href=\"policyprovider?navigation="+navigation+"&arah=&index=1&id=&policyId="+policyId+"&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">List Policy Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Provider";
	    }else{
			breadcrumb = "<a href=\"policyprovider?navigation="+navigation+"&arah=&index=1&id=&policyId="+policyId+"&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">List Policy Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Provider";
	    }
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);
		request.setAttribute("navigation", navigation);
		request.setAttribute("searchby", searchby);
		
		return new ModelAndView(new RedirectView("policyprovider" + "?alert="
				+ alertMsg + "&policyId=" + policyProviderForm.getPolicyId()));
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
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	// class-
}
