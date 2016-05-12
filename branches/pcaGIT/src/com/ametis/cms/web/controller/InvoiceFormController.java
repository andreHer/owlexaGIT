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
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.InvoiceService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.InvoiceForm;

// imports+ 

// imports- 

/**
 * Invoice is a mapping of invoice Table.
 */
public class InvoiceFormController extends SimpleFormController
// extends+

// extends-

{

	private ConfigurationService configurationService;
	
	private ActivityLogService logService;

	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	InvoiceService invoiceService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	// -- foreign affairs end

	public void setInvoiceService(InvoiceService object) {
		this.invoiceService = object;
	}

	public InvoiceService getInvoiceService() {
		return this.invoiceService;
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

	public InvoiceFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		InvoiceForm tmp = null;
		Integer invoiceId = WebUtil.getParameterInteger(request, "invoiceId");

		
		if (invoiceId != null) {
			java.io.Serializable pkey = invoiceId;
			Invoice object = invoiceService.get(pkey);

			if (object != null) { // edit object

				tmp = new InvoiceForm(object);
				
				String breadcrumb = "<a href=\"invoice?navigation=detail&invoiceId="+invoiceId+"\" class=\"linkbreadcrumb\">Detail Invoice</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Invoice";
				request.setAttribute("breadcrumb", breadcrumb);
				
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new InvoiceForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new InvoiceForm();
			tmp.setInvoiceDate(new java.sql.Date(System.currentTimeMillis()).toString());
			// foreign affairs
			
			String breadcrumb = "<a href=\"invoice\" class=\"linkbreadcrumb\">Manage Invoice</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Invoice";
			request.setAttribute("breadcrumb", breadcrumb);
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		InvoiceForm invoiceForm = (InvoiceForm) command;
		Invoice invoice = invoiceForm.getInvoice();

		// errors.setNestedPath("invoice");
		// getValidator().validate(invoice, errors);
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

		InvoiceForm invoiceForm = (InvoiceForm) command;

		Invoice res = null;
		String alertMsg = "";
		String redirectUrl = "";
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (invoiceForm.isNewInvoice()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEINVOICE");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEINVOICE access");
					return errorResult;

				}
				res = invoiceService.create(invoiceForm.getInvoice(), user);

				if (res != null) {
					redirectUrl = "excesscharge?navigation=searchunassigned&invoiceId="+res.getInvoiceId()+"&memberGroupId="+res.getMemberGroupId().getMemberGroupId();
					alertMsg = alertProperties.getMessage(
							"success.create.invoice", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.invoice", null, "fail", Locale
									.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEINVOICE");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEINVOICE access");
					return errorResult;

				}
				res = invoiceService.update(invoiceForm.getInvoice(), user);

				if (res != null) {
					redirectUrl = "invoice?navigation=detail&invoiceId="+res.getInvoiceId();
					alertMsg = alertProperties.getMessage(
							"success.update.invoice", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.invoice", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (invoiceForm.isNewInvoice()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.invoice", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.invoice", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(redirectUrl));
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

	// class-

}
