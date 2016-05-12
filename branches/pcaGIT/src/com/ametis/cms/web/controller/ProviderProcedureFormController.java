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
import com.ametis.cms.datamodel.Procedure;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderProcedure;
import com.ametis.cms.service.ProcedureService;
import com.ametis.cms.service.ProviderProcedureService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProviderProcedureForm;

// imports+

// imports-

/**
 * ProviderProcedure is a mapping of provider_procedure Table.
 */
public class ProviderProcedureFormController extends SimpleFormController
// extends+

// extends-
{

	ProviderProcedureService providerProcedureService;
	ResourceBundleMessageSource alertProperties;
	private ProcedureService procedureService;
	private SecurityService securityService;
	private ProviderService providerService;

	// foreign affairs


	// -- foreign affairs end
	
	public void setProviderProcedureService(ProviderProcedureService object) {
		this.providerProcedureService = object;
	}

	public ProviderProcedureService getProviderProcedureService() {
		return this.providerProcedureService;
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

	
	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ProviderProcedureFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ProviderProcedureForm tmp = null;
		Integer providerProcedureId = WebUtil.getParameterInteger(request,
				"providerProcedureId");
		
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (providerProcedureId != null) {
			java.io.Serializable pkey = providerProcedureId;
			ProviderProcedure object = providerProcedureService.get(pkey);

			if (object != null) { // edit object

				tmp = new ProviderProcedureForm(object);

				tmp.setProviderId(object.getProviderId().getProviderId().toString());
				tmp.setProcedureId(object.getProcedureId().getProcedureId().toString());
				
				Provider provider = providerService.get(object.getProviderId().getProviderId());
				
				if (provider != null){
					tmp.setProviderName(provider.getProviderName());
				}
				Procedure procedure = procedureService.get(object.getProcedureId().getProcedureId()); 
				
				if (procedure != null){
					tmp.setProcedureName(procedure.getProcedureName());
				}
				
				
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderProcedureForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderProcedureForm();
			// foreign affairs
			// -- foreign affairs end
			if (providerId != null){
				Provider provider = providerService.get(providerId);
				
				if (provider != null){
					tmp.setProviderName(provider.getProviderName());
					tmp.setProviderId(provider.getProviderId().toString());				
				}
			}

		}
		String breadcrumb = "<a href=\"providerprocedure?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Procedure </a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Upload Procedure";
		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ProviderProcedureForm providerProcedureForm = (ProviderProcedureForm) command;
		ProviderProcedure providerProcedure = providerProcedureForm
				.getProviderProcedure();

		// errors.setNestedPath("providerProcedure");
		// getValidator().validate(providerProcedure, errors);
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

		ProviderProcedureForm providerProcedureForm = (ProviderProcedureForm) command;

		ProviderProcedure res = null;
		String alertMsg = "";
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String providerId = providerProcedureForm.getProviderId();
		try {
			

			ActionUser user = securityService.getActionUser(request);

			if (providerProcedureForm.isNewProviderProcedure()) {
				if (navigation.equalsIgnoreCase("upload")){
					res = providerProcedureService.createUpload(providerProcedureForm
							.getProviderProcedure(),providerProcedureForm.getProcedureFile(), user);
	
					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.providerprocedure", null,
								"success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.providerprocedure", null, "fail",
								Locale.getDefault());
					}
				}
				else {
					res = providerProcedureService.create(providerProcedureForm
							.getProviderProcedure(), user);
	
					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.providerprocedure", null,
								"success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.providerprocedure", null, "fail",
								Locale.getDefault());
					}
				}
			} else {
				res = providerProcedureService.update(providerProcedureForm
						.getProviderProcedure(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.providerprocedure", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.providerprocedure", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (providerProcedureForm.isNewProviderProcedure()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.providerprocedure", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.providerprocedure", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("providerprocedure"
				+ "?providerId="+providerId+"&alert=" + alertMsg));
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

	public ProcedureService getProcedureService() {
		return procedureService;
	}

	public void setProcedureService(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}

	// class-
}
