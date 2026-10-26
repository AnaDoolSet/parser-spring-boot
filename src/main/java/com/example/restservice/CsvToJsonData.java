package com.example.restservice;

import java.util.List;
import java.util.Map;

public record CsvToJsonData(List<Map<String, String>> json) {
}
