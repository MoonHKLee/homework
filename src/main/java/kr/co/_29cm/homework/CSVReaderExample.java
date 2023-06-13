package kr.co._29cm.homework;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.domain.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CSVReaderExample {
    private final ResourceLoader resourceLoader;
    @Value("${csv.file.path}")
    private String csvPath;

    @PostConstruct
    private void readProductsFromCSV() {
        try {
            Resource resource = resourceLoader.getResource(csvPath);
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            registerCsvData(inputStreamReader);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void registerCsvData(InputStreamReader inputStreamReader) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(inputStreamReader)) {
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                String id = record[0];
                String name = record[1];
                int price = Integer.parseInt(record[2]);
                int count = Integer.parseInt(record[3]);

                Products.put(id, new Product(name, price, count));
            }
        }
    }
}
