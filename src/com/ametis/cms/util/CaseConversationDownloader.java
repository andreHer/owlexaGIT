package com.ametis.cms.util;

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
import org.apache.poi.hssf.util.Region;

import com.ametis.cms.datamodel.CaseConversation;
import com.ametis.cms.datamodel.CaseEvent;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.dto.MemberClaimDto;

public class CaseConversationDownloader {
	public static HSSFWorkbook downloadCaseConversation(Collection<CaseConversation> conversationList) {
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

            Iterator<CaseConversation> conversationIterator = conversationList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("Tanggal/Waktu");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("Keterangan");
            batchNoHCell.setCellStyle(style);


            HSSFCell benefitHCell = claimHeader.createCell((short) 2);
            benefitHCell.setCellValue("Nama / Ttd");
            benefitHCell.setCellStyle(style);

            while (conversationIterator.hasNext()) {

                CaseConversation conversation = conversationIterator.next();


                rowNumber += 1;

                String monitoring = "";
                String contactPerson = "";
                
                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                claimMember.createCell((short) 0).setCellValue(conversation.getConversationTime().toString());
                claimMember.createCell((short) 1).setCellValue(conversation.getConversationDescription());
                claimMember.createCell((short) 2).setCellValue(conversation.getCreatedBy());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
}
