package com.ametis.cms.util.automation.task;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberService;

public class MemberMutationActivatorTask {
	private MemberService memberService;

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public void activateMutation (){
		try {
			User theUser = new User();
			theUser.setUsername("system-activator");
			
			ActionUser actionUser = new ActionUser();
			actionUser.setUser(theUser);

			
			String[] eqParam = {"deletedStatus","status"};
			Object[] eqValue = {0,SubscriptionStatus.PENDING_MUTATION};
			
			int total = memberService.getTotal(null,null,eqParam,eqValue);
			Collection<Member> memberList = memberService.search(null,null,eqParam,eqValue,0,total);
			
			if (memberList != null){
				for (Iterator iterator = memberList.iterator(); iterator
						.hasNext();) {
					
					Member member = (Member) iterator.next();
										
					if (member != null && member.getStatus().intValue() == SubscriptionStatus.PENDING_MUTATION){
						ActionResult res = memberService.activateMutation(member.getMemberId(), member.getNextPolicyNumber(), 
								"", member.getNextEffectiveDate(), member.getNextExpireDate(), actionUser);
						
						if (res != null){
							if (res.isResult()){
								System.out.println("ACTIVATE MUTATION = success");
							}
							else {
								System.out.println("ACTIVATE MUTATION = Failed " + res.getAdditionalMessage());
							}
						}
					}
											
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
