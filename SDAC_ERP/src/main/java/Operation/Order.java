package Operation;

import Pojo.Orders;
import java.util.List;

public interface Order {

    // Place a new order
    public void placeOrder(Orders order);

    // View order details
    public Orders viewOrder(int orderID);

    // Update the order status
    public void updateOrderStatus(Orders order);

    // View all orders
    public List<Orders> viewAllOrders();

    // View orders by consumer port ID
    public List<Orders> getOrdersByConsumer(String consumerPortID);
    
    // Track order
    Orders trackOrder(int orderID);
    
    // New Method to get consumer order summary
    public List<Orders> getConsumerOrderSummary(String consumerPortID);
}
