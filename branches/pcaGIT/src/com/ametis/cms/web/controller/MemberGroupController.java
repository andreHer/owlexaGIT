package com.ametis.cms.web.controller;

import java.io.IOException;
import java.sql.Date;
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

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * MemberGroup is a servlet controller for member_group Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class MemberGroupController implements Controller

// extends+

// extends-

{

	private MemberGroupService memberGroupService;
	
	private ClaimService claimService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	private SecurityService securityService;

	private ActivityLogService logService;
		
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
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

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public MemberGroupService getMemberGroupService() {
		return this.memberGroupService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = memberGroupId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEMEMBERGROUP");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEMEMBERGROUP access");
				return errorResult;
				
			}
			MemberGroup res = memberGroupService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.membergroup", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.membergroup", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchMemberGroup");
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
	public ModelAndView activatePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			
			String searchtext = WebUtil.getParameterString(request,	"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			ActionUser actionUser = securityService.getActionUser(request);
			result = new ModelAndView(new RedirectView("membergroup?navigation=detail&memberGroupId="+memberGroupId));
			
			
			boolean res = memberGroupService.activate(memberGroupId, actionUser);
			
			if (res){
				result.addObject("alert", "<b>Success Activating Member Group</b>");
			}
			else {
				result.addObject("alert","<b>Failed Activating Member Group</b>");
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);

		} 
		catch (Exception e) {
			
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

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			
			String searchtext = WebUtil.getParameterString(request,	"searchtext", "");
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
			java.io.Serializable pkey = memberGroupId;
			String[] required = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
			MemberGroup object = memberGroupService.get(pkey,required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.membergroup", null, "fail", Locale
								.getDefault()));
			}
			
			double totalBenefitUsage = 0;
			double percentage = 0.0;
			
			if (object != null){
				totalBenefitUsage = claimService.getTotalBenefitUsage(object, object.getEffectiveDate(),object.getExpireDate());
			
				if (totalBenefitUsage > 0){
					//percentage = totalBenefitUsage/object.getAnnualGroupPremium().doubleValue() * 100;					
				}
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			
			result.addObject("memberGroup", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("totalBenefitUsage",totalBenefitUsage);
			result.addObject("claimPercentage", percentage);
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView lookupJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
			String navigation = request.getParameter("navigation");
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			
			result = new ModelAndView(location);


			String q = WebUtil.getParameterString(request, "q", "");

			Collection<MemberGroup> members = null;
			
			if (navigation.equalsIgnoreCase("lookupinvoicejson")){			
				members = memberGroupService.searchOutstandingExcessGroup(q);
			}else{
				if (clientId != null){
					String[] eqParam = {"deletedStatus","clientId.clientId"};
					Object[] eqValue = {0,clientId};
					
					String[] likeParam = {"groupName"};
					Object[] likeValue = {q};
					
					members = memberGroupService.search(likeParam,likeValue,eqParam,eqValue,0,10);
				}
				else {
					members = memberGroupService.searchGroup(q);
				}
			}
					
			
			result.addObject("MemberGroups", members);

			
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView showGroupMapPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {

			Integer memberGroupId = WebUtil.getParameterInteger(request,
					"memberGroupId");


			result = new ModelAndView(location);
			java.io.Serializable pkey = memberGroupId;
			MemberGroup object = memberGroupService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.membergroup", null, "fail", Locale
								.getDefault()));
			}
			
			
			result.addObject("memberGroup", object);
		} 
		catch (Exception e) {
			
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

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer searchStatus = WebUtil.getParameterInteger(request,
					"status");
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortGroupName = true, sortGroupCode = true, sortPolicyNo = true, sortProvince = true,
					sortCity = true, sortPolicyAgent = true, sortStatus = true;
			
			boolean order = true;

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			ActionUser user = securityService.getActionUser(request);
			
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
			
			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("lookupjson")||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("faximile")) {
						vLikeP.add("faximile");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("website")) {
						vLikeP.add("website");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("telephone")) {
						vLikeP.add("telephone");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("email")) {
						vLikeP.add("email");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("groupName")) {
						vLikeP.add("groupName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("postalCode")) {
						vLikeP.add("postalCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("address")) {
						vLikeP.add("address");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyAgent")) {
						vLikeP.add("policyAgent");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyNumber")) {
						vLikeP.add("policyNumber");
						vLikeQ.add(searchtext);
					}
					
					//if (searchStatus != null && searchStatus.intValue() > 0) {
					//	vEqP.add("status.statusId");
					//	vEqQ.add(searchStatus);
					//}
				}
				if (searchStatus != null && searchStatus.intValue() > 0) {
					vEqP.add("status.statusId");
					vEqQ.add(searchStatus);
				}
			}


			if (clientId != null){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
			}
			
			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.CLIENT){
					vEqP.add("clientId.clientId");
					vEqQ.add(user.getUser().getClientId().getClientId());
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

			count = memberGroupService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			
			//Start Pagination Kiri Kanan
			//if(navigation.equals("gosearch") && arah.isEmpty())
			//	checkOrder = false;

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
			"MemberGroup.ClientId",
			// foreign affairs end
			};
			
			/*Tambah Sorting*/
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("groupname")){
						sortByColumn = "groupName";
						Boolean sortByOrderName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderName", ""));
						sortGroupName = !sortByOrderName;
						order = sortGroupName;
					}else if(sortcolumn.equalsIgnoreCase("groupcode")){
						sortByColumn = "memberGroupCode";
						Boolean sortByOrderCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCode", ""));
						sortGroupCode = !sortByOrderCode;
						order = sortGroupCode;
					}else if(sortcolumn.equalsIgnoreCase("policynumber")){
						sortByColumn = "policyNumber";
						Boolean sortByOrderPolicyNo = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderPolicyNo", ""));
						sortPolicyNo = !sortByOrderPolicyNo;
						order = sortPolicyNo;
					}else if(sortcolumn.equalsIgnoreCase("province")){
						sortByColumn = "province";
						Boolean sortByOrderProvince = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProvince", ""));
						sortProvince = !sortByOrderProvince;
						order = sortProvince;
					}else if(sortcolumn.equalsIgnoreCase("city")){
						sortByColumn = "city";
						Boolean sortByOrderCity = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCity", ""));
						sortCity = !sortByOrderCity;
						order = sortCity;
					}else if(sortcolumn.equalsIgnoreCase("policyagent")){
						sortByColumn = "policyAgent";
						Boolean sortByOrderPolicyAgent = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderPolicyAgent", ""));
						sortPolicyAgent = !sortByOrderPolicyAgent;
						order = sortPolicyAgent;
					}else{
						sortByColumn = "status.status";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("groupname")){
						sortGroupName = order;
					}else if(sortcolumn.equalsIgnoreCase("groupcode")){
						sortGroupCode = order;
					}else if(sortcolumn.equalsIgnoreCase("policynumber")){
						sortPolicyNo = order;
					}else if(sortcolumn.equalsIgnoreCase("province")){
						sortProvince = order;
					}else if(sortcolumn.equalsIgnoreCase("city")){
						sortCity = order;
					}else if(sortcolumn.equalsIgnoreCase("policyagent")){
						sortPolicyAgent = order;
					}else{
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = memberGroupService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, sortByColumn, order, rowsetint, countSet.intValue());
			}else{
				collection = memberGroupService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}
			
			

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
				collection = memberGroupService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("status", searchStatus);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("MemberGroups", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortGroupName", sortGroupName);
			request.setAttribute("sortPolicyNo", sortPolicyNo);
			request.setAttribute("sortProvince", sortProvince);
			request.setAttribute("sortCity", sortCity);
			request.setAttribute("sortPolicyAgent", sortPolicyAgent);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortGroupCode", sortGroupCode);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView	unblockPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String reason = WebUtil.getParameterString(request, "reason","");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			

			result = new ModelAndView(location);
			
			
			
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE UNBLOCKING MEMBER");
			ActionResult terminate = memberGroupService.unblock(memberGroupId,reason,  actionUser);
			System.out.println("POST UNBLOCKING MEMBER");
			MemberGroup object = memberGroupService.get(memberGroupId);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			if (terminate != null){
			
				if (!terminate.isResult()) {
					result.addObject("alert","<b>Failed Unblock Member Group - " + terminate.getAdditionalMessage() + " </b>");
				}
				else {
					result.addObject("alert","<b>Success Unblock Member Group</b>");
				}
			}
			

			String[] required = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
			object = memberGroupService.get(memberGroupId,required);
			
			result.addObject("navigation", "detail");
			result.addObject("memberGroup", object);
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
	public ModelAndView	blockPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String reason = WebUtil.getParameterString(request, "reason","");
			Date start = WebUtil.getParameterDate(request, "startDate");
			Date end = WebUtil.getParameterDate(request, "endDate");
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
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE BLOCK MEMBER");
			ActionResult terminate = memberGroupService.block(memberGroupId,reason,start,end,  actionUser);
			System.out.println("POST BLOCK MEMBER");
			MemberGroup object = (MemberGroup) terminate.getResultObject();

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			if (terminate != null){
			
				if (!terminate.isResult()) {
					result.addObject("alert","<b>Failed Block Member Group - " + terminate.getAdditionalMessage() + " </b>");
				}
				else {
					result.addObject("alert","<b>Success Block Member Group</b>");
				}
			}


			String[] required = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
			object = memberGroupService.get(memberGroupId,required);
			 

			result.addObject("navigation", "detail");
			result.addObject("memberGroup", object);
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
	public ModelAndView	terminatePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer memberGroupId = WebUtil.getParameterInteger(request, "memberGroupId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String reason = WebUtil.getParameterString(request, "reason","");
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
			ActionUser actionUser = securityService.getActionUser(request); 
			
			System.out.println("PRE BLOCK MEMBER");
			MemberGroup memberGroup = memberGroupService.get(memberGroupId);
			
			

			if (memberGroup == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.member", null, "fail", Locale.getDefault()));
			}
			if (memberGroup != null){
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.TERMINATED);
				memberGroup.setStatus(status);
				
				MemberGroup res = memberGroupService.update(memberGroup, actionUser);
				if (res != null) {
					result.addObject("alert","<b>Failed Block Member Group </b>");
				}
				else {
					result.addObject("alert","<b>Success Block Member Group</b>");
				}
			}
			
			String[] required = {"MemberGroup.ClientId","MemberGroup.BusinessCategoryId"};
			memberGroup = memberGroupService.get(memberGroupId,required);

			result.addObject("navigation", "detail");
			result.addObject("memberGroup", memberGroup);
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

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome"
				: request.getParameter("navigation");

		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);

		String breadcrumb = "";
		
		try {
			System.out.println("NAVIGATION : "+navigation);
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response, "detailMemberGroup");
				String memberGroupId = request.getParameter("memberGroupId");
				breadcrumb = "<a href=\"membergroup?navigation=detail&memberGroupId="+memberGroupId+"\" class=\"linkbreadcrumb\">Detail Member Group</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				
				result = searchPerformed(request, response, "searchMemberGroup");
				breadcrumb = "<a href=\"membergroup\" class=\"linkbreadcrumb\">Search Member Group</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupMemberGroup");
			}
			else if (navigation.equals("activate")){
				result = activatePerformed (request, response,"detailMemberGroup");
			}
			else if (navigation.equalsIgnoreCase("print")){
				result = detailPerformed(request, response, "printMemberGroup");
			}
			else if (navigation.equalsIgnoreCase("lookupjson")){
				//result = searchPerformed(request, response, "lookupMemberGroupJson");
				result = lookupJsonPerformed(request, response, "lookupMemberGroupJson");
			}
			else if (navigation.equalsIgnoreCase("lookupinvoicejson")){
				result = lookupJsonPerformed(request, response, "lookupMemberGroupJson");
			}
			else if (navigation.equalsIgnoreCase("showgroupmap")){
				result = showGroupMapPerformed(request, response, "showGroupMap");
			}
			else if (navigation.equalsIgnoreCase("block")){
				result = blockPerformed(request, response, "detailMemberGroup");
			}
			else if (navigation.equalsIgnoreCase("terminate")){
				result = terminatePerformed(request, response, "detailMemberGroup");
			}
			else if (navigation.equalsIgnoreCase("preblock")){
				result = detailPerformed(request, response, "blockMemberGroup");
			}
			else if (navigation.equalsIgnoreCase("unblock")){
				result = unblockPerformed(request, response, "detailMemberGroup");
			}
			else if (navigation.equalsIgnoreCase("preunblock")){
				result = detailPerformed(request, response, "unblockMemberGroup");
			}
			else {
				result = searchPerformed(request, response, "searchMemberGroup");
				breadcrumb = "<a href=\"membergroup\" class=\"linkbreadcrumb\">Search Member Group</a>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-

}
