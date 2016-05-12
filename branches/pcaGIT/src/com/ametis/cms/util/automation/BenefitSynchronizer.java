package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.BenefitSynchronizerTask;

public class BenefitSynchronizer extends QuartzJobBean {

	private BenefitSynchronizerTask synchronizerTask;
	public BenefitSynchronizerTask getSynchronizerTask() {
		return synchronizerTask;
	}
	public void setSynchronizerTask(BenefitSynchronizerTask synchronizerTask) {
		this.synchronizerTask = synchronizerTask;
	}



	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			synchronizerTask.activateSynchronize();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
