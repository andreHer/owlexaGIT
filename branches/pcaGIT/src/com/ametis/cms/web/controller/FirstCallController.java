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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.FirstCall;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.FirstCallService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.DiagnosisDownloader;
import com.ametis.cms.util.FirstCallDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * FirstCall is a servlet controller for first_call Table. All you have to do is
 * to convert necessary data field to the named parameter
 */
public class FirstCallController implements Controller

// extends+

// extends-

{

	private FirstCallService firstCallService;

	private UserService userService;

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

	public void setFirstCallService(FirstCallService firstCallService) {
		this.firstCallService = firstCallService;
	}

	public FirstCallService getFirstCallService() {
		return this.firstCallService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer callId = WebUtil.getParameterInteger(request, "callId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = callId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEFIRSTCALL");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEFIRSTCALL access");
				return errorResult;

			}
			FirstCall res = firstCallService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.firstcall", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.firstcall", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchFirstCall");
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

	public ModelAndView closePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer callId = WebUtil.getParameterInteger(request, "callId");

			java.io.Serializable pkey = callId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CLOSEFIRSTCALL");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEFIRSTCALL access");
				return errorResult;

			}
			boolean res =firstCallService.close(pkey, user);

			if (res) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.close.firstcall", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.close.firstcall", null, "fail", Locale
								.getDefault()));

			}
			result = detailPerformed(request, response, "detailFirstCall");
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
			Integer callId = WebUtil.getParameterInteger(request, "callId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			
			String[] required = {"FirstCall.CustomerId","FirstCall.Status"};
			result = new ModelAndView(location);
			java.io.Serializable pkey = callId;
			FirstCall object = firstCallService.get(pkey,required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.firstcall", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("firstCall", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("navigation", navigation);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView jsonTotalPendingPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			String navigation = request.getParameter("navigation");
			result = new ModelAndView("jsonTotalPendingCall");
			int total = 0;
			
			
			if (navigation.equalsIgnoreCase("jsontotalopencall")){
				total = firstCallService.getTotalFollowUpNeededCall();
			}
			else if (navigation.equalsIgnoreCase("jsonmyopencall")){
				User currentUser = securityService.getCurrentUser(request);
				total = firstCallService.getTotalFollowUpNeededCall(currentUser.getUserId());
			}else if(navigation.equalsIgnoreCase("jsontotalfollowupcase")){
				total = firstCallService.getTotalFollowUpErrorLogByLogType(FirstCall.CALL_LOG_TYPE_CASE);
			}else if(navigation.equalsIgnoreCase("jsontotalfollowupmember")){
				total = firstCallService.getTotalFollowUpErrorLogByLogType(FirstCall.CALL_LOG_TYPE_MEMBER);
			}else if(navigation.equalsIgnoreCase("jsontotalfollowupclaim")){
				total = firstCallService.getTotalFollowUpErrorLogByLogType(FirstCall.CALL_LOG_TYPE_CLAIM);
			}
			
			result.addObject("result", total);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView jsonTotalErrorLog(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			String navigation = request.getParameter("navigation");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			
			result = new ModelAndView("jsonTotalPendingCall");
			int total = 0;
			
			
			if(navigation.equalsIgnoreCase("jsontotalmemberelog")){
				total = firstCallService.getTotalMemberErrorLog(memberId, FirstCall.CALL_LOG_TYPE_MEMBER);
			}else if(navigation.equalsIgnoreCase("jsontotalcaseelog")){
				total = firstCallService.getTotalCaseErrorLog(caseId, FirstCall.CALL_LOG_TYPE_CASE);
			}else if(navigation.equalsIgnoreCase("jsontotalclaimelog")){
				total = firstCallService.getTotalClaimErrorLog(claimId, FirstCall.CALL_LOG_TYPE_CLAIM);
			}
			
			result.addObject("result", total);
			
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
			String searchStatus = WebUtil.getParameterString(request,
					"searchstatus","");
			
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			ActionUser user = securityService.getActionUser(request);
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
			System.out.println("NAVIGATIONssss : "+navigation);
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("callerName")) {
						vLikeP.add("callerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("createdBy")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("location")) {
						vLikeP.add("location");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("provider")) {
						vLikeP.add("provider");
						vLikeQ.add(searchtext);
					}
				}

				if (searchStatus != null && !searchStatus.equalsIgnoreCase("") ) {
					vEqP.add("status.caseStatusId");
					vEqQ.add(Integer.valueOf(searchStatus));
				}
			}
			

			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.CLIENT){
					vEqP.add("customerId.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				}							
			}
			if (navigation.equalsIgnoreCase("searchopen")){
				vEqP.add("status.caseStatusId");
				vEqQ.add(CaseStatus.CASE_OPEN);
			}
			
			if(navigation.equalsIgnoreCase("searchfollowupcase")){
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_CASE);
				vEqP.add("status.caseStatusId");
				vEqQ.add(CaseStatus.CASE_OPEN);
				vEqP.add("followup");
				vEqQ.add("Y");
			}else if(navigation.equalsIgnoreCase("searchfollowupmember")){
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_MEMBER);
				vEqP.add("status.caseStatusId");
				vEqQ.add(CaseStatus.CASE_OPEN);
				vEqP.add("followup");
				vEqQ.add("Y");
			}else if(navigation.equalsIgnoreCase("searchfollowupclaim")){
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_CLAIM);
				vEqP.add("status.caseStatusId");
				vEqQ.add(CaseStatus.CASE_OPEN);
				vEqP.add("followup");
				vEqQ.add("Y");
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

			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"callStartTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};
				
				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
				
				
				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn,false,"callId" ,
					null, 0, count);
			}
			else {
				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				
				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,false,"callId",
					null, 0, count);
			}


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
					"FirstCall.Handledby", "FirstCall.Status",
					"FirstCall.CallCategoryId", "FirstCall.CustomerId",
			// foreign affairs end
			};
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"callStartTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};

				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,false,"callId",
					required,rowsetint, countSet.intValue());
			}
			else {
				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,false,"callId",
						required,rowsetint, countSet.intValue());
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
				if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
						&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				
					String[] betweenColumn = {"callStartTime"};
					Object[] minColumn = {minDate};
					Object[] maxColumn = {maxDate};

				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,false,"callId",
						required,rowsetint, countSet.intValue());
				}
				else {
					collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,false,"callId",
							required,rowsetint, countSet.intValue());
				}
			}
			
			String minimumDate = "";
			String maximumDate = "";
			
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01")){
				minimumDate = minDate.toString();
			}
			if (maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				maximumDate = maxDate.toString();
			}
			
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);


			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("searchstatus", searchStatus);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("FirstCalls", collection);

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
	
	public ModelAndView lookupErrorLog(HttpServletRequest request,
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
			
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			ActionUser user = securityService.getActionUser(request);
			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			System.out.println("NAVIGATION FIRST CALL : "+navigation);

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			System.out.println("SEARCH BY : "+searchby);
			System.out.println("SEARCH TEXT : "+searchtext);
			if (navigation.equals("gosearchmembererrorlog") || navigation.equals("gosearchcaseerrorlog") || navigation.equalsIgnoreCase("gosearchclaimerrorlog")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("callerName")) {
						vLikeP.add("callerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("createdBy")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("location")) {
						vLikeP.add("location");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("provider")) {
						vLikeP.add("provider");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("telephone")) {
						vLikeP.add("telephone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("category")) {
						vLikeP.add("callCategoryId.callCategoryName");
						vLikeQ.add(searchtext);
					}
				}

			}

			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.CLIENT){
					vEqP.add("customerId.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				}							
			}
			if (navigation.equalsIgnoreCase("searchopen")){
				vEqP.add("status.caseStatusId");
				vEqQ.add(CaseStatus.CASE_OPEN);
			}
			System.out.println("NAVIGATION : "+navigation);
			if(navigation.equalsIgnoreCase("searchcaseerrorlog") || navigation.equalsIgnoreCase("gosearchcaseerrorlog")){
				vEqP.add("caseId.caseId");
				vEqQ.add(caseId);
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_CASE);
			}else if(navigation.equalsIgnoreCase("searchmembererrorlog") || navigation.equalsIgnoreCase("gosearchmembererrorlog")){
				vEqP.add("customerId.memberId");
				vEqQ.add(memberId);
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_MEMBER);
			}else if(navigation.equalsIgnoreCase("searchclaimerrorlog") || navigation.equalsIgnoreCase("gosearchclaimerrorlog")){
				vEqP.add("claimId.claimId");
				vEqQ.add(claimId);
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_CLAIM);
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

//			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
//					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
//			
//				String[] betweenColumn = {"callStartTime"};
//				Object[] minColumn = {minDate};
//				Object[] maxColumn = {maxDate};
//				
//				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
//				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn, true,"priority" , null, 0, count);
//			}
//			else {
//				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
//				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ, true,"priority", null, 0, count);
//			}
			
			System.out.println("vEqP = " + vEqP);
			System.out.println("vEqQ = " + vEqQ);
			
			count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");
			System.out.println("ARAH : "+arah);
			System.out.println("COUNT : "+count);
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
					"FirstCall.Handledby", "FirstCall.Status",
					"FirstCall.CallCategoryId", "FirstCall.CustomerId",
			// foreign affairs end
			};
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"callStartTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};

				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,true,"priority",
					required,rowsetint, countSet.intValue());
			}
			else {
				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,true,"priority",
						required,rowsetint, countSet.intValue());
			}
			System.out.println("ROWSETINT : "+rowsetint);
			System.out.println("COUNTSET : "+countSet.intValue());

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
				if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
						&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				
					String[] betweenColumn = {"callStartTime"};
					Object[] minColumn = {minDate};
					Object[] maxColumn = {maxDate};

				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,true,"priority",
						required,rowsetint, countSet.intValue());
				}
				else {
					collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,true,"priority",
							required,rowsetint, countSet.intValue());
				}
			}
			
			String minimumDate = "";
			String maximumDate = "";
			
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01")){
				minimumDate = minDate.toString();
			}
			if (maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				maximumDate = maxDate.toString();
			}
			
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);


			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("FirstCalls", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("memberId", memberId);
			request.setAttribute("caseId", caseId);
			request.setAttribute("claimId", claimId);
			
			System.out.println("MIN INDEX : "+minIndex);
			System.out.println("MAX INDEX : "+maxIndex);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	
	public ModelAndView downloadCallLog(HttpServletRequest request,
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
			
			
			String logType = "";
			
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			ActionUser user = securityService.getActionUser(request);
			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			System.out.println("NAVIGATION FIRST CALL : "+navigation);

			if (StringUtils.isNumeric(rowset)) {
				rowsetint = Integer.parseInt(rowset);
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			
			System.out.println("SEARCH BY : "+searchby);
			System.out.println("SEARCH TEXT : "+searchtext);
			System.out.println("caseId : "+caseId);
			if (navigation.equals("gosearchmembererrorlog") || navigation.equals("gosearchcaseerrorlog") || navigation.equalsIgnoreCase("gosearchclaimerrorlog")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("callerName")) {
						vLikeP.add("callerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("createdBy")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("location")) {
						vLikeP.add("location");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("provider")) {
						vLikeP.add("provider");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("telephone")) {
						vLikeP.add("telephone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("category")) {
						vLikeP.add("callCategoryId.callCategoryName");
						vLikeQ.add(searchtext);
					}
				}

			}

			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.CLIENT){
					vEqP.add("customerId.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				}							
			}
			if (navigation.equalsIgnoreCase("searchopen")){
				vEqP.add("status.caseStatusId");
				vEqQ.add(CaseStatus.CASE_OPEN);
			}
			System.out.println("NAVIGATION : "+navigation);
			if(navigation.equalsIgnoreCase("downloadcaseerrorlog") || navigation.equalsIgnoreCase("gosearchcaseerrorlog")){
				vEqP.add("caseId.caseId");
				vEqQ.add(caseId);
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_CASE);
				logType = "caselog";
			}else if(navigation.equalsIgnoreCase("downloadmembererrorlog") || navigation.equalsIgnoreCase("gosearchmembererrorlog")){
				vEqP.add("customerId.memberId");
				vEqQ.add(memberId);
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_MEMBER);
				logType = "memberlog";
			}else if(navigation.equalsIgnoreCase("downloadclaimerrorlog") || navigation.equalsIgnoreCase("gosearchclaimerrorlog")){
				vEqP.add("claimId.claimId");
				vEqQ.add(claimId);
				vEqP.add("callLogType");
				vEqQ.add(FirstCall.CALL_LOG_TYPE_CLAIM);
				logType = "claimlog";
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

//			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
//					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
//			
//				String[] betweenColumn = {"callStartTime"};
//				Object[] minColumn = {minDate};
//				Object[] maxColumn = {maxDate};
//				
//				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
//				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn, true,"priority" , null, 0, count);
//			}
//			else {
//				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
//				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ, true,"priority", null, 0, count);
//			}
			
			System.out.println("vEqP = " + vEqP);
			System.out.println("vEqQ = " + vEqQ);
			
			count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");
			System.out.println("ARAH : "+arah);
			System.out.println("COUNT : "+count);
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
					"FirstCall.Handledby", "FirstCall.Status",
					"FirstCall.CallCategoryId", "FirstCall.CustomerId",
			// foreign affairs end
			};
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"callStartTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};

				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,true,"priority",
					required,rowsetint, countSet.intValue());
			}
			else {
				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,true,"priority",
						required,rowsetint, countSet.intValue());
			}
			System.out.println("ROWSETINT : "+rowsetint);
			System.out.println("COUNTSET : "+countSet.intValue());

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
				if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
						&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				
					String[] betweenColumn = {"callStartTime"};
					Object[] minColumn = {minDate};
					Object[] maxColumn = {maxDate};

				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,true,"priority",
						required,rowsetint, countSet.intValue());
				}
				else {
					collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,true,"priority",
							required,rowsetint, countSet.intValue());
				}
			}
			
			
			
			System.out.println("ROWSETINT : "+rowsetint);
			System.out.println("COUNTSET : "+countSet.intValue());

			HSSFWorkbook workbook = FirstCallDownloader.downloadReport(logType, collection);

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setHeader("Content-disposition", "inline; filename="
                    + logType+"logreport.xls");

            ServletOutputStream sos = response.getOutputStream();

            workbook.write(sos);
            // sos.write(workbook.getBytes());
            sos.close();
			
			String minimumDate = "";
			String maximumDate = "";
			
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01")){
				minimumDate = minDate.toString();
			}
			if (maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				maximumDate = maxDate.toString();
			}
			
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);


			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("FirstCalls", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("memberId", memberId);
			request.setAttribute("caseId", caseId);
			request.setAttribute("claimId", claimId);
			
			System.out.println("MIN INDEX : "+minIndex);
			System.out.println("MAX INDEX : "+maxIndex);

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

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

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

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("callerName")) {
						vLikeP.add("callerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("createdBy")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("location")) {
						vLikeP.add("location");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("provider")) {
						vLikeP.add("provider");
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

			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"callStartTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};
				
				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
				
				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn,
					null, 0, count);
			}
			else {
				count = firstCallService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				
				collection = firstCallService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					null, 0, count);
			}

			String data = "";
			int idx = 1;
			data += "No, Caller Name, Call Time, Telephone, Call Handler , Call Type, Call Description, Call Remarks  \n\n";
			
			if (collection != null){
				Iterator<FirstCall> callIterator  = collection.iterator();
				
				FirstCall call = null;
				
				while (callIterator.hasNext()){
					call = callIterator.next();
					
					data += idx + "," + call.getCallerName() + "," + call.getCallStartTime() + ","+ call.getTelephone();
					data += "," + call.getCreatedBy() + "," + call.getCallCategoryId().getCallCategoryName() + ",";
					data += "\"" + call.getDescription() + "\""+ "," + "\"" +call.getRemarks() + "\"";
					data += "\n";
					
					idx ++;
				}
			}
			System.out.println("DATA : " + data);
			
			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setHeader("Content-disposition",
					"inline; filename="  
							+ "callReport.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));
			
			
			sos.write(data.getBytes());
			sos.close();
			
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */



			
			String minimumDate = "";
			String maximumDate = "";
			
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01")){
				minimumDate = minDate.toString();
			}
			if (maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				maximumDate = maxDate.toString();
			}
			
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);


			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("FirstCalls", collection);

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

		String breadcrumb = "";


		try {
			if (navigation.equalsIgnoreCase("detail")) {
				result = detailPerformed(request, response, "detailFirstCall");
				String callId = request.getParameter("callId");
				breadcrumb = "<a href=\"firstcall?navigation=detail&callId="+callId+"\" class=\"linkbreadcrumb\">Detail First Call</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			}
			 else if (navigation.equalsIgnoreCase("close")) {
					result = closePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchFirstCall");
				breadcrumb = "<a href=\"firstcall?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search First Call</a> ";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupFirstCall");
			}
			else if (navigation.equalsIgnoreCase("export")){
				result = exportPerformed(request, response, "searchFirstCall");
			}
			
			else if (navigation.equalsIgnoreCase("jsontotalopencall") || navigation.equalsIgnoreCase("jsonmyopencall") || navigation.equalsIgnoreCase("jsontotalfollowupcase")
					|| navigation.equalsIgnoreCase("jsontotalfollowupmember") || navigation.equalsIgnoreCase("jsontotalfollowupclaim")){
				result = jsonTotalPendingPerformed(request, response, "jsonTotalPendingCall");
			}
			else if(navigation.equalsIgnoreCase("jsontotalmemberelog") || navigation.equalsIgnoreCase("jsontotalcaseelog") ||
					navigation.equalsIgnoreCase("jsontotalclaimelog")){
				result = jsonTotalErrorLog(request, response, "jsonTotalMemberLog");
			}
			else if (navigation.equalsIgnoreCase("searchcaseerrorlog")
					|| navigation.equals("searchmembererrorlog") || navigation.equals("searchclaimerrorlog")||
					navigation.equalsIgnoreCase("gosearchcaseerrorlog")
					|| navigation.equals("gosearchmembererrorlog") || navigation.equals("gosearchclaimerrorlog")) {
				result = lookupErrorLog(request, response, "lookupFirstCall");
				breadcrumb = "<a href=\"firstcall?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search First Call</a> ";
			}
			else if(navigation.equalsIgnoreCase("downloadcaseerrorlog") || navigation.equalsIgnoreCase("downloadmembererrorlog") ||
					navigation.equalsIgnoreCase("downloadclaimerrorlog")){
				result = downloadCallLog(request, response, "lookupFirstCall");
			}
			else {
				result = searchPerformed(request, response, "searchFirstCall");
				breadcrumb = "<a href=\"firstcall?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search First Call</a> ";
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
