package Controller;

import Pojo.ConsumerPort;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import Implementor.ConsumerImp;


public class ConsumerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConsumerImp consumerService;

    public void init() {
        consumerService = new ConsumerImp(); // Initialize the ConsumerImp service class
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "register":
                    registerConsumer(request, response);
                    break;
                case "login":
                    loginConsumer(request, response);
                    break;
                case "update":
                    updateConsumerProfile(request, response);
                    break;
                case "delete":
                    deleteConsumerProfile(request, response);
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

        if (action != null && action.equals("viewAll")) {
            viewConsumers(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    // Register consumer
    private void registerConsumer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");
        String password = request.getParameter("password");
        String location = request.getParameter("location");
        String role = request.getParameter("role");

        ConsumerPort consumer = new ConsumerPort(portID, password, location, role);
        consumerService.registerConsumer(consumer);
        response.sendRedirect("login.jsp");
    }

    // Login consumer
 // Login consumer
    private void loginConsumer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");
        String password = request.getParameter("password");

        ConsumerPort consumer = new ConsumerPort();
        consumer.setPortID(portID);
        consumer.setPassword(password);

        boolean isLoggedIn = consumerService.loginConsumer(consumer);

        if (isLoggedIn) {
            // If login is successful, store the portID in the session
            request.getSession().setAttribute("port_id", portID);
            
            // Redirect to the dashboard or any other page
            response.sendRedirect("consumerDashboard.jsp");
        } else {
            // Redirect to login page with an error message
            response.sendRedirect("login.jsp?error=invalid");
        }
    }

    // Update consumer profile
 // Method to update the consumer profile
    private void updateConsumerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");
        String password = request.getParameter("password");
        String location = request.getParameter("location");

        // Create Consumer object
        ConsumerPort consumer = new ConsumerPort();
        consumer.setPortID(portID);
        consumer.setPassword(password);

        // Only set location if it's not empty
        if (location != null && !location.trim().isEmpty()) {
            consumer.setLocation(location);
        } else {
            consumer.setLocation(null); // Set to null to indicate no update for location
        }

        // Call the service to update the consumer's profile
        consumerService.updateConsumerProfile(consumer);

        // Redirect to profile page
        response.sendRedirect("profile.jsp");
    }
    // Delete consumer profile
    private void deleteConsumerProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String portID = request.getParameter("portID");

        ConsumerPort consumer = new ConsumerPort();
        consumer.setPortID(portID);
        System.out.println(portID);
        consumerService.deleteConsumerProfile(consumer);
        response.sendRedirect("consumers.jsp");
    }

    // View all consumers
    private void viewConsumers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConsumerPort> consumers = consumerService.viewConsumers();
        request.setAttribute("consumers", consumers);
        request.getRequestDispatcher("viewConsumers.jsp").forward(request, response);
    }
}
