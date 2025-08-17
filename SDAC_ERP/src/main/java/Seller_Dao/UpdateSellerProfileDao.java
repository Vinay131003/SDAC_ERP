package Seller_Dao;

import Pojo.SellerPort;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class UpdateSellerProfileDao {
    public void updateSellerProfile(SellerPort seller) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL update_seller_profile(?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, seller.getPortID());
            stmt.setString(2, seller.getPassword());
            stmt.setString(3, seller.getRole());
            stmt.execute();
            System.out.println("Seller profile updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
