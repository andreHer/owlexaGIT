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
 * ClientContractItem is a mapping of client_contract_item Table.
 */
public class ClientContractItemFormController extends SimpleFormController
// extends+

// extends-

{

	ClientContractItemService clientContractItemService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	private CaseCategoryService caseCategoryService;

	// foreign affairs

	ClientContractService clientContractService;

	public void setClientContractService(ClientContractService obj) {
		this.clientContractService = obj;
	}

	public ClientContractService getClientContractService() {
		return this.clientContractService;
	}

	ItemService itemService;

	public void setItemService(ItemService obj) {
		this.itemService = obj;
	}

	public ItemService getItemService() {
		return this.itemService;
	}

	// -- foreign affairs end

	public void setClientContractItemService(ClientContractItemService object) {
		this.clientContractItemService = object;
	}

	public ClientContractItemService getClientContractItemService() {
		return this.clientContractItemService;
	}

	// generate by default
	private UserService actionuserService;

	public UserService getUserService() {
		return actionuserService;
	}

	public void setUserService(UserService userService) {
		this.actionuserService = userService;
	}

	
	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}

	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
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

	public ClientContractItemFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		ClientContractItemForm tmp = null;
		Integer clientContractItemId = WebUtil.getParameterInteger(request,
				"clientContractItemId");

		if (clientContractItemId != null) {
			java.io.Serializable pkey = clientContractItemId;
			
			ClientContractItem object = clientContractItemService.get(pkey);

			if (object != null) { // edit object

				tmp = new ClientContractItemForm(object);
				// foreign affairs

				tmp.setClientContractId(""
						+ object.getClientContractId().getClientContractId());

				tmp.setItemId("" + object.getItemId().getItemId());
				ClientContract contract = clientContractService.get(object.getClientContractId().getClientContractId());
				
				if (contract != null){
					tmp.setContractNumber(contract.getClientContractNumber());
				}
				

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClientContractItemForm();
				// foreign affairs

				Integer clientContractId = WebUtil.getParameterInteger(request,
						"clientContractId");

				if (clientContractId != null) {
					ClientContract forClass = new ClientContract();
					forClass.setClientContractId(clientContractId);
					tmp.setClientContractId("" + clientContractId);

					ClientContract clientContract = this.clientContractService
							.get(clientContractId);
					tmp.getClientContractItem().setClientContractId(
							clientContract);
				}
				Integer itemId = WebUtil.getParameterInteger(request, "itemId");

				if (itemId != null) {
					Item forClass = new Item();
					forClass.setItemId(itemId);
					tmp.setItemId("" + itemId);

					Item item = this.itemService.get(itemId);
					tmp.getClientContractItem().setItemId(item);
				}			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClientContractItemForm();
			// foreign affairs

			Integer clientContractId = WebUtil.getParameterInteger(request,
					"clientContractId");

			if (clientContractId != null) {
				ClientContract forClass = new ClientContract();
				forClass.setClientContractId(clientContractId);
				tmp.setClientContractId("" + clientContractId);

				ClientContract clientContract = this.clientContractService
						.get(clientContractId);
				tmp.getClientContractItem().setClientContractId(clientContract);
				
				if (clientContract != null){
					tmp.setContractNumber(clientContract.getClientContractNumber());
				}				
			}

			Integer itemId = WebUtil.getParameterInteger(request, "itemId");

			if (itemId != null) {
				Item forClass = new Item();
				forClass.setItemId(itemId);
				tmp.setItemId("" + itemId);

				Item item = this.itemService.get(itemId);
				tmp.getClientContractItem().setItemId(item);
			}
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		ClientContractItemForm clientContractItemForm = (ClientContractItemForm) command;
		ClientContractItem clientContractItem = clientContractItemForm
				.getClientContractItem();

		
		errors.printStackTrace();
		// errors.setNestedPath("clientContractItem");
		// getValidator().validate(clientContractItem, errors);
		// errors.setNestedPath("");
	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		Collection<Item> itemList = itemService.getClientInvoiceItemList();
		
		model.put("itemList", itemList);
		
		Collection<Item> ccList = caseCategoryService.getAll();
		
		model.put("caseCategoryList", ccList);

		return model;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClientContractItemForm clientContractItemForm = (ClientContractItemForm) command;

		ClientContractItem res = null;
		String alertMsg = "";
		String clientContractId = "";
		try {

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);

			if (clientContractItemForm.isNewClientContractItem()) {
				res = clientContractItemService.create(clientContractItemForm
						.getClientContractItem(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.clientcontractitem", null,
							"success", Locale.getDefault());
					clientContractId = res.getClientContractId().getClientContractId().toString();
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.clientcontractitem", null, "fail",
							Locale.getDefault());
				}
			} else {
				res = clientContractItemService.update(clientContractItemForm
						.getClientContractItem(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.clientcontractitem", null,
							"success", Locale.getDefault());
					
					clientContractId = res.getClientContractId().getClientContractId().toString();
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.clientcontractitem", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (clientContractItemForm.isNewClientContractItem()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.clientcontractitem", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.clientcontractitem", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("clientcontract"
				+ "?navigation=detail&alert=" + alertMsg +"&clientContractId="+clientContractId));
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
