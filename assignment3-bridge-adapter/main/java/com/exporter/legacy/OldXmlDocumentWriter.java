package com.exporter.legacy;

import java.util.List;

public class OldXmlDocumentWriter {
    public XmlWriteStatus writeXmlNode(String rootElement, List<String> keys, List<String> values) {
        if (keys == null || values == null || keys.size() != values.size()) {
            return XmlWriteStatus.ERROR_INVALID_ATTRIBUTES;
        }
        if (rootElement.contains("FAIL_DISK")) {
            return XmlWriteStatus.ERROR_DISK_FULL;
        }
        if (rootElement.contains("FAIL_IO")) {
            return XmlWriteStatus.ERROR_IO_FAILURE;
        }
        System.out.println("[LEGACY XML] Written XML node <" + rootElement + "> with " + keys.size() + " attributes.");
        return XmlWriteStatus.SUCCESS;
    }
}