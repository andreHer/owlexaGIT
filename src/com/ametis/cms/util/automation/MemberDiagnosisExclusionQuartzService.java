package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.MemberDiagnosisExclusionTask;

public class MemberDiagnosisExclusionQuartzService extends QuartzJobBean {
	
	private MemberDiagnosisExclusionTask memberDiagnosisExclusionTask;

	public MemberDiagnosisExclusionTask getMemberDiagnosisExclusionTask() {
		return memberDiagnosisExclusionTask;
	}

	public void setMemberDiagnosisExclusionTask(
			MemberDiagnosisExclusionTask memberDiagnosisExclusionTask) {
		this.memberDiagnosisExclusionTask = memberDiagnosisExclusionTask;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Execute Member Diagnosis Exclusion");
			memberDiagnosisExclusionTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
