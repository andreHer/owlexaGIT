package com.ametis.cms.web.controller;

import java.text.NumberFormat;
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
import com.ametis.cms.datamodel.BusinessCategory;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BusinessCategoryService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberGroupForm;

// imports+ 

// imports- 

/**
 * MemberGroup is a mapping of member_group Table.
 */
public class MemberGroupFormController extends SimpleFormController
// extends+

// extends-

{

	MemberGroupService memberGroupService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs
	private ActivityLogService logService;
	private BusinessCategoryService businessCategoryService;

	
	public BusinessCategoryService getBusinessCategoryService() {
		return businessCategoryService;
	}

	public void setBusinessCategoryService(
			BusinessCategoryService businessCategoryService) {
		this.businessCategoryService = businessCategoryService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	ClientService clientService;
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setClientService(ClientService obj) {
		this.clientService = obj;
	}

	public ClientService getClientService() {
		return this.clientService;
	}

	// -- foreign affairs end

	public void setMemberGroupService(MemberGroupService object) {
		this.memberGroupService = object;
	}

	public MemberGroupService getMemberGroupService() {
		return this.memberGroupService;
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

	public MemberGroupFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		MemberGroupForm tmp = null;
		Integer memberGroupId = WebUtil.getParameterInteger(request,
				"memberGroupId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (memberGroupId != null) {
			java.io.Serializable pkey = memberGroupId;
			MemberGroup object = memberGroupService.get(pkey);

			if (object != null) { // edit object

				tmp = new MemberGroupForm(object);
				// foreign affairs

				if (object.getClientId() != null){
				
					Client client = clientService.get(object.getClientId().getClientId());
					tmp.setClientId("" + object.getClientId().getClientId());
					tmp.setClientName(client.getClientName());
				}
				

				String breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Group";
				request.setAttribute("breadcrumb", breadcrumb);


				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberGroupForm();
				// foreign affairs

				Integer clientId = WebUtil.getParameterInteger(request,
						"clientId");

				if (clientId != null) {
					Client forClass = new Client();
					forClass.setClientId(clientId);
					tmp.setClientId("" + clientId);

					Client client = this.clientService.get(clientId);
					tmp.getMemberGroup().setClientId(client);
				} else {
					
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberGroupForm();
			// foreign affairs

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			if (clientId != null) {
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId("" + clientId);

				Client client = this.clientService.get(clientId);
				tmp.getMemberGroup().setClientId(client);
			} else {
				
			}

			// -- foreign affairs end
			String breadcrumb = "<a href=\"membergroup-form\" class=\"linkbreadcrumb\">Register Member Group</a>";
			request.setAttribute("breadcrumb", breadcrumb);

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		MemberGroupForm memberGroupForm = (MemberGroupForm) command;
		MemberGroup memberGroup = memberGroupForm.getMemberGroup();

		errors.printStackTrace();
		// errors.setNestedPath("memberGroup");
		// getValidator().validate(memberGroup, errors);
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
			Collection<BusinessCategory> businessCategoryList = businessCategoryService.getAll();
			
			model.put("businessCategories", businessCategoryList);
			
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			model.put("navigation", navigation);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		MemberGroupForm memberGroupForm = (MemberGroupForm) command;

		MemberGroup res = null;
		String alertMsg = "";
		String redirectURL = "membergroup";
		try {
			

			if (memberGroupForm.isNewMemberGroup()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEMEMBERGROUP");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATEMEMBERGROUP access");
					return errorResult;

				}

				if (memberGroupForm.getMemberGroup() != null) {
					memberGroupForm.getMemberGroup().setStatus(
							new SubscriptionStatus(SubscriptionStatus.PENDING));
				}
				res = memberGroupService.create(memberGroupForm
						.getMemberGroup(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.membergroup", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.membergroup", null, "fail", Locale
									.getDefault());
				}
				redirectURL = "membergroup?navigation=detail&memberGroupId="+res.getMemberGroupId()+"&alert="+alertMsg;
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEMEMBERGROUP");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATEMEMBERGROUP access");
					return errorResult;

				}
				res = memberGroupService.update(memberGroupForm
						.getMemberGroup(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.membergroup", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.membergroup", null, "fail", Locale
									.getDefault());
				}
				redirectURL = "membergroup?navigation=detail&memberGroupId="+res.getMemberGroupId()+"&alert="+alertMsg;

			}
		} catch (Exception ex) {
			if (memberGroupForm.isNewMemberGroup()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.membergroup", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.membergroup", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(redirectURL ));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);

		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		CustomNumberEditor num = new CustomNumberEditor(java.lang.Double.class,
				nf, true);
		binder.registerCustomEditor(java.lang.Double.class, num);
	}
	// class+

	// class-

}
