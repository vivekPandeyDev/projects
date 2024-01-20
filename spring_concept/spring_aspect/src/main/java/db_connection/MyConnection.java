package db_connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MyConnection {

    private static final Logger logger = LogManager.getLogger(MyConnection.class);
    private static Connection connection;
    public static Connection getConnection() {
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
