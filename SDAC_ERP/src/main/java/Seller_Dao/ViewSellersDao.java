package Seller_Dao;

import Pojo.SellerPort;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.GetConnection;

public class ViewSellersDao {
    public List<SellerPort> viewSellers() {
        List<SellerPort> sellersList = new ArrayList<>();
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL view_all_sellers()";
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                SellerPort seller = new SellerPort(
                    rs.getString("portID"),
                    rs.getString("password"),
                    rs.getString("role")
                );
                sellersList.add(seller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sellersList;
    }
}
