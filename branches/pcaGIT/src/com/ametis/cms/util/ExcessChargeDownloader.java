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
import com.ametis.cms.datamodel.ExcessCharge;
import com.ametis.cms.service.ClaimItemService;

public class ExcessChargeDownloader {

	public static HSSFWorkbook downloadReport (Collection<ExcessCharge> exList) throws Exception {
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
            batchNoHCell.setCellValue("EXCESS NUMBER");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell( (short) 2);
            claimNoHCell.setCellValue("INVOICE NUMBER");
            claimNoHCell.setCellStyle(style);

            HSSFCell admissionHCell = claimHeader.createCell( (short) 3);
            admissionHCell.setCellValue("CLAIM NUMBER");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell( (short)4);
            benefitHCell.setCellValue("CLIENT NAME");
            benefitHCell.setCellStyle(style);


            HSSFCell incuredHCell = claimHeader.createCell( (short)5);
            incuredHCell.setCellValue("MEMBER ID");
            incuredHCell.setCellStyle(style);
            
            HSSFCell cardNumHCell = claimHeader.createCell( (short)6);
            cardNumHCell.setCellValue("CARD NUMBER");
            cardNumHCell.setCellStyle(style);

            HSSFCell paidHCell = claimHeader.createCell( (short)7);
            paidHCell.setCellValue("MEMBER NAME");
            paidHCell.setCellStyle(style);

            HSSFCell diagnoseHCell = claimHeader.createCell( (short)8);
            diagnoseHCell.setCellValue("EMPLOYEE NAME");
            diagnoseHCell.setCellStyle(style);

            HSSFCell remarkHCell = claimHeader.createCell( (short) 9);
            remarkHCell.setCellValue("RELATIONSHIP");
            remarkHCell.setCellStyle(style);

            HSSFCell sexHCell = claimHeader.createCell( (short) 10);
            sexHCell.setCellValue("GROUP NAME");
            sexHCell.setCellStyle(style);
            
            HSSFCell dobHCell = claimHeader.createCell( (short) 11);
            dobHCell.setCellValue("CLAIM DATE");
            dobHCell.setCellStyle(style);
            
            

            HSSFCell excessHCell = claimHeader.createCell( (short)12);
            excessHCell.setCellValue("EXCESS CHARGE");
            excessHCell.setCellStyle(style);
            
            
            HSSFCell remarksHCell = claimHeader.createCell( (short)13);
            remarksHCell.setCellValue("STATUS");
            remarksHCell.setCellStyle(style);
            
            
            Iterator<ExcessCharge> iterator = exList.iterator();
            
            
            rowNumber += 1;
            int idx = 1;

            while (iterator.hasNext()) {

                ExcessCharge ex = iterator.next();
                

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
                String excessValue = "-";
                
                if (ex.getExcessChargeValue() != null){
                	BigDecimal bc = new BigDecimal(ex.getExcessChargeValue());
                	excessValue = bc.toPlainString();
                }
          
	        	rowNumber += 1;
	        	HSSFRow claimRow = firstSheet.createRow(rowNumber);
	
	        	claimRow.createCell((short)0).setCellValue(idx+".");
	            claimRow.createCell((short)1).setCellValue(ex.getExcessChargeNumber()); 
	            claimRow.createCell((short)2).setCellValue(invNumber); // invoice number
	            claimRow.createCell((short)3).setCellValue(ex.getClaimId().getClaimNumber()); // claim number
	            claimRow.createCell((short)4).setCellValue(ex.getMemberId().getClientName()); // payment number
	            claimRow.createCell((short)5).setCellValue(ex.getMemberId().getCustomerPolicyNumber()); // member id
	            claimRow.createCell((short)6).setCellValue(ex.getClaimId().getCardNumber()); // member id
	            claimRow.createCell((short)7).setCellValue(ex.getMemberId().getFirstName()); // employee name
	            claimRow.createCell((short)8).setCellValue(ex.getMemberId().getParentName()); // member id
	            claimRow.createCell((short)9).setCellValue(ex.getMemberId().getRelationship()); // member id
	            claimRow.createCell((short)10).setCellValue(ex.getMemberId().getGroupName()); // sex
	            claimRow.createCell((short)11).setCellValue(df.format(ex.getExcessChargeTime())); // dob
	            claimRow.createCell((short)12).setCellValue(excessValue); // plan
	            claimRow.createCell((short)13).setCellValue(ex.getExcessChargeStatus().getPaymentStatusName()); // class
	           
	            
	
	            idx += 1;
                

                


            }
           

		}
		catch (Exception e){
			e.printStackTrace();
		}
		return workbook;
	}
}
