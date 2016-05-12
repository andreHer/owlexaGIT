package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ValidatorTesterTask;

public class ValidatorTesterService  extends QuartzJobBean{

	private ValidatorTesterTask validatorTesterTask;

	public ValidatorTesterTask getValidatorTesterTask() {
		return validatorTesterTask;
	}

	public void setValidatorTesterTask(ValidatorTesterTask validatorTesterTask) {
		this.validatorTesterTask = validatorTesterTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			validatorTesterTask.validateAll();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
