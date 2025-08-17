package Products_Dao;

import Pojo.Products;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class AddProductDao {
    public void addProduct(Products product) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL AddProduct(?, ?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, product.getProductID());
            stmt.setString(2, product.getProductName());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.execute();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
