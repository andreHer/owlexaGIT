package com.ametis.cms.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

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
import com.ametis.cms.datamodel.ClaimHistory;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Payment;

public class ClaimDownloader {

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

            HSSFCell claimNumberCell = claimHeader.createCell(1);
            claimNumberCell.setCellValue("Claim Number");
            claimNumberCell.setCellStyle(style);

            HSSFCell admissionCell = claimHeader.createCell(2);
            admissionCell.setCellValue("Admission");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = claimHeader.createCell(3);
            dischargeCell.setCellValue("Discharge");
            dischargeCell.setCellStyle(style);

            HSSFCell memberCell = claimHeader.createCell(4);
            memberCell.setCellValue("Member");
            memberCell.setCellStyle(style);

            HSSFCell cardNumberCell = claimHeader.createCell(5);
            cardNumberCell.setCellValue("Card Number");
            cardNumberCell.setCellStyle(style);

            HSSFCell clientNameCell = claimHeader.createCell(6);
            clientNameCell.setCellValue("Client Name");
            clientNameCell.setCellStyle(style);

            HSSFCell groupNameCell = claimHeader.createCell(7);
            groupNameCell.setCellValue("Group Name");
            groupNameCell.setCellStyle(style);

            HSSFCell providerNameCell = claimHeader.createCell(8);
            providerNameCell.setCellValue("Provider Name");
            providerNameCell.setCellStyle(style);
			
			HSSFCell invoiceNumber = claimHeader.createCell(9); //add adq 26082015
            invoiceNumber.setCellValue("Invoice Number");
            invoiceNumber.setCellStyle(style);

            HSSFCell chargesCell = claimHeader.createCell(10);
            chargesCell.setCellValue("Charges");
            chargesCell.setCellStyle(style);

            HSSFCell approvedCell = claimHeader.createCell(11);
            approvedCell.setCellValue("Approved");
            approvedCell.setCellStyle(style);
            
            HSSFCell prePaidExcessCell = claimHeader.createCell(12);
            prePaidExcessCell.setCellValue("Pre Paid Excess");
            prePaidExcessCell.setCellStyle(style);

            HSSFCell pembayaranDimukaCell = claimHeader.createCell(13);
            pembayaranDimukaCell.setCellValue("Pembayaran Dimuka");
            pembayaranDimukaCell.setCellStyle(style);
            
            HSSFCell paidCell = claimHeader.createCell(14);
            paidCell.setCellValue("Paid");
            paidCell.setCellStyle(style);

            HSSFCell excessCell = claimHeader.createCell(15);
            excessCell.setCellValue("Excess");
            excessCell.setCellStyle(style);

            HSSFCell refundCell = claimHeader.createCell(16);
            refundCell.setCellValue("Refund");
            refundCell.setCellStyle(style);
            
            HSSFCell paidToProviderCell = claimHeader.createCell(17);
            paidToProviderCell.setCellValue("Paid to Provider");
            paidToProviderCell.setCellStyle(style);
            
            HSSFCell typeCell = claimHeader.createCell(18);
            typeCell.setCellValue("Type");
            typeCell.setCellStyle(style);

            HSSFCell serviceCell = claimHeader.createCell(19);
            serviceCell.setCellValue("Service");
            serviceCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(20);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell diagnosisCell = claimHeader.createCell(21);
            diagnosisCell.setCellValue("Diagnosis");
            diagnosisCell.setCellStyle(style);

            HSSFCell claimRemarksCell = claimHeader.createCell(22);
            claimRemarksCell.setCellValue("Claim Remarks");
            claimRemarksCell.setCellStyle(style);

            HSSFCell officerCell = claimHeader.createCell(23);
            officerCell.setCellValue("Officer");
            officerCell.setCellStyle(style);

            HSSFCell modifiedTimeCell = claimHeader.createCell(24);
            modifiedTimeCell.setCellValue("Modified Time");
            modifiedTimeCell.setCellStyle(style);

            HSSFCell employeeNameCell = claimHeader.createCell(25);
            employeeNameCell.setCellValue("Employee Name");
            employeeNameCell.setCellStyle(style);
            
            HSSFCell numberBatchCell = claimHeader.createCell(26);
            numberBatchCell.setCellValue("Number Batch");
            numberBatchCell.setCellStyle(style);
            
            HSSFCell receiveDateCell = claimHeader.createCell(27);
            receiveDateCell.setCellValue("Receive Date");
            receiveDateCell.setCellStyle(style);
            
            HSSFCell submissionCell = claimHeader.createCell(28);
            submissionCell.setCellValue("Submission Date");
            submissionCell.setCellStyle(style);
            
            HSSFCell jmlhSLACell = claimHeader.createCell(29);
            jmlhSLACell.setCellValue("SLA");
            jmlhSLACell.setCellStyle(style);
            
            
            HSSFCell employeeNumberCell = claimHeader.createCell(30);
            employeeNumberCell.setCellValue("Employee ID Number");
            employeeNumberCell.setCellStyle(style);
            
            HSSFCell departmentCell = claimHeader.createCell(31);
            departmentCell.setCellValue("Department");
            departmentCell.setCellStyle(style);
            
            HSSFCell costCenterCell = claimHeader.createCell(32);
            costCenterCell.setCellValue("Cost Center/Other Number");
            costCenterCell.setCellStyle(style);
            
            HSSFCell bankCell = claimHeader.createCell(33);
            bankCell.setCellValue("Bank");
            bankCell.setCellStyle(style);
            
            HSSFCell bankAccountCell = claimHeader.createCell(34);
            bankAccountCell.setCellValue("Bank Account");
            bankAccountCell.setCellStyle(style);
            
            HSSFCell accountNameCell = claimHeader.createCell(35);
            accountNameCell.setCellValue("Account Name");
            accountNameCell.setCellStyle(style);
            
            HSSFCell paymentRemarksCell = claimHeader.createCell(36);
            paymentRemarksCell.setCellValue("Payment Remarks");
            paymentRemarksCell.setCellStyle(style);
            
            Double totalClaimChargeValue = 0.0;
            Double totalClaimApprovedValue = 0.0;
            Double totalClaimPaidValue = 0.0;
            Double totalClaimExcessValue = 0.0;
            Double totalRefundValue = 0.0;

            for (Claim claim : claimList) {

                rowNumber += 1;
                HSSFRow claimRow = firstSheet.createRow(rowNumber);

                claimRow.createCell(0).setCellValue(rowNumber);
                claimRow.createCell(1).setCellValue(claim.getClaimNumber()); // claim number
                claimRow.createCell(2).setCellValue(claim.getAdmissionDate() == null ? "" : claim.getAdmissionDate().toString()); // admission date
                claimRow.createCell(3).setCellValue(claim.getDischargeDate() == null ? "" : claim.getDischargeDate().toString()); // discharge date

                Member member = claim.getMemberId();
                claimRow.createCell(4).setCellValue(member == null ? "" : member.getFirstName()); // member firstname

                claimRow.createCell(5).setCellValue(claim.getCardNumber()); // card number

                String clientName = "";
                BatchClaim batchClaim = claim.getBatchClaimId();
                if (batchClaim != null) {
                    Client client = batchClaim.getClientId();
                    clientName = (client == null ? "" : client.getClientName());
                }

                claimRow.createCell(6).setCellValue(clientName); // client name

                claimRow.createCell(7).setCellValue(claim.getGroupName()); // group name

                claimRow.createCell(8).setCellValue(claim.getProviderName()); // provider name
				
                
				claimRow.createCell(9).setCellValue(batchClaim == null ? "" : batchClaim.getInvoiceNumber()); // Invoice Number adq

                Double claimChargeValue = claim.getClaimChargeValue() == null ? 0.0 : claim.getClaimChargeValue();
                claimRow.createCell(10).setCellValue(claimChargeValue); // charges
                totalClaimChargeValue += claimChargeValue;

                Double claimApprovedValue = claim.getClaimApprovedValue() == null ? 0.0 : claim.getClaimApprovedValue();
                claimRow.createCell(11).setCellValue(claimApprovedValue); // approved
                totalClaimApprovedValue += claimApprovedValue;

                Double prePaidExcessValue = claim.getPrePaidExcess() == null ? 0.0 : claim.getPrePaidExcess();
                claimRow.createCell(12).setCellValue(prePaidExcessValue); // prePaidExcess;

                Double pembayaranDimukaValue = claim.getPembayaranDimuka() == null ? 0.0 : claim.getPembayaranDimuka();
                claimRow.createCell(13).setCellValue(pembayaranDimukaValue); // pembayaranDimuka;

                Double claimPaidValue = claim.getClaimPaidValue() == null ? 0.0 : claim.getClaimPaidValue();
                claimRow.createCell(14).setCellValue(claimPaidValue); // paid
                totalClaimPaidValue += claimPaidValue;

                if (pembayaranDimukaValue != null && pembayaranDimukaValue == 0.0){
	                Double claimExcessValue = claim.getClaimExcessValue() == null ? 0.0 : claim.getClaimExcessValue();
	                claimRow.createCell(15).setCellValue(claimExcessValue); // excess
	                totalClaimExcessValue += claimExcessValue;
                }
                else if (pembayaranDimukaValue != null && pembayaranDimukaValue > 0.0){
                	Double claimExcessValue = claim.getClaimExcessValue() == null ? 0.0 : claim.getClaimExcessValue();
	                claimRow.createCell(15).setCellValue(claimExcessValue-pembayaranDimukaValue); // excess
	                totalClaimExcessValue += claimExcessValue-pembayaranDimukaValue;
                }
                
                
                Double refundValue = claim.getRefund() == null ? 0.0 : claim.getRefund();
                claimRow.createCell(16).setCellValue(refundValue); // excess
                totalRefundValue += refundValue;
                
                Double paidToProviderValue = claim.getPaidToProvider() == null ? 0.0 : claim.getPaidToProvider();
                claimRow.createCell(17).setCellValue(paidToProviderValue); // excess
                
                ClaimType claimType = claim.getClaimTypeId();
                claimRow.createCell(18).setCellValue(claimType == null ? "" : claimType.getClaimTypeCode()); // type

                CaseCategory caseCategory = claim.getCaseCategoryId();
                claimRow.createCell(19).setCellValue(caseCategory == null ? "" : caseCategory.getCaseCategoryCode()); // service

                CaseStatus caseStatus = claim.getClaimStatus();
                claimRow.createCell(20).setCellValue(caseStatus == null ? "" : caseStatus.getCaseStatusName()); // status

                Diagnosis diagnosis = claim.getDiagnosisId();
                claimRow.createCell(21).setCellValue(diagnosis == null ? "" : diagnosis.getDiagnosisCode()); // diagnosis

                String remarks = "";
                if (caseStatus != null && caseStatus.getCaseStatusId().intValue() == Claim.CLAIM_REJECT) {
                    remarks = claim.getRejectionRemarks();
                } else {
                    remarks = claim.getClaimRemarks();
                }
                claimRow.createCell(22).setCellValue(remarks); // alasan

                claimRow.createCell(23).setCellValue(claim.getModifiedBy()); // modified by

                claimRow.createCell(24).setCellValue(claim.getModifiedTime() == null ? "" : claim.getModifiedTime().toString()); // modified time
                
                String  parentName = "";
                
                if (claim.getMemberId().getParentMember() != null){
                	parentName = claim.getMemberId().getParentMember().getFirstName();
                }
                claimRow.createCell(25).setCellValue(parentName); // employee name
                
                String batchClaimNumber = "";
                if (batchClaim != null) {
                    batchClaimNumber = (batchClaim == null ? "" : batchClaim.getBatchClaimNumber());
                }
                claimRow.createCell(26).setCellValue(batchClaimNumber); // number batch
                
                claimRow.createCell(27).setCellValue(claim.getClaimDate().toString()); 
                
                Payment payment = claim.getPaymentId();
                
                String paymentTime = "";
                if(payment != null){
                	paymentTime = (String) (paymentTime == null ? "" : payment.getPaymentTime().toString()) ; 
                }
                
                claimRow.createCell(28).setCellValue(paymentTime); 
                
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
                claimRow.createCell(29).setCellValue(hariKerja + " Hari");

            
                String nik = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getEmployeeIDNumber();
                
                if (nik != null){
                	if (nik.trim().equalsIgnoreCase("")){
                		nik = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getParentNumber();
                	}
                }
                else {
                	nik = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getParentNumber();
                }
                System.out.println(" NIK = " + nik);
                
                claimRow.createCell(30).setCellValue(nik);

                String department = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getDepartment();
                
                claimRow.createCell(31).setCellValue(department);
                
                String costCenter = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getJobPosition();
                
                if (costCenter != null && costCenter.trim().equalsIgnoreCase("")){
                	if (department != null){
                		String[] split = department.split("-");
                		
                		if (split != null && split.length > 1){
                			costCenter = split[1];
                		}
                	}                	
                }
                
                claimRow.createCell(32).setCellValue(costCenter);
                
                String bank = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getBank();
                claimRow.createCell(33).setCellValue(bank);
                                
                
                String bankAccount = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getBankAccount();
                claimRow.createCell(34).setCellValue(bankAccount);
                
                String bankAccountName = claim.getMemberId().getParentMember() == null ? "" : claim.getMemberId().getParentMember().getBankAccountName();
                claimRow.createCell(35).setCellValue(bankAccountName);
                
                String paymentRemarks = "";
                
                if (claim.getPaymentRemarks() != null){
                	paymentRemarks = claim.getPaymentRemarks();
                }
                else {
                	if (claim.getPaymentId() != null){
                		paymentRemarks = claim.getPaymentId().getRemarks();
                		
                	}
                }
                
                claimRow.createCell(36).setCellValue(paymentRemarks);
                
                
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

            HSSFCell totalClaimPaidCell = jumlahRow.createCell(14);
            totalClaimPaidCell.setCellValue(totalClaimPaidValue);
            totalClaimPaidCell.setCellStyle(jumlahStyle);

            HSSFCell totalClaimExcessCell = jumlahRow.createCell(15);
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
    
    public static HSSFWorkbook downloadClaimActiveReport(Collection<ClaimHistory> claimList) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {
        	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
        	
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

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 0;
            int colNumber = 0;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(colNumber++);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell claimNumberCell = claimHeader.createCell(colNumber++);
            claimNumberCell.setCellValue("Claim Number");
            claimNumberCell.setCellStyle(style);

            HSSFCell memberCellCell = claimHeader.createCell(colNumber++);
            memberCellCell.setCellValue("Member Name");
            memberCellCell.setCellStyle(style);

            HSSFCell claimDateCell = claimHeader.createCell(colNumber++);
            claimDateCell.setCellValue("Claim Date");
            claimDateCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(colNumber++);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell dateStatusCell = claimHeader.createCell(colNumber++);
            dateStatusCell.setCellValue("Date Status");
            dateStatusCell.setCellStyle(style);

            HSSFCell createdByCell = claimHeader.createCell(colNumber++);
            createdByCell.setCellValue("Created By");
            createdByCell.setCellStyle(style);

            HSSFCell durationCell = claimHeader.createCell(colNumber++);
            durationCell.setCellValue("Duration");
            durationCell.setCellStyle(style);


            for (ClaimHistory claim : claimList) {
            	colNumber = 0;
                rowNumber += 1;
                HSSFRow claimRow = firstSheet.createRow(rowNumber);

                claimRow.createCell(colNumber++).setCellValue(rowNumber);
                claimRow.createCell(colNumber++).setCellValue(claim.getClaimNumber());
                claimRow.createCell(colNumber++).setCellValue(claim.getMemberName() == null ? "" : claim.getMemberName());
                claimRow.createCell(colNumber++).setCellValue(claim.getClaimId().getClaimDate() == null ? "" : df.format(claim.getClaimId().getClaimDate())); 
                claimRow.createCell(colNumber++).setCellValue(claim.getClaimStatus().getCaseStatusName() == null ? "" : claim.getClaimStatus().getCaseStatusName()); 

                claimRow.createCell(colNumber++).setCellValue(claim.getActionTime() == null? "" : df.format(claim.getActionTime()));

                claimRow.createCell(colNumber++).setCellValue(claim.getCreatedBy());
                
                if(claim.getDurationString()!=null && claim.getClaimStatus().getCaseStatusId() == 1)
                	claimRow.createCell(colNumber++).setCellValue("");
                else
                	claimRow.createCell(colNumber++).setCellValue(claim.getDurationString());


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
    
    public static HSSFWorkbook downloadClaimAgingReport(Collection<ClaimHistory> claimList) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();

        try {
        	SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
        	
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

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 0;
            int colNumber = 0;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(colNumber++);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell claimNumberCell = claimHeader.createCell(colNumber++);
            claimNumberCell.setCellValue("Claim Number");
            claimNumberCell.setCellStyle(style);

            HSSFCell memberCellCell = claimHeader.createCell(colNumber++);
            memberCellCell.setCellValue("Member Name");
            memberCellCell.setCellStyle(style);

            HSSFCell claimDateCell = claimHeader.createCell(colNumber++);
            claimDateCell.setCellValue("Claim Date");
            claimDateCell.setCellStyle(style);

            HSSFCell statusCell = claimHeader.createCell(colNumber++);
            statusCell.setCellValue("Status");
            statusCell.setCellStyle(style);

            HSSFCell dateStatusCell = claimHeader.createCell(colNumber++);
            dateStatusCell.setCellValue("Date Status");
            dateStatusCell.setCellStyle(style);

            HSSFCell createdByCell = claimHeader.createCell(colNumber++);
            createdByCell.setCellValue("Created By");
            createdByCell.setCellStyle(style);

            HSSFCell durationCell = claimHeader.createCell(colNumber++);
            durationCell.setCellValue("Duration");
            durationCell.setCellStyle(style);


            for (ClaimHistory claim : claimList) {
            	colNumber = 0;
                rowNumber += 1;
                HSSFRow claimRow = firstSheet.createRow(rowNumber);

                claimRow.createCell(colNumber++).setCellValue(rowNumber);
                claimRow.createCell(colNumber++).setCellValue(claim.getClaimNumber());
                claimRow.createCell(colNumber++).setCellValue(claim.getMemberName() == null ? "" : claim.getMemberName());
                claimRow.createCell(colNumber++).setCellValue(claim.getClaimId().getClaimDate() == null ? "" : df.format(claim.getClaimId().getClaimDate())); 
                claimRow.createCell(colNumber++).setCellValue(claim.getClaimStatus().getCaseStatusName() == null ? "" : claim.getClaimStatus().getCaseStatusName()); 

                claimRow.createCell(colNumber++).setCellValue(claim.getActionTime() == null? "" : df.format(claim.getActionTime()));

                claimRow.createCell(colNumber++).setCellValue(claim.getCreatedBy());
                
                if(claim.getDurationString()!=null && claim.getClaimStatus().getCaseStatusId() == 1)
                	claimRow.createCell(colNumber++).setCellValue("");
                else
                	claimRow.createCell(colNumber++).setCellValue(claim.getDurationString());


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
