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
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.ItemCategory;
import com.ametis.cms.datamodel.ItemCodeClientMapping;
import com.ametis.cms.datamodel.Poliklinik;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ItemCategoryService;
import com.ametis.cms.service.ItemCodeClientMappingService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

public class ItemCodeClientMappingParser {
	private ClientService clientService;
	private ItemCategoryService itemCategoryService;
	private ItemCodeClientMappingService itemCodeClientMappingService;
	
	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public ItemCategoryService getItemCategoryService() {
		return itemCategoryService;
	}

	public void setItemCategoryService(ItemCategoryService itemCategoryService) {
		this.itemCategoryService = itemCategoryService;
	}

	public ItemCodeClientMappingService getItemCodeClientMappingService() {
		return itemCodeClientMappingService;
	}

	public void setItemCodeClientMappingService(
			ItemCodeClientMappingService itemCodeClientMappingService) {
		this.itemCodeClientMappingService = itemCodeClientMappingService;
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

	        Cell cellNamaBenefit = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
	        Cell cellKodeBenefit = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
	        Cell cellJenisCoverage = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
	        Cell cellInsuranceBenefitCode = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
	        Cell cellInsuranceCode = row.getCell(4, Row.CREATE_NULL_AS_BLANK);

	        String[] eqParam = {"clientCode", "deletedStatus"};
	        Object[] eqValue = {ParsingUtil.getCellValueAsString(cellInsuranceCode), 0};
	        
	        Client client = null;
	        
	        Collection<Client> clientlist = clientService.search(null, null, eqParam, eqValue, false, "clientId", 0, 1);
	        if (clientlist != null){
	        	Iterator<Client> iterator = clientlist.iterator();
	        	
	        	if (iterator.hasNext()){
	        		client = iterator.next();
	        	}
	        }
	        
	        if(client == null) {
	          String uploadNote = "Client Not Found for Code: " + ParsingUtil.getCellValueAsString(cellInsuranceCode);
	          errorList.add(uploadNote);
	          System.out.println(" [item client mapper log parser ] " + uploadNote);
	        } else {
	          // Client Found check Kode Benefit

	          String[] eqItemCategoryParam = {"itemCategoryCode", "deletedStatus"};
	          Object[] eqItemCategoryValue = {ParsingUtil.getCellValueAsString(cellKodeBenefit), 0};
	          
	          ItemCategory itemCategory = null;
	          
	          Collection<ItemCategory> itemCategoryList = itemCategoryService.search(null, null, eqItemCategoryParam, eqItemCategoryValue, false, "itemCategoryId",0, 1);
	          if (itemCategoryList != null && !itemCategoryList.isEmpty()){
	        	  for (Iterator iterator = itemCategoryList.iterator(); iterator
						.hasNext();) {
	        		  
	        		  itemCategory = (ItemCategory) iterator.next();
					break;
					
				}
	          }

	          if(itemCategory == null) {
	            String uploadNote = "Item Category Not Found for Code: " + ParsingUtil.getCellValueAsString(cellKodeBenefit);
	            errorList.add(uploadNote);
		          System.out.println(" [item client mapper log parser ] " + uploadNote);
	          }
	          else {
	            // Ready to insert
	            ItemCodeClientMapping itemCodeClientMapping = new ItemCodeClientMapping();
	            itemCodeClientMapping.setClientId(client);
	            itemCodeClientMapping.setItemCategoryId(itemCategory);
	            itemCodeClientMapping.setItemName(ParsingUtil.getCellValueAsString(cellNamaBenefit));
	            itemCodeClientMapping.setItemCode(ParsingUtil.getCellValueAsString(cellJenisCoverage));
	            itemCodeClientMapping.setClientItemCode(ParsingUtil.getCellValueAsString(cellInsuranceBenefitCode));


	            itemCodeClientMappingService.create(itemCodeClientMapping, user);
	          }
	        }
	      }
	      
	    } catch (FileNotFoundException e) {
	          System.out.println(" [item client mapper log parser ] " + e.getMessage());
	      errorList.add(e.getMessage());
	    } catch (IOException e) {
	    	System.out.println(" [item client mapper log parser ] " + e.getMessage());
	      errorList.add(e.getMessage());
	    } catch (InvalidFormatException e) {
	    	System.out.println(" [item client mapper log parser ] " + e.getMessage());
	      errorList.add(e.getMessage());
	    } catch (RuntimeException e) {
	    	System.out.println(" [item client mapper log parser ] " + e.getMessage());
	      errorList.add(e.getMessage());
	    }
	    catch (Exception e){
	    	System.out.println(" [item client mapper log parser ] " + e.getMessage());
		      errorList.add(e.getMessage());
	    }
	  }
}
