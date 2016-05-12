package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

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
import com.ametis.cms.dto.MemberBenefitDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.MemberGroupDto;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.ClientService;
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

@WebService(name = "ClientWebService", 
		endpointInterface = "com.ametis.cms.webservice.IClientWebService",
		serviceName="ClientWebService")
public class ClientWebServiceImpl  implements IClientWebService {

	private ClientService clientService;
	
	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Collection<ClientDto> getClientList() {//buatan Luthfi, penambahan method getclientlist untuk mengambil semua data client  
		// TODO Auto-generated method stub
		Collection<ClientDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();

		try {
			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));
			
			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			Collection<Client> clientList = clientService.getAll(sEqP, sEqQ);

			if (clientList != null ) {
				resultList = new Vector<ClientDto>();
				Iterator<Client> iterator = clientList.iterator();

				while (iterator.hasNext()) {
					ClientDto result = new ClientDto();
					Client client = iterator.next();

					result.setClientId(client.getClientId() == null ? "" : client.getClientId().toString());
					result.setClientName(client.getClientName() == null ? "" : client.getClientName());
					result.setClientCode(client.getClientCode() == null ? "" : client.getClientCode());
					result.setJoinDate(client.getRegistrationDate() == null ? "" : client.getRegistrationDate().toString());
					result.setExpireDate(client.getExpireDate() == null ? "" : client.getExpireDate().toString());
					result.setStatus(client.getDeletedStatus() == null ? "" : client.getDeletedStatus().toString());

					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

}
