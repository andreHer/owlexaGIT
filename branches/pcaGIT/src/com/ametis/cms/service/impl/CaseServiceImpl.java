
package com.ametis.cms.service.impl;


import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

import com.ametis.cms.dao.CaseDao;
import com.ametis.cms.dao.CaseMedicineDao;
import com.ametis.cms.dao.CaseProcedureDao;
import com.ametis.cms.dao.CaseStatusDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseHistory;
import com.ametis.cms.datamodel.CaseItem;
import com.ametis.cms.datamodel.CaseMedicine;
import com.ametis.cms.datamodel.CaseProvider;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.MemberProvider;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderTypeDiagnosisTreatment;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseHistoryService;
import com.ametis.cms.service.CaseItemService;
import com.ametis.cms.service.CaseProviderService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.CaseStatusService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderTypeDiagnosisTreatmentService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * Case is a servlet controller for case Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class CaseServiceImpl implements CaseService

// extends+ 

// extends- 

{
	
	private CaseDao  myCaseDao;
	private ActivityLogService activityLogService;
	private NumberCounterService numberCounterService;
	private ConfigurationService configurationService;
	private ClaimService claimService;
	private CaseItemService caseItemService;
	private ClaimItemService claimItemService;
	private CaseMedicineDao caseMedicineDao;
	private CaseProcedureDao caseProcedureDao;
	private MemberProductService memberProductService;
	private UserService userService;
	private PolicyProviderService policyProviderService;
	private PolicyService policyService;
	private MemberProviderService memberProviderService;
	private ClientProviderService clientProviderService;
	private MemberGroupProviderService memberGroupProviderService;
	private MemberService memberService;
	private CaseProviderService caseProviderService;
	private CaseHistoryService caseHistoryService;
	private DiagnosisService diagnosisService;
	private ProviderService providerService;
	private CaseStatusDao caseStatusService;
	private ProviderTypeDiagnosisTreatmentService providerTypeDiagnosisTreatmentService;
	
	
	
	
	
	
	
	public CaseStatusDao getCaseStatusService() {
		return caseStatusService;
	}
	public void setCaseStatusService(CaseStatusDao caseStatusService) {
		this.caseStatusService = caseStatusService;
	}
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}
	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	public CaseHistoryService getCaseHistoryService() {
		return caseHistoryService;
	}
	public void setCaseHistoryService(CaseHistoryService caseHistoryService) {
		this.caseHistoryService = caseHistoryService;
	}
	public CaseProviderService getCaseProviderService() {
		return caseProviderService;
	}
	public void setCaseProviderService(CaseProviderService caseProviderService) {
		this.caseProviderService = caseProviderService;
	}
	public MemberGroupProviderService getMemberGroupProviderService() {
		return memberGroupProviderService;
	}
	public void setMemberGroupProviderService(
			MemberGroupProviderService memberGroupProviderService) {
		this.memberGroupProviderService = memberGroupProviderService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public PolicyProviderService getPolicyProviderService() {
		return policyProviderService;
	}
	public void setPolicyProviderService(PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
	}
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
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
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public MemberProductService getMemberProductService() {
		return memberProductService;
	}
	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}
	public CaseMedicineDao getCaseMedicineDao() {
		return caseMedicineDao;
	}
	public void setCaseMedicineDao(CaseMedicineDao caseMedicineDao) {
		this.caseMedicineDao = caseMedicineDao;
	}
	public CaseProcedureDao getCaseProcedureDao() {
		return caseProcedureDao;
	}
	public void setCaseProcedureDao(CaseProcedureDao caseProcedureDao) {
		this.caseProcedureDao = caseProcedureDao;
	}
	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}
	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}
	public CaseItemService getCaseItemService() {
		return caseItemService;
	}
	public void setCaseItemService(CaseItemService caseItemService) {
		this.caseItemService = caseItemService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	public NumberCounterService getNumberCounterService() {
		return numberCounterService;
	}
	public void setNumberCounterService(NumberCounterService numberCounterService) {
		this.numberCounterService = numberCounterService;
	}
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public void setCaseDao (CaseDao object){
		this.myCaseDao = object;
	}
	public CaseDao getCaseDao (){
		return this.myCaseDao;
	}
	

	public ProviderTypeDiagnosisTreatmentService getProviderTypeDiagnosisTreatmentService() {
		return providerTypeDiagnosisTreatmentService;
	}
	public void setProviderTypeDiagnosisTreatmentService(
			ProviderTypeDiagnosisTreatmentService providerTypeDiagnosisTreatmentService) {
		this.providerTypeDiagnosisTreatmentService = providerTypeDiagnosisTreatmentService;
	}
	private String generateCaseCounterNumber (Case object){
		
		String result = "";	
		
		int res = 0;
		
		try {
		
			Configuration configuration  = configurationService.getClientConfiguration(object.getMemberId().getClientId().getClientId());
			
			if (configuration == null){
				configuration = configurationService.getSystemConfiguration();
			}
			Integer isUsingSequence = configuration.getIsUsingSequenceNumber();
			
			if (isUsingSequence != null && isUsingSequence.intValue() == 1){
				String seqSQL = configuration.getCaseNumberSeqName();
				
				Session session = myCaseDao.getCaseSession();
				if (session != null){
					SQLQuery q = session.createSQLQuery(seqSQL);
					
					List<Object> list = q.list();
					
					if (list != null && !list.isEmpty()){
						Iterator<Object> iterator = list.iterator();
						
						if (iterator.hasNext()){
							Object nextval = iterator.next();
							
							if (nextval != null){
								
								BigInteger num = (BigInteger) nextval;
								if (num != null){
									res = num.intValue();
								}
							}
						}
					}
				}
			}
			else {
				res = configuration.getCaseNumber();
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		if (res > 0 && res < 10){
			result = "0000"+res;
		}
		else if (res >= 10 && res < 100){
			result = "000"+res;
		}
		else if (res >= 100 && res < 1000){
			result = "00"+res;
		}
		else if (res >= 1000 && res < 10000){
			result = "0"+res;
		}
		else if (res >= 10000){
			result = ""+res;
		}
			
		
		return result;
	}
	/*
	* Method create (Case object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public Case create (Case object,ActionUser actionUser) throws Exception {

		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setIsClaimed(0);
		
		Configuration configuration = configurationService.getClientConfiguration(object.getMemberId().getClientId().getClientId());
		
		if (configuration == null){
			configuration = configurationService.getSystemConfiguration();
		}
		
		DateTime startTime = new DateTime(object.getCaseStartTime().getTime());
		
		if (object.getCaseEndTime() != null){
			DateTime endTime = new DateTime(object.getCaseEndTime().getTime());
			
			Days days = Days.daysBetween(startTime, endTime);
			object.setLongOfStay(days.getDays());
		}
		
		String caseIdString = ""+object.getCaseId();
		String caseCCString = ""+object.getCaseCategoryId().getCaseCategoryId();
		//object.setCaseNumber(yearString + monthString + " . " + caseIdString + " . " + caseCCString);
		
		String caseNumber = "";
		
		String counter = generateCaseCounterNumber(object);
		
		String month = "";
		
		DateTime dateTime = new DateTime();
		
		if (dateTime.getMonthOfYear() < 10){
			month += "0"+dateTime.getMonthOfYear();
		}
		else {
			month += dateTime.getMonthOfYear();
		}
		

		caseNumber = configuration.getCaseNumberTemplate();
		caseNumber = caseNumber.replace("${counter}", counter);
		caseNumber = caseNumber.replace("${month}", month);
		caseNumber = caseNumber.replace("${year}", dateTime.getYear()+"");
		
		object.setCaseNumber(caseNumber);
		
	
		
		object.setCreatedTime(new java.sql.Timestamp (System.currentTimeMillis()));
		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));
		object.setIsGLIssued(0);
		
		if (actionUser != null){
			User user = actionUser.getUser();	
			if (user != null){
				object.setCreatedBy(user.getUsername());
				object.setModifiedBy(user.getUsername());
			}
		}
		
		String[] requiredMember = {"Member.CurrentPolicyId"};
		Member member = memberService.get(object.getMemberId().getMemberId(),requiredMember);
		
		if (member != null){
			object.setMemberName(member.getFirstName());
			object.setMemberNumber(member.getCustomerPolicyNumber());
		}
		
		if (memberProductService != null){
            MemberProduct memberProduct = memberProductService.getMemberActiveProduct(object.getMemberId().getMemberId(), object.getCaseCategoryId().getCaseCategoryId());

            if (memberProduct != null) {
                object.setPreRemainingLimit(memberProduct.getActualBenefitLimit());
            }
		}
		
		Provider provider = null;
		
		if (object.getProviderId() != null){
			provider = providerService.get(object.getProviderId().getProviderId());
			
			object.setProviderName(provider.getProviderName());
		}
		
		if (member.getCurrentPolicyId() != null){
			int providerAllocation = member.getCurrentPolicyId().getProviderAllocationType() == null ? 0 : member.getCurrentPolicyId().getProviderAllocationType();
			
			CaseStatus rejectStatus = new CaseStatus();
			rejectStatus.setCaseStatusId(Case.CASE_REJECT);

			String serviceType = "";
			
			if (object.getCaseCategoryId().getCaseCategoryId().intValue() ==  CaseCategory.INPATIENT){
				serviceType = "inpatient";
			}
			if (object.getCaseCategoryId().getCaseCategoryId().intValue() ==  CaseCategory.OUTPATIENT){
				serviceType = "outpatient";
			}
			if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.DENTAL){
				serviceType = "dental";
			}
			if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
				serviceType = "maternity";
			}
			if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.OPTICAL){
				serviceType = "optical";
			}
			if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MEDICAL_CHECK_UP){
				serviceType = "mcuLab";
			}
			
			if (providerAllocation == Policy.PROVIDER_CLIENT_USAGE_TYPE){
				
				String[] eqParam = {"clientId.clientId","providerId.providerId","deletedStatus",serviceType};
				Object[] eqValue = {member.getCurrentPolicyId().getClientId().getClientId(),provider.getProviderId(),Integer.valueOf(0),1};
				
				int total = clientProviderService.getTotal(null,null,eqParam,eqValue);
				
				if (total == 0){
					object.setCaseStatusId(rejectStatus);
				}
			}
			else if (providerAllocation == Policy.PROVIDER_POLICY_USAGE_TYPE){
				
				String[] eqParam = {"policyId.policyId","providerId.providerId","deletedStatus",serviceType};
				Object[] eqValue = {member.getCurrentPolicyId().getPolicyId(),provider.getProviderId(),Integer.valueOf(0),1};
				
				int total = policyProviderService.getTotal(null,null,eqParam,eqValue);
				
				if (total == 0){
					object.setCaseStatusId(rejectStatus);
				}
			}
			else if (providerAllocation == Policy.PROVIDER_MEMBER_USAGE_TYPE){
				String[] eqParam = {"memberId.memberId","providerId.providerId","deletedStatus",serviceType};
				Object[] eqValue = {member.getMemberId(),provider.getProviderId(),Integer.valueOf(0),1};
				
				int total = memberProviderService.getTotal(null,null,eqParam,eqValue);
				
				if (total == 0){
					object.setCaseStatusId(rejectStatus);
				}
			}
			else if (providerAllocation == Policy.PROVIDER_GROUP_USAGE_TYPE){
				String[] eqParam = {"memberGroupId.memberGroupId","providerId.providerId","deletedStatus",serviceType};
				Object[] eqValue = {member.getMemberGroupId().getMemberGroupId(),provider.getProviderId(),Integer.valueOf(0),1};
				
				int total = memberGroupProviderService.getTotal(null,null,eqParam,eqValue);
				
				if (total == 0){
					object.setCaseStatusId(rejectStatus);
				}
			}
		
		}

		if (object.getDiagnosis1Id() != null){
			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis1Id().getDiagnosisId());
			
			if (diagnosis != null){
				object.setDiagnosis1Code(diagnosis.getDiagnosisCode());
				object.setDiagnosis1Name(diagnosis.getDescription());
				
				//Edit 20151008 by FVO, for get treatment rate by Diagnosis
				//String[] eqParam = {"diagnosisId.diagnosisId","deletedStatus","treatmentRiskId.treatmentRiskId","providerTypeId.providerTypeId"};
				//Object[] eqValue = {object.getDiagnosis1Id().getDiagnosisId(),Integer.valueOf(0),Integer.valueOf(1),Integer.valueOf(1)};
				if(object.getHasComplication() != null){
					//if(provider.getProviderTypeId() != null)
					
					String[] eqParam = {"diagnosisId.diagnosisId","deletedStatus","treatmentRiskId.treatmentRiskId","providerTypeId.providerTypeId"};
					Object[] eqValue = {object.getDiagnosis1Id().getDiagnosisId(),Integer.valueOf(0), object.getHasComplication(), 
							(provider.getProviderTypeId() != null ? provider.getProviderTypeId().getProviderTypeId() : Integer.valueOf(1))};
					
					Collection<ProviderTypeDiagnosisTreatment> inaCBGList = providerTypeDiagnosisTreatmentService.search(null,null,eqParam,eqValue,0,1);
					
					if (inaCBGList != null && inaCBGList.size() > 0){
						java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList.iterator();
						
						if (iterator.hasNext()){
							ProviderTypeDiagnosisTreatment inaCBG = iterator.next();
							if(inaCBG.getTreatmentRate() != null){
								object.setInaCbgTreatmentRate(inaCBG.getTreatmentRate());
							}
							
							//For Long of Stay by Diagnosis
							if(inaCBG.getAlos() != null){
								object.setInaCbgAlos(inaCBG.getAlos());
							}
						}
					}else{
						object.setInaCbgTreatmentRate(0.0);
					}
				}
			}
		}
		if (object.getDiagnosis2Id() != null){
			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis2Id().getDiagnosisId());
			
			if (diagnosis != null){
				object.setDiagnosis2Code(diagnosis.getDiagnosisCode());
				object.setDiagnosis2Name(diagnosis.getDescription());
			}
		}
		if (object.getDiagnosis3Id() != null){
			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis3Id().getDiagnosisId());
			
			if (diagnosis != null){
				object.setDiagnosis3Code(diagnosis.getDiagnosisCode());
				object.setDiagnosis3Name(diagnosis.getDescription());
			}
		}
		
		if (object.getCaseStatusId() != null){
			CaseStatus status = caseStatusService.get(object.getCaseStatusId().getCaseStatusId());
			
			if (status != null){
				object.setCaseStatusName(status.getCaseStatusName());
			}
		}
		
		//Add 20150810 by FVO for threshold calculation
		if (object.getCaseCategoryId().getCaseCategoryId().intValue() ==  CaseCategory.INPATIENT){
			if(configuration.getInpatientThreshold() != null){
				object.setThresholdValue(configuration.getInpatientThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() ==  CaseCategory.OUTPATIENT){
			if(configuration.getOutpatientThreshold() != null){
				object.setThresholdValue(configuration.getOutpatientThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.DENTAL){
			if(configuration.getDentalThreshold() != null){
				object.setThresholdValue(configuration.getDentalThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
			if(configuration.getMaternityThreshold() != null){
				object.setThresholdValue(configuration.getMaternityThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.OPTICAL){
			if(configuration.getOpticalThreshold() != null){
				object.setThresholdValue(configuration.getOpticalThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MEDICAL_CHECK_UP){
			if(configuration.getMedicalCheckUpThreshold() != null){
				object.setThresholdValue(configuration.getMedicalCheckUpThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		

		Case result = myCaseDao.create (object);
		
		
		
		if (result != null ){
			
			CaseStatus openStatus = new CaseStatus();
			//modified by aju on 20150824, for preAdmission :D
			//openStatus.setCaseStatusId(CaseStatus.CASE_OPEN);
			if(result.getCaseStatusId().getCaseStatusId() == Case.CASE_PRE_AUTH){
				openStatus.setCaseStatusId(CaseStatus.CASE_PRE_AUTH);
			}
			else{
				openStatus.setCaseStatusId(CaseStatus.CASE_OPEN);
			}
			
			String actionBy = actionUser.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(openStatus);
			history.setAfterStatus(openStatus);
			history.setDescription(result.getDescription());
			
			history.setBeforeActionData("");
			history.setActionType("OPEN CASE");
			history.setAfterActionData(result.toHistoryString());
			history.setCaseId(result);
			
			if (result.getDiagnosis1Name() != null){
				history.setDiagnosisCode(result.getDiagnosis1Name());
				
			}
			if (result.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(result.getDiagnosis2Name());
			}
			if (result.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(result.getDiagnosis3Name());
			}
			if (result.getProviderName() != null){
				history.setProviderName(result.getProviderName());
			}
			if (result.getCaseNumber() != null){
				history.setCaseNumber(result.getCaseNumber());
			}
			if (result.getMemberName() != null){
				history.setMemberName(result.getMemberName());
			}
			if (result.getMemberNumber() != null){
				history.setMemberNumber(result.getMemberNumber());
			}
			if (result.getCaseCategoryId() != null){
				history.setCaseCategoryName(result.getCaseCategoryId().getCaseCategoryName());
			}
			
			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(result.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			
			caseHistoryService.create(history, actionUser);
			
			if (result.getReferedCaseId() == null){
				CaseProvider cp = new CaseProvider();
				cp.setInitialCase(result);
				
			}
		}
		configuration.setCaseNumber(configuration.getCaseNumber().intValue()+1);
		configurationService.update(configuration, actionUser);
		return result;
	}
	/*
	* Method update (Case object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public Case update (Case object,ActionUser actionUser) throws Exception{

		object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		if (actionUser != null){
			User user = actionUser.getUser();	
			if (user != null){
				object.setModifiedBy (user.getUsername());
			}
		}

		String[] requiredMember = {"Member.CurrentPolicyId"};
		Member member = memberService.get(object.getMemberId().getMemberId(),requiredMember);
		
		if (member != null){
			object.setMemberName(member.getFirstName());
			object.setMemberNumber(member.getCustomerPolicyNumber());
		}
		
		Configuration configuration = configurationService.getClientConfiguration(object.getMemberId().getClientId().getClientId());
		
		if (memberProductService != null){
            MemberProduct memberProduct = memberProductService.getMemberActiveProduct(object.getMemberId().getMemberId(), object.getCaseCategoryId().getCaseCategoryId());

            if (memberProduct != null) {
                object.setPreRemainingLimit(memberProduct.getActualBenefitLimit());
            }
		}
		if (object.getDiagnosis1Id() != null){
			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis1Id().getDiagnosisId());
			
			if (diagnosis != null){
				object.setDiagnosis1Code(diagnosis.getDiagnosisCode());
				object.setDiagnosis1Name(diagnosis.getDescription());
				
				//Edit 20151008 by FVO, for get treatment rate by Diagnosis
				//String[] eqParam = {"diagnosisId.diagnosisId","deletedStatus","treatmentRiskId.treatmentRiskId","providerTypeId.providerTypeId"};
				//Object[] eqValue = {object.getDiagnosis1Id().getDiagnosisId(),Integer.valueOf(0),Integer.valueOf(1),Integer.valueOf(1)};
				if(object.getHasComplication() != null){
					Provider provider = null;
					
					if (object.getProviderId() != null){
						provider = providerService.get(object.getProviderId().getProviderId());
						object.setProviderName(provider.getProviderName());
					}
					if(provider != null){
						String[] eqParam = {"diagnosisId.diagnosisId","deletedStatus","treatmentRiskId.treatmentRiskId","providerTypeId.providerTypeId"};
						Object[] eqValue = {object.getDiagnosis1Id().getDiagnosisId(),Integer.valueOf(0), object.getHasComplication(), 
								(provider.getProviderTypeId() != null ? provider.getProviderTypeId().getProviderTypeId() : Integer.valueOf(1))};
						
						Collection<ProviderTypeDiagnosisTreatment> inaCBGList = providerTypeDiagnosisTreatmentService.search(null,null,eqParam,eqValue,0,1);
						
						if (inaCBGList != null && inaCBGList.size() > 0){
							java.util.Iterator<ProviderTypeDiagnosisTreatment> iterator = inaCBGList.iterator();
							
							if (iterator.hasNext()){
								ProviderTypeDiagnosisTreatment inaCBG = iterator.next();
								if(inaCBG.getTreatmentRate() != null){
									object.setInaCbgTreatmentRate(inaCBG.getTreatmentRate());
								}
								
								//For Long of Stay by Diagnosis
								if(inaCBG.getAlos() != null){
									object.setInaCbgAlos(inaCBG.getAlos());
								}
							}
						}else{
							object.setInaCbgTreatmentRate(0.0);
						}
					}
				}
			}
		}
		if (object.getDiagnosis2Id() != null){
			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis2Id().getDiagnosisId());
			
			if (diagnosis != null){
				object.setDiagnosis2Code(diagnosis.getDiagnosisCode());
				object.setDiagnosis2Name(diagnosis.getDescription());
			}
		}
		if (object.getDiagnosis3Id() != null){
			Diagnosis diagnosis = diagnosisService.get(object.getDiagnosis3Id().getDiagnosisId());
			
			if (diagnosis != null){
				object.setDiagnosis3Code(diagnosis.getDiagnosisCode());
				object.setDiagnosis3Name(diagnosis.getDescription());
			}
		}
		
		if (object.getCaseStatusId() != null){
			CaseStatus status = caseStatusService.get(object.getCaseStatusId().getCaseStatusId());
			
			if (status != null){
				object.setCaseStatusName(status.getCaseStatusName());
			}
		}
		
		
		//Add 20150810 by FVO for threshold calculation
		if (object.getCaseCategoryId().getCaseCategoryId().intValue() ==  CaseCategory.INPATIENT){
			if(configuration.getInpatientThreshold() != null){
				object.setThresholdValue(configuration.getInpatientThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() ==  CaseCategory.OUTPATIENT){
			if(configuration.getOutpatientThreshold() != null){
				object.setThresholdValue(configuration.getOutpatientThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.DENTAL){
			if(configuration.getDentalThreshold() != null){
				object.setThresholdValue(configuration.getDentalThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
			if(configuration.getMaternityThreshold() != null){
				object.setThresholdValue(configuration.getMaternityThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.OPTICAL){
			if(configuration.getOpticalThreshold() != null){
				object.setThresholdValue(configuration.getOpticalThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		else if (object.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.MEDICAL_CHECK_UP){
			if(configuration.getMedicalCheckUpThreshold() != null){
				object.setThresholdValue(configuration.getMedicalCheckUpThreshold());
			}else{
				object.setThresholdValue(0.0);
			}
		}
		
 		myCaseDao.update (object);
      	return object;
	}


	/*
	* Method delete (Object pkey) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return no return value karena objeknya sendiri sudah dihapus - just for consistency. Again,
	* exception if failure occured
	* WARNING ! Invalid value for the returned object, better not use it again in any
	* place
	*/
	public Case trash (java.io.Serializable pkey) throws Exception {
		Case object = myCaseDao.get (pkey);
		myCaseDao.delete (object);
		return object;
	}

	/*
	* Method delete (Case object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Case delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception{
		Case object = myCaseDao.get (pkey);


		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		
		
		
		object.setDeletedStatus(new Integer(1));

		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		


		
		if (actionUser != null){
			User user = actionUser.getUser();	
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}

		
		myCaseDao.update (object);
		return object;
	}


	/*
	* Method delete (Case object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Case delete (Case object,ActionUser actionUser) throws Exception{
		
		
//		object.setDeletedDate (new java.sql.Date (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		

		if (actionUser != null){
			User user = actionUser.getUser();	
			
		if (user != null){
			object.setDeletedBy(user.getUsername());
		}
		}

		
		myCaseDao.update (object);
		return object;
	}

// -- get section - carefull !


	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public Case get (java.io.Serializable pkey) throws Exception{
		Case object = null;
		object = myCaseDao.get(pkey);
		return object;
	}
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/

	public Case get (java.io.Serializable pkey, String[] required) throws Exception{
	    Case object = null;
	    object = myCaseDao.get(pkey);
		DaoSupportUtil.lazyInit(required,object);
	    return object;
	}
// -- get section end here


// SEARCH SECTION - PALING RUMIT !!
// * -> plain
// *b -> with columnOrder



// -- 1
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	//--req
		public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = myCaseDao.getCriteria();
		List list = null;
		try {
			DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
//			DaoSupportUtil.setOrderBy(asc,columnOrder,c);
//			DaoSupportUtil.setOrderBy(asc,"caseStatusId.orderStatus",c);
//			DaoSupportUtil.setOrderBy(!asc,"createdTime",c);
			System.out.println("columnOrder : "+columnOrder);
			if(columnOrder!=null && columnOrder !=""){
				String columnOrderArr[] = {"caseStatusId.orderStatus",columnOrder};
				DaoSupportUtil.setOrderBy(asc,columnOrderArr,c);
			}else{
				String columnOrderArr[] = {"caseStatusId.orderStatus"};
				DaoSupportUtil.setOrderBy(asc,columnOrderArr,c);
			}
			list = c.list();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Case element = (Case) iter.next();
				DaoSupportUtil.lazyInit(required,element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}
	// --req end

	public Collection searchMultiCase (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, /*String[] inColumns*/  Object[] inParamsStatus, Object[] inParamsCat, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		DetachedCriteria dc = DetachedCriteria.forClass(Case.class);
		dc.setProjection(Property.forName("providerId"));
		Criteria c = myCaseDao.getCriteria();
		
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(inParamsStatus.length > 1)
		DaoSupportUtil.setIn("caseStatusId.caseStatusId",inParamsStatus,c);
		if(inParamsCat.length > 1)
		DaoSupportUtil.setIn("caseCategoryId.caseCategoryId",inParamsCat,c);
		//DaoSupportUtil.setIn(inColumns,inParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
//		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
//		DaoSupportUtil.setOrderBy(asc,"caseStatusId.orderStatus",c);
//		DaoSupportUtil.setOrderBy(!asc,"createdTime",c);
		System.out.println("columnOrder : "+columnOrder);
		if(columnOrder!=null && columnOrder !=""){
			String columnOrderArr[] = {"caseStatusId.orderStatus",columnOrder};
			DaoSupportUtil.setOrderBy(asc,columnOrderArr,c);
		}else{
			String columnOrderArr[] = {"caseStatusId.orderStatus"};
			DaoSupportUtil.setOrderBy(asc,columnOrderArr,c);
		}
		
		
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		
		return list;
	}
	
	public Collection searchMultiCase (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,/*String[] inColumns*/  Object[] inParamsStatus, Object[] inParamsCat, boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(inParamsStatus.length > 1)
		DaoSupportUtil.setIn("caseStatusId.caseStatusId",inParamsStatus,c);
		if(inParamsCat.length > 1)
		DaoSupportUtil.setIn("caseCategoryId.caseCategoryId",inParamsCat,c);
		//DaoSupportUtil.setIn("caseCategoryId.caseCategoryId",inParams,c);
		//DaoSupportUtil.setIn(inColumns,inParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	
	public Collection searchCaseManualRegistation (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamValues, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(inParamValues.length > 1)
			DaoSupportUtil.setIn("manualRegistration",inParamValues,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			 String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			 String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}

	// req end



// -- 2 , between

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}

	// req end



 // -- 2b
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	//req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	
	public int getTotalMultiCase (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat) throws Exception{

			Criteria c = myCaseDao.getCriteria();
			DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			if(inParamsStatus.length > 1)
				DaoSupportUtil.setIn("caseStatusId.caseStatusId",inParamsStatus,c);
			if(inParamsCat.length > 1)
				DaoSupportUtil.setIn("caseCategoryId.caseCategoryId",inParamsCat,c);
			int total = DaoSupportUtil.getTotal(c);
			return total;

	}
	
	public int getTotalCaseRegisterManual (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamValues) throws Exception{

			Criteria c = myCaseDao.getCriteria();
			DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			if(inParamValues.length > 1)
				DaoSupportUtil.setIn("manualRegistration",inParamValues,c);
			int total = DaoSupportUtil.getTotal(c);
			return total;

	}
	
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2
		) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String btwnColumns, Object btwnParams1,
			Object btwnParams2
		) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = myCaseDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = myCaseDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = myCaseDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public Case searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Case obj = (Case) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public Case searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Case obj = (Case) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = myCaseDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = myCaseDao.getDetachedCriteria();
		return dc;
	}
	public boolean approve(Serializable object, ActionUser actionUser) throws Exception {
		boolean result = true;
		String[] required = {"Case.CaseStatusId"};
 		Case caseBean = get (object);
		
		if (caseBean != null){
			
			
			CaseStatus previousStatus = caseBean.getCaseStatusId();
			String previousData = caseBean.toHistoryString();
			CaseStatus status = caseBean.getCaseStatusId();
			
			//modified by aju on 20150824/27, for preAdmission
			if (status != null && (status.getCaseStatusId().intValue() == Case.CASE_OPEN 
					|| status.getCaseStatusId().intValue() == Case.CASE_PENDING_DOCUMENT
					|| status.getCaseStatusId().intValue() == Case.CASE_PENDING ||
					status.getCaseStatusId().intValue() == Case.CASE_PENDING_INVESTIGATION ||
					status.getCaseStatusId().intValue() == Case.CASE_PRE_AUTH ||
					status.getCaseStatusId().intValue() == Case.CASE_PRE_PENDING)){
				
				status = new CaseStatus();
				//modified by aju on 20150824/27, for preAdmission :D
				//status.setCaseStatusId(Case.CASE_APPROVED);
				if(previousStatus.getCaseStatusId().intValue() == Case.CASE_PRE_AUTH || previousStatus.getCaseStatusId().intValue() == Case.CASE_PRE_PENDING){
					status.setCaseStatusId(Case.CASE_PRE_OPEN);
				}
				else{
					status.setCaseStatusId(Case.CASE_APPROVED);
				}
				
				caseBean.setCaseStatusId(status);
				
				CaseCategory cc = caseBean.getCaseCategoryId();
				
				if (cc.getCaseCategoryId().intValue() == CaseCategory.INPATIENT 
						|| cc.getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
					
					caseBean.setIsGLIssued(1);
				}
			}
			else {
				result = false;
			}
			caseBean.setIsGLIssued(1);
			
			Claim claim = null;
			
			if (caseBean.getClaimId() != null){
				
				claim = claimService.get(caseBean.getClaimId().getClaimId());
				
				if (claim.getDiagnosisId() != null){
					Diagnosis diagnosis = diagnosisService.get(claim.getDiagnosisId().getDiagnosisId());
					if (diagnosis != null){
						caseBean.setDiagnosis1Id(diagnosis);
						caseBean.setDiagnosis1Code(diagnosis.getDiagnosisCode());
						caseBean.setDiagnosis1Name(diagnosis.getDiagnosisName());
					}
					
				}
				if (claim.getDiagnosis2Id() != null){
					Diagnosis diagnosis = diagnosisService.get(claim.getDiagnosis2Id().getDiagnosisId());
					if (diagnosis != null){
						caseBean.setDiagnosis2Id(diagnosis);
						caseBean.setDiagnosis2Code(diagnosis.getDiagnosisCode());
						caseBean.setDiagnosis2Name(diagnosis.getDiagnosisName());
					}
				}
				if (claim.getDiagnosis3Id() != null){
					Diagnosis diagnosis = diagnosisService.get(claim.getDiagnosis3Id().getDiagnosisId());
					if (diagnosis != null){
						caseBean.setDiagnosis3Id(diagnosis);
						caseBean.setDiagnosis3Code(diagnosis.getDiagnosisCode());
						caseBean.setDiagnosis3Name(diagnosis.getDiagnosisName());
					}
				}
			}
			update (caseBean, actionUser);
			
			CaseStatus currentStatus = caseBean.getCaseStatusId();
			String currentData = caseBean.toHistoryString();
			
			String actionBy = actionUser.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("APPROVE CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(caseBean);
			
			
			if (caseBean.getDiagnosis1Name() != null){
				history.setDiagnosisCode(caseBean.getDiagnosis1Name());				
			}
			if (caseBean.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(caseBean.getDiagnosis2Name());
			}
			if (caseBean.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(caseBean.getDiagnosis3Name());
			}
			if (caseBean.getProviderName() != null){
				history.setProviderName(caseBean.getProviderName());
			}
			if (caseBean.getCaseNumber() != null){
				history.setCaseNumber(caseBean.getCaseNumber());
			}
			if (caseBean.getMemberName() != null){
				history.setMemberName(caseBean.getMemberName());
			}
			if (caseBean.getMemberNumber() != null){
				history.setMemberNumber(caseBean.getMemberNumber());
			}
			if (caseBean.getCaseCategoryId() != null){
				history.setCaseCategoryName(caseBean.getCaseCategoryId().getCaseCategoryName());
			}
			if (claim != null){
				history.setDescription(claim.getDescription());
				
			}
			
			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(caseBean.getCaseId());
			
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				
				Seconds seconds = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()) , 
						new DateTime(System.currentTimeMillis()));
				
				int totalSecond = seconds.getSeconds();
				
				history.setDuration(totalSecond);
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(totalSecond);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			
			
			caseHistoryService.create(history, actionUser);
		}
		
		return result;
	}
	public boolean reject(Serializable object, String rejectNote, ActionUser actionUser) throws Exception {
		
		boolean result = true;
		
		Case caseBean = get (object);
		
		if (caseBean != null){
			caseBean.setIsGLIssued(0);
			CaseStatus status = new CaseStatus();
			CaseStatus previousStatus = caseBean.getCaseStatusId();
			
			if (status != null){
				System.out.println("caseServiceImpl::reject->previousStatus : " + previousStatus.getCaseStatusId());
				//modified by aju on 20150827, for PRE AUTH n PRE PENDING...perasaan kemaren dah bener jadi PRE REJECT zzzz... asem :PPP
				//status.setCaseStatusId(Integer.valueOf(Case.CASE_REJECT));
				
				if(previousStatus.getCaseStatusId() == Case.CASE_PRE_AUTH || previousStatus.getCaseStatusId() == Case.CASE_PRE_PENDING){
					status.setCaseStatusId(Integer.valueOf(Case.CASE_PRE_REJECT));
				}
				else{
					status.setCaseStatusId(Integer.valueOf(Case.CASE_REJECT));
				}
				System.out.println("caseServiceImpl::reject->newStatus : " + status.getCaseStatusId());
				caseBean.setCaseStatusId(status);
				
				
				String previousData = caseBean.toHistoryString();
				
				update(caseBean,actionUser);
				// cost containment nih harusnya
				
				CaseStatus currentStatus = caseBean.getCaseStatusId();
				String currentData = caseBean.toHistoryString();
				
				String actionBy = actionUser.getUser().getUsername();
				CaseHistory history = new CaseHistory();
				history.setActionBy(actionBy);
				history.setCreatedBy(actionBy);
				history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				history.setBeforeStatus(previousStatus);
				history.setAfterStatus(currentStatus);
				history.setActionType("REJECT CASE");
				history.setBeforeActionData(previousData);
				history.setAfterActionData(currentData);
				history.setCaseId(caseBean);
				history.setDescription(rejectNote);
				
				
				if (caseBean.getDiagnosis1Name() != null){
					history.setDiagnosisCode(caseBean.getDiagnosis1Name());				
				}
				if (caseBean.getDiagnosis2Name() != null){
					history.setDiagnosis2Code(caseBean.getDiagnosis2Name());
				}
				if (caseBean.getDiagnosis3Name() != null){
					history.setDiagnosis3Code(caseBean.getDiagnosis3Name());
				}
				if (caseBean.getProviderName() != null){
					history.setProviderName(caseBean.getProviderName());
				}
				if (caseBean.getCaseNumber() != null){
					history.setCaseNumber(caseBean.getCaseNumber());
				}
				if (caseBean.getMemberName() != null){
					history.setMemberName(caseBean.getMemberName());
				}
				if (caseBean.getMemberNumber() != null){
					history.setMemberNumber(caseBean.getMemberNumber());
				}
				if (caseBean.getCaseCategoryId() != null){
					history.setCaseCategoryName(caseBean.getCaseCategoryId().getCaseCategoryName());
				}
				
				
				CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(caseBean.getCaseId());
				
				if (previousHistory != null && previousHistory.getHistoryTime() != null){
					
					Seconds seconds = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()) , 
							new DateTime(System.currentTimeMillis()));
					
					int totalSecond = seconds.getSeconds();
					
					history.setDuration(totalSecond);
					LocalTime local = new LocalTime(0,0);
					local = local.plusSeconds(totalSecond);
					String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
					
					history.setDurationString(output);
				}
				
				
				caseHistoryService.create(history, actionUser);
				
			}
			else {
				result = false;
			}
		}
		return result;
	}
	public boolean closeAndRefer (Case theCase, ActionUser user) throws Exception {
		boolean result = false;
		
		if (theCase != null){
			update(theCase,user);
			
			Collection<CaseMedicine> medicineList = null;
		}
		return result;
	}
	public boolean close(Serializable pkey, Timestamp closingTime, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		Case theCase = get(pkey);
		
		if (theCase != null){
			
			CaseStatus previousStatus = theCase.getCaseStatusId();
			String previousData = theCase.toHistoryString();
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Case.CASE_CLOSED);
			
			theCase.setCaseStatusId(status);
			
			
			update(theCase,actionUser);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = actionUser.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("CLOSE CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			caseHistoryService.create(history, actionUser);
			
			result = true;
		}
		
		return result;
	}

	public boolean closeManagedCare(Serializable pkey, Timestamp closingTime, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		Case theCase = get(pkey);
		
		if (theCase != null){
			
			CaseStatus previousStatus = theCase.getCaseStatusId();
			String previousData = theCase.toHistoryString();
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Case.CASE_CLAIM_PROCESSED);			
			theCase.setCaseStatusId(status);		
			
			update(theCase,actionUser);
			
			Claim claim = new Claim();
			claim.setAdmissionDate(theCase.getCaseEndTime());
			claim.setCaseCategoryId(theCase.getCaseCategoryId());
			
			Claim theClaim = claimService.create(claim, actionUser);
			
			
			if (theClaim != null){
		
				
			}
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = actionUser.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("CLOSE CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			caseHistoryService.create(history, actionUser);
			result = true;
		}
		
		return result;
	}
	public boolean renew(Serializable pkey, Case memberCase,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean transfer(Serializable pkey, Case transferedCase,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getTotalClosingCase() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = myCaseDao.getCaseSession();
			
			if (session != null){
				//String query = "SELECT count(c) FROM Case c WHERE c.caseStatusId.caseStatusId = :status  " +
				//		"AND c.deletedStatus = 0";
				
				String sqlQ = "SELECT count(case_id) FROM tb_case WHERE case_status_id = :status AND deleted_status = 0";
				
				SQLQuery q = session.createSQLQuery(sqlQ);
				//org.hibernate.Query q = session.createQuery(query);
				q.setInteger("status",Case.CASE_CLOSED);			
				
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalExpireCase() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = myCaseDao.getCaseSession();
			
			if (session != null){
				//String query = "SELECT count(c) FROM Case c WHERE c.caseStatusId.caseStatusId " +
				//		"= :status AND c.caseEndTime <= :due AND c.deletedStatus = 0";
				
				String sqlQ = "SELECT count(case_id) FROM tb_case WHERE case_status_id = :status AND case_end_time <= :due AND deleted_status = 0";
				
				SQLQuery q = session.createSQLQuery(sqlQ);
				//org.hibernate.Query q = session.createQuery(query);
				q.setInteger("status",Case.CASE_APPROVED);
				q.setDate("due", new java.sql.Date(System.currentTimeMillis()));
				
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public int getTotalMonitorCase() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = myCaseDao.getCaseSession();
			
			if (session != null){
				
				String sqlQ = "SELECT count(case_id) FROM tb_case WHERE case_status_id = :status AND case_end_time > :due AND deleted_status = 0";
				
				
	//			String query = "SELECT count(c) FROM Case c WHERE c.caseStatusId.caseStatusId = :status AND " +
	//					"c.caseEndTime > :due AND c.deletedStatus = 0";
				
				
				SQLQuery q = session.createSQLQuery(sqlQ);
				//org.hibernate.Query q = session.createQuery(query);
				q.setInteger("status",Case.CASE_APPROVED);
				q.setDate("due", new java.sql.Date(System.currentTimeMillis()));
				
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalNewCase() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = myCaseDao.getCaseSession();
			
			if (session != null){
				
				//String query = "SELECT count(c) FROM Case c WHERE c.caseStatusId.caseStatusId = :status " +
				//		"AND c.caseEndTime > :due AND c.deletedStatus = 0";
				
				
				String sqlQ = "SELECT count(case_id) FROM tb_case WHERE case_status_id = :status AND case_end_time > :due AND deleted_status = 0";
				
				
				//String query = "SELECT count(c) FROM Case c WHERE c.caseStatusId.caseStatusId = :status AND " +
				//		"c.caseEndTime > :due AND c.deletedStatus = 0";
				
				
				SQLQuery q = session.createSQLQuery(sqlQ);
				
			//	org.hibernate.Query q = session.createQuery(query);
				q.setInteger("status",Case.CASE_OPEN);			
				q.setDate("due", new java.sql.Date(System.currentTimeMillis()));
				
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalCaseInvestigation() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = myCaseDao.getCaseSession();
			
			if (session != null){
				
				String sqlQ = "SELECT COUNT(DISTINCT c.case_id) " +
						"FROM tb_case c " +
						"INNER JOIN case_investigation ci ON c.case_id = ci.case_id " +
						"WHERE c.deleted_status = 0 AND ci.deleted_status = 0";
				
				
				SQLQuery q = session.createSQLQuery(sqlQ);
				
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTotalNewCOBCase() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = myCaseDao.getCaseSession();
			
			if (session != null){
				//System.out.println("CaseServiceImpl::getTotalNewCOBCase()");
				/*
				String sqlQ = "SELECT count(tbc.case_id) " +
						        "FROM tb_case tbc " +
						       "WHERE tbc.case_status_id = :status " +
						         "AND tbc.case_end_time > :due " +
						         "AND tbc.deleted_status = 0 " +
						         "AND (SELECT TRIM(linked_card_number) FROM member WHERE member_id = tbc.member_id) <> ''";
				*/
				
				String sqlQ = "SELECT count(tbc.case_id) " +
				        "FROM tb_case tbc " +
				       "WHERE tbc.case_status_id = :status " +
				         /*"AND tbc.case_end_time > :due "*/ "AND tbc.case_start_time <= :due " +
				         "AND tbc.deleted_status = 0 " +
				         "AND tbc.is_linked_member = 1 ";
				
				SQLQuery q = session.createSQLQuery(sqlQ);
				
			//	org.hibernate.Query q = session.createQuery(query);
				q.setInteger("status",Case.CASE_OPEN);			
				q.setDate("due", new java.sql.Date(System.currentTimeMillis()));
				//System.out.println("query : " + q.getQueryString());
				
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public Case createCaseItem(Integer caseId, Collection claimItem,
			Double pembulatan, ActionUser actionUser) throws Exception {
		
		Case object = get(caseId);

        if (object != null) {
            double claimCharge = 0;
            double totalExcess = 0;
			double totalApproved = 0;
            int i = 1;

            if (claimItem != null) {
                Iterator iterator = claimItem.iterator();
                while (iterator.hasNext()) {

                    CaseItem ci = (CaseItem) iterator.next();
                    ClaimItem claimItemAdd = null;
                    if (ci != null){
                    	CaseItem theCaseItem = caseItemService.getCaseItem(caseId, ci.getItemId().getItemId());
                    	
                    	if(ci.getCaseId().getClaimId()!=null){
	                    	//String[] eqParam = {"claimId.claimId","itemId.itemId", "claimId.caseId.caseId","deletedStatus", "claimId.caseCategoryId.caseCategoryId"};
	        				//Object[] eqValue = {ci.getCaseId().getClaimId().getClaimId(), ci.getItemId().getItemId(), ci.getCaseId().getCaseId(), 
	        				//		Integer.valueOf(0), ci.getCaseId().getCaseCategoryId().getCaseCategoryId()};
	                        //claimItemAdd = claimItemService.searchUnique(eqParam, eqValue);
                    		claimItemAdd = claimItemService.getClaimItem(ci.getCaseId().getClaimId().getClaimId(), ci.getItemId().getItemId());
                    	}else{
                    		String[] eqParam = {"itemId.itemId", "claimId.caseId.caseId","deletedStatus", "claimId.caseCategoryId.caseCategoryId"};
            				Object[] eqValue = {ci.getItemId().getItemId(), ci.getCaseId().getCaseId(), Integer.valueOf(0), ci.getCaseId().getCaseCategoryId().getCaseCategoryId()};
                            claimItemAdd = claimItemService.searchUnique(eqParam, eqValue);
                    	}
                    	if (theCaseItem != null){
                    		if (ci.getUsageAmount() != null && ci.getUsageAmount().doubleValue() > 0.0){
                    		
	                    		theCaseItem.setUsageAmount(ci.getUsageAmount());
	                    		theCaseItem.setUsageValue(ci.getUsageValue());
	                    		theCaseItem.setUsageTime(ci.getUsageTime());
	                    		theCaseItem.setDescription(ci.getDescription());
	                    		
	                    		caseItemService.update(theCaseItem, actionUser);
	                    		claimCharge += ci.getUsageValue();
	                    		if(claimItemAdd!=null){
	                    			if(claimItemAdd.getClaimId().getClaimStatus().getCaseStatusId()==Claim.CLAIM_PRE_OPEN){
										claimItemAdd.setClaimItemAmount(Double.valueOf(ci.getUsageAmount()));
										claimItemAdd.setClaimItemValue(Double.valueOf(ci.getUsageValue()));
										claimItemAdd.setDeletedStatus(0);
									//claimItemAdd.setClaimItemDescription(ci.getDescription());
		
//									if (!claimItemIdRef[i].equalsIgnoreCase("")){
//										claimItem.setClaimItemId(Integer.valueOf(claimItemIdRef[i]));
//									}
//									
//									if (!prePaidExcess[i].equalsIgnoreCase("")){
//										claimItem.setPrePaidExcess(Double.valueOf(prePaidExcess[i]));
//									}
										claimItemService.update(claimItemAdd, actionUser);
	                    			}
	                    		}else{
	                    			if(ci.getCaseId().getClaimId().getClaimStatus().getCaseStatusId()==Claim.CLAIM_PRE_OPEN){
		                    			claimItemAdd = new ClaimItem();
										claimItemAdd.setClaimId(ci.getCaseId().getClaimId());
				                    	claimItemAdd.setItemId(ci.getItemId());
				                    	CaseStatus status = new CaseStatus();
										status.setCaseStatusId(CaseStatus.CASE_PRE_OPEN);
				                    	claimItemAdd.setClaimItemStatus(status);
				                    	claimItemAdd.setClaimItemAmount(Double.valueOf(ci.getUsageAmount()));
				                    	claimItemAdd.setClaimItemValue(Double.valueOf(ci.getUsageValue()));
				                    	claimItemAdd.setClaimItemDescription(ci.getDescription());
				                    	claimItemAdd.setDeletedStatus(Integer.valueOf(0));
										claimItemAdd.setDeletedStatus(0);
		                    			claimItemService.create(claimItemAdd, actionUser);
	                    			}
	                    		}
                    		}
                    		else {
                    			caseItemService.delete(theCaseItem, actionUser);
                    			if(ci.getCaseId().getClaimId().getClaimStatus().getCaseStatusId()==Claim.CLAIM_PRE_OPEN){
                    				claimItemService.delete(claimItemAdd, actionUser);
                    			}
                    		}
                    	}
                    	else {
                    		if (ci.getUsageAmount() != null && ci.getUsageAmount().doubleValue() > 0.0){
			                    claimCharge += ci.getUsageValue();
			                    caseItemService.create(ci, actionUser);
			                    
			                    Claim parentClaim = null;
			                    
			                    if(ci.getCaseId().getClaimId()!=null){
			                    	java.io.Serializable pkey = ci.getCaseId().getClaimId().getClaimId();
			        				parentClaim = claimService.get(pkey);
			        				System.out.println("PARENT CLAIM : "+parentClaim.getClaimId());
			                    }else{
			                    	String[] eqClaimParam = {"caseId.caseId","deletedStatus", "caseCategoryId.caseCategoryId"};
			        				Object[] eqClaimValue = {ci.getCaseId().getCaseId(), Integer.valueOf(0), ci.getCaseId().getCaseCategoryId().getCaseCategoryId()};
			        				parentClaim = claimService.searchUnique(eqClaimParam, eqClaimValue, 0, 1);
			                    }
			                    claimItemAdd = new ClaimItem();
			                    if(parentClaim!=null){
			                    	if(parentClaim.getClaimStatus().getCaseStatusId()==Claim.CLAIM_PRE_OPEN){
				                    	 claimItemAdd.setClaimId(parentClaim);
				                    	 claimItemAdd.setItemId(ci.getItemId());
				                    	 CaseStatus status = new CaseStatus();
										 status.setCaseStatusId(CaseStatus.CASE_PRE_OPEN);
				                    	 claimItemAdd.setClaimItemStatus(status);
				                    	 claimItemAdd.setClaimItemAmount(Double.valueOf(ci.getUsageAmount()));
				                    	 claimItemAdd.setClaimItemValue(Double.valueOf(ci.getUsageValue()));
				                    	 claimItemAdd.setClaimItemDescription(ci.getDescription());
				                    	 claimItemAdd.setDeletedStatus(Integer.valueOf(0));
				                    	 claimItemService.create(claimItemAdd, actionUser);
			                    	}
			                    }
			                   
                    		}
                    	}
                    }
                    
                }
                //Add 20150806 by FVO, untuk save Excess, Approved di Case
                /*Collection<ClaimItem> claimItemList;
				String[] required = {"Claim.MemberId", "Claim.DiagnosisId"};
				Claim claim = claimService.get(object.getClaimId().getClaimId(),required);
				claimItemList = claimItemService.getBenefitCheckCalculationForLetter(claim);
				for(ClaimItem ci : claimItemList){
					if(ci.getExcessValue()!=null){
						totalExcess = totalExcess + ci.getExcessValue();
					}
					if(ci.getClaimItemApprovedValue() != null){
						//totalApproved = totalApproved + ci.getClaimItemApprovedValue();
						totalApproved = totalApproved + ci.getClaimItemCoveredValue();
					}
				}
				*/
            }
            
            object.setCaseClaimValue(claimCharge);
            //object.setCaseApprovedValue(totalApproved);
            //object.setCaseExcessValue(totalExcess);
            update(object, actionUser);
        }
        return object;
	}
	
	@Override
	public boolean closeCase(Case theCase, ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			
			CaseStatus previousStatus = theCase.getCaseStatusId();
			String previousData = theCase.toHistoryString();
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(CaseStatus.CASE_CLOSED);
			
			theCase.setCaseStatusId(status);
			
			theCase.setClosingTime(new java.sql.Timestamp(System.currentTimeMillis()));
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			
			history.setCaseId(theCase);
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setActionType("CLOSE CASE");
			
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			caseHistoryService.create(history, user);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean reversalCase(Integer caseId, String note, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		if (caseId != null){
			Case theCase = get(caseId);
			theCase.setClaimId(null);
			
			
			CaseStatus previousStatus = theCase.getCaseStatusId();
			String previousData = theCase.toHistoryString();
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(CaseStatus.CASE_OPEN);
			
			theCase.setCaseStatusId(status);
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("VOID CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("VOID CASE " + theCase.getCaseNumber() + " REMARKS = " + note);
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("REVERSAL CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}
			

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			if (note != null){
				history.setDescription(note);
			}
			caseHistoryService.create(history, user);
			
			result = true;
		}
		return result;
	}
	public boolean voidReferCase(Integer caseId, String note, ActionUser user)
	throws Exception {
// TODO Auto-generated method stub
		
		boolean result = false;
		
		if (caseId != null){
			
			Case theCase = get(caseId);
			
			CaseStatus previousStatus = theCase.getCaseStatusId();
			String previousData = theCase.toHistoryString();
			
			theCase.setClaimId(null);
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(CaseStatus.CASE_FINALIZED);
			
			theCase.setCaseStatusId(status);
			theCase.setReferalStatus(0);
			theCase.setReferedPoliklinikId(null);
			
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("VOID REFER CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("VOID REFER CASE " + theCase.getCaseNumber() + " REMARKS = " + note);
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("VOID REFER CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			history.setDescription(note);
			caseHistoryService.create(history, user);
			
			result = true;
		}
		return result;
		}
	@Override
	public boolean referCase(Case theCase, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		if (theCase != null){
			theCase.setClaimId(null);
			
			String previousData = theCase.toHistoryString();
			CaseStatus previousStatus = theCase.getCaseStatusId();
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(CaseStatus.CASE_REFERED);
			
			theCase.setCaseStatusId(status);
			theCase.setReferalStatus(1);
			

			String refNumber = RandomStringUtils.randomNumeric(10);
			theCase.setCaseReferalNumber(refNumber);
			
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("REFER CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("REFER CASE " + theCase.getCaseNumber() );
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("REFER CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			caseHistoryService.create(history, user);
			
			result = true;
		}
		return result;
	}
	public boolean approveExGratia (Serializable object,String approvalNote,byte[] exGratiaDocument,String fileName,ActionUser user) throws Exception {
		boolean result = false;
		
		Case theCase = get(object);
		
		if (theCase != null){
			theCase.setClaimId(null);
			
			CaseStatus previousStatus = theCase.getCaseStatusId();
			String previousData = theCase.toHistoryString();
			CaseStatus status = new CaseStatus();
			//modified by aju on 20150824, for preAdmission :D
			status.setCaseStatusId(CaseStatus.CASE_APPROVED);
			if(theCase.getCaseStatusId().getCaseStatusId() == Case.CASE_PRE_AUTH){
				status.setCaseStatusId(CaseStatus.CASE_PRE_OPEN);
			}
			else{
				status.setCaseStatusId(CaseStatus.CASE_APPROVED);
			}

			CaseCategory cc = theCase.getCaseCategoryId();
			
			if (cc.getCaseCategoryId().intValue() == CaseCategory.INPATIENT || cc.getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
				theCase.setIsGLIssued(1);
			}
			
			theCase.setCaseStatusId(status);
			theCase.setIsExGratia(1);
			theCase.setExGratiaNotes(approvalNote);
			theCase.setIsGLIssued(1);
			theCase.setExGratiaDocumentURL(fileName);
			
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("APPROVE EX GRATIA CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("APPROVE EX GRATIA CASE " + theCase.getCaseNumber() );
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("APPROVE EX GRATIA");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			
			caseHistoryService.create(history, user);
			
			result = true;
		}
		return result;
	}
	@Override
	public boolean assignCase(Integer caseId, Integer userId, String note,
			ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		Case theCase = get(caseId);
		
		if (theCase != null){
			
			String previousData = theCase.toHistoryString();
			CaseStatus previousStatus = theCase.getCaseStatusId();
			
			User assignee = userService.get(userId);
			
			if (assignee != null){
				theCase.setAssigneeId(assignee);
				theCase.setAssignmentNote(note);
			}
			
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("ASSIGN CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("ASSIGN CASE " + theCase.getCaseNumber() + " TO = " + assignee.getUsername() + " ( " +assignee.getFirstName()+ " )");
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("ASSIGN CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			history.setDescription(note);
			caseHistoryService.create(history, user);
			
			result = true;
		}
			
		return result;
	}
	@Override
	public boolean pendingCase(Integer caseId, Integer pendingStatus,Integer assigneeId, String note, ActionUser user)
			throws Exception {
		boolean result = false;
		
		Case theCase = get(caseId);
		
		if (theCase != null){
			
			String previousData = theCase.toHistoryString();
			CaseStatus previousStatus = theCase.getCaseStatusId();
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(pendingStatus);
			
			theCase.setCaseStatusId(status);
			
			if (assigneeId != null){
				User assignee = userService.get(assigneeId);
				
				if (assignee != null){
					theCase.setAssigneeId(assignee);
				
				}
				
			}
			theCase.setAssignmentNote(note);
			
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("PENDING CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("PENDING CASE -> CASE NUMBER = " + theCase.getCaseNumber());
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("PENDING CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}
			

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			history.setDescription(note);
			
			caseHistoryService.create(history, user);
			
			result = true;
		}
			
		return result;
	}
	@Override
	public boolean voidCase(Integer caseId, String note, ActionUser user)
			throws Exception {

		boolean result = false;
		
		if (caseId != null){
			Case theCase = get(caseId);			
			
			String previousData = theCase.toHistoryString();
			CaseStatus previousStatus = theCase.getCaseStatusId();
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(CaseStatus.VOID);
			
			theCase.setCaseStatusId(status);
			theCase.setDescription(note);
			
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("VOID  CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("VOID REFER CASE " + theCase.getCaseNumber() + " REMARKS = " + note);
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("VOID CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			
			history.setDescription(note);
			caseHistoryService.create(history, user);
			
			result = true;
		}
		return result;

	}
	
	public Collection<Case> searchCase ( String[] eqCase,  Object[] oCase, String[] sStatus,  Object[] oStatus, 
			String[] required) throws Exception {
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(eqCase, oCase, c);
		DaoSupportUtil.setEqParam(sStatus, oStatus, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	
	public Collection<Case> getCaseList (String[] sStatus,  Object[] oStatus, 
			String[] required) throws Exception {
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setEqParam(sStatus, oStatus, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	@Override
	public boolean cancelDischargeCase(Integer caseId, String note,
			ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;

		if (caseId != null){
			Case theCase = get(caseId);			
			
			String previousData = theCase.toHistoryString();
			CaseStatus previousStatus = theCase.getCaseStatusId();
			
			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(Claim.CLAIM_PENDING_INVESTIGATION);
			
					
			theCase.setCaseStatusId(status);
			theCase.setDescription(note);
			
			
			String username = "";
			if (user != null){
				username = user.getUser().getUsername();
				theCase.setModifiedBy(user.getUser().getUsername());
				theCase.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			}
			
			if (theCase.getClaimId() != null){
				claimService.openClaim(theCase.getClaimId().getClaimId(), user);
				
				Claim claim = claimService.get(theCase.getClaimId().getClaimId());
				
				if (claim != null){
					CaseStatus claimStatus = new CaseStatus();
					claimStatus.setCaseStatusId(Claim.CLAIM_PRE_OPEN);
					claim.setClaimStatus(claimStatus);
					
					claimService.update(claim, user);
			
					ActivityLog theLog = new ActivityLog();
					theLog.setAction("CHANGE CLAIM STATUS TO PRE_OPEN");
					theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
					theLog.setUsername(username);
					theLog.setIpAddress(user.getIpAddress());
					theLog.setSessionId(user.getLoginSession());
					theLog.setDescription("CHANGE CLAIM STATUS TO PRE_OPEN " + claim.getClaimNumber() + " REMARKS = " + note);
					theLog.setClaimId(claim);
					
					activityLogService.create(theLog);
					
				}
			}
			
			System.out.println("LALALALALLAA");
			
			ActivityLog theLog = new ActivityLog();
			theLog.setAction("CANCEL DISCHARGE  CASE");
			theLog.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			theLog.setUsername(username);
			theLog.setIpAddress(user.getIpAddress());
			theLog.setSessionId(user.getLoginSession());
			theLog.setDescription("CANCEL DISCHARGE CASE " + theCase.getCaseNumber() + " REMARKS = " + note);
			theLog.setCaseId(theCase);
			
			
			activityLogService.create(theLog);
			
			myCaseDao.update(theCase);
			
			CaseStatus currentStatus = theCase.getCaseStatusId();
			String currentData = theCase.toHistoryString();
			
			String actionBy = user.getUser().getUsername();
			CaseHistory history = new CaseHistory();
			history.setActionBy(actionBy);
			history.setCreatedBy(actionBy);
			history.setHistoryTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			history.setBeforeStatus(previousStatus);
			history.setAfterStatus(currentStatus);
			history.setActionType("CANCEL DISCHARGE CASE");
			history.setBeforeActionData(previousData);
			history.setAfterActionData(currentData);
			history.setDescription(note);
			
			history.setCaseId(theCase);
			if (theCase.getDiagnosis1Name() != null){
				history.setDiagnosisCode(theCase.getDiagnosis1Name());
				
			}
			if (theCase.getDiagnosis2Name() != null){
				history.setDiagnosis2Code(theCase.getDiagnosis2Name());
			}
			if (theCase.getDiagnosis3Name() != null){
				history.setDiagnosis3Code(theCase.getDiagnosis3Name());
			}
			if (theCase.getProviderName() != null){
				history.setProviderName(theCase.getProviderName());
			}
			if (theCase.getCaseNumber() != null){
				history.setCaseNumber(theCase.getCaseNumber());
			}
			if (theCase.getMemberName() != null){
				history.setMemberName(theCase.getMemberName());
			}
			if (theCase.getMemberNumber() != null){
				history.setMemberNumber(theCase.getMemberNumber());
			}
			if (theCase.getCaseCategoryId() != null){
				history.setCaseCategoryName(theCase.getCaseCategoryId().getCaseCategoryName());
			}
			

			CaseHistory previousHistory = caseHistoryService.getLatestCaseHistory(theCase.getCaseId());
			if (previousHistory != null && previousHistory.getHistoryTime() != null){
				Seconds second = Seconds.secondsBetween(new DateTime(previousHistory.getHistoryTime().getTime()), new DateTime(System.currentTimeMillis()));
				
				int duration = second.getSeconds();
				history.setDuration(duration);
				
				LocalTime local = new LocalTime(0,0);
				local = local.plusSeconds(duration);
				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
				
				history.setDurationString(output);
			}
			history.setDescription(note);
			caseHistoryService.create(history, user);
			
			result = true;
		}

		
		return result;
	}
	@Override
	public Integer updateActiveCaseLoS() throws Exception {
		// TODO Auto-generated method stub
		Integer result = 0;
		String[] eqParam = {"deletedStatus","caseStatusId.caseStatusId"};
		Object[] eqValue = {Integer.valueOf(0),CaseStatus.CASE_APPROVED};
		
		int total = getTotal(null,null,eqParam,eqValue);
		Collection<Case> activeCaseList = search(null,null,eqParam,eqValue,0,total);
		
		if (activeCaseList != null){
			for (Iterator iterator = activeCaseList.iterator(); iterator
					.hasNext();) {
				
				
				Case caseD = (Case) iterator.next();
				
				if (caseD != null){
					DateTime start = new DateTime(caseD.getCaseStartTime());
					DateTime now = new DateTime(new java.sql.Date(System.currentTimeMillis()));
					int totalDay = Days.daysBetween(start,now).getDays() + 1 ;
					
					caseD.setLongOfStay(totalDay);
					
					
					myCaseDao.update(caseD);
					
					result+=1;
				}
				
			}
		}
		
		return result;
	}
	
	public Collection searchCaseInvestigation (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,  Object[] inParamsValue,boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = myCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(inParamsValue.length >= 1)
			DaoSupportUtil.setIn("caseId", inParamsValue,c);
		//DaoSupportUtil.setIn(inColumns,inParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
//		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
//		DaoSupportUtil.setOrderBy(asc,"caseStatusId.orderStatus",c);
//		DaoSupportUtil.setOrderBy(!asc,"createdTime",c);
		System.out.println("columnOrder : "+columnOrder);
		if(columnOrder!=null && columnOrder !=""){
			String columnOrderArr[] = {"caseStatusId.orderStatus",columnOrder};
			DaoSupportUtil.setOrderBy(asc,columnOrderArr,c);
		}else{
			String columnOrderArr[] = {"caseStatusId.orderStatus"};
			DaoSupportUtil.setOrderBy(asc,columnOrderArr,c);
		}
		
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Case element = (Case) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		
		return list;
	}
	
	public int getTotalCaseInvestigation (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsValue) throws Exception{

			Criteria c = myCaseDao.getCriteria();
			DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			if(inParamsValue.length >= 1)
				DaoSupportUtil.setIn("caseId",inParamsValue,c);
			int total = DaoSupportUtil.getTotal(c);
			return total;

	}
	
	

	public Collection<Object[]> searchCaseReference (String clientName, String groupName,
			String serviceName, String providerName, String diagnosisName, Integer state, Integer province,
			Integer city, Date minimumDate, Date maxDate,String dateBy , int index, int offset) throws Exception{
//		Criteria c = myCaseDao.getCriteria();
//		c.createAlias("claimId", "claimId");
//		c.createAlias("diagnosis1Id", "diagnosis1Id");
//		c.setProjection(Projections.projectionList().add(Property.forName("claimId.claimChargeValue").max().as("max").isGrouped()));
//				.add(Property.forName("claimId.claimChargeValue").avg().as("avg"))
//				.add(Property.forName("claimId.claimChargeValue").min(), "min")
//				.add(Property.forName("diagnosis1Id.description").group())
//				.add(Property.forName(columnOrder).group()));
//		
//		String[] setAvg = {"claimId.claimChargeValue"};
//		String[] setAlias = {"avgClaimCharge"};
//		DaoSupportUtil.setAvarage(setAvg, setAlias, c);
//		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
//		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
//		DaoSupportUtil.setLimit(index, offset, c);
//		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
//		String[] setGroupby = {columnOrder};
//		DaoSupportUtil.setGroupBy(groupBy, c);
//		c.setProjection(Projections.projectionList().add(Projections.property(columnOrder)));
//		List list = c.list();
//		for (Iterator iter = list.iterator(); iter.hasNext();) {
//			Case element = (Case) iter.next();
//   			DaoSupportUtil.lazyInit(required,element);
//		}
		
		Collection<Object[]> result = new Vector<Object[]>();
		 Session session = myCaseDao.getCaseSession();
		 
		 	StringBuffer sb = new StringBuffer();;
		 	sb.append("SELECT c.claimId.diagnosisId.description , floor(avg(c.claimId.claimChargeValue)) as avaragecost,  floor(max(c.claimId.claimChargeValue)) as maxcost," 
		 			 + " floor(min(c.claimId.claimChargeValue)) as mincost, floor(avg(c.longOfStay)) as avaragelos "
		 			 +" FROM Case c LEFT JOIN c.claimId LEFT JOIN c.claimId.diagnosisId LEFT JOIN c.claimId.memberId.memberGroupId LEFT JOIN c.claimId.memberId.memberGroupId.clientId " +
		 			 " LEFT JOIN c.claimId.caseCategoryId LEFT JOIN c.claimId.providerId "
	                 + "WHERE c.deletedStatus = 0 " );
	        
	        if(clientName != null  && !clientName.equalsIgnoreCase(""))
	        	sb.append(" AND lower(c.claimId.memberId.memberGroupId.clientId.clientName) like  lower(:clientName) " );
	        
	        if(serviceName != null  && !serviceName.equalsIgnoreCase(""))
	        	sb.append("AND c.claimId.caseCategoryId.caseCategoryName like :serviceName  ") ;

	        if(diagnosisName != null  && !diagnosisName.equalsIgnoreCase(""))
	        	sb.append("AND lower(c.claimId.diagnosisId.description) like  lower(:diagnosisName)  ");
	        
	        if (groupName != null  && !groupName.equalsIgnoreCase("")) 
				sb.append("AND lower(c.claimId.memberId.memberGroupId.groupName) like  lower(:groupName)  ");
	        
//	        if (serviceName != null  && !serviceName.equalsIgnoreCase("")) 
//				sb.append("AND claim.caseCategoryId.caseCategoryName = :serviceName ");
	        
	        if (state != null ) 
	        	sb.append("AND c.claimId.providerId.countryId = :state ");
				        
	        if (providerName != null  && !providerName.equalsIgnoreCase("")) 
	        	sb.append("AND lower(c.claimId.providerId.providerName) like  (:providerName) ");
			
			if (province != null ) 
				sb.append("AND c.claimId.providerId.provinceId = :province ");
			
			if (city != null ) 
				sb.append("AND c.claimId.providerId.cityId = :city ");
			
			if((dateBy != null  && !dateBy.equalsIgnoreCase("")) &&
				(maxDate != null) && (minimumDate != null)	){
				if(dateBy.equalsIgnoreCase("dischargeDate")){
					sb.append(" AND c.claimId.dischargeDate >= :startDate "
							+ " AND c.claimId.dischargeDate <=  :endDate ");
				}else if(dateBy.equalsIgnoreCase("admissionDate")){
					sb.append(" AND c.claimId.admissionDate >=  :startDate "
							+ " AND c.claimId.admissionDate <=  :endDate ");
				}
			}
			
			sb.append(" GROUP BY c.claimId.diagnosisId.description " +
					" ORDER BY c.claimId.diagnosisId.description asc " );
			
//			if (sortByColumn != null  && !sortByColumn.equalsIgnoreCase("")) {
//			if(order){
//				sb.append(" ORDER BY claim."+sortByColumn+" asc ");
//			}else{
//				sb.append(" ORDER BY claim."+sortByColumn+" desc ");
//			}
//			}
//			
	        
	        String sqlQuery = sb.toString();
	     
	        Query query = session.createQuery(sqlQuery);
	        
	        if(clientName != null  && !clientName.equalsIgnoreCase(""))
	        	query.setString("clientName", "%" + clientName + "%");
	        
	        if(diagnosisName != null  && !diagnosisName.equalsIgnoreCase(""))
	        	query.setString("diagnosisName", "%" + diagnosisName + "%");
	        
	        if (groupName != null  && !groupName.equalsIgnoreCase("")) 
	        	query.setString("groupName", "%" + groupName + "%");
	        
	        if(serviceName != null  && !serviceName.equalsIgnoreCase(""))
	        	query.setString("serviceName", "%" + serviceName + "%");
	        
	        if (state != null ) 
	        	query.setInteger("state", state);
	        
	        if (providerName != null  && !providerName.equalsIgnoreCase("")) 
	        	query.setString("providerName", "%" + providerName + "%");
	        
	        if (city != null ) 
	        	query.setInteger("city", city);
	        
	        if (province != null ) 
	        	query.setInteger("province", province);
	        
	        if (maxDate != null )
	            query.setDate("endDate", maxDate);
	            
	        if (minimumDate != null )
	            query.setDate("startDate", minimumDate);
	            
	       
	        
//	        query.setString("sortbyColumn", sortByColumn);
	        query.setFirstResult(index);
	        query.setMaxResults(offset);

	        result = query.list();
	        return result;
//	        Object[] result = (Object[]) query.uniqueResult();

	}
	
	public int getTotalCaseReference(String clientName, String groupName,
			String serviceName, String providerName, String diagnosisName, Integer state, Integer province,
			Integer city,
			Date minimumDate, Date maxDate, String dateBy ) throws Exception{
		
		Session session = myCaseDao.getCaseSession();
		 
	 	StringBuffer sb = new StringBuffer();
	 	sb.append("SELECT c.claimId.diagnosisId.description , floor(avg(c.claimId.claimChargeValue)) as avaragecost,  floor(max(c.claimId.claimChargeValue)) as maxcost," 
		 			 + " floor(min(c.claimId.claimChargeValue)) as mincost, floor(avg(c.longOfStay)) as avaragelos "
		 			 +" FROM Case c LEFT JOIN c.claimId LEFT JOIN c.claimId.diagnosisId LEFT JOIN c.claimId.memberId.memberGroupId LEFT JOIN c.claimId.memberId.memberGroupId.clientId " +
		 			 " LEFT JOIN c.claimId.caseCategoryId LEFT JOIN c.claimId.providerId "
	                 + "WHERE c.deletedStatus = 0 " );
       
	 	 if(clientName != null  && !clientName.equalsIgnoreCase(""))
	        	sb.append(" AND lower(c.claimId.memberId.memberGroupId.clientId.clientName) like lower(:clientName) " );
	        
	        if(serviceName != null  && !serviceName.equalsIgnoreCase(""))
	        	sb.append("AND c.claimId.caseCategoryId.caseCategoryName like :serviceName  ") ;

	        if(diagnosisName != null  && !diagnosisName.equalsIgnoreCase(""))
	        	sb.append("AND lower(c.claimId.diagnosisId.description) like lower(:diagnosisName)  ");
	        
	        if (groupName != null  && !groupName.equalsIgnoreCase("")) 
				sb.append("AND lower(c.claimId.memberId.memberGroupId.groupName) like lower(:groupName)  ");
	        
//	        if (serviceName != null  && !serviceName.equalsIgnoreCase("")) 
//				sb.append("AND claim.caseCategoryId.caseCategoryName = :serviceName ");
	        
	        if (state != null ) 
	        	sb.append("AND c.claimId.providerId.countryId = :state ");
				        
	        if (providerName != null  && !providerName.equalsIgnoreCase("")) 
	        	sb.append("AND lower(c.claimId.providerId.providerName) like lower(:providerName) ");
			
			if (province != null ) 
				sb.append("AND c.claimId.providerId.provinceId = :province ");
			
			if (city != null ) 
				sb.append("AND c.claimId.providerId.cityId = :city ");
					
		if((dateBy != null  && !dateBy.equalsIgnoreCase("")) &&
			(maxDate != null) && (minimumDate != null)	){
				if(dateBy.equalsIgnoreCase("dischargeDate")){
					sb.append(" AND c.claimId.dischargeDate >= :startDate " 
							+ " AND c.claimId.dischargeDate <=  :endDate ");
				}else if(dateBy.equalsIgnoreCase("admissionDate")){
					sb.append(" AND c.claimId.admissionDate >=  :startDate "
							+ " AND c.claimId.admissionDate <=  :endDate ");
				}
			}
			
		sb.append(" GROUP BY c.claimId.diagnosisId.description " +
				  " ORDER BY c.claimId.diagnosisId.description asc " );
		
        
        String sqlQuery = sb.toString();
        System.out.println("=========Mashuri sqlQuery : "+sqlQuery);
     
        Query query = session.createQuery(sqlQuery);
        if(clientName != null  && !clientName.equalsIgnoreCase(""))
        	query.setString("clientName", "%" + clientName + "%");
        
        if(diagnosisName != null  && !diagnosisName.equalsIgnoreCase(""))
        	query.setString("diagnosisName", "%" + diagnosisName + "%");
        
        if (groupName != null  && !groupName.equalsIgnoreCase("")) 
        	query.setString("groupName",  "%" + groupName + "%");
        
        if(serviceName != null  && !serviceName.equalsIgnoreCase(""))
        	query.setString("serviceName",  "%" + serviceName + "%");
        
        if (state != null ) 
        	query.setInteger("state", state );
        
        if (providerName != null  && !providerName.equalsIgnoreCase("")) 
        	query.setString("providerName",  "%" + providerName + "%");
        
        if (city != null ) 
        	query.setInteger("city", city);
        
        if (province != null )
        	query.setInteger("province", province);
        
        if (minimumDate != null && !minimumDate.equals("") )
            query.setDate("startDate", minimumDate);
            
        if (maxDate != null && !maxDate.equals("") )
            query.setDate("endDate", maxDate);
            

        List list = query.list();
        int result = list.size();
        System.out.println("=========Mashuri result : "+result);
        return result;

		}


	//Add 20150909 by FV0, untuk requirement provider 6, provider report- mencari jumlah transaksi yang terjadi di rumah sakit selama edc tersebut rusak
	public int getTotalProviderCaseManualRegistration(Integer providerId, Integer edcTerminalId){
		int total = 0;
		try{
			Session session = myCaseDao.getCaseSession();
			if(session != null){
				Query query = session.createQuery("SELECT COUNT(cs.caseId) FROM " +
						" Case cs INNER JOIN cs.providerId, EdcTerminal edc INNER JOIN edc.providerId " +
						"  WHERE " +
						"  cs.providerId.providerId = edc.providerId.providerId " +
						"  AND" +
						"  cs.manualRegistration = 1 AND " +
						"  cs.providerId.providerId = :providerId AND " +
						"  edc.id = :edcTerminalId AND " +
						"  cs.caseStartTime > edc.lastTransaction AND " +
						"  edc.deletedStatus = 0 AND " +
						"  cs.providerId.deletedStatus = 0");
				
				query.setInteger("providerId", providerId);
				query.setInteger("edcTerminalId", edcTerminalId);
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
}
