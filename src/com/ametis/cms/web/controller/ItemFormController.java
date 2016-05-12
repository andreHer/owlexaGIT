package com.ametis.cms.web.controller;

import java.text.NumberFormat;
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
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ItemForm;

// imports+ 

// imports- 

/**
 * Item is a mapping of item Table.
 */
public class ItemFormController extends SimpleFormController
// extends+

// extends-

{

	ItemService itemService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	ItemCategoryService itemCategoryService;
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

	public void setItemCategoryService(ItemCategoryService obj) {
		this.itemCategoryService = obj;
	}

	public ItemCategoryService getItemCategoryService() {
		return this.itemCategoryService;
	}

	// -- foreign affairs end

	public void setItemService(ItemService object) {
		this.itemService = object;
	}

	public ItemService getItemService() {
		return this.itemService;
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

	public ItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ItemForm tmp = null;
		Integer itemId = WebUtil.getParameterInteger(request, "itemId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (itemId != null) {
			java.io.Serializable pkey = itemId;
			Item object = itemService.get(pkey);

			if (object != null) { // edit object

				tmp = new ItemForm(object);
				// foreign affairs

				tmp.setItemCategoryId(object.getItemCategoryId());

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ItemForm();

				// foreign affairs

				Integer itemCategoryId = WebUtil.getParameterInteger(request,
						"itemCategoryId");

				if (itemCategoryId != null) {
					ItemCategory forClass = new ItemCategory();
					forClass.setItemCategoryId(itemCategoryId);
					tmp.setItemCategoryId(forClass);

					ItemCategory itemCategory = this.itemCategoryService
							.get(itemCategoryId);
					tmp.getItem().setItemCategoryId(itemCategory);
				} else {
					ItemCategory forClass = new ItemCategory();
					// tmp.setItemCategoryId("");
					tmp.getItem().setItemCategoryId(forClass);
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ItemForm();
			// foreign affairs

			Integer itemCategoryId = WebUtil.getParameterInteger(request,
					"itemCategoryId");

			if (itemCategoryId != null) {
				ItemCategory forClass = new ItemCategory();
				forClass.setItemCategoryId(itemCategoryId);
				tmp.setItemCategoryId(forClass);

				ItemCategory itemCategory = this.itemCategoryService
						.get(itemCategoryId);
				tmp.getItem().setItemCategoryId(itemCategory);
			} else {
				ItemCategory forClass = new ItemCategory();
				// tmp.setItemCategoryId("");
				tmp.getItem().setItemCategoryId(forClass);
			}

			// -- foreign affairs end
			tmp.setItemDefaultValue("0");

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ItemForm itemForm = (ItemForm) command;
		Item item = itemForm.getItem();

		errors.printStackTrace();
		// errors.setNestedPath("item");
		// getValidator().validate(item, errors);
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

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ItemForm itemForm = (ItemForm) command;

		Item res = null;
		String alertMsg = "";

		String navigation = WebUtil.getParameterString(request, "navigation", "");
		ModelAndView result = null;

		try {
			// foreign affairs

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (itemForm.isNewItem()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATEITEM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATEITEM access");
					return errorResult;

				}
				res = itemService.create(itemForm.getItem(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.item", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.item",
							null, "fail", Locale.getDefault());
				}
				
				if (navigation.equalsIgnoreCase("simpanadd")){
					request.setAttribute("alert", alertMsg);
					result = new ModelAndView(new RedirectView("item-form?navigation=tambah&alert="+alertMsg));
						
				}
				else {
					result = new ModelAndView(new RedirectView( "item?navigation=detail&itemId="+res.getItemId()+"&alert="+alertMsg));
				}
				
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATEITEM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATEITEM access");
					return errorResult;

				}
				res = itemService.update(itemForm.getItem(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.item", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.item",
							null, "fail", Locale.getDefault());
				}
				result = new ModelAndView(new RedirectView("item?navigation=detail&itemId="+res.getItemId()+"&alert="+alertMsg));
				
			}
		} catch (Exception ex) {
			if (itemForm.isNewItem()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.item", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.item", null, "fail", Locale.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
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
		CustomNumberEditor numInt = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, numInt);

		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		binder.registerCustomEditor(java.lang.Double.class,
				new CustomNumberEditor(java.lang.Double.class, nf, true));
	}
	// class+

	// class-

}
