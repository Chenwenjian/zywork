package top.zywork.common;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestDataJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("test job running...");
        Object data = SchedulerUtils.getJobData(jobExecutionContext);
        if (data != null && data instanceof JobData) {
            JobData jobData = (JobData) data;
            System.out.println(jobData.getId() + ": " + jobData.getName());
        }
    }
}