package com.exporter.implementor;

import com.exporter.exception.ReportExportException;
import java.util.Map;

public class CsvReportExporter implements ReportExporter {
    @Override
    public void export(String title, Map<String, String> data) {
        if (data == null || data.isEmpty()) {
            throw new ReportExportException("CSV Export failed: Report data cannot be empty.");
        }
        System.out.println("[CSV ENGINE] Exporting CSV report: '" + title + "' with headers " + data.keySet());
    }
}