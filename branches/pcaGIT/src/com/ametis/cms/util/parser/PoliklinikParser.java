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
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderPoliklinik;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProviderPoliklinikService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.LoggingUtil;

public class PoliklinikParser {
	private ProviderService providerService;
	private PoliklinikService poliklinikService;
	private ProviderPoliklinikService providerPoliklinikService;

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public PoliklinikService getPoliklinikService() {
		return poliklinikService;
	}

	public void setPoliklinikService(PoliklinikService poliklinikService) {
		this.poliklinikService = poliklinikService;
	}

	public ProviderPoliklinikService getProviderPoliklinikService() {
		return providerPoliklinikService;
	}

	public void setProviderPoliklinikService(ProviderPoliklinikService providerPoliklinikService) {
		this.providerPoliklinikService = providerPoliklinikService;
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
				Cell cellPoliklinik = row.getCell(1);
				Cell cellLocation = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellTotalRoom = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellTotalDoctor = row.getCell(4, Row.CREATE_NULL_AS_BLANK);

				System.out
						.println("[Poliklinik Parser]  Searching For Provider " + cellProviderMID.getStringCellValue());

				String[] eqParam = { "edcCode", "deletedStatus" };
				Object[] eqValue = { cellProviderMID.getStringCellValue(), 0 };

				Provider provider = null;

				Collection<Provider> provList = providerService.search(null, null, eqParam, eqValue, false,
						"providerId", 0, 1);

				if (provList != null) {
					Iterator<Provider> iterator = provList.iterator();

					if (iterator.hasNext()) {
						provider = iterator.next();
					}
				}

				if (provider == null) {
					String warning = "Unable to Fnd Provider for " + cellProviderMID.getStringCellValue();
					System.out.println("[Poliklinik Parser] " + warning);
					errorList.add(warning);
				} else {
					System.out.println(
							"[Poliklinik Parser]  Searching For Poliklinik " + cellPoliklinik.getStringCellValue());

					String[] eqPoliParam = { "poliklinikCode", "deletedStatus" };
					Object[] eqPoliValue = { cellPoliklinik.getStringCellValue(), 0 };

					Poliklinik poliklinik = null;

					Collection<Poliklinik> poliList = poliklinikService.search(null, null, eqPoliParam, eqPoliValue,
							false, "poliklinikId", 0, 1);

					if (poliList != null && !poliList.isEmpty()) {
						for (Iterator iterator = poliList.iterator(); iterator.hasNext();) {

							poliklinik = (Poliklinik) iterator.next();
							break;

						}
					}

					if (poliklinik == null) {
						String warning = "Unable to Find Poliklinik for " + cellPoliklinik.getStringCellValue();
						System.out.println("[Poliklinik Parser] " + warning);
						errorList.add(warning);
					} else {
						// 

						String[] eqDocParam = { "providerId.providerId", "providerPoliklinikId.poliklinikId",
								"deletedStatus" };

						Object[] eqDocValue = { provider.getProviderId(), poliklinik.getPoliklinikId(), 0 };

						ProviderPoliklinik providerPoliklinik = null;

						Collection<ProviderPoliklinik> poliklinikList = providerPoliklinikService.search(null, null,
								eqDocParam, eqDocValue, false, "providerPoliklinikId", 0, 1);

						if (poliklinikList != null && !poliklinikList.isEmpty()) {
							for (Iterator iterator = poliklinikList.iterator(); iterator.hasNext();) {

								providerPoliklinik = (ProviderPoliklinik) iterator.next();

								break;

							}
						}

						if (providerPoliklinik == null) {
							providerPoliklinik = new ProviderPoliklinik();
							providerPoliklinik.setProviderId(provider);
							providerPoliklinik.setPoliklinikId(poliklinik);
							providerPoliklinik.setCreatedBy("ProviderPoliklinikParser");
							providerPoliklinik.setLocation(cellLocation.getStringCellValue());
							providerPoliklinik.setTotalRoom((int) cellTotalRoom.getNumericCellValue());
							providerPoliklinik.setTotalDoctor((int) cellTotalDoctor.getNumericCellValue());
							providerPoliklinik.setDeletedStatus(0);

							// providerPoliklinikService.insertProviderPoliklinik(providerPoliklinik);
							providerPoliklinikService.create(providerPoliklinik, user);

						} else {
							providerPoliklinik.setModifiedBy("ProviderPoliklinikParser");
							providerPoliklinik.setModifiedTime(new Timestamp(System.currentTimeMillis()));
							providerPoliklinik.setLocation(cellLocation.getStringCellValue());
							providerPoliklinik.setTotalRoom((int) cellTotalRoom.getNumericCellValue());
							providerPoliklinik.setTotalDoctor((int) cellTotalDoctor.getNumericCellValue());
							providerPoliklinik.setDeletedStatus(0);

							// providerPoliklinikService.updateProviderPoliklinik(providerPoliklinik);
							providerPoliklinikService.update(providerPoliklinik, user);

						}
					}
				}
				System.out.println("[Poliklinik Parser]  Finish Row ");
			}
		} catch (NullPointerException e) {
			System.out.println("[Poliklinik Parser]  " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println("[Poliklinik Parser]  " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[Poliklinik Parser]  " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[Poliklinik Parser]  " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[Poliklinik Parser]  " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
