package Order_Dao;

import Pojo.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.GetConnection;

public class ViewOrdersByConsumerDao {

    // Method to fetch orders for a specific consumer
    public List<Orders> getOrdersByConsumer(String consumerPortID) {
        List<Orders> ordersList = new ArrayList<>();
        String sql = "CALL GetOrdersByConsumer(?)"; // Ensure this procedure exists and works

        try (Connection conn = GetConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, consumerPortID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Orders order = new Orders(
                        rs.getInt("Order_ID"),          // Updated column names
                        rs.getInt("Product_ID"),
                        rs.getString("Consumer_Port_ID"),
                        rs.getInt("Quantity"),
                        rs.getTimestamp("Order_Date"),
                        rs.getBoolean("Order_Placed"),
                        rs.getBoolean("Shipped"),
                        rs.getBoolean("Out_For_Delivery"),
                        rs.getBoolean("Delivered")
                    );
                    ordersList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using logging instead of printStackTrace
        }

        return ordersList;
    }
}
