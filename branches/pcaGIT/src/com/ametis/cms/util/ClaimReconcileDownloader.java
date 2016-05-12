package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;
import com.ametis.cms.service.ClaimItemService;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ClaimReconcileDownloader {

    public static HSSFWorkbook downloadReport(Collection<Claim> claimList, Integer[] holiday) throws Exception {
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

            
            
            
           /* HSSFCell paidCell = claimHeader.createCell(13);
            paidCell.setCellValue("Paid");
            paidCell.setCellStyle(style);
           
            HSSFCell pembayaranDimukaCell = claimHeader.createCell(12);
            pembayaranDimukaCell.setCellValue("Pembayaran Dimuka");
            pembayaranDimukaCell.setCellStyle(style);
            
            
            HSSFCell jmlhSLACell = claimHeader.createCell(28);
            jmlhSLACell.setCellValue("SLA");
            jmlhSLACell.setCellStyle(style);*/
            
            
            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimPaidValue = 0.0;
            Double totalClaimExcessValue = 0.0;
            Double totalRefundValue = 0.0;

            for (Claim claim : claimList) {

                rowNumber += 1;
                HSSFRow claimRow = firstSheet.createRow(rowNumber);

                claimRow.createCell(0).setCellValue(rowNumber);
                                
                claimRow.createCell(1).setCellValue(claim.getClaimDate().toString() == null? "" : claim.getClaimDate().toString()); // receive date
                
                Payment payment = claim.getPaymentId();
                
                String paymentTime = "";
                if(payment != null){
                	paymentTime = (String) (paymentTime == null ? "" : payment.getPaymentTime().toString()) ; 
                }
                
                claimRow.createCell(2).setCellValue(paymentTime); // submission date
                

                BatchClaim batchClaim = claim.getBatchClaimId();
                String batchClaimNumber = "";
                if (batchClaim != null) {
                    batchClaimNumber = (batchClaim == null ? "" : batchClaim.getBatchClaimNumber());
                }
                claimRow.createCell(3).setCellValue(batchClaimNumber); // number batch
                
                claimRow.createCell(4).setCellValue(claim.getClaimNumber()); // claim number
                
                Member member = claim.getMemberId();
                claimRow.createCell(5).setCellValue(member == null ? "" : member.getFirstName()); // member firstname
                
                claimRow.createCell(6).setCellValue(claim.getMemberId().getParentMember().getFirstName()); // employee name
                
                claimRow.createCell(7).setCellValue(claim.getCardNumber()); // card number
                
                String clientName = "";
                
                if (batchClaim != null) {
                    Client client = batchClaim.getClientId();
                    clientName = (client == null ? "" : client.getClientName());
                }

                claimRow.createCell(8).setCellValue(clientName); // client name

                claimRow.createCell(9).setCellValue(claim.getGroupName()); // group name
                
                claimRow.createCell(10).setCellValue(claim.getAdmissionDate() == null ? "" : claim.getAdmissionDate().toString()); // admission date
                
                claimRow.createCell(11).setCellValue(claim.getDischargeDate() == null ? "" : claim.getDischargeDate().toString()); // discharge date
                
                claimRow.createCell(12).setCellValue(claim.getProviderName()); // provider name
                
                Diagnosis diagnosis = claim.getDiagnosisId();
                claimRow.createCell(13).setCellValue(diagnosis == null ? "" : diagnosis.getDiagnosisCode()); // diagnosis

                Double claimChargeValue = claim.getClaimChargeValue() == null ? 0.0 : claim.getClaimChargeValue();
                claimRow.createCell(14).setCellValue(claimChargeValue); // charges
                totalClaimChargeValue += claimChargeValue;

                Double claimApprovedValue = claim.getClaimApprovedValue() == null ? 0.0 : claim.getClaimApprovedValue();
                claimRow.createCell(15).setCellValue(claimApprovedValue); // approved
                totalClaimApprovedValue += claimApprovedValue;

                Double prePaidExcessValue = claim.getPrePaidExcess() == null ? 0.0 : claim.getPrePaidExcess();
                claimRow.createCell(16).setCellValue(prePaidExcessValue); // prePaidExcess;

                Double claimExcessValue = claim.getClaimExcessValue() == null ? 0.0 : claim.getClaimExcessValue();
                claimRow.createCell(17).setCellValue(claimExcessValue); // excess
                totalClaimExcessValue += claimExcessValue;
                
                Double refundValue = claim.getRefund() == null ? 0.0 : claim.getRefund();
                claimRow.createCell(18).setCellValue(refundValue); // refund
                totalRefundValue += refundValue;
                
                Double paidToProviderValue = claim.getPaidToProvider() == null ? 0.0 : claim.getPaidToProvider();
                claimRow.createCell(19).setCellValue(paidToProviderValue); // paid to provider
                
                ClaimType claimType = claim.getClaimTypeId();
                claimRow.createCell(20).setCellValue(claimType == null ? "" : claimType.getClaimTypeCode()); // type

                CaseCategory caseCategory = claim.getCaseCategoryId();
                claimRow.createCell(21).setCellValue(caseCategory == null ? "" : caseCategory.getCaseCategoryCode()); // service

                CaseStatus caseStatus = claim.getClaimStatus();
                claimRow.createCell(22).setCellValue(caseStatus == null ? "" : caseStatus.getCaseStatusName()); // status
                
                String remarks = "";
                if (caseStatus != null && caseStatus.getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {
                    remarks = claim.getRejectionRemarks();
                } else {
                    remarks = claim.getClaimRemarks();
                }
                claimRow.createCell(23).setCellValue(remarks); // alasan

                claimRow.createCell(24).setCellValue(claim.getModifiedBy()); // modified by

                claimRow.createCell(25).setCellValue(claim.getModifiedTime() == null ? "" : claim.getModifiedTime().toString()); // modified time
                
               
/*
                Double pembayaranDimukaValue = claim.getPembayaranDimuka() == null ? 0.0 : claim.getPembayaranDimuka();
                claimRow.createCell(12).setCellValue(pembayaranDimukaValue); // pembayaranDimuka;
                
                Double claimPaidValue = claim.getClaimPaidValue() == null ? 0.0 : claim.getClaimPaidValue();
                claimRow.createCell(13).setCellValue(claimPaidValue); // paid
                totalClaimPaidValue += claimPaidValue;

                
                int jmlhSLA = 1 ;
                
                System.out.println("claim.getClaimDate() = " + claim.getClaimDate());
                
                if(payment != null){
                	System.out.println("claim.getPaymentId().getPaymentTime() = " + payment.getPaymentTime());
                	if (claim.getClaimDate() != null & payment.getPaymentTime() != null) {
                		jmlhSLA = getWorkingDaysBetweenTwoDates(claim.getClaimDate(), claim.getPaymentId().getPaymentTime());
                	}
            	}else{
            		jmlhSLA = 0 ;
            	}
                
                if(holiday[rowNumber-1] == null){
                	holiday[rowNumber-1] = 0;
                }
                int hariKerja =  jmlhSLA - holiday[rowNumber-1];
//              	
                claimRow.createCell(28).setCellValue(hariKerja + " Hari"); // SLA
*/
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

            HSSFCell totalClaimChargeCell = jumlahRow.createCell(14);
            totalClaimChargeCell.setCellValue(totalClaimChargeValue);
            totalClaimChargeCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimApprovedCell = jumlahRow.createCell(15);
            totalClaimApprovedCell.setCellValue(totalClaimApprovedValue);
            totalClaimApprovedCell.setCellStyle(jumlahStyle);

           /* HSSFCell totalClaimPaidCell = jumlahRow.createCell(13);
            totalClaimPaidCell.setCellValue(totalClaimPaidValue);
            totalClaimPaidCell.setCellStyle(jumlahStyle);*/

            HSSFCell totalClaimExcessCell = jumlahRow.createCell(17);
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
    
    public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int workDays = 1;
	    
	    

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 1;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
	        	) {
	        
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis() ); //excluding end date

	    return workDays;
	}
}
