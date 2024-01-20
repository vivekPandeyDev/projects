package spring.batch.practice.jobManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Driver;


@Component
@PropertySource("classpath:dbconfig.properties")
public class SimpleDriverDataSourceFactory implements DataSourceFactory {

    private final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    @Autowired
    private Environment env;
    @Override
    public ConnectionProperties getConnectionProperties() {
        return new ConnectionProperties() {
            @Override
            public void setDriverClass(Class<? extends Driver> driverClass) {
                dataSource.setDriverClass(driverClass);
            }

            @Override
            public void setUrl(String url) {
                dataSource.setUrl(env.getProperty("uri"));
            }

            @Override
            public void setUsername(String username) {
                dataSource.setUsername(env.getProperty("user"));
            }

            @Override
            public void setPassword(String password) {
                dataSource.setPassword(env.getProperty("password"));
            }
        };
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }
}
