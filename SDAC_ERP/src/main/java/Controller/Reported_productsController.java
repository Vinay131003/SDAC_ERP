package Controller;

import Pojo.ReportedProducts;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import Implementor.Reported_productsImp;

 // Ensure this matches your servlet mapping
public class Reported_productsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Reported_productsImp reportedProductService;

    @Override
    public void init() {
        reportedProductService = new Reported_productsImp(); // Initialize Reported_productsImp service class
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "reportProduct":
                    reportProduct(request, response);
                    break;
                case "markAsSolved":  // New action for solving the report
                    markAsSolved(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "viewReportedProducts":
                    viewReportedProducts(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        }
    }

    // Method to report a product
    private void reportProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consumerPortId = request.getParameter("consumerPortId");
        int productId = Integer.parseInt(request.getParameter("productId"));
        String issueType = request.getParameter("issueType");
        String solution = request.getParameter("solution");

        ReportedProducts reportedProduct = new ReportedProducts();
        reportedProduct.setConsumerPortId(consumerPortId);
        reportedProduct.setProductId(productId);
        reportedProduct.setIssueType(issueType);
        reportedProduct.setSolution(solution);
        reportedProduct.setReportDate(new Timestamp(System.currentTimeMillis())); // Setting current timestamp

        // Call the service to report the product
        boolean isReported = reportedProductService.reportProduct(reportedProduct);

        // Check if the reporting was successful
        if (isReported) {
            // Set success message
            request.setAttribute("successMessage", "Product has been reported successfully.");
        } else {
            // Set error message
            request.setAttribute("errorMessage", "Failed to report the product.");
        }

        // Forward to the consumer dashboard to show the success or error message
        request.getRequestDispatcher("consumerDashboard.jsp").forward(request, response);
    }

    // Method to view all reported products
    private void viewReportedProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ReportedProducts> reportedProductsList = reportedProductService.viewReportedProducts();
        request.setAttribute("reportedProductsList", reportedProductsList);
        request.getRequestDispatcher("viewReportedProducts.jsp").forward(request, response);
    }

    // Method to handle "Mark as Solved" action
    private void markAsSolved(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportId = request.getParameter("reportId");
        
        // Call the service to mark the product as solved
        boolean isSolved = reportedProductService.markAsSolved(reportId);
        
        // Set success or error message based on the outcome
        if (isSolved) {
            request.setAttribute("successMessage", "Report has been marked as solved.");
        } else {
            request.setAttribute("errorMessage", "Failed to mark report as solved.");
        }
        
        // Redirect to the reported products page after marking as solved
        response.sendRedirect("viewReportedProducts.jsp");
    }
}
