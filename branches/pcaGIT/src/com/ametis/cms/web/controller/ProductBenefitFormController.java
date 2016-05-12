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
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.DiscountUsageType;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductBenefit;
import com.ametis.cms.datamodel.ProductLayerLimit;
import com.ametis.cms.datamodel.TreatmentLocation;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BenefitUsageTypeService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.DiscountUsageTypeService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.MeasurementUnitService;
import com.ametis.cms.service.ProductBenefitService;
import com.ametis.cms.service.ProductLayerLimitService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.TreatmentLocationService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ProductBenefitForm;

// imports+ 

// imports- 

/**
 * ProductBenefit is a mapping of product_benefit Table.
 */
public class ProductBenefitFormController extends SimpleFormController
// extends+

// extends-

{

	ProductBenefitService productBenefitService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	DiscountUsageTypeService discountUsageTypeService;
	SecurityService securityService;
	private ActivityLogService logService;
	private ProductLayerLimitService productLayerService;

	
	
	public ProductLayerLimitService getProductLayerService() {
		return productLayerService;
	}

	public void setProductLayerService(ProductLayerLimitService productLayerService) {
		this.productLayerService = productLayerService;
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

	public void setDiscountUsageTypeService(DiscountUsageTypeService obj) {
		this.discountUsageTypeService = obj;
	}

	public DiscountUsageTypeService getDiscountUsageTypeService() {
		return this.discountUsageTypeService;
	}

	BenefitUsageTypeService benefitUsageTypeService;

	public void setBenefitUsageTypeService(BenefitUsageTypeService obj) {
		this.benefitUsageTypeService = obj;
	}

	public BenefitUsageTypeService getBenefitUsageTypeService() {
		return this.benefitUsageTypeService;
	}

	ProductService productService;

	public void setProductService(ProductService obj) {
		this.productService = obj;
	}

	public ProductService getProductService() {
		return this.productService;
	}

	MeasurementUnitService measurementUnitService;

	public void setMeasurementUnitService(MeasurementUnitService obj) {
		this.measurementUnitService = obj;
	}

	public MeasurementUnitService getMeasurementUnitService() {
		return this.measurementUnitService;
	}

	TreatmentLocationService treatmentLocationService;

	public void setTreatmentLocationService(TreatmentLocationService obj) {
		this.treatmentLocationService = obj;
	}

	public TreatmentLocationService getTreatmentLocationService() {
		return this.treatmentLocationService;
	}

	ItemCategoryService itemCategoryService;

	public void setItemCategoryService(ItemCategoryService obj) {
		this.itemCategoryService = obj;
	}

	public ItemCategoryService getItemCategoryService() {
		return this.itemCategoryService;
	}

	CaseCategoryService caseCategoryService;

	public void setCaseCategoryService(CaseCategoryService obj) {
		this.caseCategoryService = obj;
	}

	public CaseCategoryService getCaseCategoryService() {
		return this.caseCategoryService;
	}

	// -- foreign affairs end

	public void setProductBenefitService(ProductBenefitService object) {
		this.productBenefitService = object;
	}

	public ProductBenefitService getProductBenefitService() {
		return this.productBenefitService;
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

	public ProductBenefitFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ProductBenefitForm tmp = null;
		Integer productBenefitId = WebUtil.getParameterInteger(request,
				"productBenefitId");
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		Integer productLayerId = WebUtil.getParameterInteger(request, "productLayerLimitId");
		Integer productClausulId = WebUtil.getParameterInteger (request,"productClausulId");
		String currentNavigation =  WebUtil.getParameterString(request, "currentNavigation", "");
		Integer index = WebUtil.getParameterInteger(request, "index");
		String searchtext = WebUtil.getParameterString(request,
				"searchtext", "");
		String navigation = WebUtil.getParameterString(request,
				"navigation", "");
		String searchby = WebUtil.getParameterString(request,
				"searchby", "");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (productBenefitId != null) {
			java.io.Serializable pkey = productBenefitId;
			ProductBenefit object = productBenefitService.get(pkey);

			if (object != null) { // edit object

				tmp = new ProductBenefitForm(object);
				// foreign affairs

				if (object.getDiscountUsageType() != null){
					tmp.setDiscountUsageType(""
						+ object.getDiscountUsageType()
								.getDiscountUsageTypeId());
				}
				if (object.getBenefitUsageType() != null){
					tmp.setBenefitUsageType(""
						+ object.getBenefitUsageType().getBenefitUsageTypeId());
				}

				if (object.getProductId() != null){

					Product product = this.productService.get(object.getProductId().getProductId());
					
					tmp.setProductId("" + product.getProductId());
					tmp.setProductName("" + product.getProductName());
				}
				if (object.getMeasurementUnit() != null){
					tmp.setMeasurementUnit(""
						+ object.getMeasurementUnit().getMeasurementUnitId());
				}

				if (object.getTreatmentLocation() != null){
					tmp.setTreatmentLocation(""
						+ object.getTreatmentLocation().getLocationId());
				}
				if (object.getItemCategoryId() != null){
					System.out.println("ITEM CATEGORY NAME : " + object.getItemCategoryId().getItemCategoryName());
					ItemCategory itemCategory = this.itemCategoryService.get(object.getItemCategoryId().getItemCategoryId());
					tmp.setItemCategoryId(""
							+ object.getItemCategoryId().getItemCategoryId());
					
					tmp.setItemCategoryName(itemCategory.getItemCategoryName());
				}
				if (object.getCaseCategoryId() != null){
					tmp.setCaseCategoryId(""
						+ object.getCaseCategoryId().getCaseCategoryId());
				}
				if (object.getSharedBenefitId() != null){
					ProductBenefit sharedBenefit = productBenefitService.get(object.getSharedBenefitId().getProductBenefitId());
					
					if (sharedBenefit != null){
						tmp.setSharedBenefitId(sharedBenefit.getProductBenefitId().toString());
						tmp.setSharedBenefitName(sharedBenefit.getItemCategoryId().getItemCategoryName());
					}
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ProductBenefitForm();
				// foreign affairs

				Integer discountUsageTypeId = WebUtil.getParameterInteger(
						request, "discountUsageType");

				if (discountUsageTypeId != null) {
					DiscountUsageType forClass = new DiscountUsageType();
					forClass.setDiscountUsageTypeId(discountUsageTypeId);
					tmp.setDiscountUsageType("" + discountUsageTypeId);

					DiscountUsageType discountUsageType = this.discountUsageTypeService
							.get(discountUsageTypeId);
					tmp.getProductBenefit().setDiscountUsageType(
							discountUsageType);
				} else {
					
				}

				Integer benefitUsageTypeId = WebUtil.getParameterInteger(
						request, "benefitUsageType");

				if (benefitUsageTypeId != null) {
					BenefitUsageType forClass = new BenefitUsageType();
					forClass.setBenefitUsageTypeId(benefitUsageTypeId);
					tmp.setBenefitUsageType("" + benefitUsageTypeId);

					BenefitUsageType benefitUsageType = this.benefitUsageTypeService
							.get(benefitUsageTypeId);
					tmp.getProductBenefit().setBenefitUsageType(
							benefitUsageType);
				} else {
					
				}


				if (productId != null) {
					
					Product forClass = new Product();
					forClass.setProductId(productId);
					tmp.setProductId("" + productId);

					Product product = this.productService.get(productId);
					tmp.getProductBenefit().setProductId(product);
					tmp.setProductName(product.getProductName());
					tmp.setProductId(product.getProductId().toString());
				} else {
					
				}

				Integer measurementUnitId = WebUtil.getParameterInteger(
						request, "measurementUnit");

				if (measurementUnitId != null) {
					MeasurementUnit forClass = new MeasurementUnit();
					forClass.setMeasurementUnitId(measurementUnitId);
					tmp.setMeasurementUnit("" + measurementUnitId);

					MeasurementUnit measurementUnit = this.measurementUnitService
							.get(measurementUnitId);
					tmp.getProductBenefit().setMeasurementUnit(measurementUnit);
				} else {
					
				}

				Integer treatmentLocationId = WebUtil.getParameterInteger(
						request, "treatmentLocation");

				if (treatmentLocationId != null) {
					TreatmentLocation forClass = new TreatmentLocation();
					forClass.setLocationId(treatmentLocationId);
					tmp.setTreatmentLocation("" + treatmentLocationId);

					TreatmentLocation treatmentLocation = this.treatmentLocationService
							.get(treatmentLocationId);
					tmp.getProductBenefit().setTreatmentLocation(
							treatmentLocation);
				} else {
					
				}

				Integer itemCategoryId = WebUtil.getParameterInteger(request,
						"itemCategoryId");

				if (itemCategoryId != null) {
					ItemCategory forClass = new ItemCategory();
					forClass.setItemCategoryId(itemCategoryId);
					tmp.setItemCategoryId("" + itemCategoryId);

					ItemCategory itemCategory = this.itemCategoryService
							.get(itemCategoryId);
					tmp.getProductBenefit().setItemCategoryId(itemCategory);
				} else {
				}

				Integer caseCategoryId = WebUtil.getParameterInteger(request,
						"caseCategoryId");

				if (caseCategoryId != null) {
					CaseCategory forClass = new CaseCategory();
					forClass.setCaseCategoryId(caseCategoryId);
					tmp.setCaseCategoryId("" + caseCategoryId);

					CaseCategory caseCategory = this.caseCategoryService
							.get(caseCategoryId);
					tmp.getProductBenefit().setCaseCategoryId(caseCategory);
				} else {
					
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ProductBenefitForm();
			// foreign affairs



			Integer benefitUsageTypeId = WebUtil.getParameterInteger(request,
					"benefitUsageType");

			if (benefitUsageTypeId != null) {
				BenefitUsageType forClass = new BenefitUsageType();
				forClass.setBenefitUsageTypeId(benefitUsageTypeId);
				tmp.setBenefitUsageType("" + benefitUsageTypeId);

				BenefitUsageType benefitUsageType = this.benefitUsageTypeService
						.get(benefitUsageTypeId);
				tmp.getProductBenefit().setBenefitUsageType(benefitUsageType);
			} else {
				
			}


			if (productId != null) {
				Product forClass = new Product();
				forClass.setProductId(productId);
				tmp.setProductId("" + productId);

				Product product = this.productService.get(productId);
				tmp.getProductBenefit().setProductId(product);
				tmp.setProductId(product.getProductId().toString());
				tmp.setProductName(product.getProductName());
			} else {
				
			}

			Integer measurementUnitId = WebUtil.getParameterInteger(request,
					"measurementUnit");

			if (measurementUnitId != null) {
				MeasurementUnit forClass = new MeasurementUnit();
				forClass.setMeasurementUnitId(measurementUnitId);
				tmp.setMeasurementUnit("" + measurementUnitId);

				MeasurementUnit measurementUnit = this.measurementUnitService
						.get(measurementUnitId);
				tmp.getProductBenefit().setMeasurementUnit(measurementUnit);
			} else {
				
			}

			Integer treatmentLocationId = WebUtil.getParameterInteger(request,
					"treatmentLocation");

			if (treatmentLocationId != null) {
				TreatmentLocation forClass = new TreatmentLocation();
				forClass.setLocationId(treatmentLocationId);
				tmp.setTreatmentLocation("" + treatmentLocationId);

				TreatmentLocation treatmentLocation = this.treatmentLocationService
						.get(treatmentLocationId);
				tmp.getProductBenefit().setTreatmentLocation(treatmentLocation);
			} else {
				
			}

			Integer itemCategoryId = WebUtil.getParameterInteger(request,
					"itemCategoryId");

			if (itemCategoryId != null) {
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId("" + itemCategoryId);

				ItemCategory itemCategory = this.itemCategoryService
						.get(itemCategoryId);
				tmp.getProductBenefit().setItemCategoryId(itemCategory);
			} else {
				
			}

			Integer caseCategoryId = WebUtil.getParameterInteger(request,
					"caseCategoryId");

			if (caseCategoryId != null) {
				CaseCategory forClass = new CaseCategory();
				forClass.setCaseCategoryId(caseCategoryId);
				tmp.setCaseCategoryId("" + caseCategoryId);

				CaseCategory caseCategory = this.caseCategoryService
						.get(caseCategoryId);
				tmp.getProductBenefit().setCaseCategoryId(caseCategory);
			} else {
				
			}

			// -- foreign affairs end

		}
		
		if (productLayerId != null){
			ProductLayerLimit limitLayer = productLayerService.get(productLayerId);
			
			if (limitLayer != null){
				tmp.setProductLayerId(productLayerId.toString());
				tmp.setLayerName(limitLayer.getLayerLevel().toString());
				if (limitLayer.getDiagnosisSetId() != null){
					tmp.setLayerType("SMM");
				}
				else {
					tmp.setLayerType("Multi Layer");
				}
			}
		}

		String breadcrumb = "<a href=\"productbenefit?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Benefit</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Benefit";
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("productId", productId);

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		Integer productId = WebUtil.getParameterInteger(request, "productId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		ProductBenefitForm productBenefitForm = (ProductBenefitForm) command;
		ProductBenefit productBenefit = productBenefitForm.getProductBenefit();
		
		errors.printStackTrace();
		
		String breadcrumb = "<a href=\"productbenefit?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Benefit</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Benefit";
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("productId", productId);

		// errors.setNestedPath("productBenefit");
		// getValidator().validate(productBenefit, errors);
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

		Collection<DiscountUsageType> discountCollection = discountUsageTypeService.getAll();
		Collection<BenefitUsageType> benefitUsageTypeCollection = benefitUsageTypeService.getAll();
		Collection<TreatmentLocation> locationCollection = treatmentLocationService.getAll();
		Collection<CaseCategory> caseCategoryCollection = caseCategoryService.getAll();
		Collection<MeasurementUnit> measuramentCollection = measurementUnitService.getAll();
		
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		
		model.put("productId", productId);
		
		model.put("discountUsageTypes", discountCollection);
		model.put("benefitUsageTypes", benefitUsageTypeCollection);
		model.put("locations", locationCollection);
		model.put("caseCategories", caseCategoryCollection);
		model.put("measurementUnits", measuramentCollection);
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		Integer productId = WebUtil.getParameterInteger(request, "productId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		ProductBenefitForm productBenefitForm = (ProductBenefitForm) command;

		ProductBenefit res = null;
		String alertMsg = "";
		ModelAndView result = null;
		
		try {
			
			

			if (productBenefitForm.getBenefitUsageType() != null) {
				productBenefitForm.getProductBenefit().setBenefitUsageType(
						this.benefitUsageTypeService.get(new Integer(
								(productBenefitForm.getBenefitUsageType()))));
			}

			if (productBenefitForm.getProductId() != null) {
				productBenefitForm.getProductBenefit().setProductId(
						this.productService.get(new Integer((productBenefitForm
								.getProductId()))));
			}

			if (productBenefitForm.getMeasurementUnit() != null) {
				productBenefitForm.getProductBenefit().setMeasurementUnit(
						this.measurementUnitService.get(new Integer(
								(productBenefitForm.getMeasurementUnit()))));
			}

			if (productBenefitForm.getTreatmentLocation() != null) {
				productBenefitForm.getProductBenefit().setTreatmentLocation(
						this.treatmentLocationService.get(new Integer(
								(productBenefitForm.getTreatmentLocation()))));
			}

			if (productBenefitForm.getItemCategoryId() != null && !productBenefitForm.getItemCategoryId().equalsIgnoreCase("")) {
				productBenefitForm.getProductBenefit().setItemCategoryId(
						this.itemCategoryService.get(new Integer(
								(productBenefitForm.getItemCategoryId()))));
			}

			if (productBenefitForm.getCaseCategoryId() != null) {
				productBenefitForm.getProductBenefit().setCaseCategoryId(
						this.caseCategoryService.get(new Integer(
								(productBenefitForm.getCaseCategoryId()))));
			}

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (productBenefitForm.isNewProductBenefit()) {

				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEPRODUCTBENEFIT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATEPRODUCTBENEFIT access");
					return errorResult;

				}
				res = productBenefitService.create(productBenefitForm
						.getProductBenefit(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.productbenefit", null, "success",
							Locale.getDefault());
					
					if (res.getProductLayerId() == null){
						result = new ModelAndView(new RedirectView("productbenefit?navigation=list&alert="+alertMsg+"&productId="+res.getProductId().getProductId()));
					}
					else {
						result = new ModelAndView(new RedirectView("product?navigation=detail&alert="+alertMsg+"&productId="+res.getProductId().getProductId()));
					}
					
					
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.productbenefit", null, "fail", Locale
									.getDefault());
					result = new ModelAndView(new RedirectView("productbenefit?navigation=list&productId="+res.getProductId().getProductId()));
					result.addObject("alert",alertMsg);
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEPRODUCTBENEFIT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATEPRODUCTBENEFIT access");
					return errorResult;

				}
				res = productBenefitService.update(productBenefitForm
						.getProductBenefit(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.productbenefit", null, "success",
							Locale.getDefault());
					
					if (res.getProductLayerId() == null){
						result = new ModelAndView(new RedirectView("productbenefit?navigation=list&alert="+alertMsg+"&productId="+res.getProductId().getProductId()));
					}
					else {
						result = new ModelAndView(new RedirectView("product?navigation=detail&alert="+alertMsg+"&productId="+res.getProductId().getProductId()));
					}
					
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.productbenefit", null, "fail", Locale
									.getDefault());
					result = new ModelAndView(new RedirectView("productbenefit?navigation=list&productId="+res.getProductId().getProductId()));
					result.addObject("alert",alertMsg);
				}

			}
		} catch (Exception ex) {
			if (productBenefitForm.isNewProductBenefit()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.productbenefit", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.productbenefit", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "<a href=\"productbenefit?navigation="+navigation+"&productId="+productId+"\" class=\"linkbreadcrumb\">List Product Benefit</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Benefit";
		request.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("navigation", navigation);
		request.setAttribute("productId", productId);
		
		return result;
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
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,req.getLocale());
		
			
		System.out.println("LOCALE : " + req.getLocale());
		

		CustomNumberEditor intNum = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, intNum);
		
		
        
		binder.registerCustomEditor(java.lang.Double.class,
                new CustomNumberEditor (java.lang.Double.class, nf,true));
	}
	// class+

	// class-

}
