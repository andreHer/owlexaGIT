package com.ametis.cms.util;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.ClaimItemDto;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.ItemService;

public class ClaimCynergyUploader {

	FileInputStream inputStream;
	private ClaimService claimService;
	private ItemService itemService;
	
	public static void main(String[] args){
		ClaimCynergyUploader uploader = new ClaimCynergyUploader("D:\\db\\Header detail claim manual.xls");
		uploader.loadContent();
	}
	public ClaimCynergyUploader(String filename){
	
		try {
			inputStream = new FileInputStream(new File(filename));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public Collection<ClaimDto> loadContent(){
		Collection<ClaimDto> result = new Vector<ClaimDto>();
		
		try {
			String mapping = "InvoiceNumber:ClaimNumber:EmployeeNumber:EmployeeName:CustomerNumber:MemberName:" +
					"Birthdate:Area:Branch:Relationship:ReceivedInsurance:RegistrationDate:InvoiceDate:CreatedTime" +
					":PaymentDate:CaseCategory:Currency:ClaimChargeValue:ClaimApprovedValue:ClaimExcessValue:ExcessPayment:" +
					"ExcessPaymentDate:DiagnoseCode:DiagnosisName:ItemCode:ItemName:ProviderName:DoctorName:ClaimType:" +
					"Remarks:PlanCode:AdmissionDate:DischargeDate";
			String[] mapArray = mapping.split(":");
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			Iterator<Row> rows = sheet.rowIterator();
			
			int i = 0;
			
			while (rows.hasNext()){
				
				Row row = rows.next();
				
				if (i > 0){
				
					Iterator<Cell> cellIterator = row.iterator();
					
					ClaimDto claim = new ClaimDto();
					ClaimItemDto itemDto = new ClaimItemDto();
					
					int x = 0;
					System.out.println("---------------------------------------------------");
					for (int z = 0; z < mapArray.length; z++){
						
						
						Cell cell = row.getCell(z);
						
						if (cell != null){
							int tipe = cell.getCellType();
							
							if (tipe == Cell.CELL_TYPE_BLANK){
								System.out.println(mapArray[z] + " = BLANK " );
							}
							if (tipe == Cell.CELL_TYPE_NUMERIC){
								if (DateUtil.isCellDateFormatted(cell)){
									System.out.println(mapArray[z] + " = " + cell.getDateCellValue());
								}
								else {
									double num = cell.getNumericCellValue();
									BigDecimal bigDec = new BigDecimal(num);
									System.out.println(mapArray[z] + " = " + bigDec.toPlainString());
								}
								
							}
							if (tipe == Cell.CELL_TYPE_BOOLEAN){
								System.out.println(mapArray[z] + " = " + cell.getBooleanCellValue());
							}
							if (tipe == Cell.CELL_TYPE_STRING){
								System.out.println(mapArray[z] + " = " + cell.getStringCellValue());
							}
						}
						else {
							System.out.println(mapArray[z] + " = BLANK " );
						}
				
						
					}
					System.out.println("---------------------------------------------------");
					/**
					while (cellIterator.hasNext()){
						String invoiceNumber = cellIterator.next().getStringCellValue();
						String claimNumber = cellIterator.next().getStringCellValue();
						String nik = cellIterator.next().getStringCellValue();
						String empName = cellIterator.next().getStringCellValue();
						String number = cellIterator.next().getStringCellValue();
						String memberName = cellIterator.next().getStringCellValue();
						String birthday = cellIterator.next().getStringCellValue();
						String area = cellIterator.next().getStringCellValue();
						String branch  = cellIterator.next().getStringCellValue();
						String relation = cellIterator.next().getStringCellValue();
						String receivedJapro = cellIterator.next().getStringCellValue();
						String receivedDate = cellIterator.next().getStringCellValue();
						String invoiceDate = cellIterator.next().getStringCellValue();
						String inputDate = cellIterator.next().getStringCellValue();
						String paymentDate = cellIterator.next().getStringCellValue();
						String serviceType = cellIterator.next().getStringCellValue();
						String currency = cellIterator.next().getStringCellValue();
						String charge = cellIterator.next().getStringCellValue();
						String approved = cellIterator.next().getStringCellValue();
						String excess = cellIterator.next().getStringCellValue();
						String excessPayment = cellIterator.next().getStringCellValue();
						String excessPayDate = cellIterator.next().getStringCellValue();
						String diagCode = cellIterator.next().getStringCellValue();
						String diagName = cellIterator.next().getStringCellValue();
						String itemCode = cellIterator.next().getStringCellValue();
						String itemName = cellIterator.next().getStringCellValue();
						String providerName = cellIterator.next().getStringCellValue();
						String doctorName = cellIterator.next().getStringCellValue();
						String claimType = cellIterator.next().getStringCellValue();
						String remarks = cellIterator.next().getStringCellValue();
						String plan = cellIterator.next().getStringCellValue();
						String admision = cellIterator.next().getStringCellValue();
						String discharge =cellIterator.next().getStringCellValue();
					
						claim.setAdmissionDate(Date.valueOf(admision));
						claim.setDischargeDate(Date.valueOf(discharge));
						claim.setPlan(plan);
						
						if (claimType != null){
							if (claimType.equalsIgnoreCase("REIMBURSEMENT")){
								claim.setClaimTypeId(ClaimType.REIMBURESEMENT);
							}
							else if (claimType.equalsIgnoreCase("PROVIDER")){
								claim.setClaimTypeId(ClaimType.CASHLESS);
							}
						}
						
						
						itemDto.setClaimNumber(claimNumber);
						itemDto.setExcessValue(Double.valueOf(excess));
						
						String[] eqParam = {"deletedStatus","claimNumber","memberId.customerPolicyNumber"};
						Object[] eqValue = {Integer.valueOf(0),claimNumber,number};
						
						String[] eqParamItem1 = {"deletedStatus","itemAlternateCode"};
						Object[] eqValueItem1 = {Integer.valueOf(0),itemCode};
						
						
						String[] eqParamItem2 = {"deletedStatus","itemAlternateCode2"};
						Object[] eqValueItem2 = {Integer.valueOf(0),itemCode};
						
						//int totalItem1 = itemService.getTotal()
						
//						int totalExistingClaim = claimService.getTotal(null,null,eqParam,eqValue);
//						
//						if (totalExistingClaim > 0){
//							
//						}
//						else {
//							
//						}
//						
						
						
					}
					*/
				}
				
				i++;
			}
			
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
		return result;
	}
	
}
