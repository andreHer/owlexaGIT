package com.ametis.cms.web.controller;

import java.io.File;
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
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.RejectedCaseService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.CaseReportDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * RejectedCase is a servlet controller for rejected_case Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class RejectedCaseController implements Controller

// extends+

// extends-
{

	private RejectedCaseService rejectedCaseService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	SecurityService securityService;

	private ActivityLogService logService;
	private ConfigurationService configurationService;

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

	public void setRejectedCaseService(RejectedCaseService rejectedCaseService) {
		this.rejectedCaseService = rejectedCaseService;
	}

	public RejectedCaseService getRejectedCaseService() {
		return this.rejectedCaseService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long rejectedCaseId = WebUtil.getParameterLong(request,
					"rejectedCaseId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = rejectedCaseId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEREJECTEDCASE");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEREJECTEDCASE access");
				return errorResult;

			}
			RejectedCase res = rejectedCaseService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.rejectedcase", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.rejectedcase", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchRejectedCase");
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
			Long rejectedCaseId = WebUtil.getParameterLong(request,
					"rejectedCaseId");
			if (rejectedCaseId == null) {
				rejectedCaseId = WebUtil.getAttributeLong(request,
						"rejectedCaseId", 0);
			}

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
			
			String[] required = { "RejectedCase.CaseId",
					"RejectedCase.CaseId.Diagnosis1Id",
					"RejectedCase.CaseId.MemberId",
					"RejectedCase.CaseId.MemberId.ParentMember",
					"RejectedCase.CaseId.MemberId.MemberGroupId.ClientId",
					"RejectedCase.CaseId.MemberId.ClientId" };
			result = new ModelAndView(location);

			java.io.Serializable pkey = rejectedCaseId;
			RejectedCase object = rejectedCaseService.get(pkey, required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.rejectedcase", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("member", object.getCaseId().getMemberId());
			result.addObject("rejectedCase", object);
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
			Long rejectedCaseId = WebUtil.getParameterLong(request,
					"rejectedCaseId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String[] required = { "RejectedCase.CaseId",
					"RejectedCase.CaseId.MemberId",
					"RejectedCase.CaseId.MemberId.MemberGroupId.ClientId",
					"RejectedCase.CaseId.MemberId.ClientId" };

			result = new ModelAndView(location);
			java.io.Serializable pkey = rejectedCaseId;
			RejectedCase object = rejectedCaseService.get(pkey, required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.rejectedcase", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("rejectedCase", object);
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
					|| navigation.equalsIgnoreCase("gosearchissued")) {

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

			count = rejectedCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String required[] = new String[] {
					// foreign affairs
					"Case.CaseStatusId", "Case.MemberId", "Case.Diagnosis1Id",
					"Case.Diagnosis2Id", "Case.CaseCategoryId",
			// foreign affairs end
			};
			collection = rejectedCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					false, "rejectedCaseId", required, rowsetint, count);

			if (navigation.equalsIgnoreCase("downloadreject")) {
				HSSFWorkbook workbook = CaseReportDownloader
						.downloadRejectedCaseReport(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "case-rejection-report.xls");

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

					if (searchby.equalsIgnoreCase("rejectionDescription")) {
						vLikeP.add("rejectionDescription");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("createdBy")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("modifiedBy")) {
						vLikeP.add("modifiedBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("deletedBy")) {
						vLikeP.add("deletedBy");
						vLikeQ.add(searchtext);
					}
				}

			}

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

			count = rejectedCaseService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			"RejectedCase.CaseId",
			// foreign affairs end
			};
			collection = rejectedCaseService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = rejectedCaseService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("RejectedCases", collection);

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
		String breadcrumb="";
		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			RejectedCase rejectedCase = rejectedCaseService
					.getCaseRejection(caseId);
			Configuration configuration = null;

			if (rejectedCase != null) {
				/*remove by aju on 20150331, kenapa cuman disini yang pake getClientConfiguration?
				configuration = configurationService
						.getClientConfiguration(rejectedCase.getCaseId()
								.getMemberId().getClientId().getClientId());
				*/

				if (configuration == null) {
					configuration = configurationService
							.getSystemConfiguration();
				}
				request.setAttribute("rejectedCaseId", rejectedCase
						.getRejectedCaseId());
			}

			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailRejectedCase");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchRejectedCase");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupRejectedCase");
			} else if (navigation.equalsIgnoreCase("downloadreject")) {
				result = downloadCaseRejectedPerformed(request, response, "searchRejectedCase");
			} else if (navigation.equalsIgnoreCase("print")) {
				String location = "printRejectionLetter";
				if (configuration != null
						&& configuration.getCompanyCode() != null) {
					String code = configuration.getCompanyCode().toLowerCase();
					System.out.println(code);
					location = location + "_" + code;
				}
				System.out.println(location);
				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/rejectedCase/");
				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printRejectionLetter";
				}

				result = detailPerformed(request, response, location);
			} else {
				result = searchPerformed(request, response,
						"searchRejectedCase");
				breadcrumb = "<a href=\"rejectedcase?navigation=searchissued?navigation=searchissued\">Search Rejected Case</a>";
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
