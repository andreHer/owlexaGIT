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
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * ItemCategory is a servlet controller for item_category Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class ItemCategoryController implements Controller

// extends+

// extends-

{

	private ItemCategoryService itemCategoryService;
	private CaseCategoryService caseCategoryService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	private SecurityService securityService;
	private ActivityLogService logService;

	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
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

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public ItemCategoryService getItemCategoryService() {
		return this.itemCategoryService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer itemCategoryId = WebUtil.getParameterInteger(request,
					"itemCategoryId");
			java.io.Serializable pkey = itemCategoryId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"DELETEITEMCATEGORY");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage",
						"You Are Not Authorized for DELETEITEMCATEGORY access");
				return errorResult;

			}
			ItemCategory res = itemCategoryService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.itemcategory", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.itemcategory", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchItemCategory");
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
			Integer itemCategoryId = WebUtil.getParameterInteger(request,
					"itemCategoryId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			Integer rowset = WebUtil.getParameterInteger(request, "rowset");

			result = new ModelAndView(location);
			java.io.Serializable pkey = itemCategoryId;
			ItemCategory object = itemCategoryService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.itemcategory", null, "fail", Locale
								.getDefault()));
			}

			result.addObject("itemCategory", object);
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
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortCategoryName = true, sortCategoryCode = true, sortEDCCode = true,
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

					if (searchby.equalsIgnoreCase("description")) {
						vLikeP.add("description");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("itemCategoryName")) {
						vLikeP.add("itemCategoryName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("itemCategoryCode")) {
						vLikeP.add("itemCategoryCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("caseCategoryName")) {
						vLikeP.add("caseCategoryId.caseCategoryName");
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

			count = itemCategoryService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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

			String required[] = new String[] {"ItemCategory.CaseCategoryId"
			// foreign affairs
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("itemcategoryname")){
						sortByColumn = "itemCategoryName";
						Boolean sortOrderCategoryName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderCategoryName", ""));
						sortCategoryName = !sortOrderCategoryName;
						order = sortCategoryName;
					}else if(sortcolumn.equalsIgnoreCase("itemcategorycode")){
						sortByColumn = "itemCategoryCode";
						Boolean sortOrderCategoryCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderCategoryCode", ""));
						sortCategoryCode = !sortOrderCategoryCode;
						order = sortCategoryCode;
					}else if(sortcolumn.equalsIgnoreCase("edccode")){
						sortByColumn = "itemCategoryEDCCode";
						Boolean sortOrderEdcCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderEdcCode", ""));
						sortEDCCode = !sortOrderEdcCode;
						order = sortEDCCode;
					}else{
						sortByColumn = "description";
						Boolean sortOrderDescription = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortOrderDescription", ""));
						sortDescription = !sortOrderDescription;
						order = sortDescription;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("itemcategoryname")){
						sortCategoryName = order;
					}else if(sortcolumn.equalsIgnoreCase("itemcategorycode")){
						sortCategoryCode = order;
					}else if(sortcolumn.equalsIgnoreCase("edccode")){
						sortEDCCode = order;
					}else{
						sortDescription = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = itemCategoryService.search(sLikeP, sLikeQ, sEqP, sEqQ,
						order, sortByColumn, required, rowsetint, countSet.intValue());

			}else{
				collection = itemCategoryService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = itemCategoryService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("sortcolumn", sortcolumn);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ItemCategorys", collection);
			request.setAttribute("rowset", rowsetint);
			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("sortCategoryCode", sortCategoryCode);
			request.setAttribute("sortCategoryName", sortCategoryName);
			request.setAttribute("sortEDCCode", sortEDCCode);
			request.setAttribute("sortDescription", sortDescription);

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

			String q = request.getParameter("q");
			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");
			
			String[] eqParam = {"deletedStatus","caseCategoryId.caseCategoryId"};
			Object[] eqValue = {0,caseCategoryId};
			String[] likeParam = {"itemCategoryName"};
			Object[] likeValue = {q};
			
			
			Collection<ItemCategory> itemList = itemCategoryService.search(likeParam,likeValue,eqParam,eqValue,0,15);
				

			result.addObject("itemCategoryList", itemList);

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
				
				String[] eqParam = {"caseCategoryId.caseCategoryId","deletedStatus"};
				Object[] eqValue = {caseCategoryId,Integer.valueOf(0)};
				
				int totalItem = itemCategoryService.getTotal(null,null,eqParam,eqValue);
				Collection<ItemCategory> itemList = itemCategoryService.search(null,null,eqParam,eqValue,0,totalItem);
				
				Vector<String> itemIdList = new Vector<String>();
				Vector<String> itemNameList = new Vector<String>();
				Vector<String> itemCodeList = new Vector<String>();
				Vector<String> itemEDCodeList = new Vector<String>();
				Vector<String> itemEDCNameList = new Vector<String>();
				Vector<String> itemCategoryNameList = new Vector<String>();
				
				
				for (Iterator iterator = itemList.iterator(); iterator
						.hasNext();) {
					
					ItemCategory item = (ItemCategory) iterator.next();
					
					itemIdList.add(item.getItemCategoryId().toString());
					itemNameList.add(item.getItemCategoryName());
					itemCodeList.add(item.getItemCategoryCode());
					itemEDCNameList.add(item.getItemCategoryEDCName());
					itemEDCodeList.add(item.getItemCategoryEDCCode());
					
					
				}				
					
				
				result = new ModelAndView("updateBulkItem");
				
				result.addObject("itemIdVector", itemIdList);
				result.addObject("itemNameVector", itemNameList);
				result.addObject("itemCodeVector", itemCodeList);
				result.addObject("itemEDCNameVector", itemEDCNameList);
				result.addObject("itemEDCCodeVector", itemEDCodeList);
				
				
			}
			else if (navigation.equalsIgnoreCase("saveupdatebulk")){
				
				String[] itemId = request.getParameterValues("itemId");
				String[] itemName = request.getParameterValues("itemName");
				String[] itemCode = request.getParameterValues("itemCode");
				String[] itemEdcCode = request.getParameterValues("itemEDCode");
				String[] itemEdcName = request.getParameterValues("itemEDCName");

				Collection<ItemCategory> itemList = new Vector<ItemCategory>();
				
				for (int i = 0; i < itemId.length; i++){
					
				
					if (itemId[i] != null && !itemId[i].trim().equalsIgnoreCase("")){
						
						ItemCategory item = new ItemCategory();
						item.setItemCategoryId(Integer.valueOf(itemId[i]));
						item.setItemCategoryCode(itemCode[i]);
						item.setItemCategoryEDCCode(itemEdcCode[i]);
						item.setItemCategoryEDCName(itemEdcName[i]);						
						
						itemList.add(item);
						
					}
				}
				boolean res = itemCategoryService.updateItemBulk(itemList, user);
				
				String alert = "";
				
				if (res){
					alert = "Success Add Medicine";
				}
				else {
					alert = "Failed Add Medicine";
				}
				result = searchPerformed(request, response, "searchItemCategory");
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
				String itemCategoryId = request.getParameter("itemCategoryId");
				result = detailPerformed(request, response,
						"detailItemCategory");
				breadcrumb = "<a href=\"itemcategory\">Search Item Category</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Detail Item Category";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchItemCategory");
				breadcrumb = "<a href=\"itemcategory?navigation=search&searchtext=&searchby=&rowset=0&index=1\">Search Item Category</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupItemCategory");
			} 
			else if (navigation.equalsIgnoreCase("inquirybulk") || navigation.equalsIgnoreCase("saveupdatebulk")) {
				result = updateBulkPerformed(request, response);
			}
			else if (navigation.equalsIgnoreCase("lookupjson")) {
				result = lookupJsonPerformed(request, response,
						"lookupItemCategoryJson");
			} else {
				result = searchPerformed(request, response,
						"searchItemCategory");
				breadcrumb = "<a href=\"itemcategory?navigation=search&searchtext=&searchby=&rowset=0&index=1\">Search Item Category</a>";
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
