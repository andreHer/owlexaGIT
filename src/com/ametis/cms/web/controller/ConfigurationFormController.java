package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ExportImportTemplate;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ExportImportTemplateService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ConfigurationForm;

// imports+ 

// imports- 

/**
 * Configuration is a mapping of configuration Table.
 */
public class ConfigurationFormController extends SimpleFormController
// extends+

// extends-

{

	ConfigurationService configurationService;
	private ExportImportTemplateService exportImportTemplateService;
	private ClientService clientService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	// foreign affairs

	// -- foreign affairs end
	private ActivityLogService logService;

	
	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public void setConfigurationService(ConfigurationService object) {
		this.configurationService = object;
	}

	public ConfigurationService getConfigurationService() {
		return this.configurationService;
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
	
	public ExportImportTemplateService getExportImportTemplateService() {
		return exportImportTemplateService;
	}

	public void setExportImportTemplateService(
			ExportImportTemplateService exportImportTemplateService) {
		this.exportImportTemplateService = exportImportTemplateService;
	}

	public ConfigurationFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ConfigurationForm tmp = null;
		String breadcrumb = "";
		Integer configurationId = WebUtil.getParameterInteger(request,
				"configurationId");
		String navigation = WebUtil.getParameterString(request,
				"navigation","");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		if (configurationId != null) {
			java.io.Serializable pkey = configurationId;
			Configuration object = configurationService.get(pkey);

			if (object != null) { // edit object

				tmp = new ConfigurationForm(object);
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ConfigurationForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else if(navigation.equalsIgnoreCase("update-provider-configuration")){ //Add 20150820 by FVO, for handle provider configuration
			Configuration objConfiguration = configurationService.get(0);
			if(objConfiguration != null){
				tmp = new ConfigurationForm(objConfiguration);
				if (navigation.equalsIgnoreCase("update-provider-configuration")) {
		        	breadcrumb = "<a href=\"provider\" class=\"linkbreadcrumb\">Search Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Provider Configuration";
		        	
		        	request.setAttribute("breadcrumb", breadcrumb);
					request.setAttribute("navigation", navigation);
		        }
			}
		}
		else { // bikin baru
			tmp = new ConfigurationForm();
			// foreign affairs
			// -- foreign affairs end
			
		}
		if (clientId != null){
			Client client = clientService.get(clientId);
			if (client != null){				
				Configuration config = configurationService.getClientConfiguration(clientId);
				if (config != null){
					tmp = new ConfigurationForm(config);
					tmp.setClientId(client.getClientId().toString());	
				}
				
		        if (navigation.equalsIgnoreCase("update-configuration")) {
		        	breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Configuration";
		        }
		        if (navigation.equalsIgnoreCase("update-risk-configuration")) {
		        	breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Risk Configuration";
		        }
		        if (navigation.equalsIgnoreCase("update-number-configuration")) {
		        	breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Number Configuration";
		        }
				request.setAttribute("breadcrumb", breadcrumb);
				request.setAttribute("navigation", navigation);
				
			}
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ConfigurationForm configurationForm = (ConfigurationForm) command;
		Configuration configuration = configurationForm.getConfiguration();

		// errors.setNestedPath("configuration");
		// getValidator().validate(configuration, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
	
		try {
			Collection<ExportImportTemplate> templateList = exportImportTemplateService.getAll();
			
			model.put("templateList", templateList);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ConfigurationForm configurationForm = (ConfigurationForm) command;

		Configuration res = null;
		String alertMsg = "";
		String clientId = "";
		
		String navigation = WebUtil.getParameterString(request, "navigation","");
		
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}
			ActionUser user = securityService.getActionUser(request);

			if (configurationForm.isNewConfiguration()) {
				res = configurationService.create(configurationForm
						.getConfiguration(), user);

				if (res != null) {
					clientId = res.getClientId().getClientId().toString();
					alertMsg = alertProperties.getMessage(
							"success.create.configuration", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.configuration", null, "fail", Locale
									.getDefault());
				}
			} else {
				res = configurationService.update(configurationForm
						.getConfiguration(), user);

				if (res != null) {
					if (res.getClientId() != null){
						clientId = res.getClientId().getClientId().toString();
					}
					else {
						clientId = "0";
					}
					
					alertMsg = alertProperties.getMessage(
							"success.update.configuration", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.configuration", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (configurationForm.isNewConfiguration()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.configuration", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.configuration", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		System.out.println("NAVIGATION : "+navigation);
		if(navigation.equalsIgnoreCase("update-provider-configuration")){
			return new ModelAndView(new RedirectView("provider?alert="
					+ alertMsg));
		}else{
			return new ModelAndView(new RedirectView("client?navigation=detail&clientId="+clientId + "&alert="
					+ alertMsg));
		}
		
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
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	// class-

}
