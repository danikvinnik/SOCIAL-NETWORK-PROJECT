package tms.c42onl.socialnetworkproject.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DatabaseConfig {

    private String url;
    private String username;
    private String password;

    private static DatabaseConfig config ;

    public static DatabaseConfig getDatabaseConfig() {
        if(config == null) {
            config = new DatabaseConfig(
                    envConfig("DB_URL"),
                    envConfig("DB_USER"),
                    envConfig("DB_PASSWORD")
            );
        }
        return config;
    }

    private static String envConfig(String name) {
        String value = System.getenv(name);
        if(value == null || value.isBlank())
            throw new IllegalArgumentException("Не задана переменная окружения %s".formatted(name));
        return value;
    }
}
