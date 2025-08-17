package Consumer_Dao;

import Connection.GetConnection;
import Pojo.ConsumerPort;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterConsumerDao {
    public void registerConsumer(ConsumerPort consumer) {
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL RegisterUser(?, ?, ?, ?, ?)";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, consumer.getPortID());
            stmt.setString(2, consumer.getPassword());
            stmt.setString(3, consumer.getPassword());
            stmt.setString(4, consumer.getLocation());
            stmt.setString(5, consumer.getRole());

            stmt.execute();
            System.out.println("Consumer registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
