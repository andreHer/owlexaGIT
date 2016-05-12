package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.MemberDiagnosisExclusion;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.PendingClaim;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.ClaimSCDto;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimProcessService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberDiagnosisExclusionService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PendingClaimService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.PolicyDiagnosisExclusionService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.SecurityUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;


// imports+ 

// imports- 

/**
 * Claim is a servlet controller for claim Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class ClaimController implements Controller

// extends+

// extends-

{

	private CaseCategoryService caseCategoryService;
	
	private DiagnosisService diagnosisService;
	
	private ClaimService claimService;
	
	private ClaimItemService claimItemService;
	
	private PolicyService  policyService;

	private UserService userService;
	
	private SecurityService securityService;
	
	private BatchClaimService batchClaimService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;

	private PendingClaimService pendingClaimService;
	
	private MemberGroupService memberGroupService;
	
	private ItemService itemService;
	
	private ProviderService providerService;
	private MemberService memberService;
	
	private ActivityLogService logService;
	private CaseService caseService;
	private MemberDiagnosisExclusionService memberDiagnosisExclusionService;
	private MemberProductService memberProductService;
	private MemberBenefitService memberBenefitService;
	private MemberClausulService memberClausulService;
	private PolicyClausulService policyClausulService;
	private CurrencyService currencyService;
	
	
	
	private PolicyDiagnosisExclusionService policyDiagnosisExclusionService;
	
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;

	private ClaimProcessService claimProcessService;
	
	
	public ClaimProcessService getClaimProcessService() {
		return claimProcessService;
	}

	public void setClaimProcessService(ClaimProcessService claimProcessService) {
		this.claimProcessService = claimProcessService;
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public PolicyDiagnosisExclusionService getPolicyDiagnosisExclusionService() {
		return policyDiagnosisExclusionService;
	}

	public void setPolicyDiagnosisExclusionService(
			PolicyDiagnosisExclusionService policyDiagnosisExclusionService) {
		this.policyDiagnosisExclusionService = policyDiagnosisExclusionService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}

	public void setMemberClausulService(MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}
	
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	
	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MemberDiagnosisExclusionService getMemberDiagnosisExclusionService() {
		return memberDiagnosisExclusionService;
	}

	public void setMemberDiagnosisExclusionService(
			MemberDiagnosisExclusionService memberDiagnosisExclusionService) {
		this.memberDiagnosisExclusionService = memberDiagnosisExclusionService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public PendingClaimService getPendingClaimService() {
		return pendingClaimService;
	}

	public void setPendingClaimService(PendingClaimService pendingClaimService) {
		this.pendingClaimService = pendingClaimService;
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

	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public ClaimService getClaimService() {
		return this.claimService;
	}

	
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}
	
	

	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			java.io.Serializable pkey = claimId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECLAIM access");
				return errorResult;
				
			}
			
			Claim res = claimService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.claim", null, "success", Locale
								.getDefault()));
			} else {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"fail.delete.claim", null, "fail", Locale
										.getDefault()));

			}
			result = searchPerformed(request, response, "searchClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}


	public ModelAndView extractPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			java.io.Serializable pkey = claimId;

			ActionUser user= securityService.getActionUser(request); 
			
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			Integer destinationBatch = WebUtil.getParameterInteger(request, "afterBatchClaimId");
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECLAIM access");
				return errorResult;
				
			}
			String[] required = {"Claim.BatchClaimId","Claim.MemberId","Claim.CaseCategoryId","Claim.MemberId.MemberGroupId","Claim.CaseId","Claim.DiagnosisId"};
			
			Claim claim = claimService.get(pkey,required);
			
			if (navigation.equalsIgnoreCase("extract")){
				result = new ModelAndView("extractClaim");
			}
			else if (navigation.equalsIgnoreCase("saveextract")){
				
				
				BatchClaim batchClaim = batchClaimService.get(destinationBatch);
				
				if (batchClaim != null ){
					claimService.extract(claimId, batchClaim, user);
				}
				//claimService.extract(object, batchClaim, actionUser)
				result = detailPerformed(request, response, "detailClaim");
			}
			
			
			result.addObject("claimId", claimId);

			result.addObject("claim", claim);
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	
	public ModelAndView completePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			java.io.Serializable pkey = claimId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "COMPLETECLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for COMPLETECLAIM access");
				return errorResult;
				
			}
			
			Claim res = claimService.completeClaim(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.complete.claim", null, "success", Locale
								.getDefault()));
			} else {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"fail.complete.claim", null, "fail", Locale
										.getDefault()));

			}
			result = detailPerformed(request, response, "detailClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView approveExGratiaPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			String navigation = WebUtil.getParameterString(request, "navigation", "");

			
			java.io.Serializable pkey = claimId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "COMPLETECLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for COMPLETECLAIM access");
				return errorResult;
				
			}
			if (navigation.equalsIgnoreCase("approveexgratia")){
				result = new ModelAndView("approveExGratiaClaim");
				
				Claim res = claimService.get(claimId);
				result.addObject("claim", res);
				
			}
			else if (navigation.equalsIgnoreCase("saveapproveexgratia")){

				
				String remarks = WebUtil.getParameterString(request, "remarks", "");
				String res = claimService.approveExGratia(claimId, remarks, user);
				
					
				if (res != null && res.equalsIgnoreCase("OK")){
					request.setAttribute("alert", "Success Approve Ex Gratia");
				}
				else {
					request.setAttribute("alert", "Failed Approve Ex Gratia");
				}
				result = detailPerformed(request, response, "detailClaim");
				
			}


		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView openPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");


			java.io.Serializable pkey = claimId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "OPENCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for OPENCLAIM access");
				return errorResult;
				
			}
			
			Claim res = claimService.openClaim(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.open.claim", null, "success", Locale
								.getDefault()));
			} else {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"fail.open.claim", null, "fail", Locale
										.getDefault()));

			}
			result = detailPerformed(request, response, "detailClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView checklistDocumentPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			ActionUser user= securityService.getActionUser(request); 
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "VOIDCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for VOIDCLAIM access");
				return errorResult;
				
			}
			/*String[] claimRequired = {"Claim.BatchClaimId","Claim.MemberId",
					"Claim.CaseCategoryId","Claim.MemberId.MemberGroupId","Claim.CaseId",
					"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.BatchClaimId.ClaimCurrency"};*/
			
			String[] claimRequired = {"Claim.BatchClaimId","Claim.MemberId", "Claim.MemberId.ParentMember",  
					"Claim.CaseCategoryId","Claim.MemberId.MemberGroupId","Claim.CaseId", "Claim.ClaimTypeId", 
					"Claim.ProviderId", "Claim.PaymentId", "Claim.MemberId.ClientId",
					"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.BatchClaimId.ClaimCurrency","Claim.CobClaimReferenceId"};
			
			Claim claim = claimService.get(claimId,claimRequired);
			
			if (navigation.equalsIgnoreCase("checklist")){			
				result = new ModelAndView("listDetailDocument");
				result.addObject("claim", claim);
				result.addObject("claimId", claimId);
			}
			else if (navigation.equalsIgnoreCase("dochecklist")){
							
				
				String invoiceDocument = WebUtil.getParameterString(request, "invoiceDocument", "");
				String glDocument = WebUtil.getParameterString(request, "glDocument", "");
				String prescriptionDocument = WebUtil.getParameterString(request, "prescriptionDocument", "");
				String referalDocument = WebUtil.getParameterString(request, "referalDocument", "");
				String edcDocument = WebUtil.getParameterString(request, "edcDocument", "");
				String labDocument = WebUtil.getParameterString(request, "labDocument", "");
				
				if (claim != null){
					if (invoiceDocument != null && invoiceDocument.equalsIgnoreCase("1")){
						claim.setInvoiceDocument(Integer.valueOf(1));
					}
					else {
						claim.setInvoiceDocument(Integer.valueOf(0));
					}
					if (glDocument != null && glDocument.equalsIgnoreCase("1")){
						claim.setGlDocument(Integer.valueOf(1));
					}
					else {
						claim.setGlDocument(Integer.valueOf(0));
					}
					
					if (prescriptionDocument != null && prescriptionDocument.equalsIgnoreCase("1")){
						claim.setPrescriptionDocument(Integer.valueOf(1));
					}
					else {
						claim.setPrescriptionDocument(Integer.valueOf(0));
					}
					if (referalDocument != null && referalDocument.equalsIgnoreCase("1")){
						claim.setReferalDocument(Integer.valueOf(1));
					}
					else {
						claim.setReferalDocument(Integer.valueOf(0));
					}
					if (edcDocument != null && edcDocument.equalsIgnoreCase("1")){
						claim.setEdcDocument(Integer.valueOf(1));
					}
					else {
						claim.setEdcDocument(Integer.valueOf(0));
					}
					if (labDocument != null && labDocument.equalsIgnoreCase("1")){
						claim.setLabDocument(Integer.valueOf(1));
					}
					else {
						claim.setLabDocument(Integer.valueOf(0));
					}
					
					claimService.update(claim, user);
					
				}
			
				return new ModelAndView(new RedirectView("claim?navigation=checklist&claimId="+claimId));
			}
			
			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView voidClaimPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			ActionUser user= securityService.getActionUser(request); 
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "VOIDCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for VOIDCLAIM access");
				return errorResult;
				
			}
			
			if (navigation.equalsIgnoreCase("approvevoid")){
			
				String notes = WebUtil.getParameterString(request, "voidNote", "");
				String tracenum = "0000000";
								
				boolean res = claimService.voidClaim(claimId,tracenum, notes, user);
	
				if (res) {
					request.setAttribute("alert", alertProperties.getMessage(
							"success.void.claim", null, "success", Locale
									.getDefault()));
				} else {
					request
							.setAttribute("alert", alertProperties.getMessage(
									"fail.void.claim", null, "fail", Locale
											.getDefault()));
	
				}
				result = detailPerformed(request, response, "detailClaim");
			}
			else if (navigation.equalsIgnoreCase("prevoid")){
				result = detailPerformed(request, response, "preVoidClaim");
			}
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView cancelRejectClaimPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			ActionUser user= securityService.getActionUser(request); 
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "VOIDCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for VOIDCLAIM access");
				return errorResult;
				
			}
			
			if (navigation.equalsIgnoreCase("cancelreject")){
			
				String notes = WebUtil.getParameterString(request, "cancelRejectNote", "");
				
				boolean res = claimService.cancelRejectClaim(claimId,notes, user);
	
				if (res) {
					request.setAttribute("alert", alertProperties.getMessage(
							"success.cancel.reject.claim", null, "success", Locale
									.getDefault()));
				} else {
					request
							.setAttribute("alert", alertProperties.getMessage(
									"fail.cancel.reject.claim", null, "fail", Locale
											.getDefault()));
	
				}
				result = detailPerformed(request, response, "detailClaim");
			}
			else if (navigation.equalsIgnoreCase("precancelreject")){
				result = detailPerformed(request, response, "cancelRejectClaim");
			}
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView testRegisterClaimPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			result = new ModelAndView("testRegisterResult");
			
			Collection<ClaimItemDto> claimDetails = new Vector<ClaimItemDto>();
			String merchantId = WebUtil.getParameterString(request, "merchantId", "");
			String cardNumber = WebUtil.getParameterString(request, "cardNumber", "");
			
			ClaimSCDto claimDto = new ClaimSCDto();
			claimDto.setServiceCategory(CaseCategory.OUTPATIENT_STR);
			claimDto.setDiagnosis1Code("J00");
			
			
			ClaimItemDto item1 = new ClaimItemDto();
			item1.setClaimItemAmount(1.0);
			item1.setClaimItemValue(100000.0);
			item1.setItemCode("OP0002");
			
			ClaimItemDto item2 = new ClaimItemDto();
			item2.setItemCode("OP0004");
			item2.setClaimItemValue(200000.0);
			item2.setClaimItemAmount(1.0);

			
			claimDetails.add(item1);
			claimDetails.add(item2);
			
			if (claimDetails != null){
				
				Claim claim = new Claim();				
				claim.setAdmissionDate(new java.sql.Date(System.currentTimeMillis()));				
				claim.setMerchantId(merchantId);				
				claim.setClaimDate(new java.sql.Date(System.currentTimeMillis()));				
				
				Provider provider = providerService.getByProviderCode(merchantId);

				Member member = memberService.getMemberByCardNumber(cardNumber);
				
				CaseCategory cc = new CaseCategory();
				
				//System.out.println("CLAIM CATEGORY : " + claimDto.getServiceCategory() + " KALO DENTAL : " + CaseCategory.DENTAL_STR);
				if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.DENTAL_STR)){
					cc.setCaseCategoryId(CaseCategory.DENTAL);
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.INPATIENT_STR)){
					cc.setCaseCategoryId(CaseCategory.INPATIENT);
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MATERNITY_STR)){
					cc.setCaseCategoryId(CaseCategory.MATERNITY);
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.OPTICAL_STR)){
					cc.setCaseCategoryId(CaseCategory.OPTICAL);
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.OUTPATIENT_STR)){
					cc.setCaseCategoryId(CaseCategory.OUTPATIENT);
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.SPECIALIST_STR)){
					cc.setCaseCategoryId(CaseCategory.SPECIALIST);
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MISC_STR)){
					cc.setCaseCategoryId(CaseCategory.MISC);
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MEDICAL_CHECK_UP_STR)){
					cc.setCaseCategoryId(CaseCategory.MEDICAL_CHECK_UP);
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}				
				
				
				claim.setCaseCategoryId(cc);				
				claim.setClaimDate(claimDto.getClaimDate());
				claim.setDischargeDate(claimDto.getDischargedDate());
				claim.setMemberId(member);
				
				if (member != null && member.getClientId() != null){
					claim.setClientId(member.getClientId().getClientId());
				}
				
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(1);
				claim.setClaimStatus(status);
				
				System.out.println ("Setting Claim Type");
				ClaimType claimType = new ClaimType();
				claimType.setClaimTypeId(ClaimType.CASHLESS);
				claim.setClaimTypeId(claimType);
				
				
				claim.setProviderId(provider);
				claim.setProviderName(provider.getProviderName());
				
				
				
				//System.out.println ("setting claim diagnosis code : " + claimDto.getDiagnosis1Code() + " | ");
				if (claimDto.getDiagnosis1Code() != null && !claimDto.getDiagnosis1Code().equalsIgnoreCase("")){
					System.out.println("nyari diagnosis : " + claimDto.getDiagnosis1Code() + " di database ! ");
					
					Diagnosis diagnosis = diagnosisService.getDiagnosisByCode(claimDto.getDiagnosis1Code());
					
					if (diagnosis != null){
					//	System.out.println("dapet nih diagnosis nya : " + diagnosis.getDiagnosisName());
						claim.setDiagnosisId(diagnosis);					
						claim.setDiagnosis1Code(diagnosis.getDiagnosisCode());
					}
				}
				if (claimDto.getDiagnosis2Code() != null && !claimDto.getDiagnosis2Code().equalsIgnoreCase("")){
					//System.out.println("nyari diagnosis 2 : " + claimDto.getDiagnosis2Code() + " di database ! ");
					Diagnosis diagnosis = diagnosisService.getDiagnosisByCode(claimDto.getDiagnosis2Code());
					if (diagnosis != null){
						//System.out.println("dapet nih diagnosis 2 nya : " + diagnosis.getDiagnosisName());						
						claim.setDiagnosis2Id(diagnosis);
						claim.setDiagnosis2Code(diagnosis.getDiagnosisCode());					
						
					}
				}
				
				if (claimDto.getDiagnosis3Code() != null && !claimDto.getDiagnosis3Code().equalsIgnoreCase("")){
					//System.out.println("nyari diagnosis 3 : " + claimDto.getDiagnosis3Code() + " di database ! ");
					Diagnosis diagnosis = diagnosisService.getDiagnosisByCode(claimDto.getDiagnosis3Code());
					if (diagnosis != null){
						//System.out.println("dapet nih diagnosis 3 nya : " + diagnosis.getDiagnosisName());
						claim.setDiagnosis3Id(diagnosis);
						claim.setDiagnosis3Code(diagnosis.getDiagnosisCode());
						
					}
				}
				//System.out.println("checking claim item iterator");
				
				Iterator<ClaimItemDto> iterator = claimDetails.iterator();
				Collection<ClaimItem> claimDetailsCollection = new Vector<ClaimItem>();
				
				while (iterator.hasNext()){
					ClaimItemDto itemDto = iterator.next();
					
					if (itemDto != null){
						
						Item item = itemService.getMemberItemByEdcCode(itemDto.getItemCode(),member.getMemberId());
						
						if (item != null){
							ClaimItem claimItem = new ClaimItem();
							claimItem.setItemId(item);
							claimItem.setItemCode(itemDto.getItemCode());
							claimItem.setClaimItemAmount(itemDto.getClaimItemAmount());
							claimItem.setClaimItemValue(itemDto.getClaimItemValue());
							CaseStatus claimItemStatus = new CaseStatus();
							claimItemStatus.setCaseStatusId(ClaimItem.CLAIM_ITEM_OPEN);
							claimItem.setClaimItemStatus(claimItemStatus);
				
							if (item != null){
					//			System.out.println("adding item to claim : " + item.getItemName()  + " item code : " + item.getItemCode());
							}
							claimDetailsCollection.add(claimItem);
						}
					}
				}
				
				ActionUser actionUser = new ActionUser();
				User user = new User();
				user.setUsername(merchantId);
				actionUser.setUser(user);
				
				//System.out.println("CREATING CLAIM FROM EDC");
				Claim claimResult = claimService.createClaimRKI(claim, claimDetailsCollection, actionUser);
				
				
				if (claimResult != null){
					//System.out.println("CLAIM CREATED : " + claimResult.getClaimNumber());			
					
					String claimNumber =   claimResult.getClaimNumber();
					StringTokenizer tokenizer = new StringTokenizer(claimNumber,"/");
					if (tokenizer.hasMoreTokens()){
						claimNumber = tokenizer.nextToken();
					}
					
					String responseInfo = claimNumber;
					System.out.println("Additional Info : " + responseInfo);
					claimDto.setClaimNumber(claimResult.getClaimNumber());
					
					result.addObject("result", responseInfo);

					
				}			
				else {					

				}
			
			}
			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView claimRKIPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			Integer claimCurrencyId = WebUtil.getParameterInteger(request, "claimCurrencyId");
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "OPENCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for OPENCLAIM access");
				return errorResult;
				
			}	
			
			Provider provider = null;
			Member member = null;
			Case theCase = null;
			
			if (providerId != null ){
				provider = providerService.get(providerId);
				
			}
			if (memberId != null){
				member = memberService.get(memberId);
				
				
			}
			if (caseId != null){
				
				theCase = caseService.get(caseId);
			}
			if (navigation.equalsIgnoreCase("claimrki")){
				
				Collection<Currency> currencyList = currencyService.getAll();
				Collection<Item> mostFrequentItems = itemService.getClaimableItem(memberId, caseCategoryId);
				
				Collection<MemberBenefit> memberBenefits = memberBenefitService.getMemberBenefitByDate(member.getMemberId(),
						theCase.getCaseCategoryId().getCaseCategoryId(),
						theCase.getCaseStartTime(), theCase.getCaseStartTime());
				
				Collection<MemberClausul> memberClausuls = memberClausulService.getMemberActiveClausul(member.getMemberId(),
						theCase.getCaseCategoryId().getCaseCategoryId());
				
				result = new ModelAndView("editClaimRKI");
				result.addObject("caseCategory", theCase.getCaseCategoryId());
			
				result.addObject("caseCategoryId", caseCategoryId);
				result.addObject("providerId", providerId);
				result.addObject("itemList", mostFrequentItems);
				result.addObject("currencyList", currencyList);
				
				if (provider != null){
					result.addObject("providerName", provider.getProviderName());
				}
				if (member != null){
					result.addObject("memberName", member.getFirstName());
					result.addObject("cardNumber", member.getCurrentCardNumber());
					result.addObject("memberId", memberId);
				}
				if (theCase != null){
					result.addObject("admissionDate", theCase.getCaseStartTime());
					result.addObject("dischargeDate", theCase.getCaseEndTime());
					
				}
				
				if (mostFrequentItems != null){
					
					Iterator<Item> iterator = mostFrequentItems.iterator();
					
					Collection colItemName = new Vector();
					Collection colItemCode = new Vector();
					Collection colItemId = new Vector(); 
					Collection blankCol = new Vector();
					Collection valueCol = new Vector();
					Collection claimItemCol = new Vector();
					Collection caseItemCol = new Vector();
					Collection amounCollection = new Vector();
					
					int totalItem = 0;
					while (iterator.hasNext()){

						Item item = iterator.next();
						
						if (item != null){
							
							colItemName.add(item.getItemName());
							colItemCode.add(item.getItemCode());
							colItemId.add(item.getItemId());
							
							blankCol.add("");
							amounCollection.add("");
							valueCol.add("");
							totalItem+=1;
						}
					}
					
					request.setAttribute("claimItemCodeVector", colItemCode);
					request.setAttribute("claimItemNameVector", colItemName);
					request.setAttribute("claimItemVector", colItemId);
					request.setAttribute("claimAmountVector", amounCollection);
					request.setAttribute("claimValueVector", valueCol);
					request.setAttribute("claimDescVector", blankCol);
					request.setAttribute("claimItemIdVector", claimItemCol);
					
					request.setAttribute("totalItem", new Integer(totalItem));
					request.setAttribute("caseId", caseId);
					result.addObject("memberBenefitList", memberBenefits);
					result.addObject("productClausulList", memberClausuls);
				}
				

			}
			else if (navigation.equalsIgnoreCase("saveclaimrki")){
				
				String[] claimItemId = request.getParameterValues("itemId");
				String[] claimAmountId = request.getParameterValues("itemAmount");
				
				
				String[] itemValue = request.getParameterValues("itemValue");
				String[] description = request.getParameterValues("description");
			
				Date admisionDate = WebUtil.getParameterDate(request, "admissionDate");
				Date dischargeDate = WebUtil.getParameterDate(request, "dischargeDate");
				
				
				String diagnosis1Id = request.getParameter("diagnosis1Id");
				String diagnosis2Id = request.getParameter("diagnosis2Id");
				String diagnosis3Id = request.getParameter("diagnosis3Id");
				
				String hasComplication = request.getParameter("hasComplication");
				
				System.out.println("COMPLICATiON : "+hasComplication);
					
				Collection collection = new Vector();					
				ClaimItem claimItem = null;				
				Item item = null;
					
				if (claimItemId != null) {
					
					ClaimType ct = new ClaimType();
					ct.setClaimTypeId(ClaimType.CASHLESS);
					
					Claim claim = new Claim();
					claim.setClaimTypeId(ct);
					claim.setMemberId(member);
					claim.setProviderId(provider);
					claim.setProviderName(provider.getProviderName());
					CaseCategory cc = new CaseCategory();
					cc.setCaseCategoryId(caseCategoryId);
					claim.setCaseCategoryId(cc);
					claim.setAdmissionDate(admisionDate);
					claim.setDischargeDate(dischargeDate);
					if(hasComplication != null){
						claim.setHasComplication(Integer.valueOf(hasComplication));
					}
					
					if (claimCurrencyId != null){
						Currency currency = new Currency();
						currency.setCurrencyId(claimCurrencyId);
						claim.setClaimCurrencyId(currency);
					}
					
					if (diagnosis1Id != null && !diagnosis1Id.equalsIgnoreCase("")){
						Diagnosis dia1 = diagnosisService.get(Integer.valueOf(diagnosis1Id));
						claim.setDiagnosisId(dia1);
					}
					if (diagnosis2Id != null && !diagnosis2Id.equalsIgnoreCase("")){
						Diagnosis dia2 = diagnosisService.get(Integer.valueOf(diagnosis2Id));
						claim.setDiagnosisId(dia2);
					}
					if (diagnosis3Id != null && !diagnosis3Id.equalsIgnoreCase("")){
						Diagnosis dia3 = diagnosisService.get(Integer.valueOf(diagnosis3Id));
						claim.setDiagnosisId(dia3);
					}
					
										
					for (int i = 0; i < claimItemId.length; i++) {
						String itemVal = itemValue[i].trim();
						String amountId = claimAmountId[i].trim();
						
						if (!itemVal.equals("") && !amountId.equals("")){
							claimItem = new ClaimItem();
					
							item = itemService.get(Integer.valueOf(claimItemId[i]));
							

							claimItem.setItemId(item);
							
							claimItem.setClaimItemAmount(Double
									.valueOf(claimAmountId[i]));
							claimItem.setClaimItemValue(Double
									.valueOf(itemValue[i]));
							
							claimItem.setClaimItemDescription(description[i]);

							
							collection.add(claimItem);
						}
					}
				
					claim.setCaseId(theCase);
										
					Claim claimResult = claimService.createClaimRKI(claim, collection, user);
					
					String[] required = {"Claim.MemberId","Claim.ProductId.ProductId","Claim.MemberId.MemberGroupId","Claim.MemberId.CurrentPolicyId","Claim.CaseCategoryId","Claim.DiagnosisId","Claim.ClaimStatus"};
					
					Claim claimRes = claimService.get(claimResult.getClaimId(),required);
					
					
					
					result = new ModelAndView("detailClaimRKI");
					result.addObject("claim", claimRes);
					
					int diagnosis1DiagExclusion = 0;
					int diagnosis2DiagExclusion = 0;
					int diagnosis3DiagExclusion = 0;
					
					if (claimRes.getDiagnosisId() != null){
						String[] eqPolicyDiagParam = {"policyId","deletedStatus","diagnosisId.diagnosisId"};
//<<<<<<< .working
		        		Object[] eqPolicyDiagValue = {claimRes.getPolicyId(),0,claimRes.getDiagnosisId().getDiagnosisId()};        		
		        		
		        		int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null,null,eqPolicyDiagParam,eqPolicyDiagValue);
		        		
		        		if (totalPolicyExclusion > 0){
		        			diagnosis1DiagExclusion = 1;
		        		}
		        		else {
		        			String[] eqMemberDiag = {"memberId.memberId","deletedStatus","diagnosisId.diagnosisId"};
		        			Object[] eqMemberDiagValue = {claimRes.getMemberId().getMemberId(),0,claimRes.getDiagnosisId().getDiagnosisId()};
		        			
		        			int totalMemberExclusion = memberDiagnosisExclusionService.getTotal(null,null,eqMemberDiag,eqMemberDiagValue);
		        			
		        			if (totalMemberExclusion > 0){
		        				diagnosis1DiagExclusion = 1;
		        			}
		        		}
		        		

					}
					if (claimRes.getDiagnosis2Id() != null){
						String[] eqPolicyDiagParam = {"policyId","deletedStatus","diagnosisId.diagnosisId"};

		        		Object[] eqPolicyDiagValue = {claimRes.getPolicyId(),0,claimRes.getDiagnosis2Id().getDiagnosisId()};        		
		        		
		        		int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null,null,eqPolicyDiagParam,eqPolicyDiagValue);
		        		
		        		if (totalPolicyExclusion > 0){
		        			diagnosis2DiagExclusion = 1;
		        		}
		        		else {
		        			String[] eqMemberDiag = {"memberId.memberId","deletedStatus","diagnosisId.diagnosisId"};
		        			Object[] eqMemberDiagValue = {claimRes.getMemberId().getMemberId(),0,claimRes.getDiagnosis2Id().getDiagnosisId()};
		        			
		        			int totalMemberExclusion = memberDiagnosisExclusionService.getTotal(null,null,eqMemberDiag,eqMemberDiagValue);
		        			
		        			if (totalMemberExclusion > 0){
		        				diagnosis2DiagExclusion = 1;
		        			}
		        		}     		
					
					}
					
					if (claimRes.getDiagnosis3Id() != null){
						String[] eqPolicyDiagParam = {"policyId","deletedStatus","diagnosisId.diagnosisId"};

						Object[] eqPolicyDiagValue = {claimRes.getPolicyId(),0,claimRes.getDiagnosis3Id().getDiagnosisId()};        		

						int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null,null,eqPolicyDiagParam,eqPolicyDiagValue);

						if (totalPolicyExclusion > 0){
							diagnosis3DiagExclusion = 1;
							//add by mhi
							
							
						}
						else {
							String[] eqMemberDiag = {"memberId.memberId","deletedStatus","diagnosisId.diagnosisId"};
							Object[] eqMemberDiagValue = {claimRes.getMemberId().getMemberId(),0,claimRes.getDiagnosis3Id().getDiagnosisId()};

							int totalMemberExclusion = memberDiagnosisExclusionService.getTotal(null,null,eqMemberDiag,eqMemberDiagValue);

							if (totalMemberExclusion > 0){
								diagnosis3DiagExclusion = 1;
							}
						}
					}
					
					if (claimResult != null){
						Collection<ClaimItem> claimItemList = claimItemService.getClaimItem(claimResult.getClaimId());
						
						result.addObject("ClaimItems", claimItemList);
						
						result.addObject("claimStatus", claimResult.getClaimStatus().getCaseStatusId());
					}
				}
			}			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	
	public ModelAndView jsonTotalPendingClaimPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			String navigation = request.getParameter("navigation");
			result = new ModelAndView("jsonTotalPendingClaim");
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "OPENCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for OPENCLAIM access");
				return errorResult;
				
			}
			int total = 0;
			
			if (navigation.equalsIgnoreCase("jsontotalopenedc")){
				total = claimService.getTotalOpenEdcClaim();
			}
			else if (navigation.equalsIgnoreCase("jsontotalopenclaim")){
				total = claimService.getTotalOpenClaim();
			}
			else if (navigation.equalsIgnoreCase("jsontotalverifiedclaim")){
				total = claimService.getTotalVerifiedClaim();
			}
			else if (navigation.equalsIgnoreCase("jsontotalcaseclaim")){
				total = claimService.getTotalOpenCaseConvertedClaim();
			}
			else if (navigation.equalsIgnoreCase("jsontotalopencob")){
				//System.out.println("ClaimController::json getTotalCOBOpenClaim()");
				total = claimService.getTotalCOBOpenClaim();
			}
			result.addObject("result", total);
			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView printClaimRKIPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			String navigation = request.getParameter("navigation");
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			result = new ModelAndView("printClaimRKI");
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "OPENCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for OPENCLAIM access");
				return errorResult;
				
			}			
			if (claimId != null){
				String[] req = {"Claim.ProviderId","Claim.MemberId","Claim.MemberId.MemberGroupId"
						,"Claim.CaseCategoryId","Claim.DiagnosisId","Claim.ProductId", "Claim.CaseId"};
				
				Claim claim = claimService.get(claimId,req);
				
				Integer excessPaymentType = claim.getProductId().getExcessPaymentType();
				
				String[] eqParam = {"policyId.policyId","deletedStatus","caseCategoryId.caseCategoryId"};
				Object[] eqValue = {claim.getPolicyId(),0,claim.getCaseCategoryId().getCaseCategoryId()};

				int totalClausul = policyClausulService.getTotal(null,null,eqParam,eqValue);
				Collection<PolicyClausul> clausulList = policyClausulService.search(null,null,eqParam,eqValue,0,totalClausul);
				
				Collection<ClaimItem> claimItemList = claimItemService.getClaimItem(claimId);
				
				result.addObject("excessPaymentType", excessPaymentType);
				
				result.addObject("claim", claim);
				result.addObject("claimItemList", claimItemList);
				result.addObject("policyClausulList", clausulList);
			}
			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView attachSwipePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			String[] claimIds = request.getParameterValues("attach_claim_id");

			String navigation  = WebUtil.getParameterString(request, "navigation", "");
		
			java.io.Serializable pkey = batchClaimId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "ATTACHSWIPE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for ATTACHSWIPE access");
				return errorResult;
				
			}
			
			if (navigation.equalsIgnoreCase("attachswipe")){
				BatchClaim batch = batchClaimService.get(batchClaimId);
				
				if (batch.getBatchClaimType().getClaimTypeId().intValue() == ClaimType.CASHLESS){				
					String[] eqParam = {"providerId.providerId","isEDCBatchAssigned","tipe","deletedStatus","claimStatus.caseStatusId","clientId"};
					Object[] eqValue = {batch.getProviderId().getProviderId(),Integer.valueOf(0),Claim.CLAIM_SWIPE,Integer.valueOf(0),
							Claim.CLAIM_CHECKED,batch.getClientId().getClientId()};
					
					String[] required = {"Claim.MemberId","Claim.ProviderId"};
					int totalClaim = claimService.getTotal(null,null,eqParam,eqValue);
					Collection<Claim> claimList = claimService.search(null,null,eqParam,eqValue,required,0,totalClaim );
					
					result = new ModelAndView("listPendingSwipeClaim");
					result.addObject("Claims",claimList);
					result.addObject("batchClaimId", batchClaimId);
					result.addObject("batchClaim", batch);
				}
				
			}
			else if (navigation.equalsIgnoreCase("saveattachswipe")){
			
				
				if (claimIds != null){
					Integer[] attachId = new Integer[claimIds.length];
					
					for (int i = 0; i < attachId.length; i++){
						attachId[i] = Integer.valueOf(claimIds[i]);
					}
				
				
					claimService.attachSwipeClaim(batchClaimId, attachId, user);
				}
				
				result = new ModelAndView(new RedirectView("batchclaim?navigation=detail&batchClaimId="+batchClaimId));
			}
			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView attachCasePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			String[] claimIds = request.getParameterValues("attach_claim_id");

			String navigation  = WebUtil.getParameterString(request, "navigation", "");
		
			java.io.Serializable pkey = batchClaimId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "ATTACHCASE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for ATTACHSWIPE access");
				return errorResult;
				
			}
			
			if (navigation.equalsIgnoreCase("attachcase")){
				BatchClaim batch = batchClaimService.get(batchClaimId);
				
				if (batch.getBatchClaimType().getClaimTypeId().intValue() == ClaimType.CASHLESS){				
					String[] eqParam = {"providerId.providerId","isEDCBatchAssigned","tipe","deletedStatus","claimStatus.caseStatusId"};
					Object[] eqValue = {batch.getProviderId().getProviderId(),Integer.valueOf(0),Claim.CLAIM_CASE,Integer.valueOf(0),
							Claim.CLAIM_CHECKED};
					
					String[] required = {"Claim.MemberId","Claim.ProviderId"};
					int totalClaim = claimService.getTotal(null,null,eqParam,eqValue);
					Collection<Claim> claimList = claimService.search(null,null,eqParam,eqValue,required,0,totalClaim );
					
					result = new ModelAndView("listPendingSwipeClaim");
					result.addObject("Claims",claimList);
					
					result.addObject("batchClaimId", batchClaimId);
					result.addObject("batchClaim", batch);
				}
				
			}
			else if (navigation.equalsIgnoreCase("saveattachcase")){
			
				
				if (claimIds != null){
					Integer[] attachId = new Integer[claimIds.length];
					
					for (int i = 0; i < attachId.length; i++){
						attachId[i] = Integer.valueOf(claimIds[i]);
					}
				
				
					claimService.attachSwipeClaim(batchClaimId, attachId, user);
				}
				
				result = new ModelAndView(new RedirectView("batchclaim?navigation=detail&batchClaimId="+batchClaimId));
			}
			
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}
	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILCLAIM access");
				return errorResult;
				
			}
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,"navigation", "");
			

			result = new ModelAndView(location);
			java.io.Serializable pkey = claimId;

			//modified by aju on 20150928, add link Claim->Member->Parent
			String[] required = {"Claim.MemberId.MemberGroupId","Claim.MemberId.RelationshipId",					
					"Claim.BatchClaimId.ClaimCurrency","Claim.BatchClaimId.PaymentCurrency",
					"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.CaseId" ,"Claim.CobClaimReferenceId",
					"Claim.MemberId.ParentMember"};

			Claim object = claimService.get(pkey, required);
			
			//add by aju on 20150928, sorry, no url param changes for member access fufufu :D
			if(isIFrameSession && sessionMemberParentId != null && object != null){
				System.out.println("it\'s still on member(" + sessionMemberId + ") login session fufufu...");
				//check first if the memberId passed from the url is on the same parentId fufufu
				java.io.Serializable theMemberIdParam = object.getMemberId().getParentMember().getMemberId();
				Member checkMember = memberService.get(theMemberIdParam);
				
				if(checkMember!=null){
					//make it back to default if the memberId from param is injected wekawekaweka :))))
					if(checkMember.getParentMember().getMemberId() != Integer.parseInt( sessionMemberParentId)){
						System.out.println("aha! gotcha! sorry, you cannot see others claim data fufufu :D");
						//memberId = Integer.parseInt(sessionMemberId);
						
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","OtherClaimAccessDenied");			
						errorResult.addObject("errorMessage", "Sorry member(" + sessionMemberId + "), you cannot see other claims freely :P");
						return errorResult;
						
					}
				}
			}
			
			

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.claim", null, "fail", Locale.getDefault()));
			}
			if (navigation.equals("approve")) {
				boolean res = claimService.approve(object, user);
				
				if (res){
					
					request.setAttribute("alert", alertProperties.getMessage(
							"success.verify.claim", null, "success", Locale.getDefault()));
				}
				else {
					request.setAttribute("alert", alertProperties.getMessage(
							"fail.verify.claim", null, "fail", Locale.getDefault()));	
				}
				
			}
			if (object != null ){
				int status = object.getClaimStatus().getCaseStatusId().intValue();
				if (status == Claim.CLAIM_PENDING || status == Claim.CLAIM_PENDING_DOCUMENT || status == Claim.CLAIM_PENDING_INVESTIGATION){
					
					PendingClaim pending =  pendingClaimService.getPendingClaim(claimId);
					
					if (pending != null){
						result.addObject("pendingClaimId", pending.getPendingClaimId());
					}
				}
				
				Integer diagnosisId  = null;
				
				MemberProduct activeProduct = memberProductService.getMemberActiveProduct(object.getMemberId().getMemberId(), object.getCaseCategoryId().getCaseCategoryId());
				if (activeProduct != null){
						int disabilityLength = activeProduct.getDisabilityLength() == null ? 60 : activeProduct.getDisabilityLength();
						
						DateTime dtAdmision = new DateTime(object.getAdmissionDate().getTime()).minus(1);
						
						DateTime lastDate = dtAdmision.minusDays(disabilityLength);
						
						Date minDate = new Date(lastDate.getMillis());
						Date maxDate = new Date(dtAdmision.getMillis());
						
					if (object.getDiagnosisId() != null){
						diagnosisId = object.getDiagnosisId().getDiagnosisId();
						
						int totalClaim = claimService.getClaimDisabilityDiagnosis(diagnosisId,object.getCaseCategoryId().getCaseCategoryId(),
								object.getMemberId().getMemberId(), minDate, maxDate);
						
						if (totalClaim > 0){
							result.addObject("diagnosis1Status", "disability");
						}
						else {
							result.addObject("diagnosis1Status", "nodisability");
						}
						
						MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(diagnosisId,object.getMemberId().getMemberId());
						
						
						if (exclusion != null && exclusion.getExpireDate() == null){							
							result.addObject("diag1exstat", "exclusion");
						}
						else if (exclusion != null && exclusion.getExpireDate() != null){
							Date expireDate = exclusion.getExpireDate();
							
							if (object.getAdmissionDate().before(expireDate)){
								result.addObject("diag1exstat", "exclusion");
							}
						}
					}
					
					if (object.getDiagnosis2Id() != null){
						
						

						MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(object.getDiagnosis2Id().getDiagnosisId(),
								object.getMemberId().getMemberId());
						
						
						if (exclusion != null && exclusion.getExpireDate() == null){							
							result.addObject("diag2exstat", "exclusion");
						}
						else if (exclusion != null && exclusion.getExpireDate() != null){
							Date expireDate = exclusion.getExpireDate();
							
							if (object.getAdmissionDate().before(expireDate)){
								result.addObject("diag2exstat", "exclusion");
							}
						}
					}
					if (object.getDiagnosis3Id() != null){
					
						
						MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(object.getDiagnosis3Id().getDiagnosisId(),
								object.getMemberId().getMemberId());
						
						
						if (exclusion != null && exclusion.getExpireDate() == null){							
							result.addObject("diag3exstat", "exclusion");
						}
						else if (exclusion != null && exclusion.getExpireDate() != null){
							Date expireDate = exclusion.getExpireDate();
							
							if (object.getAdmissionDate().before(expireDate)){
								result.addObject("diag3exstat", "exclusion");
							}
						}
					}
				}
		
			}
			/*
			 * 
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("claim", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView checkPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CHECKCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CHECKCLAIM access");
				return errorResult;
				
			}
			
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = claimId;
			String[] required = { "Claim.ClaimItems" };

			Collection claimItem = null;

			Claim object = claimService.get(pkey, required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.claim", null, "fail", Locale.getDefault()));
			} else {
				claimItem = object.getClaimItems();
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("claimItem", claimItem);
			result.addObject("claim", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	
	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			//Add by aju on 20150326, for client iFrame support :D
			String usingIFrame = WebUtil.getParameterString(request, "iframe", "no");
			
			//modified by aju on 20150417, for security purpose, use toket eeeh token :D
//			String iFrameUser = WebUtil.getParameterString(request, "uid", "");
//			String iFramePass = WebUtil.getParameterString(request, "pid", "");
//			String iFrameClientMemberId = WebUtil.getParameterString(request, "mid", "");
//			//Add by aju on 20150410, add level Client login iFram login :D
//			String iFrameLevelLogin = WebUtil.getParameterString(request, "level", "");
//			String iFrameClientId = null;
			
			String iFrameUser = "", iFramePass = "", iFrameClientMemberId = "", iFrameLevelLogin = "", iFrameClientId = null;
			
			//Add by aju on 20150417, read the token :D
			String iFrameToken = WebUtil.getParameterString(request, "token", "");
			if(iFrameToken.length()>0){
				String decodedToken = SecurityUtil.saltDecrypt(iFrameToken);
				System.out.println("Decoded Token : " + decodedToken);
				if(decodedToken!=iFrameToken){
					String[] splitToken = decodedToken.split("&");
					if(splitToken.length>0 && splitToken.length<5){
						iFrameUser = splitToken[0].replace("uid=", "");
						iFramePass = splitToken[1].replace("pid=", "");
						iFrameLevelLogin = splitToken[2].replace("level=", "");
						iFrameClientMemberId = splitToken[3].replace("mid=","");
						System.out.println("splitDecode=>uid="+iFrameUser+"&pid="+iFrameUser+"&level="+iFrameLevelLogin+"&mid="+iFrameClientMemberId);
					}
				}
			}
			
			HttpSession session = request.getSession(false);
			//check if there's session for iframe
			boolean isIFrameSession = false;
			if(session != null){
				if(session.getAttribute("iframe") != null){
					isIFrameSession = (session.getAttribute("iframe").toString().equalsIgnoreCase("yes")?true:false);
				}
			}
	
			
			if(isIFrameSession){
				//get the iFrame saved parameter from session
				usingIFrame = session.getAttribute("iframe").toString();
				
				if(iFrameLevelLogin.length()>0 && !iFrameLevelLogin.equalsIgnoreCase(session.getAttribute("levelLogin").toString())){
					
				}
				else{
					//Add by aju on 20150410, get the level login from session :D
					iFrameLevelLogin = session.getAttribute("levelLogin").toString();
				}
				
				
				//modified by aju on 20150410, check if not null on level login member filtering id :D
				//iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				
				if (iFrameLevelLogin.equalsIgnoreCase("member")){
					if(iFrameClientMemberId.length() > 0 && !iFrameClientMemberId.equalsIgnoreCase((session.getAttribute("clientMemberId")==null?"":session.getAttribute("clientMemberId").toString()))){
						isIFrameSession = false;
					}
					else{
						iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
					}
					//iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				}
				/**
				 * update menggunakan IF karena JDK 6.0 tidak support SWITCH menggunakan string
				switch (iFrameLevelLogin) {
				case "client":
					break;
				case "member":
					iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
					break;
				}
				*/
			}
			
			if(usingIFrame.equalsIgnoreCase("yes")){
				//check if there's iframe session
				if(!isIFrameSession){
					//make the dummy login
					String sessId = session == null ? "" : session.getId();
					//Check if they can do login
					boolean res = userService.login(iFrameUser, iFramePass, sessId,	request.getRemoteAddr());
					
					//if(res && (iFrameClientMemberId.trim().length()>0)){
					if(res && (iFrameLevelLogin!=null)){
						System.out.println("IFRAME ("+ iFrameLevelLogin + ") login success");
						
						//modified by aju on 20150410, add level login filtering for iFrame :d
						if (iFrameLevelLogin.equalsIgnoreCase("member")){
							Member theMember = memberService.getMember(iFrameClientMemberId);
							//add by aju on 20150410, catch it if there's no matched member id :D
							if(theMember==null)
							{
								System.out.println("IFRAME ("+ iFrameLevelLogin +") login failed!");
								ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
								errorResult.addObject("errorType","iFrameAccessDenied");			
								errorResult.addObject("errorMessage", "Member ID Not Found");
								return errorResult;
							}
							session.setAttribute("clientMemberId", iFrameClientMemberId);
							session.setAttribute("theMember", theMember);
						}
						/**
						switch (iFrameLevelLogin) {
						case "client":
							
							break;
						case "member":
							Member theMember = memberService.getMember(iFrameClientMemberId);
							//add by aju on 20150410, catch it if there's no matched member id :D
							if(theMember==null)
							{
								System.out.println("IFRAME ("+ iFrameLevelLogin +") login failed!");
								ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
								errorResult.addObject("errorType","iFrameAccessDenied");			
								errorResult.addObject("errorMessage", "Member ID Not Found");
								return errorResult;
							}
							session.setAttribute("clientMemberId", iFrameClientMemberId);
							session.setAttribute("theMember", theMember);				
							break;
						}
						*/
						User theUser = userService.getUser(iFrameUser);
						
						theUser.setPassword("");
						
						session.setAttribute("theUser", theUser);
						session.setAttribute("iframe", usingIFrame);
						//add by aju on 20150410, add level login filtering :D
						session.setAttribute("levelLogin", iFrameLevelLogin);
						
						/* remove by aju on 20150410, use level login iFrame filtering :D
						session.setAttribute("clientMemberId", iFrameClientMemberId);
						
						//Add by aju on 20150327, to keep the principal :D
						Member theMember = memberService.getMember(iFrameClientMemberId);
						session.setAttribute("theMember", theMember);
						*/
						isIFrameSession = true;
					}
					else{
						//If login failed, redirect to error  page
						System.out.println("IFRAME ("+ iFrameLevelLogin +") login failed!");
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","iFrameAccessDenied");			
						errorResult.addObject("errorMessage", "You Are Not Authorized for IFRAME access");
						return errorResult;
					}
				}
			}
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
			}
			
			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			String searchStatus = WebUtil.getParameterString(request,
					"status","");
			
			Integer batchClaimId = WebUtil.getParameterInteger(request,"batchClaimId");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
                        Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation","");
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");

			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateby", "");
			
			Double claimCharges = 0.0;
			Double claimApprovedValue = 0.0;
			Double excessCharges = 0.0;
			
			if (currentNavigation.equalsIgnoreCase("list")){
				location = "listClaim";
			}
			else if (currentNavigation.equalsIgnoreCase("listgroup")){
				location = "listGroupClaim";
			}
			else if (currentNavigation.equalsIgnoreCase("listmember")){
				location = "listMemberClaim";
			}
			else if (currentNavigation.equalsIgnoreCase("listpolicy")){
				location = "listPolicyClaim";
			}
			else if (currentNavigation.equalsIgnoreCase("detail") || navigation.equalsIgnoreCase("golistdetailclaim")){
				location = "listDetailClaim";
			}
			
			result = new ModelAndView(location);

			request.setAttribute("navigation", navigation);
			result.addObject("subnavigation",subnavigation);

			countSet = 50;
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortClaimNo = true, sortMember = true, sortCardNo = true, sortProvider = true,
					sortAdmissionDate = true, sortClaimDate = true, sortClaimCharge = true, sortClaimApprove = true,
					sortConfirmDate = true, sortStatus = true;
			boolean order = true;
			
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			//Add by aju on 20150819, for not equal
			Vector vNeqP = new Vector();
			Vector vNeqQ = new Vector();
			
			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			System.out.println("ClaimController::navigation->" + navigation);
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golistdetailclaim")
					|| navigation.equals("gosearchbysort") || navigation.equals("list") || navigation.equals("listmember") || navigation.equals("listpolicy") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext !=null && !searchtext.equals("")) {
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("claimNumber")) {
						vLikeP.add("claimNumber");
						vLikeQ.add(searchtext);
						}
					if (searchby.equalsIgnoreCase("claimRemarks")) {
						vLikeP.add("claimRemarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")){
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyNumber")){
						vLikeP.add("memberNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberGroupName")){
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("officer")){
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equals("clientName")){ //cek
						vLikeP.add("memberId.clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("invoiceNumber")){
						vLikeP.add("batchClaimId.invoiceNumber");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("paymentNumber")){
						vLikeP.add("paymentNumber");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("cardNumber")){
						vLikeP.add("cardNumber");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("diagnosis1Code")){
						vLikeP.add("diagnosis1Code");
						vLikeQ.add(searchtext);
					}
				}
				
				if (searchStatus != null && !searchStatus.equalsIgnoreCase("") ) {
					vEqP.add("claimStatus.caseStatusId");
					vEqQ.add(Integer.valueOf(searchStatus));
				}
				
				Claim x = new Claim();
				Timestamp modifTime = x.getModifiedTime();
//				modifTime.substring(0, 10);
				request.setAttribute("modifTime", modifTime);
				System.out.println(modifTime);

			}
			
			MemberGroup memberGroupObject = null;
			
			if(memberGroupId != null){
				try
				{
				java.io.Serializable memberGrouppkey = memberGroupId;
				System.out.println("member group id : "+memberGroupId);
				String[] memberGroupRequired = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
				memberGroupObject = memberGroupService.get(memberGrouppkey, memberGroupRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member group object : "+memberGroupObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			
			result.addObject("batchClaimId", batchClaimId);
			result.addObject("memberId", memberId);
			result.addObject("clientId", clientId);
			result.addObject("policyId", policyId);
			result.addObject("providerId", providerId);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("memberGroup", memberGroupObject);
			
			
			if (navigation.equalsIgnoreCase("list") || currentNavigation.equalsIgnoreCase("list") ){
				vEqP.add("batchClaimId.batchClaimId");
				vEqQ.add(batchClaimId);
				
				String[] reqBatch = {"BatchClaim.PaymentRecipient","BatchClaim.ProviderId","BatchClaim.MemberGroupId",
						"BatchClaim.MemberId","BatchClaim.BatchClaimStatus","BatchClaim.PaymentMethod","BatchClaim.ClaimCurrency"};
				
				BatchClaim batchClaim = batchClaimService.get(batchClaimId,reqBatch);
				if (batchClaim != null){
					result.addObject("batchClaim", batchClaim);
				}
				
			}
			else if (navigation.equalsIgnoreCase("listmember") || currentNavigation.equalsIgnoreCase("listmember")){
				vEqP.add("memberId.memberId");
				vEqQ.add(memberId);
				
			}
			else if (navigation.equalsIgnoreCase("listdetailclaim") || currentNavigation.equalsIgnoreCase("listdetailclaim") || currentNavigation.equalsIgnoreCase("listclaim")
					|| currentNavigation.equalsIgnoreCase("listclaimProcedure") ){
				vEqP.add("memberId.memberId");
				vEqQ.add(memberId);

				Claim claim = claimService.get(claimId);
				result.addObject("claim", claim);
			}
			else if (navigation.equalsIgnoreCase("listclient") || currentNavigation.equalsIgnoreCase("listclient")){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
				
			}
			else if (navigation.equalsIgnoreCase("listprovider") || currentNavigation.equalsIgnoreCase("listprovider")){
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			
			}
            else if (navigation.equalsIgnoreCase("listpolicy")){
                vEqP.add("policyId");
                vEqQ.add(policyId);
            }
			else if (navigation.equalsIgnoreCase("listgroup") || currentNavigation.equalsIgnoreCase("listgroup")){
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			
			}
			//Add by aju on 20150819, for cob claim filter
			else if(navigation.equalsIgnoreCase("searchopencob")){
				vEqP.add("claimStatus.caseStatusId");
				vEqQ.add(Claim.CLAIM_OPEN);
				vNeqP.add("memberId.linkedCardNumber");
				vNeqQ.add("");
			}
			else {
			
				if (user != null && user.getUser().getUserType() != null){
					if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
						vEqP.add("memberId.memberGroupId.memberGroupId");
						vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
						//add by aju on 20150326, for client iFrame suppport :D
						if(isIFrameSession){
							vEqP.add("memberId.parentMember.customerNumber");
							vEqQ.add(iFrameClientMemberId);
						}
					}
					else if (user.getUser().getUserType().intValue() == User.CLIENT){
						
							vEqP.add("batchClaimId.clientId.clientId");
							vEqQ.add(user.getUser().getClientId().getClientId());
							if (memberGroupId != null){
								vEqP.add("memberGroupId");
								vEqQ.add(memberGroupId);
							}
											}
					else if (user.getUser().getUserType().intValue() == User.PROVIDER){
						vEqP.add("providerId.providerId");
						vEqQ.add(user.getUser().getProviderId().getProviderId());
						
						//443 - Only cashless claim is allowed in provider
						vEqP.add("claimTypeId.claimTypeId");
						vEqQ.add(Integer.valueOf(2));
					}
					else if (user.getUser().getUserType().intValue() == User.BROKER){
						//vEqP.add("providerId.providerId");
						//vEqQ.add(user.getUser().getProviderId().getProviderId());
						
						vEqP.add("brokerId");
						vEqQ.add(user.getUser().getBrokerId().getBrokerId());
						
						if (memberGroupId != null){
							vEqP.add("memberGroupId");
							vEqQ.add(memberGroupId);
						}
					}
					else if (user.getUser().getUserType().intValue() == User.MEMBER){
						vEqP.add("memberId.parentMember.memberId");
						vEqQ.add(user.getUser().getMemberId().getMemberId());
						
						vEqP.add("claimTypeId.claimTypeId");
						vEqQ.add(ClaimType.REIMBURESEMENT);
					}
					else {
						if (memberGroupId != null){
							vEqP.add("memberGroupId");
							vEqQ.add(memberGroupId);
						}
					}
									
				}
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
			
			//Add by aju on 20150819, for not equal
			String sNeqP[] = new String[vNeqP.size()];
			vNeqP.toArray(sNeqP);
			Object sNeqQ[] = new Object[vNeqP.size()];
			vNeqQ.toArray(sNeqQ);

			String[] betweenBy = null;
			Object[] minDate = null;
			Object[] maxDate = null;
			
			if(!dateBy.equalsIgnoreCase("")){
				
				if (minimumDate != null && maximumDate != null){
					
					betweenBy = new String[1];
					betweenBy[0] = dateBy;
					minDate = new Date[1];
					minDate[0] = minimumDate;
					
					maxDate = new Date[1];
					maxDate[0] = maximumDate;
					
					if (dateBy.equalsIgnoreCase("createdTime")){
						minDate = new Timestamp[1];
						maxDate = new Timestamp[1];
						
						minDate[0] = new java.sql.Timestamp(minimumDate.getTime());
						maxDate[0] = new java.sql.Timestamp(maximumDate.getTime());
					}
				}
			}
			
			//Modified by aju on 20150819, for cob filter
			//count = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate);
			if(navigation.equalsIgnoreCase("searchopencob")){
				count = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, sNeqP, sNeqQ, betweenBy,minDate,maxDate);
				System.out.println("ClaimController::Count(searchopencob)->" + count);
			}
			else{
				count = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate);
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
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			result.addObject("claimCharges", claimCharges);
			result.addObject("claimApprovedValue", claimApprovedValue);
			result.addObject("excessCharges", excessCharges);
			
			String required[] = new String[] {
			// foreign affairs
					"Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
					"Claim.BatchClaimId", "Claim.CaseCategoryId","Claim.PaymentId"
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("claimnumber")){
						sortByColumn = "claimNumber";
						Boolean sortOrderClaimNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderClaimNo", ""));
						sortClaimNo = !sortOrderClaimNo;
						order = sortClaimNo;
					}else if(sortcolumn.equalsIgnoreCase("membername")){
						sortByColumn = "memberId.firstName";
						Boolean sortOrderMemberName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderMemberName", ""));
						sortMember = !sortOrderMemberName;
						order = sortMember;
					}else if(sortcolumn.equalsIgnoreCase("cardnumber")){
						sortByColumn = "cardNumber";
						Boolean sortOrderCardNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderCardNo", ""));
						sortCardNo = !sortOrderCardNo;
						order = sortCardNo;
					}else if(sortcolumn.equalsIgnoreCase("providername")){
						sortByColumn = "providerName";
						Boolean sortOrderProvider = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderProvider", ""));
						sortProvider = !sortOrderProvider;
						order = sortProvider;
					}else if(sortcolumn.equalsIgnoreCase("admissiondate")){
						sortByColumn = "admissionDate";
						Boolean sortOrderAdmission = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderAdmission", ""));
						sortAdmissionDate = !sortOrderAdmission;
						order = sortAdmissionDate;
					}else if(sortcolumn.equalsIgnoreCase("claimdate")){
						sortByColumn = "claimDate";
						Boolean sortOrderClaim = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderClaim", ""));
						sortClaimDate = !sortOrderClaim;
						order = sortClaimDate;
					}else if(sortcolumn.equalsIgnoreCase("claimcharge")){
						sortByColumn = "claimChargeValue";
						Boolean sortOrderCharge = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderCharge", ""));
						sortClaimCharge = !sortOrderCharge;
						order = sortClaimCharge;
					}else if(sortcolumn.equalsIgnoreCase("claimapprove")){
						sortByColumn = "claimApprovedValue";
						Boolean sortOrderApprove = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderApprove", ""));
						sortClaimApprove = !sortOrderApprove;
						order = sortClaimApprove;
					}else if(sortcolumn.equalsIgnoreCase("paymentconfirm")){
						sortByColumn = "paymentConfirmDate";
						Boolean sortOrderConfirmDate = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderConfirmDate", ""));
						sortConfirmDate = !sortOrderConfirmDate;
						order = sortConfirmDate;
					}else{
						sortByColumn = "claimStatus.caseStatusName";
						Boolean sortOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderStatus", ""));
						sortStatus = !sortOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("claimnumber")){
						sortClaimNo = order;
					}else if(sortcolumn.equalsIgnoreCase("membername")){
						sortMember = order;
					}else if(sortcolumn.equalsIgnoreCase("cardnumber")){
						sortCardNo = order;
					}else if(sortcolumn.equalsIgnoreCase("providername")){
						sortProvider = order;
					}else if(sortcolumn.equalsIgnoreCase("admissiondate")){
						order = sortAdmissionDate;
					}else if(sortcolumn.equalsIgnoreCase("claimdate")){
						sortClaimDate = order;
					}else if(sortcolumn.equalsIgnoreCase("claimcharge")){
						sortClaimCharge = order;
					}else if(sortcolumn.equalsIgnoreCase("claimapprove")){
						sortClaimApprove = order;
					}else if(sortcolumn.equalsIgnoreCase("paymentconfirm")){
						sortConfirmDate = order;
					}else{
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				//modified by aju on 20150819, add for searchopencob
				collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,order,sortByColumn,
						required, rowsetint, countSet.intValue());
				if(navigation.equalsIgnoreCase("searchopencob")){
					collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ, sNeqP, sNeqQ, betweenBy,minDate,maxDate,order,sortByColumn,
							required, rowsetint, countSet.intValue());
				}
				else{
					collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,order,sortByColumn,
							required, rowsetint, countSet.intValue());
				}
				
			}else{
				//modified by  aju on 20150819, add for cob searchopencob
//				collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,false,"claimId",
//						required, rowsetint, countSet.intValue());
				if(navigation.equalsIgnoreCase("searchopencob")){
					collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ, sNeqP, sNeqQ, betweenBy,minDate,maxDate,false,"claimId",
							required, rowsetint, countSet.intValue());
				}
				else{
					collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,false,"claimId",
							required, rowsetint, countSet.intValue());
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
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
				collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,false,"claimId",
						required, rowsetint, countSet.intValue());
			}

			double totalClaimValue = 0;
			double totalApprovedValue = 0;
			
			if (collection != null){
				Iterator<Claim> iterator = collection.iterator();
				
				if (iterator != null){
					while (iterator.hasNext()){
						Claim tmp = iterator.next();
						
						if (tmp != null){
							totalClaimValue += tmp.getClaimChargeValue();
							totalApprovedValue += tmp.getClaimApprovedValue();
						}
					}
				}
			}
			
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
			
			request.setAttribute("totalClaimValue", totalClaimValue);
			request.setAttribute("totalClaimApprovedValue", totalApprovedValue);
			//request.setAttribute("minimum_date", minimumDate);
			//request.setAttribute("maximum_date", maximumDate);
			request.setAttribute("dateby", dateBy);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
            request.setAttribute("policyId", policyId);
            request.setAttribute("claimId", claimId);
            request.setAttribute("sortcolumn", sortcolumn);
		
                        
            if (memberId != null){
				String[] requiredMember = {"Member.MemberGroupId","Member.ClientId",
						"Member.RelationshipId","Member.ParentMember","Member.CurrentPolicyId"};
				Member object = memberService.get(memberId,requiredMember);
				result.addObject("member", object);
				
				//hitung umur
				Date umur =  object.getBirthday();
				Calendar dob = Calendar.getInstance();  
				dob.setTime(umur);  
				Calendar today = Calendar.getInstance();  
				int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR); 
				if ((today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
				    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) ||
				    today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
					age--;  
				}
				result.addObject("age", age);
				//end
			}
            
            Policy policyObject = null;
			
			if(policyId != null){
				try
				{
				java.io.Serializable policypkey = policyId;
				System.out.println("member policy id : "+policyId);
				String[] policyRequired = {"Policy.BrokerId","Policy.ClientId","Policy.ProductType","Policy.CardTypeId"};
				policyObject = policyService.get(policypkey, policyRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			Claim claimObject = null;
			
			if(claimId != null){
				try
				{
				java.io.Serializable claimkey = claimId;
				System.out.println("claim id : "+claimId);
				String[] claimRequired = {"Claim.BatchClaimId","Claim.MemberId",
						"Claim.CaseCategoryId","Claim.MemberId.MemberGroupId","Claim.CaseId",
						"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.CobClaimReferenceId","Claim.BatchClaimId.ClaimCurrency"};
				claimObject = claimService.get(claimkey, claimRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			result.addObject("policy", policyObject);
			result.addObject("Claims", collection);
			result.addObject("claim", claimObject);
			
//			request.setAttribute("modifTime",claimObject.getModifiedTime());
			
			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			
			request.setAttribute("sortAdmissionDate", sortAdmissionDate);
			request.setAttribute("sortCardNo", sortCardNo);
			request.setAttribute("sortClaimApprove", sortClaimApprove);
			request.setAttribute("sortClaimCharge", sortClaimCharge);
			request.setAttribute("sortClaimDate", sortClaimDate);
			request.setAttribute("sortClaimNo", sortClaimNo);
			request.setAttribute("sortConfirmDate", sortConfirmDate);
			request.setAttribute("sortMember", sortMember);
			request.setAttribute("sortProvider", sortProvider);
			request.setAttribute("sortStatus", sortStatus);

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	
	
	public ModelAndView searchEDCPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			
			
			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			String searchStatus = WebUtil.getParameterString(request,
					"status","");
			
			Integer batchClaimId = WebUtil.getParameterInteger(request,"batchClaimId");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
                        Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation","");
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");

			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateby", "");
			
			Double claimCharges = 0.0;
			Double claimApprovedValue = 0.0;
			Double excessCharges = 0.0;
			
			
			result = new ModelAndView(location);

			result.addObject("subnavigation",subnavigation);

			countSet = 50;
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

			if (navigation.equals("gosearchedc") || navigation.equals("golookupedc")) {

				if (searchby != null) {
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("claimNumber")) {
						vLikeP.add("claimNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("claimRemarks")) {
						vLikeP.add("claimRemarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("memberName")){				
						
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyNumber")){
						vLikeP.add("memberNumber");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("memberGroupName")){
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("officer")){
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("iterative")) {
						vLikeP.add("iterative");
						vLikeQ.add(searchtext);
					}
					if (searchby.equals("clientName")){
						vLikeP.add("memberId.clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("invoiceNumber")){
						vLikeP.add("batchClaimId.invoiceNumber");
						vLikeQ.add(searchtext);
					}
				}

				if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
					vEqP.add("claimStatus.caseStatusId");
					vEqQ.add(Integer.valueOf(searchStatus));
				}
				
				

			}
			
			vLikeP.add("claimNumber");
			vLikeQ.add("SW");
			
			vEqP.add("claimStatus.caseStatusId");
			vEqQ.add(Claim.CLAIM_CHECKED);
			
			vEqP.add("isEDCBatchAssigned");
			vEqQ.add(0);
			
			result.addObject("batchClaimId", batchClaimId);
			result.addObject("memberId", memberId);
			result.addObject("clientId", clientId);
			result.addObject("providerId", providerId);
			result.addObject("memberGroupId", memberGroupId);
			
			
					
			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
					vEqP.add("memberId.memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
				}
				else if (user.getUser().getUserType().intValue() == User.CLIENT){
					
						vEqP.add("batchClaimId.clientId.clientId");
						vEqQ.add(user.getUser().getClientId().getClientId());
						if (memberGroupId != null){
							vEqP.add("memberGroupId");
							vEqQ.add(memberGroupId);
						}
				}
				else if (user.getUser().getUserType().intValue() == User.PROVIDER){
					vEqP.add("providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}
				
				else if (user.getUser().getUserType().intValue() == User.BROKER){
					
					vEqP.add("brokerId");
					vEqQ.add(user.getUser().getBrokerId().getBrokerId());
					
					if (memberGroupId != null){
						vEqP.add("memberGroupId");
						vEqQ.add(memberGroupId);
					}
				}
				else {
					if (memberGroupId != null){
						vEqP.add("memberGroupId");
						vEqQ.add(memberGroupId);
					}
				}
								
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

			String[] betweenBy = null;
			Object[] minDate = null;
			Object[] maxDate = null;
			
			if(!dateBy.equalsIgnoreCase("")){
				
				if (minimumDate != null && maximumDate != null){
					
					betweenBy = new String[1];
					betweenBy[0] = dateBy;
					minDate = new Date[1];
					minDate[0] = minimumDate;
					
					maxDate = new Date[1];
					maxDate[0] = maximumDate;
					
					if (dateBy.equalsIgnoreCase("createdTime")){
						minDate = new Timestamp[1];
						maxDate = new Timestamp[1];
						
						minDate[0] = new java.sql.Timestamp(minimumDate.getTime());
						maxDate[0] = new java.sql.Timestamp(maximumDate.getTime());
					}
				}
			}
			
			count = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate);

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
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			result.addObject("claimCharges", claimCharges);
			result.addObject("claimApprovedValue", claimApprovedValue);
			result.addObject("excessCharges", excessCharges);
			
			String required[] = new String[] {
			// foreign affairs
					"Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
					"Claim.BatchClaimId", "Claim.CaseCategoryId",
			// foreign affairs end
			};
			
			collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,false,"claimId",
					required, rowsetint, countSet.intValue());

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
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
				collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,false,"claimId",
						required, rowsetint, countSet.intValue());
			}

			double totalClaimValue = 0;
			double totalApprovedValue = 0;
			
			if (collection != null){
				Iterator<Claim> iterator = collection.iterator();
				
				if (iterator != null){
					while (iterator.hasNext()){
						Claim tmp = iterator.next();
						
						if (tmp != null){
							totalClaimValue += tmp.getClaimChargeValue();
							totalApprovedValue += tmp.getClaimApprovedValue();
						}
					}
				}
			}
			
			request.setAttribute("totalClaimValue", totalClaimValue);
			request.setAttribute("totalClaimApprovedValue", totalApprovedValue);
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);
			request.setAttribute("dateby", dateBy);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
                        request.setAttribute("policyId", policyId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Claims", collection);
			
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
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView exportPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			
			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			String searchStatus = WebUtil.getParameterString(request,
					"status","");
			

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");

			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateby", "");
			
			Double claimCharges = 0.0;
			Double claimApprovedValue = 0.0;
			Double excessCharges = 0.0;
		
			
			result = new ModelAndView(location);

			result.addObject("subnavigation",subnavigation);

			countSet = 50;
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

			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("claimNumber")) {
						vLikeP.add("claimNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("claimRemarks")) {
						vLikeP.add("claimRemarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("memberName")){
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyNumber")){
						vLikeP.add("memberId.customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberGroupName")){
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("iterative")) {
						vLikeP.add("iterative");
						vLikeQ.add(searchtext);
					}
					if (searchby.equals("clientName")){
						vLikeP.add("memberId.clientId.clientName");
						vLikeQ.add(searchtext);
					}
				}

				if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
					vEqP.add("claimStatus.caseStatusId");
					vEqQ.add(Integer.valueOf(searchStatus));
				}

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

			String[] betweenBy = null;
			Object[] minDate = null;
			Object[] maxDate = null;
			
			if(!dateBy.equalsIgnoreCase("")){
				
				if (minimumDate != null && maximumDate != null){
					
					betweenBy = new String[1];
					betweenBy[0] = dateBy;
					minDate = new Date[1];
					minDate[0] = minimumDate;
					
					maxDate = new Date[1];
					maxDate[0] = maximumDate;
				}
			}
			
			count = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate);
			
			collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,false,"claimId",0,count);
			int idx = 1;
			String data = "No,Dari,Tgl. Terima, Qty,  Registrasi, Verifikasi, Benefit Check,No. Klaim,No. CDV, Jumlah";
			data += "\n\n";
			
			if (collection != null) {
				Iterator<Claim> iterator = collection.iterator();

				if (iterator != null) {
					Claim claim = null;
		
					String paymentRecipient = "";
					String verifikasi = "";
					String registrasi = "X";
					String benefitCheck = "";
					String cdvNumber = "";

					while (iterator.hasNext()) {

						claim = iterator.next();

						if (claim != null) {

							if (claim.getBatchClaimId().getPaymentRecipient().getPaymentRecipientId() == PaymentRecipient.MEMBER){
								paymentRecipient = claim.getBatchClaimId().getMemberId().getFirstName();
							}
							else if (claim.getBatchClaimId().getPaymentRecipient().getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP){
								paymentRecipient = claim.getBatchClaimId().getMemberGroupId().getGroupName();
							}
							else if (claim.getBatchClaimId().getPaymentRecipient().getPaymentRecipientId() == PaymentRecipient.PROVIDER){
								paymentRecipient = claim.getBatchClaimId().getProviderId().getProviderName();
							}
							
							
							if (claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_CHECKED){
								verifikasi = "X";
								benefitCheck = "X";
							}
							else if (claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_VERIFIED){
								verifikasi = "X";
							}
							else if (claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PAID){
								verifikasi = "X";
								benefitCheck = "X";
								cdvNumber = claim.getPaymentId().getPaymentCounterNumber();
								
							}
							else if (claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PAYMENT_ISSUED){
								verifikasi = "X";
								benefitCheck = "X";
								
								cdvNumber = claim.getPaymentId().getPaymentCounterNumber();
							}
							
							data += idx + "," + paymentRecipient + ","
									+ claim.getBatchClaimId().getBatchClaimDate().toString() + ","
									+ claim.getBatchClaimId().getTotalClaim() + "," + registrasi+ "," 
									+ verifikasi+"," + benefitCheck
									+ "," + claim.getNumberClaimCounter() + "," + claim.getPaymentId().getPaymentCounterNumber() + "," + claim.getClaimChargeValue()
									+ "\n";

							
							paymentRecipient = "";
							verifikasi = "";
							benefitCheck = "";
							cdvNumber = "";
							
							idx += 1;

						}
					}
				}
			}

			System.out.println("DATA : " + data);

			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setHeader("Content-disposition", "inline; filename="
					+ "laporanVerifikasi.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));

			sos.write(data.getBytes());
			sos.close();



			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);
			request.setAttribute("dateby", dateBy);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Claims", collection);
			
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
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchExcessPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			
			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			String searchStatus = WebUtil.getParameterString(request,
					"status","");
			
			Integer batchClaimId = WebUtil.getParameterInteger(request,"batchClaimId");
			
			Integer memberGroupId = null;
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation","");
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");

			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateby", "");
			
			Double claimCharges = 0.0;
			Double claimApprovedValue = 0.0;
			Double excessCharges = 0.0;
			
			if (currentNavigation.equalsIgnoreCase("list")){
				location = "listClaim";
			}
			else if (currentNavigation.equalsIgnoreCase("listgroup")){
				location = "listGroupClaim";
			}
			else if (currentNavigation.equalsIgnoreCase("listmember")){
				location = "listMemberClaim";
			}
			
			
			result = new ModelAndView(location);

			result.addObject("subnavigation",subnavigation);

			countSet = 50;
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

			vEqP.add("claimTypeId.claimTypeId");
			vEqQ.add(ClaimType.CASHLESS);
			
			if (navigation.equals("gosearchexcess") ) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					
					if (searchby.equalsIgnoreCase("claimNumber")) {
						vLikeP.add("claimNumber");
						vLikeQ.add(searchtext);
					}
				
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("memberName")){
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("policyNumber")){
						vLikeP.add("memberId.customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberGroupName")){
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
						
						Collection cols = memberGroupService.search("groupName", searchtext,null, null,0,1);
						
						if (cols != null){
							Iterator<MemberGroup> iterator = cols.iterator();
							
							if (iterator != null && iterator.hasNext()){
								MemberGroup memberGroup = iterator.next();
								
								if (memberGroup != null){
									memberGroupId = memberGroup.getMemberGroupId();
								}
							}
						}
					}
					
					if (searchby.equals("clientName")){
						vLikeP.add("memberId.clientId.clientName");
						vLikeQ.add(searchtext);
					}
				}

				if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
					vEqP.add("claimStatus.caseStatusId");
					vEqQ.add(Integer.valueOf(searchStatus));
				}

			}
			
			
			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
					vEqP.add("memberId.memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
				}
				else if (user.getUser().getUserType().intValue() == User.CLIENT){
					vEqP.add("memberId.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				}
				else if (user.getUser().getUserType().intValue() == User.PROVIDER){
					vEqP.add("providerId.providerId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				}
								
			}
			result.addObject("batchClaimId", batchClaimId);
			result.addObject("memberId", memberId);
			result.addObject("clientId", clientId);
			result.addObject("providerId", providerId);
			result.addObject("memberGroupId", memberGroupId);
			
			
			
			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String[] betweenBy = null;
			Object[] minDate = null;
			Object[] maxDate = null;
			
			if(!dateBy.equalsIgnoreCase("")){
				
				if (minimumDate != null && maximumDate != null){
					
					betweenBy = new String[1];
					betweenBy[0] = dateBy;
					minDate = new Date[1];
					minDate[0] = minimumDate;
					
					maxDate = new Date[1];
					maxDate[0] = maximumDate;
				}
			}
			
			count = claimService.getTotalExcessClaim(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate);

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
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			result.addObject("claimCharges", claimCharges);
			result.addObject("claimApprovedValue", claimApprovedValue);
			result.addObject("excessCharges", excessCharges);
			
			String required[] = new String[] {
			// foreign affairs
					"Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
					"Claim.BatchClaimId", "Claim.CaseCategoryId",
			// foreign affairs end
			};
			
			collection = claimService.searchExcessClaim(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,
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
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
				collection = claimService.searchExcessClaim(sLikeP, sLikeQ, sEqP, sEqQ,betweenBy,minDate,maxDate,
						rowsetint, countSet.intValue());
			}

			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);
			request.setAttribute("dateby", dateBy);
			request.setAttribute("memberGroupId", memberGroupId);
			
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Claims", collection);
			
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
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

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

		String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
		Integer policyId = WebUtil.getParameterInteger(request,"policyId");
		
		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String breadcrumb = "";
		System.out.println("================================ request : "+request);
		ActionUser userLogin = securityService.getActionUser(request);
		System.out.println("================================ userLogin : "+userLogin.getLoginSession());
		System.out.println("================================ getUser : "+userLogin.getUser());
		Integer userType= 0;
		try {
			userType = userLogin.getUser().getUserType();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (userLogin != null && userType != null){
			if (userType == User.PROVIDER){
				System.out.println("================================ userLogin != null && userType != null ");
				breadcrumb = "<a href=\"claim?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Claim History</a>";
			}
		} else{
			breadcrumb = "<a href=\"claim?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Claim</a>";
		}
		
		try {
			
			//Add by aju on 20150326, for client iFrame support
			//check if there's session for iframe
			isIFrameSession = false;
			if(session != null){
				if(session.getAttribute("iframe") != null){
					isIFrameSession = (session.getAttribute("iframe").toString().equalsIgnoreCase("yes")?true:false);
				}
			}
			
			//modified by aju on 20150928, remove local, make it public
			/*
			//modified by aju on 20150410, add level login on iFrame :D
			String usingIFrame=null,iFrameClientMemberId=null,iFrameLevelLogin=null;
			*/
			usingIFrame=null;
			iFrameClientMemberId=null;
			iFrameLevelLogin=null;
			
			if(isIFrameSession){
				//System.out.println("IFrame Session detected on Member Controller");
				//get the iFrame saved parameter from session
				usingIFrame = session.getAttribute("iframe").toString();
				//Add by aju on 20150410, add level login filtering on iFrame :D
				iFrameLevelLogin = session.getAttribute("levelLogin").toString();
				
				//modified by aju on 20150410, use level login filtering :D
				//iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				
				if (iFrameLevelLogin.equalsIgnoreCase("member")){
					iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				}
				/**
				switch (iFrameLevelLogin) {
				case "client":
					
					break;
				case "member":
					iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
					break;
				}
				*/
			}
			
			//Add by aju on 20150928, no url param changes for member access fufufu :D
			//modified by aju on 20150410, add level login filtering :D
			if(isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")){
				Member m = memberService.getMember(iFrameClientMemberId);
				sessionMemberId = m.getMemberId().toString();
				sessionMemberParentId = m.getParentMember().getMemberId().toString();
			}
			
			//Add by aju on 20151001, check the user login priviledges
			boolean isAllowed = securityService.isRequestAllowed(request);
			if(!isAllowed){
				User theUser = securityService.getCurrentUser(request);
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
				errorResult.addObject("errorType","OtherFamilyAccessDenied");			
				errorResult.addObject("errorMessage", "Hey "+ theUser.getUsername() + "(" + theUser.getUserId() + "), are you missing your way? :P<br/>" 
						+ "<a href=\"claim\">Go Back</a>");
				return errorResult;
			}
			
			
			if (navigation.equalsIgnoreCase("detail")) {
				result = detailPerformed(request, response, "detailClaim");
				String claimId = request.getParameter("claimId");
				breadcrumb = "<a href=\"claim?navigation=detail&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a>";
				
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchClaim");
				breadcrumb = "<a href=\"claim?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Claim</a>";
			} else if (navigation.equalsIgnoreCase("precheck")) {

			} else if (navigation.equalsIgnoreCase("approve")) {
				result = detailPerformed(request, response, "detailClaim");
			} else if (navigation.equalsIgnoreCase("savecheck")) {

			}
			else if (navigation.equalsIgnoreCase("attachswipe") || navigation.equalsIgnoreCase("saveattachswipe")){
				result = attachSwipePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("attachcase") || navigation.equalsIgnoreCase("saveattachcase")){
				result = attachCasePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("claimexcessreport") || navigation.equalsIgnoreCase("gosearchexcess")){
				result = searchExcessPerformed(request, response, "claimExcessReport");
			}
			else if (navigation.equalsIgnoreCase("list") || subnavigation.equalsIgnoreCase("list")){
				result = searchPerformed(request, response, "listClaim");
				String batchClaimId = request.getParameter("batchClaimId");				
				breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="+batchClaimId+"\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim";
			}
			else if (navigation.equalsIgnoreCase("listmember") || subnavigation.equalsIgnoreCase("listmember")){
				result = searchPerformed (request, response, "listMemberClaim");
				
				String memberId = request.getParameter("memberId");				
				breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Claim";

			}
			else if (navigation.equalsIgnoreCase("listprovider") || subnavigation.equalsIgnoreCase("listprovider")){
				result = searchPerformed (request, response, "listProviderClaim");
				
				String providerId = request.getParameter("providerId");				
				breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Claim";

			}
			else if (navigation.equalsIgnoreCase("listclient") || subnavigation.equalsIgnoreCase("listclient")){
				result = searchPerformed (request, response, "listClientClaim");
				
				String clientId = request.getParameter("clientId");				
				breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Claim";
				
			}			
			else if (navigation.equalsIgnoreCase("listgroup") || subnavigation.equals("listgroup")){
				result = searchPerformed(request, response, "listGroupClaim");

				String groupId = request.getParameter("memberGroupId");				
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+groupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Claim";
			}
            else if (navigation.equalsIgnoreCase("listgroup") || navigation.equalsIgnoreCase("listpolicy") || subnavigation.equals("listpolicy")){
				result = searchPerformed(request, response, "listPolicyClaim");
				breadcrumb = "<a href=\"policy?navigation=detail&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Claim";
			}
			else if (navigation.equalsIgnoreCase("listdetailclaim") || navigation.equalsIgnoreCase("golistdetailclaim")){
				result = searchPerformed(request, response, "listDetailClaim");
				
				String claimId = request.getParameter("claimId");
				breadcrumb = "<a href=\"claim?navigation=detail&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim History";
				
			}
			else if (navigation.equalsIgnoreCase("open")){
				result = openPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("prevoid") || navigation.equalsIgnoreCase("approvevoid")){
				result = voidClaimPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("precancelreject") || navigation.equalsIgnoreCase("cancelreject")){
				result = cancelRejectClaimPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("testregister")){
				result = testRegisterClaimPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("claimrki") || navigation.equalsIgnoreCase("saveclaimrki")){
				result = claimRKIPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("printclaimrki")){
				result = printClaimRKIPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("export")){
				result = exportPerformed(request, response, "searchClaim");
			}
			else if (navigation.equalsIgnoreCase("checklist") || navigation.equalsIgnoreCase("dochecklist")){
				result = checklistDocumentPerformed(request, response);
				String claimId = request.getParameter("claimId");
				User theUser = securityService.getCurrentUser(request);
				session.setAttribute("theUser", theUser);
				breadcrumb = "<a href=\"claim?navigation=detail&claimId="+claimId+"\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim Document Checklist";
			}
			else if (navigation.equalsIgnoreCase("extract") || navigation.equalsIgnoreCase("saveextract")){
				result = extractPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("complete")){
				result = completePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("approveexgratia") || navigation.equalsIgnoreCase("saveapproveexgratia")){
				result = approveExGratiaPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("searchsla")
					|| navigation.equals("gosearchsla")) {
				result = searchPerformed(request, response, "searchSLATrack");
				breadcrumb = "<a href=\"claim?navigation=searchsla\">Search Claim SLA Monitor</a>";
			} 
			else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupClaim");
			}
			//modified by aju on 20150805, for COB :D
			else if (navigation.equalsIgnoreCase("jsontotalopenedc") || navigation.equalsIgnoreCase("jsontotalopenclaim")
					|| navigation.equalsIgnoreCase("jsontotalverifiedclaim") || navigation.equalsIgnoreCase("jsontotalcaseclaim")
					 || navigation.equalsIgnoreCase("jsontotalopencob")){
				result = jsonTotalPendingClaimPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("searchopenedc") || navigation.equalsIgnoreCase("gosearchopenedc")){
				result = searchEDCPerformed(request, response, "searchClaim");
			}else {
				result = searchPerformed(request, response, "searchClaim");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	private ModelAndView approvePerformed(HttpServletRequest request,
			HttpServletResponse response, String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String parsingModifiedDate(HttpServletRequest request){
		Claim claim = new Claim();
		String modifTime = claim.getModifiedTime().toString();
		modifTime.substring(0, 10);
		request.setAttribute("modifTime", modifTime);
		System.out.println(modifTime);
		
		return modifTime;
	}

	private java.util.Date getParameterInisialDate(){
		String parameterAJA = "2016-01-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(parameterAJA));
		} catch (ParseException e) {
			e.printStackTrace();
		}    
		java.util.Date inisial = c.getTime();
		return inisial;
	}

	private Date addExpiredDate(Date joinDate, Date expiredDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(expiredDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		String parameterAJA = "2016-01-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		long diff = 0;
		try {
			diff = cal.getTime().getTime() - (sdf.parse(parameterAJA)).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int IntPreExDate = (int) ( TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1);
		
		return addDatewithNumber(joinDate, IntPreExDate);
	}
	
	private Date addDatewithNumber(Date joinDate, int diff){
		Calendar c = Calendar.getInstance();
		c.setTime(joinDate);        		
		c.add(Calendar.DATE, diff);  
		return (Date) c.getTime();
	}

	// class-

}
