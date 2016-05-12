package com.ametis.cms.web.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.FirstCall;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.datamodel.ProviderTypeDiagnosisTreatment;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.TreatmentUpgradeType;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.CaseStatusService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberDiagnosisExclusionService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.PriorityService;
import com.ametis.cms.service.ProviderDoctorService;
import com.ametis.cms.service.ProviderItemService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderTypeDiagnosisTreatmentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.TreatmentUpgradeTypeService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CaseForm;

// imports+ 

// imports- 

/**
 * Case is a mapping of case Table.
 */
public class CaseFormController extends SimpleFormController
// extends+

// extends-

{

	CaseService myCaseService;
	ResourceBundleMessageSource alertProperties;
	private TreatmentUpgradeTypeService treatmentUpgradeService;
	// foreign affairs

	CaseStatusService caseStatusService;
	private SecurityService securityService;

	private ActivityLogService logService;
	private ProviderItemService providerItemService;
	private ProviderDoctorService providerDoctorService;
	private PoliklinikService poliklinikService;
	private ProviderPoliklinikService providerPoliklinikService;
	private MemberBenefitService memberBenefitService;
	private MemberDiagnosisExclusionService memberDiagnosisExclusionService;
	private ClaimService claimService;
	private MemberProductService memberProductService;
	private PolicyService policyService;
	

	
	
	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MemberDiagnosisExclusionService getMemberDiagnosisExclusionService() {
		return memberDiagnosisExclusionService;
	}

	public void setMemberDiagnosisExclusionService(
			MemberDiagnosisExclusionService memberDiagnosisExclusionService) {
		this.memberDiagnosisExclusionService = memberDiagnosisExclusionService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}

	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}

	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(
			ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}

	public ProviderDoctorService getProviderDoctorService() {
		return providerDoctorService;
	}

	public void setProviderDoctorService(ProviderDoctorService providerDoctorService) {
		this.providerDoctorService = providerDoctorService;
	}

	public ProviderItemService getProviderItemService() {
		return providerItemService;
	}

	public void setProviderItemService(ProviderItemService providerItemService) {
		this.providerItemService = providerItemService;
	}

	public TreatmentUpgradeTypeService getTreatmentUpgradeService() {
		return treatmentUpgradeService;
	}

	public void setTreatmentUpgradeService(
			TreatmentUpgradeTypeService treatmentUpgradeService) {
		this.treatmentUpgradeService = treatmentUpgradeService;
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

	public void setCaseStatusService(CaseStatusService obj) {
		this.caseStatusService = obj;
	}

	public CaseStatusService getCaseStatusService() {
		return this.caseStatusService;
	}

	PriorityService priorityService;

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

	// Ini inisialisasi apa sih?
	ProviderService providerService;

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	ItemService itemService;

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	CaseCategoryService caseCategoryService;

	public void setCaseCategoryService(CaseCategoryService obj) {
		this.caseCategoryService = obj;
	}

	public CaseCategoryService getCaseCategoryService() {
		return this.caseCategoryService;
	}

	// -- foreign affairs end

	public void setCaseService(CaseService object) {
		this.myCaseService = object;
	}

	public CaseService getCaseService() {
		return this.myCaseService;
	}

	// generate by default
	private UserService userService;
	private ProviderTypeDiagnosisTreatmentService inaCBGService;

	public ProviderTypeDiagnosisTreatmentService getInaCBGService() {
		return inaCBGService;
	}

	public void setInaCBGService(ProviderTypeDiagnosisTreatmentService inaCBGService) {
		this.inaCBGService = inaCBGService;
	}

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

	public CaseFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);

		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
		CustomNumberEditor intNum = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, intNum);

	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CaseForm tmp = null;
		Integer caseId = WebUtil.getParameterInteger(request, "caseId");
		Integer caseType = WebUtil.getParameterInteger(request, "caseType");
		Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");
		
		Policy currentPolicy = null;
		
		
		ActionUser actionUser = securityService.getActionUser(request);

		request.setAttribute("caseType", caseType);

		
		if (caseId != null) {

			java.io.Serializable pkey = caseId;
			Case object = myCaseService.get(pkey);

			if (object != null) { // edit object

				
				Member member = memberService.get(object.getMemberId().getMemberId());
				tmp = new CaseForm(object);
				// foreign affairs

				tmp.setClientName(member.getClientName());
				tmp.setGroupName(member.getGroupName());
				tmp.setCaseStatusId(""
						+ object.getCaseStatusId().getCaseStatusId());

				tmp.setMemberId(object.getMemberId());

				if (object.getDiagnosis1Id() != null){
					
					Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis1Id().getDiagnosisId());
					tmp.setDiagnosis1Id(object.getDiagnosis1Id().getDiagnosisId().toString());
					tmp.setDiagnosis1Text(diagnosis.getDescription());
				}

				if (object.getDiagnosis2Id() != null){
					tmp.setDiagnosis2Id(object.getDiagnosis2Id().getDiagnosisId().toString());
					
					Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis2Id().getDiagnosisId());
					
					tmp.setDiagnosis2Text(diagnosis.getDescription());
				}

				if (object.getDiagnosis3Id() != null){
					Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis1Id().getDiagnosisId());
					
					tmp.setDiagnosis3Text(diagnosis.getDescription());
					
					tmp.setDiagnosis3Id(object.getDiagnosis3Id().getDiagnosisId().toString());
				}

				if (object.getProviderId() != null){
					
					Provider provider = providerService.get(object.getProviderId().getProviderId());
					if (provider != null){
						tmp.setProviderId(object.getProviderId().getProviderId().toString());
						tmp.setProviderName(provider.getProviderName());
					}
				}

				if (object.getItemId() != null){
					tmp.setItemId(object.getItemId());
				}

				if (object.getPriorityId() != null){
					tmp.setPriorityId(object.getPriorityId().getPriorityId().toString());
				}

				if (object.getCaseCategoryId() != null){
					tmp.setCaseCategoryId(object.getCaseCategoryId().getCaseCategoryId().toString());
				}
				

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseForm();
				// foreign affairs

				Integer caseStatusId = WebUtil.getParameterInteger(request,
						"caseStatusId");

				if (caseStatusId != null) {
					CaseStatus forClass = new CaseStatus();
					forClass.setCaseStatusId(caseStatusId);
					tmp.setCaseStatusId("" + caseStatusId);

					CaseStatus caseStatus = this.caseStatusService
							.get(caseStatusId);
					tmp.getCase().setCaseStatusId(caseStatus);
				} else {
				
				}

				Integer memberId = WebUtil.getParameterInteger(request,
						"memberId");

				if (memberId != null) {
					Member forClass = new Member();
					forClass.setMemberId(memberId);
					tmp.setMemberId(forClass);

					Member member = this.memberService.get(memberId);
					tmp.getCase().setMemberId(member);
				} else {
				
				}

				Integer diagnosis1Id = WebUtil.getParameterInteger(request,
						"diagnosis1Id");

				if (diagnosis1Id != null) {
					

					Diagnosis diagnosis = this.diagnosisService
							.get(diagnosis1Id);
					if (diagnosis != null){
						tmp.setDiagnosis1Id(diagnosis.getDiagnosisId().toString());
						tmp.getCase().setDiagnosis1Id(diagnosis);
					}
				} else {
				}

				Integer diagnosis2Id = WebUtil.getParameterInteger(request,
						"diagnosis2Id");

				if (diagnosis2Id != null) {
					

					Diagnosis diagnosis = this.diagnosisService
							.get(diagnosis2Id);
					if (diagnosis != null){
						tmp.setDiagnosis2Id(diagnosis.getDiagnosisId().toString());
						tmp.getCase().setDiagnosis2Id(diagnosis);
					}
				} else {
					// Diagnosis forClass = new Diagnosis();
					// tmp.setDiagnosis2Id("");
					// tmp.getCase().setDiagnosis2Id(forClass);
				}

				Integer diagnosis3Id = WebUtil.getParameterInteger(request,
						"diagnosis3Id");

				if (diagnosis3Id != null) {
					

					Diagnosis diagnosis = this.diagnosisService
							.get(diagnosis3Id);
					if (diagnosis != null){
						tmp.setDiagnosis3Id(diagnosis.getDiagnosisId().toString());
						tmp.getCase().setDiagnosis3Id(diagnosis);
					}
				} else {
					// Diagnosis forClass = new Diagnosis();
					// tmp.setDiagnosis3Id("");
					// tmp.getCase().setDiagnosis3Id(forClass);
				}

				Integer providerId = WebUtil.getParameterInteger(request,
						"providerId");

				if (providerId != null) {
					tmp.setProviderId(providerId.toString());

					Provider provider = this.providerService.get(providerId);
					tmp.getCase().setProviderId(provider);
				} else {
				
				}

				if (caseCategoryId != null) {
					
					tmp.setCaseCategoryId(caseCategoryId.toString());

					CaseCategory caseCategory = this.caseCategoryService
							.get(caseCategoryId);
					tmp.getCase().setCaseCategoryId(caseCategory);
				} else {
				
				}

				// -- foreign affairs end
			}
			String breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Guarantee Letter";
			request.setAttribute("breadcrumb", breadcrumb);
		} // mau edit end
		else { // bikin baru
			tmp = new CaseForm();


			Integer memberId = WebUtil.getParameterInteger(request, "memberId");

			if (memberId != null) {
				Member forClass = new Member();
				forClass.setMemberId(memberId);
				tmp.setMemberId(forClass);

				String[] required = {"Member.ClientId","Member.MemberGroupId","Member.CurrentPolicyId"};
				Member member = this.memberService.get(memberId,required);
				
				if (member != null && member.getCurrentPolicyId() != null){
					currentPolicy = member.getCurrentPolicyId();
				}
				
				tmp.getCase().setMemberId(member);
				tmp.setGroupName(member.getGroupName());
				tmp.setClientName(member.getClientName());
				tmp.setRelationshipName(member.getRelationship());
				tmp.setMemberStatus(member.getStatus().toString());
				
				if(member.getMemberGroupId()!=null){
					if(member.getMemberGroupId().getExpireDate()!=null)
						tmp.setGroupExpireDate(member.getMemberGroupId().getExpireDate().toString());
				}
				
				
				
			} else {
				
			}

			Integer diagnosis1Id = WebUtil.getParameterInteger(request,
					"diagnosis1Id");

			if (diagnosis1Id != null) {
				

				Diagnosis diagnosis = this.diagnosisService.get(diagnosis1Id);
				if (diagnosis != null){
					tmp.setDiagnosis1Id(diagnosis.getDiagnosisId().toString());
					tmp.getCase().setDiagnosis1Id(diagnosis);
				}
			} else {
				
			}

			Integer diagnosis2Id = WebUtil.getParameterInteger(request,
					"diagnosis2Id");

			if (diagnosis2Id != null) {
				

				Diagnosis diagnosis = this.diagnosisService.get(diagnosis2Id);
				if (diagnosis != null){
					tmp.setDiagnosis2Id(diagnosis.getDiagnosisId().toString());
					tmp.getCase().setDiagnosis2Id(diagnosis);
				}
			} else {
				
			}

			Integer diagnosis3Id = WebUtil.getParameterInteger(request,
					"diagnosis3Id");

			if (diagnosis3Id != null) {
				

				Diagnosis diagnosis = this.diagnosisService.get(diagnosis3Id);
				if (diagnosis != null){
					tmp.setDiagnosis3Id(diagnosis.getDiagnosisId().toString());
					tmp.getCase().setDiagnosis3Id(diagnosis);
				}
			} else {

			}

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

			if (providerId != null) {
				
				tmp.setProviderId(providerId.toString());

				Provider provider = this.providerService.get(providerId);
				tmp.getCase().setProviderId(provider);
			} else {
				
			}

	

			Integer priorityId = WebUtil.getParameterInteger(request,
					"priorityId");

			if (priorityId != null) {
				Priority forClass = new Priority();
				forClass.setPriorityId(priorityId);
				tmp.setPriorityId(priorityId.toString());

				tmp.getCase().setPriorityId(forClass);
			} else {
				
			}


			if (caseCategoryId != null) {
				
				tmp.setCaseCategoryId(caseCategoryId.toString());

				CaseCategory caseCategory = this.caseCategoryService
						.get(caseCategoryId);
				tmp.getCase().setCaseCategoryId(caseCategory);
			} else {
				
			}
			
			

			// -- foreign affairs end
			String breadcrumb = "<a href=\"member?navigation=detail&memberId="+memberId+"\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Guarantee Letter";
			request.setAttribute("breadcrumb", breadcrumb);
		}
		
		
		if (actionUser != null && actionUser.getUser().getUserType().intValue() == User.PROVIDER){
			
			Provider provider = actionUser.getUser().getProviderId();
			
			String[] required = {"Provider.ProviderCategoryId"};
			provider = providerService.get(provider.getProviderId(),required);
			request.setAttribute("provider", provider);
			tmp.setProviderId(provider.getProviderId().toString());			
			tmp.setProviderName(provider.getProviderName());
	
			if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.RUMAH_SAKIT){
				
				String[] eqParamItem = {"providerId.providerId","itemId.itemCategoryId.itemCategoryId"};
				Object[] eqValueItem = {provider.getProviderId(),ItemCategory.POLIKLINIK};
				int totalItem = providerItemService.getTotal(null,null,eqParamItem,eqValueItem);
				Collection<ProviderItem> itemList = providerItemService.search(null,null,eqParamItem,eqValueItem,0,totalItem);
				
				String[] eqParamDoctor = {"providerId.providerId"};
				Object[] eqValueDoctor = {provider.getProviderId()};
				int totalDoctor = providerDoctorService.getTotal(null,null,eqParamDoctor,eqValueDoctor);
				Collection<ProviderDoctor> doctorList = providerDoctorService.search(null,null,eqParamDoctor,eqValueDoctor,0,totalDoctor);
				
				if (currentPolicy != null && currentPolicy.getPolicyType().intValue() == Policy.MANAGED_CARE_POLICY){
				
					String[] eqParamBen = {"caseCategoryId.caseCategoryId","deletedStatus","memberId.memberId","memberBenefitStatus"};
					Object[] eqValueBen = {CaseCategory.SPECIALIST,0,tmp.getMemberId().getMemberId(), SubscriptionStatus.ACTIVE};
					
					int totalBenefit = memberBenefitService.getTotal(null,null,eqParamBen,eqValueBen);
					Collection<MemberBenefit> benefitList = memberBenefitService.search(null,null,eqParamBen,eqValueBen,0,totalBenefit);
					
					request.setAttribute("benefitList", benefitList);
					request.setAttribute("poliklinikList", itemList);
					request.setAttribute("doctorList", doctorList);
					
				}
				else if (currentPolicy != null && currentPolicy.getPolicyType().intValue() == Policy.INDEMNITY_POLICY){
					String[] eqParamBen = {"caseCategoryId.caseCategoryId","deletedStatus","memberId.memberId","memberBenefitStatus"};
					Object[] eqValueBen = {caseCategoryId,0,tmp.getMemberId().getMemberId(), SubscriptionStatus.ACTIVE};
					
					int totalBenefit = memberBenefitService.getTotal(null,null,eqParamBen,eqValueBen);
					Collection<MemberBenefit> benefitList = memberBenefitService.search(null,null,eqParamBen,eqValueBen,0,totalBenefit);
					
					request.setAttribute("benefitList", benefitList);
					request.setAttribute("poliklinikList", itemList);
					request.setAttribute("doctorList", doctorList);
				}
				
			}
			else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK1_UMUM){
				
				String[] eqParamItem = {"providerId.providerId"};
				Object[] eqValueItem = {provider.getProviderId()};
			
				int totalItem = providerItemService.getTotal(null,null,eqParamItem,eqValueItem);
				Collection<ProviderItem> itemList = providerItemService.search(null,null,eqParamItem,eqValueItem,0,totalItem);
		
				if (currentPolicy != null && currentPolicy.getPolicyType().intValue() == Policy.MANAGED_CARE_POLICY){
					
					String[] eqParamBen = {"caseCategoryId.caseCategoryId","deletedStatus","memberId.memberId","memberBenefitStatus"};
					Object[] eqValueBen = {CaseCategory.GP_OUTPATIENT,0,tmp.getMemberId().getMemberId(),SubscriptionStatus.ACTIVE};
					
					int totalBenefit = memberBenefitService.getTotal(null,null,eqParamBen,eqValueBen);
					Collection<MemberBenefit> benefitList = memberBenefitService.search(null,null,eqParamBen,eqValueBen,0,totalBenefit);
					
					
					
					request.setAttribute("poliklinikList", itemList);
					request.setAttribute("benefitList", benefitList);
					
					Collection<Poliklinik> referedPoliklinik = poliklinikService.getAll();
					request.setAttribute("referedPoliklinikList", referedPoliklinik);
				}
				else if (currentPolicy != null && currentPolicy.getPolicyType().intValue() == Policy.INDEMNITY_POLICY){
					String[] eqParamBen = {"caseCategoryId.caseCategoryId","deletedStatus","memberId.memberId","memberBenefitStatus"};
					Object[] eqValueBen = {caseCategoryId,0,tmp.getMemberId().getMemberId(), SubscriptionStatus.ACTIVE};
					
					int totalBenefit = memberBenefitService.getTotal(null,null,eqParamBen,eqValueBen);
					Collection<MemberBenefit> benefitList = memberBenefitService.search(null,null,eqParamBen,eqValueBen,0,totalBenefit);
					
					request.setAttribute("benefitList", benefitList);
					request.setAttribute("poliklinikList", itemList);
					
				}
			}
			else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK1_GIGI){
				String[] eqParamItem = {"providerId.providerId"};
				Object[] eqValueItem = {provider.getProviderId()};
				int totalItem = providerItemService.getTotal(null,null,eqParamItem,eqValueItem);
				Collection<ProviderItem> itemList = providerItemService.search(null,null,eqParamItem,eqValueItem,0,totalItem);
				
				if (currentPolicy != null && currentPolicy.getPolicyType().intValue() == Policy.MANAGED_CARE_POLICY){
				
					String[] eqParamBen = {"caseCategoryId.caseCategoryId","deletedStatus","memberId.memberId","memberBenefitStatus"};
					Object[] eqValueBen = {CaseCategory.GP_DENTAL,0,tmp.getMemberId().getMemberId(), SubscriptionStatus.ACTIVE};
					
					int totalBenefit = memberBenefitService.getTotal(null,null,eqParamBen,eqValueBen);
					Collection<MemberBenefit> benefitList = memberBenefitService.search(null,null,eqParamBen,eqValueBen,0,totalBenefit);
					
					request.setAttribute("benefitList", benefitList);
					request.setAttribute("poliklinikList", itemList);
					
					Collection<Poliklinik> referedPoliklinik = poliklinikService.getAll();
					request.setAttribute("referedPoliklinikList", referedPoliklinik);
				}
				else if (currentPolicy != null && currentPolicy.getPolicyType().intValue() == Policy.INDEMNITY_POLICY){
					String[] eqParamBen = {"caseCategoryId.caseCategoryId","deletedStatus","memberId.memberId","memberBenefitStatus"};
					Object[] eqValueBen = {caseCategoryId,0,tmp.getMemberId().getMemberId(), SubscriptionStatus.ACTIVE};
					
					int totalBenefit = memberBenefitService.getTotal(null,null,eqParamBen,eqValueBen);
					Collection<MemberBenefit> benefitList = memberBenefitService.search(null,null,eqParamBen,eqValueBen,0,totalBenefit);
					
					request.setAttribute("benefitList", benefitList);
					request.setAttribute("poliklinikList", itemList);
					
				}
			}
			
			
		}
		else if (actionUser.getUser()!= null && actionUser.getUser().getUserType().intValue() == User.TPA) {
			String name = actionUser.getUser().getFirstName();
			tmp.setCaseHandler(name);
		}
		
		if (tmp.getProviderId() != null && tmp.getMemberId() != null){
			String[] eqParam = {"memberId.memberId","referalStatus","deletedStatus"};
			Object[] eqValue = {tmp.getMemberId().getMemberId(),	Integer.valueOf(0),Integer.valueOf(0)};
			
			Collection<Case> referalList = myCaseService.search(null,null,eqParam,eqValue,0,5);			
			request.setAttribute("referalList", referalList);
		}
		tmp.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()).toString());


		result = tmp;
		return result;
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		Integer caseType = WebUtil.getParameterInteger(request, "caseType");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		

		String test = request.getParameter("member_id");
		// Integer memberId =
		// WebUtil.getAttributeInteger(request,"member_id",0);
		Integer memberId = null;

		try {
			memberId = Integer.valueOf(test);
		} catch (Exception e) {

		}
		if (memberId != null) {

			Member member = memberService.get(memberId);
			if (member != null) {
				model.put("memberBean", member);
			}
		}

		Collection hours = new Vector();
		String tmp = "";
		for (int i = 0; i < 24; i++) {
			tmp = "";
			if (i < 10) {
				tmp = "0" + i;
			} else {
				tmp = "" + i;
			}
			hours.add(tmp);
		}
		model.put("hours", hours);

		Collection minutes = new Vector();
		for (int i = 0; i < 60; i++) {
			tmp = "";
			if (i < 10) {
				tmp = "0" + i;
			} else {
				tmp = "" + i;
			}
			minutes.add(tmp);
		}
		model.put("minutes", minutes);

		Collection caseCategory = null;
		// Collection diagnosisId = diagnosisService.getAll();

		model.put("caseType", caseType);

		Collection<Priority> priorities = priorityService.getAll();
		model.put("priorities", priorities);
		// model.put("diagnosisId", diagnosisId);

		if (caseType != null) {
			if (caseType.intValue() == FirstCall.INPATIENT) {
				// caseCategory = caseCategoryService.searchInpatientCategory();
				caseCategory = caseCategoryService.getAll();
			} else if (caseType.intValue() == FirstCall.OUTPATIENT) {
				caseCategory = caseCategoryService.searchOutpatientCategory();
			} else {
				caseCategory = caseCategoryService.getAll();
			}
		} else {
			caseCategory = caseCategoryService.getAll();
		}
		
		Collection<TreatmentUpgradeType> upgradeList = treatmentUpgradeService.getAll();
		model.put("treatmentUpgradeList", upgradeList);
		model.put("caseCategoryId", caseCategory);
		model.put("navigation", navigation);

		
		if (navigation.equalsIgnoreCase("preexgratia")){
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			
			String[] requiredCase = {"Case.MemberId","Case.MemberId.MemberGroupId","Case.ClaimId","Case.Diagnosis2Id","Case.Diagnosis3Id"};
			
			Case object = myCaseService.get(caseId,requiredCase);
			
			MemberProduct activeProduct = memberProductService.getMemberActiveProduct(object.getMemberId().getMemberId(), object.getCaseCategoryId().getCaseCategoryId());
			if (activeProduct != null){
				int disabilityLength = activeProduct.getDisabilityLength() == null ? 60 : activeProduct.getDisabilityLength();
				
				
				DateTime dtAdmision = new DateTime(object.getCaseStartTime().getTime());
				
				DateTime lastDate = dtAdmision.minusDays(disabilityLength);
				
				java.sql.Date minDate = new java.sql.Date(lastDate.getMillis());
				java.sql.Date maxDate = new java.sql.Date(dtAdmision.getMillis());
				
				Integer diagnosisId = null;
				Integer caseCategoryId = object.getCaseCategoryId().getCaseCategoryId();
				
				if (object.getDiagnosis1Id() != null){
					diagnosisId = object.getDiagnosis1Id().getDiagnosisId();
					
					int totalClaim = claimService.getClaimDisabilityDiagnosis(diagnosisId,caseCategoryId,object.getMemberId().getMemberId(), minDate, maxDate);
					
					if (totalClaim > 0){
						model.put("diagnosis1Status", "disability");
					}
					else {
						model.put("diagnosis1Status", "nodisability");
					}
					
					boolean diag1exclude = memberDiagnosisExclusionService.isExcludedDiagnosis(diagnosisId, object.getMemberId().getMemberId());
					if (diag1exclude){
						model.put("diag1exstat", "exclusion");
					}
				}
				
				if (object.getDiagnosis2Id() != null){
					boolean diag2Exclude = memberDiagnosisExclusionService.isExcludedDiagnosis(object.getDiagnosis2Id().getDiagnosisId(), object.getMemberId().getMemberId());
					
					if (diag2Exclude){
						model.put("diag2exstat", "exclusion");
					}
				}
				if (object.getDiagnosis3Id() != null){
					boolean diag3Exclude = memberDiagnosisExclusionService.isExcludedDiagnosis(object.getDiagnosis3Id().getDiagnosisId(), object.getMemberId().getMemberId());
					
					if (diag3Exclude){
						model.put("diag3exstat", "exclusion");
					}
				}
				model.put("myCase", object);
				
				if (object.getDiagnosis1Id() != null){
								
					int risk = 1;
					
					if (object.getDiagnosis2Id() != null){
						risk = 2;
						
						if (object.getDiagnosis3Id() != null){
							risk = 3;
						}
					}
					
					String[] eqParam = {"diagnosisId.diagnosisId","deletedStatus","treatmentRiskId.treatmentRiskId","providerTypeId.providerTypeId"};
					Object[] eqValue = {object.getDiagnosis1Id().getDiagnosisId(),Integer.valueOf(0),Integer.valueOf(risk),Integer.valueOf(1)};
					
					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = inaCBGService.search(null,null,eqParam,eqValue,0,1);
					
					if (inaCBGList != null && inaCBGList.size() > 0){
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList.iterator();
						
						if (iterator.hasNext()){
							ProviderTypeDiagnosisTreatment inaCBG = iterator.next();
							
							model.put("inaCbgRef", inaCBG);
						}
					}
				}
			}
		}
		return model;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		CaseForm myCaseForm = (CaseForm) command;

		System.out.println("ERROR : " + errors);

		errors.printStackTrace();

	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		CaseForm myCaseForm = (CaseForm) command;

		Case res = null;

		String redirectUrl = "case";
		RedirectView redirectView = new RedirectView(redirectUrl);
		Integer caseId = null;


		String alertMsg = "";

		try {
			// foreign affairs

			ActionUser actionUser = securityService.getActionUser(request);
			
			if (actionUser != null && actionUser.getUser().getUserType().intValue() == User.PROVIDER){
				CaseStatus caseStatus = new CaseStatus();
				caseStatus.setCaseStatusId(Integer.valueOf(Case.CASE_APPROVED));
				myCaseForm.getCase().setCaseStatusId(caseStatus);
				
				String[] required = {"Provider.ProviderCategoryId"};
				
				Provider provider = providerService.get(actionUser.getUser().getProviderId().getProviderId(),required);
				
				if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == Provider.DOKTER_PPK1){
					CaseCategory cc = new CaseCategory();
					cc.setCaseCategoryId(CaseCategory.GP_OUTPATIENT);
					myCaseForm.getCase().setCaseCategoryId(cc);
				}
				else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == Provider.DOKTER_SPECIALIST_PPK2){
					CaseCategory cc = new CaseCategory();
					cc.setCaseCategoryId(CaseCategory.SPECIALIST);
					myCaseForm.getCase().setCaseCategoryId(cc);
				}
				else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == Provider.RUMAH_SAKIT_PPK3){
					CaseCategory cc = new CaseCategory();
					cc.setCaseCategoryId(CaseCategory.INPATIENT);
					myCaseForm.getCase().setCaseCategoryId(cc);
				}
				
			}
			else {
				// Jika Refer Gimana?
			
				CaseStatus caseStatus = new CaseStatus();
				caseStatus.setCaseStatusId(Integer.valueOf(Case.CASE_OPEN));
				myCaseForm.getCase().setCaseStatusId(caseStatus);
			}

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");

			if (myCaseForm.getDiagnosis1() != null
					&& myCaseForm.getDiagnosis1().equalsIgnoreCase("")) {
				Diagnosis diagnosis1 = diagnosisService.get(Integer
						.valueOf(myCaseForm.getDiagnosis1()));
				if (diagnosis1 != null){
					myCaseForm.setDiagnosis1Id(diagnosis1.getDiagnosisId().toString());
				}
			}


			if (myCaseForm.isNewCase()) {

				String totalLOS = myCaseForm.getLongOfStay();
				
				if (totalLOS != null && !totalLOS.equalsIgnoreCase("")){
					int totalDay = Integer.valueOf(totalLOS);
					DateTime dt = new DateTime(myCaseForm.getCase().getCaseStartTime().getTime());
					dt.plusDays(totalDay);
					
					java.sql.Date endTime = new java.sql.Date(dt.getMillis());
					myCaseForm.getCase().setCaseEndTime(endTime);
				}
				if (navigation.equalsIgnoreCase("saveandapprove")) {

					ActionUser user = securityService.getActionUser(request);

					boolean isUserAuthorized = securityService
							.isUserAuthorized(user, "SAVEAPPROVECASE");

					if (!isUserAuthorized) {

						ModelAndView errorResult = new ModelAndView(
								new RedirectView("errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult
								.addObject("errorMessage",
										"You Are Not Authorized for SAVEAPPROVECASE access");
						return errorResult;

					}
					MemberProduct product = memberProductService.getMemberProductByDate(myCaseForm.getCase().getMemberId().getMemberId(),
							myCaseForm.getCase().getCaseCategoryId().getCaseCategoryId(), 
							myCaseForm.getCase().getCaseStartTime(), myCaseForm.getCase().getCaseStartTime());
					
					if (product != null){
						myCaseForm.getCase().setMemberProductId(product);
					}
					else {
						product = memberProductService.getMemberActiveProduct(myCaseForm.getCase().getMemberId().getMemberId(), 
								myCaseForm.getCase().getCaseCategoryId().getCaseCategoryId());
						
						if (product != null){
							myCaseForm.getCase().setMemberProductId(product);
						}
					}
					
					res = myCaseService.create(myCaseForm.getCase(), user);
				} else {
					ActionUser user = securityService.getActionUser(request);

					boolean isUserAuthorized = securityService
							.isUserAuthorized(user, "CREATECASE");

					if (!isUserAuthorized) {

						ModelAndView errorResult = new ModelAndView(
								new RedirectView("errorpage"));
						errorResult.addObject("errorType", "accessDenied");
						errorResult.addObject("errorMessage",
								"You Are Not Authorized for CREATECASE access");
						return errorResult;

					}
					MemberProduct product = memberProductService.getMemberProductByDate(myCaseForm.getCase().getMemberId().getMemberId(),
							myCaseForm.getCase().getCaseCategoryId().getCaseCategoryId(), 
							myCaseForm.getCase().getCaseStartTime(), myCaseForm.getCase().getCaseStartTime());
					
					if (product != null){
						myCaseForm.getCase().setMemberProductId(product);
					}
					else {
						product = memberProductService.getMemberActiveProduct(myCaseForm.getCase().getMemberId().getMemberId(), 
								myCaseForm.getCase().getCaseCategoryId().getCaseCategoryId());
						
						if (product != null){
							myCaseForm.getCase().setMemberProductId(product);
						}
					}
					res = myCaseService.create(myCaseForm.getCase(), user);
				}
				caseId = myCaseForm.getCase().getCaseId();

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.case", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.case",
							null, "fail", Locale.getDefault());
				}
			} else {
				/**
				 * 
				 * Update kemungkinan:
				 * 1. Update Data
				 * 2. Closing Case + Refer Case
				 */
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(user, "UPDATECASE");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATECASE access");
					return errorResult;

				}
				if (navigation.equalsIgnoreCase("closecase")){
					if (myCaseForm.getReferedPoliklinikId() != null && ( myCaseForm.getIsRefered().equals("1") 
							|| myCaseForm.getIsRefered().equalsIgnoreCase("2"))){
						
						myCaseForm.getCase().setReferalStatus(1);
						
						CaseStatus status = new CaseStatus();
						status.setCaseStatusId(CaseStatus.CASE_REFERED);
						myCaseForm.getCase().setCaseStatusId(status);
					}
					else {
						myCaseService.closeCase(myCaseForm.getCase(), user);
					}
				}
				else if (navigation.equalsIgnoreCase("refercase")){
					if (myCaseForm.getReferedPoliklinikId() != null ){						
						boolean refres = myCaseService.referCase(myCaseForm.getCase(), user);
						if (refres) {
							alertMsg = alertProperties.getMessage(
									"success.refer.case", null, "success", Locale
											.getDefault());
						} else {
							alertMsg = alertProperties.getMessage("fail.refer.case",
									null, "fail", Locale.getDefault());
						}
					}
					
				}
				else if (navigation.equalsIgnoreCase("approveexgratia")){
					byte[] document = null;
					String fileName = "";
					
					caseId = myCaseForm.getCase().getCaseId();
					if (myCaseForm.getMultipartFile() != null){
						document = myCaseForm.getMultipartFile().getBytes();
						fileName = System.currentTimeMillis()+"_"+ myCaseForm.getMultipartFile().getOriginalFilename();
					}
					
					boolean resApprove = myCaseService.approveExGratia(caseId, myCaseForm.getApprovalNote(), document, fileName, actionUser);
					
					if(resApprove){
						alertMsg = alertProperties.getMessage(
								"success.approve.case", null, "success", Locale
										.getDefault());
					}
					else {
						alertMsg = alertProperties.getMessage(
								"failed.approve.case", null, "success", Locale
										.getDefault());
					}
					
					
				}
				else {
					res = myCaseService.update(myCaseForm.getCase(), user);
					
					if (res != null) {
						alertMsg = alertProperties.getMessage(
								"success.update.case", null, "success", Locale
										.getDefault());
					} else {
						alertMsg = alertProperties.getMessage("fail.update.case",
								null, "fail", Locale.getDefault());
					}
				}
				caseId = myCaseForm.getCase().getCaseId();

				

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (myCaseForm.isNewCase()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.case", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.case", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(
				"case?navigation=detail&caseId=" + caseId + "&alert="
						+ alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	private void populateTimeForm(CaseForm form, Timestamp caseStart,
			Timestamp caseEnd) {

		if (form != null && caseStart != null && caseEnd != null) {
			// form.setDate()
			Date date = new Date(caseStart.getTime());
			form.setDate(date);

			Date dateEnd = new Date(caseEnd.getTime());
			form.setDateEnd(dateEnd);

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(caseStart.getTime());

			int hour = cal.get(Calendar.HOUR_OF_DAY);
			String tmp = "";
			if (hour < 10) {
				tmp = "0" + hour;
			} else {
				tmp = "" + hour;
			}
			form.setHour(tmp);

			int minute = cal.get(Calendar.MINUTE);
			if (minute < 10) {
				tmp = "0" + minute;
			} else {
				tmp = "" + minute;
			}
			form.setMinute(tmp);

			Calendar calEnd = Calendar.getInstance();
			calEnd.setTimeInMillis(caseEnd.getTime());

			int hourEnd = calEnd.get(Calendar.HOUR_OF_DAY);
			String tmpEnd = "";
			if (hourEnd < 10) {
				tmpEnd = "0" + hourEnd;
			} else {
				tmpEnd = "" + hourEnd;
			}
			form.setHourEnd(tmpEnd);

			int minuteEnd = calEnd.get(Calendar.MINUTE);
			if (minuteEnd < 10) {
				tmpEnd = "0" + minuteEnd;
			} else {
				tmpEnd = "" + minuteEnd;
			}
			form.setMinuteEnd(tmpEnd);

		}

	}

	private Timestamp getTimestamp(java.util.Date date, String hour,
			String minute) {
		Timestamp result = null;

		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
			cal.set(Calendar.MINUTE, Integer.valueOf(minute));

			result = new Timestamp(cal.getTimeInMillis());
		} catch (Exception e) {

		}

		return result;
	}

	public PriorityService getPriorityService() {
		return priorityService;
	}

	public void setPriorityService(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	// class+

	// class-

}
