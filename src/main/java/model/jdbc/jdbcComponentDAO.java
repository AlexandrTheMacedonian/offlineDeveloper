package model.jdbc;

import model.Component;
import model.ComponentDAO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexandrsemenov on 27.02.17.
 */
public class jdbcComponentDAO implements ComponentDAO {

    private DataSource dataSource;


    public Component read(long id) {
        String SQL = "SELECT id, name, price FROM components WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
             preparedStatement.setLong(1, id);
             ResultSet resultSet = preparedStatement.executeQuery(SQL);
             Component component = new Component();
             component.setId(resultSet.getInt("id"));
             component.setName(resultSet.getString("name"));
             component.setPrice(resultSet.getLong("price"));
             return component;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Component create(String name, long price) {
        String GET_LAST_INSERTED = "SELECT LAST_INSERT_ID()";
        String SQL = "INSERT INTO components (name, price) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, price);
            preparedStatement.executeUpdate();
            try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_LAST_INSERTED)) {
                resultSet.next();
                Component component = new Component();
                component.setId(resultSet.getInt(1));
                component.setPrice(price);
                component.setName(name);
                return component;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
