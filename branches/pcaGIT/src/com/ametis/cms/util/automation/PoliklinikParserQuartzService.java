package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.PoliklinikQuartzTask;

public class PoliklinikParserQuartzService extends QuartzJobBean {
	private PoliklinikQuartzTask poliklinikQuartzTask;

	public PoliklinikQuartzTask getPoliklinikQuartzTask() {
		return poliklinikQuartzTask;
	}

	public void setPoliklinikQuartzTask(PoliklinikQuartzTask poliklinikQuartzTask) {
		this.poliklinikQuartzTask = poliklinikQuartzTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Poliklinik Parser is Running");
			poliklinikQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
