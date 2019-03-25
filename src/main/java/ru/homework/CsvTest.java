package ru.homework;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import ru.homework.dao.entity.Employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvTest {

    public static void main(String[] args) throws IOException {
        List<HashMap<String, Object>> list = new ArrayList<>();
        String fileName = "src/main/resources/employees.csv";
        Path myPath = Paths.get(fileName);
        int number = 1;
        try (BufferedReader br = Files.newBufferedReader(myPath)) {
            CsvToBean csvToBean = new CsvToBeanBuilder<>(br)
                    .withType(Employees.class)
                    .withMappingStrategy(setColumMapping())
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Employees> employees1 = csvToBean.parse();
            HashMap<String, Object> row = new HashMap<>(number);
            Long numberser = employees1.stream()
                    .filter(it -> it.getRoomNumber().equals(1)).count();
            row.put("1", numberser );
            list.add(row);
            System.out.println(employees1);
        }
    }

    private static HeaderColumnNameMappingStrategy setColumMapping() {
        HeaderColumnNameMappingStrategy<Employees> strategy
                = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Employees.class);
        return strategy;
    }
}
