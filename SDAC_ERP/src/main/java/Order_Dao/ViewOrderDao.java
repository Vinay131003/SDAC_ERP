package Order_Dao;

import Pojo.Orders;
import java.sql.*;
import Connection.GetConnection;

public class ViewOrderDao {

    // Method to view order details
    public Orders viewOrder(int orderID) {
        Orders order = null;
        String sql = "CALL ViewOrder(?)";

        try (Connection conn = GetConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, orderID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Orders(
                        rs.getInt("orderID"),
                        rs.getInt("productID"),
                        rs.getString("consumerPortID"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("orderDate"),
                        rs.getBoolean("orderPlaced"),
                        rs.getBoolean("shipped"),
                        rs.getBoolean("outForDelivery"),
                        rs.getBoolean("delivered")
                    );
                }
            }
        } catch (SQLException e) {
            // Improved error handling
            e.printStackTrace();
            // Optionally log this error using a logging framework
        }
        System.out.println("Order Fetched: " + order);

        return order;
    }
}
