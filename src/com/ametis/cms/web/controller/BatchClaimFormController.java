
package com.ametis.cms.web.controller;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.ClaimReceiving;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Currency;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.PaymentMethod;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.CaseStatusService;
import com.ametis.cms.service.ClaimReceivingService;
import com.ametis.cms.service.ClaimTypeService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.CurrencyService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PaymentMethodService;
import com.ametis.cms.service.PaymentRecipientService;
import com.ametis.cms.service.PriorityService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.SecurityService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.WebUtil;
import com.ametis.cms.web.form.BatchClaimForm;

public class BatchClaimFormController extends SimpleFormController
{
	
	
	
	BatchClaimService batchClaimService ;
	ResourceBundleMessageSource alertProperties ;
	PaymentMethodService paymentMethodService;
	CurrencyService currencyService;
	private ClaimReceivingService claimReceivingService;
	// foreign affairs
	
	
	
	CaseStatusService caseStatusService;
	PriorityService priorityService;
	
	private SecurityService securityService;

	private ActivityLogService logService;

	
	
	public ActivityLogService getLogService() {
		return logService;
	}

	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public PriorityService getPriorityService() {
		return priorityService;
	}

	public void setPriorityService(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	public void setCaseStatusService(CaseStatusService obj){
		this.caseStatusService = obj;
	}

	public CaseStatusService getCaseStatusService(){
		return this.caseStatusService;
	}
				PaymentRecipientService paymentRecipientService;

	public void setPaymentRecipientService(PaymentRecipientService obj){
		this.paymentRecipientService = obj;
	}

	public PaymentRecipientService getPaymentRecipientService(){
		return this.paymentRecipientService;
	}
				MemberService memberService;

	public void setMemberService(MemberService obj){
		this.memberService = obj;
	}

	public MemberService getMemberService(){
		return this.memberService;
	}
				ClientService clientService;

	public void setClientService(ClientService obj){
		this.clientService = obj;
	}
	
	public ClientService getClientService(){
		return this.clientService;
	}
				ClaimTypeService claimTypeService;

	public void setClaimTypeService(ClaimTypeService obj){
		this.claimTypeService = obj;
	}

	public ClaimTypeService getClaimTypeService(){
		return this.claimTypeService;
	}
				MemberGroupService memberGroupService;

	public void setMemberGroupService(MemberGroupService obj){
		this.memberGroupService = obj;
	}

	public MemberGroupService getMemberGroupService(){
		return this.memberGroupService;
	}
				ProviderService providerService;

	public void setProviderService(ProviderService obj){
		this.providerService = obj;
	}

	public ProviderService getProviderService(){
		return this.providerService;
	}
				
			
	// -- foreign affairs end


	
	public void setBatchClaimService (BatchClaimService object){
	    this.batchClaimService = object;
	}
	public BatchClaimService getBatchClaimService (){
	    return this.batchClaimService;
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


	public BatchClaimFormController() {
		setSessionForm(true);
		setValidateOnBinding(true);
	}
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		Object result = null;
		BatchClaimForm tmp = null;
						Integer batchClaimId = WebUtil.getParameterInteger (request,"batchClaimId");
				
		/*
		ini ntar di uncomment aja .. trus yang tra --> ini diganti variable primary key nya
		*/
		if (batchClaimId != null) {
			java.io.Serializable pkey = batchClaimId;
			BatchClaim object = batchClaimService.get (pkey );

			 if (object != null){ // edit object

				tmp = new BatchClaimForm(object);
			 // foreign affairs
	

				PaymentRecipient recipient =object.getPaymentRecipient(); 
				
	
				if (recipient != null){
					tmp.setPaymentRecipient(recipient.getPaymentRecipientId().toString());
				}
				
				if (object.getPaymentMethod() != null){
					tmp.setPaymentMethod(object.getPaymentMethod().getPaymentMethodId().toString());
				}

				if (recipient != null){
					if (recipient.getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
						if (object.getMemberId() != null){
							Member member = memberService.get(object.getMemberId().getMemberId());
							if (member != null){
								tmp.setClaimerId(member.getMemberId());
								tmp.setClaimer(member.getFirstName() + member.getLastName());
							}
						}
					}
					else if (recipient.getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP){
						if (object.getMemberGroupId() != null){
							MemberGroup group = memberGroupService.get(object.getMemberGroupId().getMemberGroupId());
							if (group != null){
								tmp.setClaimerId(group.getMemberGroupId());
								tmp.setClaimer(group.getGroupName());
							}
						}
					}
					else {
						if (object.getProviderId() != null){
							Provider provider = providerService.get(object.getProviderId().getProviderId());
							if (provider != null){
								tmp.setClaimerId(provider.getProviderId());
								tmp.setClaimer(provider.getProviderName());
							}
						}
					}
				}				
					
			


	
				if (object.getClientId() != null){
					Client client = clientService.get(object.getClientId().getClientId());
					tmp.setClientId(client.getClientId().toString());
					tmp.setClientName(client.getClientName());
				}


				if (object.getBatchClaimType() != null){	
					tmp.setBatchClaimType(object.getBatchClaimType().getClaimTypeId().toString());
				}
	
				if (object.getPriority() != null){
					tmp.setPriority(object.getPriority().getPriorityId().toString());
				}


		// -- foreign affairs end
			}
			else {// object tidak ditemukan ?? anggap kita mau bikin baru !
				tmp = new BatchClaimForm();
					 // foreign affairs
	
				tmp.setPaymentMethod(""+PaymentMethod.TRANSFER);


			Currency claimCurrency = new Currency();
			tmp.getBatchClaim().setClaimCurrency(claimCurrency);
			
			Currency paymentCurrency = new Currency();
			tmp.getBatchClaim().setPaymentCurrency(paymentCurrency);
	
	
				Integer paymentRecipientId = WebUtil.getParameterInteger (request,"paymentRecipient");
		
			if(paymentRecipientId!=null){
				
				tmp.setPaymentRecipient(paymentRecipientId.toString());
				PaymentRecipient paymentRecipient = this.paymentRecipientService.get(paymentRecipientId);
				tmp.getBatchClaim().setPaymentRecipient(paymentRecipient);
			}else{
				
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){

				Member member = this.memberService.get(memberId);
				tmp.getBatchClaim().setMemberId(member);
				tmp.setMemberId(member.getMemberId().toString());
			}else{
				
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){

				Client client = this.clientService.get(clientId);
				tmp.getBatchClaim().setClientId(client);
				tmp.setClientId(client.getClientId().toString());
				tmp.setClientName(client.getClientName());
			}else{
				
			}


	
	
				Integer batchClaimType = WebUtil.getParameterInteger (request,"batchClaimType");
		
			if(batchClaimType!=null){
				
				tmp.setBatchClaimType(batchClaimType.toString());

				ClaimType claimType = this.claimTypeService.get(batchClaimType);
				tmp.getBatchClaim().setBatchClaimType(claimType);
			}else{
				
			}


	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				
				tmp.setMemberGroupId(memberGroupId.toString());

				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getBatchClaim().setMemberGroupId(memberGroup);
			}else{
				
			}


	
	
				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){

				Provider provider = this.providerService.get(providerId);
				tmp.getBatchClaim().setProviderId(provider);
				tmp.setProviderId(providerId.toString());
			}else{
				
			}
				Integer priorityId = WebUtil.getParameterInteger (request,"priority");
		
			if(priorityId!=null){
				Priority forClass = new Priority();
				forClass.setPriorityId(priorityId);
				
				tmp.setPriority(priorityId.toString());				
				tmp.getBatchClaim().setPriority(forClass);
			}else{
				
			}


		// -- foreign affairs end
			}
			String breadcrumb = "<a href=\"batchclaim?navigation=detail&batchClaimId="+batchClaimId+"\" class=\"linkbreadcrumb\">Detail Batch Claim</a> &nbsp;&nbsp;<b>&gt</b> &nbsp;&nbsp;Update Batch Claim";
			request.setAttribute("breadcrumb", breadcrumb);
		} // mau edit end
		else { // bikin baru
			tmp = new BatchClaimForm();
					 // foreign affairs
			Currency claimCurrency = new Currency();
			tmp.getBatchClaim().setClaimCurrency(claimCurrency);
			
			Currency paymentCurrency = new Currency();
			tmp.getBatchClaim().setPaymentCurrency(paymentCurrency);
			
			tmp.setPaymentMethod(""+PaymentMethod.TRANSFER);
	

	
	
				Integer paymentRecipientId = WebUtil.getParameterInteger (request,"paymentRecipient");
		
			if(paymentRecipientId!=null){

				PaymentRecipient paymentRecipient = this.paymentRecipientService.get(paymentRecipientId);
				tmp.getBatchClaim().setPaymentRecipient(paymentRecipient);
				tmp.setPaymentRecipient(paymentRecipientId.toString());
			}else{
				
			}


	
	
				Integer memberId = WebUtil.getParameterInteger (request,"memberId");
		
			if(memberId!=null){

				Member member = this.memberService.get(memberId);
				tmp.getBatchClaim().setMemberId(member);
				tmp.setMemberId(memberId.toString());
			}else{
				
			}


	
	
				Integer clientId = WebUtil.getParameterInteger (request,"clientId");
		
			if(clientId!=null){

				Client client = this.clientService.get(clientId);
				tmp.getBatchClaim().setClientId(client);
				tmp.setClientId(client.getClientId().toString());
				tmp.setClientName(client.getClientName());
			}else{
				Client forClass = new Client();
//				tmp.setClientId("");
				
				
				// check default client
				
				Collection<Client> clientCollection = clientService.search(null,null,"isDefault",Integer.valueOf(1),0,10);
				
				if (clientCollection != null && clientCollection.size() >0){
					Iterator<Client> iterator = clientCollection.iterator();
					
					if (iterator != null){
						forClass = iterator.next();
					}
				}
				
				tmp.getBatchClaim().setClientId(forClass);
				if(forClass != null && forClass.getClientId() != null){
					tmp.setClientId(forClass.getClientId().toString());
					tmp.setClientName(forClass.getClientName());
				}
				
			}


	
	
				Integer batchClaimType = WebUtil.getParameterInteger (request,"batchClaimType");
		
			if(batchClaimType!=null){
				ClaimType forClass = new ClaimType();
				forClass.setClaimTypeId(batchClaimType);
				tmp.setBatchClaimType(batchClaimType.toString());

				ClaimType claimType = this.claimTypeService.get(batchClaimType);
				tmp.getBatchClaim().setBatchClaimType(claimType);
			}else{
				
			}


	
	
				Integer memberGroupId = WebUtil.getParameterInteger (request,"memberGroupId");
		
			if(memberGroupId!=null){
				MemberGroup memberGroup = this.memberGroupService.get(memberGroupId);
				tmp.getBatchClaim().setMemberGroupId(memberGroup);
				tmp.setMemberGroupId(memberGroupId.toString());
			}else{

			}


	
	
				Integer providerId = WebUtil.getParameterInteger (request,"providerId");
		
			if(providerId!=null){
				Provider provider = this.providerService.get(providerId);
				tmp.getBatchClaim().setProviderId(provider);
				tmp.setProviderId(providerId.toString());
			}else{
				
			}


	
	
				Integer priorityId = WebUtil.getParameterInteger (request,"priority");
		
			if(priorityId!=null){
				Priority forClass = new Priority();
				forClass.setPriorityId(priorityId);
								
				tmp.getBatchClaim().setPriority(forClass);
				tmp.setPriority(priorityId.toString());
			}else{
				
			}


		// -- foreign affairs end
			String breadcrumb = "<a href=\"batchclaim-form\" class=\"linkbreadcrumb\">Register Batch Claim</a>";
			request.setAttribute("breadcrumb", breadcrumb);


		}
																																																														
		result =  tmp;
		return result;
	}

	protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors)
			throws Exception {



		BatchClaimForm batchClaimForm = ( BatchClaimForm ) command;
		BatchClaim batchClaim = batchClaimForm.getBatchClaim();

		System.out.println("ERROR : " + errors);
		
		String breadcrumb = "<a href=\"batchclaim-form\" class=\"linkbreadcrumb\">Register Batch Claim</a>";
		request.setAttribute("breadcrumb", breadcrumb);
//		errors.setNestedPath("batchClaim");
//		getValidator().validate(batchClaim, errors);
//		errors.setNestedPath("");
 	}

	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map model = new HashMap();
	
		
		
		Collection paymentMethod = paymentMethodService.getAll();
		Collection paymentRecipient = paymentRecipientService.getAll();
		Collection claimType = claimTypeService.getAll();
		Collection currency = currencyService.getAll();
		Collection<Priority> priority = priorityService.getAll();
		
		
				
		model.put("paymentRecipient", paymentRecipient);
		model.put("priority", priority);
		model.put("claimType", claimType);
		model.put("paymentMethod", paymentMethod);
		model.put("currencies", currency);
		
		String navigation = WebUtil.getParameterString(request, "navigation", "");
		model.put("navigation", navigation);
		
		
		String[] required = {"ClaimReceiving.ClientId", "ClaimReceiving.ProviderId"};
		
		String[] eqParam = {"receivingStatus","deletedStatus"};
		Object[] eqValue = {ClaimReceiving.RECEIVED,0};
		
		int totalReceiving = claimReceivingService.getTotal(null,null,eqParam,eqValue);
		
		Collection<ClaimReceiving> claimReceiveList = claimReceivingService.search(null,null,eqParam,eqValue,required,0,totalReceiving);
		
		model.put("claimReceiveList", claimReceiveList);
		
		
		
		return model;
	}

	protected ModelAndView onSubmit(
			HttpServletRequest request, HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		BatchClaimForm batchClaimForm = ( BatchClaimForm ) command;
		String redirectURL = "";
		
		

		BatchClaim res = null;
		String alertMsg="";
		try {
		// foreign affairs
			
			String recipient = batchClaimForm.getPaymentRecipient();
			
			
			
			if (recipient != null){
				
				batchClaimForm.getBatchClaim().setProviderId(null);
				batchClaimForm.getBatchClaim().setMemberGroupId(null);
				batchClaimForm.getBatchClaim().setMemberId(null);
				
//				batchClaimForm.getBatchClaim().setTotalClaim(Integer.valueOf(batchClaimForm.getTotalClaim()));
				
				

				if (Integer.valueOf(recipient).intValue() == PaymentRecipient.MEMBER){
					Member member = new Member();
					member.setMemberId(batchClaimForm.getClaimerId());
					
					batchClaimForm.getBatchClaim().setMemberId(member);
					batchClaimForm.getBatchClaim().setMemberName(batchClaimForm.getClaimer());

					
				}
				else if (Integer.valueOf(recipient).intValue() == PaymentRecipient.MEMBER_GROUP){
					MemberGroup memberGroup = new MemberGroup();
					memberGroup.setMemberGroupId(batchClaimForm.getClaimerId());
					batchClaimForm.getBatchClaim().setMemberGroupId(memberGroup);
					batchClaimForm.getBatchClaim().setGroupName(batchClaimForm.getClaimer());
				}
				else if (Integer.valueOf(recipient).intValue() == PaymentRecipient.PROVIDER){
					Provider provider = new Provider();
					provider.setProviderId(batchClaimForm.getClaimerId());
					
					batchClaimForm.getBatchClaim().setProviderId(provider);
					batchClaimForm.getBatchClaim().setProviderName(batchClaimForm.getClaimer());
					
				}
				
			}
			
				
			String unregistered = batchClaimForm.getUnregistered();
			
			if (unregistered != null && unregistered.equals("Y")){
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(Integer.valueOf(BatchClaim.UNREGISTERED));
				batchClaimForm.getBatchClaim().setBatchClaimStatus(status);
			}
			else {
				CaseStatus status = new CaseStatus();
				status.setCaseStatusId(Integer.valueOf(BatchClaim.BATCH_OPEN));
				batchClaimForm.getBatchClaim().setBatchClaimStatus(status);
			
			}
			
	
		// -- foreign affairs end
			String notyet = WebUtil.getParameterString(request,"notyet","");
			if(notyet.equals("true")){
				return showForm(request, response, errors);
			}
			
ActionUser user= securityService.getActionUser(request); 
			
			boolean isUserAuthorized = securityService.isUserAuthorized(user, "CREATEBATCHCLAIM");
			
			if (!isUserAuthorized){
				
				ModelAndView errorResult = new ModelAndView(new RedirectView("errorpage"));	errorResult.addObject("errorType","accessDenied");			
				errorResult.addObject("errorMessage", "You Are Not Authorized for CREATEBATCHCLAIM access");
				return errorResult;
				
			}

			if (batchClaimForm.isNewBatchClaim ()) {
				
				if (batchClaimForm.getBatchClaim() != null){
					batchClaimForm.getBatchClaim().setBatchClaimStatus(new CaseStatus(BatchClaim.BATCH_OPEN));
				}
				res = batchClaimService.create (batchClaimForm.getBatchClaim(),user);

				if (res!=null){
					
					alertMsg = alertProperties.getMessage ("success.create.batchclaim",null,"success",Locale.getDefault());
					
					return new ModelAndView(new RedirectView("batchclaim?navigation=detail&batchClaimId="+batchClaimForm.getBatchClaim().getBatchClaimId()+"&alert="+alertMsg));
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.create.batchclaim",null,"fail",Locale.getDefault());
				}
				
				
			}
			else {
				res = batchClaimService.update (batchClaimForm.getBatchClaim(),user);

				if (res!=null){
					alertMsg = alertProperties.getMessage ("success.update.batchclaim",null,"success",Locale.getDefault());
					return new ModelAndView(new RedirectView("batchclaim?navigation=detail&batchClaimId="+batchClaimForm.getBatchClaim().getBatchClaimId()+"&alert="+alertMsg));
				}
				else {
					alertMsg = alertProperties.getMessage ("fail.update.batchclaim",null,"fail",Locale.getDefault());
					
					request.setAttribute("alert",alertMsg);
					return showForm(request,response,errors);
				}

			}
		}catch (Exception ex) {
			ex.printStackTrace();
			if (batchClaimForm.isNewBatchClaim ()) {
				request.setAttribute ("alert", alertProperties.getMessage ("fail.create.batchclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}else{
				request.setAttribute ("alert", alertProperties.getMessage ("fail.update.batchclaim",null,"fail",Locale.getDefault())+" "+ex.getMessage());
			}
			ex.printStackTrace();
			return showForm(request, response, errors);
		}
		
		String breadcrumb = "<a href=\"batchclaim-form\" class=\"linkbreadcrumb\">Register Batch Claim</a>";
		request.setAttribute("breadcrumb", breadcrumb);
		
		return new ModelAndView(new RedirectView("batchclaim"+"?alert="+alertMsg));
		//return new ModelAndView(new RedirectView(redirectURL));
//		return super.onSubmit(request, response, command, errors);
	}
	protected void initBinder(HttpServletRequest req, ServletRequestDataBinder binder) throws Exception {
		super.initBinder(req, binder);
		
		NumberFormat nf = NumberFormat.getInstance(req.getLocale());
		//java.util.Currency currency = java.util.Currency.getInstance("$");
		nf.setGroupingUsed(true);
		//nf.setCurrency(currency);
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,req.getLocale());
		
			
		System.out.println("LOCALE : " + req.getLocale());
		
		CustomDateEditor cde = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true);
		//CustomDateEditor cde = new CustomDateEditor(df,true);
		binder.registerCustomEditor(Date.class,cde);

		CustomNumberEditor num = new CustomNumberEditor(Number.class,nf,true);
		binder.registerCustomEditor(Number.class,num);

		CustomNumberEditor intNum = new CustomNumberEditor(Integer.class, true);
		binder.registerCustomEditor(Integer.class, intNum);
		
		
        
		binder.registerCustomEditor(java.lang.Double.class,
                new CustomNumberEditor (java.lang.Double.class, nf,true));
		
	}
// class+ 

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

	public PaymentMethodService getPaymentMethodService() {
		return paymentMethodService;
	}

	public void setPaymentMethodService(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}

	public ClaimReceivingService getClaimReceivingService() {
		return claimReceivingService;
	}

	public void setClaimReceivingService(ClaimReceivingService claimReceivingService) {
		this.claimReceivingService = claimReceivingService;
	}

// class- 

}
