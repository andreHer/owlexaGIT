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
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductClausul;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ProductClausulService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * ProductClausul is a servlet controller for product_clausul Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class ProductClausulController implements Controller

// extends+

// extends-

{

	private ProductClausulService productClausulService;

	private UserService userService;
	private ProductService productService;

	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;
	SecurityService securityService;
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
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

	public void setProductClausulService(
			ProductClausulService productClausulService) {
		this.productClausulService = productClausulService;
	}

	public ProductClausulService getProductClausulService() {
		return this.productClausulService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer productClausulId = WebUtil.getParameterInteger(request,
					"productClausulId");
			
			Integer productId = WebUtil.getParameterInteger(request, "productId");
			String currentNavigation = WebUtil.getParameterString(request, "currentNavigation", "");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = productClausulId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user,
					"CREATEPRODUCTCLAUSUL");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView(
						"errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult
						.addObject("errorMessage",
								"You Are Not Authorized for CREATEPRODUCTCLAUSUL access");
				return errorResult;

			}
			ProductClausul res = productClausulService.delete(pkey, user);
			String alert = "";

			if (res != null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"success.delete.productclausul", null, "success",
						Locale.getDefault()));
				
				alert = alertProperties.getMessage(
						"success.delete.productclausul", null, "success",
						Locale.getDefault());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.delete.productclausul", null, "fail", Locale
								.getDefault()));
				
				alert = alertProperties.getMessage(
						"fail.delete.productclausul", null, "fail",
						Locale.getDefault());

			}
			result = new ModelAndView(new RedirectView("productclausul?navigation="+currentNavigation+"&productId="+productId+"&alert="+alert));
			
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
			Integer productClausulId = WebUtil.getParameterInteger(request,
					"productClausulId");

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
			java.io.Serializable pkey = productClausulId;
			ProductClausul object = productClausulService.get(pkey);

			if (object == null) {
				request.setAttribute("alert", alertProperties.getMessage(
						"not.found.productclausul", null, "fail", Locale
								.getDefault()));
			}
			/*
			 * convention .. kalo mau nampilkan sebuah object pake BeanClass aja
			 * ya .. ga pake 's' -adhit
			 */

			result.addObject("productClausul", object);
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

			Integer productId = WebUtil.getParameterInteger(request, "productId");
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
			

					if (searchby.equalsIgnoreCase("clausulName")) {
						vLikeP.add("clausulId.clausulName");
						vLikeQ.add(searchtext);
					}
					
				}

			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));

			if (productId != null){
				vEqP.add("productId.productId");
				vEqQ.add(productId);
			}
			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = productClausulService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"ProductClausul.ProductId", "ProductClausul.ClausulId",
			// foreign affairs end
			};
			collection = productClausulService.search(sLikeP, sLikeQ, sEqP,
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
				collection = productClausulService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("productId", productId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ProductClausuls", collection);

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
	public ModelAndView listPerformed(HttpServletRequest request,
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

			Integer productId = WebUtil.getParameterInteger(request, "productId");
			
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
			if (navigation.equals("gosearch") || navigation.equals("golookup") || navigation.equalsIgnoreCase("golistexdiagnosis")) {

				if (searchby.equalsIgnoreCase("diagnosisCode")){

					vLikeP.add("diagnosisCode");
					vLikeQ.add(searchtext);
				}
				if (searchby.equalsIgnoreCase("diagnosisName")){

					vLikeP.add("diagnosisName");
					vLikeQ.add(searchtext);
				}
			}
			else if (navigation.equals("list") || navigation.equals("golookuplist")) {
				if(searchby.equalsIgnoreCase("clausulName"))
				{
					vLikeP.add("clausulId.clausulName");
					vLikeQ.add(searchtext);
				}
				else if(searchby.equalsIgnoreCase("diagnosisCode"))
				{
					vLikeP.add("diagnosisId.diagnosisCode");
					vLikeQ.add(searchtext);
				}
			}
			else if (navigation.equals("listexdiagnosis") || navigation.equals("listexdiagnosis")){
				if(searchby.equalsIgnoreCase("diagnosisCode"))
				{
					vLikeP.add("diagnosisId.diagnosisCode");
					vLikeQ.add(searchtext);
				}
				else if(searchby.equalsIgnoreCase("diagnosisName"))
				{
					vLikeP.add("diagnosisId.diagnosisName");
					vLikeQ.add(searchtext);
				}
				else if(searchby.equalsIgnoreCase("diagnosisName"))
				{
					vLikeP.add("diagnosisId.diagnosisName");
					vLikeQ.add(searchtext);
				}
			}

			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));
			

			if (productId != null){
				vEqP.add("productId.productId");
				vEqQ.add(productId);
				
				Product product = productService.get(productId);
				result.addObject("product", product);
			}
			
			String sLikeP[] = new String[vLikeP.size()];
			vLikeP.toArray(sLikeP);
			Object sLikeQ[] = new Object[vLikeP.size()];
			vLikeQ.toArray(sLikeQ);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);

			count = productClausulService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
					"ProductClausul.ProductId", "ProductClausul.ClausulId",
			// foreign affairs end
			};
			collection = productClausulService.search(sLikeP, sLikeQ, sEqP,
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
				collection = productClausulService.search(sLikeP, sLikeQ, sEqP,
						sEqQ, required, rowsetint, countSet.intValue());
			}
			
			Product productObject = null;
			
			if(productId != null){
				try
				{
				java.io.Serializable productpkey = productId;
				System.out.println("product id : "+productId);
				String[] productRequired = {"Product.ClientId", "Product.ProductLimitType",
						"Product.ProductType","Product.CaseCategory","Product.PolicyId","Product.PolicyId.ClientId", "Product.ProductStatus",
						"Product.SharedProductId", "Product.ProductCurrencyId"};
				productObject = productService.get(productpkey, productRequired);
				
				}
				catch (Exception ex)
				{
//					System.out.println("member group object : "+productObject.getAddress());
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

			result.addObject("ProductClausuls", collection);
			result.addObject("product", productObject);

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
		String breadcrumb="";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response,
						"detailProductClausul");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search")
					|| navigation.equals("gosearch")) {
				result = searchPerformed(request, response,
						"listProductClausul");
			} else if (navigation.equalsIgnoreCase("lookup")
					|| navigation.equals("golookup")) {
				result = searchPerformed(request, response,
						"lookupProductClausul");
			} else if (navigation.equalsIgnoreCase("list") || navigation.equalsIgnoreCase("golookuplist")){
				String productId = WebUtil.getParameterString(request, "productId", "");
				result = listPerformed(request, response, "listProductClausul");
				breadcrumb = "<a href=\"product?navigation=detail&productId="+productId+"\" class=\"linkbreadcrumb\">Detail Product</a>  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Product Clausul";
				result.addObject("productId", productId);
			}	
			else if (navigation.equalsIgnoreCase("listexdiagnosis") || navigation.equalsIgnoreCase("golistexdiagnosis")){
				String productId = WebUtil.getParameterString(request, "productId", "");
				result = listPerformed(request, response, "listExcludeDiagnosisClausul");
				breadcrumb = "<a href=\"product?navigation=detail&productId="+productId+"\" class=\"linkbreadcrumb\">Detail Product</a>  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Product Diagnosis Exclusion";
				result.addObject("productId", productId);
			}		
			else {
				result = searchPerformed(request, response,
						"listProductClausul");
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
