package Consumer_Dao;

import Connection.GetConnection;
import Pojo.ConsumerPort;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteConsumerProfileDao {
    public void deleteConsumerProfile(ConsumerPort consumer) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL delete_consumer_profile(?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, consumer.getPortID());
            stmt.execute();
            System.out.println("Consumer profile deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
