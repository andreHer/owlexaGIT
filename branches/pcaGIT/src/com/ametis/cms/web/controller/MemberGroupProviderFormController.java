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
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberGroupProvider;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberGroupProviderForm;

// imports+ 

// imports- 

/**
 * MemberGroupProvider is a mapping of member_group_provider Table.
 */
public class MemberGroupProviderFormController extends SimpleFormController
// extends+

// extends-

{

	MemberGroupProviderService memberGroupProviderService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	MemberGroupService memberGroupService;
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setMemberGroupService(MemberGroupService obj) {
		this.memberGroupService = obj;
	}

	public MemberGroupService getMemberGroupService() {
		return this.memberGroupService;
	}

	ProviderService providerService;

	public void setProviderService(ProviderService obj) {
		this.providerService = obj;
	}

	public ProviderService getProviderService() {
		return this.providerService;
	}

	// -- foreign affairs end

	public void setMemberGroupProviderService(MemberGroupProviderService object) {
		this.memberGroupProviderService = object;
	}

	public MemberGroupProviderService getMemberGroupProviderService() {
		return this.memberGroupProviderService;
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

	public MemberGroupProviderFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		MemberGroupProviderForm tmp = null;
		Integer memberGroupProviderId = WebUtil.getParameterInteger(request,
				"memberGroupProviderId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (memberGroupProviderId != null) {
			java.io.Serializable pkey = memberGroupProviderId;
			MemberGroupProvider object = memberGroupProviderService.get(pkey);

			if (object != null) { // edit object

				tmp = new MemberGroupProviderForm(object);
				// foreign affairs

				tmp.setMemberGroupId(""
						+ object.getMemberGroupId().getMemberGroupId());

				tmp.setProviderId("" + object.getProviderId().getProviderId());

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberGroupProviderForm();
				// foreign affairs

				Integer memberGroupId = WebUtil.getParameterInteger(request,
						"memberGroupId");

				if (memberGroupId != null) {
					MemberGroup forClass = new MemberGroup();
					forClass.setMemberGroupId(memberGroupId);
					tmp.setMemberGroupId("" + memberGroupId);

					MemberGroup memberGroup = this.memberGroupService
							.get(memberGroupId);
					tmp.getMemberGroupProvider().setMemberGroupId(memberGroup);
				} else {
					
				}

				Integer providerId = WebUtil.getParameterInteger(request,
						"providerId");

				if (providerId != null) {
					Provider forClass = new Provider();
					forClass.setProviderId(providerId);
					tmp.setProviderId("" + providerId);

					Provider provider = this.providerService.get(providerId);
					tmp.getMemberGroupProvider().setProviderId(provider);
				} else {
					
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberGroupProviderForm();
			// foreign affairs

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			if (memberGroupId != null) {
				MemberGroup forClass = new MemberGroup();
				forClass.setMemberGroupId(memberGroupId);
				tmp.setMemberGroupId("" + memberGroupId);

				MemberGroup memberGroup = this.memberGroupService
						.get(memberGroupId);
				tmp.getMemberGroupProvider().setMemberGroupId(memberGroup);
			} else {
				
			}

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			if (providerId != null) {
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId("" + providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getMemberGroupProvider().setProviderId(provider);
			} else {
				
			}

			// -- foreign affairs end

		}
		
		String breadcrumb = "";
		
		breadcrumb = "<a href=\"membergroupprovider-upload\" class=\"linkbreadcrumb\">Register Upload Member Group Provider</a>";
		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		MemberGroupProviderForm memberGroupProviderForm = (MemberGroupProviderForm) command;
		MemberGroupProvider memberGroupProvider = memberGroupProviderForm
				.getMemberGroupProvider();

		errors.printStackTrace();
		// errors.setNestedPath("memberGroupProvider");
		// getValidator().validate(memberGroupProvider, errors);
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

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		MemberGroupProviderForm memberGroupProviderForm = (MemberGroupProviderForm) command;

		MemberGroupProvider res = null;
		String alertMsg = "";
		String navigation = WebUtil.getParameterString(request, "navigation",
				"");
		try {
			

			if (memberGroupProviderForm.isNewMemberGroupProvider()) {
				ActionUser user = securityService.getActionUser(request);
				if (navigation.equalsIgnoreCase("upload")) {

					res = memberGroupProviderService.createUpload(memberGroupProviderForm
							.getMemberGroupProvider(), memberGroupProviderForm
							.getMultipartFile(), user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.providerdoctor", null,
								"success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.providerdoctor", null, "fail",
								Locale.getDefault());
					}
				} else {
					boolean isUserAuthorized = securityService
							.isUserAuthorized(user, "CREATEGROUPPROVIDER");

					if (!isUserAuthorized) {

						ModelAndView errorResult = new ModelAndView(
								new RedirectView("errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult
								.addObject("errorMessage",
										"You Are Not Authorized for CREATEGROUPPROVIDER access");
						return errorResult;

					}
					res = memberGroupProviderService.create(
							memberGroupProviderForm.getMemberGroupProvider(),
							user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.membergroupprovider", null,
								"success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.membergroupprovider", null,
								"fail", Locale.getDefault());
					}
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEGROUPPROVIDER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATEGROUPPROVIDER access");
					return errorResult;

				}
				res = memberGroupProviderService.update(memberGroupProviderForm
						.getMemberGroupProvider(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.membergroupprovider", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.membergroupprovider", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (memberGroupProviderForm.isNewMemberGroupProvider()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.membergroupprovider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.membergroupprovider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("membergroupprovider"
				+ "?alert=" + alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
	}
	// class+

	// class-

}
