package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"service", "dao", "entity"})
public class BeanConfiguration {

    private static final Logger logger = LogManager.getLogger(BeanConfiguration.class);

    @Bean("connection")
    public Connection getConnection() {
        Connection connection = null;
        try (InputStream file = AppConfiguration.class.getClassLoader().getResourceAsStream("dbconfig.properties")

        ) {
            Properties properties = new Properties();
            properties.load(file);
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("uri"), properties);
            logger.info("connection created successfully!!");
        } catch (Exception e) {
            logger.error("error connecting to database: {}", e.getMessage());
        }
        return connection;

    }
}
