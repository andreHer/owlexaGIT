package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.dto.RecipientDto;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BrokerService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Problem is a servlet controller for problem Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class RecipientController implements Controller

// extends+

// extends-
{

	private MemberService memberService;
	private com.ametis.cms.service.ProviderService providerService;
	private MemberGroupService memberGroupService;
	private BrokerService brokerService;
	private ClientService clientService;

	private ActivityLogService logService;

	
	
	public BrokerService getBrokerService() {
		return brokerService;
	}

	public void setBrokerService(BrokerService brokerService) {
		this.brokerService = brokerService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}



	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}



	public com.ametis.cms.service.ProviderService getProviderService() {
		return providerService;
	}



	public void setProviderService(
			com.ametis.cms.service.ProviderService providerService) {
		this.providerService = providerService;
	}



	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}



	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}



	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");
		String q = WebUtil.getParameterString(request, "q", "");
		String tipe = WebUtil.getParameterString(request, "recipient", "");
		String clientId = WebUtil.getParameterString(request, "clientid", "");

		ModelAndView result = new ModelAndView("lookupRecipientJson");
		HttpSession session = request.getSession(false);
		
		System.out.println("CLIENT ID : " + clientId);

	
		try {
			
			System.out.println("HALO Q : " + q + " Tipe : " + tipe);
			Collection<RecipientDto> dto = new Vector<RecipientDto>();
			
			if (tipe.equalsIgnoreCase(PaymentRecipient.MEMBER+"")){
				
				System.out.println("SEARCH MEMBER ");
								
				Collection<Member> memberList =  null;
				
				if (clientId != null){
					memberList = memberService.searchMemberAndClient(q, Integer.valueOf(clientId));
				}
				else {
					memberList = memberService.searchMember(q);
				}
				
				if (memberList != null){
					for (Iterator iterator = memberList.iterator(); iterator
							.hasNext();) {
						
						Member member = (Member) iterator.next();
						RecipientDto dt = new RecipientDto();
						if (member.getParentMember() != null){
							dt.setId(member.getParentMember().getMemberId().toString());
						}
						else {
							dt.setId(member.getMemberId().toString());
						}
						dt.setNama(member.getFirstName());
						dt.setNumber(member.getCustomerPolicyNumber());
						dt.setTipe(tipe);
						dt.setLabel(member.getParentName());
						
						System.out.println("NAMA : " + dt.getNama());
						dto.add(dt);
					}
				}
			}
			if (tipe.equalsIgnoreCase(PaymentRecipient.MEMBER_GROUP+"")){
				
				System.out.println("SEARCH MGROUP ");
				Collection<MemberGroup> groupList = memberGroupService.searchGroup(q);
				
				for (Iterator iterator = groupList.iterator(); iterator
						.hasNext();) {
					MemberGroup memberGroup = (MemberGroup) iterator.next();
					
					RecipientDto dt = new RecipientDto();
					dt.setId(memberGroup.getMemberGroupId().toString());
					dt.setNama(memberGroup.getGroupName());
					dt.setNumber(memberGroup.getMemberGroupCode());
					dt.setTipe(tipe);
					dt.setLabel(memberGroup.getGroupName());
					
					System.out.println("NAMA : " + dt.getNama());
					dto.add(dt);
				}
			}
			
			if (tipe.equalsIgnoreCase(PaymentRecipient.PROVIDER+"")){
				Collection<Provider> providerList = providerService.searchAuthorizedProvider(q);
				
				System.out.println("SEARCH PROVIDER ");
				for (Iterator iterator = providerList.iterator(); iterator
						.hasNext();) {
					
					
					Provider provider = (Provider) iterator.next();
					
					RecipientDto dt = new RecipientDto();
					dt.setId(provider.getProviderId().toString());
					dt.setNama(provider.getProviderName());
					
					System.out.println("NAMA : " + dt.getNama());
					dt.setNumber(provider.getProviderCode());
					dt.setTipe(tipe);
					dt.setLabel(provider.getProviderName());
					
					dto.add(dt);
				}	
			}
			if (tipe.equalsIgnoreCase(PaymentRecipient.CLIENT+"")){
				
			}
			
			result.addObject("RecipientDtos", dto);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-
}
