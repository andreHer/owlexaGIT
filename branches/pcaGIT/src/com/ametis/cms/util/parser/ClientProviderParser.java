package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ClientProvider;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderSet;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClientProviderService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.service.ProviderSetService;
import com.ametis.cms.util.ParsingUtil;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class ClientProviderParser {

	private ClientService clientService;
	private ProviderService providerService;
	private ProviderSetService providerSetService;
	private ClientProviderService clientProviderService;

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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

	public ClientProviderService getClientProviderService() {
		return clientProviderService;
	}

	public void setClientProviderService(ClientProviderService clientProviderService) {
		this.clientProviderService = clientProviderService;
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

				Cell cellClientCode = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
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

				cellProviderMID.setCellType(Cell.CELL_TYPE_STRING);
				cellProviderSet.setCellType(Cell.CELL_TYPE_STRING);

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

				cellClientCode.setCellType(Cell.CELL_TYPE_STRING);
				System.out.println(
						"[ClientProviderParser] " + "Searching for Client " + cellClientCode.getStringCellValue());

				Client client = clientService.getClient(cellClientCode.getStringCellValue());
				if (client == null) {
					String msg = "Unable to Find Client " + cellClientCode.getStringCellValue();
					System.out.println("[ClientProviderParser] " + msg);

					errorList.add(msg);
				} else {

					String strProviderCode = cellProviderMID.getStringCellValue();

					System.out.println("[ClientProviderParser] " + "Searching For Provider " + strProviderCode);
					Provider provider = providerService.getByProviderCode(strProviderCode);
					String strProviderSetCode = cellProviderSet.getStringCellValue();
					System.out.println("[ClientProviderParser] " + "Searching for Provider Set " + strProviderSetCode);

					ProviderSet providerSet = providerSetService.searchUnique("providerSetCode", strProviderSetCode);

					if ((provider == null && providerSet == null)
							|| (strProviderCode.trim().equals("") && strProviderSetCode.trim().equals(""))) {
						String msg = "Unable to find Provider AND Provider Set " + strProviderCode + " / "
								+ strProviderSetCode;
						System.out.println("[ClientProviderParser] " + msg);
						errorList.add(msg);
					} else {
						if (providerSet == null || strProviderSetCode.trim().equals("")) {

							String[] param = { "clientId.clientId", "providerId.providerId", "deletedStatus" };
							Object[] value = { client.getClientId(), provider.getProviderId(), Integer.valueOf(0) };

							ClientProvider clientProvider = clientProviderService.searchUnique(param, value, 0, 1);

							if (clientProvider == null) {
								String msg = "Unable to find ClientProvider " + client.getClientCode() + " / "
										+ provider.getProviderCode() + " Inserting";

								System.out.println("[ClientProviderParser] " + msg);

								errorList.add(msg);
								clientProvider = new ClientProvider();
								clientProvider.setClientId(client);
								clientProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									msg = "UNKNOWN ACTION";
									System.out.println("[ClientProviderParser] " + msg);
									errorList.add(msg);
								}

								clientProvider.setPpk1(ppk1);
								clientProvider.setPpk2(ppk2);
								clientProvider.setPpk3(ppk3);
								clientProvider.setInpatient(inpatient);
								clientProvider.setOutpatient(outpatient);
								clientProvider.setDental(dental);
								clientProvider.setMaternity(maternity);
								clientProvider.setOptical(optical);
								clientProvider.setMcuLab(mcuLab);
								clientProvider.setMappingType(mappingType);

								if (clientProvider.getMappingType() != null) {
									clientProviderService.create(clientProvider, user);
								}
							} else {
								clientProvider.setClientId(client);
								clientProvider.setProviderId(provider);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[ClientProviderParser] " + msg);
									errorList.add(msg);
								}

								clientProvider.setPpk1(ppk1);
								clientProvider.setPpk2(ppk2);
								clientProvider.setPpk3(ppk3);
								clientProvider.setInpatient(inpatient);
								clientProvider.setOutpatient(outpatient);
								clientProvider.setDental(dental);
								clientProvider.setMaternity(maternity);
								clientProvider.setOptical(optical);
								clientProvider.setMcuLab(mcuLab);
								clientProvider.setMappingType(mappingType);

								if (clientProvider.getMappingType() != null) {
									clientProviderService.update(clientProvider, user);
								}
							}
						}
						if (provider == null) {

							String[] param = { "clientId.clientId", "providerSetId.providerSetId", "deletedStatus" };
							Object[] value = { client.getClientId(), providerSet.getProviderSetId(),
									Integer.valueOf(0) };

							ClientProvider clientProvider = clientProviderService.searchUnique(param, value, 0, 1);

							if (clientProvider == null) {
								clientProvider.setClientId(client);
								String msg = "Unable to find ClientProvider " + client.getClientCode() + " / "
										+ providerSet.getProviderSetCode();
								System.out.println("[ClientProviderParser] " + msg);
								errorList.add(msg);
							} else {
								clientProvider.setClientId(client);
								if (cellAction.getStringCellValue().trim().equalsIgnoreCase("include")) {
									mappingType = 1;
								} else if (cellAction.getStringCellValue().trim().equalsIgnoreCase("exclude")) {
									mappingType = -1;
								} else {
									String msg = "UNKNOWN ACTION";
									System.out.println("[ClientProviderParser] " + msg);
									errorList.add(msg);
								}

								clientProvider.setPpk1(ppk1);
								clientProvider.setPpk2(ppk2);
								clientProvider.setPpk3(ppk3);
								clientProvider.setInpatient(inpatient);
								clientProvider.setOutpatient(outpatient);
								clientProvider.setDental(dental);
								clientProvider.setMaternity(maternity);
								clientProvider.setOptical(optical);
								clientProvider.setMcuLab(mcuLab);
								clientProvider.setMappingType(mappingType);

								clientProviderService.update(clientProvider, user);
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[ClientProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[ClientProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[ClientProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[ClientProviderParser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}

}
