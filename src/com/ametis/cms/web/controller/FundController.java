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

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.Fund;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.FundService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.MoneyParser;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

public class FundController implements Controller
{
	private SecurityService securityService;

	private FundService fundService;
	
	private PolicyService  policyService;
	
	private ClientService clientService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	
	private ProviderService providerService;
	
	
	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}


	private ActivityLogService logService;
	private ConfigurationService configurationService;
	
	public PolicyService getPolicyService() {
		return policyService;
	}
	
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public ClientService getClientService() {
		return clientService;
	}
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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

	public void setFundService(FundService fundService) {
		this.fundService = fundService;
	}

	public FundService getFundService() {
		return this.fundService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer fundId = WebUtil.getParameterInteger(request, "fundId");

			java.io.Serializable pkey = fundId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEFUND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEFUND access");
				return errorResult;
				
			}
			Fund res = fundService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.fund", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.fund", null, "fail", Locale.getDefault()));

			}
			result = searchPerformed(request, response, "searchFund");
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
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILFUND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILFUND access");
				return errorResult;
				
			}
			Integer fundId = WebUtil.getParameterInteger(request, "fundId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String listnavigation = WebUtil.getParameterString(request, "listnavigation","");
			
			result = new ModelAndView(location);
			java.io.Serializable pkey = fundId;
			Fund object = fundService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.fund", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("fund", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("listnavigation", listnavigation);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView printRequestLetterPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILFUND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILFUND access");
				return errorResult;
				
			}
			Integer fundId = WebUtil.getParameterInteger(request, "fundId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");

			String location = "";
			java.io.Serializable pkey = fundId;
			Fund object = fundService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.fund", null, "fail", Locale.getDefault()));
			}
			if (object != null){
				Configuration config = configurationService.getClientConfiguration(object.getClientId().getClientId());
				
				if (config == null){
					config = configurationService.getSystemConfiguration();

					if (config != null){
						location = "printFundRequest_"+ config.getCompanyCode().toLowerCase();
					}
				}
				else {				
					if (config != null && (config.getCompanyCode() != null || !config.getCompanyCode().equals(""))){
						location = "printFundRequest_"+ config.getCompanyCode().toLowerCase();
					}
					else {
						config = configurationService.getSystemConfiguration();
						location = "printFundRequest_"+ config.getCompanyCode().toLowerCase();

					}
				}
				
			}
			result = new ModelAndView(location);
			result.addObject("valueDescription", MoneyParser.convert( object.getFundValue().longValue()));
			result.addObject("fund", object);
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
	public ModelAndView printApprovalLetterPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DETAILFUND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DETAILFUND access");
				return errorResult;
				
			}
			Integer fundId = WebUtil.getParameterInteger(request, "fundId");

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

			String location = "";
			java.io.Serializable pkey = fundId;
			Fund object = fundService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.fund", null, "fail", Locale.getDefault()));
			}
			if (object != null){
				Configuration config = configurationService.getClientConfiguration(object.getClientId().getClientId());
				
				if (config == null){
					config = configurationService.getSystemConfiguration();

					if (config != null){
						location = "printFundApproval_"+ config.getCompanyCode().toLowerCase();
					}
				}
				else {				
					if (config != null && (config.getCompanyCode() != null || !config.getCompanyCode().equals(""))){
						location = "printFundApproval_"+ config.getCompany().toLowerCase();
					}
					else {
						config = configurationService.getSystemConfiguration();
						location = "printFundApproval_"+ config.getCompany().toLowerCase();

					}
				}
				
			}
			result = new ModelAndView(location);

			result.addObject("fund", object);
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
	public ModelAndView approvePerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "APPROVEFUND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for APPROVEFUND access");
				return errorResult;
				
			}
			Integer fundId = WebUtil.getParameterInteger(request, "fundId");

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
			java.io.Serializable pkey = fundId;
			Fund object = fundService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.fund", null, "fail", Locale.getDefault()));
			}
			else {
				fundService.approve(object, user);
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("fund", object);
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
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHFUND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHFUND access");
				return errorResult;
				
			}
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer clientId = WebUtil.getParameterInteger(request,"clientId");
			
			Integer policyId = WebUtil.getParameterInteger(request,"policyId");
			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			result.addObject("subnavigation",subnavigation);
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortFundCode = true, sortFundCat = true, sortClient = true, sortFundValue = true,
					sortStatus = true;
			boolean order = true;
			
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
			
			//CHECKING SORTING COLUMN
			if((!navigation.equalsIgnoreCase("gosearchbysort") && arah.isEmpty() && arah.equals("")) ||
					navigation.equals("gosearch") || navigation.equals("listclient")){
				sortcolumn = "";
				request.setAttribute("sortorder", "");
				request.setAttribute("columntosort", "");
			}
			
			if (navigation.equals("gosearch") || navigation.equals("golookup")|| navigation.equals("listclient")||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty() || navigation.equals("listpolicy"))) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) {					
					if (searchby.equalsIgnoreCase("fundCode")) {
						vLikeP.add("fundCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("fund_category")){
						vLikeP.add("fundType.fundCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("client")){
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
				}

			}

			if (user != null && user.getUser().getUserType() != null){
				if (user.getUser().getUserType().intValue() == User.CLIENT){
					if (clientId == null){
						vEqP.add("clientId.clientId");
						vEqQ.add(user.getUser().getClientId().getClientId());
					}
				}
				
								
			}
			if (navigation.equalsIgnoreCase("listclient")){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
				result.addObject("clientId",clientId);
			}
			
			if (policyId != null){
				vEqP.add("policyId.policyId");
				vEqQ.add(policyId);
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

			count = fundService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);


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
			"Fund.ClientId","Fund.FundCurrency"
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("fundcode")){
						sortByColumn = "fundCode";
						Boolean sortByOrderCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCode", ""));
						sortFundCode = !sortByOrderCode;
						order = sortFundCode;
					}else if(sortcolumn.equalsIgnoreCase("fundcategory")){
						sortByColumn = "fundType.fundCategoryName";
						Boolean sortByOrderCategory = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCategory", ""));
						sortFundCat = !sortByOrderCategory;
						order = sortFundCat;
					}else if(sortcolumn.equalsIgnoreCase("client")){
						sortByColumn = "clientId.clientName";
						Boolean sortByOrderClient = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderClient", ""));
						sortClient = !sortByOrderClient;
						order = sortClient;
					}else if(sortcolumn.equalsIgnoreCase("fundvalue")){
						sortByColumn = "fundValue";
						Boolean sortByOrderFundValue = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderFundValue", ""));
						sortFundValue = !sortByOrderFundValue;
						order = sortFundValue;
					}else{
						sortByColumn = "fundStatus.paymentStatusName";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("fundcode")){
						sortFundCode = order;
					}else if(sortcolumn.equalsIgnoreCase("fundcategory")){
						sortFundCat = order;
					}else if(sortcolumn.equalsIgnoreCase("client")){
						sortClient = order;
					}else if(sortcolumn.equalsIgnoreCase("fundvalue")){
						sortFundValue = order;
					}else{
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = fundService.search(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
						required, rowsetint, countSet.intValue());
			}else{
				collection = fundService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = fundService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
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
			result.addObject("Funds", collection);
			result.addObject("client", clientObject);
			result.addObject("listnavigation", listnavigation);

			if (minimumDate != null && !minimumDate.toString().equalsIgnoreCase("1970-01-01")){
				
				request.setAttribute("minimumDate", maximumDate.toString());
			}
			if (maximumDate != null && !maximumDate.toString().equalsIgnoreCase("1970-01-01")){
				
				request.setAttribute("maximumDate", maximumDate.toString());
			}
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("policyId", policyId);
			request.setAttribute("sortClient", sortClient);
			request.setAttribute("sortFundCat", sortFundCat);
			request.setAttribute("sortFundCode", sortFundCode);
			request.setAttribute("sortFundValue", sortFundValue);
			request.setAttribute("sortStatus", sortStatus);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchProviderFundPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "SEARCHFUND");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for SEARCHFUND access");
				return errorResult;
				
			}
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");
			Integer providerId = WebUtil.getParameterInteger(request, "providerId");
			
			

			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer clientId = WebUtil.getParameterInteger(request,"clientId");
			
			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			Date minimumDate = WebUtil.getParameterDate(request, "minimum_date");
			Date maximumDate = WebUtil.getParameterDate(request, "maximum_date");
			result.addObject("subnavigation",subnavigation);
			
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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("listprovider")) {

				if (searchby != null) {					
					if (searchby.equalsIgnoreCase("fundCode")) {
						vLikeP.add("fundCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("fund_category")){
						vLikeP.add("fundType.fundCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("client")){
						vLikeP.add("clientId.clientName");
						vLikeQ.add(searchtext);
					}
				}

			}

			
			vEqP.add("providerId.providerId");
			vEqQ.add(providerId);
			
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

			count = fundService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			"Fund.ClientId","Fund.FundCurrency"
			// foreign affairs end
			};
			collection = fundService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, rowsetint, countSet.intValue());

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
				collection = fundService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */
			
			Provider providerObject = null;
			
			if(providerId != null){
				try
				{
				java.io.Serializable providerkey = providerId;
				System.out.println("provider id : "+providerId);
				String[] providerRequired = {"Provider.StatusId", "Provider.ProviderCategoryId", "Provider.ProviderGroupId","Provider.ProviderSpecId", "Provider.ProviderCurrencyId"};
				providerObject = providerService.get(providerkey, providerRequired);
				
				}
				catch (Exception ex)
				{
					//System.out.println("claim object : "+claimObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			result.addObject("Funds", collection);
			result.addObject("provider", providerObject);
			result.addObject("listnavigation", listnavigation);

			if (minimumDate != null && !minimumDate.toString().equalsIgnoreCase("1970-01-01")){
				
				request.setAttribute("minimumDate", minimumDate.toString());
			}
			if (maximumDate != null && !maximumDate.toString().equalsIgnoreCase("1970-01-01")){
				
				request.setAttribute("maximumDate", maximumDate.toString());
			}
			result.addObject("providerId",providerId);
			request.setAttribute("providerId", providerId);
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
		String listnavigation = request.getParameter("listnavigation") == null ? "welcome"
				: request.getParameter("listnavigation");

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
		String breadcrumb ="";
		try {
			
			
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				String fundId = request.getParameter("fundId");
				result = detailPerformed(request, response, "detailFund");
				breadcrumb = "<a href=\"fund?navigation=detail&fundId="+fundId+"\" class=\"linkbreadcrumb\">Detail Fund</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("approve")){
				result = approvePerformed(request, response, "detailFund");
			}
			
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchFund");
				breadcrumb = "<a href=\"fund?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Fund</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupFund");
			}
			else if (navigation.equalsIgnoreCase("listclient") || subnavigation.equalsIgnoreCase("listclient")){
				result = searchPerformed(request, response, "listClientFund");
//				String clientId = request.getParameter("clientId");
				breadcrumb = "<a href=\"client?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Floating Fund";
			}
			else if (navigation.equalsIgnoreCase("listprovider") || navigation.equalsIgnoreCase("golistprovider")){
				result = searchProviderFundPerformed(request, response, "listProviderFund");
				String providerId = request.getParameter("providerId");
				if(listnavigation.equalsIgnoreCase("searchcapitation"))
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Fund";
				}
				else
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Fund";
				}
			}
			else if (navigation.equalsIgnoreCase("listpolicy") || navigation.equalsIgnoreCase("golistpolicy")){
				result = searchPerformed(request, response, "listPolicyFund");
				String policyId = request.getParameter("policyId");
				breadcrumb = "<a href=\"case?navigation=listpolicy&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Fund";
			}
			else if (navigation.equalsIgnoreCase("printrequest")){
				result = printRequestLetterPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("printapproval")){
				result = printApprovalLetterPerformed(request, response);
			}
			else {
				result = searchPerformed(request, response, "searchFund");
				breadcrumb = "<a href=\"fund?navigation=search&searchtext=&searchby=&index=\" class=\"linkbreadcrumb\">Search Fund</a>";
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
