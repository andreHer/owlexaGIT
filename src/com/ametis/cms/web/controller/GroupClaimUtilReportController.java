package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.GroupClaimUtilReport;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.GroupClaimUtilReportService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * GroupClaimUtilReport is a servlet controller for group_claim_util_report
 * Table. All you have to do is to convert necessary data field to the named
 * parameter
 */
public class GroupClaimUtilReportController implements Controller

// extends+

// extends-
{

	private GroupClaimUtilReportService groupClaimUtilReportService;

	private UserService userService;

	private SecurityService securityService;
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

	public void setGroupClaimUtilReportService(
			GroupClaimUtilReportService groupClaimUtilReportService) {
		this.groupClaimUtilReportService = groupClaimUtilReportService;
	}

	public GroupClaimUtilReportService getGroupClaimUtilReportService() {
		return this.groupClaimUtilReportService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Long id = WebUtil.getParameterLong(request, "id");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = id;

			ActionUser user = securityService.getActionUser(request);
			GroupClaimUtilReport res = groupClaimUtilReportService.delete(pkey,
					user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.groupclaimutilreport", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.groupclaimutilreport", null, "fail",
						Locale.getDefault()));

			}
			result = searchPerformed(request, response,
					"searchGroupClaimUtilReport");
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
			Long id = WebUtil.getParameterLong(request, "id");

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
			java.io.Serializable pkey = id;
			GroupClaimUtilReport object = groupClaimUtilReportService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.groupclaimutilreport", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("groupClaimUtilReport", object);
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
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Date reportDate = WebUtil.getParameterDate(request, "report_date");

			
			//if (reportDate == null){

				reportDate = groupClaimUtilReportService.getLastReportDate();
			//}
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;
			
			ActionUser user = securityService.getActionUser(request);

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

					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupPeriode")) {
						vLikeP.add("groupPeriode");
						vLikeQ.add(searchtext);
					}
					
				}

			}
			
			if (reportDate != null){
				vEqP.add("reportDate");
				vEqQ.add(reportDate);
			}
			
			

			
			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.CLIENT){
					vEqP.add("clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
					System.out.println ("CLIENT ID : " + user.getUser().getClientId().getClientId());
				}
				if (user.getUser().getUserType().intValue() == User.BROKER){
					vEqP.add("groupId.brokerId.brokerId");
					vEqQ.add(user.getUser().getBrokerId().getBrokerId());
				}
								
			}
			// vEqP.add("deletedStatus");
			// vEqQ.add(new Integer(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			

			count = groupClaimUtilReportService.getTotal(sLikeP, sLikeQ, sEqP,
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
			// foreign affairs end
			};
			collection = groupClaimUtilReportService.search(sLikeP, sLikeQ,
					sEqP, sEqQ, required, rowsetint, countSet.intValue());

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
				collection = groupClaimUtilReportService.search(sLikeP, sLikeQ,
						sEqP, sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			String minimumDate = "";
			
			
			
			if (reportDate != null && !reportDate.toString().equalsIgnoreCase("1970-01-01")){
				minimumDate = reportDate.toString();
			}
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("GroupClaimUtilReports", collection);
			request.setAttribute("report_date", minimumDate);
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
			result = new ModelAndView(location);


			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			Date reportDate = WebUtil.getParameterDate(request, "report_date");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			
			if (reportDate != null){
				vEqP.add("reportDate");
				vEqQ.add(reportDate);
			}

			// vEqP.add("deletedStatus");
			// vEqQ.add(new Integer(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = groupClaimUtilReportService.getTotal(sLikeP, sLikeQ, sEqP,
					sEqQ);
		
			collection = groupClaimUtilReportService.search(sLikeP, sLikeQ,
					sEqP, sEqQ, rowsetint, count);

			request.setAttribute("navigation", navigation);

			String minimumDate = "";
			
			
			
			if (reportDate != null && !reportDate.toString().equalsIgnoreCase("1970-01-01")){
				minimumDate = reportDate.toString();
			}
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			if (collection != null){
				String data = "";
				int idx = 1;

				data += "No,Group Name, Total Premium, Total Member, Total Claim, Nominal Claim, Total Inpatient, Nominal Inpatient" +
						",Total Outpatient, Nominal Outpatient, Total Dental, Nominal Dental, Total Maternity, Nominal Maternity, " +
						"Total Optical, Nominal Optical, Total MCU, Nominal MCU \n\n";
				
				Iterator<GroupClaimUtilReport> reportIterator = collection.iterator();
				
				while (reportIterator.hasNext()){
					GroupClaimUtilReport report = reportIterator.next();
					
					if (report!= null){
						data += idx + "," + report.getGroupName() +"," + report.getTotalMemberPremium() +"," + report.getTotalMember();
						data += "," + report.getClaimTotal() + "," + report.getClaimTotalNominal() + "," + report.getTotalInpatient();
						data += "," + report.getTotalNominalInpatient() + "," + report.getTotalOutpatient() + "," + report.getTotalNominalOutpatient();
						data += "," + report.getTotalDental() + "," + report.getTotalNominalDental() + " ," + report.getTotalMaternity() + ",";
						data += report.getTotalNominalMaternity() + "," + report.getTotalOptical() + "," + report.getTotalNominalOptical();
						data += "," + report.getTotalMcu() + "," + report.getTotalNominalMcu()+"\n";
						idx += 1;
					}
					//
				}
				
				
				System.out.println("DATA : " + data);
				
				response.setContentType("application/x-csv");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				
				response.setHeader("Content-disposition",
						"inline; filename="  
								+ "claimutil.csv");

				ServletOutputStream sos = response.getOutputStream();

				response.setHeader("Content-length", Integer
						.toString(data.length()));
				
				
				sos.write(data.getBytes());
				sos.close();
			}
			result.addObject("GroupClaimUtilReports", collection);
			request.setAttribute("report_date", minimumDate);
			request.setAttribute("countSet", countSet);
			
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
						"detailGroupClaimUtilReport");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"groupClaimUtilReport");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupGroupClaimUtilReport");
			} 
			else if (navigation.equalsIgnoreCase("export")){
				result = exportPerformed(request, response, "detailGroupClaimUtilReport");
			}
			else {
				result = searchPerformed(request, response,
						"groupClaimUtilReport");
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
