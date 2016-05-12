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
import com.ametis.cms.datamodel.ProviderDoctor;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.PoliklinikService;
import com.ametis.cms.service.ProviderDoctorService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.LoggingUtil;

public class DoctorParser {

	private ProviderService providerService;
	private PoliklinikService poliklinikService;
	private ProviderDoctorService providerDoctorService;

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

	public ProviderDoctorService getProviderDoctorService() {
		return providerDoctorService;
	}

	public void setProviderDoctorService(ProviderDoctorService providerDoctorService) {
		this.providerDoctorService = providerDoctorService;
	}

	public void parseFile(File theFile, ArrayList<String> errorList) {

		ActionUser user = new ActionUser();
		User theUser = new User();
		theUser.setUsername("parser");
		user.setUser(theUser);

		Logger logger = Logger.getLogger("dokter-App");

		FileHandler logFile;
		SimpleFormatter simpleFormatter = new SimpleFormatter();

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
				Cell cellDoctorName = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellSenin = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellSelasa = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellRabu = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellKamis = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellJumat = row.getCell(7, Row.CREATE_NULL_AS_BLANK);
				Cell cellSabtu = row.getCell(8, Row.CREATE_NULL_AS_BLANK);
				Cell cellMinggu = row.getCell(9, Row.CREATE_NULL_AS_BLANK);

				System.out.println("[Dokter-Parser] Searching for Provider = " + cellProviderMID.getStringCellValue());

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
					System.out.println("[Dokter-Parser] " + warning);

					errorList.add(warning);
				} else {

					System.out.println("[Dokter-Parser] " + "Searching For Poliklinik " + cellPoliklinik.getStringCellValue());

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
						System.out.println("[Dokter-Parser] " + warning);

						errorList.add(warning);
					} else {

						String[] eqDocParam = { "providerId.providerId", "providerPoliklinikId.poliklinikId",
								"doctorName", "deletedStatus" };

						Object[] eqDocValue = { provider.getProviderId(), poliklinik.getPoliklinikId(),
								cellDoctorName.getStringCellValue(), 0 };

						ProviderDoctor providerDoctor = null;

						Collection<ProviderDoctor> docList = providerDoctorService.search(null, null, eqDocParam,
								eqDocValue, false, "providerDoctorId", 0, 1);

						if (docList != null && !docList.isEmpty()) {
							for (Iterator iterator = docList.iterator(); iterator.hasNext();) {

								providerDoctor = (ProviderDoctor) iterator.next();

								break;

							}
						}

						if (providerDoctor == null) {
							providerDoctor = new ProviderDoctor();

							providerDoctor.setProviderId(provider);
							providerDoctor.setProviderPoliklinikId(poliklinik);
							providerDoctor.setDoctorName(cellDoctorName.getStringCellValue());
							providerDoctor.setMonday(cellSenin.getStringCellValue());
							providerDoctor.setTuesday(cellSelasa.getStringCellValue());
							providerDoctor.setWednesday(cellRabu.getStringCellValue());
							providerDoctor.setThursday(cellKamis.getStringCellValue());
							providerDoctor.setFriday(cellJumat.getStringCellValue());
							providerDoctor.setSaturday(cellSabtu.getStringCellValue());
							providerDoctor.setSunday(cellMinggu.getStringCellValue());

							providerDoctorService.create(providerDoctor, user);
						} else {
							providerDoctor.setDoctorName(cellDoctorName.getStringCellValue());
							providerDoctor.setMonday(cellSenin.getStringCellValue());
							providerDoctor.setTuesday(cellSelasa.getStringCellValue());
							providerDoctor.setWednesday(cellRabu.getStringCellValue());
							providerDoctor.setThursday(cellKamis.getStringCellValue());
							providerDoctor.setFriday(cellJumat.getStringCellValue());
							providerDoctor.setSaturday(cellSabtu.getStringCellValue());
							providerDoctor.setSunday(cellMinggu.getStringCellValue());

							providerDoctorService.update(providerDoctor, user);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[Dokter-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[Dokter-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[Dokter-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[Dokter-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}
}
