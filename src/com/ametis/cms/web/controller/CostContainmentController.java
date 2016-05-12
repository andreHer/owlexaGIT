package com.ametis.cms.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Collection;
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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.CostContainmentType;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.CostContainmentService;
import com.ametis.cms.service.CostContainmentTypeService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * CostContainment is a servlet controller for cost_containment Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class CostContainmentController implements Controller

// extends+

// extends-

{

	private CostContainmentService costContainmentService;
	
	private CostContainmentTypeService costContainmentTypeService;
	
	private CaseService caseService;
	
	private ClientService clientService;

	private MemberGroupService memberGroupService;
	
	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	private SecurityService securityService;
private ActivityLogService logService;
	private ConfigurationService configurationService;
	
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
	
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	public CostContainmentTypeService getCostContainmentTypeService() {
		return costContainmentTypeService;
	}

	public void setCostContainmentTypeService(
			CostContainmentTypeService costContainmentTypeService) {
		this.costContainmentTypeService = costContainmentTypeService;
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

	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}

	public Integer getCountSet() {
		return this.countSet;
	}
	
	


	public void setMaxPercountSet(Integer maxCount) {
		this.maxPercountSet = maxCount;
	}

	public Integer getMaxPercountSet() {
		return this.maxPercountSet;
	}

	public void setAlertProperties(ResourceBundleMessageSource alert) {
		this.alertProperties = alert;
	}

	public ResourceBundleMessageSource getAlertProperties() {
		return alertProperties;
	}

	public void setCostContainmentService(
			CostContainmentService costContainmentService) {
		this.costContainmentService = costContainmentService;
	}

	public CostContainmentService getCostContainmentService() {
		return this.costContainmentService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer costContainmentId = WebUtil.getParameterInteger(request,
					"costContainmentId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = costContainmentId;

			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECOSTCONTAINMENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECOSTCONTAINMENT access");
				return errorResult;
				
			}
			CostContainment res = costContainmentService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.costcontainment", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.costcontainment", null, "fail", Locale
								.getDefault()));

			}
			result = new ModelAndView(new RedirectView("costcontainment?navigation=list&caseId="+res.getCaseId().getCaseId()));
		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer costContainmentId = WebUtil.getParameterInteger(request,
					"costContainmentId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = costContainmentId;
			CostContainment object = costContainmentService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.costcontainment", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("costContainment", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView printPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			String location = "printCostContainment";

			Configuration configuration = configurationService.getSystemConfiguration();

			location = location+"_"+configuration.getCompanyCode().toLowerCase();
			
			String path = request.getSession().getServletContext().
					getRealPath("/WEB-INF/jsp/costContainment/"); 
			File file = new File(path,location+".jsp");
			if (!file.exists()){
				location = "printCostContainment";
			}
			
			result = new ModelAndView(location);
			
			
			String[] eqParam = {"deletedStatus","caseId.caseId"};
			Object[] eqValue = {Integer.valueOf(0),caseId};
			String[] required = {"Case.MemberId","Case.MemberId.MemberGroupId","Case.ClaimId","Case.ClaimId.DiagnosisId"};
			
			int total = costContainmentService.getTotal(null,null,eqParam,eqValue);
			
			Collection<CostContainment> costContainments = costContainmentService.search(null,null,eqParam,eqValue,0,total);
			
			Case theCase = caseService.get(caseId,required);
			
			String currentTime = DateFormat.getDateTimeInstance(
		            DateFormat.MEDIUM, DateFormat.SHORT).format(new java.sql.Date(System.currentTimeMillis()));
			
			result.addObject("currentTime", currentTime);
			
			result.addObject("costContainments", costContainments);
			result.addObject("myCase", theCase);
			result.addObject("configuration", configuration);
			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView jsonTotalPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			ActionUser actionUser = securityService.getActionUser(request);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			String location = "printCostContainment";

			Configuration configuration = configurationService.getSystemConfiguration();

			location = location+"_"+configuration.getCompanyCode().toLowerCase();
			
			result = new ModelAndView(location);
			
			
			String[] eqParam = {"deletedStatus","caseId.caseId"};
			Object[] eqValue = {Integer.valueOf(0),caseId};
			
			int total = costContainmentService.getTotal(null,null,eqParam,eqValue);
			
			Collection<CostContainment> costContainments = costContainmentService.search(null,null,eqParam,eqValue,0,total);
			
			Case theCase = caseService.get(caseId);
			
			result.addObject("costContainments", costContainments);
			result.addObject("myCase", theCase);
			result.addObject("configuration", configuration);
			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			
			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");
			
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			
			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			
			String subNav = WebUtil.getParameterString(request,"subnav","");
			
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");
			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			
			result.addObject("subnavigation",subnavigation);

			
			Collection<CostContainmentType> costContainmentTypes = costContainmentTypeService.getAll();
			
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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golist")  ||
					navigation.equals("list") || navigation.equalsIgnoreCase("listgroup") || navigation.equalsIgnoreCase("listclient")) {

				if (searchby != null) {
				

					if (searchby.equalsIgnoreCase("itemName")) {
						vLikeP.add("itemId.itemName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("substitutionItem")){
						vLikeP.add("substitutionItemId.itemName");
						vLikeQ.add(searchtext);
					}
			
				}

			}

			if (navigation.equalsIgnoreCase("listclient") || subNav.equalsIgnoreCase("listclient")){
				if (searchby.equalsIgnoreCase("costConstainmentTypeName")) {
					vLikeP.add("costConstainmentTypeName");
					vLikeQ.add(searchtext);
				}
				
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
				result.addObject("clientId",clientId);
				
			}
			else if (navigation.equalsIgnoreCase("list") || navigation.equalsIgnoreCase("golist")){
				vEqP.add("caseId.caseId");
				vEqQ.add(caseId);
				result.addObject("caseId",caseId);
			}
			
			if (navigation.equalsIgnoreCase("listclaim")){
				if (claimId != null){
					vEqP.add("claimId.claimId");
					vEqQ.add(claimId);
				}
			}
			
			if (navigation.equalsIgnoreCase("listgroup")){
				vEqP.add("memberId.memberGroupId.memberGroupId");
				vEqQ.add(memberGroupId);
				result.addObject("memberGroupId", memberGroupId);
			}
			
			if (caseId != null){
				Case theCase = caseService.get(caseId);
				
				result.addObject("theCase", theCase);
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

			count = costContainmentService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String arah = WebUtil.getParameterString(request, "arah", "");

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
			"CostContainment.ItemId",
			// foreign affairs end
			};
			
			collection = costContainmentService.search(sLikeP, sLikeQ, sEqP,
					sEqQ, required, rowsetint, countSet.intValue());

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
				collection = costContainmentService.search(sLikeP, sLikeQ,
						sEqP, sEqQ, required, rowsetint, countSet.intValue());
			}
			
			
			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			result.addObject("costContainmentType", costContainmentTypes);
			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			MemberGroup memberGroupObject = null;
			
			if(memberGroupId != null){
				try
				{
				java.io.Serializable memberGrouppkey = memberGroupId;
				System.out.println("member group id : "+memberGroupId);
				String[] memberGroupRequired = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
				memberGroupObject = memberGroupService.get(memberGrouppkey, memberGroupRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member group object : "+memberGroupObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			Client clientObject = null;
			
			if(clientId != null){
				try
				{
				java.io.Serializable clientpkey = clientId;
				System.out.println("member client id : "+clientId);
				String[] clientRequired = {"Client.FundCurrency","Client.PaymentCurrency","Client.StatusId"};
				clientObject = clientService.get(clientpkey, clientRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member client object : "+clientObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			
			Case caseObject = null;
			
			if(caseId != null){
				try
				{
				java.io.Serializable casepkey = caseId;
				System.out.println("case Id : "+caseId);
				//modified by aju on 20150423, Add link Case->CaseStatus
				String[] caseRequired = {"Case.MemberId","Case.MemberId.ParentMember","Case.MemberId.MemberGroupId","Case.ClaimId","Case.Diagnosis2Id","Case.Diagnosis3Id","Case.CaseStatusId"};
				caseObject = caseService.get(casepkey, caseRequired);
				
				}
				catch (Exception ex)
				{
//					System.out.println("member group object : "+caseObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			result.addObject("CostContainments", collection);
			result.addObject("memberId", memberId);
			result.addObject("myCase", caseObject);
			result.addObject("claimId", claimId);
			result.addObject("client", clientObject);
			result.addObject("memberGroup", memberGroupObject);
			request.setAttribute("clientId", clientId);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");
		
		String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
		Integer clientId = WebUtil.getParameterInteger(request,"clientId");

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
		 * result = new ModelAndView ("login"); } else {
		 *  }
		 */
		String breadcrumb = "<a href=\"costcontainment\" class=\"linkbreadcrumb\">Search Cost Containment</a>";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailCostContainment");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchCostContainment");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupCostContainment");
			} else if (navigation.equalsIgnoreCase("listclient") || subnavigation.equalsIgnoreCase("listclient")){
				result = searchPerformed(request, response, "listClientCostContainment");
//				String clientId = request.getParameter("clientId");
				breadcrumb = "<a href=\"client?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Cost Containment";
			}
			else if (navigation.equalsIgnoreCase("list") || subnavigation.equals("list") || navigation.equalsIgnoreCase("golist")){
				result = searchPerformed(request, response, "listCaseCostContainment");
				String caseId = request.getParameter("caseId");	
				breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Treatment Item";
			}
			else if (navigation.equalsIgnoreCase("listclaim")){
				result = searchPerformed(request, response, "listClaimCostContainment");
			}
			else if (navigation.equalsIgnoreCase("listgroup")){
				result = searchPerformed(request, response, "listGroupCostContainment");
				String groupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+groupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Group Cost Containment";
			}
			else if (navigation.equalsIgnoreCase("printcase")){
				System.out.println("MASUK PRINT CASE");
				result = printPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("jsontotal")){
				
			}
			else {
				result = searchPerformed(request, response,
						"searchCostContainment");
			}
			result.addObject("breadcrumb", breadcrumb);
			request.setAttribute("clientId", clientId);
			request.setAttribute("navigation", navigation);

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
