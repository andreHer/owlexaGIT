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
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * BatchClaim is a servlet controller for batch_claim Table. All you have to do
 * is to convert necessary data field to the named parameter
 */
public class BatchClaimReportController implements Controller

// extends+

// extends-

{

	private BatchClaimService batchClaimService;

	private UserService userService;
	
	private SecurityService securityService;

	private ResourceBundleMessageSource alertProperties;

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


	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public BatchClaimService getBatchClaimService() {
		return this.batchClaimService;
	}

	
		public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHBATCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHBATCHCLAIM access");
				return errorResult;
				
			}
			
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
			
			Integer searchstatus = WebUtil.getParameterInteger(request, "status");
			
			Integer clientId = WebUtil.getParameterInteger(request,"clientId");
			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			java.sql.Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			java.sql.Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			
			String dateSearchBy = WebUtil.getParameterString(request, "dateBy", "batchClaimDate");
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
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("batchNumberPsea")) {
						vLikeP.add("batchNumberPsea");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaimNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("paymentMethod")) {
						vLikeP.add("paymentMethod");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("invoiceNumber")) {
						vLikeP.add("invoiceNumber");
						vLikeQ.add(searchtext);
					}
					
					if (searchstatus != null && searchstatus.intValue() > 0){
						vEqP.add("batchClaimStatus.caseStatusId");
						vEqQ.add(searchstatus);
					}
				}
				
				

			}

			if (navigation.equalsIgnoreCase("listclient")){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
				
				result.addObject("clientId", clientId);
			}
			else if (navigation.equalsIgnoreCase("list")){
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
				
				result.addObject("providerId",providerId);
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


			String arah = WebUtil.getParameterString(request, "arah", "");



			String required[] = new String[] {
			// foreign affairs
					"BatchClaim.BatchClaimStatus",
					"BatchClaim.PaymentRecipient", "BatchClaim.MemberId",
					"BatchClaim.ClientId", "BatchClaim.BatchClaimType",
					"BatchClaim.MemberGroupId", "BatchClaim.ProviderId",
					"BatchClaim.Priority",
			// foreign affairs end
			};
			
			if (minimumDate != null && maximumDate != null){
				collection = batchClaimService.search(sLikeP, sLikeQ, sEqP, sEqQ,dateSearchBy, minimumDate,maximumDate);
			}
			else {
				collection = batchClaimService.search(sLikeP,sLikeQ,sEqP,sEqQ);
			}


			if (minimumDate != null && !minimumDate.toString().equals("1970-01-01")){
				request.setAttribute("minimumDate",minimumDate);
			}
			if (maximumDate != null && !maximumDate.toString().equalsIgnoreCase("1970-01-01")){
				request.setAttribute("maximumDate", maximumDate);
			}
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("BatchClaims", collection);

			
			request.setAttribute("alert", request.getParameter("alert"));
			
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
		try {
			if (navigation.equalsIgnoreCase("report")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = searchPerformed(request, response, "reportBatchClaim");
			}
			else {
				result = searchPerformed(request, response, "reportBatchClaim");
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
