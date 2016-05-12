
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
import com.ametis.cms.datamodel.Clausul;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.Procedure;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ClausulService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProcedureService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyClausulForm;



// imports+ 

// imports- 

/**
 * PolicyClausul is a mapping of policy_clausul Table.
*/
public class PolicyClausulFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	PolicyClausulService policyClausulService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	private CaseCategoryService caseCategoryService;
	private DiagnosisService diagnosisService;
	private ProcedureService procedureService;
	private ItemCategoryService itemCategoryService;
	private PolicyService policyService;
	private ClausulService clausulService;
	// foreign affairs
	
		
	// -- foreign affairs end

private ActivityLogService logService;

	
	
	public ClausulService getClausulService() {
	return clausulService;
}

public void setClausulService(ClausulService clausulService) {
	this.clausulService = clausulService;
}

	public PolicyService getPolicyService() {
	return policyService;
}

public void setPolicyService(PolicyService policyService) {
	this.policyService = policyService;
}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public void setPolicyClausulService (PolicyClausulService object){
	    this.policyClausulService = object;
	}
	public PolicyClausulService getPolicyClausulService (){
	    return this.policyClausulService;
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

	
	
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public ProcedureService getProcedureService() {
		return procedureService;
	}

	public void setProcedureService(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}

	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public PolicyClausulFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyClausulForm tmp = null;
		Integer id = WebUtil.getParameterInteger (request,"id");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (id != null) {
				java.io.Serializable pkey = id;
				PolicyClausul object = policyClausulService.get (pkey );
			 if (object != null){ // edit object
				tmp = new PolicyClausulForm(object);
				
				if(object.getDiagnosisId()!=null){
					Diagnosis diag = diagnosisService.get(object.getDiagnosisId().getDiagnosisId());
					if (diag != null){
						tmp.setDiagnosisId(diag.getDiagnosisId().toString());
						tmp.setDiagnosisName(diag.getDescription());
					}
				}
				if(object.getClausulId()!=null){
					Clausul clau = clausulService.get(object.getClausulId().getClausulId());
					if(clau!=null){
						//tmp.setClausulId(clau.getClausulId();
						tmp.setClausulName(clau.getClausulName());
					}
				}
				if(object.getProcedureId()!=null){
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
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyClausulForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyClausulForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																		
		if (policyId != null){
			Policy policy = policyService.get(policyId);
			if (policy != null){
				tmp.setPolicyNumber(policy.getPolicyNumber());
				tmp.setPolicyId(policy.getPolicyId().toString());
			}
		}
		
		String breadcrumb = "";
		breadcrumb = "<a href=\"policyclausul?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Clausul</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Clausul";

		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {

		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		PolicyClausulForm policyClausulForm = ( PolicyClausulForm ) command;
		PolicyClausul policyClausul = policyClausulForm.getPolicyClausul();
                
                errors.printStackTrace();
                
		String breadcrumb = "";
		breadcrumb = "<a href=\"policyclausul?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Clausul</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Clausul";

		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);

//		errors.setNestedPath("policyClausul");
//		getValidator().validate(policyClausul, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		try {
			Collection<CaseCategory> caseCategoryList = caseCategoryService.getAll();
			
			model.put("caseCategories", caseCategoryList);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PolicyClausulForm policyClausulForm = ( PolicyClausulForm ) command;
		
//		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		PolicyClausul res = null;
		String alertMsg="";
		String policyId = "";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			
			ActionUser user = securityService.getActionUser(request);
			
			String clausulName = policyClausulForm.getClausulName();
			String clausulId = policyClausulForm.getClausulId();
			
			if(policyClausulForm.getCaseCategoryId().equals("-1")){
				request.setAttribute ("alertCase", alertProperties.getMessage ("fail.create.policyclausul",null,"Case Category is required",Locale.getDefault()));
				return showForm(request, response, errors);
			}
			
			if (((clausulId == null || clausulId.equalsIgnoreCase("") )&& clausulName != null && !clausulName.equalsIgnoreCase(""))){
				String[] eqParam = {"clausulName"};
				Object[] eqValue = {clausulName};
				
				int total = clausulService.getTotal(null,null,eqParam,eqValue);
				
				if (total == 0){
					Clausul clausul = new Clausul();
					clausul.setClausulName(clausulName);
					clausul.setDeletedStatus(0);
					
					Clausul clause = clausulService.create(clausul, user);
					
					if (clause != null){
						policyClausulForm.getPolicyClausul().setClausulId(clause);
					}
				}
			}
			if (policyClausulForm.isNewPolicyClausul ()) {
				try{
					res = policyClausulService.create (policyClausulForm.getPolicyClausul(),user);
				}catch (Exception ex) {
					ex.printStackTrace();
					request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policyclausul",null,"fail",Locale.getDefault()));
					return showForm(request, response, errors);
				}

				if (res!=null){
					policyId = res.getPolicyId().getPolicyId().toString();
					alertMsg = alertProperties.getMessage ("success.create.policyclausul",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policyclausul",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyClausulService.update (policyClausulForm.getPolicyClausul(),user);

				if (res!=null){
					policyId = res.getPolicyId().getPolicyId().toString();
					alertMsg = alertProperties.getMessage ("success.update.policyclausul",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policyclausul",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
                    ex.printStackTrace();
			if (policyClausulForm.isNewPolicyClausul ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policyclausul",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policyclausul",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "";
		breadcrumb = "<a href=\"policyclausul?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Clausul</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Clausul";

		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		
		return new ModelAndView(new RedirectView("policyclausul"+"?policyId="+policyId+"&alert="+alertMsg));
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
