package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberProvider;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderSet;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberProviderService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderSetService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class MemberProviderParser {

	private MemberService memberService;

	private ProviderService providerService;

	private ProviderSetService providerSetService;

	private MemberProviderService memberProviderService;

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ProviderSetService getProviderSetService() {
		return providerSetService;
	}

	public void setProviderSetService(ProviderSetService providerSetService) {
		this.providerSetService = providerSetService;
	}

	public MemberProviderService getMemberProviderService() {
		return memberProviderService;
	}

	public void setMemberProviderService(MemberProviderService memberProviderService) {
		this.memberProviderService = memberProviderService;
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

				Cell cellFullname = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellMemberId = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
				Cell cellPolicyNumber = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellProviderMID = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellProviderSetId = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellPPK1 = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellPPK2 = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellPPK3 = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell cellInPatient = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
				Cell cellOutpatient = row.getCell(9, Row.CREATE_NULL_AS_BLANK);
				Cell cellDental = row.getCell(10, Row.CREATE_NULL_AS_BLANK);
				Cell cellMaternity = row.getCell(11, Row.CREATE_NULL_AS_BLANK);
				Cell cellOptical = row.getCell(12, Row.CREATE_NULL_AS_BLANK);
				Cell cellMcuLab = row.getCell(13, Row.CREATE_NULL_AS_BLANK);
				Cell cellAction = row.getCell(14, Row.CREATE_NULL_AS_BLANK);

				if (cellProviderMID != null) {
					cellProviderMID.setCellType(Cell.CELL_TYPE_STRING);
				}
				if (cellProviderSetId != null) {
					cellProviderSetId.setCellType(Cell.CELL_TYPE_STRING);
				}

				Integer mappingType = null;

				String strMemberId = ParsingUtil.getCellValueAsString(cellMemberId);
				String strPolicyNumber = ParsingUtil.getCellValueAsString(cellPolicyNumber);
				Member memberIdAndPolicyId = memberService.getMemberByPolicyNumber(strMemberId, strPolicyNumber);
				if (memberIdAndPolicyId == null) {
					String msg = "Existing Member/Policy Not Found " + strMemberId + " / " + strPolicyNumber;
					
					System.out.println("[MemberProviderParser] " + msg);
					errorList.add(msg);
				} else {
					String providerCode = ParsingUtil.getCellValueAsString(cellProviderMID);
					Provider provider = providerService.getByProviderCode(providerCode);

					String providerSetCode = ParsingUtil.getCellValueAsString(cellProviderSetId);
					ProviderSet providerSet = providerSetService.searchUnique("providerSetCode", providerSetCode);
					Integer ppk1 = null;
					Integer ppk2 = null;
					Integer ppk3 = null;
					Integer inpatient = null;
					Integer outpatient = null;
					Integer dental = null;
					Integer maternity = null;
					Integer optical = null;
					Integer mcuLab = null;

					if (cellPPK1 != null) {
						ppk1 = ParsingUtil.convertYesNo(cellPPK1);
					}
					if (cellPPK2 != null) {
						ppk2 = ParsingUtil.convertYesNo(cellPPK2);
					}
					if (cellPPK3 != null) {
						ppk3 = ParsingUtil.convertYesNo(cellPPK3);
					}
					if (cellInPatient != null) {
						inpatient = ParsingUtil.convertYesNo(cellInPatient);
					}
					if (cellOutpatient != null) {
						outpatient = ParsingUtil.convertYesNo(cellOutpatient);
					}
					if (cellDental != null) {
						dental = ParsingUtil.convertYesNo(cellDental);
					}
					if (cellMaternity != null) {
						maternity = ParsingUtil.convertYesNo(cellMaternity);
					}
					if (cellOptical != null) {
						optical = ParsingUtil.convertYesNo(cellOptical);
					}
					if (cellMcuLab != null) {
						mcuLab = ParsingUtil.convertYesNo(cellMcuLab);
					}

					if ((provider == null && providerSet == null)
							|| (providerCode.trim().equals("") && providerSetCode.trim().equals(""))) {
						String msg = "Unable to find Provider AND Provider Set " + providerCode + " / "
								+ providerSetCode;
						System.out.println("[MemberProviderParser] " + msg);
						errorList.add(msg);
					} else {
						if (providerSet == null || providerSetCode.trim().equals("")) {

							String[] param = { "memberId.memberId", "providerId.providerId", "deletedStatus" };
							Object[] value = { memberIdAndPolicyId.getMemberId(), provider.getProviderId(),
									Integer.valueOf(0) };

							MemberProvider memberProvider = memberProviderService.searchUnique(param, value, 0, 1);

							if (memberProvider == null) {
								String msg = "Unable to Find MemberProvider: " + strMemberId + " / " + strPolicyNumber
										+ " inserting";
								System.out.println("[MemberProviderParser] " + msg);
								errorList.add(msg);
								memberProvider = new MemberProvider();
								memberProvider.setMemberId(memberIdAndPolicyId);
								memberProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									msg = "UNKNOWN ACTION";
									System.out.println("[MemberProviderParser] " + msg);
									errorList.add(msg);
								}

								memberProvider.setPpk1(ppk1);
								memberProvider.setPpk2(ppk2);
								memberProvider.setPpk3(ppk3);
								memberProvider.setInpatient(inpatient);
								memberProvider.setOutpatient(outpatient);
								memberProvider.setDental(dental);
								memberProvider.setMaternity(maternity);
								memberProvider.setOptical(optical);
								memberProvider.setMcuLab(mcuLab);
								memberProvider.setMappingType(mappingType);
								if (memberProvider.getMappingType() != null) {
									memberProviderService.create(memberProvider, user);
								}
							} else {
								memberProvider.setMemberId(memberIdAndPolicyId);
								memberProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[MemberProviderParser] " + msg);
									errorList.add(msg);
								}

								memberProvider.setPpk1(ppk1);
								memberProvider.setPpk2(ppk2);
								memberProvider.setPpk3(ppk3);
								memberProvider.setInpatient(inpatient);
								memberProvider.setOutpatient(outpatient);
								memberProvider.setDental(dental);
								memberProvider.setMaternity(maternity);
								memberProvider.setOptical(optical);
								memberProvider.setMcuLab(mcuLab);

								memberProvider.setMappingType(mappingType);
								if (memberProvider.getMappingType() != null) {
									memberProviderService.update(memberProvider, user);
								}
							}
						}
						if (provider == null || providerCode.trim().equals("")) {

							String[] param = { "memberId.memberId", "providerId.providerId", "deletedStatus" };
							Object[] value = { memberIdAndPolicyId.getMemberId(), provider.getProviderId(),
									Integer.valueOf(0) };

							MemberProvider memberProvider = memberProviderService.searchUnique(param, value, 0, 1);

							if (memberProvider == null) {
								String msg = "Unable to Find MemberProviderSet " + strMemberId + " / "
										+ providerSetCode;
								System.out.println("[MemberProviderParser] " + msg);
								errorList.add(msg);
								memberProvider = new MemberProvider();
								memberProvider.setMemberId(memberIdAndPolicyId);
								memberProvider.setProviderSetId(providerSet);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									msg = "UNKNOWN ACTION";
									System.out.println("[MemberProviderParser] " + msg);
									errorList.add(msg);
								}

								memberProvider.setPpk1(ppk1);
								memberProvider.setPpk2(ppk2);
								memberProvider.setPpk3(ppk3);
								memberProvider.setInpatient(inpatient);
								memberProvider.setOutpatient(outpatient);
								memberProvider.setDental(dental);
								memberProvider.setMaternity(maternity);
								memberProvider.setOptical(optical);
								memberProvider.setMcuLab(mcuLab);
								memberProvider.setMappingType(mappingType);
								if (memberProvider.getMappingType() != null) {
									memberProviderService.create(memberProvider, user);
								}
							} else {
								memberProvider.setMemberId(memberIdAndPolicyId);
								memberProvider.setProviderSetId(providerSet);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[MemberProviderParser] " + msg);
									errorList.add(msg);
								}
								memberProvider.setPpk1(ppk1);
								memberProvider.setPpk2(ppk2);
								memberProvider.setPpk3(ppk3);
								memberProvider.setInpatient(inpatient);
								memberProvider.setOutpatient(outpatient);
								memberProvider.setDental(dental);
								memberProvider.setMaternity(maternity);
								memberProvider.setOptical(optical);
								memberProvider.setMcuLab(mcuLab);

								memberProvider.setMappingType(mappingType);

								if (memberProvider.getMappingType() != null) {
									memberProviderService.update(memberProvider, user);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			
			System.out.println("[MemberProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[MemberProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[MemberProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[MemberProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
