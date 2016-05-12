
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
import org.springframework.context.support.ResourceBundleMessageSource;
/*
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
 * DiagnosisSet is a mapping of diagnosis_set Table.
 */
public class DiagnosisSetFormController extends SimpleFormController
		// extends+

// extends-
{

	DiagnosisSetService diagnosisSetService;
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	// foreign affairs

	ClientService clientService;

	public void setClientService(ClientService obj) {
		this.clientService = obj;
	}

	public ClientService getClientService() {
		return this.clientService;
	}

	// -- foreign affairs end

	public void setDiagnosisSetService(DiagnosisSetService object) {
		this.diagnosisSetService = object;
	}

	public DiagnosisSetService getDiagnosisSetService() {
		return this.diagnosisSetService;
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

	public DiagnosisSetFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		DiagnosisSetForm tmp = null;
		Integer diagnosisSetId = WebUtil.getParameterInteger(request, "diagnosisSetId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (diagnosisSetId != null) {
			java.io.Serializable pkey = diagnosisSetId;
			DiagnosisSet object = diagnosisSetService.get(pkey);

			if (object != null) { // edit object

				tmp = new DiagnosisSetForm(object);
				// foreign affairs

				if (object.getClientId() != null){
					Client client = this.clientService.get(object.getClientId().getClientId());
					
					
					tmp.setClientId("" + object.getClientId().getClientId());
					
					if (client != null){
						tmp.setClientName(client.getClientName());
					}
				}

				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new DiagnosisSetForm();
				// foreign affairs

				Integer clientId = WebUtil.getParameterInteger(request, "clientId");

				if (clientId != null) {
					Client forClass = new Client();
					forClass.setClientId(clientId);
					tmp.setClientId("" + clientId);

					Client client = this.clientService.get(clientId);
					tmp.getDiagnosisSet().setClientId(client);
					tmp.setClientName(client.getClientName());
				}

				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new DiagnosisSetForm();
			// foreign affairs

			Integer clientId = WebUtil.getParameterInteger(request, "clientId");

			if (clientId != null) {
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId("" + clientId);

				Client client = this.clientService.get(clientId);
				tmp.getDiagnosisSet().setClientId(client);
				tmp.setClientName(client.getClientName());
			}

		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {

		DiagnosisSetForm diagnosisSetForm = (DiagnosisSetForm) command;
		DiagnosisSet diagnosisSet = diagnosisSetForm.getDiagnosisSet();

		// errors.setNestedPath("diagnosisSet");
		// getValidator().validate(diagnosisSet, errors);
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

	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {

		DiagnosisSetForm diagnosisSetForm = (DiagnosisSetForm) command;

		DiagnosisSet res = null;
		String alertMsg = "";
		try {
			// foreign affairs
			if (diagnosisSetForm.getClientId() != null) {
				diagnosisSetForm.getDiagnosisSet()
						.setClientId(this.clientService.get(new Integer((diagnosisSetForm.getClientId()))));
			}

			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			/*
			 * Authentication auth =
			 * SecurityContextHolder.getContext().getAuthentication();
			 * ActionUser user = null; if (auth != null && auth.getPrincipal()
			 * instanceof UserDetails) { UserDetails principal =
			 * (UserDetails)auth.getPrincipal(); String[] eqP = new
			 * String[]{"email"}; String[] eqQ = new
			 * String[]{principal.getUsername()};
			 * 
			 * Collection coll =
			 * actionuserService.search(null,null,eqP,eqQ,-1,-1);
			 * if(coll==null||coll.size()<=0){ user = new ActionUser(); }else{
			 * ActionUser users[] = new ActionUser[coll.size()];
			 * coll.toArray(users); user = users[0]; }
			 * 
			 * 
			 * }
			 */

			ActionUser user = securityService.getActionUser(request);

			if (diagnosisSetForm.isNewDiagnosisSet()) {
				res = diagnosisSetService.create(diagnosisSetForm.getDiagnosisSet(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage("success.create.diagnosisset", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.create.diagnosisset", null, "fail",
							Locale.getDefault());
				}
			} else {
				res = diagnosisSetService.update(diagnosisSetForm.getDiagnosisSet(), user);

				if (res != null) {
					alertMsg = alertProperties.getMessage("success.update.diagnosisset", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage("fail.update.diagnosisset", null, "fail",
							Locale.getDefault());
				}

			}
		} catch (Exception ex) {
			if (diagnosisSetForm.isNewDiagnosisSet()) {
				request.setAttribute("alert",
						alertProperties.getMessage("fail.create.diagnosisset", null, "fail", Locale.getDefault()) + " "
								+ ex.getMessage());
			} else {
				request.setAttribute("alert",
						alertProperties.getMessage("fail.update.diagnosisset", null, "fail", Locale.getDefault()) + " "
								+ ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("diagnosisset" + "?alert=" + alertMsg));
		// return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true);
		binder.registerCustomEditor(Date.class, cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class, true);
		binder.registerCustomEditor(Number.class, num);
	}
	// class+

	// class-
}
