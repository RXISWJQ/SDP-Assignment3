package com.exporter;

import com.exporter.abstraction.EmployeePerformanceReport;
import com.exporter.abstraction.FinancialAuditReport;
import com.exporter.abstraction.Report;
import com.exporter.factory.ExporterFactory;
import com.exporter.implementor.ReportExporter;

public class Main {
    public static void main(String[] args) {
        String requestedFormat = args.length > 0 ? args[0] : "xml";

        ReportExporter exporter = ExporterFactory.createExporter(requestedFormat);

        Report financialReport = new FinancialAuditReport(exporter, "Q3_2026");
        financialReport.addField("Revenue", "$1,500,000");
        financialReport.addField("Expenses", "$900,000");
        financialReport.generateAndExport();

        Report hrReport = new EmployeePerformanceReport(exporter, "Engineering");
        hrReport.addField("TotalEmployees", "45");
        hrReport.addField("AvgRating", "4.8");
        hrReport.generateAndExport();
    }
}