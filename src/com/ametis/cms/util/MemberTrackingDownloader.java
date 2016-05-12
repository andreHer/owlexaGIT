package com.ametis.cms.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ametis.cms.datamodel.MemberImport;

public class MemberTrackingDownloader {
	public static HSSFWorkbook downloadExcel (Collection<MemberImport> memberList){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			
			HSSFSheet firstSheet = workbook.createSheet();

	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
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
			headerCol1.setCellValue("MEMBER NAME");
			headerCol1.setCellStyle(cellStyle);
			
			HSSFCell headerCol2 =header.createCell((short)2);
			headerCol2.setCellValue("MEMBER NUMBER");
			headerCol2.setCellStyle(cellStyle);
			
			HSSFCell headerCol3 =header.createCell((short)3);
			headerCol3.setCellValue("BIRTH DATE");
			headerCol3.setCellStyle(cellStyle);
			
			HSSFCell headerCol4 =header.createCell((short)4);
			headerCol4.setCellValue("GENDER ");
			headerCol4.setCellStyle(cellStyle);
			
			HSSFCell headerCol5 =header.createCell((short)5);
			headerCol5.setCellValue("EMPLOYEE NUMBER");
			headerCol5.setCellStyle(cellStyle);
			
			HSSFCell headerCol6 =header.createCell((short)6);
			headerCol6.setCellValue("EMPLOYEE NAME");
			headerCol6.setCellStyle(cellStyle);
			
			HSSFCell headerCol7 =header.createCell((short)7);
			headerCol7.setCellValue("SWIPE CARD NUMBER");
			headerCol7.setCellStyle(cellStyle);
			
			HSSFCell headerCol8 =header.createCell((short)8);
			headerCol8.setCellValue("GROUP NAME");
			headerCol8.setCellStyle(cellStyle);
			
			HSSFCell headerCol9 =header.createCell((short)9);
			headerCol9.setCellValue("GROUP CODE ");
			headerCol9.setCellStyle(cellStyle);
			
			HSSFCell headerCol10 =header.createCell((short)10);
			headerCol10.setCellValue("CLIENT CODE");
			headerCol10.setCellStyle(cellStyle);
			
			HSSFCell headerCol11 =header.createCell((short)11);
			headerCol11.setCellValue("JOIN DATE");
			headerCol11.setCellStyle(cellStyle);
			
			HSSFCell headerCol12 =header.createCell((short)12);
			headerCol12.setCellValue("EFFECTIVE DATE");
			headerCol12.setCellStyle(cellStyle);
			
			HSSFCell headerCol13 =header.createCell((short)13);
			headerCol13.setCellValue("EXPIRE DATE");
			headerCol13.setCellStyle(cellStyle);
			
			HSSFCell headerCol14 =header.createCell((short)14);
			headerCol14.setCellValue("POLICY NUMBER ");
			headerCol14.setCellStyle(cellStyle);
			
			HSSFCell headerCol15 =header.createCell((short)15);
			headerCol15.setCellValue("MODIFIED TIME ");
			headerCol15.setCellStyle(cellStyle);
			
			HSSFCell headerCol16 =header.createCell((short)16);
			headerCol16.setCellValue("ACTION ");
			headerCol16.setCellStyle(cellStyle);
			
			
			int index = 1;
			
			while (ciIterator.hasNext()){
				
				MemberImport ci = ciIterator.next();
				
				rowNumber += 1;
				HSSFRow row = firstSheet.createRow(rowNumber);
				
				
				HSSFCell contentCol0 = row.createCell((short)0);
				contentCol0.setCellValue(index);
				contentCol0.setCellStyle(cellStyle);
				
				HSSFCell contentCol1 = row.createCell((short)1);
				contentCol1.setCellValue(ci.getMemberName());
				contentCol1.setCellStyle(cellStyle);
				
				HSSFCell contentCol2 = row.createCell((short)2);
				contentCol2.setCellValue(ci.getMemberNumber());
				contentCol2.setCellStyle(cellStyle);
				
				HSSFCell contentCol3 = row.createCell((short)3);
				contentCol3.setCellValue(ci.getBirthdate());
				contentCol3.setCellStyle(cellStyle);
				
				HSSFCell contentCol4 = row.createCell((short)4);
				contentCol4.setCellValue(ci.getSex());
				contentCol4.setCellStyle(cellStyle);
				
				HSSFCell contentCol5 = row.createCell((short)5);
				contentCol5.setCellValue(ci.getParentNumber());
				contentCol5.setCellStyle(cellStyle);
				
				HSSFCell contentCol6 = row.createCell((short)6);
				contentCol6.setCellValue(ci.getParentName());
				contentCol6.setCellStyle(cellStyle);
				
				HSSFCell contentCol7 = row.createCell((short)7);
				contentCol7.setCellValue(ci.getSwipeCardNumber());
				contentCol7.setCellStyle(cellStyle);
				
				HSSFCell contentCol8 = row.createCell((short)8);
				contentCol8.setCellValue(ci.getGroupName());
				contentCol8.setCellStyle(cellStyle);
				
				HSSFCell contentCol9 = row.createCell((short)9);
				contentCol9.setCellValue(ci.getGroupCode());
				contentCol9.setCellStyle(cellStyle);
				
				HSSFCell contentCol10 = row.createCell((short)10);
				contentCol10.setCellValue(ci.getClientCode());
				contentCol10.setCellStyle(cellStyle);
				
				HSSFCell contentCol11 = row.createCell((short)11);
				if(ci.getJoinDate()!=null)
					contentCol11.setCellValue(df.format(ci.getJoinDate()));
				else
					contentCol11.setCellValue("");
				contentCol11.setCellStyle(cellStyle);
				
				HSSFCell contentCol12 = row.createCell((short)12);
				if(ci.getEffectiveDate()!=null)
					contentCol12.setCellValue(df.format(ci.getEffectiveDate()));
				else
					contentCol12.setCellValue("");
				contentCol12.setCellStyle(cellStyle);
				
				HSSFCell contentCol13 = row.createCell((short)13);
				if(ci.getExpireDate()!=null)
					contentCol13.setCellValue(df.format(ci.getExpireDate()));
				else	
					contentCol13.setCellValue("");
				contentCol13.setCellStyle(cellStyle);
				
				HSSFCell contentCol14 = row.createCell((short)14);
				contentCol14.setCellValue(ci.getPolicyNumber());
				contentCol14.setCellStyle(cellStyle);
				
				HSSFCell contentCol15 = row.createCell((short)15);
				if(ci.getCreatedTime()!=null)
					contentCol15.setCellValue(df.format(ci.getCreatedTime()));
				else
					contentCol15.setCellValue("");
				contentCol15.setCellStyle(cellStyle);				
				
				
				HSSFCell contentCol16 = row.createCell((short)16);
				contentCol16.setCellValue(ci.getActionType());
				contentCol16.setCellStyle(cellStyle);
				
				
				index+=1;
				
			}
			for(int i=0;i<=16;i++){
				firstSheet.autoSizeColumn(i, true);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	
}
