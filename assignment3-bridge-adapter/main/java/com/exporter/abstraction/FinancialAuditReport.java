package com.exporter.abstraction;

import com.exporter.implementor.ReportExporter;

public class FinancialAuditReport extends Report {
    private final String quarter;

    public FinancialAuditReport(ReportExporter exporter, String quarter) {
        super(exporter);
        this.quarter = quarter;
    }

    @Override
    public void generateAndExport() {
        String title = "Financial_Audit_" + quarter;
        addField("ReportType", "Financial Audit");
        addField("Quarter", quarter);
        exporter.export(title, data);
    }
}