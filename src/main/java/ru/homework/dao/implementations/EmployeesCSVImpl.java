package ru.homework.dao.implementations;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import ru.homework.dao.EmployeesDao;
import ru.homework.dao.entity.Employees;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesCSVImpl implements EmployeesDao {

    @Override
    public void insertEmployees(Employees employees) throws NoSuchFileException {

    }

    @Override
    public List<Employees> selectEmployeeById(Integer id) throws NoSuchIdException {
        String fileName = "src/main/resources/customers.csv";
        Path myPath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath)) {
            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(Employees.class)
                    .withMappingStrategy(setColumMapping())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Employees> employees1 = csvToBean.parse();
            return employees1.stream()
                    .filter(it -> it.getId().equals(id))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employees> selectAll() throws NoSuchFileException {
        String fileName = "src/main/resources/customers.csv";
        Path myPath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath)) {
            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(Employees.class)
                    .withMappingStrategy(setColumMapping())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteEmployee(Integer id) {

    }

    @Override
    public List<Employees> selectEmployeeByName(String name) throws NoSuchNameException {
        String fileName = "src/main/resources/customers.csv";
        Path myPath = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(myPath)) {
            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(Employees.class)
                    .withMappingStrategy(setColumMapping())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Employees> employees1 = csvToBean.parse();
            return employees1.stream()
                    .filter(it -> it.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Integer countOfEmployeeInRoom(Integer roomNumber) {
        return null;
    }

    @Override
    public List<Employees> listOfEmployeeInRoom() {
        return null;
    }

    private static HeaderColumnNameMappingStrategy setColumMapping() {
        HeaderColumnNameMappingStrategy<Employees> strategy
                = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Employees.class);
        return strategy;
    }
}
