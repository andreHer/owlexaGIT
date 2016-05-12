package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.TreatmentLocation;
import com.ametis.cms.util.StringUtil;

// imports+ 

// imports- 

/**
 * Claim is a mapping of claim Table.
 */
public class ClaimForm implements java.io.Serializable
// extends+

// extends-
{

	private boolean isNewClaim = false;

	private Claim claimBean;

	private String name;
	private String patientName;
	private String serviceProvider;
	
	private String policyNumber;
	private String diagnosis2Code;
	private String diagnosis2Text;
	private String diagnosis1Code;
	private String diagnosis1Text;
	private String diagnosis3Code;
	private String diagnosis3Text;
	
	private String diagnosis2;
	private String diagnosis3;

	private String caseNumber;
	private String batchClaimNumber;
	private String isSurgery;
	private String surgeryLevel;
	private String hasComplication;
	private String roomIsFull;
	private String roomNotAvailable;
	private String isAccident;
	private PaymentRecipient paymentRecipient;
	private Client clientId;
	private MemberGroup memberGroupId;
	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public ClaimForm() {
		this.claimBean = new Claim();
		this.isNewClaim = true;
	}

	
	public String getBatchClaimNumber() {
		return batchClaimNumber;
	}


	public void setBatchClaimNumber(String batchClaimNumber) {
		this.batchClaimNumber = batchClaimNumber;
	}


	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClaimForm(Claim object) {
		this.claimBean = object;
	}

	public boolean isNewClaim() {

		return this.isNewClaim;
	}

	public Claim getClaim() {
		return this.claimBean;
	}

	public void setClaim(Claim object) {
		this.claimBean = object;
	}

	public void setClaimId(String obj) {

		claimBean.setClaimId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getClaimId() {
		return StringUtil.getStringValue(claimBean.getClaimId());

	}

	public void setDescription(String obj) {

		claimBean.setDescription(new String(obj));

	}

	public String getDescription() {
		return StringUtil.getStringValue(claimBean.getDescription());

	}

	public void setLocationId(String obj) {

		if (obj != null && !obj.equals("") ){
			TreatmentLocation location = new TreatmentLocation();
			location.setLocationId(Integer.valueOf(obj));
			
			claimBean.setLocationId(location);
		}

	}

	public String getLocationId() {

		String result = "";
		
		TreatmentLocation location = claimBean.getLocationId();
		if (location != null){
			result = claimBean.getLocationId().getLocationId().toString();
		}
		return result;
		// return StringUtil.getStringValue(
		// claimBean.getLocationId().getLocationId());

	}

	public void setClaimNumber(String obj) {

		claimBean.setClaimNumber(new String(obj));

	}

	public String getClaimNumber() {
		return StringUtil.getStringValue(claimBean.getClaimNumber());

	}

	public void setClaimChargeValue(String obj) {

		claimBean.setClaimChargeValue(StringUtil.getDoubleValue(obj, 0));

	}

	public String getClaimChargeValue() {
		return StringUtil.getStringValue(claimBean.getClaimChargeValue());

	}

	public void setClaimDate(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			claimBean.setClaimDate(java.sql.Date.valueOf(obj));
		}

	}

	public String getClaimDate() {
		return StringUtil.getStringValue(claimBean.getClaimDate());

	}

	public void setClaimClosedDate(String obj) {

		claimBean.setClaimClosedDate(java.sql.Date.valueOf(obj));

	}

	public String getClaimClosedDate() {
		return StringUtil.getStringValue(claimBean.getClaimClosedDate());

	}

	public void setClaimRemarks(String obj) {

		claimBean.setClaimRemarks(new String(obj));

	}

	public String getClaimRemarks() {
		return StringUtil.getStringValue(claimBean.getClaimRemarks());

	}

	public void setApprovedBy(String obj) {

		claimBean.setApprovedBy(obj);

	}

	public String getApprovedBy() {
		return claimBean.getApprovedBy();

	}
	public void setVerifiedBy (String obj){
		claimBean.setVerifiedBy(obj);
		
	}
	public String getVerifiedBy (){
		return claimBean.getVerifiedBy();
	}
	
	public void setApprovedTime(String obj) {

		claimBean.setApprovedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getApprovedTime() {
		return StringUtil.getStringValue(claimBean.getApprovedTime());

	}

	public void setClosedBy(String obj) {

		claimBean.setClosedBy(obj);

	}

	public String getClosedBy() {
		return StringUtil.getStringValue(claimBean.getClosedBy());

	}

	public void setClosedTime(String obj) {

		claimBean.setClosedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getClosedTime() {
		return StringUtil.getStringValue(claimBean.getClosedTime());

	}

	public void setClaimPaidValue(String obj) {

		claimBean.setClaimPaidValue(StringUtil.getDoubleValue(obj, 0));

	}

	public String getClaimPaidValue() {
		return StringUtil.getStringValue(claimBean.getClaimPaidValue());

	}

	public void setClaimApprovedValue(String obj) {

		claimBean.setClaimApprovedValue(StringUtil.getDoubleValue(obj, 0));

	}

	public String getClaimApprovedValue() {
		return StringUtil.getStringValue(claimBean.getClaimApprovedValue());

	}

	public void setClaimProviderStatus(String obj) {

		claimBean.setClaimProviderStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getClaimProviderStatus() {
		return StringUtil.getStringValue(claimBean.getClaimProviderStatus());

	}

	public void setClaimPaymentStatus(String obj) {

		claimBean.setClaimPaymentStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getClaimPaymentStatus() {
		return StringUtil.getStringValue(claimBean.getClaimPaymentStatus());

	}

	public void setProviderName(String obj) {

		claimBean.setProviderName(new String(obj));

	}

	public String getProviderName() {
		return StringUtil.getStringValue(claimBean.getProviderName());

	}

	public void setTotalItem(String obj) {

		claimBean.setTotalItem(StringUtil.getIntegerValue(obj, 0));

	}

	public String getTotalItem() {
		return StringUtil.getStringValue(claimBean.getTotalItem());

	}

	public void setCreatedTime(String obj) {

		claimBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime() {
		return StringUtil.getStringValue(claimBean.getCreatedTime());

	}

	public void setCreatedBy(String obj) {

		claimBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy() {
		return StringUtil.getStringValue(claimBean.getCreatedBy());

	}

	public void setDeletedTime(String obj) {

		claimBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime() {
		return StringUtil.getStringValue(claimBean.getDeletedTime());

	}

	public void setDeletedBy(String obj) {

		claimBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy() {
		return StringUtil.getStringValue(claimBean.getDeletedBy());

	}

	public void setModifiedTime(String obj) {

		claimBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime() {
		return StringUtil.getStringValue(claimBean.getModifiedTime());

	}

	public void setModifiedBy(String obj) {

		claimBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy() {
		return StringUtil.getStringValue(claimBean.getModifiedBy());

	}

	public void setDeletedStatus(String obj) {

		claimBean.setDeletedStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getDeletedStatus() {
		return StringUtil.getStringValue(claimBean.getDeletedStatus());

	}

//	public void setAdmissionDate(String obj) {
//
//		claimBean.setAdmissionDate(java.sql.Date.valueOf(obj));
//
//	}
//
//	public String getAdmissionDate() {
//		return StringUtil.getStringValue(claimBean.getAdmissionDate());
//
//	}
//
//	public void setDischargeDate(String obj) {
//
//		claimBean.setDischargeDate(java.sql.Date.valueOf(obj));
//
//	}
//
//	public String getDischargeDate() {
//		return StringUtil.getStringValue(claimBean.getDischargeDate());
//
//	}
	
	public void setAdmissionDate(String obj) {
		if(obj !=null && !obj.equals("")){
			claimBean.setAdmissionDate(java.sql.Date.valueOf(obj));
		}else{
			claimBean.setAdmissionDate(null);
		}

	}

	public String getAdmissionDate() {
		String admissionDate = "";
		if(claimBean.getAdmissionDate() != null){
			admissionDate = StringUtil.getStringValue(claimBean.getAdmissionDate());
		}
		return admissionDate;

	}

	public void setDischargeDate(String obj) {
		if (obj != null && !obj.equals("")) {
			claimBean.setDischargeDate(java.sql.Date.valueOf(obj));
		} else {
			claimBean.setDischargeDate(null);
		}

	}

	public String getDischargeDate() {
		String dischargeDate = "";
		
		if (claimBean.getDischargeDate() != null) {
			dischargeDate = StringUtil.getStringValue(claimBean.getDischargeDate());
		}

		return dischargeDate;

	}

	public void setDiagnosis3Id(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			claimBean.setDiagnosis3Id(diagnosis);
		}


	}

	public String getDiagnosis3Id() {
		String result = "";
		
		if (claimBean.getDiagnosis3Id() != null){
			result = claimBean.getDiagnosis3Id().getDiagnosisId().toString();
		}
		
		return result;

	}

	public void setIterative(String obj) {

		claimBean.setIterative(new String(obj));

	}

	public String getIterative() {
		return StringUtil.getStringValue(claimBean.getIterative());

	}

	// foreign affairs

	public void setClaimTypeId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			ClaimType ctype  = new ClaimType();
			ctype.setClaimTypeId(Integer.valueOf(obj));
			claimBean.setClaimTypeId(ctype);
		}

	}

	public String getClaimTypeId() {
		String result = "";
		
		
		if ( claimBean.getClaimTypeId() != null){
			result = claimBean.getClaimTypeId().getClaimTypeId().toString();
		}
		return result;

	}

	// ---

	public void setClaimStatus(String obj) {
		CaseStatus fk = new CaseStatus();
		fk.setCaseStatusId(StringUtil.getIntegerValue(obj, 0));
		claimBean.setClaimStatus(fk);

	}

	public String getClaimStatus() {
		return StringUtil.getStringValue(claimBean.getClaimStatus()
				.getCaseStatusId());

	}

	// ---

	public void setMemberId(String obj) {

		if (obj != null && !obj.equalsIgnoreCase("")){
			Member member = new Member();
			member.setMemberId(Integer.valueOf(obj));
			claimBean.setMemberId(member);
		}

	}

	public String getMemberId() {
		String result = "";
		
		if (claimBean.getMemberId() != null){
			result = claimBean.getMemberId().getMemberId().toString();
		}
		return result;

	}

	// ---

	public void setDiagnosisId(String obj) {
		
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			claimBean.setDiagnosisId(diagnosis);
		}


	}

	public String getDiagnosisId() {
		String result = "";
		
		if (claimBean.getDiagnosisId() != null){
			result = claimBean.getDiagnosisId().getDiagnosisId().toString();
		}
		
		return result;

	}

	public void setDiagnosis1(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			claimBean.setDiagnosisId(diagnosis);
		}
	}

	public String getDiagnosis1() {
		String result = "";
		
		if (claimBean.getDiagnosisId() != null){
			result = claimBean.getDiagnosisId().getDiagnosisId().toString();
		}
		
		return result;
		

	}
	// ---

	public void setDiagnosis2Id(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			claimBean.setDiagnosis2Id(diagnosis);
		}


	}

	public String getDiagnosis2Id() {
		String result = "";
		
		if (claimBean.getDiagnosis2Id() != null){
			result = claimBean.getDiagnosis2Id().getDiagnosisId().toString();
		}
		
		return result;

	}

	// ---

	public void setBatchClaimId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			BatchClaim bc = new BatchClaim();
			bc.setBatchClaimId(Integer.valueOf(obj));
			claimBean.setBatchClaimId(bc);
		}

	}

	public String getBatchClaimId() {
		String result = "";
		
		if (claimBean.getBatchClaimId() != null){
			result = claimBean.getBatchClaimId().getBatchClaimId().toString();
		}
		return result;

	}

	// ---

	public void setCaseId(String obj) {
		
		if (obj != null && !obj.equals("")){
			Case caseRef = new Case();
			caseRef.setCaseId(Integer.valueOf(obj));
			claimBean.setCaseId(caseRef);
		}

	}

	public String getCaseId() {
		String result = "";
		if (claimBean.getCaseId() != null){
			result = claimBean.getCaseId().getCaseId().toString();
		}
		return result;

	}

	// ---

	public void setCaseCategoryId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			
			CaseCategory cc = new CaseCategory();
			cc.setCaseCategoryId(Integer.valueOf(obj));
			claimBean.setCaseCategoryId(cc);
		}

	}

	public String  getCaseCategoryId() {
		String result = "";
		
		if (claimBean.getCaseCategoryId() != null){
			result = claimBean.getCaseCategoryId().getCaseCategoryId().toString();
		}
		return result;

	}

	// ---

	public void setProviderId(String obj) {
		//if (obj != null){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			claimBean.setProviderId(provider);
		}
	//	}
	}

	public String getProviderId() {
		String result = "";
		
		if (claimBean.getProviderId() != null){
			result = claimBean.getProviderId().getProviderId().toString();
		}
		return result;
		

	}

	// ---

	public void setPaymentId(Payment obj) {
		
		claimBean.setPaymentId(obj);

	}

	public Payment getPaymentId() {
		return claimBean.getPaymentId();

	}

	public String getDiagnosis1Code() {
		return diagnosis1Code;
	}

	public void setDiagnosis1Code(String diagnosis1Code) {
		this.diagnosis1Code = diagnosis1Code;
	}

	public String getDiagnosis1Text() {
		return diagnosis1Text;
	}

	public void setDiagnosis1Text(String diagnosis1Text) {
		this.diagnosis1Text = diagnosis1Text;
	}

	public String getDiagnosis2Code() {
		return diagnosis2Code;
	}

	public void setDiagnosis2Code(String diagnosis2Code) {
		this.diagnosis2Code = diagnosis2Code;
	}

	public String getDiagnosis2Text() {
		return diagnosis2Text;
	}

	public void setDiagnosis2Text(String diagnosis2Text) {
		this.diagnosis2Text = diagnosis2Text;
	}

	public String getDiagnosis3Code() {
		return diagnosis3Code;
	}

	public void setDiagnosis3Code(String diagnosis3Code) {
		this.diagnosis3Code = diagnosis3Code;
	}

	public String getDiagnosis3Text() {
		return diagnosis3Text;
	}

	public void setDiagnosis3Text(String diagnosis3Text) {
		this.diagnosis3Text = diagnosis3Text;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getDiagnosis2() {
		return diagnosis2;
	}

	public void setDiagnosis2(String diagnosis2) {
		this.diagnosis2 = diagnosis2;
	}

	public String getDiagnosis3() {
		return diagnosis3;
	}

	public void setDiagnosis3(String diagnosis3) {
		this.diagnosis3 = diagnosis3;
	}
	public void setPembulatan (String pembulatan){
		if (pembulatan != null && !pembulatan.equalsIgnoreCase("")){
			claimBean.setPembulatan(Double.valueOf(pembulatan));
		}
	}
	public String getPembulatan(){
		String result = "";
		
		if (claimBean != null && claimBean.getPembulatan() != null){
			result = StringUtil.getStringValue(claimBean.getPembulatan(), "0");
		}
		return result;
	}
	public void setPembayaranDimuka(String pembayaranDimuka){
		if (pembayaranDimuka != null && !pembayaranDimuka.equalsIgnoreCase("")){
			claimBean.setPembayaranDimuka(Double.valueOf(pembayaranDimuka));
		}
	}
	public String getPembayaranDimuka (){
		String result = "";
		
		if (claimBean != null && claimBean.getPembayaranDimuka() != null ){
			result = StringUtil.getStringValue(claimBean.getPembayaranDimuka(),"0");
		}
		
		return result;
	}

	public String getIsSurgery() {
		return isSurgery;
	}

	public void setIsSurgery(String isSurgery) {
		this.isSurgery = isSurgery;
		if (isSurgery != null){
			if (isSurgery.equalsIgnoreCase("1")){
				claimBean.setIsSurgery(Integer.valueOf(1));
			}
			else {
				claimBean.setIsSurgery(Integer.valueOf(0));
			}
		}
		else {
			claimBean.setIsSurgery(Integer.valueOf(0));
		}
	}

	public String getSurgeryLevel() {
		return surgeryLevel;
	}

	public void setSurgeryLevel(String surgeryLevel) {
		this.surgeryLevel = surgeryLevel;
		
		if (surgeryLevel != null){
			claimBean.setSurgeryLevel(surgeryLevel);
		}
		
	}

	public String getHasComplication() {
		return hasComplication;
	}

	public void setHasComplication(String hasComplication) {
		this.hasComplication = hasComplication;
		
		if (hasComplication != null){
			claimBean.setHasComplication(Integer.valueOf(hasComplication));
		}
	}

	//Comment 20150811 by FVO, changing has complication from checkbox tu combobox
	/*
	public void setHasComplication(String hasComplication) {
		this.hasComplication = hasComplication;
		
		if (hasComplication != null){
			if (hasComplication.equalsIgnoreCase("1")){
				claimBean.setHasComplication(Integer.valueOf(1));
			}
			else {
				claimBean.setHasComplication(Integer.valueOf(0));
			}
		}
		else {
			claimBean.setHasComplication(Integer.valueOf(0));
		}
	}
	*/

	public String getRoomIsFull() {
		return roomIsFull;
	}

	public void setRoomIsFull(String roomIsFull) {
		this.roomIsFull = roomIsFull;
		
		if (roomIsFull != null){
			if (roomIsFull.equalsIgnoreCase("1")){
				claimBean.setRoomIsFull(Integer.valueOf(1));
			}
			else {
				claimBean.setRoomIsFull(Integer.valueOf(0));
			}
		}
		else {
			claimBean.setRoomIsFull(Integer.valueOf(0));
		}
	}

	public String getRoomNotAvailable() {
		return roomNotAvailable;
	}

	public void setRoomNotAvailable(String roomNotAvailable) {
		this.roomNotAvailable = roomNotAvailable;
		
		if (roomNotAvailable != null){
			if (roomNotAvailable.equalsIgnoreCase("1")){
				claimBean.setRoomNotAvailable(Integer.valueOf(1));
			}
			else {
				claimBean.setRoomNotAvailable(Integer.valueOf(0));
			}
		}
		else {
			claimBean.setRoomNotAvailable(Integer.valueOf(0));
		}
	}

	public String getIsAccident() {
		return isAccident;
	}

	public void setIsAccident(String isAccident) {
		this.isAccident = isAccident;
		if (isAccident != null){
			if (isAccident.equalsIgnoreCase("1")){
				claimBean.setIsAccident(Integer.valueOf(1));
			}
			else {
				claimBean.setIsAccident(Integer.valueOf(0));
			}
		}
		else {
			claimBean.setIsAccident(Integer.valueOf(0));
		}
		
	}

	public String getPaymentRecipient() {
		String result = "";
		
		if (this.paymentRecipient != null){
			result = paymentRecipient.getPaymentRecipientId().toString();
		}
		
		return result;
	}

	public void setPaymentRecipient(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.paymentRecipient = new PaymentRecipient();
			this.paymentRecipient.setPaymentRecipientId(Integer.valueOf(obj));
		}
	}

	public String getClientId() {
		String result = "";
		
		if (clientId != null){
			result = clientId.getClientId().toString();
		}
		return result;
	}

	public void setClientId(String obj) {
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.clientId = new Client();
			clientId.setClientId(Integer.valueOf(obj));
			this.claimBean.setClientId(Integer.valueOf(obj));
		}
		
	}

	public String getRoomUpgradeType() {
		String result = "";
		
		if (claimBean.getRoomUpgradeType() != null){
			result = claimBean.getRoomUpgradeType().toString();
		}
		return result;
	}

	public void setRoomUpgradeType(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.claimBean.setRoomUpgradeType(Integer.valueOf(obj));
		}
	}
	public String getClaimRoomRate() {
		String result = "";
		
		if (claimBean.getCurrentRoomRate() != null){
			result = claimBean.getCurrentRoomRate().toString();
		}
		return result;
	}

	public void setClaimRoomRate(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.claimBean.setCurrentRoomRate(Double.valueOf(obj));
		}
	}
	public String getBenefitRoomRate() {
		String result = "";
		
		if (claimBean.getProductRoomRate() != null){
			result = claimBean.getProductRoomRate().toString();
		}
		return result;
	}

	public void setBenefitRoomRate(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.claimBean.setProductRoomRate(Double.valueOf(obj));
		}
	}
	public String getMemberGroupId() {
		String result = "";
		
		if (memberGroupId != null){
			result = memberGroupId.getMemberGroupId().toString();
		}
		return result;
	}

	public void setMemberGroupId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.memberGroupId = new MemberGroup();
			this.memberGroupId.setMemberGroupId(Integer.valueOf(obj));
			this.claimBean.setMemberGroupId(Integer.valueOf(obj));
		}
	}
	
	public String getDoctorName() {
		String result = "";
		
		if (claimBean.getDoctorName() != null){
			result = claimBean.getDoctorName();
		}
		return result;
	}

	public void setDoctorName(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			this.claimBean.setDoctorName(obj);
		}
	}

	public void setIsRujukan(String obj) {
		if (obj!= null && !obj.equalsIgnoreCase("")){
			claimBean.setIsRujukan(Integer.valueOf(obj));
		}
	}
	public String getIsRujukan() {
		return StringUtil.getStringValue(claimBean.getIsRujukan());

	}
	public void setNomorRujukan(String obj) {
		claimBean.setNomorSuratRujukan(obj);
	}
	public String getNomorRujukan() {
		return claimBean.getNomorSuratRujukan();

	}
	public void setManualRegistration(Integer obj) {
		claimBean.setManualRegistration(obj);
	}
	public Integer getManualRegistration() {
		return claimBean.getManualRegistration();

	}
}
