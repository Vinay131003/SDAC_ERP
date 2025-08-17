package Pojo;

import java.sql.Timestamp;

public class ReportedProducts {
    private String reportId;                // Unique ID for each report
    private String consumerPortId;       // Foreign key to ConsumerPort table
    private int productId;               // Foreign key to Products table
    private String issueType;            // Type of issue ('damage', 'wrong product', etc.)
    private String solution;             // Solution status ('solved', 'pending')
    private Timestamp reportDate;        // Date of the report

    // No-argument constructor
    public ReportedProducts() {
    }

    // Constructor with parameters
    public ReportedProducts(String reportId, String consumerPortId, int productId, String issueType, String solution, Timestamp reportDate) {
        this.reportId = reportId;
        this.consumerPortId = consumerPortId;
        this.productId = productId;
        this.issueType = issueType;
        this.solution = solution;
        this.reportDate = reportDate;
    }

    // Getters and Setters
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getConsumerPortId() {
        return consumerPortId;
    }

    public void setConsumerPortId(String consumerPortId) {
        this.consumerPortId = consumerPortId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "ReportedProducts{" +
                "reportId=" + reportId +
                ", consumerPortId='" + consumerPortId + '\'' +
                ", productId=" + productId +
                ", issueType='" + issueType + '\'' +
                ", solution='" + solution + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}
