package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
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
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyProduct;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.PolicyProductService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PolicyProductForm;

// imports+ 

// imports- 

/**
 * PolicyProduct is a mapping of policy_product Table.
 */
public class PolicyProductFormController extends SimpleFormController
{

	PolicyProductService policyProductService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs
	SecurityService securityService;
	PolicyService policyService;
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

	public void setPolicyService(PolicyService obj) {
		this.policyService = obj;
	}

	public PolicyService getPolicyService() {
		return this.policyService;
	}

	ProductService productService;

	public void setProductService(ProductService obj) {
		this.productService = obj;
	}

	public ProductService getProductService() {
		return this.productService;
	}
	public void setPolicyProductService(PolicyProductService object) {
		this.policyProductService = object;
	}

	public PolicyProductService getPolicyProductService() {
		return this.policyProductService;
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

	public PolicyProductFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		PolicyProductForm tmp = null;
		Long policyProductId = WebUtil.getParameterLong(request,
				"policyProductId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (policyProductId != null) {
			java.io.Serializable pkey = policyProductId;
			PolicyProduct object = policyProductService.get(pkey);

			if (object != null) { // edit object

				tmp = new PolicyProductForm(object);
				// foreign affairs

				tmp.setPolicyId("" + object.getPolicyId().getPolicyId());

				tmp.setProductId("" + object.getProductId().getProductId());

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PolicyProductForm();
				// foreign affairs

				Integer policyId = WebUtil.getParameterInteger(request,
						"policyId");

				if (policyId != null) {
					Policy policy = this.policyService.get(policyId);
					tmp.getPolicyProduct().setPolicyId(policy);
					tmp.setPolicyNumber(policy.getPolicyNumber());
					tmp.setPolicyId(policy.getPolicyId().toString());
				} 

				Integer productId = WebUtil.getParameterInteger(request,"productId");

				if (productId != null) {
					Product product = this.productService.get(productId);
					tmp.getPolicyProduct().setProductId(product);
					tmp.setProductCode(product.getProductCode());
				} 
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PolicyProductForm();

			Integer policyId = WebUtil.getParameterInteger(request, "policyId");

			if (policyId != null) {
				Policy policy = this.policyService.get(policyId);
				tmp.getPolicyProduct().setPolicyId(policy);
				tmp.setPolicyId(policy.getPolicyId().toString());
				tmp.setPolicyNumber(policy.getPolicyNumber());
				
				
			}

			Integer productId = WebUtil.getParameterInteger(request,
					"productId");

			if (productId != null) {
				Product product = this.productService.get(productId);
				tmp.getPolicyProduct().setProductId(product);
				tmp.setProductCode(product.getProductCode());
			}
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		PolicyProductForm policyProductForm = (PolicyProductForm) command;
		errors.printStackTrace();
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		Integer policyId = WebUtil.getParameterInteger(request, "policyId");
		try {
			Policy policy = policyService.get(policyId);
			
			if (policy != null){
				model.put("clientId", policy.getClientId().getClientId());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PolicyProductForm policyProductForm = (PolicyProductForm) command;

		PolicyProduct res = null;
		String alertMsg = "";
		String redirectURL = "";
		try {

			ActionUser user = securityService.getActionUser(request);

			if (policyProductForm.isNewPolicyProduct()) {
				res = policyProductService.create(policyProductForm
						.getPolicyProduct(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.policyproduct", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.policyproduct", null, "fail", Locale
									.getDefault());
				}
				redirectURL = "policyproduct?policyId="+res.getPolicyId().getPolicyId()+"&alert="+alertMsg;
			} else {
				res = policyProductService.update(policyProductForm
						.getPolicyProduct(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.policyproduct", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.policyproduct", null, "fail", Locale
									.getDefault());
				}
				
				redirectURL = "policyproduct?policyId="+res.getPolicyId().getPolicyId()+"&alert="+alertMsg;

			}
		} catch (Exception ex) {
			if (policyProductForm.isNewPolicyProduct()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.policyproduct", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.policyproduct", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView(redirectURL));
		// return super.onSubmit(request, response, command, errors);
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
