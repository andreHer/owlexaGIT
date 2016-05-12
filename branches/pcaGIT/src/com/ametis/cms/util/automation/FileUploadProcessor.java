package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.FileUploadProcessorTask;

public class FileUploadProcessor extends QuartzJobBean {
	
	private FileUploadProcessorTask processorTask;
	
	

	public FileUploadProcessorTask getProcessorTask() {
		return processorTask;
	}



	public void setProcessorTask(FileUploadProcessorTask processorTask) {
		this.processorTask = processorTask;
	}



	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		
		try {
			processorTask.doProcess();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
