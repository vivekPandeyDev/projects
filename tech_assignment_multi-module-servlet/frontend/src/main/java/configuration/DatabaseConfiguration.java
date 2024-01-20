package configuration;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Log4j2
public class DatabaseConfiguration {
    private static Connection connection;

    private DatabaseConfiguration(){}

    public static Connection getConnection() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.1.50.198:1535:nsbt19c", "training", "training");
            log.info("connection created successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(){
        try {
            if(!connection.isClosed()){
                connection.close();
            }
            log.info("database is closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void rollback(){
        try {
            if(!connection.isClosed()){
                connection.rollback();
            }
            log.info("database is roll backed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
