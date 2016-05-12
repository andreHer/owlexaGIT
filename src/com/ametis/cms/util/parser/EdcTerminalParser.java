package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;

import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.LoggingUtil;

public class EdcTerminalParser {
	private EdcTerminalService edcTerminalService;
	private ProviderService providerService;

	public EdcTerminalService getEdcTerminalService() {
		return edcTerminalService;
	}

	public void setEdcTerminalService(EdcTerminalService edcTerminalService) {
		this.edcTerminalService = edcTerminalService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
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

				Cell cellProviderMID = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
				Cell cellTID = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
				Cell cellLocation = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellAuthKey = row.getCell(3, Row.CREATE_NULL_AS_BLANK);

				String strTID = cellTID.getStringCellValue();
				System.out.println("[EDCTemplate-Parser] Searching For EDC Terminal " + strTID);
				String[] eqParam = { "deviceNumber", "deletedStatus" };
				Object[] eqValue = { strTID, 0 };

				EdcTerminal edcTerminal = null;

				Collection<EdcTerminal> edcTerminalList = edcTerminalService.search(null, null, eqParam, eqValue, false,
						"id", 0, 1);
				if (edcTerminalList != null) {
					Iterator<EdcTerminal> iterator = edcTerminalList.iterator();

					if (iterator.hasNext()) {
						edcTerminal = iterator.next();
					}
				}

				if (edcTerminal == null) {
					String msg = "Unable to Find EDC Terminal " + strTID;

					System.out.println("[EDCTemplate-Parser] " + msg);
					errorList.add(msg);
				} else {
					String strProviderMID = cellProviderMID.getStringCellValue();

					System.out.println("[EDCTemplate-Parser] Searching For Provider " + strProviderMID);

					String[] eqProviderParam = { "edcCode", "deletedStatus" };
					Object[] eqProviderValue = { strProviderMID, 0 };

					Provider provider = null;

					Collection<Provider> providerList = providerService.search(null, null, eqProviderParam,
							eqProviderValue, false, "providerId", 0, 1);
					if (providerList != null && !providerList.isEmpty()) {
						for (Iterator iterator = providerList.iterator(); iterator.hasNext();) {

							provider = (Provider) iterator.next();
							break;

						}
					}

					if (provider == null) {
						String msg = "Unable to Find Provider " + strProviderMID;
						System.out.println("[EDCTemplate-Parser] " + msg);
						errorList.add(msg);
					} else {
						if (edcTerminal.getProviderId().equals(provider.getProviderId())) {
							edcTerminal.setAuthorizationKey(cellAuthKey.getStringCellValue());
							edcTerminal.setLocation(cellLocation.getStringCellValue());

							edcTerminalService.update(edcTerminal, user);
						} else {
							String msg = "Provider Mismatch for EDC Terminal " + strTID + " / " + strProviderMID;
							System.out.println("[EDCTemplate-Parser] " + msg);
							errorList.add(msg);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[EDCTemplate-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[EDCTemplate-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[EDCTemplate-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[EDCTemplate-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
