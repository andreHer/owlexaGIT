package com.ametis.cms.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.dto.ClaimItemDto;
import com.sun.xml.internal.fastinfoset.sax.Properties;

public class ClaimItemUtil {

	private static String[] opECCodeList = {"1000","1010","1011","1002","1001","1015","1025","1027","1026","1020","1028","1044","1045"};
	
	public static boolean isEDCItemContain(Collection<ClaimItem> itemList, String[] checkList){
		boolean result = false;
		
		if (itemList != null && checkList != null && itemList.size() == checkList.length){
			
			boolean[] tmp = new boolean[itemList.size()];
			
			for (int i = 0; i < checkList.length; i++){
				tmp[i] = false;
			}
			
			int idx = 0;
			for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
				
				ClaimItem claimItem = (ClaimItem) iterator.next();
				if (claimItem != null ){
					for (int i = 0; i < checkList.length; i++){
						String itemCode = checkList[i];
						
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase(itemCode)){
							tmp[idx] = true;
							break;
						}
					}
					idx++;
				}		
			}
			
			boolean tmpResult = true;
			for (int i = 0; i < tmp.length; i++){
				tmpResult = tmpResult && tmp[i];
			}
			
			result = tmpResult;
		}
		
		return result;
	}
	
	public static Collection<ClaimItem> getAllowedEDCItemList (Collection<ClaimItem> itemList, java.util.Properties props, String delimiter){
		Collection<ClaimItem> result = new Vector<ClaimItem>();
		
		if (itemList != null && props != null){
			String inputEDCCode = ClaimItemUtil.generateEDCCodeString(itemList, delimiter);
			
			String outputEDCCode = props.getProperty(inputEDCCode);
			System.out.println("INPUT CODE : " + inputEDCCode + " OUTPUT CODE : " + outputEDCCode);
			
			if (outputEDCCode != null && !outputEDCCode.equalsIgnoreCase("")){
			
				String[] filteredCodeList = ClaimItemUtil.reconstructEDCCodeString(outputEDCCode, delimiter);
				
				result = ClaimItemUtil.getFilteredEDCItemList(itemList, filteredCodeList);
			}
			else if (outputEDCCode == null || outputEDCCode.equalsIgnoreCase("")){
				result = itemList;
			}
		}
		
		return result;
	}
	/**
	 * 1000|1010|1011|1002|1001|1015|1025|1027|1026|1020|1028|1044|1045
	 * 
	 * 
	 * 13 item total
	 */
	private static String generateEDCCodeString(Collection<ClaimItem> itemList,String delimiter){
		String result = "";
		
		try {
						
			String[] tmpArray = new String[13];
			for (int i = 0; i < tmpArray.length; i++){
				tmpArray[i] = "";
			}

			for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
				ClaimItem claimItem = (ClaimItem) iterator.next();

				if (claimItem != null){
					for (int i = 0; i < opECCodeList.length;i++){
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase(opECCodeList[i])){
							tmpArray[i] = claimItem.getItemId().getItemEDCCode();
							break;
						}
					}
				}				
			}
			for (int i = 0; i < tmpArray.length; i++) {
				if (tmpArray[i] != null && !tmpArray[i].equalsIgnoreCase("")){
					result += tmpArray[i]+delimiter;
				}				
			}
			char[] resultChar = result.toCharArray();
			if (resultChar != null && resultChar[resultChar.length-1] == '|'){
				resultChar[resultChar.length-1] = ' ';
			}
			result = new String(resultChar);
			result = result.trim();
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	public static String[] reconstructEDCCodeString(String edcString, String delimiter){
		String[] result = null;
		
		if (edcString != null){
			StringTokenizer tokenizer = new StringTokenizer(edcString,delimiter);
			
			
			int totalToken = tokenizer.countTokens();
			System.out.println("TOKENIZE : " + edcString + " WITH DELIMITER = " + delimiter + " TOTAL TOKEN : " + totalToken);
			result = new String[totalToken];
			
			int indx = 0;
			while(tokenizer.hasMoreTokens()){
				
				result[indx] = tokenizer.nextToken();				
				indx++;
			}			
		}		
		return result;
	}
	public static Collection<ClaimItem> getFilteredEDCItemList (Collection<ClaimItem> itemList, String[] filteredCodeList){
		Collection<ClaimItem> result = new Vector<ClaimItem>();
		
		if (itemList != null && filteredCodeList != null){
			for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
				ClaimItem claimItem = (ClaimItem) iterator.next();
				
				if (claimItem != null){
					for (int i = 0; i < filteredCodeList.length; i++) {
						System.out.println("CHECKING FILTERED CODE : " + filteredCodeList[i] + " COUNTER CHECK : " + claimItem.getItemId().getItemEDCCode());
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase(filteredCodeList[i])){
							result.add(claimItem);
							break;
						}
					}
				}
			}
		}
		
		return result;
	}
	public static Collection<ClaimItem> getAllowedEDCItemList (Collection<ClaimItem> itemList){
		Collection<ClaimItem> result = new Vector<ClaimItem>();
		
		if (itemList != null){
			String[] scenario1 = {"1001","1010","1002"};
			
			String[] scenario2 = {"1010","1011","1002","1001"};
			String[] scenario3 = {"1002","1001"};
			
			String[] scenario4 = {"1011","1010","1001"};
			String[] scenario5 = {"1011","1001","1002"};
			String[] scenario6 = {"1015","1020","1044"};
			
			
			int totalItem = itemList.size();
			
			if (totalItem == 4){
				// scenario 2 handler
				if (isEDCItemContain(itemList, scenario2)){
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {					
						ClaimItem claimItem = (ClaimItem) iterator.next();
						
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1001")){
							result.add(claimItem);
						}
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1010")){
							result.add(claimItem);
						}
					}
				}
				else {
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
						ClaimItem claimItem = (ClaimItem) iterator.next();
						result.add(claimItem);					
					}
				}
			}
			else if (totalItem == 2){
				// scenario 3 handler
				if (isEDCItemContain(itemList, scenario3)){
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
						ClaimItem claimItem = (ClaimItem) iterator.next();
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1001")){
							result.add(claimItem);
						}
					}
				}
				else {
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
						ClaimItem claimItem = (ClaimItem) iterator.next();
						result.add(claimItem);					
					}
				}
			}
			else if (totalItem == 3){
				// check scenario 1 || scenario 4
				if (isEDCItemContain(itemList, scenario1) || isEDCItemContain(itemList, scenario4)){
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {					
						ClaimItem claimItem = (ClaimItem) iterator.next();
						
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1001")){
							result.add(claimItem);
						}
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1010")){
							result.add(claimItem);
						}
					}	
				}
				
				// check scenario 5
				else if (isEDCItemContain(itemList, scenario5)){
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {					
						ClaimItem claimItem = (ClaimItem) iterator.next();
						
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1001")){
							result.add(claimItem);
						}
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1011")){
							result.add(claimItem);
						}
					}	
				}
				// check scenario 6
				else if (isEDCItemContain(itemList, scenario6)){
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {					
						ClaimItem claimItem = (ClaimItem) iterator.next();
						
						if (claimItem.getItemId().getItemEDCCode().equalsIgnoreCase("1020")){
							result.add(claimItem);
						}						
					}	
				}
				else {
					for (Iterator iterator = itemList.iterator(); iterator.hasNext();) {
						ClaimItem claimItem = (ClaimItem) iterator.next();
						result.add(claimItem);					
					}
				}
				
			}
		}
		return result;
	}
}
