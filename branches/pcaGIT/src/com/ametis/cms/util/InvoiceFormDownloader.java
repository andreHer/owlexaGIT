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
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.datamodel.InvoiceItem;
import com.ametis.cms.datamodel.MemberImport;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.service.CaseService;

public class InvoiceFormDownloader {
	public static HSSFWorkbook downloadExcel (Invoice invoice, Collection<InvoiceItem> invoiceItemList, Collection<MemberImport> memberList){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			
			HSSFSheet firstSheet = workbook.createSheet("Invoice");
			HSSFSheet listSheet = workbook.createSheet("Invoice Detail");

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
	        

	        int rowNumber = 4;
			HSSFRow headerInvoice= firstSheet.createRow(rowNumber);
			
			setInvoiceContent(cellStyle, headerInvoice,(short) 0, "NO.");
			setInvoiceContent(cellStyle, headerInvoice,(short) 1, "CLIENT NAME");
			setInvoiceContent(cellStyle, headerInvoice,(short) 2, "PRODUCT");
			setInvoiceContent(cellStyle, headerInvoice,(short) 3, "GROUP NAME");
			setInvoiceContent(cellStyle, headerInvoice,(short) 4, "POLICY NUMBER");
			setInvoiceContent(cellStyle, headerInvoice,(short) 5, "MOVEMENT TYPE");
			setInvoiceContent(cellStyle, headerInvoice,(short) 6, "QTY");
			setInvoiceContent(cellStyle, headerInvoice,(short) 7, "PRICING");
			setInvoiceContent(cellStyle, headerInvoice,(short) 8, "TOTAL");

			int idx = 1;
			
			Iterator<InvoiceItem> invItemIterator = invoiceItemList.iterator();
			rowNumber++;
			while (invItemIterator.hasNext()){
			
				InvoiceItem invoiceItem = invItemIterator.next();
				
				HSSFRow invoiceContent = firstSheet.createRow(rowNumber);
								
				setInvoiceContent(cellStyle, invoiceContent,(short) 0,idx+"");
				setInvoiceContent(cellStyle, invoiceContent,(short) 1,invoiceItem.getInvoiceId().getClientId().getClientName());
				setInvoiceContent(cellStyle, invoiceContent,(short) 2,invoiceItem.getInvoiceId().getContractId().getContractTypeId().getContractTypeName());
				String groupName = invoiceItem.getMemberGroupId() == null ? "-" : invoiceItem.getMemberGroupId().getGroupName(); 
				setInvoiceContent(cellStyle, invoiceContent,(short) 3,groupName);
				String policyNumber = invoiceItem.getPolicyId() == null ? "-" : invoiceItem.getPolicyId().getPolicyNumber();
				setInvoiceContent(cellStyle, invoiceContent,(short) 4,policyNumber);
				setInvoiceContent(cellStyle, invoiceContent,(short) 5,invoiceItem.getItemId().getItemName());
				setInvoiceContent(cellStyle, invoiceContent,(short) 6,Converter.getMoney(invoiceItem.getItemAmount()));
				setInvoiceContent(cellStyle, invoiceContent,(short) 7,Converter.getMoney(invoiceItem.getItemUnitChargeValue()));
				setInvoiceContent(cellStyle, invoiceContent,(short) 8,Converter.getMoney(invoiceItem.getInvoiceItemValue()));
				
				idx++;
				rowNumber++;
			}
			
			
			Iterator<MemberImport> ciIterator = memberList.iterator();
			rowNumber = 4;
			HSSFRow header= listSheet.createRow(rowNumber);
			
			
			HSSFCell headerCol0 =header.createCell((short)0);
			headerCol0.setCellValue("No");
			headerCol0.setCellStyle(cellStyle);
			
			HSSFCell headerCol1 =header.createCell((short)1);
			headerCol1.setCellValue("FULL NAME");
			headerCol1.setCellStyle(cellStyle);
			
			HSSFCell headerCol2 =header.createCell((short)2);
			headerCol2.setCellValue("MEMBER ID");
			headerCol2.setCellStyle(cellStyle);
			
			HSSFCell headerCol3 =header.createCell((short)3);
			headerCol3.setCellValue("POLICY NUMBER");
			headerCol3.setCellStyle(cellStyle);
			
			HSSFCell headerCol4 =header.createCell((short)4);
			headerCol4.setCellValue("EMPLOYEE NUMBER");
			headerCol4.setCellStyle(cellStyle);
			
			HSSFCell headerCol5 =header.createCell((short)5);
			headerCol5.setCellValue("EMPLOYEE NAME");
			headerCol5.setCellStyle(cellStyle);
			
			HSSFCell headerCol6 =header.createCell((short)6);
			headerCol6.setCellValue("RELATIONSHIP");
			headerCol6.setCellStyle(cellStyle);
			
			HSSFCell headerCol7 =header.createCell((short)7);
			headerCol7.setCellValue("CARD NUMBER");
			headerCol7.setCellStyle(cellStyle);
			
			HSSFCell headerCol8 =header.createCell((short)8);
			headerCol8.setCellValue("CARD TEMPLATE");
			headerCol8.setCellStyle(cellStyle);
			
			HSSFCell headerCol9 =header.createCell((short)9);
			headerCol9.setCellValue("BIRTH DATE");
			headerCol9.setCellStyle(cellStyle);
			
			HSSFCell headerCol10 =header.createCell((short)10);
			headerCol10.setCellValue("JOIN DATE");
			headerCol10.setCellStyle(cellStyle);
			
			HSSFCell headerCol11 =header.createCell((short)11);
			headerCol11.setCellValue("EFFECTIVE DATE");
			headerCol11.setCellStyle(cellStyle);
			
			HSSFCell headerCol12 =header.createCell((short)12);
			headerCol12.setCellValue("EXPIRE DATE");
			headerCol12.setCellStyle(cellStyle);
			
			HSSFCell headerCol13 =header.createCell((short)13);
			headerCol13.setCellValue("PLAN ");
			headerCol13.setCellStyle(cellStyle);
			
			HSSFCell headerCol14 =header.createCell((short)14);
			headerCol14.setCellValue("GENDER ");
			headerCol14.setCellStyle(cellStyle);
			
			HSSFCell headerCol15 =header.createCell((short)15);
			headerCol15.setCellValue("GROUP NAME ");
			headerCol15.setCellStyle(cellStyle);
			
			HSSFCell headerCol16 =header.createCell((short)16);
			headerCol16.setCellValue("BRANCH ");
			headerCol16.setCellStyle(cellStyle);
			
			HSSFCell headerCol17 =header.createCell((short)17);
			headerCol17.setCellValue("SUB POLICY NUMBER ");
			headerCol17.setCellStyle(cellStyle);
			
			HSSFCell headerCol18 =header.createCell((short)18);
			headerCol18.setCellValue("NIK ");
			headerCol18.setCellStyle(cellStyle);
			
			HSSFCell headerCol19 =header.createCell((short)19);
			headerCol19.setCellValue("ADD INFO ");
			headerCol19.setCellStyle(cellStyle);
			
			
			int index = 1;
			
			while (ciIterator.hasNext()){
				
				MemberImport ci = ciIterator.next();
				
				if (ci.getPrintCard() != null && ci.getPrintCard().equalsIgnoreCase("Y")){
				
					rowNumber += 1;
					HSSFRow row = listSheet.createRow(rowNumber);
					
					
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
					contentCol3.setCellValue(ci.getPolicyNumber());
					contentCol3.setCellStyle(cellStyle);
					
					HSSFCell contentCol4 = row.createCell((short)4);
					contentCol4.setCellValue(ci.getParentNumber());
					contentCol4.setCellStyle(cellStyle);
					
					HSSFCell contentCol5 = row.createCell((short)5);
					contentCol5.setCellValue(ci.getParentName());
					contentCol5.setCellStyle(cellStyle);
					
					HSSFCell contentCol6 = row.createCell((short)6);
					contentCol6.setCellValue(ci.getRelationship());
					contentCol6.setCellStyle(cellStyle);
					
					String swipeCard = "";
					
					if (ci.getActionType() != null && ci.getActionType().equalsIgnoreCase("RENEWAL")){
						if (ci.getNextCardNumber() != null && !ci.getNextCardNumber().equalsIgnoreCase("")){
							swipeCard = ci.getNextCardNumber();
						}
					}
					else {
						swipeCard = ci.getSwipeCardNumber();
					}
					
					HSSFCell contentCol7 = row.createCell((short)7);
					contentCol7.setCellValue(swipeCard);
					contentCol7.setCellStyle(cellStyle);
					
					HSSFCell contentCol8 = row.createCell((short)8);
					contentCol8.setCellValue(ci.getCardTemplate());
					contentCol8.setCellStyle(cellStyle);
					
					HSSFCell contentCol9 = row.createCell((short)9);
					contentCol9.setCellValue(ci.getBirthdate());
					contentCol9.setCellStyle(cellStyle);
					
					HSSFCell contentCol10 = row.createCell((short)10);
					contentCol10.setCellValue(df.format(ci.getJoinDate()) );
					contentCol10.setCellStyle(cellStyle);
					
					HSSFCell contentCol11 = row.createCell((short)11);
					contentCol11.setCellValue(df.format(ci.getEffectiveDate()) );
					contentCol11.setCellStyle(cellStyle);
					
					HSSFCell contentCol12 = row.createCell((short)12);
					contentCol12.setCellValue(df.format(ci.getExpireDate()));
					contentCol12.setCellStyle(cellStyle);
					
					HSSFCell contentCol13 = row.createCell((short)13);
					contentCol13.setCellValue(ci.getProduct());
					contentCol13.setCellStyle(cellStyle);
					
					HSSFCell contentCol14 = row.createCell((short)14);
					contentCol14.setCellValue(ci.getSex());
					contentCol14.setCellStyle(cellStyle);
					
					HSSFCell contentCol15 = row.createCell((short)15);
					contentCol15.setCellValue(ci.getGroupName());
					contentCol15.setCellStyle(cellStyle);
					
					HSSFCell contentCol16 = row.createCell((short)16);
					contentCol16.setCellValue(ci.getDepartment());
					contentCol16.setCellStyle(cellStyle);
					
					HSSFCell contentCol17 = row.createCell((short)17);
					contentCol17.setCellValue(ci.getSubPolicyNumber());
					contentCol17.setCellStyle(cellStyle);
					
					HSSFCell contentCol18 = row.createCell((short)18);
					contentCol18.setCellValue(ci.getNik());
					contentCol18.setCellStyle(cellStyle);
					
					HSSFCell contentCol19 = row.createCell((short)19);
					contentCol19.setCellValue(ci.getAdditionalInfo());
					contentCol19.setCellStyle(cellStyle);
					
					
					index+=1;
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}

	private static void setInvoiceContent(HSSFCellStyle cellStyle, HSSFRow invoiceContent, short cellIndex,String value) {
		HSSFCell headerCol8Inv =invoiceContent.createCell(cellIndex);
		headerCol8Inv.setCellValue(value);
		headerCol8Inv.setCellStyle(cellStyle);
	}
	
}
