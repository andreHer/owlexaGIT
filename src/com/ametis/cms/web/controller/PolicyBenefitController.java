
package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.PolicyBenefit;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.PolicyBenefitService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.MemberTrackingDownloader;
import com.ametis.cms.util.PolicyBenefitDownloader;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 


/**
 * PolicyBenefit is a servlet controller for policy_benefit Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class PolicyBenefitController implements Controller

// extends+ 

// extends- 

{
	
	private PolicyBenefitService  policyBenefitService;
	private PolicyService  policyService;
	private SecurityService securityService;
	
	
	private UserService  userService;
	
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

private ActivityLogService logService;

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
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UserService getUserService() {
		return userService;
	}
	
	public void setSecurityService (SecurityService securityService){
		this.securityService = securityService;
	}
	public SecurityService getSecurityService (){
		return this.securityService;
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
	public void setPolicyBenefitService	(PolicyBenefitService policyBenefitService){
	this.policyBenefitService = policyBenefitService;
	}
	public PolicyBenefitService getPolicyBenefitService (){
	return this.policyBenefitService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
									Integer policyBenefitId = WebUtil.getParameterInteger (request,"policyBenefitId");
						
			/*
			Pkey ini merupakan representasi dari primary key, misalkan
			hasil get dari attribute diatas ^ itu cuma ada 1 key saja
			berarti tinggal pkey = <nama var diatas>
			tapi kalau misalkan composite primary key berarti
			tinggal bikin representasi primary key nya saja .
			*/
						java.io.Serializable pkey = policyBenefitId;
						
			ActionUser user = securityService.getActionUser(request);
		
			PolicyBenefit res = policyBenefitService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.policybenefit",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.policybenefit",null,"fail",Locale.getDefault()));

			}
			result = searchPerformed (request, response, "searchPolicyBenefit");
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
									Integer policyBenefitId = WebUtil.getParameterInteger (request,"policyBenefitId");
									
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
						java.io.Serializable pkey = policyBenefitId;
				        PolicyBenefit object = policyBenefitService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.policybenefit",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("policyBenefit", object);
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
		    Integer policyId = WebUtil.getParameterInteger(request, "policyId");

		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			

			Collection<PolicyBenefit> inpatient = getBenefit(policyId,
					CaseCategory.INPATIENT);
			Collection<PolicyBenefit> outpatient = getBenefit(policyId,
					CaseCategory.OUTPATIENT);
			Collection<PolicyBenefit> maternity = getBenefit(policyId,
					CaseCategory.MATERNITY);
			Collection<PolicyBenefit> dental = getBenefit(policyId,
					CaseCategory.DENTAL);
			Collection<PolicyBenefit> optical = getBenefit(policyId,
					CaseCategory.OPTICAL);

			Collection<PolicyBenefit> specialist = getBenefit(policyId,
					CaseCategory.SPECIALIST);
			
			Collection<PolicyBenefit> mcu = getBenefit(policyId,
					CaseCategory.MEDICAL_CHECK_UP);
			
			Collection<PolicyBenefit> misc = getBenefit(policyId,
					CaseCategory.MISC);
			
			Collection<PolicyBenefit> ppk1gigi = getBenefit(policyId,
					CaseCategory.GP_DENTAL);

			Collection<PolicyBenefit> ppk1Umum = getBenefit(policyId,
					CaseCategory.GP_OUTPATIENT);
			

			request.setAttribute("navigation", navigation);
			if (navigation.equals("downloadPolicyBenefit")) {
				navigation = "gosearch";
			}

			Policy policyObject = null;
			
			if(policyId != null){
				try
				{
				java.io.Serializable policypkey = policyId;
				System.out.println("member policy id : "+policyId);
				String[] policyRequired = {"Policy.BrokerId","Policy.ClientId","Policy.ProductType","Policy.CardTypeId"};
				policyObject = policyService.get(policypkey, policyRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			result.addObject("policy", policyObject);
			result.addObject("inpatient", inpatient);
			result.addObject("outpatient", outpatient);
			result.addObject("maternity", maternity);
			result.addObject("optical", optical);
			result.addObject("dental", dental);
			result.addObject("specialist", specialist);
			result.addObject("mcu", mcu);
			result.addObject("misc", misc);
			result.addObject("ppk1Dental", ppk1gigi);
			result.addObject("ppk1Umum", ppk1Umum);
			


		    result.addObject("policyId", policyId);
			request.setAttribute("countSet", countSet);
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("policyId", policyId);

		}
		catch (Exception e){
			e.printStackTrace();
	    		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));

		    result = new ModelAndView ("error");
		}

		return result;
	}
	
	public ModelAndView downloadXLSPolicyBenefit (HttpServletRequest request, HttpServletResponse response, String location) throws Exception {
	    ModelAndView result = null;
		try {
		    result = new ModelAndView (location);


			String rowset = WebUtil.getParameterString (request,"rowset","0");

		    Integer index = WebUtil.getParameterInteger (request, "index");
		    Integer policyId = WebUtil.getParameterInteger(request, "policyId");

		    String navigation = WebUtil.getParameterString (request,"navigation","");
		    String searchtext = WebUtil.getParameterString (request,"searchtext","");
		    String searchby = WebUtil.getParameterString (request, "searchby","");
			String sortby = WebUtil.getParameterString (request, "sortby", "");
			

			Collection<PolicyBenefit> inpatient = getBenefit(policyId,
					CaseCategory.INPATIENT);
			Collection<PolicyBenefit> outpatient = getBenefit(policyId,
					CaseCategory.OUTPATIENT);
			Collection<PolicyBenefit> maternity = getBenefit(policyId,
					CaseCategory.MATERNITY);
			Collection<PolicyBenefit> dental = getBenefit(policyId,
					CaseCategory.DENTAL);
			Collection<PolicyBenefit> optical = getBenefit(policyId,
					CaseCategory.OPTICAL);

			Collection<PolicyBenefit> specialist = getBenefit(policyId,
					CaseCategory.SPECIALIST);
			
			Collection<PolicyBenefit> mcu = getBenefit(policyId,
					CaseCategory.MEDICAL_CHECK_UP);
			
			Collection<PolicyBenefit> misc = getBenefit(policyId,
					CaseCategory.MISC);
			
			Collection<PolicyBenefit> ppk1gigi = getBenefit(policyId,
					CaseCategory.GP_DENTAL);

			Collection<PolicyBenefit> ppk1Umum = getBenefit(policyId,
					CaseCategory.GP_OUTPATIENT);
			

			request.setAttribute("navigation", navigation);
			if (navigation.equals("downloadPolicyBenefit")) {
				navigation = "gosearch";
			}

			Policy policyObject = null;
			
			if(policyId != null){
				try
				{
				java.io.Serializable policypkey = policyId;
				System.out.println("member policy id : "+policyId);
				String[] policyRequired = {"Policy.BrokerId","Policy.ClientId","Policy.ProductType","Policy.CardTypeId"};
				policyObject = policyService.get(policypkey, policyRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("member policy object : "+policyObject.getad());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			HSSFWorkbook workbook = PolicyBenefitDownloader.downloadExcel(policyId, policyBenefitService);
			System.out.println("POLICY ID : "+policyId);
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + System.currentTimeMillis()+".xls");

			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream sos = response.getOutputStream();

			workbook.write(sos);
			response.flushBuffer();
			sos.flush();
			sos.close();
			
			result.addObject("policy", policyObject);
			result.addObject("inpatient", inpatient);
			result.addObject("outpatient", outpatient);
			result.addObject("maternity", maternity);
			result.addObject("optical", optical);
			result.addObject("dental", dental);
			result.addObject("specialist", specialist);
			result.addObject("mcu", mcu);
			result.addObject("misc", misc);
			result.addObject("ppk1Dental", ppk1gigi);
			result.addObject("ppk1Umum", ppk1Umum);
			


		    result.addObject("policyId", policyId);
			request.setAttribute("countSet", countSet);
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("policyId", policyId);

		}
		catch (Exception e){
			e.printStackTrace();
	    		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));

		    result = new ModelAndView ("error");
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
    String breadcrumb = "";
    System.out.println("NAVIGATION : "+navigation);
	try {
	    if (navigation.equalsIgnoreCase ("detail")){
	    /*
	    	disesuaikan dengan halaman targetnya nih
	    */
	    	result = detailPerformed (request, response, "detailPolicyBenefit" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchPolicyBenefit" );
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupPolicyBenefit" );
	    }else if(navigation.equalsIgnoreCase("export")){
//	    	result = searchPerformed(request, response, "searchPolicyBenefit");
//	    	downloadPolicyBenefitPerformed(request, response);
	    	result = downloadXLSPolicyBenefit(request, response, "searchPolicyBenefit");
	    }
	    else {
	    	result = searchPerformed (request, response, "searchPolicyBenefit" );
	    	String policyId = request.getParameter("policyId");
			breadcrumb = "<a href=\"policy?navigation=detail&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Benefit";
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
  private Collection<PolicyBenefit> getBenefit(Integer policyId,
			Integer benefitType) throws Exception {

		Collection collection = null;

		Vector vLikeP = new Vector();
		Vector vLikeQ = new Vector();
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();

		vEqP.add("deletedStatus");
		vEqQ.add(Integer.valueOf(0));


		if (policyId != null) {
			vEqP.add("policyId.policyId");
			vEqQ.add(policyId);
		}
		if (benefitType != null) {
			vEqP.add("caseCategoryId.caseCategoryId");
			vEqQ.add(benefitType);
		}

		String sLikeP[] = new String[vLikeP.size()];
		vLikeP.toArray(sLikeP);
		Object sLikeQ[] = new Object[vLikeP.size()];
		vLikeQ.toArray(sLikeQ);

		String sEqP[] = new String[vEqP.size()];
		vEqP.toArray(sEqP);
		Object sEqQ[] = new Object[vEqP.size()];
		vEqQ.toArray(sEqQ);

		String required[] = new String[] { "PolicyBenefit.PolicyId",
				"PolicyBenefit.ItemCategoryId","PolicyBenefit.CaseCategoryId","PolicyBenefit.ProcedureId","PolicyBenefit.DiagnosisId"
				,"PolicyBenefit.DiagnosisSetId"};

		int total = policyBenefitService.getTotal(sLikeP,sLikeQ,sEqP,sEqQ);
		collection = policyBenefitService.search(sLikeP, sLikeQ, sEqP, sEqQ,required,0,total);

		if (collection != null && collection.size() > 0) {
			return collection;
		} else {
			return null;
		}

	}

  public void downloadPolicyBenefitPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			//result = new ModelAndView ("searchPolicyBenefit");
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			HSSFWorkbook workbook = PolicyBenefitDownloader.downloadExcel(policyId, policyBenefitService);
			System.out.println("POLICY ID : "+policyId);
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + System.currentTimeMillis()+".xls");

			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ServletOutputStream sos = response.getOutputStream();

			workbook.write(sos);
			response.flushBuffer();
			sos.flush();
			sos.close();
			//request.setAttribute("navigation", "");
//			request.setAttribute("actionType", actionType);
//			request.setAttribute("countSet", countSet);
			//request.setAttribute("index", 0);
			//request.setAttribute("alert", request.getParameter("alert"));
			//request.setAttribute("policyId", policyId);
			//result = searchPerformed(request, response, "searchMemberImport");
			//response.sendRedirect(location)
			//String contextPath = ((HttpServletRequest) request).getContextPath();
            //response.sendRedirect(contextPath + "/policybenefit?navigation=list&policyId="+policyId);
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return;
	}

// class+ 

// class- 

}
