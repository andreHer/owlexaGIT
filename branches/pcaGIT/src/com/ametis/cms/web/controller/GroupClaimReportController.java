package com.ametis.cms.web.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.texen.util.PropertiesUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.dto.ClaimBenefitReport;
import com.ametis.cms.dto.ClaimDiagnosisReport;
import com.ametis.cms.dto.ClaimProviderReport;
import com.ametis.cms.dto.GroupClaimBenefitReport;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimReportService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.PendingClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

public class GroupClaimReportController implements Controller {

	private ClaimService claimService;
	private SecurityService securityService;
	private PendingClaimService pendingClaimService;
	private ClaimItemService claimItemService;
	private UserService userService;
	private PropertiesUtil alertProperties;
	private MemberProductService memberProductService;
	private MemberBenefitService memberBenefitService;
	private ClaimReportService claimReportService;
	private CaseCategoryService caseCategoryService;
	private MemberGroupService memberGroupService;
	
	private ConfigurationService configurationService;
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}
	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}
	public ClaimReportService getClaimReportService() {
		return claimReportService;
	}
	public void setClaimReportService(ClaimReportService claimReportService) {
		this.claimReportService = claimReportService;
	}
	public MemberProductService getMemberProductService() {
		return memberProductService;
	}
	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}
	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}
	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}
	
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}
	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
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
	public SecurityService getSecurityService() {
		return securityService;
	}
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public PendingClaimService getPendingClaimService() {
		return pendingClaimService;
	}
	public void setPendingClaimService(PendingClaimService pendingClaimService) {
		this.pendingClaimService = pendingClaimService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private ModelAndView generateReport (HttpServletRequest request, HttpServletResponse response, String location){
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
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
					"status");
			
			Integer batchClaimId = WebUtil.getParameterInteger(request,"batchClaimId");
			
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			result.addObject("subnavigation",subnavigation);
			Integer claimType = WebUtil.getParameterInteger(request, "claimType");
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			Double claimMinimumValue = WebUtil.getParameterDouble(request, "claimValue");
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			Integer serviceType = WebUtil.getParameterInteger(request, "serviceType");
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
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("printpopup")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */
				
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
					
					if (searchby.equals("clientName")){
						vLikeP.add("memberId.clientId.clientName");
						vLikeQ.add(searchtext);
					}
				}

				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("claimStatus.caseStatusId");
					vEqQ.add(searchStatus);
				}

				if (claimType != null && claimType.intValue() > 0){
					vEqP.add("claimTypeId.claimTypeId");
					vEqQ.add(claimType);
				}
				if (serviceType != null && serviceType.intValue() > 0){
					vEqP.add("caseCategoryId.caseCategoryId");
					vEqQ.add(serviceType);
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

			


			String required[] = new String[] {
			// foreign affairs
					"Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
					"Claim.BatchClaimId", "Claim.CaseCategoryId",
			// foreign affairs end
			};
			
			if (minimumDate != null && maxDate != null){
				collection = claimService.search(sLikeP,sLikeQ,sEqP,sEqQ,"claimDate", minimumDate,maxDate,required,claimMinimumValue);
			}
			else {
				collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required,claimMinimumValue);
			}

			if (collection != null){
				Iterator<Claim> iterator = collection.iterator();
				if (iterator != null){
					Claim claim = null;
					
					while (iterator.hasNext()){
						claim = iterator.next();
						
						if (claim != null){

							double claimItemApproved = 0.0;
							
							Collection<ClaimItem> claimItems = claimItemService.search(null, null, "claimId.claimId", claim.getClaimId(),0,100);
							
							if (claimItems != null){
								Iterator<ClaimItem> iterators = claimItems.iterator();
								
								if (iterators != null){
									ClaimItem citem = null;
									while (iterators.hasNext()){
										citem = iterators.next();
										
										if (citem != null){
											
											if (citem.getClaimItemApprovedValue() != null){
												claimItemApproved += citem.getClaimItemApprovedValue();
											}
										}
									}
								}
							}
							
							
							if (claim.getClaimChargeValue() != null){
								claimChargeValue += claim.getClaimChargeValue();
							}
							
							claimPaidValue += claimItemApproved;
							
							claim.setClaimApprovedValue(claimItemApproved);
							
							if (claim.getClaimPaidValue() != null){
								//claimPaidValue += claim.getClaimPaidValue();
							}
							else {
								claimPaidValue += 0;
							}
							
							if (claim.getClaimExcessValue() != null){
								claimExcessValue += claim.getClaimExcessValue();
							}
							else {
								claimExcessValue += 0;
							}
						}
					}
				}
			}
			
			request.setAttribute("serviceType", serviceType);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("claimType", claimType);
			request.setAttribute("status",searchStatus);
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Claims", collection);
			
			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}
	private ModelAndView generateAllClaimStatisticReport (HttpServletRequest request, HttpServletResponse response, String location){
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			if (navigation.equalsIgnoreCase("claimstatreport")){
				location = "claimStatisticReport";
			}
			else if (navigation.equalsIgnoreCase("popupclaimstat")) {
				location = "popupClaimStatisticReport";
			}
			
			result = new ModelAndView(location);

			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			result.addObject("subnavigation",subnavigation);
			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			Integer serviceType = WebUtil.getParameterInteger(request, "serviceType");
			Integer totalIndex = WebUtil.getParameterInteger(request, "totalIndex") == null ? 10 : WebUtil.getParameterInteger(request, "totalIndex");
			
			Collection<CaseCategory> caseCategories = caseCategoryService.getAll();
			
			Iterator<CaseCategory> ccIterator = caseCategories.iterator();
			
			Vector<Object> totalReport = new Vector<Object>();
			
			while (ccIterator.hasNext()){
				
				CaseCategory cc = ccIterator.next();
			
				Collection<Object[]> coll = claimReportService.generateClaimReport(cc.getCaseCategoryId(), memberGroupId, minimumDate, maxDate, totalIndex);
				
				GroupClaimBenefitReport groupedReport = new GroupClaimBenefitReport();
				groupedReport.setCaseCategory(cc);
				groupedReport.setMaximumDate(maxDate.toString());
				groupedReport.setMinimumDate(minimumDate.toString());
				groupedReport.setServiceType(cc.getCaseCategoryName());
					
				MemberGroup memberGroup = memberGroupService.get(memberGroupId);
				
				if (memberGroup != null){
					request.setAttribute("group", memberGroup.getGroupName());
				}
	
				if (coll != null && coll.size() > 0){
					
					Vector<ClaimBenefitReport> reports = new Vector();
					
					Iterator<Object[]> reportIterator = coll.iterator();
					
					if (reportIterator != null){
						ClaimBenefitReport benefitReport = null;
						
						while (reportIterator.hasNext()){
							Object[] report = reportIterator.next();
							
							if (report != null){
								
								System.out.println(report[0]);
								System.out.println("REPORT Totalc claim : " + report[4]);
								long totalClaim = (Long) report[4];
								
								benefitReport = new ClaimBenefitReport();
								benefitReport.setPatientName((String)report[0]);
								benefitReport.setMemberNo((String) report[1]);
								benefitReport.setRelationship((String) report[2]);
								benefitReport.setEmployeeNo((String) report[3]);
								benefitReport.setTotalClaim((int)totalClaim);							
								benefitReport.setClaimCharge((Double) report[5]);
								benefitReport.setClaimPaid((Double) report[6]);
								benefitReport.setPercentage((Double) report[7]);
								
								reports.add(benefitReport);
							}
						}
					}
					
					groupedReport.setReports(reports);
					//result.addObject("Claims", reports);
					totalReport.add(groupedReport);
					//totalReport.add(reports);
				}
			}
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
			request.setAttribute("serviceType", serviceType);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("status",searchStatus);
			request.setAttribute("services", caseCategories);
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			
			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}
	private ModelAndView generateClaimStatisticReport (HttpServletRequest request, HttpServletResponse response, String location){
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			if (navigation.equalsIgnoreCase("claimstatreport")){
				location = "claimStatisticReport";
			}
			else if (navigation.equalsIgnoreCase("popupclaimstat")) {
				location = "popupClaimStatisticReport";
			}
			
			result = new ModelAndView(location);

			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			result.addObject("subnavigation",subnavigation);
			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			Integer serviceType = WebUtil.getParameterInteger(request, "serviceType");
			Integer totalIndex = WebUtil.getParameterInteger(request, "totalIndex") == null ? 10 : WebUtil.getParameterInteger(request, "totalIndex");
			
			Collection<Object[]> coll = claimReportService.generateClaimReport(serviceType, memberGroupId, minimumDate, maxDate, totalIndex);
			
			CaseCategory caseCategory = caseCategoryService.get(serviceType);
			
			if (caseCategory != null){
				request.setAttribute("service", caseCategory.getCaseCategoryName());
			}
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
			
			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			if (memberGroup != null){
				request.setAttribute("group", memberGroup.getGroupName());
			}

			if (coll != null && coll.size() > 0){
				Collection<ClaimBenefitReport> reports = new Vector();
				
				Iterator<Object[]> reportIterator = coll.iterator();
				
				if (reportIterator != null){
					ClaimBenefitReport benefitReport = null;
					
					while (reportIterator.hasNext()){
						Object[] report = reportIterator.next();
						
						if (report != null){
							
							System.out.println(report[0]);
							System.out.println("REPORT Totalc claim : " + report[4]);
							long totalClaim = (Long) report[4];
							
							benefitReport = new ClaimBenefitReport();
							benefitReport.setPatientName((String)report[0]);
							benefitReport.setMemberNo((String) report[1]);
							benefitReport.setRelationship((String) report[2]);
							benefitReport.setEmployeeNo((String) report[3]);
							benefitReport.setTotalClaim((int)totalClaim);							
							benefitReport.setClaimCharge((Double) report[5]);
							benefitReport.setClaimPaid((Double) report[6]);
							benefitReport.setPercentage((Double) report[7]);
							
							reports.add(benefitReport);
						}
					}
				}
				
				result.addObject("Claims", reports);
			}
			
			request.setAttribute("serviceType", serviceType);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("status",searchStatus);
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			
			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	private ModelAndView generateClaimDiagnosisReport (HttpServletRequest request, HttpServletResponse response, String location){
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			if (navigation.equalsIgnoreCase("claimstatreport")){
				location = "claimStatisticReport";
			}
			else if (navigation.equalsIgnoreCase("popupclaimstat")) {
				location = "popupClaimStatisticReport";
			}
			
			result = new ModelAndView(location);

			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			result.addObject("subnavigation",subnavigation);
			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			Integer serviceType = WebUtil.getParameterInteger(request, "serviceType");
			Integer totalIndex = WebUtil.getParameterInteger(request, "totalIndex") == null ? 10 : WebUtil.getParameterInteger(request, "totalIndex");
			
			Collection<Object[]> coll = claimReportService.generateDiagnosisReport(serviceType, memberGroupId, minimumDate, maxDate, totalIndex);

			CaseCategory caseCategory = caseCategoryService.get(serviceType);

			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
			
			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			if (memberGroup != null){
				request.setAttribute("group", memberGroup.getGroupName());
			}
			if (caseCategory != null){
				request.setAttribute("service", caseCategory.getCaseCategoryName());
			}

			if (coll != null && coll.size() > 0){
				Collection<ClaimDiagnosisReport> reports = new Vector();
				
				Iterator<Object[]> reportIterator = coll.iterator();
				
				if (reportIterator != null){
					ClaimDiagnosisReport diagnosisReport = null;
					
					while (reportIterator.hasNext()){
						Object[] report = reportIterator.next();
						
						if (report != null){
							
							System.out.println(report[0]);
							System.out.println("REPORT Totalc claim : " + report[2]);
							long totalClaim = (Long) report[2];
							long totalParticipant = (Long) report[1];
							
							diagnosisReport = new ClaimDiagnosisReport();
							diagnosisReport.setDiagnosis((String) report[0]);
							diagnosisReport.setAverageCharge((Double) report[5]);
							diagnosisReport.setBenefitPaid((Double) report[4]);
							diagnosisReport.setTotalClaim((int)totalClaim);
							diagnosisReport.setClaimCharge((Double) report[3]);
							diagnosisReport.setPercentageTotal((Double) report[6]);
							diagnosisReport.setNoParticipant((int)totalParticipant);
							//benefitReport.set
							
							reports.add(diagnosisReport);
						}
					}
				}
				
				result.addObject("Claims", reports);
			}
			request.setAttribute("serviceType", serviceType);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("status",searchStatus);
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			
			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	private ModelAndView generateClaimProviderReport (HttpServletRequest request, HttpServletResponse response, String location){
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			if (navigation.equalsIgnoreCase("claimstatreport")){
				location = "claimStatisticReport";
			}
			else if (navigation.equalsIgnoreCase("popupclaimstat")) {
				location = "popupClaimStatisticReport";
			}
			
			result = new ModelAndView(location);

			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			
			Integer memberId = WebUtil.getParameterInteger(request,"memberId");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			result.addObject("subnavigation",subnavigation);
			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			Integer serviceType = WebUtil.getParameterInteger(request, "serviceType");
			Integer totalIndex = WebUtil.getParameterInteger(request, "totalIndex") == null ? 10 : WebUtil.getParameterInteger(request, "totalIndex");
			
			CaseCategory caseCategory = caseCategoryService.get(serviceType);
			
			if (caseCategory != null){
				request.setAttribute("service", caseCategory.getCaseCategoryName());
			}
			
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			if (memberGroup != null){
				request.setAttribute("group", memberGroup.getGroupName());
			}
			Collection<Object[]> coll = claimReportService.generateProviderReport(serviceType, memberGroupId, minimumDate, maxDate, totalIndex);
			
			if (coll != null && coll.size() > 0){
				Collection<ClaimProviderReport> reports = new Vector();
				
				Iterator<Object[]> reportIterator = coll.iterator();
				
				if (reportIterator != null){
					ClaimProviderReport providerReport = null;
					
					while (reportIterator.hasNext()){
						Object[] report = reportIterator.next();
						
						if (report != null){
							
							System.out.println(report[0]);
							System.out.println("REPORT Totalc claim : " + report[4]);
							long totalClaim = (Long) report[1];
							
							providerReport = new ClaimProviderReport();
							providerReport.setProviderName((String) report[0]);
							providerReport.setTotalClaim((int)totalClaim);
							providerReport.setClaimCharge((Double)report[2]);
							providerReport.setClaimPaid((Double)report[3]);
							providerReport.setPercentageTotal((Double) report[5]);
							providerReport.setAverageClaim((Double) report[4]);
							
							
							reports.add(providerReport);
						}
					}
				}
				
				result.addObject("Claims", reports);
			}
			
			request.setAttribute("serviceType", serviceType);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("status",searchStatus);
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			
			
			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	private ModelAndView generateTop10Report (HttpServletRequest request, HttpServletResponse response, String location){
		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			
			result = new ModelAndView(location);

			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			result.addObject("subnavigation",subnavigation);
			result.addObject("navigation",navigation);
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			Integer serviceType = WebUtil.getParameterInteger(request, "serviceType");
			Integer totalIndex = WebUtil.getParameterInteger(request, "totalIndex") == null ? 10 : WebUtil.getParameterInteger(request, "totalIndex");
			
			if (serviceType == null || serviceType.equals(0)){
				serviceType = Integer.valueOf(1);
			}
			
			CaseCategory caseCategory = caseCategoryService.get(serviceType);
			
			if (caseCategory != null){
				request.setAttribute("service", caseCategory.getCaseCategoryName());
			}
			
			
			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			if (memberGroup != null){
				request.setAttribute("group", memberGroup.getGroupName());
				result.addObject("groupName", memberGroup.getGroupName());
			}
			
			if (memberGroup != null){
				if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){
					minimumDate = memberGroup.getEffectiveDate();
				}
				if (maxDate != null && maxDate.toString().equalsIgnoreCase("1970-01-01")){
					maxDate = memberGroup.getExpireDate();
				}
			}
			Collection<Object[]> providerStatColl = claimReportService.generateProviderReport(serviceType, memberGroupId, minimumDate, maxDate, totalIndex);
			
			if (providerStatColl != null && providerStatColl.size() > 0){
				Collection<ClaimProviderReport> reports = new Vector();
				
				Iterator<Object[]> reportIterator = providerStatColl.iterator();
				
				if (reportIterator != null){
					ClaimProviderReport providerReport = null;
					
					while (reportIterator.hasNext()){
						Object[] report = reportIterator.next();
						
						if (report != null){
							
							System.out.println(report[0]);
							System.out.println("REPORT Totalc claim : " + report[4]);
							long totalClaim = (Long) report[1];
							
							providerReport = new ClaimProviderReport();
							providerReport.setProviderName((String) report[0]);
							providerReport.setTotalClaim((int)totalClaim);
							providerReport.setClaimCharge((Double)report[2]);
							providerReport.setClaimPaid((Double)report[3]);
							providerReport.setPercentageTotal((Double) report[5]);
							providerReport.setAverageClaim((Double) report[4]);
							
							
							reports.add(providerReport);
						}
					}
				}
				
				result.addObject("Providers", reports);
			}
		
			Collection<Object[]> benefitStatcoll = claimReportService.generateClaimReport(serviceType, memberGroupId, minimumDate, maxDate, totalIndex);

			if (benefitStatcoll != null && benefitStatcoll.size() > 0){
				Collection<ClaimBenefitReport> reports = new Vector();
				
				Iterator<Object[]> reportIterator = benefitStatcoll.iterator();
				
				if (reportIterator != null){
					ClaimBenefitReport benefitReport = null;
					
					while (reportIterator.hasNext()){
						Object[] report = reportIterator.next();
						
						if (report != null){
							
							System.out.println(report[0]);
							System.out.println("REPORT Totalc claim : " + report[4]);
							long totalClaim = (Long) report[4];
							
							benefitReport = new ClaimBenefitReport();
							benefitReport.setPatientName((String)report[0]);
							benefitReport.setMemberNo((String) report[1]);
							benefitReport.setRelationship((String) report[2]);
							benefitReport.setEmployeeNo((String) report[3]);
							benefitReport.setTotalClaim((int)totalClaim);							
							benefitReport.setClaimCharge((Double) report[5]);
							benefitReport.setClaimPaid((Double) report[6]);
							benefitReport.setPercentage((Double) report[7]);
							
							reports.add(benefitReport);
						}
					}
				}
				
				result.addObject("Claims", reports);
			}
			
			

			Collection<Object[]> diagnosisStatcoll = claimReportService.generateDiagnosisReport(serviceType, memberGroupId, minimumDate, maxDate, totalIndex);


			if (diagnosisStatcoll != null && diagnosisStatcoll.size() > 0){
				Collection<ClaimDiagnosisReport> reports = new Vector();
				
				Iterator<Object[]> reportIterator = diagnosisStatcoll.iterator();
				
				if (reportIterator != null){
					ClaimDiagnosisReport diagnosisReport = null;
					
					while (reportIterator.hasNext()){
						Object[] report = reportIterator.next();
						
						if (report != null){
							
							System.out.println(report[0]);
							System.out.println("REPORT Totalc claim : " + report[2]);
							long totalClaim = (Long) report[2];
							long totalParticipant = (Long) report[1];
							
							diagnosisReport = new ClaimDiagnosisReport();
							diagnosisReport.setDiagnosis((String) report[0]);
							diagnosisReport.setAverageCharge((Double) report[5]);
							diagnosisReport.setBenefitPaid((Double) report[4]);
							diagnosisReport.setTotalClaim((int)totalClaim);
							diagnosisReport.setClaimCharge((Double) report[3]);
							diagnosisReport.setPercentageTotal((Double) report[6]);
							diagnosisReport.setNoParticipant((int)totalParticipant);
							//benefitReport.set
							
							reports.add(diagnosisReport);
						}
					}
				}
				
				result.addObject("Diagnosis", reports);
			}
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	public ModelAndView generateBulkReport (HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

		
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String formatReport = WebUtil.getParameterString(request, "formatReport", "PDF");
			
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			
		
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
		
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			Configuration configuration = configurationService.getSystemConfiguration();

			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
		
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			String groupName = "";
			
			if (memberGroup != null){
				groupName = memberGroup.getGroupName();
			}
			request.setAttribute("alert", request.getParameter("alert"));
			System.out.println("MIN DATE :" + minimumDate);
			System.out.println("MAX DATE :"+ maxDate);
		
			String ip = "1";
			String op = "1";
			String mat = "1";
			String dental = "1";
			String optical = "1";
			String mcu = "1";
			
			String reportFile = "GroupReport"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			result = new ModelAndView(new RedirectView("run"));
		
			result.addObject("__report", "reports/"+reportFile);
			result.addObject("__format", formatReport);
			result.addObject("GroupId", memberGroupId);
			result.addObject("GroupName", groupName);
			result.addObject("periode", minimumDate + " s/d " + maxDate);
			result.addObject("minIntervalDate", minimumDate);
			result.addObject("maxIntervalDate", maxDate);
			result.addObject("ip", ip);
			result.addObject("op", op);
			result.addObject("mat", mat);
			result.addObject("dental", dental);
			result.addObject("mcu", mcu);
			result.addObject("optic", optical);
			

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}
	public ModelAndView generateClaimPaymentReport (HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}
			
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			String formatReport = WebUtil.getParameterString(request, "formatReport", "DOC");
			
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			
		
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
		
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			

			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
		
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			String groupName = "";
			
			if (memberGroup != null){
				groupName = memberGroup.getGroupName();
			}
			request.setAttribute("alert", request.getParameter("alert"));
			System.out.println("MIN DATE :" + minimumDate);
			System.out.println("MAX DATE :"+ maxDate);
			
			result = new ModelAndView(new RedirectView("run"));
			result.addObject("__report", "reports/ClaimPaymentReport.rptdesign");
			result.addObject("__format", formatReport);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("GroupName", groupName);
			result.addObject("groupPeriode", minimumDate + " s/d " + maxDate);
			result.addObject("minDate", minimumDate);
			result.addObject("maxDate", maxDate);

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}
	public ModelAndView generateCostContainmentReport (HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}


			String formatReport = WebUtil.getParameterString(request, "formatReport", "PDF");	
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");				
			Configuration configuration = configurationService.getSystemConfiguration();

			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
		
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			String groupName = "";
			
			if (memberGroup != null){
				groupName = memberGroup.getGroupName();
			}
			request.setAttribute("alert", request.getParameter("alert"));
			System.out.println("MIN DATE :" + minimumDate);
			System.out.println("MAX DATE :"+ maxDate);
		
			
			String reportFile = "CostContainment"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			result = new ModelAndView(new RedirectView("run"));
		
			result.addObject("__report", "reports/"+reportFile);
			result.addObject("__format", formatReport);
			result.addObject("GroupId", memberGroupId);
			result.addObject("GroupName", groupName);
			result.addObject("periode", minimumDate + " s/d " + maxDate);
			result.addObject("minIntervalDate", minimumDate);
			result.addObject("maxIntervalDate", maxDate);

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}
	public ModelAndView generateOutstandingEDCReport (HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView result = null;
		try {
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;
				
			}


			String formatReport = WebUtil.getParameterString(request, "formatReport", "PDF");	
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");				
			Configuration configuration = configurationService.getSystemConfiguration();

			
			
			Double claimChargeValue = 0.0;
			Double claimPaidValue = 0.0;
			Double claimExcessValue = 0.0;
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			request.setAttribute("minimumDate", minimumDate);
			request.setAttribute("maximumDate", maxDate);
		
			
			request.setAttribute("claimChargeValue", claimChargeValue);
			request.setAttribute("claimExcessValue", claimExcessValue);
			request.setAttribute("claimPaidValue",claimPaidValue);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			String groupName = "";
			
			if (memberGroup != null){
				groupName = memberGroup.getGroupName();
			}
			request.setAttribute("alert", request.getParameter("alert"));
			System.out.println("MIN DATE :" + minimumDate);
			System.out.println("MAX DATE :"+ maxDate);
		
			
			String reportFile = "OutstandingEDCTransaction"+"_"+configuration.getCompanyCode().toLowerCase()+".rptdesign";
			result = new ModelAndView(new RedirectView("run"));
		
			result.addObject("__report", "reports/"+reportFile);
			result.addObject("__format", formatReport);
			result.addObject("GroupId", memberGroupId);
			result.addObject("GroupName", groupName);
			result.addObject("periode", minimumDate + " s/d " + maxDate);
			result.addObject("minIntervalDate", minimumDate);
			result.addObject("maxIntervalDate", maxDate);

		} catch (Exception e) {
			e.printStackTrace();

//			request.setAttribute("alert", alertProperties.getMessage(
//					"system.error " + e.getMessage(), null, "fail", Locale
//							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");

		String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
		
		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		/*
		 * if (session == null) {
		 * 
		 * request.setAttribute("alert", "<b>" +
		 * alertProperties.getValue("not.login") + "</b>");
		 * request.setAttribute("content", "/jsp/login.jsp");
		 * forward("/main.jsp", request, response);
		 * 
		 * 
		 * result = new ModelAndView ("login"); } else { }
		 */
		String breadcrumb = "";
		try {
			if (navigation.equalsIgnoreCase("report")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = generateReport(request, response, "reportClaim");				
			}
			else if (navigation.equalsIgnoreCase("printpopup")){
				result = generateReport(request, response, "popupPrintOut");
			}
			else if (navigation.equalsIgnoreCase("claimstatreport") || navigation.equalsIgnoreCase("popupclaimstat")){
				result = generateClaimStatisticReport(request, response, "claimStatisticReport");
			}
			else if (navigation.equalsIgnoreCase("diagnosispopup")){
				result = generateClaimDiagnosisReport(request, response, "claimDiagnosisReport");
			}
			else if (navigation.equalsIgnoreCase("providerpopup")){
				result = generateClaimProviderReport(request, response, "claimProviderReport");
			}
			else if (navigation.equalsIgnoreCase("benefitpopup")){
				result = generateClaimStatisticReport(request, response, "claimBenefitReport");
			}
			else if (navigation.equalsIgnoreCase("bulkgroup")){
				result = generateBulkReport(request, response);
			}
			else if (navigation.equalsIgnoreCase("top10report") || navigation.equalsIgnoreCase("gosearchtop10") ){
				result = generateTop10Report(request, response, "top10ReportPopup");
			}
			else if (navigation.equalsIgnoreCase("groupclaimpayment")){
				result = generateClaimPaymentReport(request, response);
			}
			else if (navigation.equalsIgnoreCase("costcontainment")){
				result = new ModelAndView("reportGroupCostContainment");
			}
			else if (navigation.equalsIgnoreCase("downloadcostcontainment")){
				result = generateCostContainmentReport(request,response);
			}
			else if (navigation.equalsIgnoreCase("outstandingedc")){
				result = new ModelAndView("reportOutstandingEDCTransaction");
			}
			else if (navigation.equalsIgnoreCase("downloadoutstandingedc")){
				result = generateOutstandingEDCReport(request,response);
			}
			else {
				result = generateReport(request, response, "reportGroupClaim");
				breadcrumb = "<a href=\"groupclaimreport\">Search Group Claim Report</a>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;	}
	
	
}
