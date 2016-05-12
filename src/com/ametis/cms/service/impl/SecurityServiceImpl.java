package com.ametis.cms.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActionService;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.RoleActionService;
import com.ametis.cms.service.RoleService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;


public class SecurityServiceImpl implements SecurityService{

	private UserService userService;
	private RoleActionService roleActionService;
	private RoleService roleService;
	private ActionService actionService;
	private ActivityLogService activityLogService;
	//Add by aju on 20150928, add memberService
	private MemberService memberService;
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	
	//Add by aju on 20151001, for user access checking
	private MemberGroupService memberGroupService;
	private PolicyService policyService;
	private BatchClaimService batchClaimService;
	private ClaimService claimService;
	private MemberProductService memberProductService;
	private MemberBenefitService memberBenefitService;
	private ProductService productService;
	private CaseService caseService;
	private ClaimItemService claimItemService;
	
	
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public RoleActionService getRoleActionService() {
		return roleActionService;
	}
	public void setRoleActionService(RoleActionService roleActionService) {
		this.roleActionService = roleActionService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public ActionService getActionService() {
		return actionService;
	}
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public String getSessionMemberId() {
		return sessionMemberId;
	}
	public void setSessionMemberId(String sessionMemberId) {
		this.sessionMemberId = sessionMemberId;
	}
	public String getSessionMemberParentId() {
		return sessionMemberParentId;
	}
	public void setSessionMemberParentId(String sessionMemberParentId) {
		this.sessionMemberParentId = sessionMemberParentId;
	}
	public String getiFrameLevelLogin() {
		return iFrameLevelLogin;
	}
	public void setiFrameLevelLogin(String iFrameLevelLogin) {
		this.iFrameLevelLogin = iFrameLevelLogin;
	}
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}
	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	public BatchClaimService getBatchClaimService() {
		return batchClaimService;
	}
	public void setBatchClaimService(BatchClaimService batchClaimService) {
		this.batchClaimService = batchClaimService;
	}
	public ClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
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
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public CaseService getCaseService() {
		return caseService;
	}
	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}
	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}
	public boolean isUserAuthorized(ActionUser actionUser, String actionCode)  {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			if (actionUser != null){
				
				User user = actionUser.getUser();
				
				if (user != null){
					String[] eqColumn = {"actionId.actionCode","roleId.roleId"};
					Object[] eqParam  = {actionCode,user.getRoleId().getRoleId()};
					
					int isRoleActionExist = roleActionService.getTotal(null, null, eqColumn, eqParam);
					
					if (isRoleActionExist > 0){
						result = true;
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			result = true;
		}
		result = true;
		return result;
	}
	public ActionUser getActionUser (HttpServletRequest request){
		HttpSession session = request.getSession(false);
		
		
		ActionUser actionUser = null;
		
		if (session != null){
			User user = (User) session.getAttribute("theUser");
			
			String ipAddress = request.getRemoteAddr();
			String sessionCode = request.getSession().getId();
			
			actionUser = new ActionUser();
			actionUser.setIpAddress(ipAddress);
			actionUser.setLoginSession(sessionCode);
			actionUser.setUser(user);
			actionUser.setActionURL(request.getRequestURL().toString());
		}
		return actionUser;
	}
	public User getCurrentUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		
		User user = null;
		
		if (session != null){
			user = (User) session.getAttribute("theUser");
			
			
		}
		return user;
	}
	@Override
	public boolean isUsingIFrameSession(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		try{
			//System.out.println("SecurityServiceImpl::isUsingIFrameSession...");
			HttpSession session = request.getSession(false);
			
			//Add by aju on 20150326, for client iFrame support
			//check if there's session for iframe
			isIFrameSession = false;
			if(session != null){
				if(session.getAttribute("iframe") != null){
					isIFrameSession = (session.getAttribute("iframe").toString().equalsIgnoreCase("yes")?true:false);
				}
			}
			
			//modified by aju on 20150928, remove local, make it public
			/*
			//modified by aju on 20150410, add level login on iFrame :D
			String usingIFrame=null,iFrameClientMemberId=null,iFrameLevelLogin=null;
			*/
			usingIFrame=null;
			iFrameClientMemberId=null;
			iFrameLevelLogin=null;
			
			if(isIFrameSession){
				//System.out.println("IFrame Session detected on Member Controller");
				//get the iFrame saved parameter from session
				usingIFrame = session.getAttribute("iframe").toString();
				//Add by aju on 20150410, add level login filtering on iFrame :D
				iFrameLevelLogin = session.getAttribute("levelLogin").toString();
				
				//modified by aju on 20150410, use level login filtering :D
				//iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				
				if (iFrameLevelLogin.equalsIgnoreCase("member")){
					iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
				}
				/**
				switch (iFrameLevelLogin) {
				case "client":
					
					break;
				case "member":
					iFrameClientMemberId = session.getAttribute("clientMemberId").toString();
					break;
				}
				*/
			}
			
			//Add by aju on 20150928, no url param changes for member access fufufu :D
			//modified by aju on 20150410, add level login filtering :D
			if(isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")){
				Member m = memberService.getMember(iFrameClientMemberId);
				setSessionMemberId(m.getMemberId().toString());
				setSessionMemberParentId(m.getParentMember().getMemberId().toString());
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return isIFrameSession;
	}
	@Override
	public String getTheSessionMemberId() {
		// TODO Auto-generated method stub

		return sessionMemberId; 
	}
	@Override
	public String getTheSessionMemberParentId() {
		// TODO Auto-generated method stub
		//return null;
		return sessionMemberParentId;
	}
	@Override
	public String getTheIFrameLevelLogin() {
		// TODO Auto-generated method stub
		//return null;
		return iFrameLevelLogin;
	}
	@Override
	public boolean isRequestAllowed(HttpServletRequest request) {
		
		boolean isAllowed = true;
		
		
		
		return isAllowed;
	}

}
