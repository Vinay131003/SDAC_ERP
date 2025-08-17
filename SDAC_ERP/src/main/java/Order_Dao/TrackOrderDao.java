package Order_Dao;

import Pojo.Orders;
import java.sql.*;
import Connection.GetConnection;

public class TrackOrderDao {
    public Orders trackOrder(int orderID) {
        Orders order = null;
        String sql = "CALL TrackOrder(?)"; // Call the stored procedure

        try (Connection conn = GetConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, orderID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Orders(
                        rs.getInt("Order_ID"),
                        rs.getInt("Product_ID"),
                        rs.getString("Consumer_Port_ID"),
                        rs.getInt("Quantity"),
                        rs.getTimestamp("Order_Date"),
                        rs.getBoolean("Order_Placed_Status"),
                        rs.getBoolean("Shipped_Status"),
                        rs.getBoolean("Out_For_Delivery_Status"),
                        rs.getBoolean("Delivered_Status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return order;
    }
}
