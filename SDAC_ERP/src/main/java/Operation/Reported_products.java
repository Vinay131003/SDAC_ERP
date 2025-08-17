package Operation;

import Pojo.ReportedProducts;
import java.util.List;

public interface Reported_products {

    // Report a product issue
    public boolean reportProduct(ReportedProducts reportedProduct);

    // View all reported products
    public List<ReportedProducts> viewReportedProducts();
    
    // Mark a reported product as solved
    public boolean markAsSolved(String reportId);
}
