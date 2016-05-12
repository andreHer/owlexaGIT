
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

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
import com.ametis.cms.datamodel.BankAccount;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentBatch;
import com.ametis.cms.datamodel.PaymentInstallment;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BankAccountService;
import com.ametis.cms.service.PaymentBatchService;
import com.ametis.cms.service.PaymentInstallmentService;
import com.ametis.cms.service.PaymentService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.PaymentBatchForm;



// imports+ 

// imports- 

/**
 * PaymentBatch is a mapping of payment_batch Table.
*/
public class PaymentBatchFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	PaymentBatchService paymentBatchService ;
	private PaymentService paymentService;
	private PaymentInstallmentService paymentInstallmentService;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	private BankAccountService bankAccountService;
	// foreign affairs
	
private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	// -- foreign affairs end


	
	
	public void setPaymentBatchService (PaymentBatchService object){
	    this.paymentBatchService = object;
	}
	public PaymentBatchService getPaymentBatchService (){
	    return this.paymentBatchService;
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
	
	public void setSecurityService (SecurityService object){
	    this.securityService = object;
	}
	public SecurityService getSecurityService (){
	    return this.securityService;
	}

	
	public PaymentBatchFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		PaymentBatchForm tmp = null;
						Integer id = WebUtil.getParameterInteger (request,"id");
				
		String[] paymentList = request.getParameterValues("paymentInstallmentList");
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								id != null
				) {
						java.io.Serializable pkey = id;
						PaymentBatch object = paymentBatchService.get (pkey );

			 if (object != null){ // edit object

				tmp = new PaymentBatchForm(object);
			 // foreign affairs
		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new PaymentBatchForm();
					 // foreign affairs
		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new PaymentBatchForm();
					 // foreign affairs
			// -- foreign affairs end



		}
		
		tmp.setPaymentList(paymentList);
		result =  tmp;
		
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		PaymentBatchForm paymentBatchForm = ( PaymentBatchForm ) command;
		PaymentBatch paymentBatch = paymentBatchForm.getPaymentBatch();
		
		errors.printStackTrace();

//		errors.setNestedPath("paymentBatch");
//		getValidator().validate(paymentBatch, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
		
		String[] paymentList = request.getParameterValues("paymentList");
		String[] paymentInstallmentList = request.getParameterValues("paymentInstallmentList");
		
		Integer clientId = WebUtil.getParameterInteger(request, "clientId");
		
		String[] eqParam = {"isSourcePayment","clientId.clientId"};
		Object[] eqValue = {Integer.valueOf(1),clientId};
		
		
		
		int total = bankAccountService.getTotal(null,null,eqParam,eqValue);
		Collection<BankAccount> accountList = bankAccountService.search(null,null,eqParam,eqValue,0,total);
		Collection<Payment> paymentCollection = new Vector<Payment>();
		Collection<PaymentInstallment> paymentInstallmentCollection = new Vector<PaymentInstallment>();
		/**
		if (paymentList != null){
			for (int i = 0; i < paymentList.length; i++){
				Payment payment = paymentService.get(Integer.valueOf(paymentList[i]));
				paymentCollection.add(payment);
			}
		}
		*/
		if (paymentInstallmentList != null){
			for (int i = 0; i < paymentInstallmentList.length; i++){
				if (!paymentInstallmentList[i].equalsIgnoreCase("")){
					PaymentInstallment payment = paymentInstallmentService.get(Long.valueOf(paymentInstallmentList[i]));
					paymentInstallmentCollection.add(payment);
				}
			}
		}
		model.put("accountList", accountList);
		//model.put("paymentCollection",paymentCollection);
		model.put("paymentInstallmentCollection",paymentInstallmentCollection);
		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		PaymentBatchForm paymentBatchForm = ( PaymentBatchForm ) command;

		ModelAndView result = new ModelAndView(new RedirectView("paymentbatch"));
		PaymentBatch res = null;
		String alertMsg="";
		try {
		// foreign affairs
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
			
			
			ActionUser user = securityService.getActionUser(request);
			
			if (paymentBatchForm.isNewPaymentBatch ()) {
				res = paymentBatchService.create (paymentBatchForm.getPaymentBatch(),paymentBatchForm.getPaymentListCollection(), user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.create.paymentbatch",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.paymentbatch",null,"fail",Locale.getDefault());
				}
				
				result = new ModelAndView(new RedirectView("paymentbatch"+"?navigation=detail&id="+res.getId()+"&alert="+alertMsg));
			}
			else {
				res = paymentBatchService.update (paymentBatchForm.getPaymentBatch(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.paymentbatch",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.paymentbatch",null,"fail",Locale.getDefault());
				}

				result = new ModelAndView(new RedirectView("paymentbatch"+"?navigation=detail&id="+res.getId()+"&alert="+alertMsg));
			}
		}catch (Exception ex) {
			if (paymentBatchForm.isNewPaymentBatch ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.paymentbatch",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.paymentbatch",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return result ;
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
	public BankAccountService getBankAccountService() {
		return bankAccountService;
	}
	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	public PaymentService getPaymentService() {
		return paymentService;
	}
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public PaymentInstallmentService getPaymentInstallmentService() {
		return paymentInstallmentService;
	}

	public void setPaymentInstallmentService(
			PaymentInstallmentService paymentInstallmentService) {
		this.paymentInstallmentService = paymentInstallmentService;
	}

// class- 
}
