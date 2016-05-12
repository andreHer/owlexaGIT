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
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.Converter;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Client is a servlet controller for client Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class ClientController implements Controller

// extends+

// extends-

{

	private ClientService clientService;

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

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ClientService getClientService() {
		return this.clientService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			java.io.Serializable pkey = clientId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECLIENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECLIENT access");
				return errorResult;
				
			}
			Client res = clientService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.client", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.delete.client", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchClient");
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
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			java.io.Serializable pkey = clientId;

			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETECLIENT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETECLIENT access");
				return errorResult;
				
			}
			Client res = clientService.activate(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.client", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties
						.getMessage("fail.delete.client", null, "fail", Locale
								.getDefault()));

			}
			result = detailPerformed(request, response, "detailClient");
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
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

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
			
			String[] required = {"Client.FundCurrency","Client.PaymentCurrency","Client.StatusId"};

			result = new ModelAndView(location);
			java.io.Serializable pkey = clientId;
			Client object = clientService.get(pkey,required);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.client", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("client", object);
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
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			
			String arah = WebUtil.getParameterString(request, "arah", "");
	        String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			Integer searchStatus = WebUtil.getParameterInteger(request, "status");	
			boolean sortClientName = true, sortClientCat = true, sortCity = true,
					sortProvince = true, sortCountry = true, sortStatus = true,
					sortClientNo = true, sortClientCode = true;

			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;
			
			boolean order = true;

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
			
			
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("lookupjson")|| navigation.equalsIgnoreCase("golistclient")||
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */
					
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("clientName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientCategory")){
						vLikeP.add("clientCategoryId.clientCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("address")) {
						vLikeP.add("address");
						vLikeQ.add(searchtext);
					}										
					if (searchby.equalsIgnoreCase("city")) {
						vLikeP.add("city");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("province")) {
						vLikeP.add("province");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("country")) {
						vLikeP.add("country");
						vLikeQ.add(searchtext);
					}
				}
				if (searchStatus != null && searchStatus.intValue() > 0){
					vEqP.add("statusId.statusId");
					vEqQ.add(searchStatus);					
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

			count = clientService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			//Start Pagination Kiri Kanan
			//if(navigation.equals("gosearch") && arah.isEmpty())
			//	checkOrder = false;
			
//			if(navigation.equals("search")){
//				checkOrder = true;
//			}else if(arah.isEmpty())
//				checkOrder = false;

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
			"Client.ClientCategoryId","Client.StatusId"
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("clientname")){
						sortByColumn = "clientName";
						Boolean sortByOrderName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderName", ""));
						sortClientName = !sortByOrderName;
						order = sortClientName;
					}else if(sortcolumn.equalsIgnoreCase("clientcategory")){
						sortByColumn = "clientCategoryId.clientCategoryName";
						Boolean sortByOrderCategory = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCategory", ""));
						sortClientCat = !sortByOrderCategory;
						order = sortClientCat;
					}else if(sortcolumn.equalsIgnoreCase("clientnumber")){
						sortByColumn = "clientNumber";
						Boolean sortByOrderNumber = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderNumber", ""));
						sortClientNo = !sortByOrderNumber;
						order = sortClientNo;
					}else if(sortcolumn.equalsIgnoreCase("clientcode")){
						sortByColumn = "clientCode";
						Boolean sortByOrderCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCode", ""));
						sortClientCode = !sortByOrderCode;
						order = sortClientCode;
					}else if(sortcolumn.equalsIgnoreCase("city")){
						sortByColumn = "city";
						Boolean sortByOrderCity = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCity", ""));
						sortCity = !sortByOrderCity;
						order = sortCity;
					}else if(sortcolumn.equalsIgnoreCase("province")){
						sortByColumn = "province";
						Boolean sortByOrderProvince = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProvince", ""));
						sortProvince = !sortByOrderProvince;
						order = sortProvince;
					}else if(sortcolumn.equalsIgnoreCase("country")){
						sortByColumn = "country";
						Boolean sortByOrderCountry = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderCountry", ""));
						sortCountry = !sortByOrderCountry;
						order = sortCountry;
					}else{
						sortByColumn = "statusId.status";
						Boolean sortByOrderStatus = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderStatus", ""));
						sortStatus = !sortByOrderStatus;
						order = sortStatus;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("clientname")){
						sortClientName = order;
					}else if(sortcolumn.equalsIgnoreCase("clientcategory")){
						sortClientCat = order;
					}else if(sortcolumn.equalsIgnoreCase("clientnumber")){
						sortClientNo = order;
					}else if(sortcolumn.equalsIgnoreCase("clientcode")){
						sortClientCode = order;
					}else if(sortcolumn.equalsIgnoreCase("city")){
						sortCity = order;
					}else if(sortcolumn.equalsIgnoreCase("province")){
						sortProvince = order;
					}else if(sortcolumn.equalsIgnoreCase("country")){
						sortCountry = order;
					}else{
						sortStatus = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
					required, sortByColumn, order, rowsetint, countSet.intValue());
			}else{
				collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						required, rowsetint, countSet.intValue());
			}
			
//			if(navigation.equalsIgnoreCase("search")|| (navigation.isEmpty() && checkOrder == true)|| 
//					((navigation.equals("gosearch") || navigation.equals("")) && !arah.isEmpty() && 
//							((searchTextTmp!=null && searchByTmp!=null) || (searchStatusTmp!=null&&searchStatusTmp.intValue()>0)))){ //Edit Masuk Sort By
//				String sortByColumn = new String();
//				if((arah.isEmpty()||arah.equals("")) && checkOrder==true){
//					if(sortby.equalsIgnoreCase("client_name") || sortby.equalsIgnoreCase("client_namedsc")){
//						sortByColumn = "clientName";
//						if(sortby.equals("client_name")){
//							sortClientName = false;
//						}
//						else{
//							sortClientName = true;
//						}
//						order = sortClientName;
//					}else if(sortby.equalsIgnoreCase("client_category") || sortby.equalsIgnoreCase("client_categorydsc")){
//						sortByColumn = "clientCategoryId";
//						if(sortby.equals("client_category"))
//							sortClientCat = false;
//						else
//							sortClientCat = true;
//						order = sortClientCat;
//					}else if(sortby.equalsIgnoreCase("city") || sortby.equalsIgnoreCase("citydsc")){
//						sortByColumn = "city";
//						if(sortby.equals("city"))
//							sortCity = false;
//						else
//							sortCity = true;
//						order = sortCity;
//					}else if(sortby.equalsIgnoreCase("province") || sortby.equalsIgnoreCase("provincedsc")){
//						sortByColumn = "province";
//						if(sortby.equals("province"))
//							sortProvince = false;
//						else
//							sortProvince = true;
//						order = sortProvince;
//					}else if(sortby.equalsIgnoreCase("country") || sortby.equalsIgnoreCase("countrydsc")){
//						sortByColumn = "country";
//						if(sortby.equals("country"))
//							sortCountry = false;
//						else
//							sortCountry = true;
//						order = sortCountry;
//					}else if(sortby.equalsIgnoreCase("status") || sortby.equalsIgnoreCase("statusdsc")){
//						sortByColumn = "statusId";
//						if(sortby.equals("status"))
//							sortStatus = false;
//						else
//							sortStatus = true;
//						order = sortStatus;
//					}else if(sortby.equalsIgnoreCase("client_number") || sortby.equalsIgnoreCase("client_numberdsc")){
//						sortByColumn = "clientNumber";
//						if(sortby.equals("client_number"))
//							sortClientNo = false;
//						else
//							sortClientNo = true;
//						order = sortClientNo;
//					}else if(sortby.equalsIgnoreCase("client_code") || sortby.equalsIgnoreCase("client_codedsc")){
//						sortByColumn = "clientCode";
//						if(sortby.equals("client_code"))
//							sortClientCode = false;
//						else
//							sortClientCode = true;
//						order = sortClientCode;
//					}else{
//						//sortByColumn = "";
//						sortByColumn = "statusId";
//						if(sortby.equalsIgnoreCase("status"))
//							sortStatus = false;
//						else
//							sortStatus = true;
//						order = sortStatus;
//					}
//					
////					collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
////							required, sortByColumn, order, rowsetint, countSet.intValue());
////					chkOrder = order;
////					checkOrderColumn = sortByColumn;
//					
//					//Add by  aju on 20150214, for handling null on sortByColumn (i.e click on return to list link)
//					if(!sortByColumn.isEmpty()){
//						collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//								required, sortByColumn, order, rowsetint, countSet.intValue());
//						chkOrder = order;
//						checkOrderColumn = sortByColumn;
//					}
//					else{
//						//get the default collection
//						collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//								required, rowsetint, countSet.intValue());
//						searchByTmp = null;
//						searchTextTmp = null;
//						searchStatusTmp = null;
//						checkOrder = false;
//					}
//					
//				}else if((!arah.isEmpty()||!arah.equals("")) && checkOrder == true && !checkOrderColumn.isEmpty()){
//					collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//							required, checkOrderColumn, chkOrder, rowsetint, countSet.intValue());
//				}else{
//					searchByTmp = null;
//					searchTextTmp = null;
//					searchStatusTmp = null;
//					checkOrder = false;
//					collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//							required, rowsetint, countSet.intValue());
//				}
//			}else{
//				//get the default collection
//				collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
//						required, rowsetint, countSet.intValue());
//			}
			

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
				collection = clientService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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

			result.addObject("Clients", collection);

			request.setAttribute("status", searchStatus);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortClientName", sortClientName);
			request.setAttribute("sortClientCat", sortClientCat);
			request.setAttribute("sortCity", sortCity);
			request.setAttribute("sortProvince", sortProvince);
			request.setAttribute("sortCountry", sortCountry);
			request.setAttribute("sortStatus", sortStatus);
			request.setAttribute("sortClientNo", sortClientNo);
			request.setAttribute("sortClientCode", sortClientCode);

		} catch (Exception e) {
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
			result = new ModelAndView(location);

			String q = WebUtil.getParameterString(request, "q", "");
			
			Collection<Client> clients = clientService.searchClient(q);
			
			result.addObject("Clients", clients);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView jsonTotalClientFundPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);
			
			int totalClient = clientService.getTotalEmptyFundClient();
			result.addObject("result", totalClient);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView searchClientFundPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);
			
			Collection<Client> res = clientService.searchRequiredClientFund();
			result.addObject("Clients", res);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	public ModelAndView jsonClientFundPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			
			ActionUser actionUser = securityService.getActionUser(request);
			result = new ModelAndView(location);
			
			if (actionUser != null && actionUser.getUser().getUserType().intValue() == User.CLIENT){
				
				
				Client client = clientService.get(actionUser.getUser().getClientId().getClientId());
				String floatingFund = Converter.getMoney(client.getClientFundValue());
				
				result.addObject("result", floatingFund);
			}
			
			


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
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response, "detailClient");
				String clientId = request.getParameter("clientId");
				breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("activate")) {
				result = activatePerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchClient");
				breadcrumb = "<a href=\"client\" class=\"linkbreadcrumb\">Search Client</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupClient");
				
			}
			else if (navigation.equalsIgnoreCase("lookupjson")){
				System.out.println("lookup json");
				System.out.println("q : " + request.getParameter("q"));
				result = lookupJsonPerformed(request, response, "lookupClientJson");
			}
			else if (navigation.equalsIgnoreCase("jsonclientfund")){
				
				result = jsonClientFundPerformed(request, response, "jsonTotalPendingClient");
			}	
			else if (navigation.equalsIgnoreCase("jsontotalclientfund")){
				
				result = jsonTotalClientFundPerformed(request, response, "jsonTotalPendingClient");
			}	
			else if (navigation.equalsIgnoreCase("searchrequiredfund")){
				
				result = searchClientFundPerformed(request, response, "searchRequiredClientFund");
			}
			else if (navigation.equalsIgnoreCase("trackclientfund")){
				result = searchPerformed (request,response,"searchClientFund");
			}
			else {
				result = searchPerformed(request, response, "searchClient");
				String clientId = request.getParameter("clientId");
				breadcrumb = "<a href=\"client\" class=\"linkbreadcrumb\">Search Client</a>";
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
