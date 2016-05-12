package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.BillingGeneratorTask;

public class BillingReportGenerator extends QuartzJobBean{

	private BillingGeneratorTask billingGenerator;
	
	

	public BillingGeneratorTask getBillingGenerator() {
		return billingGenerator;
	}



	public void setBillingGenerator(BillingGeneratorTask billingGenerator) {
		this.billingGenerator = billingGenerator;
	}



	protected void executeInternal(JobExecutionContext ctx)	throws JobExecutionException {
		
		billingGenerator.generateBilling();
		
		
	}

}
