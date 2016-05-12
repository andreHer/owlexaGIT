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
import com.ametis.cms.datamodel.Branch;
import com.ametis.cms.datamodel.Broker;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Quotation;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BranchService;
import com.ametis.cms.service.BrokerService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.QuotationService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.QuotationForm;

// imports+ 

// imports- 

/**
 * Quotation is a mapping of quotation Table.
 */
public class QuotationFormController extends SimpleFormController
// extends+

// extends-
{

	QuotationService quotationService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	BrokerService brokerService;
	private PolicyService policyService;

	private ActivityLogService logService;

	
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

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setBrokerService(BrokerService obj) {
		this.brokerService = obj;
	}

	public BrokerService getBrokerService() {
		return this.brokerService;
	}

	ClientService clientService;

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

	CurrencyService currencyService;

	public void setCurrencyService(CurrencyService obj) {
		this.currencyService = obj;
	}

	public CurrencyService getCurrencyService() {
		return this.currencyService;
	}

	BranchService branchService;

	public void setBranchService(BranchService obj) {
		this.branchService = obj;
	}

	public BranchService getBranchService() {
		return this.branchService;
	}

	// -- foreign affairs end

	public void setQuotationService(QuotationService object) {
		this.quotationService = object;
	}

	public QuotationService getQuotationService() {
		return this.quotationService;
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

	public QuotationFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		QuotationForm tmp = null;
		Integer quotationId = WebUtil.getParameterInteger(request,
				"quotationId");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");

		if (quotationId != null) {
			java.io.Serializable pkey = quotationId;
			Quotation object = quotationService.get(pkey);

			if (object != null) {
				tmp = new QuotationForm(object);
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new QuotationForm();
				// foreign affairs

				Integer brokerId = WebUtil.getParameterInteger(request,
						"brokerId");

				if (brokerId != null) {
					Broker forClass = new Broker();
					forClass.setBrokerId(brokerId);
					tmp.setBrokerId("" + brokerId);

					Broker broker = this.brokerService.get(brokerId);
					tmp.getQuotation().setBrokerId(broker);
				} else {
					Broker forClass = new Broker();
					// tmp.setBrokerId("");
					tmp.getQuotation().setBrokerId(forClass);
				}

				Integer clientId = WebUtil.getParameterInteger(request,
						"clientId");

				if (clientId != null) {
					Client forClass = new Client();
					forClass.setClientId(clientId);
					tmp.setClientId("" + clientId);

					Client client = this.clientService.get(clientId);
					tmp.getQuotation().setClientId(client);
				} else {
					Client forClass = new Client();
					// tmp.setClientId("");
					tmp.getQuotation().setClientId(forClass);
				}

				Integer memberGroupId = WebUtil.getParameterInteger(request,
						"memberGroupId");

				if (memberGroupId != null) {
					MemberGroup forClass = new MemberGroup();
					forClass.setMemberGroupId(memberGroupId);
					tmp.setMemberGroupId("" + memberGroupId);

					MemberGroup memberGroup = this.memberGroupService
							.get(memberGroupId);
					tmp.getQuotation().setMemberGroupId(memberGroup);
					tmp.setMemberGroupName(memberGroup.getGroupName());
				} else {
					MemberGroup forClass = new MemberGroup();
					// tmp.setMemberGroupId("");
					tmp.getQuotation().setMemberGroupId(forClass);
				}

				Integer currencyId = WebUtil.getParameterInteger(request,
						"currencyId");

				if (currencyId != null) {
					Currency forClass = new Currency();
					forClass.setCurrencyId(currencyId);
					tmp.setCurrencyId("" + currencyId);

					Currency currency = this.currencyService.get(currencyId);
					tmp.getQuotation().setCurrencyId(currency);
				} else {
					Currency forClass = new Currency();
					// tmp.setCurrencyId("");
					tmp.getQuotation().setCurrencyId(forClass);
				}

				Integer branchId = WebUtil.getParameterInteger(request,
						"branchId");

				if (branchId != null) {
					Branch forClass = new Branch();
					forClass.setBranchId(branchId);
					tmp.setBranchId("" + branchId);

					Branch branch = this.branchService.get(branchId);
					tmp.getQuotation().setBranchId(branch);
				} else {
					Branch forClass = new Branch();
					// tmp.setBranchId("");
					tmp.getQuotation().setBranchId(forClass);
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new QuotationForm();
			// foreign affairs

			Integer brokerId = WebUtil.getParameterInteger(request, "brokerId");

			if (brokerId != null) {
				Broker forClass = new Broker();
				forClass.setBrokerId(brokerId);
				tmp.setBrokerId("" + brokerId);

				Broker broker = this.brokerService.get(brokerId);
				tmp.getQuotation().setBrokerId(broker);
			} else {
				Broker forClass = new Broker();
				// tmp.setBrokerId("");
				tmp.getQuotation().setBrokerId(forClass);
			}

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			if (clientId != null) {
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId("" + clientId);

				Client client = this.clientService.get(clientId);
				tmp.getQuotation().setClientId(client);
				tmp.setClientName(client.getClientName());
			} else {
				Client forClass = new Client();
				// tmp.setClientId("");
				tmp.getQuotation().setClientId(forClass);
			}

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			if (memberGroupId != null) {
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId("" + memberGroupId);

				MemberGroup memberGroup = this.memberGroupService
						.get(memberGroupId);
				tmp.getQuotation().setMemberGroupId(memberGroup);
				tmp.setMemberGroupName(memberGroup.getGroupName());
			} else {
				MemberGroup forClass = new MemberGroup();
				// tmp.setMemberGroupId("");
				tmp.getQuotation().setMemberGroupId(forClass);
			}

			Integer currencyId = WebUtil.getParameterInteger(request,
					"currencyId");

			if (currencyId != null) {
				Currency forClass = new Currency();
				forClass.setCurrencyId(currencyId);
				tmp.setCurrencyId("" + currencyId);

				Currency currency = this.currencyService.get(currencyId);
				tmp.getQuotation().setCurrencyId(currency);
			} else {
				Currency forClass = new Currency();
				// tmp.setCurrencyId("");
				tmp.getQuotation().setCurrencyId(forClass);
			}

			Integer branchId = WebUtil.getParameterInteger(request, "branchId");

			if (branchId != null) {
				Branch forClass = new Branch();
				forClass.setBranchId(branchId);
				tmp.setBranchId("" + branchId);

				Branch branch = this.branchService.get(branchId);
				tmp.getQuotation().setBranchId(branch);
				tmp.setBranchName(branch.getBranchName());
			} else {
				Branch forClass = new Branch();
				// tmp.setBranchId("");
				tmp.getQuotation().setBranchId(forClass);
			}

			// -- foreign affairs end

		}

		if (policyId != null) {
			Policy policy = policyService.get(policyId);
			
			if (policy != null){
				tmp.setMemberGroupName(policy.getMemberGroupId().getGroupName());
				tmp.setMemberGroupId(policy.getMemberGroupId().getMemberGroupId().toString());
				
				if (policy.getBrokerId() != null){
					tmp.setBrokerName(policy.getBrokerId().getBrokerName());
					tmp.setBrokerId(policy.getBrokerId().getBrokerId().toString());
				}
				
				
			}
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		QuotationForm quotationForm = (QuotationForm) command;
		Quotation quotation = quotationForm.getQuotation();

		// errors.setNestedPath("quotation");
		// getValidator().validate(quotation, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		try {
			Collection<Currency> currencyList = currencyService.getAll();
			model.put("currencyList", currencyList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		QuotationForm quotationForm = (QuotationForm) command;

		Quotation res = null;
		String alertMsg = "";
		try {

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);
			if (quotationForm.isNewQuotation()) {
				res = quotationService.create(quotationForm.getQuotation(),
						user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.quotation", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.quotation", null, "fail", Locale
									.getDefault());
				}
			} else {
				res = quotationService.update(quotationForm.getQuotation(),
						user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.quotation", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.quotation", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (quotationForm.isNewQuotation()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.quotation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.quotation", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("quotation" + "?alert="
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

	// class-
}
