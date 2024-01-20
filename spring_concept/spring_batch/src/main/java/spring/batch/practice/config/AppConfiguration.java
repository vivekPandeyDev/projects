package spring.batch.practice.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.*;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.batch.practice.entity.Customer;
import spring.batch.practice.jobManager.CustomerItemProcessor;
import spring.batch.practice.jobManager.CustomerItemWriter;
import spring.batch.practice.repo.CustomerRepo;

@Configuration
@ComponentScan("spring.batch.practice")
@EnableBatchProcessing
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class AppConfiguration {

    @Autowired
    private CustomerRepo customerRepo;

    @Bean
    public ItemReader<Customer> itemReader()
            throws UnexpectedInputException, ParseException {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("customers.csv"));
        reader.setName("csvReader");
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }

    private LineMapper<Customer> lineMapper(){
        DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        String[] tokens = {"id", "firstName", "lastName", "email", "gender", "contactNo", "country", "dob"};
        tokenizer.setNames(tokens);
        tokenizer.setStrict(false);
        tokenizer.setDelimiter(",");

        BeanWrapperFieldSetMapper<Customer> customerBeanWrapperFieldSetMapper =new BeanWrapperFieldSetMapper<>();
        customerBeanWrapperFieldSetMapper.setTargetType(Customer.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(customerBeanWrapperFieldSetMapper);
        return lineMapper;
    }

    @Bean
    public ItemProcessor<Customer, Customer> itemProcessor() {
        return new CustomerItemProcessor();
    }

    @Bean
    public ItemWriter<Customer> writer(){
        return new CustomerItemWriter();
    }

    @Bean
    protected Step step1(JobRepository jobRepository, HibernateTransactionManager transactionManager, ItemReader<Customer> reader,
                         ItemProcessor<Customer, Customer> processor, ItemWriter<Customer> writer) {
        return new StepBuilder("step1", jobRepository).<Customer, Customer> chunk(50, transactionManager)
                .reader(reader).processor(processor).writer(writer).build();
    }

    @Bean(name = "firstBatchJob")
    public Job job(JobRepository jobRepository, @Qualifier("step1") Step step1) {
        return new JobBuilder("firstBatchJob", jobRepository).preventRestart().start(step1).build();
    }
}
