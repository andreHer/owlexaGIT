package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberLimitLayer;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

public class MemberBenefitController implements Controller

// extends+

// extends-

{

	private MemberBenefitService memberBenefitService;
	private MemberLimitLayerService memberLimitLayerService;
	private MemberProductService memberProductService;

	private UserService userService;

	private MemberService memberService;
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	private SecurityService securityService;
	private ClaimService claimService;
	
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	
	
	
	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}

	public void setMemberLimitLayerService(
			MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	private ActivityLogService logService;

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
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

	public void setMemberBenefitService(
			MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return this.memberBenefitService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberBenefitId = WebUtil.getParameterInteger(request,
					"memberBenefitId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = memberBenefitId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEMEMBERBENEFIT");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult
						.addObject("errorMessage",
								"You Are Not Authorized for DELETEMEMBERBENEFIT access");
				return errorResult;

			}
			MemberBenefit res = memberBenefitService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.memberbenefit", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.memberbenefit", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchMemberBenefit");
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
			Integer memberBenefitId = WebUtil.getParameterInteger(request,
					"memberBenefitId");

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
			java.io.Serializable pkey = memberBenefitId;
			MemberBenefit object = memberBenefitService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.memberbenefit", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("memberBenefit", object);
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
			result = new ModelAndView(location);

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			//Add by aju on 20150929, for handle iFrame things fufufu
			if(isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")){
				System.out.println("it\'s still on member(" + sessionMemberId + ") login session fufufu...");
				memberId = (memberId==null?Integer.parseInt(sessionMemberId):memberId);
				Member theMember = memberService.get(memberId);
				if(theMember!=null){
					System.out.println("checking->requestParentMemberId(" + theMember.getParentMember().getMemberId().toString() + ") vs sessionParentMemberId(" + sessionMemberParentId + ")");
					if(!theMember.getParentMember().getMemberId().toString().equals(sessionMemberParentId)){
						memberId=Integer.parseInt(sessionMemberId);
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","OtherFamilyAccessDenied");			
						errorResult.addObject("errorMessage", "Hey member("+ sessionMemberId + "), are you missing your way? :P<br/>" 
								+ "<a href=\"memberbenefit?navigation=listmember&memberId="+memberId+"\">Go Back</a>");
						return errorResult;
					}
				}
			}
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			MemberProduct ipProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.INPATIENT);

			Collection<MemberBenefit> inpatient = getBenefit(memberId,
					CaseCategory.INPATIENT,ipProduct,0);
			
			if (inpatient != null && ipProduct != null){
				Collection<MemberLimitLayer> inpatientLayer = memberLimitLayerService.getLayerList(ipProduct.getMemberProductId());
				result.addObject("inpatientLayer", inpatientLayer);
				
				result.addObject("inpatientProduct", ipProduct);
				
			}

			MemberProduct opProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.OUTPATIENT);
			
			Collection<MemberBenefit> outpatient = getBenefit(memberId,
					CaseCategory.OUTPATIENT,opProduct,0);
			
			if (outpatient != null && opProduct != null){
				Collection<MemberLimitLayer> outpatientLayer =  memberLimitLayerService.getLayerList(opProduct.getMemberProductId());
				result.addObject("outpatientLayer", outpatientLayer);
				
				result.addObject("outpatientProduct", opProduct);
			}
			MemberProduct mtProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.MATERNITY);

			Collection<MemberBenefit> maternity = getBenefit(memberId,
					CaseCategory.MATERNITY,mtProduct,0);
			
			if (maternity != null && mtProduct != null){
				Collection<MemberLimitLayer> maternityLayer =  memberLimitLayerService.getLayerList(mtProduct.getMemberProductId());
				result.addObject("maternityLayer", maternityLayer);
				
				result.addObject("maternityProduct", mtProduct);
			}
			MemberProduct deProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.DENTAL);
			Collection<MemberBenefit> dental = getBenefit(memberId,
					CaseCategory.DENTAL,deProduct,0);
			
			if (dental != null && deProduct != null){
				Collection<MemberLimitLayer> dentalLayer =  memberLimitLayerService.getLayerList(deProduct.getMemberProductId());
				result.addObject("dentalLayer", dentalLayer);
				
				
				result.addObject("dentalProduct", deProduct);
			}
			
			MemberProduct optProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.OPTICAL);

			Collection<MemberBenefit> optical = getBenefit(memberId,
					CaseCategory.OPTICAL,optProduct,0);
			
			if (optical != null  && optProduct != null){
				Collection<MemberLimitLayer> opticalLayer =  memberLimitLayerService.getLayerList(optProduct.getMemberProductId());
				result.addObject("opticalLayer", opticalLayer);
				
				result.addObject("opticalProduct", optProduct);
			}
			MemberProduct spcProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.SPECIALIST);

			Collection<MemberBenefit> specialist = getBenefit(memberId,
					CaseCategory.SPECIALIST,spcProduct,0);
			
			if (specialist != null && spcProduct != null){
				Collection<MemberLimitLayer> dentalLayer =  memberLimitLayerService.getLayerList(spcProduct.getMemberProductId());
				result.addObject("specialistLayer", dentalLayer);
				
				result.addObject("specialistProduct", spcProduct);
			}
			MemberProduct mcuProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.MEDICAL_CHECK_UP);

			Collection<MemberBenefit> mcu = getBenefit(memberId,
					CaseCategory.MEDICAL_CHECK_UP,mcuProduct,0);
			
			MemberProduct miscProduct = memberProductService.getMemberActiveProduct(memberId, CaseCategory.MISC);

			Collection<MemberBenefit> misc = getBenefit(memberId,
					CaseCategory.MISC,miscProduct,0);
			
			MemberProduct dentPPK1Product = memberProductService.getMemberActiveProduct(memberId, CaseCategory.GP_DENTAL);

			Collection<MemberBenefit> ppk1gigi = getBenefit(memberId,
					CaseCategory.GP_DENTAL,dentPPK1Product,0);

			MemberProduct gpPPK1Product = memberProductService.getMemberActiveProduct(memberId, CaseCategory.GP_OUTPATIENT);

			Collection<MemberBenefit> ppk1Umum = getBenefit(memberId,
					CaseCategory.GP_OUTPATIENT,gpPPK1Product,0);
			

			request.setAttribute("navigation", navigation);

			result.addObject("MemberBenefits", inpatient);
			
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

			result.addObject("inpatient", inpatient);
			result.addObject("outpatient", outpatient);
			result.addObject("maternity", maternity);
			result.addObject("optical", optical);
			result.addObject("dental", dental);
			result.addObject("specialist", specialist);
			result.addObject("mcu", mcu);
			result.addObject("misc", misc);
			result.addObject("ppk1Dental", ppk1gigi);
			result.addObject("ppk1Umum", ppk1Umum);
			result.addObject("claim", claimObject);
			

			result.addObject("memberId", memberId);
			result.addObject("claimId", claimId);
			result.addObject("caseId", caseId);
			request.setAttribute("countSet", countSet);

			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	private Collection<MemberBenefit> getBenefit(Integer memberId,
			Integer benefitType,MemberProduct activeProduct,Integer layer) throws Exception {

		Collection<MemberBenefit> collection = null;

		if (activeProduct != null){
			collection = memberBenefitService.getMemberBenefitList(memberId, benefitType, activeProduct.getMemberProductId(),layer);
		}
		else {
			collection = memberBenefitService.getMemberBenefitByDate(memberId, benefitType, new java.sql.Date(System.currentTimeMillis()),
					 new java.sql.Date(System.currentTimeMillis()));
		}

		if (collection != null && collection.size() > 0) {
			return collection;
		} else {
			return null;
		}

	}
	private Collection<MemberLimitLayer> getBenefitLayer(Integer memberId,
			Integer benefitType) throws Exception {

		Collection collection = null;

		Vector vLikeP = new Vector();
		Vector vLikeQ = new Vector();
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();

		vEqP.add("deletedStatus");
		vEqQ.add(Integer.valueOf(0));

		vEqP.add("status");
		vEqQ.add(SubscriptionStatus.ACTIVE);

		if (memberId != null) {
			vEqP.add("memberId.memberId");
			vEqQ.add(memberId);
		}
		if (benefitType != null) {
			vEqP.add("productId.caseCategory.caseCategoryId");
			vEqQ.add(benefitType);
		}

		String sLikeP[] = new String[vLikeP.size()];
		vLikeP.toArray(sLikeP);
		Object sLikeQ[] = new Object[vLikeP.size()];
		vLikeQ.toArray(sLikeQ);

		String sEqP[] = new String[vEqP.size()];
		vEqP.toArray(sEqP);
		Object sEqQ[] = new Object[vEqP.size()];
		vEqQ.toArray(sEqQ);

		String required[] = new String[] { "MemberLimitLayer.MemberId",
				"MemberLimitLayer.ItemCategoryId","MemberLimitLayer.DiagnosisId" };

		int total = memberLimitLayerService.getTotal(sLikeP,sLikeQ,sEqP,sEqQ);
		collection = memberLimitLayerService.search(sLikeP, sLikeQ, sEqP, sEqQ,true,"layer",0,total);

		if (collection != null && collection.size() > 0) {
			return collection;
		} else {
			return null;
		}

	}
	
	public ModelAndView jsonBenitRoomRatePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberId = WebUtil.getParameterInteger(request,
					"memberId");
			
			Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");

			System.out.println("memberId = " + memberId);
			
			result = new ModelAndView(location);
			double benefitLimit = 0.0;
			
			if (caseCategoryId != null && caseCategoryId.intValue() == CaseCategory.INPATIENT){
				MemberBenefit memberBenefit = null ;
				String[] param = {"memberId.memberId", "deletedStatus", "itemCategoryId.itemCategoryId","caseCategoryId.caseCategoryId"};
				Object[] value = {memberId, Integer.valueOf(0), Integer.valueOf(1),caseCategoryId};
						
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.search(null,null,param,value,false,"memberBenefitId",0,1);
				
				if (memberBenefitList != null && !memberBenefitList.isEmpty()){
					Iterator<MemberBenefit> iterator = memberBenefitList.iterator();
					
					if (iterator.hasNext()){
						memberBenefit = iterator.next();
					}
				}
				
				System.out.println("memberBenefit = " + memberBenefit);
				
				if(memberBenefit!=null){
					   benefitLimit = memberBenefit.getBenefitLimit() ;
					  
				}
			}
			
			//begin riyan set benefit room rate
			else{
				String[] param = { "memberId.memberId", "itemCategoryId.itemCategoryId", "deletedStatus","memberBenefitStatus" };
				Object[] value = { memberId, Integer.valueOf(1), Integer.valueOf(0),Integer.valueOf(1) };

				
				MemberBenefit benefit = memberBenefitService.searchUnique(param, value, 0, 1);
				
				benefitLimit=benefit.getBenefitLimit();
				
			}
			//end riyan set benefit room rate
			System.out.println("benefitLimit = " + benefitLimit);

			result.addObject("result", benefitLimit);

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

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String memberId = request.getParameter("memberId");
		String breadcrumb = "";

		try {
			//add aju on 20150929, new iFrame checker fufufu plus init
			System.out.println("MemberBenefitController::handleRequest()...");
			isIFrameSession = securityService.isUsingIFrameSession(request);
			iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
			System.out.println("securityService->SessionMemberId->" + securityService.getTheSessionMemberId());
			sessionMemberId = securityService.getTheSessionMemberId();
			System.out.println("securityService->SessionParentMemberId->" + securityService.getTheSessionMemberParentId());
			sessionMemberParentId = securityService.getTheSessionMemberParentId();
			
			
			if (navigation.equalsIgnoreCase("detail")) {

				result = detailPerformed(request, response,
						"detailMemberBenefit");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchMemberBenefit");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupMemberBenefit");
			} else if (navigation.equalsIgnoreCase("listclaim")) {
				result = searchPerformed(request, response, "listClaimMemberBenefit");
				String claimId = request.getParameter("claimId");

				breadcrumb = "<a href=\"claim?navigation=detail&claimId="
						+ claimId
						+ "\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim Benefit";

			} else if (navigation.equalsIgnoreCase("listmember")) {
				result = searchPerformed(request, response, "listMemberBenefit");

				breadcrumb = "<a href=\"member?navigation=detail&memberId="
						+ memberId
						+ "\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Benefit";
			} 
			
			else if (navigation.equalsIgnoreCase("listcase")){

				String caseId = request.getParameter("caseId");
				result = searchPerformed(request, response, "listCaseMemberBenefit");
				breadcrumb = "<a href=\"case?navigation=detail&caseId="
					+ caseId
					+ "\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Member Benefit";
			}
			else if (navigation.equalsIgnoreCase("jsonbenefitroomrate") ) {
				result = jsonBenitRoomRatePerformed(request,response,"jsonMemberBenefitLimit");
			}
			else {
				result = searchPerformed(request, response,
						"searchMemberBenefit");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-

}
