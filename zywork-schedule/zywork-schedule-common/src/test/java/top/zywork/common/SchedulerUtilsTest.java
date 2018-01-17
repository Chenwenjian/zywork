package top.zywork.common;

public class SchedulerUtilsTest {

    public static void main(String[] args) throws InterruptedException {
        // stop();
        // restart();
        // resumeJob();
        // resumeTrigger();
        // modify();
        startWithData();
    }

    /**
     * 启动与停止作业的测试
     * @throws InterruptedException
     */
    public static void stop() throws InterruptedException {
        SchedulerUtils.startJob("top.zywork.common.TestJob", "TestJob", "TestTrigger", "0/1 * * * * ? *");
        Thread.sleep(5 * 1000);
        SchedulerUtils.stopJob("TestJob", "TestTrigger");
    }

    /**
     * 重启作业的测试
     * @throws InterruptedException
     */
    public static void restart() throws InterruptedException {
        SchedulerUtils.startJob("top.zywork.common.TestJob", "TestJob", "TestTrigger", "0/10 * * * * ? *");
        SchedulerUtils.restartJob("TestJob");
    }

    /**
     * 暂停与恢复作业的测试
     * @throws InterruptedException
     */
    public static void resumeJob() throws InterruptedException {
        SchedulerUtils.startJob("top.zywork.common.TestJob", "TestJob", "TestTrigger", "0/1 * * * * ? *");
        Thread.sleep(5 * 1000);
        SchedulerUtils.pauseJob("TestJob");
        Thread.sleep(10 * 1000);
        SchedulerUtils.resumeJob("TestJob");
    }

    /**
     * 暂停与恢复作业的测试
     * @throws InterruptedException
     */
    public static void resumeTrigger() throws InterruptedException {
        SchedulerUtils.startJob("top.zywork.common.TestJob", "TestJob", "TestTrigger", "0/1 * * * * ? *");
        Thread.sleep(5 * 1000);
        SchedulerUtils.pauseTrigger("TestTrigger");
        Thread.sleep(10 * 1000);
        SchedulerUtils.resumeTrigger("TestTrigger");
    }

    /**
     * 修改作业测试
     * @throws InterruptedException
     */
    public static void modify() throws InterruptedException {
        SchedulerUtils.startJob("top.zywork.common.TestJob", "TestJob", "TestTrigger", "0/1 * * * * ? *");
        Thread.sleep(5 * 1000);
        SchedulerUtils.modifyJob("TestJob", "TestTrigger", "0/10 * * * * ? *");
    }

    /**
     * 启动作业并动态传递参数
     */
    public static void startWithData() {
        JobData jobData = new JobData();
        jobData.setId(1000);
        jobData.setName("Test");
        SchedulerUtils.startJob("top.zywork.common.TestDataJob", "TestDataJob", jobData, "TestDataTrigger", "0/5 * * * * ? *");
    }

}
