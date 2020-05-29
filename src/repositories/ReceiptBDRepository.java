package repositories;

import manager.database;
import model.Receipt;
import repositories.ReceiptRepository;
import service.BDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptBDRepository implements ReceiptRepository {
    @Override
    public void add(Receipt receipts) {
        String sql = "INSERT INTO receipt VALUES (NULL,?, ?, ?, ?)";
        try {
            PreparedStatement statement =  BDService.getStatament(sql);
            statement.setInt(1, receipts.getReceiptById());
            statement.setInt(2, receipts.getReceiptsByRegisterId());
            statement.setInt(3, receipts.getReceiptsByCashierId());
            statement.setInt(4, receipts.getReceiptByCouponId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Receipt getReceiptById(Integer id) {
        String sql = "SELECT * FROM receipt WHERE id = ?";
        try{
            PreparedStatement statement = BDService.getStatament(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Receipt re = new Receipt();
                re.setReceiptById(set.getInt("idReceipt"));
                re.setReceiptsByRegisterId(set.getInt("idCashier"));
                re.setReceiptsByCashierId(set.getInt("idCoupon"));
                re.setReceiptByCouponId(set.getInt("id"));
                return re;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Receipt> getCoupon() {
        String sql = "SELECT * FROM receipt";
        try{
            Map<Integer, Receipt> mp = new HashMap<>();
            PreparedStatement statement = BDService.getStatament(sql);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Receipt re = new Receipt();
                re.setReceiptById(set.getInt("idReceipt"));
                re.setReceiptsByRegisterId(set.getInt("idCashier"));
                re.setReceiptsByCashierId(set.getInt("idCoupon"));
                re.setReceiptByCouponId(set.getInt("id"));
                mp.put(set.getInt("id"),re);
            }
            return mp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
