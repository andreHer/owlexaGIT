package com.ametis.cms.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.PaymentDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ContactPerson;
import com.ametis.cms.datamodel.FloatingFundUsageType;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MessageTemplate;
import com.ametis.cms.datamodel.Notification;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentInstallment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.PaymentDto;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ContactPersonService;
import com.ametis.cms.service.NotificationService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.service.OutstandingService;
import com.ametis.cms.service.PaymentInstallmentService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.util.BankTransferDocumentConverter;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * Payment is a servlet controller for payment Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class PaymentServiceImpl implements PaymentService

// extends+

// extends-

{

	private PaymentDao paymentDao;
	private OutstandingService outstandingService;

	private ActivityLogService activityLogService;
	private BatchClaimService batchClaimService;
	private NumberCounterService numberCounterService;
	private ClaimService claimService;
	private ClientService clientService;
	private ConfigurationService configurationService;
	private NotificationService notificationService;
	private ContactPersonService contactPersonService;
	private PolicyCoverageFundService policyCoverageFundService;
	private PaymentInstallmentService paymentInstallmentService;

	public PaymentInstallmentService getPaymentInstallmentService() {
		return paymentInstallmentService;
	}

	public void setPaymentInstallmentService(PaymentInstallmentService paymentInstallmentService) {
		this.paymentInstallmentService = paymentInstallmentService;
	}

	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}

	public void setPolicyCoverageFundService(PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public NumberCounterService getNumberCounterService() {
		return numberCounterService;
	}

	public void setNumberCounterService(NumberCounterService numberCounterService) {
		this.numberCounterService = numberCounterService;
	}

	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	public OutstandingService getOutstandingService() {
		return outstandingService;
	}

	public void setOutstandingService(OutstandingService outstandingService) {
		this.outstandingService = outstandingService;
	}

	public void setPaymentDao(PaymentDao object) {
		this.paymentDao = object;
	}

	public PaymentDao getPaymentDao() {
		return this.paymentDao;
	}

	/*
	 * Method create (Payment object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */

	public Payment finalize(Payment object, String[] claimList, ActionUser actionUser) throws Exception {
		Payment result = null;

		if (object != null) {

			boolean isFullPaid = false;
			result = object;
			if (result != null) {
				BatchClaim batchClaim = result.getBatchClaim();

				if (batchClaim != null) {

					double paidValue = 0.0;
					double totalBatchPaidValue = 0.0;

					if (claimList != null) {

						for (int i = 0; i < claimList.length; i++) {
							if (claimList[i] != null && !claimList[i].equalsIgnoreCase("")) {
								Claim claimObject = claimService.get(Integer.valueOf(claimList[i]));

								if (claimObject != null) {
									if (claimObject.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PAYMENT_ISSUED) {
										claimObject.setPaymentId(object);

										claimObject.setPaidTime(
												new java.sql.Timestamp(object.getPaymentConfirmDate().getTime()));

										claimService.pay(claimObject, actionUser);

										if (claimObject.getPaidToProvider() != null) {
											paidValue += claimObject.getPaidToProvider();
										} else {
											if (claimObject.getClaimTypeId().getClaimTypeId()
													.intValue() == ClaimType.CASHLESS) {
												paidValue += claimObject.getClaimChargeValue();
											} else {
												paidValue += claimObject.getClaimApprovedValue();
											}
										}
									}
								}
							}
						}
					}

					String[] required2 = {"BatchClaim.MemberId","BatchClaim.MemberId.CurrentPolicyId","BatchClaim.ProviderId","BatchClaim.MemberGroupId"};
					
					BatchClaim theBatch = batchClaimService.get(batchClaim.getBatchClaimId(),required2);
					
					
					
					String[] eqParamPaid = {"batchClaimId.batchClaimId","deletedStatus"};
					Object[] eqValuePaid = {theBatch.getBatchClaimId(),Integer.valueOf(0)};
					
					int totalPaidClaim = claimService.getTotal(null,null,eqParamPaid,eqValuePaid);

	
					int totalUnpaidClaim = 0;

					Collection<Claim> claimPaidList = claimService.search(null, null, eqParamPaid, eqValuePaid, 0,
							totalPaidClaim);

					for (Iterator iterator = claimPaidList.iterator(); iterator.hasNext();) {

						Claim claim = (Claim) iterator.next();

						if (claim != null && claim.getPaidToProvider() != null
								&& claim.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAID) {
							totalBatchPaidValue += claim.getPaidToProvider();
						}

						if (claim.getClaimStatus().getCaseStatusId().intValue() != Claim.CLAIM_PAID) {
							totalUnpaidClaim += 1;
						}

					}

					theBatch.setBatchClaimPaidValue(totalBatchPaidValue);

					if (totalUnpaidClaim == 0) {
						CaseStatus claimStatus = new CaseStatus();

						claimStatus.setCaseStatusId(BatchClaim.PAID);
						theBatch.setBatchClaimStatus(claimStatus);
					} else {
						if (totalBatchPaidValue > 0) {
							CaseStatus claimStatus = new CaseStatus();

							claimStatus.setCaseStatusId(BatchClaim.INSTALLMENT_PAYMENT);
							theBatch.setBatchClaimStatus(claimStatus);
						}
					}
					if (result.getPaymentConfirmDate() != null) {
						theBatch.setPaymentDate(new java.sql.Date(result.getPaymentConfirmDate().getTime()));
					}

					batchClaimService.update(theBatch, actionUser);

					// Configuration configuration =
					// configurationService.getClientConfiguration(theBatch.getClientId().getClientId());

					Configuration configuration = configurationService.getSystemConfiguration();


					if (configuration != null && configuration.getIsUsingClientNotification() != null) {
						if (configuration.getIsUsingClientNotification().intValue() == 1) {
							if (claimPaidList != null) {

								if (theBatch.getPaymentRecipient().getPaymentRecipientId()
										.intValue() == PaymentRecipient.MEMBER) {

									String destination = "";
									MessageTemplate template = new MessageTemplate();

									Notification notification = new Notification();

									if (theBatch.getMemberId().getMemberId() != null
											&& theBatch.getMemberId().getEmail() != null) {
										destination = theBatch.getMemberId().getEmail();
										template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
										notification.setMemberId(theBatch.getMemberId());
									}

									notification.setBatchClaimId(theBatch);
									notification.setTemplateId(template);
									notification.setDestination(destination);
									notification.setStatus(0);
									notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
									notification.setSender("system-daemon");

							


									if (notification.getDestination() != null) {

										notificationService
												.createPAID(notification,
														actionUser, theBatch,
														claimPaidList, object);
									}
//
								}
//<<<<<<< .working
								if (theBatch.getPaymentRecipient().getPaymentRecipientId()
										.intValue() == PaymentRecipient.MEMBER_GROUP) {

									String destination = "";
									MessageTemplate template = new MessageTemplate();

									Notification notification = new Notification();

									if (theBatch.getMemberGroupId() != null
											&& theBatch.getMemberGroupId().getEmail() != null) {
										destination = theBatch.getMemberGroupId().getEmail();
										template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
										notification.setMemberId(theBatch.getMemberId());
									}

									notification.setBatchClaimId(theBatch);
									notification.setTemplateId(template);
									notification.setDestination(destination);
									notification.setStatus(0);
									notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
									notification.setSender("system-daemon");


									if (notification.getDestination() != null) {

										notificationService
												.createPAID(notification,
														actionUser, theBatch,
														claimPaidList, object);
									}
//>>>>>>> .merge-right.r1385
								}
//<<<<<<< .working
								if (theBatch.getPaymentRecipient().getPaymentRecipientId()
										.intValue() == PaymentRecipient.PROVIDER) {

									String destination = "";
									MessageTemplate template = new MessageTemplate();

									Notification notification = new Notification();

									if (theBatch.getProviderId() != null
											&& theBatch.getProviderId().getEmail() != null) {
										
									
										destination = theBatch.getProviderId().getEmail();
										template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
										notification.setMemberId(theBatch.getMemberId());
//
									}
									notification.setBatchClaimId(theBatch);
									notification.setTemplateId(template);
									notification.setDestination(destination);
									notification.setStatus(0);
									notification
											.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
									notification.setSender("system-daemon");

									if (notification.getDestination() != null) {

										notificationService
												.createPAID(notification,
														actionUser, theBatch,
														claimPaidList, object);
									}
//>>>>>>> .merge-right.r1385
								}
							}
						}

					}
				}
				// BatchClaim batch = result.get
			}
			String[] eqCDVIssuedParam = { "paymentId.paymentId", "claimStatus.caseStatusId", "deletedStatus" };
			Object[] eqCDVIssuedValue = { object.getPaymentId(), Claim.CLAIM_PAYMENT_ISSUED, 0 };

			String[] eqPaidParam = { "paymentId.paymentId", "claimStatus.caseStatusId", "deletedStatus" };
			Object[] eqPaidValue = { object.getPaymentId(), Claim.CLAIM_PAID, 0 };

			int totalUnpaid = claimService.getTotal(null, null, eqCDVIssuedParam, eqCDVIssuedValue);
			int totalPaid = claimService.getTotal(null, null, eqPaidParam, eqPaidValue);

			double totalPaidValue = 0.0;

			if (totalPaid > 0) {
				Collection<Claim> claimPaidList = claimService.search(null, null, eqPaidParam, eqPaidValue, 0,
						totalPaid);

				for (Iterator iterator = claimPaidList.iterator(); iterator.hasNext();) {

					Claim claim = (Claim) iterator.next();

					if (claim != null) {
						totalPaidValue += claim.getPaidToProvider();
					}

				}

			}

			if (totalUnpaid == 0) {
				isFullPaid = true;
			} else {
				isFullPaid = false;
			}

			PaymentStatus status = new PaymentStatus();

			if (isFullPaid) {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);
			} else {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_PARTIAL_PAID);
			}
			object.setPaymentStatus(status);
			object.setConfirmedPaymentValue(totalPaidValue);

			update(object, actionUser);
		}
		return result;
	}

	public Payment finalizeFromBatch(Payment object, ActionUser actionUser) throws Exception {
		Payment result = null;

		if (object != null) {

			boolean isFullPaid = false;
			result = object;
			if (result != null) {
				BatchClaim batchClaim = result.getBatchClaim();

				if (batchClaim != null) {

					double paidValue = 0.0;
					double totalBatchPaidValue = 0.0;

					BatchClaim theBatch = batchClaimService.get(batchClaim.getBatchClaimId());

					String[] eqParamPaid = { "batchClaimId.batchClaimId", "deletedStatus" };
					Object[] eqValuePaid = { theBatch.getBatchClaimId(), Integer.valueOf(0) };

					int totalPaidClaim = claimService.getTotal(null, null, eqParamPaid, eqValuePaid);
					int totalUnpaidClaim = 0;

					Collection<Claim> claimPaidList = claimService.search(null, null, eqParamPaid, eqValuePaid, 0,
							totalPaidClaim);

					for (Iterator iterator = claimPaidList.iterator(); iterator.hasNext();) {

						Claim claim = (Claim) iterator.next();

						if (claim != null && claim.getPaidToProvider() != null
								&& claim.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAID) {
							totalBatchPaidValue += claim.getPaidToProvider();
						}

						if (claim.getClaimStatus().getCaseStatusId().intValue() != Claim.CLAIM_PAID) {
							totalUnpaidClaim += 1;
						}

					}

					theBatch.setBatchClaimPaidValue(totalBatchPaidValue);

					if (totalUnpaidClaim == 0) {
						CaseStatus claimStatus = new CaseStatus();

						claimStatus.setCaseStatusId(BatchClaim.PAID);
						theBatch.setBatchClaimStatus(claimStatus);
					} else {
						if (totalBatchPaidValue > 0) {
							CaseStatus claimStatus = new CaseStatus();

							claimStatus.setCaseStatusId(BatchClaim.INSTALLMENT_PAYMENT);
							theBatch.setBatchClaimStatus(claimStatus);
						}
					}

					theBatch.setPaymentDate(new java.sql.Date(result.getPaymentTime().getTime()));

					batchClaimService.update(theBatch, actionUser);

					Configuration configuration = configurationService
							.getClientConfiguration(theBatch.getClientId().getClientId());

					if (configuration == null) {
						configuration = configurationService.getSystemConfiguration();
					}
					if (configuration != null && configuration.getIsUsingClientNotification() != null) {
						if (configuration.getIsUsingClientNotification().intValue() == 1) {
							if (theBatch.getPaymentRecipient().getPaymentRecipientId()
									.intValue() == PaymentRecipient.MEMBER) {

								String destination = "";
								MessageTemplate template = new MessageTemplate();

								Notification notification = new Notification();

								if (theBatch.getMemberId() != null && theBatch.getMemberId().getEmail() != null) {
									destination = theBatch.getMemberId().getEmail();
									template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
									notification.setMemberId(theBatch.getMemberId());
								}

								notification.setBatchClaimId(theBatch);
								notification.setTemplateId(template);
								notification.setDestination(destination);
								notification.setStatus(0);
								notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
								notification.setSender("system-daemon");

								notificationService.create(notification, actionUser);
							}
						}

					}
				}
				// BatchClaim batch = result.get
			}
			String[] eqCDVIssuedParam = { "paymentId.paymentId", "claimStatus.caseStatusId", "deletedStatus" };
			Object[] eqCDVIssuedValue = { object.getPaymentId(), Claim.CLAIM_PAYMENT_ISSUED, 0 };

			String[] eqPaidParam = { "paymentId.paymentId", "claimStatus.caseStatusId", "deletedStatus" };
			Object[] eqPaidValue = { object.getPaymentId(), Claim.CLAIM_PAID, 0 };

			int totalUnpaid = claimService.getTotal(null, null, eqCDVIssuedParam, eqCDVIssuedValue);
			int totalPaid = claimService.getTotal(null, null, eqPaidParam, eqPaidValue);

			double totalPaidValue = 0.0;

			if (totalPaid > 0) {
				Collection<Claim> claimPaidList = claimService.search(null, null, eqPaidParam, eqPaidValue, 0,
						totalPaid);

				for (Iterator iterator = claimPaidList.iterator(); iterator.hasNext();) {

					Claim claim = (Claim) iterator.next();

					if (claim != null && claim.getPaidToProvider() != null) {
						totalPaidValue += claim.getPaidToProvider();
					} else {
						if (claim.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT) {
							totalPaidValue += claim.getClaimApprovedValue();
						} else {
							totalPaidValue += claim.getClaimChargeValue();
						}

					}

				}

			}

			if (totalUnpaid == 0) {
				isFullPaid = true;
			} else {
				isFullPaid = false;
			}

			PaymentStatus status = new PaymentStatus();

			if (isFullPaid) {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);
			} else {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_PARTIAL_PAID);
			}
			object.setPaymentStatus(status);
			object.setConfirmedPaymentValue(totalPaidValue);

			update(object, actionUser);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Payment create(Payment object, ActionUser actionUser) throws Exception {

		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));

		if (actionUser != null) {

			User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}
		int numberCounter = 0;

		Client client = null;
		BatchClaim batch = null;

		if (object.getBatchClaim() != null) {
			batch = batchClaimService.get(object.getBatchClaim().getBatchClaimId());

			if (batch != null) {
				client = batch.getClientId();
			}
		}

		if (client == null && batch != null) {
			client = clientService.get(batch.getClientId().getClientId());
		}

		// Configuration configuration =
		// configurationService.getClientConfiguration(client.getClientId());

		Configuration configuration = configurationService.getSystemConfiguration();

		Integer isUsingSequence = configuration.getIsUsingSequenceNumber();

		if (isUsingSequence != null && isUsingSequence.intValue() == 1) {
			String seqSQL = configuration.getCdvNumberSeqName();

			Session session = paymentDao.getPaymentSession();
			if (session != null) {
				SQLQuery q = session.createSQLQuery(seqSQL);

				List<Object> list = q.list();

				if (list != null && !list.isEmpty()) {
					Iterator<Object> iterator = list.iterator();

					if (iterator.hasNext()) {
						Object nextval = iterator.next();

						if (nextval != null) {

							BigInteger num = (BigInteger) nextval;
							if (num != null) {
								numberCounter = num.intValue();
							}
						}
					}
				}
			}
		} else {
			numberCounter = configuration.getCdvNumber();
		}

		object.setAccountNumberClean(BankTransferDocumentConverter.formatToFriendlyAccount(object.getAccountNumber()));
		String counter = generatePaymentCounter(numberCounter);
		String number = generatePaymentNumber(counter, client);
		object.setPaymentCounterNumber(counter);
		object.setPaymentNumber(number);
		object.setPaymentBatchingStatus(Integer.valueOf(0));
		PaymentStatus payStatus = new PaymentStatus();
		payStatus.setPaymentStatusId(PaymentStatus.PAYMENT_OPEN);
		object.setPaymentStatus(payStatus);

		object.setPaymentTime(new java.sql.Date(System.currentTimeMillis()));

		System.out.println("PROVIDER ID : " + object.getProviderId());
		Payment result = paymentDao.create(object);

		if (result != null) {
			Outstanding outstanding = result.getOutstandingId();

			if (outstanding != null) {
				BatchClaim batchClaim = outstanding.getBatchClaimId();

				if (batchClaim != null) {

				}
			}

			BatchClaim batchClaim = result.getBatchClaim();


			if (batchClaim != null) {
				String[] required2 = { "BatchClaim.MemberId", "BatchClaim.MemberId.CurrentPolicyId","BatchClaim.ProviderId", "BatchClaim.MemberGroupId" };

				BatchClaim theBatch = batchClaimService.get(batchClaim.getBatchClaimId(), required2);


				theBatch.setBatchClaimPaidValue(result.getPaymentValue());
				System.out.println("BATCH CLAIM GETTING CLIENT : " + theBatch.getClientId().getClientId());

				System.out.println("CLient Service : " + clientService);

				Collection<Policy> policyList = batchClaimService.getPolicyList(theBatch.getBatchClaimId());
				PolicyCoverageFund warnedFund = null;

				for (Iterator iterator = policyList.iterator(); iterator.hasNext();) {
					Policy policy = (Policy) iterator.next();

					if (policy != null && policy.getIsUsingFloatingFund() != null) {

						boolean doSendWarning = false;
						int warningType = 0;

						if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE) {
							boolean send = false;

							String[] eqParam = { "deletedStatus", "policyId.policyId" };
							Object[] eqValue = { 0, policy.getPolicyId() };
							int total = policyCoverageFundService.getTotal(null, null, eqParam, eqValue);
							Collection<PolicyCoverageFund> coverageList = policyCoverageFundService.search(null, null,
									eqParam, eqValue, 0, total);

							for (Iterator iterator2 = coverageList.iterator(); iterator2.hasNext();) {
								PolicyCoverageFund policyCoverageFund = (PolicyCoverageFund) iterator2.next();
								if (policyCoverageFund != null) {
									if (policyCoverageFund.getMinimumAsoValue() >= policyCoverageFund
											.getCurrentFundValue()) {
										send = true;
										warnedFund = policyCoverageFund;
										break;
									}
								}

							}

							if (send) {
								if (policy.getLastFundWarningDate() == null) {
									doSendWarning = true;
								} else {
									if (policy.getLastFundWarningDate()
											.before(new java.sql.Date(System.currentTimeMillis()))) {
										doSendWarning = true;
									}
								}
							}
						} else if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY) {
							boolean send = false;

							if (policy.getMinimumPolicyFund() != null && policy.getCurrentPolicyFund() != null) {
								if (policy.getMinimumPolicyFund().doubleValue() >= policy.getCurrentPolicyFund()
										.doubleValue()) {
									send = true;
								}
							}

							if (send) {
								if (policy.getLastFundWarningDate() == null) {
									doSendWarning = true;
								} else {
									if (policy.getLastFundWarningDate()
											.before(new java.sql.Date(System.currentTimeMillis()))) {
										doSendWarning = true;
									}
								}
							}
						}

						if (doSendWarning) {

							if (client != null) {

								String[] eqParam = { "clientId.clientId", "deletedStatus", "paymentOfficer" };
								Object[] eqValue = { client.getClientId(), Integer.valueOf(0), 1 };
								int totalContact = contactPersonService.getTotal(null, null, eqParam, eqValue);
								Collection<ContactPerson> contactList = contactPersonService.search(null, null, eqParam,
										eqValue, 0, totalContact);

								for (Iterator iterator2 = contactList.iterator(); iterator2.hasNext();) {

									ContactPerson contactPerson = (ContactPerson) iterator2.next();

									if (contactPerson != null) {

										String destination = contactPerson.getEmail();
										MessageTemplate template = new MessageTemplate();
										Notification notification = new Notification();

										if (warningType == 1) {
											template.setId(MessageTemplate.POLICY_FUND_WARNING_EMAIL);
											notification
													.setMessageType(Notification.ASO_FUND_WARNING_NOTIFICATION_EMAIL);
										} else if (warningType == 2) {
											template.setId(MessageTemplate.POLICY_FUND_BLOCK_EMAIL);
											notification.setMessageType(Notification.ASO_FUND_BLOCK_NOTIFICATION_EMAIL);
										} else {
											template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
											notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
										}

										notification.setClientId(client);
										notification.setContactPersonId(contactPerson);
										notification.setPolicyId(policy);
										if (warnedFund != null) {
											notification.setPolicyCoverageFund(warnedFund);
										}
										notification.setBatchClaimId(theBatch);
										notification.setTemplateId(template);
										notification.setDestination(destination);
										notification.setStatus(0);

										notification.setSender("system-daemon");

										notificationService.create(notification, actionUser);

									}
								}
							}
						}
					}
				}

				if (client != null && client.getIsUsingFloatingFund() != null) {

					if (client.getIsUsingFloatingFund() == 1) {

						int claimType = theBatch.getBatchClaimType().getClaimTypeId();

						if (client.getFloatingFundUsageType() == FloatingFundUsageType.BOTH_CLAIM_TYPE) {

							/**
							 * Double currentValue =
							 * client.getClientFundValue();
							 * 
							 * Double minValue = client.getMinimumFundValue();
							 * 
							 * if ((currentValue - result.getPaymentValue()) >
							 * 0){
							 * client.setClientFundValue(currentValue-result.
							 * getPaymentValue());
							 * 
							 * clientService.update(client,actionUser); }
							 */
						} else if (client.getFloatingFundUsageType() == FloatingFundUsageType.CASHLESS_ONLY_CLAIM_TYPE
								&& claimType == ClaimType.CASHLESS) {

							/**
							 * Double currentValue =
							 * client.getClientFundValue();
							 * 
							 * Double minValue = client.getMinimumFundValue();
							 * 
							 * if ((currentValue - result.getPaymentValue()) >
							 * 0){
							 * client.setClientFundValue(currentValue-result.
							 * getPaymentValue());
							 * 
							 * clientService.update(client,actionUser); }
							 */

						} else if (client
								.getFloatingFundUsageType() == FloatingFundUsageType.REIMBURSEMENT_ONLY_CLAIM_TYPE
								&& claimType == ClaimType.REIMBURESEMENT) {

							/**
							 * Double currentValue =
							 * client.getClientFundValue();
							 * 
							 * Double minValue = client.getMinimumFundValue();
							 * 
							 * if ((currentValue - result.getPaymentValue()) >
							 * 0){
							 * client.setClientFundValue(currentValue-result.
							 * getPaymentValue());
							 * 
							 * clientService.update(client,actionUser); }
							 */
						}

					}
					//
				}

				CaseStatus status = new CaseStatus();

				status.setCaseStatusId(BatchClaim.PAYMENT_ISSUED);

				System.out.println("AFTER UPDATE BATCH CLAIM SERVICE");
				int claimTotal = claimService.getTotal(null, null, "batchClaimId.batchClaimId",
						theBatch.getBatchClaimId());
				System.out.println("GETTING CLAIM TOTAL");
				Collection<Claim> claimList = claimService.search(null, null, "batchClaimId.batchClaimId",
						theBatch.getBatchClaimId(), 0, claimTotal);
				System.out.println("CLAIM LIST NYA ");
				int claimPending = 0;

				if (claimList != null) {
					Iterator<Claim> claimIterator = claimList.iterator();

					if (claimIterator != null) {

						Claim claimObject = null;

						while (claimIterator.hasNext()) {
							claimObject = claimIterator.next();

							if (claimObject != null) {
								if (claimObject.getClaimStatus().getCaseStatusId() == Claim.CLAIM_CHECKED) {
									claimObject.setPaymentId(object);

									claimObject.setClaimStatus(status);
									claimObject.setPaymentGeneratedDate(result.getPaymentTime());
									claimObject.setPaymentNumber(result.getPaymentNumber());
									claimObject.setBank(result.getBankName());
									claimObject.setBankAccount(result.getAccountNumber());
									
									if (result.getRemarks() != null){
										claimObject.setPaymentRemarks(result.getRemarks());
									}

									claimService.update(claimObject, actionUser);
								}
								if (claimObject.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PENDING) {
									claimPending += 1;
								}
							}
						}
					}
				}
				if (claimPending == 0) {

					theBatch.setBatchClaimStatus(status);
					theBatch.setPaymentDate(new java.sql.Date(result.getPaymentTime().getTime()));

					batchClaimService.update(theBatch, actionUser);
				}

				if (claimList != null) {
					if (configuration != null && configuration.getIsUsingClientNotification() != null
							&& configuration.getIsUsingClientNotification().intValue() == 1) {
						if (theBatch.getPaymentRecipient().getPaymentRecipientId()
								.intValue() == PaymentRecipient.MEMBER) {

							String destination = "";
							MessageTemplate template = new MessageTemplate();

							Notification notification = new Notification();

							if (theBatch.getMemberId() != null && theBatch.getMemberId().getEmail() != null) {
								destination = theBatch.getMemberId().getEmail();
								template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
								notification.setMemberId(theBatch.getMemberId());
							}

							notification.setBatchClaimId(theBatch);
							notification.setTemplateId(template);
							notification.setDestination(destination);
							notification.setStatus(0);
							notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
							notification.setSender("system-daemon");

							if (notification.getDestination() != null) {

								notificationService.createCDV(notification, actionUser, theBatch, claimList);
							}
						}

						if (theBatch.getPaymentRecipient().getPaymentRecipientId()
								.intValue() == PaymentRecipient.MEMBER_GROUP) {

							String destination = "";
							MessageTemplate template = new MessageTemplate();

							Notification notification = new Notification();

							if (theBatch.getMemberGroupId() != null
									&& theBatch.getMemberGroupId().getEmail() != null) {
								destination = theBatch.getMemberGroupId().getEmail();
								template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
								notification.setMemberId(theBatch.getMemberId());
							}

							notification.setBatchClaimId(theBatch);
							notification.setTemplateId(template);
							notification.setDestination(destination);
							notification.setStatus(0);
							notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
							notification.setSender("system-daemon");

							if (notification.getDestination() != null) {

								notificationService.createCDV(notification, actionUser, theBatch, claimList);
							}
						}
//<<<<<<< .working

						if (theBatch.getPaymentRecipient().getPaymentRecipientId()
								.intValue() == PaymentRecipient.PROVIDER) {

							String destination = "";
							MessageTemplate template = new MessageTemplate();

							Notification notification = new Notification();

							if (theBatch.getProviderId() != null && theBatch.getProviderId().getEmail() != null) {
								destination = theBatch.getProviderId().getEmail();
								template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
								notification.setMemberId(theBatch.getMemberId());
								notification.setProviderId(theBatch.getProviderId());
							}

							notification.setBatchClaimId(theBatch);
							notification.setTemplateId(template);
							notification.setDestination(destination);
							notification.setStatus(0);
							notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
							notification.setSender("system-daemon");

							if (notification.getDestination() != null) {

								notificationService.createCDV(notification, actionUser, theBatch, claimList);
							}
				
						}
					}

				}

				configuration.setCdvNumber(configuration.getCdvNumber() + 1);
				configurationService.update(configuration, actionUser);

			}
			// BatchClaim batch = result.get

		}

		return result;
	}

	private String generatePaymentCounter(int counter) throws Exception {

		String number = "";

		if (counter < 10) {
			number = "000" + counter;
		} else if (counter >= 10 && counter < 100) {
			number = "00" + counter;
		} else if (counter >= 100 && counter < 1000) {
			number = "0" + counter;
		} else if (counter >= 1000) {
			number = counter + "";
		}
		return number;
	}

	private String generatePaymentNumber(String number, Client client) throws Exception {

		DateTime dt = new DateTime();

		String month = "";

		if (dt.getMonthOfYear() < 10) {
			month = "0" + dt.getMonthOfYear();
		} else {
			month = dt.getMonthOfYear() + "";
		}

		Configuration configuration = configurationService.getClientConfiguration(client.getClientId());

		if (configuration == null) {
			configuration = configurationService.getSystemConfiguration();
		}
		String paymentNumber = configuration.getPaymentNumberTemplate();
		paymentNumber = paymentNumber.replace("${counter}", number);
		paymentNumber = paymentNumber.replace("${month}", month);
		paymentNumber = paymentNumber.replace("${year}", dt.getYear() + "");

		return paymentNumber;
	}

	/*
	 * Method update (Payment object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin diubah @return object hasil update,
	 * exception jika gagal
	 */
	public Payment update(Payment object, ActionUser actionUser) throws Exception {

		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		/*
		 * Gue tambahin mekanisme NULL value checking just in case user nya null
		 */
		if (actionUser != null) {

			User user = actionUser.getUser();

			if (user != null) {
				object.setModifiedBy(user.getUsername());
			}
		}

		paymentDao.update(object);
		return object;
	}

	/*
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param pkey adalah
	 * sebuah object yang merepresentasikan primary key dari tabel yang
	 * bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
	 * composite ID @return no return value karena objeknya sendiri sudah
	 * dihapus - just for consistency. Again, exception if failure occured
	 * WARNING ! Invalid value for the returned object, better not use it again
	 * in any place
	 */
	public Payment trash(java.io.Serializable pkey) throws Exception {
		Payment object = paymentDao.get(pkey);
		paymentDao.delete(object);
		return object;
	}

	/*
	 * Method delete (Payment object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Payment delete(java.io.Serializable pkey, ActionUser actionUser) throws Exception {
		Payment object = paymentDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		paymentDao.update(object);
		return object;
	}

	/*
	 * Method delete (Payment object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Payment delete(Payment object, ActionUser actionUser) throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		paymentDao.update(object);
		return object;
	}

	// -- get section - carefull !

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @return
	 * Object yang dihasilkan dari proses retrieval, apabila object tidak
	 * ditemukan maka method akan mengembalikan nilai "NULL"
	 */
	public Payment get(java.io.Serializable pkey) throws Exception {
		Payment object = null;
		object = paymentDao.get(pkey);
		return object;
	}

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @param
	 * required adalah array dari field-field yang dibutuhkan dari hibernate
	 * object @return Object yang dihasilkan dari proses retrieval, apabila
	 * object tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */

	public Payment get(java.io.Serializable pkey, String[] required) throws Exception {
		Payment object = null;
		object = paymentDao.get(pkey);
		DaoSupportUtil.lazyInit(required, object);
		return object;
	}

	// -- get section end here

	// SEARCH SECTION - PALING RUMIT !!
	// * -> plain
	// *b -> with columnOrder

	// -- 1
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], String[] required, int index, int offset) throws Exception {
		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, String[] required, int index, int offset) throws Exception {
		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, int index,
			int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String[] required, int index, int offset) throws Exception {
		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, String[] required, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	// -- 2 , between

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, String[] required, int index, int offset)
			throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			String[] required, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- 2b
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, String[] required, int index, int offset)
			throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[], int index,
			int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[],
			String[] required, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder, int index,
			int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams)
			throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams, String eqColumns, Object eqParams) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = paymentDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = paymentDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Payment element = (Payment) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = paymentDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public Payment searchUnique(String eqColumns, Object eqParams, String[] required) throws Exception {
		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Payment obj = (Payment) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public Payment searchUnique(String eqColumns, Object eqParams) throws Exception {
		Criteria c = paymentDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Payment obj = (Payment) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * 
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = paymentDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = paymentDao.getDetachedCriteria();
		return dc;
	}

	public Payment deliverPayment(Payment object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		object.setDispositionDate(new java.sql.Date(System.currentTimeMillis()));

		PaymentStatus status = new PaymentStatus();
		status.setPaymentStatusId(PaymentStatus.PAYMENT_DISPOSITION);
		object.setPaymentStatus(status);
		update(object, actionUser);
		return object;
	}

	public Payment convertPayment(PaymentDto paymentDto) throws Exception {
		// TODO Auto-generated method stub

		Payment result = null;

		if (paymentDto != null) {
			result = new Payment();

			result.setAccountNumber(paymentDto.getAccountNumber());
			result.setBankBranch(paymentDto.getBankBranch());
			result.setBankName(paymentDto.getBankName());

			BatchClaim batchClaim = new BatchClaim();
			batchClaim.setBatchClaimId(paymentDto.getBatchClaimId());

			result.setBatchClaim(batchClaim);
			result.setConfirmedPaymentValue(paymentDto.getPaymentConfirmedValue());
			result.setCreatedBy(paymentDto.getCreatedBy());
			result.setCreatedTime(paymentDto.getCreatedTime());
			result.setDeletedBy(paymentDto.getDeletedBy());
			result.setDeletedStatus(paymentDto.getDeletedStatus());
			result.setDeletedTime(paymentDto.getDeletedTime());

			result.setDispositionDate(paymentDto.getDispositionDate());
			result.setDocumentBin(paymentDto.getDocumentBin());
			result.setGiroNumber(paymentDto.getGiroNumber());

			result.setModifiedBy(paymentDto.getModifiedBy());
			result.setModifiedTime(paymentDto.getModifiedTime());

			result.setPayeeName(paymentDto.getPayeeName());
			result.setPaymentConfirmDate(paymentDto.getPaymentConfirmDate());
			result.setPaymentCounterNumber(paymentDto.getPaymentNumberCounter());
			result.setPaymentId(paymentDto.getPaymentId());
			result.setPaymentNumber(paymentDto.getPaymentNumber());

			PaymentStatus status = new PaymentStatus();
			status.setPaymentStatusId(paymentDto.getPaymentStatus());

			result.setPaymentStatus(status);

			result.setPaymentTime(paymentDto.getPaymentTime());
			result.setPaymentValue(paymentDto.getPaymentValue());
			result.setRemarks(paymentDto.getRemarks());
			result.setValueDescription(paymentDto.getValueDescription());

			if (paymentDto.getProviderId() != null) {
				Provider provider = new Provider();
				provider.setProviderId(paymentDto.getProviderId());

				result.setProviderId(provider);
			}

			if (paymentDto.getMemberGroupId() != null) {
				MemberGroup memberGroup = new MemberGroup();
				memberGroup.setMemberGroupId(paymentDto.getMemberGroupId());

				result.setMemberGroup(memberGroup);
			}

			if (paymentDto.getMemberId() != null) {
				Member member = new Member();
				member.setMemberId(paymentDto.getMemberId());
				result.setMemberId(member);
			}
		}

		return result;
	}

	public Collection<Object[]> generatePaymentDeliveryRecap(Integer clientId, Date checkDate) throws Exception {
		Collection<Object[]> result = null;

		Session session = paymentDao.getPaymentSession();
		if (session != null) {
			Query query = session.createQuery("SELECT bc.providerId.providerId, "
					+ " (SELECT pro.providerName FROM Provider pro WHERE pro.providerId = bc.providerId.providerId) as providerName, "
					+ " bc.invoiceNumber, p.paymentNumber, bc.batchClaimDate, p.paymentTime, "
					+ " (SELECT COUNT(*) FROM ClaimItem ci INNER JOIN ci.claimId c WHERE c.batchClaimId.batchClaimId = bc.batchClaimId AND ci.deletedStatus = 0 AND ci.claimItemStatus.caseStatusId <> -1) AS totalClaim, "
					+ " p.paymentValue, " + " p.modifiedBy, " + " p.modifiedTime, " + " p.createdBy, "
					+ " p.createdTime, " + " bc.batchClaimId, " + " bc.batchClaimNumber, " + " bc.batchClaimType "
					+ " FROM Payment p LEFT JOIN p.batchClaim bc " + " LEFT JOIN p.batchClaim.providerId "
					+ " WHERE bc.deletedStatus = 0 " + " AND p.deletedStatus = 0 "
					+ " AND bc.clientId.clientId = :clientId " + " AND DATE(p.paymentTime) = :paymentTime"
					+ " ORDER BY p.paymentId ");
			query.setInteger("clientId", clientId);
			query.setDate("paymentTime", checkDate);
			// query.setInteger("claimStatus", Claim.CLAIM_PAID);
			// EXCESSPAYMENTTYPE KOSONG

			// Collection res = query.list();

			result = (Collection<Object[]>) query.list();
		}

		return result;
	}

	@Override
	public Payment deliverPayment(Payment object, String[] claimList, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		Payment result = null;

		if (object != null) {

			boolean isFullPaid = false;
			result = object;
			if (result != null) {

				int totalInstallment = paymentInstallmentService.getTotalInstallment(object.getPaymentId());

				PaymentInstallment installment = new PaymentInstallment();
				installment.setPaymentId(object);
				installment.setInstallmentNumber(totalInstallment + 1 + "");
				installment.setTotalValue(0.0);
				installment.setTotalClaim(claimList.length);
				installment.setStatus(PaymentStatus.PAYMENT_DISPOSITION);
				installment.setDispositionDate(new java.sql.Date(System.currentTimeMillis()));

				installment.setDescription(object.getRemarks());
				installment.setPaymentBatchingStatus(0);

				paymentInstallmentService.create(installment, actionUser);

				double paidValue = 0.0;

				if (claimList != null) {

					for (int i = 0; i < claimList.length; i++) {
						if (claimList[i] != null && !claimList[i].equalsIgnoreCase("")) {
							Claim claimObject = claimService.get(Integer.valueOf(claimList[i]));

							if (claimObject != null) {

								claimObject.setPaymentInstallmentId(installment);

								CaseStatus dispoStatus = new CaseStatus();
								dispoStatus.setCaseStatusId(Claim.CLAIM_PAYMENT_DISPOSITIONED);

								if (claimObject.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PAYMENT_ISSUED) {
									if (claimObject.getPaidToProvider() != null) {
										paidValue += claimObject.getPaidToProvider();
									} else {
										if (claimObject.getClaimTypeId().getClaimTypeId()
												.intValue() == ClaimType.CASHLESS) {
											paidValue += claimObject.getClaimChargeValue();
										} else {
											paidValue += claimObject.getClaimApprovedValue();
										}
									}
								}
								claimObject.setClaimStatus(dispoStatus);
								claimService.disposition(claimObject, actionUser);
							}
						}
					}
				}
				installment.setTotalValue(paidValue);

				paymentInstallmentService.update(installment, actionUser);

			}
			String[] eqCDVIssuedParam = { "paymentId.paymentId", "claimStatus.caseStatusId", "deletedStatus" };
			Object[] eqCDVIssuedValue = { object.getPaymentId(), Claim.CLAIM_PAYMENT_ISSUED, 0 };

			int totalUnpaid = claimService.getTotal(null, null, eqCDVIssuedParam, eqCDVIssuedValue);

			if (totalUnpaid == 0) {
				isFullPaid = true;
			} else {
				isFullPaid = false;
			}

			PaymentStatus status = new PaymentStatus();

			if (isFullPaid) {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_DISPOSITION);
			} else {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_PARTIAL_DISPOSITION);
			}
			object.setPaymentStatus(status);

			object.setDispositionDate(new java.sql.Date(System.currentTimeMillis()));

			update(object, actionUser);
		}
		return result;
	}

	public void deliverPaymentUploadGenerator(ActionUser actionUser) throws Exception {
		try {

			Session session = paymentDao.getPaymentSession();

			String q = "SELECT DISTINCT c.paymentId.paymentId FROM Claim c WHERE c.claimStatus.caseStatusId = :status AND "
					+ "c.paymentInstallmentId is NULL AND c.deletedStatus = 0 AND c.clientPaymentVoucherNumber IS NOT NULL";

			Query query = session.createQuery(q);
			query.setInteger("status", Claim.CLAIM_PAYMENT_ISSUED);

			List<Object> paymentList = query.list();

			for (Iterator iterator = paymentList.iterator(); iterator.hasNext();) {

				Integer paymentId = (Integer) iterator.next();

				if (paymentId != null) {
					Payment payment = get(paymentId);

					Collection<Object> claimList = claimService.getPayableUploadedClaimList(paymentId);

					if (claimList != null && claimList.size() > 0) {
						String[] claimIdList = new String[claimList.size()];

						int idx = 0;

						for (Iterator iterator2 = claimList.iterator(); iterator2.hasNext();) {

							Object object = (Object) iterator2.next();

							claimIdList[idx] = object.toString();

							idx++;

						}

						deliverPayment(payment, claimIdList, actionUser);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Payment finalize(Payment object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		Payment result = null;

		if (object != null) {

			boolean isFullPaid = false;
			result = object;
			if (result != null) {
				BatchClaim batchClaim = result.getBatchClaim();

				if (batchClaim != null) {

					String[] eqParam = { "batchClaimId.batchClaimId", "deletedStatus" };
					Object[] eqValue = { batchClaim.getBatchClaimId(), 0 };
					int totalClaim = claimService.getTotal(null, null, eqParam, eqValue);
					Collection<Claim> claimList = claimService.search(null, null, eqParam, eqValue, 0, totalClaim);

					double paidValue = 0.0;
					double totalBatchPaidValue = 0.0;

					if (claimList != null) {
						for (Iterator iterator = claimList.iterator(); iterator.hasNext();) {
							Claim claimObject = (Claim) iterator.next();

							if (claimObject != null) {
								if (claimObject.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PAYMENT_ISSUED) {
									claimObject.setPaymentId(object);

									claimObject.setPaidTime(
											new java.sql.Timestamp(object.getPaymentConfirmDate().getTime()));

									claimService.pay(claimObject, actionUser);

									if (claimObject.getPaidToProvider() != null) {
										paidValue += claimObject.getPaidToProvider();
									} else {
										if (claimObject.getClaimTypeId().getClaimTypeId()
												.intValue() == ClaimType.CASHLESS) {
											paidValue += claimObject.getClaimChargeValue();
										} else {
											paidValue += claimObject.getClaimApprovedValue();
										}
									}
								}
							}
						}
					}

					String[] required = { "BatchClaim.MemberId" };

					BatchClaim theBatch = batchClaimService.get(batchClaim.getBatchClaimId(), required);

					String[] eqParamPaid = { "batchClaimId.batchClaimId", "deletedStatus" };
					Object[] eqValuePaid = { theBatch.getBatchClaimId(), Integer.valueOf(0) };

					int totalPaidClaim = claimService.getTotal(null, null, eqParamPaid, eqValuePaid);
					int totalUnpaidClaim = 0;

					Collection<Claim> claimPaidList = claimService.search(null, null, eqParamPaid, eqValuePaid, 0,
							totalPaidClaim);

					for (Iterator iterator = claimPaidList.iterator(); iterator.hasNext();) {

						Claim claim = (Claim) iterator.next();

						if (claim != null && claim.getPaidToProvider() != null
								&& claim.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAID) {
							totalBatchPaidValue += claim.getPaidToProvider();
						}

						if (claim.getClaimStatus().getCaseStatusId().intValue() != Claim.CLAIM_PAID) {
							totalUnpaidClaim += 1;
						}

					}

					theBatch.setBatchClaimPaidValue(totalBatchPaidValue);

					if (totalUnpaidClaim == 0) {
						CaseStatus claimStatus = new CaseStatus();

						claimStatus.setCaseStatusId(BatchClaim.PAID);
						theBatch.setBatchClaimStatus(claimStatus);
					} else {
						if (totalBatchPaidValue > 0) {
							CaseStatus claimStatus = new CaseStatus();

							claimStatus.setCaseStatusId(BatchClaim.INSTALLMENT_PAYMENT);
							theBatch.setBatchClaimStatus(claimStatus);
						}
					}
					if (result.getPaymentConfirmDate() != null) {
						theBatch.setPaymentDate(result.getPaymentConfirmDate());
					}

					batchClaimService.update(theBatch, actionUser);

					Configuration configuration = configurationService
							.getClientConfiguration(theBatch.getClientId().getClientId());

					if (configuration == null) {
						configuration = configurationService.getSystemConfiguration();
					}
					if (configuration != null && configuration.getIsUsingClientNotification() != null) {
						if (configuration.getIsUsingClientNotification().intValue() == 1) {
							if (theBatch.getPaymentRecipient().getPaymentRecipientId()
									.intValue() == PaymentRecipient.MEMBER) {

								String destination = "";
								MessageTemplate template = new MessageTemplate();

								Notification notification = new Notification();

								if (theBatch.getMemberId() != null && theBatch.getMemberId().getEmail() != null) {
									destination = theBatch.getMemberId().getEmail();
									template.setId(MessageTemplate.MEMBER_CLAIM_PAID_EMAIL);
									notification.setMemberId(theBatch.getMemberId());
								}

								notification.setBatchClaimId(theBatch);
								notification.setTemplateId(template);
								notification.setDestination(destination);
								notification.setStatus(0);
								notification.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
								notification.setSender("system-daemon");

								notificationService.create(notification, actionUser);
							}
						}

					}
				}
				// BatchClaim batch = result.get
			}
			String[] eqCDVIssuedParam = { "paymentId.paymentId", "claimStatus.caseStatusId", "deletedStatus" };
			Object[] eqCDVIssuedValue = { object.getPaymentId(), Claim.CLAIM_PAYMENT_ISSUED, 0 };

			String[] eqPaidParam = { "paymentId.paymentId", "claimStatus.caseStatusId", "deletedStatus" };
			Object[] eqPaidValue = { object.getPaymentId(), Claim.CLAIM_PAID, 0 };

			int totalUnpaid = claimService.getTotal(null, null, eqCDVIssuedParam, eqCDVIssuedValue);
			int totalPaid = claimService.getTotal(null, null, eqPaidParam, eqPaidValue);

			double totalPaidValue = 0.0;

			if (totalPaid > 0) {
				Collection<Claim> claimPaidList = claimService.search(null, null, eqPaidParam, eqPaidValue, 0,
						totalPaid);

				for (Iterator iterator = claimPaidList.iterator(); iterator.hasNext();) {

					Claim claim = (Claim) iterator.next();

					if (claim != null) {
						totalPaidValue += claim.getPaidToProvider();
					}

				}

			}

			if (totalUnpaid == 0) {
				isFullPaid = true;
			} else {
				isFullPaid = false;
			}

			PaymentStatus status = new PaymentStatus();

			if (isFullPaid) {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);
			} else {
				status.setPaymentStatusId(PaymentStatus.PAYMENT_PARTIAL_PAID);
			}
			object.setPaymentStatus(status);
			object.setConfirmedPaymentValue(totalPaidValue);

			update(object, actionUser);
		}
		return result;
	}
}
