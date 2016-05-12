package com.ametis.cms.util.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.util.LoggingUtil;
import com.ametis.cms.util.ParsingUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * User: ZaQ <zaki.rahman@gmail.com>
 */
public class ClaimPaymentParser {
	
	private ClaimService claimService;
	
  public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

public void parseFile(File theFile, ArrayList<String> errorList, Configuration configuration) {
	

	ActionUser user = new ActionUser();
	User theUser = new User();
	theUser.setUsername("parser");
	user.setUser(theUser);
	
	
    Logger logger = Logger.getLogger("claimpayment-App");

    FileHandler logFile;
    SimpleFormatter simpleFormatter = new SimpleFormatter();
    try {
      logFile = new FileHandler(LoggingUtil.getLogDirectory() + "claimpayment-parser-%g.log", 5242880, 10, true);
      logFile.setFormatter(simpleFormatter);
      logger.addHandler(logFile);
    } catch (IOException e) {
      String msg = "Failure Initializing Logging";
      logger.severe(msg);
      errorList.add(msg);
    }

    Workbook workbook = null;
    try {
      workbook = WorkbookFactory.create(theFile);
      Sheet sheet = workbook.getSheetAt(0);


      Iterator<Row> rowIterator = sheet.iterator();
      rowIterator.next();

      while(rowIterator.hasNext()) {
        Row row = rowIterator.next();

        Cell cellClaimsId = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
        Cell cellPvNo = row.getCell(1, Row.CREATE_NULL_AS_BLANK);
        Cell cellPaymentDate = row.getCell(2, Row.CREATE_NULL_AS_BLANK);
        Cell cellCurrency = row.getCell(3, Row.CREATE_NULL_AS_BLANK);
        Cell cellAmountTotal = row.getCell(4, Row.CREATE_NULL_AS_BLANK);
        Cell cellBankCode = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
        Cell cellBankName = row.getCell(6, Row.CREATE_NULL_AS_BLANK);
        Cell cellAccountNo = row.getCell(7, Row.CREATE_NULL_AS_BLANK);

        Claim claim = claimService.get(ParsingUtil.getCellValueAsString(cellClaimsId));
        if(claim == null) {
          String uploadNote = "Claim Not Found for Number: " + ParsingUtil.getCellValueAsString(cellClaimsId);
          logger.severe(uploadNote);
          errorList.add(uploadNote);
        } else {
          if(configuration.getClientId().equals(claim.getClientId())) {
            claim.setPaymentConfirmDate(new java.sql.Date(ParsingUtil.getCellValueAsDate(cellPaymentDate).getTime()));
            claim.setPaymentRemarks(ParsingUtil.getCellValueAsString(cellPvNo));

            claimService.update(claim, user);
          } else {
            String uploadNote = "Client Id in Configuration Does Not Match Claim Client Id: " + configuration.getClientId() + " vs " + claim.getClientId();
            logger.severe(uploadNote);
            errorList.add(uploadNote);
          }
        }
      }
    }catch (FileNotFoundException e) {
      logger.severe(e.getMessage());
      errorList.add(e.getMessage());
    } catch (IOException e) {
      logger.severe(e.getMessage());
      errorList.add(e.getMessage());
    } catch (InvalidFormatException e) {
      logger.severe(e.getMessage());
      errorList.add(e.getMessage());
    } catch (RuntimeException e) {
      logger.severe(e.getMessage());
      errorList.add(e.getMessage());
    } catch (Exception e) {
    	 logger.severe(e.getMessage());
         errorList.add(e.getMessage());
	}
  }
}
