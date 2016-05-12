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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CardType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.TarifType;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CardTypeService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductTypeService;
import com.ametis.cms.service.QuotationService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.TarifTypeService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyForm;

// imports+ 

// imports- 

/**
 * Policy is a mapping of policy Table.
 */
public class PolicyFormController extends SimpleFormController
// extends+ 

// extends-
{

	PolicyService policyService;
	ResourceBundleMessageSource alertProperties;

	SecurityService securityService;
	private CardTypeService cardTypeService;

	// foreign affairs

	ClientService clientService;
	private ActivityLogService logService;
	private ProductTypeService productTypeService;
	
	private TarifTypeService tarifTypeService;

	
	
	
	public TarifTypeService getTarifTypeService() {
		return tarifTypeService;
	}

	public void setTarifTypeService(TarifTypeService tarifTypeService) {
		this.tarifTypeService = tarifTypeService;
	}

	public CardTypeService getCardTypeService() {
		return cardTypeService;
	}

	public void setCardTypeService(CardTypeService cardTypeService) {
		this.cardTypeService = cardTypeService;
	}

	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public void setClientService(ClientService obj) {
		this.clientService = obj;
	}

	public ClientService getClientService() {
		return this.clientService;
	}

	MemberGroupService memberGroupService;

	public void setMemberGroupService(MemberGroupService obj) {
		this.memberGroupService = obj;
	}

	public MemberGroupService getMemberGroupService() {
		return this.memberGroupService;
	}

	QuotationService quotationService;

	public void setQuotationService(QuotationService obj) {
		this.quotationService = obj;
	}

	public QuotationService getQuotationService() {
		return this.quotationService;
	}

	// -- foreign affairs end

	public void setPolicyService(PolicyService object) {
		this.policyService = object;
	}

	public PolicyService getPolicyService() {
		return this.policyService;
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

	public PolicyFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		PolicyForm tmp = null;
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb = "";
		
		System.out.println("policyForm");

		if (policyId != null) {
			java.io.Serializable pkey = policyId;
			String[] required = {"Policy.ClientId","Policy.MemberGroupId"};
			Policy object = policyService.get(pkey,required);

			if (object != null) { // edit object
				System.out.println("Edit...");
				
				
				tmp = new PolicyForm(object);
				// foreign affairs
				
				if (object.getClientId() != null){
					Client client = clientService.get(object.getClientId().getClientId());
					tmp.setClientId("" + object.getClientId().getClientId());
					tmp.setClientName(client.getClientName());
				}

				if (object.getMemberGroupId()!= null){
					MemberGroup memberGroup = memberGroupService.get(object.getMemberGroupId().getMemberGroupId());
					tmp.setMemberGroupId(""
						+ object.getMemberGroupId().getMemberGroupId());
					tmp.setMemberGroupName(memberGroup.getGroupName());
				}


			} else {
				
				/**
				 * Condition ini akan terpenuhi apabila ID dari object tersebut tidak tersedia di dalam database.
				 */
				tmp = new PolicyForm();

				Integer clientId = WebUtil.getParameterInteger(request,
						"clientId");

				if (clientId != null) {
					Client forClass = new Client();
					forClass.setClientId(clientId);
					tmp.setClientId("" + clientId);

					Client client = this.clientService.get(clientId);
					tmp.getPolicy().setClientId(client);
					tmp.setClientName(client.getClientName());
				} else {
					
				}

				Integer memberGroupId = WebUtil.getParameterInteger(request,
						"memberGroupId");

				if (memberGroupId != null) {
					MemberGroup forClass = new MemberGroup();
					forClass.setMemberGroupId(memberGroupId);
					tmp.setMemberGroupId("" + memberGroupId);

					
					MemberGroup memberGroup = this.memberGroupService
							.get(memberGroupId);
					tmp.getPolicy().setMemberGroupId(memberGroup);
					tmp.setMemberGroupName(memberGroup.getGroupName());
				} else {
				
				}

			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyForm();
			// foreign affairs

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			if (clientId != null) {
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId("" + clientId);

				Client client = this.clientService.get(clientId);
				tmp.getPolicy().setClientId(client);
			} else {
				
			}

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			if (memberGroupId != null) {
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId("" + memberGroupId);

				MemberGroup memberGroup = this.memberGroupService
						.get(memberGroupId);
				tmp.getPolicy().setMemberGroupId(memberGroup);
				tmp.setMemberGroupName(memberGroup.getGroupName());
			} else {
				
			}

			breadcrumb = "<a href=\"policy-form?navigation="+navigation+"\" class=\"linkbreadcrumb\">Register Policy</a>";
			request.setAttribute("breadcrumb", breadcrumb);

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		PolicyForm policyForm = (PolicyForm) command;
		Policy policy = policyForm.getPolicy();
		
		errors.printStackTrace();

		// errors.setNestedPath("policy");
		// getValidator().validate(policy, errors);
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
			
			Collection<ProductType> productTypes = productTypeService.getAll();
			model.put("productTypes", productTypes);
			
			Collection<CardType> cardTypeList = cardTypeService.getAll();
			model.put("cardTypeList", cardTypeList);
			
			Collection<TarifType> tarifTypeList = tarifTypeService.getAll();
			model.put("tarifTypeList", tarifTypeList);
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PolicyForm policyForm = (PolicyForm) command;

		Policy res = null;
		String alertMsg = "";
		
		String memberGroupId = null;
		try {
			// foreign affairs
			if (policyForm.getClientId() != null) {
				policyForm.getPolicy().setClientId(
						this.clientService.get(new Integer((policyForm
								.getClientId()))));
			}

			if (policyForm.getMemberGroupId() != null) {
				policyForm.getPolicy().setMemberGroupId(
						this.memberGroupService.get(new Integer((policyForm
								.getMemberGroupId()))));
				memberGroupId = policyForm.getMemberGroupId();
			}

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);

			if (policyForm.isNewPolicy()) {
				Collection<MultipartFile> tcFiles = new Vector<MultipartFile>();
				
				if (policyForm.getTcFile1() != null && !policyForm.getTcFile1().getOriginalFilename().equalsIgnoreCase("") && isAllowedExtension(policyForm.getTcFile1().getOriginalFilename()) ){
					MultipartFile tc1 = policyForm.getTcFile1();
					policyForm.getPolicy().setPolicyTcFile1("TC1"+System.currentTimeMillis()+"_"+tc1.getOriginalFilename());
					tcFiles.add(policyForm.getTcFile1());
				}
				if (policyForm.getTcFile2() != null && !policyForm.getTcFile2().getOriginalFilename().equalsIgnoreCase("") && isAllowedExtension(policyForm.getTcFile2().getOriginalFilename()) ){
					MultipartFile tc = policyForm.getTcFile2();
					
					policyForm.getPolicy().setPolicyTcFile2("TC2"+System.currentTimeMillis()+"_"+tc.getOriginalFilename());
					tcFiles.add(policyForm.getTcFile2());
				}
				if (policyForm.getTcFile3() != null && !policyForm.getTcFile3().getOriginalFilename().equalsIgnoreCase("") && isAllowedExtension(policyForm.getTcFile3().getOriginalFilename()) ){
					MultipartFile tc = policyForm.getTcFile3();					
					policyForm.getPolicy().setPolicyTcFile3("TC3"+System.currentTimeMillis()+"_"+tc.getOriginalFilename());
					tcFiles.add(policyForm.getTcFile3());
				}
				
				res = policyService.create(policyForm.getPolicy(),tcFiles, user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.policy", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.policy",
							null, "fail", Locale.getDefault());
				}
			} else {
				Collection<MultipartFile> tcFiles = new Vector<MultipartFile>();
				
				//Modified by aju on 20150226, add file extension checker :D 
				if (policyForm.getTcFile1() != null && !policyForm.getTcFile1().getOriginalFilename().equalsIgnoreCase("") && isAllowedExtension(policyForm.getTcFile1().getOriginalFilename())){
					MultipartFile tc1 = policyForm.getTcFile1();
					policyForm.getPolicy().setPolicyTcFile1("TC1"+System.currentTimeMillis()+"_"+tc1.getOriginalFilename());
					tcFiles.add(policyForm.getTcFile1());
				}
				if (policyForm.getTcFile2() != null && !policyForm.getTcFile2().getOriginalFilename().equalsIgnoreCase("")  && isAllowedExtension(policyForm.getTcFile2().getOriginalFilename()) ){
					MultipartFile tc = policyForm.getTcFile2();
					policyForm.getPolicy().setPolicyTcFile2("TC2"+System.currentTimeMillis()+"_"+tc.getOriginalFilename());
					tcFiles.add(policyForm.getTcFile2());
				}
				if (policyForm.getTcFile3() != null && !policyForm.getTcFile3().getOriginalFilename().equalsIgnoreCase("")  && isAllowedExtension(policyForm.getTcFile3().getOriginalFilename()) ){
					MultipartFile tc = policyForm.getTcFile3();					
					policyForm.getPolicy().setPolicyTcFile3("TC3"+System.currentTimeMillis()+"_"+tc.getOriginalFilename());
					tcFiles.add(policyForm.getTcFile3());
				}
				
				res = policyService.update(policyForm.getPolicy(),tcFiles, user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.policy", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.policy",
							null, "fail", Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (policyForm.isNewPolicy()) {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.create.policy", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.update.policy", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("policy?navigation=listgroup&memberGroupId="+memberGroupId + "&alert="
				+ alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}
	
	/*
	 * Add by aju on 20150226
	 * For checking the file extension fufufu :D
	 */
	private boolean isAllowedExtension(String fullFileName){
		boolean allowIt=true;
		int idxExt;
		String theExt="",theTitle="";
		String theAllowed="|xls|xlsx|doc|docx|pdf|";
		
		if(fullFileName.trim().length() > 0){
			idxExt = fullFileName.lastIndexOf(".");
			theTitle = fullFileName.substring(0, idxExt);
			theExt = fullFileName.substring(idxExt + 1);
			
			allowIt =  (theAllowed.indexOf("|"+theExt+"|") >= 0 ? true : false);
			
			System.out.println("File Extension Checker");
			System.out.println("File Name : " + theTitle );
			System.out.println("File Ext. : " + theExt);
		}
		else{
			allowIt = false;
		}
		
		System.out.println("Allowed   : " + allowIt);
		
		return allowIt;
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
