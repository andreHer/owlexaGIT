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
 * CardStock is a mapping of card_stock Table.
 */
public class CardStockFormController extends SimpleFormController
// extends+

// extends-
{

	CardStockService cardStockService;
	private ClientService clientService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;

	// foreign affairs

	// -- foreign affairs end

	public void setCardStockService(CardStockService object) {
		this.cardStockService = object;
	}

	public CardStockService getCardStockService() {
		return this.cardStockService;
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
	

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public CardStockFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CardStockForm tmp = null;
		Long id = WebUtil.getParameterLong(request, "id");
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (id != null) {
			java.io.Serializable pkey = id;
			CardStock object = cardStockService.get(pkey);

			if (object != null) { // edit object

				tmp = new CardStockForm(object);
				// foreign affairs
				// -- foreign affairs end

				if (clientId != null){
					Client client = clientService.get(clientId);
					tmp.setClientId(client.getClientId().toString());
					tmp.setClientName(client.getClientName());
				}
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CardStockForm();
				// foreign affairs
				// -- foreign affairs end

				if (clientId != null){
					Client client = clientService.get(clientId);
					tmp.setClientId(client.getClientId().toString());
					tmp.setClientName(client.getClientName());
				}
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CardStockForm();
			// foreign affairs
			// -- foreign affairs end
			

			if (clientId != null){
				Client client = clientService.get(clientId);
				tmp.setClientId(client.getClientId().toString());
				tmp.setClientName(client.getClientName());
			}
		}
		String breadcrumb = "";
        breadcrumb = "<a href=\"cardstock?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Card Stock</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Card Stock";

        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
        request.setAttribute("clientId", clientId);
        
		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		CardStockForm cardStockForm = (CardStockForm) command;
		CardStock cardStock = cardStockForm.getCardStock();

		String breadcrumb = "";
        breadcrumb = "<a href=\"cardstock?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Card Stock</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Card Stock";

        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
        request.setAttribute("clientId", clientId);
		// errors.setNestedPath("cardStock");
		// getValidator().validate(cardStock, errors);
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
//		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		
		CardStockForm cardStockForm = (CardStockForm) command;

		CardStock res = null;
		String alertMsg = "";
		String clientId = cardStockForm.getClientId();
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}


			
			ActionUser user = securityService.getActionUser(request);

			if (cardStockForm.isNewCardStock()) {
				res = cardStockService.create(cardStockForm.getCardStock(),
						user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.cardstock", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.cardstock", null, "fail", Locale
									.getDefault());
				}
			} else {
				res = cardStockService.update(cardStockForm.getCardStock(),
						user);

				if (res != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.cardstock", null, "success", Locale
									.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.cardstock", null, "fail", Locale
									.getDefault());
				}

			}
		} catch (Exception ex) {
			if (cardStockForm.isNewCardStock()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.cardstock", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.cardstock", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "";
        breadcrumb = "<a href=\"cardstock?navigation="+navigation+"&clientId="+clientId+"\" class=\"linkbreadcrumb\">List Client Card Stock</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Add Card Stock";

        request.setAttribute("breadcrumb", breadcrumb);
        request.setAttribute("navigation", navigation);
        request.setAttribute("clientId", clientId);
        
		return new ModelAndView(new RedirectView("cardstock" + "?alert="
				+ alertMsg+"&clientId="+clientId));
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
