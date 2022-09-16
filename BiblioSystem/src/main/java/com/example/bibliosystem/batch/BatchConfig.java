package com.example.bibliosystem.batch;

import com.example.bibliosystem.Pret;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

  //  @Autowired
  //  DataSource dataSource;

    private final String JOB_NAME = "jobRelance";
    private final String STEP_NAME = "relanceStep";

    @Bean
    public Job jobRelance(){
        return jobBuilderFactory.get(JOB_NAME).start(relanceStep()).build();
    }

    @Bean
    public Step relanceStep(){
        return stepBuilderFactory.get(STEP_NAME).<String, String>chunk(1)
                .reader( new PretReader())
                .processor( new PretProcessor())
                .writer( new PretWriter()).build();
    }



 /*
    private ItemProcessor<Pret, Pret> pretProcessor() {
        //
        // return new PretProcessor();
    }

    @Bean
    public ItemReader<Pret> pretReader() {
        String sql= "SELECT * FROM pret";

        return new JdbcCursorItemReaderBuilder<Pret>()
                .name("pretReader")
                .dataSource(dataSource)
                .sql(sql)
                .rowMapper(new PretMapper())
                .build();
    }

*/






}
