package com.ametis.cms.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.FirstCall;
import com.ametis.cms.datamodel.MemberGroup;

public class FirstCallDownloader {

	public static HSSFWorkbook downloadReport(String logType, Collection<FirstCall> logList) throws Exception {
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

            Iterator<FirstCall> logListIterator = logList.iterator();
	        int rowNumber = 0;
//	        HSSFRow header = firstSheet.createRow(0);

	        HSSFRow claimHeader = firstSheet.createRow(0);
			
//            HSSFRow claimHeader = firstSheet.createRow(rowNumber++);

            HSSFCell noCell = claimHeader.createCell(0);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);
            
            HSSFCell priorityCell = claimHeader.createCell((short) 1);
            priorityCell.setCellValue("Priority");
            priorityCell.setCellStyle(style);

            HSSFCell callTimeCell = claimHeader.createCell(2);
            callTimeCell.setCellValue("Call Time");
            callTimeCell.setCellStyle(style);

            HSSFCell nameCell = claimHeader.createCell(3);
            nameCell.setCellValue("Caller Name");
            nameCell.setCellStyle(style);

            HSSFCell telpCell = claimHeader.createCell(4);
            telpCell.setCellValue("Telephone");
            telpCell.setCellStyle(style);

            HSSFCell locationCell = claimHeader.createCell(5);
            locationCell.setCellValue("Location");
            locationCell.setCellStyle(style);
            
            HSSFCell categoryCell = claimHeader.createCell(6);
            categoryCell.setCellValue("Category");
            categoryCell.setCellStyle(style);
            
            HSSFCell descCell = claimHeader.createCell(7);
            descCell.setCellValue("Description");
            descCell.setCellStyle(style);
            
            HSSFCell remarkCell = claimHeader.createCell(8);
            remarkCell.setCellValue("Remark");
            remarkCell.setCellStyle(style);
            
            HSSFCell modifiedByCell = claimHeader.createCell(9);
            modifiedByCell.setCellValue("Modified By");
            modifiedByCell.setCellStyle(style);
            
            HSSFCell modifiedTimeCell = claimHeader.createCell(10);
            modifiedTimeCell.setCellValue("Modified Time");
            modifiedTimeCell.setCellStyle(style);
            
            int i = 0;

            if(logList !=null){
            	if (logListIterator != null){
            		System.out.println("size :  " + logList.size());
//    				while (logListIterator.hasNext()){
            		 for (FirstCall firstCall : logList) {

//    					FirstCall firstCall = logListIterator.next();

                		System.out.println("logListIterator :  " + logListIterator.next());
                		System.out.println("rowNumber :  " + rowNumber);
                		
                		 i += 1; 
                		 rowNumber += 1;
                		
    					HSSFRow claimRow = firstSheet.createRow(rowNumber);

    	                claimRow.createCell(0).setCellValue(i);
    	                claimRow.createCell(1).setCellValue(firstCall.getPriority().getPriorityCode());
    	                claimRow.createCell(2).setCellValue(df.format(firstCall.getCallStartTime()));
    	                claimRow.createCell(3).setCellValue(firstCall.getCallerName());
    	                claimRow.createCell(4).setCellValue(firstCall.getTelephone());
    	                claimRow.createCell(5).setCellValue(firstCall.getLocation());
    	                claimRow.createCell(6).setCellValue(firstCall.getCallCategoryId().getCallCategoryName());
    	                claimRow.createCell(7).setCellValue(firstCall.getDescription());
    	                claimRow.createCell(8).setCellValue(firstCall.getRemarks());
    	                if(firstCall.getModifiedBy() != null){
    	                claimRow.createCell(9).setCellValue(firstCall.getModifiedBy());
    	                }else{
    	                	claimRow.createCell(9).setCellValue(firstCall.getCreatedBy());
    	                }
    	                claimRow.createCell(10).setCellValue(firstCall.getModifiedTime() != null ? df.format(firstCall.getModifiedTime()) : "");
    	                
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
