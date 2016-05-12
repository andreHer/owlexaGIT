package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.ClaimInvestigationService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class SLAReportGenerator {

    public static HSSFWorkbook generateReport(Collection<Claim> claimList, ClaimInvestigationService claimInvestigationService) throws Exception {
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
            style.setAlignment(CellStyle.ALIGN_CENTER);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

            HSSFSheet firstSheet = workbook.createSheet();

            HSSFRow mergedHeader = firstSheet.createRow(0);
            HSSFCell claimsCell = mergedHeader.createCell(0);
            claimsCell.setCellValue("CLAIMS");
            claimsCell.setCellStyle(style);

            /*HSSFCell investigationCell = mergedHeader.createCell(30);
            investigationCell.setCellValue("Investigation");
            investigationCell.setCellStyle(style);*/

            firstSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 29));
            firstSheet.addMergedRegion(new CellRangeAddress(0, 0, 30, 34));

            int START_ROW = 1;

            HSSFRow claimHeader = firstSheet.createRow(START_ROW);

            HSSFCell noCell = claimHeader.createCell(0);
            noCell.setCellValue("Claims No.");
            noCell.setCellStyle(style);

           /* HSSFCell cardNoCell = claimHeader.createCell(1);
            cardNoCell.setCellValue("Card No");
            cardNoCell.setCellStyle(style);

            HSSFCell memberNumberCell = claimHeader.createCell(2);
            memberNumberCell.setCellValue("Member Number");
            memberNumberCell.setCellStyle(style);

            HSSFCell policyNoCell = claimHeader.createCell(3);
            policyNoCell.setCellValue("Policy No");
            policyNoCell.setCellStyle(style);

            HSSFCell providerCodeCell = claimHeader.createCell(4);
            providerCodeCell.setCellValue("Provider Code");
            providerCodeCell.setCellStyle(style);

            HSSFCell providerNameCell = claimHeader.createCell(5);
            providerNameCell.setCellValue("Provider Name");
            providerNameCell.setCellStyle(style);

            HSSFCell admissionDateCell = claimHeader.createCell(6);
            admissionDateCell.setCellValue("Admission Date");
            admissionDateCell.setCellStyle(style);


            HSSFCell dischargeDateCell = claimHeader.createCell(7);
            dischargeDateCell.setCellValue("Discharge Date");
            dischargeDateCell.setCellStyle(style);

            HSSFCell losCell = claimHeader.createCell(8);
            losCell.setCellValue("LOS");
            losCell.setCellStyle(style);

            HSSFCell icdCodeCell = claimHeader.createCell(9);
            icdCodeCell.setCellValue("ICD Code");
            icdCodeCell.setCellStyle(style);

            HSSFCell icdDescriptionCell = claimHeader.createCell(10);
            icdDescriptionCell.setCellValue("ICD Description");
            icdDescriptionCell.setCellStyle(style);

            HSSFCell claimsReceivedDateCell = claimHeader.createCell(11);
            claimsReceivedDateCell.setCellValue("Claims Received Date");
            claimsReceivedDateCell.setCellStyle(style);

            HSSFCell claimsRegisteredDateCell = claimHeader.createCell(12);
            claimsRegisteredDateCell.setCellValue("Claims Registered Date");
            claimsRegisteredDateCell.setCellStyle(style);

            HSSFCell dateTimeLogSentCell = claimHeader.createCell(13);
            dateTimeLogSentCell.setCellValue("Datetime of LoG sent to Hospital");
            dateTimeLogSentCell.setCellStyle(style);

            HSSFCell durationToIssueLoGCell = claimHeader.createCell(14);
            durationToIssueLoGCell.setCellValue("Duration to Issue LoG");
            durationToIssueLoGCell.setCellStyle(style);

            HSSFCell dateTimeOfDeclineLoGCell = claimHeader.createCell(15);
            dateTimeOfDeclineLoGCell.setCellValue("Datetime of Decline LoG");
            dateTimeOfDeclineLoGCell.setCellStyle(style);

            HSSFCell dateOfClaimAssessedCell = claimHeader.createCell(16);
            dateOfClaimAssessedCell.setCellValue("Date of Claim Assessed");
            dateOfClaimAssessedCell.setCellStyle(style);

            HSSFCell claimsProcessStatusCell = claimHeader.createCell(17);
            claimsProcessStatusCell.setCellValue("Claims Process Status");
            claimsProcessStatusCell.setCellStyle(style);

            HSSFCell claimsApprovedDateCell = claimHeader.createCell(18);
            claimsApprovedDateCell.setCellValue("Claims Approved Date");
            claimsApprovedDateCell.setCellStyle(style);

            HSSFCell claimsPaidDateCell = claimHeader.createCell(19);
            claimsPaidDateCell.setCellValue("Claims Paid Date");
            claimsPaidDateCell.setCellStyle(style);

            HSSFCell claimAmountInvoicedCell = claimHeader.createCell(20);
            claimAmountInvoicedCell.setCellValue("Claim Amount Invoiced");
            claimAmountInvoicedCell.setCellStyle(style);

            HSSFCell claimsDueDateCell = claimHeader.createCell(21);
            claimsDueDateCell.setCellValue("Claims Due Date");
            claimsDueDateCell.setCellStyle(style);

            HSSFCell claimAmountPendingCell = claimHeader.createCell(22);
            claimAmountPendingCell.setCellValue("Claim Amount Pending");
            claimAmountPendingCell.setCellStyle(style);

            HSSFCell claimAmountRejectedCell = claimHeader.createCell(23);
            claimAmountRejectedCell.setCellValue("Claim Amount Rejected");
            claimAmountRejectedCell.setCellStyle(style);

            HSSFCell claimCurrencycell = claimHeader.createCell(24);
            claimCurrencycell.setCellValue("Claim Currency");
            claimCurrencycell.setCellStyle(style);

            HSSFCell incurredCell = claimHeader.createCell(25);
            incurredCell.setCellValue("Incurred");
            incurredCell.setCellStyle(style);

            HSSFCell approvedCell = claimHeader.createCell(26);
            approvedCell.setCellValue("Approved");
            approvedCell.setCellStyle(style);

            HSSFCell excessCell = claimHeader.createCell(27);
            excessCell.setCellValue("Excess");
            excessCell.setCellStyle(style);

            HSSFCell paidToProviderCell = claimHeader.createCell(28);
            paidToProviderCell.setCellValue("Paid to Provider");
            paidToProviderCell.setCellStyle(style);

            HSSFCell exgratiaAmountCell = claimHeader.createCell(29);
            exgratiaAmountCell.setCellValue("Exgratia Amount");
            exgratiaAmountCell.setCellStyle(style);

//            Investiation
            HSSFCell dateOfFollowUpCell = claimHeader.createCell(30);
            dateOfFollowUpCell.setCellValue("Date of Follow Up");
            dateOfFollowUpCell.setCellStyle(style);

            HSSFCell followUpCategoryCell = claimHeader.createCell(31);
            followUpCategoryCell.setCellValue("Follow Up Category");
            followUpCategoryCell.setCellStyle(style);

            HSSFCell remarksCell = claimHeader.createCell(32);
            remarksCell.setCellValue("Remarks");
            remarksCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(33);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell followUpBycell = claimHeader.createCell(34);
            followUpBycell.setCellValue("Follow Up By");
            followUpBycell.setCellStyle(style);*/
            

            HSSFCell receiveDateCell = claimHeader.createCell(1);
            receiveDateCell.setCellValue("Receive Date");
            receiveDateCell.setCellStyle(style);
            
            HSSFCell submissionCell = claimHeader.createCell(2);
            submissionCell.setCellValue("Submission Date");
            submissionCell.setCellStyle(style);
            
            HSSFCell numberBatchCell = claimHeader.createCell(3);
            numberBatchCell.setCellValue("Number Batch");
            numberBatchCell.setCellStyle(style);
            
            HSSFCell claimNumberCell = claimHeader.createCell(4);
            claimNumberCell.setCellValue("Claim Number");
            claimNumberCell.setCellStyle(style);

            HSSFCell memberCell = claimHeader.createCell(5);
            memberCell.setCellValue("Member");
            memberCell.setCellStyle(style);
            
            HSSFCell employeeNameCell = claimHeader.createCell(6);
            employeeNameCell.setCellValue("Employee Name");
            employeeNameCell.setCellStyle(style);
            
            HSSFCell cardNumberCell = claimHeader.createCell(7);
            cardNumberCell.setCellValue("Card Number");
            cardNumberCell.setCellStyle(style);

            HSSFCell clientNameCell = claimHeader.createCell(8);
            clientNameCell.setCellValue("Client Name");
            clientNameCell.setCellStyle(style);

            HSSFCell groupNameCell = claimHeader.createCell(9);
            groupNameCell.setCellValue("Group Name");
            groupNameCell.setCellStyle(style);
            
            HSSFCell admissionCell = claimHeader.createCell(10);
            admissionCell.setCellValue("Admission");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = claimHeader.createCell(11);
            dischargeCell.setCellValue("Discharge");
            dischargeCell.setCellStyle(style);

            HSSFCell providerNameCell = claimHeader.createCell(12);
            providerNameCell.setCellValue("Provider Name");
            providerNameCell.setCellStyle(style);

            HSSFCell diagnosisCell = claimHeader.createCell(13);
            diagnosisCell.setCellValue("Diagnosis");
            diagnosisCell.setCellStyle(style);

            HSSFCell chargesCell = claimHeader.createCell(14);
            chargesCell.setCellValue("Charges");
            chargesCell.setCellStyle(style);

            HSSFCell approvedCell = claimHeader.createCell(15);
            approvedCell.setCellValue("Approved");
            approvedCell.setCellStyle(style);
            
            HSSFCell prePaidExcessCell = claimHeader.createCell(16);
            prePaidExcessCell.setCellValue("Pre Paid Excess");
            prePaidExcessCell.setCellStyle(style);

            HSSFCell excessCell = claimHeader.createCell(17);
            excessCell.setCellValue("Excess");
            excessCell.setCellStyle(style);

            HSSFCell refundCell = claimHeader.createCell(18);
            refundCell.setCellValue("Refund");
            refundCell.setCellStyle(style);
            
            HSSFCell paidToProviderCell = claimHeader.createCell(19);
            paidToProviderCell.setCellValue("Paid to Provider");
            paidToProviderCell.setCellStyle(style);
            
            HSSFCell typeCell = claimHeader.createCell(20);
            typeCell.setCellValue("Type");
            typeCell.setCellStyle(style);

            HSSFCell serviceCell = claimHeader.createCell(21);
            serviceCell.setCellValue("Service");
            serviceCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(22);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell claimRemarksCell = claimHeader.createCell(23);
            claimRemarksCell.setCellValue("Claim Remarks");
            claimRemarksCell.setCellStyle(style);

            HSSFCell officerCell = claimHeader.createCell(24);
            officerCell.setCellValue("Officer");
            officerCell.setCellStyle(style);

            HSSFCell modifiedTimeCell = claimHeader.createCell(25);
            modifiedTimeCell.setCellValue("Modified Time");
            modifiedTimeCell.setCellStyle(style);

            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimExcessValue = 0.0;

            int rowNumber = START_ROW;
            int no = 0;
            for (Claim claim : claimList) {

                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(++no);
               /* row.createCell(1).setCellValue(claim.getCardNumber());

                Member member = claim.getMemberId();
                row.createCell(2).setCellValue(member == null ? "" : member.getFirstName());
                row.createCell(3).setCellValue(member == null ? "" : member.getCustomerPolicyNumber());

                Provider provider = claim.getProviderId();
                row.createCell(4).setCellValue(provider == null ? "" : provider.getProviderCode());
                row.createCell(5).setCellValue(provider == null ? "" : provider.getProviderName());

                Date admissionDate = claim.getAdmissionDate();
                row.createCell(6).setCellValue(admissionDate == null ? "" : admissionDate.toString());
                row.createCell(7).setCellValue(claim.getDischargeDate() == null ? "" : claim.getDischargeDate().toString());

                Case aCase = claim.getCaseId();
                row.createCell(8).setCellValue(aCase == null ? "" : aCase.getLongOfStay() == null ? "" : aCase.getLongOfStay().toString());

                Diagnosis diagnosis = claim.getDiagnosisId();
                row.createCell(9).setCellValue(diagnosis == null ? "" : diagnosis.getDiagnosisCode());
                row.createCell(10).setCellValue(diagnosis == null ? "" : diagnosis.getDescription());

                row.createCell(11).setCellValue(claim.getClaimDate() == null ? "" : claim.getClaimDate() == null ? "" : claim.getClaimDate().toString());

                BatchClaim batchClaim = claim.getBatchClaimId();
                row.createCell(12).setCellValue(batchClaim == null ? "" : batchClaim.getBatchClaimDate() == null ? "" : batchClaim.getBatchClaimDate().toString());

                Date caseStartTime = (aCase == null ? null : aCase.getCaseStartTime());
                row.createCell(13).setCellValue(caseStartTime == null ? "" : caseStartTime.toString());

                Long durationToIssueLogDays = 0L;
                if (caseStartTime != null & admissionDate != null) {
                    long interval = caseStartTime.getTime() - admissionDate.getTime();
                    durationToIssueLogDays = interval/(1000 * 60 * 60 * 24);
                }
                row.createCell(14).setCellValue(durationToIssueLogDays);

                row.createCell(15).setCellValue(aCase == null ? "" : aCase.getDeclinedDate() == null ? "" : aCase.getDeclinedDate().toString());

                row.createCell(16).setCellValue(claim.getBenefitCheckedTime() == null ? "" : claim.getBenefitCheckedTime().toString());

                Payment payment = claim.getPaymentId();
                PaymentStatus paymentStatus = payment == null ? null : payment.getPaymentStatus();

                String status = "";
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

                row.createCell(18).setCellValue(claim.getApprovedTime() == null ? "" : claim.getApprovedTime().toString());
                row.createCell(19).setCellValue(claim.getPaidTime() == null ? "" : claim.getPaidTime().toString());

                row.createCell(20).setCellValue(claim.getClaimChargeValue() == null ? 0.0 : claim.getClaimChargeValue());

                row.createCell(21).setCellValue(batchClaim == null ? "" : batchClaim.getBatchDueDate() == null ? "" : batchClaim.getBatchDueDate().toString());

                Double chargeValue = claim.getClaimChargeValue() == null ? 0.0 : claim.getClaimChargeValue();

                Double pendingValue = "PENDING".equals(status) ? chargeValue : 0.0;
                row.createCell(22).setCellValue(pendingValue);

                Double approvedValue = claim.getClaimApprovedValue() == null ? 0.0 : claim.getClaimApprovedValue();

                Double amountRejected = chargeValue - approvedValue;
                row.createCell(23).setCellValue(amountRejected);

                Currency currency = claim.getClaimCurrencyId();
                row.createCell(24).setCellValue(currency == null ? "" : currency.getCurrencyCode());

                row.createCell(25).setCellValue(chargeValue == null ? 0.0 : chargeValue);
                row.createCell(26).setCellValue(approvedValue == null ? 0.0 : approvedValue);

                Double excessValue = claim.getClaimExcessValue();
                row.createCell(27).setCellValue(excessValue == null ? 0.0 : excessValue);

                Double pembayaranDiMukaValue = claim.getPembayaranDimuka() == null ? 0.0 : claim.getPembayaranDimuka();

                Double paidToProviderValue = chargeValue - pembayaranDiMukaValue;
                row.createCell(28).setCellValue(paidToProviderValue);

                Double exgratiaValue = claim.getExGratiaValue() == null ? 0.0 : claim.getExGratiaValue();
                row.createCell(29).setCellValue(exgratiaValue);

                int investigationRowNum = 0;
                for (ClaimInvestigation claimInvestigation : claimInvestigationService.getAllClaimInvestigationByClaimId(claim.getClaimId())) {

                    HSSFRow investigationRow = null;
                    if (investigationRowNum == 0) {
                        investigationRow = row;
                    } else {
                        rowNumber += 1;
                        investigationRow = firstSheet.createRow(rowNumber);
                    }

                    investigationRow.createCell(30).setCellValue(claimInvestigation.getInvestigationDate() == null ? "" : claimInvestigation.getInvestigationDate().toString());
                    investigationRow.createCell(31).setCellValue(claimInvestigation.getInvestigationSubject());
                    investigationRow.createCell(32).setCellValue(claimInvestigation.getDescription());
                    investigationRow.createCell(33).setCellValue(claimInvestigation.getStatus());
                    investigationRow.createCell(34).setCellValue(claimInvestigation.getModifiedBy());

                    investigationRowNum++;
                }*/
                
                
                row.createCell(1).setCellValue(claim.getClaimDate().toString() == null ? "" : claim.getClaimDate().toString() ); // receive date
                
                Payment payment = claim.getPaymentId();
                
                String paymentTime = "";
                if(payment != null){
                	paymentTime = (String) (paymentTime == null ? "" : payment.getPaymentTime().toString()) ; 
                }
                
                row.createCell(2).setCellValue(paymentTime); // submission date
                

                BatchClaim batchClaim = claim.getBatchClaimId();
                String batchClaimNumber = "";
                if (batchClaim != null) {
                    batchClaimNumber = (batchClaim == null ? "" : batchClaim.getBatchClaimNumber());
                }
                row.createCell(3).setCellValue(batchClaimNumber); // number batch
                
                row.createCell(4).setCellValue(claim.getClaimNumber()); // claim number
                
                Member member = claim.getMemberId();
                row.createCell(5).setCellValue(member == null ? "" : member.getFirstName()); // member firstname
                
                Member parentMember = claim.getMemberId().getParentMember();
                row.createCell(6).setCellValue(parentMember == null ? "" : parentMember.getFirstName()); // employee name
                
                row.createCell(7).setCellValue(claim.getCardNumber()); // card number
                
                String clientName = "";
                
                if (batchClaim != null) {
                    Client client = batchClaim.getClientId();
                    clientName = (client == null ? "" : client.getClientName());
                }

                row.createCell(8).setCellValue(clientName); // client name

                row.createCell(9).setCellValue(claim.getGroupName()); // group name
                
                row.createCell(10).setCellValue(claim.getAdmissionDate() == null ? "" : claim.getAdmissionDate().toString()); // admission date
                
                row.createCell(11).setCellValue(claim.getDischargeDate() == null ? "" : claim.getDischargeDate().toString()); // discharge date
                
                row.createCell(12).setCellValue(claim.getProviderName()); // provider name
                
                Diagnosis diagnosis = claim.getDiagnosisId();
                row.createCell(13).setCellValue(diagnosis == null ? "" : diagnosis.getDiagnosisCode()); // diagnosis

                Double claimChargeValue = claim.getClaimChargeValue() == null ? 0.0 : claim.getClaimChargeValue();
                row.createCell(14).setCellValue(claimChargeValue); // charges
                totalClaimChargeValue += claimChargeValue;

                Double claimApprovedValue = claim.getClaimApprovedValue() == null ? 0.0 : claim.getClaimApprovedValue();
                row.createCell(15).setCellValue(claimApprovedValue); // approved
                totalClaimApprovedValue += claimApprovedValue;

                Double prePaidExcessValue = claim.getPrePaidExcess() == null ? 0.0 : claim.getPrePaidExcess();
                row.createCell(16).setCellValue(prePaidExcessValue); // prePaidExcess;

                Double claimExcessValue = claim.getClaimExcessValue() == null ? 0.0 : claim.getClaimExcessValue();
                row.createCell(17).setCellValue(claimExcessValue); // excess
                totalClaimExcessValue += claimExcessValue;
                
                Double refundValue = claim.getRefund() == null ? 0.0 : claim.getRefund();
                row.createCell(18).setCellValue(refundValue); // refund
                
                Double paidToProviderValue = claim.getPaidToProvider() == null ? 0.0 : claim.getPaidToProvider();
                row.createCell(19).setCellValue(paidToProviderValue); // paid to provider
                
                ClaimType claimType = claim.getClaimTypeId();
                row.createCell(20).setCellValue(claimType == null ? "" : claimType.getClaimTypeCode()); // type

                CaseCategory caseCategory = claim.getCaseCategoryId();
                row.createCell(21).setCellValue(caseCategory == null ? "" : caseCategory.getCaseCategoryCode()); // service

                CaseStatus caseStatus = claim.getClaimStatus();
                row.createCell(22).setCellValue(caseStatus == null ? "" : caseStatus.getCaseStatusName()); // status
                
                String remarks = "";
                if (caseStatus != null && caseStatus.getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {
                    remarks = claim.getRejectionRemarks();
                } else {
                    remarks = claim.getClaimRemarks();
                }
                row.createCell(23).setCellValue(remarks); // alasan

                row.createCell(24).setCellValue(claim.getModifiedBy()); // modified by

                row.createCell(25).setCellValue(claim.getModifiedTime() == null ? "" : claim.getModifiedTime().toString()); // modified time
                
               

            }

            // auto size column
            HSSFRow row = firstSheet.getRow(START_ROW);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
