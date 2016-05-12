package com.ametis.cms.web.controller;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.util.MoneyParser;
import com.ametis.cms.util.WebUtil;

public class ReportController implements Controller {

    private ClaimService claimService;
    private MemberService memberService;
    private MemberGroupService memberGroupService;
    private ClaimItemService claimItemService;
    
    
    private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
    public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public ClaimService getClaimService() {
        return claimService;
    }

    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String navigation = request.getParameter("navigation") == null ? "welcome"
                : request.getParameter("navigation");

        ModelAndView result = null;
        
        System.out.println("SYSTEM OUT HANDLING REPORT");
        try {
            if (navigation.equalsIgnoreCase("claimRecapPayment")) {
                /*
                 * disesuaikan dengan halaman targetnya nih
                 */
                result = claimRecapPayment(request, response, "claimRecapPayment");
            }
            else if (navigation.equalsIgnoreCase("excessReport")){
            	result = excessRecapReport(request, response, "excessClaimRecap");
            }
            else if (navigation.equalsIgnoreCase("groupExcessRecap")){
            	result = groupExcessRecapReport(request, response, "groupExcessClaimRecap");
            }
            else if (navigation.equalsIgnoreCase("claimRecap")){
            	
            }
            else if (navigation.equalsIgnoreCase("groupLimitReport")){
            	
            }
            else if (navigation.equalsIgnoreCase("printExcessLetter")){
            	result = excessReport(request, response, "excessPrinted");
            }
            else if (navigation.equalsIgnoreCase("")){
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        return result;
    }

    public ModelAndView excessRecapReport(HttpServletRequest request, HttpServletResponse response, String location) {
        ModelAndView result = new ModelAndView(location);
        
        try {
	    
	        Date minimumDate = WebUtil.getParameterDate(request, "minimumDate");
	        Date maximumDate = WebUtil.getParameterDate(request, "maximumDate");
	        
	        
	   
        	String[] eqParam  = {"claimTypeId.claimTypeId"};
	        Object[] eqValue  = {ClaimType.CASHLESS};
	        
	        String[] between = {"claimDate"};
	        Object[] minDate = {minimumDate};
	        Object[] maxDate = {maximumDate};
	        
	        String periode = "";
	        DateFormat df = DateFormat.getDateInstance();
	        String minDateStr = df.format(minimumDate);
	        String maxDatestr = df.format(maximumDate);
	        
	        periode = minDateStr + " s/d " + maxDatestr;
	        
	
	        
	        Collection objectList = claimService.searchExcessClaim(null, null, eqParam, eqValue, between, minDate, maxDate, 0, 200);
	        
	        //result.addObject("objectList", new JRBeanCollectionDataSource(objectList));
	        
	        result.addObject("periode", periode);
		 
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        return result;
    }
    public ModelAndView groupExcessRecapReport(HttpServletRequest request, HttpServletResponse response, String location) {
        ModelAndView result = new ModelAndView(location);
        
        try {
	    
        	Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
	        Date minimumDate = WebUtil.getParameterDate(request, "minimumDate");
	        Date maximumDate = WebUtil.getParameterDate(request, "maximumDate");
	        
	        
	        if (memberGroupId != null){
		        MemberGroup memberGroup = memberGroupService.get(memberGroupId);

		        if (memberGroup != null){
			
		        	String groupName = memberGroup.getGroupName();
		        	
		        	String[] eqParam  = {"memberId.memberGroupId.memberGroupId","claimTypeId.claimTypeId"};
			        Object[] eqValue  = {memberGroupId,ClaimType.CASHLESS};
			        
			        String[] between = {"claimDate"};
			        Object[] minDate = {minimumDate};
			        Object[] maxDate = {maximumDate};
			        
			        String periode = "";
			        DateFormat df = DateFormat.getDateInstance();
			        String minDateStr = df.format(minimumDate);
			        String maxDatestr = df.format(maximumDate);
			        
			        periode = minDateStr + " s/d " + maxDatestr;
			        
			
			        
			        Collection objectList = claimService.searchExcessClaim(null, null, eqParam, eqValue, between, minDate, maxDate, 0, 200);
			        
			        if (objectList != null){
			        	System.out.println("JUMLAH OBJECT : " + objectList.size());
			        }
			        //result.addObject("objectList", new JRBeanCollectionDataSource(objectList));
			        result.addObject("groupName",groupName);
			        result.addObject("periode", periode);
		        }
	        }
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        return result;
    }
    public ModelAndView generateGroupReport (HttpServletRequest request, HttpServletResponse response,String location){
    	ModelAndView result = new ModelAndView(location);
    	
    	
    	try {
    		Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
    		
    		
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    public ModelAndView groupBenefitLimitReport(HttpServletRequest request, HttpServletResponse response, String location) {
        ModelAndView result = new ModelAndView(location);
        
        try {
	    
        	Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
	        
	        
	        if (memberGroupId != null){
		        MemberGroup memberGroup = memberGroupService.get(memberGroupId);

		        if (memberGroup != null){
			
		        	String groupName = memberGroup.getGroupName();
		        	
		        	String[] eqParam  = {"memberId.memberGroupId.memberGroupId","claimTypeId.claimTypeId"};
			        Object[] eqValue  = {memberGroupId,ClaimType.CASHLESS};
			        
			        String[] between = {"claimDate"};
			        
			        String periode = "";
			        DateFormat df = DateFormat.getDateInstance();
			        
			        
			
			        
//			        Collection objectList = claimService.searchExcessClaim(null, null, eqParam, eqValue, between, minDate, maxDate, 0, 200);
//			        
//			        if (objectList != null){
//			        	System.out.println("JUMLAH OBJECT : " + objectList.size());
//			        }
			        //result.addObject("objectList", new JRBeanCollectionDataSource(objectList));
			        result.addObject("groupName",groupName);
			        result.addObject("periode", periode);
		        }
	        }
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        return result;
    }
    public ModelAndView excessReport(HttpServletRequest request, HttpServletResponse response, String location) {
        ModelAndView result = new ModelAndView(location);
        
        try {
	    
            Integer claimId = WebUtil.getParameterInteger(request, "claimId");
        	
	        Claim claim = claimService.get(claimId);
	        
	        if (claim != null){
	        	
	        	MemberGroup memberGroup = claim.getMemberId().getMemberGroupId();
	        	
	        	result.addObject("memberName", claim.getMemberName());
	        	result.addObject("memberNumber", claim.getMemberNumber());
	        	result.addObject("claimNumber", claim.getClaimNumber());
	        	result.addObject("claimDate", claim.getClaimDate());
	        	result.addObject("diagnosisName", claim.getDiagnosisId().getDescription());
	        	result.addObject("providerName", claim.getProviderName());
	        	result.addObject("contactPerson", "");
	        	result.addObject("groupName", memberGroup.getGroupName());
	        	result.addObject("groupAddress", memberGroup.getAddress());
	        	
	        	String totalClaimExcessDescription = MoneyParser.convert(claim.getClaimExcessValue().longValue());
	        	result.addObject("valueTotalDescription", totalClaimExcessDescription);
	        	
	        	String[] eqParam = {"deletedStatus","claimId.claimId"};
	        	Object[] eqValue = {Integer.valueOf(0),claim.getClaimId()};
	        	
	        	int totalObject = claimItemService.getTotal(null, null, eqParam, eqValue);
	        	Collection<ClaimItem> objectList = claimItemService.search(null,null,eqParam,eqValue,0,totalObject);
	        	
		        //result.addObject("objectList", new JRBeanCollectionDataSource(objectList));

	        }
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        return result;
    }
    public ModelAndView claimRecapPayment(HttpServletRequest request, HttpServletResponse response, String location) {
        ModelAndView result = new ModelAndView(location);
        
        try {
	    
        	Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
	        Date minimumDate = WebUtil.getParameterDate(request, "minimumDate");
	        Date maximumDate = WebUtil.getParameterDate(request, "maximumDate");
	        
	        
	        if (memberGroupId != null){
		        MemberGroup memberGroup = memberGroupService.get(memberGroupId);

		        if (memberGroup != null){
			
		        	String groupName = memberGroup.getGroupName();
		        	
		        	String[] eqParam  = {"memberId.memberGroupId.memberGroupId"};
			        Object[] eqValue  = {memberGroupId};
			        
			        String[] between = {"claimDate"};
			        Object[] minDate = {minimumDate};
			        Object[] maxDate = {maximumDate};
			        String periode = "";
			        DateFormat df = DateFormat.getDateInstance();
			        String minDateStr = df.format(minimumDate);
			        String maxDatestr = df.format(maximumDate);
			        
			        periode = minDateStr + " s/d " + maxDatestr;
			        
			
			        Collection objectList = claimService.search(null, null, eqParam, eqValue, between, minDate, maxDate, null,0, 200);
			        //result.addObject("objectList", new JRBeanCollectionDataSource(objectList));
			        result.addObject("groupName",groupName);
			        result.addObject("periode", periode);
		        }
	        }
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        return result;
    }
}
