package Order_Dao;

import Pojo.Orders;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Connection.GetConnection;

public class ViewAllOrdersDao {

    // Method to view all orders
    public List<Orders> viewAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL ViewAllOrders()";
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Orders order = new Orders(
                    rs.getInt("order_ID"),
                    rs.getInt("Product_ID"),
                    rs.getString("consumer_Port_ID"),
                    rs.getInt("Quantity"),
                    rs.getTimestamp("Order_Date"),
                    rs.getBoolean("Order_Placed"),
                    rs.getBoolean("Shipped"),
                    rs.getBoolean("Out_For_Delivery"),
                    rs.getBoolean("Delivered")
                );
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }
}
