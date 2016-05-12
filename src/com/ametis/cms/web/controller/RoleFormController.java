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
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.RoleService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.RoleForm;

// imports+ 

// imports- 

/**
 * Role is a mapping of role Table.
 */
public class RoleFormController extends SimpleFormController
// extends+

// extends-

{

	RoleService roleService;
	SecurityService securityService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	// -- foreign affairs end

	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public void setRoleService(RoleService object) {
		this.roleService = object;
	}

	public RoleService getRoleService() {
		return this.roleService;
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

	public RoleFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		RoleForm tmp = null;
		Integer roleId = WebUtil.getParameterInteger(request, "roleId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (roleId != null) {
			java.io.Serializable pkey = roleId;
			Role object = roleService.get(pkey);

			if (object != null) { // edit object

				tmp = new RoleForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new RoleForm();
				// foreign affairs
				// -- foreign affairs end
			}
			String breadcrumb = "<a href=\"role?navigation=detail&roleId="+roleId+"\" class=\"linkbreadcrumb\">Detail Role</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Role";
			request.setAttribute("breadcrumb", breadcrumb);
		} // mau edit end
		else { // bikin baru
			tmp = new RoleForm();
			// foreign affairs
			// -- foreign affairs end
			
			String breadcrumb = "<a href=\"role\" class=\"linkbreadcrumb\">Search Role</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Role";
			request.setAttribute("breadcrumb", breadcrumb);

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		RoleForm roleForm = (RoleForm) command;
		Role role = roleForm.getRole();

		// errors.setNestedPath("role");
		// getValidator().validate(role, errors);
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

		RoleForm roleForm = (RoleForm) command;

		Role res = null;
		String alertMsg = "";
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (roleForm.isNewRole()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEROLE");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEROLE access");
					return errorResult;

				}
				res = roleService.create(roleForm.getRole(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.role", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.role",
							null, "fail", Locale.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEROLE");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEROLE access");
					return errorResult;

				}
				res = roleService.update(roleForm.getRole(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.role", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.role",
							null, "fail", Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (roleForm.isNewRole()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.role", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.role", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("role" + "?alert=" + alertMsg));
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
	}

	// class+
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	// class-

}
