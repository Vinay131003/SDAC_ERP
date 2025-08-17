package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String action = request.getParameter("action");

        // Determine role and forward to appropriate controller
        if ("login".equals(action)) {
            if ("consumer".equals(role)) {
                request.getRequestDispatcher("ConsumerController").forward(request, response);
            } else if ("seller".equals(role)) {
                request.getRequestDispatcher("SellerController").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } else if ("register".equals(action)) {
            if ("consumer".equals(role)) {
                request.getRequestDispatcher("ConsumerController").forward(request, response);
            } else if ("seller".equals(role)) {
                request.getRequestDispatcher("SellerController").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
