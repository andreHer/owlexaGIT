package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.jws.WebService;

import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.dto.DiagnosisDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.service.DiagnosisService;
import com.ametis.cms.service.MemberDiagnosisService;


@WebService(name = "DiagnosisWebService", 
		endpointInterface = "com.ametis.cms.webservice.IDiagnosisWebService"
			,serviceName="DiagnosisWebService")
public class DiagnosisWebServiceImpl implements IDiagnosisWebService{

	private DiagnosisService diagnosisService;
	private MemberDiagnosisService memberDiagnosisService;
	
	
	
	public MemberDiagnosisService getMemberDiagnosisService() {
		return memberDiagnosisService;
	}
	public void setMemberDiagnosisService(
			MemberDiagnosisService memberDiagnosisService) {
		this.memberDiagnosisService = memberDiagnosisService;
	}
	public DiagnosisService getDiagnosisService() {
		return diagnosisService;
	}
	public void setDiagnosisService(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}

	public DiagnosisDto getDiagnosis(String icdCode) {
		// TODO Auto-generated method stub
		DiagnosisDto result = null;
		
		try {
			Diagnosis diagnosis = diagnosisService.getDiagnosisByCode(icdCode);
			
			if (diagnosis != null){
				result = new DiagnosisDto();
				result.setDescription(diagnosis.getDescription());
				result.setDiagnosisCode(diagnosis.getDiagnosisCode());
				result.setDiagnosisName(diagnosis.getDiagnosisName());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isDiagnosisExcluded(String icdCode, Integer memberId) {
		// TODO Auto-generated method stub
		
		boolean result = false;
		
		try {
			
			Diagnosis diagnosis = diagnosisService.getDiagnosisByCode(icdCode);
			if (diagnosis != null){
			
				System.out.println("----- checking diagnosis code : " + icdCode + " diagnosis Id : " + diagnosis.getDiagnosisId() + "  for member : " + memberId +"  IS EXCLUDED ?!");
				String[] eqParam = {"memberId.memberId","diagnosisId.diagnosisId","diagnosisStatus"};
				Object[] eqValue = {memberId,diagnosis.getDiagnosisId(),Integer.valueOf(0)};
				
				int total = memberDiagnosisService.getTotal(null, null, eqParam, eqValue);
				if (total > 0){
					System.out.println("DIAGNOSIS : " + icdCode + " FOR MEMBER : " + memberId + " IS EXCLUDED!");
					result = true;
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean isDiagnosisNotFound(String[] diagnosisEDCCode) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			if (diagnosisEDCCode != null){
				for (int i = 0; i < diagnosisEDCCode.length; i++) {
					System.out.println("DIAGNOSA YANG DI CHECK [i = "+ i + "] = |"+diagnosisEDCCode[i]+"|" + " TRIMMED = |"+diagnosisEDCCode[i].trim()+"|");
					
					String icdCode = diagnosisEDCCode[i].trim();
					
					if (!icdCode.equalsIgnoreCase("") && i < 3){
						String[] eqParam = {"deletedStatus","alternateCode"};
						Object[] eqValue = {Integer.valueOf(0),icdCode};
						int totalCode = diagnosisService.getTotal(null,null,eqParam,eqValue);
						
						if (totalCode == 0){
							result = true;
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
	public boolean isDiagnosisNotFoundSyresnet(String[] diagnosisEDCCode) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			if (diagnosisEDCCode != null){
				for (int i = 0; i < diagnosisEDCCode.length; i++) {
					System.out.println("DIAGNOSA YANG DI CHECK [i = "+ i + "] = |"+diagnosisEDCCode[i]+"|" + " TRIMMED = |"+diagnosisEDCCode[i].trim()+"|");
					
					String icdCode = diagnosisEDCCode[i].trim();
					
					if (!icdCode.equalsIgnoreCase("") && i < 3){
						String[] eqParam = {"deletedStatus","alternateCode"};
						Object[] eqValue = {Integer.valueOf(0),icdCode};
						int totalCode = diagnosisService.getTotal(null,null,eqParam,eqValue);
						
						if (totalCode == 0){
							result = true;
						}
					}
				}
				if (result){
				
					result = false;
					
					for (int i = 0; i < diagnosisEDCCode.length; i++) {
						System.out.println("DIAGNOSA YANG DI CHECK [i = "+ i + "] = |"+diagnosisEDCCode[i]+"|" + " TRIMMED = |"+diagnosisEDCCode[i].trim()+"|");
						
						String icdCode = diagnosisEDCCode[i].trim();
						
						if (!icdCode.equalsIgnoreCase("") && i < 3){
							String[] eqParam = {"deletedStatus","icd10AlternateCode"};
							Object[] eqValue = {Integer.valueOf(0),icdCode};
							int totalCode = diagnosisService.getTotal(null,null,eqParam,eqValue);
							
							if (totalCode == 0){
								result = true;
							}
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
	
	public Collection<DiagnosisDto> getDiagnosisList(){//buatan luthfi untuk mencari data diagnosis berdasarkan diagnosis code
		Collection<DiagnosisDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		Vector vDiagnosisCode = new Vector();
		Vector voDiagnosisCode = new Vector();
		
		try {
		
			vEqP.add("deletedStatus");
			vEqQ.add(Integer.valueOf(0));
			
			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			String sDiagnosis[] = new String[vDiagnosisCode.size()];
			vDiagnosisCode.toArray(sDiagnosis);//done
			Object oDiagnosis[] = new Object[vDiagnosisCode.size()];
			voDiagnosisCode.toArray(oDiagnosis);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			Collection<Diagnosis> diagnosisList = diagnosisService.getDiagnosisList(sEqP, sEqQ);
	
				if (diagnosisList != null ) {
					resultList = new Vector<DiagnosisDto>();
					Iterator<Diagnosis> iterator = diagnosisList.iterator();
		
					while (iterator.hasNext()) {
						DiagnosisDto result = new DiagnosisDto();
						Diagnosis diagnosis = iterator.next();

						result.setDiagnosisCode(diagnosis.getDiagnosisCode() == null ? "" : diagnosis.getDiagnosisCode());
						result.setDiagnosisName(diagnosis.getDiagnosisName() == null ? "" : diagnosis.getDiagnosisName());
						result.setDescription(diagnosis.getDescription() == null ? "" : diagnosis.getDescription());
						result.setAlternateCode(diagnosis.getAlternateCode() == null ? "" : diagnosis.getAlternateCode());
						result.setInitialSymptom(diagnosis.getInitialSymptom() == null ? "" : diagnosis.getInitialSymptom());
						result.setVitalSign(diagnosis.getVitalSign() == null ? "" : diagnosis.getVitalSign());
						
						resultList.add(result);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return resultList;
		
	}
	
	public Collection<DiagnosisDto> searchDiagnosisByDiagnosisCode(String DiagnosisCode){//buatan luthfi untuk mencari data diagnosis berdasarkan diagnosis code
		Collection<DiagnosisDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		Vector vDiagnosisCode = new Vector();
		Vector voDiagnosisCode = new Vector();
		
		try {
		
			vEqP.add("deletedStatus");
			vDiagnosisCode.add("diagnosisCode");
			vEqQ.add(Integer.valueOf(0));
			voDiagnosisCode.add(DiagnosisCode);
			
			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			String sDiagnosis[] = new String[vDiagnosisCode.size()];
			vDiagnosisCode.toArray(sDiagnosis);//done
			Object oDiagnosis[] = new Object[vDiagnosisCode.size()];
			voDiagnosisCode.toArray(oDiagnosis);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			Collection<Diagnosis> diagnosisList = diagnosisService.searchDiagnosisByDiagnosisCode(sEqP,sDiagnosis, oDiagnosis, sEqQ);
	
				if (diagnosisList != null ) {
					resultList = new Vector<DiagnosisDto>();
					Iterator<Diagnosis> iterator = diagnosisList.iterator();
		
					while (iterator.hasNext()) {
						DiagnosisDto result = new DiagnosisDto();
						Diagnosis diagnosis = iterator.next();

						result.setDiagnosisCode(diagnosis.getDiagnosisCode() == null ? "" : diagnosis.getDiagnosisCode());
						result.setDiagnosisName(diagnosis.getDiagnosisName() == null ? "" : diagnosis.getDiagnosisName());
						result.setDescription(diagnosis.getDescription() == null ? "" : diagnosis.getDescription());
						result.setAlternateCode(diagnosis.getAlternateCode() == null ? "" : diagnosis.getAlternateCode());
						result.setInitialSymptom(diagnosis.getInitialSymptom() == null ? "" : diagnosis.getInitialSymptom());
						result.setVitalSign(diagnosis.getVitalSign() == null ? "" : diagnosis.getVitalSign());
						
						resultList.add(result);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return resultList;
		
	}
	
	public Collection<DiagnosisDto> searchDiagnosisByEDCCode(String EdcCode){//buatan luthfi untuk mencari data diagnosis berdasarkan edc code
		Collection<DiagnosisDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		Vector vAlternateCode = new Vector();
		Vector voAlternateCode = new Vector();
		
		try {
		
			vEqP.add("deletedStatus");
			vAlternateCode.add("alternateCode");
			vEqQ.add(Integer.valueOf(0));
			voAlternateCode.add(EdcCode);
			
			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			String sAlternateCode[] = new String[vAlternateCode.size()];
			vAlternateCode.toArray(sAlternateCode);//done
			Object oAlternateCode[] = new Object[vAlternateCode.size()];
			voAlternateCode.toArray(oAlternateCode);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			Collection<Diagnosis> diagnosisList = diagnosisService.searchDiagnosisByDiagnosisCode(sEqP,sAlternateCode, oAlternateCode, sEqQ);
	
				if (diagnosisList != null ) {
					resultList = new Vector<DiagnosisDto>();
					Iterator<Diagnosis> iterator = diagnosisList.iterator();
		
					while (iterator.hasNext()) {
						DiagnosisDto result = new DiagnosisDto();
						Diagnosis diagnosis = iterator.next();

						result.setDiagnosisCode(diagnosis.getDiagnosisCode() == null ? "" : diagnosis.getDiagnosisCode());
						result.setDiagnosisName(diagnosis.getDiagnosisName() == null ? "" : diagnosis.getDiagnosisName());
						result.setDescription(diagnosis.getDescription() == null ? "" : diagnosis.getDescription());
						result.setAlternateCode(diagnosis.getAlternateCode() == null ? "" : diagnosis.getAlternateCode());
						result.setInitialSymptom(diagnosis.getInitialSymptom() == null ? "" : diagnosis.getInitialSymptom());
						result.setVitalSign(diagnosis.getVitalSign() == null ? "" : diagnosis.getVitalSign());
						
						resultList.add(result);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return resultList;
		
	}

	public Collection<DiagnosisDto> searchDiagnosisByDiagnosisName(String DiagnosisName){//buatan luthfi untuk mencari data diagnosis berdasarkan diagnosis name
		Collection<DiagnosisDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		Vector vDiagnosisName = new Vector();
		Vector voDiagnosisName = new Vector();
		
		try {
		
			vEqP.add("deletedStatus");
			vDiagnosisName.add("diagnosisName");
			vEqQ.add(Integer.valueOf(0));
			voDiagnosisName.add(DiagnosisName);
			
			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			String sDiagnosisName[] = new String[vDiagnosisName.size()];
			vDiagnosisName.toArray(sDiagnosisName);//done
			Object oDiagnosisName[] = new Object[vDiagnosisName.size()];
			voDiagnosisName.toArray(oDiagnosisName);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			Collection<Diagnosis> diagnosisList = diagnosisService.searchDiagnosisByDiagnosisCode(sEqP,sDiagnosisName, oDiagnosisName, sEqQ);
	
				if (diagnosisList != null ) {
					resultList = new Vector<DiagnosisDto>();
					Iterator<Diagnosis> iterator = diagnosisList.iterator();
		
					while (iterator.hasNext()) {
						DiagnosisDto result = new DiagnosisDto();
						Diagnosis diagnosis = iterator.next();

						result.setDiagnosisCode(diagnosis.getDiagnosisCode() == null ? "" : diagnosis.getDiagnosisCode());
						result.setDiagnosisName(diagnosis.getDiagnosisName() == null ? "" : diagnosis.getDiagnosisName());
						result.setDescription(diagnosis.getDescription() == null ? "" : diagnosis.getDescription());
						result.setAlternateCode(diagnosis.getAlternateCode() == null ? "" : diagnosis.getAlternateCode());
						result.setInitialSymptom(diagnosis.getInitialSymptom() == null ? "" : diagnosis.getInitialSymptom());
						result.setVitalSign(diagnosis.getVitalSign() == null ? "" : diagnosis.getVitalSign());
						
						resultList.add(result);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return resultList;
		
	}
	
	public Collection<DiagnosisDto> searchDiagnosisByDescription(String DiagnosisDesc){//by aju as arthur req 20150708
		Collection<DiagnosisDto> resultList = null;
		
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();
		Vector vDiagnosisDesc = new Vector();
		Vector voDiagnosisDesc = new Vector();
		
		try {
		
			vEqP.add("deletedStatus");
			vDiagnosisDesc.add("description");
			vEqQ.add(Integer.valueOf(0));
			voDiagnosisDesc.add(DiagnosisDesc);
			
			String sEqP[] = new String[vEqP.size()];
			vEqP.toArray(sEqP);
			
			String sDiagnosisDesc[] = new String[vDiagnosisDesc.size()];
			vDiagnosisDesc.toArray(sDiagnosisDesc);//done
			Object oDiagnosisDesc[] = new Object[vDiagnosisDesc.size()];
			voDiagnosisDesc.toArray(oDiagnosisDesc);
			
			Object sEqQ[] = new Object[vEqP.size()];
			vEqQ.toArray(sEqQ);
			
			Collection<Diagnosis> diagnosisList = diagnosisService.searchDiagnosisByDiagnosisCode(sEqP,sDiagnosisDesc, oDiagnosisDesc, sEqQ);
	
				if (diagnosisList != null ) {
					resultList = new Vector<DiagnosisDto>();
					Iterator<Diagnosis> iterator = diagnosisList.iterator();
		
					while (iterator.hasNext()) {
						DiagnosisDto result = new DiagnosisDto();
						Diagnosis diagnosis = iterator.next();

						result.setDiagnosisCode(diagnosis.getDiagnosisCode() == null ? "" : diagnosis.getDiagnosisCode());
						result.setDiagnosisName(diagnosis.getDiagnosisName() == null ? "" : diagnosis.getDiagnosisName());
						result.setDescription(diagnosis.getDescription() == null ? "" : diagnosis.getDescription());
						result.setAlternateCode(diagnosis.getAlternateCode() == null ? "" : diagnosis.getAlternateCode());
						result.setInitialSymptom(diagnosis.getInitialSymptom() == null ? "" : diagnosis.getInitialSymptom());
						result.setVitalSign(diagnosis.getVitalSign() == null ? "" : diagnosis.getVitalSign());
						
						resultList.add(result);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return resultList;
		
	}

}
