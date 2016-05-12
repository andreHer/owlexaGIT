package com.ametis.cms.util;

import java.sql.Date;
import java.text.DecimalFormat;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.datamodel.Member;

public class MemberReportDownloader {
	
	public static HSSFWorkbook downloadBenefitUsageReport(Collection<Member> memberList, MemberProductService memberProductService) {
		HSSFWorkbook workbook = new HSSFWorkbook();

        try {
        	
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        return workbook;
	}
	public static HSSFWorkbook downloadMemberBenefitReport(Collection<Member> memberList, MemberProductService memberProductService) {
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
            firstSheet.setColumnWidth(0, 1500);
			firstSheet.setColumnWidth(1, 5000);
			firstSheet.setColumnWidth(2, 5000);
			firstSheet.setColumnWidth(3, 7000);
			firstSheet.setColumnWidth(4, 7000);
			firstSheet.setColumnWidth(5, 4000);
			firstSheet.setColumnWidth(6, 3000);
			firstSheet.setColumnWidth(7, 3500);
			firstSheet.setColumnWidth(8, 3500);

            Iterator<Member> memberIterator = memberList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("Member Name");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("Member Number");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell((short) 2);
            claimNoHCell.setCellValue("Birth Date");
            claimNoHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 3);
            benefitHCell.setCellValue("Employee Name");
            benefitHCell.setCellStyle(style);
            
            HSSFCell cardHCell = claimHeader.createCell((short) 4);
            cardHCell.setCellValue("Card Number");
            cardHCell.setCellStyle(style);
            
            HSSFCell clientHCell = claimHeader.createCell((short) 5);
            clientHCell.setCellValue("Client");
            clientHCell.setCellStyle(style);
            
            HSSFCell groupHCell = claimHeader.createCell((short) 6);
            groupHCell.setCellValue("Group");
            groupHCell.setCellStyle(style);

            HSSFCell admissionHCell = claimHeader.createCell((short) 7);
            admissionHCell.setCellValue("INPATIENT");
            admissionHCell.setCellStyle(style);

            HSSFCell dischargeHCell = claimHeader.createCell((short) 8);
            dischargeHCell.setCellValue("OUTPATIENT");
            dischargeHCell.setCellStyle(style);
            
            HSSFCell diagnosisHCell = claimHeader.createCell((short) 9);
            diagnosisHCell.setCellValue("MATERNITY");
            diagnosisHCell.setCellStyle(style);
            
            HSSFCell anamnesaHCell = claimHeader.createCell((short) 10);
            anamnesaHCell.setCellValue("DENTAL");
            anamnesaHCell.setCellStyle(style);
            
            HSSFCell opticalHCell = claimHeader.createCell((short) 11);
            opticalHCell.setCellValue("OPTICAL");
            opticalHCell.setCellStyle(style);
            
            HSSFCell mcuHCell = claimHeader.createCell((short) 12);
            mcuHCell.setCellValue("MCU");
            mcuHCell.setCellStyle(style);

            
            while (memberIterator.hasNext()) {

            	
                Member member = memberIterator.next();


                rowNumber += 1;

                String ipValue = "-";
                String opValue = "-";
                String dentalValue = "-";
                String opticalValue = "-";
                String maternityValue = "-";
                String mcuValue = "-";
                
                Collection<MemberProduct> memberProductList = memberProductService.getMemberActiveProduct(member.getMemberId());
                
                for (Iterator iterator = memberProductList.iterator(); iterator
						.hasNext();) {
                	
					MemberProduct memberProduct = (MemberProduct) iterator
							.next();
					
					if (memberProduct != null){
						if (memberProduct.getProductId().getCaseCategory().getCaseCategoryId().intValue() == CaseCategory.INPATIENT){
						
							if (memberProduct.getActualBenefitLimit() != null && member.getActualCustomerLimit().doubleValue() > 0){
								
							}
							else if (memberProduct.getActualBenefitLimit() != null && member.getActualCustomerLimit().doubleValue() == 0){
								ipValue = "LIMIT HABIS";
							}
						}
						if (memberProduct.getProductId().getCaseCategory().getCaseCategoryId().intValue() == CaseCategory.OUTPATIENT){
							
						}
						if (memberProduct.getProductId().getCaseCategory().getCaseCategoryId().intValue() == CaseCategory.DENTAL){
													
						}
						if (memberProduct.getProductId().getCaseCategory().getCaseCategoryId().intValue() == CaseCategory.MATERNITY){
							
						}
						if (memberProduct.getProductId().getCaseCategory().getCaseCategoryId().intValue() == CaseCategory.OPTICAL){
							
						}
						if (memberProduct.getProductId().getCaseCategory().getCaseCategoryId().intValue() == CaseCategory.MEDICAL_CHECK_UP){
							
						}
					}
					
				}
                                
                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                claimMember.createCell((short) 0).setCellValue(member.getFirstName());
                claimMember.createCell((short) 1).setCellValue(member.getCustomerPolicyNumber());
                claimMember.createCell((short) 2).setCellValue(df.format(member.getBirthday()));
                claimMember.createCell((short) 3).setCellValue(member.getParentName());
                claimMember.createCell((short) 4).setCellValue(member.getCurrentCardNumber());
                claimMember.createCell((short) 5).setCellValue(member.getClientName());
                claimMember.createCell((short) 6).setCellValue(member.getGroupName());
                
                claimMember.createCell((short) 7).setCellValue(ipValue);
                claimMember.createCell((short) 8).setCellValue(opValue);
                claimMember.createCell((short) 9).setCellValue(dentalValue);
                claimMember.createCell((short) 10).setCellValue(maternityValue);
                claimMember.createCell((short) 11).setCellValue(opticalValue);
                claimMember.createCell((short) 12).setCellValue(mcuValue);
                
                
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
}
