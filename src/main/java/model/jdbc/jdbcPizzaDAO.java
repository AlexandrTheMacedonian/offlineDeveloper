package model.jdbc;

import model.Component;
import model.Pizza;
import model.PizzaDAO;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Collection;

/**
 * Created by alexandrsemenov on 27.02.17.
 */
public class jdbcPizzaDAO implements PizzaDAO {

    private DataSource dataSource;
    private static final String GET_LAST_INSERTED = "";

    @Override
    public Pizza read(long id) {

        String SQL = "";
        String SecondSQL = "";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(SQL);
            Pizza pizza = new Pizza();
            pizza.setId(resultSet.getInt("id"));
            pizza.setName(resultSet.getString("name"));
            pizza.setPrice(resultSet.getBigDecimal("price"));

            return pizza;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pizza create(String name, BigDecimal price, Collection<Component> components) {

        String SQL = "insert into pizza(name, price) values(?, ?)";
        String SecondSQL = "insert into " +
                "pizza_components(component_id, pizza_id) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                    ps.setString(1, name);
                    ps.setBigDecimal(2, price);
                    ps.executeUpdate();
            }
            long id = 0;

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SecondSQL);
            }

            Pizza pizza = new Pizza();
            pizza.setName(name);
            pizza.setPrice(price);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
