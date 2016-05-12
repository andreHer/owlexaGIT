
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
import org.springframework.context.support.ResourceBundleMessageSource;
/*
import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
*/
import java.util.Locale;
import java.util.Collection;
import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import com.ametis.cms.service.*;
import com.ametis.cms.util.*;



// imports+ 

// imports- 

/**
 * PolicyBillingRate is a mapping of policy_billing_rate Table.
*/
public class PolicyBillingRateFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	PolicyBillingRateService policyBillingRateService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	private CaseStatusService caseStatusService;
	
	ItemService itemService;
	
	

	public CaseStatusService getCaseStatusService() {
		return caseStatusService;
	}

	public void setCaseStatusService(CaseStatusService caseStatusService) {
		this.caseStatusService = caseStatusService;
	}

	public void setItemService(ItemService obj){
		this.itemService = obj;
	}

	public ItemService getItemService(){
		return this.itemService;
	}
				PolicyService policyService;

	public void setPolicyService(PolicyService obj){
		this.policyService = obj;
	}

	public PolicyService getPolicyService(){
		return this.policyService;
	}

	

	public void setPolicyBillingRateService (PolicyBillingRateService object){
	    this.policyBillingRateService = object;
	}
	public PolicyBillingRateService getPolicyBillingRateService (){
	    return this.policyBillingRateService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getUserService() {
		return actionuserService;
	}
	public void setUserService(UserService userService) {
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

	
	public PolicyBillingRateFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PolicyBillingRateForm tmp = null;
						Integer policyBillingRateId = WebUtil.getParameterInteger (request,"policyBillingRateId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								policyBillingRateId != null
				) {
						java.io.Serializable pkey = policyBillingRateId;
						PolicyBillingRate object = policyBillingRateService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PolicyBillingRateForm(object);
			 // foreign affairs
	
				tmp.setItemId(""+
					object.getItemId().getItemId()
				);


	
				tmp.setPolicyId(""+
					object.getPolicyId().getPolicyId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyBillingRateForm();
					 // foreign affairs
	
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getPolicyBillingRate().setItemId(item);
			}


	
	
			Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyBillingRate().setPolicyId(policy);
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyBillingRateForm();
					 // foreign affairs
		
	
				Integer itemId = WebUtil.getParameterInteger (request,"itemId");
		
			if(itemId!=null){
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId(""+itemId);

				Item item = this.itemService.get(itemId);
				tmp.getPolicyBillingRate().setItemId(item);
			}
	
	
			Integer policyId = WebUtil.getParameterInteger (request,"policyId");
		
			if(policyId!=null){
				Policy forClass = new Policy();
				forClass.setPolicyId(policyId);
				tmp.setPolicyId(""+policyId);

				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyBillingRate().setPolicyId(policy);
			}


		}
																																		
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		PolicyBillingRateForm policyBillingRateForm = ( PolicyBillingRateForm ) command;
		PolicyBillingRate policyBillingRate = policyBillingRateForm.getPolicyBillingRate();

//		errors.setNestedPath("policyBillingRate");
//		getValidator().validate(policyBillingRate, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		Collection<CaseStatus> caseStatusList = caseStatusService.getAll();
		
		model.put("caseStatusList", caseStatusList);
		
		Collection<Item> itemList = itemService.getClientInvoiceItemList();
		
		model.put("itemList", itemList);

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PolicyBillingRateForm policyBillingRateForm = ( PolicyBillingRateForm ) command;

		PolicyBillingRate res = null;
		String alertMsg="";
		try {
		// foreign affairs
					if(policyBillingRateForm.getItemId() != null){
				policyBillingRateForm.getPolicyBillingRate().setItemId(
					this.itemService.get(
						new Integer((policyBillingRateForm.getItemId()))
						)
				);
			}
	
					if(policyBillingRateForm.getPolicyId() != null){
				policyBillingRateForm.getPolicyBillingRate().setPolicyId(
					this.policyService.get(
						new Integer((policyBillingRateForm.getPolicyId()))
						)
				);
			}
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			/*
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			ActionUser user = null;
			if (auth != null && auth.getPrincipal() instanceof UserDetails) {
				UserDetails principal = (UserDetails)auth.getPrincipal();
				String[] eqP = new String[]{"email"};
				String[] eqQ = new String[]{principal.getUsername()};

					Collection coll = 
				actionuserService.search(null,null,eqP,eqQ,-1,-1);
				if(coll==null||coll.size()<=0){
					user = new ActionUser();
				}else{
					ActionUser users[] = 
					new ActionUser[coll.size()];
					coll.toArray(users);
					user = users[0];
				}


			}
			*/
			
			ActionUser user = securityService.getActionUser(request);
			
			if (policyBillingRateForm.isNewPolicyBillingRate ()) {
				res = policyBillingRateService.create (policyBillingRateForm.getPolicyBillingRate(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.policybillingrate",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.policybillingrate",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = policyBillingRateService.update (policyBillingRateForm.getPolicyBillingRate(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.policybillingrate",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.policybillingrate",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (policyBillingRateForm.isNewPolicyBillingRate ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.policybillingrate",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.policybillingrate",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("policybillingrate"+"?alert="+alertMsg));
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
