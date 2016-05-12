
package com.ametis.cms.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
 * CardPrinting is a mapping of card_printing Table.
*/
public class CardPrintingFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	CardPrintingService cardPrintingService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	// foreign affairs
	
			ClientService clientService;

	public void setClientService(ClientService obj){
		this.clientService = obj;
	}

	public ClientService getClientService(){
		return this.clientService;
	}
			
	// -- foreign affairs end


	public void setCardPrintingService (CardPrintingService object){
	    this.cardPrintingService = object;
	}
	public CardPrintingService getCardPrintingService (){
	    return this.cardPrintingService;
	}
		// generate by default
		private UserService  actionuserService;
	public UserService getUserService() {
		return actionuserService;
	}
	public void setUserService(UserService userService) {
		this.actionuserService = userService;
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

	
	public CardPrintingFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		CardPrintingForm tmp = null;
						Integer cardPrintingId = WebUtil.getParameterInteger (request,"cardPrintingId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								cardPrintingId != null
				) {
						java.io.Serializable pkey = cardPrintingId;
						CardPrinting object = cardPrintingService.get (pkey );

			 if (object != null){ // edit object

				tmp = new CardPrintingForm(object);
			 // foreign affairs
	
				tmp.setClientId(""+
					object.getClientId().getClientId()
				);


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new CardPrintingForm();
					 // foreign affairs
	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getCardPrinting().setClientId(client);
			}else{
				
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new CardPrintingForm();
					 // foreign affairs
		
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getCardPrinting().setClientId(client);
			}else{
			}


		// -- foreign affairs end

			String[] cardList = request.getParameterValues("memberImportId");
			
			Collection<Integer> memberIdList = new Vector<Integer>();
			
			if (cardList != null){
				for (int i = 0; i < cardList.length; i++){
					memberIdList.add(Integer.valueOf(cardList[i]));
				}
			}
			tmp.setMemberIdList(memberIdList);


		}
																												
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		CardPrintingForm cardPrintingForm = ( CardPrintingForm ) command;
		CardPrinting cardPrinting = cardPrintingForm.getCardPrinting();

//		errors.setNestedPath("cardPrinting");
//		getValidator().validate(cardPrinting, errors);
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

		CardPrintingForm cardPrintingForm = ( CardPrintingForm ) command;

		CardPrinting res = null;
		String alertMsg="";
		String cardPrintingId = "";
		try {

			
			ActionUser user = securityService.getActionUser(request);
			
			if (cardPrintingForm.isNewCardPrinting ()) {
				Collection<Integer> memberIdList = cardPrintingForm.getMemberIdList();
				
				res = cardPrintingService.create (cardPrintingForm.getCardPrinting(),memberIdList,user);

				if (res!=null){
					cardPrintingId = res.getCardPrintingId().toString();
					alertMsg = alertProperties.getMessage ("success.create.cardprinting",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.cardprinting",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = cardPrintingService.update (cardPrintingForm.getCardPrinting(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.cardprinting",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.cardprinting",null,"fail",Locale.getDefault());
				}
				
				cardPrintingId = res.getCardPrintingId().toString();

			}
		}catch (Exception ex) {
			if (cardPrintingForm.isNewCardPrinting ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.cardprinting",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.cardprinting",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("cardprinting"+"?navigation=detail&cardPrintingId="+cardPrintingId+"&alert="+alertMsg));
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
