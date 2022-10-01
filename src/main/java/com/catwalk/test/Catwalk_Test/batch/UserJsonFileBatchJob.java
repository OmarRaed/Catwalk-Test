package com.catwalk.test.Catwalk_Test.batch;

import com.catwalk.test.Catwalk_Test.model.User;
import com.catwalk.test.Catwalk_Test.repository.UserRepo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.ui.ConcurrentModel;
import org.springframework.core.io.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@Slf4j
@Component
public class UserJsonFileBatchJob implements FileBatchJob<Map<String, Object>, User> {

    private final UserRepo repository;
    private final ObjectMapper mapper;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobLauncher jobLauncher;
    private final JobBuilderFactory jobBuilderFactory;


    public UserJsonFileBatchJob(UserRepo repository, StepBuilderFactory stepBuilderFactory, JobLauncher jobLauncher, JobBuilderFactory jobBuilderFactory) {
        this.repository = repository;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobLauncher = jobLauncher;
        this.jobBuilderFactory = jobBuilderFactory;
        this.mapper = new ObjectMapper();
    }

    //
    /*
       Json file must be in the form of a JSON Array ie [{},{}]
    */
    public void run(File file, int chunkSize) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException,FileNotFoundException {
        run(file.getName(),new FileSystemResource(file),chunkSize);
    }

    @Override
    public void run(String stepName,Resource resource, int chunkSize) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters params = new JobParametersBuilder().addLong("jobId", System.currentTimeMillis()).toJobParameters();
        Job job = jobBuilderFactory
                .get("job")
                .incrementer(new RunIdIncrementer())
                .flow(stepBuilderFactory
                        .get("step-" + stepName)
                        .<Map<String, Object>,User>chunk(chunkSize)
                        .reader(reader(resource))
                        .processor(processor())
                        .writer(writer())
                        .build())
                .end()
                .build();
        jobLauncher.run(job, params);
    }

    @Override
    public ItemProcessor<Map<String, Object>, User> processor() {
        return map -> mapper.convertValue(map,User.class);
    }

    @Override
    public ItemWriter<User> writer() {
       return repository::saveAll;
    }

    @Override
    public ItemReader<Map<String, Object>> reader(Resource resource) {
        return new JsonItemReaderBuilder<Map<String, Object>>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(this.mapper, ConcurrentModel.class))
                .resource(resource)
                .name("JsonFileReader")
                .build();
    }
}
