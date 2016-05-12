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
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.CostContainmentService;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.CostContainmentForm;

// imports+ 

// imports- 

/**
 * CostContainment is a mapping of cost_containment Table.
 */
public class CostContainmentFormController extends SimpleFormController
// extends+

// extends-

{

	CostContainmentService costContainmentService;
	ResourceBundleMessageSource alertProperties;
	// foreign affairs

	ItemService itemService;
	private SecurityService securityService;
	private ActivityLogService logService;
	private CaseService caseService;
	
	

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
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

	public void setItemService(ItemService obj) {
		this.itemService = obj;
	}

	public ItemService getItemService() {
		return this.itemService;
	}

	// -- foreign affairs end

	public void setCostContainmentService(CostContainmentService object) {
		this.costContainmentService = object;
	}

	public CostContainmentService getCostContainmentService() {
		return this.costContainmentService;
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

	public CostContainmentFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CostContainmentForm tmp = null;
		Integer costContainmentId = WebUtil.getParameterInteger(request,
				"costContainmentId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (costContainmentId != null) {
			java.io.Serializable pkey = costContainmentId;
			CostContainment object = costContainmentService.get(pkey);

			if (object != null) { // edit object

				tmp = new CostContainmentForm(object);
				// foreign affairs

				tmp.setItemId("" + object.getItemId().getItemId());
				
				String breadcrumb = "<a href=\"case?navigation=detail&caseId="+object.getCaseId().getCaseId()+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Cost Containment";
				request.setAttribute("breadcrumb", breadcrumb);
				
				if (object.getCaseId() != null){
					Case theCase = caseService.get(object.getCaseId().getCaseId());
					
					tmp.setCaseId(theCase.getCaseId().toString());
					tmp.setCaseNumber(theCase.getCaseNumber());
					tmp.setMemberId(theCase.getMemberId().getMemberId().toString());
					tmp.setClientId(theCase.getMemberId().getClientId().getClientId());
				}
				if (object.getItemId() != null){
					Item item = itemService.get(object.getItemId().getItemId());
					if (item != null){
						tmp.setItemId(item.getItemId().toString());
						tmp.setItemName(item.getItemName());
					}
				}
				if (object.getSubstitutionItemId() != null){
					Item item = itemService.get(object.getSubstitutionItemId().getItemId());
					if (item != null){
						tmp.setSubstitutionItemId(item.getItemId().toString());
						tmp.setSubstitutionItemName(item.getItemName());
					}
				}
				

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CostContainmentForm();
				// foreign affairs

				Integer itemId = WebUtil.getParameterInteger(request, "itemId");

				if (itemId != null) {
					Item forClass = new Item();
					forClass.setItemId(itemId);
					tmp.setItemId("" + itemId);

					Item item = this.itemService.get(itemId);
					tmp.getCostContainment().setItemId(item);
				} else {
					Item forClass = new Item();
					// tmp.setItemId("");
					tmp.getCostContainment().setItemId(forClass);
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CostContainmentForm();
			// foreign affairs

			Integer itemId = WebUtil.getParameterInteger(request, "itemId");
			Integer caseId = WebUtil.getParameterInteger(request, "caseId");

			if (itemId != null) {
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId("" + itemId);

				Item item = this.itemService.get(itemId);
				tmp.getCostContainment().setItemId(item);
			} else {
				Item forClass = new Item();
				// tmp.setItemId("");
				tmp.getCostContainment().setItemId(forClass);
			}
			
			if (caseId != null){
				Case theCase = caseService.get(caseId);
				tmp.getCostContainment().setCaseId(theCase);
				tmp.setCaseNumber(theCase.getCaseNumber());
				tmp.setMemberId(theCase.getMemberId().getMemberId().toString());
				if (theCase.getMemberId().getClientId() != null){
					tmp.setClientId(theCase.getMemberId().getClientId().getClientId());
				}
			}

			// -- foreign affairs end

			String breadcrumb = "<a href=\"case?navigation=detail&caseId="+caseId+"\" class=\"linkbreadcrumb\">Detail Case</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Register Cost Containment";
			request.setAttribute("breadcrumb", breadcrumb);				

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		CostContainmentForm costContainmentForm = (CostContainmentForm) command;
		CostContainment costContainment = costContainmentForm
				.getCostContainment();

		// errors.setNestedPath("costContainment");
		// getValidator().validate(costContainment, errors);
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

		CostContainmentForm costContainmentForm = (CostContainmentForm) command;

		CostContainment res = null;
		String alertMsg = "";
		String caseId = "";
		try {
			// foreign affairs
			if (costContainmentForm.getItemId() != null) {
				costContainmentForm.getCostContainment().setItemId(
						this.itemService.get(new Integer((costContainmentForm
								.getItemId()))));
			}
			if (costContainmentForm.getSubstitutionItemId() != null) {
				costContainmentForm.getCostContainment().setSubstitutionItemId(
						this.itemService.get(new Integer((costContainmentForm
								.getSubstitutionItemId()))));
			}
			if (costContainmentForm.getCaseId() != null) {
				Member member = new Member();
				member.setMemberId(Integer.valueOf(costContainmentForm.getMemberId()) );
				costContainmentForm.getCostContainment().setMemberId(member);
				Client client = new Client();
				client.setClientId(costContainmentForm.getClientId());
				costContainmentForm.getCostContainment().setClientId(client);
			}

			if (costContainmentForm.getCostContainment().getInitialItemCost() != null && costContainmentForm.getCostContainment().getTotalSubstitutionValue() != null){
				double initial = costContainmentForm.getCostContainment().getInitialItemCost();
				double substitutionValue = costContainmentForm.getCostContainment().getTotalSubstitutionValue();
				
				double containmentValue = initial - substitutionValue;
				
				costContainmentForm.getCostContainment().setCostContainmentValue(containmentValue);
			}
			
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			if (costContainmentForm.isNewCostContainment()) {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "CREATECOSTCONTAINMENT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for CREATECOSTCONTAINMENT access");
					return errorResult;

				}
				res = costContainmentService.create(costContainmentForm
						.getCostContainment(), user);

				if (res != null) {
					caseId = res.getCaseId().getCaseId().toString();
					alertMsg = alertProperties.getMessage(
							"success.create.costcontainment", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.costcontainment", null, "fail", Locale
									.getDefault());
				}
			} else {
				ActionUser user = securityService.getActionUser(request);

				boolean isUserAuthorized = securityService.isUserAuthorized(
						user, "UPDATECOSTCONTAINMENT");

				if (!isUserAuthorized) {

					ModelAndView errorResult = new ModelAndView(
							new RedirectView("errorpage"));
					errorResult.addObject("errorType", "accessDenied");
					errorResult
							.addObject("errorMessage",
									"You Are Not Authorized for UPDATECOSTCONTAINMENT access");
					return errorResult;

				}
				res = costContainmentService.update(costContainmentForm
						.getCostContainment(), user);

				if (res != null) {
					caseId = res.getCaseId().getCaseId().toString();
					alertMsg = alertProperties.getMessage(
							"success.update.costcontainment", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.costcontainment", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (costContainmentForm.isNewCostContainment()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.costcontainment", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.costcontainment", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("costcontainment?navigation=list&caseId="+caseId + "&alert="
				+ alertMsg));
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
