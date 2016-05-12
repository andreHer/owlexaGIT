package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;

// imports+ 

// imports- 

/**
 * ScheduledPayment is a mapping of scheduled_payment Table.
 */
public class ScheduledPaymentForm implements java.io.Serializable
// extends+

// extends-

{

	private boolean isNewScheduledPayment = false;
	private ScheduledPayment scheduledPaymentBean;

	/*
	 * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
	 * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
	 * mengacu ke referensi itu biar nanti automatic loading
	 * 
	 */

	public ScheduledPaymentForm() {
		this.scheduledPaymentBean = new ScheduledPayment();
		this.isNewScheduledPayment = true;
	}

	public ScheduledPaymentForm(ScheduledPayment object) {
		this.scheduledPaymentBean = object;
	}

	public boolean isNewScheduledPayment() {

		return this.isNewScheduledPayment;
	}

	public ScheduledPayment getScheduledPayment() {
		return this.scheduledPaymentBean;
	}

	public void setScheduledPayment(ScheduledPayment object) {
		this.scheduledPaymentBean = object;
	}

	public void setId(String obj) {

		scheduledPaymentBean.setId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getId() {
		return StringUtil.getStringValue(scheduledPaymentBean.getId());

	}

	public void setScheduleDate(String obj) {

		scheduledPaymentBean.setScheduleDate(java.sql.Date.valueOf(obj));

	}

	public String getScheduleDate() {
		return StringUtil
				.getStringValue(scheduledPaymentBean.getScheduleDate());

	}

	public void setNumber(String obj) {

		scheduledPaymentBean.setNumber(new String(obj));

	}

	public String getNumber() {
		return StringUtil.getStringValue(scheduledPaymentBean.getNumber());

	}

	public void setDescription(String obj) {

		scheduledPaymentBean.setDescription(new String(obj));

	}

	public String getDescription() {
		return StringUtil.getStringValue(scheduledPaymentBean.getDescription());

	}

	public void setTipe(String obj) {

		scheduledPaymentBean.setTipe(StringUtil.getIntegerValue(obj, 0));

	}

	public String getTipe() {
		return StringUtil.getStringValue(scheduledPaymentBean.getTipe());

	}

	public void setTotalAmount(String obj) {

		scheduledPaymentBean.setTotalAmount(StringUtil.getDoubleValue(obj, 0));

	}

	public String getTotalAmount() {
		return StringUtil.getStringValue(scheduledPaymentBean.getTotalAmount());

	}

	public void setTax(String obj) {

		scheduledPaymentBean.setTax(StringUtil.getDoubleValue(obj, 0));

	}

	public String getTax() {
		return StringUtil.getStringValue(scheduledPaymentBean.getTax());

	}

	public void setTotalAfterTax(String obj) {

		scheduledPaymentBean
				.setTotalAfterTax(StringUtil.getDoubleValue(obj, 0));

	}

	public String getTotalAfterTax() {
		return StringUtil.getStringValue(scheduledPaymentBean
				.getTotalAfterTax());

	}

	public void setCreatedTime(String obj) {

		scheduledPaymentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime() {
		return StringUtil.getStringValue(scheduledPaymentBean.getCreatedTime());

	}

	public void setCreatedBy(String obj) {

		scheduledPaymentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy() {
		return StringUtil.getStringValue(scheduledPaymentBean.getCreatedBy());

	}

	public void setModifiedTime(String obj) {

		scheduledPaymentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime() {
		return StringUtil
				.getStringValue(scheduledPaymentBean.getModifiedTime());

	}

	public void setModifiedBy(String obj) {

		scheduledPaymentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy() {
		return StringUtil.getStringValue(scheduledPaymentBean.getModifiedBy());

	}

	public void setDeletedTime(String obj) {

		scheduledPaymentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime() {
		return StringUtil.getStringValue(scheduledPaymentBean.getDeletedTime());

	}

	public void setDeletedBy(String obj) {

		scheduledPaymentBean.setDeletedBy(obj);

	}

	public String getDeletedBy() {
		return StringUtil.getStringValue(scheduledPaymentBean.getDeletedBy());

	}

	public void setDeletedStatus(String obj) {

		scheduledPaymentBean.setDeletedStatus(StringUtil
				.getIntegerValue(obj, 0));

	}

	public String getDeletedStatus() {
		return StringUtil.getStringValue(scheduledPaymentBean
				.getDeletedStatus());

	}

	public void setPolicyId(String obj) {

		scheduledPaymentBean.setPolicyId(StringUtil.getIntegerValue(obj, 0));

	}

	public String getPolicyId() {
		return StringUtil.getStringValue(scheduledPaymentBean.getPolicyId());

	}

	public void setExpireDate(String obj) {

		scheduledPaymentBean.setExpireDate(java.sql.Date.valueOf(obj));

	}

	public String getExpireDate() {
		return StringUtil.getStringValue(scheduledPaymentBean.getExpireDate());

	}

	public void setScheduleGeneratedDate(String obj) {

		scheduledPaymentBean.setScheduleGeneratedDate(java.sql.Date
				.valueOf(obj));

	}

	public String getScheduleGeneratedDate() {
		return StringUtil.getStringValue(scheduledPaymentBean
				.getScheduleGeneratedDate());

	}

	public void setStatus(String obj) {

		scheduledPaymentBean.setStatus(StringUtil.getIntegerValue(obj, 0));

	}

	public String getStatus() {
		return StringUtil.getStringValue(scheduledPaymentBean.getStatus());

	}

	// foreign affairs
	// -- foreign affairs end

	// class+

	// class-

}
