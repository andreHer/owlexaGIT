package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.DokterQuartzTask;

public class DokterParserQuartzService extends QuartzJobBean{
	
	private DokterQuartzTask dokterQuartzTask;
	
	
	public DokterQuartzTask getDokterQuartzTask() {
		return dokterQuartzTask;
	}
	public void setDokterQuartzTask(DokterQuartzTask dokterQuartzTask) {
		this.dokterQuartzTask = dokterQuartzTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Dokter Quartz Parser");
			dokterQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
