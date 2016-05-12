package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource; /*
 import org.acegisecurity.Authentication;
 import org.acegisecurity.context.SecurityContextHolder;
 import org.acegisecurity.userdetails.UserDetails;
 */
import java.util.Locale;
import java.util.Collection;
import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import com.ametis.cms.service.*;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.*;

// imports+

// imports-

/**
 * ProviderService is a mapping of provider_service Table.
 */
public class ProviderServiceFormController extends SimpleFormController
// extends+

// extends-

{

	ProviderServiceService providerServiceService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	// foreign affairs

	CaseCategoryService caseCategoryService;

	public void setCaseCategoryService(CaseCategoryService obj) {
		this.caseCategoryService = obj;
	}

	public CaseCategoryService getCaseCategoryService() {
		return this.caseCategoryService;
	}

	ProviderService providerService;

	public void setProviderService(ProviderService obj) {
		this.providerService = obj;
	}

	public ProviderService getProviderService() {
		return this.providerService;
	}

	// -- foreign affairs end

	public void setProviderServiceService(ProviderServiceService object) {
		this.providerServiceService = object;
	}

	public ProviderServiceService getProviderServiceService() {
		return this.providerServiceService;
	}

	// generate by default
	private UserService actionuserService;

	public UserService getUserService() {
		return actionuserService;
	}

	public void setUserService(UserService userService) {
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

	public ProviderServiceFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ProviderServiceForm tmp = null;
		Integer providerServiceId = WebUtil.getParameterInteger(request,
				"providerServiceId");
		Integer providerId = WebUtil.getParameterInteger(request,
				"providerId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (providerServiceId != null) {
			java.io.Serializable pkey = providerServiceId;
			com.ametis.cms.datamodel.ProviderService object = providerServiceService
					.get(pkey);

			if (object != null) { // edit object

				tmp = new ProviderServiceForm(object);
				// foreign affairs

				tmp.setCaseCategoryId(""
						+ object.getCaseCategoryId().getCaseCategoryId());

				tmp.setProviderId("" + object.getProviderId().getProviderId());
				
				if (object.getProviderId() != null){
					Provider provider = providerService.get(object.getProviderId().getProviderId());
					
					if (provider != null){
						tmp.setProviderName(provider.getProviderName());
					}
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderServiceForm();
				// foreign affairs

				Integer caseCategoryId = WebUtil.getParameterInteger(request,
						"caseCategoryId");

				if (caseCategoryId != null) {
					CaseCategory forClass = new CaseCategory();
					forClass.setCaseCategoryId(caseCategoryId);
					tmp.setCaseCategoryId("" + caseCategoryId);

					CaseCategory caseCategory = this.caseCategoryService
							.get(caseCategoryId);
					tmp.getProviderService().setCaseCategoryId(caseCategory);
				} else {

				}

//				Integer providerId = WebUtil.getParameterInteger(request,
//						"providerId");

				if (providerId != null) {
					Provider forClass = new Provider();
					forClass.setProviderId(providerId);
					tmp.setProviderId("" + providerId);

					Provider provider = this.providerService.get(providerId);
					tmp.getProviderService().setProviderId(provider);
					tmp.setProviderName(provider.getProviderName());
				} else {

				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderServiceForm();
			// foreign affairs

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			if (caseCategoryId != null) {
				CaseCategory forClass = new CaseCategory();
				forClass.setCaseCategoryId(caseCategoryId);
				tmp.setCaseCategoryId("" + caseCategoryId);

				CaseCategory caseCategory = this.caseCategoryService
						.get(caseCategoryId);
				tmp.getProviderService().setCaseCategoryId(caseCategory);
			} else {

			}

//			Integer providerId = WebUtil.getParameterInteger(request,
//					"providerId");

			if (providerId != null) {
				tmp.setProviderId("" + providerId);
				Provider provider = this.providerService.get(providerId);
				tmp.getProviderService().setProviderId(provider);
				if (provider != null) {
					tmp.setProviderName(provider.getProviderName());
				}
			} else {

			}

			// -- foreign affairs end

		}
		String breadcrumb = "<a href=\"providerservice?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Procedure</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Service";
		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		
		Integer providerId = WebUtil.getParameterInteger(request,
				"providerId");

		ProviderServiceForm providerServiceForm = (ProviderServiceForm) command;
		com.ametis.cms.datamodel.ProviderService providerService = providerServiceForm
				.getProviderService();
		
		String breadcrumb = "<a href=\"providerservice?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Procedure</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Service";
		request.setAttribute("breadcrumb", breadcrumb);

		// errors.setNestedPath("providerService");
		// getValidator().validate(providerService, errors);
		// errors.setNestedPath("");
		errors.printStackTrace();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		Collection<CaseCategory> cc = caseCategoryService.getAll();
		model.put("itemCategories", cc);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		Integer providerId = WebUtil.getParameterInteger(request,
				"providerId");

		ProviderServiceForm providerServiceForm = (ProviderServiceForm) command;

		com.ametis.cms.datamodel.ProviderService res = null;
		String alertMsg = "";
		try {


			ActionUser user = securityService.getActionUser(request);

			if (providerServiceForm.isNewProviderService()) {
				
				String[] eqParam = {"caseCategoryId.caseCategoryId","providerId.providerId","deletedStatus"};
		    	
				Object[] eqValue = {Integer.valueOf(providerServiceForm.getCaseCategoryId()),Integer.valueOf(providerServiceForm.getProviderId()), 
									Integer.valueOf(0)};
    	
				com.ametis.cms.datamodel.ProviderService checkProvServ = providerServiceService.searchUnique(eqParam, eqValue, 0, 1);
				if(checkProvServ == null){
					res = providerServiceService.create(providerServiceForm
							.getProviderService(), user);
					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.providerservice", null, "success",
								Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.providerservice", null, "fail", Locale
										.getDefault());
					}
				}else{
					System.out.println("MASUK TIDAK NULL");
					request.setAttribute("alertService", "Service already exist. Please select another Service.");
					return showForm(request, response, errors);
				}

			} else {
				String[] eqParam = {"caseCategoryId.caseCategoryId","providerId.providerId","deletedStatus"};
		    	
				Object[] eqValue = {Integer.valueOf(providerServiceForm.getCaseCategoryId()),Integer.valueOf(providerServiceForm.getProviderId()), 
									Integer.valueOf(0)};
				com.ametis.cms.datamodel.ProviderService checkProvServ = providerServiceService.searchUnique(eqParam, eqValue, 0, 1);
				if(checkProvServ == null){
					res = providerServiceService.update(providerServiceForm
							.getProviderService(), user);
	
					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.update.providerservice", null, "success",
								Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.update.providerservice", null, "fail", Locale
										.getDefault());
					}
				}else{
					if(checkProvServ.getProviderServiceId().toString().equals(providerServiceForm.getProviderServiceId())){
						res = providerServiceService.update(providerServiceForm
								.getProviderService(), user);
		
						if (res != null) {
							alertMsg = alertProperties.getMessage(
									"success.update.providerservice", null, "success",
									Locale.getDefault());
						} else {
							alertMsg = alertProperties.getMessage(
									"fail.update.providerservice", null, "fail", Locale
											.getDefault());
						}
					}else{
						System.out.println("MASUK TIDAK NULL");
						request.setAttribute("alertService", "Service already exist. Please select another Service.");
						return showForm(request, response, errors);
					}
						
				}

			}
		} catch (Exception ex) {
			if (providerServiceForm.isNewProviderService()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.providerservice", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.providerservice", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		String breadcrumb = "<a href=\"providerservice?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Procedure</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Service";
		request.setAttribute("breadcrumb", breadcrumb);

		
		return new ModelAndView(new RedirectView("providerservice" + "?alert="
				+ alertMsg+"&providerId="+providerServiceForm.getProviderId()));
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
