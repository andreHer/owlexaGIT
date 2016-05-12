
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * GroupClaimUtilReport is a mapping of group_claim_util_report Table.
*/
public class GroupClaimUtilReportForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewGroupClaimUtilReport = false;
	private GroupClaimUtilReport groupClaimUtilReportBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public GroupClaimUtilReportForm()
    {
    	this.groupClaimUtilReportBean = new GroupClaimUtilReport();
    	this.isNewGroupClaimUtilReport = true;
    }
    public GroupClaimUtilReportForm (GroupClaimUtilReport object){
		this.groupClaimUtilReportBean = object;
    }
    public boolean isNewGroupClaimUtilReport (){

    	return this.isNewGroupClaimUtilReport;
    }
	public GroupClaimUtilReport getGroupClaimUtilReport (){
		return this.groupClaimUtilReportBean ;
	}
	public void setGroupClaimUtilReport (GroupClaimUtilReport object){
		this.groupClaimUtilReportBean = object;
	}

			
	public void setId(String obj){

		groupClaimUtilReportBean.setId(StringUtil.getLongValue(obj,0));

	}

	public String getId(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getId());

	}
	
				
	public void setReportDate(String obj){

		groupClaimUtilReportBean.setReportDate(java.sql.Date.valueOf(obj));

	}

	public String getReportDate(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getReportDate());

	}

	
					public void setGroupName(String obj){

		groupClaimUtilReportBean.setGroupName(new String(obj));

	}

	public String getGroupName(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getGroupName());

	}
	
					public void setGroupPeriode(String obj){

		groupClaimUtilReportBean.setGroupPeriode(new String(obj));

	}

	public String getGroupPeriode(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getGroupPeriode());

	}
	
				
	public void setClaimTotal(String obj){

		groupClaimUtilReportBean.setClaimTotal(StringUtil.getIntegerValue(obj,0));

	}

	public String getClaimTotal(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getClaimTotal());

	}
	
				
	public void setClaimTotalNominal(String obj){

		groupClaimUtilReportBean.setClaimTotalNominal(StringUtil.getDoubleValue(obj,0));

	}

	public String getClaimTotalNominal(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getClaimTotalNominal());

	}
	
				
	public void setTotalMember(String obj){

		groupClaimUtilReportBean.setTotalMember(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMember(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalMember());

	}
	
				
	public void setTotalClaimRatio(String obj){

		groupClaimUtilReportBean.setTotalClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalClaimRatio(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalClaimRatio());

	}
	
				
	public void setTotalInpatient(String obj){

		groupClaimUtilReportBean.setTotalInpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalInpatient(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalInpatient());

	}
	
				
	public void setTotalNominalInpatient(String obj){

		groupClaimUtilReportBean.setTotalNominalInpatient(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalInpatient(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalNominalInpatient());

	}
	
				
	public void setTotalOutpatient(String obj){

		groupClaimUtilReportBean.setTotalOutpatient(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalOutpatient(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalOutpatient());

	}
	
				
	public void setTotalNominalOutpatient(String obj){

		groupClaimUtilReportBean.setTotalNominalOutpatient(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalOutpatient(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalNominalOutpatient());

	}
	
				
	public void setTotalDental(String obj){

		groupClaimUtilReportBean.setTotalDental(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalDental(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalDental());

	}
	
				
	public void setTotalNominalDental(String obj){

		groupClaimUtilReportBean.setTotalNominalDental(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalDental(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalNominalDental());

	}
	
				
	public void setTotalMaternity(String obj){

		groupClaimUtilReportBean.setTotalMaternity(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMaternity(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalMaternity());

	}
	
				
	public void setTotalNominalMaternity(String obj){

		groupClaimUtilReportBean.setTotalNominalMaternity(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalMaternity(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalNominalMaternity());

	}
	
				
	public void setTotalOptical(String obj){

		groupClaimUtilReportBean.setTotalOptical(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalOptical(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalOptical());

	}
	
				
	public void setTotalNominalOptical(String obj){

		groupClaimUtilReportBean.setTotalNominalOptical(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalOptical(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalNominalOptical());

	}
	
				
	public void setTotalMcu(String obj){

		groupClaimUtilReportBean.setTotalMcu(StringUtil.getIntegerValue(obj,0));

	}

	public String getTotalMcu(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalMcu());

	}
	
				
	public void setTotalNominalMcu(String obj){

		groupClaimUtilReportBean.setTotalNominalMcu(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalNominalMcu(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalNominalMcu());

	}
	
				
	public void setInpatientClaimRatio(String obj){

		groupClaimUtilReportBean.setInpatientClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getInpatientClaimRatio(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getInpatientClaimRatio());

	}
	
				
	public void setOutpatientClaimRatio(String obj){

		groupClaimUtilReportBean.setOutpatientClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getOutpatientClaimRatio(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getOutpatientClaimRatio());

	}
	
				
	public void setDentalClaimRatio(String obj){

		groupClaimUtilReportBean.setDentalClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getDentalClaimRatio(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getDentalClaimRatio());

	}
	
				
	public void setMaternityClaimRatio(String obj){

		groupClaimUtilReportBean.setMaternityClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getMaternityClaimRatio(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getMaternityClaimRatio());

	}
	
				
	public void setOpticalClaimRatio(String obj){

		groupClaimUtilReportBean.setOpticalClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getOpticalClaimRatio(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getOpticalClaimRatio());

	}
	
				
	public void setMcuClaimRatio(String obj){

		groupClaimUtilReportBean.setMcuClaimRatio(StringUtil.getDoubleValue(obj,0));

	}

	public String getMcuClaimRatio(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getMcuClaimRatio());

	}
	
				
	public void setGroupId(String obj){

		if (obj != null && !obj.equalsIgnoreCase("")){
			
			MemberGroup group = new MemberGroup();
			group.setMemberGroupId(Integer.valueOf(obj));
			groupClaimUtilReportBean.setGroupId(group);
		}

	}

	public String getGroupId(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getGroupId().getMemberGroupId());

	}
	
				
	public void setTotalMemberPremium(String obj){

		groupClaimUtilReportBean.setTotalMemberPremium(StringUtil.getDoubleValue(obj,0));

	}

	public String getTotalMemberPremium(){
		return StringUtil.getStringValue(
		groupClaimUtilReportBean.getTotalMemberPremium());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
