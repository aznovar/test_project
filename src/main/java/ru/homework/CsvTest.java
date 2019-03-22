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
import java.util.List;
import java.util.stream.Collectors;

public class CsvTest {

    public static void main(String[] args) throws IOException {

        String fileName = "src/main/resources/customers.csv";
        Path myPath = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            HeaderColumnNameMappingStrategy<Employees> strategy
                    = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Employees.class);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(Employees.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Employees> employees1 = csvToBean.parse();

            List<Employees> matches = employees1.stream()
                    .filter(it -> it.getRoomNumber().equals(1))
                    .collect(Collectors.toList());

            System.out.println(matches.get(0));
        }
    }
}
