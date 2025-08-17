package Controller;

import Pojo.SellerPort;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import Implementor.SellerImp;

public class SellerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SellerImp sellerService;

    public void init() {
        sellerService = new SellerImp(); // Initialize the SellerImp service class
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "register":
                    registerSeller(request, response);
                    break;
                case "login":
                    loginSeller(request, response);
                    break;
                case "update":
                    updateSellerProfile(request, response);
                    break;
                case "delete":
                    deleteSellerProfile(request, response);
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

        if ("viewSellers".equals(action)) {
            viewSellers(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    // Method to register a new seller
    private void registerSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        SellerPort seller = new SellerPort(portID, password, role);
        sellerService.registerSeller(seller);
        response.sendRedirect("login.jsp");
    }

    // Method to login a seller
 // Method to login a seller
    private void loginSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");
        String password = request.getParameter("password");

        SellerPort seller = new SellerPort(portID, password, null);
        
        // Check if the login was successful
        boolean loginSuccess = sellerService.loginSeller(seller);
        if (loginSuccess) {
            // Login successful, redirect to the seller dashboard
        	request.getSession().setAttribute("port_id", portID);
            response.sendRedirect("sellerDashboard.jsp");
        } else {
            // Login failed, redirect back to the login page with an error
            response.sendRedirect("login.jsp?error=invalid");
        }
    }

    // Method to update seller profile
 // Method to update seller profile
    private void updateSellerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (portID == null || password == null || role == null) {
            response.sendRedirect("update.jsp?error=missingFields");
            return;
        }

        SellerPort seller = new SellerPort(portID, password, role);
        sellerService.updateSellerProfile(seller);
        response.sendRedirect("updateSuccess.jsp");
    }
    // Method to delete a seller profile
    private void deleteSellerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");

        SellerPort seller = new SellerPort(portID, null, null);
        sellerService.deleteSellerProfile(seller);
        response.sendRedirect("deletionSuccess.jsp");
    }

    // Method to view all sellers
    private void viewSellers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SellerPort> sellersList = sellerService.viewSellers();
        request.setAttribute("sellersList", sellersList);
        request.getRequestDispatcher("viewSellers.jsp").forward(request, response);
    }
}
