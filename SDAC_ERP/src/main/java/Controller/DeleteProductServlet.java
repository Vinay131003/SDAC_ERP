package Controller; // Change this to your actual package name

import java.io.IOException;
import Products_Dao.DeleteProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productID");
        if (productID != null) {
            DeleteProductDao deleteProductDao = new DeleteProductDao();
            boolean isDeleted = deleteProductDao.deleteProduct(Integer.parseInt(productID));
            if (isDeleted) {
                request.setAttribute("message", "Product deleted successfully.");
            } else {
                request.setAttribute("message", "Failed to delete product.");
            }
        } else {
            request.setAttribute("message", "Product ID is missing.");
        }
        // Redirect back to the product view page
        request.getRequestDispatcher("sellerDashboard.jsp").forward(request, response); // Change to your actual products page
    }
}
