package Controller;

import Pojo.Products;
import Products_Dao.ViewProductsDao;
import Products_Dao.UpdateProductDao;  // Import UpdateProductDao
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import Implementor.ProductsImp;


public class ProductsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProductsImp productService;
    private ViewProductsDao viewProductsDao;
    private UpdateProductDao updateProductDao;  // Add UpdateProductDao instance

    public void init() {
        productService = new ProductsImp();        // Initialize ProductsImp service class
        viewProductsDao = new ViewProductsDao();    // Initialize ViewProductsDao for fetching all products
        updateProductDao = new UpdateProductDao();  // Initialize UpdateProductDao for updating products
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "addProduct":
                    addProduct(request, response);
                    break;
                case "updateProduct":
                    updateProduct(request, response);
                    break;
                case "deleteProduct":
                    deleteProduct(request, response);
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
                case "viewProducts":
                    viewProducts(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        }
    }

    // Method to add a product
    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Products product = new Products();
        product.setProductID(productID);
        product.setProductName(productName);
        product.setQuantity(quantity);
        product.setPrice(price);

        productService.addProduct(product);

        // Set an alert message in the request scope
        request.setAttribute("message", "Product has been added successfully!");

        // Forward the request back to the original page (e.g., products.jsp)
        RequestDispatcher dispatcher = request.getRequestDispatcher("sellerDashboard.jsp");
        dispatcher.forward(request, response);
    }


    // Method to update a product
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String quantityStr = request.getParameter("quantity");
        String priceStr = request.getParameter("price");

        Products product = new Products();
        product.setProductID(productID);
        
        // Check and set fields only if they are not empty
        if (productName != null && !productName.trim().isEmpty()) {
            product.setProductName(productName);
        }
        if (quantityStr != null && !quantityStr.trim().isEmpty()) {
            product.setQuantity(Integer.parseInt(quantityStr));
        }
        if (priceStr != null && !priceStr.trim().isEmpty()) {
            product.setPrice(Double.parseDouble(priceStr));
        }

        // Use UpdateProductDao to update the product profile in the database
        updateProductDao.updateProduct(product);
        response.sendRedirect("sellerDashboard.jsp");
    }

    // Method to delete a product
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        int productID = Integer.parseInt(request.getParameter("productID"));
        productService.deleteProduct(productID);
        request.setAttribute("message", "Product deleted successfully!");
        response.sendRedirect("sellerDashboard.jsp");
    }



    // Method to view all products
    private void viewProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all products using ViewProductsDao
        List<Products> productsList = viewProductsDao.viewProducts();
        request.setAttribute("productsList", productsList);
        request.getRequestDispatcher("viewProducts.jsp").forward(request, response);
    }

}
