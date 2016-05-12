package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ClaimPaymentParserTask;


public class ClaimPaymentParserService extends QuartzJobBean{
	
	private ClaimPaymentParserTask claimPaymentParserTask;
	
	
	public ClaimPaymentParserTask getClaimPaymentParserTask() {
		return claimPaymentParserTask;
	}
	public void setClaimPaymentParserTask(ClaimPaymentParserTask claimPaymentParserTask) {
		this.claimPaymentParserTask = claimPaymentParserTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			claimPaymentParserTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
