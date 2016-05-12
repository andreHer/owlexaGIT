package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.MemberLimitLayer;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.MemberRKIForm;

// imports+ 

// imports- 

/**
 * MemberClausul is a mapping of member_clausul Table.
 */
public class MemberRKIFormController extends SimpleFormController {

	MemberClausulService memberClausulService;
	MemberBenefitService memberBenefitService;
	MemberProductService memberProductService;
	private MemberLimitLayerService memberLimitLayerService;
	
	private ProviderService providerService;
	private ItemService itemService;
	private MemberService memberService;
	private CaseService caseService;
	private CaseCategoryService caseCategoryService;
	private PolicyProviderService policyProviderService;
	private MemberProviderService memberProviderService;
	private ClientProviderService clientProviderService;
	private MemberGroupProviderService memberGroupProviderService;
	private PolicyCoverageFundService policyCoverageFundService;

	
	ResourceBundleMessageSource alertProperties;

	
	
	private SecurityService securityService;
	private ActivityLogService logService;

	
	
	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}

	public void setMemberLimitLayerService(
			MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}

	public PolicyProviderService getPolicyProviderService() {
		return policyProviderService;
	}

	public void setPolicyProviderService(PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
	}

	public MemberProviderService getMemberProviderService() {
		return memberProviderService;
	}

	public void setMemberProviderService(MemberProviderService memberProviderService) {
		this.memberProviderService = memberProviderService;
	}

	public ClientProviderService getClientProviderService() {
		return clientProviderService;
	}

	public void setClientProviderService(ClientProviderService clientProviderService) {
		this.clientProviderService = clientProviderService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	
	

	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
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

	public void setMemberClausulService(MemberClausulService object) {
		this.memberClausulService = object;
	}

	public MemberClausulService getMemberClausulService() {
		return this.memberClausulService;
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

	public MemberRKIFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		MemberRKIForm tmp = new MemberRKIForm();
		
		String breadcrumb = "<a href=\"member-rki-form\" class=\"linkbreadcrumb\">Register Manual</a>";
		request.setAttribute("breadcrumb", breadcrumb);
		
		User theUser = securityService.getCurrentUser(request);
		
		if (theUser != null){
			if (theUser.getUserType().intValue() == User.PROVIDER){
				tmp.setProviderId(theUser.getProviderId().getProviderId().toString());
				tmp.setProviderName(theUser.getProviderId().getProviderName());
			}
		}
		
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		MemberRKIForm memberRKIForm = (MemberRKIForm) command;

		
		// errors.setNestedPath("memberClausul");
		getValidator().validate(memberRKIForm, errors);
		// errors.setNestedPath("");
		
		errors.printStackTrace();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
		try {
			
			String q = WebUtil.getParameterString(request, "cardNumber", "");
			
			/*
			 * fungsi berikut bertujuan untuk menampilkan service yang di miliki oleh member yang active pada drop down
			 */
			
			System.out.println("q  :" + q );
			
			if(q != null && !q.equalsIgnoreCase("")){
				Member member = memberService.getMemberByCardNumber(q);
				
				Collection<CaseCategory> caseCategoryList = new ArrayList<CaseCategory>();
				Collection<MemberProduct> mpList = memberProductService.getMemberActiveProduct(member.getMemberId());
				for(MemberProduct mp : mpList){
					/* System.out.println("mp "+mp.getProductId().getProductCode()+" mpList"+mpList);
					 * System.out.println("mp CAT "+mp.getProductId().getCaseCategory().getCaseCategoryName());*/
					caseCategoryList.add(mp.getProductId().getCaseCategory());
				}
				
				model.put("caseCategoryList", caseCategoryList);
				model.put("cardNumber", q);
			}
			else{
				Collection<CaseCategory> caseCategoryList = caseCategoryService.getAll();
				model.put("caseCategoryList", caseCategoryList);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		MemberRKIForm memberRKIForm = (MemberRKIForm) command;

		MemberClausul res = null;
		String alertMsg = "";
		try {
			
			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CREATEMEMBERCLAUSUL");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult
						.addObject("errorMessage",
								"You Are Not Authorized for CREATEMEMBERCLAUSUL access");
				return errorResult;

			}

			Member member = memberService.getMemberByCardNumber(memberRKIForm.getCardNumber());
			MemberProduct activeProduct = null;
			
			Provider provider = null;
			if (memberRKIForm.getProviderId() != null){
				provider = providerService.get(Integer.valueOf(memberRKIForm.getProviderId()));
			}
			
			System.out.println("TEST MANUAL REG ID : "+memberRKIForm.getManualRegistration());
			ModelAndView result = new ModelAndView("detailRegisterRKI");

			String rejectReason = "";
			Integer registerStatus = 1;
			boolean isAso = false;
			double currentAsoFund = 0.0;
			double minimumAsoFund = 0.0;
			
			if (member != null){
				result.addObject("providerId", memberRKIForm.getProviderId());
				result.addObject("caseCategoryId", memberRKIForm.getCaseCategoryId());
				result.addObject("memberId", member.getMemberId());
				
				System.out.println("Case Category ID : " + memberRKIForm.getCaseCategoryId());
				
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(member.getMemberId(),Integer.valueOf(memberRKIForm.getCaseCategoryId()) );
				
				if(memberBenefitList != null){
					if (memberBenefitList.size() == 0){
						registerStatus = 0;
						rejectReason = "Member tidak mengambil benefit ini";
					}
				}
				else {
					registerStatus = 0;
					rejectReason = "Member tidak mengambil benefit ini";
				}
				java.sql.Date admission = java.sql.Date.valueOf(memberRKIForm.getAdmissionDate());
				
				if (member.getExpireDate().before(admission)){
					registerStatus = 0;
					rejectReason = "Membership sudah tidak berlaku (expired)";
				}
				else {
					if (member.getStatus().intValue() == SubscriptionStatus.TERMINATED){
						if(memberRKIForm.getAdmissionDate() != null && !memberRKIForm.equals("")){
							
							
							if (( member.getResignedDate() != null && 
				        			( member.getResignedDate().before(admission) ||  member.getResignedDate().equals(admission) )) 
				        			|| (member.getExpireDate().before(admission) || member.getExpireDate().equals(admission))){
				        		
								registerStatus = 0;
								rejectReason = "Membership sudah tidak berlaku (TERMINATED)";
				        	}
						}
						else {
							registerStatus = 0;
							rejectReason = "Membership sudah tidak berlaku (TERMINATED)";
						}
					}
					if (member.getStatus().intValue() == SubscriptionStatus.GRACE){
						
						if(memberRKIForm.getAdmissionDate() != null && !memberRKIForm.equals("")){
							
							
							DateTime admPlus1 = new DateTime(admission).plusDays(1);
							DateTime admMinus1 = new DateTime(admission).minusDays(1);
							
							if (member.getSuspendStartDate() != null && member.getSuspendEndDate() != null){
								if (member.getSuspendStartDate().before(new java.sql.Date(admPlus1.getMillis())) 
										&& member.getSuspendEndDate().after(new java.sql.Date(admMinus1.getMillis()))){
					        		
									registerStatus = 0;
									rejectReason = "Status Member GRACE Periode";
					        	}
							}
							else if (member.getSuspendStartDate() != null && member.getSuspendEndDate() == null){
								
							}
						}
						else {
							registerStatus = 0;
							rejectReason = "Status Member GRACE Periode";
						}
					}
					if (member.getStatus().intValue() == SubscriptionStatus.BLOCKED){
						if(memberRKIForm.getAdmissionDate() != null && !memberRKIForm.equals("")){

							DateTime admPlus1 = new DateTime(admission).plusDays(1);
							DateTime admMinus1 = new DateTime(admission).minusDays(1);
							
							if (member.getSuspendStartDate() != null && member.getSuspendEndDate() != null){
								if (member.getSuspendStartDate().before(new java.sql.Date(admPlus1.getMillis())) 
										&& member.getSuspendEndDate().after(new java.sql.Date(admMinus1.getMillis()))){
					        		
									registerStatus = 0;
									rejectReason = "Status Member SUSPEND";
					        	}
							}
							else {
								registerStatus = 0;
								rejectReason = "Status Member SUSPEND";
							}
						}
						else {
							registerStatus = 0;
							rejectReason = "Status Member SUSPEND";
						}
					}
				}
				
				if (member.getCurrentPolicyId() != null){
					//Add by aju on 20150626, get the policy_type to distinguish between INDEMNITY and MANAGECARE
					int policyType = member.getCurrentPolicyId().getPolicyType() == null ? 1 : member.getCurrentPolicyId().getPolicyType();
					
					int providerAllocation = member.getCurrentPolicyId().getProviderAllocationType() == null ? 0 : member.getCurrentPolicyId().getProviderAllocationType();
					
					System.out.println("Policy.PolicyType         : " + policyType);
					System.out.println("Policy.ProviderAllocation : " + providerAllocation);
					
					//Add by aju on 20150626, reject if policy is set to MANAGECARE but the PROVIDER ALLOCATION not PER LEVEL
					if(policyType==Policy.MANAGED_CARE_POLICY && providerAllocation==Policy.ALL_PROVIDER_USAGE){
						registerStatus = 0;
						rejectReason = "Polis MANAGE CARE ini masih terbuka untuk seluruh provider";
						System.out.println("MANAGE_CARE on ALL_PROVIDER not allowed!!!");
					}
					
					Policy currentPolicy = member.getCurrentPolicyId();
					
					if (currentPolicy.getIsUsingFloatingFund() != null && currentPolicy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY){
						isAso = true;
						
						currentAsoFund = member.getCurrentPolicyId().getCurrentPolicyFund();
						minimumAsoFund = member.getCurrentPolicyId() == null ? 0.0 : member.getCurrentPolicyId().getMinimumPolicyFund();
					}
					else if (currentPolicy.getIsUsingFloatingFund() != null && currentPolicy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE){
						
						/**
						 * ada 2 kondisi jika divert dan standard
						 */
						
						String[] eqParamAso = {"deletedStatus","policyId.policyId","caseCategoryId.caseCategoryId"};
						Object[] eqValueAso = {0,member.getCurrentPolicyId().getPolicyId(),Integer.valueOf(memberRKIForm.getCaseCategoryId())};
						int totalFund = policyCoverageFundService.getTotal(null,null,eqParamAso,eqValueAso);
						
						if (totalFund > 0){
							Collection<PolicyCoverageFund> fundList = policyCoverageFundService.search(null,null,eqParamAso,eqValueAso,0,totalFund);
							
							Iterator<PolicyCoverageFund> fundIterator = fundList.iterator();
							if (fundIterator.hasNext()){
								PolicyCoverageFund perCoverageFund = fundIterator.next();
								
								if (perCoverageFund != null){
									currentAsoFund = perCoverageFund.getCurrentFundValue();
									minimumAsoFund = perCoverageFund.getMinimumAsoValue();
								}
							}
						}
						else {
							
						}
						
					}
					
					CaseStatus rejectStatus = new CaseStatus();
					rejectStatus.setCaseStatusId(Case.CASE_REJECT);
					String serviceType = "";
					
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.INPATIENT)){
						serviceType = "inpatient";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.OUTPATIENT)){
						serviceType = "outpatient";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.DENTAL)){
						serviceType = "dental";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.MATERNITY)){
						serviceType = "maternity";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.OPTICAL)){
						serviceType = "optical";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.MEDICAL_CHECK_UP)){
						serviceType = "mcuLab";
					}
					
				if(currentPolicy.getPolicyType() == 2){
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.GP_OUTPATIENT)){
						serviceType = "ppk1";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.GP_DENTAL)){
						serviceType = "ppk1";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.SPECIALIST)){
						serviceType = "ppk2";
					}
					if (memberRKIForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.INPATIENT)){
						serviceType = "ppk3";
					}
				}
					
					
					System.out.println("SEARCHING FOR SERVICE TYPE = " + serviceType + " TO YES");
					
					if (providerAllocation == Policy.PROVIDER_CLIENT_USAGE_TYPE){
						System.out.println("CLIENT PROVIDER USAGE");
						
						String[] eqParam = {"clientId.clientId","providerId.providerId","deletedStatus",serviceType};
						Object[] eqValue = {member.getCurrentPolicyId().getClientId().getClientId(),provider.getProviderId(),Integer.valueOf(0),1};
						
						int total = clientProviderService.getTotal(null,null,eqParam,eqValue);
						
						if (total == 0){
							registerStatus = 0;
							rejectReason  = "Peserta Tidak Berhak Ke Provider Ini";
						}
					}
					else if (providerAllocation == Policy.PROVIDER_POLICY_USAGE_TYPE){
						
						System.out.println("POLICY PROVIDER USAGE");
						String[] eqParam = {"policyId.policyId","providerId.providerId","deletedStatus",serviceType};
						Object[] eqValue = {member.getCurrentPolicyId().getPolicyId(),provider.getProviderId(),Integer.valueOf(0),1};
						
						int total = policyProviderService.getTotal(null,null,eqParam,eqValue);
						
						if (total == 0){
							registerStatus = 0;
							rejectReason  = "Peserta Tidak Berhak Ke Provider Ini";
						}
					}
					else if (providerAllocation == Policy.PROVIDER_MEMBER_USAGE_TYPE){
						System.out.println("MEMBER PROVIDER USAGE");
						String[] eqParam = {"memberId.memberId","providerId.providerId","deletedStatus",serviceType};
						Object[] eqValue = {member.getMemberId(),provider.getProviderId(),Integer.valueOf(0),1};
						
						int total = memberProviderService.getTotal(null,null,eqParam,eqValue);
						
						if (total == 0){
							registerStatus = 0;
							rejectReason  = "Peserta Tidak Berhak Ke Provider Ini";
						}
					}
					else if (providerAllocation == Policy.PROVIDER_GROUP_USAGE_TYPE){
						System.out.println("GROUP PROVIDER USAGE");
						String[] eqParam = {"memberGroupId.memberGroupId","providerId.providerId","deletedStatus",serviceType};
						Object[] eqValue = {member.getMemberGroupId().getMemberGroupId(),provider.getProviderId(),Integer.valueOf(0),1};
						
						int total = memberGroupProviderService.getTotal(null,null,eqParam,eqValue);
						
						if (total == 0){
							registerStatus = 0;
							rejectReason  = "Peserta Tidak Berhak Ke Provider Ini";
						}
					}
				}
				
				
				
				activeProduct = memberProductService.getMemberProductByDate(member.getMemberId(), Integer.valueOf(memberRKIForm.getCaseCategoryId()), java.sql.Date.valueOf( memberRKIForm.getAdmissionDate()),
						java.sql.Date.valueOf( memberRKIForm.getAdmissionDate()));
				
				double nonDivertedBenefit = 0;
				double divertedBenefitLimit = 0;
				boolean hasDivertedBenefit = false;
				boolean hasRealBenefit = false;
				
				/*
				 * ada 2 case, case standard dan divert
				 */
				if (activeProduct != null){
					// a
					
					hasRealBenefit = true;
					nonDivertedBenefit = memberProductService.getRemainingBenefit(member.getMemberId(), Integer.valueOf(memberRKIForm.getCaseCategoryId()),
							java.sql.Date.valueOf( memberRKIForm.getAdmissionDate()));
					
					if (nonDivertedBenefit == 0 || nonDivertedBenefit < -1.0){
						
						MemberLimitLayer additionalLayer = memberLimitLayerService.getAvailableLayer(member.getMemberId(), Integer.valueOf(memberRKIForm.getCaseCategoryId()));
						
						if (additionalLayer == null){
							registerStatus = 0;
							rejectReason  = "Benefit Peserta Sudah Habis";
						}
						else  {
							if ( additionalLayer.getActualBenefitLimit() != null && additionalLayer.getActualBenefitLimit().doubleValue() == 0.0){
								registerStatus = 0;
								rejectReason  = "Benefit Peserta Sudah Habis";
								
							}
						}
						
					}
					
					if (activeProduct.isEDCEnabled() != null && !activeProduct.isEDCEnabled()){
						registerStatus = 0;
						rejectReason  = "Benefit Hanya Berlaku Reimbursement";
					}
				}
				else {
					Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitByDate(member.getMemberId(), Integer.valueOf(memberRKIForm.getCaseCategoryId()),
							java.sql.Date.valueOf( memberRKIForm.getAdmissionDate()),java.sql.Date.valueOf( memberRKIForm.getAdmissionDate()));
					
					if (benefitList != null && benefitList.size() > 0){
						hasDivertedBenefit = true;
						
						Iterator<MemberBenefit> benefitIterator = benefitList.iterator();
						
						if (benefitIterator.hasNext()){
							MemberBenefit benefit = benefitIterator.next();
							
							if (benefit != null){
								MemberProduct mp = benefit.getMemberProductId();
								
								
								if (mp != null){
									mp = memberProductService.get(mp.getMemberProductId());
									
									divertedBenefitLimit = mp.getActualBenefitLimit() == null ? 0 : mp.getActualBenefitLimit();
								}
							}
						}
					}
				}
				
				if (hasDivertedBenefit && hasRealBenefit){
					
				}
				else if (hasDivertedBenefit && !hasRealBenefit){
					
				}
				else if (!hasDivertedBenefit && hasRealBenefit){
					
				}
				
				
				result.addObject("benefitList", memberBenefitList);
			
				result.addObject("member",member);
				result.addObject("provider", provider);
				result.addObject("registerDate", memberRKIForm.getAdmissionDate());
				
			}
			else {
				registerStatus = 0;
				rejectReason = "Data Member Tidak Ditemukan";
				
			}
			
			System.out.println("PRE ADMISSION CHECKED : " + memberRKIForm.getPreAdmission());
			
			if (registerStatus == 1){
				try {
					Case theCase = new Case();
					
					CaseCategory caseCategory = caseCategoryService.get(Integer.valueOf(memberRKIForm.getCaseCategoryId()));
					
					
					theCase.setCaseCategoryId(caseCategory);
					theCase.setMemberId(member);
					theCase.setMemberName(member.getFirstName());
					theCase.setMemberNumber(member.getCustomerPolicyNumber());
					
					theCase.setCaseStartTime( java.sql.Date.valueOf( memberRKIForm.getAdmissionDate()));
					theCase.setMemberProductId(activeProduct);
					
					//Add by aju on 20150805, for COB :D
					if(member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().trim().equalsIgnoreCase("")){
						theCase.setIsLinkedMember(1);
					}
					else{
						theCase.setIsLinkedMember(0);
					}
					
					
					if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
						theCase.setCaseEndTime(java.sql.Date.valueOf( memberRKIForm.getAdmissionDate()));
					}
					else {
						DateTime dt = new DateTime();
						DateTime endTime = dt.plusDays(4);
						
						theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
					}
					
					if (provider != null){
						theCase.setProviderId(provider);
						theCase.setProviderName(provider.getProviderName());
					}
					

					//if(memberRKIForm.getManualRegistration()!=null){
					//	theCase.setManualRegistration(Integer.valueOf(memberRKIForm.getManualRegistration()));
					//}
					
					theCase.setManualRegistration(Integer.valueOf(1));
					
					CaseStatus status = new CaseStatus();
					//modified by aju on 20150824, for preAdmission :D
					//status.setCaseStatusId(CaseStatus.CASE_OPEN);
					if(caseCategory.getParentCategoryId().getCaseCategoryId() != CaseCategory.INPATIENT){
						status.setCaseStatusId(CaseStatus.CASE_OPEN);
					}
					else{
						if(memberRKIForm.getPreAdmission()){
							status.setCaseStatusId(CaseStatus.CASE_PRE_AUTH);
						}
						else{
							status.setCaseStatusId(CaseStatus.CASE_OPEN);
						}
					}
					
					if(provider.getStatusId().getStatusId() == 2){
						status.setCaseStatusId(CaseStatus.CASE_REJECT);
						registerStatus = 0;
						rejectReason = "Provider sudah tidak berlaku (TERMINATED)";
					}
					
					theCase.setCaseType(Case.CASE_EDC_MANUAL);
					theCase.setCaseStatusId(status);
					
					System.out.println("BEFORE CREATE CASE 1 ");
					Case newCase = caseService.create(theCase, user);
					result.addObject("case", newCase);
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			//Add by aju on 20150824, for preAdmission :D
			result.addObject("preAdmission", memberRKIForm.getPreAdmission());
			
			result.addObject("registerStatus", registerStatus);
			result.addObject("rejectReason", rejectReason);
			result.addObject("cardNumber", memberRKIForm.getCardNumber());
			
			if (user != null){
				if (user.getUser().getUserType().intValue() == User.PROVIDER){
					String breadcrumb = "<a href=\"member-rki-form\" class=\"linkbreadcrumb\">Detail Registrasi Case </a>";
					request.setAttribute("breadcrumb", breadcrumb);
				}
			}

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		

	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
	}

	// class+

	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}

	public void setMemberBenefitService(
			MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(
			MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberGroupProviderService getMemberGroupProviderService() {
		return memberGroupProviderService;
	}

	public void setMemberGroupProviderService(
			MemberGroupProviderService memberGroupProviderService) {
		this.memberGroupProviderService = memberGroupProviderService;
	}

	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}

	public void setPolicyCoverageFundService(
			PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}

	// class-

}
