package Seller_Dao;

import Pojo.SellerPort;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class RegisterSellerDao {
    public void registerSeller(SellerPort seller) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL RegisterUser(?, ?, ?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, seller.getPortID());
            stmt.setString(2, seller.getPassword());
            stmt.setString(3, seller.getPassword());
            stmt.setString(4, null);
            stmt.setString(5, seller.getRole());
            stmt.execute();
            System.out.println("Seller registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
