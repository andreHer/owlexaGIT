package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BankAccount;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.dto.BankAccountDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.MemberGroupDto;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.dto.ProviderDto;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.BankAccountService;

import com.ametis.cms.dao.ProviderDao;

@WebService(name = "ProviderWebService", 
		endpointInterface = "com.ametis.cms.webservice.IProviderWebService",
		serviceName="ProviderWebService")

public class ProviderWebServiceImpl implements IProviderWebService{

	private ProviderService providerService;
	private BankAccountService bankAccountService;
	
	private ProviderDao  providerDao;
	
	
	public BankAccountService getBankAccountService() {
		return bankAccountService;
	}


	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}


	public ProviderService getProviderService() {
		return providerService;
	}


	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}


	public ProviderDao getProviderDao() {
		return providerDao;
	}


	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}


	public boolean migrateProvider(MemberDto member, String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public ProviderDto getProviderByEDC (String merchantId) {
		ProviderDto result = null;
		
		try {
			System.out.println("MERCHANT ID = " + merchantId);
			
			String[] eqParam = {"deletedStatus","edcCode"};
			Object[] eqValue = {Integer.valueOf(0),merchantId};
			
			int totalProvider = providerService.getTotal(null,null,eqParam,eqValue);
			
			
			Provider provider = providerService.getByProviderCode(merchantId);
			System.out.println("TOTAL PROVIDER :   " + totalProvider);			

			if (provider != null) {
				System.out.println("TERDAPAT PROVIDER : " + provider);
				result = new ProviderDto();
				
				result.setProviderId(provider.getProviderId().toString());
				result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
				result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
				result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
				result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
				result.setCity(provider.getCity() == null ? "" : provider.getCity());
				result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
				result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
				result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
				result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
				//Add by aju on 20150827, deletedStatus on provider
				result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
				if (provider.getEdcCode() != null ) {
					result.setEDC("YA");
				}else{
					result.setEDC("TIDAK");
				}
				
				//add by aju on 20150825, add some field to shown
				result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
				result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
				result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
				result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
				
			}
					

			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean isEDCTerminalRegistered (String merchantId, String terminalId) {
		boolean result = false;
		try {
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

	
	@SuppressWarnings("unchecked")
	public Collection<ProviderDto> searchProviderByProviderName(String providerName) {//buatan Luthfi, untuk menampilkan data provider berdasarkan provider name 
		// TODO Auto-generated method stub
		Collection<ProviderDto> resultList = null;
		
		Vector vProvider = new Vector();
		Vector voProvider = new Vector();
		
		Vector vStatus = new Vector();
		Vector voStatus = new Vector();

		try {
			vProvider.add("providerName");
			vStatus.add("deletedStatus");
			voProvider.add(providerName);
			voStatus.add(Integer.valueOf(0));
			
			//add by aju on 20151015, add only the ACTIVE and NON PROSPEK(PROVIDER), request NTW
			vStatus.add("statusId.statusId");
			voStatus.add(Integer.valueOf(1));
			vStatus.add("statusProspek");
			voStatus.add(Integer.valueOf(2));
			
			String sProvider[] = new String[vProvider.size()];
			vProvider.toArray(sProvider);//done
			Object oProvider[] = new Object[vProvider.size()];
			voProvider.toArray(oProvider);
			
			String sStatus[] = new String[vStatus.size()];
			vStatus.toArray(sStatus);//done
			Object oStatus[] = new Object[vStatus.size()];
			voStatus.toArray(oStatus);

			String[] required = {"Provider.ProviderCategoryId"};
			
			Collection<Provider> providerList = providerService.searchProvider( sProvider, oProvider, sStatus, oStatus, required);
			

			if (providerList != null ) {
				resultList = new Vector<ProviderDto>();
				Iterator<Provider> iterator = providerList.iterator();

				while (iterator.hasNext()) {
					ProviderDto result = new ProviderDto();
					Provider provider = iterator.next();
//					System.out.println(provider.getProviderId());
					
					result.setProviderId(provider.getProviderId().toString());
					result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
					result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
					result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
					result.setCity(provider.getCity() == null ? "" : provider.getCity());
					result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
					result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
					result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
					result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
					//Add by aju on 20150827, deletedStatus on provider
					result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
					if (provider.getEdcCode() != null ) {
						result.setEDC("YA");
					}else{
						result.setEDC("TIDAK");
					}
					
					//add by aju on 20150825, add some field to shown
					result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
					result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
					result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
					result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
					
//					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
//					result.setBank(provider.getBank() == null ? "" : provider.getBank());
//					result.setBankAccount(provider.getBankAccount() == null ? "" : provider.getBankAccount());
//					result.setBankBranch(provider.getBankBranch() == null ? "" : provider.getBankBranch());
					
					//harap hati2 untuk mendefinisikan hal dibawah (ini digunakan untuk mengambil data account berdasarkan providerid)
//					if (provider != null) {
//						
//						Vector vProviderName = new Vector();
//						Vector voProviderName = new Vector();
//						
//						vProviderName.add("deletedStatus");
//						vProviderName.add("providerId.providerId");
//						voProviderName.add(Integer.valueOf(0));
//						voProviderName.add(provider.getProviderId());
//						
//						String sProviderName[] = new String[vProviderName.size()];
//						vProviderName.toArray(sProviderName);//done
//						Object oProviderName[] = new Object[vProviderName.size()];
//						voProviderName.toArray(oProviderName);
//						
//						Collection<BankAccount> bankAccountList = bankAccountService.search(null,  null, sProviderName, oProviderName);
//						
//						if (bankAccountList != null){
//							Iterator<BankAccount> iterator2 = bankAccountList.iterator();
//							Collection<BankAccountDto> resultList2 = new Vector<BankAccountDto>();
//							
//							while (iterator2.hasNext()){
//								BankAccount bankAccount = iterator2.next();
//								BankAccountDto result2 = new BankAccountDto();
//								result2.setAccountName(bankAccount.getAccountName());
//								resultList2.add(result2);
//								
//							}
//							result.setAccountName(resultList2);
//						}
//					}
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}
	
	public Collection<ProviderDto> searchProviderByProviderCategory(String providerCategory) {//buatan Luthfi, untuk menampilkan data provider berdasarkan provider kategori 
		// TODO Auto-generated method stub
		Collection<ProviderDto> resultList = null;
		
		Vector vProvider = new Vector();
		Vector voProvider = new Vector();
		
		Vector vStatus = new Vector();
		Vector voStatus = new Vector();

		try {
			vProvider.add("providerCategoryId.providerCategoryName");
			vStatus.add("deletedStatus");
			voProvider.add(providerCategory);
			voStatus.add(Integer.valueOf(0));
			
			//add by aju on 20151015, add only the ACTIVE and NON PROSPEK(PROVIDER), request NTW
			vStatus.add("statusId.statusId");
			voStatus.add(Integer.valueOf(1));
			vStatus.add("statusProspek");
			voStatus.add(Integer.valueOf(2));
			
			String sProvider[] = new String[vProvider.size()];
			vProvider.toArray(sProvider);//done
			Object oProvider[] = new Object[vProvider.size()];
			voProvider.toArray(oProvider);
			
			String sStatus[] = new String[vStatus.size()];
			vStatus.toArray(sStatus);//done
			Object oStatus[] = new Object[vStatus.size()];
			voStatus.toArray(oStatus);

			String[] required = {"Provider.ProviderCategoryId"};
			
			Collection<Provider> providerList = providerService.searchProvider( sProvider, oProvider, sStatus, oStatus, required);
			

			if (providerList != null ) {
				resultList = new Vector<ProviderDto>();
				Iterator<Provider> iterator = providerList.iterator();

				while (iterator.hasNext()) {
					ProviderDto result = new ProviderDto();
					Provider provider = iterator.next();
//					System.out.println(provider.getProviderId());
					
					result.setProviderId(provider.getProviderId().toString());
					result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
					result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
					result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
					result.setCity(provider.getCity() == null ? "" : provider.getCity());
					result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
					result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
					result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
					result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
					//Add by aju on 20150827, deletedStatus on provider
					result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
					if (provider.getEdcCode() != null ) {
						result.setEDC("YA");
					}else{
						result.setEDC("TIDAK");
					}
					
					//add by aju on 20150825, add some field to shown
					result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
					result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
					result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
					result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
					
//					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
//					result.setBank(provider.getBank() == null ? "" : provider.getBank());
//					result.setBankAccount(provider.getBankAccount() == null ? "" : provider.getBankAccount());
//					result.setBankBranch(provider.getBankBranch() == null ? "" : provider.getBankBranch());
					
					//harap hati2 untuk mendefinisikan hal dibawah (ini digunakan untuk mengambil data account berdasarkan providerid)
//					if (provider != null) {
//						
//						Vector vProviderName = new Vector();
//						Vector voProviderName = new Vector();
//						
//						vProviderName.add("deletedStatus");
//						vProviderName.add("providerId.providerId");
//						voProviderName.add(Integer.valueOf(0));
//						voProviderName.add(provider.getProviderId());
//						
//						String sProviderName[] = new String[vProviderName.size()];
//						vProviderName.toArray(sProviderName);//done
//						Object oProviderName[] = new Object[vProviderName.size()];
//						voProviderName.toArray(oProviderName);
//						
//						Collection<BankAccount> bankAccountList = bankAccountService.search(null,  null, sProviderName, oProviderName);
//						
//						if (bankAccountList != null){
//							Iterator<BankAccount> iterator2 = bankAccountList.iterator();
//							Collection<BankAccountDto> resultList2 = new Vector<BankAccountDto>();
//							
//							while (iterator2.hasNext()){
//								BankAccount bankAccount = iterator2.next();
//								BankAccountDto result2 = new BankAccountDto();
//								result2.setAccountName(bankAccount.getAccountName());
//								resultList2.add(result2);
//								
//							}
//							result.setAccountName(resultList2);
//						}
//					}
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}
	
	public Collection<ProviderDto> searchProviderByProviderStatus(Integer providerStatus) {//buatan Luthfi, untuk menampilkan data provider berdasarkan provider status 
		// TODO Auto-generated method stub
		Collection<ProviderDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();

		try {
			vEqP.add("deletedStatus");
			vEqQ.add(providerStatus);

			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			String[] required = {"Provider.ProviderCategoryId"};
			
			Collection<Provider> providerList = providerService.searchProviderByProviderStatus(sEqP, sEqQ, required);


			if (providerList != null ) {
				resultList = new Vector<ProviderDto>();
				Iterator<Provider> iterator = providerList.iterator();

				while (iterator.hasNext()) {
					ProviderDto result = new ProviderDto();
					Provider provider = iterator.next();
					
					result.setProviderId(provider.getProviderId().toString());
					result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
					result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
					result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
					result.setCity(provider.getCity() == null ? "" : provider.getCity());
					result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
					result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
					result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
					result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
					//Add by aju on 20150827, deletedStatus on provider
					result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
					if (provider.getEdcCode() != null ) {
						result.setEDC("YA");
					}else{
						result.setEDC("TIDAK");
					}
					
					//add by aju on 20150825, add some field to shown
					result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
					result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
					result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
					result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
					
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}
	
	public Collection<ProviderDto> searchProviderByKota(String kota) {//buatan Luthfi, untuk menampilkan data provider berdasarkan kota
		// TODO Auto-generated method stub
		Collection<ProviderDto> resultList = null;
		
		Vector vProvider = new Vector();
		Vector voProvider = new Vector();
		
		Vector vStatus = new Vector();
		Vector voStatus = new Vector();

		try {
			vProvider.add("city");
			vStatus.add("deletedStatus");
			voProvider.add(kota);
			voStatus.add(Integer.valueOf(0));
			
			//add by aju on 20151015, add only the ACTIVE and NON PROSPEK(PROVIDER), request NTW
			vStatus.add("statusId.statusId");
			voStatus.add(Integer.valueOf(1));
			vStatus.add("statusProspek");
			voStatus.add(Integer.valueOf(2));
			
			String sProvider[] = new String[vProvider.size()];
			vProvider.toArray(sProvider);//done
			Object oProvider[] = new Object[vProvider.size()];
			voProvider.toArray(oProvider);
			
			String sStatus[] = new String[vStatus.size()];
			vStatus.toArray(sStatus);//done
			Object oStatus[] = new Object[vStatus.size()];
			voStatus.toArray(oStatus);

			String[] required = {"Provider.ProviderCategoryId"};
			
			Collection<Provider> providerList = providerService.searchProvider( sProvider, oProvider, sStatus, oStatus, required);


			if (providerList != null ) {
				resultList = new Vector<ProviderDto>();
				Iterator<Provider> iterator = providerList.iterator();

				while (iterator.hasNext()) {
					ProviderDto result = new ProviderDto();
					Provider provider = iterator.next();
					
					result.setProviderId(provider.getProviderId().toString());
					result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
					result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
					result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
					result.setCity(provider.getCity() == null ? "" : provider.getCity());
					result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
					result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
					result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
					result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
					//Add by aju on 20150827, deletedStatus on provider
					result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
					if (provider.getEdcCode() != null ) {
						result.setEDC("YA");
					}else{
						result.setEDC("TIDAK");
					}
					
					//add by aju on 20150825, add some field to shown
					result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
					result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
					result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
					result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
					
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}
	
	public Collection<ProviderDto> searchProviderByProvinsi(String provinsi) {//buatan Luthfi, untuk menampilkan data provider berdasarkan filteran provinsi 
		// TODO Auto-generated method stub
		Collection<ProviderDto> resultList = null;
		
		Vector vProvider = new Vector();
		Vector voProvider = new Vector();
		
		Vector vStatus = new Vector();
		Vector voStatus = new Vector();

		try {
			vProvider.add("province");
			vStatus.add("deletedStatus");
			voProvider.add(provinsi);
			voStatus.add(Integer.valueOf(0));
			
			//add by aju on 20151015, add only the ACTIVE and NON PROSPEK(PROVIDER), request NTW
			vStatus.add("statusId.statusId");
			voStatus.add(Integer.valueOf(1));
			vStatus.add("statusProspek");
			voStatus.add(Integer.valueOf(2));
			
			String sProvider[] = new String[vProvider.size()];
			vProvider.toArray(sProvider);//done
			Object oProvider[] = new Object[vProvider.size()];
			voProvider.toArray(oProvider);
			
			String sStatus[] = new String[vStatus.size()];
			vStatus.toArray(sStatus);//done
			Object oStatus[] = new Object[vStatus.size()];
			voStatus.toArray(oStatus);

			String[] required = {"Provider.ProviderCategoryId"};
			
			Collection<Provider> providerList = providerService.searchProvider( sProvider, oProvider, sStatus, oStatus, required);


			if (providerList != null ) {
				resultList = new Vector<ProviderDto>();
				Iterator<Provider> iterator = providerList.iterator();

				while (iterator.hasNext()) {
					ProviderDto result = new ProviderDto();
					Provider provider = iterator.next();
					
					result.setProviderId(provider.getProviderId().toString());
					result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
					result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
					result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
					result.setCity(provider.getCity() == null ? "" : provider.getCity());
					result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
					result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
					result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
					result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
					//Add by aju on 20150827, deletedStatus on provider
					result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
					if (provider.getEdcCode() != null ) {
						result.setEDC("YA");
					}else{
						result.setEDC("TIDAK");
					}
					
					//add by aju on 20150825, add some field to shown
					result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
					result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
					result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
					result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
					
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
		
	}
	
	public Collection<ProviderDto> getProviderList() {//buatan Luthfi, untuk menampilkan all data provider 
		// TODO Auto-generated method stub
		Collection<ProviderDto> resultList = null;
		
		Vector vProvider = new Vector();
		Vector voProvider = new Vector();
		
		Vector vStatus = new Vector();
		Vector voStatus = new Vector();

		try {
			vStatus.add("deletedStatus");
			voStatus.add(Integer.valueOf(0));
			
			//add by aju on 20151015, add only the ACTIVE and NON PROSPEK(PROVIDER), request NTW
			vStatus.add("statusId.statusId");
			voStatus.add(Integer.valueOf(1));
			vStatus.add("statusProspek");
			voStatus.add(Integer.valueOf(2));
			
			String sStatus[] = new String[vStatus.size()];
			vStatus.toArray(sStatus);//done
			Object oStatus[] = new Object[vStatus.size()];
			voStatus.toArray(oStatus);

			String[] required = {"Provider.ProviderCategoryId"};
			
			Collection<Provider> providerList = providerService.getProviderList(sStatus, oStatus, required);


			if (providerList != null ) {
				resultList = new Vector<ProviderDto>();
				Iterator<Provider> iterator = providerList.iterator();

				while (iterator.hasNext()) {
					ProviderDto result = new ProviderDto();
					Provider provider = iterator.next();
					
					result.setProviderId(provider.getProviderId().toString());
					result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
					result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
					result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
					result.setCity(provider.getCity() == null ? "" : provider.getCity());
					result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
					result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
					result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
					result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
					//Add by aju on 20150827, deletedStatus on provider
					result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
					if (provider.getEdcCode() != null ) {
						result.setEDC("YA");
					}else{
						result.setEDC("TIDAK");
					}
					
					//add by aju on 20150825, add some field to shown
					result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
					result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
					result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
					result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
					
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
		
	}
	
	//Add by aju on 20150827, for getting the provider by providerId
	public ProviderDto getProviderByProviderId (String providerId) {
		ProviderDto result = null;
		
		try {
			System.out.println("ProviderWebServiceImpl::getProviderByProviderId : " + providerId);
			java.io.Serializable pid = Integer.parseInt(providerId);
			
			String[] eqParam = {"providerId"};
			Object[] eqValue = {pid};
			
			int totalProvider = providerService.getTotal(null,null,eqParam,eqValue);
			
			
			Provider provider = providerService.get(pid);
			System.out.println("TOTAL PROVIDER :   " + totalProvider);			

			if (provider != null) {
				System.out.println("TERDAPAT PROVIDER : " + provider);
				result = new ProviderDto();
				
				result.setProviderId(provider.getProviderId().toString());
				result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
				result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
				result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
				result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
				result.setCity(provider.getCity() == null ? "" : provider.getCity());
				result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
				result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
				result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
				result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
				//Add by aju on 20150827, deletedStatus on provider
				result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
				if (provider.getEdcCode() != null ) {
					result.setEDC("YA");
				}else{
					result.setEDC("TIDAK");
				}
				
				//add by aju on 20150825, add some field to shown
				result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
				result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
				result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
				result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
				
				
			}
					

			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	//Add by aju on 20151027, for getting the provider by maximum distance in Kilometer
	public Collection<ProviderDto> getNearbyProviderInMaximalKilometer(String latitude, String longitude, String maxDistance) {
		Collection<ProviderDto> resultList = null;
		
		try {
			System.out.println("ProviderWebServiceImpl::getNearbyProviderInMaximalKilometer(" + latitude + "," + longitude +", " + maxDistance + ")");
			
			//check if numeric
			if(!org.apache.commons.lang.math.NumberUtils.isNumber(latitude)){
				System.out.println("Latitude NotNumeric detected fufufu :D, return null deh :D");
				return resultList;
			}
			
			if(!org.apache.commons.lang.math.NumberUtils.isNumber(longitude)){
				System.out.println("Longitude NotNumeric detected fufufu :D, return null deh :D");
				return resultList;
			}
			
			if(!org.apache.commons.lang.math.NumberUtils.isNumber(maxDistance)){
				System.out.println("MaxDistance NotNumeric detected fufufu :D, return null deh :D");
				return resultList;
			}
			
			String[] required = {"Provider.ProviderCategoryId"};
			
			Collection<Provider> providerList = providerService.getNearbyProviderByMaxKilometer(Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(maxDistance), required);
			
			if (providerList != null ) {
				resultList = new Vector<ProviderDto>();
				Iterator<Provider> iterator = providerList.iterator();

				while (iterator.hasNext()) {
					ProviderDto result = new ProviderDto();
					Provider provider = iterator.next();
					
					result.setProviderId(provider.getProviderId().toString());
					result.setProviderCode(provider.getProviderCode() == null ? "" : provider.getProviderCode());
					result.setProviderName(provider.getProviderName() == null ? "" : provider.getProviderName());
					result.setAddress(provider.getAddress() == null ? "" : provider.getAddress());
					result.setProviderCategory(provider.getProviderCategoryId().getProviderCategoryName() == null ? "" : provider.getProviderCategoryId().getProviderCategoryName());
					result.setCity(provider.getCity() == null ? "" : provider.getCity());
					result.setProvince(provider.getProvince() == null ? "" : provider.getProvince());
					result.setCountry(provider.getCountry() == null ? "" : provider.getCountry());
					result.setProviderEDCCode(provider.getEdcCode() == null ? "" : provider.getEdcCode());
					result.setStatus(provider.getStatusId().getStatusId() == null ? "" : provider.getStatusId().getStatusId().toString());
					//Add by aju on 20150827, deletedStatus on provider
					result.setDeletedStatus(provider.getDeletedStatus() == null ? "" : provider.getDeletedStatus().toString());
					if (provider.getEdcCode() != null ) {
						result.setEDC("YA");
					}else{
						result.setEDC("TIDAK");
					}
					
					//add by aju on 20150825, add some field to shown
					result.setTelephone(provider.getTelephone() == null ? "" : provider.getTelephone().toString());
					result.setFaximile(provider.getFaximile() == null ? "" : provider.getFaximile().toString());
					result.setLongitude(provider.getLongitude() == null ? "" : provider.getLongitude().toString());
					result.setLatitude(provider.getLatitude() == null ? "" : provider.getLatitude().toString());
					
					resultList.add(result);
				}
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return resultList;
	}
	
}
