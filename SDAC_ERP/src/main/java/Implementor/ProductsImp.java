package Implementor;

import Pojo.Products;
import java.util.List;
import Products_Dao.AddProductDao;
import Products_Dao.DeleteProductDao;
import Products_Dao.UpdateProductDao;
import Products_Dao.ViewProductsDao;
import Operation.ProductsOperation;

public class ProductsImp implements ProductsOperation {

    // DAO instances
    private AddProductDao addProductDao = new AddProductDao();
    private DeleteProductDao deleteProductDao = new DeleteProductDao();
    private UpdateProductDao updateProductDao = new UpdateProductDao();
    private ViewProductsDao viewProductsDao = new ViewProductsDao();

    @Override
    public void addProduct(Products product) {
        addProductDao.addProduct(product);
    }

    @Override
    public void updateProduct(Products product) {
        updateProductDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productID) {
        deleteProductDao.deleteProduct(productID);
    }

    @Override
    public List<Products> viewProducts() {
        return viewProductsDao.viewProducts();
    }
}
