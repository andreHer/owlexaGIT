package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.PolicyQuartzTask;

public class PolicyQuartzService extends QuartzJobBean {
	
	private PolicyQuartzTask policyQuartzTask;

	public PolicyQuartzTask getPolicyQuartzTask() {
		return policyQuartzTask;
	}

	public void setPolicyQuartzTask(PolicyQuartzTask policyQuartzTask) {
		this.policyQuartzTask = policyQuartzTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("[Policy Parser] Is Running ");
			policyQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
