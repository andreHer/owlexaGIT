package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.dto.ClientDto;
import com.ametis.cms.dto.MemberBenefitDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.MemberGroupDto;
import com.ametis.cms.dto.MemberProductDto;
import com.ametis.cms.dto.PolicyDto;

@WebService (targetNamespace="http://ametis.co.id/services/MemberWebService")
public interface IMemberWebService {

	@WebMethod
	public MemberDto getMember (String memberNumber,String memberGroupId,String clientId);
	//Add by aju on 20150818, for getting member by memberId
	@WebMethod
	public MemberDto getMemberByMemberId (String memberId);
	
	@WebMethod
	public MemberDto getMemberByCardNumber (String cardNumber);
	@WebMethod
	public Collection<MemberGroupDto> getMemberGroupByClient (String clientId);
	@WebMethod
	public Collection<MemberDto> searchMemberByDobClientGroupFullName (String clientName, String groupName, String dob, String name);//buatan luthfi
	@WebMethod
	public Collection<MemberDto> searchMemberByDob (Date dob);//buatan luthfi
	@WebMethod
	public Collection<MemberDto> getMemberList(Date createdTime, Date createdTime2);
	@WebMethod
	public MemberGroupDto getMemberGroup (String memberGroupId);
	
	@WebMethod
	public Collection<MemberGroupDto> getMemberGroupList ();
	
	
	@WebMethod
	public String movementMember (String memberNumber, MemberDto memberDto);
	
	@WebMethod
	public Collection<MemberDto> getParentList (String memberGroupId);
	@WebMethod
	public Collection<MemberDto> getParentListByAlphabet (String memberGroupId, String character);
	
	@WebMethod
	public Collection<MemberDto> getDependentList (String memberId);
	
	
	
	@WebMethod
	public Collection<Integer> getGetPendingEmployeeList ( String username,String password);
	
	@WebMethod
	public Collection<Integer> getPendingDataImportList (String username, String password);
	
	@WebMethod
	public String processDataImport(Integer id, String username, String password);
	
	@WebMethod
	public Collection<Integer> getGetPendingSynchronizeEmployeeList (Integer policyId, String username, String password);
	
	@WebMethod
	public Collection<Integer> getGetPendingSynchronizeDependentList (Integer policyId, String username, String password);
	
	@WebMethod
	public Collection<Integer> getGetPendingMemberList ( String username,String password);
	
	@WebMethod
	public Collection<Integer> getGetPendingUpgradeEmployeeList ( String username,String password);
	
	@WebMethod
	public Collection<Integer> getAutoRenewalNomineeList (String username, String password);
	
	@WebMethod
	public boolean requestAutoRenewalNominee(Integer memberId,String username, String password);
	
	@WebMethod
	public Collection<Integer> getGetPendingRenewalEmployeeList ( String username,String password);
	

	@WebMethod
	public Collection<Integer> getGetPendingRenewalList ( String username,String password);
	
	@WebMethod
	public Collection<Integer> getGetPendingUpgradeList ( String username,String password);
	
	@WebMethod
	public ActionResult activateMember (Integer memberId, String username,String password);
	
	@WebMethod
	public ActionResult activateMemberUpgrade (Integer memberId, String username,String password);
	
	@WebMethod
	public ActionResult activateBenefitSynchronize (Integer memberId, String username,String password);
	
	@WebMethod
	public ActionResult activateMemberRenewal (Integer memberId, String username,String password);
	
	@WebMethod
	public Collection<Integer> getBlockedList (String username, String password);	
	
	@WebMethod
	public Collection<Integer> getMutationList (String username, String password);
	
	@WebMethod
	public boolean processMutation (Integer requestId, String username, String password);
	
	@WebMethod
	public Collection<Integer> getMonthlyRenewalList (String username, String password);
	
	
	@WebMethod
	public ClientDto getClient (String clientId);
	
	@WebMethod
	public Collection<MemberBenefitDto> getMemberBenefit (String memberNumber, String kodeService);
	//Add by aju on 20150910, for getting member product list fufufu
	@WebMethod
	public Collection<MemberProductDto> getMemberProductByMemberId(Integer memberId);
	
	@WebMethod
	public String isValidProvider (String merchantId, String cardNumber,String caseCategoryCode);
	
	@WebMethod
	public boolean migrateMember (MemberDto member, String username);
	@WebMethod
	public String terminateMember (String memberNumber, String resignDate, ActionUser actionUser);
	
	@WebMethod
	public ActionResult releaseSuspend() throws Exception;
	
	@WebMethod
	public String terminateMemberList (Collection<String> memberNumbers, Collection<String> resignDates,  ActionUser actionUser );
	
	
	@WebMethod
	public ActionResult authorizeMemberEDC (String cardNumber, String service,
			String referenceNumber,String merchantId,String terminalId);
	
	@WebMethod
	public ActionResult authorizeMemberEDCManagedCare(String cardNumber, String serviceType,
			String referenceNumber, String merchantId, String terminalId) ;
	
	
	@WebMethod
	public ActionResult getFreeTextEDC (MemberDto member, String caseCategoryId) throws Exception;
	
	
	@WebMethod
	public ActionResult getAvailableMemberEDCService (String cardNumber,String memberNumber, String merchantId);
	@WebMethod
	public ActionResult getAvailableMemberEDCBenefit (String cardNumber,String memberNumber,String serviceType, String merchantId);
	
	@WebMethod
	public boolean updateExpireStatus () throws Exception;
	
	@WebMethod
	public PolicyDto getActivePolicy (String memberGroupId, ActionUser actionUser) throws Exception;
	
	@WebMethod
	public Collection<MemberDto> getGroupMember (String memberGroupId, String status);
	
	@WebMethod
	public boolean activateMember (String memberId);
	
	@WebMethod
	public boolean updateMemberBankAccount (String memberNumber, String bankAccount, String bankName, String bankBranch, 
			String bankAccountName, ActionUser actionUser);
	
	@WebMethod
	public ActionResult authorizeMemberEDCSys (String cardNumber,String memberNumber,String service, String merchantId,String terminalId);
}
 