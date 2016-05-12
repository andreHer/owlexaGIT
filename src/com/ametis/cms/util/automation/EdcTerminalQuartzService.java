package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.EdcTerminalQuartzTask;

public class EdcTerminalQuartzService extends QuartzJobBean {
	private EdcTerminalQuartzTask edcTerminalQuartzTask;

	public EdcTerminalQuartzTask getEdcTerminalQuartzTask() {
		return edcTerminalQuartzTask;
	}

	public void setEdcTerminalQuartzTask(EdcTerminalQuartzTask edcTerminalQuartzTask) {
		this.edcTerminalQuartzTask = edcTerminalQuartzTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			edcTerminalQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}	
}
