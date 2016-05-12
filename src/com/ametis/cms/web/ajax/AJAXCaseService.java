package com.ametis.cms.web.ajax;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.dto.CaseDto;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProviderDoctorService;
import com.ametis.cms.service.ProviderItemService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.ProviderService;

public class AJAXCaseService {

	private CaseService caseService;
	private ProviderItemService providerItemService;
	private ProviderDoctorService providerDoctorService;
	private ProviderService providerService;
	private DiagnosisService diagnosisService;
	private ProviderPoliklinikService providerPoliklinikService;
	private PoliklinikService poliklinikService;
	

	
	
	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(
			ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
	}

	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}

	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}

	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}

	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ProviderItemService getProviderItemService() {
		return providerItemService;
	}

	public void setProviderItemService(ProviderItemService providerItemService) {
		this.providerItemService = providerItemService;
	}

	public ProviderDoctorService getProviderDoctorService() {
		return providerDoctorService;
	}

	public void setProviderDoctorService(ProviderDoctorService providerDoctorService) {
		this.providerDoctorService = providerDoctorService;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}
	public String getPoliklinikByDiagnosis (String diagnosisId, String providerId){
		String result = "";
		
		try {

			Diagnosis diagnosis = null;
			
			if (diagnosisId != null && !diagnosisId.equalsIgnoreCase("")){
				diagnosis = diagnosisService.get(Integer.valueOf(diagnosisId));
			}
			if (providerId != null && !providerId.equalsIgnoreCase("")){
				String[] eqParam = {"deletedStatus","providerId.providerId"};
				Object[] eqValue = {0,Integer.valueOf(providerId)};
				int total = providerPoliklinikService.getTotal(null,null,eqParam,eqValue);
				Collection<ProviderPoliklinik> poliList = providerPoliklinikService.search(null,null,eqParam,eqValue,0,total);
				
				for (Iterator iterator = poliList.iterator(); iterator
						.hasNext();) {
					
					ProviderPoliklinik providerPoliklinik = (ProviderPoliklinik) iterator.next();
					
					if (providerPoliklinik != null){
						
 
						if (diagnosis != null && diagnosis.getPoliklinikId() != null){
							
							if (diagnosis.getPoliklinikId().getPoliklinikId().intValue() == providerPoliklinik.getPoliklinikId().getPoliklinikId().intValue()){
								result += "<option value='"+providerPoliklinik.getPoliklinikId().getPoliklinikId()+"' selected>"+providerPoliklinik.getPoliklinikId().getPoliklinikName()+"</option>";
							}
							else {
								result += "<option value='"+providerPoliklinik.getPoliklinikId().getPoliklinikId()+"'>"+providerPoliklinik.getPoliklinikId().getPoliklinikName()+"</option>";
							}
						}
						else {
							result += "<option value='"+providerPoliklinik.getPoliklinikId().getPoliklinikId()+"'>"+providerPoliklinik.getPoliklinikId().getPoliklinikName()+"</option>";	
						}
					}					
				}
			}
			else {

				Collection<Poliklinik> poliList = poliklinikService.getAll();
				
				for (Iterator iterator = poliList.iterator(); iterator
						.hasNext();) {
					
					Poliklinik providerPoliklinik = (Poliklinik) iterator.next();
					
					if (providerPoliklinik != null){
						
 
		 				if (diagnosis != null && diagnosis.getPoliklinikId() != null){
							
							if (diagnosis.getPoliklinikId().getPoliklinikId().intValue() == providerPoliklinik.getPoliklinikId().intValue()){
								result += "<option value='"+providerPoliklinik.getPoliklinikId()+"' selected>"+providerPoliklinik.getPoliklinikName()+"</option>";
							}
							else {
								result += "<option value='"+providerPoliklinik.getPoliklinikId()+"'>"+providerPoliklinik.getPoliklinikName()+"</option>";
							}
						}
						else {
							result += "<option value='"+providerPoliklinik.getPoliklinikId()+"'>"+providerPoliklinik.getPoliklinikName()+"</option>";	
						}
					}					
				}

			}
			
			
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String getDoctorListByProvider(String poliId,String providerId){
		String result = "";
		
		try {
			String[] eqParam = {"deletedStatus","providerId.providerId","providerPoliklinikId.poliklinikId"};
			Object[] eqValue = {0,Integer.valueOf(providerId),Integer.valueOf(poliId)};
			
			int total = providerDoctorService.getTotal(null,null,eqParam,eqValue);
			Collection<ProviderDoctor> tmp = providerDoctorService.search(null,null,eqParam,eqValue,0,total);			
			
			if (tmp != null ){
				Iterator<ProviderDoctor> iterator = tmp.iterator();
				result = "";
				
				ProviderDoctor doctor = null;
				result += "<option value=''>-- PILIH SALAH SATU --</option>";
				while (iterator.hasNext()){
					doctor = iterator.next();
					
					result += "<option value='"+doctor.getProviderDoctorId()+"'>"+doctor.getDoctorName()+"</option>";
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return result;
	}

	public String getDoctorList(int poliId){
		String result = "";
		
		try {
			String[] eqParam = {"providerItemId.providerItemId"};
			Object[] eqValue = {poliId};
			
			int total = providerDoctorService.getTotal(null,null,eqParam,eqValue);
			Collection<ProviderDoctor> tmp = providerDoctorService.search(null,null,eqParam,eqValue,0,total);			
			
			if (tmp != null ){
				Iterator<ProviderDoctor> iterator = tmp.iterator();
				result = "";
				
				ProviderDoctor doctor = null;
				
				while (iterator.hasNext()){
					doctor = iterator.next();
					
					result += "<option value='"+doctor.getProviderDoctorId()+"'>"+doctor.getDoctorName()+"</option>";
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return result;
	}
	public CaseDto getCase (int caseId){
		CaseDto result = null;
		
		try {
			
			Case referenceCase = caseService.get(caseId);
			
			if (referenceCase != null){
				result = new CaseDto();
			}
		}
		catch (Exception e){
			
		}
		
		return result;
	}
}
