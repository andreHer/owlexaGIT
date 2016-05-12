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
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderMedicine;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ProviderItemService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * ProviderItem is a servlet controller for provider_item Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class ProviderItemController implements Controller

// extends+

// extends-

{

	private ProviderItemService providerItemService;

	private UserService userService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	
	SecurityService securityService;
private ActivityLogService logService;

	private ProviderService providerService;

	
	
	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
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

	public void setProviderItemService(ProviderItemService providerItemService) {
		this.providerItemService = providerItemService;
	}

	public ProviderItemService getProviderItemService() {
		return this.providerItemService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer providerItemId = WebUtil.getParameterInteger(request,
					"providerItemId");

			
			java.io.Serializable pkey = providerItemId;

			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEPROVIDERITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEPROVIDERITEM access");
				return errorResult;
				
			}
			ProviderItem res = providerItemService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.provideritem", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.provideritem", null, "fail", Locale
								.getDefault()));

			}
			result = searchPerformed(request, response, "searchProviderItem");
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
	public ModelAndView saveItemPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = new ModelAndView("addProviderItem");

		try {
			Integer providerId = WebUtil.getParameterInteger(request,"providerId");
			String[] itemIds = request.getParameterValues("item_id");
			String[] itemValue = request.getParameterValues("item_value");
			
			
			

			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEPROVIDERITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEPROVIDERITEM access");
				return errorResult;
				
			}
			
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
	public ModelAndView addItemPerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = new ModelAndView("addProviderItem");

		try {
			Integer providerId = WebUtil.getParameterInteger(request,"providerId");
			
			String navigation = WebUtil.getParameterString(request, "navigation", "");
			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEPROVIDERITEM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEPROVIDERITEM access");
				return errorResult;
				
			}
			
			if (navigation.equalsIgnoreCase("tambahitem")){	
				
				Vector<String> itemSVIPValueList = new Vector<String>();
				Vector<String> itemVIPValueList = new Vector<String>();
				Vector<String> itemC1ValueList = new Vector<String>();
				Vector<String> itemC2ValueList = new Vector<String>();
				Vector<String> itemC3ValueList = new Vector<String>();
				Vector<String> itemRJValueList = new Vector<String>();
				
				Vector<String> nameList = new Vector<String>();
				Vector<String> itemIdList = new Vector<String>();
				Vector<String> codeList = new Vector<String>();
				
				for(int i = 0; i < 10; i++){
					
					itemSVIPValueList.add("");
					itemVIPValueList.add("");
					itemC1ValueList.add("");
					itemC2ValueList.add("");
					itemC3ValueList.add("");
					itemRJValueList.add("");
					nameList.add("");
					itemIdList.add("");
					
					codeList.add("");	
				}
				result = new ModelAndView("addProviderItem");
				
				result.addObject("valueSVIPVector", itemSVIPValueList);
				result.addObject("valueVIPVector", itemVIPValueList);
				result.addObject("valueC1Vector", itemC1ValueList);
				result.addObject("valueC2Vector", itemC2ValueList);
				result.addObject("valueC3Vector", itemC3ValueList);
				result.addObject("valueRJVector", itemRJValueList);
				result.addObject("nameVector", nameList);
				result.addObject("codeVector", codeList);
				result.addObject("itemIdVector", itemIdList);				
			}
			else if (navigation.equalsIgnoreCase("savebulk")){
				
				String[] itemId = request.getParameterValues("itemId");
				String[] itemCode = request.getParameterValues("itemCode");
				String[] itemName = request.getParameterValues("itemName");
				String[] svipChargeList = request.getParameterValues("svipValue");
				String[] vipChargeList = request.getParameterValues("vipValue");
				String[] c1ChargeList = request.getParameterValues("kelas1Value");
				String[] c2ChargeList = request.getParameterValues("kelas2Value");
				String[] c3ChargeList = request.getParameterValues("kelas3Value");
				String[] rjChargeList = request.getParameterValues("rjValue");
				

				Collection<ProviderItem> itemList = new Vector<ProviderItem>();
				
				for (int i = 0; i < itemId.length; i++){
					
				
					if (itemId[i] != null && !itemId[i].trim().equalsIgnoreCase("")){
						ProviderItem provItem = new ProviderItem();
						Item item = new Item();
						item.setItemId(Integer.valueOf(itemId[i]));
						
						
						provItem.setItemId(item);
						
						if (svipChargeList[i] != null && !svipChargeList[i].trim().equalsIgnoreCase("")){
							provItem.setSvip(Double.valueOf(svipChargeList[i]));
						}
						if (vipChargeList[i] != null && !vipChargeList[i].trim().equalsIgnoreCase("")){
							provItem.setVip(Double.valueOf(vipChargeList[i]));
						}
						if (c1ChargeList[i] != null && !c1ChargeList[i].trim().equalsIgnoreCase("")){
							provItem.setKelas1(Double.valueOf(c1ChargeList[i]));
						}
						if (c2ChargeList[i] != null && !c2ChargeList[i].trim().equalsIgnoreCase("")){
							provItem.setKelas2(Double.valueOf(c2ChargeList[i]));
						}
						if (c3ChargeList[i] != null && !c3ChargeList[i].trim().equalsIgnoreCase("")){
							provItem.setKelas3(Double.valueOf(c3ChargeList[i]));
						}
						if (rjChargeList[i] != null && !rjChargeList[i].trim().equalsIgnoreCase("")){
							provItem.setRawatJalan(Double.valueOf(rjChargeList[i]));
						}
						
						
						itemList.add(provItem);
						
					}
				}
				boolean res = providerItemService.createProviderItems(providerId, itemList, user);
				
				String alert = "";
				
				if (res){
					alert = "Success Add Item";
				}
				else {
					alert = "Failed Add Item";
				}
				result = new ModelAndView(new RedirectView("provideritem?navigation=list&providerId="+providerId+"&alert="+alert));
			}
			result.addObject("providerId", providerId);
			request.setAttribute("providerId", providerId);
		

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
			Integer providerItemId = WebUtil.getParameterInteger(request,
					"providerItemId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String listnavigation = WebUtil.getParameterString(request, "listnavigation",
					"");
			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

			result = new ModelAndView(location);
			java.io.Serializable pkey = providerItemId;
			ProviderItem object = providerItemService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.provideritem", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("providerItem", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("listnavigation",listnavigation);

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
			
			String listnavigation = WebUtil.getParameterString(request,
					"listnavigation", "");
			
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer providerId = WebUtil.getParameterInteger(request,
					"providerId");

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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equals("list")) {

				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */
					
					if(searchby.equalsIgnoreCase("itemName")){
						vLikeP.add("itemId.itemName");
						vLikeQ.add(searchtext);
					}
					if(searchby.equalsIgnoreCase("itemCode")){
						vLikeP.add("itemId.itemCode");
						vLikeQ.add(searchtext);
					}
				}

			}

			
				vEqP.add("providerId.providerId");
				vEqQ.add(providerId);
				result.addObject("providerId",providerId);
			
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

			count = providerItemService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			"ProviderItem.ProviderId",
			// foreign affairs end
			};
			collection = providerItemService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = providerItemService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}
			
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

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ProviderItems", collection);
			result.addObject("provider", providerObject);
			result.addObject("listnavigation",listnavigation);

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
		
		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);


		String providerId = request.getParameter("providerId");
		String breadcrumb = "<a href=\"provideritem?navigation=list&providerId="+providerId+"\" class=\"linkbreadcrumb\">List Provider Service</a>";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailProviderItem");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} 
			else if (navigation.equalsIgnoreCase("tambahitem") || navigation.equalsIgnoreCase("savebulk")) {
				result = addItemPerformed(request, response);
				
			}
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"searchProviderItem");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupProviderItem");
			}else if (navigation.equalsIgnoreCase("list")){
				result = searchPerformed(request, response,
						"searchProviderItem");
				if(listnavigation.equalsIgnoreCase("searchcapitation"))
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"&listnavigation="+listnavigation+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Room & Board";
				}else
				{
					breadcrumb = "<a href=\"provider?navigation=detail&providerId="+providerId+"\" class=\"linkbreadcrumb\">Detail Provider</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Provider Room & Board";
				}
			} else {
				result = searchPerformed(request, response,
						"searchProviderItem");
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
