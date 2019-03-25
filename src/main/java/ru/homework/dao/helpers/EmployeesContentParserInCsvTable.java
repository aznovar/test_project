package ru.homework.dao.helpers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.log4j.Logger;
import ru.homework.dao.connection.fileconnection.ConnectionToFileBuilderFactory;
import ru.homework.dao.connection.fileconnection.FileConnectionBuilder;
import ru.homework.dao.entity.Employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * Данный класс - имплементация интерфейса ContentParser,
 * для парсинга файла типа Csv с использованием в качестве типа entity Employees
 */
public class EmployeesContentParserInCsvTable implements ContentParser<Employees> {

    private static final Logger log = Logger.getLogger(EmployeesFiller.class);

    private FileConnectionBuilder builder = ConnectionToFileBuilderFactory.getCsvFile();

    private Path getFile() {
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
            log.error(e);
        }
        return Collections.emptyList();
    }

    /**
     * Сопоставляет данные с объектом(в данном случае это Employees),
     * используя имена столбцов в первой строке CSV-файла в качестве ссылки
     *
     * @return
     */
    private static HeaderColumnNameMappingStrategy setColumMapping() {
        HeaderColumnNameMappingStrategy<Employees> strategy
                = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Employees.class);
        return strategy;
    }
}
