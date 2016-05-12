package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ClientProviderParserTask;
import com.ametis.cms.util.automation.task.DokterQuartzTask;
import com.ametis.cms.util.automation.task.MemberGroupProviderParserTask;

public class MemberGroupProviderParserService extends QuartzJobBean{
	
	private MemberGroupProviderParserTask memberGroupProviderParserTask;
	
	
	public MemberGroupProviderParserTask getMemberGroupProviderParserTask() {
		return memberGroupProviderParserTask;
	}
	public void setMemberGroupProviderParserTask(MemberGroupProviderParserTask memberGroupProviderParserTask) {
		this.memberGroupProviderParserTask = memberGroupProviderParserTask;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Member Group Provider is Running");
			memberGroupProviderParserTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
