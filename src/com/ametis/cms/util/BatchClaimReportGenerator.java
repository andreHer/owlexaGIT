package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

public class BatchClaimReportGenerator {

    public static HSSFWorkbook generateReport(User user, Collection<BatchClaim> batchClaimList) throws Exception {
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
            claimNumberCell.setCellValue("Number");
            claimNumberCell.setCellStyle(style);

            HSSFCell admissionCell = claimHeader.createCell(2);
            admissionCell.setCellValue("Received Date");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = claimHeader.createCell(3);
            dischargeCell.setCellValue("Client Name");
            dischargeCell.setCellStyle(style);

            HSSFCell memberCell = claimHeader.createCell(4);
            memberCell.setCellValue("Provider Name");
            memberCell.setCellStyle(style);

            HSSFCell providerNameCell = claimHeader.createCell(5);
            providerNameCell.setCellValue("Group Name");
            providerNameCell.setCellStyle(style);

            HSSFCell chargesCell = claimHeader.createCell(6);
            chargesCell.setCellValue("Member Name");
            chargesCell.setCellStyle(style);

            HSSFCell approvedCell = claimHeader.createCell(7);
            approvedCell.setCellValue("Invoice Number");
            approvedCell.setCellStyle(style);

            HSSFCell paidCell = claimHeader.createCell(8);
            paidCell.setCellValue("Invoice Date");
            paidCell.setCellStyle(style);

            HSSFCell excessCell = claimHeader.createCell(9);
            excessCell.setCellValue("Payment Date");
            excessCell.setCellStyle(style);

            HSSFCell typeCell = claimHeader.createCell(10);
            typeCell.setCellValue("Charge");
            typeCell.setCellStyle(style);

            HSSFCell serviceCell = claimHeader.createCell(11);
            serviceCell.setCellValue("Approved");
            serviceCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(12);
            statusCell.setCellValue("Excess");
            statusCell.setCellStyle(style);

            HSSFCell diagnosisCell = claimHeader.createCell(13);
            diagnosisCell.setCellValue("Status");
            diagnosisCell.setCellStyle(style);

            HSSFCell officerCell = claimHeader.createCell(14);
            officerCell.setCellValue("Officer");
            officerCell.setCellStyle(style);

            HSSFCell modifiedTimeCell = claimHeader.createCell(15);
            modifiedTimeCell.setCellValue("Modified Time");
            modifiedTimeCell.setCellStyle(style);
            
            HSSFCell claimTypeTimeCell = claimHeader.createCell(16);
            claimTypeTimeCell.setCellValue("Claim Type");
            claimTypeTimeCell.setCellStyle(style);


            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            boolean isUserType2 = (user != null && Integer.valueOf(2).equals(user.getUserType()));

            for (BatchClaim batchClaim : batchClaimList) {

                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(rowNumber);
                row.createCell(1).setCellValue(batchClaim.getBatchClaimNumber()); // batchClaim number
                row.createCell(2).setCellValue(batchClaim.getBatchClaimDate() == null ? "" : batchClaim.getBatchClaimDate().toString()); // received date

                if (isUserType2) {
                    Client client = batchClaim.getClientId();
                    row.createCell(3).setCellValue(client == null ? "" : client.getClientName()); // client name
                }

                row.createCell(4).setCellValue(batchClaim.getProviderName()); // provider name
                row.createCell(5).setCellValue(batchClaim.getGroupName()); // group name
                row.createCell(6).setCellValue(batchClaim.getMemberName()); // member name
                row.createCell(7).setCellValue(batchClaim.getInvoiceNumber()); // invoice number

                row.createCell(8).setCellValue(batchClaim.getInvoiceDate() == null ? "" : batchClaim.getInvoiceDate().toString()); // invoice date
                row.createCell(9).setCellValue(batchClaim.getPaymentDate() == null ? "" : batchClaim.getPaymentDate().toString()); // payment date

                Double claimChargeValue = batchClaim.getBatchClaimInitialValue() == null ? 0.0 : batchClaim.getBatchClaimInitialValue();
                row.createCell(10).setCellValue(claimChargeValue); // batch claim initial value
                totalClaimChargeValue += claimChargeValue;

                if (isUserType2) {
                    Double claimApprovedValue = batchClaim.getBatchClaimFinalValue() == null ? 0.0 : batchClaim.getBatchClaimFinalValue();
                    row.createCell(11).setCellValue(claimApprovedValue); // batch claim final value
                    totalClaimApprovedValue += claimApprovedValue;

                    Double claimExcessValue = batchClaim.getBatchExcessValue() == null ? 0.0 : batchClaim.getBatchExcessValue();
                    row.createCell(12).setCellValue(claimExcessValue); // batch claim excess value
                    totalClaimExcessValue += claimExcessValue;
                }

                CaseStatus caseStatus = batchClaim.getBatchClaimStatus();
                row.createCell(13).setCellValue(caseStatus == null ? "" : caseStatus.getCaseStatusName()); // status name

                row.createCell(14).setCellValue(batchClaim.getModifiedBy()); // modified by
                row.createCell(15).setCellValue(batchClaim.getModifiedTime() == null ? "" : batchClaim.getModifiedTime().toString()); // modified time
                
                ClaimType claimType = batchClaim.getBatchClaimType();
                row.createCell(16).setCellValue(claimType == null ? "" : claimType.getClaimTypeName()); // claim type
                
            }

            CellStyle jumlahStyle = workbook.createCellStyle();
            jumlahStyle.setFont(fontHeader);
            jumlahStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            jumlahStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

            // last row (jumlah)
            HSSFRow jumlahRow = firstSheet.createRow(++rowNumber);

            HSSFCell jumlahCell = jumlahRow.createCell(1);
            jumlahCell.setCellValue("JUMLAH");
            jumlahCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimChargeCell = jumlahRow.createCell(10);
            totalClaimChargeCell.setCellValue(totalClaimChargeValue);
            totalClaimChargeCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimApprovedCell = jumlahRow.createCell(11);
            totalClaimApprovedCell.setCellValue(totalClaimApprovedValue);
            totalClaimApprovedCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimExcessCell = jumlahRow.createCell(12);
            totalClaimExcessCell.setCellValue(totalClaimExcessValue);
            totalClaimExcessCell.setCellStyle(jumlahStyle);

            jumlahRow.setRowStyle(jumlahStyle);

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
