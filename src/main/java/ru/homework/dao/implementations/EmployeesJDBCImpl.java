package ru.homework.dao.implementations;

import org.apache.log4j.Logger;
import ru.homework.consoleview.CommandHelper;
import ru.homework.dao.EmployeesDao;
import ru.homework.dao.connection.dbconnection.ConnectionToDatabaseBuilder;
import ru.homework.dao.connection.ConnectionToDatabaseBuilderFactory;
import ru.homework.dao.entity.Employees;
import ru.homework.dao.helpers.EmployeesFiller;
import ru.homework.dao.helpers.FillTheEntityBeanAfterReceivingResultSet;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;
import ru.homework.exceptions.NotUniqueIdException;
import ru.homework.exceptions.NotUniqueNameException;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Класс реализующий интерфейс CustomersDAO
 * Содержит в себе методы, которые отправляют запросы к БД
 */
public class EmployeesJDBCImpl implements EmployeesDao {

    private static final String INSERT_CUST =
            "INSERT INTO mydb.customers(Idcust,Name,Room_number, Salary,Position) VALUES(?,?,?,?,?)";

    private static final String SELECT_ALL =
            "SELECT * FROM mydb.customers";

    private static final String SELECT_BY_ID =
            "SELECT * FROM mydb.customers WHERE Idcust=?";

    private static final String SELECT_BY_NAME =
            "SELECT * FROM mydb.customers WHERE name=?";

    private static final String DELETE_CUST =
            "DELETE FROM customers WHERE Idcust=?";

    private static final String COUNT_CUST_IN_ROOM =
            "SELECT COUNT(Idcust)AS count FROM mydb.customers WHERE Room_number=?";

    private static final String LIST_OF_EMPLOYEES_COUNT_IN_ROOM =
            "select room_number, count(Name) from mydb.customers where Room_number between 1 and ? group by room_number";

    private ConnectionToDatabaseBuilder builder = ConnectionToDatabaseBuilderFactory.getMySQLConnectionBuilder();
    private static final Logger log = Logger.getLogger(EmployeesJDBCImpl.class);
    private FillTheEntityBeanAfterReceivingResultSet<Employees> employeesFiller = new EmployeesFiller();

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
                prepSt.setLong(3, employees.getRoomNumber());
                prepSt.setInt(4, employees.getSalary());
                prepSt.setString(5, employees.getPosition());
                prepSt.executeUpdate();
            } else if (existWithName(connection, employees.getName())) {
                throw new NotUniqueNameException("Внимание сотрудник с именем \'" + employees.getName() + "\' уже есть в базе");
            } else if (existWithId(connection, employees.getId())) {
                throw new NotUniqueIdException("Внимание сотрудник с id\'" + employees.getId() + "\' уже есть в базе");
            }
        } catch (SQLException e) {
           log.error(e);
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
                    employees = employeesFiller.fiilTheEntityBeanByResultSet(resultSet);
                    list.add(employees);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        if (employees != null) {
            return list;
        } else {
            throw new NoSuchIdException("Нет записи в\"employees\" с ID = " + id + "\n");
        }
    }

    @Override
    public List<Employees> selectAll() {
        List<Employees> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(employeesFiller.fiilTheEntityBeanByResultSet(rs));
            }
        } catch (SQLException e) {
           log.error(e);
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
            log.error(e);
        }
    }

    @Override
    public List<Employees> selectEmployeeByName(String name) throws NoSuchNameException {
        Employees employees = null;
        List<Employees> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(SELECT_BY_NAME)) {
            prepSt.setString(1, name);
            try (ResultSet resultSet = prepSt.executeQuery()) {
                while (resultSet.next()) {
                    employees = employeesFiller.fiilTheEntityBeanByResultSet(resultSet);
                    list.add(employees);
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
        if (employees != null) {
            return list;
        } else {
            throw new NoSuchNameException("Нет такого сотрудника в таблице \"employees\" с именем " + name + "\n");
        }
    }


    @Override
    public Long countOfEmployeeInRoom(Long roomNumber) {
        Long count = null;
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(COUNT_CUST_IN_ROOM)) {
            prepSt.setLong(1, roomNumber);
            ResultSet rs = prepSt.executeQuery();
            while (rs.next()) {
                count = rs.getLong("count");
            }
        } catch (SQLException e) {
           log.error(e);
        }
        return count;
    }

    @Override
    public List<HashMap<String, Object>> listOfRoomNumbersAndEmployeesInIt(Long roomNumber) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement prepSt = connection.prepareStatement(LIST_OF_EMPLOYEES_COUNT_IN_ROOM)) {
            prepSt.setLong(1, roomNumber);
            ResultSet rs = prepSt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return list;
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

}