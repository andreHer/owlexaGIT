
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
import com.ametis.cms.datamodel.GroupClaimUtilReport;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.GroupClaimUtilReportService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.GroupClaimUtilReportForm;



// imports+ 

// imports- 

/**
 * GroupClaimUtilReport is a mapping of group_claim_util_report Table.
*/
public class GroupClaimUtilReportFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	GroupClaimUtilReportService groupClaimUtilReportService ;
	ResourceBundleMessageSource alertProperties ;
	// foreign affairs
	
		
	// -- foreign affairs end
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}

	public void setGroupClaimUtilReportService (GroupClaimUtilReportService object){
	    this.groupClaimUtilReportService = object;
	}
	public GroupClaimUtilReportService getGroupClaimUtilReportService (){
	    return this.groupClaimUtilReportService;
	}
		// generate by default
		private UserService  userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setPropertiesUtil (ResourceBundleMessageSource object){
	    this.alertProperties = object;
	}
	public ResourceBundleMessageSource getPropertiesUtil (){
	    return this.alertProperties;
	}


	public GroupClaimUtilReportFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		GroupClaimUtilReportForm tmp = null;
						Long id = WebUtil.getParameterLong (request, "id");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								id != null
				) {
						java.io.Serializable pkey = id;
						GroupClaimUtilReport object = groupClaimUtilReportService.get (pkey );

			 if (object != null){ // edit object

				tmp = new GroupClaimUtilReportForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new GroupClaimUtilReportForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new GroupClaimUtilReportForm();
					 // foreign affairs
			// -- foreign affairs end



		}
																																																										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		GroupClaimUtilReportForm groupClaimUtilReportForm = ( GroupClaimUtilReportForm ) command;
		GroupClaimUtilReport groupClaimUtilReport = groupClaimUtilReportForm.getGroupClaimUtilReport();

//		errors.setNestedPath("groupClaimUtilReport");
//		getValidator().validate(groupClaimUtilReport, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		/**
			ini dipake buat populate data - data yang dibutuhkan
			contoh : Problem membutuhkan ProblemCategory
			nah fungsi method ini yaitu untuk populate list problem category ke jsp
			nanti biar bisa ditangkep sama jspnya

			contoh : Collection pc = pcontroller.searchPC();

			model.addObject("pcbeans", pc);

		*/

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		GroupClaimUtilReportForm groupClaimUtilReportForm = ( GroupClaimUtilReportForm ) command;

		GroupClaimUtilReport res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			ActionUser user = null;
			
			if (groupClaimUtilReportForm.isNewGroupClaimUtilReport ()) {
				res = groupClaimUtilReportService.create (groupClaimUtilReportForm.getGroupClaimUtilReport(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.groupclaimutilreport",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.groupclaimutilreport",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = groupClaimUtilReportService.update (groupClaimUtilReportForm.getGroupClaimUtilReport(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.groupclaimutilreport",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.groupclaimutilreport",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (groupClaimUtilReportForm.isNewGroupClaimUtilReport ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.groupclaimutilreport",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.groupclaimutilreport",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("groupclaimutilreport"+"?alert="+alertMsg));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"),true);
		binder.registerCustomEditor(Date.class,cde);
		CustomNumberEditor num = new CustomNumberEditor(Number.class,true);
		binder.registerCustomEditor(Number.class,num);
	}
// class+ 

// class- 
}
