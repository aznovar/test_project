package ru.homework.dao.helpers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import ru.homework.dao.connection.ConnectionToDatabaseBuilderFactory;
import ru.homework.dao.connection.fileconnection.FileConnectionBuilder;
import ru.homework.dao.entity.Employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class EmployeesContentParserInCsvTable implements ContentParser<Employees> {

    private FileConnectionBuilder builder = ConnectionToDatabaseBuilderFactory.getCsvFile();

    private Path getFile(){
        return builder.getFile();
    }

    public static ContentParser<Employees> getExemplarOfContentParser() {
        return new EmployeesContentParserInCsvTable();
    }

    @SuppressWarnings("unchecked")
    public List<Employees> getContentOfFile() {
        try (BufferedReader br = Files.newBufferedReader(getFile())) {
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

    private static HeaderColumnNameMappingStrategy setColumMapping() {
        HeaderColumnNameMappingStrategy<Employees> strategy
                = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Employees.class);
        return strategy;
    }
}
