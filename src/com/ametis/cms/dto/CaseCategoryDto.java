package com.ametis.cms.dto;

import java.io.Serializable;

public class CaseCategoryDto implements Serializable{

	private Integer caseCategoryId;
	private String caseCategoryName;
	private String description;

	private String caseCategoryCode;
	private String caseCategoryEdcCode;
	
	public CaseCategoryDto(){}

	public Integer getCaseCategoryId() {
		return caseCategoryId;
	}

	public void setCaseCategoryId(Integer caseCategoryId) {
		this.caseCategoryId = caseCategoryId;
	}

	public String getCaseCategoryName() {
		return caseCategoryName;
	}

	public void setCaseCategoryName(String caseCategoryName) {
		this.caseCategoryName = caseCategoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCaseCategoryCode() {
		return caseCategoryCode;
	}

	public void setCaseCategoryCode(String caseCategoryCode) {
		this.caseCategoryCode = caseCategoryCode;
	}

	public String getCaseCategoryEdcCode() {
		return caseCategoryEdcCode;
	}

	public void setCaseCategoryEdcCode(String caseCategoryEdcCode) {
		this.caseCategoryEdcCode = caseCategoryEdcCode;
	}
	
	
}
