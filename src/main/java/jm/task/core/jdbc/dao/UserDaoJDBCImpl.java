package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTO_INCREMENT, name TEXT,lastname TEXT, age INTEGER)";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(createTable);
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
        }

    }
    @Override
    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(dropTable);
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
        }

    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users(name,lastname,age) values(?, ?, ?)";
        Connection con = Util.getConnection();
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось добавить пользователя");
        }

    }
    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        Connection conn = Util.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Не получиось удалить пользователя");
        }
    }
    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось получить пользователей");
        }
        return users;
    }
    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        Connection conn = Util.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Не получилось очистить таблицу");
        }
    }
}
