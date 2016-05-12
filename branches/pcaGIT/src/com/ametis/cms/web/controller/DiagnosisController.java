package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.DiagnosisDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Diagnosis is a servlet controller for diagnosis Table. All you have to do is
 * to convert necessary data field to the named parameter
 */
public class DiagnosisController implements Controller

// extends+

// extends-

{

	private DiagnosisService diagnosisService;

	private UserService userService;

	private MemberService memberService;

	private ClientService clientService;
	private MemberGroupService memberGroupService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	private SecurityService securityService;
	private ActivityLogService logService;



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

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public DiagnosisService getDiagnosisService() {
		return this.diagnosisService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer diagnosisId = WebUtil.getParameterInteger(request,
					"diagnosisId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = diagnosisId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEDIAGNOSIS");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEDIAGNOSIS access");
				return errorResult;

			}
			Diagnosis res = diagnosisService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.diagnosis", null, "success", Locale
						.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.diagnosis", null, "fail", Locale
						.getDefault()));

			}
			result = searchPerformed(request, response, "searchDiagnosis");
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
			Integer diagnosisId = WebUtil.getParameterInteger(request,
					"diagnosisId");

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
			java.io.Serializable pkey = diagnosisId;
			Diagnosis object = diagnosisService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.diagnosis", null, "fail", Locale
						.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("diagnosis", object);
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

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,	"searchtext", "");
			//			String searchby = WebUtil.getParameterString(request, "searchby","");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			String parentElem = WebUtil.getParameterString(request, "parentElem","");
			Integer diagnosisSetId = WebUtil.getParameterInteger(request, "diagnosisSetId");

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

			boolean sortDiagnosisDesc = true, sortDiagnosisCode = true, sortEDCCode = true, sortDiagnosisName = true;
			boolean order = true;

			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}

			if (navigation.equals("gosearch") || navigation.equals("golookup") || 
					navigation.equals("gosearchbysort") || navigation.equals("golookupmm") ||  (navigation.equals("") && !arah.isEmpty())) {

				/*if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisCode")) {
						vLikeP.add("diagnosisCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosisName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisedc")) { //add by adq, kondisi searchby diagnosisedc 24082015, nomer tiket 391
						vLikeP.add("alternateCode");
						vLikeQ.add(searchtext);
					}					
				}*/
				vLikeP.add("description");
				vLikeQ.add(searchtext);
				vLikeP.add("diagnosisCode");
				vLikeQ.add(searchtext);
				vLikeP.add("diagnosisName");
				vLikeQ.add(searchtext);
				vLikeP.add("alternateCode");
				vLikeQ.add(searchtext);

			}

			if (diagnosisSetId != null){
				vEqP.add("diagnosisSetId.diagnosisSetId");
				vEqQ.add(diagnosisSetId);
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

			count = diagnosisService.getTotalOr(sLikeP, sLikeQ, sEqP, sEqQ);
			//count = diagnosisService.getTotalDiagnosis(searchtext);


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
					// foreign affairs end
			};

			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("diagnosisdesc")){
						sortByColumn = "description";
						Boolean sortByOrderDiagnosisDesc = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderDiagnosisDesc", ""));
						sortDiagnosisDesc = !sortByOrderDiagnosisDesc;
						order = sortDiagnosisDesc;
					}else if(sortcolumn.equalsIgnoreCase("diagnosiscode")){
						sortByColumn = "diagnosisCode";
						Boolean sortByOrderDiagnosisCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderDiagnosisCode", ""));
						sortDiagnosisCode = !sortByOrderDiagnosisCode;
						order = sortDiagnosisCode;
					}else if(sortcolumn.equalsIgnoreCase("diagnosisedc")){
						sortByColumn = "alternateCode";
						Boolean sortByOrderEDC = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderEDC", ""));
						sortEDCCode = !sortByOrderEDC;
						order = sortEDCCode; 
					}else{
						sortByColumn = "diagnosisName";
						Boolean sortByOrderDiagnosisName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderDiagnosisName", ""));
						sortDiagnosisName = !sortByOrderDiagnosisName;
						order = sortDiagnosisName;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("diagnosisdesc")){
						sortDiagnosisDesc = order;
					}else if(sortcolumn.equalsIgnoreCase("diagnosiscode")){
						sortDiagnosisCode = order;
					}else if(sortcolumn.equalsIgnoreCase("diagnosisedc")){
						sortEDCCode = order;
					}else{
						sortDiagnosisName = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = diagnosisService.searchOr(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
						required, rowsetint, countSet.intValue());
			}else{
				collection = diagnosisService.searchOr(sLikeP, sLikeQ, sEqP, sEqQ, required, rowsetint, countSet.intValue());
				System.out.println("taging 2");
			}



			//collection = diagnosisService.searchDiagnosis(searchtext, rowsetint, countSet.intValue());
			//collection = diagnosisService.searchDiagnosis(searchtext, index-1, countSet.intValue());
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

				collection = diagnosisService.searchOr(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());


				//collection = diagnosisService.searchDiagnosis(searchtext, rowsetint, countSet.intValue());
				//collection = diagnosisService.searchDiagnosis(searchtext, index-1, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			//			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("parentElem", parentElem);
			request.setAttribute("sortcolumn", sortcolumn);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Diagnosiss", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortDiagnosisCode", sortDiagnosisCode);
			request.setAttribute("sortDiagnosisDesc", sortDiagnosisDesc);
			request.setAttribute("sortDiagnosisName", sortDiagnosisName);
			request.setAttribute("sortEDCCode", sortEDCCode);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
					.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView lookupJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String q = WebUtil.getParameterString(request, "q", "");

			Collection<Diagnosis> diagnosis = diagnosisService.searchDiagnosis(q, 0, 10);

			result.addObject("Diagnosiss", diagnosis);


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
		try {System.out.println("NAV : "+navigation);
		if (navigation.equalsIgnoreCase("detail")) {
			/*
			 * disesuaikan dengan halaman targetnya nih
			 */
			result = detailPerformed(request, response, "detailDiagnosis");
		} else if (navigation.equalsIgnoreCase("delete")) {
			result = deletePerformed(request, response);
		} else if (navigation.equalsIgnoreCase("search")
				|| navigation.equals("gosearch")) {
			result = searchPerformed(request, response, "searchDiagnosis");
		} else if (navigation.equalsIgnoreCase("lookup")
				|| navigation.equals("golookup")) {
			result = searchPerformed(request, response, "lookupDiagnosis");
		}
		else if (navigation.equalsIgnoreCase("lookupsmm")
				|| navigation.equals("golookupsmm")) {
			result = searchPerformed(request, response, "lookupDiagnosisSMM");
		}
		else if (navigation.equalsIgnoreCase("lookupjson")){
			System.out.println("Lookup json diagnosis");
			result = lookupJsonPerformed(request, response, "lookupDiagnosisJson");
		} else if (navigation.equalsIgnoreCase("potentialhealthreport")||navigation.equalsIgnoreCase("gosearchhealthreport")) {
			result = searchPotentialHealthReportPerformed(request, response, "potentialHealthProblemReport");
		}else if(navigation.equalsIgnoreCase("printouthealthreport")){
			result = generatePrintoutReport(request, response, "potentialHealthProblemReport");
			breadcrumb = "<a href=\"diagnosis?navigation=potentialhealthreport\" class=\"linkbreadcrumb\">Search Potential Health Problem</a>";
		}
		else {
			result = searchPerformed(request, response, "searchDiagnosis");
			breadcrumb = "<a href=\"diagnosis\">Search Diagnosis</a>";
			result.addObject("breadcrumb", breadcrumb);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	public ModelAndView searchPotentialHealthReportPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			Integer diagnosisSetId = WebUtil.getParameterInteger(request, "diagnosisSetId");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String clientText = WebUtil.getParameterString(request, "clientText", "");
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			String memberGroupText = WebUtil.getParameterString(request, "memberGroupText", "");

			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			int memberSize = 0;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}

			String arah = WebUtil.getParameterString(request, "arah", "");

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")
					&& maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {
				if(clientId != null && memberGroupId!=null){
					String pColumn[] = {"deletedStatus", "clientId.clientId", "status", "memberGroupId.memberGroupId"};
					Object pValue[] = {Integer.valueOf(0), clientId, Integer.valueOf(1), memberGroupId};
					collection = diagnosisService.generatePotentialHealthProblemReport(clientId, memberGroupId, minDate, maxDate);
					memberSize = memberService.getTotal(null, null, pColumn, pValue);
					System.out.println("MEMBER SIZE : "+memberSize);
				}else if(clientId!=null){
					String pColumn[] = {"deletedStatus", "clientId.clientId", "status"};
					Object pValue[] = {Integer.valueOf(0), clientId, Integer.valueOf(1)};
					collection = diagnosisService.generatePotentialHealthProblemReport(clientId, minDate, maxDate);
					memberSize = memberService.getTotal(null, null, pColumn, pValue);
				}else{
					collection = diagnosisService.generatePotentialHealthProblemReport(0, minDate, maxDate);
				}
			}else{
				collection = diagnosisService.generatePotentialHealthProblemReport(0, minDate, maxDate);
			}

			count = collection.size();
			//count = diagnosisService.getTotalDiagnosis(searchtext);


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
					// foreign affairs end
			};

			//			if(clientId!=null){
			//				collection = diagnosisService.generatePotentialHealthProblemReport(clientId);
			//			}else{
			//				collection = diagnosisService.generatePotentialHealthProblemReport(0);
			//			}
			//System.out.println("COLLECTION : "+collection.size());




			//collection = diagnosisService.searchDiagnosis(searchtext, rowsetint, countSet.intValue());
			//collection = diagnosisService.searchDiagnosis(searchtext, index-1, countSet.intValue());
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

				//if(clientId!=null){
				//	collection = diagnosisService.generatePotentialHealthProblemReport(clientId, minDate, maxDate);
				//}


				//collection = diagnosisService.searchDiagnosis(searchtext, rowsetint, countSet.intValue());
				//collection = diagnosisService.searchDiagnosis(searchtext, index-1, countSet.intValue());
			}

			String minimumDate = "";
			String maximumDate = "";

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")) {
				minimumDate = minDate.toString();
			}
			if (maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {
				maximumDate = maxDate.toString();
			}

			request.setAttribute("navigation", navigation);

			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("potentialHealths", collection);
			result.addObject("clientId", clientId);
			result.addObject("clientText", clientText);
			result.addObject("memberGroupId", memberGroupId);
			result.addObject("memberGroupText", memberGroupText);
			result.addObject("memberSize", memberSize);

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

	private ModelAndView generatePrintoutReport(HttpServletRequest request,
			HttpServletResponse response, String location) {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHCLAIM access");
				return errorResult;

			}

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			Integer diagnosisSetId = WebUtil.getParameterInteger(request, "diagnosisSetId");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			String clientText = WebUtil.getParameterString(request, "clientText", "");
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			String memberGroupText = WebUtil.getParameterString(request, "memberGroupText", "");

			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

			Collection collection = null;
			int memberSize = 0;

			Client client = null;
			MemberGroup memberGroup = null;

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")
					&& maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {
				if(clientId != null && memberGroupId!=null){
					try{
						java.io.Serializable clientKey = clientId;
						java.io.Serializable memberGroupKey = memberGroupId;

						client = clientService.get(clientKey);
						memberGroup = memberGroupService.get(memberGroupKey);
					}catch (Exception e) {
						// TODO: handle exception
					}

					String pColumn[] = {"deletedStatus", "clientId.clientId", "status", "memberGroupId.memberGroupId"};
					Object pValue[] = {Integer.valueOf(0), clientId, Integer.valueOf(1), memberGroupId};
					collection = diagnosisService.generatePotentialHealthProblemReport(clientId, memberGroupId, minDate, maxDate);
					memberSize = memberService.getTotal(null, null, pColumn, pValue);

					HSSFWorkbook workbook = DiagnosisDownloader.downloadReport(client, memberGroup, collection, minDate, maxDate, memberSize);

					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition", "inline; filename="
							+ "potentialhealthproblem.xls");

					ServletOutputStream sos = response.getOutputStream();

					workbook.write(sos);
					// sos.write(workbook.getBytes());
					sos.close();
				}else if(clientId!=null){
					try{
						java.io.Serializable clientKey = clientId;
						java.io.Serializable memberGroupKey = memberGroupId;

						client = clientService.get(clientKey);
						memberGroup = memberGroupService.get(memberGroupKey);
					}catch (Exception e) {
						// TODO: handle exception
					}
					String pColumn[] = {"deletedStatus", "clientId.clientId", "status"};
					Object pValue[] = {Integer.valueOf(0), clientId, Integer.valueOf(1)};
					collection = diagnosisService.generatePotentialHealthProblemReport(clientId, minDate, maxDate);
					memberSize = memberService.getTotal(null, null, pColumn, pValue);

					HSSFWorkbook workbook = DiagnosisDownloader.downloadReport(client, memberGroup, collection, minDate, maxDate, memberSize);

					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition", "inline; filename="
							+ "potentialhealthproblem.xls");

					ServletOutputStream sos = response.getOutputStream();

					workbook.write(sos);
					// sos.write(workbook.getBytes());
					sos.close();
				}else{
					collection = diagnosisService.generatePotentialHealthProblemReport(0, minDate, maxDate);
					memberSize = 0;

					HSSFWorkbook workbook = DiagnosisDownloader.downloadReport(client, memberGroup, collection, minDate, maxDate, memberSize);

					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition", "inline; filename="
							+ "potentialhealthproblem.xls");

					ServletOutputStream sos = response.getOutputStream();

					workbook.write(sos);
					// sos.write(workbook.getBytes());
					sos.close();
				}
			}else{
				collection = diagnosisService.generatePotentialHealthProblemReport(0, minDate, maxDate);
				memberSize = 0;

				HSSFWorkbook workbook = DiagnosisDownloader.downloadReport(client, memberGroup, collection, minDate, maxDate, memberSize);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "potentialhealthproblem.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}
		}catch (Exception e) {
			e.printStackTrace();

			// request.setAttribute("alert", alertProperties.getMessage(
			// "system.error " + e.getMessage(), null, "fail", Locale
			// .getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}


	// class+

	// class-

}
