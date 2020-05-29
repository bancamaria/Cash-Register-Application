package repositories;

import exceptions.InexistentCashierIdException;
import model.Coupon;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapCouponRepository implements CouponRepository{
    private Map<Integer, Coupon> coupons;
    public MapCouponRepository(){ coupons = new HashMap<>(); }

    @Override
    public void addRoute(Coupon r){ coupons.put(r.getId(),r); }

    @Override
    public Coupon findRouteById(Integer id){
        Coupon coupons = null;
        try{
            if(coupons.get(id).equals(null))
                throw new InexistentCashierIdException();
            coupons = coupons.get(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return coupons;
    }
}

