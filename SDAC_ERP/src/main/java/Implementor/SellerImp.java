package Implementor;

import Operation.Seller;
import Pojo.SellerPort;
import Seller_Dao.DeleteSellerProfileDao;
import Seller_Dao.LoginSellerDao;
import Seller_Dao.RegisterSellerDao;
import Seller_Dao.UpdateSellerProfileDao;
import Seller_Dao.ViewSellersDao;

import java.util.List;

public class SellerImp implements Seller {

    // DAO instances
    private RegisterSellerDao registerSellerDao = new RegisterSellerDao();
    private LoginSellerDao loginSellerDao = new LoginSellerDao();
    private UpdateSellerProfileDao updateSellerProfileDao = new UpdateSellerProfileDao();
    private DeleteSellerProfileDao deleteSellerProfileDao = new DeleteSellerProfileDao();
    private ViewSellersDao viewSellersDao = new ViewSellersDao();

    @Override
    public void registerSeller(SellerPort seller) {
        registerSellerDao.registerSeller(seller);
    }

    @Override
 // In SellerImp
    public boolean loginSeller(SellerPort seller) {
        return loginSellerDao.loginSeller(seller); // Assuming you have an instance of LoginSellerDao
    }


    @Override
    public void updateSellerProfile(SellerPort seller) {
        updateSellerProfileDao.updateSellerProfile(seller);
    }

    @Override
    public void deleteSellerProfile(SellerPort seller) {
        deleteSellerProfileDao.deleteSellerProfile(seller.getPortID());
    }

    @Override
    public List<SellerPort> viewSellers() {
        return viewSellersDao.viewSellers();
    }
}
