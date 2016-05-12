package com.ametis.cms.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

import com.ametis.cms.dao.CaseDao;
import com.ametis.cms.dao.ClaimDao;
import com.ametis.cms.dao.MemberDao;
import com.ametis.cms.dao.RejectedClaimDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseHistory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimHistory;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.ContactPerson;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.datamodel.ExcessChargeItem;
import com.ametis.cms.datamodel.ExchangeRate;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.MemberDiagnosisExclusion;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.MemberLimitLayer;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.MemberSuspendHistory;
import com.ametis.cms.datamodel.MessageTemplate;
import com.ametis.cms.datamodel.Notification;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderTypeDiagnosisTreatment;
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.PaymentReportDetail;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.BillingItemService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseHistoryService;
import com.ametis.cms.service.ClaimHistoryService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientContractService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.ContactPersonService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.DiagnosisSetDetailService;
import com.ametis.cms.service.DiagnosisSetService;
import com.ametis.cms.service.ExcessChargeItemService;
import com.ametis.cms.service.ExcessChargeService;
import com.ametis.cms.service.ExchangeRateService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberDiagnosisExclusionService;
import com.ametis.cms.service.MemberDiagnosisService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberSuspendHistoryService;
import com.ametis.cms.service.NotificationService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.service.PolicyBenefitService;
import com.ametis.cms.service.PolicyBillingRateService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyDiagnosisExclusionService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderTypeDiagnosisTreatmentService;
import com.ametis.cms.service.RejectCategoryService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 
// imports- 
/**
 * Claim is a servlet controller for claim Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class ClaimServiceImpl implements ClaimService // extends+
// extends-
{

	private ClaimDao claimDao;
	private ExcessChargeService excessChargeService;
	private ClaimItemService claimItemService;
	private MemberBenefitService memberBenefitService;
	private MemberClausulService memberClausulService;
	private ConfigurationService configurationService;
	private MemberLimitLayerService memberLimitLayerService;
	private MemberDao memberService;
	private CaseDao caseDao;

	private CaseHistoryService caseHistoryService;
	private ClaimHistoryService claimHistoryService;

	private NotificationService notificationService;
	private BatchClaimService batchClaimService;
	private ExcessChargeItemService excessChargeItemService;
	private NumberCounterService numberCounterService;
	private MemberProductService memberProductService;
	private DiagnosisService diagnosisService;
	private CaseCategoryService caseCategoryService;
	private ProviderService providerService;
	private ActivityLogService activityLogService;
	private ClientService clientService;
	private MemberDiagnosisExclusionService memberDiagnosisExclusionService;
	private MemberDiagnosisService memberDiagnosisService;
	private PolicyClausulService policyClausulService;
	private PolicyBenefitService policyBenefitService;
	private PolicyDiagnosisExclusionService policyDiagnosisExclusionService;
	private ExchangeRateService exchangeRateService;
	private RejectedClaimDao rejectClaimDao;
	private RejectCategoryService rejectCategoryService;

	private PolicyCoverageFundService policyCoverageFundService;
	private PolicyService policyService;
	private MemberGroupService memberGroupService;
	private ContactPersonService contactPersonService;

	private MemberSuspendHistoryService memberSuspendHistoryService;

	private DiagnosisSetService diagnosisSetService;
	private DiagnosisSetDetailService diagnosisSetDetailService;
	private ClientContractService clientContractService;
	private PolicyBillingRateService policyBillingRateService;
	private BillingItemService billingItemService;

	private ProviderTypeDiagnosisTreatmentService providerTypeDiagnosisTreatmentService;

	public BillingItemService getBillingItemService() {
		return billingItemService;
	}

	public void setBillingItemService(BillingItemService billingItemService) {
		this.billingItemService = billingItemService;
	}

	public ClientContractService getClientContractService() {
		return clientContractService;
	}

	public void setClientContractService(ClientContractService clientContractService) {
		this.clientContractService = clientContractService;
	}

	public PolicyBillingRateService getPolicyBillingRateService() {
		return policyBillingRateService;
	}

	public void setPolicyBillingRateService(PolicyBillingRateService policyBillingRateService) {
		this.policyBillingRateService = policyBillingRateService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public ClaimHistoryService getClaimHistoryService() {
		return claimHistoryService;
	}

	public void setClaimHistoryService(ClaimHistoryService claimHistoryService) {
		this.claimHistoryService = claimHistoryService;
	}

	public CaseHistoryService getCaseHistoryService() {
		return caseHistoryService;
	}

	public void setCaseHistoryService(CaseHistoryService caseHistoryService) {
		this.caseHistoryService = caseHistoryService;
	}

	public DiagnosisSetService getDiagnosisSetService() {
		return diagnosisSetService;
	}

	public void setDiagnosisSetService(DiagnosisSetService diagnosisSetService) {
		this.diagnosisSetService = diagnosisSetService;
	}

	public DiagnosisSetDetailService getDiagnosisSetDetailService() {
		return diagnosisSetDetailService;
	}

	public void setDiagnosisSetDetailService(DiagnosisSetDetailService diagnosisSetDetailService) {
		this.diagnosisSetDetailService = diagnosisSetDetailService;
	}

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService(ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}

	public MemberSuspendHistoryService getMemberSuspendHistoryService() {
		return memberSuspendHistoryService;
	}

	public void setMemberSuspendHistoryService(MemberSuspendHistoryService memberSuspendHistoryService) {
		this.memberSuspendHistoryService = memberSuspendHistoryService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}

	public void setMemberLimitLayerService(MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}

	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}

	public void setPolicyCoverageFundService(PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public RejectCategoryService getRejectCategoryService() {
		return rejectCategoryService;
	}

	public void setRejectCategoryService(RejectCategoryService rejectCategoryService) {
		this.rejectCategoryService = rejectCategoryService;
	}

	public RejectedClaimDao getRejectClaimDao() {
		return rejectClaimDao;
	}

	public void setRejectClaimDao(RejectedClaimDao rejectClaimDao) {
		this.rejectClaimDao = rejectClaimDao;
	}

	public PolicyBenefitService getPolicyBenefitService() {
		return policyBenefitService;
	}

	public void setPolicyBenefitService(PolicyBenefitService policyBenefitService) {
		this.policyBenefitService = policyBenefitService;
	}

	public CaseDao getCaseDao() {
		return caseDao;
	}

	public void setCaseDao(CaseDao caseDao) {
		this.caseDao = caseDao;
	}

	public ExchangeRateService getExchangeRateService() {
		return exchangeRateService;
	}

	public void setExchangeRateService(ExchangeRateService exchangeRateService) {
		this.exchangeRateService = exchangeRateService;
	}

	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public PolicyDiagnosisExclusionService getPolicyDiagnosisExclusionService() {
		return policyDiagnosisExclusionService;
	}

	public void setPolicyDiagnosisExclusionService(PolicyDiagnosisExclusionService policyDiagnosisExclusionService) {
		this.policyDiagnosisExclusionService = policyDiagnosisExclusionService;
	}

	public MemberDiagnosisService getMemberDiagnosisService() {
		return memberDiagnosisService;
	}

	public void setMemberDiagnosisService(MemberDiagnosisService memberDiagnosisService) {
		this.memberDiagnosisService = memberDiagnosisService;
	}

	public MemberDiagnosisExclusionService getMemberDiagnosisExclusionService() {
		return memberDiagnosisExclusionService;
	}

	public void setMemberDiagnosisExclusionService(MemberDiagnosisExclusionService memberDiagnosisExclusionService) {
		this.memberDiagnosisExclusionService = memberDiagnosisExclusionService;
	}

	public MemberClausulService getMemberClausulService() {
		return memberClausulService;
	}

	public void setMemberClausulService(MemberClausulService memberClausulService) {
		this.memberClausulService = memberClausulService;
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

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public NumberCounterService getNumberCounterService() {
		return numberCounterService;
	}

	public void setNumberCounterService(NumberCounterService numberCounterService) {
		this.numberCounterService = numberCounterService;
	}

	public ExcessChargeItemService getExcessChargeItemService() {
		return excessChargeItemService;
	}

	public void setExcessChargeItemService(ExcessChargeItemService excessChargeItemService) {
		this.excessChargeItemService = excessChargeItemService;
	}

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}

	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public MemberDao getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberDao memberService) {
		this.memberService = memberService;
	}

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public ExcessChargeService getExcessChargeService() {
		return excessChargeService;
	}

	public void setExcessChargeService(ExcessChargeService excessChargeService) {
		this.excessChargeService = excessChargeService;
	}

	public void setClaimDao(ClaimDao object) {
		this.claimDao = object;
	}

	public ClaimDao getClaimDao() {
		return this.claimDao;
	}

	public ProviderTypeDiagnosisTreatmentService getProviderTypeDiagnosisTreatmentService() {
		return providerTypeDiagnosisTreatmentService;
	}

	public void setProviderTypeDiagnosisTreatmentService(
			ProviderTypeDiagnosisTreatmentService providerTypeDiagnosisTreatmentService) {
		this.providerTypeDiagnosisTreatmentService = providerTypeDiagnosisTreatmentService;
	}

	public Integer addTotalItem(Integer claimId) throws Exception {
		Integer result = 0;

		Claim claim = get(claimId);

		if (claim != null) {
			Integer totalItem = claim.getTotalItem();
			claim.setTotalItem(totalItem + 1);
			claimDao.update(claim);
			result = totalItem + 1;
		}

		return result;
	}

	/*
	 * Method create (Claim object) berfungsi untuk melakukan penambahan sebuah
	 * object kedalam database @param object adalah sebuah object yang ingin
	 * diubah @return object hasil kreasi,lengkap dengan assigned primary key,
	 * exception jika gagal
	 */

	public Claim create(Claim object, ActionUser actionUser) throws Exception {

		if (object.getClaimChargeValue() == null) {
			object.setClaimChargeValue(Double.valueOf(0));
		}
		object.setIsBilled(0);
		object.setBillingRate(0.0);
		object.setClaimPaidValue(Double.valueOf(0));
		object.setClaimExcessValue(Double.valueOf(0));
		object.setClaimApprovedValue(Double.valueOf(0));
		object.setDeletedStatus(Integer.valueOf(0));
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setReconciled(false);

		if (actionUser != null) {

			User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());
				object.setModifiedBy(user.getUsername());
			}
		}

		Member member = null;

		if (object.getMemberId() != null) {
			String[] required = { "ClientId", "MemberGroupId", "BrokerId", "ParentMember","CurrentPolicyId" };
			member = memberService.get(object.getMemberId().getMemberId());
			DaoSupportUtil.lazyInit(required, member);
		}

		if (object.getClientId() == null) {

			if (member != null && member.getClientId() != null) {
				object.setClient(member.getClientId());
				object.setClientId(member.getClientId().getClientId());
			}

		}

		/*
		 * Calculating Claim Number [Tahun]/[bulan]/[R/C]/[IP/OP/M/D/O]/Nomor
		 * Urut
		 */

		DateTime dateTime = new DateTime();
		BatchClaim batchClaim = null;

		if (object.getBatchClaimId() != null) {
			batchClaim = batchClaimService.get(object.getBatchClaimId().getBatchClaimId());

			if (batchClaim != null && batchClaim.getBatchClaimDate() != null) {
				object.setClaimDate(batchClaim.getBatchClaimDate());
			}
		}

		String claimType = "";

		String providerName = "";
		int number = 0;

		Configuration config = configurationService.getClientConfiguration(object.getClientId());

		if (config == null) {
			config = configurationService.getSystemConfiguration();
		}

		Integer isUsingSequence = config.getIsUsingSequenceNumber();

		if (isUsingSequence != null && isUsingSequence.intValue() == 1) {
			String seqSQL = config.getClaimNumberSeqName();

			Session session = claimDao.getClaimSession();
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
								number = num.intValue();
							}
						}
					}
				}
			}
		} else {
			if (config != null) {
				if (config.getCentralizedClaimNumbering() != null
						&& config.getCentralizedClaimNumbering().intValue() == 0) {
					number = config.getClaimNumber();
					int toBeUpdated = number + 1;

					config.setClaimNumber(toBeUpdated);

					configurationService.update(config, actionUser);
				} else if (config.getCentralizedClaimNumbering() != null
						&& config.getCentralizedClaimNumbering().intValue() == 1) {

					config = configurationService.getSystemConfiguration();

					number = config.getClaimNumber();
					int toBeUpdated = number + 1;

					config.setClaimNumber(toBeUpdated);

					configurationService.update(config, actionUser);
				}
			}

		}

		String counter = generateClaimCounterNumber(number);

		if (batchClaim != null) {

			if (batchClaim.getBatchClaimType() != null) {
				if (batchClaim.getBatchClaimType().getClaimTypeId() == ClaimType.CASHLESS) {
					claimType = "C";
				} else if (batchClaim.getBatchClaimType().getClaimTypeId() == ClaimType.REIMBURESEMENT) {
					claimType = "R";

				} else if (batchClaim.getBatchClaimType().getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS) {
					claimType = "RK";

				}
			}
		}

		String method = "SH";

		if (object.getTipe() != null) {
			if (object.getTipe().intValue() == Claim.CLAIM_SWIPE) {
				method = "SW";
				claimType = "C";
				object.setIsEDCBatchAssigned(0);
			}
		}
		String serviceType = "";

		if (object.getCaseCategoryId() != null) {
			System.out.println("Case Category : " + object.getCaseCategoryId() + " case category : "
					+ object.getCaseCategoryId().getCaseCategoryId());

			if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.INPATIENT) {
				serviceType = "IP";
			} else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.OUTPATIENT) {
				serviceType = "OP";
			} else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.DENTAL) {
				serviceType = "D";
			} else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.OPTICAL) {
				serviceType = "O";
			} else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MATERNITY) {
				serviceType = "M";
			} else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.SPECIALIST) {
				serviceType = "S";
			} else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MEDICAL_CHECK_UP) {
				serviceType = "MCU";
			}
		}

		object.setClaimServiceType(serviceType);
		object.setClaimPaymentType(claimType);

		String month = "";

		if (dateTime.getMonthOfYear() < 10) {
			month += "0" + dateTime.getMonthOfYear();
		} else {
			month += dateTime.getMonthOfYear();
		}

		String claimNumber = "";

		RejectCategory rejectCategory = null;

		claimNumber = config.getClaimNumberTemplate();
		claimNumber = claimNumber.replace("${counter}", counter);
		claimNumber = claimNumber.replace("${month}", month);
		claimNumber = claimNumber.replace("${year}", dateTime.getYear() + "");
		claimNumber = claimNumber.replace("${ctype}", claimType);
		claimNumber = claimNumber.replace("${category}", serviceType);
		claimNumber = claimNumber.replace("${method}", method);

		// claimNumber = counter + "/" + month + "/" + dateTime.getYear() + "/"
		// + claimType + "/" + serviceType +"/" + method;

		object.setNumberClaimCounter(counter);

		if (object.getMemberId() != null && object.getMemberId().getMemberGroupId() != null) {
			object.setMemberGroupId(object.getMemberId().getMemberGroupId().getMemberGroupId());
		}

		if (object.getMemberId() != null && object.getMemberId().getBrokerId() != null) {
			object.setBrokerId(object.getMemberId().getBrokerId().getBrokerId());
		}

		object.setClaimNumber(claimNumber);

		if (object.getProviderName().equals("") && !providerName.equalsIgnoreCase("")) {
			object.setProviderName(providerName);
		}

		if (object.getProviderId() != null) {

			Provider provider = providerService.get(object.getProviderId().getProviderId());

			if (provider != null) {
				object.setProviderName(provider.getProviderName());
				object.setProviderArea(provider.getCity());

				if (batchClaim == null) {
					object.setClaimCurrencyId(provider.getProviderCurrencyId());
				} else {
					object.setClaimCurrencyId(batchClaim.getClaimCurrency());
				}
			}
		}
		if (object.getDiagnosis2Id() != null) {

			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis2Id().getDiagnosisId());

			if (diagnosis != null) {
				object.setDiagnosis2Code(diagnosis.getDiagnosisCode());

				// check pre-existing and exclusion

			}
		}

		if (object.getDiagnosisId() != null) {
			if (object.getDiagnosisId().getDiagnosisId() != null) {
				Diagnosis diagnosis = diagnosisService.get(object.getDiagnosisId().getDiagnosisId());

				if (diagnosis != null) {
					object.setDiagnosis1Code(diagnosis.getDiagnosisCode());
					object.setDiagnoseName(diagnosis.getDiagnosisName());

				}
			}
		}

		if (object.getDiagnosis3Id() != null) {
			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis3Id().getDiagnosisId());

			if (diagnosis != null) {
				object.setDiagnosis3Code(diagnosis.getDiagnosisCode());

			}
		}

		// Member member =
		// memberService.get(object.getMemberId().getMemberId());

		object.setBank("");
		object.setBankAccount("");

		object.setOtherNumber("");
		object.setMemberPolicyNumber("");
		object.setMemberOtherNumber("");

		if (object.getMemberId() != null) {

			if (member != null) {
				object.setCardNumber(member.getCurrentCardNumber());
				object.setMemberName(member.getFirstName());
				object.setMemberNumber(member.getCustomerPolicyNumber());
				object.setRelationship(member.getRelationship());
				object.setSex(member.getGender());
				object.setBirthdate(member.getBirthday());
				object.setEffectiveDate(member.getEffectiveDate());
				object.setExpireDate(member.getExpireDate());
				object.setGroupName(member.getMemberGroupId().getGroupName());
				if (member.getCurrentPolicyId() != null) {
					object.setPolicyId(member.getCurrentPolicyId().getPolicyId());
					object.setPolicy(member.getCurrentPolicyId());
				}
				if (member.getParentMember() != null) {
					object.setBank(member.getParentMember().getBank());
					object.setBankAccount(member.getParentMember().getBankAccount());
					object.setDepartment(member.getParentMember().getDepartment());
				}
				object.setMemberPolicyNumber(member.getCustomerPolicyNumber());
			}
		}

		CaseCategory caseCategory = caseCategoryService.get(object.getCaseCategoryId().getCaseCategoryId());

		if (object.getCaseCategoryId() != null) {
			if (caseCategory != null) {
				object.setPlan(caseCategory.getCaseCategoryCode());
			}
		}

		if (member != null && caseCategory != null) {
			MemberProduct memberProduct = memberProductService.getMemberProductByDate(member.getMemberId(),
					caseCategory.getCaseCategoryId(), object.getAdmissionDate(), object.getDischargeDate());
			// MemberProduct memberProduct =
			// memberProductService.getMemberActiveProduct(member.getMemberId(),
			// caseCategory.getCaseCategoryId());

			if (memberProduct == null) {
				// suspect divert claim
			}

			if (memberProduct != null) {
				object.setProductId(memberProduct);
				object.setProductClass(memberProduct.getProductId().getProductName());
				object.setPlan(caseCategory.getCaseCategoryCode());
				object.setProductCurrencyId(memberProduct.getProductCurrencyId());

				Currency claimCurrency = object.getClaimCurrencyId();
				Currency productCurrency = memberProduct.getProductCurrencyId();

				if (claimCurrency != null && productCurrency != null) {
					// ternyata currency nya berbeda .. mari kita lakukan
					// currency rate conversion

					if (claimCurrency.getCurrencyId().intValue() != productCurrency.getCurrencyId().intValue()) {

						Integer claimCurrencyId = claimCurrency.getCurrencyId();
						Integer productCurrencyId = memberProduct.getProductCurrencyId().getCurrencyId();

						DateTime discharge = new DateTime(object.getDischargeDate().getTime());
						DateTime previousRateDate = discharge.minusDays(1);
						Date prevRateDate = new Date(previousRateDate.getMillis());

						String[] eqParamRate = { "deletedStatus", "exchangeRateDate", "firstCurrencyId.currencyId",
								"secondCurrencyId.currencyId" };
						Object[] eqValueRate = { 0, prevRateDate, claimCurrencyId, productCurrencyId };

						Collection<ExchangeRate> rateColl = exchangeRateService.search(null, null, eqParamRate,
								eqValueRate, 0, 1);

						if (rateColl != null) {
							Iterator<ExchangeRate> iteratorRate = rateColl.iterator();

							if (iteratorRate.hasNext()) {
								ExchangeRate rate = iteratorRate.next();

								if (rate != null) {
									double conversionRate = rate.getRateFirstToSecond().doubleValue();
									object.setExchangeRate(conversionRate);
									object.setExchangeRateId(rate);

									object.setExchangeRateDate(rate.getExchangeRateDate());
									object.setProductCurrencyChargeValue(conversionRate * object.getClaimChargeValue());

								}
							}
						}
					} else {
						object.setExchangeRate(1.0);
						object.setProductCurrencyChargeValue(object.getClaimChargeValue());
					}
				} else {
					object.setExchangeRate(1.0);
					object.setProductCurrencyChargeValue(object.getClaimChargeValue());
				}
			}
		}

		// add by adq untuk claim type reimburesement maka setPrePaidExcess =
		// null
		String claimNumber2 = null;
		if (object.getClaimNumber() != null) {
			claimNumber2 = object.getClaimNumber();
		}
		if ((object.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURESEMENT)
				&& (claimNumber2 == object.getClaimNumber())) {
			object.setPrePaidExcess(null);
		} // end add adq

		if (member.getStatus() != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE){
			updateSMMClaim(object, member);
		}
		else if (member.getStatus() != null && (member.getStatus().intValue() == SubscriptionStatus.BLOCKED || member.getStatus().intValue() == SubscriptionStatus.GRACE) ){
			/**
			 * BUG FIX 0000021: PROSES KLAIM SETELAH SUSPEND/GRACE
			 * 
			 */
			
			CaseStatus rejectedStatus = new CaseStatus();
			rejectedStatus.setCaseStatusId(Claim.CLAIM_REJECT);
			object.setClaimStatus(rejectedStatus);
		}

		Claim result = claimDao.create(object);

		ActivityLog claimLog = new ActivityLog();
		claimLog.setAction("CREATE CLAIM");
		claimLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
		claimLog.setUsername(actionUser.getUser().getUsername());
		claimLog.setClaimId(result);

		claimLog.setDescription("MEMBER = " + object.getMemberName() + " CASE CAT = " + object.getClaimServiceType()
				+ " CLAIM NO = " + object.getClaimNumber() + " CHARGE = "
				+ BigDecimal.valueOf(object.getClaimChargeValue()).toPlainString());

		activityLogService.create(claimLog);

		if (result != null) {
			if (batchClaim != null) {

				int incompleteClaim = batchClaim.getIncompleteClaim() == null ? 0 : batchClaim.getIncompleteClaim();
				batchClaim.setIncompleteClaim(Integer.valueOf(incompleteClaim - 1));

				if ((incompleteClaim - 1) == 0) {
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(Integer.valueOf(BatchClaim.COMPLETE));
					batchClaim.setBatchClaimStatus(status);
				}
				batchClaimService.update(batchClaim, actionUser);
			}

			if (result.getEdcTraceNumber() != null) {
				User user = new User();
				user.setUsername("system");
				if (actionUser != null) {
					actionUser.setUser(user);
				}
			}

		}

		return result;
	}

	private void updateClaimCounter(int currentCounter) {
	}

	private String generateClaimCounterNumber(int number) {

		String result = "";

		try {

			int res = number;
			if (res > 0 && res < 10) {
				result = "0000" + res;
			} else if (res >= 10 && res < 100) {
				result = "000" + res;
			} else if (res >= 100 && res < 1000) {
				result = "00" + res;
			} else if (res >= 1000 && res < 10000) {
				result = "0" + res;
			} else if (res >= 10000) {
				result = "" + res;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/*
	 * Method update (Claim object) berfungsi untuk melakukan perubahan terhadap
	 * sebuah object yang terdapat didalam database @param object adalah sebuah
	 * object yang ingin diubah @return object hasil update, exception jika
	 * gagal
	 */

	public Claim update(Claim object, ActionUser actionUser) throws Exception {

		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setModifiedBy(user.getUsername());
			}
		}

		if (object.getProviderId() != null) {
			Provider provider = providerService.get(object.getProviderId().getProviderId());
			if (provider != null) {
				object.setProviderName(provider.getProviderName());
				object.setProviderArea(provider.getCity());
			}
		}
		if (object.getDiagnosis2Id() != null) {
			object.setDiagnosis2Code(object.getDiagnosis2Id().getDiagnosisCode());
		}

		if (object.getDiagnosisId() != null) {
			object.setDiagnosis1Code(object.getDiagnosisId().getDiagnosisCode());
			object.setDiagnoseName(object.getDiagnosisId().getDiagnosisName());
		}

		if (object.getDiagnosis3Id() != null) {
			object.setDiagnosis3Code(object.getDiagnosis3Id().getDiagnosisCode());

		}

		if (object.getPaymentId() != null) {
			object.setPaymentNumber(object.getPaymentId().getPaymentNumber());
			object.setPaymentConfirmDate(object.getPaymentId().getPaymentConfirmDate());
			object.setPaymentGeneratedDate(object.getPaymentId().getPaymentTime());
		}

		if (object.getBatchClaimId() != null) {
			BatchClaim batchClaim = batchClaimService.get(object.getBatchClaimId().getBatchClaimId());
			if (batchClaim != null) {
				object.setClaimDate(batchClaim.getBatchClaimDate());
			}
		}
		Member member = null;

		if (object.getMemberId() != null) {
			String[] required = { "ClientId", "MemberGroupId", "BrokerId", "ParentMember","CurrentPolicyId" };
			member = memberService.get(object.getMemberId().getMemberId());
			DaoSupportUtil.lazyInit(required, member);
			
			object.setMemberName(member.getFirstName());
			object.setMemberNumber(member.getCustomerPolicyNumber());
			object.setRelationship(member.getRelationship());
			object.setSex(member.getGender());
			object.setEffectiveDate(member.getEffectiveDate());
			object.setExpireDate(member.getExpireDate());
			object.setBirthdate(member.getBirthday());
			object.setCardNumber(member.getCurrentCardNumber());
			
			if (member.getParentMember() != null) {
				object.setBank(member.getParentMember().getBank());

				object.setBankAccount(member.getParentMember().getBankAccount());
			}
			if (member.getMemberGroupId() != null) {
				object.setGroupName(member.getMemberGroupId().getGroupName());
				object.setMemberGroupId(member.getMemberGroupId().getMemberGroupId());
			}
			if (member.getClientId() != null) {
				object.setClient(member.getClientId());
				object.setClientId(member.getClientId().getClientId());

			}
			if (member.getCurrentPolicyId() != null) {
				object.setPolicyId(member.getCurrentPolicyId().getPolicyId());
				object.setPolicy(member.getCurrentPolicyId());
			}
			object.setMemberPolicyNumber(member.getCustomerPolicyNumber());

			if (member.getParentMember() != null) {
				object.setBank(member.getParentMember().getBank());
				object.setBankAccount(member.getParentMember().getBankAccount());
				object.setDepartment(member.getParentMember().getDepartment());
			}
		}
		

		CaseCategory cc = object.getCaseCategoryId();

		if (object.getCaseCategoryId() != null) {
			cc = caseCategoryService.get(cc.getCaseCategoryId());

			object.setClaimServiceType(cc.getCaseCategoryCode());
		}
		if (object.getClaimTypeId() != null) {
			object.setClaimPaymentType(object.getClaimTypeId().getClaimTypeCode());
		}

		object.setPlan(cc.getCaseCategoryCode());

		CaseCategory caseCategory = caseCategoryService.get(object.getCaseCategoryId().getCaseCategoryId());

		if (member != null && caseCategory != null) {
			MemberProduct memberProduct = memberProductService.getMemberProductByDate(member.getMemberId(),
					caseCategory.getCaseCategoryId(), object.getAdmissionDate(), object.getDischargeDate());
			// MemberProduct memberProduct =
			// memberProductService.getMemberActiveProduct(member.getMemberId(),
			// caseCategory.getCaseCategoryId());

			if (memberProduct != null) {
				object.setProductId(memberProduct);
				object.setProductClass(memberProduct.getProductId().getProductName());
				object.setPlan(caseCategory.getCaseCategoryCode());
				object.setProductCurrencyId(memberProduct.getProductCurrencyId());

				Currency claimCurrency = object.getClaimCurrencyId();
				Currency productCurrency = memberProduct.getProductCurrencyId();

				if (claimCurrency != null && productCurrency != null) {
					// ternyata currency nya berbeda .. mari kita lakukan
					// currency rate conversion

					if (claimCurrency.getCurrencyId().intValue() != productCurrency.getCurrencyId().intValue()) {

						Integer claimCurrencyId = claimCurrency.getCurrencyId();
						Integer productCurrencyId = memberProduct.getProductCurrencyId().getCurrencyId();

						DateTime discharge = new DateTime(object.getDischargeDate().getTime());
						DateTime previousRateDate = discharge.minusDays(1);
						Date prevRateDate = new Date(previousRateDate.getMillis());

						String[] eqParamRate = { "deletedStatus", "exchangeRateDate", "firstCurrencyId.currencyId",
								"secondCurrencyId.currencyId" };
						Object[] eqValueRate = { 0, prevRateDate, claimCurrencyId, productCurrencyId };

						Collection<ExchangeRate> rateColl = exchangeRateService.search(null, null, eqParamRate,
								eqValueRate, 0, 1);

						if (rateColl != null) {
							Iterator<ExchangeRate> iteratorRate = rateColl.iterator();

							if (iteratorRate.hasNext()) {
								ExchangeRate rate = iteratorRate.next();

								if (rate != null) {
									double conversionRate = rate.getRateFirstToSecond().doubleValue();
									object.setExchangeRate(conversionRate);
									object.setExchangeRateId(rate);

									object.setExchangeRateDate(rate.getExchangeRateDate());
									object.setProductCurrencyChargeValue(conversionRate * object.getClaimChargeValue());

								}
							}
						}
					} else {
						object.setExchangeRate(1.0);
						object.setProductCurrencyChargeValue(object.getClaimChargeValue());
					}
				} else {
					object.setExchangeRate(1.0);
					object.setProductCurrencyChargeValue(object.getClaimChargeValue());
				}
			}
		}

		// setting claim number IF the case category changed
		String claimNumber = object.getClaimNumber();
		String newClaimNumber = "";
		StringTokenizer tokenizer = new StringTokenizer(claimNumber, "/");

		String curToken = "";

		int totalToken = tokenizer.countTokens();

		while (tokenizer.hasMoreTokens()) {
			curToken = tokenizer.nextToken();

			if (curToken.equals("IP") || curToken.equals("OP") || curToken.equals("M") || curToken.equals("D")
					|| curToken.equals("OPT") || curToken.equals("MCU") || curToken.equalsIgnoreCase("null")) {

				newClaimNumber += cc.getCaseCategoryCode();

				if (totalToken > 1) {
					newClaimNumber += "/";
				}
			} else {
				newClaimNumber += curToken;

				if (totalToken > 1) {
					newClaimNumber += "/";
				}

			}
			totalToken -= 1;
		}
		object.setClaimNumber(newClaimNumber);

		if (object.getClaimCurrencyId() != null && object.getProductCurrencyId() != null) {
			// ternyata currency nya berbeda .. mari kita lakukan currency rate
			// conversion

			if (object.getClaimCurrencyId().getCurrencyId().intValue() != object.getProductCurrencyId().getCurrencyId()
					.intValue()) {

				if (object.getExchangeRate() != null) {
					object.setProductCurrencyChargeValue(object.getExchangeRate() * object.getClaimChargeValue());

				}

			} else {
				object.setExchangeRate(1.0);
				object.setProductCurrencyChargeValue(object.getClaimChargeValue());
			}
		} else {
			object.setExchangeRate(1.0);
			object.setProductCurrencyChargeValue(object.getClaimChargeValue());
		}

		// add by adq untuk claim type reimburesement maka setPrePaidExcess =
		// null
		String claimNumber2 = null;
		if (object.getClaimNumber() != null) {
			claimNumber2 = object.getClaimNumber();
		}
		if ((object.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURESEMENT)
				&& (claimNumber2 == object.getClaimNumber())) {
			object.setPrePaidExcess(null);
		} // end add adq

		updateSMMClaim(object, member);
		claimDao.update(object);
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
	public Claim trash(java.io.Serializable pkey) throws Exception {
		Claim object = claimDao.get(pkey);
		claimDao.delete(object);
		return object;
	}

	/*
	 * Method delete (Claim object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */
	public Claim delete(java.io.Serializable pkey, ActionUser actionUser) throws Exception {

		Claim object = claimDao.get(pkey);

		if (object != null) {

			if (object.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_CHECKED
					|| object.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED
					|| object.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAID) {
				// object.setDeletedDate (new java.sql.Date
				// (System.currentTimeMillis()));
				openClaim(pkey, actionUser);

				object = claimDao.get(pkey);

				object.setDeletedStatus(new Integer(1));

				if (actionUser != null) {

					User user = actionUser.getUser();

					if (user != null) {
						object.setDeletedBy(user.getUsername());
					}
				}

				claimDao.update(object);
			} else {
				object.setDeletedStatus(new Integer(1));

				if (actionUser != null) {

					User user = actionUser.getUser();

					if (user != null) {
						object.setDeletedBy(user.getUsername());
					}
				}

				claimDao.update(object);
			}
		}
		return object;
	}

	private boolean decreaseCompleteClaim(Integer claimId, ActionUser actionUser) throws Exception {
		boolean result = false;

		return result;
	}

	public boolean deleteClaimItem(java.io.Serializable claimItemId, ActionUser actionUser) throws Exception {

		boolean result = false;

		ClaimItem claimItem = claimItemService.get(claimItemId);

		if (claimItem != null
				&& claimItem.getClaimItemStatus().getCaseStatusId().intValue() == ClaimItem.CLAIM_ITEM_OPEN) {
			Claim claim = claimItem.getClaimId();

			if (claim != null) {
				Integer claimStatus = claim.getClaimStatus().getCaseStatusId();

				/*
				 * Logic ini berfungsi untuk melakukan pemeriksaan apakah Claim
				 * masih dalam status open for entry atau belum diverifikasi.
				 * 
				 * Jika Claim telah masuk proses verifikasi maka claim tidak
				 * boleh diubah - ubah
				 */
				if (claimStatus.intValue() == Claim.CLAIM_OPEN || claimStatus.intValue() == Claim.CLAIM_PROCESS_OK) {
					ClaimItem deletedItem = claimItemService.delete(claimItem, actionUser);

					if (deletedItem != null) {
					}
				}
			}
		}
		return result;
	}

	/*
	 * Method delete (Claim object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Claim delete(Claim object, ActionUser actionUser) throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {

			User user = actionUser.getUser();

			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		claimDao.update(object);
		return object;
	}

	// Mekanismenya Salah Nih :(
	public Claim approveBenefitCheck(Claim object, ActionUser actionUser) throws Exception {

		if (object != null && object.getClaimStatus().getCaseStatusId() == Claim.CLAIM_VERIFIED) {

			if (actionUser != null) {

				User user = actionUser.getUser();

				if (user != null) {
					object.setModifiedBy(user.getUsername());
				}
			}

			ClaimType tipe = object.getClaimTypeId();
			CaseCategory caseCategory = object.getCaseCategoryId();

			Collection claimItems = object.getClaimItems();

			double claimApprovedValue = 0;
			double claimExcessValue = 0;

			if (claimItems != null) {

				Iterator iterator = claimItems.iterator();
				ClaimItem cItem = null;

				ItemCategory itemCategory = null;
				Item item = null;
				double claimItemValue = 0;
				double claimItemApprovedValue = 0;
				double excessValue = 0;

				MemberBenefit memberBenefit = null;
				ExcessCharge excessCharge = null;
				ExcessChargeItem excessChargeItem = null;
				Collection excessCollection = new Vector();
				boolean isExcess = false;

				while (iterator.hasNext()) {
					cItem = (ClaimItem) iterator.next();

					if (cItem != null) {
						// memberBenefitService
						item = cItem.getItemId();

						if (item != null) {

							itemCategory = item.getItemCategoryId();

							claimItemValue = cItem.getClaimItemValue().doubleValue();
							claimItemApprovedValue = cItem.getClaimItemApprovedValue().doubleValue();

							if (tipe.getClaimTypeId().intValue() == ClaimType.CASHLESS
									|| tipe.getClaimTypeId().intValue() == ClaimType.REIMBURSEMENT_KHUSUS) {

								// excess charge if there are some excess

								excessValue = claimItemValue - claimItemApprovedValue;

								if (excessValue > 0) {

									excessChargeItem = new ExcessChargeItem();
									excessChargeItem.setItemId(item);
									excessChargeItem.setValue(Double.valueOf(excessValue));

									// kita update nih item yang punya excess
									cItem.setExcessValue(Double.valueOf(excessValue));

									// kita masukkan item yang kena excess ke
									// dalam vector
									// untuk di update ke colum excess
									excessCollection.add(excessChargeItem);

									claimExcessValue += excessValue;

									isExcess = true;
								}
							}

							claimApprovedValue += claimItemApprovedValue;

						}

						// UPDATE claim ITEM nya dengan data approved value,
						// excess dan lainnya
						claimItemService.update(cItem, actionUser);

						// pengurangan jumlah benefit dikurangi di member
						// benefit.

					}

				}

				if (isExcess) {

					excessCharge = new ExcessCharge();

					HashSet set = new HashSet();
					set.addAll(excessCollection);

					excessCharge.setExcessChargeItems(set);

					excessChargeService.create(excessCharge, actionUser);
				}
			}

			// object.set

			object.setClaimApprovedValue(Double.valueOf(claimApprovedValue));
			object.setClaimExcessValue(Double.valueOf(claimExcessValue));

			claimDao.update(object);

		}

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
	public Claim get(java.io.Serializable pkey) throws Exception {
		Claim object = null;
		object = claimDao.get(pkey);
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
	public Claim get(java.io.Serializable pkey, String[] required) throws Exception {
		Claim object = null;
		object = claimDao.get(pkey);
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);

		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);

		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end
	// -- 1b
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, int index,
			int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String[] required, int index, int offset) throws Exception {
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end
	// -- 2 , between
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// [Start] Add by aju on 20150819, add the Not Equal param fufufu :D
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] neqColumns, Object[] neqParams, String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2,
			boolean asc, String columnOrder, String[] required, int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

		DaoSupportUtil.setNeqParam(neqColumns, neqParams, c);

		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end
	// -- 2b
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[], int index,
			int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder, int index,
			int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
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

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end
	// -- search end
	// -- get total
	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams)
			throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// Add by aju on 20150819, add Not Equal Filter fufufu :D
	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] neqColumns, Object[] neqParams, String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2)
					throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setNeqParam(neqColumns, neqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams, String eqColumns, Object eqParams) throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);

		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = claimDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// Add by aju on 20150805, for COB :D
	public int getTotalCOBOpenClaim() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			Session session = claimDao.getClaimSession();
			// System.out.println("ClaimServiceImpl::getTotalCOBOpenClaim()
			// After get claim session");

			if (session != null) {
				// System.out.println("BatchClaimServiceImpl::getTotalCOBOpenClaim()");

				// modified by aju on 20150819, remove the batch_claim_id check
				// for a while...
				// String sqlQ = "select count(c.claim_id) " +
				// "from claim c " +
				// "where c.batch_claim_id is not null " +
				// "and c.deleted_status = 0 " +
				// "and c.claim_status = :status " +
				// "and (SELECT TRIM(linked_card_number) from member where
				// member_id = c.member_id) <> '' ";

				String sqlQ = "select count(c.claim_id) " + "from claim c " + "where c.deleted_status = 0 "
						+ "and c.claim_status = :status "
						+ "and (SELECT TRIM(linked_card_number) from member where member_id = c.member_id) <> '' ";

				SQLQuery q = session.createSQLQuery(sqlQ);

				// org.hibernate.Query q = session.createQuery(query);
				q.setInteger("status", Claim.CLAIM_OPEN);
				// System.out.println("query : " + q.getQueryString());

				Number num = (Number) q.uniqueResult();
				result = num.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// -- get total end
	// ---------------------------------------------------

	// Add by adq on 15092015
	public int getTotalClaimDaily(int status) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;

		Date due = new java.sql.Date(System.currentTimeMillis());

		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				// String sqlQ =
				// "select count(c.claim_id) FROM claim c where c.claim_status =
				// "+status+" and c.claim_date = now() ";

				String sqlQ = " select count(c.claim_id) FROM claim c where c.claim_status = " + status
						+ " AND to_char(c.created_time, 'yyyy-mm-dd') = '" + due + "' AND c.deleted_status = 0 ";

				SQLQuery q = session.createSQLQuery(sqlQ);

				// q.setInteger("status",Claim.CLAIM_OPEN);

				Number num = (Number) q.uniqueResult();
				result = num.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// -- get totalClaimDaily end

	public Collection getAll(String[] required) throws Exception {

		Criteria c = claimDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = claimDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------
	// unique Result
	public Claim searchUnique(String eqColumns, Object eqParams, String[] required) throws Exception {
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Claim obj = (Claim) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public Claim searchUnique(String eqColumns, Object eqParams) throws Exception {
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Claim obj = (Claim) c.uniqueResult();
		return obj;
	}

	// Add 07042015, for search unique result with more than 1 column and
	// parameter
	public Claim searchUnique(String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception {
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		Claim obj = (Claim) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * 
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = claimDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = claimDao.getDetachedCriteria();
		return dc;
	}

	public Claim createClaimItem(Claim object, Collection claimItem, Double pembulatan, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		Session s = claimDao.getClaimSession();
		s.evict(object);

		if (object != null) {
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(Claim.CLAIM_PROCESS_OK));
			object.setClaimStatus(status);

			double claimCharge = 0;

			if (claimItem != null) {
				Iterator iterator = claimItem.iterator();

				while (iterator.hasNext()) {

					ClaimItem ci = (ClaimItem) iterator.next();

					ci.setClaimItemStatus(status);
					ci.setClaimId(object);

					claimItemService.create(ci, actionUser);
				}
			}

			int totalClaimItem = 0;
			Collection<ClaimItem> claimItems = claimItemService.getClaimItem(object.getClaimId());

			MemberProduct memberProduct = null;

			if (claimItems != null) {
				Iterator<ClaimItem> ciIterator = claimItems.iterator();

				while (ciIterator.hasNext()) {

					ClaimItem ci = ciIterator.next();
					claimCharge += ci.getClaimItemValue();
					totalClaimItem += 1;

					if (object.getProductId() == null) {

						Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitByDate(
								object.getMemberId().getMemberId(), object.getCaseCategoryId().getCaseCategoryId(),
								ci.getItemId().getItemCategoryId().getItemCategoryId(), object.getAdmissionDate(),
								object.getDischargeDate(),0);
						
						// TODO: Statement di Atas Tidak Compatible dengan Multi Layer, Mohon di Review

						if (benefitList != null && benefitList.size() > 0) {

							Iterator<MemberBenefit> iteratorMB = benefitList.iterator();

							if (iteratorMB.hasNext()) {
								MemberBenefit mb = iteratorMB.next();

								if (mb.getMemberProductId() != null) {
									memberProduct = mb.getMemberProductId();
								}
							}
						}
					}
				}
			}

			if (object.getProductId() == null && memberProduct != null) {
				object.setProductId(memberProduct);
			}

			object.setTotalItem(totalClaimItem);
			object.setPembulatan(pembulatan);
			object.setClaimChargeValue(claimCharge);

			update(object, actionUser);

		}
		return object;
	}

	public Claim createClaimItemEDC(Claim object, Collection claimItem, Double pembulatan, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		CaseCategory cc = object.getCaseCategoryId();

		if (cc != null) {
			cc = caseCategoryService.get(cc.getCaseCategoryId());
			object.setCaseCategoryId(cc);
		}
		object.setTipe(Claim.CLAIM_SWIPE);
		create(object, actionUser);

		if (object != null) {

			DateTime birthDate = new DateTime(object.getMemberId().getBirthday());
			DateTime currentDate = new DateTime(object.getAdmissionDate());

			int memberAge = Days.daysBetween(birthDate, currentDate).getDays();

			double claimCharge = 0;

			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(Claim.CLAIM_VERIFIED));

			String remarks = "";
			Integer rejectCategory = null;

			Integer policyId = null;

			if (object.getPolicyId() != null) {
				policyId = object.getPolicyId();
			} else {
				if (object.getMemberId().getCurrentPolicyId() != null) {
					policyId = object.getMemberId().getCurrentPolicyId().getPolicyId();
				}
			}

			if (object.getDiagnosisId() != null) {

				String[] eqPolicyDiagParam = { "policyId", "deletedStatus", "diagnosisId.diagnosisId" };
				Object[] eqPolicyDiagValue = { policyId, 0, object.getDiagnosisId().getDiagnosisId() };

				int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null, null, eqPolicyDiagParam,
						eqPolicyDiagValue);

				if (totalPolicyExclusion > 0) {
					Collection<PolicyDiagnosisExclusion> diagExclusion = policyDiagnosisExclusionService.search(null,
							null, eqPolicyDiagParam, eqPolicyDiagValue, 0, totalPolicyExclusion);

					if (diagExclusion != null) {
						Iterator<PolicyDiagnosisExclusion> iteratorEx = diagExclusion.iterator();
						PolicyDiagnosisExclusion exclusion = null;

						if (iteratorEx.hasNext()) {
							exclusion = iteratorEx.next();
						}
						if (exclusion != null) {

							int ageConstraint = -1;
							String param = "";
							int duration = -1;
							
							if (exclusion.getPreExistingDays() != null){
								duration = exclusion.getPreExistingDays();
							}

							if (exclusion.getAgeConstraint() != null) {
								ageConstraint = exclusion.getAgeConstraint();
							}
							if (exclusion.getAgeParameter() != null) {
								param = exclusion.getAgeParameter();
							}

							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
											
										}
									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}
									}
								}

							} else {
								
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "DIAGNOSIS EXCLUSION";
	
									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									/**
									 * 0000230: [CAR] [PRE EXISTING] Setelah lewat masa Pre Existing harusnya Claim status OPEN
									 */
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}
							}
						}
					}
				} else {
					
					Diagnosis diagnosis =  object.getDiagnosisId();
					MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(
							diagnosis.getDiagnosisId(), object.getMemberId().getMemberId());

					int ageConstraint = -1;
					String param = "";
					int duration = -1;

					if (exclusion != null) {
						if (exclusion.getAgeConstraint() != null) {
							ageConstraint = exclusion.getAgeConstraint();
						}
						if (exclusion.getAgeParameter() != null) {
							param = exclusion.getAgeParameter();
						}
						duration =  exclusion.getPreExistingDays() == null ? -1 : exclusion.getPreExistingDays();
					}

					if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint == -1) {

						
						
						if (duration > 0){
							Member member = memberService.get(object.getMemberId().getMemberId());
							
							if (member != null && member.getJoinDate() != null){
								DateTime join = new DateTime(member.getJoinDate().getTime());
								DateTime admission = new DateTime(object.getAdmissionDate().getTime());
								
								int daysDuration = Days.daysBetween(join, admission).getDays();
								
								if (daysDuration <  duration){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "PRE-EXISTING DIAGNOSIS";
	
									rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
								}
							}
						}
						else {
						
							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);
	
							remarks = "DIAGNOSIS EXCLUSION";
	
							rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
						}
						
					} else if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint > 0) {

						if (param.equalsIgnoreCase("LT")) {
							if (ageConstraint > memberAge) {
								
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}
						else if (param.equalsIgnoreCase("GT")) {
							if (ageConstraint < memberAge) {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}

					}
					else if (exclusion != null && exclusion.getPreExistingDays() != null){

						Member member = memberService.get(object.getMemberId().getMemberId());
						
						if (member != null && member.getJoinDate() != null){
							DateTime join = new DateTime(member.getJoinDate().getTime());
							DateTime admission = new DateTime(object.getAdmissionDate().getTime());
							
							int daysDuration = Days.daysBetween(join, admission).getDays();
							
							if (daysDuration <  duration){
								status.setCaseStatusId(Claim.CLAIM_REJECT);
								object.setClaimStatus(status);

								remarks = "PRE-EXISTING DIAGNOSIS";

								rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
							}
						}
					}
					
					else if (exclusion != null && exclusion.getExpireDate() != null) {
						Date expireDate = exclusion.getExpireDate();

						DateTime exDt = new DateTime(expireDate);
						DateTime ex = exDt.plusDays(1);

						if (object.getAdmissionDate().before(new Date(ex.getMillis()))) {

							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);

							remarks = "PRE-EXISTING DIAGNOSIS";

							rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
						} else {
							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {

										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
							}
						}
					}
				}
			}
			if (object.getDiagnosis2Id() != null) {
				String[] eqPolicyDiagParam = { "policyId", "deletedStatus", "diagnosisId.diagnosisId" };
				Object[] eqPolicyDiagValue = { policyId, 0, object.getDiagnosis2Id().getDiagnosisId() };
				int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null, null, eqPolicyDiagParam,
						eqPolicyDiagValue);

				if (totalPolicyExclusion > 0) {
					Collection<PolicyDiagnosisExclusion> diagExclusion = policyDiagnosisExclusionService.search(null,
							null, eqPolicyDiagParam, eqPolicyDiagValue, 0, totalPolicyExclusion);

					if (diagExclusion != null) {

						Iterator<PolicyDiagnosisExclusion> iteratorEx = diagExclusion.iterator();

						PolicyDiagnosisExclusion exclusion = null;

						if (iteratorEx.hasNext()) {
							exclusion = iteratorEx.next();
						}
						if (exclusion != null) {
							int ageConstraint = -1;
							String param = "";
							
							int duration = -1;
							
							if (exclusion.getPreExistingDays() != null){
								duration = exclusion.getPreExistingDays();
							}

							if (exclusion.getAgeConstraint() != null) {
								ageConstraint = exclusion.getAgeConstraint();
							}
							if (exclusion.getAgeParameter() != null) {
								param = exclusion.getAgeParameter();
							}

							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
											
										}
									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}
									}
								}

							} else {
							
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "DIAGNOSIS EXCLUSION";
	
									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									/**
									 * 0000230: [CAR] [PRE EXISTING] Setelah lewat masa Pre Existing harusnya Claim status OPEN
									 */
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}
	
							}
						}
					}
				} else {
					
					Diagnosis diagnosis = object.getDiagnosis2Id();
					
					MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(
							diagnosis.getDiagnosisId(), object.getMemberId().getMemberId());

					int ageConstraint = -1;
					String param = "";
					int duration = -1;

					if (exclusion != null) {
						if (exclusion.getAgeConstraint() != null) {
							ageConstraint = exclusion.getAgeConstraint();
						}
						if (exclusion.getAgeParameter() != null) {
							param = exclusion.getAgeParameter();
						}
						duration =  exclusion.getPreExistingDays() == null ? -1 : exclusion.getPreExistingDays();
					}

					if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint == -1) {

						
						
						if (duration > 0){
							Member member = memberService.get(object.getMemberId().getMemberId());
							
							if (member != null && member.getJoinDate() != null){
								DateTime join = new DateTime(member.getJoinDate().getTime());
								DateTime admission = new DateTime(object.getAdmissionDate().getTime());
								
								int daysDuration = Days.daysBetween(join, admission).getDays();
								
								if (daysDuration <  duration){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "PRE-EXISTING DIAGNOSIS";
	
									rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
								}
							}
						}
						else {
						
							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);
	
							remarks = "DIAGNOSIS EXCLUSION";
	
							rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
						}
						
					} else if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint > 0) {

						if (param.equalsIgnoreCase("LT")) {
							if (ageConstraint > memberAge) {
								
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}
						if (param.equalsIgnoreCase("GT")) {
							if (ageConstraint < memberAge) {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}

					}
					else if (exclusion != null && exclusion.getPreExistingDays() != null){

						Member member = memberService.get(object.getMemberId().getMemberId());
						
						if (member != null && member.getJoinDate() != null){
							DateTime join = new DateTime(member.getJoinDate().getTime());
							DateTime admission = new DateTime(object.getAdmissionDate().getTime());
							
							int daysDuration = Days.daysBetween(join, admission).getDays();
							
							if (daysDuration <  duration){
								status.setCaseStatusId(Claim.CLAIM_REJECT);
								object.setClaimStatus(status);

								remarks = "PRE-EXISTING DIAGNOSIS";

								rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
							}
						}
					}
					
					else if (exclusion != null && exclusion.getExpireDate() != null) {
						Date expireDate = exclusion.getExpireDate();

						DateTime exDt = new DateTime(expireDate);
						DateTime ex = exDt.plusDays(1);

						if (object.getAdmissionDate().before(new Date(ex.getMillis()))) {

							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);

							remarks = "PRE-EXISTING DIAGNOSIS";

							rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
						} else {
							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {

										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
							}
						}
					}
				}
			}
			if (object.getDiagnosis3Id() != null) {
				String[] eqPolicyDiagParam = { "policyId", "deletedStatus", "diagnosisId.diagnosisId" };
				Object[] eqPolicyDiagValue = { policyId, 0, object.getDiagnosis3Id().getDiagnosisId() };

				int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null, null, eqPolicyDiagParam,
						eqPolicyDiagValue);

				if (totalPolicyExclusion > 0) {
					Collection<PolicyDiagnosisExclusion> diagExclusion = policyDiagnosisExclusionService.search(null,
							null, eqPolicyDiagParam, eqPolicyDiagValue, 0, totalPolicyExclusion);

					if (diagExclusion != null) {

						Iterator<PolicyDiagnosisExclusion> iteratorEx = diagExclusion.iterator();

						PolicyDiagnosisExclusion exclusion = null;

						if (iteratorEx.hasNext()) {
							exclusion = iteratorEx.next();
						}

						if (exclusion != null) {

							int ageConstraint = -1;
							String param = "";
							int duration = -1;
							
							if (exclusion.getPreExistingDays() != null){
								duration = exclusion.getPreExistingDays();
							}

							if (exclusion.getAgeConstraint() != null) {
								ageConstraint = exclusion.getAgeConstraint();
							}
							if (exclusion.getAgeParameter() != null) {
								param = exclusion.getAgeParameter();
							}

							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
	
											remarks = "DIAGNOSIS EXCLUSION";
	
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}
									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {
										
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
	
											remarks = "DIAGNOSIS EXCLUSION";
	
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}								
										}
									}
								}
							} else {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "DIAGNOSIS EXCLUSION";
	
									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									/**
									 * 0000230: [CAR] [PRE EXISTING] Setelah lewat masa Pre Existing harusnya Claim status OPEN
									 */
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}
	
							}
						}
					}
				} else {
					
					Diagnosis diagnosis = object.getDiagnosis3Id();
					
					MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(
							diagnosis.getDiagnosisId(), object.getMemberId().getMemberId());

					int ageConstraint = -1;
					String param = "";
					int duration = -1;

					if (exclusion != null) {
						if (exclusion.getAgeConstraint() != null) {
							ageConstraint = exclusion.getAgeConstraint();
						}
						if (exclusion.getAgeParameter() != null) {
							param = exclusion.getAgeParameter();
						}
						duration =  exclusion.getPreExistingDays() == null ? -1 : exclusion.getPreExistingDays();
					}

					if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint == -1) {

						
						
						if (duration > 0){
							Member member = memberService.get(object.getMemberId().getMemberId());
							
							if (member != null && member.getJoinDate() != null){
								DateTime join = new DateTime(member.getJoinDate().getTime());
								DateTime admission = new DateTime(object.getAdmissionDate().getTime());
								
								int daysDuration = Days.daysBetween(join, admission).getDays();
								
								if (daysDuration <  duration){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "PRE-EXISTING DIAGNOSIS";
	
									rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
								}
							}
						}
						else {
						
							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);
	
							remarks = "DIAGNOSIS EXCLUSION";
	
							rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
						}
						
					} else if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint > 0) {

						if (param.equalsIgnoreCase("LT")) {
							if (ageConstraint > memberAge) {
								
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}
						if (param.equalsIgnoreCase("GT")) {
							if (ageConstraint < memberAge) {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}

					}
					else if (exclusion != null && exclusion.getPreExistingDays() != null){

						Member member = memberService.get(object.getMemberId().getMemberId());
						
						if (member != null && member.getJoinDate() != null){
							DateTime join = new DateTime(member.getJoinDate().getTime());
							DateTime admission = new DateTime(object.getAdmissionDate().getTime());
							
							int daysDuration = Days.daysBetween(join, admission).getDays();
							
							if (daysDuration <  duration){
								status.setCaseStatusId(Claim.CLAIM_REJECT);
								object.setClaimStatus(status);

								remarks = "PRE-EXISTING DIAGNOSIS";

								rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
							}
						}
					}
					
					else if (exclusion != null && exclusion.getExpireDate() != null) {
						Date expireDate = exclusion.getExpireDate();

						DateTime exDt = new DateTime(expireDate);
						DateTime ex = exDt.plusDays(1);

						if (object.getAdmissionDate().before(new Date(ex.getMillis()))) {

							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);

							remarks = "PRE-EXISTING DIAGNOSIS";

							rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
						} else {
							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {

										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
							}
						}
					}
				}
			}
			Member member = memberService.get(object.getMemberId().getMemberId());

			if (member.getNoClaimStartDate() != null && member.getNoClaimEndDate() != null) {
				DateTime admPlus1 = new DateTime(object.getAdmissionDate()).plusDays(1);
				DateTime admMinus1 = new DateTime(object.getAdmissionDate()).minusDays(1);

				if (new java.sql.Date(admPlus1.getMillis()).after(member.getNoClaimStartDate())
						&& new java.sql.Date(admMinus1.getMillis()).before(member.getNoClaimEndDate())) {

					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "SUBJECT TO NO CLAIM";
					rejectCategory = RejectCategory.SUBJECT_NO_CLAIM;
				}
			}

			if (member.getStatus() != null && (member.getStatus().intValue() == SubscriptionStatus.BLOCKED
					|| member.getStatus().intValue() == SubscriptionStatus.GRACE)) {

				if (member.getSuspendStartDate() != null && member.getSuspendEndDate() != null) {
					DateTime admPlus1 = new DateTime(object.getAdmissionDate());
					DateTime admMinus1 = new DateTime(object.getAdmissionDate());

					if (new java.sql.Date(admPlus1.getMillis()).after(member.getSuspendStartDate())
							&& new java.sql.Date(admMinus1.getMillis()).before(member.getSuspendEndDate())) {
						status.setCaseStatusId(Claim.CLAIM_REJECT);
						object.setClaimStatus(status);

						remarks = "GRACE / SUSPEND PERIODE";

						rejectCategory = RejectCategory.SUSPEND;
					}
				} else if (member.getSuspendStartDate() != null && member.getSuspendEndDate() == null) {
					DateTime admPlus1 = new DateTime(object.getAdmissionDate());

					if (new java.sql.Date(admPlus1.getMillis()).after(member.getSuspendStartDate())) {
						status.setCaseStatusId(Claim.CLAIM_REJECT);
						object.setClaimStatus(status);

						remarks = "GRACE / SUSPEND PERIODE";

						rejectCategory = RejectCategory.SUSPEND;
					}
				} else if (member.getSuspendStartDate() == null && member.getSuspendEndDate() == null) {
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "GRACE / SUSPEND PERIODE";

					rejectCategory = RejectCategory.SUSPEND;
				}
			}
			if (member.getEffectiveDate().after(object.getAdmissionDate())) {
				// check apakah mempunyai history MemberProduct pada timeframe tersebut
				MemberProduct memberProduct = memberProductService.getMemberProductByDate(member.getMemberId(), object.getCaseCategoryId()
						.getCaseCategoryId(), object.getAdmissionDate(), object.getDischargeDate());
				
				if (memberProduct == null){
				
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);
	
					remarks = "MEMBER NOT ACTIVE";
	
					rejectCategory = RejectCategory.MEMBER_ACTIVATION;
				}
			}

			if ((member.getResignedDate() != null && (member.getResignedDate().before(object.getAdmissionDate())
					|| member.getResignedDate().equals(object.getAdmissionDate())))
					|| (member.getExpireDate().before(object.getAdmissionDate())
							|| member.getExpireDate().equals(object.getAdmissionDate()))) {

				status.setCaseStatusId(Claim.CLAIM_REJECT);
				object.setClaimStatus(status);

				remarks = "MEMBER TERMINATED";

				rejectCategory = RejectCategory.MEMBER_EXPIRED;
			}

			if (object.getClaimStatus() == null
					|| object.getClaimStatus().getCaseStatusId().intValue() != Claim.CLAIM_REJECT) {
				if (member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {
					String[] eqSuspendParam = { "memberId.memberId", "deletedStatus" };
					Object[] eqSuspendValue = { member.getMemberId(), Integer.valueOf(0) };

					int total = memberSuspendHistoryService.getTotal(null, null, eqSuspendParam, eqSuspendValue);

					Collection<MemberSuspendHistory> suspendList = memberSuspendHistoryService.search(null, null,
							eqSuspendParam, eqSuspendValue, 0, total);

					for (Iterator iterator = suspendList.iterator(); iterator.hasNext();) {

						MemberSuspendHistory memberSuspendHistory = (MemberSuspendHistory) iterator.next();

						if (memberSuspendHistory != null) {
							Date startDate = memberSuspendHistory.getStartDate();
							Date endDate = memberSuspendHistory.getEndDate();

							if (startDate != null && endDate != null) {
								if (startDate.before(object.getAdmissionDate())
										&& endDate.after(object.getAdmissionDate())) {
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "GRACE / SUSPEND PERIODE";
									rejectCategory = RejectCategory.SUSPEND;

									break;
								}
							} else if (startDate != null && endDate == null) {
								if (startDate.before(object.getAdmissionDate())) {

									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "GRACE / SUSPEND PERIODE";

									rejectCategory = RejectCategory.SUSPEND;

									break;
								}
							}
						}

					}
				}
			}
			if (member.getMemberGroupId() != null) {
				MemberGroup memberGroup = memberGroupService.get(member.getMemberGroupId().getMemberGroupId());

				if (memberGroup != null
						&& memberGroup.getStatus().getStatusId().intValue() == SubscriptionStatus.BLOCKED) {
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "GROUP SUSPEND ";
					rejectCategory = RejectCategory.SUSPEND;
				}
			}
			if (member.getCurrentPolicyId() != null) {
				Policy currentPolicy = policyService.get(member.getCurrentPolicyId().getPolicyId());

				if (currentPolicy != null
						&& currentPolicy.getPolicyStatus().getStatusId().intValue() == SubscriptionStatus.BLOCKED) {
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "POLICY SUSPEND ";
					rejectCategory = RejectCategory.SUSPEND;
				}
			}

			if (claimItem != null) {
				Iterator iterator = claimItem.iterator();
				while (iterator.hasNext()) {

					ClaimItem ci = (ClaimItem) iterator.next();
					ci.setClaimId(object);

					CaseStatus stat = new CaseStatus();

					stat.setCaseStatusId(ClaimItem.CLAIM_ITEM_APPROVED);

					if (status.getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {
						ci.setBenefitCheckRemarks(remarks);
						ci.setClaimItemApprovedValue(0.0);
						ci.setExcessValue(ci.getClaimItemValue());
						ci.setClaimItemCoveredValue(0.0);

						stat.setCaseStatusId(ClaimItem.CLAIM_ITEM_REJECTED);
					}

					ci.setClaimItemStatus(stat);

					if (object.getExchangeRate() != null) {
						double exchangeRate = object.getExchangeRate().doubleValue();
						double itemCharge = exchangeRate * ci.getClaimItemValue();
						ci.setProductCurrencyChargeValue(itemCharge);
						ci.setExchangeRate(exchangeRate);
						ci.setExchangeRateDate(object.getExchangeRateDate());
					}

					claimItemService.create(ci, actionUser);
				}
			}

			int totalClaimItem = 0;
			Collection<ClaimItem> claimItems = claimItemService.getClaimItem(object.getClaimId());

			if (claimItems != null) {
				Iterator<ClaimItem> ciIterator = claimItems.iterator();

				while (ciIterator.hasNext()) {

					ClaimItem ci = ciIterator.next();
					claimCharge += ci.getClaimItemValue();
					totalClaimItem += 1;
				}
			}

			if (remarks != null && remarks.equalsIgnoreCase("")) {
				object.setCheckerRemarks(remarks);
			}

			object.setClaimStatus(status);

			object.setTotalItem(totalClaimItem);
			object.setPembulatan(pembulatan);
			object.setClaimChargeValue(claimCharge);

			if (status.getCaseStatusId().intValue() == Claim.CLAIM_REJECT && rejectCategory != null) {

				if (object.getCaseId() != null) {
					Case theCase = caseDao.get(object.getCaseId().getCaseId());

					if (theCase != null) {
						CaseStatus rejectStatus = new CaseStatus();
						rejectStatus.setCaseStatusId(Case.CASE_REJECT);
						theCase.setCaseStatusId(rejectStatus);

						caseDao.update(theCase);
					}
				}

				object.setRejectionRemarks(remarks);

				int numberCounter = 0;
				RejectCategory category = new RejectCategory();
				category.setRejectCategoryId(rejectCategory);

				RejectedClaim rejectClaim = new RejectedClaim();
				rejectClaim.setClaimId(object);
				rejectClaim.setDescription(remarks);
				rejectClaim.setRejectionSubject(remarks);
				rejectClaim.setRejectionCategory(category);
				rejectClaim.setDeletedStatus(0);
				rejectClaim.setCreatedBy(actionUser.getUser().getUsername());
				rejectClaim.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));

				Configuration configuration = configurationService
						.getClientConfiguration(object.getMemberId().getClientId().getClientId());

				if (configuration == null) {
					configuration = configurationService.getSystemConfiguration();
				}

				Integer isUsingSequence = configuration.getIsUsingSequenceNumber();

				if (isUsingSequence != null && isUsingSequence.intValue() == 1) {
					String seqSQL = configuration.getClaimRejectSeqName();

					Session session = rejectClaimDao.getRejectionSession();

					if (session != null) {
						SQLQuery q = session.createSQLQuery(seqSQL);

						List<Object> list = q.list();

						if (list != null && !list.isEmpty()) {
							Iterator<Object> iterator = list.iterator();

							if (iterator.hasNext()) {
								Object nextval = iterator.next();

								if (nextval != null) {
									BigInteger val = (BigInteger) nextval;
									numberCounter = val.intValue();
								}
							}
						}
					}
				} else {
					numberCounter = configuration.getClaimRejectionNumber();
				}

				DateTime dateTime = new DateTime();

				int month = dateTime.getMonthOfYear();
				String monthStr = "";
				if (month < 10) {
					monthStr = "0" + month;
				} else {
					monthStr = month + "";
				}

				String number = configuration.getClaimRejectionNumberTemplate();
				String counter = numberCounter + "";

				number = number.replace("${counter}", counter);
				number = number.replace("${month}", monthStr);
				number = number.replace("${year}", dateTime.getYear() + "");

				rejectClaim.setRejectionNumber(number);

				rejectClaimDao.create(rejectClaim);
			}
			claimDao.update(object);

			/**
			 * Claim Initial History
			 */

			recordHistory(object, "NEW CLAIM", "", actionUser);
		}

		return object;
	}

	public void recordHistory(Claim object, String actionType, String note, ActionUser actionUser) throws Exception {
		ClaimHistory claimHistory = new ClaimHistory();
		claimHistory.setActionTime(new java.sql.Timestamp(System.currentTimeMillis()));
		claimHistory.setActionType(actionType);
		claimHistory.setDescription(note);
		claimHistory.setClaimNumber(object.getClaimNumber());
		claimHistory.setClaimId(object);
		claimHistory.setMemberName(object.getMemberName());
		claimHistory.setCaseCategoryName(object.getClaimServiceType());
		claimHistory.setClaimChargeValue(object.getClaimChargeValue());
		claimHistory.setClaimApprovedValue(object.getClaimApprovedValue());
		claimHistory.setClaimExcessValue(object.getClaimExcessValue());
		claimHistory.setPaidToProvider(object.getPaidToProvider());
		claimHistory.setPrePaidExcess(object.getPrePaidExcess());
		claimHistory.setRefund(object.getRefund());

		claimHistory.setClaimStatus(object.getClaimStatus());

		if (object.getProviderId() != null) {
			claimHistory.setProviderName(object.getProviderId().getProviderName());
		}
		if (actionUser != null) {
			claimHistory.setActionBy(actionUser.getUser().getUsername());
		}

		if (object.getDiagnosisId() != null) {
			claimHistory.setDiagnosis1Code(object.getDiagnosis1Code());
			claimHistory.setDiagnosis1Name(object.getDiagnoseName());

		}
		if (object.getDiagnosis2Id() != null) {
			claimHistory.setDiagnosis2Code(object.getDiagnosis2Code());
		}
		if (object.getDiagnosis3Id() != null) {
			claimHistory.setDiagnosis3Code(object.getDiagnosis3Code());
		}

		ClaimHistory latestHistory = claimHistoryService.getLatestHistory(object.getClaimId());

		if (latestHistory != null && latestHistory.getActionTime() != null) {
			Seconds seconds = Seconds.secondsBetween(new DateTime(latestHistory.getActionTime().getTime()),
					new DateTime(System.currentTimeMillis()));

			int totalSecond = seconds.getSeconds();

			claimHistory.setDuration(totalSecond);
			LocalTime local = new LocalTime(0, 0);
			local = local.plusSeconds(totalSecond);
			String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);

			claimHistory.setDurationString(output);
		}

		claimHistoryService.create(claimHistory, actionUser);
	}

	public boolean approve(Claim object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		if (object != null) {

			String[] required = { "Claim.ClaimItems" };

			// object = get (object.getClaimId(), required);
			//
			// if (object != null) {
			//
			boolean res = true;

			Collection claimItem = object.getClaimItems();

			if (claimItem != null) {

				Iterator iterator = claimItem.iterator();
				ClaimItem item = null;
				CaseStatus status = null;

				while (iterator.hasNext()) {
					item = (ClaimItem) iterator.next();

					if (item != null) {
						status = item.getClaimItemStatus();

						if (status != null
								&& status.getCaseStatusId().equals(Integer.valueOf(ClaimItem.CLAIM_ITEM_OPEN))) {
							res = false;
						}
					}

				}

				if (res) {

					status = new CaseStatus();
					status.setCaseStatusId(Integer.valueOf(Claim.CLAIM_VERIFIED));
					object.setClaimStatus(status);

					if (actionUser != null) {

						User user = actionUser.getUser();
						if (user != null) {

							object.setVerifiedBy(user.getUsername());

						}
					}
					object.setVerifiedTime(new Timestamp(System.currentTimeMillis()));

					update(object, actionUser);
					result = true;
				}
			}
			// }
		}

		return result;
	}

	public Claim updateClaim(Claim object, ActionUser actionUser) throws Exception {
		Claim result = update(object, actionUser);
		if (result != null) {
			openClaimItems(result, actionUser);
		}
		return result;
	}

	public Claim updateCaseCalculationClaim(Claim object, ActionUser actionUser) throws Exception {
		Claim result = update(object, actionUser);

		if (result != null) {
			openClaimItems(result, actionUser);
		}
		/**
		 * if (object.getCaseId() != null){
		 * 
		 * Case caseBean = caseDao.get(object.getCaseId().getCaseId());
		 * 
		 * CaseStatus previousStatus = caseBean.getCaseStatusId(); String
		 * currentData = caseBean.toHistoryString();
		 * 
		 * CaseStatus currentStatus = new CaseStatus();
		 * currentStatus.setCaseStatusId(CaseStatus.CASE_FINALIZED);
		 * 
		 * String actionBy = actionUser.getUser().getUsername(); CaseHistory
		 * history = new CaseHistory(); history.setActionBy(actionBy);
		 * history.setCreatedBy(actionBy); history.setHistoryTime(new
		 * java.sql.Timestamp(System.currentTimeMillis()));
		 * history.setCreatedTime(new
		 * java.sql.Timestamp(System.currentTimeMillis()));
		 * history.setBeforeStatus(previousStatus);
		 * history.setAfterStatus(currentStatus); history.setActionType(
		 * "FINALIZE CASE"); history.setMemberName(caseBean.getMemberName());
		 * history.setCaseNumber(caseBean.getCaseNumber());
		 * history.setCaseCategoryName
		 * (caseBean.getCaseCategoryId().getCaseCategoryName());
		 * 
		 * 
		 * history.setAfterActionData(currentData); history.setCaseId(caseBean);
		 * history.setDescription(object.getDescription());
		 * 
		 * 
		 * if (caseBean.getDiagnosis1Name() != null){
		 * history.setDiagnosisCode(caseBean.getDiagnosis1Name()); } if
		 * (caseBean.getDiagnosis2Name() != null){
		 * history.setDiagnosisCode(caseBean.getDiagnosis2Name()); } if
		 * (caseBean.getDiagnosis3Name() != null){
		 * history.setDiagnosisCode(caseBean.getDiagnosis3Name()); } if
		 * (caseBean.getProviderName() != null){
		 * history.setProviderName(caseBean.getProviderName()); } if
		 * (caseBean.getCaseNumber() != null){
		 * history.setCaseNumber(caseBean.getCaseNumber()); } if
		 * (caseBean.getMemberName() != null){
		 * history.setMemberName(caseBean.getMemberName()); } if
		 * (caseBean.getMemberNumber() != null){
		 * history.setMemberNumber(caseBean.getMemberNumber()); } if
		 * (caseBean.getCaseCategoryId() != null){
		 * history.setCaseCategoryName(caseBean
		 * .getCaseCategoryId().getCaseCategoryName()); }
		 * 
		 * 
		 * CaseHistory previousHistory =
		 * caseHistoryService.getLatestCaseHistory(caseBean.getCaseId());
		 * 
		 * if (previousHistory != null && previousHistory.getHistoryTime() !=
		 * null){
		 * 
		 * Seconds seconds = Seconds.secondsBetween(new
		 * DateTime(previousHistory.getHistoryTime().getTime()) , new
		 * DateTime(System.currentTimeMillis()));
		 * 
		 * int totalSecond = seconds.getSeconds();
		 * 
		 * history.setDuration(totalSecond); LocalTime local = new
		 * LocalTime(0,0); local = local.plusSeconds(totalSecond); String output
		 * = DateTimeFormat.forPattern("HH:mm:ss").print(local);
		 * 
		 * history.setDurationString(output); }
		 * 
		 * 
		 * caseHistoryService.create(history, actionUser); }
		 */

		return result;
	}

	public boolean openClaimItems(Claim object, ActionUser actionUser) throws Exception {
		boolean result = false;

		if (object != null) {
			String[] required = { "ClaimItem.ItemId", "ClaimItem.ClaimItemStatus" };
			Collection claimItem = claimItemService.getClaimItem(object.getClaimId(), required);

			if (claimItem != null) {
				Iterator iterator = claimItem.iterator();
				ClaimItem item = null;
				CaseStatus status = null;

				while (iterator.hasNext()) {
					item = (ClaimItem) iterator.next();

					if (item != null) {
						status = item.getClaimItemStatus();

						if (status != null
								&& status.getCaseStatusId().equals(Integer.valueOf(ClaimItem.CLAIM_ITEM_PRE_OPEN))) {
							item.setClaimItemApprovedValue(Double.valueOf(0));
							item.setExcessValue(Double.valueOf(0));
							item.setBenefitCheckedBy("");
							item.setBenefitCheckRemarks("");
							item.setBenefitCheckedDate(null);
							item.setIsCorrected(true);
							item.setClaimExGratia(Double.valueOf(0));
							item.setMemberBenefitId(null);
							CaseStatus claimStatus = new CaseStatus();
							claimStatus.setCaseStatusId(Claim.CLAIM_OPEN);
							item.setClaimItemStatus(claimStatus);

							claimItemService.update(item, actionUser);
						}
					}

				}
				result = true;
			}
		}

		return result;
	}

	// harusnya invoke ke Service Reject Claim
	public boolean reject(Claim object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		if (object != null) {

			String[] required = { "Claim.ClaimItems" };
			Claim claim = get(object.getClaimId(), required);

			if (claim != null) {
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(Claim.CLAIM_REJECT);
				claim.setClaimStatus(status);
				// begin luthfi untuk kebutuhan apabila claim reject, kolom
				// payment_id juga harus di null-kan supaya tidak masuk ke surat
				// jalan
				claim.setPaymentId(null);
				// end luthfi

				update(claim, actionUser);

				recordHistory(claim, "REJECT CLAIM", claim.getRejectionRemarks(), actionUser);

				Collection<ClaimItem> claimItems = claim.getClaimItems();

				if (claimItems != null) {
					Iterator<ClaimItem> iterator = claimItems.iterator();

					if (iterator != null) {
						while (iterator.hasNext()) {
							ClaimItem item = iterator.next();

							if (item != null) {
								claimItemService.reject(item, actionUser);
							}
						}
					}
				}
			}

			result = true;
		}

		return result;
	}

	public boolean disposition(Claim object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		if (object != null) {
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(Claim.CLAIM_PAYMENT_DISPOSITIONED));
			object.setClaimStatus(status);

			if (actionUser != null) {
				User user = actionUser.getUser();

				if (user != null) {
					object.setModifiedBy(user.getUsername());
					object.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				}
			}
			claimDao.update(object);

			recordHistory(object, "PAYMENT DISPOSITION", object.getPaymentInstallmentId().getInstallmentNumber(),
					actionUser);
			result = true;
		}

		return result;

	}

	public boolean pay(Claim object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		if (object != null) {
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(Claim.CLAIM_PAID));
			object.setClaimStatus(status);

			if (actionUser != null) {
				User user = actionUser.getUser();

				if (user != null) {
					object.setModifiedBy(user.getUsername());
					object.setModifiedTime(new Timestamp(System.currentTimeMillis()));

					object.setPaidBy(user.getUsername());

					if (object.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT) {
						object.setClaimPaidValue(object.getClaimApprovedValue());
					} else if (object.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS) {
						object.setClaimPaidValue(object.getPaidToProvider());
					}
				}
			}
			claimDao.update(object);

			recordHistory(object, "PAYMENT", object.getPaymentRemarks(), actionUser);
			result = true;
		}

		return result;

	}

	public boolean finalize(Claim object, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;

		if (object != null) {
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(Claim.CLAIM_CHECKED));
			object.setClaimStatus(status);

			if (actionUser != null) {

				User user = actionUser.getUser();
				if (user != null) {
					object.setModifiedBy(user.getUsername());
					object.setModifiedTime(new Timestamp(System.currentTimeMillis()));

					object.setApprovedBy(user.getUsername());
					object.setApprovedTime(new Timestamp(System.currentTimeMillis()));
				}
			}
			claimDao.update(object);
			recordHistory(object, "FINALIZE", "", actionUser);
			result = true;
			;
		}
		return result;
	}

	public Claim extract(Integer claimId, BatchClaim batchClaim, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		Claim result = null;

		Claim object = get(claimId);
		if (object != null && batchClaim != null) {

			BatchClaim currentBatchClaim = object.getBatchClaimId();

			if (currentBatchClaim != null) {

				int totalCurrent = getTotal(null, null, "batchClaimId.batchClaimId",
						currentBatchClaim.getBatchClaimId());
				Collection<Claim> beforeClaim = search(null, null, "batchClaimId.batchClaimId",
						currentBatchClaim.getBatchClaimId(), 0, totalCurrent);

				double currentBatchValue = 0;
				double currentBatchFinalValue = 0;

				// Claim clone = object.clone();

				if (beforeClaim != null) {
					Iterator<Claim> beforeIterator = beforeClaim.iterator();

					while (beforeIterator.hasNext()) {
						Claim claim = beforeIterator.next();

						if (claim != null) {
							Double approvedValue = claim.getClaimApprovedValue() == null ? 0.0
									: claim.getClaimApprovedValue();

							currentBatchValue += claim.getClaimChargeValue();
							currentBatchFinalValue += approvedValue;
						}
					}
				}

				object.setBatchClaimId(batchClaim);

				/*
				 * clone.setBatchClaimId(batchClaim); clone.setClaimId(null);
				 * 
				 * clone = create (clone,actionUser);
				 */
				String remarks = object.getClaimRemarks() + " \n\n" + "extracted from : "
						+ currentBatchClaim.getBatchClaimNumber() + " to " + batchClaim.getBatchClaimNumber();

				object.setClaimRemarks(remarks);
				object.setIsCorrected(true);
				// object.setCorrectionClaim(clone);
				object.setCorrectionTime(new java.sql.Timestamp(System.currentTimeMillis()));

				object = update(object, actionUser);
				// delete (object, actionUser);

				BatchClaim currentBatch = batchClaimService.get(currentBatchClaim.getBatchClaimId());
				currentBatch.setBatchClaimInitialValue(currentBatchValue);
				currentBatch.setBatchClaimFinalValue(currentBatchFinalValue);

				batchClaimService.update(currentBatch, actionUser);

				double nextBatchValue = 0;
				double nextBatchFinalValue = 0;

				int totalNext = getTotal(null, null, "batchClaimId.batchClaimId", batchClaim.getBatchClaimId());

				Collection<Claim> afterClaim = search(null, null, "batchClaimId.batchClaimId",
						batchClaim.getBatchClaimId(), 0, totalNext);

				if (afterClaim != null) {
					Iterator<Claim> afterIterator = afterClaim.iterator();

					while (afterIterator.hasNext()) {
						Claim claim = afterIterator.next();

						if (claim != null) {
							nextBatchValue += claim.getClaimChargeValue();
							nextBatchFinalValue += claim.getClaimApprovedValue();
						}
					}
				}

				BatchClaim afterBatch = batchClaimService.get(batchClaim.getBatchClaimId());

				if (afterBatch != null) {
					afterBatch.setBatchClaimInitialValue(nextBatchValue);
					afterBatch.setBatchClaimFinalValue(nextBatchFinalValue);

					batchClaimService.update(afterBatch, actionUser);
				}

				result = object;
				// result = clone;
			}
		}

		return result;
	}

	public String approveMedicalItem(Claim object, ClaimItem claimItem, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		String result = "";

		boolean res = claimItemService.approveVerifyItem(claimItem, actionUser);

		if (res) {
			// check if there are another claim item to be verified

			ClaimItem nextItem = claimItemService.getNextToVerifyItem(object);

			if (nextItem != null) {
			} else {
			}
		}

		return result;
	}

	public String approveMedicalBulk(Integer claimId, Collection<ClaimItem> claimItems, ActionUser user)
			throws Exception {

		// IF SUCCESS return OK
		// IF still have a Number Value then Return Unverified ClaimItem Ammount
		String result = "";

		Claim claim = get(claimId);
		if (claim != null && claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PROCESS_OK) {
			if (claimItems != null) {

				claimItemService.approveBulkItemVerification(claimItems, user);
				Collection<ClaimItem> verifiableItem = claimItemService.getVerifiableItem(claimId);

				if (verifiableItem != null && verifiableItem.size() > 0) {
					result = verifiableItem.size() + "";
				} else {
					result = "OK";

					if (claim != null) {
						CaseStatus status = new CaseStatus();
						status.setCaseStatusId(Claim.CLAIM_VERIFIED);
						claim.setClaimStatus(status);

						if (user != null && user.getUser() != null) {
							claim.setVerifiedBy(user.getUser().getUsername());
						}
						claim.setVerifiedTime(new Timestamp(System.currentTimeMillis()));

						update(claim, user);
					} else {
						throw new Exception("rollback data");
					}
				}
			}
		}
		return result;
	}

	public String approveExGratia(Integer claimId, String remarks, ActionUser user) throws Exception {

		// IF SUCCESS return OK
		// IF still have a Number Value then Return Unverified ClaimItem Ammount
		String result = "";

		Claim claim = get(claimId);
		if (claim != null && claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PROCESS_OK) {

			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Claim.CLAIM_VERIFIED);
			claim.setClaimStatus(status);
			claim.setIsExGratia(Integer.valueOf(1));
			claim.setMedicalRemarks(remarks);

			update(claim, user);

			recordHistory(claim, "APPROVE EX-GRATIA", remarks, user);

			Collection<ClaimItem> items = claimItemService.getClaimItem((Integer) claimId);

			if (items != null) {
				Iterator<ClaimItem> itemIterator = items.iterator();
				CaseStatus caseStatus = new CaseStatus();
				caseStatus.setCaseStatusId(Claim.CLAIM_APPROVED);

				while (itemIterator.hasNext()) {
					ClaimItem item = itemIterator.next();

					if (item != null) {
						item.setClaimItemStatus(caseStatus);
						claimItemService.update(item, user);
					}
				}
			}
			result = "OK";
		}
		return result;
	}

	public String approveClaim(Integer claimId, ActionUser user) throws Exception {

		// IF SUCCESS return OK
		// IF still have a Number Value then Return Unverified ClaimItem Ammount
		String result = "";

		Claim claim = get(claimId);
		if (claim != null && claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_PROCESS_OK) {

			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Claim.CLAIM_VERIFIED);
			claim.setClaimStatus(status);

			update(claim, user);

			recordHistory(claim, "APPROVE CLAIM", "", user);

			Collection<ClaimItem> items = claimItemService.getClaimItem((Integer) claimId);

			if (items != null) {
				Iterator<ClaimItem> itemIterator = items.iterator();
				CaseStatus caseStatus = new CaseStatus();
				caseStatus.setCaseStatusId(Claim.CLAIM_APPROVED);

				while (itemIterator.hasNext()) {
					ClaimItem item = itemIterator.next();

					if (item != null) {
						item.setClaimItemStatus(caseStatus);
						claimItemService.update(item, user);
					}
				}
			}
			result = "OK";
		}
		return result;
	}

	public String approveClaimBenefit(Integer claimId, Collection<ClaimItem> claimItems, ActionUser user)
			throws Exception {
		String result = "";

		String res = approveCheckItemBulk(claimId, claimItems, user);

		if (res != null) {
			String[] required = { "Claim.MemberId", "Claim.MemberId.MemberGroupId" };
			Claim claimResult = get(claimId, required);

			Member member = memberService.get(claimResult.getMemberId().getMemberId());

			if (claimResult.getClaimApprovedValue() < claimResult.getClaimChargeValue()) {
				if (member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().equalsIgnoreCase("")) {
					if (claimResult.getCobClaimReferenceId() == null) {
						boolean cobClaimStatus = createCobClaim(claimResult, member, claimItems, user);
					} else {
						Claim cobClaim = claimResult.getCobClaimReferenceId();

						if (cobClaim != null) {
							Collection<ClaimItem> cobClaimItems = claimItemService.getBenefitCheckItem(cobClaim);

							approveClaimBenefit(cobClaim.getClaimId(), cobClaimItems, user);
						}
					}
				}
			}

			if (claimResult.getCaseId() != null) {
				Case theCase = caseDao.get(claimResult.getCaseId().getCaseId());

				if (claimResult.getDiagnosisId() != null) {
					theCase.setDiagnosis1Id(claimResult.getDiagnosisId());
				}
				if (claimResult.getDiagnosis2Id() != null) {
					theCase.setDiagnosis2Id(claimResult.getDiagnosis2Id());
				}
				if (claimResult.getDiagnosis3Id() != null) {
					theCase.setDiagnosis3Id(claimResult.getDiagnosis3Id());
				}

				if (claimResult.getClaimChargeValue() != null) {
					theCase.setCaseClaimValue(claimResult.getClaimChargeValue());
				}
				if (claimResult.getClaimApprovedValue() != null) {
					theCase.setCaseApprovedValue(claimResult.getClaimApprovedValue());
				}
				if (claimResult.getClaimExcessValue() != null) {
					theCase.setCaseExcessValue(claimResult.getClaimExcessValue());
				}

				CaseStatus previousStatus = theCase.getCaseStatusId();

				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(CaseStatus.CASE_FINALIZED);

				if (claimResult.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {
					status.setCaseStatusId(Case.CASE_REJECT);
				}

				theCase.setCaseStatusId(status);

				if (user != null) {
					theCase.setModifiedBy(user.getUser().getUsername());

				}
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

				caseDao.update(theCase);

				// Edit 20150903 by FVO for FINALIZE CASE HISTORY
				String currentData = theCase.toHistoryString();

				CaseStatus currentStatus = new CaseStatus();
				currentStatus.setCaseStatusId(CaseStatus.CASE_FINALIZED);

				String actionBy = user.getUser().getUsername();
				CaseHistory history = new CaseHistory();
				history.setActionBy(actionBy);
				history.setCreatedBy(actionBy);
				history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setBeforeStatus(previousStatus);
				history.setAfterStatus(currentStatus);
				history.setActionType("FINALIZE CASE");
				history.setMemberName(theCase.getMemberName());
				history.setCaseNumber(theCase.getCaseNumber());
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());

				history.setAfterActionData(currentData);
				history.setCaseId(theCase);
				history.setDescription(claimResult.getDescription());

				if (theCase.getDiagnosis1Name() != null) {
					history.setDiagnosisCode(theCase.getDiagnosis1Name());
				}
				if (theCase.getDiagnosis2Name() != null) {
					history.setDiagnosisCode(theCase.getDiagnosis2Name());
				}
				if (theCase.getDiagnosis3Name() != null) {
					history.setDiagnosisCode(theCase.getDiagnosis3Name());
				}
				if (theCase.getProviderName() != null) {
					history.setProviderName(theCase.getProviderName());
				}
				if (theCase.getCaseNumber() != null) {
					history.setCaseNumber(theCase.getCaseNumber());
				}
				if (theCase.getMemberName() != null) {
					history.setMemberName(theCase.getMemberName());
				}
				if (theCase.getMemberNumber() != null) {
					history.setMemberNumber(theCase.getMemberNumber());
				}
				if (theCase.getCaseCategoryId() != null) {
					history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
				}

				CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());

				if (previousHistory != null && previousHistory.getHistoryTime() != null) {

					Seconds seconds = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()),
							new DateTime(System.currentTimeMillis()));

					int totalSecond = seconds.getSeconds();

					history.setDuration(totalSecond);
					LocalTime local = new LocalTime(0, 0);
					local = local.plusSeconds(totalSecond);
					String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);

					history.setDurationString(output);
				}

				caseHistoryService.create(history, user);
			}
		}

		return result;
	}

	public String approveCheckItemBulk(Integer claimId, Collection<ClaimItem> claimItems, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub

		String result = "";

		Claim claim = get(claimId);

		if (claim != null && (claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_VERIFIED || claim.getClaimStatus().getCaseStatusId() == Claim.CLAIM_VOID)) {

			Member member = memberService.get(claim.getMemberId().getMemberId());
			Policy policy = member.getCurrentPolicyId();
			Client client = null;

			if (claim.getMemberId().getClientId() != null) {
				client = claim.getMemberId().getClientId();
			}
			if (claimItems != null) {

				boolean isCurrencyConversion = false;
				double currencyMultiplier = claim.getExchangeRate() == null ? 1.0 : claim.getExchangeRate().doubleValue();
				if (currencyMultiplier > 1.0 || currencyMultiplier < 1.0) {
					isCurrencyConversion = true;
				}
				int excessPaymentType = 0;
				double[] res = claimItemService.approveBulkItemCheck(claimItems, user);
				double excess = 0;
				double approvedValue = 0.0;
				double claimApprovedValue = 0.0;
				double refund = 0.0;
				double prePaidExcess = 0.0;
				double paidToProvider = 0.0;

				Diagnosis diagnosis = claim.getDiagnosisId();

				// check SMM Limit
				double deductibleSMM = -1.0;
				MemberLimitLayer currentLayerLimit = memberLimitLayerService.getAvailableLayer(member.getMemberId(),claim.getCaseCategoryId().getCaseCategoryId());				
				MemberLimitLayer smmLimit = updateSMMClaim(claim, member);
				String excessRemarks = "";

				if (res != null) {
					excess = res[1];
					claimApprovedValue = res[0];
					System.out.println("EXCESS CHARGE SETELAH APPROVE BULK ITEM CHECK : " + excess + " APPROVED = " + claimApprovedValue);
				}

				if (claimApprovedValue > 0) {
					// update member benefit

					if (member != null) {
						if (member.getCurrentBenefitUsage() != null) {
							double currentBenefitUsage = member.getCurrentBenefitUsage().doubleValue();
							if (isCurrencyConversion) {
								member.setCurrentBenefitUsage(currentBenefitUsage + approvedValue * currencyMultiplier);
							} else {
								member.setCurrentBenefitUsage(currentBenefitUsage + approvedValue);
							}
						}
						if (member.getActualCustomerLimit() != null) {
							double currentBenefitLimit = member.getActualCustomerLimit().doubleValue();
							member.setActualCustomerLimit(currentBenefitLimit - approvedValue);
						}

						memberService.update(member);
						Iterator<ClaimItem> claimItemIterator = claimItems.iterator();
						if (claimItemIterator != null) {
							while (claimItemIterator.hasNext()) {
								ClaimItem claimItem = claimItemIterator.next();

								if (claimItem != null) {
									MemberBenefit memberBenefit = claimItem.getMemberBenefitId();

									if (memberBenefit == null) {
										Collection<MemberBenefit> collections = null;
										
										if (currentLayerLimit == null){
											collections = memberBenefitService.getMemberBenefitByDate(member.getMemberId(),claimItem.getItemId().getItemCategoryId().getItemCategoryId(),claim.getCaseCategoryId().getCaseCategoryId(),claim.getAdmissionDate(), claim.getDischargeDate(),0);
										}
										else {
											collections = memberBenefitService.getMemberBenefitByDate(member.getMemberId(),claimItem.getItemId().getItemCategoryId().getItemCategoryId(),claim.getCaseCategoryId().getCaseCategoryId(),claim.getAdmissionDate(), claim.getDischargeDate(),currentLayerLimit.getLayer());
										}

										if (collections != null) {
											Iterator<MemberBenefit> mbIterator = collections.iterator();

											if (mbIterator != null && mbIterator.hasNext()) {
												memberBenefit = mbIterator.next();
											}
										}
									}
									if (memberBenefit != null) {
										claimItem.setMemberBenefitId(memberBenefit);
										memberBenefit = memberBenefitService.get(memberBenefit.getMemberBenefitId());

										double currentBenefitUsage = memberBenefit.getBenefitUsage() == null ? 0.0 : memberBenefit.getBenefitUsage();

										if (isCurrencyConversion) {
											currentBenefitUsage += (claimItem.getClaimItemApprovedValue() * currencyMultiplier);
										} else {
											currentBenefitUsage += claimItem.getClaimItemApprovedValue();
										}

										double benefitRemaining = 0;

										if (memberBenefit.getBenefitCalculationMethod()
												.getBenefitUsageTypeId() == BenefitUsageType.ANNUAL) {
											if (memberBenefit.getCurrentBenefitPosition() != null) {

												if (isCurrencyConversion) {
													benefitRemaining = memberBenefit.getCurrentBenefitPosition() - claimItem.getClaimItemApprovedValue() * currencyMultiplier;
												} else {
													benefitRemaining = memberBenefit.getCurrentBenefitPosition() - claimItem.getClaimItemApprovedValue();
												}
												memberBenefit.setCurrentBenefitPosition(benefitRemaining);
											}
										}

										if (claimItem.getExcessValue() != null && claimItem.getExcessValue() > 0) {
											// check apakah cashless atau reimburse jika cashless maka excess harus
											// dimasukkan ke tabel benefit member

											double excessValue = 0;

											if (claim.getClaimTypeId().getClaimTypeId() == ClaimType.CASHLESS || claim.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS) {
												if (memberBenefit.getBenefitExcessed() != null) {
													excessValue = memberBenefit.getBenefitExcessed();

													if (isCurrencyConversion) {
														memberBenefit.setBenefitExcessed(excessValue + claimItem.getExcessValue() * currencyMultiplier);
													} else {
														memberBenefit.setBenefitExcessed(excessValue + claimItem.getExcessValue());
													}
												}
											}
										}

										double occurance = 0;
										occurance = claimItemService.getBenefitTotalOccured(member.getMemberId(),
												member.getEffectiveDate(), member.getExpireDate(),
												claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
												claim.getCaseCategoryId().getCaseCategoryId());

										if (claimItem.getClaimItemApprovedValue() != null && claimItem.getClaimItemApprovedValue() > 0) {
											memberBenefit.setBenefitOccuranceUsage(occurance + claimItem.getClaimItemAmount());
										}
										memberBenefit.setBenefitUsage(currentBenefitUsage);
										memberBenefitService.update(memberBenefit, user);
										String[] requiredMP = { "MemberProduct.ProductId" };

										MemberProduct memberProduct = memberProductService.get(memberBenefit.getMemberProductId().getMemberProductId(), requiredMP);

										/**
										 * kasus pertama : divert dengan semua item mengacu ke 1 member_product yang
										 * sama kasus kedua : divert dengan kombinasi item yang mengacu ke 2 atau
										 * lebih member_product check juga status activation nya
										 */

										if (memberProduct == null) {
											// possible divert ?

											MemberBenefit itemBenefit = null;
											Iterator<ClaimItem> iteratorItem = claimItems.iterator();
											if (iteratorItem.hasNext()) {
												ClaimItem ci = iteratorItem.next();

												if (ci != null) {
													itemBenefit = ci.getMemberBenefitId();

													if (itemBenefit != null && itemBenefit.getMemberProductId() != null) {
														memberProduct = memberProductService.get(itemBenefit.getMemberProductId().getMemberProductId(),requiredMP);
														if (memberProduct != null && memberProduct.getMemberProductStatus().getStatusId().intValue() != SubscriptionStatus.ACTIVE) {															
															if (memberProduct.getMemberProductStatus().getStatusId().intValue() == SubscriptionStatus.EXPIRED){
																// do nothing since it has been retrieved
															}
															else {
																memberProduct = memberProductService.getMemberActiveProduct(memberProduct.getMemberId().getMemberId(),memberProduct.getProductId().getCaseCategory().getCaseCategoryId());
															}
														}
													}
												}
											}

											if (itemBenefit == null) {
												Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitByDate(member.getMemberId(),claim.getCaseCategoryId().getCaseCategoryId(),claim.getAdmissionDate(), claim.getDischargeDate());
												if (memberBenefitList != null) {
													Iterator<MemberBenefit> iterator = memberBenefitList.iterator();

													if (iterator.hasNext()) {
														MemberBenefit firstMemberBenefit = iterator.next();

														if (firstMemberBenefit != null) {
															memberProduct = firstMemberBenefit.getMemberProductId();
															if (memberProduct != null && memberProduct.getMemberProductStatus().getStatusId().intValue() != SubscriptionStatus.ACTIVE) {
																if (memberProduct.getMemberProductStatus().getStatusId().intValue() == SubscriptionStatus.EXPIRED){
																// do nothing since it has been retrieved
																}
																else {	
																	memberProduct = memberProductService.getMemberActiveProduct(memberProduct.getMemberId().getMemberId(),memberProduct.getProductId().getCaseCategory().getCaseCategoryId());
																}
															}
														}
													}
												}
											}
										} else {
											if (memberProduct.getMemberProductStatus().getStatusId().intValue() != SubscriptionStatus.ACTIVE) {												
												memberProduct = memberProductService.get(memberProduct.getMemberProductId());
											}
										}

										MemberProduct sharedProductId = null;
										MemberProduct familyProductId = null;
										MemberProduct memberProductActive = null;
										MemberProduct familyProductIdActive = null;
										MemberProduct sharedProductIdActive = null;

										if (memberProduct != null) {
											sharedProductId = memberProduct.getSecondaryCoverageId();
											familyProductId = memberProduct.getFamilyProductId();

											if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE) {												
												if (memberProduct.getMemberProductStatus().getStatusId().intValue() == SubscriptionStatus.EXPIRED){
													memberProductActive = memberProduct; // do nothing since it has been retrieved
												}
												else {
													memberProductActive = memberProductService.getMemberActiveProduct(member.getMemberId(),memberBenefit.getCaseCategoryId().getCaseCategoryId());
												}
												if (memberProductActive == null) {
													// potentially divert benefit
													Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitByDate(member.getMemberId(),memberBenefit.getCaseCategoryId().getCaseCategoryId(),claim.getAdmissionDate(), claim.getDischargeDate());
													if (benefitList != null && benefitList.size() > 0) {
														for (Iterator iterator2 = benefitList.iterator(); iterator2.hasNext();) {
															MemberBenefit memberBenefit2 = (MemberBenefit) iterator2.next();
															if (memberBenefit2 != null && memberBenefit2.getMemberProductId() != null) {
																memberProductActive = memberBenefit2.getMemberProductId();

																if (memberProductActive != null) {
																	if (memberProductActive.getMemberProductStatus().getStatusId().intValue() != SubscriptionStatus.ACTIVE) {																		
																		// update ambil yang terbaru
																		if (memberProductActive.getMemberProductStatus().getStatusId().intValue() == SubscriptionStatus.EXPIRED){
																		// do nothing since it has been retrieved
																		}
																		else {
																			memberProductActive = memberProductService.getMemberActiveProduct(member.getMemberId(),memberBenefit2.getProductCaseCategoryId().getCaseCategoryId());
																		}
																	}
																}
																break;
															}
														}
													}
												}
											}
											if (memberProductActive != null) {
												if (memberProductActive.getFamilyProductId() != null){
													familyProductIdActive = memberProductService.get(memberProductActive.getFamilyProductId().getMemberProductId(), requiredMP);
												}
												if (memberProductActive.getSecondaryCoverageId() != null){
													sharedProductIdActive = memberProductService.get(memberProduct.getSecondaryCoverageId().getMemberProductId(),requiredMP);
												}
											}
											if (sharedProductId != null) {
												sharedProductId = memberProductService.get(sharedProductId.getMemberProductId(), requiredMP);
											}
											if (familyProductId != null) {
												familyProductId = memberProductService.get(familyProductId.getMemberProductId(), requiredMP);
											}
										}
										boolean deductPlan = true;
										if (memberBenefit.getIsDeductPlanLimit() != null && memberBenefit.getIsDeductPlanLimit().intValue() == 0) {
											deductPlan = false;
										}
										approvedValue = claimItem.getClaimItemApprovedValue() == null ? 0.0 : claimItem.getClaimItemApprovedValue();
										double benefitRemain = 0;

										if (memberProduct != null && smmLimit == null) {
											excessPaymentType = memberProduct.getProductId().getExcessPaymentType().intValue();
											// add IF -adq
											if (memberProduct.getActualBenefitLimit() != null) {
												benefitRemain = memberProduct.getActualBenefitLimit();
											}
											double annualBenefit = memberProduct.getAnnualBenefit() == null ? -1: memberProduct.getAnnualBenefit();
											double annualBenefitActive = 0.0;
											if (memberProductActive != null){
												annualBenefitActive = memberProductActive.getAnnualBenefit() == null ? -1 : memberProductActive.getAnnualBenefit();
											}
											if (sharedProductId == null && familyProductId == null) {
												if (memberProduct.getActualBenefitLimit() != null && memberProduct.getActualBenefitLimit().doubleValue() > 0) {
													if (deductPlan) {
														double benefitUsage = memberProduct.getBenefitUsage() == null ? 0.0 : memberProduct.getBenefitUsage().doubleValue();														
														double actualBenefitLimit = memberProduct.getActualBenefitLimit() == null ? 0.0 : memberProduct.getActualBenefitLimit().doubleValue();
														double actualBenefitLimitActive = 0.0;
														double benefitUsageActive = 0.0;

														if (memberProductActive != null) {
															actualBenefitLimitActive = memberProductActive.getActualBenefitLimit() == null ? 0.0 : memberProductActive.getActualBenefitLimit().doubleValue();															
															benefitUsageActive = memberProductActive.getBenefitUsage() == null ? 0.0 : memberProductActive.getBenefitUsage().doubleValue();
														}
														if (actualBenefitLimit > 0) {
															if (isCurrencyConversion) {
																updateBenefitUsage(approvedValue * currencyMultiplier, memberProduct, memberProductActive, benefitUsage, benefitUsageActive, actualBenefitLimit, actualBenefitLimitActive,user);
															} else {
																updateBenefitUsage(approvedValue, memberProduct,memberProductActive, benefitUsage,benefitUsageActive, actualBenefitLimit,actualBenefitLimitActive,user);
															}
															// Add IF -adq
															if (memberProduct.getActualBenefitLimit() != null) {
																benefitRemain = memberProduct.getActualBenefitLimit();
															}
															claim.setRemainingMemberLimit(benefitRemain);
														}
													}
												} else if (memberProduct.getActualBenefitLimit() != null && memberProduct.getActualBenefitLimit().doubleValue() == -1.0) {
													if (deductPlan) {
														double benefitUsage = memberProduct.getBenefitUsage() == null ? 0.0 : memberProduct.getBenefitUsage().doubleValue();
														double actualBenefitLimit = memberProduct.getActualBenefitLimit() == null ? 0.0 : memberProduct.getActualBenefitLimit().doubleValue();
														double benefitUsageActive = 0.0;
														double actualBenefitLimitActive = 0.0;

														if (memberProductActive != null) {
															benefitUsageActive = memberProductActive.getBenefitUsage() == null ? 0.0 : memberProductActive.getBenefitUsage().doubleValue();
															actualBenefitLimitActive = memberProductActive.getActualBenefitLimit() == null ? 0.0 : memberProductActive.getActualBenefitLimit().doubleValue();
														}
														if (actualBenefitLimit == -1.0) {
															if (isCurrencyConversion) {
																updateBenefitUsage(approvedValue * currencyMultiplier,memberProduct, memberProductActive,benefitUsage, benefitUsageActive,actualBenefitLimit, actualBenefitLimitActive,user);
															} else {
																updateBenefitUsage(approvedValue, memberProduct, memberProductActive, benefitUsage,benefitUsageActive, actualBenefitLimit,actualBenefitLimitActive,user);
															}
															// Add IF -adq
															if (memberProduct.getActualBenefitLimit() != null) {
																benefitRemain = memberProduct.getActualBenefitLimit();
															}
															claim.setRemainingMemberLimit(benefitRemain);
														}
													}
												} else if (memberProduct.getActualBenefitLimit() != null && memberProduct.getActualBenefitLimit().doubleValue() == 0.0) {
													calculateLayerLimit(user, claim, approvedValue,currentLayerLimit, memberProduct, benefitRemain);
												}
											} else if (sharedProductId != null && familyProductId == null) {
												annualBenefit = sharedProductId.getAnnualBenefit() == null ? -1 : sharedProductId.getAnnualBenefit();
												if (memberProductActive != null){
													annualBenefitActive = sharedProductIdActive.getAnnualBenefit() == null ? -1 : sharedProductIdActive.getAnnualBenefit();
												}
												if (sharedProductId.getActualBenefitLimit() != null && sharedProductId.getActualBenefitLimit().doubleValue() > 0) {
													if (deductPlan) {
														double benefitUsage = memberProduct.getBenefitUsage() == null ? 0.0 : memberProduct.getBenefitUsage().doubleValue();
														double actualBenefitLimit = sharedProductId.getActualBenefitLimit() == null ? 0.0 : sharedProductId.getActualBenefitLimit().doubleValue();
														double actualBenefitLimitActive = 0.0;
														double benefitUsageActive = 0.0;
														
														if (memberProductActive != null) {
															actualBenefitLimitActive = sharedProductIdActive.getActualBenefitLimit() == null ? 0.0 : sharedProductIdActive.getActualBenefitLimit().doubleValue();
															benefitUsageActive = memberProductActive.getBenefitUsage() == null ? 0.0 : memberProductActive.getBenefitUsage().doubleValue();
														}
														if (actualBenefitLimit > 0) {
															double actualBenefit = sharedProductId.getActualBenefitLimit().doubleValue();															
															if (isCurrencyConversion) {
																if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE || memberProductActive != null) {
																	updateSharedProductBenefit(approvedValue*currencyMultiplier,memberProductActive, sharedProductIdActive,benefitUsageActive,actualBenefitLimitActive,user);
																} else {
																	updateSharedProductBenefit(approvedValue*currencyMultiplier, memberProduct, sharedProductId, benefitUsage,actualBenefit,user);																	
																}
															} else {
																if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE || memberProductActive != null) {
																	updateSharedProductBenefit(approvedValue, memberProductActive, sharedProductIdActive, benefitUsageActive, actualBenefitLimitActive,user);
																} else {
																	updateSharedProductBenefit(approvedValue, memberProduct, sharedProductId, benefitUsage,actualBenefit,user);
																}
															}
															benefitRemain = sharedProductId.getActualBenefitLimit();
															claim.setRemainingMemberLimit(benefitRemain);
														}
													}
												}
												else if (sharedProductId.getActualBenefitLimit() != null && sharedProductId.getActualBenefitLimit().doubleValue() < 0) {
													//TODO: Unlimited
												}
												else {
													calculateLayerLimit(user, claim, approvedValue,currentLayerLimit, memberProduct, benefitRemain);
												}
											} else if (sharedProductId == null && familyProductId != null) {
												annualBenefit = familyProductId.getAnnualBenefit() == null ? -1 : familyProductId.getAnnualBenefit();
												if (memberProductActive != null){
													annualBenefitActive = familyProductIdActive.getAnnualBenefit() == null ? -1 : familyProductIdActive.getAnnualBenefit();
												}
												if (familyProductId.getActualBenefitLimit() != null && familyProductId.getActualBenefitLimit().doubleValue() > 0) {
													if (deductPlan) {
														double benefitUsage = memberProduct.getBenefitUsage() == null ? 0.0 : memberProduct.getBenefitUsage().doubleValue();
														double actualBenefitLimit = familyProductId.getActualBenefitLimit() == null ? 0.0 : familyProductId.getActualBenefitLimit().doubleValue();
														double actualBenefitLimitActive = 0.0;
														double benefitUsageActive = 0.0;

														if (memberProductActive != null) {
															actualBenefitLimitActive = familyProductIdActive.getActualBenefitLimit() == null ? 0.0 : familyProductIdActive.getActualBenefitLimit().doubleValue();
															benefitUsageActive = memberProductActive.getBenefitUsage() == null ? 0.0 : memberProductActive.getBenefitUsage().doubleValue();
														}
														if (actualBenefitLimit > 0) {
															if (isCurrencyConversion) {
																if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE || memberProductActive != null) {
																	updateSharedProductBenefit(approvedValue*currencyMultiplier, memberProductActive,familyProductIdActive, benefitUsageActive,actualBenefitLimitActive,user);
																} else {
																	updateSharedProductBenefit(approvedValue*currencyMultiplier, memberProduct,familyProductId, benefitUsage,actualBenefitLimit,user);
																}

															} else {
																if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE || memberProductActive != null) {
																	updateSharedProductBenefit(approvedValue,memberProductActive, familyProductIdActive,benefitUsageActive,actualBenefitLimitActive,user);
																} else {
																	updateSharedProductBenefit(approvedValue, memberProduct,familyProductId, benefitUsage,actualBenefitLimit,user);
																}
															}
															benefitRemain = familyProductId.getActualBenefitLimit();
															claim.setRemainingMemberLimit(benefitRemain);
														}
													}
												}
												else if (familyProductId.getActualBenefitLimit() != null && familyProductId.getActualBenefitLimit().doubleValue() == -1.0){
													// add adq 30102015
													double benefitUsage = memberProduct.getBenefitUsage() == null ? 0.0 : memberProduct.getBenefitUsage().doubleValue();
													double actualBenefitLimit = familyProductId.getActualBenefitLimit() == null ? 0.0 : familyProductId.getActualBenefitLimit().doubleValue();
													double benefitUsageActive = 0.0;
													double actualBenefitLimitActive = 0.0;

													if (memberProductActive != null) {
														benefitUsageActive = memberProductActive.getBenefitUsage() == null ? 0.0 : memberProductActive.getBenefitUsage().doubleValue();
														actualBenefitLimitActive = memberProductActive.getActualBenefitLimit() == null ? 0.0 : memberProductActive.getActualBenefitLimit().doubleValue();
													}
													memberProduct.setBenefitUsage(benefitUsage + approvedValue);
													if (actualBenefitLimit == -1.0) {
														if (isCurrencyConversion) {
															updateBenefitUsage(approvedValue * currencyMultiplier,memberProduct, memberProductActive, benefitUsage,benefitUsageActive, actualBenefitLimit,actualBenefitLimitActive,user);
														} else {
															updateBenefitUsage(approvedValue, memberProduct,memberProductActive, benefitUsage,benefitUsageActive, actualBenefitLimit,actualBenefitLimitActive,user);
														}
													}
													// end adq 30102015													
												}
												else if (familyProductId.getActualBenefitLimit() != null && familyProductId.getActualBenefitLimit().doubleValue() == 0.0){
													if (currentLayerLimit != null && currentLayerLimit.getFamilyLimitLayerId() != null){
														MemberLimitLayer familyLayer = memberLimitLayerService.get(currentLayerLimit.getFamilyLimitLayerId().getMemberLimitLayerId());
														if (familyLayer != null){
															calculateLayerLimit(user, claim, approvedValue,familyLayer, memberProduct, benefitRemain);
														}
													}
												}
											} else if (familyProductId != null && sharedProductId != null) {
												double actualBenefitLimit = 0.0;
												double familyPlanLimit = familyProductId.getActualBenefitLimit() == null ? 0.0 : familyProductId.getActualBenefitLimit().doubleValue();
												double planShareLimit = sharedProductId.getActualBenefitLimit() == null ? 0.0 : sharedProductId.getActualBenefitLimit().doubleValue();
												double actualBenefitLimitActive = 0.0;
												double planShareLimitActive = 0.0;
												double familyPlanLimitActive = 0.0;
												double benefitUsageActive = 0.0;

												if (memberProductActive != null) {
													benefitUsageActive = memberProductActive.getBenefitUsage() == null ? 0.0 : memberProductActive.getBenefitUsage().doubleValue();			
													planShareLimitActive = sharedProductIdActive.getActualBenefitLimit() == null ? 0.0 : sharedProductIdActive.getActualBenefitLimit().doubleValue();
													familyPlanLimitActive = familyProductIdActive.getActualBenefitLimit() == null ? 0.0 : familyProductIdActive.getActualBenefitLimit().doubleValue();												
												}
												if (familyPlanLimit > planShareLimit) {
													actualBenefitLimit = planShareLimit;
												} else {
													actualBenefitLimit = familyPlanLimit;
												}
												if (familyPlanLimitActive > planShareLimitActive) {
													actualBenefitLimitActive = planShareLimitActive;
												} else {
													actualBenefitLimitActive = familyPlanLimitActive;
												}
												if (actualBenefitLimit > 0) {
													if (deductPlan) {
														double benefitUsage = memberProduct.getBenefitUsage() == null ? 0.0 : memberProduct.getBenefitUsage().doubleValue();
														double delta = 0.0;
														if (isCurrencyConversion) {
															if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE || memberProductActive != null) {
																updateFamAndSharedBenefit(approvedValue * currencyMultiplier, memberProductActive, sharedProductIdActive, familyProductIdActive, actualBenefitLimitActive, benefitUsageActive,user);
															} else {
																updateFamAndSharedBenefit(approvedValue * currencyMultiplier,memberProduct, sharedProductId, familyProductId,actualBenefitLimit, benefitUsage,user);
															}
														} else {
															if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE || memberProductActive != null) {
																updateFamAndSharedBenefit(approvedValue,memberProductActive, sharedProductIdActive,familyProductIdActive, actualBenefitLimitActive,benefitUsageActive,user);
															} else {
																updateFamAndSharedBenefit(approvedValue, memberProduct,sharedProductId, familyProductId,actualBenefitLimit, benefitUsage,user);
															}
														}
														claim.setRemainingMemberLimit(delta);
													}
												} else if (actualBenefitLimit == 0.0){
													if (currentLayerLimit != null && currentLayerLimit.getFamilyLimitLayerId() != null){
														MemberLimitLayer familyLayer = memberLimitLayerService.get(currentLayerLimit.getFamilyLimitLayerId().getMemberLimitLayerId());
														if (familyLayer != null){
															calculateLayerLimit(user, claim, approvedValue,familyLayer, memberProduct, benefitRemain);
														}
													}													
												}
												else if (actualBenefitLimit == -1.0){
													//TODO: Update Unlimited Benefit 
												}
											}
											memberProduct.setLastClaimNumber(claim.getClaimNumber());
											memberProduct.setLastClaimDate(claim.getClaimDate());
											claim.setProductId(memberProduct);

											prePaidExcess = deductFloatingFund(user, claim, policy, prePaidExcess, claimItem, memberProduct);

											if (claimItem.getRefund() != null) {
												refund += claimItem.getRefund();
											}
											double ex = claimItem.getExcessValue() == null ? 0.0 : claimItem.getExcessValue();
											if (claimItem.getPaidToProvider() != null) {
												paidToProvider += claimItem.getPaidToProvider();
											}
											claimItemService.update(claimItem, user);
											
										} else if (memberProduct != null && smmLimit != null) {

											excessPaymentType = memberProduct.getProductId().getExcessPaymentType().intValue();
											benefitRemain = smmLimit.getActualBenefitLimit();

											double annualBenefit = smmLimit.getAnnualLimit() == null ? -1 : smmLimit.getAnnualLimit();
											claim.setProductId(memberProduct);
											claim.setRemainingMemberLimit(benefitRemain);
											claim.setSmmLayerId(smmLimit);
											claim.setIsUsingSMM(1);
											double claimUsage = smmLimit.getBenefitUsage() == null ? 0.0 : smmLimit.getBenefitUsage().doubleValue();
											
											if (smmLimit.getDeductible() != null) {
												double smmDeductible = smmLimit.getDeductible();
												if (smmDeductible > claimApprovedValue) {
													claimApprovedValue = 0.0;
												}
											}

											double smmRemaining = benefitRemain - claimItem.getClaimItemApprovedValue();
											claimUsage = claimUsage + claimItem.getClaimItemApprovedValue();

											smmLimit.setActualBenefitLimit(smmRemaining);
											smmLimit.setBenefitUsage(claimUsage);
											claim.setRemainingMemberLimit(smmRemaining);

											memberLimitLayerService.update(smmLimit, user);

											prePaidExcess = deductFloatingFund(user, claim, policy, prePaidExcess,claimItem, memberProduct);

											if (claimItem.getRefund() != null) {
												refund += claimItem.getRefund();
											}
											double ex = claimItem.getExcessValue() == null ? 0.0 : claimItem.getExcessValue();

											if (claimItem.getPaidToProvider() != null) {
												paidToProvider += claimItem.getPaidToProvider();
											}
											claimItemService.update(claimItem, user);
										}
									}
								}
							}
						}
					}
				}

				if (excess > 0) {

					System.out.println("ADA EXCESS DISINI !!!! COBA CREATE DONG ENTAH DARI EDC ATAU OPERATOR!");

					ExcessCharge excessCharge = new ExcessCharge();
					excessCharge.setClaimId(claim);
					excessCharge.setExcessChargeValue(excess);
					excessCharge.setExcessChargeNumber("" + System.currentTimeMillis());
					excessCharge.setReminderCounter(0);
					excessCharge.setDeletedStatus(0);
					excessCharge.setExcessChargeTime(new java.sql.Date(System.currentTimeMillis()));
					excessCharge.setMemberId(claim.getMemberId());
					excessCharge.setRemarks(excessRemarks);

					PaymentStatus paymentStatus = new PaymentStatus();
					if (claim.getTipe().intValue() == Claim.CLAIM_SWIPE) {
						System.out.println("Excess Payment Type : " + excessPaymentType);
						if (excessPaymentType == 2) {
							paymentStatus.setPaymentStatusId(PaymentStatus.PAYMENT_OPEN);
						} else if (excessPaymentType == 1) {
							paymentStatus.setPaymentStatusId(PaymentStatus.PAYMENT_PAID);
						} else {
							paymentStatus.setPaymentStatusId(PaymentStatus.PAYMENT_OPEN);
						}
					} else {
						paymentStatus.setPaymentStatusId(PaymentStatus.PAYMENT_OPEN);
					}

					excessCharge.setExcessChargeStatus(paymentStatus);

					ExcessCharge excessCharges = excessChargeService.create(excessCharge, user);

					System.out.println("EXCESS CREATED !!! JUMLAH EXCESS : " + excess);

					if (claimItems != null && claimItems.size() > 0) {
						result = claimItems.size() + "";
						Iterator<ClaimItem> iterator = claimItems.iterator();

						ExcessChargeItem excessItem = null;

						while (iterator.hasNext()) {
							ClaimItem chk = iterator.next();
							ClaimItem claimItem = claimItemService.get(chk.getClaimItemId());
							if (chk != null) {
								if (chk.getExcessValue().doubleValue() > 0) {

									excessItem = new ExcessChargeItem();
									excessItem.setExcessChargeId(excessCharges);
									excessItem.setItemId(chk.getItemId());
									excessItem.setValue(claimItem.getExcessValue());
									excessItem.setDeletedStatus(0);

									ExcessChargeItem excessItems = excessChargeItemService.create(excessItem, user);
									System.out.println("CREATED EXCESS ITEM : " + claimItem.getExcessValue());
								}
							}
						}
					}
				}

				Collection<ClaimItem> checkableItems = claimItemService.getBenefitCheckItem(claim);

				if (checkableItems != null && checkableItems.size() > 0) {
					result = checkableItems.size() + "";
				} else {
					result = "OK";

					if (claim != null) {
						CaseStatus status = new CaseStatus();
						status.setCaseStatusId(Claim.CLAIM_CHECKED);

						claim.setClaimStatus(status);
						claim.setClaimApprovedValue(claimApprovedValue);
						claim.setClaimExcessValue(excess);
						if (claim.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURESEMENT) {
							claim.setPrePaidExcess(0.0);
							claim.setPembayaranDimuka(0.0);
						} else {
							claim.setPrePaidExcess(prePaidExcess);
							claim.setPembayaranDimuka(prePaidExcess);
						}
						claim.setRefund(refund);
						claim.setPaidToProvider(paidToProvider);

						claim.setProductCurrencyApprovedValue(claimApprovedValue * currencyMultiplier);
						claim.setProductCurrencyExcessValue(excess * currencyMultiplier);

						claim.setApprovedTime(new java.sql.Timestamp(System.currentTimeMillis()));
						claim.setBenefitCheckedTime(new java.sql.Timestamp(System.currentTimeMillis()));

						if (claim.getBatchClaimId() != null) {

							boolean isPaymentIssued = batchClaimService
									.isBatchPaymentIssued(claim.getBatchClaimId().getBatchClaimId());

							if (!isPaymentIssued) {
								boolean completeBatch = batchClaimService
										.isBatchChecked(claim.getBatchClaimId().getBatchClaimId());

								if (completeBatch) {
									batchClaimService.closeBatch(claim.getBatchClaimId().getBatchClaimId(), user);
								}
								update(claim, user);
							} else {
								status.setCaseStatusId(Claim.CLAIM_PAYMENT_ISSUED);
								update(claim, user);
								batchClaimService.recalculateBatch(claim.getBatchClaimId().getBatchClaimId(), user);
							}
						}

						recordHistory(claim, "CHECKED CLAIM", "", user);

						// jika ASO .. maka langsung kurangin dana ASO

						if (policy != null && policy.getIsUsingFloatingFund() != null) {
							System.out.println("BENEFIT ASO MENGURANGI CLIENT ASO  !!");
							String[] eqPolicyCoverageParam = { "policyId.policyId", "caseCategoryId.caseCategoryId","deletedStatus" };
							Object[] eqPolicyCoverageValue = { policy.getPolicyId(), claim.getCaseCategoryId().getCaseCategoryId(), 0 };

							Collection<PolicyCoverageFund> fundList = policyCoverageFundService.search(null, null,
									eqPolicyCoverageParam, eqPolicyCoverageValue, 0, 1);

							if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE) {
								Iterator<PolicyCoverageFund> fundIterator = fundList.iterator();

								if (fundIterator.hasNext()) {
									PolicyCoverageFund fund = fundIterator.next();
									
									if (fund != null && fund.getCurrentFundValue() != null) {
										double currentPolicyFund = fund.getCurrentFundValue().doubleValue();
										System.out.println("BENEFIT ASO MENGURANGI CLIENT ASO  PER COVERAGE =  !!" + currentPolicyFund);
										
										if (claim.getClaimApprovedValue() > currentPolicyFund) {
											fund.setCurrentFundValue(0.0);
										} else {
											fund.setCurrentFundValue(currentPolicyFund - claim.getClaimApprovedValue());
										}
										fund = policyCoverageFundService.update(fund, user);

										/**
										 * check ASO Fund Notification
										 */
										int warningType = 0;
										boolean doSendWarning = false;

										if (fund != null) {

											if (fund.getCurrentFundValue() <= fund.getWarningAsoValue() && fund.getCurrentFundValue() > fund.getMinimumAsoValue()) {
												warningType = 1;
											} else if (fund.getCurrentFundValue() <= fund.getMinimumAsoValue()) {
												warningType = 2;
											}

											if (fund.getExcessFundValue() != null && fund.getWarningExcessValue() != null && fund.getMinimumExcessValue() != null) {												
												if (fund.getExcessFundValue() <= fund.getWarningExcessValue() && fund.getExcessFundValue() > fund.getMinimumExcessValue()) {
													warningType = 3;
												} else if (fund.getExcessFundValue() <= fund.getMinimumExcessValue()) {
													warningType = 4;
												}
											}

											if (policy.getLastFundWarningDate() == null) {
												doSendWarning = true;
											} else {
												if (policy.getLastFundWarningDate().before(new java.sql.Date(System.currentTimeMillis()))) {
													doSendWarning = true;
												}
											}

											if (client != null && doSendWarning && warningType > 0) {
												generatePerCoverageNotification(user, policy, client, fund, warningType);
											}
										}
									}
								}
							} else if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY) {

								double currentExcessDeposit = policy.getCurrentExcessFund() == null ? 0.0 : policy.getCurrentExcessFund();
								double claimExcessValue = claim.getClaimExcessValue() == null ? 0.0 : claim.getClaimExcessValue();

								if (claim.getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS && claimExcessValue > 0) {
									if (currentExcessDeposit < claim.getClaimExcessValue()) {

									}
								}

								if (policy.getCurrentPolicyFund() != null && policy.getCurrentPolicyFund() > 0) {

									double currentPolicyFund = policy.getCurrentPolicyFund().doubleValue();

									System.out.println("BENEFIT ASO MENGURANGI CLIENT ASO  GLOBAL COVERAGE =  !!"
											+ currentPolicyFund);

									if (claim.getClaimApprovedValue() > currentPolicyFund) {
										policy.setCurrentPolicyFund(0.0);

										if (policy.getPolicyFundUsage() != null) {
											double currentUsage = policy.getPolicyFundUsage();
											policy.setPolicyFundUsage(currentUsage + currentPolicyFund);
										}
									} else {
										policy.setCurrentPolicyFund(currentPolicyFund - claim.getClaimApprovedValue());

										if (policy.getPolicyFundUsage() != null) {
											double currentUsage = policy.getPolicyFundUsage();
											policy.setPolicyFundUsage(currentUsage + claim.getClaimApprovedValue());
										}
									}

									policyService.update(policy, user);

									/**
									 * check ASO Fund Notification
									 */
									double currentFund = policy.getCurrentPolicyFund();
									double fundBlockLimit = policy.getFundBlockingLimit() == null ? 0.0 : policy.getFundBlockingLimit();
									double fundWarningLimit = policy.getMinimumPolicyFund() == null ? 0.0 : policy.getMinimumPolicyFund();
									double currentExcessFund = policy.getCurrentExcessFund() == null ? 0.0 : policy.getCurrentExcessFund();
									double excessBlockLimit = policy.getExcessBlockingLimit() == null ? 0.0 : policy.getExcessBlockingLimit();
									double excessWarningLimit = policy.getMinimumExcessFund() == null ? 0.0: policy.getMinimumExcessFund();
									
									int warningType = 0;
									boolean doSendWarning = false;

									if (currentFund <= fundWarningLimit && currentFund > fundBlockLimit) {
										warningType = 1;
									} else if (currentFund <= fundBlockLimit) {
										warningType = 2;
									} else if (currentExcessFund <= excessWarningLimit
											&& currentExcessFund > excessBlockLimit) {
										warningType = 3;
									} else if (currentExcessFund <= excessBlockLimit) {
										warningType = 4;
									}
									if (policy.getLastFundWarningDate() == null) {
										doSendWarning = true;
									} else {
										if (policy.getLastFundWarningDate().before(new java.sql.Date(System.currentTimeMillis()))) {
											doSendWarning = true;
										}
									}

									if (client != null && doSendWarning && warningType > 0) {

										generatePolicyFundNotification(user, policy, client, warningType);
									}
								}
							}
						}

					} else {
						throw new Exception("rollback data");
					}
				}
			}
		}

		return result;
	}// end tanda 1

	private double deductFloatingFund(ActionUser user, Claim claim, Policy policy, double prePaidExcess,
			ClaimItem claimItem, MemberProduct memberProduct) throws Exception {
		if (memberProduct.getExcessPaymentType() != null && memberProduct.getExcessPaymentType().intValue() == Product.EXCESS_PAID_UPFRONT) {
			if (claimItem.getClaimId().getClaimTypeId().getClaimTypeId().intValue() == ClaimType.CASHLESS) {
				if (claimItem.getClaimItemApprovedValue() < claimItem.getClaimItemValue()) {
					if (claimItem.getPrePaidExcess() == null) {
						double prepaidExcess = claimItem.getClaimItemValue() - claimItem.getClaimItemApprovedValue();
						prePaidExcess += prepaidExcess;
						claimItem.setPrePaidExcess(prepaidExcess);
					}
				}
			}
		} else {

			if (policy.getIsUsingFloatingFund() != null) {
				if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY	&& policy.getCurrentExcessFund() != null) {
					if (policy.getExcessBlockingLimit() != null) {
						if (claimItem.getClaimItemApprovedValue() < claimItem.getClaimItemValue()) {
							if (policy.getCurrentExcessFund() >= policy.getExcessBlockingLimit()) {
								double prepaidExcess = updatePolicyExcessFund(user,policy, claimItem);
								prePaidExcess += prepaidExcess;
								claimItem.setPrePaidExcess(prepaidExcess);
							}
						}
					}
				} else if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE) {
					if (claimItem.getClaimItemApprovedValue() < claimItem.getClaimItemValue()) {															
						PolicyCoverageFund coverageFund = policyCoverageFundService.getPolicyFundByCategory(policy.getPolicyId(),claim.getCaseCategoryId().getCaseCategoryId());
						
						if (coverageFund != null && coverageFund.getExcessFundValue() != null) {
							if (coverageFund.getMinimumExcessValue() != null) {
								if (coverageFund.getExcessFundValue() >= coverageFund.getMinimumExcessValue()) {
									double prepaidExcess = updatePolicyCoverageExcessFund(user, claimItem, coverageFund);
									prePaidExcess += prepaidExcess;
									claimItem.setPrePaidExcess(prepaidExcess);
								}
							}
						}
					}
				}
			}
		}
		return prePaidExcess;
	}

	private void generatePolicyFundNotification(ActionUser user, Policy policy, Client client, int warningType)
			throws Exception {
		String[] eqParam = { "clientId.clientId", "deletedStatus", "paymentOfficer" };
		Object[] eqValue = { client.getClientId(), Integer.valueOf(0), 1 };
		int totalContact = contactPersonService.getTotal(null, null, eqParam, eqValue);
		Collection<ContactPerson> contactList = contactPersonService.search(null, null,eqParam, eqValue, 0, totalContact);

		for (Iterator iterator2 = contactList.iterator(); iterator2.hasNext();) {

			ContactPerson contactPerson = (ContactPerson) iterator2.next();

			if (contactPerson != null) {

				String destination = contactPerson.getEmail();
				MessageTemplate template = new MessageTemplate();
				Notification notification = new Notification();

				
				if (warningType == 1) {
					template.setId(MessageTemplate.POLICY_FUND_WARNING_EMAIL);
					notification.setMessageType(Notification.ASO_FUND_WARNING_NOTIFICATION_EMAIL);
				} else if (warningType == 2) {
					template.setId(MessageTemplate.POLICY_FUND_BLOCK_EMAIL);
					notification.setMessageType(
							Notification.ASO_FUND_BLOCK_NOTIFICATION_EMAIL);
				} else if (warningType == 3) {
					template.setId(MessageTemplate.POLICY_EXCESS_WARNING_EMAIL);
					notification.setMessageType(
							Notification.EXCESS_FUND_WARNING_NOTIFICATION_EMAIL);
				} else if (warningType == 4) {
					template.setId(MessageTemplate.POLICY_EXCESS_BLOCK_EMAIL);
					notification.setMessageType(
							Notification.EXCESS_FUND_BLOCK_NOTIFICATION_EMAIL);
				}

				notification.setClientId(client);
				notification.setContactPersonId(contactPerson);
				notification.setPolicyId(policy);

				notification.setTemplateId(template);
				notification.setDestination(destination);
				notification.setStatus(0);

				notification.setSender("system-daemon");

				notificationService.create(notification, user);
			}
		}
		policy.setLastFundWarningDate(new java.sql.Date(System.currentTimeMillis()));
		policyService.update(policy, user);
	}

	private void generatePerCoverageNotification(ActionUser user, Policy policy, Client client, PolicyCoverageFund fund,
			int warningType) throws Exception {
		

		String[] eqParam = { "clientId.clientId", "deletedStatus", "paymentOfficer" };
		Object[] eqValue = { client.getClientId(), Integer.valueOf(0), 1 };
		int totalContact = contactPersonService.getTotal(null, null, eqParam,eqValue);
		Collection<ContactPerson> contactList = contactPersonService.search(null, null, eqParam, eqValue, 0, totalContact);

		for (Iterator iterator2 = contactList.iterator(); iterator2.hasNext();) {
			ContactPerson contactPerson = (ContactPerson) iterator2.next();

			if (contactPerson != null) {

				String destination = contactPerson.getEmail();
				MessageTemplate template = new MessageTemplate();
				Notification notification = new Notification();

				if (warningType == 1) {
					template.setId(MessageTemplate.POLICY_FUND_COV_WARNING_EMAIL);
					notification.setMessageType(Notification.ASO_FUND_WARNING_NOTIFICATION_EMAIL);
				} else if (warningType == 2) {
					template.setId(MessageTemplate.POLICY_FUND_COV_BLOCK_EMAIL);
					notification.setMessageType(Notification.ASO_FUND_BLOCK_NOTIFICATION_EMAIL);
				} else if (warningType == 3) {
					template.setId(MessageTemplate.POLICY_EXCESS_COV_WARNING_EMAIL);
					notification.setMessageType(Notification.EXCESS_FUND_WARNING_NOTIFICATION_EMAIL);
				} else if (warningType == 4) {
					template.setId(MessageTemplate.POLICY_EXCESS_COV_BLOCK_EMAIL);
					notification.setMessageType(Notification.EXCESS_FUND_BLOCK_NOTIFICATION_EMAIL);
				}

				notification.setClientId(client);
				notification.setContactPersonId(contactPerson);
				notification.setPolicyId(policy);
				notification.setPolicyCoverageFund(fund);
				notification.setTemplateId(template);
				notification.setDestination(destination);
				notification.setStatus(0);

				notification.setSender("system-daemon");

				notificationService.create(notification, user);


			}
		}
		policy.setLastFundWarningDate(new java.sql.Date(System.currentTimeMillis()));
		policyService.update(policy, user);

		
	}

	private double updateFamAndSharedBenefit(double approvedValue, MemberProduct memberProduct,
			MemberProduct sharedProductId, MemberProduct familyProductId, double actualBenefitLimit,
			double benefitUsage,ActionUser user) throws Exception {
		double delta;
		delta = actualBenefitLimit - approvedValue;

		double annualBenefit = familyProductId.getAnnualBenefit() == null ? 0.0 : familyProductId.getAnnualBenefit();
		

		memberProduct.setBenefitUsage(benefitUsage + approvedValue);

		if (annualBenefit > 0 ){
			double percentage = ((annualBenefit-delta)/ annualBenefit)*100;
			memberProduct.setBenefitUsagePercentage(percentage);

			familyProductId.setActualBenefitLimit(delta);
			sharedProductId.setActualBenefitLimit(delta);
			memberProduct.setActualBenefitLimit(delta);
			
			
		}
		
		memberProductService.update(memberProduct, user);
		memberProductService.update(familyProductId, user);
		memberProductService.update(sharedProductId, user);
		
		return delta;
	}

	

	private void updateBenefitUsage(double approvedValue, MemberProduct memberProduct,
			MemberProduct memberProductActive, double benefitUsage, double benefitUsageActive,
			double actualBenefitLimit, double actualBenefitLimitActive,ActionUser user) throws Exception {
		if (memberProduct.getMemberProductStatus().getStatusId() != SubscriptionStatus.ACTIVE
				|| memberProductActive != null) {

			memberProductActive.setBenefitUsage(benefitUsageActive + approvedValue);

			if (actualBenefitLimit > 0) {

				memberProductActive.setActualBenefitLimit(actualBenefitLimitActive - approvedValue);
				double percentage = ((benefitUsageActive + approvedValue) / memberProductActive.getAnnualBenefit()) * 100;
				
				memberProductActive.setBenefitUsagePercentage(percentage);
			}
			
			memberProductService.update(memberProductActive, user);
		} else {
			memberProduct.setBenefitUsage(benefitUsage + approvedValue);

			if (actualBenefitLimit > 0) {

				memberProduct.setActualBenefitLimit(actualBenefitLimit - approvedValue);
				
				double percentage = ((benefitUsage + approvedValue) / memberProduct.getAnnualBenefit()) * 100;
				
				memberProduct.setBenefitUsagePercentage(percentage);
			}
			
			memberProductService.update(memberProduct, user);
		}
	}

	private double updatePolicyCoverageExcessFund(ActionUser user, ClaimItem claimItem, PolicyCoverageFund coverageFund)
			throws Exception {
		double availableExcessFund = coverageFund.getExcessFundValue() - coverageFund.getMinimumExcessValue();

		double excessValue = claimItem.getClaimItemValue() - claimItem.getClaimItemApprovedValue();
		double prepaidExcess = 0.0;

		if (availableExcessFund >= excessValue) {
			prepaidExcess = excessValue;
		} else {
			prepaidExcess = availableExcessFund;
		}

		coverageFund.setExcessFundValue(coverageFund.getExcessFundValue() - prepaidExcess);

		policyCoverageFundService.update(coverageFund, user);
		return prepaidExcess;
	}

	private double updatePolicyExcessFund(ActionUser user, Policy policy, ClaimItem claimItem) throws Exception {
		double availableExcessFund = policy.getCurrentExcessFund() - policy.getExcessBlockingLimit();

		double excessValue = claimItem.getClaimItemValue() - claimItem.getClaimItemApprovedValue();
		double prepaidExcess = 0.0;

		if (availableExcessFund >= excessValue) {
			prepaidExcess = excessValue;
		} else {
			prepaidExcess = availableExcessFund;
		}

		policy.setCurrentExcessFund(policy.getCurrentExcessFund() - prepaidExcess);

		policyService.update(policy, user);
		return prepaidExcess;
	}



	

	private void updateSharedProductBenefit(double approvedValue, MemberProduct memberProduct, MemberProduct sharedProductId,
			double benefitUsage, double actualBenefit, ActionUser user) throws Exception {
		
		double delta = actualBenefit - approvedValue;
		sharedProductId.setActualBenefitLimit(delta);
		
		
		memberProduct.setActualBenefitLimit(delta);
		memberProduct.setBenefitUsage(benefitUsage + approvedValue);
		
		double sharedAnnualBenefit = sharedProductId == null ? 0.0 : sharedProductId.getAnnualBenefit();
		
		double annualBenefit = sharedProductId.getAnnualBenefit() == null ? 0.0 : sharedProductId.getAnnualBenefit();
		
		if (annualBenefit > 0) {
			double percentage = ((benefitUsage + approvedValue) / annualBenefit) * 100;
			memberProduct.setBenefitUsagePercentage(percentage);
		}
		
		if (sharedAnnualBenefit > 0){
			double percentage = ((sharedAnnualBenefit - delta) / annualBenefit) * 100;
			sharedProductId.setBenefitUsagePercentage(percentage);
		}
		
		memberProductService.update(memberProduct, user);
		memberProductService.update(sharedProductId, user);
		
	}

	private MemberLimitLayer updateSMMClaim(Claim claim, Member member) throws Exception {
		MemberLimitLayer smmLimit = null;

		MemberProduct memberProduct = claim.getProductId();

		if (memberProduct != null) {
			memberProduct = memberProductService.get(memberProduct.getMemberProductId());

			Integer memberId = null;
			Integer diagnosisId = null;

			if (memberProduct.getProductId().getProductType().getProductTypeId().intValue() == ProductType.FAMILY
					|| memberProduct.getProductId().getProductType().getProductTypeId()
							.intValue() == ProductType.GROUP_FAMILY) {

				memberId = member.getParentMember().getMemberId();
			} else {
				memberId = member.getMemberId();
			}

			if (claim.getDiagnosisId() != null) {
				smmLimit = memberLimitLayerService.getSMMLayer(memberId, claim.getDiagnosisId().getDiagnosisId(),
						claim.getCaseCategoryId().getCaseCategoryId());
			}
			if (smmLimit == null || (smmLimit.getActualBenefitLimit() != null
					&& smmLimit.getActualBenefitLimit().doubleValue() == 0)) {
				if (claim.getDiagnosis2Id() != null) {
					smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(),
							claim.getDiagnosis2Id().getDiagnosisId(), claim.getCaseCategoryId().getCaseCategoryId());
				}
			}
			if (smmLimit == null || (smmLimit.getActualBenefitLimit() != null
					&& smmLimit.getActualBenefitLimit().doubleValue() == 0)) {
				if (claim.getDiagnosis3Id() != null) {
					smmLimit = memberLimitLayerService.getSMMLayer(member.getMemberId(),
							claim.getDiagnosis3Id().getDiagnosisId(), claim.getCaseCategoryId().getCaseCategoryId());
				}
			}

		}

		if (smmLimit != null && smmLimit.getActualBenefitLimit() != null && smmLimit.getActualBenefitLimit() > 0) {
			claim.setSmmLayerId(smmLimit);
			claim.setIsUsingSMM(1);
		}
		return smmLimit;
	}

	private void calculateLayerLimit(ActionUser user, Claim claim, double claimApprovedValue,
			MemberLimitLayer currentLayerLimit, MemberProduct memberProduct, double benefitRemain) throws Exception {

		if (currentLayerLimit != null) {
			double annualBenefit = currentLayerLimit.getAnnualLimit() == null ? -1 : currentLayerLimit.getAnnualLimit();

			claim.setProductId(memberProduct);

			claim.setMultiLayerId(currentLayerLimit);
			claim.setIsUsingMultiLayerLimit(1);

			double claimUsage = currentLayerLimit.getBenefitUsage() == null ? 0.0
					: currentLayerLimit.getBenefitUsage().doubleValue();

			if (annualBenefit > 0) {
				benefitRemain = annualBenefit - claimUsage;
			}
			if (currentLayerLimit.getDeductible() != null) {
				double multiLayerDeductible = currentLayerLimit.getDeductible();
				if (multiLayerDeductible > claimApprovedValue) {
					claimApprovedValue = 0.0;
				}
			}

			double mlRemaining = benefitRemain - claimApprovedValue;
			claimUsage = claimUsage + claimApprovedValue;

			currentLayerLimit.setActualBenefitLimit(mlRemaining);

			currentLayerLimit.setBenefitUsage(claimUsage);
			claim.setRemainingMemberLimit(mlRemaining);

			memberLimitLayerService.update(currentLayerLimit, user);
		}

	}

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, Double minimumClaim) throws Exception {
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

		if (minimumClaim != null) {
			DaoSupportUtil.setGreaterThanEqual(minimumClaim, "claimChargeValue", c);
		}

		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;
	}

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String betweenParam, Object minBetween, Object maxBetween, String[] required, Double minimumClaim)
					throws Exception {
		// TODO Auto-generated method stub
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(betweenParam, minBetween, maxBetween, c);

		if (minimumClaim != null) {
			DaoSupportUtil.setGreaterThanEqual(minimumClaim, "claimChargeValue", c);
		}
		List list = c.list();

		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;
	}

	public Collection<PaymentReportDetail> getBatchClaimDetail(Integer batchClaimId) throws Exception {
		// TODO Auto-generated method stub

		Collection<PaymentReportDetail> reports = new Vector<PaymentReportDetail>();
		String required[] = { "Claim.ClaimItems", "Claim.MemberId" };
		String searchLike[] = { "batchClaimId.batchClaimId", "deletedStatus" };
		Object objectLike[] = { batchClaimId, Integer.valueOf(0) };

		int totalClaim = getTotal(null, null, searchLike, objectLike);
		// Collection<Claim> claims = search(null, null,
		// "batchClaimId.batchClaimId", batchClaimId,required,0,100);
		Collection<Claim> claims = search(null, null, searchLike, objectLike, required, 0, totalClaim);
		if (claims != null) {
			Iterator<Claim> claimIterator = claims.iterator();

			Claim claim = null;

			Collection<ClaimItem> claimItems = null;
			Set<ClaimItem> ciSet = null;

			String[] requiredCI = { "ClaimItem.ItemId" };
			if (claimIterator != null) {
				while (claimIterator.hasNext()) {
					claim = claimIterator.next();

					if (claim != null) {
						PaymentReportDetail report = new PaymentReportDetail();
						report.setClaim(claim);

						ciSet = claim.getClaimItems();

						if (ciSet != null && ciSet.size() > 0) {
							claimItems = new Vector();

							Iterator<ClaimItem> ciIterator = ciSet.iterator();

							if (ciIterator != null) {
								while (ciIterator.hasNext()) {

									ClaimItem ci = ciIterator.next();

									if (ci != null && ci.getDeletedStatus() != null
											&& ci.getDeletedStatus().intValue() == 0) {
										DaoSupportUtil.lazyInit(requiredCI, ci);
										claimItems.add(ci);
									}
								}
							}
						}

						report.setClaimItems(claimItems);
						reports.add(report);
					}

				}
			}

		}

		return reports;
	}

	public double getTotalBenefitUsage(MemberGroup memberGroup, Date startDate, Date endDate) throws Exception {
		double result = 0;

		try {

			if (memberGroup != null) {
				Session session = claimDao.getClaimSession();

				Query query = session.createQuery(
						"SELECT sum(claim.claimApprovedValue) As total FROM Claim claim WHERE claim.memberId.memberGroupId.memberGroupId = :memberGroupId AND (claim.claimStatus.caseStatusId = :claimStatus"
								+ " OR claim.claimStatus.caseStatusId = :claimStatusIssued)"
								+ " AND claim.claimDate >= :startDate AND claim.claimDate <= :endDate  AND claim.deletedStatus = 0");

				query.setInteger("memberGroupId", memberGroup.getMemberGroupId());
				query.setInteger("claimStatus", Claim.CLAIM_PAID);
				query.setInteger("claimStatusIssued", Claim.CLAIM_PAYMENT_ISSUED);
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);

				Object res = (Object) query.uniqueResult();

				if (res != null) {
					result = (Double) res;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// Add by adq on 29092015
	public double getTotalUsageValue(String memberNumber, Date startDate, Date endDate) throws Exception {
		// TODO Auto-generated method stub
		double result = 0.0;

		Date due = new java.sql.Date(System.currentTimeMillis());

		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				// String sqlQ =
				// "select count(c.claim_id) FROM claim c where c.member_number
				// = "+status+" and c.claim_date = now() ";

				String sqlQ = "select sum(claim_approved_value) FROM claim c where c.member_number like '"
						+ memberNumber + "%' AND c.claim_date >= '" + startDate + "' AND c.claim_date <= '" + endDate
						+ "' AND c.deleted_status = 0 ";

				SQLQuery q = session.createSQLQuery(sqlQ);

				Number num = (Number) q.uniqueResult();
				result = num.doubleValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// end add adq 29092015

	public double getTotalBenefitUsage(Member member, Date startDate, Date endDate) throws Exception {
		double result = 0;

		try {

			if (member != null) {
				Session session = claimDao.getClaimSession();

				Query query = session.createQuery("SELECT sum(claim.claimApprovedValue) FROM Claim claim "
						+ "WHERE claim.memberId.memberId = :memberId AND claim.claimStatus.caseStatusId = :claimStatus"
						+ " AND claim.claimDate >= :startDate AND claim.claimDate <= :endDate AND claim.deletedStatus = 0");

				query.setInteger("memberId", member.getMemberId());
				query.setInteger("claimStatus", Claim.CLAIM_PAID);
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);

				Object res = (Object) query.uniqueResult();

				if (res != null) {
					result = (Double) res;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public double getTotalBenefitUsage(Member member, Integer caseCategoryId, Date startDate, Date endDate)
			throws Exception {
		double result = 0;

		try {

			if (member != null) {
				Session session = claimDao.getClaimSession();

				Query query = session.createQuery("SELECT sum(claim.claimApprovedValue) FROM Claim claim "
						+ "WHERE claim.memberId.memberId = :memberId AND (claim.claimStatus.caseStatusId = :claimStatus"
						+ "  OR claim.claimStatus.caseStatusId = :paidStatus "
						+ " OR claim.claimStatus.caseStatusId = :checkedStatus) AND claim.claimDate >= :startDate "
						+ " AND claim.claimDate <= :endDate AND claim.deletedStatus = 0");

				query.setInteger("memberId", member.getMemberId());
				query.setInteger("claimStatus", Claim.CLAIM_PAYMENT_ISSUED);
				query.setInteger("paidStatus", Claim.CLAIM_PAID);
				query.setInteger("checkedStatus", Claim.CLAIM_CHECKED);
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);

				Object res = (Object) query.uniqueResult();

				if (res != null) {
					result = (Double) res;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public double getTotalBenefitUsage(Provider provider, Date startDate, Date endDate) throws Exception {
		double result = 0;

		try {
			if (provider != null) {
				Session session = claimDao.getClaimSession();

				Query query = session.createQuery(
						"SELECT sum(claim.claimApprovedValue) FROM Claim claim WHERE claim.providerId.providerId = :provider AND claim.claimStatus.caseStatusId = :claimStatus"
								+ " AND claim.claimDate >= :startDate AND claim.claimDate <= :endDate AND claim.deletedStatus = 0");

				query.setInteger("provider", provider.getProviderId());
				query.setInteger("claimStatus", Claim.CLAIM_PAID);
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);

				Object res = (Object) query.uniqueResult();

				if (res != null) {
					result = (Double) res;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// class+

	public Claim completeClaim(Serializable claimId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		Claim claim = get(claimId);

		if (claim != null) {
			boolean isClausulExcluded = false;

			if (claim.getDiagnosisId() != null) {
				String[] eqParam = { "memberId.memberId", "decision", "diagnosisId.diagnosisId",
						"caseCategoryId.caseCategoryId", "deletedStatus" };
				Object[] eqValue = { claim.getMemberId().getMemberId(), Integer.valueOf(0),
						claim.getDiagnosisId().getDiagnosisId(), claim.getCaseCategoryId().getCaseCategoryId(),
						Integer.valueOf(0) };

				int total = memberClausulService.getTotal(null, null, eqParam, eqValue);
				if (total > 0) {
					isClausulExcluded = true;
					Collection<MemberClausul> clausulList = memberClausulService.search(null, null, eqParam, eqValue, 0,
							1);
					if (clausulList != null) {
						Iterator<MemberClausul> clausulIterator = clausulList.iterator();
						if (clausulIterator.hasNext()) {
							MemberClausul clausul = clausulIterator.next();
							if (clausul != null) {
								claim.setClausulReference(clausul.getClausulId().getClausulName());
							}
						}
					}
				}
			}
			if (claim.getDiagnosis2Id() != null) {
				String[] eqParam = { "memberId.memberId", "decision", "diagnosisId.diagnosisId",
						"caseCategoryId.caseCategoryId", "deletedStatus" };
				Object[] eqValue = { claim.getMemberId().getMemberId(), Integer.valueOf(0),
						claim.getDiagnosis2Id().getDiagnosisId(), claim.getCaseCategoryId().getCaseCategoryId(),
						Integer.valueOf(0) };

				int total = memberClausulService.getTotal(null, null, eqParam, eqValue);
				if (total > 0) {
					isClausulExcluded = true;
					Collection<MemberClausul> clausulList = memberClausulService.search(null, null, eqParam, eqValue, 0,
							1);
					if (clausulList != null) {
						Iterator<MemberClausul> clausulIterator = clausulList.iterator();
						if (clausulIterator.hasNext()) {
							MemberClausul clausul = clausulIterator.next();
							if (clausul != null) {
								claim.setClausulReference(clausul.getClausulId().getClausulName());
							}
						}
					}
				}
			}
			if (claim.getDiagnosis3Id() != null) {
				String[] eqParam = { "memberId.memberId", "decision", "diagnosisId.diagnosisId",
						"caseCategoryId.caseCategoryId", "deletedStatus" };
				Object[] eqValue = { claim.getMemberId().getMemberId(), Integer.valueOf(0),
						claim.getDiagnosis3Id().getDiagnosisId(), claim.getCaseCategoryId().getCaseCategoryId(),
						Integer.valueOf(0) };

				int total = memberClausulService.getTotal(null, null, eqParam, eqValue);
				if (total > 0) {
					isClausulExcluded = true;
					Collection<MemberClausul> clausulList = memberClausulService.search(null, null, eqParam, eqValue, 0,
							1);
					if (clausulList != null) {
						Iterator<MemberClausul> clausulIterator = clausulList.iterator();
						if (clausulIterator.hasNext()) {
							MemberClausul clausul = clausulIterator.next();
							if (clausul != null) {
								claim.setClausulReference(clausul.getClausulId().getClausulName());
							}
						}
					}
				}
			}

			if (!isClausulExcluded) {
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(Claim.CLAIM_VERIFIED);
				claim.setClaimStatus(status);

				update(claim, actionUser);

				Collection<ClaimItem> items = claimItemService.getClaimItem((Integer) claimId);

				if (items != null) {
					Iterator<ClaimItem> itemIterator = items.iterator();
					CaseStatus caseStatus = new CaseStatus();
					caseStatus.setCaseStatusId(Claim.CLAIM_APPROVED);

					while (itemIterator.hasNext()) {
						ClaimItem item = itemIterator.next();

						if (item != null) {
							item.setClaimItemStatus(caseStatus);
							claimItemService.update(item, actionUser);
						}
					}
				}
			} else {
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(Claim.CLAIM_PROCESS_OK);
				claim.setClaimStatus(status);
				claim.setIsClausulExcluded(Integer.valueOf(1));

				update(claim, actionUser);

				Collection<ClaimItem> items = claimItemService.getClaimItem((Integer) claimId);

				if (items != null) {
					Iterator<ClaimItem> itemIterator = items.iterator();
					CaseStatus caseStatus = new CaseStatus();
					caseStatus.setCaseStatusId(Claim.CLAIM_COMPLETE);

					while (itemIterator.hasNext()) {
						ClaimItem item = itemIterator.next();

						if (item != null) {
							item.setClaimItemStatus(caseStatus);
							claimItemService.update(item, actionUser);
						}
					}
				}
			}
		}

		recordHistory(claim, "COMPLETE CLAIM", "", actionUser);

		return claim;
	}

	public Claim openClaim(Serializable claimId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		String[] required = { "ProductId", "MemberGroupId", "ProviderId", "DiagnosisId", "Diagnosis2Id", "Diagnosis3Id",
				"MemberId", "ClaimItemStatus"};
		Claim claim = get(claimId, required);

		if (claim != null) {

			boolean isEmployee = false;

			CaseStatus claimStatus = claim.getClaimStatus();

			if (claim.getMemberId().getCustomerPolicyNumber().equalsIgnoreCase(claim.getMemberId().getParentNumber())) {
				isEmployee = true;
			}

			// Modified 20150414 by FVO, rollback when case->cost calculation
			String[] param = { "claimId.claimId", "deletedStatus", "claimItemStatus.caseStatusId" };
			Object[] value = { claimId, Integer.valueOf(0), claim.CLAIM_PRE_OPEN };

			int totalClaimTentative = claimItemService.getTotal(null, null, param, value);
			if (claimStatus != null) {

				if (claimStatus.getCaseStatusId().intValue() == Claim.CLAIM_CHECKED
						|| claimStatus.getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED
						|| (claimStatus.getCaseStatusId().intValue() == Claim.CLAIM_OPEN && totalClaimTentative > 0)) {
					// rollback
					System.out.println("MASUK ROLLBACK OPEN");
					Collection<ClaimItem> items = claimItemService.getClaimItem((Integer) claimId);

					ActivityLog claimLog = new ActivityLog();
					claimLog.setAction("CLAIM OPEN/REVERSAL");
					claimLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
					claimLog.setUsername(actionUser.getUser().getUsername());
					claimLog.setClaimId(claim);
					claimLog.setDescription("CNO = " + claim.getClaimNumber() + " REVERSAL TOTAL CHARGE = "
							+ claim.getClaimChargeValue());

					activityLogService.create(claimLog);

					if (items != null) {
						Iterator<ClaimItem> iteratorItem = items.iterator();

						MemberBenefit memberBenefit = null;

						while (iteratorItem.hasNext()) {

							ClaimItem ci = iteratorItem.next();

							if (ci != null) {
								memberBenefit = ci.getMemberBenefitId();

								if (memberBenefit != null) {

									String[] requiredMB = { "MemberBenefit.MemberProductId",
											"MemberBenefit.MemberProductId.ProductId" };
									memberBenefit = memberBenefitService.get(memberBenefit.getMemberBenefitId(),
											requiredMB);

									double remaining = 0.0;

									if (memberBenefit.getCurrentBenefitPosition() != null) {
										remaining = memberBenefit.getCurrentBenefitPosition()
												+ ci.getClaimItemApprovedValue();
									}
									double currentBenefitUsage = memberBenefit.getBenefitUsage()
											- ci.getClaimItemApprovedValue();
									double currentExces = memberBenefit.getBenefitExcessed() - ci.getExcessValue();

									memberBenefit.setBenefitUsage(currentBenefitUsage);
									memberBenefit.setCurrentBenefitPosition(remaining);

									double benefitLImit = memberBenefit.getBenefitLimit() == null ? 0
											: memberBenefit.getBenefitLimit();
									if (benefitLImit != 0) {
										memberBenefit.setBenefitUsagePercentage(
												currentBenefitUsage / memberBenefit.getBenefitLimit() * 100);
									}
									memberBenefit.setBenefitExcessed(currentExces);

									double occuranceUsage = memberBenefit.getBenefitOccuranceUsage() == null ? 0.0
											: memberBenefit.getBenefitOccuranceUsage();

									occuranceUsage = occuranceUsage - ci.getClaimItemAmount();

									memberBenefit.setBenefitOccuranceUsage(occuranceUsage);

									if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId()
											.intValue() == BenefitUsageType.ANNUAL) {

										if (memberBenefit.getIsAsCharge() != null
												&& !memberBenefit.getIsAsCharge().booleanValue()) {
											double currentBenefitPosition = (memberBenefit
													.getCurrentBenefitPosition() == null ? 0
															: memberBenefit.getCurrentBenefitPosition())
													+ ci.getClaimItemApprovedValue();
											memberBenefit.setCurrentBenefitPosition(currentBenefitPosition);
										}
									}

									memberBenefitService.update(memberBenefit, actionUser);

									if (claim.getSmmLayerId() == null) {

										MemberLimitLayer multiLayerLimit = claim.getMultiLayerId();

										if (multiLayerLimit == null) {
											MemberProduct memberProduct = memberBenefit.getMemberProductId();

											if (memberProduct != null) {

												MemberProduct memberProductActive = null;
												MemberProduct familyPlanIdActive = null;
												MemberProduct sharedPlanIdActive = null;
												double annualBenefitActive = 0.0;

												memberProduct = memberProductService
														.get(memberProduct.getMemberProductId());

												ProductType productType = memberProduct.getProductId().getProductType();

												boolean isFamilyPlan = false;

												if (productType != null) {
													if (productType.getProductTypeId()
															.intValue() == ProductType.GROUP_FAMILY
															|| productType.getProductTypeId()
																	.intValue() == ProductType.FAMILY) {
														isFamilyPlan = true;
													}
												}

												// untuk save member yang active
												if (memberProduct.getMemberProductStatus()
														.getStatusId() != SubscriptionStatus.ACTIVE) {
													memberProductActive = memberProductService.getMemberActiveProduct(
															claim.getMemberId().getMemberId(),
															memberBenefit.getCaseCategoryId().getCaseCategoryId());

													if (memberProductActive == null) {
														// potentially divert
														// benefit

														Collection<MemberBenefit> benefitList = memberBenefitService
																.getMemberBenefitByDate(
																		claim.getMemberId().getMemberId(),
																		memberBenefit.getCaseCategoryId()
																				.getCaseCategoryId(),
																		claim.getAdmissionDate(),
																		claim.getDischargeDate());

														if (benefitList != null && benefitList.size() > 0) {
															for (Iterator iterator2 = benefitList.iterator(); iterator2
																	.hasNext();) {
																MemberBenefit memberBenefit2 = (MemberBenefit) iterator2
																		.next();

																if (memberBenefit2 != null && memberBenefit2
																		.getMemberProductId() != null) {
																	memberProductActive = memberBenefit2
																			.getMemberProductId();

																	if (memberProductActive != null) {
																		if (memberProduct.getMemberProductStatus()
																				.getStatusId()
																				.intValue() != SubscriptionStatus.ACTIVE) {
																			// update
																			// ambil
																			// yang
																			// terbaru
																			memberProductActive = memberProductService
																					.getMemberActiveProduct(
																							claim.getMemberId()
																									.getMemberId(),
																							memberBenefit2
																									.getProductCaseCategoryId()
																									.getCaseCategoryId());
																		}
																	}

																	break;
																}

															}
														}
													}

													if (memberProductActive != null){
														sharedPlanIdActive = memberProductActive.getSecondaryCoverageId();
														if (sharedPlanIdActive != null)
															sharedPlanIdActive = memberProductService
																	.get(sharedPlanIdActive.getMemberProductId());
	
														familyPlanIdActive = memberProductActive.getFamilyProductId();
														if (familyPlanIdActive != null)
															familyPlanIdActive = memberProductService
																	.get(familyPlanIdActive.getMemberProductId());
	
														annualBenefitActive = memberProductActive.getAnnualBenefit() == null
																? -1 : memberProductActive.getAnnualBenefit();
													}
												}

												MemberProduct sharedPlanId = memberProduct.getSecondaryCoverageId();
												if (sharedPlanId != null)
													sharedPlanId = memberProductService
															.get(sharedPlanId.getMemberProductId());

												MemberProduct familyPlanId = memberProduct.getFamilyProductId();
												if (familyPlanId != null)
													familyPlanId = memberProductService
															.get(familyPlanId.getMemberProductId());

												double annualBenefit = memberProduct.getAnnualBenefit() == null ? -1
														: memberProduct.getAnnualBenefit();

												if (memberProduct.getProductId().getAnnualLimitValue()
														.doubleValue() > 0) {

													// untuk reimbuerst
													if (memberProductActive != null) {
														double approvedValue = ci.getClaimItemApprovedValue();
														double currentProductLimitActive = memberProductActive
																.getActualBenefitLimit().doubleValue() + approvedValue;

														double currentUsageActive = memberProductActive
																.getBenefitUsage() == null ? 0.0
																		: memberProductActive.getBenefitUsage();

														double productUsage = currentUsageActive - approvedValue;

														memberProductActive
																.setActualBenefitLimit(currentProductLimitActive);
														memberProductActive.setBenefitUsage(productUsage);

														if (annualBenefitActive > 0) {
															double percentage = ((currentUsageActive - approvedValue)
																	/ annualBenefitActive) * 100;
															memberProductActive.setBenefitUsagePercentage(percentage);
														}

														memberProductService.update(memberProductActive, actionUser);
														// untuk cashless
													} else {
														double approvedValue = ci.getClaimItemApprovedValue();
														double currentProductLimit = memberProduct
																.getActualBenefitLimit().doubleValue() + approvedValue;

														double currentUsage = memberProduct.getBenefitUsage() == null
																? 0.0 : memberProduct.getBenefitUsage();

														double productUsage = currentUsage - approvedValue;

														memberProduct.setActualBenefitLimit(currentProductLimit);
														memberProduct.setBenefitUsage(productUsage);

														if (annualBenefit > 0) {
															double percentage = ((currentUsage - approvedValue)
																	/ annualBenefit) * 100;
															memberProduct.setBenefitUsagePercentage(percentage);
														}

														memberProductService.update(memberProduct, actionUser);

													}

												} else {
													if (memberProductActive != null) {
														double approvedValue = ci.getClaimItemApprovedValue();
														double currentUsageActive = memberProductActive
																.getBenefitUsage() == null ? 0.0
																		: memberProductActive.getBenefitUsage();

														double productUsage = currentUsageActive - approvedValue;

														memberProductActive.setBenefitUsage(productUsage);

														memberProductService.update(memberProductActive, actionUser);

													} else {
														double approvedValue = ci.getClaimItemApprovedValue();
														double currentUsage = memberProduct.getBenefitUsage() == null
																? 0.0 : memberProduct.getBenefitUsage();

														double productUsage = currentUsage - approvedValue;

														memberProduct.setBenefitUsage(productUsage);

														memberProductService.update(memberProduct, actionUser);
													}
												}

												if (sharedPlanId != null && familyPlanId == null) {
													// sharedPlanId =
													// memberProductService.get(sharedPlanId.getMemberProductId());

													if (sharedPlanId != null) {

														if (memberProductActive != null) {

															// sharedPlanIdActive
															// =
															// memberProductService.get(sharedPlanIdActive.getMemberProductId());

															double beforeReversalLimit = sharedPlanIdActive
																	.getActualBenefitLimit().doubleValue();
															double approvedValue = ci.getClaimItemApprovedValue();
															double currentProductLimit = beforeReversalLimit
																	+ approvedValue;
															double productUsage = sharedPlanIdActive.getBenefitUsage()
																	- approvedValue;

															if (memberProductActive.getMemberId()
																	.getMemberId() != sharedPlanIdActive.getMemberId()
																			.getMemberId())
																sharedPlanIdActive
																		.setActualBenefitLimit(currentProductLimit);

															ActivityLog benefitLog = new ActivityLog();
															benefitLog.setAction("MEMBER PRODUCT OPEN/REVERSAL");
															benefitLog.setActivityLogTime(
																	new java.sql.Timestamp(System.currentTimeMillis()));
															benefitLog.setUsername(actionUser.getUser().getUsername());
															benefitLog.setClaimId(claim);

															benefitLog.setDescription(
																	"MEMBER = " + claim.getMemberName() + " CASE CAT = "
																			+ claim.getClaimServiceType()
																			+ " MEMBER PRODUCT ID = "
																			+ sharedPlanIdActive.getMemberProductId()
																			+ " FROM = "
																			+ BigDecimal.valueOf(beforeReversalLimit)
																					.toPlainString()
																			+ " TO = "
																			+ BigDecimal.valueOf(currentProductLimit)
																					.toPlainString());

															activityLogService.create(benefitLog);

															memberProductService.update(sharedPlanIdActive, actionUser);

														} else {

															double beforeReversalLimit = sharedPlanId
																	.getActualBenefitLimit().doubleValue();
															double approvedValue = ci.getClaimItemApprovedValue();
															double currentProductLimit = beforeReversalLimit
																	+ approvedValue;
															double productUsage = sharedPlanId.getBenefitUsage()
																	- approvedValue;

															if (memberProduct.getMemberId()
																	.getMemberId() != sharedPlanId.getMemberId()
																			.getMemberId())
																sharedPlanId.setActualBenefitLimit(currentProductLimit);

															ActivityLog benefitLog = new ActivityLog();
															benefitLog.setAction("MEMBER PRODUCT OPEN/REVERSAL");
															benefitLog.setActivityLogTime(
																	new java.sql.Timestamp(System.currentTimeMillis()));
															benefitLog.setUsername(actionUser.getUser().getUsername());
															benefitLog.setClaimId(claim);

															benefitLog.setDescription("MEMBER = "
																	+ claim.getMemberName() + " CASE CAT = "
																	+ claim.getClaimServiceType()
																	+ " MEMBER PRODUCT ID = "
																	+ sharedPlanId.getMemberProductId() + " FROM = "
																	+ BigDecimal.valueOf(beforeReversalLimit)
																			.toPlainString()
																	+ " TO = " + BigDecimal.valueOf(currentProductLimit)
																			.toPlainString());

															activityLogService.create(benefitLog);

															memberProductService.update(sharedPlanId, actionUser);
														}

													}
												}

												if (familyPlanId != null && sharedPlanId == null) {
													// familyPlanId =
													// memberProductService.get(familyPlanId.getMemberProductId());

													if (familyPlanId != null) {

														// untuk reimbuerst
														if (memberProductActive != null) {

															// familyPlanIdActive
															// =
															// memberProductService.get(familyPlanIdActive.getMemberProductId());

															double beforeReversalLimit = familyPlanIdActive
																	.getActualBenefitLimit().doubleValue();
															double approvedValue = ci.getClaimItemApprovedValue();
															double currentProductLimit = beforeReversalLimit
																	+ approvedValue;
															double productUsage = 0.0;

															if (familyPlanIdActive != null) {
																if (familyPlanIdActive.getBenefitUsage() != null) {
																	productUsage = familyPlanIdActive.getBenefitUsage()
																			- approvedValue;
																}

																if (memberProductActive.getMemberId()
																		.getMemberId() != familyPlanIdActive
																				.getMemberId().getMemberId())
																	familyPlanIdActive
																			.setActualBenefitLimit(currentProductLimit);
															}

															ActivityLog benefitLog = new ActivityLog();
															benefitLog.setAction("MEMBER PRODUCT OPEN/REVERSAL");
															benefitLog.setActivityLogTime(
																	new java.sql.Timestamp(System.currentTimeMillis()));
															benefitLog.setUsername(actionUser.getUser().getUsername());
															benefitLog.setClaimId(claim);

															benefitLog.setDescription(
																	"MEMBER = " + claim.getMemberName() + " CASE CAT = "
																			+ claim.getClaimServiceType()
																			+ " MEMBER PRODUCT ID = "
																			+ familyPlanIdActive.getMemberProductId()
																			+ " FROM = "
																			+ BigDecimal.valueOf(beforeReversalLimit)
																					.toPlainString()
																			+ " TO = "
																			+ BigDecimal.valueOf(currentProductLimit)
																					.toPlainString());

															activityLogService.create(benefitLog);

															memberProductService.update(familyPlanIdActive, actionUser);

														} else {
															double beforeReversalLimit = familyPlanId
																	.getActualBenefitLimit().doubleValue();
															double approvedValue = ci.getClaimItemApprovedValue();
															double currentProductLimit = beforeReversalLimit
																	+ approvedValue;
															double productUsage = 0.0;

															if (familyPlanId != null) {
																if (familyPlanId.getBenefitUsage() != null) {
																	productUsage = familyPlanId.getBenefitUsage()
																			- approvedValue;
																}

																if (memberProduct.getMemberId()
																		.getMemberId() != familyPlanId.getMemberId()
																				.getMemberId())
																	familyPlanId
																			.setActualBenefitLimit(currentProductLimit);
															}
															ActivityLog benefitLog = new ActivityLog();
															benefitLog.setAction("MEMBER PRODUCT OPEN/REVERSAL");
															benefitLog.setActivityLogTime(
																	new java.sql.Timestamp(System.currentTimeMillis()));
															benefitLog.setUsername(actionUser.getUser().getUsername());
															benefitLog.setClaimId(claim);

															benefitLog.setDescription("MEMBER = "
																	+ claim.getMemberName() + " CASE CAT = "
																	+ claim.getClaimServiceType()
																	+ " MEMBER PRODUCT ID = "
																	+ familyPlanId.getMemberProductId() + " FROM = "
																	+ BigDecimal.valueOf(beforeReversalLimit)
																			.toPlainString()
																	+ " TO = " + BigDecimal.valueOf(currentProductLimit)
																			.toPlainString());

															activityLogService.create(benefitLog);

															memberProductService.update(familyPlanId, actionUser);
														}
													}
												}

												if (familyPlanId != null && sharedPlanId != null) {

													// familyPlanId =
													// memberProductService.get(familyPlanId.getMemberProductId());
													// sharedPlanId =
													// memberProductService.get(sharedPlanId.getMemberProductId());

													if (memberProductActive != null) {

														// familyPlanIdActive =
														// memberProductService.get(familyPlanIdActive.getMemberProductId());
														if (familyPlanIdActive != null && sharedPlanIdActive != null) {

															double beforeFamilyReversalLimit = familyPlanIdActive
																	.getActualBenefitLimit().doubleValue();
															double beforeSharedReversalLimit = sharedPlanIdActive
																	.getActualBenefitLimit().doubleValue();
															double approvedValue = ci.getClaimItemApprovedValue();
															double currentFamilyProductLimit = beforeFamilyReversalLimit
																	+ approvedValue;
															double currentSharedProductLimit = beforeSharedReversalLimit
																	+ approvedValue;
															// double
															// productUsage =
															// familyPlanIdActive.getBenefitUsage()
															// - approvedValue;

															if (memberProductActive.getMemberId()
																	.getMemberId() != familyPlanIdActive.getMemberId()
																			.getMemberId()
																	&& memberProductActive.getMemberId()
																			.getMemberId() != sharedPlanIdActive
																					.getMemberId().getMemberId()) {
																familyPlanIdActive.setActualBenefitLimit(
																		currentFamilyProductLimit);
																sharedPlanId.setActualBenefitLimit(
																		currentSharedProductLimit);
															}

															ActivityLog benefitLog = new ActivityLog();
															benefitLog.setAction("MEMBER PRODUCT OPEN/REVERSAL");
															benefitLog.setActivityLogTime(
																	new java.sql.Timestamp(System.currentTimeMillis()));
															benefitLog.setUsername(actionUser.getUser().getUsername());
															benefitLog.setClaimId(claim);

															benefitLog.setDescription("MEMBER = "
																	+ claim.getMemberName() + " CASE CAT = "
																	+ claim.getClaimServiceType()
																	+ " MEMBER PRODUCT ID = "
																	+ familyPlanId.getMemberProductId() + " FROM = "
																	+ BigDecimal.valueOf(beforeFamilyReversalLimit)
																			.toPlainString()
																	+ " TO = "
																	+ BigDecimal.valueOf(currentFamilyProductLimit)
																			.toPlainString());

															activityLogService.create(benefitLog);

															memberProductService.update(familyPlanIdActive, actionUser);
															memberProductService.update(sharedPlanIdActive, actionUser);
														}

													} else {

														if (familyPlanId != null && sharedPlanId != null) {

															double beforeFamilyReversalLimit = familyPlanId
																	.getActualBenefitLimit().doubleValue();
															double beforeSharedReversalLimit = sharedPlanId
																	.getActualBenefitLimit().doubleValue();
															double approvedValue = ci.getClaimItemApprovedValue();
															double currentFamilyProductLimit = beforeFamilyReversalLimit
																	+ approvedValue;
															double currentSharedProductLimit = beforeSharedReversalLimit
																	+ approvedValue;
															// double
															// productUsage =
															// familyPlanId.getBenefitUsage()
															// - approvedValue;

															if (memberProduct.getMemberId()
																	.getMemberId() != familyPlanId.getMemberId()
																			.getMemberId()
																	&& memberProduct.getMemberId()
																			.getMemberId() != sharedPlanId.getMemberId()
																					.getMemberId()) {
																familyPlanId.setActualBenefitLimit(
																		currentFamilyProductLimit);
																sharedPlanId.setActualBenefitLimit(
																		currentSharedProductLimit);
															}

															ActivityLog benefitLog = new ActivityLog();
															benefitLog.setAction("MEMBER PRODUCT OPEN/REVERSAL");
															benefitLog.setActivityLogTime(
																	new java.sql.Timestamp(System.currentTimeMillis()));
															benefitLog.setUsername(actionUser.getUser().getUsername());
															benefitLog.setClaimId(claim);

															benefitLog.setDescription("MEMBER = "
																	+ claim.getMemberName() + " CASE CAT = "
																	+ claim.getClaimServiceType()
																	+ " MEMBER PRODUCT ID = "
																	+ familyPlanId.getMemberProductId() + " FROM = "
																	+ BigDecimal.valueOf(beforeFamilyReversalLimit)
																			.toPlainString()
																	+ " TO = "
																	+ BigDecimal.valueOf(currentFamilyProductLimit)
																			.toPlainString());

															activityLogService.create(benefitLog);

															memberProductService.update(familyPlanId, actionUser);
															memberProductService.update(sharedPlanId, actionUser);
														}
													}
												}
											}
										} else {
											MemberLimitLayer additionalLayer = memberLimitLayerService
													.get(claim.getMultiLayerId().getMemberLimitLayerId());

											if (additionalLayer != null) {
												double approvedValue = claim.getClaimApprovedValue() == null ? 0.0
														: claim.getClaimApprovedValue().doubleValue();

												double actualLimit = additionalLayer.getActualBenefitLimit() == null
														? 0.0 : additionalLayer.getActualBenefitLimit().doubleValue();
												double usage = additionalLayer.getBenefitUsage() == null ? 0.0
														: additionalLayer.getBenefitUsage().doubleValue();

												additionalLayer.setActualBenefitLimit(actualLimit + approvedValue);
												additionalLayer.setBenefitUsage(usage - approvedValue);

												memberLimitLayerService.update(additionalLayer, actionUser);
											}
										}
									} else {
										// SMM LIMIT REVERSAL

										MemberLimitLayer limit = claim.getSmmLayerId();

										if (limit != null) {

											limit = memberLimitLayerService
													.get(claim.getSmmLayerId().getMemberLimitLayerId());

											double beforeReversalLimit = limit.getActualBenefitLimit() == null ? 0.0
													: limit.getActualBenefitLimit().doubleValue();
											double approvedValue = ci.getClaimItemApprovedValue();
											double currentProductLimit = beforeReversalLimit + approvedValue;

											double beforeReversalUsage = limit.getBenefitUsage() == null ? 0.0
													: limit.getBenefitUsage();
											double currentUsage = beforeReversalUsage - approvedValue;

											limit.setActualBenefitLimit(currentProductLimit);
											limit.setBenefitUsage(currentUsage);

											memberLimitLayerService.update(limit, actionUser);
										}

									}
								}

								ci.setClaimItemApprovedValue(Double.valueOf(0));
								ci.setExcessValue(Double.valueOf(0));
								ci.setBenefitCheckedBy("");
								ci.setBenefitCheckRemarks("");
								ci.setBenefitCheckedDate(null);
								ci.setIsCorrected(true);
								ci.setClaimExGratia(Double.valueOf(0));
								ci.setMemberBenefitId(null);
								CaseStatus status = new CaseStatus();
								status.setCaseStatusId(Claim.CLAIM_OPEN);
								ci.setClaimItemStatus(status);

								claimItemService.update(ci, actionUser);
							}
						}

						/**
						 * Rollback ASO Fund
						 */

						if (claim.getPolicyId() != null) {
							Policy policy = policyService.get(claim.getPolicyId());

							if (policy.getIsUsingFloatingFund() != null
									&& policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY) {

								double currentFund = policy.getCurrentPolicyFund() == null ? 0.0
										: policy.getCurrentPolicyFund();
								double totalApprovedClaim = claim.getClaimApprovedValue() == null ? 0.0
										: claim.getClaimApprovedValue();

								double reversalValue = currentFund + totalApprovedClaim;

								ActivityLog logReversalPolicy = new ActivityLog();
								logReversalPolicy.setAction("REVERSE POLICY FUND");
								logReversalPolicy
										.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
								logReversalPolicy.setUsername(actionUser.getUser().getUsername());
								logReversalPolicy.setPolicyId(policy);
								logReversalPolicy.setDescription(
										"REVERSE POLICY FUND = " + policy.getPolicyNumber() + " FROM = " + currentFund
												+ " TO = " + reversalValue + " CNO = " + claim.getClaimNumber());

								activityLogService.create(logReversalPolicy);

								policy.setCurrentPolicyFund(reversalValue);
								policyService.update(policy, actionUser);
							} else if (policy.getIsUsingFloatingFund() != null
									&& policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE) {

								String[] eqPolicyCoverageParam = { "policyId.policyId", "caseCategoryId.caseCategoryId",
										"deletedStatus" };
								Object[] eqPolicyCoverageValue = { policy.getPolicyId(),
										claim.getCaseCategoryId().getCaseCategoryId(), 0 };

								Collection<PolicyCoverageFund> fundList = policyCoverageFundService.search(null, null,
										eqPolicyCoverageParam, eqPolicyCoverageValue, 0, 1);

								if (fundList != null) {
									Iterator<PolicyCoverageFund> iteratorFund = fundList.iterator();

									if (iteratorFund.hasNext()) {
										PolicyCoverageFund theFund = iteratorFund.next();

										if (theFund != null) {

											double currentFund = theFund.getCurrentFundValue() == null ? 0.0
													: theFund.getCurrentFundValue();
											double totalApprovedClaim = claim.getClaimApprovedValue() == null ? 0.0
													: claim.getClaimApprovedValue();

											double reversalValue = currentFund + totalApprovedClaim;

											ActivityLog logReversalPolicy = new ActivityLog();
											logReversalPolicy.setAction("REVERSE POLICY FUND");
											logReversalPolicy.setActivityLogTime(
													new java.sql.Timestamp(System.currentTimeMillis()));
											logReversalPolicy.setUsername(actionUser.getUser().getUsername());
											logReversalPolicy.setPolicyId(policy);
											logReversalPolicy.setDescription("REVERSE POLICY FUND = "
													+ policy.getPolicyNumber() + " FROM = " + currentFund + " TO = "
													+ reversalValue + " CNO = " + claim.getClaimNumber());

											activityLogService.create(logReversalPolicy);

											theFund.setCurrentFundValue(reversalValue);
											policyCoverageFundService.update(theFund, actionUser);
										}
									}
								}
							}
						}

						// setting member product

						// if excess Charge Exist
						//

						String[] eqExcessParam = { "claimId.claimId" };
						Object[] eqExcessValue = { claim.getClaimId() };
						int total = excessChargeService.getTotal(null, null, eqExcessParam, eqExcessValue);

						Collection<ExcessCharge> excessCollection = excessChargeService.search(null, null,
								eqExcessParam, eqExcessValue, 0, total);

						if (excessCollection != null) {
							Iterator<ExcessCharge> excessIterator = excessCollection.iterator();

							while (excessIterator.hasNext()) {

								ExcessCharge excess = excessIterator.next();

								if (excess != null) {

									String[] eqItemParam = { "excessChargeId.excessChargeId" };
									Object[] eqItemValue = { excess.getExcessChargeId() };

									int totalItem = excessChargeItemService.getTotal(null, null, eqItemParam,
											eqItemValue);

									Collection<ExcessChargeItem> excessItemCollection = excessChargeItemService
											.search(null, null, eqItemParam, eqItemValue, 0, totalItem);

									if (excessItemCollection != null) {
										Iterator<ExcessChargeItem> itemIterator = excessItemCollection.iterator();

										while (itemIterator.hasNext()) {
											ExcessChargeItem excessItem = itemIterator.next();

											if (excessItem != null) {
												excessChargeItemService.delete(excessItem.getExcessChargeItemId(),
														actionUser);
											}
										}
									}

									excessChargeService.delete(excess.getExcessChargeId(), actionUser);

								}
							}
						}

					}
				} else if (claimStatus.getCaseStatusId().intValue() == Claim.CLAIM_VERIFIED) {
					Collection<ClaimItem> items = claimItemService.getClaimItem((Integer) claimId);

					if (items != null) {
						Iterator<ClaimItem> iteratorItem = items.iterator();

						while (iteratorItem.hasNext()) {

							ClaimItem ci = iteratorItem.next();

							if (ci != null) {

								CaseStatus status = new CaseStatus();
								status.setCaseStatusId(Claim.CLAIM_OPEN);
								ci.setClaimItemStatus(status);

								claimItemService.update(ci, actionUser);
							}
						}

					}
				}

				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(Claim.CLAIM_OPEN);
				claim.setClaimStatus(status);
				claim.setClaimApprovedValue(Double.valueOf(0));
				claim.setClaimExcessValue(Double.valueOf(0));
				claim.setIsExGratia(0);
				claim.setIsClausulExcluded(0);

				update(claim, actionUser);

				recordHistory(claim, "OPEN CLAIM", "", actionUser);

				// reversal for cob / linked card claim

				String[] eqParamRef = { "cobClaimReferenceId.claimId", "deletedStatus" };
				Object[] eqValueRef = { claim.getClaimId(), 0 };

				Claim cobClaim = null;

				Collection<Claim> cobList = search(null, null, eqParamRef, eqValueRef, 0, 1);
				if (cobList != null) {
					Iterator<Claim> iterator = cobList.iterator();

					if (iterator.hasNext()) {
						cobClaim = iterator.next();
					}
				}

				if (cobClaim != null) {
					openClaim(cobClaim.getClaimId(), actionUser);

					voidClaim(cobClaim.getClaimId(), "XXXXXXX", "[CoB Linked Card Adjustment]", actionUser);

				}
			}
		}

		return claim;
	}

	public Integer reduceTotalItem(Integer claimId) throws Exception {
		// TODO Auto-generated method stub
		Integer result = 0;

		Claim claim = get(claimId);

		if (claim != null) {
			Integer totalItem = claim.getTotalItem();
			if (totalItem != null) {
				claim.setTotalItem(totalItem - 1);
				result = totalItem - 1;
			} else {
				claim.setTotalItem(0);
				result = 0;
			}
			claimDao.update(claim);

		}

		return result;
	}

	public Claim requestCorrection(Integer claimId, ActionUser actionUser) throws Exception {

		Claim result = null;

		if (claimId != null) {
			Claim claim = get(claimId);

			if (claim != null) {

				result = claim.cloneClaim();

				String[] eqParam = { "claimId.claimId" };
				Object[] eqValue = { claimId };
				int totalItem = claimItemService.getTotal(null, null, eqParam, eqValue);
				Collection<ClaimItem> claimItems = claimItemService.search(null, null, eqParam, eqValue, 0, totalItem);
				result = create(result, actionUser);

				if (claimItems != null) {
					Iterator<ClaimItem> claimItemIterator = claimItems.iterator();

					while (claimItemIterator.hasNext()) {
						ClaimItem claimItem = claimItemIterator.next();

						if (claimItem != null) {

							ClaimItem newClaimItem = claimItem.clone();

							newClaimItem.setClaimId(result);
							newClaimItem.setIsCorrected(false);
							newClaimItem = claimItemService.create(newClaimItem, actionUser);

							claimItem.setCorrectionClaimItem(newClaimItem);
							claimItem.setIsCorrected(true);
							claimItem.setDeletedStatus(1);
							claimItem.setDeletedTime(new Timestamp(System.currentTimeMillis()));
							claimItem.setDeletedBy("correction-request");

							claimItemService.update(claimItem, actionUser);

						}
					}
				}
				claim.setIsCorrected(true);
				claim.setCorrectionClaim(result.getClaimId());
				claim.setCorrectionTime(new Timestamp(System.currentTimeMillis()));

				claim.setDeletedBy("correction-request");
				claim.setDeletedStatus(1);
				claim.setDeletedTime(new Timestamp(System.currentTimeMillis()));

				update(claim, actionUser);

			}
		}

		return result;
	}

	public ClaimItem updateClaimItem(ClaimItem claimItem, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		ClaimItem result = null;

		if (claimItem != null) {

			ClaimItem item = claimItemService.update(claimItem, actionUser);

			ClaimItem destinationItem = item.clone();

			if (item != null) {
				result = item;

				Claim claim = get(claimItem.getClaimId().getClaimId());

				/*
				 * create historical version of claim_item create new instance
				 * with the same key
				 */

				double claimCharge = 0;

				if (claim != null) {
					String[] eqClaimParam = { "deletedStatus", "claimId.claimId" };
					Object[] eqClaimValue = { Integer.valueOf(0), claim.getClaimId() };

					int total = claimItemService.getTotal(null, null, eqClaimParam, eqClaimValue);

					Collection<ClaimItem> citems = claimItemService.search(null, null, eqClaimParam, eqClaimValue, 0,
							total);

					if (citems != null) {
						Iterator<ClaimItem> cIterator = citems.iterator();

						if (cIterator != null) {
							while (cIterator.hasNext()) {
								ClaimItem citem = cIterator.next();

								if (citem != null) {
									claimCharge += citem.getClaimItemValue();
								}
							}
						}
					}
				}

				claim.setClaimChargeValue(claimCharge);
				update(claim, actionUser);
			}

		}
		return result;
	}

	public Collection<Claim> searchExcessClaim(String[] likeColumns, Object[] likeParams, String[] eqColumn,
			Object[] eqParam, String[] betweenParam, Object[] minBetween, Object[] maxBetween, int index, int offset)
					throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumn, eqParam, c);
		DaoSupportUtil.setBetweenParam(betweenParam, minBetween, maxBetween, c);
		DaoSupportUtil.setGreaterThan(new Double(0), "claimExcessValue", c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();

		return list;

		// TODO Auto-generated method stub

	}

	// Add 20150417 by FVO, untuk Download Recap (Delivery Letter)
	public Collection<Object[]> generateClaimDownloadRecap(Integer paymentId) throws Exception {
		Collection<Object[]> result = null;

		Session session = claimDao.getClaimSession();
		if (session != null) {
			/*
			 * Query query = session.createQuery(
			 * "SELECT c.memberGroupId, c.memberId.memberGroupId.groupName, SUM(c.claimApprovedValue) AS approvedValue,"
			 * +
			 * " SUM(c.claimChargeValue) AS chargeValue, COUNT(*) AS totalClaim , SUM(c.pembayaranDimuka) AS totalDownPayment FROM Claim c LEFT JOIN c.memberId.memberGroupId "
			 * +
			 * " WHERE c.paymentId = :paymentId AND c.deletedStatus = 0 GROUP BY c.memberGroupId, c.claimTypeId, c.memberId.memberGroupId.groupName"
			 * );
			 */

			// Edit 20150424 by FVO, change logic to get Member Product Payment
			// type, if pay letter than approved value + excess value
			// Query query = session.createQuery("SELECT c.memberGroupId, " +
			// //index 0 group id
			// "c.memberId.memberGroupId.groupName, " + //index 1 group name
			// "SUM(CASE WHEN c.claimApprovedValue > -1 THEN
			// c.claimApprovedValue ELSE 0 END) AS approvedValue, "
			// + //index 2 approved value
			// "SUM(CASE WHEN c.claimChargeValue > -1 THEN c.claimChargeValue
			// ELSE 0 END) AS chargeValue, "
			// + //index 3 charge value
			// "SUM(CASE WHEN c.claimExcessValue > -1 THEN c.claimExcessValue
			// ELSE 0 END) AS excessValue, "
			// + //index 4 excess value
			// "SUM(CASE WHEN c.productId.excessPaymentType = 2 THEN
			// c.claimExcessValue ELSE 0 END) AS productExcessValue, "
			// + //index 5 product excess value
			// "COUNT(c) AS totalClaim, " + //index 6 total claim
			// "SUM(c.pembayaranDimuka) AS totalDownPayment FROM Claim c LEFT
			// JOIN c.memberId.memberGroupId "
			// +
			// " WHERE c.paymentId = :paymentId AND c.deletedStatus = 0 GROUP BY
			// c.memberGroupId, c.claimTypeId,
			// c.memberId.memberGroupId.groupName");
			/*
			 * Query query = session.createQuery("SELECT c.memberGroupId, " +
			 * //index 0 group id "c.memberId.memberGroupId.groupName, " +
			 * //index 1 group name
			 * "SUM(CASE WHEN c.claimApprovedValue > -1 THEN c.claimApprovedValue ELSE 0 END) AS approvedValue, "
			 * + //index 2 approved value
			 * "SUM(CASE WHEN c.claimChargeValue > -1 THEN c.claimChargeValue ELSE 0 END) AS chargeValue, "
			 * + //index 3 charge value
			 * "SUM(CASE WHEN c.claimExcessValue > -1 THEN c.claimExcessValue ELSE 0 END) AS excessValue, "
			 * + //index 4 excess value
			 * "SUM(CASE WHEN c.productId.excessPaymentType = 2 THEN c.claimExcessValue ELSE 0 END) AS productExcessValue, "
			 * + //index 5 product excess value "COUNT(c) AS totalClaim, " +
			 * //index 6 total claim
			 * "SUM(c.pembayaranDimuka) AS totalDownPayment FROM Claim c LEFT JOIN c.memberId.memberGroupId LEFT JOIN c.productId "
			 * +
			 * " WHERE c.paymentId = :paymentId AND c.deletedStatus = 0 GROUP BY c.memberGroupId, c.claimTypeId, c.memberId.memberGroupId.groupName"
			 * );
			 */
			Query query = session.createQuery("SELECT c.memberId.memberGroupId.memberGroupId, " + // index
																									// 0
																									// group
																									// id
			"c.memberId.memberGroupId.groupName, " + // index 1 group
														// name
			"SUM(CASE WHEN c.claimApprovedValue > -1 THEN c.claimApprovedValue ELSE 0 END) AS approvedValue, " + // index
																													// 2
																													// approved
																													// value
			"SUM(CASE WHEN c.claimChargeValue > -1 THEN c.claimChargeValue ELSE 0 END) AS chargeValue, " + // index
																											// 3
																											// charge
																											// value
			"SUM(CASE WHEN c.claimExcessValue > -1 THEN c.claimExcessValue ELSE 0 END) AS excessValue, " + // index
																											// 4
																											// excess
																											// value
			"SUM(CASE WHEN c.memberId.currentPolicyId.excessSelfCovered = 0 THEN c.claimExcessValue ELSE 0 END) AS productExcessValue, "
					+ // index 5 product excess value
					"COUNT(c) AS totalClaim, " + // index 6 total claim
					"SUM(c.pembayaranDimuka) AS totalDownPayment, " + // index 7
																		// total
																		// down
																		// payment
			"SUM(c.paidToProvider) AS totalPaidToProvider " + // index 8
																// total
																// benefit
																// paid
			"FROM Claim c LEFT JOIN c.memberId.memberGroupId LEFT JOIN c.memberId.currentPolicyId "
					+ " WHERE c.paymentId = :paymentId AND c.deletedStatus = 0 GROUP BY c.memberId.memberGroupId.memberGroupId, c.claimTypeId, c.memberId.memberGroupId.groupName");
			query.setInteger("paymentId", paymentId);
			// query.setInteger("claimStatus", Claim.CLAIM_PAID);
			// EXCESSPAYMENTTYPE KOSONG

			// Collection res = query.list();

			result = (Collection<Object[]>) query.list();
		}

		return result;
	}

	public Long getTotalClaimPendingReject(Integer batchClaimId) throws Exception {

		Session session = claimDao.getClaimSession();
		System.out.println("batchClaimId " + batchClaimId);
		Query query = session.createQuery(
				"SELECT COUNT(*) FROM Claim c WHERE c.batchClaimId = :batchClaimId AND c.claimStatus IN (4,10) AND c.deletedStatus = 0 ");

		query.setInteger("batchClaimId", batchClaimId);

		Object result = query.uniqueResult();
		System.out.println("total " + result);

		return (Long) result;

	}

	public int getTotalExcessClaim(String[] likeColumns, Object[] likeParams, String[] eqColumn, Object[] eqParam,
			String[] betweenParam, Object[] minBetween, Object[] maxBetween) throws Exception {
		// TODO Auto-generated method stub

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumn, eqParam, c);
		DaoSupportUtil.setBetweenParam(betweenParam, minBetween, maxBetween, c);
		DaoSupportUtil.setGreaterThan(new Double(0), "claimExcessValue", c);
		int total = DaoSupportUtil.getTotal(c);

		return total;

	}

	public boolean voidClaim(Integer claimId, String traceNum, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		try {
			System.out.println("[VOID] GETTING CLAIM");
			openClaim(claimId, actionUser);
			String[] required = { "Claim.ProviderId" };

			Claim claim = get(claimId,required);

			if (claim != null) {
				System.out.println("[VOID] CLAIM AVAILABLE");
				Collection<ClaimItem> claimItems = claimItemService.getClaimItem(claimId);

				if (claimItems != null) {
					Iterator<ClaimItem> iterator = claimItems.iterator();

					while (iterator.hasNext()) {
						ClaimItem claimItem = iterator.next();
						claimItemService.voidClaimItem(claimItem, actionUser);
					}
				}

				ExcessCharge excess = excessChargeService.getClaimExcess(claimId);

				if (excess != null) {
					PaymentStatus status = new PaymentStatus();
					status.setPaymentStatusId(PaymentStatus.PAYMENT_VOID);
					excess.setExcessChargeStatus(status);

					excessChargeService.update(excess, actionUser);
				}

				claim.setClaimStatus(new CaseStatus(CaseStatus.VOID));
				claim.setDescription("CLAIM VOID");
				claim.setVoidTraceNumber(traceNum);
				System.out.println("[VOID] Trace Number Set");
				// update(claim,actionUser);
				claimDao.update(claim);

				recordHistory(claim, "VOID CLAIM", "", actionUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public double getTotalBenefitUsage(Integer caseCategoryId, Member memberId, Integer status) throws Exception {
		// TODO Auto-generated method stub

		String query = "SELECT sum(c.claimApprovedValue) FROM Claim c WHERE c.caseStatusId.caseStatusId = :status AND ";
		query += " c.caseCategoryId.caseCategoryId = :cc AND c.admissionDate >= :startDate AND c.admissionDate <= :endDate AND c.deletedStatus = 0";

		Session session = claimDao.getClaimSession();

		SQLQuery sqlQuery = session.createSQLQuery(query);
		sqlQuery.setInteger("status", status);
		sqlQuery.setInteger("cc", caseCategoryId);
		sqlQuery.setDate("startDate", memberId.getEffectiveDate());
		sqlQuery.setDate("endDate", memberId.getExpireDate());

		Object result = sqlQuery.uniqueResult();

		return (Double) result;
	}

	public int verifyBulk(Serializable batchClaimId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		int result = 0;
		int totalClaimItem = claimItemService.verifyBulk(batchClaimId, actionUser);

		if (totalClaimItem > 0) {

			String query = "UPDATE Claim c SET c.claimStatus = :claimStatus, c.verifiedBy = :userId, c.verifiedTime = :time  WHERE c.batchClaimId.batchClaimId = :batchId AND c.claimStatus = 11";

			Session session = claimDao.getClaimSession();

			Query sqlQuery = session.createQuery(query);
			sqlQuery.setInteger("batchId", (Integer) batchClaimId);
			sqlQuery.setInteger("claimStatus", Claim.CLAIM_VERIFIED);
			sqlQuery.setString("userId", actionUser.getUser().getUsername());
			sqlQuery.setTimestamp("time", new Timestamp(System.currentTimeMillis()));

			result = sqlQuery.executeUpdate();

		}
		return result;
	}

	public long getTotalOpenClaim(Serializable batchClaimId) throws Exception {
		// TODO Auto-generated method stub

		String query = "SELECT count(c.claimApprovedValue) FROM Claim c WHERE c.claimStatus.caseStatusId = :status AND ";
		query += " c.batchClaimId.batchClaimId = :batchId AND c.deletedStatus = 0 ";

		Session session = claimDao.getClaimSession();

		Query sqlQuery = session.createQuery(query);
		sqlQuery.setInteger("status", Claim.CLAIM_OPEN);
		sqlQuery.setInteger("batchId", (Integer) batchClaimId);

		Object result = sqlQuery.uniqueResult();

		return (Long) result;
	}

	public Claim refreshClaim(Serializable claimId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		Claim claim = get(claimId);

		if (claim != null) {

			Collection<ClaimItem> excessItem = claimItemService.getExcessClaimItem((Integer) claimId);
			ExcessCharge excess = excessChargeService.getClaimExcess(claimId);

			if (excessItem != null && excessItem.size() > 0
					&& (claim.getClaimTypeId().getClaimTypeId() == ClaimType.CASHLESS
							|| claim.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS)) {
			} else {
				if (excess != null) {

					Collection<ExcessChargeItem> excessChargeItem = excessChargeItemService
							.getExcessItem(excess.getExcessChargeId());
					excessChargeService.delete(excess, actionUser);

					Iterator<ExcessChargeItem> iterator = excessChargeItem.iterator();
					while (iterator.hasNext()) {
						ExcessChargeItem item = iterator.next();

						excessChargeItemService.delete(item, actionUser);
					}
				}
			}

			//
		}
		return claim;
	}

	public Integer getNextClaim(Serializable batchClaimId) throws Exception {
		// TODO Auto-generated method stub
		String query = "SELECT c.claimId FROM Claim c WHERE (c.claimStatus.caseStatusId = :openStatus OR ";
		query += " c.claimStatus.caseStatusId = :completeStatus OR c.claimStatus.caseStatusId = :verifiedStatus) AND "
				+ "c.batchClaimId.batchClaimId = :batchId AND c.deletedStatus = 0 ORDER BY c.claimId  ";

		Session session = claimDao.getClaimSession();

		Query sqlQuery = session.createQuery(query);

		sqlQuery.setInteger("openStatus", Claim.CLAIM_OPEN);
		sqlQuery.setInteger("completeStatus", Claim.CLAIM_COMPLETE);
		sqlQuery.setInteger("verifiedStatus", Claim.CLAIM_VERIFIED);

		sqlQuery.setInteger("batchId", (Integer) batchClaimId);
		sqlQuery.setMaxResults(1);

		Object result = sqlQuery.uniqueResult();

		return (Integer) result;

	}

	public boolean changeClaimCategory(Integer claimId, Integer caseCategoryId, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;
		try {
			Claim object = get(claimId);

			String beforeNumber = object.getClaimNumber();
			CaseCategory cc = caseCategoryService.get(caseCategoryId);

			if (object != null) {
				object.setCaseCategoryId(cc);
				java.sql.Date claimDate = object.getClaimDate();
				String counter = object.getNumberClaimCounter();

				DateTime dateTime = new DateTime();
				BatchClaim batchClaim = object.getBatchClaimId();

				String clientCode = "";
				String claimType = "";
				String providerName = "";

				if (batchClaim != null) {
					Client client = batchClaim.getClientId();

					if (client != null) {
						clientCode = client.getClientCode();
					}
					if (batchClaim.getBatchClaimType() != null) {
						if (batchClaim.getBatchClaimType().getClaimTypeId() == ClaimType.CASHLESS) {
							claimType = "C";

							if (batchClaim.getProviderId() != null) {
								providerName = batchClaim.getProviderId().getProviderName();
							}
						} else if (batchClaim.getBatchClaimType().getClaimTypeId() == ClaimType.REIMBURESEMENT) {
							claimType = "R";

						} else if (batchClaim.getBatchClaimType().getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS) {
							claimType = "RK";

						}
					}
				}
				String serviceType = "";

				if (object.getCaseCategoryId() != null) {
					if (object.getCaseCategoryId().getCaseCategoryId() == CaseCategory.INPATIENT) {
						serviceType = "IP";
					} else if (object.getCaseCategoryId().getCaseCategoryId() == CaseCategory.OUTPATIENT) {
						serviceType = "OP";
					} else if (object.getCaseCategoryId().getCaseCategoryId() == CaseCategory.DENTAL) {
						serviceType = "D";
					} else if (object.getCaseCategoryId().getCaseCategoryId() == CaseCategory.OPTICAL) {
						serviceType = "O";
					} else if (object.getCaseCategoryId().getCaseCategoryId() == CaseCategory.MATERNITY) {
						serviceType = "M";
					} else if (object.getCaseCategoryId().getCaseCategoryId() == CaseCategory.SPECIALIST) {
						serviceType = "S";
					} else if (object.getCaseCategoryId().getCaseCategoryId() == CaseCategory.MEDICAL_CHECK_UP) {
						serviceType = "MCU";
					}
				}
				String month = "";

				if (dateTime.getMonthOfYear() < 10) {
					month += "0" + dateTime.getMonthOfYear();
				} else {
					month += dateTime.getMonthOfYear();
				}
				String claimNumber = counter + "/" + month + "/" + dateTime.getYear() + "/" + claimType + "/"
						+ serviceType;

				object.setClaimNumber(claimNumber);

				String claimRemarks = object.getClaimRemarks();

				claimRemarks += " \n change number from : " + beforeNumber + " to : " + claimNumber;

				object.setClaimRemarks(claimRemarks);

				update(object, actionUser);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeMemberClaim(Integer claimId, Integer memberId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		try {
			Claim claim = get(claimId);

			if (claim != null) {

				Member preMember = claim.getMemberId();
				Member member = memberService.get(memberId);

				claim.setMemberId(member);

				String claimRemarks = claim.getClaimRemarks();

				claimRemarks += "\n" + "change member from : " + preMember.getFirstName() + " ( "
						+ preMember.getCustomerPolicyNumber() + " )";
				claimRemarks += " to " + member.getFirstName() + " ( " + member.getCustomerPolicyNumber() + " )";
				claim.setClaimRemarks(claimRemarks);

				update(claim, actionUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Claim createClaimItem(Integer claimId, Collection claimItem, Double pembulatan, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		String[] required = { "Claim.MemberId", "Claim.MemberId.ClientId", "Claim.MemberId.CurrentPolicyId" };

		Claim object = get(claimId, required);

		if (object != null) {

			DateTime birthDate = new DateTime(object.getMemberId().getBirthday());
			DateTime currentDate = new DateTime(object.getAdmissionDate());

			int memberAge = Days.daysBetween(birthDate, currentDate).getDays();

			double claimCharge = 0;

			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Integer.valueOf(Claim.CLAIM_OPEN));

			String remarks = "";

			Integer rejectCategory = null;
			Integer policyId = null;

			if (object.getPolicyId() != null) {
				policyId = object.getPolicyId();
			} else {
				if (object.getMemberId().getCurrentPolicyId() != null) {
					policyId = object.getMemberId().getCurrentPolicyId().getPolicyId();
				}
			}

			if (object.getDiagnosisId() != null) {

				String[] eqPolicyDiagParam = { "policyId", "deletedStatus", "diagnosisId.diagnosisId" };
				Object[] eqPolicyDiagValue = { policyId, 0, object.getDiagnosisId().getDiagnosisId() };

				int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null, null, eqPolicyDiagParam,
						eqPolicyDiagValue);

				if (totalPolicyExclusion > 0) {
					Collection<PolicyDiagnosisExclusion> diagExclusion = policyDiagnosisExclusionService.search(null,
							null, eqPolicyDiagParam, eqPolicyDiagValue, 0, totalPolicyExclusion);

					if (diagExclusion != null) {

						Iterator<PolicyDiagnosisExclusion> iteratorEx = diagExclusion.iterator();

						PolicyDiagnosisExclusion exclusion = null;

						if (iteratorEx.hasNext()) {
							exclusion = iteratorEx.next();
						}
						if (exclusion != null) {

							int ageConstraint = -1;
							String param = "";
							int duration = -1;

							if (exclusion.getPreExistingDays() != null){
								duration = exclusion.getPreExistingDays();
							}
							if (exclusion.getAgeConstraint() != null) {
								ageConstraint = exclusion.getAgeConstraint();
							}
							if (exclusion.getAgeParameter() != null) {
								param = exclusion.getAgeParameter();
							}

							
							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
											
										}
									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}
									}
								}
							} else {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "DIAGNOSIS EXCLUSION";
	
									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									/**
									 * 0000230: [CAR] [PRE EXISTING] Setelah lewat masa Pre Existing harusnya Claim status OPEN
									 */
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}
	
							}
						}
					}
				} else {
					Diagnosis diagnosis =  object.getDiagnosisId();
					MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(
							diagnosis.getDiagnosisId(), object.getMemberId().getMemberId());

					int ageConstraint = -1;
					String param = "";
					int duration = -1;

					if (exclusion != null) {
						if (exclusion.getAgeConstraint() != null) {
							ageConstraint = exclusion.getAgeConstraint();
						}
						if (exclusion.getAgeParameter() != null) {
							param = exclusion.getAgeParameter();
						}
						duration =  exclusion.getPreExistingDays() == null ? -1 : exclusion.getPreExistingDays();
					}

					if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint == -1) {

						
						
						if (duration > 0){
							Member member = memberService.get(object.getMemberId().getMemberId());
							
							if (member != null && member.getJoinDate() != null){
								DateTime join = new DateTime(member.getJoinDate().getTime());
								DateTime admission = new DateTime(object.getAdmissionDate().getTime());
								
								int daysDuration = Days.daysBetween(join, admission).getDays();
								
								if (daysDuration <  duration){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "PRE-EXISTING DIAGNOSIS";
	
									rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
								}
							}
						}
						else {
						
							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);
	
							remarks = "DIAGNOSIS EXCLUSION";
	
							rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
						}
						
					} else if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint > 0) {

						if (param.equalsIgnoreCase("LT")) {
							if (ageConstraint > memberAge) {
								
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}
						else if (param.equalsIgnoreCase("GT")) {
							if (ageConstraint < memberAge) {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}

					}
					else if (exclusion != null && exclusion.getPreExistingDays() != null){

						Member member = memberService.get(object.getMemberId().getMemberId());
						
						if (member != null && member.getJoinDate() != null){
							DateTime join = new DateTime(member.getJoinDate().getTime());
							DateTime admission = new DateTime(object.getAdmissionDate().getTime());
							
							int daysDuration = Days.daysBetween(join, admission).getDays();
							
							if (daysDuration <  duration){
								status.setCaseStatusId(Claim.CLAIM_REJECT);
								object.setClaimStatus(status);

								remarks = "PRE-EXISTING DIAGNOSIS";

								rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
							}
						}
					}
					
					else if (exclusion != null && exclusion.getExpireDate() != null) {
						Date expireDate = exclusion.getExpireDate();

						DateTime exDt = new DateTime(expireDate);
						DateTime ex = exDt.plusDays(1);

						if (object.getAdmissionDate().before(new Date(ex.getMillis()))) {

							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);

							remarks = "PRE-EXISTING DIAGNOSIS";

							rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
						} else {
							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {

										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
							}
						}
					}
				}
			}
			if (object.getDiagnosis2Id() != null) {
				String[] eqPolicyDiagParam = { "policyId", "deletedStatus", "diagnosisId.diagnosisId" };
				Object[] eqPolicyDiagValue = { policyId, 0, object.getDiagnosis2Id().getDiagnosisId() };

				int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null, null, eqPolicyDiagParam,
						eqPolicyDiagValue);

				if (totalPolicyExclusion > 0) {
					Collection<PolicyDiagnosisExclusion> diagExclusion = policyDiagnosisExclusionService.search(null,
							null, eqPolicyDiagParam, eqPolicyDiagValue, 0, totalPolicyExclusion);

					if (diagExclusion != null) {

						Iterator<PolicyDiagnosisExclusion> iteratorEx = diagExclusion.iterator();

						PolicyDiagnosisExclusion exclusion = null;

						if (iteratorEx.hasNext()) {
							exclusion = iteratorEx.next();
						}
						if (exclusion != null) {
							
							int duration = -1;
							
							int ageConstraint = -1;
							String param = "";

							if (exclusion.getPreExistingDays() != null){
								duration = exclusion.getPreExistingDays();
							}
							if (exclusion.getAgeConstraint() != null) {
								ageConstraint = exclusion.getAgeConstraint();
							}
							if (exclusion.getAgeParameter() != null) {
								param = exclusion.getAgeParameter();
							}

							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
											
										}
									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}
									}
								}

							} else {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "DIAGNOSIS EXCLUSION";
	
									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									/**
									 * 0000230: [CAR] [PRE EXISTING] Setelah lewat masa Pre Existing harusnya Claim status OPEN
									 */
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}
	
							}
						}
					}
				} else {
					Diagnosis diagnosis =  object.getDiagnosis2Id();
					MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(
							diagnosis.getDiagnosisId(), object.getMemberId().getMemberId());

					int ageConstraint = -1;
					String param = "";
					int duration = -1;

					if (exclusion != null) {
						if (exclusion.getAgeConstraint() != null) {
							ageConstraint = exclusion.getAgeConstraint();
						}
						if (exclusion.getAgeParameter() != null) {
							param = exclusion.getAgeParameter();
						}
						duration =  exclusion.getPreExistingDays() == null ? -1 : exclusion.getPreExistingDays();
					}

					if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint == -1) {

						
						
						if (duration > 0){
							Member member = memberService.get(object.getMemberId().getMemberId());
							
							if (member != null && member.getJoinDate() != null){
								DateTime join = new DateTime(member.getJoinDate().getTime());
								DateTime admission = new DateTime(object.getAdmissionDate().getTime());
								
								int daysDuration = Days.daysBetween(join, admission).getDays();
								
								if (daysDuration <  duration){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "PRE-EXISTING DIAGNOSIS";
	
									rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
								}
							}
						}
						else {
						
							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);
	
							remarks = "DIAGNOSIS EXCLUSION";
	
							rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
						}
						
					} else if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint > 0) {

						if (param.equalsIgnoreCase("LT")) {
							if (ageConstraint > memberAge) {
								
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}
						else if (param.equalsIgnoreCase("GT")) {
							if (ageConstraint < memberAge) {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}

					}
					else if (exclusion != null && exclusion.getPreExistingDays() != null){

						Member member = memberService.get(object.getMemberId().getMemberId());
						
						if (member != null && member.getJoinDate() != null){
							DateTime join = new DateTime(member.getJoinDate().getTime());
							DateTime admission = new DateTime(object.getAdmissionDate().getTime());
							
							int daysDuration = Days.daysBetween(join, admission).getDays();
							
							if (daysDuration <  duration){
								status.setCaseStatusId(Claim.CLAIM_REJECT);
								object.setClaimStatus(status);

								remarks = "PRE-EXISTING DIAGNOSIS";

								rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
							}
						}
					}
					
					else if (exclusion != null && exclusion.getExpireDate() != null) {
						Date expireDate = exclusion.getExpireDate();

						DateTime exDt = new DateTime(expireDate);
						DateTime ex = exDt.plusDays(1);

						if (object.getAdmissionDate().before(new Date(ex.getMillis()))) {

							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);

							remarks = "PRE-EXISTING DIAGNOSIS";

							rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
						} else {
							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {

										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
							}
						}
					}
				}
			}
			if (object.getDiagnosis3Id() != null) {
				String[] eqPolicyDiagParam = { "policyId", "deletedStatus", "diagnosisId.diagnosisId" };
				Object[] eqPolicyDiagValue = { policyId, 0, object.getDiagnosis3Id().getDiagnosisId() };

				int totalPolicyExclusion = policyDiagnosisExclusionService.getTotal(null, null, eqPolicyDiagParam,
						eqPolicyDiagValue);

				if (totalPolicyExclusion > 0) {
					Collection<PolicyDiagnosisExclusion> diagExclusion = policyDiagnosisExclusionService.search(null,
							null, eqPolicyDiagParam, eqPolicyDiagValue, 0, totalPolicyExclusion);

					if (diagExclusion != null) {

						Iterator<PolicyDiagnosisExclusion> iteratorEx = diagExclusion.iterator();

						PolicyDiagnosisExclusion exclusion = null;

						if (iteratorEx.hasNext()) {
							exclusion = iteratorEx.next();
						}
						if (exclusion != null) {

							int ageConstraint = -1;
							String param = "";
							int duration = -1;
							
							if (exclusion.getPreExistingDays() != null ){
								duration = exclusion.getPreExistingDays();
							}

							if (exclusion.getAgeConstraint() != null) {
								ageConstraint = exclusion.getAgeConstraint();
							}
							if (exclusion.getAgeParameter() != null) {
								param = exclusion.getAgeParameter();
							}

							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
											
										}
									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
											remarks = "DIAGNOSIS EXCLUSION";
											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}
									}
								}

							} else {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "DIAGNOSIS EXCLUSION";
	
									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									/**
									 * 0000230: [CAR] [PRE EXISTING] Setelah lewat masa Pre Existing harusnya Claim status OPEN
									 */
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}
	
							}
						}
					}
				} else {

					Diagnosis diagnosis =  object.getDiagnosis3Id();
					MemberDiagnosisExclusion exclusion = memberDiagnosisExclusionService.getMemberExclusion(
							diagnosis.getDiagnosisId(), object.getMemberId().getMemberId());

					int ageConstraint = -1;
					String param = "";
					int duration = -1;

					if (exclusion != null) {
						if (exclusion.getAgeConstraint() != null) {
							ageConstraint = exclusion.getAgeConstraint();
						}
						if (exclusion.getAgeParameter() != null) {
							param = exclusion.getAgeParameter();
						}
						duration =  exclusion.getPreExistingDays() == null ? -1 : exclusion.getPreExistingDays();
					}

					if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint == -1) {

						
						
						if (duration > 0){
							Member member = memberService.get(object.getMemberId().getMemberId());
							
							if (member != null && member.getJoinDate() != null){
								DateTime join = new DateTime(member.getJoinDate().getTime());
								DateTime admission = new DateTime(object.getAdmissionDate().getTime());
								
								int daysDuration = Days.daysBetween(join, admission).getDays();
								
								if (daysDuration <  duration){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);
	
									remarks = "PRE-EXISTING DIAGNOSIS";
	
									rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
								}
							}
						}
						else {
						
							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);
	
							remarks = "DIAGNOSIS EXCLUSION";
	
							rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
						}
						
					} else if (exclusion != null && exclusion.getExpireDate() == null && ageConstraint > 0) {

						if (param.equalsIgnoreCase("LT")) {
							if (ageConstraint > memberAge) {
								
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}
						else if (param.equalsIgnoreCase("GT")) {
							if (ageConstraint < memberAge) {
								if (duration == -1){
									status.setCaseStatusId(Claim.CLAIM_REJECT);
									object.setClaimStatus(status);

									remarks = "DIAGNOSIS EXCLUSION";

									rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
								}
								else {
									 
									Member member = memberService.get(object.getMemberId().getMemberId());
									
									if (member != null && member.getJoinDate() != null){
										DateTime join = new DateTime(member.getJoinDate().getTime());
										DateTime admission = new DateTime(object.getAdmissionDate().getTime());
										
										int daysDuration = Days.daysBetween(join, admission).getDays();
										
										if (daysDuration <  duration){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);
			
											remarks = "PRE-EXISTING DIAGNOSIS";
			
											rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
										}
									}
								}

							}
						}

					}
					else if (exclusion != null && exclusion.getPreExistingDays() != null){

						Member member = memberService.get(object.getMemberId().getMemberId());
						
						if (member != null && member.getJoinDate() != null){
							DateTime join = new DateTime(member.getJoinDate().getTime());
							DateTime admission = new DateTime(object.getAdmissionDate().getTime());
							
							int daysDuration = Days.daysBetween(join, admission).getDays();
							
							if (daysDuration <  duration){
								status.setCaseStatusId(Claim.CLAIM_REJECT);
								object.setClaimStatus(status);

								remarks = "PRE-EXISTING DIAGNOSIS";

								rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
							}
						}
					}
					
					else if (exclusion != null && exclusion.getExpireDate() != null) {
						Date expireDate = exclusion.getExpireDate();

						DateTime exDt = new DateTime(expireDate);
						DateTime ex = exDt.plusDays(1);

						if (object.getAdmissionDate().before(new Date(ex.getMillis()))) {

							status.setCaseStatusId(Claim.CLAIM_REJECT);
							object.setClaimStatus(status);

							remarks = "PRE-EXISTING DIAGNOSIS";

							rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
						} else {
							if (ageConstraint > 0) {
								if (param.equalsIgnoreCase("LT")) {
									if (ageConstraint > memberAge) {
										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
								if (param.equalsIgnoreCase("GT")) {
									if (ageConstraint < memberAge) {

										if (duration == -1){
											status.setCaseStatusId(Claim.CLAIM_REJECT);
											object.setClaimStatus(status);

											remarks = "DIAGNOSIS EXCLUSION";

											rejectCategory = RejectCategory.DIAGNOSIS_EXCLUSION;
										}
										else {
											 
											Member member = memberService.get(object.getMemberId().getMemberId());
											
											if (member != null && member.getJoinDate() != null){
												DateTime join = new DateTime(member.getJoinDate().getTime());
												DateTime admission = new DateTime(object.getAdmissionDate().getTime());
												
												int daysDuration = Days.daysBetween(join, admission).getDays();
												
												if (daysDuration <  duration){
													status.setCaseStatusId(Claim.CLAIM_REJECT);
													object.setClaimStatus(status);
					
													remarks = "PRE-EXISTING DIAGNOSIS";
					
													rejectCategory = RejectCategory.PRE_EXISTING_DIAGNOSIS;
												}
											}
										}

									}
								}
							}
						}
					}
				}
			}
			Member member = memberService.get(object.getMemberId().getMemberId());

			if (member.getNoClaimStartDate() != null && member.getNoClaimEndDate() != null) {

				DateTime admPlus1 = new DateTime(object.getAdmissionDate());
				DateTime admMinus1 = new DateTime(object.getAdmissionDate());

				if (new java.sql.Date(admPlus1.getMillis()).after(member.getNoClaimStartDate())
						&& new java.sql.Date(admMinus1.getMillis()).before(member.getNoClaimEndDate())) {

					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "SUBJECT TO NO CLAIM";
					rejectCategory = RejectCategory.SUBJECT_NO_CLAIM;
				}
			}

			if (member.getStatus() != null && (member.getStatus().intValue() == SubscriptionStatus.BLOCKED
					|| member.getStatus().intValue() == SubscriptionStatus.GRACE)) {

				if (member.getSuspendStartDate() != null && member.getSuspendEndDate() != null) {
					DateTime admPlus1 = new DateTime(object.getAdmissionDate());
					DateTime admMinus1 = new DateTime(object.getAdmissionDate());

					if (new java.sql.Date(admPlus1.getMillis()).after(member.getSuspendStartDate())
							&& new java.sql.Date(admMinus1.getMillis()).before(member.getSuspendEndDate())) {
						status.setCaseStatusId(Claim.CLAIM_REJECT);
						object.setClaimStatus(status);

						remarks = "GRACE / SUSPEND PERIODE";

						rejectCategory = RejectCategory.SUSPEND;
					}
				} else if (member.getSuspendStartDate() != null && member.getSuspendEndDate() == null) {
					DateTime admPlus1 = new DateTime(object.getAdmissionDate());

					if (new java.sql.Date(admPlus1.getMillis()).after(member.getSuspendStartDate())) {
						status.setCaseStatusId(Claim.CLAIM_REJECT);
						object.setClaimStatus(status);

						remarks = "GRACE / SUSPEND PERIODE";

						rejectCategory = RejectCategory.SUSPEND;
					}
				} else if (member.getSuspendStartDate() == null && member.getSuspendEndDate() == null) {
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "GRACE / SUSPEND PERIODE";

					rejectCategory = RejectCategory.SUSPEND;
				}
			}

			if (member.getEffectiveDate().after(object.getAdmissionDate())) {
				MemberProduct memberProduct = memberProductService.getMemberProductByDate(member.getMemberId(), object.getCaseCategoryId()
						.getCaseCategoryId(), object.getAdmissionDate(), object.getDischargeDate());
				
				if (memberProduct == null){
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);
	
					remarks = "MEMBER NOT ACTIVE";
	
					rejectCategory = RejectCategory.MEMBER_ACTIVATION;
				}
			}
			if (member.getMemberGroupId() != null) {
				MemberGroup memberGroup = memberGroupService.get(member.getMemberGroupId().getMemberGroupId());

				if (memberGroup != null
						&& memberGroup.getStatus().getStatusId().intValue() == SubscriptionStatus.BLOCKED) {
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "GROUP SUSPEND ";
					rejectCategory = RejectCategory.SUSPEND;
				}
			}
			if (member.getCurrentPolicyId() != null) {
				Policy currentPolicy = policyService.get(member.getCurrentPolicyId().getPolicyId());

				if (currentPolicy != null
						&& currentPolicy.getPolicyStatus().getStatusId().intValue() == SubscriptionStatus.BLOCKED) {
					status.setCaseStatusId(Claim.CLAIM_REJECT);
					object.setClaimStatus(status);

					remarks = "POLICY SUSPEND ";
					rejectCategory = RejectCategory.SUSPEND;
				}
			}

			if ((member.getResignedDate() != null && (member.getResignedDate().before(object.getAdmissionDate())
					|| member.getResignedDate().equals(object.getAdmissionDate())))
					|| (member.getExpireDate().before(object.getAdmissionDate())
							|| member.getExpireDate().equals(object.getAdmissionDate()))) {

				status.setCaseStatusId(Claim.CLAIM_REJECT);
				object.setClaimStatus(status);

				remarks = "MEMBER TERMINATED";

				rejectCategory = RejectCategory.MEMBER_EXPIRED;
			}

			if (claimItem != null) {
				Iterator iterator = claimItem.iterator();

				Collection<ClaimItem> toBeDeleted = new Vector<ClaimItem>();

				while (iterator.hasNext()) {
					ClaimItem ci = (ClaimItem) iterator.next();

					boolean isExist = claimItemService.getIsClaimItemExist(ci.getClaimId().getClaimId(),
							ci.getItemId().getItemId());

					if (isExist) {
						ClaimItem exist = claimItemService.getClaimItem(ci.getClaimId().getClaimId(),
								ci.getItemId().getItemId());

						if (exist != null) {
							exist.setClaimItemAmount(ci.getClaimItemAmount());
							exist.setClaimItemValue(ci.getClaimItemValue());
							exist.setClaimItemApprovedValue(ci.getClaimItemApprovedValue());
							exist.setClaimItemRemarks(ci.getClaimItemRemarks());
							exist.setClaimItemDescription(ci.getClaimItemDescription());
							exist.setPrePaidExcess(ci.getPrePaidExcess());
							exist.setRefund(ci.getRefund());

							ci = exist;

						}
					}
					ci.setDeletedStatus(0);
					ci.setClaimItemStatus(status);

					if (object.getExchangeRate() != null) {
						double exchangeRate = object.getExchangeRate().doubleValue();
						double itemCharge = exchangeRate * ci.getClaimItemValue();
						ci.setProductCurrencyChargeValue(itemCharge);
						ci.setExchangeRate(exchangeRate);
						ci.setExchangeRateDate(object.getExchangeRateDate());
					}

					if (status.getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {
						ci.setBenefitCheckRemarks(remarks);
					}

					if (isExist) {
						Claim claim = get(claimId);

						claimItemService.update(ci, actionUser);
					} else {
						Claim claim = get(claimId);

						claimItemService.create(ci, actionUser);
					}
				}

				Collection<ClaimItem> existingItem = claimItemService.getClaimItem(object.getClaimId());
				Iterator<ClaimItem> existingIterator = existingItem.iterator();

				while (existingIterator.hasNext()) {

					ClaimItem currentItem = existingIterator.next();

					if (currentItem != null) {
						boolean isExist = false;

						Iterator<ClaimItem> updateIterator = claimItem.iterator();
						while (updateIterator.hasNext()) {
							ClaimItem ci = updateIterator.next();

							if (ci != null) {
								if (ci.getItemId().getItemId().intValue() == currentItem.getItemId().getItemId()
										.intValue()) {
									isExist = true;
									break;
								}
							}
						}
						if (!isExist) {
							toBeDeleted.add(currentItem);
						}
					}
				}

				for (Iterator iterator2 = toBeDeleted.iterator(); iterator2.hasNext();) {

					ClaimItem claimItem2 = (ClaimItem) iterator2.next();

					if (claimItem2 != null) {
						claimItemService.delete(claimItem2, actionUser);
					}

				}

			}

			String[] eqParam = { "claimId.claimId", "deletedStatus" };
			Object[] eqValue = { object.getClaimId(), new Integer(0) };

			int totalClaimItem = claimItemService.getTotal(null, null, eqParam, eqValue);
			Collection<ClaimItem> claimItems = claimItemService.getClaimItem(object.getClaimId());

			if (claimItems != null) {
				Iterator<ClaimItem> ciIterator = claimItems.iterator();

				while (ciIterator.hasNext()) {

					ClaimItem ci = ciIterator.next();
					claimCharge += ci.getClaimItemValue();
				}
			}

			if (remarks != null && remarks.equalsIgnoreCase("")) {
				object.setCheckerRemarks(remarks);
			}

			object.setClaimStatus(status);

			object.setTotalItem(totalClaimItem);
			object.setPembulatan(pembulatan);
			object.setClaimChargeValue(claimCharge);

			if (status.getCaseStatusId().intValue() == Claim.CLAIM_REJECT && rejectCategory != null) {

				object.setRejectionRemarks(remarks);

				int numberCounter = 0;
				RejectCategory category = new RejectCategory();
				category.setRejectCategoryId(rejectCategory);

				RejectedClaim rejectClaim = new RejectedClaim();
				rejectClaim.setClaimId(object);
				rejectClaim.setDescription(remarks);
				rejectClaim.setRejectionSubject(remarks);
				rejectClaim.setRejectionCategory(category);
				rejectClaim.setDeletedStatus(0);
				rejectClaim.setCreatedBy(actionUser.getUser().getUsername());
				rejectClaim.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));

				Configuration configuration = configurationService
						.getClientConfiguration(object.getMemberId().getClientId().getClientId());

				if (configuration == null) {
					configuration = configurationService.getSystemConfiguration();
				}

				Integer isUsingSequence = configuration.getIsUsingSequenceNumber();

				if (isUsingSequence != null && isUsingSequence.intValue() == 1) {
					String seqSQL = configuration.getClaimRejectSeqName();

					Session session = rejectClaimDao.getRejectionSession();

					if (session != null) {
						SQLQuery q = session.createSQLQuery(seqSQL);

						List<Object> list = q.list();

						if (list != null && !list.isEmpty()) {
							Iterator<Object> iterator = list.iterator();

							if (iterator.hasNext()) {
								Object nextval = iterator.next();

								if (nextval != null) {
									BigInteger val = (BigInteger) nextval;
									numberCounter = val.intValue();
								}
							}
						}
					}
				} else {
					numberCounter = configuration.getClaimRejectionNumber();
				}

				DateTime dateTime = new DateTime();

				int month = dateTime.getMonthOfYear();
				String monthStr = "";
				if (month < 10) {
					monthStr = "0" + month;
				} else {
					monthStr = month + "";
				}

				String number = configuration.getClaimRejectionNumberTemplate();
				String counter = numberCounter + "";

				number = number.replace("${counter}", counter);
				number = number.replace("${month}", monthStr);
				number = number.replace("${year}", dateTime.getYear() + "");

				rejectClaim.setRejectionNumber(number);

				rejectClaimDao.create(rejectClaim);

			}
			update(object, actionUser);

			recordHistory(object, "CREATE CLAIM", "", actionUser);

		}

		return object;

	}

	public Collection<Claim> getMemberClaim(Integer memberId) {
		Collection<Claim> result = null;

		try {
			String[] eqParam = { "memberId.memberId", "deletedStatus" };
			Object[] eqValue = { memberId, Integer.valueOf(0) };
			String[] columnOrder = { "claimDate" };

			int total = getTotal(null, null, eqParam, eqValue);
			result = search(null, null, eqParam, eqValue, true, columnOrder, null, 0, total);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private double getTotalMemberApprovedValue(Collection<Claim> claims) {
		double result = 0;

		try {
			if (claims != null) {
				Iterator<Claim> claimIterator = claims.iterator();
				Claim claim = null;

				while (claimIterator.hasNext()) {
					claim = claimIterator.next();

					result += claim.getClaimApprovedValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean synchronizeClaimProduct(Claim claim, ActionUser actionUser) {
		boolean result = false;

		try {
			MemberProduct product = memberProductService.getMemberActiveProduct(claim.getMemberId().getMemberId(),
					claim.getCaseCategoryId().getCaseCategoryId());

			if (product != null) {
				double annualLimit = product.getAnnualBenefit();
				double benefitUsage = 0;
				double excessCharge = 0;
				double exGratia = 0;
				double actualBenefitLimit = 0;
				double benefitUsagePercentage = 0;
				String lastClaimNumber = "";
				java.sql.Date lastClaimDate = null;
				Collection<Claim> claims = getMemberClaim(claim.getMemberId().getMemberId());

				if (claims != null) {
					Iterator<Claim> claimIterator = claims.iterator();
					int index = 0;
					int totalClaim = claims.size();
					while (claimIterator.hasNext()) {
						Claim theClaim = claimIterator.next();

						if (theClaim != null) {
							benefitUsage += theClaim.getClaimApprovedValue();
							if (theClaim.getClaimTypeId().getClaimTypeId() == ClaimType.CASHLESS
									|| theClaim.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS) {
								excessCharge += theClaim.getClaimExcessValue();
							}
							exGratia += theClaim.getExGratiaValue();

							index += 1;

							if (index == totalClaim) {
								lastClaimNumber = theClaim.getClaimNumber();
								lastClaimDate = theClaim.getClaimDate();
							}
						}

					}
					product.setBenefitUsage(benefitUsage);
					product.setBenefitExcessed(excessCharge);
					product.setBenefitExGratia(exGratia);

					if (annualLimit > 0) {
						benefitUsagePercentage = (benefitUsage / annualLimit) * 100;
						actualBenefitLimit = annualLimit - benefitUsage;

						product.setBenefitUsagePercentage(benefitUsagePercentage);
						product.setActualBenefitLimit(actualBenefitLimit);
					}
					product.setLastClaimDate(lastClaimDate);
					product.setLastClaimNumber(lastClaimNumber);

					memberProductService.update(product, actionUser);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean synchronizeClaim(Integer claimId, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			Claim claim = get(claimId);

			if (claim != null) {
				Collection<ClaimItem> claimItems = claimItemService.getClaimItem(claimId);

				if (claimItems != null) {

					double claimCharge = 0;
					double claimApproved = 0;
					double excessCharge = 0;
					double claimExGratia = 0;

					ClaimItem claimItem = null;

					Iterator<ClaimItem> itemIterator = claimItems.iterator();

					while (itemIterator.hasNext()) {
						claimItem = itemIterator.next();
						if (claimItem != null) {

							claimCharge += claimItem.getClaimItemValue();
							claimApproved += claimItem.getClaimItemApprovedValue();
							excessCharge += claimItem.getExcessValue();
							claimExGratia += claimItem.getClaimExGratia();

						}
					}

					claim.setClaimChargeValue(claimCharge);
					claim.setClaimApprovedValue(claimApproved);
					claim.setClaimExcessValue(excessCharge);
					claim.setClaimExcessValue(claimExGratia);

					if (excessCharge > 0 && (claim.getClaimTypeId().getClaimTypeId() == ClaimType.CASHLESS
							|| claim.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS)) {
						// check apakah ada excess charge
						// kalo tidak ada berarti excessnya baru muncul nih

						ExcessCharge excess = excessChargeService.getClaimExcess(claim.getClaimId());

						if (excess != null) {
						} else {
							excess = new ExcessCharge();
							excess.setClaimId(claim);
							excess.setExcessChargeValue(excessCharge);
							excess.setMemberId(claim.getMemberId());
							excess.setRemarks("synchronized claim generated excess");

							excessChargeService.create(excess, actionUser);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteClaimITem(ClaimItem claimItem, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		try {

			claimItem.setDeletedStatus(Integer.valueOf(1));
			claimItem.setDeletedTime(new Timestamp(System.currentTimeMillis()));
			claimItem.setDeletedBy(actionUser.getUser().getUsername());

			ClaimItem item = claimItemService.update(claimItem, actionUser);

			ClaimItem destinationItem = item.clone();

			if (item != null) {

				Claim claim = get(claimItem.getClaimId().getClaimId());

				/*
				 * create historical version of claim_item create new instance
				 * with the same key
				 */

				double claimCharge = 0;

				if (claim != null) {
					String[] eqClaimParam = { "deletedStatus", "claimId.claimId" };
					Object[] eqClaimValue = { Integer.valueOf(0), claim.getClaimId() };

					int total = claimItemService.getTotal(null, null, eqClaimParam, eqClaimValue);

					Collection<ClaimItem> citems = claimItemService.search(null, null, eqClaimParam, eqClaimValue, 0,
							total);

					if (citems != null) {
						Iterator<ClaimItem> cIterator = citems.iterator();

						if (cIterator != null) {
							while (cIterator.hasNext()) {
								ClaimItem citem = cIterator.next();

								if (citem != null) {
									claimCharge += citem.getClaimItemValue();
								}
							}
						}
					}
				}

				claim.setClaimChargeValue(claimCharge);
				update(claim, actionUser);

				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean finalizeCorrection(Claim object, Collection<ClaimItem> items, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;

		try {

			if (object != null && items != null) {
				// check if object cashless or reimburse.

				double claimTotal = 0;
				double claimApproved = 0;
				double excess = 0;

				Iterator<ClaimItem> iterator = items.iterator();

				while (iterator.hasNext()) {
					ClaimItem ci = iterator.next();

					if (ci != null) {

						double ciVal = ci.getClaimItemValue() == null ? 0 : ci.getClaimItemValue().doubleValue();
						double ciApprove = ci.getClaimItemApprovedValue() == null ? 0
								: ci.getClaimItemApprovedValue().doubleValue();
						double excesClaim = ci.getExcessValue() == null ? 0 : ci.getExcessValue().doubleValue();

						claimTotal += ciVal;
						claimApproved += ciApprove;
						excess += excesClaim;

						ClaimItem claimItem = claimItemService.getClaimItem(object.getClaimId(),
								ci.getItemId().getItemId());

						if (claimItem != null) {

							claimItem.setClaimItemAmount(ci.getClaimItemAmount());
							claimItem.setClaimItemApprovedValue(ciApprove);
							claimItem.setExcessValue(excesClaim);
							claimItem.setBenefitCheckRemarks(ci.getBenefitCheckRemarks());

							claimItemService.update(claimItem, actionUser);
						} else {
							ci.setClaimId(object);
							claimItemService.create(ci, actionUser);
						}
					}
				}

				if (object.getClaimTypeId().getClaimTypeId() == ClaimType.CASHLESS
						|| object.getClaimTypeId().getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS) {
					if (excess > 0) {
						ExcessCharge excessCharge = excessChargeService.getClaimExcess(object.getClaimId());

						if (excessCharge != null) {
							if (excessCharge.getExcessChargeValue().doubleValue() != excess) {
								excessCharge.setExcessChargeValue(excess);
								excessChargeService.update(excessCharge, actionUser);
							}
						} else {

							excessCharge = new ExcessCharge();

							excessCharge.setClaimId(object);
							excessCharge.setExcessChargeValue(excess);
							excessCharge.setMemberId(object.getMemberId());
							excessCharge.setRemarks("synchronize - correction");

							excessChargeService.create(excessCharge, actionUser);
							// excessCharge.setExcessChargeStatus(value)
						}
					}
				}

				object.setClaimApprovedValue(claimApproved);
				object.setClaimChargeValue(claimTotal);
				object.setClaimExcessValue(excess);

				update(object, actionUser);

				// refresh batch claim
				String[] param = { "batchClaimId.batchClaimId", "deletedStatus" };
				Object[] value = { object.getBatchClaimId().getBatchClaimId(), Integer.valueOf(0) };
				int totalClaim = getTotal(null, null, param, value);

				Collection<Claim> claims = search(null, null, param, value, 0, totalClaim);

				double totalClaimValue = 0.0;
				double totalApprovedValue = 0.0;
				double excessValue = 0.0;

				if (claims != null) {
					Iterator<Claim> claimIterator = claims.iterator();

					while (claimIterator.hasNext()) {

						Claim cl = claimIterator.next();

						totalClaimValue += cl.getClaimChargeValue() == null ? 0
								: cl.getClaimChargeValue().doubleValue();
						totalApprovedValue += cl.getClaimApprovedValue() == null ? 0
								: cl.getClaimApprovedValue().doubleValue();
						excessValue += cl.getClaimExcessValue() == null ? 0 : cl.getClaimExcessValue().doubleValue();
					}

					BatchClaim batchClaim = batchClaimService.get(object.getBatchClaimId().getBatchClaimId());

					if (batchClaim != null) {
						batchClaim.setTotalClaim(totalClaim);
						batchClaim.setBatchClaimInitialValue(totalClaimValue);
						batchClaim.setBatchClaimFinalValue(totalApprovedValue);
						batchClaim.setBatchExcessValue(excessValue);

						batchClaimService.update(batchClaim, actionUser);

						String[] paramFinance = { "batchClaimId.batchClaimId", "deletedStatus" };
						Object[] valueFinance = { batchClaim.getBatchClaimId(), Integer.valueOf(0) };

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void convertClaimFromBatch(Serializable batchClaimId, Integer fromCaseType, Integer toCaseType)
			throws Exception {
		// TODO Auto-generated method stub
	}

	public Collection<ClaimDto> getMemberClaimList(Integer memberId, Date startDate, Date endDate) {

		Collection<ClaimDto> result = new Vector<ClaimDto>();

		try {
			String[] eqParam = { "memberId.memberId" };
			Object[] eqValue = { memberId };
			String[] required = {};

			Object[] start = { startDate };
			Object[] end = { endDate };
			String[] between = { "claimDate" };
			int total = getTotal(null, null, eqParam, eqValue, "claimDate", startDate, endDate);
			Collection<Claim> list = search(null, null, eqParam, eqValue, between, start, end, required, 0, total);

			if (list != null) {
				for (Claim claim : list) {
					ClaimDto dto = claim.exportDto();

					Collection<ClaimItemDto> ciDto = claimItemService.getClaimItemDto(claim.getClaimId());
					dto.setClaimItemList((Vector) ciDto);
					result.add(dto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// class-

	public String approveCheckItemBulkEDC(Integer claimId, Collection<ClaimItem> claimItems, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub
		String result = "";

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean attachSwipeClaim(Integer batchClaimId, Integer[] swipeClaimId, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {

			BatchClaim batchClaim = batchClaimService.get(batchClaimId);
			int incompleteClaim = batchClaim.getIncompleteClaim().intValue();

			if (batchClaim != null && swipeClaimId != null) {
				for (int i = 0; i < swipeClaimId.length; i++) {

					Claim claim = get(swipeClaimId[i]);
					if (claim != null) {
						claim.setBatchClaimId(batchClaim);
						claim.setIsReported(Integer.valueOf(1));
						claim.setIsEDCBatchAssigned(Integer.valueOf(1));
						claim.setClaimDate(batchClaim.getBatchClaimDate());

						claimDao.update(claim);
						incompleteClaim = incompleteClaim - 1;
						System.out.println("incompleteClaim " + incompleteClaim);

						Provider provider = providerService.get(claim.getProviderId().getProviderId());
						claim.setProviderId(provider);

						recordHistory(claim, "ATTACH EDC CLAIM", "", actionUser);

					}
				}

				batchClaim.setIncompleteClaim(incompleteClaim);

				if (incompleteClaim == 0) {
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(Integer.valueOf(BatchClaim.COMPLETE));
					batchClaim.setBatchClaimStatus(status);
				}
				batchClaimService.update(batchClaim, actionUser);

			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getTotalOpenCaseConvertedClaim() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				String q = "SELECT count(c) FROM Claim c WHERE c.deletedStatus = 0 AND c.batchClaimId.batchClaimId "
						+ "is null AND c.caseId.caseId is not null";
				Query query = session.createQuery(q);

				Number num = (Number) query.uniqueResult();
				result = num.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalOpenClaim() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				String q = "SELECT count(c) FROM Claim c WHERE c.deletedStatus = 0 AND c.batchClaimId.batchClaimId is not null "
						+ "AND c.claimStatus.caseStatusId = :status";

				Query query = session.createQuery(q);
				query.setInteger("status", Claim.CLAIM_OPEN);

				Number num = (Number) query.uniqueResult();
				result = num.intValue();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalOpenEdcClaim() throws Exception {
		int result = 0;

		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				String q = "SELECT count(c) FROM Claim c WHERE c.deletedStatus = 0 AND c.batchClaimId.batchClaimId is null "
						+ " AND c.caseId.caseId is null AND c.edcTraceNumber is not null AND c.claimStatus.caseStatusId = :checked AND c.claimStatus.caseStatusId <> :void";

				Query query = session.createQuery(q);
				query.setInteger("void", CaseStatus.VOID);
				query.setInteger("checked", Claim.CLAIM_CHECKED);

				Number num = (Number) query.uniqueResult();
				result = num.intValue();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalVerifiedClaim() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				String q = "SELECT count(c) FROM Claim c WHERE c.deletedStatus = 0 AND c.batchClaimId.batchClaimId is not null "
						+ "AND (c.claimStatus.caseStatusId = :status OR c.claimStatus.caseStatusId = :status2)";

				Query query = session.createQuery(q);
				query.setInteger("status", Claim.CLAIM_CHECKED);
				query.setInteger("status2", Claim.CLAIM_APPROVED);

				Number num = (Number) query.uniqueResult();
				result = num.intValue();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection<Object> getPayableUploadedClaimList(Integer paymentId) throws Exception {
		Collection<Object> result = new Vector<Object>();

		Session session = claimDao.getClaimSession();

		if (session != null) {
			String q = "SELECT c.claimId FROM Claim c WHERE c.deletedStatus = 0 "
					+ "AND c.claimStatus.caseStatusId = :status  AND c.paymentInstallmentId IS NULL"
					+ " AND c.paymentId.paymentId = :paymentId AND c.clientPaymentVoucherNumber IS NOT NULL";

			Query query = session.createQuery(q);
			query.setInteger("status", Claim.CLAIM_PAYMENT_ISSUED);
			query.setInteger("paymentId", paymentId);

			result = query.list();

		}
		return result;
	}

	public Collection<Claim> getUnpaidMemberReimbursementClaim(Integer memberId) throws Exception {
		Collection<Claim> result = new Vector<Claim>();
		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				String q = "SELECT c FROM Claim c WHERE c.deletedStatus = 0 "
						+ "AND (c.claimStatus.caseStatusId <> :status ) AND c.claimTypeId.claimTypeId = :type"
						+ " AND c.memberId.parentMember.memberId = :memberId";

				Query query = session.createQuery(q);
				query.setInteger("status", Claim.CLAIM_PAID);
				query.setInteger("type", ClaimType.REIMBURESEMENT);
				query.setInteger("memberId", memberId);

				List objectList = query.list();

				if (objectList != null) {
					for (Iterator iterator = objectList.iterator(); iterator.hasNext();) {

						Object object = (Object) iterator.next();

						if (object instanceof Claim) {
							result.add((Claim) object);
						}

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalUnpaidMemberReimbursementClaim(Integer memberId) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;

		try {
			Session session = claimDao.getClaimSession();

			if (session != null) {
				String q = "SELECT count(c) FROM Claim c WHERE c.deletedStatus = 0 "
						+ "AND (c.claimStatus.caseStatusId <> :status ) AND c.claimTypeId.claimTypeId = :type"
						+ " AND c.memberId.parentMember.memberId = :memberId";

				Query query = session.createQuery(q);
				query.setInteger("status", Claim.CLAIM_PAID);
				query.setInteger("type", ClaimType.REIMBURESEMENT);
				query.setInteger("memberId", memberId);

				Number num = (Number) query.uniqueResult();
				result = num.intValue();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Claim createClaimRKI(Claim object, Collection<ClaimItem> claimItems, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		Claim claimResult = createClaimItemEDC(object, claimItems, 0.0, actionUser);

		if (claimResult != null) {

			System.out.println("CLAIM CREATED : " + claimResult.getClaimNumber());

			Collection<ClaimItem> claimCheckedItem = claimItemService.getBenefitCheckItem(claimResult);

			Collection<ClaimItem> approvedItem = new Vector<ClaimItem>();

			if (claimCheckedItem != null) {
				for (Iterator iterator2 = claimCheckedItem.iterator(); iterator2.hasNext();) {

					ClaimItem claimItem = (ClaimItem) iterator2.next();
					if (claimItem != null) {
						claimItem.setClaimItemApprovedValue(claimItem.getClaimItemCoveredValue());

						approvedItem.add(claimItem);
					}
				}
			}

			String res = approveCheckItemBulk(claimResult.getClaimId(), approvedItem, actionUser);

		}
		if (claimResult != null) {
			String[] required = { "Claim.MemberId", "Claim.MemberId.MemberGroupId" };
			claimResult = get(claimResult.getClaimId());

			Member member = memberService.get(claimResult.getMemberId().getMemberId());

			if (claimResult.getClaimApprovedValue() < claimResult.getClaimChargeValue()) {
				if (member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().equalsIgnoreCase("")) {
					boolean cobClaimStatus = createCobClaim(claimResult, member, claimItems, actionUser);
				}
			}

			if (claimResult.getCaseId() != null) {
				Case theCase = caseDao.get(claimResult.getCaseId().getCaseId());
				CaseStatus beforeStatus = theCase.getCaseStatusId();

				if (claimResult.getDiagnosisId() != null) {
					theCase.setDiagnosis1Id(claimResult.getDiagnosisId());
				}
				if (claimResult.getDiagnosis2Id() != null) {
					theCase.setDiagnosis2Id(claimResult.getDiagnosis2Id());
				}
				if (claimResult.getDiagnosis3Id() != null) {
					theCase.setDiagnosis3Id(claimResult.getDiagnosis3Id());
				}

				if (claimResult.getClaimChargeValue() != null) {
					theCase.setCaseClaimValue(claimResult.getClaimChargeValue());
				}
				if (claimResult.getClaimApprovedValue() != null) {
					theCase.setCaseApprovedValue(claimResult.getClaimApprovedValue());
				}
				if (claimResult.getClaimExcessValue() != null) {
					theCase.setCaseExcessValue(claimResult.getClaimExcessValue());
				}
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(CaseStatus.CASE_FINALIZED);

				if (claimResult.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {
					status.setCaseStatusId(Case.CASE_REJECT);
				}
				
				/*
				 * afwan March 3rd, 2016 - add claimId into case
				 */
				theCase.setClaimId(object);
				theCase.setCaseStatusId(status);

				if (actionUser != null) {
					theCase.setModifiedBy(actionUser.getUser().getUsername());

				}
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

				if (claimResult.getHasComplication() != null) {
					theCase.setHasComplication(claimResult.getHasComplication());

					Provider provider = null;

					if (claimResult.getProviderId() != null) {
						provider = providerService.get(claimResult.getProviderId().getProviderId());
					}

					String[] eqParam = { "diagnosisId.diagnosisId", "deletedStatus", "treatmentRiskId.treatmentRiskId",
							"providerTypeId.providerTypeId" };
					Object[] eqValue = { claimResult.getDiagnosisId().getDiagnosisId(), Integer.valueOf(0),
							object.getHasComplication(), (provider.getProviderTypeId() != null
									? provider.getProviderTypeId().getProviderTypeId() : Integer.valueOf(1)) };

					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = providerTypeDiagnosisTreatmentService
							.search(null, null, eqParam, eqValue, 0, 1);

					if (inaCBGList != null && inaCBGList.size() > 0) {
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList.iterator();

						if (iterator.hasNext()) {
							ProviderTypeDiagnosisTreatment inaCBG = iterator.next();
							if (inaCBG.getTreatmentRate() != null) {
								theCase.setInaCbgTreatmentRate(inaCBG.getTreatmentRate());
							}

							// For Long of Stay by Diagnosis
							if (inaCBG.getAlos() != null) {
								theCase.setInaCbgAlos(inaCBG.getAlos());
							}
						}
					} else {
						theCase.setInaCbgTreatmentRate(0.0);
					}
				}
				
				if(theCase.getClaimId() == null){						
					theCase.setClaimId(claimResult);
				}

				caseDao.update(theCase);

				String actionBy = actionUser.getUser().getUsername();
				CaseHistory history = new CaseHistory();
				history.setActionBy(actionBy);
				history.setCreatedBy(actionBy);
				history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setBeforeStatus(beforeStatus);
				history.setAfterStatus(status);

				history.setBeforeActionData("");
				history.setActionType("FINALIZE CASE");
				history.setAfterActionData(theCase.toHistoryString());
				history.setCaseId(theCase);

				if (theCase.getDiagnosis1Code() != null) {
					history.setDiagnosisCode(theCase.getDiagnosis1Code());
				}
				if (theCase.getDiagnosis2Code() != null) {
					history.setDiagnosisCode(theCase.getDiagnosis1Code());
				}
				if (theCase.getDiagnosis3Code() != null) {
					history.setDiagnosisCode(theCase.getDiagnosis1Code());
				}
				if (theCase.getProviderName() != null) {
					history.setProviderName(theCase.getProviderName());
				}
				if (theCase.getCaseNumber() != null) {
					history.setCaseNumber(theCase.getCaseNumber());
				}
				if (theCase.getMemberName() != null) {
					history.setMemberName(theCase.getMemberName());
				}
				if (theCase.getMemberNumber() != null) {
					history.setMemberNumber(theCase.getMemberNumber());
				}
				if (theCase.getCaseCategoryId() != null) {
					history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
				}
				CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
				if (previousHistory != null && previousHistory.getHistoryTime() != null) {
					Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()),
							new DateTime(System.currentTimeMillis()));

					int duration = second.getSeconds();
					history.setDuration(duration);

					LocalTime local = new LocalTime(0, 0);
					local = local.plusSeconds(duration);
					String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);

					history.setDurationString(output);
				}

				caseHistoryService.create(history, actionUser);

			}
		}
		return claimResult;
	}

	private boolean createCobClaim(Claim originalClaim, Member member, Collection<ClaimItem> claimItems,
			ActionUser actionUser) {
		boolean result = false;

		try {
			Claim cobClaim = null;

			/**
			 * String[] eqParamRef =
			 * {"cobClaimReferenceId.claimId","deletedStatus"}; Object[]
			 * eqValueRef = {originalClaim.getClaimId(),0};
			 * 
			 * 
			 * 
			 * Collection<Claim> cobList =
			 * search(null,null,eqParamRef,eqValueRef,0,1); if (cobList !=
			 * null){ Iterator<Claim> iterator = cobList.iterator();
			 * 
			 * if (iterator.hasNext()){ cobClaim = iterator.next(); } }
			 */

			Session memberSession = memberService.getMemberSession();
			if (memberSession != null) {
				Query q = memberSession.createQuery("SELECT m FROM Member m WHERE m.currentCardNumber = :num "
						+ "AND m.status = :stat AND m.deletedStatus = 0");

				q.setString("num", member.getLinkedCardNumber());
				q.setInteger("stat", SubscriptionStatus.ACTIVE);

				List<Member> memberList = q.list();

				if (memberList != null && memberList.size() > 0) {
					Iterator<Member> memberIterator = memberList.iterator();

					if (memberIterator.hasNext()) {

						Member linkedMember = memberIterator.next();

						if (cobClaim == null) {
							cobClaim = new Claim();
							cobClaim.setMemberId(linkedMember);
							cobClaim.setCobClaimReferenceId(originalClaim);

							if (originalClaim.getDiagnosisId() != null) {
								cobClaim.setDiagnosisId(originalClaim.getDiagnosisId());
							}
							if (originalClaim.getDiagnosis2Id() != null) {
								cobClaim.setDiagnosis2Id(originalClaim.getDiagnosis2Id());
							}
							if (originalClaim.getDiagnosis3Id() != null) {
								cobClaim.setDiagnosis3Id(originalClaim.getDiagnosis3Id());
							}

							cobClaim.setCaseCategoryId(originalClaim.getCaseCategoryId());
							if (originalClaim.getCaseId() != null) {
								cobClaim.setCaseId(originalClaim.getCaseId());
							}
							cobClaim.setAdmissionDate(originalClaim.getAdmissionDate());
							cobClaim.setDischargeDate(originalClaim.getDischargeDate());

							if (originalClaim.getProviderId() != null) {
								cobClaim.setProviderId(originalClaim.getProviderId());
							}
							cobClaim.setProviderName(originalClaim.getProviderName());

							cobClaim.setTipe(originalClaim.getTipe());
							cobClaim.setClaimTypeId(originalClaim.getClaimTypeId());
							cobClaim.setExchangeRateId(originalClaim.getExchangeRateId());
							cobClaim.setExchangeRate(originalClaim.getExchangeRate());
							cobClaim.setExchangeRateDate(originalClaim.getExchangeRateDate());
							cobClaim.setEdcClaimNumber(originalClaim.getEdcClaimNumber());
							cobClaim.setEdcTraceNumber(originalClaim.getEdcTraceNumber());

							Collection<ClaimItem> cobClaimItem = new Vector<ClaimItem>();

							if (claimItems != null) {
								for (Iterator iterator = claimItems.iterator(); iterator.hasNext();) {

									ClaimItem claimItem = (ClaimItem) iterator.next();

									if (claimItem != null
											&& claimItem.getClaimItemValue() > claimItem.getClaimItemApprovedValue()) {
										ClaimItem ci = new ClaimItem();
										ci.setClaimItemAmount(claimItem.getClaimItemAmount());
										ci.setClaimItemValue(
												claimItem.getClaimItemValue() - claimItem.getClaimItemApprovedValue());
										ci.setItemId(claimItem.getItemId());
										ci.setDeletedStatus(0);
										ci.setClaimItemDescription("[CoB Linked Card]");

										cobClaimItem.add(ci);
									}
								}
							}

							Claim claimResult = createClaimItemEDC(cobClaim, cobClaimItem, 0.0, actionUser);

							if (claimResult != null) {

								System.out.println("CLAIM CREATED : " + claimResult.getClaimNumber());

								Collection<ClaimItem> claimCheckedItem = claimItemService
										.getBenefitCheckItem(claimResult);

								Collection<ClaimItem> approvedItem = new Vector<ClaimItem>();

								if (claimCheckedItem != null) {
									for (Iterator iterator2 = claimCheckedItem.iterator(); iterator2.hasNext();) {

										ClaimItem claimItem = (ClaimItem) iterator2.next();
										if (claimItem != null) {
											claimItem.setClaimItemApprovedValue(claimItem.getClaimItemCoveredValue());
											approvedItem.add(claimItem);
										}
									}
								}
								String res = approveCheckItemBulk(claimResult.getClaimId(), approvedItem, actionUser);

							}

							Claim resultClaim = get(claimResult.getClaimId());

							if (resultClaim != null) {

							}
						} else {

							Collection<ClaimItem> claimCheckedItem = claimItemService
									.getClaimItem(originalClaim.getClaimId());

							if (claimCheckedItem != null) {
								for (Iterator iterator2 = claimCheckedItem.iterator(); iterator2.hasNext();) {

									ClaimItem claimItem = (ClaimItem) iterator2.next();

									if (claimItem != null) {

										ClaimItem origClaimItem = claimItemService.getClaimItem(cobClaim.getClaimId(),
												claimItem.getItemId().getItemId());

										if (origClaimItem != null) {
											origClaimItem.setClaimItemValue(claimItem.getClaimItemValue());
											origClaimItem.setClaimItemApprovedValue(0.0);
											origClaimItem.setClaimItemDescription("[CoB - Linked Card");
											origClaimItem.setBenefitCheckRemarks("[CoB - Linked Card]");

											claimItemService.update(origClaimItem, actionUser);
										} else {
											origClaimItem = new ClaimItem();
											origClaimItem.setClaimId(cobClaim);
											origClaimItem.setItemId(claimItem.getItemId());
											origClaimItem.setClaimItemAmount(claimItem.getClaimItemValue());
											origClaimItem.setClaimItemApprovedValue(0.0);

											origClaimItem.setClaimItemDescription("[CoB - Linked Card");
											origClaimItem.setBenefitCheckRemarks("[CoB - Linked Card]");
											origClaimItem.setDeletedStatus(0);

											origClaimItem.setClaimItemStatus(claimItem.getClaimItemStatus());

											claimItemService.create(origClaimItem, actionUser);
										}
									}
								}
							}

							Collection<ClaimItem> cobClaimCheckedItem = claimItemService.getBenefitCheckItem(cobClaim);

							Collection<ClaimItem> approvedItem = new Vector<ClaimItem>();

							if (cobClaimCheckedItem != null) {
								for (Iterator iterator2 = cobClaimCheckedItem.iterator(); iterator2.hasNext();) {

									ClaimItem claimItem = (ClaimItem) iterator2.next();
									if (claimItem != null) {
										claimItem.setClaimItemApprovedValue(claimItem.getClaimItemCoveredValue());
										approvedItem.add(claimItem);
									}
								}
							}

							String res = approveCheckItemBulk(cobClaim.getClaimId(), approvedItem, actionUser);
						}
						// synchronize parent cob claim
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer getClaimDisabilityDiagnosis(Integer diagnosisId, Integer caseCategoryId, Integer memberId,
			Date minDate, Date maxDate) throws Exception {
		// TODO Auto-generated method stub
		Integer result = null;

		try {
			String[] eqParam = { "caseCategoryId.caseCategoryId", "diagnosisId.diagnosisId", "memberId.memberId",
					"deletedStatus" };
			Object[] eqValue = { caseCategoryId, diagnosisId, memberId, Integer.valueOf(0) };

			result = getTotal(null, null, eqParam, eqValue, "admissionDate", minDate, maxDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean voidClaim(Integer claimId, String traceNumber, String notes, ActionUser actionUser)
			throws Exception {
		boolean result = false;

		try {

			System.out.println("[VOID] GETTING CLAIM");
			
//			openClaim(claimId, actionUser);
			
			String[] required = { "ProviderId.ProviderId" };
			Claim claim = get(claimId, required);

			if (claim != null) {

				System.out.println("[VOID] CLAIM AVAILABLE");
				Collection<ClaimItem> claimItems = claimItemService.getClaimItem(claimId);

				if (claimItems != null) {
					Iterator<ClaimItem> iterator = claimItems.iterator();

					while (iterator.hasNext()) {
						ClaimItem claimItem = iterator.next();
						claimItemService.voidClaimItem(claimItem, actionUser);
					}
				}

				ExcessCharge excess = excessChargeService.getClaimExcess(claimId);

				if (excess != null) {
					PaymentStatus status = new PaymentStatus();
					status.setPaymentStatusId(PaymentStatus.PAYMENT_VOID);
					excess.setExcessChargeStatus(status);

					excessChargeService.update(excess, actionUser);
				}

				claim.setClaimStatus(new CaseStatus(CaseStatus.VOID));
				claim.setDescription("CLAIM VOID");
				claim.setVoidTraceNumber(traceNumber);
				claim.setVoidNotes(notes);
				System.out.println("[VOID] Trace Number Set");
				// update(claim,actionUser);
				claimDao.update(claim);

				recordHistory(claim, "VOID CLAIM", notes, actionUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int getTotalUnInvoicedClaim(Integer clientId, Integer claimStatus, Integer claimType,
			Integer caseCategoryId) throws Exception{
		
		int total = 0;
		
		return total;
		
	}
	public int getTotalRegisteredClaim(Integer clientId, Date start, Date end, Integer claimType,
			Integer caseCategoryId) throws Exception {
		int total = 0;

		try {
			Vector vEqP = new Vector();
			Vector vEqQ = new Vector();

			vEqP.add("deletedStatus");
			vEqQ.add(0);

			if (clientId != null) {
				vEqP.add("clientId");
				vEqQ.add(clientId);
			}

			if (claimType != null && (claimType.intValue() == ClaimType.CASHLESS
					|| claimType.intValue() == ClaimType.REIMBURESEMENT)) {
				vEqP.add("claimTypeId.claimTypeId");
				vEqQ.add(claimType);
			}

			if (caseCategoryId != null) {
				vEqP.add("caseCategoryId.caseCategoryId");
				vEqQ.add(caseCategoryId);
			}

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			if (start != null && end != null) {
				total = getTotal(null, null, sEqP, sEqQ, "admissionDate", start, end);
			} else {
				total = getTotal(null, null, sEqP, sEqQ);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}

	@Override
	public Collection<PaymentReportDetail> getPaymentReport(Integer paymentId) throws Exception {
		Collection<PaymentReportDetail> reports = new Vector<PaymentReportDetail>();
		String required[] = { "Claim.ClaimItems", "Claim.MemberId" };
		String searchLike[] = { "paymentId.paymentId", "deletedStatus" };
		Object objectLike[] = { paymentId, Integer.valueOf(0) };

		int totalClaim = getTotal(null, null, searchLike, objectLike);
		// Collection<Claim> claims = search(null, null,
		// "batchClaimId.batchClaimId", batchClaimId,required,0,100);
		Collection<Claim> claims = search(null, null, searchLike, objectLike, required, 0, totalClaim);
		if (claims != null) {
			Iterator<Claim> claimIterator = claims.iterator();

			Claim claim = null;

			Collection<ClaimItem> claimItems = null;
			Set<ClaimItem> ciSet = null;

			String[] requiredCI = { "ClaimItem.ItemId" };
			if (claimIterator != null) {
				while (claimIterator.hasNext()) {
					claim = claimIterator.next();

					if (claim != null) {
						PaymentReportDetail report = new PaymentReportDetail();
						report.setClaim(claim);

						ciSet = claim.getClaimItems();

						if (ciSet != null && ciSet.size() > 0) {
							claimItems = new Vector();

							Iterator<ClaimItem> ciIterator = ciSet.iterator();

							if (ciIterator != null) {
								while (ciIterator.hasNext()) {

									ClaimItem ci = ciIterator.next();

									if (ci != null && ci.getDeletedStatus() != null
											&& ci.getDeletedStatus().intValue() == 0) {
										DaoSupportUtil.lazyInit(requiredCI, ci);
										claimItems.add(ci);
									}
								}
							}
						}

						report.setClaimItems(claimItems);
						reports.add(report);
					}

				}
			}

		}

		return reports;
	}

	@Override
	public boolean cancelRejectClaim(Integer claimId, String notes, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			if (claimId != null) {
				Claim claim = get(claimId);

				if (claim != null && claim.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {

					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(Claim.CLAIM_OPEN);

					claim.setClaimStatus(status);

					update(claim, actionUser);

					ActivityLog log = new ActivityLog();
					log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
					log.setAction("CANCEL REJECT");
					log.setDescription("Cancel Reject Claim : " + claim.getClaimNumber() + " CLAIM ID = "
							+ claim.getClaimId() + " REASON = " + notes);
					log.setUsername(actionUser.getUser().getUsername());
					log.setIpAddress(actionUser.getIpAddress());
					log.setClaimId(claim);

					activityLogService.create(log);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Collection searchMultiStatus(String[] likeColumns, Object[] likeParams, String[] eqColumns,
			Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat, Object[] notInParamsStatus,
			Object[] notInParamsCat, boolean asc, String columnOrder, String[] required, int index, int offset)
					throws Exception {
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		if (inParamsStatus != null && inParamsStatus.length > 1)
			DaoSupportUtil.setIn("claimStatus.caseStatusId", inParamsStatus, c);
		if (inParamsCat != null && inParamsCat.length > 1)
			DaoSupportUtil.setIn("caseCategoryId.caseCategoryId", inParamsCat, c);

		if (notInParamsStatus != null && notInParamsStatus.length > 1) {
			DaoSupportUtil.setNotIn("claimStatus.caseStatusId", notInParamsStatus, c);
		}
		if (notInParamsCat != null && notInParamsCat.length > 1) {
			DaoSupportUtil.setNotIn("caseCategoryId.caseCategoryId", notInParamsCat, c);
		}

		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	public Collection searchMultiStatus(String[] likeColumns, Object[] likeParams, String[] eqColumns,
			Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat, Object[] notInParamsStatus,
			Object[] notInParamsCat, boolean asc, String columnOrder, String dateBy, Date minDate, Date maxDate,
			String[] required, int index, int offset) throws Exception {
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

		if (inParamsStatus != null && inParamsStatus.length > 1) {
			DaoSupportUtil.setIn("claimStatus.caseStatusId", inParamsStatus, c);
		}
		if (inParamsCat != null && inParamsCat.length > 1) {
			DaoSupportUtil.setIn("caseCategoryId.caseCategoryId", inParamsCat, c);
		}

		if (notInParamsStatus != null && notInParamsStatus.length > 1) {
			DaoSupportUtil.setNotIn("claimStatus.caseStatusId", notInParamsStatus, c);
		}
		if (notInParamsCat != null && notInParamsCat.length > 1) {
			DaoSupportUtil.setNotIn("caseCategoryId.caseCategoryId", notInParamsCat, c);
		}

		DaoSupportUtil.setBetweenParam(dateBy, minDate, maxDate, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;
	}

	public Collection searchMultiStatus(String[] likeColumns, Object[] likeParams, String[] eqColumns,
			Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat, Object[] notInParamsStatus,
			Object[] notInParamsCat, boolean asc, String columnOrder[], String[] required, int index, int offset)
					throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		if (inParamsStatus != null && inParamsStatus.length > 1)
			DaoSupportUtil.setIn("claimStatus.caseStatusId", inParamsStatus, c);
		if (inParamsCat != null && inParamsCat.length > 1)
			DaoSupportUtil.setIn("caseCategoryId.caseCategoryId", inParamsCat, c);

		if (notInParamsStatus != null && notInParamsStatus.length > 1) {
			DaoSupportUtil.setNotIn("claimStatus.caseStatusId", notInParamsStatus, c);
		}
		if (notInParamsCat != null && notInParamsCat.length > 1) {
			DaoSupportUtil.setNotIn("caseCategoryId.caseCategoryId", notInParamsCat, c);
		}

		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	public Collection searchMultiStatus(String[] likeColumns, Object[] likeParams, String[] eqColumns,
			Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat, Object[] notInParamsStatus,
			Object[] notInParamsCat, boolean asc, String columnOrder[], String dateBy, Date minDate, Date maxDate,
			String[] required, int index, int offset) throws Exception {

		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		if (inParamsStatus != null && inParamsStatus.length > 1)
			DaoSupportUtil.setIn("claimStatus.caseStatusId", inParamsStatus, c);
		if (inParamsCat != null && inParamsCat.length > 1)
			DaoSupportUtil.setIn("caseCategoryId.caseCategoryId", inParamsCat, c);

		if (notInParamsStatus != null && notInParamsStatus.length > 1) {
			DaoSupportUtil.setNotIn("claimStatus.caseStatusId", notInParamsStatus, c);
		}
		if (notInParamsCat != null && notInParamsCat.length > 1) {
			DaoSupportUtil.setNotIn("caseCategoryId.caseCategoryId", notInParamsCat, c);
		}

		DaoSupportUtil.setBetweenParam(dateBy, minDate, maxDate, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Claim element = (Claim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	public int getTotalMultiStatus(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			Object[] inParamsStatus, Object[] inParamsCat, Object[] notInParamsStatus, Object[] notInParamsCat)
					throws Exception {
		// TODO Auto-generated method stub
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		if (inParamsStatus != null && inParamsStatus.length > 1)
			DaoSupportUtil.setIn("claimStatus.caseStatusId", inParamsStatus, c);
		if (inParamsCat != null && inParamsCat.length > 1)
			DaoSupportUtil.setIn("caseCategoryId.caseCategoryId", inParamsCat, c);

		if (notInParamsStatus != null && notInParamsStatus.length > 1) {
			DaoSupportUtil.setNotIn("claimStatus.caseStatusId", notInParamsStatus, c);
		}
		if (notInParamsCat != null && notInParamsCat.length > 1) {
			DaoSupportUtil.setNotIn("caseCategoryId.caseCategoryId", notInParamsCat, c);
		}

		int total = DaoSupportUtil.getTotal(c);

		return total;
	}

	public int getTotalMultiStatus(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			Object[] inParamsStatus, Object[] inParamsCat, Object[] notInParamsStatus, Object[] notInParamsCat,
			String dateBy, Date minimumDate, Date maxDate) throws Exception {
		// TODO Auto-generated method stub
		Criteria c = claimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		if (inParamsStatus != null && inParamsStatus.length > 1)
			DaoSupportUtil.setIn("claimStatus.caseStatusId", inParamsStatus, c);
		if (inParamsCat != null && inParamsCat.length > 1)
			DaoSupportUtil.setIn("caseCategoryId.caseCategoryId", inParamsCat, c);

		if (notInParamsStatus != null && notInParamsStatus.length > 1) {
			DaoSupportUtil.setNotIn("claimStatus.caseStatusId", notInParamsStatus, c);
		}
		if (notInParamsCat != null && notInParamsCat.length > 1) {
			DaoSupportUtil.setNotIn("caseCategoryId.caseCategoryId", notInParamsCat, c);
		}

		DaoSupportUtil.setBetweenParam(dateBy, minimumDate, maxDate, c);

		int total = DaoSupportUtil.getTotal(c);

		return total;
	}
	
	public Collection<Claim> getBatchClaimId(Integer batchClaimId) throws Exception {
		// TODO Auto-generated method stub

		String required[] = { "Claim.ClaimItems", "Claim.MemberId" };
		String searchLike[] = { "batchClaimId.batchClaimId", "deletedStatus" };
		Object objectLike[] = { batchClaimId, Integer.valueOf(0) };

		int totalClaim = getTotal(null, null, searchLike, objectLike);
		// Collection<Claim> claims = search(null, null,
		// "batchClaimId.batchClaimId", batchClaimId,required,0,100);
		Collection<Claim> claims = search(null, null, searchLike, objectLike, required, 0, totalClaim);
	

		return claims;
	}

	@Override
	public Collection<Claim> getUnbilledPolicyClaim(Integer policyId, Integer claimStatus, Integer claimTypeId,
			Integer caseCategoryId) throws Exception {
		// TODO Auto-generated method stub
		Collection<Claim> claimList = new Vector<Claim>();
		
		try {
			String q = "SELECT c FROM Claim c WHERE c.invoiceItemId is NULL "
					+ "AND c.policy.policyId = :policyId AND c.deletedStatus = 0 "
					+ "AND c.claimStatus.caseStatusId = :claimStatus ";
			
			if (claimTypeId != null){
				q += " AND claimTypeId.claimTypeId = :claimTypeId";
			}
			if (caseCategoryId != null){
				q += " AND caseCategoryId.caseCategoryId = :caseCategoryId";
			}
			
			Session session = claimDao.getClaimSession();
			
			Query query = session.createQuery(q);		
			query.setInteger("policyId", policyId);
			query.setInteger("claimStatus", claimStatus);
			
			if (claimTypeId != null){
				query.setInteger("claimTypeId", claimTypeId);
			}
			if (caseCategoryId != null){
				query.setInteger("caseCategoryId", caseCategoryId);
			}
			
			claimList = query.list();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return claimList;
		
	}

	@Override
	public Collection<Claim> getUnbilledClientClaim(Integer clientId, Integer claimStatus, Integer claimTypeId,
			Integer caseCategoryId) throws Exception {
		// TODO Auto-generated method stub
Collection<Claim> claimList = new Vector<Claim>();
		
		try {
			String q = "SELECT c FROM Claim c WHERE c.invoiceItemId is NULL "
					+ "AND c.clientId = :clientId AND c.deletedStatus = 0 "
					+ "AND c.claimStatus.caseStatusId = :claimStatus ";
			
			if (claimTypeId != null){
				q += " AND claimTypeId.claimTypeId = :claimTypeId";
			}
			if (caseCategoryId != null){
				q += " AND caseCategoryId.caseCategoryId = :caseCategoryId";
			}
			
			Session session = claimDao.getClaimSession();
			
			Query query = session.createQuery(q);		
			query.setInteger("clientId", clientId);
			query.setInteger("claimStatus", claimStatus);
			
			if (claimTypeId != null){
				query.setInteger("claimTypeId", claimTypeId);
			}
			if (caseCategoryId != null){
				query.setInteger("caseCategoryId", caseCategoryId);
			}
			
			claimList = query.list();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return claimList;
	}

}
