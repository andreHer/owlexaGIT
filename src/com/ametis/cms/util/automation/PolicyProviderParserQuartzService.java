package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.PolicyProviderQuartzTask;

public class PolicyProviderParserQuartzService extends QuartzJobBean {
	
	private PolicyProviderQuartzTask policyProviderQuartzTask;

	public PolicyProviderQuartzTask getPolicyProviderQuartzTask() {
		return policyProviderQuartzTask;
	}

	public void setPolicyProviderQuartzTask(
			PolicyProviderQuartzTask policyProviderQuartzTask) {
		this.policyProviderQuartzTask = policyProviderQuartzTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Policy Provider Parser is Running");
			policyProviderQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
