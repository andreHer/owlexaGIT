package com.ametis.cms.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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
import com.ametis.cms.datamodel.ClientMedicine;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Item is a servlet controller for item Table. All you have to do is to convert
 * necessary data field to the named parameter
 */
public class ItemController implements Controller
{

	private ItemService itemService;

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

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ItemService getItemService() {
		return this.itemService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer itemId = WebUtil.getParameterInteger(request, "itemId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = itemId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEITEM");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEITEM access");
				return errorResult;

			}
			Item res = itemService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.item", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.item", null, "fail", Locale.getDefault()));

			}
			result = searchPerformed(request, response, "searchItem");
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
			Integer itemId = WebUtil.getParameterInteger(request, "itemId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String rowset = WebUtil.getParameterString(request, "rowset", "");
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
			java.io.Serializable pkey = itemId;
			Item object = itemService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.item", null, "fail", Locale.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("item", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("rowset", rowset);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert", alertProperties.getMessage(
					"system.error " + e.getMessage(), null, "fail", Locale
							.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}
	
	public ModelAndView updateBulkPerformed (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView result = null;

 	    try {
			
 	    	Integer caseCategoryId = WebUtil.getParameterInteger (request,"caseCategoryId");	    	
			
 	    	String navigation = WebUtil.getParameterString(request, "navigation", "");
						
			ActionUser user = securityService.getActionUser(request);
			
			if (navigation.equalsIgnoreCase("inquirybulk")){	
				
				String[] eqParam = {"itemCategoryId.caseCategoryId.caseCategoryId","deletedStatus"};
				Object[] eqValue = {caseCategoryId,Integer.valueOf(0)};
				
				int totalItem = itemService.getTotal(null,null,eqParam,eqValue);
				Collection<Item> itemList = itemService.search(null,null,eqParam,eqValue,0,totalItem);
				
				Vector<String> itemIdList = new Vector<String>();
				Vector<String> itemNameList = new Vector<String>();
				Vector<String> itemCodeList = new Vector<String>();
				Vector<String> itemEDCodeList = new Vector<String>();
				Vector<String> itemEDCNameList = new Vector<String>();
				Vector<String> itemCategoryNameList = new Vector<String>();
				
				
				for (Iterator iterator = itemList.iterator(); iterator
						.hasNext();) {
					
					Item item = (Item) iterator.next();
					
					itemIdList.add(item.getItemId().toString());
					itemNameList.add(item.getItemName());
					itemCodeList.add(item.getItemCode());
					itemEDCNameList.add(item.getItemEDCName());
					itemEDCodeList.add(item.getItemEDCCode());
					itemCategoryNameList.add(item.getItemCategoryId().getItemCategoryName());
					
				}
					
					
				
				result = new ModelAndView("updateBulkItem");
				
				result.addObject("itemIdVector", itemIdList);
				result.addObject("itemNameVector", itemNameList);
				result.addObject("itemCodeVector", itemCodeList);
				result.addObject("itemEDCNameVector", itemEDCNameList);
				result.addObject("itemEDCCodeVector", itemEDCodeList);
				result.addObject("itemCategoryNameVector", itemCategoryNameList);
				result.addObject("caseCategoryId", caseCategoryId);
				
			}
			else if (navigation.equalsIgnoreCase("saveupdatebulk")){
				
				String[] itemId = request.getParameterValues("itemId");
				String[] itemCode = request.getParameterValues("itemCode");
				String[] itemEdcCode = request.getParameterValues("itemEDCCode");
				String[] itemEdcName = request.getParameterValues("itemEDCName");

				Collection<Item> itemList = new Vector<Item>();
				
				for (int i = 0; i < itemId.length; i++){
					
				
					if (itemId[i] != null && !itemId[i].trim().equalsIgnoreCase("")){
						
						Item item = new Item();
						item.setItemId(Integer.valueOf(itemId[i]));
						item.setItemCode(itemCode[i]);
						item.setItemEDCCode(itemEdcCode[i]);
						item.setItemEDCName(itemEdcName[i]);						
						
						itemList.add(item);
						
					}
				}
				boolean res = itemService.updateItemBulk(itemList, user);
				
				String alert = "";
				
				if (res){
					alert = "Success Add Medicine";
				}
				else {
					alert = "Failed Add Medicine";
				}
				result = searchPerformed(request, response, "searchItem");
				result.addObject("alert", alert);
				
			}			
		}
		catch (Exception e){
		// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
		 e.printStackTrace();
		request.setAttribute ("alert", alertProperties.getMessage ("system.error "+e.getMessage(),null,"fail",Locale.getDefault()));
		result = new ModelAndView ("error");
		}	    return result;
	}



	public ModelAndView searchPerformed(HttpServletRequest request,
			HttpServletResponse response, String location) throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String url = WebUtil.getParameterString(request, "url", "");
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");
			String parentElem = WebUtil.getParameterString(request,
					"parentElem", "");
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortItemName = true, sortItemCode = true, sortEDCCode = true, sortEDCName = true,
					sortDescription = true;
			boolean order = true;

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
			
			if (navigation.equals("gosearch") || navigation.equals("golookup")|| 
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {
				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("itemName")) {
						vLikeP.add("itemName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("itemCode")) {
						vLikeP.add("itemCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("itemDescription")) {
						vLikeP.add("itemDescription");
						vLikeQ.add(searchtext);
					}

				}

			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			if (url.equalsIgnoreCase("case-form")) {
				vEqP.add("itemCategoryId.itemCategoryId");
				vEqQ.add(Integer.valueOf(ItemCategory.RUANGAN));
				result.addObject("url", url);
			}

			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = itemService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			"Item.ItemCategoryId",
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("itemname")){
						sortByColumn = "itemName";
						Boolean sortOrderItemName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderItemName", ""));
						sortItemName = !sortOrderItemName;
						order = sortItemName;
					}else if(sortcolumn.equalsIgnoreCase("itemcode")){
						sortByColumn = "itemCode";
						Boolean sortOrderItemCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderItemCode", ""));
						sortItemCode = !sortOrderItemCode;
						order = sortItemCode;
					}else if(sortcolumn.equalsIgnoreCase("itemedccode")){
						sortByColumn = "itemEDCCode";
						Boolean sortOrderEdcCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderEdcCode", ""));
						sortEDCCode = !sortOrderEdcCode;
						order = sortEDCCode;
					}else if(sortcolumn.equalsIgnoreCase("itemedcname")){
						sortByColumn = "itemEDCName";
						Boolean sortOrderEdcName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderEdcName", ""));
						sortEDCName = !sortOrderEdcName;
						order = sortEDCName;
					}else{
						sortByColumn = "itemDescription";
						Boolean sortOrderDescription = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderDescription", ""));
						sortDescription = !sortOrderDescription;
						order = sortDescription;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("itemname")){
						sortItemName = order;
					}else if(sortcolumn.equalsIgnoreCase("itemcode")){
						sortItemCode = order;
					}else if(sortcolumn.equalsIgnoreCase("itemedccode")){
						sortEDCCode = order;
					}else if(sortcolumn.equalsIgnoreCase("itemedcname")){
						sortEDCName = order;
					}else{
						sortDescription = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = itemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						order, sortByColumn, required, rowsetint, countSet.intValue());

			}else{
				collection = itemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = itemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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

			result.addObject("Items", collection);

			request.setAttribute("rowsetint", rowsetint);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("parentElem", parentElem);
			request.setAttribute("sortDescription", sortDescription);
			request.setAttribute("sortEDCCode", sortEDCCode);
			request.setAttribute("sortEDCName", sortEDCName);
			request.setAttribute("sortItemCode", sortItemCode);
			request.setAttribute("sortItemName", sortItemName);

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
			Collection<Item> items = null;
			
			items = itemService.searchItem(q);
			
			result.addObject("Items", items);


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
				String itemId = request.getParameter("itemId");
				result = detailPerformed(request, response, "detailItem");
				breadcrumb = "<a href=\"item\" class=\"linkbreadcrumb\">Search Item</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Detail Item";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchItem");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupItem");
			} 
			else if (navigation.equalsIgnoreCase("inquirybulk") || navigation.equalsIgnoreCase("saveupdatebulk")) {
				result = updateBulkPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("lookupjson") || navigation.equalsIgnoreCase("lookupdiagnosticjson")) {
				result = lookupJsonPerformed(request, response, "lookupItemJson");
			} else {
				result = searchPerformed(request, response, "searchItem");
				breadcrumb = "<a href=\"item\" class=\"linkbreadcrumb\">Search Item</a>";
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
