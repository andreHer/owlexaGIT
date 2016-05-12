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

import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.service.CaseService;

public class CardPrintDownloader {
	public static HSSFWorkbook downloadExcel (Collection<MemberImport> memberList){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			
			HSSFSheet firstSheet = workbook.createSheet();

	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
//	        SimpleDateFormat dfTrack2 = new SimpleDateFormat("yymm");
	        HSSFRow judul= firstSheet.createRow(0);
	        
	        HSSFCellStyle cellStyle = workbook.createCellStyle();  
	        cellStyle = workbook.createCellStyle();  
	        //cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);  
	        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        cellStyle.setBorderTop((short) 1); // single line border  
	        cellStyle.setBorderBottom((short) 1); // single line border
	        cellStyle.setBorderLeft((short)1);
	        cellStyle.setBorderRight((short)1);
	        
	        
	        

			Iterator<MemberImport> ciIterator = memberList.iterator();
			int rowNumber = 4;
			HSSFRow header= firstSheet.createRow(rowNumber);
			
			
			HSSFCell headerCol0 =header.createCell((short)0);
			headerCol0.setCellValue("No");
			headerCol0.setCellStyle(cellStyle);
			
			HSSFCell headerCol1 =header.createCell((short)1);
			headerCol1.setCellValue("TRACK 1");
			headerCol1.setCellStyle(cellStyle);
			
			HSSFCell headerCol2 =header.createCell((short)2);
			headerCol2.setCellValue("TRACK 2");
			headerCol2.setCellStyle(cellStyle);
			
			HSSFCell headerCol3 =header.createCell((short)3);
			headerCol3.setCellValue("FULL NAME");
			headerCol3.setCellStyle(cellStyle);
			
			HSSFCell headerCol4 =header.createCell((short)4);
			headerCol4.setCellValue("MEMBER ID");
			headerCol4.setCellStyle(cellStyle);
			
			HSSFCell headerCol5 =header.createCell((short)5);
			headerCol5.setCellValue("POLICY NUMBER");
			headerCol5.setCellStyle(cellStyle);
			
			HSSFCell headerCol6 =header.createCell((short)6);
			headerCol6.setCellValue("EMPLOYEE NUMBER");
			headerCol6.setCellStyle(cellStyle);
			
			HSSFCell headerCol7 =header.createCell((short)7);
			headerCol7.setCellValue("EMPLOYEE NAME");
			headerCol7.setCellStyle(cellStyle);
			
			HSSFCell headerCol8 =header.createCell((short)8);
			headerCol8.setCellValue("RELATIONSHIP");
			headerCol8.setCellStyle(cellStyle);
			
			HSSFCell headerCol9 =header.createCell((short)9);
			headerCol9.setCellValue("CARD NUMBER");
			headerCol9.setCellStyle(cellStyle);
			
			HSSFCell headerCol10 =header.createCell((short)10);
			headerCol10.setCellValue("CARD TEMPLATE");
			headerCol10.setCellStyle(cellStyle);
			
			HSSFCell headerCol11 =header.createCell((short)11);
			headerCol11.setCellValue("BIRTH DATE");
			headerCol11.setCellStyle(cellStyle);
			
			HSSFCell headerCol12 =header.createCell((short)12);
			headerCol12.setCellValue("JOIN DATE");
			headerCol12.setCellStyle(cellStyle);
			
			HSSFCell headerCol13 =header.createCell((short)13);
			headerCol13.setCellValue("EFFECTIVE DATE");
			headerCol13.setCellStyle(cellStyle);
			
			HSSFCell headerCol14 =header.createCell((short)14);
			headerCol14.setCellValue("EXPIRE DATE");
			headerCol14.setCellStyle(cellStyle);
			
			HSSFCell headerCol15 =header.createCell((short)15);
			headerCol15.setCellValue("PLAN ");
			headerCol15.setCellStyle(cellStyle);
			
			HSSFCell headerCol16 =header.createCell((short)16);
			headerCol16.setCellValue("GENDER ");
			headerCol16.setCellStyle(cellStyle);
			
			HSSFCell headerCol17 =header.createCell((short)17);
			headerCol17.setCellValue("CLIENT NAME ");
			headerCol17.setCellStyle(cellStyle);
			
			HSSFCell headerCol18 =header.createCell((short)18);
			headerCol18.setCellValue("GROUP CODE ");
			headerCol18.setCellStyle(cellStyle);
			
			HSSFCell headerCol19 =header.createCell((short)19);
			headerCol19.setCellValue("DEPARTMENT ");
			headerCol19.setCellStyle(cellStyle);
			
			HSSFCell headerCol20 =header.createCell((short)20);
			headerCol20.setCellValue("SUB POLICY NUMBER ");
			headerCol20.setCellStyle(cellStyle);
			
			HSSFCell headerCol21 =header.createCell((short)21);
			headerCol21.setCellValue("NIK ");
			headerCol21.setCellStyle(cellStyle);
			
			HSSFCell headerCol22 =header.createCell((short)22);
			headerCol22.setCellValue("ADD INFO ");
			headerCol22.setCellStyle(cellStyle);
			
			
			int index = 1;
			
			while (ciIterator.hasNext()){
				
				MemberImport ci = ciIterator.next();
				
				if (ci.getPrintCard() != null && ci.getPrintCard().equalsIgnoreCase("Y")){
				
					rowNumber += 1;
					HSSFRow row = firstSheet.createRow(rowNumber);
					
					
					HSSFCell contentCol0 = row.createCell((short)0);
					contentCol0.setCellValue(index);
					contentCol0.setCellStyle(cellStyle);
					
					HSSFCell contentCol1 = row.createCell((short)1);
					contentCol1.setCellValue("B"+ci.getSwipeCardNumber()+"^"+(ci.getClientName()== null? "" : ci.getClientName())+"^");
					contentCol1.setCellStyle(cellStyle);
					
					HSSFCell contentCol2 = row.createCell((short)2);
					contentCol2.setCellValue(ci.getSwipeCardNumber()+"=9912");
					contentCol2.setCellStyle(cellStyle);
					
					HSSFCell contentCol3 = row.createCell((short)3);
					contentCol3.setCellValue(ci.getMemberName());
					contentCol3.setCellStyle(cellStyle);
					
					HSSFCell contentCol4 = row.createCell((short)4);
					contentCol4.setCellValue(ci.getMemberNumber());
					contentCol4.setCellStyle(cellStyle);
					
					HSSFCell contentCol5 = row.createCell((short)5);
					contentCol5.setCellValue(ci.getPolicyNumber());
					contentCol5.setCellStyle(cellStyle);
					
					HSSFCell contentCol6 = row.createCell((short)6);
					contentCol6.setCellValue(ci.getParentNumber());
					contentCol6.setCellStyle(cellStyle);
					
					HSSFCell contentCol7 = row.createCell((short)7);
					contentCol7.setCellValue(ci.getParentName());
					contentCol7.setCellStyle(cellStyle);
					
					HSSFCell contentCol8 = row.createCell((short)8);
					contentCol8.setCellValue(ci.getRelationship());
					contentCol8.setCellStyle(cellStyle);
					
					String swipeCard = "";
					
					if (ci.getActionType() != null && ci.getActionType().equalsIgnoreCase("RENEWAL")){
						if (ci.getNextCardNumber() != null && !ci.getNextCardNumber().equalsIgnoreCase("")){
							swipeCard = ci.getNextCardNumber();
						}
					}
					else {
						swipeCard = ci.getSwipeCardNumber();
					}
					
					HSSFCell contentCol9 = row.createCell((short)9);
					contentCol9.setCellValue(swipeCard);
					contentCol9.setCellStyle(cellStyle);
					
					HSSFCell contentCol10 = row.createCell((short)10);
					contentCol10.setCellValue(ci.getCardTemplate());
					contentCol10.setCellStyle(cellStyle);
					
					HSSFCell contentCol11 = row.createCell((short)11);
					contentCol11.setCellValue(ci.getBirthdate());
					contentCol11.setCellStyle(cellStyle);
					
					HSSFCell contentCol12 = row.createCell((short)12);
					contentCol12.setCellValue(df.format(ci.getJoinDate()) );
					contentCol12.setCellStyle(cellStyle);
					
					HSSFCell contentCol13 = row.createCell((short)13);
					contentCol13.setCellValue(df.format(ci.getEffectiveDate()) );
					contentCol13.setCellStyle(cellStyle);
					
					HSSFCell contentCol14 = row.createCell((short)14);
					contentCol14.setCellValue(df.format(ci.getExpireDate()));
					contentCol14.setCellStyle(cellStyle);
					
					HSSFCell contentCol15 = row.createCell((short)15);
					contentCol15.setCellValue(ci.getProduct());
					contentCol15.setCellStyle(cellStyle);
					
					HSSFCell contentCol16 = row.createCell((short)16);
					contentCol16.setCellValue(ci.getSex());
					contentCol16.setCellStyle(cellStyle);
					
					HSSFCell contentCol17 = row.createCell((short)17);
					contentCol17.setCellValue(ci.getClientName());
					contentCol17.setCellStyle(cellStyle);
					
					HSSFCell contentCol18 = row.createCell((short)18);
					contentCol18.setCellValue(ci.getGroupCode());
					contentCol18.setCellStyle(cellStyle);
					
					HSSFCell contentCol19 = row.createCell((short)19);
					contentCol19.setCellValue(ci.getDepartment());
					contentCol19.setCellStyle(cellStyle);
					
					HSSFCell contentCol20 = row.createCell((short)20);
					contentCol20.setCellValue(ci.getSubPolicyNumber());
					contentCol20.setCellStyle(cellStyle);
					
					HSSFCell contentCol21 = row.createCell((short)21);
					contentCol21.setCellValue(ci.getNik());
					contentCol21.setCellStyle(cellStyle);
					
					HSSFCell contentCol22 = row.createCell((short)22);
					contentCol22.setCellValue(ci.getAdditionalInfo());
					contentCol22.setCellStyle(cellStyle);
					
					
					index+=1;
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	
}
