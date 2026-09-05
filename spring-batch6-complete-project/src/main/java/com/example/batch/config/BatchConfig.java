package com.example.batch.config;

import com.example.batch.exception.RetryableException;
import com.example.batch.exception.ValidationException;
import com.example.batch.model.Customer;
import com.example.batch.processor.CustomerProcessor;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    @Bean
    FlatFileItemReader<Customer> reader() {
        return new FlatFileItemReaderBuilder<Customer>().name("reader").resource(
                        new ClassPathResource("input/customers.csv")).
                delimited().names("id", "name", "salary").fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Customer.class);
                }}).build();
    }

    @Bean
    ItemWriter<Customer> writer() {
        return items -> {
            for (Customer c : items) {
                System.out.println(c.getId() + " " + c.getName());
            }
        };
    }

    @Bean
    Step customerStep(JobRepository jr,
                      FlatFileItemReader<Customer> r, CustomerProcessor p, ItemWriter<Customer> w) {
        return new StepBuilder("customerStep", jr).
                <Customer, Customer>chunk(3).
               // transactionManager(tm).
                reader(r).processor(p).writer(w).faultTolerant().
                skip(ValidationException.class).skipLimit(10).
                retry(RetryableException.class).retryLimit(3).build();

    }
    @Bean
    Job customerJob(JobRepository jr, Step s) {

        return new JobBuilder("customerJob", jr).start(s).build();
    }
}