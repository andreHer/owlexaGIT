package com.ametis.cms.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import com.ametis.cms.util.*;
import com.ametis.cms.util.servlet.TableRenderingServlet;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.context.support.ResourceBundleMessageSource; /*
 import org.acegisecurity.Authentication;
 import org.acegisecurity.context.SecurityContextHolder;
 import org.acegisecurity.userdetails.UserDetails;
 */
import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

// imports+

// imports-

/**
 * ClaimReceiving is a servlet controller for claim_receiving Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class ClaimReceivingController implements Controller

// extends+

// extends-
{

	private ClaimReceivingService claimReceivingService;
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

	public void setClaimReceivingService(
			ClaimReceivingService claimReceivingService) {
		this.claimReceivingService = claimReceivingService;
	}

	public ClaimReceivingService getClaimReceivingService() {
		return this.claimReceivingService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer claimReceivingId = WebUtil.getParameterInteger(request,
					"claimReceivingId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = claimReceivingId;

			ActionUser user = securityService.getActionUser(request);

			ClaimReceiving res = claimReceivingService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.claimreceiving", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.claimreceiving", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchClaimReceiving");
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
			Integer claimReceivingId = WebUtil.getParameterInteger(request,
					"claimReceivingId");

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
			java.io.Serializable pkey = claimReceivingId;
			ClaimReceiving object = claimReceivingService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.claimreceiving", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("claimReceiving", object);
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
			

			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortReceivingNo = true, sortReceivingTime = true, sortTotalDocument = true, sortClient = true,
					sortProvider = true, sortCourier = true, sortOfficer = true, sortModifiedTime = true;
			boolean order = true;
			String[] orderParam;

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			boolean isDownloadReport = navigation.equals("downloadReport");
			
			//CHECKING SORTING COLUMN
			//Add 20150507 by Feiby Veronika, for sorting function
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| isDownloadReport ||  location.equals("claimreceiving") ||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {

				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("receivingNumber")) {
						vEqP.add("receivingNumber");
						vEqQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("courier")) {
						vLikeP.add("courier");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientId")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerId")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("modifiedBy")) {
						vLikeP.add("modifiedBy");
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


			//String[] betweenBy = null;
			//Object[] minDate = null;
			//Object[] maxDate = null;
			
				
			if (minimumDate != null && maximumDate != null && !maximumDate.toString().equalsIgnoreCase("1970-01-01")){
				
//				betweenBy = new String[1];
//				betweenBy[0] = "receiveDate";
//				minDate = new Date[1];
//				minDate[0] = minimumDate;
//				
//				maxDate = new Date[1];
//				maxDate[0] = maximumDate;
//				
//				
//				minDate = new Timestamp[1];
//				maxDate = new Timestamp[1];
//				
//				minDate[0] = new java.sql.Timestamp(minimumDate.getTime());
//				maxDate[0] = new java.sql.Timestamp(maximumDate.getTime());
				
				String[] betweenColumn = { "receiveDate" };
				Object[] minColumn = { minimumDate };
				Object[] maxColumn = { maximumDate };
				count = claimReceivingService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
				System.out.println("COUNT : "+count);
			}else{
				count = claimReceivingService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}
			
			
			

			//String arah = WebUtil.getParameterString(request, "arah", "");

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
					"ClaimReceiving.ClientId", "ClaimReceiving.ProviderId",
					"ClaimReceiving.MemberGroupId",
			// foreign affairs end
			};
			
			//SORTING START
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				orderParam = new String[1];
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("receivingnumber")){
						sortByColumn = "receivingNumber";
						Boolean sortOrderReceivingNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderReceivingNo", ""));
						sortReceivingNo = !sortOrderReceivingNo;
						order = sortReceivingNo;
					}else if(sortcolumn.equalsIgnoreCase("receivingtime")){
						sortByColumn = "receiveDate";
						Boolean sortOrderReceivingTime = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderReceivingTime", ""));
						sortReceivingTime = !sortOrderReceivingTime;
						order = sortReceivingTime;
					}else if(sortcolumn.equalsIgnoreCase("totaldocument")){
						sortByColumn = "totalReceiving";
						Boolean sortOrderTotalDocument = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderTotalDocument", ""));
						sortTotalDocument = !sortOrderTotalDocument;
						order = sortTotalDocument;
					}else if(sortcolumn.equalsIgnoreCase("client")){
						sortByColumn = "clientId.clientName";
						Boolean sortOrderClient = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderClient", ""));
						sortClient = !sortOrderClient;
						order = sortClient;
					}else if(sortcolumn.equalsIgnoreCase("provider")){
						sortByColumn = "providerId.providerName";
						Boolean sortOrderProvider = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderProvider", ""));
						sortProvider = !sortOrderProvider;
						order = sortProvider;
					}else if(sortcolumn.equalsIgnoreCase("courier")){
						sortByColumn = "courier";
						Boolean sortOrderCourier = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderCourier", ""));
						sortCourier = !sortOrderCourier;
						order = sortCourier;
					}else if(sortcolumn.equalsIgnoreCase("officer")){
						orderParam = new String[2];
						sortByColumn = "modifiedBy";
						orderParam[1] = "createdBy";
						Boolean sortOrderOfficer = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderOfficer", ""));
						sortOfficer = !sortOrderOfficer;
						order = sortOfficer;
					}else{
						sortByColumn = "modifiedTime";
						Boolean sortOrderModifiedTime = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderModifiedTime", ""));
						sortModifiedTime = !sortOrderModifiedTime;
						order = sortModifiedTime;
					}
					orderParam[0] = sortByColumn;
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("receivingnumber")){
						sortReceivingNo = order;
					}else if(sortcolumn.equalsIgnoreCase("receivingtime")){
						sortReceivingTime = order;
					}else if(sortcolumn.equalsIgnoreCase("totaldocument")){
						sortTotalDocument = order;
					}else if(sortcolumn.equalsIgnoreCase("client")){
						sortClient = order;
					}else if(sortcolumn.equalsIgnoreCase("provider")){
						sortProvider = order;
					}else if(sortcolumn.equalsIgnoreCase("courier")){
						sortCourier = order;
					}else if(sortcolumn.equalsIgnoreCase("officer")){
						orderParam = new String[2];
						orderParam[1] = "createdBy";
						sortOfficer = order;
					}else{
						sortModifiedTime = order;
					}
					orderParam[0] = sortByColumn;
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if(minimumDate!=null && maximumDate!=null){
					String[] betweenColumn = { "receiveDate" };
					Object[] minColumn = { minimumDate };
					Object[] maxColumn = { maximumDate };
					collection = claimReceivingService.search(sLikeP, sLikeQ, sEqP,
							sEqQ,betweenColumn,minColumn,maxColumn, order, orderParam, required,
							isDownloadReport ? 0 : rowsetint,
							isDownloadReport ? count : countSet.intValue());
				}else{
					collection = claimReceivingService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, order, orderParam, required,
							isDownloadReport ? 0 : rowsetint,
							isDownloadReport ? count : countSet.intValue());
				}
			}else{
				if(minimumDate!=null && maximumDate!=null){
					String[] betweenColumn = { "receiveDate" };
					Object[] minColumn = { minimumDate };
					Object[] maxColumn = { maximumDate };
					collection = claimReceivingService.search(sLikeP, sLikeQ, sEqP,
							sEqQ,betweenColumn,minColumn,maxColumn, required,
							isDownloadReport ? 0 : rowsetint,
							isDownloadReport ? count : countSet.intValue());
				}else{
					collection = claimReceivingService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required,
							isDownloadReport ? 0 : rowsetint,
							isDownloadReport ? count : countSet.intValue());
				}
			}
			

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
				if (minimumDate != null
						&& !minimumDate.toString().equalsIgnoreCase("1970-01-01")
						&& maximumDate != null
						&& !maximumDate.toString().equalsIgnoreCase("1970-01-01")) {
					String[] betweenColumn = { "receiveDate" };
					Object[] minColumn = { minimumDate };
					Object[] maxColumn = { maximumDate };
					collection = claimReceivingService.search(sLikeP, sLikeQ, sEqP,
							sEqQ,betweenColumn,minColumn,maxColumn, required,
							isDownloadReport ? 0 : rowsetint,
							isDownloadReport ? count : countSet.intValue());
				}else{
					collection = claimReceivingService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required,
							isDownloadReport ? 0 : rowsetint,
							isDownloadReport ? count : countSet.intValue());
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			String minDate = "";
			String maxDate = "";

			if (minimumDate != null
					&& !minimumDate.toString().equalsIgnoreCase("1970-01-01")) {
				minDate = minimumDate.toString();
			}
			if (maximumDate != null
					&& !maximumDate.toString().equalsIgnoreCase("1970-01-01")) {
				maxDate = maximumDate.toString();
			}
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			if(isDownloadReport) {
				HSSFWorkbook workbook = ClaimReceivingReportGenerator.generateReport(collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "claimReceivingReport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}

			result.addObject("ClaimReceivings", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			
			request.setAttribute("minimum_date", minDate);
			request.setAttribute("maximum_date", maxDate);
			
			request.setAttribute("sortClient", sortClient);
			request.setAttribute("sortCourier", sortCourier);
			request.setAttribute("sortModifiedTime", sortModifiedTime);
			request.setAttribute("sortOfficer", sortOfficer);
			request.setAttribute("sortProvider", sortProvider);
			request.setAttribute("sortReceivingNo", sortReceivingNo);
			request.setAttribute("sortReceivingTime", sortReceivingTime);
			request.setAttribute("sortTotalDocument", sortTotalDocument);

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
						"detailClaimReceiving");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchClaimReceiving");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupClaimReceiving");
			} else {
				result = searchPerformed(request, response,
						"searchClaimReceiving");
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
