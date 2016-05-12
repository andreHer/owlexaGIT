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

import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;

public class DiagnosisDownloader {

	public static HSSFWorkbook downloadReport(Client client, MemberGroup memberGroup, Collection<Object[]> diagnosisHealthProblemList, 
			Date startDate, Date endDate, int memberSize) throws Exception {
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

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
            DecimalFormat decFormat = new DecimalFormat("#,##0.000");

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 1;

            HSSFRow header1= firstSheet.createRow(rowNumber++);
			HSSFCell header1Col0 = header1.createCell((short)1);
			header1Col0.setCellValue("Payor");
			HSSFCell header1Col1 = header1.createCell((short)2);
			if(client!=null){
				header1Col1.setCellValue(": "+client.getClientName());
			}else{
				header1Col1.setCellValue(": ");
			}
			
			HSSFRow header2= firstSheet.createRow(rowNumber++);
			HSSFCell header2Col0 = header2.createCell((short)1);
			header2Col0.setCellValue("Corporate");
			HSSFCell header2Col1 = header2.createCell((short)2);
			if(memberGroup != null){
				header2Col1.setCellValue(": "+memberGroup.getGroupName());
			}else{
				header2Col1.setCellValue(":");
			}
			
			HSSFRow header3= firstSheet.createRow(rowNumber++);
			HSSFCell header3Col0 = header3.createCell((short)1);
			header3Col0.setCellValue("Periode");
			HSSFCell header3Col1 = header3.createCell((short)2);
			header3Col1.setCellValue(": "+df.format(startDate)+" s.d "+df.format(endDate));
            
            HSSFRow claimHeader = firstSheet.createRow(rowNumber++);

            HSSFCell noCell = claimHeader.createCell(1);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell claimNumberCell = claimHeader.createCell(2);
            claimNumberCell.setCellValue("Potential Health Problem");
            claimNumberCell.setCellStyle(style);

            HSSFCell admissionCell = claimHeader.createCell(3);
            admissionCell.setCellValue("Total Case");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = claimHeader.createCell(4);
            dischargeCell.setCellValue("Total Claimant");
            dischargeCell.setCellStyle(style);

            HSSFCell memberCell = claimHeader.createCell(5);
            memberCell.setCellValue("% Potential Health Problem");
            memberCell.setCellStyle(style);
            
            Long totalCase = 0L;
            Long totalClaim = 0L;
            Double totalPercentage = 0.0;
            Double percentage = 0.0;
            int i = 1;

            if(diagnosisHealthProblemList !=null){
            	Iterator<Object[]> potentialHealthsIterator = diagnosisHealthProblemList.iterator();
            	if (potentialHealthsIterator != null){
    				while (potentialHealthsIterator.hasNext()){
    					Object[] phealth = potentialHealthsIterator.next();
    				
    					HSSFRow claimRow = firstSheet.createRow(rowNumber++);

    	                claimRow.createCell(1).setCellValue(i++);
    	                claimRow.createCell(2).setCellValue((String)phealth[0]);
    	                claimRow.createCell(3).setCellValue((Long)phealth[1]);
    	                claimRow.createCell(4).setCellValue((Long)phealth[2]);
    	                if(memberSize>0){
    	                	percentage = ((Long)phealth[2]).doubleValue()/(memberSize)*100;
    	                }
    	                claimRow.createCell(5).setCellValue(decFormat.format(percentage));
    				}
            	}
            }
                rowNumber += 1;
              
			
            CellStyle jumlahStyle = workbook.createCellStyle();
            jumlahStyle.setFont(fontHeader);
            jumlahStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            jumlahStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

            // last row (jumlah)
            /*
            HSSFRow jumlahRow = firstSheet.createRow(++rowNumber);

            HSSFCell jumlahCell = jumlahRow.createCell(1);
            jumlahCell.setCellValue("JUMLAH");
            jumlahCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimChargeCell = jumlahRow.createCell(3);
            totalClaimChargeCell.setCellValue(totalCase);
            totalClaimChargeCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimApprovedCell = jumlahRow.createCell(4);
            totalClaimApprovedCell.setCellValue(totalClaim);
            totalClaimApprovedCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimPaidCell = jumlahRow.createCell(5);
            totalClaimPaidCell.setCellValue(totalPercentage);
            totalClaimPaidCell.setCellStyle(jumlahStyle);

	
            jumlahRow.setRowStyle(jumlahStyle);
			*/
            // auto size column
            HSSFRow row = firstSheet.getRow(4);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
