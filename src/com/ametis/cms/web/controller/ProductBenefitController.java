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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductBenefit;
import com.ametis.cms.datamodel.ProductLayerLimit;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ProductBenefitService;
import com.ametis.cms.service.ProductLayerLimitService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.util.servlet.TableRenderingServlet;

// imports+ 

// imports- 

/**
 * ProductBenefit is a servlet controller for product_benefit Table. All you
 * have to do is to convert necessary data field to the named parameter
 */
public class ProductBenefitController implements Controller

// extends+

// extends-

{

	private ProductBenefitService productBenefitService;
	private ProductLayerLimitService productLayerLimitService;

	private UserService userService;

	private ProductService productService;
	private ResourceBundleMessageSource alertProperties;
	private Integer countSet;
	private Integer maxPercountSet;

	SecurityService securityService;

	private ActivityLogService logService;

	public ProductLayerLimitService getProductLayerLimitService() {
		return productLayerLimitService;
	}

	public void setProductLayerLimitService(ProductLayerLimitService productLayerLimitService) {
		this.productLayerLimitService = productLayerLimitService;
	}

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

	public void setProductBenefitService(ProductBenefitService productBenefitService) {
		this.productBenefitService = productBenefitService;
	}

	public ProductBenefitService getProductBenefitService() {
		return this.productBenefitService;
	}

	public ModelAndView deletePerformed(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView result = null;

		try {
			Integer productBenefitId = WebUtil.getParameterInteger(request, "productBenefitId");

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */
			java.io.Serializable pkey = productBenefitId;

			ActionUser user = securityService.getActionUser(request);

			boolean isUserAuthorized = securityService.isUserAuthorized(user, "DELETEPRODUCTBENEFIT");

			if (!isUserAuthorized) {

				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));
				errorResult.addObject("errorType", "accessDenied");
				errorResult.addObject("errorMessage", "You Are Not Authorized for DELETEPRODUCTBENEFIT access");
				return errorResult;

			}
			ProductBenefit res = productBenefitService.delete(pkey, user);

			if (res != null && res.getDeletedStatus().intValue() == 1) {
				request.setAttribute("alert", alertProperties.getMessage("success.delete.productbenefit", null,
						"success", Locale.getDefault()));

				result = new ModelAndView(new RedirectView(
						"productbenefit?navigation=list&productId=" + res.getProductId().getProductId()));

			} else {
				request.setAttribute("alert",
						alertProperties.getMessage("fail.delete.productbenefit", null, "fail", Locale.getDefault()));

				result = new ModelAndView(new RedirectView(
						"productbenefit?navigation=list&productId=" + res.getProductId().getProductId()));
			}

		} catch (Exception e) {
			// nanti kalo sudah OK codenya e.printStackTrace nya di hapus saja
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));
			result = new ModelAndView("error");
		}
		return result;
	}

	public ModelAndView detailPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;

		try {
			Integer productBenefitId = WebUtil.getParameterInteger(request, "productBenefitId");
			Integer productId = WebUtil.getParameterInteger(request, "productId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request, "searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby", "");

			result = new ModelAndView(location);
			java.io.Serializable pkey = productBenefitId;
			ProductBenefit object = productBenefitService.get(pkey);

			if (object == null) {
				request.setAttribute("alert",
						alertProperties.getMessage("not.found.productbenefit", null, "fail", Locale.getDefault()));
			}

			result.addObject("productBenefit", object);
			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("searchby", searchby);
			result.addObject("productId", productId);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView lookupJsonPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;

		try {
			Integer productId = WebUtil.getParameterInteger(request, "productId");
			Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");

			Integer index = WebUtil.getParameterInteger(request, "index");
			String searchtext = WebUtil.getParameterString(request, "q", "");

			result = new ModelAndView("lookupProductBenefitJson");

			String[] eqParam = { "deletedStatus", "productId.productId", "caseCategoryId.caseCategoryId" };
			Object[] eqValue = { Integer.valueOf(0), productId, caseCategoryId };

			String[] likeParam = { "itemCategoryId.itemCategoryName" };
			Object[] likeValue = { searchtext };

			Collection<ProductBenefit> benefitList = productBenefitService.search(likeParam, likeValue, eqParam,
					eqValue, 0, 10);

			result.addObject("ProductBenefitList", benefitList);

			result.addObject("index", index);
			result.addObject("searchtext", searchtext);
			result.addObject("productId", productId);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	private boolean validateProductBenefitInput(String[] productBenefitId, String[] benefitLimit,
			HttpServletRequest request) {

		boolean isError = false;

		try {
			String alert = "";
			if (benefitLimit != null) {

				boolean isAlerted = false;
				for (int i = 0; i < benefitLimit.length; i++) {

					try {
						Double doubleVal = Double.parseDouble(benefitLimit[i]);
					} catch (Exception e) {
						isError = true;
						e.printStackTrace();
						if (!isAlerted) {
							alert += "Invalid Amount Value" + "\n\n";
							isAlerted = true;
						}

					}

				}

			}

			request.setAttribute("alert", alert);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isError;
	}

	public ModelAndView addTemplatePerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request, "navigation", "");

			Integer productId = WebUtil.getParameterInteger(request, "productId");
			// Integer productReferenceId = WebUtil.getParameterInteger(request,
			// "reference");
			result = new ModelAndView(location);
			result.addObject("productId", productId);

			if (navigation.equalsIgnoreCase("addtemplate-form")) {

				Product product = productService.get(productId);

				if (product != null) {

					if (product.getProductReference() != null) {
						String[] eqParam = { "productId.productId", "deletedStatus" };
						Object[] eqValue = { product.getProductReference().getProductId(), Integer.valueOf(0) };

						String[] eqParamLayer = { "productId.productId", "deletedStatus", "productLayerId.layerLevel" };
						Object[] eqValueLayer1 = { product.getProductReference().getProductId(), Integer.valueOf(0),
								1 };
						Object[] eqValueLayer2 = { product.getProductReference().getProductId(), Integer.valueOf(0),
								2 };
						Object[] eqValueLayer3 = { product.getProductReference().getProductId(), Integer.valueOf(0),
								3 };

						Collection<ProductBenefit> benefitUtama = new Vector<ProductBenefit>();

						Collection<ProductBenefit> productBenefitCollection = productBenefitService.search(null, null,
								eqParam, eqValue, 0, 200);

						for (Iterator iterator = productBenefitCollection.iterator(); iterator.hasNext();) {
							ProductBenefit productBenefit = (ProductBenefit) iterator.next();
							if (productBenefit != null && productBenefit.getProductLayerId() == null) {
								benefitUtama.add(productBenefit);
							}

						}

						result.addObject("productBenefitCollection", benefitUtama);
						Collection<ProductBenefit> layer1Collection = productBenefitService.search(null, null,
								eqParamLayer, eqValueLayer1, 0, 100);

						result.addObject("productBenefitLayer1Collection", layer1Collection);

						Collection<ProductBenefit> layer2Collection = productBenefitService.search(null, null,
								eqParamLayer, eqValueLayer2, 0, 100);

						result.addObject("productBenefitLayer2Collection", layer2Collection);

						Collection<ProductBenefit> layer3Collection = productBenefitService.search(null, null,
								eqParamLayer, eqValueLayer3, 0, 100);

						result.addObject("productBenefitLayer3Collection", layer3Collection);

					}
				}
			} else if (navigation.equalsIgnoreCase("addtemplate-save")) {

				String[] productBenefitId = request.getParameterValues("productBenefitId");
				String[] benefitLimit = request.getParameterValues("benefitLimit");
				String[] benefitReimburseLimit = request.getParameterValues("reimburseBenefitLimit");
				String[] benefitPerCase = request.getParameterValues("benefitPerCase");
				String[] deductible = request.getParameterValues("deductible");
				String[] layer = request.getParameterValues("layer");

				boolean isError = validateProductBenefitInput(productBenefitId, benefitLimit, request);

				if (isError) {

					result.addObject("navigation", "addtemplate-save");
					result.addObject("productId", productId);

				} else {

					Product product = productService.get(productId);

					if (product != null) {

						Collection<ProductBenefit> benefits = new Vector();
						ProductBenefit benefit = null;
						ProductLayerLimit limitLayer1 = productLayerLimitService.getProductLayer(productId, 1);
						ProductLayerLimit limitLayer2 = productLayerLimitService.getProductLayer(productId, 2);
						ProductLayerLimit limitLayer3 = productLayerLimitService.getProductLayer(productId, 3);
						
						

						if (productBenefitId != null && benefitLimit != null) {
							if (productBenefitId.length == benefitLimit.length) {

								for (int i = 0; i < productBenefitId.length; i++) {

									benefit = new ProductBenefit();
									benefit.setProductBenefitId(Integer.valueOf(productBenefitId[i]));

									if (benefitLimit[i] != null && !benefitLimit[i].equalsIgnoreCase("")) {
										benefit.setBenefitLimit(Double.valueOf(benefitLimit[i]));
									}
									if (benefitReimburseLimit[i] != null
											&& !benefitReimburseLimit[i].equalsIgnoreCase("")) {
										benefit.setReimbursementBenefitLimit(Double.valueOf(benefitReimburseLimit[i]));
									}
									if (benefitPerCase[i] != null && !benefitPerCase[i].equalsIgnoreCase("")) {
										benefit.setMaxBenefitPerCase(Double.valueOf(benefitPerCase[i]));
									}
									if (deductible[i] != null && !deductible[i].equalsIgnoreCase("")) {
										benefit.setDeductibleLimit(Double.valueOf(deductible[i]));
									}
									if (layer[i] != null && !layer[i].equalsIgnoreCase("")) {
										if (layer[i].equalsIgnoreCase("1")) {
											if (limitLayer1 != null){
												benefit.setProductLayerId(limitLayer1);
											}
										}
										if (layer[i].equalsIgnoreCase("2")) {
											if (limitLayer2 != null){
												benefit.setProductLayerId(limitLayer2);
											}
										}
										if (layer[i].equalsIgnoreCase("3")) {
											if (limitLayer3 != null){
												benefit.setProductLayerId(limitLayer3);
											}
										}
									}

									benefits.add(benefit);

								}
							}
						}

						productBenefitService.createProductBenefit(benefits, product, user);

					}

					result = new ModelAndView(new RedirectView("product?navigation=detail&productId=" + productId));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView configEDCPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;

		try {

			ActionUser user = securityService.getActionUser(request);

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request, "navigation", "");

			Integer productId = WebUtil.getParameterInteger(request, "productId");
			result = new ModelAndView(location);
			result.addObject("productId", productId);

			if (navigation.equalsIgnoreCase("configedc")) {

				Product product = productService.get(productId);

				if (product != null) {

					String[] eqParam = { "productId.productId", "deletedStatus" };
					Object[] eqValue = { productId, Integer.valueOf(0) };
					Collection<ProductBenefit> productBenefitCollection = productBenefitService.search(null, null,
							eqParam, eqValue, 0, 200);

					result.addObject("productBenefitCollection", productBenefitCollection);

				}
			} else if (navigation.equalsIgnoreCase("configedc-save")) {

				String[] productBenefitId = request.getParameterValues("productBenefitId");
				ActionUser actionUser = securityService.getActionUser(request);

				if (productBenefitId != null) {

					for (int i = 0; i < productBenefitId.length; i++) {

						String edcStatus = request.getParameter("edc_" + productBenefitId[i]);
						String publishedStatus = request.getParameter("published_" + productBenefitId[i]);

						ProductBenefit benefit = productBenefitService.get(Integer.valueOf(productBenefitId[i]));

						if (benefit != null) {

							Integer edcEnabled = 0;
							Integer benefitShowedOnEdc = 0;

							if (edcStatus != null && edcStatus.equals("yes")) {
								edcEnabled = 1;
							}
							if (publishedStatus != null && publishedStatus.equals("yes")) {
								benefitShowedOnEdc = 1;
							}

							benefit.setBenefitShowedOnEdc(benefitShowedOnEdc);
							benefit.setIsEdcEnabled(edcEnabled);

							productBenefitService.update(benefit, actionUser);
						}

					}
				}

				result = new ModelAndView(new RedirectView("product?navigation=list&productId=" + productId));

			}

			/*
			 * Pkey ini merupakan representasi dari primary key, misalkan hasil
			 * get dari attribute diatas ^ itu cuma ada 1 key saja berarti
			 * tinggal pkey = <nama var diatas> tapi kalau misalkan composite
			 * primary key berarti tinggal bikin representasi primary key nya
			 * saja .
			 */

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView searchPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request, "navigation", "");
			String searchtext = WebUtil.getParameterString(request, "searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby", "");
			String sortby = WebUtil.getParameterString(request, "sortby", "");

			Integer productId = WebUtil.getParameterInteger(request, "productId");
			Integer productLayerId = WebUtil.getParameterInteger(request, "productLayerId");

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

					if (searchby.equalsIgnoreCase("benefitName")) {
						vLikeP.add("itemCategoryId.itemCategoryName");
						vLikeQ.add(searchtext);
					}
				}

			}
			if (productId != null) {
				vEqP.add("productId.productId");
				vEqQ.add(productId);

				Product product = productService.get(productId);
				result.addObject("product", product);
			}

			if (productLayerId != null) {
				vEqP.add("productLayerId.productLayerLimitId");
				vEqQ.add(productLayerId);
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

			count = productBenefitService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

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
			else if (index.compareTo(new Integer(count / countSet.intValue() + 1)) == new Integer(1).intValue())
				index = new Integer(count / countSet.intValue() + 1);

			rowsetint = (new Integer((index.intValue() - 1) * countSet.intValue())).intValue();
			if (count % countSet.intValue() > 0) {
				result.addObject("halAkhir", new Integer(count / countSet.intValue() + 1));
			} else {
				result.addObject("halAkhir", new Integer(count / countSet.intValue()));
			}

			minIndex = (index - 1) * countSet;
			maxIndex = index * countSet;

			if (maxIndex > count) {
				maxIndex = count;
			}

			String required[] = new String[] {
					// foreign affairs
					"ProductBenefit.DiscountUsageType", "ProductBenefit.BenefitUsageType", "ProductBenefit.ProductId",
					"ProductBenefit.MeasurementUnit", "ProductBenefit.TreatmentLocation",
					"ProductBenefit.ItemCategoryId", "ProductBenefit.CaseCategoryId",
					// foreign affairs end
			};
			collection = productBenefitService.search(sLikeP, sLikeQ, sEqP, sEqQ, required, rowsetint,
					countSet.intValue());

			if (collection.size() <= 0) {
				index = new Integer(index.intValue() - 1);
				if (index.compareTo(new Integer(1)) == new Integer(-1).intValue())
					index = new Integer(1);
				else if (index.compareTo(new Integer(count / countSet.intValue() + 1)) == new Integer(1).intValue())
					index = new Integer(count / countSet.intValue() + 1);

				rowsetint = (new Integer((index.intValue() - 1) * countSet.intValue())).intValue();
				if (count % countSet.intValue() > 0) {
					result.addObject("halAkhir", new Integer(count / countSet.intValue() + 1));
				} else {
					result.addObject("halAkhir", new Integer(count / countSet.intValue()));
				}
				collection = productBenefitService.search(sLikeP, sLikeQ, sEqP, sEqQ, required, rowsetint,
						countSet.intValue());
			} else {

			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);
			request.setAttribute("productId", productId);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			result.addObject("ProductBenefits", collection);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(index));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	public ModelAndView listPerformed(HttpServletRequest request, HttpServletResponse response, String location)
			throws Exception {
		ModelAndView result = null;
		try {
			result = new ModelAndView(location);

			String rowset = WebUtil.getParameterString(request, "rowset", "0");

			Integer index = WebUtil.getParameterInteger(request, "index");

			String navigation = WebUtil.getParameterString(request, "navigation", "");
			String searchtext = WebUtil.getParameterString(request, "searchtext", "");
			String searchby = WebUtil.getParameterString(request, "searchby", "");
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
			if (navigation.equals("list") || navigation.equals("golookuplist")) {
				if (searchby != null) {
					if (searchby.equalsIgnoreCase("benefitName")) {
						vLikeP.add("itemCategoryId.itemCategoryName");
						vLikeQ.add(searchtext);
					} else if (searchby.equalsIgnoreCase("benefitCode")) {
						vLikeP.add("itemCategoryId.itemCategoryCode");
						vLikeQ.add(searchtext);
					}
				}
			}
			if (productId != null) {
				vEqP.add("productId.productId");
				vEqQ.add(productId);

				Product product = productService.get(productId);
				result.addObject("product", product);
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

			count = productBenefitService.getTotal(sLikeP, sLikeQ, sEqP, sEqQ);

			String required[] = new String[] {
					// foreign affairs
					"ProductBenefit.DiscountUsageType", "ProductBenefit.BenefitUsageType", "ProductBenefit.ProductId",
					"ProductBenefit.MeasurementUnit", "ProductBenefit.TreatmentLocation",
					"ProductBenefit.ItemCategoryId", "ProductBenefit.CaseCategoryId",
					// foreign affairs end
			};
			collection = productBenefitService.search(sLikeP, sLikeQ, sEqP, sEqQ, required, 0, count);

			if (collection.size() <= 0) {

				collection = productBenefitService.search(sLikeP, sLikeQ, sEqP, sEqQ, required, 0, count);
			} else {

			}

			request.setAttribute("searchtext", searchtext);
			request.setAttribute("searchby", searchby);
			request.setAttribute("navigation", navigation);

			/*
			 * ini gue generate juga dari nama tablenya convention -->
			 * collection = nama bean + 's' -adhit
			 */

			Product productObject = null;

			if (productId != null) {
				try {
					java.io.Serializable productpkey = productId;
					System.out.println("product id : " + productId);
					String[] productRequired = { "Product.ClientId", "Product.ProductLimitType", "Product.ProductType",
							"Product.CaseCategory", "Product.PolicyId", "Product.PolicyId.ClientId",
							"Product.ProductStatus", "Product.SharedProductId", "Product.ProductCurrencyId" };
					productObject = productService.get(productpkey, productRequired);

				} catch (Exception ex) {
					// System.out.println("member group object :
					// "+productObject.getAddress());
					System.out.println(ex.getMessage());
					ex.printStackTrace();
				}
			}

			result.addObject("ProductBenefits", collection);
			result.addObject("product", productObject);

			request.setAttribute("countSet", countSet);
			request.setAttribute("index", new Integer(0));
			request.setAttribute("count", new Integer(count));
			request.setAttribute("alert", request.getParameter("alert"));
			request.setAttribute("minIndex", new Integer(minIndex));
			request.setAttribute("maxIndex", new Integer(maxIndex));

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("alert",
					alertProperties.getMessage("system.error " + e.getMessage(), null, "fail", Locale.getDefault()));

			result = new ModelAndView("error");
		}

		return result;
	}

	/**
	 * Action Service
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get paramater navigation
		String navigation = request.getParameter("navigation") == null ? "welcome" : request.getParameter("navigation");
		Integer index = WebUtil.getParameterInteger(request, "index");
		String searchtext = WebUtil.getParameterString(request, "searchtext", "");
		String searchby = WebUtil.getParameterString(request, "searchby", "");
		Integer productId = WebUtil.getParameterInteger(request, "productId");

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
		 * result = new ModelAndView ("login"); } else { }
		 */
		String breadcrumb = "";
		try {
			if (navigation.equalsIgnoreCase("detail")) {
				/*
				 * disesuaikan dengan halaman targetnya nih
				 */
				result = detailPerformed(request, response, "detailProductBenefit");
			} else if (navigation.equalsIgnoreCase("delete")) {
				result = deletePerformed(request, response);
			} else if (navigation.equalsIgnoreCase("search") || navigation.equals("gosearch")) {
				result = searchPerformed(request, response, "listProductBenefit");
			} else if (navigation.equalsIgnoreCase("lookup") || navigation.equals("golookup")) {
				result = searchPerformed(request, response, "lookupProductBenefit");
			} else if (navigation.equalsIgnoreCase("lookupjson")) {
				result = lookupJsonPerformed(request, response, "lookupProductBenefitJson");
			} else if (navigation.equalsIgnoreCase("addtemplate-form")
					|| navigation.equalsIgnoreCase("addtemplate-save")) {
				result = addTemplatePerformed(request, response, "editProductBenefitTemplate");
			} else if (navigation.equalsIgnoreCase("configedc") || navigation.equalsIgnoreCase("configedc-save")) {
				result = configEDCPerformed(request, response, "editProductBenefitEDC");
				breadcrumb = "<a href=\"productbenefit?navigation=" + navigation + "&arah=&index=" + index
						+ "&benefitId=&productId=" + productId + "&productBenefitId=&searchtext=&searchby=" + searchby
						+ "\" class=\"linkbreadcrumb\">Configure EDC</a>";
			} else if (navigation.equalsIgnoreCase("list") || navigation.equals("golookuplist")) {
				result = listPerformed(request, response, "listProductBenefit");
				breadcrumb = "<a href=\"product?navigation=detail&productId=" + productId
						+ "\" class=\"linkbreadcrumb\">Detail Product</a>  &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;List Product Benefit";
				result.addObject("productId", productId);
			} else {
				result = searchPerformed(request, response, "searchProductBenefit");
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
