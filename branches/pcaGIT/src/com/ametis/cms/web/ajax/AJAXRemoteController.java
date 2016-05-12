package com.ametis.cms.web.ajax;

import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.ProviderCategory;
import com.ametis.cms.datamodel.ProviderGroup;
import com.ametis.cms.datamodel.RefCity;
import com.ametis.cms.datamodel.RefProvince;
import com.ametis.cms.datamodel.RefRegion;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.DependentService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProviderCategoryService;
import com.ametis.cms.service.ProviderGroupService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.RefCityService;
import com.ametis.cms.service.RefProvinceService;
import com.ametis.cms.service.RefRegionService;
import com.ametis.cms.util.StringUtil;

public class AJAXRemoteController {
	
	private MemberService memberService;
	private DependentService dependentService;
	private ItemCategoryService itemCategoryService;
	private DiagnosisService diagnosisService;
	private ConfigurationService configurationService;
	private ProviderService providerService;
	private RefRegionService refRegionService;
	private RefProvinceService refProvinceService;
	private RefCityService refCityService;
	private ProviderCategoryService providerCategoryService;
	private ProviderGroupService providerGroupService;
	
	
	
	
	
	public ProviderGroupService getProviderGroupService() {
		return providerGroupService;
	}
	public void setProviderGroupService(ProviderGroupService providerGroupService) {
		this.providerGroupService = providerGroupService;
	}
	public ProviderService getProviderService() {
		return providerService;
	}
	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}
	public RefRegionService getRefRegionService() {
		return refRegionService;
	}
	public void setRefRegionService(RefRegionService refRegionService) {
		this.refRegionService = refRegionService;
	}
	public RefProvinceService getRefProvinceService() {
		return refProvinceService;
	}
	public void setRefProvinceService(RefProvinceService refProvinceService) {
		this.refProvinceService = refProvinceService;
	}
	public RefCityService getRefCityService() {
		return refCityService;
	}
	public void setRefCityService(RefCityService refCityService) {
		this.refCityService = refCityService;
	}
	public ProviderCategoryService getProviderCategoryService() {
		return providerCategoryService;
	}
	public void setProviderCategoryService(ProviderCategoryService providerCategoryService) {
		this.providerCategoryService = providerCategoryService;
	}
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	public DependentService getDependentService() {
		return dependentService;
	}
	public void setDependentService(DependentService dependentService) {
		this.dependentService = dependentService;
	}
	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}
	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}
	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public String generateProviderCode (String categoryId, String cityId,String provGroupId){
		String result = "";
		
		try {
			
			
			
			RefCity city = refCityService.get(Integer.valueOf(cityId));
			ProviderCategory providerCategory = providerCategoryService.get(Integer.valueOf(categoryId));
			
			Configuration configuration = configurationService.getSystemConfiguration();
			// {codeProvince}{codeCity}{providerGroupCode}{provCategoryCode}{counter}
			String provCodeTemplate = configuration.getProviderCodeTemplate() == null ?
					"{codeProvince}{codeCity}{providerGroupCode}{provCategoryCode}{counter}":configuration.getProviderCodeTemplate();
			
			
			if (city != null){
				//provCodeTemplate = provCodeTemplate.replace("{codeCity}", city.getCityCode());
				result += city.getCityCode();
			}
			
			if (provGroupId != null && !provGroupId.equalsIgnoreCase("")){
				ProviderGroup providerGroup = providerGroupService.get(Integer.valueOf(provGroupId));
				if (providerGroup != null){
					result += providerGroup.getProviderGroupCode();
				}
				
			}
			else {
				result += "00";
			}
			if (providerCategory != null){
				//provCodeTemplate = provCodeTemplate.replace("{provCategoryCode}", providerCategory.getProviderCategoryCode());
				result += providerCategory.getProviderCategoryCode();				
			}
			
			if ( city != null && providerCategory != null){
				String[] eqParam = {"providerCategoryId.providerCategoryId","cityId.id","deletedStatus"};
				Object[] eqValue = {providerCategory.getProviderCategoryId(),city.getId(),0};
				
				
				
				int totalProvider = providerService.getTotal(null,null,eqParam,eqValue)+1;
				
				String counter = StringUtil.paddingCardNumber(totalProvider+"", 3);
				
				//provCodeTemplate = provCodeTemplate.replace("{counter}", counter);
				result += counter;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public String test(){
		return "test";
	}
	public Member getMember (){
		Member member = new Member();
		
		try {
			
			member = memberService.get(Integer.valueOf(1));
			
			System.out.println("MEMBER : " + member);
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return member;
	}
	public String[][] searchByMemberNumber (){
		String[][] result = new String[2][2];
		
		try {
			result[0][0] = "021-12";
			result[0][1] = "Adhityo Priyambodo";
			
			System.out.println ("TEST");
			System.out.println (result);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
