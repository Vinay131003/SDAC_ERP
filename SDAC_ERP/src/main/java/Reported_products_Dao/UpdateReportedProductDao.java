package Reported_products_Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Connection.GetConnection;

public class UpdateReportedProductDao {
    public boolean markAsSolved(String reportId) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL UpdateSolutionToSolved(?)"; // Adjust this to your stored procedure name
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, reportId);
            stmt.execute();
            System.out.println("Product report marked as solved successfully!");
            return true; // Return true if the operation was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an exception
        }
    }
}
