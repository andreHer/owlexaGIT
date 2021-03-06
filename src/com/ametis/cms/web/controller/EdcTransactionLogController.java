package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
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

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.EdcTransactionLog;
import com.ametis.cms.dto.EDCTransactionLogDto;
import com.ametis.cms.service.EdcTransactionLogService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.TimeUtils;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;
import com.ametis.cms.webservice.EDCTransactionLogWebServiceImpl;

// imports+

// imports-

/**
 * EdcTransactionLog is a servlet controller for edc_transaction_log Table. All
 * you have to do is to convert necessary data field to the named parameter
 */
public class EdcTransactionLogController implements Controller

// extends+

// extends-
{

	private EdcTransactionLogService edcTransactionLogService;
	private SecurityService securityService;

	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
//	private EDCTransactionLogWebServiceImpl edcLogWebService;

	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public UserService getActionUserService() {
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

	public void setEdcTransactionLogService(
			EdcTransactionLogService edcTransactionLogService) {
		this.edcTransactionLogService = edcTransactionLogService;
	}

	public EdcTransactionLogService getEdcTransactionLogService() {
		return this.edcTransactionLogService;
	}

//	public EDCTransactionLogWebServiceImpl getEdcLogWebService() {
//		return edcLogWebService;
//	}
//
//	public void setEdcLogWebService(
//			EDCTransactionLogWebServiceImpl edcLogWebService) {
//		this.edcLogWebService = edcLogWebService;
//	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer id = WebUtil.getParameterInteger(request, "id");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = id;

			ActionUser user = securityService.getActionUser(request);

			EdcTransactionLog res = edcTransactionLogService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.edctransactionlog", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.edctransactionlog", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response,
					"searchEdcTransactionLog");
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
			Integer id = WebUtil.getParameterInteger(request, "id");

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
			EdcTransactionLog object = edcTransactionLogService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.edctransactionlog", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("edcTransactionLog", object);
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
			
			Integer refreshTimerSet = WebUtil.getParameterInteger(request,
					"refreshTimerSet");
			
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			Date date = WebUtil.getParameterDate(request, "date");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;
			
			System.out.println("MINIMUM DATE : "+minimumDate);
			System.out.println("MAXIMUM DATE : "+maximumDate);
			System.out.println("DATE Graph Edc Transaction Log: "+date);

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

					if (searchby.equalsIgnoreCase("terminalCode")) {
						vLikeP.add("terminalCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("merchantCode")) {
						vLikeP.add("merchantCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("activityLog")) {
						vLikeP.add("activityLog");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("actionCode")) {
						vLikeP.add("actionCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")) {
						vLikeP.add("cardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("responseCode")) {
						vLikeP.add("responseCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberNumber")) {
						vLikeP.add("memberNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("serviceType")) {
						vLikeP.add("caseCategoryCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("traceNumber")) {
						vLikeP.add("traceNumber");
						vLikeQ.add(searchtext);
					}
				}

			}
			//else if(navigation.equalsIgnoreCase("tambahcoba")){
			//	EDCTransactionLogDto dto = new EDCTransactionLogDto();
				//dto.setActionCode("REGISTER");
				//dto.setCardNumber("1000622220000042");
				//dto.setMerchantCode("703039500350001");
				//dto.setTerminalCode("70300109");
				//dto.setActionCode("REGISTER");
				//dto.setCardNumber("1000620001111");
				//dto.setMerchantCode("703039400060001");
				//dto.setTerminalCode("70300031");
				//edcLogWebService.logActivity(dto);
			//}

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
			
			String[] searchByDate = { "activityTime" };
			Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };
			
			if (minimumDate != null && maximumDate != null ) {
				if (minimumDate.toString().equals("1970-01-01") && maximumDate.toString().equals("1970-01-01")){
					count = edcTransactionLogService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				}
				else {
					count = edcTransactionLogService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray);
				}
			} else {
				count = edcTransactionLogService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}

			System.out.println("COUNT : "+count);
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
			
			if (minimumDate != null && maximumDate != null &&
					!minimumDate.toString().equals("1970-01-01") &&
					!maximumDate.toString().equals("1970-01-01")) {
				collection = edcTransactionLogService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					searchByDate, minimumDateArray, maximumDateArray, required, rowsetint, countSet.intValue());
			} else {
				collection = edcTransactionLogService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, false,"id", required, rowsetint, countSet.intValue());
			}
			
			System.out.println("COLLECTION SIZE : "+collection.size());

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
				if (minimumDate != null && maximumDate != null &&
						!minimumDate.toString().equals("1970-01-01") &&
						!maximumDate.toString().equals("1970-01-01")) {
					collection = edcTransactionLogService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray, required, rowsetint, countSet.intValue());
				} else {
					collection = edcTransactionLogService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, false,"id", required, rowsetint, countSet.intValue());
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("refreshTimerSet", refreshTimerSet);
			request.setAttribute("status", searchStatus);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("EdcTransactionLogs", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			
			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("maximumDate", "");
			} else {
				request.setAttribute("maximumDate", maximumDate);
			}
			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("minimumDate", "");
			} else {
				request.setAttribute("minimumDate", minimumDate);
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
	
	public ModelAndView graphPerformed(HttpServletRequest request,
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
			
			Integer refreshTimerSet = WebUtil.getParameterInteger(request,
					"refreshTimerSet");
			
			String actionCodeSatu = WebUtil.getParameterString(request, "status", "");
			
			String[] actionCode = WebUtil.getParameterStringArray(request,
					"status");
			
		
			String dateBy = WebUtil.getParameterString(request, "dateBy", "");
			
			String dateString = WebUtil.getParameterString(request, "chooseDate", "");
		
			Date date = TimeUtils.parseDate(dateString);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection<Object[]> collection = null;
			
			System.out.println("DATE Graph Edc Transaction Log: "+date);

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

					if (searchby.equalsIgnoreCase("terminalCode")) {
						vLikeP.add("terminalCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("merchantCode")) {
						vLikeP.add("merchantCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("activityLog")) {
						vLikeP.add("activityLog");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("actionCode")) {
						vLikeP.add("actionCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("cardNumber")) {
						vLikeP.add("cardNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("responseCode")) {
						vLikeP.add("responseCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberNumber")) {
						vLikeP.add("memberNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("serviceType")) {
						vLikeP.add("caseCategoryCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("traceNumber")) {
						vLikeP.add("traceNumber");
						vLikeQ.add(searchtext);
					}
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
			
			String[] searchByDate = { "activityTime" };
			Object[] chooseDate = { date };
			
			if (dateBy.equals("allthetime")){
				count = edcTransactionLogService.getTotalAllTheTimeEdcTransactionLog(actionCode);
			}
			else if (dateBy.equals("daily")){
				count = edcTransactionLogService.getTotalDailyEdcTransactionLog(actionCode, date);
			}
			else if (dateBy.equals("monthly")){
				count = edcTransactionLogService.getTotalMonthlyEdcTransactionLog(actionCode, date);
			}
			else if (dateBy.equals("yearly")){
				count = edcTransactionLogService.getTotalYearlyEdcTransactionLog(actionCode, date);
			}

			System.out.println("COUNT : "+count);
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
			
			if (dateBy.equals("allthetime")){
				collection = edcTransactionLogService.searchEdcTransactionLogAllTheTime(actionCode, rowsetint, countSet.intValue());
			} 
			else if (dateBy.equals("daily")){
				collection = edcTransactionLogService.searchEdcTransactionLogDaily(actionCode, date, rowsetint, countSet.intValue());
			}
			else if (dateBy.equals("monthly")){
				collection = edcTransactionLogService.searchEdcTransactionLogMonthly(actionCode, date, rowsetint, countSet.intValue());
			}
			else if (dateBy.equals("yearly")){
				collection = edcTransactionLogService.searchEdcTransactionLogYearly(actionCode, date, rowsetint, countSet.intValue());
			}
			
			//System.out.println("COLLECTION SIZE : "+collection.size());
			if (collection != null)
			{
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
					if (dateBy.equals("allthetime")){
						collection = edcTransactionLogService.searchEdcTransactionLogAllTheTime(actionCode, rowsetint, countSet.intValue());
					}
					else if (dateBy.equals("daily")){
						collection = edcTransactionLogService.searchEdcTransactionLogDaily(actionCode, date, rowsetint, countSet.intValue());
					}
					else if (dateBy.equals("monthly")){
						collection = edcTransactionLogService.searchEdcTransactionLogMonthly(actionCode, date, rowsetint, countSet.intValue());
					}
					else if (dateBy.equals("yearly")){
						collection = edcTransactionLogService.searchEdcTransactionLogYearly(actionCode, date, rowsetint, countSet.intValue());
					}
				}
			}
			
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("refreshTimerSet", refreshTimerSet);
			request.setAttribute("status", actionCodeSatu);
			request.setAttribute("chooseDate", date);
			request.setAttribute("dateBy", dateBy);
			request.setAttribute("actionCode", actionCode);



			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("EdcTransactionLogs", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			
//			if (date != null)
//			{
//				if (date.toString().equals("1970-01-01")) {
//					request.setAttribute("chooseDate", "");
//				} else {
//					request.setAttribute("chooseDate", date);
//				}
//			}else {
//				request.setAttribute("chooseDate", date);
//			}


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
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailEdcTransactionLog");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchEdcTransactionLog");
				breadcrumb = "<a href=\"edctransactionlog\" class=\"linkbreadcrumb\">Search Edc Transaction Log</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupEdcTransactionLog");
			}else if (navigation.equalsIgnoreCase("graphEdcTransactionLog")) {
				result = graphPerformed(request, response,
						"graphEdcTransactionLog");
			
			
			//else if(navigation.equalsIgnoreCase("tambahcoba")){
			//	result = searchPerformed(request, response,
			//			"searchEdcTransactionLog");
			//}
			}else {
				result = searchPerformed(request, response,
						"searchEdcTransactionLog");
				breadcrumb = "<a href=\"edctransactionlog\" class=\"linkbreadcrumb\">Search Edc Transaction Log</a>";
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
