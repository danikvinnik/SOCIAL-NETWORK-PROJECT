package tms.c42onl.socialnetworkproject.config;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCManager  {

    @FunctionalInterface
    public interface PostgresWork {
        public void execute(Connection connection) throws SQLException;
    }

    public void execute(PostgresWork postgresWork) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            connection.setAutoCommit(false);
            try {
                postgresWork.execute(connection);
                connection.commit();
            } catch (SQLException | RuntimeException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    e.addSuppressed(ex);
                }

                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
