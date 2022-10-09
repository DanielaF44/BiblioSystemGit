package com.example.bibliosystem.configuration;

import com.example.bibliosystem.batch.PretProcessor;
import com.example.bibliosystem.batch.PretReader;
import com.example.bibliosystem.batch.PretWriter;
import com.example.bibliosystem.entity.custom.UserPretRelance;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public PretReader pretReader;

    @Autowired
    public PretProcessor pretProcessor;

    private final String JOB_NAME = "jobRelance";
    private final String STEP_NAME = "relanceStep";

    /**
     * Création d'un job de relance
     * @return un objet job construit par un factory et lancé dans la foulé
     */
    @Bean
    public Job jobRelance(){
        return jobBuilderFactory.get(JOB_NAME).start(relanceStep()).build();
    }

    /**
     * Etape de job
     * @return une étape de job de relance
     */
    @Bean
    public Step relanceStep(){
        return stepBuilderFactory.get(STEP_NAME).<String, String>chunk(1)
                .reader( pretReader )
                .processor( pretProcessor )
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
