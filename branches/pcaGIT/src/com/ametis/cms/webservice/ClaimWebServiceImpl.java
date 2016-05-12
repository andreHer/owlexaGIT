package com.ametis.cms.webservice;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.codehaus.xfire.transport.http.XFireServletController;
import org.joda.time.DateTime;
import org.joda.time.Days;

import com.ametis.cms.dao.PaymentDao;
import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CardType;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.EdcTransactionLog;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.OutstandingClaim;
import com.ametis.cms.datamodel.OutstandingClaimItem;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentResult;
import com.ametis.cms.datamodel.PaymentTransactionLog;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.CaseCategoryDto;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimExchangeDetailDto;
import com.ametis.cms.dto.ClaimExchangeDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.ClaimSCDto;
import com.ametis.cms.dto.EDCTransactionLogDto;
import com.ametis.cms.dto.PaymentDto;
import com.ametis.cms.dto.PaymentTransactionClientLogDto;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberDiagnosisService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.OutstandingClaimItemService;
import com.ametis.cms.service.OutstandingClaimService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.PaymentTransactionLogService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.util.ClaimItemUtil;
import com.ametis.cms.util.Converter;
import com.ametis.cms.util.StringUtil;



@WebService(name = "ClaimWebService", 
		endpointInterface = "com.ametis.cms.webservice.IClaimWebService",
		serviceName="ClaimWebService")
public class ClaimWebServiceImpl implements IClaimWebService {

	private ClaimService claimService;
	private MemberService memberService;
	private MemberProductService memberProductService;
	private MemberBenefitService memberBenefitService;
	private MemberLimitLayerService memberLimitLayerService;
	private MemberDiagnosisService memberDiagnosisService;
	private ClaimItemService claimItemService;
	private ItemService itemService;
	private ItemCategoryService itemCategoryService;
	private OutstandingClaimService outstandingClaimService;
	private OutstandingClaimItemService outstandingClaimItemService;
	private CaseCategoryService caseCategoryService;
	private DiagnosisService diagnosisService;
	private BatchClaimService batchClaimService;
	private ClientService clientService;
	private MemberGroupService memberGroupService;
	private ProviderService providerService;
	private ConfigurationService configurationService;
	private MemberProviderService memberProviderService;
	private MemberGroupProviderService memberGroupProviderService;
	private ClientProviderService clientProviderService;
	private PolicyService policyService;
	private CaseService caseService;
	private PoliklinikService poliklinikService;
	private EdcTerminalService edcTerminalService;
	
	private PaymentTransactionLogService paymentTransactionLogService;
	 
	private PaymentService paymentService;
	private SecurityService securityService;


	public PaymentService getPaymentService() {
		return paymentService;
	}


	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}


	public PaymentTransactionLogService getPaymentTransactionLogService() {
		return paymentTransactionLogService;
	}


	public void setPaymentTransactionLogService(
			PaymentTransactionLogService paymentTransactionLogService) {
		this.paymentTransactionLogService = paymentTransactionLogService;
	}
	
	public EdcTerminalService getEdcTerminalService() {
		return edcTerminalService;
	}


	public void setEdcTerminalService(EdcTerminalService edcTerminalService) {
		this.edcTerminalService = edcTerminalService;
	}


	public MemberLimitLayerService getMemberLimitLayerService() {
		return memberLimitLayerService;
	}


	public void setMemberLimitLayerService(
			MemberLimitLayerService memberLimitLayerService) {
		this.memberLimitLayerService = memberLimitLayerService;
	}


	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}


	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}


	public CaseService getCaseService() {
		return caseService;
	}


	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
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


	public MemberGroupProviderService getMemberGroupProviderService() {
		return memberGroupProviderService;
	}


	public void setMemberGroupProviderService(
			MemberGroupProviderService memberGroupProviderService) {
		this.memberGroupProviderService = memberGroupProviderService;
	}


	public ClientProviderService getClientProviderService() {
		return clientProviderService;
	}


	public void setClientProviderService(ClientProviderService clientProviderService) {
		this.clientProviderService = clientProviderService;
	}


	public ConfigurationService getConfigurationService() {
		return configurationService;
	}


	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}


	public MemberDiagnosisService getMemberDiagnosisService() {
		return memberDiagnosisService;
	}


	public void setMemberDiagnosisService(
			MemberDiagnosisService memberDiagnosisService) {
		this.memberDiagnosisService = memberDiagnosisService;
	}


	public ProviderService getProviderService() {
		return providerService;
	}


	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}


	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}


	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}


	public ClientService getClientService() {
		return clientService;
	}


	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}


	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}


	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}


	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}


	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}


	public MemberProductService getMemberProductService() {
		return memberProductService;
	}


	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}


	public MemberBenefitService getMemberBenefitService() {
		return memberBenefitService;
	}


	public void setMemberBenefitService(MemberBenefitService memberBenefitService) {
		this.memberBenefitService = memberBenefitService;
	}


	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}


	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}


	public ClaimService getClaimService() {
		return claimService;
	}


	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}


	public MemberService getMemberService() {
		return memberService;
	}


	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}


	
	
	public ItemService getItemService() {
		return itemService;
	}


	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}


	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}


	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}


	public OutstandingClaimService getOutstandingClaimService() {
		return outstandingClaimService;
	}


	public void setOutstandingClaimService(
			OutstandingClaimService outstandingClaimService) {
		this.outstandingClaimService = outstandingClaimService;
	}


	public OutstandingClaimItemService getOutstandingClaimItemService() {
		return outstandingClaimItemService;
	}


	public void setOutstandingClaimItemService(
			OutstandingClaimItemService outstandingClaimItemService) {
		this.outstandingClaimItemService = outstandingClaimItemService;
	}


	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}


	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}
	
	public void setSecurityService (SecurityService object){
	    this.securityService = object;
	}
	public SecurityService getSecurityService (){
	    return this.securityService;
	}


	public Collection<ClaimSCDto> getLastClaim(String memberNumber) {
		// TODO Auto-generated method stub
		
		Collection<ClaimSCDto> result = new Vector<ClaimSCDto>();
		
		try {
			Member member = memberService.getMember(memberNumber);
			
			if (member != null){
				String[] eqParam = {"memberId.memberId"};
				Object[] eqValue = {member.getMemberId()};
				
				Collection<Claim> claimColl = claimService.search(null, null, eqParam, eqValue, 0,3);
				
				if (claimColl != null){
					Iterator<Claim> iterator = claimColl.iterator();
					
					if (iterator != null){
						while (iterator.hasNext()){
							Claim claim = iterator.next();
							
							if (claim != null){
								ClaimSCDto claimSc = new ClaimSCDto();
								claimSc.setClaimApprovedValue(claim.getClaimApprovedValue());
								claimSc.setClaimDate(claim.getClaimDate());
								claimSc.setServiceCategory(claim.getCaseCategoryId().getCaseCategoryName());
								
								result.add(claimSc);
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
	
	//Add by aju on 20150909, for getting the claim details by claimId fufufu :D
	public Collection<ClaimItemDto> getMemberClaimDetailByClaimId(Integer claimId) {
		// TODO Auto-generated method stub
		
		Collection<ClaimItemDto> result = new Vector<ClaimItemDto>();
		
		try {
			System.out.println("Getting claimItem by claimId...");
			java.io.Serializable cid = claimId;
			
			Claim claim = claimService.get(cid);

			System.out.println("The claim : " + claim);
			
			if (claim != null){
					//get the claimItem per claim fufufu :D
					String[] eqParamClaimItem = {"claimId.claimId", "deletedStatus"};
					Object[] eqValueClaimItem = {claim.getClaimId(), 0};
					String[] requiredClaimItem = {"ClaimItem.ItemId"};
					
					System.out.println("Getting Claim Item for claimId : " + claim.getClaimId());
					int totalClaimItem = claimItemService.getTotal(null, null, eqParamClaimItem, eqValueClaimItem);
					
					System.out.println("Total Claim Item for claimId " + claim.getClaimId() + " : " + totalClaimItem);
					Collection<ClaimItem> listClaimItem = claimItemService.search(null,null,eqParamClaimItem,eqValueClaimItem,null,null,null,requiredClaimItem,0,totalClaimItem);
					
					System.out.println("Search claimItem result : " + listClaimItem.size()); 
					if (listClaimItem != null){
						for (ClaimItem claimItem : listClaimItem) {
							System.out.println("Getting claimItemId : " + claimItem.getClaimItemId());
							ClaimItemDto dto = claimItem.export();
							
							result.add(dto);
						}
					}
				
			}
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public Collection<ClaimItemDto> getMemberClaimDetailList(Integer memberId,
			Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		
		Collection<ClaimItemDto> result = new Vector<ClaimItemDto>();
		
		try {
			//Modified by aju on 20150909, there's hibernate duplicate alias error zzz, get the claim first, then the claimItem
			/*
			String[] eqParam = {"claimId.memberId.memberId"};
			Object[] eqValue = {memberId};
			String[] required = {};
			
			Object[] start = {startDate};
			Object[] end = {endDate};
			String[] between = {"claimId.claimDate"};
			System.out.println("Getting Claim Item for memberId : " + memberId);
			int total = claimItemService.getTotal(null,null,eqParam,eqValue,"claimId.claimDate",startDate,endDate);
			System.out.println("Total Claim Item : " + total);
			Collection<ClaimItem> list = claimItemService.search(null,null,eqParam,eqValue,between,start,end,required,0,total);
			
			if (list != null){
				for (ClaimItem claimItem : list) {
					ClaimItemDto dto = claimItem.export();
					
					result.add(dto);
				}
			}
			*/
			
			String[] eqParam = {"memberId.memberId", "deletedStatus"};
			Object[] eqValue = {memberId, 0};
			String[] required = {
					"Claim.MemberId", "Claim.MemberId.MemberGroupId", "Claim.BatchClaimId", "Claim.ClaimStatus", "Claim.DiagnosisId", "Claim.Diagnosis2Id", "Claim.Diagnosis3Id",
					"Claim.PaymentId"
					};
			
			Object[] start = {startDate};
			Object[] end = {endDate};
			String[] between = {"claimDate"};
			System.out.println("Getting total claim for memberId : " + memberId);
			int total = claimService.getTotal(null,null,eqParam,eqValue,"claimDate",startDate,endDate);
			System.out.println("Total claim : " + total);
			Collection<Claim> list = claimService.search(null,null,eqParam,eqValue,between,start,end,required,0,total);
			
			if (list != null){
				for (Claim claim : list) {
					/*
					ClaimDto dto = claim.exportDto();
					result.add(dto);
					*/
					
					//get the claimItem per claim fufufu :D
					String[] eqParamClaimItem = {"claimId.claimId", "deletedStatus"};
					Object[] eqValueClaimItem = {claim.getClaimId(), 0};
					String[] requiredClaimItem = {"ClaimItem.ItemId"};
					
					System.out.println("Getting Claim Item for claimId : " + claim.getClaimId());
					int totalClaimItem = claimItemService.getTotal(null, null, eqParamClaimItem, eqValueClaimItem);
					
					System.out.println("Total Claim Item for claimId " + claim.getClaimId() + " : " + totalClaimItem);
					Collection<ClaimItem> listClaimItem = claimItemService.search(null,null,eqParamClaimItem,eqValueClaimItem,null,null,null,requiredClaimItem,0,totalClaimItem);
					
					System.out.println("Search claimItem result : " + listClaimItem.size()); 
					if (listClaimItem != null){
						for (ClaimItem claimItem : listClaimItem) {
							System.out.println("Getting claimItemId : " + claimItem.getClaimItemId());
							ClaimItemDto dto = claimItem.export();
							
							result.add(dto);
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


	public Collection<ClaimDto> getMemberClaimList(Integer memberId,
			Date startDate, Date endDate) {
		
		Collection<ClaimDto> result = new Vector<ClaimDto>();
		
		try {
			String[] eqParam = {"memberId.memberId", "deletedStatus"};
			Object[] eqValue = {memberId, 0};
			String[] required = {
					"Claim.MemberId", "Claim.MemberId.MemberGroupId", "Claim.BatchClaimId", "Claim.ClaimStatus", "Claim.DiagnosisId", "Claim.Diagnosis2Id", "Claim.Diagnosis3Id",
					"Claim.PaymentId"
					};
			
			Object[] start = {startDate};
			Object[] end = {endDate};
			String[] between = {"claimDate"};
			System.out.println("Getting total claim for memberId : " + memberId);
			int total = claimService.getTotal(null,null,eqParam,eqValue,"claimDate",startDate,endDate);
			System.out.println("Total claim : " + total);
			Collection<Claim> list = claimService.search(null,null,eqParam,eqValue,between,start,end,required,0,total);
			
			if (list != null){
				for (Claim claim : list) {
					ClaimDto dto = claim.exportDto();
					
					result.add(dto);
				}
			}
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}


	public Claim getCaseClaim (ClaimSCDto claimDto, Collection<ClaimItemDto> claimDetails, String merchantId){
		Claim result = null;
		
		try {
			if (claimDto != null && claimDetails != null){
				
				System.out.println("DIDALAM VERIFIKASI EXISTING CLAIM !!!");
				
				double claimCharge = 0.0;
				
				for (Iterator iterator = claimDetails.iterator(); iterator
						.hasNext();) {
					ClaimItemDto claimItemDto = (ClaimItemDto) iterator.next();
					if (claimItemDto != null){
						claimCharge += claimItemDto.getClaimItemValue().doubleValue();
					}
					
				}
				
				
				Integer caseCategoryId = null;
				if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.DENTAL_STR)){
					caseCategoryId = CaseCategory.DENTAL;					
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.INPATIENT_STR)){
					caseCategoryId = CaseCategory.INPATIENT;
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MATERNITY_STR)){
					caseCategoryId = CaseCategory.MATERNITY;					
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.OPTICAL_STR)){
					caseCategoryId = CaseCategory.OPTICAL;					
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.OUTPATIENT_STR)){
					caseCategoryId = CaseCategory.OUTPATIENT;					
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.SPECIALIST_STR)){
					caseCategoryId = CaseCategory.SPECIALIST;
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MISC_STR)){
					caseCategoryId = CaseCategory.MISC;
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MEDICAL_CHECK_UP_STR)){
					caseCategoryId = CaseCategory.MEDICAL_CHECK_UP;
				}				
				
				Provider provider = providerService.getByProviderCode(merchantId);
				Member member = memberService.getMemberByCardNumber(claimDto.getMemberNumber());
				
				if (member != null && provider != null){
					String[] eqParam = {"caseCategoryId.caseCategoryId","deletedStatus","providerId.providerId",
							"memberId.memberId","dischargeDate","claimStatus.caseStatusId"};
					Object[] eqValue = {caseCategoryId, Integer.valueOf(0),provider.getProviderId(),member.getMemberId(),
							new java.sql.Date(System.currentTimeMillis()),Claim.CLAIM_CHECKED};
					 
					Collection<Claim> pendingClaim = claimService.search(null,null,eqParam,eqValue,0,1);
					if (pendingClaim != null){
						Iterator<Claim> iterator = pendingClaim.iterator();
						
						if (iterator.hasNext()){
							Claim tmp = iterator.next();
							
							if (tmp != null){
								double dbClaimValue = tmp.getClaimChargeValue().doubleValue();
								double dbTolerance = 0.1*dbClaimValue;
								double minClaimValue = dbClaimValue - dbTolerance;
								double maxClaimValue = dbClaimValue + dbTolerance;
								
								if (claimCharge == dbClaimValue || (minClaimValue < claimCharge && claimCharge < maxClaimValue) ){
									result = tmp;
								}
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
	
	public ActionResult registerClaim(ClaimSCDto claimDto,
			Collection<ClaimItemDto> claimDetails, String merchantId, String terminalId, String traceNumber,String referenceNumber)
			throws Exception {
		ActionResult result = new ActionResult();
		try {
			if (claimDetails != null){
				Claim claim = new Claim();				
				
				Member member = null;
				
				String[] required = {"Member.MemberGroupId","Member.ClientId","Member.MemberGroupId.Status","Member.MemberGroupId.ClientId"};
				String[] eqParamMem = {"currentCardNumber","deletedStatus"};
								
				EdcTerminal edcTerminal = edcTerminalService.getEdcTerminal(merchantId, terminalId);
				
				
				Object[] eqValueMem = {claimDto.getMemberNumber(),Integer.valueOf(0)};
				
				
				Collection<Member> memberList = memberService.search(null,null,eqParamMem,eqValueMem,required,0,1);
				
				if (memberList != null){
					Iterator<Member> memberIterator = memberList.iterator();
					
					if (memberIterator.hasNext()){
						member = memberIterator.next();
					}
				}		

				CaseCategory cc = caseCategoryService.getCaseCategoryEDC(claimDto.getServiceCategory());
				
				boolean isManagedCare = false;
				boolean isIndemnity = false;
				boolean isRefered = false;
				
				Policy policy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), member.getClientId().getClientId());

				
				if (policy != null){
					if (policy.getPolicyType().intValue() == 1){
						isIndemnity = true;
					}
					else if (policy.getPolicyType().intValue() == 2){
						isManagedCare = true;
						
						if (cc == null){
							Poliklinik poliklinik = poliklinikService.getPoliByEdcCode(claimDto.getServiceCategory());
							
							if (poliklinik != null){
								cc = poliklinik.getCaseCategoryId();
							}
						}
					}
				}
				
				if (cc.getCaseCategoryCode().equalsIgnoreCase(CaseCategory.DENTAL_STR)){
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}					
				else if (cc.getCaseCategoryCode().equalsIgnoreCase(CaseCategory.OPTICAL_STR)){
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				else if (cc.getCaseCategoryCode().equalsIgnoreCase(CaseCategory.OUTPATIENT_STR)){
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				else if (cc.getCaseCategoryCode().equalsIgnoreCase(CaseCategory.SPECIALIST_STR)){
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MISC_STR)){
					cc.setCaseCategoryId(CaseCategory.MISC);
					cc.setCaseCategoryCode(CaseCategory.MISC_STR);
				}
				else if (cc.getCaseCategoryCode().equalsIgnoreCase(CaseCategory.MEDICAL_CHECK_UP_STR)){
					claim.setAdmissionDate(claimDto.getDischargedDate());
				}
				
				System.out.println("Geeting Diagnosis For : " + claimDto.getDiagnosis1Code() + " -- " + claimDto.getDiagnosis2Code() + " -- " + claimDto.getDiagnosis3Code());
				Provider provider = providerService.getByProviderCode(merchantId);					
				System.out.println("DB Member No : " + member.getCustomerNumber() + " name : " + member.getFirstName() + " MEMBER SC = " + claimDto.getMemberNumber() + " MID = " + merchantId);
				
				String[] eqProductParam = {"memberId.memberId","productId.caseCategory.caseCategoryId","deletedStatus","memberProductStatus.statusId"};
				Object[] eqProductValue = {member.getMemberId(),cc.getCaseCategoryId(),Integer.valueOf(0),SubscriptionStatus.ACTIVE};
				
				System.out.println("BEFORE GETTING PRODUCT LIST");
				Collection<MemberProduct> productList = memberProductService.search(null,null,eqProductParam,eqProductValue,0,1);
				
				System.out.println("AFTER GETTING PRODUCT LIST");
				if (productList != null){
					System.out.println("Product List Not Null");
					
					Iterator<MemberProduct> iterator = productList.iterator();
					
					if (iterator.hasNext()){
						MemberProduct memberProduct = iterator.next();
						
						if (memberProduct != null && memberProduct.getAnnualBenefit() != null && memberProduct.getActualBenefitLimit() != null){
							if (memberProduct.getAnnualBenefit().intValue() != -1 && memberProduct.getActualBenefitLimit().doubleValue() == 0.0){
								if (memberProduct.getExcessPaymentType() != null ){
									
									if (memberProduct.getExcessPaymentType().intValue() == 1){
										System.out.println("TERNYATA SUDAH HABIS NIH ---> TAPI BOLEH LANJUT DICOVER ASURANSI");
										
									}
									else if (memberProduct.getExcessPaymentType().intValue() == 0){
										System.out.println("TERNYATA SUDAH HABIS NIH");
										result.setResult(false);
										result.setAdditionalMessage("18");
										
										return result;
									}
									
								}
								else {
									System.out.println("TERNYATA SUDAH HABIS NIH");
									result.setResult(false);
									result.setAdditionalMessage("18");
									
									return result;
								}
								
							}
							else {
								System.out.println("SISA BENEFIT = " + memberProduct.getActualBenefitLimit().doubleValue());
							}
						}
						else {
							System.out.println("MEMBER PRODUCT ACTUAL BENEFIT LIMIT IS NULL");
						}
					}
				}
				if (provider != null){
					System.out.println("Provider nya : " + provider.getProviderName());
				}
				else {
					// check validity for Provider (RC 15 / 23 )
					result.setResult(false);
					result.setAdditionalMessage("15");
					return result;
					
				}					
				
				/**
				 * checking claim condition for response code
				 */
				Policy activePolicy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId()
						, member.getMemberGroupId().getClientId().getClientId());
				
				// CHECK RC 28
				System.out.println("check 28");
				if (activePolicy != null ){
					if (activePolicy.getCardTypeId() != null && activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SHOW_CARD){
						result.setResult(false);
						result.setAdditionalMessage("28");
						return result;
					}

					// PASS RC 28
					
					// CHECK RC 29
					System.out.println("check 29");	
					if (activePolicy.getCardTypeId() != null && (activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SMART_CARD || 
							activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SWIPE_CARD)){
						
						if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_CLIENT_USAGE_TYPE){
//								String[] eqProviderParam = {"clientId.clientId","providerId.providerId"};
//								Object[] eqProviderValue = {member.getClientId().getClientId(),provider.getProviderId()};
//								
//								int total = clientProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
//								
//								if (total == 0){
//									result.setResult(false);
//									result.setAdditionalMessage("NOTPROVIDER");
//									return result;
//								}
						}
						else if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_GROUP_USAGE_TYPE){
							String[] eqProviderParam = {"memberGroupId.memberGroupId","providerId.providerId"};
							Object[] eqProviderValue = {member.getMemberGroupId().getMemberGroupId(),provider.getProviderId()};
							
							int total = memberGroupProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
							
							if (total == 0){
								result.setResult(false);
								result.setAdditionalMessage("29");
								return result;
							}
						}
						else if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_MEMBER_USAGE_TYPE){
							String[] eqProviderParam = {"memberId.memberId","providerId.providerId"};
							Object[] eqProviderValue = {member.getMemberId(),provider.getProviderId()};
							
							int total = memberProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
							
							if (total == 0){
								result.setResult(false);
								result.setAdditionalMessage("29");
								return result;
							}
						}
					}
				}
				
				System.out.println("check 17");
				// check for data validity (RC 17)
				
				if (cc.getCaseCategoryId().intValue() == CaseCategory.INPATIENT 
						|| cc.getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
					System.out.println("CHECKING RC 17 DISCHARGE DATE = " + claimDto.getDischargedDate().toString() + 
							" CURRENT : " + new java.sql.Date(System.currentTimeMillis()).toString());
					if (claimDto.getDischargedDate() != null){
						if (claimDto.getDischargedDate().after(new java.sql.Date(System.currentTimeMillis()))){
							System.out.println("RC 17 KENA DISCHARGE DATE > CURRENT");
							result.setResult(false);
							result.setAdditionalMessage("17");
							
							return result;	
						}
					}
					if (claimDetails != null){
						double itemAmount = 0.0;
						for (Iterator iterator = claimDetails.iterator(); iterator.hasNext();) {
							
							ClaimItemDto claimItemDto = (ClaimItemDto) iterator.next();
							if (claimItemDto != null){
								if (claimItemDto.getClaimItemAmount() != null){
									double tmpItemAmount = claimItemDto.getClaimItemAmount().doubleValue();
									
									if (tmpItemAmount > itemAmount){
										itemAmount = tmpItemAmount;
									}
								}
							}					
						}
						
						if (claimDto.getTotalDays() != null){
							if (itemAmount > claimDto.getTotalDays()){								
								result.setResult(false);
								result.setAdditionalMessage("17");
								return result;
							}
						}
					}
				}
				else {
					System.out.println("check 28 kedua");
					
					if (claimDetails != null){
						double itemAmount = 0.0;
						for (Iterator iterator = claimDetails.iterator(); iterator.hasNext();) {
							
							ClaimItemDto claimItemDto = (ClaimItemDto) iterator.next();
							if (claimItemDto != null){
								if (claimItemDto.getClaimItemAmount() != null){
									double tmpItemAmount = claimItemDto.getClaimItemAmount().doubleValue();
									
									System.out.println("ITEM AMOUNT = " + tmpItemAmount);
									if (tmpItemAmount > itemAmount){
										itemAmount = tmpItemAmount;
									}
								}
							}					
						}				
						
						if (itemAmount > 1){								
							result.setResult(false);
							result.setAdditionalMessage("17");
							return result;
						}
						
					}
				}
				// check RC 17 Passed
				// check for benefit validity (RC 24 / RC 26);
				
				
				/**
				 * check previous check-in case record
				 */
				String[] eqCaseParam = {"deletedStatus","providerId.providerId","caseCategoryId.caseCategoryId",
						"memberId.memberId","caseStatusId.caseStatusId"};
				Object[] eqCaseValue = {0,provider.getProviderId(),cc.getCaseCategoryId(),member.getMemberId(),CaseStatus.CASE_OPEN};
				
				Collection<Case> checkInCaseList = null;
				
				if (caseService != null){
					checkInCaseList = caseService.search(null,null,eqCaseParam,eqCaseValue,0,1);
				}
				
				Case checkInCase = null;
				
				if (checkInCaseList != null){
					Iterator<Case> iterator = checkInCaseList.iterator();
					if (iterator.hasNext()){
						checkInCase = iterator.next();
					}
				}
				
				if (checkInCase == null){
					result.setResult(false);
					result.setAdditionalMessage("22");
					result.setActionCode("NONREGISTER");
					return result;
				}
				
				
				System.out.println("PREPARE CLAIM DATA");
				claim.setAdmissionDate(claimDto.getAdmissionDate());
				claim.setTerminalId(terminalId);
				claim.setMerchantId(merchantId);
				claim.setEdcTraceNumber(traceNumber);
				claim.setClaimDate(claimDto.getClaimDate());
				claim.setOtherNumber(referenceNumber);
				claim.setCaseId(checkInCase);
				
				claim.setCaseCategoryId(cc);				
				claim.setClaimDate(claimDto.getClaimDate());
				claim.setDischargeDate(claimDto.getDischargedDate());
				claim.setMemberId(member);
				
				if (member != null && member.getClientId() != null){
					System.out.println("SETTING CLAIM ID");
					claim.setClientId(member.getClientId().getClientId());
				}
				
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(1);
				claim.setClaimStatus(status);
				
				System.out.println ("Setting Claim Type");
				ClaimType claimType = new ClaimType();
				claimType.setClaimTypeId(ClaimType.CASHLESS);
				claim.setClaimTypeId(claimType);
				
				
				claim.setProviderId(provider);
				claim.setProviderName(provider.getProviderName());
				boolean isDiagnosisExcluded = false;
				
				
				System.out.println ("setting claim diagnosis code : " + claimDto.getDiagnosis1Code() + " | ");
				if (claimDto.getDiagnosis1Code() != null && !claimDto.getDiagnosis1Code().equalsIgnoreCase("")){
					System.out.println("nyari diagnosis : " + claimDto.getDiagnosis1Code() + " di database ! ");
					
					Diagnosis diagnosis = diagnosisService.getDiagnosisByAlternateCode(claimDto.getDiagnosis1Code());
					
					if (diagnosis != null){
						System.out.println("dapet nih diagnosis nya : " + diagnosis.getDiagnosisName());
						claim.setDiagnosisId(diagnosis);
				
						claim.setDiagnosis1Code(diagnosis.getDiagnosisCode());
					}
					else {
						// RC 16
						result.setResult(false);
						result.setAdditionalMessage("16");
						return result;
					}
				}
				if (claimDto.getDiagnosis2Code() != null && !claimDto.getDiagnosis2Code().equalsIgnoreCase("")){
					System.out.println("nyari diagnosis 2 : " + claimDto.getDiagnosis2Code() + " di database ! ");
					Diagnosis diagnosis = diagnosisService.getDiagnosisByAlternateCode(claimDto.getDiagnosis2Code());
					if (diagnosis != null){
						System.out.println("dapet nih diagnosis 2 nya : " + diagnosis.getDiagnosisName());
						
						claim.setDiagnosis2Id(diagnosis);
						claim.setDiagnosis2Code(diagnosis.getDiagnosisCode());
					}
				}
				
				if (claimDto.getDiagnosis3Code() != null && !claimDto.getDiagnosis3Code().equalsIgnoreCase("")){
					System.out.println("nyari diagnosis 3 : " + claimDto.getDiagnosis3Code() + " di database ! ");
					Diagnosis diagnosis = diagnosisService.getDiagnosisByAlternateCode(claimDto.getDiagnosis3Code());
					if (diagnosis != null){
						System.out.println("dapet nih diagnosis 3 nya : " + diagnosis.getDiagnosisName());
						claim.setDiagnosis3Id(diagnosis);
						claim.setDiagnosis3Code(diagnosis.getDiagnosisCode());
					}
				}
				System.out.println("checking claim item iterator");
				
				Iterator<ClaimItemDto> iterator = claimDetails.iterator();
				
				Collection<ClaimItem> claimDetailList = new Vector<ClaimItem>();
				
				while (iterator.hasNext()){
					ClaimItemDto itemDto = iterator.next();
					
					if (itemDto != null){
						
						String[] eqParam = {"deletedStatus","itemCategoryId.itemCategoryCode","itemCode"};
						Object[] eqValue = {Integer.valueOf(0),itemDto.getItemCode(),itemDto.getItemCode()};
						
						Collection<Item> itemList = itemService.search(null,null,eqParam,eqValue,0,1);
						
						
						Item item = null;
						if (itemList != null){
							Iterator<Item> itemIterator = itemList.iterator();
							
							if (itemIterator.hasNext()){
								item = itemIterator.next();
							}
						}
						
						if (item != null){
							ClaimItem claimItem = new ClaimItem();
							claimItem.setItemId(item);
							claimItem.setItemCode(itemDto.getItemCode());
							claimItem.setClaimItemAmount(itemDto.getClaimItemAmount());
							claimItem.setClaimItemValue(itemDto.getClaimItemValue());
							CaseStatus claimItemStatus = new CaseStatus();
							claimItemStatus.setCaseStatusId(ClaimItem.CLAIM_ITEM_APPROVED);
							claimItem.setClaimItemStatus(claimItemStatus);
				
							if (item != null){
								System.out.println("adding item to claim : " + item.getItemName()  + " item code : " + item.getItemCode());
							}
							
							claimDetailList.add(claimItem);
						}
					}
				}
				
				ActionUser actionUser = new ActionUser();
				User user = new User();
				user.setUsername(merchantId);
				actionUser.setUser(user);
				
				System.out.println("CREATING CLAIM FROM EDC");
				
				
				Claim claimResult = claimService.createClaimItemEDC(claim, claimDetailList, 0.0, actionUser);
				
				
				if (claimResult != null){
					System.out.println("CLAIM CREATED : " + claimResult.getClaimNumber());
					
					String[] requiredClaim = {"Claim.ClaimStatus","Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id", "Claim.MemberId"};
					
					if (edcTerminal != null){
						edcTerminal.setLastTransaction(new java.sql.Date(System.currentTimeMillis()));
						
						//modified by aju on 20150918, perasaan udah gw ubah jadi update deh zzzzz
						//edcTerminalService.create(edcTerminal, actionUser);
						edcTerminalService.update(edcTerminal, actionUser);
						
					}
	
					claimResult = claimService.get(claimResult.getClaimId(), requiredClaim);
					
					if (claimResult.getClaimStatus().getCaseStatusId().intValue() != Claim.CLAIM_REJECT){
						
						Collection<ClaimItem> claimCheckedItem = claimItemService.getBenefitCheckItem(claimResult);
						
						Collection<ClaimItem> approvedItem = new Vector<ClaimItem>();
						
						if (claimCheckedItem != null){
							for (Iterator iterator2 = claimCheckedItem.iterator(); iterator2
									.hasNext();) {
								
								ClaimItem claimItem = (ClaimItem) iterator2.next();
								
								if (claimItem != null){
									if (!isDiagnosisExcluded){
										claimItem.setClaimItemApprovedValue(claimItem.getClaimItemCoveredValue());									
									}
									else {
										claimItem.setClaimItemApprovedValue(0.0);
										claimItem.setBenefitCheckRemarks("Diagnosis Excluded " );
									}
									approvedItem.add(claimItem);
								}							
							}
						}					
						
						String res = claimService.approveCheckItemBulk(claimResult.getClaimId(), approvedItem, actionUser);
						
						System.out.println("AFTER APPROVING CLAIM = " + res);
					}
					else {
						result.setReason(claimResult.getCheckerRemarks());
					}
					
					
					Collection<ClaimItem> details = claimItemService.getClaimItem(claimResult.getClaimId());
					
					String addResultInfo = "";
					
					int idx = 0;
					String excessInformation = " ";
					String totalClaimInfo = " " ;
					double claimExcessValue = 0;
					double claimApprovedValue = 0;
					
					for (Iterator iterator2 = details.iterator(); iterator2.hasNext();) {						
						ClaimItem claimItem = (ClaimItem) iterator2.next();						
						String line = "";
						if (idx == 0){
							if (claimItem.getBenefitCheckRemarks() != null && claimItem.getBenefitCheckRemarks().equals(ClaimItem.TIDAK_AMBIL_BENEFIT)){
								line = "SALAH KODE,"+
								StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
							}
							else {
								line = claimItem.getItemId().getItemEDCName()+","+
								StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
							}
						}
						else {
							if (claimItem.getBenefitCheckRemarks() != null && claimItem.getBenefitCheckRemarks().equals(ClaimItem.TIDAK_AMBIL_BENEFIT)){
								line = "|"+"SALAH KODE,"+
								StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
							}
							else {
								line = "|"+claimItem.getItemId().getItemEDCName()+","+
								StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
							}
						}
						if (claimItem.getClaimItemValue() != null && claimItem.getClaimItemApprovedValue() != null){
							claimApprovedValue+=claimItem.getClaimItemApprovedValue().doubleValue();
							claimExcessValue += (claimItem.getClaimItemValue().doubleValue() - claimItem.getClaimItemApprovedValue().doubleValue());
						}
						addResultInfo += line;						
						idx++;
					}

					String claimNumber =  "";
					
					String refNumberRandom = RandomStringUtils.randomNumeric(10);
					
					if (checkInCase != null){
						
						claimNumber =  checkInCase.getCaseNumber();
						
						StringTokenizer tokenizer = new StringTokenizer(claimNumber,"/");
						if (tokenizer.hasMoreTokens()){
							claimNumber = tokenizer.nextToken();
						}
						
						checkInCase.setClaimId(claim);
						checkInCase.setCaseClaimValue(claimApprovedValue+claimExcessValue);
						checkInCase.setCaseApprovedValue(claimApprovedValue);
						checkInCase.setCaseExcessValue(claimExcessValue);
						checkInCase.setCaseEndTime(new java.sql.Date(System.currentTimeMillis()));
						checkInCase.setElectronicRefNumber(refNumberRandom);
						
						if (claimResult.getDiagnosisId() != null){
							checkInCase.setDiagnosis1Id(claimResult.getDiagnosisId());
						}
						if (claimResult.getDiagnosis2Id() != null){
							checkInCase.setDiagnosis2Id(claimResult.getDiagnosis2Id());
						}
						if (claimResult.getDiagnosis3Id() != null){
							checkInCase.setDiagnosis3Id(claimResult.getDiagnosis3Id());
						}
						
						CaseStatus caseStatus = new CaseStatus();
						caseStatus.setCaseStatusId(CaseStatus.CASE_FINALIZED);					
						
						checkInCase.setCaseStatusId(caseStatus);
						caseService.update(checkInCase, actionUser);
						System.out.println("UPDATING CASE = " + checkInCase.getCaseNumber());
					}

					/*
					 * Diagnosis
					 */
					
					if (claimExcessValue > 0){
						excessInformation = StringUtil.convertEDCNumber(claimExcessValue,9);
					}
					if (claimApprovedValue > 0 ){
						totalClaimInfo = StringUtil.convertEDCNumber(claimApprovedValue, 9);
					}					
					
					String responseInfo = claimNumber+"#"+claimResult.getClaimNumber()+"#"+addResultInfo+"#"+excessInformation + "#"+totalClaimInfo+"#";
					System.out.println("Additional Info : " + responseInfo);
					claimDto.setClaimNumber(claimResult.getClaimNumber());
					//result.setResultObject(claimDto);
					result.setResult(true);
					result.setAdditionalMessage(responseInfo);
					result.setActionCode(refNumberRandom);
					
					
					if (checkInCase != null){
						result.setReferenceNumber(checkInCase.getCaseReferalNumber());
					}
					
				}
				else {					
					result.setAdditionalMessage("32");
					result.setResult(false);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			result.setResult(false);
			result.setAdditionalMessage("10");
			
		}
		return result;

	}


	public ActionResult reverseVoidClaim( String merchantId, String terminalId, String traceNumber)
			throws Exception {
		ActionResult result = new ActionResult();
		try {
			if (merchantId != null && terminalId != null && traceNumber != null){
				
				Collection<ClaimItem> claimDetailsCollection = new Vector<ClaimItem>();			
				
				
				ActionUser actionUser = new ActionUser();
				User user = new User();
				user.setUsername(merchantId);
				actionUser.setUser(user);
				
				System.out.println("RETRIEVE LAST CLAIM FROM EDC");
				
				String[] eqParam = {"edcTraceNumber","merchantId","terminalId"};
				Object[] eqValue = {traceNumber,merchantId,terminalId};
				Collection<Claim> claimResults = claimService.search(null,null,eqParam,eqValue,false,"claimId",0,1);
				
				Claim claimResult = null;
				
				if (claimResults != null){
					Iterator<Claim> iterator = claimResults.iterator();
					if(iterator.hasNext()){
						claimResult = iterator.next();
					}
				}				
				
				
				if (claimResult != null){
					System.out.println("CLAIM CREATED : " + claimResult.getClaimNumber());	
					Collection<ClaimItem> claimCheckedItem = claimItemService.getBenefitCheckItem(claimResult);					
					Collection<ClaimItem> approvedItem = new Vector<ClaimItem>();
					
					if (claimCheckedItem != null){
						for (Iterator iterator2 = claimCheckedItem.iterator(); iterator2.hasNext();) {
							
							ClaimItem claimItem = (ClaimItem) iterator2.next();
							
							if (claimItem != null){
								claimItem.setClaimItemApprovedValue(claimItem.getClaimItemCoveredValue());
								approvedItem.add(claimItem);
							}							
						}
					}
					
					String res = claimService.approveCheckItemBulk(claimResult.getClaimId(), approvedItem, actionUser);
					result.setResult(true);
					result.setAdditionalMessage(claimResult.getClaimNumber());
					//String responseInfo = claimNumber+"#"+addResultInfo+"#"+excessInformation + "#"+totalClaimInfo+"#";
					//System.out.println("VOID RESPONSE : " + responseInfo);
					
					
				}			
				else {					
					System.out.println("REVERSAL VOID NOT FOUND FOR CLAIM TRACE NUM = " + traceNumber + " TID = " + terminalId + " MID = " + merchantId);
					result.setResult(false);
					result.setAdditionalMessage("32");
				}
			
				
			}
		}
		catch (Exception e){
			result.setResult(false);
			e.printStackTrace();
		}
		return result;

	}
	public ActionResult reverseVoidClaim( String cardNumber, String merchantId, String terminalId, String traceNumber)
		throws Exception {
		ActionResult result = new ActionResult();
		try {
			if (merchantId != null && terminalId != null && traceNumber != null){
				
				
				Collection<ClaimItem> claimDetailsCollection = new Vector<ClaimItem>();			
				
				
				ActionUser actionUser = new ActionUser();
				User user = new User();
				user.setUsername(merchantId);
				actionUser.setUser(user);
				
				System.out.println("RETRIEVE LAST CLAIM FROM EDC FOR REVERSAL VOID");
				
				Member member = memberService.getMemberByCardNumber(cardNumber);
				
				String[] eqParam = {"deletedStatus","terminalId","merchantId","memberId.memberId","voidTraceNumber"};
				Object[] eqValue = {Integer.valueOf(0),terminalId,merchantId,member.getMemberId(),traceNumber};
				
				Collection<Claim> claimResults = claimService.search(null,null,eqParam,eqValue,false,"claimId",0,1);
				
				Claim claimResult = null;
				
				if (claimResults != null){
					Iterator<Claim> iterator = claimResults.iterator();
					if(iterator.hasNext()){
						claimResult = iterator.next();
					}
				}				
				
				
				if (claimResult != null){
					System.out.println("CLAIM FOR REVERSAL VOID : " + claimResult.getClaimNumber());	
					
					Collection<ClaimItem> claimCheckedItem = claimItemService.getBenefitCheckItem(claimResult);					
					Collection<ClaimItem> approvedItem = new Vector<ClaimItem>();
					
					if (claimCheckedItem != null){
						for (Iterator iterator2 = claimCheckedItem.iterator(); iterator2.hasNext();) {
							
							ClaimItem claimItem = (ClaimItem) iterator2.next();
							
							if (claimItem != null){
								claimItem.setClaimItemApprovedValue(claimItem.getClaimItemCoveredValue());
								approvedItem.add(claimItem);
							}							
						}
					}
					
					String res = claimService.approveCheckItemBulk(claimResult.getClaimId(), approvedItem, actionUser);
					result.setResult(true);
					result.setAdditionalMessage(claimResult.getClaimNumber());
					
				}			
				else {		
					System.out.println("REVERSAL VOID NOT FOUND FOR CLAIM TRACE NUM = " + traceNumber + " TID = " + terminalId + " MID = " + merchantId + " CARDNUMBER = " + cardNumber);
					

					result.setResult(false);
					result.setAdditionalMessage("32");
				}
			
				
			}
		}
		catch (Exception e){
			result.setResult(false);
			e.printStackTrace();
		}
		return result;
	
	}

	
	
	public ActionResult voidClaim( String tracenumber,String cardNumber,
			String merchantId, String terminalId) throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		result.setResult(false);
		
		try {

			Member member = memberService.getMemberByCardNumber(cardNumber);
			java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
			
			String[] eqParam = {"deletedStatus","terminalId","merchantId","edcTraceNumber","memberId.memberId"};
			Object[] eqValue = {Integer.valueOf(0),terminalId,merchantId,tracenumber,member.getMemberId()};
			
			
			Collection<Claim> claimList = claimService.search(null,null,eqParam,eqValue,false,"claimId",0,1);
			
			if (claimList != null){
				Iterator<Claim> iterator = claimList.iterator();
				
				if (iterator.hasNext()){
					Claim claim = iterator.next();
					ActionUser user = new ActionUser();
					user.setActionQuery("EDC VOID/REVERSAL");
					
					String[] eqParamItem = {"deletedStatus","claimId.claimId"};
					Object[] eqValueItem = {Integer.valueOf(0),claim.getClaimId()};
					
					String[] required = {"ClaimItem.ItemId"};
					
					int totalItem = claimItemService.getTotal(null,null,eqParamItem,eqValueItem);
					Collection<ClaimItem> claimItemList = claimItemService.search(null,null,eqParamItem,eqValueItem,required,0,totalItem);
					
					String addResultInfo = "";
					
					int idx = 0;
					String excessInformation = " ";
					double claimExcessValue = 0;
					double claimApprovedValue = 0;
				
					
					if (claimItemList != null){
						for (Iterator itemIterator = claimItemList.iterator(); itemIterator
								.hasNext();) {
							
							ClaimItem claimItem = (ClaimItem) itemIterator.next();
							
							String line = "";
							if (idx == 0){
								line = claimItem.getItemId().getItemEDCName()+","+
								StringUtil.convertEDCNumberWithDecimal(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumberWithDecimal(claimItem.getClaimItemApprovedValue(), 9) ;
							}
							else {
								line = "|"+claimItem.getItemId().getItemEDCName()+","+
								StringUtil.convertEDCNumberWithDecimal(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumberWithDecimal(claimItem.getClaimItemApprovedValue(), 9) ;
							}
							if (claimItem.getClaimItemValue() != null && claimItem.getClaimItemApprovedValue() != null){
								claimExcessValue += (claimItem.getClaimItemValue().doubleValue() - claimItem.getClaimItemApprovedValue().doubleValue());
							}
							addResultInfo += line;
							
							idx++;		
							
						}
					}
					
					String totalClaimInfo = "";				
					if (claimExcessValue > 0){
						excessInformation = StringUtil.convertEDCNumberWithDecimal(claimExcessValue,9);
					}
					if (claimApprovedValue > 0 ){
						totalClaimInfo = StringUtil.convertEDCNumberWithDecimal(claimApprovedValue, 9);
					}
					String claimNumber =   claim.getClaimNumber();
					StringTokenizer tokenizer = new StringTokenizer(claimNumber,"/");
					if (tokenizer.hasMoreTokens()){
						claimNumber = tokenizer.nextToken();
					}
					
					
					String responseInfo = claimNumber+"#"+addResultInfo+"#"+excessInformation + "#"+totalClaimInfo+"#";
					System.out.println("VOID RESPONSE : " + responseInfo);
					
					User theUser = new User();
					theUser.setUsername(merchantId);
					user.setUser(theUser);
					
					Claim res = claimService.openClaim(claim.getClaimId(), user);
					System.out.println("START VOIDING CLAIM tracenum = " + tracenumber + " term : " + terminalId + " merchant id = " + merchantId + " memberid = " + member.getMemberId());
					claimService.voidClaim(res.getClaimId(),tracenumber, user);
					
					if (res != null){
						Case checkinCase = res.getCaseId();
						if (checkinCase != null){							
							caseService.reversalCase(checkinCase.getCaseId(),"REVERSE CASE ", user);
						}
					}
					
					result.setAdditionalMessage(responseInfo);
					result.setResult(true);
					//result.setAdditionalMessage(res.);
				}
				else {
					System.out.println("CLAIM VOID / EDC REVERSAL NOT FOUND --> TRACE NUM = " + tracenumber + " CARD NUM = " + cardNumber + " TID = " + terminalId + " MERCH ID = " + merchantId);
					result.setResult(false);
					result.setAdditionalMessage("32");
				}
				
			}
			else {
				System.out.println("CLAIM VOID / EDC REVERSAL NOT FOUND --> TRACE NUM = " + tracenumber + " CARD NUM = " + cardNumber + " TID = " + terminalId + " MERCH ID = " + merchantId);
				result.setResult(false);
				result.setAdditionalMessage("32");
			}

		}
		catch (Exception e){
			e.printStackTrace();
			result.setResult(false);
		}
		return result; 
	}


	
	public ActionResult commitSynchronizeClaim(Integer claimId,
			String username, String password) throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		try {
			Claim claim = claimService.get(claimId);
			
			if (claim != null){
				claim.setReconciled(true);
				claim.setReconciledBy(username);
				claim.setReconcileTime(new java.sql.Timestamp(System.currentTimeMillis()));
				claim.setLastReconciliationTime(new java.sql.Timestamp(System.currentTimeMillis()));
				
				ActionUser actionUser = new ActionUser();
				User theUser = new User();
				theUser.setUsername(username);
				actionUser.setUser(theUser);
				
				Claim res = claimService.update(claim, actionUser);
				if (res != null){
					result.setResult(true);
				}
			}
			else {
				result.setResult(false);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}


	
	public ActionResult commitSynchronizeClaimItem(Integer claimItemId,
			String username, String password) throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		try {
			ClaimItem claimItem = claimItemService.get(claimItemId);
			
			if (claimItem != null){
				claimItem.setIsReconciled(1);
				
				claimItem.setReconciledBy(username);
				claimItem.setReconcileTime(new java.sql.Timestamp(System.currentTimeMillis()));
				
				ActionUser actionUser = new ActionUser();
				User theUser = new User();
				theUser.setUsername(username);
				actionUser.setUser(theUser);
				
				ClaimItem res = claimItemService.update(claimItem, actionUser);
				if (res != null){
					result.setResult(true);
				}
			}
			else {
				result.setResult(false);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public Collection<ClaimItemDto> getReconcileClaimItemList(Integer claimId,
			String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
		Collection<ClaimItemDto> result = new Vector<ClaimItemDto>();
		
		try {
			System.out.println("GENERATING CLAIM ITEM RECONCILE!");
			String[] eqParam = {"deletedStatus","claimId.claimId","isReconciled"};
			Object[] eqValue = {Integer.valueOf(0),claimId,Integer.valueOf(0)};
			
			Collection<ClaimItem> tempClaimList = claimItemService.search(null,null,eqParam,eqValue,0,15);
			if (tempClaimList != null){
				for (Iterator iterator = tempClaimList.iterator(); iterator
						.hasNext();) {
					ClaimItem claim = (ClaimItem) iterator.next();
					
					if (claim != null)
					{	
						System.out.println("GETTING FOR CLAIM : "+ claim.getClaimId().getClaimNumber() + " ITEM : " + claim.getItemId().getItemName());
						ClaimItemDto dto = claim.export();
						result.add(dto);
					}
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}



	public Collection<ClaimDto> getReconcileClaimList(String username,
			String password) throws Exception {
		// TODO Auto-generated method stub
		Collection<ClaimDto> result = new Vector<ClaimDto>();
		
		try {
			String[] eqParam = {"deletedStatus","reconciled"};
			Object[] eqValue = {Integer.valueOf(0),false};
			
			Collection<Claim> tempClaimList = claimService.search(null,null,eqParam,eqValue,0,15);
			if (tempClaimList != null){
				for (Iterator iterator = tempClaimList.iterator(); iterator
						.hasNext();) {
					Claim claim = (Claim) iterator.next();
					
					if (claim != null){
						ClaimDto dto = claim.exportDto();
						result.add(dto);
					}					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();			
		}
		return result;
	}



	public ActionResult commitClaimPaid(Integer claimId,Date paymentDate, String username,
			String password) throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		
		try {
			result.setResult(false);
			
			if (claimId != null){
				Claim claim = claimService.get(claimId);
				
				if (claim != null && claim.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED){
					CaseStatus paidStatus = new CaseStatus();
					paidStatus.setCaseStatusId(Claim.CLAIM_PAID);
					claim.setClaimStatus(paidStatus);
					claim.setPaymentConfirmDate(paymentDate);
					
					ActionUser actionUser = new ActionUser();
					User theUser = new User();
					theUser.setUsername(username);
					actionUser.setUser(theUser);
					
					claimService.update(claim, actionUser);
					
					result.setResult(true);
				}
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public CaseCategoryDto getCaseCategory(String edcCode) {
		// TODO Auto-generated method stub
		CaseCategoryDto result = null;
		
		if (edcCode != null){
			String[] eqParam = {"caseCategoryEdcCode","deletedStatus"};
			Object[] eqValue = {edcCode,Integer.valueOf(0)};
			
			try {
				Collection<CaseCategory> caseList = caseCategoryService.search(null,null,eqParam,eqValue,0,1);
				
				if (caseList != null){
					Iterator<CaseCategory> iterator = caseList.iterator();
					
					if (iterator.hasNext()){
						CaseCategory cc = iterator.next();
						
						if (cc != null){
							result = new CaseCategoryDto();
							
							result.setCaseCategoryName(cc.getCaseCategoryName());
							result.setCaseCategoryCode(cc.getCaseCategoryCode());
							result.setCaseCategoryEdcCode(cc.getCaseCategoryEdcCode());
							
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return result;
	}


	@Override
	public String ping() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("PING : " + new java.sql.Timestamp(System.currentTimeMillis()));
		return "HALLO";
	}


	@Override
	public ActionResult inquiryReference(String cardNum, String refNum,
			String merchantId, String terminalId) throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		result.setResult(false);
		
		try {
		
			String responseLine = "";
			Provider provider = providerService.getByProviderCode(merchantId);
			Member member = memberService.getMemberByCardNumber(cardNum);
			
			if (member != null && provider != null){
				Collection<Poliklinik> poliList = poliklinikService.getAll();
				
				if (poliList != null){
					int idx = 0;
					for (Iterator iterator = poliList.iterator(); iterator
							.hasNext();) {
						Poliklinik poliklinik = (Poliklinik) iterator.next();
						
						if (poliklinik != null){
							
							if (idx == 0){
								responseLine += poliklinik.getPoliklinikCode()+"#"+poliklinik.getPoliklinikEdcCode();
							}
							else {
								responseLine += "|"+poliklinik.getPoliklinikCode()+"#"+poliklinik.getPoliklinikEdcCode();
							}
						}						
						idx++;
					}
				}
				result.setResult(true);
				result.setAdditionalMessage(responseLine);
				result.setActionCode(refNum);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public ActionResult referCase(String cardNum, String refNum, String poliId,
			String merchantId, String terminalId, String traceNumber)
			throws Exception {
		// TODO Auto-generated method stub
		ActionResult result = new ActionResult();
		result.setResult(false);
		result.setAdditionalMessage("");
		
		try {
			if (cardNum != null && refNum != null && poliId != null){
				Member member = memberService.getMemberByCardNumber(cardNum);
				Provider provider = providerService.getByProviderCode(merchantId);
				
				Case previousCase = new Case();
				
				String[] eqParam = {"providerId.providerId","memberId.memberId","deletedStatus","electronicRefNumber"};
				Object[] eqValue = {provider.getProviderId(),member.getMemberId(), Integer.valueOf(0),refNum};
				
				Collection<Case> caseList = caseService.search(null,null,eqParam,eqValue,0,1);
				
				if (caseList != null){
					Iterator<Case> iterator = caseList.iterator();
					
					if (iterator.hasNext()){
						previousCase = iterator.next();
					}
				}
				
				String refNumber = RandomStringUtils.randomNumeric(10);
				String caseNumber = "";
				
				Poliklinik poli = poliklinikService.getPoliByEdcCode(poliId);
				if (poli != null){
					previousCase.setReferalStatus(1);
					previousCase.setReferedPoliklinikId(poli);
					previousCase.setCaseReferalNumber(refNumber);
					
					CaseStatus caseStatus = new CaseStatus();
					caseStatus.setCaseStatusId(CaseStatus.CASE_REFERED);
					
					previousCase.setCaseStatusId(caseStatus);
					
					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(terminalId);
					actionUser.setUser(user);
					StringTokenizer tokenize = new StringTokenizer(previousCase.getCaseNumber(),"/");
					caseNumber = tokenize.nextToken();
					
					caseService.update(previousCase, actionUser);
				}
				
				String responseLine = formatReferNumber(refNumber)+"#"+poli.getPoliklinikName();
				result.setAdditionalMessage(responseLine);
				result.setResult(true);
				result.setActionCode(caseNumber);
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	private String formatReferNumber(String referNumber){
		String result = "";
		
		try {
			if (referNumber != null){
				result = referNumber.substring(0, 2) + " " + referNumber.substring(3, 5) + " " + referNumber.substring(6,9);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ActionResult registerClaimSys(ClaimSCDto claimDto,
			Collection<ClaimItemDto> claimDetails, String merchantId,
			String terminalId, String traceNumber, String referenceNumber)
			throws Exception {

		ActionResult result = new ActionResult();
		try {
			if (claimDetails != null){
				
				Claim existingClaim = getCaseClaim(claimDto, claimDetails, merchantId);
				
				if (existingClaim == null){
				
					Claim claim = new Claim();
					CaseCategory cc = new CaseCategory();
					
					System.out.println("CLAIM CATEGORY : " + claimDto.getServiceCategory() + " KALO DENTAL : " + CaseCategory.DENTAL_STR);
					if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.DENTAL_STR)){
						cc.setCaseCategoryId(CaseCategory.DENTAL);
						cc.setCaseCategoryCode(CaseCategory.DENTAL_STR);
						claim.setAdmissionDate(claimDto.getDischargedDate());
					}
					else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.INPATIENT_STR)){
						cc.setCaseCategoryId(CaseCategory.INPATIENT);
						cc.setCaseCategoryCode(CaseCategory.INPATIENT_STR);
					}
					else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MATERNITY_STR)){
						cc.setCaseCategoryId(CaseCategory.MATERNITY);
						cc.setCaseCategoryCode(CaseCategory.MATERNITY_STR);
					}
					else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.OPTICAL_STR)){
						cc.setCaseCategoryId(CaseCategory.OPTICAL);
						cc.setCaseCategoryCode(CaseCategory.OPTICAL_STR);
						claim.setAdmissionDate(claimDto.getDischargedDate());
					}
					else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.OUTPATIENT_STR)){
						cc.setCaseCategoryId(CaseCategory.OUTPATIENT);
						cc.setCaseCategoryCode(CaseCategory.OUTPATIENT_STR);
						claim.setAdmissionDate(claimDto.getDischargedDate());
					}
					else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.SPECIALIST_STR)){
						cc.setCaseCategoryId(CaseCategory.SPECIALIST);
						cc.setCaseCategoryCode(CaseCategory.SPECIALIST_STR);
						claim.setAdmissionDate(claimDto.getDischargedDate());
					}
					else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MISC_STR)){
						cc.setCaseCategoryId(CaseCategory.MISC);
						cc.setCaseCategoryCode(CaseCategory.MISC_STR);
					}
					else if (claimDto.getServiceCategory().equalsIgnoreCase(CaseCategory.MEDICAL_CHECK_UP_STR)){
						cc.setCaseCategoryId(CaseCategory.MEDICAL_CHECK_UP);
						cc.setCaseCategoryCode(CaseCategory.MEDICAL_CHECK_UP_STR);
						claim.setAdmissionDate(claimDto.getDischargedDate());
					}
					
					Member member = memberService.getMemberByCardNumber(claimDto.getMemberNumber());
					
					System.out.println("Geeting Diagnosis For : " + claimDto.getDiagnosis1Code() + " -- " + claimDto.getDiagnosis2Code() + " -- " + claimDto.getDiagnosis3Code());
					Provider provider = providerService.getByProviderCode(merchantId);					
					System.out.println("DB Member No : " + member.getCustomerNumber() + " name : " + member.getFirstName() + " MEMBER SC = " + claimDto.getMemberNumber() + " MID = " + merchantId);
					
					String[] eqProductParam = {"memberId.memberId","productId.caseCategory.caseCategoryId","deletedStatus","memberProductStatus.statusId"};
					Object[] eqProductValue = {member.getMemberId(),cc.getCaseCategoryId(),Integer.valueOf(0),SubscriptionStatus.ACTIVE};
					
					Collection<MemberProduct> productList = memberProductService.search(null,null,eqProductParam,eqProductValue,0,1);
					
					if (productList != null){
						Iterator<MemberProduct> iterator = productList.iterator();
						if (iterator.hasNext()){
							MemberProduct memberProduct = iterator.next();
							if (memberProduct != null && memberProduct.getAnnualBenefit() != null && memberProduct.getActualBenefitLimit() != null){
								if (memberProduct.getAnnualBenefit().intValue() != -1 && memberProduct.getActualBenefitLimit().doubleValue() == 0.0){
									result.setResult(false);
									result.setAdditionalMessage("18");
									
									return result;									
								}
							}
						}
					}
					if (provider != null){
						System.out.println("Provider nya : " + provider.getProviderName());
					}
					else {
						// check validity for Provider (RC 15 / 23 )
						result.setResult(false);
						result.setAdditionalMessage("15");
						return result;						
					}					
					
					/**
					 * checking claim condition for response code
					 */
					Policy activePolicy = policyService.getGroupActivePolicy(member.getMemberGroupId().getMemberGroupId(), member.getMemberGroupId().getClientId().getClientId());
					
					// CHECK RC 28
					System.out.println("POLICY = " + activePolicy);
					
					if (activePolicy != null ){
						if (activePolicy.getCardTypeId() != null && activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SHOW_CARD){
							result.setResult(false);
							result.setAdditionalMessage("28");
							return result;
						}

						// PASS RC 28
						
						// CHECK RC 29
						
						if (activePolicy.getCardTypeId() != null && (activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SMART_CARD || 
								activePolicy.getCardTypeId().getCardTypeId().intValue() == CardType.SWIPE_CARD)){
							
							if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_CLIENT_USAGE_TYPE){

							}
							else if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_GROUP_USAGE_TYPE){
								String[] eqProviderParam = {"memberGroupId.memberGroupId","providerId.providerId"};
								Object[] eqProviderValue = {member.getMemberGroupId().getMemberGroupId(),provider.getProviderId()};
								
								int total = memberGroupProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
								
								if (total == 0){
									result.setResult(false);
									result.setAdditionalMessage("29");
									return result;
								}
							}
							else if (activePolicy.getProviderAllocationType().intValue() == Policy.PROVIDER_MEMBER_USAGE_TYPE){
								String[] eqProviderParam = {"memberId.memberId","providerId.providerId"};
								Object[] eqProviderValue = {member.getMemberId(),provider.getProviderId()};
								
								int total = memberProviderService.getTotal(null,null,eqProviderParam,eqProviderValue);
								
								if (total == 0){
									result.setResult(false);
									result.setAdditionalMessage("29");
									return result;
								}
							}
						}
					}
					
					// check for data validity (RC 17)
					/** per request untuk disable Maternity back-dated
					 * 
					 */
					/**
					if (cc.getCaseCategoryId().intValue() == CaseCategory.INPATIENT 
							|| cc.getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
					*/
					
					if (cc.getCaseCategoryId().intValue() == CaseCategory.INPATIENT){
						System.out.println("CHECKING RC 17 DISCHARGE DATE = " + claimDto.getDischargedDate().toString() + 
								" CURRENT : " + new java.sql.Date(System.currentTimeMillis()).toString());
						if (claimDto.getDischargedDate() != null){
							if (claimDto.getDischargedDate().after(new java.sql.Date(System.currentTimeMillis()))){
								System.out.println("RC 17 KENA DISCHARGE DATE > CURRENT");
								result.setResult(false);
								result.setAdditionalMessage("17");
								
								return result;	
							}
						}
						if (claimDetails != null){
							double itemAmount = 0.0;
							for (Iterator iterator = claimDetails.iterator(); iterator.hasNext();) {
								
								ClaimItemDto claimItemDto = (ClaimItemDto) iterator.next();
								if (claimItemDto != null){
									if (claimItemDto.getClaimItemAmount() != null){
										double tmpItemAmount = claimItemDto.getClaimItemAmount().doubleValue();
										
										if (tmpItemAmount > itemAmount){
											itemAmount = tmpItemAmount;
										}
									}
								}					
							}
							
							if (claimDto.getTotalDays() != null){
								if (itemAmount > claimDto.getTotalDays()){								
									result.setResult(false);
									result.setAdditionalMessage("17");
									return result;
								}
							}
						}
					}
					else if (cc.getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
						int totalDay = Days.daysBetween(new DateTime(claimDto.getAdmissionDate().getTime()), new DateTime(System.currentTimeMillis())).getDays() + 1;
						if ( claimDto.getTotalDays() != null && claimDto.getTotalDays() > totalDay){
							System.out.println("RC 17 KENA DISCHARGE DATE > CURRENT");
							result.setResult(false);
							result.setAdditionalMessage("17");
							
							return result;
						}
					}
					else {
						if (claimDetails != null){
							double itemAmount = 0.0;
							for (Iterator iterator = claimDetails.iterator(); iterator.hasNext();) {
								
								ClaimItemDto claimItemDto = (ClaimItemDto) iterator.next();
								if (claimItemDto != null){
									if (claimItemDto.getClaimItemAmount() != null){
										double tmpItemAmount = claimItemDto.getClaimItemAmount().doubleValue();
										
										if (tmpItemAmount > itemAmount){
											itemAmount = tmpItemAmount;
										}
									}
								}					
							}				
							
							if (itemAmount > 1){								
								result.setResult(false);
								result.setAdditionalMessage("17");
								return result;
							}
							
						}
					}
					// check RC 17 Passed
					// check for benefit validity (RC 24 / RC 26);
					
					System.out.println("CLAIM DETAILS = " + claimDetails);
					
					if (claimDetails != null){
						for (Iterator iterator = claimDetails.iterator(); iterator.hasNext();) {
							
							
							ClaimItemDto claimItemDto = (ClaimItemDto) iterator
									.next();
							
							Item item = itemService.getItemByEdcCode(claimItemDto.getItemCode());
							
							System.out.println("ITEM = " + item);
							if (item == null){
								result.setResult(false);
								result.setAdditionalMessage("26");
								return result;
							}							
						}
						// RC 26 Passed
						
						for (Iterator iterator = claimDetails.iterator(); iterator.hasNext();) {
					
							ClaimItemDto claimItemDto = (ClaimItemDto) iterator
									.next();
							
							String[] eqBenParam = {"deletedStatus","memberId.memberId","itemCategoryId.itemCategoryEDCCode","caseCategoryId.caseCategoryId"};
							Object[] eqBenValue = {Integer.valueOf(0),member.getMemberId(),claimItemDto.getItemCode(),cc.getCaseCategoryId()};
							int totalBenefit = memberBenefitService.getTotal(null,null,eqBenParam,eqBenValue);
							
							System.out.println("TOTAL BENEFIT = " + totalBenefit);
							
							if (totalBenefit == 0){
								result.setResult(false);
								result.setAdditionalMessage("24");
								return result;
							}
							
						}
						// RC 24 Passed						
					}
					
					claim.setAdmissionDate(claimDto.getAdmissionDate());
					claim.setTerminalId(terminalId);
					claim.setMerchantId(merchantId);
					claim.setEdcTraceNumber(traceNumber);
					claim.setClaimDate(claimDto.getClaimDate());
					claim.setOtherNumber(referenceNumber);
					
					claim.setCaseCategoryId(cc);				
					claim.setClaimDate(claimDto.getClaimDate());
					claim.setDischargeDate(claimDto.getDischargedDate());
					claim.setMemberId(member);
					
					if (member != null && member.getClientId() != null){
						claim.setClientId(member.getClientId().getClientId());
					}
					
					CaseStatus status = new CaseStatus();
					status.setCaseStatusId(1);
					claim.setClaimStatus(status);
					
					System.out.println ("Setting Claim Type");
					ClaimType claimType = new ClaimType();
					claimType.setClaimTypeId(ClaimType.CASHLESS);
					claim.setClaimTypeId(claimType);
					
					
					claim.setProviderId(provider);
					claim.setProviderName(provider.getProviderName());
					boolean isDiagnosisExcluded = false;
					
					
					System.out.println ("setting claim diagnosis code : " + claimDto.getDiagnosis1Code() + " | ");
					if (claimDto.getDiagnosis1Code() != null && !claimDto.getDiagnosis1Code().equalsIgnoreCase("")){
						System.out.println("nyari diagnosis : " + claimDto.getDiagnosis1Code() + " di database ! ");
						
						Diagnosis diagnosis = diagnosisService.getDiagnosisByAlternateCode(claimDto.getDiagnosis1Code());
						
						if (diagnosis != null){
							System.out.println("dapet nih diagnosis nya : " + diagnosis.getDiagnosisName());
							claim.setDiagnosisId(diagnosis);
					
							claim.setDiagnosis1Code(diagnosis.getDiagnosisCode());
							
							String[] eqParam = {"memberId.memberId","diagnosisId.diagnosisId","diagnosisStatus"};
							Object[] eqValue = {claim.getMemberId().getMemberId(),diagnosis.getDiagnosisId(),Integer.valueOf(0)};
							int total = memberDiagnosisService.getTotal(null,null,eqParam,eqValue);
							
							if (total > 0){
								System.out.println("DIAGNOSIS 1 IS EXCLUDED !");
								isDiagnosisExcluded = true;
							}
						}
						else {
							// RC 16
							result.setResult(false);
							result.setAdditionalMessage("16");
							return result;
						}
					}
					if (claimDto.getDiagnosis2Code() != null && !claimDto.getDiagnosis2Code().equalsIgnoreCase("")){
						System.out.println("nyari diagnosis 2 : " + claimDto.getDiagnosis2Code() + " di database ! ");
						Diagnosis diagnosis = diagnosisService.getDiagnosisByAlternateCode(claimDto.getDiagnosis2Code());
						if (diagnosis != null){
							System.out.println("dapet nih diagnosis 2 nya : " + diagnosis.getDiagnosisName());
							
							claim.setDiagnosis2Id(diagnosis);
							claim.setDiagnosis2Code(diagnosis.getDiagnosisCode());
							
							String[] eqParam = {"memberId.memberId","diagnosisId.diagnosisId","diagnosisStatus"};
							Object[] eqValue = {claim.getMemberId().getMemberId(),diagnosis.getDiagnosisId(),Integer.valueOf(0)};
							int total = memberDiagnosisService.getTotal(null,null,eqParam,eqValue);
							
							if (total > 0){
								System.out.println("DIAGNOSIS 2 IS EXCLUDED !");
								isDiagnosisExcluded = true;
							}
						}
					}
					
					if (claimDto.getDiagnosis3Code() != null && !claimDto.getDiagnosis3Code().equalsIgnoreCase("")){
						System.out.println("nyari diagnosis 3 : " + claimDto.getDiagnosis3Code() + " di database ! ");
						Diagnosis diagnosis = diagnosisService.getDiagnosisByAlternateCode(claimDto.getDiagnosis3Code());
						if (diagnosis != null){
							System.out.println("dapet nih diagnosis 3 nya : " + diagnosis.getDiagnosisName());
							claim.setDiagnosis3Id(diagnosis);
							claim.setDiagnosis3Code(diagnosis.getDiagnosisCode());
							
							String[] eqParam = {"memberId.memberId","diagnosisId.diagnosisId","diagnosisStatus"};
							Object[] eqValue = {claim.getMemberId().getMemberId(),diagnosis.getDiagnosisId(),Integer.valueOf(0)};
							int total = memberDiagnosisService.getTotal(null,null,eqParam,eqValue);
							
							if (total > 0){
								System.out.println("DIAGNOSIS 2 IS EXCLUDED !");
								isDiagnosisExcluded = true;
							}
						}
					}
					System.out.println("checking claim item iterator");
					
					Iterator<ClaimItemDto> iterator = claimDetails.iterator();
					Collection<ClaimItem> claimDetailsCollection = new Vector<ClaimItem>();
					Collection<ClaimItem> parentCollection = new Vector<ClaimItem>();
					Collection<ClaimItem> childCollection = new Vector<ClaimItem>();
					Collection<ClaimItem> claimDetailList = new Vector<ClaimItem>();
					
					while (iterator.hasNext()){
						ClaimItemDto itemDto = iterator.next();
						
						if (itemDto != null){
							
							String[] eqParam = {"deletedStatus","itemCategoryId.itemCategoryEDCCode","itemEDCCode"};
							Object[] eqValue = {Integer.valueOf(0),itemDto.getItemCode(),itemDto.getItemCode()};
							
							Collection<Item> itemList = itemService.search(null,null,eqParam,eqValue,0,1);
							
							
							Item item = null;
							if (itemList != null){
								Iterator<Item> itemIterator = itemList.iterator();
								
								if (itemIterator.hasNext()){
									item = itemIterator.next();
								}
							}
							
							if (item != null){
								ClaimItem claimItem = new ClaimItem();
								claimItem.setItemId(item);
								claimItem.setItemCode(itemDto.getItemCode());
								claimItem.setClaimItemAmount(itemDto.getClaimItemAmount());
								claimItem.setClaimItemValue(itemDto.getClaimItemValue());
								CaseStatus claimItemStatus = new CaseStatus();
								claimItemStatus.setCaseStatusId(ClaimItem.CLAIM_ITEM_APPROVED);
								claimItem.setClaimItemStatus(claimItemStatus);
					
								if (item != null){
									System.out.println("adding item to claim : " + item.getItemName()  + " item code : " + item.getItemCode());
								}
								
								
								
								claimDetailsCollection.add(claimItem);
							}
						}
					}
					
					
					if (claimDetailsCollection != null && claim.getCaseCategoryId().getCaseCategoryId().intValue() == CaseCategory.OUTPATIENT){
						Configuration configuration = configurationService.getSystemConfiguration();
						
						if (configuration != null){
							Properties props = new Properties();
							FileInputStream instream = new FileInputStream(configuration.getEdcFilterFileName()); 
							props.load(instream);
							
							claimDetailList = ClaimItemUtil.getAllowedEDCItemList(claimDetailsCollection, props, configuration.getEdcFilterDelimiter());
							//claimDetailList = ClaimItemUtil.getAllowedEDCItemList(claimDetailsCollection);	
						}
						
					}
					else {
						claimDetailList = claimDetailsCollection;
					}
					
					ActionUser actionUser = new ActionUser();
					User user = new User();
					user.setUsername(merchantId);
					actionUser.setUser(user);
					
					System.out.println("CREATING CLAIM FROM EDC");
					
					
					Claim claimResult = claimService.createClaimItemEDC(claim, claimDetailList, 0.0, actionUser);
					
					
					if (claimResult != null){
						System.out.println("CLAIM CREATED : " + claimResult.getClaimNumber());			
		
						Collection<ClaimItem> claimCheckedItem = claimItemService.getBenefitCheckItem(claimResult);
						
						Collection<ClaimItem> approvedItem = new Vector<ClaimItem>();
						
						if (claimCheckedItem != null){
							for (Iterator iterator2 = claimCheckedItem.iterator(); iterator2
									.hasNext();) {
								
								ClaimItem claimItem = (ClaimItem) iterator2.next();
								
								if (claimItem != null){
									if (!isDiagnosisExcluded){
										claimItem.setClaimItemApprovedValue(claimItem.getClaimItemCoveredValue());									
									}
									else {
										claimItem.setClaimItemApprovedValue(0.0);
										claimItem.setBenefitCheckRemarks("Diagnosis Excluded " );
									}
									approvedItem.add(claimItem);
								}							
							}
						}
						
						String res = claimService.approveCheckItemBulk(claimResult.getClaimId(), approvedItem, actionUser);
						Collection<ClaimItem> details = claimItemService.getClaimItem(claimResult.getClaimId());
						
						String addResultInfo = "";
						
						int idx = 0;
						String excessInformation = " ";
						String totalClaimInfo = " " ;
						double claimExcessValue = 0;
						double claimApprovedValue = 0;
						
						for (Iterator iterator2 = details.iterator(); iterator2.hasNext();) {
							
							ClaimItem claimItem = (ClaimItem) iterator2.next();
							
							String line = "";
							if (idx == 0){
								line = claimItem.getItemId().getItemEDCName()+","+
								StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
							}
							else {
								
								line = "|"+claimItem.getItemId().getItemEDCName()+","+
								StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
								+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
							}
							if (claimItem.getClaimItemValue() != null && claimItem.getClaimItemApprovedValue() != null){
								claimApprovedValue+=claimItem.getClaimItemApprovedValue().doubleValue();
								claimExcessValue += (claimItem.getClaimItemValue().doubleValue() - claimItem.getClaimItemApprovedValue().doubleValue());
							}
							addResultInfo += line;
							
							idx++;						
						}
						/*
						 * Diagnosis
						 */
						
						if (claimExcessValue > 0){
							excessInformation = StringUtil.convertEDCNumber(claimExcessValue,9);
						}
						if (claimApprovedValue > 0 ){
							totalClaimInfo = StringUtil.convertEDCNumber(claimApprovedValue, 9);
						}
						if (claim.getDiagnosisId() != null){
							
						}
						if (claim.getDiagnosis2Id() != null){
							
						}
						if (claim.getDiagnosis3Id() != null){
							
						}
						System.out.println("setting claim DTO before leaving method !");
						
						
						String claimNumber =   claimResult.getClaimNumber();
						StringTokenizer tokenizer = new StringTokenizer(claimNumber,"/");
						if (tokenizer.hasMoreTokens()){
							claimNumber = tokenizer.nextToken();
						}
						
						String responseInfo = claimNumber+"#"+addResultInfo+"#"+excessInformation + "#"+totalClaimInfo+"#";
						System.out.println("Additional Info : " + responseInfo);
						claimDto.setClaimNumber(claimResult.getClaimNumber());
						//result.setResultObject(claimDto);
						result.setResult(true);
						result.setAdditionalMessage(responseInfo);
						
					}			
					else {					
						result.setResult(false);
					}
				}
				else {
					Collection<ClaimItem> details = claimItemService.getClaimItem(existingClaim.getClaimId());
					
					String addResultInfo = "";
					
					int idx = 0;
					String excessInformation = " ";
					double claimExcessValue = 0;
					double claimApprovedValue = 0;
					for (Iterator iterator2 = details.iterator(); iterator2
							.hasNext();) {
						
						ClaimItem claimItem = (ClaimItem) iterator2.next();
						
						String line = "";
						if (idx == 0){
							line = claimItem.getItemId().getItemEDCName()+","+
							StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
							+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
						}
						else {
							line = "|"+claimItem.getItemId().getItemEDCName()+","+
							StringUtil.convertEDCNumber(claimItem.getClaimItemValue(), 9)+","
							+StringUtil.convertEDCNumber(claimItem.getClaimItemApprovedValue(), 9) ;
						}
						if (claimItem.getClaimItemValue() != null && claimItem.getClaimItemApprovedValue() != null){
							claimExcessValue += (claimItem.getClaimItemValue().doubleValue() - claimItem.getClaimItemApprovedValue().doubleValue());
						}
						addResultInfo += line;
						
						idx++;						
					}
					/*
					 * Diagnosis
					 */
					
					if (claimExcessValue > 0){
						excessInformation = StringUtil.convertEDCNumber(claimExcessValue,9);
					}
					if (existingClaim.getDiagnosisId() != null){
						
					}
					if (existingClaim.getDiagnosis2Id() != null){
						
					}
					if (existingClaim.getDiagnosis3Id() != null){
						
					}
					System.out.println("setting claim DTO before leaving method !");
					
					
					String claimNumber =   existingClaim.getClaimNumber();
					StringTokenizer tokenizer = new StringTokenizer(claimNumber,"/");
					if (tokenizer.hasMoreTokens()){
						claimNumber = tokenizer.nextToken();
					}
					
					String responseInfo = claimNumber+"#"+addResultInfo+"#"+excessInformation + "#";
					System.out.println("Additional Info : " + responseInfo);
					claimDto.setClaimNumber(existingClaim.getClaimNumber());
					//result.setResultObject(claimDto);
					result.setResult(true);
					result.setAdditionalMessage(responseInfo);
	
				}
			}
		}
		catch (Exception e){
			result.setResult(false);
			result.setAdditionalMessage("10");
			e.printStackTrace();
		}
		return result;
	}
	
	public Collection<ClaimExchangeDto> getClaimExchange(Integer claimId,
			String username, String password) throws Exception {
		// TODO Auto-generated method stub
		
		Collection<ClaimExchangeDto> result = new Vector<ClaimExchangeDto>();
		
		try {
			System.out.println("GENERATING CLAIM EXCHANGE");
			String[] eqParam = {"deletedStatus","claimId"};
			Object[] eqValue = {Integer.valueOf(0),claimId};
			
			String[] required = {
					"Claim.MemberId", "Claim.MemberId.MemberGroupId", 
					"Claim.BatchClaimId", "Claim.ClaimStatus", 
					"Claim.DiagnosisId", "Claim.Diagnosis2Id", "Claim.Diagnosis3Id",
					"Claim.PaymentId", "Claim.CaseCategoryId", "Claim.PolicyId", "Claim.ProductId", "Claim.ProviderId",
					"Claim.ClaimCurrencyId"
					};
			
			//Collection<Claim> tempClaimList = claimService.search(null,null,eqParam,eqValue,0,15);
			Collection<Claim> tempClaimList = claimService.search(null, null, eqParam, eqValue, required, 0, 15);
			
			
			if (tempClaimList != null){
				for (Iterator iterator = tempClaimList.iterator(); iterator.hasNext();) {
					Claim claim = (Claim) iterator.next();
					
					if (claim != null)
					{	
						System.out.println("Create Claim Exchange for Claim ID : "+ claim.getClaimId() + " , Claim Number : " + claim.getClaimNumber());
						//create the header
						ClaimExchangeDto dto = claim.exportExchangeDto();
						
						//now create the details fufufufu
						String[] eqParamItem = {"deletedStatus","claimId.claimId"};
						Object[] eqValueItem = {Integer.valueOf(0),claimId};
						
						String[] requiredItem = {
								"ClaimItem.ClaimId", "ClaimItem.ItemId"
								};
						Collection<ClaimItem> tempClaimItemList = claimItemService.search(null, null, eqParamItem, eqValueItem, requiredItem, 0, 15);
						
						Collection<ClaimExchangeDetailDto> claimDetail = new Vector<ClaimExchangeDetailDto>();
						
						if (tempClaimItemList != null){
							for (Iterator iteratorItem = tempClaimItemList.iterator(); iteratorItem.hasNext();) {
								ClaimItem claimItem = (ClaimItem) iteratorItem.next();
								if(claimItem != null){
									System.out.println("Create Claim Exchange Detail for Claim ID : "+ claim.getClaimId() + " , Claim Item : " + claimItem.getItemCode());
									
									ClaimExchangeDetailDto detailDto = claimItem.exportExchangeDetail();
									
									claimDetail.add(detailDto);
								}
							}
								
						}
						
						dto.setClaimExchangeDetailList(claimDetail);
						
						result.add(dto);
					}
					
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
//	public void claimPaymentActivity(PaymentTransactionClientLogDto log) {
//		// TODO Auto-generated method stub
//		
//		try {
//			if (log != null) {
//				PaymentTransactionLog paymentLog = new PaymentTransactionLog();
//				paymentLog.setAccountName(log.getAccountName());
//				paymentLog.setAccountNumber(log.getAccountNumber());
//				paymentLog.setAmountPaid(log.getAmountPaid());
//				paymentLog.setBankCode(log.getBankCode());
//				paymentLog.setBankName(log.getBankName());
//				paymentLog.setCurrency(log.getCurrency());
//				paymentLog.setClaimNumber(log.getClaimNumber());
//				paymentLog.setNoVoucherNumber(log.getNoVoucherNumber());
//				paymentLog.setPaymentDate(log.getPaymentDate());
//				
//				ActionUser user = new ActionUser();
//				
//				User usr = new User();
//				usr.setUsername("payment-transaction");
//				user.setUser(usr);
//				
//				HttpServletRequest request = XFireServletController.getRequest();
//				
//				if (request != null){
//					String ip = request.getRemoteAddr();
//					String session = request.getRequestedSessionId();
//					
//					user.setIpAddress(ip);
//					user.setLoginSession(session);
//				}
//				
//				paymentTransactionLogService.create(paymentLog, user);
//				
//			}
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	public PaymentResult claimPaymentActivity(PaymentTransactionClientLogDto log, String username) throws Exception{
		// TODO Auto-generated method stub
		PaymentResult result = new PaymentResult();
		try {
			if (log != null) {
				if(log.getAccountName() == ""){
					result.setErrorMessage("Account Name kosong");
					result.setStatusProses(2);
				}else if(log.getAccountNumber() == null){
					result.setErrorMessage("Account Number kosong");
					result.setStatusProses(2);
				}
				else if(log.getAmountPaid() == null){
					result.setErrorMessage("Amount Paid kosong");
					result.setStatusProses(2);
				}
				else if(log.getBankCode() == null){
					result.setErrorMessage("Bank Code kosong");
					result.setStatusProses(2);
				}
				else if(log.getBankName() == ""){
					result.setErrorMessage("Bank Name kosong");
					result.setStatusProses(2);
				}
				else if(log.getCurrency() == ""){
					result.setErrorMessage("Currency kosong");
					result.setStatusProses(2);
				}
				else if(log.getClaimNumber() == ""){
					result.setErrorMessage("Claim Number kosong");
					result.setStatusProses(2);
				}else if(log.getNoVoucherNumber() == ""){
					result.setErrorMessage("No Voucher Number kosong");
					result.setStatusProses(2);
				}else if(log.getPaymentDate() == null){
					result.setErrorMessage("Payment Date kosong");
					result.setStatusProses(2);
				}else{
				
				
				Claim claim = claimService.searchUnique("claimNumber", log.getClaimNumber());
				
					if(claim != null){
						
						PaymentTransactionLog paymentTransactionLog = paymentTransactionLogService.searchUnique("claimNumber", log.getClaimNumber());
						if(paymentTransactionLog == null){
						
							PaymentTransactionLog paymentLog = new PaymentTransactionLog();
							paymentLog.setAccountName(log.getAccountName());
							paymentLog.setAccountNumber(log.getAccountNumber());
							paymentLog.setAmountPaid(log.getAmountPaid());
							paymentLog.setBankCode(log.getBankCode());
							paymentLog.setBankName(log.getBankName());
							paymentLog.setCurrency(log.getCurrency());
							paymentLog.setClaimNumber(log.getClaimNumber());
							paymentLog.setNoVoucherNumber(log.getNoVoucherNumber());
							paymentLog.setPaymentDate(log.getPaymentDate());
							
							ActionUser user = new ActionUser();
							
			//				HttpServletRequest request = XFireServletController.getRequest();
							
							User usr = new User();
							usr.setUsername(username);
							user.setUser(usr);
							
			//				if (request != null){
			//					String ip = request.getRemoteAddr();
			//					String session = request.getRequestedSessionId();
			//					
			//					user.setIpAddress(ip);
			//					user.setLoginSession(session);
			//				}
							
							paymentTransactionLogService.create(paymentLog, user);
							
							result.setErrorMessage("Data berhasil diinput");
							result.setStatusProses(1);
						}else{
							result.setErrorMessage("Data claim number '"+log.getClaimNumber()+"' sudah ada payment nya");
							result.setStatusProses(2);
						}
					}else{
						result.setErrorMessage("Data claim number '"+log.getClaimNumber()+"' tidak ada");
						result.setStatusProses(2);
					}
				}
			}else{
				result.setErrorMessage("Data yang diinput tidak ada");
				result.setStatusProses(2);
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
//			result.setErrorMessage("Data tidak berhasil diinput, error = " + e);
//			result.setStatusProses(2);
		}
		
		result.setDateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		result.setPaymentVoucherNumber(result.getStatusProses() == 2 ? "" : log.getNoVoucherNumber());
		
		return result;
		
		
	}
	

	@SuppressWarnings("null")
	public void claimPaymentPaidActivity(Collection<PaymentDto> paymentDtoList, String[] claimNumberList ) {
		// TODO Auto-generated method stub
		
		try {
			
			Iterator<PaymentDto> paymentDtoIterator = paymentDtoList.iterator();
			String[] claimIdList = null ;
//			Iterator<String> claimDtoIterator = claimDtoList.iterator();
			if (claimNumberList != null){
				
				for (int i = 0; i < claimNumberList.length; i++) {
					if (claimNumberList[i] != null && !claimNumberList[i].equalsIgnoreCase("")){
						
				String[] eqParam = {"deletedStatus","claimNumber"};
				Object[] eqValue = {Integer.valueOf(0),claimNumberList[i]};
				
				//Collection<Claim> tempClaimList = claimService.search(null,null,eqParam,eqValue,0,15);
//				Collection<Claim> tempClaimList = claimService.search(null, null, eqParam, eqValue, 0, 15);
//				
//				int jumlah = 0;
//				if (tempClaimList != null){
//					for (Iterator iterator = tempClaimList.iterator(); iterator.hasNext();) {
//						Claim claim = (Claim) iterator.next();
//						
//						claimId[jumlah] = String.valueOf(claim.getClaimId());
//						
//						jumlah++;
//					}		
//				} 
				
				Claim claim = claimService.searchUnique(eqParam, eqValue, 0, 1);
				
				
				claimIdList[i] = String.valueOf(claim.getClaimId());
				
			  }
		 }
		}
			if (paymentDtoIterator != null) {

				
				ActionUser user = new ActionUser();
				
				User usr = new User();
				usr.setUsername("payment-transaction");
				user.setUser(usr);
				
				HttpServletRequest request = XFireServletController.getRequest();
				
				if (request != null){
					String ip = request.getRemoteAddr();
					String session = request.getRequestedSessionId();
					
					user.setIpAddress(ip);
					user.setLoginSession(session);
				}
			
			while(paymentDtoIterator.hasNext()){
				PaymentDto paymentDto = paymentDtoIterator.next();
				
				String eqParam = "paymentNumber";
				Object eqValue = paymentDto.getPaymentNumber();
				
				Payment payment = paymentService.searchUnique(eqParam, eqValue);
				
				String[] claimId = null;
				for(int j = 0; j < claimIdList.length ; j++){
					Claim claim = claimService.get(claimIdList[j]);
					if(claim.getBatchClaimId().getBatchClaimId() == payment.getBatchClaim().getBatchClaimId()){
						claimId[j] = String.valueOf(claim.getClaimId());
					}
				}
				
				payment.setAccountNumber(paymentDto.getAccountNumber());
				payment.setBankName(paymentDto.getBankName());
				payment.setClaimPaymentValue(paymentDto.getPaymentConfirmedValue());
				payment.setPaymentConfirmDate(paymentDto.getPaymentConfirmDate());
				payment.setPaymentNumber(paymentDto.getPaymentNumber());
				
				paymentService.finalize(payment,claimId, user);
			}
			
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
	}


}