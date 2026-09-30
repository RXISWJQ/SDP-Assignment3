package com.exporter.factory;

import com.exporter.adapter.XmlReportExporterAdapter;
import com.exporter.exception.ReportExportException;
import com.exporter.implementor.CsvReportExporter;
import com.exporter.implementor.PdfReportExporter;
import com.exporter.implementor.ReportExporter;
import com.exporter.legacy.OldXmlDocumentWriter;

public class ExporterFactory {
    public static ReportExporter createExporter(String format) {
        if (format == null) {
            throw new ReportExportException("Format type cannot be null.");
        }

        switch (format.toLowerCase()) {
            case "pdf":
                return new PdfReportExporter();
            case "csv":
                return new CsvReportExporter();
            case "xml":
                return new XmlReportExporterAdapter(new OldXmlDocumentWriter());
            default:
                throw new ReportExportException("Unsupported export format: " + format);
        }
    }
}