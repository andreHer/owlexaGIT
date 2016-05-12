package com.ametis.cms.util;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.context.ApplicationContext;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;

public class MemberGenerator {

	private static final char[] charset = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private ApplicationContext appContext;
	
	private MemberGroupService memberGroupService;
	public static void main (String[] args){
		RandomStringUtils randomizer = new RandomStringUtils();
		System.out.println("RANDOM : " +  randomizer.randomAlphanumeric(1).toUpperCase());
		
	
	}
	public MemberGenerator(){
		try {
			appContext = new org.springframework.context.support.ClassPathXmlApplicationContext("/WEB-INF/applicationContext.xml");
			
			MemberService memberService = (MemberService) appContext.getBean("memberService");
			
			memberGroupService = (MemberGroupService) appContext.getBean("memberGroupService");
			
			String[] required = {"MemberGroup.Members"};
			
			
			MemberGroup memberGroup = memberGroupService.get("",required);
			Collection<Member> memberCollection = memberGroup.getMembers();
			
			ActionUser actionUser = new ActionUser();

			User user = new User();
			user.setUsername("system-generator");
			
			actionUser.setUser(user);
			
			if (memberCollection != null){
				Iterator<Member> memberIterator = memberCollection.iterator();
				if (memberIterator != null){
					Member member = null;
					
					while (memberIterator.hasNext()){
						member = memberIterator.next();
						
						if (member != null){
							String generatedNumber = generateNumber(member);
							
							member.setCustomerPolicyNumber(generatedNumber);
							memberService.update(member, actionUser);
						}
					}
				}
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	private String generateCounter (int x){
		String result = "";
		
		if (x < 10000 && x >= 1000){
			result = "0"+x;
		}
		else if (x < 1000 && x >= 100){
			result = "00"+x;
		}
		else if (x < 100 && x >= 10 ) {
			result = "000"+x;
		}
		else if (x < 10){
			result = "0000"+x;
		}
		
		return result;
	}
	private String generateNumber (Member member){
		String result = "";
		
		if (member != null){
		
			MemberGroup memberGroup = member.getMemberGroupId();
			
			
			
			String groupCode = "";
			
			if (memberGroup != null){
			
				groupCode = memberGroup.getGroupName();
				
				RandomStringUtils randomizer = new RandomStringUtils();
				String randomNumber = randomizer.randomAlphanumeric(1);
				int counter = 0;
				result = groupCode+randomNumber+"";
				
				System.out.println ("RANDOMIZER : " + result);
			}
		}
		
		return result;
	}
}
