package com.ametis.cms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
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
import com.ametis.cms.datamodel.CaseHistory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.MemberClaimDto;

public class CaseReportDownloader {
	
	public static HSSFWorkbook downloadCaseMonitoring(Collection<CaseEvent> eventList) {
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

            HSSFSheet firstSheet = workbook.createSheet();

            Iterator<CaseEvent> eventIterator = eventList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("Tanggal/Waktu");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("Point Monitoring");
            batchNoHCell.setCellStyle(style);

            
            HSSFCell dokterHCell = claimHeader.createCell((short) 2);
            dokterHCell.setCellValue("Dokter");
            dokterHCell.setCellStyle(style);
            
            HSSFCell therapyHCell = claimHeader.createCell((short) 3);
            therapyHCell.setCellValue("Therapy");
            therapyHCell.setCellStyle(style);
            
            HSSFCell diagnosticHCell = claimHeader.createCell((short) 4);
            diagnosticHCell.setCellValue("Diagnostic Test");
            diagnosticHCell.setCellStyle(style);
            
            HSSFCell anamnesaHCell = claimHeader.createCell((short) 5);
            anamnesaHCell.setCellValue("Anamnesa");
            anamnesaHCell.setCellStyle(style);
            
            HSSFCell remarksHCell = claimHeader.createCell((short) 6);
            remarksHCell.setCellValue("Remarks");
            remarksHCell.setCellStyle(style);


            HSSFCell admissionHCell = claimHeader.createCell((short) 7);
            admissionHCell.setCellValue("PIC RS/Provider");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 8);
            benefitHCell.setCellValue("PIC TPA");
            benefitHCell.setCellStyle(style);

            while (eventIterator.hasNext()) {

                CaseEvent event = eventIterator.next();


                rowNumber += 1;

                String monitoring = "";
                String contactPerson = "";
                
                if (event.getDoctorConsult() != null && !event.getDoctorConsult().equals("")){
                	contactPerson += "Dokter Konsul : " + event.getDoctorConsult();
                }
                if (event.getNurse() != null && !event.getNurse().equals("")){
                	contactPerson += "Nurse : " + event.getNurse();
                }
                String therapy = event.getTherapy() == null ? "" : event.getTherapy();
                String doctor = event.getDoctorConsult() == null ? "" : event.getDoctorConsult();
                String diagnostic = event.getDiagnosisAspect() == null ? "" : event.getDiagnosisAspect();
                String anamnesa = event.getVitalSign() == null ? "" : event.getVitalSign();
                String remarks = event.getRemarks() == null ? "" : event.getRemarks();
                
                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                claimMember.createCell((short) 0).setCellValue(event.getMonitoringTime().toString());
                claimMember.createCell((short) 1).setCellValue(event.getEventCategoryId().getEventCategoryName());
                claimMember.createCell((short) 2).setCellValue(doctor);
                claimMember.createCell((short) 3).setCellValue(therapy);
                claimMember.createCell((short) 4).setCellValue(diagnostic);
                claimMember.createCell((short) 5).setCellValue(anamnesa);
                claimMember.createCell((short) 6).setCellValue(remarks);
                claimMember.createCell((short) 7).setCellValue(contactPerson);
                claimMember.createCell((short) 8).setCellValue(event.getMonitoredBy());
                
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
	public static HSSFWorkbook downloadCaseHistoryMonitoring(Collection<CaseHistory> historyList) {
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

            HSSFSheet firstSheet = workbook.createSheet();

            Iterator<CaseHistory> eventIterator = historyList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("Case Number");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("Member Number");
            batchNoHCell.setCellStyle(style);

            
            HSSFCell dokterHCell = claimHeader.createCell((short) 2);
            dokterHCell.setCellValue("Member Name");
            dokterHCell.setCellStyle(style);
            
            HSSFCell therapyHCell = claimHeader.createCell((short) 3);
            therapyHCell.setCellValue("Provider Name");
            therapyHCell.setCellStyle(style);
            
            HSSFCell diagnosticHCell = claimHeader.createCell((short) 4);
            diagnosticHCell.setCellValue("Case Category");
            diagnosticHCell.setCellStyle(style);
            
            HSSFCell anamnesaHCell = claimHeader.createCell((short) 5);
            anamnesaHCell.setCellValue("D1");
            anamnesaHCell.setCellStyle(style);
            
            HSSFCell remarksHCell = claimHeader.createCell((short) 6);
            remarksHCell.setCellValue("D2");
            remarksHCell.setCellStyle(style);


            HSSFCell admissionHCell = claimHeader.createCell((short) 7);
            admissionHCell.setCellValue("D3");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 8);
            benefitHCell.setCellValue("Status");
            benefitHCell.setCellStyle(style);
            
            HSSFCell descHCell = claimHeader.createCell((short) 9);
            descHCell.setCellValue("Description");
            descHCell.setCellStyle(style);
            
            HSSFCell actionByCell = claimHeader.createCell((short) 10);
            actionByCell.setCellValue("Action By");
            actionByCell.setCellStyle(style);
            
            HSSFCell durationCell = claimHeader.createCell((short) 11);
            durationCell.setCellValue("Duration");
            durationCell.setCellStyle(style);
            
            HSSFCell actionTime = claimHeader.createCell((short) 12);
            actionTime.setCellValue("Action Time");
            actionTime.setCellStyle(style);

            while (eventIterator.hasNext()) {

                CaseHistory event = eventIterator.next();
                rowNumber += 1;
                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                claimMember.createCell((short) 0).setCellValue(event.getCaseNumber());
                claimMember.createCell((short) 1).setCellValue(event.getMemberNumber());
                claimMember.createCell((short) 2).setCellValue(event.getMemberName());
                claimMember.createCell((short) 3).setCellValue(event.getProviderName());
                claimMember.createCell((short) 4).setCellValue(event.getCaseCategoryName());
                claimMember.createCell((short) 5).setCellValue(event.getDiagnosisCode());
                claimMember.createCell((short) 6).setCellValue(event.getDiagnosis2Code());
                claimMember.createCell((short) 7).setCellValue(event.getDiagnosis3Code());
                claimMember.createCell((short) 8).setCellValue(event.getAfterStatus().getCaseStatusName());
                claimMember.createCell((short) 9).setCellValue(event.getDescription());
                claimMember.createCell((short) 10).setCellValue(event.getActionBy());
                claimMember.createCell((short) 11).setCellValue(event.getDurationString());
                claimMember.createCell((short) 12).setCellValue(event.getHistoryTime() == null ? "" : event.getHistoryTime().toString());
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
	public static HSSFWorkbook downloadGLIssuedMonitoring(Collection<Case> caseList) {
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

            HSSFCell limitBenefitCell = claimHeader.createCell(9);
            limitBenefitCell.setCellValue("Limit Benefit");
            limitBenefitCell.setCellStyle(style);

            HSSFCell diagnosisCell = claimHeader.createCell(10);
            diagnosisCell.setCellValue("Diagnosis");
            diagnosisCell.setCellStyle(style);

            HSSFCell chargeCell = claimHeader.createCell(11);
            chargeCell.setCellValue("Charge");
            chargeCell.setCellStyle(style);

            HSSFCell approvedCell = claimHeader.createCell(12);
            approvedCell.setCellValue("Approved");
            approvedCell.setCellStyle(style);

            HSSFCell excessCell = claimHeader.createCell(13);
            excessCell.setCellValue("Excess");
            excessCell.setCellStyle(style);

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
	
	public static HSSFWorkbook downloadRejectedCaseReport(Collection<RejectedCase> caseList) {
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

            HSSFSheet firstSheet = workbook.createSheet();

            Iterator<RejectedCase> eventIterator = caseList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("Case Number");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("Provider");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell((short) 2);
            claimNoHCell.setCellValue("Coverage");
            claimNoHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 3);
            benefitHCell.setCellValue("Member Name");
            benefitHCell.setCellStyle(style);
            
            HSSFCell cardHCell = claimHeader.createCell((short) 4);
            cardHCell.setCellValue("Card Number");
            cardHCell.setCellStyle(style);
            
            HSSFCell clientHCell = claimHeader.createCell((short) 5);
            clientHCell.setCellValue("Client");
            clientHCell.setCellStyle(style);
            
            HSSFCell groupHCell = claimHeader.createCell((short) 6);
            groupHCell.setCellValue("Group");
            groupHCell.setCellStyle(style);

            HSSFCell admissionHCell = claimHeader.createCell((short) 7);
            admissionHCell.setCellValue("Admission Date");
            admissionHCell.setCellStyle(style);

            HSSFCell dischargeHCell = claimHeader.createCell((short) 8);
            dischargeHCell.setCellValue("Discharge Date");
            dischargeHCell.setCellStyle(style);
            
            HSSFCell diagnosisHCell = claimHeader.createCell((short) 9);
            diagnosisHCell.setCellValue("Diagnosis");
            diagnosisHCell.setCellStyle(style);
            
            HSSFCell anamnesaHCell = claimHeader.createCell((short) 10);
            anamnesaHCell.setCellValue("Anamnesa");
            anamnesaHCell.setCellStyle(style);

            HSSFCell rejectReasonHCell = claimHeader.createCell((short) 11);
            anamnesaHCell.setCellValue("Reason");
            anamnesaHCell.setCellStyle(style);
            
            while (eventIterator.hasNext()) {

            	
                RejectedCase theCase = eventIterator.next();


                rowNumber += 1;

                                
                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                claimMember.createCell((short) 0).setCellValue(theCase.getCaseId().getCaseNumber());
                claimMember.createCell((short) 1).setCellValue(theCase.getCaseId().getProviderId().getProviderName());
                claimMember.createCell((short) 2).setCellValue(theCase.getCaseId().getCaseCategoryId().getCaseCategoryCode());
                claimMember.createCell((short) 3).setCellValue(theCase.getCaseId().getMemberId().getFirstName());
                claimMember.createCell((short) 4).setCellValue(theCase.getCaseId().getCurrentCardNumber());
                claimMember.createCell((short) 5).setCellValue(theCase.getCaseId().getMemberId().getClientName());
                claimMember.createCell((short) 6).setCellValue(theCase.getCaseId().getMemberId().getGroupName());
                claimMember.createCell((short) 7).setCellValue(df.format(theCase.getCaseId().getCaseStartTime()));
                claimMember.createCell((short) 8).setCellValue(df.format(theCase.getCaseId().getCaseEndTime()) );
                claimMember.createCell((short) 9).setCellValue(theCase.getCaseId().getDiagnosis1Id().getDiagnosisName());
                claimMember.createCell((short) 10).setCellValue(theCase.getCaseId().getAnamnesa());
                claimMember.createCell((short) 11).setCellValue(theCase.getRejectionDescription());
                
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
}
