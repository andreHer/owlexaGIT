
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ExportImportTemplate;
import com.ametis.cms.datamodel.PolicyMemberMovement;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ExportImportTemplateService;
import com.ametis.cms.service.PolicyMemberMovementService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyMemberMovementForm;



// imports+ 

// imports- 


/**
 * PolicyMemberMovement is a mapping of policy_member_movement Table.
*/
public class PolicyMemberMovementFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	PolicyMemberMovementService policyMemberMovementService ;
	private ExportImportTemplateService exportImportTemplateService;
	
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
		
	// -- foreign affairs end
private ActivityLogService logService;

	
	
	public ExportImportTemplateService getExportImportTemplateService() {
	return exportImportTemplateService;
}

public void setExportImportTemplateService(
		ExportImportTemplateService exportImportTemplateService) {
	this.exportImportTemplateService = exportImportTemplateService;
}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public void setPolicyMemberMovementService (PolicyMemberMovementService object){
	    this.policyMemberMovementService = object;
	}
	public PolicyMemberMovementService getPolicyMemberMovementService (){
	    return this.policyMemberMovementService;
	}
		// generate by default
		private UserService  userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
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

	
	public PolicyMemberMovementFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyMemberMovementForm tmp = null;
		Integer id = WebUtil.getParameterInteger (request,"id");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb = "";
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (id != null) {
						java.io.Serializable pkey = id;
						PolicyMemberMovement object = policyMemberMovementService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PolicyMemberMovementForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyMemberMovementForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyMemberMovementForm();
					 // foreign affairs
			// -- foreign affairs end



		}
		if (policyId != null){
			tmp.setPolicyId(policyId.toString());
		}
		
        if (navigation.equalsIgnoreCase("listpolicy")) {
            breadcrumb = "<a href=\"policymembermovement?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Member Movement</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Policy Member Movement";
        }

        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
        request.setAttribute("policyId", policyId);
																																		
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {

		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb = "";

		PolicyMemberMovementForm policyMemberMovementForm = ( PolicyMemberMovementForm ) command;
		PolicyMemberMovement policyMemberMovement = policyMemberMovementForm.getPolicyMemberMovement();
		
        if (navigation.equalsIgnoreCase("listpolicy")) {
            breadcrumb = "<a href=\"policymembermovement?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Member Movement</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Policy Member Movement";
        }

        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
        request.setAttribute("policyId", policyId);

//		errors.setNestedPath("policyMemberMovement");
//		getValidator().validate(policyMemberMovement, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
			ini dipake buat populate data - data yang dibutuhkan
			contoh : Problem membutuhkan ProblemCategory
			nah fungsi method ini yaitu untuk populate list problem category ke jsp
			nanti biar bisa ditangkep sama jspnya

			contoh : Collection pc = pcontroller.searchPC();

			model.addObject("pcbeans", pc);

		*/

		try {
			String[] eqParam = {"tipe","deletedStatus"};
			Object[] eqValue = {Integer.valueOf(ExportImportTemplate.UPLOAD_MEMBER),Integer.valueOf(0)};
		
			int total = exportImportTemplateService.getTotal(null,null,eqParam,eqValue);
			
			Collection<ExportImportTemplate> col = exportImportTemplateService.search(null,null,eqParam,eqValue,0,total);
			
			model.put("templates", col);
		}
		catch (Exception e){
			e.printStackTrace();
		}
        

        

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
//		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb = "";

		PolicyMemberMovementForm policyMemberMovementForm = ( PolicyMemberMovementForm ) command;

		PolicyMemberMovement res = null;
		String alertMsg="";
		String policyId = "";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
		   byte[] theData = null;
		   
	            // String fileName = "";

	            policyId = policyMemberMovementForm.getPolicyMemberMovement().getPolicyId().getPolicyId().toString();
	            
	            MultipartFile multipartFile = policyMemberMovementForm.getMultipartFile();
	            System.out.println("Original File Name : " + multipartFile.getOriginalFilename());
	            System.out.println("File Name : " + multipartFile.getName());
	            policyMemberMovementForm.getPolicyMemberMovement().setFormatedMovementFile(multipartFile.getOriginalFilename());
	            

	            theData = multipartFile.getBytes();


			
			ActionUser user = securityService.getActionUser(request);
			
			if (policyMemberMovementForm.isNewPolicyMemberMovement ()) {
				
				res = policyMemberMovementService.create (policyMemberMovementForm.getPolicyMemberMovement(),theData,user);

				if (res!=null){
					policyId = res.getId().toString();
					alertMsg = alertProperties.getMessage ("success.create.policymembermovement",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policymembermovement",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyMemberMovementService.update (policyMemberMovementForm.getPolicyMemberMovement(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policymembermovement",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policymembermovement",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			ex.printStackTrace();
			if (policyMemberMovementForm.isNewPolicyMemberMovement ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policymembermovement",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policymembermovement",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
        if (navigation.equalsIgnoreCase("listpolicy")) {
            breadcrumb = "<a href=\"policymembermovement?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Member Movement</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Policy Member Movement";
        }

        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
        request.setAttribute("policyId", policyId);		
		
		return new ModelAndView(new RedirectView("policymembermovement"+"?navigation=detail&id=" +policyId + "&alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
// class+ 

// class- 

}
