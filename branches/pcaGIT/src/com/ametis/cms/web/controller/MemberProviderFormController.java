package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProvider;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberProviderForm;

// imports+

// imports-

/**
 * MemberProvider is a mapping of member_provider Table.
 */
public class MemberProviderFormController extends SimpleFormController
// extends+

// extends-

{

	MemberProviderService memberProviderService;
	private PolicyService policyService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	// foreign affairs

	MemberService memberService;

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

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

	public void setMemberProviderService(MemberProviderService object) {
		this.memberProviderService = object;
	}

	public MemberProviderService getMemberProviderService() {
		return this.memberProviderService;
	}

	// generate by default
	private UserService actionuserService;

	public UserService getnUserService() {
		return actionuserService;
	}

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
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

	public MemberProviderFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		MemberProviderForm tmp = null;
		Integer memberProviderId = WebUtil.getParameterInteger(request,
				"memberProviderId");

		if (memberProviderId != null) {
			java.io.Serializable pkey = memberProviderId;
			MemberProvider object = memberProviderService.get(pkey);

			if (object != null) { // edit object

				tmp = new MemberProviderForm(object);
				// foreign affairs

				tmp.setMemberId("" + object.getMemberId().getMemberId());

				tmp.setProviderId("" + object.getProviderId().getProviderId());

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberProviderForm();
				// foreign affairs

				Integer memberId = WebUtil.getParameterInteger(request,
						"memberId");

				if (memberId != null) {
					Member forClass = new Member();
					forClass.setMemberId(memberId);
					tmp.setMemberId("" + memberId);

					Member member = this.memberService.get(memberId);
					tmp.getMemberProvider().setMemberId(member);
				} else {
					
				}

				Integer providerId = WebUtil.getParameterInteger(request,
						"providerId");

				if (providerId != null) {
					Provider forClass = new Provider();
					forClass.setProviderId(providerId);
					tmp.setProviderId("" + providerId);

					Provider provider = this.providerService.get(providerId);
					tmp.getMemberProvider().setProviderId(provider);
				} else {
					
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberProviderForm();
			// foreign affairs

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			if (memberId != null) {
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId("" + memberId);

				Member member = this.memberService.get(memberId);
				tmp.getMemberProvider().setMemberId(member);
			} else {
				
			}

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			if (providerId != null) {
				Provider forClass = new Provider();
				forClass.setProviderId(providerId);
				tmp.setProviderId("" + providerId);

				Provider provider = this.providerService.get(providerId);
				tmp.getMemberProvider().setProviderId(provider);
			} else {
				
			}

			// -- foreign affairs end

		}
		
		String breadcrumb = "";
		
		breadcrumb = "<a href=\"memberprovider-upload\" class=\"linkbreadcrumb\">Register Upload Member Provider</a>";
		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		MemberProviderForm memberProviderForm = (MemberProviderForm) command;
		MemberProvider memberProvider = memberProviderForm.getMemberProvider();

		// errors.setNestedPath("memberProvider");
		// getValidator().validate(memberProvider, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		try {
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			if (memberId != null){
				Member member = memberService.get(memberId);
				
				if (member != null) {
					model.put("memberNumber", member.getCustomerPolicyNumber());
					model.put("memberId", memberId);
	
					String[] eqParam = { "memberGroupId.memberGroupId",
							"deletedStatus", "policyStatus" };
					Object[] eqValue = {
							member.getMemberGroupId().getMemberGroupId(),
							Integer.valueOf(0), 1 };
					Collection<Policy> policyList = policyService.search(null,
							null, eqParam, eqValue, 0, 1);
	
					if (policyList != null) {
						Iterator<Policy> iterator = policyList.iterator();
						if (iterator.hasNext()) {
							Policy policy = iterator.next();
	
							if (policy != null) {
								model.put("policyId", policy.getPolicyId());
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		MemberProviderForm memberProviderForm = (MemberProviderForm) command;

		String navigation = WebUtil.getParameterString(request, "navigation",
				"");
		MemberProvider res = null;
		String alertMsg = "";
		try {
		
			

			ActionUser user = securityService.getActionUser(request);

			if (memberProviderForm.isNewMemberProvider()) {
				if (navigation.equalsIgnoreCase("upload")) {

					res = memberProviderService.createUpload(memberProviderForm
							.getMemberProvider(), memberProviderForm
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
					res = memberProviderService.create(memberProviderForm
							.getMemberProvider(), user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.memberprovider", null,
								"success", Locale.getDefault());
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.memberprovider", null, "fail",
								Locale.getDefault());
					}
				}
			} else {
				res = memberProviderService.update(memberProviderForm
						.getMemberProvider(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.memberprovider", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.memberprovider", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (memberProviderForm.isNewMemberProvider()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.memberprovider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.memberprovider", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("memberprovider" + "?alert="
				+ alertMsg + "&memberId=" + memberProviderForm.getMemberId()));
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
