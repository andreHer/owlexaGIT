package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimStatisticDto;
import com.ametis.cms.service.ClaimReportService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;
import com.ametis.cms.web.controller.ClaimReportController;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

public class ReportTop10Generator {

    public static HSSFWorkbook generateReport(Integer clientId,Integer memberGroupId,
    		Date start, Date end, Integer topX,ProductService productService, ClaimReportService claimReportService,String clientName,String groupName) throws Exception {
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

            //creating a custom palette for the workbook
            HSSFPalette palette = workbook.getCustomPalette();
            palette.setColorAtIndex(HSSFColor.LIGHT_BLUE.index, (byte) 165, (byte) 255, (byte) 247);
            palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 33, (byte) 208, (byte) 255);
            palette.setColorAtIndex(HSSFColor.DARK_BLUE.index, (byte) 0, (byte) 159, (byte) 255);
            palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index, (byte) 231, (byte) 240, (byte) 254);

            HSSFCellStyle caseOpenStyle = workbook.createCellStyle();
            caseOpenStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
            caseOpenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle level1Style = workbook.createCellStyle();
            level1Style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            level1Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle level2Style = workbook.createCellStyle();
            level2Style.setFillForegroundColor(HSSFColor.BLUE.index);
            level2Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle level3Style = workbook.createCellStyle();
            level3Style.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
            level3Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle otherStyle = workbook.createCellStyle();
            otherStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            otherStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            // end-custom style

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

            HSSFSheet firstSheet = workbook.createSheet("Claim Amount");
            

            


            int rowNumber = 0;

            HSSFRow companyClaimHeader = firstSheet.createRow(rowNumber);
            HSSFCell companyTitleCell = companyClaimHeader.createCell(1);
            
            companyTitleCell.setCellValue("TOP " + topX + " CLAIM REPORT FOR " + groupName);
            companyTitleCell.setCellStyle(style);
            

            HSSFCellStyle numStyle = workbook.createCellStyle();
            numStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

            
            Collection<CaseCategory> ccList = productService.getGroupProductCaseCategoryList(clientId,memberGroupId);
            
            for (Iterator iterator = ccList.iterator(); iterator.hasNext();) {
				CaseCategory caseCategory = (CaseCategory) iterator.next();

	            rowNumber++;
	            rowNumber++;
	            HSSFRow titleHeader = firstSheet.createRow(rowNumber);
				HSSFCell noTitle = titleHeader.createCell(1);
				noTitle.setCellValue("TOP " + topX + " - " + caseCategory.getCaseCategoryName());
				noTitle.setCellStyle(style);
				
	            rowNumber++;
	            HSSFRow hospitalHeaderRank = firstSheet.createRow(rowNumber);
	            HSSFCell noCell3Rank = hospitalHeaderRank.createCell(1);
	            noCell3Rank.setCellValue("Rank By Claim Amount");
	            rowNumber++;
	
	            HSSFRow claimHeader = firstSheet.createRow(rowNumber);
	
	            HSSFCell noCell = claimHeader.createCell(0);
	            noCell.setCellValue("No.");
	            noCell.setCellStyle(style);
	
	            HSSFCell caseNumberCell = claimHeader.createCell(1);
	            caseNumberCell.setCellValue("Patient Name");
	            caseNumberCell.setCellStyle(style);
	
	            HSSFCell memberNameCell = claimHeader.createCell(2);
	            memberNameCell.setCellValue("Patient Number");
	            memberNameCell.setCellStyle(style);
	
	            HSSFCell clientCell = claimHeader.createCell(3);
	            clientCell.setCellValue("Employee Name");
	            clientCell.setCellStyle(style);
	
	            HSSFCell groupCell = claimHeader.createCell(4);
	            groupCell.setCellValue("Employee Number");
	            groupCell.setCellStyle(style);
	
	            HSSFCell hospitalCell = claimHeader.createCell(5);
	            hospitalCell.setCellValue("Relationship");
	            hospitalCell.setCellStyle(style);
	
	            HSSFCell admissionCell = claimHeader.createCell(6);
	            admissionCell.setCellValue("No. Of Claim");
	            admissionCell.setCellStyle(style);
	
	            HSSFCell dischargeCell = claimHeader.createCell(7);
	            dischargeCell.setCellValue("Charge");
	            dischargeCell.setCellStyle(style);
	
	            HSSFCell typeCell = claimHeader.createCell(8);
	            typeCell.setCellValue("Benefit Paid");
	            typeCell.setCellStyle(style);
	            
	            HSSFCell claimExCell = claimHeader.createCell(9);
	            claimExCell.setCellValue("Claim Excess");
	            claimExCell.setCellStyle(style);
	            
	            HSSFCell avgExcessCell = claimHeader.createCell(10);
	            avgExcessCell.setCellValue("Average Excess");
	            avgExcessCell.setCellStyle(style);

	            int no = 0;
            
            
	            
				if (caseCategory != null){
					Collection<ClaimStatisticDto> topXClaimList = claimReportService.generateTopXClaimantReport(clientId,memberGroupId, 
		            		caseCategory.getCaseCategoryId(), start, end, topX,"sum");
		            
		            for (ClaimStatisticDto statistic : topXClaimList) {
		
		                no += 1;
		                rowNumber += 1;
		                HSSFRow row = firstSheet.createRow(rowNumber);
		                
		                double claimCount = statistic.getClaimCountVal();
		                double claimAmount = statistic.getClaimAmountVal();
		                double claimPaid = statistic.getClaimPaidVal();
		                double claimExcess = statistic.getClaimExcessVal();
		                double claimAvgExcess = statistic.getAvgClaimExcessVal();
		
		                row.createCell(0).setCellValue(no);
		
		                row.createCell(1).setCellValue(statistic.getMemberName());
		                row.createCell(2).setCellValue(statistic.getMemberNumber());
		                row.createCell(3).setCellValue(statistic.getEmployeeName());
		                
		                row.createCell(4).setCellValue(statistic.getEmployeeNumber());                
		                row.createCell(5).setCellValue(statistic.getRelationship());
		                
		                HSSFCell cell6 = row.createCell(6);
		                cell6.setCellValue(claimCount);
		                cell6.setCellStyle(numStyle);		                
		                
		                HSSFCell cell7 = row.createCell(7);
		                cell7.setCellValue(claimAmount);
		                cell7.setCellStyle(numStyle);
		                
		                HSSFCell cell8 = row.createCell(8);
		                cell8.setCellValue(claimPaid);
		                cell8.setCellStyle(numStyle);
		                
		                HSSFCell cell9 = row.createCell(9);
		                cell9.setCellValue(claimExcess);
		                cell9.setCellStyle(numStyle);
		                
		                HSSFCell cell10 = row.createCell(10);
		                cell10.setCellValue(claimAvgExcess);
		                cell10.setCellStyle(numStyle);
		                
		            }
		            
		            rowNumber += 4;
		            
		            HSSFRow subTitleHeader = firstSheet.createRow(rowNumber);
					HSSFCell subnoTitle = subTitleHeader.createCell(1);
					subnoTitle.setCellValue("Rank By Claim Frequency");
					subnoTitle.setCellStyle(style);
					
					rowNumber += 1;
		            
		            HSSFRow subClaimHeader = firstSheet.createRow(rowNumber);
		        	
		            HSSFCell noCellSub = subClaimHeader.createCell(0);
		            noCellSub.setCellValue("No.");
		            noCellSub.setCellStyle(style);
		
		            HSSFCell caseNumberCellSub = subClaimHeader.createCell(1);
		            caseNumberCellSub.setCellValue("Patient Name");
		            caseNumberCellSub.setCellStyle(style);
		
		            HSSFCell memberNameCellSub = subClaimHeader.createCell(2);
		            memberNameCellSub.setCellValue("Patient Number");
		            memberNameCellSub.setCellStyle(style);
		
		            HSSFCell clientCellSub = subClaimHeader.createCell(3);
		            clientCellSub.setCellValue("Employee Name");
		            clientCellSub.setCellStyle(style);
		
		            HSSFCell groupCellSub = subClaimHeader.createCell(4);
		            groupCellSub.setCellValue("Employee Number");
		            groupCellSub.setCellStyle(style);
		
		            HSSFCell hospitalCellSub = subClaimHeader.createCell(5);
		            hospitalCellSub.setCellValue("Relationship");
		            hospitalCellSub.setCellStyle(style);
		
		            HSSFCell admissionCellSub = subClaimHeader.createCell(6);
		            admissionCellSub.setCellValue("No. Of Claim");
		            admissionCellSub.setCellStyle(style);
		
		            HSSFCell dischargeCellSub = subClaimHeader.createCell(7);
		            dischargeCellSub.setCellValue("Charge");
		            dischargeCellSub.setCellStyle(style);
		
		            HSSFCell typeCellSub = subClaimHeader.createCell(8);
		            typeCellSub.setCellValue("Benefit Paid");
		            typeCellSub.setCellStyle(style);
		            
		            HSSFCell typeCellSubEx = subClaimHeader.createCell(9);
		            typeCellSubEx.setCellValue("Claim Excess");
		            typeCellSubEx.setCellStyle(style);
		            
		            HSSFCell typeCellSubAvgEx = subClaimHeader.createCell(10);
		            typeCellSubAvgEx.setCellValue("Average Excess");
		            typeCellSubAvgEx.setCellStyle(style);

		            no = 0;
		            
		            topXClaimList = claimReportService.generateTopXClaimantReport(clientId,memberGroupId, 
		            		caseCategory.getCaseCategoryId(), start, end, topX,"frequency");
		            
		            for (ClaimStatisticDto statistic : topXClaimList) {
		
		                no += 1;
		                rowNumber += 1;
		                HSSFRow row = firstSheet.createRow(rowNumber);
		
		                double claimCount = statistic.getClaimCountVal();
		                double claimAmount = statistic.getClaimAmountVal();
		                double claimPaid = statistic.getClaimPaidVal();
		                double claimExcess = statistic.getClaimExcessVal();
		                double claimAvgExcess = statistic.getAvgClaimExcessVal();
		
		                
		                row.createCell(0).setCellValue(no);
		
		                row.createCell(1).setCellValue(statistic.getMemberName());
		                row.createCell(2).setCellValue(statistic.getMemberNumber());
		                row.createCell(3).setCellValue(statistic.getEmployeeName());
		                row.createCell(4).setCellValue(statistic.getEmployeeNumber());                
		                row.createCell(5).setCellValue(statistic.getRelationship());
		                		                
		                HSSFCell cell6 = row.createCell(6);
		                cell6.setCellValue(claimCount);
		                cell6.setCellStyle(numStyle);		                
		                
		                HSSFCell cell7 = row.createCell(7);
		                cell7.setCellValue(claimAmount);
		                cell7.setCellStyle(numStyle);
		                
		                HSSFCell cell8 = row.createCell(8);
		                cell8.setCellValue(claimPaid);
		                cell8.setCellStyle(numStyle);
		                
		                HSSFCell cell9 = row.createCell(9);
		                cell9.setCellValue(claimExcess);
		                cell9.setCellStyle(numStyle);
		                
		                HSSFCell cell10 = row.createCell(10);
		                cell10.setCellValue(claimAvgExcess);
		                cell10.setCellStyle(numStyle);
		                
		            }
		            
		            rowNumber += 3;
		            
		            no = 0;

				}
            }

            // auto size column
            HSSFRow row = firstSheet.getRow(3);
            if (row != null){
	            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
	                firstSheet.autoSizeColumn(colNum);
	            }
            }
            
            //
            

            rowNumber = 0;
            
            HSSFSheet secondSheet = workbook.createSheet("Diagnosis");
            
            HSSFRow companyClaimDiagHeader = secondSheet.createRow(rowNumber);
            HSSFCell companyTitleDiagCell = companyClaimDiagHeader.createCell(1);
            
            rowNumber ++;
            rowNumber ++;
            
            companyTitleDiagCell.setCellValue("TOP " + topX + " DIAGNOSIS REPORT FOR " + groupName);
            companyTitleDiagCell.setCellStyle(style);
            
            ccList = productService.getGroupProductCaseCategoryList(clientId,memberGroupId);
            
            for (Iterator iterator = ccList.iterator(); iterator.hasNext();) {
				CaseCategory caseCategory = (CaseCategory) iterator.next();

				HSSFRow titleHeader = secondSheet.createRow(rowNumber);
				HSSFCell noTitle = titleHeader.createCell(1);
				noTitle.setCellValue("TOP " + topX + " - " + caseCategory.getCaseCategoryName());
				noTitle.setCellStyle(style);
				
				rowNumber++;
				HSSFRow hospitalHeaderRank = secondSheet.createRow(rowNumber);
	            HSSFCell noCell3Rank = hospitalHeaderRank.createCell(1);
	            noCell3Rank.setCellValue("Rank By Claim Amount");
	            rowNumber++;
				
	            HSSFRow diagHeader = secondSheet.createRow(rowNumber);
	
	            HSSFCell noCell2 = diagHeader.createCell(0);
	            noCell2.setCellValue("No.");
	            noCell2.setCellStyle(style);
	
	            HSSFCell caseNumberCell2 = diagHeader.createCell(1);
	            caseNumberCell2.setCellValue("Diagnosis Name");
	            caseNumberCell2.setCellStyle(style);
	
	            HSSFCell memberNameCell2 = diagHeader.createCell(2);
	            memberNameCell2.setCellValue("No of Patients");
	            memberNameCell2.setCellStyle(style);
	
	            HSSFCell clientCell2 = diagHeader.createCell(3);
	            clientCell2.setCellValue("No of Claims");
	            clientCell2.setCellStyle(style);
	
	            HSSFCell groupCell2 = diagHeader.createCell(4);
	            groupCell2.setCellValue("Charge");
	            groupCell2.setCellStyle(style);
	
	            HSSFCell hospitalCell2 = diagHeader.createCell(5);
	            hospitalCell2.setCellValue("Benefit Paid");
	            hospitalCell2.setCellStyle(style);
	
	            HSSFCell admissionCell2 = diagHeader.createCell(6);
	            admissionCell2.setCellValue("Avg Cost / Diagnosis");
	            admissionCell2.setCellStyle(style);
	            
	            HSSFCell hospitalCell2Ex = diagHeader.createCell(7);
	            hospitalCell2Ex.setCellValue("Claim Excess");
	            hospitalCell2Ex.setCellStyle(style);
	
	            HSSFCell admissionCell2Avg = diagHeader.createCell(8);
	            admissionCell2Avg.setCellValue("Avg Excess");
	            admissionCell2Avg.setCellStyle(style);


				if (caseCategory != null){
					Collection<ClaimStatisticDto> topXClaimList = claimReportService.generateTopXDiagnosisReport(clientId,memberGroupId, 
		            		caseCategory.getCaseCategoryId(), start, end, topX, "sum");

					
		            int no = 0;
		            
		            for (ClaimStatisticDto statistic : topXClaimList) {
		
		                no += 1;
		                rowNumber += 1;
		                
		                HSSFRow diagContentRow = secondSheet.createRow(rowNumber);
		
		                diagContentRow.createCell(0).setCellValue(no);		
		                diagContentRow.createCell(1).setCellValue(statistic.getDiagnosisName());
		                
		                HSSFCell cell2 = diagContentRow.createCell(2);
		                cell2.setCellValue(statistic.getParticipantCountVal());
		                cell2.setCellStyle(numStyle);
		                
		                
		                HSSFCell cell3 = diagContentRow.createCell(3);
		                cell3.setCellValue(statistic.getClaimCountVal());
		                cell3.setCellStyle(numStyle);
		                
		                HSSFCell cell4 = diagContentRow.createCell(4);
		                cell4.setCellValue(statistic.getClaimAmountVal());
		                cell4.setCellStyle(numStyle);
		                
		                HSSFCell cell5 = diagContentRow.createCell(5);
		                cell5.setCellValue(statistic.getClaimPaidVal());
		                cell5.setCellStyle(numStyle);
		                
		                HSSFCell cell6 = diagContentRow.createCell(6);
		                cell6.setCellValue(statistic.getClaimAverageVal());
		                cell6.setCellStyle(numStyle);
		                
		                HSSFCell cell7 = diagContentRow.createCell(7);
		                cell7.setCellValue(statistic.getClaimExcessVal());
		                cell7.setCellStyle(numStyle);
		                
		                HSSFCell cell8 = diagContentRow.createCell(8);
		                cell8.setCellValue(statistic.getAvgClaimExcessVal());
		                cell8.setCellStyle(numStyle);
		                
		                
		                
		            }
		            rowNumber += 3;
		            
		            no = 0;
		            
					HSSFRow subtitleHeader = secondSheet.createRow(rowNumber);
					HSSFCell subnoTitle = subtitleHeader.createCell(1);
					subnoTitle.setCellValue("Rank By Claim Frequency");
					subnoTitle.setCellStyle(style);
					
					rowNumber++;
					
		            HSSFRow diagHeaderSub = secondSheet.createRow(rowNumber);
		
		            HSSFCell noCell2Sub = diagHeaderSub.createCell(0);
		            noCell2Sub.setCellValue("No.");
		            noCell2Sub.setCellStyle(style);
		
		            HSSFCell caseNumberCell2Sub = diagHeaderSub.createCell(1);
		            caseNumberCell2Sub.setCellValue("Diagnosis Name");
		            caseNumberCell2Sub.setCellStyle(style);
		
		            HSSFCell memberNameCell2Sub = diagHeaderSub.createCell(2);
		            memberNameCell2Sub.setCellValue("No of Patients");
		            memberNameCell2Sub.setCellStyle(style);
		
		            HSSFCell clientCell2Sub = diagHeaderSub.createCell(3);
		            clientCell2Sub.setCellValue("No of Claims");
		            clientCell2Sub.setCellStyle(style);
		
		            HSSFCell groupCell2Sub = diagHeaderSub.createCell(4);
		            groupCell2Sub.setCellValue("Charge");
		            groupCell2Sub.setCellStyle(style);
		
		            HSSFCell hospitalCell2Sub = diagHeaderSub.createCell(5);
		            hospitalCell2Sub.setCellValue("Benefit Paid");
		            hospitalCell2Sub.setCellStyle(style);
		
		            HSSFCell admissionCell2Sub = diagHeaderSub.createCell(6);
		            admissionCell2Sub.setCellValue("Avg Cost / Diagnosis");
		            admissionCell2Sub.setCellStyle(style);
		            
		            HSSFCell hospitalCell2SubEx = diagHeaderSub.createCell(7);
		            hospitalCell2SubEx.setCellValue("Claim Excess");
		            hospitalCell2SubEx.setCellStyle(style);
		
		            HSSFCell admissionCell2SubAvgEx = diagHeaderSub.createCell(8);
		            admissionCell2SubAvgEx.setCellValue("Avg Excess");
		            admissionCell2SubAvgEx.setCellStyle(style);


		            topXClaimList = claimReportService.generateTopXDiagnosisReport(clientId,memberGroupId, 
		            		caseCategory.getCaseCategoryId(), start, end, topX, "frequency");

		            
		            
		            for (ClaimStatisticDto statistic : topXClaimList) {
		
		                no += 1;
		                rowNumber += 1;
		                
		                HSSFRow diagContentRow = secondSheet.createRow(rowNumber);
		
		                diagContentRow.createCell(0).setCellValue(no);		
		                diagContentRow.createCell(1).setCellValue(statistic.getDiagnosisName());
		                
		                
		                HSSFCell cell2 = diagContentRow.createCell(2);
		                cell2.setCellValue(statistic.getParticipantCountVal());
		                cell2.setCellStyle(numStyle);
		                
		                
		                HSSFCell cell3 = diagContentRow.createCell(3);
		                cell3.setCellValue(statistic.getClaimCountVal());
		                cell3.setCellStyle(numStyle);
		                
		                HSSFCell cell4 = diagContentRow.createCell(4);
		                cell4.setCellValue(statistic.getClaimAmountVal());
		                cell4.setCellStyle(numStyle);
		                
		                HSSFCell cell5 = diagContentRow.createCell(5);
		                cell5.setCellValue(statistic.getClaimPaidVal());
		                cell5.setCellStyle(numStyle);
		                
		                HSSFCell cell6 = diagContentRow.createCell(6);
		                cell6.setCellValue(statistic.getClaimAverageVal());
		                cell6.setCellStyle(numStyle);
		                
		                HSSFCell cell7 = diagContentRow.createCell(7);
		                cell7.setCellValue(statistic.getClaimExcessVal());
		                cell7.setCellStyle(numStyle);
		                
		                HSSFCell cell8 = diagContentRow.createCell(8);
		                cell8.setCellValue(statistic.getAvgClaimExcessVal());
		                cell8.setCellStyle(numStyle);
		                
		            }
		            rowNumber += 3;
		            
		            no = 0;
				}

            
            }
            

            HSSFRow rowDiag = secondSheet.getRow(3);
            if (rowDiag != null){
	            for (int colNum = 0; colNum < rowDiag.getLastCellNum(); colNum++) {
	                secondSheet.autoSizeColumn(colNum);
	            }
            }
            
            rowNumber = 0;
            
            HSSFSheet providerSheet = workbook.createSheet("Provider");
            
            HSSFRow companyClaimProvHeader = providerSheet.createRow(rowNumber);
            HSSFCell companyTitleProvCell = companyClaimProvHeader.createCell(1);
            
            
            
            companyTitleProvCell.setCellValue("TOP " + topX + " PROVIDER REPORT FOR " + groupName);
            companyTitleProvCell.setCellStyle(style);
            
            rowNumber ++;
            rowNumber ++;
            
            
            ccList = productService.getGroupProductCaseCategoryList(clientId,memberGroupId);
            for (Iterator iterator = ccList.iterator(); iterator.hasNext();) {
            	
				CaseCategory caseCategory = (CaseCategory) iterator.next();
				
			
	            HSSFRow titleHeader = providerSheet.createRow(rowNumber);
				HSSFCell noTitle = titleHeader.createCell(1);
				noTitle.setCellValue("TOP " + topX + " - " + caseCategory.getCaseCategoryName());
				noTitle.setCellStyle(style);
				
	            rowNumber++;
	            HSSFRow hospitalHeaderRank = providerSheet.createRow(rowNumber);
	            HSSFCell noCell3Rank = hospitalHeaderRank.createCell(1);
	            noCell3Rank.setCellValue("Rank By Claim Amount");
	            
	
	            rowNumber++;
				
	            HSSFRow hospitalHeader = providerSheet.createRow(rowNumber);
	
	            HSSFCell noCell3 = hospitalHeader.createCell(0);
	            noCell3.setCellValue("No.");
	            noCell3.setCellStyle(style);
	
	            HSSFCell caseNumberCell3 = hospitalHeader.createCell(1);
	            caseNumberCell3.setCellValue("Provider Name");
	            caseNumberCell3.setCellStyle(style);
	
	            HSSFCell memberNameCell3 = hospitalHeader.createCell(2);
	            memberNameCell3.setCellValue("No of Claims");
	            memberNameCell3.setCellStyle(style);
	
	            HSSFCell clientCell3 = hospitalHeader.createCell(3);
	            clientCell3.setCellValue("Charge");
	            clientCell3.setCellStyle(style);
	
	            HSSFCell groupCell3 = hospitalHeader.createCell(4);
	            groupCell3.setCellValue("Benefit Paid");
	            groupCell3.setCellStyle(style);
	
	            HSSFCell hospitalCell3 = hospitalHeader.createCell(5);
	            hospitalCell3.setCellValue("Average Claim");
	            hospitalCell3.setCellStyle(style);
	            
	            HSSFCell groupCell3Ex = hospitalHeader.createCell(6);
	            groupCell3Ex.setCellValue("Claim Excess");
	            groupCell3Ex.setCellStyle(style);
	
	            HSSFCell hospitalCell3AvgEx = hospitalHeader.createCell(7);
	            hospitalCell3AvgEx.setCellValue("Average Excess");
	            hospitalCell3AvgEx.setCellStyle(style);

	            if (caseCategory != null){
		             Collection<ClaimStatisticDto> topXClaimList = claimReportService.generateTopXProviderReport(clientId,memberGroupId, 
		            		caseCategory.getCaseCategoryId(), start, end, topX, "sum");
		            int no = 0;
		            
		            for (ClaimStatisticDto statistic : topXClaimList) {		
		                no += 1;
		                rowNumber += 1;
		                
		                HSSFRow provContentRow = providerSheet.createRow(rowNumber);
		
		                provContentRow.createCell(0).setCellValue(no);		
		                provContentRow.createCell(1).setCellValue(statistic.getProviderName());
		                
		                HSSFCell cell2 = provContentRow.createCell(2); 
		                cell2.setCellValue(statistic.getClaimCountVal());
		                cell2.setCellStyle(numStyle);
		                
		                HSSFCell cell3 = provContentRow.createCell(3); 
		                cell3.setCellValue(statistic.getClaimAmountVal());
		                cell3.setCellStyle(numStyle);
		                
		                HSSFCell cell4 = provContentRow.createCell(4); 
		                cell4.setCellValue(statistic.getClaimPaidVal());
		                cell4.setCellStyle(numStyle);
		                
		                HSSFCell cell5 = provContentRow.createCell(5); 
		                cell5.setCellValue(statistic.getClaimAverageVal());
		                cell5.setCellStyle(numStyle);
		                
		                HSSFCell cell6 = provContentRow.createCell(6); 
		                cell6.setCellValue(statistic.getClaimExcessVal());
		                cell6.setCellStyle(numStyle);
		                
		                HSSFCell cell7 = provContentRow.createCell(7); 
		                cell7.setCellValue(statistic.getAvgClaimExcessVal());
		                cell7.setCellStyle(numStyle);
		                
		                
		            }		       
		            rowNumber += 3;
		            no = 0;
		            
		            HSSFRow titleHeaderSub = providerSheet.createRow(rowNumber);
					HSSFCell subnoTitle = titleHeaderSub.createCell(1);
					subnoTitle.setCellValue("Rank By Claim Frequency");
					subnoTitle.setCellStyle(style);
					
		            rowNumber++;
					
		            HSSFRow hospitalHeaderSub = providerSheet.createRow(rowNumber);
		
		            HSSFCell noCell3Sub = hospitalHeaderSub.createCell(0);
		            noCell3Sub.setCellValue("No.");
		            noCell3Sub.setCellStyle(style);
		
		            HSSFCell caseNumberCell3Sub = hospitalHeaderSub.createCell(1);
		            caseNumberCell3Sub.setCellValue("Provider Name");
		            caseNumberCell3Sub.setCellStyle(style);
		
		            HSSFCell memberNameCell3Sub = hospitalHeaderSub.createCell(2);
		            memberNameCell3Sub.setCellValue("No of Claims");
		            memberNameCell3Sub.setCellStyle(style);
		
		            HSSFCell clientCell3Sub = hospitalHeaderSub.createCell(3);
		            clientCell3Sub.setCellValue("Charge");
		            clientCell3Sub.setCellStyle(style);
		
		            HSSFCell groupCell3Sub = hospitalHeaderSub.createCell(4);
		            groupCell3Sub.setCellValue("Benefit Paid");
		            groupCell3Sub.setCellStyle(style);
		
		            HSSFCell hospitalCell3Sub = hospitalHeaderSub.createCell(5);
		            hospitalCell3Sub.setCellValue("Average Claim");
		            hospitalCell3Sub.setCellStyle(style);
		            
		            HSSFCell groupCell3SubEx = hospitalHeaderSub.createCell(6);
		            groupCell3SubEx.setCellValue("Claim Excess ");
		            groupCell3SubEx.setCellStyle(style);
		
		            HSSFCell hospitalCell3SubAvgEx = hospitalHeaderSub.createCell(7);
		            hospitalCell3SubAvgEx.setCellValue("Average Excess");
		            hospitalCell3SubAvgEx.setCellStyle(style);

		            
		            topXClaimList = claimReportService.generateTopXProviderReport(clientId,memberGroupId, 
		            		caseCategory.getCaseCategoryId(), start, end, topX, "frequency");
		            
		            
		            for (ClaimStatisticDto statistic : topXClaimList) {		
		                no += 1;
		                rowNumber += 1;
		                
		                HSSFRow provContentRow = providerSheet.createRow(rowNumber);
		
		                provContentRow.createCell(0).setCellValue(no);		
		                provContentRow.createCell(1).setCellValue(statistic.getProviderName());
		                
		                HSSFCell cell2 = provContentRow.createCell(2); 
		                cell2.setCellValue(statistic.getClaimCountVal());
		                cell2.setCellStyle(numStyle);
		                
		                HSSFCell cell3 = provContentRow.createCell(3); 
		                cell3.setCellValue(statistic.getClaimAmountVal());
		                cell3.setCellStyle(numStyle);
		                
		                HSSFCell cell4 = provContentRow.createCell(4); 
		                cell4.setCellValue(statistic.getClaimPaidVal());
		                cell4.setCellStyle(numStyle);
		                
		                HSSFCell cell5 = provContentRow.createCell(5); 
		                cell5.setCellValue(statistic.getClaimAverageVal());
		                cell5.setCellStyle(numStyle);
		                
		                HSSFCell cell6 = provContentRow.createCell(6); 
		                cell6.setCellValue(statistic.getClaimExcessVal());
		                cell6.setCellStyle(numStyle);
		                
		                HSSFCell cell7 = provContentRow.createCell(7); 
		                cell7.setCellValue(statistic.getAvgClaimExcessVal());
		                cell7.setCellStyle(numStyle);
		                
		            }		       
		            rowNumber += 3;
		            no = 0;
		            
				}
            }
            
            HSSFRow rowHospital = providerSheet.getRow(3);
            if (rowHospital != null){
	            for (int colNum = 0; colNum < rowHospital.getLastCellNum(); colNum++) {
	            	providerSheet.autoSizeColumn(colNum);
	            }
            }
            rowNumber = 0;            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
