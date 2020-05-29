package repositories;

import manager.database;
import model.SoldProduct;
import repositories.SoldProductRepository;
import service.BDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoldProductBDRegister implements SoldProductRepository {
    @Override
    public void add(SoldProduct soldProducts) {
        String sql = "INSERT INTO soldproduct VALUES (NULL,?, ?)";
        try {
            PreparedStatement statement =  BDService.getStatament(sql);
            statement.setInt(1, soldProducts.getSoldProductByReceiptIdAndProductId());
            statement.setInt(2, soldProducts.getSoldProductsByReceiptId());
            statement.setInt(3, soldProducts.getSoldProductsByProductId());
            statement.setInt(4, soldProducts.getSoldProductsByQuantity());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public SoldProduct getSoldProductById(Integer id) {
        String sql = "SELECT * FROM soldproduct WHERE id = ?";
        try{
            PreparedStatement statement = BDService.getStatament(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                SoldProduct sp = new SoldProduct();
                sp.getSoldProductByReceiptIdAndProductId(set.getInt("idReceipt"));
                sp.getSoldProductsByReceiptId(set.getInt("idReceipt"));
                sp.getSoldProductsByProductId(set.getInt("idProduct"));
                sp.getSoldProductsByQuantity(set.getInt("quantity"));
                return sp;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, SoldProduct> getCoupon() {
        String sql = "SELECT * FROM soldproduct";
        try{
            Map<Integer, SoldProduct> mp = new HashMap<>();
            PreparedStatement statement = BDService.getStatament(sql);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                SoldProduct sp = new SoldProduct();
                sp.setSoldProductByReceiptIdAndProductId(set.getInt("idReceipt"));
                sp.setSoldProductsByReceiptId(set.getInt("idReceipt"));
                sp.setSoldProductsByProductId(set.getInt("idProduct"));
                sp.setSoldProductsByQuantity(set.getInt("quantity"));
                mp.put(set.getInt("id"),sp);
            }
            return mp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
