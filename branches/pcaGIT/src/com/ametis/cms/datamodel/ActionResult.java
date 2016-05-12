package com.ametis.cms.datamodel;

import java.io.Serializable;
import java.util.Collection;

public class ActionResult implements Serializable{

	private Object resultObject;
	private String actionCode;
	private String additionalMessage;
	private String referenceNumber;
	private String reason;
	
	private boolean result;
	private Collection<Object> resultCollection;
	
	public ActionResult(){}
	
	public Object getResultObject() {
		return resultObject;
	}
	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getAdditionalMessage() {
		return additionalMessage;
	}
	public void setAdditionalMessage(String additionalMessage) {
		this.additionalMessage = additionalMessage;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Collection<Object> getResultCollection() {
		return resultCollection;
	}
	public void setResultCollection(Collection<Object> resultCollection) {
		this.resultCollection = resultCollection;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
