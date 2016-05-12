/**
 * 
 */
package com.ametis.cms.util.automation;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ametis.cms.util.automation.task.ProviderMedicineQuartzTask;

/**
 * @author Mashuri Hasan
 *
 */
public class ProviderMedicineQuartzService  extends QuartzJobBean{

	private ProviderMedicineQuartzTask providerMedicineQuartzTask;
	
	/* (non-Javadoc)
	 * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		try {
			System.out.println("Provider Medicine Parser is Running");
			providerMedicineQuartzTask.executeParse();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @return the providerMedicineQuartzTask
	 */
	public ProviderMedicineQuartzTask getProviderMedicineQuartzTask() {
		return providerMedicineQuartzTask;
	}

	/**
	 * @param providerMedicineQuartzTask the providerMedicineQuartzTask to set
	 */
	public void setProviderMedicineQuartzTask(ProviderMedicineQuartzTask providerMedicineQuartzTask) {
		this.providerMedicineQuartzTask = providerMedicineQuartzTask;
	}

}
