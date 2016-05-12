/**
 * 
 */
package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
import com.ametis.cms.datamodel.Medicine;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderMedicine;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.MedicineService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProviderMedicineService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

/**
 * @author Mashuri Hasan
 *
 */
public class ProviderMedicineParser {

	private ProviderService providerService;
	private ClientService clientService;
	private MemberGroupService memberGroupService;
	private ProviderMedicineService providerMedicineService;
	private MedicineService medicineService;
	private PolicyService policyService;

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

	public ProviderMedicineService getProviderMedicineService() {
		return providerMedicineService;
	}

	public void setProviderMedicineService(ProviderMedicineService providerMedicineService) {
		this.providerMedicineService = providerMedicineService;
	}

	public MedicineService getMedicineService() {
		return medicineService;
	}

	public void setMedicineService(MedicineService medicineService) {
		this.medicineService = medicineService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
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
				Cell cellMedicine = row.getCell(1);
				Cell cellMedicineCode = row.getCell(2);

				Cell cellSVip = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginSVip = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonSVip = row.getCell(5, Row.CREATE_NULL_AS_BLANK);

				Cell cellVip = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginVip = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonVip = row.getCell(8, Row.CREATE_NULL_AS_BLANK);

				Cell cellKelas1 = row.getCell(9, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginKelas1 = row.getCell(10, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonKelas1 = row.getCell(11, Row.CREATE_NULL_AS_BLANK);

				Cell cellKelas2 = row.getCell(12, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginKelas2 = row.getCell(13, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonKelas2 = row.getCell(14, Row.CREATE_NULL_AS_BLANK);

				Cell cellKelas3 = row.getCell(15, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginKelas3 = row.getCell(16, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonKelas3 = row.getCell(17, Row.CREATE_NULL_AS_BLANK);

				Cell cellRj = row.getCell(18, Row.CREATE_NULL_AS_BLANK);
				Cell cellmarginRj = row.getCell(19, Row.CREATE_NULL_AS_BLANK);
				Cell celldiskonRj = row.getCell(20, Row.CREATE_NULL_AS_BLANK);
				// PENAMBAHAN FIELD DAN VALIDASI BY AULIA R.
				Cell cellClientId = row.getCell(21);
				Cell cellMemberGroupId = row.getCell(22);
				Cell cellPolicyNumber = row.getCell(23);

				String strProviderMID = null, strMedicineCode = null, strClientCode = null, strMemberGroupCode = null,
						strPolicyNumber = null;
				Double dblSVip = 0.0, dblVip = 0.0, dblKelas1 = 0.0, dblKelas2 = 0.0, dblKelas3 = 0.0, dblRj = 0.0,
						dblMarginSVIP = 0.0, dblMarginVIP = 0.0, dblMarginKelas1 = 0.0, dblMarginKelas2 = 0.0,
						dblMarginKelas3 = 0.0, dblMarginRJ = 0.0, dblDiskonSVIP = 0.0, dblDiskonVIP = 0.0,
						dblDiskonKelas1 = 0.0, dblDiskonKelas2 = 0.0, dblDiskonKelas3 = 0.0, dblDiskonRJ = 0.0;
				try {
					strProviderMID = cellProviderMID.getStringCellValue();
					strMedicineCode = cellMedicineCode.getStringCellValue();

					dblSVip = ParsingUtil.getCellValueAsNumeric(cellSVip);
					dblMarginSVIP = ParsingUtil.getCellValueAsNumeric(cellmarginSVip);
					dblDiskonSVIP = ParsingUtil.getCellValueAsNumeric(celldiskonSVip);
					if (dblMarginSVIP == null && dblDiskonSVIP != null) {
						dblSVip -= (dblSVip * dblDiskonSVIP / 100);
					} else if (dblDiskonSVIP == null && dblMarginSVIP != null) {
						dblSVip += (dblSVip * dblMarginSVIP / 100);
					} else if (dblDiskonSVIP != null && dblMarginSVIP != null) {
						dblSVip += ((dblSVip * dblMarginSVIP / 100)
								- ((dblSVip * dblMarginSVIP / 100) * dblDiskonSVIP / 100));
					}

					dblVip = ParsingUtil.getCellValueAsNumeric(cellVip);
					dblMarginVIP = ParsingUtil.getCellValueAsNumeric(cellmarginVip);
					System.out.println("[ProviderMedicine-Parser] dblMarginVIP " + dblMarginVIP);

					dblDiskonVIP = ParsingUtil.getCellValueAsNumeric(celldiskonVip);
					if (dblMarginVIP == null && dblDiskonVIP != null) {
						dblVip -= (dblVip * dblDiskonVIP / 100);
					} else if (dblDiskonVIP == null && dblMarginVIP != null) {
						dblVip += (dblVip * dblMarginVIP / 100);
					} else if (dblDiskonVIP != null && dblMarginVIP != null) {
						dblVip += ((dblVip * dblMarginVIP / 100)
								- ((dblVip * dblMarginVIP / 100) * dblDiskonVIP / 100));
					}

					dblKelas1 = ParsingUtil.getCellValueAsNumeric(cellKelas1);
					dblMarginKelas1 = ParsingUtil.getCellValueAsNumeric(cellmarginKelas1);
					dblDiskonKelas1 = ParsingUtil.getCellValueAsNumeric(celldiskonKelas1);
					if (dblMarginKelas1 == null && dblDiskonKelas1 != null) {
						dblKelas1 -= (dblKelas1 * dblDiskonKelas1 / 100);
					} else if (dblDiskonKelas1 == null && dblMarginKelas1 != null) {
						dblKelas1 += (dblKelas1 * dblMarginKelas1 / 100);
					} else if (dblDiskonKelas1 != null && dblMarginKelas1 != null) {
						dblKelas1 += ((dblKelas1 * dblMarginKelas1 / 100)
								- ((dblKelas1 * dblMarginKelas1 / 100) * dblDiskonKelas1 / 100));
					}

					dblKelas2 = ParsingUtil.getCellValueAsNumeric(cellKelas2);
					dblMarginKelas2 = ParsingUtil.getCellValueAsNumeric(cellmarginKelas2);
					dblDiskonKelas2 = ParsingUtil.getCellValueAsNumeric(celldiskonKelas2);
					if (dblMarginKelas2 == null && dblDiskonKelas2 != null) {
						dblKelas2 -= (dblKelas2 * dblDiskonKelas2 / 100);
					} else if (dblDiskonKelas2 == null && dblMarginKelas2 != null) {
						dblKelas2 += (dblKelas2 * dblMarginKelas2 / 100);
					} else if (dblDiskonKelas2 != null && dblMarginKelas2 != null) {
						dblKelas2 += ((dblKelas2 * dblMarginKelas2 / 100)
								- ((dblKelas2 * dblMarginKelas2 / 100) * dblDiskonKelas2 / 100));
					}

					dblKelas3 = ParsingUtil.getCellValueAsNumeric(cellKelas3);
					dblMarginKelas3 = ParsingUtil.getCellValueAsNumeric(cellmarginKelas3);
					dblDiskonKelas3 = ParsingUtil.getCellValueAsNumeric(celldiskonKelas3);
					if (dblMarginKelas3 == null && dblDiskonKelas3 != null) {
						dblKelas3 -= (dblKelas3 * dblDiskonKelas3 / 100);
					} else if (dblDiskonKelas3 == null && dblMarginKelas3 != null) {
						dblKelas3 += (dblKelas3 * dblMarginKelas3 / 100);
					} else if (dblDiskonKelas3 != null && dblMarginKelas3 != null) {
						dblKelas3 += ((dblKelas3 * dblMarginKelas3 / 100)
								- ((dblKelas3 * dblMarginKelas3 / 100) * dblDiskonKelas3 / 100));
					}

					dblRj = ParsingUtil.getCellValueAsNumeric(cellRj);
					dblMarginRJ = ParsingUtil.getCellValueAsNumeric(cellmarginRj);
					dblDiskonRJ = ParsingUtil.getCellValueAsNumeric(celldiskonRj);
					if (dblMarginRJ == null && dblDiskonRJ != null) {
						dblRj -= (dblRj * dblDiskonRJ / 100);
					} else if (dblDiskonRJ == null && dblMarginRJ != null) {
						dblRj += (dblRj * dblMarginRJ / 100);
					} else if (dblDiskonRJ != null && dblMarginRJ != null) {
						dblRj += ((dblRj * dblMarginRJ / 100) - ((dblRj * dblMarginRJ / 100) * dblDiskonRJ / 100));
					}

					strClientCode = cellClientId.getStringCellValue();
					strMemberGroupCode = cellMemberGroupId.getStringCellValue();
					strPolicyNumber = cellPolicyNumber.getStringCellValue();

					System.out.println("[ProviderMedicine-Parser] Provider ID " + strProviderMID);
					System.out.println("[ProviderMedicine-Parser] Medicine Code " + strMedicineCode);
					System.out.println("[ProviderMedicine-Parser] SVIP " + dblSVip);
					System.out.println("[ProviderMedicine-Parser] VIP " + dblVip);
					System.out.println("[ProviderMedicine-Parser] K1 " + dblKelas1);
					System.out.println("[ProviderMedicine-Parser] K2 " + dblKelas2);
					System.out.println("[ProviderMedicine-Parser] K3 " + dblKelas3);
					System.out.println("[ProviderMedicine-Parser] RJ " + dblRj);
					System.out.println("[ProviderMedicine-Parser] Client " + strClientCode);
					System.out.println("[ProviderMedicine-Parser] MG " + strMemberGroupCode);
					System.out.println("[ProviderMedicine-Parser] Policy " + strPolicyNumber);
				} catch (IllegalStateException e) {
					System.out.println("[ProviderMedicine-Parser] Error Parsing Excel");
					System.out.println("[ProviderMedicine-Parser] " + e.getMessage());
				}

				if (strProviderMID.equalsIgnoreCase("") || strProviderMID == null) {
					String warning = "Provider MID is null";

					System.out.println("[ProviderMedicine-Parser] " + warning);
					errorList.add(warning);
				} else if (strMedicineCode.equalsIgnoreCase("") || strMedicineCode == null) {
					String warning = "Unable to Find Provider for " + strProviderMID;

					System.out.println("[ProviderMedicine-Parser] " + warning + " MEDICINE Code is Null");
					errorList.add(warning);
				} else if (!strClientCode.equalsIgnoreCase("") && !strMemberGroupCode.equalsIgnoreCase("")) {
					String warning = "Not Allowed to fill both of Client Code and Member Group Code";
					System.out.println("[ProviderMedicine-Parser] " + warning);
					errorList.add(warning);
				} else if ((strClientCode == null || strClientCode.equalsIgnoreCase(""))
						&& (strMemberGroupCode == null || strMemberGroupCode.equalsIgnoreCase(""))
						&& (!strPolicyNumber.equalsIgnoreCase(""))) {
					String warning = "Fill the field Client Code and  Member Group Code";
					System.out.println("[ProviderMedicine-Parser] " + warning);
					errorList.add(warning);
				} else if (!strClientCode.equalsIgnoreCase("") && strMemberGroupCode.equalsIgnoreCase("")
						&& !strPolicyNumber.equalsIgnoreCase("")) {
					String warning = "Fill the field Client Code for search Policy";
					System.out.println("[ProviderMedicine-Parser] " + warning);
					errorList.add(warning);
				} else if (strClientCode.equalsIgnoreCase("") && !strMemberGroupCode.equalsIgnoreCase("")
						&& !strPolicyNumber.equalsIgnoreCase("")) {
					String warning = "Fill the field Member Group Code for search Policy";
					System.out.println("[ProviderMedicine-Parser] " + warning);
					errorList.add(warning);
				} else {
					System.out.println("[ProviderMedicine-Parser] Searching For Provider " + strProviderMID);

				
					String[] eqParam = { "edcCode", "deletedStatus" };
					Object[] eqValue = { strProviderMID, 0 };

					Provider provider = null;

					Collection<Provider> providerList = providerService.search(null, null, eqParam, eqValue, false,
							"providerId", 0, 1);
					if (providerList != null) {
						Iterator<Provider> iterator = providerList.iterator();

						if (iterator.hasNext()) {
							provider = iterator.next();
						}
					}

					if (provider == null) {
						String warning = "Unable to Find Provider for " + strProviderMID;
						System.out.println("[ProviderMedicine-Parser] " + warning);
						errorList.add(warning);
					} else {

						Client client = null;
						if (!strClientCode.equalsIgnoreCase("") || strClientCode != null) {
							System.out.println("[ProviderMedicine-Parser] Searching For Client " + strClientCode);
				
							String[] eqClientParam = { "clientCode", "deletedStatus" };
							Object[] eqClientValue = { strClientCode, 0 };

							Collection<Client> clientList = clientService.search(null, null, eqClientParam,
									eqClientValue, false, "clientId", 0, 1);
							if (clientList != null && !clientList.isEmpty()) {
								for (Iterator iterator = clientList.iterator(); iterator.hasNext();) {

									client = (Client) iterator.next();
									break;

								}
							}

							if (client == null) {
								String warning = "Unable to Find Client for " + strClientCode;
								System.out.println("[ProviderMedicine-Parser] " + warning);
								errorList.add(warning);
							}
						}

						MemberGroup memberGroup = null;
						if (!strMemberGroupCode.equalsIgnoreCase("") || strMemberGroupCode != null) {
							System.out.println(
									"[ProviderMedicine-Parser] Searching For Member Group " + strMemberGroupCode);
				
							String[] eqMemberGroupParam = { "memberGroupCode", "deletedStatus" };
							Object[] eqMemberGroupValue = { strMemberGroupCode, 0 };

							Collection<MemberGroup> memberGroupList = memberGroupService.search(null, null,
									eqMemberGroupParam, eqMemberGroupValue, false, "memberGroupId", 0, 1);
							if (memberGroupList != null && !memberGroupList.isEmpty()) {
								for (Iterator iterator = memberGroupList.iterator(); iterator.hasNext();) {

									memberGroup = (MemberGroup) iterator.next();
									break;

								}
							}

							if (memberGroup == null) {
								String warning = "Unable to Find Member Group for " + strMemberGroupCode;
								System.out.println("[ProviderMedicine-Parser] " + warning);
								errorList.add(warning);
							}
						}

						Policy policy = null;
						if (strPolicyNumber != null && strMemberGroupCode != null && strClientCode != null) {
							System.out.println("[ProviderMedicine-Parser] Searching For Policy " + strPolicyNumber);
							String[] eqPolicyParam = { "policyNumber", "memberGroupCode", "clientId.clientId",
									"deletedStatus" };
							Object[] eqPolicyValue = { strPolicyNumber, strMemberGroupCode, client.getClientId(), 0 };

							Collection<Policy> policyList = policyService.search(null, null, eqPolicyParam,
									eqPolicyValue, false, "policyId", 0, 1);
							if (policyList != null && !policyList.isEmpty()) {
								for (Iterator iterator = policyList.iterator(); iterator.hasNext();) {

									policy = (Policy) iterator.next();
									break;

								}
							}

							if (policy == null) {
								String warning = "Unable to Find Policy for " + strPolicyNumber;
								System.out.println("[ProviderMedicine-Parser] " + warning);
								errorList.add(warning);
							}
						}

						System.out.println("[ProviderMedicine-Parser] Searching For Medicine " + strMedicineCode);
						String[] eqMedicineParam = { "medicineCode", "deletedStatus" };
						Object[] eqMedicineValue = { strMedicineCode, 0 };

						Medicine medicine = null;

						Collection<Medicine> medicineList = medicineService.search(null, null, eqMedicineParam,
								eqMedicineValue, false, "medicineId", 0, 1);
						if (medicineList != null && !medicineList.isEmpty()) {
							for (Iterator iterator = medicineList.iterator(); iterator.hasNext();) {

								medicine = (Medicine) iterator.next();
								break;

							}
						}

						if (medicine == null) {
							String warning = "Unable to Find Medicine for " + strMedicineCode;
							System.out.println("[ProviderMedicine-Parser] " + warning);
							errorList.add(warning);
						}

						if (medicine != null && client == null && memberGroup == null && policy == null) {
							String[] eqProviderMedicineParam = { "providerId.providerId", "medicineId.medicineId",
									"deletedStatus" };
							Object[] eqProviderMedicineValue = { provider.getProviderId(), medicine.getMedicineId(),
									0 };

							ProviderMedicine providerMedicine = null;

							Collection<ProviderMedicine> providerMedicineList = providerMedicineService.search(null,
									null, eqProviderMedicineParam, eqProviderMedicineValue, false, "providerMedicineId",
									0, 1);
				
							if (providerMedicineList != null && !providerMedicineList.isEmpty()) {
								for (Iterator iterator = providerMedicineList.iterator(); iterator.hasNext();) {

									providerMedicine = (ProviderMedicine) iterator.next();
									break;

								}
							}

							if (providerMedicine == null) {
								providerMedicine = new ProviderMedicine();

								providerMedicine.setProviderId(provider);
								providerMedicine.setMedicineId(medicine);

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setCreatedBy("ProviderMedicineParser");
								providerMedicine.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

								providerMedicineService.create(providerMedicine, user);
							} else {

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setModifiedBy("ProviderMedicineParser");
								providerMedicine.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

								providerMedicineService.update(providerMedicine, user);
							}
						} else if (medicine != null && client != null && memberGroup == null && policy == null) {
							
							String[] eqProviderMedicineParam = { "providerId.providerId", "medicineId.medicineId",
									"clientId.clientId", "deletedStatus" };
							
							Object[] eqProviderMedicineValue = { provider.getProviderId(), medicine.getMedicineId(),
									client.getClientId(), 0 };

							ProviderMedicine providerMedicine = null;

							Collection<ProviderMedicine> providerMedicineList = providerMedicineService.search(null,
									null, eqProviderMedicineParam, eqProviderMedicineValue, false, "providerMedicineId",
									0, 1);
							if (providerMedicineList != null && !providerMedicineList.isEmpty()) {
								for (Iterator iterator = providerMedicineList.iterator(); iterator.hasNext();) {

									providerMedicine = (ProviderMedicine) iterator.next();
									break;

								}
							}

							if (providerMedicine == null) {
								providerMedicine = new ProviderMedicine();

								providerMedicine.setProviderId(provider);
								providerMedicine.setMedicineId(medicine);

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setClientId(client);

								providerMedicine.setCreatedBy("ProviderMedicineParser");
								providerMedicine.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

							
								providerMedicineService.create(providerMedicine, user);
							} else {

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setModifiedBy("ProviderMedicineParser");
								providerMedicine.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

								providerMedicineService.update(providerMedicine, user);
							}
						} else if (medicine != null && client == null && memberGroup != null && policy == null) {
							
							String[] eqProviderMedicineParam = { "providerId.providerId", "medicineId.medicineId",
									"memberGroupId.memberGroupId", "deletedStatus" };
							Object[] eqProviderMedicineValue = { provider.getProviderId(), medicine.getMedicineId(),
									memberGroup.getMemberGroupId(), 0 };

							ProviderMedicine providerMedicine = null;

							Collection<ProviderMedicine> providerMedicineList = providerMedicineService.search(null,
									null, eqProviderMedicineParam, eqProviderMedicineValue, false, "providerMedicineId",
									0, 1);
							if (providerMedicineList != null && !providerMedicineList.isEmpty()) {
								for (Iterator iterator = providerMedicineList.iterator(); iterator.hasNext();) {

									providerMedicine = (ProviderMedicine) iterator.next();
									break;

								}
							}

							if (providerMedicine == null) {
								providerMedicine = new ProviderMedicine();

								providerMedicine.setProviderId(provider);
								providerMedicine.setMedicineId(medicine);

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setMemberGroupId(memberGroup);

								providerMedicine.setCreatedBy("ProviderMedicineParser");
								providerMedicine.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

								providerMedicineService.create(providerMedicine, user);
							} else {

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setModifiedBy("ProviderMedicineParser");
								providerMedicine.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

							
								providerMedicineService.update(providerMedicine, user);
							}
						} else if (medicine != null && client != null && memberGroup != null && policy != null) {
							
							String[] eqProviderMedicineParam = { "providerId.providerId", "medicineId.medicineId",
									"policyId.policyId", "deletedStatus" };
							Object[] eqProviderMedicineValue = { provider.getProviderId(), medicine.getMedicineId(),
									policy.getPolicyId(), 0 };

							ProviderMedicine providerMedicine = null;

							Collection<ProviderMedicine> providerMedicineList = providerMedicineService.search(null,
									null, eqProviderMedicineParam, eqProviderMedicineValue, false, "providerMedicineId",
									0, 1);
							if (providerMedicineList != null && !providerMedicineList.isEmpty()) {
								for (Iterator iterator = providerMedicineList.iterator(); iterator.hasNext();) {

									providerMedicine = (ProviderMedicine) iterator.next();
									break;

								}
							}

							if (providerMedicine == null) {
								providerMedicine = new ProviderMedicine();

								providerMedicine.setProviderId(provider);
								providerMedicine.setMedicineId(medicine);

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setPolicyId(policy);

								providerMedicine.setCreatedBy("ProviderMedicineParser");
								providerMedicine.setCreatedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

								// providerMedicineService.create(providerMedicine,user);
								providerMedicineService.create(providerMedicine, user);

							} else {

								providerMedicine.setSvip(dblSVip);
								providerMedicine.setMarginSVIP(dblMarginSVIP);
								providerMedicine.setDiskonSVIP(dblDiskonSVIP);
								providerMedicine.setVip(dblVip);
								providerMedicine.setMarginVIP(dblMarginVIP);
								providerMedicine.setDiskonVIP(dblDiskonVIP);
								providerMedicine.setKelas1(dblKelas1);
								providerMedicine.setMarginKelas1(dblMarginKelas1);
								providerMedicine.setDiskonKelas1(dblDiskonKelas1);
								providerMedicine.setKelas2(dblKelas2);
								providerMedicine.setMarginKelas2(dblMarginKelas2);
								providerMedicine.setDiskonKelas2(dblDiskonKelas2);
								providerMedicine.setKelas3(dblKelas3);
								providerMedicine.setMarginKelas3(dblMarginKelas3);
								providerMedicine.setDiskonKelas3(dblDiskonKelas3);
								providerMedicine.setRj(dblRj);
								providerMedicine.setMarginRJ(dblMarginRJ);
								providerMedicine.setDiskonRJ(dblDiskonRJ);

								providerMedicine.setModifiedBy("ProviderMedicineParser");
								providerMedicine.setModifiedTime(new Timestamp(System.currentTimeMillis()));
								providerMedicine.setDeletedStatus(0);

								// providerMedicineService.update(providerMedicine,user);
								providerMedicineService.update(providerMedicine, user);
							}
						} else {
							String warning = "Cannot save Provider Medicine";
							System.out.println("[ProviderMedicine-Parser] " + warning);
							errorList.add(warning);
						}

					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println("[ProviderMedicine-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("[ProviderMedicine-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[ProviderMedicine-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[ProviderMedicine-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[ProviderMedicine-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
