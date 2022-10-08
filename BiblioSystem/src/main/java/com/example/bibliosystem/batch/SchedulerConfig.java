package com.example.bibliosystem.batch;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Classe de gestion de lancement de job
 * On défini la périodicité des jobs que l'on veut lancer
 * Dans notre cas : job d'envoi de relancce par mail
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    /**
     * Lancement périodique du mail de relance des prêts expirés
     * @throws Exception Exception liée au job de relance
     */
//    @Scheduled(cron = "0 0/1 * * * *")
    public void scheduleByFixedRate() throws Exception {
        System.out.println("Batch job starting");
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
        jobLauncher.run(job, jobParameters);
        System.out.println("Batch job executed successfully ");
    }

}
