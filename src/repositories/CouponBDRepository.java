package repositories;

import manager.database;
import model.Coupon;
import repositories.CouponRepository;
import service.BDService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponBDRepository implements CouponRepository{
    @Override
    public void add(Coupon coupons) {
        String sql = "INSERT INTO coupons VALUES (NULL,?, ?, ?)";
        try {
            PreparedStatement statement =  BDService.getStatament(sql);
            statement.setInt(1, coupons.getCouponById());
            statement.setInt(2, coupons.getCouponsByDiscount());
            statement.setBoolean(3, coupons.getCouponsByUsedState());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Coupon getCouponById(Integer id) {
        String sql = "SELECT * FROM coupons WHERE id = ?";
        try{
            PreparedStatement statement = BDService.getStatament(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()){
                Coupon co = new Coupon();
                co.setCouponId(set.getString("idCoupon"));
                co.setCouponDiscount(set.getInt("salePrice"));
                co.setCouponUsedState(set.getBoolean("usedCoupon"));
                return co;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Coupon> getCoupon() {
        String sql = "SELECT * FROM coupons";
        try{
            Map<Integer, Coupon> mp = new HashMap<>();
            PreparedStatement statement = BDService.getStatament(sql);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                Coupon co = new Coupon();
                co.setCouponId(set.getInt("idCoupon"));
                co.setCouponDiscount(set.getInt("salePrice"));
                co.setCouponUsedState(set.getBoolean("usedCoupon"));
                mp.put(set.getInt("idCoupon"),co);
            }
            return mp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
