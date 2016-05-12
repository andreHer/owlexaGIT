package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ClientProviderParserTask;
import com.ametis.cms.util.automation.task.DokterQuartzTask;

public class ClientProviderParserService extends QuartzJobBean{
	
	private ClientProviderParserTask clientProviderParserTask;
	
	
	public ClientProviderParserTask getClientProviderParserTask() {
		return clientProviderParserTask;
	}
	public void setClientProviderParserTask(ClientProviderParserTask clientProviderParserTask) {
		this.clientProviderParserTask = clientProviderParserTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("CLIENT PROVIDER PARSER IS RUNNING!");
			clientProviderParserTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
