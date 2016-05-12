
package com.ametis.cms.web.form;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.util.StringUtil;

//imports- 

/**
 * Case is a mapping of case Table.
 */
public class CaseForm implements java.io.Serializable
//extends+ 

//extends- 
{
	
	
	private boolean isNewCase = false;
	private Case myCaseBean ;
	private String time;
	private Date date;
	private String hour;
	private String minute;
	private Date dateEnd;
	private String hourEnd;
	private String minuteEnd;
	
	private String roomAndBoard;
	
	private String diagnosis1Id;
	private String diagnosis2Id;
	private String diagnosis3Id;
	
	private String diagnosis1Code;
	private String diagnosis1Text;
	private String diagnosis2Code;
	private String diagnosis2Text;
	private String diagnosis3Code;
	private String diagnosis3Text;
	
	private String clientName;
	private String groupName;
	private String groupExpireDate;
	private String relationshipName;
	private String memberStatus;
	private String providerName;
	private String referedProviderName;
	private String isRefered;
	private MultipartFile multipartFile;
	
	private String hasComplication;
	/*
	 <form>Bean merupakan representasi dari "SINGLE" table dan
	 misalkan ada form2 yang merupakan referensi dari tabel lain
	 bikin aja field2 bean yang mengacu ke referensi itu
	 biar nanti automatic loading
	 
	 */
	
	public CaseForm()
	{
		this.myCaseBean = new Case();
		this.isNewCase = true;
	}
	public CaseForm (Case object){
		this.myCaseBean = object;
	}
	public boolean isNewCase (){
		
		return this.isNewCase;
	}
	public Case getCase (){
		return this.myCaseBean ;
	}
	public void setCase (Case object){
		this.myCaseBean = object;
	}
	
	
	public void setCaseId(String obj){
		
		myCaseBean.setCaseId(StringUtil.getIntegerValue(obj,0));
		
	}
	
	public String getCaseId(){
		return StringUtil.getStringValue(
				myCaseBean.getCaseId());
		
	}
	
	

	
	public String getIsRefered() {
		return isRefered;
	}
	public void setIsRefered(String isRefered) {
		this.isRefered = isRefered;
	}
	public void setCaseStartTime(String obj){
		if (obj != null || !obj.equals("")){
			System.out.println("INI OBJECT CASE START TIME : " + obj);
			myCaseBean.setCaseStartTime(java.sql.Date.valueOf(obj));	
		}
	}
	
	public String getCaseStartTime(){
		
		String result = "";
		
		if (myCaseBean != null){
			result = StringUtil.getStringValue( myCaseBean.getCaseStartTime());
		}
		return result;
		
	}
	
	
	
	public void setCaseEndTime(String obj){	
		
		if (obj != null || !obj.equals("")){
			System.out.println("INI OBJECT CASE END TIME : " + obj);
			myCaseBean.setCaseEndTime(java.sql.Date.valueOf(obj) );		
		}
	}
	
	public String getCaseEndTime(){
		String result = "";
		
		if (myCaseBean != null){
			result = StringUtil.getStringValue( myCaseBean.getCaseEndTime());
		}
		return result;
		
	}
	
	
	public void setDescription(String obj){
		
		myCaseBean.setDescription(new String(obj));
		
	}
	
	public String getDescription(){
		return StringUtil.getStringValue(
				myCaseBean.getDescription());
		
	}
	
	public void setInitialSymptom(String obj){
		
		myCaseBean.setInitialSymptom(new String(obj));
		
	}
	
	public String getInitialSymptom(){
		return StringUtil.getStringValue(
				myCaseBean.getInitialSymptom());
		
	}
	
	public void setInitialDiagnosis(String obj){
		myCaseBean.setInitialDiagnosis(obj);
	}
	public String getInitialDiagnosis(){
		return this.myCaseBean.getInitialDiagnosis();
	}
	public void setCaseNumber(String obj){
		
		myCaseBean.setCaseNumber(new String(obj));
		
	}
	
	public String getCaseNumber(){
		return StringUtil.getStringValue(
				myCaseBean.getCaseNumber());
		
	}
	
	public void setPhysician(String obj){
		
		myCaseBean.setPhysician(new String(obj));
		
	}
	
	public String getPhysician(){
		return StringUtil.getStringValue(
				myCaseBean.getPhysician());
		
	}
	
	public void setCaseHandler(String obj){
		
		myCaseBean.setCaseHandler(new String(obj));
		
	}
	
	public String getCaseHandler(){
		return StringUtil.getStringValue(
				myCaseBean.getCaseHandler());
		
	}
	
	
	
	public void setRoomAndBoardStatus(String obj){		
		myCaseBean.setRoomAndBoardStatus(StringUtil.getIntegerValue(obj,0));		
	}
	
	public String getRoomAndBoardStatus(){
		return StringUtil.getStringValue(
				myCaseBean.getRoomAndBoardStatus());
		
	}
	
	public void setLongOfStay(String obj){
		
		myCaseBean.setLongOfStay(StringUtil.getIntegerValue(obj,0));
		
	}
	
	public String getLongOfStay(){
		return StringUtil.getStringValue(
				myCaseBean.getLongOfStay());
		
	}
	
	
	public void setCaseType(String obj){
		
		myCaseBean.setCaseType(StringUtil.getIntegerValue(obj,0));
		
	}
	
	public String getCaseType(){
		return StringUtil.getStringValue(
				myCaseBean.getCaseType());
		
	}
	
	
	public void setCreatedTime(String obj){
		
		myCaseBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));
		
	}
	
	public String getCreatedTime(){
		return StringUtil.getStringValue(
				myCaseBean.getCreatedTime());
		
	}
	
	
	public void setCreatedBy(String obj){
		
		myCaseBean.setCreatedBy(new String(obj));
		
	}
	
	public String getCreatedBy(){
		return StringUtil.getStringValue(
				myCaseBean.getCreatedBy());
		
	}
	
	
	public void setDeletedTime(String obj){
		
		myCaseBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));
		
	}
	
	public String getDeletedTime(){
		return StringUtil.getStringValue(
				myCaseBean.getDeletedTime());
		
	}
	
	
	public void setDeletedBy(String obj){
		
		myCaseBean.setDeletedBy(new String(obj));
		
	}
	
	public String getDeletedBy(){
		return StringUtil.getStringValue(
				myCaseBean.getDeletedBy());
		
	}
	
	
	public void setModifiedTime(String obj){
		
		myCaseBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));
		
	}
	
	public String getModifiedTime(){
		return StringUtil.getStringValue(
				myCaseBean.getModifiedTime());
		
	}
	
	
	public void setModifiedBy(String obj){
		
		myCaseBean.setModifiedBy(new String(obj));
		
	}
	
	public String getModifiedBy(){
		return StringUtil.getStringValue(
				myCaseBean.getModifiedBy());
		
	}
	
	
	public void setDeletedStatus(String obj){
		
		myCaseBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));
		
	}
	
	public String getDeletedStatus(){
		return StringUtil.getStringValue(
				myCaseBean.getDeletedStatus());
		
	}
	
	
	
	
	// foreign affairs
	
	public void setItemId(Item obj){		
		myCaseBean.setItemId(obj);
		
	}
	
	public Item getItemId(){
		return myCaseBean.getItemId();
		
	}
	
	
	
	
	public void setProviderId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			myCaseBean.setProviderId(provider);		
		}
	}
	
	public String getProviderId(){
		String result = "";
		
		if (myCaseBean.getProviderId() != null){
			result = myCaseBean.getProviderId().getProviderId().toString();
		}
		return result;
				
	}
	
	

	
	
	
	public void setCaseStatusId(String obj){
		CaseStatus fk = new CaseStatus();
		fk.setCaseStatusId(StringUtil.getIntegerValue(obj,0));
		myCaseBean.setCaseStatusId(fk);
		
	}
	
	public String getCaseStatusId(){
		return StringUtil.getStringValue(
				myCaseBean.getCaseStatusId().getCaseStatusId());
		
	}
	//---
	

	
	
	
	public void setMemberId(Member obj){
		
		myCaseBean.setMemberId(obj);		
	}
	
	public Member getMemberId(){
		return myCaseBean.getMemberId();		
	}
	//---
	
	
	
	
	
	public void setDiagnosis1Id(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			myCaseBean.setDiagnosis1Id(diagnosis);		
		}
	}
	
	public String getDiagnosis1Id(){
		String result = "";
		
		if (myCaseBean.getDiagnosis1Id() != null){
			result = myCaseBean.getDiagnosis1Id().getDiagnosisId().toString();
		}
		return result;
		
		
	}
	//---
	
	
	
	
	public void setDiagnosis2Id(String obj){		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			myCaseBean.setDiagnosis2Id(diagnosis);		
		}		
	}
	
	public String getDiagnosis2Id(){
		String result = "";
		
		if (myCaseBean.getDiagnosis2Id() != null){
			result = myCaseBean.getDiagnosis2Id().getDiagnosisId().toString();
		}
		return result;
		
	}
	//---
	
	public void setDiagnosis3Id(String obj){		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Diagnosis diagnosis = new Diagnosis();
			diagnosis.setDiagnosisId(Integer.valueOf(obj));
			myCaseBean.setDiagnosis3Id(diagnosis);		
		}	
	}
	
	public String getDiagnosis3Id(){
		String result = "";
		
		if (myCaseBean.getDiagnosis3Id() != null){
			result = myCaseBean.getDiagnosis3Id().getDiagnosisId().toString();
		}
		return result;
		
	}
	//---
	
	
	public void setPriorityId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Priority priority = new Priority();
			priority.setPriorityId(Integer.valueOf(obj));
			myCaseBean.setPriorityId(priority);		
		}
	}
	
	public String getPriorityId(){
		String result = "";
		
		if (myCaseBean.getPriorityId() != null){
			result = myCaseBean.getPriorityId().getPriorityId().toString();
		}
		return result;
		
	}
	//---
	
	public void setCaseCategoryId(String obj){
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			CaseCategory cc = new CaseCategory();
			cc.setCaseCategoryId(Integer.valueOf(obj));
			myCaseBean.setCaseCategoryId(cc);
		}
		
	}
	
	public String getCaseCategoryId(){
		String result = "";
		
		if (myCaseBean.getCaseCategoryId() != null){
			result = myCaseBean.getCaseCategoryId().getCaseCategoryId().toString();
		}
		return result;
		
	}
	//---
	// -- foreign affairs end
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getHourEnd() {
		return hourEnd;
	}
	public void setHourEnd(String hourEnd) {
		this.hourEnd = hourEnd;
	}
	public String getMinuteEnd() {
		return minuteEnd;
	}
	public void setMinuteEnd(String minuteEnd) {
		this.minuteEnd = minuteEnd;
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
	
	public void setDiagnosis1(String diagnosis1Id) {
		this.diagnosis1Id = diagnosis1Id;
	}
	public void setDiagnosis2(String diagnosis2Id) {
		this.diagnosis2Id = diagnosis2Id;
	}
	public void setDiagnosis3(String diagnosis3Id) {
		this.diagnosis3Id = diagnosis3Id;
	}
	public String getDiagnosis1() {
		return this.diagnosis1Id;
		
	}
	public String getDiagnosis2() {
		return this.diagnosis2Id;
		
	}
	public String getDiagnosis3() {
		return this.diagnosis3Id;
		
	}
	public String getRoomAndBoard() {
		return myCaseBean.getRoomAndBoard();
	}
	public void setRoomAndBoard(String roomAndBoard) {
		myCaseBean.setRoomAndBoard(roomAndBoard);
	}
	
	public void setAnamnesa (String object){
		myCaseBean.setAnamnesa(object);
	}
	public String getAnamnesa (){
		return myCaseBean.getAnamnesa();
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupExpireDate() {
		return groupExpireDate;
	}
	public void setGroupExpireDate(String groupExpireDate) {
		this.groupExpireDate = groupExpireDate;
	}
	public String getDoctorId() {
		String result = "";
		
		if (myCaseBean.getProviderDoctorId() != null){
			result = myCaseBean.getProviderDoctorId().getProviderDoctorId().toString();
		}
		return result;
	}
	public void setDoctorId(String id) {
		if (id != null && !id.equalsIgnoreCase("")){
			ProviderDoctor item = new ProviderDoctor();
			item.setProviderDoctorId(Integer.valueOf(id));
			
			myCaseBean.setProviderDoctorId(item);
			
		}
	}
	public String getReferedDoctorId() {
		String result = "";
		
		if (myCaseBean.getReferedDoctorId() != null){
			result = myCaseBean.getReferedDoctorId().getProviderDoctorId().toString();
		}
		return result;
	}
	public void setReferedDoctorId(String id) {
		if (id != null && !id.equalsIgnoreCase("")){
			ProviderDoctor item = new ProviderDoctor();
			item.setProviderDoctorId(Integer.valueOf(id));
			
			myCaseBean.setReferedDoctorId(item);
			
		}
	}
	public String getProviderItemId() {
		String result = "";
	
		if (myCaseBean.getProviderItemId() != null){
			result = myCaseBean.getProviderItemId().getProviderItemId().toString();
		}
		return result;
	}
	public void setProviderItemId(String id) {
		if (id != null && !id.equalsIgnoreCase("")){
			ProviderItem item = new ProviderItem();
			item.setProviderItemId(Integer.valueOf(id));
			
			myCaseBean.setProviderItemId(item);
			
		}
	}
	public String getSuratRujukanId() {
		String result = "";
		
		if (myCaseBean.getReferedCaseId() != null){
			result = myCaseBean.getReferedCaseId().getCaseId().toString();
		}
		
		return result;
	}
	public void setSuratRujukanId(String id) {
		if (id != null && !id.equalsIgnoreCase("")){
			Case rujukan = new Case();
			rujukan.setCaseId(Integer.valueOf(id));
			
			myCaseBean.setReferedCaseId(rujukan);
		}
	}
	public String getRelationshipName() {
		return relationshipName;
	}
	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		
		if (memberStatus.equalsIgnoreCase("-1")){
			memberStatus = "PENDING";
		}
		if (memberStatus.equalsIgnoreCase("1")){
			memberStatus = "ACTIVE";
		}
		if (memberStatus.equalsIgnoreCase("2")){
			memberStatus = "TERMINATED";
		}
		if (memberStatus.equalsIgnoreCase("3")){
			memberStatus = "RESIGNED";
		}
		this.memberStatus = memberStatus;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getReferedProviderName() {
		return referedProviderName;
	}
	public void setReferedProviderName(String referedProviderName) {
		this.referedProviderName = referedProviderName;
	}
	public String getReferedProviderId() {
		String result = "";
		if (myCaseBean.getReferedProviderId() != null){
			result = myCaseBean.getReferedProviderId().getProviderId().toString();
		}
		return result;
	}
	public void setReferedProviderId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Provider provider = new Provider();
			provider.setProviderId(Integer.valueOf(obj));
			
			myCaseBean.setReferedProviderId(provider);
		}
	}
	public String getPoliklinikId() {
		String result = "";
		if (myCaseBean.getPoliklinikId() != null){
			result = myCaseBean.getPoliklinikId().getPoliklinikId().toString();
		}
		return result;
	}
	public void setPoliklinikId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Poliklinik poliklinik = new Poliklinik();
			poliklinik.setPoliklinikId(Integer.valueOf(obj));
			
			myCaseBean.setPoliklinikId(poliklinik);
		}
	}
	public String getReferedPoliklinikId() {
		String result = "";
		if (myCaseBean.getReferedPoliklinikId() != null){
			result = myCaseBean.getReferedPoliklinikId().getPoliklinikId().toString();
		}
		return result;
	}
	public void setReferedPoliklinikId(String obj) {
		if (obj != null && !obj.equalsIgnoreCase("")){
			Poliklinik poliklinik = new Poliklinik();
			poliklinik.setPoliklinikId(Integer.valueOf(obj));
			
			myCaseBean.setReferedPoliklinikId(poliklinik);
		}
	}
	public String getApprovalNote() {
		return myCaseBean.getExGratiaNotes();
	}
	public void setApprovalNote(String approval) {
		this.myCaseBean.setExGratiaNotes(approval);
	}
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
	public String getHasComplication() {
		return hasComplication;
	}

	public void setHasComplication(String hasComplication) {
		this.hasComplication = hasComplication;
		
		if (hasComplication != null){
			myCaseBean.setHasComplication(Integer.valueOf(hasComplication));
		}
	}
	
}
