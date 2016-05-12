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
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductLayerLimit;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductLayerLimitService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * Product is a servlet controller for product Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class ProductController implements Controller

// extends+

// extends-

{

	private ProductService productService;

	private UserService userService;
	
	private ClientService clientService;
	
	private PolicyService  policyService;

	private ResourceBundleMessageSource alertProperties;

	private Integer countSet;

	private Integer maxPercountSet;
	
	private CaseCategoryService caseCategoryService;

	SecurityService securityService;
	private ActivityLogService logService;
	private ProductLayerLimitService productLayerLimitService;
	
	

	public ProductLayerLimitService getProductLayerLimitService() {
		return productLayerLimitService;
	}

	public void setProductLayerLimitService(
			ProductLayerLimitService productLayerLimitService) {
		this.productLayerLimitService = productLayerLimitService;
	}

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

	
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
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

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductService getProductService() {
		return this.productService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer productId = WebUtil.getParameterInteger(request,
					"productId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = productId;

			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEPRODUCT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEPRODUCT access");
				return errorResult;
				
			}
			Product res = productService.delete(pkey, user);

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.product", null, "success", Locale
								.getDefault()));
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.product", null, "fail", Locale
								.getDefault()));

			}
			request.setAttribute("rowset",Integer.valueOf(0));
			result = searchPerformed(request, response, "searchProduct");
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

		String navigation = WebUtil.getParameterString(request, "navigation", "");
		try {
			Integer productId = WebUtil.getParameterInteger(request,
					"productId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = productId;

			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "ACTIVATEPRODUCT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for ACTIVATEPRODUCT access");
				return errorResult;
				
			}
			
			if (navigation.equalsIgnoreCase("activate")){
			
				boolean res = productService.activate(productId, user);
	
				if (res) {
					request.setAttribute("alert", alertProperties.getMessage(
							"success.delete.product", null, "success", Locale
									.getDefault()));
				} else {
					request.setAttribute("alert", alertProperties.getMessage(
							"fail.delete.product", null, "fail", Locale
									.getDefault()));
	
				}
			}
			else if (navigation.equalsIgnoreCase("settemplate")){
				boolean res = productService.setAsProductTemplate(productId, user);
				
				if (res) {
					request.setAttribute("alert", alertProperties.getMessage(
							"success.delete.product", null, "success", Locale
									.getDefault()));
				} else {
					request.setAttribute("alert", alertProperties.getMessage(
							"fail.delete.product", null, "fail", Locale
									.getDefault()));
	
				}
			}
			result = detailPerformed(request, response, "detailProduct");
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
	public ModelAndView synchronizePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		String navigation = WebUtil.getParameterString(request, "navigation", "");
		try {
			Integer productId = WebUtil.getParameterInteger(request,
					"productId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = productId;

			
			ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "ACTIVATEPRODUCT");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for ACTIVATEPRODUCT access");
				return errorResult;
				
			}
			
			if (navigation.equalsIgnoreCase("synchronize")){
			
				Product product = productService.get(productId);
				
				if (product != null){
	
					product.setDoSynchronize(1);
					
					productService.update(product, user);
					if (product != null) {
						request.setAttribute("alert", alertProperties.getMessage(
								"success.synchronize.product", null, "success", Locale
										.getDefault()));
					} else {
						request.setAttribute("alert", alertProperties.getMessage(
								"fail.synchronize.product", null, "fail", Locale
										.getDefault()));
		
					}
				}
			}
			//Andre: redirect ke page detailProduct link synchronize berubah 
			
			response.sendRedirect(""+request.getContextPath()+"/product?navigation=detail&productId="+productId+"");
			result = detailPerformed(request, response, "detailProduct");
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
			Integer productId = WebUtil.getParameterInteger(request,
					"productId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			
			String[] required = {"Product.ProductType","Product.ProductLimitType","Product.ClientId",
					"Product.ProductStatus","Product.CaseCategory","Product.ProductCurrencyId","Product.SharedProductId","Product.PolicyId"};

			result = new ModelAndView(location);
			java.io.Serializable pkey = productId;
			Product object = productService.get(pkey,required);
			ActionUser user = securityService.getActionUser(request);

			if (object == null) {
				request
						.setAttribute("alert", alertProperties.getMessage(
								"not.found.product", null, "fail", Locale
										.getDefault()));
			}
			
			if (object != null){
				if (object.getSharedProductId() == null){
					String[] eqSharePlanParam = {"sharedProductId.productId","deletedStatus"};
					Object[] eqSharePlanValue = {object.getProductId(),0};
					
					int totalShared = productService.getTotal(null,null,eqSharePlanParam,eqSharePlanValue);
					Collection<Product> sharedList = productService.search(null,null,eqSharePlanParam,eqSharePlanValue,0,totalShared);
					result.addObject("sharedPlan", sharedList);
				}
				else {
					String[] eqSharePlanParam = {"sharedProductId.productId","deletedStatus"};
					Object[] eqSharePlanValue = {object.getSharedProductId().getProductId(),0};
					
					int totalShared = productService.getTotal(null,null,eqSharePlanParam,eqSharePlanValue);
					Collection<Product> sharedList = productService.search(null,null,eqSharePlanParam,eqSharePlanValue,0,totalShared);
					result.addObject("sharedPlan", sharedList);
				}
				
				String[] eqParam = {"productId.productId","deletedStatus"};
				Object[] eqValue = {object.getProductId(),0};
				
				int total = productLayerLimitService.getTotal(null,null,eqParam,eqValue);
				Collection<ProductLayerLimit> layerList = productLayerLimitService.search(null,null,eqParam,eqValue,0,total);
				
				result.addObject("ProductLayerLimits", layerList);
				
			}

			result.addObject("product", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("productId", object.getProductId());
			
			request.setAttribute("userType", user.getUser().getUserId());

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
			String navigation = request.getParameter("navigation");	
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");
			
			result = new ModelAndView(location);
			String q = WebUtil.getParameterString(request, "q", "");
			Collection<Product> products = null;
			
			if (navigation.equalsIgnoreCase("lookupjson")){			
				products = productService.searchClientProduct(q, clientId);
			}
			else if (navigation.equalsIgnoreCase("lookuppolicyjson")){
				String[] eqParam = {"deletedStatus","policyId.policyId"};
				Object[] eqValue = {0,policyId};
				String[] likeParam = {"productName"};
				Object[] likeValue = {q};				
				
				
				products = productService.search(likeParam,likeValue,eqParam,eqValue,0,15);
			}
					
			
			result.addObject("Products", products);

			
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
			

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			Integer productType = WebUtil.getParameterInteger(request, "productType");
			String navigation = WebUtil.getParameterString(request,
					"navigation", "");
			String searchtext = WebUtil.getParameterString(request,
					"searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby",
					"");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer memberId = WebUtil.getParameterInteger(request, "memberId");
			Integer clientId = WebUtil.getParameterInteger(request, "clientId");
			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			String subnavigation = WebUtil.getParameterString(request,"subnavigation","");
			String currentNavigation = WebUtil.getParameterString(request, "currentnavigation", "");
			
			Collection<CaseCategory> caseCategories = caseCategoryService.getAll();
			
			if (currentNavigation.equalsIgnoreCase("listclient")){
				location = "listClientProduct";
			}
			else if (currentNavigation.equalsIgnoreCase("listmember")){
				location = "listMemberProduct";
			}
			else if (currentNavigation.equalsIgnoreCase("listpolicy")){
				location = "listPolicyProduct";
			}
			else if (currentNavigation.equalsIgnoreCase("listpackage")){
				location = "listPackageProduct";
			}
			
			result = new ModelAndView(location);
			request.setAttribute("navigation", navigation);
			result.addObject("subnavigation",subnavigation);
			result.addObject("clientId", clientId);
			result.addObject("memberId", memberId);
			result.addObject("policyId", policyId);
			int minIndex = 0;
			int maxIndex = 0;
			int totalIndex = 0;

			Collection collection = null;

			int rowsetint = 0;
			int count = 0;
			
			String arah = WebUtil.getParameterString(request, "arah", "");
			String sortcolumn = WebUtil.getParameterString(request, "sortcolumn", "");
			
			boolean sortProductName = true, sortProductCode = true, sortClient = true, sortPolicyNo = true,
					sortProductType = true;
			boolean order = true;

			if (rowset != null && !rowset.equalsIgnoreCase("" )  && StringUtils.isNumeric(rowset)) {
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
			
			if (navigation.equals("gosearch") || navigation.equals("golookup")|| navigation.equalsIgnoreCase("listpolicy") || navigation.equalsIgnoreCase("golistpolicy")|| navigation.equalsIgnoreCase("listclient") || 
					navigation.equals("gosearchbysort") || (navigation.equals("") && !arah.isEmpty())) {

				if (searchby != null && searchtext!=null && !searchtext.equals("")) {
					/**
					 * ini querynya disesuaikan dengan apa yang mau di search
					 * default nya gue bikin template search by semua field yang
					 * tipenya 'String' -adhit
					 */

					if (searchby.equalsIgnoreCase("productCode")) {
						vLikeP.add("productCode");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("productName")) {
						vLikeP.add("productName");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("policyNumber")) {
						vLikeP.add("policyId.policyNumber");
						vLikeQ.add(searchtext);
					}
					if (searchby.equalsIgnoreCase("clientName")) {
						vLikeP.add("policyId.clientId.clientName");
						vLikeQ.add(searchtext);
					}
				}
				if (productType != null && productType.intValue() > 0){
					vEqP.add("caseCategory.caseCategoryId") ;
					vEqQ.add(productType);
							
				}

			}
			
			if (navigation.equalsIgnoreCase("listmember") || subnavigation.equalsIgnoreCase("listmember")){
				vEqP.add("memberId.memberId");
				vEqQ.add(memberId);
				result.addObject("memberId",memberId);
			}
			else if (navigation.equalsIgnoreCase("listclient") || subnavigation.equalsIgnoreCase("listclient")){
				vEqP.add("clientId.clientId");
				vEqQ.add(clientId);
				result.addObject("clientId",clientId);
			}
			else if (navigation.equalsIgnoreCase("listpolicy") ){
				vEqP.add("policyId.policyId");
				vEqQ.add(policyId);
				result.addObject("policyId",policyId);
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

			count = productService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"Product.ClientId", "Product.ProductLimitType",
					"Product.ProductType","Product.CaseCategory","Product.PolicyId","Product.PolicyId.ClientId"
			// foreign affairs end
			};
			
			//SORTING START
			if(sortcolumn!=null && !sortcolumn.equals("")){
				String sortByColumn = new String();
				Boolean sortOrder = Boolean.valueOf(WebUtil.getParameterString(request, "sortorder", ""));
				String columntosort = WebUtil.getParameterString(request, "columntosort","");
				if(navigation.equals("gosearchbysort")){
					if(sortcolumn.equalsIgnoreCase("productname")){
						sortByColumn = "productName";
						Boolean sortByOrderProductName = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProductName", ""));
						sortProductName = !sortByOrderProductName;
						order = sortProductName;
					}else if(sortcolumn.equalsIgnoreCase("productcode")){
						sortByColumn = "productCode";
						Boolean sortByOrderProductCode = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProductCode", ""));
						sortProductCode = !sortByOrderProductCode;
						order = sortProductCode;
					}else if(sortcolumn.equalsIgnoreCase("client")){
						sortByColumn = "policyId.clientId.clientName";
						Boolean sortByOrderClient = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderClient", ""));
						sortClient = !sortByOrderClient;
						order = sortClient;
					}else if(sortcolumn.equalsIgnoreCase("policynumber")){
						sortByColumn = "policyId.policyNumber";
						Boolean sortByOrderPolicy = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderPolicy", ""));
						sortPolicyNo = !sortByOrderPolicy;
						order = sortPolicyNo;
					}else{
						sortByColumn = "productType.productTypeName";
						Boolean sortByOrderProduct = Boolean.valueOf(WebUtil.
								getParameterString(request, "sortByOrderProduct", ""));
						sortProductType = !sortByOrderProduct;
						order = sortProductType;
					}
					columntosort = sortByColumn;
				}else{
					sortByColumn = columntosort;
					order = sortOrder;
					if(sortcolumn.equalsIgnoreCase("productname")){
						sortProductName = order;
					}else if(sortcolumn.equalsIgnoreCase("productcode")){
						sortProductCode = order;
					}else if(sortcolumn.equalsIgnoreCase("client")){
						sortClient = order;
					}else if(sortcolumn.equalsIgnoreCase("policynumber")){
						sortPolicyNo = order;
					}else{
						sortProductType = order;
					}
				}
				request.setAttribute("sortorder", order);
				request.setAttribute("columntosort", columntosort);
				collection = productService.search(sLikeP, sLikeQ, sEqP, sEqQ, order, sortByColumn,
						required, rowsetint, countSet.intValue());
			}else{
				collection = productService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
				collection = productService.search(sLikeP, sLikeQ, sEqP, sEqQ,
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
					System.out.println("member group object : "+clientObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			result.addObject("policy", policyObject);
			result.addObject("Products", collection);
			result.addObject("client", clientObject);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));
			request.setAttribute("caseCategories", caseCategories);
			request.setAttribute("productType", productType);
			request.setAttribute("sortPolicyNo", sortPolicyNo);
			request.setAttribute("sortProductCode", sortProductCode);
			request.setAttribute("sortProductName", sortProductName);
			request.setAttribute("sortProductType", sortProductType);
			request.setAttribute("sortClient", sortClient);

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
		
		Object user = null;

		ModelAndView result = null;
		HttpSession session = request.getSession(false);


		String breadcrumb = "";
		Integer policyId = WebUtil.getParameterInteger (request,"policyId");

		try {
			if (navigation.equalsIgnoreCase("detail")) {
				result = detailPerformed(request, response, "detailProduct");
				String productId = request.getParameter("productId");
				breadcrumb = "<a href=\"product?navigation=detail&productId="+productId+"\" class=\"linkbreadcrumb\">Detail Product</a>";
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			}
			 else if (navigation.equalsIgnoreCase("synchronize")) {
					result = synchronizePerformed(request, response);
				} 
			else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "searchProduct");
				breadcrumb = "<a href=\"product?navigation=search&searchtext=&searchby=&rowset=&index=\" class=\"linkbreadcrumb\">Search Product</a>";
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupProduct");
			} else if (navigation.equalsIgnoreCase("listmember") || subnavigation.equalsIgnoreCase("listmember")) {
				result = searchPerformed(request, response, "listMemberProduct");
			} else if (navigation.equalsIgnoreCase("listclient") || subnavigation.equalsIgnoreCase("listclient")) {
				result = searchPerformed(request, response, "listClientProduct");
				String clientId = request.getParameter("clientId");
				breadcrumb = "<a href=\"client?navigation=detail&clientId="+clientId+"\" class=\"linkbreadcrumb\">Detail Client</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Client Product";
			}
			else if (navigation.equalsIgnoreCase("listpolicy") || navigation.equalsIgnoreCase("golistpolicy")) {
				result = searchPerformed(request, response, "listPolicyProduct");
				breadcrumb = "<a href=\"policy?navigation=detail&policyId="+policyId+"\" class=\"linkbreadcrumb\">Detail Policy</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Policy Product";
			} 
			else if (navigation.equalsIgnoreCase("activate") || navigation.equalsIgnoreCase("settemplate")){
				result = activatePerformed(request, response);
			}		
			else if (navigation.equalsIgnoreCase("lookupjson") || navigation.equalsIgnoreCase("lookuppolicyjson")) {
				result = lookupJsonPerformed(request, response, "lookupProductJson");
			}
			
			else {
				result = searchPerformed(request, response, "searchProduct");
				breadcrumb = "<a href=\"product?navigation=search&searchtext=&searchby=&rowset=&index=\" class=\"linkbreadcrumb\">Search Product</a>";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.addObject("breadcrumb", breadcrumb);
		result.addObject("policyId", policyId);
		Map map = TableRenderingServlet.seti18N(request, response);
		result.addAllObjects(map);
		return result;
	}

	// class+

	// class-

}
