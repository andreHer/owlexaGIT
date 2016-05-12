package com.ametis.cms.dto;

import java.io.Serializable;

public class DiagnosisDto implements Serializable{

	private String diagnosisCode;
	private String diagnosisEDCName;
	private String description;
	private String alternateCode;
	private String initialSymptom;
	private String vitalSign;

	public DiagnosisDto(){}
	
	public String getVitalSign() {
		return vitalSign;
	}

	public void setVitalSign(String vitalSign) {
		this.vitalSign = vitalSign;
	}
	
	public String getInitialSymptom() {
		return initialSymptom;
	}

	public void setInitialSymptom(String initialSymptom) {
		this.initialSymptom = initialSymptom;
	}
	
	public String getAlternateCode() {
		return alternateCode;
	}

	public void setAlternateCode(String alternateCode) {
		this.alternateCode = alternateCode;
	}

	public String getDiagnosisCode() {
		return diagnosisCode;
	}

	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}

	public String getDiagnosisName() {
		return diagnosisEDCName;
	}

	public void setDiagnosisName(String diagnosisEDCName) {
		this.diagnosisEDCName = diagnosisEDCName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
