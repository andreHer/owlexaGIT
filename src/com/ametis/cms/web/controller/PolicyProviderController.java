package com.ametis.cms.web.controller;

import java.io.IOException;
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

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.PolicyProvider;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+

// imports-

/**
 * PolicyProvider is a servlet controller for policy_provider Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class PolicyProviderController implements Controller

// extends+

// extends-
{

	private PolicyProviderService policyProviderService;
	private SecurityService securityService;
	private PolicyService  policyService;
	private UserService actionuserService;

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
	
	public UserService getUserService() {
		return actionuserService;
	}

	public void setUserService(UserService actionuserService) {
		this.actionuserService = actionuserService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public void setActionUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public UserService getActionUserService() {
		return actionuserService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
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

	public void setPolicyProviderService(
			PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
	}

	public PolicyProviderService getPolicyProviderService() {
		return this.policyProviderService;
	}

	public ModelAndView lookupJsonPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			System.out.println("lookup json provider dipanggil");
			String q = WebUtil.getParameterString(request, "q", "");

			
			Collection<PolicyProvider> provider = policyProviderService.searchMemberProvider(q,policyId,memberId);

			result.addObject("Providers", provider);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer id = WebUtil.getParameterInteger(request, "id");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = id;

			ActionUser user = securityService.getActionUser(request);

			PolicyProvider res = policyProviderService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.policyprovider", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.policyprovider", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchPolicyProvider");
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
			Integer id = WebUtil.getParameterInteger(request, "id");

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
			java.io.Serializable pkey = id;
			PolicyProvider object = policyProviderService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.policyprovider", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("policyProvider", object);
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
			String searchby = WebUtil.getParameterString(request, "searchby","");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

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
			
			if (navigation.equals("gosearch") || navigation.equals("golookup")) {

				if (searchby != null) {
					
				}

			}
//
			vEqP.add("policyId.policyId");
			vEqQ.add(policyId);

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

			count = policyProviderService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			// foreign affairs end
			};
			collection = policyProviderService.search(sLikeP, sLikeQ, sEqP,
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
				collection = policyProviderService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("policyId", policyId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
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
			result.addObject("PolicyProviders", collection);

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
		String breadcrumb = "";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailPolicyProvider");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchPolicyProvider");
			}  else if (navigation.equalsIgnoreCase("lookupjson")) {
				result = lookupJsonPerformed(request, response,
				"lookupProviderJson");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupPolicyProvider");
			} 
			else if (navigation.equalsIgnoreCase("listpolicy")) {          
				result = searchPerformed(request, response, "searchPolicyProvider");
				String policyId = request.getParameter("policyId");
				breadcrumb = "<a href=\"policy?navigation=detail&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Provider";
			}
			else {
				result = searchPerformed(request, response,
						"searchPolicyProvider");
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