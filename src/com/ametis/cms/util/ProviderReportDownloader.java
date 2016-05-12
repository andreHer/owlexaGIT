package com.ametis.cms.util;

import com.ametis.cms.datamodel.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

public class ProviderReportDownloader {

    public static HSSFWorkbook generateFacilitiesReport(Provider provider,Collection<ProviderPoliklinik> poliList, 
    		Collection<ProviderDoctor> doctorList, Collection<ProviderItem> itemList ) throws Exception {
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

            //creating a custom palette for the workbook
            HSSFPalette palette = workbook.getCustomPalette();
            palette.setColorAtIndex(HSSFColor.LIGHT_BLUE.index, (byte) 165, (byte) 255, (byte) 247);
            palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 33, (byte) 208, (byte) 255);
            palette.setColorAtIndex(HSSFColor.DARK_BLUE.index, (byte) 0, (byte) 159, (byte) 255);
            palette.setColorAtIndex(HSSFColor.GREY_25_PERCENT.index, (byte) 231, (byte) 240, (byte) 254);

            HSSFCellStyle caseOpenStyle = workbook.createCellStyle();
            caseOpenStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
            caseOpenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle level1Style = workbook.createCellStyle();
            level1Style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
            level1Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle level2Style = workbook.createCellStyle();
            level2Style.setFillForegroundColor(HSSFColor.BLUE.index);
            level2Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle level3Style = workbook.createCellStyle();
            level3Style.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
            level3Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFCellStyle otherStyle = workbook.createCellStyle();
            otherStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            otherStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            // end-custom style

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

            HSSFSheet firstSheet = workbook.createSheet("Detail Provider");
            
            int rowNumber = 0;				
            rowNumber++;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);
            
            HSSFCell caseNumberCell = claimHeader.createCell(1);
            caseNumberCell.setCellValue("Provider Name");
            caseNumberCell.setCellStyle(style);

            HSSFCell memberNameCell = claimHeader.createCell(2);
            memberNameCell.setCellValue(":");
            memberNameCell.setCellStyle(style);

            HSSFCell clientCell = claimHeader.createCell(3);
            clientCell.setCellValue(provider.getProviderName());
            clientCell.setCellStyle(style);
            
            HSSFCell provCatName = claimHeader.createCell(5);
            provCatName.setCellValue("Provider Category");
            provCatName.setCellStyle(style);

            HSSFCell hospitalCell = claimHeader.createCell(6);
            hospitalCell.setCellValue(":");
            hospitalCell.setCellStyle(style);

            HSSFCell provCat = claimHeader.createCell(7);
            provCat.setCellValue(provider.getProviderCategoryId().getProviderCategoryName());
            provCat.setCellStyle(style);

            rowNumber += 1;
            HSSFRow provHeader2 = firstSheet.createRow(rowNumber);
            
            HSSFCell provStart = provHeader2.createCell(1);
            provStart.setCellValue("Contract Start");
            provStart.setCellStyle(style);

            HSSFCell provStartSpace = provHeader2.createCell(2);
            provStartSpace.setCellValue(":");
            provStartSpace.setCellStyle(style);

            HSSFCell provStartVal = provHeader2.createCell(3);
            provStartVal.setCellValue(Converter.getDateddMMMMYYYY(provider.getContractStartDate()));
            provStartVal.setCellStyle(style);          
            
            

            HSSFCell provEnd = provHeader2.createCell(5);
            provEnd.setCellValue("Contract End ");
            provEnd.setCellStyle(style);

            HSSFCell provEndSpace = provHeader2.createCell(6);
            provEndSpace.setCellValue(":");
            provEndSpace.setCellStyle(style);

            HSSFCell provEndVal = provHeader2.createCell(7);
            provEndVal.setCellValue(Converter.getDateddMMMMYYYY(provider.getContractEndDate()) );
            provEndVal.setCellStyle(style);


            int no = 0;
            
            HSSFRow rowProv = firstSheet.getRow(1);
            for (int colNum = 0; colNum < rowProv.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }
            rowNumber = 1;
            
            HSSFSheet secondSheet = workbook.createSheet("Poliklinik List");
            
			HSSFRow titleHeader = secondSheet.createRow(rowNumber);
			HSSFCell noTitle = titleHeader.createCell(1);
			noTitle.setCellValue("");
			noTitle.setCellStyle(style);
			
			rowNumber++;
			
            HSSFRow diagHeader = secondSheet.createRow(rowNumber);

            HSSFCell noCell2 = diagHeader.createCell(0);
            noCell2.setCellValue("No.");
            noCell2.setCellStyle(style);

            HSSFCell caseNumberCell2 = diagHeader.createCell(1);
            caseNumberCell2.setCellValue("Poliklinik Name");
            caseNumberCell2.setCellStyle(style);

            HSSFCell memberNameCell2 = diagHeader.createCell(2);
            memberNameCell2.setCellValue("Location");
            memberNameCell2.setCellStyle(style);

            HSSFCell clientCell2 = diagHeader.createCell(3);
            clientCell2.setCellValue("Total Room");
            clientCell2.setCellStyle(style);

            HSSFCell groupCell2 = diagHeader.createCell(4);
            groupCell2.setCellValue("Total Doctor");
            groupCell2.setCellStyle(style);

            no = 0;
	            
            for (ProviderPoliklinik poliklinik : poliList) {

                no += 1;
                rowNumber += 1;
                
                HSSFRow diagContentRow = secondSheet.createRow(rowNumber);

                String totalRoom = poliklinik.getTotalRoom() == null ? "-" : poliklinik.getTotalRoom().toString() ;
                String totalDoctor = poliklinik.getTotalDoctor() == null ? "-" : poliklinik.getTotalDoctor().toString() ;
                diagContentRow.createCell(0).setCellValue(no);		
                diagContentRow.createCell(1).setCellValue(poliklinik.getPoliklinikId().getPoliklinikName());
                diagContentRow.createCell(2).setCellValue(poliklinik.getLocation());
                diagContentRow.createCell(3).setCellValue(totalRoom);
                diagContentRow.createCell(4).setCellValue(totalDoctor);                
                
                
            }
            rowNumber += 3;
            
            no = 0;
            

            HSSFRow rowDiag = secondSheet.getRow(2);
            for (int colNum = 0; colNum < rowDiag.getLastCellNum(); colNum++) {
                secondSheet.autoSizeColumn(colNum);
            }
            
            HSSFSheet providerSheet = workbook.createSheet("Doctor List");
            
            rowNumber = 1;
			
            HSSFRow hospitalHeader = providerSheet.createRow(rowNumber);

            HSSFCell noCell3 = hospitalHeader.createCell(0);
            noCell3.setCellValue("No.");
            noCell3.setCellStyle(style);

            HSSFCell caseNumberCell3 = hospitalHeader.createCell(1);
            caseNumberCell3.setCellValue("Doctor Name");
            caseNumberCell3.setCellStyle(style);

            HSSFCell memberNameCell3 = hospitalHeader.createCell(2);
            memberNameCell3.setCellValue("Poliklinik");
            memberNameCell3.setCellStyle(style);

            HSSFCell clientCell3 = hospitalHeader.createCell(3);
            clientCell3.setCellValue("Monday");
            clientCell3.setCellStyle(style);

            HSSFCell groupCell3 = hospitalHeader.createCell(4);
            groupCell3.setCellValue("Tueday");
            groupCell3.setCellStyle(style);

            HSSFCell hospitalCell3 = hospitalHeader.createCell(5);
            hospitalCell3.setCellValue("Wednesday");
            hospitalCell3.setCellStyle(style);
            
            HSSFCell hospitalCell4 = hospitalHeader.createCell(6);
            hospitalCell4.setCellValue("Thursday");
            hospitalCell4.setCellStyle(style);
            
            HSSFCell hospitalCell5 = hospitalHeader.createCell(7);
            hospitalCell5.setCellValue("Friday");
            hospitalCell5.setCellStyle(style);
            
            HSSFCell hospitalCell6 = hospitalHeader.createCell(8);
            hospitalCell6.setCellValue("Saturday");
            hospitalCell6.setCellStyle(style);
            
            HSSFCell hospitalCell7 = hospitalHeader.createCell(9);
            hospitalCell7.setCellValue("Sunday");
            hospitalCell7.setCellStyle(style);
            
            for (ProviderDoctor doctor : doctorList) {		
                no += 1;
                rowNumber += 1;
                
                HSSFRow provContentRow = providerSheet.createRow(rowNumber);

                String poliklinik = doctor.getProviderPoliklinikId() == null ? "-" : doctor.getProviderPoliklinikId().getPoliklinikName();
                
                provContentRow.createCell(0).setCellValue(no);		
                provContentRow.createCell(1).setCellValue(doctor.getDoctorName());
                provContentRow.createCell(2).setCellValue(poliklinik);
                provContentRow.createCell(3).setCellValue(doctor.getMonday());
                provContentRow.createCell(4).setCellValue(doctor.getTuesday());                
                provContentRow.createCell(5).setCellValue(doctor.getWednesday());		                
                provContentRow.createCell(6).setCellValue(doctor.getThursday());
                provContentRow.createCell(7).setCellValue(doctor.getFriday());
                provContentRow.createCell(8).setCellValue(doctor.getSaturday());
                provContentRow.createCell(9).setCellValue(doctor.getSunday());
                
            }		       
            rowNumber += 3;
            no = 0;
			
            
            HSSFRow rowHospital = providerSheet.getRow(2);
            for (int colNum = 0; colNum < rowHospital.getLastCellNum(); colNum++) {
            	providerSheet.autoSizeColumn(colNum);
            }
           
            rowNumber = 0;
            
            HSSFSheet frequencySheet = workbook.createSheet("Provider Facilities");
				
	            rowNumber++;
	            
	            HSSFRow claimFreqHeader = frequencySheet.createRow(rowNumber);
	
	            HSSFCell noCell4 = claimFreqHeader.createCell(0);
	            noCell4.setCellValue("No.");
	            noCell4.setCellStyle(style);
	
	            HSSFCell caseNumberCell4 = claimFreqHeader.createCell(1);
	            caseNumberCell4.setCellValue("Facilities Name");
	            caseNumberCell4.setCellStyle(style);
	
	            HSSFCell memberNameCell4 = claimFreqHeader.createCell(2);
	            memberNameCell4.setCellValue("Super VIP");
	            memberNameCell4.setCellStyle(style);
	
	            HSSFCell clientCell4 = claimFreqHeader.createCell(3);
	            clientCell4.setCellValue("VIP");
	            clientCell4.setCellStyle(style);
	
	            HSSFCell groupCell4 = claimFreqHeader.createCell(4);
	            groupCell4.setCellValue("Kelas 1");
	            groupCell4.setCellStyle(style);
	
	            HSSFCell kelas2Cell = claimFreqHeader.createCell(5);
	            kelas2Cell.setCellValue("Kelas 2");
	            kelas2Cell.setCellStyle(style);
	
	            HSSFCell admissionCell4 = claimFreqHeader.createCell(6);
	            admissionCell4.setCellValue("Kelas 3");
	            admissionCell4.setCellStyle(style);
	
	            HSSFCell dischargeCell4 = claimFreqHeader.createCell(7);
	            dischargeCell4.setCellValue("Rawat Jalan");
	            dischargeCell4.setCellStyle(style);
	

	                     
	            for (ProviderItem item : itemList) {
	
	                no += 1;
	                rowNumber += 1;
	                HSSFRow freqRow = frequencySheet.createRow(rowNumber);
	
	                String svip = item.getSvip() == null ? "-" : item.getSvip().toString();
	                String vip = item.getVip() == null ? "-" : item.getVip().toString();
	                String kelas1 = item.getKelas1() == null ? "-" : item.getKelas1().toString();
	                String kelas2 = item.getKelas2() == null ? "-" : item.getKelas2().toString();
	                String kelas3 = item.getKelas3() == null ? "-" : item.getKelas3().toString();
	                String rj = item.getRawatJalan() == null ? "-" : item.getRawatJalan().toString();
	                freqRow.createCell(0).setCellValue(no);
	
	                freqRow.createCell(1).setCellValue(item.getItemId().getItemName());
	                freqRow.createCell(2).setCellValue(svip);
	                freqRow.createCell(3).setCellValue(vip);
	                freqRow.createCell(4).setCellValue(kelas1);                
	                freqRow.createCell(5).setCellValue(kelas2);
	                freqRow.createCell(6).setCellValue(kelas3);
	                freqRow.createCell(7).setCellValue(rj);                
	                
	                
	            }
	            rowNumber += 3;
	            no = 0;

            HSSFRow rowFreq = frequencySheet.getRow(2);
            for (int colNum = 0; colNum < rowFreq.getLastCellNum(); colNum++) {
            	frequencySheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
    public static HSSFWorkbook downloadExclusionReport(Collection<Provider> providerList, String subject, String type) throws Exception {
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

            int rowNumber = 3;

            HSSFRow reportHeader = firstSheet.createRow(0);
            HSSFCell reportHeaderCell = reportHeader.createCell(1);
            reportHeaderCell.setCellValue("Provider Exclusion Report");
            reportHeaderCell.setCellStyle(style);
            
            HSSFRow reportHeaderParam = firstSheet.createRow(1);
            HSSFCell reportHeaderParamCell = reportHeaderParam.createCell(1);
            reportHeaderParamCell.setCellValue(type + " : " + subject);
            reportHeaderParamCell.setCellStyle(style);
            
            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(0);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell claimNumberCell = claimHeader.createCell(1);
            claimNumberCell.setCellValue("Provider Name");
            claimNumberCell.setCellStyle(style);

            HSSFCell admissionCell = claimHeader.createCell(2);
            admissionCell.setCellValue("Provider Categor");
            admissionCell.setCellStyle(style);

            HSSFCell dischargeCell = claimHeader.createCell(3);
            dischargeCell.setCellValue("City ");
            dischargeCell.setCellStyle(style);

            HSSFCell memberCell = claimHeader.createCell(4);
            memberCell.setCellValue("Province");
            memberCell.setCellStyle(style);

            HSSFCell cardNumberCell = claimHeader.createCell(5);
            cardNumberCell.setCellValue("IP");
            cardNumberCell.setCellStyle(style);

            HSSFCell clientNameCell = claimHeader.createCell(6);
            clientNameCell.setCellValue("OP");
            clientNameCell.setCellStyle(style);

            HSSFCell groupNameCell = claimHeader.createCell(7);
            groupNameCell.setCellValue("MT");
            groupNameCell.setCellStyle(style);

            HSSFCell providerNameCell = claimHeader.createCell(8);
            providerNameCell.setCellValue("DE");
            providerNameCell.setCellStyle(style);

            HSSFCell chargesCell = claimHeader.createCell(9);
            chargesCell.setCellValue("OPT");
            chargesCell.setCellStyle(style);

            HSSFCell approvedCell = claimHeader.createCell(10);
            approvedCell.setCellValue("LAB/MCU");
            approvedCell.setCellStyle(style);

            HSSFCell paidCell = claimHeader.createCell(11);
            paidCell.setCellValue("PPK1");
            paidCell.setCellStyle(style);

            HSSFCell excessCell = claimHeader.createCell(12);
            excessCell.setCellValue("PPK2");
            excessCell.setCellStyle(style);

            HSSFCell refundCell = claimHeader.createCell(13);
            refundCell.setCellValue("PPK3");
            refundCell.setCellStyle(style);
            
            HSSFCell typeCell = claimHeader.createCell(14);
            typeCell.setCellValue("EDC");
            typeCell.setCellStyle(style);

            int index = 1;
            for (Provider provider : providerList) {
            	
                rowNumber += 1;                
                HSSFRow claimRow = firstSheet.createRow(rowNumber);

                claimRow.createCell(0).setCellValue(index);
                claimRow.createCell(1).setCellValue(provider.getProviderName()); // claim number
                claimRow.createCell(2).setCellValue(provider.getProviderCategoryId().getProviderCategoryName()); // admission date
                claimRow.createCell(3).setCellValue(provider.getCity()); // discharge date                
                claimRow.createCell(4).setCellValue(provider.getProvince()); // member firstname
                claimRow.createCell(5).setCellValue(provider.getInpatient()); // card number
                claimRow.createCell(6).setCellValue(provider.getOutpatient()); // client name
                claimRow.createCell(7).setCellValue(provider.getMaternity()); // group name
                claimRow.createCell(8).setCellValue(provider.getDental()); // provider name                
                claimRow.createCell(9).setCellValue(provider.getOptical()); // charges
                claimRow.createCell(10).setCellValue(provider.getLab()); // approved                
                claimRow.createCell(11).setCellValue(provider.getPpk1()); // paid
                claimRow.createCell(12).setCellValue(provider.getPpk2()); // excess                
                claimRow.createCell(13).setCellValue(provider.getPpk3()); // excess

                String edc = provider.getEdcCode() == null ? "NO" : "YES";
                claimRow.createCell(14).setCellValue(edc); // type

                index++;
            }

            HSSFRow row = firstSheet.getRow(3);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static HSSFWorkbook generateReportEdcTerminal(Collection<EdcTerminal> edcTerminalList, Map<Integer, Integer> totalTrxPerTerminalMap) throws Exception {
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

            HSSFCellStyle rightAlignStyle = workbook.createCellStyle();
            rightAlignStyle.setAlignment(CellStyle.ALIGN_RIGHT);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");

            HSSFSheet firstSheet = workbook.createSheet();

            int rowNumber = 0;

            HSSFRow claimHeader = firstSheet.createRow(rowNumber);

            HSSFCell noCell = claimHeader.createCell(0);
            noCell.setCellValue("No.");
            noCell.setCellStyle(style);

            HSSFCell batchNumberCell = claimHeader.createCell(1);
            batchNumberCell.setCellValue("Merchant Name");
            batchNumberCell.setCellStyle(style);

            HSSFCell memberGroupCell = claimHeader.createCell(2);
            memberGroupCell.setCellValue("Terminal ID");
            memberGroupCell.setCellStyle(style);

            HSSFCell clientCell = claimHeader.createCell(3);
            clientCell.setCellValue("Merchant ID");
            clientCell.setCellStyle(style);

            HSSFCell providerCell = claimHeader.createCell(4);
            providerCell.setCellValue("Alamat Merchant");
            providerCell.setCellStyle(style);

            HSSFCell noInvoiceCell = claimHeader.createCell(5);
            noInvoiceCell.setCellValue("Berapa Lama EDC Tidak Terpakai (hari)");
            noInvoiceCell.setCellStyle(style);

            HSSFCell tanggalInvoiceCell = claimHeader.createCell(6);
            tanggalInvoiceCell.setCellValue("Jumlah Transaksi Selama EDC Terpakai");
            tanggalInvoiceCell.setCellStyle(style);

            HSSFCell memberNameCell = claimHeader.createCell(7);
            memberNameCell.setCellValue("Contact Person");
            memberNameCell.setCellStyle(style);

            HSSFCell memberNumberCell = claimHeader.createCell(8);
            memberNumberCell.setCellValue("Telepon");
            memberNumberCell.setCellStyle(style);


            for (EdcTerminal edcTerminal : edcTerminalList) {

                Provider provider = edcTerminal.getProviderId();

                rowNumber += 1;
                HSSFRow row = firstSheet.createRow(rowNumber);

                row.createCell(0).setCellValue(rowNumber);

                row.createCell(1).setCellValue(provider.getProviderName());

                row.createCell(2).setCellValue(edcTerminal.getDeviceNumber());

                row.createCell(3).setCellValue(provider.getEdcCode());

                row.createCell(4).setCellValue(provider.getAddress());

                String diffDaysStr = "";

                Date lastTransactionDate = edcTerminal.getLastTransaction();
                if (lastTransactionDate != null) {
                    //lastTransactionDate = new Date(edcTerminal.getCreatedTime().getTime());
                    long diffDays = (new java.util.Date().getTime() - lastTransactionDate.getTime()) / (1000 * 60 * 60 * 24);
                    diffDaysStr = Long.valueOf(diffDays).toString();
                }
                if(diffDaysStr.equals("")){
                	diffDaysStr = "0";
                }
                row.createCell(5).setCellValue(diffDaysStr);
                row.createCell(6).setCellValue(totalTrxPerTerminalMap.get(edcTerminal.getId()));

                row.createCell(7).setCellValue(provider.getContactPerson());
                row.createCell(8).setCellValue(provider.getTelephone());

            }

            // auto size column
            HSSFRow row = firstSheet.getRow(0);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                firstSheet.autoSizeColumn(colNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
