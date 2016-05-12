package com.ametis.cms.util;

import java.math.BigDecimal;
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
import org.apache.poi.hssf.util.HSSFColor;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.service.ClaimItemService;

public class GeneralClaimDownloader {

	public static HSSFWorkbook downloadReport (Collection<Claim> claimItemList, ClaimItemService claimItemService) throws Exception {
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

            String data = "Currency, Declined Reason, Bank, Bank Account";
            data += "\n\n";


            int previousClaimId = 0;
            boolean isClaimHeaderDone = false;
            
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell( (short)0);
            nameHCell.setCellValue("NO.");
            nameHCell.setCellStyle(style);

            HSSFCell batchNoHCell = claimHeader.createCell( (short) 1);
            batchNoHCell.setCellValue("POLICY HOLDER");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell( (short) 2);
            claimNoHCell.setCellValue("INVOICE NUMBER");
            claimNoHCell.setCellStyle(style);

            HSSFCell admissionHCell = claimHeader.createCell( (short) 3);
            admissionHCell.setCellValue("CLAIM NUMBER");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell( (short)4);
            benefitHCell.setCellValue("PAYMENT NUMBER");
            benefitHCell.setCellStyle(style);


            HSSFCell incuredHCell = claimHeader.createCell( (short)5);
            incuredHCell.setCellValue("MEMBER ID");
            incuredHCell.setCellStyle(style);
            
            HSSFCell cardNumHCell = claimHeader.createCell( (short)6);
            cardNumHCell.setCellValue("CARD NUMBER");
            cardNumHCell.setCellStyle(style);

            HSSFCell paidHCell = claimHeader.createCell( (short)7);
            paidHCell.setCellValue("EMPLOYEE NAME");
            paidHCell.setCellStyle(style);

            HSSFCell diagnoseHCell = claimHeader.createCell( (short)8);
            diagnoseHCell.setCellValue("MEMBER NAME");
            diagnoseHCell.setCellStyle(style);

            HSSFCell remarkHCell = claimHeader.createCell( (short) 9);
            remarkHCell.setCellValue("RELATIONSHIP");
            remarkHCell.setCellStyle(style);

            HSSFCell sexHCell = claimHeader.createCell( (short) 10);
            sexHCell.setCellValue("SEX");
            sexHCell.setCellStyle(style);
            
            HSSFCell dobHCell = claimHeader.createCell( (short) 11);
            dobHCell.setCellValue("DATE OF BIRTH");
            dobHCell.setCellStyle(style);
            
            
            HSSFCell planHCell = claimHeader.createCell( (short)12);
            planHCell.setCellValue("PLAN");
            planHCell.setCellStyle(style);
            
            HSSFCell classHCell = claimHeader.createCell( (short) 13);
            classHCell.setCellValue("CLASS");
            classHCell.setCellStyle(style);
            
            HSSFCell inceptionHCell = claimHeader.createCell( (short) 14);
            inceptionHCell.setCellValue("INCEPTION DATE");
            inceptionHCell.setCellStyle(style);
            
            HSSFCell maturityDateCell = claimHeader.createCell( (short)15);
            maturityDateCell.setCellValue("MATURITY DATE");
            maturityDateCell.setCellStyle(style);
            
            HSSFCell claimDateCell = claimHeader.createCell( (short) 16);
            claimDateCell.setCellValue("DATE CLAIM EVENT");
            claimDateCell.setCellStyle(style);
            
            HSSFCell dischargeCell = claimHeader.createCell( (short)17);
            dischargeCell.setCellValue("DISCHARGE DATE");
            dischargeCell.setCellStyle(style);
            
            HSSFCell receivedCell = claimHeader.createCell( (short)18);
            receivedCell.setCellValue("DATE RECEIVED");
            receivedCell.setCellStyle(style);
            
            HSSFCell cdvDateCell = claimHeader.createCell( (short) 19);
            cdvDateCell.setCellValue("CDV DATE");
            cdvDateCell.setCellStyle(style);
            
            HSSFCell paymentDateCell = claimHeader.createCell( (short) 20);
            paymentDateCell.setCellValue("PAYMENT DATE");
            paymentDateCell.setCellStyle(style);
            
            HSSFCell branchHCell = claimHeader.createCell( (short)21);
            branchHCell.setCellValue("BRANCH");
            branchHCell.setCellStyle(style);
            

            HSSFCell providerHCell = claimHeader.createCell( (short)22);
            providerHCell.setCellValue("PROVIDER NAME");
            providerHCell.setCellStyle(style);
            
            HSSFCell areaHCell = claimHeader.createCell( (short)23);
            areaHCell.setCellValue("AREA");
            areaHCell.setCellStyle(style);
            
            HSSFCell benCodeHCell = claimHeader.createCell( (short)24);
            benCodeHCell.setCellValue("CLAIM TYPE");
            benCodeHCell.setCellStyle(style);
                                   
            HSSFCell netAmountHCell = claimHeader.createCell( (short)25);
            netAmountHCell.setCellValue("REMAINING BENEFIT");
            netAmountHCell.setCellStyle(style);
            
            HSSFCell doctorFeeHCell = claimHeader.createCell( (short)26);
            doctorFeeHCell.setCellValue("BENEFIT CODE");
            doctorFeeHCell.setCellStyle(style);
            
            HSSFCell drugsHCell = claimHeader.createCell( (short)27);
            drugsHCell.setCellValue("DIAGNOSE");
            drugsHCell.setCellStyle(style);
            
            HSSFCell taxHCell = claimHeader.createCell( (short) 28);
            taxHCell.setCellValue("PAYMENT TYPE");
            taxHCell.setCellStyle(style);
            
            HSSFCell chargeHCell = claimHeader.createCell( (short) 29);
            chargeHCell.setCellValue("CHARGES");
            chargeHCell.setCellStyle(style);
            

            HSSFCell approvedHCell = claimHeader.createCell( (short)30);
            approvedHCell.setCellValue("PAID");
            approvedHCell.setCellStyle(style);
            

            HSSFCell declineHCell = claimHeader.createCell( (short)31);
            declineHCell.setCellValue("DECLINE");
            declineHCell.setCellStyle(style);
            

            HSSFCell excessHCell = claimHeader.createCell( (short)32);
            excessHCell.setCellValue("EXCESS CHARGE");
            excessHCell.setCellStyle(style);
            
            
            HSSFCell remarksHCell = claimHeader.createCell( (short)33);
            remarksHCell.setCellValue("REMARKS");
            remarksHCell.setCellStyle(style);
            
            HSSFCell dateFromHCell = claimHeader.createCell( (short)34);
            dateFromHCell.setCellValue("CURRENCY");
            dateFromHCell.setCellStyle(style);
            
            HSSFCell dateToHCell = claimHeader.createCell( (short)35);
            dateToHCell.setCellValue("BANK");
            dateToHCell.setCellStyle(style);
            
            HSSFCell diagHCell = claimHeader.createCell( (short)36);
            diagHCell.setCellValue("BANK ACCOUNT");
            diagHCell.setCellStyle(style);
            
            Iterator<Claim> claimIterator = claimItemList.iterator();
            
            
            rowNumber += 1;
            int idx = 1;

            while (claimIterator.hasNext()) {

                Claim claim = claimIterator.next();
                Collection<ClaimItem> ciList = claimItemService.getClaimItem(claim.getClaimId());

                String cardNumber = "-";
                String receivedDate = "";
                String invNumber = "-";
                String paymentNumber = "-";
                String cdvDate = "";
                String paymentDate = "";
                String remainingBenefit = "-";
                String postApproveBenefit = "-";
                String providerName = "-";
                String effectiveDate = "-";
                String expireDate = "-";
                String admissionDate = "-";
                String dischargeDate = "-";
                String birthdate = "-";
                
                if (claim.getBatchClaimId() != null){
                	invNumber = claim.getBatchClaimId().getInvoiceNumber();
                	paymentNumber = claim.getPaymentNumber();
                	receivedDate = claim.getBatchClaimId().getBatchClaimDate() == null ? "" : claim.getBatchClaimId().getBatchClaimDate().toString();
                	cdvDate = claim.getPaymentGeneratedDate() == null ? "" : claim.getPaymentGeneratedDate().toString();
                	paymentDate = claim.getPaymentConfirmDate() == null ? "" : claim.getPaymentConfirmDate().toString();
                }
                
                if (claim.getMemberId().getBirthday() != null){
                	birthdate = df.format(claim.getMemberId().getBirthday());
                }
                
                if (claim.getRemainingMemberLimit() != null){
                	BigDecimal bc = new BigDecimal(claim.getRemainingMemberLimit());
                	remainingBenefit = bc.toPlainString();
                }
                if (claim.getMemberId().getResignedDate() != null){
                	expireDate = df.format(claim.getMemberId().getResignedDate());
                }
                if (claim.getMemberId().getEffectiveDate() != null){
                	effectiveDate = df.format(claim.getMemberId().getEffectiveDate());
                }
                if (claim.getAdmissionDate() != null){
                	admissionDate = df.format(claim.getAdmissionDate());
                }
                if (claim.getDischargeDate() != null){
                	dischargeDate = df.format(claim.getDischargeDate());
                }
                if (claim.getProviderId() != null){
                	providerName = claim.getProviderId().getProviderName();
                }
                else {
                	providerName = claim.getProviderName();
                }
                if (claim.getClaimId().intValue() != previousClaimId){
                	rowNumber += 1;
                	HSSFRow blankRow = firstSheet.createRow(rowNumber);
                	rowNumber += 1;
                	HSSFRow claimRow = firstSheet.createRow(rowNumber);

                	claimRow.createCell((short)0).setCellValue(idx+".");
                    claimRow.createCell((short)1).setCellValue(claim.getMemberId().getClientName()); 
                    claimRow.createCell((short)2).setCellValue(invNumber); // invoice number
                    claimRow.createCell((short)3).setCellValue(claim.getClaimNumber()); // claim number
                    claimRow.createCell((short)4).setCellValue(paymentNumber); // payment number
                    claimRow.createCell((short)5).setCellValue(claim.getMemberNumber()); // member id
                    claimRow.createCell((short)6).setCellValue(claim.getCardNumber()); // member id
                    claimRow.createCell((short)7).setCellValue((claim.getMemberId().getParentMember()!=null?claim.getMemberId().getParentMember().getFirstName():"")); // employee name
                    claimRow.createCell((short)8).setCellValue(claim.getMemberName()); // member id
                    claimRow.createCell((short)9).setCellValue(claim.getRelationship()); // member id
                    claimRow.createCell((short)10).setCellValue(claim.getSex()); // sex
                    claimRow.createCell((short)11).setCellValue(birthdate); // dob
                    claimRow.createCell((short)12).setCellValue(claim.getPlan()); // plan
                    claimRow.createCell((short)13).setCellValue(claim.getProductClass()); // class
                    claimRow.createCell((short)14).setCellValue(effectiveDate); // inception date
                    claimRow.createCell((short)15).setCellValue(expireDate); // maturity date
                    claimRow.createCell((short)16).setCellValue(admissionDate); // admission date
                    claimRow.createCell((short)17).setCellValue(dischargeDate); // discharge date
                    claimRow.createCell((short)18).setCellValue(receivedDate); // receive date
                    claimRow.createCell((short)19).setCellValue(cdvDate); // cdv date
                    claimRow.createCell((short)20).setCellValue(paymentDate); // payment date
                    //claimRow.createCell((short)20).setCellValue(claim.getMemberId().getParentMember().getDepartment()); // branch 
                    claimRow.createCell((short)21).setCellValue("-"); // branch
                    claimRow.createCell((short)22).setCellValue(providerName); // provider 
                    claimRow.createCell((short)23).setCellValue(claim.getProviderArea()); // area
                    claimRow.createCell((short)24).setCellValue(claim.getClaimServiceType()); // claim type
                    claimRow.createCell((short)25).setCellValue(remainingBenefit); // remaining
                    claimRow.createCell((short)26).setCellValue("-"); // benefit code
                    claimRow.createCell((short)27).setCellValue(claim.getDiagnoseName()); // diag
                    claimRow.createCell((short)28).setCellValue(claim.getClaimPaymentType()); // payment type
                    claimRow.createCell((short)29).setCellValue(claim.getClaimChargeValue()); // charges
                    claimRow.createCell((short)30).setCellValue(claim.getClaimApprovedValue()); // paid
                    claimRow.createCell((short)31).setCellValue(claim.getClaimChargeValue()-claim.getClaimApprovedValue()); // decline
                    claimRow.createCell((short)32).setCellValue(claim.getClaimExcessValue()); // excess
                    claimRow.createCell((short)33).setCellValue("-"); // remarks
                    claimRow.createCell((short)34).setCellValue("IDR"); // currency
                    claimRow.createCell((short)35).setCellValue(claim.getBank()); // bank
                    claimRow.createCell((short)36).setCellValue(claim.getBankAccount()); // bank account
                    

                    idx += 1;
                    previousClaimId = claim.getClaimId().intValue();
                }

                Iterator<ClaimItem> ciIterator = ciList.iterator();
                
                while(ciIterator.hasNext()){
	                rowNumber += 1;
	
	                ClaimItem ci = ciIterator.next();
	                
	                HSSFRow claimRow = firstSheet.createRow(rowNumber);
	                String ciApprovedValue = "-";
	                String excessCharge = "-";
	                
	                
	                if (ci.getPostApproveRemainingBenefit() != null){
	                	BigDecimal bc = new BigDecimal(ci.getPostApproveRemainingBenefit());
	                	postApproveBenefit = bc.toPlainString();
	                }
	                if (ci.getClaimItemApprovedValue() != null){
	                	BigDecimal bc = new BigDecimal(ci.getClaimItemApprovedValue());
	                	ciApprovedValue = bc.toPlainString();
	                	
	                	BigDecimal excharge = new BigDecimal(ci.getClaimItemValue() - ci.getClaimItemApprovedValue());
	                	excessCharge = excharge.toPlainString();
	                }
	                
	                claimRow.createCell((short)0).setCellValue("");
	                claimRow.createCell((short)1).setCellValue(claim.getMemberId().getClientName());
	                claimRow.createCell((short)2).setCellValue(invNumber); // invoice number
	                claimRow.createCell((short)3).setCellValue(claim.getClaimNumber()); // claim number
	                claimRow.createCell((short)4).setCellValue(paymentNumber); // payment number
	                claimRow.createCell((short)5).setCellValue(claim.getMemberNumber()); // member id
	                claimRow.createCell((short)6).setCellValue(claim.getCardNumber()); // member id
	                claimRow.createCell((short)7).setCellValue((claim.getMemberId().getParentMember()!=null?
	                		claim.getMemberId().getParentMember().getFirstName():"")); // employee name
	                claimRow.createCell((short)8).setCellValue(claim.getMemberName()); // member id
	                claimRow.createCell((short)9).setCellValue(claim.getRelationship()); // member id
	                claimRow.createCell((short)10).setCellValue(claim.getSex()); // sex
	                claimRow.createCell((short)11).setCellValue(birthdate); // dob
	                claimRow.createCell((short)12).setCellValue(claim.getPlan()); // plan
	                claimRow.createCell((short)13).setCellValue(claim.getProductClass()); // class
	                claimRow.createCell((short)14).setCellValue(effectiveDate); // inception date
	                claimRow.createCell((short)15).setCellValue(expireDate); // maturity date
	                claimRow.createCell((short)16).setCellValue(admissionDate); // admission date
	                claimRow.createCell((short)17).setCellValue(dischargeDate); // discharge date
	                claimRow.createCell((short)18).setCellValue(""); // receive date
	                claimRow.createCell((short)19).setCellValue(""); // cdv date
	                claimRow.createCell((short)20).setCellValue(""); // payment date
	                claimRow.createCell((short)21).setCellValue(""); // branch 
	                claimRow.createCell((short)22).setCellValue(""); // provider 
	                claimRow.createCell((short)23).setCellValue(""); // area
	                claimRow.createCell((short)24).setCellValue(claim.getClaimServiceType()); // claim type
	                claimRow.createCell((short)25).setCellValue(postApproveBenefit); // remaining
	                claimRow.createCell((short)26).setCellValue(ci.getItemId().getItemName() +" - " + ci.getItemId().getItemCode()); // benefit code
	                claimRow.createCell((short)27).setCellValue(" "); // diag
	                claimRow.createCell((short)28).setCellValue(" "); // payment type
	                claimRow.createCell((short)29).setCellValue(ci.getClaimItemValue()); // charges
	                claimRow.createCell((short)30).setCellValue(ciApprovedValue); // paid
	                claimRow.createCell((short)31).setCellValue(excessCharge); // decline
	                claimRow.createCell((short)32).setCellValue(excessCharge); // excess
	                claimRow.createCell((short)33).setCellValue(ci.getBenefitCheckRemarks()); // remarks
	                claimRow.createCell((short)34).setCellValue("-"); // currency
	                claimRow.createCell((short)35).setCellValue(" "); // bank
	                claimRow.createCell((short)36).setCellValue(" "); // bank account
                }


            }
           

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return workbook;
	}
}
