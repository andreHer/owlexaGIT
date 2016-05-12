package com.ametis.cms.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.apache.poi.hssf.util.HSSFColor.GREY_25_PERCENT;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.PolicyBenefit;
import com.ametis.cms.service.PolicyBenefitService;

public class PolicyBenefitDownloader {
	public static HSSFWorkbook downloadExcel (Integer policyId, PolicyBenefitService policyBenefitService){
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		try {
			
			HSSFSheet firstSheet = workbook.createSheet();

	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
	        HSSFRow judul= firstSheet.createRow(0);
	        
	        HSSFCellStyle cellStyle = workbook.createCellStyle();  
	        cellStyle = workbook.createCellStyle();  
	        cellStyle.setBorderTop((short) 1); // single line border  
	        cellStyle.setBorderBottom((short) 1); // single line border
	        cellStyle.setBorderLeft((short)1);
	        cellStyle.setBorderRight((short)1);
	        
	        HSSFCellStyle rowStyle = workbook.createCellStyle();  
	        Font font = workbook.createFont();
	        font.setBoldweight((short)2);
	        font.setFontHeight((short) 14);
	        rowStyle = workbook.createCellStyle();  
	        rowStyle.setBorderTop((short) 1); // single line border  
	        rowStyle.setBorderBottom((short) 1); // single line border
	        rowStyle.setBorderLeft((short)1);
	        rowStyle.setBorderRight((short)1);
	        rowStyle.setFont(font);
	        rowStyle.setFillBackgroundColor(GREY_25_PERCENT.index);
	        
	        
	        Collection<PolicyBenefit> inpatient = getBenefit(policyId,
					CaseCategory.INPATIENT, policyBenefitService);
			Collection<PolicyBenefit> outpatient = getBenefit(policyId,
					CaseCategory.OUTPATIENT, policyBenefitService);
			Collection<PolicyBenefit> maternity = getBenefit(policyId,
					CaseCategory.MATERNITY, policyBenefitService);
			Collection<PolicyBenefit> dental = getBenefit(policyId,
					CaseCategory.DENTAL, policyBenefitService);
			Collection<PolicyBenefit> optical = getBenefit(policyId,
					CaseCategory.OPTICAL, policyBenefitService);

			Collection<PolicyBenefit> specialist = getBenefit(policyId,
					CaseCategory.SPECIALIST, policyBenefitService);
			
			Collection<PolicyBenefit> mcu = getBenefit(policyId,
					CaseCategory.MEDICAL_CHECK_UP, policyBenefitService);
			
			Collection<PolicyBenefit> misc = getBenefit(policyId,
					CaseCategory.MISC, policyBenefitService);
			
			Collection<PolicyBenefit> ppk1gigi = getBenefit(policyId,
					CaseCategory.GP_DENTAL, policyBenefitService);

			Collection<PolicyBenefit> ppk1Umum = getBenefit(policyId,
					CaseCategory.GP_OUTPATIENT, policyBenefitService);
	        

			int rowNumber = 3;
			
			HSSFRow inpatientHeader= firstSheet.createRow(rowNumber);
			firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, 8));
			HSSFCell headerInpatient = inpatientHeader.createCell((short)0);
			headerInpatient.setCellValue("INPATIENT");
			//inpatientHeader.setRowStyle(rowStyle);
			CellUtil.setAlignment(headerInpatient, workbook, CellStyle.ALIGN_CENTER);
			
			//CellUtil.setFont(headerInpatient, workbook, font);
			//HSSFCellUtil.setCellStyleProperty(headerInpatient, workbook, CellUtil.FILL_BACKGROUND_COLOR,
			//		HSSFCellStyle.SOLID_FOREGROUND);
			rowNumber++;
			rowNumber = policyBenefitReportGenerate(firstSheet, inpatient, rowNumber, cellStyle, workbook);
			
			rowNumber = rowNumber+2;
			
			HSSFRow outpatientHeader= firstSheet.createRow(rowNumber);
			firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, 8));
			HSSFCell headerOutpatient = outpatientHeader.createCell((short)0);
			headerOutpatient.setCellValue("OUTPATIENT");
			//outpatientHeader.setRowStyle(rowStyle);
			CellUtil.setAlignment(headerOutpatient, workbook, CellStyle.ALIGN_CENTER);
			//CellUtil.setFont(headerOutpatient, workbook, font);
			rowNumber++;
			rowNumber = policyBenefitReportGenerate(firstSheet, outpatient, rowNumber, cellStyle, workbook);
			
			rowNumber = rowNumber+2;
			
			
			HSSFRow maternityHeader= firstSheet.createRow(rowNumber);
			firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, 8));
			HSSFCell headerMaternity = maternityHeader.createCell((short)0);
			headerMaternity.setCellValue("MATERNITY");
//			//maternityHeader.setRowStyle(rowStyle);
			CellUtil.setAlignment(headerMaternity, workbook, CellStyle.ALIGN_CENTER);
			rowNumber++;
			rowNumber = policyBenefitReportGenerate(firstSheet, maternity, rowNumber, cellStyle, workbook);			
			rowNumber = rowNumber+2;
			
			HSSFRow dentalHeader= firstSheet.createRow(rowNumber);
			firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, 8));
			HSSFCell headerdental = dentalHeader.createCell((short)0);
			headerdental.setCellValue("DENTAL");
			CellUtil.setAlignment(headerdental, workbook, CellStyle.ALIGN_CENTER);
			rowNumber++;
			rowNumber = policyBenefitReportGenerate(firstSheet, dental, rowNumber, cellStyle, workbook);
			
			rowNumber = rowNumber+2;
			
			HSSFRow opticalHeader= firstSheet.createRow(rowNumber);
			firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, 8));
			HSSFCell headeroptical = opticalHeader.createCell((short)0);
			headeroptical.setCellValue("OPTICAL");
			CellUtil.setAlignment(headeroptical, workbook, CellStyle.ALIGN_CENTER);
			rowNumber++;
			rowNumber = policyBenefitReportGenerate(firstSheet, optical, rowNumber, cellStyle, workbook);
			
			rowNumber = rowNumber+2;
			
			HSSFRow mcuHeader= firstSheet.createRow(rowNumber);
			firstSheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, 0, 8));
			HSSFCell headermcu = mcuHeader.createCell((short)0);
			headermcu.setCellValue("MEDICAL CHECK UP");
			CellUtil.setAlignment(headermcu, workbook, CellStyle.ALIGN_CENTER);
			rowNumber++;
			rowNumber = policyBenefitReportGenerate(firstSheet, mcu, rowNumber, cellStyle, workbook);
			
			rowNumber = rowNumber+2;
			
			//for(int i=0;i<=8;i++){
			//	firstSheet.autoSizeColumn((short)i);
			//}
			HSSFRow row = firstSheet.getRow(5);
            for (int i = 0; i < row.getLastCellNum(); i++) {
            	firstSheet.autoSizeColumn((short)i);
            }
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	private static int policyBenefitReportGenerate(HSSFSheet firstSheet,  Collection<PolicyBenefit> policyBenefit, int rowNumber,
			HSSFCellStyle cellStyle, HSSFWorkbook workbook){
		HSSFRow header= firstSheet.createRow(rowNumber);
		
		HSSFCell headerCol0 =header.createCell((short)0);
		headerCol0.setCellValue("No");
		headerCol0.setCellStyle(cellStyle);
		
		HSSFCell headerCol1 =header.createCell((short)1);
		headerCol1.setCellValue("BENEFIT NAME");
		headerCol1.setCellStyle(cellStyle);
		
		HSSFCell headerCol2 =header.createCell((short)2);
		headerCol2.setCellValue("PROCEDURE");
		headerCol2.setCellStyle(cellStyle);
		
		HSSFCell headerCol3 =header.createCell((short)3);
		headerCol3.setCellValue("DIAGNOSIS");
		headerCol3.setCellStyle(cellStyle);
		
		HSSFCell headerCol4 =header.createCell((short)4);
		headerCol4.setCellValue("DIAGNOSIS SET");
		headerCol4.setCellStyle(cellStyle);
		
		HSSFCell headerCol5 =header.createCell((short)5);
		headerCol5.setCellValue("BENEFIT LIMIT");
		headerCol5.setCellStyle(cellStyle);
		
		HSSFCell headerCol6 =header.createCell((short)6);
		headerCol6.setCellValue("ANNUAL LIMIT");
		headerCol6.setCellStyle(cellStyle);
		
		HSSFCell headerCol7 =header.createCell((short)7);
		headerCol7.setCellValue("MAX OCCURANCE PER MEMBER");
		headerCol7.setCellStyle(cellStyle);
					
		HSSFCell headerCol8 =header.createCell((short)8);
		headerCol8.setCellValue("MAX USAGE PER MEMBER");
		headerCol8.setCellStyle(cellStyle);
		
		int index = 1;
		
		if(policyBenefit!=null){	
			Iterator<PolicyBenefit> ciIterator = policyBenefit.iterator();
			while (ciIterator.hasNext()){
				
				PolicyBenefit ci = ciIterator.next();
				
				rowNumber += 1;
				HSSFRow row = firstSheet.createRow(rowNumber);
				
				
				HSSFCell contentCol0 = row.createCell((short)0);
				contentCol0.setCellValue(index);
				contentCol0.setCellStyle(cellStyle);
				
				HSSFCell contentCol1 = row.createCell((short)1);
				if(ci.getItemCategoryId()!=null)
					contentCol1.setCellValue(ci.getItemCategoryId().getItemCategoryName());
				else
					contentCol1.setCellValue("-");
				contentCol1.setCellStyle(cellStyle);
				
				HSSFCell contentCol2 = row.createCell((short)2);
				if(ci.getProcedureId()!=null)
					contentCol2.setCellValue(ci.getProcedureId().getProcedureName());
				else
					contentCol2.setCellValue("-");
				contentCol2.setCellStyle(cellStyle);
				
				HSSFCell contentCol3 = row.createCell((short)3);
				if(ci.getDiagnosisId()!=null)
					contentCol3.setCellValue(ci.getDiagnosisId().getDescription());
				else
					contentCol3.setCellValue("-");
				contentCol3.setCellStyle(cellStyle);
				
				HSSFCell contentCol4 = row.createCell((short)4);
				if(ci.getDiagnosisSetId()!=null)
					contentCol4.setCellValue(ci.getDiagnosisSetId().getDiagnosisSetName());
				else
					contentCol4.setCellValue("-");
				contentCol4.setCellStyle(cellStyle);
				
				HSSFCell contentCol5 = row.createCell((short)5);
				if(ci.getBenefitLimit()!=null){
					if(ci.getBenefitLimit()==-1){
						contentCol5.setCellValue("AS CHARGE");
						//CellUtil.setAlignment(contentCol5, workbook, CellStyle.ALIGN_RIGHT);
						//CellStyle cStyle = workbook.createCellStyle();
						//cStyle.setAlignment(CellStyle.ALIGN_RIGHT);
						//cStyle.setVerticalAlignment(valign);
						//contentCol5.setCellStyle(cStyle)
					}else{
						contentCol5.setCellValue(ci.getBenefitLimit());
						//contentCol5.setCellType(Cell.CELL_TYPE_NUMERIC);
					}
				}else{
					contentCol5.setCellValue("");
				}
				contentCol5.setCellStyle(cellStyle);
				
				HSSFCell contentCol6 = row.createCell((short)6);
				if(ci.getAnnualBenefit()!=null){
					if(ci.getAnnualBenefit()==-1){
						contentCol6.setCellValue("AS CHARGE");
						CellUtil.setAlignment(contentCol6, workbook, CellStyle.ALIGN_RIGHT);
					}else{
						contentCol6.setCellValue(ci.getAnnualBenefit());
						//contentCol6.setCellType(Cell.CELL_TYPE_NUMERIC);
					}
				}else{
					contentCol6.setCellValue("");
				}
				contentCol6.setCellStyle(cellStyle);
				
				HSSFCell contentCol7 = row.createCell((short)7);
				if(ci.getMaxOccurancePerMember()!=null){
					if(ci.getMaxOccurancePerMember()==-1){
						contentCol7.setCellValue("UNLIMITED");
						CellUtil.setAlignment(contentCol7, workbook, CellStyle.ALIGN_RIGHT);
					}else{
						contentCol7.setCellValue(ci.getMaxOccurancePerMember());
					}
				}else{
					contentCol7.setCellValue("");
				}
				contentCol7.setCellStyle(cellStyle);
				
				HSSFCell contentCol8 = row.createCell((short)8);
				if(ci.getMaxOccurancePerCase()!=null){
					if(ci.getMaxOccurancePerCase()==-1){
						contentCol8.setCellValue("UNLIMITED");
						CellUtil.setAlignment(contentCol8, workbook, CellStyle.ALIGN_RIGHT);
					}else{
						contentCol8.setCellValue(ci.getMaxUsagePerMember());
					}
				}
				contentCol8.setCellStyle(cellStyle);
				
				index+=1;
			}
		}
	return rowNumber;

	}
	
	private static Collection<PolicyBenefit> getBenefit(Integer policyId,
			Integer benefitType, PolicyBenefitService policyBenefitService) throws Exception {

		Collection collection = null;

		Vector vLikeP = new Vector();
		Vector vLikeQ = new Vector();
		Vector vEqP = new Vector();
		Vector vEqQ = new Vector();

		vEqP.add("deletedStatus");
		vEqQ.add(Integer.valueOf(0));


		if (policyId != null) {
			vEqP.add("policyId.policyId");
			vEqQ.add(policyId);
		}
		if (benefitType != null) {
			vEqP.add("caseCategoryId.caseCategoryId");
			vEqQ.add(benefitType);
		}

		String sLikeP[] = new String[vLikeP.size()];
		vLikeP.toArray(sLikeP);
		Object sLikeQ[] = new Object[vLikeP.size()];
		vLikeQ.toArray(sLikeQ);

		String sEqP[] = new String[vEqP.size()];
		vEqP.toArray(sEqP);
		Object sEqQ[] = new Object[vEqP.size()];
		vEqQ.toArray(sEqQ);

		String required[] = new String[] { "PolicyBenefit.PolicyId",
				"PolicyBenefit.ItemCategoryId","PolicyBenefit.CaseCategoryId","PolicyBenefit.ProcedureId","PolicyBenefit.DiagnosisId"
				,"PolicyBenefit.DiagnosisSetId"};

		int total = policyBenefitService.getTotal(sLikeP,sLikeQ,sEqP,sEqQ);
		collection = policyBenefitService.search(sLikeP, sLikeQ, sEqP, sEqQ,required,0,total);

		if (collection != null && collection.size() > 0) {
			return collection;
		} else {
			return null;
		}

	}
	
}
