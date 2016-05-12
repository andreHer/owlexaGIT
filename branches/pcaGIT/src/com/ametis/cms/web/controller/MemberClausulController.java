
package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberClausul;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 


/**
 * MemberClausul is a servlet controller for member_clausul Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class MemberClausulController implements Controller

// extends+ 

// extends- 

{
	
	private MemberClausulService  memberClausulService;
	
	
	private MemberService memberService;
	private UserService  userService;
	private ClaimService claimService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	
	private SecurityService securityService;
	private ActivityLogService logService;
	
	//Add by aju on 20150928, make iframe param public fufufu :D
	private String usingIFrame;
	private String iFrameClientMemberId;
	private String iFrameLevelLogin;
	private String sessionMemberId;
	private String sessionMemberParentId;
	private boolean isIFrameSession;
	
	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	
	private PolicyClausulService policyClausulService;

	
	
	public PolicyClausulService getPolicyClausulService() {
		return policyClausulService;
	}

	public void setPolicyClausulService(PolicyClausulService policyClausulService) {
		this.policyClausulService = policyClausulService;
	}

	public MemberService getMemberService() {
	return memberService;
}

public void setMemberService(MemberService memberService) {
	this.memberService = memberService;
}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
	}
	

	public void setCountSet (Integer countSet){
		this.countSet = countSet;
	}
	public Integer getCountSet (){
		return this.countSet;
	}
	public void setMaxPercountSet (Integer maxCount){
		this.maxPercountSet = maxCount;
	}
	public Integer getMaxPercountSet (){
		return this.maxPercountSet;
	}
	public void setAlertProperties (ResourceBundleMessageSource alert){
		this.alertProperties = alert;
	}
	public ResourceBundleMessageSource getAlertProperties (){
		return alertProperties;
	}
	public void setMemberClausulService	(MemberClausulService memberClausulService){
	this.memberClausulService = memberClausulService;
	}
	public MemberClausulService getMemberClausulService (){
	return this.memberClausulService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
									Integer memberClausulId = WebUtil.getParameterInteger (request,"memberClausulId");
						
	
						java.io.Serializable pkey = memberClausulId;
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEMEMBERCLAUSUL");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEMEMBERCLAUSUL access");
				return errorResult;
				
			}
			MemberClausul res = memberClausulService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.memberclausul",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.memberclausul",null,"fail",Locale.getDefault()));

			}
			result = searchPerformed (request, response, "searchMemberClausul");
		}
		catch (Exception e){
		// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
		 e.printStackTrace();
		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));
		result = new ModelAndView ("error");
		}	    return result;
	}

	public ModelAndView detailPerformed (HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
	    ModelAndView result = null;

	    try {
									Integer memberClausulId = WebUtil.getParameterInteger (request,"memberClausulId");
									
			Integer index = WebUtil.getParameterInteger(request,"index");
			String searchtext = WebUtil.getParameterString(request,"searchtext","");
			String searchby = WebUtil.getParameterString(request,"searchby","");
			/*
				Pkey ini merupakan representasi dari primary key, misalkan
				hasil get dari attribute diatas ^ itu cuma ada 1 key saja
				berarti tinggal pkey = <nama var diatas>
				tapi kalau misalkan composite primary key berarti
				tinggal bikin representasi primary key nya saja .
			*/

			result = new ModelAndView (location);
						java.io.Serializable pkey = memberClausulId;
				        MemberClausul object = memberClausulService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.memberclausul",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("memberClausul", object);
			result.addObject("index",index);
		    result.addObject("searchtext",searchtext);
		    result.addObject("searchby",searchby);

		}
		catch (Exception e){
		 e.printStackTrace();
		 request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));

         result = new ModelAndView ("error");
		}

	    return result;
	}



	public ModelAndView searchPerformed (HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
	    ModelAndView result = null;
		try {
		    result = new ModelAndView (location);


			String rowset = WebUtil.getParameterString (request,"rowset","0");

		    Integer index = WebUtil.getParameterInteger (request, "index");

		    Integer memberId = WebUtil.getParameterInteger(request, "memberId");
		    
		  //Add by aju on 20150929, for handle iFrame things fufufu
			if(isIFrameSession && iFrameLevelLogin.equalsIgnoreCase("member")){
				System.out.println("it\'s still on member(" + sessionMemberId + ") login session fufufu...");
				memberId = (memberId==null?Integer.parseInt(sessionMemberId):memberId);
				Member theMember = memberService.get(memberId);
				if(theMember!=null){
					System.out.println("checking->requestParentMemberId(" + theMember.getParentMember().getMemberId().toString() + ") vs sessionParentMemberId(" + sessionMemberParentId + ")");
					if(!theMember.getParentMember().getMemberId().toString().equals(sessionMemberParentId)){
						memberId=Integer.parseInt(sessionMemberId);
						ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	
						errorResult.addObject("errorType","OtherFamilyAccessDenied");			
						errorResult.addObject("errorMessage", "Hey member("+ sessionMemberId + "), are you missing your way? :P<br/>" 
								+ "<a href=\"memberclausul?navigation=listmember&memberId="+memberId+"\">Go Back</a>");
						return errorResult;
					}
				}
			}
		    
		    Integer claimId = WebUtil.getParameterInteger(request, "claimId");
		    
		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

		    Collection collection = null;
		    
		    

		    int rowsetint = 0;
		    int count = 0;


		    if (StringUtils.isNumeric(rowset)) {
		      rowsetint = Integer.parseInt(rowset);
		    }
		    Vector vLikeP = new Vector();
		    Vector vLikeQ = new Vector();
		    Vector vEqP = new Vector();
		    Vector vEqQ = new Vector();
		    


		    if (memberId != null) {
				vEqP.add("memberId.memberId");
				vEqQ.add(memberId);
				
				
			}
		    vEqP.add("deletedStatus");
		    vEqQ.add(new Integer(0));
		  

		    String sLikeP[] = new String[vLikeP.size()];
		    vLikeP.toArray(sLikeP);
		    Object sLikeQ[] = new Object[vLikeP.size()];
		    vLikeQ.toArray(sLikeQ);

		    String sEqP[] = new String[vEqP.size()];
		    vEqP.toArray(sEqP);
		    Object sEqQ[] = new Object[vEqP.size()];
		    vEqQ.toArray(sEqQ);

			count = memberClausulService.getTotal (sLikeP,sLikeQ,sEqP,sEqQ);

			String arah = WebUtil.getParameterString(request, "arah","");

			if(index==null)
				index = new Integer(1);

			if(arah.equals("kanan"))
				index = new Integer(index.intValue()+1);
			else if(arah.equals("kiri"))
				index = new Integer(index.intValue()-1);
			else if(arah.equals("kiribgt"))
				index=new Integer(1);
			else if(arah.equals("kananbgt"))
				index = new Integer(count/countSet.intValue()+1);

			if(index.compareTo(new Integer(1))==new Integer(-1).intValue())
				index = new Integer(1);
			else if(index.compareTo(new Integer(count/countSet.intValue()+1))==new Integer(1).intValue())
				index = new Integer(count/countSet.intValue()+1);

			rowsetint = (new Integer((index.intValue()-1)*countSet.intValue())).intValue();
			if(count%countSet.intValue()>0){
				result.addObject("halAkhir", new Integer(count
					/ countSet.intValue() + 1));
			}else{
				result.addObject("halAkhir", new Integer(count
					/ countSet.intValue() ));
			}
			
			minIndex = (index - 1) * countSet;
			maxIndex = index  * countSet;

			if (maxIndex > count){
				maxIndex = count;
			}

			String required[] = new String[]{"MemberClausul.TreatmentUpgradeType","MemberClausul.MemberId","MemberClausul.MemberProductId"};
		    collection = memberClausulService.search (sLikeP,sLikeQ,sEqP,sEqQ,
			required,rowsetint,countSet.intValue());

		    if(collection.size()<=0){
		    	index = new Integer(index.intValue()-1);
		    	if(index.compareTo(new Integer(1))==new Integer(-1).intValue())
					index = new Integer(1);
				else if(index.compareTo(new Integer(count/countSet.intValue()+1))==new Integer(1).intValue())
					index = new Integer(count/countSet.intValue()+1);

				rowsetint = (new Integer((index.intValue()-1)*countSet.intValue())).intValue();
				if(count%countSet.intValue()>0){
					result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
				}else{
					result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() ));
				}
				collection = memberClausulService.search (sLikeP,sLikeQ,sEqP,sEqQ,
					required,rowsetint,countSet.intValue());
		    }
			
		    request.setAttribute("searchtext", searchtext);
		    request.setAttribute("searchby", searchby);
		    request.setAttribute("navigation", navigation);
		
		    if (memberId != null){
				String[] requiredMember = {"Member.MemberGroupId","Member.ClientId",
						"Member.RelationshipId","Member.ParentMember","Member.CurrentPolicyId"};
				
				Member object = memberService.get(memberId,requiredMember);
				result.addObject("member", object);
				
				//hitung umur
				Date umur =  object.getBirthday();
				Calendar dob = Calendar.getInstance();  
				dob.setTime(umur);  
				Calendar today = Calendar.getInstance();  
				int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR); 
				if ((today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
				    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) ||
				    today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
					age--;  
				}
				result.addObject("age", age);
				//end
				
				if (object != null){
					Policy currentPolicy = object.getCurrentPolicyId();
					
					if (currentPolicy != null){
						Collection<PolicyClausul> inpatientClausul = getPolicyClausul(currentPolicy.getPolicyId(), CaseCategory.INPATIENT);
						Collection<PolicyClausul> outpatientClausul = getPolicyClausul(currentPolicy.getPolicyId(), CaseCategory.OUTPATIENT);
						Collection<PolicyClausul> maternityClausul = getPolicyClausul(currentPolicy.getPolicyId(), CaseCategory.MATERNITY);
						Collection<PolicyClausul> dentalClausul = getPolicyClausul(currentPolicy.getPolicyId(), CaseCategory.DENTAL);
						Collection<PolicyClausul> opticalClausul = getPolicyClausul(currentPolicy.getPolicyId(), CaseCategory.OPTICAL);
						Collection<PolicyClausul> mcuClausul = getPolicyClausul(currentPolicy.getPolicyId(), CaseCategory.MEDICAL_CHECK_UP);
						
						if (inpatientClausul != null && inpatientClausul.size() > 0){
							result.addObject("ipPolicyClausul", inpatientClausul);
						}
						if (outpatientClausul != null && outpatientClausul.size() > 0){
							result.addObject("opPolicyClausul", outpatientClausul);
						}
						if (maternityClausul != null && maternityClausul.size() > 0){
							result.addObject("maPolicyClausul", maternityClausul);
						}
						if (dentalClausul != null && dentalClausul.size() > 0){
							result.addObject("dentalPolicyClausul", dentalClausul);
						}
						if (opticalClausul != null && opticalClausul.size() > 0){
							result.addObject("opticalPolicyClausul", opticalClausul);
						}
						if (mcuClausul != null && mcuClausul.size() > 0){
							result.addObject("mcuPolicyClausul", mcuClausul);
						}
					}
				}
			}
		    
	    	Claim claimObject = null;
			
			if(claimId != null){
				try
				{
				java.io.Serializable claimkey = claimId;
				System.out.println("claim id : "+claimId);
				String[] claimRequired = {"Claim.BatchClaimId","Claim.MemberId",
						"Claim.CaseCategoryId","Claim.MemberId.MemberGroupId","Claim.CaseId",
						"Claim.DiagnosisId","Claim.Diagnosis2Id","Claim.Diagnosis3Id","Claim.CobClaimReferenceId","Claim.BatchClaimId.ClaimCurrency"};
				
				claimObject = claimService.get(claimkey, claimRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
		
		    result.addObject("MemberClausuls",collection);
		    result.addObject("memberId", memberId);
		    result.addObject("claimId", claimId);
		    result.addObject("claim", claimObject);
			request.setAttribute("countSet", countSet);
		    request.setAttribute("index", new Integer(index));
		    request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer (minIndex));
			request.setAttribute("maxIndex", new Integer (maxIndex));

		}
		catch (Exception e){
			e.printStackTrace();
	    		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));

		    result = new ModelAndView ("error");
		}

		return result;
	}

	public Collection<PolicyClausul> getPolicyClausul(Integer policyId, Integer caseCategoryId) throws Exception{
		
		Collection<PolicyClausul> result = null;
		
		try {
			if (policyId != null && caseCategoryId != null){
				
				String[] required = {"PolicyClausul.LocationId","PolicyClausul.TreatmentUpgradeType",
						"PolicyClausul.ItemCategoryId","PolicyClausul.CaseCategoryId","PolicyClausul.DiagnosisId","PolicyClausul.ProcedureId"};
				
				String[] eqParam = {"deletedStatus","policyId.policyId","caseCategoryId.caseCategoryId"};
				Object[] eqValue = {0,policyId,caseCategoryId};
				
				int total = policyClausulService.getTotal(null,null,eqParam,eqValue);
				result = policyClausulService.search(null,null,eqParam,eqValue,required,0,total);
				
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
		
	}


  /**
   * Action Service
   */
  public ModelAndView handleRequest(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    //Get paramater navigation
    String navigation = request.getParameter("navigation") == null
        ? "welcome"
        : request.getParameter("navigation");

    Object user = null;

	ModelAndView result = null;
    HttpSession session = request.getSession(false);

/*
    if (session == null) {

      request.setAttribute("alert", "<b>" + alertProperties.getValue("not.login") + "</b>");
      request.setAttribute("content", "/jsp/login.jsp");
      forward("/main.jsp", request, response);


	  result = new ModelAndView ("login");
    }
    else {

    }
*/
    String memberId = request.getParameter("memberId");
	String breadcrumb = "";
    
	try {
		
		//add aju on 20150929, new iFrame checker fufufu plus init
		System.out.println("MemberClausulController::handleRequest()...");
		isIFrameSession = securityService.isUsingIFrameSession(request);
		iFrameLevelLogin = securityService.getTheIFrameLevelLogin();
		System.out.println("securityService->SessionMemberId->" + securityService.getTheSessionMemberId());
		sessionMemberId = securityService.getTheSessionMemberId();
		System.out.println("securityService->SessionParentMemberId->" + securityService.getTheSessionMemberParentId());
		sessionMemberParentId = securityService.getTheSessionMemberParentId();
		
		
	    if (navigation.equalsIgnoreCase ("detail")){
	    /*
	    	disesuaikan dengan halaman targetnya nih
	    */
	    	result = detailPerformed (request, response, "detailMemberClausul" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchMemberClausul" );
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupMemberClausul" );
	    }
	    else if (navigation.equalsIgnoreCase("listclaim")){
	    	result = searchPerformed(request, response, "listClaimMemberClausul");
	    	String claimId = request.getParameter("claimId");
	    	breadcrumb = "<a href=\"claim?navigation=detail&claimId="
					+ claimId
					+ "\" class=\"linkbreadcrumb\">Detail Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Claim Clausul";
	    }
	    else if (navigation.equalsIgnoreCase("listmember")){
	    	result = searchPerformed(request, response, "listMemberClausul");
	    	breadcrumb = "<a href=\"member?navigation=detail&memberId="
					+ memberId
					+ "\" class=\"linkbreadcrumb\">Detail Member</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Member Clausul";
	    }
	    else {
	    	result = searchPerformed (request, response, "searchMemberClausul" );
	    }
    }
    catch (Exception e){
    	e.printStackTrace();
    }
	result.addObject("breadcrumb", breadcrumb);
	Map map = TableRenderingServlet.seti18N(request,response);
	result.addAllObjects(map);
    return result;
  }


// class+ 

// class- 

}
