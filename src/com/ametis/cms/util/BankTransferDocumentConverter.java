package com.ametis.cms.util;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ametis.cms.datamodel.Bank;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentInstallment;
import com.ametis.cms.service.BankService;


public class BankTransferDocumentConverter {


	public static String convertToMandiri(Collection<Payment> paymentList, String fromAccount,String currency,
			String downloadDate, BankService bankService,String emailContact){
		
		String result = "";
		try {
			
			DecimalFormat formatter = new DecimalFormat("##.######");
			
			
			double totalPayment = 0.0;
			
			if (paymentList != null){
				Iterator<Payment> iteratorPayment = paymentList.iterator();
				
				while (iteratorPayment.hasNext()){
					Payment payment = iteratorPayment.next();
					if (payment != null){
						totalPayment += payment.getPaymentValue();
					}
				}
			}
			if (paymentList != null){
	
				result +="P,"+downloadDate.replace("-", "")+","+fromAccount+","+paymentList.size()+","+ formatter.format(totalPayment)+"\n";					
				Iterator<Payment> paymentIterator = paymentList.iterator();
	
				
				
				
				while (paymentIterator.hasNext()){
					Payment payment = paymentIterator.next();
					
					if (payment != null){
						String trxType = "IBU";
						String bankName = "BANK MANDIRI";
						String bankCode = "";
						String batchNumber = "";
						Bank bank = bankService.getBankByName(payment.getBankName());
						
	
						if (payment.getPaymentBatchId() != null){
							batchNumber = payment.getPaymentBatchId().getPaymentBatchNumber();
						}
						
						if (!payment.getBankName().equalsIgnoreCase("BANK MANDIRI") || !payment.getBankName().equalsIgnoreCase("MANDIRI") ){
							trxType = "LBU";
							bankName = payment.getBankName();
							
							if (bank != null){
								bankCode = bank.getBankCode();
							}
							
						}
						
						result += BankTransferDocumentConverter.formatToFriendlyAccount(payment.getAccountNumber())+","+payment.getPayeeName()
						+",,,,"+currency+","+ formatter.format(payment.getPaymentValue()) +",,"+payment.getPaymentNumber().replace("/KLMI/JAHE", "")+","+trxType+","+bankCode+","+bankName +",,,,,Y,"+emailContact+",,,Y,Y"
						+",,,,,,,,,,,,,,,,,,,"+batchNumber+"\n";
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public static String convertToMandiriInstallment(Collection<PaymentInstallment> paymentList, String fromAccount,String currency,
			String downloadDate, BankService bankService,String emailContact){
		
		String result = "";
		try {
			
			DecimalFormat formatter = new DecimalFormat("##.######");
			
			
			double totalPayment = 0.0;
			
			if (paymentList != null){
				Iterator<PaymentInstallment> iteratorPayment = paymentList.iterator();
				
				while (iteratorPayment.hasNext()){
					PaymentInstallment payment = iteratorPayment.next();
					if (payment != null){
						totalPayment += payment.getTotalValue();
					}
				}
			}
			if (paymentList != null){
	
				result +="P,"+downloadDate.replace("-", "")+","+fromAccount+","+paymentList.size()+","+ formatter.format(totalPayment)+"\n";					
				Iterator<PaymentInstallment> paymentIterator = paymentList.iterator();
	
				
				
				
				while (paymentIterator.hasNext()){
					PaymentInstallment payment = paymentIterator.next();
					
					if (payment != null){
						String trxType = "IBU";
						String bankName = "BANK MANDIRI";
						String bankCode = "";
						String batchNumber = "";
						Bank bank = bankService.getBankByName(payment.getPaymentId().getBankName());
						
	
						if (payment.getPaymentBatchId() != null){
							batchNumber = payment.getPaymentBatchId().getPaymentBatchNumber();
						}
						
						if (!payment.getPaymentId().getBankName().equalsIgnoreCase("BANK MANDIRI") || !payment.getPaymentId().getBankName().equalsIgnoreCase("MANDIRI") ){
							trxType = "LBU";
							bankName = payment.getPaymentId().getBankName();
							
							if (bank != null){
								bankCode = bank.getBankCode();
							}
							
						}
						
						result += BankTransferDocumentConverter.formatToFriendlyAccount(payment.getPaymentId().getAccountNumber())+","+payment.getPaymentId().getPayeeName()
						+",,,,"+currency+","+ formatter.format(payment.getTotalValue()) +",,"+payment.getPaymentId().getPaymentNumber().replace("/KLMI/JAHE", "")+"-"+payment.getInstallmentNumber()+","+trxType+","+bankCode+","+bankName +",,,,,Y,"+emailContact+",,,Y,Y"
						+",,,,,,,,,,,,,,,,,,,"+batchNumber+"\n";
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public static String convertToBCA (Collection<Payment> paymentList){
		String result = "";
		
		if (paymentList != null){
			
			result += "Name,Date,Amount,Acc. No,Bank Name,BI Code,Bank Branch Name,Remark 1,Remark 2,Jenis";
			result += "\n";
			Iterator<Payment> paymentIterator = paymentList.iterator();
			
			while (paymentIterator.hasNext()){
				Payment payment = paymentIterator.next();
				
				if (payment != null){
					result +="\'"+payment.getPayeeName()+",\',\'"+payment.getPaymentValue()+",\'"+ BankTransferDocumentConverter.formatToFriendlyAccount(payment.getAccountNumber()) +",\'"+payment.getBankName()+",\',\'"+payment.getBankBranch()+",\'"+payment.getPaymentNumber().replace("/KLMI/JAHE", "")+",a/n "+payment.getPayeeName()+",BCA"+"\n";
				}
			}
		}
		
		return result;
	}
	public static HSSFWorkbook downloadMandiri (Collection<Payment> paymentList, String fromAccount,String currency, BankService bankService, String downloadDate){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			HSSFSheet firstSheet = workbook.createSheet();
			
			
			firstSheet.createRow(0);
			firstSheet.createRow(1);
			firstSheet.createRow(2);
			firstSheet.createRow(3);
			firstSheet.createRow(4);
			firstSheet.createRow(5);
			
			HSSFRow header = firstSheet.createRow(6);
			
			header.createCell((short)0).setCellValue("Date");
			header.createCell((short)1).setCellValue("From Account");
			header.createCell((short)2).setCellValue("Curency");
			header.createCell((short)3).setCellValue("Amount");
			header.createCell((short)4).setCellValue("To Account");
			header.createCell((short)5).setCellValue("Transaction Type");
			header.createCell((short)6).setCellValue("Reference No");
			header.createCell((short)7).setCellValue("Remark ");
			header.createCell((short)8).setCellValue("Benef Name");
			header.createCell((short)9).setCellValue("Benef Addr1");
			header.createCell((short)10).setCellValue("Benef Addr2");
			header.createCell((short)11).setCellValue("Benef Addr3");
			header.createCell((short)12).setCellValue("Benef Bank Code");
			header.createCell((short)13).setCellValue("Benef Bank Name");
			header.createCell((short)14).setCellValue("Benef Bank Address");
			header.createCell((short)15).setCellValue("Email Address");
			header.createCell((short)16).setCellValue("Extended Payment Details");
			
			
			
			int rowNumber = 7;
			
			Iterator<Payment> iterator = paymentList.iterator();
			
			while (iterator.hasNext()){
				Payment payment = iterator.next();
				
				HSSFRow row=	firstSheet.createRow(rowNumber);
				
				String transactionType = "";
				
				if (payment.getBankName().equalsIgnoreCase("MANDIRI") || payment.getBankName().equalsIgnoreCase("BANK MANDIRI")){
					transactionType = "IBU";
				}
				else {
					transactionType = "LBU";
					
				}
				
				String bankCode = "";
				String batchNumber = "";
				Bank bank = bankService.getBankByName(payment.getBankName());
				
				if (bank != null){
					bankCode = bank.getBankCode();
				}
				if (payment.getPaymentBatchId() != null){
					batchNumber = payment.getPaymentBatchId().getPaymentBatchNumber();
				}
				row.createCell((short)0).setCellValue(downloadDate.replace("-", ""));
				row.createCell((short)1).setCellValue(fromAccount);
				row.createCell((short)2).setCellValue("IDR");
				row.createCell((short)3).setCellValue(payment.getPaymentValue().toString());
				row.createCell((short)4).setCellValue(BankTransferDocumentConverter.formatToFriendlyAccount(payment.getAccountNumber()));
				
				row.createCell((short)5).setCellValue(transactionType);
				row.createCell((short)6).setCellValue(payment.getPaymentNumber().replace("/KLMI/JAHE", ""));
				row.createCell((short)7).setCellValue("");
				row.createCell((short)8).setCellValue(payment.getPayeeName().trim());
				row.createCell((short)9).setCellValue("");
				row.createCell((short)10).setCellValue("");
				row.createCell((short)11).setCellValue("");
				row.createCell((short)12).setCellValue(bankCode.trim());
				row.createCell((short)13).setCellValue(payment.getBankName().trim());
				row.createCell((short)14).setCellValue("");
				row.createCell((short)15).setCellValue("");
				row.createCell((short)16).setCellValue(batchNumber); //"Extended Payment Details"
				
				
				
				rowNumber += 1;
				
			}
			HSSFRow row = firstSheet.getRow(6);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	public static HSSFWorkbook downloadMandiriInstallment (Collection<PaymentInstallment> paymentList, String fromAccount,String currency, BankService bankService, String downloadDate){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			HSSFSheet firstSheet = workbook.createSheet();
			
			
			firstSheet.createRow(0);
			firstSheet.createRow(1);
			firstSheet.createRow(2);
			firstSheet.createRow(3);
			firstSheet.createRow(4);
			firstSheet.createRow(5);
			
			HSSFRow header = firstSheet.createRow(6);
			
			header.createCell((short)0).setCellValue("Date");
			header.createCell((short)1).setCellValue("From Account");
			header.createCell((short)2).setCellValue("Curency");
			header.createCell((short)3).setCellValue("Amount");
			header.createCell((short)4).setCellValue("To Account");
			header.createCell((short)5).setCellValue("Transaction Type");
			header.createCell((short)6).setCellValue("Reference No");
			header.createCell((short)7).setCellValue("Remark ");
			header.createCell((short)8).setCellValue("Benef Name");
			header.createCell((short)9).setCellValue("Benef Addr1");
			header.createCell((short)10).setCellValue("Benef Addr2");
			header.createCell((short)11).setCellValue("Benef Addr3");
			header.createCell((short)12).setCellValue("Benef Bank Code");
			header.createCell((short)13).setCellValue("Benef Bank Name");
			header.createCell((short)14).setCellValue("Benef Bank Address");
			header.createCell((short)15).setCellValue("Email Address");
			header.createCell((short)16).setCellValue("Extended Payment Details");
			
			
			
			int rowNumber = 7;
			
			Iterator<PaymentInstallment> iterator = paymentList.iterator();
			
			while (iterator.hasNext()){
				PaymentInstallment payment = iterator.next();
				
				HSSFRow row=	firstSheet.createRow(rowNumber);
				
				String transactionType = "";
				
				if (payment.getPaymentId().getBankName().equalsIgnoreCase("MANDIRI") || payment.getPaymentId().getBankName().equalsIgnoreCase("BANK MANDIRI")){
					transactionType = "IBU";
				}
				else {
					transactionType = "LBU";
					
				}
				
				String bankCode = "";
				String batchNumber = "";
				Bank bank = bankService.getBankByName(payment.getPaymentId().getBankName());
				
				if (bank != null){
					bankCode = bank.getBankCode();
				}
				if (payment.getPaymentBatchId() != null){
					batchNumber = payment.getPaymentBatchId().getPaymentBatchNumber();
				}
				row.createCell((short)0).setCellValue(downloadDate.replace("-", ""));
				row.createCell((short)1).setCellValue(fromAccount);
				row.createCell((short)2).setCellValue("IDR");
				row.createCell((short)3).setCellValue(payment.getPaymentId().getPaymentValue().toString());
				row.createCell((short)4).setCellValue(BankTransferDocumentConverter.formatToFriendlyAccount(payment.getPaymentId().getAccountNumber()));
				
				row.createCell((short)5).setCellValue(transactionType);
				row.createCell((short)6).setCellValue(payment.getPaymentId().getPaymentNumber().replace("/KLMI/JAHE", ""));
				row.createCell((short)7).setCellValue("");
				row.createCell((short)8).setCellValue(payment.getPaymentId().getPayeeName().trim());
				row.createCell((short)9).setCellValue("");
				row.createCell((short)10).setCellValue("");
				row.createCell((short)11).setCellValue("");
				row.createCell((short)12).setCellValue(bankCode.trim());
				row.createCell((short)13).setCellValue(payment.getPaymentId().getBankName().trim());
				row.createCell((short)14).setCellValue("");
				row.createCell((short)15).setCellValue("");
				row.createCell((short)16).setCellValue(batchNumber); //"Extended Payment Details"
				
				
				
				rowNumber += 1;
				
			}
			HSSFRow row = firstSheet.getRow(6);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}

	public static HSSFWorkbook downloadBCA (Collection<Payment> paymentList, Date downloadDate){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			
	        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

			HSSFSheet firstSheet = workbook.createSheet();
			
			Iterator<Payment> paymentIterator = paymentList.iterator();
			int rowNumber = 0;
			HSSFRow header= firstSheet.createRow(0);
			header.createCell((short)0).setCellValue("Name");
			header.createCell((short)1).setCellValue("Trans Date");
			header.createCell((short)2).setCellValue("Amount");
			header.createCell((short)3).setCellValue("Acc. No");
			header.createCell((short)4).setCellValue("Bank Name");
			header.createCell((short)5).setCellValue("BI Code");
			header.createCell((short)6).setCellValue("Bank Branch Name");
			header.createCell((short)7).setCellValue("Remark 1");
			header.createCell((short)8).setCellValue("Remark 2");
			header.createCell((short)9).setCellValue("Jenis");
			
			while (paymentIterator.hasNext()){
				
				Payment payment = paymentIterator.next();
				
				rowNumber += 1;
				HSSFRow row = firstSheet.createRow(rowNumber);
				
				row.createCell((short)0).setCellValue(payment.getPayeeName().trim());
				row.createCell((short)1).setCellValue(df.format(downloadDate));
				row.createCell((short)2).setCellValue(payment.getPaymentValue());
				row.createCell((short)3).setCellValue(BankTransferDocumentConverter.formatToFriendlyAccount(payment.getAccountNumber()));
				row.createCell((short)4).setCellValue(payment.getBankName());
				row.createCell((short)5).setCellValue("1111111");
				row.createCell((short)6).setCellValue("JAKARTA");
				row.createCell((short)7).setCellValue("");
				row.createCell((short)8).setCellValue(payment.getPaymentNumber().replace("/KLMI/JAHE", ""));
				row.createCell((short)9).setCellValue("BCA");
				
			}
			
			HSSFRow row = firstSheet.getRow(0);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	public static HSSFWorkbook downloadBCAInstallment (Collection<PaymentInstallment> paymentList, Date downloadDate){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			
	        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

			HSSFSheet firstSheet = workbook.createSheet();
			
			Iterator<PaymentInstallment> paymentIterator = paymentList.iterator();
			int rowNumber = 0;
			HSSFRow header= firstSheet.createRow(0);
			header.createCell((short)0).setCellValue("Name");
			header.createCell((short)1).setCellValue("Trans Date");
			header.createCell((short)2).setCellValue("Amount");
			header.createCell((short)3).setCellValue("Acc. No");
			header.createCell((short)4).setCellValue("Bank Name");
			header.createCell((short)5).setCellValue("BI Code");
			header.createCell((short)6).setCellValue("Bank Branch Name");
			header.createCell((short)7).setCellValue("Remark 1");
			header.createCell((short)8).setCellValue("Remark 2");
			header.createCell((short)9).setCellValue("Jenis");
			
			while (paymentIterator.hasNext()){
				
				PaymentInstallment payment = paymentIterator.next();
				
				rowNumber += 1;
				HSSFRow row = firstSheet.createRow(rowNumber);
				
				row.createCell((short)0).setCellValue(payment.getPaymentId().getPayeeName().trim());
				row.createCell((short)1).setCellValue(df.format(downloadDate));
				row.createCell((short)2).setCellValue(payment.getTotalValue());
				row.createCell((short)3).setCellValue(BankTransferDocumentConverter.formatToFriendlyAccount(payment.getPaymentId().getAccountNumber()));
				row.createCell((short)4).setCellValue(payment.getPaymentId().getBankName());
				row.createCell((short)5).setCellValue("1111111");
				row.createCell((short)6).setCellValue("JAKARTA");
				row.createCell((short)7).setCellValue("");
				row.createCell((short)8).setCellValue(payment.getPaymentId().getPaymentNumber().replace("/KLMI/JAHE", "")+"-"+payment.getInstallmentNumber());
				row.createCell((short)9).setCellValue("BCA");
				
			}
			
			HSSFRow row = firstSheet.getRow(0);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	public static String formatToFriendlyAccount (String accountNumber){
		String result = "";
		
		try {
			result = accountNumber.replace(" ", "");
			result = result.replace("-", "");
			result = result.replace(".", "");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
