package com.exporter;

import com.exporter.abstraction.EmployeePerformanceReport;
import com.exporter.abstraction.FinancialAuditReport;
import com.exporter.abstraction.Report;
import com.exporter.adapter.XmlReportExporterAdapter;
import com.exporter.exception.ReportExportException;
import com.exporter.implementor.ReportExporter;
import com.exporter.legacy.OldXmlDocumentWriter;
import com.exporter.legacy.XmlWriteStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportExporterTest {

    @Mock
    private ReportExporter mockExporter;

    @Mock
    private OldXmlDocumentWriter mockLegacyWriter;

    @Test
    void testFinancialAuditReportDelegation() {
        Report report = new FinancialAuditReport(mockExporter, "Q1_2026");
        report.generateAndExport();

        verify(mockExporter).export(eq("Financial_Audit_Q1_2026"), any(Map.class));
    }

    @Test
    void testEmployeePerformanceReportDelegation() {
        Report report = new EmployeePerformanceReport(mockExporter, "IT");
        report.generateAndExport();

        verify(mockExporter).export(eq("Employee_Performance_IT"), any(Map.class));
    }

    @Test
    void testAdapterSuccessTranslation() {
        when(mockLegacyWriter.writeXmlNode(any(), any(), any())).thenReturn(XmlWriteStatus.SUCCESS);

        XmlReportExporterAdapter adapter = new XmlReportExporterAdapter(mockLegacyWriter);
        Report report = new FinancialAuditReport(adapter, "Q2_2026");
        report.generateAndExport();

        verify(mockLegacyWriter).writeXmlNode(eq("Financial_Audit_Q2_2026"), any(), any());
    }

    @Test
    void testAdapterFailureTranslationToException() {
        when(mockLegacyWriter.writeXmlNode(any(), any(), any())).thenReturn(XmlWriteStatus.ERROR_DISK_FULL);

        XmlReportExporterAdapter adapter = new XmlReportExporterAdapter(mockLegacyWriter);
        Report report = new FinancialAuditReport(adapter, "FAIL_DISK");

        ReportExportException exception = assertThrows(
                ReportExportException.class,
                report::generateAndExport
        );

        assertTrue(exception.getMessage().contains("ERROR_DISK_FULL"));
    }
}