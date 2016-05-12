package com.ametis.cms.web.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.service.DependentService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.util.WebUtil;

public class AJAXMemberController implements Controller{
	
	private MemberService memberService;
	private MemberGroupService memberGroupService;
	private DependentService dependentService;
	
	public DependentService getDependentService() {
		return dependentService;
	}
	public void setDependentService(DependentService dependentService) {
		this.dependentService = dependentService;
	}
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
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		
		ModelAndView result = new ModelAndView();
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		if (navigation.equalsIgnoreCase("lookup")){
			
		}
		return result;
	}
	
	
}
