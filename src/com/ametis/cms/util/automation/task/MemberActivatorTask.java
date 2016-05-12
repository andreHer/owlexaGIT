package com.ametis.cms.util.automation.task;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

import org.joda.time.DateTime;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberService;

public class MemberActivatorTask {

	private MemberService memberService;

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public void activateUpgrade() {
		try {

			User theUser = new User();
			theUser.setUsername("system-activator");

			ActionUser actionUser = new ActionUser();
			actionUser.setUser(theUser);

			System.out.println("ACTIVATE UPGRADE");
			Collection<Object> pendingEmployeeList = memberService.getAllPendingUpgradeEmployee();
			Collection<Object> pendingMemberList = memberService.getAllPendingUpgradeMember();

			for (Iterator iterator = pendingEmployeeList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();

				if (object instanceof Integer) {
					Integer memberId = (Integer) object;

					try {
						memberService.activateUpgrade(memberId, actionUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			for (Iterator iterator = pendingMemberList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();

				if (object instanceof Integer) {
					Integer memberId = (Integer) object;
					try {
						memberService.activateUpgrade(memberId, actionUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void activateRenewal() {
		try {

			User theUser = new User();
			theUser.setUsername("system-activator");

			ActionUser actionUser = new ActionUser();
			actionUser.setUser(theUser);

			System.out.println("ACTIVATE RENEWAL");
			Collection<Object> pendingEmployeeList = memberService.getAllPendingRenewEmployee();
			Collection<Object> pendingMemberList = memberService.getAllPendingRenewMember();
			Collection<Integer> autoRenewalList = memberService.getAutoRenewalNomineeList();

			for (Iterator iterator = pendingEmployeeList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();

				if (object instanceof Integer) {
					Integer memberId = (Integer) object;

					try {
						memberService.activateRenewal(memberId, actionUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			for (Iterator iterator = pendingMemberList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();

				if (object instanceof Integer) {
					Integer memberId = (Integer) object;

					try {

						memberService.activateRenewal(memberId, actionUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			for (Iterator iterator = autoRenewalList.iterator(); iterator.hasNext();) {
				Integer memberId = (Integer) iterator.next();
				
				if (memberId != null){
					try {
						Member member = memberService.get(memberId);
													
						if (member != null && member.getPolicyExpireDate() != null){
							if (member.getPolicyExpireDate().after(member.getExpireDate()) && 
									member.getExpireDate().before(new java.sql.Date(System.currentTimeMillis()))){
								
								// jika policy expire date nya masih jauh dari current expire date
								
								String paymentMethod = member.getPaymentPeriodeMethod() == null ? "" : member.getPaymentPeriodeMethod();
								
								boolean isRenewal = true;
								
								DateTime nextExpireDate = null;
								DateTime nextEffectiveDate = null;
								
								nextEffectiveDate = new DateTime(member.getExpireDate()).plusDays(1);
								
								if (paymentMethod.equalsIgnoreCase("A")){						
									nextExpireDate = new DateTime(member.getExpireDate()).plusYears(1);
								}
								else if (paymentMethod.equalsIgnoreCase("SM")){							
									nextExpireDate = new DateTime(member.getExpireDate()).plusMonths(6);							
								}
								else if (paymentMethod.equalsIgnoreCase("M")){
									nextExpireDate = new DateTime(member.getExpireDate()).plusMonths(1);
								}
								else if (paymentMethod.equalsIgnoreCase("Q")){
									nextExpireDate = new DateTime(member.getExpireDate()).plusMonths(3);
								}
									
								if (nextEffectiveDate != null && nextExpireDate != null){
									member.setNextEffectiveDate(new Date(nextEffectiveDate.getMillis()));
									member.setNextExpireDate(new Date(nextExpireDate.getMillis()));
									
									
									member.setNextCardNumber(member.getCurrentCardNumber());
									member.setNextPolicyNumber(member.getCurrentPolicyNumber());
									member.setNextCustomerNumber(member.getCustomerPolicyNumber());
									
									member.setStatus(SubscriptionStatus.PENDING_RENEWAL);
									
									memberService.update(member, actionUser);				
								}
								else {
									System.out.println("AUTO RENEWAL TIDAK BISA DILAKUKAN KARENA Payment Method Tidak Di Definisikan MEMBER ID = " + member.getMemberId());
								}
								
							}						
						}
					}
					catch (Exception e){
						e.printStackTrace();
					}
				}				
			}
			memberService.updateExpiredMember();
			memberService.activateAdvanceRenewal(actionUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void activate() {
		try {

			User theUser = new User();
			theUser.setUsername("system-activator");

			ActionUser actionUser = new ActionUser();
			actionUser.setUser(theUser);

			System.out.println("ACTIVATE MEMBER");
			Collection<Object> pendingEmployeeList = memberService.getAllPendingEmployee();
			Collection<Object> pendingMemberList = memberService.getAllPendingMember();

			for (Iterator iterator = pendingEmployeeList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();

				if (object instanceof Integer) {
					Integer memberId = (Integer) object;
					try {
						memberService.activate(memberId, actionUser);

						// Catch if there's error on current member activation
					} catch (Exception e) {
						e.printStackTrace();
					}
					/**
					 * 
					 * memberService.populateUpdateMemberProduct(actionUser,
					 * memberId);
					 */

					// memberService.activateAndUpdate(actionUser, memberId);
				}

			}

			for (Iterator iterator = pendingMemberList.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();

				if (object instanceof Integer) {
					Integer memberId = (Integer) object;

					try {
						memberService.activate(memberId, actionUser);

						// Catch if there's error on current member activation
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
