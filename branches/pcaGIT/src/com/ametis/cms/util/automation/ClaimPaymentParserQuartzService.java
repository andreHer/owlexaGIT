package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ClaimPaymentQuartzTask;

public class ClaimPaymentParserQuartzService extends QuartzJobBean {
	
	private ClaimPaymentQuartzTask claimPaymentQuartzTask;
	
	
	public ClaimPaymentQuartzTask getClaimPaymentQuartzTask() {
		return claimPaymentQuartzTask;
	}

	public void setClaimPaymentQuartzTask(
			ClaimPaymentQuartzTask claimPaymentQuartzTask) {
		this.claimPaymentQuartzTask = claimPaymentQuartzTask;
	}
	
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			claimPaymentQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	
	
}
