package tms.c42onl.socialnetworkproject.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private ConnectionProvider() {}

    public static Connection getConnection() {
        DatabaseConfig databaseConfig = DatabaseConfig.getDatabaseConfig();
            try {
                 return DriverManager.getConnection(databaseConfig.getUrl(),
                        databaseConfig.getUsername(),
                        databaseConfig.getPassword());
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка подключения к PostgreSQL", e);
            }
    }
}
