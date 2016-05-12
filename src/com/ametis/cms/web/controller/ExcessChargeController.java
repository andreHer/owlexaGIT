package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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

import com.ametis.cms.util.ExcessReportGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.ExcessReminder;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.ExcessReminderService;
import com.ametis.cms.service.FundService;
import com.ametis.cms.service.InvoiceService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.dao.DaoSupportUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * ExcessCharge is a servlet controller for excess_charge Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class ExcessChargeController implements Controller

// extends+

// extends-

{

	private ExcessChargeService excessChargeService;
	private PaymentService paymentService;
	private ExcessReminderService excessReminderService;

	private UserService userService;
	
	private MemberGroupService memberGroupService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;

	private SecurityService securityService;
	
	private ClaimItemService claimItemService;
	
	private FundService fundService;

	private ConfigurationService configurationService;
	
	private InvoiceService invoiceService;
	private ActivityLogService logService;
	private MemberService memberService;

	
	
	private BatchClaimService batchClaimService;
	
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	
	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	
	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public InvoiceService getInvoiceService() {
	return invoiceService;
}

public void setInvoiceService(InvoiceService invoiceService) {
	this.invoiceService = invoiceService;
}

	public ExcessReminderService getExcessReminderService() {
	return excessReminderService;
}

public void setExcessReminderService(ExcessReminderService excessReminderService) {
	this.excessReminderService = excessReminderService;
}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public FundService getFundService() {
		return fundService;
	}

	public void setFundService(FundService fundService) {
		this.fundService = fundService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
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

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public ExcessChargeService getExcessChargeService() {
		return this.excessChargeService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer excessChargeId = WebUtil.getParameterInteger(request,
					"excessChargeId");

			java.io.Serializable pkey = excessChargeId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEEXCESSCHARGE");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEEXCESSCHARGE access");
				return errorResult;
				
			}
			ExcessCharge res = excessChargeService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.excesscharge", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.excesscharge", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchExcessCharge");
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
	public ModelAndView badPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer excessChargeId = WebUtil.getParameterInteger(request,
					"excessChargeId");

			java.io.Serializable pkey = excessChargeId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "BADEXCESS");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for BADEXCESS access");
				return errorResult;
				
			}
			boolean res = excessChargeService.badExcess(pkey, user);

			if (res ) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.bad.excesscharge", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.bad.excesscharge", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchExcessCharge");
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
	public ModelAndView confirmSendPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer excessChargeId = WebUtil.getParameterInteger(request,
					"excessChargeId");

			
			java.io.Serializable pkey = excessChargeId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CONFIRMSEND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for BADEXCESS access");
				return errorResult;
				
			}
			boolean res = excessChargeService.confirmExcessLetterSent(pkey, user);

			if (res ) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.bad.excesscharge", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.bad.excesscharge", null, "fail", Locale
								.getDefault()));

			}
			result = detailPerformed(request, response, "detailExcessCharge");
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
	public ModelAndView saveInvoicePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer invoiceId = WebUtil.getParameterInteger(request,
					"invoiceId");

			String[] excessList = request.getParameterValues("excessIds");
			
			

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CONFIRMSEND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for BADEXCESS access");
				return errorResult;
				
			}
			boolean res = invoiceService.confirmInvoiceAssignment(invoiceId, excessList, user);

			if (res ) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.bad.excesscharge", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.bad.excesscharge", null, "fail", Locale
								.getDefault()));

			}
			result = new ModelAndView(new RedirectView("invoice?navigation=detail&invoiceId="+invoiceId));
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
	public ModelAndView printPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer excessChargeId = WebUtil.getParameterInteger(request,
					"excessChargeId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");
			
			Configuration configuration = configurationService.getSystemConfiguration();

			location = location+"_"+configuration.getCompanyCode().toLowerCase();
			
			result = new ModelAndView(location);
			java.io.Serializable pkey = excessChargeId;
			
			String[] required = {"ExcessCharge.ExcessReminders", "ExcessCharge.MemberId","ExcessCharge.MemberId.ParentMember",
					"ExcessCharge.ClaimId","ExcessCharge.MemberId.MemberGroupId",
					"ExcessCharge.MemberId.ClientId","ExcessCharge.ClaimId.DiagnosisId"};

			ExcessCharge object = excessChargeService.get(pkey, required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.excesscharge", null, "fail", Locale
								.getDefault()));
			}
			else {
				if (configuration.getExcessPrintClaimType() != null && configuration.getExcessPrintClaimType().equalsIgnoreCase("excess")){
					Member member = object.getMemberId();
					result.addObject("member", member);
					
					Collection<ClaimItem> claimItems = claimItemService.getExcessClaimItem(object.getClaimId().getClaimId());
					
					result.addObject("claimItems", claimItems);
					
					Member policyHolder = member.getParentMember();
					
					result.addObject("policyHolder", policyHolder);
				}
				else {
					Member member = object.getMemberId();
					result.addObject("member", member);
					
					Collection<ClaimItem> claimItems = claimItemService.getClaimItem(object.getClaimId().getClaimId());
					result.addObject("claimItems", claimItems);
					
					Member policyHolder = member.getParentMember();
					
					result.addObject("policyHolder", policyHolder);
				}
			}
			
			result.addObject("excessCharge", object);
			result.addObject("configuration", configuration);
			
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

	public ModelAndView exportPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
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
			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
	
			result = new ModelAndView(location);

		
			
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
			
			if (navigation.equals("export") ) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
				
					if (searchby.equalsIgnoreCase("excessNumber")) {
						vLikeP.add("excessChargeNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("memberId.customerNumber");
						vLikeQ.add(searchtext);
					}

				}

			}
			
			if (searchStatus != null && searchStatus.intValue() > 0){
				vEqP.add("excessChargeStatus.paymentStatusId");
				vEqQ.add(searchStatus);
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

			
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"excessChargeTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};
				
				count = excessChargeService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
				
				collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn,
					null, 0, count);
			}
			else {
				count = excessChargeService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				
				collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					null, 0, count);
			}

			String data = "";
			
			data += "Excess Number, Claim Number, Member Name, Member Number, Provider, Admission Date, Discharge Date, Group Name,CDV Number,CDV Date, Client Name, Claim Charge, Claim Paid, Excess Charge, Status, Reason \n\n";
			
			if (collection != null){
				Iterator<ExcessCharge> excessIterator  = collection.iterator();
				
				ExcessCharge excess = null;
				
				String reason = "";
				
				while (excessIterator.hasNext()){
					
					excess = excessIterator.next();
					
					String[] param = {"claimId.claimId","deletedStatus"};
					Object[] value = {excess.getClaimId().getClaimId(),Integer.valueOf(0)};
					
					int total = claimItemService.getTotal(null,null,param,value);
					Collection<ClaimItem> items = claimItemService.search(null, null,param,value,0,total);
					
					Iterator<ClaimItem> iterator = items.iterator();
					
					while (iterator.hasNext()){
						ClaimItem item = iterator.next();
						
						if (item != null){
							if (item.getExcessValue() != null && item.getExcessValue().doubleValue() > 0){
								reason += item.getBenefitCheckRemarks() + " & " ;
							}
						}
					}
					
					data += excess.getExcessChargeNumber()+","+ excess.getClaimId().getClaimNumber() +",\""+excess.getMemberId().getFirstName() +"\"," + excess.getMemberId().getCustomerPolicyNumber();
					data += ","+excess.getClaimId().getProviderId().getProviderName()+","+excess.getClaimId().getAdmissionDate()+","+excess.getClaimId().getDischargeDate()+","+excess.getMemberId().getMemberGroupId().getGroupName() +","+excess.getClaimId().getPaymentNumber()+"," + excess.getClaimId().getPaymentGeneratedDate() + ","+ excess.getMemberId().getClientId().getClientName() +",";
					data +=  excess.getClaimId().getClaimChargeValue() + "," +excess.getClaimId().getClaimApprovedValue() +"," +excess.getClaimId().getClaimExcessValue();
					data += "," + excess.getExcessChargeStatus().getPaymentStatusName() +"," +reason;
					data += "\n";
					
					reason = "";
				}
			}
			System.out.println("DATA : " + data);
			
			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setHeader("Content-disposition",
					"inline; filename="  
							+ "excessreport.csv");

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

			request.setAttribute("status",searchStatus);
			result.addObject("ExcessCharges", collection);
			
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
	
	public ModelAndView exportExcessRecapPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		
		ModelAndView result = null;

		try {
			
			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");
			
			String paymentBatchId = WebUtil.getParameterString(request, "paymentBatchId", "");
	
			result = new ModelAndView(location);

		
			
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
			
		
	
			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));
			
			vEqP.add("claimId.paymentId.paymentBatchId.id");
			vEqQ.add(Integer.valueOf(paymentBatchId));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
		

			
			String arah = WebUtil.getParameterString(request, "arah", "");

			
			
			count = excessChargeService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			
			collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,
				null, 0, count);
			
			String data = "";
			
			data += "No| Member Name| Excess Number| Claim Number|Provider|Group Name|CDV Number|CDV Date| Excess \n\n";
			
			if (collection != null){
				Iterator<ExcessCharge> excessIterator  = collection.iterator();
				
				ExcessCharge excess = null;
				
				String reason = "";
				int idx = 1;
				
				while (excessIterator.hasNext()){
					
					excess = excessIterator.next();
					
					
					data += idx+"|\""+excess.getMemberId().getFirstName() +"\"|"+ excess.getExcessChargeNumber()+"|"+ excess.getClaimId().getClaimNumber()  ;
					data += "|"+excess.getClaimId().getProviderId().getProviderName()+"|"+excess.getMemberId().getMemberGroupId().getGroupName() +"|"+excess.getClaimId().getPaymentNumber()+"|" + excess.getClaimId().getPaymentGeneratedDate() + "|"+ excess.getMemberId().getClientId().getClientName() +"|";
					data += excess.getClaimId().getClaimExcessValue();				
					data += "\n";
					
					idx++;
				}
			}
			System.out.println("DATA : " + data);
			
			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			
			response.setHeader("Content-disposition",
					"inline; filename="  
							+ "excessreport.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));
			
			
			sos.write(data.getBytes());
			sos.close();
			
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			
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
	
	public ModelAndView exportHeaderPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
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
			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
	
			result = new ModelAndView(location);

		
			
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
			
			if (navigation.equals("exportheader") ) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
				
					if (searchby.equalsIgnoreCase("excessNumber")) {
						vLikeP.add("excessChargeNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("memberId.customerNumber");
						vLikeQ.add(searchtext);
					}

				}

			}
			
			if (searchStatus != null && searchStatus.intValue() > 0){
				vEqP.add("excessChargeStatus.paymentStatusId");
				vEqQ.add(searchStatus);
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

			String[] required = {"ExcessCharge.ClaimId","ExcessCharge.MemberId","ExcessCharge.MemberId.ParentMember",
					"ExcessCharge.MemberId.MemberGroupId","ExcessCharge.ExcessChargeStatus"};
			
			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
			
				String[] betweenColumn = {"excessChargeTime"};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};
				
				count = excessChargeService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn);
				
				collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn,
					required, 0, count);
			}
			else {
				count = excessChargeService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				
				collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, count);
			}

//			String data = "";
//
//			data += "Invoice No| Due Date| Excess Number| Claim No| Employee Name|Member Name| Member Number| Group Name| Excess Charge|Excess Date|  Status \n\n";
//
//			if (collection != null){
//				Iterator<ExcessCharge> excessIterator  = collection.iterator();
//
//				ExcessCharge excess = null;
//
//				String reason = "";
//
//
//				while (excessIterator.hasNext()){
//					String invoiceNo = "-";
//					String dueDate = "";
//					excess = excessIterator.next();
//					String parentName = "";
//
//					if (excess.getInvoiceId() != null){
//						invoiceNo = excess.getInvoiceId().getInvoiceNumber();
//						dueDate = excess.getInvoiceId().getInvoiceDueDate().toString();
//
//						if (excess.getMemberId().getParentMember() != null){
//							parentName = excess.getMemberId().getParentMember().getFirstName();
//						}
//
//					}
//
//					data += invoiceNo+"|"+dueDate+"|"+ excess.getExcessChargeNumber()+"|"+excess.getClaimId().getClaimNumber()+"|\""+parentName+"\"|\""+excess.getMemberId().getFirstName() +"\"|'" + excess.getMemberId().getCustomerPolicyNumber()+"";
//					data += "|"+excess.getMemberId().getMemberGroupId().getGroupName() +"|";
//					data +=   +excess.getClaimId().getClaimExcessValue() +"|"+excess.getExcessChargeTime() ;
//					data += "|" + excess.getExcessChargeStatus().getPaymentStatusName() ;
//					data += "\n";
//
//					reason = "";
//				}
//			}
//			System.out.println("DATA : " + data);
//
//			response.setContentType("application/x-csv");
//			response.setHeader("Pragma", "no-cache");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setDateHeader("Expires", 0);
//
//			response.setHeader("Content-disposition",
//					"inline; filename="
//							+ "excessreport.csv");
//
//			ServletOutputStream sos = response.getOutputStream();
//
//			response.setHeader("Content-length", Integer
//					.toString(data.length()));
//
//
//			sos.write(data.getBytes());
//			sos.close();

			User currentUser = securityService.getCurrentUser(request);

			HSSFWorkbook workbook = ExcessReportGenerator.generateReport(currentUser, collection);

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setHeader("Content-disposition", "inline; filename="
					+ "excessreport.xls");

			ServletOutputStream sos = response.getOutputStream();

			workbook.write(sos);
			// sos.write(workbook.getBytes());
			sos.close();
			
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			request.setAttribute("status",searchStatus);
			result.addObject("ExcessCharges", collection);
			
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
	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer excessChargeId = WebUtil.getParameterInteger(request,
					"excessChargeId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			

			result = new ModelAndView(location);
			java.io.Serializable pkey = excessChargeId;
			
			System.out.println("------------------------- detail excess --------------------- ");
			String[] required = { "ExcessCharge.ExcessReminders","ExcessCharge.MemberId.MemberGroupId","ExcessCharge.ClaimId"};
			ExcessCharge object = excessChargeService.get(pkey, required);
			
			

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.excesscharge", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */
			
			String[] eqParam = {"excessChargeId.excessChargeId"};
			Object[] eqValue = {excessChargeId};
			
			int totalReminder = excessReminderService.getTotal(null,null,eqParam,eqValue);
			Collection<ExcessReminder> reminders = excessReminderService.search(null,null,eqParam,eqValue,0,totalReminder);

			
			result.addObject("excessReminders",reminders);
			result.addObject("excessCharge", object);
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
	
	public ModelAndView jsonPendingPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
			User currentUser = securityService.getCurrentUser(request);
			result = new ModelAndView(location);
			
			String navigation = request.getParameter("navigation");
			
			//Add by aju on 20150326, for client iFrame support
			HttpSession session = request.getSession(false);
			//check if there's session for iframe
			boolean isIFrameSession = false;
			if(session != null){
				if(session.getAttribute("iframe") != null){
					isIFrameSession = (session.getAttribute("iframe").toString().equalsIgnoreCase("yes")?true:false);
				}
			}
			String usingIFrame=null,iFrameClientMemberId=null,iFrameLevelLogin=null;
			Integer memberId;
			if(isIFrameSession){
				//System.out.println("IFrame Session detected on Member Controller");
				//get the iFrame saved parameter from session
				usingIFrame = session.getAttribute("iframe").toString();
				//Add by aju on 20150410, add level login filtering :D
				iFrameLevelLogin = session.getAttribute("levelLogin").toString();
				
				//modified by aju on 20150410, filter by level login :D
				//iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				if (iFrameLevelLogin.equalsIgnoreCase("member")){
					iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				}
				/**
				 * di-update menggunakan if karena tidak compatible switch pake string di JDK 6.0
				 */
				/**
				switch (iFrameLevelLogin) {
				case "client":
					break;
				case "member":
					iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
					break;
				}*/
			}
			
			int total = 0;
			
			if (navigation.equalsIgnoreCase("jsontotalopenexcess")){
				if (currentUser.getUserType().intValue() == User.TPA){
					total = excessChargeService.getTotalPendingExcess();
				}
				else if (currentUser.getUserType().intValue() == User.CLIENT){
					String[] eqParam = {"memberId.memberGroupId.clientId.clientId","deletedStatus","excessChargeStatus.paymentStatusId"};
					Object[] eqValue = {currentUser.getClientId().getClientId(),Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN};
					
					total = excessChargeService.getTotal(null,null,eqParam,eqValue);
				}
				else if (currentUser.getUserType().intValue() == User.MEMBER_GROUP){
					//modified by aju on 20150326, for client iFrame support fufufu :D
					if(!isIFrameSession){
						String[] eqParam = {"memberId.memberGroupId.memberGroupId","deletedStatus","excessChargeStatus.paymentStatusId"};
						Object[] eqValue = {currentUser.getMemberGroupId().getMemberGroupId(),Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN};
						total = excessChargeService.getTotal(null,null,eqParam,eqValue);
					}else{
						//add by aju on 20150326, for client iFrame support
						//filter for member family :D
						Member m = memberService.getMember(iFrameClientMemberId);
						memberId = m.getMemberId();
						String[] eqParam = {"memberId.parentMember.memberId","deletedStatus","excessChargeStatus.paymentStatusId"};
						Object[] eqValue = {memberId,Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN};
						total = excessChargeService.getTotal(null,null,eqParam,eqValue);
					}
					
				}
				else if (currentUser.getUserType().intValue() == User.MEMBER){
					String[] eqParam = {"memberId.parentMember.memberId","deletedStatus","excessChargeStatus.paymentStatusId"};
					Object[] eqValue = {currentUser.getMemberId().getMemberId(),Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN};
					
					total = excessChargeService.getTotal(null,null,eqParam,eqValue);
				}
				
				
			}
			else if (navigation.equalsIgnoreCase("jsontotalreminder")){
				total = excessChargeService.getTotalReminderExcess();
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
			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");

			ActionUser user = securityService.getActionUser(request);
			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
			String dateBy = WebUtil.getParameterString(request, "dateby", "");
			
			if (currentNavigation.equalsIgnoreCase("listgroup")){
				location = "listGroupExcessCharge";
			}
			else if (currentNavigation.equalsIgnoreCase("listmember")){
				location = "listMemberExcessCharge";
			}
			else if (currentNavigation.equalsIgnoreCase("listpayment")){
				location = "listPaymentExcessCharge";
			}
			else if (currentNavigation.equalsIgnoreCase("listbatch")){
				location = "listBatchExcessCharge";
			}

			result = new ModelAndView(location);

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			//Add by aju on 20150929, for handle iFrame things fufufu
			if(isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")){
				System.out.println("it\'s still on member(" + sessionMemberId + ") login session fufufu...");
				memberId = (memberId==null?Integer.parseInt(sessionMemberId):memberId);
				Member theMember = memberService.get(memberId);
				if(theMember!=null){
					System.out.println("checking->requestParentMemberId(" + theMember.getParentMember().getMemberId().toString() + ") vs sessionParentMemberId(" + sessionMemberParentId + ")");
					if(!theMember.getParentMember().getMemberId().toString().equals(sessionMemberParentId)){
						memberId=Integer.parseInt(sessionMemberId);
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","OtherFamilyAccessDenied");			
						errorResult.addObject("errorMessage", "Hey member("+ sessionMemberId + "), are you missing your way? :P<br/>" 
								+ "<a href=\"excesscharge?navigation=list&memberId="+memberId+"\">Go Back</a>");
						return errorResult;
					}
				}
			}
			

			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");
			Integer paymentId = WebUtil.getParameterInteger(request, "paymentId");
			Integer invoiceId = WebUtil.getParameterInteger(request, "invoiceId");
			
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
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortExcessNumber = true, sortMemberNo = true, sortMemberName = true, sortMemberGroup = true,
					sortExcessDate = true, sortExcessCharge = true, sortReminderDate = true, sortStatus = true,
					sortCounter = true, sortRemarks = true;
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
			
			System.out.println("NAVIGATION : "+navigation);
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golistbatch")||
					navigation.equals("golist")|| navigation.equals("listgroup") || navigation.equals("listbatch") ||
					navigation.equals("listpayment") || navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) { 
					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberId.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("excessNumber")) {
						vLikeP.add("excessChargeNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("customerNumber")) {
						vLikeP.add("memberNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")){
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("claimNumber")){
						vLikeP.add("claimId.claimNumber");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("remarks")){
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("excessNumber")){
						vLikeP.add("excessChargeNumber");
						vLikeQ.add(searchtext);
					}
				}
				
				

			}
			if (searchStatus != null && searchStatus.intValue() > 0){
				vEqP.add("excessChargeStatus.paymentStatusId");
				vEqQ.add(searchStatus);
			}	
			if (navigation.equalsIgnoreCase("list") || currentNavigation.equalsIgnoreCase("list") || navigation.equalsIgnoreCase("golist")){
				vEqP.add("memberId.memberId");
				vEqQ.add(memberId);
			}
			else if (navigation.equalsIgnoreCase("listgroup") || currentNavigation.equalsIgnoreCase("listgroup")){
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
				result.addObject("memberGroupId",memberGroupId);
			}			
			else if (navigation.equalsIgnoreCase("listpayment") || currentNavigation.equalsIgnoreCase("listpayment")){
				vEqP.add("claimId.batchClaimId.batchClaimId");
				vEqQ.add(batchClaimId);
			}
			else if (navigation.equalsIgnoreCase("listbatch") || currentNavigation.equalsIgnoreCase("listbatch") || navigation.equalsIgnoreCase("golistbatch") ){
				vEqP.add("claimId.batchClaimId.batchClaimId");
				vEqQ.add(batchClaimId);
				
				result.addObject("batchClaimId", batchClaimId);
				
				String[] reqBatch = {"BatchClaim.PaymentRecipient","BatchClaim.ProviderId","BatchClaim.MemberGroupId",
						"BatchClaim.MemberId","BatchClaim.BatchClaimStatus","BatchClaim.PaymentMethod","BatchClaim.ClaimCurrency"};
				BatchClaim batchClaim = batchClaimService.get(batchClaimId,reqBatch);
				if (batchClaim != null){
					result.addObject("batchClaim", batchClaim);
				}
			}
			else if (navigation.equalsIgnoreCase("listinvoice") || currentNavigation.equalsIgnoreCase("listinvoice")){
				vEqP.add("invoiceId.invoiceId");
				vEqQ.add(invoiceId);
			}
			else if (navigation.equalsIgnoreCase("searchunassigned")){
				vEqP.add("excessLetterSent");
				vEqQ.add(Integer.valueOf(0));
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
				
				request.setAttribute("invoiceId", invoiceId);
			}
			else {
			
				if (user != null && user.getUser().getUserType() != null){
					if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
						vEqP.add("memberId.memberGroupId.memberGroupId");
						vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
						
						//Add by aju on 20150326, for client iFrame support fufufu :D
						if(isIFrameSession){
							vEqP.add("memberId.parentMember.memberId");
							vEqQ.add(memberId);
						}
						
					}
					else if (user.getUser().getUserType().intValue() == User.CLIENT){
						vEqP.add("memberId.clientId.clientId");
						vEqQ.add(user.getUser().getClientId().getClientId());
					}
					else if (user.getUser().getUserType().intValue() == User.PROVIDER){
						vEqP.add("clientId.clientId");
						vEqQ.add(user.getUser().getClientId().getClientId());
					}
					else if (user.getUser().getUserType().intValue() == User.MEMBER){
						vEqP.add("memberId.parentMember.memberId");
						vEqQ.add(user.getUser().getMemberId().getMemberId());
					}
					else if (user.getUser().getUserType().intValue() == User.BROKER){
						//vEqP.add("providerId.providerId");
						//vEqQ.add(user.getUser().getProviderId().getProviderId());
						
						vEqP.add("memberId.memberGroupId.brokerId.brokerId");
						vEqQ.add(user.getUser().getBrokerId().getBrokerId());
					}
									
				}
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


			if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
					&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				if(dateBy.equals("")){
					dateBy="excessChargeTime";
				}
				String[] betweenColumn = {dateBy};
				Object[] minColumn = {minDate};
				Object[] maxColumn = {maxDate};
									
				count = excessChargeService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, betweenColumn,minColumn,maxColumn);
			}
			else {
				count = excessChargeService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}

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
					"ExcessCharge.MemberId", "ExcessCharge.MemberId.MemberGroupId", "ExcessCharge.ClaimId",
					"ExcessCharge.ExcessChargeStatus"
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("excessnumber")){
						sortByColumn = "excessChargeNumber";
						Boolean sortOrderChargeNumber = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderChargeNumber", ""));
						sortExcessNumber = !sortOrderChargeNumber;
						order = sortExcessNumber;
					}else if(sortcolumn.equalsIgnoreCase("membernumber")){
						sortByColumn = "memberId.customerPolicyNumber";
						Boolean sortOrderMemberNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderMemberNo", ""));
						sortMemberNo = !sortOrderMemberNo;
						order = sortMemberNo;
					}else if(sortcolumn.equalsIgnoreCase("membername")){
						sortByColumn = "memberId.firstName";
						Boolean sortOrderMemberName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderMemberName", ""));
						sortMemberName = !sortOrderMemberName;
						order = sortMemberName;
					}else if(sortcolumn.equalsIgnoreCase("membergroup")){
						sortByColumn = "memberId.memberGroupId.groupName";
						Boolean sortOrderMemberGroup = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderMemberGroup", ""));
						sortMemberGroup = !sortOrderMemberGroup;
						order = sortMemberGroup;
					}else if(sortcolumn.equalsIgnoreCase("excessdate")){
						sortByColumn = "excessChargeTime";
						Boolean sortOrderExcessDate = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderExcessDate", ""));
						sortExcessDate = !sortOrderExcessDate;
						order = sortExcessDate;
					}else if(sortcolumn.equalsIgnoreCase("excesscharges")){
						sortByColumn = "excessChargeValue";
						Boolean sortOrderExcessCharge = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderExcessCharge", ""));
						sortExcessCharge = !sortOrderExcessCharge;
						order = sortExcessCharge;
					}else if(sortcolumn.equalsIgnoreCase("status")){
						sortByColumn = "excessChargeStatus.paymentStatusName";
						Boolean sortOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderStatus", ""));
						sortStatus = !sortOrderStatus;
						order = sortStatus;
					}else if(sortcolumn.equalsIgnoreCase("reminderdate")){
						sortByColumn = "nextReminderTime";
						Boolean sortOrderReminder = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderReminder", ""));
						sortReminderDate = !sortOrderReminder;
						order = sortReminderDate;
					}else if(sortcolumn.equalsIgnoreCase("counter")){
						sortByColumn = "reminderCounter";
						Boolean sortOrderCounter = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderCounter", ""));
						sortCounter = !sortOrderCounter;
						order = sortCounter;
					}
					else if(sortcolumn.equalsIgnoreCase("remarks")){
						sortByColumn = "remarks";
						Boolean sortOrderRemarks = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderRemarks", ""));
						sortCounter = !sortOrderRemarks;
						order = sortCounter;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("excessnumber")){
						sortExcessNumber = order;
					}else if(sortcolumn.equalsIgnoreCase("membernumber")){
						sortMemberNo = order;
					}else if(sortcolumn.equalsIgnoreCase("membername")){
						sortMemberName = order;
					}else if(sortcolumn.equalsIgnoreCase("membergroup")){
						sortMemberGroup = order;
					}else if(sortcolumn.equalsIgnoreCase("excessdate")){
						sortExcessDate = order;
					}else if(sortcolumn.equalsIgnoreCase("excesscharges")){
						sortExcessCharge = order;
					}else if(sortcolumn.equalsIgnoreCase("status")){
						sortStatus = order;
					}else if(sortcolumn.equalsIgnoreCase("reminderdate")){
						sortReminderDate = order;
					}else if(sortcolumn.equalsIgnoreCase("counter")){
						sortCounter = order;
					}else if(sortcolumn.equalsIgnoreCase("remarks")){
						sortRemarks = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
						&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
					if(dateBy.equals("")){
						dateBy = "excessChargeTime";
					}
					String[] betweenColumn = {dateBy};
					Object[] minColumn = {minDate};
					Object[] maxColumn = {maxDate};

					collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,
						order, sortByColumn, required,rowsetint, countSet.intValue());
				}else{
					collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
							required,rowsetint, countSet.intValue());
				}
			}else{
				if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
						&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
					if(dateBy.equals("")){
						dateBy = "excessChargeTime";
					}
					String[] betweenColumn = {dateBy};
					Object[] minColumn = {minDate};
					Object[] maxColumn = {maxDate};

				collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,betweenColumn,minColumn,maxColumn ,
						required,rowsetint, countSet.intValue());
				}
				else {
					collection = excessChargeService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required,rowsetint, countSet.intValue());
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
				
				if (minDate != null && !minDate.toString().equalsIgnoreCase("1970-01-01") 
						&& maxDate != null && !maxDate.toString().equalsIgnoreCase("1970-01-01")){
				
					String[] betweenColumn = {"excessChargeTime"};
					Object[] minColumn = {minDate};
					Object[] maxColumn = {maxDate};
				collection = excessChargeService.search(sLikeP, sLikeQ, sEqP,
						sEqQ,betweenColumn,minColumn,maxColumn , required, rowsetint, countSet.intValue());
				}
				else {
					collection = excessChargeService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, rowsetint, countSet.intValue());
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
			if (memberId != null){
				String[] requiredMember = {"Member.MemberGroupId","Member.ClientId",
						"Member.RelationshipId","Member.ParentMember","Member.CurrentPolicyId"};
				Member object = memberService.get(memberId,requiredMember);
				result.addObject("member", object);
				
				//hitung umur
				Date umur =  object.getBirthday();
				Calendar dob = Calendar.getInstance();  
				dob.setTime(umur);  
				Calendar today = Calendar.getInstance();  
				int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR); 
				if ((today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
				    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) ||
				    today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
					age--;  
				}
				result.addObject("age", age);
				//end
			}
			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);
			request.setAttribute("dateby", dateBy);
			request.setAttribute("paymentId", paymentId);
			request.setAttribute("batchClaimId", batchClaimId);
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("invoiceId", invoiceId);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			MemberGroup memberGroupObject = null;
			
			if(memberGroupId != null){
				try
				{
				java.io.Serializable memberGrouppkey = memberGroupId;
				System.out.println("member group id : "+memberGroupId);
				String[] memberGroupRequired = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
				memberGroupObject = memberGroupService.get(memberGrouppkey, memberGroupRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member group object : "+memberGroupObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			Payment paymentObject = null;
			
			if (paymentId != null){
				try
				{
				java.io.Serializable groupKey = paymentId;
				System.out.println("member payment id : "+paymentId);
				String[] requiredGroup = {"Payment.OutstandingId", "Payment.MemberId","Payment.ProviderId"};
				paymentObject = paymentService.get(groupKey,requiredGroup);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			request.setAttribute("status",searchStatus);
			result.addObject("ExcessCharges", collection);
			result.addObject("memberId",memberId);
			result.addObject("memberGroup", memberGroupObject);
			result.addObject("payment", paymentObject);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortCounter", sortCounter);
			request.setAttribute("sortExcessCharge", sortExcessCharge);
			request.setAttribute("sortExcessDate", sortExcessDate);
			request.setAttribute("sortExcessNumber", sortExcessNumber);
			request.setAttribute("sortMemberGroup", sortMemberGroup);
			request.setAttribute("sortMemberName", sortMemberName);
			request.setAttribute("sortMemberNo", sortMemberNo);
			request.setAttribute("sortReminderDate", sortReminderDate);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortRemarks", sortRemarks);

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

		String breadcrumb = "";
		try {
			
			//modified by aju on 20150929, new iFrame checker fufufu plus init
			isIFrameSession = securityService.isUsingIFrameSession(request);
			iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
			System.out.println("securityService->SessionMemberId->" + securityService.getTheSessionMemberId());
			sessionMemberId = securityService.getTheSessionMemberId();
			System.out.println("securityService->SessionParentMemberId->" + securityService.getTheSessionMemberParentId());
			sessionMemberParentId = securityService.getTheSessionMemberParentId();

			
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailExcessCharge");
				String excessChargeId = request.getParameter("excessChargeId");
				breadcrumb = "<a href=\"excesscharge?navigation=detail&excessChargeId="+excessChargeId+"\" class=\"linkbreadcrumb\">Detail Excess Charge</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("bad")){
				// untuk handle bad excess charge !
				result = badPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchExcessCharge");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupExcessCharge");
			}
			else if (navigation.equalsIgnoreCase("exportrecap")){
				result = exportExcessRecapPerformed(request, response, "searchExcessCharge");
			}
			else if (navigation.equalsIgnoreCase("confirmsend")){
				result = confirmSendPerformed(request,response);
			}
			else if (navigation.equalsIgnoreCase("saveinvoice")){
				result = saveInvoicePerformed(request,response);
			}
			else if (navigation.equalsIgnoreCase("export")) {
				result = exportPerformed(request, response, "searchExcessCharge");
			}
			else if (navigation.equalsIgnoreCase("print")){
				result = printPerformed(request, response, "printExcessCharge");
			}
			else if (navigation.equalsIgnoreCase("list") || subnavigation.equalsIgnoreCase("list") || navigation.equalsIgnoreCase("golist")){
				result = searchPerformed (request, response, "listMemberExcessCharge");
				String memberId = request.getParameter("memberId");
				breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Excess Charge";
			}
			else if (navigation.equalsIgnoreCase("listgroup") || subnavigation.equalsIgnoreCase("listgroup")){
				result = searchPerformed(request, response, "listGroupExcessCharge");
				String groupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+groupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Excess Charge";
			}
			else if (navigation.equalsIgnoreCase("listpayment") || subnavigation.equalsIgnoreCase("listpayment")){
				result = searchPerformed(request, response, "listPaymentExcessCharge");
				String paymentId = request.getParameter("paymentId");
				breadcrumb = "<a href=\"payment?navigation=detail&paymentId="+paymentId+"\" class=\"linkbreadcrumb\">Detail Payment</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Excess Charge";
			}
			else if (navigation.equalsIgnoreCase("exportheader")){
				result = exportHeaderPerformed(request, response, "searchExcessCharge");
			}
			else if (navigation.equalsIgnoreCase("jsontotalopenexcess") || navigation.equalsIgnoreCase("jsontotalreminder")){
				result = jsonPendingPerformed(request, response, "jsonTotalPendingExcess");				
			}
			else if (navigation.equalsIgnoreCase("searchunassigned")){
				result = searchPerformed(request, response, "searchUnassignedExcessCharge");
			}
			else if (navigation.equalsIgnoreCase("listbatch") || navigation.equalsIgnoreCase("golistbatch")){
				result = searchPerformed(request, response, "listBatchExcessCharge");
				String batchClaimId = request.getParameter("batchClaimId");				
				breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="+batchClaimId+"\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Batch Excess Charge";
			}
			else if (navigation.equalsIgnoreCase("listinvoice")){
				result = searchPerformed(request,response,"listInvoiceExcessCharge");
			}
			else {
				result = searchPerformed(request, response,
						"searchExcessCharge");
				breadcrumb = "<a href=\"excesscharge?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Excess Charge</a>";
			}
			result.addObject("breadcrumb", breadcrumb);
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
