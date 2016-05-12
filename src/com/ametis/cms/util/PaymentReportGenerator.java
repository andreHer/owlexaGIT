package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.ClaimService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class PaymentReportGenerator {

    public static HSSFWorkbook generateReport(Collection<Payment> paymentList, Map<Integer, String> memberGroupMap, Map<Integer, Integer> totalClaimMap) throws Exception {
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

            HSSFCellStyle rightAlignStyle = workbook.createCellStyle();
            rightAlignStyle.setAlignment(CellStyle.ALIGN_RIGHT);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 0;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(0);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell batchNumberCell = claimHeader.createCell(1);
            batchNumberCell.setCellValue("Batch Number");
            batchNumberCell.setCellStyle(style);

            HSSFCell memberGroupCell = claimHeader.createCell(2);
            memberGroupCell.setCellValue("Member Group");
            memberGroupCell.setCellStyle(style);

            HSSFCell clientCell = claimHeader.createCell(3);
            clientCell.setCellValue("Client");
            clientCell.setCellStyle(style);

            HSSFCell providerCell = claimHeader.createCell(4);
            providerCell.setCellValue("Provider");
            providerCell.setCellStyle(style);

            HSSFCell noInvoiceCell = claimHeader.createCell(5);
            noInvoiceCell.setCellValue("No Invoice");
            noInvoiceCell.setCellStyle(style);
            
            HSSFCell tanggalInvoiceCell = claimHeader.createCell(6);
            tanggalInvoiceCell.setCellValue("Tanggal Invoice");
            tanggalInvoiceCell.setCellStyle(style);

            HSSFCell memberNameCell = claimHeader.createCell(7);
            memberNameCell.setCellValue("Member Name");
            memberNameCell.setCellStyle(style);

            HSSFCell memberNumberCell = claimHeader.createCell(8);
            memberNumberCell.setCellValue("Member Number");
            memberNumberCell.setCellStyle(style);

            HSSFCell receivedDateCell = claimHeader.createCell(9);
            receivedDateCell.setCellValue("Received Date");
            receivedDateCell.setCellStyle(style);
            
            HSSFCell dueDateCell = claimHeader.createCell(10);
            dueDateCell.setCellValue("Due Date");
            dueDateCell.setCellStyle(style);

            HSSFCell submissionDateCell = claimHeader.createCell(11);
            submissionDateCell.setCellValue("Submission Date");
            submissionDateCell.setCellStyle(style);

            HSSFCell paymentDateCell = claimHeader.createCell(12);
            paymentDateCell.setCellValue("Payment Date");
            paymentDateCell.setCellStyle(style);

            HSSFCell batchValueCell = claimHeader.createCell(13);
            batchValueCell.setCellValue("Batch Value");
            batchValueCell.setCellStyle(style);

            HSSFCell paidValueCell = claimHeader.createCell(14);
            paidValueCell.setCellValue("Paid Value");
            paidValueCell.setCellStyle(style);

            HSSFCell pendingValueCell = claimHeader.createCell(15);
            pendingValueCell.setCellValue("Pending Value");
            pendingValueCell.setCellStyle(style);

            HSSFCell totalClaimCell = claimHeader.createCell(16);
            totalClaimCell.setCellValue("Total Claim");
            totalClaimCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(17);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell cdvNumberCell = claimHeader.createCell(18);
            cdvNumberCell.setCellValue("CDV Number");
            cdvNumberCell.setCellStyle(style);

            HSSFCell cdvDateCell = claimHeader.createCell(19);
            cdvDateCell.setCellValue("CDV Date");
            cdvDateCell.setCellStyle(style);

            HSSFCell cdvPaymentConfirmationCell = claimHeader.createCell(20);
            cdvPaymentConfirmationCell.setCellValue("CDV Payment Confirmation");
            cdvPaymentConfirmationCell.setCellStyle(style);

            HSSFCell bankCell = claimHeader.createCell(21);
            bankCell.setCellValue("Bank");
            bankCell.setCellStyle(style);

            HSSFCell nomorSuratJalanCell = claimHeader.createCell(22);
            nomorSuratJalanCell.setCellValue("Nomor Surat Jalan");
            nomorSuratJalanCell.setCellStyle(style);

            HSSFCell typeClaimCell = claimHeader.createCell(23);
            typeClaimCell.setCellValue("Type Claim");
            typeClaimCell.setCellStyle(style);

            HSSFCell modifiedByCell = claimHeader.createCell(24);
            modifiedByCell.setCellValue("Officer");
            modifiedByCell.setCellStyle(style);

            HSSFCell modifiedTimeCell = claimHeader.createCell(25);
            modifiedTimeCell.setCellValue("Modified Time");
            modifiedTimeCell.setCellStyle(style);


            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            for (Payment payment : paymentList) {

                BatchClaim batchClaim = payment.getBatchClaim();

                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(rowNumber);

                row.createCell(1).setCellValue(batchClaim == null ? "" : batchClaim.getBatchClaimNumber());

                row.createCell(2).setCellValue(memberGroupMap.get(payment.getPaymentId()));

                if(batchClaim != null) {
                    Client client = batchClaim.getClientId();
                    row.createCell(3).setCellValue(client == null ? "" : client.getClientName());

                    Provider provider = batchClaim.getProviderId();
                    row.createCell(4).setCellValue(provider == null ? "" : provider.getProviderName());

                    row.createCell(5).setCellValue(batchClaim.getInvoiceNumber()); //No Invoice
                    row.createCell(6).setCellValue(batchClaim.getInvoiceDate() == null ? "" : batchClaim.getInvoiceDate().toString()); //Tanggal Invoice
                }

                Member member = payment.getMemberId();
                if(member != null) {
                    row.createCell(7).setCellValue(member == null ? "" : member.getFirstName());
                    row.createCell(8).setCellValue(member == null ? "" : member.getCustomerPolicyNumber()); //Member Number
                }

                if(batchClaim != null) {
                    row.createCell(9).setCellValue(batchClaim.getBatchClaimDate() == null ? "" : batchClaim.getBatchClaimDate().toString());
                    row.createCell(10).setCellValue(batchClaim.getBatchDueDate() == null ? "" : batchClaim.getBatchDueDate().toString());
                }

                row.createCell(11).setCellValue(payment.getDispositionDate() == null ? "" : payment.getDispositionDate().toString()); //Submission Date
                row.createCell(12).setCellValue(payment.getPaymentTime() == null ? "" : payment.getPaymentTime().toString());

                if (batchClaim != null) {
                    row.createCell(13).setCellValue(batchClaim.getBatchClaimInitialValue() == null ? 0.0 : batchClaim.getBatchClaimInitialValue()); //Batch Value
                    row.getCell(13).setCellStyle(rightAlignStyle);
                }

                row.createCell(14).setCellValue(payment.getPaymentValue() == null ? 0.0 : payment.getPaymentValue()); //Paid Value
                row.getCell(14).setCellStyle(rightAlignStyle);

                Outstanding outstanding = payment.getOutstandingId();
                if (outstanding != null) {
                    row.createCell(15).setCellValue(outstanding.getOutstandingValue() == null ? 0.0 : outstanding.getOutstandingValue()); //Pending Value
                    row.getCell(15).setCellStyle(rightAlignStyle);
                }
//                if (batchClaim != null) {
//                    for(Outstanding outstanding : batchClaim.getOutstandings()) {
//                    }
//                }

                row.createCell(16).setCellValue(totalClaimMap.get(payment.getPaymentId()));

                String status = "";
                PaymentStatus paymentStatus = payment.getPaymentStatus();
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
                row.createCell(17).setCellValue(status);

                row.createCell(18).setCellValue(payment.getPaymentNumber());

                row.createCell(19).setCellValue(payment.getPaymentTime() == null ? "" : payment.getPaymentTime().toString()); //CDV Date
                row.createCell(20).setCellValue(payment.getPaymentConfirmDate() == null ? "" : payment.getPaymentConfirmDate().toString()); //CDV Payment Confirmation

                String bankName = payment.getBankName() == null ? "" : payment.getBankName();
                String bankBranch = payment.getBankBranch() == null ? "" : payment.getBankBranch();
                String bankCity = payment.getBankCity() == null ? "" : payment.getBankCity();

                row.createCell(21).setCellValue(
                        String.format("%s - %s - %s", bankName, bankBranch, bankCity));

                row.createCell(22).setCellValue(payment.getPaymentNumber()); //Nomor Surat Jalan

//                row.createCell(6).setCellValue(Converter.getMoney(payment.getPaymentValue()));
//                row.getCell(6).setCellStyle(rightAlignStyle);

                if (batchClaim != null) {
                    ClaimType claimType = batchClaim.getBatchClaimType();

                    row.createCell(23).setCellValue(claimType == null ? "" : claimType.getClaimTypeName());
                }

                row.createCell(24).setCellValue(payment.getModifiedBy() == null ? payment.getCreatedBy() : payment.getModifiedBy()); // modified by

                Timestamp createdTime = payment.getCreatedTime();
                Timestamp modifiedTime = payment.getModifiedTime();
                row.createCell(25).setCellValue(modifiedTime == null ? createdTime.toString() : modifiedTime.toString()); // modified time
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
