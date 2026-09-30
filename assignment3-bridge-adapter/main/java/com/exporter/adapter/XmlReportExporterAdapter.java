package com.exporter.adapter;

import com.exporter.exception.ReportExportException;
import com.exporter.implementor.ReportExporter;
import com.exporter.legacy.OldXmlDocumentWriter;
import com.exporter.legacy.XmlWriteStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XmlReportExporterAdapter implements ReportExporter {
    private final OldXmlDocumentWriter legacyWriter;

    public XmlReportExporterAdapter(OldXmlDocumentWriter legacyWriter) {
        this.legacyWriter = legacyWriter;
    }

    @Override
    public void export(String title, Map<String, String> data) {
        if (data == null) {
            throw new ReportExportException("XML Export failed: Data map is null.");
        }

        List<String> keys = new ArrayList<>(data.keySet());
        List<String> values = new ArrayList<>(data.values());

        XmlWriteStatus status = legacyWriter.writeXmlNode(title, keys, values);

        if (status != XmlWriteStatus.SUCCESS) {
            throw new ReportExportException("Legacy XML export failed with status: " + status);
        }
    }
}