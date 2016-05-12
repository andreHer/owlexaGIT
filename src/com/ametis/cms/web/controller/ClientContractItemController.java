package com.ametis.cms.web.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import com.ametis.cms.util.*;
import com.ametis.cms.util.servlet.TableRenderingServlet;
import java.io.IOException;
import java.math.BigDecimal;

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
 * ClientContractItem is a servlet controller for client_contract_item Table.
 * All you have to do is to convert necessary data field to the named parameter
 */
public class ClientContractItemController implements Controller

// extends+

// extends-

{

	private ClientContractItemService clientContractItemService;
	private SecurityService securityService;
	private ItemService itemService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	
	
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
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

	public void setClientContractItemService(
			ClientContractItemService clientContractItemService) {
		this.clientContractItemService = clientContractItemService;
	}

	public ClientContractItemService getClientContractItemService() {
		return this.clientContractItemService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer clientContractItemId = WebUtil.getParameterInteger(request,
					"clientContractItemId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = clientContractItemId;

			ActionUser user = securityService.getActionUser(request);

			ClientContractItem res = clientContractItemService.delete(pkey,
					user);

			String alertMsg = "";
			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.clientcontractitem", null, "success",
						Locale.getDefault()));
				alertMsg = "<b>Success Delete Client Contract Item</b>";
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.clientcontractitem", null, "fail", Locale
								.getDefault()));
				alertMsg = "<b>Gagal Delete Client Contract Item</b>";
			}
			result = new ModelAndView(new RedirectView("clientcontract?navigation=detail&alert="+alertMsg+"&clientContractId="+res.getClientContractId().getClientContractId()));
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
			Integer clientContractItemId = WebUtil.getParameterInteger(request,
					"clientContractItemId");

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
			java.io.Serializable pkey = clientContractItemId;
			ClientContractItem object = clientContractItemService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.clientcontractitem", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("clientContractItem", object);
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
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
				
				}

			}

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

			count = clientContractItemService.getTotal(sLikeP, sLikeQ, sEqP,
					sEqQ);

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
					"ClientContractItem.ClientContractId",
					"ClientContractItem.ItemId",
			// foreign affairs end
			};
			collection = clientContractItemService.search(sLikeP, sLikeQ, sEqP,
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
				collection = clientContractItemService.search(sLikeP, sLikeQ,
						sEqP, sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ClientContractItems", collection);

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

	public ModelAndView addBulkPerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
			
 	    	Integer clientContractId = WebUtil.getParameterInteger (request,"clientContractId");
 	    	
			
 	    	String navigation = WebUtil.getParameterString(request, "navigation", "");
						
			ActionUser user = securityService.getActionUser(request);
			
			if (navigation.equalsIgnoreCase("addbulk")){	
				
				Collection<Item> itemList = itemService.getClientInvoiceItemList();
				
				Vector<String> itemIdList = new Vector<String>();
				Vector<String> caseCategoryList = new Vector<String>();
				Vector<String> claimTypeList = new Vector<String>();
				Vector<String> contractUnitList = new Vector<String>();
				Vector<String> descList = new Vector<String>();
				Vector<String> priceList = new Vector<String>();
				Vector<String> defaultPriceList = new Vector<String>();
				Vector<String> itemNameList = new Vector<String>();
				
				
				
				for (Iterator<Item> iterator = itemList.iterator(); iterator
						.hasNext();) {
					
					Item item = iterator.next();
				
					if (item != null){
									
						String defaultPrice = "";
						
						if (item.getItemDefaultValue() != null){
							BigDecimal bg =new BigDecimal(item.getItemDefaultValue());
							defaultPrice = bg.toPlainString();
						}
						itemNameList.add(item.getItemName());
						itemIdList.add(item.getItemId().toString());
						caseCategoryList.add("");
						claimTypeList.add("");
						contractUnitList.add("");
						descList.add("");
						priceList.add("");
						defaultPriceList.add(defaultPrice);
					}
					
				}
				result = new ModelAndView("addBulkItem");
				result.addObject("itemNameList", itemNameList);
				result.addObject("itemId", itemIdList);
				result.addObject("caseCategoryId", caseCategoryList);
				result.addObject("claimType", claimTypeList);
				result.addObject("contractUnit", contractUnitList);
				result.addObject("itemPrice", priceList);
				result.addObject("defaultPrice", defaultPriceList);
				result.addObject("description", descList);
				result.addObject("totalItem", itemList.size());
				
			}
			else if (navigation.equalsIgnoreCase("savebulk")){
				
				String[] itemId = request.getParameterValues("itemId");
				String[] caseCategoryId = request.getParameterValues("caseCategoryId");
				String[] claimType = request.getParameterValues("claimType");
				String[] contractUnit = request.getParameterValues("contractUnit");
				String[] priceList = request.getParameterValues("itemPrice");
				String[] descList = request.getParameterValues("description");

				Collection<ClientContractItem> itemList = new Vector<ClientContractItem>();
				
				for (int i = 0; i < itemId.length; i++){
									
					if (itemId[i] != null && !itemId[i].trim().equalsIgnoreCase("")){
						ClientContractItem citem = new ClientContractItem();
						Item item = itemService.get(Integer.valueOf(itemId[i]));
						
						citem.setItemId(item);
						
						if (priceList[i] != null && !priceList[i].trim().equalsIgnoreCase("")){
							citem.setItemPrice(Double.valueOf(priceList[i]));
						}
						else {
							citem.setItemPrice(-1.0);
						}
						if (caseCategoryId[i] != null && !caseCategoryId[i].trim().equalsIgnoreCase("")){
							CaseCategory cc = new CaseCategory();
							cc.setCaseCategoryId(Integer.valueOf(caseCategoryId[i]));
							citem.setCaseCategoryId(cc);
						}
						if (claimType[i] != null && !claimType[i].trim().equalsIgnoreCase("")){
							citem.setClaimType(Integer.valueOf(claimType[i]));
						}
						if (contractUnit[i] != null && !contractUnit[i].trim().equalsIgnoreCase("")){
							citem.setContractUnitType(Integer.valueOf(contractUnit[i]));
						}
						
						citem.setItemPriceReference(item.getItemDefaultValue());
						
						citem.setDescription(descList[i]);
						
						itemList.add(citem);
						
					}
				}
				boolean res = clientContractItemService.createContractItem(clientContractId, itemList, user);
				
				String alert = "";
				
				if (res){
					alert = "Success Add Contract Detail";
				}
				else {
					alert = "Failed Add Contract Detail";
				}
				result = new ModelAndView(new RedirectView("clientcontract?navigation=detail&clientContractId="+clientContractId+"&alert="+alert));
			}
			request.setAttribute("clientContractId", clientContractId);
		
			
		}
		catch (Exception e){
		// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
		 e.printStackTrace();
		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));
		result = new ModelAndView ("error");
		}	    return result;
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
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailClientContractItem");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchClientContractItem");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupClientContractItem");
			}
			else if (navigation.equalsIgnoreCase("addbulk") || navigation.equalsIgnoreCase("savebulk")){
				
				
				result = addBulkPerformed(request, response);
			}
			else {
				result = searchPerformed(request, response,
						"searchClientContractItem");
			}
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
