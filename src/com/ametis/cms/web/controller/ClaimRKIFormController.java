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
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
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
import com.ametis.cms.service.DependentService;
import com.ametis.cms.service.DiagnosisService;
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

// imports+ 

// imports- 

/**
 * Claim is a mapping of claim Table.
 */
public class ClaimRKIFormController extends SimpleFormController
// extends+

// extends-

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

	public void setTreatmentUpgradeTypeService(
			TreatmentUpgradeTypeService treatmentUpgradeTypeService) {
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

	public void setClaimTypeService(ClaimTypeService obj) {
		this.claimTypeService = obj;
	}

	public ClaimTypeService getClaimTypeService() {
		return this.claimTypeService;
	}

	CaseStatusService caseStatusService;

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

	public ClaimRKIFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ClaimForm tmp = null;
		Integer claimId = WebUtil.getParameterInteger(request, "claimId");

		String navigation = request.getParameter("navigation");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (claimId != null) {
			java.io.Serializable pkey = claimId;
			Claim object = claimService.get(pkey);

			if (object != null) { // edit object

				tmp = new ClaimForm(object);
				// foreign affairs

				tmp.setClaimTypeId(object.getClaimTypeId().getClaimTypeId().toString());

				tmp.setClaimStatus(""
						+ object.getClaimStatus().getCaseStatusId());

				tmp.setMemberId(object.getMemberId().getMemberId().toString());

				tmp.setName(object.getMemberId().getFirstName());

				tmp.setPolicyNumber(object.getMemberId()
						.getCustomerPolicyNumber());

				

				

				if (object.getDiagnosisId() != null) {
					tmp.setDiagnosis1Code(object.getDiagnosisId()
							.getDiagnosisCode());
					tmp.setDiagnosis1Text(object.getDiagnosisId()
							.getDescription());
					tmp.setDiagnosisId(object.getDiagnosisId().getDiagnosisId().toString());
				}
				if (object.getDiagnosis2Id() != null) {
					tmp.setDiagnosis2Code(object.getDiagnosis2Id()
							.getDiagnosisCode());
					tmp.setDiagnosis2Text(object.getDiagnosis2Id()
							.getDescription());
					tmp.setDiagnosis2Id(object.getDiagnosis2Id().getDiagnosisId().toString());
				}

				if (object.getDiagnosis3Id() != null) {
					tmp.setDiagnosis3Code(object.getDiagnosis3Id()
							.getDiagnosisCode());
					tmp.setDiagnosis3Text(object.getDiagnosis3Id()
							.getDescription());
					tmp.setDiagnosis3Id(object.getDiagnosis3Id().getDiagnosisId().toString());
				}

				if (object.getBatchClaimId() != null) {
					tmp.setBatchClaimId(object.getBatchClaimId().getBatchClaimId().toString());

					tmp.setPaymentRecipient(object.getBatchClaimId()
							.getPaymentRecipient().getPaymentRecipientId()
							.toString());
					tmp.setClaimTypeId(object.getBatchClaimId()
							.getBatchClaimType().getClaimTypeId().toString());

				}

				if (object.getCaseId() != null) {

					tmp.setCaseId(object.getCaseId().getCaseId().toString());
					tmp.setCaseNumber(object.getCaseId().getCaseNumber());

					tmp.setPaymentRecipient(PaymentRecipient.PROVIDER + "");
					
					tmp.setClaimTypeId(ClaimType.CASHLESS+"");
				}

				if (object.getLocationId() != null) {
					tmp.setLocationId(object.getLocationId().getLocationId()
							.toString());
				}
				if (object.getCaseCategoryId() != null){
					tmp.setCaseCategoryId(object.getCaseCategoryId().getCaseCategoryId().toString());
				}

				if (object.getProviderId() != null) {
					tmp.setProviderId(object.getProviderId().getProviderId()
							.toString());
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
				Integer claimTypeId = WebUtil.getParameterInteger(request,
						"claimTypeId");

				if (claimTypeId != null) {
					
					tmp.setClaimTypeId(claimTypeId.toString());

					// ClaimType claimType = this.claimTypeService
					// .get(claimTypeId);
					// tmp.getClaim().setClaimTypeId(claimType);
				} else {
					
				}

				Integer claimStatus = WebUtil.getParameterInteger(request,
						"claimStatus");

				if (claimStatus != null) {
					CaseStatus forClass = new CaseStatus();
					forClass.setCaseStatusId(claimStatus);
					tmp.setClaimStatus("" + claimStatus);

					CaseStatus caseStatus = this.caseStatusService
							.get(claimStatus);
					tmp.getClaim().setClaimStatus(caseStatus);
				} else {
					CaseStatus forClass = new CaseStatus();
					// tmp.setClaimStatus("");
					tmp.getClaim().setClaimStatus(forClass);
				}

				Integer memberId = WebUtil.getParameterInteger(request,
						"memberId");

				if (memberId != null) {
					
					tmp.setMemberId(memberId.toString());

					Member member = this.memberService.get(memberId);
					tmp.getClaim().setMemberId(member);
				} else {
					
				}

				Integer diagnosisId = WebUtil.getParameterInteger(request,
						"diagnosisId");

				if (diagnosisId != null) {
					Diagnosis diagnosis = this.diagnosisService
					 .get(diagnosisId);
					tmp.getClaim().setDiagnosisId(diagnosis);
					tmp.setDiagnosisId(diagnosisId.toString());
					tmp.setDiagnosis1Code(diagnosis.getDiagnosisCode());
					tmp.setDiagnosis1Text(diagnosis.getDiagnosisName());
					
				} else {
				}

				Integer diagnosis2Id = WebUtil.getParameterInteger(request,
						"diagnosis2Id");

				if (diagnosis2Id != null) {
					Diagnosis diagnosis = this.diagnosisService
					 .get(diagnosisId);
					
					tmp.getClaim().setDiagnosis2Id(diagnosis);
					tmp.setDiagnosis2Id(diagnosisId.toString());
					tmp.setDiagnosis2Code(diagnosis.getDiagnosisCode());
					tmp.setDiagnosis2Text(diagnosis.getDiagnosisName());
				}

				Integer batchClaimId = WebUtil.getParameterInteger(request,
						"batchClaimId");

				if (batchClaimId != null) {
					
					tmp.setBatchClaimId(batchClaimId.toString());

					// BatchClaim batchClaim = this.batchClaimService
					// .get(batchClaimId);
					// tmp.getClaim().setBatchClaimId(batchClaim);
				} 

				Integer caseId = WebUtil.getParameterInteger(request, "caseId");

				if (caseId != null) {

					tmp.setCaseId(caseId.toString());

					// Case myCase = this.myCaseService.get(caseId);
					// tmp.getClaim().setCaseId(myCase);
				} 

				Integer caseCategoryId = WebUtil.getParameterInteger(request,
						"caseCategoryId");

				if (caseCategoryId != null) {
					
					tmp.setCaseCategoryId(caseCategoryId.toString());
				}

				Integer providerId = WebUtil.getParameterInteger(request,
						"providerId");

				if (providerId != null) {

					tmp.setProviderId(providerId.toString());

					// Provider provider = this.providerService.get(providerId);
					// tmp.getClaim().setProviderId(provider);
				} else {

				}

				Integer paymentId = WebUtil.getParameterInteger(request,
						"paymentId");

				if (paymentId != null) {
					Payment forClass = new Payment();
					forClass.setPaymentId(paymentId);
					tmp.setPaymentId(forClass);

					// Payment payment = this.paymentService.get(paymentId);
					// tmp.getClaim().setPaymentId(payment);
				}

				// -- foreign affairs end
			}
			String breadcrumb = "<a href=\"claim?navigation=detail&claimId="
					+ claimId
					+ "\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Claim";
			request.setAttribute("breadcrumb", breadcrumb);
		} // mau edit end
		else { // bikin baru
			tmp = new ClaimForm();
			// foreign affairs

			tmp.setLocationId(null);
			Integer claimTypeId = WebUtil.getParameterInteger(request,
					"claimTypeId");

			if (claimTypeId != null) {
				
				tmp.setClaimTypeId(claimTypeId.toString());

				// ClaimType claimType = this.claimTypeService.get(claimTypeId);
				// tmp.getClaim().setClaimTypeId(claimType);
			} else {
				
			}

			Integer claimStatus = WebUtil.getParameterInteger(request,
					"claimStatus");

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

			Integer diagnosisId = WebUtil.getParameterInteger(request,
					"diagnosisId");

			if (diagnosisId != null) {

				Diagnosis diagnosis = this.diagnosisService.get(diagnosisId);
				
				if (diagnosis != null){
					tmp.getClaim().setDiagnosisId(diagnosis);
					tmp.setDiagnosis1Code(diagnosis.getDiagnosisCode());
					tmp.setDiagnosis1Text(diagnosis.getDiagnosisName());
					tmp.setDiagnosisId(diagnosis.getDiagnosisId().toString());
				}
				
				
			} else {
			}

			Integer diagnosis2Id = WebUtil.getParameterInteger(request,
					"diagnosis2Id");

			if (diagnosis2Id != null) {
				
				tmp.setDiagnosis2Id(diagnosis2Id.toString());

				Diagnosis diagnosis =
					 this.diagnosisService.get(diagnosis2Id);
				 
				tmp.getClaim().setDiagnosis2Id(diagnosis);
				tmp.setDiagnosis2Text(diagnosis.getDiagnosisName());
				tmp.setDiagnosis2Code(diagnosis.getDiagnosisCode());
				 
			}
			Integer diagnosis3Id = WebUtil.getParameterInteger(request,
					"diagnosis3Id");
			if (diagnosis3Id != null) {
				
				tmp.setDiagnosis3Id(diagnosis3Id.toString());

			} else {
			}
			Integer batchClaimId = WebUtil.getParameterInteger(request,
					"batchClaimId");

			if (batchClaimId != null) {

				tmp.setBatchClaimId(batchClaimId.toString());

				BatchClaim batchClaim = this.batchClaimService
						.get(batchClaimId);

				if (batchClaim != null) {
					tmp.getClaim().setBatchClaimId(batchClaim);
					tmp.setBatchClaimNumber(batchClaim.getBatchClaimNumber());

					ClaimType ctype = batchClaim.getBatchClaimType();
					tmp.setClaimDate(batchClaim.getBatchClaimDate().toString());
					tmp.setClaimTypeId(ctype.getClaimTypeId().toString());
					tmp.setPaymentRecipient(batchClaim.getPaymentRecipient()
							.getPaymentRecipientId().toString());

					if (ctype != null) {

						if (ctype.getClaimTypeId().intValue() == ClaimType.CASHLESS) {
							tmp.getClaim().setLocationId(
									batchClaim.getProviderId()
											.getProviderLocation());
							tmp.setProviderId(batchClaim.getProviderId()
									.getProviderId().toString());
							tmp.setProviderName(batchClaim.getProviderId()
									.getProviderName());

						} else {

							tmp.setProviderName("");
						}
						tmp.getClaim().setClaimTypeId(ctype);

					}

					if (batchClaim.getPaymentRecipient()
							.getPaymentRecipientId() == PaymentRecipient.MEMBER) {

						Member member = batchClaim.getMemberId();
						System.out.println("GETTING MEMBER INFORMATION : "
								+ member);

						if (member != null) {

							tmp.setName(StringUtil.getStringValue(member
									.getFirstName(), "")
									+ " "
									+ StringUtil.getStringValue(member
											.getLastName(), ""));
							tmp.setPolicyNumber(StringUtil.getStringValue(
									member.getCustomerPolicyNumber(), ""));
							tmp.setMemberId(member.getMemberId().toString());
							// tmp.set
						}

					}
					if (batchClaim.getPaymentRecipient()
							.getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP) {

						MemberGroup memberGroup = batchClaim.getMemberGroupId();

						if (memberGroup != null) {

							tmp.setMemberGroupId(memberGroup.getMemberGroupId()
									.toString());
							// tmp.set
						}

					}
					if (batchClaim.getTotalClaim().intValue() == 1) {
						tmp.setClaimChargeValue(batchClaim
								.getBatchClaimInitialValue()
								+ "");
						tmp.getClaim().setClaimChargeValue(
								batchClaim.getBatchClaimInitialValue());
					}

				}
			} else {
			}

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			if (caseCategoryId != null) {
				
				tmp.setCaseCategoryId(caseCategoryId.toString());

			} else {
			}

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			if (providerId != null) {

				tmp.setProviderId(providerId.toString());

			} else {

			}

			Integer paymentId = WebUtil.getParameterInteger(request,
					"paymentId");

			if (paymentId != null) {

				Payment forClass = new Payment();
				forClass.setPaymentId(paymentId);
				tmp.setPaymentId(forClass);

			} else {
				Payment forClass = new Payment();
				// tmp.setPaymentId("");
				tmp.getClaim().setPaymentId(forClass);
			}

			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			if (caseId != null) {

				Case refCase = myCaseService.get(caseId);
				if (refCase != null) {
					tmp.setCaseId(caseId.toString());
					tmp.setAdmissionDate(refCase.getCaseStartTime().toString());
					// tmp.setDischargeDate(refCase.getCaseEndTime().toString());
					// // sementara dihilangkan karena permintaan
					if (refCase.getCaseCategoryId() != null){
						tmp.setCaseCategoryId(refCase.getCaseCategoryId().getCaseCategoryId().toString());
					}
					tmp.setCaseNumber(refCase.getCaseNumber());
					
					if (refCase.getDiagnosis1Id() != null) {
						tmp.setDiagnosis1Code(refCase.getDiagnosis1Id()
								.getDiagnosisCode());
						tmp.setDiagnosis1Text(refCase.getDiagnosis1Id()
								.getDiagnosisName());
						tmp.setDiagnosisId(refCase.getDiagnosis1Id().getDiagnosisId().toString());
						
					}
					tmp.setProviderId(refCase.getProviderId().getProviderId()
							.toString());
					tmp.setProviderName(refCase.getProviderId()
							.getProviderName());
					tmp.setMemberId(refCase.getMemberId().getMemberId().toString());
					tmp.setPatientName(refCase.getMemberId().getFirstName());
					tmp.setPolicyNumber(refCase.getMemberId()
							.getCustomerPolicyNumber());
					tmp.setName(refCase.getMemberId().getFirstName());
					tmp.setClientId(refCase.getMemberId().getClientId()
							.getClientId().toString());

					tmp.setPaymentRecipient(PaymentRecipient.PROVIDER + "");
					
					tmp.setClaimTypeId(ClaimType.CASHLESS+"");
				}

			} else {
				// Case forClass = new Case();
				// tmp.setCaseId("");
				// tmp.getClaim().setCaseId(forClass);
			}

			// -- foreign affairs end

			String breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="
					+ batchClaimId
					+ "\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Claim";
			request.setAttribute("breadcrumb", breadcrumb);
			request.setAttribute("navigation", navigation);

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ClaimForm claimForm = (ClaimForm) command;

		Claim claim = claimForm.getClaim();
		errors.printStackTrace();
		System.out.println("ERROR : " + errors);
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

		Integer memberId = WebUtil.getParameterInteger(request, "memberId");

		Integer batchClaimId = WebUtil.getParameterInteger(request,
				"batchClaimId");

		if (batchClaimId != null) {

			BatchClaim batchClaim = batchClaimService.get(batchClaimId);

			if (batchClaim != null
					&& batchClaim.getBatchClaimType().getClaimTypeId()
							.intValue() == ClaimType.REIMBURESEMENT) {

				Member member = batchClaim.getMemberId();

				if (member != null) {
					String[] eqP = { "parentMember.memberId",
							"memberGroupId.clientId.clientId", "deletedStatus" };
					Object[] eqQ = {
							member.getMemberId(),
							member.getMemberGroupId().getClientId()
									.getClientId(), Integer.valueOf(0) };
					int total = memberService.getTotal(null, null, eqP, eqQ);

					System.out.println("HOLA! : " + total);
					Collection dependentBean = memberService.search(null, null,
							eqP, eqQ, 0, total);
					model.put("dependentCollection", dependentBean);
				}
			}
		}

		System.out.println("REFERENCE DATA DIPANGGIL!");
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClaimForm claimForm = (ClaimForm) command;

		Claim res = null;
		String alertMsg = "";
		String redirectUrl = "claim";
		String navigation = WebUtil.getParameterString(request, "navigation",
				"");
		ModelAndView result = null;
		boolean isError = false;
		try {
			// foreign affairs

			if (claimForm.getDiagnosis2() != null
					&& !claimForm.getDiagnosis2().equalsIgnoreCase("")) {
				Diagnosis diagnosis2 = diagnosisService.get(Integer
						.valueOf(claimForm.getDiagnosis2()));

				claimForm.getClaim().setDiagnosis2Id(diagnosis2);
			} else if (claimForm.getDiagnosis3() != null
					&& !claimForm.getDiagnosis3().equalsIgnoreCase("")) {
				Diagnosis diagnosis3 = diagnosisService.get(Integer
						.valueOf(claimForm.getDiagnosis3()));
				claimForm.getClaim().setDiagnosis3Id(diagnosis3);
			} else if (claimForm.getDiagnosis1() != null
					&& !claimForm.getDiagnosis1().equalsIgnoreCase("")) {
				Diagnosis diagnosis1 = diagnosisService.get(Integer
						.valueOf(claimForm.getDiagnosis1()));
				claimForm.getClaim().setDiagnosisId(diagnosis1);
			}

			CaseStatus claimStatus = new CaseStatus();
			claimStatus.setCaseStatusId(Integer.valueOf(Claim.CLAIM_OPEN));
			claimForm.getClaim().setClaimStatus(claimStatus);

			Claim claim = claimForm.getClaim();

			if (claim != null) {

				Date receivedDate = claim.getClaimDate();
				Date admissionDate = claim.getAdmissionDate();
				Date dischargeDate = claim.getDischargeDate();

				if (claim.getCaseId() == null) {
					// check if reimburse or post claim

					if (admissionDate.after(receivedDate)
							|| dischargeDate.after(receivedDate)) {
						isError = true;
					}
					if (admissionDate.after(dischargeDate)) {
						isError = true;
					}
				}

				ClaimType cType = claim.getClaimTypeId();

				if (cType != null && cType.getClaimTypeId() != null) {
					if (cType.getClaimTypeId().intValue() == ClaimType.CASHLESS) {
						
						if (claimForm.getBatchClaimId() != null) {
							BatchClaim bc = batchClaimService.get( Integer.valueOf(claimForm.getBatchClaimId()));
							if (bc != null){
								claim.setProviderId(bc.getProviderId());
							}
						}
						if (claimForm.getCaseId() != null) {

						}
					} else {

						try {
							Integer claimant = Integer.valueOf(claimForm
									.getPatientName());

							Member member = new Member();
							member.setMemberId(claimant);
							claim.setMemberId(member);

						} catch (Exception e) {
							e.printStackTrace();
						}

						String prov = claimForm.getProviderId();

						if (prov == null
								&& !claimForm.getProviderName()
										.equalsIgnoreCase("")) {

							ActionUser user = securityService
									.getActionUser(request);
							ProviderCategory cat = new ProviderCategory();
							cat.setProviderCategoryId(4);
							Provider provider = new Provider();
							provider.setProviderName(claimForm
									.getProviderName());
							provider.setProviderCategoryId(cat);

							Provider providerResult = providerService.create(
									provider, user);
							if (providerResult != null) {
								claim.setProviderId(providerResult);
								claim.setProviderName(claimForm
										.getProviderName());
							}
						} else {
							if (!prov.equalsIgnoreCase("")) {
								Provider provider = providerService.get(Integer
										.valueOf(prov));
								if (provider != null) {
									claim.setProviderId(provider);
									claim.setProviderName(provider
											.getProviderName());
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
				return showForm(request, response, errors);
			}

			if (claimForm.isNewClaim()) {

				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATECLAIM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATECLAIM access");
					return errorResult;

				}

				claimForm.getClaim().setTipe(Claim.CLAIM_SHOW);
				res = claimService.create(claimForm.getClaim(), user);

				if (navigation.equalsIgnoreCase("pending")) {
					// res = claimService.create(object, actionUser)

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.claim", null, "success", Locale
										.getDefault());

						redirectUrl = "pendingclaim-form?navigation=tambah&claimId="
								+ res.getClaimId();

					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.claim", null, "fail", Locale
										.getDefault());
						redirectUrl = "claim" + "?alert=" + alertMsg;
					}
				} else {

					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.create.claim", null, "success", Locale
										.getDefault());
						if (res.getBatchClaimId() != null) {
							if (navigation.equalsIgnoreCase("register")) {
								redirectUrl = "batchclaim?navigation=detail&batchClaimId="
										+ res.getBatchClaimId()
												.getBatchClaimId()
										+ "&alert="
										+ alertMsg;
							} else {
								redirectUrl = "claimitem?navigation=addclaimitem&claimId="
										+ res.getClaimId()
										+ "&batchClaimId="
										+ res.getBatchClaimId()
												.getBatchClaimId()
										+ "&alert="
										+ alertMsg;
							}
						} else {
							if (res.getCaseId() != null) {
								redirectUrl = "claimitem?navigation=addclaimitem&claimId="
										+ res.getClaimId()
										+ "&caseId="
										+ res.getCaseId().getCaseId();
							}
						}

					} else {
						alertMsg = alertProperties.getMessage(
								"fail.create.claim", null, "fail", Locale
										.getDefault());
						redirectUrl = "claim" + "?alert=" + alertMsg;
					}
				}

			} else {

				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATECLAIM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATECLAIM access");
					return errorResult;

				}
				res = claimService.update(claimForm.getClaim(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.claim", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.claim",
							null, "fail", Locale.getDefault());
				}
				redirectUrl = "claim" + "?navigation=detail&alert=" + alertMsg
						+ "&claimId=" + res.getClaimId();

			}
		} catch (Exception ex) {
			if (claimForm.isNewClaim()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.claim", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.claim", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}

		return new ModelAndView(new RedirectView(redirectUrl));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {

		super.initBinder(req, binder);

		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		nf.setGroupingUsed(true);

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, req
				.getLocale());

		CustomDateEditor cde = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, cde);

		CustomNumberEditor num = new CustomNumberEditor(Number.class, nf, true);
		binder.registerCustomEditor(Number.class, num);

		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numInt);

		binder.registerCustomEditor(java.lang.Double.class,
				new CustomNumberEditor(java.lang.Double.class, nf, true));

	}

	// class+

	public TreatmentLocationService getTreatmentLocationService() {
		return treatmentLocationService;
	}

	public void setTreatmentLocationService(
			TreatmentLocationService treatmentLocationService) {
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
