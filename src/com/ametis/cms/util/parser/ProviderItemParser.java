package com.ametis.cms.util.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Item;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.ProviderItem;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ItemService;
import com.ametis.cms.service.ProviderItemService;
import com.ametis.cms.service.ProviderService;

public class ProviderItemParser {
	private ProviderService providerService;
	private ItemService itemService;
	private ProviderItemService providerItemService;

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ProviderItemService getProviderItemService() {
		return providerItemService;
	}

	public void setProviderItemService(ProviderItemService providerItemService) {
		this.providerItemService = providerItemService;
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
				Cell cellItem = row.getCell(1);
				Cell cellSVip = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
				Cell cellVip = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
				Cell cellKelas1 = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
				Cell cellKelas2 = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
				Cell cellKelas3 = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
				Cell cellRj = row.getCell(7, Row.CREATE_NULL_AS_BLANK);

				// ProviderService providerService = new ProviderService();
				System.out.println(
						"[ProviderItem-Parser] Searching For Provider " + cellProviderMID.getStringCellValue());
				Provider provider = providerService.getByProviderCode(cellProviderMID.getStringCellValue());

				if (provider == null) {
					String warning = "Unable to Find Provider for " + cellProviderMID.getStringCellValue();
					System.out.println("[ProviderItem-Parser] " + warning);
					errorList.add(warning);
				} else {
					// ItemService itemService = new ItemService();
					System.out.println("[ProviderItem-Parser]  Searching For Item " + cellItem.getStringCellValue());

					Item item = itemService.searchUnique("itemCode", cellItem.getStringCellValue());

					if (item == null) {
						String warning = "Unable to Find Item for " + cellItem.getStringCellValue();
						System.out.println("[ProviderItem-Parser] " + warning);
						errorList.add(warning);
					} else {
						// ProviderItemService providerItemService = new
						// ProviderItemService();
						String[] param = { "providerId.providerId", "itemId.itemId", "deletedStatus" };
						Object[] value = { provider.getProviderId(), item.getItemId(), Integer.valueOf(0) };
						ProviderItem providerItem = providerItemService.searchUnique(param, value, 0, 1);

						if (providerItem == null) {
							providerItem = new ProviderItem();

							providerItem.setProviderId(provider);
							providerItem.setItemId(item);

							providerItem.setSvip(cellSVip.getNumericCellValue());
							providerItem.setVip(cellVip.getNumericCellValue());
							providerItem.setKelas1(cellKelas1.getNumericCellValue());
							providerItem.setKelas2(cellKelas2.getNumericCellValue());
							providerItem.setKelas3(cellKelas3.getNumericCellValue());
							providerItem.setRawatJalan(cellRj.getNumericCellValue());

							providerItem.setCreatedBy("ProviderItemParser");
							providerItem.setDeletedStatus(0);

							providerItemService.create(providerItem, user);
						} else {
							providerItem.setSvip(cellSVip.getNumericCellValue());
							providerItem.setVip(cellVip.getNumericCellValue());
							providerItem.setKelas1(cellKelas1.getNumericCellValue());
							providerItem.setKelas2(cellKelas2.getNumericCellValue());
							providerItem.setKelas3(cellKelas3.getNumericCellValue());
							providerItem.setRawatJalan(cellRj.getNumericCellValue());

							providerItem.setModifiedBy("ProviderItemParser");
							providerItem.setModifiedTime(new Timestamp(System.currentTimeMillis()));
							providerItem.setDeletedStatus(0);

							providerItemService.update(providerItem, user);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("[ProviderItem-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (IOException e) {
			System.out.println("[ProviderItem-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (InvalidFormatException e) {
			System.out.println("[ProviderItem-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		} catch (Exception e) {
			System.out.println("[ProviderItem-Parser] " + e.getMessage());
			errorList.add(e.getMessage());
		}
	}

}
