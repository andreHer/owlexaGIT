package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.PolicyDiagnosisExclusionTask;

public class PolicyDiagnosisExclusionQuartzService extends QuartzJobBean {

	private PolicyDiagnosisExclusionTask diagnosisExclusionTask;

	public PolicyDiagnosisExclusionTask getDiagnosisExclusionTask() {
		return diagnosisExclusionTask;
	}

	public void setDiagnosisExclusionTask(PolicyDiagnosisExclusionTask diagnosisExclusionTask) {
		this.diagnosisExclusionTask = diagnosisExclusionTask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Execute Policy Diagnosis Exclusion");
			diagnosisExclusionTask.executeParse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
