package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.BenefitCorrectionTask;

public class BenefitCorrectionService extends QuartzJobBean  {

	private BenefitCorrectionTask correctionTask;
	
	
	public BenefitCorrectionTask getCorrectionTask() {
		return correctionTask;
	}


	public void setCorrectionTask(BenefitCorrectionTask correctionTask) {
		this.correctionTask = correctionTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			correctionTask.executeCorrection();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
