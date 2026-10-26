package com.example.parser;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class CsvToJsonConverter {
 
    public static List<Map<String, String>> convertCsvToJson(String filename) {
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(
                Files.newBufferedReader(Paths.get(filename), StandardCharsets.UTF_8))
                .withCSVParser(parser)
                .build()) {

            // Read the CSV file
            // CSVReader reader = new CSVReader(new FileReader(filename));
            List<String[]> csvData = reader.readAll();
            reader.close();
 
            // Get the header row
            String[] headers = csvData.get(0);
            headers[0] = headers[0].replace("\uFEFF", "");
 
            // Create a list to hold JSON objects
            List<Map<String, String>> jsonList = new ArrayList<>();
 
            // Iterate through the data rows
            for (int i = 1; i < csvData.size(); i++) {
                String[] row = csvData.get(i);
                Map<String, String> jsonObject = new HashMap<>();
                for (int j = 0; j < headers.length; j++) {
                    jsonObject.put(headers[j], row[j]);
                }
                jsonList.add(jsonObject);
            }
 
            return jsonList;
 
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return null;
        }
    }
}