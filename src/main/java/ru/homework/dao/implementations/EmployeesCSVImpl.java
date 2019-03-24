package ru.homework.dao.implementations;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ru.homework.dao.EmployeesDao;
import ru.homework.dao.connection.ConnectionToFileBuilderFactory;
import ru.homework.dao.connection.fileconnection.FileConnectionBuilder;
import ru.homework.dao.entity.Employees;
import ru.homework.dao.helpers.EmployeesContentParserInCsvTable;
import ru.homework.exceptions.NoSuchIdException;
import ru.homework.exceptions.NoSuchNameException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EmployeesCSVImpl implements EmployeesDao {

private FileConnectionBuilder builder = ConnectionToFileBuilderFactory.getCsvFile();

private Path getFile(){
    return builder.getFile();
}

    @Override
    @SuppressWarnings("unchecked")
    public void insertEmployees(Employees employees) throws IOException {
        List<Employees> employees1;
        employees1 = EmployeesContentParserInCsvTable.getExemplarOfContentParser().getContentOfFile();
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
    public List<Employees> selectEmployeeById(Integer id) throws NoSuchIdException {
        List<Employees> employees1 = EmployeesContentParserInCsvTable.getExemplarOfContentParser().getContentOfFile();
        return employees1.stream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
    }


    @Override
    public List<Employees> selectAll() throws NoSuchFileException {
       return EmployeesContentParserInCsvTable.getExemplarOfContentParser().getContentOfFile();
    }

    @Override
    public void deleteEmployee(Integer id) {
            List<Employees> employees = EmployeesContentParserInCsvTable.getExemplarOfContentParser().getContentOfFile();
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
    public List<Employees> selectEmployeeByName(String name) throws NoSuchNameException {
            List<Employees> employees1 = EmployeesContentParserInCsvTable.getExemplarOfContentParser().getContentOfFile();
            return employees1.stream()
                    .filter(it -> it.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

    @Override
    public Long countOfEmployeeInRoom(Long roomNumber) {
        String fileName = "src/main/resources/customers.csv";
        Path myPath = Paths.get(fileName);
        Long numberOfEmployee;
        try (BufferedReader br = Files.newBufferedReader(myPath)) {
            CsvToBean csvToBean = new CsvToBeanBuilder<>(br)
                    .withType(Employees.class)
                    .withMappingStrategy(setColumMapping())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Employees> employees1 = csvToBean.parse();
            numberOfEmployee = employees1.stream()
                    .filter(it -> it.getRoomNumber().equals(roomNumber)).count();
            return numberOfEmployee;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> listOfRoomNumbersAndEmployeesInIt(Long countOfRoom) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        String fileName = "src/main/resources/customers.csv";
        Path myPath = Paths.get(fileName);
        Long number = 0L;
        try (BufferedReader br = Files.newBufferedReader(myPath)) {
            CsvToBean csvToBean = new CsvToBeanBuilder<>(br)
                    .withType(Employees.class)
                    .withMappingStrategy(setColumMapping())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Employees> employees1 = csvToBean.parse();
            while (++number <= countOfRoom) {
                list.add(fillTheHashMapOfCount(number, employees1));
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private HashMap<String, Object> fillTheHashMapOfCount(final Long number, List<Employees> employees) {
        HashMap<String, Object> row = new HashMap<>();
        row.put("В комнате номер:" + number.toString() + " число сотрудников", employees.stream()
                .filter(it -> it.getRoomNumber().equals(number)).count());
        return row;
    }

    private static HeaderColumnNameMappingStrategy setColumMapping() {
        HeaderColumnNameMappingStrategy<Employees> strategy
                = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Employees.class);
        return strategy;
    }
}
