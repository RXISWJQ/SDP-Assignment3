package com.exporter.implementor;

import java.util.Map;

public interface ReportExporter {
    void export(String title, Map<String, String> data);
}