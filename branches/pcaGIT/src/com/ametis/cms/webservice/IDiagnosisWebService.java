package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ametis.cms.dto.DiagnosisDto;
@WebService (targetNamespace="http://ametis.co.id/services/DiagnosisWebService")
public interface IDiagnosisWebService {

	@WebMethod
	public DiagnosisDto getDiagnosis (String icdCode);
	
	@WebMethod
	public boolean isDiagnosisExcluded (String icdCode, Integer memberId);
	@WebMethod
	public boolean isDiagnosisNotFound (String[] diagnosisEDCCode);
	@WebMethod
	public boolean isDiagnosisNotFoundSyresnet(String[] diagnosisEDCCode) ;
	@WebMethod
	public Collection<DiagnosisDto> searchDiagnosisByDiagnosisCode(String DiagnosisCode) ;
	@WebMethod
	public Collection<DiagnosisDto> searchDiagnosisByEDCCode(String EdcCode) ;
	@WebMethod
	public Collection<DiagnosisDto> searchDiagnosisByDiagnosisName(String DiagnosisName) ;
	@WebMethod
	public Collection<DiagnosisDto> searchDiagnosisByDescription(String DiagnosisDesc) ;
	@WebMethod
	public Collection<DiagnosisDto> getDiagnosisList();	
}
