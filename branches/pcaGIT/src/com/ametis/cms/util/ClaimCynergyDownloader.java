package com.ametis.cms.util;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.MemberClaimDto;
import com.ametis.cms.service.ClaimItemService;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClaimCynergyDownloader {

	public static HSSFWorkbook downloadRawatInap(Collection<Claim> claimList,ClaimItemService claimItemService) {
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

            Iterator<Claim> claimIterator = claimList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("INVOICE NUMBER");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("CLAIM NUMBER");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell((short) 2);
            claimNoHCell.setCellValue("DATE RECEIVED");
            claimNoHCell.setCellStyle(style);



            HSSFCell admissionHCell = claimHeader.createCell((short) 3);
            admissionHCell.setCellValue("DATE OF PAID");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 4);
            benefitHCell.setCellValue("NIK");
            benefitHCell.setCellStyle(style);


            HSSFCell incuredHCell = claimHeader.createCell((short) 5);
            incuredHCell.setCellValue("MEMBER ID");
            incuredHCell.setCellStyle(style);

            HSSFCell paidHCell = claimHeader.createCell((short) 6);
            paidHCell.setCellValue("EMPLOYEE NAME");
            paidHCell.setCellStyle(style);

            HSSFCell diagnoseHCell = claimHeader.createCell((short) 7);
            diagnoseHCell.setCellValue("MEMBER NAME");
            diagnoseHCell.setCellStyle(style);

            HSSFCell remarkHCell = claimHeader.createCell((short) 8);
            remarkHCell.setCellValue("RELATIONSHIP");
            remarkHCell.setCellStyle(style);

            HSSFCell sexHCell = claimHeader.createCell((short) 9);
            sexHCell.setCellValue("SEX");
            sexHCell.setCellStyle(style);
            
            HSSFCell planHCell = claimHeader.createCell((short) 10);
            planHCell.setCellValue("PLAN");
            planHCell.setCellStyle(style);
            
            HSSFCell dobHCell = claimHeader.createCell((short) 11);
            dobHCell.setCellValue("DATE OF BIRTH");
            dobHCell.setCellStyle(style);
            
            HSSFCell branchHCell = claimHeader.createCell((short) 12);
            branchHCell.setCellValue("BRANCH");
            branchHCell.setCellStyle(style);
            
            HSSFCell areaHCell = claimHeader.createCell((short) 13);
            areaHCell.setCellValue("AREA");
            areaHCell.setCellStyle(style);
            
            HSSFCell benCodeHCell = claimHeader.createCell((short) 14);
            benCodeHCell.setCellValue("BENEFIT CODE");
            benCodeHCell.setCellStyle(style);
            
            HSSFCell providerHCell = claimHeader.createCell((short) 15);
            providerHCell.setCellValue("PROVIDER NAME");
            providerHCell.setCellStyle(style);
            
            HSSFCell billedHCell = claimHeader.createCell((short) 16);
            billedHCell.setCellValue("BILLED");
            billedHCell.setCellStyle(style);

            
            HSSFCell netAmountHCell = claimHeader.createCell((short) 17);
            netAmountHCell.setCellValue("NET AMOUNT");
            netAmountHCell.setCellStyle(style);
            
            HSSFCell doctorFeeHCell = claimHeader.createCell((short) 18);
            doctorFeeHCell.setCellValue("DOCTOR FEE");
            doctorFeeHCell.setCellStyle(style);
            
            HSSFCell drugsHCell = claimHeader.createCell((short) 19);
            drugsHCell.setCellValue("DRUGS");
            drugsHCell.setCellStyle(style);
            
            HSSFCell taxHCell = claimHeader.createCell((short) 20);
            taxHCell.setCellValue("TAX");
            taxHCell.setCellStyle(style);
            
            HSSFCell excessHCell = claimHeader.createCell((short) 21);
            excessHCell.setCellValue("EXCESS");
            excessHCell.setCellStyle(style);
            
            
            HSSFCell remarksHCell = claimHeader.createCell((short) 22);
            remarksHCell.setCellValue("REMARKS");
            remarksHCell.setCellStyle(style);
            
            HSSFCell dateFromHCell = claimHeader.createCell((short) 23);
            dateFromHCell.setCellValue("DATE SERVICE FROM");
            dateFromHCell.setCellStyle(style);
            
            HSSFCell dateToHCell = claimHeader.createCell((short) 24);
            dateToHCell.setCellValue("DATE SERVICE TO");
            dateToHCell.setCellStyle(style);
            
            HSSFCell diagHCell = claimHeader.createCell((short) 25);
            diagHCell.setCellValue("DIAGNOSIS");
            diagHCell.setCellStyle(style);
            
            HSSFCell ctypeHCell = claimHeader.createCell((short) 26);
            ctypeHCell.setCellValue("CLAIM TYPE");
            ctypeHCell.setCellStyle(style);
            
            rowNumber += 1;
            while (claimIterator.hasNext()) {

                Claim claim = claimIterator.next();

                double claimChargeValue = 0;
                double claimApprovedValue = 0;

                rowNumber += 1;

                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                
                claimMember.createCell((short) 0).setCellValue(claim.getBatchClaimId().getInvoiceNumber());
                claimMember.createCell((short) 1).setCellValue(claim.getClaimNumber());
                claimMember.createCell((short) 2).setCellValue(df.format(claim.getBatchClaimId().getBatchClaimDate()) );

                String paidDate = "";
                if (claim.getBatchClaimId().getPaymentDate() != null){
                	paidDate = df.format(claim.getBatchClaimId().getPaymentDate());
                }
                claimMember.createCell((short) 3).setCellValue(paidDate);
                claimMember.createCell((short) 4).setCellValue(claim.getMemberId().getParentMember().getCustomerNumber());
                claimMember.createCell((short) 5).setCellValue(claim.getMemberId().getCustomerPolicyNumber());
                claimMember.createCell((short) 6).setCellValue(claim.getMemberId().getParentMember().getFirstName());
                claimMember.createCell((short) 7).setCellValue(claim.getMemberId().getFirstName());
                claimMember.createCell((short) 8).setCellValue(claim.getMemberId().getRelationshipId().getRelationshipName());
                claimMember.createCell((short) 9).setCellValue(claim.getMemberId().getGender());
                claimMember.createCell((short) 10).setCellValue(claim.getPlan());
                

                String dob = "";
                if (claim.getMemberId().getBirthday() != null){
                	dob = df.format(claim.getMemberId().getBirthday());
                }
                claimMember.createCell((short) 11).setCellValue(dob);

                claimMember.createCell((short) 12).setCellValue(claim.getDepartment());
                claimMember.createCell((short) 13).setCellValue(claim.getProviderArea());
                
                claimMember.createCell((short) 14).setCellValue(claim.getCaseCategoryId().getCaseCategoryName());
                claimMember.createCell((short) 15).setCellValue(claim.getProviderId().getProviderName());
                
                claimMember.createCell((short) 16).setCellValue(claim.getClaimChargeValue());
                claimMember.createCell((short) 17).setCellValue(claim.getClaimChargeValue());
                
                Collection<ClaimItem> claimItemList = claimItemService.getClaimItem(claim.getClaimId());
                
                Double doctorFee = 0.0;
                Double drugsFee = claim.getClaimApprovedValue();
                Double tax = 0.0;
                String remarks = "";
                
                claimMember.createCell((short) 18).setCellValue(doctorFee);
                claimMember.createCell((short) 19).setCellValue(drugsFee);
                
                claimMember.createCell((short) 20).setCellValue(tax);
                claimMember.createCell((short) 21).setCellValue(claim.getClaimExcessValue());
                claimMember.createCell((short) 22).setCellValue(remarks);
                claimMember.createCell((short) 23).setCellValue(claim.getAdmissionDate());
                claimMember.createCell((short) 24).setCellValue(claim.getDischargeDate());
                claimMember.createCell((short) 25).setCellValue(claim.getDiagnosisId().getDescription());
                claimMember.createCell((short) 26).setCellValue(claim.getClaimTypeId().getClaimTypeName());
                


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
	}
	public static HSSFWorkbook downloadReport(Collection<Claim> claimList,ClaimItemService claimItemService) {
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

            Iterator<Claim> claimIterator = claimList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("INVOICE NUMBER");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("CLAIM NUMBER");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell((short) 2);
            claimNoHCell.setCellValue("DATE RECEIVED");
            claimNoHCell.setCellStyle(style);



            HSSFCell admissionHCell = claimHeader.createCell((short) 3);
            admissionHCell.setCellValue("DATE OF PAID");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 4);
            benefitHCell.setCellValue("NIK");
            benefitHCell.setCellStyle(style);


            HSSFCell incuredHCell = claimHeader.createCell((short) 5);
            incuredHCell.setCellValue("MEMBER ID");
            incuredHCell.setCellStyle(style);

            HSSFCell paidHCell = claimHeader.createCell((short) 6);
            paidHCell.setCellValue("EMPLOYEE NAME");
            paidHCell.setCellStyle(style);

            HSSFCell diagnoseHCell = claimHeader.createCell((short) 7);
            diagnoseHCell.setCellValue("MEMBER NAME");
            diagnoseHCell.setCellStyle(style);

            HSSFCell remarkHCell = claimHeader.createCell((short) 8);
            remarkHCell.setCellValue("RELATIONSHIP");
            remarkHCell.setCellStyle(style);

            HSSFCell sexHCell = claimHeader.createCell((short) 9);
            sexHCell.setCellValue("SEX");
            sexHCell.setCellStyle(style);
            
            HSSFCell planHCell = claimHeader.createCell((short) 10);
            planHCell.setCellValue("PLAN");
            planHCell.setCellStyle(style);
            
            HSSFCell dobHCell = claimHeader.createCell((short) 11);
            dobHCell.setCellValue("DATE OF BIRTH");
            dobHCell.setCellStyle(style);
            
            HSSFCell branchHCell = claimHeader.createCell((short) 12);
            branchHCell.setCellValue("BRANCH");
            branchHCell.setCellStyle(style);
            
            HSSFCell areaHCell = claimHeader.createCell((short) 13);
            areaHCell.setCellValue("AREA");
            areaHCell.setCellStyle(style);
            
            HSSFCell benCodeHCell = claimHeader.createCell((short) 14);
            benCodeHCell.setCellValue("BENEFIT CODE");
            benCodeHCell.setCellStyle(style);
            
            HSSFCell providerHCell = claimHeader.createCell((short) 15);
            providerHCell.setCellValue("PROVIDER NAME");
            providerHCell.setCellStyle(style);
            
            HSSFCell billedHCell = claimHeader.createCell((short) 16);
            billedHCell.setCellValue("BILLED");
            billedHCell.setCellStyle(style);

            
            HSSFCell netAmountHCell = claimHeader.createCell((short) 17);
            netAmountHCell.setCellValue("NET AMOUNT");
            netAmountHCell.setCellStyle(style);
            
                        
            HSSFCell excessHCell = claimHeader.createCell((short) 18);
            excessHCell.setCellValue("EXCESS");
            excessHCell.setCellStyle(style);
            
            
            HSSFCell remarksHCell = claimHeader.createCell((short) 19);
            remarksHCell.setCellValue("REMARKS");
            remarksHCell.setCellStyle(style);
            
            HSSFCell dateFromHCell = claimHeader.createCell((short) 20);
            dateFromHCell.setCellValue("DATE SERVICE FROM");
            dateFromHCell.setCellStyle(style);
            
            HSSFCell dateToHCell = claimHeader.createCell((short) 21);
            dateToHCell.setCellValue("DATE SERVICE TO");
            dateToHCell.setCellStyle(style);
            
            HSSFCell diagHCell = claimHeader.createCell((short) 22);
            diagHCell.setCellValue("DIAGNOSIS");
            diagHCell.setCellStyle(style);
            
            HSSFCell ctypeHCell = claimHeader.createCell((short) 23);
            ctypeHCell.setCellValue("CLAIM TYPE");
            ctypeHCell.setCellStyle(style);
            
            rowNumber += 1;
            while (claimIterator.hasNext()) {

                Claim claim = claimIterator.next();

                double claimChargeValue = 0;
                double claimApprovedValue = 0;

                rowNumber += 1;

                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                String invoiceNumber = "";
                String dateReceived = "";
                String paidDate = "";
                String admissionDate = "";
                String dischargeDate = "";
                
                if (claim.getBatchClaimId() != null){
                	invoiceNumber = claim.getBatchClaimId().getInvoiceNumber();
                	dateReceived = df.format(claim.getBatchClaimId().getBatchClaimDate());
                	
                	if (claim.getBatchClaimId().getPaymentDate() != null){
                    	paidDate = df.format(claim.getBatchClaimId().getPaymentDate());
                    }
                }
                
                claimMember.createCell((short) 0).setCellValue(invoiceNumber);
                claimMember.createCell((short) 1).setCellValue(claim.getClaimNumber());
                claimMember.createCell((short) 2).setCellValue(dateReceived );

                
                
                claimMember.createCell((short) 3).setCellValue(paidDate);
                claimMember.createCell((short) 4).setCellValue(claim.getMemberId().getParentMember().getCustomerNumber());
                claimMember.createCell((short) 5).setCellValue(claim.getMemberId().getCustomerPolicyNumber());
                claimMember.createCell((short) 6).setCellValue(claim.getMemberId().getParentMember().getFirstName());
                claimMember.createCell((short) 7).setCellValue(claim.getMemberId().getFirstName());
                claimMember.createCell((short) 8).setCellValue(claim.getMemberId().getRelationship());
                claimMember.createCell((short) 9).setCellValue(claim.getMemberId().getGender());
                claimMember.createCell((short) 10).setCellValue(claim.getPlan());
                

                String dob = "";
                if (claim.getMemberId().getBirthday() != null){
                	dob = df.format(claim.getMemberId().getBirthday());
                }
                claimMember.createCell((short) 11).setCellValue(dob);

                claimMember.createCell((short) 12).setCellValue(claim.getDepartment());
                claimMember.createCell((short) 13).setCellValue(claim.getProviderArea());
                
                claimMember.createCell((short) 14).setCellValue(claim.getCaseCategoryId().getCaseCategoryName());
                claimMember.createCell((short) 15).setCellValue(claim.getProviderId().getProviderName());
                
                claimMember.createCell((short) 16).setCellValue(claim.getClaimChargeValue());
                claimMember.createCell((short) 17).setCellValue(claim.getClaimApprovedValue());
                
                Collection<ClaimItem> claimItemList = claimItemService.getClaimItem(claim.getClaimId());
                
                Double doctorFee = 0.0;
                Double drugsFee = claim.getClaimApprovedValue();
                Double tax = 0.0;
                String remarks = "";      
                admissionDate = df.format(claim.getAdmissionDate());
                dischargeDate = df.format(claim.getDischargeDate());
                
                claimMember.createCell((short) 18).setCellValue(claim.getClaimExcessValue());
                claimMember.createCell((short) 19).setCellValue(remarks);
                claimMember.createCell((short) 20).setCellValue(admissionDate);
                claimMember.createCell((short) 21).setCellValue(dischargeDate);
                claimMember.createCell((short) 22).setCellValue(claim.getDiagnosis1Code());
                claimMember.createCell((short) 23).setCellValue(claim.getClaimTypeId().getClaimTypeName());
                


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
	}

    public static HSSFWorkbook downloadRawatJalan(Collection<Claim> claimList,ClaimItemService claimItemService) {
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

            Iterator<Claim> claimIterator = claimList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("INVOICE NUMBER");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("CLAIM NUMBER");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell((short) 2);
            claimNoHCell.setCellValue("DATE RECEIVED");
            claimNoHCell.setCellStyle(style);



            HSSFCell admissionHCell = claimHeader.createCell((short) 3);
            admissionHCell.setCellValue("DATE OF PAID");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 4);
            benefitHCell.setCellValue("NIK");
            benefitHCell.setCellStyle(style);


            HSSFCell incuredHCell = claimHeader.createCell((short) 5);
            incuredHCell.setCellValue("MEMBER ID");
            incuredHCell.setCellStyle(style);

            HSSFCell paidHCell = claimHeader.createCell((short) 6);
            paidHCell.setCellValue("EMPLOYEE NAME");
            paidHCell.setCellStyle(style);

            HSSFCell diagnoseHCell = claimHeader.createCell((short) 7);
            diagnoseHCell.setCellValue("MEMBER NAME");
            diagnoseHCell.setCellStyle(style);

            HSSFCell remarkHCell = claimHeader.createCell((short) 8);
            remarkHCell.setCellValue("RELATIONSHIP");
            remarkHCell.setCellStyle(style);

            HSSFCell sexHCell = claimHeader.createCell((short) 9);
            sexHCell.setCellValue("SEX");
            sexHCell.setCellStyle(style);
            
            HSSFCell planHCell = claimHeader.createCell((short) 10);
            planHCell.setCellValue("PLAN");
            planHCell.setCellStyle(style);
            
            HSSFCell dobHCell = claimHeader.createCell((short) 11);
            dobHCell.setCellValue("DATE OF BIRTH");
            dobHCell.setCellStyle(style);
            
            HSSFCell branchHCell = claimHeader.createCell((short) 12);
            branchHCell.setCellValue("BRANCH");
            branchHCell.setCellStyle(style);
            
            HSSFCell areaHCell = claimHeader.createCell((short) 13);
            areaHCell.setCellValue("AREA");
            areaHCell.setCellStyle(style);
            
            HSSFCell benCodeHCell = claimHeader.createCell((short) 14);
            benCodeHCell.setCellValue("BENEFIT CODE");
            benCodeHCell.setCellStyle(style);
            
            HSSFCell providerHCell = claimHeader.createCell((short) 15);
            providerHCell.setCellValue("PROVIDER NAME");
            providerHCell.setCellStyle(style);
            
            HSSFCell billedHCell = claimHeader.createCell((short) 16);
            billedHCell.setCellValue("BILLED");
            billedHCell.setCellStyle(style);

            
            HSSFCell netAmountHCell = claimHeader.createCell((short) 17);
            netAmountHCell.setCellValue("NET AMOUNT");
            netAmountHCell.setCellStyle(style);
            
            HSSFCell doctorFeeHCell = claimHeader.createCell((short) 18);
            doctorFeeHCell.setCellValue("DOCTOR FEE");
            doctorFeeHCell.setCellStyle(style);
            
            HSSFCell drugsHCell = claimHeader.createCell((short) 19);
            drugsHCell.setCellValue("DRUGS");
            drugsHCell.setCellStyle(style);
            
            HSSFCell taxHCell = claimHeader.createCell((short) 20);
            taxHCell.setCellValue("TAX");
            taxHCell.setCellStyle(style);
            
            HSSFCell excessHCell = claimHeader.createCell((short) 21);
            excessHCell.setCellValue("EXCESS");
            excessHCell.setCellStyle(style);
            
            
            HSSFCell remarksHCell = claimHeader.createCell((short) 22);
            remarksHCell.setCellValue("REMARKS");
            remarksHCell.setCellStyle(style);
            
            HSSFCell dateFromHCell = claimHeader.createCell((short) 23);
            dateFromHCell.setCellValue("DATE SERVICE FROM");
            dateFromHCell.setCellStyle(style);
            
            HSSFCell dateToHCell = claimHeader.createCell((short) 24);
            dateToHCell.setCellValue("DATE SERVICE TO");
            dateToHCell.setCellStyle(style);
            
            HSSFCell diagHCell = claimHeader.createCell((short) 25);
            diagHCell.setCellValue("DIAGNOSIS");
            diagHCell.setCellStyle(style);
            
            HSSFCell ctypeHCell = claimHeader.createCell((short) 26);
            ctypeHCell.setCellValue("CLAIM TYPE");
            ctypeHCell.setCellStyle(style);
            
            while (claimIterator.hasNext()) {

                Claim claim = claimIterator.next();

                double claimChargeValue = 0;
                double claimApprovedValue = 0;

                rowNumber += 1;

                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                
                claimMember.createCell((short) 0).setCellValue(claim.getBatchClaimId().getInvoiceNumber());
                claimMember.createCell((short) 1).setCellValue(claim.getClaimNumber());
                claimMember.createCell((short) 2).setCellValue(claim.getBatchClaimId().getBatchClaimDate());

                claimMember.createCell((short) 3).setCellValue(claim.getBatchClaimId().getPaymentDate());
                claimMember.createCell((short) 4).setCellValue(claim.getMemberId().getParentMember().getCustomerNumber());
                claimMember.createCell((short) 5).setCellValue(claim.getMemberId().getCustomerPolicyNumber());
                claimMember.createCell((short) 6).setCellValue(claim.getMemberId().getParentMember().getFirstName());
                claimMember.createCell((short) 7).setCellValue(claim.getMemberId().getFirstName());
                claimMember.createCell((short) 8).setCellValue(claim.getMemberId().getRelationshipId().getRelationshipName());
                claimMember.createCell((short) 9).setCellValue(claim.getMemberId().getGender());
                claimMember.createCell((short) 10).setCellValue(claim.getPlan());
                claimMember.createCell((short) 11).setCellValue(claim.getDepartment());
                claimMember.createCell((short) 12).setCellValue(claim.getProviderArea());
                
                claimMember.createCell((short) 13).setCellValue(claim.getCaseCategoryId().getCaseCategoryName());
                claimMember.createCell((short) 14).setCellValue(claim.getProviderId().getProviderName());
                
                claimMember.createCell((short) 15).setCellValue(claim.getClaimChargeValue());
                claimMember.createCell((short) 16).setCellValue(claim.getClaimChargeValue());
                
                Double doctorFee = 0.0;
                Double drugsFee = 0.0;
                Double tax = 0.0;
                String remarks = "";
                
                claimMember.createCell((short) 17).setCellValue(doctorFee);
                claimMember.createCell((short) 18).setCellValue(drugsFee);
                
                claimMember.createCell((short) 19).setCellValue(tax);
                claimMember.createCell((short) 20).setCellValue(claim.getClaimExcessValue());
                claimMember.createCell((short) 21).setCellValue(remarks);
                claimMember.createCell((short) 22).setCellValue(claim.getAdmissionDate());
                claimMember.createCell((short) 23).setCellValue(claim.getDischargeDate());
                claimMember.createCell((short) 24).setCellValue(claim.getDiagnosisId().getDescription());
                claimMember.createCell((short) 25).setCellValue(claim.getClaimTypeId().getClaimTypeName());
                


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
}
