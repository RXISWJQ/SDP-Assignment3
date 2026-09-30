package com.exporter.abstraction;

import com.exporter.implementor.ReportExporter;
import java.util.HashMap;
import java.util.Map;

public abstract class Report {
    protected final ReportExporter exporter;
    protected final Map<String, String> data = new HashMap<>();

    public Report(ReportExporter exporter) {
        this.exporter = exporter;
    }

    public void addField(String key, String value) {
        data.put(key, value);
    }

    public abstract void generateAndExport();
}