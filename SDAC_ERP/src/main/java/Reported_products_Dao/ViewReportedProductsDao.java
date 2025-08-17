package Reported_products_Dao;

import Pojo.ReportedProducts;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.GetConnection;

public class ViewReportedProductsDao {
    public List<ReportedProducts> viewReportedProducts() {
        List<ReportedProducts> reportedProductsList = new ArrayList<>();
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL ViewReportedProducts()";
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ReportedProducts product = new ReportedProducts(
                    rs.getString("Report_ID"),
                    rs.getString("Consumer_Port_ID"),
                    rs.getInt("Product_ID"),
                    rs.getString("Issue_Type"),
                    rs.getString("Solution"),
                    rs.getTimestamp("Report_Date")
                );
                reportedProductsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reportedProductsList;
    }
}
