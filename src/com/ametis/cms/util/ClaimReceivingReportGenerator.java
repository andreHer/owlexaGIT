package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class ClaimReceivingReportGenerator {

    public static HSSFWorkbook generateReport(Collection<ClaimReceiving> claimReceivingList) throws Exception {
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

            HSSFCell claimNumberCell = claimHeader.createCell(1);
            claimNumberCell.setCellValue("Receiving Number");
            claimNumberCell.setCellStyle(style);

            HSSFCell admissionCell = claimHeader.createCell(2);
            admissionCell.setCellValue("Receiving Time");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = claimHeader.createCell(3);
            dischargeCell.setCellValue("Total Document");
            dischargeCell.setCellStyle(style);

            HSSFCell memberCell = claimHeader.createCell(4);
            memberCell.setCellValue("Client");
            memberCell.setCellStyle(style);

            HSSFCell payValueCell = claimHeader.createCell(5);
            payValueCell.setCellValue("Provider");
            payValueCell.setCellStyle(style);
            
            HSSFCell chargesCell = claimHeader.createCell(6);
            chargesCell.setCellValue("Courier");
            chargesCell.setCellStyle(style);

            HSSFCell approvedCell = claimHeader.createCell(7);
            approvedCell.setCellValue("Officer");
            approvedCell.setCellStyle(style);

            HSSFCell paidCell = claimHeader.createCell(8);
            paidCell.setCellValue("Modified Time");
            paidCell.setCellStyle(style);

            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            for (ClaimReceiving claimReceiving : claimReceivingList) {

                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(rowNumber);
                row.createCell(1).setCellValue(claimReceiving.getReceivingNumber());

                String receiveDate = (claimReceiving.getReceiveDate() == null ? "" : claimReceiving.getReceiveDate().toString());
                String receiveTime = (claimReceiving.getReceiveTime() == null ? "" : claimReceiving.getReceiveTime());

                row.createCell(2).setCellValue(receiveDate + " : " + receiveTime);

                row.createCell(3).setCellValue(claimReceiving.getTotalReceiving());

                Client client = claimReceiving.getClientId();
                row.createCell(4).setCellValue(client == null ? "" : client.getClientName());

                Provider provider = claimReceiving.getProviderId();
                row.createCell(5).setCellValue(provider == null ? "" : provider.getProviderName());

                row.createCell(6).setCellValue(claimReceiving.getCourier());

                row.createCell(7).setCellValue(claimReceiving.getModifiedBy() == null ? claimReceiving.getCreatedBy() : claimReceiving.getModifiedBy());
                row.createCell(8).setCellValue(claimReceiving.getModifiedTime() == null ? claimReceiving.getCreatedTime().toString() : claimReceiving.getModifiedTime().toString());
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
