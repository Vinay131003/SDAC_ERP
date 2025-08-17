package Controller;

import Pojo.Orders;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import Implementor.OrderImp;
import Order_Dao.ViewAllOrdersDao;
 // Add this annotation for servlet mapping
public class OrdersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderImp orderService;
    private ViewAllOrdersDao viewAllOrdersDao;

    public void init() {
        orderService = new OrderImp();
        viewAllOrdersDao = new ViewAllOrdersDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "placeOrder":
                    placeOrder(request, response);
                    break;
                case "updateStatus":
                    updateOrderStatus(request, response);
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
                case "viewOrder":
                    viewOrder(request, response);
                    break;
                case "viewAllOrders":
                    viewAllOrders(request, response);
                    break;
                case "viewOrdersByConsumer":
                    viewOrdersByConsumer(request, response);
                    break;
                case "trackOrder":
                    trackOrder(request, response);
                    break;
                case "consumerOrderSummary":
                	viewConsumerOrderSummary(request, response);
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        }
    }

    private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String consumerPortID = (String) request.getSession().getAttribute("port_id");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());

            Orders order = new Orders();
            order.setProductID(productID);
            order.setConsumerPortID(consumerPortID);
            order.setQuantity(quantity);
            order.setOrderDate(orderDate);

            orderService.placeOrder(order);
            response.sendRedirect("consumerDashboard.jsp");
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?message=Invalid input");
        } catch (Exception e) {
            response.sendRedirect("error.jsp?message=Order placement failed: " + e.getMessage());
        }
    }

    private void viewOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdParam = request.getParameter("orderID");

        if (orderIdParam == null || !orderIdParam.matches("\\d+")) {
            response.sendRedirect("orderNotFound.jsp");
            return;
        }

        int orderID = Integer.parseInt(orderIdParam);
        Orders order = orderService.viewOrder(orderID);
        if (order != null) {
            request.setAttribute("order", order);
            request.getRequestDispatcher("viewOrder.jsp").forward(request, response);
        } else {
            response.sendRedirect("orderNotFound.jsp");
        }
    }

    private void updateOrderStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderID = Integer.parseInt(request.getParameter("orderID"));
        String status = request.getParameter("status");

        Orders order = new Orders();
        order.setOrderID(orderID);

        switch (status) {
            case "Order_Placed":
                order.setOrderPlaced(true);
                break;
            case "Shipped":
                order.setShipped(true);
                break;
            case "Out_For_Delivery":
                order.setOutForDelivery(true);
                break;
            case "Delivered":
                order.setDelivered(true);
                break;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }

        orderService.updateOrderStatus(order);
        response.sendRedirect("viewAllOrders.jsp");
    }

    private void viewAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (viewAllOrdersDao == null) {
            viewAllOrdersDao = new ViewAllOrdersDao();
        }

        List<Orders> ordersList = viewAllOrdersDao.viewAllOrders();
        request.setAttribute("ordersList", ordersList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("viewAllOrders.jsp");
        dispatcher.forward(request, response);
    }

    private void trackOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderIdParam = request.getParameter("orderID");

        if (orderIdParam == null || !orderIdParam.matches("\\d+")) {
            response.sendRedirect("orderNotFound.jsp");
            return;
        }

        int orderID = Integer.parseInt(orderIdParam);
        Orders order = orderService.trackOrder(orderID);
        if (order != null) {
            request.setAttribute("order", order);
            request.getRequestDispatcher("trackOrder.jsp").forward(request, response);
        } else {
            response.sendRedirect("orderNotFound.jsp");
        }
    }

    private void viewOrdersByConsumer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consumerPortID = (String) request.getSession().getAttribute("port_id");
        List<Orders> consumerOrders = orderService.getOrdersByConsumer(consumerPortID);
        request.setAttribute("consumerOrders", consumerOrders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewOrdersByConsumer.jsp");
        dispatcher.forward(request, response);
    }

    private void viewConsumerOrderSummary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consumerPortID = request.getParameter("consumerId"); // Get the consumer ID from the form

        if (consumerPortID != null && !consumerPortID.isEmpty()) {
            List<Orders> consumerOrderSummary = orderService.getConsumerOrderSummary(consumerPortID);
            request.setAttribute("consumerOrderSummary", consumerOrderSummary);
            RequestDispatcher dispatcher = request.getRequestDispatcher("consumerOrderSummary.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp?message=Invalid Consumer ID");
        }
    }

}
