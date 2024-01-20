package app_config;

import dao.Dao;
import entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"entity","service","dao"})
public class JavaConfig {
    public static final Logger logger = LogManager.getLogger(JavaConfig.class);
    @Bean("connection")
    public Connection getConnection(){
        Connection connection= null;
        try (FileInputStream file = new FileInputStream("src/main/resources/dbconfig.properties")) {
            Properties properties = new Properties();
            properties.load(file);
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("uri"), properties);
            logger.info("connection created successfully!!");
        } catch (Exception e) {
            logger.error("error connecting to database: {}",e.getMessage());
        }
        return connection;

    }

}
