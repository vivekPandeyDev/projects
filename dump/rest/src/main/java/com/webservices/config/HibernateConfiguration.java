package com.webservices.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("driver")));
		dataSource.setUrl(env.getProperty("uri"));
		dataSource.setUsername(env.getProperty("user"));
		dataSource.setPassword(env.getProperty("password"));
		return dataSource;
	}

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setPackagesToScan("com.webservices.entity");
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
		properties.put("current_session_context_class", "thread");
		properties.put("hibernate.dbcp.initialSize", 20);
		properties.put("hibernate.dbcp.maxTotal", 10);
		properties.put("hibernate.dbcp.minIdle", 5);
		properties.put("hibernate.dbcp.maxWaitMillis", -1);
		properties.put("hibernate.h2.console.enabled",true);
		properties.put("spring.h2.console.settings.web-allow-others",true);
		properties.put("spring.datasource.platform","h2");
		
		return properties;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager txm = new HibernateTransactionManager();
		txm.setSessionFactory(sessionFactory());
		return txm;
	}

}
