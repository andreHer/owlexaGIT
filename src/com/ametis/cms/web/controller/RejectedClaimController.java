package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
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
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.RejectedClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.CaseReportDownloader;
import com.ametis.cms.util.ClaimDocumentDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * RejectedClaim is a servlet controller for rejected_claim Table. All you have
 * to do is to convert necessary data field to the named parameter
 */
public class RejectedClaimController implements Controller

// extends+

// extends-

{

	private RejectedClaimService rejectedClaimService;
	private ClaimService claimService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	private SecurityService securityService;

	private ActivityLogService logService;
	private BatchClaimService batchClaimService;

	
	
	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
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

	public void setRejectedClaimService(
			RejectedClaimService rejectedClaimService) {
		this.rejectedClaimService = rejectedClaimService;
	}

	public RejectedClaimService getRejectedClaimService() {
		return this.rejectedClaimService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long rejectedClaimId = WebUtil.getParameterLong(request,
					"rejectedClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = rejectedClaimId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEREJECTEDCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEREJECTEDCLAIM access");
				return errorResult;
				
			}
			RejectedClaim res = rejectedClaimService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.rejectedclaim", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.rejectedclaim", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchRejectedClaim");
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
			Long rejectedClaimId = WebUtil.getParameterLong(request,
					"rejectedClaimId");

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
			java.io.Serializable pkey = rejectedClaimId;
			RejectedClaim object = rejectedClaimService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.rejectedclaim", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("rejectedClaim", object);
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

	public ModelAndView printPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
			Long rejectedClaimId = WebUtil.getParameterLong(request,
					"rejectedClaimId");

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
			java.io.Serializable pkey = rejectedClaimId;
			
			String[] required = {"Claim.MemberId","Claim.MemberId.ParentMember","Claim.MemberId.MemberGroupId","Claim.MemberId.ClientId","Claim.DiagnosisId"};
			Claim claim = claimService.get(claimId,required);
			
			if (claim != null){
				
				String[] param = {"claimId.claimId"};
				Object[] value = {claim.getClaimId()};
				
				Integer total = rejectedClaimService.getTotal(null,null,param,value);
				Collection<RejectedClaim> rejectedClaims = rejectedClaimService.search(null,null,param,value,0,total);
				
				if (rejectedClaims != null ){
					
					RejectedClaim object = rejectedClaims.iterator().next();
		
					if (object == null) {
						request.setAttribute("alert", alertProperties.getMessage(
								"not.found.rejectedclaim", null, "fail", Locale
										.getDefault()));
					}
				
					
					result.addObject("rejectedClaim", object);
					result.addObject("index", index);
					result.addObject("searchtext", searchtext);
					result.addObject("searchby", searchby);
					
				}
				
				Member policyHolder = claim.getMemberId().getParentMember();
				
				result.addObject("member", claim.getMemberId());
				result.addObject("policyHolder", policyHolder);
				result.addObject("claim", claim);
			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	
	public ModelAndView downloadRejectedClaimPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		
		ModelAndView result = null;
		try {
			
			
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCASE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCASE access");
				return errorResult;
				
			}

			String url = WebUtil.getParameterString(request, "url","");
			
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
                        


			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");
			
			Integer clientId = WebUtil.getParameterInteger(request,
			"clientId");

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");


			String providerText = WebUtil.getParameterString(request, "providerText", "");
			


			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");
			
			
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
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golist") || navigation.equalsIgnoreCase("gosearchissued")) {

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
					//Penambahan Kriteria Search untuk Case Category
					if(searchby.equals("category")){
						vLikeP.add("caseCategoryId.caseCategoryName");
						vLikeQ.add(searchtext);
					}
					

					if (searchStatus != null && searchStatus.intValue() > 0) {
						vEqP.add("caseStatusId.caseStatusId");
						vEqQ.add(searchStatus);
					}
					

				}

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
			
			
			if (memberGroupId != null){
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
			}
			
			if (providerId != null){
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
			}

			if (clientId != null){
				vEqP.add("memberId.clientId.clientId");
				vEqQ.add(user.getUser().getClientId().getClientId());
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

			count = rejectedClaimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			
			String required[] = new String[] {
					// foreign affairs
					"RejectedClaim.RejectCategoryId", "RejectedClaim.ClaimId"};
			collection = rejectedClaimService.search(sLikeP, sLikeQ, sEqP, sEqQ,false,"rejectedClaimId",
					required, rowsetint, count);


			if(navigation.equalsIgnoreCase("downloadreject")){
				HSSFWorkbook workbook = ClaimDocumentDownloader.downloadRejectedClaimReport(collection);
				
				response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Pragma", "no-cache");
	            response.setHeader("Cache-Control", "no-cache");
	            response.setDateHeader("Expires", 0);

	            response.setHeader("Content-disposition", "inline; filename="
	                    + "claim-rejection-report.xls");

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

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Integer batchClaimId = WebUtil.getParameterInteger(request,"batchClaimId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			result.addObject("subnavigation",subnavigation);
			
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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golist") || navigation.equals("list")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("rejectCategory")) {
						vLikeP.add("rejectionCategory.rejectCategoryName");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("claimNumber")) {
						vLikeP.add("claimNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberId.firstName");
						vLikeQ.add(searchtext);
					}
				}

			}
			
			if (navigation.equalsIgnoreCase("list") || navigation.equalsIgnoreCase("golist")){
				
				vEqP.add("claimId.batchClaimId.batchClaimId");
				vEqQ.add(batchClaimId);
				
				String[] reqBatch = {"BatchClaim.PaymentRecipient","BatchClaim.ProviderId","BatchClaim.MemberGroupId",
						"BatchClaim.MemberId","BatchClaim.BatchClaimStatus","BatchClaim.PaymentMethod","BatchClaim.ClaimCurrency"};
				BatchClaim batchClaim = batchClaimService.get(batchClaimId,reqBatch);
				if (batchClaim != null){
					result.addObject("batchClaim", batchClaim);
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

			count = rejectedClaimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

			String required[] = new String[] {"RejectedClaim.ClaimId","RejectedClaim.ClaimId.MemberId"};
			collection = rejectedClaimService.search(sLikeP, sLikeQ, sEqP,
					sEqQ, required, rowsetint, countSet.intValue());

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
				collection = rejectedClaimService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("RejectedClaims", collection);
			result.addObject("batchClaimId",batchClaimId);

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
		 * result = new ModelAndView ("login"); } else {
		 *  }
		 */
		String breadcrumb = "";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailRejectedClaim");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchRejectedClaim");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupRejectedClaim");
			}
			else if (navigation.equalsIgnoreCase("print")) {
				result = printPerformed(request, response,
						"printRejectClaim");	
			}
			else if (navigation.equalsIgnoreCase("list") || subnavigation.equalsIgnoreCase("list") || navigation.equalsIgnoreCase("golist") ){
				result = searchPerformed (request, response, "listRejectedClaim");
				String batchClaimId = request.getParameter("batchClaimId");				
				breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="+batchClaimId+"\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Rejected Claim";
			}
			
			else {
				result = searchPerformed(request, response,
						"searchRejectedClaim");
				breadcrumb = "<a href=\"rejectedclaim?navigation=searchissued?navigation=searchissued\">Search Rejected Claim</a>";
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
