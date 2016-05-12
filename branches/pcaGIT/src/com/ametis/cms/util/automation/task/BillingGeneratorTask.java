package com.ametis.cms.util.automation.task;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ClientContract;
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.BillingItemService;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ClientContractItemService;
import com.ametis.cms.service.ClientContractService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.InvoiceService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyBillingRateService;
import com.ametis.cms.service.PolicyService;

public class BillingGeneratorTask {

	private InvoiceService invoiceService;
	private MemberImportService memberImportService;
	private BillingItemService billingItemService;
	private PolicyService policyService;
	private ClientContractService clientContractService;
	private ClientContractItemService clientContractItemService;
	private PolicyBillingRateService policyBillingRateService;
	private ClientService clientService;
	
	
	
	
	public ClientService getClientService() {
		return clientService;
	}


	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}


	public PolicyBillingRateService getPolicyBillingRateService() {
		return policyBillingRateService;
	}


	public void setPolicyBillingRateService(PolicyBillingRateService policyBillingRateService) {
		this.policyBillingRateService = policyBillingRateService;
	}


	public ClientContractService getClientContractService() {
		return clientContractService;
	}


	public void setClientContractService(ClientContractService clientContractService) {
		this.clientContractService = clientContractService;
	}


	public ClientContractItemService getClientContractItemService() {
		return clientContractItemService;
	}


	public void setClientContractItemService(ClientContractItemService clientContractItemService) {
		this.clientContractItemService = clientContractItemService;
	}


	public PolicyService getPolicyService() {
		return policyService;
	}


	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}


	public MemberImportService getMemberImportService() {
		return memberImportService;
	}


	public void setMemberImportService(MemberImportService memberImportService) {
		this.memberImportService = memberImportService;
	}


	public BillingItemService getBillingItemService() {
		return billingItemService;
	}


	public void setBillingItemService(BillingItemService billingItemService) {
		this.billingItemService = billingItemService;
	}


	public InvoiceService getInvoiceService() {
		return invoiceService;
	}


	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}


	public void generateBilling(){
		System.out.println("GENERATE BILLING");
		
		/**
		 * first step .. do correction on member_import
		 */
		
		try {
		
			Collection<MemberImport> nullPolicyImportList = memberImportService.getNullPolicyImport();
			ActionUser actionUser = new ActionUser();
			User theUser = new User();
			theUser.setUsername("generator");
			actionUser.setUser(theUser);
			
			if (nullPolicyImportList != null){
				for (Iterator iterator = nullPolicyImportList.iterator(); iterator.hasNext();) {
					MemberImport memberImport = (MemberImport) iterator.next();
					
					if (memberImport != null){
						String policyNumber = memberImport.getPolicyNumber();
						if (policyNumber != null && !policyNumber.equalsIgnoreCase("")){
							Policy policy = policyService.getActivePolicyByNumber(policyNumber);
							if (policy != null){
								memberImport.setPolicyId(policy);
								
								memberImportService.update(memberImport, actionUser);
							}
						}
					}
				}
			}
			/**
			 * Second step .. check all draft Invoice to be Finalized
			 */
			
			String[] eqParamDraft = {"deletedStatus","invoiceStatus.paymentStatusId"};
			Object[] eqValueDraft = {0,PaymentStatus.PAYMENT_DRAFT};
			
			int totalDraftInvoice = invoiceService.getTotal(null,null,eqParamDraft,eqValueDraft);
			Collection<Invoice> invoiceList = invoiceService.search(null,null,eqParamDraft,eqValueDraft,0,totalDraftInvoice);
			
			for (Iterator iterator = invoiceList.iterator(); iterator.hasNext();) {
				Invoice invoice = (Invoice) iterator.next();
				
				if (invoice != null){
					invoiceService.finalizeInvoice(invoice.getInvoiceId(), actionUser);
				}
			}
			
			
			/**
			 * Third step .. call generateInvoice in InvoiceService class
			 */
			
			Collection<Client> clientList = clientService.getAll();
			
			for (Iterator iterator = clientList.iterator(); iterator.hasNext();) {
				Client client = (Client) iterator.next();
				
				if (client != null){
					invoiceService.generateClientInvoice(client.getClientId(), actionUser);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
