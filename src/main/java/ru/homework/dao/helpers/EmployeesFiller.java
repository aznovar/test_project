package ru.homework.dao.helpers;

import ru.homework.dao.entity.Employees;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesFiller implements FillTheEntityBeanAfterReceivingResultSet<Employees> {

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
            e.printStackTrace();
        }
        return null;
    }
}
