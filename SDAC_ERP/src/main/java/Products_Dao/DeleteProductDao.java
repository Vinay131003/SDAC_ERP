package Products_Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class DeleteProductDao {
    public boolean deleteProduct(int productID) {
        boolean isDeleted = false; // To track if deletion was successful
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL DeleteProduct(?)";
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                stmt.setInt(1, productID);
                stmt.execute();
                isDeleted = true; // If execution was successful, mark as deleted
                System.out.println("Product deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted; // Return the success status
    }
}
