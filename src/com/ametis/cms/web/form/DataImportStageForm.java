package com.ametis.cms.web.form;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.util.*;
import org.springframework.web.multipart.MultipartFile;

public class DataImportStageForm implements java.io.Serializable // extends+ 
// extends- 
{
    
    private boolean isNewDataImportStage = false;
    private DataImportStage dataImportStageBean;
    private MultipartFile fileContent;
    private String clientName;
    
    /*
     * <form>Bean merupakan representasi dari "SINGLE" table dan misalkan ada
     * form2 yang merupakan referensi dari tabel lain bikin aja field2 bean yang
     * mengacu ke referensi itu biar nanti automatic loading
     *
     */
    
    public DataImportStageForm() {
        this.dataImportStageBean = new DataImportStage();
        this.isNewDataImportStage = true;
    }

    public DataImportStageForm(DataImportStage object) {
        this.dataImportStageBean = object;
    }

    public boolean isNewDataImportStage() {
        
        return this.isNewDataImportStage;
    }

    public DataImportStage getDataImportStage() {
        return this.dataImportStageBean;
    }

    public void setDataImportStage(DataImportStage object) {
        this.dataImportStageBean = object;
    }
    
    public void setId(String obj) {
        
        dataImportStageBean.setId(StringUtil.getIntegerValue(obj, 0));
        
    }
    
    public String getId() {
        return StringUtil.getStringValue(
                dataImportStageBean.getId());
        
    }
    public void setReceiveDate(String obj) {
        
        dataImportStageBean.setReceivedDate(java.sql.Date.valueOf(obj));
        
    }
    
    public String getReceiveDate() {
        return StringUtil.getStringValue(
                dataImportStageBean.getReceivedDate());
        
    }
    public void setImportDate(String obj) {
        
        dataImportStageBean.setImportDate(java.sql.Date.valueOf(obj));
        
    }
    
    public String getImportDate() {
        return StringUtil.getStringValue(
                dataImportStageBean.getImportDate());
        
    }
    
    public void setImportRawFile(String obj) {
        
        dataImportStageBean.setImportRawFile(new String(obj));
        
    }
    
    public String getImportRawFile() {
        return StringUtil.getStringValue(
                dataImportStageBean.getImportRawFile());
        
    }
    
    public void setImportReadyFile(String obj) {
        
        dataImportStageBean.setImportReadyFile(new String(obj));
        
    }
    
    public String getImportReadyFile() {
        return StringUtil.getStringValue(
                dataImportStageBean.getImportReadyFile());
        
    }
    
    public void setStatus(String obj) {
        
        dataImportStageBean.setStatus(StringUtil.getIntegerValue(obj, 0));
        
    }
    
    public String getStatus() {
        return StringUtil.getStringValue(
                dataImportStageBean.getStatus());
        
    }
    
    public void setImportNumber(String obj) {
        
        dataImportStageBean.setImportNumber(new String(obj));
        
    }
    
    public String getImportNumber() {
        return StringUtil.getStringValue(
                dataImportStageBean.getImportNumber());
        
    }
    
    public void setDescription(String obj) {
        
        dataImportStageBean.setDescription(new String(obj));
        
    }
    
    public String getDescription() {
        return StringUtil.getStringValue(
                dataImportStageBean.getDescription());
        
    }
    
    public void setCreatedTime(String obj) {
        
        dataImportStageBean.setCreatedTime(java.sql.Timestamp.valueOf(obj));
        
    }
    
    public String getCreatedTime() {
        return StringUtil.getStringValue(
                dataImportStageBean.getCreatedTime());
        
    }
    
    public void setCreatedBy(String obj) {
        
        dataImportStageBean.setCreatedBy(new String(obj));
        
    }
    
    public String getCreatedBy() {
        return StringUtil.getStringValue(
                dataImportStageBean.getCreatedBy());
        
    }
    
    public void setModifiedTime(String obj) {
        
        dataImportStageBean.setModifiedTime(java.sql.Timestamp.valueOf(obj));
        
    }
    
    public String getModifiedTime() {
        return StringUtil.getStringValue(
                dataImportStageBean.getModifiedTime());
        
    }
    
    public void setModifiedBy(String obj) {
        
        dataImportStageBean.setModifiedBy(new String(obj));
        
    }
    
    public String getModifiedBy() {
        return StringUtil.getStringValue(
                dataImportStageBean.getModifiedBy());
        
    }
    
    public void setDeletedTime(String obj) {
        
        dataImportStageBean.setDeletedTime(java.sql.Timestamp.valueOf(obj));
        
    }
    
    public String getDeletedTime() {
        return StringUtil.getStringValue(
                dataImportStageBean.getDeletedTime());
        
    }
    
    public void setDeletedBy(String obj) {
        
        dataImportStageBean.setDeletedBy(new String(obj));
        
    }
    
    public String getDeletedBy() {
        return StringUtil.getStringValue(
                dataImportStageBean.getDeletedBy());
        
    }
    
    public void setDeletedStatus(String obj) {
        
        dataImportStageBean.setDeletedStatus(StringUtil.getIntegerValue(obj, 0));
        
    }
    
    public String getDeletedStatus() {
        return StringUtil.getStringValue(
                dataImportStageBean.getDeletedStatus());
        
    }

    public void setFile(MultipartFile content) {
        System.out.println("INI DI DALAM DATA IMPORT STAGE FORM : " + content);
        this.fileContent = content;
    }

    public MultipartFile getFile() {
        return this.fileContent;
    }

    // foreign affairs
    // -- foreign affairs end
// class+ 
// class- 
    public String getTemplate() {
        String result = "";
        
        if (dataImportStageBean.getExportImportTemplate() != null) {
            result = dataImportStageBean.getExportImportTemplate().getId().toString();
        }
        return result;
    }

    public void setTemplate(String obj) {
        if (obj != null && !obj.equalsIgnoreCase("")) {
            ExportImportTemplate template = new ExportImportTemplate();
            template.setId(Integer.valueOf(obj));
            
            dataImportStageBean.setExportImportTemplate(template);
            
        }
    }
    public String getTipe(){
        String result = "";
        
        if (dataImportStageBean.getTipe() != null){
            result = dataImportStageBean.getTipe().toString();
        }
        return result;
    }
    public void setTipe (String tipe){
        if (tipe != null && !tipe.equalsIgnoreCase("")){
            dataImportStageBean.setTipe(Integer.valueOf(tipe));
        }
    }

    public String getFileFormat() {
        return dataImportStageBean.getFileFormat();
    }
    
    public void setFileFormat(String fileType) {
        this.dataImportStageBean.setFileFormat(fileType);
    }
    public String getDatePattern() {
        return dataImportStageBean.getDatePattern();
    }
    
    public void setDatePattern(String pattern) {
        this.dataImportStageBean.setDatePattern(pattern);
    }
    public String getClientId() {
    	String result = "";
    	if (dataImportStageBean.getClientId() != null){
    		result = dataImportStageBean.getClientId().getClientId().toString();
    	}
    	return result;
        
    }
    
    public void setClientId(String clientId) {
    	if (clientId != null && !clientId.equalsIgnoreCase("")){
    		Client client = new Client();
    		client.setClientId(Integer.valueOf(clientId));
    		
    		this.dataImportStageBean.setClientId(client);
    	}
    }
    
    public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getFirstLineHeader() {
    	String result = "";
    	
    	if (dataImportStageBean.getFirstLineHeader() != null){
    		result = dataImportStageBean.getFirstLineHeader().toString();
    	}
        return result;
    }
    
    public void setFirstLineHeader(String header) {
    	if (header != null && !header.equalsIgnoreCase("")){
    		this.dataImportStageBean.setFirstLineHeader(Integer.valueOf(header));
    	}
    }
}
