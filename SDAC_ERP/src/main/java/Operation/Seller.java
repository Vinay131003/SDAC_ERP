package Operation;

import Pojo.SellerPort;
import java.util.List;

public interface Seller {

    // Register a new seller
    public void registerSeller(SellerPort seller);

    // Login as a seller
    public boolean loginSeller(SellerPort seller);

    // Update seller profile
    public void updateSellerProfile(SellerPort seller);

    // Delete seller profile
    public void deleteSellerProfile(SellerPort seller);

    // View all sellers
    public List<SellerPort> viewSellers();
}
