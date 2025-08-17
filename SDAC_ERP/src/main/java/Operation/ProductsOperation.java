package Operation;

import Pojo.Products;
import java.util.List;

public interface ProductsOperation {

    // Add a new product
    public void addProduct(Products product);

    // Update an existing product
    public void updateProduct(Products product);

    // Delete a product
    public void deleteProduct(int productID);

    // View all products
    public List<Products> viewProducts();
}
