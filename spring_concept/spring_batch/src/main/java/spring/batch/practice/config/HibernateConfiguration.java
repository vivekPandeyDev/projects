package spring.batch.practice.config;

import org.hibernate.SessionFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spring.batch.practice.jobManager.SimpleDriverDataSourceFactory;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:dbconfig.properties")
public class HibernateConfiguration {
	private Environment env;

	public HibernateConfiguration(Environment env) {
		super();
		this.env = env;
	}

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	@Bean("dataSource")
	public DataSource getDataSource() {

		EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
		return embeddedDatabaseBuilder
				.addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
				.addScript("classpath:org/springframework/batch/core/schema-h2.sql")
				.setType(EmbeddedDatabaseType.H2)
				.setDataSourceFactory(simpleDriverDataSourceFactory())
				.build();
	}


	@Bean
	public SimpleDriverDataSourceFactory simpleDriverDataSourceFactory(){
		return new SimpleDriverDataSourceFactory();
	}


	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setPackagesToScan("spring.batch.practice.entity");
		lsfb.setHibernateProperties(hibernateProperties());
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lsfb.getObject();
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		return properties;
	}

	@Bean("transactionManager")
	public HibernateTransactionManager hibTransMan() {
		return new HibernateTransactionManager(sessionFactory());
	}


	@Bean
	public JobRepository getJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(getDataSource());
		factory.setTransactionManager(hibTransMan());
		factory.afterPropertiesSet();
		return factory.getObject();
	}
	@Bean("jobLauncher")
	public JobLauncher getJobLauncher() throws Exception {
		TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}

	@PreDestroy
	public void postConstruct(){
		System.out.println("----------------------------------------PROGRAM ENDS-------------------------------------------------");
	}
}
