package Products_Dao;

import Pojo.Products;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class UpdateProductDao {
    public void updateProduct(Products product) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL UpdateProduct(?, ?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, product.getProductID());

            // Set only non-null values to update specific fields
            if (product.getProductName() != null) {
                stmt.setString(2, product.getProductName());
            } else {
                stmt.setNull(2, java.sql.Types.VARCHAR);
            }
            if (product.getQuantity() != null) {
                stmt.setInt(3, product.getQuantity());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            if (product.getPrice() != null) {
                stmt.setDouble(4, product.getPrice());
            } else {
                stmt.setNull(4, java.sql.Types.DOUBLE);
            }

            stmt.execute();
            System.out.println("Product updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
