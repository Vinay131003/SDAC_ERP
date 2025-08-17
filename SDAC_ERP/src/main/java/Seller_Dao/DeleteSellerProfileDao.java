package Seller_Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class DeleteSellerProfileDao {
    public void deleteSellerProfile(String portID) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL delete_seller_profile(?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, portID);
            stmt.execute();
            System.out.println("Seller profile deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
