package com.exporter.implementor;

import com.exporter.exception.ReportExportException;
import java.util.Map;

public class PdfReportExporter implements ReportExporter {
    @Override
    public void export(String title, Map<String, String> data) {
        if (title == null || title.isBlank()) {
            throw new ReportExportException("PDF Export failed: Title cannot be empty.");
        }
        System.out.println("[PDF ENGINE] Exporting PDF report: '" + title + "' with " + data.size() + " entries.");
    }
}