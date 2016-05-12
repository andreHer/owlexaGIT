package com.ametis.cms.util.automation.task;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;

public class SuspendReleaseProcessorTask {

	
	private MemberGroupService memberGroupService;
	private MemberService memberService;
	private PolicyService policyService;
	
	
	
	
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}
	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	public boolean releaseMemberSuspend (){
		boolean result = false;
		

		try {
			String[] eqParam = {"status.statusId","deletedStatus"};
			Object[] eqValue = {SubscriptionStatus.BLOCKED,0};
			
			ActionUser actionUser = new ActionUser();
			User theUser = new User();
			theUser.setUsername("suspend-releaser");
			
			actionUser.setUser(theUser);

			int total = memberService.getTotal(null,null,eqParam,eqValue);
			
			Collection<Member> memberList = memberService.search(null,null,eqParam,eqValue,0,total);
			
			for (Iterator iterator = memberList.iterator(); iterator.hasNext();) {
				Member member = (Member) iterator.next();
				
				if (member != null){
					if (member.getSuspendEndDate().getTime() < System.currentTimeMillis()){
						if (member.getExpireDate().getTime() > System.currentTimeMillis()){						
							
							memberService.unblock(member.getMemberId(), "auto release suspend", actionUser);						
							
						}
					}
					
				}
			}
			
			result = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean releaseGroupSuspend (){
		boolean result= false;
		
		try {
			String[] eqParam = {"status.statusId","deletedStatus"};
			Object[] eqValue = {SubscriptionStatus.BLOCKED,0};
			
			ActionUser actionUser = new ActionUser();
			User theUser = new User();
			theUser.setUsername("suspend-releaser");
			
			actionUser.setUser(theUser);

			int total = memberGroupService.getTotal(null,null,eqParam,eqValue);
			
			Collection<MemberGroup> groupList = memberGroupService.search(null,null,eqParam,eqValue,0,total);
			
			
			for (Iterator iterator = groupList.iterator(); iterator.hasNext();) {
				MemberGroup memberGroup = (MemberGroup) iterator.next();
				
				if (memberGroup != null){
					if (memberGroup.getSuspendEndDate().getTime() < System.currentTimeMillis()){
						if (memberGroup.getExpireDate().getTime() > System.currentTimeMillis()){
							
							memberGroupService.unblock(memberGroup.getMemberGroupId(), "auto release suspend", actionUser);
							
						}
					}
					
				}
			}
			result = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;			
				
	}
}
