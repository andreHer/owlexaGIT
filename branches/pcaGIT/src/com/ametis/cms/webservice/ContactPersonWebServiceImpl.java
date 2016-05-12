package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Benefit;
import com.ametis.cms.datamodel.BenefitUsageType;
import com.ametis.cms.datamodel.CardType;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ContactPerson;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyClausul;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.ProductType;
import com.ametis.cms.datamodel.ProductTypePoliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.ClientDto;
import com.ametis.cms.dto.ContactPersonDto;
import com.ametis.cms.dto.MemberBenefitDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.MemberGroupDto;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.dto.ProviderDto;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ContactPersonService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.service.MemberClausulService;
import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberImportService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyClausulService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProductTypePoliklinikService;
import com.ametis.cms.service.ProductTypeService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderTypeService;
import com.ametis.cms.service.UserService;
import com.ametis.cms.util.StringUtil;

@WebService(name = "ContactPersonWebService", 
		endpointInterface = "com.ametis.cms.webservice.IContactPersonWebService",
		serviceName="ContactPersonWebService")
public class ContactPersonWebServiceImpl  implements IContactPersonWebService {

	private ContactPersonService contactPersonService;
	
	private ProviderService providerService;
	
	

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ContactPersonService getContactPersonService() {
		return contactPersonService;
	}

	public void setContactPersonService (ContactPersonService contactPersonService) {
		this.contactPersonService = contactPersonService;
	}
	


	public Collection<ContactPersonDto> searchContactPersonByName (String name) {//buatan Luthfi, untuk menampilkan data contact berdasarkan nama contact 
		// TODO Auto-generated method stub
		Collection<ContactPersonDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		
		Vector Name = new Vector();
		Vector oName = new Vector();
		
		Vector ProviderName = new Vector();
		Vector oProviderName = new Vector();

		try {
			vEqP.add("deletedStatus");
			Name.add("name");
//			ProviderName.add("providerName");
			vEqQ.add(Integer.valueOf(0));
			oName.add(name);
//			oProviderName.add(name);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			String sName[] = new String[Name.size()];
			Name.toArray(sName);
			Object voName[] = new Object[Name.size()];
			oName.toArray(voName);
			
//			String sProviderName[] = new String[ProviderName.size()];
//			ProviderName.toArray(sProviderName);
//			Object voProviderName[] = new Object[ProviderName.size()];
//			oProviderName.toArray(voProviderName);
			
			String[] required = {"ContactPerson.ProviderId", "ContactPerson.MemberGroupId", "ContactPerson.ClientId"};
			
			Collection<ContactPerson> contactPersonList = contactPersonService.searchContactPersonByName(sEqP, sEqQ, sName, voName, required);


			if (contactPersonList != null ) {
				resultList = new Vector<ContactPersonDto>();
				Iterator<ContactPerson> iterator = contactPersonList.iterator();

				while (iterator.hasNext()) {
					ContactPersonDto result = new ContactPersonDto();
					ContactPerson contactPerson = iterator.next();
					
					if(contactPerson != null)
					{
						result.setContactPersonId(contactPerson.getContactPersonId() == null ? "" : contactPerson.getContactPersonId().toString());
						result.setName(contactPerson.getName() == null ? "" : contactPerson.getName());
						result.setEmail(contactPerson.getEmail() == null ? "" : contactPerson.getEmail());
						result.setJobPosition(contactPerson.getJobPosition() == null ? "" : contactPerson.getJobPosition());
						result.setTelephone(contactPerson.getTelephone() == null ? "" : contactPerson.getTelephone());
						result.setDepartment(contactPerson.getDepartment() == null ? "" : contactPerson.getDepartment());
						result.setHandphone(contactPerson.getHandphone() == null ? "" : contactPerson.getHandphone());
					}

					resultList.add(result);
					
//					Collection<Provider> providerList = providerService.searchContactPersonByName(sEqP, sEqQ, sProviderName, voProviderName);
//					
//					if (providerList != null ) {
//						resultList = new Vector<ContactPersonDto>();
//						Iterator<Provider> iterator2 = providerList.iterator();
//
//						while (iterator.hasNext()) {
//							ContactPersonDto result2 = new ContactPersonDto();
//							Provider provider = iterator2.next();
//							
//							result2.setContactPersonId(provider.getProviderId() == null ? "" : provider.getProviderId().toString());
//							result2.setName(provider.getProviderName() == null ? "" : provider.getProviderName());
//							result2.setEmail(provider.getEmail() == null ? "" : provider.getEmail());
//							result.setJobPosition(contactPerson.getJobPosition() == null ? "" : contactPerson.getJobPosition());
//							result2.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone());
//							result.setDepartment(contactPerson.getDepartment() == null ? "" : contactPerson.getDepartment());
//							result.setHandphone(contactPerson.getHandphone() == null ? "" : contactPerson.getHandphone());
//
//							resultList.add(result2);
//						}
//					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}

}
