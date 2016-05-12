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
import com.ametis.cms.datamodel.Bank;
import com.ametis.cms.datamodel.BankAccount;
import com.ametis.cms.datamodel.Broker;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BankAccountService;
import com.ametis.cms.service.BankService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.LogUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.BankAccountForm;

// imports+ 
// imports- 
/**
 * BankAccount is a mapping of bank_account Table.
 */
public class BankAccountFormController extends SimpleFormController // extends+
// extends-
{

	BankAccountService bankAccountService;
	ResourceBundleMessageSource alertProperties;
	private CurrencyService currencyService;
	private SecurityService securityService;
	// foreign affairs
	BankService bankService;

	
	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public void setBankService(BankService obj) {
		this.bankService = obj;
	}

	public BankService getBankService() {
		return this.bankService;
	}

	ClientService clientService;
	private ActivityLogService logService;

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

	MemberService memberService;

	public void setMemberService(MemberService obj) {
		this.memberService = obj;
	}

	public MemberService getMemberService() {
		return this.memberService;
	}

	ProviderService providerService;

	public void setProviderService(ProviderService obj) {
		this.providerService = obj;
	}

	public ProviderService getProviderService() {
		return this.providerService;
	}

	// -- foreign affairs end
	public void setBankAccountService(BankAccountService object) {
		this.bankAccountService = object;
	}

	public BankAccountService getBankAccountService() {
		return this.bankAccountService;
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

	public void setSecurityService(SecurityService object) {
		this.securityService = object;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	public BankAccountFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		BankAccountForm tmp = null;
		Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
		Integer id = WebUtil.getParameterInteger(request, "id");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		String refId = WebUtil.getParameterString(request, "refId", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		String tipe = WebUtil.getParameterString(request, "tipe", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String previousURL = WebUtil.getParameterString(request,
				"previousURL", "");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (id != null) {
			java.io.Serializable pkey = id;
			BankAccount object = bankAccountService.get(pkey);

			if (object != null) { // edit object

				tmp = new BankAccountForm(object);
				// foreign affairs

				if (object.getBankId() != null){
					Bank bank = bankService.get(object.getBankId().getBankId());
					
					tmp.setBankId("" + object.getBankId().getBankId());
					tmp.setBankName(object.getBank());
				}
				
				if (object.getClientId() != null){
					tmp.setClientId("" + object.getClientId().getClientId());
				}

				if (object.getMemberGroupId() != null){
					tmp.setMemberGroupId(""
						+ object.getMemberGroupId().getMemberGroupId());
				}

				if (object.getMemberId() != null){
					tmp.setMemberId("" + object.getMemberId().getMemberId());
				}

				if (object.getProviderId() != null){
					tmp.setProviderId("" + object.getProviderId().getProviderId());
				}

				tmp.setTipe(tipe);
				tmp.setPreviousURL(previousURL);
				tmp.setRefId(refId);
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new BankAccountForm();
				// foreign affairs

				tmp.setTipe(tipe);
				tmp.setPreviousURL(previousURL);
				tmp.setRefId(refId);

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new BankAccountForm();
			// foreign affairs

			tmp.setTipe(tipe);
			tmp.setPreviousURL(previousURL);
			tmp.setRefId(refId);

		}
		
        String breadcrumb ="";
        
        if (navigation.equalsIgnoreCase("listprovider")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        if (navigation.equalsIgnoreCase("listgroup")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">List Group Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        if (navigation.equalsIgnoreCase("listclient")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        request.setAttribute("clientId", clientId);
        request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("providerId", providerId);
		request.setAttribute("memberGroupId", memberGroupId);
		request.setAttribute("index", index);
		request.setAttribute("searchby", searchby);
		request.setAttribute("tipe", tipe);
		
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		
		Integer id = WebUtil.getParameterInteger(request, "id");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		String tipe = WebUtil.getParameterString(request, "tipe", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		BankAccountForm bankAccountForm = (BankAccountForm) command;
		BankAccount bankAccount = bankAccountForm.getBankAccount();
		
        String breadcrumb ="";
        
        if (navigation.equalsIgnoreCase("listprovider")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        if (navigation.equalsIgnoreCase("listgroup")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">List Group Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        if (navigation.equalsIgnoreCase("listclient")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        request.setAttribute("memberGroupId", memberGroupId);
        request.setAttribute("clientId", clientId);
        request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("providerId", providerId);
		request.setAttribute("index", index);
		request.setAttribute("searchby", searchby);
		request.setAttribute("tipe", tipe);
		

		// errors.setNestedPath("bankAccount");
		// getValidator().validate(bankAccount, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
	
		try {
			Collection<Bank> bankList = bankService.getAll();
			Collection<Currency> currencyList = currencyService.getAll();
			
			model.put("bankList", bankList);
			model.put("currencyList", currencyList);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		BankAccountForm bankAccountForm = (BankAccountForm) command;
		
		Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
		Integer id = WebUtil.getParameterInteger(request, "id");
		Integer index = WebUtil.getParameterInteger(request, "index");
		Integer providerId = WebUtil.getParameterInteger(request, "providerId");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		String refId = WebUtil.getParameterString(request, "refId", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		String tipe = WebUtil.getParameterString(request, "tipe", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String previousURL = WebUtil.getParameterString(request,
				"previousURL", "");

		BankAccount res = null;
		String alertMsg = "";
		try {
			// foreign affairs

			System.out.println("bankId : "
					+ WebUtil.getParameterString(request, "bankId", ""));
			System.out.println("bankId 2 : " + bankAccountForm.getBankId());

			if (bankAccountForm.getTipe() != null) {
				System.out.println("TIPE FORM :" + bankAccountForm.getTipe());
				if(!bankAccountForm.getRefId().equals("") && bankAccountForm.getRefId()!=null){
					if (bankAccountForm.getTipe().equalsIgnoreCase("group")) {
						MemberGroup memberGroup = new MemberGroup();
						memberGroup.setMemberGroupId(Integer
								.valueOf(bankAccountForm.getRefId()));
						bankAccountForm.getBankAccount().setMemberGroupId(
								memberGroup);
					}
					if (bankAccountForm.getTipe().equalsIgnoreCase("broker")) {
						Broker broker = new Broker();
						broker.setBrokerId(Integer.valueOf(bankAccountForm
								.getRefId()));
						bankAccountForm.getBankAccount().setBrokerId(broker);
					}
					if (bankAccountForm.getTipe().equalsIgnoreCase("client")) {
						Client client = new Client();
						client.setClientId(Integer.valueOf(bankAccountForm
								.getRefId()));
						bankAccountForm.getBankAccount().setClientId(client);
					}
					if (bankAccountForm.getTipe().equalsIgnoreCase("provider")) {
						Provider provider = new Provider();
						provider.setProviderId(Integer.valueOf(bankAccountForm
								.getRefId()));
						bankAccountForm.getBankAccount().setProviderId(provider);
					}
					if (bankAccountForm.getTipe().equalsIgnoreCase("member")) {
						Member member = new Member();
						member.setMemberId(Integer.valueOf(bankAccountForm
								.getMemberId()));
						bankAccountForm.getBankAccount().setMemberId(member);
					}
				}
			}

			ActionUser user = securityService.getActionUser(request);

			if (bankAccountForm.isNewBankAccount()) {
				res = bankAccountService.create(bankAccountForm
						.getBankAccount(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.bankaccount", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.bankaccount", null, "fail", Locale
									.getDefault());
					
					LogUtil.log(logService, user, "Create Bank Account   -  " + alertMsg, res.toString());
				}
			} else {
				res = bankAccountService.update(bankAccountForm
						.getBankAccount(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.bankaccount", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.bankaccount", null, "fail", Locale
									.getDefault());
				}

				LogUtil.log(logService, user, "Update Bank Account   -  " + alertMsg, res.toString());
			}
		} catch (Exception ex) {
			if (bankAccountForm.isNewBankAccount()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.bankaccount", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.bankaccount", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
        String breadcrumb ="";
        
        if (navigation.equalsIgnoreCase("listprovider")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        if (navigation.equalsIgnoreCase("listgroup")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">List Group Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        if (navigation.equalsIgnoreCase("listclient")) {
            breadcrumb = "<a href=\"bankaccount?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Bank Account</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Create Bank Account";
        }
        request.setAttribute("memberGroupId", memberGroupId);
        request.setAttribute("clientId", clientId);
        request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("providerId", providerId);
		request.setAttribute("refId", refId);
		request.setAttribute("index", index);
		request.setAttribute("searchby", searchby);
		request.setAttribute("tipe", tipe);
		
		return new ModelAndView(new RedirectView(bankAccountForm
				.getPreviousURL()
				+ "&alert=" + alertMsg));


		
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
