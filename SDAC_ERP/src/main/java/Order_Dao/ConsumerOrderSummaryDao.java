package Order_Dao;

import Pojo.Orders;
import Pojo.Products;
import Connection.GetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsumerOrderSummaryDao {

    public List<Orders> fetchOrdersWithProductInfo(String consumerPortID) {
        List<Orders> orderList = new ArrayList<>();

        String query = "CALL GetConsumerOrders(?)"; // Stored procedure to get orders

        try (Connection conn = GetConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, consumerPortID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Orders order = new Orders();
                Products product = new Products();

                order.setOrderID(rs.getInt("Order_ID"));
                order.setProductID(rs.getInt("Product_ID"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setOrderDate(rs.getTimestamp("Order_Date"));

                // Populate product details from result set
                product.setProductID(rs.getInt("Product_ID"));
                order.setProductName(rs.getString("Product_Name"));
                orderList.add(order);
                
                // Print or add the product info as needed
                System.out.println("Order: " + order);
                System.out.println("Product Name: " + product.getProductName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }
}
