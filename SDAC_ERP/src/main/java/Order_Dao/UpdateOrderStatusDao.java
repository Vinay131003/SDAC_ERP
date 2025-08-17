package Order_Dao;

import Pojo.Orders;
import java.sql.*;
import Connection.GetConnection;

public class UpdateOrderStatusDao {

    // Method to update order status
	public void updateOrderStatus(Orders order) {
	    try (Connection conn = GetConnection.getConnection()) {
	        String sql = "CALL UpdateOrderStatus(?, ?)";
	        CallableStatement stmt = conn.prepareCall(sql);
	        stmt.setInt(1, order.getOrderID());

	        // Determine the status based on boolean flags
	        String status = null;
	        if (order.isDelivered()) {
	            status = "Delivered";
	        } else if (order.isOutForDelivery()) {
	            status = "Out_For_Delivery";
	        } else if (order.isShipped()) {
	            status = "Shipped";
	        } else if (order.isOrderPlaced()) {
	            status = "Order_Placed";
	        }

	        stmt.setString(2, status);
	        stmt.execute();
	        System.out.println("Order status updated successfully!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
