package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ProviderItemQuartzTask;

public class ProviderItemQuartzService extends QuartzJobBean {
	
	private ProviderItemQuartzTask providerItemQuartzTask;

	public ProviderItemQuartzTask getProviderItemQuartzTask() {
		return providerItemQuartzTask;
	}

	public void setProviderItemQuartzTask(
			ProviderItemQuartzTask providerItemQuartzTask) {
		this.providerItemQuartzTask = providerItemQuartzTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Provider Item Parser Is Running");
			providerItemQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
