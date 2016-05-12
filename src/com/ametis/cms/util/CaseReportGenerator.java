package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.CaseHistoryService;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

public class CaseReportGenerator {

    public static HSSFWorkbook generateReport(Collection<Case> caseList) throws Exception {
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

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 0;

            HSSFRow openRow = firstSheet.createRow(rowNumber++);
            HSSFCell openCell = openRow.createCell(1);
            openCell.setCellValue("Case Open [IP & MA] - Pasien Baru");
            openCell.setCellStyle(caseOpenStyle);

            HSSFRow level1Row = firstSheet.createRow(rowNumber++);
            HSSFCell level1Cell = level1Row.createCell(1);
            level1Cell.setCellValue("Case [IP & MA] - Perawatan 2-5 Hari");
            level1Cell.setCellStyle(level1Style);

            HSSFRow level2Row = firstSheet.createRow(rowNumber++);
            HSSFCell level2Cell = level2Row.createCell(1);
            level2Cell.setCellValue("Case [IP & MA] - Perawatan 6-8 Hari");
            level2Cell.setCellStyle(level2Style);

            HSSFRow level3Row = firstSheet.createRow(rowNumber++);
            HSSFCell level3Cell = level3Row.createCell(1);
            level3Cell.setCellValue("Case [IP & MA] - Perawatan > 8 Hari");
            level3Cell.setCellStyle(level3Style);

            HSSFRow otherRow = firstSheet.createRow(rowNumber++);
            HSSFCell otherCell = otherRow.createCell(1);
            otherCell.setCellValue("Case Bukan [IP & MA] ");
            otherCell.setCellStyle(otherStyle);

            rowNumber++;

            HSSFRow case1Header = firstSheet.createRow(rowNumber);

            HSSFCell noCell = case1Header.createCell(0);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell caseNumberCell = case1Header.createCell(1);
            caseNumberCell.setCellValue("Case Number");
            caseNumberCell.setCellStyle(style);

            HSSFCell memberNameCell = case1Header.createCell(2);
            memberNameCell.setCellValue("Member Name");
            memberNameCell.setCellStyle(style);

            HSSFCell clientCell = case1Header.createCell(3);
            clientCell.setCellValue("Client");
            clientCell.setCellStyle(style);

            HSSFCell groupCell = case1Header.createCell(4);
            groupCell.setCellValue("Group");
            groupCell.setCellStyle(style);

            HSSFCell hospitalCell = case1Header.createCell(5);
            hospitalCell.setCellValue("Hospital");
            hospitalCell.setCellStyle(style);

            HSSFCell admissionCell = case1Header.createCell(6);
            admissionCell.setCellValue("Admission");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = case1Header.createCell(7);
            dischargeCell.setCellValue("Discharge");
            dischargeCell.setCellStyle(style);

            HSSFCell typeCell = case1Header.createCell(8);
            typeCell.setCellValue("Type");
            typeCell.setCellStyle(style);

            HSSFCell limitBenefitCell = case1Header.createCell(9);
            limitBenefitCell.setCellValue("Limit Benefit");
            limitBenefitCell.setCellStyle(style);

            HSSFCell diagnosisCell = case1Header.createCell(10);
            diagnosisCell.setCellValue("Diagnosis");
            diagnosisCell.setCellStyle(style);

            HSSFCell chargeCell = case1Header.createCell(11);
            chargeCell.setCellValue("Charge");
            chargeCell.setCellStyle(style);

            HSSFCell approvedCell = case1Header.createCell(12);
            approvedCell.setCellValue("Approved");
            approvedCell.setCellStyle(style);

            HSSFCell excessCell = case1Header.createCell(13);
            excessCell.setCellValue("Excess");
            excessCell.setCellStyle(style);

            HSSFCell statusCell = case1Header.createCell(14);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell officerCell = case1Header.createCell(15);
            officerCell.setCellValue("Officer");
            officerCell.setCellStyle(style);

            HSSFCell createdTimeCell = case1Header.createCell(16);
            createdTimeCell.setCellValue("Created Time");
            createdTimeCell.setCellStyle(style);

            HSSFCell modifiedTimeCell = case1Header.createCell(17);
            modifiedTimeCell.setCellValue("Modified Time");
            modifiedTimeCell.setCellStyle(style);

            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            long currentTime = Calendar.getInstance().getTimeInMillis() / (1000 * 60 * 60 * 24);

            int no = 0;
            for (Case myCase : caseList) {

                no += 1;
                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(no);

                row.createCell(1).setCellValue(myCase.getCaseNumber());

                Member member = myCase.getMemberId();
                String currentCardNumber = "";
                if (member != null) {
                    Integer isVIP = member.getIsVIP();
                    if (isVIP != null) {
                        currentCardNumber = (isVIP.intValue() == 1 ? "[VIP]" : member.getCurrentCardNumber());
                    }

                    String lastName = (member.getLastName() == null ? "" : member.getLastName());
                    row.createCell(2).setCellValue(member.getFirstName() + " " + lastName + " (" + currentCardNumber + ")");
                }

                row.createCell(3).setCellValue(member == null ? "" : member.getClientName());
                row.createCell(4).setCellValue(member == null ? "" : member.getGroupName());

                Provider provider = myCase.getProviderId();
                row.createCell(5).setCellValue(provider == null ? "" : provider.getProviderName());

                row.createCell(6).setCellValue(myCase.getCaseStartTime() == null ? "" : myCase.getCaseStartTime().toString());
                row.createCell(7).setCellValue(myCase.getCaseEndTime() == null ? "" : myCase.getCaseEndTime().toString());

                CaseCategory caseCategory = myCase.getCaseCategoryId();
                row.createCell(8).setCellValue(caseCategory == null ? "" : caseCategory.getCaseCategoryCode());

                row.createCell(9).setCellValue(myCase.getPreRemainingLimit() == null ? 0.0 : myCase.getPreRemainingLimit());

                Diagnosis diagnosis1 = myCase.getDiagnosis1Id();
                row.createCell(10).setCellValue(diagnosis1 == null ? "" : diagnosis1.getDiagnosisCode());

                row.createCell(11).setCellValue(myCase.getCaseClaimValue() == null ? 0.0 : myCase.getCaseClaimValue());
                row.createCell(12).setCellValue(myCase.getCaseApprovedValue() == null ? 0.0 : myCase.getCaseApprovedValue());
                row.createCell(13).setCellValue(myCase.getCaseExcessValue() == null ? 0.0 : myCase.getCaseExcessValue());

                String status = "";
                CaseStatus caseStatus = myCase.getCaseStatusId();
                if (caseStatus != null) {
                    if (Integer.valueOf(1).equals(caseStatus.getCaseStatusId())) {
                        status = "OPEN";
                    }
                    if (Integer.valueOf(2).equals(caseStatus.getCaseStatusId())) {
                        status = "PENDING DOCUMENT";
                    }
                    if (Integer.valueOf(3).equals(caseStatus.getCaseStatusId())) {
                        status = "VERIFIED";
                    }
                    if (Integer.valueOf(4).equals(caseStatus.getCaseStatusId())) {
                        status = "REJECTED";
                    }
                    if (Integer.valueOf(5).equals(caseStatus.getCaseStatusId())) {
                        status = "CLOSED";
                    }
                    if (Integer.valueOf(6).equals(caseStatus.getCaseStatusId())) {
                        status = "PAID";
                    }
                    if (Integer.valueOf(7).equals(caseStatus.getCaseStatusId())) {
                        status = "PENDING INVESTIGATION";
                    }
                    if (Integer.valueOf(9).equals(caseStatus.getCaseStatusId())) {
                        status = "APPROVED";
                    }
                    if (Integer.valueOf(10).equals(caseStatus.getCaseStatusId())) {
                        status = "PENDING";
                    }
                    if (Integer.valueOf(12).equals(caseStatus.getCaseStatusId())) {
                        status = "COMPLETE";
                    }
                    if (Integer.valueOf(15).equals(caseStatus.getCaseStatusId())) {
                        status = "FINALIZED";
                    }
                    if (Integer.valueOf(17).equals(caseStatus.getCaseStatusId())) {
                        status = "REFERED";
                    }
                    if (Integer.valueOf(18).equals(caseStatus.getCaseStatusId())) {
                        status = "GREY AREA";
                    }
                }
                row.createCell(14).setCellValue(status);

                row.createCell(15).setCellValue(myCase.getModifiedBy()); // modified by

                row.createCell(16).setCellValue(myCase.getCreatedTime() == null ? "" : myCase.getCreatedTime().toString()); // created time

                row.createCell(17).setCellValue(myCase.getModifiedTime() == null ? "" : myCase.getModifiedTime().toString()); // modified time

                long startTime = myCase.getCaseStartTime().getTime() / (1000 * 60 * 60 * 24);

                long interval = currentTime - startTime;

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(caseStatus != null && !Integer.valueOf(4).equals(caseStatus.getCaseStatusId())
                            && !Integer.valueOf(15).equals(caseStatus.getCaseStatusId())) {

                        if (interval == 1 && caseStatus != null && Integer.valueOf(1).equals(caseStatus.getCaseStatusId())) {
                            cell.setCellStyle(caseOpenStyle);
                        } else if (interval >= 2 && interval <= 5) {
                            cell.setCellStyle(level1Style);
                        } else if (interval > 5 && interval <= 8) {
                            cell.setCellStyle(level2Style);
                        } else if (interval > 8) {
                            cell.setCellStyle(level3Style);
                        } else {
                            cell.setCellStyle(otherStyle);
                        }
                    } else {
                            cell.setCellStyle(otherStyle);
                    }
                }
            }

            // auto size column
            HSSFRow row = firstSheet.getRow(rowNumber);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
    
    public static HSSFWorkbook generateReportCaseProvider(Collection<Case> caseList) throws Exception {
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


            // end-custom style

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 0;

            rowNumber++;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(0);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell caseNumberCell = claimHeader.createCell(1);
            caseNumberCell.setCellValue("Case Number");
            caseNumberCell.setCellStyle(style);

            HSSFCell memberNameCell = claimHeader.createCell(2);
            memberNameCell.setCellValue("Member Name");
            memberNameCell.setCellStyle(style);

            HSSFCell clientCell = claimHeader.createCell(3);
            clientCell.setCellValue("Client");
            clientCell.setCellStyle(style);

            HSSFCell groupCell = claimHeader.createCell(4);
            groupCell.setCellValue("Group");
            groupCell.setCellStyle(style);

            HSSFCell hospitalCell = claimHeader.createCell(5);
            hospitalCell.setCellValue("Hospital");
            hospitalCell.setCellStyle(style);

            HSSFCell admissionCell = claimHeader.createCell(6);
            admissionCell.setCellValue("Admission");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = claimHeader.createCell(7);
            dischargeCell.setCellValue("Discharge");
            dischargeCell.setCellStyle(style);

            HSSFCell typeCell = claimHeader.createCell(8);
            typeCell.setCellValue("Type");
            typeCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(14);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell officerCell = claimHeader.createCell(15);
            officerCell.setCellValue("Officer");
            officerCell.setCellStyle(style);

            HSSFCell createdTimeCell = claimHeader.createCell(16);
            createdTimeCell.setCellValue("Created Time");
            createdTimeCell.setCellStyle(style);

            HSSFCell modifiedTimeCell = claimHeader.createCell(17);
            modifiedTimeCell.setCellValue("Modified Time");
            modifiedTimeCell.setCellStyle(style);

            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            long currentTime = Calendar.getInstance().getTimeInMillis() / (1000 * 60 * 60 * 24);

            int no = 0;
            for (Case myCase : caseList) {

                no += 1;
                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(no);

                row.createCell(1).setCellValue(myCase.getCaseNumber());

                Member member = myCase.getMemberId();
                String currentCardNumber = "";
                if (member != null) {
                    Integer isVIP = member.getIsVIP();
                    if (isVIP != null) {
                        currentCardNumber = (isVIP.intValue() == 1 ? "[VIP]" : member.getCurrentCardNumber());
                    }

                    String lastName = (member.getLastName() == null ? "" : member.getLastName());
                    row.createCell(2).setCellValue(member.getFirstName() + " " + lastName + " (" + currentCardNumber + ")");
                }

                row.createCell(3).setCellValue(member == null ? "" : member.getClientName());
                row.createCell(4).setCellValue(member == null ? "" : member.getGroupName());

                Provider provider = myCase.getProviderId();
                row.createCell(5).setCellValue(provider == null ? "" : provider.getProviderName());

                row.createCell(6).setCellValue(myCase.getCaseStartTime() == null ? "" : myCase.getCaseStartTime().toString());
                row.createCell(7).setCellValue(myCase.getCaseEndTime() == null ? "" : myCase.getCaseEndTime().toString());

                CaseCategory caseCategory = myCase.getCaseCategoryId();
                row.createCell(8).setCellValue(caseCategory == null ? "" : caseCategory.getCaseCategoryCode());

                String status = "";
                CaseStatus caseStatus = myCase.getCaseStatusId();
                if (caseStatus != null) {
                    if (Integer.valueOf(1).equals(caseStatus.getCaseStatusId())) {
                        status = "OPEN";
                    }
                    if (Integer.valueOf(2).equals(caseStatus.getCaseStatusId())) {
                        status = "PENDING DOCUMENT";
                    }
                    if (Integer.valueOf(3).equals(caseStatus.getCaseStatusId())) {
                        status = "VERIFIED";
                    }
                    if (Integer.valueOf(4).equals(caseStatus.getCaseStatusId())) {
                        status = "REJECTED";
                    }
                    if (Integer.valueOf(5).equals(caseStatus.getCaseStatusId())) {
                        status = "CLOSED";
                    }
                    if (Integer.valueOf(6).equals(caseStatus.getCaseStatusId())) {
                        status = "PAID";
                    }
                    if (Integer.valueOf(7).equals(caseStatus.getCaseStatusId())) {
                        status = "PENDING INVESTIGATION";
                    }
                    if (Integer.valueOf(9).equals(caseStatus.getCaseStatusId())) {
                        status = "APPROVED";
                    }
                    if (Integer.valueOf(10).equals(caseStatus.getCaseStatusId())) {
                        status = "PENDING";
                    }
                    if (Integer.valueOf(12).equals(caseStatus.getCaseStatusId())) {
                        status = "COMPLETE";
                    }
                    if (Integer.valueOf(15).equals(caseStatus.getCaseStatusId())) {
                        status = "FINALIZED";
                    }
                    if (Integer.valueOf(17).equals(caseStatus.getCaseStatusId())) {
                        status = "REFERED";
                    }
                    if (Integer.valueOf(18).equals(caseStatus.getCaseStatusId())) {
                        status = "GREY AREA";
                    }
                }
                row.createCell(14).setCellValue(status);

                row.createCell(15).setCellValue(myCase.getModifiedBy()); // modified by

                row.createCell(16).setCellValue(myCase.getCreatedTime() == null ? "" : myCase.getCreatedTime().toString()); // created time

                row.createCell(17).setCellValue(myCase.getModifiedTime() == null ? "" : myCase.getModifiedTime().toString()); // modified time

            }

            // auto size column
            HSSFRow row = firstSheet.getRow(rowNumber);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
    
    public static HSSFWorkbook generateReportCaseGLApproved(Collection<Case> caseList, CaseHistoryService caseHistoryService) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {
        	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
        	SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
        	
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


            // end-custom style

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 0;
            int colNumber = 0;
            int col2Number = 0;
            int col3Number = 0;

            rowNumber++;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(colNumber++);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell caseNumberCell = claimHeader.createCell(colNumber++);
            caseNumberCell.setCellValue("Case Number");
            caseNumberCell.setCellStyle(style);

            HSSFCell memberNameCell = claimHeader.createCell(colNumber++);
            memberNameCell.setCellValue("Member Name");
            memberNameCell.setCellStyle(style);
            
            HSSFCell cardNumberCell = claimHeader.createCell(colNumber++);
            cardNumberCell.setCellValue("Card Number");
            cardNumberCell.setCellStyle(style);

            HSSFCell clientCell = claimHeader.createCell(colNumber++);
            clientCell.setCellValue("Client");
            clientCell.setCellStyle(style);

            HSSFCell groupCell = claimHeader.createCell(colNumber++);
            groupCell.setCellValue("Group");
            groupCell.setCellStyle(style);

            HSSFCell hospitalCell = claimHeader.createCell(colNumber++);
            hospitalCell.setCellValue("Hospital");
            hospitalCell.setCellStyle(style);
            
            HSSFCell typeCell = claimHeader.createCell(colNumber++);
            typeCell.setCellValue("Type");
            typeCell.setCellStyle(style);
            
            HSSFCell diagnosisCell = claimHeader.createCell(colNumber++);
            diagnosisCell.setCellValue("Diagnosis");
            diagnosisCell.setCellStyle(style);
            
            firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, colNumber, colNumber+7));

            HSSFCell openCell = claimHeader.createCell(colNumber);
            openCell.setCellValue("OPEN");
            openCell.setCellStyle(style);
            col2Number = colNumber;
            colNumber = colNumber + 8;
            
            firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, colNumber, colNumber+3));
            
            HSSFCell pendingCell = claimHeader.createCell(colNumber);
            pendingCell.setCellValue("PENDING");
            pendingCell.setCellStyle(style);
            colNumber = colNumber + 4;
            
            firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, colNumber, colNumber+3));
            
            HSSFCell approveCell = claimHeader.createCell(colNumber);
            approveCell.setCellValue("APPROVE");
            approveCell.setCellStyle(style);
            colNumber = colNumber + 4;
            
            firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, colNumber, colNumber+2));
            
            HSSFCell closedCell = claimHeader.createCell(colNumber);
            closedCell.setCellValue("CLOSED");
            closedCell.setCellStyle(style);
            colNumber = colNumber + 3;
            
            firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, colNumber, colNumber+6));
            
            HSSFCell finalizedCell = claimHeader.createCell(colNumber);
            finalizedCell.setCellValue("FINALIZED");
            finalizedCell.setCellStyle(style);
            colNumber = colNumber+7;
            col3Number = colNumber;
            
            HSSFCell caseDateCell = claimHeader.createCell(colNumber++);
            caseDateCell.setCellValue("Case Date");
            caseDateCell.setCellStyle(style);

            HSSFCell limitBenefitCell = claimHeader.createCell(colNumber++);
            limitBenefitCell.setCellValue("Limit Benefit");
            limitBenefitCell.setCellStyle(style);
            
            HSSFCell chargeCell = claimHeader.createCell(colNumber++);
            chargeCell.setCellValue("Charge");
            chargeCell.setCellStyle(style);
            
            HSSFCell approvedCell = claimHeader.createCell(colNumber++);
            approvedCell.setCellValue("Approved");
            approvedCell.setCellStyle(style);
            
            HSSFCell excessCell = claimHeader.createCell(colNumber++);
            excessCell.setCellValue("Excess");
            excessCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(colNumber++);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell descriptionCell = claimHeader.createCell(colNumber++);
            descriptionCell.setCellValue("Description");
            descriptionCell.setCellStyle(style);

            HSSFCell longOfStayCell = claimHeader.createCell(colNumber++);
            longOfStayCell.setCellValue("Long Of Stay");
            longOfStayCell.setCellStyle(style);
            
            rowNumber++;
            
            HSSFRow case2Header = firstSheet.createRow(rowNumber);

            HSSFCell dtOpenCell = case2Header.createCell(col2Number++);
            dtOpenCell.setCellValue("Date Time");
            dtOpenCell.setCellStyle(style);
            
            HSSFCell alOpenCell = case2Header.createCell(col2Number++);
            alOpenCell.setCellValue("AL");
            alOpenCell.setCellStyle(style);
            
            HSSFCell jalOpenCell = case2Header.createCell(col2Number++);
            jalOpenCell.setCellValue("JAL");
            jalOpenCell.setCellStyle(style);
            
            HSSFCell dfOpenCell = case2Header.createCell(col2Number++);
            dfOpenCell.setCellValue("Duration Form");
            dfOpenCell.setCellStyle(style);

            HSSFCell rmaOpenCell = case2Header.createCell(col2Number++);
            rmaOpenCell.setCellValue("RMA");
            rmaOpenCell.setCellStyle(style);
            
            HSSFCell jrmaOpenCell = case2Header.createCell(col2Number++);
            jrmaOpenCell.setCellValue("JRMA");
            jrmaOpenCell.setCellStyle(style);
            
            HSSFCell df2OpenCell = case2Header.createCell(col2Number++);
            df2OpenCell.setCellValue("Duration Form");
            df2OpenCell.setCellStyle(style);
            
            HSSFCell agentOpenCell = case2Header.createCell(col2Number++);
            agentOpenCell.setCellValue("Agent");
            agentOpenCell.setCellStyle(style);
            
            HSSFCell dtPendingCell = case2Header.createCell(col2Number++);
            dtPendingCell.setCellValue("Date Time");
            dtPendingCell.setCellStyle(style);
            
            HSSFCell dsPendingCell = case2Header.createCell(col2Number++);
            dsPendingCell.setCellValue("Duration Status");
            dsPendingCell.setCellStyle(style);
            
            HSSFCell descPendingCell = case2Header.createCell(col2Number++);
            descPendingCell.setCellValue("Description");
            descPendingCell.setCellStyle(style);
            
            HSSFCell agentPendingCell = case2Header.createCell(col2Number++);
            agentPendingCell.setCellValue("Agent");
            agentPendingCell.setCellStyle(style);
            
            HSSFCell dtApproveCell = case2Header.createCell(col2Number++);
            dtApproveCell.setCellValue("Date Time");
            dtApproveCell.setCellStyle(style);
            
            HSSFCell dsApproveCell = case2Header.createCell(col2Number++);
            dsApproveCell.setCellValue("Duration Status");
            dsApproveCell.setCellStyle(style);
            
            HSSFCell glApproveCell = case2Header.createCell(col2Number++);
            glApproveCell.setCellValue("GL");
            glApproveCell.setCellStyle(style);
            
            HSSFCell agentApproveCell = case2Header.createCell(col2Number++);
            agentApproveCell.setCellValue("Agent");
            agentApproveCell.setCellStyle(style);
            
            HSSFCell dtClosedCell = case2Header.createCell(col2Number++);
            dtClosedCell.setCellValue("Date Time");
            dtClosedCell.setCellStyle(style);
            
            HSSFCell dsClosedCell = case2Header.createCell(col2Number++);
            dsClosedCell.setCellValue("Duration Status");
            dsClosedCell.setCellStyle(style);
            
            HSSFCell agentClosedCell = case2Header.createCell(col2Number++);
            agentClosedCell.setCellValue("Agent");
            agentClosedCell.setCellStyle(style);
            
            HSSFCell dtFinalCell = case2Header.createCell(col2Number++);
            dtFinalCell.setCellValue("Date Time");
            dtFinalCell.setCellStyle(style);
            
            HSSFCell dsFinalCell = case2Header.createCell(col2Number++);
            dsFinalCell.setCellValue("Duration Status");
            dsFinalCell.setCellStyle(style);
            
            HSSFCell fbFinalCell = case2Header.createCell(col2Number++);
            fbFinalCell.setCellValue("Final Bill");
            fbFinalCell.setCellStyle(style);
            

            HSSFCell sp3FinalCell = case2Header.createCell(col2Number++);
            sp3FinalCell.setCellValue("SP3");
            sp3FinalCell.setCellStyle(style);
            
            HSSFCell dfFinalCell = case2Header.createCell(col2Number++);
            dfFinalCell.setCellValue("Duration Form");
            dfFinalCell.setCellStyle(style);
            
            HSSFCell agentFinalCell = case2Header.createCell(col2Number++);
            agentFinalCell.setCellValue("Agent");
            agentFinalCell.setCellStyle(style);
            
            HSSFCell remarkFinalCell = case2Header.createCell(col2Number++);
            remarkFinalCell.setCellValue("Remarks");
            remarkFinalCell.setCellStyle(style);
            
            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            long currentTime = Calendar.getInstance().getTimeInMillis() / (1000 * 60 * 60 * 24);

            int no = 0;
            int col4Number = col3Number;
            for (Case myCase : caseList) {
            	colNumber = 0;
                no += 1;
                rowNumber += 1;
                col4Number = col3Number;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(colNumber++).setCellValue(no);

                row.createCell(colNumber++).setCellValue(myCase.getCaseNumber());
                
                row.createCell(colNumber++).setCellValue(myCase.getMemberId().getFirstName());
                
                row.createCell(colNumber++).setCellValue(myCase.getMemberId().getCurrentCardNumber());
                
                row.createCell(colNumber++).setCellValue(myCase.getMemberId().getClientName());
                
                row.createCell(colNumber++).setCellValue(myCase.getMemberId().getGroupName());
                
                row.createCell(colNumber++).setCellValue(myCase.getProviderId().getProviderName());
                
                row.createCell(colNumber++).setCellValue(myCase.getCaseCategoryId().getCaseCategoryCode());
                
                row.createCell(colNumber++).setCellValue(myCase.getDiagnosis1Id() != null ? myCase.getDiagnosis1Id().getDescription() : "");
                
                //OPEN REPORT
                String[] eqColumnOpen = {"deletedStatus", "actionType", "caseId.caseId", "afterStatus.caseStatusId"};
                Object[] eqValueOpen = {Integer.valueOf(0), "OPEN CASE", myCase.getCaseId(), Integer.valueOf(1)};
                CaseHistory openHistory = caseHistoryService.searchUniqueGLApprove(eqColumnOpen, eqValueOpen);
                
                if(openHistory != null){
                	row.createCell(colNumber++).setCellValue(openHistory.getCreatedTime()!= null?df2.format(openHistory.getCreatedTime()):"");
                	row.createCell(colNumber++).setCellValue(""); //OPEN AL
                    row.createCell(colNumber++).setCellValue(""); //OPEN JAL
                    row.createCell(colNumber++).setCellValue(openHistory.getDurationString()!=null?openHistory.getDurationString():"00:00:00");
                    row.createCell(colNumber++).setCellValue(""); //OPEN RMA
                    row.createCell(colNumber++).setCellValue(""); //OPEN JMA
                    row.createCell(colNumber++).setCellValue(openHistory.getDurationString()!=null?openHistory.getDurationString():"00:00:00");
                    row.createCell(colNumber++).setCellValue(openHistory.getCreatedBy()!=null?openHistory.getCreatedBy():"");
                }else{
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	colNumber++;
                }
                
                //PENDING CASE
                String[] eqColumnPending = {"deletedStatus", "actionType", "caseId.caseId", "beforeStatus.caseStatusId", "afterStatus.caseStatusId"};
                Object[] eqValuePending = {Integer.valueOf(0), "PENDING CASE", myCase.getCaseId(), Integer.valueOf(1), Integer.valueOf(10)};
                CaseHistory pendingHistory = caseHistoryService.searchUniqueGLApprove(eqColumnPending, eqValuePending);
                
                if(pendingHistory != null){
                	row.createCell(colNumber++).setCellValue(pendingHistory.getCreatedTime()!= null?df2.format(pendingHistory.getCreatedTime()):"");
                	row.createCell(colNumber++).setCellValue(pendingHistory.getDurationString()!=null?pendingHistory.getDurationString():"");
                	row.createCell(colNumber++).setCellValue(myCase.getAssignmentNote()!=null?myCase.getAssignmentNote():"");
                	row.createCell(colNumber++).setCellValue(pendingHistory.getCreatedBy()!=null?pendingHistory.getCreatedBy():"");
                }else{
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	colNumber++;
                }
                
                //APPROVE CASE
                String[] eqColumnApprove = {"deletedStatus", "actionType", "caseId.caseId", "beforeStatus.caseStatusId", "afterStatus.caseStatusId"};
                CaseHistory approveHistory = null;
                if(pendingHistory==null){
                	 Object[] eqValueApprove = {Integer.valueOf(0), "APPROVE CASE", myCase.getCaseId(), Integer.valueOf(1), Integer.valueOf(9)};
                     approveHistory = caseHistoryService.searchUniqueGLApprove(eqColumnApprove, eqValueApprove);
                }else{
                	 Object[] eqValueApprove = {Integer.valueOf(0), "APPROVE CASE", myCase.getCaseId(), Integer.valueOf(10), Integer.valueOf(9)};
                     approveHistory = caseHistoryService.searchUniqueGLApprove(eqColumnApprove, eqValueApprove);
                }
                
                if(approveHistory != null && openHistory != null){
                	Seconds seconds = Seconds.secondsBetween(new DateTime(openHistory.getHistoryTime().getTime()) , 
    						new DateTime(approveHistory.getHistoryTime().getTime()));
                	row.createCell(colNumber++).setCellValue(approveHistory.getCreatedTime()!= null?df2.format(approveHistory.getCreatedTime()):"");
                	//row.createCell(colNumber++).setCellValue(approveHistory.getDurationString()!=null?approveHistory.getDurationString():"");
                	LocalTime local = new LocalTime(0,0);
    				local = local.plusSeconds(seconds.getSeconds());
    				String output = DateTimeFormat.forPattern("HH:mm:ss").print(local);
                	row.createCell(colNumber++).setCellValue(output !=null? output:"");
                	row.createCell(colNumber++).setCellValue("");
                	row.createCell(colNumber++).setCellValue(approveHistory.getCreatedBy()!=null?approveHistory.getCreatedBy():"");
                }else{
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	colNumber++;
                }
                
                //CLOSE CASE
                String[] eqColumnClose = {"deletedStatus", "actionType", "caseId.caseId", "beforeStatus.caseStatusId", "afterStatus.caseStatusId"};
                Object[] eqValueClose = {Integer.valueOf(0), "CLOSE CASE", myCase.getCaseId(), Integer.valueOf(9), Integer.valueOf(5)};
                CaseHistory closeHistory = caseHistoryService.searchUniqueGLApprove(eqColumnClose, eqValueClose);
                
                if(closeHistory != null){
                	row.createCell(colNumber++).setCellValue(closeHistory.getCreatedTime()!= null?df2.format(closeHistory.getCreatedTime()):"");
                	row.createCell(colNumber++).setCellValue(closeHistory.getDurationString()!=null?closeHistory.getDurationString():"");
                	row.createCell(colNumber++).setCellValue(closeHistory.getCreatedBy()!=null?closeHistory.getCreatedBy():"");
                }else{
                	colNumber++;
                	colNumber++;
                	colNumber++;
                }
                
                //FINALIZED CASE
                String[] eqColumnFinal = {"deletedStatus", "actionType", "caseId.caseId", "beforeStatus.caseStatusId", "afterStatus.caseStatusId"};
                Object[] eqValueFinal = {Integer.valueOf(0), "FINALIZE CASE", myCase.getCaseId(), Integer.valueOf(5), Integer.valueOf(15)};
                CaseHistory finalHistory = caseHistoryService.searchUniqueGLApprove(eqColumnFinal, eqValueFinal);
                
                if(finalHistory != null){
                	row.createCell(colNumber++).setCellValue(finalHistory.getCreatedTime()!= null?df2.format(finalHistory.getCreatedTime()):"");
                	row.createCell(colNumber++).setCellValue(finalHistory.getDurationString()!=null?finalHistory.getDurationString():"");
                	colNumber++;
                	colNumber++;
                	colNumber++;
                	row.createCell(colNumber++).setCellValue(finalHistory.getCreatedBy()!=null?finalHistory.getCreatedBy():"");
//                	row.createCell(colNumber++).setCellValue(myCase.getDescription()!=null?myCase.getDescription():"");
                	row.createCell(colNumber++).setCellValue(finalHistory.getDescription()!=null?finalHistory.getDescription():"");
                }
                
                if(myCase.getCaseStartTime() != null)
                	row.createCell(col4Number++).setCellValue(df.format(myCase.getCaseStartTime()));
                else
                	row.createCell(col4Number++).setCellValue("");
                
                if(myCase.getPreRemainingLimit() != null)
                	row.createCell(col4Number++).setCellValue(myCase.getPreRemainingLimit());
                else
                	row.createCell(col4Number++).setCellValue("");
                
                if(myCase.getCaseClaimValue() != null)
                	row.createCell(col4Number++).setCellValue(myCase.getCaseClaimValue());
                else
                	row.createCell(col4Number++).setCellValue("");
                
                if(myCase.getCaseApprovedValue() != null)
                	row.createCell(col4Number++).setCellValue(myCase.getCaseApprovedValue());
                else
                	row.createCell(col4Number++).setCellValue("");
                
                if(myCase.getCaseExcessValue() != null)
                	row.createCell(col4Number++).setCellValue(myCase.getCaseExcessValue());
                else
                	row.createCell(col4Number++).setCellValue("");
                
                row.createCell(col4Number++).setCellValue(myCase.getCaseStatusId().getCaseStatusName());
                row.createCell(col4Number++).setCellValue(myCase.getDescription());

                if(myCase.getLongOfStay() != null)
                	row.createCell(col4Number++).setCellValue(myCase.getLongOfStay());
                else
                	row.createCell(col4Number++).setCellValue("");
            }

            // auto size column
            HSSFRow row = firstSheet.getRow(rowNumber);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
