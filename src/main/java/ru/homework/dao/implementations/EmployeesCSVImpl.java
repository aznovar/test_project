package ru.homework.dao.implementations;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.homework.dao.EmployeesDao;
import ru.homework.dao.connection.fileconnection.ConnectionToFileBuilderFactory;
import ru.homework.dao.connection.fileconnection.FileConnectionBuilder;
import ru.homework.dao.entity.Employees;
import ru.homework.dao.helpers.ContentParser;
import ru.homework.dao.helpers.EmployeesContentParserInCsvTable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Имплементация интерфейса EmployeesDao, реализующая методы
 * содержащие в себе CRUD запросы к файлу типа Csv
 */
public class EmployeesCSVImpl implements EmployeesDao {

    private FileConnectionBuilder builder = ConnectionToFileBuilderFactory.getCsvFile();
    private ContentParser contentParser = EmployeesContentParserInCsvTable.getExemplarOfContentParser();

    private Path getFile() {
        return builder.getFile();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void insertEmployees(Employees employees) {
        List<Employees> employees1;
        employees1 = contentParser.getContentOfFile();
        employees1.add(employees);
        try (BufferedWriter writer = Files.newBufferedWriter(getFile(),
                StandardCharsets.UTF_8)) {
            StatefulBeanToCsv<Employees> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(employees1);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            Logger.getLogger(EmployeesCSVImpl.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employees> selectEmployeeById(Integer id) {
        List<Employees> employees1 = contentParser.getContentOfFile();
        return employees1.stream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Employees> selectAll() {
        return contentParser.getContentOfFile();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deleteEmployee(Integer id) {
        List<Employees> employees = contentParser.getContentOfFile();
        Iterator<Employees> emplIterator = employees.iterator();
        while (emplIterator.hasNext()) {
            Employees employees1 = emplIterator.next();
            if (employees1.getId().equals(id))
                emplIterator.remove();
        }
        try (BufferedWriter writer = Files.newBufferedWriter(getFile(),
                StandardCharsets.UTF_8)) {
            StatefulBeanToCsv<Employees> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(employees);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            Logger.getLogger(EmployeesCSVImpl.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
        }
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Employees> selectEmployeeByName(String name) {
        List<Employees> employees1 = contentParser.getContentOfFile();
        return employees1.stream()
                .filter(it -> it.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public Long countOfEmployeeInRoom(Long roomNumber) {
        Long numberOfEmployee;
        List<Employees> employees1 = contentParser.getContentOfFile();
        numberOfEmployee = employees1.stream()
                .filter(it -> it.getRoomNumber().equals(roomNumber)).count();
        return numberOfEmployee;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> listOfRoomNumbersAndEmployeesInIt(Long countOfRoom) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        Long number = 0L;
        List<Employees> employees1 = contentParser.getContentOfFile();
        while (++number <= countOfRoom) {
            list.add(fillTheHashMapOfCount(number, employees1));
        }
        return list;
    }

    /**
     * Метод заполняет объект типа хэш-мап двумя параметрами
     *
     * @param number    - это номер комнаты,в которой нужно посчитать число сотрудников
     * @param employees - это список сотрудников и инфы о них, спарсенный с файла.
     *                  По этому списку, при помощи параметра number, выводится значение
     *                  числа сотрудников в комнате
     * @return row - хэш-мапа заполненная двумя параметрами: номером комнаты и числом сотрудников в ней
     */
    private HashMap<String, Object> fillTheHashMapOfCount(final Long number, List<Employees> employees) {
        HashMap<String, Object> row = new HashMap<>();
        row.put("В комнате номер:" + number.toString() + " число сотрудников", employees.stream()
                .filter(it -> it.getRoomNumber().equals(number)).count());
        return row;
    }
}
