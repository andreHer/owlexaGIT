
package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.util.StringUtil;

// imports- 

/**
 * PaymentRecipient is a mapping of payment_recipient Table.
*/
public class PaymentRecipientForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewPaymentRecipient = false;
	private PaymentRecipient paymentRecipientBean ;
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public PaymentRecipientForm()
    {
    	this.paymentRecipientBean = new PaymentRecipient();
    	this.isNewPaymentRecipient = true;
    }
    public PaymentRecipientForm (PaymentRecipient object){
		this.paymentRecipientBean = object;
    }
    public boolean isNewPaymentRecipient (){

    	return this.isNewPaymentRecipient;
    }
	public PaymentRecipient getPaymentRecipient (){
		return this.paymentRecipientBean ;
	}
	public void setPaymentRecipient (PaymentRecipient object){
		this.paymentRecipientBean = object;
	}

			
	public void setPaymentRecipientId(String obj){

		paymentRecipientBean.setPaymentRecipientId(StringUtil.getIntegerValue(obj,0));

	}

	public String getPaymentRecipientId(){
		return StringUtil.getStringValue(
		paymentRecipientBean.getPaymentRecipientId());

	}
	
					public void setPaymentRecipientName(String obj){

		paymentRecipientBean.setPaymentRecipientName(new String(obj));

	}

	public String getPaymentRecipientName(){
		return StringUtil.getStringValue(
		paymentRecipientBean.getPaymentRecipientName());

	}
	
					public void setDescription(String obj){

		paymentRecipientBean.setDescription(new String(obj));

	}

	public String getDescription(){
		return StringUtil.getStringValue(
		paymentRecipientBean.getDescription());

	}
	
		

	// foreign affairs
		// -- foreign affairs end

// class+ 

// class- 
}
