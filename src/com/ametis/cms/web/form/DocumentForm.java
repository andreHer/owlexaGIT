
package com.ametis.cms.web.form;

import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
// imports+ 

// imports- 

/**
 * Document is a mapping of document Table.
*/
public class DocumentForm implements java.io.Serializable
// extends+ 

// extends- 
{


	private boolean isNewDocument = false;
	private Document documentBean ;
	
	private MultipartFile file1;
	private MultipartFile file2;
	private MultipartFile file3;
	private MultipartFile file4;
	private MultipartFile file5;
	
	private String docNum1;
	private String docNum2;
	private String docNum3;
	private String docNum4;
	private String docNum5;
	
	private String documentCategoryId1;
	private String documentCategoryId2;
	private String documentCategoryId3;
	private String documentCategoryId4;
	private String documentCategoryId5;
	
	private String memberNumber;
	private String memberName;
	private String claimNumber;
	
	/*
		<form>Bean merupakan representasi dari "SINGLE" table dan
		misalkan ada form2 yang merupakan referensi dari tabel lain
		bikin aja field2 bean yang mengacu ke referensi itu
		biar nanti automatic loading

	*/

    public DocumentForm()
    {
    	this.documentBean = new Document();
    	this.isNewDocument = true;
    }
    public DocumentForm (Document object){
		this.documentBean = object;
    }
    public boolean isNewDocument (){

    	return this.isNewDocument;
    }
	public Document getDocument (){
		return this.documentBean ;
	}
	public void setDocument (Document object){
		this.documentBean = object;
	}

			
	public void setDocumentId(String obj){

		documentBean.setDocumentId(StringUtil.getIntegerValue(obj,0));

	}

	public String getDocumentId(){
		return StringUtil.getStringValue(
		documentBean.getDocumentId());

	}
	
					public void setDocumentNumber(String obj){

		documentBean.setDocumentNumber(new String(obj));

	}

	public String getDocumentNumber(){
		return StringUtil.getStringValue(
		documentBean.getDocumentNumber());

	}
	
										
	public void setStatus(String obj){

		documentBean.setStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getStatus(){
		return StringUtil.getStringValue(
		documentBean.getStatus());

	}
	
					public void setDocumentUrl(String obj){

		documentBean.setDocumentUrl(new String(obj));

	}

	public String getDocumentUrl(){
		return StringUtil.getStringValue(
		documentBean.getDocumentUrl());

	}
	
				
	public void setCreatedTime(String obj){

		documentBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getCreatedTime(){
		return StringUtil.getStringValue(
		documentBean.getCreatedTime());

	}

	
					public void setCreatedBy(String obj){

		documentBean.setCreatedBy(new String(obj));

	}

	public String getCreatedBy(){
		return StringUtil.getStringValue(
		documentBean.getCreatedBy());

	}
	
				
	public void setModifiedTime(String obj){

		documentBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getModifiedTime(){
		return StringUtil.getStringValue(
		documentBean.getModifiedTime());

	}

	
					public void setModifiedBy(String obj){

		documentBean.setModifiedBy(new String(obj));

	}

	public String getModifiedBy(){
		return StringUtil.getStringValue(
		documentBean.getModifiedBy());

	}
	
				
	public void setDeletedStatus(String obj){

		documentBean.setDeletedStatus(StringUtil.getIntegerValue(obj,0));

	}

	public String getDeletedStatus(){
		return StringUtil.getStringValue(
		documentBean.getDeletedStatus());

	}
	
					public void setDeletedBy(String obj){

		documentBean.setDeletedBy(new String(obj));

	}

	public String getDeletedBy(){
		return StringUtil.getStringValue(
		documentBean.getDeletedBy());

	}
	
				
	public void setDeletedTime(String obj){

		documentBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));

	}

	public String getDeletedTime(){
		return StringUtil.getStringValue(
		documentBean.getDeletedTime());

	}

	
					

	// foreign affairs
	
	
	public void setCaseId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Case fk = new Case();
			fk.setCaseId(StringUtil.getIntegerValue(obj,0));
			documentBean.setCaseId(fk);
		}

	}

	public String getCaseId(){
		String result = "";
		
		if (documentBean.getCaseId() != null){
			result = StringUtil.getStringValue(
					documentBean.getCaseId().getCaseId());
		}
		return result;
		

	}
	
	public void setClaimId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Claim fk = new Claim();
			fk.setClaimId(StringUtil.getIntegerValue(obj,0));
			documentBean.setClaimId(fk);
		}

	}

	public String getClaimId(){
		String result = "";
		
		if (documentBean.getClaimId() != null){
			result = StringUtil.getStringValue(
					documentBean.getClaimId().getClaimId());
		}
		return result;
		

	}
	//---
	
	

	
	public void setDocumentCategoryId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			DocumentCategory fk = new DocumentCategory();
			fk.setDocumentCategoryId(StringUtil.getIntegerValue(obj,0));
			documentBean.setDocumentCategoryId(fk);
		}

	}

	public String getDocumentCategoryId(){
		String result = "";
		
		if (documentBean.getDocumentCategoryId() != null){
			result = StringUtil.getStringValue(
					documentBean.getDocumentCategoryId().getDocumentCategoryId());
		}
		return result;
		

	}
	//---
	
	

	
	public void setMemberId(String obj){
		if (obj != null && !obj.equalsIgnoreCase("")){
			Member fk = new Member();
			fk.setMemberId(StringUtil.getIntegerValue(obj,0));
			documentBean.setMemberId(fk);
		}

	}

	public String getMemberId(){
		String result = "";
		
		if (documentBean.getMemberId() != null){
			result = StringUtil.getStringValue(
					documentBean.getMemberId().getMemberId());
		}
		return result;
		

	}
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	public MultipartFile getFile2() {
		return file2;
	}
	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
	public MultipartFile getFile3() {
		return file3;
	}
	public void setFile3(MultipartFile file3) {
		this.file3 = file3;
	}
	public MultipartFile getFile4() {
		return file4;
	}
	public void setFile4(MultipartFile file4) {
		this.file4 = file4;
	}
	public MultipartFile getFile5() {
		return file5;
	}
	public void setFile5(MultipartFile file5) {
		this.file5 = file5;
	}
	public String getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getDocNum1() {
		return docNum1;
	}
	public void setDocNum1(String docNum1) {
		this.docNum1 = docNum1;
	}
	public String getDocNum2() {
		return docNum2;
	}
	public void setDocNum2(String docNum2) {
		this.docNum2 = docNum2;
	}
	public String getDocNum3() {
		return docNum3;
	}
	public void setDocNum3(String docNum3) {
		this.docNum3 = docNum3;
	}
	public String getDocNum4() {
		return docNum4;
	}
	public void setDocNum4(String docNum4) {
		this.docNum4 = docNum4;
	}
	public String getDocNum5() {
		return docNum5;
	}
	public void setDocNum5(String docNum5) {
		this.docNum5 = docNum5;
	}
	public String getDocumentCategoryId1() {
		return documentCategoryId1;
	}
	public void setDocumentCategoryId1(String documentCategoryId1) {
		this.documentCategoryId1 = documentCategoryId1;
	}
	public String getDocumentCategoryId2() {
		return documentCategoryId2;
	}
	public void setDocumentCategoryId2(String documentCategoryId2) {
		this.documentCategoryId2 = documentCategoryId2;
	}
	public String getDocumentCategoryId3() {
		return documentCategoryId3;
	}
	public void setDocumentCategoryId3(String documentCategoryId3) {
		this.documentCategoryId3 = documentCategoryId3;
	}
	public String getDocumentCategoryId4() {
		return documentCategoryId4;
	}
	public void setDocumentCategoryId4(String documentCategoryId4) {
		this.documentCategoryId4 = documentCategoryId4;
	}
	public String getDocumentCategoryId5() {
		return documentCategoryId5;
	}
	public void setDocumentCategoryId5(String documentCategoryId5) {
		this.documentCategoryId5 = documentCategoryId5;
	}
	
	
}
