package br.com.sincronizacao_receita.a_old.config_old;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {
    @Bean
    public JobDetail quartzJobDetail() {
        //return JobBuilder.newJob(BatchScheduleJob.class).storeDurably().build();
        return null;
    }

    @Bean
    public Trigger jobTrigger() {
        /*JobDetail job = newJob(MyJob.class)
                .withIdentity("myJob")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity(triggerKey("myTrigger", "myTriggerGroup"))
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(1)
                        .repeatForever())
                .startAt(futureDate(10, MINUTES))
                .build();

        scheduler.scheduleJob(job, trigger);*/


        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInMinutes(1) //InHours(24) //rvalInSeconds(5)
                //.withRepeatCount(4)
                .repeatForever();
        return TriggerBuilder
                .newTrigger()
                .forJob(quartzJobDetail())
                .withSchedule(scheduleBuilder)
                //.startAt()
                .build();
    }
}