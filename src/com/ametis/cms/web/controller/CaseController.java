package com.ametis.cms.web.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseItem;
import com.ametis.cms.datamodel.CaseMedicine;
import com.ametis.cms.datamodel.CaseProcedure;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderTypeDiagnosisTreatment;
import com.ametis.cms.datamodel.Role;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseHistoryService;
import com.ametis.cms.service.CaseInvestigationService;
import com.ametis.cms.service.CaseItemService;
import com.ametis.cms.service.CaseMedicineService;
import com.ametis.cms.service.CaseProcedureService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DiagnosisSetService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberDiagnosisExclusionService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderServiceService;
import com.ametis.cms.service.ProviderTypeDiagnosisTreatmentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.CaseReportDownloader;
import com.ametis.cms.util.CaseReportGenerator;
import com.ametis.cms.util.TimeUtils;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Case is a servlet controller for case Table. All you have to do is to convert
 * necessary data field to the named parameter
 */
public class CaseController implements Controller

// extends+

// extends-

{

	private CaseService myCaseService;
	private ProviderTypeDiagnosisTreatmentService inaCBGService;
	private MemberGroupService memberGroupService;

	private UserService userService;

	private MemberService memberService;

	private ProviderService providerService;
	private ProviderServiceService providerServiceService;

	private PolicyService policyService;

	private MemberProductService memberProductService;
	private MemberDiagnosisExclusionService memberDiagnosisExclusionService;
	private DiagnosisSetService diagnosisSetService;

	private SecurityService securityService;

	private ResourceBundleMessageSource alertProperties;

	private CaseProcedureService caseProcedureService;
	private CaseItemService caseItemService;
	private CaseMedicineService caseMedicineService;
	private MemberBenefitService memberBenefitService;
	private MemberClausulService memberClausulService;

	private CaseInvestigationService caseInvestigationService;

	private Integer countSet;

	private Integer maxPercountSet;

	private ConfigurationService configurationService;

	private ClaimService claimService;
	private ClaimItemService claimItemService;
	private ActivityLogService logService;

	private CaseHistoryService caseHistoryService;

	// Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}

	public void setMemberClausulService(
			MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
	}

	public DiagnosisSetService getDiagnosisSetService() {
		return diagnosisSetService;
	}

	public void setDiagnosisSetService(DiagnosisSetService diagnosisSetService) {
		this.diagnosisSetService = diagnosisSetService;
	}

	public MemberDiagnosisExclusionService getMemberDiagnosisExclusionService() {
		return memberDiagnosisExclusionService;
	}

	public void setMemberDiagnosisExclusionService(
			MemberDiagnosisExclusionService memberDiagnosisExclusionService) {
		this.memberDiagnosisExclusionService = memberDiagnosisExclusionService;
	}

	public ProviderServiceService getProviderServiceService() {
		return providerServiceService;
	}

	public void setProviderServiceService(
			ProviderServiceService providerServiceService) {
		this.providerServiceService = providerServiceService;
	}

	public CaseProcedureService getCaseProcedureService() {
		return caseProcedureService;
	}

	public void setCaseProcedureService(
			CaseProcedureService caseProcedureService) {
		this.caseProcedureService = caseProcedureService;
	}

	public CaseItemService getCaseItemService() {
		return caseItemService;
	}

	public void setCaseItemService(CaseItemService caseItemService) {
		this.caseItemService = caseItemService;
	}

	public CaseMedicineService getCaseMedicineService() {
		return caseMedicineService;
	}

	public void setCaseMedicineService(CaseMedicineService caseMedicineService) {
		this.caseMedicineService = caseMedicineService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(
			MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public ProviderTypeDiagnosisTreatmentService getInaCBGService() {
		return inaCBGService;
	}

	public void setInaCBGService(
			ProviderTypeDiagnosisTreatmentService inaCBGService) {
		this.inaCBGService = inaCBGService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(
			MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public CaseService getMyCaseService() {
		return myCaseService;
	}

	public void setMyCaseService(CaseService myCaseService) {
		this.myCaseService = myCaseService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}

	public Integer getCountSet() {
		return this.countSet;
	}

	public void setMaxPercountSet(Integer maxCount) {
		this.maxPercountSet = maxCount;
	}

	public Integer getMaxPercountSet() {
		return this.maxPercountSet;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setCaseService(CaseService myCaseService) {
		this.myCaseService = myCaseService;
	}

	public CaseService getCaseService() {
		return this.myCaseService;
	}

	public CaseInvestigationService getCaseInvestigationService() {
		return caseInvestigationService;
	}

	public void setCaseInvestigationService(
			CaseInvestigationService caseInvestigationService) {
		this.caseInvestigationService = caseInvestigationService;
	}

	public CaseHistoryService getCaseHistoryService() {
		return caseHistoryService;
	}

	public void setCaseHistoryService(CaseHistoryService caseHistoryService) {
		this.caseHistoryService = caseHistoryService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = caseId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETECASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETECASE access");
				return errorResult;

			}
			Case res = myCaseService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.case", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.case", null, "fail", Locale.getDefault()));

			}
			result = searchPerformed(request, response, "searchCase");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView voidPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			String reason = WebUtil.getParameterString(request, "reason", "");
			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"VOIDCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for VOIDCASE access");
				return errorResult;

			}
			if (navigation.equalsIgnoreCase("void")) {

				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ParentMember",
						"Case.MemberId.MemberGroupId", "Case.ClaimId",
						"Case.Diagnosis2Id", "Case.Diagnosis3Id" };

				Case object = myCaseService.get(caseId, requiredCase);
				result = new ModelAndView("voidCase");
				result.addObject("myCase", object);
			} else if (navigation.equalsIgnoreCase("dovoid")) {

				boolean res = myCaseService.voidCase(caseId, reason, user);
				String alert = "";

				if (res) {
					alert = alertProperties.getMessage("success.void.case",
							null, "success", Locale.getDefault());
				} else {
					alert = alertProperties.getMessage("fail.void.case", null,
							"fail", Locale.getDefault());

				}
				result = new ModelAndView(new RedirectView(
						"case?navigation=detail&caseId=" + caseId + "&alert="
								+ alert));
			}
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView cancelDischargePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			String reason = WebUtil.getParameterString(request, "reason", "");
			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CANCELDISCHARGE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for CANCELDISCHARGE access");
				return errorResult;

			}
			if (navigation.equalsIgnoreCase("canceldischarge")) {

				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ParentMember",
						"Case.MemberId.MemberGroupId", "Case.ClaimId",
						"Case.Diagnosis2Id", "Case.Diagnosis3Id" };

				Case object = myCaseService.get(caseId, requiredCase);
				result = new ModelAndView("cancelDischargeCase");
				result.addObject("myCase", object);
			} else if (navigation.equalsIgnoreCase("docanceldischarge")) {

				boolean res = myCaseService.cancelDischargeCase(caseId, reason,
						user);
				String alert = "";

				if (res) {
					alert = alertProperties.getMessage("success.void.case",
							null, "success", Locale.getDefault());
				} else {
					alert = alertProperties.getMessage("fail.void.case", null,
							"fail", Locale.getDefault());

				}
				result = new ModelAndView(new RedirectView(
						"case?navigation=detail&caseId=" + caseId + "&alert="
								+ alert));
			}
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView rejectPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = caseId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"REJECTCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for REJECTCASE access");
				return errorResult;

			}

			boolean res = false;// myCaseService.reject(pkey,user);

			if (res) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.case", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.case", null, "fail", Locale.getDefault()));

			}
			request.setAttribute("caseId", caseId);
			result = detailPerformed(request, response, "detailCase");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView terminatePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			java.io.Serializable pkey = caseId;
			java.sql.Timestamp closingTime = new java.sql.Timestamp(
					System.currentTimeMillis());

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"TERMINATECASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for TERMINATECASE access");
				return errorResult;

			}
			boolean res = myCaseService.close(pkey, closingTime, user);

			request.setAttribute("caseId", caseId);
			result = detailPerformed(request, response, "detailCase");

			if (res) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.close.case", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.close.case", null, "fail", Locale.getDefault()));

			}

		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView referCasePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			java.io.Serializable pkey = caseId;
			java.sql.Timestamp closingTime = new java.sql.Timestamp(
					System.currentTimeMillis());

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"REFERCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for REFERCASE access");
				return errorResult;

			}
			Case theCase = myCaseService.get(caseId);

			result = new ModelAndView(new RedirectView(
					"case-form?caseType=2&memberId="
							+ theCase.getMemberId().getMemberId().intValue()
							+ "&refCaseId=" + caseId));

		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView approvePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			java.io.Serializable pkey = caseId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"APPROVECASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for APPROVECASE access");
				return errorResult;

			}

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			if (claimId != null) {
				java.io.Serializable claimPkey = claimId;
				Claim claim = claimService.get(claimPkey);
				Case tmpCase = myCaseService.get(pkey);
				tmpCase.setClaimId(claim);

				// Add 20150811 by FVO, for complication
				if (claim.getHasComplication() != null) {
					tmpCase.setHasComplication(claim.getHasComplication());
				}

				myCaseService.update(tmpCase, user);
			}

			boolean res = myCaseService.approve(pkey, user);

			if (res) {
				// Add 20150409, untuk redirect ke claim setelah approve
				// Masih dipikir2 dlu.
				// String redirectUrl =
				// "claim-form?navigation=approvecase&caseId="+caseId;
				// return new ModelAndView(new RedirectView(redirectUrl));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.case", null, "fail", Locale.getDefault()));

			}
			request.setAttribute("caseId", caseId);
			result = detailPerformed(request, response, "detailCase");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView lookupPendingJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			result = new ModelAndView("jsonTotalPendingCase");

			User currentUser = securityService.getCurrentUser(request);

			int total = 0;
			if (currentUser != null) {

				String[] eqParam = { "deletedStatus", "status",
						"caseStatusId.caseStatusId" };
				Object[] eqValue = { Integer.valueOf(0), Integer.valueOf(0),
						Case.CASE_OPEN };
				total = myCaseService.getTotal(null, null, eqParam, eqValue);

			}
			result.addObject("result", total);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView lookupClaimJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			result = new ModelAndView("lookupCaseJson");

			String q = WebUtil.getParameterString(request, "q", "");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			User currentUser = securityService.getCurrentUser(request);

			Collection<Case> caseList = new Vector<Case>();

			if (currentUser != null) {

				String[] likeParam = { "caseNumber" };
				Object[] likeValue = { q };
				String[] eqParam = { "deletedStatus", "memberId.memberId",
						"providerId.providerId", "isClaimed" };
				Object[] eqValue = { Integer.valueOf(0), memberId, providerId,
						Integer.valueOf(0) };

				caseList = myCaseService.search(likeParam, likeValue, eqParam,
						eqValue, 0, 10);

			}
			result.addObject("Cases", caseList);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String navigation = request.getParameter("navigation");

			java.io.Serializable pkey = caseId;
			// modified by aju on 20150329, add required/key Case->CaseStatus :D
			// modified again by aju on 20150330, add required for Case->Client,
			// Case->Group
			String[] requiredCase = { "Case.MemberId",
					"Case.MemberId.ParentMember",
					"Case.MemberId.MemberGroupId", "Case.ClaimId",
					"Case.Diagnosis2Id", "Case.Diagnosis3Id",
					"Case.CaseStatusId", "Case.MemberId.ClientId",
					"Case.PoliklinikId", "Case.MemberId.MemberGroupId",
					"Case.MemberId.CurrentPolicyId",
					"Case.ClaimId.ClaimStatus", "Case.ProviderId",
					"Case.ProviderId.ProviderCategoryId","Case.MemberProductId" };

			Case object = myCaseService.get(pkey, requiredCase);

			if (user != null) {
				if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					if (object.getMemberId().getCurrentPolicyId()
							.getPolicyType().intValue() == Policy.MANAGED_CARE_POLICY) {
						location = "detailCaseManagedCare";
					} else if (object.getMemberId().getCurrentPolicyId()
							.getPolicyType().intValue() == Policy.INDEMNITY_POLICY) {
						location = "detailCase";
					}
				}
			}

			result = new ModelAndView(location);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.case", null, "fail", Locale.getDefault()));
			} else {
				String[] required = { "Member.ClientId",
						"Member.MemberGroupId", "Member.RelationshipId",
						"Member.ParentMember" };
				Member member = memberService.get(object.getMemberId()
						.getMemberId(), required);
				result.addObject("member", member);
				
				MemberProduct product = object.getMemberProductId();

				if (product != null) {
					result.addObject("benefit", "IP "
							+ product.getProductId().getServiceClass());
				}
				else {
					product = memberProductService
					.getMemberActiveProduct(object.getMemberId()
							.getMemberId(), CaseCategory.INPATIENT);
					
					if (product != null){
						result.addObject("benefit", "IP "
								+ product.getProductId().getServiceClass());
					}
					
				}
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String tanggal = df.format(object.getCaseStartTime());
				result.addObject("tanggal", tanggal);

				if (object.getDiagnosis1Id() != null) {

					int risk = 1;

					if (object.getDiagnosis2Id() != null) {
						risk = 2;

						if (object.getDiagnosis3Id() != null) {
							risk = 3;
						}
					}

					String[] eqParam = { "diagnosisId.diagnosisId",
							"deletedStatus", "treatmentRiskId.treatmentRiskId",
							"providerTypeId.providerTypeId" };
					Object[] eqValue = {
							object.getDiagnosis1Id().getDiagnosisId(),
							Integer.valueOf(0), Integer.valueOf(risk),
							Integer.valueOf(1) };

					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = inaCBGService
							.search(null, null, eqParam, eqValue, 0, 1);

					if (inaCBGList != null && inaCBGList.size() > 0) {
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList
								.iterator();

						if (iterator.hasNext()) {
							ProviderTypeDiagnosisTreatment inaCBG = iterator
									.next();

							result.addObject("inaCbgRef", inaCBG);
						}
					}
				}
			}

			result.addObject("myCase", object);

			// procedure, medicine, benefit

			if (user.getUser().getUserType().intValue() == User.PROVIDER) {
				String[] eqParam = { "deletedStatus", "caseId.caseId" };
				Object[] eqValue = { 0, object.getCaseId() };

				Collection<CaseProcedure> caseProcedureList = caseProcedureService
						.search(null, null, eqParam, eqValue, 0, 10);
				Collection<CaseMedicine> caseMedicineList = caseMedicineService
						.search(null, null, eqParam, eqValue, 0, 15);
				Collection<CaseItem> caseItemList = caseItemService.search(
						null, null, eqParam, eqValue, 0, 15);

				Provider provider = user.getUser().getProviderId();

				String[] requiredP = { "Provider.ProviderCategoryId" };
				provider = providerService.get(provider.getProviderId(),
						requiredP);

				result.addObject("caseProcedureList", caseProcedureList);
				result.addObject("caseMedicineList", caseMedicineList);
				result.addObject("caseItemList", caseItemList);

			}
			if (object != null) {

				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

				String birthday = "";
				String caseDate = "";
				String tanggalSurat = "";

				birthday = format.format(object.getMemberId().getBirthday());
				caseDate = format.format(object.getCaseStartTime());
				tanggalSurat = format.format(object.getCaseStartTime());

				result.addObject("caseDate", caseDate);
				result.addObject("birthdate", birthday);

				// check disability

				MemberProduct activeProduct = object.getMemberProductId();
				
				if (activeProduct == null){
					activeProduct = memberProductService
							.getMemberActiveProduct(object.getMemberId()
									.getMemberId(), object.getCaseCategoryId()
									.getCaseCategoryId());
				}
				
				if (activeProduct != null) {
					int disabilityLength = activeProduct.getDisabilityLength() == null ? 60
							: activeProduct.getDisabilityLength();

					DateTime dtAdmision = new DateTime(object
							.getCaseStartTime().getTime()).minusDays(1);

					DateTime lastDate = dtAdmision.minusDays(disabilityLength);

					Date minDate = new Date(lastDate.getMillis());
					Date maxDate = new Date(dtAdmision.getMillis()); //

					Integer diagnosisId = null;
					Integer caseCategoryId = object.getCaseCategoryId()
							.getCaseCategoryId();

					if (object.getDiagnosis1Id() != null) {
						diagnosisId = object.getDiagnosis1Id().getDiagnosisId();
						int totalClaim = claimService
								.getClaimDisabilityDiagnosis(diagnosisId,
										caseCategoryId, object.getMemberId()
												.getMemberId(), minDate,
										maxDate);

						if (totalClaim > 0) {
							result.addObject("diagnosis1Status", "disability");
						} else {
							result.addObject("diagnosis1Status", "nodisability");
						}

						boolean diag1exclude = memberDiagnosisExclusionService
								.isExcludedDiagnosis(diagnosisId, object
										.getMemberId().getMemberId());
						if (diag1exclude) {
							result.addObject("diag1exstat", "exclusion");
						}
					}

					if (object.getDiagnosis2Id() != null) {
						boolean diag2Exclude = memberDiagnosisExclusionService
								.isExcludedDiagnosis(object.getDiagnosis2Id()
										.getDiagnosisId(), object.getMemberId()
										.getMemberId());

						if (diag2Exclude) {
							result.addObject("diag2exstat", "exclusion");
						}
					}
					if (object.getDiagnosis3Id() != null) {
						boolean diag3Exclude = memberDiagnosisExclusionService
								.isExcludedDiagnosis(object.getDiagnosis3Id()
										.getDiagnosisId(), object.getMemberId()
										.getMemberId());

						if (diag3Exclude) {
							result.addObject("diag3exstat", "exclusion");
						}
					}

					String[] eqParamBen = { "deletedStatus",
							"memberId.memberId",
							"productCaseCategoryId.caseCategoryId",
							"memberBenefitStatus" };
					Object[] eqValueBen = { 0,
							object.getMemberId().getMemberId(),
							object.getCaseCategoryId().getCaseCategoryId(),
							SubscriptionStatus.ACTIVE };

					int totalBen = memberBenefitService.getTotal(null, null,
							eqParamBen, eqValueBen);

					Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(object.getMemberId().getMemberId(),
							object.getCaseCategoryId().getCaseCategoryId(), activeProduct.getMemberProductId(),0);
					
					//TODO: check Layer if Multi Layer

					result.addObject("benefitList", memberBenefitList);

					if (navigation.equalsIgnoreCase("printIdemnityLetter")) {
						String[] eqParam = { "deletedStatus",
								"memberId.memberId",
								"productCaseCategoryId.caseCategoryId",
								"memberBenefitStatus",
								"itemCategoryId.itemCategoryId","memberProductId.memberProductId" };
						Object[] eqValue = { 0,
								object.getMemberId().getMemberId(),
								object.getCaseCategoryId().getCaseCategoryId(),
								SubscriptionStatus.ACTIVE, Integer.valueOf(1) ,activeProduct.getMemberProductId()};
						
						MemberBenefit roomBenefit = memberBenefitService
								.searchUnique(eqParam, eqValue);
						result.addObject("roomBenefit", roomBenefit);
					}
					if (navigation.equalsIgnoreCase("printAuthorizationLetter")
							|| navigation.equalsIgnoreCase("printGL")
							|| navigation
									.equalsIgnoreCase("printConfirmLetter")
							|| navigation.equalsIgnoreCase("printMedicalForm")) {
						
						MemberProduct product = object.getMemberProductId();
						
						if (product == null){
							product = memberProductService
									.getMemberActiveProduct(object.getMemberId()
											.getMemberId(), object
											.getCaseCategoryId()
											.getCaseCategoryId());
						}
						
						if (product != null) {
							if (product.getProductId().getServiceClass() != null) {
								result.addObject("roomBenefit", product
										.getProductId().getServiceClass());
							} else {
								result.addObject("roomBenefit", "");
							}
						}
						
						MemberBenefit icuBenefit = memberBenefitService
								.getActiveMemberBenefit(object.getMemberId()
										.getMemberId(), object
										.getCaseCategoryId()
										.getCaseCategoryId(), ItemCategory.ICU);
						
						result.addObject("icuBenefit", icuBenefit);
					}
				}

				// String required[] = new String[] {
				// // foreign affairs
				// "ClaimItem.ClaimId.ClaimTypeId", "ClaimItem.MemberBenefitId",
				// // foreign affairs end
				// };

				// Add 20140413 by FVO, for print Benefit Calculation Letter
				String type = request.getParameter("type");
				if (object.getClaimId() != null
						&& type == null
						&& !navigation
								.equalsIgnoreCase("printBenefitLetterFinalized")) {
					Collection<ClaimItem> claimItemList;
					String[] required = { "Claim.MemberId", "Claim.DiagnosisId" };
					Claim claim = claimService.get(object.getClaimId()
							.getClaimId(), required);
					claimItemList = claimItemService.getClaimItem(object
							.getClaimId().getClaimId());

					result.addObject("claimItemList", claimItemList);
					result.addObject("checkClaim", claim);
				} else if (navigation
						.equalsIgnoreCase("printBenefitLetterFinalized")
						&& type.equalsIgnoreCase("finalized")) { // Add
																	// 07042015,
																	// for
																	// setting
																	// Finalized
																	// Benefit
																	// Calculation
																	// Letter
					String[] eqParam = { "deletedStatus", "caseId.caseId" };
					Object[] eqValue = { 0, object.getCaseId() };
					Claim checkClaim = claimService.searchUnique(eqParam,
							eqValue, 0, 1);
					if (checkClaim != null) {
						Collection<ClaimItem> claimItemList = claimItemService
								.getClaimItemsForBenefitLetter(checkClaim
										.getClaimId());

						result.addObject("claimItemList", claimItemList);
						result.addObject("checkClaim", checkClaim);
					}
				} else if (navigation
						.equalsIgnoreCase("printBenefitLetterFinalized")
						&& type.equalsIgnoreCase("notfinalized")) { // Add
																	// 08042015,
																	// for
																	// setting
																	// Benefit
																	// Calculation
																	// Letter
					Collection<ClaimItem> claimItemList;
					String[] required = { "Claim.MemberId", "Claim.DiagnosisId" };
					Claim claim = claimService.get(object.getClaimId()
							.getClaimId(), required);
					claimItemList = claimItemService
							.getBenefitCheckCalculationForLetter(claim);

					result.addObject("claimItemList", claimItemList);
					result.addObject("checkClaim", claim);
					double totalApproved = 0;
					double totalExcess = 0;
					double totalCharge = 0;

					if (claimItemList != null) {
						Iterator<ClaimItem> checkedItemIterator = claimItemList
								.iterator();
						while (checkedItemIterator.hasNext()) {
							ClaimItem checkedItem = checkedItemIterator.next();

							if (checkedItem != null) {

								double covered = checkedItem
										.getClaimItemCoveredValue() == null ? 0
										: checkedItem
												.getClaimItemCoveredValue();
								totalApproved += covered;

								double excess = checkedItem.getExcessValue() == null ? 0
										: checkedItem.getExcessValue();
								totalExcess += excess;

								double charge = checkedItem.getClaimItemValue() == null ? 0
										: checkedItem.getClaimItemValue();
								totalCharge += charge;
							}
						}
					}
					Double totalApprovedDbl = Double.valueOf(totalApproved);
					Double totalExcessDbl = Double.valueOf(totalExcess);
					Double totalChargeDbl = Double.valueOf(totalCharge);
					result.addObject("totalApproved",
							totalApprovedDbl.longValue());
					result.addObject("totalExcess", totalExcessDbl.longValue());
					result.addObject("totalCharge", totalChargeDbl.longValue());
					/*
					 * ArrayList<CaseItem> caseItemsArray = new
					 * ArrayList<CaseItem>(); ArrayList<MemberBenefit>
					 * memberBenefits = new ArrayList<MemberBenefit>();
					 * ArrayList<Double> benefitLimit = new ArrayList<Double>();
					 * ArrayList<Double> chargeValue = new ArrayList<Double>();
					 * ArrayList<Double> approvedValue = new
					 * ArrayList<Double>();
					 * 
					 * Collection<CaseItem> caseItems =
					 * caseItemService.getCaseItem(caseId); Iterator<CaseItem>
					 * iteratorItems = caseItems.iterator(); BigDecimal
					 * totalUsageValue = BigDecimal.ZERO;
					 * while(iteratorItems.hasNext()){ CaseItem caseItem =
					 * iteratorItems.next(); caseItemsArray.add(caseItem);
					 * chargeValue.add(caseItem.getUsageValue()); MemberBenefit
					 * itemBenefit =
					 * memberBenefitService.getActiveMemberBenefit(
					 * object.getMemberId().getMemberId(),
					 * object.getCaseCategoryId().getCaseCategoryId(),
					 * caseItem.getItemId
					 * ().getItemCategoryId().getItemCategoryId());
					 * if(itemBenefit != null)
					 * benefitLimit.add(itemBenefit.getBenefitLimit()); else
					 * benefitLimit.add(0.0); memberBenefits.add(itemBenefit);
					 * if(caseItem.getUsageValue()!=null){ totalUsageValue =
					 * totalUsageValue
					 * .add(BigDecimal.valueOf(caseItem.getUsageValue())); } }
					 * result.addObject("caseItemsArray", caseItemsArray);
					 * result.addObject("benefitLimit", benefitLimit);
					 * result.addObject("chargeValue", chargeValue);
					 * result.addObject("itemsSize", caseItems.size());
					 * result.addObject("memberBenefits", memberBenefits);
					 * result.addObject("totalUsageValue",totalUsageValue);
					 */
				}
			}

			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("caseCategoryId", object.getCaseCategoryId()
					.getCaseCategoryId());
			result.addObject("memberBenefitService", memberBenefitService);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView reversalReferPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			java.io.Serializable pkey = caseId;
			String[] requiredCase = { "Case.MemberId",
					"Case.MemberId.MemberGroupId", "Case.ClaimId",
					"Case.Diagnosis2Id", "Case.Diagnosis3Id" };

			Case object = myCaseService.get(pkey, requiredCase);

			if (user != null) {
				if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					location = "detailCaseManagedCare";
				}
			}

			result = new ModelAndView(location);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.case", null, "fail", Locale.getDefault()));
			} else {
				String[] required = { "Member.ClientId",
						"Member.MemberGroupId", "Member.RelationshipId" };
				Member member = memberService.get(object.getMemberId()
						.getMemberId(), required);
				result.addObject("member", member);
				MemberProduct product = memberProductService
						.getMemberActiveProduct(object.getMemberId()
								.getMemberId(), CaseCategory.INPATIENT);

				if (product != null) {
					result.addObject("benefit", "IP "
							+ product.getProductId().getServiceClass());
				}
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String tanggal = df.format(object.getCaseStartTime());
				result.addObject("tanggal", tanggal);

				if (object.getDiagnosis1Id() != null) {

					int risk = 1;

					if (object.getDiagnosis2Id() != null) {
						risk = 2;

						if (object.getDiagnosis3Id() != null) {
							risk = 3;
						}
					}

					String[] eqParam = { "diagnosisId.diagnosisId",
							"deletedStatus", "treatmentRiskId.treatmentRiskId",
							"providerTypeId.providerTypeId" };
					Object[] eqValue = {
							object.getDiagnosis1Id().getDiagnosisId(),
							Integer.valueOf(0), Integer.valueOf(risk),
							Integer.valueOf(1) };

					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = inaCBGService
							.search(null, null, eqParam, eqValue, 0, 1);

					if (inaCBGList != null && inaCBGList.size() > 0) {
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList
								.iterator();

						if (iterator.hasNext()) {
							ProviderTypeDiagnosisTreatment inaCBG = iterator
									.next();

							result.addObject("inaCbgRef", inaCBG);
						}
					}
				}
			}

			result.addObject("myCase", object);

			if (object != null) {

				if (navigation.equalsIgnoreCase("prereverserefer")) {

					SimpleDateFormat format = new SimpleDateFormat(
							"dd-MMM-yyyy");

					String birthday = "";
					String caseDate = "";
					String tanggalSurat = "";

					birthday = format
							.format(object.getMemberId().getBirthday());
					caseDate = format.format(object.getCaseStartTime());
					tanggalSurat = format.format(object.getCaseStartTime());

					result.addObject("caseDate", caseDate);
					result.addObject("birthdate", birthday);

					// check disability

					MemberProduct activeProduct = memberProductService
							.getMemberActiveProduct(object.getMemberId()
									.getMemberId(), object.getCaseCategoryId()
									.getCaseCategoryId());
					if (activeProduct != null) {
						int disabilityLength = activeProduct
								.getDisabilityLength() == null ? 60
								: activeProduct.getDisabilityLength();

						DateTime dtAdmision = new DateTime(object
								.getCaseStartTime().getTime());

						DateTime lastDate = dtAdmision
								.minusDays(disabilityLength);

						Date minDate = new Date(lastDate.getMillis());
						Date maxDate = new Date(dtAdmision.getMillis());

						Integer diagnosisId = null;
						Integer caseCategoryId = object.getCaseCategoryId()
								.getCaseCategoryId();

						if (object.getDiagnosis1Id() != null) {
							diagnosisId = object.getDiagnosis1Id()
									.getDiagnosisId();
							int totalClaim = claimService
									.getClaimDisabilityDiagnosis(diagnosisId,
											caseCategoryId, object
													.getMemberId()
													.getMemberId(), minDate,
											maxDate);

							if (totalClaim > 0) {
								result.addObject("diagnosis1Status",
										"disability");
							} else {
								result.addObject("diagnosis1Status",
										"nodisability");
							}

							boolean diag1exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(diagnosisId, object
											.getMemberId().getMemberId());
							if (diag1exclude) {
								result.addObject("diag1exstat", "exclusion");
							}
						}

						if (object.getDiagnosis2Id() != null) {
							boolean diag2Exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(
											object.getDiagnosis2Id()
													.getDiagnosisId(), object
													.getMemberId()
													.getMemberId());

							if (diag2Exclude) {
								result.addObject("diag2exstat", "exclusion");
							}
						}
						if (object.getDiagnosis3Id() != null) {
							boolean diag3Exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(
											object.getDiagnosis3Id()
													.getDiagnosisId(), object
													.getMemberId()
													.getMemberId());

							if (diag3Exclude) {
								result.addObject("diag3exstat", "exclusion");
							}
						}
					}
				} else if (navigation.equalsIgnoreCase("reversalrefer")) {
					String note = WebUtil.getParameterString(request, "note",
							"");
					boolean res = myCaseService.voidReferCase(caseId, note,
							user);

					String alert = "";
					if (res) {
						result = new ModelAndView(new RedirectView(
								"case?navigation=detail&caseId=" + caseId
										+ "&alert=" + alert));
					}
				}

			}
			result.addObject("caseId", caseId);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView assignCasePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			java.io.Serializable pkey = caseId;
			String[] requiredCase = { "Case.MemberId",
					"Case.MemberId.MemberGroupId", "Case.ClaimId",
					"Case.Diagnosis2Id", "Case.Diagnosis3Id" };

			Case object = myCaseService.get(pkey, requiredCase);

			if (user != null) {
				if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					location = "detailCaseManagedCare";
				}
			}

			result = new ModelAndView(location);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.case", null, "fail", Locale.getDefault()));
			} else {
				String[] required = { "Member.ClientId",
						"Member.MemberGroupId", "Member.RelationshipId" };
				Member member = memberService.get(object.getMemberId()
						.getMemberId(), required);
				result.addObject("member", member);
				MemberProduct product = memberProductService
						.getMemberActiveProduct(object.getMemberId()
								.getMemberId(), CaseCategory.INPATIENT);

				if (product != null) {
					result.addObject("benefit", "IP "
							+ product.getProductId().getServiceClass());
				}
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String tanggal = df.format(object.getCaseStartTime());
				result.addObject("tanggal", tanggal);

				if (object.getDiagnosis1Id() != null) {

					int risk = 1;

					if (object.getDiagnosis2Id() != null) {
						risk = 2;

						if (object.getDiagnosis3Id() != null) {
							risk = 3;
						}
					}

					String[] eqParam = { "diagnosisId.diagnosisId",
							"deletedStatus", "treatmentRiskId.treatmentRiskId",
							"providerTypeId.providerTypeId" };
					Object[] eqValue = {
							object.getDiagnosis1Id().getDiagnosisId(),
							Integer.valueOf(0), Integer.valueOf(risk),
							Integer.valueOf(1) };

					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = inaCBGService
							.search(null, null, eqParam, eqValue, 0, 1);

					if (inaCBGList != null && inaCBGList.size() > 0) {
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList
								.iterator();

						if (iterator.hasNext()) {
							ProviderTypeDiagnosisTreatment inaCBG = iterator
									.next();

							result.addObject("inaCbgRef", inaCBG);
						}
					}
				}
			}

			result.addObject("myCase", object);

			if (object != null) {

				if (navigation.equalsIgnoreCase("preassign")) {

					Collection<User> userList = new Vector<User>();

					Collection<User> caseStaffList = userService
							.getUserByRole(Role.CASE_MANAGEMENT_STAFF);
					Collection<User> caseManagerList = userService
							.getUserByRole(Role.CASE_MANAGEMENT_HEAD);
					Collection<User> callCenterList = userService
							.getUserByRole(Role.CUSTOMER_SERVICE);

					for (Iterator iterator = caseStaffList.iterator(); iterator
							.hasNext();) {
						User user2 = (User) iterator.next();

						if (user2 != null) {
							userList.add(user2);
						}
					}

					for (Iterator iterator = caseManagerList.iterator(); iterator
							.hasNext();) {
						User user2 = (User) iterator.next();

						if (user2 != null) {
							userList.add(user2);
						}
					}

					for (Iterator iterator = callCenterList.iterator(); iterator
							.hasNext();) {
						User user2 = (User) iterator.next();

						if (user2 != null) {
							userList.add(user2);
						}
					}

					SimpleDateFormat format = new SimpleDateFormat(
							"dd-MMM-yyyy");

					String birthday = "";
					String caseDate = "";
					String tanggalSurat = "";

					birthday = format
							.format(object.getMemberId().getBirthday());
					caseDate = format.format(object.getCaseStartTime());
					tanggalSurat = format.format(object.getCaseStartTime());

					result.addObject("caseDate", caseDate);
					result.addObject("birthdate", birthday);
					result.addObject("userList", userList);

					// check disability

					MemberProduct activeProduct = memberProductService
							.getMemberActiveProduct(object.getMemberId()
									.getMemberId(), object.getCaseCategoryId()
									.getCaseCategoryId());
					if (activeProduct != null) {
						int disabilityLength = activeProduct
								.getDisabilityLength() == null ? 60
								: activeProduct.getDisabilityLength();

						DateTime dtAdmision = new DateTime(object
								.getCaseStartTime().getTime());

						DateTime lastDate = dtAdmision
								.minusDays(disabilityLength);

						Date minDate = new Date(lastDate.getMillis());
						Date maxDate = new Date(dtAdmision.getMillis());

						Integer diagnosisId = null;
						Integer caseCategoryId = object.getCaseCategoryId()
								.getCaseCategoryId();

						if (object.getDiagnosis1Id() != null) {
							diagnosisId = object.getDiagnosis1Id()
									.getDiagnosisId();
							int totalClaim = claimService
									.getClaimDisabilityDiagnosis(diagnosisId,
											caseCategoryId, object
													.getMemberId()
													.getMemberId(), minDate,
											maxDate);

							if (totalClaim > 0) {
								result.addObject("diagnosis1Status",
										"disability");
							} else {
								result.addObject("diagnosis1Status",
										"nodisability");
							}

							boolean diag1exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(diagnosisId, object
											.getMemberId().getMemberId());
							if (diag1exclude) {
								result.addObject("diag1exstat", "exclusion");
							}
						}

						if (object.getDiagnosis2Id() != null) {
							boolean diag2Exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(
											object.getDiagnosis2Id()
													.getDiagnosisId(), object
													.getMemberId()
													.getMemberId());

							if (diag2Exclude) {
								result.addObject("diag2exstat", "exclusion");
							}
						}
						if (object.getDiagnosis3Id() != null) {
							boolean diag3Exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(
											object.getDiagnosis3Id()
													.getDiagnosisId(), object
													.getMemberId()
													.getMemberId());

							if (diag3Exclude) {
								result.addObject("diag3exstat", "exclusion");
							}
						}
					}
				} else if (navigation.equalsIgnoreCase("assigncase")) {
					String note = WebUtil.getParameterString(request, "note",
							"");
					Integer assigneeId = WebUtil.getParameterInteger(request,
							"assigneeId");
					boolean res = myCaseService.assignCase(caseId, assigneeId,
							note, user);

					String alert = "";
					if (res) {
						alert = "<b>Success Assign Case</b>";
					} else {
						alert = "<b>Failed Assign Case</b>";
					}

					result = new ModelAndView(new RedirectView(
							"case?navigation=detail&caseId=" + caseId
									+ "&alert=" + alert));
				}

			}
			result.addObject("caseId", caseId);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView pendingCasePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"PENDINGCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			java.io.Serializable pkey = caseId;
			// modified by aju on 20150827, add CaseStatusId relation :D
			String[] requiredCase = { "Case.MemberId",
					"Case.MemberId.MemberGroupId", "Case.ClaimId",
					"Case.Diagnosis2Id", "Case.Diagnosis3Id",
					"Case.CaseStatusId" };

			Case object = myCaseService.get(pkey, requiredCase);

			result = new ModelAndView(location);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.case", null, "fail", Locale.getDefault()));
			} else {
				String[] required = { "Member.ClientId",
						"Member.MemberGroupId", "Member.RelationshipId" };
				Member member = memberService.get(object.getMemberId()
						.getMemberId(), required);
				result.addObject("member", member);
				MemberProduct product = memberProductService
						.getMemberActiveProduct(object.getMemberId()
								.getMemberId(), CaseCategory.INPATIENT);

				if (product != null) {
					result.addObject("benefit", "IP "
							+ product.getProductId().getServiceClass());
				}
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String tanggal = df.format(object.getCaseStartTime());
				result.addObject("tanggal", tanggal);

				if (object.getDiagnosis1Id() != null) {

					int risk = 1;

					if (object.getDiagnosis2Id() != null) {
						risk = 2;

						if (object.getDiagnosis3Id() != null) {
							risk = 3;
						}
					}

					String[] eqParam = { "diagnosisId.diagnosisId",
							"deletedStatus", "treatmentRiskId.treatmentRiskId",
							"providerTypeId.providerTypeId" };
					Object[] eqValue = {
							object.getDiagnosis1Id().getDiagnosisId(),
							Integer.valueOf(0), Integer.valueOf(risk),
							Integer.valueOf(1) };

					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = inaCBGService
							.search(null, null, eqParam, eqValue, 0, 1);

					if (inaCBGList != null && inaCBGList.size() > 0) {
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList
								.iterator();

						if (iterator.hasNext()) {
							ProviderTypeDiagnosisTreatment inaCBG = iterator
									.next();

							result.addObject("inaCbgRef", inaCBG);
						}
					}
				}
			}

			result.addObject("myCase", object);

			if (object != null) {

				if (navigation.equalsIgnoreCase("prepending")) {

					Collection<User> userList = new Vector<User>();

					Collection<User> caseManagerList = userService
							.getUserByRole(Role.CASE_MANAGEMENT_HEAD);

					for (Iterator iterator = caseManagerList.iterator(); iterator
							.hasNext();) {
						User user2 = (User) iterator.next();

						if (user2 != null) {
							userList.add(user2);
						}
					}
					SimpleDateFormat format = new SimpleDateFormat(
							"dd-MMM-yyyy");

					String birthday = "";
					String caseDate = "";
					String tanggalSurat = "";

					birthday = format
							.format(object.getMemberId().getBirthday());
					caseDate = format.format(object.getCaseStartTime());
					tanggalSurat = format.format(object.getCaseStartTime());

					result.addObject("caseDate", caseDate);
					result.addObject("birthdate", birthday);
					result.addObject("userList", userList);

					// check disability

					MemberProduct activeProduct = memberProductService
							.getMemberActiveProduct(object.getMemberId()
									.getMemberId(), object.getCaseCategoryId()
									.getCaseCategoryId());
					if (activeProduct != null) {
						int disabilityLength = activeProduct
								.getDisabilityLength() == null ? 60
								: activeProduct.getDisabilityLength();

						DateTime dtAdmision = new DateTime(object
								.getCaseStartTime().getTime());

						DateTime lastDate = dtAdmision
								.minusDays(disabilityLength);

						Date minDate = new Date(lastDate.getMillis());
						Date maxDate = new Date(dtAdmision.getMillis());

						Integer diagnosisId = null;
						Integer caseCategoryId = object.getCaseCategoryId()
								.getCaseCategoryId();

						if (object.getDiagnosis1Id() != null) {
							diagnosisId = object.getDiagnosis1Id()
									.getDiagnosisId();
							int totalClaim = claimService
									.getClaimDisabilityDiagnosis(diagnosisId,
											caseCategoryId, object
													.getMemberId()
													.getMemberId(), minDate,
											maxDate);

							if (totalClaim > 0) {
								result.addObject("diagnosis1Status",
										"disability");
							} else {
								result.addObject("diagnosis1Status",
										"nodisability");
							}

							boolean diag1exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(diagnosisId, object
											.getMemberId().getMemberId());
							if (diag1exclude) {
								result.addObject("diag1exstat", "exclusion");
							}
						}

						if (object.getDiagnosis2Id() != null) {
							boolean diag2Exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(
											object.getDiagnosis2Id()
													.getDiagnosisId(), object
													.getMemberId()
													.getMemberId());

							if (diag2Exclude) {
								result.addObject("diag2exstat", "exclusion");
							}
						}
						if (object.getDiagnosis3Id() != null) {
							boolean diag3Exclude = memberDiagnosisExclusionService
									.isExcludedDiagnosis(
											object.getDiagnosis3Id()
													.getDiagnosisId(), object
													.getMemberId()
													.getMemberId());

							if (diag3Exclude) {
								result.addObject("diag3exstat", "exclusion");
							}
						}
					}
				} else if (navigation.equalsIgnoreCase("pendingcase")) {
					String note = WebUtil.getParameterString(request, "note",
							"");
					Integer assigneeId = WebUtil.getParameterInteger(request,
							"assigneeId");
					Integer pendingStatus = WebUtil.getParameterInteger(
							request, "pendingStatus");

					boolean res = myCaseService.pendingCase(caseId,
							pendingStatus, assigneeId, note, user);

					String alert = "";
					if (res) {
						alert = "<b>Success Pending Case</b>";
					} else {
						alert = "<b>Failed Pending Case</b>";
					}

					result = new ModelAndView(new RedirectView(
							"case?navigation=detail&caseId=" + caseId
									+ "&alert=" + alert));
				}

			}
			result.addObject("caseId", caseId);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView downloadCaseRejectedPerformed(
			HttpServletRequest request, HttpServletResponse response,
			String location) throws Exception {

		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			String providerText = WebUtil.getParameterString(request,
					"providerText", "");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");

			result = new ModelAndView(location);

			result.addObject("subnavigation", subnavigation);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equalsIgnoreCase("golist")
					|| navigation.equalsIgnoreCase("gosearchissued")
					|| navigation.equals("downloadExcel")) {

				if (searchby != null) {

					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")) {
						vLikeP.add("currentCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("memberId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("memberId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("physician")) {
						vLikeP.add("physician");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosis1Id.diagnosisName");
						vLikeQ.add(searchtext);
					}
					// Penambahan Kriteria Search untuk Case Category
					if (searchby.equals("category")) {
						vLikeP.add("caseCategoryId.caseCategoryName");
						vLikeQ.add(searchtext);
					}

					if (searchStatus != null && searchStatus.intValue() > 0) {
						vEqP.add("caseStatusId.caseStatusId");
						vEqQ.add(searchStatus);
					}

				}

			}

			if (providerId != null && !providerText.equalsIgnoreCase("")) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			}
			result.addObject("providerText", providerText);
			result.addObject("caseCategoryId", caseCategoryId);

			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);

			result.addObject("searchby", searchby);

			if (caseCategoryId != null) {
				vEqP.add("caseCategoryId.caseCategoryId");
				vEqQ.add(caseCategoryId);
			}

			if (memberGroupId != null) {
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}

			if (providerId != null) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			}

			if (clientId != null) {
				vEqP.add("memberId.clientId.clientId");
				vEqQ.add(user.getUser().getClientId().getClientId());
			}

			vEqP.add("isGLIssued");
			vEqQ.add(1);

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId",
			// foreign affairs end
			};
			collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					false, "caseId", required, rowsetint, count);

			if (navigation.equalsIgnoreCase("downloadissued")) {
				HSSFWorkbook workbook = CaseReportDownloader
						.downloadGLIssuedMonitoring(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "glissuedreport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView downloadGLIssuedPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			String arah = WebUtil.getParameterString(request, "arah", "");

			String sortcolumn = WebUtil.getParameterString(request,
					"sortcolumn", "");
			boolean sortCaseNumber = true, sortMemberName = true, sortProvider = true, sortAdmissionDate = true, sortDischargeDate = true, sortType = true, sortLimit = true, sortDiagnosis = true, sortCharge = true, sortAprrove = true, sortExcess = true, sortStatus = true, sortModifiedBy = true, sortCreatedTime = true, sortModifiedTime = true, sortClient = true, sortGroup = true, sortLongOfStay = true, sortAssignee = true;
			boolean order = true;

			if (index == null) {
				index = 0;
			}

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			String[] searchStatusPassJSP = WebUtil.getParameterStringArray(
					request, "status");
			Integer[] searchStatusMulti = null;
			if (searchStatusPassJSP.length > 1) {
				searchStatusMulti = new Integer[searchStatusPassJSP.length];
				for (int i = 0; i < searchStatusPassJSP.length; i++) {
					searchStatusMulti[i] = Integer
							.parseInt(searchStatusPassJSP[i]);
				}
			}
			// end aulia
			// begin aulia
			String[] caseCategoryIdPassJSP = WebUtil.getParameterStringArray(
					request, "caseCategoryId");

			Integer[] caseCategoryIdMulti = null;
			if (caseCategoryIdPassJSP.length > 1) {
				caseCategoryIdMulti = new Integer[caseCategoryIdPassJSP.length];
				for (int i = 0; i < caseCategoryIdPassJSP.length; i++) {
					caseCategoryIdMulti[i] = Integer
							.parseInt(caseCategoryIdPassJSP[i]);
				}
			}

			String providerText = WebUtil.getParameterString(request,
					"providerText", "");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");

			result = new ModelAndView(location);

			result.addObject("subnavigation", subnavigation);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			Vector vInQStatus = new Vector();
			Vector vInQCat = new Vector();

			if ((!navigation.equalsIgnoreCase("gosearchissuedbysort")
					&& arah.isEmpty() && arah.equals(""))
					|| navigation.equals("gosearchissued")
					|| navigation.equals("downloadissued")) {
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}

			if (navigation.equalsIgnoreCase("gosearchissued")
					|| navigation.equals("downloadissued")
					|| navigation.equalsIgnoreCase("gosearchissuedbysort")) {

				if (searchby != null) {

					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")) {
						vLikeP.add("currentCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("memberId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("memberId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("physician")) {
						vLikeP.add("physician");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosis1Id.diagnosisName");
						vLikeQ.add(searchtext);
					}
				}
			}

			if (searchStatusMulti == null) {
				// menghilangkan conditional yang besar dari nol, karena
				// sebelumnya menggunakan single select pada page jsp, by Aulia
				// R. 1-6-2015
				// if (searchStatus != null && searchStatus.intValue() > 0) {
				if (searchStatus != null) {
					vEqP.add("caseStatusId.caseStatusId");
					vEqQ.add(searchStatus);
				}
			} else {
				if (searchStatusMulti != null) {

					Integer searchStatus2 = null;
					for (int i = 0; i < searchStatusMulti.length; i++) {
						searchStatus2 = Integer
								.parseInt(searchStatusPassJSP[i]);
						vInQStatus.add(searchStatus2);
					}
				}

				result.addObject("statusMulti", searchStatusPassJSP);
			}

			if (caseCategoryIdMulti == null) {
				result.addObject("caseCategoryId", caseCategoryId);
			} else {
				result.addObject("caseCategoryIdMulti", caseCategoryIdPassJSP);
			}

			if (caseCategoryIdMulti == null) {
				if (caseCategoryId != null) {
					vEqP.add("caseCategoryId.caseCategoryId");
					vEqQ.add(caseCategoryId);
				}
			} else {
				if (caseCategoryIdMulti != null) {
					// vEqP.add("caseCategoryId.caseCategoryId");

					Integer caseCategoryId2 = null;
					for (int i = 0; i < caseCategoryIdMulti.length; i++) {
						caseCategoryId2 = Integer
								.parseInt(caseCategoryIdPassJSP[i]);
						vInQCat.add(caseCategoryId2);
					}
				}
			}
			result.addObject("providerText", providerText);

			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);

			result.addObject("searchby", searchby);

			if (memberGroupId != null) {
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}
			if (providerId != null) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			}

			if (clientId != null) {
				vEqP.add("memberId.clientId.clientId");
				vEqQ.add(clientId);
			}

			vEqP.add("isGLIssued");
			vEqQ.add(1);

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			Object sInQStatus[] = new Object[vInQStatus.size()];
			vInQStatus.toArray(sInQStatus);
			Object sInQCat[] = new Object[vInQCat.size()];
			vInQCat.toArray(sInQCat);

			if (caseCategoryIdMulti != null && searchStatusMulti != null
					|| caseCategoryIdMulti == null && searchStatusMulti != null
					|| caseCategoryIdMulti != null && searchStatusMulti == null) {
				count = myCaseService.getTotalMultiCase(sLikeP, sLikeQ, sEqP,
						sEqQ, sInQStatus, sInQCat);
			} else {
				count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId",
			// foreign affairs end
			};

			String sortByColumn = new String();
			if (sortcolumn != null && !sortcolumn.equals("")) {
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(
						request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request,
						"columntosort", "");
				if (navigation.equals("gosearchissuedbysort")) {
					if (sortcolumn.equals("casenumber")) {
						sortByColumn = "caseNumber";
						Boolean sortByOrderCaseNo = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderCaseNo", ""));
						sortCaseNumber = !sortByOrderCaseNo;
						order = sortCaseNumber;
					} else if (sortcolumn.equals("membername")) {
						sortByColumn = "memberId.firstName";
						Boolean sortByOrderMember = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderMember", ""));
						sortMemberName = !sortByOrderMember;
						order = sortMemberName;
					} else if (sortcolumn.equals("providername")) {
						if (providerId != null
								&& !providerText.equalsIgnoreCase("")) {
							sortByColumn = "providerId";
						} else {
							sortByColumn = "providerId.providerName";
						}
						Boolean sortByOrderProvider = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderProvider", ""));
						sortProvider = !sortByOrderProvider;
						order = sortProvider;
					} else if (sortcolumn.equals("admissiondate")) {
						sortByColumn = "caseStartTime";
						Boolean sortByOrderAdmission = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderAdmission", ""));
						sortAdmissionDate = !sortByOrderAdmission;
						order = sortAdmissionDate;
					} else if (sortcolumn.equals("dischargedate")) {
						sortByColumn = "caseEndTime";
						Boolean sortByOrderDischarge = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderDischarge", ""));
						sortDischargeDate = !sortByOrderDischarge;
						order = sortDischargeDate;
					} else if (sortcolumn.equals("type")) {
						sortByColumn = "caseCategoryId";
						Boolean sortByOrderType = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortByOrderType",
										""));
						sortType = !sortByOrderType;
						order = sortType;
					} else if (sortcolumn.equals("longofstay")) {
						sortByColumn = "longOfStay";
						Boolean sortByOrderLongOfStay = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderLongOfStay", ""));
						sortLongOfStay = !sortByOrderLongOfStay;
						order = sortLongOfStay;
					} else if (sortcolumn.equals("limit")) {
						sortByColumn = "preRemainingLimit";
						Boolean sortByOrderLimit = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderLimit", ""));
						sortLimit = !sortByOrderLimit;
						order = sortLimit;
					} else if (sortcolumn.equals("diagnosis")) {
						sortByColumn = "diagnosis1Id.diagnosisCode";
						Boolean sortByOrderDiagnosis = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderDiagnosis", ""));
						sortDiagnosis = !sortByOrderDiagnosis;
						order = sortDiagnosis;
					} else if (sortcolumn.equals("charge")) {
						sortByColumn = "caseClaimValue";
						Boolean sortByOrderCharge = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderCharge", ""));
						sortCharge = !sortByOrderCharge;
						order = sortCharge;
					} else if (sortcolumn.equals("approve")) {
						sortByColumn = "caseApprovedValue";
						Boolean sortByOrderApprove = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderApprove", ""));
						sortAprrove = !sortByOrderApprove;
						order = sortAprrove;
					} else if (sortcolumn.equals("excess")) {
						sortByColumn = "caseExcessValue";
						Boolean sortByOrderExcess = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderExcess", ""));
						sortExcess = !sortByOrderExcess;
						order = sortExcess;
					} else if (sortcolumn.equals("status")) {
						if (searchStatus != null && searchStatus.intValue() > 0) {
							sortByColumn = "caseStatusId";
						} else {
							sortByColumn = "caseStatusId.caseStatusName";
						}
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					} else if (sortcolumn.equals("modifiedby")) {
						sortByColumn = "modifiedBy";
						Boolean sortByOrderModifiedBy = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderModifiedBy", ""));
						sortModifiedBy = !sortByOrderModifiedBy;
						order = sortModifiedBy;
					} else if (sortcolumn.equals("createdtime")) {
						sortByColumn = "createdTime";
						Boolean sortByOrderCreatedTime = Boolean
								.valueOf(WebUtil.getParameterString(request,
										"sortByOrderCreatedTime", ""));
						sortCreatedTime = !sortByOrderCreatedTime;
						order = sortCreatedTime;
					} else if (sortcolumn.equals("modifiedtime")) {
						sortByColumn = "modifiedTime";
						Boolean sortByOrderModifTime = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderModifTime", ""));
						sortModifiedTime = !sortByOrderModifTime;
						order = sortModifiedTime;
					} else if (sortcolumn.equalsIgnoreCase("clientname")) {
						sortByColumn = "memberId.clientName";
						Boolean sortByOrderClient = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderClient", ""));
						sortClient = !sortByOrderClient;
						order = sortClient;
					} else if (sortcolumn.equalsIgnoreCase("assignee")) {
						sortByColumn = "assigneeId.firstName";
						Boolean sortByOrderAssignee = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderAssignee", ""));
						sortAssignee = !sortByOrderAssignee;
						order = sortAssignee;
					} else {
						sortByColumn = "memberId.groupName";
						Boolean sortByOrderGroup = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderGroup", ""));
						sortGroup = !sortByOrderGroup;
						order = sortGroup;
					}
					columntosort = sortByColumn;
				} else {
					sortByColumn = columntosort;
					order = sortOrder;
					if (sortcolumn.equals("casenumber")) {
						sortCaseNumber = order;
					} else if (sortcolumn.equals("membername")) {
						sortMemberName = order;
					} else if (sortcolumn.equals("providername")) {
						sortProvider = order;
					} else if (sortcolumn.equals("admissiondate")) {
						sortAdmissionDate = order;
					} else if (sortcolumn.equals("dischargedate")) {
						sortDischargeDate = order;
					} else if (sortcolumn.equals("type")) {
						sortType = order;
					} else if (sortcolumn.equals("longofstay")) {
						sortLongOfStay = order;
					} else if (sortcolumn.equals("limit")) {
						sortLimit = order;
					} else if (sortcolumn.equals("diagnosis")) {
						sortDiagnosis = order;
					} else if (sortcolumn.equals("charge")) {
						sortCharge = order;
					} else if (sortcolumn.equals("approve")) {
						sortAprrove = order;
					} else if (sortcolumn.equals("excess")) {
						sortExcess = order;
					} else if (sortcolumn.equals("status")) {
						sortStatus = order;
					} else if (sortcolumn.equals("modifiedby")) {
						sortModifiedBy = order;
					} else if (sortcolumn.equals("createdtime")) {
						sortCreatedTime = order;
					} else if (sortcolumn.equals("modifiedtime")) {
						sortModifiedTime = order;
					} else if (sortcolumn.equalsIgnoreCase("clientname")) {
						sortClient = order;
					} else if (sortcolumn.equalsIgnoreCase("assignee")) {
						sortAssignee = order;
					} else {
						sortGroup = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if (caseCategoryIdMulti != null && searchStatusMulti != null
						|| caseCategoryIdMulti == null
						&& searchStatusMulti != null
						|| caseCategoryIdMulti != null
						&& searchStatusMulti == null) {
					/**
					 * collection = myCaseService.searchMultiCase(sLikeP,
					 * sLikeQ, sEqP, sEqQ, sInQStatus, sInQCat,order,
					 * sortByColumn, required, rowsetint, countSet.intValue());
					 */

					collection = myCaseService.searchMultiCase(sLikeP, sLikeQ,
							sEqP, sEqQ, sInQStatus, sInQCat, order,
							sortByColumn, required, 0, count);
				} else {
					/**
					 * collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
					 * sEqQ, order, sortByColumn, required, rowsetint,
					 * countSet.intValue());
					 */

					collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, order, sortByColumn, required, 0, count);
				}
			} else {
				if (caseCategoryIdMulti != null && searchStatusMulti != null
						|| caseCategoryIdMulti == null
						&& searchStatusMulti != null
						|| caseCategoryIdMulti != null
						&& searchStatusMulti == null) {

					/**
					 * collection = myCaseService.searchMultiCase(sLikeP,
					 * sLikeQ, sEqP, sEqQ, sInQStatus, sInQCat, false, "caseId",
					 * required, rowsetint, countSet.intValue());
					 */

					/**
					 * show all result for reporting AP
					 */
					collection = myCaseService.searchMultiCase(sLikeP, sLikeQ,
							sEqP, sEqQ, sInQStatus, sInQCat, false, "caseId",
							required, 0, count);
				} else {

					/**
					 * collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
					 * sEqQ, false, "caseId", required, rowsetint,
					 * countSet.intValue());
					 */

					collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, false, "caseId", required, 0, count);
				}

			}

			if (navigation.equalsIgnoreCase("downloadissued")) {
				HSSFWorkbook workbook = CaseReportDownloader
						.downloadGLIssuedMonitoring(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "glissuedreport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			result.addObject("Cases", collection);
			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView downloadGLActivePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			String arah = WebUtil.getParameterString(request, "arah", "");

			String sortcolumn = WebUtil.getParameterString(request,
					"sortcolumn", "");
			boolean sortCaseNumber = true, sortMemberName = true, sortProvider = true, sortAdmissionDate = true, sortDischargeDate = true, sortType = true, sortLimit = true, sortDiagnosis = true, sortCharge = true, sortAprrove = true, sortExcess = true, sortStatus = true, sortModifiedBy = true, sortCreatedTime = true, sortModifiedTime = true, sortClient = true, sortGroup = true, sortLongOfStay = true, sortAssignee = true;
			boolean order = true;

			if (index == null) {
				index = 0;
			}

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			String[] searchStatusPassJSP = WebUtil.getParameterStringArray(
					request, "status");
			Integer[] searchStatusMulti = null;
			if (searchStatusPassJSP.length > 1) {
				searchStatusMulti = new Integer[searchStatusPassJSP.length];
				for (int i = 0; i < searchStatusPassJSP.length; i++) {
					searchStatusMulti[i] = Integer
							.parseInt(searchStatusPassJSP[i]);
				}
			}
			// end aulia
			// begin aulia
			String[] caseCategoryIdPassJSP = WebUtil.getParameterStringArray(
					request, "caseCategoryId");

			Integer[] caseCategoryIdMulti = null;
			if (caseCategoryIdPassJSP.length > 1) {
				caseCategoryIdMulti = new Integer[caseCategoryIdPassJSP.length];
				for (int i = 0; i < caseCategoryIdPassJSP.length; i++) {
					caseCategoryIdMulti[i] = Integer
							.parseInt(caseCategoryIdPassJSP[i]);
				}
			}

			String providerText = WebUtil.getParameterString(request,
					"providerText", "");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");

			result = new ModelAndView(location);

			result.addObject("subnavigation", subnavigation);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			Vector vInQStatus = new Vector();
			Vector vInQCat = new Vector();

			if ((!navigation.equalsIgnoreCase("gosearchactivebysort")
					&& arah.isEmpty() && arah.equals(""))
					|| navigation.equals("gosearchactive")
					|| navigation.equals("downloadactive")) {
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}

			if (navigation.equalsIgnoreCase("gosearchactive")
					|| navigation.equals("downloadactive")
					|| navigation.equalsIgnoreCase("gosearchactivebysort")) {

				if (searchby != null) {

					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")) {
						vLikeP.add("currentCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("memberId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("memberId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("physician")) {
						vLikeP.add("physician");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosis1Id.diagnosisName");
						vLikeQ.add(searchtext);
					}
				}
			}

			if (caseCategoryIdMulti == null) {
				result.addObject("caseCategoryId", caseCategoryId);
			} else {
				result.addObject("caseCategoryIdMulti", caseCategoryIdPassJSP);
			}

			if (caseCategoryIdMulti == null) {
				if (caseCategoryId != null) {
					vEqP.add("caseCategoryId.caseCategoryId");
					vEqQ.add(caseCategoryId);
				}
			} else {
				if (caseCategoryIdMulti != null) {
					// vEqP.add("caseCategoryId.caseCategoryId");

					Integer caseCategoryId2 = null;
					for (int i = 0; i < caseCategoryIdMulti.length; i++) {
						caseCategoryId2 = Integer
								.parseInt(caseCategoryIdPassJSP[i]);
						vInQCat.add(caseCategoryId2);
					}
				}
			}
			result.addObject("providerText", providerText);

			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);

			result.addObject("searchby", searchby);

			if (memberGroupId != null) {
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}
			if (providerId != null) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			}

			if (clientId != null) {
				vEqP.add("memberId.clientId.clientId");
				vEqQ.add(clientId);
			}

			vEqP.add("caseStatusId.caseStatusId");
			vEqQ.add(CaseStatus.CASE_APPROVED);

			vEqP.add("isGLIssued");
			vEqQ.add(1);

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			Object sInQStatus[] = new Object[vInQStatus.size()];
			vInQStatus.toArray(sInQStatus);
			Object sInQCat[] = new Object[vInQCat.size()];
			vInQCat.toArray(sInQCat);

			if (caseCategoryIdMulti != null) {
				count = myCaseService.getTotalMultiCase(sLikeP, sLikeQ, sEqP,
						sEqQ, sInQStatus, sInQCat);
			} else {
				count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId",
			// foreign affairs end
			};

			String sortByColumn = new String();
			if (sortcolumn != null && !sortcolumn.equals("")) {
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(
						request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request,
						"columntosort", "");
				if (navigation.equals("gosearchactivebysort")) {
					if (sortcolumn.equals("casenumber")) {
						sortByColumn = "caseNumber";
						Boolean sortByOrderCaseNo = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderCaseNo", ""));
						sortCaseNumber = !sortByOrderCaseNo;
						order = sortCaseNumber;
					} else if (sortcolumn.equals("membername")) {
						sortByColumn = "memberId.firstName";
						Boolean sortByOrderMember = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderMember", ""));
						sortMemberName = !sortByOrderMember;
						order = sortMemberName;
					} else if (sortcolumn.equals("providername")) {
						if (providerId != null
								&& !providerText.equalsIgnoreCase("")) {
							sortByColumn = "providerId";
						} else {
							sortByColumn = "providerId.providerName";
						}
						Boolean sortByOrderProvider = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderProvider", ""));
						sortProvider = !sortByOrderProvider;
						order = sortProvider;
					} else if (sortcolumn.equals("admissiondate")) {
						sortByColumn = "caseStartTime";
						Boolean sortByOrderAdmission = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderAdmission", ""));
						sortAdmissionDate = !sortByOrderAdmission;
						order = sortAdmissionDate;
					} else if (sortcolumn.equals("dischargedate")) {
						sortByColumn = "caseEndTime";
						Boolean sortByOrderDischarge = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderDischarge", ""));
						sortDischargeDate = !sortByOrderDischarge;
						order = sortDischargeDate;
					} else if (sortcolumn.equals("type")) {
						sortByColumn = "caseCategoryId";
						Boolean sortByOrderType = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortByOrderType",
										""));
						sortType = !sortByOrderType;
						order = sortType;
					} else if (sortcolumn.equals("longofstay")) {
						sortByColumn = "longOfStay";
						Boolean sortByOrderLongOfStay = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderLongOfStay", ""));
						sortLongOfStay = !sortByOrderLongOfStay;
						order = sortLongOfStay;
					} else if (sortcolumn.equals("limit")) {
						sortByColumn = "preRemainingLimit";
						Boolean sortByOrderLimit = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderLimit", ""));
						sortLimit = !sortByOrderLimit;
						order = sortLimit;
					} else if (sortcolumn.equals("diagnosis")) {
						sortByColumn = "diagnosis1Id.diagnosisCode";
						Boolean sortByOrderDiagnosis = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderDiagnosis", ""));
						sortDiagnosis = !sortByOrderDiagnosis;
						order = sortDiagnosis;
					} else if (sortcolumn.equals("charge")) {
						sortByColumn = "caseClaimValue";
						Boolean sortByOrderCharge = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderCharge", ""));
						sortCharge = !sortByOrderCharge;
						order = sortCharge;
					} else if (sortcolumn.equals("approve")) {
						sortByColumn = "caseApprovedValue";
						Boolean sortByOrderApprove = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderApprove", ""));
						sortAprrove = !sortByOrderApprove;
						order = sortAprrove;
					} else if (sortcolumn.equals("excess")) {
						sortByColumn = "caseExcessValue";
						Boolean sortByOrderExcess = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderExcess", ""));
						sortExcess = !sortByOrderExcess;
						order = sortExcess;
					} else if (sortcolumn.equals("status")) {
						if (searchStatus != null && searchStatus.intValue() > 0) {
							sortByColumn = "caseStatusId";
						} else {
							sortByColumn = "caseStatusId.caseStatusName";
						}
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					} else if (sortcolumn.equals("modifiedby")) {
						sortByColumn = "modifiedBy";
						Boolean sortByOrderModifiedBy = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderModifiedBy", ""));
						sortModifiedBy = !sortByOrderModifiedBy;
						order = sortModifiedBy;
					} else if (sortcolumn.equals("createdtime")) {
						sortByColumn = "createdTime";
						Boolean sortByOrderCreatedTime = Boolean
								.valueOf(WebUtil.getParameterString(request,
										"sortByOrderCreatedTime", ""));
						sortCreatedTime = !sortByOrderCreatedTime;
						order = sortCreatedTime;
					} else if (sortcolumn.equals("modifiedtime")) {
						sortByColumn = "modifiedTime";
						Boolean sortByOrderModifTime = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderModifTime", ""));
						sortModifiedTime = !sortByOrderModifTime;
						order = sortModifiedTime;
					} else if (sortcolumn.equalsIgnoreCase("clientname")) {
						sortByColumn = "memberId.clientName";
						Boolean sortByOrderClient = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderClient", ""));
						sortClient = !sortByOrderClient;
						order = sortClient;
					} else if (sortcolumn.equalsIgnoreCase("assignee")) {
						sortByColumn = "assigneeId.firstName";
						Boolean sortByOrderAssignee = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderAssignee", ""));
						sortAssignee = !sortByOrderAssignee;
						order = sortAssignee;
					} else {
						sortByColumn = "memberId.groupName";
						Boolean sortByOrderGroup = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderGroup", ""));
						sortGroup = !sortByOrderGroup;
						order = sortGroup;
					}
					columntosort = sortByColumn;
				} else {
					sortByColumn = columntosort;
					order = sortOrder;
					if (sortcolumn.equals("casenumber")) {
						sortCaseNumber = order;
					} else if (sortcolumn.equals("membername")) {
						sortMemberName = order;
					} else if (sortcolumn.equals("providername")) {
						sortProvider = order;
					} else if (sortcolumn.equals("admissiondate")) {
						sortAdmissionDate = order;
					} else if (sortcolumn.equals("dischargedate")) {
						sortDischargeDate = order;
					} else if (sortcolumn.equals("type")) {
						sortType = order;
					} else if (sortcolumn.equals("longofstay")) {
						sortLongOfStay = order;
					} else if (sortcolumn.equals("limit")) {
						sortLimit = order;
					} else if (sortcolumn.equals("diagnosis")) {
						sortDiagnosis = order;
					} else if (sortcolumn.equals("charge")) {
						sortCharge = order;
					} else if (sortcolumn.equals("approve")) {
						sortAprrove = order;
					} else if (sortcolumn.equals("excess")) {
						sortExcess = order;
					} else if (sortcolumn.equals("status")) {
						sortStatus = order;
					} else if (sortcolumn.equals("modifiedby")) {
						sortModifiedBy = order;
					} else if (sortcolumn.equals("createdtime")) {
						sortCreatedTime = order;
					} else if (sortcolumn.equals("modifiedtime")) {
						sortModifiedTime = order;
					} else if (sortcolumn.equalsIgnoreCase("clientname")) {
						sortClient = order;
					} else if (sortcolumn.equalsIgnoreCase("assignee")) {
						sortAssignee = order;
					} else {
						sortGroup = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if (caseCategoryIdMulti != null && searchStatusMulti != null
						|| caseCategoryIdMulti == null
						&& searchStatusMulti != null
						|| caseCategoryIdMulti != null
						&& searchStatusMulti == null) {

					collection = myCaseService.searchMultiCase(sLikeP, sLikeQ,
							sEqP, sEqQ, sInQStatus, sInQCat, order,
							sortByColumn, required, rowsetint,
							countSet.intValue());
				} else {
					collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, order, sortByColumn, required, rowsetint,
							countSet.intValue());
				}
			} else {
				if (caseCategoryIdMulti != null && searchStatusMulti != null
						|| caseCategoryIdMulti == null
						&& searchStatusMulti != null
						|| caseCategoryIdMulti != null
						&& searchStatusMulti == null) {

					collection = myCaseService.searchMultiCase(sLikeP, sLikeQ,
							sEqP, sEqQ, sInQStatus, sInQCat, false, "caseId",
							required, rowsetint, countSet.intValue());
				} else {
					collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, false, "caseId", required, rowsetint,
							countSet.intValue());
				}

			}

			if (navigation.equalsIgnoreCase("downloadactive")) {
				HSSFWorkbook workbook = CaseReportDownloader
						.downloadGLIssuedMonitoring(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "activeCaseReport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			result.addObject("Cases", collection);
			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView reversalCasePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			java.io.Serializable pkey = caseId;
			String[] requiredCase = { "Case.MemberId",
					"Case.MemberId.MemberGroupId", "Case.ClaimId",
					"Case.Diagnosis2Id", "Case.Diagnosis3Id" };

			Case object = myCaseService.get(pkey, requiredCase);

			result = new ModelAndView(location);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.case", null, "fail", Locale.getDefault()));
			} else {
				String[] required = { "Member.ClientId",
						"Member.MemberGroupId", "Member.RelationshipId" };
				Member member = memberService.get(object.getMemberId()
						.getMemberId(), required);
				result.addObject("member", member);
				MemberProduct product = memberProductService
						.getMemberActiveProduct(object.getMemberId()
								.getMemberId(), CaseCategory.INPATIENT);

				if (product != null) {
					result.addObject("benefit", "IP "
							+ product.getProductId().getServiceClass());
				}
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String tanggal = df.format(object.getCaseStartTime());
				result.addObject("tanggal", tanggal);

				if (object.getDiagnosis1Id() != null) {

					int risk = 1;

					if (object.getDiagnosis2Id() != null) {
						risk = 2;

						if (object.getDiagnosis3Id() != null) {
							risk = 3;
						}
					}

					String[] eqParam = { "diagnosisId.diagnosisId",
							"deletedStatus", "treatmentRiskId.treatmentRiskId",
							"providerTypeId.providerTypeId" };
					Object[] eqValue = {
							object.getDiagnosis1Id().getDiagnosisId(),
							Integer.valueOf(0), Integer.valueOf(risk),
							Integer.valueOf(1) };

					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = inaCBGService
							.search(null, null, eqParam, eqValue, 0, 1);

					if (inaCBGList != null && inaCBGList.size() > 0) {
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList
								.iterator();

						if (iterator.hasNext()) {
							ProviderTypeDiagnosisTreatment inaCBG = iterator
									.next();

							result.addObject("inaCbgRef", inaCBG);
						}
					}
				}
			}

			result.addObject("myCase", object);

			// procedure, medicine, benefit

			if (object != null) {

				SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

				String birthday = "";
				String caseDate = "";
				String tanggalSurat = "";

				birthday = format.format(object.getMemberId().getBirthday());
				caseDate = format.format(object.getCaseStartTime());
				tanggalSurat = format.format(object.getCaseStartTime());

				result.addObject("caseDate", caseDate);
				result.addObject("birthdate", birthday);

			}
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView jsonTotalPendingCasePerformed(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView result = null;

		try {

			result = new ModelAndView("jsonTotalPendingCase");

			String navigation = request.getParameter("navigation");
			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}
			int total = 0;

			if (navigation.equalsIgnoreCase("jsontotalopencase")) {
				total = myCaseService.getTotalMonitorCase();
			} else if (navigation.equalsIgnoreCase("jsontotalexpirecase")) {
				total = myCaseService.getTotalExpireCase();
			} else if (navigation.equalsIgnoreCase("jsontotalclosingcase")) {
				total = myCaseService.getTotalClosingCase();
			} else if (navigation.equalsIgnoreCase("jsontotalnewcase")) {
				total = myCaseService.getTotalNewCase();
			} else if (navigation
					.equalsIgnoreCase("jsontotalcaseinvestigation")) {
				total = myCaseService.getTotalCaseInvestigation();
			}
			// Add by aju on 20150804, for COB :D
			else if (navigation.equalsIgnoreCase("jsontotalnewcobcase")) {
				total = myCaseService.getTotalNewCOBCase();
				// System.out.println("Total Open COB Case : " + total);
			} else if (navigation.equalsIgnoreCase("jsontotaledccase")) {
				String[] eqParam = { "deletedStatus",
						"caseStatusId.caseStatusId", "caseType" };
				Object[] eqValue = { 0, CaseStatus.CASE_OPEN, Case.CASE_EDC };

				total = myCaseService.getTotal(null, null, eqParam, eqValue);
			}

			result.addObject("result", total);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView printClosingLetterPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;

			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			result = new ModelAndView(location);

			java.io.Serializable pkey = caseId;
			String[] required = { "Case.MemberId",
					"Case.MemberId.MemberGroupId", "Case.MemberId.ClientId",
					"Case.MemberId.ParentMember" };
			Case object = myCaseService.get(pkey, required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.case", null, "fail", Locale.getDefault()));
			} else {
				result.addObject("member", object.getMemberId());
				MemberProduct product = memberProductService
						.getMemberActiveProduct(object.getMemberId()
								.getMemberId(), CaseCategory.INPATIENT);

				if (product != null) {
					result.addObject("benefit", "IP "
							+ product.getProductId().getServiceClass());
				}
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String tanggal = df.format(object.getCaseStartTime());
				result.addObject("tanggal", tanggal);

			}

			result.addObject("myCase", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView verifyMemberPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCASE");

			if (!isUserAuthorized) {
				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCASE access");
				return errorResult;
			}

			String memberNumber = WebUtil.getParameterString(request,
					"memberNumber", "");
			String referalCode = WebUtil.getParameterString(request,
					"referNumber", "");
			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			result = new ModelAndView(location);

			if (navigation.equalsIgnoreCase("docheckmember")) {
				Member member = memberService
						.getMemberByCardNumber(memberNumber);

				result.addObject("memberBean", member);

				if (member != null) {
					result = new ModelAndView(new RedirectView(
							"register-form?memberId=" + member.getMemberId()
									+ "&caseCategoryId=" + caseCategoryId));
				} else {

				}
			} else {

				if (user != null
						&& user.getUser().getUserType().intValue() == User.PROVIDER) {
					Collection<CaseCategory> serviceList = new Vector<CaseCategory>();

					String[] eqParam = { "deletedStatus",
							"providerId.providerId" };
					Object[] eqValue = { 0,
							user.getUser().getProviderId().getProviderId() };

					int total = providerServiceService.getTotal(null, null,
							eqParam, eqValue);

					Collection<com.ametis.cms.datamodel.ProviderService> providerServiceList = providerServiceService
							.search(null, null, eqParam, eqValue, 0, total);

					if (providerServiceList != null) {
						for (Iterator iterator = providerServiceList.iterator(); iterator
								.hasNext();) {

							com.ametis.cms.datamodel.ProviderService provService = (com.ametis.cms.datamodel.ProviderService) iterator
									.next();

							if (provService != null) {
								serviceList
										.add(provService.getCaseCategoryId());
							}
						}
					}

					result.addObject("caseCategoryList", serviceList);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView printCostReportPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"PRINTCOST");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for PRINTCOST access");
				return errorResult;

			}

			String location = "printCostReport";

			Configuration configuration = configurationService
					.getSystemConfiguration();

			location = location + "_"
					+ configuration.getCompanyCode().toLowerCase();

			// Jika Nama Perusahaan tidak ada
			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/jsp/myCase/");
			File file = new File(path, location + ".jsp");
			if (!file.exists()) {
				location = "printCostReport";
			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			result = new ModelAndView(location);
			java.io.Serializable pkey = caseId;

			String[] required = { "Case.ClaimId", "Case.MemberId",
					"Case.MemberId.MemberGroupId", "Case.ClaimId.DiagnosisId" };
			Case object = myCaseService.get(pkey, required);

			Collection<ClaimItem> claimItems = claimItemService
					.getClaimItem(object.getClaimId().getClaimId());

			String currentTime = DateFormat.getDateTimeInstance(
					DateFormat.MEDIUM, DateFormat.SHORT).format(
					new java.sql.Date(System.currentTimeMillis()));

			result.addObject("currentTime", currentTime);
			result.addObject("configuration", configuration);
			result.addObject("claimItems", claimItems);
			result.addObject("myCase", object);
			result.addObject("claim", object.getClaimId());

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			// Add by aju on 20150929, for handle iFrame things fufufu
			if (isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")) {
				/**
				System.out.println("it\'s still on member(" + sessionMemberId
						+ ") login session fufufu..."); */
				
				memberId = (memberId == null ? Integer
						.parseInt(sessionMemberId) : memberId);
				Member theMember = memberService.get(memberId);
				if (theMember != null) {
					/**
					System.out.println("checking->requestParentMemberId("
							+ theMember.getParentMember().getMemberId()
									.toString() + ") vs sessionParentMemberId("
							+ sessionMemberParentId + ")");
					*/
					if (!theMember.getParentMember().getMemberId().toString()
							.equals(sessionMemberParentId)) {
						memberId = Integer.parseInt(sessionMemberId);
						ModelAndView errorResult = new ModelAndView(
								new RedirectView("errorpage"));
						errorResult.addObject("errorType",
								"OtherFamilyAccessDenied");
						errorResult
								.addObject(
										"errorMessage",
										"Hey member("
												+ sessionMemberId
												+ "), are you missing your way? :P<br/>"
												+ "<a href=\"case?navigation=listmember&memberId="
												+ memberId + "\">Go Back</a>");
						return errorResult;
					}
				}
			}

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			System.out.println("searchStatus :" +searchStatus);
			// begin aulia
			String[] searchStatusPassJSP = WebUtil.getParameterStringArray(
					request, "status");
			Integer[] searchStatusMulti = null;
			if (searchStatusPassJSP.length > 1) {
				searchStatusMulti = new Integer[searchStatusPassJSP.length];
				for (int i = 0; i < searchStatusPassJSP.length; i++) {
					searchStatusMulti[i] = Integer
							.parseInt(searchStatusPassJSP[i]);
				}
			}
			// end aulia
			// begin aulia
			String[] caseCategoryIdPassJSP = WebUtil.getParameterStringArray(
					request, "caseCategoryId");
			Integer[] caseCategoryIdMulti = null;
			if (caseCategoryIdPassJSP.length > 1) {
				caseCategoryIdMulti = new Integer[caseCategoryIdPassJSP.length];
				for (int i = 0; i < caseCategoryIdPassJSP.length; i++) {
					caseCategoryIdMulti[i] = Integer
							.parseInt(caseCategoryIdPassJSP[i]);
				}
			}
			// end aulia

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			String providerText = WebUtil.getParameterString(request,
					"providerText", "");
			Integer refreshTimerSet = WebUtil.getParameterInteger(request,
					"refreshTimerSet");

			// Add by aju on 20150420, for user setting View List Max Rows per
			// page :D
			Integer viewCountSet = WebUtil.getParameterInteger(request,
					"viewCountSet");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");
			String arah = WebUtil.getParameterString(request, "arah", "");

			// SORTING ADD 03102015
			String sortcolumn = WebUtil.getParameterString(request,
					"sortcolumn", "");
			boolean sortCaseNumber = true, sortMemberName = true, sortProvider = true, sortAdmissionDate = true, sortDischargeDate = true, sortType = true, sortLimit = true, sortDiagnosis = true, sortCharge = true, sortAprrove = true, sortExcess = true, sortStatus = true, sortModifiedBy = true, sortCreatedTime = true, sortModifiedTime = true, sortClient = true, sortGroup = true, sortLongOfStay = true, sortAssignee = true, sortInaCBGAlos = true, sortInaCBGRate = true;
			boolean order = true;

			Collection caseIds = null;

			if (currentNavigation.equalsIgnoreCase("listmember")) {
				location = "listMemberCase";
			} else if (currentNavigation.equalsIgnoreCase("listgroup")) {
				location = "listGroupCase";
			} else if (currentNavigation.equalsIgnoreCase("listprovider")) {
				location = "listProviderCase";
			} else if (currentNavigation.equalsIgnoreCase("listpolicy")) {
				location = "listPolicyCase";
			} else if (currentNavigation.equalsIgnoreCase("list")) {
				location = "listCase";
			}
			if (user.getUser().getUserType().intValue() == User.PROVIDER) {
				location = "searchCaseProvider";
			}
			if (navigation.equalsIgnoreCase("mycase")
					|| navigation.equalsIgnoreCase("gosearchmycase")) {
				location = "searchAssignedCase";
			}

			result = new ModelAndView(location);

			result.addObject("subnavigation", subnavigation);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			// Vector vInP = new Vector();
			Vector vInQStatus = new Vector();
			Vector vInQCat = new Vector();

			// CHECKING SORTING COLUMN
			if ((!navigation.equalsIgnoreCase("gosearchbysort")
					&& arah.isEmpty() && arah.equals(""))
					|| navigation.equals("gosearch")
					|| navigation.equals("downloadExcel")) {
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}

			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equalsIgnoreCase("golist")
					|| navigation.equals("search")
					|| navigation.equals("gosearchbysort")
					|| (navigation.equals("") && !arah.isEmpty())
					|| navigation.equals("downloadExcel")
					|| navigation.equalsIgnoreCase("gosearchmycase")
					|| navigation.equalsIgnoreCase("listmember")
					|| navigation.equalsIgnoreCase("listgroup")
					|| navigation.equalsIgnoreCase("listpolicy")
					|| navigation.equalsIgnoreCase("list")) {

				if (searchby != null && searchtext != null
						&& !searchtext.equals("")) {

					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")) {
						vLikeP.add("memberId.currentCardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("memberId.clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("physician")) {
						vLikeP.add("physician");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosis1Id.diagnosisName");
						vLikeQ.add(searchtext);
					}
					// Penambahan Kriteria Search untuk Case Category
					// if(searchby.equals("category")){
					// vLikeP.add("caseCategoryId.caseCategoryName");
					// vLikeQ.add(searchtext);
					// }

				}

			}

			if (searchStatusMulti == null) {
				// menghilangkan conditional yang besar dari nol, karena
				// sebelumnya menggunakan single select pada page jsp, by Aulia
				// R. 1-6-2015
				// if (searchStatus != null && searchStatus.intValue() > 0) {
				if (searchStatus != null) {
					vEqP.add("caseStatusId.caseStatusId");
					vEqQ.add(searchStatus);
				}
			} else {
				if (searchStatusMulti != null) {

					Integer searchStatus2 = null;
					for (int i = 0; i < searchStatusMulti.length; i++) {
						searchStatus2 = Integer
								.parseInt(searchStatusPassJSP[i]);
						vInQStatus.add(searchStatus2);
					}
				}

				result.addObject("statusMulti", searchStatusPassJSP);
			}

			if (providerId != null && !providerText.equalsIgnoreCase("")) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			}
			result.addObject("providerText", providerText);

			if (caseCategoryIdMulti == null) {
				result.addObject("caseCategoryId", caseCategoryId);
				result.addObject("caseCategoryIdSingle", caseCategoryId+"");
			} else {
				result.addObject("caseCategoryIdMulti", caseCategoryIdPassJSP);
			}
			result.addObject("memberId", memberId);
			result.addObject("claimId", claimId);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);
			result.addObject("policyId", policyId);
			result.addObject("searchby", searchby);

			if (caseCategoryIdMulti == null) {
				if (caseCategoryId != null) {
					vEqP.add("caseCategoryId.caseCategoryId");
					vEqQ.add(caseCategoryId);
				}
			} else {
				if (caseCategoryIdMulti != null) {
					// vEqP.add("caseCategoryId.caseCategoryId");

					Integer caseCategoryId2 = null;
					for (int i = 0; i < caseCategoryIdMulti.length; i++) {
						caseCategoryId2 = Integer
								.parseInt(caseCategoryIdPassJSP[i]);
						vInQCat.add(caseCategoryId2);
					}
				}
			}

			if (url.equalsIgnoreCase("claim-form")) {
				vEqP.add("memberId.memberId");
				vEqQ.add(memberId);
			}
			if (navigation.equalsIgnoreCase("listmember")
					|| navigation.equalsIgnoreCase("list")
					|| currentNavigation.equalsIgnoreCase("listmember")
					|| currentNavigation.equalsIgnoreCase("list")) {
				vEqP.add("memberId.memberId");
				vEqQ.add(memberId);

				Member memberBean = memberService.get(memberId);

				if (memberBean != null) {
					String name = memberBean.getFirstName() + " "
							+ memberBean.getLastName();
					result.addObject("name", name);
				}

			} else if (navigation.equalsIgnoreCase("listprovider")
					|| currentNavigation.equalsIgnoreCase("listprovider")) {

				Provider providerBean = providerService.get(providerId);

				if (providerBean != null) {
					String name = providerBean.getProviderName();
					result.addObject("name", name);
				}
			} else if (navigation.equalsIgnoreCase("listgroup")
					|| currentNavigation.equalsIgnoreCase("listgroup")) {
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);

			} else if (navigation.equalsIgnoreCase("listpolicy")
					|| currentNavigation.equalsIgnoreCase("listpolicy")) {
				vEqP.add("policyId.policyId");
				vEqQ.add(policyId);
			} else if (navigation.equalsIgnoreCase("mycase")
					|| navigation.equalsIgnoreCase("gosearchmycase")) {

				vEqP.add("assigneeId.userId");
				vEqQ.add(user.getUser().getUserId());
			} else {

			}
			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberId.memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {
					vEqP.add("memberId.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}

			}

			if (navigation.equalsIgnoreCase("searchmonitor")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_APPROVED);
				vEqP.add("isExpired");
				vEqQ.add(Integer.valueOf(0));
			} else if (navigation.equalsIgnoreCase("searchopen")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_OPEN);
			}
			// Add by aju on 20150805, for COB
			else if (navigation.equalsIgnoreCase("searchopencob")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_OPEN);
				vEqP.add("isLinkedMember");
				vEqQ.add(Integer.valueOf(1));
			} else if (navigation.equalsIgnoreCase("searchexpire")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_APPROVED);
				vEqP.add("isExpired");
				vEqQ.add(Integer.valueOf(1));
			} else if (navigation.equalsIgnoreCase("searchclosed")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_CLOSED);
			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			Object sInQStatus[] = new Object[vInQStatus.size()];
			vInQStatus.toArray(sInQStatus);
			Object sInQCat[] = new Object[vInQCat.size()];
			vInQCat.toArray(sInQCat);

			if (caseCategoryIdMulti != null && searchStatusMulti != null
					|| caseCategoryIdMulti == null && searchStatusMulti != null
					|| caseCategoryIdMulti != null && searchStatusMulti == null) {
				count = myCaseService.getTotalMultiCase(sLikeP, sLikeQ, sEqP,
						sEqQ, sInQStatus, sInQCat);
			} else {
				if (navigation.equalsIgnoreCase("mycaseinvestigation")) {
					System.out.println("MASUK COUNT");
					caseIds = caseInvestigationService.getTotalCaseId();
					System.out.println("CASE ID SIZE : " + caseIds.size());
					Object ids[] = new Object[caseIds.size()];
					caseIds.toArray(ids);
					System.out.println("ID : " + ids[0]);
					count = myCaseService.getTotalCaseInvestigation(sLikeP,
							sLikeQ, sEqP, sEqQ, ids);
					System.out.println("NILAI COUNT : " + count);
				} else {
					count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				}
			}

			// Add by aju on 20150420, for user View List Max Rows per page :D
			if (viewCountSet != null) {
				if (viewCountSet.intValue() > 0) {
					countSet = viewCountSet.intValue();
					maxPercountSet = viewCountSet.intValue();
				}
			}

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId", "Case.ClaimId",
					"Case.Description",
			// foreign affairs end
			};

			// SORTING START
			String sortByColumn = new String();
			if (sortcolumn != null && !sortcolumn.equals("")) {
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(
						request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request,
						"columntosort", "");
				if (navigation.equals("gosearchbysort")) {
					if (sortcolumn.equals("casenumber")) {
						sortByColumn = "caseNumber";
						Boolean sortByOrderCaseNo = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderCaseNo", ""));
						sortCaseNumber = !sortByOrderCaseNo;
						order = sortCaseNumber;
					} else if (sortcolumn.equals("membername")) {
						sortByColumn = "memberId.firstName";
						Boolean sortByOrderMember = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderMember", ""));
						sortMemberName = !sortByOrderMember;
						order = sortMemberName;
					} else if (sortcolumn.equals("providername")) {
						if (providerId != null
								&& !providerText.equalsIgnoreCase("")) {
							sortByColumn = "providerId";
						} else {
							sortByColumn = "providerId.providerName";
						}
						Boolean sortByOrderProvider = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderProvider", ""));
						sortProvider = !sortByOrderProvider;
						order = sortProvider;
					} else if (sortcolumn.equals("admissiondate")) {
						sortByColumn = "caseStartTime";
						Boolean sortByOrderAdmission = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderAdmission", ""));
						sortAdmissionDate = !sortByOrderAdmission;
						order = sortAdmissionDate;
					} else if (sortcolumn.equals("dischargedate")) {
						sortByColumn = "caseEndTime";
						Boolean sortByOrderDischarge = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderDischarge", ""));
						sortDischargeDate = !sortByOrderDischarge;
						order = sortDischargeDate;
					} else if (sortcolumn.equals("type")) {
						sortByColumn = "caseCategoryId";
						Boolean sortByOrderType = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortByOrderType",
										""));
						sortType = !sortByOrderType;
						order = sortType;
					} else if (sortcolumn.equals("longofstay")) {
						sortByColumn = "longOfStay";
						Boolean sortByOrderLongOfStay = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderLongOfStay", ""));
						sortLongOfStay = !sortByOrderLongOfStay;
						order = sortLongOfStay;
					} else if (sortcolumn.equals("limit")) {
						sortByColumn = "preRemainingLimit";
						Boolean sortByOrderLimit = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderLimit", ""));
						sortLimit = !sortByOrderLimit;
						order = sortLimit;
					} else if (sortcolumn.equals("diagnosis")) {
						sortByColumn = "diagnosis1Id.diagnosisCode";
						Boolean sortByOrderDiagnosis = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderDiagnosis", ""));
						sortDiagnosis = !sortByOrderDiagnosis;
						order = sortDiagnosis;
					} else if (sortcolumn.equals("charge")) {
						sortByColumn = "caseClaimValue";
						Boolean sortByOrderCharge = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderCharge", ""));
						sortCharge = !sortByOrderCharge;
						order = sortCharge;
					} else if (sortcolumn.equals("approve")) {
						sortByColumn = "caseApprovedValue";
						Boolean sortByOrderApprove = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderApprove", ""));
						sortAprrove = !sortByOrderApprove;
						order = sortAprrove;
					} else if (sortcolumn.equals("excess")) {
						sortByColumn = "caseExcessValue";
						Boolean sortByOrderExcess = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderExcess", ""));
						sortExcess = !sortByOrderExcess;
						order = sortExcess;
					} else if (sortcolumn.equals("status")) {
						if (searchStatus != null && searchStatus.intValue() > 0) {
							sortByColumn = "caseStatusId";
						} else {
							sortByColumn = "caseStatusId.caseStatusName";
						}
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					} else if (sortcolumn.equals("modifiedby")) {
						sortByColumn = "modifiedBy";
						Boolean sortByOrderModifiedBy = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderModifiedBy", ""));
						sortModifiedBy = !sortByOrderModifiedBy;
						order = sortModifiedBy;
					} else if (sortcolumn.equals("createdtime")) {
						sortByColumn = "createdTime";
						Boolean sortByOrderCreatedTime = Boolean
								.valueOf(WebUtil.getParameterString(request,
										"sortByOrderCreatedTime", ""));
						sortCreatedTime = !sortByOrderCreatedTime;
						order = sortCreatedTime;
					} else if (sortcolumn.equals("modifiedtime")) {
						sortByColumn = "modifiedTime";
						Boolean sortByOrderModifTime = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderModifTime", ""));
						sortModifiedTime = !sortByOrderModifTime;
						order = sortModifiedTime;
					} else if (sortcolumn.equalsIgnoreCase("clientname")) {
						sortByColumn = "memberId.clientName";
						Boolean sortByOrderClient = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderClient", ""));
						sortClient = !sortByOrderClient;
						order = sortClient;
					} else if (sortcolumn.equalsIgnoreCase("assignee")) {
						sortByColumn = "assigneeId.firstName";
						Boolean sortByOrderAssignee = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderAssignee", ""));
						sortAssignee = !sortByOrderAssignee;
						order = sortAssignee;
					} else if (sortcolumn.equalsIgnoreCase("inacbgrate")) {
						sortByColumn = "inaCbgTreatmentRate";
						Boolean sortByOrderInaCBGRate = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderInaCBGRate", ""));
						sortInaCBGRate = !sortByOrderInaCBGRate;
						order = sortInaCBGRate;
					} else if (sortcolumn.equalsIgnoreCase("inacbgalos")) {
						sortByColumn = "inaCbgAlos";
						Boolean sortByOrderInaCBGAlos = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderInaCBGAlos", ""));
						sortInaCBGAlos = !sortByOrderInaCBGAlos;
						order = sortInaCBGAlos;
					} else {
						sortByColumn = "memberId.groupName";
						Boolean sortByOrderGroup = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortByOrderGroup", ""));
						sortGroup = !sortByOrderGroup;
						order = sortGroup;
					}
					columntosort = sortByColumn;
				} else {
					sortByColumn = columntosort;
					order = sortOrder;
					if (sortcolumn.equals("casenumber")) {
						sortCaseNumber = order;
					} else if (sortcolumn.equals("membername")) {
						sortMemberName = order;
					} else if (sortcolumn.equals("providername")) {
						sortProvider = order;
					} else if (sortcolumn.equals("admissiondate")) {
						sortAdmissionDate = order;
					} else if (sortcolumn.equals("dischargedate")) {
						sortDischargeDate = order;
					} else if (sortcolumn.equals("type")) {
						sortType = order;
					} else if (sortcolumn.equals("longofstay")) {
						sortLongOfStay = order;
					} else if (sortcolumn.equals("limit")) {
						sortLimit = order;
					} else if (sortcolumn.equals("diagnosis")) {
						sortDiagnosis = order;
					} else if (sortcolumn.equals("charge")) {
						sortCharge = order;
					} else if (sortcolumn.equals("approve")) {
						sortAprrove = order;
					} else if (sortcolumn.equals("excess")) {
						sortExcess = order;
					} else if (sortcolumn.equals("status")) {
						sortStatus = order;
					} else if (sortcolumn.equals("modifiedby")) {
						sortModifiedBy = order;
					} else if (sortcolumn.equals("createdtime")) {
						sortCreatedTime = order;
					} else if (sortcolumn.equals("modifiedtime")) {
						sortModifiedTime = order;
					} else if (sortcolumn.equalsIgnoreCase("clientname")) {
						sortClient = order;
					} else if (sortcolumn.equalsIgnoreCase("assignee")) {
						sortAssignee = order;
					} else if (sortcolumn.equalsIgnoreCase("inacbgrate")) {
						sortInaCBGRate = order;
					} else if (sortcolumn.equalsIgnoreCase("inacbgalos")) {
						sortInaCBGAlos = order;
					} else {
						sortGroup = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if (caseCategoryIdMulti != null && searchStatusMulti != null
						|| caseCategoryIdMulti == null
						&& searchStatusMulti != null
						|| caseCategoryIdMulti != null
						&& searchStatusMulti == null) {

					collection = myCaseService.searchMultiCase(sLikeP, sLikeQ,
							sEqP, sEqQ, sInQStatus, sInQCat, order,
							sortByColumn, required, rowsetint,
							countSet.intValue());
				} else {
					if (navigation.equalsIgnoreCase("mycaseinvestigation")) {
						Object ids[] = new Object[caseIds.size()];
						caseIds.toArray(ids);
						collection = myCaseService.searchCaseInvestigation(
								sLikeP, sLikeQ, sEqP, sEqQ, ids, order,
								sortByColumn, required, rowsetint,
								countSet.intValue());
					} else {
						collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
								sEqQ, order, sortByColumn, required, rowsetint,
								countSet.intValue());
					}
				}
				
			} else {
				if (caseCategoryIdMulti != null && searchStatusMulti != null
						|| caseCategoryIdMulti == null
						&& searchStatusMulti != null
						|| caseCategoryIdMulti != null
						&& searchStatusMulti == null) {

					collection = myCaseService.searchMultiCase(sLikeP, sLikeQ,
							sEqP, sEqQ, sInQStatus, sInQCat, false, "caseId",
							required, rowsetint, countSet.intValue());
				} else {
					if (navigation.equalsIgnoreCase("mycaseinvestigation")) {
						Object ids[] = new Object[caseIds.size()];
						caseIds.toArray(ids);
						collection = myCaseService.searchCaseInvestigation(
								sLikeP, sLikeQ, sEqP, sEqQ, ids, false,
								"caseId", required, rowsetint,
								countSet.intValue());
					} else {
						System.out.println("INDEX : "+rowsetint);
						System.out.println("OFFSET : "+countSet.intValue());
						collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
								sEqQ, false, "caseId", required, rowsetint,
								countSet.intValue());
					}
				}

			}

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				/*
				 * collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
				 * sEqQ,false,"caseId", required, rowsetint,
				 * countSet.intValue());
				 */

				if (caseCategoryIdMulti != null && searchStatusMulti != null
						|| caseCategoryIdMulti == null
						&& searchStatusMulti != null
						|| caseCategoryIdMulti != null
						&& searchStatusMulti == null) {

					collection = myCaseService.searchMultiCase(sLikeP, sLikeQ,
							sEqP, sEqQ, sInQStatus, sInQCat, false, "caseId",
							required, rowsetint, countSet.intValue());
				} else {
					collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, false, "caseId", required, rowsetint,
							countSet.intValue());
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			if (memberId != null) {
				String[] requiredMember = { "Member.MemberGroupId",
						"Member.ClientId", "Member.RelationshipId",
						"Member.ParentMember", "Member.CurrentPolicyId" };
				Member object = memberService.get(memberId, requiredMember);
				result.addObject("member", object);

				// hitung umur
				Date umur = object.getBirthday();
				Calendar dob = Calendar.getInstance();
				dob.setTime(umur);
				Calendar today = Calendar.getInstance();
				int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
				if ((today.get(Calendar.MONTH) == dob.get(Calendar.MONTH) && today
						.get(Calendar.DAY_OF_MONTH) < dob
						.get(Calendar.DAY_OF_MONTH))
						|| today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
					age--;
				}
				result.addObject("age", age);
				// end
			}

			MemberGroup memberGroupObject = null;

			if (memberGroupId != null) {
				try {
					java.io.Serializable memberGrouppkey = memberGroupId;
					String[] memberGroupRequired = { "MemberGroup.ClientId",
							"MemberGroup.BusinessCategoryId" };
					memberGroupObject = memberGroupService.get(memberGrouppkey,
							memberGroupRequired);

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			Policy policyObject = null;

			if (policyId != null) {
				try {
					java.io.Serializable policypkey = policyId;
					System.out.println("member policy id : " + policyId);
					String[] policyRequired = { "Policy.BrokerId",
							"Policy.ClientId", "Policy.ProductType",
							"Policy.CardTypeId" };
					policyObject = policyService
							.get(policypkey, policyRequired);

				} catch (Exception ex) {
					// System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			Claim claimObject = null;

			if (claimId != null) {
				try {
					java.io.Serializable claimkey = claimId;
					System.out.println("claim id : " + claimId);
					String[] claimRequired = { "Claim.BatchClaimId",
							"Claim.MemberId", "Claim.CaseCategoryId",
							"Claim.MemberId.MemberGroupId", "Claim.CaseId",
							"Claim.DiagnosisId", "Claim.Diagnosis2Id",
							"Claim.Diagnosis3Id", "Claim.CobClaimReferenceId",
							"Claim.BatchClaimId.ClaimCurrency" };
					claimObject = claimService.get(claimkey, claimRequired);

				} catch (Exception ex) {
					// System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			if (navigation.equals("downloadExcel")) {

				collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						false, "caseId", required, 0, count);

				HSSFWorkbook workbook = CaseReportGenerator
						.generateReport(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "casereport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				sos.close();
			}

			result.addObject("policy", policyObject);
			result.addObject("Cases", collection);
			result.addObject("memberGroup", memberGroupObject);
			result.addObject("claim", claimObject);

			System.out.println("searchStatus 2 :" +searchStatus);
			request.setAttribute("status", searchStatus);
			result.addObject("statusSingle", searchStatus+"");
			
			// Add by aju on 20150420, for user View List Max Rows per page :D
			if (viewCountSet != null) {
				if (viewCountSet.intValue() > 0) {
					// make it back to default after filtering the max rows
					// showed :D
					// check the countSet and maxPercountSet default value on
					// applicationContext.xml :D
					countSet = 15;
					maxPercountSet = 15;
				}
				request.setAttribute("viewCountSet", viewCountSet);
			}

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("refreshTimerSet", refreshTimerSet);

			request.setAttribute("sortAdmissionDate", sortAdmissionDate);
			request.setAttribute("sortAprrove", sortAprrove);
			request.setAttribute("sortCaseNumber", sortCaseNumber);
			request.setAttribute("sortCharge", sortCharge);
			request.setAttribute("sortDiagnosis", sortDiagnosis);
			request.setAttribute("sortDischargeDate", sortDischargeDate);
			request.setAttribute("sortExcess", sortExcess);
			request.setAttribute("sortLimit", sortLimit);
			request.setAttribute("sortMemberName", sortMemberName);
			request.setAttribute("sortModifiedBy", sortModifiedBy);
			request.setAttribute("sortCreatedTime", sortCreatedTime);
			request.setAttribute("sortModifiedTime", sortModifiedTime);
			request.setAttribute("sortProvider", sortProvider);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortType", sortType);
			request.setAttribute("sortClient", sortClient);
			request.setAttribute("sortGroup", sortGroup);
			request.setAttribute("sortLongOfStay", sortLongOfStay);
			request.setAttribute("sortAssignee", sortAssignee);
			request.setAttribute("sortInaCBGRate", sortInaCBGRate);
			request.setAttribute("sortInaCBGAlos", sortInaCBGAlos);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchReferencePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			String clientName = WebUtil.getParameterString(request,
					"clientName", "");

			String diagnosisName = WebUtil.getParameterString(request,
					"diagnosisName", "");

			String groupName = WebUtil.getParameterString(request, "groupName",
					"");

			String serviceName = WebUtil.getParameterString(request,
					"serviceName", "");

			String providerName = WebUtil.getParameterString(request,
					"providerName", "");

			Integer countryId = WebUtil.getParameterInteger(request,
					"countryId");

			String countryName = WebUtil.getParameterString(request,
					"countryName", "");

			Integer provinceId = WebUtil.getParameterInteger(request,
					"provinceId");

			String provinceName = WebUtil.getParameterString(request,
					"provinceName", "");

			Integer cityId = WebUtil.getParameterInteger(request, "cityId");

			String cityName = WebUtil.getParameterString(request, "cityName",
					"");

			// Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			// Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

			String minDateString = request.getParameter("minimum_date");
			Date minDate = TimeUtils.parseDate(minDateString);

			String maxDateString = request.getParameter("maximum_date");
			Date maxDate = TimeUtils.parseDate(maxDateString);

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			String dateBy = WebUtil.getParameterString(request, "dateBy", "");

			// Add by aju on 20150420, for user setting View List Max Rows per
			// page :D
			Integer viewCountSet = WebUtil.getParameterInteger(request,
					"viewCountSet");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");
			String arah = WebUtil.getParameterString(request, "arah", "");

			/*
			 * //SORTING ADD 03102015 String sortcolumn =
			 * WebUtil.getParameterString(request, "sortcolumn", ""); boolean
			 * sortAvarageCost = true, sortMaxCost = true, sortMinCost = true,
			 * sortAvarageLOS = true, sortDiagnosis = true; boolean order =
			 * true;
			 * 
			 * if (currentNavigation.equalsIgnoreCase("listmember")){ location =
			 * "listMemberCase"; } else if
			 * (currentNavigation.equalsIgnoreCase("listgroup")){ location =
			 * "listGroupCase"; } else if
			 * (currentNavigation.equalsIgnoreCase("listprovider")){ location =
			 * "listProviderCase"; } else if
			 * (currentNavigation.equalsIgnoreCase("listpolicy")){ location =
			 * "listPolicyCase"; } else if
			 * (currentNavigation.equalsIgnoreCase("list")){ location =
			 * "listCase"; } if (user.getUser().getUserType().intValue() ==
			 * User.PROVIDER){ location = "searchCaseProvider"; } if
			 * (navigation.equalsIgnoreCase("mycase") ||
			 * navigation.equalsIgnoreCase("gosearchmycase")){ location =
			 * "searchAssignedCase"; }
			 */

			result = new ModelAndView(location);

			result.addObject("subnavigation", subnavigation);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection<Object[]> collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			/*
			 * Vector vLikeP = new Vector(); Vector vLikeQ = new Vector();
			 * Vector vEqP = new Vector(); Vector vEqQ = new Vector(); //Vector
			 * vInP = new Vector(); Vector vInQStatus = new Vector(); Vector
			 * vInQCat = new Vector();
			 * 
			 * 
			 * //CHECKING SORTING COLUMN
			 * if((!navigation.equalsIgnoreCase("gosearchbysortReference") &&
			 * arah.isEmpty() && arah.equals("")) ||
			 * navigation.equals("gosearchReference") ||
			 * navigation.equals("downloadExcelReference")){ sortcolumn = "";
			 * request.setAttribute("sortorder", "");
			 * request.setAttribute("columntosort", ""); }
			 */

			result.addObject("clientName", clientName);
			result.addObject("diagnosisName", diagnosisName);
			result.addObject("groupName", groupName);
			result.addObject("serviceName", serviceName);
			result.addObject("countryId", countryId);
			result.addObject("providerName", providerName);
			result.addObject("provinceId", provinceId);
			result.addObject("cityId", cityId);
			result.addObject("maxDate", maxDate);
			result.addObject("claimId", claimId);
			result.addObject("minDate", minDate);
			result.addObject("dateBy", dateBy);
			result.addObject("countryName", countryName);
			result.addObject("cityName", cityName);
			result.addObject("provinceName", provinceName);

			result.addObject("memberId", memberId);
			result.addObject("claimId", claimId);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);
			result.addObject("policyId", policyId);

			/*
			 * if (url.equalsIgnoreCase("claim-form")){
			 * vEqP.add("memberId.memberId"); vEqQ.add(memberId); } if
			 * (navigation.equalsIgnoreCase("listmember") ||
			 * navigation.equalsIgnoreCase("list") ||
			 * currentNavigation.equalsIgnoreCase("listmember") ||
			 * currentNavigation.equalsIgnoreCase("list")) {
			 * vEqP.add("memberId.memberId"); vEqQ.add(memberId);
			 * 
			 * Member memberBean = memberService.get(memberId);
			 * 
			 * if (memberBean != null) { String name = memberBean.getFirstName()
			 * + " " + memberBean.getLastName(); result.addObject("name", name);
			 * }
			 * 
			 * } else if (navigation.equalsIgnoreCase("listprovider") ||
			 * currentNavigation.equalsIgnoreCase("listprovider") ) {
			 * 
			 * 
			 * 
			 * Provider providerBean = providerService.get(providerId);
			 * 
			 * if (providerBean != null) { String name =
			 * providerBean.getProviderName(); result.addObject("name", name); }
			 * } else if (navigation.equalsIgnoreCase("listgroup") ||
			 * currentNavigation.equalsIgnoreCase("listgroup")){
			 * vEqP.add("memberId.memberGroupId.memberGroupId");
			 * vEqQ.add(memberGroupId);
			 * 
			 * } else if (navigation.equalsIgnoreCase("listpolicy") ||
			 * currentNavigation.equalsIgnoreCase("listpolicy")){
			 * vEqP.add("policyId.policyId"); vEqQ.add(policyId); } else if
			 * (navigation.equalsIgnoreCase("mycase") ||
			 * navigation.equalsIgnoreCase("gosearchmycase")){
			 * 
			 * vEqP.add("assigneeId.userId");
			 * vEqQ.add(user.getUser().getUserId()); } else {
			 * 
			 * if (user != null && user.getUser().getUserType() != null){ if
			 * (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
			 * vEqP.add("memberId.memberGroupId.memberGroupId");
			 * vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId()); }
			 * else if (user.getUser().getUserType().intValue() == User.CLIENT){
			 * vEqP.add("memberId.clientId.clientId");
			 * vEqQ.add(user.getUser().getClientId().getClientId()); } else if
			 * (user.getUser().getUserType().intValue() == User.PROVIDER){
			 * vEqP.add("providerId.providerId");
			 * vEqQ.add(user.getUser().getProviderId().getProviderId()); }
			 * 
			 * } }
			 * 
			 * if (navigation.equalsIgnoreCase("searchmonitor")){
			 * vEqP.add("caseStatusId.caseStatusId");
			 * vEqQ.add(Case.CASE_APPROVED); vEqP.add("isExpired");
			 * vEqQ.add(Integer.valueOf(0)); } else if
			 * (navigation.equalsIgnoreCase("searchopen")){
			 * vEqP.add("caseStatusId.caseStatusId"); vEqQ.add(Case.CASE_OPEN);
			 * } else if (navigation.equalsIgnoreCase("searchexpire")){
			 * vEqP.add("caseStatusId.caseStatusId");
			 * vEqQ.add(Case.CASE_APPROVED); vEqP.add("isExpired");
			 * vEqQ.add(Integer.valueOf(1)); } else if
			 * (navigation.equalsIgnoreCase("searchclosed")){
			 * vEqP.add("caseStatusId.caseStatusId");
			 * vEqQ.add(Case.CASE_CLOSED); }
			 * 
			 * vEqP.add("deletedStatus"); vEqQ.add(Integer.valueOf(0));
			 * 
			 * 
			 * String sLikeP[] = new String[vLikeP.size()];
			 * vLikeP.toArray(sLikeP); Object sLikeQ[] = new
			 * Object[vLikeP.size()]; vLikeQ.toArray(sLikeQ);
			 * 
			 * String sEqP[] = new String[vEqP.size()]; vEqP.toArray(sEqP);
			 * Object sEqQ[] = new Object[vEqP.size()]; vEqQ.toArray(sEqQ);
			 * Object sInQStatus[] = new Object[vInQStatus.size()];
			 * vInQStatus.toArray(sInQStatus); Object sInQCat[] = new
			 * Object[vInQCat.size()]; vInQCat.toArray(sInQCat);
			 */

			count = myCaseService.getTotalCaseReference(clientName, groupName,
					serviceName, providerName, diagnosisName, countryId,
					provinceId, cityId, minDate, maxDate, dateBy);

			// Add by aju on 20150420, for user View List Max Rows per page :D
			if (viewCountSet != null) {
				if (viewCountSet.intValue() > 0) {
					countSet = viewCountSet.intValue();
					maxPercountSet = viewCountSet.intValue();
				}
			}

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();

			System.out.println("index : " + index);
			System.out.println("count : " + count);
			System.out.println("countSet : " + countSet);
			System.out.println("maxPerCountSet : " + maxPercountSet);

			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue() + 1));
				System.out.println(" hslskir 1 "
						+ new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue()));
				System.out.println(" hslskir 2 "
						+ new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			// SORTING START
			/*
			 * String sortByColumn = new String(); if(sortcolumn!=null &&
			 * !sortcolumn.equals("")){ Boolean sortOrder =
			 * Boolean.valueOf(WebUtil.getParameterString(request, "sortorder",
			 * "")); String columntosort = WebUtil.getParameterString(request,
			 * "columntosort","");
			 * if(navigation.equals("gosearchbysortReference")){
			 * if(sortcolumn.equals("avaragecost")){ sortByColumn =
			 * "avaragecost"; Boolean sortByOrderAvarageCost =
			 * Boolean.valueOf(WebUtil. getParameterString(request,
			 * "sortByOrderCaseNo", "")); sortAvarageCost =
			 * !sortByOrderAvarageCost; order = sortAvarageCost; }else
			 * if(sortcolumn.equals("maxcost")){ sortByColumn = "maxcost";
			 * Boolean sortByOrderMaxCost = Boolean.valueOf(WebUtil.
			 * getParameterString(request, "sortByOrderMaxCost", ""));
			 * sortMaxCost = !sortByOrderMaxCost; order = sortMaxCost; }else
			 * if(sortcolumn.equals("mincost")){ sortByColumn = "mincost";
			 * Boolean sortByOrderMinCost = Boolean.valueOf(WebUtil.
			 * getParameterString(request, "sortByOrderMinCost", ""));
			 * sortMinCost = !sortByOrderMinCost; order = sortMinCost; }else
			 * if(sortcolumn.equals("avaragelos")){ sortByColumn = "avaragelos";
			 * Boolean sortByOrderAvarageLOS = Boolean.valueOf(WebUtil.
			 * getParameterString(request, "sortByOrderAvarageLOS", ""));
			 * sortAvarageLOS = !sortByOrderAvarageLOS; order = sortAvarageLOS;
			 * }else if(sortcolumn.equals("diagnosis")){ sortByColumn =
			 * "diagnosisId.diagnosisCode"; Boolean sortByOrderDiagnosis =
			 * Boolean.valueOf(WebUtil. getParameterString(request,
			 * "sortByOrderDiagnosis", "")); sortDiagnosis =
			 * !sortByOrderDiagnosis; order = sortDiagnosis; } columntosort =
			 * sortByColumn; }else{ sortByColumn = columntosort; order =
			 * sortOrder; if(sortcolumn.equals("avaragecost")){ sortAvarageCost
			 * = order; }else if(sortcolumn.equals("maxcost")){ sortMaxCost =
			 * order; }else if(sortcolumn.equals("mincost")){ sortMinCost =
			 * order; }else if(sortcolumn.equals("avaragelos")){ sortAvarageLOS
			 * = order; }else if(sortcolumn.equals("diagnosis")){ sortDiagnosis
			 * = order; } } request.setAttribute("sortorder", order);
			 * request.setAttribute("columntosort", columntosort);
			 */

			collection = myCaseService.searchCaseReference(clientName,
					groupName, serviceName, providerName, diagnosisName,
					countryId, provinceId, cityId, minDate, maxDate, dateBy,
					rowsetint, countSet.intValue());

			/*
			 * }else{
			 * 
			 * collection = claimService.searchCaseReferenceClaim(clientName,
			 * groupName, serviceName, providerName, diagnosisName, countryId,
			 * provinceId, cityId, minDate, maxDate, dateBy, rowsetint,
			 * countSet.intValue());
			 * 
			 * }
			 */

			if (collection.size() <= 0) {
				System.out.println("AHAY");
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}

				collection = myCaseService.searchCaseReference(clientName,
						groupName, serviceName, providerName, diagnosisName,
						countryId, provinceId, cityId, minDate, maxDate,
						dateBy, rowsetint, countSet.intValue());

			}

			request.setAttribute("navigation", navigation);
			// request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * if (navigation.equals("downloadExcel")) {
			 * 
			 * collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
			 * false, "caseId", required, 0, count);
			 * 
			 * HSSFWorkbook workbook =
			 * CaseReportGenerator.generateReport(collection);
			 * 
			 * response.setContentType("application/vnd.ms-excel");
			 * response.setHeader("Pragma", "no-cache");
			 * response.setHeader("Cache-Control", "no-cache");
			 * response.setDateHeader("Expires", 0);
			 * 
			 * response.setHeader("Content-disposition", "inline; filename=" +
			 * "casereport.xls");
			 * 
			 * ServletOutputStream sos = response.getOutputStream();
			 * 
			 * workbook.write(sos); sos.close(); }
			 */
			result.addObject("Cases", collection);

			// Add by aju on 20150420, for user View List Max Rows per page :D
			if (viewCountSet != null) {
				if (viewCountSet.intValue() > 0) {
					// make it back to default after filtering the max rows
					// showed :D
					// check the countSet and maxPercountSet default value on
					// applicationContext.xml :D
					countSet = 15;
					maxPercountSet = 15;
				}
				request.setAttribute("viewCountSet", viewCountSet);
			}

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

			/*
			 * request.setAttribute("sortAvarageCost", sortAvarageCost);
			 * request.setAttribute("sortMaxCost", sortMaxCost);
			 * request.setAttribute("sortMinCost", sortMinCost);
			 * request.setAttribute("sortAvarageLOS", sortAvarageLOS);
			 * request.setAttribute("sortDiagnosis", sortDiagnosis);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchEDCPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");

			if (currentNavigation.equalsIgnoreCase("listmember")) {
				location = "listMemberCase";
			} else if (currentNavigation.equalsIgnoreCase("listgroup")) {
				location = "listGroupCase";
			} else if (currentNavigation.equalsIgnoreCase("listprovider")) {
				location = "listProviderCase";
			}

			result = new ModelAndView(location);

			result.addObject("subnavigation", subnavigation);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equals("downloadExcel")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("physician")) {
						vLikeP.add("physician");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosis1Id.diagnosisName");
						vLikeQ.add(searchtext);
					}

					if (searchStatus != null && searchStatus.intValue() > 0) {
						vEqP.add("caseStatusId.caseStatusId");
						vEqQ.add(searchStatus);
					}
					if (caseCategoryId != null) {
						vEqP.add("caseCategoryId.caseCategoryId");
						vEqQ.add(caseCategoryId);
					}

				}

			}

			result.addObject("caseCategoryId", caseCategoryId);
			result.addObject("memberId", memberId);
			result.addObject("claimId", claimId);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);
			result.addObject("policyId", policyId);

			vEqP.add("caseType");
			vEqQ.add(Case.CASE_EDC);

			if (navigation.equalsIgnoreCase("searchedcmonitor")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_APPROVED);
				vEqP.add("isExpired");
				vEqQ.add(Integer.valueOf(0));
			} else if (navigation.equalsIgnoreCase("searchedcopen")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_OPEN);
			} else if (navigation.equalsIgnoreCase("searchedcexpire")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_APPROVED);
				vEqP.add("isExpired");
				vEqQ.add(Integer.valueOf(1));
			} else if (navigation.equalsIgnoreCase("searchedcclosed")) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(Case.CASE_CLOSED);
			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId",
			// foreign affairs end
			};
			collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					false, "caseId", required, rowsetint, countSet.intValue());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						false, "caseId", required, rowsetint,
						countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Cases", collection);

			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView approveExGratiaPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			java.io.Serializable pkey = caseId;

			String approvalNote = WebUtil.getParameterString(request,
					"approvalNote", "");

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"APPROVECASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for APPROVECASE access");
				return errorResult;

			}

			if (navigation.equalsIgnoreCase("approveexgratia")) {
				boolean res = myCaseService.approve(pkey, user);

				if (res) {
					request.setAttribute("alert", alertProperties.getMessage(
							"success.delete.case", null, "success",
							Locale.getDefault()));
				} else {
					request.setAttribute("alert", alertProperties.getMessage(
							"fail.delete.case", null, "fail",
							Locale.getDefault()));

				}

				result = detailPerformed(request, response, "detailCase");
			} else if (navigation.equalsIgnoreCase("preexgratia")) {

				result = detailPerformed(request, response, "preExGratiaCase");
			}
			request.setAttribute("caseId", caseId);
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");

		String subnavigation = WebUtil.getParameterString(request,
				"subnavigation", "");

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		try {

			// add aju on 20150929, new iFrame checker fufufu plus init
			//System.out.println("CaseController::handleRequest()...");
			isIFrameSession = securityService.isUsingIFrameSession(request);
			iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
			
			/**
			System.out.println("securityService->SessionMemberId->"
					+ securityService.getTheSessionMemberId());
			*/
			sessionMemberId = securityService.getTheSessionMemberId();
			
			/**
			System.out.println("securityService->SessionParentMemberId->"
					+ securityService.getTheSessionMemberParentId());
					*/
			
			sessionMemberParentId = securityService
					.getTheSessionMemberParentId();

			// Add by aju on 20151001, check the user login priviledges
			boolean isAllowed = securityService.isRequestAllowed(request);
			if (!isAllowed) {
				User theUser = securityService.getCurrentUser(request);

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "OtherFamilyAccessDenied");
				errorResult.addObject(
						"errorMessage",
						"Hey " + theUser.getUsername() + "("
								+ theUser.getUserId()
								+ "), are you missing your way? :P<br/>"
								+ "<a href=\"case\">Go Back</a>");
				return errorResult;
			}

			Configuration config = configurationService
					.getSystemConfiguration();
			String breadcrumb = "";

			//System.out.println("NAVIGATION CASE: " + navigation);
			if (navigation.equalsIgnoreCase("detail")) {

				result = detailPerformed(request, response, "detailCase");
				String caseId = request.getParameter("caseId");
				breadcrumb = "<a href=\"case?navigation=detail&caseId="
						+ caseId
						+ "\" class=\"linkbreadcrumb\">Detail Case</a>";

			} else if (navigation.equalsIgnoreCase("printrefer")) {
				result = detailPerformed(request, response, "printReferStruct");
				String caseId = request.getParameter("caseId");
				breadcrumb = "<a href=\"case?navigation=detail&caseId="
						+ caseId
						+ "\" class=\"linkbreadcrumb\">Detail Case</a>";

			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("approve")) {
				result = approvePerformed(request, response);
			}

			else if (navigation.equalsIgnoreCase("preexgratia")
					|| navigation.equalsIgnoreCase("approveexgratia")) {
				result = approveExGratiaPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")
					|| navigation.equals("downloadExcel")) {
				result = searchPerformed(request, response, "searchCase");
				breadcrumb = "<a href=\"case\" class=\"linkbreadcrumb\">Search Case</a>";
			} else if (navigation.equalsIgnoreCase("searchcase")) {
				result = searchPerformed(request, response, "searchCase");
				breadcrumb = "<a href=\"case\" class=\"linkbreadcrumb\">Search Active Case</a>";
			} else if (navigation.equalsIgnoreCase("searchopen")) {
				result = searchPerformed(request, response, "searchCase");
				breadcrumb = "<a href=\"case\" class=\"linkbreadcrumb\">Search Payment</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupCase");

			} else if (navigation.equalsIgnoreCase("listmember")
					|| subnavigation.equalsIgnoreCase("listmember")) {

				result = searchPerformed(request, response, "listMemberCase");

				String memberId = request.getParameter("memberId");
				breadcrumb = "<a href=\"member?navigation=detail&memberId="
						+ memberId
						+ "\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Case";

			} else if (navigation.equalsIgnoreCase("list")
					|| subnavigation.equalsIgnoreCase("list")
					|| navigation.equalsIgnoreCase("golist")) {
				result = searchPerformed(request, response, "listCase");
				String claimId = request.getParameter("claimId");
				breadcrumb = "<a href=\"claim?navigation=detail&claimId="
						+ claimId
						+ "\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim Case History";
			} else if (navigation.equalsIgnoreCase("listprovider")
					|| subnavigation.equalsIgnoreCase("listprovider")) {

				result = searchPerformed(request, response, "listProviderCase");
				String providerId = request.getParameter("providerId");
				breadcrumb = "<a href=\"provider?navigation=detail&providerId="
						+ providerId
						+ "\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Case";
			} else if (navigation.equalsIgnoreCase("listpolicy")
					|| subnavigation.equalsIgnoreCase("listpolicy")) {
				System.out.println("test list policy");

				result = searchPerformed(request, response, "listPolicyCase");
				String policyId = request.getParameter("policyId");
				breadcrumb = "<a href=\"policy?navigation=detail&policyId="
						+ policyId
						+ "\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Case";
			}

			else if (navigation.equalsIgnoreCase("terminate")) {
				result = terminatePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("refercase")) {
				result = referCasePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("listgroup")) {
				result = searchPerformed(request, response, "listGroupCase");
				String groupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="
						+ groupId
						+ "\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Case";
			} else if (navigation.equalsIgnoreCase("printGL")) {
				// ini belum di custom menggunakan layout standard
				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);

				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printGuaranteeLetter_"
							+ config.getCompanyCode() + "_en";
				} else {
					location = "printGuaranteeLetter_"
							+ config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					// location = "printGuaranteeLetter";
					location = "printLetterNotFound";
				}

				//System.out.println("PRINT LOCATION : " + location);

				result = detailPerformed(request, response, location);
			} else if (navigation.equalsIgnoreCase("printClosing")) {
				String languageSelect = request.getParameter("langselect") == null ? "ina"
						: request.getParameter("navigation");
				String location = "";
				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printCaseClosingLetter_"
							+ config.getCompanyCode();
				} else {
					location = "printCaseClosingLetter_"
							+ config.getCompanyCode();
				}

				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");
				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printCaseClosingLetter";
				}

				//System.out.println("PRINT LOCATION : " + location);

				result = printClosingLetterPerformed(request, response,
						location);
			}
			// Add 02042015, for Idemnity Letter
			else if (navigation.equalsIgnoreCase("printIdemnityLetter")) {
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);
				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printIdemnityLetter_" + config.getCompanyCode()
							+ "_en";
				} else {
					location = "printIdemnityLetter_" + config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);
			}
			// Add 06042015, for print Finalized Benefit Calculation
			else if (navigation.equalsIgnoreCase("printBenefitLetterFinalized")) {
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);
				System.out.println("LANGSELECT : " + languageSelect);
				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printFinalizedBenefitCalculationLetter_"
							+ config.getCompanyCode() + "_en";
				} else {
					location = "printFinalizedBenefitCalculationLetter_"
							+ config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);
			}
			// Add 20150422 by FVO, for print Medical Form
			else if (navigation.equalsIgnoreCase("printMedicalForm")) {
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);
				System.out.println("LANGSELECT : " + languageSelect);
				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printMedicalFormLetter_"
							+ config.getCompanyCode() + "_en";
				} else {
					location = "printMedicalFormLetter_"
							+ config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);
			} else if (navigation.equalsIgnoreCase("printcost")) {

				result = printCostReportPerformed(request, response);
			}// Edit 20150505 by FVO, Multilanguage Letter by User Option
			else if (navigation.equalsIgnoreCase("printConfirmLetter")) {
				// String languageSelect = request.getParameter("langselect") ==
				// null ? "ina"
				// : request.getParameter("navigation");
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);
				System.out.println("LANGSELECT : " + languageSelect);
				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printConfirmLetter_" + config.getCompanyCode()
							+ "_en";
				} else {
					location = "printConfirmLetter_" + config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				System.out.println("PRINT LOCATION : " + location);

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					// System.out.println("FILE1 KOSONG");
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);

			}
			// Add 01042015, for Confirmation Letter
			else if (navigation.equalsIgnoreCase("printConfirmLetterWithType")) {
				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);

				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printConfirmLetterWithType_"
							+ config.getCompanyCode() + "_en";
				} else {
					location = "printConfirmLetterWithType_"
							+ config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);
			}
			// Add by aju on 20150331, for printing Authorization Letter fufufu
			else if (navigation.equalsIgnoreCase("printAuthorizationLetter")) {
				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);

				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printAuthorizationLetter_"
							+ config.getCompanyCode() + "_en";
				} else {
					location = "printAuthorizationLetter_"
							+ config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				System.out.println("PRINT LOCATION : " + location);

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					// System.out.println("FILE1 KOSONG");
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);

			}
			// Add by aju on 20150401, for printing Investigation Letter fufufu
			else if (navigation.equalsIgnoreCase("printInvestigationLetter")) {
				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);

				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printInvestigationLetter_"
							+ config.getCompanyCode() + "_en";
				} else {
					location = "printInvestigationLetter_"
							+ config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					// System.out.println("FILE1 KOSONG");
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);

			}
			// Add 02042015, for Idemnity Letter
			else if (navigation.equalsIgnoreCase("printRejectedLetter")) {
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);
				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printRejectedLetter_" + config.getCompanyCode()
							+ "_en";
				} else {
					location = "printRejectedLetter_" + config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);
			}
			// Add 20150814 by FVO for print Returning Letter
			else if (navigation.equalsIgnoreCase("printReturningLetter")) {
				String languageSelect = WebUtil.getParameterString(request,
						"langselect", "");
				String location = "";
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/myCase/");

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");
				java.io.Serializable pkey = caseId;
				// modified by aju on 20150329, add required/key
				// Case->CaseStatus :D
				// modified again by aju on 20150330, add required for
				// Case->Client, Case->Group
				String[] requiredCase = { "Case.MemberId",
						"Case.MemberId.ClientId" };

				Case object = myCaseService.get(pkey, requiredCase);

				if (languageSelect.equalsIgnoreCase("en")) {
					location = "printReturningLetter_"
							+ config.getCompanyCode() + "_en";
				} else {
					location = "printReturningLetter_"
							+ config.getCompanyCode();
				}

				if (object.getMemberId().getClientId() != null) {
					File file = new File(path, location
							+ "_"
							+ object.getMemberId().getClientId()
									.getClientCode() + ".jsp");
					if (file.exists()) {
						location = location
								+ "_"
								+ object.getMemberId().getClientId()
										.getClientCode();
					}
				}

				System.out.println("PRINT LOCATION : " + location);

				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					// System.out.println("FILE1 KOSONG");
					location = "printLetterNotFound";
				}

				result = detailPerformed(request, response, location);

			} else if (navigation.equalsIgnoreCase("jsontotalopencase")
					|| navigation.equalsIgnoreCase("jsontotalexpirecase")
					|| navigation.equalsIgnoreCase("jsontotalclosingcase")
					|| navigation.equalsIgnoreCase("jsontotalnewcase")
					|| navigation.equalsIgnoreCase("jsontotaledccase")
					|| navigation
							.equalsIgnoreCase("jsontotalcaseinvestigation")
					|| navigation.equalsIgnoreCase("jsontotalnewcobcase")) {

				result = jsonTotalPendingCasePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("lookupclaimjson")) {
				result = lookupClaimJsonPerformed(request, response,
						"lookupCaseJson");
			} else if (navigation.equalsIgnoreCase("verifymember")
					|| navigation.equalsIgnoreCase("docheckmember")) {
				result = verifyMemberPerformed(request, response,
						"checkMemberRegistration");
			} else if (navigation.equalsIgnoreCase("searchedcopen")
					|| navigation.equalsIgnoreCase("searchedcmonitor")
					|| navigation.equalsIgnoreCase("searchedcclosed")) {
				result = searchEDCPerformed(request, response, "searchCase");
			} else if (navigation.equalsIgnoreCase("mycase")
					|| navigation.equalsIgnoreCase("gosearchmycase")) {
				result = searchPerformed(request, response, "searchCase");
			} else if (navigation.equalsIgnoreCase("prereverserefer")
					|| navigation.equalsIgnoreCase("reversalrefer")) {
				result = reversalReferPerformed(request, response,
						"reverseReferCase");
			} else if (navigation.equalsIgnoreCase("prereversecase")
					|| navigation.equalsIgnoreCase("reversalcase")) {
				result = reversalCasePerformed(request, response, "reverseCase");
			} else if (navigation.equalsIgnoreCase("preassign")
					|| navigation.equalsIgnoreCase("assigncase")) {
				result = assignCasePerformed(request, response, "assignCase");
			} else if (navigation.equalsIgnoreCase("prepending")
					|| navigation.equalsIgnoreCase("pendingcase")) {
				result = pendingCasePerformed(request, response, "pendingCase");
			} else if (navigation.equalsIgnoreCase("downloadissued")
					|| navigation.equalsIgnoreCase("gosearchissued")
					|| navigation.equalsIgnoreCase("searchissued")
					|| navigation.equalsIgnoreCase("gosearchissuedbysort")) {
				result = downloadGLIssuedPerformed(request, response,
						"searchIssuedCaseReport");
			} else if (navigation.equalsIgnoreCase("downloadactive")
					|| navigation.equalsIgnoreCase("gosearchactive")
					|| navigation.equalsIgnoreCase("searchactive")
					|| navigation.equalsIgnoreCase("gosearchactivebysort")) {
				result = downloadGLActivePerformed(request, response,
						"searchActiveCaseReport");
			} else if (navigation.equalsIgnoreCase("downloadreject")
					|| navigation.equalsIgnoreCase("gosearchrejected")) {
				result = downloadCaseRejectedPerformed(request, response,
						"searchCase");
			} else if (navigation.equalsIgnoreCase("void")
					|| navigation.equalsIgnoreCase("dovoid")) {
				result = voidPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("canceldischarge")
					|| navigation.equalsIgnoreCase("docanceldischarge")) {
				result = cancelDischargePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("mycaseinvestigation")) {
				result = searchPerformed(request, response, "searchCase");
			} else if (navigation.equalsIgnoreCase("searchReference")
					|| navigation.equals("gosearchReference")
					|| navigation.equals("downloadExcelReference")) {
				result = searchReferencePerformed(request, response,
						"searchCaseReference");
				breadcrumb = "<a href=\"case\" class=\"linkbreadcrumb\">Search Case Reference</a>";
			} else if (navigation.equalsIgnoreCase("edcreport")
					|| navigation
							.equalsIgnoreCase("downloadExcelCaseEDCReport")
					|| navigation.equalsIgnoreCase("golistcaseedcreport")) {
				result = searchCaseEDCManualPerformed(request, response,
						"searchCaseEDCReport");
				breadcrumb = "<a href=\"caseedcreport\" class=\"linkbreadcrumb\">Case ED Report</a>";
			} else if (navigation.equalsIgnoreCase("glapprovedreport")
					|| navigation
							.equalsIgnoreCase("downloadExcelCaseGLApproved")
					|| navigation
							.equalsIgnoreCase("golistcaseglapprovedreport")) {
				result = searchCaseGLReportPerformed(request, response,
						"searchCaseGLApproved");
				breadcrumb = "<a href=\"caseedcreport\" class=\"linkbreadcrumb\">Case ED Report</a>";
			} else {
				result = searchPerformed(request, response, "searchCase");
				breadcrumb = "<a href=\"case\" class=\"linkbreadcrumb\">Search Case</a>";
			}
			result.addObject("breadcrumb", breadcrumb);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	public ModelAndView searchCaseEDCManualPerformed(
			HttpServletRequest request, HttpServletResponse response,
			String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			result = new ModelAndView(location);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equals("downloadExcelCaseEDCReport")
					|| navigation.equals("golistcaseedcreport")) {

				if (searchby != null && searchtext != null
						&& !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("physician")) {
						vLikeP.add("physician");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosis1Id.diagnosisName");
						vLikeQ.add(searchtext);
					}

					if (searchStatus != null && searchStatus.intValue() > 0) {
						vEqP.add("caseStatusId.caseStatusId");
						vEqQ.add(searchStatus);
					}
					if (caseCategoryId != null) {
						vEqP.add("caseCategoryId.caseCategoryId");
						vEqQ.add(caseCategoryId);
					}

				}

			}

			result.addObject("caseCategoryId", caseCategoryId);
			result.addObject("memberId", memberId);
			result.addObject("claimId", claimId);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);
			result.addObject("policyId", policyId);

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			Object manualReg[] = { Integer.valueOf(1), Integer.valueOf(2),
					Integer.valueOf(3), Integer.valueOf(4) };
			count = myCaseService.getTotalCaseRegisterManual(sLikeP, sLikeQ,
					sEqP, sEqQ, manualReg);

			String arah = WebUtil.getParameterString(request, "arah", "");

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId",
			// foreign affairs end
			};

			collection = myCaseService.searchCaseManualRegistation(sLikeP,
					sLikeQ, sEqP, sEqQ, manualReg, false, "caseId", required,
					rowsetint, countSet.intValue());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				collection = myCaseService.searchCaseManualRegistation(sLikeP,
						sLikeQ, sEqP, sEqQ, manualReg, false, "caseId",
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Cases", collection);

			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

			if (navigation.equals("downloadExcelCaseEDCReport")) {

				collection = myCaseService.searchCaseManualRegistation(sLikeP,
						sLikeQ, sEqP, sEqQ, manualReg, false, "caseId",
						required, 0, count);

				HSSFWorkbook workbook = CaseReportGenerator
						.generateReportCaseProvider(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "caseedcreport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				sos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchCaseGLReportPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCASE access");
				return errorResult;

			}

			String url = WebUtil.getParameterString(request, "url", "");

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");
			Integer diagnosisId = WebUtil.getParameterInteger(request,
					"diagnosisId");
			String clientText = WebUtil.getParameterString(request,
					"clientText", "");
			String providerText = WebUtil.getParameterString(request,
					"providerText", "");
			String diagnosisText = WebUtil.getParameterString(request,
					"diagnosisText", "");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Date minimumDate = WebUtil
					.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil
					.getParameterDate(request, "maximum_date");

			System.out.println("MINIMUM DATE : " + minimumDate);
			System.out.println("MAXIMUM DATE : " + maximumDate);

			result = new ModelAndView(location);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equals("downloadExcelCaseEDCReport")
					|| navigation.equals("golistcaseedcreport")) {

				if (searchby != null && searchtext != null
						&& !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("caseNumber")) {
						vLikeP.add("caseNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("physician")) {
						vLikeP.add("physician");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosis1Id.diagnosisName");
						vLikeQ.add(searchtext);
					}

				}
			}

			if (searchStatus != null) {
				vEqP.add("caseStatusId.caseStatusId");
				vEqQ.add(searchStatus);
			}
			if (caseCategoryId != null) {
				vEqP.add("caseCategoryId.caseCategoryId");
				vEqQ.add(caseCategoryId);
			}
			if (diagnosisId != null) {
				vEqP.add("diagnosis1Id.diagnosisId");
				vEqQ.add(diagnosisId);
			}
			if (clientId != null) {
				vEqP.add("memberId.clientId.clientId");
				vEqQ.add(clientId);
			}
			if (providerId != null) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			}
			vEqP.add("isGLIssued");
			vEqQ.add(Integer.valueOf(1));

			result.addObject("caseCategoryId", caseCategoryId);
			result.addObject("memberId", memberId);
			result.addObject("claimId", claimId);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("providerId", providerId);
			result.addObject("policyId", policyId);
			result.addObject("clientId", clientId);
			result.addObject("diagnosisId", diagnosisId);

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String[] searchByDate = { "caseStartTime" };
			Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };

			if (minimumDate != null && maximumDate != null) {
				if (minimumDate.toString().equals("1970-01-01")
						&& maximumDate.toString().equals("1970-01-01")) {
					count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				} else {
					count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
							searchByDate, minimumDateArray, maximumDateArray);
				}
			} else {
				count = myCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}

			String arah = WebUtil.getParameterString(request, "arah", "");

			if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId",
					"Case.MemberId.ClientId", "Case.CaseStatusId",
			// foreign affairs end
			};

			if (minimumDate != null && maximumDate != null
					&& !minimumDate.toString().equals("1970-01-01")
					&& !maximumDate.toString().equals("1970-01-01")) {
				collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray,
						false, "caseId", required, rowsetint,
						countSet.intValue());
			} else {
				collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						false, "caseId", required, rowsetint,
						countSet.intValue());
			}

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				if (minimumDate != null && maximumDate != null
						&& !minimumDate.toString().equals("1970-01-01")
						&& !maximumDate.toString().equals("1970-01-01")) {
					collection = myCaseService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, searchByDate, minimumDateArray,
							maximumDateArray, required, rowsetint,
							countSet.intValue());
				} else {
					collection = collection = myCaseService.search(sLikeP,
							sLikeQ, sEqP, sEqQ, false, "caseId", required,
							rowsetint, countSet.intValue());
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("maximumDate", "");
			} else {
				request.setAttribute("maximumDate", maximumDate);
			}
			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("minimumDate", "");
			} else {
				request.setAttribute("minimumDate", minimumDate);
			}
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Cases", collection);
			request.setAttribute("providerText", providerText);
			request.setAttribute("diagnosisText", diagnosisText);
			request.setAttribute("clientText", clientText);

			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

			if (navigation.equals("downloadExcelCaseGLApproved")) {

				// collection =
				// myCaseService.searchCaseManualRegistation(sLikeP, sLikeQ,
				// sEqP, sEqQ, manualReg, false, "caseId",
				// required, 0, count);
				collection = myCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						false, "caseId", required, 0, count);

				HSSFWorkbook workbook = CaseReportGenerator
						.generateReportCaseGLApproved(collection,
								caseHistoryService);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "caseglapprovedreport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				sos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	// class+

	// class-

}
