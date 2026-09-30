package com.exporter.abstraction;

import com.exporter.implementor.ReportExporter;

public class EmployeePerformanceReport extends Report {
    private final String department;

    public EmployeePerformanceReport(ReportExporter exporter, String department) {
        super(exporter);
        this.department = department;
    }

    @Override
    public void generateAndExport() {
        String title = "Employee_Performance_" + department;
        addField("ReportType", "Employee Performance");
        addField("Department", department);
        exporter.export(title, data);
    }
}