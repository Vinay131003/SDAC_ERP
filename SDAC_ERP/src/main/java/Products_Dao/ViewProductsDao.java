package Products_Dao;

import Pojo.Products;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Connection.GetConnection;

public class ViewProductsDao {
    public List<Products> viewProducts() {
        List<Products> productsList = new ArrayList<>();
        try (Connection conn = GetConnection.getConnection()) {
            String sql = "CALL ViewProducts()";
            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setProductID(rs.getInt("Product_ID"));        // Match column name 'Product_ID'
                product.setProductName(rs.getString("Product_Name")); // Match column name 'Product_Name'
                
                // Use Integer for quantity and Double for price
                product.setQuantity(rs.getObject("Quantity", Integer.class)); // Changed to Integer
                product.setPrice(rs.getObject("Price", Double.class));         // Changed to Double
                
                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }
    

}
