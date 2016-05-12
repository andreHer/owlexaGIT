package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.SuspendReleaseProcessorTask;

public class SuspendReleaseProcessor  extends QuartzJobBean{

	private SuspendReleaseProcessorTask suspendProcessorTask;
	
	
	
	public SuspendReleaseProcessorTask getSuspendProcessorTask() {
		return suspendProcessorTask;
	}



	public void setSuspendProcessorTask(SuspendReleaseProcessorTask suspendProcessorTask) {
		this.suspendProcessorTask = suspendProcessorTask;
	}



	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		suspendProcessorTask.releaseMemberSuspend();
		suspendProcessorTask.releaseGroupSuspend();
	}
	

}
