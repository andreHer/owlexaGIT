package com.ametis.cms.web.controller;

import java.io.File;
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
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderTypeDiagnosisTreatment;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.PaymentReportDetail;
import com.ametis.cms.dto.PaymentReportSummary;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderTypeDiagnosisTreatmentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.BatchClaimReportGenerator;
import com.ametis.cms.util.ClaimDocumentDownloader;
import com.ametis.cms.util.LogUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * BatchClaim is a servlet controller for batch_claim Table. All you have to do
 * is to convert necessary data field to the named parameter
 */
public class BatchClaimController implements Controller

// extends+

// extends-

{

	private BatchClaimService batchClaimService;
	private ConfigurationService configurationService;

	private ClientService clientService;

	private ProviderService providerService;

	private UserService userService;

	private SecurityService securityService;

	private ResourceBundleMessageSource alertProperties;

	private ClaimService claimService;
	private ClaimItemService claimItemService;

	private Integer countSet;

	private Integer maxPercountSet;

	private PaymentService paymentService;

	private ActivityLogService logService;

	private ProviderTypeDiagnosisTreatmentService inaCBGService;

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public PaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
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

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public BatchClaimService getBatchClaimService() {
		return this.batchClaimService;
	}

	public ProviderTypeDiagnosisTreatmentService getInaCBGService() {
		return inaCBGService;
	}

	public void setInaCBGService(
			ProviderTypeDiagnosisTreatmentService inaCBGService) {
		this.inaCBGService = inaCBGService;
	}

	public ModelAndView completePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"COMPLETEBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for COMPLETEBATCHCLAIM access");
				return errorResult;

			}

			BatchClaim res = batchClaimService.completeBatch(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.batchclaim", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.batchclaim", null, "fail",
						Locale.getDefault()));

			}

			LogUtil.log(logService, user,
					"Complete Batch - " + request.getAttribute("alert"),
					res.toString());
			result = detailPerformed(request, response, "detailBatchClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView openPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"OPENBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for OPENBATCHCLAIM access");
				return errorResult;

			}

			BatchClaim res = batchClaimService.openBatch(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.batchclaim", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.batchclaim", null, "fail",
						Locale.getDefault()));

			}

			LogUtil.log(logService, user,
					"Open Batch Claim - " + request.getAttribute("alert"),
					res.toString());
			result = detailPerformed(request, response, "detailBatchClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView jsonTotalPendingPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {

			String navigation = request.getParameter("navigation");
			User user = securityService.getCurrentUser(request);
			int total = 0;
			result = new ModelAndView("jsonTotalPendingBatchClaim");

			if (navigation.equalsIgnoreCase("jsonclosedbatchclaim")) {
				if (user != null) {
					if (user.getUserType().intValue() == User.TPA) {
						String[] eqParam = { "deletedStatus",
								"batchClaimStatus.caseStatusId" };
						Object[] eqValue = { Integer.valueOf(0),
								BatchClaim.CLOSED };

						total = batchClaimService.getTotal(null, null, eqParam,
								eqValue);
					} else if (user.getUserType().intValue() == User.CLIENT) {
						String[] eqParam = { "deletedStatus",
								"batchClaimStatus.caseStatusId",
								"clientId.clientId" };
						Object[] eqValue = { Integer.valueOf(0),
								BatchClaim.CLOSED,
								user.getClientId().getClientId() };

						total = batchClaimService.getTotal(null, null, eqParam,
								eqValue);
					}
				}
			} else if (navigation.equalsIgnoreCase("jsontotalopen")) {
				if (user != null) {
					if (user.getUserType().intValue() == User.TPA) {
						String[] eqParam = { "deletedStatus",
								"batchClaimStatus.caseStatusId" };
						Object[] eqValue = { Integer.valueOf(0),
								BatchClaim.BATCH_OPEN };

						total = batchClaimService.getTotal(null, null, eqParam,
								eqValue);
					} else if (user.getUserType().intValue() == User.CLIENT) {
						String[] eqParam = { "deletedStatus",
								"batchClaimStatus.caseStatusId",
								"clientId.clientId" };
						Object[] eqValue = { Integer.valueOf(0),
								BatchClaim.BATCH_OPEN,
								user.getClientId().getClientId() };

						total = batchClaimService.getTotal(null, null, eqParam,
								eqValue);
					} else if (user.getUserType().intValue() == User.PROVIDER) {
						// 443 - show count my unpaidprovider in sidebar
						ActionUser userAction = securityService
								.getActionUser(request);
						String[] eqParam = { "providerId.providerId",
								"batchClaimStatus.caseStatusId" };
						Object[] eqValue = {
								userAction.getUser().getProviderId()
										.getProviderId(), BatchClaim.BATCH_OPEN };
						total = batchClaimService.getTotal(null, null, eqParam,
								eqValue);
					}
				}
			}

			result.addObject("result", total);

		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView closePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CLOSEBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for CLOSEBATCHCLAIM access");
				return errorResult;

			}

			BatchClaim res = batchClaimService.closeBatch(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.batchclaim", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.batchclaim", null, "fail",
						Locale.getDefault()));

			}

			LogUtil.log(logService, user,
					"Close Batch Claim - " + request.getAttribute("alert"),
					res.toString());
			result = detailPerformed(request, response, "detailBatchClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView printCOBPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = new ModelAndView("printCOBRequest");

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			ActionUser user = securityService.getActionUser(request);

			BatchClaim res = batchClaimService.get(pkey);

			String[] eqBatchP = { "deletedStatus", "batchClaimId.batchClaimId" };
			Object[] eqBatchQ = { Integer.valueOf(0), res.getBatchClaimId() };

			String[] required = { "Claim.MemberId", "Claim.MemberId.ParentId",
					"Claim.DiagnosisId" };

			int totalClaim = claimService.getTotal();
			Collection<Claim> claimList = claimService.search(null, null,
					eqBatchP, eqBatchQ, required, 0, totalClaim);
			Collection<Claim> claimRes = new Vector<Claim>();

			for (Iterator iterator = claimList.iterator(); iterator.hasNext();) {
				Claim object = (Claim) iterator.next();

				if (object.getDiagnosisId() != null) {

					int risk = 1;

					if (object.getDiagnosis2Id() != null) {
						risk = 2;

						if (object.getDiagnosis3Id() != null) {
							risk = 3;
						}
					}

					String[] eqParam = { "diagnosisId.diagnosisId",
							"deletedStatus", "treatmentRiskId.treatmentRiskId",
							"providerTypeId.providerTypeId" };
					Object[] eqValue = {
							object.getDiagnosisId().getDiagnosisId(),
							Integer.valueOf(0), Integer.valueOf(risk),
							Integer.valueOf(1) };

					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = inaCBGService
							.search(null, null, eqParam, eqValue, 0, 1);

					if (inaCBGList != null && inaCBGList.size() > 0) {
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iteratorClaim = inaCBGList
								.iterator();

						if (iteratorClaim.hasNext()) {
							ProviderTypeDiagnosisTreatment inaCBG = iteratorClaim
									.next();

							if (inaCBG != null) {
								object.setCobClaimCharge(inaCBG
										.getTreatmentRate());
							}
						}
					}
					claimRes.add(object);
				}

			}

			result.addObject("reports", claimRes);

		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView printBarcode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = new ModelAndView("printBatchBarcode");

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			ActionUser user = securityService.getActionUser(request);

			BatchClaim batchCode = batchClaimService.get(pkey);
			result.addObject(batchCode);
			request.setAttribute("batchNumber", batchCode.getBatchClaimNumber());

		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView verifyBulkPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"VERIFYBULK");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for VERIFYBULK access");
				return errorResult;

			}

			int res = claimService.verifyBulk(batchClaimId, user);

			if (res > 0) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.batchclaim", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.batchclaim", null, "fail",
						Locale.getDefault()));

			}

			LogUtil.log(logService, user, "Verify Bulk Batch Claim - "
					+ request.getAttribute("alert"), "batch claim id : "
					+ batchClaimId);
			result = detailPerformed(request, response, "detailBatchClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView voidPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"VOIDBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for VOIDBATCHCLAIM access");
				return errorResult;

			}

			if (navigation.equalsIgnoreCase("prevoid")) {
				result = new ModelAndView("voidBatchClaim");
			} else if (navigation.equalsIgnoreCase("void")) {

				BatchClaim res = batchClaimService.voidBatch(pkey, user);

				if (res != null) {
					request.setAttribute("alert", alertProperties.getMessage(
							"success.delete.batchclaim", null, "success",
							Locale.getDefault()));
				} else {
					request.setAttribute("alert", alertProperties.getMessage(
							"fail.delete.batchclaim", null, "fail",
							Locale.getDefault()));

				}

				LogUtil.log(logService, user,
						"Void Batch Claim - " + request.getAttribute("alert"),
						res.toString());
			}

			result = detailPerformed(request, response, "detailBatchClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = batchClaimId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEBATCHCLAIM access");
				return errorResult;

			}
			BatchClaim res = batchClaimService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.batchclaim", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.batchclaim", null, "fail",
						Locale.getDefault()));

			}
			LogUtil.log(logService, user,
					"Delete Batch Claim - " + request.getAttribute("alert"),
					res.toString());
			result = searchPerformed(request, response, "searchBatchClaim");
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);
			String navigation = request.getParameter("navigation");

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DETAILBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DETAILBATCHCLAIM access");
				return errorResult;

			}

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			result = new ModelAndView(location);
			java.io.Serializable pkey = batchClaimId;
			String[] required = { "BatchClaim.PaymentRecipient",
					"BatchClaim.BatchClaimStatus", "BatchClaim.PaymentMethod",
					"BatchClaim.ClaimCurrency", "BatchClaim.PaymentCurrency",
					"BatchClaim.ProviderId", "BatchClaim.MemberId",
					"BatchClaim.MemberGroupId", "BatchClaim.ClientId" };

			BatchClaim object = batchClaimService.get(pkey, required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.batchclaim", null, "fail",
						Locale.getDefault()));
			}

			if (object != null) {
				Payment payment = null;

				Collection<Payment> paymentCollection = paymentService.search(
						null, null, "batchClaim.batchClaimId",
						object.getBatchClaimId(), 0, 10);

				if (paymentCollection != null && paymentCollection.size() > 0) {
					Iterator<Payment> iterator = paymentCollection.iterator();

					if (iterator.hasNext()) {
						payment = iterator.next();
					}

				}

				Long totalOpenClaim = claimService
						.getTotalOpenClaim(batchClaimId);

				request.setAttribute("paymentCollection", paymentCollection);
				request.setAttribute("paymentbean", payment);
				request.setAttribute("openClaim", totalOpenClaim);

				// Add 20150420 by FVO, for generate Delivery Document/Letter
				if (navigation.equalsIgnoreCase("printdownloadrecap")) {
					Collection<Object[]> coll = claimService
							.generateClaimDownloadRecap(payment.getPaymentId());
					result.addObject("claimCollection", coll);
					// if (coll != null && coll.size() > 0){
					//
					// Iterator<Object[]> reportIterator = coll.iterator();
					//
					// if (reportIterator != null){
					// while (reportIterator.hasNext()){
					// Object[] report = reportIterator.next();
					//
					// if (report != null){
					//
					// }
					// }
					// }
					// }else{
					// System.out.println("COLL NULL");
					// }
				}
			}

			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			LogUtil.log(logService, user,
					"Detail Batch Claim - " + request.getAttribute("alert"),
					object.toString());

			result.addObject("batchClaim", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("listnavigation", listnavigation);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView exportBatchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"EXPORTBATCH");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for EXPORTBATCH access");
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

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");

			result.addObject("subnavigation", subnavigation);

			Integer claimType = WebUtil.getParameterInteger(request,
					"claimType");

			Integer serviceType = WebUtil.getParameterInteger(request,
					"serviceType");

			Date minimumDate = WebUtil
					.getParameterDate(request, "minimum_date");
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

			if (navigation.equals("download")) {

				if (searchby != null) {

					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equals("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equals("memberName")) {
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}

					if (searchby.equalsIgnoreCase("invoiceNumber")) {
						vLikeP.add("invoiceNumber");
						vLikeQ.add(searchtext);
					}
				}

				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("batchClaimStatus.caseStatusId");
					vEqQ.add(searchStatus);
				}

				if (claimType != null && claimType.intValue() > 0) {
					vEqP.add("claimTypeId.claimTypeId");
					vEqQ.add(claimType);
				}
				if (serviceType != null && serviceType.intValue() > 0) {
					vEqP.add("caseCategoryId.caseCategoryId");
					vEqQ.add(serviceType);
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

			String required[] = new String[] {
					// foreign affairs
					"BatchClaim.BatchClaimStatus",
					"BatchClaim.PaymentRecipient", "BatchClaim.MemberId",
					"BatchClaim.ClientId", "BatchClaim.BatchClaimType",
					"BatchClaim.MemberGroupId", "BatchClaim.ProviderId",
					"BatchClaim.Priority",
			// foreign affairs end
			};

			if (minimumDate != null && maxDate != null) {
				collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, "batchClaimDate", minimumDate, maxDate, required);
			} else {
				int totalBatch = batchClaimService.getTotal(sLikeP, sLikeQ,
						sEqP, sEqQ);
				collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, 0, totalBatch);
			}

			System.out.println("Collection : " + collection.size());
			/**
			 * int idx = 1; String data =
			 * "No|Batch Number|Member Group| Provider| MemberName| MemberNumber| Received Date| Batch Value|Total Claim| Status| CDV Number| CDV Date| CDV Payment Confirmation"
			 * ; data += "\n\n"; if (collection != null) { Iterator<BatchClaim>
			 * iterator = collection.iterator();
			 * 
			 * if (iterator != null) { BatchClaim batchClaim = null;
			 * 
			 * String cdvNumber = ""; String cdvDate = ""; String cdvConfirmDate
			 * = ""; String memberGroupName = ""; String providerName = "";
			 * String clientName = ""; String memberName = ""; String
			 * memberNumber = ""; String batchStatus = "";
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * batchClaim = iterator.next();
			 * 
			 * if (batchClaim != null) {
			 * 
			 * String[] param = { "deletedStatus", "batchClaim.batchClaimId" };
			 * 
			 * Object[] object = { Integer.valueOf(0),
			 * batchClaim.getBatchClaimId() };
			 * 
			 * if (batchClaim.getBatchClaimStatus()
			 * .getCaseStatusId().intValue() == BatchClaim.PAYMENT_ISSUED ||
			 * batchClaim.getBatchClaimStatus() .getCaseStatusId().intValue() ==
			 * BatchClaim.PAID) {
			 * 
			 * Collection<Payment> paymentCollection =
			 * paymentService.search(null, null, param, object, 0, 1);
			 * 
			 * if (paymentCollection != null && paymentCollection.size() > 0) {
			 * Iterator<Payment> paymentIterator = paymentCollection
			 * .iterator();
			 * 
			 * Payment payment = paymentIterator.next(); if (payment != null) {
			 * cdvNumber = payment.getPaymentNumber(); cdvDate =
			 * payment.getPaymentTime() .toString();
			 * 
			 * if (batchClaim.getBatchClaimStatus()
			 * .getCaseStatusId().intValue() == BatchClaim.PAID) {
			 * cdvConfirmDate = payment .getPaymentConfirmDate() .toString(); }
			 * } } }
			 * 
			 * if (batchClaim.getProviderId() != null) { providerName =
			 * batchClaim.getProviderName(); } if (batchClaim.getMemberGroupId()
			 * != null) { memberGroupName = batchClaim.getGroupName(); } if
			 * (batchClaim.getMemberId() != null) { memberName =
			 * batchClaim.getMemberName();
			 * 
			 * memberNumber = batchClaim.getClaimerNumber(); }
			 * 
			 * if (batchClaim.getBatchClaimStatus() != null) { batchStatus = "";
			 * 
			 * if (batchClaim.getBatchClaimStatus() != null){ if
			 * (batchClaim.getBatchClaimStatus().getCaseStatusId().intValue() ==
			 * BatchClaim.BATCH_OPEN){ batchStatus = "OPEN"; } if
			 * (batchClaim.getBatchClaimStatus().getCaseStatusId().intValue() ==
			 * BatchClaim.CLOSED){ batchStatus = "CLOSED"; } if
			 * (batchClaim.getBatchClaimStatus().getCaseStatusId().intValue() ==
			 * BatchClaim.COMPLETE){ batchStatus = "COMPLETE"; } if
			 * (batchClaim.getBatchClaimStatus().getCaseStatusId().intValue() ==
			 * BatchClaim.PAID){ batchStatus = "PAID"; } if
			 * (batchClaim.getBatchClaimStatus().getCaseStatusId().intValue() ==
			 * BatchClaim.PAYMENT_ISSUED){ batchStatus = "CDV ISSUED"; } if
			 * (batchClaim.getBatchClaimStatus().getCaseStatusId().intValue() ==
			 * BatchClaim.INSTALLMENT_PAYMENT){ batchStatus =
			 * "INSTALLMENT PAYMENT"; } }
			 * 
			 * }
			 * 
			 * data += idx + "|" + batchClaim.getBatchClaimNumber() + "|" +
			 * memberGroupName + "|" + providerName + "|" + memberName + "|" +
			 * memberNumber + "|" + batchClaim.getBatchClaimDate() + "|" +
			 * batchClaim.getBatchClaimInitialValue() + "|" +
			 * batchClaim.getTotalClaim() + "|" + batchStatus + "|" + cdvNumber
			 * + "|" + cdvDate + "|" + cdvConfirmDate + "\n";
			 * 
			 * cdvNumber = ""; cdvDate = ""; cdvConfirmDate = "";
			 * 
			 * providerName = ""; memberGroupName = ""; memberName = "";
			 * memberNumber = ""; idx += 1;
			 * 
			 * } } } }
			 * 
			 * System.out.println("DATA : " + data);
			 * 
			 * response.setContentType("application/x-csv");
			 * response.setHeader("Pragma", "no-cache");
			 * response.setHeader("Cache-Control", "no-cache");
			 * response.setDateHeader("Expires", 0);
			 * 
			 * response.setHeader("Content-disposition", "inline; filename=" +
			 * "batchClaimReport.csv");
			 * 
			 * ServletOutputStream sos = response.getOutputStream();
			 * 
			 * response.setHeader("Content-length", Integer
			 * .toString(data.length()));
			 * 
			 * sos.write(data.getBytes()); sos.close();
			 **/

			LogUtil.log(logService, user, "Export Batch Claim - ",
					"SearchText : " + searchtext + "  SearchBy : " + searchby);

			HSSFWorkbook workbook = BatchClaimReportGenerator.generateReport(
					user.getUser(), collection);

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setHeader("Content-disposition", "inline; filename="
					+ "batchClaimReport.xls");

			ServletOutputStream sos = response.getOutputStream();

			workbook.write(sos);
			// sos.write(workbook.getBytes());
			sos.close();

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			request.setAttribute("alert", request.getParameter("alert"));

		} catch (Exception e) {
			e.printStackTrace();

			// request.setAttribute("alert", alertProperties.getMessage(
			// "system.error " + e.getMessage(), null, "fail", Locale
			// .getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchPaymentBalancePerformed(
			HttpServletRequest request, HttpServletResponse response,
			String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHBATCHCLAIM access");
				return errorResult;

			}

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			String dateBy = WebUtil.getParameterString(request, "dateBy",
					"batchClaimDate");
			// String searchstatus = WebUtil.getParameterString(request,
			// "status","");
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");
			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");

			if (currentNavigation.equalsIgnoreCase("list")) {

				location = "listProviderBatchClaim";
			} else if (currentNavigation.equalsIgnoreCase("listclient")) {
				location = "listClientBatchClaim";
			}

			result = new ModelAndView(location);

			result.addObject("providerId", providerId);
			result.addObject("clientId", clientId);

			result.addObject("subnavigation", subnavigation);
			result.addObject("listnavigation", listnavigation);

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
			String sortcolumn = WebUtil.getParameterString(request,
					"sortcolumn", "");

			boolean sortBatchNo = true, sortReceiveDate = true, sortClient = true, sortProvider = true, sortGroup = true, sortMember = true, sortInvoiceNo = true, sortInvoiceDate = true, sortPaymentDate = true, sortCharge = true, sortApproved = true, sortExcess = true, sortStatus = true;
			boolean order = true;

			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			// CHECKING SORTING COLUMN
			if ((!navigation.equalsIgnoreCase("gosearchbysort")
					&& arah.isEmpty() && arah.equals(""))
					|| navigation.equals("gosearch")) {
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}

			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equals("gosearchbysort")
					|| (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && !searchtext.equals("")
						&& searchtext != null) {
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaimNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("invoiceNumber")) {
						vLikeP.add("invoiceNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("batchClaimStatus.caseStatusId");
					vEqQ.add(Integer.valueOf(searchStatus));

					// 443 - Only cashless claim is allowed in provider
					vEqP.add("claimTypeId.claimTypeId");
					vEqQ.add(Integer.valueOf(2));
				}

			}

			if (navigation.equalsIgnoreCase("listclient")
					|| currentNavigation.equalsIgnoreCase("listclient")) {
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);

			} else if (navigation.equalsIgnoreCase("list")
					|| currentNavigation.equalsIgnoreCase("list")) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);

			}

			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {

					if (clientId == null) {
						vEqP.add("clientId.clientId");
						vEqQ.add(user.getUser().getClientId().getClientId());
					}
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());

					// 443 - Only cashless claim is allowed in provider
					vEqP.add("claimTypeId.claimTypeId");
					vEqQ.add(Integer.valueOf(2));
				}

			}

			if (navigation.equalsIgnoreCase("lookupextract")) {
				Integer claimId = WebUtil.getParameterInteger(request,
						"claimId");
				if (claimId != null) {
					String[] required = { "Claim.ProviderId",
							"Claim.ClaimTypeId" };
					Claim claim = claimService.get(claimId, required);

					if (claim != null && claim.getClaimTypeId() != null) {

						if (claim.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS) {
							vEqP.add("providerId.providerId");
							vEqQ.add(claim.getProviderId().getProviderId());
						} else if (claim.getClaimTypeId().getClaimTypeId()
								.intValue() == ClaimType.REIMBURESEMENT) {

						}
					}
				}
			}

			Client clientObject = null;

			if (clientId != null) {
				try {
					java.io.Serializable clientpkey = clientId;
					System.out.println("member client id : " + clientId);
					String[] clientRequired = { "Client.FundCurrency",
							"Client.PaymentCurrency", "Client.StatusId" };
					clientObject = clientService
							.get(clientpkey, clientRequired);

				} catch (Exception ex) {
					System.out.println("member group object : "
							+ clientObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			Provider providerObject = null;

			if (providerId != null) {
				try {
					java.io.Serializable providerkey = providerId;
					System.out.println("provider id : " + providerId);
					String[] providerRequired = { "Provider.StatusId",
							"Provider.ProviderCategoryId",
							"Provider.ProviderGroupId",
							"Provider.ProviderSpecId",
							"Provider.ProviderCurrencyId" };
					providerObject = providerService.get(providerkey,
							providerRequired);

				} catch (Exception ex) {
					// System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			result.addObject("providerId", providerId);
			result.addObject("clientId", clientId);
			result.addObject("client", clientObject);
			result.addObject("provider", providerObject);

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

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")
					&& maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

				String[] betweenColumn = { dateBy };
				Object[] minColumn = { minDate };
				Object[] maxColumn = { maxDate };

				count = batchClaimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						betweenColumn, minColumn, maxColumn);
			} else {
				count = batchClaimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"BatchClaim.BatchClaimStatus",
					"BatchClaim.PaymentRecipient", "BatchClaim.MemberId",
					"BatchClaim.ClientId", "BatchClaim.BatchClaimType",
					"BatchClaim.MemberGroupId", "BatchClaim.ProviderId",
					"BatchClaim.Priority",
			// foreign affairs end
			};

			// SORTING START
			if (sortcolumn != null && !sortcolumn.equals("")) {
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(
						request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request,
						"columntosort", "");
				if (navigation.equals("gosearchbysort")) {
					if (sortcolumn.equalsIgnoreCase("batchnumber")) {
						sortByColumn = "batchClaimNumber";
						Boolean sortOrderBatchNo = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderBatchNo", ""));
						sortBatchNo = !sortOrderBatchNo;
						order = sortBatchNo;
					} else if (sortcolumn.equalsIgnoreCase("receiveddate")) {
						sortByColumn = "batchClaimDate";
						Boolean sortOrderBatchDate = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderBatchDate", ""));
						sortReceiveDate = !sortOrderBatchDate;
						order = sortReceiveDate;
					} else if (sortcolumn.equalsIgnoreCase("client")) {
						sortByColumn = "clientId.clientName";
						Boolean sortOrderClient = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderClient",
										""));
						sortClient = !sortOrderClient;
						order = sortClient;
					} else if (sortcolumn.equalsIgnoreCase("provider")) {
						sortByColumn = "providerName";
						Boolean sortOrderProvider = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderProvider", ""));
						sortProvider = !sortOrderProvider;
						order = sortProvider;
					} else if (sortcolumn.equalsIgnoreCase("groupname")) {
						sortByColumn = "groupName";
						Boolean sortOrderGroup = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderGroup",
										""));
						sortGroup = !sortOrderGroup;
						order = sortGroup;
					} else if (sortcolumn.equalsIgnoreCase("membername")) {
						sortByColumn = "memberName";
						Boolean sortOrderMember = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderMember",
										""));
						sortMember = !sortOrderMember;
						order = sortMember;
					} else if (sortcolumn.equalsIgnoreCase("invoicenumber")) {
						sortByColumn = "invoiceNumber";
						Boolean sortOrderInvoiceNo = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderInvoiceNo", ""));
						sortInvoiceNo = !sortOrderInvoiceNo;
						order = sortInvoiceNo;
					} else if (sortcolumn.equalsIgnoreCase("invoicedate")) {
						sortByColumn = "invoiceDate";
						Boolean sortOrderInvoiceDate = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderInvoiceDate", ""));
						sortInvoiceDate = !sortOrderInvoiceDate;
						order = sortInvoiceDate;
					} else if (sortcolumn.equalsIgnoreCase("paymentdate")) {
						sortByColumn = "paymentDate";
						Boolean sortOrderPaymentDate = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderPaymentDate", ""));
						sortPaymentDate = !sortOrderPaymentDate;
						order = sortPaymentDate;
					} else if (sortcolumn.equalsIgnoreCase("charge")) {
						sortByColumn = "batchClaimInitialValue";
						Boolean sortOrderCharge = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderCharge",
										""));
						sortCharge = !sortOrderCharge;
						order = sortCharge;
					} else if (sortcolumn.equalsIgnoreCase("approved")) {
						sortByColumn = "batchClaimFinalValue";
						Boolean sortOrderApproved = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderApproved", ""));
						sortApproved = !sortOrderApproved;
						order = sortApproved;
					} else if (sortcolumn.equalsIgnoreCase("excess")) {
						sortByColumn = "batchExcessValue";
						Boolean sortOrderExcess = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderExcess",
										""));
						sortExcess = !sortOrderExcess;
						order = sortExcess;
					} else {
						sortByColumn = "batchClaimStatus.caseStatusName";
						Boolean sortOrderStatus = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderStatus",
										""));
						sortStatus = !sortOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				} else {
					sortByColumn = columntosort;
					order = sortOrder;
					if (sortcolumn.equalsIgnoreCase("batchnumber")) {
						sortBatchNo = order;
					} else if (sortcolumn.equalsIgnoreCase("receiveddate")) {
						sortReceiveDate = order;
					} else if (sortcolumn.equalsIgnoreCase("client")) {
						sortClient = order;
					} else if (sortcolumn.equalsIgnoreCase("provider")) {
						sortProvider = order;
					} else if (sortcolumn.equalsIgnoreCase("groupname")) {
						sortGroup = order;
					} else if (sortcolumn.equalsIgnoreCase("membername")) {
						sortMember = order;
					} else if (sortcolumn.equalsIgnoreCase("invoicenumber")) {
						sortInvoiceNo = order;
					} else if (sortcolumn.equalsIgnoreCase("invoicedate")) {
						sortInvoiceDate = order;
					} else if (sortcolumn.equalsIgnoreCase("paymentdate")) {
						sortPaymentDate = order;
					} else if (sortcolumn.equalsIgnoreCase("charge")) {
						sortCharge = order;
					} else if (sortcolumn.equalsIgnoreCase("approved")) {
						sortApproved = order;
					} else if (sortcolumn.equalsIgnoreCase("excess")) {
						sortExcess = order;
					} else {
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, order,
							sortByColumn, required, rowsetint,
							countSet.intValue());
				} else {
					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, order, sortByColumn, required, rowsetint,
							countSet.intValue());
				}
			} else {
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn,
							required, rowsetint, countSet.intValue());
				} else {
					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, rowsetint, countSet.intValue());
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
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn,
							required, rowsetint, countSet.intValue());
				} else {
					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, rowsetint, countSet.intValue());
					System.out.println("TOTAL : " + collection.size());
				}
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

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

			LogUtil.log(logService, user, "Search Batch Claim - ",
					"SearchText : " + searchtext + "  SearchBy : " + searchby);

			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);

			result.addObject("BatchClaims", collection);

			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("dateBy", dateBy);
			request.setAttribute("sortApproved", sortApproved);
			request.setAttribute("sortBatchNo", sortBatchNo);
			request.setAttribute("sortCharge", sortCharge);
			request.setAttribute("sortClient", sortClient);
			request.setAttribute("sortExcess", sortExcess);
			request.setAttribute("sortGroup", sortGroup);
			request.setAttribute("sortInvoiceDate", sortInvoiceDate);
			request.setAttribute("sortInvoiceNo", sortInvoiceNo);
			request.setAttribute("sortMember", sortMember);
			request.setAttribute("sortPaymentDate", sortPaymentDate);
			request.setAttribute("sortProvider", sortProvider);
			request.setAttribute("sortReceiveDate", sortReceiveDate);
			request.setAttribute("sortStatus", sortStatus);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"SEARCHBATCHCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for SEARCHBATCHCLAIM access");
				return errorResult;

			}

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			String dateBy = WebUtil.getParameterString(request, "dateBy",
					"batchClaimDate");
			// String searchstatus = WebUtil.getParameterString(request,
			// "status","");
			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");

			Date minDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			String subnavigation = WebUtil.getParameterString(request,
					"subnavigation", "");
			String currentNavigation = WebUtil.getParameterString(request,
					"currentnavigation", "");

			if (currentNavigation.equalsIgnoreCase("list")) {

				location = "listProviderBatchClaim";
			} else if (currentNavigation.equalsIgnoreCase("listclient")) {
				location = "listClientBatchClaim";
			}

			result = new ModelAndView(location);

			result.addObject("providerId", providerId);
			result.addObject("clientId", clientId);

			result.addObject("subnavigation", subnavigation);
			result.addObject("listnavigation", listnavigation);

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
			String sortcolumn = WebUtil.getParameterString(request,
					"sortcolumn", "");

			boolean sortBatchNo = true, sortReceiveDate = true, sortClient = true, sortProvider = true, sortGroup = true, sortMember = true, sortInvoiceNo = true, sortInvoiceDate = true, sortPaymentDate = true, sortCharge = true, sortApproved = true, sortExcess = true, sortStatus = true, sortClaimType = true;
			boolean order = true;

			Vector vLikeP = new Vector();
			Vector vLikeQ = new Vector();
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			boolean isDownload = navigation.equalsIgnoreCase("download");

			// CHECKING SORTING COLUMN
			if ((!navigation.equalsIgnoreCase("gosearchbysort")
					&& arah.isEmpty() && arah.equals(""))
					|| navigation.equals("gosearch")) {
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}

			if (navigation.equals("gosearch") || navigation.equals("golookup")
					|| navigation.equals("listclient")
					|| navigation.equals("list") || isDownload
					|| navigation.equals("gosearchbysort")
					|| (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && !searchtext.equals("")
						&& searchtext != null) {
					if (searchby.equalsIgnoreCase("batchClaimNumber")) {
						vLikeP.add("batchClaimNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("invoiceNumber")) {
						vLikeP.add("invoiceNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("providerName")) {
						vLikeP.add("providerId.providerName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberGroupName")) {
						vLikeP.add("memberGroupId.groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("memberName")) {
						vLikeP.add("memberName");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("batchClaimStatus.caseStatusId");
					vEqQ.add(Integer.valueOf(searchStatus));
				}

			}

			if (navigation.equalsIgnoreCase("listclient")
					|| currentNavigation.equalsIgnoreCase("listclient")) {
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);

			} else if (navigation.equalsIgnoreCase("list")
					|| currentNavigation.equalsIgnoreCase("list")) {
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);

			}

			if (user != null && user.getUser().getUserType() != null) {
				if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP) {
					vEqP.add("memberGroupId.memberGroupId");
					vEqQ.add(user.getUser().getMemberGroupId()
							.getMemberGroupId());
				} else if (user.getUser().getUserType().intValue() == User.CLIENT) {

					if (clientId == null) {
						vEqP.add("clientId.clientId");
						vEqQ.add(user.getUser().getClientId().getClientId());
					}
				} else if (user.getUser().getUserType().intValue() == User.PROVIDER) {
					vEqP.add("providerId.providerId");
					vEqQ.add(user.getUser().getProviderId().getProviderId());
				}

			}

			if (navigation.equalsIgnoreCase("lookupextract")) {
				Integer claimId = WebUtil.getParameterInteger(request,
						"claimId");
				if (claimId != null) {
					String[] required = { "Claim.ProviderId",
							"Claim.ClaimTypeId" };
					Claim claim = claimService.get(claimId, required);

					if (claim != null && claim.getClaimTypeId() != null) {

						if (claim.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS) {
							vEqP.add("providerId.providerId");
							vEqQ.add(claim.getProviderId().getProviderId());
						} else if (claim.getClaimTypeId().getClaimTypeId()
								.intValue() == ClaimType.REIMBURESEMENT) {

						}
					}
				}
			}

			Client clientObject = null;

			if (clientId != null) {
				try {
					java.io.Serializable clientpkey = clientId;
					System.out.println("member client id : " + clientId);
					String[] clientRequired = { "Client.FundCurrency",
							"Client.PaymentCurrency", "Client.StatusId" };
					clientObject = clientService
							.get(clientpkey, clientRequired);

				} catch (Exception ex) {
					System.out.println("member group object : "
							+ clientObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			Provider providerObject = null;

			if (providerId != null) {
				try {
					java.io.Serializable providerkey = providerId;
					System.out.println("provider id : " + providerId);
					String[] providerRequired = { "Provider.StatusId",
							"Provider.ProviderCategoryId",
							"Provider.ProviderGroupId",
							"Provider.ProviderSpecId",
							"Provider.ProviderCurrencyId" };
					providerObject = providerService.get(providerkey,
							providerRequired);

				} catch (Exception ex) {
					// System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			result.addObject("providerId", providerId);
			result.addObject("clientId", clientId);
			result.addObject("client", clientObject);
			result.addObject("provider", providerObject);

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

			if (minDate != null
					&& !minDate.toString().equalsIgnoreCase("1970-01-01")
					&& maxDate != null
					&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

				String[] betweenColumn = { dateBy };
				Object[] minColumn = { minDate };
				Object[] maxColumn = { maxDate };

				count = batchClaimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ,
						betweenColumn, minColumn, maxColumn);
			} else {
				count = batchClaimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);
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
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir",
						new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"BatchClaim.BatchClaimStatus",
					"BatchClaim.PaymentRecipient", "BatchClaim.MemberId",
					"BatchClaim.ClientId", "BatchClaim.BatchClaimType",
					"BatchClaim.MemberGroupId", "BatchClaim.ProviderId",
					"BatchClaim.Priority",
			// foreign affairs end
			};

			// SORTING START
			if (sortcolumn != null && !sortcolumn.equals("")) {
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(
						request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request,
						"columntosort", "");
				if (navigation.equals("gosearchbysort")) {
					if (sortcolumn.equalsIgnoreCase("batchnumber")) {
						sortByColumn = "batchClaimNumber";
						Boolean sortOrderBatchNo = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderBatchNo", ""));
						sortBatchNo = !sortOrderBatchNo;
						order = sortBatchNo;
					} else if (sortcolumn.equalsIgnoreCase("receiveddate")) {
						sortByColumn = "batchClaimDate";
						Boolean sortOrderBatchDate = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderBatchDate", ""));
						sortReceiveDate = !sortOrderBatchDate;
						order = sortReceiveDate;
					} else if (sortcolumn.equalsIgnoreCase("client")) {
						sortByColumn = "clientId.clientName";
						Boolean sortOrderClient = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderClient",
										""));
						sortClient = !sortOrderClient;
						order = sortClient;
					} else if (sortcolumn.equalsIgnoreCase("provider")) {
						sortByColumn = "providerName";
						Boolean sortOrderProvider = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderProvider", ""));
						sortProvider = !sortOrderProvider;
						order = sortProvider;
					} else if (sortcolumn.equalsIgnoreCase("groupname")) {
						sortByColumn = "groupName";
						Boolean sortOrderGroup = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderGroup",
										""));
						sortGroup = !sortOrderGroup;
						order = sortGroup;
					} else if (sortcolumn.equalsIgnoreCase("membername")) {
						sortByColumn = "memberName";
						Boolean sortOrderMember = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderMember",
										""));
						sortMember = !sortOrderMember;
						order = sortMember;
					} else if (sortcolumn.equalsIgnoreCase("invoicenumber")) {
						sortByColumn = "invoiceNumber";
						Boolean sortOrderInvoiceNo = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderInvoiceNo", ""));
						sortInvoiceNo = !sortOrderInvoiceNo;
						order = sortInvoiceNo;
					} else if (sortcolumn.equalsIgnoreCase("invoicedate")) {
						sortByColumn = "invoiceDate";
						Boolean sortOrderInvoiceDate = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderInvoiceDate", ""));
						sortInvoiceDate = !sortOrderInvoiceDate;
						order = sortInvoiceDate;
					} else if (sortcolumn.equalsIgnoreCase("paymentdate")) {
						sortByColumn = "paymentDate";
						Boolean sortOrderPaymentDate = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderPaymentDate", ""));
						sortPaymentDate = !sortOrderPaymentDate;
						order = sortPaymentDate;
					} else if (sortcolumn.equalsIgnoreCase("charge")) {
						sortByColumn = "batchClaimInitialValue";
						Boolean sortOrderCharge = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderCharge",
										""));
						sortCharge = !sortOrderCharge;
						order = sortCharge;
					} else if (sortcolumn.equalsIgnoreCase("approved")) {
						sortByColumn = "batchClaimFinalValue";
						Boolean sortOrderApproved = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderApproved", ""));
						sortApproved = !sortOrderApproved;
						order = sortApproved;
					} else if (sortcolumn.equalsIgnoreCase("excess")) {
						sortByColumn = "batchExcessValue";
						Boolean sortOrderExcess = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderExcess",
										""));
						sortExcess = !sortOrderExcess;
						order = sortExcess;
					} else if (sortcolumn.equalsIgnoreCase("status")) {
						sortByColumn = "batchClaimStatus.caseStatusName";
						Boolean sortOrderStatus = Boolean.valueOf(WebUtil
								.getParameterString(request, "sortOrderStatus",
										""));
						sortStatus = !sortOrderStatus;
						order = sortStatus;
					} else {
						sortByColumn = "batchClaimType.claimTypeName";
						Boolean sortOrderClaimType = Boolean.valueOf(WebUtil
								.getParameterString(request,
										"sortOrderClaimType", ""));
						sortClaimType = !sortOrderClaimType;
						order = sortClaimType;
					}
					columntosort = sortByColumn;
				} else {
					sortByColumn = columntosort;
					order = sortOrder;
					if (sortcolumn.equalsIgnoreCase("batchnumber")) {
						sortBatchNo = order;
					} else if (sortcolumn.equalsIgnoreCase("receiveddate")) {
						sortReceiveDate = order;
					} else if (sortcolumn.equalsIgnoreCase("client")) {
						sortClient = order;
					} else if (sortcolumn.equalsIgnoreCase("provider")) {
						sortProvider = order;
					} else if (sortcolumn.equalsIgnoreCase("groupname")) {
						sortGroup = order;
					} else if (sortcolumn.equalsIgnoreCase("membername")) {
						sortMember = order;
					} else if (sortcolumn.equalsIgnoreCase("invoicenumber")) {
						sortInvoiceNo = order;
					} else if (sortcolumn.equalsIgnoreCase("invoicedate")) {
						sortInvoiceDate = order;
					} else if (sortcolumn.equalsIgnoreCase("paymentdate")) {
						sortPaymentDate = order;
					} else if (sortcolumn.equalsIgnoreCase("charge")) {
						sortCharge = order;
					} else if (sortcolumn.equalsIgnoreCase("approved")) {
						sortApproved = order;
					} else if (sortcolumn.equalsIgnoreCase("excess")) {
						sortExcess = order;
					} else {
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn, order,
							sortByColumn, required, isDownload ? 0 : rowsetint,
							isDownload ? count : countSet.intValue());
				} else {
					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, order, sortByColumn, required, isDownload ? 0
									: rowsetint,
							isDownload ? count : countSet.intValue());
				}
			} else {
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn,
							required, isDownload ? 0 : rowsetint,
							isDownload ? count : countSet.intValue());
				} else {
					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, isDownload ? 0 : rowsetint,
							isDownload ? count : countSet.intValue());
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
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir",
							new Integer(count / countSet.intValue()));
				}
				if (minDate != null
						&& !minDate.toString().equalsIgnoreCase("1970-01-01")
						&& maxDate != null
						&& !maxDate.toString().equalsIgnoreCase("1970-01-01")) {

					String[] betweenColumn = { dateBy };
					Object[] minColumn = { minDate };
					Object[] maxColumn = { maxDate };

					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, betweenColumn, minColumn, maxColumn,
							required, isDownload ? 0 : rowsetint,
							isDownload ? count : countSet.intValue());
				} else {
					collection = batchClaimService.search(sLikeP, sLikeQ, sEqP,
							sEqQ, required, isDownload ? 0 : rowsetint,
							isDownload ? count : countSet.intValue());
					System.out.println("TOTAL : " + collection.size());
				}
			}

			if (isDownload) {
				System.out.println(">>> Download");
				HSSFWorkbook workbook = BatchClaimReportGenerator
						.generateReport(user.getUser(), collection);

				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "batchClaimReport.xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				// sos.write(workbook.getBytes());
				sos.close();
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

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

			LogUtil.log(logService, user, "Search Batch Claim - ",
					"SearchText : " + searchtext + "  SearchBy : " + searchby);

			request.setAttribute("minimum_date", minimumDate);
			request.setAttribute("maximum_date", maximumDate);

			result.addObject("BatchClaims", collection);

			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("dateBy", dateBy);
			request.setAttribute("sortApproved", sortApproved);
			request.setAttribute("sortBatchNo", sortBatchNo);
			request.setAttribute("sortCharge", sortCharge);
			request.setAttribute("sortClient", sortClient);
			request.setAttribute("sortExcess", sortExcess);
			request.setAttribute("sortGroup", sortGroup);
			request.setAttribute("sortInvoiceDate", sortInvoiceDate);
			request.setAttribute("sortInvoiceNo", sortInvoiceNo);
			request.setAttribute("sortMember", sortMember);
			request.setAttribute("sortPaymentDate", sortPaymentDate);
			request.setAttribute("sortProvider", sortProvider);
			request.setAttribute("sortReceiveDate", sortReceiveDate);
			request.setAttribute("sortStatus", sortStatus);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	// Edit 30032015, untuk handle Print Download Recap
	public ModelAndView downloadClaimPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DOWNLOADCLAIM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DOWNLOADCLAIM access");
				return errorResult;

			}
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

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

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

			String[] batchReq = { "BatchClaim.ProviderId",
					"BatchClaim.MemberGroupId", "BatchClaim.MemberId" };
			BatchClaim batchClaim = batchClaimService.get(batchClaimId,
					batchReq);

			String groupName = "";

			if (batchClaimId != null) {
				vEqP.add("batchClaimId.batchClaimId");
				vEqQ.add(batchClaimId);

				if (batchClaim.getMemberGroupId() != null) {
					groupName = batchClaim.getMemberGroupId().getGroupName();
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
					// foreign affairs
					"Claim.MemberId", "Claim.MemberId.ParentMember",
					"Claim.CaseCategoryId", "Claim.ClaimStatus"
			// foreign affairs end
			};

			int idx = 1;
			String data = "";

			int total = claimService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, 0, total);

			LogUtil.log(logService, user, "Download Claim - ", "SearchText : "
					+ searchtext + "  SearchBy : " + searchby);

			if (collection != null) {

				// data =
				// BankTransferDocumentConverter.convertToBCA(collection);

				Payment newPayment = new Payment();
				Collection<Payment> paymentCollection = paymentService.search(
						null, null, "batchClaim.batchClaimId",
						batchClaim.getBatchClaimId(), 0, 10);

				if (paymentCollection != null && paymentCollection.size() > 0) {
					Iterator<Payment> iterator = paymentCollection.iterator();

					if (iterator.hasNext()) {
						newPayment = iterator.next();
					}

				}
				// HSSFWorkbook workbook =
				// ClaimDocumentDownloader.downloadClaim(
				// collection, groupName, newPayment);
				HSSFWorkbook workbook = ClaimDocumentDownloader
						.downloadBatchClaim(batchClaim, collection, newPayment);

				java.sql.Date date = new java.sql.Date(
						System.currentTimeMillis());

				String filename = batchClaim.getBatchClaimId() + ".xls";
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
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView printClaimDetailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {

		ModelAndView result = null;

		try {

			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			Configuration configuration = configurationService
					.getSystemConfiguration();
			String institution = configuration.getCompanyCode().toLowerCase();

			location = location + "_" + institution;
			result = new ModelAndView(location);
			java.io.Serializable pkey = batchClaimId;
			String officerName = "";

			String reimbursementPolicyGroupNumber = "";
			String reimbursementPolicyGroupName = "";
			String reimbursementKantorCabang = "";

			ActionUser actionUser = securityService.getActionUser(request);

			String[] required = { "BatchClaim.MemberId",
					"BatchClaim.MemberGroupId", "BatchClaim.ProviderId",
					"BatchClaim.MemberId.MemberGroupId.ClientId" };

			Collection<PaymentReportDetail> reports = null;
			double pembulatan = 0;
			double pembayaranDimuka = 0;

			BatchClaim batchClaim = batchClaimService.get(batchClaimId,
					required);

			result.addObject("batchClaim", batchClaim);

			if (batchClaim != null) {
				reports = claimService.getBatchClaimDetail(batchClaim
						.getBatchClaimId());

				PaymentRecipient paymentRecipient = batchClaim
						.getPaymentRecipient();
				PaymentReportSummary reportSummary = claimItemService
						.getPaymentReportSummary(batchClaim.getBatchClaimId());

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
						reimbursementKantorCabang = batchClaim.getMemberId()
								.getMemberGroupId().getClientId()
								.getClientName();
						reimbursementPolicyGroupName = batchClaim.getMemberId()
								.getMemberGroupId().getGroupName();
						reimbursementPolicyGroupNumber = batchClaim
								.getMemberId().getMemberGroupId()
								.getPolicyNumber();
					} else if (paymentRecipient.getPaymentRecipientId()
							.intValue() == PaymentRecipient.MEMBER_GROUP) {
						insurancePeriode = batchClaim.getMemberGroupId()
								.getEffectiveDate().toString()
								+ " s/d "
								+ batchClaim.getMemberGroupId().getExpireDate()
										.toString();

						reimbursementKantorCabang = batchClaim
								.getMemberGroupId().getClientId()
								.getClientName();
						reimbursementPolicyGroupName = batchClaim
								.getMemberGroupId().getGroupName();
						reimbursementPolicyGroupNumber = batchClaim
								.getMemberGroupId().getPolicyNumber();
					} else if (paymentRecipient.getPaymentRecipientId()
							.intValue() == PaymentRecipient.PROVIDER) {

						if (batchClaim.getProviderId().getContractStartDate() != null) {
							insurancePeriode = batchClaim.getProviderId()
									.getContractStartDate().toString()
									+ " s/d "
									+ batchClaim.getProviderId()
											.getContractEndDate().toString();
						}
					}

					request.setAttribute("insurancePeriode", insurancePeriode);
				}

				if (reports != null) {
					Iterator<PaymentReportDetail> report = reports.iterator();
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
									claimCharge += claim.getClaimChargeValue();

								if (claim.getClaimApprovedValue() != null)
									claimApproved += claim
											.getClaimApprovedValue();

								if (claim.getClaimExcessValue() != null)
									claimExcess += claim.getClaimExcessValue();

								if (claim.getExGratiaValue() != null)
									claimExGratia += claim.getExGratiaValue();

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

			if (actionUser != null) {
				User theUser = actionUser.getUser();

				if (theUser != null) {
					officerName = theUser.getFirstName();

				}
			}

			result.addObject("configuration", configuration);
			result.addObject("reports", reports);
			result.addObject("pembulatan", pembulatan);
			result.addObject("batchClaim", batchClaim);
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
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;

	}

	public ModelAndView printBatchSummaryPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) {

		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			java.sql.Date currentDate = new java.sql.Date(
					System.currentTimeMillis());

			Configuration configuration = configurationService
					.getSystemConfiguration();
			String institution = configuration.getCompanyCode().toLowerCase();
			location = location + "_" + institution;

			result = new ModelAndView(location);
			java.io.Serializable pkey = batchClaimId;
			String preparedBy = "";

			BatchClaim bc = batchClaimService.get(pkey);
			String[] requiredC = { "Claim.MemberId",
					"Claim.MemberId.MemberGroupId", "Claim.MemberGroupId",
					"Claim.ClientId", "Claim.ProviderId" };

			if (bc != null) {
				String[] eqP = { "batchClaimId.batchClaimId", "deletedStatus" };
				Object[] eqQ = { bc.getBatchClaimId(), Integer.valueOf(0) };

				int totalClaim = claimService.getTotal(null, null,
						"batchClaimId.batchClaimId", bc.getBatchClaimId());

				Collection<Claim> claims = claimService.search(null, null, eqP,
						eqQ, requiredC, 0, totalClaim);

				request.setAttribute("Claims", claims);
				request.setAttribute("totalClaim", totalClaim);
			}

			if (user != null) {
				User theUser = user.getUser();

				if (theUser != null) {
					preparedBy = theUser.getUsername().toUpperCase() + "";
				}
			}

			result.addObject("batchClaim", bc);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("currentDate", currentDate);
			result.addObject("preparedBy", preparedBy);
			result.addObject("configuration", configuration);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail",
					Locale.getDefault()));

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
		String listnavigation = request.getParameter("listnavigation") == null ? "welcome"
				: request.getParameter("listnavigation");

		String subnavigation = WebUtil.getParameterString(request,
				"subnavigation", "");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");

		Object user = null;
		ActionUser actionUser = securityService.getActionUser(request);

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String breadcrumb = "";

		try {
			Configuration config = configurationService
					.getSystemConfiguration();

			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				
				//riyan
				String[] required = { "BatchClaim.MemberId",
						"BatchClaim.ClientId", "BatchClaim.MemberGroupId" };
				
				Integer batchClaim = WebUtil.getParameterInteger(request,
						"batchClaimId");
				
				BatchClaim batchClaimx = this.batchClaimService.get(
						batchClaim, required);
				String[] eqParamId = {"batchClaimId.batchClaimId","deletedStatus"};
				Object[] eqValueId = {batchClaimx.getBatchClaimId(),Integer.valueOf(0)};
				
				int totalClaim = claimService.getTotal(null, null,
						eqParamId, eqValueId);
				
				result = detailPerformed(request, response, "detailBatchClaim");
				String batchClaimId = request.getParameter("batchClaimId");
				request.setAttribute("claimTotal", totalClaim);
				breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="
						+ batchClaimId
						+ "\" class=\"linkbreadcrumb\">Detail Batch Claim</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("download")) {
				// result = exportBatchPerformed(request, response,
				// "searchBatchClaim");
				result = searchPerformed(request, response, "searchBatchClaim");
			}

			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchBatchClaim");
				String batchClaimStatus = request.getParameter("status");
				// breadcrumb =
				// "<a href=\"batchclaim\" class=\"linkbreadcrumb\">Search Batch Claim</a>";
				// breadcrumb =
				// "<a href=\"batchclaim?status="+batchClaimStatus+"&navigation=gosearch\" class=\"linkbreadcrumb\">Search Batch Claim</a>";
				breadcrumb = "<a href=\"batchclaim?status="
						+ batchClaimStatus
						+ "&navigation=gosearch\" class=\"linkbreadcrumb\">My Unpaid Invoice</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")
					|| navigation.equalsIgnoreCase("lookupextract")
					|| navigation.equalsIgnoreCase("golookupextract")) {
				result = searchPerformed(request, response, "lookupBatchClaim");
			} else if (navigation.equalsIgnoreCase("list")
					|| subnavigation.equalsIgnoreCase("list")) {
				result = searchPerformed(request, response,
						"listProviderBatchClaim");
				String providerId = request.getParameter("providerId");
				if (listnavigation.equalsIgnoreCase("searchcapitation")) {
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="
							+ providerId
							+ "&listnavigation="
							+ listnavigation
							+ "\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Batch Claim";
				} else {
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="
							+ providerId
							+ "\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Batch Claim";
				}
			} else if (navigation.equalsIgnoreCase("listclient")
					|| subnavigation.equalsIgnoreCase("listclient")) {
				result = searchPerformed(request, response,
						"listClientBatchClaim");
				// String clientId = request.getParameter("clientId");

				breadcrumb = "<a href=\"client?navigation="
						+ navigation
						+ "&clientId="
						+ clientId
						+ "\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Batch Claim";

			} else if (navigation.equalsIgnoreCase("complete")) {
				result = completePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("open")) {
				result = openPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("jsonclosedbatchclaim")
					|| navigation.equalsIgnoreCase("jsontotalopen")) {
				result = jsonTotalPendingPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("close")) {
				result = closePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("printcob")) {
				result = printCOBPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("printbarcode")) {
				result = printBarcode(request, response);
			} else if (navigation.equalsIgnoreCase("searchbalance")
					|| navigation.equalsIgnoreCase("gosearchbalance")
					|| navigation.equalsIgnoreCase("downloadbalance")) {
				result = searchPaymentBalancePerformed(request, response,
						"searchPaymentBalance");
			} else if (navigation.equalsIgnoreCase("verifybulk")) {
				result = verifyBulkPerformed(request, response);
			} else if (navigation.equalsIgnoreCase("downloadclaim")) {
				result = downloadClaimPerformed(request, response,
						"detailBatchClaim");
			} else if (navigation.equalsIgnoreCase("prevoid")
					|| navigation.equalsIgnoreCase("void")) {

			} else if (navigation.equalsIgnoreCase("printpayment")) {
				result = printBatchSummaryPerformed(request, response,
						"claimPaymentForm");
			} else if (navigation.equalsIgnoreCase("printpaymentdetails")) {
				result = printClaimDetailPerformed(request, response,
						"printBatchPaymentDetail");
			}
			// Add 20150417 by FVO, for Print Recap (Surat Jalan)
			else if (navigation.equalsIgnoreCase("printdownloadrecap")) {
				String location = "printDownloadRecap_"
						+ config.getCompanyCode();

				String path = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jsp/batchClaim/");
				File file = new File(path, location + ".jsp");
				if (!file.exists()) {
					location = "printDownloadRecap";
				}

				result = detailPerformed(request, response, location);
			} else {
				result = searchPerformed(request, response, "searchBatchClaim");
				// breadcrumb =
				// "<a href=\"batchclaim?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Batch Claim</a>";
				breadcrumb = "<a href=\"batchclaim?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">My Invoice History</a>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-

}
