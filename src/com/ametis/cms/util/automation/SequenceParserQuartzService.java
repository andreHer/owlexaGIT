/**
 * 
 */
package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.SequenceParserQuartzTask;

/**
 * @author Mashuri Hasan
 *
 */
public class SequenceParserQuartzService  extends QuartzJobBean{

	private SequenceParserQuartzTask sequenceParserQuartzTask;
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			sequenceParserQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public SequenceParserQuartzTask getSequenceParserQuartzTask() {
		return sequenceParserQuartzTask;
	}

	public void setSequenceParserQuartzTask(
			SequenceParserQuartzTask sequenceParserQuartzTask) {
		this.sequenceParserQuartzTask = sequenceParserQuartzTask;
	}
	
	

}
