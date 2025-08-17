package Consumer_Dao;

import Connection.GetConnection;
import Pojo.ConsumerPort;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewConsumerDao {
    public List<ConsumerPort> viewConsumers() {
        List<ConsumerPort> consumersList = new ArrayList<>();
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL view_all_consumers()";
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ConsumerPort consumer = new ConsumerPort(
                        rs.getString("portID"),
                        rs.getString("password"),
                        rs.getString("location"),
                        rs.getString("role")
                );
                consumersList.add(consumer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consumersList;
    }
}
