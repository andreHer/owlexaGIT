package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BankAccount;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.dto.BankAccountDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.MemberGroupDto;
import com.ametis.cms.dto.CaseDto;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.dto.ProviderDto;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.BankAccountService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.web.controller.MemberElectronicCardController;


@WebService(name = "CaseWebService", 
		endpointInterface = "com.ametis.cms.webservice.ICaseWebService",
		serviceName="CaseWebService")

public class CaseWebServiceImpl implements ICaseWebService{

	private CaseService caseService;
	private MemberElectronicCardService memberElectronicCardService;

	public MemberElectronicCardService getMemberElectronicCardService() {
		return memberElectronicCardService;
	}

	public void setMemberElectronicCardService(
			MemberElectronicCardService memberElectronicCardService) {
		this.memberElectronicCardService = memberElectronicCardService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

		
	public Collection<CaseDto> searchCaseByCaseNumber(String caseNumber) {//buatan Luthfi, untuk menampilkan data case berdasarkan filter nomor case
		// TODO Auto-generated method stub
		Collection<CaseDto> resultList = null;
		
		Vector vCase = new Vector();
		Vector voCase = new Vector();
		
		Vector vStatus = new Vector();
		Vector voStatus = new Vector();

		try {
			vCase.add("caseNumber");
			vStatus.add("deletedStatus");
			voCase.add(caseNumber);
			voStatus.add(Integer.valueOf(0));
			
			String sCase[] = new String[vCase.size()];
			vCase.toArray(sCase);//done
			Object oCase[] = new Object[vCase.size()];
			voCase.toArray(oCase);
			
			String sStatus[] = new String[vStatus.size()];
			vStatus.toArray(sStatus);//done
			Object oStatus[] = new Object[vStatus.size()];
			voStatus.toArray(oStatus);

			String[] required = {"Case.ItemId", "Case.MemberId", "Case.CaseCategoryId", "Case.ClaimId"};
			
			Collection<Case> caseList = caseService.searchCase( sCase, oCase, sStatus, oStatus, required);


			if (caseList != null ) {
				resultList = new Vector<CaseDto>();
				Iterator<Case> iterator = caseList.iterator();

				while (iterator.hasNext()) {
					CaseDto result = new CaseDto();
					Case cases = iterator.next();
					
					result.setCaseNumber(cases.getCaseNumber() == null ? "" : cases.getCaseNumber());
					result.setCaseId(cases.getCaseId() == null ? "" : cases.getCaseId().toString());
					result.setMemberId(cases.getMemberId().getMemberId() == null ? "" : cases.getMemberId().getMemberId().toString());
					result.setCardNumber(cases.getMemberId().getCurrentCardNumber() == null ? "" : cases.getMemberId().getCurrentCardNumber());
					result.setName(cases.getMemberId().getFullName() == null ? "" : cases.getMemberId().getFullName());
					result.setClient(cases.getMemberId().getClientName() == null ? "" : cases.getMemberId().getClientName());
					result.setGroup(cases.getMemberId().getGroupName() == null ? "" : cases.getMemberId().getGroupName());
					result.setType(cases.getCaseCategoryId().getCaseCategoryName() == null ? "" : cases.getCaseCategoryId().getCaseCategoryName());
					result.setCharge(cases.getCaseClaimValue() == null ? "" : cases.getCaseClaimValue().toString());
					result.setPolicyNumber(cases.getMemberId().getCurrentPolicyNumber() == null ? "" : cases.getMemberId().getCurrentPolicyNumber());
					result.setDateAndTime(cases.getModifiedTime() == null ? "" : cases.getModifiedTime().toString());
					result.setHospital(cases.getProviderId().getProviderName() == null ? "" : cases.getProviderId().getProviderName());
					result.setOfficer(cases.getClaimId().getModifiedBy() == null ? "" : cases.getClaimId().getModifiedBy());


//					try 
//					{
//						Collection<MemberElectronicCard> tmpRes = memberElectronicCardService.getCardNumberByMember(cases.getMemberId().getMemberId());
//						
//						for (MemberElectronicCard memberElectronicCard : tmpRes){
//							result.setCardNumber(memberElectronicCard.getCardNumber() == null ? "" : memberElectronicCard.getCardNumber());
//						}
//					}
//					catch (Exception e){
//						e.printStackTrace();
//					}
					
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}
	
	public Collection<CaseDto> getCaseList() {//buatan Luthfi, untuk menampilkan data case berdasarkan filter nomor case
		// TODO Auto-generated method stub
		Collection<CaseDto> resultList = null;
		
		Vector vStatus = new Vector();
		Vector voStatus = new Vector();

		try {
			vStatus.add("deletedStatus");
			voStatus.add(Integer.valueOf(0));
			
			String sStatus[] = new String[vStatus.size()];
			vStatus.toArray(sStatus);//done
			Object oStatus[] = new Object[vStatus.size()];
			voStatus.toArray(oStatus);

			String[] required = {"Case.ItemId", "Case.MemberId", "Case.CaseCategoryId", "Case.ProviderId"};
			
			Collection<Case> caseList = caseService.getCaseList(sStatus, oStatus, required);


			if (caseList != null ) {
				resultList = new Vector<CaseDto>();
				Iterator<Case> iterator = caseList.iterator();

				while (iterator.hasNext()) {
					CaseDto result = new CaseDto();
					Case cases = iterator.next();
					
					result.setCaseNumber(cases.getCaseNumber() == null ? "" : cases.getCaseNumber());
					result.setCaseId(cases.getCaseId() == null ? "" : cases.getCaseId().toString());
					result.setMemberId(cases.getMemberId().getMemberId() == null ? "" : cases.getMemberId().getMemberId().toString());
					result.setCardNumber(cases.getMemberId().getCurrentCardNumber() == null ? "" : cases.getMemberId().getCurrentCardNumber());
					result.setName(cases.getMemberId().getFullName() == null ? "" : cases.getMemberId().getFullName());
					result.setClient(cases.getMemberId().getClientName() == null ? "" : cases.getMemberId().getClientName());
					result.setGroup(cases.getMemberId().getGroupName() == null ? "" : cases.getMemberId().getGroupName());
					result.setType(cases.getCaseCategoryId().getCaseCategoryName() == null ? "" : cases.getCaseCategoryId().getCaseCategoryName());
					result.setCharge(cases.getCaseClaimValue() == null ? "" : cases.getCaseClaimValue().toString());
					result.setPolicyNumber(cases.getMemberId().getCurrentPolicyNumber() == null ? "" : cases.getMemberId().getCurrentPolicyNumber());
					result.setDateAndTime(cases.getModifiedTime() == null ? "" : cases.getModifiedTime().toString());
					result.setHospital(cases.getProviderId().getProviderName() == null ? "" : cases.getProviderId().getProviderName());
					
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}
	
	
}
