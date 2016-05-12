package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

public class PolicyParser {
	private ClientService clientService;
	private MemberGroupService memberGroupService;
	private PolicyService policyService;

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

	public void parseFile(File theFile, ArrayList<String> errorList) throws Exception {

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

				Cell cellClientCode = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellPolicyNumber = row.getCell(1, Row.CREATE_NULL_AS_BLANK); // policy_number
				Cell cellMasterGroupCode = row.getCell(2, Row.CREATE_NULL_AS_BLANK); // insert?
				Cell cellGroupName = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellPolicyDate = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellEffectiveDate = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellExpireDate = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellPolicyType = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell cellCardType = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
				Cell cellProviderAllocation = row.getCell(9, Row.CREATE_NULL_AS_BLANK);
				Cell cellIsAso = row.getCell(10, Row.CREATE_NULL_AS_BLANK);
				Cell cellMinFloatingFund = row.getCell(11, Row.CREATE_NULL_AS_BLANK);
				Cell cellFundWarningPct = row.getCell(12, Row.CREATE_NULL_AS_BLANK);
				Cell cellFundBlockPct = row.getCell(13, Row.CREATE_NULL_AS_BLANK);
				Cell cellReimburseLength = row.getCell(14, Row.CREATE_NULL_AS_BLANK);
				Cell cellReimburseMaxAcceptance = row.getCell(15, Row.CREATE_NULL_AS_BLANK);
				Cell cellExcessExpireLength = row.getCell(16, Row.CREATE_NULL_AS_BLANK);

				Client client = clientService.searchUnique("clientCode",
						ParsingUtil.getCellValueAsString(cellClientCode));
				if (client == null) {
					String msg = "Unable to find Client by Code: " + ParsingUtil.getCellValueAsString(cellClientCode);
					System.out.println(msg);
					errorList.add(msg);
				} else {

					MemberGroup memberGroup = memberGroupService.searchUnique("memberGroupCode",ParsingUtil.getCellValueAsString(cellMasterGroupCode));
					if (memberGroup == null) {
						memberGroup = new MemberGroup();
						memberGroup.setClientId(client);
						memberGroup.setMemberGroupCode(ParsingUtil.getCellValueAsString(cellMasterGroupCode));
						memberGroup.setGroupName(ParsingUtil.getCellValueAsString(cellGroupName));
						memberGroup.setTipe("G");
						// insert new member group
						memberGroupService.create(memberGroup, user);
					}
					String[] param = { "policyNumber", "groupCode", "deletedStatus" };
					Object[] value = { ParsingUtil.getCellValueAsString(cellPolicyNumber),ParsingUtil.getCellValueAsString(cellMasterGroupCode), Integer.valueOf(0) };

					Policy policy = policyService.searchUnique(param, value, 0, 1);
					
					if (policy != null) {
						String msg = "Existing Policy Number / Group Code Exists: "
								+ ParsingUtil.getCellValueAsString(cellPolicyNumber) + " / "
								+ ParsingUtil.getCellValueAsString(cellMasterGroupCode);
						
						System.out.println(msg);
						errorList.add(msg);
					} else {
						policy = new Policy();
						policy.setPolicyNumber(ParsingUtil.getCellValueAsString(cellPolicyNumber));
						policy.setMemberGroupId(memberGroup);

						java.sql.Date policyDate = new java.sql.Date(
								ParsingUtil.getCellValueAsDate(cellPolicyDate).getTime());
						policy.setPolicyDate(policyDate);
						java.sql.Date effectiveDate = new java.sql.Date(
								ParsingUtil.getCellValueAsDate(cellEffectiveDate).getTime());

						policy.setEffectiveDate(effectiveDate);

						java.sql.Date expireDate = new java.sql.Date(
								ParsingUtil.getCellValueAsDate(cellExpireDate).getTime());

						policy.setExpireDate(expireDate);
						policy.setPolicyType(parsePolicyType(cellPolicyType));

						// policy.setCardTypeId(parseCardType(cellCardType));
						policy.setProviderAllocationType(parseProviderAllocation(cellProviderAllocation));
						policy.setIsUsingFloatingFund(parseIsAso(cellIsAso));
						policy.setMinimumPolicyFund(cellMinFloatingFund.getNumericCellValue());
						Double fundWarningPct = cellFundWarningPct.getNumericCellValue();
						if (fundWarningPct != null && (fundWarningPct < 0 || fundWarningPct > 100)) {
							fundWarningPct = null;
						}
						policy.setFundWarningPercentage(fundWarningPct);
						Double fundBlockPct = cellFundBlockPct.getNumericCellValue();
						if (fundBlockPct != null && (fundBlockPct < 0 || fundBlockPct > 100)) {
							fundBlockPct = null;
						}
						policy.setFundWarningPercentage(fundBlockPct);
						policy.setReimburseExpireDay(ParsingUtil.getCellValueAsNumeric(cellReimburseLength).intValue());
						policy.setReimburseMaxReceiveDay(
								ParsingUtil.getCellValueAsNumeric(cellReimburseMaxAcceptance).intValue());
						policy.setExcessChargeExpireDay(
								ParsingUtil.getCellValueAsNumeric(cellExcessExpireLength).intValue());
						policy.setClientId(client);

						Collection<MultipartFile> tcFiles = new Vector<MultipartFile>();

						policyService.create(policy, tcFiles, user);
					}

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println(e.getMessage());
			errorList.add(e.getMessage());
		}
	}

	public static Integer parseCardType(Cell cell) {
		Integer returnValue = 1;
		String cellValue = ParsingUtil.getCellValueAsString(cell);
		if (cellValue.equalsIgnoreCase("show")) {
			returnValue = 1;
		} else if (cellValue.equalsIgnoreCase("swipe")) {
			returnValue = 2;
		}
		return returnValue;
	}

	public static Integer parseIsAso(Cell cell) {
		Integer returnValue = 0;
		String cellValue = ParsingUtil.getCellValueAsString(cell);
		if (cellValue.equalsIgnoreCase("y")) {
			returnValue = 1;
		} else if (cellValue.equalsIgnoreCase("n")) {
			returnValue = 0;
		}
		return returnValue;
	}

	public static Integer parsePolicyType(Cell cell) {
		Integer returnValue = 1;
		String cellValue = ParsingUtil.getCellValueAsString(cell);
		if (cellValue.equalsIgnoreCase("i")) {
			returnValue = 1;
		} else if (cellValue.equalsIgnoreCase("mc")) {
			returnValue = 2;
		}
		return returnValue;
	}

	public static Integer parseProviderAllocation(Cell cell) {
		Integer returnValue = 1;
		String cellValue = ParsingUtil.getCellValueAsString(cell);
		if (cellValue.equalsIgnoreCase("all")) {
			returnValue = 1;
		} else if (cellValue.equalsIgnoreCase("group")) {
			returnValue = 2;
		} else if (cellValue.equalsIgnoreCase("member")) {
			returnValue = 2;
		}
		return returnValue;
	}

}
