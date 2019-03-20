package ru.homework.dao.jdbc;

import ru.homework.dao.CustomersDao;
import ru.homework.dao.connections.ConnectionBuilder;
import ru.homework.dao.connections.ConnectionBuilderFactory;
import ru.homework.dao.entity.Customers;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.homework.dao.params.SelectorsConst.*;

/**
 * Класс реализующий интерфейс CustomersDAO
 * Содержит в себе методы, которые отправляют запросы к БД
 */
public class CustomersJDBCImpl implements CustomersDao {


    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();

    /**
     * Метод, при вызове которого, осуществляется подключение к БД
     *
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public void insertCustomer(Customers customers) throws NotUniqueIdException, NotUniqueNameException {
        try (Connection connection = getConnection()) {
            if (customers.getId() != 0 && !existWithName(connection, customers.getName())) {
                PreparedStatement prepSt = connection.prepareStatement(INSERT_CUST);
                prepSt.setInt(1, customers.getId());
                prepSt.setString(2, customers.getName());
                prepSt.setInt(3, customers.getRoomNumber());
                prepSt.setInt(4, customers.getSalary());
                prepSt.setString(5, customers.getPosition());
                prepSt.executeUpdate();
            } else if (existWithName(connection, customers.getName())) {
                throw new NotUniqueNameException("Внимание сотрудник с именем \'" + customers.getName() + "\' уже есть в базе");
            } else if (existWithId(connection, customers.getId())) {
                throw new NotUniqueIdException("Внимание сотрудник с id\'" + customers.getId() + "\' уже есть в базе");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customers selectCustById(Integer id) throws NoSuchIdException {
        Customers customers = null;
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(SELECT_BY_ID)) {
            prepSt.setInt(1, id);
            try (ResultSet resultSet = prepSt.executeQuery()) {
                while (resultSet.next()) {
                    customers = fillCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (customers != null) {
            return customers;
        } else {
            throw new NoSuchIdException("There is no record in \"customers\" with ID " + id + "\n");
        }
    }

    @Override
    public List<Customers> selectAll() {
        List<Customers> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillCustomer(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteCustomer(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(DELETE_CUST)) {
            prepSt.setInt(1, id);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customers selectCustomerByName(String name) throws NoSuchNameException {
        Customers customers = null;
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(SELECT_BY_NAME)) {
            prepSt.setString(1, name);
            try (ResultSet resultSet = prepSt.executeQuery()) {
                while (resultSet.next()) {
                    customers = fillCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (customers != null) {
            return customers;
        } else {
            throw new NoSuchNameException("Нет такого сотрудника в таблице \"customers\" с именем " + name + "\n");
        }
    }


    @Override
    public Integer countOfCustomersInRoom(Integer roomNumber) {
        Integer count = null;
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(COUNT_CUST_IN_ROOM)) {
            prepSt.setInt(1, roomNumber);
            ResultSet rs = prepSt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Customers> listOfCountCustomersInRoom() {
        return null;
    }

    /**
     * Метод проверяет есть ли в текущем наполнении БД
     * сотрудник с именем которое приходит на вход
     *
     * @param connection
     * @param name
     * @return boolean
     * @throws SQLException
     */
    private boolean existWithName(Connection connection, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME);
        statement.setString(1, name);
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return name.equalsIgnoreCase(rs.getString("name"));
            } else {
                return false;
            }
        }
    }

    /**
     * Метод проверяет есть ли в текущем наполнении БД
     * сотрудник с id пришедшем на вход
     *
     * @param connection
     * @param id
     * @return
     * @throws SQLException
     */
    private boolean existWithId(Connection connection, Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setInt(1, id);
        try (ResultSet rs = statement.executeQuery()) {
            return rs.next();
        }
    }

    /**
     * Метод заполняющий объект customers данными,
     * которые появились после успешно выполненного запроса
     *
     * @param rs
     * @return customers
     * @throws SQLException
     */
    private Customers fillCustomer(ResultSet rs) throws SQLException {
        Customers customers = new Customers();
        customers.setId(rs.getInt("Idcust"));
        customers.setName(rs.getString("Name"));
        customers.setRoomNumber(rs.getInt("Room_number"));
        customers.setPosition(rs.getString("position"));
        customers.setSalary(rs.getInt("salary"));
        return customers;
    }
}