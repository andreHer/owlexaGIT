package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

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

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.RejectCategory;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.PolicyDto;
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
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;
import com.ametis.cms.web.form.MemberEDCRegisterForm;

// imports+ 

// imports- 

/**
 * MemberClausul is a mapping of member_clausul Table.
 */
public class MemberEDCRegisterFormController extends SimpleFormController {

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
	
	
	private PoliklinikService poliklinikService;
	private PolicyService policyService;
	private PolicyClausulService policyClausulService;
	private MemberElectronicCardService memberCardService;

	
	
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

	public MemberEDCRegisterFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		MemberEDCRegisterForm tmp = new MemberEDCRegisterForm();
		
		String breadcrumb = "<a href=\"member-edc-emulator\" class=\"linkbreadcrumb\">EDC Register Emulator</a>";
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

		MemberEDCRegisterForm memberEDCRegisterForm = (MemberEDCRegisterForm) command;

		
		// errors.setNestedPath("memberClausul");
		getValidator().validate(memberEDCRegisterForm, errors);
		// errors.setNestedPath("");
		
		errors.printStackTrace();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
		try {
			Collection<CaseCategory> caseCategoryList = caseCategoryService.getAll();
			
			model.put("caseCategoryList", caseCategoryList);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		System.out.println(".:: MemberEDCRegisterFormController ::.");
		
		MemberEDCRegisterForm memberEDCRegisterForm = (MemberEDCRegisterForm) command;
		Integer serviceCaseCategoryId;

		MemberClausul res = null;
		String alertMsg = "";
		try {
			ActionResult authRes = new ActionResult();
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
			
			System.out.println(".:: MemberEDCRegisterFormController ::.");
			System.out.println("Provider ID   : " + memberEDCRegisterForm.getProviderId() + "\nProvider Name : " + memberEDCRegisterForm.getProviderName());
			System.out.println("Card Number   : " + memberEDCRegisterForm.getCardNumber());
			System.out.println("Claim Service (based on EDC Code) : " + memberEDCRegisterForm.getCaseCategoryId());
			System.out.println("Refer Number  : " + memberEDCRegisterForm.getReferenceNumber());
			
			

			Member member = memberService.getMemberByCardNumber(memberEDCRegisterForm.getCardNumber());
			Provider provider = null;
			if (memberEDCRegisterForm.getProviderId() != null){
				provider = providerService.get(Integer.valueOf(memberEDCRegisterForm.getProviderId()));
			}
			
			CaseCategory tmpCaseCategory = caseCategoryService.getCaseCategoryEDC(memberEDCRegisterForm.getCaseCategoryId());
			if(tmpCaseCategory == null){
				//if no data found on CaseCategory by EDC Code, check from Poliklinik
				Poliklinik poliklinik = poliklinikService.getPoliByEdcCode(memberEDCRegisterForm.getCaseCategoryId());
				if(poliklinik != null){
					tmpCaseCategory = poliklinik.getCaseCategoryId();
				}
			}
			
			System.out.println("Claim Service (based on CaseCategory) : " + tmpCaseCategory.getCaseCategoryId() + " (" + tmpCaseCategory.getCaseCategoryName() + ") ");
			
			ModelAndView result = new ModelAndView("detailRegisterEDCEmulator");

			String rejectReason = "";
			Integer registerStatus = 1;
			boolean isAso = false;
			double currentAsoFund = 0.0;
			double minimumAsoFund = 0.0;
			
			//[START] Try to mimic EDC :D
			
			MemberDto memberDto = getMemberByCardNumber(memberEDCRegisterForm.getCardNumber());
			
			if(member.getCurrentPolicyId().getPolicyType() == Policy.INDEMNITY_POLICY){
				System.out.println(".:: BEFORE AUTHORIZE EDC INDEMNITY ::.");
				authRes = authorizeMemberEDC(memberEDCRegisterForm.getCardNumber(), memberEDCRegisterForm.getCaseCategoryId(), "", memberEDCRegisterForm.getProviderId(), "", memberEDCRegisterForm.getAdmissionDate(),user);
								
				System.out.println(".:: AFTER AUTHORIZE EDC INDEMNITY ::.");
				System.out.println("Action Code        : " + authRes.getActionCode());
				System.out.println("Reason             : " + authRes.getReason());
				System.out.println("Reference Number   : " + authRes.getReferenceNumber());
				System.out.println("isResult           : " + authRes.isResult());
				System.out.println("Additional Message : " + authRes.getAdditionalMessage());
				
			}else if(member.getCurrentPolicyId().getPolicyType() == Policy.MANAGED_CARE_POLICY){
				System.out.println(".:: BEFORE AUTHORIZE EDC MANAGEDCARE ::.");
				authRes = authorizeMemberEDCManagedCare(memberEDCRegisterForm.getCardNumber(), memberEDCRegisterForm.getCaseCategoryId(), memberEDCRegisterForm.getReferenceNumber(), memberEDCRegisterForm.getProviderId(), "",memberEDCRegisterForm.getAdmissionDate(),user);
								
				System.out.println(".:: AFTER AUTHORIZE EDC MANAGEDCARE ::.");
				System.out.println("Action Code        : " + authRes.getActionCode());
				System.out.println("Reason             : " + authRes.getReason());
				System.out.println("Reference Number   : " + authRes.getReferenceNumber());
				System.out.println("isResult           : " + authRes.isResult());
				System.out.println("Additional Message : " + authRes.getAdditionalMessage());
			}
			
			ActionResult resText = getFreeTextEDC(memberDto, memberEDCRegisterForm.getCaseCategoryId());
			System.out.println(".:: EDC FREE TEXT ::.");
			System.out.println("Free Text : " + resText.getAdditionalMessage().toUpperCase());
			
			if(authRes.isResult()){
				System.out.println("REGISTER SUCCESS");
			}
			else{
				String responseCode="";
				registerStatus = 0;
				
				if (authRes.getAdditionalMessage().equals("TERMINATED")){                        		
            		responseCode = "08";//masa pertanggungan sudah berakhir
            		rejectReason = "Peserta Tidak Aktif;";
            	}
            	else if (authRes.getAdditionalMessage().equals("RESIGNED")){                        		
            		responseCode = "08"; // masa pertanggungan sudah berakhir
            		rejectReason = "Peserta Tidak Aktif;";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("ALREADYREGISTERED")){
            		responseCode = "27"; // masa pertanggungan sudah berakhir
            		rejectReason = "Peserta Sudah; Registrasi;";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("BLOCKED")){                        		
            		responseCode = "14";// Invalid/expired ard
            		rejectReason = "Kartu Tidak Aktif;";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("NOTPROVIDER")){                        		
            		responseCode = "29";// BQ (Merchant is not supported) or 31 (Issuer is not supported)
            		rejectReason = "Peserta Tidak Berhak; di Provider Ini;";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("NOTCOVERED")){                        		
            		responseCode = "09";
            		rejectReason = "Tidak Mengambil; Benefit Ini;";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("INVALIDCARD")){                        		
            		responseCode = "14";
            		rejectReason = "Kartu Tidak Aktif;";
            	}                        	
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("EMPTYBENEFIT")){                        		
            		//responseCode = "18";
            		//isomsg.set(48,"Benefit Sudah Habis;");
            		responseCode = "00";
            		rejectReason = "LIMIT BENEFIT SUDAH HABIS";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("NOFUND")){
            		responseCode ="00";
            		rejectReason = "DANA PELAYANAN SUDAH HABIS";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("MAXCHILDREN")){
            		responseCode ="45";
            		rejectReason = "Jumlah Anak; Melebihi Kuota";
            	}
            	else if (authRes.getAdditionalMessage().equalsIgnoreCase("NOTREFERED")){                        		
            		responseCode = "30";
            		rejectReason = "Tidak Ada Rujukan;";
            	}
				
				System.out.println("REGISTER FAILED -> " + rejectReason + " (" + responseCode + ")");
				
			}
			
			//[END] Try to mimic the EDC
			
			
			if (member != null){
				result.addObject("providerId", memberEDCRegisterForm.getProviderId());
				//result.addObject("caseCategoryId", memberEDCRegisterForm.getCaseCategoryId());
				result.addObject("caseCategoryId", tmpCaseCategory.getCaseCategoryId());
				result.addObject("memberId", member.getMemberId());
				
				System.out.println("Case Category ID : " + tmpCaseCategory.getCaseCategoryId());
				
				
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(member.getMemberId(),tmpCaseCategory.getCaseCategoryId() );
				/*
				
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
				*/
				
				/*
				if (member.getExpireDate().before(new java.sql.Date(System.currentTimeMillis()))){
					registerStatus = 0;
					rejectReason = "Membership sudah tidak berlaku (expired)";
				}
				else {
					if (member.getStatus().intValue() == SubscriptionStatus.TERMINATED){
						if(memberEDCRegisterForm.getAdmissionDate() != null && !memberEDCRegisterForm.equals("")){
							java.sql.Date admission = java.sql.Date.valueOf(memberEDCRegisterForm.getAdmissionDate());
							
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
						
						if(memberEDCRegisterForm.getAdmissionDate() != null && !memberEDCRegisterForm.equals("")){
							java.sql.Date admission = java.sql.Date.valueOf(memberEDCRegisterForm.getAdmissionDate());
							
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
						if(memberEDCRegisterForm.getAdmissionDate() != null && !memberEDCRegisterForm.equals("")){
							java.sql.Date admission = java.sql.Date.valueOf(memberEDCRegisterForm.getAdmissionDate());
							

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
				*/
				
				/*
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
						
						
						
						String[] eqParamAso = {"deletedStatus","policyId.policyId","caseCategoryId.caseCategoryId"};
						Object[] eqValueAso = {0,member.getCurrentPolicyId().getPolicyId(),Integer.valueOf(memberEDCRegisterForm.getCaseCategoryId())};
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
					
					if (memberEDCRegisterForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.INPATIENT)){
						serviceType = "inpatient";
					}
					if (memberEDCRegisterForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.OUTPATIENT)){
						serviceType = "outpatient";
					}
					if (memberEDCRegisterForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.DENTAL)){
						serviceType = "dental";
					}
					if (memberEDCRegisterForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.MATERNITY)){
						serviceType = "maternity";
					}
					if (memberEDCRegisterForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.OPTICAL)){
						serviceType = "optical";
					}
					if (memberEDCRegisterForm.getCaseCategoryId().equalsIgnoreCase(""+CaseCategory.MEDICAL_CHECK_UP)){
						serviceType = "mcuLab";
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
				*/
				
				/*
				MemberProduct activeProduct = memberProductService.getMemberProductByDate(member.getMemberId(), Integer.valueOf(memberEDCRegisterForm.getCaseCategoryId()), java.sql.Date.valueOf( memberEDCRegisterForm.getAdmissionDate()),
						java.sql.Date.valueOf( memberEDCRegisterForm.getAdmissionDate()));
				
				double nonDivertedBenefit = 0;
				double divertedBenefitLimit = 0;
				boolean hasDivertedBenefit = false;
				boolean hasRealBenefit = false;
				
				
				if (activeProduct != null){
					// a
					
					hasRealBenefit = true;
					nonDivertedBenefit = memberProductService.getRemainingBenefit(member.getMemberId(), Integer.valueOf(memberEDCRegisterForm.getCaseCategoryId()),
							java.sql.Date.valueOf( memberEDCRegisterForm.getAdmissionDate()));
					
					if (nonDivertedBenefit == 0 || nonDivertedBenefit < -1.0){
						registerStatus = 0;
						rejectReason  = "Benefit Peserta Sudah Habis";
					}
					
					if (activeProduct.isEDCEnabled() != null && !activeProduct.isEDCEnabled()){
						registerStatus = 0;
						rejectReason  = "Benefit Hanya Berlaku Reimbursement";
					}
				}
				else {
					Collection<MemberBenefit> benefitList = memberBenefitService.getMemberBenefitByDate(member.getMemberId(), Integer.valueOf(memberEDCRegisterForm.getCaseCategoryId()),
							java.sql.Date.valueOf( memberEDCRegisterForm.getAdmissionDate()),java.sql.Date.valueOf( memberEDCRegisterForm.getAdmissionDate()));
					
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
				*/
				
				result.addObject("benefitList", memberBenefitList);
			
				result.addObject("member",member);
				result.addObject("provider", provider);
				result.addObject("registerDate", memberEDCRegisterForm.getAdmissionDate());
				
			}
			else {
				registerStatus = 0;
				rejectReason = "Data Member Tidak Ditemukan";
				
			}
			
			if (registerStatus == 1){
				try {
					/* modified for  edc mimic
					Case theCase = new Case();
					CaseCategory caseCategory = new CaseCategory();
					caseCategory.setCaseCategoryId(Integer.valueOf(memberEDCRegisterForm.getCaseCategoryId()));
					
					theCase.setCaseCategoryId(caseCategory);
					theCase.setMemberId(member);
					theCase.setCaseStartTime( java.sql.Date.valueOf( memberEDCRegisterForm.getAdmissionDate()));
					
					
					if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
						theCase.setCaseEndTime(java.sql.Date.valueOf( memberEDCRegisterForm.getAdmissionDate()));
					}
					else {
						DateTime dt = new DateTime();
						DateTime endTime = dt.plusDays(4);
						
						theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
					}
					
					if (provider != null){
						theCase.setProviderId(provider);
					}
					

					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(CaseStatus.CASE_OPEN);
					theCase.setCaseType(Case.CASE_EDC_MANUAL);
					theCase.setCaseStatusId(status);
					
					System.out.println("BEFORE CREATE CASE 1 ");
					Case newCase = caseService.create(theCase, user);
					*/
					
					Case newCase = caseService.get( Integer.parseInt(authRes.getActionCode()));
					
					result.addObject("case", newCase);
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
			result.addObject("registerStatus", registerStatus);
			result.addObject("rejectReason", rejectReason);
			result.addObject("cardNumber", memberEDCRegisterForm.getCardNumber());

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		

	}
	
	//Add by aju on 20150707, mimic the EDC Flow for registration
	public String isValidProvider (String merchantId, String cardNumber,String caseCategoryCode){
		String result = "FALSE";
		try {
			if (merchantId != null && cardNumber != null){
				Member member = memberService.getMemberByCardNumber(cardNumber);
				
				//modified EDC Mimic for manual registration
				//Provider provider = providerService.getByProviderCode(merchantId);
				Provider provider = providerService.get(Integer.parseInt(merchantId));
				
				Policy policy = null;
				System.out.println("CHECKING MERCHANT ID : " + merchantId + " CARD NUMBER : " + cardNumber);
				
				if (provider.getStatusId().getStatusId().intValue() == SubscriptionStatus.ACTIVE 
						&& provider.getContractEndDate().after(new java.sql.Date(System.currentTimeMillis()))){
					
					if (member != null){
						Client client = member.getClientId();
						MemberGroup group = member.getMemberGroupId();
						
						policy = policyService.getGroupActivePolicy(group.getMemberGroupId());
						
						System.out.println ("CLIENT ID : "+ client.getClientId() + " MEMBER GROUP  : " + group.getMemberGroupId() + " POLICY ID :  " + policy.getPolicyId() + " POLICY ALLOCATION : " + policy.getProviderAllocationType().intValue());
						
						if (policy != null){
							String serviceType = "";
							
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.INPATIENT_STR)){
								serviceType = "inpatient";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.OUTPATIENT_STR)){
								serviceType = "outpatient";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.DENTAL_STR)){
								serviceType = "dental";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.MATERNITY_STR)){
								serviceType = "maternity";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.OPTICAL_STR)){
								serviceType = "optical";
							}
							if (caseCategoryCode.equalsIgnoreCase(CaseCategory.MEDICAL_CHECK_UP_STR)){
								serviceType = "mcuLab";
							}
							
							if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_CLIENT_USAGE_TYPE){
								String[] eqParam = {"clientId.clientId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {client.getClientId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = clientProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_POLICY_USAGE_TYPE){
								String[] eqParam = {"policyId.policyId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {policy.getPolicyId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = policyProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_GROUP_USAGE_TYPE){
								String[] eqParam = {"memberGroupId.memberGroupId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {member.getMemberGroupId().getMemberGroupId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = memberGroupProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.PROVIDER_MEMBER_USAGE_TYPE){
								String[] eqParam = {"memberId.memberId","providerId.providerId","deletedStatus",serviceType};
								Object[] eqValue = {member.getMemberId(),provider.getProviderId(),Integer.valueOf(0),1};
								
								int total = memberProviderService.getTotal(null,null,eqParam,eqValue);
								
								if (total > 0){
									result = "TRUE";
								}
							}
							else if (policy.getProviderAllocationType() != null && policy.getProviderAllocationType().intValue() == Policy.ALL_PROVIDER_USAGE){
								result = "TRUE";
							}
						}						
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//add by aju on 20150707, mimic the EDC flow for registration
	public PolicyDto getActivePolicy(String memberGroupId, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		PolicyDto result = null;
		
		try {
			Policy policy = policyService.getGroupActivePolicy(Integer.valueOf(memberGroupId));
			
			if (policy != null){
				result = policy.exportDto();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//add by aju on 20150707, mimic the EDC flow for registration
	public MemberDto getMemberByCardNumber(String cardNumber) {
		// TODO Auto-generated method stub
		MemberDto result = null;
		try {
			
			Member member = memberService.getMemberByCardNumber( cardNumber);
			
			if (member != null){
				
				result = new MemberDto();
				result.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
				result.setCustomerNumber(member.getCustomerNumber());
				result.setMemberId(member.getMemberId());
				if (member.getParentMember() != null){
					result.setParentId(member.getParentMember().getMemberId().toString());
					result.setParentName(member.getParentMember().getFirstName());
				}
				result.setCardNumber(member.getCurrentCardNumber() == null ? "" : member.getCurrentCardNumber());				
				
				if (member.getCurrentPolicyId() != null){
					result.setPolicyNumber(member.getCurrentPolicyId().getPolicyNumber() == null ? "" : member.getCurrentPolicyId().getPolicyNumber());
				}

				
				result.setFirstName(member.getFirstName());
				result.setBirthday(member.getBirthday());
				result.setExpireDate(member.getExpireDate());
				result.setEffectiveDate(member.getEffectiveDate());
				result.setResignedDate(member.getResignedDate());
				
				result.setClientName(member.getClientId().getClientName());
				result.setClientCode(member.getClientId().getClientCode());
				
				if (member.getMemberGroupId() != null){
					result.setMemberGroupCode(member.getMemberGroupId().getMemberGroupCode() == null ? "" : member.getMemberGroupId().getMemberGroupCode());
					result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
					
					
					result.setMemberGroupName(member.getMemberGroupId().getGroupName());
					
					PolicyDto activePolicy = getActivePolicy(member.getMemberGroupId().getMemberGroupId().toString(), null);
					
					if (activePolicy != null){
						result.setPolicyType(activePolicy.getPolicyType());
					}
					
				}
				
				
				if (member.getMemberGroupId() != null){
					if (member.getMemberGroupId().getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
						result.setStatus(member.getStatus().toString());
					}
					else {
						result.setStatus(member.getMemberGroupId().getStatus().getStatusId().toString());
					}
				}
				
				String[] eqCardParam = {"memberId.memberId","cardNumber"};
				Object[] eqCardValue = {member.getMemberId(),cardNumber};
				
				Collection<MemberElectronicCard> cardList = memberCardService.search(null,null,eqCardParam,eqCardValue,0,1);
				
				if (cardList != null){
					Iterator<MemberElectronicCard> iterator = cardList.iterator();
					
					if (iterator.hasNext()){
						MemberElectronicCard card = iterator.next();
						
						if (card != null ){
							result.setCardStatus(card.getCardStatus().toString());
						}
						else {
							result.setCardStatus("0");
						}
					}
				}
				else {
					result.setCardStatus("UNKNOWN");
				}
				
			}
			else {
				String[] eqCardParam = {"deletedStatus","cardNumber"};
				Object[] eqCardValue = {Integer.valueOf(0),cardNumber};
				
				Collection<MemberElectronicCard> cardList = memberCardService.search(null,null,eqCardParam,eqCardValue,0,1);
				if (cardList != null){
					Iterator<MemberElectronicCard> iterator = cardList.iterator();
					if (iterator.hasNext()){
				
						MemberElectronicCard memberCard = iterator.next();
						
						String[] reqMember = {"Member.MemberGroupId","Member.MemberGroupId.ClientId","Member.ClientId"};
						member = memberCard.getMemberId();
						
						result = new MemberDto();
						result.setCustomerPolicyNumber(member.getCustomerPolicyNumber());
						result.setCustomerNumber(member.getCustomerNumber());
						result.setMemberId(member.getMemberId());
						
						
						result.setFirstName(member.getFirstName());
						result.setBirthday(member.getBirthday());
						result.setExpireDate(member.getExpireDate());
						result.setEffectiveDate(member.getEffectiveDate());
						result.setResignedDate(member.getResignedDate());
						result.setClientName(member.getClientId().getClientName());
						result.setClientCode(member.getClientId().getClientCode());
						
						
						if (member.getMemberGroupId() != null){
							result.setMemberGroupId(member.getMemberGroupId().getMemberGroupId().toString());
							result.setPolicyNumber(member.getCurrentPolicyId().getPolicyNumber());
							result.setMemberGroupName(member.getMemberGroupId().getGroupName());
							
							if (member.getMemberGroupId().getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
								result.setStatus(member.getStatus().toString());
							}
							else {
								result.setStatus(member.getMemberGroupId().getStatus().getStatusId().toString());
							}
						}
						
						result.setCardStatus(memberCard.getCardStatus().toString());
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//Add by aju on 20150707, mimic the EDC flow for registration
	public ActionResult getFreeTextEDC(MemberDto memberDto, String serviceCategory)
			throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();

		String edcText = "";
		CaseCategory cc = caseCategoryService.getCaseCategoryEDC(serviceCategory);
		
		if (cc == null){
			
			Poliklinik poliklinik = poliklinikService.getPoliByEdcCode(serviceCategory);
			
			if (poliklinik != null){
				cc = poliklinik.getCaseCategoryId();
			}
		}
		
		String[] required = {"Member.MemberGroupId","Member.MemberGroupId","Member.MemberGroupId.ClientId"};
		
		Member member = memberService.get(memberDto.getMemberId(),required);
		
		Policy activePolicy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), 
				member.getMemberGroupId().getClientId().getClientId());
		
		if (activePolicy != null){
			String[] eqParam = {"policyId.policyId","isEdc","deletedStatus","caseCategoryId.caseCategoryId"};
			Object[] eqValue = {activePolicy.getPolicyId(),Integer.valueOf(1),Integer.valueOf(0),cc.getCaseCategoryId()};
			
			Collection<PolicyClausul> clausulList = policyClausulService.search(null,null,eqParam,eqValue,0,10);
		
			for (Iterator iterator = clausulList.iterator(); iterator.hasNext();) {
				
				PolicyClausul policyClausul = (PolicyClausul) iterator.next();
				
				if (policyClausul != null){
					String line = policyClausul.getDescription();
					if (line != null){
						int len = line.length();
						if (len < 40){
							line = StringUtil.padding(line, 40);
							System.out.println("EDC TEXT = |" + line + "|");
							
						}
						else {
							line = StringUtil.padding(line, 40*(len/40+1));	
							System.out.println("EDC TEXT LONG 40 = |" + line + "|");
						}
						edcText += line;
					}
				}
				
			}
			result.setAdditionalMessage(edcText);
			result.setResult(true);
		}
		return result;
	}
	
	//add by aju on 20150707, mimic the EDC  flow for registration
	public ActionResult authorizeMemberEDC(String cardNumber, String serviceType,
			String referenceNumber, String merchantId, String terminalId, String admissionDate, ActionUser theUser) {
		// TODO Auto-generated method stub

		ActionResult result = new ActionResult();
		result.setResult(false);

		try {
			System.out.println(".:: EDC Authorize Mimic ::.");
			System.out.println("CARD NUMBER    : " + cardNumber);
			System.out.println("SERVICE TYPE   : " + serviceType );
			System.out.println("REFER NUMBER   : " +  referenceNumber);
			System.out.println("MERCHANT ID    : " + merchantId);
			System.out.println("TERMINAL ID    : " + terminalId);
			System.out.println("ADMISSION DATE : " + admissionDate);
			
			java.sql.Date admissionDt = java.sql.Date.valueOf(admissionDate);
			
			// check clausul dulu.. jika tidak ada maka default true;

			Member member = memberService.getMemberByCardNumber(cardNumber);

			//modified EDC Mimic for manual registration
			//Provider provider = providerService.getByProviderCode(merchantId);
			Provider provider = providerService.get(Integer.parseInt(merchantId));
			
			CaseCategory caseCategory = caseCategoryService.getCaseCategoryEDC(serviceType);
			
			/*
			System.out.println("EDC Terminal Service : " + edcTerminalService);
			
			EdcTerminal edcTerminal = null;
			
			if (edcTerminalService != null){
				edcTerminal = edcTerminalService.getEdcTerminal(merchantId, terminalId);
			}
			*/
			
			if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {

				int totalCase = 0;
				
				String[] eqExistingParam = {"providerId.providerId","deletedStatus","caseStartTime","memberId.memberId"
						,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
				
				/* use admission date set by user for manual
				Object[] eqExistingValue = {provider.getProviderId(),Integer.valueOf(0),new java.sql.Date(System.currentTimeMillis()),
						member.getMemberId(),CaseStatus.CASE_OPEN,caseCategory.getCaseCategoryId()};
				*/
				Object[] eqExistingValue = {provider.getProviderId(),Integer.valueOf(0),admissionDt,
						member.getMemberId(),CaseStatus.CASE_OPEN,caseCategory.getCaseCategoryId()};
				
				totalCase = caseService.getTotal(null,null,eqExistingParam,eqExistingValue);
				
				if (totalCase > 0){
					result.setAdditionalMessage("ALREADYREGISTERED");
					result.setResult(false);
					return result;
				}
				
				String isValidProvider = isValidProvider(merchantId, cardNumber, caseCategory.getCaseCategoryCode());
				if (isValidProvider != null && !isValidProvider.equalsIgnoreCase("TRUE")){
					result.setAdditionalMessage("NOTPROVIDER");
					result.setResult(false);
					return result;
				}
					
				System.out.println("Membership status => ACTIVE");
				
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(member.getMemberId(),
						caseCategory.getCaseCategoryId());
				
				boolean isManagedCare = false;
				boolean isIndemnity = false;
				boolean isRefered = false;
				
				boolean isUsingPolicyAsoFund = false;
				boolean isUsingPolicyCoverageFund = false;
				
				Policy policy = policyService.get(member.getCurrentPolicyId().getPolicyId());
				
				System.out.println("POLICY ==> " + policy);
				
				if (policy != null){
					if (policy.getPolicyType().intValue() == 1){
						isIndemnity = true;
					}
					else if (policy.getPolicyType().intValue() == 2){
						isManagedCare = true;
					}
					
					if (policy.getIsUsingFloatingFund() != null){
						if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_COVERAGE){
							isUsingPolicyCoverageFund = true;
						}
						if (policy.getIsUsingFloatingFund().intValue() == Policy.FUND_LEVEL_POLICY){
							isUsingPolicyAsoFund = true;
						}
					}
				}	
				
				if (caseCategory != null && caseCategory.getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
					System.out.println("DIDALAM MATERNITY CHECK");
					String[] eqMaxChildParam = {"deletedStatus","iterationType","caseCategoryId.caseCategoryId"};
					Object[] eqMaxChildValue = {0,PolicyClausul.MA_MAX_CHILDREN,caseCategory.getCaseCategoryId()};
					
					Collection<PolicyClausul> maxChildCollection = policyClausulService.search(null,null,eqMaxChildParam,eqMaxChildValue,0,1);
					
					if (maxChildCollection != null && maxChildCollection.size() > 0){
						System.out.println("ADA CLAUSUL CHECK MATERNITY CHECK");
						Iterator<PolicyClausul> iterator= maxChildCollection.iterator();
						
						if (iterator.hasNext()){
							PolicyClausul maxClausul = iterator.next();
							
							if (maxClausul != null && maxClausul.getBenefitIteration() != null){
								
								int totalChildren = maxClausul.getBenefitIteration();
								
								
								
								Integer parentId = member.getParentMember().getMemberId();
								
								String[] eqChildren = {"deletedStatus","parentMember.memberId","relationship"};
								Object[] eqChildrenVal = {0,parentId,"CHILDREN"};
								
								int currentTotalChildren = memberService.getTotal(null,null,eqChildren,eqChildrenVal);
								
								System.out.println("DIDALAM MATERNITY CHECK MAX CHILDER = " +totalChildren + " CURRENT CHILDREN = " + currentTotalChildren);
								
								if (currentTotalChildren >= totalChildren){
									result.setAdditionalMessage("MAXCHILDREN");
									result.setResult(false);
									return result;
								}
								
							}
						}
					}
				}
								
				MemberProduct product = memberProductService.getMemberActiveProduct(member.getMemberId(), caseCategory.getCaseCategoryId());
				
				System.out.println("Product = " + product);

				String responseLine = "";
				int index = 0;
				
				if (product == null && memberBenefitList != null){
					// indicate divert benefit
					
					if (memberBenefitList.size() > 0){
						Iterator<MemberBenefit> iterator = memberBenefitList.iterator();
						
						if (iterator.hasNext()){
							MemberBenefit mb = iterator.next();
							
							if (mb != null){
								product = mb.getMemberProductId();
							}
						}
					}					
				}
				
				if (isUsingPolicyAsoFund){
					double currentFund = policy.getCurrentPolicyFund();
					double minFund = policy.getMinimumPolicyFund();
					
					if (minFund >= currentFund){

						System.out.println("IS USING POLICY ASO FUND AND SUPPOSED TO BE BLOCKED");
						boolean isExcessCovered = false;
						Product theProduct = product.getProductId();
						
						if (theProduct != null && theProduct.getExcessPaymentType().intValue() == Product.EXCESS_PAID_LATER){
							isExcessCovered = true;
							System.out.println("ASO FUND EMPTY BUT ALLOWED TO REGISTER");
						}
						
						if (!isExcessCovered){
							result.setAdditionalMessage("NOFUND");
							result.setResult(false);
							return result;	
						}
					}
				}
				if (isUsingPolicyCoverageFund){
					String[] eqFundParam = {"policyId.policyId","caseCategoryId.caseCategoryId","deletedStatus"};
					Object[] eqFundValue = {policy.getPolicyId(),caseCategory.getCaseCategoryId(),0};
					
					Collection<PolicyCoverageFund> fundList = policyCoverageFundService.search(null,null,eqFundParam,eqFundValue,0,1);
					boolean isFundApproved = false;
					
					if(fundList != null){
						Iterator<PolicyCoverageFund> fundIterator = fundList.iterator();
						
						if (fundIterator.hasNext()){
							PolicyCoverageFund fund = fundIterator.next();
							
							if(fund != null){
								double currentFund = fund.getCurrentFundValue();
								double minFund = fund.getMinimumAsoValue();
								
								if (minFund < currentFund){
									isFundApproved = true;
								}
								else {
									if (product.getProductId().getExcessPaymentType().intValue() == Product.EXCESS_PAID_LATER){
										isFundApproved = true;
									}
								}
							}
						}
					}
					
					if (!isFundApproved){
						result.setAdditionalMessage("NOFUND");
						result.setResult(false);
						return result;	
					}
				}


				//System.out.println("MEMBER PRODUCT ==> " + product + " LIMIT ==> " + product.getActualBenefitLimit());
				
				if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() > 0){

					System.out.println("DIDALAM ACTUAL BENEFIT > 0 ");
					System.out.println("BENEFIT LIST ==> " + memberBenefitList + " SIZE = " + memberBenefitList.size());
					
					if (memberBenefitList != null) {
						Vector<String> sendItemList = new Vector<String>();
						
						for (Iterator iterator = memberBenefitList.iterator(); iterator
								.hasNext();) {
	

							MemberBenefit memberBenefit = (MemberBenefit) iterator
									.next();
	
							if (memberBenefit != null
									&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
								String itemCategoryName = "";
								
								if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
								}
								else {
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
								}
								
								if (index == 0) {
									responseLine += itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;													
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
												double percentage = currentBenefit/totalBenefit*100;
												
												
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								} else {
									responseLine += "|" + itemCategoryName
									+ ","
									+ memberBenefit.getItemCategoryId()
									.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}					
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
	
												double percentage = currentBenefit/totalBenefit*100;
												
													
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								}
							}
							index++;
						}
					}
	
					System.out.println("RESPONSE LINE 1 ==> " + responseLine);
					Case theCase = new Case();
					theCase.setCaseCategoryId(caseCategory);
					theCase.setMemberId(member);
					//modified by aju for manual register
					//theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
					theCase.setCaseStartTime(admissionDt);
					
					
					/*
					if (edcTerminal != null){
						theCase.setTerminalId(edcTerminal);
					}
					*/
					
					if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
						/* modified by aju for manual
						theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
						*/
						theCase.setCaseStartTime(admissionDt);
					}
					else {
						//modified by aju for manual
						java.sql.Date ndt = admissionDt;
						
						//DateTime dt = new DateTime();
						DateTime dt = new DateTime(ndt);
						DateTime endTime = dt.plusDays(4);
						System.out.println("dischargeDate : " + endTime.toString());
						theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
					}
					
					if (provider != null){
						theCase.setProviderId(provider);
					}
					
					/* modified for manual
					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(merchantId);
					actionUser.setUser(user);
					*/
					ActionUser actionUser = theUser;
					
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(CaseStatus.CASE_OPEN);
					//modified for manual
					//theCase.setCaseType(Case.CASE_EDC);
					theCase.setCaseType(Case.CASE_EDC_MANUAL);
					theCase.setCaseStatusId(status);
					
					//Add by aju on 20150805, for COB :D
					if(member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().trim().equalsIgnoreCase("")){
						theCase.setIsLinkedMember(1);
					}
					else{
						theCase.setIsLinkedMember(0);
					}
					
					System.out.println("BEFORE CREATE CASE 1 ");
					Case newCase = caseService.create(theCase, actionUser);
	
					String caseNum = "";
					
					if (newCase != null){
						caseNum = newCase.getCaseNumber();
						result.setActionCode(newCase.getCaseId().toString());
					}
	
					responseLine = caseNum+"#"+responseLine;
					
					System.out.println("AFTER CREATE CASE 1");
					result.setAdditionalMessage(responseLine);
					result.setResult(true);
				}
				else if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() == -1.0){

					System.out.println("DIDALAM ACTUAL BENEFIT ---> -1 ");
					System.out.println("BENEFIT LIST ==> " + memberBenefitList + " SIZE = " + memberBenefitList.size());
					
					if (memberBenefitList != null) {
						Vector<String> sendItemList = new Vector<String>();
						
						for (Iterator iterator = memberBenefitList.iterator(); iterator
								.hasNext();) {
	
							//System.out.println("ITERATOR ==> " + index);
							MemberBenefit memberBenefit = (MemberBenefit) iterator
									.next();
	
							if (memberBenefit != null
									&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
								String itemCategoryName = "";
								
								if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
								}
								else {
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
								}
								
								if (index == 0) {
									responseLine += itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
													
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
												double percentage = currentBenefit/totalBenefit*100;
												
												
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								} else {
									responseLine += "|" + itemCategoryName
									+ ","
									+ memberBenefit.getItemCategoryId()
									.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}					
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
	
												double percentage = currentBenefit/totalBenefit*100;
												
													
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								}
							}
							index++;
						}
					}
	
					System.out.println("RESPONSE LINE 1 ==> " + responseLine);
					Case theCase = new Case();
					theCase.setCaseCategoryId(caseCategory);
					theCase.setMemberId(member);
					
					//modified for manual
					//theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
					theCase.setCaseStartTime(java.sql.Date.valueOf(admissionDate));
					
					/*
					if (edcTerminal != null){
						theCase.setTerminalId(edcTerminal);
					}
					*/
					
					if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
						//modified for manual
						//theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
						theCase.setCaseEndTime(java.sql.Date.valueOf(admissionDate));
					}
					else {
						//modified for manual
						java.sql.Date ndt = admissionDt;
						
						//DateTime dt = new DateTime();
						DateTime dt = new DateTime(ndt);
						DateTime endTime = dt.plusDays(4);
						
						theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
					}
					
					if (provider != null){
						theCase.setProviderId(provider);
					}
					
					/*  modified for manual
					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(merchantId);
					actionUser.setUser(user);
					*/
					ActionUser actionUser = theUser;
					
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(CaseStatus.CASE_OPEN);
					/* modified for manual
					theCase.setCaseType(Case.CASE_EDC);
					*/
					theCase.setCaseType(Case.CASE_EDC_MANUAL);
					theCase.setCaseStatusId(status);
					
					System.out.println("BEFORE CREATE CASE 1 ");
					Case newCase = caseService.create(theCase, actionUser);
	
					String caseNum = "";
					
					if (newCase != null){
						caseNum = newCase.getCaseNumber();
						result.setActionCode(newCase.getCaseId().toString());
					}
	
					responseLine = caseNum+"#"+responseLine;
					
					System.out.println("AFTER CREATE CASE 1");
					result.setAdditionalMessage(responseLine);
					result.setResult(true);
				}
				else {
					if (product != null && product.getActualBenefitLimit() != null && product.getActualBenefitLimit().doubleValue() == 0){
						System.out.println("BENEFIT HABIS!!");
						
						if (product.getProductId().getExcessPaymentType() != null && product.getProductId().getExcessPaymentType().intValue() == Product.EXCESS_PAID_LATER){

							if (memberBenefitList != null) {
								Vector<String> sendItemList = new Vector<String>();
								
								for (Iterator iterator = memberBenefitList.iterator(); iterator
										.hasNext();) {
			
									MemberBenefit memberBenefit = (MemberBenefit) iterator
											.next();
			
									if (memberBenefit != null
											&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
										String itemCategoryName = "";
										
										if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
											itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
										}
										else {
											itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
										}
										if (index == 0) {
											responseLine += itemCategoryName
													+ ","
													+ memberBenefit.getItemCategoryId()
													.getItemCategoryCode();
											
											if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
												if (memberBenefit.getBenefitLimit() != null){
													if (memberBenefit.getBenefitLimit() == -1){
														responseLine += ",AS CHARGE"; 
													}
													else {											
														String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
														responseLine += "," + limitSend;
													}
												}
											}
											else {
												if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
													if (memberBenefit.getBenefitLimit() != null){
														if (memberBenefit.getBenefitLimit() == -1){
															responseLine += ",AS CHARGE"; 
														}
														else {												
															
															String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
															responseLine += "," + limitSend;
															
														}
													}
												}
												else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
													double currentBenefit = 0;
													double totalBenefit = -1;
													double benefitUsage = 0;
													
													if (memberBenefit.getBenefitLimit() != null){
														totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
													}
													if (memberBenefit.getCurrentBenefitPosition() != null){
														currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
													}
													if (memberBenefit.getBenefitUsage() != null){
														benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
													}
													
													if (totalBenefit == -1){
														responseLine += ",DIJAMIN";
													}
													else {
														currentBenefit = totalBenefit - benefitUsage;
														double percentage = currentBenefit/totalBenefit*100;
														
														
														if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
															if (percentage < 10){
																responseLine += ",LMT <10%";
															}
															else if (percentage >= 10 && percentage < 30){
																responseLine += ",LMT <30%";
															}
															else if (percentage >= 30 && percentage < 50){
																responseLine += ",LMT <50%";
															}
															else {
																responseLine += ",DIJAMIN";
															}		
														}
														else {
															responseLine += ",DIJAMIN";
														}
													}
												}
											}
										} else {
											responseLine += "|" + itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
											
											if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
												if (memberBenefit.getBenefitLimit() != null){
													if (memberBenefit.getBenefitLimit() == -1){
														responseLine += ",AS CHARGE"; 
													}
													else {											
														String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
														responseLine += "," + limitSend;
													}
												}
											}
											else {
												if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
													if (memberBenefit.getBenefitLimit() != null){
														if (memberBenefit.getBenefitLimit() == -1){
															responseLine += ",AS CHARGE"; 
														}
														else {												
															String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
															responseLine += "," + limitSend;
														}
													}
												}
												else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
													double currentBenefit = 0;
													double totalBenefit = -1;
													double benefitUsage = 0;
													
													if (memberBenefit.getBenefitLimit() != null){
														totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
													}
													if (memberBenefit.getCurrentBenefitPosition() != null){
														currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
													}					
													if (memberBenefit.getBenefitUsage() != null){
														benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
													}
													if (totalBenefit == -1){
														responseLine += ",DIJAMIN";
													}
													else {
														currentBenefit = totalBenefit - benefitUsage;
			
														double percentage = currentBenefit/totalBenefit*100;
														
															
														if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
															if (percentage < 10){
																responseLine += ",LMT <10%";
															}
															else if (percentage >= 10 && percentage < 30){
																responseLine += ",LMT <30%";
															}
															else if (percentage >= 30 && percentage < 50){
																responseLine += ",LMT <50%";
															}
															else {
																responseLine += ",DIJAMIN";
															}		
														}
														else {
															responseLine += ",DIJAMIN";
														}
													}
												}
											}
										}
									}
									index++;
								}
							}
			
							System.out.println("AFTER RESPONSE --> " + responseLine);
							Case theCase = new Case();
							theCase.setCaseCategoryId(caseCategory);
							theCase.setMemberId(member);
							//modified for manual
							//theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
							theCase.setCaseStartTime(java.sql.Date.valueOf(admissionDate));
					
							/*
							if (edcTerminal != null){
								theCase.setTerminalId(edcTerminal);
							}
							*/
							
							if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
								//modified for manual
								//theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
								theCase.setCaseEndTime(java.sql.Date.valueOf(admissionDate));
							}
							else {
								//modified for manual
								java.sql.Date ndt = admissionDt; 
								
								//modified for manual
								//DateTime dt = new DateTime();
								DateTime dt = new DateTime(ndt);
								DateTime endTime = dt.plusDays(4);
								
								theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
							}
							
							if (provider != null){
								theCase.setProviderId(provider);
							}
							
						/* modified for manual
							ActionUser actionUser = new ActionUser();
							User user = new User();
							user.setUsername(merchantId);
							actionUser.setUser(user);
						*/	
							ActionUser actionUser = theUser;
							
							CaseStatus status = new CaseStatus();
							status.setCaseStatusId(CaseStatus.CASE_OPEN);
							
							//modified for manual
							//theCase.setCaseType(Case.CASE_EDC);
							theCase.setCaseType(Case.CASE_EDC_MANUAL);
							
							theCase.setCaseStatusId(status);
							
							System.out.println("BEFORE CREATE CASE 2 ");
							Case newCase = caseService.create(theCase, actionUser);
							String caseNum = "";
							
							if (newCase != null){
								caseNum = newCase.getCaseNumber();
								result.setActionCode(newCase.getCaseId().toString());
							}
			
							responseLine = caseNum+"#"+responseLine;
							result.setAdditionalMessage(responseLine);
							result.setResult(true);
						}
						else if (product.getProductId().getExcessPaymentType() != null && product.getProductId().getExcessPaymentType().intValue() == Product.EXCESS_PAID_UPFRONT){
							result.setAdditionalMessage("EMPTYBENEFIT");
							result.setResult(false);
						}
						else {
							result.setAdditionalMessage("EMPTYBENEFIT");
							result.setResult(false);
						}
					}
					else {
						System.out.println("TERNYATA TIDAK DICOVER --> EXCESS");
						result.setAdditionalMessage("EMPTYBENEFIT");
						result.setResult(false);
					}
				}
				
				
				
			}
			else {
				System.out.println("ELSE --> FALSE");
				result.setResult(false);
				
				int status = member.getStatus().intValue();
				
				System.out.println("STATUS ===> " + status); 
				if (status == SubscriptionStatus.RESIGNED || status == -2){
					result.setAdditionalMessage("RESIGNED");
				}
				else if (status == SubscriptionStatus.TERMINATED ){
					result.setAdditionalMessage("TERMINATED");
				}
				else if (status == SubscriptionStatus.BLOCKED || status == SubscriptionStatus.INACTIVE){
					result.setAdditionalMessage("BLOCKED");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	//Add by aju on 20150707, mimic thhe EDC flow for registration
	public ActionResult authorizeMemberEDCManagedCare(String cardNumber, String serviceType,
			String referenceNumber, String merchantId, String terminalId, String admissionDate, ActionUser theUser) {
		// TODO Auto-generated method stub

		ActionResult result = new ActionResult();
		result.setResult(false);

		try {
			// check clausul dulu.. jika tidak ada maka default true;

			Member member = memberService.getMemberByCardNumber(cardNumber);

			//modified using the providerId
			//Provider provider = providerService.getByProviderCode(merchantId);
			Provider provider = providerService.get(Integer.parseInt(merchantId));
			
			java.sql.Date admissionDt = java.sql.Date.valueOf(admissionDate);
			
			CaseCategory caseCategory = caseCategoryService
					.getCaseCategoryEDC(serviceType);
			
			System.out.println("Service TYPE = " + serviceType);
			Poliklinik poliklinik = poliklinikService.getPoliByEdcCode(serviceType);
			
			Case referedCase = null;
			
			
			if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE) {

				
				int totalCase = 0;
				Integer ccId = null;
				
				if (caseCategory != null){
					ccId = caseCategory.getCaseCategoryId();
				}
				else {
					ccId = poliklinik.getCaseCategoryId().getCaseCategoryId();
				}
				
				String[] eqExistingParam = {"providerId.providerId","deletedStatus","caseStartTime","memberId.memberId"
						,"caseStatusId.caseStatusId","caseCategoryId.caseCategoryId"};
				
				/* modified for manual
				Object[] eqExistingValue = {provider.getProviderId(),Integer.valueOf(0),new java.sql.Date(System.currentTimeMillis()),
						member.getMemberId(),CaseStatus.CASE_OPEN,ccId};
				*/
				Object[] eqExistingValue = {provider.getProviderId(),Integer.valueOf(0),admissionDt,
						member.getMemberId(),CaseStatus.CASE_OPEN,ccId};
				
				totalCase = caseService.getTotal(null,null,eqExistingParam,eqExistingValue);
				
				if (totalCase > 0){
					result.setAdditionalMessage("ALREADYREGISTERED");
					result.setResult(false);
					return result;
				}
				
				System.out.println("Membership status => ACTIVE");
				// check apakah menggunakan RUJUKAN atau tidak
				
				if (caseCategory == null){
					if (poliklinik != null){
						caseCategory = poliklinik.getCaseCategoryId();
					}
				}
				
				Collection<MemberBenefit> memberBenefitList = memberBenefitService.getMemberBenefitList(member.getMemberId(),
						caseCategory.getCaseCategoryId());
				
				if (poliklinik != null && referenceNumber != null && member != null){
					String[] eqRefCaseParam = {"deletedStatus","memberId.memberId","caseReferalNumber",
							"referedPoliklinikId.poliklinikId","referalStatus"};
					System.out.println("GETTING REFERED CASE = " + referenceNumber + " poliklinik = " + poliklinik.getPoliklinikId());
					 
					Object[] eqValueRefCase = {Integer.valueOf(0),member.getMemberId(),referenceNumber,poliklinik.getPoliklinikId(),Integer.valueOf(1)};
					
					Collection<Case> referedCaseList = caseService.search(null,null,eqRefCaseParam,eqValueRefCase,0,1);
					
					if (referedCaseList != null){
						Iterator<Case> iterator = referedCaseList.iterator();
						
						if (iterator.hasNext()){
							referedCase = iterator.next();
							System.out.println("GETTING REFERED CASE = " + referenceNumber + " poliklinik = " + poliklinik.getPoliklinikId() +" CASE NUM = " + referedCase.getCaseNumber());
						}
					}
				}
				
				boolean isRefered = false;			
				
				
				if (provider != null){
					if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.PPK2){
						if (referedCase == null){
							
							if (poliklinik != null && poliklinik.getNotUsingReference() != null){
								if (poliklinik.getNotUsingReference().intValue() == 0){
									result.setResult(false);
									result.setAdditionalMessage("NOTREFERED");
									return result;	
								}
							}
							else {
								result.setResult(false);
								result.setAdditionalMessage("NOTREFERED");
								return result;
							}
						}
					}
					else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.RUMAH_SAKIT){
						// PPK3 dan atau PPK2
						if (referedCase == null){
							if (poliklinik != null && poliklinik.getNotUsingReference() != null){
								if (poliklinik.getNotUsingReference().intValue() == 0){
									result.setResult(false);
									result.setAdditionalMessage("NOTREFERED");
									return result;	
								}
							}
							else {
								result.setResult(false);
								result.setAdditionalMessage("NOTREFERED");
								return result;
							}							
						}						
					}					
					else if (provider.getProviderCategoryId().getProviderCategoryId().intValue() == ProviderCategory.LAB){
						
					}
				}
				
				
				MemberProduct product = memberProductService.getMemberActiveProduct(member.getMemberId(), caseCategory.getCaseCategoryId());

				String responseLine = "";
				int index = 0;
				
				if (product != null){

					System.out.println("MEMBER PRODUCT ==> " + product + " LIMIT ==> " + product.getActualBenefitLimit());
				
				
					System.out.println("DIDALAM ACTUAL BENEFIT > 0 ");
					System.out.println("BENEFIT LIST ==> " + memberBenefitList + " SIZE = " + memberBenefitList.size());
					
					if (memberBenefitList != null) {
						Vector<String> sendItemList = new Vector<String>();
						
						for (Iterator iterator = memberBenefitList.iterator(); iterator
								.hasNext();) {
	
							System.out.println("ITERATOR ==> " + index);
							MemberBenefit memberBenefit = (MemberBenefit) iterator
									.next();
	
							if (memberBenefit != null
									&& memberBenefit.getIsEDCEnabled() != null && memberBenefit.getIsEDCEnabled().booleanValue()) {
								String itemCategoryName = "";
								
								if (memberBenefit.getItemCategoryId().getItemCategoryEDCName() != null){
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryEDCName();
								}
								else {
									itemCategoryName = memberBenefit.getItemCategoryId().getItemCategoryName();
								}
								
								if (index == 0) {
									responseLine += itemCategoryName
											+ ","
											+ memberBenefit.getItemCategoryId()
											.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getCurrentBenefitPosition().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
													
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
												double percentage = currentBenefit/totalBenefit*100;
												
												
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								} else {
									responseLine += "|" + itemCategoryName
									+ ","
									+ memberBenefit.getItemCategoryId()
									.getItemCategoryCode();
									
									if (memberBenefit.getBenefitLimitPublishedOnEdc() == null){
										if (memberBenefit.getBenefitLimit() != null){
											if (memberBenefit.getBenefitLimit() == -1){
												responseLine += ",AS CHARGE"; 
											}
											else {											
												String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);											
												responseLine += "," + limitSend;
											}
										}
									}
									else {
										if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 1){
											if (memberBenefit.getBenefitLimit() != null){
												if (memberBenefit.getBenefitLimit() == -1){
													responseLine += ",AS CHARGE"; 
												}
												else {												
													String limitSend = StringUtil.convertEDCNumber(memberBenefit.getBenefitLimit().doubleValue(), 9);												
													responseLine += "," + limitSend;
												}
											}
										}
										else if (memberBenefit.getBenefitLimitPublishedOnEdc().intValue() == 0) {
											double currentBenefit = 0;
											double totalBenefit = -1;
											double benefitUsage = 0;
											
											if (memberBenefit.getBenefitLimit() != null){
												totalBenefit = memberBenefit.getBenefitLimit().doubleValue();
											}
											if (memberBenefit.getCurrentBenefitPosition() != null){
												currentBenefit = memberBenefit.getCurrentBenefitPosition().doubleValue();
											}					
											if (memberBenefit.getBenefitUsage() != null){
												benefitUsage = memberBenefit.getBenefitUsage().doubleValue();
											}
											if (totalBenefit == -1){
												responseLine += ",DIJAMIN";
											}
											else {
												currentBenefit = totalBenefit - benefitUsage;
	
												double percentage = currentBenefit/totalBenefit*100;
												
													
												if (memberBenefit.getBenefitCalculationMethod().getBenefitUsageTypeId().intValue() == MemberBenefit.ACCUMULATIVE_VALUE){
													if (percentage < 10){
														responseLine += ",LMT <10%";
													}
													else if (percentage >= 10 && percentage < 30){
														responseLine += ",LMT <30%";
													}
													else if (percentage >= 30 && percentage < 50){
														responseLine += ",LMT <50%";
													}
													else {
														responseLine += ",DIJAMIN";
													}		
												}
												else {
													responseLine += ",DIJAMIN";
												}
											}
										}
									}
								}
							}
							index++;
						}
				
	
						System.out.println("RESPONSE LINE 1 ==> " + responseLine);
						Case theCase = new Case();
						theCase.setCaseCategoryId(caseCategory);
						theCase.setMemberId(member);
						
						/* modified for manual
						theCase.setCaseStartTime(new java.sql.Date(System.currentTimeMillis()));
						*/
						theCase.setCaseStartTime(admissionDt);
						
						theCase.setIsGLIssued(1);
						
						/*
						if (terminalId != null){
							String[] eqTerm = {"providerId.providerId","deviceNumber","deletedStatus"};
							Object[] eqValueTerm = {provider.getProviderId(),terminalId,0};							
							
							Collection<EdcTerminal> edcTerminalList = edcTerminalService.search(null,null,eqTerm,eqValueTerm,0,1);
							
							if (edcTerminalList != null && edcTerminalList.size() > 0){
								
								Iterator<EdcTerminal> termIterator= edcTerminalList.iterator();
								
								EdcTerminal edcTerminal = termIterator.next();
								
								if (edcTerminal != null){
									theCase.setTerminalId(edcTerminal);
									theCase.setMerchantId(provider.getEdcCode());
								}
							}						
						}
						*/
						
						if (caseCategory.getCaseCategoryId().intValue() != CaseCategory.INPATIENT){
							//modified for manual
							//theCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
							theCase.setCaseEndTime(admissionDt);
						}
						else {
							//modified for manual
							java.sql.Date ndt = admissionDt;
							
							//modified for manual
							//DateTime dt = new DateTime();
							DateTime dt = new DateTime(ndt);
							
							DateTime endTime = dt.plusDays(4);
							
							theCase.setCaseEndTime(new java.sql.Date(endTime.getMillis()));
						}
						
						if (provider != null){
							theCase.setProviderId(provider);
						}
						
					/* modified for manual
						ActionUser actionUser = new ActionUser();
						User user = new User();
						user.setUsername(merchantId);
						actionUser.setUser(user);
					*/	
						ActionUser  actionUser = theUser;
						
						CaseStatus status = new CaseStatus();
						status.setCaseStatusId(CaseStatus.CASE_OPEN);
						
						/*  modified for manual
						theCase.setCaseType(Case.CASE_EDC);
						*/
						theCase.setCaseType(Case.CASE_EDC_MANUAL);
						
						theCase.setCaseStatusId(status);
						
						//Add by aju on 20150805, for COB :D
						if(member.getLinkedCardNumber() != null && !member.getLinkedCardNumber().trim().equalsIgnoreCase("")){
							theCase.setIsLinkedMember(1);
						}
						else{
							theCase.setIsLinkedMember(0);
						}
						
						System.out.println("BEFORE CREATE CASE 1 ");
						Case newCase = caseService.create(theCase, actionUser);
		
						String caseNum = "";
						
						if (newCase != null){
							caseNum = newCase.getCaseNumber();
							result.setActionCode(newCase.getCaseId().toString());
						}
		
						responseLine = caseNum+"#"+responseLine;
						
						System.out.println("AFTER CREATE CASE 1");
						result.setAdditionalMessage(responseLine);
						result.setReferenceNumber(referenceNumber);
						result.setResult(true);
					}
					else {
						result.setResult(false);
						result.setAdditionalMessage("NOTCOVERED");
					}
				}
				else {
					result.setResult(false);
					result.setAdditionalMessage("NOTCOVERED");
				}
			}
			else {
				System.out.println("ELSE --> FALSE");
				result.setResult(false);
				
				int status = member.getStatus().intValue();
				
				System.out.println("STATUS ===> " + status); 
				if (status == SubscriptionStatus.RESIGNED || status == -2){
					result.setAdditionalMessage("RESIGNED");
				}
				else if (status == SubscriptionStatus.TERMINATED ){
					result.setAdditionalMessage("TERMINATED");
				}
				else if (status == SubscriptionStatus.BLOCKED || status == SubscriptionStatus.INACTIVE){
					result.setAdditionalMessage("BLOCKED");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
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

	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}

	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public MemberElectronicCardService getMemberCardService() {
		return memberCardService;
	}

	public void setMemberCardService(MemberElectronicCardService memberCardService) {
		this.memberCardService = memberCardService;
	}

	// class-

}
