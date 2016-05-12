package com.ametis.cms.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseItem;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberLimitLayer;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.MostFrequentItem;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.ProductClausul;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseItemService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.MostFrequentItemService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.ProductClausulService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

public class ClaimItemController implements Controller {

	private ItemService itemService;

	private ItemCategoryService itemCategoryService;

	private MemberProductService memberProductService;
	private MemberLimitLayerService memberLimitLayerService;

	private ClaimItemService claimItemService;

	private DiagnosisService diagnosisService;

	private SecurityService securityService;

	private ClaimService claimService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;

	private MostFrequentItemService mostFrequentItemService;

	private MemberBenefitService memberBenefitService;
	private MemberClausulService memberClausulService;

	private ProductClausulService productClausulService;

	private ActivityLogService logService;

	private CaseItemService caseItemService;
	private CaseService caseService;

	private PolicyClausulService policyClausulService;

	// Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	private MemberService memberService;

	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}

	public void setMemberLimitLayerService(
			MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}

	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(
			PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}

	public void setMemberClausulService(
			MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public CaseItemService getCaseItemService() {
		return caseItemService;
	}

	public void setCaseItemService(CaseItemService caseItemService) {
		this.caseItemService = caseItemService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public ProductClausulService getProductClausulService() {
		return productClausulService;
	}

	public void setProductClausulService(
			ProductClausulService productClausulService) {
		this.productClausulService = productClausulService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(
			MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MostFrequentItemService getMostFrequentItemService() {
		return mostFrequentItemService;
	}

	public void setMostFrequentItemService(
			MostFrequentItemService mostFrequentItemService) {
		this.mostFrequentItemService = mostFrequentItemService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
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

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(
			MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public ClaimItemService getClaimItemService() {
		return this.claimItemService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimItemId = WebUtil.getParameterInteger(request,
					"claimItemId");

			java.io.Serializable pkey = claimItemId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETECLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETECLAIMITEM access");
				return errorResult;

			}
			// ClaimItem res = claimItemService.delete(pkey, user);
			ClaimItem claimItem = claimItemService.get(pkey);

			boolean res = claimService.deleteClaimITem(claimItem, user);

			// if (res != null) {
			if (res) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.claimitem", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.claimitem", null, "fail",
						Locale.getDefault()));

			}
			result = searchPerformed(request, response, "searchClaimItem");
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

	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILCLAIMITEM access");
				return errorResult;

			}
			Integer claimItemId = WebUtil.getParameterInteger(request,
					"claimItemId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			result = new ModelAndView(location);
			java.io.Serializable pkey = claimItemId;
			String[] required = { "ClaimItem.ClaimId", "ClaimItem.ItemId" };

			ClaimItem object = claimItemService.get(pkey, required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.claimitem", null, "fail",
						Locale.getDefault()));
			} else {
				String[] requiredClaim = { "Claim.MemberId", };
				Claim claim = claimService.get(
						object.getClaimId().getClaimId(), requiredClaim);
				result.addObject("claim", claim);
				result.addObject("memberId", claim.getMemberId().getMemberId());
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("claimItem", object);
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

	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCLAIMITEM access");
				return errorResult;

			}
			result = new ModelAndView(location);

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
					"searchstatus");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			countSet = 50;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| location.equals("claimitem")) {

				if (searchby != null) {

					if (searchby.equalsIgnoreCase("itemName")) {
						vLikeP.add("itemId.itemName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("claimItemDescription")) {
						vLikeP.add("claimItemDescription");
						vLikeQ.add(searchtext);
					}

				}

			}

			if (searchStatus != null
					&& !searchStatus.equals(Integer.valueOf(-1))) {
				vEqP.add("claimItemStatus.caseStatusId");
				vEqQ.add(searchStatus);

			}

			vEqP.add("claimId.claimId");
			vEqQ.add(claimId);

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

			count = claimItemService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"ClaimItem.ItemId", "ClaimItem.ClaimId",
					"ClaimItem.MeasurementUnitId",
			// foreign affairs end
			};
			collection = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				collection = claimItemService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			Claim claimObject = null;

			if (claimId != null) {
				try {
					java.io.Serializable claimkey = claimId;
					System.out.println("claim id : " + claimId);
					String[] claimRequired = { "Claim.BatchClaimId",
							"Claim.MemberId", "Claim.CaseCategoryId",
							"Claim.MemberId.MemberGroupId", "Claim.CaseId",
							"Claim.BatchClaimId.ClaimCurrency",
							"Claim.DiagnosisId", "Claim.Diagnosis2Id",
							"Claim.Diagnosis3Id", "Claim.CobClaimReferenceId" };
					claimObject = claimService.get(claimkey, claimRequired);

				} catch (Exception ex) {
					// System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("searchstatus", searchStatus);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ClaimItems", collection);
			result.addObject("claimId", claimId);
			result.addObject("claim", claimObject);
			result.addObject("memberId", memberId);

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

	public ModelAndView searchBenefitUsagePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCLAIMITEM access");
				return errorResult;

			}
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			String prev = WebUtil.getParameterString(request, "prev", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"searchstatus");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			Integer memberBenefitId = WebUtil.getParameterInteger(request,
					"memberBenefitId");
			Integer itemCategoryId = WebUtil.getParameterInteger(request,
					"itemCategoryId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			// Add by aju on 20150929, for handle iFrame things fufufu
			if (isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")) {
				System.out.println("it\'s still on member(" + sessionMemberId
						+ ") login session fufufu...");
				memberId = (memberId == null ? Integer
						.parseInt(sessionMemberId) : memberId);
				Member theMember = memberService.get(memberId);
				if (theMember != null) {
					System.out.println("checking->requestParentMemberId("
							+ theMember.getParentMember().getMemberId()
									.toString() + ") vs sessionParentMemberId("
							+ sessionMemberParentId + ")");
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
												+ "<a href=\"memberbenefit?navigation=listmember&memberId="
												+ memberId + "\">Go Back</a>");
						return errorResult;
					}
				}
			}

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			countSet = 50;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equals("usagetrack")
					|| navigation.equals("gosearchbysort")) {
				if (searchby != null) {

					if (searchby.equalsIgnoreCase("claimItemRemarks")) {
						vLikeP.add("claimItemRemarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("claimItemDescription")) {
						vLikeP.add("claimItemDescription");
						vLikeQ.add(searchtext);
					}
					if (searchStatus != null && !searchStatus.equals("")) {
						vEqP.add("claimItemStatus.caseStatusId");
						vEqQ.add(Integer.valueOf(searchStatus));
					}
				}
			}

			vEqP.add("memberBenefitId.memberBenefitId");
			vEqQ.add(memberBenefitId);

			vEqP.add("itemId.itemCategoryId.itemCategoryId");
			vEqQ.add(itemCategoryId);

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

			count = claimItemService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"ClaimItem.ItemId", "ClaimItem.ClaimId",
					"ClaimItem.MeasurementUnitId", "ClaimItem.ClaimItemStatus",
					"ClaimItem.MemberBenefitId"
			// foreign affairs end
			};
			collection = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				collection = claimItemService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("searchstatus", searchStatus);
			request.setAttribute("itemCategoryId", itemCategoryId);
			request.setAttribute("prev", prev);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ClaimItems", collection);
			result.addObject("claimId", claimId);
			result.addObject("memberId", memberId);
			result.addObject("memberBenefitId", memberBenefitId);

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

	private boolean validateClaimItemInput(String[] claimItemId,
			String[] claimAmount, String[] itemValue, HttpServletRequest request) {
		boolean isError = false;
		try {
			boolean isDuplicate = false;

			String alert = "";
			if (claimItemId != null) {
				System.out.println("CLAIM ITEM : " + claimItemId.length);

				Vector claimItemVector = new Vector();

				for (int i = 0; i < claimItemId.length; i++) {
					for (int j = 0; j < claimItemId.length; j++) {
						if (j != i) {
							if (claimItemId[i].equals(claimItemId[j])) {

								isDuplicate = true;
								isError = true;

							}
						}
					}

				}
				alert += "Duplicate Item" + "\n\n";

			}

			if (claimAmount != null) {
				System.out.println("CLAIM Amount : " + claimAmount.length);

				Vector claimAmountVector = new Vector();

				boolean isAlerted = false;
				for (int i = 0; i < claimAmount.length; i++) {

					try {
						Double doubleVal = Double.parseDouble(claimAmount[i]);
					} catch (Exception e) {
						isError = true;

						if (!isAlerted) {
							alert += "Invalid Amount Value" + "\n\n";
							isAlerted = true;
						}

					}

				}

			}

			if (itemValue != null) {
				System.out.println("CLAIM Value : " + itemValue.length);

				Vector claimValueVector = new Vector();

				boolean isAlerted = false;
				for (int i = 0; i < itemValue.length; i++) {

					try {
						Double doubleVal = Double.parseDouble(itemValue[i]);
					} catch (Exception e) {

						if (!isAlerted) {
							alert += "Invalid Item Value" + "\n\n";
							isAlerted = true;
						}
						isError = true;

					}

					claimValueVector.add(itemValue[i]);
				}
				request.setAttribute("claimValueVector", claimValueVector);
			}
			request.setAttribute("alert", alert);

		} catch (Exception e) {

		}
		return isError;
	}

	private boolean validateItemVerification(String[] claimItemId,
			HttpServletRequest request) {
		boolean isError = false;

		try {
			boolean isDuplicate = false;

			String alert = "";
			if (claimItemId != null) {
				System.out.println("CLAIM ITEM : " + claimItemId.length);

				Vector claimItemVector = new Vector();

				for (int i = 0; i < claimItemId.length; i++) {
					for (int j = 0; j < claimItemId.length; j++) {
						if (j != i) {
							if (claimItemId[i].equals(claimItemId[j])) {

								isDuplicate = true;
								isError = true;

							}
						}
					}

				}
				if (!isError) {
					for (int i = 0; i < claimItemId.length; i++) {
						String approval = request.getParameter("approval"
								+ claimItemId[i]);

						if (approval == null || approval.equals("")) {
							isError = false;
						}

					}
				}
				alert += "Duplicate Item" + "\n\n";

			}

		} catch (Exception e) {

		}
		return isError;
	}

	public ModelAndView saveClaimItemPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SAVECLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SAVECLAIMITEM access");
				return errorResult;

			}
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			String[] claimItemId = request.getParameterValues("itemId");
			String[] claimAmountId = request.getParameterValues("itemAmount");
			String[] measurementUnit = request
					.getParameterValues("measurementUnit");

			String[] claimItemIdRef = request.getParameterValues("claimItemId");

			String[] itemValue = request.getParameterValues("itemValue");
			String[] prePaidExcess = request
					.getParameterValues("prePaidExcess");
			String[] faultExcessProvider = request
					.getParameterValues("faultExcessProvider");
			String[] description = request.getParameterValues("description");
			Double pembulatan = WebUtil.getParameterDouble(request,
					"pembulatan");

			Claim claim = claimService.get(claimId);

			if (claim != null && claim.getBatchClaimId() != null) {
				result = new ModelAndView(new RedirectView(
						"claim?navigation=detail&claimId=" + claimId));
			}
			if (claim != null && claim.getBatchClaimId() == null
					&& claim.getCaseId() != null) {
				result = new ModelAndView(new RedirectView(
						"claim?navigation=detail&claimId=" + claimId
								+ "&caseId=" + claim.getCaseId().getCaseId()));
			}
			if (claim != null && claim.getBatchClaimId() == null
					&& claim.getCaseId() == null) {
				result = new ModelAndView(new RedirectView(
						"claim?navigation=detail&claimId=" + claimId));
			}

			if (navigation.equals("saveitemandreturnbatch")) {

				if (batchClaimId == null || batchClaimId == 0) {
					if (claim != null) {
						batchClaimId = claim.getBatchClaimId()
								.getBatchClaimId();
					}
				}
				result = new ModelAndView(new RedirectView(
						"batchclaim?navigation=detail&batchClaimId="
								+ batchClaimId));

			}
			boolean isError = false;
			if (isError) {

				result = addClaimItemPerformed(request, response,
						"addClaimItem");

				Vector vect = new Vector();
				Vector itemVect = new Vector();
				Vector valueVect = new Vector();
				Vector amountVect = new Vector();

				if (description != null) {
					for (int i = 0; i < description.length; i++) {
						vect.add(description[i]);
						itemVect.add(claimItemId[i]);
						valueVect.add(itemValue[i]);
						amountVect.add(claimAmountId[i]);

					}
				}
				result.addObject("claimDescVector", vect);
				result.addObject("claimItemVector", itemVect);
				result.addObject("claimValueVector", valueVect);
				result.addObject("claimAmountVector", amountVect);

				result.addObject("navigation", "addclaimitem");
				result.addObject("claimId", claimId);

			} else {
				if (claim != null) {

					Collection collection = new Vector();
					ClaimItem claimItem = null;
					Item item = null;

					if (claimItemId != null) {
						// updating existing or add new item
						for (int i = 0; i < claimItemId.length; i++) {
							String itemVal = itemValue[i].trim();
							String amountId = claimAmountId[i].trim();

							if (!itemVal.equals("") && !amountId.equals("")) {
								claimItem = new ClaimItem();
								claimItem.setClaimId(claim);

								item = new Item();
								item.setItemId(Integer.valueOf(claimItemId[i]));

								claimItem.setItemId(item);
								CaseStatus status = new CaseStatus();
								status.setCaseStatusId(CaseStatus.CASE_OPEN);
								claimItem.setClaimItemStatus(status);
								claimItem.setClaimItemAmount(Double
										.valueOf(claimAmountId[i]));
								claimItem.setClaimItemValue(Double
										.valueOf(itemValue[i]));
								claimItem
										.setClaimItemDescription(description[i]);

								if (!claimItemIdRef[i].equalsIgnoreCase("")) {
									claimItem.setClaimItemId(Integer
											.valueOf(claimItemIdRef[i]));
								}

								if (!prePaidExcess[i].equalsIgnoreCase("")) {
									claimItem.setPrePaidExcess(Double
											.valueOf(prePaidExcess[i]));
								}
								if (!faultExcessProvider[i].equalsIgnoreCase("")) {
									claimItem.setFaultExcessProvider(Double.valueOf(faultExcessProvider[i]));
								}
								collection.add(claimItem);
							}
						}
					}

					claimService.createClaimItem(claim.getClaimId(),
							collection, pembulatan, user);

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

	public ModelAndView addItemPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {

		ModelAndView result = null;
		try {

		} catch (Exception e) {

		}
		return result;
	}

	public ModelAndView addClaimItemPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"ADDCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for ADDCLAIMITEM access");
				return errorResult;

			}

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			
			
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");
			result = new ModelAndView("addBulkClaimItem");

			Claim claim = claimService.get(claimId);
			MemberLimitLayer smmLayer = null;
			boolean isSMM = false;
			
			if (claim.getIsUsingSMM() != null && claim.getIsUsingSMM() == 1){
				isSMM = true;
				
				if (claim.getSmmLayerId() != null){
					smmLayer = memberLimitLayerService.get(claim.getSmmLayerId().getMemberLimitLayerId());
				}
				
			}
			
			int totalItemLeft = 0;

			if (claim != null) {

				String[] param = { "claimId.claimId", "deletedStatus" };
				Object[] value = { claimId, Integer.valueOf(0) };

				int total = claimItemService.getTotal(null, null, param, value);
				System.out.println("TOTAL CLAIM : " + total);
				Collection claimItem = claimItemService.search(null, null,
						param, value, 0, total);

				Collection<Item> claimableItem = itemService.getClaimableItem(
						claim.getMemberId().getMemberId(), claim
								.getCaseCategoryId().getCaseCategoryId(), claim
								.getAdmissionDate(), claim.getDischargeDate());

				System.out
						.println("CLAIMABLE ITEM : " + claimableItem != null ? claimableItem
								.size() : 0);
				System.out.println("CASE CATEGORY : "
						+ claim.getCaseCategoryId().getCaseCategoryId());

				Collection<CaseItem> caseItemCollection = null;
				int layer = 0;
				
				MemberProduct memberProduct = memberProductService.getMemberProductByDate(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId(),
						 claim.getAdmissionDate(),
							claim.getDischargeDate());
				
				if (memberProduct == null){
					memberProduct = memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());
				}
				else {
					if (memberProduct.getFamilyProductId() != null){
						memberProduct = memberProduct.getFamilyProductId();
					}
				}

				Collection<MemberBenefit> memberBenefits = null;
				
				
				if (memberProduct != null){
					
					SubscriptionStatus mpStatus = memberProduct.getMemberProductStatus();
					
					if (memberProduct.getActualBenefitLimit() != null && !isSMM && mpStatus.getStatusId().intValue() == SubscriptionStatus.ACTIVE){
						if (memberProduct.getActualBenefitLimit().doubleValue() == -1.0 || memberProduct.getActualBenefitLimit().doubleValue() > 0.0 ){
							memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
									memberProduct.getMemberProductId(), 0);
						}
						else {
							MemberLimitLayer availableLayer = memberLimitLayerService.getAvailableLayer(memberProduct.getMemberId().getMemberId(), 
									  claim.getCaseCategoryId().getCaseCategoryId(), 
										memberProduct.getMemberProductId());
							
							if (availableLayer != null){
								memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
										memberProduct.getMemberProductId(), availableLayer.getLayer());
								
								layer = availableLayer.getLayer();
							}
						}
					}	
					else if (isSMM && memberProduct != null && smmLayer != null && mpStatus.getStatusId().intValue() == SubscriptionStatus.ACTIVE){
						memberBenefits = new Vector<MemberBenefit>();
						
						Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
								memberProduct.getMemberProductId(), 0);
						
						if (memberBenefitList != null){
							for (Iterator iterator = memberBenefitList.iterator(); iterator.hasNext();) {
								MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
								
								if (memberBenefit != null){
									MemberBenefit smmBenefit = memberBenefitService
											.getActiveMemberBenefitSMM(smmLayer.getMemberLimitLayerId(), claim.getCaseCategoryId().getCaseCategoryId(), 
													memberBenefit.getItemCategoryId().getItemCategoryId());
									
									if (smmBenefit != null){
										memberBenefits.add(smmBenefit);
									}
									else {
										memberBenefits.add(memberBenefit);
									}
								}								
							}
						}
					}
					
					else if (memberProduct.getActualBenefitLimit() != null && !isSMM && mpStatus.getStatusId().intValue() != SubscriptionStatus.ACTIVE){
						if (mpStatus.getStatusId().intValue() == SubscriptionStatus.EXPIRED ){
							
							if (memberProduct.getActualBenefitLimit().doubleValue() == -1.0 || memberProduct.getActualBenefitLimit().doubleValue() > 0.0 ){
								memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
										memberProduct.getMemberProductId(), 0);
							}
							else {
								MemberLimitLayer availableLayer = memberLimitLayerService.getAvailableLayer(memberProduct.getMemberId().getMemberId(), 
										  claim.getCaseCategoryId().getCaseCategoryId(), 
											memberProduct.getMemberProductId());
								
								if (availableLayer != null){
									memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
											memberProduct.getMemberProductId(), availableLayer.getLayer());
									
									layer = availableLayer.getLayer();
								}
							}
							
						}
						else if ( mpStatus.getStatusId().intValue() == SubscriptionStatus.UPGRADED){
							MemberProduct activeMemberProduct = memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());
							
							if (activeMemberProduct.getActualBenefitLimit().doubleValue() == -1.0 || activeMemberProduct.getActualBenefitLimit().doubleValue() > 0.0 ){
								/**
								 * Jika active limit masih ada, maka gunakan member benefit list dari layer utama dari previous list
								 */
								memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
										memberProduct.getMemberProductId(), 0);
							}
							else {
								MemberLimitLayer availableLayer = memberLimitLayerService.getAvailableLayer(activeMemberProduct.getMemberId().getMemberId(), 
										  claim.getCaseCategoryId().getCaseCategoryId(), 
										  activeMemberProduct.getMemberProductId());
								
								if (availableLayer != null){
									memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
											memberProduct.getMemberProductId(), availableLayer.getLayer());
									
									layer = availableLayer.getLayer();
								}
							}
						}
					}
					
				}
				

				Collection<MemberClausul> memberClausuls = memberClausulService
						.getMemberActiveClausul(claim.getMemberId()
								.getMemberId(), claim.getCaseCategoryId()
								.getCaseCategoryId());

				if (memberBenefits != null && memberBenefits.size() == 0) {
					memberBenefits = memberBenefitService.getMemberBenefitList(
							claim.getMemberId().getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());
				}
				

				if (claim.getCaseId() != null && claim.getBatchClaimId() == null) {
					caseItemCollection = caseItemService.getCaseItem(claim.getCaseId().getCaseId());
				}

				if (claimableItem != null) {

					Iterator<Item> iterator = claimableItem.iterator();

					Collection colItemName = new Vector();
					Collection colItemCode = new Vector();
					Collection colItemId = new Vector();
					Collection blankCol = new Vector();
					Collection valueCol = new Vector();
					Collection claimItemCol = new Vector();
					Collection caseItemCol = new Vector();
					Collection amounCollection = new Vector();
					Collection ruleCollection = new Vector();
					Collection prePaidExcess = new Vector();
					Collection paidToProvider = new Vector();
					Collection faultExcessProvider = new Vector();

					int totalItem = 0;
					while (iterator.hasNext()) {

						Item item = iterator.next();

						if (item != null) {

							colItemName.add(item.getItemName());
							colItemCode.add(item.getItemCode());

							item = itemService.get(item.getItemId());

							System.out.println("ITEM " + totalItem
									+ item.getItemName() + " "
									+ item.getItemCode());

							Collection<MemberBenefit> memberBenefitList = memberBenefitService
									.getMemberBenefitByDate(claim.getMemberId()
											.getMemberId(), item
											.getItemCategoryId()
											.getItemCategoryId(), claim
											.getCaseCategoryId()
											.getCaseCategoryId(), claim
											.getAdmissionDate(), claim
											.getDischargeDate(),layer);
							System.out.println("ADMISSION DATE : "
									+ claim.getAdmissionDate());
							System.out.println("DISCHARGE DATE : "
									+ claim.getDischargeDate());

							String ruleName = "";

							if (memberBenefitList != null) {

								if (memberBenefitList.size() == 0) {
									memberBenefitList = new Vector<MemberBenefit>();

									MemberBenefit memberBenefit = null;
									
									if (isSMM && smmLayer != null){

										memberBenefit = memberBenefitService
												.getActiveMemberBenefitSMM(smmLayer.getMemberLimitLayerId(), claim.getCaseCategoryId().getCaseCategoryId(), 
														item.getItemCategoryId().getItemCategoryId());
									}

									if (memberBenefit == null){

										memberBenefit = memberBenefitService.getActiveMemberBenefit(claim.getMemberId().getMemberId(), claim
														.getCaseCategoryId()
														.getCaseCategoryId(), item
														.getItemCategoryId()
														.getItemCategoryId());
	
									}
								
									if (memberBenefit != null) {
										memberBenefitList.add(memberBenefit);
									}
								}
								

								Iterator<MemberBenefit> benIterator = memberBenefitList
										.iterator();

								if (benIterator.hasNext()) {
									MemberBenefit memberBenefit = benIterator
											.next();

									System.out.println("MEMEBER BENEFIT : "
											+ memberBenefit
													.getMemberBenefitId());

									if (memberBenefit != null) {
										if (memberBenefit.getBenefitLimit() != null) {
											if (memberBenefit.getBenefitLimit().doubleValue() == -1.0) {
												ruleName = "AS CHARGE";
												
												if (isSMM && smmLayer != null){
													MemberBenefit smmBenefit = memberBenefitService.getActiveMemberBenefitSMM(smmLayer.getMemberLimitLayerId(),
															claim.getCaseCategoryId().getCaseCategoryId(), 
															item.getItemCategoryId().getItemCategoryId());
													
													if (smmBenefit != null && smmBenefit.getBenefitLimit() != null){
														if (smmBenefit.getBenefitLimit() >= 0.0){
															BigDecimal bc = new BigDecimal(smmBenefit.getBenefitLimit());
															ruleName = bc.toPlainString();		
														}
													}
												}
											} else {

												if (memberBenefit.getBenefitLimit().doubleValue() > 0.0) {
													BigDecimal bc = new BigDecimal(memberBenefit.getBenefitLimit());
													ruleName = bc.toPlainString();
												
													if (isSMM && smmLayer != null){
														MemberBenefit smmBenefit = memberBenefitService.getActiveMemberBenefitSMM(smmLayer.getMemberLimitLayerId(),
																claim.getCaseCategoryId().getCaseCategoryId(), 
																item.getItemCategoryId().getItemCategoryId());
														
														if (smmBenefit != null && smmBenefit.getBenefitLimit() != null){
															if (smmBenefit.getBenefitLimit() >= 0.0){
																bc = new BigDecimal(smmBenefit.getBenefitLimit());
																ruleName = bc.toPlainString();		
															}
														}
													}
													
												} else if (memberBenefit.getBenefitLimit().doubleValue() == 0.0) {
													if (claim.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT) {
														BigDecimal bc = new BigDecimal(memberBenefit.getReimbursementBenefitLimit());
														ruleName = bc.toPlainString();
														
														if (isSMM && smmLayer != null){
															MemberBenefit smmBenefit = memberBenefitService.getActiveMemberBenefitSMM(smmLayer.getMemberLimitLayerId(),
																	claim.getCaseCategoryId().getCaseCategoryId(), 
																	item.getItemCategoryId().getItemCategoryId());
															
															if (smmBenefit != null && smmBenefit.getReimbursementBenefitLimit() != null){
																if (smmBenefit.getReimbursementBenefitLimit() >= 0.0){
																	bc = new BigDecimal(smmBenefit.getReimbursementBenefitLimit());
																	ruleName = bc.toPlainString();		
																}
															}
														}
													}
												}
											}
										}

										String rule = "";

										if (memberBenefit
												.getBenefitCalculationMethod()
												.getBenefitUsageTypeId()
												.intValue() == MemberBenefit.ACCUMULATIVE_VALUE) {
											rule = "ANNUAL";
										}
										if (memberBenefit
												.getBenefitCalculationMethod()
												.getBenefitUsageTypeId()
												.intValue() == MemberBenefit.PERDISABILITY) {
											rule = "PER DISABILITY";
										}
										if (memberBenefit
												.getBenefitCalculationMethod()
												.getBenefitUsageTypeId()
												.intValue() == MemberBenefit.MAX_DAILY_AND_OCCURANCE_METHOD) {
											rule = "MAX DAILY";
										}
										ruleName += " / " + rule;
									}
								}
							}
							ruleCollection.add(ruleName);
							colItemId.add(item.getItemId());

							Iterator<ClaimItem> itemIterator = claimItem
									.iterator();

							if (itemIterator != null
									&& claim.getBatchClaimId() != null) {
								boolean isAvailable = false;
								while (itemIterator.hasNext()) {
									ClaimItem ci = itemIterator.next();

									System.out.println("CLAIM ITEM : "
											+ ci.getClaimItemId().intValue());
									System.out.println("ITEM : "
											+ item.getItemId().intValue());
									if (ci != null
											&& (ci.getItemId().getItemId()
													.intValue() == item
													.getItemId().intValue())) {
										BigDecimal bc = new BigDecimal(
												ci.getClaimItemValue());
										valueCol.add(bc.toPlainString());

										amounCollection.add(ci
												.getClaimItemAmount()
												.toString());
										claimItemCol.add(ci.getClaimItemId()
												.toString());

										if (ci.getPaidToProvider() != null) {
											BigDecimal paidToProv = new BigDecimal(
													ci.getPaidToProvider());
											paidToProvider.add(paidToProv
													.toPlainString());
										} else {
											paidToProvider.add("0");
										}
										if (ci.getPrePaidExcess() != null) {
											BigDecimal paidToProv = new BigDecimal(
													ci.getPrePaidExcess());
											prePaidExcess.add(paidToProv
													.toPlainString());
										} else {
											prePaidExcess.add("0");
										}
										if (ci.getFaultExcessProvider() != null) {
											BigDecimal faultExcessProv = new BigDecimal(
													ci.getFaultExcessProvider());
											faultExcessProvider
													.add(faultExcessProv
															.toPlainString());
										} else {
											faultExcessProvider.add("0");
										}
										isAvailable = true;
									}
								}
								if (!isAvailable) {
									valueCol.add("");
									amounCollection.add("");
									claimItemCol.add("");
									prePaidExcess.add("");
									paidToProvider.add("");
									faultExcessProvider.add("");

								}
							} else if (itemIterator != null
									&& claim.getBatchClaimId() == null
									&& caseItemCollection == null) {
								boolean isAvailable = false;
								while (itemIterator.hasNext()) {
									ClaimItem ci = itemIterator.next();

									if (ci != null
											&& (ci.getItemId().getItemId()
													.intValue() == item
													.getItemId().intValue())) {

										BigDecimal bc = new BigDecimal(
												ci.getClaimItemValue());
										valueCol.add(bc.toPlainString());
										amounCollection.add(ci
												.getClaimItemAmount()
												.toString());
										claimItemCol.add(ci.getClaimItemId()
												.toString());

										if (ci.getPaidToProvider() != null) {
											BigDecimal paidToProv = new BigDecimal(
													ci.getPaidToProvider());
											paidToProvider.add(paidToProv
													.toPlainString());
										} else {
											paidToProvider.add("0");
										}
										if (ci.getPrePaidExcess() != null) {
											BigDecimal paidToProv = new BigDecimal(
													ci.getPrePaidExcess());
											prePaidExcess.add(paidToProv
													.toPlainString());
										} else {
											prePaidExcess.add("0");
										}
										if (ci.getFaultExcessProvider() != null) {
											BigDecimal faultExcessProv = new BigDecimal(
													ci.getFaultExcessProvider());
											faultExcessProvider
													.add(faultExcessProv
															.toPlainString());
										} else {
											faultExcessProvider.add("0");
										}

										isAvailable = true;
									}
								}
								if (!isAvailable) {
									valueCol.add("");
									amounCollection.add("");
									claimItemCol.add("");
									prePaidExcess.add("");
									paidToProvider.add("");
									faultExcessProvider.add("");
								}
							} else if (caseItemCollection != null
									&& claim.getCaseId() != null && total == 0) {

								boolean isAvailable = false;

								CaseItem caseItem = caseItemService
										.getCaseItem(claim.getCaseId()
												.getCaseId(), item.getItemId());

								if (caseItem != null) {
									String itemName = item.getItemName();
									String itemCase = caseItem.getItemId()
											.getItemName();

									BigDecimal bc = new BigDecimal(
											caseItem.getUsageValue());

									valueCol.add(bc.toPlainString());

									String totalBiaya = bc.toPlainString();

									amounCollection.add(caseItem
											.getUsageAmount().toString());

									claimItemCol.add("");
									prePaidExcess.add("");
									paidToProvider.add("");
									// claimItemCol.add(caseItem.getCaseItemId().toString());
									isAvailable = true;
								} else {

									while (itemIterator.hasNext()) {
										ClaimItem ci = itemIterator.next();

										if (ci != null
												&& (ci.getItemId().getItemId()
														.intValue() == item
														.getItemId().intValue())) {

											BigDecimal bc = new BigDecimal(
													ci.getClaimItemValue());
											valueCol.add(bc.toPlainString());
											amounCollection.add(ci
													.getClaimItemAmount()
													.toString());
											claimItemCol.add(ci
													.getClaimItemId()
													.toString());
											if (ci.getPaidToProvider() != null) {
												BigDecimal paidToProv = new BigDecimal(
														ci.getPaidToProvider());
												paidToProvider.add(paidToProv
														.toPlainString());
											} else {
												paidToProvider.add("0");
											}
											if (ci.getPrePaidExcess() != null) {
												BigDecimal paidToProv = new BigDecimal(
														ci.getPrePaidExcess());
												prePaidExcess.add(paidToProv
														.toPlainString());
											} else {
												prePaidExcess.add("0");
											}
											if (ci.getFaultExcessProvider() != null) {
												BigDecimal faultExcessProv = new BigDecimal(
														ci.getFaultExcessProvider());
												faultExcessProvider
														.add(faultExcessProv
																.toPlainString());
											} else {
												faultExcessProvider.add("0");
											}

											isAvailable = true;
										}
									}
									if (!isAvailable) {
										valueCol.add("");
										amounCollection.add("");
										claimItemCol.add("");
										prePaidExcess.add("");
										paidToProvider.add("");
										faultExcessProvider.add("");
									}
								}

								/**
								 * if (!isAvailable){ valueCol.add("");
								 * amounCollection.add("");
								 * claimItemCol.add(""); }
								 */
							} else if (caseItemCollection != null
									&& claim.getCaseId() != null && total > 0) {

								boolean isAvailable = false;
								while (itemIterator.hasNext()) {
									ClaimItem ci = itemIterator.next();

									if (ci != null
											&& (ci.getItemId().getItemId()
													.intValue() == item
													.getItemId().intValue())) {
										BigDecimal bc = new BigDecimal(
												ci.getClaimItemValue());
										valueCol.add(bc.toPlainString());

										amounCollection.add(ci
												.getClaimItemAmount()
												.toString());
										claimItemCol.add(ci.getClaimItemId()
												.toString());

										if (ci.getPaidToProvider() != null) {
											BigDecimal paidToProv = new BigDecimal(
													ci.getPaidToProvider());
											paidToProvider.add(paidToProv
													.toPlainString());
										} else {
											paidToProvider.add("0");
										}
										if (ci.getPrePaidExcess() != null) {
											BigDecimal paidToProv = new BigDecimal(
													ci.getPrePaidExcess());
											prePaidExcess.add(paidToProv
													.toPlainString());
										} else {
											prePaidExcess.add("0");
										}
										if (ci.getFaultExcessProvider() != null) {
											BigDecimal faultExcessProv = new BigDecimal(
													ci.getFaultExcessProvider());
											faultExcessProvider
													.add(faultExcessProv
															.toPlainString());
										} else {
											faultExcessProvider.add("0");
										}
										isAvailable = true;
									}
								}
								if (!isAvailable) {
									valueCol.add("");
									amounCollection.add("");
									claimItemCol.add("");
									prePaidExcess.add("");
									paidToProvider.add("");
									faultExcessProvider.add("");
								}
							}

							blankCol.add("");
							totalItem += 1;
						}
					}

					request.setAttribute("paidToProviderVector", paidToProvider);
					request.setAttribute("claimItemCodeVector", colItemCode);
					request.setAttribute("claimItemNameVector", colItemName);
					request.setAttribute("claimItemRuleVector", ruleCollection);
					request.setAttribute("claimItemVector", colItemId);
					request.setAttribute("claimAmountVector", amounCollection);
					request.setAttribute("claimValueVector", valueCol);
					request.setAttribute("claimDescVector", blankCol);
					request.setAttribute("claimItemIdVector", claimItemCol);
					request.setAttribute("caseItemIdVector", caseItemCol);
					request.setAttribute("prePaidExcessVector", prePaidExcess);
					request.setAttribute("faultExcessProviderVector",
							faultExcessProvider);

					request.setAttribute("totalItem", new Integer(totalItem));
					result.addObject("memberBenefitList", memberBenefits);
					result.addObject("productClausulList", memberClausuls);
				}
			}

			result.addObject("batchClaimId", batchClaimId);

			result.addObject("claimId", claimId);
			result.addObject("claimValue", claim.getClaimChargeValue());
			result.addObject("pembayaranDimuka", claim.getPembayaranDimuka());
		} catch (Exception e) {
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

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String claimId = request.getParameter("claimId");
		String breadcrumb = "<a href=\"claim?navigation=detail&claimId="
				+ claimId + "\" class=\"linkbreadcrumb\">Detail Claim</a>";

		try {

			// add aju on 20150929, new iFrame checker fufufu plus init
			System.out.println("MemberBenefitController::handleRequest()...");
			isIFrameSession = securityService.isUsingIFrameSession(request);
			iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
			System.out.println("securityService->SessionMemberId->"
					+ securityService.getTheSessionMemberId());
			sessionMemberId = securityService.getTheSessionMemberId();
			System.out.println("securityService->SessionParentMemberId->"
					+ securityService.getTheSessionMemberParentId());
			sessionMemberParentId = securityService
					.getTheSessionMemberParentId();

			if (navigation.equalsIgnoreCase("detail")) {

				result = detailPerformed(request, response, "detailClaimItem");

			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchClaimItem");

			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupClaimItem");
			} else if (navigation.equalsIgnoreCase("addclaimitem")) {
				result = addClaimItemPerformed(request, response,
						"addClaimItem");
				breadcrumb += " &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Claim Item";

			} else if (navigation.equalsIgnoreCase("saveclaimitem")) {
				result = saveClaimItemPerformed(request, response,
						"detailClaim");

			} else if (navigation.equalsIgnoreCase("usagetrack")) {
				result = searchBenefitUsagePerformed(request, response,
						"listUsageItem");
			} else if (navigation.equalsIgnoreCase("saveitemandreturnbatch")) {
				result = saveClaimItemPerformed(request, response,
						"detailClaim");
			} else if (navigation.equalsIgnoreCase("reject")
					|| navigation.equalsIgnoreCase("approve")
					|| navigation.equals("verify")) {
				result = verifyClaimItemPerformed(request, response,
						"verifyClaimItem");
			} else if (navigation.equalsIgnoreCase("checkitem")) {
				result = checkClaimItemPerformed(request, response,
						"checkClaimItem");

				breadcrumb += " &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Benefit Check Item";
			} else if (navigation.equalsIgnoreCase("verifybulk")
					|| navigation.equalsIgnoreCase("approveverifybulk")) {
				result = verifyItemBulkPerformed(request, response,
						"verifyItemBulk");

				breadcrumb += " &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Verify Claim Item";
			} else if (navigation.equalsIgnoreCase("checkitembulk")
					|| navigation.equalsIgnoreCase("approvecheckbulk")) {

				result = checkItemBulkPerformed(request, response,
						"checkItemBulk");

				breadcrumb += " &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Benefit Check Item";
			} else {
				result = searchPerformed(request, response, "searchClaimItem");
				breadcrumb += " &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim Item";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	private ModelAndView checkClaimItemPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {

		ModelAndView result = null;

		try {

			Integer claimItemId = WebUtil.getParameterInteger(request,
					"claimItemId");

			Integer index = WebUtil.getParameterInteger(request, "index");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CHECKCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for CHECKCLAIMITEM access");
				return errorResult;

			}
			String claimItemRemarks = WebUtil.getParameterString(request,
					"claimItemRemarks", "");

			result = new ModelAndView(location);

			// java.io.Serializable pkey = claimItemId;

			Claim claim = claimService.get(claimId);

			ClaimItem object = null;

			if (claimItemId == null) {
				object = claimItemService.getNextToCheckItem(claim);
			} else {

			}

			if (object == null) {
				// ada dua jenis problem nih..
				//
				result = new ModelAndView(new RedirectView(
						"claim?navigation=finalize&index=1&claimId=" + claimId));
			} else {
				result = new ModelAndView(
						new RedirectView("claimitem-form?claimItemId="
								+ object.getClaimItemId()));
			}

			boolean res = false;

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.claimitem", null, "fail",
						Locale.getDefault()));
			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("claimId", claimId);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
		// TODO Auto-generated method stub

	}

	private ModelAndView verifyClaimItemPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {
		// TODO Auto-generated method stub
		ModelAndView result = null;

		try {

			Integer claimItemId = WebUtil.getParameterInteger(request,
					"claimItemId");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"VERIFYCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for VERIFYCLAIMITEM access");
				return errorResult;

			}

			String claimItemRemarks = WebUtil.getParameterString(request,
					"claimItemRemarks", "");

			result = new ModelAndView(location);

			java.io.Serializable pkey = claimItemId;

			Claim claim = new Claim();
			claim.setClaimId(claimId);

			ClaimItem object = null;

			if (claimItemId != null) {
				object = claimItemService.get(claimItemId);
			}

			if (claimItemId == null) {
				object = claimItemService.getNextToVerifyItem(claim);
			} else {

			}

			if (object == null) {
				// ada dua jenis problem nih..
				//
				result = new ModelAndView(new RedirectView(
						"claim?navigation=approve&index=1&claimId=" + claimId));
			}

			boolean res = false;

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.claimitem", null, "fail",
						Locale.getDefault()));
			} else {

				object.setMedicalRemarks(claimItemRemarks);

				if (navigation.equals("approve")) {
					res = claimItemService.approveVerifyItem(object, user);
				} else if (navigation.equals("reject")) {
					res = claimItemService.rejectVerifyItem(object, user);
				}

			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("claimId", claimId);
			result.addObject("claimItem", object);
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

	private ModelAndView verifyItemBulkPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {
		// TODO Auto-generated method stub
		ModelAndView result = null;

		try {

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"VERIFYCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for VERIFYCLAIMITEM access");
				return errorResult;

			}

			String claimItemRemarks = WebUtil.getParameterString(request,
					"claimItemRemarks", "");

			result = new ModelAndView(location);

			Claim claim = new Claim();
			claim.setClaimId(claimId);

			ClaimItem object = null;
			Collection<ClaimItem> verifiableItem = null;

			if (navigation.equalsIgnoreCase("verifybulk")) {

				verifiableItem = claimItemService.getVerifiableItem(claimId);

				result.addObject("verifiableItems", verifiableItem);

			} else if (navigation.equalsIgnoreCase("approveverifybulk")) {

				String[] claimItems = request.getParameterValues("claimItemId");

				String[] description = request
						.getParameterValues("description");

				result = new ModelAndView(new RedirectView(
						"claim?navigation=detail&claimId=" + claimId));

				boolean isError = validateItemVerification(claimItems, request);

				if (isError) {
					result = addClaimItemPerformed(request, response,
							"addClaimItem");

					Vector vect = new Vector();
					Vector itemVect = new Vector();

					if (description != null) {
						for (int i = 0; i < description.length; i++) {
							vect.add(description[i]);
							itemVect.add(claimItems[i]);

						}
					}
					result.addObject("claimDescVector", vect);
					result.addObject("claimItemVector", itemVect);

					result.addObject("navigation", "addclaimitem");
					result.addObject("claimId", claimId);

					System.out.println("TEST : " + vect.get(0));

				} else {
					claim = claimService.get(claimId);

					if (claim != null) {

						Collection collection = new Vector();

						ClaimItem claimItem = null;

						Item item = null;

						if (claimItems != null) {
							for (int i = 0; i < claimItems.length; i++) {

								claimItem = claimItemService.get(Integer
										.valueOf(claimItems[i]));
								claimItem.setClaimId(claim);

								claimItem.setMedicalRemarks(description[i]);

								CaseStatus status = new CaseStatus();

								String approval = request
										.getParameter("approval"
												+ claimItem.getClaimItemId());
								if (approval != null
										&& approval.equals("approve")) {
									status.setCaseStatusId(ClaimItem.CLAIM_ITEM_APPROVED);
									claimItem.setClaimItemStatus(status);
								} else if (approval != null
										&& approval.equals("reject")) {
									status.setCaseStatusId(ClaimItem.CLAIM_ITEM_REJECTED);
									claimItem.setClaimItemStatus(status);
								}

								collection.add(claimItem);
							}

						}
						claimService.approveMedicalBulk(claimId, collection,
								user);
					}
				}

			}

			result.addObject("claimId", claimId);
			result.addObject("claimItem", object);
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

	private ModelAndView checkItemBulkPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {
		// TODO Auto-generated method stub
		ModelAndView result = null;

		try {
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CHECKCLAIMITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for CHECKCLAIMITEM access");
				return errorResult;

			}

			String claimItemRemarks = WebUtil.getParameterString(request,
					"claimItemRemarks", "");

			result = new ModelAndView(location);

			String[] required = { "Claim.MemberId" };
			Claim claim = claimService.get(claimId, required);

			MemberLimitLayer smmLayer = null;
			boolean isSMM = false;
			
			if (claim.getIsUsingSMM() != null && claim.getIsUsingSMM() == 1){
				isSMM = true;
				
				if (claim.getSmmLayerId() != null){
					smmLayer = memberLimitLayerService.get(claim.getSmmLayerId().getMemberLimitLayerId());
				}
				
			}
			
			ClaimItem object = null;
			Collection<ClaimItem> checkItems = null;

			if (navigation.equalsIgnoreCase("checkitembulk")) {

				checkItems = claimItemService.getBenefitCheckItem(claim);

				String[] claimParam = { "memberId.memberId", "deletedStatus",
						"caseCategoryId.caseCategoryId" };
				Object[] claimValue = { claim.getMemberId().getMemberId(),
						Integer.valueOf(0),
						claim.getCaseCategoryId().getCaseCategoryId() };

				int total = claimService.getTotal(null, null, claimParam,
						claimValue);

				Collection<Claim> claimList = claimService.search(null, null,
						claimParam, claimValue, 0, total);

				MemberProduct memberProduct = memberProductService.getMemberProductByDate(claim.getMemberId().getMemberId(), 
						claim.getCaseCategoryId().getCaseCategoryId(),
						 claim.getAdmissionDate(),
							claim.getDischargeDate());
				
				if (memberProduct == null){
					memberProduct = memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(), 
							claim.getCaseCategoryId().getCaseCategoryId());
				}
				else {
					if (memberProduct.getFamilyProductId() != null){
						memberProduct = memberProduct.getFamilyProductId();
					}
				}
				
				// **^
				if (memberProduct == null) {
					// potentially divert benefit
					Member theMember = memberService.get(claim.getMemberId()
							.getMemberId());
					Collection<MemberBenefit> benefitList = memberBenefitService
							.getMemberBenefitByDate(theMember.getMemberId(),
									claim.getCaseCategoryId()
											.getCaseCategoryId(), claim
											.getAdmissionDate(), claim
											.getDischargeDate());

					if (benefitList != null && benefitList.size() > 0) {
						for (Iterator iterator2 = benefitList.iterator(); iterator2
								.hasNext();) {
							MemberBenefit memberBenefit2 = (MemberBenefit) iterator2
									.next();

							if (memberBenefit2 != null
									&& memberBenefit2.getMemberProductId() != null) {
								// memberProduct =
								// memberBenefit2.getMemberProductId();
								String[] requiredProd = { "MemberProduct.MemberProductStatus" };
								memberProduct = memberProductService.get(
										memberBenefit2.getMemberProductId()
												.getMemberProductId(),
										requiredProd);
								if (memberProduct != null) {
									if (memberProduct.getMemberProductStatus()
											.getStatusId().intValue() != SubscriptionStatus.ACTIVE
											&& theMember.getStatus().intValue() == SubscriptionStatus.ACTIVE) {
										// update ambil yang terbaru

										memberProduct = memberProductService
												.getMemberActiveProduct(
														theMember.getMemberId(),
														memberBenefit2
																.getProductCaseCategoryId()
																.getCaseCategoryId());

									}
									if (memberProduct.getMemberProductStatus()
											.getStatusId().intValue() != 1
											&& theMember.getStatus().intValue() == SubscriptionStatus.EXPIRED) {

										memberProduct = memberProductService
												.getMemberActiveProduct(
														theMember.getMemberId(),
														memberBenefit2
																.getProductCaseCategoryId()
																.getCaseCategoryId());

									}
								}

								break;
							}

						}
					}
				}
				// **^

				double benefit = 0;
				double outerLimit = 0;
				
				if (memberProduct != null) {

					benefit = memberProduct.getAnnualBenefit() == null ? 0
							: memberProduct.getAnnualBenefit();
				}


				
				Collection<MemberBenefit> memberBenefits = null;
				
				
				if (memberProduct != null){

					
					if (memberProduct.getActualBenefitLimit() != null && !isSMM){
						if (memberProduct.getActualBenefitLimit().doubleValue() == -1.0 || memberProduct.getActualBenefitLimit().doubleValue() > 0.0 ){
							memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
									memberProduct.getMemberProductId(), 0);
						}
						else {
							MemberLimitLayer availableLayer = memberLimitLayerService.getAvailableLayer(memberProduct.getMemberId().getMemberId(), 
									  claim.getCaseCategoryId().getCaseCategoryId(), 
										memberProduct.getMemberProductId());
							
							if (availableLayer != null){
								memberBenefits = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
										memberProduct.getMemberProductId(), availableLayer.getLayer());
								
				
							}
						}
					}					
					else if (isSMM && memberProduct != null && smmLayer != null){
						memberBenefits = new Vector<MemberBenefit>();
						
						Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(memberProduct.getMemberId().getMemberId(),  claim.getCaseCategoryId().getCaseCategoryId(), 
								memberProduct.getMemberProductId(), 0);
						
						if (memberBenefitList != null){
							for (Iterator iterator = memberBenefitList.iterator(); iterator.hasNext();) {
								MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
								
								if (memberBenefit != null){
									MemberBenefit smmBenefit = memberBenefitService
											.getActiveMemberBenefitSMM(smmLayer.getMemberLimitLayerId(), claim.getCaseCategoryId().getCaseCategoryId(), 
													memberBenefit.getItemCategoryId().getItemCategoryId());
									
									if (smmBenefit != null){
										memberBenefits.add(smmBenefit);
									}
									else {
										memberBenefits.add(memberBenefit);
									}
								}								
							}
						}
					}
				}

				
				if (claim.getIsUsingSMM() != null
						&& claim.getIsUsingSMM().intValue() == 1) {
					MemberLimitLayer layerSMM = claim.getSmmLayerId();
					if (layerSMM != null) {
						if (layerSMM.getActualBenefitLimit() != null) {
							outerLimit = layerSMM.getActualBenefitLimit();
						}
					}
				} else if (claim.getIsUsingMultiLayerLimit() != null
						&& claim.getIsUsingMultiLayerLimit().intValue() == 1) {
					MemberLimitLayer layer = claim.getMultiLayerId();
					if (layer != null) {
						if (layer.getActualBenefitLimit() != null) {
							outerLimit = layer.getActualBenefitLimit();
						}
					}
				} else {
					if (memberProduct != null) {
						if (memberProduct.getFamilyProductId() != null) {
							MemberProduct memberProductfamily = memberProductService
									.get(memberProduct.getFamilyProductId()
											.getMemberProductId()); // add by
																	// adq
																	// 20102015
							outerLimit = memberProductfamily
									.getActualBenefitLimit();

						} else if (memberProduct.getActualBenefitLimit() != null) {
							outerLimit = memberProduct.getActualBenefitLimit();
						}
					}
					/*
					 * else{ String memberNumber = claim.getMemberNumber();
					 * double actualValue =
					 * claimService.getTotalUsageValue(memberNumber,
					 * claim.getEffectiveDate(),claim.getExpireDate());
					 * outerLimit = actualValue; outerLimit =
					 * memberProduct.getActualBenefitLimit(); }
					 */
				}

				double totalApproved = 0, totalCharges = 0, totalCovered = 0, totalExcess = 0, totalPrepaidExcess = 0, totalRefund = 0, totalPaidToProvider = 0, totalFaultExcessProvider = 0;

				if (checkItems != null) {
					Iterator<ClaimItem> checkedItemIterator = checkItems
							.iterator();
					while (checkedItemIterator.hasNext()) {
						ClaimItem checkedItem = checkedItemIterator.next();

						if (checkedItem != null) {

							double covered = checkedItem
									.getClaimItemCoveredValue() == null ? 0
									: checkedItem.getClaimItemCoveredValue();
							double exess = checkedItem
									.getExcessValue() == null ? 0
									: checkedItem.getExcessValue();
							double charges = checkedItem
									.getClaimItemValue() == null ? 0
									: checkedItem.getClaimItemValue();
							double prePaidExcess = checkedItem
									.getPrePaidExcess() == null ? 0
									: checkedItem.getPrePaidExcess();
							double refund = checkedItem
									.getRefund() == null ? 0
									: checkedItem.getRefund();
							double paidToProvider = checkedItem
									.getPaidToProvider() == null ? 0
									: checkedItem.getPaidToProvider();
							double faultExcessProvider = checkedItem
									.getFaultExcessProvider() == null ? 0
									: checkedItem.getFaultExcessProvider();
							totalApproved += covered;
							totalCharges += charges;
							totalExcess += exess;
							totalCovered += covered;
							totalPrepaidExcess += prePaidExcess;
							totalRefund += refund;
							totalPaidToProvider += paidToProvider;
							totalFaultExcessProvider+= faultExcessProvider;
						}
					}
				}

				if (memberProduct != null) {
					String[] productParam = { "productId.productId",
							"deletedStatus" };
					Object[] productValue = {
							memberProduct.getProductId().getProductId(),
							Integer.valueOf(0) };
					int totalProductClausul = productClausulService.getTotal(
							null, null, productParam, productValue);
					Collection<ProductClausul> productClausulList = productClausulService
							.search(null, null, productParam, productValue, 0,
									totalProductClausul);
					result.addObject("productClausulList", productClausulList);
				}

				// Add 20150511 by Feiby Veronika for list Clausul
				// System.out.println("MEMBER ID : "+claim.getMemberId().getMemberId());
				// System.out.println("POLICY CLAUSUL : "+claim.getMemberId().getCurrentPolicyId().getPolicyId());
				if (claim.getMemberId().getCurrentPolicyId() != null) {
					Collection<PolicyClausul> inpatientClausul = getPolicyClausul(
							claim.getMemberId().getCurrentPolicyId()
									.getPolicyId(), CaseCategory.INPATIENT);
					Collection<PolicyClausul> outpatientClausul = getPolicyClausul(
							claim.getMemberId().getCurrentPolicyId()
									.getPolicyId(), CaseCategory.OUTPATIENT);
					Collection<PolicyClausul> maternityClausul = getPolicyClausul(
							claim.getMemberId().getCurrentPolicyId()
									.getPolicyId(), CaseCategory.MATERNITY);
					Collection<PolicyClausul> dentalClausul = getPolicyClausul(
							claim.getMemberId().getCurrentPolicyId()
									.getPolicyId(), CaseCategory.DENTAL);
					Collection<PolicyClausul> opticalClausul = getPolicyClausul(
							claim.getMemberId().getCurrentPolicyId()
									.getPolicyId(), CaseCategory.OPTICAL);
					Collection<PolicyClausul> mcuClausul = getPolicyClausul(
							claim.getMemberId().getCurrentPolicyId()
									.getPolicyId(),
							CaseCategory.MEDICAL_CHECK_UP);

					if (inpatientClausul != null && inpatientClausul.size() > 0) {
						result.addObject("ipPolicyClausul", inpatientClausul);
					}
					if (outpatientClausul != null
							&& outpatientClausul.size() > 0) {
						result.addObject("opPolicyClausul", outpatientClausul);
					}
					if (maternityClausul != null && maternityClausul.size() > 0) {
						result.addObject("maPolicyClausul", maternityClausul);
					}
					if (dentalClausul != null && dentalClausul.size() > 0) {
						result.addObject("dentalPolicyClausul", dentalClausul);
					}
					if (opticalClausul != null && opticalClausul.size() > 0) {
						result.addObject("opticalPolicyClausul", opticalClausul);
					}
					if (mcuClausul != null && mcuClausul.size() > 0) {
						result.addObject("mcuPolicyClausul", mcuClausul);
					}
				}
				Double totalApprovedDbl = Double.valueOf(totalApproved);
				Double totalChargesDbl = Double.valueOf(totalCharges);
				Double totalExcessDbl = Double.valueOf(totalExcess);
				Double totalCoveredDbl = Double.valueOf(totalCovered);
				Double totalPrepaidExcessDbl = Double.valueOf(totalPrepaidExcess);
				Double totalRefundDbl = Double.valueOf(totalRefund);
				Double totalPaidToProviderDbl = Double.valueOf(totalPaidToProvider);
				Double totalFaultExcessProviderDbl = Double.valueOf(totalFaultExcessProvider);

				String totalApprovedStr = totalApprovedDbl.toString();

				result.addObject("outerBenefitLimit", outerLimit);
				result.addObject("checkItems", checkItems);
				result.addObject("claimList", claimList);
				result.addObject("memberBenefitList", memberBenefits);
				result.addObject("memberProduct", memberProduct);
				result.addObject("benefit", benefit);

				result.addObject("totalApproved", totalApprovedDbl.longValue());
				result.addObject("totalCharges", totalChargesDbl.longValue());
				result.addObject("totalExcess", totalExcessDbl.longValue());
				result.addObject("totalCovered", totalCoveredDbl.longValue());
				result.addObject("totalPrepaidExcess", totalPrepaidExcessDbl.longValue());
				result.addObject("totalRefund", totalRefundDbl.longValue());
				result.addObject("totalPaidToProvider", totalPaidToProviderDbl.longValue());
				result.addObject("totalFaultExcessProvider", totalFaultExcessProviderDbl.longValue());
				
			} else if (navigation.equalsIgnoreCase("approvecheckbulk")) {

				String[] claimItems = request.getParameterValues("claimItemId");

				String[] description = request
						.getParameterValues("description");
				String[] claimApprovedValue = request
						.getParameterValues("approvedValues");
				String[] exGratiaValues = request
						.getParameterValues("exGratiaValues");
				String[] refundValues = request
						.getParameterValues("refundValues");
				String[] prePaidValues = request
						.getParameterValues("prePaidExcess");
				String[] paidToProviderValues = request
						.getParameterValues("paidToProviderValues");

				result = new ModelAndView(new RedirectView(
						"claim?navigation=detail&claimId=" + claimId));

				boolean isError = validateItemVerification(claimItems, request);

				if (isError) {
					result = addClaimItemPerformed(request, response,
							"addClaimItem");

					Vector vect = new Vector();
					Vector itemVect = new Vector();
					Vector claimApproveVect = new Vector();
					Vector claimExGratiaVect = new Vector();
					Vector refundVect = new Vector();
					Vector paidToProvVect = new Vector();
					Vector prePaidExcessVector = new Vector();

					if (description != null) {
						for (int i = 0; i < description.length; i++) {

							vect.add(description[i]);
							itemVect.add(claimItems[i]);
							claimApproveVect.add(claimApprovedValue[i]);
							claimExGratiaVect.add(exGratiaValues[i]);
							refundVect.add(refundValues[i]);
							prePaidExcessVector.add(prePaidValues[i]);
							paidToProvVect.add(paidToProviderValues[i]);

						}
					}

					result.addObject("claimDescVector", vect);
					result.addObject("claimItemVector", itemVect);

					result.addObject("claimApproveVector", claimApproveVect);
					result.addObject("claimExGratiaVector", claimExGratiaVect);
					result.addObject("refundVector", refundVect);
					result.addObject("prePaidExcess", prePaidExcessVector);
					result.addObject("paidToProviderValues", paidToProvVect);

					result.addObject("navigation", "addclaimitem");
					result.addObject("claimId", claimId);

					System.out.println("TEST : " + vect.get(0));

				} else {
					claim = claimService.get(claimId);

					if (claim != null) {

						Collection collection = new Vector();

						ClaimItem claimItem = null;

						Item item = null;

						if (claimItems != null) {
							String[] requiredCI = { "ClaimItem.ItemId" };
							for (int i = 0; i < claimItems.length; i++) {

								claimItem = claimItemService.get(
										Integer.valueOf(claimItems[i]),
										requiredCI);
								claimItem.setClaimId(claim);

								claimItem
										.setBenefitCheckRemarks(description[i]);

								claimItem.setClaimItemApprovedValue(Double
										.parseDouble(claimApprovedValue[i]));

								if (paidToProviderValues != null
										&& paidToProviderValues[i] != null
										&& !paidToProviderValues[i]
												.equalsIgnoreCase("")) {
									claimItem
											.setPaidToProvider(Double
													.parseDouble(paidToProviderValues[i]));
								}
								if (prePaidValues != null
										&& prePaidValues[i] != null
										&& !prePaidValues[i]
												.equalsIgnoreCase("")) {
									claimItem.setPrePaidExcess(Double
											.valueOf(prePaidValues[i]));
								}
								if (exGratiaValues != null
										&& exGratiaValues[i] != null
										&& !exGratiaValues[i]
												.equalsIgnoreCase("")) {
									claimItem.setClaimExGratia(Double
											.parseDouble(exGratiaValues[i]));
								}
								if (refundValues != null
										&& refundValues[i] != null
										&& !refundValues[i]
												.equalsIgnoreCase("")) {
									claimItem.setRefund(Double
											.valueOf(refundValues[i]));
								}

								collection.add(claimItem);
							}
						}
						claimService.approveClaimBenefit(claimId, collection,
								user);

						if (claim.getBatchClaimId() != null
								&& claim.getCaseId() == null) {
							Integer nextClaimId = claimService
									.getNextClaim(claim.getBatchClaimId()
											.getBatchClaimId());

							if (nextClaimId != null) {
								result = new ModelAndView(new RedirectView(
										"claim?navigation=detail&claimId="
												+ nextClaimId + "&index=0"));
							} else {

								result = new ModelAndView(new RedirectView(
										"batchclaim?navigation=detail&batchClaimId="
												+ claim.getBatchClaimId()
														.getBatchClaimId()
												+ "&index=0"));
							}
						} else if (claim.getCaseId() != null
								&& claim.getBatchClaimId() == null) {
							Case theCase = caseService.get(claim.getCaseId()
									.getCaseId());
							CaseStatus caseStatus = new CaseStatus();
							caseStatus
									.setCaseStatusId(Case.CASE_CLAIM_PROCESSED);
							theCase.setCaseStatusId(caseStatus);
							theCase.setClaimId(claim);
							theCase.setDiagnosis1Id(claim.getDiagnosisId());

							if (claim.getDiagnosis2Id() != null) {
								theCase.setDiagnosis2Id(claim.getDiagnosis2Id());
							}
							if (claim.getDiagnosis3Id() != null) {
								theCase.setDiagnosis3Id(claim.getDiagnosis3Id());
							}

							theCase.setCaseEndTime(claim.getDischargeDate());
							theCase.setCaseExcessValue(claim
									.getClaimExcessValue());
							theCase.setCaseApprovedValue(claim
									.getClaimApprovedValue());
							theCase.setCaseClaimValue(claim
									.getClaimChargeValue());

							caseService.update(theCase, user);

							result = new ModelAndView(new RedirectView(
									"case?navigation=detail&caseId="
											+ theCase.getCaseId() + "&index=0"));
						}
					}
				}
			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("claimId", claimId);
			result.addObject("claim", claim);
			result.addObject("claimItem", object);
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

	public Collection<PolicyClausul> getPolicyClausul(Integer policyId,
			Integer caseCategoryId) throws Exception {

		Collection<PolicyClausul> result = null;

		try {
			if (policyId != null && caseCategoryId != null) {

				String[] required = { "PolicyClausul.LocationId",
						"PolicyClausul.TreatmentUpgradeType",
						"PolicyClausul.ItemCategoryId",
						"PolicyClausul.CaseCategoryId",
						"PolicyClausul.DiagnosisId",
						"PolicyClausul.ProcedureId" };

				String[] eqParam = { "deletedStatus", "policyId.policyId",
						"caseCategoryId.caseCategoryId" };
				Object[] eqValue = { 0, policyId, caseCategoryId };

				// int total =
				// policyClausulService.getTotal(null,null,eqParam,eqValue);
				result = policyClausulService.search(null, null, eqParam,
						eqValue, required, 0, -1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}
	// class+

	// class-

}
