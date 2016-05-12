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
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MemberGroupProvider;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderSet;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberGroupProviderService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderSetService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class MemberGroupProviderParser {

	private PolicyService policyService;

	private MemberGroupService memberGroupService;

	private ProviderService providerService;

	private ProviderSetService providerSetService;

	private MemberGroupProviderService memberGroupProviderService;

	public MemberGroupProviderService getMemberGroupProviderService() {
		return memberGroupProviderService;
	}

	public void setMemberGroupProviderService(MemberGroupProviderService memberGroupProviderService) {
		this.memberGroupProviderService = memberGroupProviderService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
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

	public static Integer convertYesNo(Cell cell) {

		Integer returnValue = 0;
		if (cell != null) {
			if (ParsingUtil.getCellValueAsString(cell).trim().equalsIgnoreCase("y")) {
				returnValue = 1;
			}
		}

		return returnValue;
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

				Cell cellGroupCode = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellProviderMID = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
				Cell cellProviderSetId = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellPPK1 = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellPPK2 = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellPPK3 = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellInPatient = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellOutpatient = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell cellDental = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
				Cell cellMaternity = row.getCell(9, Row.CREATE_NULL_AS_BLANK);
				Cell cellOptical = row.getCell(10, Row.CREATE_NULL_AS_BLANK);
				Cell cellMcuLab = row.getCell(11, Row.CREATE_NULL_AS_BLANK);
				Cell cellAction = row.getCell(12, Row.CREATE_NULL_AS_BLANK);

				String strProviderMID = null;
				String strProviderSet = null;

				if (cellProviderMID != null) {
					cellProviderMID.setCellType(Cell.CELL_TYPE_STRING);
					strProviderMID = ParsingUtil.getCellValueAsString(cellProviderMID);
				}
				if (cellProviderSetId != null) {
					cellProviderSetId.setCellType(Cell.CELL_TYPE_STRING);
					strProviderSet = ParsingUtil.getCellValueAsString(cellProviderSetId);
				}

				Integer mappingType = null;
				MemberGroup memberGroup = null;

				cellGroupCode.setCellType(Cell.CELL_TYPE_STRING);
				String strGroupCode = cellGroupCode.getStringCellValue();

				memberGroup = memberGroupService.getMemberGroupByCode(strGroupCode);

				if (memberGroup == null) {
					String warning = "Unable to find Member Group " + strGroupCode;
					System.out.println("[MemberGroupProvider-Parser] " + warning);
					
					errorList.add(warning);
				} else {
					
					System.out.println("[MemberGroupProvider-Parser] " + "Searching for Provider " + strProviderMID);
					Provider provider = providerService.getByProviderCode(strProviderMID);

					System.out.println("[MemberGroupProvider-Parser] " + "Searching for Provider Set " + strProviderSet);
					ProviderSet providerSet = providerSetService.searchUnique("providerSetCode", strProviderSet);

					if ((provider == null && providerSet == null)
							|| (strProviderMID.trim().equals("") && strProviderSet.trim().equals(""))) {
						String msg = "Unable to find Provider AND Provider Set " + strProviderMID + " / "
								+ strProviderSet;
						System.out.println("[MemberGroupProvider-Parser] " + msg);
						errorList.add(msg);
					} else {
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
							ppk1 = convertYesNo(cellPPK1);
						}
						if (cellPPK2 != null) {
							ppk2 = convertYesNo(cellPPK2);
						}
						if (cellPPK3 != null) {
							ppk3 = convertYesNo(cellPPK3);
						}
						if (cellInPatient != null) {
							inpatient = convertYesNo(cellInPatient);
						}
						if (cellOutpatient != null) {
							outpatient = convertYesNo(cellOutpatient);
						}
						if (cellDental != null) {
							dental = convertYesNo(cellDental);
						}
						if (cellMaternity != null) {
							maternity = convertYesNo(cellMaternity);
						}
						if (cellOptical != null) {
							optical = convertYesNo(cellOptical);
						}
						if (cellMcuLab != null) {
							mcuLab = convertYesNo(cellMcuLab);
						}

						if (providerSet == null || strProviderSet.trim().equals("")) {

							String[] param = { "memberGroupId.memberGroupId", "providerId.providerId","deletedStatus" };
							Object[] value = { memberGroup.getMemberGroupId(), provider.getProviderId(),Integer.valueOf(0) };

							MemberGroupProvider memberGroupProvider = memberGroupProviderService.searchUnique(param,value, 0, 1);
							
							if (memberGroupProvider == null) {
								String msg = "Unable to find MemberGroupProvider " + memberGroup.getMemberGroupId()
										+ " / " + provider.getProviderCode() + " Inserting";
								System.out.println("[MemberGroupProvider-Parser] " + msg);
								errorList.add(msg);

								memberGroupProvider = new MemberGroupProvider();
								memberGroupProvider.setMemberGroupId(memberGroup);
								memberGroupProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									msg = "UNKNOWN ACTION";
									System.out.println("[MemberGroupProvider-Parser] " + msg);
									errorList.add(msg);
								}

								memberGroupProvider.setPpk1(ppk1);
								memberGroupProvider.setPpk2(ppk2);
								memberGroupProvider.setPpk3(ppk3);
								memberGroupProvider.setInpatient(inpatient);
								memberGroupProvider.setOutpatient(outpatient);
								memberGroupProvider.setDental(dental);
								memberGroupProvider.setMaternity(maternity);
								memberGroupProvider.setOptical(optical);
								memberGroupProvider.setMcuLab(mcuLab);

								memberGroupProvider.setMappingType(mappingType);
								if (memberGroupProvider.getMappingType() != null) {
									memberGroupProviderService.create(memberGroupProvider, user);
								}
							} else {
								memberGroupProvider.setMemberGroupId(memberGroup);
								memberGroupProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[MemberGroupProvider-Parser] " + msg);
									errorList.add(msg);
								}

								memberGroupProvider.setPpk1(ppk1);
								memberGroupProvider.setPpk2(ppk2);
								memberGroupProvider.setPpk3(ppk3);
								memberGroupProvider.setInpatient(inpatient);
								memberGroupProvider.setOutpatient(outpatient);
								memberGroupProvider.setDental(dental);
								memberGroupProvider.setMaternity(maternity);
								memberGroupProvider.setOptical(optical);
								memberGroupProvider.setMcuLab(mcuLab);

								memberGroupProvider.setMappingType(mappingType);
								if (memberGroupProvider.getMappingType() != null) {
									memberGroupProviderService.update(memberGroupProvider, user);
								}
							}
						}
						if (provider == null || strProviderMID.trim().equals("")) {

							String[] param = { "memberGroupId.memberGroupId", "providerId.providerId",
									"deletedStatus" };
							Object[] value = { memberGroup.getMemberGroupId(), provider.getProviderId(),
									Integer.valueOf(0) };

							MemberGroupProvider memberGroupProvider = memberGroupProviderService.searchUnique(param,
									value, 0, 1);

							if (memberGroupProvider == null) {
								String msg = "Unable to find MemberGroupProvider " + memberGroup.getMemberGroupId()
										+ " / " + providerSet.getProviderSetCode();
								System.out.println("[MemberGroupProvider-Parser] " + msg);
								errorList.add(msg);

								memberGroupProvider = new MemberGroupProvider();
								memberGroupProvider.setMemberGroupId(memberGroup);
								memberGroupProvider.setProviderSetId(providerSet);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									msg = "UNKNOWN ACTION";
									System.out.println("[MemberGroupProvider-Parser] " + msg);
									errorList.add(msg);
								}

								memberGroupProvider.setPpk1(ppk1);
								memberGroupProvider.setPpk2(ppk2);
								memberGroupProvider.setPpk3(ppk3);
								memberGroupProvider.setInpatient(inpatient);
								memberGroupProvider.setOutpatient(outpatient);
								memberGroupProvider.setDental(dental);
								memberGroupProvider.setMaternity(maternity);
								memberGroupProvider.setOptical(optical);
								memberGroupProvider.setMcuLab(mcuLab);
								memberGroupProvider.setMappingType(mappingType);
								if (memberGroupProvider.getMappingType() != null) {
									memberGroupProviderService.create(memberGroupProvider, user);
								}
							} else {
								memberGroupProvider.setProviderSetId(providerSet);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[MemberGroupProvider-Parser] " + msg);
									errorList.add(msg);
								}

								memberGroupProvider.setPpk1(ppk1);
								memberGroupProvider.setPpk2(ppk2);
								memberGroupProvider.setPpk3(ppk3);
								memberGroupProvider.setInpatient(inpatient);
								memberGroupProvider.setOutpatient(outpatient);
								memberGroupProvider.setDental(dental);
								memberGroupProvider.setMaternity(maternity);
								memberGroupProvider.setOptical(optical);
								memberGroupProvider.setMcuLab(mcuLab);
								memberGroupProvider.setMappingType(mappingType);
								if (memberGroupProvider.getMappingType() != null) {
									memberGroupProviderService.update(memberGroupProvider, user);
								}
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[MemberGroupProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[MemberGroupProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[MemberGroupProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[MemberGroupProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
