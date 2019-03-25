package ru.homework.dao.helpers;

import org.apache.log4j.Logger;
import ru.homework.dao.entity.Employees;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Этот класс имплементирует интерфейс FillTheEntityBeanAfterReceivingResultSet
 * для объекта типа Employees и заполняет его поля данными полученными из ResultSet объекта
 * смапленными по названиям столбцов
 */
public class EmployeesFiller implements FillTheEntityBeanAfterReceivingResultSet<Employees> {

    private static final Logger log = Logger.getLogger(EmployeesFiller.class);

    @Override
    public Employees fiilTheEntityBeanByResultSet(ResultSet resultSet) {
        try {
            Employees employees = new Employees();
            employees.setId(resultSet.getInt("Idcust"));
            employees.setName(resultSet.getString("Name"));
            employees.setRoomNumber(resultSet.getLong("Room_number"));
            employees.setPosition(resultSet.getString("position"));
            employees.setSalary(resultSet.getInt("salary"));
            return employees;
        } catch (SQLException e) {
            log.error(e);
        }
        return null;
    }
}
