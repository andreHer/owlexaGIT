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
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClaimRelifeDownloader {

    
    public static HSSFWorkbook downloadMember(Collection<MemberClaimDto> claimList) {
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

            Iterator<MemberClaimDto> claimIterator = claimList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("Nama");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("Batch No.");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell((short) 2);
            claimNoHCell.setCellValue("Claim No.");
            claimNoHCell.setCellStyle(style);



            HSSFCell admissionHCell = claimHeader.createCell((short) 3);
            admissionHCell.setCellValue("Date of Admission");
            admissionHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 4);
            benefitHCell.setCellValue("Benefit");
            benefitHCell.setCellStyle(style);


            HSSFCell incuredHCell = claimHeader.createCell((short) 5);
            incuredHCell.setCellValue("Incured");
            incuredHCell.setCellStyle(style);

            HSSFCell paidHCell = claimHeader.createCell((short) 6);
            paidHCell.setCellValue("Paid");
            paidHCell.setCellStyle(style);

            HSSFCell diagnoseHCell = claimHeader.createCell((short) 7);
            diagnoseHCell.setCellValue("Diagnose");
            diagnoseHCell.setCellStyle(style);

            HSSFCell remarkHCell = claimHeader.createCell((short) 8);
            remarkHCell.setCellValue("Remarks");
            remarkHCell.setCellStyle(style);


            while (claimIterator.hasNext()) {

                MemberClaimDto member = claimIterator.next();

                double claimChargeValue = 0;
                double claimApprovedValue = 0;

                rowNumber += 1;

                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                claimMember.createCell((short) 0).setCellValue(member.getMember().getFirstName() + " - " + member.getMember().getCustomerPolicyNumber());

                claimMember.createCell((short) 3).setCellValue("Employee : " + member.getMember().getParentMember().getFirstName()
                        + " - " + member.getMember().getParentMember().getCustomerNumber());

                claimMember.createCell((short) 6).setCellValue("Plan : " + member.getMember().getCurrentProductCode());

                firstSheet.addMergedRegion(new Region(rowNumber, (short) 0, rowNumber, (short) 2));
                firstSheet.addMergedRegion(new Region(rowNumber, (short) 3, rowNumber, (short) 5));
                firstSheet.addMergedRegion(new Region(rowNumber, (short) 6, rowNumber, (short) 7));
                Collection<ClaimDto> memberClaimList = member.getClaimDto();

                if (memberClaimList != null) {
                    Iterator<ClaimDto> memberClaimIterator = memberClaimList.iterator();

                    while (memberClaimIterator.hasNext()) {
                        ClaimDto claim = memberClaimIterator.next();

                        if (claim != null) {


                            Collection<ClaimItemDto> claimItemDto = claim.getClaimItemList();

                            if (claimItemDto != null) {

                                Iterator<ClaimItemDto> ciIterator = claimItemDto.iterator();

                                while (ciIterator.hasNext()) {

                                    ClaimItemDto ciDto = ciIterator.next();
                                    if (ciDto != null) {
                                        rowNumber += 1;

                                        HSSFRow claimItemRow = firstSheet.createRow(rowNumber);

                                        claimItemRow.createCell((short) 0).setCellValue("");
                                        claimItemRow.createCell((short) 1).setCellValue("");
                                        claimItemRow.createCell((short) 2).setCellValue("");

                                        HSSFCell ciAdmissionCell = claimItemRow.createCell((short) 3);
                                        ciAdmissionCell.setCellValue(claim.getAdmissionDate());
                                        ciAdmissionCell.setCellStyle(ciStyle);

                                        HSSFCell ciBenefitCell = claimItemRow.createCell((short) 4);
                                        ciBenefitCell.setCellValue(ciDto.getClaimItemName());
                                        ciBenefitCell.setCellStyle(ciStyle);

                                        HSSFCell ciIncuredCell = claimItemRow.createCell((short) 5);
                                        ciIncuredCell.setCellValue(ciDto.getClaimItemValue());
                                        ciIncuredCell.setCellStyle(ciStyle);

                                        HSSFCell ciPaidCell = claimItemRow.createCell((short) 6);
                                        ciPaidCell.setCellValue(ciDto.getClaimItemApprovedValue());
                                        ciPaidCell.setCellStyle(ciStyle);

                                        claimItemRow.createCell((short) 4).setCellValue(ciDto.getClaimItemValue());
                                        claimItemRow.createCell((short) 5).setCellValue(ciDto.getClaimItemApprovedValue());
                                        claimItemRow.createCell((short) 6).setCellValue(ciDto.getExcessValue());
                                        claimItemRow.createCell((short) 7).setCellValue("");
                                        claimItemRow.createCell((short) 8).setCellValue("");
                                        claimItemRow.createCell((short) 9).setCellValue(ciDto.getBenefitRemarks());
                                    }
                                }
                            }
                        }
                    }
                }
                rowNumber += 1;



                HSSFRow closingRow = firstSheet.createRow(rowNumber);

                closingRow.createCell((short) 0).setCellValue("");
                closingRow.createCell((short) 1).setCellValue("");
                closingRow.createCell((short) 2).setCellValue("");
                closingRow.createCell((short) 3).setCellValue("");
                closingRow.createCell((short) 4).setCellValue("");
                closingRow.createCell((short) 5).setCellValue("");
                closingRow.createCell((short) 6).setCellValue("");
                closingRow.createCell((short) 7).setCellValue("");
                closingRow.createCell((short) 8).setCellValue("");
                closingRow.createCell((short) 9).setCellValue("");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
}
