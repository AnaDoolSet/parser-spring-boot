package com.example.restservice;

import java.util.List;
import java.util.Map;

import com.example.parser.CsvToJsonConverter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CsvToJsonDataController {
  @GetMapping("/csvtojson")
  public CsvToJsonData csvToJson(@RequestParam(defaultValue = "{}") String filename) {
    List<Map<String, String>> jsonList = CsvToJsonConverter.convertCsvToJson(filename);
    return new CsvToJsonData(jsonList);
  }
}