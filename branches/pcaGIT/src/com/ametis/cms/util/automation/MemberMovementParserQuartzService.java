package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.MemberMovementQuartzTask;

public class MemberMovementParserQuartzService extends QuartzJobBean {
	private MemberMovementQuartzTask memberMovementQuartzTask;

	public MemberMovementQuartzTask getMemberMovementQuartzTask() {
		return memberMovementQuartzTask;
	}

	public void setMemberMovementQuartzTask(
			MemberMovementQuartzTask memberMovementQuartzTask) {
		this.memberMovementQuartzTask = memberMovementQuartzTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			memberMovementQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
