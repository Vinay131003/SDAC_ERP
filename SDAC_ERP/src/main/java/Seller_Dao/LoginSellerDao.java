package Seller_Dao;

import Pojo.SellerPort;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.GetConnection;

public class LoginSellerDao {
    public boolean loginSeller(SellerPort seller) {
        boolean loginSuccess = false;
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL LoginUser(?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, seller.getPortID());
            stmt.setString(2, seller.getPassword());
            stmt.setString(3, "seller");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                loginSuccess = true;
                System.out.println("Login successful for seller: " + seller.getPortID());
            } else {
                System.out.println("Invalid credentials for seller.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginSuccess;
    }
}
