package com.ametis.cms.service;

import java.sql.Date;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ametis.cms.dto.ClaimStatisticDto;
import com.ametis.cms.dto.PerformanceStatisticDto;

public interface ClaimReportService {

	/*
	 * Output :
	 * 
	 * Patient
	 * Membership Number
	 * Relationship
	 * No Claim
	 * Claim Charge
	 * Benefit Paid
	 * % Total
	 * 
	 */
	
	public PerformanceStatisticDto generateClaimMonitor() throws Exception;
	
	public Collection<ClaimStatisticDto> generateTop10ClaimantReport(Integer clientId) throws Exception;
	public Collection<ClaimStatisticDto> generateTop10DiagnosisReport(Integer clientId) throws Exception;
	public Collection<ClaimStatisticDto> generateTop10ProviderReport(Integer clientId) throws Exception;
	public Collection<ClaimStatisticDto> generateTop10ClaimUpgrade(Integer clientId) throws Exception;

	public Collection<ClaimStatisticDto> generateTopXDiagnosisReport (Integer policyId, Integer caseCategoryId, Date start, Date end, Integer topX) throws Exception;
	public Collection<ClaimStatisticDto> generateTopXProviderReport (Integer policyId, Integer caseCategoryId, Date start, Date end, Integer topX) throws Exception;
	public Collection<ClaimStatisticDto> generateTopXClaimantReport (Integer policyId, Integer caseCategoryId, Date start, Date end, Integer topX) throws Exception;
	public Collection<ClaimStatisticDto> generateTopXFrequencyReport (Integer policyId, Integer caseCategoryId, Date start, Date end, Integer topX) throws Exception;
	
	public Collection<ClaimStatisticDto> generateTopXDiagnosisReport (Integer clientId,Integer groupId, Integer caseCategoryId, 
			Date start, Date end, Integer topX, String sortby) throws Exception;
	public Collection<ClaimStatisticDto> generateTopXProviderReport (Integer clientId,Integer groupId, Integer caseCategoryId, 
			Date start, Date end, Integer topX, String sortby) throws Exception;
	public Collection<ClaimStatisticDto> generateTopXClaimantReport (Integer clientId,Integer groupId, Integer caseCategoryId, 
			Date start, Date end, Integer topX, String sortby) throws Exception;
	public Collection<ClaimStatisticDto> generateTopXFrequencyReport (Integer clientId,Integer groupId, Integer caseCategoryId, Date start, Date end, Integer topX) throws Exception;
	
	
	public Collection<ClaimStatisticDto> generateTop10ClaimantReport(Integer clientId,Integer groupId) throws Exception;
	public Collection<ClaimStatisticDto> generateTop10DiagnosisReport(Integer clientId,Integer groupId) throws Exception;
	public Collection<ClaimStatisticDto> generateTop10ProviderReport(Integer clientId,Integer groupId) throws Exception;
	public Collection<ClaimStatisticDto> generateTop10ClaimUpgrade(Integer clientId,Integer groupId) throws Exception;
	
	
	public Collection<Object> generateClaimReport(String keyword,
			String keyCategory, Date start, Date end, Integer status,
			Integer jenisLayanan, Integer jenisClaim) throws Exception ;
	
	public java.util.Collection<Object[]> generateClaimReport(Integer serviceType, Integer memberGroupId, 
			Date start, Date end, Integer totalIndex) throws Exception;
	
	public java.util.Collection<Object[]> generateClaimReportByClient(Integer serviceType, Integer clientId, 
			Date start, Date end, Integer totalIndex) throws Exception;
	
	public java.util.Collection<Object[]> generateClaimReportByClientAndGroup(Integer serviceType, Integer clientId, 
			Integer groupId,Date start, Date end, Integer totalIndex) throws Exception;
	
	 public String generateClientOutstandingClaim(Integer clientId) throws Exception;
	
	/*
	 * Output :
	 * 
	 * Provider Name
	 * Number of Claim
	 * Charge
	 * Benefit Paid
	 * % Total
	 * Average Claim Cost
	 * 
	 */
	public java.util.Collection<Object[]> generateProviderReport(Integer serviceType, Integer memberGroupId,
			Date start, Date end, Integer totalIndex) throws Exception;
	
	/*
	 * Output :
	 * 
	 * Diagnosis/Procedure
	 * Number Participant
	 * Number Claim
	 * Charge
	 * Benefit Paid
	 * % Total
	 * Average Charge
	 * 
	 */
	public java.util.Collection<Object[]> generateDiagnosisReport(Integer serviceType,Integer memberGroupId, 
                Date start, Date end,Integer totalIndex) throws Exception;
	
        
        public java.util.Collection<Object[]> generateClientClaimGroupComparison (Integer year, Integer clientId) throws Exception;
        
	public java.util.Collection<Object[]> generateClientClaimProviderComparison (Integer year, Integer clientId) throws Exception;
        
	public java.util.Collection<Object[]> generateClientClaimDiagnosisComparison (Integer year, Integer clientId) throws Exception;
        
        public double[] generateClientYearlyClaimGrowth (Integer year, Integer clientId)  throws Exception;
        
        public java.util.Collection<Object[]> generateGroupClaimProviderComparison (Integer year, Integer clientId) throws Exception;
        
	public java.util.Collection<Object[]> generateGroupClaimDiagnosisComparison (Integer year, Integer clientId) throws Exception;
        
        public double[] generateGroupYearlyClaimGrowth (Integer year, Integer clientId)  throws Exception;
        
        
	public java.util.Collection<Object[]> generateClaimGroupComparison (Integer year) throws Exception;
        
	public java.util.Collection<Object[]> generateClaimProviderComparison (Integer year) throws Exception;
        
	public java.util.Collection<Object[]> generateClaimDiagnosisComparison (Integer year) throws Exception;
        
	public double[] generateProviderClaimGrowth (Integer year,Integer providerId)  throws Exception;
    public double[] generateYearlyClaimGrowth (Integer year)  throws Exception;
        
	public java.util.Collection<Object[]> generateClientPremiumComparison (Integer year) throws Exception;
	
	public java.util.Collection<Object[]> generateClaimStatisticReport(String keyword,String keyCategory, Date start, Date end,
			Integer status, Integer jenisLayanan, Integer jenisClaim) throws Exception; 
	
	public Integer getTotalClaimStatisticReport(String keyword,String keyCategory, Date start, Date end,
			Integer status, Integer jenisLayanan, Integer jenisClaim) throws Exception; 
	
	public HSSFWorkbook generateStatistic (Integer memberGroupId, Date start, Date end, Integer jenisLayanan, Integer jenisClaim) throws Exception;
	
	//Add 20151123 by FVO for Client Dashboard
	public Collection<Object[]> generateClaimClientComparison(Integer year, Integer clientId)
            throws Exception;
	
}
