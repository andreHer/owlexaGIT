
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
import com.ametis.cms.datamodel.ExportImportTemplate;
import com.ametis.cms.datamodel.ExternalRawData;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ExportImportTemplateService;
import com.ametis.cms.service.ExternalRawDataService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ExternalRawDataForm;



// imports+ 

// imports- 

/**
 * ExternalRawData is a mapping of external_raw_data Table.
*/
public class ExternalRawDataFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ExternalRawDataService externalRawDataService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ExportImportTemplateService exportImportTemplateService;
			private ActivityLogService logService;

			
			
			public ActivityLogService getLogService() {
				return logService;
			}

			public void setLogService(ActivityLogService logService) {
				this.logService = logService;
			}
	public void setExportImportTemplateService(ExportImportTemplateService obj){
		this.exportImportTemplateService = obj;
	}

	public ExportImportTemplateService getExportImportTemplateService(){
		return this.exportImportTemplateService;
	}
			
	// -- foreign affairs end


	public void setExternalRawDataService (ExternalRawDataService object){
	    this.externalRawDataService = object;
	}
	public ExternalRawDataService getExternalRawDataService (){
	    return this.externalRawDataService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getActionUserService() {
		return actionuserService;
	}
	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}
	
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}
	
	public void setSecurityService (SecurityService object){
	    this.securityService = object;
	}
	public SecurityService getSecurityService (){
	    return this.securityService;
	}

	
	public ExternalRawDataFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ExternalRawDataForm tmp = null;
						Integer id = WebUtil.getParameterInteger (request,"id");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								id != null
				) {
						java.io.Serializable pkey = id;
						ExternalRawData object = externalRawDataService.get (pkey );

			 if (object != null){ // edit object

				tmp = new ExternalRawDataForm(object);
			 // foreign affairs
	
				tmp.setTemplateId(""+
					object.getTemplateId().getId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ExternalRawDataForm();
					 // foreign affairs
	
	
				Integer templateId = WebUtil.getParameterInteger (request,"templateId");
		
			if(templateId!=null){
				ExportImportTemplate forClass = new ExportImportTemplate();
				forClass.setId(templateId);
				tmp.setTemplateId(""+templateId);

				ExportImportTemplate exportImportTemplate = this.exportImportTemplateService.get(templateId);
				tmp.getExternalRawData().setTemplateId(exportImportTemplate);
			}else{
				ExportImportTemplate forClass = new ExportImportTemplate();
//				tmp.setTemplateId("");
				tmp.getExternalRawData().setTemplateId(forClass);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ExternalRawDataForm();
					 // foreign affairs
		
	
				Integer templateId = WebUtil.getParameterInteger (request,"templateId");
		
			if(templateId!=null){
				ExportImportTemplate forClass = new ExportImportTemplate();
				forClass.setId(templateId);
				tmp.setTemplateId(""+templateId);

				ExportImportTemplate exportImportTemplate = this.exportImportTemplateService.get(templateId);
				tmp.getExternalRawData().setTemplateId(exportImportTemplate);
			}else{
				ExportImportTemplate forClass = new ExportImportTemplate();
//				tmp.setTemplateId("");
				tmp.getExternalRawData().setTemplateId(forClass);
			}


		// -- foreign affairs end



		}
										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ExternalRawDataForm externalRawDataForm = ( ExternalRawDataForm ) command;
		ExternalRawData externalRawData = externalRawDataForm.getExternalRawData();
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ExternalRawDataForm externalRawDataForm = ( ExternalRawDataForm ) command;

		ExternalRawData res = null;
		String alertMsg="";
		try {
	
			
			ActionUser user = securityService.getActionUser(request);
			
			if (externalRawDataForm.isNewExternalRawData ()) {
				res = externalRawDataService.create (externalRawDataForm.getExternalRawData(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.externalrawdata",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.externalrawdata",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = externalRawDataService.update (externalRawDataForm.getExternalRawData(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.externalrawdata",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.externalrawdata",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (externalRawDataForm.isNewExternalRawData ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.externalrawdata",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.externalrawdata",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("externalrawdata"+"?navigation=list&id="+externalRawDataForm.getExternalRawData().getImportSessionId().getId()+"&alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
	}
// class+ 

// class- 
}
