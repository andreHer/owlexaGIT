package com.ametis.cms.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.FirstCall;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.MemberClaimDto;

public class EdcTerminalReportDownloader {
	public static HSSFWorkbook downloadCaseMonitoring(Collection<EdcTerminal> eventList) {
		 HSSFWorkbook workbook = new HSSFWorkbook();

	        try {
	            HSSFFont ciFont = workbook.createFont();
	            HSSFCellStyle ciStyle = workbook.createCellStyle();
	            ciStyle.setFont(ciFont);

	            HSSFCellStyle style = workbook.createCellStyle();

	            HSSFFont fontHeader = workbook.createFont();
	            fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	            fontHeader.setFontHeightInPoints((short) 9);
	            fontHeader.setFontName("Verdana");

	            style.setFont(fontHeader);

	            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);
	            style.setBottomBorderColor(HSSFColor.BLACK.index);
	            style.setFillBackgroundColor(HSSFColor.GREY_50_PERCENT.index);
	            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	            style.setLeftBorderColor(HSSFColor.BLACK.index);
	            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	            style.setRightBorderColor(HSSFColor.BLACK.index);
	            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	            style.setTopBorderColor(HSSFColor.BLACK.index);

	            
	            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
	            DecimalFormat decFormat = new DecimalFormat("#,##0.000");

	            HSSFSheet firstSheet = workbook.createSheet();

	            Iterator<EdcTerminal> edcListIterator = eventList.iterator();
		        int rowNumber = 0;
//		        HSSFRow header = firstSheet.createRow(0);

		        HSSFRow claimHeader = firstSheet.createRow(0);
				
//	            HSSFRow claimHeader = firstSheet.createRow(rowNumber++);

	            HSSFCell noCell = claimHeader.createCell(0);
	            noCell.setCellValue("No.");
	            noCell.setCellStyle(style);
	            
	            HSSFCell priorityCell = claimHeader.createCell((short) 1);
	            priorityCell.setCellValue("Terminal ID");
	            priorityCell.setCellStyle(style);

	            HSSFCell callTimeCell = claimHeader.createCell(2);
	            callTimeCell.setCellValue("Provider ID");
	            callTimeCell.setCellStyle(style);

	            HSSFCell nameCell = claimHeader.createCell(3);
	            nameCell.setCellValue("Provider Name");
	            nameCell.setCellStyle(style);

	            HSSFCell telpCell = claimHeader.createCell(4);
	            telpCell.setCellValue("Telphone");
	            telpCell.setCellStyle(style);

	            HSSFCell locationCell = claimHeader.createCell(5);
	            locationCell.setCellValue("Contact Person");
	            locationCell.setCellStyle(style);
	            
	            HSSFCell categoryCell = claimHeader.createCell(6);
	            categoryCell.setCellValue("Address");
	            categoryCell.setCellStyle(style);
	            
	            HSSFCell descCell = claimHeader.createCell(7);
	            descCell.setCellValue("Days Not Used");
	            descCell.setCellStyle(style);
	            
	            HSSFCell remarkCell = claimHeader.createCell(8);
	            remarkCell.setCellValue("Status");
	            remarkCell.setCellStyle(style);
	            
	            
	            int i = 0;

	            if(eventList !=null){
	            	if (edcListIterator != null){
	            		 for (EdcTerminal edcTerminal : eventList) {
	                		 i += 1; 
	                		 rowNumber += 1;
	
	                		 HSSFRow edcRow = firstSheet.createRow(rowNumber);
	
	                		 edcRow.createCell(0).setCellValue(i);
	                		 edcRow.createCell(1).setCellValue(edcTerminal.getDeviceNumber());
	                		 edcRow.createCell(2).setCellValue(edcTerminal.getProviderId().getEdcCode());
	                		 edcRow.createCell(3).setCellValue(edcTerminal.getProviderId().getProviderName());
	                		 edcRow.createCell(4).setCellValue(edcTerminal.getProviderId().getTelephone());
	                		 edcRow.createCell(5).setCellValue(edcTerminal.getProviderId().getContactPerson());
	                		 edcRow.createCell(6).setCellValue(edcTerminal.getProviderId().getAddress());
	                		 
	                		 if(edcTerminal.getLastTransaction()!=null){
	                			 Date dateNow = new Date();
	                			 //long differenceMilliSeconds = dateNow.getTime() - edcTerminal.getLastTransaction().getTime();
	                			 //long days = differenceMilliSeconds / 1000 / 60 / 60 / 24;
	                			 long date1 =  dateNow.getTime() / (1000*60*60*24);
	                			 long date2 = edcTerminal.getLastTransaction().getTime() / (1000*60*60*24);
	                			 long days = date1 - date2;
	                			 edcRow.createCell(7).setCellValue(days+" Day/s");
	                		 }else{
	                			 edcRow.createCell(7).setCellValue("");
	                		 }
	                		 
	                		 
	                		 if(edcTerminal.getIsFault() == 1){
	                			 edcRow.createCell(8).setCellValue("DEFECTIVE");
	                		 }else if(edcTerminal.getIsFault() == 2){
	                			 edcRow.createCell(8).setCellValue("LOST SIGNAL");
	                		 }else if(edcTerminal.getIsFault() == 3){
	                			 edcRow.createCell(8).setCellValue("EDC Belum Sampai");
	                		 }else{
	                			 edcRow.createCell(8).setCellValue("Others");
	                		 }
	            		 }  
	    			}
	            }
	               
	              
				
	            // auto size column
	            HSSFRow row = firstSheet.getRow(1);
	            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
	                firstSheet.autoSizeColumn(colNum);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return workbook;
    }
}
