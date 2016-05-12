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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.datamodel.RejectedClaim;
import com.ametis.cms.service.CaseService;

public class ClaimDocumentDownloader {
	public static HSSFWorkbook downloadExcel (Collection<ClaimItem> claimItems, String groupName, Payment payment){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			
			HSSFSheet firstSheet = workbook.createSheet();

	        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	        HSSFRow judul= firstSheet.createRow(0);
	        judul.createCell((short)0).setCellValue("Report Klaim " + groupName + " per " + df.format( payment.getPaymentTime()));
	        
	        HSSFCellStyle cellStyle = workbook.createCellStyle();  
	        cellStyle = workbook.createCellStyle();  
	        //cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);  
	        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        cellStyle.setBorderTop((short) 1); // single line border  
	        cellStyle.setBorderBottom((short) 1); // single line border
	        cellStyle.setBorderLeft((short)1);
	        cellStyle.setBorderRight((short)1);
	        

			Iterator<ClaimItem> ciIterator = claimItems.iterator();
			int rowNumber = 4;
			HSSFRow header= firstSheet.createRow(rowNumber);
			
			
			HSSFCell headerCol0 =header.createCell((short)0);
			headerCol0.setCellValue("No");
			headerCol0.setCellStyle(cellStyle);
			
			HSSFCell headerCol1 =header.createCell((short)1);
			headerCol1.setCellValue("Claim No");
			headerCol1.setCellStyle(cellStyle);
			
			HSSFCell headerCol2 =header.createCell((short)2);
			headerCol2.setCellValue("No. ID");
			headerCol2.setCellStyle(cellStyle);
			
			HSSFCell headerCol3 =header.createCell((short)3);
			headerCol3.setCellValue("NIP");
			headerCol3.setCellStyle(cellStyle);
			
			HSSFCell headerCol4 =header.createCell((short)4);
			headerCol4.setCellValue("Nama Pegawai");
			headerCol4.setCellStyle(cellStyle);
			
			HSSFCell headerCol5 =header.createCell((short)5);
			headerCol5.setCellValue("Nama Peserta");
			headerCol5.setCellStyle(cellStyle);
			
			HSSFCell headerCol6 =header.createCell((short)6);
			headerCol6.setCellValue("Tgl Kejadian");
			headerCol6.setCellStyle(cellStyle);
			
			HSSFCell headerCol7 =header.createCell((short)7);
			headerCol7.setCellValue("Status");
			headerCol7.setCellStyle(cellStyle);
			
			HSSFCell headerCol8 =header.createCell((short)8);
			headerCol8.setCellValue("Provider");
			headerCol8.setCellStyle(cellStyle);
			
			HSSFCell headerCol9 =header.createCell((short)9);
			headerCol9.setCellValue("Diagnosa");
			headerCol9.setCellStyle(cellStyle);
			
			HSSFCell headerCol10 =header.createCell((short)10);
			headerCol10.setCellValue("Treatment");
			headerCol10.setCellStyle(cellStyle);
			
			HSSFCell headerCol11 =header.createCell((short)11);
			headerCol11.setCellValue("Plan");
			headerCol11.setCellStyle(cellStyle);
			
			HSSFCell headerCol12 =header.createCell((short)12);
			headerCol12.setCellValue("Benefit");
			headerCol12.setCellStyle(cellStyle);
			
			HSSFCell headerCol13 =header.createCell((short)13);
			headerCol13.setCellValue("Claim Diajukan");
			headerCol13.setCellStyle(cellStyle);
			
			HSSFCell headerCol14 =header.createCell((short)14);
			headerCol14.setCellValue("Claim Dibayar");
			headerCol14.setCellStyle(cellStyle);
			
			HSSFCell headerCol15 =header.createCell((short)15);
			headerCol15.setCellValue("Keterangan");
			headerCol15.setCellStyle(cellStyle);
			
			int index = 1;
			
			while (ciIterator.hasNext()){
				
				ClaimItem ci = ciIterator.next();
				
				rowNumber += 1;
				HSSFRow row = firstSheet.createRow(rowNumber);
				String providerName = "";
				
				if (ci.getClaimId().getProviderId() != null){
					providerName = ci.getClaimId().getProviderId().getProviderName();
				}
				else {
					providerName = ci.getClaimId().getProviderName();
				}
				
				HSSFCell contentCol0 = row.createCell((short)0);
				contentCol0.setCellValue(index);
				contentCol0.setCellStyle(cellStyle);
				
				HSSFCell contentCol1 = row.createCell((short)1);
				contentCol1.setCellValue(ci.getClaimId().getClaimNumber());
				contentCol1.setCellStyle(cellStyle);
				
				HSSFCell contentCol2 = row.createCell((short)2);
				contentCol2.setCellValue(ci.getClaimId().getMemberId().getCustomerPolicyNumber());
				contentCol2.setCellStyle(cellStyle);
				
				HSSFCell contentCol3 = row.createCell((short)3);
				//Edit 20150513 by Feiby Veronika for handling NULL value
				contentCol3.setCellValue(ci.getClaimId().getMemberId().getParentMember() == null ? "" : ci.getClaimId().getMemberId().getParentMember().getCustomerNumber());
				contentCol3.setCellStyle(cellStyle);
				
				HSSFCell contentCol4 = row.createCell((short)4);
				//Edit 20150513 by Feiby Veronika for handling NULL value
				contentCol4.setCellValue(ci.getClaimId().getMemberId().getParentMember() == null ? "" : ci.getClaimId().getMemberId().getParentMember().getFirstName());
				contentCol4.setCellStyle(cellStyle);
				
				HSSFCell contentCol5 = row.createCell((short)5);
				contentCol5.setCellValue(ci.getClaimId().getMemberId().getFirstName());
				contentCol5.setCellStyle(cellStyle);
				
				HSSFCell contentCol6 = row.createCell((short)6);
				contentCol6.setCellValue(ci.getClaimId().getAdmissionDate() == null ? "" : df.format( ci.getClaimId().getAdmissionDate()));
				contentCol6.setCellStyle(cellStyle);
				
				HSSFCell contentCol7 = row.createCell((short)7);
				contentCol7.setCellValue(ci.getClaimId().getMemberId().getRelationship());
				contentCol7.setCellStyle(cellStyle);
				
				HSSFCell contentCol8 = row.createCell((short)8);
				contentCol8.setCellValue(providerName);
				contentCol8.setCellStyle(cellStyle);
				
				HSSFCell contentCol9 = row.createCell((short)9);
				contentCol9.setCellValue(ci.getClaimId().getDiagnosisId() == null ? "" : ci.getClaimId().getDiagnosisId().getDiagnosisName());
				contentCol9.setCellStyle(cellStyle);
				
				HSSFCell contentCol10 = row.createCell((short)10);
				contentCol10.setCellValue("");
				contentCol10.setCellStyle(cellStyle);
				
				HSSFCell contentCol11 = row.createCell((short)11);
				String productCode = "";
				if (ci.getClaimId().getProductId() != null){
					productCode = ci.getClaimId().getProductId().getProductId().getProductCode();
				}
				contentCol11.setCellValue(productCode);
				contentCol11.setCellStyle(cellStyle);
				
				HSSFCell contentCol12 = row.createCell((short)12);
				contentCol12.setCellValue(ci.getItemId() == null ? "" : ci.getItemId().getItemName());
				contentCol12.setCellStyle(cellStyle);
				
				HSSFCell contentCol13 = row.createCell((short)13);
				contentCol13.setCellValue(ci.getClaimItemValue());
				contentCol13.setCellStyle(cellStyle);
				
				HSSFCell contentCol14 = row.createCell((short)14);
				contentCol14.setCellValue(ci.getClaimItemApprovedValue());
				contentCol14.setCellStyle(cellStyle);
				
				HSSFCell contentCol15 = row.createCell((short)15);
				contentCol15.setCellValue(ci.getBenefitCheckRemarks());
				contentCol15.setCellStyle(cellStyle);
				index+=1;
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	public static HSSFWorkbook downloadClaim (Collection<Claim> claims, String groupName, Payment payment){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {

	        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	        DecimalFormat decFormat = new DecimalFormat("#.00");
	        HSSFCellStyle cellStyle = workbook.createCellStyle();  
	        cellStyle = workbook.createCellStyle();  
	        //cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);  
	        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        cellStyle.setBorderTop((short) 1); // single line border  
	        cellStyle.setBorderBottom((short) 1); // single line border
	        cellStyle.setBorderLeft((short)1);
	        cellStyle.setBorderRight((short)1);
			
	        
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
			
			
			HSSFRow reportNameHeader= firstSheet.createRow(2);
			HSSFCell reportNameSubHeaderCol0 = reportNameHeader.createCell((short)1);
			reportNameSubHeaderCol0.setCellValue("SURAT JALAN");
			
			HSSFRow invNoHeader= firstSheet.createRow(3);
			HSSFCell invNoSubHeaderCol0 = invNoHeader.createCell((short)1);
			invNoSubHeaderCol0.setCellValue("Invoice Number");
			HSSFCell invNoSubHeaderCol1 = invNoHeader.createCell((short)2);
			invNoSubHeaderCol1.setCellValue(payment.getBatchClaim().getInvoiceNumber());
			
			if (payment.getBatchClaim().getProviderId() != null){
				HSSFRow recipientHeader= firstSheet.createRow(4);
				HSSFCell recSubHeaderCol0 = recipientHeader.createCell((short)1);
				
				recSubHeaderCol0.setCellValue("Provider Name");
				HSSFCell recSubHeaderCol1 = recipientHeader.createCell((short)2);
				System.out.println("PROVIDER NAME = " + payment.getBatchClaim().getProviderId().getProviderName());
				String providerName = payment.getBatchClaim().getProviderId().getProviderName();
				recSubHeaderCol1.setCellValue(providerName);
			}
			if (payment.getBatchClaim().getMemberId() != null){
				HSSFRow recipientHeader= firstSheet.createRow(4);
				HSSFCell recSubHeaderCol0 = recipientHeader.createCell((short)1);
				
				recSubHeaderCol0.setCellValue("Member Name");
				HSSFCell recSubHeaderCol1 = recipientHeader.createCell((short)2);
				String memberName = payment.getBatchClaim().getMemberId().getFirstName();
				recSubHeaderCol1.setCellValue(memberName);
			}
			if (payment.getBatchClaim().getMemberGroupId() != null){
				HSSFRow recipientHeader= firstSheet.createRow(4);
				HSSFCell recSubHeaderCol0 = recipientHeader.createCell((short)1);
				
				recSubHeaderCol0.setCellValue("Group Name");
				HSSFCell recSubHeaderCol1 = recipientHeader.createCell((short)2);
				
				String memberGroupName = payment.getBatchClaim().getMemberGroupId().getGroupName();
				recSubHeaderCol1.setCellValue(memberGroupName);
			}
			
			
			
			HSSFRow subHeader= firstSheet.createRow(6);
			HSSFCell subHeaderCol0 = subHeader.createCell((short)3);
			subHeaderCol0.setCellValue("FORMULIR PENGAJUAN KLAIM");
			
			
			
			HSSFRow subHeader2= firstSheet.createRow(4);
			HSSFCell subHeader2Col0 = subHeader2.createCell((short)3);
			subHeader2Col0.setCellValue(groupName);
			
			HSSFRow toHeader= firstSheet.createRow(8);
			HSSFCell toHeaderCell = toHeader.createCell((short)1);
			toHeaderCell.setCellValue("Kepada YTH");
			
			HSSFRow contactPersonHeader= firstSheet.createRow(9);
			HSSFCell cpHeaderCell = contactPersonHeader.createCell((short)1);
			cpHeaderCell.setCellValue(payment.getBatchClaim().getClientId().getClientName());
			
			HSSFCell dibayarkanCell = contactPersonHeader.createCell((short)5);
			dibayarkanCell.setCellValue("Total yang harus dibayar");
			
			HSSFCell dibayarkanCellVal = contactPersonHeader.createCell((short)7);
			if (payment.getBatchClaim().getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP
					|| payment.getBatchClaim().getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){
				
				dibayarkanCellVal.setCellValue(payment.getBatchClaim().getBatchClaimFinalValue());
			}
			else {
				dibayarkanCellVal.setCellValue(payment.getBatchClaim().getBatchClaimInitialValue());
			}
			
			
			Iterator<Claim> ciIterator = claims.iterator();
			int rowNumber = 12;
			HSSFRow header= firstSheet.createRow(rowNumber);
			
			HSSFCell headerCol0 = header.createCell((short)0);
			headerCol0.setCellValue("No");
			headerCol0.setCellStyle(cellStyle);
			
			
			
			HSSFCell headerCol1 = header.createCell((short)1);
			headerCol1.setCellValue("No Peserta");		
			headerCol1.setCellStyle(cellStyle);
			
			
			
			
			HSSFCell headerCol2 = header.createCell((short)2);
			headerCol2.setCellValue("No Karyawan");
			headerCol2.setCellStyle(cellStyle);
			
			HSSFCell headerCol3 = header.createCell((short)3);
			headerCol3.setCellValue("Nama Pasien");
			headerCol3.setCellStyle(cellStyle);
			
			HSSFCell headerCol4 =header.createCell((short)4);
			headerCol4.setCellValue("Nama Pegawai");
			headerCol4.setCellStyle(cellStyle);
			
			HSSFCell headerCol5 =header.createCell((short)5);
			headerCol5.setCellValue("Status");
			headerCol5.setCellStyle(cellStyle);
			
			HSSFCell headerCol6 =header.createCell((short)6);
			headerCol6.setCellValue("Tgl Kuitansi");
			headerCol6.setCellStyle(cellStyle);
			
			HSSFCell headerCol7 =header.createCell((short)7);
			headerCol7.setCellValue("Jumlah");
			headerCol7.setCellStyle(cellStyle);
			
			HSSFCell headerCol8 =header.createCell((short)8);
			headerCol8.setCellValue("Keterangan");
			headerCol8.setCellStyle(cellStyle);
			
			int index = 1;
			double totalValue = 0.0;
			double nonPendingValue = 0.0;
			
			while (ciIterator.hasNext()){
				
				Claim ci = ciIterator.next();
				
				rowNumber += 1;
				
				if (ci != null){
					if (ci.getBatchClaimId().getBatchClaimType().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT){
						totalValue += ci.getClaimApprovedValue().doubleValue();
						
						
						if (ci.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED){
							nonPendingValue += ci.getClaimApprovedValue().doubleValue();
						}				
					}
					else {
						totalValue += ci.getClaimChargeValue().doubleValue();
						
						
						if (ci.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED){
							nonPendingValue += ci.getClaimChargeValue().doubleValue();
						}
					}
				}
				HSSFRow row = firstSheet.createRow(rowNumber);
				
				HSSFCell contentCol0 = row.createCell((short)0);
				contentCol0.setCellValue(index);
				contentCol0.setCellStyle(cellStyle);
				
				HSSFCell contentCol1 =row.createCell((short)1);
				contentCol1.setCellValue(ci.getMemberId().getCustomerPolicyNumber());
				contentCol1.setCellStyle(cellStyle);
				
				HSSFCell contentCol2 = row.createCell((short)2);
				contentCol2.setCellValue(ci.getMemberId().getParentMember().getCustomerNumber());
				contentCol2.setCellStyle(cellStyle);
				
				HSSFCell contentCol3 = row.createCell((short)3);
				contentCol3.setCellValue(ci.getMemberId().getFirstName());
				contentCol3.setCellStyle(cellStyle);
				
				HSSFCell contentCol4 =row.createCell((short)4);
				contentCol4.setCellValue(ci.getMemberId().getParentMember().getFirstName());
				contentCol4.setCellStyle(cellStyle);
				
				HSSFCell contentCol5 =row.createCell((short)5);
				contentCol5.setCellValue(ci.getClaimStatus().getCaseStatusName());
				contentCol5.setCellStyle(cellStyle);
				
				HSSFCell contentCol6 =row.createCell((short)6);
				contentCol6.setCellValue(df.format( ci.getAdmissionDate()));
				contentCol6.setCellStyle(cellStyle);
				
				HSSFCell contentCol7 =row.createCell((short)7);
				contentCol7.setCellValue(ci.getClaimChargeValue());
				contentCol7.setCellStyle(cellStyle);
				
				HSSFCell contentCol8 =row.createCell((short)8);
				contentCol8.setCellValue(ci.getCaseCategoryId().getCaseCategoryName());
				contentCol8.setCellStyle(cellStyle);
				
				index+=1;
				
			}
			rowNumber += 2;
			
			HSSFRow subTotalRow = firstSheet.createRow(rowNumber);
			HSSFCell subTotalCell = subTotalRow.createCell((short)6);
			subTotalCell.setCellValue("Sub-Total");
			
			
			HSSFCell subTotalValCell = subTotalRow.createCell((short)7);
			subTotalValCell.setCellValue(totalValue);
			
			rowNumber += 3;
			
			HSSFRow subFooter= firstSheet.createRow(rowNumber);
			HSSFCell subFooterCol0 = subFooter.createCell((short)0);
			subFooterCol0.setCellValue("Yang Menyerahkan,");
			HSSFCell subFooterCol1 = subFooter.createCell((short)3);
			subFooterCol1.setCellValue("Diterima Oleh,");
			HSSFCell subFooterCol2 = subFooter.createCell((short)6);
			subFooterCol2.setCellValue("Diterima,");
			
			
			rowNumber += 5;
			
			HSSFRow subFooter2= firstSheet.createRow(rowNumber);
			HSSFCell subFooter2Col0 = subFooter2.createCell((short)0);
			subFooter2Col0.setCellValue("( )");
			HSSFCell subFooter2Col1 = subFooter2.createCell((short)3);
			subFooter2Col1.setCellValue("( "+payment.getBatchClaim().getClientId().getClientName()+"  )");
			HSSFCell subFooter2Col2 = subFooter2.createCell((short)6);
			subFooter2Col2.setCellValue("( "+payment.getBatchClaim().getClientId().getClientName()+"  )");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	//Add 24032015 for Batch Claim Document Recap
	public static HSSFWorkbook downloadBatchClaim (BatchClaim batchClaim, Collection<Claim> claims, Payment payment){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {

	        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	        DecimalFormat decFormat = new DecimalFormat("#.00");
	        HSSFCellStyle cellStyle = workbook.createCellStyle();  
	        cellStyle = workbook.createCellStyle();  
	        //cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);  
	        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        cellStyle.setBorderTop((short) 1); // single line border  
	        cellStyle.setBorderBottom((short) 1); // single line border
	        cellStyle.setBorderLeft((short)1);
	        cellStyle.setBorderRight((short)1);
	        
	        HSSFCellStyle cellHeaderStyle = workbook.createCellStyle();  
	        cellHeaderStyle = workbook.createCellStyle();  
	        //cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);  
	        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
	        cellHeaderStyle.setBorderTop((short) 1); // single line border  
	        cellHeaderStyle.setBorderBottom((short) 1); // single line border
	        cellHeaderStyle.setBorderLeft((short)1);
	        cellHeaderStyle.setBorderRight((short)1);
	        cellHeaderStyle.setAlignment(CellStyle.ALIGN_CENTER);
	        cellHeaderStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
	        
			HSSFSheet firstSheet = workbook.createSheet();
			firstSheet.setColumnWidth(0, 1500);
			firstSheet.setColumnWidth(1, 5000);
			firstSheet.setColumnWidth(2, 5000);
			firstSheet.setColumnWidth(3, 7000);
			firstSheet.setColumnWidth(4, 7000);
			firstSheet.setColumnWidth(5, 7000);
			firstSheet.setColumnWidth(6, 3500);
			firstSheet.setColumnWidth(7, 3500);
			firstSheet.setColumnWidth(8, 4500);
			firstSheet.setColumnWidth(9, 4500);
			firstSheet.setColumnWidth(10, 4500);
			firstSheet.setColumnWidth(11, 4500);
			firstSheet.setColumnWidth(12, 5000);
			
			int rowNumber = 12;
			int rowStart = 2;
			
			HSSFRow reportNameHeader= firstSheet.createRow(rowStart++);
			HSSFCell reportNameSubHeaderCol0 = reportNameHeader.createCell((short)1);
			reportNameSubHeaderCol0.setCellValue("SURAT JALAN");
			HSSFCell reportNameSubHeaderCol1 = reportNameHeader.createCell((short)2);
			reportNameSubHeaderCol1.setCellValue(payment.getPaymentNumber());
			
			HSSFRow batchNoHeader= firstSheet.createRow(rowStart++);
			HSSFCell batchNoSubHeaderCol0 = batchNoHeader.createCell((short)1);
			batchNoSubHeaderCol0.setCellValue("Batch Number");
			HSSFCell batchNoSubHeaderCol1 = batchNoHeader.createCell((short)2);
			batchNoSubHeaderCol1.setCellValue(batchClaim.getBatchClaimNumber());
			
			HSSFRow invNoHeader= firstSheet.createRow(rowStart++);
			HSSFCell invNoSubHeaderCol0 = invNoHeader.createCell((short)1);
			invNoSubHeaderCol0.setCellValue("Invoice Number");
			HSSFCell invNoSubHeaderCol1 = invNoHeader.createCell((short)2);
			invNoSubHeaderCol1.setCellValue(batchClaim.getInvoiceNumber());
			
			HSSFRow recipientHeader= firstSheet.createRow(rowStart++);
			HSSFCell recSubHeaderCol0 = recipientHeader.createCell((short)1);
			recSubHeaderCol0.setCellValue("Provider Name");
			HSSFCell recSubHeaderCol1 = recipientHeader.createCell((short)2);
			recSubHeaderCol1.setCellValue((batchClaim.getProviderId() != null?batchClaim.getProviderId().getProviderName():""));
			
			
			HSSFRow recDateHeader= firstSheet.createRow(rowStart++);
			HSSFCell recDateHeaderCol0 = recDateHeader.createCell((short)1);
			recDateHeaderCol0.setCellValue("Received Date");
			HSSFCell recDateHeaderCol1 = recDateHeader.createCell((short)2);
			recDateHeaderCol1.setCellValue((batchClaim.getBatchClaimDate()!=null?df.format(batchClaim.getBatchClaimDate()):""));
			
			firstSheet.addMergedRegion(new CellRangeAddress(rowStart, rowStart, 0, rowNumber));
			HSSFRow subHeader= firstSheet.createRow(rowStart++);
			HSSFCell subHeaderCol0 = subHeader.createCell((short)0);
			subHeaderCol0.setCellValue("FORMULIR PENGAJUAN KLAIM");
			CellUtil.setAlignment(subHeaderCol0, workbook, CellStyle.ALIGN_CENTER);
			
			rowStart++;
			HSSFRow toHeader= firstSheet.createRow(rowStart++);
			HSSFCell toHeaderCell = toHeader.createCell((short)1);
			toHeaderCell.setCellValue("Kepada YTH");
			
			HSSFRow contactPersonHeader= firstSheet.createRow(rowStart++);
			HSSFCell cpHeaderCell = contactPersonHeader.createCell((short)1);
			cpHeaderCell.setCellValue(batchClaim.getClientId().getClientName());
			
			HSSFCell dibayarkanCell = contactPersonHeader.createCell((short)7);
			dibayarkanCell.setCellValue("Total yang harus dibayar");
			
			HSSFCell dibayarkanCellVal = contactPersonHeader.createCell((short)9);
//			if (batchClaim.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER_GROUP
//					|| batchClaim.getPaymentRecipient().getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER){		
//				dibayarkanCellVal.setCellValue(batchClaim.getBatchClaimFinalValue());
//			}
//			else {
//				dibayarkanCellVal.setCellValue(batchClaim.getBatchClaimInitialValue());
//			}
			
			rowStart++;
			Iterator<Claim> ciIterator = claims.iterator();
			
			HSSFRow header1= firstSheet.createRow(rowStart++);
			HSSFRow header2= firstSheet.createRow(rowStart++);
			firstSheet.addMergedRegion(new CellRangeAddress(rowStart-2, rowStart-2, 8, 10));
			
			HSSFCell headerCell8 = header1.createCell((short)8);
			headerCell8.setCellValue("Jumlah");
			headerCell8.setCellStyle(cellStyle);
			
			HSSFCell headerCell9 = header1.createCell((short)9);
			headerCell9.setCellStyle(cellStyle);
			HSSFCell headerCell10 = header1.createCell((short)10);
			headerCell10.setCellStyle(cellStyle);
//			HSSFCell headerCell4 = header1.createCell((short)10);
//			headerCell4.setCellStyle(cellStyle);
			CellUtil.setAlignment(headerCell8, workbook, CellStyle.ALIGN_CENTER);
			
			HSSFCell headerCol0 = header1.createCell((short)0);
			headerCol0.setCellValue("No");
			headerCol0.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col0 = header2.createCell((short)0);
			header2Col0.setCellStyle(cellHeaderStyle);
			
			HSSFCell headerCol1 = header1.createCell((short)1);
			headerCol1.setCellValue("No Peserta");		
			headerCol1.setCellStyle(cellHeaderStyle);	
			HSSFCell header2Col1 = header2.createCell((short)1);
			header2Col1.setCellStyle(cellHeaderStyle);		
			
			HSSFCell headerCol2 = header1.createCell((short)2);
			headerCol2.setCellValue("No Karyawan");
			headerCol2.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col2 = header2.createCell((short)2);
			header2Col2.setCellStyle(cellStyle);
			
			HSSFCell headerCol3 =header1.createCell((short)3);
			headerCol3.setCellValue("No. Klaim");
			headerCol3.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col3 =header2.createCell((short)3);
			header2Col3.setCellStyle(cellStyle);
			
			HSSFCell headerCol4 =header1.createCell((short)4);
			headerCol4.setCellValue("Nama Pasien");
			headerCol4.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col4 =header2.createCell((short)4);
			header2Col4.setCellStyle(cellStyle);
			
			HSSFCell headerCol5 =header1.createCell((short)5);
			headerCol5.setCellValue("Nama Pegawai");
			headerCol5.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col5 =header2.createCell((short)5);
			header2Col5.setCellStyle(cellStyle);
			
			HSSFCell headerCol6 =header1.createCell((short)6);
			headerCol6.setCellValue("Status");
			headerCol6.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col6 =header2.createCell((short)6);
			header2Col6.setCellStyle(cellStyle);
			
			HSSFCell headerCol7 =header1.createCell((short)7);
			headerCol7.setCellValue("Tgl Kuitansi");
			headerCol7.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col7 =header2.createCell((short)7);
			header2Col7.setCellStyle(cellStyle);
			
			//HSSFCell headerCol7 =header1.createCell((short)7);
			//headerCol7.setCellValue("Jumlah");
			//headerCol7.setCellStyle(cellStyle);
			
			HSSFCell headerCol11 =header1.createCell((short)11);
			headerCol11.setCellValue("Keterangan");
			headerCol11.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col11 =header2.createCell((short)11);
			header2Col11.setCellStyle(cellStyle);
			
			HSSFCell headerCol12 =header1.createCell((short)12);
			headerCol12.setCellValue("Remarks");
			headerCol12.setCellStyle(cellHeaderStyle);
			HSSFCell header2Col12 =header2.createCell((short)12);
			header2Col12.setCellStyle(cellStyle);
			
			int mergeCol = 8;
			int colSize = 13;
			for(int i = 0; i<mergeCol;i++){
				firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber+1, i, i));
			}
			for(int i = mergeCol+3; i<colSize;i++){
				firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber+1, i, i));
			}
			
			
			HSSFCell headerColCharge =header2.createCell((short)8);
			headerColCharge.setCellValue("Tagihan");
			headerColCharge.setCellStyle(cellHeaderStyle);
			
			HSSFCell headerColBenefit =header2.createCell((short)9);
			headerColBenefit.setCellValue("Ditanggung");
			headerColBenefit.setCellStyle(cellHeaderStyle);
			
			HSSFCell headerColExcess =header2.createCell((short)10);
			headerColExcess.setCellValue("Tidak Ditanggung");
			headerColExcess.setCellStyle(cellHeaderStyle);
			
			rowNumber++;
			
			int index = 1;
			double totalValue = 0.0;
			double nonPendingValue = 0.0;
			
			while (ciIterator.hasNext()){
				
				Claim ci = ciIterator.next();
				
				rowNumber += 1;
				
				if (ci != null){
					//if (ci.getBatchClaimId().getBatchClaimType().getClaimTypeId().intValue() == ClaimType.REIMBURESEMENT){
					//	totalValue += ci.getClaimApprovedValue().doubleValue();
					//	
					//	
					//	if (ci.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED){
					//		nonPendingValue += ci.getClaimApprovedValue().doubleValue();
					//	}				
					//}
					//else {
					//	totalValue += ci.getClaimChargeValue().doubleValue();
					//	
					//	
					//	if (ci.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED){
					//		nonPendingValue += ci.getClaimChargeValue().doubleValue();
					//	}
					//}
					totalValue += ci.getClaimApprovedValue().doubleValue();
					
					if (ci.getClaimStatus().getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED){
						nonPendingValue += ci.getClaimApprovedValue().doubleValue();
					}	
				}
				HSSFRow row = firstSheet.createRow(rowNumber);
				
				HSSFCell contentCol0 = row.createCell((short)0);
				contentCol0.setCellValue(index);
				contentCol0.setCellStyle(cellStyle);
				
				HSSFCell contentCol1 =row.createCell((short)1);
				System.out.println("CLAIM MEMBER: "+ci.getMemberId().getCustomerPolicyNumber());
				System.out.println("MEMBER : "+ci.getMemberName());
				contentCol1.setCellValue(ci.getMemberId().getCustomerPolicyNumber());
				contentCol1.setCellStyle(cellStyle);
				
				HSSFCell contentCol2 =row.createCell((short)2);
				contentCol2.setCellValue(ci.getMemberId().getParentMember().getCustomerNumber());
				contentCol2.setCellStyle(cellStyle);
				
				HSSFCell contentCol3 = row.createCell((short)3);
				contentCol3.setCellValue(ci.getClaimNumber());
				contentCol3.setCellStyle(cellStyle);
				
				HSSFCell contentCol4 = row.createCell((short)4);
				contentCol4.setCellValue(ci.getMemberId().getFirstName());
				contentCol4.setCellStyle(cellStyle);
				
				HSSFCell contentCol5 =row.createCell((short)5);
				contentCol5.setCellValue((ci.getMemberId().getParentMember()!=null?
						ci.getMemberId().getParentMember().getFirstName():""));
				contentCol5.setCellStyle(cellStyle);
				
				HSSFCell contentCol6 =row.createCell((short)6);
				contentCol6.setCellValue(ci.getClaimStatus().getCaseStatusName());
				contentCol6.setCellStyle(cellStyle);
				
				HSSFCell contentCol7 =row.createCell((short)7);
				contentCol7.setCellValue(ci.getAdmissionDate()!=null?df.format(ci.getAdmissionDate()):"");
				contentCol7.setCellStyle(cellStyle);
				
				HSSFCell contentCol8 =row.createCell((short)8);
				contentCol8.setCellValue(ci.getClaimChargeValue());
				contentCol8.setCellStyle(cellStyle);
				
				HSSFCell contentCol9 =row.createCell((short)9);
				contentCol9.setCellValue(ci.getClaimApprovedValue());
				contentCol9.setCellStyle(cellStyle);
				
				HSSFCell contentCol10 =row.createCell((short)10);
				contentCol10.setCellValue(ci.getClaimExcessValue());
				contentCol10.setCellStyle(cellStyle);
				
				HSSFCell contentCol11 =row.createCell((short)11);
				contentCol11.setCellValue(ci.getCaseCategoryId().getCaseCategoryName());
				contentCol11.setCellStyle(cellStyle);
				
				HSSFCell contentCol12 =row.createCell((short)12);
				contentCol12.setCellValue(ci.getClaimRemarks());
				contentCol12.setCellStyle(cellStyle);
				
				index+=1;
				
			}
			rowNumber += 2;
			
			dibayarkanCellVal.setCellValue(totalValue);
			HSSFRow subTotalRow = firstSheet.createRow(rowNumber);
			HSSFCell subTotalCell = subTotalRow.createCell((short)7);
			subTotalCell.setCellValue("Sub-Total");
			
			
			HSSFCell subTotalValCell = subTotalRow.createCell((short)9);
			subTotalValCell.setCellValue(totalValue);
			
			rowNumber += 3;
			
			HSSFRow subFooter= firstSheet.createRow(rowNumber);
			HSSFCell subFooterCol0 = subFooter.createCell((short)2);
			subFooterCol0.setCellValue("Yang Menyerahkan,");
			HSSFCell subFooterCol1 = subFooter.createCell((short)5);
			subFooterCol1.setCellValue("Diterima Oleh,");
			HSSFCell subFooterCol2 = subFooter.createCell((short)8);
			subFooterCol2.setCellValue("Diterima,");
			
			
			rowNumber += 5;
			
			HSSFRow subFooter2= firstSheet.createRow(rowNumber);
			HSSFCell subFooter2Col0 = subFooter2.createCell((short)2);
			subFooter2Col0.setCellValue("( )");
			HSSFCell subFooter2Col1 = subFooter2.createCell((short)5);
			subFooter2Col1.setCellValue("( "+batchClaim.getClientId().getClientName()+"  )");
			HSSFCell subFooter2Col2 = subFooter2.createCell((short)8);
			subFooter2Col2.setCellValue("( "+batchClaim.getClientId().getClientName()+"  )");
			
//			for(int i = 0; i < colSize; i++) {
//            	firstSheet.autoSizeColumn((short)i);
//            }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	public static HSSFWorkbook downloadRejectedClaimReport(Collection<RejectedClaim> claimList) {
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

            Iterator<RejectedClaim> eventIterator = claimList.iterator();
            int rowNumber = 4;
            HSSFRow header = firstSheet.createRow(0);

            HSSFRow claimHeader = firstSheet.createRow(3);

            HSSFCell nameHCell = claimHeader.createCell((short) 0);
            nameHCell.setCellValue("Claim Number");
            nameHCell.setCellStyle(style);


            HSSFCell batchNoHCell = claimHeader.createCell((short) 1);
            batchNoHCell.setCellValue("Provider");
            batchNoHCell.setCellStyle(style);

            HSSFCell claimNoHCell = claimHeader.createCell((short) 2);
            claimNoHCell.setCellValue("Coverage");
            claimNoHCell.setCellStyle(style);

            HSSFCell benefitHCell = claimHeader.createCell((short) 3);
            benefitHCell.setCellValue("Member Name");
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
            admissionHCell.setCellValue("Admission Date");
            admissionHCell.setCellStyle(style);

            HSSFCell dischargeHCell = claimHeader.createCell((short) 8);
            dischargeHCell.setCellValue("Discharge Date");
            dischargeHCell.setCellStyle(style);
            
            HSSFCell diagnosisHCell = claimHeader.createCell((short) 9);
            diagnosisHCell.setCellValue("Diagnosis");
            diagnosisHCell.setCellStyle(style);
            
            HSSFCell anamnesaHCell = claimHeader.createCell((short) 10);
            anamnesaHCell.setCellValue("Reason");
            anamnesaHCell.setCellStyle(style);

            
            while (eventIterator.hasNext()) {

            	
                RejectedClaim theCase = eventIterator.next();


                rowNumber += 1;

                                
                HSSFRow claimMember = firstSheet.createRow(rowNumber);
                claimMember.createCell((short) 0).setCellValue(theCase.getClaimId().getClaimNumber());
                claimMember.createCell((short) 1).setCellValue(theCase.getClaimId().getProviderId().getProviderName());
                claimMember.createCell((short) 2).setCellValue(theCase.getClaimId().getCaseCategoryId().getCaseCategoryCode());
                claimMember.createCell((short) 3).setCellValue(theCase.getClaimId().getMemberId().getFirstName());
                claimMember.createCell((short) 4).setCellValue(theCase.getClaimId().getCardNumber());
                claimMember.createCell((short) 5).setCellValue(theCase.getClaimId().getMemberId().getClientName());
                claimMember.createCell((short) 6).setCellValue(theCase.getClaimId().getMemberId().getGroupName());
                claimMember.createCell((short) 7).setCellValue(df.format(theCase.getClaimId().getAdmissionDate()));
                claimMember.createCell((short) 8).setCellValue(df.format(theCase.getClaimId().getDischargeDate()) );
                claimMember.createCell((short) 9).setCellValue(theCase.getClaimId().getDiagnosisId().getDiagnosisName());
                claimMember.createCell((short) 10).setCellValue(theCase.getDescription());
                
                
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return workbook;
    }
}
