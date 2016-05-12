package com.ametis.cms.web.controller;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.TreatmentUpgradeType;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.CaseStatusService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClaimTypeService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DependentService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.TreatmentLocationService;
import com.ametis.cms.service.TreatmentUpgradeTypeService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ClaimForm;

/**
 * Claim is a mapping of claim Table.
 */
public class ClaimFormController extends SimpleFormController
{

	ClaimService claimService;

	ResourceBundleMessageSource alertProperties;

	TreatmentLocationService treatmentLocationService;

	DependentService dependentService;
	private TreatmentUpgradeTypeService treatmentUpgradeTypeService;

	// foreign affairs

	ClaimTypeService claimTypeService;
	private SecurityService securityService;
	private ActivityLogService logService;

	public TreatmentUpgradeTypeService getTreatmentUpgradeTypeService() {
		return treatmentUpgradeTypeService;
	}

	public void setTreatmentUpgradeTypeService(TreatmentUpgradeTypeService treatmentUpgradeTypeService) {
		this.treatmentUpgradeTypeService = treatmentUpgradeTypeService;
	}

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

	// public CaseService getMyCaseService() {
	// return myCaseService;
	// }

	// public void setMyCaseService(CaseService myCaseService) {
	// this.myCaseService = myCaseService;
	// }

	public void setClaimTypeService(ClaimTypeService obj) {
		this.claimTypeService = obj;
	}

	public ClaimTypeService getClaimTypeService() {
		return this.claimTypeService;
	}

	CaseStatusService caseStatusService;
	
	private MemberProductService memberProductService;
	private ConfigurationService configurationService;
	
	
	

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public void setCaseStatusService(CaseStatusService obj) {
		this.caseStatusService = obj;
	}

	public CaseStatusService getCaseStatusService() {
		return this.caseStatusService;
	}

	MemberService memberService;

	public void setMemberService(MemberService obj) {
		this.memberService = obj;
	}

	public MemberService getMemberService() {
		return this.memberService;
	}

	DiagnosisService diagnosisService;

	public void setDiagnosisService(DiagnosisService obj) {
		this.diagnosisService = obj;
	}

	public DiagnosisService getDiagnosisService() {
		return this.diagnosisService;
	}

	BatchClaimService batchClaimService;

	public void setBatchClaimService(BatchClaimService obj) {
		this.batchClaimService = obj;
	}

	public BatchClaimService getBatchClaimService() {
		return this.batchClaimService;
	}

	CaseService myCaseService;

	public void setCaseService(CaseService obj) {
		this.myCaseService = obj;
	}

	public CaseService getCaseService() {
		return this.myCaseService;
	}

	CaseCategoryService caseCategoryService;

	public void setCaseCategoryService(CaseCategoryService obj) {
		this.caseCategoryService = obj;
	}

	public CaseCategoryService getCaseCategoryService() {
		return this.caseCategoryService;
	}

	ProviderService providerService;

	public void setProviderService(ProviderService obj) {
		this.providerService = obj;
	}

	public ProviderService getProviderService() {
		return this.providerService;
	}

	PaymentService paymentService;

	public void setPaymentService(PaymentService obj) {
		this.paymentService = obj;
	}

	public PaymentService getPaymentService() {
		return this.paymentService;
	}

	// -- foreign affairs end

	public void setClaimService(ClaimService object) {
		this.claimService = object;
	}

	public ClaimService getClaimService() {
		return this.claimService;
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
	
	
	//Begin Riyan
	MemberBenefitService memberBenefitService;
	
	
	

	

	public void setMemberBenefitService(MemberBenefitService obj) {
		this.memberBenefitService = obj;
	}

	public MemberBenefitService getMemberBenefitService() {
		return this.memberBenefitService;
	}

	public ClaimFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		System.out.println("MASUK FORM BACKING");
		Object result = null;
		ClaimForm tmp = null;
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");

		String navigation = request.getParameter("navigation");
		Integer caseId = WebUtil.getParameterInteger(request, "caseId");
		String caseNavigation = WebUtil.getAttributeString(request, "caseNavigation", "");
		System.out.println("FORM BACK : " + caseNavigation);

		Claim checkClaim = null;
		// Add 20150904, untuk mengecek dari Cost Calculation, jika claim sudah
		// ter-create jangan bikin new
		// if(navigation.equals("calculate") ||
		// navigation.equals("approvecase")){
		if (caseId != null) {
			String[] param = { "caseId.caseId", "deletedStatus" };
			Object[] value = { caseId, Integer.valueOf(0) };

			checkClaim = claimService.searchUnique(param, value, 0, 1);
			if (checkClaim != null)
				claimId = checkClaim.getClaimId();
			System.out.println("Masuk CALCULATE");
		}
		// }

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (claimId != null) {
			java.io.Serializable pkey = claimId;

			String[] required = { "Claim.BatchClaimId.ClaimCurrency", "Claim.DiagnosisId", "Claim.Diagnosis2Id",
					"Claim.Diagnosis3Id", "Claim.CaseId", "Claim.BatchClaimId.PaymentRecipient",
					"Claim.BatchClaimId.BatchClaimType" };

			Claim object = claimService.get(pkey, required);

			if (object != null) { // edit object
				tmp = new ClaimForm(object);
				// foreign affairs

				tmp.setClaimTypeId(object.getClaimTypeId().getClaimTypeId().toString());

				tmp.setClaimStatus("" + object.getClaimStatus().getCaseStatusId());

				if (object.getMemberId() != null) {
					Member member = memberService.get(object.getMemberId().getMemberId());
					
					String[] param = { "memberId.memberId", "itemCategoryId.itemCategoryId", "deletedStatus","memberBenefitStatus" };
					Object[] value = { object.getMemberId().getMemberId(), Integer.valueOf(1), Integer.valueOf(0),Integer.valueOf(1) };

					
					MemberBenefit benefit = memberBenefitService.searchUnique(param, value, 0, 1);
					
					if (member != null) {
						tmp.setMemberId(member.getMemberId().toString());
						tmp.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
						tmp.setClientId(member.getClientId().getClientId().toString());
						tmp.setName(member.getFirstName());
						tmp.setPolicyNumber(member.getCustomerPolicyNumber());
						if(benefit!=null){
							tmp.setBenefitRoomRate(String.valueOf(benefit.getBenefitLimit()));	
						}
						
					}
				}

				if (object.getDiagnosisId() != null) {
					Diagnosis diagnosis = diagnosisService.get(object.getDiagnosisId().getDiagnosisId());
					if (diagnosis != null) {
						tmp.setDiagnosisId(diagnosis.getDiagnosisId().toString());
						tmp.setDiagnosis1Code(diagnosis.getDiagnosisCode());
						tmp.setDiagnosis1Text(diagnosis.getDescription());
					}
				}
				if (object.getDiagnosis2Id() != null) {
					Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis2Id().getDiagnosisId());

					if (diagnosis != null) {
						tmp.setDiagnosis2Id(diagnosis.getDiagnosisId().toString());
						tmp.setDiagnosis2Code(diagnosis.getDiagnosisCode());
						tmp.setDiagnosis2Text(diagnosis.getDescription());
					}
				}

				if (object.getDiagnosis3Id() != null) {
					Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis3Id().getDiagnosisId());

					if (diagnosis != null) {
						tmp.setDiagnosis3Id(diagnosis.getDiagnosisId().toString());
						tmp.setDiagnosis3Code(diagnosis.getDiagnosisCode());
						tmp.setDiagnosis3Text(diagnosis.getDescription());
					}
				}

				if (object.getBatchClaimId() != null) {

					BatchClaim batchClaim = batchClaimService.get(object.getBatchClaimId().getBatchClaimId());

					tmp.setBatchClaimId(batchClaim.getBatchClaimId().toString());
					tmp.setBatchClaimNumber(batchClaim.getBatchClaimNumber());

					if (batchClaim.getPaymentRecipient() != null) {

						tmp.setPaymentRecipient(batchClaim.getPaymentRecipient().getPaymentRecipientId().toString());
					}

					if (batchClaim.getBatchClaimType() != null) {
						tmp.setClaimTypeId(batchClaim.getBatchClaimType().getClaimTypeId().toString());
					}

				}
				if (object.getCaseId() != null) {
					String caseNumber = object.getCaseId().getCaseNumber();
					tmp.setCaseId(object.getCaseId().getCaseId().toString());
					tmp.setCaseNumber(caseNumber);

					tmp.setPaymentRecipient(PaymentRecipient.PROVIDER + "");
					ClaimType ctype = new ClaimType();
					ctype.setClaimTypeId(ClaimType.CASHLESS);
					tmp.setClaimTypeId(ClaimType.CASHLESS + "");
				}

				if (object.getLocationId() != null) {
					tmp.setLocationId(object.getLocationId().getLocationId().toString());
				}
				if (object.getCaseCategoryId() != null) {
					tmp.setCaseCategoryId(object.getCaseCategoryId().getCaseCategoryId().toString());
				}

				if (object.getProviderId() != null) {
					tmp.setProviderId(object.getProviderId().getProviderId().toString());
				}

				if (object.getPaymentId() == null) {
					object.setPaymentId(new Payment());
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClaimForm();
				// foreign affairs

				tmp.setLocationId(null);

				// tmp.setLocationId()
				Integer claimTypeId = WebUtil.getParameterInteger(request, "claimTypeId");

				if (claimTypeId != null) {

					tmp.setClaimTypeId(claimTypeId.toString());

				} else {

				}

				Integer claimStatus = WebUtil.getParameterInteger(request, "claimStatus");

				if (claimStatus != null) {
					CaseStatus forClass = new CaseStatus();
					forClass.setCaseStatusId(claimStatus);
					tmp.setClaimStatus("" + claimStatus);

					CaseStatus caseStatus = this.caseStatusService.get(claimStatus);
					tmp.getClaim().setClaimStatus(caseStatus);
				} else {
					CaseStatus forClass = new CaseStatus();
					// tmp.setClaimStatus("");
					tmp.getClaim().setClaimStatus(forClass);
				}

				Integer memberId = WebUtil.getParameterInteger(request, "memberId");

				if (memberId != null) {

					tmp.setMemberId(memberId.toString());

					Member member = this.memberService.get(memberId);
					tmp.getClaim().setMemberId(member);
				} else {
				}

				Integer diagnosisId = WebUtil.getParameterInteger(request, "diagnosisId");

				if (diagnosisId != null) {

					Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
					tmp.getClaim().setDiagnosisId(diagnosis);
					tmp.setDiagnosisId(diagnosis.getDiagnosisId().toString());
					tmp.setDiagnosis1Code(diagnosis.getDiagnosisCode());
					tmp.setDiagnosis1Text(diagnosis.getDiagnosisName());
				}

				Integer diagnosis2Id = WebUtil.getParameterInteger(request, "diagnosis2Id");

				if (diagnosis2Id != null) {

					Diagnosis diagnosis = this.diagnosisService.get(diagnosis2Id);
					tmp.getClaim().setDiagnosis2Id(diagnosis);
					tmp.setDiagnosis2Id(diagnosis.getDiagnosisId().toString());
					tmp.setDiagnosis2Code(diagnosis.getDiagnosisCode());
					tmp.setDiagnosis2Text(diagnosis.getDiagnosisName());

				}

				Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");

				if (batchClaimId != null) {
					BatchClaim bc = batchClaimService.get(batchClaimId);
					if (bc != null) {
						tmp.setBatchClaimId(batchClaimId.toString());
						tmp.setBatchClaimNumber(bc.getBatchClaimNumber());
					}
				} else {

				}

				if (caseId != null) {

					tmp.setCaseId(caseId.toString());

					Case myCase = this.myCaseService.get(caseId);
					tmp.getClaim().setCaseId(myCase);

					if (myCase != null) {
						tmp.setCaseNumber(myCase.getCaseNumber());
					}
				}

				Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");

				if (caseCategoryId != null) {
					tmp.setCaseCategoryId(caseCategoryId.toString());

					// CaseCategory caseCategory = this.caseCategoryService
					// .get(caseCategoryId);
					// tmp.getClaim().setCaseCategoryId(caseCategory);
				} else {

				}

				Integer providerId = WebUtil.getParameterInteger(request, "providerId");

				if (providerId != null) {

					tmp.setProviderId(providerId.toString());

					// Provider provider = this.providerService.get(providerId);
					// tmp.getClaim().setProviderId(provider);
				} else {

				}

				Integer paymentId = WebUtil.getParameterInteger(request, "paymentId");

				if (paymentId != null) {
					Payment forClass = new Payment();
					forClass.setPaymentId(paymentId);
					tmp.setPaymentId(forClass);

					// Payment payment = this.paymentService.get(paymentId);
					// tmp.getClaim().setPaymentId(payment);
				} else {
					Payment forClass = new Payment();
					// tmp.setPaymentId("");
					tmp.getClaim().setPaymentId(forClass);
				}

				// -- foreign affairs end
			}
			String breadcrumb = "<a href=\"claim?navigation=detail&claimId=" + claimId
					+ "\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Claim";
			request.setAttribute("breadcrumb", breadcrumb);
			request.setAttribute("navigation", navigation);

		} // mau edit end
		else { // bikin baru
			tmp = new ClaimForm();
			// foreign affairs

			tmp.setLocationId(null);
			Integer claimTypeId = WebUtil.getParameterInteger(request, "claimTypeId");

			if (claimTypeId != null) {

				tmp.setClaimTypeId(claimTypeId.toString());

				// ClaimType claimType = this.claimTypeService.get(claimTypeId);
				// tmp.getClaim().setClaimTypeId(claimType);
			} else {

			}

			Integer claimStatus = WebUtil.getParameterInteger(request, "claimStatus");

			if (claimStatus != null) {
				CaseStatus forClass = new CaseStatus();
				forClass.setCaseStatusId(claimStatus);
				tmp.setClaimStatus("" + claimStatus);

				CaseStatus caseStatus = this.caseStatusService.get(claimStatus);
				tmp.getClaim().setClaimStatus(caseStatus);
			} else {
				CaseStatus forClass = new CaseStatus();
				// tmp.setClaimStatus("");
				tmp.getClaim().setClaimStatus(forClass);
			}

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			if (memberId != null) {

				tmp.setMemberId(memberId.toString());

				Member member = this.memberService.get(memberId);
				tmp.getClaim().setMemberId(member);
			} else {
			}

			Integer diagnosisId = WebUtil.getParameterInteger(request, "diagnosisId");

			if (diagnosisId != null) {

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getClaim().setDiagnosisId(diagnosis);
				tmp.setDiagnosisId(diagnosis.getDiagnosisId().toString());
				tmp.setDiagnosis1Code(diagnosis.getDiagnosisCode());
				tmp.setDiagnosis1Text(diagnosis.getDiagnosisName());

			}

			Integer diagnosis2Id = WebUtil.getParameterInteger(request, "diagnosis2Id");

			if (diagnosis2Id != null) {
				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);

				tmp.getClaim().setDiagnosis2Id(diagnosis);
				tmp.setDiagnosis2Id(diagnosis.getDiagnosisId().toString());
				tmp.setDiagnosis2Code(diagnosis.getDiagnosisCode());
				tmp.setDiagnosis2Text(diagnosis.getDiagnosisName());

			}
			Integer diagnosis3Id = WebUtil.getParameterInteger(request, "diagnosis3Id");

			if (diagnosis3Id != null) {
				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				tmp.getClaim().setDiagnosis3Id(diagnosis);
				tmp.setDiagnosis3Id(diagnosis.getDiagnosisId().toString());
				tmp.setDiagnosis3Code(diagnosis.getDiagnosisCode());
				tmp.setDiagnosis3Text(diagnosis.getDiagnosisName());

			}

			Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");

			if (batchClaimId != null) {

				String[] required = { "BatchClaim.MemberId", "BatchClaim.ClientId", "BatchClaim.MemberGroupId" };

				BatchClaim batchClaim = this.batchClaimService.get(batchClaimId, required);

				if (batchClaim != null) {
					tmp.setBatchClaimId(batchClaim.getBatchClaimId().toString());
					tmp.setBatchClaimNumber(batchClaim.getBatchClaimNumber());
					tmp.getClaim().setBatchClaimId(batchClaim);

					ClaimType ctype = batchClaim.getBatchClaimType();
					tmp.setClaimDate(batchClaim.getBatchClaimDate().toString());
					tmp.setClaimTypeId(ctype.getClaimTypeId().toString());
					tmp.setPaymentRecipient(batchClaim.getPaymentRecipient().getPaymentRecipientId().toString());

					if (ctype != null) {

						if (ctype.getClaimTypeId().intValue() == ClaimType.CASHLESS) {

							String[] providerReq = { "Provider.ProviderLocation" };

							Provider provider = providerService.get(batchClaim.getProviderId().getProviderId(),
									providerReq);
							tmp.getClaim().setLocationId(provider.getProviderLocation());
							tmp.setProviderId(batchClaim.getProviderId().getProviderId().toString());
							tmp.setProviderName(provider.getProviderName());

						} else {

							tmp.setProviderName("");
						}
						tmp.getClaim().setClaimTypeId(ctype);

					}

					if (batchClaim.getPaymentRecipient().getPaymentRecipientId() == PaymentRecipient.MEMBER) {

						Member member = batchClaim.getMemberId();
						System.out.println("GETTING MEMBER INFORMATION : " + member);

						if (member != null) {

							tmp.setName(StringUtil.getStringValue(member.getFirstName(), "") + " "
									+ StringUtil.getStringValue(member.getLastName(), ""));
							tmp.setPolicyNumber(StringUtil.getStringValue(member.getCustomerPolicyNumber(), ""));
							tmp.setMemberId(member.getMemberId().toString());
							// tmp.set
						}

					}
					if (batchClaim.getPaymentRecipient().getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP) {

						MemberGroup memberGroup = batchClaim.getMemberGroupId();

						if (memberGroup != null) {

							tmp.setMemberGroupId(memberGroup.getMemberGroupId().toString());
							// tmp.set
						}

					}
					if (batchClaim.getTotalClaim() != null) {
						if (batchClaim.getTotalClaim().intValue() == 1) {
							tmp.setClaimChargeValue(batchClaim.getBatchClaimInitialValue() + "");
							tmp.getClaim().setClaimChargeValue(batchClaim.getBatchClaimInitialValue());
						}
					}

				}
			} else {
			}

			Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");

			if (caseCategoryId != null) {
				tmp.setCaseCategoryId(caseCategoryId.toString());

			} else {

			}

			Integer providerId = WebUtil.getParameterInteger(request, "providerId");

			if (providerId != null) {

				tmp.setProviderId(providerId.toString());

			} else {
				// Provider forClass = new Provider();
				// tmp.setProviderId("");
				// tmp.getClaim().setProviderId(forClass);
			}

			Integer paymentId = WebUtil.getParameterInteger(request, "paymentId");

			if (paymentId != null) {

				Payment forClass = new Payment();
				forClass.setPaymentId(paymentId);
				tmp.setPaymentId(forClass);

			} else {
				Payment forClass = new Payment();
				// tmp.setPaymentId("");
				tmp.getClaim().setPaymentId(forClass);
			}

			if (caseId != null) {

				Case refCase = myCaseService.get(caseId);
				if (refCase != null) {
					tmp.setCaseId(caseId.toString());
					tmp.setAdmissionDate(refCase.getCaseStartTime().toString());
					// tmp.setDischargeDate(refCase.getCaseEndTime().toString());
					// // sementara dihilangkan karena permintaan

					if (refCase.getCaseCategoryId() != null) {
						tmp.setCaseCategoryId(refCase.getCaseCategoryId().getCaseCategoryId().toString());
					}
					tmp.setCaseNumber(refCase.getCaseNumber());

					if (refCase.getDiagnosis1Id() != null) {
						tmp.setDiagnosisId(refCase.getDiagnosis1Id().getDiagnosisId().toString());
						tmp.setDiagnosis1Code(refCase.getDiagnosis1Id().getDiagnosisCode());
						tmp.setDiagnosis1Text(refCase.getDiagnosis1Id().getDiagnosisName());
					}
					if (refCase.getDiagnosis2Id() != null) {
						Diagnosis diag = diagnosisService.get(refCase.getDiagnosis2Id().getDiagnosisId());

						tmp.setDiagnosis2Id(diag.getDiagnosisId().toString());

						tmp.setDiagnosis2Code(diag.getDiagnosisCode());
						tmp.setDiagnosis2Text(diag.getDiagnosisName());
					}
					if (refCase.getDiagnosis3Id() != null) {
						Diagnosis diag = diagnosisService.get(refCase.getDiagnosis3Id().getDiagnosisId());
						tmp.setDiagnosis3Id(diag.getDiagnosisId().toString());
						tmp.setDiagnosis3Code(diag.getDiagnosisCode());
						tmp.setDiagnosis3Text(diag.getDiagnosisName());
					}

					tmp.setProviderId(refCase.getProviderId().getProviderId().toString());
					tmp.setProviderName(refCase.getProviderId().getProviderName());
					tmp.setMemberId(refCase.getMemberId().getMemberId().toString());
					tmp.setPatientName(refCase.getMemberId().getFirstName());
					tmp.setPolicyNumber(refCase.getMemberId().getCustomerPolicyNumber());
					tmp.setName(refCase.getMemberId().getFirstName());
					tmp.setClientId(refCase.getMemberId().getClientId().getClientId().toString());

					tmp.setPaymentRecipient(PaymentRecipient.PROVIDER + "");

					tmp.setClaimTypeId(ClaimType.CASHLESS + "");

					if (refCase.getManualRegistration() != null) {
						tmp.setManualRegistration(refCase.getManualRegistration());
					}
				}

			} else {
				// Case forClass = new Case();
				// tmp.setCaseId("");
				// tmp.getClaim().setCaseId(forClass);
			}

			// -- foreign affairs end

			String breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId=" + batchClaimId
					+ "\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Claim";
			request.setAttribute("breadcrumb", breadcrumb);
			request.setAttribute("navigation", navigation);

		}
		
		
		
		System.out.println("NAVIGATION : " + navigation);
		if (navigation.equalsIgnoreCase("approvecase") || navigation.equalsIgnoreCase("calculate")) {
			request.setAttribute("caseNavigation", navigation);
		} else {
			request.setAttribute("caseNavigation", caseNavigation);
		}
		request.setAttribute("navigation", navigation);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {
		System.out.println("MASUK ON BIND AND VALIDATE");
		ClaimForm claimForm = (ClaimForm) command;

		Claim claim = claimForm.getClaim();
		errors.printStackTrace();
		System.out.println("ERROR : " + errors);
		String navigation = request.getParameter("navigation");
		String casenav = request.getParameter("caseNavigation");
		request.setAttribute("navigation", navigation);
		request.setAttribute("caseNavigation", casenav);
		System.out.println("NAV : " + navigation);
		System.out.println("CASE NAV : " + casenav);
		// errors.setNestedPath("claim");
		// getValidator().validate(claim, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		Collection treatmentLocation = treatmentLocationService.getAll();
		Collection claimTypes = claimTypeService.getAll();
		Collection caseCategory = caseCategoryService.getAll();
		Collection<TreatmentUpgradeType> upgradeType = treatmentUpgradeTypeService.getAll();

		model.put("roomUpgradeType", upgradeType);
		model.put("treatmentLocation", treatmentLocation);
		model.put("claimType", claimTypes);
		model.put("caseCategory", caseCategory);

		Collection patientBean = new Vector();

		// Integer memberId = WebUtil.getParameterInteger(request, "memberId");

		Integer batchClaimId = WebUtil.getParameterInteger(request, "batchClaimId");

		if (batchClaimId != null) {

			BatchClaim batchClaim = batchClaimService.get(batchClaimId);

			if (batchClaim != null
					&& batchClaim.getBatchClaimType().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT
					&& batchClaim.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER) {

				Integer memberId = batchClaim.getMemberId().getMemberId();

				if (memberId != null) {
					String[] required = { "Member.MemberGroupId", "Member.ClientId", "Member.RelationshipId",
							"Member.ParentMember" };
					Member member = memberService.get(memberId, required);

					String[] eqP = { "parentMember.memberId", "memberGroupId.clientId.clientId", "deletedStatus" };
					Object[] eqQ = { member.getMemberId(), member.getMemberGroupId().getClientId().getClientId(),
							Integer.valueOf(0) };

					int total = memberService.getTotal(null, null, eqP, eqQ);

					System.out.println("HOLA! : " + total);
					Collection dependentBean = memberService.search(null, null, eqP, eqQ, 0, total);
					model.put("dependentCollection", dependentBean);
				}
			}
		}

		System.out.println("REFERENCE DATA DIPANGGIL!");
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		System.out.println("MASUK ON SUBMIT");
		ClaimForm claimForm = (ClaimForm) command;

		Claim res = null;
		String alertMsg = "";
		String redirectUrl = "claim";
		Integer claimStatusTmp = null;
		;
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		String caseNavigation = WebUtil.getParameterString(request, "caseNavigation", "");

		ModelAndView result = null;
		boolean isError = false;
		try {
			// foreign affairs

			if (claimForm.getAdmissionDate() != null && claimForm.getDischargeDate() != null) {

				if (claimForm.getDiagnosis2Id() != null && !claimForm.getDiagnosis2Id().equalsIgnoreCase("")) {

					if (claimForm.isNewClaim()) {
						Diagnosis diagnosis2 = diagnosisService.get(Integer.valueOf(claimForm.getDiagnosis2Id()));

						claimForm.getClaim().setDiagnosis2Id(diagnosis2);
					}

					else {
						if (claimForm.getDiagnosis2Text().equalsIgnoreCase("")) {
							claimForm.getClaim().setDiagnosis2Id(null);
							claimForm.getClaim().setDiagnosis2Code("");
						}
					}

				} else {
					if (!claimForm.isNewClaim()) {
						claimForm.getClaim().setDiagnosis2Id(null);
						claimForm.getClaim().setDiagnosis2Code("");

					}
				}

				if (claimForm.getDiagnosis3Id() != null && !claimForm.getDiagnosis3Id().equalsIgnoreCase("")) {

					if (claimForm.isNewClaim()) {
						Diagnosis diagnosis3 = diagnosisService.get(Integer.valueOf(claimForm.getDiagnosis3Id()));
						claimForm.getClaim().setDiagnosis3Id(diagnosis3);
					} else {
						if (claimForm.getDiagnosis3Text().equalsIgnoreCase("")) {
							claimForm.getClaim().setDiagnosis3Id(null);
							claimForm.getClaim().setDiagnosis3Code("");
						}
					}

				} else {
					if (!claimForm.isNewClaim()) {
						claimForm.getClaim().setDiagnosis3Id(null);
						claimForm.getClaim().setDiagnosis3Code("");
					}
				}

				if (claimForm.getDiagnosis1() != null && !claimForm.getDiagnosis1().equalsIgnoreCase("")) {

					Diagnosis diagnosis1 = diagnosisService.get(Integer.valueOf(claimForm.getDiagnosisId()));
					claimForm.getClaim().setDiagnosisId(diagnosis1);
				} else {
					if (!claimForm.isNewClaim()) {
						claimForm.getClaim().setDiagnosisId(null);
						claimForm.getClaim().setDiagnosis1Code("");

					}
				}

				CaseStatus claimStatus = new CaseStatus();

				// modified by aju on 20151019, add handler for PRE AUTH case,
				// cause PRE AUTH case used the APPROVE button too zzzz...
				// Edit 20150414 by FVO, untuk setting status pada Claim dengan
				// status Tentative/Sementara
				// CLaim dengan status Tentative tidak boleh diganti-ganti
				// if(caseNavigation.equalsIgnoreCase("approvecase") &&
				// claimForm.isNewClaim()){
				if (caseNavigation.equalsIgnoreCase("approvecase")) {
					System.out.println("ENTER NEW CLAIM TENTATIVE");
					claimStatus.setCaseStatusId(Integer.valueOf(Claim.CLAIM_PRE_OPEN));
				} else {
					claimStatusTmp = claimForm.getClaim().getClaimStatus().getCaseStatusId();
					claimStatus.setCaseStatusId(Integer.valueOf(Claim.CLAIM_OPEN));
				}
				claimForm.getClaim().setClaimStatus(claimStatus);

				Claim claim = claimForm.getClaim();

				if (claim != null) {

					Date receivedDate = claim.getClaimDate();
					Date admissionDate = claim.getAdmissionDate();
					Date dischargeDate = claim.getDischargeDate();

					if (claim.getCaseId() == null) {
						// check if reimburse or post claim

						if (claim.getTipe() == null || claim.getTipe().intValue() == Claim.CLAIM_SHOW) {
							if (admissionDate.after(receivedDate) || dischargeDate.after(receivedDate)) {
								isError = true;
							}
							if (admissionDate.after(dischargeDate)) {
								isError = true;
							}
						}
					}

					ClaimType cType = claim.getClaimTypeId();

					if (cType != null && cType.getClaimTypeId() != null) {
						if (cType.getClaimTypeId().intValue() == ClaimType.CASHLESS) {

							if (claimForm.getBatchClaimId() != null
									&& !claimForm.getBatchClaimId().equalsIgnoreCase("")) {
								BatchClaim bc = batchClaimService.get(Integer.valueOf(claimForm.getBatchClaimId()));

								if (bc != null) {
									claim.setProviderId(bc.getProviderId());
								}
							}
							if (claimForm.getCaseId() != null) {

							}
						} else {

							try {

								if (claimForm.getPatientName() != null
										&& (!claimForm.getPatientName().equalsIgnoreCase("")
												|| !claimForm.getPatientName().equalsIgnoreCase("-"))) {
									Integer claimant = Integer.valueOf(claimForm.getPatientName());

									Member member = new Member();
									member.setMemberId(claimant);
									claim.setMemberId(member);
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							String prov = claimForm.getProviderId();

							if (prov == null && !claimForm.getProviderName().equalsIgnoreCase("")) {

								ActionUser user = securityService.getActionUser(request);
								ProviderCategory cat = new ProviderCategory();
								cat.setProviderCategoryId(4);
								Provider provider = new Provider();
								provider.setProviderName(claimForm.getProviderName());
								provider.setProviderCategoryId(cat);

								Provider providerResult = providerService.create(provider, user);
								if (providerResult != null) {
									claim.setProviderId(providerResult);
									claim.setProviderName(claimForm.getProviderName());
								}
							} else {
								if (!prov.equalsIgnoreCase("")) {
									Provider provider = providerService.get(Integer.valueOf(prov));
									if (provider != null) {
										claim.setProviderId(provider);
										claim.setProviderName(provider.getProviderName());
										claim.setProviderArea(provider.getCity());
									}
								}
							}
							claim.setCaseId(null);

						}
						claim.setPaymentId(null);
					} else {

					}

				}

				// -- foreign affairs end
				String notyet = WebUtil.getParameterString(request, "notyet", "");
				if (notyet.equals("true")) {
					System.out.println("MASUK NOT YET");
					return showForm(request, response, errors);
				}

				if (claimForm.isNewClaim()) {

					ActionUser user = securityService.getActionUser(request);

					boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATECLAIM");

					if (!isUserAuthorized) {

						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult.addObject("errorMessage", "You Are Not Authorized for CREATECLAIM access");
						return errorResult;

					}

					if (claimForm.getClaim().getCaseId() != null) {
						claimForm.getClaim().setTipe(Claim.CLAIM_CASE);
						claimForm.getClaim().setIsEDCBatchAssigned(0);
					} else {
						claimForm.getClaim().setTipe(Claim.CLAIM_SHOW);
					}
					res = claimService.create(claimForm.getClaim(), user);
					System.out.println("CASE NAV : " + caseNavigation);
					System.out.println("NAVIGAtiON : " + navigation);

					if (navigation.equalsIgnoreCase("pending")) {
						// res = claimService.create(object, actionUser)

						if (res != null) {
							alertMsg = alertProperties.getMessage("success.create.claim", null, "success",
									Locale.getDefault());

							redirectUrl = "pendingclaim-form?navigation=tambah&claimId=" + res.getClaimId();

						} else {
							alertMsg = alertProperties.getMessage("fail.create.claim", null, "fail",
									Locale.getDefault());
							redirectUrl = "claim?alert=" + alertMsg;
						}
					} else {

						if (res != null) {
							alertMsg = alertProperties.getMessage("success.create.claim", null, "success",
									Locale.getDefault());
							if (res.getBatchClaimId() != null) {
								if (navigation.equalsIgnoreCase("register")) {
									redirectUrl = "batchclaim?navigation=detail&batchClaimId="
											+ res.getBatchClaimId().getBatchClaimId() + "&alert=" + alertMsg;
								} else {
									redirectUrl = "claimitem?navigation=addclaimitem&claimId=" + res.getClaimId()
											+ "&batchClaimId=" + res.getBatchClaimId().getBatchClaimId() + "&alert="
											+ alertMsg;
								}
							} else {
								// Add 20150410 by FVO, untuk redirect ke case
								// bila navigasi approve dari case, maka akan
								// kembali ke case
								if (res.getCaseId() != null) {
									if (caseNavigation.equalsIgnoreCase("approvecase")) {
										redirectUrl = "case?navigation=approve&caseId=" + res.getCaseId().getCaseId()
												+ "&claimId=" + res.getClaimId();
									} else {
										redirectUrl = "claimitem?navigation=addclaimitem&claimId=" + res.getClaimId()
												+ "&caseId=" + res.getCaseId().getCaseId();
									}
								}
							}

						} else {
							alertMsg = alertProperties.getMessage("fail.create.claim", null, "fail",
									Locale.getDefault());
							redirectUrl = "claim" + "?alert=" + alertMsg;
						}
					}

				} else {

					ActionUser user = securityService.getActionUser(request);

					boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATECLAIM");

					if (!isUserAuthorized) {

						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult.addObject("errorMessage", "You Are Not Authorized for UPDATECLAIM access");
						return errorResult;

					}
					if (caseNavigation.equalsIgnoreCase("calculate")) {
						res = claimService.updateCaseCalculationClaim(claimForm.getClaim(), user);
					} else {
						res = claimService.update(claimForm.getClaim(), user);
					}

					if (res != null) {
						alertMsg = alertProperties.getMessage("success.update.claim", null, "success",
								Locale.getDefault());
						// Add 20150410 by FVO, untuk redirect bila dari case
						// melakukan cost calculation
						if (caseNavigation.equalsIgnoreCase("calculate")) {
							System.out.println(
									"CLAIM STATUS : " + claimForm.getClaim().getClaimStatus().getCaseStatusName());
							// if(claimStatusTmp == Claim.CLAIM_TENTATIVE){
							// claimService.openClaimItems(res, user);
							// System.out.println("MASUK ROLlBACK OPEN CLAIM");
							// }

							redirectUrl = "claimitem?navigation=addclaimitem&claimId=" + res.getClaimId() + "&caseId="
									+ res.getCaseId().getCaseId();
						} else if (caseNavigation.equalsIgnoreCase("approvecase")) {
							redirectUrl = "case?navigation=approve&caseId=" + res.getCaseId().getCaseId() + "&claimId="
									+ res.getClaimId();
						} else {// Add 20150410, Default redirect when edit
							redirectUrl = "claim?navigation=detail&alert=" + alertMsg + "&claimId=" + res.getClaimId();
						}
					} else {
						alertMsg = alertProperties.getMessage("fail.update.claim", null, "fail", Locale.getDefault());

						redirectUrl = "claim?navigation=detail&alert=" + alertMsg + "&claimId="
								+ claimForm.getClaimId();
					}

				}
			} else {
				request.setAttribute("alert",
						alertProperties.getMessage("fail.create.claim", null, "fail", Locale.getDefault()));
				return showForm(request, response, errors);
			}
		} catch (Exception ex) {
			if (claimForm.isNewClaim()) {
				request.setAttribute("alert",
						alertProperties.getMessage("fail.create.claim", null, "fail", Locale.getDefault()) + " "
								+ ex.getMessage());
			} else {
				request.setAttribute("alert",
						alertProperties.getMessage("fail.update.claim", null, "fail", Locale.getDefault()) + " "
								+ ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}

		return new ModelAndView(new RedirectView(redirectUrl));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {

		super.initBinder(req, binder);

		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		nf.setGroupingUsed(true);

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, req.getLocale());

		CustomDateEditor cde = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, cde);

		CustomNumberEditor num = new CustomNumberEditor(Number.class, nf, true);
		binder.registerCustomEditor(Number.class, num);

		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numInt);

		binder.registerCustomEditor(java.lang.Double.class, new CustomNumberEditor(java.lang.Double.class, nf, true));

	}

	// class+

	public TreatmentLocationService getTreatmentLocationService() {
		return treatmentLocationService;
	}

	public void setTreatmentLocationService(TreatmentLocationService treatmentLocationService) {
		this.treatmentLocationService = treatmentLocationService;
	}

	public DependentService getDependentService() {
		return dependentService;
	}

	public void setDependentService(DependentService dependentService) {
		this.dependentService = dependentService;
	}

	// class-

}
