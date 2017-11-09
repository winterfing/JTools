//package tools.scheduler;
//
//import java.text.ParseException;
//
//import org.quartz.CronScheduleBuilder;
//import org.quartz.CronTrigger;
//import org.quartz.Job;
//import org.quartz.JobBuilder;
//import org.quartz.JobDataMap;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.SchedulerFactory;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.quartz.impl.StdSchedulerFactory;
//
//
///**
// * @ClassName: JobDispatchUtil
// * @Description: 任务调度工具类
// * @author jhr
// * @date Jul 18, 2015 5:15:12 PM
// */
//public class SchedulerUtil
//{
//    private static SchedulerFactory sf                 = new StdSchedulerFactory();
//
//    private static String           JOB_GROUP_NAME     = "XW_RECON";
//
//    private static String           TRIGGER_GROUP_NAME = "XW_RECON_TRIGGER";
//
//    /**
//     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
//     * 
//     * @param jobName
//     *            任务名
//     * @param job
//     *            任务
//     * @param time
//     *            时间设置，参考quartz说明文档
//     * @throws SchedulerException
//     * @throws ParseException
//     */
//    public static void addJob(String jobName, Job job, String time) throws SchedulerException, ParseException
//    {
//        Scheduler sched = sf.getScheduler();
//        JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());// 任务名，任务组，任务执行类
//        // 触发器
//        CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
//        trigger.setCronExpression(time);// 触发器时间设定
//        sched.scheduleJob(jobDetail, trigger);
//        // 启动
//        if (!sched.isShutdown())
//            sched.start();
//    }
//
//    /**
//     * 添加一个定时任务
//     * 
//     * @param jobName
//     *            任务名
//     * @param jobGroupName
//     *            任务组名
//     * @param triggerName
//     *            触发器名
//     * @param triggerGroupName
//     *            触发器组名
//     * @param job
//     *            任务
//     * @param time
//     *            时间设置，参考quartz说明文档
//     * @throws SchedulerException
//     * @throws ParseException
//     */
//    public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Job job, String time)
//            throws SchedulerException, ParseException
//    {
//        try {
//            //得到默认的调度器
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            //定义当前调度器的具体作业对象
//            JobDetail jobDetail = JobBuilder.
//                                    newJob(job.getClass()).
//                                    withIdentity(jobName, jobGroupName).
//                                    build();
//            //定义当前具体作业对象的参数
//            JobDataMap jobDataMap = jobDetail.getJobDataMap();
//            jobDataMap.put("name", "cronTriggerMap");
//            jobDataMap.put("group", "cronTriggerGrounp");
//            
//            //作业的触发器
//            //和之前的 SimpleTrigger 类似，现在的 CronTrigger 也是一个接口，通过 Tribuilder 的 build()方法来实例化
//            CronTrigger cronTrigger = TriggerBuilder. 
//                                        newTrigger().
//                                        withIdentity(triggerName, triggerGroupName).
//                                        //在任务调度器中，使用任务调度器的 CronScheduleBuilder 来生成一个具体的 CronTrigger 对象
//                                        withSchedule(CronScheduleBuilder.cronSchedule(time)).
//                                        build();
//            //注册作业和触发器
//            scheduler.scheduleJob(jobDetail, cronTrigger);
//            
//            //开始调度任务
//            scheduler.start();
//            System.out.println(scheduler.isShutdown());
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }        
//    }
//
//    /**
//     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
//     * 
//     * @param jobName
//     * @param time
//     * @throws SchedulerException
//     * @throws ParseException
//     */
//    public static void modifyJobTime(String jobName, String time) throws SchedulerException, ParseException
//    {
//        Scheduler sched = sf.getScheduler();
//        sched.pauseJob(JobKey.jobKey(jobName, TRIGGER_GROUP_NAME));
//        Trigger trigger = sched.getTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));
//        if (trigger != null)
//        {
//            trigger.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(time));
//            sched.resumeTrigger();
//        }
//    }
//
//    /**
//     * 修改一个任务的触发时间
//     * 
//     * @param triggerName
//     * @param triggerGroupName
//     * @param time
//     * @throws SchedulerException
//     * @throws ParseException
//     */
//    public static void modifyJobTime(String triggerName, String triggerGroupName, String time) throws SchedulerException, ParseException
//    {
//        Scheduler sched = sf.getScheduler();
//        Trigger trigger = sched.getTrigger(triggerName, triggerGroupName);
//        if (trigger != null)
//        {
//            CronTrigger ct = (CronTrigger) trigger;
//            // 修改时间
//            ct.setCronExpression(time);
//            // 重启触发器
//            sched.resumeTrigger(triggerName, triggerGroupName);
//        }
//    }
//
//    /**
//     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
//     * 
//     * @param jobName
//     * @throws SchedulerException
//     */
//    public static void removeJob(String jobName) throws SchedulerException
//    {
//        Scheduler sched = sf.getScheduler();
//        sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
//        sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
//        sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
//    }
//
//    /**
//     * 移除一个任务
//     * 
//     * @param jobName
//     * @param jobGroupName
//     * @param triggerName
//     * @param triggerGroupName
//     * @throws SchedulerException
//     */
//    public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) throws SchedulerException
//    {
//        Scheduler sched = sf.getScheduler();
//        sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
//        sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
//        sched.deleteJob(jobName, jobGroupName);// 删除任务
//    }
//}
