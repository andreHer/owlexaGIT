package com.ametis.cms.web.controller;

import java.text.DateFormat;
import java.text.NumberFormat;
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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ClientCategory;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.FloatingFundUsageType;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientCategoryService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ClientForm;

// imports+ 

// imports- 

/**
 * Client is a mapping of client Table.
 */
public class ClientFormController extends SimpleFormController
// extends+

// extends-

{

	ClientService clientService;
	ResourceBundleMessageSource alertProperties;
	private CurrencyService currencyService;
	// foreign affairs
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	ClientCategoryService clientCategoryService;
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setClientCategoryService(ClientCategoryService obj) {
		this.clientCategoryService = obj;
	}

	public ClientCategoryService getClientCategoryService() {
		return this.clientCategoryService;
	}

	// -- foreign affairs end

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public void setClientService(ClientService object) {
		this.clientService = object;
	}

	public ClientService getClientService() {
		return this.clientService;
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

	public ClientFormController() {
		setSessionForm(true);

		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ClientForm tmp = null;
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (clientId != null) {
			java.io.Serializable pkey = clientId;
			Client object = clientService.get(pkey);

			if (object != null) { // edit object

				tmp = new ClientForm(object);
				// foreign affairs

				if (object.getClientCategoryId() != null){
					tmp.setClientCategoryId(""
						+ object.getClientCategoryId().getClientCategoryId());
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClientForm();
				// foreign affairs
				Integer clientCategoryId = WebUtil.getParameterInteger(request,
						"clientCategoryId");

				if (clientCategoryId != null) {
					ClientCategory forClass = new ClientCategory();
					forClass.setClientCategoryId(clientCategoryId);
					tmp.setClientCategoryId("" + clientCategoryId);

					ClientCategory clientCategory = this.clientCategoryService
							.get(clientCategoryId);
					tmp.getClient().setClientCategoryId(clientCategory);

				} else {

				}
				
				// -- foreign affairs end
				
			}
			String breadcrumb = "<a href=\"client-form?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">Update Client</a>";
			request.setAttribute("breadcrumb", breadcrumb);
			request.setAttribute("clientId", clientId);
			request.setAttribute("navigation", navigation);

		} // mau edit end
		else { // bikin baru
			tmp = new ClientForm();
			// foreign affairs

			Integer clientCategoryId = WebUtil.getParameterInteger(request,
					"clientCategoryId");

			if (clientCategoryId != null) {
				ClientCategory forClass = new ClientCategory();
				forClass.setClientCategoryId(clientCategoryId);
				tmp.setClientCategoryId("" + clientCategoryId);

				ClientCategory clientCategory = this.clientCategoryService
						.get(clientCategoryId);
				tmp.getClient().setClientCategoryId(clientCategory);
			} else {
			
			}
			

			// -- foreign affairs end

			String breadcrumb = "<a href=\"client-form\" class=\"linkbreadcrumb\">Register Client</a>";
			request.setAttribute("breadcrumb", breadcrumb);

		}

		result = tmp;

		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ClientForm clientForm = (ClientForm) command;
		Client client = clientForm.getClient();

		// errors.setNestedPath("client");
		// getValidator().validate(client, errors);
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

		try {
			
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			model.put("navigation", navigation);
			
			
			Collection category = clientCategoryService.getAll();
			model.put("clientCategory", category);

			Collection currencies = currencyService.getAll();
			model.put("currencies", currencies);

			Collection<FloatingFundUsageType> fundUsage = new Vector<FloatingFundUsageType>();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClientForm clientForm = (ClientForm) command;

		Client res = null;
		String alertMsg = "";
		String clientId = "";
		try {
			// foreign affairs
			if (clientForm.getClientCategoryId() != null) {
				clientForm.getClient().setClientCategoryId(
						this.clientCategoryService.get(new Integer((clientForm
								.getClientCategoryId()))));
			}

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			vEqP.add("clientCategoryId.clientCategoryId");
			vEqQ.add(Integer.valueOf(clientForm.getClientCategoryId()));
			vEqP.add("clientNumber");
			vEqQ.add(clientForm.getClientNumber());
			vEqP.add("clientCode");
			vEqQ.add(clientForm.getClientCode());
			vEqP.add("registrationDate");

			DateFormat formatter ; 
			Date dateRegis;
			Date dateExpire;
			formatter = new SimpleDateFormat("yyyy-mm-dd");
		   	dateRegis = formatter.parse(clientForm.getRegistrationDate());
		   	dateExpire = formatter.parse(clientForm.getExpireDate());

			vEqQ.add(dateRegis);
			vEqP.add("expireDate");
			vEqQ.add(dateExpire);
			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			Client checkClient = clientService.searchUnique(sEqP, sEqQ, 0, 1);
			
			if(checkClient==null || !clientForm.isNewClient()){
				if (clientForm.isNewClient()) {
	
					ActionUser user = securityService.getActionUser(request);
	
					boolean isUserAuthorized = securityService.isUserAuthorized(
							user, "CREATECLIENT");
	
					if (!isUserAuthorized) {
	
						ModelAndView errorResult = new ModelAndView(
								new RedirectView("errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult.addObject("errorMessage",
								"You Are Not Authorized for CREATECLIENT access");
						return errorResult;
	
					}
					res = clientService.create(clientForm.getClient(), user);
	
					if (res != null) {
						clientId = res.getClientId().toString();
						alertMsg = alertProperties.getMessage(
								"success.create.client", null, "success", Locale
										.getDefault());
					} else {
						alertMsg = alertProperties.getMessage("fail.create.client",
								null, "fail", Locale.getDefault());
					}
				} else {
					ActionUser user = securityService.getActionUser(request);
	
					boolean isUserAuthorized = securityService.isUserAuthorized(
							user, "UPDATECLIENT");
	
					if (!isUserAuthorized) {
	
						ModelAndView errorResult = new ModelAndView(
								new RedirectView("errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult.addObject("errorMessage",
								"You Are Not Authorized for UPDATECLIENT access");
						return errorResult;
	
					}
					if(checkClient==null){
						res = clientService.update(clientForm.getClient(), user);
					}else if(checkClient.getClientId().toString().equals(clientForm.getClientId())){
						res = clientService.update(clientForm.getClient(), user);
					}else{
						request.setAttribute("alertExist", alertProperties.getMessage("fail.create.exist",
								null, "fail", Locale.getDefault())+checkClient.getClientName()+"-"+checkClient.getClientCode());
						return showForm(request, response, errors);
					}
					if (res != null) {
						clientId = res.getClientId().toString();
						alertMsg = alertProperties.getMessage(
								"success.update.client", null, "success", Locale
										.getDefault());
					} else {
						alertMsg = alertProperties.getMessage("fail.update.client",
								null, "fail", Locale.getDefault());
					}
				}
			}else{
				request.setAttribute("alertExist", alertProperties.getMessage("fail.create.exist",
								null, "fail", Locale.getDefault())+checkClient.getClientName()+"-"+checkClient.getClientCode());
				return showForm(request, response, errors);
			}
		} catch (Exception ex) {
			if (clientForm.isNewClient()) {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.create.client", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.update.client", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("client?navigation=detail&clientId="+clientId + "&alert="
				+ alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, num);

		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		binder.registerCustomEditor(java.lang.Double.class,
				new CustomNumberEditor(java.lang.Double.class, nf, true));
	}
	// class+

	// class-

}
