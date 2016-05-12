
package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * PolicyClausul is a servlet controller for policy_clausul Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class PolicyClausulController implements Controller

// extends+ 

// extends- 
{
	
	private PolicyClausulService  policyClausulService;
	private SecurityService securityService;
	private PolicyService  policyService;
	
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
	public void setPolicyClausulService	(PolicyClausulService policyClausulService){
	this.policyClausulService = policyClausulService;
	}
	public PolicyClausulService getPolicyClausulService (){
	return this.policyClausulService;
	}
	public ModelAndView deletePerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
									Integer id = WebUtil.getParameterInteger (request,"id");
						
			/*
			Pkey ini merupakan representasi dari primary key, misalkan
			hasil get dari attribute diatas ^ itu cuma ada 1 key saja
			berarti tinggal pkey = <nama var diatas>
			tapi kalau misalkan composite primary key berarti
			tinggal bikin representasi primary key nya saja .
			*/
						java.io.Serializable pkey = id;
						
			ActionUser user = securityService.getActionUser(request);
		
			PolicyClausul res = policyClausulService.delete (pkey,user);

			if (res!=null){
				request.setAttribute ("alert", alertProperties.getMessage ("success.delete.policyclausul",null,"success",Locale.getDefault()));
			}
			else {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.delete.policyclausul",null,"fail",Locale.getDefault()));

			}
			result = searchPerformed (request, response, "searchPolicyClausul");
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
			Integer id = WebUtil.getParameterInteger (request,"id");
									
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
						java.io.Serializable pkey = id;
				        PolicyClausul object = policyClausulService.get (pkey);

			if (object == null){
				request.setAttribute ("alert", alertProperties.getMessage ("not.found.policyclausul",null,"fail",Locale.getDefault()));
			}
			/*
			convention .. kalo mau nampilkan sebuah object
			pake BeanClass aja ya .. ga pake 's'
			-adhit
			*/

		    result.addObject ("policyClausul", object);
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
			
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

		    Collection collection = null;

		    int rowsetint = 0;
		    int count = 0;


		    if (StringUtils.isNumeric(rowset)) {
		      rowsetint = Integer.parseInt(rowset);
		    }
		    String[] eqParam = {"deletedStatus","caseCategoryId.caseCategoryId","policyId.policyId"};
		    Object[] eqValueInpatient = {Integer.valueOf(0),CaseCategory.INPATIENT,policyId};
		    
		    
		    Object[] eqValueOutpatient = {Integer.valueOf(0),CaseCategory.OUTPATIENT,policyId};
		    
		    
		    Object[] eqValueMaternity = {Integer.valueOf(0),CaseCategory.MATERNITY,policyId};
		    
		    
		    Object[] eqValueDental = {Integer.valueOf(0),CaseCategory.DENTAL,policyId};
		    
		    
		    Object[] eqValueOptical = {Integer.valueOf(0),CaseCategory.OPTICAL,policyId};
		    Object[] eqValueMcu = {Integer.valueOf(0),CaseCategory.MEDICAL_CHECK_UP,policyId};
		    
		    int totalInpatient = policyClausulService.getTotal(null,null,eqParam,eqValueInpatient);
		    Collection<PolicyClausul> inpatientList = policyClausulService.search(null,null,eqParam,eqValueInpatient,0,totalInpatient);
		    
		    int totalOutpatient = policyClausulService.getTotal(null,null,eqParam,eqValueOutpatient);
		    Collection<PolicyClausul> outpatientList = policyClausulService.search(null,null,eqParam,eqValueOutpatient,0,totalOutpatient);
		    
		    int totalMaternity = policyClausulService.getTotal(null,null,eqParam,eqValueMaternity);
		    Collection<PolicyClausul> maternityList = policyClausulService.search(null,null,eqParam,eqValueMaternity,0,totalMaternity);
		    
		    int totalDental = policyClausulService.getTotal(null,null,eqParam,eqValueDental);
		    Collection<PolicyClausul> dentalList = policyClausulService.search(null,null,eqParam,eqValueDental,0,totalDental);
		    
		    int totalOptical = policyClausulService.getTotal(null,null,eqParam,eqValueOptical);
		    Collection<PolicyClausul> opticalList = policyClausulService.search(null,null,eqParam,eqValueOptical,0,totalOptical);

		    int totalMcu = policyClausulService.getTotal(null,null,eqParam,eqValueMcu);
		    Collection<PolicyClausul> mcuList = policyClausulService.search(null,null,eqParam,eqValueMcu,0,totalMcu);
		    
		    Policy policyObject = null;
			
			if(policyId != null){
				try
				{
				java.io.Serializable policypkey = policyId;
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
		    result.addObject("inpatientClausuls", inpatientList);
		    result.addObject("outpatientClausuls", outpatientList);
		    result.addObject("maternityClausuls", maternityList);
		    result.addObject("dentalClausuls", dentalList);
		    result.addObject("opticalClausuls", opticalList);
		    result.addObject("mcuClausuls", mcuList);
		    
		    result.addObject("policyId", policyId);
		    request.setAttribute("navigation", navigation);
			request.setAttribute("countSet", countSet);
			request.setAttribute("alert", request.getParameter("alert"));

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
	try {
	    if (navigation.equalsIgnoreCase ("detail")){
	    /*
	    	disesuaikan dengan halaman targetnya nih
	    */
	    	result = detailPerformed (request, response, "detailPolicyClausul" );
	    }
	    else if (navigation.equalsIgnoreCase ("delete")){
	    	result = deletePerformed (request, response );
	    }
	    else if (navigation.equalsIgnoreCase ("search") || navigation.equals("gosearch")){
	    	result = searchPerformed (request, response, "searchPolicyClausul" );
	    }
	    else if (navigation.equalsIgnoreCase ("lookup") || navigation.equals("golookup")){
	    	result = searchPerformed (request, response, "lookupPolicyClausul" );
	    }
	    else {
	    	result = searchPerformed (request, response, "searchPolicyClausul" );
	    	String policyId = request.getParameter("policyId");
			breadcrumb = "<a href=\"policy?navigation=detail&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Clausul";
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
