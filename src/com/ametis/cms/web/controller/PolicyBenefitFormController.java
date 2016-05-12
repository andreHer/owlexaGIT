
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
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.DiagnosisSet;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyBenefit;
import com.ametis.cms.datamodel.Procedure;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.DiagnosisSetService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.PolicyBenefitService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProcedureService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyBenefitForm;



// imports+ 

// imports- 


/**
 * PolicyBenefit is a mapping of policy_benefit Table.
*/
public class PolicyBenefitFormController extends SimpleFormController
// extends+ 

// extends- 

{
	
	
	
	PolicyBenefitService policyBenefitService ;
	private PolicyService policyService;
	private CaseCategoryService caseCategoryService;
	
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
		
	// -- foreign affairs end

	private ActivityLogService logService;
	private ProcedureService procedureService;
	private DiagnosisService diagnosisService;
	private DiagnosisSetService diagnosisSetService;
	private ItemCategoryService itemCategoryService;
	
	

	
	
	public ProcedureService getProcedureService() {
		return procedureService;
	}

	public void setProcedureService(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public DiagnosisSetService getDiagnosisSetService() {
		return diagnosisSetService;
	}

	public void setDiagnosisSetService(DiagnosisSetService diagnosisSetService) {
		this.diagnosisSetService = diagnosisSetService;
	}

	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public void setPolicyBenefitService (PolicyBenefitService object){
	    this.policyBenefitService = object;
	}
	public PolicyBenefitService getPolicyBenefitService (){
	    return this.policyBenefitService;
	}
		// generate by default
		private UserService  userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
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

	
	
	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PolicyBenefitFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyBenefitForm tmp = null;
		Integer policyBenefitId = WebUtil.getParameterInteger (request,"policyBenefitId");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
				
		
		if (policyBenefitId != null) {
			java.io.Serializable pkey = policyBenefitId;
			PolicyBenefit object = policyBenefitService.get (pkey );

			if (object != null){ // edit object
				tmp = new PolicyBenefitForm(object);
				
				if (object.getDiagnosisId() != null){
					Diagnosis diag = diagnosisService.get(object.getDiagnosisId().getDiagnosisId());
					if (diag != null){
						tmp.setDiagnosisId(diag.getDiagnosisId().toString());
						tmp.setDiagnosisName(diag.getDiagnosisName());
					}
				}
				if (object.getDiagnosisSetId() != null){
					DiagnosisSet diag = diagnosisSetService.get(object.getDiagnosisSetId().getDiagnosisSetId());
					if (diag != null){
						tmp.setDiagnosisSetId(diag.getDiagnosisSetId().toString());
						tmp.setDiagnosisSetName(diag.getDiagnosisSetName());
					}
				}
				if (object.getProcedureId() != null){
					Procedure proc = procedureService.get(object.getProcedureId().getProcedureId());
					if (proc != null){
						tmp.setProcedureId(proc.getProcedureId().toString());
						tmp.setProcedureName(proc.getProcedureName());
					}
				}
				if (object.getItemCategoryId() != null){
					ItemCategory item = itemCategoryService.get(object.getItemCategoryId().getItemCategoryId());
					if (item != null){
						tmp.setItemCategoryId(item.getItemCategoryId().toString());
						tmp.setItemCategoryName(item.getItemCategoryName());
					}
				}
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyBenefitForm();
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyBenefitForm();
			
			
		}
		
		if (policyId != null){
			Policy policy = policyService.get(policyId);
			
			if (policy != null){
				tmp.setPolicyNumber(policy.getPolicyNumber());
				tmp.setGroupName(policy.getMemberGroupId().getGroupName());
				tmp.setPolicyId(policy.getPolicyId().toString());
			}
		}
		String breadcrumb = "";
		breadcrumb = "<a href=\"policybenefit?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Benefit</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Benefit";
			
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);

		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {

		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		PolicyBenefitForm policyBenefitForm = ( PolicyBenefitForm ) command;
		PolicyBenefit policyBenefit = policyBenefitForm.getPolicyBenefit();
		errors.printStackTrace();
		
		String breadcrumb = "";
		breadcrumb = "<a href=\"policybenefit?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Benefit</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Benefit";
			
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);

//		errors.setNestedPath("policyBenefit");
//		getValidator().validate(policyBenefit, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
		
		try {
			Collection<CaseCategory> ccList = caseCategoryService.getAll();
			model.put("ccList", ccList);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PolicyBenefitForm policyBenefitForm = ( PolicyBenefitForm ) command;

		PolicyBenefit res = null;
		String alertMsg="";
		String policyId = policyBenefitForm.getPolicyId();
//		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		try {
			
			
			
		
			ActionUser user = securityService.getActionUser(request);
			
			if (policyBenefitForm.isNewPolicyBenefit ()) {
				res = policyBenefitService.create (policyBenefitForm.getPolicyBenefit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.policybenefit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policybenefit",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyBenefitService.update (policyBenefitForm.getPolicyBenefit(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policybenefit",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policybenefit",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (policyBenefitForm.isNewPolicyBenefit ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policybenefit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policybenefit",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "";
		breadcrumb = "<a href=\"policybenefit?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Benefit</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Benefit";
			
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("policyId", policyId);
		
		return new ModelAndView(new RedirectView("policybenefit"+"?navigation=list&policyId="+policyId+"&alert="+alertMsg));
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
