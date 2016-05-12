package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.PolicyProvider;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderSet;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.PolicyProviderService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderSetService;
import com.ametis.cms.util.ParsingUtil;

public class PolicyProviderParser {

	private PolicyService policyService;
	private ProviderService providerService;
	private ProviderSetService providerSetService;
	private PolicyProviderService policyProviderService;

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
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

	public PolicyProviderService getPolicyProviderService() {
		return policyProviderService;
	}

	public void setPolicyProviderService(PolicyProviderService policyProviderService) {
		this.policyProviderService = policyProviderService;
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

				Cell cellPolicyNumber = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellProviderMID = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
				Cell cellProviderSet = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
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

				Integer mappingType = null;

				System.out.println(
						"[PolicyProvider-Parser] Searching for Policy " + cellPolicyNumber.getStringCellValue());

				String[] eqParam = { "policyNumber", "deletedStatus" };
				Object[] eqValue = { cellPolicyNumber.getStringCellValue(), 0 };

				Policy policy = null;

				Collection<Policy> policyList = policyService.search(null, null, eqParam, eqValue, false, "policyId", 0,
						1);
				if (policyList != null) {
					Iterator<Policy> iterator = policyList.iterator();

					if (iterator.hasNext()) {
						policy = iterator.next();
					}
				}

				if (policy == null) {
					String msg = "Unable to Find Policy " + cellPolicyNumber.getStringCellValue();
					System.out.println("[PolicyProvider-Parser] " + msg);
					errorList.add(msg);
				} else {
					if (cellProviderMID != null) {
						cellProviderMID.setCellType(Cell.CELL_TYPE_STRING);
					}
					if (cellProviderSet != null) {
						cellProviderSet.setCellType(Cell.CELL_TYPE_STRING);
					}
					String strProviderCode = cellProviderMID.getStringCellValue();
					System.out.println("[PolicyProvider-Parser] " + "Searching For Provider " + strProviderCode);

					String[] eqProvParam = { "providerCode", "deletedStatus" };
					Object[] eqProvValue = { strProviderCode, 0 };

					Provider provider = null;

					Collection<Provider> provList = providerService.search(null, null, eqProvParam, eqProvValue, false,
							"providerid", 0, 1);
					if (provList != null && !provList.isEmpty()) {
						for (Iterator iterator = provList.iterator(); iterator.hasNext();) {
							provider = (Provider) iterator.next();
							break;
						}
					}

					String strProviderSetCode = cellProviderSet.getStringCellValue();
					System.out.println("[PolicyProvider-Parser] " + "Searching for Provider Set " + strProviderSetCode);

					String[] eqProvSetParam = { "providerSetCode", "deletedStatus" };
					Object[] eqProvSetValue = { strProviderSetCode, 0 };

					ProviderSet providerSet = null;

					Collection<ProviderSet> provSetList = providerSetService.search(null, null, eqProvSetParam,
							eqProvSetValue, false, "providerSetId", 0, 1);

					if (provSetList != null && !provSetList.isEmpty()) {
						for (Iterator iterator = provSetList.iterator(); iterator.hasNext();) {

							providerSet = (ProviderSet) iterator.next();

							break;

						}
					}

					if ((provider == null && providerSet == null)
							|| (strProviderCode.trim().equals("") && strProviderSetCode.trim().equals(""))) {
						String msg = "Unable to find Provider AND Provider Set " + strProviderCode + " / "
								+ strProviderSetCode;
						System.out.println("[PolicyProvider-Parser] " + msg);
						errorList.add(msg);
					} else {
						if (providerSet == null || strProviderSetCode.trim().equals("")) {

							String[] eqPolicyProvParam = { "policyId.policyId", "policyProviderId.providerId",
									"deletedStatus" };
							Object[] eqPolicyProvValue = { policy.getPolicyId(), provider.getProviderId(), 0 };

							PolicyProvider policyProvider = null;

							Collection<PolicyProvider> policyProvList = policyProviderService.search(null, null,
									eqPolicyProvParam, eqPolicyProvValue, false, "policyProviderId", 0, 1);

							if (policyProvList != null && !policyProvList.isEmpty()) {
								for (Iterator iterator = policyProvList.iterator(); iterator.hasNext();) {

									policyProvider = (PolicyProvider) iterator.next();

									break;

								}
							}

							if (policyProvider == null) {
								String msg = "Unable to find PolicyProvider " + policy.getPolicyNumber() + " / "
										+ provider.getProviderCode() + " Inserting";
								System.out.println("[PolicyProvider-Parser] " + msg);
								policyProvider = new PolicyProvider();
								policyProvider.setPolicyId(policy);
								policyProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									msg = "UNKNOWN ACTION";
									System.out.println("[PolicyProvider-Parser] " + msg);
									errorList.add(msg);
								}
								policyProvider.setPpk1(ppk1);
								policyProvider.setPpk2(ppk2);
								policyProvider.setPpk3(ppk3);
								policyProvider.setInpatient(inpatient);
								policyProvider.setOutpatient(outpatient);
								policyProvider.setDental(dental);
								policyProvider.setMaternity(maternity);
								policyProvider.setOptical(optical);
								policyProvider.setMcuLab(mcuLab);
								policyProvider.setType(String.valueOf(mappingType));
								policyProvider.setType(String.valueOf(mappingType));

								if (policyProvider.getType() != null) {
									policyProviderService.create(policyProvider, user);
								}
							} else {
								policyProvider.setPolicyId(policy);
								policyProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[PolicyProvider-Parser] " + msg);
									errorList.add(msg);
								}

								policyProvider.setPpk1(ppk1);
								policyProvider.setPpk2(ppk2);
								policyProvider.setPpk3(ppk3);
								policyProvider.setInpatient(inpatient);
								policyProvider.setOutpatient(outpatient);
								policyProvider.setDental(dental);
								policyProvider.setMaternity(maternity);
								policyProvider.setOptical(optical);
								policyProvider.setMcuLab(mcuLab);
								policyProvider.setType(String.valueOf(mappingType));

								if (policyProvider.getType() != null) {
									policyProviderService.update(policyProvider, user);
								}
							}
						}
						if (provider == null) {

							String[] eqPolicyProvParam = { "policyId.policyId", "providerSet.providerSetId",
									"deletedStatus" };
							Object[] eqPolicyProvValue = { policy.getPolicyId(), providerSet.getProviderSetId(), 0 };

							PolicyProvider policyProvider = null;

							Collection<PolicyProvider> policyProvList = policyProviderService.search(null, null,
									eqPolicyProvParam, eqPolicyProvValue, false, "policyProviderId", 0, 1);

							if (policyProvList != null && !policyProvList.isEmpty()) {
								for (Iterator iterator = policyProvList.iterator(); iterator.hasNext();) {

									policyProvider = (PolicyProvider) iterator.next();

									break;

								}
							}

							if (policyProvider == null) {
								String msg = "Unable to find PolicyProvider " + policy.getPolicyNumber() + " / "
										+ providerSet.getProviderSetCode() + " INSERTING";
								System.out.println("[PolicyProvider-Parser] " + msg);
								policyProvider = new PolicyProvider();
								policyProvider.setPolicyId(policy);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									msg = "UNKNOWN ACTION";
									System.out.println("[PolicyProvider-Parser] " + msg);
									errorList.add(msg);
								}
								policyProvider.setPpk1(ppk1);
								policyProvider.setPpk2(ppk2);
								policyProvider.setPpk3(ppk3);
								policyProvider.setInpatient(inpatient);
								policyProvider.setOutpatient(outpatient);
								policyProvider.setDental(dental);
								policyProvider.setMaternity(maternity);
								policyProvider.setOptical(optical);
								policyProvider.setMcuLab(mcuLab);
								policyProvider.setType(String.valueOf(mappingType));
								policyProvider.setType(String.valueOf(mappingType));

								if (policyProvider.getType() != null) {
									policyProviderService.create(policyProvider, user);
								}
							} else {
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[PolicyProvider-Parser] " + msg);
									errorList.add(msg);
								}

								policyProvider.setPpk1(ppk1);
								policyProvider.setPpk2(ppk2);
								policyProvider.setPpk3(ppk3);
								policyProvider.setInpatient(inpatient);
								policyProvider.setOutpatient(outpatient);
								policyProvider.setDental(dental);
								policyProvider.setMaternity(maternity);
								policyProvider.setOptical(optical);
								policyProvider.setMcuLab(mcuLab);
								policyProvider.setType(String.valueOf(mappingType));
								policyProvider.setModifiedBy("PolicyProviderParser");
								policyProvider.setModifiedTime(new Timestamp(System.currentTimeMillis()));

								policyProviderService.update(policyProvider, user);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[PolicyProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[PolicyProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[PolicyProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[PolicyProvider-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
