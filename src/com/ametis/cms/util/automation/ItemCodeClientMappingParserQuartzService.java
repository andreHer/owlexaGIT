package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ItemCodeClientMappingQuartzTask;

public class ItemCodeClientMappingParserQuartzService extends QuartzJobBean {
	private ItemCodeClientMappingQuartzTask itemCodeClientMappingQuartzTask;

	public ItemCodeClientMappingQuartzTask getItemCodeClientMappingQuartzTask() {
		return itemCodeClientMappingQuartzTask;
	}

	public void setItemCodeClientMappingQuartzTask(
			ItemCodeClientMappingQuartzTask itemCodeClientMappingQuartzTask) {
		this.itemCodeClientMappingQuartzTask = itemCodeClientMappingQuartzTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			itemCodeClientMappingQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}	
}
