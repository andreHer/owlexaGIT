package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.MemberActivatorTask;

public class MemberActivator extends QuartzJobBean {

	private MemberActivatorTask memberActivatorTask;

	public MemberActivatorTask getMemberActivatorTask() {
		return memberActivatorTask;
	}

	public void setMemberActivatorTask(MemberActivatorTask memberActivatorTask) {
		this.memberActivatorTask = memberActivatorTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			
			memberActivatorTask.activate();
			memberActivatorTask.activateRenewal();
			memberActivatorTask.activateUpgrade();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
