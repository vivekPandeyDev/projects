package spring.batch.practice.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.batch.practice.config.AppConfiguration;
import spring.batch.practice.config.HibernateConfiguration;

import java.io.IOException;

public class AppRunner {
    public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException, IOException {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(HibernateConfiguration.class, AppConfiguration.class);
        applicationContext.registerShutdownHook();
        JobLauncher jobLauncher =  applicationContext.getBean(JobLauncher.class);
        Job job= applicationContext.getBean(Job.class);
        JobParameters jobParameters =  new JobParametersBuilder().addLong("startAt",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(job,jobParameters);
        applicationContext.close();
    }
}
