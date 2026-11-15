package tms.c42onl.socialnetworkproject.servlets;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
import tms.c42onl.socialnetworkproject.config.DatabaseConfig;

@WebListener
public class ServletContextListener implements jakarta.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConfig config = DatabaseConfig.getDatabaseConfig();
        Flyway.configure()
                .dataSource(
                        config.getUrl(),
                        config.getUsername(),
                        config.getPassword()
                )
                .load()
                .migrate();
    }
}
