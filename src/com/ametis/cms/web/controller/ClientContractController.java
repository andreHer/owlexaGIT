package com.ametis.cms.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import com.ametis.cms.util.*;
import com.ametis.cms.util.servlet.TableRenderingServlet;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.context.support.ResourceBundleMessageSource; /*
 import org.acegisecurity.Authentication;
 import org.acegisecurity.context.SecurityContextHolder;
 import org.acegisecurity.userdetails.UserDetails;
 */
import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.*;
import java.util.*;

// imports+

// imports-

/**
 * ClientContract is a servlet controller for client_contract Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class ClientContractController implements Controller

// extends+

// extends-
{

	private ClientContractService clientContractService;
	private ClientContractItemService clientContractItemService;
	private SecurityService securityService;

	private ClientService clientService;
	private UserService actionuserService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	
	
	public ClientContractItemService getClientContractItemService() {
		return clientContractItemService;
	}

	public void setClientContractItemService(
			ClientContractItemService clientContractItemService) {
		this.clientContractItemService = clientContractItemService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public UserService getUserService() {
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

	public void setClientContractService(
			ClientContractService clientContractService) {
		this.clientContractService = clientContractService;
	}

	public ClientContractService getClientContractService() {
		return this.clientContractService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer clientContractId = WebUtil.getParameterInteger(request,
					"clientContractId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = clientContractId;

			ActionUser user = securityService.getActionUser(request);

			ClientContract res = clientContractService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.clientcontract", null, "success",
						Locale.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.clientcontract", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchClientContract");
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
			Integer clientContractId = WebUtil.getParameterInteger(request,
					"clientContractId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby","");


			ActionUser user = securityService.getActionUser(request);
			boolean isResult = clientContractService.activate(clientContractId, user);

			if (isResult) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.activate.clientcontract", null, "fail", Locale
								.getDefault()));
			}

			result = new ModelAndView(new RedirectView( "clientcontract?navigation=detail&clientContractId="+clientContractId));

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
	public ModelAndView detailPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;

		try {
			Integer clientContractId = WebUtil.getParameterInteger(request,
					"clientContractId");

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
			java.io.Serializable pkey = clientContractId;
			ClientContract object = clientContractService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.clientcontract", null, "fail", Locale
								.getDefault()));
			}
			
			
			if (object != null){
				String[] eqParam = {"clientContractId.clientContractId","deletedStatus"};
				Object[] eqValue = {object.getClientContractId(),0};
				
				int total = clientContractItemService.getTotal(null,null,eqParam,eqValue);
				
				Collection<ClientContractItem> itemList = clientContractItemService.search(null,null,eqParam,eqValue,0,total);
				
				result.addObject("ClientContractItems", itemList);
			}

			result.addObject("clientContract", object);
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
			
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("listclient")) {

				if (searchby != null) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("clientContractNumber")) {
						vLikeP.add("clientContractNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					
				}

			}
			if (navigation.equalsIgnoreCase("listclient")){
				result = new ModelAndView("listClientContract");
			}

			vEqP.add("deletedStatus");
			vEqQ.add(new Integer(0));
			
			Client clientObject =null;

			if (clientId != null){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
				
				try
				{
				java.io.Serializable clientpkey = clientId;
				System.out.println("member client id : "+clientId);
				String[] clientRequired = {"Client.FundCurrency","Client.PaymentCurrency","Client.StatusId"};
				clientObject = clientService.get(clientpkey, clientRequired);
				
				}
				catch (Exception ex)
				{
					System.out.println("member group object : "+clientObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}
			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = clientContractService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"ClientContract.ContractTypeId", "ClientContract.ClientId",
			// foreign affairs end
			};
			collection = clientContractService.search(sLikeP, sLikeQ, sEqP,
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
				collection = clientContractService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ClientContracts", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			result.addObject("client", clientObject);

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
        Integer clientId = WebUtil.getParameterInteger(request, "clientId");

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
						"detailClientContract");
				breadcrumb = "<a href=\"client?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Contract History</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Detail Client Contract History";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("activate")) {
				result = activatePerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchClientContract");
			}else if (navigation.equalsIgnoreCase("listclient")) {
				result = searchPerformed(request, response,
						"listClientContract");
				breadcrumb = "<a href=\"client?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Contract History";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupClientContract");
			} else {
				result = searchPerformed(request, response,
						"searchClientContract");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("clientId", clientId);

		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-
}
