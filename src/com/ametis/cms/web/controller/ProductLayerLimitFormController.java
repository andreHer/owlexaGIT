package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.support.ResourceBundleMessageSource; /*
 import org.acegisecurity.Authentication;
 import org.acegisecurity.context.SecurityContextHolder;
 import org.acegisecurity.userdetails.UserDetails;
 */
import java.util.Locale;
import java.util.Collection;
import com.ametis.cms.datamodel.*;
import com.ametis.cms.web.form.*;
import com.ametis.cms.service.*;
import com.ametis.cms.util.*;

// imports+

// imports-

/**
 * ProductLayerLimit is a mapping of product_layer_limit Table.
 */
public class ProductLayerLimitFormController extends SimpleFormController
// extends+

// extends-
{

	ProductLayerLimitService productLayerLimitService;
	ResourceBundleMessageSource alertProperties;
	
	private ProductTypeService productTypeService;
	private SecurityService securityService;
	// foreign affairs

	ProductService productService;

	public void setProductService(ProductService obj) {
		this.productService = obj;
	}

	public ProductService getProductService() {
		return this.productService;
	}

	// -- foreign affairs end

	public void setProductLayerLimitService(ProductLayerLimitService object) {
		this.productLayerLimitService = object;
	}

	public ProductLayerLimitService getProductLayerLimitService() {
		return this.productLayerLimitService;
	}

	// generate by default
	private UserService actionuserService;

	public UserService getUserService() {
		return actionuserService;
	}

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}

	public void setPropertiesUtil(ResourceBundleMessageSource object) {
		this.alertProperties = object;
	}

	public ResourceBundleMessageSource getPropertiesUtil() {
		return this.alertProperties;
	}

	public void setSecurityService(SecurityService object) {
		this.securityService = object;
	}

	public SecurityService getSecurityService() {
		return this.securityService;
	}

	
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ProductLayerLimitFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ProductLayerLimitForm tmp = null;
		Integer productLayerLimitId = WebUtil.getParameterInteger(request,
				"productLayerLimitId");
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		Integer productReferenceId = WebUtil.getParameterInteger(request,
				"productReferenceId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (productLayerLimitId != null) {
			java.io.Serializable pkey = productLayerLimitId;
			ProductLayerLimit object = productLayerLimitService.get(pkey);

			if (object != null) { // edit object

				tmp = new ProductLayerLimitForm(object);
				if (object.getDiagnosisSetId() != null) {
					tmp.setDiagnosisSetName(object.getDiagnosisSetId()
							.getDiagnosisSetName());
				}
				// foreign affairs

				Product product = productService.get(object.getProductId()
						.getProductId());
				if (product != null) {
					tmp.setProductId("" + object.getProductId().getProductId());
					tmp.setProductName(product.getProductName());
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductLayerLimitForm();
				// foreign affairs

				if (productId != null) {
					Product forClass = new Product();
					forClass.setProductId(productId);
					tmp.setProductId("" + productId);

					Product product = this.productService.get(productId);
					tmp.getProductLayerLimit().setProductId(product);
				} else {
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProductLayerLimitForm();
			// foreign affairs

			if (productId != null) {
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId("" + productId);

				Product product = this.productService.get(productId);
				tmp.getProductLayerLimit().setProductId(product);
				tmp.setProductName(product.getProductName());
			}
		}

		String breadcrumb = "<a href=\"productlayerlimit-form?navigation=tambah&productId="
				+ productId
				+ "&productReferenceId="
				+ productReferenceId
				+ "&rowset=&index=&productId="
				+ productId
				+ "&productLayerLimitId=\" class=\"linkbreadcrumb\">Add Limit Layer</a>";
		request.setAttribute("breadcrumb", breadcrumb);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ProductLayerLimitForm productLayerLimitForm = (ProductLayerLimitForm) command;
		ProductLayerLimit productLayerLimit = productLayerLimitForm
				.getProductLayerLimit();

		// errors.setNestedPath("productLayerLimit");
		// getValidator().validate(productLayerLimit, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
		try {
			Collection<ProductType> productTypeList = productTypeService.getAll();
			
			model.put("productTypeList", productTypeList);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ProductLayerLimitForm productLayerLimitForm = (ProductLayerLimitForm) command;

		ProductLayerLimit res = null;
		String alertMsg = "";
		String productId = productLayerLimitForm.getProductId();
		try {
			// foreign affairs
			if (productLayerLimitForm.getProductId() != null) {
				productLayerLimitForm.getProductLayerLimit().setProductId(
						this.productService.get(new Integer(
								(productLayerLimitForm.getProductId()))));
			}

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);

			if (productLayerLimitForm.isNewProductLayerLimit()) {
				res = productLayerLimitService.create(productLayerLimitForm
						.getProductLayerLimit(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.productlayerlimit", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.productlayerlimit", null, "fail",
							Locale.getDefault());
				}
			} else {
				res = productLayerLimitService.update(productLayerLimitForm
						.getProductLayerLimit(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.productlayerlimit", null,
							"success", Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.productlayerlimit", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (productLayerLimitForm.isNewProductLayerLimit()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.productlayerlimit", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.productlayerlimit", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(
				"product?navigation=detail&productId=" + productId + "&alert="
						+ alertMsg));

	}

	protected void initBinder(HttpServletRequest req,
			ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat(
				"yyyy/MM/dd"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
	}
	// class+

	// class-
}
