package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class ExcessReportGenerator {

    public static HSSFWorkbook generateReport(User user, Collection<ExcessCharge> excessChargeList) throws Exception {
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

            int rowNumber = 0;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(0);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell excessNumberCell = claimHeader.createCell(1);
            excessNumberCell.setCellValue("Excess Number");
            excessNumberCell.setCellStyle(style);

            HSSFCell memberNumberCell = claimHeader.createCell(2);
            memberNumberCell.setCellValue("Member Number");
            memberNumberCell.setCellStyle(style);

            HSSFCell memberNameCell = claimHeader.createCell(3);
            memberNameCell.setCellValue("Member Name");
            memberNameCell.setCellStyle(style);

            HSSFCell memberGroupCell = claimHeader.createCell(4);
            memberGroupCell.setCellValue("Member Group");
            memberGroupCell.setCellStyle(style);

            HSSFCell excessDateCell = claimHeader.createCell(5);
            excessDateCell.setCellValue("Excess Date");
            excessDateCell.setCellStyle(style);

            HSSFCell excessChargeCell = claimHeader.createCell(6);
            excessChargeCell.setCellValue("Excess Charge");
            excessChargeCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(7);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell reminderDateCell = claimHeader.createCell(8);
            reminderDateCell.setCellValue("Reminder Date");
            reminderDateCell.setCellStyle(style);

            HSSFCell counterCell = claimHeader.createCell(9);
            counterCell.setCellValue("Counter");
            counterCell.setCellStyle(style);

            HSSFCell remarksCell = claimHeader.createCell(10);
            remarksCell.setCellValue("Remarks");
            remarksCell.setCellStyle(style);

//            HSSFCell modifiedByCell = claimHeader.createCell(10);
//            modifiedByCell.setCellValue("Officer");
//            modifiedByCell.setCellStyle(style);
//
//            HSSFCell modifiedTimeCell = claimHeader.createCell(11);
//            modifiedTimeCell.setCellValue("Modified Time");
//            modifiedTimeCell.setCellStyle(style);


            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            boolean isUserType2 = (user != null && Integer.valueOf(2).equals(user.getUserType()));

            for (ExcessCharge excessCharge : excessChargeList) {

                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(rowNumber);
                row.createCell(1).setCellValue(excessCharge.getExcessChargeNumber());

                Member member = excessCharge.getMemberId();
                row.createCell(2).setCellValue(member == null ? "" : member.getCustomerPolicyNumber());

                row.createCell(3).setCellValue(member == null ? "" : member.getFirstName());

                MemberGroup memberGroup = (member == null ? null : member.getMemberGroupId());
                row.createCell(4).setCellValue(memberGroup == null ? "" : memberGroup.getGroupName());

                row.createCell(5).setCellValue(excessCharge.getExcessChargeTime() == null ? "" : excessCharge.getExcessChargeTime().toString());
                row.createCell(6).setCellValue(excessCharge.getExcessChargeValue() == null ? 0.0 : excessCharge.getExcessChargeValue());

                String status = "";
                PaymentStatus paymentStatus = excessCharge.getExcessChargeStatus();
                if (paymentStatus != null) {
                    if (Integer.valueOf(1).equals(paymentStatus.getPaymentStatusId())) {
                        status = "OPEN";
                    }
                    if (Integer.valueOf(2).equals(paymentStatus.getPaymentStatusId())) {
                        status = "PAID";
                    }
                    if (Integer.valueOf(3).equals(paymentStatus.getPaymentStatusId())) {
                        status = "APPROVED";
                    }
                    if (Integer.valueOf(4).equals(paymentStatus.getPaymentStatusId())) {
                        status = "BAD";
                    }
                    if (Integer.valueOf(5).equals(paymentStatus.getPaymentStatusId())) {
                        status = "PENDING";
                    }
                    if (Integer.valueOf(6).equals(paymentStatus.getPaymentStatusId())) {
                        status = "DISPOSITIONED";
                    }
                    if (Integer.valueOf(7).equals(paymentStatus.getPaymentStatusId())) {
                        status = "PARTIAL PAID";
                    }
                }
                row.createCell(7).setCellValue(status);

                if (isUserType2) {
                    row.createCell(8).setCellValue(excessCharge.getNextReminderTime() == null ? "" : excessCharge.getNextReminderTime().toString());
                    row.createCell(9).setCellValue(excessCharge.getReminderCounter() == null ? 0 : excessCharge.getReminderCounter());
                }

                row.createCell(10).setCellValue(excessCharge.getRemarks());

//                row.createCell(10).setCellValue(excessCharge.getModifiedBy()); // modified by
//                row.createCell(11).setCellValue(excessCharge.getModifiedTime() == null ? "" : excessCharge.getModifiedTime().toString()); // modified time
            }

            // auto size column
            HSSFRow row = firstSheet.getRow(0);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
