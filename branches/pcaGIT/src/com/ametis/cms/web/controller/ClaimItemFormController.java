package com.ametis.cms.web.controller;

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
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.MeasurementUnitService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.ClaimItemForm;

// imports+ 

// imports- 

/**
 * ClaimItem is a mapping of claim_item Table.
 */
public class ClaimItemFormController extends SimpleFormController
// extends+

// extends-

{

	ClaimItemService claimItemService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	ItemService itemService;

	Integer countSet;
	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	private SecurityService securityService;

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public Integer getCountSet() {
		return countSet;
	}

	public void setCountSet(Integer countSet) {
		this.countSet = countSet;
	}

	public void setItemService(ItemService obj) {
		this.itemService = obj;
	}

	public ItemService getItemService() {
		return this.itemService;
	}

	ClaimService claimService;

	public void setClaimService(ClaimService obj) {
		this.claimService = obj;
	}

	public ClaimService getClaimService() {
		return this.claimService;
	}

	MeasurementUnitService measurementUnitService;

	public void setMeasurementUnitService(MeasurementUnitService obj) {
		this.measurementUnitService = obj;
	}

	public MeasurementUnitService getMeasurementUnitService() {
		return this.measurementUnitService;
	}

	// -- foreign affairs end

	public void setClaimItemService(ClaimItemService object) {
		this.claimItemService = object;
	}

	public ClaimItemService getClaimItemService() {
		return this.claimItemService;
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

	public ClaimItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ClaimItemForm tmp = null;
		Integer claimItemId = WebUtil.getParameterInteger(request,
				"claimItemId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (claimItemId != null) {
			java.io.Serializable pkey = claimItemId;
			ClaimItem object = claimItemService.get(pkey);

			if (object != null) { // edit object

				tmp = new ClaimItemForm(object);
				// foreign affairs

				if (object.getItemId() != null){
					Item item = itemService.get(object.getItemId().getItemId());
					
					if (item != null){
						tmp.setItemId(object.getItemId().getItemId().toString());
						tmp.setItemName(item.getItemName());
					}
				}
				
				Claim claim = claimService.get(object.getClaimId().getClaimId());
				
				if (claim != null){
					tmp.setClaimId(claim.getClaimId().toString());			
					tmp.setClaimNumber(claim.getClaimNumber());
					tmp.setMemberId(claim.getMemberId().getMemberId().toString());
				}


				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClaimItemForm();
				// foreign affairs

				Integer itemId = WebUtil.getParameterInteger(request, "itemId");

				if (itemId != null) {

					Item item = this.itemService.get(itemId);
					tmp.getClaimItem().setItemId(item);
					
					tmp.setItemId(item.getItemId().toString());
					tmp.setItemName(item.getItemName());
					
				} else {
				}

				Integer claimId = WebUtil.getParameterInteger(request,
						"claimId");

				if (claimId != null) {
					// Claim forClass = new Claim();
					// forClass.setClaimId(claimId);
					// tmp.setClaimId(""+claimId);

					Claim claim = this.claimService.get(claimId);
					tmp.getClaimItem().setClaimId(claim);
					tmp.setClaimNumber(claim.getClaimNumber());
				} 

		
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClaimItemForm();
			// foreign affairs

			Integer itemId = WebUtil.getParameterInteger(request, "itemId");

			if (itemId != null) {

				Item item = this.itemService.get(itemId);
				tmp.getClaimItem().setItemId(item);
				
				tmp.setItemId(item.getItemId().toString());
				tmp.setItemName(item.getItemName());
			}

			Integer claimId = WebUtil.getParameterInteger(request, "claimId");

			if (claimId != null) {
				// Claim forClass = new Claim();
				// forClass.setClaimId(claimId);
				// tmp.setClaimId(""+claimId);

				Claim claim = this.claimService.get(claimId);
				tmp.getClaimItem().setClaimId(claim);
				
				tmp.setClaimId(claim.getClaimId().toString());
				tmp.setClaimNumber(claim.getClaimNumber());
			}

			

			// -- foreign affairs end

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ClaimItemForm claimItemForm = (ClaimItemForm) command;
		ClaimItem claimItem = claimItemForm.getClaimItem();

		System.out.println("ERROR : " + errors);
		// errors.setNestedPath("claimItem");
		// getValidator().validate(claimItem, errors);
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
		Collection measurementUnit = measurementUnitService.getAll();
		model.put("measurementUnit", measurementUnit);
		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClaimItemForm claimItemForm = (ClaimItemForm) command;

		ClaimItem res = null;
		String alertMsg = "";
		String url = "";
		try {
			// foreign affairs

			if (claimItemForm.isNewClaimItem()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATECLAIMITEM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATECLAIMITEM access");
					return errorResult;

				}
				res = claimItemService.create(claimItemForm.getClaimItem(),
						user);
				url = "&index=0&countSet=" + countSet + "&count=";

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.claimitem", null, "success", Locale
									.getDefault());

				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.claimitem", null, "fail", Locale
									.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATECLAIMITEM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATECLAIMITEM access");
					return errorResult;

				}
				ClaimItem ci = claimItemForm.getClaimItem();
				res = claimService.updateClaimItem(ci, user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.claimitem", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.claimitem", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (claimItemForm.isNewClaimItem()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.claimitem", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.claimitem", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("claimitem?alert=" + alertMsg
				+ "&claimId=" + claimItemForm.getClaimId()
				+ "&memberId="
				+ claimItemForm.getMemberId()));
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
