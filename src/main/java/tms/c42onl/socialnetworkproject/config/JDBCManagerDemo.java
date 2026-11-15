package tms.c42onl.socialnetworkproject.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCManagerDemo {

    private final JDBCManager jdbcManager;

    public JDBCManagerDemo() {
        this.jdbcManager = new JDBCManager();
    }

    public void testJDBCConnection() throws SQLException {
        String sql = "SELECT * FROM users";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {
        }

    }

    public void testTransactionWithCommit() {
        jdbcManager.execute(connection -> {
            String sql1 = "INSERT INTO users ( name, username) VALUES (?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setString(1, "Danik");
                preparedStatement.setString(2, "danik_test_1");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Ivan");
                preparedStatement.setString(2, "ivan_test_1");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void testTransactionWithRollback() {
        jdbcManager.execute(connection -> {
            String sql1 = "INSERT INTO users ( name, username) VALUES (?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setString(1, "Zhenya");
                preparedStatement.setString(2, "zhenya_test_1");
                preparedStatement.executeUpdate();

                preparedStatement.setString(1, "Ivan");
                preparedStatement.setString(2, "zhenya_test_1");
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
