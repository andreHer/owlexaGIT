package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseInvestigation;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseInvestigationService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * CaseInvestigation is a servlet controller for case_investigation Table. All
 * you have to do is to convert necessary data field to the named parameter
 */
public class CaseInvestigationController implements Controller

// extends+

// extends-

{

	private CaseInvestigationService caseInvestigationService;

	private UserService userService;

	private SecurityService securityService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	
	private CaseService caseService;

	
	

	private ActivityLogService logService;

	
	public CaseService getCaseService() {
		return caseService;
	}
	
	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
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

	public void setCaseInvestigationService(
			CaseInvestigationService caseInvestigationService) {
		this.caseInvestigationService = caseInvestigationService;
	}

	public CaseInvestigationService getCaseInvestigationService() {
		return this.caseInvestigationService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long caseInvestigationId = WebUtil.getParameterLong(request,
					"caseInvestigationId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = caseInvestigationId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECASEINVESTIGATION");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECASEINVESTIGATION access");
				return errorResult;
				
			}
			CaseInvestigation res = caseInvestigationService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.caseinvestigation", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.caseinvestigation", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response,
					"searchCaseInvestigation");
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

	public ModelAndView approvePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long caseInvestigationId = WebUtil.getParameterLong(request,
					"caseInvestigationId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = caseInvestigationId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECASEINVESTIGATION");
			
			if (!isUserAuthorized){				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECASEINVESTIGATION access");
				return errorResult;
				
			}
			CaseInvestigation res = caseInvestigationService.get(pkey);

			if (res != null) {
				res.setDecision(1);
				caseInvestigationService.update(res, user);
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.caseinvestigation", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.caseinvestigation", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response,
					"searchCaseInvestigation");
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
	public ModelAndView rejectPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long caseInvestigationId = WebUtil.getParameterLong(request,
					"caseInvestigationId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = caseInvestigationId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECASEINVESTIGATION");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECASEINVESTIGATION access");
				return errorResult;
				
			}
			CaseInvestigation res = caseInvestigationService.get(pkey);

			if (res != null) {
				res.setDecision(0);
				caseInvestigationService.update(res, user);
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.caseinvestigation", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.caseinvestigation", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response,
					"searchCaseInvestigation");
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
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILCASEINVESTIGATION");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILCASEINVESTIGATION access");
				return errorResult;
				
			}
			
			Long caseInvestigationId = WebUtil.getParameterLong(request,
					"caseInvestigationId");

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
			java.io.Serializable pkey = caseInvestigationId;
			CaseInvestigation object = caseInvestigationService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.caseinvestigation", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("caseInvestigation", object);
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
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCASEINVESTIGATION");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCASEINVESTIGATION access");
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

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");
			
			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");

			if (currentNavigation.equalsIgnoreCase("list")){
				location = "listCaseInvestigation";
			}

			result = new ModelAndView(location);

			result.addObject("subnavigation", subnavigation);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null && !rowset.equalsIgnoreCase("") && StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("list")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("category")) {
						vLikeP.add("investigationCategoryId.investigationCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("investigationSubject")) {
						vLikeP.add("investigationSubject");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("headDoctor")) {
						vLikeP.add("headDoctor");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("consultDoctor")) {
						vLikeP.add("consultDoctor");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("nurse")) {
						vLikeP.add("nurse");
						vLikeQ.add(searchtext);
					}
				}

			}

			vEqP.add("caseId.caseId");
			vEqQ.add(caseId);
			result.addObject("caseId", caseId);
			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = caseInvestigationService.getTotal(sLikeP, sLikeQ, sEqP,
					sEqQ);

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

			String required[] = new String[] {
					// foreign affairs
					"CaseInvestigation.InvestigationCategoryId",
					"CaseInvestigation.CaseId",
			// foreign affairs end
			};
			collection = caseInvestigationService.search(sLikeP, sLikeQ, sEqP,
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
				collection = caseInvestigationService.search(sLikeP, sLikeQ,
						sEqP, sEqQ, required, rowsetint, countSet.intValue());
			}
			
			Case caseObject = null;
			
			if(caseId != null){
				try
				{
				java.io.Serializable casepkey = caseId;
				System.out.println("case Id : "+caseId);
				//modified by aju on 20150423, Add link Case-CaseStatus
				String[] caseRequired = {"Case.MemberId","Case.MemberId.ParentMember","Case.MemberId.MemberGroupId","Case.ClaimId","Case.Diagnosis2Id","Case.Diagnosis3Id","Case.CaseStatusId"};
				caseObject = caseService.get(casepkey, caseRequired);
				
				}
				catch (Exception ex)
				{
//					System.out.println("member group object : "+caseObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("CaseInvestigations", collection);
			result.addObject("myCase", caseObject);

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

		String subnavigation = WebUtil.getParameterString(request,
				"subnavigation", "");

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String caseId = request.getParameter("caseId");
		String breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a>";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				
				result = detailPerformed(request, response,"detailCaseInvestigation");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseinvestigation?navigation=list&caseId="+caseId+"\" class=\"linkbreadcrumb\">List Case Investigation</a>";
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Detail Case Investigation";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			}
			 else if (navigation.equalsIgnoreCase("approve")) {
					result = approvePerformed(request, response);
				} 
			 else if (navigation.equalsIgnoreCase("reject")) {
					result = rejectPerformed(request, response);
				} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchCaseInvestigation");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupCaseInvestigation");
			} else if (navigation.equalsIgnoreCase("list")
					|| subnavigation.equalsIgnoreCase("list")) {
				result = searchPerformed(request, response,
						"listCaseInvestigation");
				breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Investigation";
			} else {
				result = searchPerformed(request, response,
						"searchCaseInvestigation");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Case Investigation";
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
