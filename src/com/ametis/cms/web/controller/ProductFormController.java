package com.ametis.cms.web.controller;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductLimitType;
import com.ametis.cms.datamodel.ProductType;
//Add by aju on 20150217
import com.ametis.cms.datamodel.CardType;
import com.ametis.cms.service.CardTypeService;
//
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductLimitTypeService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.ProductTypeService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProductForm;

// imports+ 

// imports- 

/**
 * Product is a mapping of product Table.
 */
public class ProductFormController extends SimpleFormController
// extends+

// extends-

{

	ProductService productService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	ClientService clientService;
	SecurityService securityService;
	CaseCategoryService caseCategoryService;
	private ActivityLogService logService;
	private CurrencyService currencyService;
	private PolicyService policyService;
	
	//Add by aju on 20150217, to remove the card type hardcode
	private CardTypeService cardTypeService;

	
	
	
	public CardTypeService getCardTypeService() {
		return cardTypeService;
	}

	public void setCardTypeService(CardTypeService cardTypeService) {
		this.cardTypeService = cardTypeService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setClientService(ClientService obj) {
		this.clientService = obj;
	}

	public ClientService getClientService() {
		return this.clientService;
	}

	ProductLimitTypeService productLimitTypeService;

	public void setProductLimitTypeService(ProductLimitTypeService obj) {
		this.productLimitTypeService = obj;
	}

	public ProductLimitTypeService getProductLimitTypeService() {
		return this.productLimitTypeService;
	}

	ProductTypeService productTypeService;

	public void setProductTypeService(ProductTypeService obj) {
		this.productTypeService = obj;
	}

	public ProductTypeService getProductTypeService() {
		return this.productTypeService;
	}

	// -- foreign affairs end

	public void setProductService(ProductService object) {
		this.productService = object;
	}

	public ProductService getProductService() {
		return this.productService;
	}
	

	// generate by default
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPropertiesUtil(ResourceBundleMessageSource object) {
		this.alertProperties = object;
	}

	public ResourceBundleMessageSource getPropertiesUtil() {
		return this.alertProperties;
	}

	public ProductFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ProductForm tmp = null;
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		String productReferenceId = WebUtil.getParameterString(request, "productReferenceId", "");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		String breadcrumb = "";

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (productId != null) {
			java.io.Serializable pkey = productId;
			String[] required = {"Product.ClientId"};
			Product object = productService.get(pkey,required);

			if (object != null) { // edit object

				tmp = new ProductForm(object);
				// foreign affairs

				tmp.setProductLimitType(""
						+ object.getProductLimitType().getProductLimitTypeId());

				tmp.setProductType(""
						+ object.getProductType().getProductTypeId());
				
				if (navigation.equalsIgnoreCase("derivative")){
					tmp.setIsNewProduct();
					tmp.setProductReference(productReferenceId);
				}
				
				if (object.getPolicyId() != null){
					Policy policy = policyService.get(object.getPolicyId().getPolicyId());
					
					if (policy != null){
						tmp.setPolicyId(policy.getPolicyId().toString());
						tmp.setPolicyNumber(policy.getPolicyNumber());
					}
				}
				
				if (object.getSharedProductId() != null){
					Product product = productService.get(object.getSharedProductId().getProductId());
					tmp.setSharedProductName(product.getProductName());
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductForm();
				// foreign affairs

				tmp.setProductReference(productReferenceId);

				Integer productLimitTypeId = WebUtil.getParameterInteger(
						request, "productLimitType");

				if (productLimitTypeId != null) {
					ProductLimitType forClass = new ProductLimitType();
					forClass.setProductLimitTypeId(productLimitTypeId);
					tmp.setProductLimitType("" + productLimitTypeId);

					ProductLimitType productLimitType = this.productLimitTypeService
							.get(productLimitTypeId);
					tmp.getProduct().setProductLimitType(productLimitType);
				} else {
					ProductLimitType forClass = new ProductLimitType();
					// tmp.setProductLimitType("");
					tmp.getProduct().setProductLimitType(forClass);
				}

				Integer productTypeId = WebUtil.getParameterInteger(request,
						"productType");

				if (productTypeId != null) {
					ProductType forClass = new ProductType();
					forClass.setProductTypeId(productTypeId);
					tmp.setProductType("" + productTypeId);

					ProductType productType = this.productTypeService
							.get(productTypeId);
					tmp.getProduct().setProductType(productType);
				} else {
					ProductType forClass = new ProductType();
					// tmp.setProductType("");
					tmp.getProduct().setProductType(forClass);
				}

				// -- foreign affairs end
			}
			
	        if (navigation.equalsIgnoreCase("update")) {
	        	 breadcrumb= "<a href=\"product?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">Detail Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Product";
	        }
	        if (navigation.equalsIgnoreCase("tambah")) {
	        	 breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
	        }
	        else {
	        	 breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
	        }
		} // mau edit end
		else { // bikin baru
			tmp = new ProductForm();
			// foreign affairs
			
			tmp.setReimbursementPercentage("100");
			tmp.setCashlessPercentage("100");
			tmp.setOverseasPercentage("100");
			tmp.setDomesticPercentage("100");



			Integer productLimitTypeId = WebUtil.getParameterInteger(request,
					"productLimitType");

			if (productLimitTypeId != null) {
				ProductLimitType forClass = new ProductLimitType();
				forClass.setProductLimitTypeId(productLimitTypeId);
				tmp.setProductLimitType("" + productLimitTypeId);

				ProductLimitType productLimitType = this.productLimitTypeService
						.get(productLimitTypeId);
				tmp.getProduct().setProductLimitType(productLimitType);
			} else {
				ProductLimitType forClass = new ProductLimitType();
				// tmp.setProductLimitType("");
				tmp.getProduct().setProductLimitType(forClass);
			}

			Integer productTypeId = WebUtil.getParameterInteger(request,
					"productType");

			if (productTypeId != null) {
				ProductType forClass = new ProductType();
				forClass.setProductTypeId(productTypeId);
				tmp.setProductType("" + productTypeId);

				ProductType productType = this.productTypeService
						.get(productTypeId);
				tmp.getProduct().setProductType(productType);
			} else {
				ProductType forClass = new ProductType();
				// tmp.setProductType("");
				tmp.getProduct().setProductType(forClass);
			}

			// -- foreign affairs end
			
	        if (navigation.equalsIgnoreCase("listclient")) {
				breadcrumb = "<a href=\"product?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
	        }
	        if (navigation.equalsIgnoreCase("listpolicy")) {
				breadcrumb = "<a href=\"product?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
	        }
	        if (navigation.equalsIgnoreCase("update")) {
	          	 breadcrumb= "<a href=\"product?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">Detail Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Product";
	        }
	        if (navigation.equalsIgnoreCase("tambah")) {
	             breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
	        }
	        else {
	        	 breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
	        }
			request.setAttribute("breadcrumb", breadcrumb);
			request.setAttribute("navigation", navigation);
			request.setAttribute("productId", productId);
			request.setAttribute("clientId", clientId);
			request.setAttribute("policyId", policyId);
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ProductForm productForm = (ProductForm) command;
		Product product = productForm.getProduct();
		
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");

		errors.printStackTrace();
		
		String breadcrumb = "";
		
        if (navigation.equalsIgnoreCase("listclient")) {
			breadcrumb = "<a href=\"product-form?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
        }
        if (navigation.equalsIgnoreCase("listpolicy")) {
			breadcrumb = "<a href=\"product?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
        }
        if (navigation.equalsIgnoreCase("update")) {
       	 breadcrumb= "<a href=\"product?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">Detail Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Product";
       }
       if (navigation.equalsIgnoreCase("tambah")) {
       	 breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
       }
       else {
      	 breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
      }
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("productId", productId);
		request.setAttribute("clientId", clientId);
		request.setAttribute("policyId", policyId);

		// errors.setNestedPath("product");
		// getValidator().validate(product, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
		 * ini dipake buat populate data - data yang dibutuhkan contoh : Problem
		 * membutuhkan ProblemCategory nah fungsi method ini yaitu untuk
		 * populate list problem category ke jsp nanti biar bisa ditangkep sama
		 * jspnya
		 * 
		 * contoh : Collection pc = pcontroller.searchPC();
		 * 
		 * model.addObject("pcbeans", pc);
		 * 
		 */
		Collection productType = productTypeService.getAll();
		Collection productLimitType = productLimitTypeService.getAll();
		model.put("productType", productType);
		model.put("productLimitType", productLimitType);

		Collection<CaseCategory> caseCategoryCollection = caseCategoryService.getAll();
		model.put("caseCategories", caseCategoryCollection);
		
		Collection<Currency> currencyList = currencyService.getAll();
		model.put("currencyList", currencyList);
		
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		model.put("navigation", navigation);
		
		//Add by aju on 20150217, to remove the card type hardcode
		Collection<CardType> cardTypeList = cardTypeService.getAll();
		model.put("cardTypeList", cardTypeList);
		
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		Integer policyId = WebUtil.getParameterInteger(request, "policyId");

		ProductForm productForm = (ProductForm) command;

		Product res = null;
		String alertMsg = "";
		String redirectUrl = "";
		try {
			

			if (productForm.getProductLimitType() != null) {
				productForm.getProduct().setProductLimitType(
						this.productLimitTypeService.get(new Integer(
								(productForm.getProductLimitType()))));
			}

			if (productForm.getProductType() != null) {
				productForm.getProduct().setProductType(
						this.productTypeService.get(new Integer((productForm
								.getProductType()))));
			}

			// -- foreign affairs end

			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (productForm.isNewProduct()) {

				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEPRODUCT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEPRODUCT access");
					return errorResult;

				}
				Product product = productForm.getProduct();
				if (product != null){
					product.setProductId(null);
					product.setProductBenefits(null);
					product.setProductClausuls(null);
					product.setMemberProducts(null);
					product.setProductPackages(null);
					product.setGroupProducts(null);
					product.setIsTemplate(null);
					
					if (productForm.getProductReference() != null && !productForm.getProductReference().equalsIgnoreCase("")){
						Product reference = new Product();
						reference.setProductId(Integer.valueOf(productForm.getProductReference()));
						product.setProductReference(reference);
						
					}
				}
				res = productService.create(product, user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.product", null, "success", Locale
									.getDefault());
					redirectUrl = "product?navigation=detail&productId="
							+ res.getProductId();

				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.product", null, "fail", Locale
									.getDefault());
					redirectUrl = "product" + "?alert=" + alertMsg;
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEPRODUCT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEPRODUCT access");
					return errorResult;

				}
				res = productService.update(productForm.getProduct(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.product", null, "success", Locale
									.getDefault());
					redirectUrl = "product?navigation=detail&productId="
							+ res.getProductId();
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.product", null, "fail", Locale
									.getDefault());
					redirectUrl = "product" + "?alert=" + alertMsg;
				}

			}
		} catch (Exception ex) {
			if (productForm.isNewProduct()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.product", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.product", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "";
		
        if (navigation.equalsIgnoreCase("listclient")) {
			breadcrumb = "<a href=\"product-form?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
        }
        if (navigation.equalsIgnoreCase("listpolicy")) {
			breadcrumb = "<a href=\"product?navigation="+navigation+"&policyId="+policyId+"\" class=\"linkbreadcrumb\">List Policy Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
        }
        if (navigation.equalsIgnoreCase("update")) {
       	 breadcrumb= "<a href=\"product?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">Detail Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Product";
        }
        if (navigation.equalsIgnoreCase("tambah")) {
       	 breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
        }
        else {
       	 breadcrumb= "<a href=\"product\" class=\"linkbreadcrumb\">Search Product</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Product";
        }
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("productId", productId);
		request.setAttribute("clientId", clientId);
		request.setAttribute("policyId", policyId);

		return new ModelAndView(new RedirectView(redirectUrl));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"dd-MM-yyyy"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
		
		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		//java.util.Currency currency = java.util.Currency.getInstance("$");
		nf.setGroupingUsed(true);
		//nf.setCurrency(currency);

		CustomNumberEditor intNum = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, intNum);
		
		
        
		binder.registerCustomEditor(java.lang.Double.class,
                new CustomNumberEditor (java.lang.Double.class, nf,true));
	}
	// class+

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	// class-

}
