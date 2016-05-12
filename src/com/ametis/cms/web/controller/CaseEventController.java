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

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseEventService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.CaseReportDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * CaseEvent is a servlet controller for case_event Table. All you have to do is
 * to convert necessary data field to the named parameter
 */
public class CaseEventController implements Controller

// extends+

// extends-

{

	private CaseEventService caseEventService;
	
	private SecurityService securityService;
	
	private CaseService caseService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;

private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
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

	public void setCaseEventService(CaseEventService caseEventService) {
		this.caseEventService = caseEventService;
	}

	public CaseEventService getCaseEventService() {
		return this.caseEventService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseEventId = WebUtil.getParameterInteger(request,
					"caseEventId");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = caseEventId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECASEEVENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECASEEVENT access");
				return errorResult;
				
			}
			
			CaseEvent res = caseEventService.delete(pkey, user);
			String alert = "";

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.caseevent", null, "success", Locale
								.getDefault()));
				alert = alertProperties.getMessage(
						"success.delete.caseevent", null, "success", Locale
						.getDefault());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.caseevent", null, "fail", Locale
								.getDefault()));
				
				alert = alertProperties.getMessage(
						"fail.delete.caseevent", null, "fail", Locale
						.getDefault());

			}
			result = new ModelAndView(new RedirectView("caseevent?navigation=list&caseId="+caseId+"&alert="+alert));
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
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILCASEEVENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILCASEEVENT access");
				return errorResult;
				
			}
			
			Integer caseEventId = WebUtil.getParameterInteger(request,
					"caseEventId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");
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
			java.io.Serializable pkey = caseEventId;
			CaseEvent object = caseEventService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.caseevent", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("caseEvent", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("rowset", rowset);
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
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCASEEVENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCASEEVENT access");
				return errorResult;
				
			}
			Integer countSet = 30;
			
			result = new ModelAndView(location);

			Integer rowsetint = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Integer caseId = WebUtil.getParameterInteger(request,"caseId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			result.addObject("subnavigation",subnavigation);
			
			Case theCase = caseService.get(caseId);
			
			
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			
			int count = 0;

			
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("list")) {

				if (searchby != null && !searchtext.equals("") && searchtext!=null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("therapy")) {
						vLikeP.add("therapy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("vitalSign")) {
						vLikeP.add("vitalSign");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					
					if (searchby.equalsIgnoreCase("doctorConsult")) {
						vLikeP.add("doctorConsult");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("nurse")) {
						vLikeP.add("nurse");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("monitoredBy")) {
						vLikeP.add("monitoredBy");
						vLikeQ.add(searchtext);
					}
					//Add 25022015 by User Request
					if(searchby.equalsIgnoreCase("category")){
						vLikeP.add("eventCategoryId.eventCategoryName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("diagnostic")){
						vLikeP.add("diagnosticTesting");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("procedure")){
						vLikeP.add("procedurePlan");
						vLikeQ.add(searchtext);
					}
				}

			}
			
			
			if (navigation.equalsIgnoreCase("list") || navigation.equalsIgnoreCase("gosearch")){
				vEqP.add("caseId.caseId");
				vEqQ.add(caseId);
				
			}
			result.addObject("caseId",caseId);
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

			count = caseEventService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			"CaseEvent.CaseId",
			// foreign affairs end
			};
			collection = caseEventService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = caseEventService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			if (theCase != null){
				result.addObject("theCase", theCase);
			}
			
			Case caseObject = null;
			
			if(caseId != null){
				try
				{
				java.io.Serializable casepkey = caseId;
				System.out.println("case Id : "+caseId);
				//modified by aju on 20150423, add link Case->CaseStatus
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

			result.addObject("CaseEvents", collection);
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
	public ModelAndView downloadPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHCASEEVENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHCASEEVENT access");
				return errorResult;
				
			}
			
			result = new ModelAndView(location);

			Integer rowsetint = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Integer caseId = WebUtil.getParameterInteger(request,"caseId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			result.addObject("subnavigation",subnavigation);
			
			//Case theCase = caseSe
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			
			int count = 0;

			
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			
			
			
			vEqP.add("caseId.caseId");
			vEqQ.add(caseId);
			
			
			result.addObject("caseId",caseId);
			
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

			count = caseEventService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			
			collection = caseEventService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					null, 0, countSet.intValue());
			result.addObject("CaseEvents", collection);
			
			HSSFWorkbook workbook = CaseReportDownloader.downloadCaseMonitoring(collection);

			
            String filename = "case-monitoring-report-"+caseId+".xls";
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + filename);

            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            ServletOutputStream sos = response.getOutputStream();

            // response.setHeader("Content-length", Integer
            // .toString(workbook.getBytes().length));

            workbook.write(sos);
            // sos.write(workbook.getBytes());
            sos.close();
            
            result = new ModelAndView(new RedirectView("case?navigation=detail&caseId="+caseId));

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


		String caseId = request.getParameter("caseId");
		String breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a>";
		try {
			if (navigation.equalsIgnoreCase("detail")) {				
				result = detailPerformed(request, response, "detailCaseEvent");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;<a href=\"caseevent?navigation=list&caseId="+caseId+"\" class=\"linkbreadcrumb\">List Case Monitoring</a>&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Detail Case Monitoring";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchCaseEvent");

				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Case Monitoring";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupCaseEvent");
			} else if (navigation.equalsIgnoreCase("list") || subnavigation.equalsIgnoreCase("list")) {
				result = searchPerformed(request, response, "searchCaseEvent");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Case Event";
			} 
			else if (navigation.equalsIgnoreCase("downloadmonitor") ) {
				result = downloadPerformed(request, response, "searchCaseEvent");
			} 
			else {
				result = searchPerformed(request, response, "searchCaseEvent");
				breadcrumb += "&nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Case Monitoring";
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
