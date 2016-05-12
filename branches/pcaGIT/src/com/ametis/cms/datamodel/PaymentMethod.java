package com.ametis.cms.datamodel;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="payment_method")
public class PaymentMethod implements Serializable{
	
	public static final int TRANSFER = 1;
	public static final int CASH = 2;
	
	public static final int MODE_ANNUALLY = 1;
	public static final int MODE_SEMESTERLY = 2;
	public static final int MODE_QUARTERLY = 3;
	
	
	private Integer paymentMethodId;
	private String paymentMethod;
	private String description;
	private Integer isDefault;
	
	public PaymentMethod (){
		
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="payment_method_name")
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	@Id
	@Column(name="payment_method_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	@Column(name="is_default")
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	
}
