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
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.EdcTerminalForm;

// imports+

// imports-

/**
 * EdcTerminal is a mapping of edc_terminal Table.
 */
public class EdcTerminalFormController extends SimpleFormController
// extends+

// extends-

{

	EdcTerminalService edcTerminalService;
	private com.ametis.cms.service.ProviderService providerService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;

	// foreign affairs

	// -- foreign affairs end

	public void setEdcTerminalService(EdcTerminalService object) {
		this.edcTerminalService = object;
	}

	public EdcTerminalService getEdcTerminalService() {
		return this.edcTerminalService;
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

	public EdcTerminalFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		EdcTerminalForm tmp = null;
		Integer id = WebUtil.getParameterInteger(request, "id");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");


		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (id != null) {
			java.io.Serializable pkey = id;
			EdcTerminal object = edcTerminalService.get(pkey);

			if (object != null) { // edit object

				Provider provider = providerService.get(object.getProviderId().getProviderId());
				tmp = new EdcTerminalForm(object);
				// foreign affairs
				// -- foreign affairs end
				tmp.setProviderName(provider.getProviderName());
				
				String breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update EDC Terminal";
				request.setAttribute("breadcrumb", breadcrumb);
				
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new EdcTerminalForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new EdcTerminalForm();
			// foreign affairs
			// -- foreign affairs end
			if (providerId != null){
				Provider provider = providerService.get(providerId);
				if (provider != null){
					tmp.setProviderId(provider.getProviderId().toString());
					tmp.setProviderName(provider.getProviderName());
				}
				String breadcrumb = "<a href=\"edcterminal?navigation=listprovider&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider EDC Terminal</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add EDC Terminal";
				request.setAttribute("breadcrumb", breadcrumb);

			}
		}

		
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		EdcTerminalForm edcTerminalForm = (EdcTerminalForm) command;
		EdcTerminal edcTerminal = edcTerminalForm.getEdcTerminal();

		// errors.setNestedPath("edcTerminal");
		// getValidator().validate(edcTerminal, errors);
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

		EdcTerminalForm edcTerminalForm = (EdcTerminalForm) command;

		EdcTerminal res = null;
		String alertMsg = "";
		String providerId = "";
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}


			ActionUser user = securityService.getActionUser(request);
			
			
			Provider check=null;
			if(edcTerminalForm.getProviderId() != null){
				check = providerService.get(Integer.valueOf(edcTerminalForm.getProviderId()));
			}
			
			if (edcTerminalForm.isNewEdcTerminal()) {
				//Start Check Unique EDC Terminal
				int totalEdc = 0;
				Vector vEqP = new Vector();
				Vector vEqQ = new Vector();
				vEqP.add("deviceNumber");
		    	vEqQ.add(edcTerminalForm.getDeviceNumber());
		    	vEqP.add("deletedStatus");
				vEqQ.add(new Integer(0));
				String sEqP[] = new String[vEqP.size()];
			    vEqP.toArray(sEqP);
			    Object sEqQ[] = new Object[vEqP.size()];
			    vEqQ.toArray(sEqQ);
				if(edcTerminalForm.getDeviceNumber()!=null || 
						!edcTerminalForm.getEdcTerminal().getDeviceNumber().equals("")){
					try{
						totalEdc = edcTerminalService.getTotalEdcTerminal(edcTerminalForm.getEdcTerminal().getDeviceNumber());
						if(totalEdc <= 0){
							res = edcTerminalService.create(edcTerminalForm.getEdcTerminal(), user);
						}else{
							EdcTerminal checkEdc = edcTerminalService.searchUnique(sEqP, sEqQ, 0, 1);
							request.setAttribute("alertEdcTerminal", 
									"Terminal Device Number Already Used by \n"+
									checkEdc.getProviderId().getProviderName());
							request.setAttribute("alertEdc", alertProperties.getMessage(
									"fail.create.edcterminal", null, "Terminal Device Number Already Used", Locale.getDefault()));
							return showForm(request, response, errors);
						}
					}catch (Exception ex) {
						ex.printStackTrace();
						EdcTerminal checkEdc = edcTerminalService.searchUnique(sEqP, sEqQ, 0, 1);
						request.setAttribute("alertEdcTerminal", 
								"Terminal Device Number Already Used by \n"+
								checkEdc.getProviderId().getProviderName());
						request.setAttribute("alertEdc", alertProperties.getMessage(
								"fail.create.edcterminal", null, "Terminal Device Number Already Used", Locale.getDefault()));
						return showForm(request, response, errors);
					}
				}else{
					res = edcTerminalService.create(edcTerminalForm
							.getEdcTerminal(), user);
				}
				
				providerId = edcTerminalForm.getProviderId();

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.edcterminal", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.edcterminal", null, "fail", Locale
									.getDefault());
				}
			} else {
				if(edcTerminalForm.getEdcTerminal().getDeviceNumber()!=null || 
						!edcTerminalForm.getEdcTerminal().getDeviceNumber().equals("")){
					Vector vEqP = new Vector();
					Vector vEqQ = new Vector();
					vEqP.add("deviceNumber");
			    	vEqQ.add(edcTerminalForm.getDeviceNumber());
			    	vEqP.add("deletedStatus");
					vEqQ.add(new Integer(0));
					String sEqP[] = new String[vEqP.size()];
				    vEqP.toArray(sEqP);
				    Object sEqQ[] = new Object[vEqP.size()];
				    vEqQ.toArray(sEqQ);
					try{
						providerId = edcTerminalForm.getProviderId();
						EdcTerminal checkEdc = edcTerminalService.get(edcTerminalForm.getEdcTerminal().getId());
						int totalEdc = 0;
						if(checkEdc.getDeviceNumber().equals(edcTerminalForm.getEdcTerminal().getDeviceNumber())){
							res = edcTerminalService.update(edcTerminalForm
									.getEdcTerminal(), user);
						}else{
							totalEdc = edcTerminalService.getTotalEdcTerminal(edcTerminalForm.getEdcTerminal().getDeviceNumber());
							if(totalEdc <= 0){
								res = edcTerminalService.update(edcTerminalForm
										.getEdcTerminal(), user);
							}else{
								EdcTerminal checkEdcTmp = edcTerminalService.searchUnique(sEqP, sEqQ, 0, 1);
								request.setAttribute("alertEdcTerminal", 
										"Terminal Device Number Already Used by \n"+
										checkEdcTmp.getProviderId().getProviderName());
								request.setAttribute("alertEdc", alertProperties.getMessage(
										"fail.create.edcterminal", null, "Terminal Device Number Already Used", Locale.getDefault()));
								return showForm(request, response, errors);
							}
						}
					}catch (Exception ex) {
						ex.printStackTrace();
						EdcTerminal checkEdcTmp = edcTerminalService.searchUnique(sEqP, sEqQ, 0, 1);
						request.setAttribute("alertEdcTerminal", 
								"Terminal Device Number Already Used by \n"+
								checkEdcTmp.getProviderId().getProviderName());
						request.setAttribute("alertEdc", alertProperties.getMessage(
									"fail.create.edcterminal", null, "Terminal Device Number Already Used", Locale.getDefault()));
						return showForm(request, response, errors);
					}
				}else{
					res = edcTerminalService.update(edcTerminalForm
							.getEdcTerminal(), user);
				}

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.edcterminal", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.edcterminal", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (edcTerminalForm.isNewEdcTerminal()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.edcterminal", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.edcterminal", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("edcterminal" + "?providerId="+providerId+"&alert="
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
	public com.ametis.cms.service.ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(
			com.ametis.cms.service.ProviderService providerService) {
		this.providerService = providerService;
	}

	// class-

}
