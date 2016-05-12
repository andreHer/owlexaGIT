package com.ametis.cms.web.ajax;

import java.util.Collection;
import java.util.Iterator;

import com.ametis.cms.datamodel.ItemMeasurementUnit;
import com.ametis.cms.datamodel.MeasurementUnit;
import com.ametis.cms.service.ItemMeasurementUnitService;
import com.ametis.cms.service.MeasurementUnitService;

public class AJAXMeasurementService {


	private ItemMeasurementUnitService itemMeasurementUnitService;

	public ItemMeasurementUnitService getItemMeasurementUnitService() {
		return itemMeasurementUnitService;
	}
	public void setItemMeasurementUnitService(
			ItemMeasurementUnitService itemMeasurementUnitService) {
		this.itemMeasurementUnitService = itemMeasurementUnitService;
	}
	
	
	public String changeMeasurement(Integer itemCategory){
		String result = "";
		
		try {
			Collection<ItemMeasurementUnit> measurement = itemMeasurementUnitService.search(null,null,"itemCategoryId.itemCategoryId",itemCategory,0,10);
			
			if (measurement != null){
				Iterator<ItemMeasurementUnit> iterator = measurement.iterator();
				
				if (iterator != null){
					ItemMeasurementUnit itemMeasurementUnit = null;
					
					while (iterator.hasNext()){
						itemMeasurementUnit = iterator.next();
						
						if (itemMeasurementUnit != null){
							result += "<option value='"+itemMeasurementUnit.getMeasurementUnitId().getMeasurementUnitId()+"'>"+itemMeasurementUnit.getMeasurementUnitId().getMeasurementUnitName()+"</option>";
						}
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
