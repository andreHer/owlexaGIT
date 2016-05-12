package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.PaymentUploadGeneratorTask;

public class PaymentUploadGenerator  extends QuartzJobBean{


	private PaymentUploadGeneratorTask generatorTask;
	
	
	public PaymentUploadGeneratorTask getGeneratorTask() {
		return generatorTask;
	}


	public void setGeneratorTask(PaymentUploadGeneratorTask generatorTask) {
		this.generatorTask = generatorTask;
	}


	protected void executeInternal(JobExecutionContext ctx)	throws JobExecutionException {
		
		generatorTask.processPaymentUpload();
		
		
	}
}
