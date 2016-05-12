package com.ametis.cms.web.controller;

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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.datamodel.CaseItem;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseEventService;
import com.ametis.cms.service.CaseItemService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CaseItemForm;

// imports+ 

// imports- 

/**
 * CaseItem is a mapping of case_item Table.
 */
public class CaseItemFormController extends SimpleFormController
// extends+

// extends-

{

	CaseItemService caseItemService;
	ResourceBundleMessageSource alertProperties;
	private ItemService itemService;
	private ClaimItemService claimItemService;
	private CaseService myCaseService;
	private ClaimService claimService;

	CaseEventService caseEventService;
	private SecurityService securityService;
	
	public void setCaseService(CaseService object) {
		this.myCaseService = object;
	}

	public CaseService getCaseService() {
		return this.myCaseService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	public void setCaseEventService(CaseEventService obj) {
		this.caseEventService = obj;
	}

	public CaseEventService getCaseEventService() {
		return this.caseEventService;
	}

	private ActivityLogService logService;

	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	// -- foreign affairs end

	public void setCaseItemService(CaseItemService object) {
		this.caseItemService = object;
	}

	public CaseItemService getCaseItemService() {
		return this.caseItemService;
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

	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}

	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public CaseItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CaseItemForm tmp = null;

		Integer caseItemId = WebUtil.getParameterInteger(request, "caseItemId");
		Integer caseId = WebUtil.getParameterInteger(request, "caseId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (caseItemId != null) {
			java.io.Serializable pkey = caseItemId;
			CaseItem object = caseItemService.get(pkey);

			if (object != null) { // edit object

				tmp = new CaseItemForm(object);
				// foreign affairs

				if (object.getCaseEventId() != null){
					tmp.setCaseEventId(""
						+ object.getCaseEventId().getCaseEventId());
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseItemForm();
				// foreign affairs

				Integer caseEventId = WebUtil.getParameterInteger(request,
						"caseEventId");

				if (caseEventId != null) {
					CaseEvent forClass = new CaseEvent();
					forClass.setCaseEventId(caseEventId);
					tmp.setCaseEventId("" + caseEventId);

					CaseEvent caseEvent = this.caseEventService
							.get(caseEventId);
					tmp.getCaseItem().setCaseEventId(caseEvent);
				} else {

				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CaseItemForm();
			// foreign affairs

			Integer caseEventId = WebUtil.getParameterInteger(request,
					"caseEventId");

			if (caseEventId != null) {
				CaseEvent forClass = new CaseEvent();
				forClass.setCaseEventId(caseEventId);
				tmp.setCaseEventId("" + caseEventId);

				CaseEvent caseEvent = this.caseEventService.get(caseEventId);
				tmp.getCaseItem().setCaseEventId(caseEvent);
			} else {
			}
		}

		if (caseId != null) {
			if (tmp != null) {
				tmp.setCaseId(caseId.toString());

			}
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		CaseItemForm caseItemForm = (CaseItemForm) command;
		CaseItem caseItem = caseItemForm.getCaseItem();

		errors.printStackTrace();
		// errors.setNestedPath("caseItem");
		// getValidator().validate(caseItem, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		String navigation = WebUtil.getParameterString(request, "navigation","");
		Integer memberId = WebUtil.getParameterInteger(request, "memberId");
		Integer caseCategoryId = WebUtil.getParameterInteger(request, "caseCategoryId");
		
		Collection<Item> itemList = null;
		String[] eqParam = {"deletedStatus","itemType"};
		
		if (navigation.equalsIgnoreCase("adddiagnostic")) {
		
			Object[] eqValue = {0,Item.DIAGNOSTIC_TYPE};
			
			int total = itemService.getTotal(null,null,eqParam,eqValue);
			itemList = itemService.search(null,null,eqParam,eqValue,0,total);

		}
		else if (navigation.equalsIgnoreCase("addadmin")) {
			Object[] eqValue = {0,Item.ADMINISTRATIVE_TYPE};
			
			int total = itemService.getTotal(null,null,eqParam,eqValue);
			itemList = itemService.search(null,null,eqParam,eqValue,0,total);
		}
		else {
			if(memberId != null && caseCategoryId!= null){
				itemList = itemService.getClaimableItem(memberId, caseCategoryId);
			}
		}
		
		

		model.put("itemList", itemList);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		CaseItemForm caseItemForm = (CaseItemForm) command;

		CaseItem res = null;
		String alertMsg = "";

		ModelAndView result = null;

		try {
			// foreign affairs

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (caseItemForm.isNewCaseItem()) {

				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATECASEITEM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for CREATECASEITEM access");
					return errorResult;

				}
				res = caseItemService.create(caseItemForm.getCaseItem(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.caseitem", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.caseitem", null, "fail", Locale
									.getDefault());
				}

				result = new ModelAndView(new RedirectView(
						"caseitem?navigation=list&caseId="
								+ res.getCaseId().getCaseId()));
				result.addObject("alert", alertMsg);
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATECASEITEM");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult.addObject("errorMessage",
							"You Are Not Authorized for UPDATECASEITEM access");
					return errorResult;

				}
				res = caseItemService.update(caseItemForm.getCaseItem(), user);

				if (res != null) {
					//Add 20151304 by FVO, penambahan untuk ikut otomatis update pada claim item saat update case item
					String[] requiredCase = {"Case.MemberId","Case.ClaimId","Case.CaseStatusId", "Case.CaseCategoryId", "Case.ClaimId.ClaimStatus"};
					java.io.Serializable pkey = res.getCaseId().getCaseId();
					Case object = myCaseService.get(pkey,requiredCase);
					if(object.getClaimId()!=null){
						String[] eqParam = {"claimId.claimId","itemId.itemId", "claimId.caseId.caseId","deletedStatus", "claimId.caseCategoryId.caseCategoryId"};
	    				Object[] eqValue = {object.getClaimId().getClaimId(), res.getItemId().getItemId(), object.getCaseId(), Integer.valueOf(0), 
	    						object.getCaseCategoryId().getCaseCategoryId()};
	    				ClaimItem claimItemAdd = claimItemService.searchUnique(eqParam, eqValue);
	    				if(claimItemAdd!=null){
	    					claimItemAdd.setClaimItemAmount(Double.valueOf(caseItemForm.getUsageAmount()));
	    					claimItemAdd.setClaimItemValue(Double.valueOf(caseItemForm.getUsageValue()));
	    					if(object.getClaimId().getClaimStatus().getCaseStatusId() != Claim.CLAIM_OPEN){
	    						claimItemService.update(claimItemAdd, user);
	    					}
	    				}else{
	    					Claim parentClaim = null;
		                    
		                    if(object.getClaimId()!=null){
		                    	java.io.Serializable claimPkey = object.getClaimId().getClaimId();
		        				parentClaim = claimService.get(claimPkey);
		                    }else{
		                    	String[] eqClaimParam = {"caseId.caseId","deletedStatus", "caseCategoryId.caseCategoryId"};
		        				Object[] eqClaimValue = {object.getCaseId(), Integer.valueOf(0), object.getCaseCategoryId().getCaseCategoryId()};
		        				parentClaim = claimService.searchUnique(eqClaimParam, eqClaimValue, 0, 1);
		                    }
		                    claimItemAdd = new ClaimItem();
		                    if(parentClaim!=null){
		                    	 claimItemAdd.setClaimId(parentClaim);
		                    	 claimItemAdd.setItemId(res.getItemId());
		                    	 CaseStatus status = new CaseStatus();
								 status.setCaseStatusId(CaseStatus.CASE_OPEN);
		                    	 claimItemAdd.setClaimItemStatus(status);
		                    	 claimItemAdd.setClaimItemAmount(Double.valueOf(res.getUsageAmount()));
		                    	 claimItemAdd.setClaimItemValue(Double.valueOf(res.getUsageValue()));
		                    	 claimItemAdd.setClaimItemDescription(res.getDescription());
		                    	 if(object.getClaimId().getClaimStatus().getCaseStatusId() != Claim.CLAIM_OPEN){
		                    		 claimItemService.create(claimItemAdd, user);
		                    	 }
		                    }
	    				}
					}
					alertMsg = alertProperties.getMessage(
							"success.update.caseitem", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.caseitem", null, "fail", Locale
									.getDefault());
				}

				result = new ModelAndView(new RedirectView(
						"caseitem?navigation=list&caseId="
								+ res.getCaseId().getCaseId()));
				result.addObject("alert", alertMsg);
			}
		} catch (Exception ex) {
			if (caseItemForm.isNewCaseItem()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.caseitem", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.caseitem", null, "fail", Locale
								.getDefault())
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
	}
	// class+

	// class-

}
