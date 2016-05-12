package com.ametis.cms.web.controller;

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
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.Fund;
import com.ametis.cms.datamodel.FundCategory;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.FundCategoryService;
import com.ametis.cms.service.FundService;
import com.ametis.cms.service.OutstandingService;
import com.ametis.cms.service.PaymentStatusService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.FundForm;

// imports+ 

// imports- 

/**
 * Fund is a mapping of fund Table.
 */
public class FundFormController extends SimpleFormController
// extends+

// extends-

{

	CurrencyService currencyService;

	FundCategoryService fundCategoryService;

	FundService fundService;

	PaymentStatusService paymentStatusService;

	ResourceBundleMessageSource alertProperties;
	
	ExcessChargeService excessChargeService;
	
	OutstandingService outstandingService;
	private ProviderService providerService;
	private PolicyService policyService;
	private PolicyCoverageFundService policyCoverageFundService;

	// foreign affairs

	ClientService clientService;

	private SecurityService securityService;
private ActivityLogService logService;

	
	
	public ProviderService getProviderService() {
	return providerService;
}

public void setProviderService(ProviderService providerService) {
	this.providerService = providerService;
}

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
	
	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public OutstandingService getOutstandingService() {
		return outstandingService;
	}

	public void setOutstandingService(OutstandingService outstandingService) {
		this.outstandingService = outstandingService;
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public FundCategoryService getFundCategoryService() {
		return fundCategoryService;
	}

	public void setFundCategoryService(FundCategoryService fundCategoryService) {
		this.fundCategoryService = fundCategoryService;
	}

	public void setClientService(ClientService obj) {
		this.clientService = obj;
	}

	public ClientService getClientService() {
		return this.clientService;
	}

	// -- foreign affairs end

	public PaymentStatusService getPaymentStatusService() {
		return paymentStatusService;
	}

	public void setPaymentStatusService(
			PaymentStatusService paymentStatusService) {
		this.paymentStatusService = paymentStatusService;
	}

	public void setFundService(FundService object) {
		this.fundService = object;
	}

	public FundService getFundService() {
		return this.fundService;
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

	public FundFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		FundForm tmp = null;
		Integer fundId = WebUtil.getParameterInteger(request, "fundId");
		Integer excessChargeId = WebUtil.getParameterInteger(request,
				"excessChargeId");
		
		Integer outstandingId = WebUtil.getParameterInteger(request,"outstandingId");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		Integer invoiceId = WebUtil.getParameterInteger(request, "invoiceId");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		Integer policyCoverageFundId = WebUtil.getParameterInteger(request, "policyCoverageFundId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb ="";

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (fundId != null) {

			java.io.Serializable pkey = fundId;
			Fund object = fundService.get(pkey);

			if (object != null) { // edit object

				tmp = new FundForm(object);
				if(object.getClientId() != null){
					tmp.setClientId(object.getClientId().getClientId().toString());
					tmp.setClientName(object.getClientId().getClientName());
				}

			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new FundForm();
				// foreign affairs
				
				Integer clientId = WebUtil.getParameterInteger(request,
						"clientId");

				if (clientId != null) {

					Client client = this.clientService.get(clientId);
					tmp.getFund().setClientId(client);
					tmp.setClientId(client.getClientId().toString());
					tmp.setClientName(client.getClientName());
				} else {
					
				}
				PaymentStatus status = new PaymentStatus();
				status.setPaymentStatusId(Integer.valueOf(PaymentStatus.PAYMENT_OPEN));
				tmp.setFundStatus(status);

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new FundForm();
			// foreign affairs

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			if (clientId != null) {

				Client client = this.clientService.get(clientId);
				tmp.getFund().setClientId(client);
				tmp.setClientId(client.getClientId().toString());
				tmp.setClientName(client.getClientName());
			} 
			else {
			}
			
			PaymentStatus status = new PaymentStatus();
			status.setPaymentStatusId(Integer.valueOf(PaymentStatus.PAYMENT_OPEN));
			tmp.setFundStatus(status);
			
			if (navigation.equalsIgnoreCase("listinvoiceexcess")){
				breadcrumb = "<a href=\"invoice?navigation=detail&invoiceId="+invoiceId+"\" class=\"linkbreadcrumb\">Detail Invoice</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;";
				breadcrumb += "<a href=\"excesscharge?navigation=listinvoice&invoiceId="+invoiceId+"\" class=\"linkbreadcrumb\">Excess Charge List</a>  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp; Excess Payment";
			}
			if (navigation.equalsIgnoreCase("addpolicyfund")){
				breadcrumb = "<a href=\"fund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Fund";
			}
			if (navigation.equalsIgnoreCase("addpolicyexcessfund")){
				breadcrumb = "<a href=\"fund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Excess Fund";
			}
			if (navigation.equalsIgnoreCase("addcoveragefund")){
				breadcrumb = "<a href=\"policycoveragefund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Coverege Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Fund";
			}
			if (navigation.equalsIgnoreCase("addcoverageexcess")){
				breadcrumb = "<a href=\"policycoveragefund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Coverege Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Excess Fund";
			}
			if (navigation.equalsIgnoreCase("tambah")){
				breadcrumb = "<a href=\"fund\" class=\"linkbreadcrumb\">Search Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Request Floating Fund";
			}
			request.setAttribute("breadcrumb", breadcrumb);
			request.setAttribute("navigation", navigation);
			request.setAttribute("policyId", policyId);
		
		}
		if (providerId != null){
			Provider provider = providerService.get(providerId);
			
			if (provider != null){
				tmp.setProviderId(provider.getProviderId().toString());
				tmp.setProviderName(provider.getProviderName());
			}
		}

		if (excessChargeId != null){
			String[] required = {"ExcessCharge.ClaimId","ExcessCharge.ClaimId.BatchClaimId.PaymentCurrency","ExcessCharge.MemberId.ClientId"};
			ExcessCharge excess = excessChargeService.get(excessChargeId,required);
			
			if (excess != null){
				tmp.setExcessCharge(excess);
				
				tmp.setFundType(Integer.valueOf(Fund.EXCESS_CHARGE_PAYMENT)+"");
				
				tmp.setClientId(excess.getMemberId().getClientId().getClientId().toString());
				tmp.setClientName(excess.getMemberId().getClientId().getClientName());
				if (excess.getExcessChargeValue() != null){
					tmp.setFundValue(StringUtil.getStringValue(excess.getExcessChargeValue()) );
				}
				
				if (excess.getClaimId().getBatchClaimId() != null){
					Currency cur = excess.getClaimId().getBatchClaimId().getPaymentCurrency();
					tmp.setFundCurrency(cur.getCurrencyId()+"");
				}
			}
			else {
				tmp.setExcessCharge(new ExcessCharge());
			}
		}
		if (navigation.equalsIgnoreCase("addpolicyfund") || navigation.equalsIgnoreCase("addpolicyexcessfund") || navigation.equalsIgnoreCase("addcoveragefund") || navigation.equalsIgnoreCase("addcoverageexcess")){
			if (policyId != null){
				Policy policy = policyService.get(policyId);
				
				if (policy != null){
					Client client = clientService.get(policy.getClientId().getClientId());
					
					tmp.setPolicyNumber(policy.getPolicyNumber());
					tmp.setClientName(client.getClientName());
					tmp.setPolicyId(policy.getPolicyId().toString());
					tmp.setClientId(client.getClientId().toString());
				}
			}
		}
		
		
		
		
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb ="";
		
		FundForm fundForm = (FundForm) command;
		Fund fund = fundForm.getFund();
		
		if (navigation.equalsIgnoreCase("addpolicyfund")){
			breadcrumb = "<a href=\"fund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Fund";
		}
		if (navigation.equalsIgnoreCase("addpolicyexcessfund")){
			breadcrumb = "<a href=\"fund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Excess Fund";
		}
		if (navigation.equalsIgnoreCase("addcoveragefund")){
			breadcrumb = "<a href=\"policycoveragefund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Coverege Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Fund";
		}
		if (navigation.equalsIgnoreCase("addcoverageexcess")){
			breadcrumb = "<a href=\"policycoveragefund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Coverege Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Excess Fund";
		}
		if (navigation.equalsIgnoreCase("tambah")){
			breadcrumb = "<a href=\"fund\" class=\"linkbreadcrumb\">Search Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Request Floating Fund";
		}
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("policyId", policyId);

		errors.printStackTrace();
		// errors.setNestedPath("fund");
		// getValidator().validate(fund, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		
		Map model = new HashMap();
		
		if (navigation.equalsIgnoreCase("addprovider")){
			FundCategory category = new FundCategory();
			category.setFundCategoryId(Fund.PROVIDER_CAPITATION_PAYMENT);
			category.setFundCategoryName("Provider Capitation Fund");
			
			Collection<FundCategory> categoryList = new Vector<FundCategory>();
			categoryList.add(category);
			
			model.put("fundCategory",categoryList);
		}
		else if (navigation.equalsIgnoreCase("addpolicyfund")){
			FundCategory category = new FundCategory();
			category.setFundCategoryId(Fund.POLICY_FLOATING_FUND);
			category.setFundCategoryName("Policy Aso Fund");
			
			Collection<FundCategory> categoryList = new Vector<FundCategory>();
			categoryList.add(category);
			
			model.put("fundCategory",categoryList);
		}
		else if (navigation.equalsIgnoreCase("addpolicyexcessfund")){
			FundCategory category = new FundCategory();
			category.setFundCategoryId(Fund.POLICY_EXCESS_FUND);
			category.setFundCategoryName("Policy Aso Excess Fund");
			
			Collection<FundCategory> categoryList = new Vector<FundCategory>();
			categoryList.add(category);
			
			model.put("fundCategory",categoryList);
		}
		else if (navigation.equalsIgnoreCase("addcoveragefund")){
			FundCategory category = new FundCategory();
			category.setFundCategoryId(Fund.POLICY_COVERAGE_FUND);
			category.setFundCategoryName("Coverage Aso Fund");
			
			Collection<FundCategory> categoryList = new Vector<FundCategory>();
			categoryList.add(category);
			
			model.put("fundCategory",categoryList);
			String[] eqParam = {"deletedStatus","policyId.policyId"};
			Object[] eqValue = {0,policyId};
			
			int total = policyCoverageFundService.getTotal(null,null,eqParam,eqValue);
			Collection<PolicyCoverageFund> coverageList = policyCoverageFundService.search(null,null,eqParam,eqValue,0,total);
			model.put("coverageList", coverageList);
		}
		else if (navigation.equalsIgnoreCase("addcoverageexcess")){
			FundCategory category = new FundCategory();
			category.setFundCategoryId(Fund.POLICY_COVERAGE_EXCESS_FUND);
			category.setFundCategoryName("Coverage Excess Fund");
			
			Collection<FundCategory> categoryList = new Vector<FundCategory>();
			categoryList.add(category);
			
			model.put("fundCategory",categoryList);
			
			String[] eqParam = {"deletedStatus","policyId.policyId"};
			Object[] eqValue = {0,policyId};
			
			int total = policyCoverageFundService.getTotal(null,null,eqParam,eqValue);
			Collection<PolicyCoverageFund> coverageList = policyCoverageFundService.search(null,null,eqParam,eqValue,0,total);
			model.put("coverageList", coverageList);
		}
		else {
			Collection fundCategory = fundCategoryService.getAll();
			model.put("fundCategory", fundCategory);
		}

		Collection currency = currencyService.getAll();
		model.put("fundCurrency", currency);
		
		
		model.put("navigation", navigation);
		
		String invoiceId = request.getParameter("invoiceId");
		model.put("invoiceId", invoiceId);
		
		String excessId = request.getParameter("excessChargeId");
		model.put("excessChargeId", excessId);

		//Collection paymentStatusCol = paymentStatusService.getAll();
		//model.put("paymentStatusCollection", paymentStatusCol);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String breadcrumb ="";
		FundForm fundForm = (FundForm) command;

		String navigation = request.getParameter("navigation"); 
		Fund res = null;
		String redirectUrl = "fund";
		String alertMsg = "";
		try {
			// foreign affairs

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}
			

			if (fundForm.isNewFund()) {
				ActionUser user= securityService.getActionUser(request); 
			
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEFUND");
			
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEFUND access");
					return errorResult;					
				}
				
				if (fundForm.getFund() != null){				
					fundForm.getFund().setFundStatus(new PaymentStatus(PaymentStatus.PAYMENT_PENDING));			
				}
				
				res = fundService.create(fundForm.getFund(), user);

				if (res != null) {
					if (navigation.equalsIgnoreCase("listinvoiceexcess")){
						String invoiceId = request.getParameter("invoiceId");
						redirectUrl = "excesscharge?navigation=listinvoice&invoiceId="+invoiceId+"&alert=Sukses melakukan pembayaran excess";
					}
					else if (navigation.equalsIgnoreCase("detailexcess")){
						String excessChargeId = request.getParameter("excessChargeId");
						redirectUrl = "excesscharge?navigation=detail&excessChargeId="+excessChargeId+"&alert=Sukses melakukan pembayaran excess";
					}
					else if (navigation.equalsIgnoreCase("addcoveragefund") || navigation.equalsIgnoreCase("addcoverageexcess")){
						
						redirectUrl = "policycoveragefund?navigation=listpolicy&policyId="+fundForm.getPolicyId()+"&alert=Sukses melakukan top up";
					}
					else if (navigation.equalsIgnoreCase("addpolicyfund") || navigation.equalsIgnoreCase("addpolicyexcessfund")){
						
						redirectUrl = "fund?navigation=listpolicy&policyId="+fundForm.getPolicyId()+"&alert=Sukses melakukan top up";
					}
					else {
						redirectUrl = "fund?alert=Sukses melakukan pembayaran";
					}
					alertMsg = alertProperties.getMessage(
							"success.create.fund", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.fund",
							null, "fail", Locale.getDefault());
				}
			} else {
				ActionUser user= securityService.getActionUser(request); 
			
				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATEFUND");
			
				if (!isUserAuthorized){
					
					ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
					errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATEFUND access");
					return errorResult;
					
				}
				res = fundService.update(fundForm.getFund(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.fund", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.fund",
							null, "fail", Locale.getDefault());
				}

			}
		} catch (Exception ex) {

			ex.printStackTrace();
			if (fundForm.isNewFund()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.fund", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.fund", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		if (navigation.equalsIgnoreCase("addpolicyfund")){
			breadcrumb = "<a href=\"fund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Fund";
		}
		if (navigation.equalsIgnoreCase("addpolicyexcessfund")){
			breadcrumb = "<a href=\"fund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Excess Fund";
		}
		if (navigation.equalsIgnoreCase("addcoveragefund")){
			breadcrumb = "<a href=\"policycoveragefund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Coverege Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Fund";
		}
		if (navigation.equalsIgnoreCase("addcoverageexcess")){
			breadcrumb = "<a href=\"policycoveragefund?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Coverege Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Excess Fund";
		}
		if (navigation.equalsIgnoreCase("tambah")){
			breadcrumb = "<a href=\"fund\" class=\"linkbreadcrumb\">Search Fund</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Request Floating Fund";
		}
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("policyId", policyId);
		
		return new ModelAndView(new RedirectView(redirectUrl));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);
		

		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numInt);
		
		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
        binder.registerCustomEditor(java.lang.Double.class,
                new CustomNumberEditor (java.lang.Double.class, nf, true));
		
	}
	// class+

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}

	public void setPolicyCoverageFundService(
			PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}

	// class-

}
