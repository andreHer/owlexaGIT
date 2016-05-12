/**
 * 
 */
package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ProviderProcedureQuartzTask;

/**
 * @author Mashuri Hasan
 *
 */
public class ProviderProcedureQuartzService  extends QuartzJobBean{

	private ProviderProcedureQuartzTask providerProcedureQuartzTask;
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Provider Procedure Parser is Running");
			providerProcedureQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public ProviderProcedureQuartzTask getProviderProcedureQuartzTask() {
		return providerProcedureQuartzTask;
	}

	public void setProviderProcedureQuartzTask(
			ProviderProcedureQuartzTask providerProcedureQuartzTask) {
		this.providerProcedureQuartzTask = providerProcedureQuartzTask;
	}

}
