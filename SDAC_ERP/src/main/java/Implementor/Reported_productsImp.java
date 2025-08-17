package Implementor;

import Pojo.ReportedProducts;
import java.util.List;
import Reported_products_Dao.ReportProductDao;
import Reported_products_Dao.ViewReportedProductsDao;
import Reported_products_Dao.UpdateReportedProductDao;  // Import the new DAO for updating reports
import Operation.Reported_products;

public class Reported_productsImp implements Reported_products {

    // DAO instances
    private ReportProductDao reportProductDao = new ReportProductDao();
    private ViewReportedProductsDao viewReportedProductsDao = new ViewReportedProductsDao();
    private UpdateReportedProductDao updateReportedProductDao = new UpdateReportedProductDao();  // New DAO instance

    @Override
    public boolean reportProduct(ReportedProducts reportedProduct) {
        return reportProductDao.reportProduct(reportedProduct);  // Assuming the DAO method returns a boolean
    }

    @Override
    public List<ReportedProducts> viewReportedProducts() {
        return viewReportedProductsDao.viewReportedProducts();
    }

    // New method to mark the product report as solved
    @Override
    public boolean markAsSolved(String reportId) {
        // Call the DAO method and return its result
        return updateReportedProductDao.markAsSolved(reportId);
    }

}
