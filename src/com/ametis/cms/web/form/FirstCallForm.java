
package com.ametis.cms.web.form;

import java.sql.Timestamp;
import java.util.Date;

import com.ametis.cms.datamodel.CallCategory;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.FirstCall;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Priority;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * FirstCall is a mapping of first_call Table.
*/
public class FirstCallForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewFirstCall = false;
	private FirstCall firstCallBean ;
	private String time;
	private Date date;
	private String hour;
	private String minute;
	private Integer memberId;
	
	private String clientName;
	private String groupName;
	private String memberStatus;
	//private Priority priority;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public FirstCallForm()
    {
    	this.firstCallBean = new FirstCall();
    	this.isNewFirstCall = true;
    }
    public FirstCallForm (FirstCall object){
		this.firstCallBean = object;
    }
    public boolean isNewFirstCall (){
    	
    	return this.isNewFirstCall;
    }
	public FirstCall getFirstCall (){
		return this.firstCallBean ;
	}
	public void setFirstCall (FirstCall object){
		this.firstCallBean = object;
	}

			
	public void setCallId(String obj){

		firstCallBean.setCallId(StringUtil.getIntegerValue(obj,0));

	}

	public String getCallId(){
		return StringUtil.getStringValue(
		firstCallBean.getCallId());

	}
	
				
	public void setCallStartTime(Timestamp obj){
		firstCallBean.setCallStartTime(obj);

	}

	public Timestamp getCallStartTime(){
		return firstCallBean.getCallStartTime();

	}
	
	/*public String getCallStartTime(){
		return StringUtil.getStringValue(
		firstCallBean.getCallStartTime());

	}*/
	
				
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public void setCallEndTime(Timestamp obj){

		firstCallBean.setCallEndTime(obj);

	}

	public Timestamp getCallEndTime(){
		return firstCallBean.getCallEndTime();

	}

	
	public void setDescription(String obj){

		firstCallBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		firstCallBean.getDescription());

	}
	
	public void setRemarks(String obj){

		firstCallBean.setRemarks(new String(obj));

	}

	public String getRemarks(){
		return StringUtil.getStringValue(
		firstCallBean.getRemarks());

	}
	
	public void setCallerName(String obj){
		firstCallBean.setCallerName(new String(obj));

	}

	public String getCallerName(){
		return StringUtil.getStringValue(
		firstCallBean.getCallerName());

	}
	
	public void setCity(String obj){

		firstCallBean.setCity(new String(obj));

	}

	public String getCity(){
		return StringUtil.getStringValue(
		firstCallBean.getCity());

	}
	
							
	public void setCreatedTime(String obj){

		firstCallBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		firstCallBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		firstCallBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		firstCallBean.getCreatedBy());

	}
	
				
	public void setDeletedTime(String obj){

		firstCallBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		firstCallBean.getDeletedTime());

	}

	
					public void setDeletedBy(String obj){

		firstCallBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		firstCallBean.getDeletedBy());

	}
	
				
	public void setModifiedTime(String obj){

		firstCallBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		firstCallBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		firstCallBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		firstCallBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		firstCallBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		firstCallBean.getDeletedStatus());

	}
	
					public void setLocation(String obj){

		firstCallBean.setLocation(new String(obj));

	}

	public String getLocation(){
		return StringUtil.getStringValue(
		firstCallBean.getLocation());

	}
	
					public void setProvider(String obj){

		firstCallBean.setProvider(new String(obj));

	}

	public String getProvider(){
		return StringUtil.getStringValue(
		firstCallBean.getProvider());

	}
	
		

	// foreign affairs
	
	

	
	public void setHandledby(String obj){
		User fk = new User();
		fk.setUserId(StringUtil.getIntegerValue(obj,0));
		firstCallBean.setHandledby(fk);

	}

	public String getHandledby(){
		return StringUtil.getStringValue(
		firstCallBean.getHandledby().getUserId());

	}
	//---
	
	

	
	public void setStatus(String obj){
		CaseStatus fk = new CaseStatus();
		fk.setCaseStatusId(StringUtil.getIntegerValue(obj,0));
		firstCallBean.setStatus(fk);

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		firstCallBean.getStatus().getCaseStatusId());

	}
	//---
	
	

	
	public void setCallCategoryId(String obj){
		CallCategory fk = new CallCategory();
		fk.setCallCategoryId(StringUtil.getIntegerValue(obj,0));
		firstCallBean.setCallCategoryId(fk);

	}

	public String getCallCategoryId(){
		String result = "";
		
		if (firstCallBean.getCallCategoryId() != null){
			result = StringUtil.getStringValue(
					firstCallBean.getCallCategoryId().getCallCategoryId());
		}
		return result;

	}
	
	public void setPriority(String obj) {
		
		if (obj != null && !obj.equalsIgnoreCase("")){
			Priority priority = new Priority();
			priority.setPriorityId(Integer.valueOf(obj));
			firstCallBean.setPriority(priority);
		}
	}
	public String getPriority() {
		String result = "";
		
		if (firstCallBean.getPriority() != null){
			result = firstCallBean.getPriority().getPriorityId().toString();
		}
		return result;
		
	}

	
	//---
	
	

	
	public void setCustomerId(Member obj){		
		firstCallBean.setCustomerId(obj);

	}

	public Member getCustomerId(){
		return 
		firstCallBean.getCustomerId();

	}
	public void setCaseId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Case myCase = new Case();
			myCase.setCaseId(Integer.valueOf(obj));
			firstCallBean.setCaseId(myCase);
		}

	}

	public String getClaimId(){
		String result = "";
		
		if (firstCallBean.getClaimId() != null){
			result = StringUtil.getStringValue(
					firstCallBean.getClaimId().getClaimId());
		}
		return result;

	}
	public void setClaimId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Claim claim = new Claim();
			claim.setClaimId(Integer.valueOf(obj));
			firstCallBean.setClaimId(claim);
		}

	}

	public String getCaseId(){
		String result = "";
		
		if (firstCallBean.getCaseId() != null){
			result = StringUtil.getStringValue(
					firstCallBean.getCaseId().getCaseId());
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public void setTelephoneNumber (String number){
		this.firstCallBean.setTelephone(number);
	}
	public String getTelephoneNumber (){
		return this.firstCallBean.getTelephone();
	}
	public void setFollowup (String status){
		this.firstCallBean.setFollowup(status);
	}	
	public String getFollowup (){		
		return this.firstCallBean.getFollowup();
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
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		if (memberStatus.equalsIgnoreCase("1")){
			memberStatus = "ACTIVE";
		}
		if (memberStatus.equalsIgnoreCase("-1")){
			memberStatus = "PENDING";
		}
		if (memberStatus.equalsIgnoreCase("2")){
			memberStatus = "TERMINATED";
		}
		if (memberStatus.equalsIgnoreCase("3")){
			memberStatus = "RESIGNED";
		}
		
		this.memberStatus = memberStatus;
	}
	
	public void setCallLogType(String obj){

		firstCallBean.setCallLogType(StringUtil.getIntegerValue(obj,0));

	}

	public String getCallLogType(){
		return StringUtil.getStringValue(
		firstCallBean.getCallLogType());

	}
	

// class+ 

// class- 
}
