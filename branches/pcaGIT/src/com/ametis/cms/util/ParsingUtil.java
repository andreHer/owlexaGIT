package com.ametis.cms.util;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;


public class ParsingUtil {
	 public static Integer convertYesNo(Cell cell) {
		    System.out.println("CONVERTING " + cell.getStringCellValue());
		    Integer returnValue = 0;
		    if(cell != null) {
		      if(ParsingUtil.getCellValueAsString(cell).trim().equalsIgnoreCase("y")) {
		        returnValue = 1;
		      }
		    }
		    else {
		      returnValue = 0;
		    }
		    return returnValue;
		  }

		  public static String getCellValueAsString(Cell cell) {
		    String returnValue = null;

		    if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		      if(DateUtil.isCellDateFormatted(cell)) {
		        returnValue = cell.getDateCellValue().toString();
		      }
		      else {
		        returnValue = cell.getNumericCellValue() + "";
		      }
		    } else if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
		      returnValue = cell.getRichStringCellValue().getString();
		    }

		    if(returnValue == null) {
		      returnValue = "";
		    }

		    return returnValue;
		  }

		  public static Double getCellValueAsNumeric(Cell cell) {
		    Double returnValue = null;
		    if(cell != null) {
		      if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		        returnValue = cell.getNumericCellValue();
		      } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
		        try {
		          returnValue = Double.parseDouble(cell.getStringCellValue());
		        } catch (NumberFormatException nfe) {
		          returnValue = null;
		        }
		      }
		    }

		    return returnValue;
		  }

		  public static java.sql.Date getCellValueAsDate(Cell cell) {
		    java.sql.Date returnValue = null;
		    if(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
		      return returnValue;
		    }
		    if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		      if(DateUtil.isCellDateFormatted(cell)) {
		        returnValue = new java.sql.Date(cell.getDateCellValue().getTime()) ;
		      }
		    }
		    return returnValue;
		  }
}
