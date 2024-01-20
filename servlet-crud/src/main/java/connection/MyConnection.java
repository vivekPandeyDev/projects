package connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyConnection {
	private static Connection connection;
	private static final Logger logger = LogManager.getLogger(MyConnection.class);

	private MyConnection() {
	}

	public static Connection getConnection() {

		try (InputStream file = MyConnection.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
			Properties properties = new Properties();
			properties.load(file);
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(properties.getProperty("uri"), properties);
			logger.info("connection created successfully!!");
		} catch (Exception e) {
			logger.error("Cannot create connection : {}", e.getMessage());
		}
		return connection;
	}
}
