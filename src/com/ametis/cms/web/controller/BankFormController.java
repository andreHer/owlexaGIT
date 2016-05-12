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
import com.ametis.cms.datamodel.Bank;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BankService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.BankForm;

// imports+

// imports-

/**
 * Bank is a mapping of bank Table.
 */
public class BankFormController extends SimpleFormController
// extends+

// extends-

{

	BankService bankService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	// foreign affairs

	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	// -- foreign affairs end

	public void setBankService(BankService object) {
		this.bankService = object;
	}

	public BankService getBankService() {
		return this.bankService;
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

	public void setSecurityService(SecurityService object) {
		this.securityService = object;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	public BankFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		BankForm tmp = null;
		Integer bankId = WebUtil.getParameterInteger(request, "bankId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (bankId != null) {
			java.io.Serializable pkey = bankId;
			Bank object = bankService.get(pkey);

			if (object != null) { // edit object

				tmp = new BankForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new BankForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new BankForm();
			// foreign affairs
			// -- foreign affairs end

		}
		
		String breadcrumb = "";
		breadcrumb = "<a href=\"bank-form\" class=\"linkbreadcrumb\">Register Bank</a>";
		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		BankForm bankForm = (BankForm) command;
		Bank bank = bankForm.getBank();

		// errors.setNestedPath("bank");
		// getValidator().validate(bank, errors);
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

		BankForm bankForm = (BankForm) command;

		Bank res = null;
		String alertMsg = "";
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);

			if (bankForm.isNewBank()) {

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEBANK");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEBANK access");
					return errorResult;

				}
				res = bankService.create(bankForm.getBank(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.bank", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.bank",
							null, "fail", Locale.getDefault());
				}
			} else {

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEBANK");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEBANK access");
					return errorResult;

				}
				res = bankService.update(bankForm.getBank(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.bank", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.bank",
							null, "fail", Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (bankForm.isNewBank()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.bank", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.bank", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("bank" + "?alert=" + alertMsg));
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
