package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

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
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderGroup;
import com.ametis.cms.datamodel.ProviderType;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProviderCategoryService;
import com.ametis.cms.service.ProviderGroupService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderTypeService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProviderForm;

// imports+ 

// imports- 

/**
 * Provider is a mapping of provider Table.
 */
public class ProviderFormController extends SimpleFormController
// extends+

// extends-

{

	ProviderService providerService;
	private PoliklinikService poliklinikService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	private ProviderGroupService providerGroupService;
	ProviderCategoryService providerCategoryService;

	SecurityService securityService;
private ActivityLogService logService;
	private CurrencyService currencyService;
	
	private ProviderTypeService providerTypeService;
	
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

	public void setProviderCategoryService(ProviderCategoryService obj) {
		this.providerCategoryService = obj;
	}

	public ProviderCategoryService getProviderCategoryService() {
		return this.providerCategoryService;
	}

	// -- foreign affairs end

	public void setProviderService(ProviderService object) {
		this.providerService = object;
	}

	public ProviderService getProviderService() {
		return this.providerService;
	}
	
	
	
	public ProviderGroupService getProviderGroupService() {
		return providerGroupService;
	}

	public void setProviderGroupService(ProviderGroupService providerGroupService) {
		this.providerGroupService = providerGroupService;
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

	public ProviderFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ProviderForm tmp = null;
		String navigation = request.getParameter("navigation");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		Integer memberId = WebUtil.getParameterInteger(request, "memberId");
		Integer index = WebUtil.getParameterInteger(request, "index");
		String searchby = WebUtil.getParameterString(request, "searchby", "");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (providerId != null) {
			java.io.Serializable pkey = providerId;
			Provider object = providerService.get(pkey);

			if (object != null) { // edit object

				tmp = new ProviderForm(object);
				// foreign affairs

				tmp.setProviderCategoryId(""
						+ object.getProviderCategoryId()
								.getProviderCategoryId());

				tmp.setStatusId("" + object.getStatusId().getStatusId());

				String breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Provider";
				request.setAttribute("breadcrumb", breadcrumb);


				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProviderForm();
				// foreign affairs

				Integer providerCategoryId = WebUtil.getParameterInteger(
						request, "providerCategoryId");

				if (providerCategoryId != null) {
					ProviderCategory forClass = new ProviderCategory();
					forClass.setProviderCategoryId(providerCategoryId);
					tmp.setProviderCategoryId("" + providerCategoryId);

					ProviderCategory providerCategory = this.providerCategoryService
							.get(providerCategoryId);
					tmp.getProvider().setProviderCategoryId(providerCategory);
				} else {
					ProviderCategory forClass = new ProviderCategory();
					// tmp.setProviderCategoryId("");
					tmp.getProvider().setProviderCategoryId(forClass);
				}

				Integer statusId = WebUtil.getParameterInteger(request,
						"statusId");

				if (statusId != null) {
					SubscriptionStatus forClass = new SubscriptionStatus();
					forClass.setStatusId(statusId);
					tmp.setStatusId("" + statusId);

					tmp.getProvider().setStatusId(forClass);
				} else {
					SubscriptionStatus forClass = new SubscriptionStatus();
					// tmp.setStatusId("");
					tmp.getProvider().setStatusId(forClass);
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProviderForm();
			// foreign affairs

			Integer providerCategoryId = WebUtil.getParameterInteger(request,
					"providerCategoryId");

			if (providerCategoryId != null) {
				ProviderCategory forClass = new ProviderCategory();
				forClass.setProviderCategoryId(providerCategoryId);
				tmp.setProviderCategoryId("" + providerCategoryId);

				ProviderCategory providerCategory = this.providerCategoryService
						.get(providerCategoryId);
				tmp.getProvider().setProviderCategoryId(providerCategory);
			} else {
				ProviderCategory forClass = new ProviderCategory();
				// tmp.setProviderCategoryId("");
				tmp.getProvider().setProviderCategoryId(forClass);
			}

			Integer statusId = WebUtil.getParameterInteger(request, "statusId");

			if (statusId != null) {
				SubscriptionStatus forClass = new SubscriptionStatus();
				forClass.setStatusId(statusId);
				tmp.setStatusId("" + statusId);

				tmp.getProvider().setStatusId(forClass);
			} else {
				SubscriptionStatus forClass = new SubscriptionStatus();
				// tmp.setStatusId("");
				tmp.getProvider().setStatusId(forClass);
			}

			String breadcrumb = "<a href=\"provider-form?navigation="+navigation+"&arah=&memberId="+memberId+"&index="+index+"&providerId=&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">Register Provider</a>";
			request.setAttribute("breadcrumb", breadcrumb); 


		}
		
		request.setAttribute("navigation", navigation);
		System.out.println("Navigation Form Backing = :"+navigation);
		request.setAttribute("memberId", memberId);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ProviderForm providerForm = (ProviderForm) command;
		Provider provider = providerForm.getProvider();
		String navigation = request.getParameter("navigation");
		Integer memberId = WebUtil.getParameterInteger(request, "memberId");
		Integer index = WebUtil.getParameterInteger(request, "index");
		String searchby = WebUtil.getParameterString(request, "searchby", "");

		// errors.setNestedPath("provider");
		// getValidator().validate(provider, errors);
		// errors.setNestedPath("");
		
		request.setAttribute("navigation", navigation);
		System.out.println("Navigation On Bind= :"+navigation);
		request.setAttribute("memberId", memberId);
		
		
		String breadcrumb = "<a href=\"provider-form?navigation="+navigation+"&arah=&memberId="+memberId+"&index="+index+"&providerId=&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">Register Provider</a>";
		request.setAttribute("breadcrumb", breadcrumb);

		errors.printStackTrace();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
		Collection<ProviderGroup> groupList = providerGroupService.getAll();
		
		model.put("providerGroupList", groupList);

		String navigation = WebUtil.getParameterString(request, "navigation", "");
		model.put("navigation", navigation);
		
		Collection category = providerCategoryService.getAll();
		model.put("providerCategory", category);
		
		Collection<Currency> currencyList = currencyService.getAll();
		model.put("currencyList", currencyList);
		
		Collection<Poliklinik> poliList = poliklinikService.getAll();
		model.put("poliList",poliList);
		
		Collection<ProviderType> providerTypeList = providerTypeService.getAll();
		model.put("providerTypeList",providerTypeList);
		

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ProviderForm providerForm = (ProviderForm) command;
		Integer memberId = WebUtil.getParameterInteger(request, "memberId");
		String navigation= WebUtil.getParameterString(request, "navigation", "");
		Integer index = WebUtil.getParameterInteger(request, "index");
		String searchby = WebUtil.getParameterString(request, "searchby", "");


		Provider res = null;
		String alertMsg = "";
		String providerId = "";
		
		try {
			// foreign affairs
			if (providerForm.getProviderCategoryId() != null) {
				providerForm.getProvider().setProviderCategoryId(
						this.providerCategoryService.get(new Integer(
								(providerForm.getProviderCategoryId()))));
			}

		
				
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}
			
			//Start Check Edc
			Provider checkEdcUnique = null;
			int totalEdc = 0;
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			

			if (providerForm.isNewProvider()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEPROVIDER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEPROVIDER access");
					return errorResult;

				}
				Provider provider = providerForm.getProvider();
				
				if (provider != null){
					provider.setStatusId(new SubscriptionStatus(SubscriptionStatus.PENDING));
				}
				if(providerForm.getProviderEdcCode() != null && !provider.getEdcCode().equals("")){
					try{
						//vLikeP.add("edcCode");
						//vLikeQ.add(provider.getEdcCode());
						vEqP.add("deletedStatus");
						vEqQ.add(Integer.valueOf(0));
						vEqP.add("edcCode");
						vEqQ.add(provider.getEdcCode());
						String sLikeP[] = new String[vLikeP.size()];
						vLikeP.toArray(sLikeP);
						Object sLikeQ[] = new Object[vLikeP.size()];
						vLikeQ.toArray(sLikeQ);

						String sEqP[] = new String[vEqP.size()];
						vEqP.toArray(sEqP);
						Object sEqQ[] = new Object[vEqP.size()];
						vEqQ.toArray(sEqQ);
						
						totalEdc = providerService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
						if(totalEdc==0)
							res = providerService.create(provider, user);
						else{
							Provider checkEdc = providerService.searchUnique(sEqP, sEqQ, 0, 1);
							request.setAttribute("alertEdcTerminal", 
									"EDC Code Already Used by \n"+checkEdc.getProviderName());
							request.setAttribute("alertEdc", alertProperties.getMessage(
									"fail.create.provideredc", null, "fail", Locale.getDefault()));
							return showForm(request, response, errors);
						}
					}catch (Exception ex) {
							request.setAttribute("alertEdc", alertProperties.getMessage(
									"fail.create.provideredc", null, "fail", Locale.getDefault()));
						ex.printStackTrace();
						return showForm(request, response, errors);
					}
				}else{
					res = providerService.create(provider, user);
				}
				
				
				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.provider", null, "success", Locale
									.getDefault());
					
					providerId = res.getProviderId().toString();
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.provider", null, "fail", Locale
									.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEPROVIDER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEPROVIDER access");
					return errorResult;

				}
				Provider checkEdc = null;
				if(providerForm.getProvider().getEdcCode() != null && providerForm.getProviderEdcCode()!=null 
						&& !providerForm.getProviderEdcCode().equals("") && !providerForm.getProvider().getEdcCode().equals("")
						&& providerForm.getProviderEdcCode() != null){
					try{
						checkEdc = providerService.get(Integer.valueOf(providerForm.getProviderId()));
						if(checkEdc.getEdcCode().equals(providerForm.getProviderEdcCode())){
							res = providerService.update(providerForm.getProvider(), user);
						}else{
							vEqP.add("deletedStatus");
							vEqQ.add(Integer.valueOf(0));
							vEqP.add("edcCode");
							vEqQ.add(providerForm.getProvider().getEdcCode());
							String sLikeP[] = new String[vLikeP.size()];
							vLikeP.toArray(sLikeP);
							Object sLikeQ[] = new Object[vLikeP.size()];
							vLikeQ.toArray(sLikeQ);

							String sEqP[] = new String[vEqP.size()];
							vEqP.toArray(sEqP);
							Object sEqQ[] = new Object[vEqP.size()];
							vEqQ.toArray(sEqQ);
							totalEdc = providerService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
							if(totalEdc == 0)
								res = providerService.update(providerForm.getProvider(), user);
							else{
								Provider check = providerService.searchUnique(sEqP, sEqQ, 0, 1);
								request.setAttribute("alertEdcTerminal", 
										"EDC Code Already Used \n"+check.getProviderName());
								request.setAttribute("alertEdc", alertProperties.getMessage(
										"fail.create.provideredc", null, "fail", Locale.getDefault()));
								return showForm(request, response, errors);
							}
						}
					}catch (Exception ex) {
							request.setAttribute("alertEdc", alertProperties.getMessage(
									"fail.create.provideredc", null, "fail", Locale.getDefault()));
						ex.printStackTrace();
						return showForm(request, response, errors);
					}
				}else{
					res = providerService.update(providerForm.getProvider(), user);
				}

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.provider", null, "success", Locale
									.getDefault());
					
					providerId = res.getProviderId().toString();
					
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.provider", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (providerForm.isNewProvider()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.provider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.provider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "<a href=\"provider-form?navigation="+navigation+"&arah=&memberId="+memberId+"&index="+index+"&providerId=&searchtext=&searchby="+searchby+"\" class=\"linkbreadcrumb\">Register Provider</a>";
		request.setAttribute("breadcrumb", breadcrumb);
			
		request.setAttribute("memberId", memberId);
		request.setAttribute("navigation", navigation);

		
		return new ModelAndView(new RedirectView("provider?navigation=detail&providerId="+ providerId + "&alert="
				+ alertMsg));
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

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}

	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}

	public ProviderTypeService getProviderTypeService() {
		return providerTypeService;
	}

	public void setProviderTypeService(ProviderTypeService providerTypeService) {
		this.providerTypeService = providerTypeService;
	}

	// class-

}
