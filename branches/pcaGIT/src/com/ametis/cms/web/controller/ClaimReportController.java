package com.ametis.cms.web.controller;

import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ametis.cms.service.*;
import com.ametis.cms.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.velocity.texen.util.PropertiesUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.MemberClaimDto;
import com.ametis.cms.util.servlet.TableRenderingServlet;


public class ClaimReportController implements Controller {

    private ClaimService claimService;
    private ClaimInvestigationService claimInvestigationService;
    private SecurityService securityService;
    private PendingClaimService pendingClaimService;
    private ClaimItemService claimItemService;
    private UserService userService;
    private PropertiesUtil alertProperties;
    private MemberProductService memberProductService;
    private MemberBenefitService memberBenefitService;
    private ClaimReportService claimReportService;
    private MemberService memberService;
    private PolicyService policyService;
    private ActivityLogService logService;
    private ProductService productService;
    private ClientService clientService;
    private MemberGroupService memberGroupService;
    private HolidayService holidayService;
    private ClaimHistoryService claimHistoryService;
    
    private Integer countSet;
    private Integer maxPercountSet;
    

    public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public ClaimInvestigationService getClaimInvestigationService() {
        return claimInvestigationService;
    }

    public void setClaimInvestigationService(ClaimInvestigationService claimInvestigationService) {
        this.claimInvestigationService = claimInvestigationService;
    }

    public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public PolicyService getPolicyService() {
	return policyService;
}

public void setPolicyService(PolicyService policyService) {
	this.policyService = policyService;
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

    public ClaimReportService getClaimReportService() {
        return claimReportService;
    }

    public void setClaimReportService(ClaimReportService claimReportService) {
        this.claimReportService = claimReportService;
    }

    public MemberProductService getMemberProductService() {
        return memberProductService;
    }

    public void setMemberProductService(
            MemberProductService memberProductService) {
        this.memberProductService = memberProductService;
    }

    public MemberBenefitService getMemberBenefitService() {
        return memberBenefitService;
    }

    public void setMemberBenefitService(
            MemberBenefitService memberBenefitService) {
        this.memberBenefitService = memberBenefitService;
    }

    public ClaimItemService getClaimItemService() {
        return claimItemService;
    }

    public void setClaimItemService(ClaimItemService claimItemService) {
        this.claimItemService = claimItemService;
    }

    public ClaimService getClaimService() {
        return claimService;
    }

    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public PendingClaimService getPendingClaimService() {
        return pendingClaimService;
    }

    public void setPendingClaimService(PendingClaimService pendingClaimService) {
        this.pendingClaimService = pendingClaimService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    public HolidayService getHolidayService() {
		return holidayService;
	}

	public void setHolidayService(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

    public ClaimHistoryService getClaimHistoryService() {
		return claimHistoryService;
	}

	public void setClaimHistoryService(ClaimHistoryService claimHistoryService) {
		this.claimHistoryService = claimHistoryService;
	}

	public Integer getCountSet() {
		return countSet;
	}

	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}

	public Integer getMaxPercountSet() {
		return maxPercountSet;
	}

	public void setMaxPercountSet(Integer maxPercountSet) {
		this.maxPercountSet = maxPercountSet;
	}

	private ModelAndView generateReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }
            String navigation = WebUtil.getParameterString(request, "navigation", "");
            
            if (navigation.equalsIgnoreCase("popuptrack")) {
                location = "popupTrackClaim";
            }
            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");

            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;
            Double claimApprovedValue = 0.0;

            Double claimMinimumValue = WebUtil.getParameterDouble(request,
                    "claimValue");
            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
            
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
            String dateBy = WebUtil.getParameterString(request, "dateBy",
                    "claimDate");
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

            if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("gosearchclaimreconcile")
                    || navigation.equals("printpopup")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        vLikeP.add("providerName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("claimNumber")) {
                        vLikeP.add("claimNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberName")) {
                        vLikeP.add("memberId.firstName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        vLikeP.add("memberId.customerPolicyNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        vLikeP.add("memberId.memberGroupId.groupName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equals("clientName")) {
                        vLikeP.add("memberId.clientId.clientName");
                        vLikeQ.add(searchtext);
                    }
					if (searchby.equals("invoiceNumber")) { //add adq 26082015 invoicenumber
                        vLikeP.add("batchClaimId.invoiceNumber");
                        vLikeQ.add(searchtext);
                    } //end edd 26082015
                    if (searchby.equals("officer")) {
                        vLikeP.add("createdBy");
                        vLikeQ.add(searchtext);
                    }
                    
                    if (searchby.equals("paymentNumber")) {
                        vLikeP.add("paymentId.paymentNumber");
                        vLikeQ.add(searchtext);
                    }

                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    vEqP.add("claimStatus.caseStatusId");
                    vEqQ.add(Integer.valueOf(searchStatus));
                }

                if (claimType != null && claimType.intValue() > 0) {
                    vEqP.add("claimTypeId.claimTypeId");
                    vEqQ.add(claimType);
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    vEqP.add("caseCategoryId.caseCategoryId");
                    vEqQ.add(serviceType);
                }
            }
            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }

            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));


            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            String required[] = new String[]{
                // foreign affairs
                "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId", "Claim.MemberId.ParentMember",
                "Claim.BatchClaimId", "Claim.CaseCategoryId","Claim.DiagnosisId", "Claim.PaymentId"// foreign affairs end
            };

            if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, maxDate, required,
                        claimMinimumValue);
            }
            else if (minimumDate != null && maxDate == null){
            	collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, minimumDate, required,
                        claimMinimumValue);
            }
            else if (maxDate != null && minimumDate == null){
            	collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, maxDate, maxDate, required,
                        claimMinimumValue);
            }
            else {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, claimMinimumValue);
            }

            if (collection != null) {
                Iterator<Claim> iterator = collection.iterator();
                if (iterator != null) {
                    Claim claim = null;

                    while (iterator.hasNext()) {
                        claim = iterator.next();

                        if (claim != null) {

                            double claimItemApproved = 0.0;
                            

                            Collection<ClaimItem> claimItems = claimItemService.search(null, null, "claimId.claimId",
                                    claim.getClaimId(), 0, 100);

                            if (claimItems != null) {
                                Iterator<ClaimItem> iterators = claimItems.iterator();

                                if (iterators != null) {
                                    ClaimItem citem = null;
                                    while (iterators.hasNext()) {
                                        citem = iterators.next();

                                        if (citem != null) {

                                            if (citem.getClaimItemApprovedValue() != null) {
                                                claimItemApproved += citem.getClaimItemApprovedValue();
                                            }
                                            
                                        }
                                    }
                                }
                            }

                            if (claim.getClaimChargeValue() != null) {
                                claimChargeValue += claim.getClaimChargeValue();
                            }
                            
                            if (claim.getClaimPaidValue() != null) {
                            	claimPaidValue += claim.getClaimPaidValue();
                            }

                            
                            claimApprovedValue += claimItemApproved;

                            claim.setClaimApprovedValue(claimItemApproved);
                            

                            if (claim.getClaimPaidValue() != null) {
                                // claimPaidValue += claim.getClaimPaidValue();
                            } else {
                                claimPaidValue += 0;
                            }
                            
                            if (claim.getClaimApprovedValue() != null) {
                                // claimPaidValue += claim.getClaimPaidValue();
                            } else {
                            	claimApprovedValue += 0;
                            }

                            if (claim.getClaimExcessValue() != null) {
                                claimExcessValue += claim.getClaimExcessValue();
                            } else {
                                claimExcessValue += 0;
                            }
                        }
                    }
                }
            }
            
            Collection<Claim> claims = collection;
            Integer[] holiday =  new Integer[claims.size()];
            Integer row = 0;
            
            for(Claim claimSLA : claims){
            	            	            	
            	System.out.println("claim id : " + claimSLA.getClaimId());
            	Date paymentTime = null ;
            	int jmlhSLA = 1 ;
            	int libur = 0;
            	
            	if(claimSLA.getPaymentId() != null){
            		paymentTime = claimSLA.getPaymentId().getPaymentTime();
            		libur = holidayService.getHolidaySLA(claimSLA.getClaimDate(), paymentTime);
            		jmlhSLA = getWorkingDaysBetweenTwoDates(claimSLA.getClaimDate(), paymentTime);
            	}else{
            		jmlhSLA = 0 ;
            	}
            	
            	holiday[row] = jmlhSLA - libur;
            	row++;
            }
            
            System.out.println("holiday : " + holiday);

            request.setAttribute("holiday", holiday);
            request.setAttribute("serviceType", serviceType);
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            request.setAttribute("claimApprovedValue", claimApprovedValue);
            

            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            
            request.setAttribute("dateBy", dateBy);
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }

    private ModelAndView trackSLAReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }
            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            if (navigation.equalsIgnoreCase("popuptrack")) {
                location = "popupTrackClaim";
            }
            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");

            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;
            String dateBy = WebUtil.getParameterString(request, "dateBy",
                    "claimDate");

            Double claimMinimumValue = WebUtil.getParameterDouble(request,
                    "claimValue");
            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            System.out.println("minimum_date = " + minimumDate);
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
            System.out.println("maxDate = " + maxDate);
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
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

            if (navigation.equals("gosearch") || navigation.equals("golookup")
                    || navigation.equals("printpopup")
                    || navigation.equals("gotracksla")
                    || navigation.equals("printoutsla")) {

            	  if (searchby != null) {
                      /**
                       * ini querynya disesuaikan dengan apa yang mau di search
                       * default nya gue bikin template search by semua field yang
                       * tipenya 'String' -adhit
                       */
                      if (searchby.equalsIgnoreCase("providerName")) {
                          vLikeP.add("providerName");
                          vLikeQ.add(searchtext);
                      }
                      if (searchby.equalsIgnoreCase("claimNumber")) {
                          vLikeP.add("claimNumber");
                          vLikeQ.add(searchtext);
                      }
                      if (searchby.equalsIgnoreCase("memberName")) {
                          vLikeP.add("memberId.firstName");
                          vLikeQ.add(searchtext);
                      }
                      if (searchby.equalsIgnoreCase("policyNumber")) {
                          vLikeP.add("memberId.customerPolicyNumber");
                          vLikeQ.add(searchtext);
                      }
                      if (searchby.equalsIgnoreCase("memberGroupName")) {
                          vLikeP.add("memberId.memberGroupId.groupName");
                          vLikeQ.add(searchtext);
                      }

                      if (searchby.equals("clientName")) {
                          vLikeP.add("memberId.clientId.clientName");
                          vLikeQ.add(searchtext);
                      }
  					if (searchby.equals("invoiceNumber")) { //add adq 26082015 invoicenumber
                          vLikeP.add("batchClaimId.invoiceNumber");
                          vLikeQ.add(searchtext);
                      } //end edd 26082015
                      if (searchby.equals("officer")) {
                          vLikeP.add("createdBy");
                          vLikeQ.add(searchtext);
                      }
                      
                      if (searchby.equals("paymentNumber")) {
                          vLikeP.add("paymentId.paymentNumber");
                          vLikeQ.add(searchtext);
                      }

                  }

                  if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                      vEqP.add("claimStatus.caseStatusId");
                      vEqQ.add(Integer.valueOf(searchStatus));
                  }

                  if (claimType != null && claimType.intValue() > 0) {
                      vEqP.add("claimTypeId.claimTypeId");
                      vEqQ.add(claimType);
                  }
                  if (serviceType != null && serviceType.intValue() > 0) {
                      vEqP.add("caseCategoryId.caseCategoryId");
                      vEqQ.add(serviceType);
                  }
              }
              if (user != null){
              	if (user.getUser().getUserType().intValue() == User.CLIENT){
              		vEqP.add("clientId");
              		vEqQ.add(user.getUser().getClientId().getClientId());
              	}
              	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
              		vEqP.add("memberId.memberGroupId.memberGroupId");
              		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
              	}
              	if (user.getUser().getUserType().intValue() == User.PROVIDER){
              		vEqP.add("providerId.providerId");
              		vEqQ.add(user.getUser().getProviderId().getProviderId());
              	}
              	
              }

              vEqP.add("deletedStatus");
              vEqQ.add(Integer.valueOf(0));


              String sLikeP[] = new String[vLikeP.size()];
              vLikeP.toArray(sLikeP);
              Object sLikeQ[] = new Object[vLikeP.size()];
              vLikeQ.toArray(sLikeQ);

              String sEqP[] = new String[vEqP.size()];
              vEqP.toArray(sEqP);
              Object sEqQ[] = new Object[vEqP.size()];
              vEqQ.toArray(sEqQ);

              String required[] = new String[]{
                  // foreign affairs
                  "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId", "Claim.MemberId.ParentMember",
                  "Claim.BatchClaimId", "Claim.CaseCategoryId","Claim.DiagnosisId", "Claim.PaymentId"// foreign affairs end
              };


            /*String required[] = new String[]{
                    // foreign affairs
                    "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId", "Claim.ProviderId", "Claim.MemberId.ParentMember",
                    "Claim.BatchClaimId", "Claim.CaseCategoryId","Claim.PaymentId", "Claim.CaseId",// foreign affairs end
                    "Claim.DiagnosisId"
            };*/

            /*if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, maxDate, required,
                        claimMinimumValue);
            } else {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, claimMinimumValue);
            }*/
            
            if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, maxDate, required,
                        claimMinimumValue);
            }
            else if (minimumDate != null && maxDate == null){
            	collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, minimumDate, required,
                        claimMinimumValue);
            }
            else if (maxDate != null && minimumDate == null){
            	collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, maxDate, maxDate, required,
                        claimMinimumValue);
            }
            else {
            	  collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                          required, claimMinimumValue);
            }
            
            System.out.println("collection.size 1 = " + collection.size());

            if (collection != null) {
                Iterator<Claim> iterator = collection.iterator();
                if (iterator != null) {
                    Claim claim = null;

                    while (iterator.hasNext()) {
                        claim = iterator.next();

                        if (claim != null) {

                            double claimItemApproved = 0.0;

                            Collection<ClaimItem> claimItems = claimItemService.search(null, null, "claimId.claimId",
                                    claim.getClaimId(), 0, 100);

                            if (claimItems != null) {
                                Iterator<ClaimItem> iterators = claimItems.iterator();

                                if (iterators != null) {
                                    ClaimItem citem = null;
                                    while (iterators.hasNext()) {
                                        citem = iterators.next();

                                        if (citem != null) {

                                            if (citem.getClaimItemApprovedValue() != null) {
                                                claimItemApproved += citem.getClaimItemApprovedValue();
                                            }
                                        }
                                    }
                                }
                            }

                            if (claim.getClaimChargeValue() != null) {
                                claimChargeValue += claim.getClaimChargeValue();
                            }

                            claimPaidValue += claimItemApproved;

                            claim.setClaimApprovedValue(claimItemApproved);

                            if (claim.getClaimPaidValue() != null) {
                                // claimPaidValue += claim.getClaimPaidValue();
                            } else {
                                claimPaidValue += 0;
                            }

                            if (claim.getClaimExcessValue() != null) {
                                claimExcessValue += claim.getClaimExcessValue();
                            } else {
                                claimExcessValue += 0;
                            }
                        }
                    }
                }
            }
            
            System.out.println("collection.size 2 = " + collection.size());
            
            Collection<Claim> claimm = collection ;
            
            /*for(Claim claim : claimm ){
            	int jmlhSLA = getWorkingDaysBetweenTwoDates(claim.getClaimDate(), claim.getPaymentId().getPaymentTime());
            	
            	request.setAttribute("jmlhSLA", jmlhSLA);
            }*/

            if(navigation.equals("printoutsla")) {
                HSSFWorkbook workbook = SLAReportGenerator.generateReport(collection, claimInvestigationService);

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);

                response.setHeader("Content-disposition", "inline; filename="
                        + "slatrack.xls");

                ServletOutputStream sos = response.getOutputStream();

                workbook.write(sos);
                // sos.write(workbook.getBytes());
                sos.close();
            }

            request.setAttribute("serviceType", serviceType);
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }


    private ModelAndView cynergyClaimReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }
            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            if (navigation.equalsIgnoreCase("popuptrack")) {
                location = "popupTrackClaim";
            }
            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");

            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;
            String dateBy = WebUtil.getParameterString(request, "dateBy",
                    "claimDate");

            Double claimMinimumValue = WebUtil.getParameterDouble(request,
                    "claimValue");
            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
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

            if (navigation.equals("docynergy")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        vLikeP.add("providerName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("memberName")) {
                        vLikeP.add("memberId.firstName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        vLikeP.add("memberId.customerPolicyNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        vLikeP.add("memberId.memberGroupId.groupName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equals("clientName")) {
                        vLikeP.add("memberId.clientId.clientName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equals("claimNumber")) {
                        vLikeP.add("claimNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equals("paymentNumber")) {
                        vLikeP.add("paymentNumber");
                        vLikeQ.add(searchtext);
                    }
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    vEqP.add("claimStatus.caseStatusId");
                    vEqQ.add(Integer.valueOf(searchStatus));
                }

                if (claimType != null && claimType.intValue() > 0) {
                    vEqP.add("claimTypeId.claimTypeId");
                    vEqQ.add(claimType);
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    vEqP.add("caseCategoryId.caseCategoryId");
                    vEqQ.add(serviceType);
                }
            }


            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            String required[] = new String[]{
                // foreign affairs
                "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
                "Claim.BatchClaimId", "Claim.CaseCategoryId","Claim.MemberId.ParentMember","Claim.ProviderId"
            };

            if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, maxDate, required,
                        claimMinimumValue);
            } else {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, claimMinimumValue);
            }

            request.setAttribute("serviceType", serviceType);
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));
            
            if (navigation.equalsIgnoreCase("exportcynergy")){
            
	            HSSFWorkbook workbook = ClaimCynergyDownloader.downloadReport(collection, claimItemService);
	
	            String filename = "group-member-claim.xls";
	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition",
	                    "attachment; filename=" + filename);
	
	            response.setHeader("Pragma", "no-cache");
	            response.setHeader("Cache-Control", "no-cache");
	            response.setDateHeader("Expires", 0);
	
	            ServletOutputStream sos = response.getOutputStream();
	
	            // response.setHeader("Content-length", Integer
	            // .toString(workbook.getBytes().length));
	
	            workbook.write(sos);
	            // sos.write(workbook.getBytes());
	            sos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = new ModelAndView("error");
        }

        return result;

    }
    private ModelAndView topXClaimReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }
            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            if (navigation.equalsIgnoreCase("popuptrack")) {
                location = "popupTrackClaim";
            }
            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");


            Integer policyId = WebUtil.getParameterInteger(request,
                    "policyId");
            
            Integer totalTop = WebUtil.getParameterInteger(request, "topTotal");


            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            Integer groupId = WebUtil.getParameterInteger(request, "memberGroupId");
            

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");
            
            
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


            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}            	
            }
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);



            
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));
            
            if (navigation.equalsIgnoreCase("exporttopx")){
            	
            	String memberGroupName = "";
            	String clientName = "";
            	
            	if (clientId != null){
            		Client client = clientService.get(clientId);
            		if (client != null){
            			clientName = client.getClientName();
            		}
            	}
            	if (groupId != null){
            		MemberGroup memberGroup  = memberGroupService.get(groupId);
            		if (memberGroup != null){
            			memberGroupName = memberGroup.getGroupName();
            		}
            	}
            
	            HSSFWorkbook workbook = ReportTop10Generator.generateReport(clientId,groupId, minimumDate, maxDate,
	            		totalTop,productService, claimReportService,clientName,memberGroupName);
	
	            String filename = "group-top-x-report.xls";
	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition",
	                    "attachment; filename=" + filename);
	
	            response.setHeader("Pragma", "no-cache");
	            response.setHeader("Cache-Control", "no-cache");
	            response.setDateHeader("Expires", 0);
	
	            ServletOutputStream sos = response.getOutputStream();
	            
	            workbook.write(sos);
	            sos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = new ModelAndView("error");
        }

        return result;

    }

    private ModelAndView generateClaimStatisticReport(
            HttpServletRequest request, HttpServletResponse response,
            String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");

            if (navigation.equalsIgnoreCase("claimstatreport")) {
                location = "claimStatisticReport";
            } else if (navigation.equalsIgnoreCase("popupclaimstat")) {
                location = "popupClaimStatisticReport";
            }

            result = new ModelAndView(location);

            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");

            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer memberGroupId = WebUtil.getParameterInteger(request,
                    "memberGroupId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");

            String dateBy = WebUtil.getParameterString(request, "dateBy", "");

            result.addObject("subnavigation", subnavigation);

            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
            
            Integer totalIndex = WebUtil.getParameterInteger(request,
                    "totalIndex") == null ? 10 : WebUtil.getParameterInteger(
                    request, "totalIndex");

            /**
             * parameter client belum ada!
             */
            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		Collection<Object[]> coll = claimReportService.generateClaimReport(
                            serviceType, user.getUser().getClientId().getClientId(), minimumDate, maxDate,
                            totalIndex);
            	}
            	else if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		Collection<Object[]> coll = claimReportService.generateClaimReport(
                            serviceType, user.getUser().getMemberGroupId().getMemberGroupId(), minimumDate, maxDate,
                            totalIndex);
            	}
            }
            

            request.setAttribute("serviceType", serviceType);
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("status", searchStatus);

            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }

    private ModelAndView generateTrackStatisticReport(
            HttpServletRequest request, HttpServletResponse response,
            String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");
            
            String dateBy = WebUtil.getParameterString(request, "dateBy", "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            Integer reportFormat = WebUtil.getParameterInteger(request, "reportFormat");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

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

            if (navigation.equals("gosearch") || navigation.equals("golookup")
                    || navigation.equalsIgnoreCase("printoutreport")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        vLikeP.add("providerName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("claimNumber")) {
                        vLikeP.add("claimNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberName")) {
                        vLikeP.add("memberId.firstName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        vLikeP.add("memberId.customerPolicyNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        vLikeP.add("memberId.memberGroupId.groupName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equals("clientName")) {
                        vLikeP.add("memberId.clientId.clientName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("officer")) {
                        vLikeP.add("createdBy");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("paymentNumber")) {
                        vLikeP.add("paymentId.paymentNumber");
                        vLikeQ.add(searchtext);
                    }
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    vEqP.add("claimStatus.caseStatusId");
                    vEqQ.add( Integer.valueOf(searchStatus));
                }

                if (claimType != null && claimType.intValue() > 0) {
                    vEqP.add("claimTypeId.claimTypeId");
                    vEqQ.add(claimType);
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    vEqP.add("caseCategoryId.caseCategoryId");
                    vEqQ.add(serviceType);
                }

            }


            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            // String required[] = new String[] {
            // // foreign affairs
            // "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
            // "Claim.BatchClaimId", "Claim.CaseCategoryId",
            // // foreign affairs end
            // };

            String required[] = new String[]{ // foreign affairs
            };
            if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, maxDate, required, null);
            } else {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, null);
            }

            int idx = 1;
            String data = "No|Policy Holder|Claim Number| CDV Number| Name of Member|Member ID| Relation| Sex| Date of Birth";
            data += "|Plan|Class|Inception Date| Maturity Date| Date of Claim Event|Discharge Date| Date of Claim Accepted";
            data += "|CDV Date| Date of Claim Paid|Facility| Facility Area| Claim Type| Limit Benefit| Remaining Benefit| Length of Stay";
            data += "|Action Type|Diagnose| Payment Type|Charge|Paid|Declined|Excess|Charge| Paid| Declined| Excess| Currency| Declined Reason| Bank| Bank Account";
            data += "\n\n";
            if (collection != null) {
                Iterator<Claim> iterator = collection.iterator();

                Collection<ClaimItem> claimItems = null;

                if (iterator != null) {
                    Claim claim = null;

                    while (iterator.hasNext()) {

                        claim = iterator.next();

                        if (claim != null) {

                            claimItems = claimItemService.search(null, null,
                                    "claimId.claimId", claim.getClaimId(), 0,
                                    100);

                            if (claim.getClaimChargeValue() != null) {
                                claimChargeValue += claim.getClaimChargeValue();
                            }

                            if (claim.getClaimPaidValue() != null) {
                                claimPaidValue += claim.getClaimPaidValue();
                            } else {
                                claimPaidValue += 0;
                            }

                            if (claim.getClaimExcessValue() != null) {
                                claimExcessValue += claim.getClaimExcessValue();
                            } else {
                                claimExcessValue += 0;
                            }

                            Member member = claim.getMemberId();

                            String provider = "";
                            String areaProvider = "";

                            if (claim.getProviderId() != null) {
                                provider = claim.getProviderId().getProviderName();

                                areaProvider = claim.getProviderId().getCity();
                            } else {
                                provider = claim.getProviderName();
                            }

                            double limit = 0;
                            double remaining = 0;
                            double claimDeclinedValue = 0;
                            double excessClaim = 0;
                            double paidValue = 0;
                            String annualLimit = "";
                            String kelas = "";

                            String cdvNumber = "";
                            String cdvDate = "";
                            String paymentConfirmedDate = "";
                            String bank = "";
                            String bankAccount = "";
                            String payeeName = "";

                            if (claim.getPaymentId() != null) {
                                cdvNumber = claim.getPaymentId().getPaymentNumber();

                                if (claim.getPaymentId().getPaymentTime() != null) {
                                    cdvDate = claim.getPaymentId().getPaymentTime().toString();
                                }

                                if (claim.getPaymentId().getPaymentConfirmDate() != null) {
                                    paymentConfirmedDate = claim.getPaymentId().getPaymentConfirmDate().toString();
                                }
                                bank = claim.getPaymentId().getBankName();
                                bankAccount = claim.getPaymentId().getAccountNumber();
                                payeeName = claim.getPaymentId().getPayeeName();
                            }

                            if (member.getCustomerLimit() != null) {
                                limit = member.getCustomerLimit().doubleValue();
                            }

                            if (member.getActualCustomerLimit() != null) {
                                remaining = claim.getRemainingMemberLimit() == null ? 0
                                        : claim.getRemainingMemberLimit();
                            }

                            if (claim.getClaimApprovedValue() != null) {
                                paidValue = claim.getClaimApprovedValue().doubleValue();
                            }

                            MemberProduct memberProduct = memberProductService.getMemberActiveProduct(member.getMemberId(), claim.getCaseCategoryId().getCaseCategoryId());

                            if (memberProduct != null) {
                                Product product = memberProduct.getProductId();

                                if (product != null) {
                                    kelas = product.getServiceClass();

                                    if (product.getAnnualLimitValue() != null) {
                                        limit = product.getAnnualLimitValue();
                                    }
                                }
                            }

                            String declinedReason = "";

                            data += idx + "|"
                                    + member.getMemberGroupId().getGroupName()
                                    + "|" + claim.getClaimNumber() + "|"
                                    + cdvNumber + "|\"" + member.getFirstName();
                            data += "\"|" + member.getCustomerPolicyNumber()
                                    + "|" + member.getRelationship() + "|";
                            data += member.getGender()
                                    + "|"
                                    + member.getBirthday()
                                    + "|"
                                    + claim.getCaseCategoryId().getCaseCategoryCode();
                            data += "|" + kelas + "|"
                                    + member.getEffectiveDate() + "|"
                                    + member.getExpireDate();
                            data += "|"
                                    + claim.getAdmissionDate()         
                                    + "|"
                                    + claim.getDischargeDate()
                                    + "|"                          
                                    + claim.getBatchClaimId().getBatchClaimDate() + "|"
                                    + cdvDate + "|" + paymentConfirmedDate;
                            data += "|\""
                                    + provider
                                    + "\"|\""
                                    + areaProvider
                                    + "\"|"
                                    + claim.getCaseCategoryId().getCaseCategoryCode() + "|"
                                    + limit;
                            data += "|"
                                    + remaining
                                    + "|||"
                                    + claim.getDiagnosisId().getDiagnosisCode()
                                    + "|"
                                    + claim.getBatchClaimId().getBatchClaimType().getClaimTypeCode();
                            data += "|||||" + claim.getClaimChargeValue() + "|"
                                    + paidValue + "," + claimDeclinedValue
                                    + "|" + excessClaim + "||\""
                                    + declinedReason + "\"|\"" + bank + "\"|\""
                                    + bankAccount + "\"" + "|\"" + payeeName
                                    + "\"";

                            declinedReason = "";
                            // delimiter untuk new line
                            data += "\n";
                            cdvNumber = "";
                            cdvDate = "";
                            paymentConfirmedDate = "";
                            idx += 1;

                        }
                    }
                }
            }

            System.out.println("DATA : " + data);

            response.setContentType("application/x-csv");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setHeader("Content-disposition", "inline; filename="
                    + "claimreport.csv");

            ServletOutputStream sos = response.getOutputStream();

            response.setHeader("Content-length", Integer.toString(data.length()));

            sos.write(data.getBytes());
            sos.close();

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }


    private ModelAndView generateXLSTrackStatisticReport(
            HttpServletRequest request, HttpServletResponse response,
            String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            Integer reportFormat = WebUtil.getParameterInteger(request, "reportFormat");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            String dateBy = WebUtil.getParameterString(request, "dateBy", "claimDate");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

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

            if (navigation.equals("gosearch") || navigation.equals("golookup")
                    || navigation.equalsIgnoreCase("printoutreport")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        vLikeP.add("providerName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("memberName")) {
                        vLikeP.add("memberId.firstName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        vLikeP.add("memberId.customerPolicyNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        vLikeP.add("groupName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equals("clientName")) {
                        vLikeP.add("memberId.clientId.clientName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equals("invoiceNumber")) { //add adq 26082015 invoicenumber
                        vLikeP.add("batchClaimId.invoiceNumber");
                        vLikeQ.add(searchtext);
                    } //end add

                    if (searchby.equalsIgnoreCase("officer")) {
                        vLikeP.add("createdBy");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("paymentNumber")) {
                        vLikeP.add("paymentId.paymentNumber");
                        vLikeQ.add(searchtext);
                    }
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    vEqP.add("claimStatus.caseStatusId");
                    vEqQ.add( Integer.valueOf(searchStatus));
                }

                if (claimType != null && claimType.intValue() > 0) {
                    vEqP.add("claimTypeId.claimTypeId");
                    vEqQ.add(claimType);
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    vEqP.add("caseCategoryId.caseCategoryId");
                    vEqQ.add(serviceType);
                }

            }


            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            

            String required[] = new String[]{ "Claim.BatchClaimId","Claim.MemberId","Claim.ProviderId","Claim.MemberId.ParentMember","Claim.CaseCategoryId"};
            if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,dateBy
                        , minimumDate, maxDate, required, null);
            } else {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, null);
            }


            HSSFWorkbook workbook = GeneralClaimDownloader.downloadReport(collection,claimItemService);
            
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setHeader("Content-disposition", "inline; filename="
                    + "claimreport.xls");

            ServletOutputStream sos = response.getOutputStream();




            workbook.write(sos);
            // sos.write(workbook.getBytes());
            sos.close();

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }            
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }
private ModelAndView generatePrintoutTrackReport(
            HttpServletRequest request, HttpServletResponse response,
            String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            
            String dateBy = WebUtil.getParameterString(request, "dateBy", "claimDate");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

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

            if (navigation.equals("gosearch") || navigation.equals("golookup")
                    || navigation.equalsIgnoreCase("printoutreport")
                    || navigation.equalsIgnoreCase("printouttrack")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        vLikeP.add("providerName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("memberName")) {
                        vLikeP.add("memberId.firstName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        vLikeP.add("memberId.customerPolicyNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        vLikeP.add("memberId.memberGroupId.groupName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equals("clientName")) {
                        vLikeP.add("memberId.clientId.clientName");
                        vLikeQ.add(searchtext);
                    }
					if (searchby.equals("invoiceNumber")) { //add adq 26082015 invoicenumber
                        vLikeP.add("batchClaimId.invoiceNumber");
                        vLikeQ.add(searchtext);
                    } //end add
                    
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    vEqP.add("claimStatus.caseStatusId");
                    vEqQ.add(Integer.valueOf(searchStatus));
                }

                if (claimType != null && claimType.intValue() > 0) {
                    vEqP.add("claimTypeId.claimTypeId");
                    vEqQ.add(claimType);
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    vEqP.add("caseCategoryId.caseCategoryId");
                    vEqQ.add(serviceType);
                }

            }
            

            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }

            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            String required[] = new String[]{
                // foreign affairs
                "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
                "Claim.BatchClaimId", "Claim.CaseCategoryId","Claim.PaymentId", "Claim.ProviderId","Claim.DiagnosisId"// foreign affairs end
            };

            if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, maxDate, required, null);
            } else {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, null);
            }

            int idx = 1;
            String data = "No|Claim Number|Date of Claim Accepted| Group| Member Name| Member Number";
            data += "|Provider Name|Charge|Paid|Declined|Excess| Diagnosis";
            data += "|CDV Date| CDV Number| Date of Claim Paid|Operator";
            data += "";
            data += "\n\n";
            if (collection != null) {
                Iterator<Claim> iterator = collection.iterator();

                Collection<ClaimItem> claimItems = null;

                if (iterator != null) {
                    Claim claim = null;
                    String[] requiredItem = {"ClaimItem.ItemId"};

                    while (iterator.hasNext()) {

                        claim = iterator.next();

                        if (claim != null) {

                            claimItems = claimItemService.search(null, null,
                                    "claimId.claimId", claim.getClaimId(),requiredItem, 0,
                                    100);

                            if (claim.getClaimChargeValue() != null) {
                                claimChargeValue += claim.getClaimChargeValue();
                            }

                            if (claim.getClaimPaidValue() != null) {
                                claimPaidValue += claim.getClaimPaidValue();
                            } else {
                                claimPaidValue += 0;
                            }

                            if (claim.getClaimExcessValue() != null) {
                                claimExcessValue += claim.getClaimExcessValue();
                            } else {
                                claimExcessValue += 0;
                            }

                            Member member = claim.getMemberId();

                            String provider = "";
                            String areaProvider = "";

                            if (claim.getProviderId() != null) {
                                provider = claim.getProviderId().getProviderName();

                                areaProvider = claim.getProviderId().getCity();
                            } else {
                                provider = claim.getProviderName();
                            }

                            double limit = 0;
                            double remaining = 0;
                            double claimDeclinedValue = 0;
                            double excessClaim = 0;
                            double paidValue = 0;
                            String annualLimit = "";
                            String kelas = "";
                            String cdvNumber = "";
                            String cdvDate = "";
                            String cdvConfirmDate = "";

                            if (claim.getPaymentId() != null) {
                                cdvNumber = claim.getPaymentId().getPaymentNumber();

                                cdvDate = claim.getPaymentId().getPaymentTime() == null ? ""
                                        : claim.getPaymentId().getPaymentTime().toString();

                                if (claim.getPaymentId().getPaymentConfirmDate() != null) {
                                    cdvConfirmDate = claim.getPaymentId().getPaymentConfirmDate().toString();
                                }

                            }

                            if (member.getCustomerLimit() != null) {
                                limit = member.getCustomerLimit().doubleValue();
                            }

                            if (member.getActualCustomerLimit() != null) {
                                remaining = claim.getRemainingMemberLimit() == null ? 0
                                        : claim.getRemainingMemberLimit();
                            }

                            if (claim.getClaimApprovedValue() != null) {
                                paidValue = claim.getClaimApprovedValue().doubleValue();
                            }

                            data += idx + "|" + claim.getClaimNumber() + "|"
                                    + claim.getClaimDate() + "|"
                                    + member.getMemberGroupId().getGroupName()
                                    + "|\"" + member.getFirstName() + "\"|"
                                    + member.getCustomerPolicyNumber();

                            data += "|" + provider + "|"
                                    + claim.getClaimChargeValue() + "|"
                                    + paidValue + "|" + excessClaim + "|";
                            data += "|"
                                    + claim.getDiagnosisId().getDiagnosisCode()
                                    + "|" + cdvDate + "|" + cdvNumber + "|"
                                    + cdvConfirmDate + "|"
                                    + claim.getCreatedBy() + "\n";

                            idx += 1;

                        }
                    }
                }
            }

            System.out.println("DATA : " + data);

            response.setContentType("application/x-csv");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setHeader("Content-disposition", "inline; filename="
                    + "claimtrackreport.csv");

            ServletOutputStream sos = response.getOutputStream();

            response.setHeader("Content-length", Integer.toString(data.length()));

            sos.write(data.getBytes());
            sos.close();

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }

    private ModelAndView generateMemberClaimReport(HttpServletRequest request,
            HttpServletResponse response, String location) {

        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");

            /*
             * belum ada mekanisme filtering berdasarkan clientId, memberGroupId, providerId
             */
            if (navigation.equalsIgnoreCase("downloadMemberClaim")) {

                Integer memberGroupId = WebUtil.getParameterInteger(request,
                        "memberGroupId");

                String subnavigation = WebUtil.getParameterString(request,
                        "subnavigation", "");
                result.addObject("subnavigation", subnavigation);

                String dateBy = WebUtil.getParameterString(request, "dateBy", "claimDate");
                Date minimumDate = WebUtil.getParameterDate(request,
                        "minimum_date");
                Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

                Collection<MemberClaimDto> memberClaimList = new Vector<MemberClaimDto>();

                Collection<Member> memberList = memberService.getMemberList(memberGroupId);

                for (Member member : memberList) {

                    MemberClaimDto memberClaimDto = new MemberClaimDto();
                    memberClaimDto.setMember(member);

                    Collection<ClaimDto> claimDtoList = claimService.getMemberClaimList(member.getMemberId(),
                            minimumDate, maxDate);

                    memberClaimDto.setClaimDto(claimDtoList);

                    memberClaimList.add(memberClaimDto);
                }

                HSSFWorkbook workbook = ClaimRelifeDownloader.downloadMember(memberClaimList);

                String filename = "group-member-claim.xls";
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + filename);

                response.setHeader("Pragma", "no-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);

                ServletOutputStream sos = response.getOutputStream();

                // response.setHeader("Content-length", Integer
                // .toString(workbook.getBytes().length));

                workbook.write(sos);
                // sos.write(workbook.getBytes());
                sos.close();
                
                request.setAttribute("dateBy", dateBy);
                request.setAttribute("minDate", minimumDate);
                request.setAttribute("maxDate", maxDate);
                
                if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                    request.setAttribute("minDate", "");
                    
                }
                if (maxDate != null && maxDate.toString().equals("1970-01-01")){
                	request.setAttribute("maxDate", "");
                }
            }
            
            request.setAttribute("navigation", navigation);

            request.setAttribute("index", 0);
            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }
    private ModelAndView jsonOutstandingClaimReport(HttpServletRequest request,
            HttpServletResponse response, String location) {

        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView("jsonTotalPendingClaim");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            
            if (user.getUser() != null && user.getUser().getUserType().intValue() == User.CLIENT){
            	String totalOutstanding = claimReportService.generateClientOutstandingClaim(user.getUser().getClientId().getClientId());
            	result.addObject("result", totalOutstanding);
            }
           

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }
    private ModelAndView generatePrintoutSLAReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");
            String dateBy = WebUtil.getParameterString(request, "dateBy", "claimDate");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

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

            if (navigation.equals("gosearch") || navigation.equals("golookup")
                    || navigation.equalsIgnoreCase("printoutreport")
                    || navigation.equalsIgnoreCase("printouttrack")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        vLikeP.add("providerName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("memberName")) {
                        vLikeP.add("memberId.firstName");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        vLikeP.add("memberId.customerPolicyNumber");
                        vLikeQ.add(searchtext);
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        vLikeP.add("memberId.memberGroupId.groupName");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equals("clientName")) {
                        vLikeP.add("memberId.clientId.clientName");
                        vLikeQ.add(searchtext);
                    }
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    vEqP.add("claimStatus.caseStatusId");
                    vEqQ.add(Integer.valueOf(searchStatus));
                }

                if (claimType != null && claimType.intValue() > 0) {
                    vEqP.add("claimTypeId.claimTypeId");
                    vEqQ.add(claimType);
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    vEqP.add("caseCategoryId.caseCategoryId");
                    vEqQ.add(serviceType);
                }

            }


            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            String required[] = {"Claim.PaymentId"};

            if (minimumDate != null && maxDate != null) {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        dateBy, minimumDate, maxDate, required, null);
            } else {
                collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                        required, null);
            }

//            int idx = 1;
//            String data = "No|Claim Number|Admission Date| Received Date| CDV Date|CDV Number| CDV Confirm Date| Claim Charge| Payment Value";
//            data += "\n\n";
//            if (collection != null) {
//                Iterator<Claim> iterator = collection.iterator();
//
//                Collection<ClaimItem> claimItems = null;
//
//                if (iterator != null) {
//                    Claim claim = null;
//
//                    String cdvNumber = "";
//                    String cdvDate = "";
//                    String cdvConfirmDate = "";
//                    String paymentValue = "";
//                    String claimCharge = "";
//
//                    while (iterator.hasNext()) {
//
//                        claim = iterator.next();
//
//                        if (claim != null) {
//
//                            if (claim.getPaymentId() != null) {
//                                cdvNumber = claim.getPaymentId().getPaymentNumber();
//
//                                NumberFormat nf = NumberFormat.getInstance();
//
//                                paymentValue = claim.getClaimApprovedValue().toString();
//                                claimCharge = claim.getClaimChargeValue().toString();
//
//                                cdvDate = claim.getPaymentId().getPaymentTime() == null ? ""
//                                        : claim.getPaymentId().getPaymentTime().toString();
//
//                                if (claim.getPaymentId().getPaymentConfirmDate() != null) {
//                                    cdvConfirmDate = claim.getPaymentId().getPaymentConfirmDate().toString();
//                                }
//
//                            }
//
//                            data += idx + "|" + claim.getClaimNumber() + "|"
//                                    + claim.getAdmissionDate() + "|"
//                                    + claim.getClaimDate() + "|" + cdvDate
//                                    + "|" + cdvNumber + "|" + cdvConfirmDate
//                                    + "|" + claimCharge + "|" + paymentValue
//                                    + "\n";
//
//                            cdvNumber = "";
//                            cdvDate = "";
//                            cdvConfirmDate = "";
//
//                            idx += 1;
//
//                        }
//                    }
//                }
//            }
//
//            System.out.println("DATA : " + data);
//
//            response.setContentType("application/x-csv");
//            response.setHeader("Pragma", "no-cache");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expires", 0);
//
//            response.setHeader("Content-disposition", "inline; filename="
//                    + "slatrack.csv");
//
//            ServletOutputStream sos = response.getOutputStream();
//
//            response.setHeader("Content-length", Integer.toString(data.length()));
//
//            sos.write(data.getBytes());
//            sos.close();


            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }

    private ModelAndView generatePrintoutReportReconcile(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");
            String dateBy = WebUtil.getParameterString(request, "dateBy", "claimDate");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");

            Integer formatDocument = WebUtil.getParameterInteger(request, "reportFormat");


            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

            int minIndex = 0;
            int maxIndex = 0;
            int totalIndex = 0;

            Collection collection = null;

            int rowsetint = 0;
            int count = 0;

            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            int idx = 1;

            if (StringUtils.isNumeric(rowset)) {
                rowsetint = Integer.parseInt(rowset);
            }
            Vector vLikeP = new Vector();
            Vector vLikeQ = new Vector();
            Vector vEqP = new Vector();
            Vector vEqQ = new Vector();

            if (navigation.equals("gosearch") || navigation.equals("golookup")|| navigation.equals("gosearchclaimreconcile")
                    || navigation.equalsIgnoreCase("printoutreport") || navigation.equalsIgnoreCase("printoutreportClaimReconcile")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {

                            vLikeP.add("providerName");
                            vLikeQ.add(searchtext);

                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.providerName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("providerName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.providerName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }

                    if (searchby.equalsIgnoreCase("memberName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.firstName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.firstName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("memberId.firstName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.firstName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {

                            vLikeP.add("memberId.customerPolicyNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.customerPolicyNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("memberId.customerPolicyNumber");
                                    vLikeQ.add(searchtext);
                                } else {

                                    vLikeP.add("claimId.memberId.customerPolicyNumber");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.memberGroupId.groupName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.memberGroupId.groupName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {
                                    vLikeP.add("memberId.memberGroupId.groupName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.memberGroupId.groupName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }

                    if (searchby.equals("clientName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.clientId.clientName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.clientId.clientName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {
                                    vLikeP.add("memberId.clientId.clientName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.clientId.clientName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equals("claimNumber")) {
                      
                        vLikeP.add("claimNumber");
                        vLikeQ.add(searchtext);
                       
                    }
                    if (searchby.equalsIgnoreCase("officer")) {
                        vLikeP.add("createdBy");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("paymentNumber")) {
                    	vLikeP.add("paymentId.paymentNumber");
                        vLikeQ.add(searchtext);
                        
                    }
                    if (searchby.equals("invoice")) {
                        vLikeP.add("batchClaimId.invoiceNumber");
                        vLikeQ.add(searchtext);
                    }
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("claimStatus.caseStatusId");
                        vEqQ.add(Integer.valueOf(searchStatus));
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.claimStatus.caseStatusId");
                        vEqQ.add(Integer.valueOf(searchStatus));
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("claimStatus.caseStatusId");
                                vEqQ.add(Integer.valueOf(searchStatus));
                            } else {
                                vEqP.add("claimId.claimStatus.caseStatusId");
                                vEqQ.add(Integer.valueOf(searchStatus));
                            }
                        }

                    }
                }

                if (claimType != null && claimType.intValue() > 0) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("claimTypeId.claimTypeId");
                        vEqQ.add(claimType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.claimTypeId.claimTypeId");
                        vEqQ.add(claimType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("claimTypeId.claimTypeId");
                                vEqQ.add(claimType);
                            } else {
                                vEqP.add("claimId.claimTypeId.claimTypeId");
                                vEqQ.add(claimType);

                            }
                        }

                    }
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("caseCategoryId.caseCategoryId");
                        vEqQ.add(serviceType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.caseCategoryId.caseCategoryId");
                        vEqQ.add(serviceType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("caseCategoryId.caseCategoryId");
                                vEqQ.add(serviceType);
                            } else {
                                vEqP.add("claimId.caseCategoryId.caseCategoryId");
                                vEqQ.add(serviceType);
                            }
                        }

                    }
                }

            }

            
            /**
            if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT) {
                vEqP.add("tipe");
                vEqQ.add(Integer.valueOf(0));
            }*/

            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }
                    
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            // String required[] = new String[] {
            // // foreign affairs
            // "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
            // "Claim.BatchClaimId", "Claim.CaseCategoryId",
            // // foreign affairs end
            // };

            String required[] = {"Claim.ProviderId","Claim.MemberId","Claim.MemberId.ParentMember","Claim.BatchClaimId",
            		"Claim.BatchClaimId.BatchClaimType","Claim.MemberId.MemberGroupId","Claim.CaseCategoryId","Claim.PaymentId"};
            



            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);


            String data = "";

            if (formatDocument != null) {

                // 1 ---> full 
                if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT) {

                    if (minimumDate != null && maxDate != null) {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                dateBy, minimumDate, maxDate, required, null);
                    } else {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }

                    data = ReportFormatter.formatCSVComplete(collection, claimItemService, memberProductService);
                    data = data.replace("null", "");
                    
                            
                    System.out.println(data);

                    response.setContentType("application/x-csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimreport.csv");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                    Collection<ClaimItem> claimItems = null;

                    if (minimumDate != null && maxDate != null) {
                        claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                               "claim2Id."+dateBy, minimumDate, maxDate, required, null);
                    } else {
                        claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }
                    
                    System.out.println("TOTAL DETAIL : " + claimItems.size());

                    data = ReportFormatter.formatDetailIPMatDownload(claimItems);
                    data = data.replace("null", "");
                    System.out.println(data);

                    response.setContentType("text/csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimdetail.txt");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {

                    if (serviceType == null
                            || serviceType.intValue() == CaseCategory.INPATIENT
                            || serviceType.intValue() == CaseCategory.MATERNITY) {

                        if (minimumDate != null && maxDate != null) {
                            collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    dateBy, minimumDate, maxDate, required, null);
                        } else {
                            collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    required, null);
                        }

                        data = ReportFormatter.formatHeaderIPMatDownload(collection);
                        data = data.replace("null", "");
                        System.out.println(data);


                    } else {

                        Collection<ClaimItem> claimItems = null;
                        
                        if (minimumDate != null && maxDate != null) {
                            claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    "claim2Id."+dateBy, minimumDate, maxDate, required, null);
                        } else {
                            claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    required, null);
                        }

                        System.out.println("TOTAL DETAIL HEADER RJ : " + claimItems.size());
                        data = ReportFormatter.formatHeaderRJDownload(claimItems);
                        data = data.replace("null", "");
                        System.out.println(data);

                    }


                    response.setContentType("text/csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimheader.txt");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                    System.out.println("=fahri- PRINT EXCEL");

                    required = new String[]{
                            "Claim.ClaimTypeId", "Claim.CaseCategoryId", "Claim.ClaimStatus",
                            "Claim.DiagnosisId", "Claim.MemberId", "Claim.MemberId.ParentMember",
                            "Claim.BatchClaimId", "Claim.PaymentId"
                    };

                    if (minimumDate != null && maxDate != null) {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                dateBy, minimumDate, maxDate, required, null);
                    } else {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }

                    Collection<Claim> claims = collection;
                    Integer[] holiday =  new Integer[claims.size()];
                   
                    for(Claim claimSLA : claims){
                    		
                    	Integer row = 0;
                    	
                    	System.out.println("claim id : " + claimSLA.getClaimId());
                    	Date paymentTime = null ;
                    	
                    	
                    	if(claimSLA.getPaymentId() != null){
                    		paymentTime = claimSLA.getPaymentId().getPaymentTime();
                    		
                    		holiday[row] = holidayService.getHolidaySLA(claimSLA.getClaimDate(), paymentTime);
                    	}else{
                    		holiday[row] = 0 ;
                    	}
                    	
                    	row++;
                    }
                    
                    System.out.println("collection.size : " + collection.size());
                    HSSFWorkbook workbook = ClaimReconcileDownloader.downloadReport(collection, holiday);

                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setDateHeader("Expires", 0);

                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimreport.xls");

                    ServletOutputStream sos = response.getOutputStream();

                    workbook.write(sos);
                    // sos.write(workbook.getBytes());
                    sos.close();
                }
            }

            System.out.println("DATA : " + data);





            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }
    
    private ModelAndView generatePrintoutReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");
            String dateBy = WebUtil.getParameterString(request, "dateBy", "claimDate");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");

            Integer formatDocument = WebUtil.getParameterInteger(request, "reportFormat");


            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

            int minIndex = 0;
            int maxIndex = 0;
            int totalIndex = 0;

            Collection collection = null;

            int rowsetint = 0;
            int count = 0;

            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            int idx = 1;

            if (StringUtils.isNumeric(rowset)) {
                rowsetint = Integer.parseInt(rowset);
            }
            Vector vLikeP = new Vector();
            Vector vLikeQ = new Vector();
            Vector vEqP = new Vector();
            Vector vEqQ = new Vector();

            if (navigation.equals("gosearch") || navigation.equals("golookup")
                    || navigation.equalsIgnoreCase("printoutreport")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {

                            vLikeP.add("providerName");
                            vLikeQ.add(searchtext);

                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.providerName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("providerName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.providerName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
					
					if (searchby.equalsIgnoreCase("invoiceNumber")) { //add adq 26082015
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {                          
							vLikeP.add("batchClaimId.invoiceNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {                      
							vLikeP.add("batchClaimId.invoiceNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("invoiceNumber");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("batchClaimId.invoiceNumber");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }//end add adq 26082015

                    if (searchby.equalsIgnoreCase("memberName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.firstName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.firstName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("memberId.firstName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.firstName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {

                            vLikeP.add("memberId.customerPolicyNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.customerPolicyNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("memberId.customerPolicyNumber");
                                    vLikeQ.add(searchtext);
                                } else {

                                    vLikeP.add("claimId.memberId.customerPolicyNumber");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.memberGroupId.groupName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.memberGroupId.groupName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {
                                    vLikeP.add("memberId.memberGroupId.groupName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.memberGroupId.groupName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }

                    if (searchby.equals("clientName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.clientId.clientName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.clientId.clientName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {
                                    vLikeP.add("memberId.clientId.clientName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.clientId.clientName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equals("claimNumber")) {
                      
                        vLikeP.add("claimNumber");
                        vLikeQ.add(searchtext);
                       
                    }
                    if (searchby.equalsIgnoreCase("officer")) {
                        vLikeP.add("createdBy");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("paymentNumber")) {
                    	vLikeP.add("paymentId.paymentNumber");
                        vLikeQ.add(searchtext);
                        
                    }
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("claimStatus.caseStatusId");
                        vEqQ.add(Integer.valueOf(searchStatus));
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.claimStatus.caseStatusId");
                        vEqQ.add(Integer.valueOf(searchStatus));
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("claimStatus.caseStatusId");
                                vEqQ.add(Integer.valueOf(searchStatus));
                            } else {
                                vEqP.add("claimId.claimStatus.caseStatusId");
                                vEqQ.add(Integer.valueOf(searchStatus));
                            }
                        }

                    }
                }

                if (claimType != null && claimType.intValue() > 0) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("claimTypeId.claimTypeId");
                        vEqQ.add(claimType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.claimTypeId.claimTypeId");
                        vEqQ.add(claimType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("claimTypeId.claimTypeId");
                                vEqQ.add(claimType);
                            } else {
                                vEqP.add("claimId.claimTypeId.claimTypeId");
                                vEqQ.add(claimType);

                            }
                        }

                    }
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("caseCategoryId.caseCategoryId");
                        vEqQ.add(serviceType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.caseCategoryId.caseCategoryId");
                        vEqQ.add(serviceType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("caseCategoryId.caseCategoryId");
                                vEqQ.add(serviceType);
                            } else {
                                vEqP.add("claimId.caseCategoryId.caseCategoryId");
                                vEqQ.add(serviceType);
                            }
                        }

                    }
                }

            }

            
            /**
            if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT) {
                vEqP.add("tipe");
                vEqQ.add(Integer.valueOf(0));
            }*/

            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }
                    
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            // String required[] = new String[] {
            // // foreign affairs
            // "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
            // "Claim.BatchClaimId", "Claim.CaseCategoryId",
            // // foreign affairs end
            // };

            String required[] = {"Claim.ProviderId","Claim.MemberId","Claim.MemberId.ParentMember","Claim.BatchClaimId",
            		"Claim.BatchClaimId.BatchClaimType","Claim.MemberId.MemberGroupId","Claim.CaseCategoryId","Claim.PaymentId","Claim.ClaimStatus","Claim.DiagnosisId"};
            



            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);


            String data = "";

            if (formatDocument != null) {

                // 1 ---> full 
                if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT) {

                    if (minimumDate != null && maxDate != null) {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                dateBy, minimumDate, maxDate, required, null);
                    } else {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }

                    data = ReportFormatter.formatCSVComplete(collection, claimItemService, memberProductService);
                    data = data.replace("null", "");
                    
                            
                    System.out.println(data);

                    response.setContentType("application/x-csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimreport.csv");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                    Collection<ClaimItem> claimItems = null;

                    if (minimumDate != null && maxDate != null) {
                        claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                               "claim2Id."+dateBy, minimumDate, maxDate, required, null);
                    } else {
                        claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }
                    
                    System.out.println("TOTAL DETAIL : " + claimItems.size());

                    data = ReportFormatter.formatDetailIPMatDownload(claimItems);
                    data = data.replace("null", "");
                    System.out.println(data);

                    response.setContentType("text/csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimdetail.txt");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {

                    if (serviceType == null
                            || serviceType.intValue() == CaseCategory.INPATIENT
                            || serviceType.intValue() == CaseCategory.MATERNITY) {

                        if (minimumDate != null && maxDate != null) {
                            collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    dateBy, minimumDate, maxDate, required, null);
                        } else {
                            collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    required, null);
                        }

                        data = ReportFormatter.formatHeaderIPMatDownload(collection);
                        data = data.replace("null", "");
                        System.out.println(data);


                    } else {

                        Collection<ClaimItem> claimItems = null;
                        
                        if (minimumDate != null && maxDate != null) {
                            claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    "claim2Id."+dateBy, minimumDate, maxDate, required, null);
                        } else {
                            claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    required, null);
                        }

                        System.out.println("TOTAL DETAIL HEADER RJ : " + claimItems.size());
                        data = ReportFormatter.formatHeaderRJDownload(claimItems);
                        data = data.replace("null", "");
                        System.out.println(data);

                    }


                    response.setContentType("text/csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimheader.txt");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                    System.out.println("=fahri- PRINT EXCEL");

                    required = new String[]{
                            "Claim.ClaimTypeId", "Claim.CaseCategoryId", "Claim.ClaimStatus",
                            "Claim.DiagnosisId", "Claim.MemberId", "Claim.MemberId.ParentMember",
                            "Claim.BatchClaimId", "Claim.PaymentId"
                    };

                    if (minimumDate != null && maxDate != null) {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                dateBy, minimumDate, maxDate, required, null);
						 System.out.println("Tanggal di isi");
                    } else {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }

                    Collection<Claim> claims = collection;
                    Integer[] holiday =  new Integer[claims.size()];
                   
                    for(Claim claimSLA : claims){
                    		
                    	Integer row = 0;
                    	
                    	System.out.println("claim id : " + claimSLA.getClaimId());
                    	Date paymentTime = null ;
                    	
                    	
                    	if(claimSLA.getPaymentId() != null){
                    		paymentTime = claimSLA.getPaymentId().getPaymentTime();
                    		
                    		holiday[row] = holidayService.getHolidaySLA(claimSLA.getClaimDate(), paymentTime);
                    	}else{
                    		holiday[row] = 0 ;
                    	}
                    	
                    	row++;
                    }
                    
                    System.out.println("collection.size : " + collection.size());
                    HSSFWorkbook workbook = ClaimDownloader.downloadReport(collection, holiday);

                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setDateHeader("Expires", 0);

                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimreport.xls");

                    ServletOutputStream sos = response.getOutputStream();

                    workbook.write(sos);
                    // sos.write(workbook.getBytes());
                    sos.close();
                }
            }

            System.out.println("DATA : " + data);





            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }
    
    private ModelAndView generatePrintoutActiveClaimReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");
            String dateBy = WebUtil.getParameterString(request, "dateBy", "claimDate");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");

            Integer formatDocument = WebUtil.getParameterInteger(request, "reportFormat");


            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

            int minIndex = 0;
            int maxIndex = 0;
            int totalIndex = 0;

            Collection collection = null;

            int rowsetint = 0;
            int count = 0;

            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            int idx = 1;

            if (StringUtils.isNumeric(rowset)) {
                rowsetint = Integer.parseInt(rowset);
            }
            Vector vLikeP = new Vector();
            Vector vLikeQ = new Vector();
            Vector vEqP = new Vector();
            Vector vEqQ = new Vector();

            if (navigation.equals("gosearch") || navigation.equals("golookup")
                    || navigation.equalsIgnoreCase("printoutreport")) {

                if (searchby != null) {
                    /**
                     * ini querynya disesuaikan dengan apa yang mau di search
                     * default nya gue bikin template search by semua field yang
                     * tipenya 'String' -adhit
                     */
                    if (searchby.equalsIgnoreCase("providerName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {

                            vLikeP.add("providerName");
                            vLikeQ.add(searchtext);

                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.providerName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("providerName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.providerName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }

                    if (searchby.equalsIgnoreCase("memberName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.firstName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.firstName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("memberId.firstName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.firstName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equalsIgnoreCase("policyNumber")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {

                            vLikeP.add("memberId.customerPolicyNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.customerPolicyNumber");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {

                                    vLikeP.add("memberId.customerPolicyNumber");
                                    vLikeQ.add(searchtext);
                                } else {

                                    vLikeP.add("claimId.memberId.customerPolicyNumber");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equalsIgnoreCase("memberGroupName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.memberGroupId.groupName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.memberGroupId.groupName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {
                                    vLikeP.add("memberId.memberGroupId.groupName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.memberGroupId.groupName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }

                    if (searchby.equals("clientName")) {
                        if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                                || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                            vLikeP.add("memberId.clientId.clientName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                            vLikeP.add("claimId.memberId.clientId.clientName");
                            vLikeQ.add(searchtext);
                        }
                        if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                            if (serviceType != null) {

                                if (serviceType.intValue() == CaseCategory.INPATIENT
                                        || serviceType.intValue() == CaseCategory.MATERNITY) {
                                    vLikeP.add("memberId.clientId.clientName");
                                    vLikeQ.add(searchtext);
                                } else {
                                    vLikeP.add("claimId.memberId.clientId.clientName");
                                    vLikeQ.add(searchtext);
                                }
                            }

                        }
                    }
                    if (searchby.equals("claimNumber")) {
                      
                        vLikeP.add("claimNumber");
                        vLikeQ.add(searchtext);
                       
                    }
                    if (searchby.equalsIgnoreCase("officer")) {
                        vLikeP.add("createdBy");
                        vLikeQ.add(searchtext);
                    }

                    if (searchby.equalsIgnoreCase("paymentNumber")) {
                    	vLikeP.add("paymentId.paymentNumber");
                        vLikeQ.add(searchtext);
                        
                    }
                }

                if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("claimStatus.caseStatusId");
                        vEqQ.add(Integer.valueOf(searchStatus));
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.claimStatus.caseStatusId");
                        vEqQ.add(Integer.valueOf(searchStatus));
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("claimStatus.caseStatusId");
                                vEqQ.add(Integer.valueOf(searchStatus));
                            } else {
                                vEqP.add("claimId.claimStatus.caseStatusId");
                                vEqQ.add(Integer.valueOf(searchStatus));
                            }
                        }

                    }
                }

                if (claimType != null && claimType.intValue() > 0) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("claimTypeId.claimTypeId");
                        vEqQ.add(claimType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.claimTypeId.claimTypeId");
                        vEqQ.add(claimType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("claimTypeId.claimTypeId");
                                vEqQ.add(claimType);
                            } else {
                                vEqP.add("claimId.claimTypeId.claimTypeId");
                                vEqQ.add(claimType);

                            }
                        }

                    }
                }
                if (serviceType != null && serviceType.intValue() > 0) {
                    if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT
                            || formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                        vEqP.add("caseCategoryId.caseCategoryId");
                        vEqQ.add(serviceType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                        vEqP.add("claimId.caseCategoryId.caseCategoryId");
                        vEqQ.add(serviceType);
                    }
                    if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {
                        if (serviceType != null) {

                            if (serviceType.intValue() == CaseCategory.INPATIENT || serviceType.intValue() == CaseCategory.MATERNITY) {
                                vEqP.add("caseCategoryId.caseCategoryId");
                                vEqQ.add(serviceType);
                            } else {
                                vEqP.add("claimId.caseCategoryId.caseCategoryId");
                                vEqQ.add(serviceType);
                            }
                        }

                    }
                }

            }

            
            /**
            if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT) {
                vEqP.add("tipe");
                vEqQ.add(Integer.valueOf(0));
            }*/

            if (user != null){
            	if (user.getUser().getUserType().intValue() == User.CLIENT){
            		vEqP.add("clientId");
            		vEqQ.add(user.getUser().getClientId().getClientId());
            	}
            	if (user.getUser().getUserType().intValue() == User.MEMBER_GROUP){
            		vEqP.add("memberId.memberGroupId.memberGroupId");
            		vEqQ.add(user.getUser().getMemberGroupId().getMemberGroupId());
            	}
            	if (user.getUser().getUserType().intValue() == User.PROVIDER){
            		vEqP.add("providerId.providerId");
            		vEqQ.add(user.getUser().getProviderId().getProviderId());
            	}
            	
            }
                    
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);

            // String required[] = new String[] {
            // // foreign affairs
            // "Claim.ClaimTypeId", "Claim.ClaimStatus", "Claim.MemberId",
            // "Claim.BatchClaimId", "Claim.CaseCategoryId",
            // // foreign affairs end
            // };

            String required[] = {"Claim.ProviderId","Claim.MemberId","Claim.MemberId.ParentMember","Claim.BatchClaimId",
            		"Claim.BatchClaimId.BatchClaimType","Claim.MemberId.MemberGroupId","Claim.CaseCategoryId","Claim.PaymentId"};
            



            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);


            String data = "";

            if (formatDocument != null) {

                // 1 ---> full 
                if (formatDocument.intValue() == ReportFormatter.FULL_CSV_REPORT) {

                    if (minimumDate != null && maxDate != null) {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                dateBy, minimumDate, maxDate, required, null);
                    } else {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }

                    data = ReportFormatter.formatCSVComplete(collection, claimItemService, memberProductService);
                    data = data.replace("null", "");
                    
                            
                    System.out.println(data);

                    response.setContentType("application/x-csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimreport.csv");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.TEXT_DETAIL_REPORT) {
                    Collection<ClaimItem> claimItems = null;

                    if (minimumDate != null && maxDate != null) {
                        claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                               "claim2Id."+dateBy, minimumDate, maxDate, required, null);
                    } else {
                        claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }
                    
                    System.out.println("TOTAL DETAIL : " + claimItems.size());

                    data = ReportFormatter.formatDetailIPMatDownload(claimItems);
                    data = data.replace("null", "");
                    System.out.println(data);

                    response.setContentType("text/csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimdetail.txt");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.TEXT_HEADER_REPORT) {

                    if (serviceType == null
                            || serviceType.intValue() == CaseCategory.INPATIENT
                            || serviceType.intValue() == CaseCategory.MATERNITY) {

                        if (minimumDate != null && maxDate != null) {
                            collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    dateBy, minimumDate, maxDate, required, null);
                        } else {
                            collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    required, null);
                        }

                        data = ReportFormatter.formatHeaderIPMatDownload(collection);
                        data = data.replace("null", "");
                        System.out.println(data);


                    } else {

                        Collection<ClaimItem> claimItems = null;
                        
                        if (minimumDate != null && maxDate != null) {
                            claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    "claim2Id."+dateBy, minimumDate, maxDate, required, null);
                        } else {
                            claimItems = claimItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                    required, null);
                        }

                        System.out.println("TOTAL DETAIL HEADER RJ : " + claimItems.size());
                        data = ReportFormatter.formatHeaderRJDownload(claimItems);
                        data = data.replace("null", "");
                        System.out.println(data);

                    }


                    response.setContentType("text/csv");
                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimheader.txt");


                    ServletOutputStream sos = response.getOutputStream();

                    response.setHeader("Content-length", Integer.toString(data.length()));

                    sos.write(data.getBytes());
                    sos.close();
                }
                if (formatDocument.intValue() == ReportFormatter.EXCEL_REPORT) {
                    System.out.println("=fahri- PRINT EXCEL");

                    required = new String[]{
                            "Claim.ClaimTypeId", "Claim.CaseCategoryId", "Claim.ClaimStatus",
                            "Claim.DiagnosisId", "Claim.MemberId", "Claim.MemberId.ParentMember",
                            "Claim.BatchClaimId", "Claim.PaymentId"
                    };

                    if (minimumDate != null && maxDate != null) {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                dateBy, minimumDate, maxDate, required, null);
                    } else {
                        collection = claimService.search(sLikeP, sLikeQ, sEqP, sEqQ,
                                required, null);
                    }

                    Collection<Claim> claims = collection;
                    Integer[] holiday =  new Integer[claims.size()];
                   
                    for(Claim claimSLA : claims){
                    		
                    	Integer row = 0;
                    	
                    	System.out.println("claim id : " + claimSLA.getClaimId());
                    	Date paymentTime = null ;
                    	
                    	
                    	if(claimSLA.getPaymentId() != null){
                    		paymentTime = claimSLA.getPaymentId().getPaymentTime();
                    		
                    		holiday[row] = holidayService.getHolidaySLA(claimSLA.getClaimDate(), paymentTime);
                    	}else{
                    		holiday[row] = 0 ;
                    	}
                    	
                    	row++;
                    }
                    
                    System.out.println("collection.size : " + collection.size());
                    HSSFWorkbook workbook = ClaimDownloader.downloadReport(collection, holiday);

                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Pragma", "no-cache");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setDateHeader("Expires", 0);

                    response.setHeader("Content-disposition", "inline; filename="
                            + "claimreport.xls");

                    ServletOutputStream sos = response.getOutputStream();

                    workbook.write(sos);
                    // sos.write(workbook.getBytes());
                    sos.close();
                }
            }

            System.out.println("DATA : " + data);





            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }

    private ModelAndView generateClaimStatReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");

            String searchStatus = WebUtil.getParameterString(request,
                    "status","");

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            
            String dateBy  = WebUtil.getParameterString(request, "dateBy", "claimDate");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            Integer serviceType = WebUtil.getParameterInteger(request,
                    "serviceType");
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

            int minIndex = 0;
            int maxIndex = 0;
            int totalIndex = 0;

            Collection collection = null;

            int rowsetint = 0;
            int count = 0;

            /**
             * belum ada parameter client id 
             */
            long startTime = System.currentTimeMillis();
            // belum ada date by
            
            collection = claimReportService.generateClaimStatisticReport(
                    searchtext, searchby, minimumDate, maxDate, Integer.valueOf(searchStatus),
                    serviceType, claimType);
            long endTime = System.currentTimeMillis();

            System.out.println(" TOTAL TIME FOR : " + collection.size()
                    + " RECORD IS " + (endTime - startTime) / 1000 + " s");

            int idx = 1;
            String data = "No|Policy Holder|Claim Number| CDV Number| Name of Member|Member ID| Relation| Sex| Date of Birth";
            data += "|Plan,Class|Inception Date| Maturity Date| Date of Claim Event|Discharge Date| Date of Claim Accepted";
            data += "|CDV Date| Date of Claim Paid|Facility| Facility Area| Claim Type| Limit Benefit| Remaining Benefit| Length of Stay";
            data += "|Action Type|Diagnose| Payment Type|Charge|Paid|Declined|Excess|Charge| Paid| Declined| Excess| Currency| Declined Reason| Bank| Bank Account| Payee Name";
            data += "\n\n";

            if (collection != null) {
                Iterator<Object[]> iterator = collection.iterator();

                Collection<ClaimItem> claimItems = null;

                if (iterator != null) {
                    Object[] claim = null;

                    while (iterator.hasNext()) {

                        claim = iterator.next();

                        if (claim != null) {

                            claimItems = claimItemService.search(null, null,
                                    "claimId.claimId", Integer.valueOf(claim[0].toString()), 0, 100);

                            if (claim[11] != null) {
                                claimChargeValue += Double.valueOf(claim[11].toString());
                            }

                            if (claim[19] != null) {
                                claimPaidValue += Double.valueOf(claim[19].toString());
                            } else {
                                claimPaidValue += 0;
                            }

                            if (claim[28] != null) {
                                claimExcessValue += Double.valueOf(claim[28].toString());
                            } else {
                                claimExcessValue += 0;
                            }

                            String provider = "";
                            String areaProvider = "";

                            if (claim[23] != null) {
                                provider = claim[23] == null ? "" : claim[23].toString();

                                areaProvider = claim[68] == null ? ""
                                        : claim[68].toString();
                            } else {
                                provider = claim[23] == null ? "" : claim[23].toString();
                            }

                            double limit = 0;
                            double remaining = 0;
                            double claimDeclinedValue = 0;
                            double excessClaim = 0;
                            double paidValue = 0;
                            String annualLimit = "";
                            String kelas = "";

                            String cdvNumber = "";
                            String cdvDate = "";
                            String paymentConfirmedDate = "";
                            String bank = "";
                            String bankAccount = "";
                            String payeeName = "";
                            String claimTypeStr = "";
                            String effectiveDate = "";
                            String expireDate = "";

                            if (claim[45] != null) {
                                cdvNumber = claim[54] == null ? "" : claim[54].toString();

                                cdvDate = claim[55] == null ? "" : claim[55].toString();

                                if (claim[56] != null) {
                                    paymentConfirmedDate = claim[56] == null ? ""
                                            : claim[56].toString();
                                }

                                bank = claim[85] == null ? "" : claim[85].toString();
                                bankAccount = claim[86] == null ? ""
                                        : claim[86].toString();
                                payeeName = claim[89] == null ? "" : claim[89].toString();
                            }

                            if (claim[20] != null) {
                                paidValue = Double.valueOf(claim[20].toString());
                            }

                            if (claim[46] != null && claim[40] != null) {
                                MemberProduct memberProduct = memberProductService.getMemberActiveProduct(Integer.valueOf(claim[46].toString()),
                                        Integer.valueOf(claim[40].toString()));

                                if (memberProduct != null) {
                                    Product product = memberProduct.getProductId();

                                    if (product != null) {
                                        kelas = product.getServiceClass();

                                        if (product.getAnnualLimitValue() != null) {
                                            limit = product.getAnnualLimitValue();
                                        }
                                        if (memberProduct.getAnnualBenefit() != null
                                                && memberProduct.getBenefitUsage() != null) {
                                            remaining = memberProduct.getAnnualBenefit()
                                                    - memberProduct.getBenefitUsage();
                                        }

                                    }

                                }
                            }

                            if (claim[88] != null) {
                                claimTypeStr = claim[88] == null ? ""
                                        : claim[88].toString();
                            }
                            if (claim[90] != null) {
                                effectiveDate = claim[90].toString();
                            }
                            if (claim[91] != null) {
                                expireDate = claim[91].toString();
                            }

                            String declinedReason = "";

                            data += idx + "|" + claim[57] + "|" + claim[12]
                                    + "|" + claim[54] + "|\"" + claim[59];
                            data += "\"|" + claim[60] + "|" + claim[64] + "|";
                            data += claim[65] + "|" + claim[84] + "|"
                                    + claim[87];
                            data += "|" + kelas + "|" + effectiveDate + "|"
                                    + expireDate;
                            data += "|" + claim[25] + "|" + claim[26] + "|"
                                    + claim[13] + "|" + cdvDate + "|"
                                    + paymentConfirmedDate;
                            data += "|\"" + provider + "\"|\"" + areaProvider
                                    + "\"|" + claim[87] + "|" + limit;
                            data += "|" + remaining + "|||" + claim[51] + "|"
                                    + claimTypeStr;
                            data += "|||||" + claim[11] + "|" + paidValue + "|"
                                    + claimDeclinedValue + "|" + excessClaim
                                    + "||\"" + declinedReason + "\"|\""
                                    + claim[86] + "\"|\"" + claim[85] + "\""
                                    + "|\"" + payeeName + "\"";

                            declinedReason = "";
                            // delimiter untuk new line
                            data += "\n";
                            cdvNumber = "";
                            cdvDate = "";
                            paymentConfirmedDate = "";
                            idx += 1;
                            if (claimItems != null) {
                                Iterator<ClaimItem> ciIterator = claimItems.iterator();

                                ClaimItem ci = null;

                                double declined = 0.0;
                                double excess = 0.0;
                                String reason = "";

                                if (ciIterator != null) {

                                    String groupName = "";
                                    int ciIndex = 0;

                                    while (ciIterator.hasNext()) {
                                        ci = ciIterator.next();
                                        double claimItemApprovedValue = 0.0;

                                        if (ci.getClaimItemApprovedValue() != null) {
                                            declined = ci.getClaimItemValue()
                                                    - ci.getClaimItemApprovedValue();
                                        } else {
                                            declined = ci.getClaimItemValue();
                                        }
                                        if (ci.getMedicalRemarks() != null
                                                && !ci.getMedicalRemarks().equalsIgnoreCase("")) {
                                            reason = ci.getMedicalRemarks();
                                        }

                                        if (ci.getBenefitCheckRemarks() != null
                                                && !ci.getBenefitCheckRemarks().equals("")) {
                                            reason = ci.getBenefitCheckRemarks();
                                        }

                                        if (ciIndex == 0) {
                                            groupName = claim[57] == null ? ""
                                                    : claim[57].toString();
                                        } else {
                                            groupName = "";
                                        }

                                        if (ci.getClaimItemApprovedValue() != null) {
                                            claimItemApprovedValue = ci.getClaimItemApprovedValue().doubleValue();
                                        }

                                        ciIndex += 1;
                                        data += idx + "|" + claim[57] + "|" + claim[12]
                                                                                    + "|" + claim[54] + "|\"" + claim[59];
                                                                            data += "\"|" + claim[60] + "|" + claim[64] + "|";
                                                                            data += claim[65] + "|" + claim[84] + "|"
                                                                                    + claim[87];
                                                                            data += "|" + kelas + "|" + effectiveDate + "|"
                                                                                    + expireDate;
                                                                            data += "|" + claim[25] + "|" + claim[26] + "|"
                                                                                    + claim[13] + "|" + cdvDate + "|"
                                                                                    + paymentConfirmedDate;
                                                                            data += "|\"" + provider + "\"|\"" + areaProvider
                                                                                    + "\"|" + claim[87] + "|" + limit;
                                                                            data += "|" + remaining + "|||" + claim[51] + "|"
                                                                                    + claimTypeStr;
                                                                            
                                        data += "|";
                                        data += claim[57] +"|"+claim[12]+"|"+claim[54]+"|"+claim[59]+"|"+claim[60]+"|"+claim[64]+"|";
                                        data += claim[65] + "|"+ claim[84] +"|"+claim[87]+"|"+kelas+"|"+effectiveDate+"|"+expireDate+"|"+claim[25];
                                        data += ","+claim[26]+"|" + claim[13]+"|"+cdvDate+"|"+paymentConfirmedDate+"|\""+provider+"\"|\""+areaProvider+"\"|";
                                        data += claim[87] +"|"+limit+"|"+remaining+"||";
                                        data += ci.getItemId().getItemName()
                                                + "|||"
                                                + ci.getClaimItemValue();
                                        data += "|" + claimItemApprovedValue
                                                + "|" + declined + "|" + excess;
                                        data += "|||||\"" + reason + "\"||\n";

                                        reason = "";
                                    }
                                    data += "\n";
                                }
                            }
                        }
                    }

                }

            }

            System.out.println("DATA : " + data);

            response.setContentType("application/x-csv");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            response.setHeader("Content-disposition", "inline; filename="
                    + "claimreport.csv");

            ServletOutputStream sos = response.getOutputStream();

            response.setHeader("Content-length", Integer.toString(data.length()));

            sos.write(data.getBytes());
            sos.close();

            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }
    private ModelAndView generateClaimTransactionReport(HttpServletRequest request,
            HttpServletResponse response, String location) {
        ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");

            String searchStatus = null;
            
            String[] searchStatusPassJSP = WebUtil.getParameterStringArray(request,"status");
            
			Integer[] searchStatusMulti = null;
			
			
			if(searchStatusPassJSP.length > 1){
				
				searchStatusMulti = new Integer[searchStatusPassJSP.length];
				for(int i = 0;i < searchStatusPassJSP.length;i++)
				{
					searchStatusMulti[i] = Integer.parseInt(searchStatusPassJSP[i]);
				}
			}
			else if (searchStatusPassJSP.length == 1){
				searchStatus = searchStatusPassJSP[0];
			}
			

            Integer serviceType = null;
            
            String[] searchCategories = WebUtil.getParameterStringArray(request,"serviceType");
            
			Integer[] searchCategoryMulti = null;
			
			if(searchCategories.length > 1){
				
				searchStatusMulti = new Integer[searchStatusPassJSP.length];
				for(int i = 0;i < searchStatusPassJSP.length;i++)
				{
					searchStatusMulti[i] = Integer.parseInt(searchStatusPassJSP[i]);
				}
			}
			else if (searchCategories.length == 1){
				serviceType = Integer.valueOf(searchCategories[0]);
			}

            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            
            String dateBy  = WebUtil.getParameterString(request, "dateBy", "claimDate");

            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
            Date maxDate = WebUtil.getParameterDate(request, "maximum_date");

            int minIndex = 0;
            int maxIndex = 0;
            int totalIndex = 0;

            Collection collection = null;

            int rowsetint = 0;
            int count = 0;

            Vector vLikeP = new Vector();
            Vector vLikeQ = new Vector();
            Vector vEqP = new Vector();
            Vector vEqQ = new Vector();


            if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                vEqP.add("claimStatus.caseStatusId");
                vEqQ.add(Integer.valueOf(searchStatus));
            }

            if (claimType != null && claimType.intValue() > 0) {
                vEqP.add("claimTypeId.claimTypeId");
                vEqQ.add(claimType);
            }
            if (serviceType != null && serviceType.intValue() > 0) {
                vEqP.add("caseCategoryId.caseCategoryId");
                vEqQ.add(serviceType);
            }



            if (clientId != null){
            	vEqP.add("memberId.clientId.clientId");
            	vEqQ.add(clientId);
            }
            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);
            
            Object[] notInStatus = new Object[2];
            notInStatus[0] = Claim.CLAIM_VOID;
            notInStatus[1] = Claim.CLAIM_REJECT;
            

            String required[] = {"Claim.PaymentId","Claim.MemberId","Claim.ClaimTypeId","Claim.CaseCategoryId","Claim.ClaimStatus"
            		,"Claim.DiagnosisId","Claim.ProviderId","Claim.BatchClaimId"};
            
            int total = 0;

            if (minimumDate != null && maxDate != null && !minimumDate.equals("1970-01-01") && !maxDate.equals("1970-01-01")) {
            	
            	total = claimService.getTotalMultiStatus(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, searchCategoryMulti, notInStatus, null, dateBy, minimumDate, maxDate);
                collection = claimService.searchMultiStatus(sLikeP, sLikeQ, sEqP, sEqQ,searchStatusMulti,searchCategoryMulti,notInStatus,null,
                        true,"claimNumber",dateBy, minimumDate, maxDate, required, 0, total);
                
                
            } else {
        
            	total = claimService.getTotalMultiStatus(sLikeP, sLikeQ, sEqP, sEqQ,searchStatusMulti,searchCategoryMulti,notInStatus,null);
                collection = claimService.searchMultiStatus(sLikeP, sLikeQ, sEqP, sEqQ,searchStatusMulti,searchCategoryMulti,notInStatus,null,
                        true,"claimNumber",required, 0,total);
            }

            
            /**
             * belum ada parameter client id 
             */
            long startTime = System.currentTimeMillis();
            // belum ada date by
            
            
            long endTime = System.currentTimeMillis();
            
            if (navigation.equalsIgnoreCase("downloadtransaction")){

	            System.out.println(" TOTAL TIME FOR : " + collection.size()
	                    + " RECORD IS " + (endTime - startTime) / 1000 + " s");
	
	            String data = ReportFormatter.formatCSVTransferComplete(collection, claimItemService, memberProductService);
	            
	           // System.out.println("DATA : " + data);
	
	            response.setContentType("application/x-csv");
	            response.setHeader("Pragma", "no-cache");
	            response.setHeader("Cache-Control", "no-cache");
	            response.setDateHeader("Expires", 0);
	
	            response.setHeader("Content-disposition", "inline; filename="
	                    + "claimreport.csv");
	
	            ServletOutputStream sos = response.getOutputStream();
	
	            response.setHeader("Content-length", Integer.toString(data.length()));
	
	            sos.write(data.getBytes());
	            sos.close();
            }

            request.setAttribute("clientId", clientId);
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            request.setAttribute("dateBy", dateBy);
            request.setAttribute("minDate", minimumDate);
            request.setAttribute("maxDate", maxDate);
            request.setAttribute("statusMulti", searchStatusPassJSP);
            
            if (minimumDate != null && minimumDate.toString().equals("1970-01-01")){

                request.setAttribute("minDate", "");
                
            }
            if (maxDate != null && maxDate.toString().equals("1970-01-01")){
            	request.setAttribute("maxDate", "");
            }
            /*
             * ini gue generate juga dari nama tablenya convention -->
             * collection = nama bean + 's' -adhit
             */

            result.addObject("Claims", collection);

            request.setAttribute("alert", request.getParameter("alert"));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;

    }
	
	 public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
		    Calendar startCal = Calendar.getInstance();
		    startCal.setTime(startDate);        

		    Calendar endCal = Calendar.getInstance();
		    endCal.setTime(endDate);

		    int workDays = 1;
		    
		    

		    //Return 0 if start and end are the same
		    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
		        return 1;
		    }

		    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
		        startCal.setTime(endDate);
		        endCal.setTime(startDate);
		    }

		    do {
		       //excluding start date
		        startCal.add(Calendar.DAY_OF_MONTH, 1);
		        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
		        	) {
		        
		            workDays++;
		        }
		    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis() ); //excluding end date

		    return workDays;
		}

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        String navigation = request.getParameter("navigation") == null ? "welcome"
                : request.getParameter("navigation");

        String subnavigation = WebUtil.getParameterString(request,
                "subnavigation", "");

        Object user = null;

        ModelAndView result = null;
        HttpSession session = request.getSession(false);

        /*
         * if (session == null) {
         *
         * request.setAttribute("alert", "<b>" +
         * alertProperties.getValue("not.login") + "</b>");
         * request.setAttribute("content", "/jsp/login.jsp");
         * forward("/main.jsp", request, response);
         *
         *
         * result = new ModelAndView ("login"); } else { }
         */
        String breadcrumb = "";
        try {
            if (navigation.equalsIgnoreCase("report")) {
                /*
                 * disesuaikan dengan halaman targetnya nih
                 */
                result = generateReport(request, response, "reportClaim");
                breadcrumb = "<a href=\"claimreport\">Search Report Claim Statistic</a>";
            } else if (navigation.equalsIgnoreCase("printoutreport")) {
                // result = generateClaimStatReport(request, response,
                // "trackClaim");
                result = generatePrintoutReport(request, response, "printClaimReport");
//                result = generateXLSTrackStatisticReport(request, response, "printClaimReport");
                
            } else if (navigation.equalsIgnoreCase("printpopup")) {
                result = generateReport(request, response, "popupPrintOut");
            } else if (navigation.equalsIgnoreCase("claimstatreport")
                    || navigation.equalsIgnoreCase("popupclaimstat")) {
                result = generateClaimStatisticReport(request, response,
                        "claimStatisticReport");
            } else if (navigation.equalsIgnoreCase("track")
                    || navigation.equalsIgnoreCase("gotrack")
                    || navigation.equalsIgnoreCase("popuptrack")) {
                result = generateReport(request, response, "trackClaim");
                breadcrumb = "<a href=\"claimreport?navigation=track\">Search Report Claim Tracking</a>";
            } else if (navigation.equalsIgnoreCase("printouttrack")) {

                result = generatePrintoutTrackReport(request, response,
                        "trackClaim");
            } else if (navigation.equalsIgnoreCase("tracksla")
                    || navigation.equalsIgnoreCase("gotracksla")
                    || navigation.equalsIgnoreCase("popuptracksla")
                    || navigation.equalsIgnoreCase("printoutsla")) {
                result = trackSLAReport(request, response, "trackClaimSLA");
                breadcrumb = "<a href=\"claimreport?navigation=tracksla\">Search Report Claim SLA Tracking</a>";
            } else if (navigation.equalsIgnoreCase("trackstatistic")
                    || navigation.equalsIgnoreCase("gotrackstatistic")) {
           
            	result = generateXLSTrackStatisticReport(request, response, "claimStatisticReport");
            }
//            else if (navigation.equalsIgnoreCase("printoutsla")) {
//                result = generatePrintoutSLAReport(request, response,
//                        "trackClaimSLA");
//            }
            else if (navigation.equalsIgnoreCase("memberClaim")
                    || navigation.equalsIgnoreCase("downloadMemberClaim")) {
                result = generateMemberClaimReport(request, response,
                        "memberClaim");
            } 
            else if (navigation.equalsIgnoreCase("jsonclientoutstanding")){
            	result = jsonOutstandingClaimReport(request, response, "jsonTotalPendingClaim");
            }
            else if (navigation.equalsIgnoreCase("top10report")
                    || navigation.equalsIgnoreCase("gosearchtop10")) {
                result = trackSLAReport(request, response, "top10ReportPopup");
            }
            else if (navigation.equalsIgnoreCase("topxreport")
                    || navigation.equalsIgnoreCase("exporttopx")) {
                result = topXClaimReport(request, response, "topXReportClaim");
            }
            else if (navigation.equalsIgnoreCase("cynergy") || navigation.equalsIgnoreCase("docynergy") ||
            		navigation.equalsIgnoreCase("exportcynergy") ) {
                result = cynergyClaimReport(request, response, "reportClaimCynergy");
            }
            else if (navigation.equalsIgnoreCase("searchtransaction" )|| navigation.equalsIgnoreCase("gosearchtransaction") 
                    || navigation.equalsIgnoreCase("downloadtransaction")) {
                result = generateClaimTransactionReport(request, response, "dailyTransactionReport");
            }else if (navigation.equalsIgnoreCase("claimreconcile")
					|| navigation.equals("gosearchclaimreconcile")) {
				result = generateReport(request, response, "reportClaimReconcile");
				breadcrumb = "<a href=\"claim?navigation=searchsla\">Search Claim Reconcile</a>";
			} 
            else if (navigation.equalsIgnoreCase("printoutreportClaimReconcile")) {
                
                result = generatePrintoutReportReconcile(request, response, "printClaimReconcileReport");
                
            } 
            else if(navigation.equalsIgnoreCase("activeclaimreport") || navigation.equalsIgnoreCase("gosearchclaimactive") || 
            		navigation.equalsIgnoreCase("downloadclaimactivereport")){
            	result = generateReportActiveClaim(request, response, "reportClaimActive");
            }
            else if(navigation.equalsIgnoreCase("claimagingreport") || navigation.equalsIgnoreCase("gosearchclaimaging") || 
            		navigation.equalsIgnoreCase("downloadclaimagingreport")){
            	result = generateReportAgingClaim(request, response, "reportClaimAging");
            }
            else {
                result = generateReport(request, response, "reportClaim");
                breadcrumb = "<a href=\"claimreport\">Search Report Claim Statistic</a>";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.addObject("breadcrumb", breadcrumb);
        Map map = TableRenderingServlet.seti18N(request, response);
        result.addAllObjects(map);
        return result;
    }

    private ModelAndView generateReportActiveClaim(HttpServletRequest request,
            HttpServletResponse response, String location) {
    	ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");
            
            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            
            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            String arah = WebUtil.getParameterString(request, "arah", "");


            String searchStatus = null;
            
            String[] searchStatusMultiple = WebUtil.getParameterStringArray(request,"status");
            
			Object[] searchStatusMulti = null;
			
			
			if(searchStatusMultiple.length >= 1){
				
				searchStatusMulti = new Integer[searchStatusMultiple.length];
				for(int i = 0;i < searchStatusMultiple.length;i++)
				{
					searchStatusMulti[i] = Integer.parseInt(searchStatusMultiple[i]);
				}
			}
			else{
				searchStatusMulti = new Object[6];
				searchStatusMulti[0] = Integer.valueOf(1);
				searchStatusMulti[1] = Integer.valueOf(6);
				searchStatusMulti[2] = Integer.valueOf(8);
				searchStatusMulti[3] = Integer.valueOf(13);
				searchStatusMulti[4] = Integer.valueOf(3);
				searchStatusMulti[5] = Integer.valueOf(5);
			}
			

            //Integer serviceType = null;
            
            //String[] searchCategories = WebUtil.getParameterStringArray(request,"serviceType");
            
			//Integer[] searchCategoryMulti = null;
			
			/*if(searchCategories.length > 1){
				
				searchStatusMulti = new Integer[searchStatusPassJSP.length];
				for(int i = 0;i < searchStatusPassJSP.length;i++)
				{
					searchStatusMulti[i] = Integer.parseInt(searchStatusPassJSP[i]);
				}
			}
			else if (searchCategories.length == 1){
				serviceType = Integer.valueOf(searchCategories[0]);
			}

			*/
			
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");

            int minIndex = 0;
            int maxIndex = 0;
            int totalIndex = 0;

            Collection collection = null;

            int rowsetint = 0;
            int count = 0;

            Vector vLikeP = new Vector();
            Vector vLikeQ = new Vector();
            Vector vEqP = new Vector();
            Vector vEqQ = new Vector();


            if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                vEqP.add("claimStatus.caseStatusId");
                vEqQ.add(Integer.valueOf(searchStatus));
            }

            if (claimType != null && claimType.intValue() > 0) {
                vEqP.add("claimTypeId.claimTypeId");
                vEqQ.add(claimType);
            }
            //if (serviceType != null && serviceType.intValue() > 0) {
            //    vEqP.add("caseCategoryId.caseCategoryId");
            //    vEqQ.add(serviceType);
            //}

            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);
            
            
            Object fault[] = {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)};
            String[] searchByDate = { "claimId.claimDate" };
            Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };

			String[] columnOrder = {"claimId", "actionTime"};
            
            
			if (minimumDate != null && maximumDate != null && !minimumDate.toString().equals("1970-01-01") &&
					!maximumDate.toString().equals("1970-01-01")) {
            	count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, searchByDate, minimumDateArray, maximumDateArray);
            } else {
            	count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti);
            }

            System.out.println("SEARCH MULTI STAT : "+searchStatusMulti.toString());
            System.out.println("COUnt :" +count);
            System.out.println("COUNT SET : "+countSet);
            if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
							"ClaimHistory.ClaimId", "ClaimHistory.ClaimId.MemberId", "ClaimHistory.ClaimId.CaseId"
					// foreign affairs end
					};
			
			if (minimumDate != null && maximumDate != null && !minimumDate.toString().equals("1970-01-01") &&
					!maximumDate.toString().equals("1970-01-01")) {
				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
					searchByDate, minimumDateArray, maximumDateArray, required, rowsetint, countSet.intValue());
			} else {
				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
					required, rowsetint, countSet.intValue());
			}
			
			System.out.println("COLLECTION SIZE : "+collection.size());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
			}


            if (navigation.equalsIgnoreCase("downloadclaimactivereport")){

            	if (minimumDate != null && maximumDate != null && !minimumDate.toString().equals("1970-01-01") &&
    					!maximumDate.toString().equals("1970-01-01")) {
    				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
    					searchByDate, minimumDateArray, maximumDateArray, required, 0, count);
    			} else {
    				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
    					required, 0, count);
    			}
            	
            	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String nowDate = df.format(new java.util.Date());
            	
            	HSSFWorkbook workbook = ClaimDownloader.downloadClaimActiveReport(collection);
            	
            	response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "claimactivereport"+nowDate+".xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				sos.close();
            }

            request.setAttribute("clientId", clientId);
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            request.setAttribute("statusMulti", searchStatusMultiple);
            

            result.addObject("ClaimsActive", collection);

            request.setAttribute("alert", request.getParameter("alert"));
            
            if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("maximumDate", "");
			} else {
				request.setAttribute("maximumDate", maximumDate);
			}
			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("minimumDate", "");
			} else {
				request.setAttribute("minimumDate", minimumDate);
			}
			
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;
    }
    
    private ModelAndView generateReportAgingClaim(HttpServletRequest request,
            HttpServletResponse response, String location) {
    	ModelAndView result = null;
        try {

            ActionUser user = securityService.getActionUser(request);

            boolean isUserAuthorized = securityService.isUserAuthorized(user,
                    "SEARCHCLAIM");

            if (!isUserAuthorized) {

                ModelAndView errorResult = new ModelAndView(new RedirectView(
                        "errorpage"));
                errorResult.addObject("errorType", "accessDenied");
                errorResult.addObject("errorMessage",
                        "You Are Not Authorized for SEARCHCLAIM access");
                return errorResult;

            }

            result = new ModelAndView(location);

            String rowset = WebUtil.getParameterString(request, "rowset", "0");

            Integer index = WebUtil.getParameterInteger(request, "index");

            String navigation = WebUtil.getParameterString(request,
                    "navigation", "");
            String searchtext = WebUtil.getParameterString(request,
                    "searchtext", "");
            String searchby = WebUtil.getParameterString(request, "searchby",
                    "");
            String sortby = WebUtil.getParameterString(request, "sortby", "");
            
            Integer batchClaimId = WebUtil.getParameterInteger(request,
                    "batchClaimId");

            Integer memberId = WebUtil.getParameterInteger(request, "memberId");

            Integer providerId = WebUtil.getParameterInteger(request,
                    "providerId");

            Integer clientId = WebUtil.getParameterInteger(request, "clientId");
            
            String subnavigation = WebUtil.getParameterString(request,
                    "subnavigation", "");
            result.addObject("subnavigation", subnavigation);
            
            Integer claimType = WebUtil.getParameterInteger(request,
                    "claimType");
            String arah = WebUtil.getParameterString(request, "arah", "");

            Integer agingInterval = WebUtil.getParameterInteger(request, "agingInterval");
            
            String searchStatus = null;
            
            String[] searchStatusMultiple = WebUtil.getParameterStringArray(request,"status");
            
			Object[] searchStatusMulti = null;
			
			
			if(searchStatusMultiple.length >= 1){
				
				searchStatusMulti = new Integer[searchStatusMultiple.length];
				for(int i = 0;i < searchStatusMultiple.length;i++)
				{
					searchStatusMulti[i] = Integer.parseInt(searchStatusMultiple[i]);
				}
			}
			else{
				searchStatusMulti = new Object[3];
				searchStatusMulti[0] = Integer.valueOf(1);
				searchStatusMulti[1] = Integer.valueOf(8);
				searchStatusMulti[2] = Integer.valueOf(13);
			}
			

            //Integer serviceType = null;
            
            //String[] searchCategories = WebUtil.getParameterStringArray(request,"serviceType");
            
			//Integer[] searchCategoryMulti = null;
			
			/*if(searchCategories.length > 1){
				
				searchStatusMulti = new Integer[searchStatusPassJSP.length];
				for(int i = 0;i < searchStatusPassJSP.length;i++)
				{
					searchStatusMulti[i] = Integer.parseInt(searchStatusPassJSP[i]);
				}
			}
			else if (searchCategories.length == 1){
				serviceType = Integer.valueOf(searchCategories[0]);
			}

			*/
			
            Double claimChargeValue = 0.0;
            Double claimPaidValue = 0.0;
            Double claimExcessValue = 0.0;

            Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");

            int minIndex = 0;
            int maxIndex = 0;
            int totalIndex = 0;

            Collection collection = null;
            Collection collectionReport = null;

            int rowsetint = 0;
            int count = 0;

            Vector vLikeP = new Vector();
            Vector vLikeQ = new Vector();
            Vector vEqP = new Vector();
            Vector vEqQ = new Vector();


            if (searchStatus != null && !searchStatus.equalsIgnoreCase("")) {
                vEqP.add("claimStatus.caseStatusId");
                vEqQ.add(Integer.valueOf(searchStatus));
            }

            if (claimType != null && claimType.intValue() > 0) {
                vEqP.add("claimTypeId.claimTypeId");
                vEqQ.add(claimType);
            }
            //if (serviceType != null && serviceType.intValue() > 0) {
            //    vEqP.add("caseCategoryId.caseCategoryId");
            //    vEqQ.add(serviceType);
            //}

            vEqP.add("deletedStatus");
            vEqQ.add(Integer.valueOf(0));

            String sLikeP[] = new String[vLikeP.size()];
            vLikeP.toArray(sLikeP);
            Object sLikeQ[] = new Object[vLikeP.size()];
            vLikeQ.toArray(sLikeQ);

            String sEqP[] = new String[vEqP.size()];
            vEqP.toArray(sEqP);
            Object sEqQ[] = new Object[vEqP.size()];
            vEqQ.toArray(sEqQ);
            
            
            Object fault[] = {Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)};
            String[] searchByDate = { "claimId.claimDate" };
            Object[] minimumDateArray = { minimumDate };
			Object[] maximumDateArray = { maximumDate };

			String[] columnOrder = {"claimId", "actionTime"};
            
			Object lsthValue = null;
			String lsthColumn = "claimId.claimDate";
			
			if(agingInterval == null){
				agingInterval = 0;
			}
			
			if(agingInterval > 0){
				if(agingInterval == 1){
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, Integer.valueOf(-30));
					java.util.Date lessDate = cal.getTime();
					Object[] lDate = {lessDate};
					Object[] nowDate = {new java.util.Date()};
					count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, searchByDate, lDate, nowDate);
				}else if(agingInterval == 2){
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.add(Calendar.DATE, Integer.valueOf(-30));
					cal2.add(Calendar.DATE, Integer.valueOf(-40));
					java.util.Date minDate = cal2.getTime();
					java.util.Date maxDate = cal1.getTime();
					Object[] min = {minDate};
					Object[] max = {maxDate};
					String[] col = {"claimId.claimDate"};
					count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, searchByDate, min, max);
				}else if(agingInterval == 3){
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.add(Calendar.DATE, Integer.valueOf(-41));
					cal2.add(Calendar.DATE, Integer.valueOf(-60));
					java.util.Date minDate = cal2.getTime();
					java.util.Date maxDate = cal1.getTime();
					Object[] min = {minDate};
					Object[] max = {maxDate};
					String[] col = {"claimDate"};
					count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, searchByDate, min, max);
				}else{
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, Integer.valueOf(-60));
					java.util.Date lessDate = cal.getTime();
					Object lDate = lessDate;
					String searchDate = "claimId.claimDate";
					count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchDate, lDate,  searchStatusMulti);
				}
			}else if(agingInterval == 0){
				count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti);
			}
            
			/*if (minimumDate != null && maximumDate != null && !minimumDate.toString().equals("1970-01-01") &&
					!maximumDate.toString().equals("1970-01-01")) {
            	count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, searchByDate, minimumDateArray, maximumDateArray);
            } else {
            	count = claimHistoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti);
            }
			 */
            System.out.println("SEARCH MULTI STAT : "+searchStatusMulti.toString());
            System.out.println("COUnt :" +count);
            System.out.println("COUNT SET : "+countSet);
            if (index == null)
				index = new Integer(1);

			if (arah.equals("kanan"))
				index = new Integer(index.intValue() + 1);
			else if (arah.equals("kiri"))
				index = new Integer(index.intValue() - 1);
			else if (arah.equals("kiribgt"))
				index = new Integer(1);
			else if (arah.equals("kananbgt"))
				index = new Integer(count / countSet.intValue() + 1);

			if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
				index = new Integer(1);
			else if (index.compareTo(new Integer(count / countSet.intValue()
					+ 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1)
					* countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count
						/ countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
							"ClaimHistory.ClaimId", "ClaimHistory.ClaimId.MemberId", "ClaimHistory.ClaimId.CaseId"
					// foreign affairs end
					};
			
			if(agingInterval != null && agingInterval > 0){
				if(agingInterval == 1){
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, Integer.valueOf(-30));
					java.util.Date lessDate = cal.getTime();
					Object[] lDate = {lessDate};
					Object[] nowDate = {new java.util.Date()};
					collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
							searchByDate, lDate, nowDate, required, rowsetint, countSet.intValue());
					collectionReport = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
							searchByDate, lDate, nowDate, required, 0, count);
				}else if(agingInterval == 2){
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.add(Calendar.DATE, Integer.valueOf(-30));
					cal2.add(Calendar.DATE, Integer.valueOf(-40));
					java.util.Date minDate = cal2.getTime();
					java.util.Date maxDate = cal1.getTime();
					Object[] min = {minDate};
					Object[] max = {maxDate};
					String[] col = {"claimId.claimDate"};
					collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
							searchByDate, min, max, required, rowsetint, countSet.intValue());
					collectionReport = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
							searchByDate, min, max, required, 0, count);
				}else if(agingInterval == 3){
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					cal1.add(Calendar.DATE, Integer.valueOf(-41));
					cal2.add(Calendar.DATE, Integer.valueOf(-60));
					java.util.Date minDate = cal2.getTime();
					java.util.Date maxDate = cal1.getTime();
					Object[] min = {minDate};
					Object[] max = {maxDate};
					String[] col = {"claimDate"};
					collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
							searchByDate, min, max, required, rowsetint, countSet.intValue());
					collectionReport = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
							searchByDate, min, max, required, 0, count);
				}else{
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, Integer.valueOf(-60));
					java.util.Date lessDate = cal.getTime();
					Object lDate = lessDate;
					String searchDate = "claimId.claimDate";
					collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchDate, lDate, searchStatusMulti, true, columnOrder,
							required, rowsetint, countSet.intValue());
					collectionReport = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchDate, lDate, searchStatusMulti, true, columnOrder,
							required, 0, count);
				}
			}else if(agingInterval == 0){
				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
						required, rowsetint, countSet.intValue());
				collectionReport = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
						required, 0, count);
			}
			
			/*if (minimumDate != null && maximumDate != null && !minimumDate.toString().equals("1970-01-01") &&
					!maximumDate.toString().equals("1970-01-01")) {
				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
					searchByDate, minimumDateArray, maximumDateArray, required, rowsetint, countSet.intValue());
			} else {
				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
					required, rowsetint, countSet.intValue());
			}
			*/
			
			System.out.println("COLLECTION SIZE : "+collection.size());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1)
						.intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count
						/ countSet.intValue() + 1)) == new Integer(1)
						.intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1)
						* countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count
							/ countSet.intValue()));
				}
			}


            if (navigation.equalsIgnoreCase("downloadclaimagingreport")){

            	/*if (minimumDate != null && maximumDate != null && !minimumDate.toString().equals("1970-01-01") &&
    					!maximumDate.toString().equals("1970-01-01")) {
    				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
    					searchByDate, minimumDateArray, maximumDateArray, required, 0, count);
    			} else {
    				collection = claimHistoryService.search(sLikeP, sLikeQ, sEqP, sEqQ, searchStatusMulti, true, columnOrder,
    					required, 0, count);
    			}
            	*/
            	
            	
            	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				String nowDate = df.format(new java.util.Date());
				System.out.println("Report Size : "+collectionReport.size());
            	
            	HSSFWorkbook workbook = ClaimDownloader.downloadClaimAgingReport(collectionReport);
            	
            	response.setContentType("application/vnd.ms-excel");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setHeader("Content-disposition", "inline; filename="
						+ "claimagingreport"+nowDate+".xls");

				ServletOutputStream sos = response.getOutputStream();

				workbook.write(sos);
				sos.close();
            }

            request.setAttribute("clientId", clientId);
            request.setAttribute("searchtext", searchtext);
            request.setAttribute("searchby", searchby);
            request.setAttribute("navigation", navigation);
            request.setAttribute("claimType", claimType);
            request.setAttribute("status", searchStatus);
            request.setAttribute("claimChargeValue", claimChargeValue);
            request.setAttribute("claimExcessValue", claimExcessValue);
            request.setAttribute("claimPaidValue", claimPaidValue);
            request.setAttribute("statusMulti", searchStatusMultiple);
            request.setAttribute("agingInterval", agingInterval);
            

            result.addObject("ClaimsActive", collection);

            request.setAttribute("alert", request.getParameter("alert"));
            
            if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("maximumDate", "");
			} else {
				request.setAttribute("maximumDate", maximumDate);
			}
			if (maximumDate != null
					&& maximumDate.toString().equals("1970-01-01")) {
				request.setAttribute("minimumDate", "");
			} else {
				request.setAttribute("minimumDate", minimumDate);
			}
			
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

        } catch (Exception e) {
            e.printStackTrace();

            // request.setAttribute("alert", alertProperties.getMessage(
            // "system.error " + e.getMessage(), null, "fail", Locale
            // .getDefault()));

            result = new ModelAndView("error");
        }

        return result;
    }
}
