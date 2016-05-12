package com.ametis.cms.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ametis.cms.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ContactPerson;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentBatch;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.PaymentReportDetail;
import com.ametis.cms.dto.PaymentReportSummary;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BankService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ContactPersonService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.PaymentBatchService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Payment is a servlet controller for payment Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class PaymentController implements Controller

// extends+

// extends-

{

	private PaymentService paymentService;

	private UserService userService;

	private ClaimService claimService;
	private ClaimItemService claimItemService;
	private PaymentBatchService paymentBatchService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	private ConfigurationService configurationService;
	private BankService bankService;
	private ExcessChargeService excessChargeService;

	private SecurityService securityService;
private ActivityLogService logService;
	private BatchClaimService batchClaimService;

	private ContactPersonService contactPersonService;
	
	
	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

	public PaymentBatchService getPaymentBatchService() {
		return paymentBatchService;
	}

	public void setPaymentBatchService(PaymentBatchService paymentBatchService) {
		this.paymentBatchService = paymentBatchService;
	}

	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
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

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public PaymentService getPaymentService() {
		return this.paymentService;
	}

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = paymentId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEPAYMENT");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEPAYMENT access");
				return errorResult;

			}
			Payment res = paymentService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.payment", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.payment", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchPayment");
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

	public ModelAndView deliverPaymentPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			java.io.Serializable pkey = paymentId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEPAYMENT");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEPAYMENT access");
				return errorResult;

			}
			Payment res = paymentService.get(pkey);

			if (res != null) {
				res = paymentService.deliverPayment(res, user);

			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.payment", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchPayment");
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
	public ModelAndView jsonTotalPendingPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			User user = securityService.getCurrentUser(request);
			
			String navigation = request.getParameter("navigation");
		
			int total = 0;
			
			
			result = new ModelAndView("jsonTotalPendingPayment");
			
			if (navigation.equalsIgnoreCase("jsontotalpending")){
				
				if (user != null ){
					if (user.getUserType().intValue() == User.TPA){
						String[] eqParam = {"deletedStatus","paymentStatus.paymentStatusId"};
						Object[] eqValue = {Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN};
						
						total = paymentService.getTotal(null,null,eqParam,eqValue);
					}
					else if (user.getUserType().intValue() == User.CLIENT){
						String[] eqParam = {"deletedStatus","paymentStatus.paymentStatusId","batchClaim.clientId.clientId"};
						Object[] eqValue = {Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN, user.getClientId().getClientId()};
						
						total = paymentService.getTotal(null,null,eqParam,eqValue);
					}
				}
			}
			else if (navigation.equalsIgnoreCase("jsontotalunbatched")){
				if (user.getUserType().intValue() == User.TPA){
					String[] eqParam = {"deletedStatus","paymentStatus.paymentStatusId","paymentBatchingStatus"};
					Object[] eqValue = {Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN,Integer.valueOf(0)};
					
					total = paymentService.getTotal(null,null,eqParam,eqValue);
				}
				else if (user.getUserType().intValue() == User.CLIENT){
					String[] eqParam = {"deletedStatus","paymentStatus.paymentStatusId","batchClaim.clientId.clientId","paymentBatchingStatus"};
					Object[] eqValue = {Integer.valueOf(0),PaymentStatus.PAYMENT_OPEN, user.getClientId().getClientId(),Integer.valueOf(0)};
					
					total = paymentService.getTotal(null,null,eqParam,eqValue);
				}
			}
			else if (navigation.equalsIgnoreCase("jsonsentpayment")){
				if (user.getUserType().intValue() == User.TPA){
					String[] eqParam = {"deletedStatus","paymentStatus.paymentStatusId","paymentBatchingStatus"};
					Object[] eqValue = {Integer.valueOf(0),PaymentStatus.PAYMENT_DISPOSITION,Integer.valueOf(0)};
					
					total = paymentService.getTotal(null,null,eqParam,eqValue);
				}
				else if (user.getUserType().intValue() == User.CLIENT){
					String[] eqParam = {"deletedStatus","paymentStatus.paymentStatusId","batchClaim.clientId.clientId","paymentBatchingStatus"};
					Object[] eqValue = {Integer.valueOf(0),PaymentStatus.PAYMENT_DISPOSITION, user.getClientId().getClientId(),Integer.valueOf(0)};
					
					total = paymentService.getTotal(null,null,eqParam,eqValue);
				}
			}
			
			result.addObject("result", total);
			

			
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
			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");
			
			

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			ActionUser user = securityService.getActionUser(request);

			result = new ModelAndView(location);
			java.io.Serializable pkey = paymentId;
			Payment object = paymentService.get(pkey);

			if (object == null) {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"not.found.payment", null, "fail", Locale
										.getDefault()));
			}

			String[] eqParam = { "claimId.batchClaimId.batchClaimId" };
			Object[] eqValue = { object.getBatchClaim().getBatchClaimId() };
			int totalExcess = excessChargeService.getTotal(null, null, eqParam,
					eqValue);

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			if (object != null && navigation.equalsIgnoreCase("confirm")) {
				String[] claimList = WebUtil.getParameterStringArray(request, "claimList");
				object = paymentService.finalize(object,claimList, user);
			}
			
			//Add 20150421 by FVO, for print Delivery Letter (Surat Jalan)
			if(navigation.equalsIgnoreCase("printdeliveryletter")){
				Collection<Object[]> coll = claimService.generateClaimDownloadRecap(object.getPaymentId());
				result.addObject("claimCollection", coll);
				if(object.getBatchClaim()!=null){
					long totPendingAndReject = claimService.getTotalClaimPendingReject(object.getBatchClaim().getBatchClaimId());
					java.io.Serializable batchKey = object.getBatchClaim().getBatchClaimId();
					String[] required = {"BatchClaim.PaymentRecipient","BatchClaim.BatchClaimStatus",
							"BatchClaim.PaymentMethod","BatchClaim.ClaimCurrency","BatchClaim.PaymentCurrency",
							"BatchClaim.ProviderId","BatchClaim.MemberId","BatchClaim.MemberGroupId", "BatchClaim.ClientId", "BatchClaim.BatchClaimType"};
					BatchClaim batchObj = batchClaimService.get(batchKey,required);
					result.addObject("batchObj", batchObj);
					result.addObject("totPendingAndReject", totPendingAndReject);
					
					ContactPerson contactPerson = null;
		
					String[] sEqP = {"deletedStatus","clientId.clientId","paymentOfficer"};
					Object[] sEqQ = {0,batchObj.getClientId().getClientId(),1};
					
					
					
					Collection<ContactPerson> contactList = contactPersonService.search(null,null,sEqP,sEqQ,0,1);
					if (contactList != null && !contactList.isEmpty()){
						Iterator<ContactPerson> iterator = contactList.iterator();
						
						if (iterator.hasNext()){
							contactPerson = iterator.next();
						}
					}
					
					result.addObject("contactPerson", contactPerson);
					
				}
			}
			
			
			
			result.addObject("payment", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("rowset", rowset);
			result.addObject("totalExcess", Integer.valueOf(totalExcess));
			
			//Add 20150420 by FVO, for print delivery letter (Surat jalan)
			if(navigation.equalsIgnoreCase("printdownloadrecap")){
				Collection<Object[]> coll = claimService.generateClaimDownloadRecap(object.getPaymentId());
				long totPendingAndReject = claimService.getTotalClaimPendingReject(object.getBatchClaim().getBatchClaimId());
				result.addObject("claimCollection", coll);
				result.addObject("totPendingAndReject", totPendingAndReject);
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

	public ModelAndView printPaymentPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {

		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);
			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			java.sql.Date currentDate = new java.sql.Date(System
					.currentTimeMillis());
			

			Configuration configuration = configurationService
					.getSystemConfiguration();
			String institution = configuration.getCompanyCode().toLowerCase();
			location = location + "_" + institution;

			result = new ModelAndView(location);
			java.io.Serializable pkey = paymentId;
			String preparedBy = "";
			Payment object = paymentService.get(pkey);

			if (object == null) {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"not.found.payment", null, "fail", Locale
										.getDefault()));
			}

			BatchClaim bc = object.getBatchClaim();
			String[] requiredC = {"Claim.MemberId","Claim.MemberId.MemberGroupId","Claim.MemberGroupId","Claim.ClientId","Claim.ProviderId"};

			if (bc != null) {
				String[] eqP = { "batchClaimId.batchClaimId", "deletedStatus" };
				Object[] eqQ = { bc.getBatchClaimId(), Integer.valueOf(0) };

				int totalClaim = claimService.getTotal(null, null,"batchClaimId.batchClaimId", bc.getBatchClaimId());

				Collection<Claim> claims = claimService.search(null, null, eqP,	eqQ,requiredC, 0, totalClaim);

				request.setAttribute("Claims", claims);
			}

			if (user != null) {
				User theUser = user.getUser();

				if (theUser != null) {
					preparedBy = theUser.getUsername().toUpperCase() + "";
				}
			}


			result.addObject("payment", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("currentDate", currentDate);
			result.addObject("preparedBy", preparedBy);
			result.addObject("configuration", configuration);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	public ModelAndView printCashCreditPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {

		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);
			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			java.sql.Date currentDate = new java.sql.Date(System
					.currentTimeMillis());
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			Configuration configuration = configurationService
					.getSystemConfiguration();
			String institution = configuration.getCompanyCode().toLowerCase();
			location = location + "_" + institution;

			result = new ModelAndView(location);
			java.io.Serializable pkey = paymentId;
			String preparedBy = "";
			Payment object = paymentService.get(pkey);

			if (object == null) {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"not.found.payment", null, "fail", Locale
										.getDefault()));
			}

			BatchClaim bc = object.getBatchClaim();

			if (bc != null) {
				String[] eqP = { "batchClaimId.batchClaimId", "deletedStatus" };
				Object[] eqQ = { bc.getBatchClaimId(), Integer.valueOf(0) };
				int totalClaim = claimService.getTotal(null, null,
						"batchClaimId.batchClaimId", bc.getBatchClaimId());

				Collection<Claim> claims = claimService.search(null, null, eqP,
						eqQ, 0, totalClaim);

				request.setAttribute("Claims", claims);
			}

			if (user != null) {
				User theUser = user.getUser();

				if (theUser != null) {
					preparedBy = theUser.getUsername().toUpperCase() + "";
				}
			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("payment", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("currentDate", currentDate);
			result.addObject("preparedBy", preparedBy);
			result.addObject("configuration", configuration);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	public ModelAndView printPaymentDetailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {

		ModelAndView result = null;

		try {

			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			

			Configuration configuration = configurationService
					.getSystemConfiguration();
			String institution = configuration.getCompanyCode().toLowerCase();

			location = location + "_" + institution;
			result = new ModelAndView(location);
			java.io.Serializable pkey = paymentId;
			String officerName = "";

			String reimbursementPolicyGroupNumber = "";
			String reimbursementPolicyGroupName = "";
			String reimbursementKantorCabang = "";

			ActionUser actionUser = securityService.getActionUser(request);

			String[] required = {"Payment.BatchClaim","Payment.BatchClaim.MemberId","Payment.BatchClaim.MemberGroupId","Payment.BatchClaim.MemberGroupId.CurrentActivePolicyId","Payment.BatchClaim.ProviderId",
					"Payment.BatchClaim.MemberId.MemberGroupId.ClientId"};
			Payment object = paymentService.get(pkey,required);

			if (object == null) {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"not.found.payment", null, "fail", Locale
										.getDefault()));
			}

			Collection<PaymentReportDetail> reports = null;
			double pembulatan = 0;
			double pembayaranDimuka = 0;
			if (object != null) {
				BatchClaim batchClaim = object.getBatchClaim();

				result.addObject("batchClaim", batchClaim);

				if (batchClaim != null) {
					reports = claimService.getPaymentReport(object.getPaymentId());

					PaymentRecipient paymentRecipient = batchClaim.getPaymentRecipient();
					
					PaymentReportSummary reportSummary = claimItemService
							.getReportSummaryByPayment(object.getPaymentId());

					if (reportSummary != null) {
						System.out.println(reportSummary);
					}

					String insurancePeriode = "";

					if (paymentRecipient != null) {
						if (paymentRecipient.getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER) {
							insurancePeriode = batchClaim.getMemberId()
									.getEffectiveDate().toString()
									+ " s/d "
									+ batchClaim.getMemberId().getExpireDate()
											.toString();
							reimbursementKantorCabang = batchClaim
									.getMemberId().getMemberGroupId()
									.getClientId().getClientName();
							reimbursementPolicyGroupName = batchClaim
									.getMemberId().getMemberGroupId()
									.getGroupName();
							reimbursementPolicyGroupNumber = batchClaim
									.getMemberId().getMemberGroupId()
									.getPolicyNumber();
						} else if (paymentRecipient.getPaymentRecipientId()
								.intValue() == PaymentRecipient.MEMBER_GROUP) {
							insurancePeriode = batchClaim.getMemberGroupId()
									.getCurrentActivePolicyId().getEffectiveDate().toString()
									+ " s/d "
									+ batchClaim.getMemberGroupId()
											.getCurrentActivePolicyId().getExpireDate().toString();

							reimbursementKantorCabang = batchClaim
									.getMemberGroupId().getClientId()
									.getClientName();
							reimbursementPolicyGroupName = batchClaim
									.getMemberGroupId().getGroupName();
							reimbursementPolicyGroupNumber = batchClaim
									.getMemberGroupId().getPolicyNumber();
						} else if (paymentRecipient.getPaymentRecipientId()
								.intValue() == PaymentRecipient.PROVIDER) {
							insurancePeriode = batchClaim.getProviderId()
									.getContractStartDate().toString()
									+ " s/d "
									+ batchClaim.getProviderId()
											.getContractEndDate().toString();
						}

						request.setAttribute("insurancePeriode",
								insurancePeriode);
					}

					if (reports != null) {
						Iterator<PaymentReportDetail> report = reports
								.iterator();
						double claimCharge = 0.0;
						double claimApproved = 0.0;
						double claimRejected = 0.0;
						double claimExcess = 0.0;
						double claimExGratia = 0.0;

						if (report != null) {
							Claim claim = null;
							PaymentReportDetail payReport = null;
							while (report.hasNext()) {
								payReport = report.next();

								if (payReport != null) {
									claim = payReport.getClaim();

									if (claim.getClaimChargeValue() != null)
										claimCharge += claim
												.getClaimChargeValue();

									if (claim.getClaimApprovedValue() != null)
										claimApproved += claim
												.getClaimApprovedValue();

									if (claim.getClaimExcessValue() != null)
										claimExcess += claim
												.getClaimExcessValue();

									if (claim.getExGratiaValue() != null)
										claimExGratia += claim
												.getExGratiaValue();

									if (claim != null) {
										if (claim.getPembulatan() != null) {
											pembulatan += claim.getPembulatan();
										}
										if (claim.getPembayaranDimuka() != null) {
											pembayaranDimuka += claim
													.getPembayaranDimuka();
										}
									}
								}
							}
						}
					}
				}

			}

			if (actionUser != null) {
				User theUser = actionUser.getUser();

				if (theUser != null) {
					officerName = theUser.getFirstName();

				}
			}

			result.addObject("configuration", configuration);
			result.addObject("reports", reports);
			result.addObject("pembulatan", pembulatan);
			result.addObject("payment", object);
			result.addObject("pembayaranDimuka", pembayaranDimuka);
			result.addObject("officerName", officerName);
			result.addObject("reimbursementKantorCabang",
					reimbursementKantorCabang);
			result.addObject("reimbursementPolicyGroupNumber",
					reimbursementPolicyGroupNumber);
			result.addObject("reimbursementPolicyGroupName",
					reimbursementPolicyGroupName);

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

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			String searchstatus = WebUtil.getParameterString(request, "searchstatus","");
			Date minimumDate = WebUtil
					.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil
					.getParameterDate(request, "maximum_date");
			
			String dateBy = WebUtil.getParameterString(request, "dateBy", "");
			Integer claimType  = WebUtil.getParameterInteger(request, "claimType");

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortInvoiceNo = true, sortPaymentNo = true, sortBatchNo = true, sortBank = true, sortAccountNo = true,
					sortPayeeName = true, sortDueDate = true, sortPaymentDate = true, sortStatus = true;
			boolean order = true;
			
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			boolean isExportDeliver = navigation.equals("exportdeliver");
			
			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golistbatch") ||
					isExportDeliver || navigation.equalsIgnoreCase("listbatch") ||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) {	
					//created by andre
					if (searchby.equalsIgnoreCase("invoiceNumber")) {
						vLikeP.add("batchClaim.invoiceNumber");
						vLikeQ.add(searchtext);
					}
					//created by andre //
					if (searchby.equalsIgnoreCase("bankName")) {
						vLikeP.add("bankName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("giroNumber")) {
						vLikeP.add("giroNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("payeeName")) {
						vLikeP.add("payeeName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("paymentNumber")) {
						vLikeP.add("paymentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaim.batchClaimNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("officer")) {
						vLikeP.add("createdBy");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("batchClaim.memberId.firstName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberNumber")) {
						vLikeP.add("batchClaim.memberId.customerPolicyNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("batchClaim.providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("batchClaim.clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("batchClaim.memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
				}
				
				if (searchstatus != null && !searchstatus.equalsIgnoreCase("")){
					vEqP.add("paymentStatus.paymentStatusId");
					vEqQ.add(Integer.valueOf(searchstatus));
				}
			}
			
			if (claimType != null){
				vEqP.add("batchClaim.batchClaimType.claimTypeId");
				vEqQ.add(claimType);
			}

			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberGroup.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {
					vEqP.add("batchClaim.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("batchClaim.providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}

			}
			if (navigation.equalsIgnoreCase("searchpending")){
				vEqP.add("paymentStatus.paymentStatusId");
				vEqQ.add(PaymentStatus.PAYMENT_OPEN);
			}
			if (navigation.equalsIgnoreCase("listbatch") || navigation.equalsIgnoreCase("golistbatch")){
				vEqP.add("batchClaim.batchClaimId");
				vEqQ.add(batchClaimId);
				
				String[] reqBatch = {"BatchClaim.PaymentRecipient","BatchClaim.ProviderId","BatchClaim.MemberGroupId",
						"BatchClaim.MemberId","BatchClaim.BatchClaimStatus","BatchClaim.PaymentMethod","BatchClaim.ClaimCurrency"};
				BatchClaim batchClaim = batchClaimService.get(batchClaimId,reqBatch);
				if (batchClaim != null){
					result.addObject("batchClaim", batchClaim);
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

			String[] searchByDate = { dateBy};
			Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };

			if (minimumDate != null && maximumDate != null ) {
				if (minimumDate.toString().equals("1970-01-01") && maximumDate.toString().equals("1970-01-01")){
					count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
				}
				else {
					count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray);
				}
			} else {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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

			String required[] = new String[] {"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
					"Payment.BatchClaim", "Payment.BatchClaim.ProviderId", "Payment.BatchClaim.BatchClaimType","Payment.BatchClaim.MemberGroupId",
					"Payment.MemberId.MemberGroupId", "Payment.OutstandingId"
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					//created by andre
					if(sortcolumn.equalsIgnoreCase("invoiceNumber")){
						sortByColumn = "invoiceNumber";
						Boolean sortOrderInvoiceNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderInvoiceNo", ""));
						sortInvoiceNo = !sortOrderInvoiceNo;
						order = sortInvoiceNo;
						//created by andre//
					}else if(sortcolumn.equalsIgnoreCase("paymentnumber")){
							sortByColumn = "paymentNumber";
							Boolean sortOrderPayNo = Boolean.valueOf(WebUtil.
									getParameterString(request, "sortOrderPayNo", ""));
							sortPaymentNo = !sortOrderPayNo;
							order = sortPaymentNo;
					}else if(sortcolumn.equalsIgnoreCase("batchnumber")){
						sortByColumn = "batchClaim.batchClaimNumber";
						Boolean sortOrderBatchNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderBatchNo", ""));
						sortBatchNo = !sortOrderBatchNo;
						order = sortBatchNo;
					}else if(sortcolumn.equalsIgnoreCase("bankname")){
						sortByColumn = "bankName";
						Boolean sortOrderBank = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderBank", ""));
						sortBank = !sortOrderBank;
						order = sortBank;
					}else if(sortcolumn.equalsIgnoreCase("accountnumber")){
						sortByColumn = "accountNumber";
						Boolean sortOrderAccNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderAccNo", ""));
						sortAccountNo = !sortOrderAccNo;
						order = sortAccountNo;
					}else if(sortcolumn.equalsIgnoreCase("payeename")){
						sortByColumn = "payeeName";
						Boolean sortOrderPayeeName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderPayeeName", ""));
						sortPayeeName = !sortOrderPayeeName;
						order = sortPayeeName;
					}else if(sortcolumn.equalsIgnoreCase("duedate")){
						sortByColumn = "batchClaim.batchDueDate";
						Boolean sortOrderDueDate = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderDueDate", ""));
						sortDueDate = !sortOrderDueDate;
						order = sortDueDate;
					}else if(sortcolumn.equalsIgnoreCase("paymentdate")){
						sortByColumn = "paymentConfirmDate";
						Boolean sortOrderPayDate = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderPayDate", ""));
						sortPaymentDate = !sortOrderPayDate;
						order = sortPaymentDate;
					}else{
						sortByColumn = "paymentStatus.paymentStatusName";
						Boolean sortOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderStatus", ""));
						sortStatus = !sortOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					//created by andre
					if(sortcolumn.equalsIgnoreCase("invoiceNumber")){
						sortInvoiceNo = order;
					//created by andre//
					}else if(sortcolumn.equalsIgnoreCase("paymentnumber")){
						sortPaymentNo = order;
					}else if(sortcolumn.equalsIgnoreCase("batchnumber")){
						sortBatchNo = order;
					}else if(sortcolumn.equalsIgnoreCase("bankname")){
						sortBank = order;
					}else if(sortcolumn.equalsIgnoreCase("accountnumber")){
						sortAccountNo = order;
					}else if(sortcolumn.equalsIgnoreCase("payeename")){
						sortPayeeName = order;
					}else if(sortcolumn.equalsIgnoreCase("duedate")){
						sortDueDate = order;
					}else if(sortcolumn.equalsIgnoreCase("paymentdate")){
						sortPaymentDate = order;
					}else{
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if (minimumDate != null && maximumDate != null &&
						!minimumDate.toString().equals("1970-01-01") &&
						!maximumDate.toString().equals("1970-01-01")) {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray, order, sortByColumn,
						required,
							isExportDeliver ? 0 : rowsetint,
							isExportDeliver ? count : countSet.intValue());
				} else {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
							required,
							isExportDeliver ? 0 : rowsetint,
							isExportDeliver ? count : countSet.intValue());
				}

			}else{
				if (minimumDate != null && maximumDate != null) {
					if (minimumDate.toString().equals("1970-01-01") && maximumDate.toString().equals("1970-01-01")){
						collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
								required,
								isExportDeliver ? 0 : rowsetint,
								isExportDeliver ? count : countSet.intValue());
					}
					else {
						collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							searchByDate, minimumDateArray, maximumDateArray,
							required,
								isExportDeliver ? 0 : rowsetint,
								isExportDeliver ? count : countSet.intValue());
					}
				} else {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
							required,
							isExportDeliver ? 0 : rowsetint,
							isExportDeliver ? count : countSet.intValue());
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

				if (minimumDate != null && maximumDate != null) {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, searchByDate, minimumDateArray,
							maximumDateArray, required,
							isExportDeliver ? 0 : rowsetint,
							isExportDeliver ? count : countSet.intValue());
				} else {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required,
							isExportDeliver ? 0 : rowsetint,
							isExportDeliver ? count : countSet.intValue());
				}
			}

			Map<Integer, String> memberGroupMap = new HashMap<Integer, String>();
			Map<Integer, Integer> totalClaimMap = new HashMap<Integer, Integer>();

			for (Object object : collection) {
				Payment payment = (Payment) object;

				String memberGroup = "";
				String recipient = "";

				String[] eqParam = {"paymentId.paymentId"};
				Object[] eqValue = {payment.getPaymentId()};

				int totalClaim = claimService.getTotal(null, null, eqParam, eqValue);

				BatchClaim batchClaim = payment.getBatchClaim();

				if (batchClaim != null) {
					if (batchClaim.getPaymentRecipient() != null) {
						if (batchClaim.getPaymentRecipient()
								.getPaymentRecipientId() == PaymentRecipient.MEMBER) {
							recipient = "PESERTA - "
									+ batchClaim.getMemberId()
									.getMemberGroupId()
									.getGroupName();
							memberGroup = batchClaim.getMemberId()
									.getMemberGroupId()
									.getGroupName();

						} else if (batchClaim.getPaymentRecipient()
								.getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP) {
							recipient = batchClaim.getMemberGroupId()
									.getGroupName();
							memberGroup = batchClaim.getMemberGroupId()
									.getGroupName();
						} else {
							recipient = batchClaim.getProviderId()
									.getProviderName();
							memberGroup = "Klaim Rumah Sakit";
						}
					} else {

						Collection<Claim> claims = claimService.search(null, null, eqParam, eqValue, 0, 1);
						for (Claim claim : claims) {
							if (claim.getMemberId() != null) {
								memberGroup = claim
										.getMemberId()
										.getMemberGroupId()
										.getGroupName();
							} else {
								memberGroup = payment
										.getPayeeName();
							}
						}

						// memberGroup = payment.getPayeeName();
					}
				}

				totalClaimMap.put(payment.getPaymentId(), totalClaim);
				memberGroupMap.put(payment.getPaymentId(), memberGroup);
			}

			if(navigation.equals("exportdeliver")) {
				HSSFWorkbook workbook = PaymentReportGenerator.generateReport(collection, memberGroupMap, totalClaimMap);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "paymentReport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}

			request.setAttribute("totalClaimMap", totalClaimMap);
			request.setAttribute("memberGroupMap", memberGroupMap);

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("batchClaimId", batchClaimId);
			request.setAttribute("sortcolumn", sortcolumn);
			request.setAttribute("dateBy", dateBy);
			request.setAttribute("claimType", claimType);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("searchstatus", searchstatus);

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
			
			request.setAttribute("sortInvoiceNo", sortInvoiceNo);
			request.setAttribute("sortAccountNo", sortAccountNo);
			request.setAttribute("sortBank", sortBank);
			request.setAttribute("sortBatchNo", sortBatchNo);
			request.setAttribute("sortDueDate", sortDueDate);
			request.setAttribute("sortPayeeName", sortPayeeName);
			request.setAttribute("sortPaymentDate", sortPaymentDate);
			request.setAttribute("sortPaymentNo", sortPaymentNo);
			request.setAttribute("sortStatus", sortStatus);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchPendingPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

		

			String batchClaimId = WebUtil.getParameterString(request,
					"batchClaimId", "");
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();
			if (navigation.equals("gosearchpending") || navigation.equals("gosearchdelivered")) {
				if (searchby != null) {
					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankName")) {
						vLikeP.add("bankName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("giroNumber")) {
						vLikeP.add("giroNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("payeeName")) {
						vLikeP.add("payeeName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("paymentNumber")) {
						vLikeP.add("paymentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaim.batchClaimNumber");
						vLikeQ.add(searchtext);
					}
				}
			}

			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberGroup.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {
					vEqP.add("batchClaim.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("batchClaim.providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}

			}
			if (navigation.equalsIgnoreCase("searchpending")){
				vEqP.add("paymentStatus.paymentStatusId");
				vEqQ.add(PaymentStatus.PAYMENT_OPEN);
			}
			if (navigation.equalsIgnoreCase("searchdelivered")){
				vEqP.add("paymentStatus.paymentStatusId");
				vEqQ.add(PaymentStatus.PAYMENT_DISPOSITION);
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

			
			
			count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			

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
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId","Payment.BatchClaim","Payment.BatchClaim.ProviderId","Payment.BatchClaim.BatchClaimType"
			// foreign affairs end
			};

			
			collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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

				
				collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, rowsetint, countSet.intValue());
				
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("batchClaimId", batchClaimId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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

	public ModelAndView searchConfirmPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			String batchClaimId = WebUtil.getParameterString(request,
					"batchClaimId", "");
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			if (navigation.equals("gosearchconfirm")
					|| navigation.equals("golookupconfirm")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankName")) {
						vLikeP.add("bankName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("giroNumber")) {
						vLikeP.add("giroNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("payeeName")) {
						vLikeP.add("payeeName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("paymentNumber")) {
						vLikeP.add("paymentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaim.batchClaimNumber");
						vLikeQ.add(searchtext);
					}
				}

			}

			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {
					vEqP.add("batchClaim.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("batchClaim.providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}

			}
			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			vEqP.add("paymentStatus.paymentStatusId");
			vEqQ.add(new Integer(PaymentStatus.PAYMENT_DISPOSITION));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
			// foreign affairs end
			};

			// collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
			// required, rowsetint, countSet.intValue());
			collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, count);

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

				// collection = paymentService.search(sLikeP, sLikeQ, sEqP,
				// sEqQ,
				// required, rowsetint, countSet.intValue());
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, 0, count);
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("batchClaimId", batchClaimId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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

	public ModelAndView searchBatchingPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			String batchClaimId = WebUtil.getParameterString(request,
					"batchClaimId", "");
			
			String clientId = WebUtil.getParameterString(request, "clientId", "");
			String clientName = WebUtil.getParameterString(request, "clientName", "");
			
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			if (navigation.equals("gosearchbatching")
					|| navigation.equals("golookupconfirm")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankName")) {
						vLikeP.add("bankName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("giroNumber")) {
						vLikeP.add("giroNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("payeeName")) {
						vLikeP.add("payeeName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("paymentNumber")) {
						vLikeP.add("paymentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaim.batchClaimNumber");
						vLikeQ.add(searchtext);
					}
				}

			}


			
			if (clientId != null && !clientId.equalsIgnoreCase("")){
				vEqP.add("batchClaim.clientId.clientId");
				vEqQ.add(new Integer(clientId));
				vEqP.add("deletedStatus");
				vEqQ.add(new Integer(0));
			}
			else {
				vEqP.add("deletedStatus");
				vEqQ.add(new Integer(3));
			}

			vEqP.add("paymentStatus.paymentStatusId");
			vEqQ.add(new Integer(PaymentStatus.PAYMENT_DISPOSITION));

			vEqP.add("paymentBatchingStatus");
			vEqQ.add(new Integer(0));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
			// foreign affairs end
			};

			// collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
			// required, rowsetint, countSet.intValue());
			collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, count);

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

				// collection = paymentService.search(sLikeP, sLikeQ, sEqP,
				// sEqQ,
				// required, rowsetint, countSet.intValue());
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, 0, count);
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("batchClaimId", batchClaimId);
			request.setAttribute("clientName", clientName);
			request.setAttribute("clientId", clientId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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

	public ModelAndView searchPaidPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Date minimumDate = WebUtil
					.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil
					.getParameterDate(request, "maximum_date");

			String batchClaimId = WebUtil.getParameterString(request,
					"batchClaimId", "");
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
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

					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankName")) {
						vLikeP.add("bankName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("giroNumber")) {
						vLikeP.add("giroNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("payeeName")) {
						vLikeP.add("payeeName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("paymentNumber")) {
						vLikeP.add("paymentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaim.batchClaimNumber");
						vLikeQ.add(searchtext);
					}
				}

			}

			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {
					vEqP.add("batchClaim.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("batchClaim.providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}

			}
			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			vEqP.add("paymentStatus.paymentStatusId");
			vEqQ.add(new Integer(PaymentStatus.PAYMENT_PAID));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String[] searchByDate = { "paymentTime" };
			Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };

			if (minimumDate != null && maximumDate != null) {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray);
			} else {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
			// foreign affairs end
			};

			if (minimumDate != null && maximumDate != null) {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray,
						required, rowsetint, countSet.intValue());
			} else {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
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

				if (minimumDate != null && maximumDate != null) {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, searchByDate, minimumDateArray,
							maximumDateArray, required, rowsetint, countSet
									.intValue());
				} else {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, rowsetint, countSet.intValue());
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("batchClaimId", batchClaimId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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
	public ModelAndView searchPaymentReportPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Integer clientId = WebUtil.getParameterInteger(request,"clientId");
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");			
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			String clientName = WebUtil.getParameterString(request, "clientName", "");
			String memberGroupName = WebUtil.getParameterString(request, "memberGroupName", "");
			String providerName = WebUtil.getParameterString(request, "providerName", "");

			Date minimumDate = WebUtil
					.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil
					.getParameterDate(request, "maximum_date");

			String batchClaimId = WebUtil.getParameterString(request,
					"batchClaimId", "");
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();


			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {
					vEqP.add("batchClaim.clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("batchClaim.providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}
			}
			
			if (clientId != null){
				vEqP.add("batchClaim.clientId.clientId");
				vEqQ.add(clientId);
			}
			if (providerId != null){
				vEqP.add("batchClaim.providerId.providerId");
				vEqQ.add(providerId);
			}
			
			if (memberGroupId != null){
				vEqP.add("batchClaim.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
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

			String[] searchByDate = { "paymentTime" };
			Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };

			if (minimumDate != null && maximumDate != null) {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray);
			} else {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
			// foreign affairs end
			};

			if (minimumDate != null && maximumDate != null) {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray,
						required, rowsetint, countSet.intValue());
			} else {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
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

				if (minimumDate != null && maximumDate != null) {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, searchByDate, minimumDateArray,
							maximumDateArray, required, rowsetint, countSet
									.intValue());
				} else {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, rowsetint, countSet.intValue());
				}
			}

			request.setAttribute("searchtext", searchtext);
			
			request.setAttribute("memberGroupName", memberGroupName);
			request.setAttribute("providerName", providerName);
			request.setAttribute("clientName", clientName);
			request.setAttribute("clientId", clientId);
			request.setAttribute("memberGroupId", memberGroupId);
			request.setAttribute("providerId", providerId);
			
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("batchClaimId", batchClaimId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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

	public ModelAndView claimReportPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Date minimumDate = WebUtil
					.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil
					.getParameterDate(request, "maximum_date");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
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

					if (searchby.equalsIgnoreCase("remarks")) {
						vLikeP.add("remarks");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("bankName")) {
						vLikeP.add("bankName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("accountNumber")) {
						vLikeP.add("accountNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("giroNumber")) {
						vLikeP.add("giroNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("payeeName")) {
						vLikeP.add("payeeName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("paymentNumber")) {
						vLikeP.add("paymentNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaim.batchClaimNumber");
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

			String[] searchByDate = { "paymentTime" };
			Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };

			if (minimumDate != null && maximumDate != null) {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray);
			} else {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
			// foreign affairs end
			};

			if (minimumDate != null && maximumDate != null) {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray,
						required, rowsetint, countSet.intValue());
			} else {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
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

				if (minimumDate != null && maximumDate != null) {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, searchByDate, minimumDateArray,
							maximumDateArray, required, rowsetint, countSet
									.intValue());
				} else {
					collection = paymentService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, rowsetint, countSet.intValue());
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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

	public ModelAndView exportDeliverPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer paymentBatchId = WebUtil.getParameterInteger(request,
					"paymentBatchId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			if (paymentBatchId != null) {
				vEqP.add("paymentBatchId.id");
				vEqQ.add(paymentBatchId);
			}

			vEqP.add("paymentStatus.paymentStatusId");
			vEqQ.add(new Integer(PaymentStatus.PAYMENT_DISPOSITION));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String required[] = new String[] {
					// foreign affairs
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId","Payment.BatchClaim.BatchClaimType"
					
			// foreign affairs end
			};

			collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, count);

			int idx = 1;
			// String data = "No Receive Date Jatuh Tempo No CDV Group Tipe
			// Claim Penerima Pembayaran Bank Cabang NO Rekening Atas Nama
			// Amount Nomor Invoice Payment Date";
			String data = "No,Receive Date,Due Date,No CDV, Member Group,Jumlah Klaim, Pembayaran, Payee Name,Bank, Account Number, Payee Name,  Amount, Invoice, Payment Date";
			data += "\n\n";
			if (collection != null) {
				Iterator<Payment> iterator = collection.iterator();

				if (iterator != null) {
					Payment payment = null;

					String cdvNumber = "";
					String cdvDate = "";
					String payeeName = "";
					String paymentAmount = "";
					String memberGroup = "";
					String claimType = ""; // Cashless || Reimbursement
					String recipient = "";
					String totalClaim = "";

					while (iterator.hasNext()) {

						payment = iterator.next();

						if (payment != null) {

							BatchClaim batch = payment.getBatchClaim();
							if (batch != null) {

								String[] eqParam = { "batchClaimId.batchClaimId" };
								Object[] eqValue = { batch.getBatchClaimId() };

								int total = claimService.getTotal(null, null,
										eqParam, eqValue);
								totalClaim = total + "";

								claimType = batch.getBatchClaimType()
										.getClaimTypeName();

								if (batch.getPaymentRecipient() != null) {
									if (batch.getPaymentRecipient()
											.getPaymentRecipientId() == PaymentRecipient.MEMBER) {
										recipient = "PESERTA - "
												+ batch.getMemberId()
														.getMemberGroupId()
														.getGroupName();
										memberGroup = batch.getMemberId()
												.getMemberGroupId()
												.getGroupName();

									} else if (batch.getPaymentRecipient()
											.getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP) {
										recipient = batch.getMemberGroupId()
												.getGroupName();
										memberGroup = batch.getMemberGroupId()
												.getGroupName();
									} else {
										recipient = batch.getProviderId()
												.getProviderName();
										memberGroup = "Klaim Rumah Sakit";
									}
								} else {

									Collection<Claim> claims = claimService
											.search(null, null, eqParam,
													eqValue, 0, 1);
									if (claims != null) {
										Iterator<Claim> claimIterator = claims
												.iterator();

										if (claimIterator.hasNext()) {
											Claim claim = claimIterator.next();

											if (claim != null
													&& claim.getMemberId() != null) {
												memberGroup = claim
														.getMemberId()
														.getMemberGroupId()
														.getGroupName();
											} else {
												memberGroup = payment
														.getPayeeName();
											}
										}
									}

									// memberGroup = payment.getPayeeName();
								}
							}

							data += idx + ", " + batch.getBatchClaimDate()
									+ "," + batch.getBatchDueDate() + ","
									+ payment.getPaymentNumber() + ","
									+ memberGroup + "," + totalClaim + ",";
							data += claimType
									+ ","
									+ recipient
									+ ",\""
									+ payment.getBankName()
									+ "\",\'"
									+ payment.getAccountNumber()
									+ ",\""
									+ payment.getPayeeName()
									+ "\",\""
									+ Converter.getMoney(payment
											.getPaymentValue())
									+ "\",\""
									+ payment.getBatchClaim()
											.getInvoiceNumber() + "\","
									+ payment.getPaymentTime();
							data += "\n";
							idx += 1;

						}
					}
				}
			}

			System.out.println("DATA : " + data);

			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setHeader("Content-disposition", "inline; filename="
					+ "cdvDisposition.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));

			sos.write(data.getBytes());
			sos.close();

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView exportDeliverInquiryPerformed(
			HttpServletRequest request, HttpServletResponse response,
			String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Date minimumDate = WebUtil
					.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil
					.getParameterDate(request, "maximum_date");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			vEqP.add("paymentStatus.paymentStatusId");
			vEqQ.add(new Integer(PaymentStatus.PAYMENT_DISPOSITION));

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String[] searchByDate = { "dispositionDate" };
			Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };
			
			

			if (minimumDate != null && maximumDate != null) {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray);
			} else {
				count = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			}

			String required[] = new String[] {
					// foreign affairs
					"Payment.OutstandingId", "Payment.MemberId","Payment.MemberId.MemberGroupId",
					"Payment.MemberGroupId",
					"Payment.BatchClaim.MemberGroupId","Payment.BatchClaim.BatchClaimType","Payment.BatchClaimId.ProviderId"
			// foreign affairs end
			};

			if (minimumDate != null && maximumDate != null) {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						searchByDate, minimumDateArray, maximumDateArray,
						required, 0, count);
			} else {
				collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, 0, count);
			}

			int idx = 1;
			// String data = "No Receive Date Jatuh Tempo No CDV Group Tipe
			// Claim Penerima Pembayaran Bank Cabang NO Rekening Atas Nama
			// Amount Nomor Invoice Payment Date";
			String data = "No|Receive Date|Due Date|No CDV| Member Group| Pembayaran| Payee Name|Bank| Account Number|" +
					" Payee Name|  Amount| Invoice| Payment Date";
			data += "\n\n";
			if (collection != null) {
				Iterator<Payment> iterator = collection.iterator();

				if (iterator != null) {
					Payment payment = null;

					String cdvNumber = "";
					String cdvDate = "";
					String payeeName = "";
					String paymentAmount = "";
					String memberGroup = "";
					String claimType = ""; // Cashless || Reimbursement
					String recipient = "";

					while (iterator.hasNext()) {

						payment = iterator.next();

						if (payment != null) {

							BatchClaim batch = payment.getBatchClaim();
							if (batch != null) {
								claimType = batch.getBatchClaimType()
										.getClaimTypeName();

								if (batch.getPaymentRecipient() != null){
									if (batch.getPaymentRecipient()
											.getPaymentRecipientId() == PaymentRecipient.MEMBER) {
										
										recipient = "PESERTA - "
												+ batch.getMemberId()
														.getMemberGroupId()
														.getGroupName();
										
										memberGroup = batch.getMemberId()
												.getMemberGroupId().getGroupName();
	
									} else if (batch.getPaymentRecipient()
											.getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP) {
										recipient = batch.getMemberGroupId()
												.getGroupName();
										memberGroup = batch.getMemberGroupId()
												.getGroupName();
									} else {
										recipient = batch.getProviderName();
										memberGroup = "Klaim Rumah Sakit";
									}
								}
							}

							data += idx + "| " + batch.getBatchClaimDate()
									+ "|" + batch.getBatchDueDate() + "|"
									+ payment.getPaymentNumber() + "|"
									+ memberGroup + "|";
							data += claimType
									+ "|\""
									+ recipient
									+ "\"|\""
									+ payment.getBankName()
									+ "\"|"
									+ payment.getAccountNumber()
									+ "|\""
									+ payment.getPayeeName()
									+ "\"|\""
									+ Converter.getMoney(payment
											.getPaymentValue())
									+ "\"|"
									+ payment.getBatchClaim()
											.getInvoiceNumber() + "|"
									+ payment.getPaymentTime();
							data += "\n";
							idx += 1;

						}
					}
				}
			}

			System.out.println("DATA : " + data);

			response.setContentType("application/x-csv");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setHeader("Content-disposition", "inline; filename="
					+ "cdvDisposition.csv");

			ServletOutputStream sos = response.getOutputStream();

			response.setHeader("Content-length", Integer
					.toString(data.length()));

			sos.write(data.getBytes());
			sos.close();

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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

	public ModelAndView downloadExcelPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			// vEqP.add("paymentStatus.paymentStatusId");
			// vEqQ.add(new Integer(PaymentStatus.PAYMENT_DISPOSITION));

			String[] requiredBatch = {"Payment.BatchClaim","Payment.BatchClaim.MemberGroupId"};
			Payment payment = paymentService.get(paymentId,requiredBatch);

			String groupName = "";

			if (payment != null) {
				vEqP.add("claimId.batchClaimId.batchClaimId");
				vEqQ.add(payment.getBatchClaim().getBatchClaimId());
				//Add 20150513 by Feiby Veronika, to get claim item with detail payment id
				vEqP.add("claimId.paymentId.paymentId");
				vEqQ.add(payment.getPaymentId());

				if (payment.getBatchClaim().getMemberGroupId() != null) {
					groupName = payment.getBatchClaim().getMemberGroupId()
							.getGroupName();
				}
			}

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String required[] = new String[] {

					"ClaimItem.ClaimId.MemberId","ClaimItem.ClaimId.MemberId.ParentMember",
					"ClaimItem.ItemId","ClaimItem.ClaimId.DiagnosisId","ClaimItem.ClaimId.ProductId","ClaimItem.ClaimId.ProviderId"
			};

			int idx = 1;
			String data = "";

			int total = claimItemService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
			//DISINI
			collection = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, total);

			if (collection != null) {

				// data =
				// BankTransferDocumentConverter.convertToBCA(collection);

				HSSFWorkbook workbook = ClaimDocumentDownloader.downloadExcel(
						collection, groupName, payment);

				java.sql.Date date = new java.sql.Date(System
						.currentTimeMillis());

				String filename = payment.getPaymentId() + ".xls";
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

	public ModelAndView downloadClaimPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));
			
			String[] requiredBatch = {"Payment.BatchClaim","Payment.BatchClaim.MemberGroupId",
					"Payment.BatchClaim.ProviderId","Payment.BatchClaim.MemberId"};

			Payment payment = paymentService.get(paymentId,requiredBatch);
			
			String groupName = "";

			if (payment != null) {
				vEqP.add("batchClaimId.batchClaimId");
				vEqQ.add(payment.getBatchClaim().getBatchClaimId());
				
				if (payment.getBatchClaim().getMemberGroupId() != null){
					groupName = payment.getBatchClaim().getMemberGroupId().getGroupName();
				}

			}

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String required[] = new String[] {
				"Claim.MemberId","Claim.MemberId.ParentMember","Claim.CaseCategoryId","Claim.ClaimStatus"
			};

			int idx = 1;
			String data = "";

			int total = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, total);

			if (collection != null) {

				// data =
				// BankTransferDocumentConverter.convertToBCA(collection);


				HSSFWorkbook workbook = ClaimDocumentDownloader.downloadClaim(
						collection, groupName, payment);

				java.sql.Date date = new java.sql.Date(System
						.currentTimeMillis());

				String filename = payment.getPaymentId() + ".xls";
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

	public ModelAndView exportBankPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			String bankType = WebUtil.getParameterString(request, "bankType",
					"");

			String downloadDate = WebUtil.getParameterString(request,
					"downloadDate", "");

			Integer paymentBatchId = WebUtil.getParameterInteger(request,
					"paymentBatchId");

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));

			/*
			 * if (bankType.equalsIgnoreCase("bca")){ vLikeP.add("bankName");
			 * vLikeQ.add("BCA"); } else if
			 * (bankType.equalsIgnoreCase("mandiri")){ vLikeP.add("bankName");
			 * vLikeQ.add("Mandiri"); } else { }
			 */

			// vEqP.add("paymentStatus.paymentStatusId");
			// vEqQ.add(new Integer(PaymentStatus.PAYMENT_DISPOSITION));
			if (paymentBatchId != null) {
				vEqP.add("paymentBatchId.id");
				vEqQ.add(paymentBatchId);

			}

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			String required[] = new String[] {
					// foreign affairs
					"Payment.OutstandingId", "Payment.MemberId",
					"Payment.ProviderId",
			// foreign affairs end
			};

			int idx = 1;
			String data = "";

			int total = paymentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			collection = paymentService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, total);

			if (bankType.equalsIgnoreCase("bca")) {

				if (collection != null) {

					// data =
					// BankTransferDocumentConverter.convertToBCA(collection);

					java.sql.Date dlDate = java.sql.Date.valueOf(downloadDate);

					HSSFWorkbook workbook = BankTransferDocumentConverter
							.downloadBCA(collection, dlDate);

					java.sql.Date date = new java.sql.Date(System
							.currentTimeMillis());

					String filename = date.toString().replace("-", "")
							+ "-bca.xls";
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + filename);

					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					ServletOutputStream sos = response.getOutputStream();

					workbook.write(sos);
					
					sos.close();
				}
			} else if (bankType.equalsIgnoreCase("mandiri")) {

				if (collection != null) {

					String fromAccount = "";
					String emailContact = "";
					if (paymentBatchId != null) {
						PaymentBatch paymentBatch = paymentBatchService
								.get(paymentBatchId);
						fromAccount = paymentBatch.getPaymentAccountSource()
								.getBankAccount();
					}
					data = BankTransferDocumentConverter.convertToMandiri(
							collection, fromAccount, "IDR", downloadDate,
							bankService,emailContact);

					System.out.println("DATA : " + data);

					response.setContentType("application/x-csv");
					response.setHeader("Pragma", "no-cache");
					response.setHeader("Cache-Control", "no-cache");
					response.setDateHeader("Expires", 0);

					response.setHeader("Content-disposition",
							"inline; filename=" + fromAccount + ".csv");

					ServletOutputStream sos = response.getOutputStream();

					response.setHeader("Content-length", Integer.toString(data
							.length()));

					sos.write(data.getBytes());
					sos.close();
				}
			} else {

			}

			/*
			 * 
			 * System.out.println("DATA : " + data);
			 * 
			 * response.setContentType("application/x-csv");
			 * response.setHeader("Pragma", "no-cache");
			 * response.setHeader("Cache-Control", "no-cache");
			 * response.setDateHeader("Expires", 0);
			 * 
			 * response.setHeader("Content-disposition", "inline; filename=" +
			 * "cdvDisposition.csv");
			 * 
			 * ServletOutputStream sos = response.getOutputStream();
			 * 
			 * response.setHeader("Content-length", Integer
			 * .toString(data.length()));
			 * 
			 * sos.write(data.getBytes()); sos.close();
			 */

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("Payments", collection);

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


		String breadcrumb = "<a href=\"payment?navigation=search&searchtext=&searchby=&rowset=&index=1\" class=\"linkbreadcrumb\">Search Batch Claim</a>";
		try {
			Configuration config = configurationService.getSystemConfiguration();
			if (navigation.equalsIgnoreCase("detail")
					|| navigation.equalsIgnoreCase("confirm")) {
				result = detailPerformed(request, response, "detailPayment");
				String paymentId = request.getParameter("paymentId");
				String index = request.getParameter("index");
				breadcrumb = "<a href=\"payment?navigation=detail&index="+index+"&paymentId="+paymentId+"\" class=\"linkbreadcrumb\">Detail Payment</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")
					|| navigation.equals("exportdeliver")) {
				result = searchPerformed(request, response, "searchPayment");
				breadcrumb = "<a href=\"payment?navigation=search&searchtext=&searchby=&rowset=&index=1\" class=\"linkbreadcrumb\">Search Payment</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupPayment");
			} else if (navigation.equalsIgnoreCase("printpayment")) {
				result = printPaymentPerformed(request, response,
						"claimPaymentForm");
			} else if (navigation.equalsIgnoreCase("deliverpayment")) {
				result = deliverPaymentPerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("jsontotalpending") || navigation.equalsIgnoreCase("jsontotalunbatched") 
					|| navigation.equalsIgnoreCase("jsonsentpayment") ) {
				result = jsonTotalPendingPerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("searchconfirm")
					|| navigation.equalsIgnoreCase("gosearchconfirm")) {
				result = searchConfirmPerformed(request, response,
						"searchConfirmPayment");
			} else if (navigation.equalsIgnoreCase("searchpaid")) {
				result = searchPaidPerformed(request, response,
						"searchPaidPayment");
			} else if (navigation.equalsIgnoreCase("printpaymentdetails")) {
				result = printPaymentDetailPerformed(request, response,
						"printBatchPaymentDetail");
			} else if (navigation.equalsIgnoreCase("claimreport")) {
				result = claimReportPerformed(request, response,
						"searchClaimPayment");
			} else if (navigation.equalsIgnoreCase("listbatch") || navigation.equalsIgnoreCase("golistbatch")) {
				result = searchPerformed(request, response, "listBatchPayment");
				String batchClaimId = request.getParameter("batchClaimId");
				breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="+batchClaimId+"\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Batch Payment";
			} else if (navigation.equalsIgnoreCase("exportdisposition")) {
				result = exportDeliverPerformed(request, response,
						"searchConfirmPayment");
			} else if (navigation.equalsIgnoreCase("searchbatching")
					|| navigation.equalsIgnoreCase("gosearchbatching")) {
				result = searchBatchingPerformed(request, response,
						"searchPaymentBatching");
			}
//			Generate Payment Excel numpang di searchPerformed
//			else if (navigation.equalsIgnoreCase("exportdeliver")) {
//				result = exportDeliverInquiryPerformed(request, response,
//				"searchPayment");
//			}
			else if (navigation.equalsIgnoreCase("exportbank")) {
				result = exportBankPerformed(request, response, "searchPayment");
			} else if (navigation.equalsIgnoreCase("downloadexcel")) {
				result = downloadExcelPerformed(request, response,
						"detailPayment");
			} else if (navigation.equalsIgnoreCase("downloadclaim")) {
				result = downloadClaimPerformed(request, response,
						"searchPayment");
			} else if (navigation.equalsIgnoreCase("printcc")) {
				result = printCashCreditPerformed(request, response,
						"kasKredit");
			} 			
			else if (navigation.equalsIgnoreCase("searchpending") || navigation.equalsIgnoreCase("searchdelivered")) {
				result = searchPendingPerformed(request, response, "searchPayment");
			}
			else if (navigation.equalsIgnoreCase("searchreport") || navigation.equalsIgnoreCase("gosearchreport") || navigation.equalsIgnoreCase("downloadreport")) {
				result = searchPaymentReportPerformed(request, response, "searchPaymentReport");
			}
			else if (navigation.equalsIgnoreCase("printdeliveryletter")){
				String location = "printDeliveryLetter_"+config.getCompanyCode();
				
				String path = request.getSession().getServletContext().
						getRealPath("/WEB-INF/jsp/payment/"); 
				File file = new File(path,location+".jsp");
				if (!file.exists()){
					location = "printDeliveryLetter";
				}
				
				result = detailPerformed(request, response, location);
			}
			//Add 20150428 by FVO, for report
			else if(navigation.equalsIgnoreCase("reportmonitor") || navigation.equalsIgnoreCase("gosearchreportmonitor")){
				System.out.println("MASUK MONITOR");
				result = searchPaymentMonitorPerformed(request, response, "reportPaymentMonitor");
			}
			else if (navigation.equalsIgnoreCase("printreportmonitor")){
				String location = "printDeliveryLetterGlobal_"+config.getCompanyCode();
				
				String path = request.getSession().getServletContext().
						getRealPath("/WEB-INF/jsp/payment/"); 
				File file = new File(path,location+".jsp");
				if (!file.exists()){
					location = "printDeliveryLetterGlobal";
				}
				
				result = searchPaymentMonitorPerformed(request, response, location);
			}
			else {
				result = searchPerformed(request, response, "searchPayment");
				breadcrumb = "<a href=\"payment?navigation=search&searchtext=&searchby=&rowset=&index=1\" class=\"linkbreadcrumb\">Search Payment</a>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}
	
	public ModelAndView searchPaymentMonitorPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String clientText = WebUtil.getParameterString(request,
					"clientText", "");
			String clientNumber = WebUtil.getParameterString(request,
					"clientNumber", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			String searchstatus = WebUtil.getParameterString(request, "searchstatus","");
			Date checkDate = WebUtil
					.getParameterDate(request, "checkDate");

			Integer clientId = WebUtil.getParameterInteger(request,
					"clientId");
			ActionUser user = securityService.getActionUser(request);

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection<Object[]> coll = null;

			int rowsetint = 0;
			int count = 0;

			if (rowset != null) {
				rowsetint = rowset.intValue();
			}
			
			
			if(clientId != null && checkDate != null){
				coll = paymentService.generatePaymentDeliveryRecap(clientId, checkDate);
				
				if(navigation.equalsIgnoreCase("printreportmonitor")){
					ContactPerson contactPerson = null;
					Vector vEqP = new Vector();
					Vector vEqQ = new Vector();
					
					vEqP.add("deletedStatus");
					vEqQ.add(new Integer(0));
					
					vEqP.add("clientId.clientId");
					vEqQ.add(clientId);
					
					vEqP.add("paymentOfficer");
					vEqQ.add(new Integer(1));
		
					String sEqP[] = new String[vEqP.size()];
					vEqP.toArray(sEqP);
					Object sEqQ[] = new Object[vEqP.size()];
					vEqQ.toArray(sEqQ);
					
					String[] required = {};
					
					contactPerson = contactPersonService.searchUnique(sEqP, sEqQ, required);
					
					result.addObject("contactPerson", contactPerson);
				}

			}
			
			result.addObject("paymentCollection", coll);
			
			

//			if(navigation.equals("exportdeliver")) {
//				HSSFWorkbook workbook = PaymentReportGenerator.generateReport(collection);
//
//				response.setContentType("application/vnd.ms-excel");
//				response.setHeader("Pragma", "no-cache");
//				response.setHeader("Cache-Control", "no-cache");
//				response.setDateHeader("Expires", 0);
//
//				response.setHeader("Content-disposition", "inline; filename="
//						+ "paymentReport.xls");
//
//				ServletOutputStream sos = response.getOutputStream();
//
//				workbook.write(sos);
//				// sos.write(workbook.getBytes());
//				sos.close();
//			}
			request.setAttribute("clientText", clientText);
			request.setAttribute("clientNumber", clientNumber);
			result.addObject("clientNo", clientNumber);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("clientId", clientId);
			request.setAttribute("usermonitor", user.getUser().getFirstName());
			if (checkDate != null
					&& checkDate.toString().equals("1970-01-01")) {
				request.setAttribute("checkDate", "");
			} else {
				request.setAttribute("checkDate", checkDate);
			}

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */


//			request.setAttribute("countSet", countSet);
//			request.setAttribute("index", new Integer(index));
//			request.setAttribute("count", new Integer(count));
//			request.setAttribute("alert", request.getParameter("alert"));
//			request.setAttribute("minIndex", new Integer(minIndex));
//			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("searchstatus", searchstatus);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	// class+

	// class-

}
