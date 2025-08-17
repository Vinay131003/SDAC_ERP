package Consumer_Dao;

import Connection.GetConnection;
import Pojo.ConsumerPort;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class UpdateConsumerProfileDao {
    public void updateConsumerProfile(ConsumerPort consumer) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL UpdateProfile(?, ?, ?)";  // Call stored procedure
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, consumer.getPortID());    // PortID remains constant
            stmt.setString(2, consumer.getPassword());  // Update password
            stmt.setString(3, consumer.getLocation());  // Update location

            stmt.execute();  // Execute the stored procedure
            System.out.println("Consumer profile updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();  // Handle SQL exceptions
        }
    }
}
