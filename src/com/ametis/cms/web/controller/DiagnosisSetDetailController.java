package com.ametis.cms.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import com.ametis.cms.util.*;
import com.ametis.cms.util.servlet.TableRenderingServlet;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.context.support.ResourceBundleMessageSource; /*
 import org.acegisecurity.Authentication;
 import org.acegisecurity.context.SecurityContextHolder;
 import org.acegisecurity.userdetails.UserDetails;
 */
import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.*;
import java.util.*;

// imports+

// imports-

/**
 * DiagnosisSetDetail is a servlet controller for diagnosis_set_detail Table.
 * All you have to do is to convert necessary data field to the named parameter
 */
public class DiagnosisSetDetailController implements Controller

// extends+

// extends-
{

	private DiagnosisSetDetailService diagnosisSetDetailService;
	private SecurityService securityService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public UserService getUserService() {
		return actionuserService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
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

	public void setDiagnosisSetDetailService(
			DiagnosisSetDetailService diagnosisSetDetailService) {
		this.diagnosisSetDetailService = diagnosisSetDetailService;
	}

	public DiagnosisSetDetailService getDiagnosisSetDetailService() {
		return this.diagnosisSetDetailService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer diagnosisSetDetailId = WebUtil.getParameterInteger(request,
					"diagnosisSetDetailId");

			java.io.Serializable pkey = diagnosisSetDetailId;

			ActionUser user = securityService.getActionUser(request);

			DiagnosisSetDetail res = diagnosisSetDetailService.delete(pkey,
					user);

			String alert = "";
			if (res != null) {
				alert = alertProperties.getMessage(
						"success.delete.diagnosissetdetail", null, "success",
						Locale.getDefault());
			} else {
				alert = alertProperties.getMessage(
						"fail.delete.diagnosissetdetail", null, "fail", Locale
						.getDefault());

			}
			result = new ModelAndView(new RedirectView("diagnosisset?navigation=detail&alert="+alert+"&diagnosisSetId="+res.getDiagnosisSetId().getDiagnosisSetId()));
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
			Integer diagnosisSetDetailId = WebUtil.getParameterInteger(request,
					"diagnosisSetDetailId");

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
			java.io.Serializable pkey = diagnosisSetDetailId;
			DiagnosisSetDetail object = diagnosisSetDetailService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.diagnosissetdetail", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("diagnosisSetDetail", object);
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

	public ModelAndView addBulkPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			Integer diagnosisSetId = WebUtil.getParameterInteger(request,
					"diagnosisSetId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user = securityService.getActionUser(request);

			if (navigation.equalsIgnoreCase("addbulk")) {

				Vector<String> medicineList = new Vector<String>();
				Vector<String> medicineIdList = new Vector<String>();
				Vector<String> descList = new Vector<String>();

				for (int i = 0; i < 10; i++) {

					medicineList.add("");
					medicineIdList.add("");
					descList.add("");

				}
				result = new ModelAndView("addBulkDiagnosis");

				result.addObject("nameVector", medicineList);
				result.addObject("medIdVector", medicineIdList);
				result.addObject("codeVector", descList);

			} else if (navigation.equalsIgnoreCase("savebulk")) {

				String[] diagnosisId = request
						.getParameterValues("diagnosisId");
				String[] nameList = request.getParameterValues("diagnosisName");
				String[] codeList = request.getParameterValues("diagnosisCode");

				Collection<DiagnosisSetDetail> diagnosisList = new Vector<DiagnosisSetDetail>();

				for (int i = 0; i < diagnosisId.length; i++) {
					if (diagnosisId[i] != null
							&& !diagnosisId[i].trim().equalsIgnoreCase("")) {

						DiagnosisSetDetail diagnosis = new DiagnosisSetDetail();
						Diagnosis diag = new Diagnosis();
						diag.setDiagnosisId(Integer.valueOf(diagnosisId[i]));

						diagnosis.setDiagnosisId(diag);

						if (nameList != null && nameList.length > 0) {
							diagnosis.setDiagnosisName(nameList[i]);
						}
						if (codeList != null && codeList.length > 0) {
							diagnosis.setDiagnosisCode(codeList[i]);
						}

						diagnosisList.add(diagnosis);
					}
				}
				boolean res = diagnosisSetDetailService.createDetails(
						diagnosisSetId, diagnosisList, user);

				String alert = "";

				if (res) {
					alert = "Success Add Diagnosis";
				} else {
					alert = "Failed Add Diagnosis";
				}
				result = new ModelAndView(new RedirectView(
						"diagnosisset?navigation=detail&diagnosisSetId="
								+ diagnosisSetId + "&alert=" + alert));
			}
			result.addObject("diagnosisSetId", diagnosisSetId);

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

					if (searchby.equalsIgnoreCase("diagnosisCode")) {
						vLikeP.add("diagnosisCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("diagnosisName")) {
						vLikeP.add("diagnosisName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
				
				}

			}

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));
			
			if (diagnosisSetId != null){
				vEqP.add("diagnosisSetId.diagnosisSetId");
				vEqQ.add(diagnosisSetId);
				
				request.setAttribute("diagnosisSetId", diagnosisSetId);
			}

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = diagnosisSetDetailService.getTotal(sLikeP, sLikeQ, sEqP,
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
					"DiagnosisSetDetail.DiagnosisId",
					"DiagnosisSetDetail.DiagnosisSetId",
			// foreign affairs end
			};
			collection = diagnosisSetDetailService.search(sLikeP, sLikeQ, sEqP,
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
				collection = diagnosisSetDetailService.search(sLikeP, sLikeQ,
						sEqP, sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("DiagnosisSetDetails", collection);
			

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
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailDiagnosisSetDetail");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("addbulk")
					|| navigation.equalsIgnoreCase("savebulk")) {
				result = addBulkPerformed(request, response, "addBulkDiagnosis");
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchDiagnosisSetDetail");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupDiagnosisSetDetail");
			} else {
				result = searchPerformed(request, response,
						"searchDiagnosisSetDetail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-
}
