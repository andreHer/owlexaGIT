package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.dto.BankAccountDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.dto.ProviderDto;
@WebService (targetNamespace="http://ametis.co.id/services/ProviderWebService")

public interface IProviderWebService {


	@WebMethod
	public boolean migrateProvider (MemberDto member, String username);
	@WebMethod
	public ProviderDto getProviderByEDC (String merchantId);
	@WebMethod
	public boolean isEDCTerminalRegistered (String merchantId, String terminalId);
	@WebMethod
	public Collection<ProviderDto> searchProviderByProviderName (String providerName);//buatan luthfi
	@WebMethod
	public Collection<ProviderDto> searchProviderByProviderCategory (String providerCategory);//buatan luthfi
	@WebMethod
	public Collection<ProviderDto> searchProviderByProviderStatus (Integer providerStatus);//buatan luthfi
	@WebMethod
	public Collection<ProviderDto> searchProviderByKota(String kota);//buatan luthfi	
	@WebMethod
	public Collection<ProviderDto> searchProviderByProvinsi(String provinsi);//buatan luthfi
	@WebMethod
	public Collection<ProviderDto> getProviderList();
	//Add by aju on 20150827
	@WebMethod
	public ProviderDto getProviderByProviderId(String providerId);
	//Add by aju on 20151027
	@WebMethod
	public Collection<ProviderDto> getNearbyProviderInMaximalKilometer(String latitude, String longitude, String maxDistance);
	
}
