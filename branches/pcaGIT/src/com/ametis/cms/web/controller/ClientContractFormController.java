
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
 * ClientContract is a mapping of client_contract Table.
*/
public class ClientContractFormController extends SimpleFormController
// extends+ 

// extends- 
{
	
	
	
	ClientContractService clientContractService ;
	ResourceBundleMessageSource alertProperties ;
	private SecurityService securityService;
	private ProductTypeService productTypeService;
	private CurrencyService currencyService;
	// foreign affairs
	
			ContractTypeService contractTypeService;

	public void setContractTypeService(ContractTypeService obj){
		this.contractTypeService = obj;
	}

	public ContractTypeService getContractTypeService(){
		return this.contractTypeService;
	}
				ClientService clientService;

	public void setClientService(ClientService obj){
		this.clientService = obj;
	}

	public ClientService getClientService(){
		return this.clientService;
	}
			
	// -- foreign affairs end


	public void setClientContractService (ClientContractService object){
	    this.clientContractService = object;
	}
	public ClientContractService getClientContractService (){
	    return this.clientContractService;
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
	
	
	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ClientContractFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		ClientContractForm tmp = null;
						Integer clientContractId = WebUtil.getParameterInteger (request,"clientContractId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (								clientContractId != null
				) {
						java.io.Serializable pkey = clientContractId;
						ClientContract object = clientContractService.get (pkey );
						Client client = clientService.get(object.getClientId().getClientId());

			 if (object != null){ // edit object

				tmp = new ClientContractForm(object);
			 // foreign affairs
	
				tmp.setContractTypeId(""+
					object.getContractTypeId().getContractTypeId()
				);


	
				tmp.setClientId(""+
					object.getClientId().getClientId()
					
				);
				tmp.setClientName(client.getClientName());


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new ClientContractForm();
					 // foreign affairs
	
	
				Integer contractTypeId = WebUtil.getParameterInteger (request,"contractTypeId");
		
			if(contractTypeId!=null){
				ContractType forClass = new ContractType();
				forClass.setContractTypeId(contractTypeId);
				tmp.setContractTypeId(""+contractTypeId);

				ContractType contractType = this.contractTypeService.get(contractTypeId);
				tmp.getClientContract().setContractTypeId(contractType);
			}else{
				
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				tmp.getClientContract().setClientId(client);
				tmp.setClientName(client.getClientName());
			}else{
				
			}


		// -- foreign affairs end
			}
		} // mau edit end
		else { // bikin baru
			tmp = new ClientContractForm();
					 // foreign affairs
		
	
				Integer contractTypeId = WebUtil.getParameterInteger (request,"contractTypeId");
		
			if(contractTypeId!=null){
				ContractType forClass = new ContractType();
				forClass.setContractTypeId(contractTypeId);
				tmp.setContractTypeId(""+contractTypeId);

				ContractType contractType = this.contractTypeService.get(contractTypeId);
				tmp.getClientContract().setContractTypeId(contractType);
			}else{
				
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){
				Client forClass = new Client();
				forClass.setClientId(clientId);
				tmp.setClientId(""+clientId);

				Client client = this.clientService.get(clientId);
				tmp.getClientContract().setClientId(client);
				tmp.setClientName(client.getClientName());
			}else{
				
			}


		// -- foreign affairs end



		}
																																										
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		ClientContractForm clientContractForm = ( ClientContractForm ) command;
		ClientContract clientContract = clientContractForm.getClientContract();

//		errors.setNestedPath("clientContract");
//		getValidator().validate(clientContract, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();

		Collection<ContractType> typeList = contractTypeService.getAll();
		Collection<ProductType> productTypeList = productTypeService.getAll();
		Collection<Currency> currencyList = currencyService.getAll();
		
		model.put("currencyList", currencyList);
		model.put("productTypeList", productTypeList);
		
		model.put("typeList", typeList);

		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		ClientContractForm clientContractForm = ( ClientContractForm ) command;

		ClientContract res = null;
		String alertMsg="";
		String clientContractId = "";
		try {
		// foreign affairs
			
	
			
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
				ActionUser user = securityService.getActionUser(request);
			
			if (clientContractForm.isNewClientContract ()) {
				res = clientContractService.create (clientContractForm.getClientContract(),user);

				if (res!=null){
					clientContractId = res.getClientContractId().toString();
					alertMsg = alertProperties.getMessage ("success.create.clientcontract",null,"success",Locale.getDefault());
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.clientcontract",null,"fail",Locale.getDefault());
				}
			}
			else {
				res = clientContractService.update (clientContractForm.getClientContract(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.clientcontract",null,"success",Locale.getDefault());
					
					clientContractId = res.getClientContractId().toString();
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.clientcontract",null,"fail",Locale.getDefault());
				}

			}
		}catch (Exception ex) {
			if (clientContractForm.isNewClientContract ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.clientcontract",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.clientcontract",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		return new ModelAndView(new RedirectView("clientcontract"+"?navigation=detail&clientContractId="+clientContractId+"&alert="+alertMsg));
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
