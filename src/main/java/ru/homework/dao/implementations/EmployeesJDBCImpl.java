package ru.homework.dao.implementations;

import ru.homework.dao.EmployeesDao;
import ru.homework.dao.connections.ConnectionBuilder;
import ru.homework.dao.connections.ConnectionBuilderFactory;
import ru.homework.dao.entity.Employees;
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

import static ru.homework.dao.params.DatabaseSelectorsConst.*;

/**
 * Класс реализующий интерфейс CustomersDAO
 * Содержит в себе методы, которые отправляют запросы к БД
 */
public class EmployeesJDBCImpl implements EmployeesDao {


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
    public void insertEmployees(Employees employees) throws NotUniqueIdException, NotUniqueNameException {
        try (Connection connection = getConnection()) {
            if (employees.getId() != 0 && !existWithName(connection, employees.getName())) {
                PreparedStatement prepSt = connection.prepareStatement(INSERT_CUST);
                prepSt.setInt(1, employees.getId());
                prepSt.setString(2, employees.getName());
                prepSt.setInt(3, employees.getRoomNumber());
                prepSt.setInt(4, employees.getSalary());
                prepSt.setString(5, employees.getPosition());
                prepSt.executeUpdate();
            } else if (existWithName(connection, employees.getName())) {
                throw new NotUniqueNameException("Внимание сотрудник с именем \'" + employees.getName() + "\' уже есть в базе");
            } else if (existWithId(connection, employees.getId())) {
                throw new NotUniqueIdException("Внимание сотрудник с id\'" + employees.getId() + "\' уже есть в базе");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employees> selectEmployeeById(Integer id) throws NoSuchIdException {
        Employees employees = null;
        List<Employees> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(SELECT_BY_ID)) {
            prepSt.setInt(1, id);
            try (ResultSet resultSet = prepSt.executeQuery()) {
                while (resultSet.next()) {
                    employees = fillCustomer(resultSet);
                    list.add(employees);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees != null) {
            return list;
        } else {
            throw new NoSuchIdException("There is no record in \"employees\" with ID " + id + "\n");
        }
    }

    @Override
    public List<Employees> selectAll() {
        List<Employees> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillCustomer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteEmployee(Integer id) {
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(DELETE_CUST)) {
            prepSt.setInt(1, id);
            prepSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public  List<Employees> selectEmployeeByName(String name) throws NoSuchNameException {
        Employees employees = null;
        List<Employees> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(SELECT_BY_NAME)) {
            prepSt.setString(1, name);
            try (ResultSet resultSet = prepSt.executeQuery()) {
                while (resultSet.next()) {
                    employees = fillCustomer(resultSet);
                    list.add(employees);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (employees != null) {
            return list ;
        } else {
            throw new NoSuchNameException("Нет такого сотрудника в таблице \"employees\" с именем " + name + "\n");
        }
    }


    @Override
    public Integer countOfEmployeeInRoom(Integer roomNumber) {
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
    public List<Employees> listOfEmployeeInRoom() {
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
    //TODO выделить в отдельный класс
    private Employees fillCustomer(ResultSet rs) throws SQLException {
        Employees employees = new Employees();
        employees.setId(rs.getInt("Idcust"));
        employees.setName(rs.getString("Name"));
        employees.setRoomNumber(rs.getInt("Room_number"));
        employees.setPosition(rs.getString("position"));
        employees.setSalary(rs.getInt("salary"));
        return employees;
    }
}