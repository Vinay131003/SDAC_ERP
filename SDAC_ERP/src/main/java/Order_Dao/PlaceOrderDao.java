package Order_Dao;

import Pojo.Orders;
import java.sql.*;
import Connection.GetConnection;

public class PlaceOrderDao {

    // Method to place an order
    public void placeOrder(Orders order) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL PlaceOrder(?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, order.getProductID());
            stmt.setString(2, order.getConsumerPortID());
            stmt.setInt(3, order.getQuantity());
            stmt.execute();
            System.out.println("Order placed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
