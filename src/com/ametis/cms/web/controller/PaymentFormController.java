package com.ametis.cms.web.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.OutstandingService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.MoneyParser;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PaymentForm;

// imports+ 

// imports- 

/**
 * Payment is a mapping of payment Table.
 */
public class PaymentFormController extends SimpleFormController
// extends+

// extends-

{

	PaymentService paymentService;
	ResourceBundleMessageSource alertProperties;
	private ClaimService claimService;
	// foreign affairs

	BatchClaimService batchClaimService;

	OutstandingService outstandingService;
	private SecurityService securityService;
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setOutstandingService(OutstandingService obj) {
		this.outstandingService = obj;
	}

	public OutstandingService getOutstandingService() {
		return this.outstandingService;
	}

	MemberService memberService;

	public void setMemberService(MemberService obj) {
		this.memberService = obj;
	}

	public MemberService getMemberService() {
		return this.memberService;
	}

	ProviderService providerService;

	public void setProviderService(ProviderService obj) {
		this.providerService = obj;
	}

	public ProviderService getProviderService() {
		return this.providerService;
	}

	// -- foreign affairs end

	public void setPaymentService(PaymentService object) {
		this.paymentService = object;
	}

	public PaymentService getPaymentService() {
		return this.paymentService;
	}

	// generate by default
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPropertiesUtil(ResourceBundleMessageSource object) {
		this.alertProperties = object;
	}

	public ResourceBundleMessageSource getPropertiesUtil() {
		return this.alertProperties;
	}

	public PaymentFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		PaymentForm tmp = null;
		Integer batchClaims = null;
		Integer paymentId = WebUtil.getParameterInteger(request, "paymentId");
		String navigation = WebUtil.getParameterString(request, "navigation",
				"");
		Integer batchClaimId = WebUtil.getParameterInteger(request,
				"batchClaimId");
		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (paymentId != null) {
			java.io.Serializable pkey = paymentId;
			Payment object = paymentService.get(pkey);

			batchClaims = object.getBatchClaim().getBatchClaimId();
			if (object != null) { // edit object

				tmp = new PaymentForm(object);
				// foreign affairs

				if (object.getOutstandingId() != null) {
					tmp.setOutstandingId(""
							+ object.getOutstandingId().getOutstandingId());
				}
				if (object.getMemberId() != null) {
					tmp.setMemberId("" + object.getMemberId().getMemberId());
				}
				if (object.getProviderId() != null) {
					tmp.setProviderId(""
							+ object.getProviderId().getProviderId());
				}

				BatchClaim batchClaim = object.getBatchClaim();

				if (batchClaim != null) {
					String[] reqBC = { "BatchClaim.MemberId",
							"BatchClaim.MemberGroupId", "BatchClaim.ProviderId" };

					batchClaim = batchClaimService.get(
							batchClaim.getBatchClaimId(), reqBC);
					tmp.setBatchNumber(batchClaim.getBatchClaimNumber());

					PaymentRecipient recipient = batchClaim
							.getPaymentRecipient();
					if (recipient != null) {
						if (recipient.getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER) {

							tmp.setPaymentRecipient(batchClaim.getMemberId()
									.getFirstName());
						} else if (recipient.getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP) {

							tmp.setPaymentRecipient(batchClaim
									.getMemberGroupId().getGroupName());
						} else if (recipient.getPaymentRecipientId().intValue() == PaymentRecipient.PROVIDER) {
							tmp.setPaymentRecipient(batchClaim.getProviderId()
									.getProviderName());
						}
					}
				}
				//

				String breadcrumb = "<a href=\"payment?navigation=detail&paymentId="
						+ paymentId
						+ "\" class=\"linkbreadcrumb\">Detail Payment</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Payment";
				request.setAttribute("breadcrumb", breadcrumb);
				request.setAttribute("navigation", navigation);

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PaymentForm();
				// foreign affairs

				Integer outstandingId = WebUtil.getParameterInteger(request,
						"outstandingId");

				if (outstandingId != null) {
					Outstanding forClass = new Outstanding();
					forClass.setOutstandingId(outstandingId);
					tmp.setOutstandingId("" + outstandingId);

					Outstanding outstanding = this.outstandingService
							.get(outstandingId);
					tmp.getPayment().setOutstandingId(outstanding);
				} else {
					Outstanding forClass = new Outstanding();
					// tmp.setOutstandingId("");
					tmp.getPayment().setOutstandingId(forClass);
				}

				Integer memberId = WebUtil.getParameterInteger(request,
						"memberId");

				if (memberId != null) {
					Member forClass = new Member();
					forClass.setMemberId(memberId);
					tmp.setMemberId("" + memberId);

					Member member = this.memberService.get(memberId);
					tmp.getPayment().setMemberId(member);
				} else {
					Member forClass = new Member();
					// tmp.setMemberId("");
					tmp.getPayment().setMemberId(forClass);
				}

				Integer providerId = WebUtil.getParameterInteger(request,
						"providerId");

				if (providerId != null) {
					Provider forClass = new Provider();
					forClass.setProviderId(providerId);
					tmp.setProviderId("" + providerId);

					Provider provider = this.providerService.get(providerId);
					tmp.getPayment().setProviderId(provider);
				} else {
					Provider forClass = new Provider();
					// tmp.setProviderId("");
					tmp.getPayment().setProviderId(forClass);
				}

				// -- foreign affairs end
			}

		} // mau edit end
		else { // bikin baru
			tmp = new PaymentForm();
			// foreign affairs

			BatchClaim batchClaim = null;

			if (batchClaimId != null) {

				String breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="
						+ batchClaimId
						+ "\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Payment";
				request.setAttribute("breadcrumb", breadcrumb);

				String[] required = { 
						"BatchClaim.MemberId",  "BatchClaim.ProviderId",
						"BatchClaim.MemberGroupId" };
				batchClaim = batchClaimService.get(batchClaimId, required);

				String[] eqClaimParam = { "batchClaimId.batchClaimId",
						"deletedStatus", "claimStatus.caseStatusId" };
				Object[] eqClaimValue = { batchClaim.getBatchClaimId(), 0,
						Claim.CLAIM_CHECKED };

				int totalClaim = claimService.getTotal(null, null,
						eqClaimParam, eqClaimValue);
				Collection<Claim> claimList = claimService.search(null, null,
						eqClaimParam, eqClaimValue, 0, totalClaim);

				double approvedValue = 0.0;
				double chargeValue = 0.0;
				double pembayaranDimuka = 0.0;

				if (claimList != null) {
					for (Claim claim : claimList) {

						if (claim != null) {
							approvedValue += claim.getClaimApprovedValue();
							chargeValue += claim.getClaimChargeValue();

							if (claim.getPembayaranDimuka() != null) {
								pembayaranDimuka += claim.getPembayaranDimuka();
							}
						}
					}
				}

				Double materai = batchClaim.getBiayaMaterai();
				String paymentValue = approvedValue + "";
				tmp.setClaimPaymentValue(paymentValue);

				if (batchClaim != null) {
					PaymentRecipient recipient = batchClaim
							.getPaymentRecipient();

					if (recipient.getPaymentRecipientId() == PaymentRecipient.MEMBER) {
						Member member = batchClaim.getMemberId();
						if (member != null) {
							tmp.setAccountNumber(member.getBankAccount());
							tmp.setBankName(member.getBank());
							tmp.setPayeeName(member.getBankAccountName());
							tmp.setBankBranch(member.getBankBranch());
							tmp.setPaymentRecipient(StringUtils.defaultString(
									member.getFirstName(), "")
									+ " "
									+ StringUtils.defaultString(
											member.getLastName(), ""));

							tmp.setMemberId(member.getMemberId().toString());
						}
					} else if (recipient.getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP) {
						MemberGroup memberGroup = batchClaim.getMemberGroupId();

						if (memberGroup != null) {
							tmp.setAccountNumber(memberGroup.getBankAccount());
							tmp.setBankName(memberGroup.getBank());
							tmp.setPayeeName(memberGroup.getBankAccountName());
							tmp.setBankBranch(memberGroup.getBankBranch());
							tmp.setPaymentRecipient(memberGroup.getGroupName());
						}

					} else if (recipient.getPaymentRecipientId() == PaymentRecipient.PROVIDER) {
						java.io.Serializable pkey = batchClaim.getProviderId().getProviderId();
						Provider provider = providerService.get(pkey);
						
						double discountValue = 0.0;

						if (provider != null) {
							tmp.setAccountNumber(provider.getBankAccount());
							tmp.setBankName(provider.getBank());
							tmp.setPayeeName(provider.getAccountName());
							tmp.setBankBranch(provider.getBankBranch());
							tmp.setPaymentRecipient(provider.getProviderName());

							if (provider.getDiscount() != null) {
								double percentage = provider.getDiscount()
										.doubleValue();

								if (percentage <= 100 && percentage >= 0) {

									discountValue = chargeValue * percentage
											/ 100;
									String info = " Provider Discount = "
											+ percentage + " %";
									String remarks = info + tmp.getRemarks();

									tmp.setRemarks(remarks);

									if (discountValue > 0.0) {
										BigDecimal bc = new BigDecimal(
												discountValue);
										if (bc != null) {
											tmp.setDiscount(bc.toPlainString());
										}
									}
								}
							}
						}

						double biayaMaterai = 0.0;

						if (materai != null) {
							// Edit 20150420 by FVO, change from
							// batchClaimId.batchClaimId -->
							// batchClaim.batchClaimId
							String[] eqParamPayment = {
									"batchClaim.batchClaimId", "deletedStatus" };
							Object[] eqValuePayment = { batchClaimId, 0 };
							int totalPayment = paymentService.getTotal(null,
									null, eqParamPayment, eqValuePayment);

							Collection<Payment> paymentList = paymentService
									.search(null, null, eqParamPayment,
											eqValuePayment, 0, totalPayment);

							boolean materaiHasPaid = false;
							if (paymentList != null) {
								for (Payment payment : paymentList) {

									if (payment != null
											&& payment.getBiayaMaterai() != null
											&& payment.getBiayaMaterai()
													.doubleValue() > 0) {
										materaiHasPaid = true;
									}
								}
							}
							if (!materaiHasPaid) {
								BigDecimal bc = new BigDecimal(materai);

								tmp.setMaterai(bc.toPlainString());
								biayaMaterai = batchClaim.getBiayaMaterai() == null ? 0.0
										: batchClaim.getBiayaMaterai();
							}
						}

						BigDecimal payment = new BigDecimal(chargeValue
								- pembayaranDimuka + biayaMaterai
								- discountValue);

						paymentValue = payment.toEngineeringString();
					}
					tmp.setBatchNumber(batchClaim.getBatchClaimNumber());
					tmp.setClaimPaymentValue(paymentValue);
					tmp.setBatchClaim(batchClaimId.toString());

					request.setAttribute("batchClaim", batchClaim);
				}
			}

		}

		request.setAttribute("batchClaimId", batchClaims);
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		PaymentForm paymentForm = (PaymentForm) command;
		Payment payment = paymentForm.getPayment();

		// errors.setNestedPath("payment");
		// getValidator().validate(payment, errors);
		// errors.setNestedPath("");
		errors.printStackTrace();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
		 * ini dipake buat populate data - data yang dibutuhkan contoh : Problem
		 * membutuhkan ProblemCategory nah fungsi method ini yaitu untuk
		 * populate list problem category ke jsp nanti biar bisa ditangkep sama
		 * jspnya
		 * 
		 * contoh : Collection pc = pcontroller.searchPC();
		 * 
		 * model.addObject("pcbeans", pc);
		 * 
		 */
		Integer paymentId = WebUtil.getParameterInteger(request, "paymentId");
		String navigation = WebUtil.getParameterString(request, "navigation",
				"");
		if (paymentId != null) {

			if (navigation.equalsIgnoreCase("deliver")) {
				String[] eqParam = { "deletedStatus", "paymentId.paymentId",
						"claimStatus.caseStatusId" };
				Object[] eqValue = { 0, paymentId, Claim.CLAIM_PAYMENT_ISSUED };

				int total = claimService.getTotal(null, null, eqParam, eqValue);
				Collection<Claim> claimList = claimService.search(null, null,
						eqParam, eqValue, 0, total);

				model.put("claimList", claimList);
			} else if (navigation.equalsIgnoreCase("confirm")) {
				String[] eqParam = { "deletedStatus", "paymentId.paymentId",
						"claimStatus.caseStatusId" };
				Object[] eqValue = { 0, paymentId,
						Claim.CLAIM_PAYMENT_DISPOSITIONED };

				int total = claimService.getTotal(null, null, eqParam, eqValue);
				Collection<Claim> claimList = claimService.search(null, null,
						eqParam, eqValue, 0, total);

				model.put("claimList", claimList);
			}
		}

		request.setAttribute("navigation", navigation);
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PaymentForm paymentForm = (PaymentForm) command;

		Payment res = null;
		String alertMsg = "";

		RedirectView redirectView = new RedirectView("payment" + "?alert="
				+ alertMsg);
		try {
			// foreign affairs

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (paymentForm.isNewPayment()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEPAYMENT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEPAYMENT access");
					return errorResult;

				}

				res = paymentForm.getPayment();
				if (res.getPaymentValue() != null) {
					res.setValueDescription(MoneyParser.convert(res
							.getPaymentValue().longValue()));
				} else {
					double paymentValue = 0.0;

					if (res.getClaimPaymentValue() != null) {
						paymentValue += res.getClaimPaymentValue();
					}
					if (res.getAdministrationCost() != null) {
						paymentValue += res.getAdministrationCost();
					}
					res.setValueDescription(MoneyParser
							.convert((long) paymentValue));
					res.setPaymentValue(paymentValue);
				}
				res = paymentService.create(res, user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.payment", null, "success",
							Locale.getDefault());

					redirectView = new RedirectView(
							"payment?navigation=detail&paymentId="
									+ res.getPaymentId() + "&alert=" + alertMsg);
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.payment", null, "fail",
							Locale.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEPAYMENT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEPAYMENT access");
					return errorResult;

				}
				res = paymentForm.getPayment();
				if (res.getPaymentValue() != null) {
					res.setValueDescription(MoneyParser.convert(res
							.getPaymentValue().longValue()));
				} else {
					double paymentValue = 0.0;

					if (res.getClaimPaymentValue() != null) {
						paymentValue += res.getClaimPaymentValue();
					}
					if (res.getAdministrationCost() != null) {
						paymentValue += res.getAdministrationCost();
					}
					res.setValueDescription(MoneyParser
							.convert((long) paymentValue));
					res.setPaymentValue(paymentValue);
				}

				if (navigation.equalsIgnoreCase("confirm")) {
					String[] claimList = WebUtil.getParameterStringArray(
							request, "claimList");
					res = paymentService.finalize(paymentForm.getPayment(),
							claimList, user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.confirm.payment", null, "success",
								Locale.getDefault());
						redirectView = new RedirectView(
								"payment?navigation=detail&paymentId="
										+ res.getPaymentId() + "&alert="
										+ alertMsg);
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.confirm.payment", null, "fail",
								Locale.getDefault());

					}
				} else if (navigation.equalsIgnoreCase("deliver")) {
					String[] claimList = WebUtil.getParameterStringArray(
							request, "claimList");
					res = paymentService.deliverPayment(
							paymentForm.getPayment(), claimList, user);

					if (res != null) {
						alertMsg = "<b>Success Payment Disposition</b>";
						redirectView = new RedirectView(
								"payment?navigation=detail&paymentId="
										+ res.getPaymentId() + "&alert="
										+ alertMsg);
					} else {
						alertMsg = "<b>Failed Payment Disposition</b>";

					}
				} else {

					res = paymentService.update(paymentForm.getPayment(), user);

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.update.payment", null, "success",
								Locale.getDefault());
						redirectView = new RedirectView(
								"payment?navigation=detail&paymentId="
										+ res.getPaymentId() + "&alert="
										+ alertMsg);
					} else {
						alertMsg = alertProperties.getMessage(
								"fail.update.payment", null, "fail",
								Locale.getDefault());

					}
				}

			}
		} catch (Exception ex) {
			if (paymentForm.isNewPayment()) {
				request.setAttribute(
						"alert",
						alertProperties.getMessage("fail.create.payment", null,
								"fail", Locale.getDefault())
								+ " "
								+ ex.getMessage());
			} else {
				request.setAttribute(
						"alert",
						alertProperties.getMessage("fail.update.payment", null,
								"fail", Locale.getDefault())
								+ " "
								+ ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(redirectView);
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);

		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);

		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		binder.registerCustomEditor(java.lang.Double.class,
				new CustomNumberEditor(java.lang.Double.class, nf, true));
	}
	// class+

	// class-

}
