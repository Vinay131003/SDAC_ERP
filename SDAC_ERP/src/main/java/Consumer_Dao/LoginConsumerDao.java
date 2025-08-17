package Consumer_Dao;

import Connection.GetConnection;
import Pojo.ConsumerPort;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginConsumerDao {
    public boolean loginConsumer(ConsumerPort consumer) {
        boolean isSuccess = false;
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "{CALL LoginUser(?, ?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, consumer.getPortID());
            stmt.setString(2, consumer.getPassword());
            stmt.setString(3, "consumer"); // Assuming this method is for consumer login

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
