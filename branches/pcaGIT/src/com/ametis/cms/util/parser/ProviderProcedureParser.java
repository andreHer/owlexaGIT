/**
 * 
 */
package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Procedure;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderProcedure;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProcedureService;
import com.ametis.cms.service.ProviderProcedureService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

/**
 * @author Mashuri Hasan
 *
 */
public class ProviderProcedureParser {
	private ProviderService providerService;
	private ClientService clientService;
	private MemberGroupService memberGroupService;
	private PolicyService policyService;
	private ProcedureService procedureService;
	private ProviderProcedureService providerProcedureService;
	
	public ProviderService getProviderService() {
		return providerService;
	}



	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}



	public ClientService getClientService() {
		return clientService;
	}



	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}



	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}



	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}



	public PolicyService getPolicyService() {
		return policyService;
	}



	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}



	public ProcedureService getProcedureService() {
		return procedureService;
	}



	public void setProcedureService(ProcedureService procedureService) {
		this.procedureService = procedureService;
	}



	public ProviderProcedureService getProviderProcedureService() {
		return providerProcedureService;
	}



	public void setProviderProcedureService(
			ProviderProcedureService providerProcedureService) {
		this.providerProcedureService = providerProcedureService;
	}



	public void parseFile(File theFile, ArrayList<String> errorList) {
		

		ActionUser user = new ActionUser();
		User theUser = new User();
		theUser.setUsername("parser");
		user.setUser(theUser);

		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(theFile);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Cell cellProviderMID = row.getCell(0);
				Cell cellTindakan = row.getCell(1);
				Cell cellSVip = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginSVip = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonSVip = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellVip = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginVip = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonVip = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell cellKelas1 = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginKelas1 = row.getCell(9, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonKelas1 = row.getCell(10, Row.CREATE_NULL_AS_BLANK);
				Cell cellKelas2 = row.getCell(11, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginKelas2 = row.getCell(12, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonKelas2 = row.getCell(13, Row.CREATE_NULL_AS_BLANK);
				Cell cellKelas3 = row.getCell(14, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginKelas3 = row.getCell(15, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonKelas3 = row.getCell(16, Row.CREATE_NULL_AS_BLANK);
				Cell cellRj = row.getCell(17, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginRj = row.getCell(18, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonRj = row.getCell(19, Row.CREATE_NULL_AS_BLANK);
				//PENAMBAHAN FIELD DAN VALIDASI BY AULIA R.
				Cell cellClientId = row.getCell(20);
				Cell cellMemberGroupId = row.getCell(21);
				Cell cellPolicyNumber = row.getCell(22);

				String strProviderMID = cellProviderMID.getStringCellValue();
				String strTindakan = ParsingUtil.getCellValueAsString(cellTindakan);

				Double dblSVip = ParsingUtil.getCellValueAsNumeric(cellSVip);
				Double dblMarginSVIP = ParsingUtil.getCellValueAsNumeric(cellmarginSVip);
				System.out.println("[ProviderProcedure-Parser] dblMarginSVIP " + dblMarginSVIP);
				Double dblDiskonSVIP = ParsingUtil.getCellValueAsNumeric(celldiskonSVip);
				if(dblMarginSVIP == null && dblDiskonSVIP != null ){
					dblSVip -= (dblSVip * dblDiskonSVIP/100);
				}else if(dblDiskonSVIP == null && dblMarginSVIP != null){
					dblSVip += (dblSVip * dblMarginSVIP/100) ;
				}else if(dblDiskonSVIP != null && dblMarginSVIP != null){
					dblSVip += ((dblSVip * dblMarginSVIP/100) - ((dblSVip * dblMarginSVIP/100) * dblDiskonSVIP/100));
				}

				Double dblVip = ParsingUtil.getCellValueAsNumeric(cellVip);
				Double dblMarginVIP = ParsingUtil.getCellValueAsNumeric(cellmarginVip);
				Double dblDiskonVIP = ParsingUtil.getCellValueAsNumeric(celldiskonVip);
				if(dblMarginVIP == null && dblDiskonVIP != null){
					dblVip -= (dblVip * dblDiskonVIP/100);
				}else if(dblDiskonVIP == null && dblMarginVIP != null){
					dblVip += (dblVip * dblMarginVIP/100) ;
				}else if(dblDiskonVIP != null && dblMarginVIP != null){
					dblVip += ((dblVip * dblMarginVIP/100) - ((dblVip * dblMarginVIP/100) * dblDiskonVIP/100));
				}

				Double dblKelas1 = ParsingUtil.getCellValueAsNumeric(cellKelas1);
				Double dblMarginKelas1 = ParsingUtil.getCellValueAsNumeric(cellmarginKelas1);
				Double dblDiskonKelas1 = ParsingUtil.getCellValueAsNumeric(celldiskonKelas1);
				if(dblMarginKelas1 == null && dblDiskonKelas1 != null){
					dblKelas1 -= (dblKelas1 * dblDiskonKelas1/100);
				}else if(dblDiskonKelas1 == null && dblMarginKelas1 != null){
					dblKelas1 += (dblKelas1 * dblMarginKelas1/100) ;
				}else if(dblDiskonKelas1 != null && dblMarginKelas1 != null){
					dblKelas1 += ((dblKelas1*dblMarginKelas1/100) - ((dblKelas1*dblMarginKelas1/100) * dblDiskonKelas1/100));
				}

				Double dblKelas2 = ParsingUtil.getCellValueAsNumeric(cellKelas2);
				Double dblMarginKelas2 = ParsingUtil.getCellValueAsNumeric(cellmarginKelas2);
				Double dblDiskonKelas2 = ParsingUtil.getCellValueAsNumeric(celldiskonKelas2);
				if(dblMarginKelas2 == null && dblDiskonKelas2 != null){
					dblKelas2 -= (dblKelas2 * dblDiskonKelas2/100);
				}else if(dblDiskonKelas2 == null && dblMarginKelas2 != null){
					dblKelas2 += (dblKelas2 * dblMarginKelas2/100) ;
				}else if(dblDiskonKelas2 != null && dblMarginKelas2 != null){
					dblKelas2 += ((dblKelas2*dblMarginKelas2/100) - ((dblKelas2*dblMarginKelas2/100) * dblDiskonKelas2/100));
				}

				Double dblKelas3 = ParsingUtil.getCellValueAsNumeric(cellKelas3);
				Double dblMarginKelas3 = ParsingUtil.getCellValueAsNumeric(cellmarginKelas3);
				Double dblDiskonKelas3 = ParsingUtil.getCellValueAsNumeric(celldiskonKelas3);
				if(dblMarginKelas3 == null && dblDiskonKelas3 != null){
					dblKelas3 -= (dblKelas3 * dblDiskonKelas3/100);
				}else if(dblDiskonKelas3 == null && dblMarginKelas3 != null){
					dblKelas3 += (dblKelas3 * dblMarginKelas3/100) ;
				}else if(dblDiskonKelas3 != null && dblMarginKelas3 != null){
					dblKelas3 += ((dblKelas3*dblMarginKelas3/100) - ((dblKelas3*dblMarginKelas3/100) * dblDiskonKelas3/100));
				}

				Double dblRj = ParsingUtil.getCellValueAsNumeric(cellRj);
				Double dblMarginRJ = ParsingUtil.getCellValueAsNumeric(cellmarginRj);
				Double dblDiskonRJ = ParsingUtil.getCellValueAsNumeric(celldiskonRj);
				if(dblMarginRJ == null && dblDiskonRJ != null){
					dblRj -= (dblRj * dblDiskonRJ/100);
				}else if(dblDiskonRJ == null && dblMarginRJ != null){
					dblRj += (dblRj * dblMarginRJ/100) ;
				}else if(dblDiskonRJ != null && dblMarginRJ != null){
					dblRj += ((dblRj*dblMarginRJ/100) - ((dblRj*dblMarginRJ/100) * dblDiskonRJ/100));
				}

				String strClientCode = cellClientId.getStringCellValue();
				String strMemberGroupCode = cellMemberGroupId.getStringCellValue();
				String strPolicyNumber = cellPolicyNumber.getStringCellValue();


				if(strProviderMID.equalsIgnoreCase("") || strProviderMID == null) {
					String warning = "Provider MID is null";
					System.out.println("[ProviderProcedure-Parser] " + warning);
					errorList.add(warning);
				}
				else if(strTindakan.equalsIgnoreCase("") || strTindakan == null) {
					String warning = "Procedure is null";
					System.out.println("[ProviderProcedure-Parser] " + warning);
					errorList.add(warning);
				}
				else if(!strClientCode.equalsIgnoreCase("") && !strMemberGroupCode.equalsIgnoreCase("") && strPolicyNumber.equalsIgnoreCase("")){
					String warning = "Not Allowed to fill both of Client Code and Member Group Code";
					System.out.println("[ProviderProcedure-Parser] " + warning);
					errorList.add(warning);
				}
				else if(strClientCode.equalsIgnoreCase("") && strMemberGroupCode.equalsIgnoreCase("")
						&& !strPolicyNumber.equalsIgnoreCase("")){
					String warning = "Fill the field Client Code and  Member Group Code for search Policy";
					System.out.println("[ProviderProcedure-Parser] " + warning);
					errorList.add(warning);
				}
				else if(!strClientCode.equalsIgnoreCase("") && strMemberGroupCode.equalsIgnoreCase("")
						&& !strPolicyNumber.equalsIgnoreCase("")){
					String warning = "Fill the field Client Code for search Policy";
					System.out.println("[ProviderProcedure-Parser] " + warning);
					errorList.add(warning);
				}
				else if(strClientCode.equalsIgnoreCase("") && !strMemberGroupCode.equalsIgnoreCase("")
						&& !strPolicyNumber.equalsIgnoreCase("")){
					String warning = "Fill the field Member Group Code for search Policy";
					System.out.println("[ProviderProcedure-Parser] " + warning);
					errorList.add(warning);
				}else{
					System.out.println("[ProviderProcedure-Parser] Searching For Provider " + strProviderMID);
					Provider provider = providerService.getByProviderCode(strProviderMID);

					if (provider == null) {
						String warning = "Unable to Find Provider for " + cellProviderMID.getStringCellValue();
						System.out.println("[ProviderProcedure-Parser] " + warning);
						errorList.add(warning);
					} else {

						Client client = null;
						if(!strClientCode.equalsIgnoreCase("") ){
							System.out.println("[ProviderProcedure-Parser] Searching For Client " + strClientCode);
							client = clientService.searchUnique("clientCode",strClientCode);
							if (client == null) {
								String warning = "Unable to Find Client for " + strClientCode;
								System.out.println("[ProviderProcedure-Parser] " + warning);
								errorList.add(warning);
							}
						}

						MemberGroup memberGroup = null;
						if( !strMemberGroupCode.equalsIgnoreCase("") ){
							System.out.println("[ProviderProcedure-Parser] Searching For Member Group " + strMemberGroupCode);
							memberGroup = memberGroupService.searchUnique("memberGroupCode",strMemberGroupCode);
							if (memberGroup == null) {
								String warning = "Unable to Find Member Group for " + strMemberGroupCode;
								System.out.println("[ProviderProcedure-Parser] " + warning);
								errorList.add(warning);
							}
						}

						Policy policy = null;
						if(client != null && memberGroup != null && !strPolicyNumber.equalsIgnoreCase("")){
							System.out.println("[ProviderProcedure-Parser] Searching For Policy " + strPolicyNumber);
							String[] param= {"policyNumber","memberGroupId.memberGroupId","clientId.clientId","deletedStatus"}; 
			            	Object[] value= {strPolicyNumber,memberGroup.getMemberGroupId(),client.getClientId(),Integer.valueOf(0)};
							policy = policyService.searchUnique(param,value,0,1 );
							if (policy == null) {
								String warning = "Unable to Find Policy for " + strPolicyNumber;
								System.out.println("[ProviderProcedure-Parser] " + warning);
								errorList.add(warning);
							}
						}


						System.out.println("[ProviderProcedure-Parser] Searching For Procedure" + strTindakan);
						Procedure procedure = procedureService.searchUnique("procedureCode",strTindakan);
						if (procedure == null) {
							String warning = "Unable to Find Procedure for " + strTindakan;
							System.out.println("[ProviderProcedure-Parser] " + warning);
							errorList.add(warning);
						}

						if(procedure != null && client == null && memberGroup == null && policy == null ){
//							ProviderProcedureService providerProcedureService = new ProviderProcedureService();
							
							String[] param= {"providerId.providerId","procedureId.procedureId","deletedStatus"}; 
			            	Object[] value= {provider.getProviderId(),procedure.getProcedureId(),Integer.valueOf(0)};
							ProviderProcedure providerProcedure = providerProcedureService.searchUnique(param,value,0,1);


							if (providerProcedure == null) {
								providerProcedure = new ProviderProcedure();

								providerProcedure.setProviderId(provider);
								providerProcedure.setProcedureId(procedure);

								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setCreatedBy("ProviderProcedureParser");
								providerProcedure.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.create(providerProcedure, user);
							} else {
								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setModifiedBy("ProviderProcedureParser");
								providerProcedure.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.update(providerProcedure, user);
							}
						}else if(procedure != null && client != null && memberGroup == null && policy == null ){
							String[] param= {"providerId.providerId","procedureId.procedureId","clientId.clientId","deletedStatus"}; 
			            	Object[] value= {provider.getProviderId(),procedure.getProcedureId(),client.getClientId(),Integer.valueOf(0)};
							ProviderProcedure providerProcedure = providerProcedureService.searchUnique(param, value,0,1);

							if (providerProcedure == null) {
								providerProcedure = new ProviderProcedure();

								providerProcedure.setProviderId(provider);
								providerProcedure.setProcedureId(procedure);

								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setClientId(client);

								providerProcedure.setCreatedBy("ProviderProcedureParser");
								providerProcedure.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.create(providerProcedure, user);
							} else {
								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setModifiedBy("ProviderProcedureParser");
								providerProcedure.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.update(providerProcedure, user);
							}  
						}else if(procedure != null && client == null && memberGroup != null && policy == null ){
							String[] param= {"providerId.providerId","procedureId.procedureId","memberGroupId.memberGroupId","deletedStatus"}; 
			            	Object[] value= {provider.getProviderId(),procedure.getProcedureId(),memberGroup.getMemberGroupId(),Integer.valueOf(0)};
							ProviderProcedure providerProcedure = providerProcedureService.searchUnique(param,value, 0,1);

							if (providerProcedure == null) {
								providerProcedure = new ProviderProcedure();

								providerProcedure.setProviderId(provider);
								providerProcedure.setProcedureId(procedure);

								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setMemberGroupId(memberGroup);

								providerProcedure.setCreatedBy("ProviderProcedureParser");
								providerProcedure.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.create(providerProcedure, user);
							} else {
								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setModifiedBy("ProviderProcedureParser");
								providerProcedure.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.update(providerProcedure, user);
							}  
						}else  if(procedure != null && client != null && memberGroup != null && policy != null ){
							String[] param= {"providerId.providerId","procedureId.procedureId","policyId.policyId","deletedStatus"}; 
			            	Object[] value= {provider.getProviderId(),procedure.getProcedureId(),policy.getPolicyId(),Integer.valueOf(0)};
							ProviderProcedure providerProcedure = providerProcedureService.searchUnique(param,value,0,1);

							if (providerProcedure == null) {
								providerProcedure = new ProviderProcedure();

								providerProcedure.setProviderId(provider);
								providerProcedure.setProcedureId(procedure);

								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setPolicyId(policy);

								providerProcedure.setCreatedBy("ProviderProcedureParser");
								providerProcedure.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.create(providerProcedure, user);
							} else {
								providerProcedure.setSvip(dblSVip);
								providerProcedure.setMarginSVIP(dblMarginSVIP);
								providerProcedure.setDiskonSVIP(dblDiskonSVIP);
								providerProcedure.setVip(dblVip);
								providerProcedure.setMarginVIP(dblMarginVIP);
								providerProcedure.setDiskonVIP(dblDiskonVIP);
								providerProcedure.setC1(dblKelas1);
								providerProcedure.setMarginKelas1(dblMarginKelas1);
								providerProcedure.setDiskonKelas1(dblDiskonKelas1);
								providerProcedure.setC2(dblKelas2);
								providerProcedure.setMarginKelas2(dblMarginKelas2);
								providerProcedure.setDiskonKelas2(dblDiskonKelas2);
								providerProcedure.setC3(dblKelas3);
								providerProcedure.setMarginKelas3(dblMarginKelas3);
								providerProcedure.setDiskonKelas3(dblDiskonKelas3);
								providerProcedure.setRj(dblRj);
								providerProcedure.setMarginRJ(dblMarginRJ);
								providerProcedure.setDiskonRJ(dblDiskonRJ);

								providerProcedure.setModifiedBy("ProviderProcedureParser");
								providerProcedure.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerProcedure.setDeletedStatus(0);

								providerProcedureService.update(providerProcedure, user);
							}  
						}else{
							String warning = "Cannot save Provider Medicine";
							System.out.println("[ProviderProcedure-Parser] " + warning);
							errorList.add(warning);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[ProviderProcedure-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[ProviderProcedure-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[ProviderProcedure-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[ProviderProcedure-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
