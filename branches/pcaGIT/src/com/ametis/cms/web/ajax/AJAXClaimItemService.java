package com.ametis.cms.web.ajax;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.ClaimService;

public class AJAXClaimItemService {

	private ClaimItemService claimItemService;
	private ClaimService claimService;
	
	public ClaimItemService getClaimItemService() {
		return claimItemService;
	}


	public void setClaimItemService(ClaimItemService claimItemService) {
		this.claimItemService = claimItemService;
	}
	

	
	public ClaimService getClaimService() {
		return claimService;
	}


	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public boolean isDataCorrect (String claimId, String[] claimItemList){
		boolean result = false;
		
		
		try {
			double approved = 0;
			double charge = 0;
			
			if (claimItemList != null){
				for (int i = 0; i < claimItemList.length; i++){
					approved += Double.parseDouble(claimItemList[i]);
				}
				
				Collection ci = claimItemService.getClaimItem(Integer.valueOf(claimId));
				
				if (ci != null){
					Iterator<ClaimItem> iterator = ci.iterator();
					
					while (iterator.hasNext()){
						ClaimItem cItem = iterator.next();
						
						if (cItem != null){
							charge += cItem.getClaimItemValue();
						}
					}
				}
			}
			
			if (approved <= charge){
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public String[] getClaimItemList (String claimId){
		String[] result = null;
		
		try {
			Collection<ClaimItem> items = claimItemService.getVerifiableItem(Integer.valueOf(claimId));
			
			if (items != null){
				result = new String[items.size()];
				
				Iterator<ClaimItem> iterator = items.iterator();
				
				ClaimItem claimItem = null;
				
				for (int i =0 ; i < result.length; i++){
					
					if (iterator.hasNext()){
						claimItem = iterator.next();
						
						if (claimItem != null){
							result[i] = claimItem.getClaimItemId().toString();
						}
					}
				}
				
			}
		}
		catch (Exception e){
			
		}
		return result;
	}
	public String getClaimItemCharge (String claimItemId){
		String result = "0";
		
		try {
			ClaimItem claimItem = claimItemService.get(Integer.valueOf(claimItemId));
			
			if (claimItem != null){
				double value = claimItem.getClaimItemValue();
				result = value+"";
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
			result = "0";
		}
		return result;
	}
	public String removeClaimItem (String claimItemId){
		String result = "0";
		
		try {
			ClaimItem claimItem = claimItemService.get(Integer.valueOf(claimItemId));
			
			if (claimItem != null){
				double value = claimItem.getClaimItemValue();
				result = value+"";
			}
		
		}
		catch (Exception e){
			e.printStackTrace();
			result = "0";
		}
		return result;
	}
	//public String hapusItem (String[] itemId, String[] itemCode, String[] itemName, String[] itemAmount,String[] itemValue, String[] itemDesc, String deletedIdx, String claimId){
		public String hapusItem (String itemId, String itemCode, String itemName, String itemAmount,String itemValue, String itemDesc, String deletedIdx, String claimId){	
		
		String result = "";
		
		try {
			
			System.out.println("HAPUS ITEM");
			Integer totalClaim = claimService.reduceTotalItem(Integer.valueOf(claimId));
			String singleRow = "";
			int deletedId = deletedIdx == null ? 0 : Integer.valueOf(deletedIdx);
			
			int currentIndex = 1;
			
			StringTokenizer itemIdTokenizer = new StringTokenizer(itemId,"|");
			StringTokenizer itemCodeTokenizer = new StringTokenizer(itemCode,"|");
			StringTokenizer itemNameTokenizer = new StringTokenizer(itemName,"|");
			StringTokenizer itemAmountTokenizer = new StringTokenizer(itemAmount,"|");
			StringTokenizer itemValueTokenizer = new StringTokenizer(itemValue,"|");
			StringTokenizer itemDescTokenizer = new StringTokenizer(itemDesc,"|");
			
			int countToken = itemIdTokenizer.countTokens();
			int countItemDesc = itemDescTokenizer.countTokens();
			
			int index = 1;
			
			System.out.println("HAPUS ITEM DONKS");
			System.out.println("ITEM ID : " + itemId);
			System.out.println("ITEM Code : " + itemCode);
			System.out.println("ITEM Name : " + itemName);
			System.out.println("ITEM AMOUNT : " + itemAmount);
			System.out.println("ITEM VALUE : " + itemValue);
			System.out.println("ITEM DESC :  " + itemDesc);
			System.out.println("CLAIM ID : " + claimId);
			
			
			String itemIdStr = "";
			String itemCodeStr = "";
			String itemNameStr = "";
			String itemValueStr = "";
			String itemAmountStr = "";
			String itemDescStr = "";
			
			while (itemIdTokenizer.hasMoreTokens()){
				
				if (itemIdTokenizer.hasMoreTokens()){
					itemIdStr = itemIdTokenizer.nextToken().trim();
				}
				if (itemCodeTokenizer.hasMoreTokens()){
					itemCodeStr = itemCodeTokenizer.nextToken().trim();
				}
				if (itemNameTokenizer.hasMoreTokens()){
					itemNameStr = itemNameTokenizer.nextToken().trim();
				}
				if (itemValueTokenizer.hasMoreTokens()){
					itemValueStr = itemValueTokenizer.nextToken().trim();
				}
				if (itemAmountTokenizer.hasMoreTokens()){
					itemAmountStr = itemAmountTokenizer.nextToken().trim();
				}
				if (itemDescTokenizer.hasMoreTokens()){
					itemDescStr = itemDescTokenizer.nextToken().trim();
				}
				
				if (deletedId != (currentIndex)){
				
					singleRow += "<tr height=\"20\">";
					singleRow += "<input type=\"hidden\" id=\"item"+index+"Id\" name=\"itemId\" value=\""+itemIdStr+"\">";
					singleRow += "<td class=\"oddListRowS1\" align=\"center\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">"+index+"</td>";
								    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
								    singleRow += "<input class=\"input2\" size=\"10\" type=\"text\" id=\"item"+index+"Code\" name=\"itemCode\" value=\""+itemCodeStr+"\"> " ;
								    singleRow += "<input type=\"button\" name=\"PILIH\" class=\"button\" value=\"Lookup\" onClick=\"javascript:lookupItem('item"+index+"')\"></td>" ;
								    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">";
								    singleRow += "<input type=\"text\" class=\"input2\" size=\"15\" id=\"item"+index+"Name\"  value=\""+itemNameStr+"\" name=\"itemName\" readonly=\"readonly\" > </td>";
								    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
								    singleRow += " 	<input class=\"input2\" size=\"10\" type=\"text\" onchange=\"javascript:calculateValue()\" style=\"text-align: right;\" name=\"itemAmount\" value=\""+itemAmountStr+"\"></td>";
								    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
								    singleRow += " 	<input class=\"input2\" type=\"text\"  onchange=\"javascript:calculateValue()\" id=\"idItemValue"+index+"\" style=\"text-align: right;\" name=\"itemValue\" value=\""+itemValueStr+"\"></td>";
								    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
								    singleRow += 		"<TEXTAREA class=\"textarea2\" cols=\"30\" rows=\"2\" name=\"description\">"+itemDescStr+"</TEXTAREA></td>";
								    singleRow += "<td align=\"center\" class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"center\">";
								    singleRow += "		<input class=\"button\" type=\"button\" name=\"itemDeleteButton\" onClick=\"javascript:hapusItem("+index+")\" value=\"delete\"></td>";
								    singleRow += "</tr>";
					
								    singleRow += "<tr><td colspan=\"20\" class=\"listViewHRS1\"></td></tr>";
								    
					result += singleRow;
					singleRow = "";
					
					index += 1;	
				}
				currentIndex += 1;
				
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
			result = "0";
		}
		return result;
	}
	public String addClaimItem (String itemId,String itemCode, String itemName, String itemAmount,String itemValue, String itemDesc, String claimId){
		String result = "";
		
		try {
			
			//Integer totalClaim = claimService.addTotalItem(Integer.valueOf(claimId));
			
			System.out.println("NAMBAH ITEM AWAL DONKS");
			String singleRow = "";
			
			int currentIndex = 1;
			
			StringTokenizer itemIdTokenizer = new StringTokenizer(itemId,"|");
			StringTokenizer itemCodeTokenizer = new StringTokenizer(itemCode,"|");
			StringTokenizer itemNameTokenizer = new StringTokenizer(itemName,"|");
			StringTokenizer itemAmountTokenizer = new StringTokenizer(itemAmount,"|");
			StringTokenizer itemValueTokenizer = new StringTokenizer(itemValue,"|");
			StringTokenizer itemDescTokenizer = new StringTokenizer(itemDesc,"|");

			System.out.println("NAMBAH ITEM DONKS");
			System.out.println("ITEM ID : " + itemId);
			System.out.println("ITEM Code : " + itemCode);
			System.out.println("ITEM Name : " + itemName);
			System.out.println("ITEM AMOUNT : " + itemAmount);
			System.out.println("ITEM VALUE : " + itemValue);
			System.out.println("ITEM DESC :  " + itemDesc);
			System.out.println("CLAIM ID : " + claimId);
			
			
			int index = 1;
			
			String itemIdStr = "";
			String itemCodeStr = "";
			String itemNameStr = "";
			String itemValueStr = "";
			String itemAmountStr = "";
			String itemDescStr = "";
			
			int countToken = itemIdTokenizer.countTokens();
			
			int countItemDesc = itemDescTokenizer.countTokens();
		
			System.out.println("COUNT TOKEN : " + countToken);
			
	
			if (countToken == 0){
				singleRow += "<tr height=\"20\">";
				singleRow += "<input type=\"hidden\" id=\"item"+index+"Id\" name=\"itemId\" value=\""+itemId+"\">";
				singleRow += "<td class=\"oddListRowS1\" align=\"center\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">"+index+"</td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += "<input class=\"input2\" size=\"10\" type=\"text\" id=\"item"+index+"Code\" name=\"itemCode\" value=\""+itemCode+"\"> " ;
							    singleRow += "<input type=\"button\" name=\"PILIH\" class=\"button\" value=\"Lookup\" onClick=\"javascript:lookupItem('item"+index+"')\"></td>" ;
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += "<input type=\"text\" class=\"input2\" size=\"15\" id=\"item"+index+"Name\"  value=\""+itemName+"\" name=\"itemName\" readonly=\"readonly\" > </td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += " 	<input class=\"input2\" size=\"10\" type=\"text\" onchange=\"javascript:calculateValue()\" style=\"text-align: right;\" name=\"itemAmount\" value=\""+itemAmount+"\"></td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += " 	<input class=\"input2\" type=\"text\"  onchange=\"javascript:calculateValue()\" id=\"idItemValue"+index+"\" style=\"text-align: right;\" name=\"itemValue\" value=\""+itemValue+"\"></td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += 		"<TEXTAREA class=\"textarea2\" cols=\"30\" rows=\"2\" name=\"description\">"+itemDesc+"</TEXTAREA></td>";
							    singleRow += "<td align=\"center\" class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"center\">";
							    singleRow += "		<input class=\"button\" type=\"button\" name=\"itemDeleteButton\" onClick=\"javascript:hapusItem("+index+")\" value=\"delete\"></td>";
							    singleRow += "</tr>";
				
							    singleRow += "<tr><td colspan=\"20\" class=\"listViewHRS1\"></td></tr>";
				
			}
			
			while (countToken > 0){
				
			
				System.out.println("COUNT TOKEN : " + countToken);
				
				
				
				if (itemIdTokenizer.hasMoreTokens()){
					itemIdStr = itemIdTokenizer.nextToken().trim();
				}
				if (itemCodeTokenizer.hasMoreTokens()){
					itemCodeStr = itemCodeTokenizer.nextToken().trim();
				}
				if (itemNameTokenizer.hasMoreTokens()){
					itemNameStr = itemNameTokenizer.nextToken().trim();
				}
				if (itemValueTokenizer.hasMoreTokens()){
					itemValueStr = itemValueTokenizer.nextToken().trim();
				}
				if (itemAmountTokenizer.hasMoreTokens()){
					itemAmountStr = itemAmountTokenizer.nextToken().trim();
				}
				if (itemDescTokenizer.hasMoreTokens()){
					itemDescStr = itemDescTokenizer.nextToken().trim();
				}
				
				
				singleRow += "<tr height=\"20\">";
				singleRow += "<input type=\"hidden\" id=\"item"+index+"Id\" name=\"itemId\" value=\""+itemIdStr+"\">";
				singleRow += "<td class=\"oddListRowS1\" align=\"center\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">"+index+"</td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += "<input class=\"input2\" size=\"10\" type=\"text\" id=\"item"+index+"Code\" name=\"itemCode\" value=\""+itemCodeStr+"\"> " ;
							    singleRow += "<input type=\"button\" name=\"PILIH\" class=\"button\" value=\"Lookup\" onClick=\"javascript:lookupItem('item"+index+"')\"></td>" ;
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += "<input type=\"text\" class=\"input2\" size=\"15\" id=\"item"+index+"Name\"  value=\""+itemNameStr+"\" name=\"itemName\" readonly=\"readonly\" > </td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += " 	<input class=\"input2\" size=\"10\" type=\"text\" onchange=\"javascript:calculateValue()\" style=\"text-align: right;\" name=\"itemAmount\" value=\""+itemAmountStr+"\"></td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += " 	<input class=\"input2\" type=\"text\"  onchange=\"javascript:calculateValue()\" id=\"idItemValue"+index+"\" style=\"text-align: right;\" name=\"itemValue\" value=\""+itemValueStr+"\"></td>";
							    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
							    singleRow += 		"<TEXTAREA class=\"textarea2\" cols=\"30\" rows=\"2\" name=\"description\">"+itemDescStr+"</TEXTAREA></td>";
							    singleRow += "<td align=\"center\" class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"center\">";
							    singleRow += "		<input class=\"button\" type=\"button\" name=\"itemDeleteButton\" onClick=\"javascript:hapusItem("+index+")\" value=\"delete\"></td>";
							    singleRow += "</tr>";
				
							    singleRow += "<tr><td colspan=\"20\" class=\"listViewHRS1\"></td></tr>";
							    
				result += singleRow;
				singleRow = "";
				
				index += 1;	
				countToken -= 1;
				
			}
			
			
			singleRow += "<tr height=\"20\">";
			singleRow += "<input type=\"hidden\" id=\"item"+index+"Id\" name=\"itemId\" value=\""+itemIdStr+"\">";
			singleRow += "<td class=\"oddListRowS1\" align=\"center\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">"+index+"</td>";
						    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
						    singleRow += "<input class=\"input2\" size=\"10\" type=\"text\" id=\"item"+index+"Code\" name=\"itemCode\" value=\"\"> " ;
						    singleRow += "<input type=\"button\" name=\"PILIH\" class=\"button\" value=\"Lookup\" onClick=\"javascript:lookupItem('item"+index+"')\"></td>" ;
						    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">";
						    singleRow += "<input type=\"text\" class=\"input2\" size=\"15\" id=\"item"+index+"Name\"  value=\"\" name=\"itemName\" readonly=\"readonly\" > </td>";
						    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
						    singleRow += " 	<input class=\"input2\" size=\"10\" type=\"text\" onchange=\"javascript:calculateValue()\" style=\"text-align: right;\" name=\"itemAmount\" value=\"\"></td>";
						    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
						    singleRow += " 	<input class=\"input2\" type=\"text\"  onchange=\"javascript:calculateValue()\" id=\"idItemValue"+index+"\" style=\"text-align: right;\" name=\"itemValue\" value=\"0\"></td>";
						    singleRow += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
						    singleRow += 		"<TEXTAREA class=\"textarea2\" cols=\"30\" rows=\"2\" name=\"description\"></TEXTAREA></td>";
						    singleRow += "<td align=\"center\" class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"center\">";
						    singleRow += "		<input class=\"button\" type=\"button\" name=\"itemDeleteButton\" onClick=\"javascript:hapusItem("+index+")\" value=\"delete\"></td>";
						    singleRow += "</tr>";
			
						    singleRow += "<tr><td colspan=\"20\" class=\"listViewHRS1\"></td></tr>";
						    
			result += singleRow;
			
			
			index += 1;	
			
			System.out.println (result);
			
		}
		catch (Exception e){
			e.printStackTrace();
			result = "0";
		}
		return result;
	}
	public String addItem (String claimId){
		String result = "";
		
		try {
			Integer totalClaim = claimService.addTotalItem(Integer.valueOf(claimId));
			
			if (totalClaim != null){
				
				result += "<tr height=\"20\">";
				result += "<input type=\"hidden\" id=\"item"+totalClaim+"Id\" name=\"itemId\" value=\"\">";
							    result += "<td class=\"oddListRowS1\" align=\"center\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">"+totalClaim+"</td>";
			    result += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
				result += "<input class=\"input2\" size=\"10\" type=\"text\" id=\"item"+totalClaim+"Code\" name=\"itemCode\" value=\"\"> " ;
				result += "<input type=\"button\" name=\"PILIH\" class=\"button\" value=\"Lookup\" onClick=\"javascript:lookupItem('item"+totalClaim+"')\"></td>" ;
				result += "<td class=\"oddListRowS1\" bgcolor=\"#4f85c6\" nowrap=\"nowrap\" valign=\"top\">";
			    result += "<input type=\"text\" class=\"input2\" size=\"15\" id=\"item"+totalClaim+"Name\" name=\"itemName\" readonly=\"readonly\" > </td>";
				result += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
				result += " 	<input class=\"input2\" size=\"10\" type=\"text\" onchange=\"javascript:calculateValue()\" style=\"text-align: right;\" name=\"itemAmount\" value=\"\"></td>";
				result += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
				result += " 	<input class=\"input2\" type=\"text\"  onchange=\"javascript:calculateValue()\" id=\"idItemValue"+totalClaim+"\" style=\"text-align: right;\" name=\"itemValue\" value=\"\"></td>";
				result += "<td class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"top\">";
				result += 		"<TEXTAREA class=\"textarea2\" cols=\"30\" rows=\"2\" name=\"description\"></TEXTAREA></td>";
				result += "<td align=\"center\" class=\"oddListRowS1\" bgcolor=\"#e7f0fe\" nowrap=\"nowrap\" valign=\"center\">";
				result += "		<input class=\"button\" type=\"button\" name=\"itemValue\" onClick=\"javascript:hapusItem("+totalClaim+")\" value=\"delete\"></td>";
				result += "</tr>";
				
				result += "<tr><td colspan=\"20\" class=\"listViewHRS1\"></td></tr>";
		    
			}
		}
		catch (Exception e){
			e.printStackTrace();
			result = "0";
		}
		return result;
	}
	public String checkBalance (String totalClaim, String totalClaimItem, String pembayaranDimuka, String pembulatan){
		String result = "nook";
		
		System.out.println("check balance : " + totalClaim + " " + totalClaimItem);
		if (totalClaim != null && totalClaimItem != null){
			try {
				
				if (totalClaim.indexOf(",") > 0){
					totalClaim.replace(',', '\0');
					System.out.println("setelah diubah : " + totalClaim);
				}
			
				double downPayment = 0;
				
				if (pembayaranDimuka != null && !pembayaranDimuka.equals("")){
					downPayment = Double.valueOf(pembayaranDimuka);
				}
				double bulat = Double.valueOf(pembulatan);
				
				double totalClaimValue = Double.valueOf(totalClaim);
				double totalClaimItemValue = Double.valueOf(totalClaimItem);
				
				if (totalClaimValue == (totalClaimItemValue+downPayment+bulat)){
					result = "ok";
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public String pembulatan (String totalClaim, String totalClaimItem, String pembayaranDimuka){
		String result = "0";
		
		System.out.println("check balance : " + totalClaim + " " + totalClaimItem);
		if (totalClaim != null && totalClaimItem != null){
			try {
				
				if (totalClaim.indexOf(",") > 0){
					totalClaim.replace(',', '\0');
					System.out.println("setelah diubah : " + totalClaim);
				}
				double downPayment = 0;
				if (pembayaranDimuka != null && !pembayaranDimuka.equals("")){
					 downPayment = Double.valueOf(pembayaranDimuka);
				}
				
				double totalClaimValue = Double.valueOf(totalClaim);
				double totalClaimItemValue = Double.valueOf(totalClaimItem);
				
				double bulat = totalClaimValue - totalClaimItemValue - downPayment;
				
				result = bulat + "";
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
