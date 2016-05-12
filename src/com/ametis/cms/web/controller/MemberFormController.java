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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.Relationship;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProductTypeService;
import com.ametis.cms.service.RelationshipService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberForm;

// imports+ 

// imports- 

/**
 * Member is a mapping of member Table.
 */
public class MemberFormController extends SimpleFormController
// extends+

// extends-

{

	MemberService memberService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	ProductTypeService productTypeService;
	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setProductTypeService(ProductTypeService obj) {
		this.productTypeService = obj;
	}

	public ProductTypeService getProductTypeService() {
		return this.productTypeService;
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

	RelationshipService relationshipService;

	public void setRelationshipService(RelationshipService obj) {
		this.relationshipService = obj;
	}

	public RelationshipService getRelationshipService() {
		return this.relationshipService;
	}

	// -- foreign affairs end

	public void setMemberService(MemberService object) {
		this.memberService = object;
	}

	public MemberService getMemberService() {
		return this.memberService;
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

	public MemberFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		MemberForm tmp = null;
		Integer memberId = WebUtil.getParameterInteger(request, "memberId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (memberId != null) {
			java.io.Serializable pkey = memberId;
			Member object = memberService.get(pkey);

			if (object != null) { // edit object

				tmp = new MemberForm(object);
				// foreign affairs

				if (object.getMemberType() != null) {
					tmp.setMemberType(""
							+ object.getMemberType().getProductTypeId());
				}

				if (object.getClientId() != null) {
					tmp.setClientId(object.getClientId().getClientId()
							.toString());
				}

				if (object.getMemberGroupId() != null) {

					tmp.setMemberGroupId(object.getMemberGroupId()
							.getMemberGroupId().toString());
				}

				if (object.getRelationshipId() != null) {
					tmp.setRelationshipId(""
							+ object.getRelationshipId().getRelationshipId());
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new MemberForm();
				// foreign affairs

				Integer memberType = WebUtil.getParameterInteger(request,
						"memberType");

				if (memberType != null) {
					ProductType forClass = new ProductType();
					forClass.setProductTypeId(memberType);
					tmp.setMemberType("" + memberType);

					ProductType productType = this.productTypeService
							.get(memberType);
					tmp.getMember().setMemberType(productType);
				}

				Integer clientId = WebUtil.getParameterInteger(request,
						"clientId");

				if (clientId != null) {

					Client client = this.clientService.get(clientId);
					tmp.getMember().setClientId(client);
				}

				Integer memberGroupId = WebUtil.getParameterInteger(request,
						"memberGroupId");

				if (memberGroupId != null) {

					MemberGroup memberGroup = this.memberGroupService
							.get(memberGroupId);
					tmp.getMember().setMemberGroupId(memberGroup);
				}

				Integer relationshipId = WebUtil.getParameterInteger(request,
						"relationshipId");

				if (relationshipId != null) {
					Relationship forClass = new Relationship();
					forClass.setRelationshipId(relationshipId);
					tmp.setRelationshipId("" + relationshipId);

					Relationship relationship = this.relationshipService
							.get(relationshipId);
					tmp.getMember().setRelationshipId(relationship);
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new MemberForm();
			// foreign affairs

			Integer memberType = WebUtil.getParameterInteger(request,
					"memberType");

			if (memberType != null) {
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(memberType);
				tmp.setMemberType("" + memberType);

				ProductType productType = this.productTypeService
						.get(memberType);
				tmp.getMember().setMemberType(productType);
			}

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			if (clientId != null) {

				Client client = this.clientService.get(clientId);
				tmp.getMember().setClientId(client);
			}

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			if (memberGroupId != null) {

				MemberGroup memberGroup = this.memberGroupService
						.get(memberGroupId);
				tmp.getMember().setMemberGroupId(memberGroup);
			}

			Integer relationshipId = WebUtil.getParameterInteger(request,
					"relationshipId");

			if (relationshipId != null) {
				Relationship forClass = new Relationship();
				forClass.setRelationshipId(relationshipId);
				tmp.setRelationshipId("" + relationshipId);

				Relationship relationship = this.relationshipService
						.get(relationshipId);
				tmp.getMember().setRelationshipId(relationship);
			}

			// -- foreign affairs end

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		MemberForm memberForm = (MemberForm) command;
		Member member = memberForm.getMember();

		// errors.setNestedPath("member");
		// getValidator().validate(member, errors);
		// errors.setNestedPath("");
		System.out.println(errors);
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
		String navigation = WebUtil.getParameterString(request, "navigation","");
		
		model.put("navigation", navigation);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		MemberForm memberForm = (MemberForm) command;

		Member res = null;
		Member member = null;
		String alertMsg = "";
		String memberId = "";
		try {
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (memberForm.isNewMember()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEMEMBER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEMEMBER access");
					return errorResult;

				}
				member = memberForm.getMember();

				if (memberForm.getMember() != null) {
					// memberForm.getMember().setMemberType(new
					// ProductType(ProductType.FULL_ADMINISTRATION));
				}

				if (memberForm.getRelationshipId() == null) {
					member.setRelationshipId(null);
				} else if (memberForm.getRelationshipId() != null
						&& memberForm.getRelationshipId().equals("")) {
					member.setRelationshipId(null);
				}
				System.out.println(member.getMemberGroupId());

				res = memberService.create(member, memberForm
						.getCurrentProductCode(), user);

				if (res != null) {
					memberId = res.getMemberId().toString();
					alertMsg = alertProperties.getMessage(
							"success.create.member", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.member",
							null, "fail", Locale.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEMEMBER");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEMEMBER access");
					return errorResult;

				}
				res = memberService.update(memberForm.getMember(), user);

				if (res != null) {
					memberId = res.getMemberId().toString();
					alertMsg = alertProperties.getMessage(
							"success.update.member", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.member",
							null, "fail", Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (memberForm.isNewMember()) {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.create.member", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.update.member", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("member" + "?navigation=detail&memberId="+memberId+"&alert="
				+ alertMsg));
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

		// Untuk ngubah semua string yang di-bind dengan tipe integer
		CustomNumberEditor inte = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, inte);
	}
	// class+

	// class-

}
