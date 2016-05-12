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
 * CaseProcedure is a mapping of case_procedure Table.
 */
public class CaseProcedureFormController extends SimpleFormController
// extends+

// extends-
{

	CaseProcedureService caseProcedureService;
	private CaseService caseService;
	private ClaimService claimService;

	private ClaimProcedureService claimProcedureService;
	
	private ProcedureService procedureService;

	// foreign affairs

	// -- foreign affairs end

	public ProcedureService getProcedureService() {
		return procedureService;
	}

	public void setProcedureService(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}

	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}
	ResourceBundleMessageSource alertProperties;
	private SecurityService securityService;
	
	public ClaimProcedureService getClaimProcedureService() {
		return claimProcedureService;
	}

	public void setClaimProcedureService(ClaimProcedureService claimProcedureService) {
		this.claimProcedureService = claimProcedureService;
	}
	
	public void setCaseProcedureService(CaseProcedureService object) {
		this.caseProcedureService = object;
	}

	public CaseProcedureService getCaseProcedureService() {
		return this.caseProcedureService;
	}

	
	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	// generate by default
	private UserService actionuserService;

	public UserService getActionUserService() {
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

	public CaseProcedureFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Object result = null;
		CaseProcedureForm tmp = null;
		Integer caseProcedureId = WebUtil.getParameterInteger(request,
				"caseProcedureId");
		Integer caseId = WebUtil.getParameterInteger(request, "caseId");

		/*
		 * ini ntar di uncomment aja .. trus yang tra --> ini diganti variable
		 * primary key nya
		 */
		if (caseProcedureId != null) {
			java.io.Serializable pkey = caseProcedureId;
			CaseProcedure object = caseProcedureService.get(pkey);

			if (object != null) { // edit object

				tmp = new CaseProcedureForm(object);
				if(object.getProcedureId().getProcedureName()!= null || !object.getProcedureId().getProcedureName().equals(""))
					tmp.setProcedureName(object.getProcedureId().getProcedureName());
				// foreign affairs
				// -- foreign affairs end
			} else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CaseProcedureForm();
				// foreign affairs
				// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CaseProcedureForm();
			// foreign affairs
			// -- foreign affairs end

			if (caseId != null) {
				Case theCase = new Case();
				theCase.setCaseId(caseId);
				tmp.getCaseProcedure().setCaseId(theCase);
			}

		}
		
		if (caseId != null) {
			java.io.Serializable pkeyCaseId = caseId;
			
			String[] req = {"Case.MemberId.CurrentPolicyId.TarifTypeId"};
			Case myCase = caseService.get(pkeyCaseId, req);
			
			if(myCase.getMemberId().getCurrentPolicyId() != null ){
				if (myCase.getMemberId().getCurrentPolicyId().getTarifTypeId() != null){
					request.setAttribute("tarifType", myCase.getMemberId().getCurrentPolicyId().getTarifTypeId().getTarifTypeId());
					System.out.println("tarifType = " +  myCase.getMemberId().getCurrentPolicyId().getTarifTypeId().getTarifTypeId());
				}
				else {
					request.setAttribute("tarifType", 1);
				}
			}else{
				request.setAttribute("tarifType", 1);
			}
			request.setAttribute("roomAndBoard", myCase.getRoomAndBoard());
			request.setAttribute("caseCategory", myCase.getCaseCategoryId().getCaseCategoryId());
			
			request.setAttribute("claimId", myCase.getClaimId().getClaimId());
			
			System.out.println("myCase = " + myCase.getCaseId());
		}

		result = tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {

		CaseProcedureForm caseProcedureForm = (CaseProcedureForm) command;
		CaseProcedure caseProcedure = caseProcedureForm.getCaseProcedure();

		// errors.setNestedPath("caseProcedure");
		// getValidator().validate(caseProcedure, errors);
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

		CaseProcedureForm caseProcedureForm = (CaseProcedureForm) command;
		
		CaseProcedure res = null;
		ClaimProcedure resClaim = null;
		String alertMsg = "";
		String redirectURL = "";
		
		
		try {
			// foreign affairs
			// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request, "notyet", "");
			
			Integer claimId = WebUtil.getParameterInteger(request, "claimId");
			
//			java.io.Serializable pkeyClaimId = caseProcedureForm.getCaseProcedure().getCaseId().getClaimId().getClaimId();
			java.io.Serializable pkeyClaimId = claimId;
			Claim claim = claimService.get(pkeyClaimId);
			
			java.io.Serializable pkeyProcedureId = Integer.parseInt(caseProcedureForm.getProcedureId());
			Procedure procedure = procedureService.get(pkeyProcedureId);
			
			if (notyet.equals("true")) {
				return showForm(request, response, errors);
			}

			ActionUser user = securityService.getActionUser(request);

			if (caseProcedureForm.isNewCaseProcedure()) {
				res = caseProcedureService.create(caseProcedureForm
						.getCaseProcedure(), user);

				ClaimProcedure claimProcedure = new ClaimProcedure();
				claimProcedure.setClaimId(claim);
				claimProcedure.setDescription(caseProcedureForm.getDescription());
				claimProcedure.setProcedureId(procedure);
				claimProcedure.setReferencePrice(StringUtil.getDoubleValue(caseProcedureForm.getReferencePrice(),0));
				claimProcedure.setTotalCharge(StringUtil.getDoubleValue(caseProcedureForm.getTotalCharge(),0));
				claimProcedure.setStatus(StringUtil.getIntegerValue(caseProcedureForm.getStatus(),0));
				claimProcedure.setApprovalRemarks(caseProcedureForm.getApprovalRemarks());
				claimProcedure.setCaseProcedureId(StringUtil.getIntegerValue(caseProcedureForm.getCaseProcedureId(),0));
				
				resClaim = claimProcedureService.create(claimProcedure, user);				
				
				if (res != null && resClaim != null) {
					alertMsg = alertProperties.getMessage(
							"success.create.caseprocedure", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.create.caseprocedure", null, "fail", Locale
									.getDefault());
				}
				redirectURL = "caseprocedure?navigation=list&caseId="+res.getCaseId().getCaseId()+"&alert="+alertMsg;
			} else {
				res = caseProcedureService.update(caseProcedureForm
						.getCaseProcedure(), user);
				
				ClaimProcedure claimProcedure = new ClaimProcedure();
				claimProcedure.setStatus(StringUtil.getIntegerValue(caseProcedureForm.getStatus(),0));
				
				resClaim = claimProcedureService.update(claimProcedure, user);				

				if (res != null && resClaim != null) {
					alertMsg = alertProperties.getMessage(
							"success.update.caseprocedure", null, "success",
							Locale.getDefault());
				} else {
					alertMsg = alertProperties.getMessage(
							"fail.update.caseprocedure", null, "fail", Locale
									.getDefault());
				}
				redirectURL = "caseprocedure?navigation=list&caseId="+res.getCaseId().getCaseId()+"&alert="+alertMsg;

			}
		} catch (Exception ex) {
			if (caseProcedureForm.isNewCaseProcedure()) {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.create.caseprocedure", null, "fail", Locale
								.getDefault())
						+ " " + ex.getMessage());
			} else {
				request.setAttribute("alert", alertProperties.getMessage(
						"fail.update.caseprocedure", null, "fail", Locale
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
