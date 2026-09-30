# Design Rationale Document: Document Export Engine

## 1. Selected Problem Domain
The system automates the generation and export of company digital reports (`FinancialAuditReport`, `EmployeePerformanceReport`) into various file formats (`PDF`, `CSV`, `XML`).

## 2. Why Bridge Alone or Adapter Alone is Not Enough
* **Why Bridge alone fails:** The Bridge pattern decouples the report abstractions from the export engine implementations. However, a required legacy export module (`OldXmlDocumentWriter`) has an incompatible interface and returns status objects instead of throwing exceptions. Without an Adapter, the Bridge `Implementor` interface cannot accommodate this legacy component without modifying its source code.
* **Why Adapter alone fails:** The Adapter pattern can unify the interface of `OldXmlDocumentWriter` with `ReportExporter`. However, using only Adapter would bind report types directly to export implementations, leading to a class explosion (e.g., `FinancialAuditPdfReport`, `FinancialAuditCsvReport`, `EmployeePerformancePdfReport`).

## 3. Genuine Incompatibility of the Adapted Class
The `OldXmlDocumentWriter` class meets the strict incompatibility bar required by Section 3.3:
1. **Method & Signature mismatch:** It uses `writeXmlNode` instead of `export`.
2. **Data Structure mismatch:** It accepts two separate `List<String>` parameters (keys and values) rather than a unified `Map<String, String>`.
3. **Failure Mechanism mismatch:** It returns an `XmlWriteStatus` enum value (`ERROR_DISK_FULL`, `ERROR_INVALID_ATTRIBUTES`) instead of throwing exceptions. The `XmlReportExporterAdapter` intercepts these status codes and translates them into uniform `ReportExportException` instances.

## 4. Complexity Module Selection
* **Selected Module:** Dynamic Implementor Selection (Section 5).
* **Implementation:** The `ExporterFactory` selects the `ReportExporter` implementation at runtime based on the input argument, insulating the client from hard-coding concrete export classes.

## 5. Limitation of the Final Design
Adding a new parameter to the `ReportExporter` interface (e.g., adding `String author`) requires updating all concrete implementors, including the adapter class.