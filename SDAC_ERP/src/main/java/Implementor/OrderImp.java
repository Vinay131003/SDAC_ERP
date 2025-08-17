package Implementor;

import Pojo.Orders;
import java.util.List;
import Order_Dao.PlaceOrderDao;
import Order_Dao.UpdateOrderStatusDao;
import Order_Dao.ViewAllOrdersDao;
import Order_Dao.ViewOrderDao;
import Order_Dao.ViewOrdersByConsumerDao;
import Order_Dao.TrackOrderDao;
import Order_Dao.ConsumerOrderSummaryDao; // Import ConsumerOrderSummaryDao
import Operation.Order;

public class OrderImp implements Order {

    // DAO instances
    private PlaceOrderDao placeOrderDao = new PlaceOrderDao();
    private UpdateOrderStatusDao updateOrderStatusDao = new UpdateOrderStatusDao();
    private ViewAllOrdersDao viewAllOrdersDao = new ViewAllOrdersDao();
    private ViewOrderDao viewOrderDao = new ViewOrderDao();
    private ViewOrdersByConsumerDao viewOrdersByConsumerDao = new ViewOrdersByConsumerDao();
    private TrackOrderDao trackOrderDao = new TrackOrderDao();
    private ConsumerOrderSummaryDao consumerOrderSummaryDao = new ConsumerOrderSummaryDao();

    // Method to place an order
    @Override
    public void placeOrder(Orders order) {
        placeOrderDao.placeOrder(order);
    }

    // Method to view order details
    @Override
    public Orders viewOrder(int orderID) {
        return viewOrderDao.viewOrder(orderID);
    }

    // Method to update order status
    @Override
    public void updateOrderStatus(Orders order) {
        updateOrderStatusDao.updateOrderStatus(order);
    }

    // Method to view all orders
    @Override
    public List<Orders> viewAllOrders() {
        return viewAllOrdersDao.viewAllOrders();
    }

    // Method to get orders by consumer port ID
    @Override
    public List<Orders> getOrdersByConsumer(String consumerPortID) {
        return viewOrdersByConsumerDao.getOrdersByConsumer(consumerPortID);
    }

    // New Method to get consumer order summary
    @Override
    public List<Orders> getConsumerOrderSummary(String consumerPortID) {
        return consumerOrderSummaryDao.fetchOrdersWithProductInfo(consumerPortID);
    }

    // New Method to track an order
    @Override
    public Orders trackOrder(int orderID) {
        return trackOrderDao.trackOrder(orderID);
    }
}
