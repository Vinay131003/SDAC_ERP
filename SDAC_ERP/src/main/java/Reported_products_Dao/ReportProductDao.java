package Reported_products_Dao;

import Pojo.ReportedProducts;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class ReportProductDao {
    public boolean reportProduct(ReportedProducts reportedProduct) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL ReportProduct(?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, reportedProduct.getProductId());
            stmt.setString(2, reportedProduct.getConsumerPortId());
            stmt.setString(3, reportedProduct.getIssueType());
            stmt.execute();
            System.out.println("Product reported successfully!");
            return true; // Return true for successful reporting
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false for failure
        }
    }
}
