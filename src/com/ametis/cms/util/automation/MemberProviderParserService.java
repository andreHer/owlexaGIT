package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ClientProviderParserTask;
import com.ametis.cms.util.automation.task.DokterQuartzTask;
import com.ametis.cms.util.automation.task.MemberGroupProviderParserTask;
import com.ametis.cms.util.automation.task.MemberProviderParserTask;

public class MemberProviderParserService extends QuartzJobBean{
	
	private MemberProviderParserTask memberProviderParserTask;
	
	
	public MemberProviderParserTask getMemberProviderParserTask() {
		return memberProviderParserTask;
	}
	public void setMemberProviderParserTask(MemberProviderParserTask memberProviderParserTask) {
		this.memberProviderParserTask = memberProviderParserTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Member Provider Parser is Running");
			memberProviderParserTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
