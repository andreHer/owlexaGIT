package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.MemberMutationActivatorTask;

public class MemberMutationActivator extends QuartzJobBean {

	private MemberMutationActivatorTask mutationTask;
	
	
	public MemberMutationActivatorTask getMutationTask() {
		return mutationTask;
	}


	public void setMutationTask(MemberMutationActivatorTask mutationTask) {
		this.mutationTask = mutationTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			mutationTask.activateMutation();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
